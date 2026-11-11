package androidx.work;

import android.app.PendingIntent;
import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.work.impl.WorkManagerImpl;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: WorkManager.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u0000 <2\u00020\u0001:\u0002<=B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\b\u001a\u00020\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH&J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u0010J\u0016\u0010\u000e\u001a\u00020\u000f2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00100\rH&J\u001e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u0010J&\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00100\rH&J \u0010\u0016\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u0010H\u0016J&\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00100\rH&J \u0010\u0017\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u001aH&J\u0010\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dH&J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u0013H&J\u0010\u0010 \u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H&J\b\u0010!\u001a\u00020\tH&J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u001c\u001a\u00020\u001dH&J\b\u0010$\u001a\u00020\tH&J\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H&J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020'0)H&J\u0018\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0&2\u0006\u0010\u001c\u001a\u00020\u001dH&J\u0018\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0-2\u0006\u0010\u001c\u001a\u00020\u001dH&J\u0018\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0)2\u0006\u0010\u001c\u001a\u00020\u001dH&J\u001c\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\r0&2\u0006\u0010\u001f\u001a\u00020\u0013H&J\u001c\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\r0-2\u0006\u0010\u001f\u001a\u00020\u0013H&J\u001c\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\r0)2\u0006\u0010\u001f\u001a\u00020\u0013H&J\u001c\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\r0&2\u0006\u0010\u0012\u001a\u00020\u0013H&J\u001c\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\r0-2\u0006\u0010\u0012\u001a\u00020\u0013H&J\u001c\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\r0)2\u0006\u0010\u0012\u001a\u00020\u0013H&J\u001c\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\r0&2\u0006\u00106\u001a\u000207H&J\u001c\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\r0-2\u0006\u00106\u001a\u000207H&J\u001c\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\r0)2\u0006\u00106\u001a\u000207H&J\u0016\u0010:\u001a\b\u0012\u0004\u0012\u00020;0)2\u0006\u0010\n\u001a\u00020\u000bH&R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006>"}, d2 = {"Landroidx/work/WorkManager;", "", "<init>", "()V", "configuration", "Landroidx/work/Configuration;", "getConfiguration", "()Landroidx/work/Configuration;", "enqueue", "Landroidx/work/Operation;", "request", "Landroidx/work/WorkRequest;", "requests", "", "beginWith", "Landroidx/work/WorkContinuation;", "Landroidx/work/OneTimeWorkRequest;", "beginUniqueWork", "uniqueWorkName", "", "existingWorkPolicy", "Landroidx/work/ExistingWorkPolicy;", "enqueueUniqueWork", "enqueueUniquePeriodicWork", "existingPeriodicWorkPolicy", "Landroidx/work/ExistingPeriodicWorkPolicy;", "Landroidx/work/PeriodicWorkRequest;", "cancelWorkById", "id", "Ljava/util/UUID;", "cancelAllWorkByTag", "tag", "cancelUniqueWork", "cancelAllWork", "createCancelPendingIntent", "Landroid/app/PendingIntent;", "pruneWork", "getLastCancelAllTimeMillisLiveData", "Landroidx/lifecycle/LiveData;", "", "getLastCancelAllTimeMillis", "Lcom/google/common/util/concurrent/ListenableFuture;", "getWorkInfoByIdLiveData", "Landroidx/work/WorkInfo;", "getWorkInfoByIdFlow", "Lkotlinx/coroutines/flow/Flow;", "getWorkInfoById", "getWorkInfosByTagLiveData", "getWorkInfosByTagFlow", "getWorkInfosByTag", "getWorkInfosForUniqueWorkLiveData", "getWorkInfosForUniqueWorkFlow", "getWorkInfosForUniqueWork", "getWorkInfosLiveData", "workQuery", "Landroidx/work/WorkQuery;", "getWorkInfosFlow", "getWorkInfos", "updateWork", "Landroidx/work/WorkManager$UpdateResult;", "Companion", "UpdateResult", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class WorkManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: WorkManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/WorkManager$UpdateResult;", "", "<init>", "(Ljava/lang/String;I)V", "NOT_APPLIED", "APPLIED_IMMEDIATELY", "APPLIED_FOR_NEXT_RUN", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum UpdateResult {
        NOT_APPLIED,
        APPLIED_IMMEDIATELY,
        APPLIED_FOR_NEXT_RUN;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<UpdateResult> getEntries() {
            return $ENTRIES;
        }
    }

    @Deprecated(message = "Use the overload receiving Context", replaceWith = @ReplaceWith(expression = "WorkManager.getContext(context)", imports = {}))
    @JvmStatic
    public static WorkManager getInstance() {
        return INSTANCE.getInstance();
    }

    @JvmStatic
    public static WorkManager getInstance(Context context) {
        return INSTANCE.getInstance(context);
    }

    @JvmStatic
    public static void initialize(Context context, Configuration configuration) {
        INSTANCE.initialize(context, configuration);
    }

    @JvmStatic
    public static boolean isInitialized() {
        return INSTANCE.isInitialized();
    }

    public abstract WorkContinuation beginUniqueWork(String uniqueWorkName, ExistingWorkPolicy existingWorkPolicy, List<OneTimeWorkRequest> requests);

    public abstract WorkContinuation beginWith(List<OneTimeWorkRequest> requests);

    public abstract Operation cancelAllWork();

    public abstract Operation cancelAllWorkByTag(String tag);

    public abstract Operation cancelUniqueWork(String uniqueWorkName);

    public abstract Operation cancelWorkById(UUID id);

    public abstract PendingIntent createCancelPendingIntent(UUID id);

    public abstract Operation enqueue(List<? extends WorkRequest> requests);

    public abstract Operation enqueueUniquePeriodicWork(String uniqueWorkName, ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy, PeriodicWorkRequest request);

    public abstract Operation enqueueUniqueWork(String uniqueWorkName, ExistingWorkPolicy existingWorkPolicy, List<OneTimeWorkRequest> requests);

    public abstract Configuration getConfiguration();

    public abstract ListenableFuture<Long> getLastCancelAllTimeMillis();

    public abstract LiveData<Long> getLastCancelAllTimeMillisLiveData();

    public abstract ListenableFuture<WorkInfo> getWorkInfoById(UUID id);

    public abstract Flow<WorkInfo> getWorkInfoByIdFlow(UUID id);

    public abstract LiveData<WorkInfo> getWorkInfoByIdLiveData(UUID id);

    public abstract ListenableFuture<List<WorkInfo>> getWorkInfos(WorkQuery workQuery);

    public abstract ListenableFuture<List<WorkInfo>> getWorkInfosByTag(String tag);

    public abstract Flow<List<WorkInfo>> getWorkInfosByTagFlow(String tag);

    public abstract LiveData<List<WorkInfo>> getWorkInfosByTagLiveData(String tag);

    public abstract Flow<List<WorkInfo>> getWorkInfosFlow(WorkQuery workQuery);

    public abstract ListenableFuture<List<WorkInfo>> getWorkInfosForUniqueWork(String uniqueWorkName);

    public abstract Flow<List<WorkInfo>> getWorkInfosForUniqueWorkFlow(String uniqueWorkName);

    public abstract LiveData<List<WorkInfo>> getWorkInfosForUniqueWorkLiveData(String uniqueWorkName);

    public abstract LiveData<List<WorkInfo>> getWorkInfosLiveData(WorkQuery workQuery);

    public abstract Operation pruneWork();

    public abstract ListenableFuture<UpdateResult> updateWork(WorkRequest request);

    /* JADX INFO: compiled from: WorkManager.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0017J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0017J\b\u0010\f\u001a\u00020\rH\u0017¨\u0006\u000e"}, d2 = {"Landroidx/work/WorkManager$Companion;", "", "<init>", "()V", "getInstance", "Landroidx/work/WorkManager;", "context", "Landroid/content/Context;", "initialize", "", "configuration", "Landroidx/work/Configuration;", "isInitialized", "", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Deprecated(message = "Use the overload receiving Context", replaceWith = @ReplaceWith(expression = "WorkManager.getContext(context)", imports = {}))
        @JvmStatic
        public WorkManager getInstance() {
            WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance();
            if (workManagerImpl != null) {
                return workManagerImpl;
            }
            throw new IllegalStateException("WorkManager is not initialized properly.  The most likely cause is that you disabled WorkManagerInitializer in your manifest but forgot to call WorkManager#initialize in your Application#onCreate or a ContentProvider.".toString());
        }

        @JvmStatic
        public WorkManager getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(context);
            Intrinsics.checkNotNullExpressionValue(workManagerImpl, "getInstance(...)");
            return workManagerImpl;
        }

        @JvmStatic
        public void initialize(Context context, Configuration configuration) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            WorkManagerImpl.initialize(context, configuration);
        }

        @JvmStatic
        public boolean isInitialized() {
            return WorkManagerImpl.isInitialized();
        }
    }

    public final Operation enqueue(WorkRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return enqueue(CollectionsKt.listOf(request));
    }

    public final WorkContinuation beginWith(OneTimeWorkRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return beginWith(CollectionsKt.listOf(request));
    }

    public final WorkContinuation beginUniqueWork(String uniqueWorkName, ExistingWorkPolicy existingWorkPolicy, OneTimeWorkRequest request) {
        Intrinsics.checkNotNullParameter(uniqueWorkName, "uniqueWorkName");
        Intrinsics.checkNotNullParameter(existingWorkPolicy, "existingWorkPolicy");
        Intrinsics.checkNotNullParameter(request, "request");
        return beginUniqueWork(uniqueWorkName, existingWorkPolicy, CollectionsKt.listOf(request));
    }

    public Operation enqueueUniqueWork(String uniqueWorkName, ExistingWorkPolicy existingWorkPolicy, OneTimeWorkRequest request) {
        Intrinsics.checkNotNullParameter(uniqueWorkName, "uniqueWorkName");
        Intrinsics.checkNotNullParameter(existingWorkPolicy, "existingWorkPolicy");
        Intrinsics.checkNotNullParameter(request, "request");
        return enqueueUniqueWork(uniqueWorkName, existingWorkPolicy, CollectionsKt.listOf(request));
    }
}
