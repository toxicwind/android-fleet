package b.f.b.b.c.a;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f742a = "a";

    /* JADX WARN: Code duplicated, block: B:36:0x00f7 A[DONT_GENERATE, PHI: r0 r11
      0x00f7: PHI (r0v6 android.database.Cursor) = (r0v5 android.database.Cursor), (r0v7 android.database.Cursor) binds: [B:35:0x00f5, B:29:0x00e6] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r11v1 java.lang.String) = (r11v0 java.lang.String), (r11v3 java.lang.String) binds: [B:35:0x00f5, B:29:0x00e6] A[DONT_GENERATE, DONT_INLINE]] */
    public static String a(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("serviceCode can not be empty");
        }
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("businessCode can not be empty");
        }
        if (TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("defaultDomain can not be empty");
        }
        if (str3.startsWith("http")) {
            throw new IllegalArgumentException("defaultDomain can not start with 'http' or 'https'");
        }
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = context.getApplicationContext().getContentResolver().query(Uri.parse("content://com.hisense.smartsense.domain/domain/" + str + "/" + str2), null, null, null, null);
                if (cursorQuery != null) {
                    if (cursorQuery.moveToFirst()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("domain_url"));
                        Log.d(f742a, "domain found,url=" + string);
                        String authority = new URL(string).getAuthority();
                        if (!TextUtils.isEmpty(authority)) {
                            str3 = authority;
                        }
                    } else {
                        String strA = a(context);
                        if (TextUtils.isEmpty(strA)) {
                            Log.i(f742a, "domain can not be found,use default domain=" + str3);
                        } else {
                            str3 = str.equals(str2) ? String.format("%s-%s.%s", str, strA, "intsmarthub.com") : String.format("%s-%s-%s.%s", str2, str, strA, "intsmarthub.com");
                            Log.i(f742a, "domain can not be found,use builtIn domain=" + str3);
                        }
                    }
                }
            } catch (Exception e2) {
                Log.e(f742a, e2.toString());
            }
            return str3;
        } finally {
            if (0 != 0) {
                cursorQuery.close();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0049  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v4, types: [android.database.Cursor] */
    public static String a(Context context) throws Throwable {
        Throwable th;
        Cursor cursorQuery;
        String string = null;
        try {
            try {
                cursorQuery = context.getApplicationContext().getContentResolver().query(Uri.parse("content://com.hisense.smartsense.domain/region"), null, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            string = cursorQuery.getString(cursorQuery.getColumnIndex("region_code"));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.e(f742a, e.toString());
                        if (cursorQuery != null) {
                        }
                        return string;
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (context != 0) {
                    context.close();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            cursorQuery = null;
        } catch (Throwable th3) {
            th = th3;
            context = 0;
            if (context != 0) {
                context.close();
            }
            throw th;
        }
        return string;
    }
}
