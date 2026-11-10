package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvChCommonBase;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvATSCChannelInfo extends MtkTvChannelInfoBase {
    private static final String TAG = "MtkTvATSCChannelInfo";
    private int majorNumber;
    private int minorNumber;
    private int mod;
    private int nwId;
    private int onId;
    private int progId;
    private int scanIdx;
    private int tsId;

    public MtkTvATSCChannelInfo() {
    }

    public MtkTvATSCChannelInfo(String str) {
        this.svlId = MtkTvChCommonBase.getSvlIdByName(str);
    }

    public MtkTvATSCChannelInfo(int i, int i2) {
        super(i, i2);
        Log.d(TAG, "MtkTvATSCChannelInfo Constructor(svlid = " + i + ", svlRecId = " + i2 + ")\n");
    }

    public void _setMajorNum(int i) {
        this.majorNumber = i;
    }

    public void setMajorNum(int i) {
        _setMajorNum(i);
        setChannelNumberEdited(true);
    }

    public int getMajorNum() {
        return this.majorNumber;
    }

    public void _setMinorNum(int i) {
        this.minorNumber = i;
    }

    public void setMinorNum(int i) {
        _setMinorNum(i);
        setChannelNumberEdited(true);
    }

    public int getMinorNum() {
        return this.minorNumber;
    }

    public void setProgId(int i) {
        this.progId = i;
    }

    public int getProgId() {
        return this.progId;
    }

    public void setScanIdx(int i) {
        this.scanIdx = i;
    }

    public int getScanIdx() {
        return this.scanIdx;
    }

    public void setMod(int i) {
        this.mod = i;
    }

    public int getMod() {
        return this.mod;
    }

    @Override // com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase
    public void _setChannelNumberByChannelID(int i) {
        this.majorNumber = MtkTvChCommonBase.SB_ATSC_GET_MAJOR_CHANNEL_NUM(i);
        this.minorNumber = MtkTvChCommonBase.SB_ATSC_GET_MINOR_CHANNEL_NUM(i);
    }

    public int getNwId() {
        return this.nwId;
    }

    public void setNwId(int i) {
        this.nwId = i;
    }

    public int getOnId() {
        return this.onId;
    }

    public void setOnId(int i) {
        this.onId = i;
    }

    public int getTsId() {
        return this.tsId;
    }

    public void setTsId(int i) {
        this.tsId = i;
    }

    @Override // com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase
    public String toString() {
        return "MtkTvATSCChannelInfo   [svlId=" + this.svlId + " , svlRecId=" + this.svlRecId + " , channelId=" + this.channelId + " , brdcstType=" + BrdcstTypeToString(this.brdcstType) + " , nwMask=" + this.nwMask + " , optionMask=" + this.optionMask + " , serviceType=" + this.serviceType + " , channelNumber=" + this.channelNumber + " , serviceName=" + this.serviceName + " , privateData=" + Arrays.toString(this.privateData) + " , customData=" + Arrays.toString(this.customData) + " , frequency=" + this.frequency + " , brdcstMedium=" + this.brdcstMedium + " , majorNumber=" + this.majorNumber + " , minorNumber=" + this.minorNumber + " , scanIdx=" + this.scanIdx + " , progId=" + this.progId + " , mod=" + this.mod + " , nwId=" + this.nwId + " , onId=" + this.onId + " , tsId=" + this.tsId + "]\n";
    }
}
