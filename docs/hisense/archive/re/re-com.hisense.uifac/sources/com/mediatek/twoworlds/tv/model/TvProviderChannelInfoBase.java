package com.mediatek.twoworlds.tv.model;

import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;

/* JADX INFO: loaded from: classes.dex */
public class TvProviderChannelInfoBase {
    private static final String TAG = "TvProviderChannelInfo";
    protected int mBrowsableMask;
    protected int mChannelId;
    protected String mChannelNumber;
    protected String mDisplayName;
    protected int mDisplayNumber;
    protected int mGoogleType;
    protected int mHashcode;
    protected int mLockedMask;
    protected int mMajorNumber;
    protected int mMinorNumber;
    protected int mNetworkMask;
    protected int mOption2Mask;
    protected int mOptionMask;
    protected int mOriginalNetworkId;
    protected int mProgramNumber;
    protected int mSdtServiceType;
    protected int mServiceType;
    protected int mSvlId;
    protected int mSvlRecId;
    protected int mTransportStreamId;
    protected int mType;

    public int getBrowsableMask() {
        return this.mBrowsableMask;
    }

    public void setBrowsableMask(int i) {
        this.mBrowsableMask = i;
    }

    public int getLockedMask() {
        return this.mLockedMask;
    }

    public void setLockedMask(int i) {
        this.mLockedMask = i;
    }

    public int getSvlId() {
        return this.mSvlId;
    }

    protected void setSvlId(int i) {
        this.mSvlId = i;
    }

    public int getSvlRecId() {
        return this.mSvlRecId;
    }

    protected void setSvlRecId(int i) {
        this.mSvlRecId = i;
    }

    public int getHashcode() {
        return this.mHashcode;
    }

    public void setHashcode(int i) {
        this.mHashcode = i;
    }

    public int getbroadcastType() {
        return this.mType;
    }

    public void setbroadcastType(int i) {
        this.mType = i;
    }

    public int getGoogBrdcstType() {
        return this.mGoogleType;
    }

    public void setGoogBrdcstType(int i) {
        this.mGoogleType = i;
    }

    public int getServiceType() {
        return this.mServiceType;
    }

    public void setServiceType(int i) {
        this.mServiceType = i;
    }

    public int getOriginalNetworkId() {
        return this.mOriginalNetworkId;
    }

    public void setOriginalNetworkId(int i) {
        this.mOriginalNetworkId = i;
    }

    public int getTransportStreamId() {
        return this.mTransportStreamId;
    }

    public void setTransportStreamId(int i) {
        this.mTransportStreamId = i;
    }

    public int getProgramNumber() {
        return this.mProgramNumber;
    }

    public void setProgramNumber(int i) {
        this.mProgramNumber = i;
    }

    public int getDisplayNumber() {
        return this.mDisplayNumber;
    }

    public void setDisplayNumber(int i) {
        this.mDisplayNumber = i;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public int getMajorNumber() {
        return this.mMajorNumber;
    }

    public void setMajorNumber(int i) {
        this.mMajorNumber = i;
    }

    public int getMinorNumber() {
        return this.mMinorNumber;
    }

    public void setMinorNumber(int i) {
        this.mMinorNumber = i;
    }

    public String getChannelNumber() {
        int i = this.mType;
        if (i == 0 || 3 == i) {
            if ((this.mChannelId & 128) == 0) {
                this.mChannelNumber = Integer.toString(this.mMajorNumber) + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + Integer.toString(this.mMinorNumber);
            } else {
                this.mChannelNumber = Integer.toString(this.mMajorNumber);
            }
        } else if (5 == i) {
            if ((this.mChannelId & 128) == 0) {
                this.mChannelNumber = Integer.toString(this.mMajorNumber) + "." + Integer.toString(this.mMinorNumber);
            } else {
                this.mChannelNumber = Integer.toString(this.mMajorNumber);
            }
        } else {
            this.mChannelNumber = Integer.toString(this.mDisplayNumber);
        }
        return this.mChannelNumber;
    }

    public int getChannelId() {
        return this.mChannelId;
    }

    public void setChannelId(int i) {
        this.mChannelId = i;
    }

    public int getNetworkMask() {
        return this.mNetworkMask;
    }

    public void setNetworkMask(int i) {
        this.mNetworkMask = i;
    }

    public int getOptionMask() {
        return this.mOptionMask;
    }

    public void setOptionMask(int i) {
        this.mOptionMask = i;
    }

    public int getOption2Mask() {
        return this.mOption2Mask;
    }

    public void setOption2Mask(int i) {
        this.mOption2Mask = i;
    }

    public int getSdtServiceType() {
        return this.mSdtServiceType;
    }

    public void setSdtServiceType(int i) {
        this.mSdtServiceType = i;
    }

    public String toString() {
        return "MtkTvChannelInfo info mSvlId=" + this.mSvlId + " , mSvlRecId=" + this.mSvlRecId + " , mHashcode=" + this.mHashcode + " , mType=" + this.mType + " , mOriginalNetworkId=" + this.mOriginalNetworkId + " , mTransportStreamId=" + this.mTransportStreamId + " , mProgramNumber=" + this.mProgramNumber + " , mDisplayNumber=" + this.mDisplayNumber + " , mDisplayName=" + this.mDisplayName + " , mChannelNumber=" + this.mChannelNumber + " , mChannelId=" + this.mChannelId + " , mGoogleType=" + this.mGoogleType + " , mBrowsableMask=" + this.mBrowsableMask + " , mLockedMask=" + this.mLockedMask + "]\n";
    }
}
