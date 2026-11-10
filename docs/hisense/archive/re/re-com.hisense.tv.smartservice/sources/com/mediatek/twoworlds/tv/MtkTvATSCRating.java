package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvATSCRating extends MtkTvATSCRatingBase {
    public static MtkTvATSCRating mtkTvATSCRating;

    public static MtkTvATSCRating getInstance() {
        MtkTvATSCRating mtkTvATSCRating2 = mtkTvATSCRating;
        if (mtkTvATSCRating2 != null) {
            return mtkTvATSCRating2;
        }
        mtkTvATSCRating = new MtkTvATSCRating();
        return mtkTvATSCRating;
    }
}
