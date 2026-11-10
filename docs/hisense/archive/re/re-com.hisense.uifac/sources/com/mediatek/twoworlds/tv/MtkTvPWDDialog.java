package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvPWDDialog extends MtkTvPWDDialogBase {
    private static MtkTvPWDDialog mtkTvPWDDialog;

    private MtkTvPWDDialog() {
    }

    public static MtkTvPWDDialog getInstance() {
        MtkTvPWDDialog mtkTvPWDDialog2 = mtkTvPWDDialog;
        if (mtkTvPWDDialog2 != null) {
            return mtkTvPWDDialog2;
        }
        MtkTvPWDDialog mtkTvPWDDialog3 = new MtkTvPWDDialog();
        mtkTvPWDDialog = mtkTvPWDDialog3;
        return mtkTvPWDDialog3;
    }
}
