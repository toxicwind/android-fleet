package com.lagradost.cloudstream3.ui;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.HomePageResponse;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.ProviderType;
import com.lagradost.cloudstream3.SearchResponseList;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.VPNStatus;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.utils.AtomicMutableList;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.TimeoutKt;
import okhttp3.internal.url._UrlKt;
import org.mozilla.classfile.ByteCode;

/* JADX INFO: compiled from: APIRepository.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 @2\u00020\u0001:\u0001@B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001c\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010'\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u0010(J$\u0010)\u001a\b\u0012\u0004\u0012\u00020*0%2\u0006\u0010+\u001a\u00020\u00142\u0006\u0010,\u001a\u00020-H\u0086@¢\u0006\u0002\u0010.J\u001c\u0010/\u001a\b\u0012\u0004\u0012\u00020*0%2\u0006\u0010+\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u0010(J\u000e\u00100\u001a\u00020\tH\u0086@¢\u0006\u0002\u00101J0\u0010\u001c\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001a0%2\u0006\u0010,\u001a\u00020-2\n\b\u0002\u00103\u001a\u0004\u0018\u00010-H\u0086@¢\u0006\u0002\u00104J\u0018\u00105\u001a\u00020\t2\b\u00106\u001a\u0004\u0018\u00010\u0014H\u0086@¢\u0006\u0002\u0010(JF\u00107\u001a\u00020\u000b2\u0006\u00108\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u000b2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\t0;2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020\t0;H\u0086@¢\u0006\u0002\u0010?R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u000eR\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u0006A"}, d2 = {"Lcom/lagradost/cloudstream3/ui/APIRepository;", "", "api", "Lcom/lagradost/cloudstream3/MainAPI;", "<init>", "(Lcom/lagradost/cloudstream3/MainAPI;)V", "getApi", "()Lcom/lagradost/cloudstream3/MainAPI;", "afterPluginsLoaded", "", "forceReload", "", "hasMainPage", "getHasMainPage", "()Z", "providerType", "Lcom/lagradost/cloudstream3/ProviderType;", "getProviderType", "()Lcom/lagradost/cloudstream3/ProviderType;", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "mainPage", "", "Lcom/lagradost/cloudstream3/MainPageData;", "getMainPage", "()Ljava/util/List;", "hasQuickSearch", "getHasQuickSearch", "vpnStatus", "Lcom/lagradost/cloudstream3/VPNStatus;", "getVpnStatus", "()Lcom/lagradost/cloudstream3/VPNStatus;", "load", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "Lcom/lagradost/cloudstream3/LoadResponse;", ImagesContract.URL, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "Lcom/lagradost/cloudstream3/SearchResponseList;", SearchIntents.EXTRA_QUERY, "page", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "quickSearch", "waitForHomeDelay", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/lagradost/cloudstream3/HomePageResponse;", "nameIndex", "(ILjava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "extractorVerifierJob", "extractorData", "loadLinks", "data", "isCasting", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class APIRepository {
    public static final int CACHE_SIZE = 20;
    private static final long DEFAULT_TIMEOUT = 120000;
    private static final long MAX_TIMEOUT = 480000;
    private static final long MIN_TIMEOUT = 5000;
    private static int cacheIndex;
    private final MainAPI api;
    private final boolean hasMainPage;
    private final boolean hasQuickSearch;
    private final List<MainPageData> mainPage;
    private final String mainUrl;
    private final String name;
    private final ProviderType providerType;
    private final VPNStatus vpnStatus;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static HashSet<DubStatus> dubStatusActive = new HashSet<>();
    private static final MainAPI noneApi = new MainAPI() { // from class: com.lagradost.cloudstream3.ui.APIRepository$Companion$noneApi$1
        private String name = "None";
        private final Set<TvType> supportedTypes = SetsKt.emptySet();
        private String lang = "";

        @Override // com.lagradost.cloudstream3.MainAPI
        public String getName() {
            return this.name;
        }

        @Override // com.lagradost.cloudstream3.MainAPI
        public void setName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.name = str;
        }

        @Override // com.lagradost.cloudstream3.MainAPI
        public Set<TvType> getSupportedTypes() {
            return this.supportedTypes;
        }

        @Override // com.lagradost.cloudstream3.MainAPI
        public String getLang() {
            return this.lang;
        }

        @Override // com.lagradost.cloudstream3.MainAPI
        public void setLang(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.lang = str;
        }
    };
    private static final MainAPI randomApi = new MainAPI() { // from class: com.lagradost.cloudstream3.ui.APIRepository$Companion$randomApi$1
        private String name = "Random";
        private final Set<TvType> supportedTypes = SetsKt.emptySet();
        private String lang = "";

        @Override // com.lagradost.cloudstream3.MainAPI
        public String getName() {
            return this.name;
        }

        @Override // com.lagradost.cloudstream3.MainAPI
        public void setName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.name = str;
        }

        @Override // com.lagradost.cloudstream3.MainAPI
        public Set<TvType> getSupportedTypes() {
            return this.supportedTypes;
        }

        @Override // com.lagradost.cloudstream3.MainAPI
        public String getLang() {
            return this.lang;
        }

        @Override // com.lagradost.cloudstream3.MainAPI
        public void setLang(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.lang = str;
        }
    };
    private static final AtomicMutableList<Companion.SavedLoadResponse> cache = Coroutines.INSTANCE.atomicListOf(new Companion.SavedLoadResponse[0]);

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.APIRepository$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: APIRepository.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.APIRepository", f = "APIRepository.kt", i = {0, 0, 0, 0}, l = {212}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "isCasting"}, nl = {215}, s = {"L$0", "L$1", "L$2", "Z$0"}, v = 2)
    static final class C08231 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C08231(Continuation<? super C08231> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return APIRepository.this.loadLinks(null, false, null, null, this);
        }
    }

    /* JADX INFO: compiled from: APIRepository.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001#B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0015\u0010 \u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\"R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/ui/APIRepository$Companion;", "", "<init>", "()V", "DEFAULT_TIMEOUT", "", "MAX_TIMEOUT", "MIN_TIMEOUT", "dubStatusActive", "Ljava/util/HashSet;", "Lcom/lagradost/cloudstream3/DubStatus;", "Lkotlin/collections/HashSet;", "getDubStatusActive", "()Ljava/util/HashSet;", "setDubStatusActive", "(Ljava/util/HashSet;)V", "noneApi", "Lcom/lagradost/cloudstream3/MainAPI;", "getNoneApi", "()Lcom/lagradost/cloudstream3/MainAPI;", "randomApi", "getRandomApi", "isInvalidData", "", "data", "", "cache", "Lcom/lagradost/cloudstream3/utils/AtomicMutableList;", "Lcom/lagradost/cloudstream3/ui/APIRepository$Companion$SavedLoadResponse;", "cacheIndex", "", "CACHE_SIZE", "getTimeout", "desired", "(Ljava/lang/Long;)J", "SavedLoadResponse", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HashSet<DubStatus> getDubStatusActive() {
            return APIRepository.dubStatusActive;
        }

        public final void setDubStatusActive(HashSet<DubStatus> hashSet) {
            Intrinsics.checkNotNullParameter(hashSet, "<set-?>");
            APIRepository.dubStatusActive = hashSet;
        }

        public final MainAPI getNoneApi() {
            return APIRepository.noneApi;
        }

        public final MainAPI getRandomApi() {
            return APIRepository.randomApi;
        }

        public final boolean isInvalidData(String data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return data.length() == 0 || Intrinsics.areEqual(data, _UrlKt.PATH_SEGMENT_ENCODE_SET_URI) || Intrinsics.areEqual(data, "about:blank");
        }

        /* JADX INFO: compiled from: APIRepository.kt */
        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/APIRepository$Companion$SavedLoadResponse;", "", "unixTime", "", "response", "Lcom/lagradost/cloudstream3/LoadResponse;", "hash", "Lkotlin/Pair;", "", "<init>", "(JLcom/lagradost/cloudstream3/LoadResponse;Lkotlin/Pair;)V", "getUnixTime", "()J", "getResponse", "()Lcom/lagradost/cloudstream3/LoadResponse;", "getHash", "()Lkotlin/Pair;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class SavedLoadResponse {
            private final Pair<String, String> hash;
            private final LoadResponse response;
            private final long unixTime;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ SavedLoadResponse copy$default(SavedLoadResponse savedLoadResponse, long j, LoadResponse loadResponse, Pair pair, int i, Object obj) {
                if ((i & 1) != 0) {
                    j = savedLoadResponse.unixTime;
                }
                if ((i & 2) != 0) {
                    loadResponse = savedLoadResponse.response;
                }
                if ((i & 4) != 0) {
                    pair = savedLoadResponse.hash;
                }
                return savedLoadResponse.copy(j, loadResponse, pair);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final long getUnixTime() {
                return this.unixTime;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final LoadResponse getResponse() {
                return this.response;
            }

            public final Pair<String, String> component3() {
                return this.hash;
            }

            public final SavedLoadResponse copy(long unixTime, LoadResponse response, Pair<String, String> hash) {
                Intrinsics.checkNotNullParameter(response, "response");
                Intrinsics.checkNotNullParameter(hash, "hash");
                return new SavedLoadResponse(unixTime, response, hash);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof SavedLoadResponse)) {
                    return false;
                }
                SavedLoadResponse savedLoadResponse = (SavedLoadResponse) other;
                return this.unixTime == savedLoadResponse.unixTime && Intrinsics.areEqual(this.response, savedLoadResponse.response) && Intrinsics.areEqual(this.hash, savedLoadResponse.hash);
            }

            public int hashCode() {
                return (((NextAiring$$ExternalSyntheticBackport0.m(this.unixTime) * 31) + this.response.hashCode()) * 31) + this.hash.hashCode();
            }

            public String toString() {
                return "SavedLoadResponse(unixTime=" + this.unixTime + ", response=" + this.response + ", hash=" + this.hash + ')';
            }

            public SavedLoadResponse(long j, LoadResponse response, Pair<String, String> hash) {
                Intrinsics.checkNotNullParameter(response, "response");
                Intrinsics.checkNotNullParameter(hash, "hash");
                this.unixTime = j;
                this.response = response;
                this.hash = hash;
            }

            public final long getUnixTime() {
                return this.unixTime;
            }

            public final LoadResponse getResponse() {
                return this.response;
            }

            public final Pair<String, String> getHash() {
                return this.hash;
            }
        }

        public final long getTimeout(Long desired) {
            return RangesKt.coerceIn(desired != null ? desired.longValue() : APIRepository.DEFAULT_TIMEOUT, 5000L, APIRepository.MAX_TIMEOUT);
        }
    }

    public APIRepository(MainAPI api) {
        Intrinsics.checkNotNullParameter(api, "api");
        this.api = api;
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().plusAssign(new AnonymousClass1(this));
        this.hasMainPage = api.getHasMainPage();
        this.providerType = api.getProviderType();
        this.name = api.getName();
        this.mainUrl = api.getMainUrl();
        this.mainPage = api.getMainPage();
        this.hasQuickSearch = api.getHasQuickSearch();
        this.vpnStatus = api.getVpnStatus();
    }

    public final MainAPI getApi() {
        return this.api;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void afterPluginsLoaded(boolean forceReload) {
        if (forceReload) {
            cache.clear();
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.APIRepository$1, reason: invalid class name */
    /* JADX INFO: compiled from: APIRepository.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        AnonymousClass1(Object obj) {
            super(1, obj, APIRepository.class, "afterPluginsLoaded", "afterPluginsLoaded(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((APIRepository) this.receiver).afterPluginsLoaded(z);
        }
    }

    public final boolean getHasMainPage() {
        return this.hasMainPage;
    }

    public final ProviderType getProviderType() {
        return this.providerType;
    }

    public final String getName() {
        return this.name;
    }

    public final String getMainUrl() {
        return this.mainUrl;
    }

    public final List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    public final boolean getHasQuickSearch() {
        return this.hasQuickSearch;
    }

    public final VPNStatus getVpnStatus() {
        return this.vpnStatus;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.APIRepository$load$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: APIRepository.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/LoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.APIRepository$load$2", f = "APIRepository.kt", i = {}, l = {87}, m = "invokeSuspend", n = {}, nl = {119}, s = {}, v = 2)
    static final class C08222 extends SuspendLambda implements Function1<Continuation<? super LoadResponse>, Object> {
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08222(String str, Continuation<? super C08222> continuation) {
            super(1, continuation);
            this.$url = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return APIRepository.this.new C08222(this.$url, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super LoadResponse> continuation) {
            return ((C08222) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.APIRepository$load$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: APIRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/LoadResponse;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.APIRepository$load$2$1", f = "APIRepository.kt", i = {0, 0, 0}, l = {105}, m = "invokeSuspend", n = {"fixedUrl", "lookingForHash", "cached"}, nl = {107}, s = {"L$0", "L$1", "L$2"}, v = 2)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super LoadResponse>, Object> {
            final /* synthetic */ String $url;
            Object L$0;
            Object L$1;
            Object L$2;
            int label;
            final /* synthetic */ APIRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(String str, APIRepository aPIRepository, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$url = str;
                this.this$0 = aPIRepository;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$url, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super LoadResponse> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws ErrorLoadingException {
                Pair pair;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                ArrayList arrayList = null;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (APIRepository.INSTANCE.isInvalidData(this.$url)) {
                        throw new ErrorLoadingException(null, 1, null);
                    }
                    String strFixUrl = MainAPIKt.fixUrl(this.this$0.getApi(), this.$url);
                    final Pair pair2 = new Pair(this.this$0.getApi().getName(), strFixUrl);
                    LoadResponse loadResponse = (LoadResponse) APIRepository.cache.withLock(new Function0() { // from class: com.lagradost.cloudstream3.ui.APIRepository$load$2$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return APIRepository.C08222.AnonymousClass1.invokeSuspend$lambda$0(pair2);
                        }
                    });
                    if (loadResponse != null) {
                        return loadResponse;
                    }
                    this.L$0 = SpillingKt.nullOutSpilledVariable(strFixUrl);
                    this.L$1 = pair2;
                    this.L$2 = SpillingKt.nullOutSpilledVariable(loadResponse);
                    this.label = 1;
                    obj = this.this$0.getApi().load(strFixUrl, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pair = pair2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    pair = (Pair) this.L$1;
                    ResultKt.throwOnFailure(obj);
                }
                LoadResponse loadResponse2 = (LoadResponse) obj;
                if (loadResponse2 != null) {
                    List<String> tags = loadResponse2.getTags();
                    if (tags != null) {
                        ArrayList arrayList2 = new ArrayList();
                        for (Object obj2 : tags) {
                            if (!StringsKt.isBlank((String) obj2)) {
                                arrayList2.add(obj2);
                            }
                        }
                        arrayList = arrayList2;
                    }
                    loadResponse2.setTags(arrayList);
                    final Companion.SavedLoadResponse savedLoadResponse = new Companion.SavedLoadResponse(APIHolder.INSTANCE.getUnixTime(), loadResponse2, pair);
                    APIRepository.cache.withLock(new Function0() { // from class: com.lagradost.cloudstream3.ui.APIRepository$load$2$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return APIRepository.C08222.AnonymousClass1.invokeSuspend$lambda$1$1(savedLoadResponse);
                        }
                    });
                    return loadResponse2;
                }
                throw new ErrorLoadingException(null, 1, null);
            }

            static final LoadResponse invokeSuspend$lambda$0(Pair pair) {
                for (Companion.SavedLoadResponse savedLoadResponse : APIRepository.cache) {
                    if (Intrinsics.areEqual(savedLoadResponse.getHash(), pair) && APIHolder.INSTANCE.getUnixTime() - savedLoadResponse.getUnixTime() < 600) {
                        return savedLoadResponse.getResponse();
                    }
                }
                return null;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final Unit invokeSuspend$lambda$1$1(Companion.SavedLoadResponse savedLoadResponse) {
                if (APIRepository.cache.size() > 20) {
                    APIRepository.cache.set(APIRepository.cacheIndex, savedLoadResponse);
                    Companion companion = APIRepository.INSTANCE;
                    APIRepository.cacheIndex = (APIRepository.cacheIndex + 1) % 20;
                } else {
                    APIRepository.cache.add(savedLoadResponse);
                }
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            Object objWithTimeout = TimeoutKt.withTimeout(APIRepository.INSTANCE.getTimeout(APIRepository.this.getApi().getLoadTimeoutMs()), new AnonymousClass1(this.$url, APIRepository.this, null), this);
            return objWithTimeout == coroutine_suspended ? coroutine_suspended : objWithTimeout;
        }
    }

    public final Object load(String str, Continuation<? super Resource<? extends LoadResponse>> continuation) {
        return ArchComponentExtKt.safeApiCall(new C08222(str, null), continuation);
    }

    public final Object search(String str, int i, Continuation<? super Resource<SearchResponseList>> continuation) {
        if (str.length() == 0) {
            return new Resource.Success(MainAPIKt.newSearchResponseList$default(CollectionsKt.emptyList(), null, 2, null));
        }
        return ArchComponentExtKt.safeApiCall(new C08262(str, i, null), continuation);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.APIRepository$search$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: APIRepository.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/SearchResponseList;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.APIRepository$search$2", f = "APIRepository.kt", i = {}, l = {128}, m = "invokeSuspend", n = {}, nl = {132}, s = {}, v = 2)
    static final class C08262 extends SuspendLambda implements Function1<Continuation<? super SearchResponseList>, Object> {
        final /* synthetic */ int $page;
        final /* synthetic */ String $query;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08262(String str, int i, Continuation<? super C08262> continuation) {
            super(1, continuation);
            this.$query = str;
            this.$page = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return APIRepository.this.new C08262(this.$query, this.$page, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super SearchResponseList> continuation) {
            return ((C08262) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.APIRepository$search$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: APIRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/SearchResponseList;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.APIRepository$search$2$1", f = "APIRepository.kt", i = {}, l = {129}, m = "invokeSuspend", n = {}, nl = {130}, s = {}, v = 2)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SearchResponseList>, Object> {
            final /* synthetic */ int $page;
            final /* synthetic */ String $query;
            int label;
            final /* synthetic */ APIRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(APIRepository aPIRepository, String str, int i, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = aPIRepository;
                this.$query = str;
                this.$page = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$query, this.$page, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SearchResponseList> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws ErrorLoadingException {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    obj = this.this$0.getApi().search(this.$query, this.$page, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                SearchResponseList searchResponseList = (SearchResponseList) obj;
                if (searchResponseList != null) {
                    return searchResponseList;
                }
                throw new ErrorLoadingException(null, 1, null);
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            Object objWithTimeout = TimeoutKt.withTimeout(APIRepository.INSTANCE.getTimeout(APIRepository.this.getApi().getSearchTimeoutMs()), new AnonymousClass1(APIRepository.this, this.$query, this.$page, null), this);
            return objWithTimeout == coroutine_suspended ? coroutine_suspended : objWithTimeout;
        }
    }

    public final Object quickSearch(String str, Continuation<? super Resource<SearchResponseList>> continuation) {
        if (str.length() == 0) {
            return new Resource.Success(MainAPIKt.newSearchResponseList$default(CollectionsKt.emptyList(), null, 2, null));
        }
        return ArchComponentExtKt.safeApiCall(new C08252(str, null), continuation);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.APIRepository$quickSearch$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: APIRepository.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/SearchResponseList;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.APIRepository$quickSearch$2", f = "APIRepository.kt", i = {}, l = {141}, m = "invokeSuspend", n = {}, nl = {146}, s = {}, v = 2)
    static final class C08252 extends SuspendLambda implements Function1<Continuation<? super SearchResponseList>, Object> {
        final /* synthetic */ String $query;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08252(String str, Continuation<? super C08252> continuation) {
            super(1, continuation);
            this.$query = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return APIRepository.this.new C08252(this.$query, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super SearchResponseList> continuation) {
            return ((C08252) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.APIRepository$quickSearch$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: APIRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/SearchResponseList;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.APIRepository$quickSearch$2$1", f = "APIRepository.kt", i = {}, l = {143}, m = "invokeSuspend", n = {}, nl = {144}, s = {}, v = 2)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SearchResponseList>, Object> {
            final /* synthetic */ String $query;
            int label;
            final /* synthetic */ APIRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(APIRepository aPIRepository, String str, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = aPIRepository;
                this.$query = str;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$query, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SearchResponseList> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws ErrorLoadingException {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    obj = this.this$0.getApi().quickSearch(this.$query, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                List list = (List) obj;
                if (list != null) {
                    return MainAPIKt.newSearchResponseList(list, Boxing.boxBoolean(false));
                }
                throw new ErrorLoadingException(null, 1, null);
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            Object objWithTimeout = TimeoutKt.withTimeout(APIRepository.INSTANCE.getTimeout(APIRepository.this.getApi().getQuickSearchTimeoutMs()), new AnonymousClass1(APIRepository.this, this.$query, null), this);
            return objWithTimeout == coroutine_suspended ? coroutine_suspended : objWithTimeout;
        }
    }

    public final Object waitForHomeDelay(Continuation<? super Unit> continuation) {
        long sequentialMainPageScrollDelay = (this.api.getSequentialMainPageScrollDelay() + this.api.getLastHomepageRequest()) - APIHolder.INSTANCE.getUnixTimeMS();
        if (sequentialMainPageScrollDelay < 0) {
            return Unit.INSTANCE;
        }
        Object objDelay = DelayKt.delay(sequentialMainPageScrollDelay, continuation);
        return objDelay == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDelay : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.APIRepository$getMainPage$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: APIRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/HomePageResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.APIRepository$getMainPage$2", f = "APIRepository.kt", i = {}, l = {158}, m = "invokeSuspend", n = {}, nl = {ByteCode.MONITORENTER}, s = {}, v = 2)
    static final class C08212 extends SuspendLambda implements Function1<Continuation<? super List<? extends HomePageResponse>>, Object> {
        final /* synthetic */ Integer $nameIndex;
        final /* synthetic */ int $page;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08212(Integer num, int i, Continuation<? super C08212> continuation) {
            super(1, continuation);
            this.$nameIndex = num;
            this.$page = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return APIRepository.this.new C08212(this.$nameIndex, this.$page, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super List<? extends HomePageResponse>> continuation) {
            return invoke2((Continuation<? super List<HomePageResponse>>) continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<? super List<HomePageResponse>> continuation) {
            return ((C08212) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.APIRepository$getMainPage$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: APIRepository.kt */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/HomePageResponse;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.APIRepository$getMainPage$2$1", f = "APIRepository.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {163, 173, 176, ByteCode.ARRAYLENGTH}, m = "invokeSuspend", n = {"$this$withTimeout", "data", "$i$a$-let-APIRepository$getMainPage$2$1$2", "$this$withTimeout", "$this$invokeSuspend_u24lambda_u242", "first", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "data", "$i$a$-run-APIRepository$getMainPage$2$1$3", "$i$f$map", "$i$f$mapTo", "$i$a$-map-APIRepository$getMainPage$2$1$3$1", "$this$withTimeout", "$this$invokeSuspend_u24lambda_u242", "first", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "data", "$i$a$-run-APIRepository$getMainPage$2$1$3", "$i$f$map", "$i$f$mapTo", "$i$a$-map-APIRepository$getMainPage$2$1$3$1", "$this$withTimeout", "$this$invokeSuspend_u24lambda_u242", "$this$invokeSuspend_u24lambda_u242_u241", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "$i$a$-run-APIRepository$getMainPage$2$1$3", "$i$a$-with-APIRepository$getMainPage$2$1$3$2", "$i$f$map", "$i$f$mapTo", "$i$a$-map-APIRepository$getMainPage$2$1$3$2$2"}, nl = {162, 174, 179, 233}, s = {"L$0", "L$1", "I$0", "L$0", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "L$9", "I$1", "I$2", "I$3", "I$4", "L$0", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "L$9", "I$1", "I$2", "I$3", "I$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "I$0", "I$1", "I$2", "I$3", "I$4"}, v = 2)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends HomePageResponse>>, Object> {
            final /* synthetic */ Integer $nameIndex;
            final /* synthetic */ int $page;
            int I$0;
            int I$1;
            int I$2;
            int I$3;
            int I$4;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$10;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            Object L$6;
            Object L$7;
            Object L$8;
            Object L$9;
            int label;
            final /* synthetic */ APIRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(APIRepository aPIRepository, Integer num, int i, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = aPIRepository;
                this.$nameIndex = num;
                this.$page = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$nameIndex, this.$page, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends HomePageResponse>> continuation) {
                return invoke2(coroutineScope, (Continuation<? super List<HomePageResponse>>) continuation);
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<HomePageResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:53:0x02fd  */
            /* JADX WARN: Code duplicated, block: B:56:0x0348  */
            /* JADX WARN: Code duplicated, block: B:61:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x0272 -> B:43:0x027c). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x0348 -> B:57:0x0349). Please report as a decompilation issue!!! */
            /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final java.lang.Object invokeSuspend(java.lang.Object r25) {
                /*
                    Method dump skipped, instruction units count: 851
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.APIRepository.C08212.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            Object objWithTimeout = TimeoutKt.withTimeout(APIRepository.INSTANCE.getTimeout(APIRepository.this.getApi().getGetMainPageTimeoutMs()), new AnonymousClass1(APIRepository.this, this.$nameIndex, this.$page, null), this);
            return objWithTimeout == coroutine_suspended ? coroutine_suspended : objWithTimeout;
        }
    }

    public static /* synthetic */ Object getMainPage$default(APIRepository aPIRepository, int i, Integer num, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            num = null;
        }
        return aPIRepository.getMainPage(i, num, continuation);
    }

    public final Object getMainPage(int i, Integer num, Continuation<? super Resource<? extends List<HomePageResponse>>> continuation) {
        return ArchComponentExtKt.safeApiCall(new C08212(num, i, null), continuation);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.APIRepository$extractorVerifierJob$2, reason: invalid class name */
    /* JADX INFO: compiled from: APIRepository.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.APIRepository$extractorVerifierJob$2", f = "APIRepository.kt", i = {}, l = {200}, m = "invokeSuspend", n = {}, nl = {201}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ String $extractorData;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$extractorData = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return APIRepository.this.new AnonymousClass2(this.$extractorData, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (APIRepository.this.getApi().extractorVerifierJob(this.$extractorData, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final Object extractorVerifierJob(String str, Continuation<? super Unit> continuation) {
        Object objSafeApiCall = ArchComponentExtKt.safeApiCall(new AnonymousClass2(str, null), continuation);
        return objSafeApiCall == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSafeApiCall : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object loadLinks(String str, boolean z, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Boolean> continuation) {
        C08231 c08231;
        if (continuation instanceof C08231) {
            c08231 = (C08231) continuation;
            if ((c08231.label & Integer.MIN_VALUE) != 0) {
                c08231.label -= Integer.MIN_VALUE;
            } else {
                c08231 = new C08231(continuation);
            }
        } else {
            c08231 = new C08231(continuation);
        }
        C08231 c08232 = c08231;
        Object objWithTimeout = c08232.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08232.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(objWithTimeout);
                Companion companion = INSTANCE;
                if (companion.isInvalidData(str)) {
                    return Boxing.boxBoolean(false);
                }
                long timeout = companion.getTimeout(this.api.getLoadLinksTimeoutMs());
                C08242 c08242 = new C08242(str, z, function1, function2, null);
                c08232.L$0 = SpillingKt.nullOutSpilledVariable(str);
                c08232.L$1 = SpillingKt.nullOutSpilledVariable(function1);
                c08232.L$2 = SpillingKt.nullOutSpilledVariable(function2);
                c08232.Z$0 = z;
                c08232.label = 1;
                objWithTimeout = TimeoutKt.withTimeout(timeout, c08242, c08232);
                if (objWithTimeout == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                boolean z2 = c08232.Z$0;
                ResultKt.throwOnFailure(objWithTimeout);
            }
            return Boxing.boxBoolean(((Boolean) objWithTimeout).booleanValue());
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            return Boxing.boxBoolean(false);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.APIRepository$loadLinks$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: APIRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.APIRepository$loadLinks$2", f = "APIRepository.kt", i = {}, l = {213}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
    static final class C08242 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ Function1<ExtractorLink, Unit> $callback;
        final /* synthetic */ String $data;
        final /* synthetic */ boolean $isCasting;
        final /* synthetic */ Function1<SubtitleFile, Unit> $subtitleCallback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08242(String str, boolean z, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super C08242> continuation) {
            super(2, continuation);
            this.$data = str;
            this.$isCasting = z;
            this.$subtitleCallback = function1;
            this.$callback = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return APIRepository.this.new C08242(this.$data, this.$isCasting, this.$subtitleCallback, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((C08242) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            Object objLoadLinks = APIRepository.this.getApi().loadLinks(this.$data, this.$isCasting, this.$subtitleCallback, this.$callback, this);
            return objLoadLinks == coroutine_suspended ? coroutine_suspended : objLoadLinks;
        }
    }
}
