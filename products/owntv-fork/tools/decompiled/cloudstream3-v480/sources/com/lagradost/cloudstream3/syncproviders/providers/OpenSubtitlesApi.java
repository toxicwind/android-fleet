package com.lagradost.cloudstream3.syncproviders.providers;

import android.util.Log;
import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities;
import com.lagradost.cloudstream3.syncproviders.AuthData;
import com.lagradost.cloudstream3.syncproviders.AuthLoginRequirement;
import com.lagradost.cloudstream3.syncproviders.AuthLoginResponse;
import com.lagradost.cloudstream3.syncproviders.AuthToken;
import com.lagradost.cloudstream3.syncproviders.SubtitleAPI;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import io.ktor.http.ContentType;
import io.ktor.util.date.GMTDateParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
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
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.classfile.ByteCode;

/* JADX INFO: compiled from: OpenSubtitlesApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 12\u00020\u0001:\b12345678B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\u000fH\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0002J\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0096@¢\u0006\u0002\u0010\u001fJ\u001a\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0096@¢\u0006\u0002\u0010\u001fJ\u0018\u0010\"\u001a\u0004\u0018\u00010\u001d2\u0006\u0010#\u001a\u00020$H\u0096@¢\u0006\u0002\u0010%J(\u0010&\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'2\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020,H\u0096@¢\u0006\u0002\u0010-J\"\u0010.\u001a\u0004\u0018\u00010\u00052\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010/\u001a\u00020(H\u0096@¢\u0006\u0002\u00100R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007¨\u00069"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi;", "Lcom/lagradost/cloudstream3/syncproviders/SubtitleAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "idPrefix", "getIdPrefix", "icon", "", "getIcon", "()Ljava/lang/Integer;", "hasInApp", "", "getHasInApp", "()Z", "inAppLoginRequirement", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginRequirement;", "getInAppLoginRequirement", "()Lcom/lagradost/cloudstream3/syncproviders/AuthLoginRequirement;", "createAccountUrl", "getCreateAccountUrl", "canDoRequest", "throwIfCantDoRequest", "", "throwGotTooManyRequests", "refreshToken", "Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "token", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "user", "Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "login", "form", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "", "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;", "auth", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", SearchIntents.EXTRA_QUERY, "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", MediaTrack.ROLE_SUBTITLE, "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "OAuthToken", "Results", "ResultData", "ResultAttributes", "ResultFiles", "ResultDownloadLink", "ResultFeatureDetails", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class OpenSubtitlesApi extends SubtitleAPI {
    public static final long COOLDOWN_DURATION = 30000;
    public static final String HOST = "https://api.opensubtitles.com/api/v1";
    public static final String TAG = "OPENSUBS";
    private static long currentCoolDown = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String userAgent = "Cloudstream3 v0.2";
    public static final String API_KEY = "uyBLgFD17MgrYmA0gSXoKllMJBelOYj2";
    private static final Map<String, String> headers = MapsKt.mapOf(TuplesKt.to("user-agent", userAgent), TuplesKt.to("Api-Key", API_KEY));
    private final String name = "OpenSubtitles";
    private final String idPrefix = "opensubtitles";
    private final int icon = R.drawable.open_subtitles_icon;
    private final boolean hasInApp = true;
    private final AuthLoginRequirement inAppLoginRequirement = new AuthLoginRequirement(true, true, false, false, 12, null);
    private final String createAccountUrl = "https://www.opensubtitles.com/en/users/sign_up";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$load$1, reason: invalid class name */
    /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi", f = "OpenSubtitlesApi.kt", i = {0, 0}, l = {ByteCode.MONITOREXIT}, m = "load", n = {"auth", MediaTrack.ROLE_SUBTITLE}, nl = {209}, s = {"L$0", "L$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
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
            return OpenSubtitlesApi.this.load(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$login$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi", f = "OpenSubtitlesApi.kt", i = {0, 0, 0}, l = {81}, m = "login", n = {"form", HintConstants.AUTOFILL_HINT_USERNAME, HintConstants.AUTOFILL_HINT_PASSWORD}, nl = {90}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class C08061 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C08061(Continuation<? super C08061> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OpenSubtitlesApi.this.login((AuthLoginResponse) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi", f = "OpenSubtitlesApi.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {127}, m = "search", n = {"auth", SearchIntents.EXTRA_QUERY, "langOpenSubTag", "queryText", "epQuery", "seasonQuery", "yearQuery", "searchQueryUrl", "imdbId", "epNum", "seasonNum", "yearNum"}, nl = {133}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "I$1", "I$2", "I$3"}, v = 2)
    static final class C08071 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        C08071(Continuation<? super C08071> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OpenSubtitlesApi.this.search(null, null, this);
        }
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
    public String getCreateAccountUrl() {
        return this.createAccountUrl;
    }

    /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$Companion;", "", "<init>", "()V", "API_KEY", "", "HOST", "TAG", "COOLDOWN_DURATION", "", "currentCoolDown", "getCurrentCoolDown", "()J", "setCurrentCoolDown", "(J)V", "userAgent", "headers", "", "getHeaders", "()Ljava/util/Map;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long getCurrentCoolDown() {
            return OpenSubtitlesApi.currentCoolDown;
        }

        public final void setCurrentCoolDown(long j) {
            OpenSubtitlesApi.currentCoolDown = j;
        }

        public final Map<String, String> getHeaders() {
            return OpenSubtitlesApi.headers;
        }
    }

    private final boolean canDoRequest() {
        return APIHolder.INSTANCE.getUnixTimeMS() > currentCoolDown;
    }

    private final void throwIfCantDoRequest() throws ErrorLoadingException {
        if (canDoRequest()) {
            return;
        }
        throw new ErrorLoadingException("Too many requests wait for " + ((currentCoolDown - APIHolder.INSTANCE.getUnixTimeMS()) / 1000) + GMTDateParser.SECONDS);
    }

    private final void throwGotTooManyRequests() throws ErrorLoadingException {
        currentCoolDown = APIHolder.INSTANCE.getUnixTimeMS() + 30000;
        throw new ErrorLoadingException("Too many requests");
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object refreshToken(AuthToken authToken, Continuation<? super AuthToken> continuation) throws JsonProcessingException {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        AppUtils appUtils = AppUtils.INSTANCE;
        String payload = authToken.getPayload();
        if (payload == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(AuthLoginResponse.INSTANCE.serializer());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(AuthLoginResponse.class), null, 2, null));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
            }
        }
        KSerializer kSerializer = (KSerializer) (Result.m8942isFailureimpl(objM8936constructorimpl) ? null : objM8936constructorimpl);
        if (kSerializer != null) {
            try {
                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, payload);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
                objDecodeFromString = MainAPIKt.getMapper().readValue(payload, new TypeReference<AuthLoginResponse>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$refreshToken$$inlined$parseJson$1
                });
            } catch (Throwable unused) {
                objDecodeFromString = MainAPIKt.getMapper().readValue(payload, new TypeReference<AuthLoginResponse>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$refreshToken$$inlined$parseJson$1
                });
            }
        } else {
            objDecodeFromString = MainAPIKt.getMapper().readValue(payload, new TypeReference<AuthLoginResponse>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$refreshToken$$inlined$parseJson$1
            });
        }
        return login((AuthLoginResponse) objDecodeFromString, continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0005, code lost:
    
        r8 = r8.getPayload();
     */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object user(com.lagradost.cloudstream3.syncproviders.AuthToken r8, kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.syncproviders.AuthUser> r9) throws com.fasterxml.jackson.core.JsonProcessingException {
        /*
            r7 = this;
            com.lagradost.cloudstream3.utils.AppUtils r7 = com.lagradost.cloudstream3.utils.AppUtils.INSTANCE
            r7 = 0
            if (r8 == 0) goto L99
            java.lang.String r8 = r8.getPayload()
            if (r8 != 0) goto Ld
            goto L99
        Ld:
            kotlin.Result$Companion r9 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L1a
            com.lagradost.cloudstream3.syncproviders.AuthLoginResponse$Companion r9 = com.lagradost.cloudstream3.syncproviders.AuthLoginResponse.INSTANCE     // Catch: java.lang.Throwable -> L1a
            kotlinx.serialization.KSerializer r9 = r9.serializer()     // Catch: java.lang.Throwable -> L1a
            java.lang.Object r9 = kotlin.Result.m8936constructorimpl(r9)     // Catch: java.lang.Throwable -> L1a
            goto L26
        L1a:
            r0 = move-exception
            r9 = r0
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r9)
            java.lang.Object r9 = kotlin.Result.m8936constructorimpl(r9)
        L26:
            java.lang.Throwable r0 = kotlin.Result.m8939exceptionOrNullimpl(r9)
            if (r0 != 0) goto L2d
            goto L53
        L2d:
            kotlin.Result$Companion r9 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L47
            kotlinx.serialization.json.Json r9 = com.lagradost.cloudstream3.MainAPIKt.getJson()     // Catch: java.lang.Throwable -> L47
            kotlinx.serialization.modules.SerializersModule r9 = r9.getSerializersModule()     // Catch: java.lang.Throwable -> L47
            java.lang.Class<com.lagradost.cloudstream3.syncproviders.AuthLoginResponse> r0 = com.lagradost.cloudstream3.syncproviders.AuthLoginResponse.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)     // Catch: java.lang.Throwable -> L47
            r1 = 2
            kotlinx.serialization.KSerializer r9 = kotlinx.serialization.modules.SerializersModule.getContextual$default(r9, r0, r7, r1, r7)     // Catch: java.lang.Throwable -> L47
            java.lang.Object r9 = kotlin.Result.m8936constructorimpl(r9)     // Catch: java.lang.Throwable -> L47
            goto L53
        L47:
            r0 = move-exception
            r9 = r0
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r9)
            java.lang.Object r9 = kotlin.Result.m8936constructorimpl(r9)
        L53:
            boolean r0 = kotlin.Result.m8942isFailureimpl(r9)
            if (r0 == 0) goto L5a
            r9 = r7
        L5a:
            kotlinx.serialization.KSerializer r9 = (kotlinx.serialization.KSerializer) r9
            if (r9 == 0) goto L70
            kotlinx.serialization.json.Json r0 = com.lagradost.cloudstream3.MainAPIKt.getJson()     // Catch: kotlinx.serialization.SerializationException -> L69 java.lang.Throwable -> L70
            kotlinx.serialization.DeserializationStrategy r9 = (kotlinx.serialization.DeserializationStrategy) r9     // Catch: kotlinx.serialization.SerializationException -> L69 java.lang.Throwable -> L70
            java.lang.Object r8 = r0.decodeFromString(r9, r8)     // Catch: kotlinx.serialization.SerializationException -> L69 java.lang.Throwable -> L70
            goto L81
        L69:
            r0 = move-exception
            r9 = r0
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            com.lagradost.cloudstream3.mvvm.ArchComponentExtKt.logError(r9)
        L70:
            com.fasterxml.jackson.databind.json.JsonMapper r9 = com.lagradost.cloudstream3.MainAPIKt.getMapper()
            com.fasterxml.jackson.databind.ObjectMapper r9 = (com.fasterxml.jackson.databind.ObjectMapper) r9
            com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$user$$inlined$parseJson$1 r0 = new com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$user$$inlined$parseJson$1
            r0.<init>()
            com.fasterxml.jackson.core.type.TypeReference r0 = (com.fasterxml.jackson.core.type.TypeReference) r0
            java.lang.Object r8 = r9.readValue(r8, r0)
        L81:
            com.lagradost.cloudstream3.syncproviders.AuthLoginResponse r8 = (com.lagradost.cloudstream3.syncproviders.AuthLoginResponse) r8
            java.lang.String r1 = r8.getUsername()
            if (r1 != 0) goto L8a
            return r7
        L8a:
            int r2 = r1.hashCode()
            com.lagradost.cloudstream3.syncproviders.AuthUser r0 = new com.lagradost.cloudstream3.syncproviders.AuthUser
            r5 = 12
            r6 = 0
            r3 = 0
            r4 = 0
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r0
        L99:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi.user(com.lagradost.cloudstream3.syncproviders.AuthToken, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object login(AuthLoginResponse authLoginResponse, Continuation<? super AuthToken> continuation) throws ErrorLoadingException {
        C08061 c08061;
        String password;
        AuthLoginResponse authLoginResponse2;
        if (continuation instanceof C08061) {
            c08061 = (C08061) continuation;
            if ((c08061.label & Integer.MIN_VALUE) != 0) {
                c08061.label -= Integer.MIN_VALUE;
            } else {
                c08061 = new C08061(continuation);
            }
        } else {
            c08061 = new C08061(continuation);
        }
        Object obj = c08061.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08061.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String username = authLoginResponse.getUsername();
            if (username == null || (password = authLoginResponse.getPassword()) == null) {
                return null;
            }
            Requests app2 = MainActivityKt.getApp();
            Map mapPlus = MapsKt.plus(MapsKt.mapOf(TuplesKt.to("Content-Type", "application/json")), headers);
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to(HintConstants.AUTOFILL_HINT_USERNAME, username), TuplesKt.to(HintConstants.AUTOFILL_HINT_PASSWORD, password));
            c08061.L$0 = authLoginResponse;
            c08061.L$1 = SpillingKt.nullOutSpilledVariable(username);
            c08061.L$2 = SpillingKt.nullOutSpilledVariable(password);
            c08061.label = 1;
            Object objPost$default = Requests.post$default(app2, "https://api.opensubtitles.com/api/v1/login", mapPlus, null, null, null, null, null, mapMapOf, null, false, 0, null, 0L, null, false, null, c08061, 65404, null);
            if (objPost$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = objPost$default;
            authLoginResponse2 = authLoginResponse;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            authLoginResponse2 = (AuthLoginResponse) c08061.L$0;
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        String token = ((OAuthToken) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(OAuthToken.class))).getToken();
        if (token != null) {
            return new AuthToken(token, (String) null, Boxing.boxLong(APIHolder.INSTANCE.getUnixTime() + 86400), (Long) null, AppUtils.INSTANCE.toJson(authLoginResponse2), 10, (DefaultConstructorMarker) null);
        }
        throw new ErrorLoadingException("Invalid password or username");
    }

    /* JADX WARN: Code duplicated, block: B:178:0x025b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:81:0x0256  */
    /* JADX WARN: Code duplicated, block: B:88:0x026c A[Catch: Exception -> 0x0209, TRY_LEAVE, TryCatch #1 {Exception -> 0x0209, blocks: (B:72:0x0224, B:79:0x0250, B:82:0x0257, B:88:0x026c, B:87:0x0267, B:78:0x0246, B:71:0x021a, B:75:0x022b, B:68:0x020c, B:84:0x025b), top: B:175:0x0207, inners: #0, #2, #3, #5 }] */
    @Override // com.lagradost.cloudstream3.syncproviders.SubtitleAPI
    public Object search(AuthData authData, AbstractSubtitleEntities.SubtitleSearch subtitleSearch, Continuation<? super List<AbstractSubtitleEntities.SubtitleEntity>> continuation) throws ErrorLoadingException {
        C08071 c08071;
        OpenSubtitlesApi openSubtitlesApi;
        String str;
        AbstractSubtitleEntities.SubtitleSearch subtitleSearch2;
        String str2;
        String strReplace$default;
        Object objDecodeFromString;
        List<ResultData> data;
        String movieName;
        Integer epNumber;
        Integer seasonNumber;
        Integer year;
        List<ResultFiles> files;
        String strValueOf;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        if (continuation instanceof C08071) {
            c08071 = (C08071) continuation;
            if ((c08071.label & Integer.MIN_VALUE) != 0) {
                c08071.label -= Integer.MIN_VALUE;
                openSubtitlesApi = this;
            } else {
                openSubtitlesApi = this;
                c08071 = openSubtitlesApi.new C08071(continuation);
            }
        } else {
            openSubtitlesApi = this;
            c08071 = openSubtitlesApi.new C08071(continuation);
        }
        Object obj = c08071.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08071.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            openSubtitlesApi.throwIfCantDoRequest();
            String strFromCodeToOpenSubtitlesTag = SubtitleHelper.INSTANCE.fromCodeToOpenSubtitlesTag(subtitleSearch.getLang());
            if (strFromCodeToOpenSubtitlesTag == null && (strFromCodeToOpenSubtitlesTag = subtitleSearch.getLang()) == null) {
                strFromCodeToOpenSubtitlesTag = "";
            }
            String imdbId = subtitleSearch.getImdbId();
            int i2 = (imdbId == null || (strReplace$default = StringsKt.replace$default(imdbId, TtmlNode.TAG_TT, "", false, 4, (Object) null)) == null) ? 0 : Integer.parseInt(strReplace$default);
            String query = subtitleSearch.getQuery();
            Integer epNumber2 = subtitleSearch.getEpNumber();
            int iIntValue = epNumber2 != null ? epNumber2.intValue() : 0;
            Integer seasonNumber2 = subtitleSearch.getSeasonNumber();
            int iIntValue2 = seasonNumber2 != null ? seasonNumber2.intValue() : 0;
            Integer year2 = subtitleSearch.getYear();
            int iIntValue3 = year2 != null ? year2.intValue() : 0;
            String str3 = iIntValue > 0 ? "&episode_number=" + iIntValue : "";
            String str4 = iIntValue2 > 0 ? "&season_number=" + iIntValue2 : "";
            String str5 = iIntValue3 > 0 ? "&year=" + iIntValue3 : "";
            boolean z = i2 > 0;
            if (z) {
                str = "https://api.opensubtitles.com/api/v1/subtitles?imdb_id=" + i2 + "&languages=" + strFromCodeToOpenSubtitlesTag + str5 + str3 + str4;
            } else {
                if (z) {
                    throw new NoWhenBranchMatchedException();
                }
                str = "https://api.opensubtitles.com/api/v1/subtitles?query=" + query + "&languages=" + strFromCodeToOpenSubtitlesTag + str5 + str3 + str4;
            }
            Requests app2 = MainActivityKt.getApp();
            Map mapPlus = MapsKt.plus(MapsKt.mapOf(new Pair("Content-Type", "application/json")), headers);
            c08071.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c08071.L$1 = subtitleSearch;
            c08071.L$2 = SpillingKt.nullOutSpilledVariable(strFromCodeToOpenSubtitlesTag);
            c08071.L$3 = SpillingKt.nullOutSpilledVariable(query);
            c08071.L$4 = SpillingKt.nullOutSpilledVariable(str3);
            c08071.L$5 = SpillingKt.nullOutSpilledVariable(str4);
            c08071.L$6 = SpillingKt.nullOutSpilledVariable(str5);
            c08071.L$7 = str;
            c08071.I$0 = i2;
            c08071.I$1 = iIntValue;
            c08071.I$2 = iIntValue2;
            c08071.I$3 = iIntValue3;
            c08071.label = 1;
            String str6 = str;
            Object obj2 = Requests.get$default(app2, str6, mapPlus, null, null, null, false, 0, null, 0L, null, false, null, c08071, 4092, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            subtitleSearch2 = subtitleSearch;
            obj = obj2;
            str2 = str6;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c08071.I$3;
            int i4 = c08071.I$2;
            int i5 = c08071.I$1;
            int i6 = c08071.I$0;
            str2 = (String) c08071.L$7;
            subtitleSearch2 = (AbstractSubtitleEntities.SubtitleSearch) c08071.L$1;
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        Log.i(TAG, "searchQueryUrl => " + str2);
        Log.i(TAG, "Search Req => " + niceResponse.getText());
        if (!niceResponse.getIsSuccessful()) {
            if (niceResponse.getCode() == 429) {
                openSubtitlesApi.throwGotTooManyRequests();
            }
            return null;
        }
        ArrayList arrayList = new ArrayList();
        AppUtils appUtils = AppUtils.INSTANCE;
        String text = niceResponse.getText();
        try {
            if (text == null) {
                objDecodeFromString = null;
            } else {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(Results.INSTANCE.serializer());
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                    if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                        objM8936constructorimpl = null;
                    }
                    kSerializer = (KSerializer) objM8936constructorimpl;
                    if (kSerializer != null) {
                        try {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
                        } catch (SerializationException e) {
                            ArchComponentExtKt.logError(e);
                            objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<Results>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$search$$inlined$tryParseJson$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<Results>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$search$$inlined$tryParseJson$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<Results>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$search$$inlined$tryParseJson$1
                        });
                    }
                } else {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Results.class), null, 2, null));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                        objM8936constructorimpl = null;
                    }
                    kSerializer = (KSerializer) objM8936constructorimpl;
                    if (kSerializer != null) {
                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<Results>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$search$$inlined$tryParseJson$1
                        });
                    }
                }
            }
        } catch (Exception unused2) {
            objDecodeFromString = null;
        }
        Results results = (Results) objDecodeFromString;
        if (results != null && (data = results.getData()) != null) {
            for (ResultData resultData : data) {
                ResultAttributes attributes = resultData.getAttributes();
                if (attributes != null) {
                    ResultFeatureDetails featDetails = attributes.getFeatDetails();
                    List<ResultFiles> files2 = attributes.getFiles();
                    if (files2 == null) {
                        movieName = null;
                        break;
                    }
                    Iterator<T> it = files2.iterator();
                    do {
                        if (!it.hasNext()) {
                            movieName = null;
                            break;
                        }
                        movieName = ((ResultFiles) it.next()).getFileName();
                    } while (movieName == null);
                    if (movieName == null) {
                        movieName = featDetails != null ? featDetails.getMovieName() : null;
                        if (movieName == null) {
                            movieName = featDetails != null ? featDetails.getTitle() : null;
                            if (movieName == null) {
                                movieName = featDetails != null ? featDetails.getParentTitle() : null;
                                if (movieName == null && (movieName = attributes.getRelease()) == null) {
                                    movieName = subtitleSearch2.getQuery();
                                }
                            }
                        }
                    }
                    String str7 = movieName;
                    String strFromCodeToLangTagIETF = SubtitleHelper.INSTANCE.fromCodeToLangTagIETF(attributes.getLanguage());
                    String str8 = strFromCodeToLangTagIETF == null ? "" : strFromCodeToLangTagIETF;
                    if (featDetails == null || (epNumber = featDetails.getEpisodeNumber()) == null) {
                        epNumber = subtitleSearch2.getEpNumber();
                    }
                    Integer num = epNumber;
                    if (featDetails == null || (seasonNumber = featDetails.getSeasonNumber()) == null) {
                        seasonNumber = subtitleSearch2.getSeasonNumber();
                    }
                    Integer num2 = seasonNumber;
                    if (featDetails == null || (year = featDetails.getYear()) == null) {
                        year = subtitleSearch2.getYear();
                    }
                    Integer num3 = year;
                    TvType tvType = (num2 != null ? num2.intValue() : 0) > 0 ? TvType.TvSeries : TvType.Movie;
                    Boolean hearingImpaired = attributes.getHearingImpaired();
                    boolean zBooleanValue = hearingImpaired != null ? hearingImpaired.booleanValue() : false;
                    ResultAttributes attributes2 = resultData.getAttributes();
                    if (attributes2 != null && (files = attributes2.getFiles()) != null) {
                        Iterator<T> it2 = files.iterator();
                        while (it2.hasNext()) {
                            Integer fileId = ((ResultFiles) it2.next()).getFileId();
                            arrayList.add(new AbstractSubtitleEntities.SubtitleEntity(openSubtitlesApi.getIdPrefix(), str7, str8, (fileId == null || (strValueOf = String.valueOf(fileId.intValue())) == null) ? "" : strValueOf, tvType, openSubtitlesApi.getName(), num, num2, num3, zBooleanValue, null, 1024, null));
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.SubtitleAPI
    public Object load(AuthData authData, AbstractSubtitleEntities.SubtitleEntity subtitleEntity, Continuation<? super String> continuation) throws ErrorLoadingException {
        AnonymousClass1 anonymousClass1;
        OpenSubtitlesApi openSubtitlesApi;
        List list;
        int i;
        Object obj;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
                openSubtitlesApi = this;
            } else {
                openSubtitlesApi = this;
                anonymousClass1 = openSubtitlesApi.new AnonymousClass1(continuation);
            }
        } else {
            openSubtitlesApi = this;
            anonymousClass1 = openSubtitlesApi.new AnonymousClass1(continuation);
        }
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            if (authData == null) {
                return null;
            }
            openSubtitlesApi.throwIfCantDoRequest();
            Requests app2 = MainActivityKt.getApp();
            Pair[] pairArr = new Pair[3];
            StringBuilder sb = new StringBuilder("Bearer ");
            String accessToken = authData.getToken().getAccessToken();
            if (accessToken != null) {
                sb.append(accessToken);
                pairArr[0] = new Pair("Authorization", sb.toString());
                pairArr[1] = new Pair("Content-Type", "application/json");
                pairArr[2] = new Pair("Accept", "*/*");
                Map mapPlus = MapsKt.plus(MapsKt.mapOf(pairArr), headers);
                Map mapMapOf = MapsKt.mapOf(new Pair(FontsContractCompat.Columns.FILE_ID, subtitleEntity.getData()));
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(authData);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(subtitleEntity);
                anonymousClass1.label = 1;
                AnonymousClass1 anonymousClass2 = anonymousClass1;
                list = null;
                i = 2;
                Object objPost$default = Requests.post$default(app2, "https://api.opensubtitles.com/api/v1/download", mapPlus, null, null, null, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 65500, null);
                if (objPost$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj2 = objPost$default;
            } else {
                throw new ErrorLoadingException("No access token active in current session");
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj2);
            i = 2;
            list = null;
        }
        NiceResponse niceResponse = (NiceResponse) obj2;
        Log.i(TAG, "Request result  => (" + niceResponse.getCode() + ") " + niceResponse.getText());
        if (niceResponse.getIsSuccessful()) {
            AppUtils appUtils = AppUtils.INSTANCE;
            String text = niceResponse.getText();
            try {
                if (text == null) {
                    obj = list;
                } else {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultDownloadLink.INSTANCE.serializer());
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(ResultDownloadLink.class), list, i, list));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                    }
                    Object obj3 = objM8936constructorimpl;
                    if (Result.m8942isFailureimpl(obj3)) {
                        obj3 = list;
                    }
                    KSerializer kSerializer = (KSerializer) obj3;
                    if (kSerializer != null) {
                        try {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
                        } catch (SerializationException e) {
                            ArchComponentExtKt.logError(e);
                            objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<ResultDownloadLink>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$load$$inlined$tryParseJson$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<ResultDownloadLink>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$load$$inlined$tryParseJson$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<ResultDownloadLink>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$load$$inlined$tryParseJson$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
                obj = list;
            }
            ResultDownloadLink resultDownloadLink = (ResultDownloadLink) obj;
            if (resultDownloadLink != null) {
                String link = resultDownloadLink.getLink();
                if (link == null) {
                    link = "";
                }
                Log.i(TAG, "Request load link => " + link);
                return link;
            }
        } else if (niceResponse.getCode() == 429) {
            throwGotTooManyRequests();
        }
        return list;
    }

    /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 *2\u00020\u0001:\u0002)*B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0014J&\u0010\u001a\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004J%\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0001¢\u0006\u0002\b(R&\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R(\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u0017\u0012\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006+"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$OAuthToken;", "", "token", "", NotificationCompat.CATEGORY_STATUS, "", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getToken$annotations", "()V", "getToken", "()Ljava/lang/String;", "setToken", "(Ljava/lang/String;)V", "getStatus$annotations", "getStatus", "()Ljava/lang/Integer;", "setStatus", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$OAuthToken;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class OAuthToken {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private Integer status;

        @JsonProperty("token")
        private String token;

        /* JADX WARN: Multi-variable type inference failed */
        public OAuthToken() {
            this((String) null, (Integer) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ OAuthToken copy$default(OAuthToken oAuthToken, String str, Integer num, int i, Object obj) {
            if ((i & 1) != 0) {
                str = oAuthToken.token;
            }
            if ((i & 2) != 0) {
                num = oAuthToken.status;
            }
            return oAuthToken.copy(str, num);
        }

        @SerialName(NotificationCompat.CATEGORY_STATUS)
        public static /* synthetic */ void getStatus$annotations() {
        }

        @SerialName("token")
        public static /* synthetic */ void getToken$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getToken() {
            return this.token;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getStatus() {
            return this.status;
        }

        public final OAuthToken copy(@JsonProperty("token") String token, @JsonProperty(NotificationCompat.CATEGORY_STATUS) Integer status) {
            return new OAuthToken(token, status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OAuthToken)) {
                return false;
            }
            OAuthToken oAuthToken = (OAuthToken) other;
            return Intrinsics.areEqual(this.token, oAuthToken.token) && Intrinsics.areEqual(this.status, oAuthToken.status);
        }

        public int hashCode() {
            String str = this.token;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Integer num = this.status;
            return iHashCode + (num != null ? num.hashCode() : 0);
        }

        public String toString() {
            return "OAuthToken(token=" + this.token + ", status=" + this.status + ')';
        }

        /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$OAuthToken$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$OAuthToken;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<OAuthToken> serializer() {
                return OpenSubtitlesApi$OAuthToken$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ OAuthToken(int i, String str, Integer num, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.token = null;
            } else {
                this.token = str;
            }
            if ((i & 2) == 0) {
                this.status = null;
            } else {
                this.status = num;
            }
        }

        public OAuthToken(@JsonProperty("token") String str, @JsonProperty(NotificationCompat.CATEGORY_STATUS) Integer num) {
            this.token = str;
            this.status = num;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(OAuthToken self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.token != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.token);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.status == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.status);
        }

        public /* synthetic */ OAuthToken(String str, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num);
        }

        public final String getToken() {
            return this.token;
        }

        public final void setToken(String str) {
            this.token = str;
        }

        public final Integer getStatus() {
            return this.status;
        }

        public final void setStatus(Integer num) {
            this.status = num;
        }
    }

    /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B\u0019\u0012\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\u0012\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J%\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\b R,\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0006¨\u0006#"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$Results;", "", "data", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultData;", "<init>", "(Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Ljava/util/List;", "setData", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Results {

        @JsonProperty("data")
        private List<ResultData> data;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$Results$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OpenSubtitlesApi.Results._childSerializers$_anonymous_();
            }
        })};

        /* JADX WARN: Multi-variable type inference failed */
        public Results() {
            this((List) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(OpenSubtitlesApi$ResultData$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Results copy$default(Results results, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = results.data;
            }
            return results.copy(list);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        public final List<ResultData> component1() {
            return this.data;
        }

        public final Results copy(@JsonProperty("data") List<ResultData> data) {
            return new Results(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Results) && Intrinsics.areEqual(this.data, ((Results) other).data);
        }

        public int hashCode() {
            List<ResultData> list = this.data;
            if (list == null) {
                return 0;
            }
            return list.hashCode();
        }

        public String toString() {
            return "Results(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$Results$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$Results;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Results> serializer() {
                return OpenSubtitlesApi$Results$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Results(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.data = CollectionsKt.emptyList();
            } else {
                this.data = list;
            }
        }

        public Results(@JsonProperty("data") List<ResultData> list) {
            this.data = list;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Results self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            if (!output.shouldEncodeElementDefault(serialDesc, 0) && Intrinsics.areEqual(self.data, CollectionsKt.emptyList())) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 0, lazyArr[0].getValue(), self.data);
        }

        public /* synthetic */ Results(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CollectionsKt.emptyList() : list);
        }

        public final List<ResultData> getData() {
            return this.data;
        }

        public final void setData(List<ResultData> list) {
            this.data = list;
        }
    }

    /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 .2\u00020\u0001:\u0002-.B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bB9\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0006HÆ\u0003J-\u0010\u001f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020\nHÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0001¢\u0006\u0002\b,R&\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R&\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R&\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006/"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultData;", "", "id", "", "type", "attributes", "Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultAttributes;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultAttributes;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultAttributes;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getType$annotations", "getType", "setType", "getAttributes$annotations", "getAttributes", "()Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultAttributes;", "setAttributes", "(Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultAttributes;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ResultData {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("attributes")
        private ResultAttributes attributes;

        @JsonProperty("id")
        private String id;

        @JsonProperty("type")
        private String type;

        public ResultData() {
            this((String) null, (String) null, (ResultAttributes) null, 7, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ ResultData copy$default(ResultData resultData, String str, String str2, ResultAttributes resultAttributes, int i, Object obj) {
            if ((i & 1) != 0) {
                str = resultData.id;
            }
            if ((i & 2) != 0) {
                str2 = resultData.type;
            }
            if ((i & 4) != 0) {
                resultAttributes = resultData.attributes;
            }
            return resultData.copy(str, str2, resultAttributes);
        }

        @SerialName("attributes")
        public static /* synthetic */ void getAttributes$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final ResultAttributes getAttributes() {
            return this.attributes;
        }

        public final ResultData copy(@JsonProperty("id") String id, @JsonProperty("type") String type, @JsonProperty("attributes") ResultAttributes attributes) {
            return new ResultData(id, type, attributes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResultData)) {
                return false;
            }
            ResultData resultData = (ResultData) other;
            return Intrinsics.areEqual(this.id, resultData.id) && Intrinsics.areEqual(this.type, resultData.type) && Intrinsics.areEqual(this.attributes, resultData.attributes);
        }

        public int hashCode() {
            String str = this.id;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.type;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            ResultAttributes resultAttributes = this.attributes;
            return iHashCode2 + (resultAttributes != null ? resultAttributes.hashCode() : 0);
        }

        public String toString() {
            return "ResultData(id=" + this.id + ", type=" + this.type + ", attributes=" + this.attributes + ')';
        }

        /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultData;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ResultData> serializer() {
                return OpenSubtitlesApi$ResultData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ResultData(int i, String str, String str2, ResultAttributes resultAttributes, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.id = null;
            } else {
                this.id = str;
            }
            if ((i & 2) == 0) {
                this.type = null;
            } else {
                this.type = str2;
            }
            if ((i & 4) != 0) {
                this.attributes = resultAttributes;
                return;
            }
            this.attributes = new ResultAttributes((String) null, (String) null, (String) null, (String) null, (List) null, (ResultFeatureDetails) null, (Boolean) null, 127, (DefaultConstructorMarker) null);
        }

        public ResultData(@JsonProperty("id") String str, @JsonProperty("type") String str2, @JsonProperty("attributes") ResultAttributes resultAttributes) {
            this.id = str;
            this.type = str2;
            this.attributes = resultAttributes;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(ResultData self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.id != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.id);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.type != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.type);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 2)) {
                if (Intrinsics.areEqual(self.attributes, new ResultAttributes((String) null, (String) null, (String) null, (String) null, (List) null, (ResultFeatureDetails) null, (Boolean) null, 127, (DefaultConstructorMarker) null))) {
                    return;
                }
            }
            output.encodeNullableSerializableElement(serialDesc, 2, OpenSubtitlesApi$ResultAttributes$$serializer.INSTANCE, self.attributes);
        }

        public final String getId() {
            return this.id;
        }

        public final void setId(String str) {
            this.id = str;
        }

        public final String getType() {
            return this.type;
        }

        public final void setType(String str) {
            this.type = str;
        }

        public /* synthetic */ ResultData(String str, String str2, ResultAttributes resultAttributes, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? new ResultAttributes((String) null, (String) null, (String) null, (String) null, (List) null, (ResultFeatureDetails) null, (Boolean) null, 127, (DefaultConstructorMarker) null) : resultAttributes);
        }

        public final ResultAttributes getAttributes() {
            return this.attributes;
        }

        public final void setAttributes(ResultAttributes resultAttributes) {
            this.attributes = resultAttributes;
        }
    }

    /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 J2\u00020\u0001:\u0002IJBa\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fBg\b\u0010\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u000e\u0010\u0014J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u00108\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u0010\u0010:\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u00100Jh\u0010;\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001¢\u0006\u0002\u0010<J\u0014\u0010=\u001a\u00020\r2\b\u0010>\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010?\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010@\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u00002\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GH\u0001¢\u0006\u0002\bHR&\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR&\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR&\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001e\u0010\u0016\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR&\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b!\u0010\u0016\u001a\u0004\b\"\u0010\u0018\"\u0004\b#\u0010\u001aR,\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b$\u0010\u0016\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R&\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b)\u0010\u0016\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R(\u0010\f\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u00103\u0012\u0004\b.\u0010\u0016\u001a\u0004\b/\u00100\"\u0004\b1\u00102¨\u0006K"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultAttributes;", "", "subtitleId", "", "language", "release", ImagesContract.URL, "files", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFiles;", "featDetails", "Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFeatureDetails;", "hearingImpaired", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFeatureDetails;Ljava/lang/Boolean;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFeatureDetails;Ljava/lang/Boolean;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSubtitleId$annotations", "()V", "getSubtitleId", "()Ljava/lang/String;", "setSubtitleId", "(Ljava/lang/String;)V", "getLanguage$annotations", "getLanguage", "setLanguage", "getRelease$annotations", "getRelease", "setRelease", "getUrl$annotations", "getUrl", "setUrl", "getFiles$annotations", "getFiles", "()Ljava/util/List;", "setFiles", "(Ljava/util/List;)V", "getFeatDetails$annotations", "getFeatDetails", "()Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFeatureDetails;", "setFeatDetails", "(Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFeatureDetails;)V", "getHearingImpaired$annotations", "getHearingImpaired", "()Ljava/lang/Boolean;", "setHearingImpaired", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFeatureDetails;Ljava/lang/Boolean;)Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultAttributes;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ResultAttributes {

        @JsonProperty("feature_details")
        private ResultFeatureDetails featDetails;

        @JsonProperty("files")
        private List<ResultFiles> files;

        @JsonProperty("hearing_impaired")
        private Boolean hearingImpaired;

        @JsonProperty("language")
        private String language;

        @JsonProperty("release")
        private String release;

        @JsonProperty("subtitle_id")
        private String subtitleId;

        @JsonProperty(ImagesContract.URL)
        private String url;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi$ResultAttributes$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OpenSubtitlesApi.ResultAttributes._childSerializers$_anonymous_();
            }
        }), null, null};

        public ResultAttributes() {
            this((String) null, (String) null, (String) null, (String) null, (List) null, (ResultFeatureDetails) null, (Boolean) null, 127, (DefaultConstructorMarker) null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(OpenSubtitlesApi$ResultFiles$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ResultAttributes copy$default(ResultAttributes resultAttributes, String str, String str2, String str3, String str4, List list, ResultFeatureDetails resultFeatureDetails, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                str = resultAttributes.subtitleId;
            }
            if ((i & 2) != 0) {
                str2 = resultAttributes.language;
            }
            if ((i & 4) != 0) {
                str3 = resultAttributes.release;
            }
            if ((i & 8) != 0) {
                str4 = resultAttributes.url;
            }
            if ((i & 16) != 0) {
                list = resultAttributes.files;
            }
            if ((i & 32) != 0) {
                resultFeatureDetails = resultAttributes.featDetails;
            }
            if ((i & 64) != 0) {
                bool = resultAttributes.hearingImpaired;
            }
            ResultFeatureDetails resultFeatureDetails2 = resultFeatureDetails;
            Boolean bool2 = bool;
            List list2 = list;
            String str5 = str3;
            return resultAttributes.copy(str, str2, str5, str4, list2, resultFeatureDetails2, bool2);
        }

        @SerialName("feature_details")
        public static /* synthetic */ void getFeatDetails$annotations() {
        }

        @SerialName("files")
        public static /* synthetic */ void getFiles$annotations() {
        }

        @SerialName("hearing_impaired")
        public static /* synthetic */ void getHearingImpaired$annotations() {
        }

        @SerialName("language")
        public static /* synthetic */ void getLanguage$annotations() {
        }

        @SerialName("release")
        public static /* synthetic */ void getRelease$annotations() {
        }

        @SerialName("subtitle_id")
        public static /* synthetic */ void getSubtitleId$annotations() {
        }

        @SerialName(ImagesContract.URL)
        public static /* synthetic */ void getUrl$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSubtitleId() {
            return this.subtitleId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getRelease() {
            return this.release;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        public final List<ResultFiles> component5() {
            return this.files;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final ResultFeatureDetails getFeatDetails() {
            return this.featDetails;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Boolean getHearingImpaired() {
            return this.hearingImpaired;
        }

        public final ResultAttributes copy(@JsonProperty("subtitle_id") String subtitleId, @JsonProperty("language") String language, @JsonProperty("release") String release, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("files") List<ResultFiles> files, @JsonProperty("feature_details") ResultFeatureDetails featDetails, @JsonProperty("hearing_impaired") Boolean hearingImpaired) {
            return new ResultAttributes(subtitleId, language, release, url, files, featDetails, hearingImpaired);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResultAttributes)) {
                return false;
            }
            ResultAttributes resultAttributes = (ResultAttributes) other;
            return Intrinsics.areEqual(this.subtitleId, resultAttributes.subtitleId) && Intrinsics.areEqual(this.language, resultAttributes.language) && Intrinsics.areEqual(this.release, resultAttributes.release) && Intrinsics.areEqual(this.url, resultAttributes.url) && Intrinsics.areEqual(this.files, resultAttributes.files) && Intrinsics.areEqual(this.featDetails, resultAttributes.featDetails) && Intrinsics.areEqual(this.hearingImpaired, resultAttributes.hearingImpaired);
        }

        public int hashCode() {
            String str = this.subtitleId;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.language;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.release;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.url;
            int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            List<ResultFiles> list = this.files;
            int iHashCode5 = (iHashCode4 + (list == null ? 0 : list.hashCode())) * 31;
            ResultFeatureDetails resultFeatureDetails = this.featDetails;
            int iHashCode6 = (iHashCode5 + (resultFeatureDetails == null ? 0 : resultFeatureDetails.hashCode())) * 31;
            Boolean bool = this.hearingImpaired;
            return iHashCode6 + (bool != null ? bool.hashCode() : 0);
        }

        public String toString() {
            return "ResultAttributes(subtitleId=" + this.subtitleId + ", language=" + this.language + ", release=" + this.release + ", url=" + this.url + ", files=" + this.files + ", featDetails=" + this.featDetails + ", hearingImpaired=" + this.hearingImpaired + ')';
        }

        /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultAttributes$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultAttributes;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ResultAttributes> serializer() {
                return OpenSubtitlesApi$ResultAttributes$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ResultAttributes(int i, String str, String str2, String str3, String str4, List list, ResultFeatureDetails resultFeatureDetails, Boolean bool, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.subtitleId = null;
            } else {
                this.subtitleId = str;
            }
            if ((i & 2) == 0) {
                this.language = null;
            } else {
                this.language = str2;
            }
            if ((i & 4) == 0) {
                this.release = null;
            } else {
                this.release = str3;
            }
            if ((i & 8) == 0) {
                this.url = null;
            } else {
                this.url = str4;
            }
            this.files = (i & 16) == 0 ? CollectionsKt.emptyList() : list;
            if ((i & 32) == 0) {
                this.featDetails = new ResultFeatureDetails((Integer) null, (String) null, (String) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, (String) null, (Integer) null, (Integer) null, 2047, (DefaultConstructorMarker) null);
            } else {
                this.featDetails = resultFeatureDetails;
            }
            if ((i & 64) == 0) {
                this.hearingImpaired = null;
            } else {
                this.hearingImpaired = bool;
            }
        }

        public ResultAttributes(@JsonProperty("subtitle_id") String str, @JsonProperty("language") String str2, @JsonProperty("release") String str3, @JsonProperty(ImagesContract.URL) String str4, @JsonProperty("files") List<ResultFiles> list, @JsonProperty("feature_details") ResultFeatureDetails resultFeatureDetails, @JsonProperty("hearing_impaired") Boolean bool) {
            this.subtitleId = str;
            this.language = str2;
            this.release = str3;
            this.url = str4;
            this.files = list;
            this.featDetails = resultFeatureDetails;
            this.hearingImpaired = bool;
        }

        /* JADX WARN: Code duplicated, block: B:37:0x00a2  */
        @JvmStatic
        public static final /* synthetic */ void write$Self$app(ResultAttributes self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.subtitleId != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.subtitleId);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.language != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.language);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.release != null) {
                output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.release);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || self.url != null) {
                output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.url);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.files, CollectionsKt.emptyList())) {
                output.encodeNullableSerializableElement(serialDesc, 4, lazyArr[4].getValue(), self.files);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5)) {
                output.encodeNullableSerializableElement(serialDesc, 5, OpenSubtitlesApi$ResultFeatureDetails$$serializer.INSTANCE, self.featDetails);
            } else {
                if (!Intrinsics.areEqual(self.featDetails, new ResultFeatureDetails((Integer) null, (String) null, (String) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, (String) null, (Integer) null, (Integer) null, 2047, (DefaultConstructorMarker) null))) {
                    output.encodeNullableSerializableElement(serialDesc, 5, OpenSubtitlesApi$ResultFeatureDetails$$serializer.INSTANCE, self.featDetails);
                }
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 6) && self.hearingImpaired == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 6, BooleanSerializer.INSTANCE, self.hearingImpaired);
        }

        public final String getSubtitleId() {
            return this.subtitleId;
        }

        public final void setSubtitleId(String str) {
            this.subtitleId = str;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final void setLanguage(String str) {
            this.language = str;
        }

        public final String getRelease() {
            return this.release;
        }

        public final void setRelease(String str) {
            this.release = str;
        }

        public final String getUrl() {
            return this.url;
        }

        public final void setUrl(String str) {
            this.url = str;
        }

        public /* synthetic */ ResultAttributes(String str, String str2, String str3, String str4, List list, ResultFeatureDetails resultFeatureDetails, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? new ResultFeatureDetails((Integer) null, (String) null, (String) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, (String) null, (Integer) null, (Integer) null, 2047, (DefaultConstructorMarker) null) : resultFeatureDetails, (i & 64) != 0 ? null : bool);
        }

        public final List<ResultFiles> getFiles() {
            return this.files;
        }

        public final void setFiles(List<ResultFiles> list) {
            this.files = list;
        }

        public final ResultFeatureDetails getFeatDetails() {
            return this.featDetails;
        }

        public final void setFeatDetails(ResultFeatureDetails resultFeatureDetails) {
            this.featDetails = resultFeatureDetails;
        }

        public final Boolean getHearingImpaired() {
            return this.hearingImpaired;
        }

        public final void setHearingImpaired(Boolean bool) {
            this.hearingImpaired = bool;
        }
    }

    /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 *2\u00020\u0001:\u0002)*B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u001a\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0005HÖ\u0081\u0004J%\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0001¢\u0006\u0002\b(R(\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u0012\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R&\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0013\u0010\r\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006+"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFiles;", "", "fileId", "", "fileName", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFileId$annotations", "()V", "getFileId", "()Ljava/lang/Integer;", "setFileId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getFileName$annotations", "getFileName", "()Ljava/lang/String;", "setFileName", "(Ljava/lang/String;)V", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/String;)Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFiles;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ResultFiles {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty(FontsContractCompat.Columns.FILE_ID)
        private Integer fileId;

        @JsonProperty("file_name")
        private String fileName;

        /* JADX WARN: Multi-variable type inference failed */
        public ResultFiles() {
            this((Integer) null, (String) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ ResultFiles copy$default(ResultFiles resultFiles, Integer num, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                num = resultFiles.fileId;
            }
            if ((i & 2) != 0) {
                str = resultFiles.fileName;
            }
            return resultFiles.copy(num, str);
        }

        @SerialName(FontsContractCompat.Columns.FILE_ID)
        public static /* synthetic */ void getFileId$annotations() {
        }

        @SerialName("file_name")
        public static /* synthetic */ void getFileName$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getFileId() {
            return this.fileId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFileName() {
            return this.fileName;
        }

        public final ResultFiles copy(@JsonProperty(FontsContractCompat.Columns.FILE_ID) Integer fileId, @JsonProperty("file_name") String fileName) {
            return new ResultFiles(fileId, fileName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResultFiles)) {
                return false;
            }
            ResultFiles resultFiles = (ResultFiles) other;
            return Intrinsics.areEqual(this.fileId, resultFiles.fileId) && Intrinsics.areEqual(this.fileName, resultFiles.fileName);
        }

        public int hashCode() {
            Integer num = this.fileId;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.fileName;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "ResultFiles(fileId=" + this.fileId + ", fileName=" + this.fileName + ')';
        }

        /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFiles$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFiles;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ResultFiles> serializer() {
                return OpenSubtitlesApi$ResultFiles$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ResultFiles(int i, Integer num, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.fileId = null;
            } else {
                this.fileId = num;
            }
            if ((i & 2) == 0) {
                this.fileName = null;
            } else {
                this.fileName = str;
            }
        }

        public ResultFiles(@JsonProperty(FontsContractCompat.Columns.FILE_ID) Integer num, @JsonProperty("file_name") String str) {
            this.fileId = num;
            this.fileName = str;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(ResultFiles self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.fileId != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.fileId);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.fileName == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.fileName);
        }

        public /* synthetic */ ResultFiles(Integer num, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str);
        }

        public final Integer getFileId() {
            return this.fileId;
        }

        public final void setFileId(Integer num) {
            this.fileId = num;
        }

        public final String getFileName() {
            return this.fileName;
        }

        public final void setFileName(String str) {
            this.fileName = str;
        }
    }

    /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 C2\u00020\u0001:\u0002BCB[\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fBa\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u000b\u0010\u0010J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010/\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jb\u00103\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u00104J\u0014\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00108\u001a\u00020\u0006HÖ\u0081\u0004J\n\u00109\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@H\u0001¢\u0006\u0002\bAR&\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R&\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R(\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001f\u0012\u0004\b\u001a\u0010\u0012\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR(\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001f\u0012\u0004\b \u0010\u0012\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001eR&\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b#\u0010\u0012\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u0016R&\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b&\u0010\u0012\u001a\u0004\b'\u0010\u0014\"\u0004\b(\u0010\u0016R&\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b)\u0010\u0012\u001a\u0004\b*\u0010\u0014\"\u0004\b+\u0010\u0016¨\u0006D"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultDownloadLink;", "", "link", "", "fileName", "requests", "", "remaining", ContentType.Message.TYPE, "resetTime", "resetTimeUtc", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getLink$annotations", "()V", "getLink", "()Ljava/lang/String;", "setLink", "(Ljava/lang/String;)V", "getFileName$annotations", "getFileName", "setFileName", "getRequests$annotations", "getRequests", "()Ljava/lang/Integer;", "setRequests", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getRemaining$annotations", "getRemaining", "setRemaining", "getMessage$annotations", "getMessage", "setMessage", "getResetTime$annotations", "getResetTime", "setResetTime", "getResetTimeUtc$annotations", "getResetTimeUtc", "setResetTimeUtc", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultDownloadLink;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ResultDownloadLink {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("file_name")
        private String fileName;

        @JsonProperty("link")
        private String link;

        @JsonProperty(ContentType.Message.TYPE)
        private String message;

        @JsonProperty("remaining")
        private Integer remaining;

        @JsonProperty("requests")
        private Integer requests;

        @JsonProperty("reset_time")
        private String resetTime;

        @JsonProperty("reset_time_utc")
        private String resetTimeUtc;

        public ResultDownloadLink() {
            this((String) null, (String) null, (Integer) null, (Integer) null, (String) null, (String) null, (String) null, 127, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ ResultDownloadLink copy$default(ResultDownloadLink resultDownloadLink, String str, String str2, Integer num, Integer num2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = resultDownloadLink.link;
            }
            if ((i & 2) != 0) {
                str2 = resultDownloadLink.fileName;
            }
            if ((i & 4) != 0) {
                num = resultDownloadLink.requests;
            }
            if ((i & 8) != 0) {
                num2 = resultDownloadLink.remaining;
            }
            if ((i & 16) != 0) {
                str3 = resultDownloadLink.message;
            }
            if ((i & 32) != 0) {
                str4 = resultDownloadLink.resetTime;
            }
            if ((i & 64) != 0) {
                str5 = resultDownloadLink.resetTimeUtc;
            }
            String str6 = str4;
            String str7 = str5;
            String str8 = str3;
            Integer num3 = num;
            return resultDownloadLink.copy(str, str2, num3, num2, str8, str6, str7);
        }

        @SerialName("file_name")
        public static /* synthetic */ void getFileName$annotations() {
        }

        @SerialName("link")
        public static /* synthetic */ void getLink$annotations() {
        }

        @SerialName(ContentType.Message.TYPE)
        public static /* synthetic */ void getMessage$annotations() {
        }

        @SerialName("remaining")
        public static /* synthetic */ void getRemaining$annotations() {
        }

        @SerialName("requests")
        public static /* synthetic */ void getRequests$annotations() {
        }

        @SerialName("reset_time")
        public static /* synthetic */ void getResetTime$annotations() {
        }

        @SerialName("reset_time_utc")
        public static /* synthetic */ void getResetTimeUtc$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLink() {
            return this.link;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFileName() {
            return this.fileName;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getRequests() {
            return this.requests;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getRemaining() {
            return this.remaining;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getResetTime() {
            return this.resetTime;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getResetTimeUtc() {
            return this.resetTimeUtc;
        }

        public final ResultDownloadLink copy(@JsonProperty("link") String link, @JsonProperty("file_name") String fileName, @JsonProperty("requests") Integer requests, @JsonProperty("remaining") Integer remaining, @JsonProperty(ContentType.Message.TYPE) String message, @JsonProperty("reset_time") String resetTime, @JsonProperty("reset_time_utc") String resetTimeUtc) {
            return new ResultDownloadLink(link, fileName, requests, remaining, message, resetTime, resetTimeUtc);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResultDownloadLink)) {
                return false;
            }
            ResultDownloadLink resultDownloadLink = (ResultDownloadLink) other;
            return Intrinsics.areEqual(this.link, resultDownloadLink.link) && Intrinsics.areEqual(this.fileName, resultDownloadLink.fileName) && Intrinsics.areEqual(this.requests, resultDownloadLink.requests) && Intrinsics.areEqual(this.remaining, resultDownloadLink.remaining) && Intrinsics.areEqual(this.message, resultDownloadLink.message) && Intrinsics.areEqual(this.resetTime, resultDownloadLink.resetTime) && Intrinsics.areEqual(this.resetTimeUtc, resultDownloadLink.resetTimeUtc);
        }

        public int hashCode() {
            String str = this.link;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.fileName;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            Integer num = this.requests;
            int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.remaining;
            int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str3 = this.message;
            int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.resetTime;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.resetTimeUtc;
            return iHashCode6 + (str5 != null ? str5.hashCode() : 0);
        }

        public String toString() {
            return "ResultDownloadLink(link=" + this.link + ", fileName=" + this.fileName + ", requests=" + this.requests + ", remaining=" + this.remaining + ", message=" + this.message + ", resetTime=" + this.resetTime + ", resetTimeUtc=" + this.resetTimeUtc + ')';
        }

        /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultDownloadLink$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultDownloadLink;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ResultDownloadLink> serializer() {
                return OpenSubtitlesApi$ResultDownloadLink$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ResultDownloadLink(int i, String str, String str2, Integer num, Integer num2, String str3, String str4, String str5, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.link = null;
            } else {
                this.link = str;
            }
            if ((i & 2) == 0) {
                this.fileName = null;
            } else {
                this.fileName = str2;
            }
            if ((i & 4) == 0) {
                this.requests = null;
            } else {
                this.requests = num;
            }
            if ((i & 8) == 0) {
                this.remaining = null;
            } else {
                this.remaining = num2;
            }
            if ((i & 16) == 0) {
                this.message = null;
            } else {
                this.message = str3;
            }
            if ((i & 32) == 0) {
                this.resetTime = null;
            } else {
                this.resetTime = str4;
            }
            if ((i & 64) == 0) {
                this.resetTimeUtc = null;
            } else {
                this.resetTimeUtc = str5;
            }
        }

        public ResultDownloadLink(@JsonProperty("link") String str, @JsonProperty("file_name") String str2, @JsonProperty("requests") Integer num, @JsonProperty("remaining") Integer num2, @JsonProperty(ContentType.Message.TYPE) String str3, @JsonProperty("reset_time") String str4, @JsonProperty("reset_time_utc") String str5) {
            this.link = str;
            this.fileName = str2;
            this.requests = num;
            this.remaining = num2;
            this.message = str3;
            this.resetTime = str4;
            this.resetTimeUtc = str5;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(ResultDownloadLink self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.link != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.link);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.fileName != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.fileName);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.requests != null) {
                output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.requests);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || self.remaining != null) {
                output.encodeNullableSerializableElement(serialDesc, 3, IntSerializer.INSTANCE, self.remaining);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || self.message != null) {
                output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.message);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || self.resetTime != null) {
                output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.resetTime);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 6) && self.resetTimeUtc == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.resetTimeUtc);
        }

        public /* synthetic */ ResultDownloadLink(String str, String str2, Integer num, Integer num2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5);
        }

        public final String getLink() {
            return this.link;
        }

        public final void setLink(String str) {
            this.link = str;
        }

        public final String getFileName() {
            return this.fileName;
        }

        public final void setFileName(String str) {
            this.fileName = str;
        }

        public final Integer getRequests() {
            return this.requests;
        }

        public final void setRequests(Integer num) {
            this.requests = num;
        }

        public final Integer getRemaining() {
            return this.remaining;
        }

        public final void setRemaining(Integer num) {
            this.remaining = num;
        }

        public final String getMessage() {
            return this.message;
        }

        public final void setMessage(String str) {
            this.message = str;
        }

        public final String getResetTime() {
            return this.resetTime;
        }

        public final void setResetTime(String str) {
            this.resetTime = str;
        }

        public final String getResetTimeUtc() {
            return this.resetTimeUtc;
        }

        public final void setResetTimeUtc(String str) {
            this.resetTimeUtc = str;
        }
    }

    /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b6\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 W2\u00020\u0001:\u0002VWB\u008b\u0001\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000f\u0010\u0010B\u0089\u0001\b\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u000f\u0010\u0014J\u0010\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010=\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010D\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0092\u0001\u0010G\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010HJ\u0014\u0010I\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010L\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010M\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020TH\u0001¢\u0006\u0002\bUR(\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001b\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR&\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001c\u0010\u0016\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R&\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b!\u0010\u0016\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R(\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001b\u0012\u0004\b$\u0010\u0016\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR(\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001b\u0012\u0004\b'\u0010\u0016\u001a\u0004\b(\u0010\u0018\"\u0004\b)\u0010\u001aR(\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001b\u0012\u0004\b*\u0010\u0016\u001a\u0004\b+\u0010\u0018\"\u0004\b,\u0010\u001aR(\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001b\u0012\u0004\b-\u0010\u0016\u001a\u0004\b.\u0010\u0018\"\u0004\b/\u0010\u001aR(\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001b\u0012\u0004\b0\u0010\u0016\u001a\u0004\b1\u0010\u0018\"\u0004\b2\u0010\u001aR&\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b3\u0010\u0016\u001a\u0004\b4\u0010\u001e\"\u0004\b5\u0010 R(\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001b\u0012\u0004\b6\u0010\u0016\u001a\u0004\b7\u0010\u0018\"\u0004\b8\u0010\u001aR(\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001b\u0012\u0004\b9\u0010\u0016\u001a\u0004\b:\u0010\u0018\"\u0004\b;\u0010\u001a¨\u0006X"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFeatureDetails;", "", "year", "", "title", "", "movieName", "imdbId", "tmdbId", "seasonNumber", "episodeNumber", "parentImdbId", "parentTitle", "parentTmdbId", "parentFeatureId", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getYear$annotations", "()V", "getYear", "()Ljava/lang/Integer;", "setYear", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getTitle$annotations", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "getMovieName$annotations", "getMovieName", "setMovieName", "getImdbId$annotations", "getImdbId", "setImdbId", "getTmdbId$annotations", "getTmdbId", "setTmdbId", "getSeasonNumber$annotations", "getSeasonNumber", "setSeasonNumber", "getEpisodeNumber$annotations", "getEpisodeNumber", "setEpisodeNumber", "getParentImdbId$annotations", "getParentImdbId", "setParentImdbId", "getParentTitle$annotations", "getParentTitle", "setParentTitle", "getParentTmdbId$annotations", "getParentTmdbId", "setParentTmdbId", "getParentFeatureId$annotations", "getParentFeatureId", "setParentFeatureId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFeatureDetails;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ResultFeatureDetails {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER)
        private Integer episodeNumber;

        @JsonProperty("imdb_id")
        private Integer imdbId;

        @JsonProperty("movie_name")
        private String movieName;

        @JsonProperty("parent_feature_id")
        private Integer parentFeatureId;

        @JsonProperty("parent_imdb_id")
        private Integer parentImdbId;

        @JsonProperty("parent_title")
        private String parentTitle;

        @JsonProperty("parent_tmdb_id")
        private Integer parentTmdbId;

        @JsonProperty(TvContractCompat.Programs.COLUMN_SEASON_NUMBER)
        private Integer seasonNumber;

        @JsonProperty("title")
        private String title;

        @JsonProperty("tmdb_id")
        private Integer tmdbId;

        @JsonProperty("year")
        private Integer year;

        public ResultFeatureDetails() {
            this((Integer) null, (String) null, (String) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, (String) null, (Integer) null, (Integer) null, 2047, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ ResultFeatureDetails copy$default(ResultFeatureDetails resultFeatureDetails, Integer num, String str, String str2, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, String str3, Integer num7, Integer num8, int i, Object obj) {
            if ((i & 1) != 0) {
                num = resultFeatureDetails.year;
            }
            if ((i & 2) != 0) {
                str = resultFeatureDetails.title;
            }
            if ((i & 4) != 0) {
                str2 = resultFeatureDetails.movieName;
            }
            if ((i & 8) != 0) {
                num2 = resultFeatureDetails.imdbId;
            }
            if ((i & 16) != 0) {
                num3 = resultFeatureDetails.tmdbId;
            }
            if ((i & 32) != 0) {
                num4 = resultFeatureDetails.seasonNumber;
            }
            if ((i & 64) != 0) {
                num5 = resultFeatureDetails.episodeNumber;
            }
            if ((i & 128) != 0) {
                num6 = resultFeatureDetails.parentImdbId;
            }
            if ((i & 256) != 0) {
                str3 = resultFeatureDetails.parentTitle;
            }
            if ((i & 512) != 0) {
                num7 = resultFeatureDetails.parentTmdbId;
            }
            if ((i & 1024) != 0) {
                num8 = resultFeatureDetails.parentFeatureId;
            }
            Integer num9 = num7;
            Integer num10 = num8;
            Integer num11 = num6;
            String str4 = str3;
            Integer num12 = num4;
            Integer num13 = num5;
            Integer num14 = num3;
            String str5 = str2;
            return resultFeatureDetails.copy(num, str, str5, num2, num14, num12, num13, num11, str4, num9, num10);
        }

        @SerialName(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER)
        public static /* synthetic */ void getEpisodeNumber$annotations() {
        }

        @SerialName("imdb_id")
        public static /* synthetic */ void getImdbId$annotations() {
        }

        @SerialName("movie_name")
        public static /* synthetic */ void getMovieName$annotations() {
        }

        @SerialName("parent_feature_id")
        public static /* synthetic */ void getParentFeatureId$annotations() {
        }

        @SerialName("parent_imdb_id")
        public static /* synthetic */ void getParentImdbId$annotations() {
        }

        @SerialName("parent_title")
        public static /* synthetic */ void getParentTitle$annotations() {
        }

        @SerialName("parent_tmdb_id")
        public static /* synthetic */ void getParentTmdbId$annotations() {
        }

        @SerialName(TvContractCompat.Programs.COLUMN_SEASON_NUMBER)
        public static /* synthetic */ void getSeasonNumber$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        @SerialName("tmdb_id")
        public static /* synthetic */ void getTmdbId$annotations() {
        }

        @SerialName("year")
        public static /* synthetic */ void getYear$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getParentTmdbId() {
            return this.parentTmdbId;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Integer getParentFeatureId() {
            return this.parentFeatureId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getMovieName() {
            return this.movieName;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getImdbId() {
            return this.imdbId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getTmdbId() {
            return this.tmdbId;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getSeasonNumber() {
            return this.seasonNumber;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getEpisodeNumber() {
            return this.episodeNumber;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getParentImdbId() {
            return this.parentImdbId;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getParentTitle() {
            return this.parentTitle;
        }

        public final ResultFeatureDetails copy(@JsonProperty("year") Integer year, @JsonProperty("title") String title, @JsonProperty("movie_name") String movieName, @JsonProperty("imdb_id") Integer imdbId, @JsonProperty("tmdb_id") Integer tmdbId, @JsonProperty(TvContractCompat.Programs.COLUMN_SEASON_NUMBER) Integer seasonNumber, @JsonProperty(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER) Integer episodeNumber, @JsonProperty("parent_imdb_id") Integer parentImdbId, @JsonProperty("parent_title") String parentTitle, @JsonProperty("parent_tmdb_id") Integer parentTmdbId, @JsonProperty("parent_feature_id") Integer parentFeatureId) {
            return new ResultFeatureDetails(year, title, movieName, imdbId, tmdbId, seasonNumber, episodeNumber, parentImdbId, parentTitle, parentTmdbId, parentFeatureId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResultFeatureDetails)) {
                return false;
            }
            ResultFeatureDetails resultFeatureDetails = (ResultFeatureDetails) other;
            return Intrinsics.areEqual(this.year, resultFeatureDetails.year) && Intrinsics.areEqual(this.title, resultFeatureDetails.title) && Intrinsics.areEqual(this.movieName, resultFeatureDetails.movieName) && Intrinsics.areEqual(this.imdbId, resultFeatureDetails.imdbId) && Intrinsics.areEqual(this.tmdbId, resultFeatureDetails.tmdbId) && Intrinsics.areEqual(this.seasonNumber, resultFeatureDetails.seasonNumber) && Intrinsics.areEqual(this.episodeNumber, resultFeatureDetails.episodeNumber) && Intrinsics.areEqual(this.parentImdbId, resultFeatureDetails.parentImdbId) && Intrinsics.areEqual(this.parentTitle, resultFeatureDetails.parentTitle) && Intrinsics.areEqual(this.parentTmdbId, resultFeatureDetails.parentTmdbId) && Intrinsics.areEqual(this.parentFeatureId, resultFeatureDetails.parentFeatureId);
        }

        public int hashCode() {
            Integer num = this.year;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.title;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.movieName;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Integer num2 = this.imdbId;
            int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.tmdbId;
            int iHashCode5 = (iHashCode4 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Integer num4 = this.seasonNumber;
            int iHashCode6 = (iHashCode5 + (num4 == null ? 0 : num4.hashCode())) * 31;
            Integer num5 = this.episodeNumber;
            int iHashCode7 = (iHashCode6 + (num5 == null ? 0 : num5.hashCode())) * 31;
            Integer num6 = this.parentImdbId;
            int iHashCode8 = (iHashCode7 + (num6 == null ? 0 : num6.hashCode())) * 31;
            String str3 = this.parentTitle;
            int iHashCode9 = (iHashCode8 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Integer num7 = this.parentTmdbId;
            int iHashCode10 = (iHashCode9 + (num7 == null ? 0 : num7.hashCode())) * 31;
            Integer num8 = this.parentFeatureId;
            return iHashCode10 + (num8 != null ? num8.hashCode() : 0);
        }

        public String toString() {
            return "ResultFeatureDetails(year=" + this.year + ", title=" + this.title + ", movieName=" + this.movieName + ", imdbId=" + this.imdbId + ", tmdbId=" + this.tmdbId + ", seasonNumber=" + this.seasonNumber + ", episodeNumber=" + this.episodeNumber + ", parentImdbId=" + this.parentImdbId + ", parentTitle=" + this.parentTitle + ", parentTmdbId=" + this.parentTmdbId + ", parentFeatureId=" + this.parentFeatureId + ')';
        }

        /* JADX INFO: compiled from: OpenSubtitlesApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFeatureDetails$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi$ResultFeatureDetails;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ResultFeatureDetails> serializer() {
                return OpenSubtitlesApi$ResultFeatureDetails$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ResultFeatureDetails(int i, Integer num, String str, String str2, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, String str3, Integer num7, Integer num8, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.year = null;
            } else {
                this.year = num;
            }
            if ((i & 2) == 0) {
                this.title = null;
            } else {
                this.title = str;
            }
            if ((i & 4) == 0) {
                this.movieName = null;
            } else {
                this.movieName = str2;
            }
            if ((i & 8) == 0) {
                this.imdbId = null;
            } else {
                this.imdbId = num2;
            }
            if ((i & 16) == 0) {
                this.tmdbId = null;
            } else {
                this.tmdbId = num3;
            }
            if ((i & 32) == 0) {
                this.seasonNumber = null;
            } else {
                this.seasonNumber = num4;
            }
            if ((i & 64) == 0) {
                this.episodeNumber = null;
            } else {
                this.episodeNumber = num5;
            }
            if ((i & 128) == 0) {
                this.parentImdbId = null;
            } else {
                this.parentImdbId = num6;
            }
            if ((i & 256) == 0) {
                this.parentTitle = null;
            } else {
                this.parentTitle = str3;
            }
            if ((i & 512) == 0) {
                this.parentTmdbId = null;
            } else {
                this.parentTmdbId = num7;
            }
            if ((i & 1024) == 0) {
                this.parentFeatureId = null;
            } else {
                this.parentFeatureId = num8;
            }
        }

        public ResultFeatureDetails(@JsonProperty("year") Integer num, @JsonProperty("title") String str, @JsonProperty("movie_name") String str2, @JsonProperty("imdb_id") Integer num2, @JsonProperty("tmdb_id") Integer num3, @JsonProperty(TvContractCompat.Programs.COLUMN_SEASON_NUMBER) Integer num4, @JsonProperty(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER) Integer num5, @JsonProperty("parent_imdb_id") Integer num6, @JsonProperty("parent_title") String str3, @JsonProperty("parent_tmdb_id") Integer num7, @JsonProperty("parent_feature_id") Integer num8) {
            this.year = num;
            this.title = str;
            this.movieName = str2;
            this.imdbId = num2;
            this.tmdbId = num3;
            this.seasonNumber = num4;
            this.episodeNumber = num5;
            this.parentImdbId = num6;
            this.parentTitle = str3;
            this.parentTmdbId = num7;
            this.parentFeatureId = num8;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(ResultFeatureDetails self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.year != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.year);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.title != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.title);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.movieName != null) {
                output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.movieName);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || self.imdbId != null) {
                output.encodeNullableSerializableElement(serialDesc, 3, IntSerializer.INSTANCE, self.imdbId);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || self.tmdbId != null) {
                output.encodeNullableSerializableElement(serialDesc, 4, IntSerializer.INSTANCE, self.tmdbId);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || self.seasonNumber != null) {
                output.encodeNullableSerializableElement(serialDesc, 5, IntSerializer.INSTANCE, self.seasonNumber);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 6) || self.episodeNumber != null) {
                output.encodeNullableSerializableElement(serialDesc, 6, IntSerializer.INSTANCE, self.episodeNumber);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 7) || self.parentImdbId != null) {
                output.encodeNullableSerializableElement(serialDesc, 7, IntSerializer.INSTANCE, self.parentImdbId);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 8) || self.parentTitle != null) {
                output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.parentTitle);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 9) || self.parentTmdbId != null) {
                output.encodeNullableSerializableElement(serialDesc, 9, IntSerializer.INSTANCE, self.parentTmdbId);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 10) && self.parentFeatureId == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 10, IntSerializer.INSTANCE, self.parentFeatureId);
        }

        public /* synthetic */ ResultFeatureDetails(Integer num, String str, String str2, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, String str3, Integer num7, Integer num8, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : num3, (i & 32) != 0 ? null : num4, (i & 64) != 0 ? null : num5, (i & 128) != 0 ? null : num6, (i & 256) != 0 ? null : str3, (i & 512) != 0 ? null : num7, (i & 1024) != 0 ? null : num8);
        }

        public final Integer getYear() {
            return this.year;
        }

        public final void setYear(Integer num) {
            this.year = num;
        }

        public final String getTitle() {
            return this.title;
        }

        public final void setTitle(String str) {
            this.title = str;
        }

        public final String getMovieName() {
            return this.movieName;
        }

        public final void setMovieName(String str) {
            this.movieName = str;
        }

        public final Integer getImdbId() {
            return this.imdbId;
        }

        public final void setImdbId(Integer num) {
            this.imdbId = num;
        }

        public final Integer getTmdbId() {
            return this.tmdbId;
        }

        public final void setTmdbId(Integer num) {
            this.tmdbId = num;
        }

        public final Integer getSeasonNumber() {
            return this.seasonNumber;
        }

        public final void setSeasonNumber(Integer num) {
            this.seasonNumber = num;
        }

        public final Integer getEpisodeNumber() {
            return this.episodeNumber;
        }

        public final void setEpisodeNumber(Integer num) {
            this.episodeNumber = num;
        }

        public final Integer getParentImdbId() {
            return this.parentImdbId;
        }

        public final void setParentImdbId(Integer num) {
            this.parentImdbId = num;
        }

        public final String getParentTitle() {
            return this.parentTitle;
        }

        public final void setParentTitle(String str) {
            this.parentTitle = str;
        }

        public final Integer getParentTmdbId() {
            return this.parentTmdbId;
        }

        public final void setParentTmdbId(Integer num) {
            this.parentTmdbId = num;
        }

        public final Integer getParentFeatureId() {
            return this.parentFeatureId;
        }

        public final void setParentFeatureId(Integer num) {
            this.parentFeatureId = num;
        }
    }
}
