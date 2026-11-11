package com.lagradost.cloudstream3.ui.result;

import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0003J5\u0010\u0013\u001a\u00020\u00002\u001a\b\u0002\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0006HÖ\u0081\u0004R,\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ExtractedTrailerData;", "", "mirros", "", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "", "subtitles", "Lcom/lagradost/cloudstream3/SubtitleFile;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getMirros", "()Ljava/util/List;", "setMirros", "(Ljava/util/List;)V", "getSubtitles", "setSubtitles", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ExtractedTrailerData {
    private List<? extends Pair<? extends ExtractorLink, String>> mirros;
    private List<SubtitleFile> subtitles;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ExtractedTrailerData copy$default(ExtractedTrailerData extractedTrailerData, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = extractedTrailerData.mirros;
        }
        if ((i & 2) != 0) {
            list2 = extractedTrailerData.subtitles;
        }
        return extractedTrailerData.copy(list, list2);
    }

    public final List<Pair<ExtractorLink, String>> component1() {
        return this.mirros;
    }

    public final List<SubtitleFile> component2() {
        return this.subtitles;
    }

    public final ExtractedTrailerData copy(List<? extends Pair<? extends ExtractorLink, String>> mirros, List<SubtitleFile> subtitles) {
        Intrinsics.checkNotNullParameter(mirros, "mirros");
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        return new ExtractedTrailerData(mirros, subtitles);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExtractedTrailerData)) {
            return false;
        }
        ExtractedTrailerData extractedTrailerData = (ExtractedTrailerData) other;
        return Intrinsics.areEqual(this.mirros, extractedTrailerData.mirros) && Intrinsics.areEqual(this.subtitles, extractedTrailerData.subtitles);
    }

    public int hashCode() {
        return (this.mirros.hashCode() * 31) + this.subtitles.hashCode();
    }

    public String toString() {
        return "ExtractedTrailerData(mirros=" + this.mirros + ", subtitles=" + this.subtitles + ')';
    }

    public ExtractedTrailerData(List<? extends Pair<? extends ExtractorLink, String>> mirros, List<SubtitleFile> subtitles) {
        Intrinsics.checkNotNullParameter(mirros, "mirros");
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        this.mirros = mirros;
        this.subtitles = subtitles;
    }

    public final List<Pair<ExtractorLink, String>> getMirros() {
        return this.mirros;
    }

    public final void setMirros(List<? extends Pair<? extends ExtractorLink, String>> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mirros = list;
    }

    public /* synthetic */ ExtractedTrailerData(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2);
    }

    public final List<SubtitleFile> getSubtitles() {
        return this.subtitles;
    }

    public final void setSubtitles(List<SubtitleFile> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.subtitles = list;
    }
}
