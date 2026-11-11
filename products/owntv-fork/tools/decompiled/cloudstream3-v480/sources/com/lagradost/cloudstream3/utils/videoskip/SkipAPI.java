package com.lagradost.cloudstream3.utils.videoskip;

import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SkipAPI.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b&\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@¢\u0006\u0002\u0010\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/SkipAPI;", "", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "supportedTypes", "", "Lcom/lagradost/cloudstream3/TvType;", "getSupportedTypes", "()Ljava/util/Set;", "stamps", "", "Lcom/lagradost/cloudstream3/utils/videoskip/SkipStamp;", "data", "Lcom/lagradost/cloudstream3/LoadResponse;", "episode", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "episodeDurationMs", "", "(Lcom/lagradost/cloudstream3/LoadResponse;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class SkipAPI {
    private final String name = "NONE";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<SkipAPI> skipApis = CollectionsKt.listOf((Object[]) new SkipAPI[]{new AniSkip(), new TheIntroDBSkip(), new IntroDbSkip(), new AnimeSkip()});
    private static final ConcurrentHashMap<Integer, List<VideoSkipStamp>> cachedStamps = new ConcurrentHashMap<>();

    public abstract Set<TvType> getSupportedTypes();

    public Object stamps(LoadResponse loadResponse, ResultEpisode resultEpisode, long j, Continuation<? super List<SkipStamp>> continuation) {
        return stamps$suspendImpl(this, loadResponse, resultEpisode, j, continuation);
    }

    public String getName() {
        return this.name;
    }

    static /* synthetic */ Object stamps$suspendImpl(SkipAPI skipAPI, LoadResponse loadResponse, ResultEpisode resultEpisode, long j, Continuation<? super List<SkipStamp>> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    /* JADX INFO: compiled from: SkipAPI.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0086@¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R&\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/SkipAPI$Companion;", "", "<init>", "()V", "Lcom/lagradost/cloudstream3/LoadResponse;", "data", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "episode", "", "episodeDurationMs", "", "hasNextEpisode", "", "Lcom/lagradost/cloudstream3/utils/videoskip/VideoSkipStamp;", "videoStamps", "(Lcom/lagradost/cloudstream3/LoadResponse;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/lagradost/cloudstream3/utils/videoskip/SkipAPI;", "skipApis", "Ljava/util/List;", "j$/util/concurrent/ConcurrentHashMap", "", "cachedStamps", "Lj$/util/concurrent/ConcurrentHashMap;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Code duplicated, block: B:20:0x0080  */
        /* JADX WARN: Code duplicated, block: B:25:0x00c7 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:26:0x00c8  */
        /* JADX WARN: Code duplicated, block: B:51:0x0095 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:53:? A[LOOP:1: B:18:0x007a->B:53:?, LOOP_END, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:7:0x0016  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00c8 -> B:27:0x00d0). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        public final java.lang.Object videoStamps(com.lagradost.cloudstream3.LoadResponse r18, com.lagradost.cloudstream3.ui.result.ResultEpisode r19, long r20, boolean r22, kotlin.coroutines.Continuation<? super java.util.List<com.lagradost.cloudstream3.utils.videoskip.VideoSkipStamp>> r23) {
            /*
                Method dump skipped, instruction units count: 312
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.videoskip.SkipAPI.Companion.videoStamps(com.lagradost.cloudstream3.LoadResponse, com.lagradost.cloudstream3.ui.result.ResultEpisode, long, boolean, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
