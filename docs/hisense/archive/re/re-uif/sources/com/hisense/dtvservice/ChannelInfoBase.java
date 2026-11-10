package com.hisense.dtvservice;

/* JADX INFO: loaded from: classes.dex */
public class ChannelInfoBase {
    private int audioSys;
    private int brdcstMedium;
    private int brdcstType;
    private int centralFrequency;
    private int channelId;
    private String channelNum;
    private int colorSys;
    private int editMask;
    private int frequency;
    private int modulationType;
    private int originalNetworkId;
    private int serviceMask;
    private int serviceType;
    private int transportStreamId;
    private int tunerMode;
    private int tvSys;
    private String channelKey = new String();
    private String serviceName = new String();

    public String getChannelKey() {
        return this.channelKey;
    }

    public void setChannelKey(String str) {
        this.channelKey = str;
    }

    public void setTunerMode(int i) {
        this.tunerMode = i;
    }

    public void setTvSys(int i) {
        this.tvSys = i;
    }

    public void setAudioSys(int i) {
        this.audioSys = i;
    }

    public void setColorSys(int i) {
        this.colorSys = i;
    }

    public void setCentralFrequency(int i) {
        this.centralFrequency = i;
    }

    public void setTransportStreamId(int i) {
        this.transportStreamId = i;
    }

    public void setChannelId(int i) {
        this.channelId = i;
    }

    public int getBrdcstType() {
        return this.brdcstType;
    }

    public void setBrdcstType(int i) {
        this.brdcstType = i;
    }

    public void setServiceType(int i) {
        this.serviceType = i;
    }

    public String getChannelNum() {
        return this.channelNum;
    }

    public void setChannelNum(String str) {
        this.channelNum = str;
    }

    public void setServiceName(String str) {
        this.serviceName = str;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public void setBrdcstMedium(int i) {
        this.brdcstMedium = i;
    }

    public void setModulationType(int i) {
        this.modulationType = i;
    }

    public void setOriginalNetworkId(int i) {
        this.originalNetworkId = i;
    }

    public void setServiceMask(int i) {
        this.serviceMask = i;
    }

    public void setEditMask(int i) {
        this.editMask = i;
    }

    public String toString() {
        return "ChannelInfoBase{channelKey='" + this.channelKey + "', tunerMode=" + this.tunerMode + ", tvSys=" + this.tvSys + ", audioSys=" + this.audioSys + ", colorSys=" + this.colorSys + ", centralFrequency=" + this.centralFrequency + ", transportStreamId=" + this.transportStreamId + ", channelId=" + this.channelId + ", brdcstType=" + this.brdcstType + ", serviceType=" + this.serviceType + ", channelNum=" + this.channelNum + ", serviceName='" + this.serviceName + "', frequency=" + this.frequency + ", brdcstMedium=" + this.brdcstMedium + ", modulationType=" + this.modulationType + ", originalNetworkId=" + this.originalNetworkId + ", serviceMask=" + this.serviceMask + ", editMask=" + this.editMask + '}';
    }
}
