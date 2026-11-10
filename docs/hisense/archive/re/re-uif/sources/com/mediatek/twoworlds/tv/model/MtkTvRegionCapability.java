package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvRegionCapability {
    private boolean isEnable = false;
    private int height_max = 0;
    private int height_min = 0;
    private int width_max = 0;
    private int width_min = 0;
    private int y_max = 0;
    private int y_min = 0;
    private int x_max = 0;
    private int x_min = 0;

    public boolean getEnable() {
        return this.isEnable;
    }

    public int getXMin() {
        return this.x_min;
    }

    public int getXMax() {
        return this.x_max;
    }

    public int getYMin() {
        return this.y_min;
    }

    public int getYMax() {
        return this.y_max;
    }

    public int getWidthMin() {
        return this.width_min;
    }

    public int getWidthMax() {
        return this.width_max;
    }

    public int getHeightMin() {
        return this.height_min;
    }

    public int getHeightMax() {
        return this.height_max;
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }

    public void setXMin(int i) {
        this.x_min = i;
    }

    public void setXMax(int i) {
        this.x_max = i;
    }

    public void setYMin(int i) {
        this.y_min = i;
    }

    public void setYMax(int i) {
        this.y_max = i;
    }

    public void setWidthMin(int i) {
        this.width_min = i;
    }

    public void setWidthMax(int i) {
        this.width_max = i;
    }

    public void setHeightMin(int i) {
        this.height_min = i;
    }

    public void setHeightMax(int i) {
        this.height_max = i;
    }
}
