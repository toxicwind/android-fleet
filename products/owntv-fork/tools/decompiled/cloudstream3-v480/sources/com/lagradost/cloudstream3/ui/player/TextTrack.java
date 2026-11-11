package com.lagradost.cloudstream3.ui.player;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/TextTrack;", "Lcom/lagradost/cloudstream3/ui/player/Track;", "id", "", "label", "language", "sampleMimeType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getLabel", "getLanguage", "getSampleMimeType", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TextTrack implements Track {
    private final String id;
    private final String label;
    private final String language;
    private final String sampleMimeType;

    public static /* synthetic */ TextTrack copy$default(TextTrack textTrack, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = textTrack.id;
        }
        if ((i & 2) != 0) {
            str2 = textTrack.label;
        }
        if ((i & 4) != 0) {
            str3 = textTrack.language;
        }
        if ((i & 8) != 0) {
            str4 = textTrack.sampleMimeType;
        }
        return textTrack.copy(str, str2, str3, str4);
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

    public final TextTrack copy(String id, String label, String language, String sampleMimeType) {
        return new TextTrack(id, label, language, sampleMimeType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextTrack)) {
            return false;
        }
        TextTrack textTrack = (TextTrack) other;
        return Intrinsics.areEqual(this.id, textTrack.id) && Intrinsics.areEqual(this.label, textTrack.label) && Intrinsics.areEqual(this.language, textTrack.language) && Intrinsics.areEqual(this.sampleMimeType, textTrack.sampleMimeType);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.label;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.language;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.sampleMimeType;
        return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "TextTrack(id=" + this.id + ", label=" + this.label + ", language=" + this.language + ", sampleMimeType=" + this.sampleMimeType + ')';
    }

    public TextTrack(String str, String str2, String str3, String str4) {
        this.id = str;
        this.label = str2;
        this.language = str3;
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

    @Override // com.lagradost.cloudstream3.ui.player.Track
    public String getSampleMimeType() {
        return this.sampleMimeType;
    }
}
