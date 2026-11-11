package com.lagradost.cloudstream3.syncproviders.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.cast.MediaTrack;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities;
import com.lagradost.cloudstream3.syncproviders.AuthData;
import com.lagradost.cloudstream3.syncproviders.SubtitleAPI;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import io.ktor.http.ContentDisposition;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
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
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.schabi.newpipe.extractor.services.soundcloud.linkHandler.SoundcloudSearchQueryHandlerFactory;

/* JADX INFO: compiled from: SubSource.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u001b2\u00020\u0001:\b\u001b\u001c\u001d\u001e\u001f !\"B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0096@¢\u0006\u0002\u0010\u0015J$\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0019\u001a\u00020\u0010H\u0096@¢\u0006\u0002\u0010\u001aR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006#"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi;", "Lcom/lagradost/cloudstream3/syncproviders/SubtitleAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "idPrefix", "getIdPrefix", "requiresLogin", "", "getRequiresLogin", "()Z", "search", "", "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;", "auth", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", SearchIntents.EXTRA_QUERY, "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getResources", "", "Lcom/lagradost/cloudstream3/subtitles/SubtitleResource;", MediaTrack.ROLE_SUBTITLE, "(Lcom/lagradost/cloudstream3/subtitles/SubtitleResource;Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "SearchRoot", "Users", "Results", "ItemRoot", "Subtitles", "DownloadRoot", "Subtitle", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SubSourceApi extends SubtitleAPI {
    public static final String APIURL = "https://api.subsource.net/v1";
    private final boolean requiresLogin;
    private final String name = "SubSource";
    private final String idPrefix = "subsource";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi$getResources$1, reason: invalid class name */
    /* JADX INFO: compiled from: SubSource.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi", f = "SubSource.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {94, 98}, m = "getResources", n = {"$this$getResources", "auth", MediaTrack.ROLE_SUBTITLE, "$this$getResources", "auth", MediaTrack.ROLE_SUBTITLE, "data"}, nl = {95, 103}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubSourceApi.this.getResources(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubSource.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi", f = "SubSource.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {34, 48}, m = "search", n = {"auth", SearchIntents.EXTRA_QUERY, "queryLang", "type", "auth", SearchIntents.EXTRA_QUERY, "queryLang", "type", "searchResponse", "firstResult"}, nl = {44, 52}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"}, v = 2)
    static final class C08201 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C08201(Continuation<? super C08201> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubSourceApi.this.search(null, null, this);
        }
    }

    static final String getResources$lambda$0(String name, File file) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(file, "<unused var>");
        return name;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getIdPrefix() {
        return this.idPrefix;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public boolean getRequiresLogin() {
        return this.requiresLogin;
    }

    /* JADX WARN: Code duplicated, block: B:103:0x02a1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:52:0x020c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:53:0x020d  */
    /* JADX WARN: Code duplicated, block: B:56:0x0224  */
    /* JADX WARN: Code duplicated, block: B:67:0x0254  */
    /* JADX WARN: Code duplicated, block: B:68:0x0257  */
    /* JADX WARN: Code duplicated, block: B:71:0x0285  */
    /* JADX WARN: Code duplicated, block: B:73:0x029e  */
    /* JADX WARN: Code duplicated, block: B:79:0x02c2  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:85:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:94:0x0249 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:96:0x021e A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.lagradost.cloudstream3.syncproviders.SubtitleAPI
    public Object search(AuthData authData, AbstractSubtitleEntities.SubtitleSearch subtitleSearch, Continuation<? super List<AbstractSubtitleEntities.SubtitleEntity>> continuation) {
        C08201 c08201;
        Locale locale;
        boolean z;
        int i;
        Object obj;
        C08201 c08202;
        AuthData authData2;
        String str;
        TvType tvType;
        AbstractSubtitleEntities.SubtitleSearch subtitleSearch2;
        NiceResponse niceResponse;
        Object safe;
        Results results;
        AbstractSubtitleEntities.SubtitleSearch subtitleSearch3;
        String str2;
        TvType tvType2;
        Object safe2;
        ItemRoot itemRoot;
        ArrayList arrayList;
        int i2;
        ArrayList arrayList2;
        String str3;
        ArrayList arrayList3;
        boolean z2;
        boolean z3;
        ArrayList arrayList4;
        boolean z4;
        Integer hearingImpaired;
        boolean z5;
        Subtitles subtitles;
        if (continuation instanceof C08201) {
            c08201 = (C08201) continuation;
            if ((c08201.label & Integer.MIN_VALUE) != 0) {
                c08201.label -= Integer.MIN_VALUE;
            } else {
                c08201 = new C08201(continuation);
            }
        } else {
            c08201 = new C08201(continuation);
        }
        Object obj2 = c08201.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c08201.label;
        try {
            if (i3 != 0) {
                if (i3 == 1) {
                    TvType tvType3 = (TvType) c08201.L$3;
                    String str4 = (String) c08201.L$2;
                    AbstractSubtitleEntities.SubtitleSearch subtitleSearch4 = (AbstractSubtitleEntities.SubtitleSearch) c08201.L$1;
                    authData2 = (AuthData) c08201.L$0;
                    ResultKt.throwOnFailure(obj2);
                    i = 2;
                    z = false;
                    locale = null;
                    str = str4;
                    tvType = tvType3;
                    c08202 = c08201;
                    obj = coroutine_suspended;
                    subtitleSearch2 = subtitleSearch4;
                } else {
                    if (i3 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    TvType tvType4 = (TvType) c08201.L$3;
                    str2 = (String) c08201.L$2;
                    AbstractSubtitleEntities.SubtitleSearch subtitleSearch5 = (AbstractSubtitleEntities.SubtitleSearch) c08201.L$1;
                    ResultKt.throwOnFailure(obj2);
                    tvType2 = tvType4;
                    i = 2;
                    z = false;
                    locale = null;
                    subtitleSearch3 = subtitleSearch5;
                }
                NiceResponse niceResponse2 = (NiceResponse) obj2;
                try {
                    ResponseParser parser = niceResponse2.getParser();
                    Intrinsics.checkNotNull(parser);
                    safe2 = parser.parseSafe(niceResponse2.getText(), Reflection.getOrCreateKotlinClass(ItemRoot.class));
                } catch (Exception e) {
                    e.printStackTrace();
                    safe2 = locale;
                }
                itemRoot = (ItemRoot) safe2;
                if (itemRoot == null) {
                    return locale;
                }
                ArrayList<Subtitles> subtitles2 = itemRoot.getSubtitles();
                arrayList = new ArrayList();
                for (Object obj3 : subtitles2) {
                    subtitles = (Subtitles) obj3;
                    if (((Intrinsics.areEqual(subtitles.getReleaseType(), "trailer") && StringsKt.equals(subtitles.getLanguage(), str2, true)) ? 1 : z ? 1 : 0) != 0) {
                        arrayList.add(obj3);
                    }
                }
                i2 = 1;
                arrayList2 = arrayList;
                if (tvType2 == TvType.Movie) {
                    z2 = z ? 1 : 0;
                } else {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    Object[] objArr = new Object[1];
                    objArr[z ? 1 : 0] = subtitleSearch3.getEpNumber();
                    str3 = String.format(locale, "E%02d", Arrays.copyOf(objArr, 1));
                    Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
                    arrayList3 = new ArrayList();
                    for (Object obj4 : arrayList2) {
                        z3 = z;
                        if (StringsKt.contains$default(((Subtitles) obj4).getReleaseInfo(), str3, z3, i, (Object) null)) {
                            arrayList3.add(obj4);
                        }
                        z = z3 ? 1 : 0;
                    }
                    z2 = z;
                    arrayList2 = arrayList3;
                }
                ArrayList<Subtitles> arrayList5 = arrayList2;
                arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
                for (Subtitles subtitles3 : arrayList5) {
                    String idPrefix = getIdPrefix();
                    String releaseInfo = subtitles3.getReleaseInfo();
                    String language = subtitles3.getLanguage();
                    String link = subtitles3.getLink();
                    z4 = z2;
                    String name = getName();
                    Integer epNumber = subtitleSearch3.getEpNumber();
                    Integer seasonNumber = subtitleSearch3.getSeasonNumber();
                    hearingImpaired = subtitles3.getHearingImpaired();
                    if (hearingImpaired == null && hearingImpaired.intValue() == i2) {
                        z5 = i2;
                    } else {
                        z5 = z4 ? 1 : 0;
                    }
                    arrayList4.add(new AbstractSubtitleEntities.SubtitleEntity(idPrefix, releaseInfo, language, link, tvType2, name, epNumber, seasonNumber, null, z5, null, 1280, null));
                    z2 = z4 ? 1 : 0;
                    i2 = i2;
                }
                return arrayList4;
            }
            ResultKt.throwOnFailure(obj2);
            if (subtitleSearch.getImdbId() == null) {
                return null;
            }
            String strFromTagToEnglishLanguageName = SubtitleHelper.INSTANCE.fromTagToEnglishLanguageName(subtitleSearch.getLang());
            Integer seasonNumber2 = subtitleSearch.getSeasonNumber();
            TvType tvType5 = (seasonNumber2 != null ? seasonNumber2.intValue() : 0) > 0 ? TvType.TvSeries : TvType.Movie;
            Requests app2 = MainActivityKt.getApp();
            String imdbId = subtitleSearch.getImdbId();
            Intrinsics.checkNotNull(imdbId);
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("includeSeasons", Boxing.boxBoolean(false)), TuplesKt.to("limit", Boxing.boxInt(15)), TuplesKt.to(SearchIntents.EXTRA_QUERY, imdbId), TuplesKt.to("signal", "{}"));
            TimeUnit timeUnit = TimeUnit.MINUTES;
            c08201.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c08201.L$1 = subtitleSearch;
            c08201.L$2 = strFromTagToEnglishLanguageName;
            c08201.L$3 = tvType5;
            c08201.label = 1;
            TvType tvType6 = tvType5;
            locale = null;
            z = false;
            i = 2;
            C08201 c08203 = c08201;
            obj = coroutine_suspended;
            Object objPost$default = Requests.post$default(app2, "https://api.subsource.net/v1/movie/search", null, null, null, null, null, null, mapMapOf, null, false, 120, timeUnit, 0L, null, false, null, c08203, 62334, null);
            c08202 = c08203;
            if (objPost$default != obj) {
                authData2 = authData;
                str = strFromTagToEnglishLanguageName;
                obj2 = objPost$default;
                tvType = tvType6;
                subtitleSearch2 = subtitleSearch;
            }
            return obj;
            ResponseParser parser2 = niceResponse.getParser();
            Intrinsics.checkNotNull(parser2);
            safe = parser2.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(SearchRoot.class));
        } catch (Exception e2) {
            e2.printStackTrace();
            safe = locale;
        }
        niceResponse = (NiceResponse) obj2;
        SearchRoot searchRoot = (SearchRoot) safe;
        if (searchRoot == null || (results = (Results) CollectionsKt.firstOrNull((List) searchRoot.getResults())) == null) {
            return locale;
        }
        Requests app3 = MainActivityKt.getApp();
        String str5 = APIURL + StringsKt.replace$default(results.getLink(), "series", "subtitles", false, 4, (Object) null);
        AuthData authData3 = authData2;
        TimeUnit timeUnit2 = TimeUnit.MINUTES;
        c08202.L$0 = SpillingKt.nullOutSpilledVariable(authData3);
        c08202.L$1 = subtitleSearch2;
        c08202.L$2 = str;
        c08202.L$3 = tvType;
        c08202.L$4 = SpillingKt.nullOutSpilledVariable(searchRoot);
        c08202.L$5 = SpillingKt.nullOutSpilledVariable(results);
        c08202.label = i;
        TvType tvType7 = tvType;
        String str6 = str;
        subtitleSearch3 = subtitleSearch2;
        obj2 = Requests.get$default(app3, str5, null, null, null, null, false, 120, timeUnit2, 0L, null, false, null, c08202, 3902, null);
        if (obj2 != obj) {
            str2 = str6;
            tvType2 = tvType7;
            NiceResponse niceResponse3 = (NiceResponse) obj2;
            ResponseParser parser3 = niceResponse3.getParser();
            Intrinsics.checkNotNull(parser3);
            safe2 = parser3.parseSafe(niceResponse3.getText(), Reflection.getOrCreateKotlinClass(ItemRoot.class));
            itemRoot = (ItemRoot) safe2;
            if (itemRoot == null) {
                return locale;
            }
            ArrayList<Subtitles> subtitles4 = itemRoot.getSubtitles();
            arrayList = new ArrayList();
            while (r0.hasNext()) {
                subtitles = (Subtitles) obj3;
                if (((Intrinsics.areEqual(subtitles.getReleaseType(), "trailer") && StringsKt.equals(subtitles.getLanguage(), str2, true)) ? 1 : z ? 1 : 0) != 0) {
                    arrayList.add(obj3);
                }
            }
            i2 = 1;
            arrayList2 = arrayList;
            if (tvType2 == TvType.Movie) {
                z2 = z ? 1 : 0;
            } else {
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Object[] objArr2 = new Object[1];
                objArr2[z ? 1 : 0] = subtitleSearch3.getEpNumber();
                str3 = String.format(locale, "E%02d", Arrays.copyOf(objArr2, 1));
                Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
                arrayList3 = new ArrayList();
                while (r1.hasNext()) {
                    z3 = z;
                    if (StringsKt.contains$default(((Subtitles) obj4).getReleaseInfo(), str3, z3, i, (Object) null)) {
                        arrayList3.add(obj4);
                    }
                    z = z3 ? 1 : 0;
                }
                z2 = z;
                arrayList2 = arrayList3;
            }
            ArrayList<Subtitles> arrayList6 = arrayList2;
            arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList6, 10));
            while (r1.hasNext()) {
                String idPrefix2 = getIdPrefix();
                String releaseInfo2 = subtitles3.getReleaseInfo();
                String language2 = subtitles3.getLanguage();
                String link2 = subtitles3.getLink();
                z4 = z2;
                String name2 = getName();
                Integer epNumber2 = subtitleSearch3.getEpNumber();
                Integer seasonNumber3 = subtitleSearch3.getSeasonNumber();
                hearingImpaired = subtitles3.getHearingImpaired();
                if (hearingImpaired == null) {
                    z5 = z4 ? 1 : 0;
                } else {
                    z5 = i2;
                }
                arrayList4.add(new AbstractSubtitleEntities.SubtitleEntity(idPrefix2, releaseInfo2, language2, link2, tvType2, name2, epNumber2, seasonNumber3, null, z5, null, 1280, null));
                z2 = z4 ? 1 : 0;
                i2 = i2;
            }
            return arrayList4;
        }
        return obj;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0117, code lost:
    
        if (r6.addZipUrl(r4, r7, r2) == r1) goto L31;
     */
    @Override // com.lagradost.cloudstream3.syncproviders.SubtitleAPI
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getResources(com.lagradost.cloudstream3.subtitles.SubtitleResource r21, com.lagradost.cloudstream3.syncproviders.AuthData r22, com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities.SubtitleEntity r23, kotlin.coroutines.Continuation<? super kotlin.Unit> r24) {
        /*
            Method dump skipped, instruction units count: 285
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi.getResources(com.lagradost.cloudstream3.subtitles.SubtitleResource, com.lagradost.cloudstream3.syncproviders.AuthData, com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities$SubtitleEntity, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: compiled from: SubSource.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 32\u00020\u0001:\u000223BG\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0018\b\u0003\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007\u0012\u0018\b\u0003\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0005j\b\u0012\u0004\u0012\u00020\t`\u0007¢\u0006\u0004\b\n\u0010\u000bBE\b\u0010\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\n\u0010\u0010J\u0010\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0014J\u0019\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0003J\u0019\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0005j\b\u0012\u0004\u0012\u00020\t`\u0007HÆ\u0003JN\u0010#\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0018\b\u0003\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00072\u0018\b\u0003\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0005j\b\u0012\u0004\u0012\u00020\t`\u0007HÆ\u0001¢\u0006\u0002\u0010$J\u0014\u0010%\u001a\u00020\u00032\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010'\u001a\u00020\rHÖ\u0081\u0004J\n\u0010(\u001a\u00020)HÖ\u0081\u0004J%\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0001¢\u0006\u0002\b1R(\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u0017\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R4\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR4\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0005j\b\u0012\u0004\u0012\u00020\t`\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001d\u0010\u0012\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001c¨\u00064"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$SearchRoot;", "", "success", "", "results", "Ljava/util/ArrayList;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Results;", "Lkotlin/collections/ArrayList;", SoundcloudSearchQueryHandlerFactory.USERS, "Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Users;", "<init>", "(Ljava/lang/Boolean;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Boolean;Ljava/util/ArrayList;Ljava/util/ArrayList;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSuccess$annotations", "()V", "getSuccess", "()Ljava/lang/Boolean;", "setSuccess", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getResults$annotations", "getResults", "()Ljava/util/ArrayList;", "setResults", "(Ljava/util/ArrayList;)V", "getUsers$annotations", "getUsers", "setUsers", "component1", "component2", "component3", "copy", "(Ljava/lang/Boolean;Ljava/util/ArrayList;Ljava/util/ArrayList;)Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$SearchRoot;", "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SearchRoot {

        @JsonProperty("results")
        private ArrayList<Results> results;

        @JsonProperty("success")
        private Boolean success;

        @JsonProperty(SoundcloudSearchQueryHandlerFactory.USERS)
        private ArrayList<Users> users;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi$SearchRoot$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SubSourceApi.SearchRoot._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi$SearchRoot$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SubSourceApi.SearchRoot._childSerializers$_anonymous_$0();
            }
        })};

        public SearchRoot() {
            this((Boolean) null, (ArrayList) null, (ArrayList) null, 7, (DefaultConstructorMarker) null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(SubSourceApi$Results$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(SubSourceApi$Users$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SearchRoot copy$default(SearchRoot searchRoot, Boolean bool, ArrayList arrayList, ArrayList arrayList2, int i, Object obj) {
            if ((i & 1) != 0) {
                bool = searchRoot.success;
            }
            if ((i & 2) != 0) {
                arrayList = searchRoot.results;
            }
            if ((i & 4) != 0) {
                arrayList2 = searchRoot.users;
            }
            return searchRoot.copy(bool, arrayList, arrayList2);
        }

        @SerialName("results")
        public static /* synthetic */ void getResults$annotations() {
        }

        @SerialName("success")
        public static /* synthetic */ void getSuccess$annotations() {
        }

        @SerialName(SoundcloudSearchQueryHandlerFactory.USERS)
        public static /* synthetic */ void getUsers$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Boolean getSuccess() {
            return this.success;
        }

        public final ArrayList<Results> component2() {
            return this.results;
        }

        public final ArrayList<Users> component3() {
            return this.users;
        }

        public final SearchRoot copy(@JsonProperty("success") Boolean success, @JsonProperty("results") ArrayList<Results> results, @JsonProperty(SoundcloudSearchQueryHandlerFactory.USERS) ArrayList<Users> users) {
            Intrinsics.checkNotNullParameter(results, "results");
            Intrinsics.checkNotNullParameter(users, "users");
            return new SearchRoot(success, results, users);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SearchRoot)) {
                return false;
            }
            SearchRoot searchRoot = (SearchRoot) other;
            return Intrinsics.areEqual(this.success, searchRoot.success) && Intrinsics.areEqual(this.results, searchRoot.results) && Intrinsics.areEqual(this.users, searchRoot.users);
        }

        public int hashCode() {
            Boolean bool = this.success;
            return ((((bool == null ? 0 : bool.hashCode()) * 31) + this.results.hashCode()) * 31) + this.users.hashCode();
        }

        public String toString() {
            return "SearchRoot(success=" + this.success + ", results=" + this.results + ", users=" + this.users + ')';
        }

        /* JADX INFO: compiled from: SubSource.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$SearchRoot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$SearchRoot;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SearchRoot> serializer() {
                return SubSourceApi$SearchRoot$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SearchRoot(int i, Boolean bool, ArrayList arrayList, ArrayList arrayList2, SerializationConstructorMarker serializationConstructorMarker) {
            this.success = (i & 1) == 0 ? null : bool;
            if ((i & 2) == 0) {
                this.results = new ArrayList<>();
            } else {
                this.results = arrayList;
            }
            if ((i & 4) == 0) {
                this.users = new ArrayList<>();
            } else {
                this.users = arrayList2;
            }
        }

        public SearchRoot(@JsonProperty("success") Boolean bool, @JsonProperty("results") ArrayList<Results> results, @JsonProperty(SoundcloudSearchQueryHandlerFactory.USERS) ArrayList<Users> users) {
            Intrinsics.checkNotNullParameter(results, "results");
            Intrinsics.checkNotNullParameter(users, "users");
            this.success = bool;
            this.results = results;
            this.users = users;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(SearchRoot self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.success != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, BooleanSerializer.INSTANCE, self.success);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.results, new ArrayList())) {
                output.encodeSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.results);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 2) && Intrinsics.areEqual(self.users, new ArrayList())) {
                return;
            }
            output.encodeSerializableElement(serialDesc, 2, lazyArr[2].getValue(), self.users);
        }

        public final Boolean getSuccess() {
            return this.success;
        }

        public final void setSuccess(Boolean bool) {
            this.success = bool;
        }

        public /* synthetic */ SearchRoot(Boolean bool, ArrayList arrayList, ArrayList arrayList2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? new ArrayList() : arrayList, (i & 4) != 0 ? new ArrayList() : arrayList2);
        }

        public final ArrayList<Results> getResults() {
            return this.results;
        }

        public final void setResults(ArrayList<Results> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.results = arrayList;
        }

        public final ArrayList<Users> getUsers() {
            return this.users;
        }

        public final void setUsers(ArrayList<Users> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.users = arrayList;
        }
    }

    /* JADX INFO: compiled from: SubSource.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 82\u00020\u0001:\u000278BE\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0018\b\u0003\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00050\bj\b\u0012\u0004\u0012\u00020\u0005`\t¢\u0006\u0004\b\n\u0010\u000bBI\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\u0010\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0019\u0010'\u001a\u0012\u0012\u0004\u0012\u00020\u00050\bj\b\u0012\u0004\u0012\u00020\u0005`\tHÆ\u0003JL\u0010(\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0018\b\u0003\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00050\bj\b\u0012\u0004\u0012\u00020\u0005`\tHÆ\u0001¢\u0006\u0002\u0010)J\u0014\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010-\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010.\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00002\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0001¢\u0006\u0002\b6R(\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u0016\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R&\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR&\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001c\u0010\u0011\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR4\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00050\bj\b\u0012\u0004\u0012\u00020\u0005`\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001f\u0010\u0011\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u00069"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Users;", "", "id", "", "displayname", "", "avatar", "badges", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getDisplayname$annotations", "getDisplayname", "()Ljava/lang/String;", "setDisplayname", "(Ljava/lang/String;)V", "getAvatar$annotations", "getAvatar", "setAvatar", "getBadges$annotations", "getBadges", "()Ljava/util/ArrayList;", "setBadges", "(Ljava/util/ArrayList;)V", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;)Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Users;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Users {

        @JsonProperty("avatar")
        private String avatar;

        @JsonProperty("badges")
        private ArrayList<String> badges;

        @JsonProperty("displayname")
        private String displayname;

        @JsonProperty("id")
        private Integer id;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi$Users$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SubSourceApi.Users._childSerializers$_anonymous_();
            }
        })};

        public Users() {
            this((Integer) null, (String) null, (String) null, (ArrayList) null, 15, (DefaultConstructorMarker) null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Users copy$default(Users users, Integer num, String str, String str2, ArrayList arrayList, int i, Object obj) {
            if ((i & 1) != 0) {
                num = users.id;
            }
            if ((i & 2) != 0) {
                str = users.displayname;
            }
            if ((i & 4) != 0) {
                str2 = users.avatar;
            }
            if ((i & 8) != 0) {
                arrayList = users.badges;
            }
            return users.copy(num, str, str2, arrayList);
        }

        @SerialName("avatar")
        public static /* synthetic */ void getAvatar$annotations() {
        }

        @SerialName("badges")
        public static /* synthetic */ void getBadges$annotations() {
        }

        @SerialName("displayname")
        public static /* synthetic */ void getDisplayname$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDisplayname() {
            return this.displayname;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getAvatar() {
            return this.avatar;
        }

        public final ArrayList<String> component4() {
            return this.badges;
        }

        public final Users copy(@JsonProperty("id") Integer id, @JsonProperty("displayname") String displayname, @JsonProperty("avatar") String avatar, @JsonProperty("badges") ArrayList<String> badges) {
            Intrinsics.checkNotNullParameter(badges, "badges");
            return new Users(id, displayname, avatar, badges);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Users)) {
                return false;
            }
            Users users = (Users) other;
            return Intrinsics.areEqual(this.id, users.id) && Intrinsics.areEqual(this.displayname, users.displayname) && Intrinsics.areEqual(this.avatar, users.avatar) && Intrinsics.areEqual(this.badges, users.badges);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.displayname;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.avatar;
            return ((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.badges.hashCode();
        }

        public String toString() {
            return "Users(id=" + this.id + ", displayname=" + this.displayname + ", avatar=" + this.avatar + ", badges=" + this.badges + ')';
        }

        /* JADX INFO: compiled from: SubSource.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Users$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Users;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Users> serializer() {
                return SubSourceApi$Users$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Users(int i, Integer num, String str, String str2, ArrayList arrayList, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.id = null;
            } else {
                this.id = num;
            }
            if ((i & 2) == 0) {
                this.displayname = null;
            } else {
                this.displayname = str;
            }
            if ((i & 4) == 0) {
                this.avatar = null;
            } else {
                this.avatar = str2;
            }
            if ((i & 8) == 0) {
                this.badges = new ArrayList<>();
            } else {
                this.badges = arrayList;
            }
        }

        public Users(@JsonProperty("id") Integer num, @JsonProperty("displayname") String str, @JsonProperty("avatar") String str2, @JsonProperty("badges") ArrayList<String> badges) {
            Intrinsics.checkNotNullParameter(badges, "badges");
            this.id = num;
            this.displayname = str;
            this.avatar = str2;
            this.badges = badges;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Users self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.id != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.displayname != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.displayname);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.avatar != null) {
                output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.avatar);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 3) && Intrinsics.areEqual(self.badges, new ArrayList())) {
                return;
            }
            output.encodeSerializableElement(serialDesc, 3, lazyArr[3].getValue(), self.badges);
        }

        public final Integer getId() {
            return this.id;
        }

        public final void setId(Integer num) {
            this.id = num;
        }

        public final String getDisplayname() {
            return this.displayname;
        }

        public final void setDisplayname(String str) {
            this.displayname = str;
        }

        public final String getAvatar() {
            return this.avatar;
        }

        public final void setAvatar(String str) {
            this.avatar = str;
        }

        public /* synthetic */ Users(Integer num, String str, String str2, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? new ArrayList() : arrayList);
        }

        public final ArrayList<String> getBadges() {
            return this.badges;
        }

        public final void setBadges(ArrayList<String> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.badges = arrayList;
        }
    }

    /* JADX INFO: compiled from: SubSource.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b;\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 _2\u00020\u0001:\u0002^_B¥\u0001\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0018\b\u0003\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000ej\b\u0012\u0004\u0012\u00020\u0005`\u000f\u0012\u0018\b\u0003\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000ej\b\u0012\u0004\u0012\u00020\u0005`\u000f\u0012\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0012\u0010\u0013B\u0095\u0001\b\u0010\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000e\u0012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000e\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u0012\u0010\u0017J\u0010\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u000b\u0010E\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010G\u001a\u00020\u0005HÆ\u0003J\u0010\u0010H\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u000b\u0010I\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010K\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u00105J\u0019\u0010L\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000ej\b\u0012\u0004\u0012\u00020\u0005`\u000fHÆ\u0003J\u0019\u0010M\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000ej\b\u0012\u0004\u0012\u00020\u0005`\u000fHÆ\u0003J\u0010\u0010N\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u00105J¬\u0001\u0010O\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0018\b\u0003\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000ej\b\u0012\u0004\u0012\u00020\u0005`\u000f2\u0018\b\u0003\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000ej\b\u0012\u0004\u0012\u00020\u0005`\u000f2\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010PJ\u0014\u0010Q\u001a\u00020R2\b\u0010S\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010T\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010U\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020\u00002\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\\H\u0001¢\u0006\u0002\b]R(\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001e\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR&\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001f\u0010\u0019\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R&\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b$\u0010\u0019\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#R$\u0010\u0007\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b'\u0010\u0019\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#R(\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001e\u0012\u0004\b*\u0010\u0019\u001a\u0004\b+\u0010\u001b\"\u0004\b,\u0010\u001dR&\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b-\u0010\u0019\u001a\u0004\b.\u0010!\"\u0004\b/\u0010#R&\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b0\u0010\u0019\u001a\u0004\b1\u0010!\"\u0004\b2\u0010#R(\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u00108\u0012\u0004\b3\u0010\u0019\u001a\u0004\b4\u00105\"\u0004\b6\u00107R4\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000ej\b\u0012\u0004\u0012\u00020\u0005`\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b9\u0010\u0019\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R4\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000ej\b\u0012\u0004\u0012\u00020\u0005`\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b>\u0010\u0019\u001a\u0004\b?\u0010;\"\u0004\b@\u0010=R(\u0010\u0011\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u00108\u0012\u0004\bA\u0010\u0019\u001a\u0004\bB\u00105\"\u0004\bC\u00107¨\u0006`"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Results;", "", "id", "", "title", "", "type", "link", "releaseYear", "poster", "subtitleCount", "rating", "", "cast", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "genres", "score", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/lang/Double;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/lang/Double;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getTitle$annotations", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "getType$annotations", "getType", "setType", "getLink$annotations", "getLink", "setLink", "getReleaseYear$annotations", "getReleaseYear", "setReleaseYear", "getPoster$annotations", "getPoster", "setPoster", "getSubtitleCount$annotations", "getSubtitleCount", "setSubtitleCount", "getRating$annotations", "getRating", "()Ljava/lang/Double;", "setRating", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getCast$annotations", "getCast", "()Ljava/util/ArrayList;", "setCast", "(Ljava/util/ArrayList;)V", "getGenres$annotations", "getGenres", "setGenres", "getScore$annotations", "getScore", "setScore", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/lang/Double;)Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Results;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Results {

        @JsonProperty("cast")
        private ArrayList<String> cast;

        @JsonProperty("genres")
        private ArrayList<String> genres;

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("link")
        private String link;

        @JsonProperty("poster")
        private String poster;

        @JsonProperty("rating")
        private Double rating;

        @JsonProperty("releaseYear")
        private Integer releaseYear;

        @JsonProperty("score")
        private Double score;

        @JsonProperty("subtitleCount")
        private String subtitleCount;

        @JsonProperty("title")
        private String title;

        @JsonProperty("type")
        private String type;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi$Results$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SubSourceApi.Results._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi$Results$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SubSourceApi.Results._childSerializers$_anonymous_$0();
            }
        }), null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Results copy$default(Results results, Integer num, String str, String str2, String str3, Integer num2, String str4, String str5, Double d, ArrayList arrayList, ArrayList arrayList2, Double d2, int i, Object obj) {
            if ((i & 1) != 0) {
                num = results.id;
            }
            if ((i & 2) != 0) {
                str = results.title;
            }
            if ((i & 4) != 0) {
                str2 = results.type;
            }
            if ((i & 8) != 0) {
                str3 = results.link;
            }
            if ((i & 16) != 0) {
                num2 = results.releaseYear;
            }
            if ((i & 32) != 0) {
                str4 = results.poster;
            }
            if ((i & 64) != 0) {
                str5 = results.subtitleCount;
            }
            if ((i & 128) != 0) {
                d = results.rating;
            }
            if ((i & 256) != 0) {
                arrayList = results.cast;
            }
            if ((i & 512) != 0) {
                arrayList2 = results.genres;
            }
            if ((i & 1024) != 0) {
                d2 = results.score;
            }
            ArrayList arrayList3 = arrayList2;
            Double d3 = d2;
            Double d4 = d;
            ArrayList arrayList4 = arrayList;
            String str6 = str4;
            String str7 = str5;
            Integer num3 = num2;
            String str8 = str2;
            return results.copy(num, str, str8, str3, num3, str6, str7, d4, arrayList4, arrayList3, d3);
        }

        @SerialName("cast")
        public static /* synthetic */ void getCast$annotations() {
        }

        @SerialName("genres")
        public static /* synthetic */ void getGenres$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("link")
        public static /* synthetic */ void getLink$annotations() {
        }

        @SerialName("poster")
        public static /* synthetic */ void getPoster$annotations() {
        }

        @SerialName("rating")
        public static /* synthetic */ void getRating$annotations() {
        }

        @SerialName("releaseYear")
        public static /* synthetic */ void getReleaseYear$annotations() {
        }

        @SerialName("score")
        public static /* synthetic */ void getScore$annotations() {
        }

        @SerialName("subtitleCount")
        public static /* synthetic */ void getSubtitleCount$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        public final ArrayList<String> component10() {
            return this.genres;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Double getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getLink() {
            return this.link;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getReleaseYear() {
            return this.releaseYear;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getPoster() {
            return this.poster;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getSubtitleCount() {
            return this.subtitleCount;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Double getRating() {
            return this.rating;
        }

        public final ArrayList<String> component9() {
            return this.cast;
        }

        public final Results copy(@JsonProperty("id") Integer id, @JsonProperty("title") String title, @JsonProperty("type") String type, @JsonProperty("link") String link, @JsonProperty("releaseYear") Integer releaseYear, @JsonProperty("poster") String poster, @JsonProperty("subtitleCount") String subtitleCount, @JsonProperty("rating") Double rating, @JsonProperty("cast") ArrayList<String> cast, @JsonProperty("genres") ArrayList<String> genres, @JsonProperty("score") Double score) {
            Intrinsics.checkNotNullParameter(link, "link");
            Intrinsics.checkNotNullParameter(cast, "cast");
            Intrinsics.checkNotNullParameter(genres, "genres");
            return new Results(id, title, type, link, releaseYear, poster, subtitleCount, rating, cast, genres, score);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Results)) {
                return false;
            }
            Results results = (Results) other;
            return Intrinsics.areEqual(this.id, results.id) && Intrinsics.areEqual(this.title, results.title) && Intrinsics.areEqual(this.type, results.type) && Intrinsics.areEqual(this.link, results.link) && Intrinsics.areEqual(this.releaseYear, results.releaseYear) && Intrinsics.areEqual(this.poster, results.poster) && Intrinsics.areEqual(this.subtitleCount, results.subtitleCount) && Intrinsics.areEqual((Object) this.rating, (Object) results.rating) && Intrinsics.areEqual(this.cast, results.cast) && Intrinsics.areEqual(this.genres, results.genres) && Intrinsics.areEqual((Object) this.score, (Object) results.score);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.title;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.type;
            int iHashCode3 = (((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.link.hashCode()) * 31;
            Integer num2 = this.releaseYear;
            int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str3 = this.poster;
            int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.subtitleCount;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Double d = this.rating;
            int iHashCode7 = (((((iHashCode6 + (d == null ? 0 : d.hashCode())) * 31) + this.cast.hashCode()) * 31) + this.genres.hashCode()) * 31;
            Double d2 = this.score;
            return iHashCode7 + (d2 != null ? d2.hashCode() : 0);
        }

        public String toString() {
            return "Results(id=" + this.id + ", title=" + this.title + ", type=" + this.type + ", link=" + this.link + ", releaseYear=" + this.releaseYear + ", poster=" + this.poster + ", subtitleCount=" + this.subtitleCount + ", rating=" + this.rating + ", cast=" + this.cast + ", genres=" + this.genres + ", score=" + this.score + ')';
        }

        /* JADX INFO: compiled from: SubSource.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Results$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Results;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Results> serializer() {
                return SubSourceApi$Results$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Results(int i, Integer num, String str, String str2, String str3, Integer num2, String str4, String str5, Double d, ArrayList arrayList, ArrayList arrayList2, Double d2, SerializationConstructorMarker serializationConstructorMarker) {
            if (8 != (i & 8)) {
                PluginExceptionsKt.throwMissingFieldException(i, 8, SubSourceApi$Results$$serializer.INSTANCE.getDescriptor());
            }
            if ((i & 1) == 0) {
                this.id = null;
            } else {
                this.id = num;
            }
            if ((i & 2) == 0) {
                this.title = null;
            } else {
                this.title = str;
            }
            if ((i & 4) == 0) {
                this.type = null;
            } else {
                this.type = str2;
            }
            this.link = str3;
            if ((i & 16) == 0) {
                this.releaseYear = null;
            } else {
                this.releaseYear = num2;
            }
            if ((i & 32) == 0) {
                this.poster = null;
            } else {
                this.poster = str4;
            }
            if ((i & 64) == 0) {
                this.subtitleCount = null;
            } else {
                this.subtitleCount = str5;
            }
            if ((i & 128) == 0) {
                this.rating = null;
            } else {
                this.rating = d;
            }
            if ((i & 256) == 0) {
                this.cast = new ArrayList<>();
            } else {
                this.cast = arrayList;
            }
            if ((i & 512) == 0) {
                this.genres = new ArrayList<>();
            } else {
                this.genres = arrayList2;
            }
            if ((i & 1024) == 0) {
                this.score = null;
            } else {
                this.score = d2;
            }
        }

        public Results(@JsonProperty("id") Integer num, @JsonProperty("title") String str, @JsonProperty("type") String str2, @JsonProperty("link") String link, @JsonProperty("releaseYear") Integer num2, @JsonProperty("poster") String str3, @JsonProperty("subtitleCount") String str4, @JsonProperty("rating") Double d, @JsonProperty("cast") ArrayList<String> cast, @JsonProperty("genres") ArrayList<String> genres, @JsonProperty("score") Double d2) {
            Intrinsics.checkNotNullParameter(link, "link");
            Intrinsics.checkNotNullParameter(cast, "cast");
            Intrinsics.checkNotNullParameter(genres, "genres");
            this.id = num;
            this.title = str;
            this.type = str2;
            this.link = link;
            this.releaseYear = num2;
            this.poster = str3;
            this.subtitleCount = str4;
            this.rating = d;
            this.cast = cast;
            this.genres = genres;
            this.score = d2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Results self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.id != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.title != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.title);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.type != null) {
                output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.type);
            }
            output.encodeStringElement(serialDesc, 3, self.link);
            if (output.shouldEncodeElementDefault(serialDesc, 4) || self.releaseYear != null) {
                output.encodeNullableSerializableElement(serialDesc, 4, IntSerializer.INSTANCE, self.releaseYear);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || self.poster != null) {
                output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.poster);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 6) || self.subtitleCount != null) {
                output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.subtitleCount);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 7) || self.rating != null) {
                output.encodeNullableSerializableElement(serialDesc, 7, DoubleSerializer.INSTANCE, self.rating);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 8) || !Intrinsics.areEqual(self.cast, new ArrayList())) {
                output.encodeSerializableElement(serialDesc, 8, lazyArr[8].getValue(), self.cast);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 9) || !Intrinsics.areEqual(self.genres, new ArrayList())) {
                output.encodeSerializableElement(serialDesc, 9, lazyArr[9].getValue(), self.genres);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 10) && self.score == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 10, DoubleSerializer.INSTANCE, self.score);
        }

        public final Integer getId() {
            return this.id;
        }

        public final void setId(Integer num) {
            this.id = num;
        }

        public final String getTitle() {
            return this.title;
        }

        public final void setTitle(String str) {
            this.title = str;
        }

        public final String getType() {
            return this.type;
        }

        public final void setType(String str) {
            this.type = str;
        }

        public final String getLink() {
            return this.link;
        }

        public final void setLink(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.link = str;
        }

        public final Integer getReleaseYear() {
            return this.releaseYear;
        }

        public final void setReleaseYear(Integer num) {
            this.releaseYear = num;
        }

        public final String getPoster() {
            return this.poster;
        }

        public final void setPoster(String str) {
            this.poster = str;
        }

        public final String getSubtitleCount() {
            return this.subtitleCount;
        }

        public final void setSubtitleCount(String str) {
            this.subtitleCount = str;
        }

        public final Double getRating() {
            return this.rating;
        }

        public final void setRating(Double d) {
            this.rating = d;
        }

        public /* synthetic */ Results(Integer num, String str, String str2, String str3, Integer num2, String str4, String str5, Double d, ArrayList arrayList, ArrayList arrayList2, Double d2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, str3, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : d, (i & 256) != 0 ? new ArrayList() : arrayList, (i & 512) != 0 ? new ArrayList() : arrayList2, (i & 1024) != 0 ? null : d2);
        }

        public final ArrayList<String> getCast() {
            return this.cast;
        }

        public final void setCast(ArrayList<String> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.cast = arrayList;
        }

        public final ArrayList<String> getGenres() {
            return this.genres;
        }

        public final void setGenres(ArrayList<String> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.genres = arrayList;
        }

        public final Double getScore() {
            return this.score;
        }

        public final void setScore(Double d) {
            this.score = d;
        }
    }

    /* JADX INFO: compiled from: SubSource.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B!\u0012\u0018\b\u0001\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0004\b\u0006\u0010\u0007B+\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u0019\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J#\u0010\u0013\u001a\u00020\u00002\u0018\b\u0003\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!R4\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0007¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$ItemRoot;", "", "subtitles", "Ljava/util/ArrayList;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitles;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/util/ArrayList;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/ArrayList;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSubtitles$annotations", "()V", "getSubtitles", "()Ljava/util/ArrayList;", "setSubtitles", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ItemRoot {

        @JsonProperty("subtitles")
        private ArrayList<Subtitles> subtitles;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi$ItemRoot$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SubSourceApi.ItemRoot._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(SubSourceApi$Subtitles$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ItemRoot copy$default(ItemRoot itemRoot, ArrayList arrayList, int i, Object obj) {
            if ((i & 1) != 0) {
                arrayList = itemRoot.subtitles;
            }
            return itemRoot.copy(arrayList);
        }

        @SerialName("subtitles")
        public static /* synthetic */ void getSubtitles$annotations() {
        }

        public final ArrayList<Subtitles> component1() {
            return this.subtitles;
        }

        public final ItemRoot copy(@JsonProperty("subtitles") ArrayList<Subtitles> subtitles) {
            Intrinsics.checkNotNullParameter(subtitles, "subtitles");
            return new ItemRoot(subtitles);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ItemRoot) && Intrinsics.areEqual(this.subtitles, ((ItemRoot) other).subtitles);
        }

        public int hashCode() {
            return this.subtitles.hashCode();
        }

        public String toString() {
            return "ItemRoot(subtitles=" + this.subtitles + ')';
        }

        /* JADX INFO: compiled from: SubSource.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$ItemRoot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$ItemRoot;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ItemRoot> serializer() {
                return SubSourceApi$ItemRoot$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ItemRoot(int i, ArrayList arrayList, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, SubSourceApi$ItemRoot$$serializer.INSTANCE.getDescriptor());
            }
            this.subtitles = arrayList;
        }

        public ItemRoot(@JsonProperty("subtitles") ArrayList<Subtitles> subtitles) {
            Intrinsics.checkNotNullParameter(subtitles, "subtitles");
            this.subtitles = subtitles;
        }

        public final ArrayList<Subtitles> getSubtitles() {
            return this.subtitles;
        }

        public final void setSubtitles(ArrayList<Subtitles> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.subtitles = arrayList;
        }
    }

    /* JADX INFO: compiled from: SubSource.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\bK\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 m2\u00020\u0001:\u0002lmB·\u0001\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\u0018\b\u0003\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000fj\b\u0012\u0004\u0012\u00020\u0005`\u0010\u0012\b\b\u0001\u0010\u0011\u001a\u00020\u0005\u0012\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0015\u0010\u0016B\u00ad\u0001\b\u0010\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0004\b\u0015\u0010\u001aJ\u0010\u0010P\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\t\u0010Q\u001a\u00020\u0005HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010S\u001a\u00020\u0005HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010U\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u0010V\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010X\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u0010Y\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0019\u0010Z\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000fj\b\u0012\u0004\u0012\u00020\u0005`\u0010HÆ\u0003J\t\u0010[\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010]\u001a\u0004\u0018\u00010\u0014HÆ\u0003¢\u0006\u0002\u0010LJ¾\u0001\u0010^\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00052\u0018\b\u0003\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000fj\b\u0012\u0004\u0012\u00020\u0005`\u00102\b\b\u0003\u0010\u0011\u001a\u00020\u00052\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÆ\u0001¢\u0006\u0002\u0010_J\u0014\u0010`\u001a\u00020\u00142\b\u0010a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010b\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010c\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020\u00002\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020jH\u0001¢\u0006\u0002\bkR(\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010!\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R$\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\"\u0010\u001c\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R&\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b'\u0010\u001c\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&R$\u0010\u0007\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b*\u0010\u001c\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R&\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b-\u0010\u001c\u001a\u0004\b.\u0010$\"\u0004\b/\u0010&R(\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010!\u0012\u0004\b0\u0010\u001c\u001a\u0004\b1\u0010\u001e\"\u0004\b2\u0010 R&\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b3\u0010\u001c\u001a\u0004\b4\u0010$\"\u0004\b5\u0010&R&\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b6\u0010\u001c\u001a\u0004\b7\u0010$\"\u0004\b8\u0010&R(\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010!\u0012\u0004\b9\u0010\u001c\u001a\u0004\b:\u0010\u001e\"\u0004\b;\u0010 R&\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b<\u0010\u001c\u001a\u0004\b=\u0010$\"\u0004\b>\u0010&R4\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000fj\b\u0012\u0004\u0012\u00020\u0005`\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b?\u0010\u001c\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR$\u0010\u0011\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bD\u0010\u001c\u001a\u0004\bE\u0010$\"\u0004\bF\u0010&R&\u0010\u0012\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bG\u0010\u001c\u001a\u0004\bH\u0010$\"\u0004\bI\u0010&R(\u0010\u0013\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010O\u0012\u0004\bJ\u0010\u001c\u001a\u0004\bK\u0010L\"\u0004\bM\u0010N¨\u0006n"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitles;", "", "id", "", "language", "", "releaseType", "releaseInfo", "uploadDate", "hearingImpaired", MediaTrack.ROLE_CAPTION, "rating", "uploaderId", "uploaderDisplayname", "uploaderBadges", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "link", "productionType", "lastSubtitle", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getLanguage$annotations", "getLanguage", "()Ljava/lang/String;", "setLanguage", "(Ljava/lang/String;)V", "getReleaseType$annotations", "getReleaseType", "setReleaseType", "getReleaseInfo$annotations", "getReleaseInfo", "setReleaseInfo", "getUploadDate$annotations", "getUploadDate", "setUploadDate", "getHearingImpaired$annotations", "getHearingImpaired", "setHearingImpaired", "getCaption$annotations", "getCaption", "setCaption", "getRating$annotations", "getRating", "setRating", "getUploaderId$annotations", "getUploaderId", "setUploaderId", "getUploaderDisplayname$annotations", "getUploaderDisplayname", "setUploaderDisplayname", "getUploaderBadges$annotations", "getUploaderBadges", "()Ljava/util/ArrayList;", "setUploaderBadges", "(Ljava/util/ArrayList;)V", "getLink$annotations", "getLink", "setLink", "getProductionType$annotations", "getProductionType", "setProductionType", "getLastSubtitle$annotations", "getLastSubtitle", "()Ljava/lang/Boolean;", "setLastSubtitle", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitles;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Subtitles {

        @JsonProperty(MediaTrack.ROLE_CAPTION)
        private String caption;

        @JsonProperty("hearing_impaired")
        private Integer hearingImpaired;

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("language")
        private String language;

        @JsonProperty("last_subtitle")
        private Boolean lastSubtitle;

        @JsonProperty("link")
        private String link;

        @JsonProperty("production_type")
        private String productionType;

        @JsonProperty("rating")
        private String rating;

        @JsonProperty("release_info")
        private String releaseInfo;

        @JsonProperty("release_type")
        private String releaseType;

        @JsonProperty("upload_date")
        private String uploadDate;

        @JsonProperty("uploader_badges")
        private ArrayList<String> uploaderBadges;

        @JsonProperty("uploader_displayname")
        private String uploaderDisplayname;

        @JsonProperty("uploader_id")
        private Integer uploaderId;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi$Subtitles$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SubSourceApi.Subtitles._childSerializers$_anonymous_();
            }
        }), null, null, null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        @SerialName(MediaTrack.ROLE_CAPTION)
        public static /* synthetic */ void getCaption$annotations() {
        }

        @SerialName("hearing_impaired")
        public static /* synthetic */ void getHearingImpaired$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("language")
        public static /* synthetic */ void getLanguage$annotations() {
        }

        @SerialName("last_subtitle")
        public static /* synthetic */ void getLastSubtitle$annotations() {
        }

        @SerialName("link")
        public static /* synthetic */ void getLink$annotations() {
        }

        @SerialName("production_type")
        public static /* synthetic */ void getProductionType$annotations() {
        }

        @SerialName("rating")
        public static /* synthetic */ void getRating$annotations() {
        }

        @SerialName("release_info")
        public static /* synthetic */ void getReleaseInfo$annotations() {
        }

        @SerialName("release_type")
        public static /* synthetic */ void getReleaseType$annotations() {
        }

        @SerialName("upload_date")
        public static /* synthetic */ void getUploadDate$annotations() {
        }

        @SerialName("uploader_badges")
        public static /* synthetic */ void getUploaderBadges$annotations() {
        }

        @SerialName("uploader_displayname")
        public static /* synthetic */ void getUploaderDisplayname$annotations() {
        }

        @SerialName("uploader_id")
        public static /* synthetic */ void getUploaderId$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getUploaderDisplayname() {
            return this.uploaderDisplayname;
        }

        public final ArrayList<String> component11() {
            return this.uploaderBadges;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getLink() {
            return this.link;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getProductionType() {
            return this.productionType;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Boolean getLastSubtitle() {
            return this.lastSubtitle;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getReleaseType() {
            return this.releaseType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getReleaseInfo() {
            return this.releaseInfo;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getUploadDate() {
            return this.uploadDate;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getHearingImpaired() {
            return this.hearingImpaired;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getCaption() {
            return this.caption;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getRating() {
            return this.rating;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getUploaderId() {
            return this.uploaderId;
        }

        public final Subtitles copy(@JsonProperty("id") Integer id, @JsonProperty("language") String language, @JsonProperty("release_type") String releaseType, @JsonProperty("release_info") String releaseInfo, @JsonProperty("upload_date") String uploadDate, @JsonProperty("hearing_impaired") Integer hearingImpaired, @JsonProperty(MediaTrack.ROLE_CAPTION) String caption, @JsonProperty("rating") String rating, @JsonProperty("uploader_id") Integer uploaderId, @JsonProperty("uploader_displayname") String uploaderDisplayname, @JsonProperty("uploader_badges") ArrayList<String> uploaderBadges, @JsonProperty("link") String link, @JsonProperty("production_type") String productionType, @JsonProperty("last_subtitle") Boolean lastSubtitle) {
            Intrinsics.checkNotNullParameter(language, "language");
            Intrinsics.checkNotNullParameter(releaseInfo, "releaseInfo");
            Intrinsics.checkNotNullParameter(uploaderBadges, "uploaderBadges");
            Intrinsics.checkNotNullParameter(link, "link");
            return new Subtitles(id, language, releaseType, releaseInfo, uploadDate, hearingImpaired, caption, rating, uploaderId, uploaderDisplayname, uploaderBadges, link, productionType, lastSubtitle);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Subtitles)) {
                return false;
            }
            Subtitles subtitles = (Subtitles) other;
            return Intrinsics.areEqual(this.id, subtitles.id) && Intrinsics.areEqual(this.language, subtitles.language) && Intrinsics.areEqual(this.releaseType, subtitles.releaseType) && Intrinsics.areEqual(this.releaseInfo, subtitles.releaseInfo) && Intrinsics.areEqual(this.uploadDate, subtitles.uploadDate) && Intrinsics.areEqual(this.hearingImpaired, subtitles.hearingImpaired) && Intrinsics.areEqual(this.caption, subtitles.caption) && Intrinsics.areEqual(this.rating, subtitles.rating) && Intrinsics.areEqual(this.uploaderId, subtitles.uploaderId) && Intrinsics.areEqual(this.uploaderDisplayname, subtitles.uploaderDisplayname) && Intrinsics.areEqual(this.uploaderBadges, subtitles.uploaderBadges) && Intrinsics.areEqual(this.link, subtitles.link) && Intrinsics.areEqual(this.productionType, subtitles.productionType) && Intrinsics.areEqual(this.lastSubtitle, subtitles.lastSubtitle);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (((num == null ? 0 : num.hashCode()) * 31) + this.language.hashCode()) * 31;
            String str = this.releaseType;
            int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.releaseInfo.hashCode()) * 31;
            String str2 = this.uploadDate;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Integer num2 = this.hearingImpaired;
            int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str3 = this.caption;
            int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.rating;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Integer num3 = this.uploaderId;
            int iHashCode7 = (iHashCode6 + (num3 == null ? 0 : num3.hashCode())) * 31;
            String str5 = this.uploaderDisplayname;
            int iHashCode8 = (((((iHashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.uploaderBadges.hashCode()) * 31) + this.link.hashCode()) * 31;
            String str6 = this.productionType;
            int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
            Boolean bool = this.lastSubtitle;
            return iHashCode9 + (bool != null ? bool.hashCode() : 0);
        }

        public String toString() {
            return "Subtitles(id=" + this.id + ", language=" + this.language + ", releaseType=" + this.releaseType + ", releaseInfo=" + this.releaseInfo + ", uploadDate=" + this.uploadDate + ", hearingImpaired=" + this.hearingImpaired + ", caption=" + this.caption + ", rating=" + this.rating + ", uploaderId=" + this.uploaderId + ", uploaderDisplayname=" + this.uploaderDisplayname + ", uploaderBadges=" + this.uploaderBadges + ", link=" + this.link + ", productionType=" + this.productionType + ", lastSubtitle=" + this.lastSubtitle + ')';
        }

        /* JADX INFO: compiled from: SubSource.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitles$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitles;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Subtitles> serializer() {
                return SubSourceApi$Subtitles$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Subtitles(int i, Integer num, String str, String str2, String str3, String str4, Integer num2, String str5, String str6, Integer num3, String str7, ArrayList arrayList, String str8, String str9, Boolean bool, SerializationConstructorMarker serializationConstructorMarker) {
            if (2058 != (i & 2058)) {
                PluginExceptionsKt.throwMissingFieldException(i, 2058, SubSourceApi$Subtitles$$serializer.INSTANCE.getDescriptor());
            }
            if ((i & 1) == 0) {
                this.id = null;
            } else {
                this.id = num;
            }
            this.language = str;
            if ((i & 4) == 0) {
                this.releaseType = null;
            } else {
                this.releaseType = str2;
            }
            this.releaseInfo = str3;
            if ((i & 16) == 0) {
                this.uploadDate = null;
            } else {
                this.uploadDate = str4;
            }
            if ((i & 32) == 0) {
                this.hearingImpaired = null;
            } else {
                this.hearingImpaired = num2;
            }
            if ((i & 64) == 0) {
                this.caption = null;
            } else {
                this.caption = str5;
            }
            if ((i & 128) == 0) {
                this.rating = null;
            } else {
                this.rating = str6;
            }
            if ((i & 256) == 0) {
                this.uploaderId = null;
            } else {
                this.uploaderId = num3;
            }
            if ((i & 512) == 0) {
                this.uploaderDisplayname = null;
            } else {
                this.uploaderDisplayname = str7;
            }
            if ((i & 1024) == 0) {
                this.uploaderBadges = new ArrayList<>();
            } else {
                this.uploaderBadges = arrayList;
            }
            this.link = str8;
            if ((i & 4096) == 0) {
                this.productionType = null;
            } else {
                this.productionType = str9;
            }
            if ((i & 8192) == 0) {
                this.lastSubtitle = null;
            } else {
                this.lastSubtitle = bool;
            }
        }

        public Subtitles(@JsonProperty("id") Integer num, @JsonProperty("language") String language, @JsonProperty("release_type") String str, @JsonProperty("release_info") String releaseInfo, @JsonProperty("upload_date") String str2, @JsonProperty("hearing_impaired") Integer num2, @JsonProperty(MediaTrack.ROLE_CAPTION) String str3, @JsonProperty("rating") String str4, @JsonProperty("uploader_id") Integer num3, @JsonProperty("uploader_displayname") String str5, @JsonProperty("uploader_badges") ArrayList<String> uploaderBadges, @JsonProperty("link") String link, @JsonProperty("production_type") String str6, @JsonProperty("last_subtitle") Boolean bool) {
            Intrinsics.checkNotNullParameter(language, "language");
            Intrinsics.checkNotNullParameter(releaseInfo, "releaseInfo");
            Intrinsics.checkNotNullParameter(uploaderBadges, "uploaderBadges");
            Intrinsics.checkNotNullParameter(link, "link");
            this.id = num;
            this.language = language;
            this.releaseType = str;
            this.releaseInfo = releaseInfo;
            this.uploadDate = str2;
            this.hearingImpaired = num2;
            this.caption = str3;
            this.rating = str4;
            this.uploaderId = num3;
            this.uploaderDisplayname = str5;
            this.uploaderBadges = uploaderBadges;
            this.link = link;
            this.productionType = str6;
            this.lastSubtitle = bool;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Subtitles self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.id != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            }
            output.encodeStringElement(serialDesc, 1, self.language);
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.releaseType != null) {
                output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.releaseType);
            }
            output.encodeStringElement(serialDesc, 3, self.releaseInfo);
            if (output.shouldEncodeElementDefault(serialDesc, 4) || self.uploadDate != null) {
                output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.uploadDate);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || self.hearingImpaired != null) {
                output.encodeNullableSerializableElement(serialDesc, 5, IntSerializer.INSTANCE, self.hearingImpaired);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 6) || self.caption != null) {
                output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.caption);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 7) || self.rating != null) {
                output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.rating);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 8) || self.uploaderId != null) {
                output.encodeNullableSerializableElement(serialDesc, 8, IntSerializer.INSTANCE, self.uploaderId);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 9) || self.uploaderDisplayname != null) {
                output.encodeNullableSerializableElement(serialDesc, 9, StringSerializer.INSTANCE, self.uploaderDisplayname);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 10) || !Intrinsics.areEqual(self.uploaderBadges, new ArrayList())) {
                output.encodeSerializableElement(serialDesc, 10, lazyArr[10].getValue(), self.uploaderBadges);
            }
            output.encodeStringElement(serialDesc, 11, self.link);
            if (output.shouldEncodeElementDefault(serialDesc, 12) || self.productionType != null) {
                output.encodeNullableSerializableElement(serialDesc, 12, StringSerializer.INSTANCE, self.productionType);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 13) && self.lastSubtitle == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 13, BooleanSerializer.INSTANCE, self.lastSubtitle);
        }

        public final Integer getId() {
            return this.id;
        }

        public final void setId(Integer num) {
            this.id = num;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final void setLanguage(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.language = str;
        }

        public final String getReleaseType() {
            return this.releaseType;
        }

        public final void setReleaseType(String str) {
            this.releaseType = str;
        }

        public final String getReleaseInfo() {
            return this.releaseInfo;
        }

        public final void setReleaseInfo(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.releaseInfo = str;
        }

        public final String getUploadDate() {
            return this.uploadDate;
        }

        public final void setUploadDate(String str) {
            this.uploadDate = str;
        }

        public final Integer getHearingImpaired() {
            return this.hearingImpaired;
        }

        public final void setHearingImpaired(Integer num) {
            this.hearingImpaired = num;
        }

        public final String getCaption() {
            return this.caption;
        }

        public final void setCaption(String str) {
            this.caption = str;
        }

        public final String getRating() {
            return this.rating;
        }

        public final void setRating(String str) {
            this.rating = str;
        }

        public final Integer getUploaderId() {
            return this.uploaderId;
        }

        public final void setUploaderId(Integer num) {
            this.uploaderId = num;
        }

        public final String getUploaderDisplayname() {
            return this.uploaderDisplayname;
        }

        public final void setUploaderDisplayname(String str) {
            this.uploaderDisplayname = str;
        }

        public /* synthetic */ Subtitles(Integer num, String str, String str2, String str3, String str4, Integer num2, String str5, String str6, Integer num3, String str7, ArrayList arrayList, String str8, String str9, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, str, (i & 4) != 0 ? null : str2, str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : num2, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : num3, (i & 512) != 0 ? null : str7, (i & 1024) != 0 ? new ArrayList() : arrayList, str8, (i & 4096) != 0 ? null : str9, (i & 8192) != 0 ? null : bool);
        }

        public final ArrayList<String> getUploaderBadges() {
            return this.uploaderBadges;
        }

        public final void setUploaderBadges(ArrayList<String> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.uploaderBadges = arrayList;
        }

        public final String getLink() {
            return this.link;
        }

        public final void setLink(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.link = str;
        }

        public final String getProductionType() {
            return this.productionType;
        }

        public final void setProductionType(String str) {
            this.productionType = str;
        }

        public final Boolean getLastSubtitle() {
            return this.lastSubtitle;
        }

        public final void setLastSubtitle(Boolean bool) {
            this.lastSubtitle = bool;
        }
    }

    /* JADX INFO: compiled from: SubSource.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0011\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR$\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0005¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$DownloadRoot;", "", MediaTrack.ROLE_SUBTITLE, "Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitle;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitle;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitle;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSubtitle$annotations", "()V", "getSubtitle", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitle;", "setSubtitle", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class DownloadRoot {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty(MediaTrack.ROLE_SUBTITLE)
        private Subtitle subtitle;

        public static /* synthetic */ DownloadRoot copy$default(DownloadRoot downloadRoot, Subtitle subtitle, int i, Object obj) {
            if ((i & 1) != 0) {
                subtitle = downloadRoot.subtitle;
            }
            return downloadRoot.copy(subtitle);
        }

        @SerialName(MediaTrack.ROLE_SUBTITLE)
        public static /* synthetic */ void getSubtitle$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Subtitle getSubtitle() {
            return this.subtitle;
        }

        public final DownloadRoot copy(@JsonProperty(MediaTrack.ROLE_SUBTITLE) Subtitle subtitle) {
            Intrinsics.checkNotNullParameter(subtitle, "subtitle");
            return new DownloadRoot(subtitle);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DownloadRoot) && Intrinsics.areEqual(this.subtitle, ((DownloadRoot) other).subtitle);
        }

        public int hashCode() {
            return this.subtitle.hashCode();
        }

        public String toString() {
            return "DownloadRoot(subtitle=" + this.subtitle + ')';
        }

        /* JADX INFO: compiled from: SubSource.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$DownloadRoot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$DownloadRoot;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<DownloadRoot> serializer() {
                return SubSourceApi$DownloadRoot$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ DownloadRoot(int i, Subtitle subtitle, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, SubSourceApi$DownloadRoot$$serializer.INSTANCE.getDescriptor());
            }
            this.subtitle = subtitle;
        }

        public DownloadRoot(@JsonProperty(MediaTrack.ROLE_SUBTITLE) Subtitle subtitle) {
            Intrinsics.checkNotNullParameter(subtitle, "subtitle");
            this.subtitle = subtitle;
        }

        public final Subtitle getSubtitle() {
            return this.subtitle;
        }

        public final void setSubtitle(Subtitle subtitle) {
            Intrinsics.checkNotNullParameter(subtitle, "<set-?>");
            this.subtitle = subtitle;
        }
    }

    /* JADX INFO: compiled from: SubSource.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\bc\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u008b\u00012\u00020\u0001:\u0004\u008a\u0001\u008b\u0001B\u0083\u0002\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0018\b\u0003\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00050\nj\b\u0012\u0004\u0012\u00020\u0005`\u000b\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\b\b\u0001\u0010\u001a\u001a\u00020\u0005¢\u0006\u0004\b\u001b\u0010\u001cBé\u0001\b\u0010\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f¢\u0006\u0004\b\u001b\u0010 J\u0010\u0010h\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010$J\u000b\u0010i\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010l\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010$J\u0019\u0010m\u001a\u0012\u0012\u0004\u0012\u00020\u00050\nj\b\u0012\u0004\u0012\u00020\u0005`\u000bHÆ\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010q\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010$J\u0010\u0010r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010$J\u000b\u0010s\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010t\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010u\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010v\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010$J\u000b\u0010w\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010x\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010y\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010z\u001a\u0004\u0018\u00010\u0019HÆ\u0003¢\u0006\u0002\u0010aJ\t\u0010{\u001a\u00020\u0005HÆ\u0003J\u008a\u0002\u0010|\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\u0018\b\u0003\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00050\nj\b\u0012\u0004\u0012\u00020\u0005`\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0003\u0010\u001a\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010}J\u0014\u0010~\u001a\u00020\u00192\b\u0010\u007f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\u000b\u0010\u0080\u0001\u001a\u00020\u0003HÖ\u0081\u0004J\u000b\u0010\u0081\u0001\u001a\u00020\u0005HÖ\u0081\u0004J-\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0007\u0010\u0084\u0001\u001a\u00020\u00002\b\u0010\u0085\u0001\u001a\u00030\u0086\u00012\b\u0010\u0087\u0001\u001a\u00030\u0088\u0001H\u0001¢\u0006\u0003\b\u0089\u0001R(\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010'\u0012\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R&\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b(\u0010\"\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R&\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b-\u0010\"\u001a\u0004\b.\u0010*\"\u0004\b/\u0010,R&\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b0\u0010\"\u001a\u0004\b1\u0010*\"\u0004\b2\u0010,R(\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010'\u0012\u0004\b3\u0010\"\u001a\u0004\b4\u0010$\"\u0004\b5\u0010&R4\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00050\nj\b\u0012\u0004\u0012\u00020\u0005`\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b6\u0010\"\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R&\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b;\u0010\"\u001a\u0004\b<\u0010*\"\u0004\b=\u0010,R&\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b>\u0010\"\u001a\u0004\b?\u0010*\"\u0004\b@\u0010,R&\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bA\u0010\"\u001a\u0004\bB\u0010*\"\u0004\bC\u0010,R(\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010'\u0012\u0004\bD\u0010\"\u001a\u0004\bE\u0010$\"\u0004\bF\u0010&R(\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010'\u0012\u0004\bG\u0010\"\u001a\u0004\bH\u0010$\"\u0004\bI\u0010&R&\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bJ\u0010\"\u001a\u0004\bK\u0010*\"\u0004\bL\u0010,R&\u0010\u0012\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bM\u0010\"\u001a\u0004\bN\u0010*\"\u0004\bO\u0010,R&\u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bP\u0010\"\u001a\u0004\bQ\u0010*\"\u0004\bR\u0010,R(\u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010'\u0012\u0004\bS\u0010\"\u001a\u0004\bT\u0010$\"\u0004\bU\u0010&R&\u0010\u0015\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bV\u0010\"\u001a\u0004\bW\u0010*\"\u0004\bX\u0010,R&\u0010\u0016\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bY\u0010\"\u001a\u0004\bZ\u0010*\"\u0004\b[\u0010,R&\u0010\u0017\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\\\u0010\"\u001a\u0004\b]\u0010*\"\u0004\b^\u0010,R(\u0010\u0018\u001a\u0004\u0018\u00010\u00198\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010d\u0012\u0004\b_\u0010\"\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR$\u0010\u001a\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\be\u0010\"\u001a\u0004\bf\u0010*\"\u0004\bg\u0010,¨\u0006\u008c\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitle;", "", "id", "", "uploadedAt", "", "language", "rating", "uploadedBy", "releaseInfo", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", MediaTrack.ROLE_COMMENTARY, "files", ContentDisposition.Parameters.Size, "downloads", "comments", "productionType", "releaseType", "episode", "hearingImpaired", "foreignParts", "framerate", "preview", "userUploaded", "", "downloadToken", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getUploadedAt$annotations", "getUploadedAt", "()Ljava/lang/String;", "setUploadedAt", "(Ljava/lang/String;)V", "getLanguage$annotations", "getLanguage", "setLanguage", "getRating$annotations", "getRating", "setRating", "getUploadedBy$annotations", "getUploadedBy", "setUploadedBy", "getReleaseInfo$annotations", "getReleaseInfo", "()Ljava/util/ArrayList;", "setReleaseInfo", "(Ljava/util/ArrayList;)V", "getCommentary$annotations", "getCommentary", "setCommentary", "getFiles$annotations", "getFiles", "setFiles", "getSize$annotations", "getSize", "setSize", "getDownloads$annotations", "getDownloads", "setDownloads", "getComments$annotations", "getComments", "setComments", "getProductionType$annotations", "getProductionType", "setProductionType", "getReleaseType$annotations", "getReleaseType", "setReleaseType", "getEpisode$annotations", "getEpisode", "setEpisode", "getHearingImpaired$annotations", "getHearingImpaired", "setHearingImpaired", "getForeignParts$annotations", "getForeignParts", "setForeignParts", "getFramerate$annotations", "getFramerate", "setFramerate", "getPreview$annotations", "getPreview", "setPreview", "getUserUploaded$annotations", "getUserUploaded", "()Ljava/lang/Boolean;", "setUserUploaded", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getDownloadToken$annotations", "getDownloadToken", "setDownloadToken", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitle;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Subtitle {

        @JsonProperty(MediaTrack.ROLE_COMMENTARY)
        private String commentary;

        @JsonProperty("comments")
        private Integer comments;

        @JsonProperty("download_token")
        private String downloadToken;

        @JsonProperty("downloads")
        private Integer downloads;

        @JsonProperty("episode")
        private String episode;

        @JsonProperty("files")
        private String files;

        @JsonProperty("foreign_parts")
        private String foreignParts;

        @JsonProperty("framerate")
        private String framerate;

        @JsonProperty("hearing_impaired")
        private Integer hearingImpaired;

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("language")
        private String language;

        @JsonProperty("preview")
        private String preview;

        @JsonProperty("production_type")
        private String productionType;

        @JsonProperty("rating")
        private String rating;

        @JsonProperty("release_info")
        private ArrayList<String> releaseInfo;

        @JsonProperty("release_type")
        private String releaseType;

        @JsonProperty(ContentDisposition.Parameters.Size)
        private String size;

        @JsonProperty("uploaded_at")
        private String uploadedAt;

        @JsonProperty("uploaded_by")
        private Integer uploadedBy;

        @JsonProperty("user_uploaded")
        private Boolean userUploaded;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi$Subtitle$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SubSourceApi.Subtitle._childSerializers$_anonymous_();
            }
        }), null, null, null, null, null, null, null, null, null, null, null, null, null, null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Subtitle copy$default(Subtitle subtitle, Integer num, String str, String str2, String str3, Integer num2, ArrayList arrayList, String str4, String str5, String str6, Integer num3, Integer num4, String str7, String str8, String str9, Integer num5, String str10, String str11, String str12, Boolean bool, String str13, int i, Object obj) {
            String str14;
            Boolean bool2;
            Integer num6 = (i & 1) != 0 ? subtitle.id : num;
            String str15 = (i & 2) != 0 ? subtitle.uploadedAt : str;
            String str16 = (i & 4) != 0 ? subtitle.language : str2;
            String str17 = (i & 8) != 0 ? subtitle.rating : str3;
            Integer num7 = (i & 16) != 0 ? subtitle.uploadedBy : num2;
            ArrayList arrayList2 = (i & 32) != 0 ? subtitle.releaseInfo : arrayList;
            String str18 = (i & 64) != 0 ? subtitle.commentary : str4;
            String str19 = (i & 128) != 0 ? subtitle.files : str5;
            String str20 = (i & 256) != 0 ? subtitle.size : str6;
            Integer num8 = (i & 512) != 0 ? subtitle.downloads : num3;
            Integer num9 = (i & 1024) != 0 ? subtitle.comments : num4;
            String str21 = (i & 2048) != 0 ? subtitle.productionType : str7;
            String str22 = (i & 4096) != 0 ? subtitle.releaseType : str8;
            String str23 = (i & 8192) != 0 ? subtitle.episode : str9;
            Integer num10 = num6;
            Integer num11 = (i & 16384) != 0 ? subtitle.hearingImpaired : num5;
            String str24 = (i & 32768) != 0 ? subtitle.foreignParts : str10;
            String str25 = (i & 65536) != 0 ? subtitle.framerate : str11;
            String str26 = (i & 131072) != 0 ? subtitle.preview : str12;
            Boolean bool3 = (i & 262144) != 0 ? subtitle.userUploaded : bool;
            if ((i & 524288) != 0) {
                bool2 = bool3;
                str14 = subtitle.downloadToken;
            } else {
                str14 = str13;
                bool2 = bool3;
            }
            return subtitle.copy(num10, str15, str16, str17, num7, arrayList2, str18, str19, str20, num8, num9, str21, str22, str23, num11, str24, str25, str26, bool2, str14);
        }

        @SerialName(MediaTrack.ROLE_COMMENTARY)
        public static /* synthetic */ void getCommentary$annotations() {
        }

        @SerialName("comments")
        public static /* synthetic */ void getComments$annotations() {
        }

        @SerialName("download_token")
        public static /* synthetic */ void getDownloadToken$annotations() {
        }

        @SerialName("downloads")
        public static /* synthetic */ void getDownloads$annotations() {
        }

        @SerialName("episode")
        public static /* synthetic */ void getEpisode$annotations() {
        }

        @SerialName("files")
        public static /* synthetic */ void getFiles$annotations() {
        }

        @SerialName("foreign_parts")
        public static /* synthetic */ void getForeignParts$annotations() {
        }

        @SerialName("framerate")
        public static /* synthetic */ void getFramerate$annotations() {
        }

        @SerialName("hearing_impaired")
        public static /* synthetic */ void getHearingImpaired$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("language")
        public static /* synthetic */ void getLanguage$annotations() {
        }

        @SerialName("preview")
        public static /* synthetic */ void getPreview$annotations() {
        }

        @SerialName("production_type")
        public static /* synthetic */ void getProductionType$annotations() {
        }

        @SerialName("rating")
        public static /* synthetic */ void getRating$annotations() {
        }

        @SerialName("release_info")
        public static /* synthetic */ void getReleaseInfo$annotations() {
        }

        @SerialName("release_type")
        public static /* synthetic */ void getReleaseType$annotations() {
        }

        @SerialName(ContentDisposition.Parameters.Size)
        public static /* synthetic */ void getSize$annotations() {
        }

        @SerialName("uploaded_at")
        public static /* synthetic */ void getUploadedAt$annotations() {
        }

        @SerialName("uploaded_by")
        public static /* synthetic */ void getUploadedBy$annotations() {
        }

        @SerialName("user_uploaded")
        public static /* synthetic */ void getUserUploaded$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getDownloads() {
            return this.downloads;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Integer getComments() {
            return this.comments;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getProductionType() {
            return this.productionType;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getReleaseType() {
            return this.releaseType;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getEpisode() {
            return this.episode;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final Integer getHearingImpaired() {
            return this.hearingImpaired;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final String getForeignParts() {
            return this.foreignParts;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final String getFramerate() {
            return this.framerate;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final String getPreview() {
            return this.preview;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final Boolean getUserUploaded() {
            return this.userUploaded;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUploadedAt() {
            return this.uploadedAt;
        }

        /* JADX INFO: renamed from: component20, reason: from getter */
        public final String getDownloadToken() {
            return this.downloadToken;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getRating() {
            return this.rating;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getUploadedBy() {
            return this.uploadedBy;
        }

        public final ArrayList<String> component6() {
            return this.releaseInfo;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getCommentary() {
            return this.commentary;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getFiles() {
            return this.files;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getSize() {
            return this.size;
        }

        public final Subtitle copy(@JsonProperty("id") Integer id, @JsonProperty("uploaded_at") String uploadedAt, @JsonProperty("language") String language, @JsonProperty("rating") String rating, @JsonProperty("uploaded_by") Integer uploadedBy, @JsonProperty("release_info") ArrayList<String> releaseInfo, @JsonProperty(MediaTrack.ROLE_COMMENTARY) String commentary, @JsonProperty("files") String files, @JsonProperty(ContentDisposition.Parameters.Size) String size, @JsonProperty("downloads") Integer downloads, @JsonProperty("comments") Integer comments, @JsonProperty("production_type") String productionType, @JsonProperty("release_type") String releaseType, @JsonProperty("episode") String episode, @JsonProperty("hearing_impaired") Integer hearingImpaired, @JsonProperty("foreign_parts") String foreignParts, @JsonProperty("framerate") String framerate, @JsonProperty("preview") String preview, @JsonProperty("user_uploaded") Boolean userUploaded, @JsonProperty("download_token") String downloadToken) {
            Intrinsics.checkNotNullParameter(releaseInfo, "releaseInfo");
            Intrinsics.checkNotNullParameter(downloadToken, "downloadToken");
            return new Subtitle(id, uploadedAt, language, rating, uploadedBy, releaseInfo, commentary, files, size, downloads, comments, productionType, releaseType, episode, hearingImpaired, foreignParts, framerate, preview, userUploaded, downloadToken);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Subtitle)) {
                return false;
            }
            Subtitle subtitle = (Subtitle) other;
            return Intrinsics.areEqual(this.id, subtitle.id) && Intrinsics.areEqual(this.uploadedAt, subtitle.uploadedAt) && Intrinsics.areEqual(this.language, subtitle.language) && Intrinsics.areEqual(this.rating, subtitle.rating) && Intrinsics.areEqual(this.uploadedBy, subtitle.uploadedBy) && Intrinsics.areEqual(this.releaseInfo, subtitle.releaseInfo) && Intrinsics.areEqual(this.commentary, subtitle.commentary) && Intrinsics.areEqual(this.files, subtitle.files) && Intrinsics.areEqual(this.size, subtitle.size) && Intrinsics.areEqual(this.downloads, subtitle.downloads) && Intrinsics.areEqual(this.comments, subtitle.comments) && Intrinsics.areEqual(this.productionType, subtitle.productionType) && Intrinsics.areEqual(this.releaseType, subtitle.releaseType) && Intrinsics.areEqual(this.episode, subtitle.episode) && Intrinsics.areEqual(this.hearingImpaired, subtitle.hearingImpaired) && Intrinsics.areEqual(this.foreignParts, subtitle.foreignParts) && Intrinsics.areEqual(this.framerate, subtitle.framerate) && Intrinsics.areEqual(this.preview, subtitle.preview) && Intrinsics.areEqual(this.userUploaded, subtitle.userUploaded) && Intrinsics.areEqual(this.downloadToken, subtitle.downloadToken);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.uploadedAt;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.language;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.rating;
            int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Integer num2 = this.uploadedBy;
            int iHashCode5 = (((iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31) + this.releaseInfo.hashCode()) * 31;
            String str4 = this.commentary;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.files;
            int iHashCode7 = (iHashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.size;
            int iHashCode8 = (iHashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
            Integer num3 = this.downloads;
            int iHashCode9 = (iHashCode8 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Integer num4 = this.comments;
            int iHashCode10 = (iHashCode9 + (num4 == null ? 0 : num4.hashCode())) * 31;
            String str7 = this.productionType;
            int iHashCode11 = (iHashCode10 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.releaseType;
            int iHashCode12 = (iHashCode11 + (str8 == null ? 0 : str8.hashCode())) * 31;
            String str9 = this.episode;
            int iHashCode13 = (iHashCode12 + (str9 == null ? 0 : str9.hashCode())) * 31;
            Integer num5 = this.hearingImpaired;
            int iHashCode14 = (iHashCode13 + (num5 == null ? 0 : num5.hashCode())) * 31;
            String str10 = this.foreignParts;
            int iHashCode15 = (iHashCode14 + (str10 == null ? 0 : str10.hashCode())) * 31;
            String str11 = this.framerate;
            int iHashCode16 = (iHashCode15 + (str11 == null ? 0 : str11.hashCode())) * 31;
            String str12 = this.preview;
            int iHashCode17 = (iHashCode16 + (str12 == null ? 0 : str12.hashCode())) * 31;
            Boolean bool = this.userUploaded;
            return ((iHashCode17 + (bool != null ? bool.hashCode() : 0)) * 31) + this.downloadToken.hashCode();
        }

        public String toString() {
            return "Subtitle(id=" + this.id + ", uploadedAt=" + this.uploadedAt + ", language=" + this.language + ", rating=" + this.rating + ", uploadedBy=" + this.uploadedBy + ", releaseInfo=" + this.releaseInfo + ", commentary=" + this.commentary + ", files=" + this.files + ", size=" + this.size + ", downloads=" + this.downloads + ", comments=" + this.comments + ", productionType=" + this.productionType + ", releaseType=" + this.releaseType + ", episode=" + this.episode + ", hearingImpaired=" + this.hearingImpaired + ", foreignParts=" + this.foreignParts + ", framerate=" + this.framerate + ", preview=" + this.preview + ", userUploaded=" + this.userUploaded + ", downloadToken=" + this.downloadToken + ')';
        }

        /* JADX INFO: compiled from: SubSource.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitle$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitle;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Subtitle> serializer() {
                return SubSourceApi$Subtitle$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Subtitle(int i, Integer num, String str, String str2, String str3, Integer num2, ArrayList arrayList, String str4, String str5, String str6, Integer num3, Integer num4, String str7, String str8, String str9, Integer num5, String str10, String str11, String str12, Boolean bool, String str13, SerializationConstructorMarker serializationConstructorMarker) {
            if (524288 != (i & 524288)) {
                PluginExceptionsKt.throwMissingFieldException(i, 524288, SubSourceApi$Subtitle$$serializer.INSTANCE.getDescriptor());
            }
            if ((i & 1) == 0) {
                this.id = null;
            } else {
                this.id = num;
            }
            if ((i & 2) == 0) {
                this.uploadedAt = null;
            } else {
                this.uploadedAt = str;
            }
            if ((i & 4) == 0) {
                this.language = null;
            } else {
                this.language = str2;
            }
            if ((i & 8) == 0) {
                this.rating = null;
            } else {
                this.rating = str3;
            }
            if ((i & 16) == 0) {
                this.uploadedBy = null;
            } else {
                this.uploadedBy = num2;
            }
            if ((i & 32) == 0) {
                this.releaseInfo = new ArrayList<>();
            } else {
                this.releaseInfo = arrayList;
            }
            if ((i & 64) == 0) {
                this.commentary = null;
            } else {
                this.commentary = str4;
            }
            if ((i & 128) == 0) {
                this.files = null;
            } else {
                this.files = str5;
            }
            if ((i & 256) == 0) {
                this.size = null;
            } else {
                this.size = str6;
            }
            if ((i & 512) == 0) {
                this.downloads = null;
            } else {
                this.downloads = num3;
            }
            if ((i & 1024) == 0) {
                this.comments = null;
            } else {
                this.comments = num4;
            }
            if ((i & 2048) == 0) {
                this.productionType = null;
            } else {
                this.productionType = str7;
            }
            if ((i & 4096) == 0) {
                this.releaseType = null;
            } else {
                this.releaseType = str8;
            }
            if ((i & 8192) == 0) {
                this.episode = null;
            } else {
                this.episode = str9;
            }
            if ((i & 16384) == 0) {
                this.hearingImpaired = null;
            } else {
                this.hearingImpaired = num5;
            }
            if ((32768 & i) == 0) {
                this.foreignParts = null;
            } else {
                this.foreignParts = str10;
            }
            if ((65536 & i) == 0) {
                this.framerate = null;
            } else {
                this.framerate = str11;
            }
            if ((131072 & i) == 0) {
                this.preview = null;
            } else {
                this.preview = str12;
            }
            if ((i & 262144) == 0) {
                this.userUploaded = null;
            } else {
                this.userUploaded = bool;
            }
            this.downloadToken = str13;
        }

        public Subtitle(@JsonProperty("id") Integer num, @JsonProperty("uploaded_at") String str, @JsonProperty("language") String str2, @JsonProperty("rating") String str3, @JsonProperty("uploaded_by") Integer num2, @JsonProperty("release_info") ArrayList<String> releaseInfo, @JsonProperty(MediaTrack.ROLE_COMMENTARY) String str4, @JsonProperty("files") String str5, @JsonProperty(ContentDisposition.Parameters.Size) String str6, @JsonProperty("downloads") Integer num3, @JsonProperty("comments") Integer num4, @JsonProperty("production_type") String str7, @JsonProperty("release_type") String str8, @JsonProperty("episode") String str9, @JsonProperty("hearing_impaired") Integer num5, @JsonProperty("foreign_parts") String str10, @JsonProperty("framerate") String str11, @JsonProperty("preview") String str12, @JsonProperty("user_uploaded") Boolean bool, @JsonProperty("download_token") String downloadToken) {
            Intrinsics.checkNotNullParameter(releaseInfo, "releaseInfo");
            Intrinsics.checkNotNullParameter(downloadToken, "downloadToken");
            this.id = num;
            this.uploadedAt = str;
            this.language = str2;
            this.rating = str3;
            this.uploadedBy = num2;
            this.releaseInfo = releaseInfo;
            this.commentary = str4;
            this.files = str5;
            this.size = str6;
            this.downloads = num3;
            this.comments = num4;
            this.productionType = str7;
            this.releaseType = str8;
            this.episode = str9;
            this.hearingImpaired = num5;
            this.foreignParts = str10;
            this.framerate = str11;
            this.preview = str12;
            this.userUploaded = bool;
            this.downloadToken = downloadToken;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Subtitle self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.id != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.uploadedAt != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.uploadedAt);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.language != null) {
                output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.language);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || self.rating != null) {
                output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.rating);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || self.uploadedBy != null) {
                output.encodeNullableSerializableElement(serialDesc, 4, IntSerializer.INSTANCE, self.uploadedBy);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || !Intrinsics.areEqual(self.releaseInfo, new ArrayList())) {
                output.encodeSerializableElement(serialDesc, 5, lazyArr[5].getValue(), self.releaseInfo);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 6) || self.commentary != null) {
                output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.commentary);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 7) || self.files != null) {
                output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.files);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 8) || self.size != null) {
                output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.size);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 9) || self.downloads != null) {
                output.encodeNullableSerializableElement(serialDesc, 9, IntSerializer.INSTANCE, self.downloads);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 10) || self.comments != null) {
                output.encodeNullableSerializableElement(serialDesc, 10, IntSerializer.INSTANCE, self.comments);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 11) || self.productionType != null) {
                output.encodeNullableSerializableElement(serialDesc, 11, StringSerializer.INSTANCE, self.productionType);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 12) || self.releaseType != null) {
                output.encodeNullableSerializableElement(serialDesc, 12, StringSerializer.INSTANCE, self.releaseType);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 13) || self.episode != null) {
                output.encodeNullableSerializableElement(serialDesc, 13, StringSerializer.INSTANCE, self.episode);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 14) || self.hearingImpaired != null) {
                output.encodeNullableSerializableElement(serialDesc, 14, IntSerializer.INSTANCE, self.hearingImpaired);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 15) || self.foreignParts != null) {
                output.encodeNullableSerializableElement(serialDesc, 15, StringSerializer.INSTANCE, self.foreignParts);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 16) || self.framerate != null) {
                output.encodeNullableSerializableElement(serialDesc, 16, StringSerializer.INSTANCE, self.framerate);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 17) || self.preview != null) {
                output.encodeNullableSerializableElement(serialDesc, 17, StringSerializer.INSTANCE, self.preview);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 18) || self.userUploaded != null) {
                output.encodeNullableSerializableElement(serialDesc, 18, BooleanSerializer.INSTANCE, self.userUploaded);
            }
            output.encodeStringElement(serialDesc, 19, self.downloadToken);
        }

        public final Integer getId() {
            return this.id;
        }

        public final void setId(Integer num) {
            this.id = num;
        }

        public final String getUploadedAt() {
            return this.uploadedAt;
        }

        public final void setUploadedAt(String str) {
            this.uploadedAt = str;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final void setLanguage(String str) {
            this.language = str;
        }

        public final String getRating() {
            return this.rating;
        }

        public final void setRating(String str) {
            this.rating = str;
        }

        public final Integer getUploadedBy() {
            return this.uploadedBy;
        }

        public final void setUploadedBy(Integer num) {
            this.uploadedBy = num;
        }

        public /* synthetic */ Subtitle(Integer num, String str, String str2, String str3, Integer num2, ArrayList arrayList, String str4, String str5, String str6, Integer num3, Integer num4, String str7, String str8, String str9, Integer num5, String str10, String str11, String str12, Boolean bool, String str13, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? new ArrayList() : arrayList, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : str6, (i & 512) != 0 ? null : num3, (i & 1024) != 0 ? null : num4, (i & 2048) != 0 ? null : str7, (i & 4096) != 0 ? null : str8, (i & 8192) != 0 ? null : str9, (i & 16384) != 0 ? null : num5, (32768 & i) != 0 ? null : str10, (65536 & i) != 0 ? null : str11, (131072 & i) != 0 ? null : str12, (i & 262144) != 0 ? null : bool, str13);
        }

        public final ArrayList<String> getReleaseInfo() {
            return this.releaseInfo;
        }

        public final void setReleaseInfo(ArrayList<String> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.releaseInfo = arrayList;
        }

        public final String getCommentary() {
            return this.commentary;
        }

        public final void setCommentary(String str) {
            this.commentary = str;
        }

        public final String getFiles() {
            return this.files;
        }

        public final void setFiles(String str) {
            this.files = str;
        }

        public final String getSize() {
            return this.size;
        }

        public final void setSize(String str) {
            this.size = str;
        }

        public final Integer getDownloads() {
            return this.downloads;
        }

        public final void setDownloads(Integer num) {
            this.downloads = num;
        }

        public final Integer getComments() {
            return this.comments;
        }

        public final void setComments(Integer num) {
            this.comments = num;
        }

        public final String getProductionType() {
            return this.productionType;
        }

        public final void setProductionType(String str) {
            this.productionType = str;
        }

        public final String getReleaseType() {
            return this.releaseType;
        }

        public final void setReleaseType(String str) {
            this.releaseType = str;
        }

        public final String getEpisode() {
            return this.episode;
        }

        public final void setEpisode(String str) {
            this.episode = str;
        }

        public final Integer getHearingImpaired() {
            return this.hearingImpaired;
        }

        public final void setHearingImpaired(Integer num) {
            this.hearingImpaired = num;
        }

        public final String getForeignParts() {
            return this.foreignParts;
        }

        public final void setForeignParts(String str) {
            this.foreignParts = str;
        }

        public final String getFramerate() {
            return this.framerate;
        }

        public final void setFramerate(String str) {
            this.framerate = str;
        }

        public final String getPreview() {
            return this.preview;
        }

        public final void setPreview(String str) {
            this.preview = str;
        }

        public final Boolean getUserUploaded() {
            return this.userUploaded;
        }

        public final void setUserUploaded(Boolean bool) {
            this.userUploaded = bool;
        }

        public final String getDownloadToken() {
            return this.downloadToken;
        }

        public final void setDownloadToken(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.downloadToken = str;
        }
    }
}
