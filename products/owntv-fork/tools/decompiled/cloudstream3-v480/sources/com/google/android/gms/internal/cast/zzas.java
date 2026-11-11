package com.google.android.gms.internal.cast;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.RelativeLayout;
import com.google.android.gms.cast.framework.IntroductoryOverlay;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.internal.featurehighlight.HelpTextView;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzas extends RelativeLayout implements IntroductoryOverlay {
    private final boolean zza;
    private Activity zzb;
    private IntroductoryOverlay.OnOverlayDismissedListener zzc;
    private View zzd;
    private String zze;
    private boolean zzf;
    private int zzg;

    public zzas(IntroductoryOverlay.Builder builder) {
        super(builder.zzb());
        this.zzb = builder.zzb();
        this.zza = builder.zzf();
        this.zzc = builder.zzd();
        this.zzd = builder.zzc();
        this.zze = builder.zze();
        this.zzg = builder.zza();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzd() {
        removeAllViews();
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        this.zze = null;
        this.zzg = 0;
        this.zzf = false;
    }

    @Override // com.google.android.gms.cast.framework.IntroductoryOverlay
    public final void remove() {
        Activity activity;
        if (!this.zzf || (activity = this.zzb) == null) {
            return;
        }
        ((ViewGroup) activity.getWindow().getDecorView()).removeView(this);
        zzd();
    }

    @Override // com.google.android.gms.cast.framework.IntroductoryOverlay
    public final void show() {
        View view;
        Activity activity = this.zzb;
        if (activity == null || (view = this.zzd) == null || this.zzf) {
            return;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) activity.getSystemService("accessibility");
        if (accessibilityManager != null && accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return;
        }
        if (this.zza && PreferenceManager.getDefaultSharedPreferences(activity).getBoolean("googlecast-introOverlayShown", false)) {
            zzd();
            return;
        }
        com.google.android.gms.cast.framework.internal.featurehighlight.zzh zzhVar = new com.google.android.gms.cast.framework.internal.featurehighlight.zzh(activity);
        int i = this.zzg;
        if (i != 0) {
            zzhVar.zzj(i);
        }
        addView(zzhVar);
        HelpTextView helpTextView = (HelpTextView) activity.getLayoutInflater().inflate(R.layout.cast_help_text, (ViewGroup) zzhVar, false);
        helpTextView.setText(this.zze, null);
        zzhVar.zzn(helpTextView);
        zzhVar.zzi(view, null, true, new zzar(this, activity, zzhVar));
        this.zzf = true;
        ((ViewGroup) activity.getWindow().getDecorView()).addView(this);
        zzhVar.zzl(null);
    }
}
