package com.mediatek.twoworlds.tv.model;

import com.mediatek.twoworlds.tv.common.MtkTvChCommonBase;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvAnalogChannelInfo extends MtkTvChannelInfoBase {
    private static final String TAG = "MtkTvAnalogChannelInfo";
    private int audioSys;
    private int centralFrequency;
    private int colorSys;
    private boolean noAutoFineTune;
    private int nwId;
    private int onId;
    private int scanIdx;
    private int sch_blcok_from_second;
    private int sch_block_enable;
    private int sch_block_to_second;
    private int sch_block_type;
    private int tsId;
    private int tvSys;

    public enum ANALOG_SCHEDULE_BLOCK_TYPE {
        SCHEDULE_BLOCK_NONE,
        SCHEDULE_BLOCK_DAILY,
        SCHEDULE_BLOCK_ONCE
    }

    public MtkTvAnalogChannelInfo() {
        this.svlId = MtkTvChCommonBase.getSvlIdByName(MtkTvChCommonBase.DB_ANA);
    }

    public MtkTvAnalogChannelInfo(String str) {
        this.svlId = MtkTvChCommonBase.getSvlIdByName(str);
    }

    public MtkTvAnalogChannelInfo(int i, int i2) {
        super(i, i2);
    }

    public void setSchBlkEnable(int i) {
        this.sch_block_enable = i;
    }

    public int getSchBlkEnable() {
        return this.sch_block_enable;
    }

    public int getSchBlkType() {
        return this.sch_block_type;
    }

    public void setSchBlkType(int i) {
        this.sch_block_type = i;
    }

    public int getSchBlkFromTime() {
        return this.sch_blcok_from_second;
    }

    public void setSchBlkFromTime(int i) {
        this.sch_blcok_from_second = i;
    }

    public int getSchBlkToTime() {
        return this.sch_block_to_second;
    }

    public void setSchBlkToTime(int i) {
        this.sch_block_to_second = i;
    }

    public int getTvSys() {
        return this.tvSys;
    }

    public void setTvSys(int i) {
        this.tvSys = i;
    }

    public int getAudioSys() {
        return this.audioSys;
    }

    public void setAudioSys(int i) {
        this.audioSys = i;
    }

    public int getColorSys() {
        return this.colorSys;
    }

    public void setColorSys(int i) {
        this.colorSys = i;
    }

    public boolean isNoAutoFineTune() {
        return this.noAutoFineTune;
    }

    public void setNoAutoFineTune(boolean z) {
        this.noAutoFineTune = z;
    }

    public int getScanIdx() {
        return this.scanIdx;
    }

    public void setScanIdx(int i) {
        this.scanIdx = i;
    }

    public int getCentralFreq() {
        return this.centralFrequency;
    }

    public void setCentralFreq(int i) {
        this.centralFrequency = i;
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
        return "MtkTvAnalogChannelInfo [svlId=" + this.svlId + " , svlRecId=" + this.svlRecId + " , channelId=" + this.channelId + " , brdcstType=" + BrdcstTypeToString(this.brdcstType) + " , nwMask=" + this.nwMask + " , optionMask=" + this.optionMask + " , serviceType=" + this.serviceType + " , channelNumber=" + this.channelNumber + " , serviceName=" + this.serviceName + " , privateData=" + Arrays.toString(this.privateData) + " , customData=" + Arrays.toString(this.customData) + " , frequency=" + this.frequency + " , brdcstMedium=" + this.brdcstMedium + " , tvSys=" + this.tvSys + " , audioSys=" + this.audioSys + " , colorSys=" + this.colorSys + " , frequency=" + getFrequency() + " , noAutoFineTune=" + this.noAutoFineTune + " , brdcstMedium=" + getBrdcstMedium() + " , centralFreq=" + getCentralFreq() + "]\n";
    }
}
