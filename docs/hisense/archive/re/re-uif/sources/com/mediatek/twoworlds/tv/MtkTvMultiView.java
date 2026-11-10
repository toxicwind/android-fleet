package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvMultiView extends MtkTvMultiViewBase {
    private static MtkTvMultiView mtkTvMultiView;

    private MtkTvMultiView() {
    }

    public static MtkTvMultiView getInstance() {
        MtkTvMultiView mtkTvMultiView2 = mtkTvMultiView;
        if (mtkTvMultiView2 != null) {
            return mtkTvMultiView2;
        }
        MtkTvMultiView mtkTvMultiView3 = new MtkTvMultiView();
        mtkTvMultiView = mtkTvMultiView3;
        return mtkTvMultiView3;
    }
}
