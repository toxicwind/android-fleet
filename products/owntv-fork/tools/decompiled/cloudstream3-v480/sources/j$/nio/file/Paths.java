package j$.nio.file;

import java.net.URI;

/* JADX INFO: loaded from: classes3.dex */
public final class Paths {
    public static Path get(String str, String... strArr) {
        return Path.CC.of(str, strArr);
    }

    public static Path get(URI uri) {
        return Path.CC.of(uri);
    }
}
