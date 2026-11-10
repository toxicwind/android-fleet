package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public class TvProviderChannelEventBase {
    public static final int UPDATTE_EVENT_TYPE_CLEAN_DB = 4;
    public static final int UPDATTE_EVENT_TYPE_DELETE_REC = 3;
    public static final int UPDATTE_EVENT_TYPE_INSERT_REC = 1;
    public static final int UPDATTE_EVENT_TYPE_LOAD_DB = 5;
    public static final int UPDATTE_EVENT_TYPE_UPDATE_REC = 2;
    public int mCount;
    public int[] mEventType;
    public int mSvlId;
    public int[] mSvlRecId;

    public int getCount() {
        return this.mCount;
    }

    public int[] getEventType() {
        return this.mEventType;
    }

    public int getSvlId() {
        return this.mSvlId;
    }

    public int[] getSvlRecId() {
        return this.mSvlRecId;
    }

    public void setCount(int i) {
        this.mCount = i;
    }

    public void setEventType(int[] iArr) {
        this.mEventType = iArr;
    }

    public void setSvlId(int i) {
        this.mSvlId = i;
    }

    public void setSvlRecId(int[] iArr) {
        this.mSvlRecId = iArr;
    }
}
