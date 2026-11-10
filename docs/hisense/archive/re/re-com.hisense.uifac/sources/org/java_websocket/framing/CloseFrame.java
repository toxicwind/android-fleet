package org.java_websocket.framing;

import com.mediatek.twoworlds.tv.MtkTvScanCeBase;
import java.nio.ByteBuffer;
import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.util.ByteBufferUtils;
import org.java_websocket.util.Charsetfunctions;

/* JADX INFO: loaded from: classes.dex */
public class CloseFrame extends ControlFrame {
    private int code;
    private String reason;

    public CloseFrame() {
        super(Opcode.CLOSING);
        setReason("");
        setCode(1000);
    }

    public void setCode(int i) {
        this.code = i;
        if (i == 1015) {
            this.code = MtkTvScanCeBase.OPERATOR_WUHAN_SHENGWANG;
            this.reason = "";
        }
        updatePayload();
    }

    public void setReason(String str) {
        if (str == null) {
            str = "";
        }
        this.reason = str;
        updatePayload();
    }

    public int getCloseCode() {
        return this.code;
    }

    public String getMessage() {
        return this.reason;
    }

    @Override // org.java_websocket.framing.FramedataImpl1
    public String toString() {
        return super.toString() + "code: " + this.code;
    }

    @Override // org.java_websocket.framing.ControlFrame, org.java_websocket.framing.FramedataImpl1
    public void isValid() throws InvalidDataException {
        super.isValid();
        if (this.code == 1007 && this.reason.isEmpty()) {
            throw new InvalidDataException(MtkTvScanCeBase.OPERATOR_XIAN_GUANGDIAN, "Received text is no valid utf8 string!");
        }
        if (this.code == 1005 && this.reason.length() > 0) {
            throw new InvalidDataException(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG, "A close frame must have a closecode if it has a reason");
        }
        int i = this.code;
        if (i > 1015 && i < 3000) {
            throw new InvalidDataException(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG, "Trying to send an illegal close code!");
        }
        int i2 = this.code;
        if (i2 == 1006 || i2 == 1015 || i2 == 1005 || i2 > 4999 || i2 < 1000 || i2 == 1004) {
            throw new InvalidFrameException("closecode must not be sent over the wire: " + this.code);
        }
    }

    @Override // org.java_websocket.framing.FramedataImpl1
    public void setPayload(ByteBuffer byteBuffer) {
        this.code = MtkTvScanCeBase.OPERATOR_WUHAN_SHENGWANG;
        this.reason = "";
        byteBuffer.mark();
        if (byteBuffer.remaining() == 0) {
            this.code = 1000;
            return;
        }
        if (byteBuffer.remaining() == 1) {
            this.code = MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG;
            return;
        }
        if (byteBuffer.remaining() >= 2) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            byteBufferAllocate.position(2);
            byteBufferAllocate.putShort(byteBuffer.getShort());
            byteBufferAllocate.position(0);
            this.code = byteBufferAllocate.getInt();
        }
        byteBuffer.reset();
        try {
            validateUtf8(byteBuffer, byteBuffer.position());
        } catch (InvalidDataException unused) {
            this.code = MtkTvScanCeBase.OPERATOR_XIAN_GUANGDIAN;
            this.reason = null;
        }
    }

    private void validateUtf8(ByteBuffer byteBuffer, int i) throws InvalidDataException {
        try {
            try {
                byteBuffer.position(byteBuffer.position() + 2);
                this.reason = Charsetfunctions.stringUtf8(byteBuffer);
                byteBuffer.position(i);
            } catch (IllegalArgumentException unused) {
                throw new InvalidDataException(MtkTvScanCeBase.OPERATOR_XIAN_GUANGDIAN);
            }
        } catch (Throwable th) {
            byteBuffer.position(i);
            throw th;
        }
    }

    private void updatePayload() {
        byte[] bArrUtf8Bytes = Charsetfunctions.utf8Bytes(this.reason);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.putInt(this.code);
        byteBufferAllocate.position(2);
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(bArrUtf8Bytes.length + 2);
        byteBufferAllocate2.put(byteBufferAllocate);
        byteBufferAllocate2.put(bArrUtf8Bytes);
        byteBufferAllocate2.rewind();
        super.setPayload(byteBufferAllocate2);
    }

    @Override // org.java_websocket.framing.FramedataImpl1, org.java_websocket.framing.Framedata
    public ByteBuffer getPayloadData() {
        if (this.code == 1005) {
            return ByteBufferUtils.getEmptyByteBuffer();
        }
        return super.getPayloadData();
    }

    @Override // org.java_websocket.framing.FramedataImpl1
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CloseFrame.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        CloseFrame closeFrame = (CloseFrame) obj;
        if (this.code != closeFrame.code) {
            return false;
        }
        String str = this.reason;
        if (str != null) {
            return str.equals(closeFrame.reason);
        }
        return closeFrame.reason == null;
    }

    @Override // org.java_websocket.framing.FramedataImpl1
    public int hashCode() {
        int iHashCode = ((super.hashCode() * 31) + this.code) * 31;
        String str = this.reason;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }
}
