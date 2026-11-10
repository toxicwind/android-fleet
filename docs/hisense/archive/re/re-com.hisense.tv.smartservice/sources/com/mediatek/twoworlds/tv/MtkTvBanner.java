package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvBanner extends MtkTvBannerBase {
    public static MtkTvBanner mtkTvBanner;

    public static MtkTvBanner getInstance() {
        MtkTvBanner mtkTvBanner2 = mtkTvBanner;
        if (mtkTvBanner2 != null) {
            return mtkTvBanner2;
        }
        mtkTvBanner = new MtkTvBanner();
        return mtkTvBanner;
    }
}
