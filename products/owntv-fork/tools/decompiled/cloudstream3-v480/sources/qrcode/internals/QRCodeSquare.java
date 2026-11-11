package qrcode.internals;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QRCodeSquare.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010 \u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u0005H\u0007J\u0012\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u0005H\u0007J\u0013\u0010#\u001a\u00020\u00032\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010%\u001a\u00020\u0005H\u0016J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0000HÆ\u0003J[\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0000HÆ\u0001J\t\u0010/\u001a\u000200HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0010\"\u0004\b\u001f\u0010\u0012¨\u00061"}, d2 = {"Lqrcode/internals/QRCodeSquare;", "", "dark", "", "row", "", "col", "moduleSize", "squareInfo", "Lqrcode/internals/QRCodeSquareInfo;", "rowSize", "colSize", "parent", "<init>", "(ZIIILqrcode/internals/QRCodeSquareInfo;IILqrcode/internals/QRCodeSquare;)V", "getDark", "()Z", "setDark", "(Z)V", "getRow", "()I", "getCol", "getModuleSize", "getSquareInfo", "()Lqrcode/internals/QRCodeSquareInfo;", "getRowSize", "getColSize", "getParent", "()Lqrcode/internals/QRCodeSquare;", "rendered", "getRendered", "setRendered", "absoluteX", "cellSize", "absoluteY", "equals", "other", "hashCode", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "toString", "", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class QRCodeSquare {
    private final int col;
    private final int colSize;
    private boolean dark;
    private final int moduleSize;
    private final QRCodeSquare parent;
    private boolean rendered;
    private final int row;
    private final int rowSize;
    private final QRCodeSquareInfo squareInfo;

    public static /* synthetic */ QRCodeSquare copy$default(QRCodeSquare qRCodeSquare, boolean z, int i, int i2, int i3, QRCodeSquareInfo qRCodeSquareInfo, int i4, int i5, QRCodeSquare qRCodeSquare2, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            z = qRCodeSquare.dark;
        }
        if ((i6 & 2) != 0) {
            i = qRCodeSquare.row;
        }
        if ((i6 & 4) != 0) {
            i2 = qRCodeSquare.col;
        }
        if ((i6 & 8) != 0) {
            i3 = qRCodeSquare.moduleSize;
        }
        if ((i6 & 16) != 0) {
            qRCodeSquareInfo = qRCodeSquare.squareInfo;
        }
        if ((i6 & 32) != 0) {
            i4 = qRCodeSquare.rowSize;
        }
        if ((i6 & 64) != 0) {
            i5 = qRCodeSquare.colSize;
        }
        if ((i6 & 128) != 0) {
            qRCodeSquare2 = qRCodeSquare.parent;
        }
        int i7 = i5;
        QRCodeSquare qRCodeSquare3 = qRCodeSquare2;
        QRCodeSquareInfo qRCodeSquareInfo2 = qRCodeSquareInfo;
        int i8 = i4;
        return qRCodeSquare.copy(z, i, i2, i3, qRCodeSquareInfo2, i8, i7, qRCodeSquare3);
    }

    public final int absoluteX() {
        return absoluteX$default(this, 0, 1, null);
    }

    public final int absoluteY() {
        return absoluteY$default(this, 0, 1, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getDark() {
        return this.dark;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getRow() {
        return this.row;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getCol() {
        return this.col;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getModuleSize() {
        return this.moduleSize;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final QRCodeSquareInfo getSquareInfo() {
        return this.squareInfo;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getRowSize() {
        return this.rowSize;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getColSize() {
        return this.colSize;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final QRCodeSquare getParent() {
        return this.parent;
    }

    public final QRCodeSquare copy(boolean dark, int row, int col, int moduleSize, QRCodeSquareInfo squareInfo, int rowSize, int colSize, QRCodeSquare parent) {
        Intrinsics.checkNotNullParameter(squareInfo, "squareInfo");
        return new QRCodeSquare(dark, row, col, moduleSize, squareInfo, rowSize, colSize, parent);
    }

    public String toString() {
        return "QRCodeSquare(dark=" + this.dark + ", row=" + this.row + ", col=" + this.col + ", moduleSize=" + this.moduleSize + ", squareInfo=" + this.squareInfo + ", rowSize=" + this.rowSize + ", colSize=" + this.colSize + ", parent=" + this.parent + ')';
    }

    public QRCodeSquare(boolean z, int i, int i2, int i3, QRCodeSquareInfo squareInfo, int i4, int i5, QRCodeSquare qRCodeSquare) {
        Intrinsics.checkNotNullParameter(squareInfo, "squareInfo");
        this.dark = z;
        this.row = i;
        this.col = i2;
        this.moduleSize = i3;
        this.squareInfo = squareInfo;
        this.rowSize = i4;
        this.colSize = i5;
        this.parent = qRCodeSquare;
    }

    public final boolean getDark() {
        return this.dark;
    }

    public final void setDark(boolean z) {
        this.dark = z;
    }

    public final int getRow() {
        return this.row;
    }

    public final int getCol() {
        return this.col;
    }

    public final int getModuleSize() {
        return this.moduleSize;
    }

    public /* synthetic */ QRCodeSquare(boolean z, int i, int i2, int i3, QRCodeSquareInfo qRCodeSquareInfo, int i4, int i5, QRCodeSquare qRCodeSquare, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, i, i2, i3, (i6 & 16) != 0 ? new QRCodeSquareInfo(QRCodeSquareType.DEFAULT, QRCodeRegion.UNKNOWN) : qRCodeSquareInfo, (i6 & 32) != 0 ? 1 : i4, (i6 & 64) != 0 ? 1 : i5, (i6 & 128) != 0 ? null : qRCodeSquare);
    }

    public final QRCodeSquareInfo getSquareInfo() {
        return this.squareInfo;
    }

    public final int getRowSize() {
        return this.rowSize;
    }

    public final int getColSize() {
        return this.colSize;
    }

    public final QRCodeSquare getParent() {
        return this.parent;
    }

    public final boolean getRendered() {
        return this.rendered;
    }

    public final void setRendered(boolean z) {
        this.rendered = z;
    }

    public static /* synthetic */ int absoluteX$default(QRCodeSquare qRCodeSquare, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 25;
        }
        return qRCodeSquare.absoluteX(i);
    }

    public final int absoluteX(int cellSize) {
        return this.col * cellSize;
    }

    public static /* synthetic */ int absoluteY$default(QRCodeSquare qRCodeSquare, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 25;
        }
        return qRCodeSquare.absoluteY(i);
    }

    public final int absoluteY(int cellSize) {
        return this.row * cellSize;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        QRCodeSquare qRCodeSquare = (QRCodeSquare) other;
        return this.row == qRCodeSquare.row && this.col == qRCodeSquare.col && this.rowSize == qRCodeSquare.rowSize && this.colSize == qRCodeSquare.colSize;
    }

    public int hashCode() {
        return (((((this.row * 31) + this.col) * 31) + this.rowSize) * 31) + this.colSize;
    }
}
