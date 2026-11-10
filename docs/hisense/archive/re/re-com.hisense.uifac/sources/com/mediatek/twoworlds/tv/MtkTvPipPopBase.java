package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.model.MtkTvPipPopFucusInfoBase;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvPipPopBase {
    private static final String TAG = "MtkTvPipPopBase";
    protected MtkTvKeyEventBase mtkKeyEvent = new MtkTvKeyEventBase();
    private MtkTvPipPopFucusInfoBase pipPopFocusInfo = new MtkTvPipPopFucusInfoBase(0, 0, 0, 0);

    public int popEnterNextTvMode() {
        return this.mtkKeyEvent.sendKey(0, 0);
    }

    public int popSwapWindow() {
        return this.mtkKeyEvent.sendKey(0, 0);
    }

    public int popSwitchAudioFocus() {
        return this.mtkKeyEvent.sendKey(0, 0);
    }

    public int popNextPipWindowSize() {
        return this.mtkKeyEvent.sendKey(0, 0);
    }

    public int popNextPipWindowPosition() {
        Log.d(TAG, "popGetPipFocusId 0");
        Log.d(TAG, "popGetPipFocusId 0" + this.mtkKeyEvent.sendKey(0, 0));
        return 0;
    }

    public boolean popGetPipFocusId() {
        boolean zPopGetPipFocusId_native = TVNativeWrapper.popGetPipFocusId_native();
        Log.d(TAG, "popGetPipFocusId " + zPopGetPipFocusId_native);
        return zPopGetPipFocusId_native;
    }

    public MtkTvPipPopFucusInfoBase popGetFocusInfo() {
        Log.d(TAG, "Enter popGetFocusInfo ");
        TVNativeWrapper.popGetFocusInfo_native(this.pipPopFocusInfo);
        Log.d(TAG, "popGetFocusInfo " + this.pipPopFocusInfo.getPopX() + this.pipPopFocusInfo.getPopY() + this.pipPopFocusInfo.getPopW() + this.pipPopFocusInfo.getPopH());
        return this.pipPopFocusInfo;
    }
}
