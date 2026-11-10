package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzhu<T> {
    public static final /* synthetic */ int zzc = 0;
    public static final Object zzd = new Object();
    public static volatile zzhs zze = null;
    public static volatile boolean zzf = false;
    public static final AtomicReference<Collection<zzhu<?>>> zzg = new AtomicReference<>();
    public static final zzhw zzh = new zzhw(zzhl.zza, null);
    public static final AtomicInteger zzi = new AtomicInteger();
    public final zzhr zza;
    public final String zzb;
    public final T zzj;
    public volatile int zzk = -1;
    public volatile T zzl;
    public final boolean zzm;

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ zzhu(zzhr zzhrVar, String str, Object obj, boolean z, zzht zzhtVar) {
        if (zzhrVar.zzb == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zza = zzhrVar;
        this.zzb = str;
        this.zzj = obj;
        this.zzm = true;
    }

    @Deprecated
    public static void zzd(final Context context) {
        boolean z = zzf;
        synchronized (zzd) {
            zzhs zzhsVar = zze;
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zzhsVar == null || zzhsVar.zza() != context) {
                zzha.zze();
                zzhv.zzc();
                zzhh.zze();
                zze = new zzgx(context, zzif.zza(new zzib() { // from class: com.google.android.gms.internal.measurement.zzhm
                    @Override // com.google.android.gms.internal.measurement.zzib
                    public final Object zza() {
                        zzhz zziaVar;
                        Object zziaVar2;
                        Context contextCreateDeviceProtectedStorageContext = context;
                        int i = zzhu.zzc;
                        String str = Build.TYPE;
                        String str2 = Build.TAGS;
                        if ((!str.equals(MtkTvConfigTypeBase.S639_CFG_LANG_ENG) && !str.equals("userdebug")) || (!str2.contains("dev-keys") && !str2.contains("test-keys"))) {
                            return zzhx.zza;
                        }
                        zzgw.zza();
                        if (!contextCreateDeviceProtectedStorageContext.isDeviceProtectedStorage()) {
                            contextCreateDeviceProtectedStorageContext = contextCreateDeviceProtectedStorageContext.createDeviceProtectedStorageContext();
                        }
                        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                        try {
                            StrictMode.allowThreadDiskWrites();
                            try {
                                File file = new File(contextCreateDeviceProtectedStorageContext.getDir("phenotype_hermetic", 0), "overrides.txt");
                                zziaVar = file.exists() ? new zzia(file) : zzhx.zza;
                            } catch (RuntimeException e2) {
                                Log.e("HermeticFileOverrides", "no data dir", e2);
                                zziaVar = zzhx.zza;
                            }
                            if (zziaVar.zzb()) {
                                File file2 = (File) zziaVar.zza();
                                try {
                                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
                                    try {
                                        HashMap map = new HashMap();
                                        HashMap map2 = new HashMap();
                                        while (true) {
                                            String line = bufferedReader.readLine();
                                            if (line == null) {
                                                break;
                                            }
                                            String[] strArrSplit = line.split(" ", 3);
                                            if (strArrSplit.length != 3) {
                                                Log.e("HermeticFileOverrides", line.length() != 0 ? "Invalid: ".concat(line) : new String("Invalid: "));
                                            } else {
                                                String str3 = new String(strArrSplit[0]);
                                                String strDecode = Uri.decode(new String(strArrSplit[1]));
                                                String strDecode2 = (String) map2.get(strArrSplit[2]);
                                                if (strDecode2 == null) {
                                                    String str4 = new String(strArrSplit[2]);
                                                    strDecode2 = Uri.decode(str4);
                                                    if (strDecode2.length() < 1024 || strDecode2 == str4) {
                                                        map2.put(str4, strDecode2);
                                                    }
                                                }
                                                if (!map.containsKey(str3)) {
                                                    map.put(str3, new HashMap());
                                                }
                                                ((Map) map.get(str3)).put(strDecode, strDecode2);
                                            }
                                        }
                                        String string = file2.toString();
                                        StringBuilder sb = new StringBuilder(string.length() + 7);
                                        sb.append("Parsed ");
                                        sb.append(string);
                                        Log.i("HermeticFileOverrides", sb.toString());
                                        zzhi zzhiVar = new zzhi(map);
                                        bufferedReader.close();
                                        zziaVar2 = new zzia(zzhiVar);
                                    } catch (Throwable th) {
                                        try {
                                            bufferedReader.close();
                                        } catch (Throwable unused) {
                                        }
                                        throw th;
                                    }
                                } catch (IOException e3) {
                                    throw new RuntimeException(e3);
                                }
                            } else {
                                zziaVar2 = zzhx.zza;
                            }
                            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                            return zziaVar2;
                        } catch (Throwable th2) {
                            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                            throw th2;
                        }
                    }
                }));
                zzi.incrementAndGet();
            }
        }
    }

    public static void zze() {
        zzi.incrementAndGet();
    }

    public abstract T zza(Object obj);

    /* JADX WARN: Code duplicated, block: B:39:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:40:0x00b3 A[Catch: all -> 0x0125, TryCatch #0 {, blocks: (B:12:0x001c, B:14:0x0020, B:16:0x0028, B:18:0x003f, B:20:0x004b, B:22:0x0054, B:24:0x0066, B:26:0x0071, B:25:0x006b, B:52:0x00df, B:54:0x00ef, B:56:0x0105, B:57:0x0108, B:58:0x010c, B:40:0x00b3, B:42:0x00b9, B:46:0x00cf, B:48:0x00d5, B:51:0x00dd, B:45:0x00cb, B:28:0x0076, B:30:0x007c, B:32:0x008a, B:35:0x00a2, B:37:0x00ac, B:59:0x0111, B:61:0x011d, B:62:0x0122, B:63:0x0123), top: B:70:0x001c }] */
    /* JADX WARN: Code duplicated, block: B:42:0x00b9 A[Catch: all -> 0x0125, TryCatch #0 {, blocks: (B:12:0x001c, B:14:0x0020, B:16:0x0028, B:18:0x003f, B:20:0x004b, B:22:0x0054, B:24:0x0066, B:26:0x0071, B:25:0x006b, B:52:0x00df, B:54:0x00ef, B:56:0x0105, B:57:0x0108, B:58:0x010c, B:40:0x00b3, B:42:0x00b9, B:46:0x00cf, B:48:0x00d5, B:51:0x00dd, B:45:0x00cb, B:28:0x0076, B:30:0x007c, B:32:0x008a, B:35:0x00a2, B:37:0x00ac, B:59:0x0111, B:61:0x011d, B:62:0x0122, B:63:0x0123), top: B:70:0x001c }] */
    /* JADX WARN: Code duplicated, block: B:44:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:45:0x00cb A[Catch: all -> 0x0125, TryCatch #0 {, blocks: (B:12:0x001c, B:14:0x0020, B:16:0x0028, B:18:0x003f, B:20:0x004b, B:22:0x0054, B:24:0x0066, B:26:0x0071, B:25:0x006b, B:52:0x00df, B:54:0x00ef, B:56:0x0105, B:57:0x0108, B:58:0x010c, B:40:0x00b3, B:42:0x00b9, B:46:0x00cf, B:48:0x00d5, B:51:0x00dd, B:45:0x00cb, B:28:0x0076, B:30:0x007c, B:32:0x008a, B:35:0x00a2, B:37:0x00ac, B:59:0x0111, B:61:0x011d, B:62:0x0122, B:63:0x0123), top: B:70:0x001c }] */
    /* JADX WARN: Code duplicated, block: B:48:0x00d5 A[Catch: all -> 0x0125, TryCatch #0 {, blocks: (B:12:0x001c, B:14:0x0020, B:16:0x0028, B:18:0x003f, B:20:0x004b, B:22:0x0054, B:24:0x0066, B:26:0x0071, B:25:0x006b, B:52:0x00df, B:54:0x00ef, B:56:0x0105, B:57:0x0108, B:58:0x010c, B:40:0x00b3, B:42:0x00b9, B:46:0x00cf, B:48:0x00d5, B:51:0x00dd, B:45:0x00cb, B:28:0x0076, B:30:0x007c, B:32:0x008a, B:35:0x00a2, B:37:0x00ac, B:59:0x0111, B:61:0x011d, B:62:0x0122, B:63:0x0123), top: B:70:0x001c }] */
    /* JADX WARN: Code duplicated, block: B:49:0x00da  */
    /* JADX WARN: Code duplicated, block: B:51:0x00dd A[Catch: all -> 0x0125, TryCatch #0 {, blocks: (B:12:0x001c, B:14:0x0020, B:16:0x0028, B:18:0x003f, B:20:0x004b, B:22:0x0054, B:24:0x0066, B:26:0x0071, B:25:0x006b, B:52:0x00df, B:54:0x00ef, B:56:0x0105, B:57:0x0108, B:58:0x010c, B:40:0x00b3, B:42:0x00b9, B:46:0x00cf, B:48:0x00d5, B:51:0x00dd, B:45:0x00cb, B:28:0x0076, B:30:0x007c, B:32:0x008a, B:35:0x00a2, B:37:0x00ac, B:59:0x0111, B:61:0x011d, B:62:0x0122, B:63:0x0123), top: B:70:0x001c }] */
    /* JADX WARN: Code duplicated, block: B:54:0x00ef A[Catch: all -> 0x0125, TryCatch #0 {, blocks: (B:12:0x001c, B:14:0x0020, B:16:0x0028, B:18:0x003f, B:20:0x004b, B:22:0x0054, B:24:0x0066, B:26:0x0071, B:25:0x006b, B:52:0x00df, B:54:0x00ef, B:56:0x0105, B:57:0x0108, B:58:0x010c, B:40:0x00b3, B:42:0x00b9, B:46:0x00cf, B:48:0x00d5, B:51:0x00dd, B:45:0x00cb, B:28:0x0076, B:30:0x007c, B:32:0x008a, B:35:0x00a2, B:37:0x00ac, B:59:0x0111, B:61:0x011d, B:62:0x0122, B:63:0x0123), top: B:70:0x001c }] */
    /* JADX WARN: Code duplicated, block: B:56:0x0105 A[Catch: all -> 0x0125, TryCatch #0 {, blocks: (B:12:0x001c, B:14:0x0020, B:16:0x0028, B:18:0x003f, B:20:0x004b, B:22:0x0054, B:24:0x0066, B:26:0x0071, B:25:0x006b, B:52:0x00df, B:54:0x00ef, B:56:0x0105, B:57:0x0108, B:58:0x010c, B:40:0x00b3, B:42:0x00b9, B:46:0x00cf, B:48:0x00d5, B:51:0x00dd, B:45:0x00cb, B:28:0x0076, B:30:0x007c, B:32:0x008a, B:35:0x00a2, B:37:0x00ac, B:59:0x0111, B:61:0x011d, B:62:0x0122, B:63:0x0123), top: B:70:0x001c }] */
    /* JADX WARN: Code duplicated, block: B:57:0x0108 A[Catch: all -> 0x0125, TryCatch #0 {, blocks: (B:12:0x001c, B:14:0x0020, B:16:0x0028, B:18:0x003f, B:20:0x004b, B:22:0x0054, B:24:0x0066, B:26:0x0071, B:25:0x006b, B:52:0x00df, B:54:0x00ef, B:56:0x0105, B:57:0x0108, B:58:0x010c, B:40:0x00b3, B:42:0x00b9, B:46:0x00cf, B:48:0x00d5, B:51:0x00dd, B:45:0x00cb, B:28:0x0076, B:30:0x007c, B:32:0x008a, B:35:0x00a2, B:37:0x00ac, B:59:0x0111, B:61:0x011d, B:62:0x0122, B:63:0x0123), top: B:70:0x001c }] */
    public final T zzb() {
        zzha zzhaVarZza;
        Object objZzb;
        T tZza;
        zzhr zzhrVar;
        zzhr zzhrVar2;
        String str;
        String strZzb;
        zzhz<zzhi> zzhzVarZza;
        String strZza;
        if (!this.zzm && this.zzb == null) {
            throw new NullPointerException("flagName must not be null");
        }
        int i = zzi.get();
        if (this.zzk < i) {
            synchronized (this) {
                if (this.zzk < i) {
                    zzhs zzhsVar = zze;
                    boolean z = zzf;
                    if (zzhsVar == null) {
                        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
                    }
                    zzhr zzhrVar3 = this.zza;
                    boolean z2 = zzhrVar3.zzf;
                    boolean z3 = zzhrVar3.zzg;
                    String strZzb2 = zzhh.zza(zzhsVar.zza()).zzb("gms:phenotype:phenotype_flag:debug_bypass_phenotype");
                    if (strZzb2 == null || !zzgv.zzc.matcher(strZzb2).matches()) {
                        if (this.zza.zzb == null) {
                            Context contextZza = zzhsVar.zza();
                            String str2 = this.zza.zza;
                            zzhv.zza(contextZza, null);
                            throw null;
                        }
                        if (zzhj.zza(zzhsVar.zza(), this.zza.zzb)) {
                            boolean z4 = this.zza.zzh;
                            zzhaVarZza = zzha.zza(zzhsVar.zza().getContentResolver(), this.zza.zzb);
                        } else {
                            zzhaVarZza = null;
                        }
                        if (zzhaVarZza != null && (objZzb = zzhaVarZza.zzb(zzc())) != null) {
                            tZza = zza(objZzb);
                        }
                        if (tZza != null) {
                            zzhrVar = this.zza;
                            if (zzhrVar.zze) {
                                tZza = null;
                            } else {
                                zzhy<Context, Boolean> zzhyVar = zzhrVar.zzi;
                                zzhh zzhhVarZza = zzhh.zza(zzhsVar.zza());
                                zzhrVar2 = this.zza;
                                if (zzhrVar2.zze) {
                                    str = null;
                                } else {
                                    String str3 = zzhrVar2.zzc;
                                    str = this.zzb;
                                }
                                strZzb = zzhhVarZza.zzb(str);
                                if (strZzb != null) {
                                    tZza = zza(strZzb);
                                } else {
                                    tZza = null;
                                }
                            }
                            if (tZza == null) {
                                tZza = this.zzj;
                            }
                        }
                        zzhzVarZza = zzhsVar.zzb().zza();
                        if (zzhzVarZza.zzb()) {
                            zzhi zzhiVarZza = zzhzVarZza.zza();
                            zzhr zzhrVar4 = this.zza;
                            Uri uri = zzhrVar4.zzb;
                            String str4 = zzhrVar4.zza;
                            strZza = zzhiVarZza.zza(uri, null, zzhrVar4.zzd, this.zzb);
                            if (strZza == null) {
                                tZza = this.zzj;
                            } else {
                                tZza = zza(strZza);
                            }
                        }
                        this.zzl = tZza;
                        this.zzk = i;
                    } else if (Log.isLoggable("PhenotypeFlag", 3)) {
                        String strValueOf = String.valueOf(zzc());
                        Log.d("PhenotypeFlag", strValueOf.length() != 0 ? "Bypass reading Phenotype values for flag: ".concat(strValueOf) : new String("Bypass reading Phenotype values for flag: "));
                    }
                    tZza = null;
                    if (tZza != null) {
                        zzhrVar = this.zza;
                        if (zzhrVar.zze) {
                            zzhy<Context, Boolean> zzhyVar2 = zzhrVar.zzi;
                            zzhh zzhhVarZza2 = zzhh.zza(zzhsVar.zza());
                            zzhrVar2 = this.zza;
                            if (zzhrVar2.zze) {
                                str = null;
                            } else {
                                String str5 = zzhrVar2.zzc;
                                str = this.zzb;
                            }
                            strZzb = zzhhVarZza2.zzb(str);
                            if (strZzb != null) {
                                tZza = zza(strZzb);
                            } else {
                                tZza = null;
                            }
                        } else {
                            tZza = null;
                        }
                        if (tZza == null) {
                            tZza = this.zzj;
                        }
                    }
                    zzhzVarZza = zzhsVar.zzb().zza();
                    if (zzhzVarZza.zzb()) {
                        zzhi zzhiVarZza2 = zzhzVarZza.zza();
                        zzhr zzhrVar5 = this.zza;
                        Uri uri2 = zzhrVar5.zzb;
                        String str6 = zzhrVar5.zza;
                        strZza = zzhiVarZza2.zza(uri2, null, zzhrVar5.zzd, this.zzb);
                        if (strZza == null) {
                            tZza = this.zzj;
                        } else {
                            tZza = zza(strZza);
                        }
                    }
                    this.zzl = tZza;
                    this.zzk = i;
                }
            }
        }
        return this.zzl;
    }

    public final String zzc() {
        String str = this.zza.zzd;
        return this.zzb;
    }
}
