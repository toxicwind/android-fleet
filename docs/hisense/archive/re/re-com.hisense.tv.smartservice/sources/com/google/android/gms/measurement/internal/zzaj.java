package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzom;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.storage.StorageMetadata;
import com.hmct.cloud.sdk.utils.Constants;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzaj extends zzki {
    public static final String[] zza = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    public static final String[] zzb = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    public static final String[] zzc = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;"};
    public static final String[] zzd = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    public static final String[] zze = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    public static final String[] zzg = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    public static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    public static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    public final zzai zzj;
    public final zzke zzk;

    public zzaj(zzks zzksVar) {
        super(zzksVar);
        this.zzk = new zzke(this.zzs.zzav());
        this.zzs.zzf();
        this.zzj = new zzai(this, this.zzs.zzau(), "google_app_measurement.db");
    }

    @WorkerThread
    public static final void zzX(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty("value");
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put("value", (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put("value", (Long) obj);
        } else {
            if (!(obj instanceof Double)) {
                throw new IllegalArgumentException("Invalid value type");
            }
            contentValues.put("value", (Double) obj);
        }
    }

    @WorkerThread
    private final long zzab(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                Cursor cursorRawQuery = zzh().rawQuery(str, strArr);
                if (!cursorRawQuery.moveToFirst()) {
                    throw new SQLiteException("Database returned empty set");
                }
                long j = cursorRawQuery.getLong(0);
                cursorRawQuery.close();
                return j;
            } catch (SQLiteException e2) {
                this.zzs.zzay().zzd().zzc("Database error", str, e2);
                throw e2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    private final long zzac(String str, String[] strArr, long j) {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = zzh().rawQuery(str, strArr);
                if (!cursorRawQuery.moveToFirst()) {
                    cursorRawQuery.close();
                    return j;
                }
                long j2 = cursorRawQuery.getLong(0);
                cursorRawQuery.close();
                return j2;
            } catch (SQLiteException e2) {
                this.zzs.zzay().zzd().zzc("Database error", str, e2);
                throw e2;
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final void zzA() {
        zzg();
        zzY();
        if (zzK()) {
            long jZza = this.zzf.zzs().zza.zza();
            long jElapsedRealtime = this.zzs.zzav().elapsedRealtime();
            long jAbs = Math.abs(jElapsedRealtime - jZza);
            this.zzs.zzf();
            if (jAbs > zzdy.zzx.zza(null).longValue()) {
                this.zzf.zzs().zza.zzb(jElapsedRealtime);
                zzg();
                zzY();
                if (zzK()) {
                    SQLiteDatabase sQLiteDatabaseZzh = zzh();
                    this.zzs.zzf();
                    int iDelete = sQLiteDatabaseZzh.delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(this.zzs.zzav().currentTimeMillis()), String.valueOf(zzaf.zzA())});
                    if (iDelete > 0) {
                        this.zzs.zzay().zzj().zzb("Deleted stale rows. rowsDeleted", Integer.valueOf(iDelete));
                    }
                }
            }
        }
    }

    @WorkerThread
    public final void zzB(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzY();
        try {
            zzh().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e2) {
            this.zzs.zzay().zzd().zzd("Error deleting user property. appId", zzel.zzn(str), this.zzs.zzj().zzf(str2), e2);
        }
    }

    @WorkerThread
    public final void zzC(String str, List<com.google.android.gms.internal.measurement.zzeh> list) {
        Iterator<com.google.android.gms.internal.measurement.zzeh> it;
        String str2;
        com.google.android.gms.internal.measurement.zzeg zzegVar;
        boolean z;
        String str3 = "app_id=? and audience_id=?";
        Preconditions.checkNotNull(list);
        int i = 0;
        while (i < list.size()) {
            com.google.android.gms.internal.measurement.zzeg zzegVarZzbv = list.get(i).zzbv();
            if (zzegVarZzbv.zza() != 0) {
                zzegVar = zzegVarZzbv;
                int i2 = 0;
                while (i2 < zzegVar.zza()) {
                    com.google.android.gms.internal.measurement.zzei zzeiVarZzbv = zzegVar.zze(i2).zzbv();
                    com.google.android.gms.internal.measurement.zzei zzeiVarClone = zzeiVarZzbv.clone();
                    String strZzb = zzgs.zzb(zzeiVarZzbv.zze());
                    if (strZzb != null) {
                        zzeiVarClone.zzb(strZzb);
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z2 = z;
                    int i3 = 0;
                    while (i3 < zzeiVarZzbv.zza()) {
                        com.google.android.gms.internal.measurement.zzel zzelVarZzd = zzeiVarZzbv.zzd(i3);
                        com.google.android.gms.internal.measurement.zzei zzeiVar = zzeiVarZzbv;
                        String str4 = str3;
                        String strZzb2 = zzig.zzb(zzelVarZzd.zze(), zzgt.zza, zzgt.zzb);
                        if (strZzb2 != null) {
                            com.google.android.gms.internal.measurement.zzek zzekVarZzbv = zzelVarZzd.zzbv();
                            zzekVarZzbv.zza(strZzb2);
                            zzeiVarClone.zzc(i3, zzekVarZzbv.zzaA());
                            z2 = true;
                        }
                        i3++;
                        zzeiVarZzbv = zzeiVar;
                        str3 = str4;
                    }
                    String str5 = str3;
                    if (z2) {
                        zzegVar.zzc(i2, zzeiVarClone);
                        list.set(i, zzegVarZzbv.zzaA());
                        zzegVar = zzegVarZzbv;
                    }
                    i2++;
                    str3 = str5;
                }
                str2 = str3;
            } else {
                str2 = str3;
                zzegVar = zzegVarZzbv;
            }
            if (zzegVar.zzb() != 0) {
                for (int i4 = 0; i4 < zzegVar.zzb(); i4++) {
                    com.google.android.gms.internal.measurement.zzes zzesVarZzf = zzegVar.zzf(i4);
                    String strZzb3 = zzig.zzb(zzesVarZzf.zze(), zzgu.zza, zzgu.zzb);
                    if (strZzb3 != null) {
                        com.google.android.gms.internal.measurement.zzer zzerVarZzbv = zzesVarZzf.zzbv();
                        zzerVarZzbv.zza(strZzb3);
                        zzegVar.zzd(i4, zzerVarZzbv);
                        list.set(i, zzegVarZzbv.zzaA());
                        zzegVar = zzegVarZzbv;
                    }
                }
            }
            i++;
            str3 = str2;
        }
        String str6 = str3;
        zzY();
        zzg();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        SQLiteDatabase sQLiteDatabaseZzh = zzh();
        sQLiteDatabaseZzh.beginTransaction();
        try {
            zzY();
            zzg();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase sQLiteDatabaseZzh2 = zzh();
            sQLiteDatabaseZzh2.delete("property_filters", "app_id=?", new String[]{str});
            sQLiteDatabaseZzh2.delete("event_filters", "app_id=?", new String[]{str});
            Iterator<com.google.android.gms.internal.measurement.zzeh> it2 = list.iterator();
            while (it2.hasNext()) {
                com.google.android.gms.internal.measurement.zzeh next = it2.next();
                zzY();
                zzg();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(next);
                if (next.zzk()) {
                    int iZza = next.zza();
                    Iterator<com.google.android.gms.internal.measurement.zzej> it3 = next.zzg().iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (!it3.next().zzp()) {
                                this.zzs.zzay().zzk().zzc("Event filter with no ID. Audience definition ignored. appId, audienceId", zzel.zzn(str), Integer.valueOf(iZza));
                                break;
                            }
                        } else {
                            Iterator<com.google.android.gms.internal.measurement.zzes> it4 = next.zzh().iterator();
                            while (true) {
                                if (!it4.hasNext()) {
                                    Iterator<com.google.android.gms.internal.measurement.zzej> it5 = next.zzg().iterator();
                                    while (true) {
                                        if (!it5.hasNext()) {
                                            it = it2;
                                            Iterator<com.google.android.gms.internal.measurement.zzes> it6 = next.zzh().iterator();
                                            while (true) {
                                                if (!it6.hasNext()) {
                                                    it2 = it;
                                                    break;
                                                }
                                                com.google.android.gms.internal.measurement.zzes next2 = it6.next();
                                                zzY();
                                                zzg();
                                                Preconditions.checkNotEmpty(str);
                                                Preconditions.checkNotNull(next2);
                                                if (TextUtils.isEmpty(next2.zze())) {
                                                    this.zzs.zzay().zzk().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzel.zzn(str), Integer.valueOf(iZza), String.valueOf(next2.zzj() ? Integer.valueOf(next2.zza()) : null));
                                                } else {
                                                    byte[] bArrZzbs = next2.zzbs();
                                                    ContentValues contentValues = new ContentValues();
                                                    contentValues.put("app_id", str);
                                                    contentValues.put("audience_id", Integer.valueOf(iZza));
                                                    contentValues.put("filter_id", next2.zzj() ? Integer.valueOf(next2.zza()) : null);
                                                    Iterator<com.google.android.gms.internal.measurement.zzes> it7 = it6;
                                                    contentValues.put("property_name", next2.zze());
                                                    contentValues.put("session_scoped", next2.zzk() ? Boolean.valueOf(next2.zzi()) : null);
                                                    contentValues.put("data", bArrZzbs);
                                                    try {
                                                        if (zzh().insertWithOnConflict("property_filters", null, contentValues, 5) == -1) {
                                                            this.zzs.zzay().zzd().zzb("Failed to insert property filter (got -1). appId", zzel.zzn(str));
                                                        } else {
                                                            it6 = it7;
                                                        }
                                                    } catch (SQLiteException e2) {
                                                        this.zzs.zzay().zzd().zzc("Error storing property filter. appId", zzel.zzn(str), e2);
                                                    }
                                                }
                                            }
                                        } else {
                                            com.google.android.gms.internal.measurement.zzej next3 = it5.next();
                                            zzY();
                                            zzg();
                                            Preconditions.checkNotEmpty(str);
                                            Preconditions.checkNotNull(next3);
                                            if (!TextUtils.isEmpty(next3.zzg())) {
                                                byte[] bArrZzbs2 = next3.zzbs();
                                                it = it2;
                                                ContentValues contentValues2 = new ContentValues();
                                                contentValues2.put("app_id", str);
                                                contentValues2.put("audience_id", Integer.valueOf(iZza));
                                                contentValues2.put("filter_id", next3.zzp() ? Integer.valueOf(next3.zzb()) : null);
                                                contentValues2.put("event_name", next3.zzg());
                                                contentValues2.put("session_scoped", next3.zzq() ? Boolean.valueOf(next3.zzn()) : null);
                                                contentValues2.put("data", bArrZzbs2);
                                                try {
                                                    if (zzh().insertWithOnConflict("event_filters", null, contentValues2, 5) == -1) {
                                                        this.zzs.zzay().zzd().zzb("Failed to insert event filter (got -1). appId", zzel.zzn(str));
                                                    }
                                                    it2 = it;
                                                } catch (SQLiteException e3) {
                                                    this.zzs.zzay().zzd().zzc("Error storing event filter. appId", zzel.zzn(str), e3);
                                                    zzY();
                                                    zzg();
                                                    Preconditions.checkNotEmpty(str);
                                                    SQLiteDatabase sQLiteDatabaseZzh3 = zzh();
                                                    String str7 = str6;
                                                    sQLiteDatabaseZzh3.delete("property_filters", str7, new String[]{str, String.valueOf(iZza)});
                                                    sQLiteDatabaseZzh3.delete("event_filters", str7, new String[]{str, String.valueOf(iZza)});
                                                    str6 = str7;
                                                    it2 = it;
                                                    break;
                                                }
                                            } else {
                                                this.zzs.zzay().zzk().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzel.zzn(str), Integer.valueOf(iZza), String.valueOf(next3.zzp() ? Integer.valueOf(next3.zzb()) : null));
                                                it = it2;
                                            }
                                        }
                                        zzY();
                                        zzg();
                                        Preconditions.checkNotEmpty(str);
                                        SQLiteDatabase sQLiteDatabaseZzh4 = zzh();
                                        String str8 = str6;
                                        sQLiteDatabaseZzh4.delete("property_filters", str8, new String[]{str, String.valueOf(iZza)});
                                        sQLiteDatabaseZzh4.delete("event_filters", str8, new String[]{str, String.valueOf(iZza)});
                                        str6 = str8;
                                        it2 = it;
                                        break;
                                    }
                                }
                                if (!it4.next().zzj()) {
                                    this.zzs.zzay().zzk().zzc("Property filter with no ID. Audience definition ignored. appId, audienceId", zzel.zzn(str), Integer.valueOf(iZza));
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    this.zzs.zzay().zzk().zzb("Audience with no ID. appId", zzel.zzn(str));
                }
            }
            ArrayList arrayList = new ArrayList();
            for (com.google.android.gms.internal.measurement.zzeh zzehVar : list) {
                arrayList.add(zzehVar.zzk() ? Integer.valueOf(zzehVar.zza()) : null);
            }
            Preconditions.checkNotEmpty(str);
            zzY();
            zzg();
            SQLiteDatabase sQLiteDatabaseZzh5 = zzh();
            try {
                long jZzab = zzab("select count(1) from audience_filter_values where app_id=?", new String[]{str});
                int iMax = Math.max(0, Math.min(MtkTvScanDvbsBase.EXCHANGE_TYPE_SB_GET, this.zzs.zzf().zze(str, zzdy.zzE)));
                if (jZzab > iMax) {
                    ArrayList arrayList2 = new ArrayList();
                    int i5 = 0;
                    while (true) {
                        if (i5 >= arrayList.size()) {
                            String strJoin = TextUtils.join(",", arrayList2);
                            StringBuilder sb = new StringBuilder(String.valueOf(strJoin).length() + 2);
                            sb.append("(");
                            sb.append(strJoin);
                            sb.append(")");
                            String string = sb.toString();
                            StringBuilder sb2 = new StringBuilder(string.length() + 140);
                            sb2.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
                            sb2.append(string);
                            sb2.append(" order by rowid desc limit -1 offset ?)");
                            sQLiteDatabaseZzh5.delete("audience_filter_values", sb2.toString(), new String[]{str, Integer.toString(iMax)});
                            break;
                        }
                        Integer num = (Integer) arrayList.get(i5);
                        if (num == null) {
                            break;
                        }
                        arrayList2.add(Integer.toString(num.intValue()));
                        i5++;
                    }
                }
            } catch (SQLiteException e4) {
                this.zzs.zzay().zzd().zzc("Database error querying filters. appId", zzel.zzn(str), e4);
            }
            sQLiteDatabaseZzh.setTransactionSuccessful();
            sQLiteDatabaseZzh.endTransaction();
        } catch (Throwable th) {
            sQLiteDatabaseZzh.endTransaction();
            throw th;
        }
    }

    @WorkerThread
    public final void zzD() {
        zzY();
        zzh().setTransactionSuccessful();
    }

    @WorkerThread
    public final void zzE(zzg zzgVar) {
        Preconditions.checkNotNull(zzgVar);
        zzg();
        zzY();
        String strZzt = zzgVar.zzt();
        Preconditions.checkNotNull(strZzt);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", strZzt);
        contentValues.put("app_instance_id", zzgVar.zzu());
        contentValues.put("gmp_app_id", zzgVar.zzz());
        contentValues.put("resettable_device_id_hash", zzgVar.zzB());
        contentValues.put("last_bundle_index", Long.valueOf(zzgVar.zzo()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzgVar.zzp()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzgVar.zzn()));
        contentValues.put("app_version", zzgVar.zzw());
        contentValues.put("app_store", zzgVar.zzv());
        contentValues.put("gmp_version", Long.valueOf(zzgVar.zzm()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzgVar.zzj()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzgVar.zzaj()));
        contentValues.put("day", Long.valueOf(zzgVar.zzi()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzgVar.zzg()));
        contentValues.put("daily_events_count", Long.valueOf(zzgVar.zzf()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzgVar.zzd()));
        contentValues.put("config_fetched_time", Long.valueOf(zzgVar.zzc()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzgVar.zzl()));
        contentValues.put("app_version_int", Long.valueOf(zzgVar.zzb()));
        contentValues.put("firebase_instance_id", zzgVar.zzx());
        contentValues.put("daily_error_events_count", Long.valueOf(zzgVar.zze()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzgVar.zzh()));
        contentValues.put("health_monitor_sample", zzgVar.zzA());
        contentValues.put("android_id", Long.valueOf(zzgVar.zza()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzgVar.zzai()));
        contentValues.put("admob_app_id", zzgVar.zzr());
        contentValues.put("dynamite_version", Long.valueOf(zzgVar.zzk()));
        List<String> listZzC = zzgVar.zzC();
        if (listZzC != null) {
            if (listZzC.size() == 0) {
                this.zzs.zzay().zzk().zzb("Safelisted events should not be an empty list. appId", strZzt);
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", listZzC));
            }
        }
        zzom.zzc();
        if (this.zzs.zzf().zzs(strZzt, zzdy.zzac)) {
            contentValues.put("ga_app_id", zzgVar.zzy());
        }
        try {
            SQLiteDatabase sQLiteDatabaseZzh = zzh();
            if (sQLiteDatabaseZzh.update("apps", contentValues, "app_id = ?", new String[]{strZzt}) == 0 && sQLiteDatabaseZzh.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                this.zzs.zzay().zzd().zzb("Failed to insert/update app (got -1). appId", zzel.zzn(strZzt));
            }
        } catch (SQLiteException e2) {
            this.zzs.zzay().zzd().zzc("Error storing app. appId", zzel.zzn(strZzt), e2);
        }
    }

    @WorkerThread
    public final void zzF(zzap zzapVar) {
        Preconditions.checkNotNull(zzapVar);
        zzg();
        zzY();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzapVar.zza);
        contentValues.put("name", zzapVar.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzapVar.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzapVar.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzapVar.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzapVar.zzg));
        contentValues.put("last_bundled_day", zzapVar.zzh);
        contentValues.put("last_sampled_complex_event_id", zzapVar.zzi);
        contentValues.put("last_sampling_rate", zzapVar.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzapVar.zze));
        Boolean bool = zzapVar.zzk;
        contentValues.put("last_exempt_from_sampling", (bool == null || !bool.booleanValue()) ? null : 1L);
        try {
            if (zzh().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                this.zzs.zzay().zzd().zzb("Failed to insert/update event aggregates (got -1). appId", zzel.zzn(zzapVar.zza));
            }
        } catch (SQLiteException e2) {
            this.zzs.zzay().zzd().zzc("Error storing event aggregates. appId", zzel.zzn(zzapVar.zza), e2);
        }
    }

    @WorkerThread
    public final void zzG(String str, byte[] bArr, String str2) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzY();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        contentValues.put("config_last_modified_time", str2);
        try {
            if (zzh().update("apps", contentValues, "app_id = ?", new String[]{str}) == 0) {
                this.zzs.zzay().zzd().zzb("Failed to update remote config (got 0). appId", zzel.zzn(str));
            }
        } catch (SQLiteException e2) {
            this.zzs.zzay().zzd().zzc("Error storing remote config. appId", zzel.zzn(str), e2);
        }
    }

    public final boolean zzH() {
        return zzab("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean zzI() {
        return zzab("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }

    public final boolean zzJ() {
        return zzab("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    @VisibleForTesting
    public final boolean zzK() {
        Context contextZzau = this.zzs.zzau();
        this.zzs.zzf();
        return contextZzau.getDatabasePath("google_app_measurement.db").exists();
    }

    public final boolean zzL(String str, Long l, long j, com.google.android.gms.internal.measurement.zzfo zzfoVar) {
        zzg();
        zzY();
        Preconditions.checkNotNull(zzfoVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] bArrZzbs = zzfoVar.zzbs();
        this.zzs.zzay().zzj().zzc("Saving complex main event, appId, data size", this.zzs.zzj().zzd(str), Integer.valueOf(bArrZzbs.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", bArrZzbs);
        try {
            if (zzh().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            this.zzs.zzay().zzd().zzb("Failed to insert complex main event (got -1). appId", zzel.zzn(str));
            return false;
        } catch (SQLiteException e2) {
            this.zzs.zzay().zzd().zzc("Error storing complex main event. appId", zzel.zzn(str), e2);
            return false;
        }
    }

    @WorkerThread
    public final boolean zzM(zzab zzabVar) {
        Preconditions.checkNotNull(zzabVar);
        zzg();
        zzY();
        String str = zzabVar.zza;
        Preconditions.checkNotNull(str);
        if (zzp(str, zzabVar.zzc.zzb) == null) {
            long jZzab = zzab("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str});
            this.zzs.zzf();
            if (jZzab >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzabVar.zzb);
        contentValues.put("name", zzabVar.zzc.zzb);
        Object objZza = zzabVar.zzc.zza();
        Preconditions.checkNotNull(objZza);
        zzX(contentValues, "value", objZza);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzabVar.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzabVar.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzabVar.zzh));
        contentValues.put("timed_out_event", this.zzs.zzv().zzan(zzabVar.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzabVar.zzd));
        contentValues.put("triggered_event", this.zzs.zzv().zzan(zzabVar.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzabVar.zzc.zzc));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzabVar.zzj));
        contentValues.put("expired_event", this.zzs.zzv().zzan(zzabVar.zzk));
        try {
            if (zzh().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                this.zzs.zzay().zzd().zzb("Failed to insert/update conditional user property (got -1)", zzel.zzn(str));
            }
        } catch (SQLiteException e2) {
            this.zzs.zzay().zzd().zzc("Error storing conditional user property", zzel.zzn(str), e2);
        }
        return true;
    }

    @WorkerThread
    public final boolean zzN(zzkx zzkxVar) {
        Preconditions.checkNotNull(zzkxVar);
        zzg();
        zzY();
        if (zzp(zzkxVar.zza, zzkxVar.zzc) == null) {
            if (zzkz.zzah(zzkxVar.zzc)) {
                if (zzab("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzkxVar.zza}) >= this.zzs.zzf().zzf(zzkxVar.zza, zzdy.zzF, 25, 100)) {
                    return false;
                }
            } else if (!"_npa".equals(zzkxVar.zzc)) {
                long jZzab = zzab("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzkxVar.zza, zzkxVar.zzb});
                this.zzs.zzf();
                if (jZzab >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzkxVar.zza);
        contentValues.put("origin", zzkxVar.zzb);
        contentValues.put("name", zzkxVar.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzkxVar.zzd));
        zzX(contentValues, "value", zzkxVar.zze);
        try {
            if (zzh().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                this.zzs.zzay().zzd().zzb("Failed to insert/update user property (got -1). appId", zzel.zzn(zzkxVar.zza));
            }
        } catch (SQLiteException e2) {
            this.zzs.zzay().zzd().zzc("Error storing user property. appId", zzel.zzn(zzkxVar.zza), e2);
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x022a  */
    /* JADX WARN: Code duplicated, block: B:94:0x0230  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v4, types: [boolean] */
    public final void zzW(String str, long j, long j2, zzkr zzkrVar) throws Throwable {
        Cursor cursor;
        ?? IsEmpty;
        String str2;
        Cursor cursorRawQuery;
        String string;
        int i;
        String str3;
        String[] strArr;
        Preconditions.checkNotNull(zzkrVar);
        zzg();
        zzY();
        String string2 = null;
        try {
            try {
                SQLiteDatabase sQLiteDatabaseZzh = zzh();
                IsEmpty = TextUtils.isEmpty(null);
                try {
                    if (IsEmpty != 0) {
                        String[] strArr2 = j2 != -1 ? new String[]{String.valueOf(j2), String.valueOf(j)} : new String[]{String.valueOf(j)};
                        str2 = j2 != -1 ? "rowid <= ? and " : "";
                        StringBuilder sb = new StringBuilder(str2.length() + MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_SOURCE_READY);
                        sb.append("select app_id, metadata_fingerprint from raw_events where ");
                        sb.append(str2);
                        sb.append("app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;");
                        cursorRawQuery = sQLiteDatabaseZzh.rawQuery(sb.toString(), strArr2);
                        if (!cursorRawQuery.moveToFirst()) {
                            cursorRawQuery.close();
                            return;
                        } else {
                            string2 = cursorRawQuery.getString(0);
                            string = cursorRawQuery.getString(1);
                            cursorRawQuery.close();
                        }
                    } else {
                        String[] strArr3 = j2 != -1 ? new String[]{null, String.valueOf(j2)} : new String[]{null};
                        str2 = j2 != -1 ? " and rowid <= ?" : "";
                        StringBuilder sb2 = new StringBuilder(str2.length() + 84);
                        sb2.append("select metadata_fingerprint from raw_events where app_id = ?");
                        sb2.append(str2);
                        sb2.append(" order by rowid limit 1;");
                        cursorRawQuery = sQLiteDatabaseZzh.rawQuery(sb2.toString(), strArr3);
                        if (!cursorRawQuery.moveToFirst()) {
                            cursorRawQuery.close();
                            return;
                        } else {
                            string = cursorRawQuery.getString(0);
                            cursorRawQuery.close();
                        }
                    }
                    Cursor cursor2 = cursorRawQuery;
                    String str4 = string;
                    try {
                        Cursor cursorQuery = sQLiteDatabaseZzh.query("raw_events_metadata", new String[]{StorageMetadata.CUSTOM_METADATA_KEY}, "app_id = ? and metadata_fingerprint = ?", new String[]{string2, str4}, null, null, "rowid", Constants.LANGUAGE_FRENCH);
                        try {
                            if (!cursorQuery.moveToFirst()) {
                                this.zzs.zzay().zzd().zzb("Raw event metadata record is missing. appId", zzel.zzn(string2));
                                cursorQuery.close();
                                return;
                            }
                            try {
                                com.google.android.gms.internal.measurement.zzfy zzfyVarZzaA = ((com.google.android.gms.internal.measurement.zzfx) zzku.zzl(com.google.android.gms.internal.measurement.zzfy.zzu(), cursorQuery.getBlob(0))).zzaA();
                                if (cursorQuery.moveToNext()) {
                                    this.zzs.zzay().zzk().zzb("Get multiple raw event metadata records, expected one. appId", zzel.zzn(string2));
                                }
                                cursorQuery.close();
                                Preconditions.checkNotNull(zzfyVarZzaA);
                                zzkrVar.zza = zzfyVarZzaA;
                                if (j2 != -1) {
                                    i = 1;
                                    str3 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
                                    strArr = new String[]{string2, str4, String.valueOf(j2)};
                                } else {
                                    i = 1;
                                    str3 = "app_id = ? and metadata_fingerprint = ?";
                                    strArr = new String[]{string2, str4};
                                }
                                Cursor cursorQuery2 = sQLiteDatabaseZzh.query("raw_events", new String[]{"rowid", "name", NotificationCompat.CarExtender.KEY_TIMESTAMP, "data"}, str3, strArr, null, null, "rowid", null);
                                if (!cursorQuery2.moveToFirst()) {
                                    this.zzs.zzay().zzk().zzb("Raw event data disappeared while in transaction. appId", zzel.zzn(string2));
                                    cursorQuery2.close();
                                    return;
                                }
                                do {
                                    long j3 = cursorQuery2.getLong(0);
                                    try {
                                        com.google.android.gms.internal.measurement.zzfn zzfnVar = (com.google.android.gms.internal.measurement.zzfn) zzku.zzl(com.google.android.gms.internal.measurement.zzfo.zze(), cursorQuery2.getBlob(3));
                                        zzfnVar.zzi(cursorQuery2.getString(i));
                                        zzfnVar.zzm(cursorQuery2.getLong(2));
                                        if (!zzkrVar.zza(j3, zzfnVar.zzaA())) {
                                            cursorQuery2.close();
                                            return;
                                        }
                                    } catch (IOException e2) {
                                        this.zzs.zzay().zzd().zzc("Data loss. Failed to merge raw event. appId", zzel.zzn(string2), e2);
                                    }
                                } while (cursorQuery2.moveToNext());
                                cursorQuery2.close();
                            } catch (IOException e3) {
                                this.zzs.zzay().zzd().zzc("Data loss. Failed to merge raw event metadata. appId", zzel.zzn(string2), e3);
                                cursorQuery.close();
                            }
                        } catch (SQLiteException e4) {
                            e = e4;
                            cursor2 = cursorQuery;
                            IsEmpty = cursor2;
                            this.zzs.zzay().zzd().zzc("Data loss. Error selecting raw event. appId", zzel.zzn(string2), e);
                            if (IsEmpty != 0) {
                                IsEmpty.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor2 = cursorQuery;
                            cursor = cursor2;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    } catch (SQLiteException e5) {
                        e = e5;
                        IsEmpty = cursor2;
                        this.zzs.zzay().zzd().zzc("Data loss. Error selecting raw event. appId", zzel.zzn(string2), e);
                        if (IsEmpty != 0) {
                            IsEmpty.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = cursor2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (SQLiteException e6) {
                    e = e6;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (SQLiteException e7) {
            e = e7;
            IsEmpty = 0;
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
        }
    }

    @WorkerThread
    public final int zza(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzY();
        try {
            return zzh().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e2) {
            this.zzs.zzay().zzd().zzd("Error deleting conditional property", zzel.zzn(str), this.zzs.zzj().zzf(str2), e2);
            return 0;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzki
    public final boolean zzb() {
        return false;
    }

    @VisibleForTesting
    @WorkerThread
    public final long zzc(String str, String str2) {
        long jZzac;
        SQLiteException e2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty("first_open_count");
        zzg();
        zzY();
        SQLiteDatabase sQLiteDatabaseZzh = zzh();
        sQLiteDatabaseZzh.beginTransaction();
        try {
            try {
                StringBuilder sb = new StringBuilder(48);
                sb.append("select ");
                sb.append("first_open_count");
                sb.append(" from app2 where app_id=?");
                jZzac = zzac(sb.toString(), new String[]{str}, -1L);
                try {
                    if (jZzac == -1) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("app_id", str);
                        contentValues.put("first_open_count", (Integer) 0);
                        contentValues.put("previous_install_count", (Integer) 0);
                        if (sQLiteDatabaseZzh.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                            this.zzs.zzay().zzd().zzc("Failed to insert column (got -1). appId", zzel.zzn(str), "first_open_count");
                            return -1L;
                        }
                        jZzac = 0;
                        this.zzs.zzay().zzd().zzd("Error inserting column. appId", zzel.zzn(str), "first_open_count", e2);
                        return jZzac;
                    }
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("app_id", str);
                    contentValues2.put("first_open_count", Long.valueOf(1 + jZzac));
                    if (sQLiteDatabaseZzh.update("app2", contentValues2, "app_id = ?", new String[]{str}) == 0) {
                        this.zzs.zzay().zzd().zzc("Failed to update column (got 0). appId", zzel.zzn(str), "first_open_count");
                        return -1L;
                    }
                    sQLiteDatabaseZzh.setTransactionSuccessful();
                    return jZzac;
                } catch (SQLiteException e3) {
                    e2 = e3;
                    this.zzs.zzay().zzd().zzd("Error inserting column. appId", zzel.zzn(str), "first_open_count", e2);
                }
            } catch (SQLiteException e4) {
                jZzac = 0;
                e2 = e4;
            }
        } finally {
            sQLiteDatabaseZzh.endTransaction();
        }
    }

    @WorkerThread
    public final long zzd() {
        return zzac("select max(bundle_end_timestamp) from queue", null, 0L);
    }

    @WorkerThread
    public final long zze() {
        return zzac("select max(timestamp) from raw_events", null, 0L);
    }

    public final long zzf(String str) {
        Preconditions.checkNotEmpty(str);
        return zzac("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    @VisibleForTesting
    @WorkerThread
    public final SQLiteDatabase zzh() {
        zzg();
        try {
            return this.zzj.getWritableDatabase();
        } catch (SQLiteException e2) {
            this.zzs.zzay().zzk().zzb("Error opening database", e2);
            throw e2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:46:0x00da  */
    public final Bundle zzi(String str) throws Throwable {
        Cursor cursorRawQuery;
        zzg();
        zzY();
        Cursor cursor = null;
        try {
            try {
                cursorRawQuery = zzh().rawQuery("select parameters from default_event_params where app_id=?", new String[]{str});
                try {
                    if (!cursorRawQuery.moveToFirst()) {
                        this.zzs.zzay().zzj().zza("Default event parameters not found");
                        cursorRawQuery.close();
                        return null;
                    }
                    try {
                        com.google.android.gms.internal.measurement.zzfo zzfoVarZzaA = ((com.google.android.gms.internal.measurement.zzfn) zzku.zzl(com.google.android.gms.internal.measurement.zzfo.zze(), cursorRawQuery.getBlob(0))).zzaA();
                        this.zzf.zzu();
                        List<com.google.android.gms.internal.measurement.zzfs> listZzi = zzfoVarZzaA.zzi();
                        Bundle bundle = new Bundle();
                        for (com.google.android.gms.internal.measurement.zzfs zzfsVar : listZzi) {
                            String strZzg = zzfsVar.zzg();
                            if (zzfsVar.zzu()) {
                                bundle.putDouble(strZzg, zzfsVar.zza());
                            } else if (zzfsVar.zzv()) {
                                bundle.putFloat(strZzg, zzfsVar.zzb());
                            } else if (zzfsVar.zzy()) {
                                bundle.putString(strZzg, zzfsVar.zzh());
                            } else if (zzfsVar.zzw()) {
                                bundle.putLong(strZzg, zzfsVar.zzd());
                            }
                        }
                        cursorRawQuery.close();
                        return bundle;
                    } catch (IOException e2) {
                        this.zzs.zzay().zzd().zzc("Failed to retrieve default event parameters. appId", zzel.zzn(str), e2);
                        cursorRawQuery.close();
                        return null;
                    }
                } catch (SQLiteException e3) {
                    e = e3;
                    this.zzs.zzay().zzd().zzb("Error selecting default event parameters", e);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursorRawQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final zzg zzj(String str) {
        Cursor cursorQuery;
        Preconditions.checkNotEmpty(str);
        zzg();
        zzY();
        try {
            boolean z = true;
            cursorQuery = zzh().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id", "adid_reporting_enabled", "admob_app_id", "dynamite_version", "safelisted_events", "ga_app_id"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                try {
                    if (!cursorQuery.moveToFirst()) {
                        cursorQuery.close();
                        return null;
                    }
                    zzg zzgVar = new zzg(this.zzf.zzq(), str);
                    zzgVar.zzI(cursorQuery.getString(0));
                    zzgVar.zzY(cursorQuery.getString(1));
                    zzgVar.zzag(cursorQuery.getString(2));
                    zzgVar.zzac(cursorQuery.getLong(3));
                    zzgVar.zzad(cursorQuery.getLong(4));
                    zzgVar.zzab(cursorQuery.getLong(5));
                    zzgVar.zzK(cursorQuery.getString(6));
                    zzgVar.zzJ(cursorQuery.getString(7));
                    zzgVar.zzZ(cursorQuery.getLong(8));
                    zzgVar.zzT(cursorQuery.getLong(9));
                    zzgVar.zzae(cursorQuery.isNull(10) || cursorQuery.getInt(10) != 0);
                    zzgVar.zzS(cursorQuery.getLong(11));
                    zzgVar.zzQ(cursorQuery.getLong(12));
                    zzgVar.zzP(cursorQuery.getLong(13));
                    zzgVar.zzN(cursorQuery.getLong(14));
                    zzgVar.zzM(cursorQuery.getLong(15));
                    zzgVar.zzV(cursorQuery.getLong(16));
                    zzgVar.zzL(cursorQuery.isNull(17) ? -2147483648L : cursorQuery.getInt(17));
                    zzgVar.zzW(cursorQuery.getString(18));
                    zzgVar.zzO(cursorQuery.getLong(19));
                    zzgVar.zzR(cursorQuery.getLong(20));
                    zzgVar.zzaa(cursorQuery.getString(21));
                    long j = 0;
                    if (!this.zzs.zzf().zzs(null, zzdy.zzam)) {
                        zzgVar.zzH(cursorQuery.isNull(22) ? 0L : cursorQuery.getLong(22));
                    }
                    if (!cursorQuery.isNull(23) && cursorQuery.getInt(23) == 0) {
                        z = false;
                    }
                    zzgVar.zzG(z);
                    zzgVar.zzF(cursorQuery.getString(24));
                    if (!cursorQuery.isNull(25)) {
                        j = cursorQuery.getLong(25);
                    }
                    zzgVar.zzU(j);
                    if (!cursorQuery.isNull(26)) {
                        zzgVar.zzah(Arrays.asList(cursorQuery.getString(26).split(",", -1)));
                    }
                    zzom.zzc();
                    if (this.zzs.zzf().zzs(str, zzdy.zzac)) {
                        zzgVar.zzX(cursorQuery.getString(27));
                    }
                    zzgVar.zzD();
                    if (cursorQuery.moveToNext()) {
                        this.zzs.zzay().zzd().zzb("Got multiple records for app, expected one. appId", zzel.zzn(str));
                    }
                    cursorQuery.close();
                    return zzgVar;
                } catch (SQLiteException e2) {
                    e = e2;
                    this.zzs.zzay().zzd().zzc("Error querying app. appId", zzel.zzn(str), e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
        }
        th = th;
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        throw th;
    }

    @WorkerThread
    public final zzab zzk(String str, String str2) throws Throwable {
        Cursor cursorQuery;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzY();
        try {
            cursorQuery = zzh().query("conditional_properties", new String[]{"origin", "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                try {
                    if (!cursorQuery.moveToFirst()) {
                        cursorQuery.close();
                        return null;
                    }
                    String string = cursorQuery.getString(0);
                    if (string == null) {
                        string = "";
                    }
                    String str3 = string;
                    Object objZzq = zzq(cursorQuery, 1);
                    boolean z = cursorQuery.getInt(2) != 0;
                    zzab zzabVar = new zzab(str, str3, new zzkv(str2, cursorQuery.getLong(8), objZzq, str3), cursorQuery.getLong(6), z, cursorQuery.getString(3), (zzat) this.zzf.zzu().zzh(cursorQuery.getBlob(5), zzat.CREATOR), cursorQuery.getLong(4), (zzat) this.zzf.zzu().zzh(cursorQuery.getBlob(7), zzat.CREATOR), cursorQuery.getLong(9), (zzat) this.zzf.zzu().zzh(cursorQuery.getBlob(10), zzat.CREATOR));
                    if (cursorQuery.moveToNext()) {
                        this.zzs.zzay().zzd().zzc("Got multiple records for conditional property, expected one", zzel.zzn(str), this.zzs.zzj().zzf(str2));
                    }
                    cursorQuery.close();
                    return zzabVar;
                } catch (SQLiteException e2) {
                    e = e2;
                    this.zzs.zzay().zzd().zzd("Error querying conditional property", zzel.zzn(str), this.zzs.zzj().zzf(str2), e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
        }
        th = th;
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        throw th;
    }

    @WorkerThread
    public final zzah zzl(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return zzm(j, str, 1L, false, false, z3, false, z5);
    }

    @WorkerThread
    public final zzah zzm(long j, String str, long j2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzY();
        String[] strArr = {str};
        zzah zzahVar = new zzah();
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase sQLiteDatabaseZzh = zzh();
                Cursor cursorQuery = sQLiteDatabaseZzh.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    this.zzs.zzay().zzk().zzb("Not updating daily counts, app is not known. appId", zzel.zzn(str));
                    cursorQuery.close();
                    return zzahVar;
                }
                if (cursorQuery.getLong(0) == j) {
                    zzahVar.zzb = cursorQuery.getLong(1);
                    zzahVar.zza = cursorQuery.getLong(2);
                    zzahVar.zzc = cursorQuery.getLong(3);
                    zzahVar.zzd = cursorQuery.getLong(4);
                    zzahVar.zze = cursorQuery.getLong(5);
                }
                if (z) {
                    zzahVar.zzb += j2;
                }
                if (z2) {
                    zzahVar.zza += j2;
                }
                if (z3) {
                    zzahVar.zzc += j2;
                }
                if (z4) {
                    zzahVar.zzd += j2;
                }
                if (z5) {
                    zzahVar.zze += j2;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("day", Long.valueOf(j));
                contentValues.put("daily_public_events_count", Long.valueOf(zzahVar.zza));
                contentValues.put("daily_events_count", Long.valueOf(zzahVar.zzb));
                contentValues.put("daily_conversions_count", Long.valueOf(zzahVar.zzc));
                contentValues.put("daily_error_events_count", Long.valueOf(zzahVar.zzd));
                contentValues.put("daily_realtime_events_count", Long.valueOf(zzahVar.zze));
                sQLiteDatabaseZzh.update("apps", contentValues, "app_id=?", strArr);
                cursorQuery.close();
                return zzahVar;
            } catch (SQLiteException e2) {
                this.zzs.zzay().zzd().zzc("Error updating daily counts. appId", zzel.zzn(str), e2);
                if (0 != 0) {
                    cursor.close();
                }
                return zzahVar;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final zzap zzn(String str, String str2) {
        Cursor cursor;
        Cursor cursor2;
        Boolean boolValueOf;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzY();
        try {
            try {
                Cursor cursorQuery = zzh().query("events", (String[]) new ArrayList(Arrays.asList("lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_bundled_day", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling", "current_session_count")).toArray(new String[0]), "app_id=? and name=?", new String[]{str, str2}, null, null, null);
                try {
                    if (!cursorQuery.moveToFirst()) {
                        cursorQuery.close();
                        return null;
                    }
                    long j = cursorQuery.getLong(0);
                    long j2 = cursorQuery.getLong(1);
                    long j3 = cursorQuery.getLong(2);
                    long j4 = cursorQuery.isNull(3) ? 0L : cursorQuery.getLong(3);
                    Long lValueOf = cursorQuery.isNull(4) ? null : Long.valueOf(cursorQuery.getLong(4));
                    Long lValueOf2 = cursorQuery.isNull(5) ? null : Long.valueOf(cursorQuery.getLong(5));
                    Long lValueOf3 = cursorQuery.isNull(6) ? null : Long.valueOf(cursorQuery.getLong(6));
                    if (cursorQuery.isNull(7)) {
                        boolValueOf = null;
                    } else {
                        boolValueOf = Boolean.valueOf(cursorQuery.getLong(7) == 1);
                    }
                    cursor2 = cursorQuery;
                    try {
                        zzap zzapVar = new zzap(str, str2, j, j2, cursorQuery.isNull(8) ? 0L : cursorQuery.getLong(8), j3, j4, lValueOf, lValueOf2, lValueOf3, boolValueOf);
                        if (cursor2.moveToNext()) {
                            this.zzs.zzay().zzd().zzb("Got multiple records for event aggregates, expected one. appId", zzel.zzn(str));
                        }
                        cursor2.close();
                        return zzapVar;
                    } catch (SQLiteException e2) {
                        e = e2;
                    }
                } catch (SQLiteException e3) {
                    e = e3;
                    cursor2 = cursorQuery;
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor2 = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
        this.zzs.zzay().zzd().zzd("Error querying events. appId", zzel.zzn(str), this.zzs.zzj().zzd(str2), e);
        if (cursor2 != null) {
            cursor2.close();
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00a7  */
    @WorkerThread
    public final zzkx zzp(String str, String str2) {
        Cursor cursorQuery;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzY();
        Cursor cursor = null;
        try {
            try {
                cursorQuery = zzh().query("user_attributes", new String[]{"set_timestamp", "value", "origin"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
                try {
                    if (!cursorQuery.moveToFirst()) {
                        cursorQuery.close();
                        return null;
                    }
                    long j = cursorQuery.getLong(0);
                    Object objZzq = zzq(cursorQuery, 1);
                    if (objZzq == null) {
                        cursorQuery.close();
                        return null;
                    }
                    zzkx zzkxVar = new zzkx(str, cursorQuery.getString(2), str2, j, objZzq);
                    if (cursorQuery.moveToNext()) {
                        this.zzs.zzay().zzd().zzb("Got multiple records for user property, expected one. appId", zzel.zzn(str));
                    }
                    cursorQuery.close();
                    return zzkxVar;
                } catch (SQLiteException e2) {
                    e = e2;
                    this.zzs.zzay().zzd().zzd("Error querying user property. appId", zzel.zzn(str), this.zzs.zzj().zzf(str2), e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    @VisibleForTesting
    @WorkerThread
    public final Object zzq(Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            a.a(this.zzs, "Loaded invalid null value from database");
            return null;
        }
        if (type == 1) {
            return Long.valueOf(cursor.getLong(i));
        }
        if (type == 2) {
            return Double.valueOf(cursor.getDouble(i));
        }
        if (type == 3) {
            return cursor.getString(i);
        }
        if (type != 4) {
            this.zzs.zzay().zzd().zzb("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
            return null;
        }
        a.a(this.zzs, "Loaded invalid blob type value, ignoring it");
        return null;
    }

    @WorkerThread
    public final String zzr() throws Throwable {
        Throwable th;
        Cursor cursorRawQuery;
        SQLiteException e2;
        try {
            cursorRawQuery = zzh().rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
            try {
                try {
                    if (!cursorRawQuery.moveToFirst()) {
                        cursorRawQuery.close();
                        return null;
                    }
                    String string = cursorRawQuery.getString(0);
                    cursorRawQuery.close();
                    return string;
                } catch (SQLiteException e3) {
                    e2 = e3;
                    this.zzs.zzay().zzd().zzb("Database error getting next bundle app id", e2);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (SQLiteException e4) {
            e2 = e4;
            cursorRawQuery = null;
        } catch (Throwable th3) {
            th = th3;
            cursorRawQuery = null;
        }
        th = th2;
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        throw th;
    }

    @WorkerThread
    public final List<zzab> zzs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzY();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzt(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Code duplicated, block: B:26:0x012d  */
    public final List<zzab> zzt(String str, String[] strArr) {
        zzg();
        zzY();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                SQLiteDatabase sQLiteDatabaseZzh = zzh();
                String[] strArr2 = {"app_id", "origin", "name", "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"};
                this.zzs.zzf();
                cursorQuery = sQLiteDatabaseZzh.query("conditional_properties", strArr2, str, strArr, null, null, "rowid", "1001");
                if (!cursorQuery.moveToFirst()) {
                    cursorQuery.close();
                    return arrayList;
                }
                do {
                    int size = arrayList.size();
                    this.zzs.zzf();
                    if (size >= 1000) {
                        zzej zzejVarZzd = this.zzs.zzay().zzd();
                        this.zzs.zzf();
                        zzejVarZzd.zzb("Read more than the max allowed conditional properties, ignoring extra", 1000);
                        break;
                    }
                    String string = cursorQuery.getString(0);
                    String string2 = cursorQuery.getString(1);
                    String string3 = cursorQuery.getString(2);
                    Object objZzq = zzq(cursorQuery, 3);
                    boolean z = cursorQuery.getInt(4) != 0;
                    String string4 = cursorQuery.getString(5);
                    long j = cursorQuery.getLong(6);
                    zzat zzatVar = (zzat) this.zzf.zzu().zzh(cursorQuery.getBlob(7), zzat.CREATOR);
                    arrayList.add(new zzab(string, string2, new zzkv(string3, cursorQuery.getLong(10), objZzq, string2), cursorQuery.getLong(8), z, string4, zzatVar, j, (zzat) this.zzf.zzu().zzh(cursorQuery.getBlob(9), zzat.CREATOR), cursorQuery.getLong(11), (zzat) this.zzf.zzu().zzh(cursorQuery.getBlob(12), zzat.CREATOR)));
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return arrayList;
            } catch (SQLiteException e2) {
                this.zzs.zzay().zzd().zzb("Error querying conditional user property value", e2);
                List<zzab> listEmptyList = Collections.emptyList();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return listEmptyList;
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        throw th;
    }

    @WorkerThread
    public final List<zzkx> zzu(String str) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzY();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                this.zzs.zzf();
                cursorQuery = zzh().query("user_attributes", new String[]{"name", "origin", "set_timestamp", "value"}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
                if (!cursorQuery.moveToFirst()) {
                    cursorQuery.close();
                    return arrayList;
                }
                do {
                    String string = cursorQuery.getString(0);
                    String string2 = cursorQuery.getString(1);
                    if (string2 == null) {
                        string2 = "";
                    }
                    String str2 = string2;
                    long j = cursorQuery.getLong(2);
                    Object objZzq = zzq(cursorQuery, 3);
                    if (objZzq == null) {
                        this.zzs.zzay().zzd().zzb("Read invalid user property value, ignoring it. appId", zzel.zzn(str));
                    } else {
                        arrayList.add(new zzkx(str, str2, string, j, objZzq));
                    }
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return arrayList;
            } catch (SQLiteException e2) {
                this.zzs.zzay().zzd().zzc("Error querying user properties. appId", zzel.zzn(str), e2);
                List<zzkx> listEmptyList = Collections.emptyList();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return listEmptyList;
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x011d  */
    @WorkerThread
    public final List<zzkx> zzv(String str, String str2, String str3) {
        String string;
        Preconditions.checkNotEmpty(str);
        zzg();
        zzY();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                ArrayList arrayList2 = new ArrayList(3);
                try {
                    arrayList2.add(str);
                    StringBuilder sb = new StringBuilder("app_id=?");
                    if (TextUtils.isEmpty(str2)) {
                        string = str2;
                    } else {
                        string = str2;
                        try {
                            arrayList2.add(string);
                            sb.append(" and origin=?");
                        } catch (SQLiteException e2) {
                            e = e2;
                            this.zzs.zzay().zzd().zzd("(2)Error querying user properties", zzel.zzn(str), string, e);
                            List<zzkx> listEmptyList = Collections.emptyList();
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            return listEmptyList;
                        }
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        arrayList2.add(String.valueOf(str3).concat("*"));
                        sb.append(" and name glob ?");
                    }
                    String[] strArr = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
                    String string2 = sb.toString();
                    this.zzs.zzf();
                    cursorQuery = zzh().query("user_attributes", new String[]{"name", "set_timestamp", "value", "origin"}, string2, strArr, null, null, "rowid", "1001");
                    if (!cursorQuery.moveToFirst()) {
                        cursorQuery.close();
                        return arrayList;
                    }
                    do {
                        int size = arrayList.size();
                        this.zzs.zzf();
                        if (size >= 1000) {
                            zzej zzejVarZzd = this.zzs.zzay().zzd();
                            this.zzs.zzf();
                            zzejVarZzd.zzb("Read more than the max allowed user properties, ignoring excess", 1000);
                            break;
                        }
                        String string3 = cursorQuery.getString(0);
                        long j = cursorQuery.getLong(1);
                        Object objZzq = zzq(cursorQuery, 2);
                        string = cursorQuery.getString(3);
                        if (objZzq == null) {
                            this.zzs.zzay().zzd().zzd("(2)Read invalid user property value, ignoring it", zzel.zzn(str), string, str3);
                        } else {
                            arrayList.add(new zzkx(str, string, string3, j, objZzq));
                        }
                    } while (cursorQuery.moveToNext());
                    cursorQuery.close();
                    return arrayList;
                } catch (SQLiteException e3) {
                    e = e3;
                    string = str2;
                    this.zzs.zzay().zzd().zzd("(2)Error querying user properties", zzel.zzn(str), string, e);
                    List<zzkx> listEmptyList2 = Collections.emptyList();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return listEmptyList2;
                }
            } catch (SQLiteException e4) {
                e = e4;
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final void zzw() {
        zzY();
        zzh().beginTransaction();
    }

    public final void zzx(List<Long> list) {
        Preconditions.checkNotNull(list);
        zzg();
        zzY();
        StringBuilder sb = new StringBuilder("rowid in (");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(list.get(i).longValue());
        }
        sb.append(")");
        int iDelete = zzh().delete("raw_events", sb.toString(), null);
        if (iDelete != list.size()) {
            this.zzs.zzay().zzd().zzc("Deleted fewer rows from raw events table than expected", Integer.valueOf(iDelete), Integer.valueOf(list.size()));
        }
    }

    @WorkerThread
    public final void zzy() {
        zzY();
        zzh().endTransaction();
    }

    @VisibleForTesting
    @WorkerThread
    public final void zzz(List<Long> list) {
        zzg();
        zzY();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzK()) {
            String strJoin = TextUtils.join(",", list);
            String strA = a.a(new StringBuilder(String.valueOf(strJoin).length() + 2), "(", strJoin, ")");
            if (zzab(a.a(new StringBuilder(strA.length() + 80), "SELECT COUNT(1) FROM queue WHERE rowid IN ", strA, " AND retry_count =  2147483647 LIMIT 1"), null) > 0) {
                a.c(this.zzs, "The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase sQLiteDatabaseZzh = zzh();
                StringBuilder sb = new StringBuilder(strA.length() + 127);
                sb.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb.append(strA);
                sb.append(" AND (retry_count IS NULL OR retry_count < ");
                sb.append(Integer.MAX_VALUE);
                sb.append(")");
                sQLiteDatabaseZzh.execSQL(sb.toString());
            } catch (SQLiteException e2) {
                a.a(this.zzs, "Error incrementing retry count. error", e2);
            }
        }
    }
}
