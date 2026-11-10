package androidx.work.impl.workers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CombineContinuationsWorker extends Worker {
    public CombineContinuationsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override // androidx.work.Worker
    @NonNull
    public ListenableWorker.Result doWork() {
        return new ListenableWorker.Result.Success(getInputData());
    }
}
