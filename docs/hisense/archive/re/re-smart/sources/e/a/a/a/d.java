package e.a.a.a;

import com.google.common.net.InetAddresses;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char f2154a;

    static {
        Character.toString(InetAddresses.IPV4_DELIMITER);
        f2154a = File.separatorChar;
        char c2 = f2154a;
    }

    public static boolean a() {
        return f2154a == '\\';
    }
}
