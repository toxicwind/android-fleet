package qrcode.internals;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import qrcode.raw.QRCodeDataType;

/* JADX INFO: compiled from: QRData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002¨\u0006\u000f"}, d2 = {"Lqrcode/internals/QRAlphaNum;", "Lqrcode/internals/QRData;", "data", "", "<init>", "(Ljava/lang/String;)V", "write", "", "buffer", "Lqrcode/internals/BitBuffer;", "length", "", "charCode", "c", "", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class QRAlphaNum extends QRData {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QRAlphaNum(String data) {
        super(QRCodeDataType.UPPER_ALPHA_NUM, data);
        Intrinsics.checkNotNullParameter(data, "data");
    }

    @Override // qrcode.internals.QRData
    public void write(BitBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int length = getData().length();
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i2 >= length) {
                break;
            }
            buffer.put((charCode(getData().charAt(i)) * 45) + charCode(getData().charAt(i2)), 11);
            i += 2;
        }
        if (i < length) {
            buffer.put(charCode(getData().charAt(i)), 6);
        }
    }

    @Override // qrcode.internals.QRData
    public int length() {
        return getData().length();
    }

    private final int charCode(char c) {
        if ('0' <= c && c < ':') {
            return c - '0';
        }
        if ('A' <= c && c < '[') {
            return c - '7';
        }
        if (c == ' ') {
            return 36;
        }
        if (c == ':') {
            return 44;
        }
        if (c == '$') {
            return 37;
        }
        if (c == '%') {
            return 38;
        }
        if (c == '*') {
            return 39;
        }
        if (c == '+') {
            return 40;
        }
        switch (c) {
            case '-':
                return 41;
            case '.':
                return 42;
            case '/':
                return 43;
            default:
                throw new IllegalArgumentException("Illegal character: " + c);
        }
    }
}
