package com.lagradost.cloudstream3.extractors;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByseSX.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/PlaybackDecrypt;", "", "sources", "", "Lcom/lagradost/cloudstream3/extractors/PlaybackDecryptSource;", "<init>", "(Ljava/util/List;)V", "getSources", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PlaybackDecrypt {
    private final List<PlaybackDecryptSource> sources;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PlaybackDecrypt copy$default(PlaybackDecrypt playbackDecrypt, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = playbackDecrypt.sources;
        }
        return playbackDecrypt.copy(list);
    }

    public final List<PlaybackDecryptSource> component1() {
        return this.sources;
    }

    public final PlaybackDecrypt copy(List<PlaybackDecryptSource> sources) {
        Intrinsics.checkNotNullParameter(sources, "sources");
        return new PlaybackDecrypt(sources);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PlaybackDecrypt) && Intrinsics.areEqual(this.sources, ((PlaybackDecrypt) other).sources);
    }

    public int hashCode() {
        return this.sources.hashCode();
    }

    public String toString() {
        return "PlaybackDecrypt(sources=" + this.sources + ')';
    }

    public PlaybackDecrypt(List<PlaybackDecryptSource> sources) {
        Intrinsics.checkNotNullParameter(sources, "sources");
        this.sources = sources;
    }

    public final List<PlaybackDecryptSource> getSources() {
        return this.sources;
    }
}
