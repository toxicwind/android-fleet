package io.ktor.http.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DigestAlgorithm.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lio/ktor/http/auth/DigestAlgorithm;", "Ljava/security/MessageDigest;", "toDigester", "(Lio/ktor/http/auth/DigestAlgorithm;)Ljava/security/MessageDigest;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DigestAlgorithm_jvmKt {
    public static final MessageDigest toDigester(DigestAlgorithm digestAlgorithm) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(digestAlgorithm, "<this>");
        MessageDigest messageDigest = MessageDigest.getInstance(digestAlgorithm.getHashName());
        Intrinsics.checkNotNullExpressionValue(messageDigest, "getInstance(...)");
        return messageDigest;
    }
}
