package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvUpgrade extends MtkTvUpgradeBase {
    public static MtkTvUpgrade mtkTvUpgrade;

    public static MtkTvUpgrade getInstance() {
        MtkTvUpgrade mtkTvUpgrade2 = mtkTvUpgrade;
        if (mtkTvUpgrade2 != null) {
            return mtkTvUpgrade2;
        }
        mtkTvUpgrade = new MtkTvUpgrade();
        return mtkTvUpgrade;
    }
}
