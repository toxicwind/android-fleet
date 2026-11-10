package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvTeletextTopBlockBase {
    private MtkTvTeletextPageBase blockPageAddr = new MtkTvTeletextPageBase();
    private boolean blockHasName = false;
    private String blockName = "";

    public MtkTvTeletextPageBase getBlockPageAddr() {
        return this.blockPageAddr;
    }

    public void setBlockPageAddr(MtkTvTeletextPageBase mtkTvTeletextPageBase) {
        this.blockPageAddr = mtkTvTeletextPageBase;
    }

    public boolean isBlockHasName() {
        return this.blockHasName;
    }

    public void setBlockHasName(boolean z) {
        this.blockHasName = z;
    }

    public String getBlockName() {
        return this.blockName;
    }

    public void setBlockName(String str) {
        this.blockName = str;
    }
}
