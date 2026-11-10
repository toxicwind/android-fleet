package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvSrcVideoResolution {
    public static final int TIMING_TYPE_GRAPHIC = 2;
    public static final int TIMING_TYPE_NOT_SUPPORT = 3;
    public static final int TIMING_TYPE_UNKNOWN = 0;
    public static final int TIMING_TYPE_VIDEO = 1;
    public int mFrameRate = 0;
    public int mTimingType = 0;
    public int mHeight = 0;
    public int mWidth = 0;
    public boolean mIsProgressive = false;

    public int getFrameRate() {
        return this.mFrameRate;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public boolean getIsProgressive() {
        return this.mIsProgressive;
    }

    public int getTimingType() {
        return this.mTimingType;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public void setFrameRate(int i) {
        this.mFrameRate = i;
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }

    public void setIsProgressive(boolean z) {
        this.mIsProgressive = z;
    }

    public void setTimingType(int i) {
        this.mTimingType = i;
    }

    public void setWidth(int i) {
        this.mWidth = i;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvSrcVideoResolution  mWidth=");
        sbA.append(this.mWidth);
        sbA.append(" , mHeight=");
        sbA.append(this.mHeight);
        sbA.append(" , mTimingType=");
        sbA.append(this.mTimingType);
        sbA.append(" , mIsProgressive=");
        sbA.append(this.mIsProgressive);
        sbA.append("\n");
        return sbA.toString();
    }
}
