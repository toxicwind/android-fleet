package com.lagradost.cloudstream3.ui.result;

import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\"\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t`\n¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J%\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t`\nHÆ\u0003JO\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032$\b\u0002\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t`\nHÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\tHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR-\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t`\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;", "", CloudStreamPackage.LINKS_EXTRA, "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", CloudStreamPackage.SUBTITLE_EXTRA, "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "syncData", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/HashMap;)V", "getLinks", "()Ljava/util/List;", "getSubs", "getSyncData", "()Ljava/util/HashMap;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LinkLoadingResult {
    private final List<ExtractorLink> links;
    private final List<SubtitleData> subs;
    private final HashMap<String, String> syncData;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LinkLoadingResult copy$default(LinkLoadingResult linkLoadingResult, List list, List list2, HashMap map, int i, Object obj) {
        if ((i & 1) != 0) {
            list = linkLoadingResult.links;
        }
        if ((i & 2) != 0) {
            list2 = linkLoadingResult.subs;
        }
        if ((i & 4) != 0) {
            map = linkLoadingResult.syncData;
        }
        return linkLoadingResult.copy(list, list2, map);
    }

    public final List<ExtractorLink> component1() {
        return this.links;
    }

    public final List<SubtitleData> component2() {
        return this.subs;
    }

    public final HashMap<String, String> component3() {
        return this.syncData;
    }

    public final LinkLoadingResult copy(List<? extends ExtractorLink> links, List<SubtitleData> subs, HashMap<String, String> syncData) {
        Intrinsics.checkNotNullParameter(links, "links");
        Intrinsics.checkNotNullParameter(subs, "subs");
        Intrinsics.checkNotNullParameter(syncData, "syncData");
        return new LinkLoadingResult(links, subs, syncData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LinkLoadingResult)) {
            return false;
        }
        LinkLoadingResult linkLoadingResult = (LinkLoadingResult) other;
        return Intrinsics.areEqual(this.links, linkLoadingResult.links) && Intrinsics.areEqual(this.subs, linkLoadingResult.subs) && Intrinsics.areEqual(this.syncData, linkLoadingResult.syncData);
    }

    public int hashCode() {
        return (((this.links.hashCode() * 31) + this.subs.hashCode()) * 31) + this.syncData.hashCode();
    }

    public String toString() {
        return "LinkLoadingResult(links=" + this.links + ", subs=" + this.subs + ", syncData=" + this.syncData + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LinkLoadingResult(List<? extends ExtractorLink> links, List<SubtitleData> subs, HashMap<String, String> syncData) {
        Intrinsics.checkNotNullParameter(links, "links");
        Intrinsics.checkNotNullParameter(subs, "subs");
        Intrinsics.checkNotNullParameter(syncData, "syncData");
        this.links = links;
        this.subs = subs;
        this.syncData = syncData;
    }

    public final List<ExtractorLink> getLinks() {
        return this.links;
    }

    public final List<SubtitleData> getSubs() {
        return this.subs;
    }

    public final HashMap<String, String> getSyncData() {
        return this.syncData;
    }
}
