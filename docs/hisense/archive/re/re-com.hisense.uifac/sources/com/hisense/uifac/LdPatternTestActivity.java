package com.hisense.uifac;

import android.app.Activity;
import android.app.Instrumentation;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.uifac.menu.util.MenuConfigManager;
import com.mediatek.twoworlds.tv.MtkTvScanCeBase;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LdPatternTestActivity extends Activity {
    private static Handler mHandler;
    private static Handler mNoUIHandler;
    private Bitmap bitmap;
    private SurfaceHolder mHolder;
    private boolean mIs4KPhoto;
    private Paint mPaint;
    MenuConfigManager mcf;
    private int mCurrentIndex = 0;
    private boolean mIsTestRunning = false;
    private FacFunction_Adapter mFacFunction_Adapter = null;
    private List<String> mPathList = new ArrayList();

    static /* synthetic */ int access$508(LdPatternTestActivity ldPatternTestActivity) {
        int i = ldPatternTestActivity.mCurrentIndex;
        ldPatternTestActivity.mCurrentIndex = i + 1;
        return i;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Log.i("Fac_LdPatternTestActivity", "enter Ld Test");
        super.onCreate(bundle);
        this.mcf = MenuConfigManager.getInstance(this);
        setTheme(R.style.AnimationTheme);
        setContentView(R.layout.serial_test_pattern);
        this.mPaint = new Paint();
        SurfaceHolder holder = ((SurfaceView) findViewById(R.id.ld_surfaceview)).getHolder();
        this.mHolder = holder;
        holder.setFixedSize(3840, 2160);
        this.mFacFunction_Adapter = FacFunction_Adapter.getInstance(getApplicationContext());
        initHandler();
        initLdPattern();
        this.mHolder.addCallback(new SurfaceHolder.Callback() { // from class: com.hisense.uifac.LdPatternTestActivity.1
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                Log.i("Fac_LdPatternTestActivity", "******surfaceDestroyed******");
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                LdPatternTestActivity.this.refreshUI();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                Log.i("Fac_LdPatternTestActivity", "******surfaceChanged******");
            }
        });
    }

    private void initHandler() {
        mHandler = new MyHandler(this);
        HandlerThread handlerThread = new HandlerThread("nouithread");
        handlerThread.start();
        mNoUIHandler = new WeakHandler<LdPatternTestActivity>(this, handlerThread.getLooper(), this) { // from class: com.hisense.uifac.LdPatternTestActivity.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.hisense.uifac.LdPatternTestActivity.WeakHandler
            public void handleTheMessage(LdPatternTestActivity ldPatternTestActivity, Message message) {
                Log.i("Fac_LdPatternTestActivity", "ThreadHandler msg.what = " + message.what);
                if (message.what != 3001) {
                    return;
                }
                new Instrumentation().sendKeyDownUpSync(4);
            }
        };
    }

    private void destoryHandler() {
        if (mHandler != null) {
            Log.i("Fac_LdPatternTestActivity", "mHandler.removeCallbacksAndMessages(null);");
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        if (mNoUIHandler != null) {
            Log.i("Fac_LdPatternTestActivity", "mThreadHandler.");
            mNoUIHandler.removeCallbacksAndMessages(null);
            mNoUIHandler = null;
        }
    }

    private static class MyHandler extends Handler {
        WeakReference<Activity> reference;

        public MyHandler(Activity activity) {
            this.reference = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.reference != null) {
                Log.i("Fac_LdPatternTestActivity", "MyHandler Msg.what = " + message.what);
                LdPatternTestActivity ldPatternTestActivity = (LdPatternTestActivity) this.reference.get();
                if (ldPatternTestActivity != null) {
                    switch (message.what) {
                        case MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHENGWANG /* 1001 */:
                            ldPatternTestActivity.setPicturePlay();
                            if (ldPatternTestActivity.mPathList.size() > 1) {
                                LdPatternTestActivity.mHandler.sendEmptyMessageDelayed(MtkTvScanCeBase.OPERATOR_HUNAN_GUANGDIAN, 1000L);
                                ldPatternTestActivity.mIsTestRunning = true;
                            }
                            break;
                        case MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG /* 1002 */:
                            LdPatternTestActivity.mHandler.removeCallbacksAndMessages(null);
                            ldPatternTestActivity.mIsTestRunning = false;
                            Log.i("Fac_LdPatternTestActivity", "stop on index:" + ldPatternTestActivity.mCurrentIndex);
                            break;
                        case MtkTvScanCeBase.OPERATOR_HUNAN_GUANGDIAN /* 1003 */:
                            LdPatternTestActivity.access$508(ldPatternTestActivity);
                            if (ldPatternTestActivity.mCurrentIndex >= ldPatternTestActivity.mPathList.size()) {
                                ldPatternTestActivity.mCurrentIndex = 0;
                            }
                            ldPatternTestActivity.setPicturePlay();
                            LdPatternTestActivity.mHandler.sendEmptyMessageDelayed(MtkTvScanCeBase.OPERATOR_HUNAN_GUANGDIAN, 1000L);
                            break;
                    }
                }
                Log.i("Fac_LdPatternTestActivity", "MyHandler RegisterCheckActivity is null");
                return;
            }
            Log.i("Fac_LdPatternTestActivity", "MyHandler reference is null");
        }
    }

    private void initLdPattern() {
        File file = new File("/hisprod/config/factory/pattern");
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                Log.i("Fac_LdPatternTestActivity", "./pattern exist, but dir is null!");
                showLDMsg("pattern Not Exist");
                mNoUIHandler.sendEmptyMessageDelayed(3001, 1500L);
                return;
            }
            for (File file2 : fileArrListFiles) {
                if (file2.getName().endsWith(".jpg")) {
                    this.mPathList.add(file2.getPath());
                }
            }
            List<String> list = this.mPathList;
            if (list != null && list.size() > 0) {
                sortFiles(this.mPathList);
                return;
            }
            Log.i("Fac_LdPatternTestActivity", "./pattern exist, but no ld file!");
            showLDMsg("pattern Not Exist");
            mNoUIHandler.sendEmptyMessageDelayed(3001, 1500L);
            return;
        }
        Log.i("Fac_LdPatternTestActivity", "no ./pattern dir!");
        showLDMsg("pattern Not Exist");
        mNoUIHandler.sendEmptyMessageDelayed(3001, 1500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPicturePlay() {
        int i;
        int i2;
        this.bitmap = null;
        List<String> list = this.mPathList;
        if (list == null || list.size() <= 0) {
            return;
        }
        String str = this.mPathList.get(this.mCurrentIndex);
        Log.i("Fac_LdPatternTestActivity", "current index:" + this.mCurrentIndex);
        Log.i("Fac_LdPatternTestActivity", "current path:" + str);
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
        if (bitmapDecodeFile != null) {
            this.mIs4KPhoto = check4kPhoto(bitmapDecodeFile);
            int width = bitmapDecodeFile.getWidth();
            int height = bitmapDecodeFile.getHeight();
            if (this.mIs4KPhoto) {
                i = 3840;
                i2 = 2160;
            } else {
                i = 1920;
                i2 = 1080;
            }
            Log.i("Fac_LdPatternTestActivity", "setImageBitmap: " + width + "x" + height + " -> " + i + "x" + i2);
            float f = (float) i;
            float f2 = (float) width;
            float f3 = (float) i2;
            float f4 = (float) height;
            float fMin = Math.min(f / f2, f3 / f4);
            Matrix matrix = new Matrix();
            matrix.setScale(fMin, fMin);
            matrix.postTranslate((f - (f2 * fMin)) / 2.0f, (f3 - (f4 * fMin)) / 2.0f);
            setImageMatrix(bitmapDecodeFile, matrix);
            return;
        }
        Log.i("Fac_LdPatternTestActivity", "bit map is null");
    }

    public void setImageMatrix(Bitmap bitmap, Matrix matrix) {
        if (bitmap != null) {
            Log.i("Fac_LdPatternTestActivity", "enter setImageMatrix" + this.mIs4KPhoto);
            this.bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
        refreshUI();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUI() {
        Bitmap bitmap;
        Log.i("Fac_LdPatternTestActivity", "refreshUI");
        Canvas canvasLockCanvas = this.mHolder.lockCanvas(new Rect(0, 0, 3840, 2160));
        Log.i("Fac_LdPatternTestActivity", " enter surfaceCreated :canvas :" + canvasLockCanvas + "mHolder :" + this.mHolder);
        if (canvasLockCanvas == null || this.mHolder == null || this.mPaint == null || (bitmap = this.bitmap) == null) {
            return;
        }
        int width = bitmap.getWidth();
        int height = this.bitmap.getHeight();
        canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        if (this.mIs4KPhoto) {
            canvasLockCanvas.drawBitmap(this.bitmap, (3840 - width) / 2, (2160 - height) / 2, this.mPaint);
        } else {
            int i = ((1920 - width) / 2) * 2;
            int i2 = ((1080 - height) / 2) * 2;
            canvasLockCanvas.drawBitmap(this.bitmap, new Rect(0, 0, 1920, 1080), new Rect(i, i2, i + 3840, i2 + 2160), this.mPaint);
        }
        this.mHolder.unlockCanvasAndPost(canvasLockCanvas);
        this.mHolder.lockCanvas(new Rect(0, 0, 0, 0));
        this.mHolder.unlockCanvasAndPost(canvasLockCanvas);
    }

    public boolean check4kPhoto(Bitmap bitmap) {
        Boolean bool;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Log.i("Fac_LdPatternTestActivity", "original bitmap width is : " + width + "original bitmap height is : " + height);
        if ((width > height && (width >= 3840 || height >= 2160)) || (width <= height && (width >= 2160 || height >= 3840))) {
            bool = Boolean.TRUE;
        } else {
            bool = Boolean.FALSE;
        }
        return bool.booleanValue();
    }

    private void sortFiles(List<String> list) {
        Collections.sort(list, new Comparator() { // from class: com.hisense.uifac.-$$Lambda$LdPatternTestActivity$EFE0EqRNWGbt6nGKZdf9VkgdRpc
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return LdPatternTestActivity.lambda$sortFiles$0((String) obj, (String) obj2);
            }
        });
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            Log.i("Fac_LdPatternTestActivity", "file path:" + it.next());
        }
        this.mPathList = list;
        mHandler.sendEmptyMessage(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHENGWANG);
        this.mFacFunction_Adapter.setLDTestEnable(1);
    }

    static /* synthetic */ int lambda$sortFiles$0(String str, String str2) {
        int iIntValue = Integer.valueOf(str.trim().substring(str.lastIndexOf("/") + 1).split("[.jpg]")[0]).intValue();
        int iIntValue2 = Integer.valueOf(str2.trim().substring(str2.lastIndexOf("/") + 1).split("[.jpg]")[0]).intValue();
        if (iIntValue == iIntValue2) {
            return 0;
        }
        return iIntValue < iIntValue2 ? -1 : 1;
    }

    @Override // android.app.Activity
    protected void onStop() {
        Log.i("Fac_LdPatternTestActivity", "enter ld onStop");
        super.onStop();
        this.mIsTestRunning = false;
        this.mCurrentIndex = 0;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Log.i("Fac_LdPatternTestActivity", "onKeyDown keycode = " + i + " " + keyEvent.toString());
        if (i != 85 && i != 21 && i != 22 && i != 4) {
            return true;
        }
        if (i == 85) {
            if (this.mIsTestRunning) {
                Log.i("Fac_LdPatternTestActivity", "ld test will pause");
                mHandler.sendEmptyMessage(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG);
            } else {
                Log.i("Fac_LdPatternTestActivity", "ld test will restart again");
                Log.i("Fac_LdPatternTestActivity", "later will continue on index:" + this.mCurrentIndex);
                mHandler.sendEmptyMessage(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHENGWANG);
            }
        }
        if (i == 22 && !this.mIsTestRunning && this.mPathList.size() > 1) {
            int i2 = this.mCurrentIndex + 1;
            this.mCurrentIndex = i2;
            if (i2 >= this.mPathList.size()) {
                this.mCurrentIndex = 0;
            }
            Log.i("Fac_LdPatternTestActivity", "receive dpad right munual play");
            setPicturePlay();
        }
        if (i == 21 && !this.mIsTestRunning && this.mPathList.size() > 1) {
            int i3 = this.mCurrentIndex - 1;
            this.mCurrentIndex = i3;
            if (i3 < 0) {
                this.mCurrentIndex = this.mPathList.size() - 1;
            }
            Log.i("Fac_LdPatternTestActivity", "receive dpad left munual play");
            setPicturePlay();
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        Log.i("Fac_LdPatternTestActivity", "onBackPressed");
        this.mIsTestRunning = false;
        this.mCurrentIndex = 0;
        destoryHandler();
        this.mFacFunction_Adapter.setLDTestEnable(0);
    }

    private void showLDMsg(String str) {
        this.mcf.displayHisToast(str);
    }

    static abstract class WeakHandler<T> extends Handler {
        private static String TAG = "fac_WeakHandler";
        protected WeakReference<T> reference;

        protected abstract void handleTheMessage(T t, Message message);

        public WeakHandler(Looper looper, T t) {
            super(looper);
            this.reference = new WeakReference<>(t);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Log.i(TAG, "msg = " + message.what);
            super.handleMessage(message);
            T t = this.reference.get();
            if (t == null) {
                return;
            }
            handleTheMessage(t, message);
        }
    }
}
