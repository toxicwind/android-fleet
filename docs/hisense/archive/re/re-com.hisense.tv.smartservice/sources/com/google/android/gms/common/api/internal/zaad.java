package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public class zaad extends zak {
    public GoogleApiManager zabo;
    public final ArraySet<ApiKey<?>> zafs;

    public zaad(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment, GoogleApiAvailability.zaao);
        this.zafs = new ArraySet<>(0);
        this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", this);
    }

    public static void zaa(Activity activity, GoogleApiManager googleApiManager, ApiKey<?> apiKey) {
        LifecycleFragment fragment = LifecycleCallback.getFragment(activity);
        zaad zaadVar = (zaad) fragment.getCallbackOrNull("ConnectionlessLifecycleHelper", zaad.class);
        if (zaadVar == null) {
            zaadVar = new zaad(fragment);
        }
        zaadVar.zabo = googleApiManager;
        Preconditions.checkNotNull(apiKey, "ApiKey cannot be null");
        zaadVar.zafs.add(apiKey);
        googleApiManager.zaa(zaadVar);
    }

    private final void zaai() {
        if (this.zafs.isEmpty()) {
            return;
        }
        this.zabo.zaa(this);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onResume() {
        zaai();
    }

    @Override // com.google.android.gms.common.api.internal.zak, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        this.zadh = true;
        zaai();
    }

    @Override // com.google.android.gms.common.api.internal.zak, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        this.zadh = false;
        this.zabo.zab(this);
    }

    public final ArraySet<ApiKey<?>> zaah() {
        return this.zafs;
    }

    @Override // com.google.android.gms.common.api.internal.zak
    public final void zam() {
        this.zabo.zam();
    }

    @Override // com.google.android.gms.common.api.internal.zak
    public final void zaa(ConnectionResult connectionResult, int i) {
        this.zabo.zaa(connectionResult, i);
    }
}
