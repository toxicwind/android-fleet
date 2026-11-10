package a.a.a.a.a.a;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f0a = "a";

    public static String a(Context context, String str, String str2, String str3) {
        return b(context, str, str2, str3, true);
    }

    public static String b(Context context, String str, String str2, String str3, boolean z) {
        String str4;
        String str5;
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
                        Log.d(f0a, "domain found,url=" + string);
                        String authority = new URL(string).getAuthority();
                        if (!TextUtils.isEmpty(authority)) {
                            str3 = authority;
                        }
                    } else if (z) {
                        String strC = c(context);
                        if (TextUtils.isEmpty(strC)) {
                            str4 = f0a;
                            str5 = "domain can not be found,use default domain=" + str3;
                        } else {
                            str3 = str.equals(str2) ? String.format("%s-%s.%s", str, strC, "intsmarthub.com") : String.format("%s-%s-%s.%s", str2, str, strC, "intsmarthub.com");
                            str4 = f0a;
                            str5 = "domain can not be found,use builtIn domain=" + str3;
                        }
                        Log.i(str4, str5);
                    } else {
                        Log.d(f0a, "domain can not be found,use default domain=" + str3);
                    }
                }
            } catch (Exception e) {
                Log.e(f0a, e.toString());
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
    public static String c(Context context) throws Throwable {
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
                    } catch (Exception e) {
                        e = e;
                        Log.e(f0a, e.toString());
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
        } catch (Exception e2) {
            e = e2;
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
