package coil3.size;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;

/* JADX INFO: compiled from: Dimension.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcoil3/size/Dimension;", "", "Pixels", "Undefined", "Lcoil3/size/Dimension$Pixels;", "Lcoil3/size/Dimension$Undefined;", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface Dimension {

    /* JADX INFO: compiled from: Dimension.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\u0003HÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u000f"}, d2 = {"Lcoil3/size/Dimension$Pixels;", "Lcoil3/size/Dimension;", "px", "", "constructor-impl", "(I)I", "getPx", "()I", "equals", "", "other", "", "hashCode", "toString", "", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @JvmInline
    public static final class Pixels implements Dimension {
        private final int px;

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ Pixels m8123boximpl(int i) {
            return new Pixels(i);
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m8125equalsimpl(int i, Object obj) {
            return (obj instanceof Pixels) && i == ((Pixels) obj).m8129unboximpl();
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m8126equalsimpl0(int i, int i2) {
            return i == i2;
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m8127hashCodeimpl(int i) {
            return i;
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m8128toStringimpl(int i) {
            return "Pixels(px=" + i + ')';
        }

        public boolean equals(Object other) {
            return m8125equalsimpl(this.px, other);
        }

        public int hashCode() {
            return m8127hashCodeimpl(this.px);
        }

        public String toString() {
            return m8128toStringimpl(this.px);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ int m8129unboximpl() {
            return this.px;
        }

        private /* synthetic */ Pixels(int i) {
            this.px = i;
        }

        public final int getPx() {
            return this.px;
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static int m8124constructorimpl(int i) {
            if (i > 0) {
                return i;
            }
            throw new IllegalArgumentException("px must be > 0.".toString());
        }
    }

    /* JADX INFO: compiled from: Dimension.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcoil3/size/Dimension$Undefined;", "Lcoil3/size/Dimension;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Undefined implements Dimension {
        public static final Undefined INSTANCE = new Undefined();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Undefined)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -2093724603;
        }

        public String toString() {
            return "Undefined";
        }

        private Undefined() {
        }
    }
}
