package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvAnalogCloseCaption extends MtkTvAnalogCloseCaptionBase {
    private static MtkTvAnalogCloseCaption mtkTvAnalogCloseCaption;

    private MtkTvAnalogCloseCaption() {
    }

    public static MtkTvAnalogCloseCaption getInstance() {
        MtkTvAnalogCloseCaption mtkTvAnalogCloseCaption2 = mtkTvAnalogCloseCaption;
        if (mtkTvAnalogCloseCaption2 != null) {
            return mtkTvAnalogCloseCaption2;
        }
        MtkTvAnalogCloseCaption mtkTvAnalogCloseCaption3 = new MtkTvAnalogCloseCaption();
        mtkTvAnalogCloseCaption = mtkTvAnalogCloseCaption3;
        return mtkTvAnalogCloseCaption3;
    }
}
