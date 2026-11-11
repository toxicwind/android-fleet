package com.lagradost.cloudstream3;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/TrackerType;", "", "<init>", "(Ljava/lang/String;I)V", "MOVIE", "TV", "TV_SHORT", "ONA", "OVA", "SPECIAL", "MUSIC", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum TrackerType {
    MOVIE,
    TV,
    TV_SHORT,
    ONA,
    OVA,
    SPECIAL,
    MUSIC;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<TrackerType> getEntries() {
        return $ENTRIES;
    }

    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/lagradost/cloudstream3/TrackerType$Companion;", "", "<init>", "()V", "getTypes", "", "Lcom/lagradost/cloudstream3/TrackerType;", "type", "Lcom/lagradost/cloudstream3/TvType;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: MainAPI.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[TvType.values().length];
                try {
                    iArr[TvType.Movie.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[TvType.AnimeMovie.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[TvType.TvSeries.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[TvType.Anime.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[TvType.OVA.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[TvType.Others.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<TrackerType> getTypes(TvType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case 1:
                    return SetsKt.setOf(TrackerType.MOVIE);
                case 2:
                    return SetsKt.setOf(TrackerType.MOVIE);
                case 3:
                    return SetsKt.setOf((Object[]) new TrackerType[]{TrackerType.TV, TrackerType.TV_SHORT});
                case 4:
                    return SetsKt.setOf((Object[]) new TrackerType[]{TrackerType.TV, TrackerType.TV_SHORT, TrackerType.ONA, TrackerType.OVA});
                case 5:
                    return SetsKt.setOf((Object[]) new TrackerType[]{TrackerType.OVA, TrackerType.SPECIAL, TrackerType.ONA});
                case 6:
                    return SetsKt.setOf(TrackerType.MUSIC);
                default:
                    return SetsKt.emptySet();
            }
        }
    }
}
