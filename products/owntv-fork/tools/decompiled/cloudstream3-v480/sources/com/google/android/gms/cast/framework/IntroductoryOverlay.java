package com.google.android.gms.cast.framework;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import androidx.mediarouter.app.MediaRouteButton;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzpb;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public interface IntroductoryOverlay {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
    public interface OnOverlayDismissedListener {
        void onOverlayDismissed();
    }

    void remove();

    void show();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
    public static class Builder {
        private final Activity zza;
        private final View zzb;
        private int zzc;
        private String zzd;
        private OnOverlayDismissedListener zze;
        private boolean zzf;

        public Builder(Activity activity, MenuItem menuItem) {
            this.zza = (Activity) Preconditions.checkNotNull(activity);
            this.zzb = ((MenuItem) Preconditions.checkNotNull(menuItem)).getActionView();
        }

        public IntroductoryOverlay build() {
            com.google.android.gms.internal.cast.zzp.zzd(zzpb.INSTRUCTIONS_VIEW);
            return new com.google.android.gms.internal.cast.zzas(this);
        }

        public Builder setButtonText(int i) {
            this.zza.getResources().getString(i);
            return this;
        }

        public Builder setButtonText(String str) {
            return this;
        }

        public Builder setFocusRadius(float f) {
            return this;
        }

        public Builder setFocusRadiusId(int i) {
            this.zza.getResources().getDimension(i);
            return this;
        }

        public Builder setOnOverlayDismissedListener(OnOverlayDismissedListener onOverlayDismissedListener) {
            this.zze = onOverlayDismissedListener;
            return this;
        }

        public Builder setOverlayColor(int i) {
            this.zzc = this.zza.getResources().getColor(i);
            return this;
        }

        public Builder setSingleTime() {
            this.zzf = true;
            return this;
        }

        public Builder setTitleText(int i) {
            this.zzd = this.zza.getResources().getString(i);
            return this;
        }

        public Builder setTitleText(String str) {
            this.zzd = str;
            return this;
        }

        public final int zza() {
            return this.zzc;
        }

        public final Activity zzb() {
            return this.zza;
        }

        public final View zzc() {
            return this.zzb;
        }

        public final OnOverlayDismissedListener zzd() {
            return this.zze;
        }

        public final String zze() {
            return this.zzd;
        }

        public final boolean zzf() {
            return this.zzf;
        }

        public Builder(Activity activity, MediaRouteButton mediaRouteButton) {
            this.zza = (Activity) Preconditions.checkNotNull(activity);
            this.zzb = (View) Preconditions.checkNotNull(mediaRouteButton);
        }
    }
}
