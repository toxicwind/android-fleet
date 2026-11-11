package dev.whyoleg.cryptography.providers.base.operations;

import com.google.android.gms.cast.MediaTrack;
import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.operations.SignFunction;
import dev.whyoleg.cryptography.operations.UpdateFunction;
import dev.whyoleg.cryptography.providers.base.BytesKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: AccumulatingSignFunction.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001d\bF\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\n\u001a\u00020\u000bH\u0082\u0080\u0004J\"\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0096\u0080\u0004J\u001a\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u000fH\u0096\u0080\u0004J\n\u0010\u0014\u001a\u00020\u0004H\u0096\u0080\u0004J\n\u0010\u0015\u001a\u00020\u000bH\u0096\u0080\u0004J\b\u0010\u0016\u001a\u00020\u000bH\u0016R\u001b\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\u0004X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/AccumulatingSignFunction;", "Ldev/whyoleg/cryptography/operations/SignFunction;", MediaTrack.ROLE_SIGN, "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "isClosed", "", "accumulator", "ensureNotClosed", "", "update", "source", "startIndex", "", "endIndex", "signIntoByteArray", "destination", "destinationOffset", "signToByteArray", "reset", "close", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AccumulatingSignFunction implements SignFunction {
    private byte[] accumulator;
    private boolean isClosed;
    private final Function1<byte[], byte[]> sign;

    @Override // dev.whyoleg.cryptography.operations.SignFunction
    public /* synthetic */ ByteString sign() {
        return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(signToByteArray());
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

    /* JADX WARN: Multi-variable type inference failed */
    public AccumulatingSignFunction(Function1<? super byte[], byte[]> sign) {
        Intrinsics.checkNotNullParameter(sign, "sign");
        this.sign = sign;
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

    @Override // dev.whyoleg.cryptography.operations.SignFunction
    public int signIntoByteArray(byte[] destination, int destinationOffset) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        byte[] bArrSignToByteArray = signToByteArray();
        BytesKt.checkBounds(destination.length, destinationOffset, bArrSignToByteArray.length + destinationOffset);
        ArraysKt.copyInto$default(bArrSignToByteArray, destination, destinationOffset, 0, 0, 12, (Object) null);
        return bArrSignToByteArray.length;
    }

    @Override // dev.whyoleg.cryptography.operations.SignFunction
    public byte[] signToByteArray() {
        ensureNotClosed();
        byte[] bArrInvoke = this.sign.invoke(this.accumulator);
        reset();
        return bArrInvoke;
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
