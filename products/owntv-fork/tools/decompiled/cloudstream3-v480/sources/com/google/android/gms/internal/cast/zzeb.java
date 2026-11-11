package com.google.android.gms.internal.cast;

import android.hardware.display.DisplayManager;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.ComplianceOptions;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzeb extends zzea {
    final /* synthetic */ zzed zza;
    private final zzei zzb;

    public zzeb(zzed zzedVar, zzei zzeiVar) {
        this.zza = zzedVar;
        this.zzb = zzeiVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.cast.zzea, com.google.android.gms.internal.cast.zzek
    public final void zzb(int i, int i2, Surface surface) {
        zzef.zza.d("onConnected", new Object[0]);
        zzei zzeiVar = this.zzb;
        DisplayManager displayManager = (DisplayManager) zzeiVar.getContext().getSystemService("display");
        if (displayManager == null) {
            zzef.zza.e("Unable to get the display manager", new Object[0]);
            this.zza.setResult(new zzee(Status.RESULT_INTERNAL_ERROR));
            return;
        }
        zzed zzedVar = this.zza;
        zzef zzefVar = zzedVar.zzc;
        zzef.zzf(zzefVar);
        zzefVar.zzc = displayManager.createVirtualDisplay("private_display", i, i2, ((i < i2 ? i : i2) * 320) / 1080, surface, 2);
        if (zzefVar.zzc == null) {
            zzef.zza.e("Unable to create virtual display", new Object[0]);
            zzedVar.setResult(new zzee(Status.RESULT_INTERNAL_ERROR));
        } else if (zzefVar.zzc.getDisplay() == null) {
            zzef.zza.e("Virtual display does not have a display", new Object[0]);
            zzedVar.setResult(new zzee(Status.RESULT_INTERNAL_ERROR));
        } else {
            try {
                ((zzel) zzeiVar.getService()).zzf(this, zzefVar.zzc.getDisplay().getDisplayId(), ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(zzeiVar.getContext()).build()));
            } catch (RemoteException | IllegalStateException unused) {
                zzef.zza.e("Unable to provision the route's new virtual Display", new Object[0]);
                this.zza.setResult(new zzee(Status.RESULT_INTERNAL_ERROR));
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.zzea, com.google.android.gms.internal.cast.zzek
    public final void zzc() {
        zzef.zza.d("onConnectedWithDisplay", new Object[0]);
        zzed zzedVar = this.zza;
        zzef zzefVar = zzedVar.zzc;
        if (zzefVar.zzc == null) {
            zzef.zza.e("There is no virtual display", new Object[0]);
            zzedVar.setResult(new zzee(Status.RESULT_INTERNAL_ERROR));
            return;
        }
        Display display = zzefVar.zzc.getDisplay();
        if (display != null) {
            zzedVar.setResult(new zzee(display));
        } else {
            zzef.zza.e("Virtual display no longer has a display", new Object[0]);
            zzedVar.setResult(new zzee(Status.RESULT_INTERNAL_ERROR));
        }
    }

    @Override // com.google.android.gms.internal.cast.zzea, com.google.android.gms.internal.cast.zzek
    public final void zzd(int i) throws RemoteException {
        zzef.zza.d("onError: %d", Integer.valueOf(i));
        zzed zzedVar = this.zza;
        zzef.zzf(zzedVar.zzc);
        zzedVar.setResult(new zzee(Status.RESULT_INTERNAL_ERROR));
    }
}
