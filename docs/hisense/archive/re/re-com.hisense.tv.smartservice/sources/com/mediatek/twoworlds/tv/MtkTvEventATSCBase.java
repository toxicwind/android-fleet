package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.model.MtkTvEventInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvEventATSCBase {
    public static final String TAG = "TV_MtkTvEventATSCBase";

    public int[] checkEITStauts(int i, int i2) {
        return TVNativeWrapper.checkEITStauts_native(i, i2);
    }

    public boolean checkEventBlock(int i) {
        Log.d(TAG, "checkEventBlock\n");
        return false;
    }

    public int freeEvent(int i) {
        Log.d(TAG, "freeEvent\n");
        return TVNativeWrapper.freeEvent_native(i);
    }

    public MtkTvEventInfoBase getEvent(int i) {
        Log.d(TAG, "getEvent\n");
        MtkTvEventInfoBase mtkTvEventInfoBase = new MtkTvEventInfoBase();
        TVNativeWrapper.getEvent_native(i, mtkTvEventInfoBase);
        Log.d(TAG, mtkTvEventInfoBase.toString());
        return mtkTvEventInfoBase;
    }

    public MtkTvEventInfoBase getEventByIndex(int i, int i2) {
        Log.d(TAG, "getEventByIndex\n");
        MtkTvEventInfoBase mtkTvEventInfoBase = new MtkTvEventInfoBase();
        TVNativeWrapper.getEventByIndex_native(i, i2, mtkTvEventInfoBase);
        StringBuilder sbA = a.a("get event string");
        sbA.append(mtkTvEventInfoBase.toString());
        Log.d(TAG, sbA.toString());
        return mtkTvEventInfoBase;
    }

    public String getEventDetailByIndex(int i, int i2) {
        String eventDetailByIndex_native = TVNativeWrapper.getEventDetailByIndex_native(i, i2);
        a.c("event detail", eventDetailByIndex_native, TAG);
        return eventDetailByIndex_native;
    }

    public int getEventNumberInEIT(int i) {
        Log.d(TAG, "getEventNumberInEIT " + i + " \n");
        return TVNativeWrapper.getEventNumberInEIT_native(i);
    }

    public int getEventRatingMapByIndex(int i, int i2, MtkTvRatingConvert2Goo mtkTvRatingConvert2Goo) {
        return TVNativeWrapper.getEventRatingMapByIndex_native(i, i2, mtkTvRatingConvert2Goo);
    }

    public int loadEventByEIT(int i, int i2) {
        Log.d(TAG, a.a("loadEvents ", i, " ", i2, " \n"));
        if (i2 < 0 || i2 > 127) {
            a.a("loadEvents ", i2, " wrong,only EIT0-EIT127 is support\n", TAG);
        }
        return TVNativeWrapper.loadEventByEIT_native(i, i2);
    }

    public int loadEvents(int i, long j, int i2, int[] iArr) {
        return -1;
    }

    public int[] loadEvents(int i, long j, int i2) {
        Log.d(TAG, "loadEvents\n");
        return TVNativeWrapper.loadEvents_native(i, j, i2);
    }

    public boolean checkEventBlock(int i, int i2) {
        Log.d(TAG, "checkEventBlockby channel id\n");
        return TVNativeWrapper.checkAtscEventBlock_native(i, i2);
    }
}
