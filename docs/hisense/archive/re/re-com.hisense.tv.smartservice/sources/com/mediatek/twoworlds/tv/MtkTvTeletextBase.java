package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.model.MtkTvTeletextPageBase;
import com.mediatek.twoworlds.tv.model.MtkTvTeletextTopBlockBase;
import com.mediatek.twoworlds.tv.model.MtkTvTeletextTopGroupBase;
import com.mediatek.twoworlds.tv.model.MtkTvTeletextTopPageBase;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvTeletextBase {
    public static final String TAG = "MtkTvTeletextBase";
    public MtkTvKeyEventBase mtkKeyEvent = new MtkTvKeyEventBase();

    public MtkTvTeletextBase() {
        Log.d(TAG, "MtkTvTeletextBase object created");
    }

    public MtkTvTeletextPageBase getCurrentTeletextPage() {
        Log.d(TAG, "Enter getCurrentTeletextPage Here.\n");
        MtkTvTeletextPageBase mtkTvTeletextPageBase = new MtkTvTeletextPageBase();
        int currentTeletextPage_native = TVNativeWrapper.getCurrentTeletextPage_native(mtkTvTeletextPageBase);
        if (currentTeletextPage_native != 0) {
            a.b("TVNativeWrapper.getCurrentTeletextPage_native failed! return ", currentTeletextPage_native, ".\n", TAG);
        }
        return mtkTvTeletextPageBase;
    }

    public List<MtkTvTeletextTopBlockBase> getTeletextTopBlockList() {
        Log.d(TAG, "Enter getTeletextTopBlockList Here.\n");
        ArrayList arrayList = new ArrayList();
        int teletextTopBlockList_native = TVNativeWrapper.getTeletextTopBlockList_native(arrayList);
        if (teletextTopBlockList_native == 0) {
            return arrayList;
        }
        a.b("TVNativeWrapper.getTeletextTopBlockList_native failed! return ", teletextTopBlockList_native, ".\n", TAG);
        return null;
    }

    public List<MtkTvTeletextTopGroupBase> getTeletextTopGroupList(MtkTvTeletextTopBlockBase mtkTvTeletextTopBlockBase) {
        Log.d(TAG, "Enter getTeletextTopGroupList Here.\n");
        if (mtkTvTeletextTopBlockBase == null || mtkTvTeletextTopBlockBase.getBlockPageAddr() == null || 255 == mtkTvTeletextTopBlockBase.getBlockPageAddr().getPageNumber()) {
            Log.e(TAG, "Invalid teletext top block!\n");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int teletextTopGroupList_native = TVNativeWrapper.getTeletextTopGroupList_native(mtkTvTeletextTopBlockBase, arrayList);
        if (teletextTopGroupList_native == 0) {
            return arrayList;
        }
        a.b("TVNativeWrapper.getTeletextTopGroupList failed! return ", teletextTopGroupList_native, ".\n", TAG);
        return null;
    }

    public List<MtkTvTeletextTopPageBase> getTeletextTopPageList(MtkTvTeletextTopGroupBase mtkTvTeletextTopGroupBase) {
        Log.d(TAG, "Enter getTeletextTopPageList Here.\n");
        if (mtkTvTeletextTopGroupBase == null || mtkTvTeletextTopGroupBase.getGroupPageAddr() == null || 255 == mtkTvTeletextTopGroupBase.getGroupPageAddr().getPageNumber()) {
            Log.e(TAG, "Invalid teletext top group!\n");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int teletextTopPageList_native = TVNativeWrapper.getTeletextTopPageList_native(mtkTvTeletextTopGroupBase, arrayList);
        if (teletextTopPageList_native == 0) {
            return arrayList;
        }
        a.b("TVNativeWrapper.getTeletextTopPageList failed! return ", teletextTopPageList_native, ".\n", TAG);
        return null;
    }

    public int setTeletextPage(MtkTvTeletextPageBase mtkTvTeletextPageBase) {
        Log.d(TAG, "Enter setTeletextPage Here.\n");
        if (mtkTvTeletextPageBase != null && 255 != mtkTvTeletextPageBase.getPageNumber()) {
            return TVNativeWrapper.setTeletextPage_native(mtkTvTeletextPageBase);
        }
        Log.e(TAG, "Invalid teletext page address!\n");
        return -1;
    }

    public int start() {
        return this.mtkKeyEvent.sendKeyClick(this.mtkKeyEvent.androidKeyToDFBkey(233));
    }

    public int stop() {
        return this.mtkKeyEvent.sendKeyClick(this.mtkKeyEvent.androidKeyToDFBkey(4));
    }

    public boolean teletextHasTopInfo() {
        Log.d(TAG, "Enter teletextHasTopInfo Here.\n");
        return TVNativeWrapper.teletextHasTopInfo_native();
    }
}
