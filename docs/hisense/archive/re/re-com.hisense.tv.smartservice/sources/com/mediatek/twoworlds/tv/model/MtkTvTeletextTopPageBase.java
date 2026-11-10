package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvTeletextTopPageBase {
    public MtkTvTeletextPageBase normalPageAddr = new MtkTvTeletextPageBase();
    public boolean normalPageHasName = false;
    public String normalPageName = "";

    public MtkTvTeletextPageBase getNormalPageAddr() {
        return this.normalPageAddr;
    }

    public String getNormalPageName() {
        return this.normalPageName;
    }

    public boolean isNormalPageHasName() {
        return this.normalPageHasName;
    }

    public void setNormalPageAddr(MtkTvTeletextPageBase mtkTvTeletextPageBase) {
        this.normalPageAddr = mtkTvTeletextPageBase;
    }

    public void setNormalPageHasName(boolean z) {
        this.normalPageHasName = z;
    }

    public void setNormalPageName(String str) {
        this.normalPageName = str;
    }
}
