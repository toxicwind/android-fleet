package com.lagradost.cloudstream3.utils;

import android.util.Log;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: SyncUtil.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001:\u0006\u0019\u001a\u001b\u001c\u001d\u001eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u000e\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\bH\u0086@¢\u0006\u0002\u0010\u0011J2\u0010\u0012\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u000f2\u0006\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\bH\u0082@¢\u0006\u0002\u0010\u0015J&\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\u0017\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\bH\u0086@¢\u0006\u0002\u0010\u0015R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SyncUtil;", "", "<init>", "()V", "regexs", "", "Lkotlin/text/Regex;", "TAG", "", "GOGOANIME", "NINE_ANIME", "TWIST_MOE", "matchList", "", "getIdsFromUrl", "Lkotlin/Pair;", ImagesContract.URL, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIdsFromSlug", "slug", "site", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUrlsFromId", "id", "type", "SyncPage", "SyncPages", "ProviderPage", "MalSyncPage", "Anilist", "Mal", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SyncUtil {
    private static final String TAG = "SYNCUTIL";
    public static final SyncUtil INSTANCE = new SyncUtil();
    private static final List<Regex> regexs = CollectionsKt.listOf((Object[]) new Regex[]{new Regex("(9anime)\\.(?:to|center|id)/watch/.*?\\.([^/?]*)"), new Regex("(gogoanime|gogoanimes)\\..*?/category/([^/?]*)"), new Regex("(twist\\.moe)/a/([^/?]*)")});
    private static final String NINE_ANIME = "9anime";
    private static final String GOGOANIME = "Gogoanime";
    private static final String TWIST_MOE = "Twistmoe";
    private static final Map<String, String> matchList = MapsKt.mapOf(TuplesKt.to(NINE_ANIME, NINE_ANIME), TuplesKt.to("gogoanime", GOGOANIME), TuplesKt.to("gogoanimes", GOGOANIME), TuplesKt.to("twist.moe", TWIST_MOE));

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.SyncUtil$getIdsFromSlug$1, reason: invalid class name */
    /* JADX INFO: compiled from: SyncUtil.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.SyncUtil", f = "SyncUtil.kt", i = {0, 0, 0}, l = {74}, m = "getIdsFromSlug", n = {"slug", "site", ImagesContract.URL}, nl = {75}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncUtil.this.getIdsFromSlug(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.SyncUtil$getIdsFromUrl$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncUtil.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.SyncUtil", f = "SyncUtil.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {44, 49}, m = "getIdsFromUrl", n = {ImagesContract.URL, "regex", "match", "site", "slug", "realSite", "$i$a$-let-SyncUtil$getIdsFromUrl$2", "$i$a$-let-SyncUtil$getIdsFromUrl$2$1", ImagesContract.URL, "regex", "match", "site", "slug", "realSite", "$i$a$-let-SyncUtil$getIdsFromUrl$2", "$i$a$-let-SyncUtil$getIdsFromUrl$2$1", "$i$a$-run-SyncUtil$getIdsFromUrl$2$1$2"}, nl = {45, 50}, s = {"L$0", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1", "L$0", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1", "I$2"}, v = 2)
    static final class C09511 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C09511(Continuation<? super C09511> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncUtil.this.getIdsFromUrl(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.SyncUtil$getUrlsFromId$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncUtil.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.SyncUtil", f = "SyncUtil.kt", i = {0, 0, 0}, l = {93}, m = "getUrlsFromId", n = {"id", "type", ImagesContract.URL}, nl = {176}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class C09521 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C09521(Continuation<? super C09521> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncUtil.this.getUrlsFromId(null, null, this);
        }
    }

    private SyncUtil() {
    }

    /* JADX WARN: Code duplicated, block: B:22:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:26:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:28:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:31:0x011d  */
    /* JADX WARN: Code duplicated, block: B:34:0x012a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:35:0x012b  */
    /* JADX WARN: Code duplicated, block: B:37:0x0133  */
    /* JADX WARN: Code duplicated, block: B:40:0x0181  */
    /* JADX WARN: Code duplicated, block: B:45:0x0189  */
    /* JADX WARN: Code duplicated, block: B:47:0x018e  */
    /* JADX WARN: Code duplicated, block: B:53:0x00c8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:55:? A[LOOP:0: B:20:0x00b2->B:55:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x0181 -> B:41:0x0182). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x0189 -> B:46:0x018b). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:31:0x011d
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object getIdsFromUrl(java.lang.String r19, kotlin.coroutines.Continuation<? super kotlin.Pair<java.lang.String, java.lang.String>> r20) {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.SyncUtil.getIdsFromUrl(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v36 */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v38 */
    /* JADX WARN: Type inference failed for: r0v40, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r0v43, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r0v47, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r0v55 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v14, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v20 */
    public final Object getIdsFromSlug(String str, String str2, Continuation<? super Pair<String, String>> continuation) {
        AnonymousClass1 anonymousClass1;
        List list;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Object obj;
        ?? malId;
        Anilist anilist;
        Mal mal;
        Integer id;
        ?? r8;
        Anilist anilist2;
        Integer id2;
        Integer aniId;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        List list2 = null;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj2);
                Log.i(TAG, "getIdsFromSlug " + str + ' ' + str2);
                String str3 = "https://raw.githubusercontent.com/MALSync/MAL-Sync-Backup/master/data/pages/" + str2 + '/' + str + ".json";
                Requests app2 = MainActivityKt.getApp();
                TimeUnit timeUnit = TimeUnit.DAYS;
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                anonymousClass1.label = 1;
                list = null;
                list2 = null;
                try {
                    Object obj3 = Requests.get$default(app2, str3, null, null, null, null, false, 1, timeUnit, 0L, null, false, null, anonymousClass1, 3902, null);
                    if (obj3 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj2 = obj3;
                } catch (Exception e) {
                    e = e;
                    ArchComponentExtKt.logError(e);
                    return list;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj2);
                list = null;
            }
            String text = ((NiceResponse) obj2).getText();
            AppUtils appUtils = AppUtils.INSTANCE;
            if (text == null) {
                obj = list;
            } else {
                try {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(MalSyncPage.INSTANCE.serializer());
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(MalSyncPage.class), list, 2, list));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                    }
                    Object obj4 = objM8936constructorimpl;
                    if (Result.m8942isFailureimpl(obj4)) {
                        obj4 = list;
                    }
                    KSerializer kSerializer = (KSerializer) obj4;
                    if (kSerializer != null) {
                        try {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
                        } catch (SerializationException e2) {
                            ArchComponentExtKt.logError(e2);
                            objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<MalSyncPage>() { // from class: com.lagradost.cloudstream3.utils.SyncUtil$getIdsFromSlug$$inlined$tryParseJson$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<MalSyncPage>() { // from class: com.lagradost.cloudstream3.utils.SyncUtil$getIdsFromSlug$$inlined$tryParseJson$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<MalSyncPage>() { // from class: com.lagradost.cloudstream3.utils.SyncUtil$getIdsFromSlug$$inlined$tryParseJson$1
                        });
                    }
                    obj = objDecodeFromString;
                } catch (Exception unused2) {
                    obj = list;
                }
            }
            MalSyncPage malSyncPage = (MalSyncPage) obj;
            if (malSyncPage == null || (malId = malSyncPage.getMalId()) == 0) {
                if (malSyncPage == null || (mal = malSyncPage.getMal()) == null) {
                    malId = list;
                } else {
                    id = mal.getId();
                }
                if (malId == 0) {
                    malId = (malSyncPage == null || (anilist = malSyncPage.getAnilist()) == null) ? list : anilist.getMalId();
                }
            }
            if (malSyncPage != null && (aniId = malSyncPage.getAniId()) != null) {
                r8 = aniId;
            } else if (malSyncPage == null || (anilist2 = malSyncPage.getAnilist()) == null) {
                r8 = list;
            } else {
                id2 = anilist2.getId();
            }
            if (malId != 0) {
                r8 = id2;
                return TuplesKt.to(String.valueOf(malId.intValue()), r8 != 0 ? String.valueOf(r8.intValue()) : list);
            }
            r8 = id2;
            return list;
        } catch (Exception e3) {
            e = e3;
            list = list2;
        }
    }

    static /* synthetic */ Object getIdsFromSlug$default(SyncUtil syncUtil, String str, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = GOGOANIME;
        }
        return syncUtil.getIdsFromSlug(str, str2, continuation);
    }

    public static /* synthetic */ Object getUrlsFromId$default(SyncUtil syncUtil, String str, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "anilist";
        }
        return syncUtil.getUrlsFromId(str, str2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    public final Object getUrlsFromId(String str, String str2, Continuation<? super List<String>> continuation) {
        C09521 c09521;
        String str3;
        String str4 = str;
        String str5 = str2;
        if (continuation instanceof C09521) {
            c09521 = (C09521) continuation;
            if ((c09521.label & Integer.MIN_VALUE) != 0) {
                c09521.label -= Integer.MIN_VALUE;
            } else {
                c09521 = new C09521(continuation);
            }
        } else {
            c09521 = new C09521(continuation);
        }
        Object obj = c09521.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09521.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String str6 = "https://raw.githubusercontent.com/MALSync/MAL-Sync-Backup/master/data/" + str5 + "/anime/" + str4 + ".json";
            Requests app2 = MainActivityKt.getApp();
            TimeUnit timeUnit = TimeUnit.DAYS;
            c09521.L$0 = str4;
            c09521.L$1 = str5;
            c09521.L$2 = SpillingKt.nullOutSpilledVariable(str6);
            c09521.label = 1;
            C09521 c09522 = c09521;
            str3 = "/anime/";
            Object obj2 = Requests.get$default(app2, str6, null, null, null, null, false, 1, timeUnit, 0L, null, false, null, c09522, 3902, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = obj2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            String str7 = (String) c09521.L$1;
            String str8 = (String) c09521.L$0;
            ResultKt.throwOnFailure(obj);
            str5 = str7;
            str4 = str8;
            str3 = "/anime/";
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        SyncPages pages = ((SyncPage) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(SyncPage.class))).getPages();
        if (pages == null) {
            return CollectionsKt.emptyList();
        }
        Set setUnion = CollectionsKt.union(CollectionsKt.union(pages.getGogoanime().values(), pages.getNineanime().values()), pages.getTwistmoe().values());
        ArrayList arrayList = new ArrayList();
        Iterator it = setUnion.iterator();
        while (it.hasNext()) {
            String url = ((ProviderPage) it.next()).getUrl();
            if (url != null) {
                arrayList.add(url);
            }
        }
        List mutableList = CollectionsKt.toMutableList((Collection) arrayList);
        if (Intrinsics.areEqual(str5, "anilist")) {
            Iterator<MainAPI> it2 = APIHolder.INSTANCE.getApis().filter(new Function1() { // from class: com.lagradost.cloudstream3.utils.SyncUtil$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return Boolean.valueOf(SyncUtil.getUrlsFromId$lambda$1((MainAPI) obj3));
                }
            }).iterator();
            while (it2.hasNext()) {
                mutableList.add(it2.next().getMainUrl() + str3 + str4);
            }
        }
        return mutableList;
    }

    static final boolean getUrlsFromId$lambda$1(MainAPI it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return StringsKt.contains((CharSequence) it.getName(), (CharSequence) "Aniflix", true);
    }

    /* JADX INFO: compiled from: SyncUtil.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SyncUtil$SyncPage;", "", "pages", "Lcom/lagradost/cloudstream3/utils/SyncUtil$SyncPages;", "<init>", "(Lcom/lagradost/cloudstream3/utils/SyncUtil$SyncPages;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/utils/SyncUtil$SyncPages;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getPages$annotations", "()V", "getPages", "()Lcom/lagradost/cloudstream3/utils/SyncUtil$SyncPages;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SyncPage {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("Pages")
        private final SyncPages pages;

        public static /* synthetic */ SyncPage copy$default(SyncPage syncPage, SyncPages syncPages, int i, Object obj) {
            if ((i & 1) != 0) {
                syncPages = syncPage.pages;
            }
            return syncPage.copy(syncPages);
        }

        @SerialName("Pages")
        public static /* synthetic */ void getPages$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final SyncPages getPages() {
            return this.pages;
        }

        public final SyncPage copy(@JsonProperty("Pages") SyncPages pages) {
            return new SyncPage(pages);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof SyncPage) && Intrinsics.areEqual(this.pages, ((SyncPage) other).pages);
        }

        public int hashCode() {
            SyncPages syncPages = this.pages;
            if (syncPages == null) {
                return 0;
            }
            return syncPages.hashCode();
        }

        public String toString() {
            return "SyncPage(pages=" + this.pages + ')';
        }

        /* JADX INFO: compiled from: SyncUtil.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SyncUtil$SyncPage$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/SyncUtil$SyncPage;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SyncPage> serializer() {
                return SyncUtil$SyncPage$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SyncPage(int i, SyncPages syncPages, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, SyncUtil$SyncPage$$serializer.INSTANCE.getDescriptor());
            }
            this.pages = syncPages;
        }

        public SyncPage(@JsonProperty("Pages") SyncPages syncPages) {
            this.pages = syncPages;
        }

        public final SyncPages getPages() {
            return this.pages;
        }
    }

    /* JADX INFO: compiled from: SyncUtil.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0002()BI\u0012\u0014\b\u0003\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0014\b\u0003\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0014\b\u0003\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\b\u0010\tB]\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\u0015\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u0015\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u0015\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003JK\u0010\u001a\u001a\u00020\u00002\u0014\b\u0003\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0014\b\u0003\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0014\b\u0003\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0004HÖ\u0081\u0004J%\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b'R(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R(\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R(\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012¨\u0006*"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SyncUtil$SyncPages;", "", "nineanime", "", "", "Lcom/lagradost/cloudstream3/utils/SyncUtil$ProviderPage;", "gogoanime", "twistmoe", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Ljava/util/Map;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNineanime$annotations", "()V", "getNineanime", "()Ljava/util/Map;", "getGogoanime$annotations", "getGogoanime", "getTwistmoe$annotations", "getTwistmoe", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SyncPages {

        @JsonProperty(SyncUtil.GOGOANIME)
        private final Map<String, ProviderPage> gogoanime;

        @JsonProperty(SyncUtil.NINE_ANIME)
        private final Map<String, ProviderPage> nineanime;

        @JsonProperty(SyncUtil.TWIST_MOE)
        private final Map<String, ProviderPage> twistmoe;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.SyncUtil$SyncPages$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncUtil.SyncPages._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.SyncUtil$SyncPages$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncUtil.SyncPages._childSerializers$_anonymous_$0();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.SyncUtil$SyncPages$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncUtil.SyncPages._childSerializers$_anonymous_$1();
            }
        })};

        public SyncPages() {
            this((Map) null, (Map) null, (Map) null, 7, (DefaultConstructorMarker) null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new LinkedHashMapSerializer(StringSerializer.INSTANCE, SyncUtil$ProviderPage$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new LinkedHashMapSerializer(StringSerializer.INSTANCE, SyncUtil$ProviderPage$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$1() {
            return new LinkedHashMapSerializer(StringSerializer.INSTANCE, SyncUtil$ProviderPage$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SyncPages copy$default(SyncPages syncPages, Map map, Map map2, Map map3, int i, Object obj) {
            if ((i & 1) != 0) {
                map = syncPages.nineanime;
            }
            if ((i & 2) != 0) {
                map2 = syncPages.gogoanime;
            }
            if ((i & 4) != 0) {
                map3 = syncPages.twistmoe;
            }
            return syncPages.copy(map, map2, map3);
        }

        @SerialName(SyncUtil.GOGOANIME)
        public static /* synthetic */ void getGogoanime$annotations() {
        }

        @SerialName(SyncUtil.NINE_ANIME)
        public static /* synthetic */ void getNineanime$annotations() {
        }

        @SerialName(SyncUtil.TWIST_MOE)
        public static /* synthetic */ void getTwistmoe$annotations() {
        }

        public final Map<String, ProviderPage> component1() {
            return this.nineanime;
        }

        public final Map<String, ProviderPage> component2() {
            return this.gogoanime;
        }

        public final Map<String, ProviderPage> component3() {
            return this.twistmoe;
        }

        public final SyncPages copy(@JsonProperty(SyncUtil.NINE_ANIME) Map<String, ProviderPage> nineanime, @JsonProperty(SyncUtil.GOGOANIME) Map<String, ProviderPage> gogoanime, @JsonProperty(SyncUtil.TWIST_MOE) Map<String, ProviderPage> twistmoe) {
            Intrinsics.checkNotNullParameter(nineanime, "nineanime");
            Intrinsics.checkNotNullParameter(gogoanime, "gogoanime");
            Intrinsics.checkNotNullParameter(twistmoe, "twistmoe");
            return new SyncPages(nineanime, gogoanime, twistmoe);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SyncPages)) {
                return false;
            }
            SyncPages syncPages = (SyncPages) other;
            return Intrinsics.areEqual(this.nineanime, syncPages.nineanime) && Intrinsics.areEqual(this.gogoanime, syncPages.gogoanime) && Intrinsics.areEqual(this.twistmoe, syncPages.twistmoe);
        }

        public int hashCode() {
            return (((this.nineanime.hashCode() * 31) + this.gogoanime.hashCode()) * 31) + this.twistmoe.hashCode();
        }

        public String toString() {
            return "SyncPages(nineanime=" + this.nineanime + ", gogoanime=" + this.gogoanime + ", twistmoe=" + this.twistmoe + ')';
        }

        /* JADX INFO: compiled from: SyncUtil.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SyncUtil$SyncPages$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/SyncUtil$SyncPages;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SyncPages> serializer() {
                return SyncUtil$SyncPages$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SyncPages(int i, Map map, Map map2, Map map3, SerializationConstructorMarker serializationConstructorMarker) {
            this.nineanime = (i & 1) == 0 ? MapsKt.emptyMap() : map;
            if ((i & 2) == 0) {
                this.gogoanime = MapsKt.emptyMap();
            } else {
                this.gogoanime = map2;
            }
            if ((i & 4) == 0) {
                this.twistmoe = MapsKt.emptyMap();
            } else {
                this.twistmoe = map3;
            }
        }

        public SyncPages(@JsonProperty(SyncUtil.NINE_ANIME) Map<String, ProviderPage> nineanime, @JsonProperty(SyncUtil.GOGOANIME) Map<String, ProviderPage> gogoanime, @JsonProperty(SyncUtil.TWIST_MOE) Map<String, ProviderPage> twistmoe) {
            Intrinsics.checkNotNullParameter(nineanime, "nineanime");
            Intrinsics.checkNotNullParameter(gogoanime, "gogoanime");
            Intrinsics.checkNotNullParameter(twistmoe, "twistmoe");
            this.nineanime = nineanime;
            this.gogoanime = gogoanime;
            this.twistmoe = twistmoe;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(SyncPages self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.nineanime, MapsKt.emptyMap())) {
                output.encodeSerializableElement(serialDesc, 0, lazyArr[0].getValue(), self.nineanime);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.gogoanime, MapsKt.emptyMap())) {
                output.encodeSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.gogoanime);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 2) && Intrinsics.areEqual(self.twistmoe, MapsKt.emptyMap())) {
                return;
            }
            output.encodeSerializableElement(serialDesc, 2, lazyArr[2].getValue(), self.twistmoe);
        }

        public /* synthetic */ SyncPages(Map map, Map map2, Map map3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? MapsKt.emptyMap() : map, (i & 2) != 0 ? MapsKt.emptyMap() : map2, (i & 4) != 0 ? MapsKt.emptyMap() : map3);
        }

        public final Map<String, ProviderPage> getNineanime() {
            return this.nineanime;
        }

        public final Map<String, ProviderPage> getGogoanime() {
            return this.gogoanime;
        }

        public final Map<String, ProviderPage> getTwistmoe() {
            return this.twistmoe;
        }
    }

    /* JADX INFO: compiled from: SyncUtil.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/utils/SyncUtil$ProviderPage;", "", ImagesContract.URL, "", "<init>", "(Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getUrl$annotations", "()V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ProviderPage {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty(ImagesContract.URL)
        private final String url;

        public static /* synthetic */ ProviderPage copy$default(ProviderPage providerPage, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = providerPage.url;
            }
            return providerPage.copy(str);
        }

        @SerialName(ImagesContract.URL)
        public static /* synthetic */ void getUrl$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        public final ProviderPage copy(@JsonProperty(ImagesContract.URL) String url) {
            return new ProviderPage(url);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ProviderPage) && Intrinsics.areEqual(this.url, ((ProviderPage) other).url);
        }

        public int hashCode() {
            String str = this.url;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "ProviderPage(url=" + this.url + ')';
        }

        /* JADX INFO: compiled from: SyncUtil.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SyncUtil$ProviderPage$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/SyncUtil$ProviderPage;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ProviderPage> serializer() {
                return SyncUtil$ProviderPage$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ProviderPage(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, SyncUtil$ProviderPage$$serializer.INSTANCE.getDescriptor());
            }
            this.url = str;
        }

        public ProviderPage(@JsonProperty(ImagesContract.URL) String str) {
            this.url = str;
        }

        public final String getUrl() {
            return this.url;
        }
    }

    /* JADX INFO: compiled from: SyncUtil.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\bF\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 l2\u00020\u0001:\u0002klBß\u0001\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0019\u0010\u001aBÏ\u0001\b\u0010\u0012\u0006\u0010\u001b\u001a\u00020\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b\u0019\u0010\u001eJ\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010Q\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010/J\u0010\u0010R\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010/J\u0010\u0010S\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010/J\u000b\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010U\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u00109J\u0010\u0010V\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u00109J\u000b\u0010W\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0015HÆ\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jæ\u0001\u0010]\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010^J\u0014\u0010_\u001a\u00020\n2\b\u0010`\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010a\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010b\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020\u00002\u0006\u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020iH\u0001¢\u0006\u0002\bjR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010 \u001a\u0004\b$\u0010\"R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010 \u001a\u0004\b&\u0010\"R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010 \u001a\u0004\b(\u0010\"R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010 \u001a\u0004\b*\u0010\"R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010 \u001a\u0004\b,\u0010\"R \u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u00100\u0012\u0004\b-\u0010 \u001a\u0004\b.\u0010/R \u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u00100\u0012\u0004\b1\u0010 \u001a\u0004\b2\u0010/R \u0010\f\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u00100\u0012\u0004\b3\u0010 \u001a\u0004\b4\u0010/R\u001e\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b5\u0010 \u001a\u0004\b6\u0010\"R \u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010:\u0012\u0004\b7\u0010 \u001a\u0004\b8\u00109R \u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010:\u0012\u0004\b;\u0010 \u001a\u0004\b<\u00109R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b=\u0010 \u001a\u0004\b>\u0010\"R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b?\u0010 \u001a\u0004\b@\u0010\"R\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bA\u0010 \u001a\u0004\bB\u0010\"R\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00158\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bC\u0010 \u001a\u0004\bD\u0010ER\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u00178\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bF\u0010 \u001a\u0004\bG\u0010HR\u001e\u0010\u0018\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bI\u0010 \u001a\u0004\bJ\u0010\"¨\u0006m"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SyncUtil$MalSyncPage;", "", "identifier", "", "type", "page", "title", ImagesContract.URL, "image", "hentai", "", "sticky", "active", "actor", "malId", "", "aniId", "createdAt", "updatedAt", "deletedAt", "mal", "Lcom/lagradost/cloudstream3/utils/SyncUtil$Mal;", "anilist", "Lcom/lagradost/cloudstream3/utils/SyncUtil$Anilist;", "malUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/SyncUtil$Mal;Lcom/lagradost/cloudstream3/utils/SyncUtil$Anilist;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/SyncUtil$Mal;Lcom/lagradost/cloudstream3/utils/SyncUtil$Anilist;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getIdentifier$annotations", "()V", "getIdentifier", "()Ljava/lang/String;", "getType$annotations", "getType", "getPage$annotations", "getPage", "getTitle$annotations", "getTitle", "getUrl$annotations", "getUrl", "getImage$annotations", "getImage", "getHentai$annotations", "getHentai", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getSticky$annotations", "getSticky", "getActive$annotations", "getActive", "getActor$annotations", "getActor", "getMalId$annotations", "getMalId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getAniId$annotations", "getAniId", "getCreatedAt$annotations", "getCreatedAt", "getUpdatedAt$annotations", "getUpdatedAt", "getDeletedAt$annotations", "getDeletedAt", "getMal$annotations", "getMal", "()Lcom/lagradost/cloudstream3/utils/SyncUtil$Mal;", "getAnilist$annotations", "getAnilist", "()Lcom/lagradost/cloudstream3/utils/SyncUtil$Anilist;", "getMalUrl$annotations", "getMalUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/SyncUtil$Mal;Lcom/lagradost/cloudstream3/utils/SyncUtil$Anilist;Ljava/lang/String;)Lcom/lagradost/cloudstream3/utils/SyncUtil$MalSyncPage;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MalSyncPage {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("active")
        private final Boolean active;

        @JsonProperty("actor")
        private final String actor;

        @JsonProperty("aniId")
        private final Integer aniId;

        @JsonProperty("Anilist")
        private final Anilist anilist;

        @JsonProperty("createdAt")
        private final String createdAt;

        @JsonProperty("deletedAt")
        private final String deletedAt;

        @JsonProperty("hentai")
        private final Boolean hentai;

        @JsonProperty("identifier")
        private final String identifier;

        @JsonProperty("image")
        private final String image;

        @JsonProperty("Mal")
        private final Mal mal;

        @JsonProperty("malId")
        private final Integer malId;

        @JsonProperty("malUrl")
        private final String malUrl;

        @JsonProperty("page")
        private final String page;

        @JsonProperty("sticky")
        private final Boolean sticky;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("type")
        private final String type;

        @JsonProperty("updatedAt")
        private final String updatedAt;

        @JsonProperty(ImagesContract.URL)
        private final String url;

        public static /* synthetic */ MalSyncPage copy$default(MalSyncPage malSyncPage, String str, String str2, String str3, String str4, String str5, String str6, Boolean bool, Boolean bool2, Boolean bool3, String str7, Integer num, Integer num2, String str8, String str9, String str10, Mal mal, Anilist anilist, String str11, int i, Object obj) {
            String str12;
            Anilist anilist2;
            String str13 = (i & 1) != 0 ? malSyncPage.identifier : str;
            String str14 = (i & 2) != 0 ? malSyncPage.type : str2;
            String str15 = (i & 4) != 0 ? malSyncPage.page : str3;
            String str16 = (i & 8) != 0 ? malSyncPage.title : str4;
            String str17 = (i & 16) != 0 ? malSyncPage.url : str5;
            String str18 = (i & 32) != 0 ? malSyncPage.image : str6;
            Boolean bool4 = (i & 64) != 0 ? malSyncPage.hentai : bool;
            Boolean bool5 = (i & 128) != 0 ? malSyncPage.sticky : bool2;
            Boolean bool6 = (i & 256) != 0 ? malSyncPage.active : bool3;
            String str19 = (i & 512) != 0 ? malSyncPage.actor : str7;
            Integer num3 = (i & 1024) != 0 ? malSyncPage.malId : num;
            Integer num4 = (i & 2048) != 0 ? malSyncPage.aniId : num2;
            String str20 = (i & 4096) != 0 ? malSyncPage.createdAt : str8;
            String str21 = (i & 8192) != 0 ? malSyncPage.updatedAt : str9;
            String str22 = str13;
            String str23 = (i & 16384) != 0 ? malSyncPage.deletedAt : str10;
            Mal mal2 = (i & 32768) != 0 ? malSyncPage.mal : mal;
            Anilist anilist3 = (i & 65536) != 0 ? malSyncPage.anilist : anilist;
            if ((i & 131072) != 0) {
                anilist2 = anilist3;
                str12 = malSyncPage.malUrl;
            } else {
                str12 = str11;
                anilist2 = anilist3;
            }
            return malSyncPage.copy(str22, str14, str15, str16, str17, str18, bool4, bool5, bool6, str19, num3, num4, str20, str21, str23, mal2, anilist2, str12);
        }

        @SerialName("active")
        public static /* synthetic */ void getActive$annotations() {
        }

        @SerialName("actor")
        public static /* synthetic */ void getActor$annotations() {
        }

        @SerialName("aniId")
        public static /* synthetic */ void getAniId$annotations() {
        }

        @SerialName("Anilist")
        public static /* synthetic */ void getAnilist$annotations() {
        }

        @SerialName("createdAt")
        public static /* synthetic */ void getCreatedAt$annotations() {
        }

        @SerialName("deletedAt")
        public static /* synthetic */ void getDeletedAt$annotations() {
        }

        @SerialName("hentai")
        public static /* synthetic */ void getHentai$annotations() {
        }

        @SerialName("identifier")
        public static /* synthetic */ void getIdentifier$annotations() {
        }

        @SerialName("image")
        public static /* synthetic */ void getImage$annotations() {
        }

        @SerialName("Mal")
        public static /* synthetic */ void getMal$annotations() {
        }

        @SerialName("malId")
        public static /* synthetic */ void getMalId$annotations() {
        }

        @SerialName("malUrl")
        public static /* synthetic */ void getMalUrl$annotations() {
        }

        @SerialName("page")
        public static /* synthetic */ void getPage$annotations() {
        }

        @SerialName("sticky")
        public static /* synthetic */ void getSticky$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        @SerialName("updatedAt")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        @SerialName(ImagesContract.URL)
        public static /* synthetic */ void getUrl$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getIdentifier() {
            return this.identifier;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getActor() {
            return this.actor;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Integer getMalId() {
            return this.malId;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final Integer getAniId() {
            return this.aniId;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getCreatedAt() {
            return this.createdAt;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getDeletedAt() {
            return this.deletedAt;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final Mal getMal() {
            return this.mal;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final Anilist getAnilist() {
            return this.anilist;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final String getMalUrl() {
            return this.malUrl;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPage() {
            return this.page;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getImage() {
            return this.image;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Boolean getHentai() {
            return this.hentai;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Boolean getSticky() {
            return this.sticky;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Boolean getActive() {
            return this.active;
        }

        public final MalSyncPage copy(@JsonProperty("identifier") String identifier, @JsonProperty("type") String type, @JsonProperty("page") String page, @JsonProperty("title") String title, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("image") String image, @JsonProperty("hentai") Boolean hentai, @JsonProperty("sticky") Boolean sticky, @JsonProperty("active") Boolean active, @JsonProperty("actor") String actor, @JsonProperty("malId") Integer malId, @JsonProperty("aniId") Integer aniId, @JsonProperty("createdAt") String createdAt, @JsonProperty("updatedAt") String updatedAt, @JsonProperty("deletedAt") String deletedAt, @JsonProperty("Mal") Mal mal, @JsonProperty("Anilist") Anilist anilist, @JsonProperty("malUrl") String malUrl) {
            return new MalSyncPage(identifier, type, page, title, url, image, hentai, sticky, active, actor, malId, aniId, createdAt, updatedAt, deletedAt, mal, anilist, malUrl);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MalSyncPage)) {
                return false;
            }
            MalSyncPage malSyncPage = (MalSyncPage) other;
            return Intrinsics.areEqual(this.identifier, malSyncPage.identifier) && Intrinsics.areEqual(this.type, malSyncPage.type) && Intrinsics.areEqual(this.page, malSyncPage.page) && Intrinsics.areEqual(this.title, malSyncPage.title) && Intrinsics.areEqual(this.url, malSyncPage.url) && Intrinsics.areEqual(this.image, malSyncPage.image) && Intrinsics.areEqual(this.hentai, malSyncPage.hentai) && Intrinsics.areEqual(this.sticky, malSyncPage.sticky) && Intrinsics.areEqual(this.active, malSyncPage.active) && Intrinsics.areEqual(this.actor, malSyncPage.actor) && Intrinsics.areEqual(this.malId, malSyncPage.malId) && Intrinsics.areEqual(this.aniId, malSyncPage.aniId) && Intrinsics.areEqual(this.createdAt, malSyncPage.createdAt) && Intrinsics.areEqual(this.updatedAt, malSyncPage.updatedAt) && Intrinsics.areEqual(this.deletedAt, malSyncPage.deletedAt) && Intrinsics.areEqual(this.mal, malSyncPage.mal) && Intrinsics.areEqual(this.anilist, malSyncPage.anilist) && Intrinsics.areEqual(this.malUrl, malSyncPage.malUrl);
        }

        public int hashCode() {
            String str = this.identifier;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.type;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.page;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.title;
            int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.url;
            int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.image;
            int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
            Boolean bool = this.hentai;
            int iHashCode7 = (iHashCode6 + (bool == null ? 0 : bool.hashCode())) * 31;
            Boolean bool2 = this.sticky;
            int iHashCode8 = (iHashCode7 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
            Boolean bool3 = this.active;
            int iHashCode9 = (iHashCode8 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
            String str7 = this.actor;
            int iHashCode10 = (iHashCode9 + (str7 == null ? 0 : str7.hashCode())) * 31;
            Integer num = this.malId;
            int iHashCode11 = (iHashCode10 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.aniId;
            int iHashCode12 = (iHashCode11 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str8 = this.createdAt;
            int iHashCode13 = (iHashCode12 + (str8 == null ? 0 : str8.hashCode())) * 31;
            String str9 = this.updatedAt;
            int iHashCode14 = (iHashCode13 + (str9 == null ? 0 : str9.hashCode())) * 31;
            String str10 = this.deletedAt;
            int iHashCode15 = (iHashCode14 + (str10 == null ? 0 : str10.hashCode())) * 31;
            Mal mal = this.mal;
            int iHashCode16 = (iHashCode15 + (mal == null ? 0 : mal.hashCode())) * 31;
            Anilist anilist = this.anilist;
            int iHashCode17 = (iHashCode16 + (anilist == null ? 0 : anilist.hashCode())) * 31;
            String str11 = this.malUrl;
            return iHashCode17 + (str11 != null ? str11.hashCode() : 0);
        }

        public String toString() {
            return "MalSyncPage(identifier=" + this.identifier + ", type=" + this.type + ", page=" + this.page + ", title=" + this.title + ", url=" + this.url + ", image=" + this.image + ", hentai=" + this.hentai + ", sticky=" + this.sticky + ", active=" + this.active + ", actor=" + this.actor + ", malId=" + this.malId + ", aniId=" + this.aniId + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", deletedAt=" + this.deletedAt + ", mal=" + this.mal + ", anilist=" + this.anilist + ", malUrl=" + this.malUrl + ')';
        }

        /* JADX INFO: compiled from: SyncUtil.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SyncUtil$MalSyncPage$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/SyncUtil$MalSyncPage;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MalSyncPage> serializer() {
                return SyncUtil$MalSyncPage$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MalSyncPage(int i, String str, String str2, String str3, String str4, String str5, String str6, Boolean bool, Boolean bool2, Boolean bool3, String str7, Integer num, Integer num2, String str8, String str9, String str10, Mal mal, Anilist anilist, String str11, SerializationConstructorMarker serializationConstructorMarker) {
            if (262143 != (i & 262143)) {
                PluginExceptionsKt.throwMissingFieldException(i, 262143, SyncUtil$MalSyncPage$$serializer.INSTANCE.getDescriptor());
            }
            this.identifier = str;
            this.type = str2;
            this.page = str3;
            this.title = str4;
            this.url = str5;
            this.image = str6;
            this.hentai = bool;
            this.sticky = bool2;
            this.active = bool3;
            this.actor = str7;
            this.malId = num;
            this.aniId = num2;
            this.createdAt = str8;
            this.updatedAt = str9;
            this.deletedAt = str10;
            this.mal = mal;
            this.anilist = anilist;
            this.malUrl = str11;
        }

        public MalSyncPage(@JsonProperty("identifier") String str, @JsonProperty("type") String str2, @JsonProperty("page") String str3, @JsonProperty("title") String str4, @JsonProperty(ImagesContract.URL) String str5, @JsonProperty("image") String str6, @JsonProperty("hentai") Boolean bool, @JsonProperty("sticky") Boolean bool2, @JsonProperty("active") Boolean bool3, @JsonProperty("actor") String str7, @JsonProperty("malId") Integer num, @JsonProperty("aniId") Integer num2, @JsonProperty("createdAt") String str8, @JsonProperty("updatedAt") String str9, @JsonProperty("deletedAt") String str10, @JsonProperty("Mal") Mal mal, @JsonProperty("Anilist") Anilist anilist, @JsonProperty("malUrl") String str11) {
            this.identifier = str;
            this.type = str2;
            this.page = str3;
            this.title = str4;
            this.url = str5;
            this.image = str6;
            this.hentai = bool;
            this.sticky = bool2;
            this.active = bool3;
            this.actor = str7;
            this.malId = num;
            this.aniId = num2;
            this.createdAt = str8;
            this.updatedAt = str9;
            this.deletedAt = str10;
            this.mal = mal;
            this.anilist = anilist;
            this.malUrl = str11;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MalSyncPage self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.identifier);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.type);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.page);
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.title);
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.url);
            output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.image);
            output.encodeNullableSerializableElement(serialDesc, 6, BooleanSerializer.INSTANCE, self.hentai);
            output.encodeNullableSerializableElement(serialDesc, 7, BooleanSerializer.INSTANCE, self.sticky);
            output.encodeNullableSerializableElement(serialDesc, 8, BooleanSerializer.INSTANCE, self.active);
            output.encodeNullableSerializableElement(serialDesc, 9, StringSerializer.INSTANCE, self.actor);
            output.encodeNullableSerializableElement(serialDesc, 10, IntSerializer.INSTANCE, self.malId);
            output.encodeNullableSerializableElement(serialDesc, 11, IntSerializer.INSTANCE, self.aniId);
            output.encodeNullableSerializableElement(serialDesc, 12, StringSerializer.INSTANCE, self.createdAt);
            output.encodeNullableSerializableElement(serialDesc, 13, StringSerializer.INSTANCE, self.updatedAt);
            output.encodeNullableSerializableElement(serialDesc, 14, StringSerializer.INSTANCE, self.deletedAt);
            output.encodeNullableSerializableElement(serialDesc, 15, SyncUtil$Mal$$serializer.INSTANCE, self.mal);
            output.encodeNullableSerializableElement(serialDesc, 16, SyncUtil$Anilist$$serializer.INSTANCE, self.anilist);
            output.encodeNullableSerializableElement(serialDesc, 17, StringSerializer.INSTANCE, self.malUrl);
        }

        public final String getIdentifier() {
            return this.identifier;
        }

        public final String getType() {
            return this.type;
        }

        public final String getPage() {
            return this.page;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getImage() {
            return this.image;
        }

        public final Boolean getHentai() {
            return this.hentai;
        }

        public final Boolean getSticky() {
            return this.sticky;
        }

        public final Boolean getActive() {
            return this.active;
        }

        public final String getActor() {
            return this.actor;
        }

        public final Integer getMalId() {
            return this.malId;
        }

        public final Integer getAniId() {
            return this.aniId;
        }

        public final String getCreatedAt() {
            return this.createdAt;
        }

        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final String getDeletedAt() {
            return this.deletedAt;
        }

        public final Mal getMal() {
            return this.mal;
        }

        public final Anilist getAnilist() {
            return this.anilist;
        }

        public final String getMalUrl() {
            return this.malUrl;
        }
    }

    /* JADX INFO: compiled from: SyncUtil.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 L2\u00020\u0001:\u0002KLB\u008b\u0001\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0010\u0010\u0011B\u0089\u0001\b\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0010\u0010\u0015J\u0010\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J\u000b\u00104\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010*J\u000b\u0010:\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0092\u0001\u0010=\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010>J\u0014\u0010?\u001a\u00020\f2\b\u0010@\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010A\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010B\u001a\u00020\u0006HÖ\u0081\u0004J%\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\u00002\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020IH\u0001¢\u0006\u0002\bJR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001a\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001a\u0012\u0004\b\u001b\u0010\u0017\u001a\u0004\b\u001c\u0010\u0019R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0017\u001a\u0004\b\u001e\u0010\u001fR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0017\u001a\u0004\b!\u0010\u001fR\u001e\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0017\u001a\u0004\b#\u0010\u001fR\u001e\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u0017\u001a\u0004\b%\u0010\u001fR\u001e\u0010\n\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u0017\u001a\u0004\b'\u0010\u001fR \u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010+\u0012\u0004\b(\u0010\u0017\u001a\u0004\b)\u0010*R\u001e\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b,\u0010\u0017\u001a\u0004\b-\u0010\u001fR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b.\u0010\u0017\u001a\u0004\b/\u0010\u001fR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b0\u0010\u0017\u001a\u0004\b1\u0010\u001f¨\u0006M"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SyncUtil$Anilist;", "", "id", "", "malId", "type", "", "title", ImagesContract.URL, "image", "category", "hentai", "", "createdAt", "updatedAt", "deletedAt", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMalId$annotations", "getMalId", "getType$annotations", "getType", "()Ljava/lang/String;", "getTitle$annotations", "getTitle", "getUrl$annotations", "getUrl", "getImage$annotations", "getImage", "getCategory$annotations", "getCategory", "getHentai$annotations", "getHentai", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getCreatedAt$annotations", "getCreatedAt", "getUpdatedAt$annotations", "getUpdatedAt", "getDeletedAt$annotations", "getDeletedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/lagradost/cloudstream3/utils/SyncUtil$Anilist;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Anilist {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("category")
        private final String category;

        @JsonProperty("createdAt")
        private final String createdAt;

        @JsonProperty("deletedAt")
        private final String deletedAt;

        @JsonProperty("hentai")
        private final Boolean hentai;

        @JsonProperty("id")
        private final Integer id;

        @JsonProperty("image")
        private final String image;

        @JsonProperty("malId")
        private final Integer malId;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("type")
        private final String type;

        @JsonProperty("updatedAt")
        private final String updatedAt;

        @JsonProperty(ImagesContract.URL)
        private final String url;

        public static /* synthetic */ Anilist copy$default(Anilist anilist, Integer num, Integer num2, String str, String str2, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, int i, Object obj) {
            if ((i & 1) != 0) {
                num = anilist.id;
            }
            if ((i & 2) != 0) {
                num2 = anilist.malId;
            }
            if ((i & 4) != 0) {
                str = anilist.type;
            }
            if ((i & 8) != 0) {
                str2 = anilist.title;
            }
            if ((i & 16) != 0) {
                str3 = anilist.url;
            }
            if ((i & 32) != 0) {
                str4 = anilist.image;
            }
            if ((i & 64) != 0) {
                str5 = anilist.category;
            }
            if ((i & 128) != 0) {
                bool = anilist.hentai;
            }
            if ((i & 256) != 0) {
                str6 = anilist.createdAt;
            }
            if ((i & 512) != 0) {
                str7 = anilist.updatedAt;
            }
            if ((i & 1024) != 0) {
                str8 = anilist.deletedAt;
            }
            String str9 = str7;
            String str10 = str8;
            Boolean bool2 = bool;
            String str11 = str6;
            String str12 = str4;
            String str13 = str5;
            String str14 = str3;
            String str15 = str;
            return anilist.copy(num, num2, str15, str2, str14, str12, str13, bool2, str11, str9, str10);
        }

        @SerialName("category")
        public static /* synthetic */ void getCategory$annotations() {
        }

        @SerialName("createdAt")
        public static /* synthetic */ void getCreatedAt$annotations() {
        }

        @SerialName("deletedAt")
        public static /* synthetic */ void getDeletedAt$annotations() {
        }

        @SerialName("hentai")
        public static /* synthetic */ void getHentai$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("image")
        public static /* synthetic */ void getImage$annotations() {
        }

        @SerialName("malId")
        public static /* synthetic */ void getMalId$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        @SerialName("updatedAt")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        @SerialName(ImagesContract.URL)
        public static /* synthetic */ void getUrl$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getDeletedAt() {
            return this.deletedAt;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getMalId() {
            return this.malId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getImage() {
            return this.image;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getCategory() {
            return this.category;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Boolean getHentai() {
            return this.hentai;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getCreatedAt() {
            return this.createdAt;
        }

        public final Anilist copy(@JsonProperty("id") Integer id, @JsonProperty("malId") Integer malId, @JsonProperty("type") String type, @JsonProperty("title") String title, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("image") String image, @JsonProperty("category") String category, @JsonProperty("hentai") Boolean hentai, @JsonProperty("createdAt") String createdAt, @JsonProperty("updatedAt") String updatedAt, @JsonProperty("deletedAt") String deletedAt) {
            return new Anilist(id, malId, type, title, url, image, category, hentai, createdAt, updatedAt, deletedAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Anilist)) {
                return false;
            }
            Anilist anilist = (Anilist) other;
            return Intrinsics.areEqual(this.id, anilist.id) && Intrinsics.areEqual(this.malId, anilist.malId) && Intrinsics.areEqual(this.type, anilist.type) && Intrinsics.areEqual(this.title, anilist.title) && Intrinsics.areEqual(this.url, anilist.url) && Intrinsics.areEqual(this.image, anilist.image) && Intrinsics.areEqual(this.category, anilist.category) && Intrinsics.areEqual(this.hentai, anilist.hentai) && Intrinsics.areEqual(this.createdAt, anilist.createdAt) && Intrinsics.areEqual(this.updatedAt, anilist.updatedAt) && Intrinsics.areEqual(this.deletedAt, anilist.deletedAt);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            Integer num2 = this.malId;
            int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str = this.type;
            int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.title;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.url;
            int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.image;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.category;
            int iHashCode7 = (iHashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
            Boolean bool = this.hentai;
            int iHashCode8 = (iHashCode7 + (bool == null ? 0 : bool.hashCode())) * 31;
            String str6 = this.createdAt;
            int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.updatedAt;
            int iHashCode10 = (iHashCode9 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.deletedAt;
            return iHashCode10 + (str8 != null ? str8.hashCode() : 0);
        }

        public String toString() {
            return "Anilist(id=" + this.id + ", malId=" + this.malId + ", type=" + this.type + ", title=" + this.title + ", url=" + this.url + ", image=" + this.image + ", category=" + this.category + ", hentai=" + this.hentai + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", deletedAt=" + this.deletedAt + ')';
        }

        /* JADX INFO: compiled from: SyncUtil.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SyncUtil$Anilist$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/SyncUtil$Anilist;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Anilist> serializer() {
                return SyncUtil$Anilist$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Anilist(int i, Integer num, Integer num2, String str, String str2, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, SerializationConstructorMarker serializationConstructorMarker) {
            if (2047 != (i & 2047)) {
                PluginExceptionsKt.throwMissingFieldException(i, 2047, SyncUtil$Anilist$$serializer.INSTANCE.getDescriptor());
            }
            this.id = num;
            this.malId = num2;
            this.type = str;
            this.title = str2;
            this.url = str3;
            this.image = str4;
            this.category = str5;
            this.hentai = bool;
            this.createdAt = str6;
            this.updatedAt = str7;
            this.deletedAt = str8;
        }

        public Anilist(@JsonProperty("id") Integer num, @JsonProperty("malId") Integer num2, @JsonProperty("type") String str, @JsonProperty("title") String str2, @JsonProperty(ImagesContract.URL) String str3, @JsonProperty("image") String str4, @JsonProperty("category") String str5, @JsonProperty("hentai") Boolean bool, @JsonProperty("createdAt") String str6, @JsonProperty("updatedAt") String str7, @JsonProperty("deletedAt") String str8) {
            this.id = num;
            this.malId = num2;
            this.type = str;
            this.title = str2;
            this.url = str3;
            this.image = str4;
            this.category = str5;
            this.hentai = bool;
            this.createdAt = str6;
            this.updatedAt = str7;
            this.deletedAt = str8;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Anilist self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.malId);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.type);
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.title);
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.url);
            output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.image);
            output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.category);
            output.encodeNullableSerializableElement(serialDesc, 7, BooleanSerializer.INSTANCE, self.hentai);
            output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.createdAt);
            output.encodeNullableSerializableElement(serialDesc, 9, StringSerializer.INSTANCE, self.updatedAt);
            output.encodeNullableSerializableElement(serialDesc, 10, StringSerializer.INSTANCE, self.deletedAt);
        }

        public final Integer getId() {
            return this.id;
        }

        public final Integer getMalId() {
            return this.malId;
        }

        public final String getType() {
            return this.type;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getImage() {
            return this.image;
        }

        public final String getCategory() {
            return this.category;
        }

        public final Boolean getHentai() {
            return this.hentai;
        }

        public final String getCreatedAt() {
            return this.createdAt;
        }

        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final String getDeletedAt() {
            return this.deletedAt;
        }
    }

    /* JADX INFO: compiled from: SyncUtil.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 H2\u00020\u0001:\u0002GHB\u007f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u000f\u0010\u0010B\u007f\b\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u000f\u0010\u0014J\u0010\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u00100\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010'J\u000b\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0086\u0001\u00109\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010:J\u0014\u0010;\u001a\u00020\u000b2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010=\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010>\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\u00002\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020EH\u0001¢\u0006\u0002\bFR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0019\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u001e\u0010\u001cR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0016\u001a\u0004\b \u0010\u001cR\u001e\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u0016\u001a\u0004\b\"\u0010\u001cR\u001e\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u0016\u001a\u0004\b$\u0010\u001cR \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010(\u0012\u0004\b%\u0010\u0016\u001a\u0004\b&\u0010'R\u001e\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u0016\u001a\u0004\b*\u0010\u001cR\u001e\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010\u0016\u001a\u0004\b,\u0010\u001cR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b-\u0010\u0016\u001a\u0004\b.\u0010\u001c¨\u0006I"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SyncUtil$Mal;", "", "id", "", "type", "", "title", ImagesContract.URL, "image", "category", "hentai", "", "createdAt", "updatedAt", "deletedAt", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getType$annotations", "getType", "()Ljava/lang/String;", "getTitle$annotations", "getTitle", "getUrl$annotations", "getUrl", "getImage$annotations", "getImage", "getCategory$annotations", "getCategory", "getHentai$annotations", "getHentai", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getCreatedAt$annotations", "getCreatedAt", "getUpdatedAt$annotations", "getUpdatedAt", "getDeletedAt$annotations", "getDeletedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/lagradost/cloudstream3/utils/SyncUtil$Mal;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Mal {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("category")
        private final String category;

        @JsonProperty("createdAt")
        private final String createdAt;

        @JsonProperty("deletedAt")
        private final String deletedAt;

        @JsonProperty("hentai")
        private final Boolean hentai;

        @JsonProperty("id")
        private final Integer id;

        @JsonProperty("image")
        private final String image;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("type")
        private final String type;

        @JsonProperty("updatedAt")
        private final String updatedAt;

        @JsonProperty(ImagesContract.URL)
        private final String url;

        public static /* synthetic */ Mal copy$default(Mal mal, Integer num, String str, String str2, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, int i, Object obj) {
            if ((i & 1) != 0) {
                num = mal.id;
            }
            if ((i & 2) != 0) {
                str = mal.type;
            }
            if ((i & 4) != 0) {
                str2 = mal.title;
            }
            if ((i & 8) != 0) {
                str3 = mal.url;
            }
            if ((i & 16) != 0) {
                str4 = mal.image;
            }
            if ((i & 32) != 0) {
                str5 = mal.category;
            }
            if ((i & 64) != 0) {
                bool = mal.hentai;
            }
            if ((i & 128) != 0) {
                str6 = mal.createdAt;
            }
            if ((i & 256) != 0) {
                str7 = mal.updatedAt;
            }
            if ((i & 512) != 0) {
                str8 = mal.deletedAt;
            }
            String str9 = str7;
            String str10 = str8;
            Boolean bool2 = bool;
            String str11 = str6;
            String str12 = str4;
            String str13 = str5;
            return mal.copy(num, str, str2, str3, str12, str13, bool2, str11, str9, str10);
        }

        @SerialName("category")
        public static /* synthetic */ void getCategory$annotations() {
        }

        @SerialName("createdAt")
        public static /* synthetic */ void getCreatedAt$annotations() {
        }

        @SerialName("deletedAt")
        public static /* synthetic */ void getDeletedAt$annotations() {
        }

        @SerialName("hentai")
        public static /* synthetic */ void getHentai$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("image")
        public static /* synthetic */ void getImage$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        @SerialName("updatedAt")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        @SerialName(ImagesContract.URL)
        public static /* synthetic */ void getUrl$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getDeletedAt() {
            return this.deletedAt;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getImage() {
            return this.image;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getCategory() {
            return this.category;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Boolean getHentai() {
            return this.hentai;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getCreatedAt() {
            return this.createdAt;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final Mal copy(@JsonProperty("id") Integer id, @JsonProperty("type") String type, @JsonProperty("title") String title, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("image") String image, @JsonProperty("category") String category, @JsonProperty("hentai") Boolean hentai, @JsonProperty("createdAt") String createdAt, @JsonProperty("updatedAt") String updatedAt, @JsonProperty("deletedAt") String deletedAt) {
            return new Mal(id, type, title, url, image, category, hentai, createdAt, updatedAt, deletedAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Mal)) {
                return false;
            }
            Mal mal = (Mal) other;
            return Intrinsics.areEqual(this.id, mal.id) && Intrinsics.areEqual(this.type, mal.type) && Intrinsics.areEqual(this.title, mal.title) && Intrinsics.areEqual(this.url, mal.url) && Intrinsics.areEqual(this.image, mal.image) && Intrinsics.areEqual(this.category, mal.category) && Intrinsics.areEqual(this.hentai, mal.hentai) && Intrinsics.areEqual(this.createdAt, mal.createdAt) && Intrinsics.areEqual(this.updatedAt, mal.updatedAt) && Intrinsics.areEqual(this.deletedAt, mal.deletedAt);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.type;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.title;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.url;
            int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.image;
            int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.category;
            int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
            Boolean bool = this.hentai;
            int iHashCode7 = (iHashCode6 + (bool == null ? 0 : bool.hashCode())) * 31;
            String str6 = this.createdAt;
            int iHashCode8 = (iHashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.updatedAt;
            int iHashCode9 = (iHashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.deletedAt;
            return iHashCode9 + (str8 != null ? str8.hashCode() : 0);
        }

        public String toString() {
            return "Mal(id=" + this.id + ", type=" + this.type + ", title=" + this.title + ", url=" + this.url + ", image=" + this.image + ", category=" + this.category + ", hentai=" + this.hentai + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", deletedAt=" + this.deletedAt + ')';
        }

        /* JADX INFO: compiled from: SyncUtil.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SyncUtil$Mal$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/SyncUtil$Mal;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Mal> serializer() {
                return SyncUtil$Mal$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Mal(int i, Integer num, String str, String str2, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, SerializationConstructorMarker serializationConstructorMarker) {
            if (1023 != (i & AnalyticsListener.EVENT_DRM_KEYS_LOADED)) {
                PluginExceptionsKt.throwMissingFieldException(i, AnalyticsListener.EVENT_DRM_KEYS_LOADED, SyncUtil$Mal$$serializer.INSTANCE.getDescriptor());
            }
            this.id = num;
            this.type = str;
            this.title = str2;
            this.url = str3;
            this.image = str4;
            this.category = str5;
            this.hentai = bool;
            this.createdAt = str6;
            this.updatedAt = str7;
            this.deletedAt = str8;
        }

        public Mal(@JsonProperty("id") Integer num, @JsonProperty("type") String str, @JsonProperty("title") String str2, @JsonProperty(ImagesContract.URL) String str3, @JsonProperty("image") String str4, @JsonProperty("category") String str5, @JsonProperty("hentai") Boolean bool, @JsonProperty("createdAt") String str6, @JsonProperty("updatedAt") String str7, @JsonProperty("deletedAt") String str8) {
            this.id = num;
            this.type = str;
            this.title = str2;
            this.url = str3;
            this.image = str4;
            this.category = str5;
            this.hentai = bool;
            this.createdAt = str6;
            this.updatedAt = str7;
            this.deletedAt = str8;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Mal self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.type);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.title);
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.url);
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.image);
            output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.category);
            output.encodeNullableSerializableElement(serialDesc, 6, BooleanSerializer.INSTANCE, self.hentai);
            output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.createdAt);
            output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.updatedAt);
            output.encodeNullableSerializableElement(serialDesc, 9, StringSerializer.INSTANCE, self.deletedAt);
        }

        public final Integer getId() {
            return this.id;
        }

        public final String getType() {
            return this.type;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getImage() {
            return this.image;
        }

        public final String getCategory() {
            return this.category;
        }

        public final Boolean getHentai() {
            return this.hentai;
        }

        public final String getCreatedAt() {
            return this.createdAt;
        }

        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final String getDeletedAt() {
            return this.deletedAt;
        }
    }
}
