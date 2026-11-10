package com.mediatek.twoworlds.tv.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvEventGroupBase {
    public static int MAX_EVENT_COMMON_INFO = 32;
    private MtkTvEventComom[] eventCommom = new MtkTvEventComom[MAX_EVENT_COMMON_INFO];
    private short eventType = 0;
    private short eventCommonCnt = 0;

    public MtkTvEventGroupBase() {
        for (int i = 0; i < MAX_EVENT_COMMON_INFO; i++) {
            this.eventCommom[i] = new MtkTvEventComom();
        }
    }

    public short geteventType() {
        return this.eventType;
    }

    public void seteventType(short s) {
        this.eventType = s;
    }

    public short geteventCommonCnt() {
        return this.eventCommonCnt;
    }

    public void seteventCommonCnt(short s) {
        this.eventCommonCnt = s;
    }

    public MtkTvEventComom[] geteventCommom() {
        return this.eventCommom;
    }

    public void seteventCommom(MtkTvEventComom[] mtkTvEventComomArr) {
        this.eventCommom = mtkTvEventComomArr;
    }

    public String toString() {
        return "MtkTvEventGroup: eventType=" + ((int) this.eventType) + ", eventCommonCnt=" + ((int) this.eventCommonCnt) + ", eventCommom=" + Arrays.toString(this.eventCommom);
    }
}
