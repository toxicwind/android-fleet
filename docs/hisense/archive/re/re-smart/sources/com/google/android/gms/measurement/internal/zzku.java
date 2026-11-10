package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.measurement.zzlb;
import com.google.android.gms.internal.measurement.zzpe;
import com.google.firebase.installations.Utils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class zzku extends zzki {
    public zzku(zzks zzksVar) {
        super(zzksVar);
    }

    public static final void zzA(com.google.android.gms.internal.measurement.zzfn zzfnVar, String str, Object obj) {
        List<com.google.android.gms.internal.measurement.zzfs> listZzp = zzfnVar.zzp();
        int i = 0;
        while (true) {
            if (i >= listZzp.size()) {
                i = -1;
                break;
            } else if (str.equals(listZzp.get(i).zzg())) {
                break;
            } else {
                i++;
            }
        }
        com.google.android.gms.internal.measurement.zzfr zzfrVarZze = com.google.android.gms.internal.measurement.zzfs.zze();
        zzfrVarZze.zzj(str);
        if (obj instanceof Long) {
            zzfrVarZze.zzi(((Long) obj).longValue());
        } else if (obj instanceof String) {
            zzfrVarZze.zzk((String) obj);
        } else if (obj instanceof Double) {
            zzfrVarZze.zzh(((Double) obj).doubleValue());
        } else if (obj instanceof Bundle[]) {
            zzfrVarZze.zzb(zzq((Bundle[]) obj));
        }
        if (i >= 0) {
            zzfnVar.zzj(i, zzfrVarZze);
        } else {
            zzfnVar.zze(zzfrVarZze);
        }
    }

    @WorkerThread
    public static final boolean zzB(zzat zzatVar, zzp zzpVar) {
        Preconditions.checkNotNull(zzatVar);
        Preconditions.checkNotNull(zzpVar);
        return (TextUtils.isEmpty(zzpVar.zzb) && TextUtils.isEmpty(zzpVar.zzq)) ? false : true;
    }

    public static final com.google.android.gms.internal.measurement.zzfs zzC(com.google.android.gms.internal.measurement.zzfo zzfoVar, String str) {
        for (com.google.android.gms.internal.measurement.zzfs zzfsVar : zzfoVar.zzi()) {
            if (zzfsVar.zzg().equals(str)) {
                return zzfsVar;
            }
        }
        return null;
    }

    public static final Object zzD(com.google.android.gms.internal.measurement.zzfo zzfoVar, String str) {
        com.google.android.gms.internal.measurement.zzfs zzfsVarZzC = zzC(zzfoVar, str);
        if (zzfsVarZzC == null) {
            return null;
        }
        if (zzfsVarZzC.zzy()) {
            return zzfsVarZzC.zzh();
        }
        if (zzfsVarZzC.zzw()) {
            return Long.valueOf(zzfsVarZzC.zzd());
        }
        if (zzfsVarZzC.zzu()) {
            return Double.valueOf(zzfsVarZzC.zza());
        }
        if (zzfsVarZzC.zzc() <= 0) {
            return null;
        }
        List<com.google.android.gms.internal.measurement.zzfs> listZzi = zzfsVarZzC.zzi();
        ArrayList arrayList = new ArrayList();
        for (com.google.android.gms.internal.measurement.zzfs zzfsVar : listZzi) {
            if (zzfsVar != null) {
                Bundle bundle = new Bundle();
                for (com.google.android.gms.internal.measurement.zzfs zzfsVar2 : zzfsVar.zzi()) {
                    if (zzfsVar2.zzy()) {
                        bundle.putString(zzfsVar2.zzg(), zzfsVar2.zzh());
                    } else if (zzfsVar2.zzw()) {
                        bundle.putLong(zzfsVar2.zzg(), zzfsVar2.zzd());
                    } else if (zzfsVar2.zzu()) {
                        bundle.putDouble(zzfsVar2.zzg(), zzfsVar2.zza());
                    }
                }
                if (!bundle.isEmpty()) {
                    arrayList.add(bundle);
                }
            }
        }
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }

    private final void zzE(StringBuilder sb, int i, List<com.google.android.gms.internal.measurement.zzfs> list) {
        if (list == null) {
            return;
        }
        int i2 = i + 1;
        for (com.google.android.gms.internal.measurement.zzfs zzfsVar : list) {
            if (zzfsVar != null) {
                zzG(sb, i2);
                sb.append("param {\n");
                zzJ(sb, i2, "name", zzfsVar.zzx() ? this.zzs.zzj().zze(zzfsVar.zzg()) : null);
                zzJ(sb, i2, "string_value", zzfsVar.zzy() ? zzfsVar.zzh() : null);
                zzJ(sb, i2, "int_value", zzfsVar.zzw() ? Long.valueOf(zzfsVar.zzd()) : null);
                zzJ(sb, i2, "double_value", zzfsVar.zzu() ? Double.valueOf(zzfsVar.zza()) : null);
                if (zzfsVar.zzc() > 0) {
                    zzE(sb, i2, zzfsVar.zzi());
                }
                zzG(sb, i2);
                sb.append("}\n");
            }
        }
    }

    private final void zzF(StringBuilder sb, int i, com.google.android.gms.internal.measurement.zzel zzelVar) {
        String str;
        if (zzelVar == null) {
            return;
        }
        zzG(sb, i);
        sb.append("filter {\n");
        if (zzelVar.zzh()) {
            zzJ(sb, i, "complement", Boolean.valueOf(zzelVar.zzg()));
        }
        if (zzelVar.zzj()) {
            zzJ(sb, i, "param_name", this.zzs.zzj().zze(zzelVar.zze()));
        }
        if (zzelVar.zzk()) {
            int i2 = i + 1;
            com.google.android.gms.internal.measurement.zzex zzexVarZzd = zzelVar.zzd();
            if (zzexVarZzd != null) {
                zzG(sb, i2);
                sb.append("string_filter {\n");
                if (zzexVarZzd.zzi()) {
                    switch (zzexVarZzd.zzj()) {
                        case 1:
                            str = "UNKNOWN_MATCH_TYPE";
                            break;
                        case 2:
                            str = "REGEXP";
                            break;
                        case 3:
                            str = "BEGINS_WITH";
                            break;
                        case 4:
                            str = "ENDS_WITH";
                            break;
                        case 5:
                            str = "PARTIAL";
                            break;
                        case 6:
                            str = "EXACT";
                            break;
                        default:
                            str = "IN_LIST";
                            break;
                    }
                    zzJ(sb, i2, "match_type", str);
                }
                if (zzexVarZzd.zzh()) {
                    zzJ(sb, i2, "expression", zzexVarZzd.zzd());
                }
                if (zzexVarZzd.zzg()) {
                    zzJ(sb, i2, "case_sensitive", Boolean.valueOf(zzexVarZzd.zzf()));
                }
                if (zzexVarZzd.zza() > 0) {
                    zzG(sb, i2 + 1);
                    sb.append("expression_list {\n");
                    for (String str2 : zzexVarZzd.zze()) {
                        zzG(sb, i2 + 2);
                        sb.append(str2);
                        sb.append("\n");
                    }
                    sb.append("}\n");
                }
                zzG(sb, i2);
                sb.append("}\n");
            }
        }
        if (zzelVar.zzi()) {
            zzK(sb, i + 1, "number_filter", zzelVar.zzc());
        }
        zzG(sb, i);
        sb.append("}\n");
    }

    public static final void zzG(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    public static final String zzH(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    public static final void zzI(StringBuilder sb, int i, String str, com.google.android.gms.internal.measurement.zzgd zzgdVar) {
        if (zzgdVar == null) {
            return;
        }
        zzG(sb, 3);
        sb.append(str);
        sb.append(" {\n");
        if (zzgdVar.zzb() != 0) {
            zzG(sb, 4);
            sb.append("results: ");
            int i2 = 0;
            for (Long l : zzgdVar.zzk()) {
                int i3 = i2 + 1;
                if (i2 != 0) {
                    sb.append(", ");
                }
                sb.append(l);
                i2 = i3;
            }
            sb.append('\n');
        }
        if (zzgdVar.zzd() != 0) {
            zzG(sb, 4);
            sb.append("status: ");
            int i4 = 0;
            for (Long l2 : zzgdVar.zzn()) {
                int i5 = i4 + 1;
                if (i4 != 0) {
                    sb.append(", ");
                }
                sb.append(l2);
                i4 = i5;
            }
            sb.append('\n');
        }
        if (zzgdVar.zza() != 0) {
            zzG(sb, 4);
            sb.append("dynamic_filter_timestamps: {");
            int i6 = 0;
            for (com.google.android.gms.internal.measurement.zzfm zzfmVar : zzgdVar.zzj()) {
                int i7 = i6 + 1;
                if (i6 != 0) {
                    sb.append(", ");
                }
                sb.append(zzfmVar.zzh() ? Integer.valueOf(zzfmVar.zza()) : null);
                sb.append(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
                sb.append(zzfmVar.zzg() ? Long.valueOf(zzfmVar.zzb()) : null);
                i6 = i7;
            }
            sb.append("}\n");
        }
        if (zzgdVar.zzc() != 0) {
            zzG(sb, 4);
            sb.append("sequence_filter_timestamps: {");
            int i8 = 0;
            for (com.google.android.gms.internal.measurement.zzgf zzgfVar : zzgdVar.zzm()) {
                int i9 = i8 + 1;
                if (i8 != 0) {
                    sb.append(", ");
                }
                sb.append(zzgfVar.zzi() ? Integer.valueOf(zzgfVar.zzb()) : null);
                sb.append(": [");
                Iterator<Long> it = zzgfVar.zzf().iterator();
                int i10 = 0;
                while (it.hasNext()) {
                    long jLongValue = it.next().longValue();
                    int i11 = i10 + 1;
                    if (i10 != 0) {
                        sb.append(", ");
                    }
                    sb.append(jLongValue);
                    i10 = i11;
                }
                sb.append("]");
                i8 = i9;
            }
            sb.append("}\n");
        }
        zzG(sb, 3);
        sb.append("}\n");
    }

    public static final void zzJ(StringBuilder sb, int i, String str, Object obj) {
        if (obj == null) {
            return;
        }
        zzG(sb, i + 1);
        sb.append(str);
        sb.append(": ");
        sb.append(obj);
        sb.append('\n');
    }

    public static final void zzK(StringBuilder sb, int i, String str, com.google.android.gms.internal.measurement.zzeq zzeqVar) {
        String str2;
        if (zzeqVar == null) {
            return;
        }
        zzG(sb, i);
        sb.append(str);
        sb.append(" {\n");
        if (zzeqVar.zzg()) {
            int iZzm = zzeqVar.zzm();
            if (iZzm == 1) {
                str2 = "UNKNOWN_COMPARISON_TYPE";
            } else if (iZzm == 2) {
                str2 = "LESS_THAN";
            } else if (iZzm != 3) {
                str2 = iZzm != 4 ? "BETWEEN" : "EQUAL";
            } else {
                str2 = "GREATER_THAN";
            }
            zzJ(sb, i, "comparison_type", str2);
        }
        if (zzeqVar.zzi()) {
            zzJ(sb, i, "match_as_float", Boolean.valueOf(zzeqVar.zzf()));
        }
        if (zzeqVar.zzh()) {
            zzJ(sb, i, "comparison_value", zzeqVar.zzc());
        }
        if (zzeqVar.zzk()) {
            zzJ(sb, i, "min_comparison_value", zzeqVar.zze());
        }
        if (zzeqVar.zzj()) {
            zzJ(sb, i, "max_comparison_value", zzeqVar.zzd());
        }
        zzG(sb, i);
        sb.append("}\n");
    }

    public static int zza(com.google.android.gms.internal.measurement.zzfx zzfxVar, String str) {
        for (int i = 0; i < zzfxVar.zzb(); i++) {
            if (str.equals(zzfxVar.zzak(i).zzf())) {
                return i;
            }
        }
        return -1;
    }

    public static <Builder extends zzlb> Builder zzl(Builder builder, byte[] bArr) {
        com.google.android.gms.internal.measurement.zzjj zzjjVarZzb = com.google.android.gms.internal.measurement.zzjj.zzb();
        return zzjjVarZzb != null ? (Builder) builder.zzaw(bArr, zzjjVarZzb) : (Builder) builder.zzav(bArr);
    }

    public static List<com.google.android.gms.internal.measurement.zzfs> zzq(Bundle[] bundleArr) {
        ArrayList arrayList = new ArrayList();
        for (Bundle bundle : bundleArr) {
            if (bundle != null) {
                com.google.android.gms.internal.measurement.zzfr zzfrVarZze = com.google.android.gms.internal.measurement.zzfs.zze();
                for (String str : bundle.keySet()) {
                    com.google.android.gms.internal.measurement.zzfr zzfrVarZze2 = com.google.android.gms.internal.measurement.zzfs.zze();
                    zzfrVarZze2.zzj(str);
                    Object obj = bundle.get(str);
                    if (obj instanceof Long) {
                        zzfrVarZze2.zzi(((Long) obj).longValue());
                    } else if (obj instanceof String) {
                        zzfrVarZze2.zzk((String) obj);
                    } else if (obj instanceof Double) {
                        zzfrVarZze2.zzh(((Double) obj).doubleValue());
                    }
                    zzfrVarZze.zzc(zzfrVarZze2);
                }
                if (zzfrVarZze.zza() > 0) {
                    arrayList.add(zzfrVarZze.zzaA());
                }
            }
        }
        return arrayList;
    }

    public static List<Long> zzs(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            long j = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i * 64) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    j |= 1 << i2;
                }
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    public static boolean zzw(List<Long> list, int i) {
        if (i < list.size() * 64) {
            return ((1 << (i % 64)) & list.get(i / 64).longValue()) != 0;
        }
        return false;
    }

    public static boolean zzy(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    @Override // com.google.android.gms.measurement.internal.zzki
    public final boolean zzb() {
        return false;
    }

    @WorkerThread
    public final long zzd(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        this.zzs.zzv().zzg();
        MessageDigest messageDigestZzE = zzkz.zzE();
        if (messageDigestZzE != null) {
            return zzkz.zzp(messageDigestZzE.digest(bArr));
        }
        a.a(this.zzs, "Failed to get MD5");
        return 0L;
    }

    public final Bundle zzf(Map<String, Object> map, boolean z) {
        Bundle bundle = new Bundle();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj == null) {
                bundle.putString(str, null);
            } else if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (!(obj instanceof ArrayList)) {
                bundle.putString(str, obj.toString());
            } else if (z) {
                zzpe.zzc();
                if (this.zzs.zzf().zzs(null, zzdy.zzau)) {
                    ArrayList arrayList = (ArrayList) obj;
                    ArrayList arrayList2 = new ArrayList();
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        arrayList2.add(zzf((Map) arrayList.get(i), false));
                    }
                    bundle.putParcelableArray(str, (Parcelable[]) arrayList2.toArray(new Parcelable[0]));
                } else {
                    ArrayList arrayList3 = (ArrayList) obj;
                    ArrayList<? extends Parcelable> arrayList4 = new ArrayList<>();
                    int size2 = arrayList3.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        arrayList4.add(zzf((Map) arrayList3.get(i2), false));
                    }
                    bundle.putParcelableArrayList(str, arrayList4);
                }
            }
        }
        return bundle;
    }

    public final <T extends Parcelable> T zzh(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.unmarshall(bArr, 0, bArr.length);
            parcelObtain.setDataPosition(0);
            return creator.createFromParcel(parcelObtain);
        } catch (SafeParcelReader.ParseException unused) {
            this.zzs.zzay().zzd().zza("Failed to load parcelable from buffer");
            return null;
        } finally {
            parcelObtain.recycle();
        }
    }

    public final zzat zzi(com.google.android.gms.internal.measurement.zzaa zzaaVar) {
        Object obj;
        Bundle bundleZzf = zzf(zzaaVar.zze(), true);
        String string = (!bundleZzf.containsKey("_o") || (obj = bundleZzf.get("_o")) == null) ? "app" : obj.toString();
        String strZzb = zzgs.zzb(zzaaVar.zzd());
        if (strZzb == null) {
            strZzb = zzaaVar.zzd();
        }
        return new zzat(strZzb, new zzar(bundleZzf), string, zzaaVar.zza());
    }

    public final com.google.android.gms.internal.measurement.zzfo zzj(zzao zzaoVar) {
        com.google.android.gms.internal.measurement.zzfn zzfnVarZze = com.google.android.gms.internal.measurement.zzfo.zze();
        zzfnVarZze.zzl(zzaoVar.zze);
        for (String str : zzaoVar.zzf.zza.keySet()) {
            com.google.android.gms.internal.measurement.zzfr zzfrVarZze = com.google.android.gms.internal.measurement.zzfs.zze();
            zzfrVarZze.zzj(str);
            Object objZzf = zzaoVar.zzf.zzf(str);
            Preconditions.checkNotNull(objZzf);
            zzu(zzfrVarZze, objZzf);
            zzfnVarZze.zze(zzfrVarZze);
        }
        return zzfnVarZze.zzaA();
    }

    public final String zzm(com.google.android.gms.internal.measurement.zzfw zzfwVar) {
        if (zzfwVar == null) {
            return "";
        }
        StringBuilder sbA = a.a("\nbatch {\n");
        for (com.google.android.gms.internal.measurement.zzfy zzfyVar : zzfwVar.zzd()) {
            if (zzfyVar != null) {
                zzG(sbA, 1);
                sbA.append("bundle {\n");
                if (zzfyVar.zzbh()) {
                    zzJ(sbA, 1, "protocol_version", Integer.valueOf(zzfyVar.zzd()));
                }
                zzJ(sbA, 1, "platform", zzfyVar.zzK());
                if (zzfyVar.zzbd()) {
                    zzJ(sbA, 1, "gmp_version", Long.valueOf(zzfyVar.zzn()));
                }
                if (zzfyVar.zzbn()) {
                    zzJ(sbA, 1, "uploading_gmp_version", Long.valueOf(zzfyVar.zzs()));
                }
                if (zzfyVar.zzbb()) {
                    zzJ(sbA, 1, "dynamite_version", Long.valueOf(zzfyVar.zzk()));
                }
                if (zzfyVar.zzaY()) {
                    zzJ(sbA, 1, "config_version", Long.valueOf(zzfyVar.zzi()));
                }
                zzJ(sbA, 1, "gmp_app_id", zzfyVar.zzH());
                zzJ(sbA, 1, "admob_app_id", zzfyVar.zzx());
                zzJ(sbA, 1, "app_id", zzfyVar.zzy());
                zzJ(sbA, 1, "app_version", zzfyVar.zzB());
                if (zzfyVar.zzaW()) {
                    zzJ(sbA, 1, "app_version_major", Integer.valueOf(zzfyVar.zza()));
                }
                zzJ(sbA, 1, "firebase_instance_id", zzfyVar.zzF());
                if (zzfyVar.zzba()) {
                    zzJ(sbA, 1, "dev_cert_hash", Long.valueOf(zzfyVar.zzj()));
                }
                zzJ(sbA, 1, "app_store", zzfyVar.zzA());
                if (zzfyVar.zzbm()) {
                    zzJ(sbA, 1, "upload_timestamp_millis", Long.valueOf(zzfyVar.zzr()));
                }
                if (zzfyVar.zzbk()) {
                    zzJ(sbA, 1, "start_timestamp_millis", Long.valueOf(zzfyVar.zzq()));
                }
                if (zzfyVar.zzbc()) {
                    zzJ(sbA, 1, "end_timestamp_millis", Long.valueOf(zzfyVar.zzm()));
                }
                if (zzfyVar.zzbg()) {
                    zzJ(sbA, 1, "previous_bundle_start_timestamp_millis", Long.valueOf(zzfyVar.zzp()));
                }
                if (zzfyVar.zzbf()) {
                    zzJ(sbA, 1, "previous_bundle_end_timestamp_millis", Long.valueOf(zzfyVar.zzo()));
                }
                zzJ(sbA, 1, "app_instance_id", zzfyVar.zzz());
                zzJ(sbA, 1, "resettable_device_id", zzfyVar.zzL());
                zzJ(sbA, 1, "ds_id", zzfyVar.zzE());
                if (zzfyVar.zzbe()) {
                    zzJ(sbA, 1, "limited_ad_tracking", Boolean.valueOf(zzfyVar.zzaT()));
                }
                zzJ(sbA, 1, "os_version", zzfyVar.zzJ());
                zzJ(sbA, 1, "device_model", zzfyVar.zzD());
                zzJ(sbA, 1, "user_default_language", zzfyVar.zzM());
                if (zzfyVar.zzbl()) {
                    zzJ(sbA, 1, "time_zone_offset_minutes", Integer.valueOf(zzfyVar.zzf()));
                }
                if (zzfyVar.zzaX()) {
                    zzJ(sbA, 1, "bundle_sequential_index", Integer.valueOf(zzfyVar.zzb()));
                }
                if (zzfyVar.zzbj()) {
                    zzJ(sbA, 1, "service_upload", Boolean.valueOf(zzfyVar.zzaU()));
                }
                zzJ(sbA, 1, "health_monitor", zzfyVar.zzI());
                if (!this.zzs.zzf().zzs(null, zzdy.zzam) && zzfyVar.zzaV() && zzfyVar.zzh() != 0) {
                    zzJ(sbA, 1, "android_id", Long.valueOf(zzfyVar.zzh()));
                }
                if (zzfyVar.zzbi()) {
                    zzJ(sbA, 1, "retry_counter", Integer.valueOf(zzfyVar.zze()));
                }
                if (zzfyVar.zzaZ()) {
                    zzJ(sbA, 1, "consent_signals", zzfyVar.zzC());
                }
                List<com.google.android.gms.internal.measurement.zzgh> listZzP = zzfyVar.zzP();
                if (listZzP != null) {
                    for (com.google.android.gms.internal.measurement.zzgh zzghVar : listZzP) {
                        if (zzghVar != null) {
                            zzG(sbA, 2);
                            sbA.append("user_property {\n");
                            zzJ(sbA, 2, "set_timestamp_millis", zzghVar.zzs() ? Long.valueOf(zzghVar.zzc()) : null);
                            zzJ(sbA, 2, "name", this.zzs.zzj().zzf(zzghVar.zzf()));
                            zzJ(sbA, 2, "string_value", zzghVar.zzg());
                            zzJ(sbA, 2, "int_value", zzghVar.zzr() ? Long.valueOf(zzghVar.zzb()) : null);
                            zzJ(sbA, 2, "double_value", zzghVar.zzq() ? Double.valueOf(zzghVar.zza()) : null);
                            zzG(sbA, 2);
                            sbA.append("}\n");
                        }
                    }
                }
                List<com.google.android.gms.internal.measurement.zzfk> listZzN = zzfyVar.zzN();
                if (listZzN != null) {
                    for (com.google.android.gms.internal.measurement.zzfk zzfkVar : listZzN) {
                        if (zzfkVar != null) {
                            zzG(sbA, 2);
                            sbA.append("audience_membership {\n");
                            if (zzfkVar.zzk()) {
                                zzJ(sbA, 2, "audience_id", Integer.valueOf(zzfkVar.zza()));
                            }
                            if (zzfkVar.zzm()) {
                                zzJ(sbA, 2, "new_audience", Boolean.valueOf(zzfkVar.zzj()));
                            }
                            zzI(sbA, 2, "current_data", zzfkVar.zzd());
                            if (zzfkVar.zzn()) {
                                zzI(sbA, 2, "previous_data", zzfkVar.zze());
                            }
                            zzG(sbA, 2);
                            sbA.append("}\n");
                        }
                    }
                }
                List<com.google.android.gms.internal.measurement.zzfo> listZzO = zzfyVar.zzO();
                if (listZzO != null) {
                    for (com.google.android.gms.internal.measurement.zzfo zzfoVar : listZzO) {
                        if (zzfoVar != null) {
                            zzG(sbA, 2);
                            sbA.append("event {\n");
                            zzJ(sbA, 2, "name", this.zzs.zzj().zzd(zzfoVar.zzh()));
                            if (zzfoVar.zzu()) {
                                zzJ(sbA, 2, "timestamp_millis", Long.valueOf(zzfoVar.zzd()));
                            }
                            if (zzfoVar.zzt()) {
                                zzJ(sbA, 2, "previous_timestamp_millis", Long.valueOf(zzfoVar.zzc()));
                            }
                            if (zzfoVar.zzs()) {
                                zzJ(sbA, 2, "count", Integer.valueOf(zzfoVar.zza()));
                            }
                            if (zzfoVar.zzb() != 0) {
                                zzE(sbA, 2, zzfoVar.zzi());
                            }
                            zzG(sbA, 2);
                            sbA.append("}\n");
                        }
                    }
                }
                zzG(sbA, 1);
                sbA.append("}\n");
            }
        }
        sbA.append("}\n");
        return sbA.toString();
    }

    public final String zzo(com.google.android.gms.internal.measurement.zzej zzejVar) {
        if (zzejVar == null) {
            return "null";
        }
        StringBuilder sbA = a.a("\nevent_filter {\n");
        if (zzejVar.zzp()) {
            zzJ(sbA, 0, "filter_id", Integer.valueOf(zzejVar.zzb()));
        }
        zzJ(sbA, 0, "event_name", this.zzs.zzj().zzd(zzejVar.zzg()));
        String strZzH = zzH(zzejVar.zzk(), zzejVar.zzm(), zzejVar.zzn());
        if (!strZzH.isEmpty()) {
            zzJ(sbA, 0, "filter_type", strZzH);
        }
        if (zzejVar.zzo()) {
            zzK(sbA, 1, "event_count_filter", zzejVar.zzf());
        }
        if (zzejVar.zza() > 0) {
            sbA.append("  filters {\n");
            Iterator<com.google.android.gms.internal.measurement.zzel> it = zzejVar.zzh().iterator();
            while (it.hasNext()) {
                zzF(sbA, 2, it.next());
            }
        }
        zzG(sbA, 1);
        sbA.append("}\n}\n");
        return sbA.toString();
    }

    public final String zzp(com.google.android.gms.internal.measurement.zzes zzesVar) {
        if (zzesVar == null) {
            return "null";
        }
        StringBuilder sbA = a.a("\nproperty_filter {\n");
        if (zzesVar.zzj()) {
            zzJ(sbA, 0, "filter_id", Integer.valueOf(zzesVar.zza()));
        }
        zzJ(sbA, 0, "property_name", this.zzs.zzj().zzf(zzesVar.zze()));
        String strZzH = zzH(zzesVar.zzg(), zzesVar.zzh(), zzesVar.zzi());
        if (!strZzH.isEmpty()) {
            zzJ(sbA, 0, "filter_type", strZzH);
        }
        zzF(sbA, 1, zzesVar.zzb());
        sbA.append("}\n");
        return sbA.toString();
    }

    public final List<Long> zzr(List<Long> list, List<Integer> list2) {
        int i;
        ArrayList arrayList = new ArrayList(list);
        for (Integer num : list2) {
            if (num.intValue() < 0) {
                this.zzs.zzay().zzk().zzb("Ignoring negative bit index to be cleared", num);
            } else {
                int iIntValue = num.intValue() / 64;
                if (iIntValue >= arrayList.size()) {
                    this.zzs.zzay().zzk().zzc("Ignoring bit index greater than bitSet size", num, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(iIntValue, Long.valueOf(((Long) arrayList.get(iIntValue)).longValue() & (~(1 << (num.intValue() % 64)))));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (true) {
            int i2 = size2;
            i = size;
            size = i2;
            if (size < 0 || ((Long) arrayList.get(size)).longValue() != 0) {
                break;
            }
            size2 = size - 1;
        }
        return arrayList.subList(0, i);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x005b  */
    /* JADX WARN: Code duplicated, block: B:28:0x0061  */
    /* JADX WARN: Code duplicated, block: B:30:0x0067  */
    /* JADX WARN: Code duplicated, block: B:32:0x0073  */
    /* JADX WARN: Code duplicated, block: B:34:0x0077  */
    /* JADX WARN: Code duplicated, block: B:36:0x0080  */
    /* JADX WARN: Code duplicated, block: B:38:0x0088  */
    /* JADX WARN: Code duplicated, block: B:40:0x0094  */
    /* JADX WARN: Code duplicated, block: B:42:0x0098  */
    /* JADX WARN: Code duplicated, block: B:52:0x004b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:55:0x0051 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:57:0x000d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x000d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x0070 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x0091 A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:26:0x005b, please report this as an issue */
    public final Map<String, Object> zzt(Bundle bundle, boolean z) {
        ArrayList arrayList;
        ArrayList arrayList2;
        int size;
        int i;
        Object obj;
        int i2;
        HashMap map = new HashMap();
        for (String str : bundle.keySet()) {
            Object obj2 = bundle.get(str);
            zzpe.zzc();
            if (this.zzs.zzf().zzs(null, zzdy.zzau)) {
                if ((obj2 instanceof Parcelable[]) || (obj2 instanceof ArrayList) || (obj2 instanceof Bundle)) {
                    if (z) {
                        arrayList = new ArrayList();
                        if (obj2 instanceof Parcelable[]) {
                            for (Parcelable parcelable : (Parcelable[]) obj2) {
                                if (parcelable instanceof Bundle) {
                                    arrayList.add(zzt((Bundle) parcelable, false));
                                }
                            }
                        } else if (obj2 instanceof ArrayList) {
                            arrayList2 = (ArrayList) obj2;
                            size = arrayList2.size();
                            for (i = 0; i < size; i++) {
                                obj = arrayList2.get(i);
                                if (obj instanceof Bundle) {
                                    arrayList.add(zzt((Bundle) obj, false));
                                }
                            }
                        } else if (obj2 instanceof Bundle) {
                            arrayList.add(zzt((Bundle) obj2, false));
                        }
                        map.put(str, arrayList);
                    }
                } else if (obj2 != null) {
                    map.put(str, obj2);
                }
            } else if ((obj2 instanceof Bundle[]) || (obj2 instanceof ArrayList) || (obj2 instanceof Bundle)) {
                if (z) {
                    arrayList = new ArrayList();
                    if (obj2 instanceof Parcelable[]) {
                        while (i2 < r5) {
                            if (parcelable instanceof Bundle) {
                                arrayList.add(zzt((Bundle) parcelable, false));
                            }
                        }
                    } else if (obj2 instanceof ArrayList) {
                        arrayList2 = (ArrayList) obj2;
                        size = arrayList2.size();
                        while (i < size) {
                            obj = arrayList2.get(i);
                            if (obj instanceof Bundle) {
                                arrayList.add(zzt((Bundle) obj, false));
                            }
                        }
                    } else if (obj2 instanceof Bundle) {
                        arrayList.add(zzt((Bundle) obj2, false));
                    }
                    map.put(str, arrayList);
                }
            } else if (obj2 != null) {
                map.put(str, obj2);
            }
        }
        return map;
    }

    public final void zzu(com.google.android.gms.internal.measurement.zzfr zzfrVar, Object obj) {
        Preconditions.checkNotNull(obj);
        zzfrVar.zzg();
        zzfrVar.zze();
        zzfrVar.zzd();
        zzfrVar.zzf();
        if (obj instanceof String) {
            zzfrVar.zzk((String) obj);
            return;
        }
        if (obj instanceof Long) {
            zzfrVar.zzi(((Long) obj).longValue());
            return;
        }
        if (obj instanceof Double) {
            zzfrVar.zzh(((Double) obj).doubleValue());
        } else if (obj instanceof Bundle[]) {
            zzfrVar.zzb(zzq((Bundle[]) obj));
        } else {
            a.a(this.zzs, "Ignoring invalid (type) event param value", obj);
        }
    }

    public final void zzv(com.google.android.gms.internal.measurement.zzgg zzggVar, Object obj) {
        Preconditions.checkNotNull(obj);
        zzggVar.zzc();
        zzggVar.zzb();
        zzggVar.zza();
        if (obj instanceof String) {
            zzggVar.zzh((String) obj);
            return;
        }
        if (obj instanceof Long) {
            zzggVar.zze(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzggVar.zzd(((Double) obj).doubleValue());
        } else {
            a.a(this.zzs, "Ignoring invalid (type) user attribute value", obj);
        }
    }

    public final boolean zzx(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(this.zzs.zzav().currentTimeMillis() - j) > j2;
    }

    public final byte[] zzz(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            a.a(this.zzs, "Failed to gzip content", e2);
            throw e2;
        }
    }
}
