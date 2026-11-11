package qrcode.internals;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;
import qrcode.raw.ErrorCorrectionLevel;
import qrcode.raw.MaskPattern;

/* JADX INFO: compiled from: QRCodeSetup.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u0006\u001a\u00020\u00072\u0014\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t0\t2\b\b\u0002\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ+\u0010\r\u001a\u00020\u00072\u0014\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t0\t2\b\b\u0002\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ+\u0010\u000e\u001a\u00020\u00072\u0014\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t0\t2\b\b\u0002\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ;\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0014\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t0\t2\b\b\u0002\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\u0012J0\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J \u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J \u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J \u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J \u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J)\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00052\u0014\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t0\t¢\u0006\u0002\u0010\u001fJ)\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00052\u0014\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t0\t¢\u0006\u0002\u0010\u001fJ9\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010!\u001a\u00020\u00052\u0014\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t0\t¢\u0006\u0002\u0010'J1\u0010(\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00052\u0014\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t0\t¢\u0006\u0002\u0010)J9\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020,2\u0006\u0010%\u001a\u00020&2\u0006\u0010!\u001a\u00020\u00052\u0014\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t0\t¢\u0006\u0002\u0010-JG\u0010.\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u00142\u0014\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t0\t2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0002\u00101R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lqrcode/internals/QRCodeSetup;", "", "<init>", "()V", "DEFAULT_PROBE_SIZE", "", "setupTopLeftPositionProbePattern", "", "modules", "", "Lqrcode/internals/QRCodeSquare;", "probeSize", "([[Lqrcode/internals/QRCodeSquare;I)V", "setupTopRightPositionProbePattern", "setupBottomLeftPositionProbePattern", "setupPositionProbePattern", "rowOffset", "colOffset", "(II[[Lqrcode/internals/QRCodeSquare;I)V", "isInsideModules", "", "row", "col", "modulesSize", "isTopBottomRowSquare", "isLeftRightColSquare", "isMidSquare", "findSquareRegion", "Lqrcode/internals/QRCodeRegion;", "setupPositionAdjustPattern", "type", "(I[[Lqrcode/internals/QRCodeSquare;)V", "setupTimingPattern", "moduleCount", "setupTypeInfo", "errorCorrectionLevel", "Lqrcode/raw/ErrorCorrectionLevel;", "maskPattern", "Lqrcode/raw/MaskPattern;", "(Lqrcode/raw/ErrorCorrectionLevel;Lqrcode/raw/MaskPattern;I[[Lqrcode/internals/QRCodeSquare;)V", "setupTypeNumber", "(II[[Lqrcode/internals/QRCodeSquare;)V", "applyMaskPattern", "data", "", "([ILqrcode/raw/MaskPattern;I[[Lqrcode/internals/QRCodeSquare;)V", "set", ES6Iterator.VALUE_PROPERTY, "parent", "(IIZ[[Lqrcode/internals/QRCodeSquare;Lqrcode/internals/QRCodeSquare;)V", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class QRCodeSetup {
    private static final int DEFAULT_PROBE_SIZE = 7;
    public static final QRCodeSetup INSTANCE = new QRCodeSetup();

    private final boolean isInsideModules(int row, int rowOffset, int col, int colOffset, int modulesSize) {
        int i;
        int i2 = row + rowOffset;
        return i2 >= 0 && i2 < modulesSize && (i = col + colOffset) >= 0 && i < modulesSize;
    }

    private final boolean isLeftRightColSquare(int row, int col, int probeSize) {
        if (row < 0 || row >= probeSize) {
            return false;
        }
        return col == 0 || col == probeSize - 1;
    }

    private final boolean isMidSquare(int row, int col, int probeSize) {
        return 2 <= row && row < probeSize + (-2) && 2 <= col && col <= probeSize + (-3);
    }

    private final boolean isTopBottomRowSquare(int row, int col, int probeSize) {
        if (col < 0 || col >= probeSize) {
            return false;
        }
        return row == 0 || row == probeSize - 1;
    }

    private QRCodeSetup() {
    }

    public static /* synthetic */ void setupTopLeftPositionProbePattern$default(QRCodeSetup qRCodeSetup, QRCodeSquare[][] qRCodeSquareArr, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 7;
        }
        qRCodeSetup.setupTopLeftPositionProbePattern(qRCodeSquareArr, i);
    }

    public final void setupTopLeftPositionProbePattern(QRCodeSquare[][] modules, int probeSize) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        setupPositionProbePattern(0, 0, modules, probeSize);
    }

    public static /* synthetic */ void setupTopRightPositionProbePattern$default(QRCodeSetup qRCodeSetup, QRCodeSquare[][] qRCodeSquareArr, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 7;
        }
        qRCodeSetup.setupTopRightPositionProbePattern(qRCodeSquareArr, i);
    }

    public final void setupTopRightPositionProbePattern(QRCodeSquare[][] modules, int probeSize) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        setupPositionProbePattern(modules.length - probeSize, 0, modules, probeSize);
    }

    public static /* synthetic */ void setupBottomLeftPositionProbePattern$default(QRCodeSetup qRCodeSetup, QRCodeSquare[][] qRCodeSquareArr, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 7;
        }
        qRCodeSetup.setupBottomLeftPositionProbePattern(qRCodeSquareArr, i);
    }

    public final void setupBottomLeftPositionProbePattern(QRCodeSquare[][] modules, int probeSize) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        setupPositionProbePattern(0, modules.length - probeSize, modules, probeSize);
    }

    public static /* synthetic */ void setupPositionProbePattern$default(QRCodeSetup qRCodeSetup, int i, int i2, QRCodeSquare[][] qRCodeSquareArr, int i3, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            i3 = 7;
        }
        qRCodeSetup.setupPositionProbePattern(i, i2, qRCodeSquareArr, i3);
    }

    public final void setupPositionProbePattern(int rowOffset, int colOffset, QRCodeSquare[][] modules, int probeSize) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(modules, "modules");
        int length = modules.length;
        QRCodeSquare qRCodeSquare = new QRCodeSquare(false, rowOffset, colOffset, length, new QRCodeSquareInfo(QRCodeSquareType.POSITION_PROBE, QRCodeRegion.UNKNOWN), probeSize, probeSize, null, 128, null);
        int i3 = probeSize;
        int i4 = -1;
        if (-1 > i3) {
            return;
        }
        int i5 = -1;
        while (true) {
            if (i4 <= i3) {
                int i6 = i4;
                int i7 = length;
                while (true) {
                    boolean zIsInsideModules = isInsideModules(i5, rowOffset, i6, colOffset, i7);
                    i = i5;
                    int i8 = i6;
                    length = i7;
                    if (zIsInsideModules) {
                        int i9 = i + rowOffset;
                        int i10 = i8 + colOffset;
                        i2 = probeSize;
                        modules[i9][i10] = new QRCodeSquare(isTopBottomRowSquare(i, i8, i3) || isLeftRightColSquare(i, i8, i3) || isMidSquare(i, i8, i3), i9, i10, length, new QRCodeSquareInfo(QRCodeSquareType.POSITION_PROBE, findSquareRegion(i, i8, i3)), 0, 0, qRCodeSquare, 96, null);
                    } else {
                        i2 = i3;
                    }
                    if (i8 == i2) {
                        break;
                    }
                    int i11 = i8 + 1;
                    i5 = i;
                    i3 = i2;
                    i6 = i11;
                    i4 = i4;
                    i7 = length;
                }
            } else {
                i = i5;
                i2 = i3;
                i4 = i4;
            }
            if (i == i2) {
                return;
            }
            i5 = i + 1;
            i3 = i2;
            i4 = i4;
        }
    }

    private final QRCodeRegion findSquareRegion(int row, int col, int probeSize) {
        if (row == 0) {
            if (col == 0) {
                return QRCodeRegion.TOP_LEFT_CORNER;
            }
            if (col == probeSize - 1) {
                return QRCodeRegion.TOP_RIGHT_CORNER;
            }
            if (col == probeSize) {
                return QRCodeRegion.MARGIN;
            }
            return QRCodeRegion.TOP_MID;
        }
        int i = probeSize - 1;
        if (row == i) {
            if (col == 0) {
                return QRCodeRegion.BOTTOM_LEFT_CORNER;
            }
            if (col == i) {
                return QRCodeRegion.BOTTOM_RIGHT_CORNER;
            }
            if (col == probeSize) {
                return QRCodeRegion.MARGIN;
            }
            return QRCodeRegion.BOTTOM_MID;
        }
        if (row == probeSize) {
            return QRCodeRegion.MARGIN;
        }
        if (col == 0) {
            return QRCodeRegion.LEFT_MID;
        }
        if (col == i) {
            return QRCodeRegion.RIGHT_MID;
        }
        if (col == probeSize) {
            return QRCodeRegion.MARGIN;
        }
        return QRCodeRegion.CENTER;
    }

    public final void setupPositionAdjustPattern(int type, QRCodeSquare[][] modules) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        int[] patternPosition = QRUtil.INSTANCE.getPatternPosition(type);
        for (int i : patternPosition) {
            for (int i2 : patternPosition) {
                if (modules[i][i2] == null) {
                    QRCodeSquare[][] qRCodeSquareArr = modules;
                    QRCodeSquare qRCodeSquare = new QRCodeSquare(false, i - 2, i2 - 2, qRCodeSquareArr.length, new QRCodeSquareInfo(QRCodeSquareType.POSITION_ADJUST, QRCodeRegion.UNKNOWN), 6, 6, null, 128, null);
                    int i3 = -2;
                    int i4 = -2;
                    while (i4 < 3) {
                        int i5 = i3;
                        while (i5 < 3) {
                            int i6 = i + i4;
                            int i7 = i2 + i5;
                            modules[i6][i7] = new QRCodeSquare(i4 == i3 || i4 == 2 || i5 == i3 || i5 == 2 || (i4 == 0 && i5 == 0), i6, i7, qRCodeSquareArr.length, new QRCodeSquareInfo(QRCodeSquareType.POSITION_ADJUST, QRCodeRegion.UNKNOWN), 0, 0, qRCodeSquare, 96, null);
                            i5++;
                            i3 = -2;
                        }
                        i4++;
                        i3 = -2;
                    }
                }
            }
        }
    }

    public final void setupTimingPattern(int moduleCount, QRCodeSquare[][] modules) {
        int i;
        Intrinsics.checkNotNullParameter(modules, "modules");
        int i2 = 8;
        int i3 = moduleCount - 8;
        int i4 = 8;
        while (true) {
            if (i4 >= i3) {
                break;
            }
            QRCodeSquare[] qRCodeSquareArr = modules[i4];
            if (qRCodeSquareArr[6] == null) {
                qRCodeSquareArr[6] = new QRCodeSquare(i4 % 2 == 0, i4, 6, modules.length, new QRCodeSquareInfo(QRCodeSquareType.TIMING_PATTERN, QRCodeRegion.UNKNOWN), 0, 0, null, 96, null);
            }
            i4++;
        }
        while (i2 < i3) {
            QRCodeSquare[] qRCodeSquareArr2 = modules[6];
            if (qRCodeSquareArr2[i2] != null) {
                i = i2;
            } else {
                i = i2;
                qRCodeSquareArr2[i] = new QRCodeSquare(i2 % 2 == 0, 6, i, modules.length, new QRCodeSquareInfo(QRCodeSquareType.TIMING_PATTERN, QRCodeRegion.UNKNOWN), 0, 0, null, 96, null);
            }
            i2 = i + 1;
        }
    }

    public final void setupTypeInfo(ErrorCorrectionLevel errorCorrectionLevel, MaskPattern maskPattern, int moduleCount, QRCodeSquare[][] modules) {
        int i;
        Intrinsics.checkNotNullParameter(errorCorrectionLevel, "errorCorrectionLevel");
        Intrinsics.checkNotNullParameter(maskPattern, "maskPattern");
        QRCodeSquare[][] modules2 = modules;
        Intrinsics.checkNotNullParameter(modules2, "modules");
        int bCHTypeInfo = QRUtil.INSTANCE.getBCHTypeInfo((errorCorrectionLevel.getValue() << 3) | maskPattern.ordinal());
        int i2 = 0;
        while (i2 < 15) {
            boolean z = ((bCHTypeInfo >> i2) & 1) == 1;
            if (i2 < 6) {
                set$default(this, i2, 8, z, modules2, null, 16, null);
                i = i2;
            } else {
                i = i2;
                if (i < 8) {
                    set$default(this, i + 1, 8, z, modules, null, 16, null);
                } else {
                    set$default(this, (moduleCount - 15) + i, 8, z, modules, null, 16, null);
                }
            }
            i2 = i + 1;
            modules2 = modules;
        }
        for (int i3 = 0; i3 < 15; i3++) {
            boolean z2 = ((bCHTypeInfo >> i3) & 1) == 1;
            if (i3 < 8) {
                set$default(this, 8, (moduleCount - i3) - 1, z2, modules, null, 16, null);
            } else if (i3 < 9) {
                set$default(this, 8, 15 - i3, z2, modules, null, 16, null);
            } else {
                set$default(this, 8, 14 - i3, z2, modules, null, 16, null);
            }
        }
        set$default(this, moduleCount - 8, 8, true, modules, null, 16, null);
    }

    public final void setupTypeNumber(int type, int moduleCount, QRCodeSquare[][] modules) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        int bCHTypeNumber = QRUtil.INSTANCE.getBCHTypeNumber(type);
        for (int i = 0; i < 18; i++) {
            set$default(this, i / 3, ((i % 3) + moduleCount) - 11, ((bCHTypeNumber >> i) & 1) == 1, modules, null, 16, null);
        }
        for (int i2 = 0; i2 < 18; i2++) {
            set$default(this, ((i2 % 3) + moduleCount) - 11, i2 / 3, ((bCHTypeNumber >> i2) & 1) == 1, modules, null, 16, null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0044  */
    public final void applyMaskPattern(int[] data, MaskPattern maskPattern, int moduleCount, QRCodeSquare[][] modules) {
        int i;
        int i2;
        int i3;
        boolean z;
        QRCodeSquare[][] modules2 = modules;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(maskPattern, "maskPattern");
        Intrinsics.checkNotNullParameter(modules2, "modules");
        int i4 = moduleCount - 1;
        int i5 = i4;
        int i6 = -1;
        int i7 = 7;
        int i8 = 0;
        while (i4 > 0) {
            if (i4 == 6) {
                i4--;
            }
            int i9 = i4;
            while (true) {
                int i10 = i8;
                i = i7;
                int i11 = 0;
                while (i11 < 2) {
                    int i12 = i11;
                    int i13 = i9 - i12;
                    if (modules2[i5][i13] == null) {
                        if (i10 < data.length) {
                            z = ((data[i10] >>> i) & 1) == 1;
                        }
                        if (QRUtil.INSTANCE.getMask(maskPattern, i5, i13)) {
                            z = !z;
                        }
                        i3 = i12;
                        int i14 = i10;
                        set$default(this, i5, i13, z, modules2, null, 16, null);
                        int i15 = i - 1;
                        if (i15 == -1) {
                            i10 = i14 + 1;
                            i = 7;
                        } else {
                            i = i15;
                            i10 = i14;
                        }
                    } else {
                        i3 = i12;
                    }
                    i11 = i3 + 1;
                    modules2 = modules;
                }
                i2 = i10;
                i5 += i6;
                if (i5 < 0 || moduleCount <= i5) {
                    break;
                }
                modules2 = modules;
                i7 = i;
                i8 = i2;
            }
            i5 -= i6;
            i6 = -i6;
            i4 = i9 - 2;
            modules2 = modules;
            i7 = i;
            i8 = i2;
        }
    }

    static /* synthetic */ void set$default(QRCodeSetup qRCodeSetup, int i, int i2, boolean z, QRCodeSquare[][] qRCodeSquareArr, QRCodeSquare qRCodeSquare, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            qRCodeSquare = null;
        }
        qRCodeSetup.set(i, i2, z, qRCodeSquareArr, qRCodeSquare);
    }

    private final void set(int row, int col, boolean value, QRCodeSquare[][] modules, QRCodeSquare parent) {
        QRCodeSquare[] qRCodeSquareArr = modules[row];
        QRCodeSquare qRCodeSquare = qRCodeSquareArr[col];
        if (qRCodeSquare != null) {
            qRCodeSquare.setDark(value);
        } else {
            qRCodeSquareArr[col] = new QRCodeSquare(value, row, col, modules.length, null, 0, 0, parent, 112, null);
        }
    }
}
