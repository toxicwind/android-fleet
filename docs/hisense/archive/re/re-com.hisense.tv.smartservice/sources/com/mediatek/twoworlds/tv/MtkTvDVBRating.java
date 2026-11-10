package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvDVBRating extends MtkTvDVBRatingBase {
    public static MtkTvDVBRating mtkTvDVBRating;

    public static MtkTvDVBRating getInstance() {
        MtkTvDVBRating mtkTvDVBRating2 = mtkTvDVBRating;
        if (mtkTvDVBRating2 != null) {
            return mtkTvDVBRating2;
        }
        mtkTvDVBRating = new MtkTvDVBRating();
        return mtkTvDVBRating;
    }
}
