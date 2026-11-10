package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvRectangle {
    public float h;
    public float w;
    public float x;
    public float y;

    public MtkTvRectangle() {
    }

    public float getH() {
        return this.h;
    }

    public float getW() {
        return this.w;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setH(float f2) {
        this.h = f2;
    }

    public void setW(float f2) {
        this.w = f2;
    }

    public void setX(float f2) {
        this.x = f2;
    }

    public void setY(float f2) {
        this.y = f2;
    }

    public MtkTvRectangle(float f2, float f3, float f4, float f5) {
        this.x = f2;
        this.y = f3;
        this.w = f4;
        this.h = f5;
    }
}
