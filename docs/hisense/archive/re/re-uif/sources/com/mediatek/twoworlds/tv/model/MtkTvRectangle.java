package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvRectangle {
    private float h;
    private float w;
    private float x;
    private float y;

    public MtkTvRectangle() {
    }

    public MtkTvRectangle(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.w = f3;
        this.h = f4;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float f) {
        this.x = f;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float f) {
        this.y = f;
    }

    public float getW() {
        return this.w;
    }

    public void setW(float f) {
        this.w = f;
    }

    public float getH() {
        return this.h;
    }

    public void setH(float f) {
        this.h = f;
    }
}
