package com.google.android.gms.cast;

import android.hardware.display.DisplayManager;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.ComplianceOptions;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.internal.cast.zzeg;
import com.google.android.gms.internal.cast.zzel;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzab extends zzad {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ zzeg zzb;
    final /* synthetic */ CastRemoteDisplayClient zzc;
    final /* synthetic */ zzak zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzab(CastRemoteDisplayClient castRemoteDisplayClient, TaskCompletionSource taskCompletionSource, zzeg zzegVar, zzak zzakVar) {
        super(null);
        this.zza = taskCompletionSource;
        this.zzb = zzegVar;
        this.zzd = zzakVar;
        this.zzc = castRemoteDisplayClient;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.cast.zzad, com.google.android.gms.internal.cast.zzek
    public final void zzb(int i, int i2, Surface surface) throws RemoteException {
        CastRemoteDisplayClient castRemoteDisplayClient = this.zzc;
        castRemoteDisplayClient.zzc.d("onConnected", new Object[0]);
        DisplayManager displayManager = (DisplayManager) castRemoteDisplayClient.getApplicationContext().getSystemService("display");
        if (displayManager == null) {
            castRemoteDisplayClient.zzc.e("Unable to get the display manager", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zza);
            return;
        }
        CastRemoteDisplayClient.zzd(castRemoteDisplayClient);
        castRemoteDisplayClient.zzd = displayManager.createVirtualDisplay("private_display", i, i2, (Math.min(i, i2) * 320) / 1080, surface, 2);
        if (castRemoteDisplayClient.zzd == null) {
            castRemoteDisplayClient.zzc.e("Unable to create virtual display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zza);
            return;
        }
        Display display = castRemoteDisplayClient.zzd.getDisplay();
        if (display == null) {
            castRemoteDisplayClient.zzc.e("Virtual display does not have a display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zza);
            return;
        }
        try {
            zzeg zzegVar = this.zzb;
            ((zzel) zzegVar.getService()).zzf(this, display.getDisplayId(), ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(zzegVar.getContext()).build()));
        } catch (RemoteException | IllegalStateException unused) {
            this.zzc.zzc.e("Unable to provision the route's new virtual Display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zza);
        }
    }

    @Override // com.google.android.gms.cast.zzad, com.google.android.gms.internal.cast.zzek
    public final void zzc() {
        CastRemoteDisplayClient castRemoteDisplayClient = this.zzc;
        castRemoteDisplayClient.zzc.d("onConnectedWithDisplay", new Object[0]);
        if (castRemoteDisplayClient.zzd == null) {
            castRemoteDisplayClient.zzc.e("There is no virtual display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zza);
            return;
        }
        Display display = castRemoteDisplayClient.zzd.getDisplay();
        if (display != null) {
            TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, display, this.zza);
        } else {
            castRemoteDisplayClient.zzc.e("Virtual display no longer has a display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zza);
        }
    }

    @Override // com.google.android.gms.cast.zzad, com.google.android.gms.internal.cast.zzek
    public final void zzd(int i) throws RemoteException {
        CastRemoteDisplayClient castRemoteDisplayClient = this.zzc;
        castRemoteDisplayClient.zzc.d("onError: %d", Integer.valueOf(i));
        CastRemoteDisplayClient.zzd(castRemoteDisplayClient);
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zza);
    }

    @Override // com.google.android.gms.cast.zzad, com.google.android.gms.internal.cast.zzek
    public final void zze(boolean z) {
        this.zzc.zzc.d("onRemoteDisplayMuteStateChanged: %b", Boolean.valueOf(z));
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            CastRemoteDisplayLocalService castRemoteDisplayLocalService = zzakVar.zza;
            castRemoteDisplayLocalService.zzv("onRemoteDisplayMuteStateChanged: " + z);
            CastRemoteDisplayLocalService.Callbacks callbacks = (CastRemoteDisplayLocalService.Callbacks) castRemoteDisplayLocalService.zzg.get();
            if (callbacks != null) {
                callbacks.onRemoteDisplayMuteStateChanged(z);
            }
        }
    }
}
