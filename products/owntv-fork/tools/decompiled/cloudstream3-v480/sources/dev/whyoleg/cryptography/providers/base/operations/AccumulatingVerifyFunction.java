package dev.whyoleg.cryptography.providers.base.operations;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.operations.UpdateFunction;
import dev.whyoleg.cryptography.operations.VerifyFunction;
import dev.whyoleg.cryptography.providers.base.BytesKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: AccumulatingVerifyFunction.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001BC\bF\u00128\u0010\u0002\u001a4\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0003¢\u0006\u0004\b\n\u0010\u000bJ\n\u0010\u000f\u001a\u00020\u0010H\u0082\u0080\u0004J\"\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0096\u0080\u0004J\"\u0010\u0016\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0096\u0080\u0004J\"\u0010\u0002\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0096\u0080\u0004J$\u0010\u0017\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0082\u0080\u0004J\n\u0010\u0018\u001a\u00020\u0010H\u0096\u0080\u0004J\b\u0010\u0019\u001a\u00020\u0010H\u0016RA\u0010\u0002\u001a4\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\f\u001a\u00020\rX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000e\u001a\u00020\u0004X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/AccumulatingVerifyFunction;", "Ldev/whyoleg/cryptography/operations/VerifyFunction;", "verify", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "data", "signature", "", "<init>", "(Lkotlin/jvm/functions/Function2;)V", "isClosed", "", "accumulator", "ensureNotClosed", "", "update", "source", "startIndex", "", "endIndex", "tryVerify", "verifyError", "reset", "close", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AccumulatingVerifyFunction implements VerifyFunction {
    private byte[] accumulator;
    private boolean isClosed;
    private final Function2<byte[], byte[], String> verify;

    @Override // dev.whyoleg.cryptography.operations.VerifyFunction
    public /* synthetic */ boolean tryVerify(ByteString byteString, int i, int i2) {
        return VerifyFunction.CC.$default$tryVerify(this, byteString, i, i2);
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public /* synthetic */ void update(RawSource rawSource) {
        UpdateFunction.CC.$default$update(this, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public /* synthetic */ void update(ByteString byteString, int i, int i2) {
        UpdateFunction.CC.$default$update(this, byteString, i, i2);
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public /* synthetic */ RawSink updatingSink(RawSink rawSink) {
        return UpdateFunction.CC.$default$updatingSink(this, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public /* synthetic */ RawSource updatingSource(RawSource rawSource) {
        return UpdateFunction.CC.$default$updatingSource(this, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.VerifyFunction
    public /* synthetic */ void verify(ByteString byteString, int i, int i2) {
        VerifyFunction.CC.$default$verify(this, byteString, i, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AccumulatingVerifyFunction(Function2<? super byte[], ? super byte[], String> verify) {
        Intrinsics.checkNotNullParameter(verify, "verify");
        this.verify = verify;
        this.accumulator = BytesKt.getEmptyByteArray();
    }

    private final void ensureNotClosed() {
        if (this.isClosed) {
            throw new IllegalStateException("Already closed".toString());
        }
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public void update(byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        ensureNotClosed();
        BytesKt.checkBounds(source.length, startIndex, endIndex);
        this.accumulator = ArraysKt.plus(this.accumulator, ArraysKt.copyOfRange(source, startIndex, endIndex));
    }

    @Override // dev.whyoleg.cryptography.operations.VerifyFunction
    public boolean tryVerify(byte[] signature, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        return verifyError(signature, startIndex, endIndex) == null;
    }

    @Override // dev.whyoleg.cryptography.operations.VerifyFunction
    public void verify(byte[] signature, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        String strVerifyError = verifyError(signature, startIndex, endIndex);
        if (strVerifyError == null) {
            return;
        }
        throw new IllegalStateException(("Invalid signature: " + strVerifyError).toString());
    }

    private final String verifyError(byte[] signature, int startIndex, int endIndex) {
        ensureNotClosed();
        BytesKt.checkBounds(signature.length, startIndex, endIndex);
        String strInvoke = this.verify.invoke(this.accumulator, ArraysKt.copyOfRange(signature, startIndex, endIndex));
        reset();
        return strInvoke;
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public void reset() {
        ensureNotClosed();
        this.accumulator = BytesKt.getEmptyByteArray();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.isClosed = true;
        this.accumulator = BytesKt.getEmptyByteArray();
    }
}
