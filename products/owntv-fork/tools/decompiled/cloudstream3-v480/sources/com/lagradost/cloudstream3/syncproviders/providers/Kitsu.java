package com.lagradost.cloudstream3.syncproviders.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.net.HttpHeaders;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: KitsuApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0082@¢\u0006\u0002\u0010\bJ:\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0018J,\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f2\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0086@¢\u0006\u0002\u0010\u001cR2\u0010\t\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u000b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu;", "", "<init>", "()V", "getKitsuData", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse;", SearchIntents.EXTRA_QUERY, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cache", "", "Lkotlin/Pair;", "", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Node;", "isEnabled", "", "()Z", "setEnabled", "(Z)V", "getEpisodesDetails", "malId", "anilistId", "isResponseRequired", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getKitsuEpisodesDetails", "id", "site", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "KitsuResponse", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Kitsu {
    public static final Kitsu INSTANCE = new Kitsu();
    private static final Map<Pair<String, String>, Map<Integer, KitsuResponse.Node>> cache = new LinkedHashMap();
    private static boolean isEnabled = true;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.Kitsu$getEpisodesDetails$1, reason: invalid class name */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.Kitsu", f = "KitsuApi.kt", i = {0, 0, 0, 1, 1, 1}, l = {689, 697}, m = "getEpisodesDetails", n = {"malId", "anilistId", "isResponseRequired", "malId", "anilistId", "isResponseRequired"}, nl = {690, 698}, s = {"L$0", "L$1", "Z$0", "L$0", "L$1", "Z$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Kitsu.this.getEpisodesDetails(null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.Kitsu$getKitsuData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.Kitsu", f = "KitsuApi.kt", i = {0, 0}, l = {669}, m = "getKitsuData", n = {SearchIntents.EXTRA_QUERY, "headers"}, nl = {673}, s = {"L$0", "L$1"}, v = 2)
    static final class C07811 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07811(Continuation<? super C07811> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Kitsu.this.getKitsuData(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.Kitsu$getKitsuEpisodesDetails$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.Kitsu", f = "KitsuApi.kt", i = {0, 0, 0}, l = {744}, m = "getKitsuEpisodesDetails", n = {"id", "site", SearchIntents.EXTRA_QUERY}, nl = {745}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class C07821 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C07821(Continuation<? super C07821> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Kitsu.this.getKitsuEpisodesDetails(null, null, this);
        }
    }

    private Kitsu() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object getKitsuData(String str, Continuation<? super KitsuResponse> continuation) {
        C07811 c07811;
        if (continuation instanceof C07811) {
            c07811 = (C07811) continuation;
            if ((c07811.label & Integer.MIN_VALUE) != 0) {
                c07811.label -= Integer.MIN_VALUE;
            } else {
                c07811 = new C07811(continuation);
            }
        } else {
            c07811 = new C07811(continuation);
        }
        Object obj = c07811.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07811.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Content-Type", "application/json"), TuplesKt.to("Accept", "application/json"), TuplesKt.to("Connection", "keep-alive"), TuplesKt.to(HttpHeaders.DNT, "1"), TuplesKt.to("Origin", "https://kitsu.io"));
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf2 = MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, str));
            c07811.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c07811.L$1 = SpillingKt.nullOutSpilledVariable(mapMapOf);
            c07811.label = 1;
            Object objPost$default = Requests.post$default(app2, "https://kitsu.io/api/graphql", mapMapOf, null, null, null, mapMapOf2, null, null, null, false, 0, null, 0L, null, false, null, c07811, 65500, null);
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
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        return parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(KitsuResponse.class));
    }

    public final boolean isEnabled() {
        return isEnabled;
    }

    public final void setEnabled(boolean z) {
        isEnabled = z;
    }

    public static /* synthetic */ Object getEpisodesDetails$default(Kitsu kitsu, String str, String str2, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return kitsu.getEpisodesDetails(str, str2, z, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x008f A[Catch: Exception -> 0x0039, TRY_ENTER, TryCatch #0 {Exception -> 0x0039, blocks: (B:13:0x0034, B:45:0x00a8, B:47:0x00ac, B:42:0x008f), top: B:53:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a5, code lost:
    
        if (r11 == r1) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getEpisodesDetails(java.lang.String r8, java.lang.String r9, boolean r10, kotlin.coroutines.Continuation<? super java.util.Map<java.lang.Integer, com.lagradost.cloudstream3.syncproviders.providers.Kitsu.KitsuResponse.Node>> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof com.lagradost.cloudstream3.syncproviders.providers.Kitsu.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            com.lagradost.cloudstream3.syncproviders.providers.Kitsu$getEpisodesDetails$1 r0 = (com.lagradost.cloudstream3.syncproviders.providers.Kitsu.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            com.lagradost.cloudstream3.syncproviders.providers.Kitsu$getEpisodesDetails$1 r0 = new com.lagradost.cloudstream3.syncproviders.providers.Kitsu$getEpisodesDetails$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L5a
            if (r2 == r5) goto L44
            if (r2 != r4) goto L3c
            boolean r7 = r0.Z$0
            java.lang.Object r7 = r0.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Exception -> L39
            goto La8
        L39:
            r7 = move-exception
            goto Lb4
        L3c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L44:
            boolean r8 = r0.Z$0
            java.lang.Object r9 = r0.L$1
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r10 = r0.L$0
            java.lang.String r10 = (java.lang.String) r10
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Exception -> L55
            r6 = r10
            r10 = r8
            r8 = r6
            goto L7b
        L55:
            r11 = move-exception
            r6 = r10
            r10 = r8
            r8 = r6
            goto L88
        L5a:
            kotlin.ResultKt.throwOnFailure(r11)
            if (r10 != 0) goto L64
            boolean r11 = com.lagradost.cloudstream3.syncproviders.providers.Kitsu.isEnabled
            if (r11 != 0) goto L64
            return r3
        L64:
            if (r9 == 0) goto L8d
            java.lang.String r11 = "ANILIST_ANIME"
            r0.L$0 = r8     // Catch: java.lang.Exception -> L87
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)     // Catch: java.lang.Exception -> L87
            r0.L$1 = r2     // Catch: java.lang.Exception -> L87
            r0.Z$0 = r10     // Catch: java.lang.Exception -> L87
            r0.label = r5     // Catch: java.lang.Exception -> L87
            java.lang.Object r11 = r7.getKitsuEpisodesDetails(r9, r11, r0)     // Catch: java.lang.Exception -> L87
            if (r11 != r1) goto L7b
            goto La7
        L7b:
            java.util.Map r11 = (java.util.Map) r11     // Catch: java.lang.Exception -> L87
            if (r11 == 0) goto L8d
            boolean r2 = r11.isEmpty()     // Catch: java.lang.Exception -> L87
            if (r2 == 0) goto L86
            goto L8d
        L86:
            return r11
        L87:
            r11 = move-exception
        L88:
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            com.lagradost.cloudstream3.mvvm.ArchComponentExtKt.logError(r11)
        L8d:
            if (r8 == 0) goto Lb9
            java.lang.String r11 = "MYANIMELIST_ANIME"
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch: java.lang.Exception -> L39
            r0.L$0 = r2     // Catch: java.lang.Exception -> L39
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)     // Catch: java.lang.Exception -> L39
            r0.L$1 = r9     // Catch: java.lang.Exception -> L39
            r0.Z$0 = r10     // Catch: java.lang.Exception -> L39
            r0.label = r4     // Catch: java.lang.Exception -> L39
            java.lang.Object r11 = r7.getKitsuEpisodesDetails(r8, r11, r0)     // Catch: java.lang.Exception -> L39
            if (r11 != r1) goto La8
        La7:
            return r1
        La8:
            java.util.Map r11 = (java.util.Map) r11     // Catch: java.lang.Exception -> L39
            if (r11 == 0) goto Lb9
            boolean r7 = r11.isEmpty()     // Catch: java.lang.Exception -> L39
            if (r7 == 0) goto Lb3
            goto Lb9
        Lb3:
            return r11
        Lb4:
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            com.lagradost.cloudstream3.mvvm.ArchComponentExtKt.logError(r7)
        Lb9:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.syncproviders.providers.Kitsu.getEpisodesDetails(java.lang.String, java.lang.String, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object getKitsuEpisodesDetails(String str, String str2, Continuation<? super Map<Integer, KitsuResponse.Node>> continuation) {
        C07821 c07821;
        KitsuResponse.LookupMapping lookupMapping;
        KitsuResponse.Episodes episodes;
        List<KitsuResponse.Node> nodes;
        Integer num;
        if (continuation instanceof C07821) {
            c07821 = (C07821) continuation;
            if ((c07821.label & Integer.MIN_VALUE) != 0) {
                c07821.label -= Integer.MIN_VALUE;
            } else {
                c07821 = new C07821(continuation);
            }
        } else {
            c07821 = new C07821(continuation);
        }
        Object kitsuData = c07821.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07821.label;
        if (i == 0) {
            ResultKt.throwOnFailure(kitsuData);
            if (StringsKt.isBlank(str)) {
                throw new IllegalArgumentException("Black id".toString());
            }
            if (StringsKt.isBlank(str2)) {
                throw new IllegalArgumentException("invalid site".toString());
            }
            Map<Pair<String, String>, Map<Integer, KitsuResponse.Node>> map = cache;
            if (map.containsKey(TuplesKt.to(str, str2))) {
                return map.get(TuplesKt.to(str, str2));
            }
            String str3 = "\nquery {\n  lookupMapping(externalId: " + str + ", externalSite: " + str2 + ") {\n    __typename\n    ... on Anime {\n      id\n      episodes(first: 2000) {\n        nodes {\n          number\n          titles {\n            canonical\n          }\n          description\n          thumbnail {\n            original {\n              url\n            }\n          }\n        }\n      }\n    }\n  }\n}";
            c07821.L$0 = str;
            c07821.L$1 = str2;
            c07821.L$2 = SpillingKt.nullOutSpilledVariable(str3);
            c07821.label = 1;
            kitsuData = getKitsuData(str3, c07821);
            if (kitsuData == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str2 = (String) c07821.L$1;
            str = (String) c07821.L$0;
            ResultKt.throwOnFailure(kitsuData);
        }
        KitsuResponse.Data data = ((KitsuResponse) kitsuData).getData();
        if (data == null || (lookupMapping = data.getLookupMapping()) == null || (episodes = lookupMapping.getEpisodes()) == null || (nodes = episodes.getNodes()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (KitsuResponse.Node node : nodes) {
            Pair pair = (node == null || (num = node.getNum()) == null) ? null : TuplesKt.to(Boxing.boxInt(num.intValue()), node);
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        Map<Integer, KitsuResponse.Node> map2 = MapsKt.toMap(arrayList);
        if (!map2.isEmpty()) {
            cache.put(TuplesKt.to(str, str2), map2);
        }
        return map2;
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0087\b\u0018\u0000 (2\u00020\u0001:\n\u001f !\"#$%&'(B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006)"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse;", "", "data", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Data;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Data;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Data;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Data;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "Data", "LookupMapping", "Episodes", "Node", "Description", "Thumbnail", "Original", "Titles", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class KitsuResponse {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("data")
        private final Data data;

        /* JADX WARN: Multi-variable type inference failed */
        public KitsuResponse() {
            this((Data) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ KitsuResponse copy$default(KitsuResponse kitsuResponse, Data data, int i, Object obj) {
            if ((i & 1) != 0) {
                data = kitsuResponse.data;
            }
            return kitsuResponse.copy(data);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Data getData() {
            return this.data;
        }

        public final KitsuResponse copy(@JsonProperty("data") Data data) {
            return new KitsuResponse(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof KitsuResponse) && Intrinsics.areEqual(this.data, ((KitsuResponse) other).data);
        }

        public int hashCode() {
            Data data = this.data;
            if (data == null) {
                return 0;
            }
            return data.hashCode();
        }

        public String toString() {
            return "KitsuResponse(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<KitsuResponse> serializer() {
                return Kitsu$KitsuResponse$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ KitsuResponse(int i, Data data, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.data = null;
            } else {
                this.data = data;
            }
        }

        public KitsuResponse(@JsonProperty("data") Data data) {
            this.data = data;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(KitsuResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.data == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 0, Kitsu$KitsuResponse$Data$$serializer.INSTANCE, self.data);
        }

        public /* synthetic */ KitsuResponse(Data data, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : data);
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Data;", "", "lookupMapping", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$LookupMapping;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$LookupMapping;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$LookupMapping;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getLookupMapping$annotations", "()V", "getLookupMapping", "()Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$LookupMapping;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @Serializable
        public static final /* data */ class Data {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);

            @JsonProperty("lookupMapping")
            private final LookupMapping lookupMapping;

            /* JADX WARN: Multi-variable type inference failed */
            public Data() {
                this((LookupMapping) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
            }

            public static /* synthetic */ Data copy$default(Data data, LookupMapping lookupMapping, int i, Object obj) {
                if ((i & 1) != 0) {
                    lookupMapping = data.lookupMapping;
                }
                return data.copy(lookupMapping);
            }

            @SerialName("lookupMapping")
            public static /* synthetic */ void getLookupMapping$annotations() {
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final LookupMapping getLookupMapping() {
                return this.lookupMapping;
            }

            public final Data copy(@JsonProperty("lookupMapping") LookupMapping lookupMapping) {
                return new Data(lookupMapping);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Data) && Intrinsics.areEqual(this.lookupMapping, ((Data) other).lookupMapping);
            }

            public int hashCode() {
                LookupMapping lookupMapping = this.lookupMapping;
                if (lookupMapping == null) {
                    return 0;
                }
                return lookupMapping.hashCode();
            }

            public String toString() {
                return "Data(lookupMapping=" + this.lookupMapping + ')';
            }

            /* JADX INFO: compiled from: KitsuApi.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Data$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Data;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<Data> serializer() {
                    return Kitsu$KitsuResponse$Data$$serializer.INSTANCE;
                }
            }

            public /* synthetic */ Data(int i, LookupMapping lookupMapping, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 1) == 0) {
                    this.lookupMapping = null;
                } else {
                    this.lookupMapping = lookupMapping;
                }
            }

            public Data(@JsonProperty("lookupMapping") LookupMapping lookupMapping) {
                this.lookupMapping = lookupMapping;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$app(Data self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.lookupMapping == null) {
                    return;
                }
                output.encodeNullableSerializableElement(serialDesc, 0, Kitsu$KitsuResponse$LookupMapping$$serializer.INSTANCE, self.lookupMapping);
            }

            public /* synthetic */ Data(LookupMapping lookupMapping, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : lookupMapping);
            }

            public final LookupMapping getLookupMapping() {
                return this.lookupMapping;
            }
        }

        public final Data getData() {
            return this.data;
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0002$%B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0016\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0001¢\u0006\u0002\b#R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006&"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$LookupMapping;", "", "id", "", "episodes", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Episodes;", "<init>", "(Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Episodes;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Episodes;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/String;", "getEpisodes$annotations", "getEpisodes", "()Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Episodes;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @Serializable
        public static final /* data */ class LookupMapping {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);

            @JsonProperty("episodes")
            private final Episodes episodes;

            @JsonProperty("id")
            private final String id;

            /* JADX WARN: Multi-variable type inference failed */
            public LookupMapping() {
                this((String) null, (Episodes) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
            }

            public static /* synthetic */ LookupMapping copy$default(LookupMapping lookupMapping, String str, Episodes episodes, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = lookupMapping.id;
                }
                if ((i & 2) != 0) {
                    episodes = lookupMapping.episodes;
                }
                return lookupMapping.copy(str, episodes);
            }

            @SerialName("episodes")
            public static /* synthetic */ void getEpisodes$annotations() {
            }

            @SerialName("id")
            public static /* synthetic */ void getId$annotations() {
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getId() {
                return this.id;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final Episodes getEpisodes() {
                return this.episodes;
            }

            public final LookupMapping copy(@JsonProperty("id") String id, @JsonProperty("episodes") Episodes episodes) {
                return new LookupMapping(id, episodes);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof LookupMapping)) {
                    return false;
                }
                LookupMapping lookupMapping = (LookupMapping) other;
                return Intrinsics.areEqual(this.id, lookupMapping.id) && Intrinsics.areEqual(this.episodes, lookupMapping.episodes);
            }

            public int hashCode() {
                String str = this.id;
                int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
                Episodes episodes = this.episodes;
                return iHashCode + (episodes != null ? episodes.hashCode() : 0);
            }

            public String toString() {
                return "LookupMapping(id=" + this.id + ", episodes=" + this.episodes + ')';
            }

            /* JADX INFO: compiled from: KitsuApi.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$LookupMapping$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$LookupMapping;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<LookupMapping> serializer() {
                    return Kitsu$KitsuResponse$LookupMapping$$serializer.INSTANCE;
                }
            }

            public /* synthetic */ LookupMapping(int i, String str, Episodes episodes, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 1) == 0) {
                    this.id = null;
                } else {
                    this.id = str;
                }
                if ((i & 2) == 0) {
                    this.episodes = null;
                } else {
                    this.episodes = episodes;
                }
            }

            public LookupMapping(@JsonProperty("id") String str, @JsonProperty("episodes") Episodes episodes) {
                this.id = str;
                this.episodes = episodes;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$app(LookupMapping self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (output.shouldEncodeElementDefault(serialDesc, 0) || self.id != null) {
                    output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.id);
                }
                if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.episodes == null) {
                    return;
                }
                output.encodeNullableSerializableElement(serialDesc, 1, Kitsu$KitsuResponse$Episodes$$serializer.INSTANCE, self.episodes);
            }

            public /* synthetic */ LookupMapping(String str, Episodes episodes, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : episodes);
            }

            public final String getId() {
                return this.id;
            }

            public final Episodes getEpisodes() {
                return this.episodes;
            }
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u001b\u0012\u0012\b\u0003\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B-\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0010\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u0013\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\u0012\b\u0003\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR&\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Episodes;", "", "nodes", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Node;", "<init>", "(Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNodes$annotations", "()V", "getNodes", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @Serializable
        public static final /* data */ class Episodes {

            @JsonProperty("nodes")
            private final List<Node> nodes;

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.Kitsu$KitsuResponse$Episodes$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Kitsu.KitsuResponse.Episodes._childSerializers$_anonymous_();
                }
            })};

            /* JADX WARN: Multi-variable type inference failed */
            public Episodes() {
                this((List) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
                return new ArrayListSerializer(BuiltinSerializersKt.getNullable(Kitsu$KitsuResponse$Node$$serializer.INSTANCE));
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Episodes copy$default(Episodes episodes, List list, int i, Object obj) {
                if ((i & 1) != 0) {
                    list = episodes.nodes;
                }
                return episodes.copy(list);
            }

            @SerialName("nodes")
            public static /* synthetic */ void getNodes$annotations() {
            }

            public final List<Node> component1() {
                return this.nodes;
            }

            public final Episodes copy(@JsonProperty("nodes") List<Node> nodes) {
                return new Episodes(nodes);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Episodes) && Intrinsics.areEqual(this.nodes, ((Episodes) other).nodes);
            }

            public int hashCode() {
                List<Node> list = this.nodes;
                if (list == null) {
                    return 0;
                }
                return list.hashCode();
            }

            public String toString() {
                return "Episodes(nodes=" + this.nodes + ')';
            }

            /* JADX INFO: compiled from: KitsuApi.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Episodes$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Episodes;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<Episodes> serializer() {
                    return Kitsu$KitsuResponse$Episodes$$serializer.INSTANCE;
                }
            }

            public /* synthetic */ Episodes(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 1) == 0) {
                    this.nodes = null;
                } else {
                    this.nodes = list;
                }
            }

            public Episodes(@JsonProperty("nodes") List<Node> list) {
                this.nodes = list;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$app(Episodes self, CompositeEncoder output, SerialDescriptor serialDesc) {
                Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
                if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.nodes == null) {
                    return;
                }
                output.encodeNullableSerializableElement(serialDesc, 0, lazyArr[0].getValue(), self.nodes);
            }

            public /* synthetic */ Episodes(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : list);
            }

            public final List<Node> getNodes() {
                return this.nodes;
            }
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 32\u00020\u0001:\u000223B7\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bBC\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\tHÆ\u0003J>\u0010\"\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010#J\u0014\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010'\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010(\u001a\u00020)HÖ\u0081\u0004J%\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0001¢\u0006\u0002\b1R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0014\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0011\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u001aR\u001e\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001c\u0010\u001d¨\u00064"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Node;", "", "num", "", "titles", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Titles;", "description", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Description;", "thumbnail", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Thumbnail;", "<init>", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Titles;Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Description;Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Thumbnail;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Titles;Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Description;Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Thumbnail;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNum$annotations", "()V", "getNum", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitles$annotations", "getTitles", "()Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Titles;", "getDescription$annotations", "getDescription", "()Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Description;", "getThumbnail$annotations", "getThumbnail", "()Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Thumbnail;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Titles;Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Description;Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Thumbnail;)Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Node;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @Serializable
        public static final /* data */ class Node {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);

            @JsonProperty("description")
            private final Description description;

            @JsonProperty("number")
            private final Integer num;

            @JsonProperty("thumbnail")
            private final Thumbnail thumbnail;

            @JsonProperty("titles")
            private final Titles titles;

            public Node() {
                this((Integer) null, (Titles) null, (Description) null, (Thumbnail) null, 15, (DefaultConstructorMarker) null);
            }

            public static /* synthetic */ Node copy$default(Node node, Integer num, Titles titles, Description description, Thumbnail thumbnail, int i, Object obj) {
                if ((i & 1) != 0) {
                    num = node.num;
                }
                if ((i & 2) != 0) {
                    titles = node.titles;
                }
                if ((i & 4) != 0) {
                    description = node.description;
                }
                if ((i & 8) != 0) {
                    thumbnail = node.thumbnail;
                }
                return node.copy(num, titles, description, thumbnail);
            }

            @SerialName("description")
            public static /* synthetic */ void getDescription$annotations() {
            }

            @SerialName("number")
            public static /* synthetic */ void getNum$annotations() {
            }

            @SerialName("thumbnail")
            public static /* synthetic */ void getThumbnail$annotations() {
            }

            @SerialName("titles")
            public static /* synthetic */ void getTitles$annotations() {
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Integer getNum() {
                return this.num;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final Titles getTitles() {
                return this.titles;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final Description getDescription() {
                return this.description;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final Thumbnail getThumbnail() {
                return this.thumbnail;
            }

            public final Node copy(@JsonProperty("number") Integer num, @JsonProperty("titles") Titles titles, @JsonProperty("description") Description description, @JsonProperty("thumbnail") Thumbnail thumbnail) {
                return new Node(num, titles, description, thumbnail);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Node)) {
                    return false;
                }
                Node node = (Node) other;
                return Intrinsics.areEqual(this.num, node.num) && Intrinsics.areEqual(this.titles, node.titles) && Intrinsics.areEqual(this.description, node.description) && Intrinsics.areEqual(this.thumbnail, node.thumbnail);
            }

            public int hashCode() {
                Integer num = this.num;
                int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
                Titles titles = this.titles;
                int iHashCode2 = (iHashCode + (titles == null ? 0 : titles.hashCode())) * 31;
                Description description = this.description;
                int iHashCode3 = (iHashCode2 + (description == null ? 0 : description.hashCode())) * 31;
                Thumbnail thumbnail = this.thumbnail;
                return iHashCode3 + (thumbnail != null ? thumbnail.hashCode() : 0);
            }

            public String toString() {
                return "Node(num=" + this.num + ", titles=" + this.titles + ", description=" + this.description + ", thumbnail=" + this.thumbnail + ')';
            }

            /* JADX INFO: compiled from: KitsuApi.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Node$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Node;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<Node> serializer() {
                    return Kitsu$KitsuResponse$Node$$serializer.INSTANCE;
                }
            }

            public /* synthetic */ Node(int i, Integer num, Titles titles, Description description, Thumbnail thumbnail, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 1) == 0) {
                    this.num = null;
                } else {
                    this.num = num;
                }
                if ((i & 2) == 0) {
                    this.titles = null;
                } else {
                    this.titles = titles;
                }
                if ((i & 4) == 0) {
                    this.description = null;
                } else {
                    this.description = description;
                }
                if ((i & 8) == 0) {
                    this.thumbnail = null;
                } else {
                    this.thumbnail = thumbnail;
                }
            }

            public Node(@JsonProperty("number") Integer num, @JsonProperty("titles") Titles titles, @JsonProperty("description") Description description, @JsonProperty("thumbnail") Thumbnail thumbnail) {
                this.num = num;
                this.titles = titles;
                this.description = description;
                this.thumbnail = thumbnail;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$app(Node self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (output.shouldEncodeElementDefault(serialDesc, 0) || self.num != null) {
                    output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.num);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || self.titles != null) {
                    output.encodeNullableSerializableElement(serialDesc, 1, Kitsu$KitsuResponse$Titles$$serializer.INSTANCE, self.titles);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 2) || self.description != null) {
                    output.encodeNullableSerializableElement(serialDesc, 2, Kitsu$KitsuResponse$Description$$serializer.INSTANCE, self.description);
                }
                if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.thumbnail == null) {
                    return;
                }
                output.encodeNullableSerializableElement(serialDesc, 3, Kitsu$KitsuResponse$Thumbnail$$serializer.INSTANCE, self.thumbnail);
            }

            public /* synthetic */ Node(Integer num, Titles titles, Description description, Thumbnail thumbnail, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : titles, (i & 4) != 0 ? null : description, (i & 8) != 0 ? null : thumbnail);
            }

            public final Integer getNum() {
                return this.num;
            }

            public final Titles getTitles() {
                return this.titles;
            }

            public final Description getDescription() {
                return this.description;
            }

            public final Thumbnail getThumbnail() {
                return this.thumbnail;
            }
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Description;", "", "en", "", "<init>", "(Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getEn$annotations", "()V", "getEn", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @Serializable
        public static final /* data */ class Description {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);

            @JsonProperty("en")
            private final String en;

            /* JADX WARN: Multi-variable type inference failed */
            public Description() {
                this((String) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
            }

            public static /* synthetic */ Description copy$default(Description description, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = description.en;
                }
                return description.copy(str);
            }

            @SerialName("en")
            public static /* synthetic */ void getEn$annotations() {
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getEn() {
                return this.en;
            }

            public final Description copy(@JsonProperty("en") String en) {
                return new Description(en);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Description) && Intrinsics.areEqual(this.en, ((Description) other).en);
            }

            public int hashCode() {
                String str = this.en;
                if (str == null) {
                    return 0;
                }
                return str.hashCode();
            }

            public String toString() {
                return "Description(en=" + this.en + ')';
            }

            /* JADX INFO: compiled from: KitsuApi.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Description$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Description;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<Description> serializer() {
                    return Kitsu$KitsuResponse$Description$$serializer.INSTANCE;
                }
            }

            public /* synthetic */ Description(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 1) == 0) {
                    this.en = null;
                } else {
                    this.en = str;
                }
            }

            public Description(@JsonProperty("en") String str) {
                this.en = str;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$app(Description self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.en == null) {
                    return;
                }
                output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.en);
            }

            public /* synthetic */ Description(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str);
            }

            public final String getEn() {
                return this.en;
            }
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Thumbnail;", "", "original", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Original;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Original;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Original;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getOriginal$annotations", "()V", "getOriginal", "()Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Original;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @Serializable
        public static final /* data */ class Thumbnail {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);

            @JsonProperty("original")
            private final Original original;

            /* JADX WARN: Multi-variable type inference failed */
            public Thumbnail() {
                this((Original) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
            }

            public static /* synthetic */ Thumbnail copy$default(Thumbnail thumbnail, Original original, int i, Object obj) {
                if ((i & 1) != 0) {
                    original = thumbnail.original;
                }
                return thumbnail.copy(original);
            }

            @SerialName("original")
            public static /* synthetic */ void getOriginal$annotations() {
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Original getOriginal() {
                return this.original;
            }

            public final Thumbnail copy(@JsonProperty("original") Original original) {
                return new Thumbnail(original);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Thumbnail) && Intrinsics.areEqual(this.original, ((Thumbnail) other).original);
            }

            public int hashCode() {
                Original original = this.original;
                if (original == null) {
                    return 0;
                }
                return original.hashCode();
            }

            public String toString() {
                return "Thumbnail(original=" + this.original + ')';
            }

            /* JADX INFO: compiled from: KitsuApi.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Thumbnail$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Thumbnail;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<Thumbnail> serializer() {
                    return Kitsu$KitsuResponse$Thumbnail$$serializer.INSTANCE;
                }
            }

            public /* synthetic */ Thumbnail(int i, Original original, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 1) == 0) {
                    this.original = null;
                } else {
                    this.original = original;
                }
            }

            public Thumbnail(@JsonProperty("original") Original original) {
                this.original = original;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$app(Thumbnail self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.original == null) {
                    return;
                }
                output.encodeNullableSerializableElement(serialDesc, 0, Kitsu$KitsuResponse$Original$$serializer.INSTANCE, self.original);
            }

            public /* synthetic */ Thumbnail(Original original, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : original);
            }

            public final Original getOriginal() {
                return this.original;
            }
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Original;", "", ImagesContract.URL, "", "<init>", "(Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getUrl$annotations", "()V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @Serializable
        public static final /* data */ class Original {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);

            @JsonProperty(ImagesContract.URL)
            private final String url;

            /* JADX WARN: Multi-variable type inference failed */
            public Original() {
                this((String) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
            }

            public static /* synthetic */ Original copy$default(Original original, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = original.url;
                }
                return original.copy(str);
            }

            @SerialName(ImagesContract.URL)
            public static /* synthetic */ void getUrl$annotations() {
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getUrl() {
                return this.url;
            }

            public final Original copy(@JsonProperty(ImagesContract.URL) String url) {
                return new Original(url);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Original) && Intrinsics.areEqual(this.url, ((Original) other).url);
            }

            public int hashCode() {
                String str = this.url;
                if (str == null) {
                    return 0;
                }
                return str.hashCode();
            }

            public String toString() {
                return "Original(url=" + this.url + ')';
            }

            /* JADX INFO: compiled from: KitsuApi.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Original$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Original;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<Original> serializer() {
                    return Kitsu$KitsuResponse$Original$$serializer.INSTANCE;
                }
            }

            public /* synthetic */ Original(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 1) == 0) {
                    this.url = null;
                } else {
                    this.url = str;
                }
            }

            public Original(@JsonProperty(ImagesContract.URL) String str) {
                this.url = str;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$app(Original self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.url == null) {
                    return;
                }
                output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.url);
            }

            public /* synthetic */ Original(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str);
            }

            public final String getUrl() {
                return this.url;
            }
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Titles;", "", "canonical", "", "<init>", "(Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getCanonical$annotations", "()V", "getCanonical", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @Serializable
        public static final /* data */ class Titles {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);

            @JsonProperty("canonical")
            private final String canonical;

            /* JADX WARN: Multi-variable type inference failed */
            public Titles() {
                this((String) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
            }

            public static /* synthetic */ Titles copy$default(Titles titles, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = titles.canonical;
                }
                return titles.copy(str);
            }

            @SerialName("canonical")
            public static /* synthetic */ void getCanonical$annotations() {
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getCanonical() {
                return this.canonical;
            }

            public final Titles copy(@JsonProperty("canonical") String canonical) {
                return new Titles(canonical);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Titles) && Intrinsics.areEqual(this.canonical, ((Titles) other).canonical);
            }

            public int hashCode() {
                String str = this.canonical;
                if (str == null) {
                    return 0;
                }
                return str.hashCode();
            }

            public String toString() {
                return "Titles(canonical=" + this.canonical + ')';
            }

            /* JADX INFO: compiled from: KitsuApi.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Titles$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/Kitsu$KitsuResponse$Titles;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<Titles> serializer() {
                    return Kitsu$KitsuResponse$Titles$$serializer.INSTANCE;
                }
            }

            public /* synthetic */ Titles(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 1) == 0) {
                    this.canonical = null;
                } else {
                    this.canonical = str;
                }
            }

            public Titles(@JsonProperty("canonical") String str) {
                this.canonical = str;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$app(Titles self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.canonical == null) {
                    return;
                }
                output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.canonical);
            }

            public /* synthetic */ Titles(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str);
            }

            public final String getCanonical() {
                return this.canonical;
            }
        }
    }
}
