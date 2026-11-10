package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.model.MtkTvPvrBrowserItemBase;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvPvrBrowserBase {
    private static final String TAG = "MtkTvPvrBrowser ";

    public MtkTvPvrBrowserBase() {
        Log.d(TAG, "Enter MtkTvPvrBrowser struct Here.");
    }

    public MtkTvPvrBrowserItemBase getPvrBrowserItemByPath(String str) {
        Log.d(TAG, "Enter getPvrBrowserItemByPath Here.");
        MtkTvPvrBrowserItemBase mtkTvPvrBrowserItemBase = new MtkTvPvrBrowserItemBase();
        mtkTvPvrBrowserItemBase.mPath = str;
        mtkTvPvrBrowserItemBase.mChannelId = TVNativeWrapper.getPvrBrowserItemChannelId_native(str);
        mtkTvPvrBrowserItemBase.mChannelName = TVNativeWrapper.getPvrBrowserItemChannelName_native(str);
        mtkTvPvrBrowserItemBase.mDuration = TVNativeWrapper.getPvrBrowserItemDuration_native(str);
        mtkTvPvrBrowserItemBase.mProgramName = TVNativeWrapper.getPvrBrowserItemProgramName_native(str);
        long pvrBrowserItemStartTime_native = TVNativeWrapper.getPvrBrowserItemStartTime_native(str);
        mtkTvPvrBrowserItemBase.mStartTime = pvrBrowserItemStartTime_native;
        mtkTvPvrBrowserItemBase.mEndTime = pvrBrowserItemStartTime_native + mtkTvPvrBrowserItemBase.mDuration;
        mtkTvPvrBrowserItemBase.mMajorChannelNum = TVNativeWrapper.getPvrBrowserItemMajorChannelNum_native(str);
        mtkTvPvrBrowserItemBase.mMinorChannelNum = TVNativeWrapper.getPvrBrowserItemMinorChannelNum_native(str);
        mtkTvPvrBrowserItemBase.mData = TVNativeWrapper.getPvrBrowserItemDate_native(str);
        mtkTvPvrBrowserItemBase.mWeek = TVNativeWrapper.getPvrBrowserItemWeek_native(str);
        mtkTvPvrBrowserItemBase.mTime = TVNativeWrapper.getPvrBrowserItemTime_native(str);
        mtkTvPvrBrowserItemBase.mRetention = TVNativeWrapper.getPvrBrowserItemRentention_native(str);
        mtkTvPvrBrowserItemBase.mFirstRatingRange = TVNativeWrapper.getPvrBrowserItemFirstRatingRange_native(str);
        mtkTvPvrBrowserItemBase.mStopReason = TVNativeWrapper.getPvrBrowserItemStopReason_native(str);
        mtkTvPvrBrowserItemBase.mChannelLock = TVNativeWrapper.getPvrBrowserItemChannelLock_native(str);
        mtkTvPvrBrowserItemBase.mInputLock = TVNativeWrapper.getPvrBrowserItemInputLock_native(str);
        return mtkTvPvrBrowserItemBase;
    }

    public int getPvrBrowserItemCount() {
        return TVNativeWrapper.getPvrBrowserItemCount_native();
    }

    public MtkTvPvrBrowserItemBase getPvrBrowserItemByIndex(int i) {
        Log.d(TAG, "Enter getPvrBrowserItemByIndex Here.");
        MtkTvPvrBrowserItemBase mtkTvPvrBrowserItemBase = new MtkTvPvrBrowserItemBase();
        mtkTvPvrBrowserItemBase.mPath = TVNativeWrapper.getPvrBrowserItemPath_native(i);
        mtkTvPvrBrowserItemBase.mChannelId = TVNativeWrapper.getPvrBrowserItemChannelId_native(i);
        mtkTvPvrBrowserItemBase.mMajorChannelNum = TVNativeWrapper.getPvrBrowserItemMajorChannelNum_native(i);
        mtkTvPvrBrowserItemBase.mMinorChannelNum = TVNativeWrapper.getPvrBrowserItemMinorChannelNum_native(i);
        mtkTvPvrBrowserItemBase.mChannelName = TVNativeWrapper.getPvrBrowserItemChannelName_native(i);
        mtkTvPvrBrowserItemBase.mDuration = TVNativeWrapper.getPvrBrowserItemDuration_native(i);
        mtkTvPvrBrowserItemBase.mProgramName = TVNativeWrapper.getPvrBrowserItemProgramName_native(i);
        mtkTvPvrBrowserItemBase.mData = TVNativeWrapper.getPvrBrowserItemDate_native(i);
        mtkTvPvrBrowserItemBase.mWeek = TVNativeWrapper.getPvrBrowserItemWeek_native(i);
        mtkTvPvrBrowserItemBase.mTime = TVNativeWrapper.getPvrBrowserItemTime_native(i);
        long pvrBrowserItemStartTime_native = TVNativeWrapper.getPvrBrowserItemStartTime_native(i);
        mtkTvPvrBrowserItemBase.mStartTime = pvrBrowserItemStartTime_native;
        mtkTvPvrBrowserItemBase.mEndTime = pvrBrowserItemStartTime_native + mtkTvPvrBrowserItemBase.mDuration;
        mtkTvPvrBrowserItemBase.mRetention = TVNativeWrapper.getPvrBrowserItemRentention_native(i);
        mtkTvPvrBrowserItemBase.mFirstRatingRange = TVNativeWrapper.getPvrBrowserItemFirstRatingRange_native(i);
        mtkTvPvrBrowserItemBase.mStopReason = TVNativeWrapper.getPvrBrowserItemStopReason_native(i);
        mtkTvPvrBrowserItemBase.mChannelLock = TVNativeWrapper.getPvrBrowserItemChannelLock_native(i);
        mtkTvPvrBrowserItemBase.mInputLock = TVNativeWrapper.getPvrBrowserItemInputLock_native(i);
        return mtkTvPvrBrowserItemBase;
    }

    public int getPvrBrowserRecordingFileCount() {
        return TVNativeWrapper.getPvrBrowserRecordingFileCount_native();
    }

    public String getPvrBrowserRecordingFileName(int i) {
        return TVNativeWrapper.getPvrBrowserRecordingFileName_native(i);
    }

    public int deletePvrBrowserFileByIndex(int i) {
        return TVNativeWrapper.deletePvrBrowserFileByIndex_native(i);
    }
}
