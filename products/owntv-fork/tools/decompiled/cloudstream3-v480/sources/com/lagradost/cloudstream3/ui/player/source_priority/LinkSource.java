package com.lagradost.cloudstream3.ui.player.source_priority;

import com.lagradost.cloudstream3.utils.ExtractorLink;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QualityProfileDialog.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bJ\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/source_priority/LinkSource;", "", "source", "", "<init>", "(Ljava/lang/String;)V", "extractorLink", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Lcom/lagradost/cloudstream3/utils/ExtractorLink;)V", "getSource", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LinkSource {
    private final String source;

    public static /* synthetic */ LinkSource copy$default(LinkSource linkSource, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = linkSource.source;
        }
        return linkSource.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    public final LinkSource copy(String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new LinkSource(source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LinkSource) && Intrinsics.areEqual(this.source, ((LinkSource) other).source);
    }

    public int hashCode() {
        return this.source.hashCode();
    }

    public String toString() {
        return "LinkSource(source=" + this.source + ')';
    }

    public LinkSource(String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
    }

    public final String getSource() {
        return this.source;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LinkSource(ExtractorLink extractorLink) {
        this(extractorLink.getSource());
        Intrinsics.checkNotNullParameter(extractorLink, "extractorLink");
    }
}
