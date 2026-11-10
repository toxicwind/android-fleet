package com.ju.lib.utils.glide;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseLoadImageStatusListener implements GlideUtils.ILoadImageStatusListener {
    public int mLocalUrlType = -1;

    public boolean canRecycleOriginResource() {
        int i = this.mLocalUrlType;
        return i == 2 || i == 0;
    }

    public void setLoadImageType(int i) {
        this.mLocalUrlType = i;
    }
}
