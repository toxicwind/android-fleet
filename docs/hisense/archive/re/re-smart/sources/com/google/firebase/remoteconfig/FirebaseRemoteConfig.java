package com.google.firebase.remoteconfig;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.XmlRes;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.AbtException;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import com.google.firebase.remoteconfig.internal.ConfigGetParameterHandler;
import com.google.firebase.remoteconfig.internal.ConfigMetadataClient;
import com.google.firebase.remoteconfig.internal.DefaultsXmlParser;
import e.b.a;
import e.b.b;
import e.b.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseRemoteConfig {
    public static final boolean DEFAULT_VALUE_FOR_BOOLEAN = false;
    public static final byte[] DEFAULT_VALUE_FOR_BYTE_ARRAY = new byte[0];
    public static final double DEFAULT_VALUE_FOR_DOUBLE = 0.0d;
    public static final long DEFAULT_VALUE_FOR_LONG = 0;
    public static final String DEFAULT_VALUE_FOR_STRING = "";
    public static final int LAST_FETCH_STATUS_FAILURE = 1;
    public static final int LAST_FETCH_STATUS_NO_FETCH_YET = 0;
    public static final int LAST_FETCH_STATUS_SUCCESS = -1;
    public static final int LAST_FETCH_STATUS_THROTTLED = 2;
    public static final String TAG = "FirebaseRemoteConfig";
    public static final int VALUE_SOURCE_DEFAULT = 1;
    public static final int VALUE_SOURCE_REMOTE = 2;
    public static final int VALUE_SOURCE_STATIC = 0;
    public final ConfigCacheClient activatedConfigsCache;
    public final Context context;
    public final ConfigCacheClient defaultConfigsCache;
    public final Executor executor;
    public final ConfigFetchHandler fetchHandler;
    public final ConfigCacheClient fetchedConfigsCache;

    @Nullable
    public final FirebaseABTesting firebaseAbt;
    public final FirebaseApp firebaseApp;
    public final FirebaseInstallationsApi firebaseInstallations;
    public final ConfigMetadataClient frcMetadata;
    public final ConfigGetParameterHandler getHandler;

    public FirebaseRemoteConfig(Context context, FirebaseApp firebaseApp, FirebaseInstallationsApi firebaseInstallationsApi, @Nullable FirebaseABTesting firebaseABTesting, Executor executor, ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2, ConfigCacheClient configCacheClient3, ConfigFetchHandler configFetchHandler, ConfigGetParameterHandler configGetParameterHandler, ConfigMetadataClient configMetadataClient) {
        this.context = context;
        this.firebaseApp = firebaseApp;
        this.firebaseInstallations = firebaseInstallationsApi;
        this.firebaseAbt = firebaseABTesting;
        this.executor = executor;
        this.fetchedConfigsCache = configCacheClient;
        this.activatedConfigsCache = configCacheClient2;
        this.defaultConfigsCache = configCacheClient3;
        this.fetchHandler = configFetchHandler;
        this.getHandler = configGetParameterHandler;
        this.frcMetadata = configMetadataClient;
    }

    public static /* synthetic */ FirebaseRemoteConfigInfo a(Task task, Task task2) {
        return (FirebaseRemoteConfigInfo) task.getResult();
    }

    @NonNull
    public static FirebaseRemoteConfig getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static boolean isFetchedFresh(ConfigContainer configContainer, @Nullable ConfigContainer configContainer2) {
        return configContainer2 == null || !configContainer.getFetchTime().equals(configContainer2.getFetchTime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean processActivatePutTask(Task<ConfigContainer> task) {
        if (!task.isSuccessful()) {
            return false;
        }
        this.fetchedConfigsCache.clear();
        if (task.getResult() != null) {
            updateAbtWithActivatedExperiments(task.getResult().getAbtExperiments());
            return true;
        }
        Log.e(TAG, "Activated configs written to disk are null.");
        return true;
    }

    private Task<Void> setDefaultsWithStringsMapAsync(Map<String, String> map) {
        try {
            return this.defaultConfigsCache.put(ConfigContainer.newBuilder().replaceConfigsWith(map).build()).onSuccessTask(new SuccessContinuation() { // from class: b.e.b.h.i
                @Override // com.google.android.gms.tasks.SuccessContinuation
                public final Task then(Object obj) {
                    return Tasks.forResult(null);
                }
            });
        } catch (b e2) {
            Log.e(TAG, "The provided defaults map could not be processed.", e2);
            return Tasks.forResult(null);
        }
    }

    @VisibleForTesting
    public static List<Map<String, String>> toExperimentInfoMaps(a aVar) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < aVar.a(); i++) {
            HashMap map = new HashMap();
            d dVarA = aVar.a(i);
            Iterator<String> itA = dVarA.a();
            while (itA.hasNext()) {
                String next = itA.next();
                map.put(next, dVarA.h(next));
            }
            arrayList.add(map);
        }
        return arrayList;
    }

    @NonNull
    public Task<Boolean> activate() {
        final Task<ConfigContainer> task = this.fetchedConfigsCache.get();
        final Task<ConfigContainer> task2 = this.activatedConfigsCache.get();
        return Tasks.whenAllComplete((Task<?>[]) new Task[]{task, task2}).continueWithTask(this.executor, new Continuation() { // from class: b.e.b.h.c
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task3) {
                return this.f620a.a(task, task2, task3);
            }
        });
    }

    @NonNull
    public Task<FirebaseRemoteConfigInfo> ensureInitialized() {
        Task<ConfigContainer> task = this.activatedConfigsCache.get();
        Task<ConfigContainer> task2 = this.defaultConfigsCache.get();
        Task<ConfigContainer> task3 = this.fetchedConfigsCache.get();
        final Task taskCall = Tasks.call(this.executor, new Callable() { // from class: b.e.b.h.j
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f630a.getInfo();
            }
        });
        return Tasks.whenAllComplete((Task<?>[]) new Task[]{task, task2, task3, taskCall, this.firebaseInstallations.getId(), this.firebaseInstallations.getToken(false)}).continueWith(this.executor, new Continuation() { // from class: b.e.b.h.b
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task4) {
                return FirebaseRemoteConfig.a(taskCall, task4);
            }
        });
    }

    @NonNull
    public Task<Void> fetch() {
        return this.fetchHandler.fetch().onSuccessTask(new SuccessContinuation() { // from class: b.e.b.h.e
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return Tasks.forResult(null);
            }
        });
    }

    @NonNull
    public Task<Boolean> fetchAndActivate() {
        return fetch().onSuccessTask(this.executor, new SuccessContinuation() { // from class: b.e.b.h.h
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return this.f628a.a((Void) obj);
            }
        });
    }

    @NonNull
    public Map<String, FirebaseRemoteConfigValue> getAll() {
        return this.getHandler.getAll();
    }

    public boolean getBoolean(@NonNull String str) {
        return this.getHandler.getBoolean(str);
    }

    public double getDouble(@NonNull String str) {
        return this.getHandler.getDouble(str);
    }

    @NonNull
    public FirebaseRemoteConfigInfo getInfo() {
        return this.frcMetadata.getInfo();
    }

    @NonNull
    public Set<String> getKeysByPrefix(@NonNull String str) {
        return this.getHandler.getKeysByPrefix(str);
    }

    public long getLong(@NonNull String str) {
        return this.getHandler.getLong(str);
    }

    @NonNull
    public String getString(@NonNull String str) {
        return this.getHandler.getString(str);
    }

    @NonNull
    public FirebaseRemoteConfigValue getValue(@NonNull String str) {
        return this.getHandler.getValue(str);
    }

    @NonNull
    public Task<Void> reset() {
        return Tasks.call(this.executor, new Callable() { // from class: b.e.b.h.d
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f623a.a();
            }
        });
    }

    @NonNull
    public Task<Void> setConfigSettingsAsync(@NonNull final FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        return Tasks.call(this.executor, new Callable() { // from class: b.e.b.h.f
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f625a.a(firebaseRemoteConfigSettings);
            }
        });
    }

    @NonNull
    public Task<Void> setDefaultsAsync(@NonNull Map<String, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof byte[]) {
                map2.put(entry.getKey(), new String((byte[]) value));
            } else {
                map2.put(entry.getKey(), value.toString());
            }
        }
        return setDefaultsWithStringsMapAsync(map2);
    }

    public void startLoadingConfigsFromDisk() {
        this.activatedConfigsCache.get();
        this.defaultConfigsCache.get();
        this.fetchedConfigsCache.get();
    }

    @VisibleForTesting
    public void updateAbtWithActivatedExperiments(@NonNull a aVar) {
        if (this.firebaseAbt == null) {
            return;
        }
        try {
            this.firebaseAbt.replaceAllExperiments(toExperimentInfoMaps(aVar));
        } catch (AbtException e2) {
            Log.w(TAG, "Could not update ABT experiments.", e2);
        } catch (b e3) {
            Log.e(TAG, "Could not parse ABT experiments from the JSON response.", e3);
        }
    }

    @NonNull
    public static FirebaseRemoteConfig getInstance(@NonNull FirebaseApp firebaseApp) {
        return ((RemoteConfigComponent) firebaseApp.get(RemoteConfigComponent.class)).getDefault();
    }

    public /* synthetic */ Task a(Void r1) {
        return activate();
    }

    public /* synthetic */ Task a(Task task, Task task2, Task task3) {
        if (task.isSuccessful() && task.getResult() != null) {
            ConfigContainer configContainer = (ConfigContainer) task.getResult();
            if (task2.isSuccessful() && !isFetchedFresh(configContainer, (ConfigContainer) task2.getResult())) {
                return Tasks.forResult(false);
            }
            return this.activatedConfigsCache.put(configContainer).continueWith(this.executor, new Continuation() { // from class: b.e.b.h.a
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task4) {
                    return Boolean.valueOf(this.f599a.processActivatePutTask(task4));
                }
            });
        }
        return Tasks.forResult(false);
    }

    @NonNull
    public Task<Void> fetch(long j) {
        return this.fetchHandler.fetch(j).onSuccessTask(new SuccessContinuation() { // from class: b.e.b.h.g
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return Tasks.forResult(null);
            }
        });
    }

    @NonNull
    public Task<Void> setDefaultsAsync(@XmlRes int i) {
        return setDefaultsWithStringsMapAsync(DefaultsXmlParser.getDefaultsFromXml(this.context, i));
    }

    public /* synthetic */ Void a(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.frcMetadata.setConfigSettings(firebaseRemoteConfigSettings);
        return null;
    }

    public /* synthetic */ Void a() {
        this.activatedConfigsCache.clear();
        this.fetchedConfigsCache.clear();
        this.defaultConfigsCache.clear();
        this.frcMetadata.clear();
        return null;
    }
}
