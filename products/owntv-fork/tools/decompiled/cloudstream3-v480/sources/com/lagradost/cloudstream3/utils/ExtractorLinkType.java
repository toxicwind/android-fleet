package com.lagradost.cloudstream3.utils;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: ExtractorApi.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "", "<init>", "(Ljava/lang/String;I)V", "VIDEO", "M3U8", "DASH", "TORRENT", "MAGNET", "getMimeType", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum ExtractorLinkType {
    VIDEO,
    M3U8,
    DASH,
    TORRENT,
    MAGNET;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ExtractorLinkType.values().length];
            try {
                iArr[ExtractorLinkType.VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ExtractorLinkType.M3U8.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ExtractorLinkType.DASH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ExtractorLinkType.TORRENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ExtractorLinkType.MAGNET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<ExtractorLinkType> getEntries() {
        return $ENTRIES;
    }

    public final String getMimeType() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return "video/mp4";
        }
        if (i == 2) {
            return "application/x-mpegURL";
        }
        if (i == 3) {
            return "application/dash+xml";
        }
        if (i == 4 || i == 5) {
            return "application/x-bittorrent";
        }
        throw new NoWhenBranchMatchedException();
    }
}
