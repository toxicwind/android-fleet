package dev.whyoleg.cryptography.providers.base.operations;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import kotlin.Metadata;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;

/* JADX INFO: compiled from: BaseCipherFunction.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H¦\u0080\u0004J\u0012\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\tH¦\u0080\u0004J\u0012\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "", "transform", "", "source", "startIndex", "", "endIndex", "transformedSource", "Lkotlinx/io/RawSource;", "transformedSink", "Lkotlinx/io/RawSink;", "sink", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface CipherFunction {
    byte[] transform(byte[] source, int startIndex, int endIndex);

    RawSink transformedSink(RawSink sink);

    RawSource transformedSource(RawSource source);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.providers.base.operations.CipherFunction$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: BaseCipherFunction.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ byte[] transform$default(CipherFunction cipherFunction, byte[] bArr, int i, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transform");
            }
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = bArr.length;
            }
            return cipherFunction.transform(bArr, i, i2);
        }
    }
}
