package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvEventComom {
    private int channelId = 0;
    private int eventId = 0;

    public int getChannelId() {
        return this.channelId;
    }

    public void setChannelId(int i) {
        this.channelId = i;
    }

    public int getEventId() {
        return this.eventId;
    }

    public void setEventId(int i) {
        this.eventId = i;
    }

    public String toString() {
        return "MtkTvEventComom: channelId=" + this.channelId + ", eventId=" + this.eventId;
    }
}
