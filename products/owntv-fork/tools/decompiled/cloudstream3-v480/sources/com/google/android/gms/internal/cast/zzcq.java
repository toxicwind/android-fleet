package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.common.util.PlatformVersion;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzcq extends UIController {
    private final ImageView zza;
    private final View zzb;
    private final boolean zzc;
    private final Drawable zzd;
    private final String zze;
    private final Drawable zzf;
    private final String zzg;
    private final Drawable zzh;
    private final String zzi;
    private boolean zzj = false;

    public zzcq(ImageView imageView, Context context, Drawable drawable, Drawable drawable2, Drawable drawable3, View view, boolean z) {
        this.zza = imageView;
        this.zzd = drawable;
        this.zzf = drawable2;
        this.zzh = drawable3 != null ? drawable3 : drawable2;
        this.zze = context.getString(R.string.cast_play);
        this.zzg = context.getString(R.string.cast_pause);
        this.zzi = context.getString(R.string.cast_stop);
        this.zzb = view;
        this.zzc = z;
        imageView.setEnabled(false);
    }

    private final void zza(Drawable drawable, String str) {
        ImageView imageView = this.zza;
        boolean zEquals = drawable.equals(imageView.getDrawable());
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(str);
        imageView.setVisibility(0);
        imageView.setEnabled(true);
        View view = this.zzb;
        if (view != null) {
            view.setVisibility(8);
        }
        if (zEquals || !this.zzj) {
            return;
        }
        imageView.sendAccessibilityEvent(8);
    }

    private final void zzb(boolean z) {
        if (PlatformVersion.isAtLeastLollipop()) {
            this.zzj = this.zza.isAccessibilityFocused();
        }
        View view = this.zzb;
        if (view != null) {
            view.setVisibility(0);
            if (this.zzj) {
                view.sendAccessibilityEvent(8);
            }
        }
        ImageView imageView = this.zza;
        imageView.setVisibility(true == this.zzc ? 4 : 0);
        imageView.setEnabled(!z);
    }

    private final void zzc() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zza.setEnabled(false);
            return;
        }
        if (remoteMediaClient.isPlaying()) {
            if (remoteMediaClient.isLiveStream()) {
                zza(this.zzh, this.zzi);
                return;
            } else {
                zza(this.zzf, this.zzg);
                return;
            }
        }
        if (remoteMediaClient.isBuffering()) {
            zzb(false);
        } else if (remoteMediaClient.isPaused()) {
            zza(this.zzd, this.zze);
        } else if (remoteMediaClient.isLoadingNextItem()) {
            zzb(true);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzc();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        zzb(true);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zzc();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zza.setEnabled(false);
        super.onSessionEnded();
    }
}
