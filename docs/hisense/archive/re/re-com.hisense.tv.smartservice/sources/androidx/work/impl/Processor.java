package androidx.work.impl;

import android.content.Context;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;
import androidx.work.Configuration;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class Processor implements ExecutionListener, ForegroundProcessor {
    public static final String FOREGROUND_WAKELOCK_TAG = "ProcessorForegroundLck";
    public static final String TAG = Logger.tagWithPrefix("Processor");
    public Context mAppContext;
    public Configuration mConfiguration;
    public List<Scheduler> mSchedulers;
    public WorkDatabase mWorkDatabase;
    public TaskExecutor mWorkTaskExecutor;
    public Map<String, WorkerWrapper> mEnqueuedWorkMap = new HashMap();
    public Map<String, WorkerWrapper> mForegroundWorkMap = new HashMap();
    public Set<String> mCancelledIds = new HashSet();
    public final List<ExecutionListener> mOuterListeners = new ArrayList();

    @Nullable
    public PowerManager.WakeLock mForegroundLock = null;
    public final Object mLock = new Object();

    private static class FutureListener implements Runnable {

        @NonNull
        public ExecutionListener mExecutionListener;

        @NonNull
        public ListenableFuture<Boolean> mFuture;

        @NonNull
        public String mWorkSpecId;

        public FutureListener(@NonNull ExecutionListener executionListener, @NonNull String workSpecId, @NonNull ListenableFuture<Boolean> future) {
            this.mExecutionListener = executionListener;
            this.mWorkSpecId = workSpecId;
            this.mFuture = future;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean zBooleanValue;
            try {
                zBooleanValue = this.mFuture.get().booleanValue();
            } catch (InterruptedException | ExecutionException unused) {
                zBooleanValue = true;
            }
            this.mExecutionListener.onExecuted(this.mWorkSpecId, zBooleanValue);
        }
    }

    public Processor(@NonNull Context appContext, @NonNull Configuration configuration, @NonNull TaskExecutor workTaskExecutor, @NonNull WorkDatabase workDatabase, @NonNull List<Scheduler> schedulers) {
        this.mAppContext = appContext;
        this.mConfiguration = configuration;
        this.mWorkTaskExecutor = workTaskExecutor;
        this.mWorkDatabase = workDatabase;
        this.mSchedulers = schedulers;
    }

    public static boolean interrupt(@NonNull String id, @Nullable WorkerWrapper wrapper) {
        if (wrapper == null) {
            Logger.get().debug(TAG, String.format("WorkerWrapper could not be found for %s", id), new Throwable[0]);
            return false;
        }
        wrapper.interrupt();
        Logger.get().debug(TAG, String.format("WorkerWrapper interrupted for %s", id), new Throwable[0]);
        return true;
    }

    private void stopForegroundService() {
        synchronized (this.mLock) {
            if (!(!this.mForegroundWorkMap.isEmpty())) {
                try {
                    this.mAppContext.startService(SystemForegroundDispatcher.createStopForegroundIntent(this.mAppContext));
                } catch (Throwable th) {
                    Logger.get().error(TAG, "Unable to stop foreground service", th);
                }
                if (this.mForegroundLock != null) {
                    this.mForegroundLock.release();
                    this.mForegroundLock = null;
                }
            }
        }
    }

    public void addExecutionListener(@NonNull ExecutionListener executionListener) {
        synchronized (this.mLock) {
            this.mOuterListeners.add(executionListener);
        }
    }

    public boolean hasWork() {
        boolean z;
        synchronized (this.mLock) {
            z = (this.mEnqueuedWorkMap.isEmpty() && this.mForegroundWorkMap.isEmpty()) ? false : true;
        }
        return z;
    }

    public boolean isCancelled(@NonNull String id) {
        boolean zContains;
        synchronized (this.mLock) {
            zContains = this.mCancelledIds.contains(id);
        }
        return zContains;
    }

    public boolean isEnqueued(@NonNull String workSpecId) {
        boolean z;
        synchronized (this.mLock) {
            z = this.mEnqueuedWorkMap.containsKey(workSpecId) || this.mForegroundWorkMap.containsKey(workSpecId);
        }
        return z;
    }

    public boolean isEnqueuedInForeground(@NonNull String workSpecId) {
        boolean zContainsKey;
        synchronized (this.mLock) {
            zContainsKey = this.mForegroundWorkMap.containsKey(workSpecId);
        }
        return zContainsKey;
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(@NonNull final String workSpecId, boolean needsReschedule) {
        synchronized (this.mLock) {
            this.mEnqueuedWorkMap.remove(workSpecId);
            Logger.get().debug(TAG, String.format("%s %s executed; reschedule = %s", getClass().getSimpleName(), workSpecId, Boolean.valueOf(needsReschedule)), new Throwable[0]);
            Iterator<ExecutionListener> it = this.mOuterListeners.iterator();
            while (it.hasNext()) {
                it.next().onExecuted(workSpecId, needsReschedule);
            }
        }
    }

    public void removeExecutionListener(@NonNull ExecutionListener executionListener) {
        synchronized (this.mLock) {
            this.mOuterListeners.remove(executionListener);
        }
    }

    @Override // androidx.work.impl.foreground.ForegroundProcessor
    public void startForeground(@NonNull String workSpecId, @NonNull ForegroundInfo foregroundInfo) {
        synchronized (this.mLock) {
            Logger.get().info(TAG, String.format("Moving WorkSpec (%s) to the foreground", workSpecId), new Throwable[0]);
            WorkerWrapper workerWrapperRemove = this.mEnqueuedWorkMap.remove(workSpecId);
            if (workerWrapperRemove != null) {
                if (this.mForegroundLock == null) {
                    this.mForegroundLock = WakeLocks.newWakeLock(this.mAppContext, FOREGROUND_WAKELOCK_TAG);
                    this.mForegroundLock.acquire();
                }
                this.mForegroundWorkMap.put(workSpecId, workerWrapperRemove);
                ContextCompat.startForegroundService(this.mAppContext, SystemForegroundDispatcher.createStartForegroundIntent(this.mAppContext, workSpecId, foregroundInfo));
            }
        }
    }

    public boolean startWork(@NonNull String id) {
        return startWork(id, null);
    }

    public boolean stopAndCancelWork(@NonNull String id) {
        boolean zInterrupt;
        synchronized (this.mLock) {
            boolean z = true;
            Logger.get().debug(TAG, String.format("Processor cancelling %s", id), new Throwable[0]);
            this.mCancelledIds.add(id);
            WorkerWrapper workerWrapperRemove = this.mForegroundWorkMap.remove(id);
            if (workerWrapperRemove == null) {
                z = false;
            }
            if (workerWrapperRemove == null) {
                workerWrapperRemove = this.mEnqueuedWorkMap.remove(id);
            }
            zInterrupt = interrupt(id, workerWrapperRemove);
            if (z) {
                stopForegroundService();
            }
        }
        return zInterrupt;
    }

    @Override // androidx.work.impl.foreground.ForegroundProcessor
    public void stopForeground(@NonNull String workSpecId) {
        synchronized (this.mLock) {
            this.mForegroundWorkMap.remove(workSpecId);
            stopForegroundService();
        }
    }

    public boolean stopForegroundWork(@NonNull String id) {
        boolean zInterrupt;
        synchronized (this.mLock) {
            Logger.get().debug(TAG, String.format("Processor stopping foreground work %s", id), new Throwable[0]);
            zInterrupt = interrupt(id, this.mForegroundWorkMap.remove(id));
        }
        return zInterrupt;
    }

    public boolean stopWork(@NonNull String id) {
        boolean zInterrupt;
        synchronized (this.mLock) {
            Logger.get().debug(TAG, String.format("Processor stopping background work %s", id), new Throwable[0]);
            zInterrupt = interrupt(id, this.mEnqueuedWorkMap.remove(id));
        }
        return zInterrupt;
    }

    public boolean startWork(@NonNull String id, @Nullable WorkerParameters.RuntimeExtras runtimeExtras) {
        synchronized (this.mLock) {
            if (isEnqueued(id)) {
                Logger.get().debug(TAG, String.format("Work %s is already enqueued for processing", id), new Throwable[0]);
                return false;
            }
            WorkerWrapper workerWrapperBuild = new WorkerWrapper.Builder(this.mAppContext, this.mConfiguration, this.mWorkTaskExecutor, this, this.mWorkDatabase, id).withSchedulers(this.mSchedulers).withRuntimeExtras(runtimeExtras).build();
            ListenableFuture<Boolean> future = workerWrapperBuild.getFuture();
            future.addListener(new FutureListener(this, id, future), this.mWorkTaskExecutor.getMainThreadExecutor());
            this.mEnqueuedWorkMap.put(id, workerWrapperBuild);
            this.mWorkTaskExecutor.getBackgroundExecutor().execute(workerWrapperBuild);
            Logger.get().debug(TAG, String.format("%s: processing %s", Processor.class.getSimpleName(), id), new Throwable[0]);
            return true;
        }
    }
}
