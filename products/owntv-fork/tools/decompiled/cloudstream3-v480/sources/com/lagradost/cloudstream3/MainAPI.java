package com.lagradost.cloudstream3;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.syncproviders.SyncIdName;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NotImplementedError;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b)\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 }2\u00020\u0001:\u0001}B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ \u0010[\u001a\u0004\u0018\u00010]2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020aH\u0096@¢\u0006\u0002\u0010bJ \u0010c\u001a\u0004\u0018\u00010d2\u0006\u0010e\u001a\u00020\n2\u0006\u0010^\u001a\u00020_H\u0096@¢\u0006\u0002\u0010fJ\u001e\u0010c\u001a\n\u0012\u0004\u0012\u00020g\u0018\u00010Y2\u0006\u0010e\u001a\u00020\nH\u0096@¢\u0006\u0002\u0010hJ\u001e\u0010i\u001a\n\u0012\u0004\u0012\u00020g\u0018\u00010Y2\u0006\u0010e\u001a\u00020\nH\u0096@¢\u0006\u0002\u0010hJ\u0018\u0010j\u001a\u0004\u0018\u00010k2\u0006\u0010l\u001a\u00020\nH\u0096@¢\u0006\u0002\u0010hJ\u0018\u0010m\u001a\u00020\u00052\b\u0010n\u001a\u0004\u0018\u00010\nH\u0096@¢\u0006\u0002\u0010hJF\u0010o\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\n2\u0006\u0010p\u001a\u00020\u00162\u0012\u0010q\u001a\u000e\u0012\u0004\u0012\u00020s\u0012\u0004\u0012\u00020\u00050r2\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020u\u0012\u0004\u0012\u00020\u00050rH\u0096@¢\u0006\u0002\u0010vJ\u0012\u0010w\u001a\u0004\u0018\u00010x2\u0006\u0010y\u001a\u00020uH\u0016J \u0010z\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020J2\u0006\u0010{\u001a\u00020\nH\u0096@¢\u0006\u0002\u0010|R\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\u0016X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0016X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001a\u0010\u001e\u001a\u00020\u001fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u001fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#R\u001a\u0010'\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#R\u001a\u0010*\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\f\"\u0004\b,\u0010\u000eR\u0014\u0010-\u001a\u00020\u0016X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0018R\u0014\u0010/\u001a\u00020\u0016X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0018R\u0014\u00101\u001a\u00020\u0016X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0018R\u0014\u00103\u001a\u00020\u0016X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0018R\u001c\u00105\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\f\"\u0004\b7\u0010\u000eR\u0014\u00108\u001a\u00020\u0016X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0018R\u0014\u0010:\u001a\u00020\u0016X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0018R\u0018\u0010<\u001a\u0004\u0018\u00010\u001fX\u0096\u0004¢\u0006\n\n\u0002\u0010?\u001a\u0004\b=\u0010>R\u0018\u0010@\u001a\u0004\u0018\u00010\u001fX\u0096\u0004¢\u0006\n\n\u0002\u0010?\u001a\u0004\bA\u0010>R\u0018\u0010B\u001a\u0004\u0018\u00010\u001fX\u0096\u0004¢\u0006\n\n\u0002\u0010?\u001a\u0004\bC\u0010>R\u0018\u0010D\u001a\u0004\u0018\u00010\u001fX\u0096\u0004¢\u0006\n\n\u0002\u0010?\u001a\u0004\bE\u0010>R\u0018\u0010F\u001a\u0004\u0018\u00010\u001fX\u0096\u0004¢\u0006\n\n\u0002\u0010?\u001a\u0004\bG\u0010>R\u001a\u0010H\u001a\b\u0012\u0004\u0012\u00020J0IX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010LR\u001a\u0010M\u001a\b\u0012\u0004\u0012\u00020N0IX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bO\u0010LR\u0014\u0010P\u001a\u00020QX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010SR\u0014\u0010T\u001a\u00020UX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010WR\u001a\u0010X\u001a\b\u0012\u0004\u0012\u00020Z0YX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\\¨\u0006~"}, d2 = {"Lcom/lagradost/cloudstream3/MainAPI;", "", "<init>", "()V", "init", "", "overrideWithNewData", "data", "Lcom/lagradost/cloudstream3/ProvidersInfoJson;", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "setMainUrl", "storedCredentials", "getStoredCredentials", "setStoredCredentials", "canBeOverridden", "", "getCanBeOverridden", "()Z", "setCanBeOverridden", "(Z)V", "sequentialMainPage", "getSequentialMainPage", "setSequentialMainPage", "sequentialMainPageDelay", "", "getSequentialMainPageDelay", "()J", "setSequentialMainPageDelay", "(J)V", "sequentialMainPageScrollDelay", "getSequentialMainPageScrollDelay", "setSequentialMainPageScrollDelay", "lastHomepageRequest", "getLastHomepageRequest", "setLastHomepageRequest", "lang", "getLang", "setLang", "instantLinkLoading", "getInstantLinkLoading", "hasChromecastSupport", "getHasChromecastSupport", "hasDownloadSupport", "getHasDownloadSupport", "usesWebView", "getUsesWebView", "sourcePlugin", "getSourcePlugin", "setSourcePlugin", "hasMainPage", "getHasMainPage", "hasQuickSearch", "getHasQuickSearch", "loadLinksTimeoutMs", "getLoadLinksTimeoutMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMainPageTimeoutMs", "getGetMainPageTimeoutMs", "searchTimeoutMs", "getSearchTimeoutMs", "quickSearchTimeoutMs", "getQuickSearchTimeoutMs", "loadTimeoutMs", "getLoadTimeoutMs", "supportedSyncNames", "", "Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "getSupportedSyncNames", "()Ljava/util/Set;", "supportedTypes", "Lcom/lagradost/cloudstream3/TvType;", "getSupportedTypes", "vpnStatus", "Lcom/lagradost/cloudstream3/VPNStatus;", "getVpnStatus", "()Lcom/lagradost/cloudstream3/VPNStatus;", "providerType", "Lcom/lagradost/cloudstream3/ProviderType;", "getProviderType", "()Lcom/lagradost/cloudstream3/ProviderType;", "mainPage", "", "Lcom/lagradost/cloudstream3/MainPageData;", "getMainPage", "()Ljava/util/List;", "Lcom/lagradost/cloudstream3/HomePageResponse;", "page", "", "request", "Lcom/lagradost/cloudstream3/MainPageRequest;", "(ILcom/lagradost/cloudstream3/MainPageRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "Lcom/lagradost/cloudstream3/SearchResponseList;", SearchIntents.EXTRA_QUERY, "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/lagradost/cloudstream3/SearchResponse;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "quickSearch", "load", "Lcom/lagradost/cloudstream3/LoadResponse;", ImagesContract.URL, "extractorVerifierJob", "extractorData", "loadLinks", "isCasting", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getVideoInterceptor", "Lokhttp3/Interceptor;", "extractorLink", "getLoadUrl", "id", "(Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class MainAPI {
    private static HashMap<String, ProvidersInfoJson> overrideData;
    private final Long getMainPageTimeoutMs;
    private final boolean hasMainPage;
    private final boolean hasQuickSearch;
    private final boolean instantLinkLoading;
    private long lastHomepageRequest;
    private final Long loadLinksTimeoutMs;
    private final Long loadTimeoutMs;
    private final Long quickSearchTimeoutMs;
    private final Long searchTimeoutMs;
    private boolean sequentialMainPage;
    private long sequentialMainPageDelay;
    private long sequentialMainPageScrollDelay;
    private String sourcePlugin;
    private String storedCredentials;
    private final boolean usesWebView;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static SettingsJson settingsForProvider = new SettingsJson(false, 1, null);
    private String name = "NONE";
    private String mainUrl = "NONE";
    private boolean canBeOverridden = true;
    private String lang = "en";
    private final boolean hasChromecastSupport = true;
    private final boolean hasDownloadSupport = true;
    private final Set<SyncIdName> supportedSyncNames = SetsKt.emptySet();
    private final Set<TvType> supportedTypes = SetsKt.setOf((Object[]) new TvType[]{TvType.Movie, TvType.TvSeries, TvType.Cartoon, TvType.Anime, TvType.OVA});
    private final VPNStatus vpnStatus = VPNStatus.None;
    private final ProviderType providerType = ProviderType.DirectProvider;
    private final List<MainPageData> mainPage = CollectionsKt.listOf(new MainPageData("", "", false));

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPI$search$1, reason: invalid class name */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPI", f = "MainAPI.kt", i = {0, 0, 0}, l = {640}, m = "search$suspendImpl", n = {"$this", SearchIntents.EXTRA_QUERY, "page"}, nl = {643}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MainAPI.search$suspendImpl(MainAPI.this, null, 0, this);
        }
    }

    static /* synthetic */ Object getLoadUrl$suspendImpl(MainAPI mainAPI, SyncIdName syncIdName, String str, Continuation<? super String> continuation) {
        return null;
    }

    public Object extractorVerifierJob(String str, Continuation<? super Unit> continuation) {
        return extractorVerifierJob$suspendImpl(this, str, continuation);
    }

    public Object getLoadUrl(SyncIdName syncIdName, String str, Continuation<? super String> continuation) {
        return getLoadUrl$suspendImpl(this, syncIdName, str, continuation);
    }

    public Object getMainPage(int i, MainPageRequest mainPageRequest, Continuation<? super HomePageResponse> continuation) {
        return getMainPage$suspendImpl(this, i, mainPageRequest, continuation);
    }

    public Interceptor getVideoInterceptor(ExtractorLink extractorLink) {
        Intrinsics.checkNotNullParameter(extractorLink, "extractorLink");
        return null;
    }

    public Object load(String str, Continuation<? super LoadResponse> continuation) {
        return load$suspendImpl(this, str, continuation);
    }

    public Object loadLinks(String str, boolean z, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Boolean> continuation) {
        return loadLinks$suspendImpl(this, str, z, function1, function2, continuation);
    }

    public Object quickSearch(String str, Continuation<? super List<? extends SearchResponse>> continuation) {
        return quickSearch$suspendImpl(this, str, continuation);
    }

    public Object search(String str, int i, Continuation<? super SearchResponseList> continuation) {
        return search$suspendImpl(this, str, i, continuation);
    }

    public Object search(String str, Continuation<? super List<? extends SearchResponse>> continuation) {
        return search$suspendImpl(this, str, continuation);
    }

    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R:\u0010\u0004\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/MainAPI$Companion;", "", "<init>", "()V", "overrideData", "Ljava/util/HashMap;", "", "Lcom/lagradost/cloudstream3/ProvidersInfoJson;", "Lkotlin/collections/HashMap;", "getOverrideData", "()Ljava/util/HashMap;", "setOverrideData", "(Ljava/util/HashMap;)V", "settingsForProvider", "Lcom/lagradost/cloudstream3/SettingsJson;", "getSettingsForProvider", "()Lcom/lagradost/cloudstream3/SettingsJson;", "setSettingsForProvider", "(Lcom/lagradost/cloudstream3/SettingsJson;)V", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HashMap<String, ProvidersInfoJson> getOverrideData() {
            return MainAPI.overrideData;
        }

        public final void setOverrideData(HashMap<String, ProvidersInfoJson> map) {
            MainAPI.overrideData = map;
        }

        public final SettingsJson getSettingsForProvider() {
            return MainAPI.settingsForProvider;
        }

        public final void setSettingsForProvider(SettingsJson settingsJson) {
            Intrinsics.checkNotNullParameter(settingsJson, "<set-?>");
            MainAPI.settingsForProvider = settingsJson;
        }
    }

    public final void init() {
        ProvidersInfoJson providersInfoJson;
        HashMap<String, ProvidersInfoJson> map = overrideData;
        if (map == null || (providersInfoJson = map.get(Reflection.getOrCreateKotlinClass(getClass()).getSimpleName())) == null) {
            return;
        }
        overrideWithNewData(providersInfoJson);
    }

    public final void overrideWithNewData(ProvidersInfoJson data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (getCanBeOverridden()) {
            setName(data.getName());
            if (!StringsKt.isBlank(data.getUrl()) && !Intrinsics.areEqual(data.getUrl(), "NONE")) {
                setMainUrl(data.getUrl());
            }
            setStoredCredentials(data.getCredentials());
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public String getMainUrl() {
        return this.mainUrl;
    }

    public void setMainUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mainUrl = str;
    }

    public String getStoredCredentials() {
        return this.storedCredentials;
    }

    public void setStoredCredentials(String str) {
        this.storedCredentials = str;
    }

    public boolean getCanBeOverridden() {
        return this.canBeOverridden;
    }

    public void setCanBeOverridden(boolean z) {
        this.canBeOverridden = z;
    }

    public boolean getSequentialMainPage() {
        return this.sequentialMainPage;
    }

    public void setSequentialMainPage(boolean z) {
        this.sequentialMainPage = z;
    }

    public long getSequentialMainPageDelay() {
        return this.sequentialMainPageDelay;
    }

    public void setSequentialMainPageDelay(long j) {
        this.sequentialMainPageDelay = j;
    }

    public long getSequentialMainPageScrollDelay() {
        return this.sequentialMainPageScrollDelay;
    }

    public void setSequentialMainPageScrollDelay(long j) {
        this.sequentialMainPageScrollDelay = j;
    }

    public final long getLastHomepageRequest() {
        return this.lastHomepageRequest;
    }

    public final void setLastHomepageRequest(long j) {
        this.lastHomepageRequest = j;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lang = str;
    }

    public boolean getInstantLinkLoading() {
        return this.instantLinkLoading;
    }

    public boolean getHasChromecastSupport() {
        return this.hasChromecastSupport;
    }

    public boolean getHasDownloadSupport() {
        return this.hasDownloadSupport;
    }

    public boolean getUsesWebView() {
        return this.usesWebView;
    }

    public final String getSourcePlugin() {
        return this.sourcePlugin;
    }

    public final void setSourcePlugin(String str) {
        this.sourcePlugin = str;
    }

    public boolean getHasMainPage() {
        return this.hasMainPage;
    }

    public boolean getHasQuickSearch() {
        return this.hasQuickSearch;
    }

    public Long getLoadLinksTimeoutMs() {
        return this.loadLinksTimeoutMs;
    }

    public Long getGetMainPageTimeoutMs() {
        return this.getMainPageTimeoutMs;
    }

    public Long getSearchTimeoutMs() {
        return this.searchTimeoutMs;
    }

    public Long getQuickSearchTimeoutMs() {
        return this.quickSearchTimeoutMs;
    }

    public Long getLoadTimeoutMs() {
        return this.loadTimeoutMs;
    }

    public Set<SyncIdName> getSupportedSyncNames() {
        return this.supportedSyncNames;
    }

    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    public VPNStatus getVpnStatus() {
        return this.vpnStatus;
    }

    public ProviderType getProviderType() {
        return this.providerType;
    }

    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    static /* synthetic */ Object getMainPage$suspendImpl(MainAPI mainAPI, int i, MainPageRequest mainPageRequest, Continuation<? super HomePageResponse> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    static /* synthetic */ Object search$suspendImpl(MainAPI mainAPI, String str, int i, Continuation<? super SearchResponseList> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = mainAPI.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = mainAPI.new AnonymousClass1(continuation);
        }
        Object objSearch = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objSearch);
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(mainAPI);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.I$0 = i;
            anonymousClass1.label = 1;
            objSearch = mainAPI.search(str, anonymousClass1);
            if (objSearch == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = anonymousClass1.I$0;
            ResultKt.throwOnFailure(objSearch);
        }
        List list = (List) objSearch;
        if (list == null) {
            return null;
        }
        return MainAPIKt.newSearchResponseList(list, Boxing.boxBoolean(false));
    }

    static /* synthetic */ Object search$suspendImpl(MainAPI mainAPI, String str, Continuation<? super List<? extends SearchResponse>> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object quickSearch$suspendImpl(MainAPI mainAPI, String str, Continuation<? super List<? extends SearchResponse>> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object load$suspendImpl(MainAPI mainAPI, String str, Continuation<? super LoadResponse> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object extractorVerifierJob$suspendImpl(MainAPI mainAPI, String str, Continuation<? super Unit> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object loadLinks$suspendImpl(MainAPI mainAPI, String str, boolean z, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Boolean> continuation) {
        throw new NotImplementedError(null, 1, null);
    }
}
