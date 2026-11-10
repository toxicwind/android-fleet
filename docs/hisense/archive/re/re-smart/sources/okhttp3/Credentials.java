package okhttp3;

import b.a.a.a.a;
import com.google.firebase.installations.Utils;
import d.j;
import java.nio.charset.Charset;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes2.dex */
public final class Credentials {
    public static String basic(String str, String str2) {
        return basic(str, str2, Util.ISO_8859_1);
    }

    public static String basic(String str, String str2, Charset charset) {
        return a.a("Basic ", j.a(str + Utils.APP_ID_IDENTIFICATION_SUBSTRING + str2, charset).a());
    }
}
