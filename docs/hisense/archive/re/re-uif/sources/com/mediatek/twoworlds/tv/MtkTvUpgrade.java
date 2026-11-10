package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvUpgrade extends MtkTvUpgradeBase {
    private static MtkTvUpgrade mtkTvUpgrade;

    private MtkTvUpgrade() {
    }

    public static MtkTvUpgrade getInstance() {
        MtkTvUpgrade mtkTvUpgrade2 = mtkTvUpgrade;
        if (mtkTvUpgrade2 != null) {
            return mtkTvUpgrade2;
        }
        MtkTvUpgrade mtkTvUpgrade3 = new MtkTvUpgrade();
        mtkTvUpgrade = mtkTvUpgrade3;
        return mtkTvUpgrade3;
    }
}
