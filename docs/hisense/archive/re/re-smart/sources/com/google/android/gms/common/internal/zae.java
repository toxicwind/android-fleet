package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.common.api.internal.LifecycleFragment;

/* JADX INFO: loaded from: classes.dex */
public final class zae extends DialogRedirect {
    public final /* synthetic */ int val$requestCode;
    public final /* synthetic */ Intent zaos;
    public final /* synthetic */ LifecycleFragment zaot;

    public zae(Intent intent, LifecycleFragment lifecycleFragment, int i) {
        this.zaos = intent;
        this.zaot = lifecycleFragment;
        this.val$requestCode = i;
    }

    @Override // com.google.android.gms.common.internal.DialogRedirect
    public final void redirect() {
        Intent intent = this.zaos;
        if (intent != null) {
            this.zaot.startActivityForResult(intent, this.val$requestCode);
        }
    }
}
