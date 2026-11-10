package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvFavoritelistInfoBase {
    private static final String TAG = "MtkTvFavoritelistInfo";
    protected int brdcstType;
    protected int caSystemId;
    protected int channelId;
    protected String channelName;
    protected String channelNumber;
    protected int inptSrcId;
    protected int nwMask;
    protected int servType;
    protected String shortName;
    protected int svlId;
    protected int svlRecId;

    public MtkTvFavoritelistInfoBase() {
        Log.d(TAG, "MtkTvFavoritelistInfo Constructor\n");
    }

    public MtkTvFavoritelistInfoBase(int i, int i2) {
        Log.d(TAG, "MtkTvFavoritelistInfo Constructor(svlId = " + i + ", svlRecId = " + i2 + ")\n");
        this.svlId = i;
        this.svlRecId = i2;
    }

    public String getChannelNumber() {
        return this.channelNumber;
    }

    public void setChannelNumber(String str) {
        this.channelNumber = str;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String str) {
        this.channelName = str;
    }

    public int getChannelId() {
        return this.channelId;
    }

    public void setChannelId(int i) {
        this.channelId = i;
    }

    public int getSvlId() {
        return this.svlId;
    }

    protected void setSvlId(int i) {
        this.svlId = i;
    }

    public int getSvlRecId() {
        return this.svlRecId;
    }

    protected void setSvlRecId(int i) {
        this.svlRecId = i;
    }

    public int getInptSrcId() {
        return this.inptSrcId;
    }

    protected void setInptSrcId(int i) {
        this.inptSrcId = i;
    }

    public int getNwMask() {
        return this.nwMask;
    }

    public void setNwMask(int i) {
        this.nwMask = i;
    }

    public int getBrdcstType() {
        return this.brdcstType;
    }

    public void setBrdcstType(int i) {
        this.brdcstType = i;
    }

    public int getServType() {
        return this.servType;
    }

    public void setServType(int i) {
        this.servType = i;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String str) {
        this.shortName = str;
    }

    public int getCaSystemId() {
        return this.caSystemId;
    }

    public void setCaSystemId(int i) {
        this.caSystemId = i;
    }
}
