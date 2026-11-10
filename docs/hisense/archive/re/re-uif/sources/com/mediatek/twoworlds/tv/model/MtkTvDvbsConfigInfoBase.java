package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvDvbsConfigInfoBase {
    private static final String TAG = "MtkTvDvbsConfigInfoBase";
    protected int bandFreq;
    protected int diseqcType;
    protected int diseqcTypeEx;
    protected int lnbHighFreq;
    protected int lnbLowFreq;
    protected int lnbPower;
    protected int lnbSwitchFreq;
    protected int lnbType;
    protected int m22k;
    protected int mask;
    protected int mduType;
    protected int motorPosition;
    protected int motorType;
    protected int orbPos;
    protected int polar;
    protected int port;
    protected int portEx;
    protected int position;
    protected String satName;
    protected int satlRecId;
    protected int subBandFreq;
    protected int subDiseqcType;
    protected int subDiseqcTypeEx;
    protected int subLnbPower;
    protected int subM22k;
    protected int subMduType;
    protected int subPort;
    protected int subPortEx;
    protected int subPosition;
    protected int subToneBurst;
    protected int subUserBand;
    protected int toneBurst;
    protected int userBand;

    public int getSatlRecId() {
        return this.satlRecId;
    }

    public void setSatlRecId(int i) {
        this.satlRecId = i;
    }

    public int getMask() {
        return this.mask;
    }

    public void setMask(int i) {
        this.mask = i;
    }

    public int getLnbType() {
        return this.lnbType;
    }

    public void setLnbType(int i) {
        this.lnbType = i;
    }

    public int getLnbLowFreq() {
        return this.lnbLowFreq;
    }

    public void setLnbLowFreq(int i) {
        this.lnbLowFreq = i;
    }

    public int getLnbHighFreq() {
        return this.lnbHighFreq;
    }

    public void setLnbHighFreq(int i) {
        this.lnbHighFreq = i;
    }

    public int getLnbSwitchFreq() {
        return this.lnbSwitchFreq;
    }

    public void setLnbSwitchFreq(int i) {
        this.lnbSwitchFreq = i;
    }

    public int getDiseqcType() {
        return this.diseqcType;
    }

    public void setDiseqcType(int i) {
        this.diseqcType = i;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public int getDiseqcTypeEx() {
        return this.diseqcTypeEx;
    }

    public void setDiseqcTypeEx(int i) {
        this.diseqcTypeEx = i;
    }

    public int getPortEx() {
        return this.portEx;
    }

    public void setPortEx(int i) {
        this.portEx = i;
    }

    public int getSubDiseqcTypeEx() {
        return this.subDiseqcTypeEx;
    }

    public void setSubDiseqcTypeEx(int i) {
        this.subDiseqcTypeEx = i;
    }

    public int getSubPortEx() {
        return this.subPortEx;
    }

    public void setSubPortEx(int i) {
        this.subPortEx = i;
    }

    public int getM22k() {
        return this.m22k;
    }

    public void setM22k(int i) {
        this.m22k = i;
    }

    public int getToneBurst() {
        return this.toneBurst;
    }

    public void setToneBurst(int i) {
        this.toneBurst = i;
    }

    public int getLnbPower() {
        return this.lnbPower;
    }

    public void setLnbPower(int i) {
        this.lnbPower = i;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public int getUserBand() {
        return this.userBand;
    }

    public void setUserBand(int i) {
        this.userBand = i;
    }

    public int getBandFreq() {
        return this.bandFreq;
    }

    public void setBandFreq(int i) {
        this.bandFreq = i;
    }

    public int getMduType() {
        return this.mduType;
    }

    public void setMduType(int i) {
        this.mduType = i;
    }

    public int getOrbPos() {
        return this.orbPos;
    }

    public void setOrbPos(int i) {
        this.orbPos = i;
    }

    public int getSubDiseqcType() {
        return this.subDiseqcType;
    }

    public void setSubDiseqcType(int i) {
        this.subDiseqcType = i;
    }

    public int getSubPort() {
        return this.subPort;
    }

    public void setSubPort(int i) {
        this.subPort = i;
    }

    public int getSubM22k() {
        return this.subM22k;
    }

    public void setSubM22k(int i) {
        this.subM22k = i;
    }

    public int getSubToneBurst() {
        return this.subToneBurst;
    }

    public void setSubToneBurst(int i) {
        this.subToneBurst = i;
    }

    public int getSubLnbPower() {
        return this.subLnbPower;
    }

    public void setSubLnbPower(int i) {
        this.subLnbPower = i;
    }

    public int getSubPosition() {
        return this.subPosition;
    }

    public void setSubPosition(int i) {
        this.subPosition = i;
    }

    public int getSubUserBand() {
        return this.subUserBand;
    }

    public void setSubUserBand(int i) {
        this.subUserBand = i;
    }

    public int getSubBandFreq() {
        return this.subBandFreq;
    }

    public void setSubBandFreq(int i) {
        this.subBandFreq = i;
    }

    public int getSubMduType() {
        return this.subMduType;
    }

    public void setSubMduType(int i) {
        this.subMduType = i;
    }

    public String getSatName() {
        return this.satName;
    }

    public void setSatName(String str) {
        this.satName = str;
    }

    public int getMotorType() {
        return this.motorType;
    }

    public void setMotorType(int i) {
        this.motorType = i;
    }

    public int getMotorPosition() {
        return this.motorPosition;
    }

    public void setMotorPosition(int i) {
        this.motorPosition = i;
    }

    public int getPolar() {
        return this.polar;
    }

    public void setPolar(int i) {
        this.polar = i;
    }

    public String toString() {
        return "satlRecId=" + this.satlRecId + ", mask=" + this.mask + ", lnbType=" + this.lnbType + ", lnbLowFreq=" + this.lnbLowFreq + ", lnbHighFreq=" + this.lnbHighFreq + ", lnbSwitchFreq=" + this.lnbSwitchFreq + ", diseqcType=" + this.diseqcType + ", port=" + this.port + ", m22k=" + this.m22k + ", toneBurst=" + this.toneBurst + ", lnbPower=" + this.lnbPower + ", position=" + this.position + ", userBand=" + this.userBand + ", bankFreq=" + this.bandFreq + ", mduType=" + this.mduType + ", orbPos=" + this.orbPos + ", subDiseqcType=" + this.subDiseqcType + ", subPort=" + this.subPort + ", subM22k=" + this.subM22k + ", subToneBurst=" + this.subToneBurst + ", subLnbPower=" + this.subLnbPower + ", subPosition=" + this.subPosition + ", subUserBand=" + this.subUserBand + ", subBankFreq=" + this.subBandFreq + ", subMduType=" + this.subMduType + ", diseqcTypeEx=" + this.diseqcTypeEx + ", portEx=" + this.portEx + ", subDiseqcTypeEx=" + this.subDiseqcTypeEx + ", subPortEx=" + this.subPortEx + ", satName=" + this.satName + ", motorType=" + this.motorType + ", motorPosition=" + this.motorPosition + "]\n";
    }
}
