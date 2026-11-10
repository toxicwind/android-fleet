package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class TvProviderChannelEventBase {
    public static final int UPDATTE_EVENT_TYPE_CLEAN_DB = 4;
    public static final int UPDATTE_EVENT_TYPE_DELETE_REC = 3;
    public static final int UPDATTE_EVENT_TYPE_INSERT_REC = 1;
    public static final int UPDATTE_EVENT_TYPE_LOAD_DB = 5;
    public static final int UPDATTE_EVENT_TYPE_UPDATE_REC = 2;
    protected int mCount;
    protected int[] mEventType;
    protected int mSvlId;
    protected int[] mSvlRecId;

    public int getSvlId() {
        return this.mSvlId;
    }

    public void setSvlId(int i) {
        this.mSvlId = i;
    }

    public int getCount() {
        return this.mCount;
    }

    public void setCount(int i) {
        this.mCount = i;
    }

    public int[] getEventType() {
        return this.mEventType;
    }

    public void setEventType(int[] iArr) {
        this.mEventType = iArr;
    }

    public int[] getSvlRecId() {
        return this.mSvlRecId;
    }

    public void setSvlRecId(int[] iArr) {
        this.mSvlRecId = iArr;
    }
}
