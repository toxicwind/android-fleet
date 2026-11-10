package org.java_websocket.exceptions;

import com.mediatek.twoworlds.tv.MtkTvScanCeBase;

/* JADX INFO: loaded from: classes.dex */
public class InvalidHandshakeException extends InvalidDataException {
    private static final long serialVersionUID = -1426533877490484964L;

    public InvalidHandshakeException() {
        super(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG);
    }

    public InvalidHandshakeException(String str) {
        super(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG, str);
    }
}
