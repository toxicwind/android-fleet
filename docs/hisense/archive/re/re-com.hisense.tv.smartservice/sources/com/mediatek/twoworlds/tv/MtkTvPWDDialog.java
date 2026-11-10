package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvPWDDialog extends MtkTvPWDDialogBase {
    public static MtkTvPWDDialog mtkTvPWDDialog;

    public static MtkTvPWDDialog getInstance() {
        MtkTvPWDDialog mtkTvPWDDialog2 = mtkTvPWDDialog;
        if (mtkTvPWDDialog2 != null) {
            return mtkTvPWDDialog2;
        }
        mtkTvPWDDialog = new MtkTvPWDDialog();
        return mtkTvPWDDialog;
    }
}
