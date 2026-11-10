package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class zak extends LifecycleCallback implements DialogInterface.OnCancelListener {
    public final GoogleApiAvailability zace;
    public volatile boolean zadh;
    public final AtomicReference<zam> zadi;
    public final Handler zadj;

    public zak(LifecycleFragment lifecycleFragment) {
        this(lifecycleFragment, GoogleApiAvailability.zaao);
    }

    public static int zaa(@Nullable zam zamVar) {
        if (zamVar == null) {
            return -1;
        }
        return zamVar.zap();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onActivityResult(int i, int i2, Intent intent) {
        zam zamVar = this.zadi.get();
        boolean z = true;
        if (i != 1) {
            if (i != 2) {
                z = false;
            } else {
                int iIsGooglePlayServicesAvailable = this.zace.isGooglePlayServicesAvailable(getActivity());
                z = iIsGooglePlayServicesAvailable == 0;
                if (zamVar == null) {
                    return;
                }
                if (zamVar.getConnectionResult().getErrorCode() == 18 && iIsGooglePlayServicesAvailable == 18) {
                    return;
                }
            }
        } else if (i2 != -1) {
            if (i2 == 0) {
                zam zamVar2 = new zam(new ConnectionResult(1, intent != null ? intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13) : 13, null, zamVar.getConnectionResult().toString()), zaa(zamVar));
                this.zadi.set(zamVar2);
                zamVar = zamVar2;
            }
            z = false;
        }
        if (z) {
            zao();
        } else if (zamVar != null) {
            zaa(zamVar.getConnectionResult(), zamVar.zap());
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        zaa(new ConnectionResult(13, null), zaa(this.zadi.get()));
        zao();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.zadi.set(bundle.getBoolean("resolving_error", false) ? new zam(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onSaveInstanceState(Bundle bundle) {
        zam zamVar = this.zadi.get();
        if (zamVar != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zamVar.zap());
            bundle.putInt("failed_status", zamVar.getConnectionResult().getErrorCode());
            bundle.putParcelable("failed_resolution", zamVar.getConnectionResult().getResolution());
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        this.zadh = true;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        this.zadh = false;
    }

    public abstract void zaa(ConnectionResult connectionResult, int i);

    public final void zab(ConnectionResult connectionResult, int i) {
        zam zamVar = new zam(connectionResult, i);
        if (this.zadi.compareAndSet(null, zamVar)) {
            this.zadj.post(new zal(this, zamVar));
        }
    }

    public abstract void zam();

    public final void zao() {
        this.zadi.set(null);
        zam();
    }

    @VisibleForTesting
    public zak(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.zadi = new AtomicReference<>(null);
        this.zadj = new com.google.android.gms.internal.base.zar(Looper.getMainLooper());
        this.zace = googleApiAvailability;
    }
}
