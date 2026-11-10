package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvBanner extends MtkTvBannerBase {
    private static MtkTvBanner mtkTvBanner;

    private MtkTvBanner() {
    }

    public static MtkTvBanner getInstance() {
        MtkTvBanner mtkTvBanner2 = mtkTvBanner;
        if (mtkTvBanner2 != null) {
            return mtkTvBanner2;
        }
        MtkTvBanner mtkTvBanner3 = new MtkTvBanner();
        mtkTvBanner = mtkTvBanner3;
        return mtkTvBanner3;
    }
}
