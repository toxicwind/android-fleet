package com.hisense.tv.smartsense.module.firebase.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.e;
import b.f.b.b.b.b.b.c;
import b.f.b.b.b.b.e.d;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class FirebaseLogReportProvider extends ContentProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final UriMatcher f1966a = new UriMatcher(-1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f1967b = "FirebaseLogReportProvider";

    static {
        f1966a.addURI("com.hisense.smartsense.firebase", "log/*", 1);
        f1966a.addURI("com.hisense.smartsense.firebase", "log_forever/*", 2);
        f1966a.addURI("com.hisense.smartsense.firebase", AppMeasurement.CRASH_ORIGIN, 3);
        f1966a.addURI("com.hisense.smartsense.firebase", "anr", 4);
    }

    public void a(Uri uri, ContentValues contentValues) {
        try {
            Map<String, Object> mapA = a(contentValues);
            e.f34a.a(mapA);
            new c(getContext()).a(mapA);
        } catch (Exception e2) {
            Log.e(f1967b, e2.toString());
        }
    }

    public void b(Uri uri, ContentValues contentValues) {
        try {
            Map<String, Object> mapA = a(contentValues);
            e.f34a.a(mapA);
            new c(getContext()).b(mapA);
        } catch (Exception e2) {
            Log.e(f1967b, e2.toString());
        }
    }

    public void c(Uri uri, ContentValues contentValues) {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() < 2) {
            throw new IllegalArgumentException("the uri path is wrong");
        }
        try {
            String str = pathSegments.get(1);
            Map<String, Object> mapA = a(contentValues);
            e.f34a.a(mapA);
            d.a(getContext()).a(str, mapA);
        } catch (Exception e2) {
            Log.e(f1967b, e2.toString());
        }
    }

    public void d(Uri uri, ContentValues contentValues) {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() < 2) {
            throw new IllegalArgumentException("the uri path is wrong");
        }
        try {
            String str = pathSegments.get(1);
            Map<String, Object> mapA = a(contentValues);
            e.f34a.a(mapA);
            d.a(getContext()).b(str, mapA);
        } catch (Exception e2) {
            Log.e(f1967b, e2.toString());
        }
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        throw new IllegalArgumentException("delete not support");
    }

    @Override // android.content.ContentProvider
    @Nullable
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        int iMatch = f1966a.match(uri);
        if (iMatch == 1) {
            c(uri, contentValues);
            return null;
        }
        if (iMatch == 2) {
            d(uri, contentValues);
            return null;
        }
        if (iMatch == 3) {
            b(uri, contentValues);
            return null;
        }
        if (iMatch != 4) {
            return null;
        }
        a(uri, contentValues);
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        throw new IllegalArgumentException("query not support");
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        throw new IllegalArgumentException("update not support");
    }

    public Map<String, Object> a(ContentValues contentValues) {
        HashMap map = new HashMap();
        for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
}
