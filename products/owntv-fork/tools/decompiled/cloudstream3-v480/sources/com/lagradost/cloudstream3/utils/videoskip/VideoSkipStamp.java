package com.lagradost.cloudstream3.utils.videoskip;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UiText;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SkipAPI.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J'\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/VideoSkipStamp;", "", "timestamp", "Lcom/lagradost/cloudstream3/utils/videoskip/SkipStamp;", "skipToNextEpisode", "", "source", "", "<init>", "(Lcom/lagradost/cloudstream3/utils/videoskip/SkipStamp;ZLjava/lang/String;)V", "getTimestamp", "()Lcom/lagradost/cloudstream3/utils/videoskip/SkipStamp;", "getSkipToNextEpisode", "()Z", "getSource", "()Ljava/lang/String;", "uiText", "Lcom/lagradost/cloudstream3/utils/UiText;", "getUiText", "()Lcom/lagradost/cloudstream3/utils/UiText;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class VideoSkipStamp {
    private final boolean skipToNextEpisode;
    private final String source;
    private final SkipStamp timestamp;
    private final UiText uiText;

    public static /* synthetic */ VideoSkipStamp copy$default(VideoSkipStamp videoSkipStamp, SkipStamp skipStamp, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            skipStamp = videoSkipStamp.timestamp;
        }
        if ((i & 2) != 0) {
            z = videoSkipStamp.skipToNextEpisode;
        }
        if ((i & 4) != 0) {
            str = videoSkipStamp.source;
        }
        return videoSkipStamp.copy(skipStamp, z, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SkipStamp getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getSkipToNextEpisode() {
        return this.skipToNextEpisode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    public final VideoSkipStamp copy(SkipStamp timestamp, boolean skipToNextEpisode, String source) {
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(source, "source");
        return new VideoSkipStamp(timestamp, skipToNextEpisode, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VideoSkipStamp)) {
            return false;
        }
        VideoSkipStamp videoSkipStamp = (VideoSkipStamp) other;
        return Intrinsics.areEqual(this.timestamp, videoSkipStamp.timestamp) && this.skipToNextEpisode == videoSkipStamp.skipToNextEpisode && Intrinsics.areEqual(this.source, videoSkipStamp.source);
    }

    public int hashCode() {
        return (((this.timestamp.hashCode() * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.skipToNextEpisode)) * 31) + this.source.hashCode();
    }

    public String toString() {
        return "VideoSkipStamp(timestamp=" + this.timestamp + ", skipToNextEpisode=" + this.skipToNextEpisode + ", source=" + this.source + ')';
    }

    public VideoSkipStamp(SkipStamp timestamp, boolean z, String source) {
        UiText uiTextTxt;
        UiText uiTextTxt2;
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(source, "source");
        this.timestamp = timestamp;
        this.skipToNextEpisode = z;
        this.source = source;
        if (z) {
            uiTextTxt = TextUtilKt.txt(R.string.next_episode, new Object[0]);
        } else {
            int i = R.string.skip_type_format;
            String label = timestamp.getLabel();
            uiTextTxt = TextUtilKt.txt(i, (label == null || (uiTextTxt2 = TextUtilKt.txt(label)) == null) ? TextUtilKt.txt(timestamp.getType().getRes(), new Object[0]) : uiTextTxt2);
        }
        this.uiText = uiTextTxt;
    }

    public final SkipStamp getTimestamp() {
        return this.timestamp;
    }

    public final boolean getSkipToNextEpisode() {
        return this.skipToNextEpisode;
    }

    public final String getSource() {
        return this.source;
    }

    public final UiText getUiText() {
        return this.uiText;
    }
}
