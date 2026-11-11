package com.lagradost.cloudstream3.ui.player;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003JV\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011R\u0016\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006#"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/VideoTrack;", "Lcom/lagradost/cloudstream3/ui/player/Track;", "id", "", "label", "language", "width", "", "height", "sampleMimeType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getLabel", "getLanguage", "getWidth", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHeight", "getSampleMimeType", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/lagradost/cloudstream3/ui/player/VideoTrack;", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class VideoTrack implements Track {
    private final Integer height;
    private final String id;
    private final String label;
    private final String language;
    private final String sampleMimeType;
    private final Integer width;

    public static /* synthetic */ VideoTrack copy$default(VideoTrack videoTrack, String str, String str2, String str3, Integer num, Integer num2, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = videoTrack.id;
        }
        if ((i & 2) != 0) {
            str2 = videoTrack.label;
        }
        if ((i & 4) != 0) {
            str3 = videoTrack.language;
        }
        if ((i & 8) != 0) {
            num = videoTrack.width;
        }
        if ((i & 16) != 0) {
            num2 = videoTrack.height;
        }
        if ((i & 32) != 0) {
            str4 = videoTrack.sampleMimeType;
        }
        Integer num3 = num2;
        String str5 = str4;
        return videoTrack.copy(str, str2, str3, num, num3, str5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getHeight() {
        return this.height;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getSampleMimeType() {
        return this.sampleMimeType;
    }

    public final VideoTrack copy(String id, String label, String language, Integer width, Integer height, String sampleMimeType) {
        return new VideoTrack(id, label, language, width, height, sampleMimeType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VideoTrack)) {
            return false;
        }
        VideoTrack videoTrack = (VideoTrack) other;
        return Intrinsics.areEqual(this.id, videoTrack.id) && Intrinsics.areEqual(this.label, videoTrack.label) && Intrinsics.areEqual(this.language, videoTrack.language) && Intrinsics.areEqual(this.width, videoTrack.width) && Intrinsics.areEqual(this.height, videoTrack.height) && Intrinsics.areEqual(this.sampleMimeType, videoTrack.sampleMimeType);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.label;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.language;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num = this.width;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.height;
        int iHashCode5 = (iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str4 = this.sampleMimeType;
        return iHashCode5 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "VideoTrack(id=" + this.id + ", label=" + this.label + ", language=" + this.language + ", width=" + this.width + ", height=" + this.height + ", sampleMimeType=" + this.sampleMimeType + ')';
    }

    public VideoTrack(String str, String str2, String str3, Integer num, Integer num2, String str4) {
        this.id = str;
        this.label = str2;
        this.language = str3;
        this.width = num;
        this.height = num2;
        this.sampleMimeType = str4;
    }

    @Override // com.lagradost.cloudstream3.ui.player.Track
    public String getId() {
        return this.id;
    }

    @Override // com.lagradost.cloudstream3.ui.player.Track
    public String getLabel() {
        return this.label;
    }

    @Override // com.lagradost.cloudstream3.ui.player.Track
    public String getLanguage() {
        return this.language;
    }

    public final Integer getWidth() {
        return this.width;
    }

    public final Integer getHeight() {
        return this.height;
    }

    @Override // com.lagradost.cloudstream3.ui.player.Track
    public String getSampleMimeType() {
        return this.sampleMimeType;
    }
}
