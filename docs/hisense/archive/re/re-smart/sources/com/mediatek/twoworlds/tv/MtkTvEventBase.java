package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvExceptionBase;
import com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvEventInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvEventBase {
    public static final String TAG = "MtkTvEvent ";
    public int mMaxEventNumPerChannel;

    public MtkTvEventBase() {
        this.mMaxEventNumPerChannel = 64;
        Log.d(TAG, "Enter MtkTvEvent Here.");
        this.mMaxEventNumPerChannel = 64;
    }

    public boolean checkEventBlock(int i, int i2) {
        Log.d(TAG, "Enter getEventListByChannelId Here. channelID =" + i + "eventId =" + i2);
        return TVNativeWrapper.checkEventBlock_native(i, i2);
    }

    public int clearActiveWindows() {
        Log.d(TAG, "Enter clearActiveWindows Here.");
        return TVNativeWrapper.clearActiveWindow_native();
    }

    public int[] getCurrentActiveWinChannelList() {
        Log.d(TAG, "get active window channel list");
        return TVNativeWrapper.getCurrentActiveWinChannelList_native();
    }

    public long getCurrentActiveWinEndTime() {
        Log.d(TAG, "get active window end time");
        return TVNativeWrapper.getCurrentActiveWinEndTime_native();
    }

    public long getCurrentActiveWinStartTime() {
        Log.d(TAG, "get active window start time");
        return TVNativeWrapper.getCurrentActiveWinStartTime_native();
    }

    public MtkTvEventInfoBase getEventInfoByEventId(int i, int i2) {
        Log.d(TAG, "Enter getEventInfoByEventId Here.");
        if (i < 0) {
            return null;
        }
        MtkTvEventInfoBase mtkTvEventInfoBase = new MtkTvEventInfoBase();
        TVNativeWrapper.getEventInfoByEventId_native(i, i2, mtkTvEventInfoBase);
        return mtkTvEventInfoBase;
    }

    public List<MtkTvEventInfoBase> getEventListByChannelId(int i, long j, long j2) {
        ArrayList arrayList;
        a.b("Enter getEventListByChannelId Here. channelID =", i, TAG);
        if (j <= 0 || j2 <= 0) {
            Log.d(TAG, "getEventListByChannelId  the query time is not correct");
            return null;
        }
        try {
            synchronized (this) {
                arrayList = new ArrayList();
                int eventListByChannelId_native = TVNativeWrapper.getEventListByChannelId_native(i, j, j2, this.mMaxEventNumPerChannel, arrayList);
                Log.d(TAG, "TVNativeWrapper : getEventList_native  ret = " + eventListByChannelId_native + "  by ChannelID " + i);
                if (eventListByChannelId_native != 0) {
                    throw new MtkTvExceptionBase(eventListByChannelId_native, "TVNativeWrapper getEventList_native fail");
                }
            }
            return arrayList;
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public int getEventRatingMapById(int i, int i2, MtkTvRatingConvert2Goo mtkTvRatingConvert2Goo) {
        return TVNativeWrapper.getEventRatingMapById_native(i, i2, mtkTvRatingConvert2Goo);
    }

    public MtkTvEventInfoBase getPFEventInfoByChannel(int i, boolean z) {
        Log.d(TAG, "Enter getEventInfoByEventId Here.");
        MtkTvEventInfoBase mtkTvEventInfoBase = new MtkTvEventInfoBase();
        if (TVNativeWrapper.getPFEventInfoByChannel_native(i, z, mtkTvEventInfoBase) == 0) {
            return mtkTvEventInfoBase;
        }
        return null;
    }

    public int setCurrentActiveWindows(List<MtkTvChannelInfoBase> list, long j) {
        Log.d(TAG, "Enter setCurrentActiveWindows Here.");
        if (list == null || j <= 0) {
            return -1;
        }
        TVNativeWrapper.setCurrentActiveWindowsInfo_native(list, j);
        return 0;
    }

    public int setMaxEventNum(int i) {
        a.b("Enter setMaxEventNum Here. num = ", i, TAG);
        if (i < 0 || i > 1023) {
            return -1;
        }
        this.mMaxEventNumPerChannel = i;
        return 0;
    }

    public int setCurrentActiveWindows(List<MtkTvChannelInfoBase> list, long j, long j2) {
        Log.d(TAG, "Enter setCurrentActiveWindowsEx Here.");
        if (list == null || j <= 0 || j2 <= 0) {
            return -1;
        }
        TVNativeWrapper.setCurrentActiveWindowsInfoEx_native(list, j, j2);
        return 0;
    }
}
