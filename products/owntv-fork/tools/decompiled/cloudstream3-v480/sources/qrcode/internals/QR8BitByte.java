package qrcode.internals;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import qrcode.raw.QRCodeDataType;

/* JADX INFO: compiled from: QRData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lqrcode/internals/QR8BitByte;", "Lqrcode/internals/QRData;", "data", "", "<init>", "(Ljava/lang/String;)V", "dataBytes", "", "write", "", "buffer", "Lqrcode/internals/BitBuffer;", "length", "", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class QR8BitByte extends QRData {
    private final byte[] dataBytes;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QR8BitByte(String data) {
        super(QRCodeDataType.DEFAULT, data);
        Intrinsics.checkNotNullParameter(data, "data");
        this.dataBytes = StringsKt.encodeToByteArray(data);
    }

    @Override // qrcode.internals.QRData
    public void write(BitBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int length = this.dataBytes.length;
        for (int i = 0; i < length; i++) {
            buffer.put(this.dataBytes[i], 8);
        }
    }

    @Override // qrcode.internals.QRData
    public int length() {
        return this.dataBytes.length;
    }
}
