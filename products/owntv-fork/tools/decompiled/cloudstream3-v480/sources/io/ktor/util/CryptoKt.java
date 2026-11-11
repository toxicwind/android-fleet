package io.ktor.util;

import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"io/ktor/util/CryptoKt__CryptoJvmKt", "io/ktor/util/CryptoKt__CryptoKt"}, k = 4, mv = {2, 3, 0}, xi = 48)
public final class CryptoKt {
    public static final int NONCE_SIZE_IN_BYTES = 16;
    public static final int NONCE_SIZE_IN_CHARS = 32;

    public static final Digest Digest(String str) {
        return CryptoKt__CryptoJvmKt.Digest(str);
    }

    public static final Object build(Digest digest, String str, Charset charset, Continuation<? super byte[]> continuation) {
        return CryptoKt__CryptoKt.build(digest, str, charset, continuation);
    }

    public static final Object build(Digest digest, byte[] bArr, Continuation<? super byte[]> continuation) {
        return CryptoKt__CryptoKt.build(digest, bArr, continuation);
    }

    @Deprecated(message = "Use generateNonceBlocking in blocking contexts and generateNonceSuspend in non-blocking contexts", replaceWith = @ReplaceWith(expression = "generateNonceBlocking()", imports = {}))
    public static final String generateNonce() {
        return CryptoKt__CryptoKt.generateNonce();
    }

    @Deprecated(message = "Use generateNonceBlocking in blocking contexts and generateNonceSuspend in non-blocking contexts", replaceWith = @ReplaceWith(expression = "generateNonceBlocking(size).toByteArray()", imports = {"io.ktor.utils.io.core.toByteArray"}))
    public static final byte[] generateNonce(int i) {
        return CryptoKt__CryptoKt.generateNonce(i);
    }

    public static final String generateNonceBlocking(int i) {
        return CryptoKt__CryptoJvmKt.generateNonceBlocking(i);
    }

    public static final Object generateNonceSuspend(int i, Continuation<? super String> continuation) {
        return CryptoKt__CryptoJvmKt.generateNonceSuspend(i, continuation);
    }

    public static final Function1<String, byte[]> getDigestFunction(String str, Function1<? super String, String> function1) {
        return CryptoKt__CryptoJvmKt.getDigestFunction(str, function1);
    }

    @Deprecated(message = "prefer kotlin.text.toHexString", replaceWith = @ReplaceWith(expression = "bytes.toHexString()", imports = {"kotlin.text.toHexString"}))
    public static final String hex(byte[] bArr) {
        return CryptoKt__CryptoKt.hex(bArr);
    }

    @Deprecated(message = "prefer kotlin.text.hexToByteArray", replaceWith = @ReplaceWith(expression = "s.hexToByteArray()", imports = {"kotlin.text.hexToByteArray"}))
    public static final byte[] hex(String str) {
        return CryptoKt__CryptoKt.hex(str);
    }

    public static final byte[] sha1(byte[] bArr) {
        return CryptoKt__CryptoJvmKt.sha1(bArr);
    }
}
