package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvFreqChgParamBase {
    public static final String TAG = "MtkTvFreqSelectParam";
    public int conType;
    public int freq;
    public int freqType;
    public int satLstId;
    public int satLstRecId;
    public int satPol;
    public int symRate;
    public int tunerMod;

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

    public int getConType() {
        return this.conType;
    }

    public int getSatLstId() {
        return this.satLstId;
    }

    public int getSatLstRecId() {
        return this.satLstRecId;
    }

    public int getSatPol() {
        return this.satPol;
    }

    public int getfreq() {
        return this.freq;
    }

    public int getfreqType() {
        return this.freqType;
    }

    public int getsymRate() {
        return this.symRate;
    }

    public int gettunerMod() {
        return this.tunerMod;
    }

    public void setConType(int i) {
        this.conType = i;
    }

    public void setFreq(int i) {
        this.freq = i;
    }

    public void setFreqType(int i) {
        this.freqType = i;
    }

    public void setSatLstId(int i) {
        this.satLstId = i;
    }

    public void setSatLstRecId(int i) {
        this.satLstRecId = i;
    }

    public void setSatPol(int i) {
        this.satPol = i;
    }

    public void setsymRate(int i) {
        this.symRate = i;
    }

    public void settunerMod(int i) {
        this.tunerMod = i;
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
}
