package com.google.android.exoplayer.text.pgs;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SubPicture {
    private int compositionNumber;
    private long endTime;
    private List<Object> erasePatch;
    private boolean excluded;
    private boolean forced;
    private int height;
    private int imageHeight;
    private int imageWidth;
    private long startTime;
    private boolean wasDecoded;
    private int width;
    private int xOffset;
    private int yOffset;

    public abstract int getImageHeight();

    public abstract int getImageWidth();

    public abstract int getXOffset();

    public abstract int getYOffset();

    public SubPicture() {
        this.erasePatch = new ArrayList();
    }

    public SubPicture(SubPicture subPicture) {
        this.erasePatch = new ArrayList();
        this.imageWidth = subPicture.getImageWidth();
        this.imageHeight = subPicture.getImageHeight();
        this.xOffset = subPicture.getXOffset();
        this.yOffset = subPicture.getYOffset();
        this.imageWidth = subPicture.imageWidth;
        this.imageHeight = subPicture.imageHeight;
        this.xOffset = subPicture.xOffset;
        this.yOffset = subPicture.yOffset;
        this.width = subPicture.width;
        this.height = subPicture.height;
        this.startTime = subPicture.startTime;
        this.endTime = subPicture.endTime;
        this.forced = subPicture.forced;
        this.compositionNumber = subPicture.compositionNumber;
        this.wasDecoded = subPicture.wasDecoded;
        this.excluded = subPicture.excluded;
        if (subPicture.erasePatch != null) {
            this.erasePatch = new ArrayList(subPicture.erasePatch);
        }
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public boolean isForced() {
        return this.forced;
    }

    public void setForced(boolean z) {
        this.forced = z;
    }

    public void setCompositionNumber(int i) {
        this.compositionNumber = i;
    }

    public String toString() {
        return "SubPicture [imageWidth=" + this.imageWidth + ", imageHeight=" + this.imageHeight + ", xOffset=" + this.xOffset + ", yOffset=" + this.yOffset + ", width=" + this.width + ", height=" + this.height + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", forced=" + this.forced + ", compositionNumber=" + this.compositionNumber + ", wasDecoded=" + this.wasDecoded + ", excluded=" + this.excluded + ", erasePatch=" + this.erasePatch + "]";
    }
}
