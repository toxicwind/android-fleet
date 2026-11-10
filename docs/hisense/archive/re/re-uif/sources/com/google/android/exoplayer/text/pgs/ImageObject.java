package com.google.android.exoplayer.text.pgs;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ImageObject {
    private int bufferSize;
    private List<ImageObjectFragment> fragmentList;
    private int height;
    private int paletteID;
    private int width;
    private int xOffset;
    private int yOffset;

    public ImageObject() {
        this.fragmentList = new ArrayList();
    }

    public ImageObject(ImageObject imageObject) {
        this.fragmentList = new ArrayList();
        this.fragmentList = new ArrayList(imageObject.fragmentList);
        this.paletteID = imageObject.paletteID;
        this.bufferSize = imageObject.bufferSize;
        this.width = imageObject.width;
        this.height = imageObject.height;
        this.xOffset = imageObject.xOffset;
        this.yOffset = imageObject.yOffset;
    }

    public List<ImageObjectFragment> getFragmentList() {
        return this.fragmentList;
    }

    public int getPaletteID() {
        return this.paletteID;
    }

    public void setPaletteID(int i) {
        this.paletteID = i;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void setBufferSize(int i) {
        this.bufferSize = i;
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

    public int getXOffset() {
        return this.xOffset;
    }

    public void setXOffset(int i) {
        this.xOffset = i;
    }

    public int getYOffset() {
        return this.yOffset;
    }

    public void setYOffset(int i) {
        this.yOffset = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ImageObject.class != obj.getClass()) {
            return false;
        }
        ImageObject imageObject = (ImageObject) obj;
        return this.bufferSize == imageObject.bufferSize && this.height == imageObject.height && this.paletteID == imageObject.paletteID && this.width == imageObject.width && this.xOffset == imageObject.xOffset && this.yOffset == imageObject.yOffset && this.fragmentList.equals(imageObject.fragmentList);
    }

    public int hashCode() {
        return (((((((((((this.fragmentList.hashCode() * 31) + this.paletteID) * 31) + this.bufferSize) * 31) + this.width) * 31) + this.height) * 31) + this.xOffset) * 31) + this.yOffset;
    }

    public String toString() {
        return "ImageObject [fragmentList=" + this.fragmentList + ", paletteID=" + this.paletteID + ", bufferSize=" + this.bufferSize + ", width=" + this.width + ", height=" + this.height + ", xOffset=" + this.xOffset + ", yOffset=" + this.yOffset + "]";
    }
}
