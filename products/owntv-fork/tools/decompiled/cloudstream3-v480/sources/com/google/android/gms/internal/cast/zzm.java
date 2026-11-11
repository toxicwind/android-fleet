package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import com.google.android.gms.cast.internal.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzm {
    public String zzb;
    public String zzc;
    public String zzf;
    public int zzg;
    public boolean zzo;
    public int zzp;
    private final zzaf zzr;
    private static final Logger zzq = new Logger("ApplicationAnalyticsSession");
    public static long zza = System.currentTimeMillis();
    public String zzh = "";
    public String zzi = "";
    public String zzj = "";
    public String zzk = "";
    public String zzl = "";
    public String zzm = "";
    public int zzn = 0;
    public long zzd = zza;
    public int zze = 1;

    private zzm(zzaf zzafVar) {
        this.zzr = zzafVar;
    }

    public static zzm zza(zzaf zzafVar) {
        zzm zzmVar = new zzm(zzafVar);
        zza++;
        return zzmVar;
    }

    public static zzm zzb(SharedPreferences sharedPreferences, zzaf zzafVar) {
        if (sharedPreferences == null) {
            return null;
        }
        zzm zzmVar = new zzm(zzafVar);
        zzmVar.zzo = sharedPreferences.getBoolean("is_output_switcher_enabled", false);
        if (!sharedPreferences.contains("application_id")) {
            return null;
        }
        zzmVar.zzb = sharedPreferences.getString("application_id", "");
        if (!sharedPreferences.contains("receiver_metrics_id")) {
            return null;
        }
        zzmVar.zzc = sharedPreferences.getString("receiver_metrics_id", "");
        if (!sharedPreferences.contains("analytics_session_id")) {
            return null;
        }
        zzmVar.zzd = sharedPreferences.getLong("analytics_session_id", 0L);
        if (!sharedPreferences.contains("event_sequence_number")) {
            return null;
        }
        zzmVar.zze = sharedPreferences.getInt("event_sequence_number", 0);
        if (!sharedPreferences.contains("receiver_session_id")) {
            return null;
        }
        zzmVar.zzf = sharedPreferences.getString("receiver_session_id", "");
        zzmVar.zzg = sharedPreferences.getInt("device_capabilities", 0);
        zzmVar.zzh = sharedPreferences.getString("device_model_name", "");
        zzmVar.zzi = sharedPreferences.getString("manufacturer", "");
        zzmVar.zzj = sharedPreferences.getString("product_name", "");
        zzmVar.zzk = sharedPreferences.getString("build_type", "");
        zzmVar.zzl = sharedPreferences.getString("cast_build_version", "");
        zzmVar.zzm = sharedPreferences.getString("system_build_number", "");
        zzmVar.zzn = sharedPreferences.getInt("device_category", 0);
        zzmVar.zzp = sharedPreferences.getInt("analytics_session_start_type", 0);
        return zzmVar;
    }

    public final void zzc(SharedPreferences sharedPreferences) {
        if (sharedPreferences == null) {
            return;
        }
        zzq.d("Save the ApplicationAnalyticsSession to SharedPreferences %s", sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString("application_id", this.zzb);
        editorEdit.putString("receiver_metrics_id", this.zzc);
        editorEdit.putLong("analytics_session_id", this.zzd);
        editorEdit.putInt("event_sequence_number", this.zze);
        editorEdit.putString("receiver_session_id", this.zzf);
        editorEdit.putInt("device_capabilities", this.zzg);
        editorEdit.putString("device_model_name", this.zzh);
        editorEdit.putString("manufacturer", this.zzi);
        editorEdit.putString("product_name", this.zzj);
        editorEdit.putString("build_type", this.zzk);
        editorEdit.putString("cast_build_version", this.zzl);
        editorEdit.putString("system_build_number", this.zzm);
        editorEdit.putInt("device_category", this.zzn);
        editorEdit.putInt("analytics_session_start_type", this.zzp);
        editorEdit.putBoolean("is_output_switcher_enabled", this.zzo);
        editorEdit.apply();
    }

    public final boolean zzd() {
        return this.zzr.zzf();
    }
}
