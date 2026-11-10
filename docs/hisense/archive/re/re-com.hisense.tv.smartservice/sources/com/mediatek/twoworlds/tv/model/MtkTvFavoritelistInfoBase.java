package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvFavoritelistInfoBase {
    public static final String TAG = "MtkTvFavoritelistInfo";
    public int brdcstType;
    public int caSystemId;
    public int channelId;
    public String channelName;
    public String channelNumber;
    public int inptSrcId;
    public int nwMask;
    public int servType;
    public String shortName;
    public int svlId;
    public int svlRecId;

    public MtkTvFavoritelistInfoBase() {
        Log.d(TAG, "MtkTvFavoritelistInfo Constructor\n");
    }

    public int getBrdcstType() {
        return this.brdcstType;
    }

    public int getCaSystemId() {
        return this.caSystemId;
    }

    public int getChannelId() {
        return this.channelId;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public String getChannelNumber() {
        return this.channelNumber;
    }

    public int getInptSrcId() {
        return this.inptSrcId;
    }

    public int getNwMask() {
        return this.nwMask;
    }

    public int getServType() {
        return this.servType;
    }

    public String getShortName() {
        return this.shortName;
    }

    public int getSvlId() {
        return this.svlId;
    }

    public int getSvlRecId() {
        return this.svlRecId;
    }

    public void setBrdcstType(int i) {
        this.brdcstType = i;
    }

    public void setCaSystemId(int i) {
        this.caSystemId = i;
    }

    public void setChannelId(int i) {
        this.channelId = i;
    }

    public void setChannelName(String str) {
        this.channelName = str;
    }

    public void setChannelNumber(String str) {
        this.channelNumber = str;
    }

    public void setInptSrcId(int i) {
        this.inptSrcId = i;
    }

    public void setNwMask(int i) {
        this.nwMask = i;
    }

    public void setServType(int i) {
        this.servType = i;
    }

    public void setShortName(String str) {
        this.shortName = str;
    }

    public void setSvlId(int i) {
        this.svlId = i;
    }

    public void setSvlRecId(int i) {
        this.svlRecId = i;
    }

    public MtkTvFavoritelistInfoBase(int i, int i2) {
        Log.d(TAG, a.a("MtkTvFavoritelistInfo Constructor(svlId = ", i, ", svlRecId = ", i2, ")\n"));
        this.svlId = i;
        this.svlRecId = i2;
    }
}
