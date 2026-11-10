package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvBisskeyInfoBase {
    public static final int CUSTOM_DATA_LEN = 8;
    public static final int KEY_LEN = 8;
    public static final int PRIVATE_DATA_LEN = 20;
    private static final String TAG = "MtkTvBisskeyInfo";
    protected int bslId;
    protected int bslRecId;
    protected int frequency;
    protected int polarization;
    protected int programId;
    protected byte[] serviceCwKey;
    protected int symRate;

    private void bisskeyInfoSetDefault() {
    }

    public MtkTvBisskeyInfoBase() {
        this.serviceCwKey = new byte[8];
        bisskeyInfoSetDefault();
    }

    public MtkTvBisskeyInfoBase(int i, int i2) {
        this.serviceCwKey = new byte[8];
        bisskeyInfoSetDefault();
        this.bslId = i;
        this.bslRecId = i2;
    }

    public int getBslId() {
        return this.bslId;
    }

    public void setBslId(int i) {
        this.bslId = i;
    }

    public int getBslRecId() {
        return this.bslRecId;
    }

    protected void setBslRecId(int i) {
        this.bslRecId = i;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public int getSymRate() {
        return this.symRate;
    }

    public void setSymRate(int i) {
        this.symRate = i;
    }

    public int getPolarization() {
        return this.polarization;
    }

    public void setPolarization(int i) {
        this.polarization = i;
    }

    public int getProgramId() {
        return this.programId;
    }

    public void setProgramId(int i) {
        this.programId = i;
    }

    public byte[] getServiceCwKey() {
        return this.serviceCwKey;
    }

    public void setServiceCwKey(byte[] bArr) {
        if (bArr == null) {
            Log.d(TAG, "setServiceCwKey fail!, argu is null.\n");
        } else if (bArr.length > 8) {
            Log.d(TAG, "setServiceCwKey fail because data length more than 8! \n");
        } else {
            System.arraycopy(bArr, 0, this.serviceCwKey, 0, 8);
        }
    }

    public String toString() {
        return "MtkTvBisskeyInfo [bslId=" + this.bslId + " , bslRecId=" + this.bslRecId + " , frequency=" + this.frequency + " , symRate=" + this.symRate + " , polarization=" + this.polarization + " , programId=" + this.programId + ", serviceCwKey=" + Arrays.toString(this.serviceCwKey) + "]\n";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MtkTvBisskeyInfoBase.class != obj.getClass()) {
            return false;
        }
        MtkTvBisskeyInfoBase mtkTvBisskeyInfoBase = (MtkTvBisskeyInfoBase) obj;
        return this.bslId == mtkTvBisskeyInfoBase.bslId && this.bslRecId == mtkTvBisskeyInfoBase.bslRecId && this.frequency == mtkTvBisskeyInfoBase.frequency && this.symRate == mtkTvBisskeyInfoBase.symRate && this.polarization == mtkTvBisskeyInfoBase.polarization && this.programId == mtkTvBisskeyInfoBase.programId;
    }
}
