package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzv {
    public final /* synthetic */ zzz zza;
    public com.google.android.gms.internal.measurement.zzfo zzb;
    public Long zzc;
    public long zzd;

    /* JADX WARN: Code duplicated, block: B:67:0x01d7  */
    public final com.google.android.gms.internal.measurement.zzfo zza(String str, com.google.android.gms.internal.measurement.zzfo zzfoVar) {
        Cursor cursor;
        Cursor cursorRawQuery;
        Object obj;
        String strZzh = zzfoVar.zzh();
        List<com.google.android.gms.internal.measurement.zzfs> listZzi = zzfoVar.zzi();
        this.zza.zzf.zzu();
        Long l = (Long) zzku.zzD(zzfoVar, "_eid");
        if (l != null) {
            if (strZzh.equals("_ep")) {
                Preconditions.checkNotNull(l);
                this.zza.zzf.zzu();
                String str2 = (String) zzku.zzD(zzfoVar, "_en");
                Pair pairCreate = null;
                if (TextUtils.isEmpty(str2)) {
                    this.zza.zzs.zzay().zzh().zzb("Extra parameter without an event name. eventId", l);
                    return null;
                }
                if (this.zzb == null || this.zzc == null || l.longValue() != this.zzc.longValue()) {
                    zzaj zzajVarZzi = this.zza.zzf.zzi();
                    zzajVarZzi.zzg();
                    zzajVarZzi.zzY();
                    try {
                        try {
                            cursorRawQuery = zzajVarZzi.zzh().rawQuery("select main_event, children_to_process from main_event_params where app_id=? and event_id=?", new String[]{str, l.toString()});
                            try {
                                if (cursorRawQuery.moveToFirst()) {
                                    try {
                                        pairCreate = Pair.create(((com.google.android.gms.internal.measurement.zzfn) zzku.zzl(com.google.android.gms.internal.measurement.zzfo.zze(), cursorRawQuery.getBlob(0))).zzaA(), Long.valueOf(cursorRawQuery.getLong(1)));
                                    } catch (IOException e2) {
                                        zzajVarZzi.zzs.zzay().zzd().zzd("Failed to merge main event. appId, eventId", zzel.zzn(str), l, e2);
                                        cursorRawQuery.close();
                                        pairCreate = null;
                                    }
                                } else {
                                    zzajVarZzi.zzs.zzay().zzj().zza("Main event not found");
                                }
                                cursorRawQuery.close();
                            } catch (SQLiteException e3) {
                                e = e3;
                                zzajVarZzi.zzs.zzay().zzd().zzb("Error selecting main event", e);
                                if (cursorRawQuery != null) {
                                    cursorRawQuery.close();
                                }
                                pairCreate = null;
                                if (pairCreate != null) {
                                }
                                this.zza.zzs.zzay().zzh().zzc("Extra parameter without existing main event. eventName, eventId", str2, l);
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
                        cursor = null;
                        if (0 != 0) {
                            cursor.close();
                        }
                        throw th;
                    }
                    if (pairCreate != null || (obj = pairCreate.first) == null) {
                        this.zza.zzs.zzay().zzh().zzc("Extra parameter without existing main event. eventName, eventId", str2, l);
                        return null;
                    }
                    this.zzb = (com.google.android.gms.internal.measurement.zzfo) obj;
                    this.zzd = ((Long) pairCreate.second).longValue();
                    this.zza.zzf.zzu();
                    this.zzc = (Long) zzku.zzD(this.zzb, "_eid");
                }
                long j = this.zzd - 1;
                this.zzd = j;
                if (j <= 0) {
                    zzaj zzajVarZzi2 = this.zza.zzf.zzi();
                    zzajVarZzi2.zzg();
                    zzajVarZzi2.zzs.zzay().zzj().zzb("Clearing complex main event info. appId", str);
                    try {
                        zzajVarZzi2.zzh().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                    } catch (SQLiteException e5) {
                        a.a(zzajVarZzi2.zzs, "Error clearing complex main event", e5);
                    }
                } else {
                    this.zza.zzf.zzi().zzL(str, l, this.zzd, this.zzb);
                }
                ArrayList arrayList = new ArrayList();
                for (com.google.android.gms.internal.measurement.zzfs zzfsVar : this.zzb.zzi()) {
                    this.zza.zzf.zzu();
                    if (zzku.zzC(zzfoVar, zzfsVar.zzg()) == null) {
                        arrayList.add(zzfsVar);
                    }
                }
                if (arrayList.isEmpty()) {
                    this.zza.zzs.zzay().zzh().zzb("No unique parameters in main event. eventName", str2);
                } else {
                    arrayList.addAll(listZzi);
                    listZzi = arrayList;
                }
                strZzh = str2;
            } else {
                this.zzc = l;
                this.zzb = zzfoVar;
                this.zza.zzf.zzu();
                Object objZzD = zzku.zzD(zzfoVar, "_epc");
                long jLongValue = ((Long) (objZzD != null ? objZzD : 0L)).longValue();
                this.zzd = jLongValue;
                if (jLongValue <= 0) {
                    this.zza.zzs.zzay().zzh().zzb("Complex event with zero extra param count. eventName", strZzh);
                } else {
                    zzaj zzajVarZzi3 = this.zza.zzf.zzi();
                    Preconditions.checkNotNull(l);
                    zzajVarZzi3.zzL(str, l, this.zzd, zzfoVar);
                }
            }
        }
        com.google.android.gms.internal.measurement.zzfn zzfnVarZzbv = zzfoVar.zzbv();
        zzfnVarZzbv.zzi(strZzh);
        zzfnVarZzbv.zzg();
        zzfnVarZzbv.zzd(listZzi);
        return zzfnVarZzbv.zzaA();
    }
}
