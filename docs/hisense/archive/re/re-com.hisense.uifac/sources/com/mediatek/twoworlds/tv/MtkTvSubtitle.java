package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvSubtitle extends MtkTvSubtitleBase {
    private static MtkTvSubtitle mtkTvSubtitle;

    private MtkTvSubtitle() {
    }

    public static MtkTvSubtitle getInstance() {
        MtkTvSubtitle mtkTvSubtitle2 = mtkTvSubtitle;
        if (mtkTvSubtitle2 != null) {
            return mtkTvSubtitle2;
        }
        MtkTvSubtitle mtkTvSubtitle3 = new MtkTvSubtitle();
        mtkTvSubtitle = mtkTvSubtitle3;
        return mtkTvSubtitle3;
    }
}
