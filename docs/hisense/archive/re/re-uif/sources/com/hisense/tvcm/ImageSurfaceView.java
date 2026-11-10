package com.hisense.tvcm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/* JADX INFO: loaded from: classes.dex */
public class ImageSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private Bitmap mBitmap;
    private boolean mIs4KPhoto;
    private SurfaceHolder mSurfaceHolder;

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    public ImageSurfaceView(Context context) {
        super(context);
        Log.i("Fac_ImageSurfaceView", "ImageSurfaceView context is " + context);
        initImageSurfaceView();
    }

    private void initImageSurfaceView() {
        Log.i("Fac_ImageSurfaceView", "enter initImageSurfaceView");
        SurfaceHolder holder = getHolder();
        this.mSurfaceHolder = holder;
        holder.setFormat(-2);
    }

    private void drawImage() {
        Canvas canvasLockCanvas = this.mSurfaceHolder.lockCanvas(new Rect(0, 0, 3840, 2160));
        Log.i("Fac_ImageSurfaceView", "enter drawImage :canvas :" + canvasLockCanvas + "mSurfaceHolder :" + this.mSurfaceHolder);
        Paint paint = new Paint();
        if (canvasLockCanvas == null || this.mSurfaceHolder == null) {
            return;
        }
        try {
            int width = this.mBitmap.getWidth();
            int height = this.mBitmap.getHeight();
            canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            if (this.mIs4KPhoto) {
                canvasLockCanvas.drawBitmap(this.mBitmap, (3840 - width) / 2, (2160 - height) / 2, paint);
            } else {
                int i = ((1920 - width) / 2) * 2;
                int i2 = ((1080 - height) / 2) * 2;
                canvasLockCanvas.drawBitmap(this.mBitmap, new Rect(0, 0, 1920, 1080), new Rect(i, i2, i + 3840, i2 + 2160), paint);
            }
            this.mSurfaceHolder.unlockCanvasAndPost(canvasLockCanvas);
        } catch (Exception e) {
            Log.e("Fac_ImageSurfaceView", e.getMessage());
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i("Fac_ImageSurfaceView", "enter surfaceCreated***************");
        drawImage();
    }
}
