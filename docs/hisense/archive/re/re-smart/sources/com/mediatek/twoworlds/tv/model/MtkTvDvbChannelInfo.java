package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvChCommonBase;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvDvbChannelInfo extends MtkTvChannelInfoBase {
    public static final String TAG = "MtkTvDvbChannelInfo";
    public int bandWidth;
    public int barkerMask;
    public int categoryMask;
    public int hbbtvStatus;
    public int mfType;
    public int mod;
    public int nwId;
    public String nwName;
    public int nwRecId;
    public int onId;
    public int pol;
    public int progId;
    public int satRecId;
    public int sdtServiceType;
    public String shortName;
    public String svcProName;
    public int symRate;
    public int tsId;

    public MtkTvDvbChannelInfo() {
        this.shortName = "";
        this.nwName = "";
        this.svcProName = "";
    }

    public int getBandWidth() {
        return this.bandWidth;
    }

    public int getBarkerMask() {
        return this.barkerMask;
    }

    public int getCategoryMask() {
        return this.categoryMask;
    }

    public int getHbbtvStatus() {
        return this.hbbtvStatus;
    }

    public int getMfType() {
        return this.mfType;
    }

    public int getMod() {
        return this.mod;
    }

    public int getNwId() {
        return this.nwId;
    }

    public String getNwName() {
        return this.nwName;
    }

    public int getNwRecId() {
        return this.nwRecId;
    }

    public int getOnId() {
        return this.onId;
    }

    public int getPol() {
        return this.pol;
    }

    public int getProgId() {
        return this.progId;
    }

    public int getSatRecId() {
        return this.satRecId;
    }

    public int getSdtServiceType() {
        return this.sdtServiceType;
    }

    public String getShortName() {
        return this.shortName;
    }

    public String getSvcProName() {
        return this.svcProName;
    }

    public int getSymRate() {
        return this.symRate;
    }

    public int getTsId() {
        return this.tsId;
    }

    public void setBandWidth(int i) {
        this.bandWidth = i;
    }

    public void setBarkerMask(int i) {
        this.barkerMask = i;
    }

    public void setCategoryMask(int i) {
        this.categoryMask = i;
    }

    public void setHbbtvStatus(int i) {
        this.hbbtvStatus = i;
    }

    public void setMfType(int i) {
        this.mfType = i;
    }

    public void setMod(int i) {
        this.mod = i;
    }

    public void setNwId(int i) {
        this.nwId = i;
    }

    public void setNwName(String str) {
        this.nwName = str;
    }

    public void setNwRecId(int i) {
        this.nwRecId = i;
    }

    public void setOnId(int i) {
        this.onId = i;
    }

    public void setPol(int i) {
        this.pol = i;
    }

    public void setProgId(int i) {
        this.progId = i;
    }

    public void setSatRecId(int i) {
        this.satRecId = i;
    }

    public void setSdtServiceType(int i) {
        this.sdtServiceType = i;
    }

    public void setShortName(String str) {
        this.shortName = str;
    }

    public void setSvcProName(String str) {
        this.svcProName = str;
    }

    public void setSymRate(int i) {
        this.symRate = i;
    }

    public void setTsId(int i) {
        this.tsId = i;
    }

    @Override // com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase
    public String toString() {
        StringBuilder sbA = a.a("MtkTvDVBChannelInfo    [svlId=");
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
        sbA.append(" , shortName=");
        sbA.append(this.shortName);
        sbA.append(" , bandWidth=");
        sbA.append(this.bandWidth);
        sbA.append(" , nwId=");
        sbA.append(this.nwId);
        sbA.append(" , tsId=");
        sbA.append(this.tsId);
        sbA.append(" , onId=");
        sbA.append(this.onId);
        sbA.append(" , progId=");
        sbA.append(this.progId);
        sbA.append(" , symRate=");
        sbA.append(this.symRate);
        sbA.append(" , mod=");
        sbA.append(this.mod);
        sbA.append(" , satRecId=");
        sbA.append(this.satRecId);
        sbA.append(" , nwName=");
        sbA.append(this.nwName);
        sbA.append(" , barkerMask=");
        sbA.append(this.barkerMask);
        sbA.append(" , sdtServiceType=");
        sbA.append(this.sdtServiceType);
        sbA.append(" , mfType=");
        sbA.append(this.mfType);
        sbA.append(" , categoryMask=");
        return a.a(sbA, this.categoryMask, "]\n");
    }

    public MtkTvDvbChannelInfo(String str) {
        this.shortName = "";
        this.nwName = "";
        this.svcProName = "";
        this.svlId = MtkTvChCommonBase.getSvlIdByName(str);
    }

    public MtkTvDvbChannelInfo(int i, int i2) {
        super(i, i2);
        this.shortName = "";
        this.nwName = "";
        this.svcProName = "";
    }
}
