package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MtkTvCIMMIBase {
    public static final String TAG = "MtkTvCIMMI";
    public int mmi_id;

    public MtkTvCIMMIBase(int i) {
        this.mmi_id = i;
    }

    public int getMMIId() {
        return this.mmi_id;
    }

    public void setMMIId(int i) {
        this.mmi_id = i;
    }

    public MtkTvCIMMIBase() {
    }
}
