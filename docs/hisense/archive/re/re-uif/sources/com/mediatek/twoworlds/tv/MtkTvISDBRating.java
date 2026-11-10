package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvISDBRating extends MtkTvISDBRatingBase {
    private static MtkTvISDBRating mtkTvISDBRating;

    private MtkTvISDBRating() {
    }

    public static MtkTvISDBRating getInstance() {
        MtkTvISDBRating mtkTvISDBRating2 = mtkTvISDBRating;
        if (mtkTvISDBRating2 != null) {
            return mtkTvISDBRating2;
        }
        MtkTvISDBRating mtkTvISDBRating3 = new MtkTvISDBRating();
        mtkTvISDBRating = mtkTvISDBRating3;
        return mtkTvISDBRating3;
    }
}
