package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvSrcVideoResolution {
    public static final int TIMING_TYPE_GRAPHIC = 2;
    public static final int TIMING_TYPE_NOT_SUPPORT = 3;
    public static final int TIMING_TYPE_UNKNOWN = 0;
    public static final int TIMING_TYPE_VIDEO = 1;
    private int mFrameRate = 0;
    private int mTimingType = 0;
    private int mHeight = 0;
    private int mWidth = 0;
    private boolean mIsProgressive = false;

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public void setWidth(int i) {
        this.mWidth = i;
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }

    public int getTimingType() {
        return this.mTimingType;
    }

    public void setTimingType(int i) {
        this.mTimingType = i;
    }

    public int getFrameRate() {
        return this.mFrameRate;
    }

    public void setFrameRate(int i) {
        this.mFrameRate = i;
    }

    public boolean getIsProgressive() {
        return this.mIsProgressive;
    }

    public void setIsProgressive(boolean z) {
        this.mIsProgressive = z;
    }

    public String toString() {
        return "MtkTvSrcVideoResolution  mWidth=" + this.mWidth + " , mHeight=" + this.mHeight + " , mTimingType=" + this.mTimingType + " , mIsProgressive=" + this.mIsProgressive + "\n";
    }
}
