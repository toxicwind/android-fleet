package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvBookingBase {
    public static final String TAG = "MtkTvBookingBase";
    public int mBookingId;
    public int mChannelId;
    public int mDevIndex;
    public int mEventId;
    public String mEventTitle;
    public int mGenre;
    public int mInfoData;
    public int mRecordDelay;
    public long mRecordDuration;
    public long mRecordStartTime;
    public int mRmdMode;
    public int mRptMode;
    public int mRsltMode;
    public int mSrcType;
    public int mTunerType;
    public int mType;
    public String mUrlInfo;

    public MtkTvBookingBase() {
        Log.d(TAG, "Enter MtkTvBookingBase struct Here.");
        this.mEventTitle = "";
        this.mBookingId = 0;
        this.mChannelId = 0;
        this.mDevIndex = 0;
        this.mGenre = 0;
        this.mRecordStartTime = 0L;
        this.mRecordDuration = 0L;
        this.mRecordDelay = 0;
        this.mRmdMode = 0;
        this.mRsltMode = 0;
        this.mInfoData = 0;
        this.mRptMode = 0;
        this.mSrcType = 0;
        this.mTunerType = 0;
        this.mType = 0;
        this.mEventId = 0;
        this.mUrlInfo = "";
    }

    public int getBookingId() {
        return this.mBookingId;
    }

    public int getChannelId() {
        return this.mChannelId;
    }

    public int getDeviceIndex() {
        return this.mDevIndex;
    }

    public int getEventId() {
        return this.mEventId;
    }

    public String getEventTitle() {
        return this.mEventTitle;
    }

    public int getGenre() {
        return this.mGenre;
    }

    public int getInfoData() {
        return this.mInfoData;
    }

    public int getRecordDelay() {
        return this.mRecordDelay;
    }

    public long getRecordDuration() {
        return this.mRecordDuration;
    }

    public int getRecordMode() {
        return this.mRmdMode;
    }

    public long getRecordStartTime() {
        return this.mRecordStartTime;
    }

    public int getRepeatMode() {
        return this.mRptMode;
    }

    public int getRsltMode() {
        return this.mRsltMode;
    }

    public int getSourceType() {
        return this.mSrcType;
    }

    public int getTunerType() {
        return this.mTunerType;
    }

    public int getType() {
        return this.mType;
    }

    public String getUrlInfo() {
        return this.mUrlInfo;
    }

    public void setBookingId(int i) {
        this.mBookingId = i;
    }

    public void setChannelId(int i) {
        this.mChannelId = i;
    }

    public void setDeviceIndex(int i) {
        this.mDevIndex = i;
    }

    public void setEventId(int i) {
        this.mEventId = i;
    }

    public void setEventTitle(String str) {
        this.mEventTitle = str;
    }

    public void setGenre(int i) {
        this.mGenre = i;
    }

    public void setInfoData(int i) {
        this.mInfoData = i;
    }

    public void setRecordDelay(int i) {
        this.mRecordDelay = i;
    }

    public void setRecordDuration(long j) {
        this.mRecordDuration = j;
    }

    public void setRecordMode(int i) {
        this.mRmdMode = i;
    }

    public void setRecordStartTime(long j) {
        this.mRecordStartTime = j;
    }

    public void setRepeatMode(int i) {
        this.mRptMode = i;
    }

    public void setRsltMode(int i) {
        this.mRsltMode = i;
    }

    public void setSourceType(int i) {
        this.mSrcType = i;
    }

    public void setTunerType(int i) {
        this.mTunerType = i;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public void setUrlInfo(String str) {
        this.mUrlInfo = str;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvBookingBase [EventTitle=");
        sbA.append(this.mEventTitle);
        sbA.append(", mBookingId=");
        sbA.append(this.mBookingId);
        sbA.append(", mChannelId=");
        sbA.append(this.mChannelId);
        sbA.append(", DevIndex=");
        sbA.append(this.mDevIndex);
        sbA.append(", Genre=");
        sbA.append(this.mGenre);
        sbA.append(", StartTime=");
        sbA.append(this.mRecordStartTime);
        sbA.append(", duration=");
        sbA.append(this.mRecordDuration);
        sbA.append(", Delay=");
        sbA.append(this.mRecordDelay);
        sbA.append(", RmdMode=");
        sbA.append(this.mRmdMode);
        sbA.append(", RsltMode=");
        sbA.append(this.mRsltMode);
        sbA.append(", InfoData=");
        sbA.append(this.mInfoData);
        sbA.append(", mRptMode=");
        sbA.append(this.mRptMode);
        sbA.append(", mSrcType=");
        sbA.append(this.mSrcType);
        sbA.append(", mTunerType=");
        return a.a(sbA, this.mTunerType, "]");
    }
}
