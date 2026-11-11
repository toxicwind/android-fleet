package kotlin.reflect;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: KVariance.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/reflect/KVariance;", "", "<init>", "(Ljava/lang/String;I)V", "INVARIANT", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN, HlsMediaPlaylist.Interstitial.SNAP_TYPE_OUT, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum KVariance {
    INVARIANT,
    IN,
    OUT;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<KVariance> getEntries() {
        return $ENTRIES;
    }
}
