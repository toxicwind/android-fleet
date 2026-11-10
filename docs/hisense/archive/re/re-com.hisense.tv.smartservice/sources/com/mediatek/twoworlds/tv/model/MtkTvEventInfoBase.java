package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvEventInfoBase {
    public static int MAX_AUDIO_COMPONENT_INFO = 8;
    public static int MAX_COMPONENT_INFO = 8;
    public static int MAX_EVENT_GROUP_INFO = 1;
    public static int MAX_EVENT_LINKAGE_INFO = 4;
    public static int MAX_EVENT_SERIES_INFO = 1;
    public boolean caption;
    public int channelId;
    public long duration;
    public int eventCategoryNum;
    public String eventDetail;
    public String eventDetail_extended;
    public int eventId;
    public String eventRating;
    public int eventRatingType;
    public String eventTitle;
    public boolean freeCaMode;
    public int guidanceMode;
    public String guidanceText;
    public long startTime;
    public int svlId;
    public int[] caSystemId = new int[4];
    public int[] eventCategory = new int[8];
    public MtkTvEventLinkageBase[] eventLinkage = new MtkTvEventLinkageBase[MAX_EVENT_LINKAGE_INFO];
    public MtkTvEventGroupBase[] eventGroup = new MtkTvEventGroupBase[MAX_EVENT_GROUP_INFO];
    public MtkTvEventSeriesBase[] eventSeries = new MtkTvEventSeriesBase[MAX_EVENT_SERIES_INFO];
    public MtkTvEventComponentDescriptorBase[] eventComponent = new MtkTvEventComponentDescriptorBase[MAX_COMPONENT_INFO];
    public MtkTvEventAudioComponentBase[] eventAudioComponent = new MtkTvEventAudioComponentBase[MAX_AUDIO_COMPONENT_INFO];

    public MtkTvEventInfoBase() {
        for (int i = 0; i < MAX_EVENT_LINKAGE_INFO; i++) {
            this.eventLinkage[i] = new MtkTvEventLinkageBase();
        }
        for (int i2 = 0; i2 < MAX_EVENT_GROUP_INFO; i2++) {
            this.eventGroup[i2] = new MtkTvEventGroupBase();
        }
        for (int i3 = 0; i3 < MAX_EVENT_SERIES_INFO; i3++) {
            this.eventSeries[i3] = new MtkTvEventSeriesBase();
        }
        for (int i4 = 0; i4 < MAX_COMPONENT_INFO; i4++) {
            this.eventComponent[i4] = new MtkTvEventComponentDescriptorBase();
        }
        for (int i5 = 0; i5 < MAX_AUDIO_COMPONENT_INFO; i5++) {
            this.eventAudioComponent[i5] = new MtkTvEventAudioComponentBase();
        }
        this.svlId = 0;
        this.channelId = 0;
        this.eventId = 0;
        this.startTime = 0L;
        this.duration = 0L;
        this.caption = false;
        this.freeCaMode = false;
        this.eventTitle = "";
        this.eventDetail = "";
        this.eventDetail_extended = "";
        this.guidanceMode = 0;
        this.guidanceText = "";
        this.eventRating = "";
        this.eventRatingType = 0;
        int i6 = 0;
        while (true) {
            int[] iArr = this.caSystemId;
            if (i6 >= iArr.length) {
                break;
            }
            iArr[i6] = 0;
            i6++;
        }
        this.eventCategoryNum = 0;
        int i7 = 0;
        while (true) {
            int[] iArr2 = this.eventCategory;
            if (i7 >= iArr2.length) {
                return;
            }
            iArr2[i7] = 0;
            i7++;
        }
    }

    public int[] getCaSystemId() {
        return this.caSystemId;
    }

    public int getChannelId() {
        return this.channelId;
    }

    public long getDuration() {
        return this.duration;
    }

    public MtkTvEventAudioComponentBase[] getEventAudioComponent() {
        return this.eventAudioComponent;
    }

    public int[] getEventCategory() {
        return this.eventCategory;
    }

    public int getEventCategoryNum() {
        return this.eventCategoryNum;
    }

    public MtkTvEventComponentDescriptorBase[] getEventComponent() {
        return this.eventComponent;
    }

    public String getEventDetail() {
        return this.eventDetail;
    }

    public String getEventDetailExtend() {
        return this.eventDetail_extended;
    }

    public MtkTvEventGroupBase[] getEventGroup() {
        return this.eventGroup;
    }

    public int getEventId() {
        return this.eventId;
    }

    public MtkTvEventLinkageBase[] getEventLinkage() {
        return this.eventLinkage;
    }

    public String getEventRating() {
        return this.eventRating;
    }

    public int getEventRatingType() {
        return this.eventRatingType;
    }

    public MtkTvEventSeriesBase[] getEventSeries() {
        return this.eventSeries;
    }

    public String getEventTitle() {
        return this.eventTitle;
    }

    public int getGuidanceMode() {
        return this.guidanceMode;
    }

    public String getGuidanceText() {
        return this.guidanceText;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public int getSvlId() {
        return this.svlId;
    }

    public boolean isCaption() {
        return this.caption;
    }

    public boolean isFreeCaMode() {
        return this.freeCaMode;
    }

    public void setCaSystemId(int[] iArr) {
        this.caSystemId = iArr;
    }

    public void setCaption(boolean z) {
        this.caption = z;
    }

    public void setChannelId(int i) {
        this.channelId = i;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public void setEventAudioComponent(MtkTvEventAudioComponentBase[] mtkTvEventAudioComponentBaseArr) {
        this.eventAudioComponent = mtkTvEventAudioComponentBaseArr;
    }

    public void setEventCategory(int[] iArr) {
        this.eventCategory = iArr;
    }

    public void setEventCategoryNum(int i) {
        this.eventCategoryNum = i;
    }

    public void setEventComponent(MtkTvEventComponentDescriptorBase[] mtkTvEventComponentDescriptorBaseArr) {
        this.eventComponent = mtkTvEventComponentDescriptorBaseArr;
    }

    public void setEventDetail(String str) {
        this.eventDetail = str;
    }

    public void setEventDetailExtened(String str) {
        this.eventDetail_extended = str;
    }

    public void setEventGroup(MtkTvEventGroupBase[] mtkTvEventGroupBaseArr) {
        this.eventGroup = mtkTvEventGroupBaseArr;
    }

    public void setEventId(int i) {
        this.eventId = i;
    }

    public void setEventLinkage(MtkTvEventLinkageBase[] mtkTvEventLinkageBaseArr) {
        this.eventLinkage = mtkTvEventLinkageBaseArr;
    }

    public void setEventRating(String str) {
        this.eventRating = str;
    }

    public void setEventRatingType(int i) {
        this.eventRatingType = i;
    }

    public void setEventSeries(MtkTvEventSeriesBase[] mtkTvEventSeriesBaseArr) {
        this.eventSeries = mtkTvEventSeriesBaseArr;
    }

    public void setEventTitle(String str) {
        this.eventTitle = str;
    }

    public void setFreeCaMode(boolean z) {
        this.freeCaMode = z;
    }

    public void setGuidanceMode(int i) {
        this.guidanceMode = i;
    }

    public void setGuidanceText(String str) {
        this.guidanceText = str;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public void setSvlId(int i) {
        this.svlId = i;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvEventInfo [svlId=");
        sbA.append(this.svlId);
        sbA.append(", channelId=");
        sbA.append(this.channelId);
        sbA.append(", eventId=");
        sbA.append(this.eventId);
        sbA.append(", startTime=");
        sbA.append(this.startTime);
        sbA.append(", duration=");
        sbA.append(this.duration);
        sbA.append(", caption=");
        sbA.append(this.caption);
        sbA.append(", freeCaMode=");
        sbA.append(this.freeCaMode);
        sbA.append(", eventTitle=");
        sbA.append(this.eventTitle);
        sbA.append(", eventDetail=");
        sbA.append(this.eventDetail);
        sbA.append(", eventDetailextended=");
        sbA.append(this.eventDetail_extended);
        sbA.append(", guidanceMode=");
        sbA.append(this.guidanceMode);
        sbA.append(", guidanceText=");
        sbA.append(this.guidanceText);
        sbA.append(", eventRating=");
        sbA.append(this.eventRating);
        sbA.append(", eventRatingType=");
        sbA.append(this.eventRatingType);
        sbA.append(", caSystemId=");
        sbA.append(Arrays.toString(this.caSystemId));
        sbA.append(", eventCategoryNum=");
        sbA.append(this.eventCategoryNum);
        sbA.append(", eventCategory=");
        sbA.append(Arrays.toString(this.eventCategory));
        sbA.append(", eventLinkage=");
        sbA.append(Arrays.toString(this.eventLinkage));
        sbA.append(", eventSeries=");
        sbA.append(Arrays.toString(this.eventSeries));
        sbA.append(", eventGroup=");
        sbA.append(Arrays.toString(this.eventGroup));
        sbA.append(", eventComponent=");
        sbA.append(Arrays.toString(this.eventComponent));
        sbA.append(", eventAudioComponent=");
        sbA.append(Arrays.toString(this.eventAudioComponent));
        sbA.append("]");
        return sbA.toString();
    }
}
