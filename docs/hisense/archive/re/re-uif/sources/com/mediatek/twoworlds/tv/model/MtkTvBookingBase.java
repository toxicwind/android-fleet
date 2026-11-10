package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvBookingBase {
    public static final String TAG = "MtkTvBookingBase";
    private int mBookingId;
    private int mChannelId;
    private int mDevIndex;
    private int mEventId;
    private String mEventTitle;
    private int mGenre;
    private int mInfoData;
    private int mRecordDelay;
    private long mRecordDuration;
    private long mRecordStartTime;
    private int mRmdMode;
    private int mRptMode;
    private int mRsltMode;
    private int mSrcType;
    private int mTunerType;
    private int mType;
    private String mUrlInfo;

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

    public String getEventTitle() {
        return this.mEventTitle;
    }

    public void setEventTitle(String str) {
        this.mEventTitle = str;
    }

    public int getChannelId() {
        return this.mChannelId;
    }

    public void setChannelId(int i) {
        this.mChannelId = i;
    }

    public int getDeviceIndex() {
        return this.mDevIndex;
    }

    public void setDeviceIndex(int i) {
        this.mDevIndex = i;
    }

    public int getGenre() {
        return this.mGenre;
    }

    public void setGenre(int i) {
        this.mGenre = i;
    }

    public long getRecordStartTime() {
        return this.mRecordStartTime;
    }

    public void setRecordStartTime(long j) {
        this.mRecordStartTime = j;
    }

    public long getRecordDuration() {
        return this.mRecordDuration;
    }

    public void setRecordDuration(long j) {
        this.mRecordDuration = j;
    }

    public int getRecordDelay() {
        return this.mRecordDelay;
    }

    public void setRecordDelay(int i) {
        this.mRecordDelay = i;
    }

    public int getRecordMode() {
        return this.mRmdMode;
    }

    public void setRecordMode(int i) {
        this.mRmdMode = i;
    }

    public int getRepeatMode() {
        return this.mRptMode;
    }

    public void setRepeatMode(int i) {
        this.mRptMode = i;
    }

    public int getSourceType() {
        return this.mSrcType;
    }

    public void setSourceType(int i) {
        this.mSrcType = i;
    }

    public int getTunerType() {
        return this.mTunerType;
    }

    public void setTunerType(int i) {
        this.mTunerType = i;
    }

    public int getRsltMode() {
        return this.mRsltMode;
    }

    public void setRsltMode(int i) {
        this.mRsltMode = i;
    }

    public int getInfoData() {
        return this.mInfoData;
    }

    public void setInfoData(int i) {
        this.mInfoData = i;
    }

    public int getBookingId() {
        return this.mBookingId;
    }

    public void setBookingId(int i) {
        this.mBookingId = i;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public int getEventId() {
        return this.mEventId;
    }

    public void setEventId(int i) {
        this.mEventId = i;
    }

    public String getUrlInfo() {
        return this.mUrlInfo;
    }

    public void setUrlInfo(String str) {
        this.mUrlInfo = str;
    }

    public String toString() {
        return "MtkTvBookingBase [EventTitle=" + this.mEventTitle + ", mBookingId=" + this.mBookingId + ", mChannelId=" + this.mChannelId + ", DevIndex=" + this.mDevIndex + ", Genre=" + this.mGenre + ", StartTime=" + this.mRecordStartTime + ", duration=" + this.mRecordDuration + ", Delay=" + this.mRecordDelay + ", RmdMode=" + this.mRmdMode + ", RsltMode=" + this.mRsltMode + ", InfoData=" + this.mInfoData + ", mRptMode=" + this.mRptMode + ", mSrcType=" + this.mSrcType + ", mTunerType=" + this.mTunerType + "]";
    }
}
