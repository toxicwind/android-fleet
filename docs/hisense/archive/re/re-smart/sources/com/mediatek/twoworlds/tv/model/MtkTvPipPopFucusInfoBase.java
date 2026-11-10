package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvPipPopFucusInfoBase {
    public static final String TAG = "MtkTvPipPopFucusInfoBase";
    public int h;
    public int w;
    public int x;
    public int y;

    public MtkTvPipPopFucusInfoBase() {
        Log.d(TAG, "MtkTvPipPopFucusInfoBase Constructor\n");
    }

    public int getPopH() {
        a.b(a.a("getPopH"), this.h, TAG);
        return this.h;
    }

    public int getPopW() {
        a.b(a.a("getPopW"), this.w, TAG);
        return this.w;
    }

    public int getPopX() {
        a.b(a.a("getPopX"), this.x, TAG);
        return this.x;
    }

    public int getPopY() {
        a.b(a.a("getPopY"), this.y, TAG);
        return this.y;
    }

    public void setPopH(int i) {
        a.b("setPopH", i, TAG);
        this.h = i;
    }

    public void setPopW(int i) {
        a.b("setPopWw", i, TAG);
        this.w = i;
    }

    public void setPopX(int i) {
        a.b("setPopX", i, TAG);
        this.x = i;
    }

    public void setPopY(int i) {
        a.b("setPopY", i, TAG);
        this.y = i;
    }

    public MtkTvPipPopFucusInfoBase(int i, int i2, int i3, int i4) {
        this.x = i;
        this.y = i2;
        this.w = i3;
        this.h = i4;
    }
}
