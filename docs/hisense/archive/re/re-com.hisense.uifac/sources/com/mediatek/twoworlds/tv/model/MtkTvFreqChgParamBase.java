package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvFreqChgParamBase {
    private static final String TAG = "MtkTvFreqSelectParam";
    protected int conType;
    protected int freq;
    protected int freqType;
    protected int satLstId;
    protected int satLstRecId;
    protected int satPol;
    protected int symRate;
    protected int tunerMod;

    public MtkTvFreqChgParamBase() {
        this.conType = 0;
        this.freqType = 7;
        this.freq = 0;
        this.tunerMod = 0;
        this.symRate = 0;
        this.satPol = 0;
        this.satLstId = 0;
        this.satLstRecId = 0;
    }

    public MtkTvFreqChgParamBase(int i, int i2, int i3, int i4, int i5) {
        this.conType = i;
        this.freqType = i2;
        this.freq = i3;
        this.tunerMod = i4;
        this.symRate = i5;
    }

    public MtkTvFreqChgParamBase(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.conType = i;
        this.freqType = i2;
        this.freq = i3;
        this.tunerMod = i4;
        this.symRate = i5;
        this.satPol = i6;
        this.satLstId = i7;
        this.satLstRecId = i8;
    }

    public void setConType(int i) {
        this.conType = i;
    }

    public void setFreqType(int i) {
        this.freqType = i;
    }

    public void setFreq(int i) {
        this.freq = i;
    }

    public void settunerMod(int i) {
        this.tunerMod = i;
    }

    public void setsymRate(int i) {
        this.symRate = i;
    }

    public void setSatPol(int i) {
        this.satPol = i;
    }

    public void setSatLstId(int i) {
        this.satLstId = i;
    }

    public void setSatLstRecId(int i) {
        this.satLstRecId = i;
    }

    public int getConType() {
        return this.conType;
    }

    public int getfreqType() {
        return this.freqType;
    }

    public int getfreq() {
        return this.freq;
    }

    public int gettunerMod() {
        return this.tunerMod;
    }

    public int getsymRate() {
        return this.symRate;
    }

    public int getSatPol() {
        return this.satPol;
    }

    public int getSatLstId() {
        return this.satLstId;
    }

    public int getSatLstRecId() {
        return this.satLstRecId;
    }
}
