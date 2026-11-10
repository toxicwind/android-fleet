package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvTeletextTopPageBase {
    private MtkTvTeletextPageBase normalPageAddr = new MtkTvTeletextPageBase();
    private boolean normalPageHasName = false;
    private String normalPageName = "";

    public MtkTvTeletextPageBase getNormalPageAddr() {
        return this.normalPageAddr;
    }

    public void setNormalPageAddr(MtkTvTeletextPageBase mtkTvTeletextPageBase) {
        this.normalPageAddr = mtkTvTeletextPageBase;
    }

    public boolean isNormalPageHasName() {
        return this.normalPageHasName;
    }

    public void setNormalPageHasName(boolean z) {
        this.normalPageHasName = z;
    }

    public String getNormalPageName() {
        return this.normalPageName;
    }

    public void setNormalPageName(String str) {
        this.normalPageName = str;
    }
}
