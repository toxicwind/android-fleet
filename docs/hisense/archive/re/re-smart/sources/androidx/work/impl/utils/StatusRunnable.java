package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.work.WorkInfo;
import androidx.work.WorkQuery;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class StatusRunnable<T> implements Runnable {
    public final SettableFuture<T> mFuture = new SettableFuture<>();

    @NonNull
    public static StatusRunnable<List<WorkInfo>> forStringIds(@NonNull final WorkManagerImpl workManager, @NonNull final List<String> ids) {
        return new StatusRunnable<List<WorkInfo>>() { // from class: androidx.work.impl.utils.StatusRunnable.1
            @Override // androidx.work.impl.utils.StatusRunnable
            public List<WorkInfo> runInternal() {
                return WorkSpec.WORK_INFO_MAPPER.apply(workManager.getWorkDatabase().workSpecDao().getWorkStatusPojoForIds(ids));
            }
        };
    }

    @NonNull
    public static StatusRunnable<List<WorkInfo>> forTag(@NonNull final WorkManagerImpl workManager, @NonNull final String tag) {
        return new StatusRunnable<List<WorkInfo>>() { // from class: androidx.work.impl.utils.StatusRunnable.3
            @Override // androidx.work.impl.utils.StatusRunnable
            public List<WorkInfo> runInternal() {
                return WorkSpec.WORK_INFO_MAPPER.apply(workManager.getWorkDatabase().workSpecDao().getWorkStatusPojoForTag(tag));
            }
        };
    }

    @NonNull
    public static StatusRunnable<WorkInfo> forUUID(@NonNull final WorkManagerImpl workManager, @NonNull final UUID id) {
        return new StatusRunnable<WorkInfo>() { // from class: androidx.work.impl.utils.StatusRunnable.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.work.impl.utils.StatusRunnable
            public WorkInfo runInternal() {
                WorkSpec.WorkInfoPojo workStatusPojoForId = workManager.getWorkDatabase().workSpecDao().getWorkStatusPojoForId(id.toString());
                if (workStatusPojoForId != null) {
                    return workStatusPojoForId.toWorkInfo();
                }
                return null;
            }
        };
    }

    @NonNull
    public static StatusRunnable<List<WorkInfo>> forUniqueWork(@NonNull final WorkManagerImpl workManager, @NonNull final String name) {
        return new StatusRunnable<List<WorkInfo>>() { // from class: androidx.work.impl.utils.StatusRunnable.4
            @Override // androidx.work.impl.utils.StatusRunnable
            public List<WorkInfo> runInternal() {
                return WorkSpec.WORK_INFO_MAPPER.apply(workManager.getWorkDatabase().workSpecDao().getWorkStatusPojoForName(name));
            }
        };
    }

    @NonNull
    public static StatusRunnable<List<WorkInfo>> forWorkQuerySpec(@NonNull final WorkManagerImpl workManager, @NonNull final WorkQuery querySpec) {
        return new StatusRunnable<List<WorkInfo>>() { // from class: androidx.work.impl.utils.StatusRunnable.5
            @Override // androidx.work.impl.utils.StatusRunnable
            public List<WorkInfo> runInternal() {
                return WorkSpec.WORK_INFO_MAPPER.apply(workManager.getWorkDatabase().rawWorkInfoDao().getWorkInfoPojos(RawQueries.workQueryToRawQuery(querySpec)));
            }
        };
    }

    @NonNull
    public ListenableFuture<T> getFuture() {
        return this.mFuture;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.mFuture.set(runInternal());
        } catch (Throwable th) {
            this.mFuture.setException(th);
        }
    }

    @WorkerThread
    public abstract T runInternal();
}
