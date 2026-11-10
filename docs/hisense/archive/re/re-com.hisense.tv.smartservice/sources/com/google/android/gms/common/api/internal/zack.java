package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class zack<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    public final WeakReference<GoogleApiClient> zadr;
    public final zacm zakw;
    public ResultTransform<? super R, ? extends Result> zakr = null;
    public zack<? extends Result> zaks = null;
    public volatile ResultCallbacks<? super R> zakt = null;
    public PendingResult<R> zaku = null;
    public final Object zadp = new Object();
    public Status zakv = null;
    public boolean zakx = false;

    public zack(WeakReference<GoogleApiClient> weakReference) {
        Preconditions.checkNotNull(weakReference, "GoogleApiClient reference must not be null");
        this.zadr = weakReference;
        GoogleApiClient googleApiClient = this.zadr.get();
        this.zakw = new zacm(this, googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    public static void zab(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e2) {
                String strValueOf = String.valueOf(result);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 18);
                sb.append("Unable to release ");
                sb.append(strValueOf);
                Log.w("TransformedResultImpl", sb.toString(), e2);
            }
        }
    }

    private final void zabs() {
        if (this.zakr == null && this.zakt == null) {
            return;
        }
        GoogleApiClient googleApiClient = this.zadr.get();
        if (!this.zakx && this.zakr != null && googleApiClient != null) {
            googleApiClient.zaa(this);
            this.zakx = true;
        }
        Status status = this.zakv;
        if (status != null) {
            zae(status);
            return;
        }
        PendingResult<R> pendingResult = this.zaku;
        if (pendingResult != null) {
            pendingResult.setResultCallback(this);
        }
    }

    private final boolean zabu() {
        return (this.zakt == null || this.zadr.get() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zad(Status status) {
        synchronized (this.zadp) {
            this.zakv = status;
            zae(this.zakv);
        }
    }

    private final void zae(Status status) {
        synchronized (this.zadp) {
            if (this.zakr != null) {
                Status statusOnFailure = this.zakr.onFailure(status);
                Preconditions.checkNotNull(statusOnFailure, "onFailure must not return null");
                this.zaks.zad(statusOnFailure);
            } else if (zabu()) {
                this.zakt.onFailure(status);
            }
        }
    }

    @Override // com.google.android.gms.common.api.TransformedResult
    public final void andFinally(@NonNull ResultCallbacks<? super R> resultCallbacks) {
        synchronized (this.zadp) {
            boolean z = true;
            Preconditions.checkState(this.zakt == null, "Cannot call andFinally() twice.");
            if (this.zakr != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zakt = resultCallbacks;
            zabs();
        }
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final void onResult(R r) {
        synchronized (this.zadp) {
            if (!r.getStatus().isSuccess()) {
                zad(r.getStatus());
                zab(r);
            } else if (this.zakr != null) {
                zacb.zahy.submit(new zacn(this, r));
            } else if (zabu()) {
                this.zakt.onSuccess(r);
            }
        }
    }

    @Override // com.google.android.gms.common.api.TransformedResult
    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        zack<? extends Result> zackVar;
        synchronized (this.zadp) {
            boolean z = true;
            Preconditions.checkState(this.zakr == null, "Cannot call then() twice.");
            if (this.zakt != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zakr = resultTransform;
            zackVar = new zack<>(this.zadr);
            this.zaks = zackVar;
            zabs();
        }
        return zackVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zaa(PendingResult<?> pendingResult) {
        synchronized (this.zadp) {
            this.zaku = pendingResult;
            zabs();
        }
    }

    public final void zabt() {
        this.zakt = null;
    }
}
