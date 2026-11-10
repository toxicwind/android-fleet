package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvEventSeriesBase {
    private int seriesId = 0;
    private short repeatLabel = 0;
    private short programPattern = 0;
    private boolean expireDateValidFlag = false;
    private int expireDate = 0;
    private int episodeNum = 0;
    private int lastEpisodeNum = 0;
    private String seriesName = "";

    public int getSeriesId() {
        return this.seriesId;
    }

    public void setSeriesId(int i) {
        this.seriesId = i;
    }

    public short getRepeatLabel() {
        return this.repeatLabel;
    }

    public void setRepeatLabel(short s) {
        this.repeatLabel = s;
    }

    public short getProgramPatternl() {
        return this.programPattern;
    }

    public void setProgramPattern(short s) {
        this.programPattern = s;
    }

    public boolean isExpireDateValidFlag() {
        return this.expireDateValidFlag;
    }

    public void setExpireDateValidFlag(boolean z) {
        this.expireDateValidFlag = z;
    }

    public int getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(int i) {
        this.expireDate = i;
    }

    public int getEpisodeNum() {
        return this.episodeNum;
    }

    public void setEpisodeNum(int i) {
        this.episodeNum = i;
    }

    public int getLastEpisodeNum() {
        return this.lastEpisodeNum;
    }

    public void setLastEpisodeNum(int i) {
        this.lastEpisodeNum = i;
    }

    public String getSeriesName() {
        return this.seriesName;
    }

    public void setSeriesName(String str) {
        this.seriesName = str;
    }

    public String toString() {
        return "MtkTvEventSeries: seriesId=" + this.seriesId + ", repeatLabel=" + ((int) this.repeatLabel) + ", programPattern=" + ((int) this.programPattern) + ", expireDateValidFlag=" + this.expireDateValidFlag + ", expireDate=" + this.expireDate + ", episodeNum=" + this.episodeNum + ", lastEpisodeNum=" + this.lastEpisodeNum + ", seriesName=" + this.seriesName;
    }
}
