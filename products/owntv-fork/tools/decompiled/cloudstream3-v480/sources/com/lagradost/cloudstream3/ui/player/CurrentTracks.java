package com.lagradost.cloudstream3.ui.player;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\f\u0010\rJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003Ja\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004J\n\u0010#\u001a\u00020$HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/CurrentTracks;", "", "currentVideoTrack", "Lcom/lagradost/cloudstream3/ui/player/VideoTrack;", "currentAudioTrack", "Lcom/lagradost/cloudstream3/ui/player/AudioTrack;", "currentTextTracks", "", "Lcom/lagradost/cloudstream3/ui/player/TextTrack;", "allVideoTracks", "allAudioTracks", "allTextTracks", "<init>", "(Lcom/lagradost/cloudstream3/ui/player/VideoTrack;Lcom/lagradost/cloudstream3/ui/player/AudioTrack;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getCurrentVideoTrack", "()Lcom/lagradost/cloudstream3/ui/player/VideoTrack;", "getCurrentAudioTrack", "()Lcom/lagradost/cloudstream3/ui/player/AudioTrack;", "getCurrentTextTracks", "()Ljava/util/List;", "getAllVideoTracks", "getAllAudioTracks", "getAllTextTracks", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CurrentTracks {
    private final List<AudioTrack> allAudioTracks;
    private final List<TextTrack> allTextTracks;
    private final List<VideoTrack> allVideoTracks;
    private final AudioTrack currentAudioTrack;
    private final List<TextTrack> currentTextTracks;
    private final VideoTrack currentVideoTrack;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CurrentTracks copy$default(CurrentTracks currentTracks, VideoTrack videoTrack, AudioTrack audioTrack, List list, List list2, List list3, List list4, int i, Object obj) {
        if ((i & 1) != 0) {
            videoTrack = currentTracks.currentVideoTrack;
        }
        if ((i & 2) != 0) {
            audioTrack = currentTracks.currentAudioTrack;
        }
        if ((i & 4) != 0) {
            list = currentTracks.currentTextTracks;
        }
        if ((i & 8) != 0) {
            list2 = currentTracks.allVideoTracks;
        }
        if ((i & 16) != 0) {
            list3 = currentTracks.allAudioTracks;
        }
        if ((i & 32) != 0) {
            list4 = currentTracks.allTextTracks;
        }
        List list5 = list3;
        List list6 = list4;
        return currentTracks.copy(videoTrack, audioTrack, list, list2, list5, list6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final VideoTrack getCurrentVideoTrack() {
        return this.currentVideoTrack;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final AudioTrack getCurrentAudioTrack() {
        return this.currentAudioTrack;
    }

    public final List<TextTrack> component3() {
        return this.currentTextTracks;
    }

    public final List<VideoTrack> component4() {
        return this.allVideoTracks;
    }

    public final List<AudioTrack> component5() {
        return this.allAudioTracks;
    }

    public final List<TextTrack> component6() {
        return this.allTextTracks;
    }

    public final CurrentTracks copy(VideoTrack currentVideoTrack, AudioTrack currentAudioTrack, List<TextTrack> currentTextTracks, List<VideoTrack> allVideoTracks, List<AudioTrack> allAudioTracks, List<TextTrack> allTextTracks) {
        Intrinsics.checkNotNullParameter(currentTextTracks, "currentTextTracks");
        Intrinsics.checkNotNullParameter(allVideoTracks, "allVideoTracks");
        Intrinsics.checkNotNullParameter(allAudioTracks, "allAudioTracks");
        Intrinsics.checkNotNullParameter(allTextTracks, "allTextTracks");
        return new CurrentTracks(currentVideoTrack, currentAudioTrack, currentTextTracks, allVideoTracks, allAudioTracks, allTextTracks);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CurrentTracks)) {
            return false;
        }
        CurrentTracks currentTracks = (CurrentTracks) other;
        return Intrinsics.areEqual(this.currentVideoTrack, currentTracks.currentVideoTrack) && Intrinsics.areEqual(this.currentAudioTrack, currentTracks.currentAudioTrack) && Intrinsics.areEqual(this.currentTextTracks, currentTracks.currentTextTracks) && Intrinsics.areEqual(this.allVideoTracks, currentTracks.allVideoTracks) && Intrinsics.areEqual(this.allAudioTracks, currentTracks.allAudioTracks) && Intrinsics.areEqual(this.allTextTracks, currentTracks.allTextTracks);
    }

    public int hashCode() {
        VideoTrack videoTrack = this.currentVideoTrack;
        int iHashCode = (videoTrack == null ? 0 : videoTrack.hashCode()) * 31;
        AudioTrack audioTrack = this.currentAudioTrack;
        return ((((((((iHashCode + (audioTrack != null ? audioTrack.hashCode() : 0)) * 31) + this.currentTextTracks.hashCode()) * 31) + this.allVideoTracks.hashCode()) * 31) + this.allAudioTracks.hashCode()) * 31) + this.allTextTracks.hashCode();
    }

    public String toString() {
        return "CurrentTracks(currentVideoTrack=" + this.currentVideoTrack + ", currentAudioTrack=" + this.currentAudioTrack + ", currentTextTracks=" + this.currentTextTracks + ", allVideoTracks=" + this.allVideoTracks + ", allAudioTracks=" + this.allAudioTracks + ", allTextTracks=" + this.allTextTracks + ')';
    }

    public CurrentTracks(VideoTrack videoTrack, AudioTrack audioTrack, List<TextTrack> currentTextTracks, List<VideoTrack> allVideoTracks, List<AudioTrack> allAudioTracks, List<TextTrack> allTextTracks) {
        Intrinsics.checkNotNullParameter(currentTextTracks, "currentTextTracks");
        Intrinsics.checkNotNullParameter(allVideoTracks, "allVideoTracks");
        Intrinsics.checkNotNullParameter(allAudioTracks, "allAudioTracks");
        Intrinsics.checkNotNullParameter(allTextTracks, "allTextTracks");
        this.currentVideoTrack = videoTrack;
        this.currentAudioTrack = audioTrack;
        this.currentTextTracks = currentTextTracks;
        this.allVideoTracks = allVideoTracks;
        this.allAudioTracks = allAudioTracks;
        this.allTextTracks = allTextTracks;
    }

    public final VideoTrack getCurrentVideoTrack() {
        return this.currentVideoTrack;
    }

    public final AudioTrack getCurrentAudioTrack() {
        return this.currentAudioTrack;
    }

    public final List<TextTrack> getCurrentTextTracks() {
        return this.currentTextTracks;
    }

    public final List<VideoTrack> getAllVideoTracks() {
        return this.allVideoTracks;
    }

    public final List<AudioTrack> getAllAudioTracks() {
        return this.allAudioTracks;
    }

    public final List<TextTrack> getAllTextTracks() {
        return this.allTextTracks;
    }
}
