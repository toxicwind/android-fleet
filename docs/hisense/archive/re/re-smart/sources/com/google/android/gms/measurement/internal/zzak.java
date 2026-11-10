package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.ju.lib.datalayer.database.asist.SQLBuilder;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class zzak {
    /* JADX WARN: Code duplicated, block: B:49:0x00fb  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v28, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.database.Cursor] */
    @WorkerThread
    public static void zza(zzel zzelVar, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) throws Throwable {
        ?? r1;
        Cursor cursorQuery;
        if (zzelVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        try {
            try {
                cursorQuery = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
                try {
                    boolean zMoveToFirst = cursorQuery.moveToFirst();
                    cursorQuery.close();
                    if (!zMoveToFirst) {
                        r1 = str2;
                        sQLiteDatabase.execSQL(r1);
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    zzelVar.zzk().zzc("Error querying for table", str, e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                }
            } catch (Throwable th) {
                th = th;
                if (r1 != 0) {
                    r1.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            r1 = 0;
            if (r1 != 0) {
                r1.close();
            }
            throw th;
        }
        try {
            HashSet hashSet = new HashSet();
            StringBuilder sb = new StringBuilder(str.length() + 22);
            sb.append(SQLBuilder.SELECT_ANY_FROM);
            sb.append(str);
            sb.append(" LIMIT 0");
            Cursor cursorRawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
            try {
                Collections.addAll(hashSet, cursorRawQuery.getColumnNames());
                cursorRawQuery.close();
                for (String str4 : str3.split(",")) {
                    if (!hashSet.remove(str4)) {
                        StringBuilder sb2 = new StringBuilder(str.length() + 35 + String.valueOf(str4).length());
                        sb2.append("Table ");
                        sb2.append(str);
                        sb2.append(" is missing required column: ");
                        sb2.append(str4);
                        throw new SQLiteException(sb2.toString());
                    }
                }
                if (strArr != null) {
                    for (int i = 0; i < strArr.length; i += 2) {
                        if (!hashSet.remove(strArr[i])) {
                            sQLiteDatabase.execSQL(strArr[i + 1]);
                        }
                    }
                }
                if (hashSet.isEmpty()) {
                    return;
                }
                zzelVar.zzk().zzc("Table has extra columns. table, columns", str, TextUtils.join(", ", hashSet));
            } catch (Throwable th3) {
                cursorRawQuery.close();
                throw th3;
            }
        } catch (SQLiteException e4) {
            zzelVar.zzd().zzb("Failed to verify columns on table that was just created", str);
            throw e4;
        }
    }

    public static void zzb(zzel zzelVar, SQLiteDatabase sQLiteDatabase) {
        if (zzelVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        File file = new File(sQLiteDatabase.getPath());
        if (!file.setReadable(false, false)) {
            zzelVar.zzk().zza("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            zzelVar.zzk().zza("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            zzelVar.zzk().zza("Failed to turn on database read permission for owner");
        }
        if (file.setWritable(true, true)) {
            return;
        }
        zzelVar.zzk().zza("Failed to turn on database write permission for owner");
    }
}
