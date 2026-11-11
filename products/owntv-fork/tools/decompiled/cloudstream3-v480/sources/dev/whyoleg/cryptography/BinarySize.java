package dev.whyoleg.cryptography;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BinarySize.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001 B\u0011\bB\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000H\u0086\u0082\u0004¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000H\u0086\u0082\u0004¢\u0006\u0004\b\u000f\u0010\rJ\u0019\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0003H\u0086\u0082\u0004¢\u0006\u0004\b\u0011\u0010\rJ\u0019\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0003H\u0086\u0082\u0004¢\u0006\u0004\b\u0013\u0010\rJ\u0019\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0003H\u0086\u0082\u0004¢\u0006\u0004\b\u0015\u0010\rJ\u0019\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0000H\u0096\u0082\u0004¢\u0006\u0004\b\u0017\u0010\rJ\u0011\u0010\u0018\u001a\u00020\u0019H\u0096\u0080\u0004¢\u0006\u0004\b\u001a\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u000b\u001a\u0004\u0018\u00010\u001eHÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0006\u001a\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0015\u0010\b\u001a\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006!"}, d2 = {"Ldev/whyoleg/cryptography/BinarySize;", "", "bits", "", "constructor-impl", "(I)I", "inBits", "getInBits-impl", "inBytes", "getInBytes-impl", "plus", "other", "plus-XsDGKLw", "(II)I", "minus", "minus-XsDGKLw", "times", "times-M1VWhS4", TtmlNode.TAG_DIV, "div-M1VWhS4", "rem", "rem-M1VWhS4", "compareTo", "compareTo-6q1zMKY", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "", "hashCode", "Companion", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
public final class BinarySize implements Comparable<BinarySize> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int bits;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ BinarySize m8654boximpl(int i) {
        return new BinarySize(i);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m8658equalsimpl(int i, Object obj) {
        return (obj instanceof BinarySize) && i == ((BinarySize) obj).getBits();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m8659equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: getInBits-impl, reason: not valid java name */
    public static final int m8660getInBitsimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m8662hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object other) {
        return m8658equalsimpl(this.bits, other);
    }

    public int hashCode() {
        return m8662hashCodeimpl(this.bits);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getBits() {
        return this.bits;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(BinarySize binarySize) {
        return m8668compareTo6q1zMKY(binarySize.getBits());
    }

    private /* synthetic */ BinarySize(int i) {
        this.bits = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m8656constructorimpl(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Value must be greater than or equal to 0".toString());
        }
        if (i % 8 == 0) {
            return i;
        }
        throw new IllegalArgumentException("Value must be a multiple of 8".toString());
    }

    /* JADX INFO: renamed from: getInBytes-impl, reason: not valid java name */
    public static final int m8661getInBytesimpl(int i) {
        return i / 8;
    }

    /* JADX INFO: renamed from: plus-XsDGKLw, reason: not valid java name */
    public static final int m8664plusXsDGKLw(int i, int i2) {
        return m8656constructorimpl(i + i2);
    }

    /* JADX INFO: renamed from: minus-XsDGKLw, reason: not valid java name */
    public static final int m8663minusXsDGKLw(int i, int i2) {
        return m8656constructorimpl(i - i2);
    }

    /* JADX INFO: renamed from: times-M1VWhS4, reason: not valid java name */
    public static final int m8666timesM1VWhS4(int i, int i2) {
        return m8656constructorimpl(i * i2);
    }

    /* JADX INFO: renamed from: div-M1VWhS4, reason: not valid java name */
    public static final int m8657divM1VWhS4(int i, int i2) {
        return m8656constructorimpl(i / i2);
    }

    /* JADX INFO: renamed from: rem-M1VWhS4, reason: not valid java name */
    public static final int m8665remM1VWhS4(int i, int i2) {
        return m8656constructorimpl(i % i2);
    }

    /* JADX INFO: renamed from: compareTo-6q1zMKY, reason: not valid java name */
    public static int m8655compareTo6q1zMKY(int i, int i2) {
        return Intrinsics.compare(i, i2);
    }

    /* JADX INFO: renamed from: compareTo-6q1zMKY, reason: not valid java name */
    public int m8668compareTo6q1zMKY(int i) {
        return m8655compareTo6q1zMKY(this.bits, i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m8667toStringimpl(int i) {
        return i + " bits";
    }

    public String toString() {
        return m8667toStringimpl(this.bits);
    }

    /* JADX INFO: compiled from: BinarySize.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0004\u001a\u00020\u0005*\u00020\u00068FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\t\u001a\u00020\u0005*\u00020\u00068FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Ldev/whyoleg/cryptography/BinarySize$Companion;", "", "<init>", "()V", "bits", "Ldev/whyoleg/cryptography/BinarySize;", "", "getBits-M1VWhS4", "(I)I", "bytes", "getBytes-M1VWhS4", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getBits-M1VWhS4, reason: not valid java name */
        public final int m8670getBitsM1VWhS4(int i) {
            return BinarySize.m8656constructorimpl(i);
        }

        /* JADX INFO: renamed from: getBytes-M1VWhS4, reason: not valid java name */
        public final int m8671getBytesM1VWhS4(int i) {
            return BinarySize.m8656constructorimpl(i * 8);
        }
    }
}
