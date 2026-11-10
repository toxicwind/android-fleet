package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvChCommonBase;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvAnalogChannelInfo extends MtkTvChannelInfoBase {
    public static final String TAG = "MtkTvAnalogChannelInfo";
    public int audioSys;
    public int centralFrequency;
    public int colorSys;
    public boolean noAutoFineTune;
    public int nwId;
    public int onId;
    public int scanIdx;
    public int sch_blcok_from_second;
    public int sch_block_enable;
    public int sch_block_to_second;
    public int sch_block_type;
    public int tsId;
    public int tvSys;

    public enum ANALOG_SCHEDULE_BLOCK_TYPE {
        SCHEDULE_BLOCK_NONE,
        SCHEDULE_BLOCK_DAILY,
        SCHEDULE_BLOCK_ONCE
    }

    public MtkTvAnalogChannelInfo() {
        this.svlId = MtkTvChCommonBase.getSvlIdByName(MtkTvChCommonBase.DB_ANA);
    }

    public int getAudioSys() {
        return this.audioSys;
    }

    public int getCentralFreq() {
        return this.centralFrequency;
    }

    public int getColorSys() {
        return this.colorSys;
    }

    public int getNwId() {
        return this.nwId;
    }

    public int getOnId() {
        return this.onId;
    }

    public int getScanIdx() {
        return this.scanIdx;
    }

    public int getSchBlkEnable() {
        return this.sch_block_enable;
    }

    public int getSchBlkFromTime() {
        return this.sch_blcok_from_second;
    }

    public int getSchBlkToTime() {
        return this.sch_block_to_second;
    }

    public int getSchBlkType() {
        return this.sch_block_type;
    }

    public int getTsId() {
        return this.tsId;
    }

    public int getTvSys() {
        return this.tvSys;
    }

    public boolean isNoAutoFineTune() {
        return this.noAutoFineTune;
    }

    public void setAudioSys(int i) {
        this.audioSys = i;
    }

    public void setCentralFreq(int i) {
        this.centralFrequency = i;
    }

    public void setColorSys(int i) {
        this.colorSys = i;
    }

    public void setNoAutoFineTune(boolean z) {
        this.noAutoFineTune = z;
    }

    public void setNwId(int i) {
        this.nwId = i;
    }

    public void setOnId(int i) {
        this.onId = i;
    }

    public void setScanIdx(int i) {
        this.scanIdx = i;
    }

    public void setSchBlkEnable(int i) {
        this.sch_block_enable = i;
    }

    public void setSchBlkFromTime(int i) {
        this.sch_blcok_from_second = i;
    }

    public void setSchBlkToTime(int i) {
        this.sch_block_to_second = i;
    }

    public void setSchBlkType(int i) {
        this.sch_block_type = i;
    }

    public void setTsId(int i) {
        this.tsId = i;
    }

    public void setTvSys(int i) {
        this.tvSys = i;
    }

    @Override // com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase
    public String toString() {
        StringBuilder sbA = a.a("MtkTvAnalogChannelInfo [svlId=");
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
        sbA.append(" , tvSys=");
        sbA.append(this.tvSys);
        sbA.append(" , audioSys=");
        sbA.append(this.audioSys);
        sbA.append(" , colorSys=");
        sbA.append(this.colorSys);
        sbA.append(" , frequency=");
        sbA.append(getFrequency());
        sbA.append(" , noAutoFineTune=");
        sbA.append(this.noAutoFineTune);
        sbA.append(" , brdcstMedium=");
        sbA.append(getBrdcstMedium());
        sbA.append(" , centralFreq=");
        sbA.append(getCentralFreq());
        sbA.append("]\n");
        return sbA.toString();
    }

    public MtkTvAnalogChannelInfo(String str) {
        this.svlId = MtkTvChCommonBase.getSvlIdByName(str);
    }

    public MtkTvAnalogChannelInfo(int i, int i2) {
        super(i, i2);
    }
}
