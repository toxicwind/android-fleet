package com.lagradost.cloudstream3.ui.player;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0012JV\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0083\u0004J\n\u0010!\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0012¨\u0006#"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/AudioTrack;", "Lcom/lagradost/cloudstream3/ui/player/Track;", "id", "", "label", "language", "sampleMimeType", "channelCount", "", "formatIndex", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getId", "()Ljava/lang/String;", "getLabel", "getLanguage", "getSampleMimeType", "getChannelCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getFormatIndex", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/ui/player/AudioTrack;", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AudioTrack implements Track {
    private final Integer channelCount;
    private final Integer formatIndex;
    private final String id;
    private final String label;
    private final String language;
    private final String sampleMimeType;

    public static /* synthetic */ AudioTrack copy$default(AudioTrack audioTrack, String str, String str2, String str3, String str4, Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = audioTrack.id;
        }
        if ((i & 2) != 0) {
            str2 = audioTrack.label;
        }
        if ((i & 4) != 0) {
            str3 = audioTrack.language;
        }
        if ((i & 8) != 0) {
            str4 = audioTrack.sampleMimeType;
        }
        if ((i & 16) != 0) {
            num = audioTrack.channelCount;
        }
        if ((i & 32) != 0) {
            num2 = audioTrack.formatIndex;
        }
        Integer num3 = num;
        Integer num4 = num2;
        return audioTrack.copy(str, str2, str3, str4, num3, num4);
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
    public final String getSampleMimeType() {
        return this.sampleMimeType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getChannelCount() {
        return this.channelCount;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getFormatIndex() {
        return this.formatIndex;
    }

    public final AudioTrack copy(String id, String label, String language, String sampleMimeType, Integer channelCount, Integer formatIndex) {
        return new AudioTrack(id, label, language, sampleMimeType, channelCount, formatIndex);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioTrack)) {
            return false;
        }
        AudioTrack audioTrack = (AudioTrack) other;
        return Intrinsics.areEqual(this.id, audioTrack.id) && Intrinsics.areEqual(this.label, audioTrack.label) && Intrinsics.areEqual(this.language, audioTrack.language) && Intrinsics.areEqual(this.sampleMimeType, audioTrack.sampleMimeType) && Intrinsics.areEqual(this.channelCount, audioTrack.channelCount) && Intrinsics.areEqual(this.formatIndex, audioTrack.formatIndex);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.label;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.language;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.sampleMimeType;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Integer num = this.channelCount;
        int iHashCode5 = (iHashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.formatIndex;
        return iHashCode5 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        return "AudioTrack(id=" + this.id + ", label=" + this.label + ", language=" + this.language + ", sampleMimeType=" + this.sampleMimeType + ", channelCount=" + this.channelCount + ", formatIndex=" + this.formatIndex + ')';
    }

    public AudioTrack(String str, String str2, String str3, String str4, Integer num, Integer num2) {
        this.id = str;
        this.label = str2;
        this.language = str3;
        this.sampleMimeType = str4;
        this.channelCount = num;
        this.formatIndex = num2;
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

    @Override // com.lagradost.cloudstream3.ui.player.Track
    public String getSampleMimeType() {
        return this.sampleMimeType;
    }

    public final Integer getChannelCount() {
        return this.channelCount;
    }

    public final Integer getFormatIndex() {
        return this.formatIndex;
    }
}
