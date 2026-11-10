package com.google.android.gms.common.api.internal;

import android.os.Build;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
public abstract class zac {
    public final int type;

    public zac(int i) {
        this.type = i;
    }

    public static Status zaa(RemoteException remoteException) {
        StringBuilder sb = new StringBuilder();
        int i = Build.VERSION.SDK_INT;
        if (remoteException instanceof TransactionTooLargeException) {
            sb.append("TransactionTooLargeException: ");
        }
        sb.append(remoteException.getLocalizedMessage());
        return new Status(8, sb.toString());
    }

    public abstract void zaa(@NonNull Status status);

    public abstract void zaa(@NonNull zaz zazVar, boolean z);

    public abstract void zaa(@NonNull RuntimeException runtimeException);

    public abstract void zac(GoogleApiManager.zaa<?> zaaVar);
}
