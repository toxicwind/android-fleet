package com.google.android.gms.common.internal;

import android.content.Intent;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public final class zac extends DialogRedirect {
    public final /* synthetic */ Fragment val$fragment;
    public final /* synthetic */ int val$requestCode;
    public final /* synthetic */ Intent zaos;

    public zac(Intent intent, Fragment fragment, int i) {
        this.zaos = intent;
        this.val$fragment = fragment;
        this.val$requestCode = i;
    }

    @Override // com.google.android.gms.common.internal.DialogRedirect
    public final void redirect() {
        Intent intent = this.zaos;
        if (intent != null) {
            this.val$fragment.startActivityForResult(intent, this.val$requestCode);
        }
    }
}
