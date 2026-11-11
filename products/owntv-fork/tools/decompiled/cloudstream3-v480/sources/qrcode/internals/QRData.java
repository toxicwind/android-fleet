package qrcode.internals;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import qrcode.raw.QRCodeDataType;

/* JADX INFO: compiled from: QRData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b \u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lqrcode/internals/QRData;", "", "dataType", "Lqrcode/raw/QRCodeDataType;", "data", "", "<init>", "(Lqrcode/raw/QRCodeDataType;Ljava/lang/String;)V", "getDataType", "()Lqrcode/raw/QRCodeDataType;", "getData", "()Ljava/lang/String;", "length", "", "write", "", "buffer", "Lqrcode/internals/BitBuffer;", "getLengthInBits", "type", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class QRData {
    private final String data;
    private final QRCodeDataType dataType;

    /* JADX INFO: compiled from: QRData.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[QRCodeDataType.values().length];
            try {
                iArr[QRCodeDataType.NUMBERS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[QRCodeDataType.UPPER_ALPHA_NUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[QRCodeDataType.DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public abstract int length();

    public abstract void write(BitBuffer buffer);

    public QRData(QRCodeDataType dataType, String data) {
        Intrinsics.checkNotNullParameter(dataType, "dataType");
        Intrinsics.checkNotNullParameter(data, "data");
        this.dataType = dataType;
        this.data = data;
    }

    public final String getData() {
        return this.data;
    }

    public final QRCodeDataType getDataType() {
        return this.dataType;
    }

    public final int getLengthInBits(int type) {
        if (1 <= type && type < 10) {
            int i = WhenMappings.$EnumSwitchMapping$0[this.dataType.ordinal()];
            if (i == 1) {
                return 10;
            }
            if (i == 2) {
                return 9;
            }
            if (i == 3) {
                return 8;
            }
            throw new NoWhenBranchMatchedException();
        }
        if (1 <= type && type < 27) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[this.dataType.ordinal()];
            if (i2 == 1) {
                return 12;
            }
            if (i2 == 2) {
                return 11;
            }
            if (i2 == 3) {
                return 16;
            }
            throw new NoWhenBranchMatchedException();
        }
        if (1 <= type && type < 41) {
            int i3 = WhenMappings.$EnumSwitchMapping$0[this.dataType.ordinal()];
            if (i3 == 1) {
                return 14;
            }
            if (i3 == 2) {
                return 13;
            }
            if (i3 == 3) {
                return 16;
            }
            throw new NoWhenBranchMatchedException();
        }
        throw new IllegalArgumentException("'type' must be greater than 0 and cannot be greater than 40: " + type);
    }
}
