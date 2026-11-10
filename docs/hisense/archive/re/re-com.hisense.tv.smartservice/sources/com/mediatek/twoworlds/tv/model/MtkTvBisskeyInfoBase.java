package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import b.a.a.a.a;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvBisskeyInfoBase {
    public static final int CUSTOM_DATA_LEN = 8;
    public static final int KEY_LEN = 8;
    public static final int PRIVATE_DATA_LEN = 20;
    public static final String TAG = "MtkTvBisskeyInfo";
    public int bslId;
    public int bslRecId;
    public int frequency;
    public int polarization;
    public int programId;
    public byte[] serviceCwKey;
    public int symRate;

    public MtkTvBisskeyInfoBase() {
        this.serviceCwKey = new byte[8];
    }

    private void bisskeyInfoSetDefault() {
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

    public int getBslId() {
        return this.bslId;
    }

    public int getBslRecId() {
        return this.bslRecId;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public int getPolarization() {
        return this.polarization;
    }

    public int getProgramId() {
        return this.programId;
    }

    public byte[] getServiceCwKey() {
        return this.serviceCwKey;
    }

    public int getSymRate() {
        return this.symRate;
    }

    public void setBslId(int i) {
        this.bslId = i;
    }

    public void setBslRecId(int i) {
        this.bslRecId = i;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public void setPolarization(int i) {
        this.polarization = i;
    }

    public void setProgramId(int i) {
        this.programId = i;
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

    public void setSymRate(int i) {
        this.symRate = i;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvBisskeyInfo [bslId=");
        sbA.append(this.bslId);
        sbA.append(" , bslRecId=");
        sbA.append(this.bslRecId);
        sbA.append(" , frequency=");
        sbA.append(this.frequency);
        sbA.append(" , symRate=");
        sbA.append(this.symRate);
        sbA.append(" , polarization=");
        sbA.append(this.polarization);
        sbA.append(" , programId=");
        sbA.append(this.programId);
        sbA.append(", serviceCwKey=");
        sbA.append(Arrays.toString(this.serviceCwKey));
        sbA.append("]\n");
        return sbA.toString();
    }

    public MtkTvBisskeyInfoBase(int i, int i2) {
        this.serviceCwKey = new byte[8];
        this.bslId = i;
        this.bslRecId = i2;
    }
}
