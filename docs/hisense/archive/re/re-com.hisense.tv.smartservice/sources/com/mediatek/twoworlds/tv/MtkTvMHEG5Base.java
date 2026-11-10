package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.model.MtkTvMHEG5PfgBase;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvMHEG5Base {
    public static final String TAG = "MtkTvMHEG5Base";
    public static boolean bInternalScrn;
    public static MtkTvMHEG5PfgBase pfg = new MtkTvMHEG5PfgBase();

    public MtkTvMHEG5Base() {
        Log.d(TAG, "MtkTvMHEG5Base object created");
    }

    public boolean getInternalScrnMode() {
        Log.d(TAG, "getInternalScrnMode entered");
        TVNativeWrapper.getInternalScrnMode_native(this);
        Log.i(TAG, "bInternalScrn:" + bInternalScrn);
        Log.d(TAG, "getInternalScrnMode exit");
        return bInternalScrn;
    }

    public MtkTvMHEG5PfgBase getPfgInfo() {
        Log.d(TAG, "getPfgString entered");
        TVNativeWrapper.getPfgInfo_native(this);
        Log.i(TAG, "str:" + pfg.pfgString + " show:" + pfg.show);
        Log.d(TAG, "getPfgString exit");
        return pfg;
    }

    public void setHbbtvStatus(boolean z) {
        Log.d(TAG, "setHbbtvStatus entered, bSuccess:" + z);
        TVNativeWrapper.setHbbtvStatus_native(z);
        Log.d(TAG, "setHbbtvStatus exit");
    }

    public void setInternalScrnMode(boolean z) {
        Log.i(TAG, "setInternalScrnMode enterdbInternal:" + z);
        bInternalScrn = z;
        Log.i(TAG, "setInternalScrnMode exit");
    }

    public void setMheg5Disable() {
        Log.d(TAG, "setMheg5Disable entered.");
        TVNativeWrapper.setMheg5Disable_native();
        Log.d(TAG, "setMheg5Disable exit");
    }

    public void setMheg5Enable() {
        Log.d(TAG, "setMheg5Enable entered.");
        TVNativeWrapper.setMheg5Enable_native();
        Log.d(TAG, "setMheg5Enable exit");
    }

    public void setMheg5Status(int i, int i2, int i3, int i4) {
        Log.d(TAG, "setMheg5Status entered.");
        TVNativeWrapper.setMheg5Status_native(i, i2, i3, i4);
        Log.d(TAG, "setMheg5Status exit");
    }

    public void setPfgResult(boolean z) {
        Log.d(TAG, "setPfgResult entered, bRight:" + z);
        TVNativeWrapper.setPfgResult_native(z);
        Log.d(TAG, "setPfgResult exit");
    }

    public void setPfgShow(boolean z) {
        Log.i(TAG, "setPfgShow enterdshow:" + z);
        pfg.show = z;
        Log.i(TAG, "setPfgShow exit");
    }

    public void setPfgString(String str) {
        Log.i(TAG, "setPfgString enterdstr:" + str);
        pfg.pfgString = str;
        Log.i(TAG, "setPfgString exit");
    }
}
