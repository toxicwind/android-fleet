package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.IdGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(23)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SystemJobScheduler implements Scheduler {
    public static final String TAG = Logger.tagWithPrefix("SystemJobScheduler");
    public final Context mContext;
    public final JobScheduler mJobScheduler;
    public final SystemJobInfoConverter mSystemJobInfoConverter;
    public final WorkManagerImpl mWorkManager;

    public SystemJobScheduler(@NonNull Context context, @NonNull WorkManagerImpl workManager) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        SystemJobInfoConverter systemJobInfoConverter = new SystemJobInfoConverter(context);
        this.mContext = context;
        this.mWorkManager = workManager;
        this.mJobScheduler = jobScheduler;
        this.mSystemJobInfoConverter = systemJobInfoConverter;
    }

    public static void cancelAll(@NonNull Context context) {
        List<JobInfo> pendingJobs;
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler == null || (pendingJobs = getPendingJobs(context, jobScheduler)) == null || pendingJobs.isEmpty()) {
            return;
        }
        Iterator<JobInfo> it = pendingJobs.iterator();
        while (it.hasNext()) {
            cancelJobById(jobScheduler, it.next().getId());
        }
    }

    public static void cancelJobById(@NonNull JobScheduler jobScheduler, int id) {
        try {
            jobScheduler.cancel(id);
        } catch (Throwable th) {
            Logger.get().error(TAG, String.format(Locale.getDefault(), "Exception while trying to cancel job (%d)", Integer.valueOf(id)), th);
        }
    }

    @Nullable
    public static List<Integer> getPendingJobIds(@NonNull Context context, @NonNull JobScheduler jobScheduler, @NonNull String workSpecId) {
        List<JobInfo> pendingJobs = getPendingJobs(context, jobScheduler);
        if (pendingJobs == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(2);
        for (JobInfo jobInfo : pendingJobs) {
            if (workSpecId.equals(getWorkSpecIdFromJobInfo(jobInfo))) {
                arrayList.add(Integer.valueOf(jobInfo.getId()));
            }
        }
        return arrayList;
    }

    @Nullable
    public static List<JobInfo> getPendingJobs(@NonNull Context context, @NonNull JobScheduler jobScheduler) {
        List<JobInfo> allPendingJobs;
        try {
            allPendingJobs = jobScheduler.getAllPendingJobs();
        } catch (Throwable th) {
            Logger.get().error(TAG, "getAllPendingJobs() is not reliable on this device.", th);
            allPendingJobs = null;
        }
        if (allPendingJobs == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(allPendingJobs.size());
        ComponentName componentName = new ComponentName(context, (Class<?>) SystemJobService.class);
        for (JobInfo jobInfo : allPendingJobs) {
            if (componentName.equals(jobInfo.getService())) {
                arrayList.add(jobInfo);
            }
        }
        return arrayList;
    }

    @Nullable
    public static String getWorkSpecIdFromJobInfo(@NonNull JobInfo jobInfo) {
        PersistableBundle extras = jobInfo.getExtras();
        if (extras == null) {
            return null;
        }
        try {
            if (extras.containsKey(SystemJobInfoConverter.EXTRA_WORK_SPEC_ID)) {
                return extras.getString(SystemJobInfoConverter.EXTRA_WORK_SPEC_ID);
            }
            return null;
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public static boolean reconcileJobs(@NonNull Context context, @NonNull WorkManagerImpl workManager) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        List<JobInfo> pendingJobs = getPendingJobs(context, jobScheduler);
        List<String> workSpecIds = workManager.getWorkDatabase().systemIdInfoDao().getWorkSpecIds();
        boolean z = false;
        HashSet hashSet = new HashSet(pendingJobs != null ? pendingJobs.size() : 0);
        if (pendingJobs != null && !pendingJobs.isEmpty()) {
            for (JobInfo jobInfo : pendingJobs) {
                String workSpecIdFromJobInfo = getWorkSpecIdFromJobInfo(jobInfo);
                if (TextUtils.isEmpty(workSpecIdFromJobInfo)) {
                    cancelJobById(jobScheduler, jobInfo.getId());
                } else {
                    hashSet.add(workSpecIdFromJobInfo);
                }
            }
        }
        Iterator<String> it = workSpecIds.iterator();
        while (it.hasNext()) {
            if (!hashSet.contains(it.next())) {
                Logger.get().debug(TAG, "Reconciling jobs", new Throwable[0]);
                z = true;
                break;
            }
        }
        if (z) {
            WorkDatabase workDatabase = workManager.getWorkDatabase();
            workDatabase.beginTransaction();
            try {
                WorkSpecDao workSpecDao = workDatabase.workSpecDao();
                Iterator<String> it2 = workSpecIds.iterator();
                while (it2.hasNext()) {
                    workSpecDao.markWorkSpecScheduled(it2.next(), -1L);
                }
                workDatabase.setTransactionSuccessful();
            } finally {
                workDatabase.endTransaction();
            }
        }
        return z;
    }

    @Override // androidx.work.impl.Scheduler
    public void cancel(@NonNull String workSpecId) {
        List<Integer> pendingJobIds = getPendingJobIds(this.mContext, this.mJobScheduler, workSpecId);
        if (pendingJobIds == null || pendingJobIds.isEmpty()) {
            return;
        }
        Iterator<Integer> it = pendingJobIds.iterator();
        while (it.hasNext()) {
            cancelJobById(this.mJobScheduler, it.next().intValue());
        }
        this.mWorkManager.getWorkDatabase().systemIdInfoDao().removeSystemIdInfo(workSpecId);
    }

    @Override // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return true;
    }

    @Override // androidx.work.impl.Scheduler
    public void schedule(@NonNull WorkSpec... workSpecs) {
        WorkDatabase workDatabase = this.mWorkManager.getWorkDatabase();
        IdGenerator idGenerator = new IdGenerator(workDatabase);
        for (WorkSpec workSpec : workSpecs) {
            workDatabase.beginTransaction();
            try {
                WorkSpec workSpec2 = workDatabase.workSpecDao().getWorkSpec(workSpec.id);
                if (workSpec2 == null) {
                    Logger.get().warning(TAG, "Skipping scheduling " + workSpec.id + " because it's no longer in the DB", new Throwable[0]);
                    workDatabase.setTransactionSuccessful();
                } else if (workSpec2.state != WorkInfo.State.ENQUEUED) {
                    Logger.get().warning(TAG, "Skipping scheduling " + workSpec.id + " because it is no longer enqueued", new Throwable[0]);
                    workDatabase.setTransactionSuccessful();
                } else {
                    SystemIdInfo systemIdInfo = workDatabase.systemIdInfoDao().getSystemIdInfo(workSpec.id);
                    int iNextJobSchedulerIdWithRange = systemIdInfo != null ? systemIdInfo.systemId : idGenerator.nextJobSchedulerIdWithRange(this.mWorkManager.getConfiguration().getMinJobSchedulerId(), this.mWorkManager.getConfiguration().getMaxJobSchedulerId());
                    if (systemIdInfo == null) {
                        this.mWorkManager.getWorkDatabase().systemIdInfoDao().insertSystemIdInfo(new SystemIdInfo(workSpec.id, iNextJobSchedulerIdWithRange));
                    }
                    scheduleInternal(workSpec, iNextJobSchedulerIdWithRange);
                    int i = Build.VERSION.SDK_INT;
                    workDatabase.setTransactionSuccessful();
                }
                workDatabase.endTransaction();
            } catch (Throwable th) {
                workDatabase.endTransaction();
                throw th;
            }
        }
    }

    @VisibleForTesting
    public void scheduleInternal(WorkSpec workSpec, int jobId) {
        JobInfo jobInfoConvert = this.mSystemJobInfoConverter.convert(workSpec, jobId);
        Logger.get().debug(TAG, String.format("Scheduling work ID %s Job ID %s", workSpec.id, Integer.valueOf(jobId)), new Throwable[0]);
        try {
            if (this.mJobScheduler.schedule(jobInfoConvert) == 0) {
                Logger.get().warning(TAG, String.format("Unable to schedule work ID %s", workSpec.id), new Throwable[0]);
                if (workSpec.expedited && workSpec.outOfQuotaPolicy == OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST) {
                    workSpec.expedited = false;
                    Logger.get().debug(TAG, String.format("Scheduling a non-expedited job (work ID %s)", workSpec.id), new Throwable[0]);
                    scheduleInternal(workSpec, jobId);
                }
            }
        } catch (IllegalStateException e2) {
            List<JobInfo> pendingJobs = getPendingJobs(this.mContext, this.mJobScheduler);
            String str = String.format(Locale.getDefault(), "JobScheduler 100 job limit exceeded.  We count %d WorkManager jobs in JobScheduler; we have %d tracked jobs in our DB; our Configuration limit is %d.", Integer.valueOf(pendingJobs != null ? pendingJobs.size() : 0), Integer.valueOf(this.mWorkManager.getWorkDatabase().workSpecDao().getScheduledWork().size()), Integer.valueOf(this.mWorkManager.getConfiguration().getMaxSchedulerLimit()));
            Logger.get().error(TAG, str, new Throwable[0]);
            throw new IllegalStateException(str, e2);
        } catch (Throwable th) {
            Logger.get().error(TAG, String.format("Unable to schedule %s", workSpec), th);
        }
    }

    @VisibleForTesting
    public SystemJobScheduler(Context context, WorkManagerImpl workManager, JobScheduler jobScheduler, SystemJobInfoConverter systemJobInfoConverter) {
        this.mContext = context;
        this.mWorkManager = workManager;
        this.mJobScheduler = jobScheduler;
        this.mSystemJobInfoConverter = systemJobInfoConverter;
    }
}
