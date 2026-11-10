package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvTeletextTopBlockBase {
    public MtkTvTeletextPageBase blockPageAddr = new MtkTvTeletextPageBase();
    public boolean blockHasName = false;
    public String blockName = "";

    public String getBlockName() {
        return this.blockName;
    }

    public MtkTvTeletextPageBase getBlockPageAddr() {
        return this.blockPageAddr;
    }

    public boolean isBlockHasName() {
        return this.blockHasName;
    }

    public void setBlockHasName(boolean z) {
        this.blockHasName = z;
    }

    public void setBlockName(String str) {
        this.blockName = str;
    }

    public void setBlockPageAddr(MtkTvTeletextPageBase mtkTvTeletextPageBase) {
        this.blockPageAddr = mtkTvTeletextPageBase;
    }
}
