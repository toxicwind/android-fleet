package qrcode.internals;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QRCodeSquare.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lqrcode/internals/QRCodeSquareInfo;", "", "type", "Lqrcode/internals/QRCodeSquareType;", TtmlNode.TAG_REGION, "Lqrcode/internals/QRCodeRegion;", "<init>", "(Lqrcode/internals/QRCodeSquareType;Lqrcode/internals/QRCodeRegion;)V", "getType", "()Lqrcode/internals/QRCodeSquareType;", "getRegion", "()Lqrcode/internals/QRCodeRegion;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class QRCodeSquareInfo {
    private final QRCodeRegion region;
    private final QRCodeSquareType type;

    public static /* synthetic */ QRCodeSquareInfo copy$default(QRCodeSquareInfo qRCodeSquareInfo, QRCodeSquareType qRCodeSquareType, QRCodeRegion qRCodeRegion, int i, Object obj) {
        if ((i & 1) != 0) {
            qRCodeSquareType = qRCodeSquareInfo.type;
        }
        if ((i & 2) != 0) {
            qRCodeRegion = qRCodeSquareInfo.region;
        }
        return qRCodeSquareInfo.copy(qRCodeSquareType, qRCodeRegion);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final QRCodeSquareType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final QRCodeRegion getRegion() {
        return this.region;
    }

    public final QRCodeSquareInfo copy(QRCodeSquareType type, QRCodeRegion region) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(region, "region");
        return new QRCodeSquareInfo(type, region);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QRCodeSquareInfo)) {
            return false;
        }
        QRCodeSquareInfo qRCodeSquareInfo = (QRCodeSquareInfo) other;
        return this.type == qRCodeSquareInfo.type && this.region == qRCodeSquareInfo.region;
    }

    public int hashCode() {
        return (this.type.hashCode() * 31) + this.region.hashCode();
    }

    public String toString() {
        return "QRCodeSquareInfo(type=" + this.type + ", region=" + this.region + ')';
    }

    public QRCodeSquareInfo(QRCodeSquareType type, QRCodeRegion region) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(region, "region");
        this.type = type;
        this.region = region;
    }

    public final QRCodeSquareType getType() {
        return this.type;
    }

    public final QRCodeRegion getRegion() {
        return this.region;
    }
}
