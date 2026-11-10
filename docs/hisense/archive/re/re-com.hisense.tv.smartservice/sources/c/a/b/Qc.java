package c.a.b;

import java.net.Authenticator;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes2.dex */
public class Qc implements Sc.a {
    public PasswordAuthentication a(String str, InetAddress inetAddress, int i, String str2, String str3, String str4) {
        URL url;
        try {
            url = new URL(str2, str, i, "");
        } catch (MalformedURLException unused) {
            Sc.f1147a.log(Level.WARNING, String.format("failed to create URL for Authenticator: %s %s", str2, str));
            url = null;
        }
        return Authenticator.requestPasswordAuthentication(str, inetAddress, i, str2, str3, str4, url, Authenticator.RequestorType.PROXY);
    }
}
