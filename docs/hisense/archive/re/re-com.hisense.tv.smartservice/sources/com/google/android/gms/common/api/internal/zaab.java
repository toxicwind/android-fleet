package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Add missing generic type declarations: [TResult] */
/* JADX INFO: loaded from: classes.dex */
public final class zaab<TResult> implements OnCompleteListener<TResult> {
    public final /* synthetic */ TaskCompletionSource zafp;
    public final /* synthetic */ zaz zafq;

    public zaab(zaz zazVar, TaskCompletionSource taskCompletionSource) {
        this.zafq = zazVar;
        this.zafp = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(@NonNull Task<TResult> task) {
        this.zafq.zafn.remove(this.zafp);
    }
}
