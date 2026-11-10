package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvChCommonBase;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvISDBChannelInfo extends MtkTvChannelInfoBase {
    public static final String TAG = "MtkTvISDBChannelInfo";
    public int channelIdx;
    public int majorNumber;
    public int minorNumber;
    public int nwId;
    public int onId;
    public int progId;
    public int rfIdx;
    public int sch_blcok_from_second;
    public int sch_block_enable;
    public int sch_block_to_second;
    public int sch_block_type;
    public int tsID;
    public String tsName;

    public enum ISDB_SCHEDULE_BLOCK_TYPE {
        SCHEDULE_BLOCK_NONE,
        SCHEDULE_BLOCK_DAILY,
        SCHEDULE_BLOCK_ONCE
    }

    public MtkTvISDBChannelInfo() {
        isdbChannelInfosetDefault();
    }

    private int getChIdx() {
        return this.channelIdx;
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

    private void setChIdx(int i) {
        this.channelIdx = i;
    }

    @Override // com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase
    public void _setChannelNumberByChannelID(int i) {
        this.majorNumber = MtkTvChCommonBase.SB_ISDB_GET_MAJOR_NUMBER(i);
        this.minorNumber = MtkTvChCommonBase.SB_ISDB_GET_MINOR_NUMBER(i);
        this.channelIdx = MtkTvChCommonBase.SB_ISDB_GET_CHANNEL_INDEX(i);
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

    public int getNwId() {
        return this.nwId;
    }

    public int getOnID() {
        return this.onId;
    }

    public int getProgId() {
        return this.progId;
    }

    public int getRfIdx() {
        return this.rfIdx;
    }

    public int getSchBlkEnable() {
        a.a(a.a("Digital getSchBlkEnable = "), this.sch_block_enable, ")\n", TAG);
        return this.sch_block_enable;
    }

    public int getSchBlkFromTime() {
        return this.sch_blcok_from_second;
    }

    public int getSchBlkToTime() {
        return this.sch_block_to_second;
    }

    public int getSchBlkType() {
        a.a(a.a("Digital getSchBlkType = "), this.sch_block_type, ")\n", TAG);
        return this.sch_block_type;
    }

    public int getTsID() {
        return this.tsID;
    }

    public String getTsName() {
        return this.tsName;
    }

    public void setMajorNum(int i) {
        _setMajorNum(i);
        setChannelNumberEdited(true);
    }

    public void setMinorNum(int i) {
        _setMinorNum(i);
        setChannelNumberEdited(true);
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

    public void setRfIdx(int i) {
        this.rfIdx = i;
    }

    public void setSchBlkEnable(int i) {
        a.a("Digital setSchBlkEnable = ", i, ")\n", TAG);
        this.sch_block_enable = i;
    }

    public void setSchBlkFromTime(int i) {
        this.sch_blcok_from_second = i;
        a.a("Digital setSchBlkFromTime = ", i, ")\n", TAG);
    }

    public void setSchBlkToTime(int i) {
        this.sch_block_to_second = i;
        a.a("Digital setSchBlkToTime = ", i, ")\n", TAG);
    }

    public void setSchBlkType(int i) {
        a.a("Digital setSchBlkType = ", i, ")\n", TAG);
        this.sch_block_type = i;
    }

    public void setTsId(int i) {
        this.tsID = i;
    }

    public void setTsName(String str) {
        this.tsName = str;
    }

    @Override // com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase
    public String toString() {
        StringBuilder sbA = a.a("MtkTvISDBChannelInfo   [svlId=");
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
        sbA.append(" , rfIdx=");
        sbA.append(this.rfIdx);
        sbA.append(" , onId=");
        sbA.append(this.onId);
        sbA.append(" , tsId=");
        sbA.append(this.tsID);
        sbA.append(" , majorNumber=");
        sbA.append(this.majorNumber);
        sbA.append(" , minorNumber=");
        sbA.append(this.minorNumber);
        sbA.append(" , channelIdx=");
        sbA.append(this.channelIdx);
        sbA.append(" , progId=");
        sbA.append(this.progId);
        sbA.append(" , sch_block_enable=");
        sbA.append(this.sch_block_enable);
        sbA.append(" , sch_block_type=");
        sbA.append(this.sch_block_type);
        sbA.append(" , sch_block_from_second=");
        sbA.append(this.sch_blcok_from_second);
        sbA.append(" , sch_block_to_second=");
        sbA.append(this.sch_block_to_second);
        sbA.append(" , nwId=");
        return a.a(sbA, this.nwId, "]\n");
    }

    public MtkTvISDBChannelInfo(String str) {
        isdbChannelInfosetDefault();
        this.svlId = MtkTvChCommonBase.getSvlIdByName(str);
    }

    public MtkTvISDBChannelInfo(int i, int i2) {
        super(i, i2);
        isdbChannelInfosetDefault();
        Log.d(TAG, a.a("MtkTvISDBChannelInfo Constructor(svlid = ", i, ", svlRecId = ", i2, ")\n"));
    }
}
