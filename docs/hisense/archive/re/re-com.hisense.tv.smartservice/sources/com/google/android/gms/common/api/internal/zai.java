package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.installations.Utils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class zai extends zak {
    public final SparseArray<zaa> zacw;

    private class zaa implements GoogleApiClient.OnConnectionFailedListener {
        public final int zadd;
        public final GoogleApiClient zade;
        public final GoogleApiClient.OnConnectionFailedListener zadf;

        public zaa(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.zadd = i;
            this.zade = googleApiClient;
            this.zadf = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
        public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            String strValueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 27);
            sb.append("beginFailureResolution for ");
            sb.append(strValueOf);
            Log.d("AutoManageHelper", sb.toString());
            zai.this.zab(connectionResult, this.zadd);
        }
    }

    public zai(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment, GoogleApiAvailability.zaao);
        this.zacw = new SparseArray<>();
        this.mLifecycleFragment.addCallback("AutoManageHelper", this);
    }

    public static zai zaa(LifecycleActivity lifecycleActivity) {
        LifecycleFragment fragment = LifecycleCallback.getFragment(lifecycleActivity);
        zai zaiVar = (zai) fragment.getCallbackOrNull("AutoManageHelper", zai.class);
        return zaiVar != null ? zaiVar : new zai(fragment);
    }

    @Nullable
    private final zaa zab(int i) {
        if (this.zacw.size() <= i) {
            return null;
        }
        SparseArray<zaa> sparseArray = this.zacw;
        return sparseArray.get(sparseArray.keyAt(i));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.zacw.size(); i++) {
            zaa zaaVarZab = zab(i);
            if (zaaVarZab != null) {
                printWriter.append((CharSequence) str).append("GoogleApiClient #").print(zaaVarZab.zadd);
                printWriter.println(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
                zaaVarZab.zade.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zak, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        this.zadh = true;
        boolean z = this.zadh;
        String strValueOf = String.valueOf(this.zacw);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 14);
        sb.append("onStart ");
        sb.append(z);
        sb.append(" ");
        sb.append(strValueOf);
        Log.d("AutoManageHelper", sb.toString());
        if (this.zadi.get() == null) {
            for (int i = 0; i < this.zacw.size(); i++) {
                zaa zaaVarZab = zab(i);
                if (zaaVarZab != null) {
                    zaaVarZab.zade.connect();
                }
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zak, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        this.zadh = false;
        for (int i = 0; i < this.zacw.size(); i++) {
            zaa zaaVarZab = zab(i);
            if (zaaVarZab != null) {
                zaaVarZab.zade.disconnect();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zak
    public final void zam() {
        for (int i = 0; i < this.zacw.size(); i++) {
            zaa zaaVarZab = zab(i);
            if (zaaVarZab != null) {
                zaaVarZab.zade.connect();
            }
        }
    }

    public final void zaa(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        boolean z = this.zacw.indexOfKey(i) < 0;
        StringBuilder sb = new StringBuilder(54);
        sb.append("Already managing a GoogleApiClient with id ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zam zamVar = this.zadi.get();
        boolean z2 = this.zadh;
        String strValueOf = String.valueOf(zamVar);
        StringBuilder sb2 = new StringBuilder(strValueOf.length() + 49);
        sb2.append("starting AutoManage for client ");
        sb2.append(i);
        sb2.append(" ");
        sb2.append(z2);
        sb2.append(" ");
        sb2.append(strValueOf);
        Log.d("AutoManageHelper", sb2.toString());
        this.zacw.put(i, new zaa(i, googleApiClient, onConnectionFailedListener));
        if (this.zadh && zamVar == null) {
            String strValueOf2 = String.valueOf(googleApiClient);
            StringBuilder sb3 = new StringBuilder(strValueOf2.length() + 11);
            sb3.append("connecting ");
            sb3.append(strValueOf2);
            Log.d("AutoManageHelper", sb3.toString());
            googleApiClient.connect();
        }
    }

    public final void zaa(int i) {
        zaa zaaVar = this.zacw.get(i);
        this.zacw.remove(i);
        if (zaaVar != null) {
            zaaVar.zade.unregisterConnectionFailedListener(zaaVar);
            zaaVar.zade.disconnect();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zak
    public final void zaa(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zaa zaaVar = this.zacw.get(i);
        if (zaaVar != null) {
            zaa(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zaaVar.zadf;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }
}
