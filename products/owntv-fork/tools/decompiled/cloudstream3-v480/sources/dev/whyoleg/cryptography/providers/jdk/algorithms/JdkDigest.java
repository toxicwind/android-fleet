package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.operations.HashFunction;
import dev.whyoleg.cryptography.operations.Hasher;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkDigest.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B'\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b¢\u0006\u0004\b\t\u0010\nJ\n\u0010\u0011\u001a\u00020\u0001H\u0096\u0080\u0004J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004R\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\bX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\f\u0012\b\u0012\u00060\u000fj\u0002`\u00100\u000eX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDigest;", "Ldev/whyoleg/cryptography/operations/Hasher;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "algorithm", "", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/lang/String;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "messageDigest", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljava/security/MessageDigest;", "Ldev/whyoleg/cryptography/providers/jdk/JMessageDigest;", "hasher", "createHashFunction", "Ldev/whyoleg/cryptography/operations/HashFunction;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkDigest implements Hasher, Digest {
    private final CryptographyAlgorithmId<Digest> id;
    private final Pooled<MessageDigest> messageDigest;

    @Override // dev.whyoleg.cryptography.operations.Hasher
    public /* synthetic */ Object hash(RawSource rawSource, Continuation continuation) {
        return Hasher.CC.hash$suspendImpl(this, rawSource, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.Hasher
    public /* synthetic */ Object hash(ByteString byteString, Continuation continuation) {
        return Hasher.CC.hash$suspendImpl(this, byteString, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.Hasher
    public /* synthetic */ Object hash(byte[] bArr, Continuation continuation) {
        return Hasher.CC.hash$suspendImpl(this, bArr, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.Hasher
    public /* synthetic */ ByteString hashBlocking(RawSource rawSource) {
        return Hasher.CC.$default$hashBlocking(this, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.Hasher
    public /* synthetic */ ByteString hashBlocking(ByteString byteString) {
        return Hasher.CC.$default$hashBlocking(this, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.Hasher
    public /* synthetic */ byte[] hashBlocking(byte[] bArr) {
        return Hasher.CC.$default$hashBlocking(this, bArr);
    }

    public JdkDigest(JdkCryptographyState state, String algorithm, CryptographyAlgorithmId<Digest> id) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
        this.messageDigest = state.messageDigest(algorithm);
    }

    @Override // dev.whyoleg.cryptography.algorithms.Digest, dev.whyoleg.cryptography.CryptographyAlgorithm
    public CryptographyAlgorithmId<Digest> getId() {
        return this.id;
    }

    @Override // dev.whyoleg.cryptography.algorithms.Digest
    public Hasher hasher() {
        return this;
    }

    @Override // dev.whyoleg.cryptography.operations.Hasher
    public HashFunction createHashFunction() {
        Pooled.Resource<MessageDigest> resourceBorrowResource = this.messageDigest.borrowResource();
        resourceBorrowResource.access().reset();
        return new JdkHashFunction(resourceBorrowResource);
    }
}
