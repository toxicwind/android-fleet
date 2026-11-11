package qrcode.internals;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: QRCodeSquare.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lqrcode/internals/QRCodeRegion;", "", "<init>", "(Ljava/lang/String;I)V", "TOP_LEFT_CORNER", "TOP_RIGHT_CORNER", "TOP_MID", "LEFT_MID", "RIGHT_MID", "CENTER", "BOTTOM_LEFT_CORNER", "BOTTOM_RIGHT_CORNER", "BOTTOM_MID", "MARGIN", "UNKNOWN", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public enum QRCodeRegion {
    TOP_LEFT_CORNER,
    TOP_RIGHT_CORNER,
    TOP_MID,
    LEFT_MID,
    RIGHT_MID,
    CENTER,
    BOTTOM_LEFT_CORNER,
    BOTTOM_RIGHT_CORNER,
    BOTTOM_MID,
    MARGIN,
    UNKNOWN;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<QRCodeRegion> getEntries() {
        return $ENTRIES;
    }
}
