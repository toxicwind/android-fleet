package androidx.work.impl.utils;

import androidx.annotation.RestrictTo;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkManagerImpl;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class StartWorkRunnable implements Runnable {
    public WorkerParameters.RuntimeExtras mRuntimeExtras;
    public WorkManagerImpl mWorkManagerImpl;
    public String mWorkSpecId;

    public StartWorkRunnable(WorkManagerImpl workManagerImpl, String workSpecId, WorkerParameters.RuntimeExtras runtimeExtras) {
        this.mWorkManagerImpl = workManagerImpl;
        this.mWorkSpecId = workSpecId;
        this.mRuntimeExtras = runtimeExtras;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.mWorkManagerImpl.getProcessor().startWork(this.mWorkSpecId, this.mRuntimeExtras);
    }
}
