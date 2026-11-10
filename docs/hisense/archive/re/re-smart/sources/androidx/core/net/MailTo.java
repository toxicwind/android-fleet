package androidx.core.net;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class MailTo {
    public static final String BCC = "bcc";
    public static final String BODY = "body";
    public static final String CC = "cc";
    public static final String MAILTO = "mailto";
    public static final String MAILTO_SCHEME = "mailto:";
    public static final String SUBJECT = "subject";
    public static final String TO = "to";
    public HashMap<String, String> mHeaders = new HashMap<>();

    public static boolean isMailTo(@Nullable String uri) {
        return uri != null && uri.startsWith(MAILTO_SCHEME);
    }

    @NonNull
    public static MailTo parse(@NonNull String uri) {
        String strDecode;
        String strSubstring;
        if (uri == null) {
            throw new NullPointerException();
        }
        if (!isMailTo(uri)) {
            throw new ParseException("Not a mailto scheme");
        }
        int iIndexOf = uri.indexOf(35);
        if (iIndexOf != -1) {
            uri = uri.substring(0, iIndexOf);
        }
        int iIndexOf2 = uri.indexOf(63);
        if (iIndexOf2 == -1) {
            strDecode = Uri.decode(uri.substring(7));
            strSubstring = null;
        } else {
            strDecode = Uri.decode(uri.substring(7, iIndexOf2));
            strSubstring = uri.substring(iIndexOf2 + 1);
        }
        MailTo mailTo = new MailTo();
        if (strSubstring != null) {
            for (String str : strSubstring.split("&")) {
                String[] strArrSplit = str.split("=", 2);
                if (strArrSplit.length != 0) {
                    mailTo.mHeaders.put(Uri.decode(strArrSplit[0]).toLowerCase(Locale.ROOT), strArrSplit.length > 1 ? Uri.decode(strArrSplit[1]) : null);
                }
            }
        }
        String to = mailTo.getTo();
        if (to != null) {
            strDecode = a.a(strDecode, ", ", to);
        }
        mailTo.mHeaders.put(TO, strDecode);
        return mailTo;
    }

    @Nullable
    public String getBcc() {
        return this.mHeaders.get(BCC);
    }

    @Nullable
    public String getBody() {
        return this.mHeaders.get(BODY);
    }

    @Nullable
    public String getCc() {
        return this.mHeaders.get(CC);
    }

    @Nullable
    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    @Nullable
    public String getSubject() {
        return this.mHeaders.get(SUBJECT);
    }

    @Nullable
    public String getTo() {
        return this.mHeaders.get(TO);
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder(MAILTO_SCHEME);
        sb.append('?');
        for (Map.Entry<String, String> entry : this.mHeaders.entrySet()) {
            sb.append(Uri.encode(entry.getKey()));
            sb.append('=');
            sb.append(Uri.encode(entry.getValue()));
            sb.append('&');
        }
        return sb.toString();
    }

    public static boolean isMailTo(@Nullable Uri uri) {
        return uri != null && MAILTO.equals(uri.getScheme());
    }

    @NonNull
    public static MailTo parse(@NonNull Uri uri) {
        return parse(uri.toString());
    }
}
