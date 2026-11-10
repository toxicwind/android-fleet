package org.java_websocket.framing;

import com.mediatek.twoworlds.tv.MtkTvScanCeBase;
import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.util.Charsetfunctions;

/* JADX INFO: loaded from: classes.dex */
public class TextFrame extends DataFrame {
    public TextFrame() {
        super(Opcode.TEXT);
    }

    @Override // org.java_websocket.framing.DataFrame, org.java_websocket.framing.FramedataImpl1
    public void isValid() throws InvalidDataException {
        super.isValid();
        if (!Charsetfunctions.isValidUTF8(getPayloadData())) {
            throw new InvalidDataException(MtkTvScanCeBase.OPERATOR_XIAN_GUANGDIAN, "Received text is no valid utf8 string!");
        }
    }
}
