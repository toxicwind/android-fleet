package dev.whyoleg.cryptography.operations;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.CoreKt;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: UpdateFunction.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00060\u0001j\u0002`\u0002J\n\u0010\u0003\u001a\u00020\u0004H¦\u0080\u0004J&\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH¦\u0080\u0004J&\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u000b2\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH\u0096\u0080\u0004J\u0012\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\fH\u0096\u0080\u0004J\u0012\u0010\r\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\fH\u0096\u0080\u0004J\u0012\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0096\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/UpdateFunction;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "reset", "", "update", "source", "", "startIndex", "", "endIndex", "Lkotlinx/io/bytestring/ByteString;", "Lkotlinx/io/RawSource;", "updatingSource", "updatingSink", "Lkotlinx/io/RawSink;", "sink", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface UpdateFunction extends AutoCloseable {
    void reset();

    void update(RawSource source);

    void update(ByteString source, int startIndex, int endIndex);

    void update(byte[] source, int startIndex, int endIndex);

    RawSink updatingSink(RawSink sink);

    RawSource updatingSource(RawSource source);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.UpdateFunction$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: UpdateFunction.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ void update$default(UpdateFunction updateFunction, byte[] bArr, int i, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: update");
            }
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = bArr.length;
            }
            updateFunction.update(bArr, i, i2);
        }

        public static /* synthetic */ void update$default(UpdateFunction updateFunction, ByteString byteString, int i, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: update");
            }
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = byteString.getSize();
            }
            updateFunction.update(byteString, i, i2);
        }

        public static void $default$update(UpdateFunction _this, RawSource source) {
            Intrinsics.checkNotNullParameter(source, "source");
            CoreKt.buffered(_this.updatingSource(source)).transferTo(CoreKt.discardingSink());
        }

        public static RawSource $default$updatingSource(UpdateFunction _this, RawSource source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new UpdatingSource(source, _this);
        }

        public static RawSink $default$updatingSink(UpdateFunction _this, RawSink sink) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            return new UpdatingSink(sink, _this);
        }

        public static void $default$update(UpdateFunction _this, ByteString source, int i, int i2) {
            Intrinsics.checkNotNullParameter(source, "source");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            _this.update(source.getData(), i, i2);
        }
    }
}
