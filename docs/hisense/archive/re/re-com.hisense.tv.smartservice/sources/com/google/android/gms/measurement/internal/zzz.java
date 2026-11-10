package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzoa;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class zzz extends zzki {
    public String zza;
    public Set<Integer> zzb;
    public Map<Integer, zzt> zzc;
    public Long zzd;
    public Long zze;

    public zzz(zzks zzksVar) {
        super(zzksVar);
    }

    private final zzt zzd(Integer num) {
        if (this.zzc.containsKey(num)) {
            return this.zzc.get(num);
        }
        zzt zztVar = new zzt(this, this.zza, null);
        this.zzc.put(num, zztVar);
        return zztVar;
    }

    private final boolean zzf(int i, int i2) {
        zzt zztVar = this.zzc.get(Integer.valueOf(i));
        if (zztVar == null) {
            return false;
        }
        return zztVar.zze.get(i2);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0265  */
    /* JADX WARN: Code duplicated, block: B:102:0x0270  */
    /* JADX WARN: Code duplicated, block: B:105:0x0285  */
    /* JADX WARN: Code duplicated, block: B:109:0x02b3 A[Catch: all -> 0x02e7, SQLiteException -> 0x02ea, LOOP:11: B:109:0x02b3->B:503:?, LOOP_START, TryCatch #4 {all -> 0x02e7, blocks: (B:107:0x02ad, B:109:0x02b3, B:111:0x02c4, B:112:0x02cc, B:115:0x02e2, B:125:0x02f2), top: B:429:0x029d }] */
    /* JADX WARN: Code duplicated, block: B:111:0x02c4 A[Catch: all -> 0x02e7, SQLiteException -> 0x02ea, TryCatch #4 {all -> 0x02e7, blocks: (B:107:0x02ad, B:109:0x02b3, B:111:0x02c4, B:112:0x02cc, B:115:0x02e2, B:125:0x02f2), top: B:429:0x029d }] */
    /* JADX WARN: Code duplicated, block: B:115:0x02e2 A[Catch: all -> 0x02e7, SQLiteException -> 0x02ea, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x02e7, blocks: (B:107:0x02ad, B:109:0x02b3, B:111:0x02c4, B:112:0x02cc, B:115:0x02e2, B:125:0x02f2), top: B:429:0x029d }] */
    /* JADX WARN: Code duplicated, block: B:131:0x031a  */
    /* JADX WARN: Code duplicated, block: B:133:0x0336  */
    /* JADX WARN: Code duplicated, block: B:158:0x03d7  */
    /* JADX WARN: Code duplicated, block: B:160:0x03db  */
    /* JADX WARN: Code duplicated, block: B:164:0x03e6  */
    /* JADX WARN: Code duplicated, block: B:166:0x040b  */
    /* JADX WARN: Code duplicated, block: B:172:0x0420  */
    /* JADX WARN: Code duplicated, block: B:176:0x043a  */
    /* JADX WARN: Code duplicated, block: B:177:0x0443  */
    /* JADX WARN: Code duplicated, block: B:181:0x044f  */
    /* JADX WARN: Code duplicated, block: B:187:0x0464  */
    /* JADX WARN: Code duplicated, block: B:194:0x049c  */
    /* JADX WARN: Code duplicated, block: B:197:0x04a5  */
    /* JADX WARN: Code duplicated, block: B:199:0x04af  */
    /* JADX WARN: Code duplicated, block: B:201:0x04d7  */
    /* JADX WARN: Code duplicated, block: B:202:0x04db  */
    /* JADX WARN: Code duplicated, block: B:207:0x04fe A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:231:0x059a  */
    /* JADX WARN: Code duplicated, block: B:234:0x05ae  */
    /* JADX WARN: Code duplicated, block: B:238:0x05d2  */
    /* JADX WARN: Code duplicated, block: B:239:0x0611  */
    /* JADX WARN: Code duplicated, block: B:242:0x065e  */
    /* JADX WARN: Code duplicated, block: B:248:0x06aa  */
    /* JADX WARN: Code duplicated, block: B:255:0x06d2  */
    /* JADX WARN: Code duplicated, block: B:257:0x06dd  */
    /* JADX WARN: Code duplicated, block: B:266:0x070d A[LOOP:8: B:249:0x06ac->B:266:0x070d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:271:0x0718  */
    /* JADX WARN: Code duplicated, block: B:273:0x0722 A[PHI: r0 r11 r13 r25 r28 r29
      0x0722: PHI (r0v93 java.util.Map) = (r0v95 java.util.Map), (r0v104 java.util.Map) binds: [B:292:0x075c, B:272:0x071e] A[DONT_GENERATE, DONT_INLINE]
      0x0722: PHI (r11v16 android.database.Cursor) = (r11v17 android.database.Cursor), (r11v19 android.database.Cursor) binds: [B:292:0x075c, B:272:0x071e] A[DONT_GENERATE, DONT_INLINE]
      0x0722: PHI (r13v12 java.lang.String) = (r13v13 java.lang.String), (r13v16 java.lang.String) binds: [B:292:0x075c, B:272:0x071e] A[DONT_GENERATE, DONT_INLINE]
      0x0722: PHI (r25v4 java.lang.String) = (r25v5 java.lang.String), (r25v7 java.lang.String) binds: [B:292:0x075c, B:272:0x071e] A[DONT_GENERATE, DONT_INLINE]
      0x0722: PHI (r28v7 java.lang.String) = (r28v8 java.lang.String), (r28v13 java.lang.String) binds: [B:292:0x075c, B:272:0x071e] A[DONT_GENERATE, DONT_INLINE]
      0x0722: PHI (r29v7 java.lang.String) = (r29v8 java.lang.String), (r29v14 java.lang.String) binds: [B:292:0x075c, B:272:0x071e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:298:0x0769  */
    /* JADX WARN: Code duplicated, block: B:302:0x077f  */
    /* JADX WARN: Code duplicated, block: B:308:0x07b4  */
    /* JADX WARN: Code duplicated, block: B:310:0x07e3 A[LOOP:10: B:306:0x07ae->B:310:0x07e3, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:314:0x0803  */
    /* JADX WARN: Code duplicated, block: B:320:0x0827  */
    /* JADX WARN: Code duplicated, block: B:323:0x0836  */
    /* JADX WARN: Code duplicated, block: B:325:0x0849  */
    /* JADX WARN: Code duplicated, block: B:329:0x088d A[Catch: all -> 0x08f4, SQLiteException -> 0x08f6, LOOP:4: B:329:0x088d->B:344:0x08e5, LOOP_START, PHI: r3
      0x088d: PHI (r3v26 java.util.Iterator<com.google.android.gms.internal.measurement.zzgh>) = 
      (r3v19 java.util.Iterator<com.google.android.gms.internal.measurement.zzgh>)
      (r3v28 java.util.Iterator<com.google.android.gms.internal.measurement.zzgh>)
     binds: [B:328:0x088b, B:344:0x08e5] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #18 {all -> 0x08f4, blocks: (B:327:0x0887, B:329:0x088d, B:330:0x0892, B:332:0x08a3, B:334:0x08b3, B:336:0x08be, B:341:0x08da, B:338:0x08c5, B:340:0x08d3, B:360:0x0903, B:346:0x08ea), top: B:448:0x0887 }] */
    /* JADX WARN: Code duplicated, block: B:334:0x08b3 A[Catch: all -> 0x08f4, SQLiteException -> 0x08f6, TryCatch #18 {all -> 0x08f4, blocks: (B:327:0x0887, B:329:0x088d, B:330:0x0892, B:332:0x08a3, B:334:0x08b3, B:336:0x08be, B:341:0x08da, B:338:0x08c5, B:340:0x08d3, B:360:0x0903, B:346:0x08ea), top: B:448:0x0887 }] */
    /* JADX WARN: Code duplicated, block: B:335:0x08bc  */
    /* JADX WARN: Code duplicated, block: B:344:0x08e5 A[LOOP:4: B:329:0x088d->B:344:0x08e5, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:345:0x08e8  */
    /* JADX WARN: Code duplicated, block: B:367:0x0925  */
    /* JADX WARN: Code duplicated, block: B:371:0x0939  */
    /* JADX WARN: Code duplicated, block: B:374:0x095e  */
    /* JADX WARN: Code duplicated, block: B:377:0x096f  */
    /* JADX WARN: Code duplicated, block: B:379:0x0986  */
    /* JADX WARN: Code duplicated, block: B:381:0x099a  */
    /* JADX WARN: Code duplicated, block: B:382:0x09a3  */
    /* JADX WARN: Code duplicated, block: B:384:0x09d3  */
    /* JADX WARN: Code duplicated, block: B:387:0x09db  */
    /* JADX WARN: Code duplicated, block: B:396:0x0a2c  */
    /* JADX WARN: Code duplicated, block: B:397:0x0a35  */
    /* JADX WARN: Code duplicated, block: B:401:0x0a44 A[PHI: r68
      0x0a44: PHI (r68v3 java.util.Map) = (r68v4 java.util.Map), (r0v126 java.util.Map) binds: [B:400:0x0a42, B:398:0x0a36] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:407:0x0a73  */
    /* JADX WARN: Code duplicated, block: B:412:0x0ad3 A[Catch: SQLiteException -> 0x0ae7, TRY_LEAVE, TryCatch #14 {SQLiteException -> 0x0ae7, blocks: (B:410:0x0ac9, B:412:0x0ad3), top: B:443:0x0ac9 }] */
    /* JADX WARN: Code duplicated, block: B:422:0x0b05  */
    /* JADX WARN: Code duplicated, block: B:475:0x0afe A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:480:0x08e0 A[EDGE_INSN: B:480:0x08e0->B:343:0x08e0 BREAK  A[LOOP:4: B:329:0x088d->B:344:0x08e5], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:481:0x094f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:483:0x0a4d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:484:0x0a16 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:487:0x0a40 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:489:0x05bc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:491:0x05a8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:493:0x0707 A[EDGE_INSN: B:493:0x0707->B:265:0x0707 BREAK  A[LOOP:8: B:249:0x06ac->B:266:0x070d], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:495:0x07a3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:496:0x0795 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:500:0x07fd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:501:0x07f3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:506:0x03c6 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:519:0x055c A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:526:0x042c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:528:0x041a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:531:0x0470 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:534:0x045e A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:544:0x0211 A[EDGE_INSN: B:544:0x0211->B:76:0x0211 BREAK  A[LOOP:20: B:65:0x01c5->B:77:0x0217], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:55:0x0178  */
    /* JADX WARN: Code duplicated, block: B:62:0x01b2 A[Catch: all -> 0x0222, SQLiteException -> 0x0225, TRY_LEAVE, TryCatch #0 {all -> 0x0222, blocks: (B:60:0x01ac, B:62:0x01b2, B:64:0x01c0, B:65:0x01c5, B:66:0x01cf, B:67:0x01df, B:74:0x020b, B:69:0x01ee, B:71:0x01fe, B:73:0x0204, B:93:0x0237), top: B:424:0x0192 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x01c0 A[Catch: all -> 0x0222, SQLiteException -> 0x0225, TRY_ENTER, TryCatch #0 {all -> 0x0222, blocks: (B:60:0x01ac, B:62:0x01b2, B:64:0x01c0, B:65:0x01c5, B:66:0x01cf, B:67:0x01df, B:74:0x020b, B:69:0x01ee, B:71:0x01fe, B:73:0x0204, B:93:0x0237), top: B:424:0x0192 }] */
    /* JADX WARN: Code duplicated, block: B:77:0x0217 A[LOOP:20: B:65:0x01c5->B:77:0x0217, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:95:0x0250  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v47, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v11, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v32 */
    /* JADX WARN: Type inference failed for: r5v34, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r5v38, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v39 */
    /* JADX WARN: Type inference failed for: r5v40, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r5v41 */
    /* JADX WARN: Type inference failed for: r5v42 */
    /* JADX WARN: Type inference failed for: r5v43 */
    /* JADX WARN: Type inference failed for: r5v44 */
    /* JADX WARN: Type inference failed for: r5v47 */
    /* JADX WARN: Type inference failed for: r5v48 */
    /* JADX WARN: Type inference failed for: r5v49 */
    /* JADX WARN: Type inference failed for: r5v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r5v50 */
    /* JADX WARN: Type inference failed for: r5v8, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v9 */
    @WorkerThread
    public final List<com.google.android.gms.internal.measurement.zzfk> zza(String str, List<com.google.android.gms.internal.measurement.zzfo> list, List<com.google.android.gms.internal.measurement.zzgh> list2, Long l, Long l2) throws Throwable {
        int i;
        int i2;
        boolean z;
        ?? r5;
        Map map;
        ?? r6;
        zzaj zzajVarZzi;
        String str2;
        String str3;
        Cursor cursorQuery;
        Map map2;
        Map map3;
        Iterator it;
        int iIntValue;
        com.google.android.gms.internal.measurement.zzgd zzgdVar;
        BitSet bitSet;
        BitSet bitSet2;
        ArrayMap arrayMap;
        List<com.google.android.gms.internal.measurement.zzej> list3;
        int i3;
        String str4;
        String str5;
        Iterator<com.google.android.gms.internal.measurement.zzgf> it2;
        com.google.android.gms.internal.measurement.zzgf next;
        Long lValueOf;
        String str6;
        ArrayMap arrayMap2;
        Map arrayMap3;
        ?? Zzh;
        Cursor cursorRawQuery;
        Iterator it3;
        com.google.android.gms.internal.measurement.zzgd zzgdVar2;
        List<Integer> list4;
        Map map4;
        Integer numValueOf;
        List arrayList;
        String str7;
        String str8;
        String str9;
        zzu zzuVar;
        zzv zzvVar;
        ArrayMap arrayMap4;
        Iterator<com.google.android.gms.internal.measurement.zzfo> it4;
        com.google.android.gms.internal.measurement.zzfo next2;
        com.google.android.gms.internal.measurement.zzfo zzfoVarZza;
        zzaj zzajVarZzi2;
        String str10;
        String strZzh;
        zzap zzapVarZzn;
        zzap zzapVar;
        long j;
        String strZzh2;
        Map mapEmptyMap;
        String str11;
        String str12;
        Iterator it5;
        int iIntValue2;
        Set<Integer> set;
        Integer numValueOf2;
        Iterator it6;
        boolean zZzd;
        Map map5;
        Iterator it7;
        zzw zzwVar;
        zzaj zzajVarZzi3;
        String str13;
        ArrayMap arrayMap5;
        Cursor cursorQuery2;
        Integer numValueOf3;
        List list5;
        List arrayList2;
        String str14;
        ArrayMap arrayMap6;
        Iterator<com.google.android.gms.internal.measurement.zzgh> it8;
        String strZzf;
        Map mapEmptyMap2;
        String str15;
        String str16;
        Iterator it9;
        int iIntValue3;
        Set<Integer> set2;
        Integer numValueOf4;
        Iterator it10;
        boolean zZzd2;
        com.google.android.gms.internal.measurement.zzes zzesVar;
        Integer numValueOf5;
        zzy zzyVar;
        Integer numValueOf6;
        zzaj zzajVarZzi4;
        String str17;
        ArrayMap arrayMap7;
        Cursor cursorQuery3;
        Integer numValueOf7;
        List list6;
        List arrayList3;
        String str18;
        ArrayList arrayList4;
        Iterator<Integer> it11;
        zzaj zzajVarZzi5;
        String str19;
        ContentValues contentValues;
        ArrayMap arrayMap8;
        int i4;
        Cursor cursorQuery4;
        Cursor cursor;
        List arrayList5;
        String str20 = "current_results";
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        this.zza = str;
        this.zzb = new HashSet();
        this.zzc = new ArrayMap();
        this.zzd = l;
        this.zze = l2;
        Iterator<com.google.android.gms.internal.measurement.zzfo> it12 = list.iterator();
        while (true) {
            i = 0;
            i2 = 1;
            if (!it12.hasNext()) {
                z = false;
                break;
            }
            if ("_s".equals(it12.next().zzh())) {
                z = true;
                break;
            }
        }
        zzoa.zzc();
        boolean zZzs = this.zzs.zzf().zzs(this.zza, zzdy.zzY);
        zzoa.zzc();
        boolean zZzs2 = this.zzs.zzf().zzs(this.zza, zzdy.zzX);
        if (z) {
            zzaj zzajVarZzi6 = this.zzf.zzi();
            String str21 = this.zza;
            zzajVarZzi6.zzY();
            zzajVarZzi6.zzg();
            Preconditions.checkNotEmpty(str21);
            ContentValues contentValues2 = new ContentValues();
            ?? r7 = "current_session_count";
            contentValues2.put("current_session_count", (Integer) 0);
            try {
                r7 = new String[]{str21};
                zzajVarZzi6.zzh().update("events", contentValues2, "app_id = ?", r7);
                r5 = r7;
            } catch (SQLiteException e2) {
                zzajVarZzi6.zzs.zzay().zzd().zzc("Error resetting session-scoped event counts. appId", zzel.zzn(str21), e2);
                r5 = r7;
            }
        }
        Map mapEmptyMap3 = Collections.emptyMap();
        String str22 = "Failed to merge filter. appId";
        String str23 = "Database error querying filters. appId";
        String str24 = "data";
        String str25 = "audience_id";
        ?? r8 = r5;
        r8 = r5;
        if (zZzs2 && zZzs) {
            zzaj zzajVarZzi7 = this.zzf.zzi();
            String str26 = this.zza;
            Preconditions.checkNotEmpty(str26);
            ArrayMap arrayMap9 = new ArrayMap();
            try {
                try {
                    cursorQuery4 = zzajVarZzi7.zzh().query("event_filters", new String[]{"audience_id", "data"}, "app_id=?", new String[]{str26}, null, null, null);
                    try {
                        if (cursorQuery4.moveToFirst()) {
                            while (true) {
                                try {
                                    com.google.android.gms.internal.measurement.zzej zzejVarZzaA = ((com.google.android.gms.internal.measurement.zzei) zzku.zzl(com.google.android.gms.internal.measurement.zzej.zzc(), cursorQuery4.getBlob(i2))).zzaA();
                                    if (zzejVarZzaA.zzo()) {
                                        Integer numValueOf8 = Integer.valueOf(cursorQuery4.getInt(i));
                                        List list7 = (List) arrayMap9.get(numValueOf8);
                                        if (list7 == null) {
                                            arrayList5 = new ArrayList();
                                            arrayMap9.put(numValueOf8, arrayList5);
                                        } else {
                                            arrayList5 = list7;
                                        }
                                        arrayList5.add(zzejVarZzaA);
                                    }
                                } catch (IOException e3) {
                                    zzajVarZzi7.zzs.zzay().zzd().zzc("Failed to merge filter. appId", zzel.zzn(str26), e3);
                                }
                                if (!cursorQuery4.moveToNext()) {
                                    break;
                                }
                                i = 0;
                                i2 = 1;
                            }
                            cursorQuery4.close();
                            map = arrayMap9;
                            r6 = cursorQuery4;
                        } else {
                            mapEmptyMap3 = Collections.emptyMap();
                            cursor = cursorQuery4;
                            cursor.close();
                            r8 = cursor;
                            map = mapEmptyMap3;
                            r6 = r8;
                        }
                    } catch (SQLiteException e4) {
                        e = e4;
                        zzajVarZzi7.zzs.zzay().zzd().zzc("Database error querying filters. appId", zzel.zzn(str26), e);
                        mapEmptyMap3 = Collections.emptyMap();
                        cursor = cursorQuery4;
                        r8 = cursorQuery4;
                        if (cursorQuery4 != null) {
                        }
                        map = mapEmptyMap3;
                        r6 = r8;
                        zzajVarZzi = this.zzf.zzi();
                        str2 = this.zza;
                        zzajVarZzi.zzY();
                        zzajVarZzi.zzg();
                        Preconditions.checkNotEmpty(str2);
                        try {
                            cursorQuery = zzajVarZzi.zzh().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str2}, null, null, null);
                            try {
                                if (cursorQuery.moveToFirst()) {
                                    arrayMap8 = new ArrayMap();
                                    while (true) {
                                        i4 = cursorQuery.getInt(0);
                                        try {
                                            arrayMap8.put(Integer.valueOf(i4), ((com.google.android.gms.internal.measurement.zzgc) zzku.zzl(com.google.android.gms.internal.measurement.zzgd.zzf(), cursorQuery.getBlob(1))).zzaA());
                                            str3 = str25;
                                        } catch (IOException e5) {
                                            str3 = str25;
                                            try {
                                                zzajVarZzi.zzs.zzay().zzd().zzd("Failed to merge filter results. appId, audienceId, error", zzel.zzn(str2), Integer.valueOf(i4), e5);
                                            } catch (SQLiteException e6) {
                                                e = e6;
                                                str24 = str24;
                                                zzajVarZzi.zzs.zzay().zzd().zzc("Database error querying filter results. appId", zzel.zzn(str2), e);
                                                Map mapEmptyMap4 = Collections.emptyMap();
                                                if (cursorQuery != null) {
                                                    cursorQuery.close();
                                                }
                                                map2 = mapEmptyMap4;
                                                if (!map2.isEmpty()) {
                                                    HashSet hashSet = new HashSet(map2.keySet());
                                                    if (z) {
                                                        str6 = this.zza;
                                                        Preconditions.checkNotEmpty(str6);
                                                        Preconditions.checkNotNull(map2);
                                                        arrayMap2 = new ArrayMap();
                                                        if (!map2.isEmpty()) {
                                                            zzaj zzajVarZzi8 = this.zzf.zzi();
                                                            zzajVarZzi8.zzY();
                                                            zzajVarZzi8.zzg();
                                                            Preconditions.checkNotEmpty(str6);
                                                            arrayMap3 = new ArrayMap();
                                                            Zzh = zzajVarZzi8.zzh();
                                                            try {
                                                                try {
                                                                    cursorRawQuery = Zzh.rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str6, str6});
                                                                    try {
                                                                        if (cursorRawQuery.moveToFirst()) {
                                                                            do {
                                                                                numValueOf = Integer.valueOf(cursorRawQuery.getInt(0));
                                                                                arrayList = (List) arrayMap3.get(numValueOf);
                                                                                if (arrayList == null) {
                                                                                    arrayList = new ArrayList();
                                                                                    arrayMap3.put(numValueOf, arrayList);
                                                                                }
                                                                                arrayList.add(Integer.valueOf(cursorRawQuery.getInt(1)));
                                                                            } while (cursorRawQuery.moveToNext());
                                                                        } else {
                                                                            arrayMap3 = Collections.emptyMap();
                                                                        }
                                                                    } catch (SQLiteException e7) {
                                                                        e = e7;
                                                                        zzajVarZzi8.zzs.zzay().zzd().zzc("Database error querying scoped filters. appId", zzel.zzn(str6), e);
                                                                        arrayMap3 = Collections.emptyMap();
                                                                        if (cursorRawQuery != null) {
                                                                        }
                                                                        it3 = map2.keySet().iterator();
                                                                        while (it3.hasNext()) {
                                                                            int iIntValue4 = ((Integer) it3.next()).intValue();
                                                                            Integer numValueOf9 = Integer.valueOf(iIntValue4);
                                                                            zzgdVar2 = (com.google.android.gms.internal.measurement.zzgd) map2.get(numValueOf9);
                                                                            list4 = (List) arrayMap3.get(numValueOf9);
                                                                            if (list4 != null) {
                                                                            }
                                                                            map4 = arrayMap3;
                                                                            arrayMap2.put(numValueOf9, zzgdVar2);
                                                                            arrayMap3 = map4;
                                                                            it3 = it3;
                                                                        }
                                                                        map3 = arrayMap2;
                                                                        it = hashSet.iterator();
                                                                        while (it.hasNext()) {
                                                                            iIntValue = ((Integer) it.next()).intValue();
                                                                            zzgdVar = (com.google.android.gms.internal.measurement.zzgd) map3.get(Integer.valueOf(iIntValue));
                                                                            bitSet = new BitSet();
                                                                            bitSet2 = new BitSet();
                                                                            arrayMap = new ArrayMap();
                                                                            if (zzgdVar != null) {
                                                                                for (com.google.android.gms.internal.measurement.zzfm zzfmVar : zzgdVar.zzj()) {
                                                                                    if (zzfmVar.zzh()) {
                                                                                        Integer numValueOf10 = Integer.valueOf(zzfmVar.zza());
                                                                                        if (zzfmVar.zzg()) {
                                                                                            lValueOf = Long.valueOf(zzfmVar.zzb());
                                                                                        } else {
                                                                                            lValueOf = null;
                                                                                        }
                                                                                        arrayMap.put(numValueOf10, lValueOf);
                                                                                    }
                                                                                }
                                                                            }
                                                                            ArrayMap arrayMap10 = new ArrayMap();
                                                                            if (zzgdVar != null) {
                                                                                it2 = zzgdVar.zzm().iterator();
                                                                                while (it2.hasNext()) {
                                                                                    next = it2.next();
                                                                                    if (!next.zzi()) {
                                                                                    }
                                                                                }
                                                                            }
                                                                            Map map6 = map3;
                                                                            if (zzgdVar != null) {
                                                                                i3 = 0;
                                                                                while (i3 < zzgdVar.zzd() * 64) {
                                                                                    if (zzku.zzw(zzgdVar.zzn(), i3)) {
                                                                                        str4 = str23;
                                                                                        str5 = str22;
                                                                                        this.zzs.zzay().zzj().zzc("Filter already evaluated. audience ID, filter ID", Integer.valueOf(iIntValue), Integer.valueOf(i3));
                                                                                        bitSet2.set(i3);
                                                                                        if (zzku.zzw(zzgdVar.zzk(), i3)) {
                                                                                            bitSet.set(i3);
                                                                                        }
                                                                                        i3++;
                                                                                        str23 = str4;
                                                                                        str22 = str5;
                                                                                    } else {
                                                                                        str4 = str23;
                                                                                        str5 = str22;
                                                                                    }
                                                                                    arrayMap.remove(Integer.valueOf(i3));
                                                                                    i3++;
                                                                                    str23 = str4;
                                                                                    str22 = str5;
                                                                                }
                                                                            }
                                                                            String str27 = str23;
                                                                            String str28 = str22;
                                                                            Integer numValueOf11 = Integer.valueOf(iIntValue);
                                                                            com.google.android.gms.internal.measurement.zzgd zzgdVar3 = (com.google.android.gms.internal.measurement.zzgd) map2.get(numValueOf11);
                                                                            if (!zZzs2) {
                                                                            }
                                                                            this.zzc.put(Integer.valueOf(iIntValue), new zzt(this, this.zza, zzgdVar3, bitSet, bitSet2, arrayMap, arrayMap10, null));
                                                                            map2 = map2;
                                                                            str23 = str27;
                                                                            map = map;
                                                                            map3 = map6;
                                                                            str22 = str28;
                                                                        }
                                                                        String str29 = str23;
                                                                        str7 = str22;
                                                                        str8 = str3;
                                                                        str9 = str24;
                                                                        zzuVar = null;
                                                                        if (!list.isEmpty()) {
                                                                            zzvVar = new zzv(this, zzuVar);
                                                                            arrayMap4 = new ArrayMap();
                                                                            it4 = list.iterator();
                                                                            while (it4.hasNext()) {
                                                                                next2 = it4.next();
                                                                                zzfoVarZza = zzvVar.zza(this.zza, next2);
                                                                                if (zzfoVarZza != null) {
                                                                                    zzajVarZzi2 = this.zzf.zzi();
                                                                                    str10 = this.zza;
                                                                                    strZzh = zzfoVarZza.zzh();
                                                                                    zzapVarZzn = zzajVarZzi2.zzn(str10, next2.zzh());
                                                                                    if (zzapVarZzn == null) {
                                                                                        zzajVarZzi2.zzs.zzay().zzk().zzc("Event aggregate wasn't created during raw event logging. appId, event", zzel.zzn(str10), zzajVarZzi2.zzs.zzj().zzd(strZzh));
                                                                                        zzapVar = new zzap(str10, next2.zzh(), 1L, 1L, 1L, next2.zzd(), 0L, null, null, null, null);
                                                                                    } else {
                                                                                        zzapVar = new zzap(zzapVarZzn.zza, zzapVarZzn.zzb, zzapVarZzn.zzc + 1, zzapVarZzn.zzd + 1, zzapVarZzn.zze + 1, zzapVarZzn.zzf, zzapVarZzn.zzg, zzapVarZzn.zzh, zzapVarZzn.zzi, zzapVarZzn.zzj, zzapVarZzn.zzk);
                                                                                    }
                                                                                    this.zzf.zzi().zzF(zzapVar);
                                                                                    j = zzapVar.zzc;
                                                                                    strZzh2 = zzfoVarZza.zzh();
                                                                                    mapEmptyMap = (Map) arrayMap4.get(strZzh2);
                                                                                    if (mapEmptyMap == null) {
                                                                                        zzajVarZzi3 = this.zzf.zzi();
                                                                                        str13 = this.zza;
                                                                                        zzajVarZzi3.zzY();
                                                                                        zzajVarZzi3.zzg();
                                                                                        Preconditions.checkNotEmpty(str13);
                                                                                        Preconditions.checkNotEmpty(strZzh2);
                                                                                        arrayMap5 = new ArrayMap();
                                                                                        str8 = str8;
                                                                                        str9 = str9;
                                                                                        try {
                                                                                            try {
                                                                                                str11 = str20;
                                                                                                try {
                                                                                                    cursorQuery2 = zzajVarZzi3.zzh().query("event_filters", new String[]{str8, str9}, "app_id=? AND event_name=?", new String[]{str13, strZzh2}, null, null, null);
                                                                                                    try {
                                                                                                        try {
                                                                                                            if (cursorQuery2.moveToFirst()) {
                                                                                                                str9 = str9;
                                                                                                                while (true) {
                                                                                                                    try {
                                                                                                                        try {
                                                                                                                            com.google.android.gms.internal.measurement.zzej zzejVarZzaA2 = ((com.google.android.gms.internal.measurement.zzei) zzku.zzl(com.google.android.gms.internal.measurement.zzej.zzc(), cursorQuery2.getBlob(1))).zzaA();
                                                                                                                            numValueOf3 = Integer.valueOf(cursorQuery2.getInt(0));
                                                                                                                            list5 = (List) arrayMap5.get(numValueOf3);
                                                                                                                            if (list5 == null) {
                                                                                                                                str8 = str8;
                                                                                                                                try {
                                                                                                                                    arrayList2 = new ArrayList();
                                                                                                                                    arrayMap5.put(numValueOf3, arrayList2);
                                                                                                                                } catch (SQLiteException e8) {
                                                                                                                                    e = e8;
                                                                                                                                    str12 = str7;
                                                                                                                                    zzajVarZzi3.zzs.zzay().zzd().zzc(str29, zzel.zzn(str13), e);
                                                                                                                                    mapEmptyMap = Collections.emptyMap();
                                                                                                                                    if (cursorQuery2 != null) {
                                                                                                                                        cursorQuery2.close();
                                                                                                                                    }
                                                                                                                                    arrayMap4.put(strZzh2, mapEmptyMap);
                                                                                                                                    it5 = mapEmptyMap.keySet().iterator();
                                                                                                                                    while (it5.hasNext()) {
                                                                                                                                        iIntValue2 = ((Integer) it5.next()).intValue();
                                                                                                                                        set = this.zzb;
                                                                                                                                        numValueOf2 = Integer.valueOf(iIntValue2);
                                                                                                                                        if (set.contains(numValueOf2)) {
                                                                                                                                            this.zzs.zzay().zzj().zzb("Skipping failed audience ID", numValueOf2);
                                                                                                                                        } else {
                                                                                                                                            it6 = ((List) mapEmptyMap.get(numValueOf2)).iterator();
                                                                                                                                            zZzd = true;
                                                                                                                                            while (true) {
                                                                                                                                                if (!it6.hasNext()) {
                                                                                                                                                    map5 = mapEmptyMap;
                                                                                                                                                    it7 = it5;
                                                                                                                                                    break;
                                                                                                                                                }
                                                                                                                                                com.google.android.gms.internal.measurement.zzej zzejVar = (com.google.android.gms.internal.measurement.zzej) it6.next();
                                                                                                                                                zzwVar = new zzw(this, this.zza, iIntValue2, zzejVar);
                                                                                                                                                map5 = mapEmptyMap;
                                                                                                                                                it7 = it5;
                                                                                                                                                zZzd = zzwVar.zzd(this.zzd, this.zze, zzfoVarZza, j, zzapVar, zzf(iIntValue2, zzejVar.zzb()));
                                                                                                                                                if (!zZzd) {
                                                                                                                                                    this.zzb.add(Integer.valueOf(iIntValue2));
                                                                                                                                                    break;
                                                                                                                                                }
                                                                                                                                                zzd(Integer.valueOf(iIntValue2)).zzc(zzwVar);
                                                                                                                                                mapEmptyMap = map5;
                                                                                                                                                it5 = it7;
                                                                                                                                            }
                                                                                                                                            if (!zZzd) {
                                                                                                                                                this.zzb.add(Integer.valueOf(iIntValue2));
                                                                                                                                            }
                                                                                                                                            mapEmptyMap = map5;
                                                                                                                                            it5 = it7;
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    it4 = it4;
                                                                                                                                    str7 = str12;
                                                                                                                                    zzvVar = zzvVar;
                                                                                                                                    str20 = str11;
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                str8 = str8;
                                                                                                                                arrayList2 = list5;
                                                                                                                            }
                                                                                                                            arrayList2.add(zzejVarZzaA2);
                                                                                                                            str12 = str7;
                                                                                                                        } catch (IOException e9) {
                                                                                                                            str8 = str8;
                                                                                                                            str12 = str7;
                                                                                                                            zzajVarZzi3.zzs.zzay().zzd().zzc(str12, zzel.zzn(str13), e9);
                                                                                                                        }
                                                                                                                        try {
                                                                                                                            if (!cursorQuery2.moveToNext()) {
                                                                                                                                break;
                                                                                                                            }
                                                                                                                            str7 = str12;
                                                                                                                            arrayMap5 = arrayMap5;
                                                                                                                            str8 = str8;
                                                                                                                        } catch (SQLiteException e10) {
                                                                                                                            e = e10;
                                                                                                                            zzajVarZzi3.zzs.zzay().zzd().zzc(str29, zzel.zzn(str13), e);
                                                                                                                            mapEmptyMap = Collections.emptyMap();
                                                                                                                            if (cursorQuery2 != null) {
                                                                                                                                cursorQuery2.close();
                                                                                                                            }
                                                                                                                        }
                                                                                                                    } catch (SQLiteException e11) {
                                                                                                                        e = e11;
                                                                                                                        str8 = str8;
                                                                                                                        str12 = str7;
                                                                                                                        zzajVarZzi3.zzs.zzay().zzd().zzc(str29, zzel.zzn(str13), e);
                                                                                                                        mapEmptyMap = Collections.emptyMap();
                                                                                                                        if (cursorQuery2 != null) {
                                                                                                                            cursorQuery2.close();
                                                                                                                        }
                                                                                                                        arrayMap4.put(strZzh2, mapEmptyMap);
                                                                                                                        it5 = mapEmptyMap.keySet().iterator();
                                                                                                                        while (it5.hasNext()) {
                                                                                                                            iIntValue2 = ((Integer) it5.next()).intValue();
                                                                                                                            set = this.zzb;
                                                                                                                            numValueOf2 = Integer.valueOf(iIntValue2);
                                                                                                                            if (set.contains(numValueOf2)) {
                                                                                                                                this.zzs.zzay().zzj().zzb("Skipping failed audience ID", numValueOf2);
                                                                                                                            } else {
                                                                                                                                it6 = ((List) mapEmptyMap.get(numValueOf2)).iterator();
                                                                                                                                zZzd = true;
                                                                                                                                while (true) {
                                                                                                                                    if (!it6.hasNext()) {
                                                                                                                                        map5 = mapEmptyMap;
                                                                                                                                        it7 = it5;
                                                                                                                                        break;
                                                                                                                                    }
                                                                                                                                    com.google.android.gms.internal.measurement.zzej zzejVar2 = (com.google.android.gms.internal.measurement.zzej) it6.next();
                                                                                                                                    zzwVar = new zzw(this, this.zza, iIntValue2, zzejVar2);
                                                                                                                                    map5 = mapEmptyMap;
                                                                                                                                    it7 = it5;
                                                                                                                                    zZzd = zzwVar.zzd(this.zzd, this.zze, zzfoVarZza, j, zzapVar, zzf(iIntValue2, zzejVar2.zzb()));
                                                                                                                                    if (!zZzd) {
                                                                                                                                        this.zzb.add(Integer.valueOf(iIntValue2));
                                                                                                                                        break;
                                                                                                                                    }
                                                                                                                                    zzd(Integer.valueOf(iIntValue2)).zzc(zzwVar);
                                                                                                                                    mapEmptyMap = map5;
                                                                                                                                    it5 = it7;
                                                                                                                                }
                                                                                                                                if (!zZzd) {
                                                                                                                                    this.zzb.add(Integer.valueOf(iIntValue2));
                                                                                                                                }
                                                                                                                                mapEmptyMap = map5;
                                                                                                                                it5 = it7;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        it4 = it4;
                                                                                                                        str7 = str12;
                                                                                                                        zzvVar = zzvVar;
                                                                                                                        str20 = str11;
                                                                                                                    }
                                                                                                                }
                                                                                                                cursorQuery2.close();
                                                                                                                mapEmptyMap = arrayMap5;
                                                                                                            } else {
                                                                                                                str9 = str9;
                                                                                                                str8 = str8;
                                                                                                                str12 = str7;
                                                                                                                mapEmptyMap = Collections.emptyMap();
                                                                                                                cursorQuery2.close();
                                                                                                            }
                                                                                                        } catch (Throwable th) {
                                                                                                            th = th;
                                                                                                            if (cursorQuery2 != null) {
                                                                                                                cursorQuery2.close();
                                                                                                            }
                                                                                                            throw th;
                                                                                                        }
                                                                                                    } catch (SQLiteException e12) {
                                                                                                        e = e12;
                                                                                                        str9 = str9;
                                                                                                    }
                                                                                                } catch (SQLiteException e13) {
                                                                                                    e = e13;
                                                                                                    str12 = str7;
                                                                                                    cursorQuery2 = null;
                                                                                                    zzajVarZzi3.zzs.zzay().zzd().zzc(str29, zzel.zzn(str13), e);
                                                                                                    mapEmptyMap = Collections.emptyMap();
                                                                                                    if (cursorQuery2 != null) {
                                                                                                        cursorQuery2.close();
                                                                                                    }
                                                                                                    arrayMap4.put(strZzh2, mapEmptyMap);
                                                                                                    it5 = mapEmptyMap.keySet().iterator();
                                                                                                    while (it5.hasNext()) {
                                                                                                        iIntValue2 = ((Integer) it5.next()).intValue();
                                                                                                        set = this.zzb;
                                                                                                        numValueOf2 = Integer.valueOf(iIntValue2);
                                                                                                        if (set.contains(numValueOf2)) {
                                                                                                            this.zzs.zzay().zzj().zzb("Skipping failed audience ID", numValueOf2);
                                                                                                        } else {
                                                                                                            it6 = ((List) mapEmptyMap.get(numValueOf2)).iterator();
                                                                                                            zZzd = true;
                                                                                                            while (true) {
                                                                                                                if (!it6.hasNext()) {
                                                                                                                    map5 = mapEmptyMap;
                                                                                                                    it7 = it5;
                                                                                                                    break;
                                                                                                                }
                                                                                                                com.google.android.gms.internal.measurement.zzej zzejVar3 = (com.google.android.gms.internal.measurement.zzej) it6.next();
                                                                                                                zzwVar = new zzw(this, this.zza, iIntValue2, zzejVar3);
                                                                                                                map5 = mapEmptyMap;
                                                                                                                it7 = it5;
                                                                                                                zZzd = zzwVar.zzd(this.zzd, this.zze, zzfoVarZza, j, zzapVar, zzf(iIntValue2, zzejVar3.zzb()));
                                                                                                                if (!zZzd) {
                                                                                                                    this.zzb.add(Integer.valueOf(iIntValue2));
                                                                                                                    break;
                                                                                                                }
                                                                                                                zzd(Integer.valueOf(iIntValue2)).zzc(zzwVar);
                                                                                                                mapEmptyMap = map5;
                                                                                                                it5 = it7;
                                                                                                            }
                                                                                                            if (!zZzd) {
                                                                                                                this.zzb.add(Integer.valueOf(iIntValue2));
                                                                                                            }
                                                                                                            mapEmptyMap = map5;
                                                                                                            it5 = it7;
                                                                                                        }
                                                                                                    }
                                                                                                    it4 = it4;
                                                                                                    str7 = str12;
                                                                                                    zzvVar = zzvVar;
                                                                                                    str20 = str11;
                                                                                                }
                                                                                            } catch (Throwable th2) {
                                                                                                th = th2;
                                                                                                cursorQuery2 = null;
                                                                                            }
                                                                                        } catch (SQLiteException e14) {
                                                                                            e = e14;
                                                                                            str11 = str20;
                                                                                        }
                                                                                        arrayMap4.put(strZzh2, mapEmptyMap);
                                                                                    } else {
                                                                                        str11 = str20;
                                                                                        str12 = str7;
                                                                                    }
                                                                                    it5 = mapEmptyMap.keySet().iterator();
                                                                                    while (it5.hasNext()) {
                                                                                        iIntValue2 = ((Integer) it5.next()).intValue();
                                                                                        set = this.zzb;
                                                                                        numValueOf2 = Integer.valueOf(iIntValue2);
                                                                                        if (set.contains(numValueOf2)) {
                                                                                            this.zzs.zzay().zzj().zzb("Skipping failed audience ID", numValueOf2);
                                                                                        } else {
                                                                                            it6 = ((List) mapEmptyMap.get(numValueOf2)).iterator();
                                                                                            zZzd = true;
                                                                                            while (true) {
                                                                                                if (!it6.hasNext()) {
                                                                                                    map5 = mapEmptyMap;
                                                                                                    it7 = it5;
                                                                                                    break;
                                                                                                }
                                                                                                com.google.android.gms.internal.measurement.zzej zzejVar4 = (com.google.android.gms.internal.measurement.zzej) it6.next();
                                                                                                zzwVar = new zzw(this, this.zza, iIntValue2, zzejVar4);
                                                                                                map5 = mapEmptyMap;
                                                                                                it7 = it5;
                                                                                                zZzd = zzwVar.zzd(this.zzd, this.zze, zzfoVarZza, j, zzapVar, zzf(iIntValue2, zzejVar4.zzb()));
                                                                                                if (!zZzd) {
                                                                                                    this.zzb.add(Integer.valueOf(iIntValue2));
                                                                                                    break;
                                                                                                }
                                                                                                zzd(Integer.valueOf(iIntValue2)).zzc(zzwVar);
                                                                                                mapEmptyMap = map5;
                                                                                                it5 = it7;
                                                                                            }
                                                                                            if (!zZzd) {
                                                                                                this.zzb.add(Integer.valueOf(iIntValue2));
                                                                                            }
                                                                                            mapEmptyMap = map5;
                                                                                            it5 = it7;
                                                                                        }
                                                                                    }
                                                                                    it4 = it4;
                                                                                    str7 = str12;
                                                                                    zzvVar = zzvVar;
                                                                                    str20 = str11;
                                                                                }
                                                                            }
                                                                        }
                                                                        str14 = str20;
                                                                        if (!list2.isEmpty()) {
                                                                            arrayMap6 = new ArrayMap();
                                                                            it8 = list2.iterator();
                                                                            while (it8.hasNext()) {
                                                                                com.google.android.gms.internal.measurement.zzgh next3 = it8.next();
                                                                                strZzf = next3.zzf();
                                                                                mapEmptyMap2 = (Map) arrayMap6.get(strZzf);
                                                                                if (mapEmptyMap2 == null) {
                                                                                    zzajVarZzi4 = this.zzf.zzi();
                                                                                    str17 = this.zza;
                                                                                    zzajVarZzi4.zzY();
                                                                                    zzajVarZzi4.zzg();
                                                                                    Preconditions.checkNotEmpty(str17);
                                                                                    Preconditions.checkNotEmpty(strZzf);
                                                                                    arrayMap7 = new ArrayMap();
                                                                                    str15 = str8;
                                                                                    str16 = str9;
                                                                                    try {
                                                                                        cursorQuery3 = zzajVarZzi4.zzh().query("property_filters", new String[]{str15, str16}, "app_id=? AND property_name=?", new String[]{str17, strZzf}, null, null, null);
                                                                                        try {
                                                                                            try {
                                                                                                if (cursorQuery3.moveToFirst()) {
                                                                                                    while (true) {
                                                                                                        try {
                                                                                                            com.google.android.gms.internal.measurement.zzes zzesVarZzaA = ((com.google.android.gms.internal.measurement.zzer) zzku.zzl(com.google.android.gms.internal.measurement.zzes.zzc(), cursorQuery3.getBlob(1))).zzaA();
                                                                                                            numValueOf7 = Integer.valueOf(cursorQuery3.getInt(0));
                                                                                                            list6 = (List) arrayMap7.get(numValueOf7);
                                                                                                            if (list6 == null) {
                                                                                                                arrayList3 = new ArrayList();
                                                                                                                arrayMap7.put(numValueOf7, arrayList3);
                                                                                                            } else {
                                                                                                                arrayList3 = list6;
                                                                                                            }
                                                                                                            arrayList3.add(zzesVarZzaA);
                                                                                                        } catch (IOException e15) {
                                                                                                            zzajVarZzi4.zzs.zzay().zzd().zzc("Failed to merge filter", zzel.zzn(str17), e15);
                                                                                                        }
                                                                                                        try {
                                                                                                            if (!cursorQuery3.moveToNext()) {
                                                                                                                break;
                                                                                                            }
                                                                                                            it8 = it8;
                                                                                                        } catch (SQLiteException e16) {
                                                                                                            e = e16;
                                                                                                            zzajVarZzi4.zzs.zzay().zzd().zzc(str29, zzel.zzn(str17), e);
                                                                                                            mapEmptyMap2 = Collections.emptyMap();
                                                                                                            if (cursorQuery3 != null) {
                                                                                                                cursorQuery3.close();
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                    cursorQuery3.close();
                                                                                                    mapEmptyMap2 = arrayMap7;
                                                                                                } else {
                                                                                                    it8 = it8;
                                                                                                    mapEmptyMap2 = Collections.emptyMap();
                                                                                                    cursorQuery3.close();
                                                                                                }
                                                                                            } catch (Throwable th3) {
                                                                                                th = th3;
                                                                                                if (cursorQuery3 != null) {
                                                                                                    cursorQuery3.close();
                                                                                                }
                                                                                                throw th;
                                                                                            }
                                                                                        } catch (SQLiteException e17) {
                                                                                            e = e17;
                                                                                            it8 = it8;
                                                                                        }
                                                                                    } catch (SQLiteException e18) {
                                                                                        e = e18;
                                                                                        it8 = it8;
                                                                                        cursorQuery3 = null;
                                                                                    } catch (Throwable th4) {
                                                                                        th = th4;
                                                                                        cursorQuery3 = null;
                                                                                    }
                                                                                    arrayMap6.put(strZzf, mapEmptyMap2);
                                                                                } else {
                                                                                    it8 = it8;
                                                                                    str15 = str8;
                                                                                    str16 = str9;
                                                                                }
                                                                                it9 = mapEmptyMap2.keySet().iterator();
                                                                                while (it9.hasNext()) {
                                                                                    iIntValue3 = ((Integer) it9.next()).intValue();
                                                                                    set2 = this.zzb;
                                                                                    numValueOf4 = Integer.valueOf(iIntValue3);
                                                                                    if (set2.contains(numValueOf4)) {
                                                                                        this.zzs.zzay().zzj().zzb("Skipping failed audience ID", numValueOf4);
                                                                                        break;
                                                                                    }
                                                                                    it10 = ((List) mapEmptyMap2.get(numValueOf4)).iterator();
                                                                                    zZzd2 = true;
                                                                                    while (true) {
                                                                                        if (it10.hasNext()) {
                                                                                            zzesVar = (com.google.android.gms.internal.measurement.zzes) it10.next();
                                                                                            if (Log.isLoggable(this.zzs.zzay().zzq(), 2)) {
                                                                                                zzej zzejVarZzj = this.zzs.zzay().zzj();
                                                                                                Integer numValueOf12 = Integer.valueOf(iIntValue3);
                                                                                                if (zzesVar.zzj()) {
                                                                                                    numValueOf6 = Integer.valueOf(zzesVar.zza());
                                                                                                } else {
                                                                                                    numValueOf6 = null;
                                                                                                }
                                                                                                zzejVarZzj.zzd("Evaluating filter. audience, filter, property", numValueOf12, numValueOf6, this.zzs.zzj().zzf(zzesVar.zze()));
                                                                                                this.zzs.zzay().zzj().zzb("Filter definition", this.zzf.zzu().zzp(zzesVar));
                                                                                            }
                                                                                            if (zzesVar.zzj()) {
                                                                                            }
                                                                                            zzej zzejVarZzk = this.zzs.zzay().zzk();
                                                                                            Object objZzn = zzel.zzn(this.zza);
                                                                                            if (zzesVar.zzj()) {
                                                                                                numValueOf5 = Integer.valueOf(zzesVar.zza());
                                                                                            } else {
                                                                                                numValueOf5 = null;
                                                                                            }
                                                                                            zzejVarZzk.zzc("Invalid property filter ID. appId, id", objZzn, String.valueOf(numValueOf5));
                                                                                            this.zzb.add(Integer.valueOf(iIntValue3));
                                                                                            mapEmptyMap2 = mapEmptyMap2;
                                                                                        } else {
                                                                                            mapEmptyMap2 = mapEmptyMap2;
                                                                                        }
                                                                                        if (!zZzd2) {
                                                                                            this.zzb.add(Integer.valueOf(iIntValue3));
                                                                                        }
                                                                                        mapEmptyMap2 = mapEmptyMap2;
                                                                                        zzd(Integer.valueOf(iIntValue3)).zzc(zzyVar);
                                                                                        mapEmptyMap2 = mapEmptyMap2;
                                                                                    }
                                                                                }
                                                                                it8 = it8;
                                                                                str9 = str16;
                                                                                str8 = str15;
                                                                            }
                                                                        }
                                                                        str18 = str8;
                                                                        arrayList4 = new ArrayList();
                                                                        Set<Integer> setKeySet = this.zzc.keySet();
                                                                        setKeySet.removeAll(this.zzb);
                                                                        it11 = setKeySet.iterator();
                                                                        while (it11.hasNext()) {
                                                                            int iIntValue5 = it11.next().intValue();
                                                                            Map<Integer, zzt> map7 = this.zzc;
                                                                            Integer numValueOf13 = Integer.valueOf(iIntValue5);
                                                                            zzt zztVar = map7.get(numValueOf13);
                                                                            Preconditions.checkNotNull(zztVar);
                                                                            com.google.android.gms.internal.measurement.zzfk zzfkVarZza = zztVar.zza(iIntValue5);
                                                                            arrayList4.add(zzfkVarZza);
                                                                            zzajVarZzi5 = this.zzf.zzi();
                                                                            str19 = this.zza;
                                                                            com.google.android.gms.internal.measurement.zzgd zzgdVarZzd = zzfkVarZza.zzd();
                                                                            zzajVarZzi5.zzY();
                                                                            zzajVarZzi5.zzg();
                                                                            Preconditions.checkNotEmpty(str19);
                                                                            Preconditions.checkNotNull(zzgdVarZzd);
                                                                            byte[] bArrZzbs = zzgdVarZzd.zzbs();
                                                                            contentValues = new ContentValues();
                                                                            contentValues.put("app_id", str19);
                                                                            contentValues.put(str18, numValueOf13);
                                                                            String str30 = str14;
                                                                            contentValues.put(str30, bArrZzbs);
                                                                            try {
                                                                                try {
                                                                                    if (zzajVarZzi5.zzh().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                                                                                        zzajVarZzi5.zzs.zzay().zzd().zzb("Failed to insert filter results (got -1). appId", zzel.zzn(str19));
                                                                                    }
                                                                                } catch (SQLiteException e19) {
                                                                                    e = e19;
                                                                                    zzajVarZzi5.zzs.zzay().zzd().zzc("Error storing filter results. appId", zzel.zzn(str19), e);
                                                                                }
                                                                            } catch (SQLiteException e20) {
                                                                                e = e20;
                                                                            }
                                                                            str14 = str30;
                                                                        }
                                                                        return arrayList4;
                                                                    }
                                                                } catch (Throwable th5) {
                                                                    th = th5;
                                                                    if (Zzh != 0) {
                                                                        Zzh.close();
                                                                    }
                                                                    throw th;
                                                                }
                                                            } catch (SQLiteException e21) {
                                                                e = e21;
                                                                cursorRawQuery = null;
                                                            } catch (Throwable th6) {
                                                                th = th6;
                                                                Zzh = 0;
                                                                if (Zzh != 0) {
                                                                    Zzh.close();
                                                                }
                                                                throw th;
                                                            }
                                                            cursorRawQuery.close();
                                                            it3 = map2.keySet().iterator();
                                                            while (it3.hasNext()) {
                                                                int iIntValue6 = ((Integer) it3.next()).intValue();
                                                                Integer numValueOf14 = Integer.valueOf(iIntValue6);
                                                                zzgdVar2 = (com.google.android.gms.internal.measurement.zzgd) map2.get(numValueOf14);
                                                                list4 = (List) arrayMap3.get(numValueOf14);
                                                                if (list4 != null) {
                                                                }
                                                                map4 = arrayMap3;
                                                                arrayMap2.put(numValueOf14, zzgdVar2);
                                                                arrayMap3 = map4;
                                                                it3 = it3;
                                                            }
                                                        }
                                                        map3 = arrayMap2;
                                                    } else {
                                                        map3 = map2;
                                                    }
                                                    it = hashSet.iterator();
                                                    while (it.hasNext()) {
                                                        iIntValue = ((Integer) it.next()).intValue();
                                                        zzgdVar = (com.google.android.gms.internal.measurement.zzgd) map3.get(Integer.valueOf(iIntValue));
                                                        bitSet = new BitSet();
                                                        bitSet2 = new BitSet();
                                                        arrayMap = new ArrayMap();
                                                        if (zzgdVar != null) {
                                                            while (r2.hasNext()) {
                                                                if (zzfmVar.zzh()) {
                                                                    Integer numValueOf15 = Integer.valueOf(zzfmVar.zza());
                                                                    if (zzfmVar.zzg()) {
                                                                        lValueOf = Long.valueOf(zzfmVar.zzb());
                                                                    } else {
                                                                        lValueOf = null;
                                                                    }
                                                                    arrayMap.put(numValueOf15, lValueOf);
                                                                }
                                                            }
                                                        }
                                                        ArrayMap arrayMap11 = new ArrayMap();
                                                        if (zzgdVar != null) {
                                                            it2 = zzgdVar.zzm().iterator();
                                                            while (it2.hasNext()) {
                                                                next = it2.next();
                                                                if (!next.zzi()) {
                                                                }
                                                            }
                                                        }
                                                        Map map8 = map3;
                                                        if (zzgdVar != null) {
                                                            i3 = 0;
                                                            while (i3 < zzgdVar.zzd() * 64) {
                                                                if (zzku.zzw(zzgdVar.zzn(), i3)) {
                                                                    str4 = str23;
                                                                    str5 = str22;
                                                                    this.zzs.zzay().zzj().zzc("Filter already evaluated. audience ID, filter ID", Integer.valueOf(iIntValue), Integer.valueOf(i3));
                                                                    bitSet2.set(i3);
                                                                    if (zzku.zzw(zzgdVar.zzk(), i3)) {
                                                                        bitSet.set(i3);
                                                                    }
                                                                    i3++;
                                                                    str23 = str4;
                                                                    str22 = str5;
                                                                } else {
                                                                    str4 = str23;
                                                                    str5 = str22;
                                                                }
                                                                arrayMap.remove(Integer.valueOf(i3));
                                                                i3++;
                                                                str23 = str4;
                                                                str22 = str5;
                                                            }
                                                        }
                                                        String str210 = str23;
                                                        String str211 = str22;
                                                        Integer numValueOf16 = Integer.valueOf(iIntValue);
                                                        com.google.android.gms.internal.measurement.zzgd zzgdVar4 = (com.google.android.gms.internal.measurement.zzgd) map2.get(numValueOf16);
                                                        if (!zZzs2) {
                                                        }
                                                        this.zzc.put(Integer.valueOf(iIntValue), new zzt(this, this.zza, zzgdVar4, bitSet, bitSet2, arrayMap, arrayMap11, null));
                                                        map2 = map2;
                                                        str23 = str210;
                                                        map = map;
                                                        map3 = map8;
                                                        str22 = str211;
                                                    }
                                                }
                                                String str212 = str23;
                                                str7 = str22;
                                                str8 = str3;
                                                str9 = str24;
                                                zzuVar = null;
                                                if (!list.isEmpty()) {
                                                    zzvVar = new zzv(this, zzuVar);
                                                    arrayMap4 = new ArrayMap();
                                                    it4 = list.iterator();
                                                    while (it4.hasNext()) {
                                                        next2 = it4.next();
                                                        zzfoVarZza = zzvVar.zza(this.zza, next2);
                                                        if (zzfoVarZza != null) {
                                                            zzajVarZzi2 = this.zzf.zzi();
                                                            str10 = this.zza;
                                                            strZzh = zzfoVarZza.zzh();
                                                            zzapVarZzn = zzajVarZzi2.zzn(str10, next2.zzh());
                                                            if (zzapVarZzn == null) {
                                                                zzajVarZzi2.zzs.zzay().zzk().zzc("Event aggregate wasn't created during raw event logging. appId, event", zzel.zzn(str10), zzajVarZzi2.zzs.zzj().zzd(strZzh));
                                                                zzapVar = new zzap(str10, next2.zzh(), 1L, 1L, 1L, next2.zzd(), 0L, null, null, null, null);
                                                            } else {
                                                                zzapVar = new zzap(zzapVarZzn.zza, zzapVarZzn.zzb, zzapVarZzn.zzc + 1, zzapVarZzn.zzd + 1, zzapVarZzn.zze + 1, zzapVarZzn.zzf, zzapVarZzn.zzg, zzapVarZzn.zzh, zzapVarZzn.zzi, zzapVarZzn.zzj, zzapVarZzn.zzk);
                                                            }
                                                            this.zzf.zzi().zzF(zzapVar);
                                                            j = zzapVar.zzc;
                                                            strZzh2 = zzfoVarZza.zzh();
                                                            mapEmptyMap = (Map) arrayMap4.get(strZzh2);
                                                            if (mapEmptyMap == null) {
                                                                zzajVarZzi3 = this.zzf.zzi();
                                                                str13 = this.zza;
                                                                zzajVarZzi3.zzY();
                                                                zzajVarZzi3.zzg();
                                                                Preconditions.checkNotEmpty(str13);
                                                                Preconditions.checkNotEmpty(strZzh2);
                                                                arrayMap5 = new ArrayMap();
                                                                str8 = str8;
                                                                str9 = str9;
                                                                str11 = str20;
                                                                cursorQuery2 = zzajVarZzi3.zzh().query("event_filters", new String[]{str8, str9}, "app_id=? AND event_name=?", new String[]{str13, strZzh2}, null, null, null);
                                                                if (cursorQuery2.moveToFirst()) {
                                                                    str9 = str9;
                                                                    while (true) {
                                                                        com.google.android.gms.internal.measurement.zzej zzejVarZzaA3 = ((com.google.android.gms.internal.measurement.zzei) zzku.zzl(com.google.android.gms.internal.measurement.zzej.zzc(), cursorQuery2.getBlob(1))).zzaA();
                                                                        numValueOf3 = Integer.valueOf(cursorQuery2.getInt(0));
                                                                        list5 = (List) arrayMap5.get(numValueOf3);
                                                                        if (list5 == null) {
                                                                            str8 = str8;
                                                                            arrayList2 = new ArrayList();
                                                                            arrayMap5.put(numValueOf3, arrayList2);
                                                                        } else {
                                                                            str8 = str8;
                                                                            arrayList2 = list5;
                                                                        }
                                                                        arrayList2.add(zzejVarZzaA3);
                                                                        str12 = str7;
                                                                        if (!cursorQuery2.moveToNext()) {
                                                                            break;
                                                                            break;
                                                                        }
                                                                        str7 = str12;
                                                                        arrayMap5 = arrayMap5;
                                                                        str8 = str8;
                                                                    }
                                                                    cursorQuery2.close();
                                                                    mapEmptyMap = arrayMap5;
                                                                } else {
                                                                    str9 = str9;
                                                                    str8 = str8;
                                                                    str12 = str7;
                                                                    mapEmptyMap = Collections.emptyMap();
                                                                    cursorQuery2.close();
                                                                }
                                                                arrayMap4.put(strZzh2, mapEmptyMap);
                                                            } else {
                                                                str11 = str20;
                                                                str12 = str7;
                                                            }
                                                            it5 = mapEmptyMap.keySet().iterator();
                                                            while (it5.hasNext()) {
                                                                iIntValue2 = ((Integer) it5.next()).intValue();
                                                                set = this.zzb;
                                                                numValueOf2 = Integer.valueOf(iIntValue2);
                                                                if (set.contains(numValueOf2)) {
                                                                    this.zzs.zzay().zzj().zzb("Skipping failed audience ID", numValueOf2);
                                                                } else {
                                                                    it6 = ((List) mapEmptyMap.get(numValueOf2)).iterator();
                                                                    zZzd = true;
                                                                    while (true) {
                                                                        if (!it6.hasNext()) {
                                                                            map5 = mapEmptyMap;
                                                                            it7 = it5;
                                                                            break;
                                                                        }
                                                                        com.google.android.gms.internal.measurement.zzej zzejVar5 = (com.google.android.gms.internal.measurement.zzej) it6.next();
                                                                        zzwVar = new zzw(this, this.zza, iIntValue2, zzejVar5);
                                                                        map5 = mapEmptyMap;
                                                                        it7 = it5;
                                                                        zZzd = zzwVar.zzd(this.zzd, this.zze, zzfoVarZza, j, zzapVar, zzf(iIntValue2, zzejVar5.zzb()));
                                                                        if (!zZzd) {
                                                                            this.zzb.add(Integer.valueOf(iIntValue2));
                                                                            break;
                                                                        }
                                                                        zzd(Integer.valueOf(iIntValue2)).zzc(zzwVar);
                                                                        mapEmptyMap = map5;
                                                                        it5 = it7;
                                                                    }
                                                                    if (!zZzd) {
                                                                        this.zzb.add(Integer.valueOf(iIntValue2));
                                                                    }
                                                                    mapEmptyMap = map5;
                                                                    it5 = it7;
                                                                }
                                                            }
                                                            it4 = it4;
                                                            str7 = str12;
                                                            zzvVar = zzvVar;
                                                            str20 = str11;
                                                        }
                                                    }
                                                }
                                                str14 = str20;
                                                if (!list2.isEmpty()) {
                                                    arrayMap6 = new ArrayMap();
                                                    it8 = list2.iterator();
                                                    while (it8.hasNext()) {
                                                        com.google.android.gms.internal.measurement.zzgh next4 = it8.next();
                                                        strZzf = next4.zzf();
                                                        mapEmptyMap2 = (Map) arrayMap6.get(strZzf);
                                                        if (mapEmptyMap2 == null) {
                                                            zzajVarZzi4 = this.zzf.zzi();
                                                            str17 = this.zza;
                                                            zzajVarZzi4.zzY();
                                                            zzajVarZzi4.zzg();
                                                            Preconditions.checkNotEmpty(str17);
                                                            Preconditions.checkNotEmpty(strZzf);
                                                            arrayMap7 = new ArrayMap();
                                                            str15 = str8;
                                                            str16 = str9;
                                                            cursorQuery3 = zzajVarZzi4.zzh().query("property_filters", new String[]{str15, str16}, "app_id=? AND property_name=?", new String[]{str17, strZzf}, null, null, null);
                                                            if (cursorQuery3.moveToFirst()) {
                                                                while (true) {
                                                                    com.google.android.gms.internal.measurement.zzes zzesVarZzaA2 = ((com.google.android.gms.internal.measurement.zzer) zzku.zzl(com.google.android.gms.internal.measurement.zzes.zzc(), cursorQuery3.getBlob(1))).zzaA();
                                                                    numValueOf7 = Integer.valueOf(cursorQuery3.getInt(0));
                                                                    list6 = (List) arrayMap7.get(numValueOf7);
                                                                    if (list6 == null) {
                                                                        arrayList3 = new ArrayList();
                                                                        arrayMap7.put(numValueOf7, arrayList3);
                                                                    } else {
                                                                        arrayList3 = list6;
                                                                    }
                                                                    arrayList3.add(zzesVarZzaA2);
                                                                    if (!cursorQuery3.moveToNext()) {
                                                                        break;
                                                                        break;
                                                                    }
                                                                    it8 = it8;
                                                                }
                                                                cursorQuery3.close();
                                                                mapEmptyMap2 = arrayMap7;
                                                            } else {
                                                                it8 = it8;
                                                                mapEmptyMap2 = Collections.emptyMap();
                                                                cursorQuery3.close();
                                                            }
                                                            arrayMap6.put(strZzf, mapEmptyMap2);
                                                        } else {
                                                            it8 = it8;
                                                            str15 = str8;
                                                            str16 = str9;
                                                        }
                                                        it9 = mapEmptyMap2.keySet().iterator();
                                                        while (it9.hasNext()) {
                                                            iIntValue3 = ((Integer) it9.next()).intValue();
                                                            set2 = this.zzb;
                                                            numValueOf4 = Integer.valueOf(iIntValue3);
                                                            if (set2.contains(numValueOf4)) {
                                                                this.zzs.zzay().zzj().zzb("Skipping failed audience ID", numValueOf4);
                                                                break;
                                                                break;
                                                            }
                                                            it10 = ((List) mapEmptyMap2.get(numValueOf4)).iterator();
                                                            zZzd2 = true;
                                                            while (true) {
                                                                if (it10.hasNext()) {
                                                                    zzesVar = (com.google.android.gms.internal.measurement.zzes) it10.next();
                                                                    if (Log.isLoggable(this.zzs.zzay().zzq(), 2)) {
                                                                        zzej zzejVarZzj2 = this.zzs.zzay().zzj();
                                                                        Integer numValueOf17 = Integer.valueOf(iIntValue3);
                                                                        if (zzesVar.zzj()) {
                                                                            numValueOf6 = Integer.valueOf(zzesVar.zza());
                                                                        } else {
                                                                            numValueOf6 = null;
                                                                        }
                                                                        zzejVarZzj2.zzd("Evaluating filter. audience, filter, property", numValueOf17, numValueOf6, this.zzs.zzj().zzf(zzesVar.zze()));
                                                                        this.zzs.zzay().zzj().zzb("Filter definition", this.zzf.zzu().zzp(zzesVar));
                                                                    }
                                                                    if (zzesVar.zzj()) {
                                                                    }
                                                                    zzej zzejVarZzk2 = this.zzs.zzay().zzk();
                                                                    Object objZzn2 = zzel.zzn(this.zza);
                                                                    if (zzesVar.zzj()) {
                                                                        numValueOf5 = Integer.valueOf(zzesVar.zza());
                                                                    } else {
                                                                        numValueOf5 = null;
                                                                    }
                                                                    zzejVarZzk2.zzc("Invalid property filter ID. appId, id", objZzn2, String.valueOf(numValueOf5));
                                                                    this.zzb.add(Integer.valueOf(iIntValue3));
                                                                    mapEmptyMap2 = mapEmptyMap2;
                                                                } else {
                                                                    mapEmptyMap2 = mapEmptyMap2;
                                                                }
                                                                if (!zZzd2) {
                                                                    this.zzb.add(Integer.valueOf(iIntValue3));
                                                                }
                                                                mapEmptyMap2 = mapEmptyMap2;
                                                                zzd(Integer.valueOf(iIntValue3)).zzc(zzyVar);
                                                                mapEmptyMap2 = mapEmptyMap2;
                                                            }
                                                        }
                                                        it8 = it8;
                                                        str9 = str16;
                                                        str8 = str15;
                                                    }
                                                }
                                                str18 = str8;
                                                arrayList4 = new ArrayList();
                                                Set<Integer> setKeySet2 = this.zzc.keySet();
                                                setKeySet2.removeAll(this.zzb);
                                                it11 = setKeySet2.iterator();
                                                while (it11.hasNext()) {
                                                    int iIntValue7 = it11.next().intValue();
                                                    Map<Integer, zzt> map9 = this.zzc;
                                                    Integer numValueOf18 = Integer.valueOf(iIntValue7);
                                                    zzt zztVar2 = map9.get(numValueOf18);
                                                    Preconditions.checkNotNull(zztVar2);
                                                    com.google.android.gms.internal.measurement.zzfk zzfkVarZza2 = zztVar2.zza(iIntValue7);
                                                    arrayList4.add(zzfkVarZza2);
                                                    zzajVarZzi5 = this.zzf.zzi();
                                                    str19 = this.zza;
                                                    com.google.android.gms.internal.measurement.zzgd zzgdVarZzd2 = zzfkVarZza2.zzd();
                                                    zzajVarZzi5.zzY();
                                                    zzajVarZzi5.zzg();
                                                    Preconditions.checkNotEmpty(str19);
                                                    Preconditions.checkNotNull(zzgdVarZzd2);
                                                    byte[] bArrZzbs2 = zzgdVarZzd2.zzbs();
                                                    contentValues = new ContentValues();
                                                    contentValues.put("app_id", str19);
                                                    contentValues.put(str18, numValueOf18);
                                                    String str31 = str14;
                                                    contentValues.put(str31, bArrZzbs2);
                                                    if (zzajVarZzi5.zzh().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                                                        zzajVarZzi5.zzs.zzay().zzd().zzb("Failed to insert filter results (got -1). appId", zzel.zzn(str19));
                                                    }
                                                    str14 = str31;
                                                }
                                                return arrayList4;
                                            }
                                        }
                                        try {
                                            if (!cursorQuery.moveToNext()) {
                                                break;
                                            }
                                            arrayMap8 = arrayMap8;
                                            str25 = str3;
                                            str24 = str24;
                                        } catch (SQLiteException e22) {
                                            e = e22;
                                            zzajVarZzi.zzs.zzay().zzd().zzc("Database error querying filter results. appId", zzel.zzn(str2), e);
                                            Map mapEmptyMap5 = Collections.emptyMap();
                                            if (cursorQuery != null) {
                                                cursorQuery.close();
                                            }
                                            map2 = mapEmptyMap5;
                                        }
                                    }
                                    cursorQuery.close();
                                    map2 = arrayMap8;
                                } else {
                                    Map mapEmptyMap6 = Collections.emptyMap();
                                    cursorQuery.close();
                                    map2 = mapEmptyMap6;
                                    str3 = "audience_id";
                                    str24 = "data";
                                }
                            } catch (SQLiteException e23) {
                                e = e23;
                                str3 = "audience_id";
                            }
                            if (!map2.isEmpty()) {
                                HashSet hashSet2 = new HashSet(map2.keySet());
                                if (z) {
                                    str6 = this.zza;
                                    Preconditions.checkNotEmpty(str6);
                                    Preconditions.checkNotNull(map2);
                                    arrayMap2 = new ArrayMap();
                                    if (!map2.isEmpty()) {
                                        zzaj zzajVarZzi9 = this.zzf.zzi();
                                        zzajVarZzi9.zzY();
                                        zzajVarZzi9.zzg();
                                        Preconditions.checkNotEmpty(str6);
                                        arrayMap3 = new ArrayMap();
                                        Zzh = zzajVarZzi9.zzh();
                                        cursorRawQuery = Zzh.rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str6, str6});
                                        if (cursorRawQuery.moveToFirst()) {
                                            do {
                                                numValueOf = Integer.valueOf(cursorRawQuery.getInt(0));
                                                arrayList = (List) arrayMap3.get(numValueOf);
                                                if (arrayList == null) {
                                                    arrayList = new ArrayList();
                                                    arrayMap3.put(numValueOf, arrayList);
                                                }
                                                arrayList.add(Integer.valueOf(cursorRawQuery.getInt(1)));
                                            } while (cursorRawQuery.moveToNext());
                                        } else {
                                            arrayMap3 = Collections.emptyMap();
                                        }
                                        cursorRawQuery.close();
                                        it3 = map2.keySet().iterator();
                                        while (it3.hasNext()) {
                                            int iIntValue8 = ((Integer) it3.next()).intValue();
                                            Integer numValueOf19 = Integer.valueOf(iIntValue8);
                                            zzgdVar2 = (com.google.android.gms.internal.measurement.zzgd) map2.get(numValueOf19);
                                            list4 = (List) arrayMap3.get(numValueOf19);
                                            if (list4 != null) {
                                            }
                                            map4 = arrayMap3;
                                            arrayMap2.put(numValueOf19, zzgdVar2);
                                            arrayMap3 = map4;
                                            it3 = it3;
                                        }
                                    }
                                    map3 = arrayMap2;
                                } else {
                                    map3 = map2;
                                }
                                it = hashSet2.iterator();
                                while (it.hasNext()) {
                                    iIntValue = ((Integer) it.next()).intValue();
                                    zzgdVar = (com.google.android.gms.internal.measurement.zzgd) map3.get(Integer.valueOf(iIntValue));
                                    bitSet = new BitSet();
                                    bitSet2 = new BitSet();
                                    arrayMap = new ArrayMap();
                                    if (zzgdVar != null) {
                                        while (r2.hasNext()) {
                                            if (zzfmVar.zzh()) {
                                                Integer numValueOf110 = Integer.valueOf(zzfmVar.zza());
                                                if (zzfmVar.zzg()) {
                                                    lValueOf = Long.valueOf(zzfmVar.zzb());
                                                } else {
                                                    lValueOf = null;
                                                }
                                                arrayMap.put(numValueOf110, lValueOf);
                                            }
                                        }
                                    }
                                    ArrayMap arrayMap12 = new ArrayMap();
                                    if (zzgdVar != null) {
                                        it2 = zzgdVar.zzm().iterator();
                                        while (it2.hasNext()) {
                                            next = it2.next();
                                            if (!next.zzi()) {
                                            }
                                        }
                                    }
                                    Map map10 = map3;
                                    if (zzgdVar != null) {
                                        i3 = 0;
                                        while (i3 < zzgdVar.zzd() * 64) {
                                            if (zzku.zzw(zzgdVar.zzn(), i3)) {
                                                str4 = str23;
                                                str5 = str22;
                                                this.zzs.zzay().zzj().zzc("Filter already evaluated. audience ID, filter ID", Integer.valueOf(iIntValue), Integer.valueOf(i3));
                                                bitSet2.set(i3);
                                                if (zzku.zzw(zzgdVar.zzk(), i3)) {
                                                    bitSet.set(i3);
                                                }
                                                i3++;
                                                str23 = str4;
                                                str22 = str5;
                                            } else {
                                                str4 = str23;
                                                str5 = str22;
                                            }
                                            arrayMap.remove(Integer.valueOf(i3));
                                            i3++;
                                            str23 = str4;
                                            str22 = str5;
                                        }
                                    }
                                    String str213 = str23;
                                    String str214 = str22;
                                    Integer numValueOf111 = Integer.valueOf(iIntValue);
                                    com.google.android.gms.internal.measurement.zzgd zzgdVar5 = (com.google.android.gms.internal.measurement.zzgd) map2.get(numValueOf111);
                                    if (!zZzs2) {
                                    }
                                    this.zzc.put(Integer.valueOf(iIntValue), new zzt(this, this.zza, zzgdVar5, bitSet, bitSet2, arrayMap, arrayMap12, null));
                                    map2 = map2;
                                    str23 = str213;
                                    map = map;
                                    map3 = map10;
                                    str22 = str214;
                                }
                            }
                            String str215 = str23;
                            str7 = str22;
                            str8 = str3;
                            str9 = str24;
                            zzuVar = null;
                            if (!list.isEmpty()) {
                                zzvVar = new zzv(this, zzuVar);
                                arrayMap4 = new ArrayMap();
                                it4 = list.iterator();
                                while (it4.hasNext()) {
                                    next2 = it4.next();
                                    zzfoVarZza = zzvVar.zza(this.zza, next2);
                                    if (zzfoVarZza != null) {
                                        zzajVarZzi2 = this.zzf.zzi();
                                        str10 = this.zza;
                                        strZzh = zzfoVarZza.zzh();
                                        zzapVarZzn = zzajVarZzi2.zzn(str10, next2.zzh());
                                        if (zzapVarZzn == null) {
                                            zzajVarZzi2.zzs.zzay().zzk().zzc("Event aggregate wasn't created during raw event logging. appId, event", zzel.zzn(str10), zzajVarZzi2.zzs.zzj().zzd(strZzh));
                                            zzapVar = new zzap(str10, next2.zzh(), 1L, 1L, 1L, next2.zzd(), 0L, null, null, null, null);
                                        } else {
                                            zzapVar = new zzap(zzapVarZzn.zza, zzapVarZzn.zzb, zzapVarZzn.zzc + 1, zzapVarZzn.zzd + 1, zzapVarZzn.zze + 1, zzapVarZzn.zzf, zzapVarZzn.zzg, zzapVarZzn.zzh, zzapVarZzn.zzi, zzapVarZzn.zzj, zzapVarZzn.zzk);
                                        }
                                        this.zzf.zzi().zzF(zzapVar);
                                        j = zzapVar.zzc;
                                        strZzh2 = zzfoVarZza.zzh();
                                        mapEmptyMap = (Map) arrayMap4.get(strZzh2);
                                        if (mapEmptyMap == null) {
                                            zzajVarZzi3 = this.zzf.zzi();
                                            str13 = this.zza;
                                            zzajVarZzi3.zzY();
                                            zzajVarZzi3.zzg();
                                            Preconditions.checkNotEmpty(str13);
                                            Preconditions.checkNotEmpty(strZzh2);
                                            arrayMap5 = new ArrayMap();
                                            str8 = str8;
                                            str9 = str9;
                                            str11 = str20;
                                            cursorQuery2 = zzajVarZzi3.zzh().query("event_filters", new String[]{str8, str9}, "app_id=? AND event_name=?", new String[]{str13, strZzh2}, null, null, null);
                                            if (cursorQuery2.moveToFirst()) {
                                                str9 = str9;
                                                while (true) {
                                                    com.google.android.gms.internal.measurement.zzej zzejVarZzaA4 = ((com.google.android.gms.internal.measurement.zzei) zzku.zzl(com.google.android.gms.internal.measurement.zzej.zzc(), cursorQuery2.getBlob(1))).zzaA();
                                                    numValueOf3 = Integer.valueOf(cursorQuery2.getInt(0));
                                                    list5 = (List) arrayMap5.get(numValueOf3);
                                                    if (list5 == null) {
                                                        str8 = str8;
                                                        arrayList2 = new ArrayList();
                                                        arrayMap5.put(numValueOf3, arrayList2);
                                                    } else {
                                                        str8 = str8;
                                                        arrayList2 = list5;
                                                    }
                                                    arrayList2.add(zzejVarZzaA4);
                                                    str12 = str7;
                                                    if (!cursorQuery2.moveToNext()) {
                                                        break;
                                                        break;
                                                    }
                                                    str7 = str12;
                                                    arrayMap5 = arrayMap5;
                                                    str8 = str8;
                                                }
                                                cursorQuery2.close();
                                                mapEmptyMap = arrayMap5;
                                            } else {
                                                str9 = str9;
                                                str8 = str8;
                                                str12 = str7;
                                                mapEmptyMap = Collections.emptyMap();
                                                cursorQuery2.close();
                                            }
                                            arrayMap4.put(strZzh2, mapEmptyMap);
                                        } else {
                                            str11 = str20;
                                            str12 = str7;
                                        }
                                        it5 = mapEmptyMap.keySet().iterator();
                                        while (it5.hasNext()) {
                                            iIntValue2 = ((Integer) it5.next()).intValue();
                                            set = this.zzb;
                                            numValueOf2 = Integer.valueOf(iIntValue2);
                                            if (set.contains(numValueOf2)) {
                                                this.zzs.zzay().zzj().zzb("Skipping failed audience ID", numValueOf2);
                                            } else {
                                                it6 = ((List) mapEmptyMap.get(numValueOf2)).iterator();
                                                zZzd = true;
                                                while (true) {
                                                    if (!it6.hasNext()) {
                                                        map5 = mapEmptyMap;
                                                        it7 = it5;
                                                        break;
                                                    }
                                                    com.google.android.gms.internal.measurement.zzej zzejVar6 = (com.google.android.gms.internal.measurement.zzej) it6.next();
                                                    zzwVar = new zzw(this, this.zza, iIntValue2, zzejVar6);
                                                    map5 = mapEmptyMap;
                                                    it7 = it5;
                                                    zZzd = zzwVar.zzd(this.zzd, this.zze, zzfoVarZza, j, zzapVar, zzf(iIntValue2, zzejVar6.zzb()));
                                                    if (!zZzd) {
                                                        this.zzb.add(Integer.valueOf(iIntValue2));
                                                        break;
                                                    }
                                                    zzd(Integer.valueOf(iIntValue2)).zzc(zzwVar);
                                                    mapEmptyMap = map5;
                                                    it5 = it7;
                                                }
                                                if (!zZzd) {
                                                    this.zzb.add(Integer.valueOf(iIntValue2));
                                                }
                                                mapEmptyMap = map5;
                                                it5 = it7;
                                            }
                                        }
                                        it4 = it4;
                                        str7 = str12;
                                        zzvVar = zzvVar;
                                        str20 = str11;
                                    }
                                }
                            }
                            str14 = str20;
                            if (!list2.isEmpty()) {
                                arrayMap6 = new ArrayMap();
                                it8 = list2.iterator();
                                while (it8.hasNext()) {
                                    com.google.android.gms.internal.measurement.zzgh next5 = it8.next();
                                    strZzf = next5.zzf();
                                    mapEmptyMap2 = (Map) arrayMap6.get(strZzf);
                                    if (mapEmptyMap2 == null) {
                                        zzajVarZzi4 = this.zzf.zzi();
                                        str17 = this.zza;
                                        zzajVarZzi4.zzY();
                                        zzajVarZzi4.zzg();
                                        Preconditions.checkNotEmpty(str17);
                                        Preconditions.checkNotEmpty(strZzf);
                                        arrayMap7 = new ArrayMap();
                                        str15 = str8;
                                        str16 = str9;
                                        cursorQuery3 = zzajVarZzi4.zzh().query("property_filters", new String[]{str15, str16}, "app_id=? AND property_name=?", new String[]{str17, strZzf}, null, null, null);
                                        if (cursorQuery3.moveToFirst()) {
                                            while (true) {
                                                com.google.android.gms.internal.measurement.zzes zzesVarZzaA3 = ((com.google.android.gms.internal.measurement.zzer) zzku.zzl(com.google.android.gms.internal.measurement.zzes.zzc(), cursorQuery3.getBlob(1))).zzaA();
                                                numValueOf7 = Integer.valueOf(cursorQuery3.getInt(0));
                                                list6 = (List) arrayMap7.get(numValueOf7);
                                                if (list6 == null) {
                                                    arrayList3 = new ArrayList();
                                                    arrayMap7.put(numValueOf7, arrayList3);
                                                } else {
                                                    arrayList3 = list6;
                                                }
                                                arrayList3.add(zzesVarZzaA3);
                                                if (!cursorQuery3.moveToNext()) {
                                                    break;
                                                    break;
                                                }
                                                it8 = it8;
                                            }
                                            cursorQuery3.close();
                                            mapEmptyMap2 = arrayMap7;
                                        } else {
                                            it8 = it8;
                                            mapEmptyMap2 = Collections.emptyMap();
                                            cursorQuery3.close();
                                        }
                                        arrayMap6.put(strZzf, mapEmptyMap2);
                                    } else {
                                        it8 = it8;
                                        str15 = str8;
                                        str16 = str9;
                                    }
                                    it9 = mapEmptyMap2.keySet().iterator();
                                    while (it9.hasNext()) {
                                        iIntValue3 = ((Integer) it9.next()).intValue();
                                        set2 = this.zzb;
                                        numValueOf4 = Integer.valueOf(iIntValue3);
                                        if (set2.contains(numValueOf4)) {
                                            this.zzs.zzay().zzj().zzb("Skipping failed audience ID", numValueOf4);
                                            break;
                                            break;
                                        }
                                        it10 = ((List) mapEmptyMap2.get(numValueOf4)).iterator();
                                        zZzd2 = true;
                                        while (true) {
                                            if (it10.hasNext()) {
                                                zzesVar = (com.google.android.gms.internal.measurement.zzes) it10.next();
                                                if (Log.isLoggable(this.zzs.zzay().zzq(), 2)) {
                                                    zzej zzejVarZzj3 = this.zzs.zzay().zzj();
                                                    Integer numValueOf112 = Integer.valueOf(iIntValue3);
                                                    if (zzesVar.zzj()) {
                                                        numValueOf6 = Integer.valueOf(zzesVar.zza());
                                                    } else {
                                                        numValueOf6 = null;
                                                    }
                                                    zzejVarZzj3.zzd("Evaluating filter. audience, filter, property", numValueOf112, numValueOf6, this.zzs.zzj().zzf(zzesVar.zze()));
                                                    this.zzs.zzay().zzj().zzb("Filter definition", this.zzf.zzu().zzp(zzesVar));
                                                }
                                                if (zzesVar.zzj()) {
                                                }
                                                zzej zzejVarZzk3 = this.zzs.zzay().zzk();
                                                Object objZzn3 = zzel.zzn(this.zza);
                                                if (zzesVar.zzj()) {
                                                    numValueOf5 = Integer.valueOf(zzesVar.zza());
                                                } else {
                                                    numValueOf5 = null;
                                                }
                                                zzejVarZzk3.zzc("Invalid property filter ID. appId, id", objZzn3, String.valueOf(numValueOf5));
                                                this.zzb.add(Integer.valueOf(iIntValue3));
                                                mapEmptyMap2 = mapEmptyMap2;
                                            } else {
                                                mapEmptyMap2 = mapEmptyMap2;
                                            }
                                            if (!zZzd2) {
                                                this.zzb.add(Integer.valueOf(iIntValue3));
                                            }
                                            mapEmptyMap2 = mapEmptyMap2;
                                            zzd(Integer.valueOf(iIntValue3)).zzc(zzyVar);
                                            mapEmptyMap2 = mapEmptyMap2;
                                        }
                                    }
                                    it8 = it8;
                                    str9 = str16;
                                    str8 = str15;
                                }
                            }
                            str18 = str8;
                            arrayList4 = new ArrayList();
                            Set<Integer> setKeySet3 = this.zzc.keySet();
                            setKeySet3.removeAll(this.zzb);
                            it11 = setKeySet3.iterator();
                            while (it11.hasNext()) {
                                int iIntValue9 = it11.next().intValue();
                                Map<Integer, zzt> map11 = this.zzc;
                                Integer numValueOf113 = Integer.valueOf(iIntValue9);
                                zzt zztVar3 = map11.get(numValueOf113);
                                Preconditions.checkNotNull(zztVar3);
                                com.google.android.gms.internal.measurement.zzfk zzfkVarZza3 = zztVar3.zza(iIntValue9);
                                arrayList4.add(zzfkVarZza3);
                                zzajVarZzi5 = this.zzf.zzi();
                                str19 = this.zza;
                                com.google.android.gms.internal.measurement.zzgd zzgdVarZzd3 = zzfkVarZza3.zzd();
                                zzajVarZzi5.zzY();
                                zzajVarZzi5.zzg();
                                Preconditions.checkNotEmpty(str19);
                                Preconditions.checkNotNull(zzgdVarZzd3);
                                byte[] bArrZzbs3 = zzgdVarZzd3.zzbs();
                                contentValues = new ContentValues();
                                contentValues.put("app_id", str19);
                                contentValues.put(str18, numValueOf113);
                                String str32 = str14;
                                contentValues.put(str32, bArrZzbs3);
                                if (zzajVarZzi5.zzh().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                                    zzajVarZzi5.zzs.zzay().zzd().zzb("Failed to insert filter results (got -1). appId", zzel.zzn(str19));
                                }
                                str14 = str32;
                            }
                            return arrayList4;
                        } catch (Throwable th7) {
                            th = th7;
                            if (r6 != 0) {
                                r6.close();
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th8) {
                    th = th8;
                    if (r5 != 0) {
                        r5.close();
                    }
                    throw th;
                }
            } catch (SQLiteException e24) {
                e = e24;
                cursorQuery4 = null;
            } catch (Throwable th9) {
                th = th9;
                r5 = 0;
                if (r5 != 0) {
                    r5.close();
                }
                throw th;
            }
        } else {
            map = mapEmptyMap3;
            r6 = r8;
        }
        zzajVarZzi = this.zzf.zzi();
        str2 = this.zza;
        zzajVarZzi.zzY();
        zzajVarZzi.zzg();
        Preconditions.checkNotEmpty(str2);
        try {
            cursorQuery = zzajVarZzi.zzh().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str2}, null, null, null);
            if (cursorQuery.moveToFirst()) {
                Map mapEmptyMap7 = Collections.emptyMap();
                cursorQuery.close();
                map2 = mapEmptyMap7;
                str3 = "audience_id";
                str24 = "data";
            } else {
                arrayMap8 = new ArrayMap();
                while (true) {
                    i4 = cursorQuery.getInt(0);
                    arrayMap8.put(Integer.valueOf(i4), ((com.google.android.gms.internal.measurement.zzgc) zzku.zzl(com.google.android.gms.internal.measurement.zzgd.zzf(), cursorQuery.getBlob(1))).zzaA());
                    str3 = str25;
                    if (!cursorQuery.moveToNext()) {
                        break;
                        break;
                    }
                    arrayMap8 = arrayMap8;
                    str25 = str3;
                    str24 = str24;
                }
                cursorQuery.close();
                map2 = arrayMap8;
            }
        } catch (SQLiteException e25) {
            e = e25;
            str3 = "audience_id";
            str24 = "data";
            cursorQuery = null;
        } catch (Throwable th10) {
            th = th10;
            r6 = 0;
            if (r6 != 0) {
                r6.close();
            }
            throw th;
        }
        if (!map2.isEmpty()) {
            HashSet hashSet3 = new HashSet(map2.keySet());
            if (z) {
                str6 = this.zza;
                Preconditions.checkNotEmpty(str6);
                Preconditions.checkNotNull(map2);
                arrayMap2 = new ArrayMap();
                if (!map2.isEmpty()) {
                    zzaj zzajVarZzi10 = this.zzf.zzi();
                    zzajVarZzi10.zzY();
                    zzajVarZzi10.zzg();
                    Preconditions.checkNotEmpty(str6);
                    arrayMap3 = new ArrayMap();
                    Zzh = zzajVarZzi10.zzh();
                    cursorRawQuery = Zzh.rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str6, str6});
                    if (cursorRawQuery.moveToFirst()) {
                        do {
                            numValueOf = Integer.valueOf(cursorRawQuery.getInt(0));
                            arrayList = (List) arrayMap3.get(numValueOf);
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                arrayMap3.put(numValueOf, arrayList);
                            }
                            arrayList.add(Integer.valueOf(cursorRawQuery.getInt(1)));
                        } while (cursorRawQuery.moveToNext());
                    } else {
                        arrayMap3 = Collections.emptyMap();
                    }
                    cursorRawQuery.close();
                    it3 = map2.keySet().iterator();
                    while (it3.hasNext()) {
                        int iIntValue10 = ((Integer) it3.next()).intValue();
                        Integer numValueOf114 = Integer.valueOf(iIntValue10);
                        zzgdVar2 = (com.google.android.gms.internal.measurement.zzgd) map2.get(numValueOf114);
                        list4 = (List) arrayMap3.get(numValueOf114);
                        if (list4 != null || list4.isEmpty()) {
                            map4 = arrayMap3;
                            arrayMap2.put(numValueOf114, zzgdVar2);
                            arrayMap3 = map4;
                            it3 = it3;
                        } else {
                            map4 = arrayMap3;
                            List<Long> listZzr = this.zzf.zzu().zzr(zzgdVar2.zzk(), list4);
                            if (listZzr.isEmpty()) {
                                arrayMap3 = map4;
                            } else {
                                com.google.android.gms.internal.measurement.zzgc zzgcVarZzbv = zzgdVar2.zzbv();
                                zzgcVarZzbv.zze();
                                zzgcVarZzbv.zzb(listZzr);
                                List<Long> listZzr2 = this.zzf.zzu().zzr(zzgdVar2.zzn(), list4);
                                zzgcVarZzbv.zzf();
                                zzgcVarZzbv.zzd(listZzr2);
                                for (int i5 = 0; i5 < zzgdVar2.zza(); i5++) {
                                    if (list4.contains(Integer.valueOf(zzgdVar2.zze(i5).zza()))) {
                                        zzgcVarZzbv.zzg(i5);
                                    }
                                }
                                for (int i6 = 0; i6 < zzgdVar2.zzc(); i6++) {
                                    if (list4.contains(Integer.valueOf(zzgdVar2.zzi(i6).zzb()))) {
                                        zzgcVarZzbv.zzh(i6);
                                    }
                                }
                                arrayMap2.put(Integer.valueOf(iIntValue10), zzgcVarZzbv.zzaA());
                                arrayMap3 = map4;
                                it3 = it3;
                            }
                        }
                    }
                }
                map3 = arrayMap2;
            } else {
                map3 = map2;
            }
            it = hashSet3.iterator();
            while (it.hasNext()) {
                iIntValue = ((Integer) it.next()).intValue();
                zzgdVar = (com.google.android.gms.internal.measurement.zzgd) map3.get(Integer.valueOf(iIntValue));
                bitSet = new BitSet();
                bitSet2 = new BitSet();
                arrayMap = new ArrayMap();
                if (zzgdVar != null && zzgdVar.zza() != 0) {
                    while (r2.hasNext()) {
                        if (zzfmVar.zzh()) {
                            Integer numValueOf115 = Integer.valueOf(zzfmVar.zza());
                            if (zzfmVar.zzg()) {
                                lValueOf = Long.valueOf(zzfmVar.zzb());
                            } else {
                                lValueOf = null;
                            }
                            arrayMap.put(numValueOf115, lValueOf);
                        }
                    }
                }
                ArrayMap arrayMap13 = new ArrayMap();
                if (zzgdVar != null && zzgdVar.zzc() != 0) {
                    it2 = zzgdVar.zzm().iterator();
                    while (it2.hasNext()) {
                        next = it2.next();
                        if (!next.zzi() && next.zza() > 0) {
                            arrayMap13.put(Integer.valueOf(next.zzb()), Long.valueOf(next.zzc(next.zza() - 1)));
                            map3 = map3;
                            it2 = it2;
                        }
                    }
                }
                Map map12 = map3;
                if (zzgdVar != null) {
                    i3 = 0;
                    while (i3 < zzgdVar.zzd() * 64) {
                        if (zzku.zzw(zzgdVar.zzn(), i3)) {
                            str4 = str23;
                            str5 = str22;
                            this.zzs.zzay().zzj().zzc("Filter already evaluated. audience ID, filter ID", Integer.valueOf(iIntValue), Integer.valueOf(i3));
                            bitSet2.set(i3);
                            if (zzku.zzw(zzgdVar.zzk(), i3)) {
                                bitSet.set(i3);
                            }
                            i3++;
                            str23 = str4;
                            str22 = str5;
                        } else {
                            str4 = str23;
                            str5 = str22;
                        }
                        arrayMap.remove(Integer.valueOf(i3));
                        i3++;
                        str23 = str4;
                        str22 = str5;
                    }
                }
                String str216 = str23;
                String str217 = str22;
                Integer numValueOf116 = Integer.valueOf(iIntValue);
                com.google.android.gms.internal.measurement.zzgd zzgdVar6 = (com.google.android.gms.internal.measurement.zzgd) map2.get(numValueOf116);
                if (!zZzs2 && zZzs && (list3 = (List) map.get(numValueOf116)) != null && this.zze != null && this.zzd != null) {
                    for (com.google.android.gms.internal.measurement.zzej zzejVar7 : list3) {
                        int iZzb = zzejVar7.zzb();
                        long jLongValue = this.zze.longValue() / 1000;
                        if (zzejVar7.zzm()) {
                            jLongValue = this.zzd.longValue() / 1000;
                        }
                        Integer numValueOf20 = Integer.valueOf(iZzb);
                        if (arrayMap.containsKey(numValueOf20)) {
                            arrayMap.put(numValueOf20, Long.valueOf(jLongValue));
                        }
                        if (arrayMap13.containsKey(numValueOf20)) {
                            arrayMap13.put(numValueOf20, Long.valueOf(jLongValue));
                        }
                    }
                }
                this.zzc.put(Integer.valueOf(iIntValue), new zzt(this, this.zza, zzgdVar6, bitSet, bitSet2, arrayMap, arrayMap13, null));
                map2 = map2;
                str23 = str216;
                map = map;
                map3 = map12;
                str22 = str217;
            }
        }
        String str218 = str23;
        str7 = str22;
        str8 = str3;
        str9 = str24;
        zzuVar = null;
        if (!list.isEmpty()) {
            zzvVar = new zzv(this, zzuVar);
            arrayMap4 = new ArrayMap();
            it4 = list.iterator();
            while (it4.hasNext()) {
                next2 = it4.next();
                zzfoVarZza = zzvVar.zza(this.zza, next2);
                if (zzfoVarZza != null) {
                    zzajVarZzi2 = this.zzf.zzi();
                    str10 = this.zza;
                    strZzh = zzfoVarZza.zzh();
                    zzapVarZzn = zzajVarZzi2.zzn(str10, next2.zzh());
                    if (zzapVarZzn == null) {
                        zzajVarZzi2.zzs.zzay().zzk().zzc("Event aggregate wasn't created during raw event logging. appId, event", zzel.zzn(str10), zzajVarZzi2.zzs.zzj().zzd(strZzh));
                        zzapVar = new zzap(str10, next2.zzh(), 1L, 1L, 1L, next2.zzd(), 0L, null, null, null, null);
                    } else {
                        zzapVar = new zzap(zzapVarZzn.zza, zzapVarZzn.zzb, zzapVarZzn.zzc + 1, zzapVarZzn.zzd + 1, zzapVarZzn.zze + 1, zzapVarZzn.zzf, zzapVarZzn.zzg, zzapVarZzn.zzh, zzapVarZzn.zzi, zzapVarZzn.zzj, zzapVarZzn.zzk);
                    }
                    this.zzf.zzi().zzF(zzapVar);
                    j = zzapVar.zzc;
                    strZzh2 = zzfoVarZza.zzh();
                    mapEmptyMap = (Map) arrayMap4.get(strZzh2);
                    if (mapEmptyMap == null) {
                        zzajVarZzi3 = this.zzf.zzi();
                        str13 = this.zza;
                        zzajVarZzi3.zzY();
                        zzajVarZzi3.zzg();
                        Preconditions.checkNotEmpty(str13);
                        Preconditions.checkNotEmpty(strZzh2);
                        arrayMap5 = new ArrayMap();
                        str8 = str8;
                        str9 = str9;
                        str11 = str20;
                        cursorQuery2 = zzajVarZzi3.zzh().query("event_filters", new String[]{str8, str9}, "app_id=? AND event_name=?", new String[]{str13, strZzh2}, null, null, null);
                        if (cursorQuery2.moveToFirst()) {
                            str9 = str9;
                            while (true) {
                                com.google.android.gms.internal.measurement.zzej zzejVarZzaA5 = ((com.google.android.gms.internal.measurement.zzei) zzku.zzl(com.google.android.gms.internal.measurement.zzej.zzc(), cursorQuery2.getBlob(1))).zzaA();
                                numValueOf3 = Integer.valueOf(cursorQuery2.getInt(0));
                                list5 = (List) arrayMap5.get(numValueOf3);
                                if (list5 == null) {
                                    str8 = str8;
                                    arrayList2 = new ArrayList();
                                    arrayMap5.put(numValueOf3, arrayList2);
                                } else {
                                    str8 = str8;
                                    arrayList2 = list5;
                                }
                                arrayList2.add(zzejVarZzaA5);
                                str12 = str7;
                                if (!cursorQuery2.moveToNext()) {
                                    break;
                                    break;
                                }
                                str7 = str12;
                                arrayMap5 = arrayMap5;
                                str8 = str8;
                            }
                            cursorQuery2.close();
                            mapEmptyMap = arrayMap5;
                        } else {
                            str9 = str9;
                            str8 = str8;
                            str12 = str7;
                            mapEmptyMap = Collections.emptyMap();
                            cursorQuery2.close();
                        }
                        arrayMap4.put(strZzh2, mapEmptyMap);
                    } else {
                        str11 = str20;
                        str12 = str7;
                    }
                    it5 = mapEmptyMap.keySet().iterator();
                    while (it5.hasNext()) {
                        iIntValue2 = ((Integer) it5.next()).intValue();
                        set = this.zzb;
                        numValueOf2 = Integer.valueOf(iIntValue2);
                        if (set.contains(numValueOf2)) {
                            this.zzs.zzay().zzj().zzb("Skipping failed audience ID", numValueOf2);
                        } else {
                            it6 = ((List) mapEmptyMap.get(numValueOf2)).iterator();
                            zZzd = true;
                            while (true) {
                                if (!it6.hasNext()) {
                                    map5 = mapEmptyMap;
                                    it7 = it5;
                                    break;
                                }
                                com.google.android.gms.internal.measurement.zzej zzejVar8 = (com.google.android.gms.internal.measurement.zzej) it6.next();
                                zzwVar = new zzw(this, this.zza, iIntValue2, zzejVar8);
                                map5 = mapEmptyMap;
                                it7 = it5;
                                zZzd = zzwVar.zzd(this.zzd, this.zze, zzfoVarZza, j, zzapVar, zzf(iIntValue2, zzejVar8.zzb()));
                                if (!zZzd) {
                                    this.zzb.add(Integer.valueOf(iIntValue2));
                                    break;
                                }
                                zzd(Integer.valueOf(iIntValue2)).zzc(zzwVar);
                                mapEmptyMap = map5;
                                it5 = it7;
                            }
                            if (!zZzd) {
                                this.zzb.add(Integer.valueOf(iIntValue2));
                            }
                            mapEmptyMap = map5;
                            it5 = it7;
                        }
                    }
                    it4 = it4;
                    str7 = str12;
                    zzvVar = zzvVar;
                    str20 = str11;
                }
            }
        }
        str14 = str20;
        if (!list2.isEmpty()) {
            arrayMap6 = new ArrayMap();
            it8 = list2.iterator();
            while (it8.hasNext()) {
                com.google.android.gms.internal.measurement.zzgh next6 = it8.next();
                strZzf = next6.zzf();
                mapEmptyMap2 = (Map) arrayMap6.get(strZzf);
                if (mapEmptyMap2 == null) {
                    zzajVarZzi4 = this.zzf.zzi();
                    str17 = this.zza;
                    zzajVarZzi4.zzY();
                    zzajVarZzi4.zzg();
                    Preconditions.checkNotEmpty(str17);
                    Preconditions.checkNotEmpty(strZzf);
                    arrayMap7 = new ArrayMap();
                    str15 = str8;
                    str16 = str9;
                    cursorQuery3 = zzajVarZzi4.zzh().query("property_filters", new String[]{str15, str16}, "app_id=? AND property_name=?", new String[]{str17, strZzf}, null, null, null);
                    if (cursorQuery3.moveToFirst()) {
                        while (true) {
                            com.google.android.gms.internal.measurement.zzes zzesVarZzaA4 = ((com.google.android.gms.internal.measurement.zzer) zzku.zzl(com.google.android.gms.internal.measurement.zzes.zzc(), cursorQuery3.getBlob(1))).zzaA();
                            numValueOf7 = Integer.valueOf(cursorQuery3.getInt(0));
                            list6 = (List) arrayMap7.get(numValueOf7);
                            if (list6 == null) {
                                arrayList3 = new ArrayList();
                                arrayMap7.put(numValueOf7, arrayList3);
                            } else {
                                arrayList3 = list6;
                            }
                            arrayList3.add(zzesVarZzaA4);
                            if (!cursorQuery3.moveToNext()) {
                                break;
                                break;
                            }
                            it8 = it8;
                        }
                        cursorQuery3.close();
                        mapEmptyMap2 = arrayMap7;
                    } else {
                        it8 = it8;
                        mapEmptyMap2 = Collections.emptyMap();
                        cursorQuery3.close();
                    }
                    arrayMap6.put(strZzf, mapEmptyMap2);
                } else {
                    it8 = it8;
                    str15 = str8;
                    str16 = str9;
                }
                it9 = mapEmptyMap2.keySet().iterator();
                while (it9.hasNext()) {
                    iIntValue3 = ((Integer) it9.next()).intValue();
                    set2 = this.zzb;
                    numValueOf4 = Integer.valueOf(iIntValue3);
                    if (set2.contains(numValueOf4)) {
                        this.zzs.zzay().zzj().zzb("Skipping failed audience ID", numValueOf4);
                        break;
                        break;
                    }
                    it10 = ((List) mapEmptyMap2.get(numValueOf4)).iterator();
                    zZzd2 = true;
                    while (true) {
                        if (it10.hasNext()) {
                            zzesVar = (com.google.android.gms.internal.measurement.zzes) it10.next();
                            if (Log.isLoggable(this.zzs.zzay().zzq(), 2)) {
                                zzej zzejVarZzj4 = this.zzs.zzay().zzj();
                                Integer numValueOf117 = Integer.valueOf(iIntValue3);
                                if (zzesVar.zzj()) {
                                    numValueOf6 = Integer.valueOf(zzesVar.zza());
                                } else {
                                    numValueOf6 = null;
                                }
                                zzejVarZzj4.zzd("Evaluating filter. audience, filter, property", numValueOf117, numValueOf6, this.zzs.zzj().zzf(zzesVar.zze()));
                                this.zzs.zzay().zzj().zzb("Filter definition", this.zzf.zzu().zzp(zzesVar));
                            }
                            if (zzesVar.zzj() || zzesVar.zza() > 256) {
                                zzej zzejVarZzk4 = this.zzs.zzay().zzk();
                                Object objZzn4 = zzel.zzn(this.zza);
                                if (zzesVar.zzj()) {
                                    numValueOf5 = Integer.valueOf(zzesVar.zza());
                                } else {
                                    numValueOf5 = null;
                                }
                                zzejVarZzk4.zzc("Invalid property filter ID. appId, id", objZzn4, String.valueOf(numValueOf5));
                                this.zzb.add(Integer.valueOf(iIntValue3));
                                mapEmptyMap2 = mapEmptyMap2;
                            } else {
                                zzyVar = new zzy(this, this.zza, iIntValue3, zzesVar);
                                zZzd2 = zzyVar.zzd(this.zzd, this.zze, next6, zzf(iIntValue3, zzesVar.zza()));
                                if (zZzd2) {
                                    zzd(Integer.valueOf(iIntValue3)).zzc(zzyVar);
                                    mapEmptyMap2 = mapEmptyMap2;
                                } else {
                                    this.zzb.add(Integer.valueOf(iIntValue3));
                                }
                            }
                        } else {
                            mapEmptyMap2 = mapEmptyMap2;
                        }
                        if (!zZzd2) {
                            this.zzb.add(Integer.valueOf(iIntValue3));
                        }
                        mapEmptyMap2 = mapEmptyMap2;
                    }
                }
                it8 = it8;
                str9 = str16;
                str8 = str15;
            }
        }
        str18 = str8;
        arrayList4 = new ArrayList();
        Set<Integer> setKeySet4 = this.zzc.keySet();
        setKeySet4.removeAll(this.zzb);
        it11 = setKeySet4.iterator();
        while (it11.hasNext()) {
            int iIntValue11 = it11.next().intValue();
            Map<Integer, zzt> map13 = this.zzc;
            Integer numValueOf118 = Integer.valueOf(iIntValue11);
            zzt zztVar4 = map13.get(numValueOf118);
            Preconditions.checkNotNull(zztVar4);
            com.google.android.gms.internal.measurement.zzfk zzfkVarZza4 = zztVar4.zza(iIntValue11);
            arrayList4.add(zzfkVarZza4);
            zzajVarZzi5 = this.zzf.zzi();
            str19 = this.zza;
            com.google.android.gms.internal.measurement.zzgd zzgdVarZzd4 = zzfkVarZza4.zzd();
            zzajVarZzi5.zzY();
            zzajVarZzi5.zzg();
            Preconditions.checkNotEmpty(str19);
            Preconditions.checkNotNull(zzgdVarZzd4);
            byte[] bArrZzbs4 = zzgdVarZzd4.zzbs();
            contentValues = new ContentValues();
            contentValues.put("app_id", str19);
            contentValues.put(str18, numValueOf118);
            String str33 = str14;
            contentValues.put(str33, bArrZzbs4);
            if (zzajVarZzi5.zzh().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                zzajVarZzi5.zzs.zzay().zzd().zzb("Failed to insert filter results (got -1). appId", zzel.zzn(str19));
            }
            str14 = str33;
        }
        return arrayList4;
    }

    @Override // com.google.android.gms.measurement.internal.zzki
    public final boolean zzb() {
        return false;
    }
}
