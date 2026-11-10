package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvEventComom {
    public int channelId = 0;
    public int eventId = 0;

    public int getChannelId() {
        return this.channelId;
    }

    public int getEventId() {
        return this.eventId;
    }

    public void setChannelId(int i) {
        this.channelId = i;
    }

    public void setEventId(int i) {
        this.eventId = i;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvEventComom: channelId=");
        sbA.append(this.channelId);
        sbA.append(", eventId=");
        sbA.append(this.eventId);
        return sbA.toString();
    }
}
