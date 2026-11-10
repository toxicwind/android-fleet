package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zacg implements Runnable {
    public final /* synthetic */ com.google.android.gms.signin.internal.zak zagu;
    public final /* synthetic */ zace zakl;

    public zacg(zace zaceVar, com.google.android.gms.signin.internal.zak zakVar) {
        this.zakl = zaceVar;
        this.zagu = zakVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zakl.zac(this.zagu);
    }
}
