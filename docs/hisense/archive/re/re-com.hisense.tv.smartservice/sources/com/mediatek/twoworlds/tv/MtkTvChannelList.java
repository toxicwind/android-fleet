package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvChannelList extends MtkTvChannelListBase {
    public static MtkTvChannelList mtkTvChannelList;

    public static MtkTvChannelList getInstance() {
        MtkTvChannelList mtkTvChannelList2 = mtkTvChannelList;
        if (mtkTvChannelList2 != null) {
            return mtkTvChannelList2;
        }
        mtkTvChannelList = new MtkTvChannelList();
        return mtkTvChannelList;
    }
}
