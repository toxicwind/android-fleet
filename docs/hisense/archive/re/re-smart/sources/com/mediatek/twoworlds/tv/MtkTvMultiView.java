package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvMultiView extends MtkTvMultiViewBase {
    public static MtkTvMultiView mtkTvMultiView;

    public static MtkTvMultiView getInstance() {
        MtkTvMultiView mtkTvMultiView2 = mtkTvMultiView;
        if (mtkTvMultiView2 != null) {
            return mtkTvMultiView2;
        }
        mtkTvMultiView = new MtkTvMultiView();
        return mtkTvMultiView;
    }
}
