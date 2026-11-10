package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvChannelList extends MtkTvChannelListBase {
    private static MtkTvChannelList mtkTvChannelList;

    private MtkTvChannelList() {
    }

    public static MtkTvChannelList getInstance() {
        MtkTvChannelList mtkTvChannelList2 = mtkTvChannelList;
        if (mtkTvChannelList2 != null) {
            return mtkTvChannelList2;
        }
        MtkTvChannelList mtkTvChannelList3 = new MtkTvChannelList();
        mtkTvChannelList = mtkTvChannelList3;
        return mtkTvChannelList3;
    }
}
