package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvAnalogCloseCaption extends MtkTvAnalogCloseCaptionBase {
    public static MtkTvAnalogCloseCaption mtkTvAnalogCloseCaption;

    public static MtkTvAnalogCloseCaption getInstance() {
        MtkTvAnalogCloseCaption mtkTvAnalogCloseCaption2 = mtkTvAnalogCloseCaption;
        if (mtkTvAnalogCloseCaption2 != null) {
            return mtkTvAnalogCloseCaption2;
        }
        mtkTvAnalogCloseCaption = new MtkTvAnalogCloseCaption();
        return mtkTvAnalogCloseCaption;
    }
}
