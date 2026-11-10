package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.model.MtkTvPipPopFucusInfoBase;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvPipPopBase {
    public static final String TAG = "MtkTvPipPopBase";
    public MtkTvKeyEventBase mtkKeyEvent = new MtkTvKeyEventBase();
    public MtkTvPipPopFucusInfoBase pipPopFocusInfo = new MtkTvPipPopFucusInfoBase(0, 0, 0, 0);

    public int popEnterNextTvMode() {
        return this.mtkKeyEvent.sendKey(0, 0);
    }

    public MtkTvPipPopFucusInfoBase popGetFocusInfo() {
        Log.d(TAG, "Enter popGetFocusInfo ");
        TVNativeWrapper.popGetFocusInfo_native(this.pipPopFocusInfo);
        Log.d(TAG, "popGetFocusInfo " + this.pipPopFocusInfo.getPopX() + this.pipPopFocusInfo.getPopY() + this.pipPopFocusInfo.getPopW() + this.pipPopFocusInfo.getPopH());
        return this.pipPopFocusInfo;
    }

    public boolean popGetPipFocusId() {
        boolean zPopGetPipFocusId_native = TVNativeWrapper.popGetPipFocusId_native();
        a.a("popGetPipFocusId ", zPopGetPipFocusId_native, TAG);
        return zPopGetPipFocusId_native;
    }

    public int popNextPipWindowPosition() {
        a.b("popGetPipFocusId ", 0, TAG);
        Log.d(TAG, "popGetPipFocusId 0" + this.mtkKeyEvent.sendKey(0, 0));
        return 0;
    }

    public int popNextPipWindowSize() {
        return this.mtkKeyEvent.sendKey(0, 0);
    }

    public int popSwapWindow() {
        return this.mtkKeyEvent.sendKey(0, 0);
    }

    public int popSwitchAudioFocus() {
        return this.mtkKeyEvent.sendKey(0, 0);
    }
}
