package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Binder;
import android.os.StrictMode;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzha implements zzhe {
    public final ContentResolver zzc;
    public final Uri zzd;
    public volatile Map<String, String> zzg;

    @GuardedBy("ConfigurationContentLoader.class")
    public static final Map<Uri, zzha> zzb = new ArrayMap();
    public static final String[] zza = {"key", "value"};
    public final ContentObserver zze = new zzgz(this, null);
    public final Object zzf = new Object();

    @GuardedBy("this")
    public final List<zzhb> zzh = new ArrayList();

    public zzha(ContentResolver contentResolver, Uri uri) {
        if (contentResolver == null || uri == null) {
            throw null;
        }
        this.zzc = contentResolver;
        this.zzd = uri;
        contentResolver.registerContentObserver(uri, false, this.zze);
    }

    public static zzha zza(ContentResolver contentResolver, Uri uri) {
        zzha zzhaVar;
        synchronized (zzha.class) {
            zzhaVar = zzb.get(uri);
            if (zzhaVar == null) {
                try {
                    zzha zzhaVar2 = new zzha(contentResolver, uri);
                    try {
                        zzb.put(uri, zzhaVar2);
                    } catch (SecurityException unused) {
                    }
                    zzhaVar = zzhaVar2;
                } catch (SecurityException unused2) {
                }
            }
        }
        return zzhaVar;
    }

    public static synchronized void zze() {
        for (zzha zzhaVar : zzb.values()) {
            zzhaVar.zzc.unregisterContentObserver(zzhaVar.zze);
        }
        zzb.clear();
    }

    @Override // com.google.android.gms.internal.measurement.zzhe
    public final /* bridge */ /* synthetic */ Object zzb(String str) {
        return zzc().get(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Map<java.lang.String, java.lang.String>] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.Map<java.lang.String, java.lang.String>] */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.os.StrictMode$ThreadPolicy] */
    /* JADX WARN: Type inference failed for: r0v6, types: [android.os.StrictMode$ThreadPolicy] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public final Map<String, String> zzc() {
        Map<String, String> mapZzd;
        ?? r0;
        Map<String, String> map = this.zzg;
        ?? r1 = map;
        if (map == null) {
            synchronized (this.zzf) {
                ?? AllowThreadDiskReads = this.zzg;
                r0 = AllowThreadDiskReads;
                if (AllowThreadDiskReads == 0) {
                    try {
                        AllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                        try {
                            try {
                                mapZzd = zzd();
                            } catch (SecurityException unused) {
                                long jClearCallingIdentity = Binder.clearCallingIdentity();
                                try {
                                    Map<String, String> mapZzd2 = zzd();
                                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                                    mapZzd = mapZzd2;
                                } catch (Throwable th) {
                                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                                    throw th;
                                }
                            }
                            StrictMode.setThreadPolicy(AllowThreadDiskReads);
                        } catch (SQLiteException | IllegalStateException | SecurityException unused2) {
                            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
                            StrictMode.setThreadPolicy(AllowThreadDiskReads);
                            mapZzd = null;
                        }
                        this.zzg = mapZzd;
                        r0 = mapZzd;
                    } catch (Throwable th2) {
                        StrictMode.setThreadPolicy(AllowThreadDiskReads);
                        throw th2;
                    }
                }
            }
            r1 = r0;
        }
        return r1 != 0 ? r1 : Collections.emptyMap();
    }

    public final /* synthetic */ Map zzd() {
        Cursor cursorQuery = this.zzc.query(this.zzd, zza, null, null, null);
        if (cursorQuery == null) {
            return Collections.emptyMap();
        }
        try {
            int count = cursorQuery.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            Map arrayMap = count <= 256 ? new ArrayMap(count) : new HashMap(count, 1.0f);
            while (cursorQuery.moveToNext()) {
                arrayMap.put(cursorQuery.getString(0), cursorQuery.getString(1));
            }
            return arrayMap;
        } finally {
            cursorQuery.close();
        }
    }

    public final void zzf() {
        synchronized (this.zzf) {
            this.zzg = null;
            zzhu.zzi.incrementAndGet();
        }
        synchronized (this) {
            Iterator<zzhb> it = this.zzh.iterator();
            while (it.hasNext()) {
                it.next().zza();
            }
        }
    }
}
