package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvSubtitle extends MtkTvSubtitleBase {
    public static MtkTvSubtitle mtkTvSubtitle;

    public static MtkTvSubtitle getInstance() {
        MtkTvSubtitle mtkTvSubtitle2 = mtkTvSubtitle;
        if (mtkTvSubtitle2 != null) {
            return mtkTvSubtitle2;
        }
        mtkTvSubtitle = new MtkTvSubtitle();
        return mtkTvSubtitle;
    }
}
