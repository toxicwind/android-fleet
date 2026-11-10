package com.google.zxing.datamatrix.encoder;

import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;

/* JADX INFO: loaded from: classes.dex */
final class DataMatrixSymbolInfo144 extends SymbolInfo {
    @Override // com.google.zxing.datamatrix.encoder.SymbolInfo
    public int getDataLengthForInterleavedBlock(int i) {
        return i <= 8 ? MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_UNAUTHORIZED : MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BUF_FULL;
    }

    @Override // com.google.zxing.datamatrix.encoder.SymbolInfo
    public int getInterleavedBlockCount() {
        return 10;
    }

    DataMatrixSymbolInfo144() {
        super(false, 1558, 620, 22, 22, 36, -1, 62);
    }
}
