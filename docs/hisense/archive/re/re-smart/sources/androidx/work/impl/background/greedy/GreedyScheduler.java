package androidx.work.impl.background.greedy;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.ProcessUtils;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class GreedyScheduler implements Scheduler, WorkConstraintsCallback, ExecutionListener {
    public static final String TAG = Logger.tagWithPrefix("GreedyScheduler");
    public final Context mContext;
    public DelayedWorkTracker mDelayedWorkTracker;
    public Boolean mInDefaultProcess;
    public boolean mRegisteredExecutionListener;
    public final WorkConstraintsTracker mWorkConstraintsTracker;
    public final WorkManagerImpl mWorkManagerImpl;
    public final Set<WorkSpec> mConstrainedWorkSpecs = new HashSet();
    public final Object mLock = new Object();

    public GreedyScheduler(@NonNull Context context, @NonNull Configuration configuration, @NonNull TaskExecutor taskExecutor, @NonNull WorkManagerImpl workManagerImpl) {
        this.mContext = context;
        this.mWorkManagerImpl = workManagerImpl;
        this.mWorkConstraintsTracker = new WorkConstraintsTracker(context, taskExecutor, this);
        this.mDelayedWorkTracker = new DelayedWorkTracker(this, configuration.getRunnableScheduler());
    }

    private void checkDefaultProcess() {
        this.mInDefaultProcess = Boolean.valueOf(ProcessUtils.isDefaultProcess(this.mContext, this.mWorkManagerImpl.getConfiguration()));
    }

    private void registerExecutionListenerIfNeeded() {
        if (this.mRegisteredExecutionListener) {
            return;
        }
        this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
        this.mRegisteredExecutionListener = true;
    }

    private void removeConstraintTrackingFor(@NonNull String workSpecId) {
        synchronized (this.mLock) {
            for (WorkSpec workSpec : this.mConstrainedWorkSpecs) {
                if (workSpec.id.equals(workSpecId)) {
                    Logger.get().debug(TAG, String.format("Stopping tracking for %s", workSpecId), new Throwable[0]);
                    this.mConstrainedWorkSpecs.remove(workSpec);
                    this.mWorkConstraintsTracker.replace(this.mConstrainedWorkSpecs);
                    break;
                }
            }
        }
    }

    @Override // androidx.work.impl.Scheduler
    public void cancel(@NonNull String workSpecId) {
        if (this.mInDefaultProcess == null) {
            checkDefaultProcess();
        }
        if (!this.mInDefaultProcess.booleanValue()) {
            Logger.get().info(TAG, "Ignoring schedule request in non-main process", new Throwable[0]);
            return;
        }
        registerExecutionListenerIfNeeded();
        Logger.get().debug(TAG, String.format("Cancelling work ID %s", workSpecId), new Throwable[0]);
        DelayedWorkTracker delayedWorkTracker = this.mDelayedWorkTracker;
        if (delayedWorkTracker != null) {
            delayedWorkTracker.unschedule(workSpecId);
        }
        this.mWorkManagerImpl.stopWork(workSpecId);
    }

    @Override // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return false;
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(@NonNull List<String> workSpecIds) {
        for (String str : workSpecIds) {
            Logger.get().debug(TAG, String.format("Constraints met: Scheduling work ID %s", str), new Throwable[0]);
            this.mWorkManagerImpl.startWork(str);
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(@NonNull List<String> workSpecIds) {
        for (String str : workSpecIds) {
            Logger.get().debug(TAG, String.format("Constraints not met: Cancelling work ID %s", str), new Throwable[0]);
            this.mWorkManagerImpl.stopWork(str);
        }
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(@NonNull String workSpecId, boolean needsReschedule) {
        removeConstraintTrackingFor(workSpecId);
    }

    @Override // androidx.work.impl.Scheduler
    public void schedule(@NonNull WorkSpec... workSpecs) {
        if (this.mInDefaultProcess == null) {
            checkDefaultProcess();
        }
        if (!this.mInDefaultProcess.booleanValue()) {
            Logger.get().info(TAG, "Ignoring schedule request in a secondary process", new Throwable[0]);
            return;
        }
        registerExecutionListenerIfNeeded();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (WorkSpec workSpec : workSpecs) {
            long jCalculateNextRunTime = workSpec.calculateNextRunTime();
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (workSpec.state == WorkInfo.State.ENQUEUED) {
                if (jCurrentTimeMillis < jCalculateNextRunTime) {
                    DelayedWorkTracker delayedWorkTracker = this.mDelayedWorkTracker;
                    if (delayedWorkTracker != null) {
                        delayedWorkTracker.schedule(workSpec);
                    }
                } else if (workSpec.hasConstraints()) {
                    int i = Build.VERSION.SDK_INT;
                    if (workSpec.constraints.requiresDeviceIdle()) {
                        Logger.get().debug(TAG, String.format("Ignoring WorkSpec %s, Requires device idle.", workSpec), new Throwable[0]);
                    } else {
                        int i2 = Build.VERSION.SDK_INT;
                        if (workSpec.constraints.hasContentUriTriggers()) {
                            Logger.get().debug(TAG, String.format("Ignoring WorkSpec %s, Requires ContentUri triggers.", workSpec), new Throwable[0]);
                        } else {
                            hashSet.add(workSpec);
                            hashSet2.add(workSpec.id);
                        }
                    }
                } else {
                    Logger.get().debug(TAG, String.format("Starting work for %s", workSpec.id), new Throwable[0]);
                    this.mWorkManagerImpl.startWork(workSpec.id);
                }
            }
        }
        synchronized (this.mLock) {
            if (!hashSet.isEmpty()) {
                Logger.get().debug(TAG, String.format("Starting tracking for [%s]", TextUtils.join(",", hashSet2)), new Throwable[0]);
                this.mConstrainedWorkSpecs.addAll(hashSet);
                this.mWorkConstraintsTracker.replace(this.mConstrainedWorkSpecs);
            }
        }
    }

    @VisibleForTesting
    public void setDelayedWorkTracker(@NonNull DelayedWorkTracker delayedWorkTracker) {
        this.mDelayedWorkTracker = delayedWorkTracker;
    }

    @VisibleForTesting
    public GreedyScheduler(@NonNull Context context, @NonNull WorkManagerImpl workManagerImpl, @NonNull WorkConstraintsTracker workConstraintsTracker) {
        this.mContext = context;
        this.mWorkManagerImpl = workManagerImpl;
        this.mWorkConstraintsTracker = workConstraintsTracker;
    }
}
