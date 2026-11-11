package com.lagradost.cloudstream3.syncproviders;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: AuthRepo.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000 L2\u00020\u0001:\u0001LB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010%\u001a\u0004\u0018\u00010&H\u0084@¢\u0006\u0002\u0010'J\u0006\u0010(\u001a\u00020\tJ\u0006\u0010)\u001a\u00020*J\u0016\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020-H\u0086@¢\u0006\u0002\u0010.J\u000e\u0010/\u001a\u00020*2\u0006\u00100\u001a\u00020&J\b\u00101\u001a\u0004\u0018\u00010&J\b\u00102\u001a\u0004\u0018\u000103J\b\u00104\u001a\u0004\u0018\u00010-J\u0010\u0010?\u001a\u0004\u0018\u00010@H\u0086@¢\u0006\u0002\u0010'J\u0016\u0010A\u001a\u00020\t2\u0006\u0010B\u001a\u000203H\u0082@¢\u0006\u0002\u0010CJ\u0016\u0010D\u001a\u00020\t2\u0006\u0010E\u001a\u00020FH\u0086@¢\u0006\u0002\u0010GJ\u0016\u0010D\u001a\u00020\t2\u0006\u0010H\u001a\u00020@H\u0086@¢\u0006\u0002\u0010IJ\u0016\u0010D\u001a\u00020\t2\u0006\u0010J\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010KR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\f\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000eR\u0011\u0010\u001a\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\u001c\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u001e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0017R\u0013\u0010 \u001a\u0004\u0018\u00010!8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b$\u0010\u0017R\u0017\u00105\u001a\b\u0012\u0004\u0012\u00020&068F¢\u0006\u0006\u001a\u0004\b7\u00108R$\u0010:\u001a\u00020\u00122\u0006\u00109\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>¨\u0006M"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthRepo;", "", "api", "Lcom/lagradost/cloudstream3/syncproviders/AuthAPI;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/AuthAPI;)V", "getApi", "()Lcom/lagradost/cloudstream3/syncproviders/AuthAPI;", "isValidRedirectUrl", "", ImagesContract.URL, "", "idPrefix", "getIdPrefix", "()Ljava/lang/String;", "name", "getName", "icon", "", "getIcon", "()Ljava/lang/Integer;", "requiresLogin", "getRequiresLogin", "()Z", "createAccountUrl", "getCreateAccountUrl", "hasOAuth2", "getHasOAuth2", "hasPin", "getHasPin", "hasInApp", "getHasInApp", "inAppLoginRequirement", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginRequirement;", "getInAppLoginRequirement", "()Lcom/lagradost/cloudstream3/syncproviders/AuthLoginRequirement;", "isAvailable", "freshAuth", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openOAuth2Page", "openOAuth2PageWithToast", "", "logout", TypedValues.TransitionType.S_FROM, "Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthUser;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshUser", "newAuth", "authData", "authToken", "Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "authUser", "accounts", "", "getAccounts", "()[Lcom/lagradost/cloudstream3/syncproviders/AuthData;", ES6Iterator.VALUE_PROPERTY, "accountId", "getAccountId", "()I", "setAccountId", "(I)V", "pinRequest", "Lcom/lagradost/cloudstream3/syncproviders/AuthPinData;", "setupLogin", "token", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "form", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "payload", "(Lcom/lagradost/cloudstream3/syncproviders/AuthPinData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "redirectUrl", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class AuthRepo {
    private static final Map<String, String> oauthPayload = new LinkedHashMap();
    private final AuthAPI api;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.AuthRepo$freshAuth$1, reason: invalid class name */
    /* JADX INFO: compiled from: AuthRepo.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.AuthRepo", f = "AuthRepo.kt", i = {0}, l = {37}, m = "freshAuth", n = {"data"}, nl = {38}, s = {"L$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepo.this.freshAuth(this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.AuthRepo$login$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthRepo.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.AuthRepo", f = "AuthRepo.kt", i = {0, 1}, l = {152, 152}, m = "login", n = {"form", "form"}, nl = {152, -1}, s = {"L$0", "L$0"}, v = 2)
    static final class C07651 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07651(Continuation<? super C07651> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepo.this.login((AuthLoginResponse) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.AuthRepo$login$2, reason: invalid class name */
    /* JADX INFO: compiled from: AuthRepo.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.AuthRepo", f = "AuthRepo.kt", i = {0, 1}, l = {157, 157}, m = "login", n = {"payload", "payload"}, nl = {157, -1}, s = {"L$0", "L$0"}, v = 2)
    static final class AnonymousClass2 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepo.this.login((AuthPinData) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.AuthRepo$login$3, reason: invalid class name */
    /* JADX INFO: compiled from: AuthRepo.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.AuthRepo", f = "AuthRepo.kt", i = {0, 1}, l = {163, 162}, m = "login", n = {"redirectUrl", "redirectUrl"}, nl = {165, -1}, s = {"L$0", "L$0"}, v = 2)
    static final class AnonymousClass3 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepo.this.login((String) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.AuthRepo$logout$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthRepo.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.AuthRepo", f = "AuthRepo.kt", i = {0, 0, 0, 0, 0}, l = {80}, m = "logout", n = {TypedValues.TransitionType.S_FROM, "currentAccounts", "newAccounts", "oldAccounts", "oldAccount"}, nl = {81}, s = {"L$0", "L$1", "L$2", "L$3", "L$5"}, v = 2)
    static final class C07661 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C07661(Continuation<? super C07661> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepo.this.logout(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.AuthRepo$setupLogin$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthRepo.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.AuthRepo", f = "AuthRepo.kt", i = {0}, l = {129}, m = "setupLogin", n = {"token"}, nl = {131}, s = {"L$0"}, v = 2)
    static final class C07671 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07671(Continuation<? super C07671> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthRepo.this.setupLogin(null, this);
        }
    }

    public AuthRepo(AuthAPI api) {
        Intrinsics.checkNotNullParameter(api, "api");
        this.api = api;
    }

    public AuthAPI getApi() {
        return this.api;
    }

    public final boolean isValidRedirectUrl(final String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        Boolean bool = (Boolean) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.AuthRepo$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(this.f$0.getApi().isValidRedirectUrl(url));
            }
        });
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final String getIdPrefix() {
        return getApi().getIdPrefix();
    }

    public final String getName() {
        return getApi().getName();
    }

    public final Integer getIcon() {
        return getApi().getIcon();
    }

    public final boolean getRequiresLogin() {
        return getApi().getRequiresLogin();
    }

    public final String getCreateAccountUrl() {
        return getApi().getCreateAccountUrl();
    }

    public final boolean getHasOAuth2() {
        return getApi().getHasOAuth2();
    }

    public final boolean getHasPin() {
        return getApi().getHasPin();
    }

    public final boolean getHasInApp() {
        return getApi().getHasInApp();
    }

    public final AuthLoginRequirement getInAppLoginRequirement() {
        return getApi().getInAppLoginRequirement();
    }

    public final boolean isAvailable() {
        return (getApi().getRequiresLogin() && authUser() == null) ? false : true;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    protected final Object freshAuth(Continuation<? super AuthData> continuation) {
        AnonymousClass1 anonymousClass1;
        AuthData authData;
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
            AuthData authData2 = authData();
            if (authData2 == null) {
                return null;
            }
            if (!AuthToken.isAccessTokenExpired$default(authData2.getToken(), 0L, 1, null)) {
                return authData2;
            }
            AuthAPI api = getApi();
            AuthToken token = authData2.getToken();
            anonymousClass1.L$0 = authData2;
            anonymousClass1.label = 1;
            Object objRefreshToken = api.refreshToken(token, anonymousClass1);
            if (objRefreshToken == coroutine_suspended) {
                return coroutine_suspended;
            }
            authData = authData2;
            obj = objRefreshToken;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            authData = (AuthData) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
        }
        AuthToken authToken = (AuthToken) obj;
        if (authToken == null) {
            return null;
        }
        AuthData authData3 = new AuthData(authData.getUser(), authToken);
        refreshUser(authData3);
        return authData3;
    }

    public final boolean openOAuth2Page() {
        AuthLoginPage authLoginPageLoginRequest = getApi().loginRequest();
        if (authLoginPageLoginRequest == null) {
            return false;
        }
        Map<String, String> map = oauthPayload;
        synchronized (map) {
            map.put(getIdPrefix(), authLoginPageLoginRequest.getPayload());
        }
        CloudStreamApp.Companion.openBrowser$default(CloudStreamApp.INSTANCE, authLoginPageLoginRequest.getUrl(), false, null, 6, null);
        return true;
    }

    public final void openOAuth2PageWithToast() {
        try {
            if (openOAuth2Page()) {
                return;
            }
            CommonActivity.showToast$default(CommonActivity.INSTANCE, TextUtilKt.txt(R.string.authenticated_user_fail, getApi().getName()), (Integer) null, 2, (Object) null);
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            if ((th instanceof ErrorLoadingException) && th.getMessage() != null) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, th.getMessage(), (Integer) null, 2, (Object) null);
            } else {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, TextUtilKt.txt(R.string.authenticated_user_fail, getApi().getName()), (Integer) null, 2, (Object) null);
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(6:31|46|32|51|(1:34)(1:35)|29) */
    /* JADX WARN: Code duplicated, block: B:31:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:34:0x0100 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:35:0x0101  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0108, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0109, code lost:
    
        r6 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x010c, code lost:
    
        com.lagradost.cloudstream3.mvvm.ArchComponentExtKt.logError(r6);
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x0101 -> B:36:0x0102). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object logout(com.lagradost.cloudstream3.syncproviders.AuthUser r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.syncproviders.AuthRepo.logout(com.lagradost.cloudstream3.syncproviders.AuthUser, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void refreshUser(AuthData newAuth) {
        Intrinsics.checkNotNullParameter(newAuth, "newAuth");
        AuthData[] authDataArrAccounts = AccountManager.INSTANCE.accounts(getIdPrefix());
        ArrayList arrayList = new ArrayList(authDataArrAccounts.length);
        for (AuthData authData : authDataArrAccounts) {
            if (authData.getUser().getId() == newAuth.getUser().getId()) {
                authData = newAuth;
            }
            arrayList.add(authData);
        }
        AccountManager.INSTANCE.updateAccounts(getIdPrefix(), (AuthData[]) arrayList.toArray(new AuthData[0]));
    }

    public final AuthData authData() {
        AuthData authData;
        synchronized (AccountManager.INSTANCE.getCachedAccountIds()) {
            Integer num = AccountManager.INSTANCE.getCachedAccountIds().get(getIdPrefix());
            authData = null;
            if (num != null) {
                int iIntValue = num.intValue();
                AuthData[] authDataArr = AccountManager.INSTANCE.getCachedAccounts().get(getIdPrefix());
                if (authDataArr != null) {
                    for (AuthData authData2 : authDataArr) {
                        if (authData2.getUser().getId() == iIntValue) {
                            authData = authData2;
                            break;
                        }
                    }
                }
            }
        }
        return authData;
    }

    public final AuthToken authToken() {
        AuthData authData = authData();
        if (authData != null) {
            return authData.getToken();
        }
        return null;
    }

    public final AuthUser authUser() {
        AuthData authData = authData();
        if (authData != null) {
            return authData.getUser();
        }
        return null;
    }

    public final AuthData[] getAccounts() {
        AuthData[] authDataArr;
        synchronized (AccountManager.INSTANCE.getCachedAccounts()) {
            authDataArr = AccountManager.INSTANCE.getCachedAccounts().get(getIdPrefix());
            if (authDataArr == null) {
                authDataArr = new AuthData[0];
            }
        }
        return authDataArr;
    }

    public final int getAccountId() {
        int iIntValue;
        synchronized (AccountManager.INSTANCE.getCachedAccountIds()) {
            Integer num = AccountManager.INSTANCE.getCachedAccountIds().get(getIdPrefix());
            iIntValue = num != null ? num.intValue() : -1;
        }
        return iIntValue;
    }

    public final void setAccountId(int i) {
        AccountManager.INSTANCE.updateAccountsId(getIdPrefix(), i);
    }

    public final Object pinRequest(Continuation<? super AuthPinData> continuation) {
        return getApi().pinRequest(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object setupLogin(AuthToken authToken, Continuation<? super Boolean> continuation) throws ErrorLoadingException {
        C07671 c07671;
        if (continuation instanceof C07671) {
            c07671 = (C07671) continuation;
            if ((c07671.label & Integer.MIN_VALUE) != 0) {
                c07671.label -= Integer.MIN_VALUE;
            } else {
                c07671 = new C07671(continuation);
            }
        } else {
            c07671 = new C07671(continuation);
        }
        Object objUser = c07671.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07671.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objUser);
            AuthAPI api = getApi();
            c07671.L$0 = authToken;
            c07671.label = 1;
            objUser = api.user(authToken, c07671);
            if (objUser == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            authToken = (AuthToken) c07671.L$0;
            ResultKt.throwOnFailure(objUser);
        }
        AuthUser authUser = (AuthUser) objUser;
        if (authUser == null) {
            return Boxing.boxBoolean(false);
        }
        AuthData authData = new AuthData(authUser, authToken);
        AuthData[] authDataArrAccounts = AccountManager.INSTANCE.accounts(getIdPrefix());
        for (AuthData authData2 : authDataArrAccounts) {
            if (authData2.getUser().getId() == authData.getUser().getId()) {
                throw new ErrorLoadingException("Already logged into this account");
            }
        }
        AccountManager.INSTANCE.updateAccounts(getIdPrefix(), (AuthData[]) ArraysKt.plus(authDataArrAccounts, authData));
        AccountManager.INSTANCE.updateAccountsId(getIdPrefix(), authUser.getId());
        if (this instanceof SyncRepo) {
            ((SyncRepo) this).setRequireLibraryRefresh(true);
        }
        return Boxing.boxBoolean(true);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object login(AuthLoginResponse authLoginResponse, Continuation<? super Boolean> continuation) throws ErrorLoadingException {
        C07651 c07651;
        if (continuation instanceof C07651) {
            c07651 = (C07651) continuation;
            if ((c07651.label & Integer.MIN_VALUE) != 0) {
                c07651.label -= Integer.MIN_VALUE;
            } else {
                c07651 = new C07651(continuation);
            }
        } else {
            c07651 = new C07651(continuation);
        }
        Object objLogin = c07651.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07651.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objLogin);
            AuthAPI api = getApi();
            c07651.L$0 = SpillingKt.nullOutSpilledVariable(authLoginResponse);
            c07651.L$1 = this;
            c07651.label = 1;
            objLogin = api.login(authLoginResponse, c07651);
            if (objLogin != coroutine_suspended) {
            }
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objLogin);
            return objLogin;
        }
        this = (AuthRepo) c07651.L$1;
        authLoginResponse = (AuthLoginResponse) c07651.L$0;
        ResultKt.throwOnFailure(objLogin);
        AuthToken authToken = (AuthToken) objLogin;
        if (authToken == null) {
            return Boxing.boxBoolean(false);
        }
        c07651.L$0 = SpillingKt.nullOutSpilledVariable(authLoginResponse);
        c07651.L$1 = null;
        c07651.label = 2;
        Object obj = this.setupLogin(authToken, c07651);
        return obj == coroutine_suspended ? coroutine_suspended : obj;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object login(AuthPinData authPinData, Continuation<? super Boolean> continuation) throws ErrorLoadingException {
        AnonymousClass2 anonymousClass2;
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
        Object objLogin = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass2.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objLogin);
            AuthAPI api = getApi();
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(authPinData);
            anonymousClass2.L$1 = this;
            anonymousClass2.label = 1;
            objLogin = api.login(authPinData, anonymousClass2);
            if (objLogin != coroutine_suspended) {
            }
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objLogin);
            return objLogin;
        }
        this = (AuthRepo) anonymousClass2.L$1;
        authPinData = (AuthPinData) anonymousClass2.L$0;
        ResultKt.throwOnFailure(objLogin);
        AuthToken authToken = (AuthToken) objLogin;
        if (authToken == null) {
            return Boxing.boxBoolean(false);
        }
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(authPinData);
        anonymousClass2.L$1 = null;
        anonymousClass2.label = 2;
        Object obj = this.setupLogin(authToken, anonymousClass2);
        return obj == coroutine_suspended ? coroutine_suspended : obj;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object login(String str, Continuation<? super Boolean> continuation) throws ErrorLoadingException {
        AnonymousClass3 anonymousClass3;
        String str2;
        if (continuation instanceof AnonymousClass3) {
            anonymousClass3 = (AnonymousClass3) continuation;
            if ((anonymousClass3.label & Integer.MIN_VALUE) != 0) {
                anonymousClass3.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass3 = new AnonymousClass3(continuation);
            }
        } else {
            anonymousClass3 = new AnonymousClass3(continuation);
        }
        Object objLogin = anonymousClass3.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass3.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objLogin);
            AuthAPI api = getApi();
            Map<String, String> map = oauthPayload;
            synchronized (map) {
                str2 = map.get(getApi().getIdPrefix());
            }
            anonymousClass3.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass3.L$1 = this;
            anonymousClass3.label = 1;
            objLogin = api.login(str, str2, anonymousClass3);
            if (objLogin != coroutine_suspended) {
            }
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objLogin);
            return objLogin;
        }
        this = (AuthRepo) anonymousClass3.L$1;
        str = (String) anonymousClass3.L$0;
        ResultKt.throwOnFailure(objLogin);
        AuthToken authToken = (AuthToken) objLogin;
        if (authToken != null) {
            anonymousClass3.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass3.L$1 = null;
            anonymousClass3.label = 2;
            Object obj = this.setupLogin(authToken, anonymousClass3);
            return obj == coroutine_suspended ? coroutine_suspended : obj;
        }
        return Boxing.boxBoolean(false);
    }
}
