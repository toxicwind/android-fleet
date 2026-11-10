package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvTeletextTopGroupBase {
    private MtkTvTeletextPageBase groupPageAddr = new MtkTvTeletextPageBase();
    private boolean groupHasName = false;
    private String groupName = "";

    public MtkTvTeletextPageBase getGroupPageAddr() {
        return this.groupPageAddr;
    }

    public void setGroupPageAddr(MtkTvTeletextPageBase mtkTvTeletextPageBase) {
        this.groupPageAddr = mtkTvTeletextPageBase;
    }

    public boolean isGroupHasName() {
        return this.groupHasName;
    }

    public void setGroupHasName(boolean z) {
        this.groupHasName = z;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }
}
