package j$.desugar.sun.nio.fs;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
abstract class DesugarUtil {
    private static final Charset jnuEncoding = StandardCharsets.UTF_8;

    static Charset jnuEncoding() {
        return jnuEncoding;
    }

    static Set newSet(Object... objArr) {
        HashSet hashSet = new HashSet();
        for (Object obj : objArr) {
            hashSet.add(obj);
        }
        return hashSet;
    }
}
