package com.google.firebase.remoteconfig.internal;

import android.text.format.DateUtils;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import b.a.a.a.a;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigFetchThrottledException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class ConfigFetchHandler {

    @VisibleForTesting
    public static final int HTTP_TOO_MANY_REQUESTS = 429;
    public final Provider<AnalyticsConnector> analyticsConnector;
    public final Clock clock;
    public final Map<String, String> customHttpHeaders;
    public final Executor executor;
    public final ConfigCacheClient fetchedConfigsCache;
    public final FirebaseInstallationsApi firebaseInstallations;
    public final ConfigFetchHttpClient frcBackendApiClient;
    public final ConfigMetadataClient frcMetadata;
    public final Random randomGenerator;
    public static final long DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS = TimeUnit.HOURS.toSeconds(12);

    @VisibleForTesting
    public static final int[] BACKOFF_TIME_DURATIONS_IN_MINUTES = {2, 4, 8, 16, 32, 64, 128, 256};

    public static class FetchResponse {
        public final Date fetchTime;
        public final ConfigContainer fetchedConfigs;

        @Nullable
        public final String lastFetchETag;
        public final int status;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Status {
            public static final int BACKEND_HAS_NO_UPDATES = 1;
            public static final int BACKEND_UPDATES_FETCHED = 0;
            public static final int LOCAL_STORAGE_USED = 2;
        }

        public FetchResponse(Date date, int i, ConfigContainer configContainer, @Nullable String str) {
            this.fetchTime = date;
            this.status = i;
            this.fetchedConfigs = configContainer;
            this.lastFetchETag = str;
        }

        public static FetchResponse forBackendHasNoUpdates(Date date) {
            return new FetchResponse(date, 1, null, null);
        }

        public static FetchResponse forBackendUpdatesFetched(ConfigContainer configContainer, String str) {
            return new FetchResponse(configContainer.getFetchTime(), 0, configContainer, str);
        }

        public static FetchResponse forLocalStorageUsed(Date date) {
            return new FetchResponse(date, 2, null, null);
        }

        public Date getFetchTime() {
            return this.fetchTime;
        }

        public ConfigContainer getFetchedConfigs() {
            return this.fetchedConfigs;
        }

        @Nullable
        public String getLastFetchETag() {
            return this.lastFetchETag;
        }

        public int getStatus() {
            return this.status;
        }
    }

    public ConfigFetchHandler(FirebaseInstallationsApi firebaseInstallationsApi, Provider<AnalyticsConnector> provider, Executor executor, Clock clock, Random random, ConfigCacheClient configCacheClient, ConfigFetchHttpClient configFetchHttpClient, ConfigMetadataClient configMetadataClient, Map<String, String> map) {
        this.firebaseInstallations = firebaseInstallationsApi;
        this.analyticsConnector = provider;
        this.executor = executor;
        this.clock = clock;
        this.randomGenerator = random;
        this.fetchedConfigsCache = configCacheClient;
        this.frcBackendApiClient = configFetchHttpClient;
        this.frcMetadata = configMetadataClient;
        this.customHttpHeaders = map;
    }

    private boolean areCachedFetchConfigsValid(long j, Date date) {
        Date lastSuccessfulFetchTime = this.frcMetadata.getLastSuccessfulFetchTime();
        if (lastSuccessfulFetchTime.equals(ConfigMetadataClient.LAST_FETCH_TIME_NO_FETCH_YET)) {
            return false;
        }
        return date.before(new Date(TimeUnit.SECONDS.toMillis(j) + lastSuccessfulFetchTime.getTime()));
    }

    private FirebaseRemoteConfigServerException createExceptionWithGenericMessage(FirebaseRemoteConfigServerException firebaseRemoteConfigServerException) throws FirebaseRemoteConfigClientException {
        String str;
        int httpStatusCode = firebaseRemoteConfigServerException.getHttpStatusCode();
        if (httpStatusCode == 401) {
            str = "The request did not have the required credentials. Please make sure your google-services.json is valid.";
        } else if (httpStatusCode == 403) {
            str = "The user is not authorized to access the project. Please make sure you are using the API key that corresponds to your Firebase project.";
        } else {
            if (httpStatusCode == 429) {
                throw new FirebaseRemoteConfigClientException("The throttled response from the server was not handled correctly by the FRC SDK.");
            }
            if (httpStatusCode != 500) {
                switch (httpStatusCode) {
                    case 502:
                    case 503:
                    case 504:
                        str = "The server is unavailable. Please try again later.";
                        break;
                    default:
                        str = "The server returned an unexpected error.";
                        break;
                }
            } else {
                str = "There was an internal server error.";
            }
        }
        return new FirebaseRemoteConfigServerException(firebaseRemoteConfigServerException.getHttpStatusCode(), a.a("Fetch failed: ", str), firebaseRemoteConfigServerException);
    }

    private String createThrottledMessage(long j) {
        return String.format("Fetch is throttled. Please wait before calling fetch again: %s", DateUtils.formatElapsedTime(TimeUnit.MILLISECONDS.toSeconds(j)));
    }

    @WorkerThread
    private FetchResponse fetchFromBackend(String str, String str2, Date date) throws FirebaseRemoteConfigFetchThrottledException, FirebaseRemoteConfigServerException {
        try {
            FetchResponse fetchResponseFetch = this.frcBackendApiClient.fetch(this.frcBackendApiClient.createHttpURLConnection(), str, str2, getUserProperties(), this.frcMetadata.getLastFetchETag(), this.customHttpHeaders, date);
            if (fetchResponseFetch.getLastFetchETag() != null) {
                this.frcMetadata.setLastFetchETag(fetchResponseFetch.getLastFetchETag());
            }
            this.frcMetadata.resetBackoff();
            return fetchResponseFetch;
        } catch (FirebaseRemoteConfigServerException e2) {
            ConfigMetadataClient.BackoffMetadata backoffMetadataUpdateAndReturnBackoffMetadata = updateAndReturnBackoffMetadata(e2.getHttpStatusCode(), date);
            if (shouldThrottle(backoffMetadataUpdateAndReturnBackoffMetadata, e2.getHttpStatusCode())) {
                throw new FirebaseRemoteConfigFetchThrottledException(backoffMetadataUpdateAndReturnBackoffMetadata.getBackoffEndTime().getTime());
            }
            throw createExceptionWithGenericMessage(e2);
        }
    }

    private Task<FetchResponse> fetchFromBackendAndCacheResponse(String str, String str2, Date date) {
        try {
            final FetchResponse fetchResponseFetchFromBackend = fetchFromBackend(str, str2, date);
            return fetchResponseFetchFromBackend.getStatus() != 0 ? Tasks.forResult(fetchResponseFetchFromBackend) : this.fetchedConfigsCache.put(fetchResponseFetchFromBackend.getFetchedConfigs()).onSuccessTask(this.executor, new SuccessContinuation() { // from class: b.e.b.h.a.e
                @Override // com.google.android.gms.tasks.SuccessContinuation
                public final Task then(Object obj) {
                    return Tasks.forResult(fetchResponseFetchFromBackend);
                }
            });
        } catch (FirebaseRemoteConfigException e2) {
            return Tasks.forException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchIfCacheExpiredAndNotThrottled, reason: merged with bridge method [inline-methods] */
    public Task<FetchResponse> a(Task<ConfigContainer> task, long j) {
        Task taskContinueWithTask;
        final Date date = new Date(this.clock.currentTimeMillis());
        if (task.isSuccessful() && areCachedFetchConfigsValid(j, date)) {
            return Tasks.forResult(FetchResponse.forLocalStorageUsed(date));
        }
        Date backoffEndTimeInMillis = getBackoffEndTimeInMillis(date);
        if (backoffEndTimeInMillis != null) {
            taskContinueWithTask = Tasks.forException(new FirebaseRemoteConfigFetchThrottledException(createThrottledMessage(backoffEndTimeInMillis.getTime() - date.getTime()), backoffEndTimeInMillis.getTime()));
        } else {
            final Task<String> id = this.firebaseInstallations.getId();
            final Task<InstallationTokenResult> token = this.firebaseInstallations.getToken(false);
            taskContinueWithTask = Tasks.whenAllComplete((Task<?>[]) new Task[]{id, token}).continueWithTask(this.executor, new Continuation() { // from class: b.e.b.h.a.d
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task2) {
                    return this.f607a.a(id, token, date, task2);
                }
            });
        }
        return taskContinueWithTask.continueWithTask(this.executor, new Continuation() { // from class: b.e.b.h.a.c
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task2) {
                return this.f605a.a(date, task2);
            }
        });
    }

    @Nullable
    private Date getBackoffEndTimeInMillis(Date date) {
        Date backoffEndTime = this.frcMetadata.getBackoffMetadata().getBackoffEndTime();
        if (date.before(backoffEndTime)) {
            return backoffEndTime;
        }
        return null;
    }

    private long getRandomizedBackoffDurationInMillis(int i) {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        int[] iArr = BACKOFF_TIME_DURATIONS_IN_MINUTES;
        long millis = timeUnit.toMillis(iArr[Math.min(i, iArr.length) - 1]);
        return (millis / 2) + ((long) this.randomGenerator.nextInt((int) millis));
    }

    @WorkerThread
    private Map<String, String> getUserProperties() {
        HashMap map = new HashMap();
        AnalyticsConnector analyticsConnector = this.analyticsConnector.get();
        if (analyticsConnector == null) {
            return map;
        }
        for (Map.Entry<String, Object> entry : analyticsConnector.getUserProperties(false).entrySet()) {
            map.put(entry.getKey(), entry.getValue().toString());
        }
        return map;
    }

    private boolean isThrottleableServerError(int i) {
        return i == 429 || i == 502 || i == 503 || i == 504;
    }

    private boolean shouldThrottle(ConfigMetadataClient.BackoffMetadata backoffMetadata, int i) {
        return backoffMetadata.getNumFailedFetches() > 1 || i == 429;
    }

    private ConfigMetadataClient.BackoffMetadata updateAndReturnBackoffMetadata(int i, Date date) {
        if (isThrottleableServerError(i)) {
            updateBackoffMetadataWithLastFailedFetchTime(date);
        }
        return this.frcMetadata.getBackoffMetadata();
    }

    private void updateBackoffMetadataWithLastFailedFetchTime(Date date) {
        int numFailedFetches = this.frcMetadata.getBackoffMetadata().getNumFailedFetches() + 1;
        this.frcMetadata.setBackoffMetadata(numFailedFetches, new Date(date.getTime() + getRandomizedBackoffDurationInMillis(numFailedFetches)));
    }

    private void updateLastFetchStatusAndTime(Task<FetchResponse> task, Date date) {
        if (task.isSuccessful()) {
            this.frcMetadata.updateLastFetchAsSuccessfulAt(date);
            return;
        }
        Exception exception = task.getException();
        if (exception == null) {
            return;
        }
        if (exception instanceof FirebaseRemoteConfigFetchThrottledException) {
            this.frcMetadata.updateLastFetchAsThrottled();
        } else {
            this.frcMetadata.updateLastFetchAsFailed();
        }
    }

    public Task<FetchResponse> fetch() {
        return fetch(this.frcMetadata.getMinimumFetchIntervalInSeconds());
    }

    @VisibleForTesting
    public Provider<AnalyticsConnector> getAnalyticsConnector() {
        return this.analyticsConnector;
    }

    public /* synthetic */ Task a(Task task, Task task2, Date date, Task task3) {
        if (task.isSuccessful()) {
            return !task2.isSuccessful() ? Tasks.forException(new FirebaseRemoteConfigClientException("Firebase Installations failed to get installation auth token for fetch.", task2.getException())) : fetchFromBackendAndCacheResponse((String) task.getResult(), ((InstallationTokenResult) task2.getResult()).getToken(), date);
        }
        return Tasks.forException(new FirebaseRemoteConfigClientException("Firebase Installations failed to get installation ID for fetch.", task.getException()));
    }

    public Task<FetchResponse> fetch(final long j) {
        return this.fetchedConfigsCache.get().continueWithTask(this.executor, new Continuation() { // from class: b.e.b.h.a.f
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f612a.a(j, task);
            }
        });
    }

    public /* synthetic */ Task a(Date date, Task task) {
        updateLastFetchStatusAndTime(task, date);
        return task;
    }
}
