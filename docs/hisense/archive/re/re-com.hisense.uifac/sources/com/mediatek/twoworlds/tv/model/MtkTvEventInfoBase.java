package com.mediatek.twoworlds.tv.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvEventInfoBase {
    public static int MAX_AUDIO_COMPONENT_INFO = 8;
    public static int MAX_COMPONENT_INFO = 8;
    public static int MAX_EVENT_GROUP_INFO = 1;
    public static int MAX_EVENT_LINKAGE_INFO = 4;
    public static int MAX_EVENT_SERIES_INFO = 1;
    private boolean caption;
    private int channelId;
    private long duration;
    private int eventCategoryNum;
    private String eventDetail;
    private String eventDetail_extended;
    private int eventId;
    private String eventRating;
    private int eventRatingType;
    private String eventTitle;
    private boolean freeCaMode;
    private int guidanceMode;
    private String guidanceText;
    private long startTime;
    private int svlId;
    private int[] caSystemId = new int[4];
    private int[] eventCategory = new int[8];
    private MtkTvEventLinkageBase[] eventLinkage = new MtkTvEventLinkageBase[MAX_EVENT_LINKAGE_INFO];
    private MtkTvEventGroupBase[] eventGroup = new MtkTvEventGroupBase[MAX_EVENT_GROUP_INFO];
    private MtkTvEventSeriesBase[] eventSeries = new MtkTvEventSeriesBase[MAX_EVENT_SERIES_INFO];
    private MtkTvEventComponentDescriptorBase[] eventComponent = new MtkTvEventComponentDescriptorBase[MAX_COMPONENT_INFO];
    private MtkTvEventAudioComponentBase[] eventAudioComponent = new MtkTvEventAudioComponentBase[MAX_AUDIO_COMPONENT_INFO];

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

    public int getSvlId() {
        return this.svlId;
    }

    public void setSvlId(int i) {
        this.svlId = i;
    }

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

    public int getEventRatingType() {
        return this.eventRatingType;
    }

    public void setEventRatingType(int i) {
        this.eventRatingType = i;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public boolean isCaption() {
        return this.caption;
    }

    public void setCaption(boolean z) {
        this.caption = z;
    }

    public boolean isFreeCaMode() {
        return this.freeCaMode;
    }

    public void setFreeCaMode(boolean z) {
        this.freeCaMode = z;
    }

    public String getEventTitle() {
        return this.eventTitle;
    }

    public void setEventTitle(String str) {
        this.eventTitle = str;
    }

    public String getEventRating() {
        return this.eventRating;
    }

    public void setEventRating(String str) {
        this.eventRating = str;
    }

    public String getEventDetail() {
        return this.eventDetail;
    }

    public String getEventDetailExtend() {
        return this.eventDetail_extended;
    }

    public void setEventDetail(String str) {
        this.eventDetail = str;
    }

    public void setEventDetailExtened(String str) {
        this.eventDetail_extended = str;
    }

    public int getGuidanceMode() {
        return this.guidanceMode;
    }

    public void setGuidanceMode(int i) {
        this.guidanceMode = i;
    }

    public String getGuidanceText() {
        return this.guidanceText;
    }

    public void setGuidanceText(String str) {
        this.guidanceText = str;
    }

    public int[] getCaSystemId() {
        return this.caSystemId;
    }

    public void setCaSystemId(int[] iArr) {
        this.caSystemId = iArr;
    }

    public int getEventCategoryNum() {
        return this.eventCategoryNum;
    }

    public void setEventCategoryNum(int i) {
        this.eventCategoryNum = i;
    }

    public int[] getEventCategory() {
        return this.eventCategory;
    }

    public void setEventCategory(int[] iArr) {
        this.eventCategory = iArr;
    }

    public MtkTvEventLinkageBase[] getEventLinkage() {
        return this.eventLinkage;
    }

    public void setEventLinkage(MtkTvEventLinkageBase[] mtkTvEventLinkageBaseArr) {
        this.eventLinkage = mtkTvEventLinkageBaseArr;
    }

    public MtkTvEventGroupBase[] getEventGroup() {
        return this.eventGroup;
    }

    public void setEventGroup(MtkTvEventGroupBase[] mtkTvEventGroupBaseArr) {
        this.eventGroup = mtkTvEventGroupBaseArr;
    }

    public MtkTvEventSeriesBase[] getEventSeries() {
        return this.eventSeries;
    }

    public void setEventSeries(MtkTvEventSeriesBase[] mtkTvEventSeriesBaseArr) {
        this.eventSeries = mtkTvEventSeriesBaseArr;
    }

    public MtkTvEventComponentDescriptorBase[] getEventComponent() {
        return this.eventComponent;
    }

    public void setEventComponent(MtkTvEventComponentDescriptorBase[] mtkTvEventComponentDescriptorBaseArr) {
        this.eventComponent = mtkTvEventComponentDescriptorBaseArr;
    }

    public MtkTvEventAudioComponentBase[] getEventAudioComponent() {
        return this.eventAudioComponent;
    }

    public void setEventAudioComponent(MtkTvEventAudioComponentBase[] mtkTvEventAudioComponentBaseArr) {
        this.eventAudioComponent = mtkTvEventAudioComponentBaseArr;
    }

    public String toString() {
        return "MtkTvEventInfo [svlId=" + this.svlId + ", channelId=" + this.channelId + ", eventId=" + this.eventId + ", startTime=" + this.startTime + ", duration=" + this.duration + ", caption=" + this.caption + ", freeCaMode=" + this.freeCaMode + ", eventTitle=" + this.eventTitle + ", eventDetail=" + this.eventDetail + ", eventDetailextended=" + this.eventDetail_extended + ", guidanceMode=" + this.guidanceMode + ", guidanceText=" + this.guidanceText + ", eventRating=" + this.eventRating + ", eventRatingType=" + this.eventRatingType + ", caSystemId=" + Arrays.toString(this.caSystemId) + ", eventCategoryNum=" + this.eventCategoryNum + ", eventCategory=" + Arrays.toString(this.eventCategory) + ", eventLinkage=" + Arrays.toString(this.eventLinkage) + ", eventSeries=" + Arrays.toString(this.eventSeries) + ", eventGroup=" + Arrays.toString(this.eventGroup) + ", eventComponent=" + Arrays.toString(this.eventComponent) + ", eventAudioComponent=" + Arrays.toString(this.eventAudioComponent) + "]";
    }
}
