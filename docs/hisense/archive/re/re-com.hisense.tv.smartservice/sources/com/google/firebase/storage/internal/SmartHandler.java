package com.google.firebase.storage.internal;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.StorageTaskScheduler;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class SmartHandler {
    public static boolean testMode = false;
    public final Executor executor;
    public final Handler handler;

    public SmartHandler(@Nullable Executor executor) {
        this.executor = executor;
        if (this.executor != null) {
            this.handler = null;
        } else if (testMode) {
            this.handler = null;
        } else {
            this.handler = new Handler(Looper.getMainLooper());
        }
    }

    public void callBack(@NonNull Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        Handler handler = this.handler;
        if (handler != null) {
            handler.post(runnable);
            return;
        }
        Executor executor = this.executor;
        if (executor != null) {
            executor.execute(runnable);
        } else {
            StorageTaskScheduler.sInstance.scheduleCallback(runnable);
        }
    }
}
