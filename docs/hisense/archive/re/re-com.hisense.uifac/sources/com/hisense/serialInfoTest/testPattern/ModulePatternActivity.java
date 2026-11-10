package com.hisense.serialInfoTest.testPattern;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.RelativeLayout;
import com.hisense.serialInfoTest.util.Logger;
import com.hisense.uifac.R;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;

/* JADX INFO: loaded from: classes.dex */
public class ModulePatternActivity extends Activity {
    private static final int[][] pattern_array = {new int[]{0, R.drawable.color_0}, new int[]{16, R.drawable.color_16}, new int[]{26, R.drawable.color_26}, new int[]{32, R.drawable.color_32}, new int[]{48, R.drawable.color_48}, new int[]{64, R.drawable.color_64}, new int[]{76, R.drawable.color_76}, new int[]{128, R.drawable.color_128}, new int[]{MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_NUM_READY, R.drawable.color_178}, new int[]{MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED, R.drawable.color_192}, new int[]{255, R.drawable.color_255}};
    private BitmapDrawable mBitmapDrawable = null;
    private RelativeLayout mMenuMainRelativeLayout;

    int getPattern(int i) {
        int i2 = 0;
        while (true) {
            int[][] iArr = pattern_array;
            if (i2 >= iArr.length) {
                return R.drawable.color_0;
            }
            if (i == iArr[i2][0]) {
                return iArr[i2][1];
            }
            i2++;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Logger.d("enter module onCreate");
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        getWindow().setFlags(1024, 1024);
        getWindow().addFlags(128);
        setContentView(R.layout.serial_test_pattern);
        this.mMenuMainRelativeLayout = (RelativeLayout) findViewById(R.id.testpattern_relativeLayout);
        Logger.i("index is " + extras.getInt("index", 0));
        this.mMenuMainRelativeLayout.setBackgroundResource(getPattern(extras.getInt("index", 0)));
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        Logger.d("enter module onNewIntent");
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras();
        Logger.i("index is " + extras.getInt("index", 0));
        BitmapDrawable bitmapDrawable = this.mBitmapDrawable;
        if (bitmapDrawable != null) {
            bitmapDrawable.getBitmap().recycle();
            this.mBitmapDrawable = null;
        }
        this.mMenuMainRelativeLayout.setBackgroundResource(getPattern(extras.getInt("index", 0)));
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
    }
}
