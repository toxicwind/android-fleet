package com.lagradost.cloudstream3.syncproviders.providers;

import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities;
import com.lagradost.cloudstream3.subtitles.SubtitleResource;
import com.lagradost.cloudstream3.syncproviders.AuthData;
import com.lagradost.cloudstream3.syncproviders.AuthLoginRequirement;
import com.lagradost.cloudstream3.syncproviders.AuthLoginResponse;
import com.lagradost.cloudstream3.syncproviders.AuthToken;
import com.lagradost.cloudstream3.syncproviders.AuthUser;
import com.lagradost.cloudstream3.syncproviders.SubtitleAPI;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import com.uwetrottmann.tmdb2.Tmdb;
import io.ktor.http.ContentType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
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
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: Subdl.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\n\u0018\u0000 22\u00020\u0001:\t23456789:B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0096@¢\u0006\u0002\u0010\u001eJ\u001a\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\u001bH\u0096@¢\u0006\u0002\u0010\"J(\u0010#\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010$2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020)H\u0096@¢\u0006\u0002\u0010*J$\u0010+\u001a\u00020,*\u00020-2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010.\u001a\u00020%H\u0096@¢\u0006\u0002\u0010/R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0014\u0010\u0018\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u001a\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000501X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi;", "Lcom/lagradost/cloudstream3/syncproviders/SubtitleAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "idPrefix", "getIdPrefix", "icon", "", "getIcon", "()Ljava/lang/Integer;", "hasInApp", "", "getHasInApp", "()Z", "inAppLoginRequirement", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginRequirement;", "getInAppLoginRequirement", "()Lcom/lagradost/cloudstream3/syncproviders/AuthLoginRequirement;", "requiresLogin", "getRequiresLogin", "createAccountUrl", "getCreateAccountUrl", "login", "Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "form", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "user", "Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "token", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "", "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;", "auth", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", SearchIntents.EXTRA_QUERY, "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getResources", "", "Lcom/lagradost/cloudstream3/subtitles/SubtitleResource;", MediaTrack.ROLE_SUBTITLE, "(Lcom/lagradost/cloudstream3/subtitles/SubtitleResource;Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "langTagIETF2subdl", "", "Companion", "SubtitleOAuthEntity", "OAuthTokenResponse", "UserData", "ApiKeyResponse", "Usage", "ApiResponse", "Result", "Subtitle", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SubDlApi extends SubtitleAPI {
    public static final String APIENDPOINT = "https://api.subdl.com/api/v1/subtitles";
    public static final String APIURL = "https://api.subdl.com";
    public static final String DOWNLOADENDPOINT = "https://dl.subdl.com";
    private final String name = "SubDL";
    private final String idPrefix = "subdl";
    private final int icon = R.drawable.subdl_logo_big;
    private final boolean hasInApp = true;
    private final AuthLoginRequirement inAppLoginRequirement = new AuthLoginRequirement(true, false, true, false, 10, null);
    private final boolean requiresLogin = true;
    private final String createAccountUrl = "https://subdl.com/panel/register";
    private final Map<String, String> langTagIETF2subdl = MapsKt.mapOf(TuplesKt.to("en-bg", "BG_EN"), TuplesKt.to("en-de", "EN_DE"), TuplesKt.to("en-hu", "HU_EN"), TuplesKt.to("en-nl", "NL_EN"), TuplesKt.to("pt-br", "BR_PT"), TuplesKt.to("zh-hant", "ZH_BG"));

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SubDlApi$login$1, reason: invalid class name */
    /* JADX INFO: compiled from: Subdl.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SubDlApi", f = "Subdl.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {37, 45}, m = "login", n = {"form", "email", HintConstants.AUTOFILL_HINT_PASSWORD, "form", "email", HintConstants.AUTOFILL_HINT_PASSWORD, "tokenResponse"}, nl = {43, 50}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"}, v = 2)
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
            return SubDlApi.this.login((AuthLoginResponse) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SubDlApi$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Subdl.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SubDlApi", f = "Subdl.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {88}, m = "search", n = {"auth", SearchIntents.EXTRA_QUERY, "apiKey", "queryText", "langSubdlCode", "idQuery", "epQuery", "seasonQuery", "yearQuery", "searchQueryUrl", "epNum", "seasonNum", "yearNum"}, nl = {95}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "I$0", "I$1", "I$2"}, v = 2)
    static final class C08191 extends ContinuationImpl {
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
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        C08191(Continuation<? super C08191> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubDlApi.this.search(null, null, this);
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
    public Integer getIcon() {
        return Integer.valueOf(this.icon);
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public boolean getHasInApp() {
        return this.hasInApp;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public AuthLoginRequirement getInAppLoginRequirement() {
        return this.inAppLoginRequirement;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public boolean getRequiresLogin() {
        return this.requiresLogin;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getCreateAccountUrl() {
        return this.createAccountUrl;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object login(AuthLoginResponse authLoginResponse, Continuation<? super AuthToken> continuation) {
        AnonymousClass1 anonymousClass1;
        String password;
        String str;
        int i;
        Object obj;
        Object objPost$default;
        AnonymousClass1 anonymousClass2;
        String str2;
        AuthLoginResponse authLoginResponse2;
        String str3;
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
        int i2 = anonymousClass1.label;
        if (i2 != 0) {
            if (i2 == 1) {
                String str4 = (String) anonymousClass1.L$2;
                String str5 = (String) anonymousClass1.L$1;
                authLoginResponse2 = (AuthLoginResponse) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj2);
                str = str4;
                anonymousClass2 = anonymousClass1;
                obj = coroutine_suspended;
                objPost$default = obj2;
                i = 2;
                str2 = str5;
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                String str6 = (String) anonymousClass1.L$1;
                ResultKt.throwOnFailure(obj2);
                str3 = str6;
            }
            NiceResponse niceResponse = (NiceResponse) obj2;
            ResponseParser parser = niceResponse.getParser();
            Intrinsics.checkNotNull(parser);
            return new AuthToken(((ApiKeyResponse) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(ApiKeyResponse.class))).getApiKey(), (String) null, (Long) null, (Long) null, str3, 14, (DefaultConstructorMarker) null);
        }
        ResultKt.throwOnFailure(obj2);
        String email = authLoginResponse.getEmail();
        if (email == null || (password = authLoginResponse.getPassword()) == null) {
            return null;
        }
        Requests app2 = MainActivityKt.getApp();
        Map mapMapOf = MapsKt.mapOf(TuplesKt.to("email", email), TuplesKt.to(HintConstants.AUTOFILL_HINT_PASSWORD, password));
        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(authLoginResponse);
        anonymousClass1.L$1 = email;
        anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(password);
        anonymousClass1.label = 1;
        str = password;
        i = 2;
        AnonymousClass1 anonymousClass3 = anonymousClass1;
        obj = coroutine_suspended;
        objPost$default = Requests.post$default(app2, "https://api.subdl.com/login", null, null, null, null, null, null, mapMapOf, null, false, 0, null, 0L, null, false, null, anonymousClass3, 65406, null);
        anonymousClass2 = anonymousClass3;
        if (objPost$default != obj) {
            str2 = email;
            authLoginResponse2 = authLoginResponse;
        }
        return obj;
        NiceResponse niceResponse2 = (NiceResponse) objPost$default;
        ResponseParser parser2 = niceResponse2.getParser();
        Intrinsics.checkNotNull(parser2);
        OAuthTokenResponse oAuthTokenResponse = (OAuthTokenResponse) parser2.parse(niceResponse2.getText(), Reflection.getOrCreateKotlinClass(OAuthTokenResponse.class));
        Requests app3 = MainActivityKt.getApp();
        Map mapMapOf2 = MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + oAuthTokenResponse.getToken()));
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(authLoginResponse2);
        anonymousClass2.L$1 = str2;
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(oAuthTokenResponse);
        anonymousClass2.label = i;
        String str7 = str2;
        Object obj3 = Requests.get$default(app3, "https://api.subdl.com/user/userApi", mapMapOf2, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4092, null);
        if (obj3 != obj) {
            str3 = str7;
            obj2 = obj3;
            NiceResponse niceResponse3 = (NiceResponse) obj2;
            ResponseParser parser3 = niceResponse3.getParser();
            Intrinsics.checkNotNull(parser3);
            return new AuthToken(((ApiKeyResponse) parser3.parse(niceResponse3.getText(), Reflection.getOrCreateKotlinClass(ApiKeyResponse.class))).getApiKey(), (String) null, (Long) null, (Long) null, str3, 14, (DefaultConstructorMarker) null);
        }
        return obj;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object user(AuthToken authToken, Continuation<? super AuthUser> continuation) {
        String payload;
        if (authToken == null || (payload = authToken.getPayload()) == null) {
            return null;
        }
        return new AuthUser(payload, payload.hashCode(), (String) null, (Map) null, 12, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.SubtitleAPI
    public Object search(AuthData authData, AbstractSubtitleEntities.SubtitleSearch subtitleSearch, Continuation<? super List<AbstractSubtitleEntities.SubtitleEntity>> continuation) {
        C08191 c08191;
        String accessToken;
        String str;
        AbstractSubtitleEntities.SubtitleSearch subtitleSearch2;
        Object safe;
        List<Subtitle> subtitles;
        Object next;
        String lang;
        if (continuation instanceof C08191) {
            c08191 = (C08191) continuation;
            if ((c08191.label & Integer.MIN_VALUE) != 0) {
                c08191.label -= Integer.MIN_VALUE;
            } else {
                c08191 = new C08191(continuation);
            }
        } else {
            c08191 = new C08191(continuation);
        }
        Object obj = c08191.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08191.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (authData == null || (accessToken = authData.getToken().getAccessToken()) == null) {
                return null;
            }
            String query = subtitleSearch.getQuery();
            Integer epNumber = subtitleSearch.getEpNumber();
            int iIntValue = epNumber != null ? epNumber.intValue() : 0;
            Integer seasonNumber = subtitleSearch.getSeasonNumber();
            int iIntValue2 = seasonNumber != null ? seasonNumber.intValue() : 0;
            Integer year = subtitleSearch.getYear();
            int iIntValue3 = year != null ? year.intValue() : 0;
            String lang2 = this.langTagIETF2subdl.get(String.valueOf(subtitleSearch.getLang()));
            if (lang2 == null) {
                lang2 = subtitleSearch.getLang();
            }
            if (subtitleSearch.getImdbId() != null) {
                str = "&imdb_id=" + subtitleSearch.getImdbId();
            } else if (subtitleSearch.getTmdbId() != null) {
                str = "&tmdb_id=" + subtitleSearch.getTmdbId();
            } else {
                str = null;
            }
            String str2 = iIntValue > 0 ? "&episode_number=" + iIntValue : "";
            String str3 = iIntValue2 <= 0 ? "" : "&season_number=" + iIntValue2;
            String str4 = iIntValue3 > 0 ? "&year=" + iIntValue3 : "";
            String str5 = str == null ? "https://api.subdl.com/api/v1/subtitles?api_key=" + accessToken + "&film_name=" + query + "&languages=" + lang2 + str2 + str3 + str4 : "https://api.subdl.com/api/v1/subtitles?api_key=" + accessToken + str + "&languages=" + lang2 + str2 + str3 + str4;
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Accept", "application/json"));
            c08191.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c08191.L$1 = subtitleSearch;
            c08191.L$2 = SpillingKt.nullOutSpilledVariable(accessToken);
            c08191.L$3 = SpillingKt.nullOutSpilledVariable(query);
            c08191.L$4 = SpillingKt.nullOutSpilledVariable(lang2);
            c08191.L$5 = SpillingKt.nullOutSpilledVariable(str);
            c08191.L$6 = SpillingKt.nullOutSpilledVariable(str2);
            c08191.L$7 = SpillingKt.nullOutSpilledVariable(str3);
            c08191.L$8 = SpillingKt.nullOutSpilledVariable(str4);
            c08191.L$9 = SpillingKt.nullOutSpilledVariable(str5);
            c08191.I$0 = iIntValue;
            c08191.I$1 = iIntValue2;
            c08191.I$2 = iIntValue3;
            c08191.label = 1;
            Object obj2 = Requests.get$default(app2, str5, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, c08191, 4092, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            subtitleSearch2 = subtitleSearch;
            obj = obj2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c08191.I$2;
            int i3 = c08191.I$1;
            int i4 = c08191.I$0;
            subtitleSearch2 = (AbstractSubtitleEntities.SubtitleSearch) c08191.L$1;
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        try {
            ResponseParser parser = niceResponse.getParser();
            Intrinsics.checkNotNull(parser);
            safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(ApiResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
            safe = null;
        }
        ApiResponse apiResponse = (ApiResponse) safe;
        if (apiResponse == null || (subtitles = apiResponse.getSubtitles()) == null) {
            return null;
        }
        List<Subtitle> list = subtitles;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Subtitle subtitle : list) {
            Iterator<T> it = this.langTagIETF2subdl.entrySet().iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((Map.Entry) next).getValue(), subtitle.getLang()));
            Map.Entry entry = (Map.Entry) next;
            if (entry == null || (lang = (String) entry.getKey()) == null) {
                lang = subtitle.getLang();
            }
            String str6 = lang;
            Integer episode = subtitle.getEpisode();
            if (episode == null) {
                episode = subtitleSearch2.getEpNumber();
            }
            Integer num = episode;
            Integer season = subtitle.getSeason();
            if (season == null) {
                season = subtitleSearch2.getSeasonNumber();
            }
            Integer num2 = season;
            TvType tvType = (num2 != null ? num2.intValue() : 0) > 0 ? TvType.TvSeries : TvType.Movie;
            String idPrefix = getIdPrefix();
            String releaseName = subtitle.getReleaseName();
            String str7 = DOWNLOADENDPOINT + subtitle.getUrl();
            String name = getName();
            Boolean hearingImpaired = subtitle.getHearingImpaired();
            arrayList.add(new AbstractSubtitleEntities.SubtitleEntity(idPrefix, releaseName, str6, str7, tvType, name, num, num2, null, hearingImpaired != null ? hearingImpaired.booleanValue() : false, null, 1280, null));
        }
        return arrayList;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SubtitleAPI
    public Object getResources(SubtitleResource subtitleResource, AuthData authData, AbstractSubtitleEntities.SubtitleEntity subtitleEntity, Continuation<? super Unit> continuation) throws IOException {
        Object objAddZipUrl = subtitleResource.addZipUrl(subtitleEntity.getData(), new Function2() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SubDlApi$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return SubDlApi.getResources$lambda$0((String) obj, (File) obj2);
            }
        }, continuation);
        return objAddZipUrl == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAddZipUrl : Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: Subdl.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 52\u00020\u0001:\u000245B?\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tBM\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003JA\u0010&\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010*\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010+\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00002\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0001¢\u0006\u0002\b3R$\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R&\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R&\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014R&\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001e\u0010\u0010\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014¨\u00066"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$SubtitleOAuthEntity;", "", "userEmail", "", "pass", "name", "accessToken", "apiKey", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getUserEmail$annotations", "()V", "getUserEmail", "()Ljava/lang/String;", "setUserEmail", "(Ljava/lang/String;)V", "getPass$annotations", "getPass", "setPass", "getName$annotations", "getName", "setName", "getAccessToken$annotations", "getAccessToken", "setAccessToken", "getApiKey$annotations", "getApiKey", "setApiKey", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SubtitleOAuthEntity {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("accessToken")
        private String accessToken;

        @JsonProperty("apiKey")
        private String apiKey;

        @JsonProperty("name")
        private String name;

        @JsonProperty("pass")
        private String pass;

        @JsonProperty("userEmail")
        private String userEmail;

        public static /* synthetic */ SubtitleOAuthEntity copy$default(SubtitleOAuthEntity subtitleOAuthEntity, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = subtitleOAuthEntity.userEmail;
            }
            if ((i & 2) != 0) {
                str2 = subtitleOAuthEntity.pass;
            }
            if ((i & 4) != 0) {
                str3 = subtitleOAuthEntity.name;
            }
            if ((i & 8) != 0) {
                str4 = subtitleOAuthEntity.accessToken;
            }
            if ((i & 16) != 0) {
                str5 = subtitleOAuthEntity.apiKey;
            }
            String str6 = str5;
            String str7 = str3;
            return subtitleOAuthEntity.copy(str, str2, str7, str4, str6);
        }

        @SerialName("accessToken")
        public static /* synthetic */ void getAccessToken$annotations() {
        }

        @SerialName("apiKey")
        public static /* synthetic */ void getApiKey$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("pass")
        public static /* synthetic */ void getPass$annotations() {
        }

        @SerialName("userEmail")
        public static /* synthetic */ void getUserEmail$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUserEmail() {
            return this.userEmail;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPass() {
            return this.pass;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getAccessToken() {
            return this.accessToken;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getApiKey() {
            return this.apiKey;
        }

        public final SubtitleOAuthEntity copy(@JsonProperty("userEmail") String userEmail, @JsonProperty("pass") String pass, @JsonProperty("name") String name, @JsonProperty("accessToken") String accessToken, @JsonProperty("apiKey") String apiKey) {
            Intrinsics.checkNotNullParameter(userEmail, "userEmail");
            Intrinsics.checkNotNullParameter(pass, "pass");
            return new SubtitleOAuthEntity(userEmail, pass, name, accessToken, apiKey);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SubtitleOAuthEntity)) {
                return false;
            }
            SubtitleOAuthEntity subtitleOAuthEntity = (SubtitleOAuthEntity) other;
            return Intrinsics.areEqual(this.userEmail, subtitleOAuthEntity.userEmail) && Intrinsics.areEqual(this.pass, subtitleOAuthEntity.pass) && Intrinsics.areEqual(this.name, subtitleOAuthEntity.name) && Intrinsics.areEqual(this.accessToken, subtitleOAuthEntity.accessToken) && Intrinsics.areEqual(this.apiKey, subtitleOAuthEntity.apiKey);
        }

        public int hashCode() {
            int iHashCode = ((this.userEmail.hashCode() * 31) + this.pass.hashCode()) * 31;
            String str = this.name;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.accessToken;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.apiKey;
            return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "SubtitleOAuthEntity(userEmail=" + this.userEmail + ", pass=" + this.pass + ", name=" + this.name + ", accessToken=" + this.accessToken + ", apiKey=" + this.apiKey + ')';
        }

        /* JADX INFO: compiled from: Subdl.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$SubtitleOAuthEntity$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$SubtitleOAuthEntity;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SubtitleOAuthEntity> serializer() {
                return SubDlApi$SubtitleOAuthEntity$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SubtitleOAuthEntity(int i, String str, String str2, String str3, String str4, String str5, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, SubDlApi$SubtitleOAuthEntity$$serializer.INSTANCE.getDescriptor());
            }
            this.userEmail = str;
            this.pass = str2;
            if ((i & 4) == 0) {
                this.name = null;
            } else {
                this.name = str3;
            }
            if ((i & 8) == 0) {
                this.accessToken = null;
            } else {
                this.accessToken = str4;
            }
            if ((i & 16) == 0) {
                this.apiKey = null;
            } else {
                this.apiKey = str5;
            }
        }

        public SubtitleOAuthEntity(@JsonProperty("userEmail") String userEmail, @JsonProperty("pass") String pass, @JsonProperty("name") String str, @JsonProperty("accessToken") String str2, @JsonProperty("apiKey") String str3) {
            Intrinsics.checkNotNullParameter(userEmail, "userEmail");
            Intrinsics.checkNotNullParameter(pass, "pass");
            this.userEmail = userEmail;
            this.pass = pass;
            this.name = str;
            this.accessToken = str2;
            this.apiKey = str3;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(SubtitleOAuthEntity self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.userEmail);
            output.encodeStringElement(serialDesc, 1, self.pass);
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.name != null) {
                output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.name);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || self.accessToken != null) {
                output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.accessToken);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 4) && self.apiKey == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.apiKey);
        }

        public /* synthetic */ SubtitleOAuthEntity(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5);
        }

        public final String getUserEmail() {
            return this.userEmail;
        }

        public final void setUserEmail(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.userEmail = str;
        }

        public final String getPass() {
            return this.pass;
        }

        public final void setPass(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.pass = str;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            this.name = str;
        }

        public final String getAccessToken() {
            return this.accessToken;
        }

        public final void setAccessToken(String str) {
            this.accessToken = str;
        }

        public final String getApiKey() {
            return this.apiKey;
        }

        public final void setApiKey(String str) {
            this.apiKey = str;
        }
    }

    /* JADX INFO: compiled from: Subdl.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 02\u00020\u0001:\u0002/0B5\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nBC\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\t\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0019J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J<\u0010!\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\"J\u0014\u0010#\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\fHÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0001¢\u0006\u0002\b.R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0016R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001a\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001c\u0010\u0013¨\u00061"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$OAuthTokenResponse;", "", "token", "", "userData", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$UserData;", NotificationCompat.CATEGORY_STATUS, "", ContentType.Message.TYPE, "<init>", "(Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$UserData;Ljava/lang/Boolean;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$UserData;Ljava/lang/Boolean;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getToken$annotations", "()V", "getToken", "()Ljava/lang/String;", "getUserData$annotations", "getUserData", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$UserData;", "getStatus$annotations", "getStatus", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getMessage$annotations", "getMessage", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$UserData;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$OAuthTokenResponse;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class OAuthTokenResponse {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty(ContentType.Message.TYPE)
        private final String message;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final Boolean status;

        @JsonProperty("token")
        private final String token;

        @JsonProperty("userData")
        private final UserData userData;

        public static /* synthetic */ OAuthTokenResponse copy$default(OAuthTokenResponse oAuthTokenResponse, String str, UserData userData, Boolean bool, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = oAuthTokenResponse.token;
            }
            if ((i & 2) != 0) {
                userData = oAuthTokenResponse.userData;
            }
            if ((i & 4) != 0) {
                bool = oAuthTokenResponse.status;
            }
            if ((i & 8) != 0) {
                str2 = oAuthTokenResponse.message;
            }
            return oAuthTokenResponse.copy(str, userData, bool, str2);
        }

        @SerialName(ContentType.Message.TYPE)
        public static /* synthetic */ void getMessage$annotations() {
        }

        @SerialName(NotificationCompat.CATEGORY_STATUS)
        public static /* synthetic */ void getStatus$annotations() {
        }

        @SerialName("token")
        public static /* synthetic */ void getToken$annotations() {
        }

        @SerialName("userData")
        public static /* synthetic */ void getUserData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getToken() {
            return this.token;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final UserData getUserData() {
            return this.userData;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Boolean getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final OAuthTokenResponse copy(@JsonProperty("token") String token, @JsonProperty("userData") UserData userData, @JsonProperty(NotificationCompat.CATEGORY_STATUS) Boolean status, @JsonProperty(ContentType.Message.TYPE) String message) {
            Intrinsics.checkNotNullParameter(token, "token");
            return new OAuthTokenResponse(token, userData, status, message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OAuthTokenResponse)) {
                return false;
            }
            OAuthTokenResponse oAuthTokenResponse = (OAuthTokenResponse) other;
            return Intrinsics.areEqual(this.token, oAuthTokenResponse.token) && Intrinsics.areEqual(this.userData, oAuthTokenResponse.userData) && Intrinsics.areEqual(this.status, oAuthTokenResponse.status) && Intrinsics.areEqual(this.message, oAuthTokenResponse.message);
        }

        public int hashCode() {
            int iHashCode = this.token.hashCode() * 31;
            UserData userData = this.userData;
            int iHashCode2 = (iHashCode + (userData == null ? 0 : userData.hashCode())) * 31;
            Boolean bool = this.status;
            int iHashCode3 = (iHashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
            String str = this.message;
            return iHashCode3 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "OAuthTokenResponse(token=" + this.token + ", userData=" + this.userData + ", status=" + this.status + ", message=" + this.message + ')';
        }

        /* JADX INFO: compiled from: Subdl.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$OAuthTokenResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$OAuthTokenResponse;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<OAuthTokenResponse> serializer() {
                return SubDlApi$OAuthTokenResponse$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ OAuthTokenResponse(int i, String str, UserData userData, Boolean bool, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, SubDlApi$OAuthTokenResponse$$serializer.INSTANCE.getDescriptor());
            }
            this.token = str;
            if ((i & 2) == 0) {
                this.userData = null;
            } else {
                this.userData = userData;
            }
            if ((i & 4) == 0) {
                this.status = null;
            } else {
                this.status = bool;
            }
            if ((i & 8) == 0) {
                this.message = null;
            } else {
                this.message = str2;
            }
        }

        public OAuthTokenResponse(@JsonProperty("token") String token, @JsonProperty("userData") UserData userData, @JsonProperty(NotificationCompat.CATEGORY_STATUS) Boolean bool, @JsonProperty(ContentType.Message.TYPE) String str) {
            Intrinsics.checkNotNullParameter(token, "token");
            this.token = token;
            this.userData = userData;
            this.status = bool;
            this.message = str;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(OAuthTokenResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.token);
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.userData != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, SubDlApi$UserData$$serializer.INSTANCE, self.userData);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.status != null) {
                output.encodeNullableSerializableElement(serialDesc, 2, BooleanSerializer.INSTANCE, self.status);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.message == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.message);
        }

        public /* synthetic */ OAuthTokenResponse(String str, UserData userData, Boolean bool, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : userData, (i & 4) != 0 ? null : bool, (i & 8) != 0 ? null : str2);
        }

        public final String getToken() {
            return this.token;
        }

        public final UserData getUserData() {
            return this.userData;
        }

        public final Boolean getStatus() {
            return this.status;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    /* JADX INFO: compiled from: Subdl.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 82\u00020\u0001:\u000278BO\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fB_\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u000b\u0010\u0011J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\bHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003JQ\u0010*\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\b2\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0014\u0010+\u001a\u00020\b2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010-\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010.\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00002\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0001¢\u0006\u0002\b6R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u001c\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0013\u001a\u0004\b\u001b\u0010\u0015R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0013\u001a\u0004\b\u001d\u0010\u001eR\u001e\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0013\u001a\u0004\b \u0010\u0015R\u001c\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u0013\u001a\u0004\b\"\u0010\u0015¨\u00069"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$UserData;", "", "email", "", "name", "country", "scStepCode", "scVerified", "", HintConstants.AUTOFILL_HINT_USERNAME, "scUsername", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getEmail$annotations", "()V", "getEmail", "()Ljava/lang/String;", "getName$annotations", "getName", "getCountry$annotations", "getCountry", "getScStepCode$annotations", "getScStepCode", "getScVerified$annotations", "getScVerified", "()Z", "getUsername$annotations", "getUsername", "getScUsername$annotations", "getScUsername", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class UserData {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("country")
        private final String country;

        @JsonProperty("email")
        private final String email;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("scStepCode")
        private final String scStepCode;

        @JsonProperty("scUsername")
        private final String scUsername;

        @JsonProperty("scVerified")
        private final boolean scVerified;

        @JsonProperty(HintConstants.AUTOFILL_HINT_USERNAME)
        private final String username;

        public static /* synthetic */ UserData copy$default(UserData userData, String str, String str2, String str3, String str4, boolean z, String str5, String str6, int i, Object obj) {
            if ((i & 1) != 0) {
                str = userData.email;
            }
            if ((i & 2) != 0) {
                str2 = userData.name;
            }
            if ((i & 4) != 0) {
                str3 = userData.country;
            }
            if ((i & 8) != 0) {
                str4 = userData.scStepCode;
            }
            if ((i & 16) != 0) {
                z = userData.scVerified;
            }
            if ((i & 32) != 0) {
                str5 = userData.username;
            }
            if ((i & 64) != 0) {
                str6 = userData.scUsername;
            }
            String str7 = str5;
            String str8 = str6;
            boolean z2 = z;
            String str9 = str3;
            return userData.copy(str, str2, str9, str4, z2, str7, str8);
        }

        @SerialName("country")
        public static /* synthetic */ void getCountry$annotations() {
        }

        @SerialName("email")
        public static /* synthetic */ void getEmail$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("scStepCode")
        public static /* synthetic */ void getScStepCode$annotations() {
        }

        @SerialName("scUsername")
        public static /* synthetic */ void getScUsername$annotations() {
        }

        @SerialName("scVerified")
        public static /* synthetic */ void getScVerified$annotations() {
        }

        @SerialName(HintConstants.AUTOFILL_HINT_USERNAME)
        public static /* synthetic */ void getUsername$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCountry() {
            return this.country;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getScStepCode() {
            return this.scStepCode;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getScVerified() {
            return this.scVerified;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getUsername() {
            return this.username;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getScUsername() {
            return this.scUsername;
        }

        public final UserData copy(@JsonProperty("email") String email, @JsonProperty("name") String name, @JsonProperty("country") String country, @JsonProperty("scStepCode") String scStepCode, @JsonProperty("scVerified") boolean scVerified, @JsonProperty(HintConstants.AUTOFILL_HINT_USERNAME) String username, @JsonProperty("scUsername") String scUsername) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(country, "country");
            Intrinsics.checkNotNullParameter(scStepCode, "scStepCode");
            Intrinsics.checkNotNullParameter(scUsername, "scUsername");
            return new UserData(email, name, country, scStepCode, scVerified, username, scUsername);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UserData)) {
                return false;
            }
            UserData userData = (UserData) other;
            return Intrinsics.areEqual(this.email, userData.email) && Intrinsics.areEqual(this.name, userData.name) && Intrinsics.areEqual(this.country, userData.country) && Intrinsics.areEqual(this.scStepCode, userData.scStepCode) && this.scVerified == userData.scVerified && Intrinsics.areEqual(this.username, userData.username) && Intrinsics.areEqual(this.scUsername, userData.scUsername);
        }

        public int hashCode() {
            int iHashCode = ((((((((this.email.hashCode() * 31) + this.name.hashCode()) * 31) + this.country.hashCode()) * 31) + this.scStepCode.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.scVerified)) * 31;
            String str = this.username;
            return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.scUsername.hashCode();
        }

        public String toString() {
            return "UserData(email=" + this.email + ", name=" + this.name + ", country=" + this.country + ", scStepCode=" + this.scStepCode + ", scVerified=" + this.scVerified + ", username=" + this.username + ", scUsername=" + this.scUsername + ')';
        }

        /* JADX INFO: compiled from: Subdl.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$UserData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$UserData;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<UserData> serializer() {
                return SubDlApi$UserData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ UserData(int i, String str, String str2, String str3, String str4, boolean z, String str5, String str6, SerializationConstructorMarker serializationConstructorMarker) {
            if (95 != (i & 95)) {
                PluginExceptionsKt.throwMissingFieldException(i, 95, SubDlApi$UserData$$serializer.INSTANCE.getDescriptor());
            }
            this.email = str;
            this.name = str2;
            this.country = str3;
            this.scStepCode = str4;
            this.scVerified = z;
            if ((i & 32) == 0) {
                this.username = null;
            } else {
                this.username = str5;
            }
            this.scUsername = str6;
        }

        public UserData(@JsonProperty("email") String email, @JsonProperty("name") String name, @JsonProperty("country") String country, @JsonProperty("scStepCode") String scStepCode, @JsonProperty("scVerified") boolean z, @JsonProperty(HintConstants.AUTOFILL_HINT_USERNAME) String str, @JsonProperty("scUsername") String scUsername) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(country, "country");
            Intrinsics.checkNotNullParameter(scStepCode, "scStepCode");
            Intrinsics.checkNotNullParameter(scUsername, "scUsername");
            this.email = email;
            this.name = name;
            this.country = country;
            this.scStepCode = scStepCode;
            this.scVerified = z;
            this.username = str;
            this.scUsername = scUsername;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(UserData self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.email);
            output.encodeStringElement(serialDesc, 1, self.name);
            output.encodeStringElement(serialDesc, 2, self.country);
            output.encodeStringElement(serialDesc, 3, self.scStepCode);
            output.encodeBooleanElement(serialDesc, 4, self.scVerified);
            if (output.shouldEncodeElementDefault(serialDesc, 5) || self.username != null) {
                output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.username);
            }
            output.encodeStringElement(serialDesc, 6, self.scUsername);
        }

        public /* synthetic */ UserData(String str, String str2, String str3, String str4, boolean z, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, z, (i & 32) != 0 ? null : str5, str6);
        }

        public final String getEmail() {
            return this.email;
        }

        public final String getName() {
            return this.name;
        }

        public final String getCountry() {
            return this.country;
        }

        public final String getScStepCode() {
            return this.scStepCode;
        }

        public final boolean getScVerified() {
            return this.scVerified;
        }

        public final String getUsername() {
            return this.username;
        }

        public final String getScUsername() {
            return this.scUsername;
        }
    }

    /* JADX INFO: compiled from: Subdl.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 ,2\u00020\u0001:\u0002+,B)\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB9\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0007HÆ\u0003J0\u0010\u001d\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0014\u0010\u001f\u001a\u00020\u00032\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0001¢\u0006\u0002\b*R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0010\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0010\u001a\u0004\b\u0018\u0010\u0019¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$ApiKeyResponse;", "", "ok", "", "apiKey", "", "usage", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Usage;", "<init>", "(Ljava/lang/Boolean;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Usage;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Boolean;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Usage;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getOk$annotations", "()V", "getOk", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getApiKey$annotations", "getApiKey", "()Ljava/lang/String;", "getUsage$annotations", "getUsage", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Usage;", "component1", "component2", "component3", "copy", "(Ljava/lang/Boolean;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Usage;)Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$ApiKeyResponse;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ApiKeyResponse {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty(Tmdb.PARAM_API_KEY)
        private final String apiKey;

        @JsonProperty("ok")
        private final Boolean ok;

        @JsonProperty("usage")
        private final Usage usage;

        public static /* synthetic */ ApiKeyResponse copy$default(ApiKeyResponse apiKeyResponse, Boolean bool, String str, Usage usage, int i, Object obj) {
            if ((i & 1) != 0) {
                bool = apiKeyResponse.ok;
            }
            if ((i & 2) != 0) {
                str = apiKeyResponse.apiKey;
            }
            if ((i & 4) != 0) {
                usage = apiKeyResponse.usage;
            }
            return apiKeyResponse.copy(bool, str, usage);
        }

        @SerialName(Tmdb.PARAM_API_KEY)
        public static /* synthetic */ void getApiKey$annotations() {
        }

        @SerialName("ok")
        public static /* synthetic */ void getOk$annotations() {
        }

        @SerialName("usage")
        public static /* synthetic */ void getUsage$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Boolean getOk() {
            return this.ok;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getApiKey() {
            return this.apiKey;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Usage getUsage() {
            return this.usage;
        }

        public final ApiKeyResponse copy(@JsonProperty("ok") Boolean ok, @JsonProperty(Tmdb.PARAM_API_KEY) String apiKey, @JsonProperty("usage") Usage usage) {
            Intrinsics.checkNotNullParameter(apiKey, "apiKey");
            return new ApiKeyResponse(ok, apiKey, usage);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ApiKeyResponse)) {
                return false;
            }
            ApiKeyResponse apiKeyResponse = (ApiKeyResponse) other;
            return Intrinsics.areEqual(this.ok, apiKeyResponse.ok) && Intrinsics.areEqual(this.apiKey, apiKeyResponse.apiKey) && Intrinsics.areEqual(this.usage, apiKeyResponse.usage);
        }

        public int hashCode() {
            Boolean bool = this.ok;
            int iHashCode = (((bool == null ? 0 : bool.hashCode()) * 31) + this.apiKey.hashCode()) * 31;
            Usage usage = this.usage;
            return iHashCode + (usage != null ? usage.hashCode() : 0);
        }

        public String toString() {
            return "ApiKeyResponse(ok=" + this.ok + ", apiKey=" + this.apiKey + ", usage=" + this.usage + ')';
        }

        /* JADX INFO: compiled from: Subdl.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$ApiKeyResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$ApiKeyResponse;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ApiKeyResponse> serializer() {
                return SubDlApi$ApiKeyResponse$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ApiKeyResponse(int i, Boolean bool, String str, Usage usage, SerializationConstructorMarker serializationConstructorMarker) {
            if (2 != (i & 2)) {
                PluginExceptionsKt.throwMissingFieldException(i, 2, SubDlApi$ApiKeyResponse$$serializer.INSTANCE.getDescriptor());
            }
            this.ok = (i & 1) == 0 ? false : bool;
            this.apiKey = str;
            if ((i & 4) == 0) {
                this.usage = null;
            } else {
                this.usage = usage;
            }
        }

        public ApiKeyResponse(@JsonProperty("ok") Boolean bool, @JsonProperty(Tmdb.PARAM_API_KEY) String apiKey, @JsonProperty("usage") Usage usage) {
            Intrinsics.checkNotNullParameter(apiKey, "apiKey");
            this.ok = bool;
            this.apiKey = apiKey;
            this.usage = usage;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(ApiKeyResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual((Object) self.ok, (Object) false)) {
                output.encodeNullableSerializableElement(serialDesc, 0, BooleanSerializer.INSTANCE, self.ok);
            }
            output.encodeStringElement(serialDesc, 1, self.apiKey);
            if (!output.shouldEncodeElementDefault(serialDesc, 2) && self.usage == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 2, SubDlApi$Usage$$serializer.INSTANCE, self.usage);
        }

        public /* synthetic */ ApiKeyResponse(Boolean bool, String str, Usage usage, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : bool, str, (i & 4) != 0 ? null : usage);
        }

        public final Boolean getOk() {
            return this.ok;
        }

        public final String getApiKey() {
            return this.apiKey;
        }

        public final Usage getUsage() {
            return this.usage;
        }
    }

    /* JADX INFO: compiled from: Subdl.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ&\u0010\u0015\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0010\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0010\u0012\u0004\b\u0011\u0010\r\u001a\u0004\b\u0012\u0010\u000f¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Usage;", "", "total", "", "today", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Long;Ljava/lang/Long;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTotal$annotations", "()V", "getTotal", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getToday$annotations", "getToday", "component1", "component2", "copy", "(Ljava/lang/Long;Ljava/lang/Long;)Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Usage;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Usage {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("today")
        private final Long today;

        @JsonProperty("total")
        private final Long total;

        /* JADX WARN: Multi-variable type inference failed */
        public Usage() {
            this((Long) null, (Long) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ Usage copy$default(Usage usage, Long l, Long l2, int i, Object obj) {
            if ((i & 1) != 0) {
                l = usage.total;
            }
            if ((i & 2) != 0) {
                l2 = usage.today;
            }
            return usage.copy(l, l2);
        }

        @SerialName("today")
        public static /* synthetic */ void getToday$annotations() {
        }

        @SerialName("total")
        public static /* synthetic */ void getTotal$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Long getTotal() {
            return this.total;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Long getToday() {
            return this.today;
        }

        public final Usage copy(@JsonProperty("total") Long total, @JsonProperty("today") Long today) {
            return new Usage(total, today);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Usage)) {
                return false;
            }
            Usage usage = (Usage) other;
            return Intrinsics.areEqual(this.total, usage.total) && Intrinsics.areEqual(this.today, usage.today);
        }

        public int hashCode() {
            Long l = this.total;
            int iHashCode = (l == null ? 0 : l.hashCode()) * 31;
            Long l2 = this.today;
            return iHashCode + (l2 != null ? l2.hashCode() : 0);
        }

        public String toString() {
            return "Usage(total=" + this.total + ", today=" + this.today + ')';
        }

        /* JADX INFO: compiled from: Subdl.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Usage$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Usage;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Usage> serializer() {
                return SubDlApi$Usage$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Usage(int i, Long l, Long l2, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.total = 0L;
            } else {
                this.total = l;
            }
            if ((i & 2) == 0) {
                this.today = 0L;
            } else {
                this.today = l2;
            }
        }

        public Usage(@JsonProperty("total") Long l, @JsonProperty("today") Long l2) {
            this.total = l;
            this.today = l2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Usage self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Long l;
            Long l2;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || (l2 = self.total) == null || l2.longValue() != 0) {
                output.encodeNullableSerializableElement(serialDesc, 0, LongSerializer.INSTANCE, self.total);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || (l = self.today) == null || l.longValue() != 0) {
                output.encodeNullableSerializableElement(serialDesc, 1, LongSerializer.INSTANCE, self.today);
            }
        }

        public /* synthetic */ Usage(Long l, Long l2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 0L : l, (i & 2) != 0 ? 0L : l2);
        }

        public final Long getTotal() {
            return this.total;
        }

        public final Long getToday() {
            return this.today;
        }
    }

    /* JADX INFO: compiled from: Subdl.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 -2\u00020\u0001:\u0002,-B7\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0010\b\u0003\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nBE\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\t\u0010\u000fJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0011\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005HÆ\u0003J>\u0010\u001d\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0003\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0014\u0010\u001f\u001a\u00020\u00032\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\fHÖ\u0081\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004J%\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0001¢\u0006\u0002\b+R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0014\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0011\u001a\u0004\b\u0016\u0010\u0017R$\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u0017¨\u0006."}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$ApiResponse;", "", NotificationCompat.CATEGORY_STATUS, "", "results", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Result;", "subtitles", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Subtitle;", "<init>", "(Ljava/lang/Boolean;Ljava/util/List;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Boolean;Ljava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getStatus$annotations", "()V", "getStatus", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getResults$annotations", "getResults", "()Ljava/util/List;", "getSubtitles$annotations", "getSubtitles", "component1", "component2", "component3", "copy", "(Ljava/lang/Boolean;Ljava/util/List;Ljava/util/List;)Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$ApiResponse;", "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ApiResponse {

        @JsonProperty("results")
        private final List<Result> results;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final Boolean status;

        @JsonProperty("subtitles")
        private final List<Subtitle> subtitles;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SubDlApi$ApiResponse$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SubDlApi.ApiResponse._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SubDlApi$ApiResponse$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SubDlApi.ApiResponse._childSerializers$_anonymous_$0();
            }
        })};

        public ApiResponse() {
            this((Boolean) null, (List) null, (List) null, 7, (DefaultConstructorMarker) null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(SubDlApi$Result$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(SubDlApi$Subtitle$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiResponse copy$default(ApiResponse apiResponse, Boolean bool, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                bool = apiResponse.status;
            }
            if ((i & 2) != 0) {
                list = apiResponse.results;
            }
            if ((i & 4) != 0) {
                list2 = apiResponse.subtitles;
            }
            return apiResponse.copy(bool, list, list2);
        }

        @SerialName("results")
        public static /* synthetic */ void getResults$annotations() {
        }

        @SerialName(NotificationCompat.CATEGORY_STATUS)
        public static /* synthetic */ void getStatus$annotations() {
        }

        @SerialName("subtitles")
        public static /* synthetic */ void getSubtitles$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Boolean getStatus() {
            return this.status;
        }

        public final List<Result> component2() {
            return this.results;
        }

        public final List<Subtitle> component3() {
            return this.subtitles;
        }

        public final ApiResponse copy(@JsonProperty(NotificationCompat.CATEGORY_STATUS) Boolean status, @JsonProperty("results") List<Result> results, @JsonProperty("subtitles") List<Subtitle> subtitles) {
            return new ApiResponse(status, results, subtitles);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ApiResponse)) {
                return false;
            }
            ApiResponse apiResponse = (ApiResponse) other;
            return Intrinsics.areEqual(this.status, apiResponse.status) && Intrinsics.areEqual(this.results, apiResponse.results) && Intrinsics.areEqual(this.subtitles, apiResponse.subtitles);
        }

        public int hashCode() {
            Boolean bool = this.status;
            int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
            List<Result> list = this.results;
            int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
            List<Subtitle> list2 = this.subtitles;
            return iHashCode2 + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            return "ApiResponse(status=" + this.status + ", results=" + this.results + ", subtitles=" + this.subtitles + ')';
        }

        /* JADX INFO: compiled from: Subdl.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$ApiResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$ApiResponse;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ApiResponse> serializer() {
                return SubDlApi$ApiResponse$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ApiResponse(int i, Boolean bool, List list, List list2, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.status = null;
            } else {
                this.status = bool;
            }
            if ((i & 2) == 0) {
                this.results = null;
            } else {
                this.results = list;
            }
            if ((i & 4) == 0) {
                this.subtitles = null;
            } else {
                this.subtitles = list2;
            }
        }

        public ApiResponse(@JsonProperty(NotificationCompat.CATEGORY_STATUS) Boolean bool, @JsonProperty("results") List<Result> list, @JsonProperty("subtitles") List<Subtitle> list2) {
            this.status = bool;
            this.results = list;
            this.subtitles = list2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(ApiResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.status != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, BooleanSerializer.INSTANCE, self.status);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.results != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.results);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 2) && self.subtitles == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 2, lazyArr[2].getValue(), self.subtitles);
        }

        public /* synthetic */ ApiResponse(Boolean bool, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? null : list, (i & 4) != 0 ? null : list2);
        }

        public final Boolean getStatus() {
            return this.status;
        }

        public final List<Result> getResults() {
            return this.results;
        }

        public final List<Subtitle> getSubtitles() {
            return this.subtitles;
        }
    }

    /* JADX INFO: compiled from: Subdl.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 =2\u00020\u0001:\u0002<=B[\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rBa\b\u0010\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\f\u0010\u0011J\u0010\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010 J\u000b\u0010+\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015Jb\u0010-\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010.J\u0014\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00102\u001a\u00020\u0003HÖ\u0081\u0004J\n\u00103\u001a\u00020\u0005HÖ\u0081\u0004J%\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u00002\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0001¢\u0006\u0002\b;R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0016\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0013\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0013\u001a\u0004\b\u001b\u0010\u0019R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0013\u001a\u0004\b\u001d\u0010\u0019R \u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010!\u0012\u0004\b\u001e\u0010\u0013\u001a\u0004\b\u001f\u0010 R\u001e\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0013\u001a\u0004\b#\u0010\u0019R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0016\u0012\u0004\b$\u0010\u0013\u001a\u0004\b%\u0010\u0015¨\u0006>"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Result;", "", "sdId", "", "type", "", "name", "imdbId", "tmdbId", "", "firstAirDate", "year", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSdId$annotations", "()V", "getSdId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getType$annotations", "getType", "()Ljava/lang/String;", "getName$annotations", "getName", "getImdbId$annotations", "getImdbId", "getTmdbId$annotations", "getTmdbId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFirstAirDate$annotations", "getFirstAirDate", "getYear$annotations", "getYear", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Result;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Result {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("first_air_date")
        private final String firstAirDate;

        @JsonProperty("imdb_id")
        private final String imdbId;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("sd_id")
        private final Integer sdId;

        @JsonProperty("tmdb_id")
        private final Long tmdbId;

        @JsonProperty("type")
        private final String type;

        @JsonProperty("year")
        private final Integer year;

        public Result() {
            this((Integer) null, (String) null, (String) null, (String) null, (Long) null, (String) null, (Integer) null, 127, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Result copy$default(Result result, Integer num, String str, String str2, String str3, Long l, String str4, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                num = result.sdId;
            }
            if ((i & 2) != 0) {
                str = result.type;
            }
            if ((i & 4) != 0) {
                str2 = result.name;
            }
            if ((i & 8) != 0) {
                str3 = result.imdbId;
            }
            if ((i & 16) != 0) {
                l = result.tmdbId;
            }
            if ((i & 32) != 0) {
                str4 = result.firstAirDate;
            }
            if ((i & 64) != 0) {
                num2 = result.year;
            }
            String str5 = str4;
            Integer num3 = num2;
            Long l2 = l;
            String str6 = str2;
            return result.copy(num, str, str6, str3, l2, str5, num3);
        }

        @SerialName("first_air_date")
        public static /* synthetic */ void getFirstAirDate$annotations() {
        }

        @SerialName("imdb_id")
        public static /* synthetic */ void getImdbId$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("sd_id")
        public static /* synthetic */ void getSdId$annotations() {
        }

        @SerialName("tmdb_id")
        public static /* synthetic */ void getTmdbId$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        @SerialName("year")
        public static /* synthetic */ void getYear$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getSdId() {
            return this.sdId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getImdbId() {
            return this.imdbId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Long getTmdbId() {
            return this.tmdbId;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getFirstAirDate() {
            return this.firstAirDate;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        public final Result copy(@JsonProperty("sd_id") Integer sdId, @JsonProperty("type") String type, @JsonProperty("name") String name, @JsonProperty("imdb_id") String imdbId, @JsonProperty("tmdb_id") Long tmdbId, @JsonProperty("first_air_date") String firstAirDate, @JsonProperty("year") Integer year) {
            return new Result(sdId, type, name, imdbId, tmdbId, firstAirDate, year);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return Intrinsics.areEqual(this.sdId, result.sdId) && Intrinsics.areEqual(this.type, result.type) && Intrinsics.areEqual(this.name, result.name) && Intrinsics.areEqual(this.imdbId, result.imdbId) && Intrinsics.areEqual(this.tmdbId, result.tmdbId) && Intrinsics.areEqual(this.firstAirDate, result.firstAirDate) && Intrinsics.areEqual(this.year, result.year);
        }

        public int hashCode() {
            Integer num = this.sdId;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.type;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.name;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.imdbId;
            int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Long l = this.tmdbId;
            int iHashCode5 = (iHashCode4 + (l == null ? 0 : l.hashCode())) * 31;
            String str4 = this.firstAirDate;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Integer num2 = this.year;
            return iHashCode6 + (num2 != null ? num2.hashCode() : 0);
        }

        public String toString() {
            return "Result(sdId=" + this.sdId + ", type=" + this.type + ", name=" + this.name + ", imdbId=" + this.imdbId + ", tmdbId=" + this.tmdbId + ", firstAirDate=" + this.firstAirDate + ", year=" + this.year + ')';
        }

        /* JADX INFO: compiled from: Subdl.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Result$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Result;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Result> serializer() {
                return SubDlApi$Result$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Result(int i, Integer num, String str, String str2, String str3, Long l, String str4, Integer num2, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.sdId = null;
            } else {
                this.sdId = num;
            }
            if ((i & 2) == 0) {
                this.type = null;
            } else {
                this.type = str;
            }
            if ((i & 4) == 0) {
                this.name = null;
            } else {
                this.name = str2;
            }
            if ((i & 8) == 0) {
                this.imdbId = null;
            } else {
                this.imdbId = str3;
            }
            if ((i & 16) == 0) {
                this.tmdbId = null;
            } else {
                this.tmdbId = l;
            }
            if ((i & 32) == 0) {
                this.firstAirDate = null;
            } else {
                this.firstAirDate = str4;
            }
            if ((i & 64) == 0) {
                this.year = null;
            } else {
                this.year = num2;
            }
        }

        public Result(@JsonProperty("sd_id") Integer num, @JsonProperty("type") String str, @JsonProperty("name") String str2, @JsonProperty("imdb_id") String str3, @JsonProperty("tmdb_id") Long l, @JsonProperty("first_air_date") String str4, @JsonProperty("year") Integer num2) {
            this.sdId = num;
            this.type = str;
            this.name = str2;
            this.imdbId = str3;
            this.tmdbId = l;
            this.firstAirDate = str4;
            this.year = num2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Result self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.sdId != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.sdId);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.type != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.type);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.name != null) {
                output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.name);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || self.imdbId != null) {
                output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.imdbId);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || self.tmdbId != null) {
                output.encodeNullableSerializableElement(serialDesc, 4, LongSerializer.INSTANCE, self.tmdbId);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || self.firstAirDate != null) {
                output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.firstAirDate);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 6) && self.year == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 6, IntSerializer.INSTANCE, self.year);
        }

        public /* synthetic */ Result(Integer num, String str, String str2, String str3, Long l, String str4, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : l, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : num2);
        }

        public final Integer getSdId() {
            return this.sdId;
        }

        public final String getType() {
            return this.type;
        }

        public final String getName() {
            return this.name;
        }

        public final String getImdbId() {
            return this.imdbId;
        }

        public final Long getTmdbId() {
            return this.tmdbId;
        }

        public final String getFirstAirDate() {
            return this.firstAirDate;
        }

        public final Integer getYear() {
            return this.year;
        }
    }

    /* JADX INFO: compiled from: Subdl.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 H2\u00020\u0001:\u0002GHBy\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010B\u007f\b\u0010\u0012\u0006\u0010\u0011\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u000f\u0010\u0014J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010%J\u0010\u00106\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010%J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00108\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010-J\u0080\u0001\u00109\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001¢\u0006\u0002\u0010:J\u0014\u0010;\u001a\u00020\u000e2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010=\u001a\u00020\nHÖ\u0081\u0004J\n\u0010>\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\u00002\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020EH\u0001¢\u0006\u0002\bFR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018R\u001c\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u001c\u0010\u0018R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u001e\u0010\u0018R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0016\u001a\u0004\b \u0010\u0018R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u0016\u001a\u0004\b\"\u0010\u0018R \u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010&\u0012\u0004\b#\u0010\u0016\u001a\u0004\b$\u0010%R \u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010&\u0012\u0004\b'\u0010\u0016\u001a\u0004\b(\u0010%R\u001e\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u0016\u001a\u0004\b*\u0010\u0018R \u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010.\u0012\u0004\b+\u0010\u0016\u001a\u0004\b,\u0010-¨\u0006I"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Subtitle;", "", "releaseName", "", "name", "lang", "author", ImagesContract.URL, "subtitlePage", "season", "", "episode", "language", "hearingImpaired", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getReleaseName$annotations", "()V", "getReleaseName", "()Ljava/lang/String;", "getName$annotations", "getName", "getLang$annotations", "getLang", "getAuthor$annotations", "getAuthor", "getUrl$annotations", "getUrl", "getSubtitlePage$annotations", "getSubtitlePage", "getSeason$annotations", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode$annotations", "getEpisode", "getLanguage$annotations", "getLanguage", "getHearingImpaired$annotations", "getHearingImpaired", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Subtitle;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Subtitle {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("author")
        private final String author;

        @JsonProperty("episode")
        private final Integer episode;

        @JsonProperty("hi")
        private final Boolean hearingImpaired;

        @JsonProperty("lang")
        private final String lang;

        @JsonProperty("language")
        private final String language;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("release_name")
        private final String releaseName;

        @JsonProperty("season")
        private final Integer season;

        @JsonProperty("subtitlePage")
        private final String subtitlePage;

        @JsonProperty(ImagesContract.URL)
        private final String url;

        public static /* synthetic */ Subtitle copy$default(Subtitle subtitle, String str, String str2, String str3, String str4, String str5, String str6, Integer num, Integer num2, String str7, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                str = subtitle.releaseName;
            }
            if ((i & 2) != 0) {
                str2 = subtitle.name;
            }
            if ((i & 4) != 0) {
                str3 = subtitle.lang;
            }
            if ((i & 8) != 0) {
                str4 = subtitle.author;
            }
            if ((i & 16) != 0) {
                str5 = subtitle.url;
            }
            if ((i & 32) != 0) {
                str6 = subtitle.subtitlePage;
            }
            if ((i & 64) != 0) {
                num = subtitle.season;
            }
            if ((i & 128) != 0) {
                num2 = subtitle.episode;
            }
            if ((i & 256) != 0) {
                str7 = subtitle.language;
            }
            if ((i & 512) != 0) {
                bool = subtitle.hearingImpaired;
            }
            String str8 = str7;
            Boolean bool2 = bool;
            Integer num3 = num;
            Integer num4 = num2;
            String str9 = str5;
            String str10 = str6;
            return subtitle.copy(str, str2, str3, str4, str9, str10, num3, num4, str8, bool2);
        }

        @SerialName("author")
        public static /* synthetic */ void getAuthor$annotations() {
        }

        @SerialName("episode")
        public static /* synthetic */ void getEpisode$annotations() {
        }

        @SerialName("hi")
        public static /* synthetic */ void getHearingImpaired$annotations() {
        }

        @SerialName("lang")
        public static /* synthetic */ void getLang$annotations() {
        }

        @SerialName("language")
        public static /* synthetic */ void getLanguage$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("release_name")
        public static /* synthetic */ void getReleaseName$annotations() {
        }

        @SerialName("season")
        public static /* synthetic */ void getSeason$annotations() {
        }

        @SerialName("subtitlePage")
        public static /* synthetic */ void getSubtitlePage$annotations() {
        }

        @SerialName(ImagesContract.URL)
        public static /* synthetic */ void getUrl$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getReleaseName() {
            return this.releaseName;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Boolean getHearingImpaired() {
            return this.hearingImpaired;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLang() {
            return this.lang;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getAuthor() {
            return this.author;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getSubtitlePage() {
            return this.subtitlePage;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getSeason() {
            return this.season;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getEpisode() {
            return this.episode;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        public final Subtitle copy(@JsonProperty("release_name") String releaseName, @JsonProperty("name") String name, @JsonProperty("lang") String lang, @JsonProperty("author") String author, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("subtitlePage") String subtitlePage, @JsonProperty("season") Integer season, @JsonProperty("episode") Integer episode, @JsonProperty("language") String language, @JsonProperty("hi") Boolean hearingImpaired) {
            Intrinsics.checkNotNullParameter(releaseName, "releaseName");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(lang, "lang");
            return new Subtitle(releaseName, name, lang, author, url, subtitlePage, season, episode, language, hearingImpaired);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Subtitle)) {
                return false;
            }
            Subtitle subtitle = (Subtitle) other;
            return Intrinsics.areEqual(this.releaseName, subtitle.releaseName) && Intrinsics.areEqual(this.name, subtitle.name) && Intrinsics.areEqual(this.lang, subtitle.lang) && Intrinsics.areEqual(this.author, subtitle.author) && Intrinsics.areEqual(this.url, subtitle.url) && Intrinsics.areEqual(this.subtitlePage, subtitle.subtitlePage) && Intrinsics.areEqual(this.season, subtitle.season) && Intrinsics.areEqual(this.episode, subtitle.episode) && Intrinsics.areEqual(this.language, subtitle.language) && Intrinsics.areEqual(this.hearingImpaired, subtitle.hearingImpaired);
        }

        public int hashCode() {
            int iHashCode = ((((this.releaseName.hashCode() * 31) + this.name.hashCode()) * 31) + this.lang.hashCode()) * 31;
            String str = this.author;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.url;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.subtitlePage;
            int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Integer num = this.season;
            int iHashCode5 = (iHashCode4 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.episode;
            int iHashCode6 = (iHashCode5 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str4 = this.language;
            int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Boolean bool = this.hearingImpaired;
            return iHashCode7 + (bool != null ? bool.hashCode() : 0);
        }

        public String toString() {
            return "Subtitle(releaseName=" + this.releaseName + ", name=" + this.name + ", lang=" + this.lang + ", author=" + this.author + ", url=" + this.url + ", subtitlePage=" + this.subtitlePage + ", season=" + this.season + ", episode=" + this.episode + ", language=" + this.language + ", hearingImpaired=" + this.hearingImpaired + ')';
        }

        /* JADX INFO: compiled from: Subdl.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Subtitle$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi$Subtitle;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Subtitle> serializer() {
                return SubDlApi$Subtitle$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Subtitle(int i, String str, String str2, String str3, String str4, String str5, String str6, Integer num, Integer num2, String str7, Boolean bool, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, SubDlApi$Subtitle$$serializer.INSTANCE.getDescriptor());
            }
            this.releaseName = str;
            this.name = str2;
            this.lang = str3;
            if ((i & 8) == 0) {
                this.author = null;
            } else {
                this.author = str4;
            }
            if ((i & 16) == 0) {
                this.url = null;
            } else {
                this.url = str5;
            }
            if ((i & 32) == 0) {
                this.subtitlePage = null;
            } else {
                this.subtitlePage = str6;
            }
            if ((i & 64) == 0) {
                this.season = null;
            } else {
                this.season = num;
            }
            if ((i & 128) == 0) {
                this.episode = null;
            } else {
                this.episode = num2;
            }
            if ((i & 256) == 0) {
                this.language = null;
            } else {
                this.language = str7;
            }
            if ((i & 512) == 0) {
                this.hearingImpaired = null;
            } else {
                this.hearingImpaired = bool;
            }
        }

        public Subtitle(@JsonProperty("release_name") String releaseName, @JsonProperty("name") String name, @JsonProperty("lang") String lang, @JsonProperty("author") String str, @JsonProperty(ImagesContract.URL) String str2, @JsonProperty("subtitlePage") String str3, @JsonProperty("season") Integer num, @JsonProperty("episode") Integer num2, @JsonProperty("language") String str4, @JsonProperty("hi") Boolean bool) {
            Intrinsics.checkNotNullParameter(releaseName, "releaseName");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(lang, "lang");
            this.releaseName = releaseName;
            this.name = name;
            this.lang = lang;
            this.author = str;
            this.url = str2;
            this.subtitlePage = str3;
            this.season = num;
            this.episode = num2;
            this.language = str4;
            this.hearingImpaired = bool;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Subtitle self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.releaseName);
            output.encodeStringElement(serialDesc, 1, self.name);
            output.encodeStringElement(serialDesc, 2, self.lang);
            if (output.shouldEncodeElementDefault(serialDesc, 3) || self.author != null) {
                output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.author);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || self.url != null) {
                output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.url);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || self.subtitlePage != null) {
                output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.subtitlePage);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 6) || self.season != null) {
                output.encodeNullableSerializableElement(serialDesc, 6, IntSerializer.INSTANCE, self.season);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 7) || self.episode != null) {
                output.encodeNullableSerializableElement(serialDesc, 7, IntSerializer.INSTANCE, self.episode);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 8) || self.language != null) {
                output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.language);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 9) && self.hearingImpaired == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 9, BooleanSerializer.INSTANCE, self.hearingImpaired);
        }

        public /* synthetic */ Subtitle(String str, String str2, String str3, String str4, String str5, String str6, Integer num, Integer num2, String str7, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : num, (i & 128) != 0 ? null : num2, (i & 256) != 0 ? null : str7, (i & 512) != 0 ? null : bool);
        }

        public final String getReleaseName() {
            return this.releaseName;
        }

        public final String getName() {
            return this.name;
        }

        public final String getLang() {
            return this.lang;
        }

        public final String getAuthor() {
            return this.author;
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getSubtitlePage() {
            return this.subtitlePage;
        }

        public final Integer getSeason() {
            return this.season;
        }

        public final Integer getEpisode() {
            return this.episode;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final Boolean getHearingImpaired() {
            return this.hearingImpaired;
        }
    }
}
