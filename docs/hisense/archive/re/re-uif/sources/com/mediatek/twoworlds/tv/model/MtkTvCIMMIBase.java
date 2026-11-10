package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public abstract class MtkTvCIMMIBase {
    private static final String TAG = "MtkTvCIMMI";
    protected int mmi_id;

    public MtkTvCIMMIBase(int i) {
        this.mmi_id = i;
    }

    public MtkTvCIMMIBase() {
    }

    public int getMMIId() {
        return this.mmi_id;
    }

    public void setMMIId(int i) {
        this.mmi_id = i;
    }
}
