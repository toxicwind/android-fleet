package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.SystemClock;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import b.a.a.a.a;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.remoteconfig.internal.DefaultsXmlParser;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzee extends zzf {
    public final zzed zza;
    public boolean zzb;

    public zzee(zzfv zzfvVar) {
        super(zzfvVar);
        Context contextZzau = this.zzs.zzau();
        this.zzs.zzf();
        this.zza = new zzed(this, contextZzau, "google_app_measurement_local.db");
    }

    /* JADX WARN: Code duplicated, block: B:72:0x0118 A[PHI: r3
      0x0118: PHI (r3v3 android.database.sqlite.SQLiteDatabase) = (r3v1 android.database.sqlite.SQLiteDatabase), (r3v4 android.database.sqlite.SQLiteDatabase) binds: [B:56:0x00e8, B:71:0x0116] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:77:0x0126  */
    /* JADX WARN: Code duplicated, block: B:79:0x012b  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r15v7, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r5v3, types: [android.database.Cursor] */
    @WorkerThread
    private final boolean zzq(int i, byte[] bArr) throws Throwable {
        SQLiteDatabase sQLiteDatabaseZzh;
        ?? r15;
        ?? r16;
        zzg();
        int i2 = 0;
        if (this.zzb) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put(DefaultsXmlParser.XML_TAG_ENTRY, bArr);
        this.zzs.zzf();
        int i3 = 5;
        ?? r17 = 0;
        for (int i4 = 5; i2 < i4; i4 = 5) {
            ?? r3 = 0;
            r3 = 0;
            ?? r4 = 0;
            ?? r5 = 0;
            r3 = 0;
            sQLiteDatabase = null;
            SQLiteDatabase sQLiteDatabase = null;
            try {
                sQLiteDatabaseZzh = zzh();
                try {
                    if (sQLiteDatabaseZzh == null) {
                        this.zzb = true;
                        return r17;
                    }
                    sQLiteDatabaseZzh.beginTransaction();
                    ?? RawQuery = sQLiteDatabaseZzh.rawQuery("select count(1) from messages", null);
                    long j = 0;
                    if (RawQuery != 0) {
                        try {
                            if (RawQuery.moveToFirst()) {
                                j = RawQuery.getLong(r17);
                            }
                        } catch (SQLiteDatabaseLockedException unused) {
                            r3 = RawQuery;
                            try {
                                SystemClock.sleep(i3);
                                i3 += 20;
                                if (r3 != 0) {
                                    r3.close();
                                }
                                if (sQLiteDatabaseZzh != null) {
                                    sQLiteDatabaseZzh.close();
                                }
                                i2++;
                                r17 = 0;
                            } catch (Throwable th) {
                                th = th;
                                if (r3 != 0) {
                                    r3.close();
                                }
                                if (sQLiteDatabaseZzh != null) {
                                    sQLiteDatabaseZzh.close();
                                }
                                throw th;
                            }
                        } catch (SQLiteFullException e2) {
                            e = e2;
                            r4 = RawQuery;
                            r16 = r4;
                            sQLiteDatabase = sQLiteDatabaseZzh;
                            this.zzs.zzay().zzd().zzb("Error writing entry; local database full", e);
                            this.zzb = true;
                            if (r16 != 0) {
                                r16.close();
                            }
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.close();
                            }
                            i2++;
                            r17 = 0;
                        } catch (SQLiteException e3) {
                            e = e3;
                            r5 = RawQuery;
                            r15 = r5;
                            sQLiteDatabase = sQLiteDatabaseZzh;
                            if (sQLiteDatabase != null) {
                                try {
                                    if (sQLiteDatabase.inTransaction()) {
                                        sQLiteDatabase.endTransaction();
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    sQLiteDatabaseZzh = sQLiteDatabase;
                                    r3 = r15;
                                    if (r3 != 0) {
                                        r3.close();
                                    }
                                    if (sQLiteDatabaseZzh != null) {
                                        sQLiteDatabaseZzh.close();
                                    }
                                    throw th;
                                }
                            }
                            this.zzs.zzay().zzd().zzb("Error writing entry to local database", e);
                            this.zzb = true;
                            if (r15 != 0) {
                                r15.close();
                            }
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.close();
                            }
                            i2++;
                            r17 = 0;
                        } catch (Throwable th3) {
                            th = th3;
                            r3 = RawQuery;
                            if (r3 != 0) {
                                r3.close();
                            }
                            if (sQLiteDatabaseZzh != null) {
                                sQLiteDatabaseZzh.close();
                            }
                            throw th;
                        }
                    }
                    if (j >= 100000) {
                        this.zzs.zzay().zzd().zza("Data loss, local db full");
                        long j2 = (100000 - j) + 1;
                        String[] strArr = new String[1];
                        strArr[r17] = Long.toString(j2);
                        long jDelete = sQLiteDatabaseZzh.delete(NotificationCompat.CarExtender.KEY_MESSAGES, "rowid in (select rowid from messages order by rowid asc limit ?)", strArr);
                        if (jDelete != j2) {
                            this.zzs.zzay().zzd().zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j2), Long.valueOf(jDelete), Long.valueOf(j2 - jDelete));
                        }
                    }
                    sQLiteDatabaseZzh.insertOrThrow(NotificationCompat.CarExtender.KEY_MESSAGES, null, contentValues);
                    sQLiteDatabaseZzh.setTransactionSuccessful();
                    sQLiteDatabaseZzh.endTransaction();
                    if (RawQuery != 0) {
                        RawQuery.close();
                    }
                    sQLiteDatabaseZzh.close();
                    return true;
                } catch (SQLiteDatabaseLockedException unused2) {
                } catch (SQLiteFullException e4) {
                    e = e4;
                } catch (SQLiteException e5) {
                    e = e5;
                }
            } catch (SQLiteDatabaseLockedException unused3) {
                sQLiteDatabaseZzh = null;
            } catch (SQLiteFullException e6) {
                e = e6;
                r16 = 0;
            } catch (SQLiteException e7) {
                e = e7;
                r15 = 0;
            } catch (Throwable th4) {
                th = th4;
                sQLiteDatabaseZzh = null;
            }
        }
        a.b(this.zzs, "Failed to write entry to local database");
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final boolean zzf() {
        return false;
    }

    @VisibleForTesting
    @WorkerThread
    public final SQLiteDatabase zzh() {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:134:0x0202 A[Catch: all -> 0x024c, TryCatch #22 {all -> 0x024c, blocks: (B:76:0x017d, B:78:0x0187, B:79:0x0196, B:132:0x01fc, B:134:0x0202, B:135:0x0205, B:150:0x022f, B:142:0x021f), top: B:174:0x01fc }] */
    /* JADX WARN: Code duplicated, block: B:137:0x0216  */
    /* JADX WARN: Code duplicated, block: B:145:0x0226  */
    /* JADX WARN: Code duplicated, block: B:152:0x0240  */
    /* JADX WARN: Code duplicated, block: B:154:0x0245 A[PHI: r9 r14
      0x0245: PHI (r9v3 int) = (r9v1 int), (r9v1 int), (r9v4 int) binds: [B:138:0x0219, B:153:0x0243, B:146:0x0229] A[DONT_GENERATE, DONT_INLINE]
      0x0245: PHI (r14v9 android.database.sqlite.SQLiteDatabase) = 
      (r14v7 android.database.sqlite.SQLiteDatabase)
      (r14v8 android.database.sqlite.SQLiteDatabase)
      (r14v10 android.database.sqlite.SQLiteDatabase)
     binds: [B:138:0x0219, B:153:0x0243, B:146:0x0229] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:159:0x0251  */
    /* JADX WARN: Code duplicated, block: B:161:0x0256  */
    /* JADX WARN: Code duplicated, block: B:174:0x01fc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:186:0x01da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:197:0x0248 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:198:0x0248 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:199:0x0248 A[SYNTHETIC] */
    public final List<AbstractSafeParcelable> zzi(int i) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursorQuery;
        Cursor cursorQuery2;
        long j;
        String str;
        String[] strArr;
        zzkv zzkvVarCreateFromParcel;
        zzab zzabVarCreateFromParcel;
        zzg();
        Cursor cursor = null;
        if (this.zzb) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!zzl()) {
            return arrayList;
        }
        int i2 = 5;
        for (int i3 = 0; i3 < 5; i3++) {
            try {
                SQLiteDatabase sQLiteDatabaseZzh = zzh();
                if (sQLiteDatabaseZzh == null) {
                    this.zzb = true;
                    return null;
                }
                try {
                    sQLiteDatabaseZzh.beginTransaction();
                    try {
                        try {
                            cursorQuery2 = sQLiteDatabaseZzh.query(NotificationCompat.CarExtender.KEY_MESSAGES, new String[]{"rowid"}, "type=?", new String[]{"3"}, null, null, "rowid desc", "1");
                            try {
                                long j2 = -1;
                                if (cursorQuery2.moveToFirst()) {
                                    j = cursorQuery2.getLong(0);
                                    try {
                                        cursorQuery2.close();
                                    } catch (SQLiteDatabaseLockedException unused) {
                                        sQLiteDatabase = sQLiteDatabaseZzh;
                                        cursorQuery = null;
                                        SystemClock.sleep(i2);
                                        i2 += 20;
                                        if (cursorQuery != null) {
                                            cursorQuery.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                    } catch (SQLiteFullException e2) {
                                        e = e2;
                                        sQLiteDatabase = sQLiteDatabaseZzh;
                                        cursorQuery = null;
                                        this.zzs.zzay().zzd().zzb("Error reading entries from local database", e);
                                        this.zzb = true;
                                        if (cursorQuery != null) {
                                            cursorQuery.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                    } catch (SQLiteException e3) {
                                        e = e3;
                                        sQLiteDatabase = sQLiteDatabaseZzh;
                                        cursorQuery = null;
                                        if (sQLiteDatabase != null) {
                                            try {
                                                if (sQLiteDatabase.inTransaction()) {
                                                    sQLiteDatabase.endTransaction();
                                                }
                                            } catch (Throwable th) {
                                                th = th;
                                                cursor = cursorQuery;
                                                if (cursor != null) {
                                                    cursor.close();
                                                }
                                                if (sQLiteDatabase != null) {
                                                    sQLiteDatabase.close();
                                                }
                                                throw th;
                                            }
                                        }
                                        this.zzs.zzay().zzd().zzb("Error reading entries from local database", e);
                                        this.zzb = true;
                                        if (cursorQuery != null) {
                                            cursorQuery.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        sQLiteDatabase = sQLiteDatabaseZzh;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                        throw th;
                                    }
                                } else {
                                    cursorQuery2.close();
                                    j = -1;
                                }
                                if (j != -1) {
                                    str = "rowid<?";
                                    strArr = new String[]{String.valueOf(j)};
                                } else {
                                    str = null;
                                    strArr = null;
                                }
                                cursorQuery = sQLiteDatabaseZzh.query(NotificationCompat.CarExtender.KEY_MESSAGES, new String[]{"rowid", "type", DefaultsXmlParser.XML_TAG_ENTRY}, str, strArr, null, null, "rowid asc", Integer.toString(100));
                                while (cursorQuery.moveToNext()) {
                                    try {
                                        j2 = cursorQuery.getLong(0);
                                        int i4 = cursorQuery.getInt(1);
                                        byte[] blob = cursorQuery.getBlob(2);
                                        if (i4 == 0) {
                                            Parcel parcelObtain = Parcel.obtain();
                                            try {
                                                try {
                                                    parcelObtain.unmarshall(blob, 0, blob.length);
                                                    parcelObtain.setDataPosition(0);
                                                    zzat zzatVarCreateFromParcel = zzat.CREATOR.createFromParcel(parcelObtain);
                                                    parcelObtain.recycle();
                                                    if (zzatVarCreateFromParcel != null) {
                                                        arrayList.add(zzatVarCreateFromParcel);
                                                    }
                                                } catch (Throwable th3) {
                                                    parcelObtain.recycle();
                                                    throw th3;
                                                }
                                            } catch (SafeParcelReader.ParseException unused2) {
                                                this.zzs.zzay().zzd().zza("Failed to load event from local database");
                                                parcelObtain.recycle();
                                            }
                                        } else if (i4 == 1) {
                                            Parcel parcelObtain2 = Parcel.obtain();
                                            try {
                                                try {
                                                    parcelObtain2.unmarshall(blob, 0, blob.length);
                                                    parcelObtain2.setDataPosition(0);
                                                    zzkvVarCreateFromParcel = zzkv.CREATOR.createFromParcel(parcelObtain2);
                                                    parcelObtain2.recycle();
                                                } catch (Throwable th4) {
                                                    parcelObtain2.recycle();
                                                    throw th4;
                                                }
                                            } catch (SafeParcelReader.ParseException unused3) {
                                                this.zzs.zzay().zzd().zza("Failed to load user property from local database");
                                                parcelObtain2.recycle();
                                                zzkvVarCreateFromParcel = null;
                                            }
                                            if (zzkvVarCreateFromParcel != null) {
                                                arrayList.add(zzkvVarCreateFromParcel);
                                            }
                                        } else if (i4 == 2) {
                                            Parcel parcelObtain3 = Parcel.obtain();
                                            try {
                                                try {
                                                    parcelObtain3.unmarshall(blob, 0, blob.length);
                                                    parcelObtain3.setDataPosition(0);
                                                    zzabVarCreateFromParcel = zzab.CREATOR.createFromParcel(parcelObtain3);
                                                    parcelObtain3.recycle();
                                                } catch (Throwable th5) {
                                                    parcelObtain3.recycle();
                                                    throw th5;
                                                }
                                            } catch (SafeParcelReader.ParseException unused4) {
                                                this.zzs.zzay().zzd().zza("Failed to load conditional user property from local database");
                                                parcelObtain3.recycle();
                                                zzabVarCreateFromParcel = null;
                                            }
                                            if (zzabVarCreateFromParcel != null) {
                                                arrayList.add(zzabVarCreateFromParcel);
                                            }
                                        } else if (i4 == 3) {
                                            this.zzs.zzay().zzk().zza("Skipping app launch break");
                                        } else {
                                            this.zzs.zzay().zzd().zza("Unknown record type in local database");
                                        }
                                    } catch (SQLiteDatabaseLockedException unused5) {
                                        sQLiteDatabase = sQLiteDatabaseZzh;
                                        SystemClock.sleep(i2);
                                        i2 += 20;
                                        if (cursorQuery != null) {
                                            cursorQuery.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                    } catch (SQLiteFullException e4) {
                                        e = e4;
                                        sQLiteDatabase = sQLiteDatabaseZzh;
                                        this.zzs.zzay().zzd().zzb("Error reading entries from local database", e);
                                        this.zzb = true;
                                        if (cursorQuery != null) {
                                            cursorQuery.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                    } catch (SQLiteException e5) {
                                        e = e5;
                                        sQLiteDatabase = sQLiteDatabaseZzh;
                                        if (sQLiteDatabase != null) {
                                            if (sQLiteDatabase.inTransaction()) {
                                                sQLiteDatabase.endTransaction();
                                            }
                                        }
                                        this.zzs.zzay().zzd().zzb("Error reading entries from local database", e);
                                        this.zzb = true;
                                        if (cursorQuery != null) {
                                            cursorQuery.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                    } catch (Throwable th6) {
                                        th = th6;
                                        sQLiteDatabase = sQLiteDatabaseZzh;
                                        cursor = cursorQuery;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                        throw th;
                                    }
                                }
                                sQLiteDatabase = sQLiteDatabaseZzh;
                                try {
                                    if (sQLiteDatabase.delete(NotificationCompat.CarExtender.KEY_MESSAGES, "rowid <= ?", new String[]{Long.toString(j2)}) < arrayList.size()) {
                                        this.zzs.zzay().zzd().zza("Fewer entries removed from local database than expected");
                                    }
                                    sQLiteDatabase.setTransactionSuccessful();
                                    sQLiteDatabase.endTransaction();
                                    cursorQuery.close();
                                    sQLiteDatabase.close();
                                    return arrayList;
                                } catch (SQLiteDatabaseLockedException unused6) {
                                    SystemClock.sleep(i2);
                                    i2 += 20;
                                    if (cursorQuery != null) {
                                        cursorQuery.close();
                                    }
                                    if (sQLiteDatabase != null) {
                                        sQLiteDatabase.close();
                                    }
                                } catch (SQLiteFullException e6) {
                                    e = e6;
                                    this.zzs.zzay().zzd().zzb("Error reading entries from local database", e);
                                    this.zzb = true;
                                    if (cursorQuery != null) {
                                        cursorQuery.close();
                                    }
                                    if (sQLiteDatabase != null) {
                                        sQLiteDatabase.close();
                                    }
                                } catch (SQLiteException e7) {
                                    e = e7;
                                    if (sQLiteDatabase != null) {
                                        if (sQLiteDatabase.inTransaction()) {
                                            sQLiteDatabase.endTransaction();
                                        }
                                    }
                                    this.zzs.zzay().zzd().zzb("Error reading entries from local database", e);
                                    this.zzb = true;
                                    if (cursorQuery != null) {
                                        cursorQuery.close();
                                    }
                                    if (sQLiteDatabase != null) {
                                        sQLiteDatabase.close();
                                    }
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                sQLiteDatabase = sQLiteDatabaseZzh;
                                if (cursorQuery2 != null) {
                                    try {
                                        cursorQuery2.close();
                                    } catch (SQLiteDatabaseLockedException unused7) {
                                        cursorQuery = null;
                                        SystemClock.sleep(i2);
                                        i2 += 20;
                                        if (cursorQuery != null) {
                                            cursorQuery.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                    } catch (SQLiteFullException e8) {
                                        e = e8;
                                        cursorQuery = null;
                                        this.zzs.zzay().zzd().zzb("Error reading entries from local database", e);
                                        this.zzb = true;
                                        if (cursorQuery != null) {
                                            cursorQuery.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                    } catch (SQLiteException e9) {
                                        e = e9;
                                        cursorQuery = null;
                                        if (sQLiteDatabase != null) {
                                            if (sQLiteDatabase.inTransaction()) {
                                                sQLiteDatabase.endTransaction();
                                            }
                                        }
                                        this.zzs.zzay().zzd().zzb("Error reading entries from local database", e);
                                        this.zzb = true;
                                        if (cursorQuery != null) {
                                            cursorQuery.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                    } catch (Throwable th8) {
                                        th = th8;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                        throw th;
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th9) {
                            th = th9;
                            sQLiteDatabase = sQLiteDatabaseZzh;
                            cursorQuery2 = null;
                            if (cursorQuery2 != null) {
                                cursorQuery2.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th10) {
                        th = th10;
                        sQLiteDatabase = sQLiteDatabaseZzh;
                    }
                } catch (SQLiteDatabaseLockedException unused8) {
                    sQLiteDatabase = sQLiteDatabaseZzh;
                } catch (SQLiteFullException e10) {
                    e = e10;
                    sQLiteDatabase = sQLiteDatabaseZzh;
                } catch (SQLiteException e11) {
                    e = e11;
                    sQLiteDatabase = sQLiteDatabaseZzh;
                } catch (Throwable th11) {
                    th = th11;
                    sQLiteDatabase = sQLiteDatabaseZzh;
                }
            } catch (SQLiteDatabaseLockedException unused9) {
                cursorQuery = null;
                sQLiteDatabase = null;
            } catch (SQLiteFullException e12) {
                e = e12;
                cursorQuery = null;
                sQLiteDatabase = null;
            } catch (SQLiteException e13) {
                e = e13;
                cursorQuery = null;
                sQLiteDatabase = null;
            } catch (Throwable th12) {
                th = th12;
                sQLiteDatabase = null;
            }
        }
        a.c(this.zzs, "Failed to read events from database in reasonable time");
        return null;
    }

    @WorkerThread
    public final void zzj() {
        int iDelete;
        zzg();
        try {
            SQLiteDatabase sQLiteDatabaseZzh = zzh();
            if (sQLiteDatabaseZzh == null || (iDelete = sQLiteDatabaseZzh.delete(NotificationCompat.CarExtender.KEY_MESSAGES, null, null)) <= 0) {
                return;
            }
            this.zzs.zzay().zzj().zzb("Reset local analytics data. records", Integer.valueOf(iDelete));
        } catch (SQLiteException e2) {
            a.a(this.zzs, "Error resetting local analytics data. error", e2);
        }
    }

    @WorkerThread
    public final boolean zzk() {
        return zzq(3, new byte[0]);
    }

    @VisibleForTesting
    public final boolean zzl() {
        Context contextZzau = this.zzs.zzau();
        this.zzs.zzf();
        return contextZzau.getDatabasePath("google_app_measurement_local.db").exists();
    }

    @WorkerThread
    public final boolean zzm() {
        zzg();
        if (!this.zzb && zzl()) {
            int i = 5;
            for (int i2 = 0; i2 < 5; i2++) {
                SQLiteDatabase sQLiteDatabase = null;
                try {
                    try {
                        SQLiteDatabase sQLiteDatabaseZzh = zzh();
                        if (sQLiteDatabaseZzh == null) {
                            this.zzb = true;
                            return false;
                        }
                        sQLiteDatabaseZzh.beginTransaction();
                        sQLiteDatabaseZzh.delete(NotificationCompat.CarExtender.KEY_MESSAGES, "type == ?", new String[]{Integer.toString(3)});
                        sQLiteDatabaseZzh.setTransactionSuccessful();
                        sQLiteDatabaseZzh.endTransaction();
                        sQLiteDatabaseZzh.close();
                        return true;
                    } catch (SQLiteDatabaseLockedException unused) {
                        SystemClock.sleep(i);
                        i += 20;
                        if (0 != 0) {
                            sQLiteDatabase.close();
                        }
                    }
                } catch (SQLiteFullException e2) {
                    this.zzs.zzay().zzd().zzb("Error deleting app launch break from local database", e2);
                    this.zzb = true;
                    if (0 != 0) {
                        sQLiteDatabase.close();
                    }
                } catch (SQLiteException e3) {
                    if (0 != 0) {
                        try {
                            if (sQLiteDatabase.inTransaction()) {
                                sQLiteDatabase.endTransaction();
                            }
                        } catch (Throwable th) {
                            if (0 != 0) {
                                sQLiteDatabase.close();
                            }
                            throw th;
                        }
                    }
                    this.zzs.zzay().zzd().zzb("Error deleting app launch break from local database", e3);
                    this.zzb = true;
                    if (0 != 0) {
                        sQLiteDatabase.close();
                    }
                }
            }
            a.c(this.zzs, "Error deleting app launch break from local database in reasonable time");
        }
        return false;
    }

    public final boolean zzn(zzab zzabVar) {
        byte[] bArrZzan = this.zzs.zzv().zzan(zzabVar);
        if (bArrZzan.length <= 131072) {
            return zzq(2, bArrZzan);
        }
        this.zzs.zzay().zzh().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean zzo(zzat zzatVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzau.zza(zzatVar, parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length <= 131072) {
            return zzq(0, bArrMarshall);
        }
        this.zzs.zzay().zzh().zza("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zzp(zzkv zzkvVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzkw.zza(zzkvVar, parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length <= 131072) {
            return zzq(1, bArrMarshall);
        }
        this.zzs.zzay().zzh().zza("User property too long for local database. Sending directly to service");
        return false;
    }
}
