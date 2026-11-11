package kotlinx.io.bytestring;

import com.google.common.base.Ascii;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteString.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001 B\u001b\bB\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B%\bV\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\u000bJ\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u0096\u0082\u0004J\n\u0010\f\u001a\u00020\tH\u0096\u0080\u0004J\u0012\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\tH\u0086\u0082\u0004J\u001e\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH\u0086\u0080\u0004J0\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH\u0086\u0080\u0004J\u001c\u0010\u001b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH\u0086\u0080\u0004J\u0012\u0010\u001c\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0000H\u0096\u0082\u0004J\n\u0010\u001d\u001a\u00020\u001eH\u0096\u0080\u0004J\n\u0010\u001f\u001a\u00020\u0003H\u0081\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u00020\t8\u0002@\u0002X\u0083\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\r\u001a\u00020\t8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006!"}, d2 = {"Lkotlinx/io/bytestring/ByteString;", "", "data", "", "dummy", "", "<init>", "([BLjava/lang/Object;)V", "startIndex", "", "endIndex", "([BII)V", "hashCode", ContentDisposition.Parameters.Size, "getSize", "()I", "equals", "", "other", "get", "", "index", "toByteArray", "copyInto", "", "destination", "destinationOffset", "substring", "compareTo", "toString", "", "getBackingArrayReference", "Companion", "kotlinx-io-bytestring"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ByteString implements Comparable<ByteString> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ByteString EMPTY = new ByteString(new byte[0], null);
    private static final char[] HEX_DIGITS;
    private final byte[] data;

    @BenignDataRace
    private int hashCode;

    public /* synthetic */ ByteString(byte[] bArr, Object obj, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, obj);
    }

    private ByteString(byte[] bArr, Object obj) {
        this.data = bArr;
    }

    public /* synthetic */ ByteString(byte[] bArr, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? bArr.length : i2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ByteString(byte[] data, int i, int i2) {
        this(ArraysKt.copyOfRange(data, i, i2), null);
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX INFO: compiled from: ByteString.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0080\u0080\u0004¢\u0006\u0002\b\u000bR\u0015\u0010\u0004\u001a\u00020\u0005X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000f\u0010\f\u001a\u00020\rX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/io/bytestring/ByteString$Companion;", "", "<init>", "()V", "EMPTY", "Lkotlinx/io/bytestring/ByteString;", "getEMPTY$kotlinx_io_bytestring", "()Lkotlinx/io/bytestring/ByteString;", "wrap", "byteArray", "", "wrap$kotlinx_io_bytestring", "HEX_DIGITS", "", "kotlinx-io-bytestring"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ByteString getEMPTY$kotlinx_io_bytestring() {
            return ByteString.EMPTY;
        }

        public final ByteString wrap$kotlinx_io_bytestring(byte[] byteArray) {
            Intrinsics.checkNotNullParameter(byteArray, "byteArray");
            DefaultConstructorMarker defaultConstructorMarker = null;
            return new ByteString(byteArray, defaultConstructorMarker, defaultConstructorMarker);
        }
    }

    static {
        char[] charArray = "0123456789abcdef".toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        HEX_DIGITS = charArray;
    }

    public final int getSize() {
        return this.data.length;
    }

    public boolean equals(Object other) {
        int i;
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ByteString byteString = (ByteString) other;
        byte[] bArr = byteString.data;
        int length = bArr.length;
        byte[] bArr2 = this.data;
        if (length != bArr2.length) {
            return false;
        }
        int i2 = byteString.hashCode;
        if (i2 == 0 || (i = this.hashCode) == 0 || i2 == i) {
            return Arrays.equals(bArr2, bArr);
        }
        return false;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = Arrays.hashCode(this.data);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    public final byte get(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("index (" + index + ") is out of byte string bounds: [0.." + getSize() + ')');
        }
        return this.data[index];
    }

    public static /* synthetic */ byte[] toByteArray$default(ByteString byteString, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = byteString.getSize();
        }
        return byteString.toByteArray(i, i2);
    }

    public final byte[] toByteArray(int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            throw new IllegalArgumentException(("startIndex (" + startIndex + ") > endIndex (" + endIndex + ')').toString());
        }
        return ArraysKt.copyOfRange(this.data, startIndex, endIndex);
    }

    public static /* synthetic */ void copyInto$default(ByteString byteString, byte[] bArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = byteString.getSize();
        }
        byteString.copyInto(bArr, i, i2, i3);
    }

    public final void copyInto(byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (startIndex > endIndex) {
            throw new IllegalArgumentException(("startIndex (" + startIndex + ") > endIndex (" + endIndex + ')').toString());
        }
        ArraysKt.copyInto(this.data, destination, destinationOffset, startIndex, endIndex);
    }

    public static /* synthetic */ ByteString substring$default(ByteString byteString, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = byteString.getSize();
        }
        return byteString.substring(i, i2);
    }

    public final ByteString substring(int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return EMPTY;
        }
        return new ByteString(this.data, startIndex, endIndex);
    }

    @Override // java.lang.Comparable
    public int compareTo(ByteString other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (other == this) {
            return 0;
        }
        byte[] bArr = this.data;
        byte[] bArr2 = other.data;
        int iMin = Math.min(getSize(), other.getSize());
        for (int i = 0; i < iMin; i++) {
            int iCompare = Intrinsics.compare(UByte.m8954constructorimpl(bArr[i]) & 255, UByte.m8954constructorimpl(bArr2[i]) & 255);
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Intrinsics.compare(getSize(), other.getSize());
    }

    public String toString() {
        if (ByteStringKt.isEmpty(this)) {
            return "ByteString(size=0)";
        }
        String strValueOf = String.valueOf(getSize());
        StringBuilder sb = new StringBuilder(strValueOf.length() + 22 + (getSize() * 2));
        sb.append("ByteString(size=");
        sb.append(strValueOf);
        sb.append(" hex=");
        byte[] bArr = this.data;
        int size = getSize();
        for (int i = 0; i < size; i++) {
            byte b = bArr[i];
            char[] cArr = HEX_DIGITS;
            sb.append(cArr[(b >>> 4) & 15]);
            sb.append(cArr[b & Ascii.SI]);
        }
        sb.append(')');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: renamed from: getBackingArrayReference, reason: from getter */
    public final byte[] getData() {
        return this.data;
    }
}
