package com.android.launcher3.icons;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
public class BitmapInfo {
    public static final Bitmap LOW_RES_ICON;
    public final Bitmap icon;

    static {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
        LOW_RES_ICON = bitmapCreateBitmap;
        fromBitmap(bitmapCreateBitmap);
    }

    public BitmapInfo(Bitmap bitmap, int i) {
        this.icon = bitmap;
    }

    public static BitmapInfo fromBitmap(Bitmap bitmap) {
        return of(bitmap, 0);
    }

    public static BitmapInfo of(Bitmap bitmap, int i) {
        return new BitmapInfo(bitmap, i);
    }

    public interface Extender {
        default BitmapInfo getExtendedInfo(Bitmap bitmap, int i, BaseIconFactory baseIconFactory) {
            return BitmapInfo.of(bitmap, i);
        }
    }
}
