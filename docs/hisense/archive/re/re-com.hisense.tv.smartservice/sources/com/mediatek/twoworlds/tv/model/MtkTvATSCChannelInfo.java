package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvChCommonBase;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvATSCChannelInfo extends MtkTvChannelInfoBase {
    public static final String TAG = "MtkTvATSCChannelInfo";
    public int majorNumber;
    public int minorNumber;
    public int mod;
    public int nwId;
    public int onId;
    public int progId;
    public int scanIdx;
    public int tsId;

    public MtkTvATSCChannelInfo() {
    }

    @Override // com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase
    public void _setChannelNumberByChannelID(int i) {
        this.majorNumber = MtkTvChCommonBase.SB_ATSC_GET_MAJOR_CHANNEL_NUM(i);
        this.minorNumber = MtkTvChCommonBase.SB_ATSC_GET_MINOR_CHANNEL_NUM(i);
    }

    public void _setMajorNum(int i) {
        this.majorNumber = i;
    }

    public void _setMinorNum(int i) {
        this.minorNumber = i;
    }

    public int getMajorNum() {
        return this.majorNumber;
    }

    public int getMinorNum() {
        return this.minorNumber;
    }

    public int getMod() {
        return this.mod;
    }

    public int getNwId() {
        return this.nwId;
    }

    public int getOnId() {
        return this.onId;
    }

    public int getProgId() {
        return this.progId;
    }

    public int getScanIdx() {
        return this.scanIdx;
    }

    public int getTsId() {
        return this.tsId;
    }

    public void setMajorNum(int i) {
        _setMajorNum(i);
        setChannelNumberEdited(true);
    }

    public void setMinorNum(int i) {
        _setMinorNum(i);
        setChannelNumberEdited(true);
    }

    public void setMod(int i) {
        this.mod = i;
    }

    public void setNwId(int i) {
        this.nwId = i;
    }

    public void setOnId(int i) {
        this.onId = i;
    }

    public void setProgId(int i) {
        this.progId = i;
    }

    public void setScanIdx(int i) {
        this.scanIdx = i;
    }

    public void setTsId(int i) {
        this.tsId = i;
    }

    @Override // com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase
    public String toString() {
        StringBuilder sbA = a.a("MtkTvATSCChannelInfo   [svlId=");
        sbA.append(this.svlId);
        sbA.append(" , svlRecId=");
        sbA.append(this.svlRecId);
        sbA.append(" , channelId=");
        sbA.append(this.channelId);
        sbA.append(" , brdcstType=");
        sbA.append(BrdcstTypeToString(this.brdcstType));
        sbA.append(" , nwMask=");
        sbA.append(this.nwMask);
        sbA.append(" , optionMask=");
        sbA.append(this.optionMask);
        sbA.append(" , serviceType=");
        sbA.append(this.serviceType);
        sbA.append(" , channelNumber=");
        sbA.append(this.channelNumber);
        sbA.append(" , serviceName=");
        sbA.append(this.serviceName);
        sbA.append(" , privateData=");
        sbA.append(Arrays.toString(this.privateData));
        sbA.append(" , customData=");
        sbA.append(Arrays.toString(this.customData));
        sbA.append(" , frequency=");
        sbA.append(this.frequency);
        sbA.append(" , brdcstMedium=");
        sbA.append(this.brdcstMedium);
        sbA.append(" , majorNumber=");
        sbA.append(this.majorNumber);
        sbA.append(" , minorNumber=");
        sbA.append(this.minorNumber);
        sbA.append(" , scanIdx=");
        sbA.append(this.scanIdx);
        sbA.append(" , progId=");
        sbA.append(this.progId);
        sbA.append(" , mod=");
        sbA.append(this.mod);
        sbA.append(" , nwId=");
        sbA.append(this.nwId);
        sbA.append(" , onId=");
        sbA.append(this.onId);
        sbA.append(" , tsId=");
        return a.a(sbA, this.tsId, "]\n");
    }

    public MtkTvATSCChannelInfo(String str) {
        this.svlId = MtkTvChCommonBase.getSvlIdByName(str);
    }

    public MtkTvATSCChannelInfo(int i, int i2) {
        super(i, i2);
        Log.d(TAG, a.a("MtkTvATSCChannelInfo Constructor(svlid = ", i, ", svlRecId = ", i2, ")\n"));
    }
}
