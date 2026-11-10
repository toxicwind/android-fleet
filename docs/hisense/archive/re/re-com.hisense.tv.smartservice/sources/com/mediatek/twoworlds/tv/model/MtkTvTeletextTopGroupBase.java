package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvTeletextTopGroupBase {
    public MtkTvTeletextPageBase groupPageAddr = new MtkTvTeletextPageBase();
    public boolean groupHasName = false;
    public String groupName = "";

    public String getGroupName() {
        return this.groupName;
    }

    public MtkTvTeletextPageBase getGroupPageAddr() {
        return this.groupPageAddr;
    }

    public boolean isGroupHasName() {
        return this.groupHasName;
    }

    public void setGroupHasName(boolean z) {
        this.groupHasName = z;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public void setGroupPageAddr(MtkTvTeletextPageBase mtkTvTeletextPageBase) {
        this.groupPageAddr = mtkTvTeletextPageBase;
    }
}
