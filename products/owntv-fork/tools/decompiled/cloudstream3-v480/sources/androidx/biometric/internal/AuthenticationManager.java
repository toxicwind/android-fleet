package androidx.biometric.internal;

import android.content.Context;
import android.os.Build;
import androidx.biometric.AuthenticationRequest;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.R;
import androidx.biometric.internal.data.CanceledFrom;
import androidx.biometric.internal.viewmodel.AuthenticationViewModel;
import androidx.biometric.utils.AuthenticatorUtils;
import androidx.biometric.utils.BiometricErrorData;
import androidx.biometric.utils.DeviceUtils;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: AuthenticationManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 C2\u00020\u0001:\u0001CBI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u001e\u00103\u001a\u00020\u00102\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$J&\u00104\u001a\u00020\u00102\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u0001082\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u000e\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020<J\u0006\u0010=\u001a\u00020\u0010J\b\u0010>\u001a\u00020\u0010H\u0002J\b\u0010?\u001a\u00020\u0010H\u0002J\b\u0010@\u001a\u00020\u0010H\u0002J\b\u0010A\u001a\u00020\u0010H\u0002J\u0016\u0010B\u001a\u00020\u00102\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020 @BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\"\u0010%\u001a\u0004\u0018\u00010$2\b\u0010\u001f\u001a\u0004\u0018\u00010$@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010(\u001a\u00020)¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u00101\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b1\u00102¨\u0006D"}, d2 = {"Landroidx/biometric/internal/AuthenticationManager;", "", "context", "Landroid/content/Context;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "viewModel", "Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "confirmCredentialActivityLauncher", "Ljava/lang/Runnable;", "clientExecutor", "Ljava/util/concurrent/Executor;", "clientAuthenticationCallback", "Landroidx/biometric/BiometricPrompt$AuthenticationCallback;", "onDismissed", "Lkotlin/Function0;", "", "<init>", "(Landroid/content/Context;Landroidx/lifecycle/LifecycleOwner;Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;Ljava/lang/Runnable;Ljava/util/concurrent/Executor;Landroidx/biometric/BiometricPrompt$AuthenticationCallback;Lkotlin/jvm/functions/Function0;)V", "getContext", "()Landroid/content/Context;", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "getViewModel", "()Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "getConfirmCredentialActivityLauncher", "()Ljava/lang/Runnable;", "getClientExecutor", "()Ljava/util/concurrent/Executor;", "getClientAuthenticationCallback", "()Landroidx/biometric/BiometricPrompt$AuthenticationCallback;", ES6Iterator.VALUE_PROPERTY, "Landroidx/biometric/internal/AuthenticationResultDispatcher;", "resultDispatcher", "getResultDispatcher", "()Landroidx/biometric/internal/AuthenticationResultDispatcher;", "Landroidx/biometric/internal/AuthenticationUiStateObserver;", "uiStateObserver", "getUiStateObserver", "()Landroidx/biometric/internal/AuthenticationUiStateObserver;", "lifecycleContainer", "Landroidx/biometric/BiometricPrompt$LifecycleContainer;", "getLifecycleContainer", "()Landroidx/biometric/BiometricPrompt$LifecycleContainer;", "isInitialized", "", "isAuthenticationPrepared", "callbackObserverJob", "Lkotlinx/coroutines/Job;", "isNegativeButtonPressPendingObserver", "()Lkotlin/jvm/functions/Function0;", "initialize", "authenticate", "info", "Landroidx/biometric/BiometricPrompt$PromptInfo;", "crypto", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "showAuthentication", "cancelAuthentication", "canceledFrom", "Landroidx/biometric/internal/data/CanceledFrom;", "dismiss", "startObservingAuth", "stopObservingAuth", "connectCallbackObservers", "disconnectCallbackObservers", "showPromptForAuthentication", "Companion", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AuthenticationManager {
    private static final Companion Companion = new Companion(null);
    private static final int SHOW_PROMPT_DELAY_MS = 600;
    private Job callbackObserverJob;
    private final BiometricPrompt.AuthenticationCallback clientAuthenticationCallback;
    private final Executor clientExecutor;
    private final Runnable confirmCredentialActivityLauncher;
    private final Context context;
    private boolean isAuthenticationPrepared;
    private boolean isInitialized;
    private final Function0<Unit> isNegativeButtonPressPendingObserver;
    private final BiometricPrompt.LifecycleContainer lifecycleContainer;
    private final LifecycleOwner lifecycleOwner;
    private final Function0<Unit> onDismissed;
    private AuthenticationResultDispatcher resultDispatcher;
    private AuthenticationUiStateObserver uiStateObserver;
    private final AuthenticationViewModel viewModel;

    /* JADX INFO: compiled from: AuthenticationManager.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Lifecycle.Event.ON_DESTROY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AuthenticationManager(final Context context, LifecycleOwner lifecycleOwner, final AuthenticationViewModel viewModel, final Runnable confirmCredentialActivityLauncher, final Executor clientExecutor, final BiometricPrompt.AuthenticationCallback clientAuthenticationCallback, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(confirmCredentialActivityLauncher, "confirmCredentialActivityLauncher");
        Intrinsics.checkNotNullParameter(clientExecutor, "clientExecutor");
        Intrinsics.checkNotNullParameter(clientAuthenticationCallback, "clientAuthenticationCallback");
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        this.viewModel = viewModel;
        this.confirmCredentialActivityLauncher = confirmCredentialActivityLauncher;
        this.clientExecutor = clientExecutor;
        this.clientAuthenticationCallback = clientAuthenticationCallback;
        this.onDismissed = function0;
        final Function0 function1 = new Function0() { // from class: androidx.biometric.internal.AuthenticationManager$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AuthenticationManager.resultDispatcher$lambda$0(this.f$0);
            }
        };
        this.resultDispatcher = new AuthenticationResultDispatcher(context, viewModel, clientExecutor, clientAuthenticationCallback, confirmCredentialActivityLauncher, function1) { // from class: androidx.biometric.internal.AuthenticationManager$resultDispatcher$1
        };
        this.lifecycleContainer = new BiometricPrompt.LifecycleContainer(lifecycleOwner.getLifecycleRegistry());
        this.isNegativeButtonPressPendingObserver = new Function0() { // from class: androidx.biometric.internal.AuthenticationManager$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AuthenticationManager.isNegativeButtonPressPendingObserver$lambda$0(this.f$0);
            }
        };
    }

    public /* synthetic */ AuthenticationManager(Context context, LifecycleOwner lifecycleOwner, AuthenticationViewModel authenticationViewModel, Runnable runnable, Executor executor, BiometricPrompt.AuthenticationCallback authenticationCallback, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, lifecycleOwner, authenticationViewModel, runnable, executor, authenticationCallback, (i & 64) != 0 ? null : function0);
    }

    public final Context getContext() {
        return this.context;
    }

    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    public final AuthenticationViewModel getViewModel() {
        return this.viewModel;
    }

    public final Runnable getConfirmCredentialActivityLauncher() {
        return this.confirmCredentialActivityLauncher;
    }

    public final Executor getClientExecutor() {
        return this.clientExecutor;
    }

    public final BiometricPrompt.AuthenticationCallback getClientAuthenticationCallback() {
        return this.clientAuthenticationCallback;
    }

    public final AuthenticationResultDispatcher getResultDispatcher() {
        return this.resultDispatcher;
    }

    static final Unit resultDispatcher$lambda$0(AuthenticationManager authenticationManager) {
        authenticationManager.dismiss();
        return Unit.INSTANCE;
    }

    public final AuthenticationUiStateObserver getUiStateObserver() {
        return this.uiStateObserver;
    }

    public final BiometricPrompt.LifecycleContainer getLifecycleContainer() {
        return this.lifecycleContainer;
    }

    public final Function0<Unit> isNegativeButtonPressPendingObserver() {
        return this.isNegativeButtonPressPendingObserver;
    }

    static final Unit isNegativeButtonPressPendingObserver$lambda$0(AuthenticationManager authenticationManager) {
        AuthenticationRequest.Biometric.Fallback singleFallbackOption = authenticationManager.viewModel.getSingleFallbackOption();
        if (singleFallbackOption instanceof AuthenticationRequest.Biometric.Fallback.OverriddenDeviceCredential) {
            authenticationManager.resultDispatcher.showKMAsFallback();
        } else if (singleFallbackOption instanceof AuthenticationRequest.Biometric.Fallback.DefaultCancel) {
            authenticationManager.resultDispatcher.onAuthenticationError(5, authenticationManager.context.getString(R.string.generic_error_user_canceled));
            authenticationManager.cancelAuthentication(CanceledFrom.USER);
        } else if (singleFallbackOption instanceof AuthenticationRequest.Biometric.Fallback.CustomOption) {
            AuthenticationResultDispatcher authenticationResultDispatcher = authenticationManager.resultDispatcher;
            AuthenticationRequest.Biometric.Fallback singleFallbackOption2 = authenticationManager.viewModel.getSingleFallbackOption();
            Intrinsics.checkNotNull(singleFallbackOption2, "null cannot be cast to non-null type androidx.biometric.AuthenticationRequest.Biometric.Fallback.CustomOption");
            authenticationResultDispatcher.sendFallbackOptionAndDismiss((AuthenticationRequest.Biometric.Fallback.CustomOption) singleFallbackOption2);
            authenticationManager.cancelAuthentication(CanceledFrom.NEGATIVE_BUTTON);
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void initialize$default(AuthenticationManager authenticationManager, AuthenticationResultDispatcher authenticationResultDispatcher, AuthenticationUiStateObserver authenticationUiStateObserver, int i, Object obj) {
        if ((i & 1) != 0) {
            authenticationResultDispatcher = authenticationManager.resultDispatcher;
        }
        if ((i & 2) != 0) {
            authenticationUiStateObserver = authenticationManager.uiStateObserver;
        }
        authenticationManager.initialize(authenticationResultDispatcher, authenticationUiStateObserver);
    }

    public final void initialize(AuthenticationResultDispatcher resultDispatcher, AuthenticationUiStateObserver uiStateObserver) {
        if (resultDispatcher != null) {
            this.resultDispatcher = resultDispatcher;
        }
        if (uiStateObserver != null) {
            this.uiStateObserver = uiStateObserver;
        }
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;
        this.lifecycleContainer.addObserver(new LifecycleEventObserver() { // from class: androidx.biometric.internal.AuthenticationManager$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                AuthenticationManager.initialize$lambda$2(this.f$0, lifecycleOwner, event);
            }
        });
    }

    static final void initialize$lambda$2(AuthenticationManager authenticationManager, LifecycleOwner owner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(event, "event");
        int i = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i == 1) {
            authenticationManager.startObservingAuth();
            return;
        }
        if (i == 2) {
            if (!AuthenticationManagerKt.isPermanentlyRemoved(owner, event) || authenticationManager.viewModel.isConfirmingDeviceCredential()) {
                return;
            }
            authenticationManager.cancelAuthentication(CanceledFrom.INTERNAL);
            return;
        }
        if (i != 3) {
            return;
        }
        authenticationManager.stopObservingAuth();
        authenticationManager.viewModel.resetHandlerKey();
        authenticationManager.lifecycleContainer.clearObservers();
    }

    public final void authenticate(BiometricPrompt.PromptInfo info, BiometricPrompt.CryptoObject crypto, Function0<Unit> showAuthentication) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(showAuthentication, "showAuthentication");
        startObservingAuth();
        this.viewModel.setPromptInfo(info);
        this.viewModel.setIdentityCheckAvailable(BiometricManager.from(this.context).isIdentityCheckAvailable());
        this.viewModel.setCryptoObject(crypto);
        this.viewModel.setCanceledFrom(CanceledFrom.INTERNAL);
        AuthenticationViewModel authenticationViewModel = this.viewModel;
        authenticationViewModel.setNegativeButtonTextOverride(DefaultAuthenticationHandlerKt.isManagingDeviceCredentialButton(this.context, authenticationViewModel.getAllowedAuthenticators()) ? this.context.getString(R.string.confirm_device_credential_password) : null);
        if (DefaultAuthenticationHandlerKt.isKeyguardManagerNeededForNoBiometric(this.context, this.viewModel.getAllowedAuthenticators())) {
            this.viewModel.setDelayingPrompt(false);
        }
        if (this.viewModel.isDelayingPrompt()) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.lifecycleOwner), null, null, new AnonymousClass1(showAuthentication, null), 3, null);
        } else {
            showPromptForAuthentication(showAuthentication);
        }
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.AuthenticationManager$authenticate$1, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticationManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.AuthenticationManager$authenticate$1", f = "AuthenticationManager.kt", i = {}, l = {210}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $showAuthentication;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Function0<Unit> function0, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$showAuthentication = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AuthenticationManager.this.new AnonymousClass1(this.$showAuthentication, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(600L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            AuthenticationManager.this.showPromptForAuthentication(this.$showAuthentication);
            return Unit.INSTANCE;
        }
    }

    public final void cancelAuthentication(CanceledFrom canceledFrom) {
        Intrinsics.checkNotNullParameter(canceledFrom, "canceledFrom");
        if (canceledFrom == CanceledFrom.CLIENT || !this.viewModel.isIgnoringCancel()) {
            this.viewModel.setCanceledFrom(canceledFrom);
            this.viewModel.getCancellationSignalProvider().cancel();
        }
    }

    public final void dismiss() {
        this.viewModel.setPromptShowing(false);
        this.viewModel.setConfirmingDeviceCredential(false);
        if (DeviceUtils.shouldDelayShowingPrompt(this.context, Build.MODEL)) {
            this.viewModel.setDelayingPrompt(true);
            this.viewModel.setDelayedDelayingPrompt(false, 600L);
        }
        stopObservingAuth();
        Function0<Unit> function0 = this.onDismissed;
        if (function0 != null) {
            function0.invoke();
        }
    }

    private final void startObservingAuth() {
        if (this.isAuthenticationPrepared) {
            return;
        }
        this.isAuthenticationPrepared = true;
        connectCallbackObservers();
        AuthenticationUiStateObserver authenticationUiStateObserver = this.uiStateObserver;
        if (authenticationUiStateObserver != null) {
            authenticationUiStateObserver.connectObservers();
        }
        if (Build.VERSION.SDK_INT == 29 && AuthenticatorUtils.isDeviceCredentialAllowed(this.viewModel.getAllowedAuthenticators())) {
            this.viewModel.setIgnoringCancel(true);
            this.viewModel.setDelayedIgnoringCancel(false, 250L);
        }
    }

    private final void stopObservingAuth() {
        this.isAuthenticationPrepared = false;
        disconnectCallbackObservers();
        AuthenticationUiStateObserver authenticationUiStateObserver = this.uiStateObserver;
        if (authenticationUiStateObserver != null) {
            authenticationUiStateObserver.disconnectObservers();
        }
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.AuthenticationManager$connectCallbackObservers$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthenticationManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.AuthenticationManager$connectCallbackObservers$1", f = "AuthenticationManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C01961(Continuation<? super C01961> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01961 c01961 = AuthenticationManager.this.new C01961(continuation);
            c01961.L$0 = obj;
            return c01961;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01961) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.biometric.internal.AuthenticationManager$connectCallbackObservers$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: AuthenticationManager.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.biometric.internal.AuthenticationManager$connectCallbackObservers$1$1", f = "AuthenticationManager.kt", i = {}, l = {284}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class C00021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ AuthenticationManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00021(AuthenticationManager authenticationManager, Continuation<? super C00021> continuation) {
                super(2, continuation);
                this.this$0 = authenticationManager;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00021(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow<BiometricPrompt.AuthenticationResult> authenticationResult = this.this$0.getViewModel().getAuthenticationResult();
                    final AuthenticationManager authenticationManager = this.this$0;
                    this.label = 1;
                    if (authenticationResult.collect(new FlowCollector() { // from class: androidx.biometric.internal.AuthenticationManager.connectCallbackObservers.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit((BiometricPrompt.AuthenticationResult) obj2, (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(BiometricPrompt.AuthenticationResult authenticationResult2, Continuation<? super Unit> continuation) {
                            authenticationManager.getResultDispatcher().onAuthenticationSucceeded(authenticationResult2);
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00021(AuthenticationManager.this, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass2(AuthenticationManager.this, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass3(AuthenticationManager.this, null), 3, null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* JADX INFO: renamed from: androidx.biometric.internal.AuthenticationManager$connectCallbackObservers$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: AuthenticationManager.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.biometric.internal.AuthenticationManager$connectCallbackObservers$1$2", f = "AuthenticationManager.kt", i = {}, l = {289}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ AuthenticationManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(AuthenticationManager authenticationManager, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.this$0 = authenticationManager;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow<BiometricErrorData> authenticationError = this.this$0.getViewModel().getAuthenticationError();
                    final AuthenticationManager authenticationManager = this.this$0;
                    this.label = 1;
                    if (authenticationError.collect(new FlowCollector() { // from class: androidx.biometric.internal.AuthenticationManager.connectCallbackObservers.1.2.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit((BiometricErrorData) obj2, (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(BiometricErrorData biometricErrorData, Continuation<? super Unit> continuation) {
                            authenticationManager.getResultDispatcher().onAuthenticationError(biometricErrorData.getErrorCode(), biometricErrorData.getErrorMessage());
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: androidx.biometric.internal.AuthenticationManager$connectCallbackObservers$1$3, reason: invalid class name */
        /* JADX INFO: compiled from: AuthenticationManager.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.biometric.internal.AuthenticationManager$connectCallbackObservers$1$3", f = "AuthenticationManager.kt", i = {}, l = {297}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ AuthenticationManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(AuthenticationManager authenticationManager, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.this$0 = authenticationManager;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow<Unit> flowIsAuthenticationFailurePending = this.this$0.getViewModel().isAuthenticationFailurePending();
                    final AuthenticationManager authenticationManager = this.this$0;
                    this.label = 1;
                    if (flowIsAuthenticationFailurePending.collect(new FlowCollector() { // from class: androidx.biometric.internal.AuthenticationManager.connectCallbackObservers.1.3.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit((Unit) obj2, (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(Unit unit, Continuation<? super Unit> continuation) {
                            authenticationManager.getResultDispatcher().onAuthenticationFailed();
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    private final void connectCallbackObservers() {
        this.callbackObserverJob = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.lifecycleOwner), null, null, new C01961(null), 3, null);
    }

    private final void disconnectCallbackObservers() {
        Job job = this.callbackObserverJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.callbackObserverJob = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPromptForAuthentication(Function0<Unit> showAuthentication) {
        showAuthentication.invoke();
        this.viewModel.setPromptShowing(true);
        this.viewModel.setAwaitingResult(true);
    }

    /* JADX INFO: compiled from: AuthenticationManager.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/biometric/internal/AuthenticationManager$Companion;", "", "<init>", "()V", "SHOW_PROMPT_DELAY_MS", "", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
