package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;

/* JADX INFO: loaded from: classes2.dex */
public class TvProviderChannelInfoBase {
    public static final String TAG = "TvProviderChannelInfo";
    public int mBrowsableMask;
    public int mChannelId;
    public String mChannelNumber;
    public String mDisplayName;
    public int mDisplayNumber;
    public int mGoogleType;
    public int mHashcode;
    public int mLockedMask;
    public int mMajorNumber;
    public int mMinorNumber;
    public int mNetworkMask;
    public int mOption2Mask;
    public int mOptionMask;
    public int mOriginalNetworkId;
    public int mProgramNumber;
    public int mSdtServiceType;
    public int mServiceType;
    public int mSvlId;
    public int mSvlRecId;
    public int mTransportStreamId;
    public int mType;

    public int getBrowsableMask() {
        return this.mBrowsableMask;
    }

    public int getChannelId() {
        return this.mChannelId;
    }

    public String getChannelNumber() {
        int i = this.mType;
        if (i == 0 || 3 == i) {
            if ((this.mChannelId & 128) == 0) {
                this.mChannelNumber = Integer.toString(this.mMajorNumber) + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + Integer.toString(this.mMinorNumber);
            } else {
                this.mChannelNumber = Integer.toString(this.mMajorNumber);
            }
        } else if (5 != i) {
            this.mChannelNumber = Integer.toString(this.mDisplayNumber);
        } else if ((this.mChannelId & 128) == 0) {
            this.mChannelNumber = Integer.toString(this.mMajorNumber) + "." + Integer.toString(this.mMinorNumber);
        } else {
            this.mChannelNumber = Integer.toString(this.mMajorNumber);
        }
        return this.mChannelNumber;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public int getDisplayNumber() {
        return this.mDisplayNumber;
    }

    public int getGoogBrdcstType() {
        return this.mGoogleType;
    }

    public int getHashcode() {
        return this.mHashcode;
    }

    public int getLockedMask() {
        return this.mLockedMask;
    }

    public int getMajorNumber() {
        return this.mMajorNumber;
    }

    public int getMinorNumber() {
        return this.mMinorNumber;
    }

    public int getNetworkMask() {
        return this.mNetworkMask;
    }

    public int getOption2Mask() {
        return this.mOption2Mask;
    }

    public int getOptionMask() {
        return this.mOptionMask;
    }

    public int getOriginalNetworkId() {
        return this.mOriginalNetworkId;
    }

    public int getProgramNumber() {
        return this.mProgramNumber;
    }

    public int getSdtServiceType() {
        return this.mSdtServiceType;
    }

    public int getServiceType() {
        return this.mServiceType;
    }

    public int getSvlId() {
        return this.mSvlId;
    }

    public int getSvlRecId() {
        return this.mSvlRecId;
    }

    public int getTransportStreamId() {
        return this.mTransportStreamId;
    }

    public int getbroadcastType() {
        return this.mType;
    }

    public void setBrowsableMask(int i) {
        this.mBrowsableMask = i;
    }

    public void setChannelId(int i) {
        this.mChannelId = i;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public void setDisplayNumber(int i) {
        this.mDisplayNumber = i;
    }

    public void setGoogBrdcstType(int i) {
        this.mGoogleType = i;
    }

    public void setHashcode(int i) {
        this.mHashcode = i;
    }

    public void setLockedMask(int i) {
        this.mLockedMask = i;
    }

    public void setMajorNumber(int i) {
        this.mMajorNumber = i;
    }

    public void setMinorNumber(int i) {
        this.mMinorNumber = i;
    }

    public void setNetworkMask(int i) {
        this.mNetworkMask = i;
    }

    public void setOption2Mask(int i) {
        this.mOption2Mask = i;
    }

    public void setOptionMask(int i) {
        this.mOptionMask = i;
    }

    public void setOriginalNetworkId(int i) {
        this.mOriginalNetworkId = i;
    }

    public void setProgramNumber(int i) {
        this.mProgramNumber = i;
    }

    public void setSdtServiceType(int i) {
        this.mSdtServiceType = i;
    }

    public void setServiceType(int i) {
        this.mServiceType = i;
    }

    public void setSvlId(int i) {
        this.mSvlId = i;
    }

    public void setSvlRecId(int i) {
        this.mSvlRecId = i;
    }

    public void setTransportStreamId(int i) {
        this.mTransportStreamId = i;
    }

    public void setbroadcastType(int i) {
        this.mType = i;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvChannelInfo info mSvlId=");
        sbA.append(this.mSvlId);
        sbA.append(" , mSvlRecId=");
        sbA.append(this.mSvlRecId);
        sbA.append(" , mHashcode=");
        sbA.append(this.mHashcode);
        sbA.append(" , mType=");
        sbA.append(this.mType);
        sbA.append(" , mOriginalNetworkId=");
        sbA.append(this.mOriginalNetworkId);
        sbA.append(" , mTransportStreamId=");
        sbA.append(this.mTransportStreamId);
        sbA.append(" , mProgramNumber=");
        sbA.append(this.mProgramNumber);
        sbA.append(" , mDisplayNumber=");
        sbA.append(this.mDisplayNumber);
        sbA.append(" , mDisplayName=");
        sbA.append(this.mDisplayName);
        sbA.append(" , mChannelNumber=");
        sbA.append(this.mChannelNumber);
        sbA.append(" , mChannelId=");
        sbA.append(this.mChannelId);
        sbA.append(" , mGoogleType=");
        sbA.append(this.mGoogleType);
        sbA.append(" , mBrowsableMask=");
        sbA.append(this.mBrowsableMask);
        sbA.append(" , mLockedMask=");
        return a.a(sbA, this.mLockedMask, "]\n");
    }
}
