package com.lagradost.cloudstream3.ui.player;

import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.videoskip.VideoSkipStamp;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;
import kotlinx.collections.immutable.PersistentSet;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: PlayerGeneratorViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u001e\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bo\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\"\b\u0002\u0010\t\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005j\u0002`\b0\u0002\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u000e¢\u0006\u0004\b\u0016\u0010\u0017J1\u0010\u001a\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005j\u0002`\b0\u00192\u0006\u0010\u0018\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ+\u0010\u001d\u001a\u00020\u00002\u001a\u0010\u001c\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005j\u0002`\bH\u0007¢\u0006\u0004\b\u001d\u0010\u001fJ\u0017\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u001d\u0010 J\u001d\u0010\u001d\u001a\u00020\u00002\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030!H\u0007¢\u0006\u0004\b#\u0010$J1\u0010\u001d\u001a\u00020\u00002 \u0010\"\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005j\u0002`\b0!H\u0007¢\u0006\u0004\b%\u0010$J\u001d\u0010\u001d\u001a\u00020\u00002\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000b0!H\u0007¢\u0006\u0004\b&\u0010$J\u0017\u0010'\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0003H\u0007¢\u0006\u0004\b'\u0010\u001eJ+\u0010'\u001a\u00020\u00002\u001a\u0010\u001c\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005j\u0002`\bH\u0007¢\u0006\u0004\b'\u0010\u001fJ\u0017\u0010'\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u000bH\u0007¢\u0006\u0004\b'\u0010 J\u001d\u0010'\u001a\u00020\u00002\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030!H\u0007¢\u0006\u0004\b(\u0010$J1\u0010'\u001a\u00020\u00002 \u0010\"\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005j\u0002`\b0!H\u0007¢\u0006\u0004\b)\u0010$J\u001d\u0010'\u001a\u00020\u00002\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000b0!H\u0007¢\u0006\u0004\b*\u0010$J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0003¢\u0006\u0004\b+\u0010,J*\u0010-\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005j\u0002`\b0\u0002HÆ\u0003¢\u0006\u0004\b-\u0010,J\u0016\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003¢\u0006\u0004\b.\u0010/J\u0016\u00100\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003¢\u0006\u0004\b0\u00101J\u0012\u00102\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0004\b2\u00103J\u0010\u00104\u001a\u00020\u0012HÆ\u0003¢\u0006\u0004\b4\u00105Jz\u00106\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\"\b\u0002\u0010\t\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005j\u0002`\b0\u00022\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0012HÆ\u0001¢\u0006\u0004\b6\u00107J\u0010\u00109\u001a\u000208HÖ\u0001¢\u0006\u0004\b9\u0010:J\u0010\u0010;\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b;\u00105J\u001a\u0010>\u001a\u00020=2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b>\u0010?R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010@\u001a\u0004\bA\u0010,R1\u0010\t\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005j\u0002`\b0\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010@\u001a\u0004\bB\u0010,R\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006¢\u0006\f\n\u0004\b\f\u0010C\u001a\u0004\bD\u0010/R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010E\u001a\u0004\bF\u00101R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010G\u001a\u0004\bH\u00103R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010I\u001a\u0004\bJ\u00105R:\u0010L\u001a(\u0012\u0004\u0012\u00020\u0012\u0012\u001e\u0012\u001c\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005j\u0002`\b0\u00190K8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010M¨\u0006N"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/VideoState;", "", "Lkotlinx/collections/immutable/PersistentSet;", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "subtitles", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "Lcom/lagradost/cloudstream3/ui/player/VideoLink;", CloudStreamPackage.LINKS_EXTRA, "Lkotlinx/collections/immutable/PersistentList;", "Lcom/lagradost/cloudstream3/utils/videoskip/VideoSkipStamp;", "stamps", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "", "loading", "Lcom/lagradost/cloudstream3/ui/player/GeneratorState;", "generatorState", "", "instance", "<init>", "(Lkotlinx/collections/immutable/PersistentSet;Lkotlinx/collections/immutable/PersistentSet;Lkotlinx/collections/immutable/PersistentList;Lcom/lagradost/cloudstream3/mvvm/Resource;Lcom/lagradost/cloudstream3/ui/player/GeneratorState;I)V", "clearSortedLinksCache", "()V", "qualityProfile", "", "sortLinks", "(I)Ljava/util/List;", "item", "add", "(Lcom/lagradost/cloudstream3/ui/player/SubtitleData;)Lcom/lagradost/cloudstream3/ui/player/VideoState;", "(Lkotlin/Pair;)Lcom/lagradost/cloudstream3/ui/player/VideoState;", "(Lcom/lagradost/cloudstream3/utils/videoskip/VideoSkipStamp;)Lcom/lagradost/cloudstream3/ui/player/VideoState;", "", "items", "addSubtitleData", "(Ljava/util/Collection;)Lcom/lagradost/cloudstream3/ui/player/VideoState;", "addVideoLink", "addVideoSkipStamp", "set", "setSubtitleData", "setVideoLink", "setVideoSkipStamp", "component1", "()Lkotlinx/collections/immutable/PersistentSet;", "component2", "component3", "()Lkotlinx/collections/immutable/PersistentList;", "component4", "()Lcom/lagradost/cloudstream3/mvvm/Resource;", "component5", "()Lcom/lagradost/cloudstream3/ui/player/GeneratorState;", "component6", "()I", "copy", "(Lkotlinx/collections/immutable/PersistentSet;Lkotlinx/collections/immutable/PersistentSet;Lkotlinx/collections/immutable/PersistentList;Lcom/lagradost/cloudstream3/mvvm/Resource;Lcom/lagradost/cloudstream3/ui/player/GeneratorState;I)Lcom/lagradost/cloudstream3/ui/player/VideoState;", "", "toString", "()Ljava/lang/String;", "hashCode", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "Lkotlinx/collections/immutable/PersistentSet;", "getSubtitles", "getLinks", "Lkotlinx/collections/immutable/PersistentList;", "getStamps", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "getLoading", "Lcom/lagradost/cloudstream3/ui/player/GeneratorState;", "getGeneratorState", "I", "getInstance", "j$/util/concurrent/ConcurrentHashMap", "sortedLinks", "Lj$/util/concurrent/ConcurrentHashMap;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class VideoState {
    private final GeneratorState generatorState;
    private final int instance;
    private final PersistentSet<Pair<ExtractorLink, ExtractorUri>> links;
    private final Resource<Unit> loading;
    private final ConcurrentHashMap<Integer, List<Pair<ExtractorLink, ExtractorUri>>> sortedLinks;
    private final PersistentList<VideoSkipStamp> stamps;
    private final PersistentSet<SubtitleData> subtitles;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VideoState copy$default(VideoState videoState, PersistentSet persistentSet, PersistentSet persistentSet2, PersistentList persistentList, Resource resource, GeneratorState generatorState, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            persistentSet = videoState.subtitles;
        }
        if ((i2 & 2) != 0) {
            persistentSet2 = videoState.links;
        }
        if ((i2 & 4) != 0) {
            persistentList = videoState.stamps;
        }
        if ((i2 & 8) != 0) {
            resource = videoState.loading;
        }
        if ((i2 & 16) != 0) {
            generatorState = videoState.generatorState;
        }
        if ((i2 & 32) != 0) {
            i = videoState.instance;
        }
        GeneratorState generatorState2 = generatorState;
        int i3 = i;
        return videoState.copy(persistentSet, persistentSet2, persistentList, resource, generatorState2, i3);
    }

    public final PersistentSet<SubtitleData> component1() {
        return this.subtitles;
    }

    public final PersistentSet<Pair<ExtractorLink, ExtractorUri>> component2() {
        return this.links;
    }

    public final PersistentList<VideoSkipStamp> component3() {
        return this.stamps;
    }

    public final Resource<Unit> component4() {
        return this.loading;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final GeneratorState getGeneratorState() {
        return this.generatorState;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getInstance() {
        return this.instance;
    }

    public final VideoState copy(PersistentSet<SubtitleData> subtitles, PersistentSet<? extends Pair<? extends ExtractorLink, ExtractorUri>> links, PersistentList<VideoSkipStamp> stamps, Resource<Unit> loading, GeneratorState generatorState, int instance) {
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        Intrinsics.checkNotNullParameter(links, "links");
        Intrinsics.checkNotNullParameter(stamps, "stamps");
        Intrinsics.checkNotNullParameter(loading, "loading");
        return new VideoState(subtitles, links, stamps, loading, generatorState, instance);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VideoState)) {
            return false;
        }
        VideoState videoState = (VideoState) other;
        return Intrinsics.areEqual(this.subtitles, videoState.subtitles) && Intrinsics.areEqual(this.links, videoState.links) && Intrinsics.areEqual(this.stamps, videoState.stamps) && Intrinsics.areEqual(this.loading, videoState.loading) && Intrinsics.areEqual(this.generatorState, videoState.generatorState) && this.instance == videoState.instance;
    }

    public int hashCode() {
        int iHashCode = ((((((this.subtitles.hashCode() * 31) + this.links.hashCode()) * 31) + this.stamps.hashCode()) * 31) + this.loading.hashCode()) * 31;
        GeneratorState generatorState = this.generatorState;
        return ((iHashCode + (generatorState == null ? 0 : generatorState.hashCode())) * 31) + this.instance;
    }

    public String toString() {
        return "VideoState(subtitles=" + this.subtitles + ", links=" + this.links + ", stamps=" + this.stamps + ", loading=" + this.loading + ", generatorState=" + this.generatorState + ", instance=" + this.instance + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public VideoState(PersistentSet<SubtitleData> subtitles, PersistentSet<? extends Pair<? extends ExtractorLink, ExtractorUri>> links, PersistentList<VideoSkipStamp> stamps, Resource<Unit> loading, GeneratorState generatorState, int i) {
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        Intrinsics.checkNotNullParameter(links, "links");
        Intrinsics.checkNotNullParameter(stamps, "stamps");
        Intrinsics.checkNotNullParameter(loading, "loading");
        this.subtitles = subtitles;
        this.links = links;
        this.stamps = stamps;
        this.loading = loading;
        this.generatorState = generatorState;
        this.instance = i;
        this.sortedLinks = new ConcurrentHashMap<>();
    }

    public /* synthetic */ VideoState(PersistentSet persistentSet, PersistentSet persistentSet2, PersistentList persistentList, Resource.Loading loading, GeneratorState generatorState, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? ExtensionsKt.persistentSetOf() : persistentSet, (i2 & 2) != 0 ? ExtensionsKt.persistentSetOf() : persistentSet2, (i2 & 4) != 0 ? ExtensionsKt.persistentListOf() : persistentList, (i2 & 8) != 0 ? new Resource.Loading(null, 1, null) : loading, (i2 & 16) != 0 ? null : generatorState, i);
    }

    public final PersistentSet<SubtitleData> getSubtitles() {
        return this.subtitles;
    }

    public final PersistentSet<Pair<ExtractorLink, ExtractorUri>> getLinks() {
        return this.links;
    }

    public final PersistentList<VideoSkipStamp> getStamps() {
        return this.stamps;
    }

    public final Resource<Unit> getLoading() {
        return this.loading;
    }

    public final GeneratorState getGeneratorState() {
        return this.generatorState;
    }

    public final int getInstance() {
        return this.instance;
    }

    public final void clearSortedLinksCache() {
        this.sortedLinks.clear();
    }

    public final List<Pair<ExtractorLink, ExtractorUri>> sortLinks(final int qualityProfile) {
        List<Pair<ExtractorLink, ExtractorUri>> list = this.sortedLinks.get(Integer.valueOf(qualityProfile));
        if (list != null) {
            return list;
        }
        List<Pair<ExtractorLink, ExtractorUri>> listSortedWith = CollectionsKt.sortedWith(this.links, new Comparator() { // from class: com.lagradost.cloudstream3.ui.player.VideoState$sortLinks$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(-QualityDataHelper.INSTANCE.getLinkPriority(qualityProfile, (ExtractorLink) ((Pair) t).getFirst())), Integer.valueOf(-QualityDataHelper.INSTANCE.getLinkPriority(qualityProfile, (ExtractorLink) ((Pair) t2).getFirst())));
            }
        });
        this.sortedLinks.put(Integer.valueOf(qualityProfile), listSortedWith);
        return listSortedWith;
    }

    public final VideoState add(SubtitleData item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return copy$default(this, this.subtitles.add(item), null, null, null, null, 0, 62, null);
    }

    public final VideoState add(Pair<? extends ExtractorLink, ExtractorUri> item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return copy$default(this, null, this.links.add(item), null, null, null, 0, 61, null);
    }

    public final VideoState add(VideoSkipStamp item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return copy$default(this, null, null, this.stamps.add(item), null, null, 0, 59, null);
    }

    public final VideoState addSubtitleData(Collection<SubtitleData> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        return copy$default(this, this.subtitles.addAll((Collection<? extends SubtitleData>) items), null, null, null, null, 0, 62, null);
    }

    public final VideoState addVideoLink(Collection<? extends Pair<? extends ExtractorLink, ExtractorUri>> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        return copy$default(this, null, this.links.addAll((Collection<? extends Pair<ExtractorLink, ExtractorUri>>) items), null, null, null, 0, 61, null);
    }

    public final VideoState addVideoSkipStamp(Collection<VideoSkipStamp> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        return copy$default(this, null, null, this.stamps.addAll((Collection<? extends VideoSkipStamp>) items), null, null, 0, 59, null);
    }

    public final VideoState set(SubtitleData item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return copy$default(this, ExtensionsKt.persistentSetOf(item), null, null, null, null, 0, 62, null);
    }

    public final VideoState set(Pair<? extends ExtractorLink, ExtractorUri> item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return copy$default(this, null, ExtensionsKt.persistentSetOf(item), null, null, null, 0, 61, null);
    }

    public final VideoState set(VideoSkipStamp item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return copy$default(this, null, null, ExtensionsKt.persistentListOf(item), null, null, 0, 59, null);
    }

    public final VideoState setSubtitleData(Collection<SubtitleData> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        return copy$default(this, ExtensionsKt.toPersistentSet(items), null, null, null, null, 0, 62, null);
    }

    public final VideoState setVideoLink(Collection<? extends Pair<? extends ExtractorLink, ExtractorUri>> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        return copy$default(this, null, ExtensionsKt.toPersistentSet(items), null, null, null, 0, 61, null);
    }

    public final VideoState setVideoSkipStamp(Collection<VideoSkipStamp> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        return copy$default(this, null, null, ExtensionsKt.toPersistentList(items), null, null, 0, 59, null);
    }
}
