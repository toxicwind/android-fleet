package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvPvrBrowserItemBase {
    public static final String TAG = "MtkTvPvrBrowserItemBase";
    public long mChannelId;
    public int mChannelLock;
    public String mChannelName;
    public String mData;
    public long mDuration;
    public long mEndTime;
    public long mFirstRatingRange;
    public int mInputLock;
    public int mMajorChannelNum;
    public int mMinorChannelNum;
    public String mPath;
    public String mProgramName;
    public long mRetention;
    public long mStartTime;
    public long mStopReason;
    public String mTime;
    public String mWeek;

    public MtkTvPvrBrowserItemBase() {
        Log.d(TAG, "Enter MtkTvPvrBrowserItemBase struct Here.");
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvPvrBrowserItemBase   [mPath=");
        sbA.append(this.mPath);
        sbA.append(" , mChannelId=");
        sbA.append(this.mChannelId);
        sbA.append(" , mDuration=");
        sbA.append(this.mDuration);
        sbA.append(" , mChannelName=");
        sbA.append(this.mChannelName);
        sbA.append(" , mStartTime=");
        sbA.append(this.mStartTime);
        sbA.append(" , mEndTime=");
        sbA.append(this.mEndTime);
        sbA.append(", mChannelLock=");
        sbA.append(this.mChannelLock);
        sbA.append(" , mInputLock-");
        return a.a(sbA, this.mInputLock, "]\n");
    }
}
