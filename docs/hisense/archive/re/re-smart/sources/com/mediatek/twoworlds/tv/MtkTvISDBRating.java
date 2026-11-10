package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvISDBRating extends MtkTvISDBRatingBase {
    public static MtkTvISDBRating mtkTvISDBRating;

    public static MtkTvISDBRating getInstance() {
        MtkTvISDBRating mtkTvISDBRating2 = mtkTvISDBRating;
        if (mtkTvISDBRating2 != null) {
            return mtkTvISDBRating2;
        }
        mtkTvISDBRating = new MtkTvISDBRating();
        return mtkTvISDBRating;
    }
}
