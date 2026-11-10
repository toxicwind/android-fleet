package androidx.work.impl.utils.taskexecutor;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.impl.utils.SerialExecutor;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkManagerTaskExecutor implements TaskExecutor {
    public final SerialExecutor mBackgroundExecutor;
    public final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    public final Executor mMainThreadExecutor = new Executor() { // from class: androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor.1
        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable command) {
            WorkManagerTaskExecutor.this.postToMainThread(command);
        }
    };

    public WorkManagerTaskExecutor(@NonNull Executor backgroundExecutor) {
        this.mBackgroundExecutor = new SerialExecutor(backgroundExecutor);
    }

    @Override // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public void executeOnBackgroundThread(Runnable runnable) {
        this.mBackgroundExecutor.execute(runnable);
    }

    @Override // androidx.work.impl.utils.taskexecutor.TaskExecutor
    @NonNull
    public SerialExecutor getBackgroundExecutor() {
        return this.mBackgroundExecutor;
    }

    @Override // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public Executor getMainThreadExecutor() {
        return this.mMainThreadExecutor;
    }

    @Override // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public void postToMainThread(Runnable runnable) {
        this.mMainThreadHandler.post(runnable);
    }
}
