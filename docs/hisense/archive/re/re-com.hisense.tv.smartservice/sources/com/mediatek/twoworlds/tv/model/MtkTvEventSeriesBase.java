package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvEventSeriesBase {
    public int seriesId = 0;
    public short repeatLabel = 0;
    public short programPattern = 0;
    public boolean expireDateValidFlag = false;
    public int expireDate = 0;
    public int episodeNum = 0;
    public int lastEpisodeNum = 0;
    public String seriesName = "";

    public int getEpisodeNum() {
        return this.episodeNum;
    }

    public int getExpireDate() {
        return this.expireDate;
    }

    public int getLastEpisodeNum() {
        return this.lastEpisodeNum;
    }

    public short getProgramPatternl() {
        return this.programPattern;
    }

    public short getRepeatLabel() {
        return this.repeatLabel;
    }

    public int getSeriesId() {
        return this.seriesId;
    }

    public String getSeriesName() {
        return this.seriesName;
    }

    public boolean isExpireDateValidFlag() {
        return this.expireDateValidFlag;
    }

    public void setEpisodeNum(int i) {
        this.episodeNum = i;
    }

    public void setExpireDate(int i) {
        this.expireDate = i;
    }

    public void setExpireDateValidFlag(boolean z) {
        this.expireDateValidFlag = z;
    }

    public void setLastEpisodeNum(int i) {
        this.lastEpisodeNum = i;
    }

    public void setProgramPattern(short s) {
        this.programPattern = s;
    }

    public void setRepeatLabel(short s) {
        this.repeatLabel = s;
    }

    public void setSeriesId(int i) {
        this.seriesId = i;
    }

    public void setSeriesName(String str) {
        this.seriesName = str;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvEventSeries: seriesId=");
        sbA.append(this.seriesId);
        sbA.append(", repeatLabel=");
        sbA.append((int) this.repeatLabel);
        sbA.append(", programPattern=");
        sbA.append((int) this.programPattern);
        sbA.append(", expireDateValidFlag=");
        sbA.append(this.expireDateValidFlag);
        sbA.append(", expireDate=");
        sbA.append(this.expireDate);
        sbA.append(", episodeNum=");
        sbA.append(this.episodeNum);
        sbA.append(", lastEpisodeNum=");
        sbA.append(this.lastEpisodeNum);
        sbA.append(", seriesName=");
        sbA.append(this.seriesName);
        return sbA.toString();
    }
}
