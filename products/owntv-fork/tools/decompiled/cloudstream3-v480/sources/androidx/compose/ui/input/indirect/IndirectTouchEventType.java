package androidx.compose.ui.input.indirect;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: IndirectTouchEvent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/input/indirect/IndirectTouchEventType;", "", ES6Iterator.VALUE_PROPERTY, "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "other", "hashCode", "Companion", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
public final class IndirectTouchEventType {
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int Unknown = m4979constructorimpl(0);
    private static final int Press = m4979constructorimpl(1);
    private static final int Release = m4979constructorimpl(2);
    private static final int Move = m4979constructorimpl(3);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ IndirectTouchEventType m4978boximpl(int i) {
        return new IndirectTouchEventType(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m4979constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4980equalsimpl(int i, Object obj) {
        return (obj instanceof IndirectTouchEventType) && i == ((IndirectTouchEventType) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4981equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4982hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object other) {
        return m4980equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m4982hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    /* JADX INFO: compiled from: IndirectTouchEvent.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/input/indirect/IndirectTouchEventType$Companion;", "", "<init>", "()V", "Unknown", "Landroidx/compose/ui/input/indirect/IndirectTouchEventType;", "getUnknown-LxEHWp8", "()I", "I", "Press", "getPress-LxEHWp8", "Release", "getRelease-LxEHWp8", "Move", "getMove-LxEHWp8", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getUnknown-LxEHWp8, reason: not valid java name */
        public final int m4988getUnknownLxEHWp8() {
            return IndirectTouchEventType.Unknown;
        }

        /* JADX INFO: renamed from: getPress-LxEHWp8, reason: not valid java name */
        public final int m4986getPressLxEHWp8() {
            return IndirectTouchEventType.Press;
        }

        /* JADX INFO: renamed from: getRelease-LxEHWp8, reason: not valid java name */
        public final int m4987getReleaseLxEHWp8() {
            return IndirectTouchEventType.Release;
        }

        /* JADX INFO: renamed from: getMove-LxEHWp8, reason: not valid java name */
        public final int m4985getMoveLxEHWp8() {
            return IndirectTouchEventType.Move;
        }
    }

    private /* synthetic */ IndirectTouchEventType(int i) {
        this.value = i;
    }

    public String toString() {
        return m4983toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4983toStringimpl(int i) {
        if (m4981equalsimpl0(i, Press)) {
            return "Press";
        }
        if (m4981equalsimpl0(i, Release)) {
            return "Release";
        }
        return m4981equalsimpl0(i, Move) ? "Move" : "Unknown";
    }
}
