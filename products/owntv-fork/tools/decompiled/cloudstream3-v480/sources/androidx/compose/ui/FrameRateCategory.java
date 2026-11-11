package androidx.compose.ui;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: FrameRateCategory.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087@\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/FrameRateCategory;", "", ES6Iterator.VALUE_PROPERTY, "", "constructor-impl", "(F)F", "toString", "", "toString-impl", "(F)Ljava/lang/String;", "equals", "", "other", "hashCode", "", "Companion", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
public final class FrameRateCategory {
    private final float value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final float Default = m3595constructorimpl(Float.NaN);
    private static final float Normal = m3595constructorimpl(-3.0f);
    private static final float High = m3595constructorimpl(-4.0f);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FrameRateCategory m3594boximpl(float f) {
        return new FrameRateCategory(f);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static float m3595constructorimpl(float f) {
        return f;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m3596equalsimpl(float f, Object obj) {
        return (obj instanceof FrameRateCategory) && Float.compare(f, ((FrameRateCategory) obj).getValue()) == 0;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m3597equalsimpl0(float f, float f2) {
        return Float.compare(f, f2) == 0;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m3598hashCodeimpl(float f) {
        return Float.floatToIntBits(f);
    }

    public boolean equals(Object other) {
        return m3596equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m3598hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ float getValue() {
        return this.value;
    }

    /* JADX INFO: compiled from: FrameRateCategory.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/compose/ui/FrameRateCategory$Companion;", "", "<init>", "()V", "Default", "Landroidx/compose/ui/FrameRateCategory;", "getDefault-NSsRyOo", "()F", "F", "Normal", "getNormal-NSsRyOo", "High", "getHigh-NSsRyOo", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getDefault-NSsRyOo, reason: not valid java name */
        public final float m3601getDefaultNSsRyOo() {
            return FrameRateCategory.Default;
        }

        /* JADX INFO: renamed from: getNormal-NSsRyOo, reason: not valid java name */
        public final float m3603getNormalNSsRyOo() {
            return FrameRateCategory.Normal;
        }

        /* JADX INFO: renamed from: getHigh-NSsRyOo, reason: not valid java name */
        public final float m3602getHighNSsRyOo() {
            return FrameRateCategory.High;
        }
    }

    private /* synthetic */ FrameRateCategory(float f) {
        this.value = f;
    }

    public String toString() {
        return m3599toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m3599toStringimpl(float f) {
        if (f == -3.0f) {
            return "Normal";
        }
        if (f == -4.0f) {
            return "High";
        }
        return "Default";
    }
}
