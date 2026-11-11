package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzp {
    private static final Logger zza = new Logger("FeatureUsageAnalytics");
    private static final String zzb = "22.1.0";
    private static zzp zzc;
    private final zzh zzd;
    private final SharedPreferences zze;
    private final String zzf;
    private long zzl;
    private final Clock zzk = DefaultClock.getInstance();
    private final Set zzi = new HashSet();
    private final Set zzj = new HashSet();
    private final Handler zzh = new zzet(Looper.getMainLooper());
    private final Runnable zzg = new Runnable() { // from class: com.google.android.gms.internal.cast.zzo
        @Override // java.lang.Runnable
        public final void run() {
            zzp.zzc(this.zza);
        }
    };

    private zzp(SharedPreferences sharedPreferences, zzh zzhVar, String str) {
        this.zze = sharedPreferences;
        this.zzd = zzhVar;
        this.zzf = str;
    }

    public static synchronized zzp zza(SharedPreferences sharedPreferences, zzh zzhVar, String str) {
        if (zzc == null) {
            zzc = new zzp(sharedPreferences, zzhVar, str);
        }
        return zzc;
    }

    static String zzb(String str, String str2) {
        return String.format("%s%s", str, str2);
    }

    public static /* synthetic */ void zzc(zzp zzpVar) {
        Set set = zzpVar.zzi;
        if (set.isEmpty()) {
            return;
        }
        Set set2 = zzpVar.zzj;
        long j = true != set2.equals(set) ? 86400000L : 172800000L;
        long jZzf = zzpVar.zzf();
        long j2 = zzpVar.zzl;
        if (j2 == 0 || jZzf - j2 >= j) {
            zza.d("Upload the feature usage report.", new Object[0]);
            zzpo zzpoVarZza = zzpp.zza();
            zzpoVarZza.zzb(zzb);
            zzpoVarZza.zza(zzpVar.zzf);
            zzpp zzppVar = (zzpp) zzpoVarZza.zzr();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(set);
            zzpi zzpiVarZza = zzpj.zza();
            zzpiVarZza.zza(arrayList);
            zzpiVarZza.zzb(zzppVar);
            zzpj zzpjVar = (zzpj) zzpiVarZza.zzr();
            zzqd zzqdVarZzc = zzqe.zzc();
            zzqdVarZzc.zzc(zzpjVar);
            zzpVar.zzd.zzf((zzqe) zzqdVarZzc.zzr(), 243);
            SharedPreferences sharedPreferences = zzpVar.zze;
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            if (!set2.equals(set)) {
                set2.clear();
                set2.addAll(set);
                Iterator it = set2.iterator();
                while (it.hasNext()) {
                    String string = Integer.toString(((zzpb) it.next()).zza());
                    String strZzh = zzpVar.zzh(string);
                    String strZzb = zzb("feature_usage_timestamp_reported_feature_", string);
                    if (!TextUtils.equals(strZzh, strZzb)) {
                        long j3 = sharedPreferences.getLong(strZzh, 0L);
                        editorEdit.remove(strZzh);
                        if (j3 != 0) {
                            editorEdit.putLong(strZzb, j3);
                        }
                    }
                }
            }
            zzpVar.zzl = jZzf;
            editorEdit.putLong("feature_usage_last_report_time", jZzf).apply();
        }
    }

    public static void zzd(zzpb zzpbVar) {
        zzp zzpVar;
        if (!zzh.zza || (zzpVar = zzc) == null) {
            return;
        }
        zzpVar.zze.edit().putLong(zzpVar.zzh(Integer.toString(zzpbVar.zza())), zzpVar.zzf()).apply();
        zzpVar.zzi.add(zzpbVar);
        zzpVar.zzj();
    }

    private final long zzf() {
        return ((Clock) Preconditions.checkNotNull(this.zzk)).currentTimeMillis();
    }

    private static zzpb zzg(String str) {
        try {
            switch (Integer.parseInt(str)) {
                case 0:
                    return zzpb.DEVELOPER_FEATURE_FLAG_UNKNOWN;
                case 1:
                    return zzpb.CAF_CAST_BUTTON;
                case 2:
                    return zzpb.CAF_EXPANDED_CONTROLLER;
                case 3:
                    return zzpb.CAF_MINI_CONTROLLER;
                case 4:
                    return zzpb.CAF_CONTAINER_CONTROLLER;
                case 5:
                    return zzpb.CAST_CONTEXT;
                case 6:
                    return zzpb.IMAGE_CACHE;
                case 7:
                    return zzpb.IMAGE_PICKER;
                case 8:
                    return zzpb.AD_BREAK_PARSER;
                case 9:
                    return zzpb.UI_STYLE;
                case 10:
                    return zzpb.HARDWARE_VOLUME_BUTTON;
                case 11:
                    return zzpb.NON_CAST_DEVICE_PROVIDER;
                case 12:
                    return zzpb.PAUSE_CONTROLLER;
                case 13:
                    return zzpb.SEEK_CONTROLLER;
                case 14:
                    return zzpb.STREAM_VOLUME;
                case 15:
                    return zzpb.UI_MEDIA_CONTROLLER;
                case 16:
                    return zzpb.PLAYBACK_RATE_CONTROLLER;
                case 17:
                    return zzpb.PRECACHE;
                case 18:
                    return zzpb.INSTRUCTIONS_VIEW;
                case 19:
                    return zzpb.OPTION_SUSPEND_SESSIONS_WHEN_BACKGROUNDED;
                case 20:
                    return zzpb.OPTION_STOP_RECEIVER_APPLICATION_WHEN_ENDING_SESSION;
                case 21:
                    return zzpb.OPTION_DISABLE_DISCOVERY_AUTOSTART;
                case 22:
                    return zzpb.OPTION_DISABLE_ANALYTICS_LOGGING;
                case 23:
                    return zzpb.OPTION_PHYSICAL_VOLUME_BUTTONS_WILL_CONTROL_DEVICE_VOLUME;
                case 24:
                    return zzpb.CAF_EXPANDED_CONTROLLER_HIDE_STREAM_POSITION_CONTROLS_FOR_LIVE_CONTENT;
                case 25:
                    return zzpb.CAF_EXPANDED_CONTROLLER_WITH_LIVE_CONTENT;
                case 26:
                    return zzpb.REMOTE_MEDIA_CLIENT_LOAD_MEDIA_WITH_OPTIONS;
                case 27:
                    return zzpb.REMOTE_MEDIA_CLIENT_QUEUE_LOAD_ITEMS_WITH_OPTIONS;
                case 28:
                    return zzpb.REMOTE_MEDIA_CLIENT_LOAD_MEDIA_WITH_LOAD_REQUEST_DATA;
                case 29:
                    return zzpb.LAUNCH_OPTION_ANDROID_RECEIVER_COMPATIBLE;
                case 30:
                    return zzpb.CAST_CONTEXT_SET_LAUNCH_CREDENTIALS_DATA;
                case 31:
                    return zzpb.START_DISCOVERY_AFTER_FIRST_TAP_ON_CAST_BUTTON;
                case 32:
                    return zzpb.CAST_UNAVAILABLE_BUTTON_VISIBLE;
                case 33:
                    return zzpb.CAST_DEFAULT_MEDIA_ROUTER_DIALOG;
                case 34:
                    return zzpb.CAST_CUSTOM_MEDIA_ROUTER_DIALOG;
                case 35:
                    return zzpb.CAST_OUTPUT_SWITCHER_ENABLED;
                case 36:
                    return zzpb.CAST_TRANSFER_TO_LOCAL_ENABLED;
                case 37:
                    return zzpb.CAST_BUTTON_IS_TRIGGERED_DEFAULT_CAST_DIALOG_FALSE;
                case 38:
                    return zzpb.CAST_BUTTON_DELEGATE;
                case 39:
                    return zzpb.CAST_BUTTON_DELEGATE_PRESENT_LNA_PERMISSION_CUSTOM_DIALOG;
                case 40:
                    return zzpb.CAST_BUTTON_DELEGATE_PRESENT_CAST_STATE_CUSTOM_DIALOG;
                case 41:
                    return zzpb.CAST_TRANSFER_TO_LOCAL_USED;
                case 42:
                    return zzpb.MEDIA_REQUEST_ITEM_MAP_HLS_SEGMENT_FORMAT_TO_STRING;
                case 43:
                    return zzpb.MEDIA_REQUEST_ITEM_MAP_HLS_SEGMENT_FORMAT_STRING_TO_ENUM;
                case 44:
                    return zzpb.HLS_SEGMENT_MAP_HLS_SEGMENT_FORMAT_TO_STRING;
                case 45:
                    return zzpb.HLS_SEGMENT_MAP_HLS_SEGMENT_FORMAT_STRING_TO_ENUM;
                case 46:
                    return zzpb.HLS_VIDEO_SEGMENT_MAP_HLS_VIDEO_SEGMENT_FORMAT_TO_STRING;
                case 47:
                    return zzpb.HLS_VIDEO_SEGMENT_MAP_HLS_VIDEO_SEGMENT_FORMAT_STRING_TO_ENUM;
                case 48:
                    return zzpb.CAST_SLIDER_SET_AD_BLOCK_POSITIONS;
                case 49:
                    return zzpb.CAF_NOTIFICATION_SERVICE;
                case 50:
                    return zzpb.HARDWARE_VOLUME_BUTTON_PRESS;
                case 51:
                    return zzpb.CAST_SDK_DEFAULT_DEVICE_DIALOG;
                case 52:
                    return zzpb.CAST_SDK_CUSTOM_DEVICE_DIALOG;
                case 53:
                    return zzpb.PERSISTENT_CAST_BUTTON_DISCOVERY_DISABLED_WITH_CONFLICT_TYPES;
                case 54:
                    return zzpb.CAST_DEVICE_DIALOG_FACTORY_INSTANTIATED;
                case 55:
                    return zzpb.CAF_MEDIA_NOTIFICATION_PROXY;
                case 56:
                    return zzpb.REMOTE_CONNECTION_MANAGER_ACQUIRED;
                case 57:
                    return zzpb.REMOTE_CONNECTION_CALLBACK_SET;
                default:
                    return null;
            }
        } catch (NumberFormatException unused) {
            return zzpb.DEVELOPER_FEATURE_FLAG_UNKNOWN;
        }
    }

    @RequiresNonNull({"sharedPreferences"})
    private final String zzh(String str) {
        SharedPreferences sharedPreferences = this.zze;
        String strZzb = zzb("feature_usage_timestamp_reported_feature_", str);
        return sharedPreferences.contains(strZzb) ? strZzb : zzb("feature_usage_timestamp_detected_feature_", str);
    }

    private final void zzi(Set set) {
        if (set.isEmpty()) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.zze.edit();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            editorEdit.remove((String) it.next());
        }
        editorEdit.apply();
    }

    @RequiresNonNull({"handler", "reportFeatureUsageRunnable"})
    private final void zzj() {
        this.zzh.post(this.zzg);
    }

    public final void zze() {
        zzpb zzpbVarZzg;
        SharedPreferences sharedPreferences = this.zze;
        Set set = this.zzi;
        String string = sharedPreferences.getString("feature_usage_sdk_version", null);
        String string2 = sharedPreferences.getString("feature_usage_package_name", null);
        set.clear();
        Set set2 = this.zzj;
        set2.clear();
        this.zzl = 0L;
        String str = zzb;
        if (!str.equals(string) || !this.zzf.equals(string2)) {
            HashSet hashSet = new HashSet();
            for (String str2 : sharedPreferences.getAll().keySet()) {
                if (str2.startsWith("feature_usage_timestamp_")) {
                    hashSet.add(str2);
                }
            }
            hashSet.add("feature_usage_last_report_time");
            zzi(hashSet);
            sharedPreferences.edit().putString("feature_usage_sdk_version", str).putString("feature_usage_package_name", this.zzf).apply();
            return;
        }
        this.zzl = sharedPreferences.getLong("feature_usage_last_report_time", 0L);
        long jZzf = zzf();
        HashSet hashSet2 = new HashSet();
        for (String str3 : sharedPreferences.getAll().keySet()) {
            if (str3.startsWith("feature_usage_timestamp_")) {
                long j = sharedPreferences.getLong(str3, 0L);
                if (j != 0 && jZzf - j > 1209600000) {
                    hashSet2.add(str3);
                } else if (str3.startsWith("feature_usage_timestamp_reported_feature_")) {
                    zzpb zzpbVarZzg2 = zzg(str3.substring(41));
                    if (zzpbVarZzg2 != null) {
                        set2.add(zzpbVarZzg2);
                        set.add(zzpbVarZzg2);
                    }
                } else if (str3.startsWith("feature_usage_timestamp_detected_feature_") && (zzpbVarZzg = zzg(str3.substring(41))) != null) {
                    set.add(zzpbVarZzg);
                }
            }
        }
        zzi(hashSet2);
        Preconditions.checkNotNull(this.zzh);
        Preconditions.checkNotNull(this.zzg);
        zzj();
    }
}
