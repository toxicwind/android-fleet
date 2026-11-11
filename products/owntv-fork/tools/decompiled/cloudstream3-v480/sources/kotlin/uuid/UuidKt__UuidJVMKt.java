package kotlin.uuid;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: UuidJVM.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0080\u0080\u0004\u001a\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0081\u0080\u0004\u001a\u0016\u0010\b\u001a\u00020\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0081\u0080\u0004\u001a.\u0010\f\u001a\u00020\u0001*\u00020\t2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0081\u0080\u0004\u001a\u001e\u0010\u0011\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\tH\u0081\u0080\u0004\u001a\u0012\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0081\u0080\u0004\u001a\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0081\u0080\u0004\u001a\u0012\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0015H\u0081\u0080\u0004\u001a\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0018\u001a\u00020\u0015H\u0081\u0080\u0004\u001a\u000e\u0010\u001a\u001a\u00020\u0007*\u00020\u001bH\u0087\u0088\u0004\u001a\u000e\u0010\u001c\u001a\u00020\u001b*\u00020\u0007H\u0087\u0088\u0004\u001a\u000e\u0010\u001d\u001a\u00020\u0007*\u00020\u001eH\u0087\u0080\u0004\u001a\u0016\u0010\u001d\u001a\u00020\u0007*\u00020\u001e2\u0006\u0010\n\u001a\u00020\u000bH\u0087\u0080\u0004\u001a\u0016\u0010\u001f\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010\u0006\u001a\u00020\u0007H\u0087\u0080\b\u001a\u001e\u0010\u001f\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0087\u0080\b\u001a\u000e\u0010 \u001a\u00020\t*\u00020\tH\u0080\u0088\u0004¨\u0006!"}, d2 = {"secureRandomBytes", "", "destination", "", "serializedUuid", "", "uuid", "Lkotlin/uuid/Uuid;", "getLongAt", "", "index", "", "formatBytesInto", "dst", "dstOffset", "startIndex", "endIndex", "setLongAt", ES6Iterator.VALUE_PROPERTY, "uuidParseHexDash", "hexDashString", "", "uuidParseHexDashOrNull", "uuidParseHex", "hexString", "uuidParseHexOrNull", "toKotlinUuid", "Ljava/util/UUID;", "toJavaUuid", "getUuid", "Ljava/nio/ByteBuffer;", "putUuid", "reverseBytes", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/uuid/UuidKt")
class UuidKt__UuidJVMKt {
    public static final void secureRandomBytes(byte[] destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        SecureRandomHolder.INSTANCE.getInstance().nextBytes(destination);
    }

    public static final Object serializedUuid(Uuid uuid) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        return new UuidSerialized(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }

    public static final long getLongAt(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return UuidKt.getLongAtCommonImpl(bArr, i);
    }

    public static final void formatBytesInto(long j, byte[] dst, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(dst, "dst");
        UuidKt.formatBytesIntoCommonImpl(j, dst, i, i2, i3);
    }

    public static final void setLongAt(byte[] bArr, int i, long j) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        UuidKt.setLongAtCommonImpl(bArr, i, j);
    }

    public static final Uuid uuidParseHexDash(String hexDashString) {
        Intrinsics.checkNotNullParameter(hexDashString, "hexDashString");
        return UuidKt.uuidParseHexDashCommonImpl(hexDashString);
    }

    public static final Uuid uuidParseHexDashOrNull(String hexDashString) {
        Intrinsics.checkNotNullParameter(hexDashString, "hexDashString");
        return UuidKt.uuidParseHexDashOrNullCommonImpl(hexDashString);
    }

    public static final Uuid uuidParseHex(String hexString) {
        Intrinsics.checkNotNullParameter(hexString, "hexString");
        return UuidKt.uuidParseHexCommonImpl(hexString);
    }

    public static final Uuid uuidParseHexOrNull(String hexString) {
        Intrinsics.checkNotNullParameter(hexString, "hexString");
        return UuidKt.uuidParseHexOrNullCommonImpl(hexString);
    }

    public static final Uuid toKotlinUuid(UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "<this>");
        return Uuid.INSTANCE.fromLongs(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }

    public static final UUID toJavaUuid(Uuid uuid) {
        Intrinsics.checkNotNullParameter(uuid, "<this>");
        return new UUID(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }

    public static final Uuid getUuid(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        if (byteBuffer.position() + 15 >= byteBuffer.limit()) {
            throw new BufferUnderflowException();
        }
        long jReverseBytes = byteBuffer.getLong();
        long jReverseBytes2 = byteBuffer.getLong();
        if (Intrinsics.areEqual(byteBuffer.order(), ByteOrder.LITTLE_ENDIAN)) {
            jReverseBytes = Long.reverseBytes(jReverseBytes);
            jReverseBytes2 = Long.reverseBytes(jReverseBytes2);
        }
        return Uuid.INSTANCE.fromLongs(jReverseBytes, jReverseBytes2);
    }

    public static final Uuid getUuid(ByteBuffer byteBuffer, int i) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        if (i < 0) {
            throw new IndexOutOfBoundsException("Negative index: " + i);
        }
        if (i + 15 >= byteBuffer.limit()) {
            throw new IndexOutOfBoundsException("Not enough bytes to read a uuid at index: " + i + ", with limit: " + byteBuffer.limit() + ' ');
        }
        long jReverseBytes = byteBuffer.getLong(i);
        long jReverseBytes2 = byteBuffer.getLong(i + 8);
        if (Intrinsics.areEqual(byteBuffer.order(), ByteOrder.LITTLE_ENDIAN)) {
            jReverseBytes = Long.reverseBytes(jReverseBytes);
            jReverseBytes2 = Long.reverseBytes(jReverseBytes2);
        }
        return Uuid.INSTANCE.fromLongs(jReverseBytes, jReverseBytes2);
    }

    @IgnorableReturnValue
    public static final ByteBuffer putUuid(ByteBuffer byteBuffer, Uuid uuid) {
        ByteBuffer byteBufferPutLong;
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        if (byteBuffer.position() + 15 >= byteBuffer.limit()) {
            throw new BufferOverflowException();
        }
        if (Intrinsics.areEqual(byteBuffer.order(), ByteOrder.BIG_ENDIAN)) {
            byteBuffer.putLong(mostSignificantBits);
            byteBufferPutLong = byteBuffer.putLong(leastSignificantBits);
        } else {
            byteBuffer.putLong(Long.reverseBytes(mostSignificantBits));
            byteBufferPutLong = byteBuffer.putLong(Long.reverseBytes(leastSignificantBits));
        }
        Intrinsics.checkNotNullExpressionValue(byteBufferPutLong, "toLongs(...)");
        return byteBufferPutLong;
    }

    @IgnorableReturnValue
    public static final ByteBuffer putUuid(ByteBuffer byteBuffer, int i, Uuid uuid) {
        ByteBuffer byteBufferPutLong;
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        if (i < 0) {
            throw new IndexOutOfBoundsException("Negative index: " + i);
        }
        if (i + 15 >= byteBuffer.limit()) {
            throw new IndexOutOfBoundsException("Not enough capacity to write a uuid at index: " + i + ", with limit: " + byteBuffer.limit() + ' ');
        }
        if (Intrinsics.areEqual(byteBuffer.order(), ByteOrder.BIG_ENDIAN)) {
            byteBuffer.putLong(i, mostSignificantBits);
            byteBufferPutLong = byteBuffer.putLong(i + 8, leastSignificantBits);
        } else {
            byteBuffer.putLong(i, Long.reverseBytes(mostSignificantBits));
            byteBufferPutLong = byteBuffer.putLong(i + 8, Long.reverseBytes(leastSignificantBits));
        }
        Intrinsics.checkNotNullExpressionValue(byteBufferPutLong, "toLongs(...)");
        return byteBufferPutLong;
    }

    public static final long reverseBytes(long j) {
        return Long.reverseBytes(j);
    }
}
