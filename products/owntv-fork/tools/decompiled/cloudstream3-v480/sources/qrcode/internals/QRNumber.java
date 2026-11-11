package qrcode.internals;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import qrcode.raw.QRCodeDataType;

/* JADX INFO: compiled from: QRData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lqrcode/internals/QRNumber;", "Lqrcode/internals/QRData;", "data", "", "<init>", "(Ljava/lang/String;)V", "write", "", "buffer", "Lqrcode/internals/BitBuffer;", "length", "", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class QRNumber extends QRData {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QRNumber(String data) {
        super(QRCodeDataType.NUMBERS, data);
        Intrinsics.checkNotNullParameter(data, "data");
    }

    @Override // qrcode.internals.QRData
    public void write(BitBuffer buffer) {
        int i;
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int length = length();
        int i2 = 0;
        while (true) {
            i = i2 + 2;
            if (i >= length) {
                break;
            }
            int i3 = i2 + 3;
            String strSubstring = getData().substring(i2, i3);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            buffer.put(Integer.parseInt(strSubstring), 10);
            i2 = i3;
        }
        if (i2 < length) {
            int i4 = length - i2;
            if (i4 == 1) {
                String strSubstring2 = getData().substring(i2, i2 + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                buffer.put(Integer.parseInt(strSubstring2), 4);
            } else if (i4 == 2) {
                String strSubstring3 = getData().substring(i2, i);
                Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                buffer.put(Integer.parseInt(strSubstring3), 7);
            }
        }
    }

    @Override // qrcode.internals.QRData
    public int length() {
        return getData().length();
    }
}
