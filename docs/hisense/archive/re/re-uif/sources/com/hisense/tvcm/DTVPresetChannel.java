package com.hisense.tvcm;

/* JADX INFO: loaded from: classes.dex */
public class DTVPresetChannel {
    private int bindWidth;
    private String channelName;
    private int channelNum;
    private int frequency;
    private ModulationMode mModulationMode;
    private int networkId;
    private int orgNetworkId;
    private int serviceId;
    private int symRate;
    private int tsId;

    public enum ModulationMode {
        MOD_UNKNOWN,
        MOD_PSK_8,
        MOD_VSB_8,
        MOD_VSB_16,
        MOD_QAM_16,
        MOD_QAM_32,
        MOD_QAM_64,
        MOD_QAM_80,
        MOD_QAM_96,
        MOD_QAM_128,
        MOD_QAM_160,
        MOD_QAM_192,
        MOD_QAM_256,
        MOD_QAM_320,
        MOD_QAM_640,
        MOD_QAM_512,
        MOD_QAM_1024,
        MOD_QPSK,
        MOD_OQPSK,
        MOD_BPSK,
        MOD_VSB_AM,
        MOD_QAM_4_NR,
        MOD_FM_RADIO
    }

    public DTVPresetChannel(int i, String str, int i2, int i3, int i4, int i5, int i6, int i7, int i8, ModulationMode modulationMode) {
        this.channelNum = i;
        this.channelName = str;
        this.frequency = i2;
        this.bindWidth = i3;
        this.networkId = i4;
        this.orgNetworkId = i5;
        this.tsId = i6;
        this.serviceId = i7;
        this.symRate = i8;
        this.mModulationMode = modulationMode;
    }

    public int getBindWidth() {
        return this.bindWidth;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public int getChannelNum() {
        return this.channelNum;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public ModulationMode getMod() {
        return this.mModulationMode;
    }

    public int getNetworkId() {
        return this.networkId;
    }

    public int getOrgNetworkId() {
        return this.orgNetworkId;
    }

    public int getServiceId() {
        return this.serviceId;
    }

    public int getSymRate() {
        return this.symRate;
    }

    public int getTsId() {
        return this.tsId;
    }
}
