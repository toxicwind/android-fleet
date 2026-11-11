package com.lagradost.cloudstream3.actions.temp;

import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: MpvPackage.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/MpvYTDLPackage;", "Lcom/lagradost/cloudstream3/actions/temp/MpvPackage;", "<init>", "()V", "sourceTypes", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "getSourceTypes", "()Ljava/util/Set;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MpvYTDLPackage extends MpvPackage {
    private final Set<ExtractorLinkType> sourceTypes;

    public MpvYTDLPackage() {
        super("MPV YTDL", "is.xyz.mpv.ytdl", null, 4, null);
        this.sourceTypes = SetsKt.setOf((Object[]) new ExtractorLinkType[]{ExtractorLinkType.VIDEO, ExtractorLinkType.DASH, ExtractorLinkType.M3U8});
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public Set<ExtractorLinkType> getSourceTypes() {
        return this.sourceTypes;
    }
}
