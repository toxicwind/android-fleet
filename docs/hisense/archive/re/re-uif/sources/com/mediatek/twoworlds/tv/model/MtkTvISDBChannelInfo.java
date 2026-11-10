package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvChCommonBase;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvISDBChannelInfo extends MtkTvChannelInfoBase {
    private static final String TAG = "MtkTvISDBChannelInfo";
    private int channelIdx;
    private int majorNumber;
    private int minorNumber;
    private int nwId;
    private int onId;
    private int progId;
    private int rfIdx;
    private int sch_blcok_from_second;
    private int sch_block_enable;
    private int sch_block_to_second;
    private int sch_block_type;
    private int tsID;
    private String tsName;

    public enum ISDB_SCHEDULE_BLOCK_TYPE {
        SCHEDULE_BLOCK_NONE,
        SCHEDULE_BLOCK_DAILY,
        SCHEDULE_BLOCK_ONCE
    }

    private void isdbChannelInfosetDefault() {
        this.rfIdx = 0;
        this.onId = 0;
        this.tsID = 0;
        this.majorNumber = 0;
        this.minorNumber = 0;
        this.channelIdx = 0;
        this.progId = 0;
    }

    public MtkTvISDBChannelInfo() {
        isdbChannelInfosetDefault();
    }

    public MtkTvISDBChannelInfo(String str) {
        isdbChannelInfosetDefault();
        this.svlId = MtkTvChCommonBase.getSvlIdByName(str);
    }

    public MtkTvISDBChannelInfo(int i, int i2) {
        super(i, i2);
        isdbChannelInfosetDefault();
        Log.d(TAG, "MtkTvISDBChannelInfo Constructor(svlid = " + i + ", svlRecId = " + i2 + ")\n");
    }

    public void setSchBlkEnable(int i) {
        Log.d(TAG, "Digital setSchBlkEnable = " + i + ")\n");
        this.sch_block_enable = i;
    }

    public int getSchBlkEnable() {
        Log.d(TAG, "Digital getSchBlkEnable = " + this.sch_block_enable + ")\n");
        return this.sch_block_enable;
    }

    public int getSchBlkType() {
        Log.d(TAG, "Digital getSchBlkType = " + this.sch_block_type + ")\n");
        return this.sch_block_type;
    }

    public void setSchBlkType(int i) {
        Log.d(TAG, "Digital setSchBlkType = " + i + ")\n");
        this.sch_block_type = i;
    }

    public int getSchBlkFromTime() {
        return this.sch_blcok_from_second;
    }

    public void setSchBlkFromTime(int i) {
        this.sch_blcok_from_second = i;
        Log.d(TAG, "Digital setSchBlkFromTime = " + i + ")\n");
    }

    public int getSchBlkToTime() {
        return this.sch_block_to_second;
    }

    public void setSchBlkToTime(int i) {
        this.sch_block_to_second = i;
        Log.d(TAG, "Digital setSchBlkToTime = " + i + ")\n");
    }

    public int getMajorNum() {
        return this.majorNumber;
    }

    public void _setMajorNum(int i) {
        this.majorNumber = i;
    }

    public void setMajorNum(int i) {
        _setMajorNum(i);
        setChannelNumberEdited(true);
    }

    public int getMinorNum() {
        return this.minorNumber;
    }

    public void _setMinorNum(int i) {
        this.minorNumber = i;
    }

    public void setMinorNum(int i) {
        _setMinorNum(i);
        setChannelNumberEdited(true);
    }

    private int getChIdx() {
        return this.channelIdx;
    }

    private void setChIdx(int i) {
        this.channelIdx = i;
    }

    public int getProgId() {
        return this.progId;
    }

    public void setProgId(int i) {
        this.progId = i;
    }

    public int getRfIdx() {
        return this.rfIdx;
    }

    public void setRfIdx(int i) {
        this.rfIdx = i;
    }

    public int getOnID() {
        return this.onId;
    }

    public void setOnId(int i) {
        this.onId = i;
    }

    public int getTsID() {
        return this.tsID;
    }

    public void setTsId(int i) {
        this.tsID = i;
    }

    public int getNwId() {
        return this.nwId;
    }

    public void setNwId(int i) {
        this.nwId = i;
    }

    public String getTsName() {
        return this.tsName;
    }

    public void setTsName(String str) {
        this.tsName = str;
    }

    @Override // com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase
    public void _setChannelNumberByChannelID(int i) {
        this.majorNumber = MtkTvChCommonBase.SB_ISDB_GET_MAJOR_NUMBER(i);
        this.minorNumber = MtkTvChCommonBase.SB_ISDB_GET_MINOR_NUMBER(i);
        this.channelIdx = MtkTvChCommonBase.SB_ISDB_GET_CHANNEL_INDEX(i);
    }

    @Override // com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase
    public String toString() {
        return "MtkTvISDBChannelInfo   [svlId=" + this.svlId + " , svlRecId=" + this.svlRecId + " , channelId=" + this.channelId + " , brdcstType=" + BrdcstTypeToString(this.brdcstType) + " , nwMask=" + this.nwMask + " , optionMask=" + this.optionMask + " , serviceType=" + this.serviceType + " , channelNumber=" + this.channelNumber + " , serviceName=" + this.serviceName + " , privateData=" + Arrays.toString(this.privateData) + " , customData=" + Arrays.toString(this.customData) + " , frequency=" + this.frequency + " , brdcstMedium=" + this.brdcstMedium + " , rfIdx=" + this.rfIdx + " , onId=" + this.onId + " , tsId=" + this.tsID + " , majorNumber=" + this.majorNumber + " , minorNumber=" + this.minorNumber + " , channelIdx=" + this.channelIdx + " , progId=" + this.progId + " , sch_block_enable=" + this.sch_block_enable + " , sch_block_type=" + this.sch_block_type + " , sch_block_from_second=" + this.sch_blcok_from_second + " , sch_block_to_second=" + this.sch_block_to_second + " , nwId=" + this.nwId + "]\n";
    }
}
