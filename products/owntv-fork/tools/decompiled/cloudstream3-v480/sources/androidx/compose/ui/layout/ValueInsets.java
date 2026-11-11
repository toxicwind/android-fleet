package androidx.compose.ui.layout;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.JvmInline;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: ValueInsets.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0081@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0012\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0012\u0010\u000e\u001a\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0012\u0010\u0010\u001a\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000b\u0088\u0001\u0002¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/layout/ValueInsets;", "", "packedValue", "", "constructor-impl", "(J)J", "getPackedValue", "()J", TtmlNode.LEFT, "", "getLeft-impl", "(J)I", "top", "getTop-impl", TtmlNode.RIGHT, "getRight-impl", "bottom", "getBottom-impl", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
public final class ValueInsets {
    private final long packedValue;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ValueInsets m5696boximpl(long j) {
        return new ValueInsets(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m5697constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m5698equalsimpl(long j, Object obj) {
        return (obj instanceof ValueInsets) && j == ((ValueInsets) obj).m5706unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5699equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getBottom-impl, reason: not valid java name */
    public static final int m5700getBottomimpl(long j) {
        return (int) (j & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    /* JADX INFO: renamed from: getLeft-impl, reason: not valid java name */
    public static final int m5701getLeftimpl(long j) {
        return (int) ((j >>> 48) & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    /* JADX INFO: renamed from: getRight-impl, reason: not valid java name */
    public static final int m5702getRightimpl(long j) {
        return (int) ((j >>> 16) & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    /* JADX INFO: renamed from: getTop-impl, reason: not valid java name */
    public static final int m5703getTopimpl(long j) {
        return (int) ((j >>> 32) & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m5704hashCodeimpl(long j) {
        return UByte$$ExternalSyntheticBackport0.m(j);
    }

    public boolean equals(Object obj) {
        return m5698equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m5704hashCodeimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m5706unboximpl() {
        return this.packedValue;
    }

    private /* synthetic */ ValueInsets(long j) {
        this.packedValue = j;
    }

    public final long getPackedValue() {
        return this.packedValue;
    }

    public String toString() {
        return m5705toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m5705toStringimpl(long j) {
        return "ValueInsets(" + ((int) ((j >>> 48) & WebSocketProtocol.PAYLOAD_SHORT_MAX)) + ", " + ((int) ((j >>> 32) & WebSocketProtocol.PAYLOAD_SHORT_MAX)) + ", " + ((int) ((j >>> 16) & WebSocketProtocol.PAYLOAD_SHORT_MAX)) + ", " + ((int) (j & WebSocketProtocol.PAYLOAD_SHORT_MAX)) + ')';
    }
}
