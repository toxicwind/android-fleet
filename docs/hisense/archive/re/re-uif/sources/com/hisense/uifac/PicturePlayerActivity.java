package com.hisense.uifac;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacPropertyDemo;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class PicturePlayerActivity extends Activity {
    public static String PICTURE_FILE_NAME = "pictureFileName";
    public static String PICTURE_FILE_PATH = "pictureFileDirctory";
    private Bitmap bitmap;
    private Bitmap mBitmap;
    private Bundle mBundle;
    private SurfaceHolder mHolder;
    private boolean mIs4KPhoto;
    private Paint mPaint;
    private String path;
    private FacFunction_Adapter mFacFunction_Adapter = null;
    private String mPictureFileName = null;
    private String mPictureFilePath = null;
    private boolean isFocusPic = false;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBundle = getIntent().getExtras();
        this.mFacFunction_Adapter = FacFunction_Adapter.getInstance(getApplicationContext());
        setContentView(R.layout.picture_player);
        getWindow().addFlags(128);
        this.mPaint = new Paint();
        SurfaceHolder holder = ((SurfaceView) findViewById(R.id.surfaceview)).getHolder();
        this.mHolder = holder;
        holder.setFixedSize(3840, 2160);
        preparePlayPicture();
        this.mHolder.addCallback(new SurfaceHolder.Callback() { // from class: com.hisense.uifac.PicturePlayerActivity.1
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                Log.i("Fac_PicturePlayerActivity", "******surfaceDestroyed******");
                PicturePlayerActivity.this.isFocusPic = false;
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                Canvas canvasLockCanvas = PicturePlayerActivity.this.mHolder.lockCanvas(new Rect(0, 0, 3840, 2160));
                Log.i("Fac_PicturePlayerActivity", " enter surfaceCreated :canvas :" + canvasLockCanvas + "mHolder :" + PicturePlayerActivity.this.mHolder);
                if (canvasLockCanvas == null || PicturePlayerActivity.this.mHolder == null || PicturePlayerActivity.this.mPaint == null || PicturePlayerActivity.this.bitmap == null) {
                    return;
                }
                int width = PicturePlayerActivity.this.bitmap.getWidth();
                int height = PicturePlayerActivity.this.bitmap.getHeight();
                canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                if (PicturePlayerActivity.this.mIs4KPhoto) {
                    canvasLockCanvas.drawBitmap(PicturePlayerActivity.this.bitmap, (3840 - width) / 2, (2160 - height) / 2, PicturePlayerActivity.this.mPaint);
                } else {
                    int i = ((1920 - width) / 2) * 2;
                    int i2 = ((1080 - height) / 2) * 2;
                    canvasLockCanvas.drawBitmap(PicturePlayerActivity.this.bitmap, new Rect(0, 0, 1920, 1080), new Rect(i, i2, i + 3840, i2 + 2160), PicturePlayerActivity.this.mPaint);
                }
                PicturePlayerActivity.this.mHolder.unlockCanvasAndPost(canvasLockCanvas);
                PicturePlayerActivity.this.mHolder.lockCanvas(new Rect(0, 0, 0, 0));
                PicturePlayerActivity.this.mHolder.unlockCanvasAndPost(canvasLockCanvas);
            }
        });
    }

    private void preparePlayPicture() {
        int i;
        int i2;
        Bundle bundle = this.mBundle;
        if (bundle == null || bundle.getString(PICTURE_FILE_PATH) == null || this.mBundle.getString(PICTURE_FILE_NAME) == null) {
            return;
        }
        this.mPictureFilePath = this.mBundle.getString(PICTURE_FILE_PATH);
        this.mPictureFileName = this.mBundle.getString(PICTURE_FILE_NAME);
        Log.i("Fac_PicturePlayerActivity", " mPictureFileName is : " + this.mPictureFileName);
        if (this.mPictureFileName.contains("Resolution_Focus")) {
            this.isFocusPic = true;
        }
        String str = this.mPictureFilePath + File.separator + this.mPictureFileName;
        this.path = str;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
        this.mBitmap = bitmapDecodeFile;
        if (bitmapDecodeFile != null) {
            this.mIs4KPhoto = check4kPhoto(bitmapDecodeFile);
            int width = this.mBitmap.getWidth();
            int height = this.mBitmap.getHeight();
            if (this.mIs4KPhoto) {
                i = 3840;
                i2 = 2160;
            } else {
                i = 1920;
                i2 = 1080;
            }
            Log.i("Fac_PicturePlayerActivity", "setImageBitmap: " + width + "x" + height + " -> " + i + "x" + i2);
            float f = (float) i;
            float f2 = (float) width;
            float f3 = (float) i2;
            float f4 = (float) height;
            float fMin = Math.min(f / f2, f3 / f4);
            Matrix matrix = new Matrix();
            matrix.setScale(fMin, fMin);
            matrix.postTranslate((f - (f2 * fMin)) / 2.0f, (f3 - (f4 * fMin)) / 2.0f);
            setImageMatrix(this.mBitmap, matrix);
        }
    }

    public boolean check4kPhoto(Bitmap bitmap) {
        Boolean bool;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Log.i("Fac_PicturePlayerActivity", "original bitmap width is : " + width + "original bitmap height is : " + height);
        if ((width > height && (width >= 3840 || height >= 2160)) || (width <= height && (width >= 2160 || height >= 3840))) {
            bool = Boolean.TRUE;
        } else {
            bool = Boolean.FALSE;
        }
        return bool.booleanValue();
    }

    public void setImageMatrix(Bitmap bitmap, Matrix matrix) {
        if (bitmap != null) {
            Log.i("Fac_PicturePlayerActivity", "enter setImageMatrix" + this.mIs4KPhoto);
            this.bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Log.d("Fac_PicturePlayerActivity", "****PicturePlayerActivity**** onKeyDown: " + keyEvent);
        if (i == 4390) {
            return true;
        }
        if (i == 4357) {
            finish();
            return true;
        }
        if (i == 21) {
            if (this.isFocusPic && FacPropertyDemo.HIFAC_LASER_FOCUS_SUPPORT) {
                this.mFacFunction_Adapter.setAdjustFocus(1);
            }
        } else if (i == 22 && this.isFocusPic && FacPropertyDemo.HIFAC_LASER_FOCUS_SUPPORT) {
            this.mFacFunction_Adapter.setAdjustFocus(2);
        }
        return super.onKeyDown(i, keyEvent);
    }
}
