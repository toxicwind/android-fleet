package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvDVBRating extends MtkTvDVBRatingBase {
    private static MtkTvDVBRating mtkTvDVBRating;

    private MtkTvDVBRating() {
    }

    public static MtkTvDVBRating getInstance() {
        MtkTvDVBRating mtkTvDVBRating2 = mtkTvDVBRating;
        if (mtkTvDVBRating2 != null) {
            return mtkTvDVBRating2;
        }
        MtkTvDVBRating mtkTvDVBRating3 = new MtkTvDVBRating();
        mtkTvDVBRating = mtkTvDVBRating3;
        return mtkTvDVBRating3;
    }
}
