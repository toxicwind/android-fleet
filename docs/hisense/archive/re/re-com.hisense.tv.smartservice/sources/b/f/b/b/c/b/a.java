package b.f.b.b.c.b;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f743a = "a";

    public static void a(Context context, String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("eventName can not be empty");
        }
        if (map == null) {
            throw new IllegalArgumentException("map can not be null");
        }
        ContentValues contentValues = new ContentValues();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            contentValues.put(entry.getKey(), entry.getValue());
        }
        try {
            context.getApplicationContext().getContentResolver().insert(Uri.parse("content://com.hisense.smartsense.firebase/log/" + str), contentValues);
        } catch (Exception e2) {
            Log.e(f743a, e2.toString());
        }
    }
}
