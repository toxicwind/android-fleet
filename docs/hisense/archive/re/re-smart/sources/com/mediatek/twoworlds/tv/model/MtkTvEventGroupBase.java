package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvEventGroupBase {
    public static int MAX_EVENT_COMMON_INFO = 32;
    public MtkTvEventComom[] eventCommom = new MtkTvEventComom[MAX_EVENT_COMMON_INFO];
    public short eventType = 0;
    public short eventCommonCnt = 0;

    public MtkTvEventGroupBase() {
        for (int i = 0; i < MAX_EVENT_COMMON_INFO; i++) {
            this.eventCommom[i] = new MtkTvEventComom();
        }
    }

    public MtkTvEventComom[] geteventCommom() {
        return this.eventCommom;
    }

    public short geteventCommonCnt() {
        return this.eventCommonCnt;
    }

    public short geteventType() {
        return this.eventType;
    }

    public void seteventCommom(MtkTvEventComom[] mtkTvEventComomArr) {
        this.eventCommom = mtkTvEventComomArr;
    }

    public void seteventCommonCnt(short s) {
        this.eventCommonCnt = s;
    }

    public void seteventType(short s) {
        this.eventType = s;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvEventGroup: eventType=");
        sbA.append((int) this.eventType);
        sbA.append(", eventCommonCnt=");
        sbA.append((int) this.eventCommonCnt);
        sbA.append(", eventCommom=");
        sbA.append(Arrays.toString(this.eventCommom));
        return sbA.toString();
    }
}
