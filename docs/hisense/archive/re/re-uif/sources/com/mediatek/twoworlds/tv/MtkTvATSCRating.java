package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvATSCRating extends MtkTvATSCRatingBase {
    private static MtkTvATSCRating mtkTvATSCRating;

    private MtkTvATSCRating() {
    }

    public static MtkTvATSCRating getInstance() {
        MtkTvATSCRating mtkTvATSCRating2 = mtkTvATSCRating;
        if (mtkTvATSCRating2 != null) {
            return mtkTvATSCRating2;
        }
        MtkTvATSCRating mtkTvATSCRating3 = new MtkTvATSCRating();
        mtkTvATSCRating = mtkTvATSCRating3;
        return mtkTvATSCRating3;
    }
}
