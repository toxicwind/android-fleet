package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvPipPopFucusInfoBase {
    private static final String TAG = "MtkTvPipPopFucusInfoBase";
    private int h;
    private int w;
    private int x;
    private int y;

    public MtkTvPipPopFucusInfoBase() {
        Log.d(TAG, "MtkTvPipPopFucusInfoBase Constructor\n");
    }

    public MtkTvPipPopFucusInfoBase(int i, int i2, int i3, int i4) {
        this.x = i;
        this.y = i2;
        this.w = i3;
        this.h = i4;
    }

    public int getPopX() {
        Log.d(TAG, "getPopX" + this.x);
        return this.x;
    }

    public int getPopY() {
        Log.d(TAG, "getPopY" + this.y);
        return this.y;
    }

    public int getPopW() {
        Log.d(TAG, "getPopW" + this.w);
        return this.w;
    }

    public int getPopH() {
        Log.d(TAG, "getPopH" + this.h);
        return this.h;
    }

    public void setPopX(int i) {
        Log.d(TAG, "setPopX" + i);
        this.x = i;
    }

    public void setPopY(int i) {
        Log.d(TAG, "setPopY" + i);
        this.y = i;
    }

    public void setPopW(int i) {
        Log.d(TAG, "setPopWw" + i);
        this.w = i;
    }

    public void setPopH(int i) {
        Log.d(TAG, "setPopH" + i);
        this.h = i;
    }
}
