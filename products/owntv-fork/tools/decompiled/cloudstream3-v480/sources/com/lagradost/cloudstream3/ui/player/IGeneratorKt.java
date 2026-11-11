package com.lagradost.cloudstream3.ui.player;

import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: IGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\t\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0004\"\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0004\"\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0004¨\u0006\u000b"}, d2 = {"LOADTYPE_INAPP", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "getLOADTYPE_INAPP", "()Ljava/util/Set;", "LOADTYPE_INAPP_DOWNLOAD", "getLOADTYPE_INAPP_DOWNLOAD", "LOADTYPE_CHROMECAST", "getLOADTYPE_CHROMECAST", "LOADTYPE_ALL", "getLOADTYPE_ALL", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class IGeneratorKt {
    private static final Set<ExtractorLinkType> LOADTYPE_INAPP = SetsKt.setOf((Object[]) new ExtractorLinkType[]{ExtractorLinkType.VIDEO, ExtractorLinkType.DASH, ExtractorLinkType.M3U8, ExtractorLinkType.TORRENT, ExtractorLinkType.MAGNET});
    private static final Set<ExtractorLinkType> LOADTYPE_INAPP_DOWNLOAD = SetsKt.setOf((Object[]) new ExtractorLinkType[]{ExtractorLinkType.VIDEO, ExtractorLinkType.M3U8});
    private static final Set<ExtractorLinkType> LOADTYPE_CHROMECAST = SetsKt.setOf((Object[]) new ExtractorLinkType[]{ExtractorLinkType.VIDEO, ExtractorLinkType.DASH, ExtractorLinkType.M3U8});
    private static final Set<ExtractorLinkType> LOADTYPE_ALL = CollectionsKt.toSet(ExtractorLinkType.getEntries());

    public static final Set<ExtractorLinkType> getLOADTYPE_INAPP() {
        return LOADTYPE_INAPP;
    }

    public static final Set<ExtractorLinkType> getLOADTYPE_INAPP_DOWNLOAD() {
        return LOADTYPE_INAPP_DOWNLOAD;
    }

    public static final Set<ExtractorLinkType> getLOADTYPE_CHROMECAST() {
        return LOADTYPE_CHROMECAST;
    }

    public static final Set<ExtractorLinkType> getLOADTYPE_ALL() {
        return LOADTYPE_ALL;
    }
}
