package com.lagradost.cloudstream3;

import androidx.compose.runtime.ComposerKt;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.AtomicList;
import com.lagradost.cloudstream3.utils.AtomicMutableList;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.RequestBodyTypes;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlin.time.Clock;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.mozilla.classfile.ByteCode;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u000f\u001a\u00020\u0010J\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0012J\u000e\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\fJ\u000e\u0010\"\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\fJ\u0012\u0010#\u001a\u00020\u00102\b\b\u0002\u0010$\u001a\u00020%H\u0002J\u0012\u0010&\u001a\u0004\u0018\u00010\f2\b\u0010'\u001a\u0004\u0018\u00010\u0012J\u0012\u0010(\u001a\u0004\u0018\u00010\f2\b\u0010)\u001a\u0004\u0018\u00010\u0012J,\u0010*\u001a\u0004\u0018\u00010\u00122\u0006\u0010)\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u00122\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0012H\u0086@¢\u0006\u0002\u0010-J8\u00102\u001a\u0004\u0018\u0001032\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u0012052\u000e\u00106\u001a\n\u0012\u0004\u0012\u000208\u0018\u0001072\b\u00109\u001a\u0004\u0018\u00010\u001bH\u0086@¢\u0006\u0002\u0010:J@\u00102\u001a\u0004\u0018\u0001032\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u0012052\u000e\u00106\u001a\n\u0012\u0004\u0012\u000208\u0018\u0001072\b\u00109\u001a\u0004\u0018\u00010\u001b2\u0006\u0010;\u001a\u00020%H\u0086@¢\u0006\u0002\u0010<J\u001a\u0010=\u001a\u0004\u0018\u0001002\b\u0010>\u001a\u0004\u0018\u00010\u0012H\u0082@¢\u0006\u0002\u0010?R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R(\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR*\u0010.\u001a\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u0002000/j\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u000200`1X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/lagradost/cloudstream3/APIHolder;", "", "<init>", "()V", "unixTimeMS", "", "getUnixTimeMS", "()J", "unixTime", "getUnixTime", "allProviders", "Lcom/lagradost/cloudstream3/utils/AtomicMutableList;", "Lcom/lagradost/cloudstream3/MainAPI;", "getAllProviders", "()Lcom/lagradost/cloudstream3/utils/AtomicMutableList;", "initAll", "", "capitalize", "", "apis", "Lcom/lagradost/cloudstream3/utils/AtomicList;", "getApis", "()Lcom/lagradost/cloudstream3/utils/AtomicList;", "setApis", "(Lcom/lagradost/cloudstream3/utils/AtomicList;)V", "apiMap", "", "", "getApiMap", "()Ljava/util/Map;", "setApiMap", "(Ljava/util/Map;)V", "addPluginMapping", "plugin", "removePluginMapping", "initMap", "forcedUpdate", "", "getApiFromNameNull", "apiName", "getApiFromUrlNull", ImagesContract.URL, "getCaptchaToken", "key", "referer", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trackerCache", "Ljava/util/HashMap;", "Lcom/lagradost/cloudstream3/AniSearch;", "Lkotlin/collections/HashMap;", "getTracker", "Lcom/lagradost/cloudstream3/Tracker;", "titles", "", "types", "", "Lcom/lagradost/cloudstream3/TrackerType;", "year", "(Ljava/util/List;Ljava/util/Set;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lessAccurate", "(Ljava/util/List;Ljava/util/Set;Ljava/lang/Integer;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchAnilist", "title", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class APIHolder {
    private static Map<String, Integer> apiMap;
    public static final APIHolder INSTANCE = new APIHolder();
    private static final AtomicMutableList<MainAPI> allProviders = Coroutines.INSTANCE.atomicListOf(new MainAPI[0]);
    private static AtomicList<MainAPI> apis = Coroutines.INSTANCE.atomicListOf(new MainAPI[0]);
    private static HashMap<String, AniSearch> trackerCache = new HashMap<>();

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.APIHolder$getCaptchaToken$1, reason: invalid class name */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.APIHolder", f = "MainAPI.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2}, l = {ByteCode.MONITORENTER, 203, ComposerKt.reuseKey}, m = "getCaptchaToken", n = {ImagesContract.URL, "key", "referer", "_url", "domain", ImagesContract.URL, "key", "referer", "_url", "domain", "vToken", ImagesContract.URL, "key", "referer", "_url", "domain", "vToken", "recapToken"}, nl = {ByteCode.IFNONNULL, ComposerKt.providerMapsKey, 217}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return APIHolder.this.getCaptchaToken(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.APIHolder$getTracker$2, reason: invalid class name */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.APIHolder", f = "MainAPI.kt", i = {0, 0, 0, 0, 0}, l = {257}, m = "getTracker", n = {"titles", "types", "year", "mainTitle", "lessAccurate"}, nl = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0"}, v = 2)
    static final class AnonymousClass2 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return APIHolder.this.getTracker(null, null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.APIHolder$searchAnilist$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.APIHolder", f = "MainAPI.kt", i = {0, 0, 0}, l = {327}, m = "searchAnilist", n = {"title", SearchIntents.EXTRA_QUERY, "data"}, nl = {328}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class C06931 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C06931(Continuation<? super C06931> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return APIHolder.this.searchAnilist(null, this);
        }
    }

    private APIHolder() {
    }

    public final long getUnixTimeMS() {
        return Clock.System.INSTANCE.now().toEpochMilliseconds();
    }

    public final long getUnixTime() {
        return getUnixTimeMS() / 1000;
    }

    public final AtomicMutableList<MainAPI> getAllProviders() {
        return allProviders;
    }

    public final void initAll() {
        allProviders.withLock(new Function0() { // from class: com.lagradost.cloudstream3.APIHolder$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return APIHolder.initAll$lambda$0();
            }
        });
        apiMap = null;
    }

    static final Unit initAll$lambda$0() {
        Iterator<MainAPI> it = allProviders.iterator();
        while (it.hasNext()) {
            it.next().init();
        }
        return Unit.INSTANCE;
    }

    public final String capitalize(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (str.length() <= 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        char cCharAt = str.charAt(0);
        sb.append((Object) (Character.isLowerCase(cCharAt) ? CharsKt.titlecase(cCharAt) : String.valueOf(cCharAt)));
        String strSubstring = str.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        sb.append(strSubstring);
        return sb.toString();
    }

    public final AtomicList<MainAPI> getApis() {
        return apis;
    }

    public final void setApis(AtomicList<MainAPI> atomicList) {
        Intrinsics.checkNotNullParameter(atomicList, "<set-?>");
        apis = atomicList;
    }

    public final Map<String, Integer> getApiMap() {
        return apiMap;
    }

    public final void setApiMap(Map<String, Integer> map) {
        apiMap = map;
    }

    public final void addPluginMapping(final MainAPI plugin) {
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        apis.withLock(new Function0() { // from class: com.lagradost.cloudstream3.APIHolder$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return APIHolder.addPluginMapping$lambda$0(plugin);
            }
        });
        initMap(true);
    }

    static final Unit addPluginMapping$lambda$0(MainAPI mainAPI) {
        apis = apis.plus(mainAPI);
        return Unit.INSTANCE;
    }

    public final void removePluginMapping(final MainAPI plugin) {
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        apis.withLock(new Function0() { // from class: com.lagradost.cloudstream3.APIHolder$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return APIHolder.removePluginMapping$lambda$0(plugin);
            }
        });
        initMap(true);
    }

    static final Unit removePluginMapping$lambda$0(final MainAPI mainAPI) {
        apis = apis.filter(new Function1() { // from class: com.lagradost.cloudstream3.APIHolder$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(APIHolder.removePluginMapping$lambda$0$0(mainAPI, (MainAPI) obj));
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removePluginMapping$lambda$0$0(MainAPI mainAPI, MainAPI it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !Intrinsics.areEqual(it, mainAPI);
    }

    static /* synthetic */ void initMap$default(APIHolder aPIHolder, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        aPIHolder.initMap(z);
    }

    private final void initMap(final boolean forcedUpdate) {
        apis.withLock(new Function0() { // from class: com.lagradost.cloudstream3.APIHolder$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return APIHolder.initMap$lambda$0(forcedUpdate);
            }
        });
    }

    static final Unit initMap$lambda$0(boolean z) {
        if (apiMap == null || z) {
            AtomicList<MainAPI> atomicList = apis;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(atomicList, 10));
            int i = 0;
            for (MainAPI mainAPI : atomicList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                arrayList.add(TuplesKt.to(mainAPI.getName(), Integer.valueOf(i)));
                i = i2;
            }
            apiMap = MapsKt.toMap(arrayList);
        }
        return Unit.INSTANCE;
    }

    public final MainAPI getApiFromNameNull(final String apiName) {
        if (apiName == null) {
            return null;
        }
        return (MainAPI) allProviders.withLock(new Function0() { // from class: com.lagradost.cloudstream3.APIHolder$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return APIHolder.getApiFromNameNull$lambda$0(apiName);
            }
        });
    }

    static final MainAPI getApiFromNameNull$lambda$0(final String str) {
        initMap$default(INSTANCE, false, 1, null);
        return (MainAPI) apis.withLock(new Function0() { // from class: com.lagradost.cloudstream3.APIHolder$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return APIHolder.getApiFromNameNull$lambda$0$0(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MainAPI getApiFromNameNull$lambda$0$0(String str) {
        MainAPI next;
        Integer num;
        Map<String, Integer> map = apiMap;
        if (map != null && (num = map.get(str)) != null) {
            MainAPI mainAPI = (MainAPI) CollectionsKt.getOrNull(apis, num.intValue());
            if (mainAPI != null) {
                return mainAPI;
            }
        }
        Iterator<MainAPI> it = allProviders.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(next.getName(), str)) {
                return next;
            }
        }
        next = null;
        return next;
    }

    public final MainAPI getApiFromUrlNull(final String url) {
        if (url == null) {
            return null;
        }
        return (MainAPI) allProviders.withLock(new Function0() { // from class: com.lagradost.cloudstream3.APIHolder$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return APIHolder.getApiFromUrlNull$lambda$0(url);
            }
        });
    }

    static final MainAPI getApiFromUrlNull$lambda$0(String str) {
        MainAPI mainAPI;
        MainAPI next;
        Iterator<MainAPI> it = allProviders.iterator();
        do {
            mainAPI = null;
            if (it.hasNext()) {
                next = it.next();
            }
            return mainAPI;
        } while (!StringsKt.startsWith$default(str, next.getMainUrl(), false, 2, (Object) null));
        mainAPI = next;
        return mainAPI;
    }

    public static /* synthetic */ Object getCaptchaToken$default(APIHolder aPIHolder, String str, String str2, String str3, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        return aPIHolder.getCaptchaToken(str, str2, str3, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0216 A[Catch: Exception -> 0x02d4, TryCatch #1 {Exception -> 0x02d4, blocks: (B:43:0x02c1, B:35:0x0208, B:37:0x0216, B:40:0x0220, B:31:0x017a, B:27:0x0165), top: B:52:0x0165 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x021d  */
    /* JADX WARN: Code duplicated, block: B:40:0x0220 A[Catch: Exception -> 0x02d4, TryCatch #1 {Exception -> 0x02d4, blocks: (B:43:0x02c1, B:35:0x0208, B:37:0x0216, B:40:0x0220, B:31:0x017a, B:27:0x0165), top: B:52:0x0165 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x001e  */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x02be, code lost:
    
        if (r1 == r5) goto L42;
     */
    /* JADX WARN: Instruction removed from duplicated block: B:40:0x0220, please report this as an issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCaptchaToken(java.lang.String r30, java.lang.String r31, java.lang.String r32, kotlin.coroutines.Continuation<? super java.lang.String> r33) {
        /*
            Method dump skipped, instruction units count: 734
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.APIHolder.getCaptchaToken(java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object getTracker(List<String> list, Set<? extends TrackerType> set, Integer num, Continuation<? super Tracker> continuation) {
        return getTracker(list, set, num, false, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:108:0x014a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:111:0x0151 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:113:0x0143 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:123:0x010c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:124:0x010a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:125:? A[LOOP:1: B:59:0x00f8->B:125:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:126:0x0140 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:127:0x013e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:128:? A[LOOP:2: B:72:0x0124->B:128:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:41:0x00bf A[Catch: all -> 0x019d, TryCatch #0 {all -> 0x019d, blocks: (B:12:0x003e, B:25:0x0087, B:27:0x008b, B:32:0x00a1, B:34:0x00a7, B:36:0x00ad, B:38:0x00b3, B:39:0x00b9, B:41:0x00bf, B:43:0x00c8, B:51:0x00dd, B:53:0x00e3, B:55:0x00ea, B:58:0x00f4, B:59:0x00f8, B:61:0x00fe, B:66:0x010f, B:68:0x0116, B:71:0x0120, B:72:0x0124, B:74:0x012a, B:88:0x0152, B:91:0x0157, B:93:0x016b, B:99:0x017d, B:95:0x0171, B:97:0x0177, B:46:0x00d3, B:17:0x0051, B:19:0x005a, B:21:0x006a, B:102:0x0191, B:103:0x019c), top: B:107:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x00c8 A[Catch: all -> 0x019d, TryCatch #0 {all -> 0x019d, blocks: (B:12:0x003e, B:25:0x0087, B:27:0x008b, B:32:0x00a1, B:34:0x00a7, B:36:0x00ad, B:38:0x00b3, B:39:0x00b9, B:41:0x00bf, B:43:0x00c8, B:51:0x00dd, B:53:0x00e3, B:55:0x00ea, B:58:0x00f4, B:59:0x00f8, B:61:0x00fe, B:66:0x010f, B:68:0x0116, B:71:0x0120, B:72:0x0124, B:74:0x012a, B:88:0x0152, B:91:0x0157, B:93:0x016b, B:99:0x017d, B:95:0x0171, B:97:0x0177, B:46:0x00d3, B:17:0x0051, B:19:0x005a, B:21:0x006a, B:102:0x0191, B:103:0x019c), top: B:107:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:53:0x00e3 A[Catch: all -> 0x019d, TryCatch #0 {all -> 0x019d, blocks: (B:12:0x003e, B:25:0x0087, B:27:0x008b, B:32:0x00a1, B:34:0x00a7, B:36:0x00ad, B:38:0x00b3, B:39:0x00b9, B:41:0x00bf, B:43:0x00c8, B:51:0x00dd, B:53:0x00e3, B:55:0x00ea, B:58:0x00f4, B:59:0x00f8, B:61:0x00fe, B:66:0x010f, B:68:0x0116, B:71:0x0120, B:72:0x0124, B:74:0x012a, B:88:0x0152, B:91:0x0157, B:93:0x016b, B:99:0x017d, B:95:0x0171, B:97:0x0177, B:46:0x00d3, B:17:0x0051, B:19:0x005a, B:21:0x006a, B:102:0x0191, B:103:0x019c), top: B:107:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:58:0x00f4 A[Catch: all -> 0x019d, TryCatch #0 {all -> 0x019d, blocks: (B:12:0x003e, B:25:0x0087, B:27:0x008b, B:32:0x00a1, B:34:0x00a7, B:36:0x00ad, B:38:0x00b3, B:39:0x00b9, B:41:0x00bf, B:43:0x00c8, B:51:0x00dd, B:53:0x00e3, B:55:0x00ea, B:58:0x00f4, B:59:0x00f8, B:61:0x00fe, B:66:0x010f, B:68:0x0116, B:71:0x0120, B:72:0x0124, B:74:0x012a, B:88:0x0152, B:91:0x0157, B:93:0x016b, B:99:0x017d, B:95:0x0171, B:97:0x0177, B:46:0x00d3, B:17:0x0051, B:19:0x005a, B:21:0x006a, B:102:0x0191, B:103:0x019c), top: B:107:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:61:0x00fe A[Catch: all -> 0x019d, TryCatch #0 {all -> 0x019d, blocks: (B:12:0x003e, B:25:0x0087, B:27:0x008b, B:32:0x00a1, B:34:0x00a7, B:36:0x00ad, B:38:0x00b3, B:39:0x00b9, B:41:0x00bf, B:43:0x00c8, B:51:0x00dd, B:53:0x00e3, B:55:0x00ea, B:58:0x00f4, B:59:0x00f8, B:61:0x00fe, B:66:0x010f, B:68:0x0116, B:71:0x0120, B:72:0x0124, B:74:0x012a, B:88:0x0152, B:91:0x0157, B:93:0x016b, B:99:0x017d, B:95:0x0171, B:97:0x0177, B:46:0x00d3, B:17:0x0051, B:19:0x005a, B:21:0x006a, B:102:0x0191, B:103:0x019c), top: B:107:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x010c A[EDGE_INSN: B:64:0x010c->B:65:0x010d BREAK  A[LOOP:1: B:59:0x00f8->B:125:?]] */
    /* JADX WARN: Code duplicated, block: B:66:0x010f A[Catch: all -> 0x019d, TryCatch #0 {all -> 0x019d, blocks: (B:12:0x003e, B:25:0x0087, B:27:0x008b, B:32:0x00a1, B:34:0x00a7, B:36:0x00ad, B:38:0x00b3, B:39:0x00b9, B:41:0x00bf, B:43:0x00c8, B:51:0x00dd, B:53:0x00e3, B:55:0x00ea, B:58:0x00f4, B:59:0x00f8, B:61:0x00fe, B:66:0x010f, B:68:0x0116, B:71:0x0120, B:72:0x0124, B:74:0x012a, B:88:0x0152, B:91:0x0157, B:93:0x016b, B:99:0x017d, B:95:0x0171, B:97:0x0177, B:46:0x00d3, B:17:0x0051, B:19:0x005a, B:21:0x006a, B:102:0x0191, B:103:0x019c), top: B:107:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:71:0x0120 A[Catch: all -> 0x019d, TryCatch #0 {all -> 0x019d, blocks: (B:12:0x003e, B:25:0x0087, B:27:0x008b, B:32:0x00a1, B:34:0x00a7, B:36:0x00ad, B:38:0x00b3, B:39:0x00b9, B:41:0x00bf, B:43:0x00c8, B:51:0x00dd, B:53:0x00e3, B:55:0x00ea, B:58:0x00f4, B:59:0x00f8, B:61:0x00fe, B:66:0x010f, B:68:0x0116, B:71:0x0120, B:72:0x0124, B:74:0x012a, B:88:0x0152, B:91:0x0157, B:93:0x016b, B:99:0x017d, B:95:0x0171, B:97:0x0177, B:46:0x00d3, B:17:0x0051, B:19:0x005a, B:21:0x006a, B:102:0x0191, B:103:0x019c), top: B:107:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x012a A[Catch: all -> 0x019d, TryCatch #0 {all -> 0x019d, blocks: (B:12:0x003e, B:25:0x0087, B:27:0x008b, B:32:0x00a1, B:34:0x00a7, B:36:0x00ad, B:38:0x00b3, B:39:0x00b9, B:41:0x00bf, B:43:0x00c8, B:51:0x00dd, B:53:0x00e3, B:55:0x00ea, B:58:0x00f4, B:59:0x00f8, B:61:0x00fe, B:66:0x010f, B:68:0x0116, B:71:0x0120, B:72:0x0124, B:74:0x012a, B:88:0x0152, B:91:0x0157, B:93:0x016b, B:99:0x017d, B:95:0x0171, B:97:0x0177, B:46:0x00d3, B:17:0x0051, B:19:0x005a, B:21:0x006a, B:102:0x0191, B:103:0x019c), top: B:107:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:77:0x0140 A[EDGE_INSN: B:77:0x0140->B:78:0x0141 BREAK  A[LOOP:2: B:72:0x0124->B:128:?]] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:90:0x0156  */
    /* JADX WARN: Code duplicated, block: B:91:0x0157 A[Catch: all -> 0x019d, TryCatch #0 {all -> 0x019d, blocks: (B:12:0x003e, B:25:0x0087, B:27:0x008b, B:32:0x00a1, B:34:0x00a7, B:36:0x00ad, B:38:0x00b3, B:39:0x00b9, B:41:0x00bf, B:43:0x00c8, B:51:0x00dd, B:53:0x00e3, B:55:0x00ea, B:58:0x00f4, B:59:0x00f8, B:61:0x00fe, B:66:0x010f, B:68:0x0116, B:71:0x0120, B:72:0x0124, B:74:0x012a, B:88:0x0152, B:91:0x0157, B:93:0x016b, B:99:0x017d, B:95:0x0171, B:97:0x0177, B:46:0x00d3, B:17:0x0051, B:19:0x005a, B:21:0x006a, B:102:0x0191, B:103:0x019c), top: B:107:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:95:0x0171 A[Catch: all -> 0x019d, TryCatch #0 {all -> 0x019d, blocks: (B:12:0x003e, B:25:0x0087, B:27:0x008b, B:32:0x00a1, B:34:0x00a7, B:36:0x00ad, B:38:0x00b3, B:39:0x00b9, B:41:0x00bf, B:43:0x00c8, B:51:0x00dd, B:53:0x00e3, B:55:0x00ea, B:58:0x00f4, B:59:0x00f8, B:61:0x00fe, B:66:0x010f, B:68:0x0116, B:71:0x0120, B:72:0x0124, B:74:0x012a, B:88:0x0152, B:91:0x0157, B:93:0x016b, B:99:0x017d, B:95:0x0171, B:97:0x0177, B:46:0x00d3, B:17:0x0051, B:19:0x005a, B:21:0x006a, B:102:0x0191, B:103:0x019c), top: B:107:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:97:0x0177 A[Catch: all -> 0x019d, TryCatch #0 {all -> 0x019d, blocks: (B:12:0x003e, B:25:0x0087, B:27:0x008b, B:32:0x00a1, B:34:0x00a7, B:36:0x00ad, B:38:0x00b3, B:39:0x00b9, B:41:0x00bf, B:43:0x00c8, B:51:0x00dd, B:53:0x00e3, B:55:0x00ea, B:58:0x00f4, B:59:0x00f8, B:61:0x00fe, B:66:0x010f, B:68:0x0116, B:71:0x0120, B:72:0x0124, B:74:0x012a, B:88:0x0152, B:91:0x0157, B:93:0x016b, B:99:0x017d, B:95:0x0171, B:97:0x0177, B:46:0x00d3, B:17:0x0051, B:19:0x005a, B:21:0x006a, B:102:0x0191, B:103:0x019c), top: B:107:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:98:0x017c  */
    public final Object getTracker(List<String> list, Set<? extends TrackerType> set, Integer num, boolean z, Continuation<? super Tracker> continuation) {
        AnonymousClass2 anonymousClass2;
        String str;
        AniSearch aniSearch;
        Set<? extends TrackerType> set2;
        Integer num2;
        boolean z2;
        Object obj;
        AniSearch.Data data;
        AniSearch.Data.Page page;
        ArrayList<AniSearch.Data.Page.Media> media;
        Iterator<T> it;
        Object next;
        AniSearch.Data.Page.Media media2;
        AniSearch.Data.Page.Media.CoverImage coverImage;
        AniSearch.Data.Page.Media.CoverImage coverImage2;
        String large;
        AniSearch.Data.Page.Media media3;
        boolean z3;
        AniSearch.Data.Page.Media.Title title;
        boolean z4;
        boolean z5;
        Set<? extends TrackerType> set3;
        Iterator<T> it2;
        List<String> list2;
        Iterator<T> it3;
        Integer seasonYear;
        List<String> list3 = list;
        if (continuation instanceof AnonymousClass2) {
            anonymousClass2 = (AnonymousClass2) continuation;
            if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                anonymousClass2.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass2 = new AnonymousClass2(continuation);
            }
        } else {
            anonymousClass2 = new AnonymousClass2(continuation);
        }
        Object obj2 = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass2.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj2);
                if (list3.isEmpty()) {
                    throw new IllegalArgumentException("titles must no be empty when calling getTracker".toString());
                }
                str = list3.get(0);
                AniSearch aniSearch2 = trackerCache.get(str);
                if (aniSearch2 == null) {
                    anonymousClass2.L$0 = list3;
                    anonymousClass2.L$1 = set;
                    anonymousClass2.L$2 = num;
                    anonymousClass2.L$3 = str;
                    anonymousClass2.Z$0 = z;
                    anonymousClass2.label = 1;
                    Object objSearchAnilist = searchAnilist(str, anonymousClass2);
                    if (objSearchAnilist == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj = objSearchAnilist;
                    set2 = set;
                    num2 = num;
                    z2 = z;
                } else {
                    aniSearch = aniSearch2;
                    set2 = set;
                    num2 = num;
                    z2 = z;
                }
                data = aniSearch.getData();
                if (data != null && (page = data.getPage()) != null && (media = page.getMedia()) != null) {
                    it = media.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                        media3 = (AniSearch.Data.Page.Media) next;
                        if (num2 != null) {
                            seasonYear = media3.getSeasonYear();
                            int iIntValue = num2.intValue();
                            if (seasonYear != null && seasonYear.intValue() == iIntValue) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                        } else {
                            z3 = true;
                        }
                        title = media3.getTitle();
                        if (title != null) {
                            z4 = false;
                            break;
                        }
                        list2 = list3;
                        if ((list2 instanceof Collection) || !list2.isEmpty()) {
                            it3 = list2.iterator();
                            while (true) {
                                if (it3.hasNext()) {
                                    if (title.isMatchingTitles((String) it3.next())) {
                                        z4 = true;
                                        break;
                                    }
                                }
                            }
                        }
                        z4 = false;
                        break;
                        if (set2 != null) {
                            z5 = false;
                            break;
                        }
                        set3 = set2;
                        if ((set3 instanceof Collection) || !set3.isEmpty()) {
                            it2 = set3.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    if (StringsKt.equals(((TrackerType) it2.next()).name(), media3.getFormat(), true)) {
                                        z5 = true;
                                        break;
                                    }
                                }
                            }
                        }
                        z5 = false;
                        break;
                        if (!z2) {
                            if (z4 && z5 && z3) {
                                break;
                            }
                        } else if (!z4 || (z5 && z3)) {
                            break;
                            break;
                        }
                    }
                    media2 = (AniSearch.Data.Page.Media) next;
                    if (media2 == null) {
                        Integer idMal = media2.getIdMal();
                        String strValueOf = String.valueOf(media2.getId());
                        coverImage = media2.getCoverImage();
                        if (coverImage != null || (large = coverImage.getExtraLarge()) == null) {
                            coverImage2 = media2.getCoverImage();
                            if (coverImage2 != null) {
                                large = coverImage2.getLarge();
                            } else {
                                large = null;
                            }
                        }
                        return new Tracker(idMal, null, strValueOf, large, media2.getBannerImage());
                    }
                }
                return null;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            z2 = anonymousClass2.Z$0;
            String str2 = (String) anonymousClass2.L$3;
            num2 = (Integer) anonymousClass2.L$2;
            set2 = (Set) anonymousClass2.L$1;
            List<String> list4 = (List) anonymousClass2.L$0;
            ResultKt.throwOnFailure(obj2);
            str = str2;
            list3 = list4;
            obj = obj2;
            aniSearch = (AniSearch) obj;
            if (aniSearch != null) {
                trackerCache.put(str, aniSearch);
            } else {
                aniSearch = null;
            }
            if (aniSearch == null) {
                return null;
            }
            data = aniSearch.getData();
            if (data != null) {
                it = media.iterator();
                while (true) {
                    if (it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    media3 = (AniSearch.Data.Page.Media) next;
                    if (num2 != null) {
                        seasonYear = media3.getSeasonYear();
                        int iIntValue2 = num2.intValue();
                        if (seasonYear != null) {
                            z3 = true;
                        }
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    title = media3.getTitle();
                    if (title != null) {
                        z4 = false;
                        break;
                    }
                    list2 = list3;
                    if (list2 instanceof Collection) {
                        it3 = list2.iterator();
                        while (true) {
                            if (it3.hasNext()) {
                                z4 = false;
                                break;
                            }
                            if (title.isMatchingTitles((String) it3.next())) {
                                z4 = true;
                                break;
                            }
                        }
                    } else {
                        it3 = list2.iterator();
                        while (true) {
                            if (it3.hasNext()) {
                                z4 = false;
                                break;
                            }
                            if (title.isMatchingTitles((String) it3.next())) {
                                z4 = true;
                                break;
                            }
                        }
                    }
                    if (set2 != null) {
                        z5 = false;
                        break;
                    }
                    set3 = set2;
                    if (set3 instanceof Collection) {
                        it2 = set3.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                z5 = false;
                                break;
                            }
                            if (StringsKt.equals(((TrackerType) it2.next()).name(), media3.getFormat(), true)) {
                                z5 = true;
                                break;
                            }
                        }
                    } else {
                        it2 = set3.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                z5 = false;
                                break;
                            }
                            if (StringsKt.equals(((TrackerType) it2.next()).name(), media3.getFormat(), true)) {
                                z5 = true;
                                break;
                            }
                        }
                    }
                    if (!z2) {
                        if (!z4) {
                            break;
                        }
                    } else if (z4) {
                        continue;
                    }
                }
                media2 = (AniSearch.Data.Page.Media) next;
                if (media2 == null) {
                    Integer idMal2 = media2.getIdMal();
                    String strValueOf2 = String.valueOf(media2.getId());
                    coverImage = media2.getCoverImage();
                    if (coverImage != null) {
                        coverImage2 = media2.getCoverImage();
                        if (coverImage2 != null) {
                            large = coverImage2.getLarge();
                        } else {
                            large = null;
                        }
                    } else {
                        coverImage2 = media2.getCoverImage();
                        if (coverImage2 != null) {
                            large = coverImage2.getLarge();
                        } else {
                            large = null;
                        }
                    }
                    return new Tracker(idMal2, null, strValueOf2, large, media2.getBannerImage());
                }
            }
            return null;
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object searchAnilist(String str, Continuation<? super AniSearch> continuation) {
        C06931 c06931;
        if (continuation instanceof C06931) {
            c06931 = (C06931) continuation;
            if ((c06931.label & Integer.MIN_VALUE) != 0) {
                c06931.label -= Integer.MIN_VALUE;
            } else {
                c06931 = new C06931(continuation);
            }
        } else {
            c06931 = new C06931(continuation);
        }
        Object obj = c06931.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c06931.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String string = StringsKt.trim((CharSequence) "query (\n  $page: Int = 1\n  $search: String\n  $sort: [MediaSort] = [POPULARITY_DESC, SCORE_DESC]\n  $type: MediaType\n) {\n  Page(page: $page, perPage: 20) {\n    media(\n      search: $search\n      sort: $sort\n      type: $type\n    ) {\n      id\n      idMal\n      title { romaji english }\n      coverImage { extraLarge large }\n      bannerImage\n      seasonYear\n      format\n    }\n  }\n}").toString();
            RequestBody requestBodyCreate = RequestBody.INSTANCE.create(AppUtils.INSTANCE.toJson(MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, string), TuplesKt.to("variables", MapsKt.mapOf(TuplesKt.to("search", str), TuplesKt.to("sort", "SEARCH_MATCH"), TuplesKt.to("type", "ANIME"))))), MediaType.INSTANCE.parse(RequestBodyTypes.JSON));
            Requests app2 = MainActivityKt.getApp();
            c06931.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c06931.L$1 = SpillingKt.nullOutSpilledVariable(string);
            c06931.L$2 = SpillingKt.nullOutSpilledVariable(requestBodyCreate);
            c06931.label = 1;
            Object objPost$default = Requests.post$default(app2, "https://graphql.anilist.co", null, null, null, null, null, null, null, requestBodyCreate, false, 0, null, 0L, null, false, null, c06931, 65278, null);
            if (objPost$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = objPost$default;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        try {
            ResponseParser parser = niceResponse.getParser();
            Intrinsics.checkNotNull(parser);
            return parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(AniSearch.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
