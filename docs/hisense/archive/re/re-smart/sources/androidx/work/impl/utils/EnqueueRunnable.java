package androidx.work.impl.utils;

import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.WorkRequest;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.model.Dependency;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTag;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class EnqueueRunnable implements Runnable {
    public static final String TAG = Logger.tagWithPrefix("EnqueueRunnable");
    public final OperationImpl mOperation = new OperationImpl();
    public final WorkContinuationImpl mWorkContinuation;

    public EnqueueRunnable(@NonNull WorkContinuationImpl workContinuation) {
        this.mWorkContinuation = workContinuation;
    }

    public static boolean enqueueContinuation(@NonNull WorkContinuationImpl workContinuation) {
        boolean zEnqueueWorkWithPrerequisites = enqueueWorkWithPrerequisites(workContinuation.getWorkManagerImpl(), workContinuation.getWork(), (String[]) WorkContinuationImpl.prerequisitesFor(workContinuation).toArray(new String[0]), workContinuation.getName(), workContinuation.getExistingWorkPolicy());
        workContinuation.markEnqueued();
        return zEnqueueWorkWithPrerequisites;
    }

    /* JADX WARN: Code duplicated, block: B:84:0x013e A[PHI: r0 r8 r11 r12 r13
      0x013e: PHI (r0v1 java.lang.String[]) = (r0v0 java.lang.String[]), (r0v0 java.lang.String[]), (r0v13 java.lang.String[]), (r0v13 java.lang.String[]) binds: [B:32:0x006a, B:34:0x0078, B:83:0x013d, B:82:0x013b] A[DONT_GENERATE, DONT_INLINE]
      0x013e: PHI (r8v2 boolean) = (r8v1 boolean), (r8v1 boolean), (r8v5 boolean), (r8v6 boolean) binds: [B:32:0x006a, B:34:0x0078, B:83:0x013d, B:82:0x013b] A[DONT_GENERATE, DONT_INLINE]
      0x013e: PHI (r11v2 boolean) = (r11v1 boolean), (r11v1 boolean), (r11v4 boolean), (r11v4 boolean) binds: [B:32:0x006a, B:34:0x0078, B:83:0x013d, B:82:0x013b] A[DONT_GENERATE, DONT_INLINE]
      0x013e: PHI (r12v2 boolean) = (r12v1 boolean), (r12v1 boolean), (r12v5 boolean), (r12v5 boolean) binds: [B:32:0x006a, B:34:0x0078, B:83:0x013d, B:82:0x013b] A[DONT_GENERATE, DONT_INLINE]
      0x013e: PHI (r13v2 boolean) = (r13v1 boolean), (r13v1 boolean), (r13v5 boolean), (r13v5 boolean) binds: [B:32:0x006a, B:34:0x0078, B:83:0x013d, B:82:0x013b] A[DONT_GENERATE, DONT_INLINE]] */
    public static boolean enqueueWorkWithPrerequisites(WorkManagerImpl workManagerImpl, @NonNull List<? extends WorkRequest> workList, String[] prerequisiteIds, String name, ExistingWorkPolicy existingWorkPolicy) {
        boolean z;
        boolean z2;
        boolean z3;
        String[] strArr = prerequisiteIds;
        long jCurrentTimeMillis = System.currentTimeMillis();
        WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
        boolean z4 = true;
        boolean z5 = strArr != null && strArr.length > 0;
        if (z5) {
            z = true;
            z2 = false;
            z3 = false;
            for (String str : strArr) {
                WorkSpec workSpec = workDatabase.workSpecDao().getWorkSpec(str);
                if (workSpec == null) {
                    Logger.get().error(TAG, String.format("Prerequisite %s doesn't exist; not enqueuing", str), new Throwable[0]);
                    return false;
                }
                WorkInfo.State state = workSpec.state;
                z &= state == WorkInfo.State.SUCCEEDED;
                if (state == WorkInfo.State.FAILED) {
                    z3 = true;
                } else if (state == WorkInfo.State.CANCELLED) {
                    z2 = true;
                }
            }
        } else {
            z = true;
            z2 = false;
            z3 = false;
        }
        boolean z6 = !TextUtils.isEmpty(name);
        if (z6 && !z5) {
            List<WorkSpec.IdAndState> workSpecIdAndStatesForName = workDatabase.workSpecDao().getWorkSpecIdAndStatesForName(name);
            if (workSpecIdAndStatesForName.isEmpty()) {
                z4 = false;
            } else if (existingWorkPolicy == ExistingWorkPolicy.APPEND || existingWorkPolicy == ExistingWorkPolicy.APPEND_OR_REPLACE) {
                DependencyDao dependencyDao = workDatabase.dependencyDao();
                List arrayList = new ArrayList();
                for (WorkSpec.IdAndState idAndState : workSpecIdAndStatesForName) {
                    if (!dependencyDao.hasDependents(idAndState.id)) {
                        boolean z7 = (idAndState.state == WorkInfo.State.SUCCEEDED) & z;
                        WorkInfo.State state2 = idAndState.state;
                        if (state2 == WorkInfo.State.FAILED) {
                            z3 = true;
                        } else if (state2 == WorkInfo.State.CANCELLED) {
                            z2 = true;
                        }
                        arrayList.add(idAndState.id);
                        z = z7;
                    }
                }
                if (existingWorkPolicy == ExistingWorkPolicy.APPEND_OR_REPLACE && (z2 || z3)) {
                    WorkSpecDao workSpecDao = workDatabase.workSpecDao();
                    Iterator<WorkSpec.IdAndState> it = workSpecDao.getWorkSpecIdAndStatesForName(name).iterator();
                    while (it.hasNext()) {
                        workSpecDao.delete(it.next().id);
                    }
                    arrayList = Collections.emptyList();
                    z2 = false;
                    z3 = false;
                }
                strArr = (String[]) arrayList.toArray(strArr);
                z5 = strArr.length > 0;
                z4 = false;
            } else {
                if (existingWorkPolicy == ExistingWorkPolicy.KEEP) {
                    Iterator<WorkSpec.IdAndState> it2 = workSpecIdAndStatesForName.iterator();
                    while (it2.hasNext()) {
                        WorkInfo.State state3 = it2.next().state;
                        if (state3 == WorkInfo.State.ENQUEUED || state3 == WorkInfo.State.RUNNING) {
                            return false;
                        }
                    }
                }
                CancelWorkRunnable.forName(name, workManagerImpl, false).run();
                WorkSpecDao workSpecDao2 = workDatabase.workSpecDao();
                Iterator<WorkSpec.IdAndState> it3 = workSpecIdAndStatesForName.iterator();
                while (it3.hasNext()) {
                    workSpecDao2.delete(it3.next().id);
                }
            }
        } else {
            z4 = false;
        }
        Iterator<? extends WorkRequest> it4 = workList.iterator();
        while (it4.hasNext()) {
            WorkRequest next = it4.next();
            WorkSpec workSpec2 = next.getWorkSpec();
            if (!z5 || z) {
                if (workSpec2.isPeriodic()) {
                    workSpec2.periodStartTime = 0L;
                } else {
                    workSpec2.periodStartTime = jCurrentTimeMillis;
                }
            } else if (z3) {
                workSpec2.state = WorkInfo.State.FAILED;
            } else if (z2) {
                workSpec2.state = WorkInfo.State.CANCELLED;
            } else {
                workSpec2.state = WorkInfo.State.BLOCKED;
            }
            int i = Build.VERSION.SDK_INT;
            if (workSpec2.state == WorkInfo.State.ENQUEUED) {
                z4 = true;
            }
            workDatabase.workSpecDao().insertWorkSpec(workSpec2);
            if (z5) {
                int length = strArr.length;
                int i2 = 0;
                while (i2 < length) {
                    workDatabase.dependencyDao().insertDependency(new Dependency(next.getStringId(), strArr[i2]));
                    i2++;
                    strArr = strArr;
                    it4 = it4;
                }
            }
            String[] strArr2 = strArr;
            Iterator<? extends WorkRequest> it5 = it4;
            Iterator<String> it6 = next.getTags().iterator();
            while (it6.hasNext()) {
                workDatabase.workTagDao().insert(new WorkTag(it6.next(), next.getStringId()));
            }
            if (z6) {
                workDatabase.workNameDao().insert(new WorkName(name, next.getStringId()));
            }
            strArr = strArr2;
            it4 = it5;
        }
        return z4;
    }

    public static boolean processContinuation(@NonNull WorkContinuationImpl workContinuation) {
        List<WorkContinuationImpl> parents = workContinuation.getParents();
        boolean z = false;
        if (parents != null) {
            boolean zProcessContinuation = false;
            for (WorkContinuationImpl workContinuationImpl : parents) {
                if (workContinuationImpl.isEnqueued()) {
                    Logger.get().warning(TAG, String.format("Already enqueued work ids (%s).", TextUtils.join(", ", workContinuationImpl.getIds())), new Throwable[0]);
                } else {
                    zProcessContinuation |= processContinuation(workContinuationImpl);
                }
            }
            z = zProcessContinuation;
        }
        return enqueueContinuation(workContinuation) | z;
    }

    public static void tryDelegateConstrainedWorkSpec(WorkSpec workSpec) {
        Constraints constraints = workSpec.constraints;
        String str = workSpec.workerClassName;
        if (str.equals(ConstraintTrackingWorker.class.getName())) {
            return;
        }
        if (constraints.requiresBatteryNotLow() || constraints.requiresStorageNotLow()) {
            Data.Builder builder = new Data.Builder();
            builder.putAll(workSpec.input).putString(ConstraintTrackingWorker.ARGUMENT_CLASS_NAME, str);
            workSpec.workerClassName = ConstraintTrackingWorker.class.getName();
            workSpec.input = builder.build();
        }
    }

    public static boolean usesScheduler(@NonNull WorkManagerImpl workManager, @NonNull String className) {
        try {
            Class<?> cls = Class.forName(className);
            Iterator<Scheduler> it = workManager.getSchedulers().iterator();
            while (it.hasNext()) {
                if (cls.isAssignableFrom(it.next().getClass())) {
                    return true;
                }
            }
        } catch (ClassNotFoundException unused) {
        }
        return false;
    }

    @VisibleForTesting
    public boolean addToDatabase() {
        WorkDatabase workDatabase = this.mWorkContinuation.getWorkManagerImpl().getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            boolean zProcessContinuation = processContinuation(this.mWorkContinuation);
            workDatabase.setTransactionSuccessful();
            return zProcessContinuation;
        } finally {
            workDatabase.endTransaction();
        }
    }

    @NonNull
    public Operation getOperation() {
        return this.mOperation;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.mWorkContinuation.hasCycles()) {
                throw new IllegalStateException(String.format("WorkContinuation has cycles (%s)", this.mWorkContinuation));
            }
            if (addToDatabase()) {
                PackageManagerHelper.setComponentEnabled(this.mWorkContinuation.getWorkManagerImpl().getApplicationContext(), RescheduleReceiver.class, true);
                scheduleWorkInBackground();
            }
            this.mOperation.setState(Operation.SUCCESS);
        } catch (Throwable th) {
            this.mOperation.setState(new Operation.State.FAILURE(th));
        }
    }

    @VisibleForTesting
    public void scheduleWorkInBackground() {
        WorkManagerImpl workManagerImpl = this.mWorkContinuation.getWorkManagerImpl();
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }
}
