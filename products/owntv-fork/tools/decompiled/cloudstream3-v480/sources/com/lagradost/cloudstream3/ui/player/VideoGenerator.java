package com.lagradost.cloudstream3.ui.player;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0010H&¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0010J\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0010Jl\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\"\u0010\u001b\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001d\u0012\u0004\u0012\u00020 0\u001c2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020 0\u001c2\u0006\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\nH¦@¢\u0006\u0002\u0010%R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006&"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/VideoGenerator;", ExifInterface.GPS_DIRECTION_TRUE, "", "videos", "", "<init>", "(Ljava/util/List;)V", "getVideos", "()Ljava/util/List;", "hasCache", "", "getHasCache", "()Z", "canSkipLoading", "getCanSkipLoading", "getId", "", "index", "(I)Ljava/lang/Integer;", "hasNext", "videoIndex", "hasPrev", "generateLinks", "clearCache", "sourceTypes", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "callback", "Lkotlin/Function1;", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "", "subtitleCallback", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", TypedValues.CycleType.S_WAVE_OFFSET, "isCasting", "(ZLjava/util/Set;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class VideoGenerator<T> {
    private final List<T> videos;

    public abstract Object generateLinks(boolean z, Set<? extends ExtractorLinkType> set, Function1<? super Pair<? extends ExtractorLink, ExtractorUri>, Unit> function1, Function1<? super SubtitleData, Unit> function2, int i, boolean z2, Continuation<? super Boolean> continuation);

    public abstract boolean getCanSkipLoading();

    public abstract boolean getHasCache();

    public abstract Integer getId(int index);

    public final boolean hasPrev(int videoIndex) {
        return videoIndex > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public VideoGenerator(List<? extends T> videos) {
        Intrinsics.checkNotNullParameter(videos, "videos");
        this.videos = videos;
    }

    public final List<T> getVideos() {
        return this.videos;
    }

    public final boolean hasNext(int videoIndex) {
        return videoIndex < CollectionsKt.getLastIndex(this.videos);
    }
}
