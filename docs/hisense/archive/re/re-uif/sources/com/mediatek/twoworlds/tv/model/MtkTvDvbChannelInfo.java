package com.mediatek.twoworlds.tv.model;

import com.mediatek.twoworlds.tv.common.MtkTvChCommonBase;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvDvbChannelInfo extends MtkTvChannelInfoBase {
    private static final String TAG = "MtkTvDvbChannelInfo";
    private int bandWidth;
    protected int barkerMask;
    protected int categoryMask;
    protected int hbbtvStatus;
    protected int mfType;
    private int mod;
    private int nwId;
    private String nwName;
    private int nwRecId;
    private int onId;
    private int pol;
    private int progId;
    private int satRecId;
    protected int sdtServiceType;
    private String shortName;
    protected String svcProName;
    private int symRate;
    private int tsId;

    public MtkTvDvbChannelInfo() {
        this.shortName = "";
        this.nwName = "";
        this.svcProName = "";
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

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String str) {
        this.shortName = str;
    }

    public int getBandWidth() {
        return this.bandWidth;
    }

    public void setBandWidth(int i) {
        this.bandWidth = i;
    }

    public int getNwId() {
        return this.nwId;
    }

    public void setNwId(int i) {
        this.nwId = i;
    }

    public int getNwRecId() {
        return this.nwRecId;
    }

    public void setNwRecId(int i) {
        this.nwRecId = i;
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

    public int getProgId() {
        return this.progId;
    }

    public void setProgId(int i) {
        this.progId = i;
    }

    public int getSymRate() {
        return this.symRate;
    }

    public void setSymRate(int i) {
        this.symRate = i;
    }

    public int getMod() {
        return this.mod;
    }

    public void setMod(int i) {
        this.mod = i;
    }

    public int getPol() {
        return this.pol;
    }

    public void setPol(int i) {
        this.pol = i;
    }

    public int getSatRecId() {
        return this.satRecId;
    }

    public void setSatRecId(int i) {
        this.satRecId = i;
    }

    public String getNwName() {
        return this.nwName;
    }

    public void setNwName(String str) {
        this.nwName = str;
    }

    public int getBarkerMask() {
        return this.barkerMask;
    }

    public void setBarkerMask(int i) {
        this.barkerMask = i;
    }

    public int getSdtServiceType() {
        return this.sdtServiceType;
    }

    public void setSdtServiceType(int i) {
        this.sdtServiceType = i;
    }

    public int getHbbtvStatus() {
        return this.hbbtvStatus;
    }

    public void setHbbtvStatus(int i) {
        this.hbbtvStatus = i;
    }

    public String getSvcProName() {
        return this.svcProName;
    }

    public void setSvcProName(String str) {
        this.svcProName = str;
    }

    public int getMfType() {
        return this.mfType;
    }

    public void setMfType(int i) {
        this.mfType = i;
    }

    public int getCategoryMask() {
        return this.categoryMask;
    }

    public void setCategoryMask(int i) {
        this.categoryMask = i;
    }

    @Override // com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase
    public String toString() {
        return "MtkTvDVBChannelInfo    [svlId=" + this.svlId + " , svlRecId=" + this.svlRecId + " , channelId=" + this.channelId + " , brdcstType=" + BrdcstTypeToString(this.brdcstType) + " , nwMask=" + this.nwMask + " , optionMask=" + this.optionMask + " , serviceType=" + this.serviceType + " , channelNumber=" + this.channelNumber + " , serviceName=" + this.serviceName + " , privateData=" + Arrays.toString(this.privateData) + " , customData=" + Arrays.toString(this.customData) + " , frequency=" + this.frequency + " , brdcstMedium=" + this.brdcstMedium + " , shortName=" + this.shortName + " , bandWidth=" + this.bandWidth + " , nwId=" + this.nwId + " , tsId=" + this.tsId + " , onId=" + this.onId + " , progId=" + this.progId + " , symRate=" + this.symRate + " , mod=" + this.mod + " , satRecId=" + this.satRecId + " , nwName=" + this.nwName + " , barkerMask=" + this.barkerMask + " , sdtServiceType=" + this.sdtServiceType + " , mfType=" + this.mfType + " , categoryMask=" + this.categoryMask + "]\n";
    }
}
