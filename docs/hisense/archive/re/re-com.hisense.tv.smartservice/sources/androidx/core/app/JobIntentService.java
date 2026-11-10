package androidx.core.app;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.work.impl.background.systemalarm.CommandHandler;
import b.a.a.a.a;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class JobIntentService extends Service {
    public static final boolean DEBUG = false;
    public static final String TAG = "JobIntentService";
    public final ArrayList<CompatWorkItem> mCompatQueue;
    public WorkEnqueuer mCompatWorkEnqueuer;
    public CommandProcessor mCurProcessor;
    public CompatJobEngine mJobImpl;
    public static final Object sLock = new Object();
    public static final HashMap<ComponentName, WorkEnqueuer> sClassWorkEnqueuer = new HashMap<>();
    public boolean mInterruptIfStopped = false;
    public boolean mStopped = false;
    public boolean mDestroyed = false;

    final class CommandProcessor extends AsyncTask<Void, Void, Void> {
        public CommandProcessor() {
        }

        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            while (true) {
                GenericWorkItem genericWorkItemDequeueWork = JobIntentService.this.dequeueWork();
                if (genericWorkItemDequeueWork == null) {
                    return null;
                }
                JobIntentService.this.onHandleWork(genericWorkItemDequeueWork.getIntent());
                genericWorkItemDequeueWork.complete();
            }
        }

        @Override // android.os.AsyncTask
        public void onCancelled(Void aVoid) {
            JobIntentService.this.processorFinished();
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Void aVoid) {
            JobIntentService.this.processorFinished();
        }
    }

    interface CompatJobEngine {
        IBinder compatGetBinder();

        GenericWorkItem dequeueWork();
    }

    static final class CompatWorkEnqueuer extends WorkEnqueuer {
        public final Context mContext;
        public final PowerManager.WakeLock mLaunchWakeLock;
        public boolean mLaunchingService;
        public final PowerManager.WakeLock mRunWakeLock;
        public boolean mServiceProcessing;

        public CompatWorkEnqueuer(Context context, ComponentName cn) {
            super(cn);
            this.mContext = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            this.mLaunchWakeLock = powerManager.newWakeLock(1, cn.getClassName() + ":launch");
            this.mLaunchWakeLock.setReferenceCounted(false);
            this.mRunWakeLock = powerManager.newWakeLock(1, cn.getClassName() + ":run");
            this.mRunWakeLock.setReferenceCounted(false);
        }

        @Override // androidx.core.app.JobIntentService.WorkEnqueuer
        public void enqueueWork(Intent work) {
            Intent intent = new Intent(work);
            intent.setComponent(this.mComponentName);
            if (this.mContext.startService(intent) != null) {
                synchronized (this) {
                    if (!this.mLaunchingService) {
                        this.mLaunchingService = true;
                        if (!this.mServiceProcessing) {
                            this.mLaunchWakeLock.acquire(60000L);
                        }
                    }
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.WorkEnqueuer
        public void serviceProcessingFinished() {
            synchronized (this) {
                if (this.mServiceProcessing) {
                    if (this.mLaunchingService) {
                        this.mLaunchWakeLock.acquire(60000L);
                    }
                    this.mServiceProcessing = false;
                    this.mRunWakeLock.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.WorkEnqueuer
        public void serviceProcessingStarted() {
            synchronized (this) {
                if (!this.mServiceProcessing) {
                    this.mServiceProcessing = true;
                    this.mRunWakeLock.acquire(CommandHandler.WORK_PROCESSING_TIME_IN_MS);
                    this.mLaunchWakeLock.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.WorkEnqueuer
        public void serviceStartReceived() {
            synchronized (this) {
                this.mLaunchingService = false;
            }
        }
    }

    final class CompatWorkItem implements GenericWorkItem {
        public final Intent mIntent;
        public final int mStartId;

        public CompatWorkItem(Intent intent, int startId) {
            this.mIntent = intent;
            this.mStartId = startId;
        }

        @Override // androidx.core.app.JobIntentService.GenericWorkItem
        public void complete() {
            JobIntentService.this.stopSelf(this.mStartId);
        }

        @Override // androidx.core.app.JobIntentService.GenericWorkItem
        public Intent getIntent() {
            return this.mIntent;
        }
    }

    interface GenericWorkItem {
        void complete();

        Intent getIntent();
    }

    @RequiresApi(26)
    static final class JobServiceEngineImpl extends JobServiceEngine implements CompatJobEngine {
        public static final boolean DEBUG = false;
        public static final String TAG = "JobServiceEngineImpl";
        public final Object mLock;
        public JobParameters mParams;
        public final JobIntentService mService;

        final class WrapperWorkItem implements GenericWorkItem {
            public final JobWorkItem mJobWork;

            public WrapperWorkItem(JobWorkItem jobWork) {
                this.mJobWork = jobWork;
            }

            @Override // androidx.core.app.JobIntentService.GenericWorkItem
            public void complete() {
                synchronized (JobServiceEngineImpl.this.mLock) {
                    if (JobServiceEngineImpl.this.mParams != null) {
                        JobServiceEngineImpl.this.mParams.completeWork(this.mJobWork);
                    }
                }
            }

            @Override // androidx.core.app.JobIntentService.GenericWorkItem
            public Intent getIntent() {
                return this.mJobWork.getIntent();
            }
        }

        public JobServiceEngineImpl(JobIntentService service) {
            super(service);
            this.mLock = new Object();
            this.mService = service;
        }

        @Override // androidx.core.app.JobIntentService.CompatJobEngine
        public IBinder compatGetBinder() {
            return getBinder();
        }

        @Override // androidx.core.app.JobIntentService.CompatJobEngine
        public GenericWorkItem dequeueWork() {
            synchronized (this.mLock) {
                if (this.mParams == null) {
                    return null;
                }
                JobWorkItem jobWorkItemDequeueWork = this.mParams.dequeueWork();
                if (jobWorkItemDequeueWork == null) {
                    return null;
                }
                jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(this.mService.getClassLoader());
                return new WrapperWorkItem(jobWorkItemDequeueWork);
            }
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters params) {
            this.mParams = params;
            this.mService.ensureProcessorRunningLocked(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters params) {
            boolean zDoStopCurrentWork = this.mService.doStopCurrentWork();
            synchronized (this.mLock) {
                this.mParams = null;
            }
            return zDoStopCurrentWork;
        }
    }

    @RequiresApi(26)
    static final class JobWorkEnqueuer extends WorkEnqueuer {
        public final JobInfo mJobInfo;
        public final JobScheduler mJobScheduler;

        public JobWorkEnqueuer(Context context, ComponentName cn, int jobId) {
            super(cn);
            ensureJobId(jobId);
            this.mJobInfo = new JobInfo.Builder(jobId, this.mComponentName).setOverrideDeadline(0L).build();
            this.mJobScheduler = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }

        @Override // androidx.core.app.JobIntentService.WorkEnqueuer
        public void enqueueWork(Intent work) {
            this.mJobScheduler.enqueue(this.mJobInfo, new JobWorkItem(work));
        }
    }

    static abstract class WorkEnqueuer {
        public final ComponentName mComponentName;
        public boolean mHasJobId;
        public int mJobId;

        public WorkEnqueuer(ComponentName cn) {
            this.mComponentName = cn;
        }

        public abstract void enqueueWork(Intent work);

        public void ensureJobId(int jobId) {
            if (!this.mHasJobId) {
                this.mHasJobId = true;
                this.mJobId = jobId;
            } else {
                if (this.mJobId == jobId) {
                    return;
                }
                StringBuilder sbA = a.a("Given job ID ", jobId, " is different than previous ");
                sbA.append(this.mJobId);
                throw new IllegalArgumentException(sbA.toString());
            }
        }

        public void serviceProcessingFinished() {
        }

        public void serviceProcessingStarted() {
        }

        public void serviceStartReceived() {
        }
    }

    public JobIntentService() {
        int i = Build.VERSION.SDK_INT;
        this.mCompatQueue = null;
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Class<?> cls, int jobId, @NonNull Intent work) {
        enqueueWork(context, new ComponentName(context, cls), jobId, work);
    }

    public static WorkEnqueuer getWorkEnqueuer(Context context, ComponentName cn, boolean hasJobId, int jobId) {
        WorkEnqueuer workEnqueuer = sClassWorkEnqueuer.get(cn);
        if (workEnqueuer != null) {
            return workEnqueuer;
        }
        int i = Build.VERSION.SDK_INT;
        if (!hasJobId) {
            throw new IllegalArgumentException("Can't be here without a job id");
        }
        JobWorkEnqueuer jobWorkEnqueuer = new JobWorkEnqueuer(context, cn, jobId);
        sClassWorkEnqueuer.put(cn, jobWorkEnqueuer);
        return jobWorkEnqueuer;
    }

    public GenericWorkItem dequeueWork() {
        CompatJobEngine compatJobEngine = this.mJobImpl;
        if (compatJobEngine != null) {
            return compatJobEngine.dequeueWork();
        }
        synchronized (this.mCompatQueue) {
            if (this.mCompatQueue.size() <= 0) {
                return null;
            }
            return this.mCompatQueue.remove(0);
        }
    }

    public boolean doStopCurrentWork() {
        CommandProcessor commandProcessor = this.mCurProcessor;
        if (commandProcessor != null) {
            commandProcessor.cancel(this.mInterruptIfStopped);
        }
        this.mStopped = true;
        return onStopCurrentWork();
    }

    public void ensureProcessorRunningLocked(boolean reportStarted) {
        if (this.mCurProcessor == null) {
            this.mCurProcessor = new CommandProcessor();
            WorkEnqueuer workEnqueuer = this.mCompatWorkEnqueuer;
            if (workEnqueuer != null && reportStarted) {
                workEnqueuer.serviceProcessingStarted();
            }
            this.mCurProcessor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public boolean isStopped() {
        return this.mStopped;
    }

    @Override // android.app.Service
    public IBinder onBind(@NonNull Intent intent) {
        CompatJobEngine compatJobEngine = this.mJobImpl;
        if (compatJobEngine != null) {
            return compatJobEngine.compatGetBinder();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        int i = Build.VERSION.SDK_INT;
        this.mJobImpl = new JobServiceEngineImpl(this);
        this.mCompatWorkEnqueuer = null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList<CompatWorkItem> arrayList = this.mCompatQueue;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.mDestroyed = true;
                this.mCompatWorkEnqueuer.serviceProcessingFinished();
            }
        }
    }

    public abstract void onHandleWork(@NonNull Intent intent);

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        if (this.mCompatQueue == null) {
            return 2;
        }
        this.mCompatWorkEnqueuer.serviceStartReceived();
        synchronized (this.mCompatQueue) {
            ArrayList<CompatWorkItem> arrayList = this.mCompatQueue;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new CompatWorkItem(intent, startId));
            ensureProcessorRunningLocked(true);
        }
        return 3;
    }

    public boolean onStopCurrentWork() {
        return true;
    }

    public void processorFinished() {
        ArrayList<CompatWorkItem> arrayList = this.mCompatQueue;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.mCurProcessor = null;
                if (this.mCompatQueue != null && this.mCompatQueue.size() > 0) {
                    ensureProcessorRunningLocked(false);
                } else if (!this.mDestroyed) {
                    this.mCompatWorkEnqueuer.serviceProcessingFinished();
                }
            }
        }
    }

    public void setInterruptIfStopped(boolean interruptIfStopped) {
        this.mInterruptIfStopped = interruptIfStopped;
    }

    public static void enqueueWork(@NonNull Context context, @NonNull ComponentName component, int jobId, @NonNull Intent work) {
        if (work == null) {
            throw new IllegalArgumentException("work must not be null");
        }
        synchronized (sLock) {
            WorkEnqueuer workEnqueuer = getWorkEnqueuer(context, component, true, jobId);
            workEnqueuer.ensureJobId(jobId);
            workEnqueuer.enqueueWork(work);
        }
    }
}
