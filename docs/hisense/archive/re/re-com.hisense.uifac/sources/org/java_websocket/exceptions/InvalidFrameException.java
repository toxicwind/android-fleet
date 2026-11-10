package org.java_websocket.exceptions;

import com.mediatek.twoworlds.tv.MtkTvScanCeBase;

/* JADX INFO: loaded from: classes.dex */
public class InvalidFrameException extends InvalidDataException {
    private static final long serialVersionUID = -9016496369828887591L;

    public InvalidFrameException() {
        super(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG);
    }

    public InvalidFrameException(String str) {
        super(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG, str);
    }
}
