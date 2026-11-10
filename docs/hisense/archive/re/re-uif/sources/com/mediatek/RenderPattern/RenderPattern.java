package com.mediatek.RenderPattern;

import android.graphics.Bitmap;
import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
public class RenderPattern {
    public static int INIT_ERROR;
    public static int INIT_OK;
    private boolean m_init;
    private int m_osdPath;
    private long m_patternHandle;

    private native int nativeDeinitRenderPattern(long j);

    private native long nativeInitRenderPattern(int i);

    private native int nativeRenderPatternOnOsd(Rect rect, Rect rect2, int i, int i2, int[] iArr, int i3, long j);

    private native int nativeSendCmd2K();

    private native int nativeSendCmd4K();

    static {
        try {
            System.loadLibrary("patapi");
            System.out.println("System.loadLibrary: successfully");
        } catch (UnsatisfiedLinkError e) {
            System.out.println("System.loadLibrary" + e);
            e.printStackTrace(System.out);
        }
        INIT_OK = 0;
        INIT_ERROR = -1;
    }

    public RenderPattern() {
        this.m_osdPath = 1;
        this.m_init = false;
    }

    public RenderPattern(int i) {
        this.m_osdPath = i;
        this.m_init = false;
    }

    public int initRenderPattern() {
        long jNativeInitRenderPattern = nativeInitRenderPattern(this.m_osdPath);
        this.m_patternHandle = jNativeInitRenderPattern;
        if (jNativeInitRenderPattern == 0) {
            return INIT_ERROR;
        }
        this.m_init = true;
        return INIT_OK;
    }

    public int deinitRenderPattern() {
        if (!this.m_init) {
            return 0;
        }
        int iNativeDeinitRenderPattern = nativeDeinitRenderPattern(this.m_patternHandle);
        if (iNativeDeinitRenderPattern == 0) {
            this.m_init = false;
        }
        return iNativeDeinitRenderPattern;
    }

    public int renderPatternOnOsd(Rect rect, Rect rect2, Bitmap bitmap) {
        int[] iArr = new int[bitmap.getRowBytes() * bitmap.getHeight()];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        System.out.println("renderPatternOnOsd, width = " + bitmap.getWidth() + ", height = " + bitmap.getHeight() + ", size = " + bitmap.getRowBytes());
        return nativeRenderPatternOnOsd(rect, rect2, bitmap.getWidth(), bitmap.getHeight(), iArr, bitmap.getRowBytes(), this.m_patternHandle);
    }

    public int SendCmd2K() {
        return nativeSendCmd2K();
    }

    public int SendCmd4K() {
        return nativeSendCmd2K();
    }
}
