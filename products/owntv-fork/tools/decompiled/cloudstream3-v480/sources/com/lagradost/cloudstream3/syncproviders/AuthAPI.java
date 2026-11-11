package com.lagradost.cloudstream3.syncproviders;

import androidx.mediarouter.media.GlobalMediaRouter;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.slf4j.Marker;

/* JADX INFO: compiled from: AuthAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 >2\u00020\u0001:\u0002>?B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u0005H\u0016J\"\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020\u00052\b\u0010&\u001a\u0004\u0018\u00010\u0005H\u0096@¢\u0006\u0002\u0010'J\n\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0010\u0010*\u001a\u0004\u0018\u00010+H\u0096@¢\u0006\u0002\u0010,J\u0018\u0010-\u001a\u0004\u0018\u00010$2\u0006\u0010.\u001a\u00020$H\u0096@¢\u0006\u0002\u0010/J\u0018\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010&\u001a\u00020+H\u0096@¢\u0006\u0002\u00100J\u0018\u0010#\u001a\u0004\u0018\u00010$2\u0006\u00101\u001a\u000202H\u0096@¢\u0006\u0002\u00103J\u001a\u00104\u001a\u0004\u0018\u0001052\b\u0010.\u001a\u0004\u0018\u00010$H\u0096@¢\u0006\u0002\u0010/J\u0016\u00106\u001a\u0002072\u0006\u0010.\u001a\u00020$H\u0096@¢\u0006\u0002\u0010/J\b\u00108\u001a\u000209H\u0007J\n\u0010:\u001a\u0004\u0018\u00010;H\u0007J\u0010\u0010<\u001a\u0004\u0018\u00010=H\u0087@¢\u0006\u0002\u0010,R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0096\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0007R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0007R\u0014\u0010\u0017\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0014\u0010\u0019\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0014\u0010\u001b\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006@"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthAPI;", "", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "idPrefix", "getIdPrefix", "icon", "", "getIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "requiresLogin", "", "getRequiresLogin", "()Z", "createAccountUrl", "getCreateAccountUrl", "redirectUrlIdentifier", "getRedirectUrlIdentifier", "hasOAuth2", "getHasOAuth2", "hasPin", "getHasPin", "hasInApp", "getHasInApp", "inAppLoginRequirement", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginRequirement;", "getInAppLoginRequirement", "()Lcom/lagradost/cloudstream3/syncproviders/AuthLoginRequirement;", "isValidRedirectUrl", ImagesContract.URL, "login", "Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "redirectUrl", "payload", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginRequest", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginPage;", "pinRequest", "Lcom/lagradost/cloudstream3/syncproviders/AuthPinData;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshToken", "token", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthPinData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "form", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "user", "Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "invalidateToken", "", "toRepo", "Lcom/lagradost/cloudstream3/syncproviders/AuthRepo;", "loginInfo", "Lcom/lagradost/cloudstream3/syncproviders/AuthAPI$LoginInfo;", "getPersonalLibrary", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryMetadata;", "Companion", "LoginInfo", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class AuthAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String createAccountUrl;
    private final boolean hasInApp;
    private final boolean hasOAuth2;
    private final boolean hasPin;
    private final Integer icon;
    private final AuthLoginRequirement inAppLoginRequirement;
    private final String redirectUrlIdentifier;
    private final String name = "NONE";
    private final String idPrefix = "NONE";
    private final boolean requiresLogin = true;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.AuthAPI$getPersonalLibrary$1, reason: invalid class name */
    /* JADX INFO: compiled from: AuthAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.AuthAPI", f = "AuthAPI.kt", i = {}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED}, m = "getPersonalLibrary", n = {}, nl = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED}, s = {}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthAPI.this.getPersonalLibrary(this);
        }
    }

    public Object invalidateToken(AuthToken authToken, Continuation<?> continuation) {
        return invalidateToken$suspendImpl(this, authToken, continuation);
    }

    public Object login(AuthLoginResponse authLoginResponse, Continuation<? super AuthToken> continuation) {
        return login$suspendImpl(this, authLoginResponse, continuation);
    }

    public Object login(AuthPinData authPinData, Continuation<? super AuthToken> continuation) {
        return login$suspendImpl(this, authPinData, continuation);
    }

    public Object login(String str, String str2, Continuation<? super AuthToken> continuation) {
        return login$suspendImpl(this, str, str2, continuation);
    }

    public Object pinRequest(Continuation<? super AuthPinData> continuation) {
        return pinRequest$suspendImpl(this, continuation);
    }

    public Object refreshToken(AuthToken authToken, Continuation<? super AuthToken> continuation) {
        return refreshToken$suspendImpl(this, authToken, continuation);
    }

    public Object user(AuthToken authToken, Continuation<? super AuthUser> continuation) {
        return user$suspendImpl(this, authToken, continuation);
    }

    public String getName() {
        return this.name;
    }

    public String getIdPrefix() {
        return this.idPrefix;
    }

    public Integer getIcon() {
        return this.icon;
    }

    public boolean getRequiresLogin() {
        return this.requiresLogin;
    }

    public String getCreateAccountUrl() {
        return this.createAccountUrl;
    }

    public String getRedirectUrlIdentifier() {
        return this.redirectUrlIdentifier;
    }

    public boolean getHasOAuth2() {
        return this.hasOAuth2;
    }

    public boolean getHasPin() {
        return this.hasPin;
    }

    public boolean getHasInApp() {
        return this.hasInApp;
    }

    public AuthLoginRequirement getInAppLoginRequirement() {
        return this.inAppLoginRequirement;
    }

    /* JADX INFO: compiled from: AuthAPI.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u000eJ\u0006\u0010\u0010\u001a\u00020\u000eR\u001a\u0010\u0004\u001a\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u0003\u001a\u0004\b\u000b\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthAPI$Companion;", "", "<init>", "()V", "unixTime", "", "getUnixTime$annotations", "getUnixTime", "()J", "unixTimeMs", "getUnixTimeMs$annotations", "getUnixTimeMs", "splitRedirectUrl", "", "", "redirectUrl", "generateCodeVerifier", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Use APIHolder.unixTime instead", replaceWith = @ReplaceWith(expression = "APIHolder.unixTime", imports = {"com.lagradost.cloudstream3.APIHolder"}))
        public static /* synthetic */ void getUnixTime$annotations() {
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Use APIHolder.unixTimeMS instead", replaceWith = @ReplaceWith(expression = "unixTimeMS", imports = {"com.lagradost.cloudstream3.APIHolder.unixTimeMS"}))
        public static /* synthetic */ void getUnixTimeMs$annotations() {
        }

        private Companion() {
        }

        public final long getUnixTime() {
            return APIHolder.INSTANCE.getUnixTime();
        }

        public final long getUnixTimeMs() {
            return APIHolder.INSTANCE.getUnixTimeMS();
        }

        public final Map<String, String> splitRedirectUrl(String redirectUrl) throws MalformedURLException {
            Intrinsics.checkNotNullParameter(redirectUrl, "redirectUrl");
            AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
            URL url = new URI(StringsKt.replace$default(StringsKt.replace$default(redirectUrl, AccountManager.APP_STRING, "https", false, 4, (Object) null), "/#", "?", false, 4, (Object) null)).toURL();
            Intrinsics.checkNotNullExpressionValue(url, "toURL(...)");
            return appContextUtils.splitQuery(url);
        }

        public final String generateCodeVerifier() {
            byte[] bArr = new byte[96];
            new SecureRandom().nextBytes(bArr);
            return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.trimEnd(MainAPIKt.base64Encode(bArr), '='), Marker.ANY_NON_NULL_MARKER, "-", false, 4, (Object) null), "/", "_", false, 4, (Object) null), "\n", "", false, 4, (Object) null);
        }
    }

    public boolean isValidRedirectUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        if (getRedirectUrlIdentifier() != null) {
            if (StringsKt.contains$default((CharSequence) url, (CharSequence) ("/" + getRedirectUrlIdentifier()), false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ Object login$suspendImpl(AuthAPI authAPI, String str, String str2, Continuation<? super AuthToken> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    public AuthLoginPage loginRequest() {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object pinRequest$suspendImpl(AuthAPI authAPI, Continuation<? super AuthPinData> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object refreshToken$suspendImpl(AuthAPI authAPI, AuthToken authToken, Continuation<? super AuthToken> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object login$suspendImpl(AuthAPI authAPI, AuthPinData authPinData, Continuation<? super AuthToken> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object login$suspendImpl(AuthAPI authAPI, AuthLoginResponse authLoginResponse, Continuation<? super AuthToken> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object user$suspendImpl(AuthAPI authAPI, AuthToken authToken, Continuation<? super AuthUser> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object invalidateToken$suspendImpl(AuthAPI authAPI, AuthToken authToken, Continuation<?> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Please use the new API for AuthAPI")
    public final AuthRepo toRepo() {
        if (this instanceof SubtitleAPI) {
            return new SubtitleRepo((SubtitleAPI) this);
        }
        if (this instanceof SyncAPI) {
            return new SyncRepo((SyncAPI) this);
        }
        throw new NotImplementedError("Unknown inheritance from AuthAPI");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Please use the new API for AuthAPI")
    public final LoginInfo loginInfo() {
        AuthUser authUser = toRepo().authUser();
        if (authUser != null) {
            return new LoginInfo(authUser.getProfilePicture(), authUser.getName(), -1);
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Please use the new API for AuthAPI")
    public final Object getPersonalLibrary(Continuation<? super SyncAPI.LibraryMetadata> continuation) {
        AnonymousClass1 anonymousClass1;
        Object objM8425libraryIoAF18A;
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
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AuthRepo repo = toRepo();
            SyncRepo syncRepo = repo instanceof SyncRepo ? (SyncRepo) repo : null;
            if (syncRepo == null) {
                return null;
            }
            anonymousClass1.label = 1;
            objM8425libraryIoAF18A = syncRepo.m8425libraryIoAF18A(anonymousClass1);
            if (objM8425libraryIoAF18A == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            objM8425libraryIoAF18A = ((Result) obj).getValue();
        }
        ResultKt.throwOnFailure(objM8425libraryIoAF18A);
        return (SyncAPI.LibraryMetadata) objM8425libraryIoAF18A;
    }

    /* JADX INFO: compiled from: AuthAPI.kt */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Please use the new API for AuthAPI")
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthAPI$LoginInfo;", "", "profilePicture", "", "name", "accountIndex", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;I)V", "getProfilePicture", "()Ljava/lang/String;", "getName", "getAccountIndex", "()I", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class LoginInfo {
        private final int accountIndex;
        private final String name;
        private final String profilePicture;

        public LoginInfo(String str, String str2, int i) {
            this.profilePicture = str;
            this.name = str2;
            this.accountIndex = i;
        }

        public /* synthetic */ LoginInfo(String str, String str2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? null : str, str2, i);
        }

        public final String getProfilePicture() {
            return this.profilePicture;
        }

        public final String getName() {
            return this.name;
        }

        public final int getAccountIndex() {
            return this.accountIndex;
        }
    }
}
