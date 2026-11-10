package b.a.a.a;

import android.util.Log;
import androidx.fragment.app.Fragment;

/* JADX INFO: compiled from: outline */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(String str, Fragment fragment, String str2) {
        return str + fragment + str2;
    }

    public static String b(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static StringBuilder c(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static void d(String str, Fragment fragment, String str2) {
        Log.v(str2, str + fragment);
    }
}
