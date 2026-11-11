package com.lagradost.cloudstream3.ui.player;

import kotlin.Metadata;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0014\u0010\n\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/Track;", "", "id", "", "getId", "()Ljava/lang/String;", "label", "getLabel", "language", "getLanguage", "sampleMimeType", "getSampleMimeType", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface Track {
    String getId();

    String getLabel();

    String getLanguage();

    String getSampleMimeType();
}
