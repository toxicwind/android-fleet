package kotlinx.io.bytestring;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: ByteStringJvmExt.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004\u001a\u0016\u0010\u0005\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004\u001a\u000e\u0010\u0006\u001a\u00020\u0007*\u00020\u0002H\u0086\u0080\u0004\u001a\u0018\u0010\b\u001a\u00020\u0002*\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u0086\u0080\u0004\u001a\u001e\u0010\b\u001a\u00020\u0002*\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0086\u0080\u0004\u001a\u0016\u0010\f\u001a\u00020\r*\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0002H\u0086\u0080\u0004\u001a\u001e\u0010\f\u001a\u00020\r*\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u0002H\u0086\u0080\u0004\u001a\u001e\u0010\u000f\u001a\u00020\r*\u00020\u00072\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0082\u0080\u0004¨\u0006\u0011"}, d2 = {"decodeToString", "", "Lkotlinx/io/bytestring/ByteString;", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "encodeToByteString", "asReadOnlyByteBuffer", "Ljava/nio/ByteBuffer;", "getByteString", "length", "", "at", "putByteString", "", TypedValues.Custom.S_STRING, "checkIndexAndCapacity", "idx", "kotlinx-io-bytestring"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteStringJvmExtKt {
    public static final String decodeToString(ByteString byteString, Charset charset) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return new String(byteString.getData(), charset);
    }

    public static final ByteString encodeToByteString(String str, Charset charset) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        ByteString.Companion companion = ByteString.INSTANCE;
        byte[] bytes = str.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return companion.wrap$kotlinx_io_bytestring(bytes);
    }

    public static final ByteBuffer asReadOnlyByteBuffer(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
        ByteBuffer byteBufferAsReadOnlyBuffer = ByteBuffer.wrap(byteString.getData()).asReadOnlyBuffer();
        Intrinsics.checkNotNullExpressionValue(byteBufferAsReadOnlyBuffer, "asReadOnlyBuffer(...)");
        return byteBufferAsReadOnlyBuffer;
    }

    public static /* synthetic */ ByteString getByteString$default(ByteBuffer byteBuffer, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = byteBuffer.remaining();
        }
        return getByteString(byteBuffer, i);
    }

    public static final ByteString getByteString(ByteBuffer byteBuffer, int i) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        if (i < 0) {
            throw new IndexOutOfBoundsException("length should be non-negative (was " + i + ')');
        }
        if (byteBuffer.remaining() < i) {
            throw new IndexOutOfBoundsException("length (" + i + ") exceeds remaining bytes count ({" + byteBuffer.remaining() + "})");
        }
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(bArr);
    }

    public static final ByteString getByteString(ByteBuffer byteBuffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        checkIndexAndCapacity(byteBuffer, i, i2);
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = byteBuffer.get(i + i3);
        }
        return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(bArr);
    }

    public static final void putByteString(ByteBuffer byteBuffer, ByteString string) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
        byteBuffer.put(string.getData());
    }

    public static final void putByteString(ByteBuffer byteBuffer, int i, ByteString string) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        checkIndexAndCapacity(byteBuffer, i, string.getSize());
        IntRange indices = ByteStringKt.getIndices(string);
        int first = indices.getFirst();
        int last = indices.getLast();
        if (first > last) {
            return;
        }
        while (true) {
            byteBuffer.put(i + first, string.get(first));
            if (first == last) {
                return;
            } else {
                first++;
            }
        }
    }

    private static final void checkIndexAndCapacity(ByteBuffer byteBuffer, int i, int i2) {
        if (i < 0 || i >= byteBuffer.limit()) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of this ByteBuffer's bounds: [0, " + byteBuffer.limit() + ')');
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("length should be non-negative (was " + i2 + ')');
        }
        if (i + i2 <= byteBuffer.limit()) {
            return;
        }
        throw new IndexOutOfBoundsException("There's not enough space to put ByteString of length " + i2 + " starting from index " + i);
    }
}
