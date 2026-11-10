package androidx.work.impl.workers;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class DiagnosticsWorker extends Worker {
    public static final String TAG = Logger.tagWithPrefix("DiagnosticsWrkr");

    public DiagnosticsWorker(@NonNull Context context, @NonNull WorkerParameters parameters) {
        super(context, parameters);
    }

    @NonNull
    public static String workSpecRow(@NonNull WorkSpec workSpec, @Nullable String name, @Nullable Integer systemId, @NonNull String tags) {
        return String.format("\n%s\t %s\t %s\t %s\t %s\t %s\t", workSpec.id, workSpec.workerClassName, systemId, workSpec.state.name(), name, tags);
    }

    @NonNull
    public static String workSpecRows(@NonNull WorkNameDao workNameDao, @NonNull WorkTagDao workTagDao, @NonNull SystemIdInfoDao systemIdInfoDao, @NonNull List<WorkSpec> workSpecs) {
        StringBuilder sb = new StringBuilder();
        int i = Build.VERSION.SDK_INT;
        sb.append(String.format("\n Id \t Class Name\t %s\t State\t Unique Name\t Tags\t", "Job Id"));
        for (WorkSpec workSpec : workSpecs) {
            Integer numValueOf = null;
            SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(workSpec.id);
            if (systemIdInfo != null) {
                numValueOf = Integer.valueOf(systemIdInfo.systemId);
            }
            sb.append(workSpecRow(workSpec, TextUtils.join(",", workNameDao.getNamesForWorkSpecId(workSpec.id)), numValueOf, TextUtils.join(",", workTagDao.getTagsForWorkSpecId(workSpec.id))));
        }
        return sb.toString();
    }

    @Override // androidx.work.Worker
    @NonNull
    public ListenableWorker.Result doWork() {
        WorkDatabase workDatabase = WorkManagerImpl.getInstance(getApplicationContext()).getWorkDatabase();
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        WorkNameDao workNameDao = workDatabase.workNameDao();
        WorkTagDao workTagDao = workDatabase.workTagDao();
        SystemIdInfoDao systemIdInfoDao = workDatabase.systemIdInfoDao();
        List<WorkSpec> recentlyCompletedWork = workSpecDao.getRecentlyCompletedWork(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1L));
        List<WorkSpec> runningWork = workSpecDao.getRunningWork();
        List<WorkSpec> allEligibleWorkSpecsForScheduling = workSpecDao.getAllEligibleWorkSpecsForScheduling(200);
        if (recentlyCompletedWork != null && !recentlyCompletedWork.isEmpty()) {
            Logger.get().info(TAG, "Recently completed work:\n\n", new Throwable[0]);
            Logger.get().info(TAG, workSpecRows(workNameDao, workTagDao, systemIdInfoDao, recentlyCompletedWork), new Throwable[0]);
        }
        if (runningWork != null && !runningWork.isEmpty()) {
            Logger.get().info(TAG, "Running work:\n\n", new Throwable[0]);
            Logger.get().info(TAG, workSpecRows(workNameDao, workTagDao, systemIdInfoDao, runningWork), new Throwable[0]);
        }
        if (allEligibleWorkSpecsForScheduling != null && !allEligibleWorkSpecsForScheduling.isEmpty()) {
            Logger.get().info(TAG, "Enqueued work:\n\n", new Throwable[0]);
            Logger.get().info(TAG, workSpecRows(workNameDao, workTagDao, systemIdInfoDao, allEligibleWorkSpecsForScheduling), new Throwable[0]);
        }
        return new ListenableWorker.Result.Success();
    }
}
