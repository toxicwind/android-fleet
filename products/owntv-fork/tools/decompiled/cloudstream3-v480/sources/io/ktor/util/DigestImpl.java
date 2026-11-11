package io.ktor.util;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CryptoJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0083@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000e\u001a\u00020\bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0011\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0017\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004¢\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u001b\u001a\u00020\u0018HÖ\u0081\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001f\u001a\u00020\u001cHÖ\u0081\u0004¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\"\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006#"}, d2 = {"Lio/ktor/util/DigestImpl;", "Lio/ktor/util/Digest;", "Ljava/security/MessageDigest;", "delegate", "constructor-impl", "(Ljava/security/MessageDigest;)Ljava/security/MessageDigest;", "", "bytes", "", "plusAssign-impl", "(Ljava/security/MessageDigest;[B)V", "plusAssign", "reset-impl", "(Ljava/security/MessageDigest;)V", "reset", "build-impl", "(Ljava/security/MessageDigest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "other", "", "equals-impl", "(Ljava/security/MessageDigest;Ljava/lang/Object;)Z", "equals", "", "hashCode-impl", "(Ljava/security/MessageDigest;)I", "hashCode", "", "toString-impl", "(Ljava/security/MessageDigest;)Ljava/lang/String;", "toString", "Ljava/security/MessageDigest;", "getDelegate", "()Ljava/security/MessageDigest;", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
final class DigestImpl implements Digest {
    private final MessageDigest delegate;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ DigestImpl m8823boximpl(MessageDigest messageDigest) {
        return new DigestImpl(messageDigest);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static MessageDigest m8825constructorimpl(MessageDigest delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        return delegate;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m8826equalsimpl(MessageDigest messageDigest, Object obj) {
        return (obj instanceof DigestImpl) && Intrinsics.areEqual(messageDigest, ((DigestImpl) obj).m8832unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m8827equalsimpl0(MessageDigest messageDigest, MessageDigest messageDigest2) {
        return Intrinsics.areEqual(messageDigest, messageDigest2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m8828hashCodeimpl(MessageDigest messageDigest) {
        return messageDigest.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m8831toStringimpl(MessageDigest messageDigest) {
        return "DigestImpl(delegate=" + messageDigest + ')';
    }

    public boolean equals(Object obj) {
        return m8826equalsimpl(this.delegate, obj);
    }

    public int hashCode() {
        return m8828hashCodeimpl(this.delegate);
    }

    public String toString() {
        return m8831toStringimpl(this.delegate);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ MessageDigest m8832unboximpl() {
        return this.delegate;
    }

    private /* synthetic */ DigestImpl(MessageDigest messageDigest) {
        this.delegate = messageDigest;
    }

    public final MessageDigest getDelegate() {
        return this.delegate;
    }

    @Override // io.ktor.util.Digest
    public void plusAssign(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        m8829plusAssignimpl(this.delegate, bytes);
    }

    /* JADX INFO: renamed from: plusAssign-impl, reason: not valid java name */
    public static void m8829plusAssignimpl(MessageDigest messageDigest, byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        messageDigest.update(bytes);
    }

    @Override // io.ktor.util.Digest
    public void reset() {
        m8830resetimpl(this.delegate);
    }

    /* JADX INFO: renamed from: reset-impl, reason: not valid java name */
    public static void m8830resetimpl(MessageDigest messageDigest) {
        messageDigest.reset();
    }

    /* JADX INFO: renamed from: build-impl, reason: not valid java name */
    public static Object m8824buildimpl(MessageDigest messageDigest, Continuation<? super byte[]> continuation) {
        byte[] bArrDigest = messageDigest.digest();
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return bArrDigest;
    }

    @Override // io.ktor.util.Digest
    public Object build(Continuation<? super byte[]> continuation) {
        return m8824buildimpl(this.delegate, continuation);
    }
}
