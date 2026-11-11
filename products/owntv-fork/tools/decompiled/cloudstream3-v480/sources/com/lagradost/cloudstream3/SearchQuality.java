package com.lagradost.cloudstream3;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0013\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/SearchQuality;", "", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(Ljava/lang/String;ILjava/lang/Integer;)V", "Cam", "CamRip", "HdCam", "Telesync", "WorkPrint", "Telecine", "HQ", "HD", "HDR", "BlueRay", "DVD", "SD", "FourK", "UHD", "SDR", "WebRip", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum SearchQuality {
    Cam(1),
    CamRip(2),
    HdCam(3),
    Telesync(4),
    WorkPrint(5),
    Telecine(6),
    HQ(7),
    HD(8),
    HDR(9),
    BlueRay(10),
    DVD(11),
    SD(12),
    FourK(13),
    UHD(14),
    SDR(15),
    WebRip(16);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<SearchQuality> getEntries() {
        return $ENTRIES;
    }

    SearchQuality(Integer num) {
    }
}
