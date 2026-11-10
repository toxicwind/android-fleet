package com.google.android.gms.common;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zab implements SuccessContinuation {
    public static final SuccessContinuation zaar = new zab();

    @Override // com.google.android.gms.tasks.SuccessContinuation
    public final Task then(Object obj) {
        return Tasks.forResult(null);
    }
}
