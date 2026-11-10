package androidx.work.impl;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.arch.core.util.Function;
import androidx.core.os.BuildCompat;
import androidx.lifecycle.LiveData;
import androidx.work.Configuration;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.R;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkQuery;
import androidx.work.WorkRequest;
import androidx.work.WorkerParameters;
import androidx.work.impl.background.greedy.GreedyScheduler;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.CancelWorkRunnable;
import androidx.work.impl.utils.ForceStopRunnable;
import androidx.work.impl.utils.LiveDataUtils;
import androidx.work.impl.utils.PreferenceUtils;
import androidx.work.impl.utils.PruneWorkRunnable;
import androidx.work.impl.utils.RawQueries;
import androidx.work.impl.utils.StartWorkRunnable;
import androidx.work.impl.utils.StatusRunnable;
import androidx.work.impl.utils.StopWorkRunnable;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import androidx.work.multiprocess.RemoteWorkManager;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkManagerImpl extends WorkManager {
    public static final int MAX_PRE_JOB_SCHEDULER_API_LEVEL = 22;
    public static final int MIN_JOB_SCHEDULER_API_LEVEL = 23;
    public static final String REMOTE_WORK_MANAGER_CLIENT = "androidx.work.multiprocess.RemoteWorkManagerClient";
    public Configuration mConfiguration;
    public Context mContext;
    public boolean mForceStopRunnableCompleted;
    public PreferenceUtils mPreferenceUtils;
    public Processor mProcessor;
    public volatile RemoteWorkManager mRemoteWorkManager;
    public BroadcastReceiver.PendingResult mRescheduleReceiverResult;
    public List<Scheduler> mSchedulers;
    public WorkDatabase mWorkDatabase;
    public TaskExecutor mWorkTaskExecutor;
    public static final String TAG = Logger.tagWithPrefix("WorkManagerImpl");
    public static WorkManagerImpl sDelegatedInstance = null;
    public static WorkManagerImpl sDefaultInstance = null;
    public static final Object sLock = new Object();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkManagerImpl(@NonNull Context context, @NonNull Configuration configuration, @NonNull TaskExecutor workTaskExecutor) {
        this(context, configuration, workTaskExecutor, context.getResources().getBoolean(R.bool.workmanager_test_configuration));
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Deprecated
    public static WorkManagerImpl getInstance() {
        synchronized (sLock) {
            if (sDelegatedInstance != null) {
                return sDelegatedInstance;
            }
            return sDefaultInstance;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void initialize(@NonNull Context context, @NonNull Configuration configuration) {
        synchronized (sLock) {
            if (sDelegatedInstance != null && sDefaultInstance != null) {
                throw new IllegalStateException("WorkManager is already initialized.  Did you try to initialize it manually without disabling WorkManagerInitializer? See WorkManager#initialize(Context, Configuration) or the class level Javadoc for more information.");
            }
            if (sDelegatedInstance == null) {
                Context applicationContext = context.getApplicationContext();
                if (sDefaultInstance == null) {
                    sDefaultInstance = new WorkManagerImpl(applicationContext, configuration, new WorkManagerTaskExecutor(configuration.getTaskExecutor()), applicationContext.getResources().getBoolean(R.bool.workmanager_test_configuration));
                }
                sDelegatedInstance = sDefaultInstance;
            }
        }
    }

    private void internalInit(@NonNull Context context, @NonNull Configuration configuration, @NonNull TaskExecutor workTaskExecutor, @NonNull WorkDatabase workDatabase, @NonNull List<Scheduler> schedulers, @NonNull Processor processor) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mConfiguration = configuration;
        this.mWorkTaskExecutor = workTaskExecutor;
        this.mWorkDatabase = workDatabase;
        this.mSchedulers = schedulers;
        this.mProcessor = processor;
        this.mPreferenceUtils = new PreferenceUtils(workDatabase);
        this.mForceStopRunnableCompleted = false;
        int i = Build.VERSION.SDK_INT;
        if (applicationContext.isDeviceProtectedStorage()) {
            throw new IllegalStateException("Cannot initialize WorkManager in direct boot mode");
        }
        this.mWorkTaskExecutor.executeOnBackgroundThread(new ForceStopRunnable(applicationContext, this));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void setDelegate(@Nullable WorkManagerImpl delegate) {
        synchronized (sLock) {
            sDelegatedInstance = delegate;
        }
    }

    private void tryInitializeMultiProcessSupport() {
        try {
            this.mRemoteWorkManager = (RemoteWorkManager) Class.forName(REMOTE_WORK_MANAGER_CLIENT).getConstructor(Context.class, WorkManagerImpl.class).newInstance(this.mContext, this);
        } catch (Throwable th) {
            Logger.get().debug(TAG, "Unable to initialize multi-process support", th);
        }
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public WorkContinuation beginUniqueWork(@NonNull String uniqueWorkName, @NonNull ExistingWorkPolicy existingWorkPolicy, @NonNull List<OneTimeWorkRequest> work) {
        if (work.isEmpty()) {
            throw new IllegalArgumentException("beginUniqueWork needs at least one OneTimeWorkRequest.");
        }
        return new WorkContinuationImpl(this, uniqueWorkName, existingWorkPolicy, work);
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public WorkContinuation beginWith(@NonNull List<OneTimeWorkRequest> work) {
        if (work.isEmpty()) {
            throw new IllegalArgumentException("beginWith needs at least one OneTimeWorkRequest.");
        }
        return new WorkContinuationImpl(this, work);
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public Operation cancelAllWork() {
        CancelWorkRunnable cancelWorkRunnableForAll = CancelWorkRunnable.forAll(this);
        this.mWorkTaskExecutor.executeOnBackgroundThread(cancelWorkRunnableForAll);
        return cancelWorkRunnableForAll.getOperation();
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public Operation cancelAllWorkByTag(@NonNull final String tag) {
        CancelWorkRunnable cancelWorkRunnableForTag = CancelWorkRunnable.forTag(tag, this);
        this.mWorkTaskExecutor.executeOnBackgroundThread(cancelWorkRunnableForTag);
        return cancelWorkRunnableForTag.getOperation();
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public Operation cancelUniqueWork(@NonNull String uniqueWorkName) {
        CancelWorkRunnable cancelWorkRunnableForName = CancelWorkRunnable.forName(uniqueWorkName, this, true);
        this.mWorkTaskExecutor.executeOnBackgroundThread(cancelWorkRunnableForName);
        return cancelWorkRunnableForName.getOperation();
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public Operation cancelWorkById(@NonNull UUID id) {
        CancelWorkRunnable cancelWorkRunnableForId = CancelWorkRunnable.forId(id, this);
        this.mWorkTaskExecutor.executeOnBackgroundThread(cancelWorkRunnableForId);
        return cancelWorkRunnableForId.getOperation();
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public PendingIntent createCancelPendingIntent(@NonNull UUID id) {
        return PendingIntent.getService(this.mContext, 0, SystemForegroundDispatcher.createCancelWorkIntent(this.mContext, id.toString()), BuildCompat.isAtLeastS() ? 167772160 : 134217728);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public List<Scheduler> createSchedulers(@NonNull Context context, @NonNull Configuration configuration, @NonNull TaskExecutor taskExecutor) {
        return Arrays.asList(Schedulers.createBestAvailableBackgroundScheduler(context, this), new GreedyScheduler(context, configuration, taskExecutor, this));
    }

    @NonNull
    public WorkContinuationImpl createWorkContinuationForUniquePeriodicWork(@NonNull String uniqueWorkName, @NonNull ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy, @NonNull PeriodicWorkRequest periodicWork) {
        return new WorkContinuationImpl(this, uniqueWorkName, existingPeriodicWorkPolicy == ExistingPeriodicWorkPolicy.KEEP ? ExistingWorkPolicy.KEEP : ExistingWorkPolicy.REPLACE, Collections.singletonList(periodicWork));
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public Operation enqueue(@NonNull List<? extends WorkRequest> requests) {
        if (requests.isEmpty()) {
            throw new IllegalArgumentException("enqueue needs at least one WorkRequest.");
        }
        return new WorkContinuationImpl(this, requests).enqueue();
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public Operation enqueueUniquePeriodicWork(@NonNull String uniqueWorkName, @NonNull ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy, @NonNull PeriodicWorkRequest periodicWork) {
        return createWorkContinuationForUniquePeriodicWork(uniqueWorkName, existingPeriodicWorkPolicy, periodicWork).enqueue();
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public Operation enqueueUniqueWork(@NonNull String uniqueWorkName, @NonNull ExistingWorkPolicy existingWorkPolicy, @NonNull List<OneTimeWorkRequest> work) {
        return new WorkContinuationImpl(this, uniqueWorkName, existingWorkPolicy, work).enqueue();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Context getApplicationContext() {
        return this.mContext;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Configuration getConfiguration() {
        return this.mConfiguration;
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public ListenableFuture<Long> getLastCancelAllTimeMillis() {
        final SettableFuture settableFuture = new SettableFuture();
        final PreferenceUtils preferenceUtils = this.mPreferenceUtils;
        this.mWorkTaskExecutor.executeOnBackgroundThread(new Runnable() { // from class: androidx.work.impl.WorkManagerImpl.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    settableFuture.set(Long.valueOf(preferenceUtils.getLastCancelAllTimeMillis()));
                } catch (Throwable th) {
                    settableFuture.setException(th);
                }
            }
        });
        return settableFuture;
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public LiveData<Long> getLastCancelAllTimeMillisLiveData() {
        return this.mPreferenceUtils.getLastCancelAllTimeMillisLiveData();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PreferenceUtils getPreferenceUtils() {
        return this.mPreferenceUtils;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Processor getProcessor() {
        return this.mProcessor;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteWorkManager getRemoteWorkManager() {
        if (this.mRemoteWorkManager == null) {
            synchronized (sLock) {
                if (this.mRemoteWorkManager == null) {
                    tryInitializeMultiProcessSupport();
                    if (this.mRemoteWorkManager == null && !TextUtils.isEmpty(this.mConfiguration.getDefaultProcessName())) {
                        throw new IllegalStateException("Invalid multiprocess configuration. Define an `implementation` dependency on :work:work-multiprocess library");
                    }
                }
            }
        }
        return this.mRemoteWorkManager;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public List<Scheduler> getSchedulers() {
        return this.mSchedulers;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkDatabase getWorkDatabase() {
        return this.mWorkDatabase;
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public ListenableFuture<WorkInfo> getWorkInfoById(@NonNull UUID id) {
        StatusRunnable<WorkInfo> statusRunnableForUUID = StatusRunnable.forUUID(this, id);
        this.mWorkTaskExecutor.getBackgroundExecutor().execute(statusRunnableForUUID);
        return statusRunnableForUUID.getFuture();
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public LiveData<WorkInfo> getWorkInfoByIdLiveData(@NonNull UUID id) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.workSpecDao().getWorkStatusPojoLiveDataForIds(Collections.singletonList(id.toString())), new Function<List<WorkSpec.WorkInfoPojo>, WorkInfo>() { // from class: androidx.work.impl.WorkManagerImpl.2
            @Override // androidx.arch.core.util.Function
            public WorkInfo apply(List<WorkSpec.WorkInfoPojo> input) {
                if (input == null || input.size() <= 0) {
                    return null;
                }
                return input.get(0).toWorkInfo();
            }
        }, this.mWorkTaskExecutor);
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public ListenableFuture<List<WorkInfo>> getWorkInfos(@NonNull WorkQuery workQuery) {
        StatusRunnable<List<WorkInfo>> statusRunnableForWorkQuerySpec = StatusRunnable.forWorkQuerySpec(this, workQuery);
        this.mWorkTaskExecutor.getBackgroundExecutor().execute(statusRunnableForWorkQuerySpec);
        return statusRunnableForWorkQuerySpec.getFuture();
    }

    public LiveData<List<WorkInfo>> getWorkInfosById(@NonNull List<String> workSpecIds) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.workSpecDao().getWorkStatusPojoLiveDataForIds(workSpecIds), WorkSpec.WORK_INFO_MAPPER, this.mWorkTaskExecutor);
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public ListenableFuture<List<WorkInfo>> getWorkInfosByTag(@NonNull String tag) {
        StatusRunnable<List<WorkInfo>> statusRunnableForTag = StatusRunnable.forTag(this, tag);
        this.mWorkTaskExecutor.getBackgroundExecutor().execute(statusRunnableForTag);
        return statusRunnableForTag.getFuture();
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public LiveData<List<WorkInfo>> getWorkInfosByTagLiveData(@NonNull String tag) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.workSpecDao().getWorkStatusPojoLiveDataForTag(tag), WorkSpec.WORK_INFO_MAPPER, this.mWorkTaskExecutor);
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public ListenableFuture<List<WorkInfo>> getWorkInfosForUniqueWork(@NonNull String uniqueWorkName) {
        StatusRunnable<List<WorkInfo>> statusRunnableForUniqueWork = StatusRunnable.forUniqueWork(this, uniqueWorkName);
        this.mWorkTaskExecutor.getBackgroundExecutor().execute(statusRunnableForUniqueWork);
        return statusRunnableForUniqueWork.getFuture();
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public LiveData<List<WorkInfo>> getWorkInfosForUniqueWorkLiveData(@NonNull String uniqueWorkName) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.workSpecDao().getWorkStatusPojoLiveDataForName(uniqueWorkName), WorkSpec.WORK_INFO_MAPPER, this.mWorkTaskExecutor);
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public LiveData<List<WorkInfo>> getWorkInfosLiveData(@NonNull WorkQuery workQuery) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.rawWorkInfoDao().getWorkInfoPojosLiveData(RawQueries.workQueryToRawQuery(workQuery)), WorkSpec.WORK_INFO_MAPPER, this.mWorkTaskExecutor);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public TaskExecutor getWorkTaskExecutor() {
        return this.mWorkTaskExecutor;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onForceStopRunnableCompleted() {
        synchronized (sLock) {
            this.mForceStopRunnableCompleted = true;
            if (this.mRescheduleReceiverResult != null) {
                this.mRescheduleReceiverResult.finish();
                this.mRescheduleReceiverResult = null;
            }
        }
    }

    @Override // androidx.work.WorkManager
    @NonNull
    public Operation pruneWork() {
        PruneWorkRunnable pruneWorkRunnable = new PruneWorkRunnable(this);
        this.mWorkTaskExecutor.executeOnBackgroundThread(pruneWorkRunnable);
        return pruneWorkRunnable.getOperation();
    }

    public void rescheduleEligibleWork() {
        int i = Build.VERSION.SDK_INT;
        SystemJobScheduler.cancelAll(getApplicationContext());
        getWorkDatabase().workSpecDao().resetScheduledState();
        Schedulers.schedule(getConfiguration(), getWorkDatabase(), getSchedulers());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setReschedulePendingResult(@NonNull BroadcastReceiver.PendingResult rescheduleReceiverResult) {
        synchronized (sLock) {
            this.mRescheduleReceiverResult = rescheduleReceiverResult;
            if (this.mForceStopRunnableCompleted) {
                this.mRescheduleReceiverResult.finish();
                this.mRescheduleReceiverResult = null;
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void startWork(@NonNull String workSpecId) {
        startWork(workSpecId, null);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void stopForegroundWork(@NonNull String workSpecId) {
        this.mWorkTaskExecutor.executeOnBackgroundThread(new StopWorkRunnable(this, workSpecId, true));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void stopWork(@NonNull String workSpecId) {
        this.mWorkTaskExecutor.executeOnBackgroundThread(new StopWorkRunnable(this, workSpecId, false));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void startWork(@NonNull String workSpecId, @Nullable WorkerParameters.RuntimeExtras runtimeExtras) {
        this.mWorkTaskExecutor.executeOnBackgroundThread(new StartWorkRunnable(this, workSpecId, runtimeExtras));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkManagerImpl(@NonNull Context context, @NonNull Configuration configuration, @NonNull TaskExecutor workTaskExecutor, boolean useTestDatabase) {
        this(context, configuration, workTaskExecutor, WorkDatabase.create(context.getApplicationContext(), workTaskExecutor.getBackgroundExecutor(), useTestDatabase));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static WorkManagerImpl getInstance(@NonNull Context context) {
        WorkManagerImpl workManagerImpl;
        synchronized (sLock) {
            workManagerImpl = getInstance();
            if (workManagerImpl == null) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext instanceof Configuration.Provider) {
                    initialize(applicationContext, ((Configuration.Provider) applicationContext).getWorkManagerConfiguration());
                    workManagerImpl = getInstance(applicationContext);
                } else {
                    throw new IllegalStateException("WorkManager is not initialized properly.  You have explicitly disabled WorkManagerInitializer in your manifest, have not manually called WorkManager#initialize at this point, and your Application does not implement Configuration.Provider.");
                }
            }
        }
        return workManagerImpl;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkManagerImpl(@NonNull Context context, @NonNull Configuration configuration, @NonNull TaskExecutor workTaskExecutor, @NonNull WorkDatabase database) {
        Context applicationContext = context.getApplicationContext();
        Logger.setLogger(new Logger.LogcatLogger(configuration.getMinimumLoggingLevel()));
        List<Scheduler> listCreateSchedulers = createSchedulers(applicationContext, configuration, workTaskExecutor);
        internalInit(context, configuration, workTaskExecutor, database, listCreateSchedulers, new Processor(context, configuration, workTaskExecutor, database, listCreateSchedulers));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkManagerImpl(@NonNull Context context, @NonNull Configuration configuration, @NonNull TaskExecutor workTaskExecutor, @NonNull WorkDatabase workDatabase, @NonNull List<Scheduler> schedulers, @NonNull Processor processor) {
        internalInit(context, configuration, workTaskExecutor, workDatabase, schedulers, processor);
    }
}
