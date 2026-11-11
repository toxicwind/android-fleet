package com.lagradost.cloudstream3.utils.videoskip;

import androidx.autofill.HintConstants;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.actions.SearchIntents;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.syncproviders.AuthAPI;
import com.lagradost.cloudstream3.syncproviders.AuthLoginRequirement;
import com.lagradost.cloudstream3.syncproviders.AuthLoginResponse;
import com.lagradost.cloudstream3.syncproviders.AuthToken;
import com.lagradost.cloudstream3.syncproviders.AuthUser;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: AnimeSkip.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\b!\"#$%&'(B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0096@¢\u0006\u0002\u0010\u001cJ\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0096@¢\u0006\u0002\u0010 R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0014\u0010\u0014\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007¨\u0006)"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth;", "Lcom/lagradost/cloudstream3/syncproviders/AuthAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "inAppLoginRequirement", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginRequirement;", "getInAppLoginRequirement", "()Lcom/lagradost/cloudstream3/syncproviders/AuthLoginRequirement;", "idPrefix", "getIdPrefix", "hasInApp", "", "getHasInApp", "()Z", "createAccountUrl", "getCreateAccountUrl", "baseClientId", "getBaseClientId", "md5", TvContractCompat.PARAM_INPUT, "user", "Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "token", "Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "form", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "LoginRoot", "LoginData", "Login", "ApiRoot", "ApiData", "MyApiClient", "Account", "Payload", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AnimeSkipAuth extends AuthAPI {
    private final String name = "AnimeSkip";
    private final AuthLoginRequirement inAppLoginRequirement = new AuthLoginRequirement(true, true, false, false, 12, null);
    private final String idPrefix = "anime-skip";
    private final boolean hasInApp = true;
    private final String createAccountUrl = "https://anime-skip.com/account";
    private final String baseClientId = "as1JgiMbW4wKfmTLWXS79iTDQFll76pk";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.videoskip.AnimeSkipAuth$login$1, reason: invalid class name */
    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.videoskip.AnimeSkipAuth", f = "AnimeSkip.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {112, 134}, m = "login", n = {"form", "hash", "emailOrUserName", "loginQuery", "form", "hash", "emailOrUserName", "loginQuery", "loginRoot", "authToken", "refreshToken", "account", "clientQuery"}, nl = {120, 143}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeSkipAuth.this.login((AuthLoginResponse) null, this);
        }
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public AuthLoginRequirement getInAppLoginRequirement() {
        return this.inAppLoginRequirement;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getIdPrefix() {
        return this.idPrefix;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public boolean getHasInApp() {
        return this.hasInApp;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getCreateAccountUrl() {
        return this.createAccountUrl;
    }

    public final String getBaseClientId() {
        return this.baseClientId;
    }

    public final String md5(String input) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(input, "input");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = input.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String string = new BigInteger(1, messageDigest.digest(bytes)).toString(16);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return StringsKt.padStart(string, 32, '0');
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$LoginRoot;", "", "data", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$LoginData;", "<init>", "(Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$LoginData;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$LoginData;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$LoginData;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class LoginRoot {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("data")
        private final LoginData data;

        public static /* synthetic */ LoginRoot copy$default(LoginRoot loginRoot, LoginData loginData, int i, Object obj) {
            if ((i & 1) != 0) {
                loginData = loginRoot.data;
            }
            return loginRoot.copy(loginData);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final LoginData getData() {
            return this.data;
        }

        public final LoginRoot copy(@JsonProperty("data") LoginData data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new LoginRoot(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof LoginRoot) && Intrinsics.areEqual(this.data, ((LoginRoot) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "LoginRoot(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$LoginRoot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$LoginRoot;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<LoginRoot> serializer() {
                return AnimeSkipAuth$LoginRoot$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ LoginRoot(int i, LoginData loginData, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AnimeSkipAuth$LoginRoot$$serializer.INSTANCE.getDescriptor());
            }
            this.data = loginData;
        }

        public LoginRoot(@JsonProperty("data") LoginData data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final LoginData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$LoginData;", "", "login", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Login;", "<init>", "(Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Login;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Login;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getLogin$annotations", "()V", "getLogin", "()Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Login;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class LoginData {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("login")
        private final Login login;

        public static /* synthetic */ LoginData copy$default(LoginData loginData, Login login, int i, Object obj) {
            if ((i & 1) != 0) {
                login = loginData.login;
            }
            return loginData.copy(login);
        }

        @SerialName("login")
        public static /* synthetic */ void getLogin$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Login getLogin() {
            return this.login;
        }

        public final LoginData copy(@JsonProperty("login") Login login) {
            Intrinsics.checkNotNullParameter(login, "login");
            return new LoginData(login);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof LoginData) && Intrinsics.areEqual(this.login, ((LoginData) other).login);
        }

        public int hashCode() {
            return this.login.hashCode();
        }

        public String toString() {
            return "LoginData(login=" + this.login + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$LoginData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$LoginData;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<LoginData> serializer() {
                return AnimeSkipAuth$LoginData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ LoginData(int i, Login login, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AnimeSkipAuth$LoginData$$serializer.INSTANCE.getDescriptor());
            }
            this.login = login;
        }

        public LoginData(@JsonProperty("login") Login login) {
            Intrinsics.checkNotNullParameter(login, "login");
            this.login = login;
        }

        public final Login getLogin() {
            return this.login;
        }
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0002()B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB9\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J'\u0010\u001a\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b'R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0016¨\u0006*"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Login;", "", "authToken", "", "refreshToken", "account", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Account;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Account;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Account;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAuthToken$annotations", "()V", "getAuthToken", "()Ljava/lang/String;", "getRefreshToken$annotations", "getRefreshToken", "getAccount$annotations", "getAccount", "()Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Account;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Login {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("account")
        private final Account account;

        @JsonProperty("authToken")
        private final String authToken;

        @JsonProperty("refreshToken")
        private final String refreshToken;

        public static /* synthetic */ Login copy$default(Login login, String str, String str2, Account account, int i, Object obj) {
            if ((i & 1) != 0) {
                str = login.authToken;
            }
            if ((i & 2) != 0) {
                str2 = login.refreshToken;
            }
            if ((i & 4) != 0) {
                account = login.account;
            }
            return login.copy(str, str2, account);
        }

        @SerialName("account")
        public static /* synthetic */ void getAccount$annotations() {
        }

        @SerialName("authToken")
        public static /* synthetic */ void getAuthToken$annotations() {
        }

        @SerialName("refreshToken")
        public static /* synthetic */ void getRefreshToken$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAuthToken() {
            return this.authToken;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Account getAccount() {
            return this.account;
        }

        public final Login copy(@JsonProperty("authToken") String authToken, @JsonProperty("refreshToken") String refreshToken, @JsonProperty("account") Account account) {
            Intrinsics.checkNotNullParameter(authToken, "authToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            Intrinsics.checkNotNullParameter(account, "account");
            return new Login(authToken, refreshToken, account);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Login)) {
                return false;
            }
            Login login = (Login) other;
            return Intrinsics.areEqual(this.authToken, login.authToken) && Intrinsics.areEqual(this.refreshToken, login.refreshToken) && Intrinsics.areEqual(this.account, login.account);
        }

        public int hashCode() {
            return (((this.authToken.hashCode() * 31) + this.refreshToken.hashCode()) * 31) + this.account.hashCode();
        }

        public String toString() {
            return "Login(authToken=" + this.authToken + ", refreshToken=" + this.refreshToken + ", account=" + this.account + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Login$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Login;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Login> serializer() {
                return AnimeSkipAuth$Login$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Login(int i, String str, String str2, Account account, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AnimeSkipAuth$Login$$serializer.INSTANCE.getDescriptor());
            }
            this.authToken = str;
            this.refreshToken = str2;
            this.account = account;
        }

        public Login(@JsonProperty("authToken") String authToken, @JsonProperty("refreshToken") String refreshToken, @JsonProperty("account") Account account) {
            Intrinsics.checkNotNullParameter(authToken, "authToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            Intrinsics.checkNotNullParameter(account, "account");
            this.authToken = authToken;
            this.refreshToken = refreshToken;
            this.account = account;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Login self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.authToken);
            output.encodeStringElement(serialDesc, 1, self.refreshToken);
            output.encodeSerializableElement(serialDesc, 2, AnimeSkipAuth$Account$$serializer.INSTANCE, self.account);
        }

        public final String getAuthToken() {
            return this.authToken;
        }

        public final String getRefreshToken() {
            return this.refreshToken;
        }

        public final Account getAccount() {
            return this.account;
        }
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$ApiRoot;", "", "data", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$ApiData;", "<init>", "(Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$ApiData;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$ApiData;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$ApiData;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ApiRoot {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("data")
        private final ApiData data;

        public static /* synthetic */ ApiRoot copy$default(ApiRoot apiRoot, ApiData apiData, int i, Object obj) {
            if ((i & 1) != 0) {
                apiData = apiRoot.data;
            }
            return apiRoot.copy(apiData);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ApiData getData() {
            return this.data;
        }

        public final ApiRoot copy(@JsonProperty("data") ApiData data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new ApiRoot(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ApiRoot) && Intrinsics.areEqual(this.data, ((ApiRoot) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "ApiRoot(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$ApiRoot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$ApiRoot;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ApiRoot> serializer() {
                return AnimeSkipAuth$ApiRoot$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ApiRoot(int i, ApiData apiData, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AnimeSkipAuth$ApiRoot$$serializer.INSTANCE.getDescriptor());
            }
            this.data = apiData;
        }

        public ApiRoot(@JsonProperty("data") ApiData data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final ApiData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0017\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\u0011\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$ApiData;", "", "myApiClients", "", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$MyApiClient;", "<init>", "(Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getMyApiClients$annotations", "()V", "getMyApiClients", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ApiData {

        @JsonProperty("myApiClients")
        private final List<MyApiClient> myApiClients;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.videoskip.AnimeSkipAuth$ApiData$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AnimeSkipAuth.ApiData._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AnimeSkipAuth$MyApiClient$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiData copy$default(ApiData apiData, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = apiData.myApiClients;
            }
            return apiData.copy(list);
        }

        @SerialName("myApiClients")
        public static /* synthetic */ void getMyApiClients$annotations() {
        }

        public final List<MyApiClient> component1() {
            return this.myApiClients;
        }

        public final ApiData copy(@JsonProperty("myApiClients") List<MyApiClient> myApiClients) {
            Intrinsics.checkNotNullParameter(myApiClients, "myApiClients");
            return new ApiData(myApiClients);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ApiData) && Intrinsics.areEqual(this.myApiClients, ((ApiData) other).myApiClients);
        }

        public int hashCode() {
            return this.myApiClients.hashCode();
        }

        public String toString() {
            return "ApiData(myApiClients=" + this.myApiClients + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$ApiData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$ApiData;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ApiData> serializer() {
                return AnimeSkipAuth$ApiData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ApiData(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AnimeSkipAuth$ApiData$$serializer.INSTANCE.getDescriptor());
            }
            this.myApiClients = list;
        }

        public ApiData(@JsonProperty("myApiClients") List<MyApiClient> myApiClients) {
            Intrinsics.checkNotNullParameter(myApiClients, "myApiClients");
            this.myApiClients = myApiClients;
        }

        public final List<MyApiClient> getMyApiClients() {
            return this.myApiClients;
        }
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$MyApiClient;", "", "id", "", "<init>", "(Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MyApiClient {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final String id;

        public static /* synthetic */ MyApiClient copy$default(MyApiClient myApiClient, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = myApiClient.id;
            }
            return myApiClient.copy(str);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        public final MyApiClient copy(@JsonProperty("id") String id) {
            Intrinsics.checkNotNullParameter(id, "id");
            return new MyApiClient(id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MyApiClient) && Intrinsics.areEqual(this.id, ((MyApiClient) other).id);
        }

        public int hashCode() {
            return this.id.hashCode();
        }

        public String toString() {
            return "MyApiClient(id=" + this.id + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$MyApiClient$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$MyApiClient;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MyApiClient> serializer() {
                return AnimeSkipAuth$MyApiClient$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MyApiClient(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AnimeSkipAuth$MyApiClient$$serializer.INSTANCE.getDescriptor());
            }
            this.id = str;
        }

        public MyApiClient(@JsonProperty("id") String id) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.id = id;
        }

        public final String getId() {
            return this.id;
        }
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007B9\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J'\u0010\u0018\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001c\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Account;", "", "profileUrl", "", HintConstants.AUTOFILL_HINT_USERNAME, "email", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getProfileUrl$annotations", "()V", "getProfileUrl", "()Ljava/lang/String;", "getUsername$annotations", "getUsername", "getEmail$annotations", "getEmail", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Account {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("email")
        private final String email;

        @JsonProperty("profileUrl")
        private final String profileUrl;

        @JsonProperty(HintConstants.AUTOFILL_HINT_USERNAME)
        private final String username;

        public static /* synthetic */ Account copy$default(Account account, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = account.profileUrl;
            }
            if ((i & 2) != 0) {
                str2 = account.username;
            }
            if ((i & 4) != 0) {
                str3 = account.email;
            }
            return account.copy(str, str2, str3);
        }

        @SerialName("email")
        public static /* synthetic */ void getEmail$annotations() {
        }

        @SerialName("profileUrl")
        public static /* synthetic */ void getProfileUrl$annotations() {
        }

        @SerialName(HintConstants.AUTOFILL_HINT_USERNAME)
        public static /* synthetic */ void getUsername$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getProfileUrl() {
            return this.profileUrl;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUsername() {
            return this.username;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        public final Account copy(@JsonProperty("profileUrl") String profileUrl, @JsonProperty(HintConstants.AUTOFILL_HINT_USERNAME) String username, @JsonProperty("email") String email) {
            Intrinsics.checkNotNullParameter(profileUrl, "profileUrl");
            Intrinsics.checkNotNullParameter(username, "username");
            Intrinsics.checkNotNullParameter(email, "email");
            return new Account(profileUrl, username, email);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Account)) {
                return false;
            }
            Account account = (Account) other;
            return Intrinsics.areEqual(this.profileUrl, account.profileUrl) && Intrinsics.areEqual(this.username, account.username) && Intrinsics.areEqual(this.email, account.email);
        }

        public int hashCode() {
            return (((this.profileUrl.hashCode() * 31) + this.username.hashCode()) * 31) + this.email.hashCode();
        }

        public String toString() {
            return "Account(profileUrl=" + this.profileUrl + ", username=" + this.username + ", email=" + this.email + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Account$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Account;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Account> serializer() {
                return AnimeSkipAuth$Account$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Account(int i, String str, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AnimeSkipAuth$Account$$serializer.INSTANCE.getDescriptor());
            }
            this.profileUrl = str;
            this.username = str2;
            this.email = str3;
        }

        public Account(@JsonProperty("profileUrl") String profileUrl, @JsonProperty(HintConstants.AUTOFILL_HINT_USERNAME) String username, @JsonProperty("email") String email) {
            Intrinsics.checkNotNullParameter(profileUrl, "profileUrl");
            Intrinsics.checkNotNullParameter(username, "username");
            Intrinsics.checkNotNullParameter(email, "email");
            this.profileUrl = profileUrl;
            this.username = username;
            this.email = email;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Account self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.profileUrl);
            output.encodeStringElement(serialDesc, 1, self.username);
            output.encodeStringElement(serialDesc, 2, self.email);
        }

        public final String getProfileUrl() {
            return this.profileUrl;
        }

        public final String getUsername() {
            return this.username;
        }

        public final String getEmail() {
            return this.email;
        }
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 +2\u00020\u0001:\u0002*+B/\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bBC\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J1\u0010\u001c\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\nHÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b)R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u001c\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0011¨\u0006,"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Payload;", "", "profileUrl", "", HintConstants.AUTOFILL_HINT_USERNAME, "email", "clientId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getProfileUrl$annotations", "()V", "getProfileUrl", "()Ljava/lang/String;", "getUsername$annotations", "getUsername", "getEmail$annotations", "getEmail", "getClientId$annotations", "getClientId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Payload {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("clientId")
        private final String clientId;

        @JsonProperty("email")
        private final String email;

        @JsonProperty("profileUrl")
        private final String profileUrl;

        @JsonProperty(HintConstants.AUTOFILL_HINT_USERNAME)
        private final String username;

        public static /* synthetic */ Payload copy$default(Payload payload, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = payload.profileUrl;
            }
            if ((i & 2) != 0) {
                str2 = payload.username;
            }
            if ((i & 4) != 0) {
                str3 = payload.email;
            }
            if ((i & 8) != 0) {
                str4 = payload.clientId;
            }
            return payload.copy(str, str2, str3, str4);
        }

        @SerialName("clientId")
        public static /* synthetic */ void getClientId$annotations() {
        }

        @SerialName("email")
        public static /* synthetic */ void getEmail$annotations() {
        }

        @SerialName("profileUrl")
        public static /* synthetic */ void getProfileUrl$annotations() {
        }

        @SerialName(HintConstants.AUTOFILL_HINT_USERNAME)
        public static /* synthetic */ void getUsername$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getProfileUrl() {
            return this.profileUrl;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUsername() {
            return this.username;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getClientId() {
            return this.clientId;
        }

        public final Payload copy(@JsonProperty("profileUrl") String profileUrl, @JsonProperty(HintConstants.AUTOFILL_HINT_USERNAME) String username, @JsonProperty("email") String email, @JsonProperty("clientId") String clientId) {
            Intrinsics.checkNotNullParameter(profileUrl, "profileUrl");
            Intrinsics.checkNotNullParameter(username, "username");
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(clientId, "clientId");
            return new Payload(profileUrl, username, email, clientId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Payload)) {
                return false;
            }
            Payload payload = (Payload) other;
            return Intrinsics.areEqual(this.profileUrl, payload.profileUrl) && Intrinsics.areEqual(this.username, payload.username) && Intrinsics.areEqual(this.email, payload.email) && Intrinsics.areEqual(this.clientId, payload.clientId);
        }

        public int hashCode() {
            return (((((this.profileUrl.hashCode() * 31) + this.username.hashCode()) * 31) + this.email.hashCode()) * 31) + this.clientId.hashCode();
        }

        public String toString() {
            return "Payload(profileUrl=" + this.profileUrl + ", username=" + this.username + ", email=" + this.email + ", clientId=" + this.clientId + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Payload$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth$Payload;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Payload> serializer() {
                return AnimeSkipAuth$Payload$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Payload(int i, String str, String str2, String str3, String str4, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, AnimeSkipAuth$Payload$$serializer.INSTANCE.getDescriptor());
            }
            this.profileUrl = str;
            this.username = str2;
            this.email = str3;
            this.clientId = str4;
        }

        public Payload(@JsonProperty("profileUrl") String profileUrl, @JsonProperty(HintConstants.AUTOFILL_HINT_USERNAME) String username, @JsonProperty("email") String email, @JsonProperty("clientId") String clientId) {
            Intrinsics.checkNotNullParameter(profileUrl, "profileUrl");
            Intrinsics.checkNotNullParameter(username, "username");
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(clientId, "clientId");
            this.profileUrl = profileUrl;
            this.username = username;
            this.email = email;
            this.clientId = clientId;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Payload self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.profileUrl);
            output.encodeStringElement(serialDesc, 1, self.username);
            output.encodeStringElement(serialDesc, 2, self.email);
            output.encodeStringElement(serialDesc, 3, self.clientId);
        }

        public final String getProfileUrl() {
            return this.profileUrl;
        }

        public final String getUsername() {
            return this.username;
        }

        public final String getEmail() {
            return this.email;
        }

        public final String getClientId() {
            return this.clientId;
        }
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object user(AuthToken authToken, Continuation<? super AuthUser> continuation) throws JsonProcessingException {
        String payload;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        AppUtils appUtils = AppUtils.INSTANCE;
        if (authToken == null || (payload = authToken.getPayload()) == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(Payload.INSTANCE.serializer());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Payload.class), null, 2, null));
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
                objDecodeFromString = MainAPIKt.getMapper().readValue(payload, new TypeReference<Payload>() { // from class: com.lagradost.cloudstream3.utils.videoskip.AnimeSkipAuth$user$$inlined$parseJson$1
                });
            } catch (Throwable unused) {
                objDecodeFromString = MainAPIKt.getMapper().readValue(payload, new TypeReference<Payload>() { // from class: com.lagradost.cloudstream3.utils.videoskip.AnimeSkipAuth$user$$inlined$parseJson$1
                });
            }
        } else {
            objDecodeFromString = MainAPIKt.getMapper().readValue(payload, new TypeReference<Payload>() { // from class: com.lagradost.cloudstream3.utils.videoskip.AnimeSkipAuth$user$$inlined$parseJson$1
            });
        }
        Payload payload2 = (Payload) objDecodeFromString;
        return new AuthUser(payload2.getUsername(), payload2.getEmail().hashCode(), payload2.getProfileUrl(), (Map) null, 8, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object login(AuthLoginResponse authLoginResponse, Continuation<? super AuthToken> continuation) throws NoSuchAlgorithmException, ErrorLoadingException {
        AnonymousClass1 anonymousClass1;
        String str;
        char c;
        char c2;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object objPost$default;
        AnonymousClass1 anonymousClass2;
        AuthLoginResponse authLoginResponse2;
        String str2;
        String str3;
        Account account;
        int i;
        String str4;
        String str5;
        MyApiClient myApiClient;
        String id;
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
        Object obj8 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj8);
            String password = authLoginResponse.getPassword();
            if (password == null) {
                return null;
            }
            String strMd5 = md5(password);
            String email = authLoginResponse.getEmail();
            if (email == null && (email = authLoginResponse.getUsername()) == null) {
                return null;
            }
            String str6 = email;
            String str7 = "\n    {\n      login(usernameEmail: \"" + str6 + "\", passwordHash: \"" + strMd5 + "\") {\n        authToken\n        refreshToken\n        account {\n          profileUrl\n          username\n          email\n        }\n      }\n    }\n";
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, str7));
            Map mapMapOf2 = MapsKt.mapOf(TuplesKt.to("Accept", "*/*"), TuplesKt.to("content-type", "application/json"), TuplesKt.to("X-Client-ID", this.baseClientId));
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(authLoginResponse);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(strMd5);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(str6);
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(str7);
            anonymousClass1.label = 1;
            str = str7;
            c = 1;
            c2 = 3;
            obj = "X-Client-ID";
            obj2 = "application/json";
            obj3 = "*/*";
            AnonymousClass1 anonymousClass3 = anonymousClass1;
            obj4 = coroutine_suspended;
            obj5 = "content-type";
            obj6 = SearchIntents.EXTRA_QUERY;
            obj7 = "Accept";
            objPost$default = Requests.post$default(app2, "https://api.anime-skip.com/graphql", mapMapOf2, null, null, null, null, null, mapMapOf, null, false, 0, null, 0L, null, false, null, anonymousClass3, 65404, null);
            anonymousClass2 = anonymousClass3;
            if (objPost$default != obj4) {
                authLoginResponse2 = authLoginResponse;
                str2 = strMd5;
                str3 = str6;
            }
            return obj4;
        }
        if (i2 == 1) {
            String str8 = (String) anonymousClass1.L$3;
            str3 = (String) anonymousClass1.L$2;
            str2 = (String) anonymousClass1.L$1;
            authLoginResponse2 = (AuthLoginResponse) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj8);
            str = str8;
            obj = "X-Client-ID";
            c2 = 3;
            obj2 = "application/json";
            obj5 = "content-type";
            obj3 = "*/*";
            obj6 = SearchIntents.EXTRA_QUERY;
            c = 1;
            anonymousClass2 = anonymousClass1;
            obj4 = coroutine_suspended;
            objPost$default = obj8;
            obj7 = "Accept";
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Account account2 = (Account) anonymousClass1.L$7;
            String str9 = (String) anonymousClass1.L$6;
            String str10 = (String) anonymousClass1.L$5;
            ResultKt.throwOnFailure(obj8);
            str4 = str9;
            str5 = str10;
            account = account2;
            i = 0;
        }
        NiceResponse niceResponse = (NiceResponse) obj8;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        myApiClient = (MyApiClient) CollectionsKt.getOrNull(((ApiRoot) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(ApiRoot.class))).getData().getMyApiClients(), i);
        if (myApiClient != null || (id = myApiClient.getId()) == null) {
            throw new ErrorLoadingException("No API token found");
        }
        return new AuthToken(str5, str4, (Long) null, (Long) null, AppUtils.INSTANCE.toJson(new Payload(account.getProfileUrl(), account.getUsername(), account.getEmail(), id)), 12, (DefaultConstructorMarker) null);
        NiceResponse niceResponse2 = (NiceResponse) objPost$default;
        ResponseParser parser2 = niceResponse2.getParser();
        Intrinsics.checkNotNull(parser2);
        LoginRoot loginRoot = (LoginRoot) parser2.parse(niceResponse2.getText(), Reflection.getOrCreateKotlinClass(LoginRoot.class));
        String authToken = loginRoot.getData().getLogin().getAuthToken();
        String refreshToken = loginRoot.getData().getLogin().getRefreshToken();
        Account account3 = loginRoot.getData().getLogin().getAccount();
        Requests app3 = MainActivityKt.getApp();
        Map mapMapOf3 = MapsKt.mapOf(TuplesKt.to(obj6, "{\n  myApiClients {\n    id\n  }\n}"));
        Pair[] pairArr = new Pair[4];
        pairArr[0] = TuplesKt.to(obj7, obj3);
        pairArr[c] = TuplesKt.to(obj5, obj2);
        pairArr[2] = TuplesKt.to("Authorization", "Bearer " + authToken);
        pairArr[c2] = TuplesKt.to(obj, this.baseClientId);
        Map mapMapOf4 = MapsKt.mapOf(pairArr);
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(authLoginResponse2);
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str2);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str3);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(str);
        anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(loginRoot);
        anonymousClass2.L$5 = authToken;
        anonymousClass2.L$6 = refreshToken;
        anonymousClass2.L$7 = account3;
        anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable("{\n  myApiClients {\n    id\n  }\n}");
        anonymousClass2.label = 2;
        account = account3;
        i = 0;
        Object objPost$default2 = Requests.post$default(app3, "https://api.anime-skip.com/graphql", mapMapOf4, null, null, null, null, null, mapMapOf3, null, false, 0, null, 0L, null, false, null, anonymousClass2, 65404, null);
        if (objPost$default2 != obj4) {
            str4 = refreshToken;
            obj8 = objPost$default2;
            str5 = authToken;
            NiceResponse niceResponse3 = (NiceResponse) obj8;
            ResponseParser parser3 = niceResponse3.getParser();
            Intrinsics.checkNotNull(parser3);
            myApiClient = (MyApiClient) CollectionsKt.getOrNull(((ApiRoot) parser3.parse(niceResponse3.getText(), Reflection.getOrCreateKotlinClass(ApiRoot.class))).getData().getMyApiClients(), i);
            if (myApiClient != null) {
            }
            throw new ErrorLoadingException("No API token found");
        }
        return obj4;
    }
}
