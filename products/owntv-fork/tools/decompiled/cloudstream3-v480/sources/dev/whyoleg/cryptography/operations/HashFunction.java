package dev.whyoleg.cryptography.operations;

import kotlin.Metadata;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: Hash.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003H¦\u0080\u0004J\n\u0010\u0007\u001a\u00020\u0005H¦\u0080\u0004J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/HashFunction;", "Ldev/whyoleg/cryptography/operations/UpdateFunction;", "hashIntoByteArray", "", "destination", "", "destinationOffset", "hashToByteArray", "hash", "Lkotlinx/io/bytestring/ByteString;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface HashFunction extends UpdateFunction {
    ByteString hash();

    int hashIntoByteArray(byte[] destination, int destinationOffset);

    byte[] hashToByteArray();

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.HashFunction$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Hash.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ int hashIntoByteArray$default(HashFunction hashFunction, byte[] bArr, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: hashIntoByteArray");
            }
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return hashFunction.hashIntoByteArray(bArr, i);
        }
    }
}
