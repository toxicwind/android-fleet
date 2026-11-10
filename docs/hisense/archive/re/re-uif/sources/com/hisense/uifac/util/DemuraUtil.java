package com.hisense.uifac.util;

import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import com.hisense.uifac.R;

/* JADX INFO: loaded from: classes.dex */
public class DemuraUtil {
    private static int[] mDemuraPictureResourceId = {R.drawable.demura10ire, R.drawable.demura20ire, R.drawable.demura30ire, R.drawable.demura40ire, R.drawable.demura50ire, R.drawable.demura60ire, R.drawable.demura70ire, R.drawable.demura80ire, R.drawable.demura90ire, R.drawable.demura100ire, R.drawable.demuradotpattern, R.drawable.demuraframe};
    private static int mCurrentIndex = -1;
    private static View mLayout = null;

    public boolean isDemuraKeyCode(int i) {
        return i >= 5100 && i <= 5111;
    }

    public void setLayout(View view) {
        mLayout = view;
    }

    public boolean setDemuraPictureByKeyCode(int i) {
        Log.i("Fac_DemuraUtil", "setDemuraPictureByKeyCode keyCode = " + i);
        if (mLayout == null) {
            Log.i("Fac_DemuraUtil", "setDemuraPictureByKeyCode mLayout == null = ");
            return false;
        }
        if (!isDemuraKeyCode(i)) {
            Log.i("Fac_DemuraUtil", "setDemuraPictureByKeyCode not demura key keyCode = " + i);
            mLayout.setBackgroundResource(R.drawable.translucent_background);
            return false;
        }
        mCurrentIndex = i - 5100;
        Log.i("Fac_DemuraUtil", "setDemuraPictureByKeyCode mCurrentIndex = " + mCurrentIndex);
        mLayout.setBackgroundResource(mDemuraPictureResourceId[mCurrentIndex]);
        return true;
    }

    public static void setDemuraPropStatus(String str) {
        SystemProperties.set("sys.vt.hisense.factory.demura.card_show", str);
    }
}
