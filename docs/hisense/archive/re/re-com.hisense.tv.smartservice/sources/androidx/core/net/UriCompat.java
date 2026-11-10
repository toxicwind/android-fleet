package androidx.core.net;

import android.net.Uri;
import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.common.net.InetAddresses;
import com.google.firebase.installations.Utils;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;

/* JADX INFO: loaded from: classes.dex */
public final class UriCompat {
    @NonNull
    public static String toSafeString(@NonNull Uri uri) {
        String scheme = uri.getScheme();
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        if (scheme != null) {
            if (scheme.equalsIgnoreCase(MtkTvConfigTypeBase.S639_CFG_LANG_TEL) || scheme.equalsIgnoreCase("sip") || scheme.equalsIgnoreCase("sms") || scheme.equalsIgnoreCase("smsto") || scheme.equalsIgnoreCase(MailTo.MAILTO) || scheme.equalsIgnoreCase("nfc")) {
                StringBuilder sb = new StringBuilder(64);
                sb.append(scheme);
                sb.append(InetAddresses.IPV6_DELIMITER);
                if (schemeSpecificPart != null) {
                    for (int i = 0; i < schemeSpecificPart.length(); i++) {
                        char cCharAt = schemeSpecificPart.charAt(i);
                        if (cCharAt == '-' || cCharAt == '@' || cCharAt == '.') {
                            sb.append(cCharAt);
                        } else {
                            sb.append('x');
                        }
                    }
                }
                return sb.toString();
            }
            if (scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https") || scheme.equalsIgnoreCase("ftp") || scheme.equalsIgnoreCase("rtsp")) {
                StringBuilder sbA = a.a("//");
                String string = "";
                sbA.append(uri.getHost() != null ? uri.getHost() : "");
                if (uri.getPort() != -1) {
                    StringBuilder sbA2 = a.a(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
                    sbA2.append(uri.getPort());
                    string = sbA2.toString();
                }
                schemeSpecificPart = a.a(sbA, string, "/...");
            }
        }
        StringBuilder sb2 = new StringBuilder(64);
        if (scheme != null) {
            sb2.append(scheme);
            sb2.append(InetAddresses.IPV6_DELIMITER);
        }
        if (schemeSpecificPart != null) {
            sb2.append(schemeSpecificPart);
        }
        return sb2.toString();
    }
}
