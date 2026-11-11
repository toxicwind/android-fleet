package androidx.biometric.internal.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.R;
import androidx.biometric.internal.FingerprintManagerCompat;
import androidx.biometric.internal.data.CanceledFrom;
import androidx.biometric.internal.viewmodel.AuthenticationViewModel;
import androidx.biometric.internal.viewmodel.AuthenticationViewModelFactory;
import androidx.biometric.internal.viewmodel.FingerprintDialogViewModel;
import androidx.biometric.utils.AuthenticatorUtils;
import androidx.biometric.utils.BiometricErrorData;
import androidx.biometric.utils.CryptoObjectUtils;
import androidx.biometric.utils.DeviceUtils;
import androidx.biometric.utils.ErrorUtils;
import androidx.core.content.ContextCompat;
import androidx.core.os.CancellationSignal;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.mediarouter.media.GlobalMediaRouter;
import java.util.concurrent.CancellationException;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
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
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: FingerprintDialogActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0003\b\u0007\u0018\u0000 /2\u00020\u0001:\u0001/B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001bH\u0014J\n\u0010\u001f\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010 \u001a\u00020\u001bH\u0002J\u0010\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u001bH\u0002J\b\u0010%\u001a\u00020\u001bH\u0002J\u0018\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0002J\u0012\u0010+\u001a\u00020\u001b2\b\u0010,\u001a\u0004\u0018\u00010-H\u0002J\b\u0010.\u001a\u00020\u001bH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u0017\u0010\u0018¨\u00060"}, d2 = {"Landroidx/biometric/internal/ui/FingerprintDialogActivity;", "Landroidx/activity/ComponentActivity;", "<init>", "()V", "fingerprintDialog", "Landroid/app/AlertDialog;", "resetDialogJob", "Lkotlinx/coroutines/Job;", "fingerprintIcon", "Landroid/widget/ImageView;", "helpMessageView", "Landroid/widget/TextView;", "errorTextColor", "", "normalTextColor", "fingerprintDialogViewModel", "Landroidx/biometric/internal/viewmodel/FingerprintDialogViewModel;", "getFingerprintDialogViewModel", "()Landroidx/biometric/internal/viewmodel/FingerprintDialogViewModel;", "fingerprintDialogViewModel$delegate", "Lkotlin/Lazy;", "authenticationViewModel", "Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "getAuthenticationViewModel", "()Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "authenticationViewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "showAlertDialog", "destroyAlertDialog", "cancelAuthentication", "canceledFrom", "Landroidx/biometric/internal/data/CanceledFrom;", "connectObservers", "showAuthentication", "authenticateWithFingerprint", "fingerprintManager", "Landroidx/biometric/internal/FingerprintManagerCompat;", "context", "Landroid/content/Context;", "showFingerprintErrorMessage", "errorMessage", "", "resetDialog", "Companion", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FingerprintDialogActivity extends ComponentActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final long DISMISS_INSTANTLY_DELAY_MS = 500;
    public static final int HIDE_DIALOG_DELAY_MS = 2000;
    private static final long MESSAGE_DISPLAY_TIME_MS = 2000;
    private static final String TAG = "FingerprintDialogController";
    private static ViewModelProvider.Factory fingerprintDialogViewModelFactory;

    /* JADX INFO: renamed from: authenticationViewModel$delegate, reason: from kotlin metadata */
    private final Lazy authenticationViewModel;
    private int errorTextColor;
    private AlertDialog fingerprintDialog;

    /* JADX INFO: renamed from: fingerprintDialogViewModel$delegate, reason: from kotlin metadata */
    private final Lazy fingerprintDialogViewModel;
    private ImageView fingerprintIcon;
    private TextView helpMessageView;
    private int normalTextColor;
    private Job resetDialogJob;

    public FingerprintDialogActivity() {
        final FingerprintDialogActivity fingerprintDialogActivity = this;
        final Function0 function0 = null;
        this.fingerprintDialogViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(FingerprintDialogViewModel.class), new Function0<ViewModelStore>() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return fingerprintDialogActivity.getViewModelStore();
            }
        }, new Function0() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FingerprintDialogActivity.fingerprintDialogViewModel_delegate$lambda$0(this.f$0);
            }
        }, new Function0<CreationExtras>() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? fingerprintDialogActivity.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
        this.authenticationViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(AuthenticationViewModel.class), new Function0<ViewModelStore>() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity$special$$inlined$viewModels$default$5
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return fingerprintDialogActivity.getViewModelStore();
            }
        }, new Function0() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FingerprintDialogActivity.authenticationViewModel_delegate$lambda$0();
            }
        }, new Function0<CreationExtras>() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity$special$$inlined$viewModels$default$6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? fingerprintDialogActivity.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
    }

    /* JADX INFO: compiled from: FingerprintDialogActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R&\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/biometric/internal/ui/FingerprintDialogActivity$Companion;", "", "<init>", "()V", "fingerprintDialogViewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getFingerprintDialogViewModelFactory$annotations", "getFingerprintDialogViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setFingerprintDialogViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "HIDE_DIALOG_DELAY_MS", "", "DISMISS_INSTANTLY_DELAY_MS", "", "MESSAGE_DISPLAY_TIME_MS", "TAG", "", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getFingerprintDialogViewModelFactory$annotations() {
        }

        private Companion() {
        }

        public final ViewModelProvider.Factory getFingerprintDialogViewModelFactory() {
            return FingerprintDialogActivity.fingerprintDialogViewModelFactory;
        }

        public final void setFingerprintDialogViewModelFactory(ViewModelProvider.Factory factory) {
            FingerprintDialogActivity.fingerprintDialogViewModelFactory = factory;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FingerprintDialogViewModel getFingerprintDialogViewModel() {
        return (FingerprintDialogViewModel) this.fingerprintDialogViewModel.getValue();
    }

    static final ViewModelProvider.Factory fingerprintDialogViewModel_delegate$lambda$0(FingerprintDialogActivity fingerprintDialogActivity) {
        ViewModelProvider.Factory factory = fingerprintDialogViewModelFactory;
        return factory == null ? fingerprintDialogActivity.getDefaultViewModelProviderFactory() : factory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AuthenticationViewModel getAuthenticationViewModel() {
        return (AuthenticationViewModel) this.authenticationViewModel.getValue();
    }

    static final ViewModelProvider.Factory authenticationViewModel_delegate$lambda$0() {
        return new AuthenticationViewModelFactory();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        int color;
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 26) {
            color = FingerprintDialogActivityKt.getThemedColorFor(this, Api26Impl.INSTANCE.getColorErrorAttr());
        } else {
            color = ContextCompat.getColor(this, R.color.biometric_error_color);
        }
        this.errorTextColor = color;
        this.normalTextColor = FingerprintDialogActivityKt.getThemedColorFor(this, android.R.attr.textColorSecondary);
        showAlertDialog();
        connectObservers();
        if (savedInstanceState == null) {
            showAuthentication();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        destroyAlertDialog();
        if (isChangingConfigurations()) {
            return;
        }
        cancelAuthentication(CanceledFrom.INTERNAL);
    }

    private final AlertDialog showAlertDialog() {
        String singleFallbackOptionText;
        FingerprintDialogActivity fingerprintDialogActivity = this;
        if (DeviceUtils.shouldHideFingerprintDialog(fingerprintDialogActivity, Build.MODEL)) {
            return null;
        }
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C01971(null), 3, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(fingerprintDialogActivity);
        builder.setTitle(getAuthenticationViewModel().getTitle());
        View viewInflate = LayoutInflater.from(builder.getContext()).inflate(R.layout.fingerprint_dialog_layout, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.fingerprint_subtitle);
        if (textView != null) {
            CharSequence subtitle = getAuthenticationViewModel().getSubtitle();
            if (TextUtils.isEmpty(subtitle)) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(subtitle);
            }
        }
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.fingerprint_description);
        if (textView2 != null) {
            CharSequence description = getAuthenticationViewModel().getDescription();
            if (TextUtils.isEmpty(description)) {
                textView2.setVisibility(8);
            } else {
                textView2.setVisibility(0);
                textView2.setText(description);
            }
        }
        this.fingerprintIcon = (ImageView) viewInflate.findViewById(R.id.fingerprint_icon);
        this.helpMessageView = (TextView) viewInflate.findViewById(R.id.fingerprint_error);
        if (AuthenticatorUtils.isDeviceCredentialAllowed(getAuthenticationViewModel().getAllowedAuthenticators())) {
            singleFallbackOptionText = getString(R.string.confirm_device_credential_password);
        } else {
            singleFallbackOptionText = getAuthenticationViewModel().getSingleFallbackOptionText();
        }
        builder.setNegativeButton(singleFallbackOptionText, new DialogInterface.OnClickListener() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.getAuthenticationViewModel().setNegativeButtonPressPending();
            }
        });
        builder.setView(viewInflate);
        AlertDialog alertDialogCreate = builder.create();
        this.fingerprintDialog = alertDialogCreate;
        if (alertDialogCreate != null) {
            alertDialogCreate.setCanceledOnTouchOutside(false);
            alertDialogCreate.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    FingerprintDialogActivity.showAlertDialog$lambda$1$0(this.f$0, dialogInterface);
                }
            });
            alertDialogCreate.show();
        }
        resetDialog();
        return this.fingerprintDialog;
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.ui.FingerprintDialogActivity$showAlertDialog$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FingerprintDialogActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.ui.FingerprintDialogActivity$showAlertDialog$1", f = "FingerprintDialogActivity.kt", i = {}, l = {142}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01971 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C01971(Continuation<? super C01971> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FingerprintDialogActivity.this.new C01971(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01971) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(500L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            FingerprintDialogActivity.this.getFingerprintDialogViewModel().setDismissedInstantly(false);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialog$lambda$1$0(FingerprintDialogActivity fingerprintDialogActivity, DialogInterface dialogInterface) {
        fingerprintDialogActivity.getFingerprintDialogViewModel().setCancelPending(true);
    }

    private final void destroyAlertDialog() {
        AlertDialog alertDialog = this.fingerprintDialog;
        if (alertDialog != null) {
            try {
                try {
                    alertDialog.dismiss();
                    Unit unit = Unit.INSTANCE;
                } catch (Exception e) {
                    Integer.valueOf(Log.e(TAG, "Error dismissing fingerprint dialog", e));
                }
                this.fingerprintDialog = null;
            } catch (Throwable th) {
                this.fingerprintDialog = null;
                throw th;
            }
        }
        getFingerprintDialogViewModel().setDismissedInstantly(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelAuthentication(CanceledFrom canceledFrom) {
        if (canceledFrom == CanceledFrom.USER) {
            getAuthenticationViewModel().setAuthenticationError(new BiometricErrorData(10, ErrorUtils.getFingerprintErrorString(this, 10)));
        }
        getAuthenticationViewModel().getCancellationSignalProvider().cancel();
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$1, reason: invalid class name */
    /* JADX INFO: compiled from: FingerprintDialogActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$1", f = "FingerprintDialogActivity.kt", i = {}, l = {240}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FingerprintDialogActivity.this.new AnonymousClass1(continuation);
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
                Flow<Boolean> flowIsCancelPending = FingerprintDialogActivity.this.getFingerprintDialogViewModel().isCancelPending();
                final FingerprintDialogActivity fingerprintDialogActivity = FingerprintDialogActivity.this;
                this.label = 1;
                if (flowIsCancelPending.collect(new FlowCollector() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity.connectObservers.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit(((Boolean) obj2).booleanValue(), (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(boolean z, Continuation<? super Unit> continuation) {
                        if (z) {
                            fingerprintDialogActivity.cancelAuthentication(CanceledFrom.USER);
                            fingerprintDialogActivity.finish();
                        }
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

    private final void connectObservers() {
        FingerprintDialogActivity fingerprintDialogActivity = this;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fingerprintDialogActivity), null, null, new AnonymousClass1(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fingerprintDialogActivity), null, null, new AnonymousClass2(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fingerprintDialogActivity), null, null, new AnonymousClass3(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fingerprintDialogActivity), null, null, new AnonymousClass4(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fingerprintDialogActivity), null, null, new AnonymousClass5(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fingerprintDialogActivity), null, null, new AnonymousClass6(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fingerprintDialogActivity), null, null, new AnonymousClass7(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fingerprintDialogActivity), null, null, new AnonymousClass8(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fingerprintDialogActivity), null, null, new AnonymousClass9(null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$2, reason: invalid class name */
    /* JADX INFO: compiled from: FingerprintDialogActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$2", f = "FingerprintDialogActivity.kt", i = {}, l = {249}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FingerprintDialogActivity.this.new AnonymousClass2(continuation);
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
                StateFlow<FingerprintDialogState> state = FingerprintDialogActivity.this.getFingerprintDialogViewModel().getState();
                final FingerprintDialogActivity fingerprintDialogActivity = FingerprintDialogActivity.this;
                this.label = 1;
                if (state.collect(new FlowCollector() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity.connectObservers.2.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((FingerprintDialogState) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(FingerprintDialogState fingerprintDialogState, Continuation<? super Unit> continuation) {
                        if (fingerprintDialogActivity.getFingerprintDialogViewModel().get_previousState() != FingerprintDialogState.NONE || fingerprintDialogState != FingerprintDialogState.FINGERPRINT) {
                            Job job = fingerprintDialogActivity.resetDialogJob;
                            if (job != null) {
                                Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
                            }
                            FingerprintDialogActivity fingerprintDialogActivity2 = fingerprintDialogActivity;
                            fingerprintDialogActivity2.resetDialogJob = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fingerprintDialogActivity2), null, null, new C00071(fingerprintDialogActivity, null), 3, null);
                        }
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$2$1$1, reason: invalid class name and collision with other inner class name */
                    /* JADX INFO: compiled from: FingerprintDialogActivity.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$2$1$1", f = "FingerprintDialogActivity.kt", i = {}, l = {257}, m = "invokeSuspend", n = {}, s = {}, v = 1)
                    static final class C00071 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        int label;
                        final /* synthetic */ FingerprintDialogActivity this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C00071(FingerprintDialogActivity fingerprintDialogActivity, Continuation<? super C00071> continuation) {
                            super(2, continuation);
                            this.this$0 = fingerprintDialogActivity;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new C00071(this.this$0, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C00071) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                this.label = 1;
                                if (DelayKt.delay(2000L, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            this.this$0.resetDialog();
                            this.this$0.resetDialogJob = null;
                            return Unit.INSTANCE;
                        }
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
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$3, reason: invalid class name */
    /* JADX INFO: compiled from: FingerprintDialogActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$3", f = "FingerprintDialogActivity.kt", i = {}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_DISCONNECTED}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FingerprintDialogActivity.this.new AnonymousClass3(continuation);
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
                StateFlow<Integer> drawableResId = FingerprintDialogActivity.this.getFingerprintDialogViewModel().getDrawableResId();
                final FingerprintDialogActivity fingerprintDialogActivity = FingerprintDialogActivity.this;
                this.label = 1;
                if (drawableResId.collect(new FlowCollector() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity.connectObservers.3.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit(((Number) obj2).intValue(), (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(int i2, Continuation<? super Unit> continuation) {
                        if (i2 != 0) {
                            Drawable drawable = ContextCompat.getDrawable(fingerprintDialogActivity, i2);
                            ImageView imageView = fingerprintDialogActivity.fingerprintIcon;
                            if (imageView != null) {
                                imageView.setImageDrawable(drawable);
                            }
                            if (fingerprintDialogActivity.getFingerprintDialogViewModel().getShouldAnimateIcon().getValue().booleanValue() && drawable != null) {
                                Api21ImplForFm.INSTANCE.startAnimation(drawable);
                            }
                        }
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
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$4, reason: invalid class name */
    /* JADX INFO: compiled from: FingerprintDialogActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$4", f = "FingerprintDialogActivity.kt", i = {}, l = {278}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FingerprintDialogActivity.this.new AnonymousClass4(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow<Pair<CharSequence, Boolean>> helpMessageInfo = FingerprintDialogActivity.this.getFingerprintDialogViewModel().getHelpMessageInfo();
                final FingerprintDialogActivity fingerprintDialogActivity = FingerprintDialogActivity.this;
                this.label = 1;
                if (helpMessageInfo.collect(new FlowCollector() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity.connectObservers.4.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((Pair<? extends CharSequence, Boolean>) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(Pair<? extends CharSequence, Boolean> pair, Continuation<? super Unit> continuation) {
                        CharSequence charSequenceComponent1 = pair.component1();
                        boolean zBooleanValue = pair.component2().booleanValue();
                        TextView textView = fingerprintDialogActivity.helpMessageView;
                        if (!Intrinsics.areEqual(textView != null ? textView.getText() : null, charSequenceComponent1)) {
                            TextView textView2 = fingerprintDialogActivity.helpMessageView;
                            if (textView2 != null) {
                                textView2.setText(charSequenceComponent1);
                            }
                            TextView textView3 = fingerprintDialogActivity.helpMessageView;
                            if (textView3 != null) {
                                FingerprintDialogActivity fingerprintDialogActivity2 = fingerprintDialogActivity;
                                textView3.setTextColor(zBooleanValue ? fingerprintDialogActivity2.errorTextColor : fingerprintDialogActivity2.normalTextColor);
                            }
                        }
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
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$5, reason: invalid class name */
    /* JADX INFO: compiled from: FingerprintDialogActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$5", f = "FingerprintDialogActivity.kt", i = {}, l = {290}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass5(Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FingerprintDialogActivity.this.new AnonymousClass5(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<CharSequence> authenticationHelpMessage = FingerprintDialogActivity.this.getAuthenticationViewModel().getAuthenticationHelpMessage();
                final FingerprintDialogActivity fingerprintDialogActivity = FingerprintDialogActivity.this;
                this.label = 1;
                if (authenticationHelpMessage.collect(new FlowCollector() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity.connectObservers.5.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((CharSequence) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(CharSequence charSequence, Continuation<? super Unit> continuation) {
                        if (charSequence != null) {
                            fingerprintDialogActivity.showFingerprintErrorMessage(charSequence);
                        }
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

    /* JADX INFO: renamed from: androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$6, reason: invalid class name */
    /* JADX INFO: compiled from: FingerprintDialogActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$6", f = "FingerprintDialogActivity.kt", i = {}, l = {296}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass6(Continuation<? super AnonymousClass6> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FingerprintDialogActivity.this.new AnonymousClass6(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<Unit> flowIsNegativeButtonPressPending = FingerprintDialogActivity.this.getAuthenticationViewModel().isNegativeButtonPressPending();
                final FingerprintDialogActivity fingerprintDialogActivity = FingerprintDialogActivity.this;
                this.label = 1;
                if (flowIsNegativeButtonPressPending.collect(new FlowCollector() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity.connectObservers.6.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((Unit) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(Unit unit, Continuation<? super Unit> continuation) {
                        fingerprintDialogActivity.finish();
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

    /* JADX INFO: renamed from: androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$7, reason: invalid class name */
    /* JADX INFO: compiled from: FingerprintDialogActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$7", f = "FingerprintDialogActivity.kt", i = {}, l = {299}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass7(Continuation<? super AnonymousClass7> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FingerprintDialogActivity.this.new AnonymousClass7(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<BiometricPrompt.AuthenticationResult> authenticationResult = FingerprintDialogActivity.this.getAuthenticationViewModel().getAuthenticationResult();
                final FingerprintDialogActivity fingerprintDialogActivity = FingerprintDialogActivity.this;
                this.label = 1;
                if (authenticationResult.collect(new FlowCollector() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity.connectObservers.7.1
                    public final Object emit(BiometricPrompt.AuthenticationResult authenticationResult2, Continuation<? super Unit> continuation) {
                        fingerprintDialogActivity.finish();
                        return Unit.INSTANCE;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((BiometricPrompt.AuthenticationResult) obj2, (Continuation<? super Unit>) continuation);
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

    /* JADX INFO: renamed from: androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$8, reason: invalid class name */
    /* JADX INFO: compiled from: FingerprintDialogActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$8", f = "FingerprintDialogActivity.kt", i = {}, l = {302}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass8 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass8(Continuation<? super AnonymousClass8> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FingerprintDialogActivity.this.new AnonymousClass8(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass8) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<BiometricErrorData> authenticationError = FingerprintDialogActivity.this.getAuthenticationViewModel().getAuthenticationError();
                final FingerprintDialogActivity fingerprintDialogActivity = FingerprintDialogActivity.this;
                this.label = 1;
                if (authenticationError.collect(new FlowCollector() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity.connectObservers.8.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((BiometricErrorData) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(BiometricErrorData biometricErrorData, Continuation<? super Unit> continuation) {
                        int knownErrorCodeForAuthenticate = ErrorUtils.toKnownErrorCodeForAuthenticate(biometricErrorData.getErrorCode());
                        boolean z = ErrorUtils.isLockoutError(knownErrorCodeForAuthenticate) && fingerprintDialogActivity.getAuthenticationViewModel().isOverriddenDeviceCredential();
                        boolean z2 = knownErrorCodeForAuthenticate == 5;
                        if (!z && !z2 && !fingerprintDialogActivity.getFingerprintDialogViewModel().getIsDismissedInstantly()) {
                            fingerprintDialogActivity.showFingerprintErrorMessage(biometricErrorData.getErrorMessage());
                            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fingerprintDialogActivity), null, null, new C00081(fingerprintDialogActivity, null), 3, null);
                        } else {
                            fingerprintDialogActivity.finish();
                        }
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$8$1$1, reason: invalid class name and collision with other inner class name */
                    /* JADX INFO: compiled from: FingerprintDialogActivity.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$8$1$1", f = "FingerprintDialogActivity.kt", i = {}, l = {320}, m = "invokeSuspend", n = {}, s = {}, v = 1)
                    static final class C00081 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        int label;
                        final /* synthetic */ FingerprintDialogActivity this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C00081(FingerprintDialogActivity fingerprintDialogActivity, Continuation<? super C00081> continuation) {
                            super(2, continuation);
                            this.this$0 = fingerprintDialogActivity;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new C00081(this.this$0, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C00081) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                this.label = 1;
                                if (DelayKt.delay(FingerprintDialogActivityKt.getDismissDialogDelay(this.this$0), this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            this.this$0.finish();
                            return Unit.INSTANCE;
                        }
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

    /* JADX INFO: renamed from: androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$9, reason: invalid class name */
    /* JADX INFO: compiled from: FingerprintDialogActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.ui.FingerprintDialogActivity$connectObservers$9", f = "FingerprintDialogActivity.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass9 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass9(Continuation<? super AnonymousClass9> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FingerprintDialogActivity.this.new AnonymousClass9(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass9) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<Unit> flowIsAuthenticationFailurePending = FingerprintDialogActivity.this.getAuthenticationViewModel().isAuthenticationFailurePending();
                final FingerprintDialogActivity fingerprintDialogActivity = FingerprintDialogActivity.this;
                this.label = 1;
                if (flowIsAuthenticationFailurePending.collect(new FlowCollector() { // from class: androidx.biometric.internal.ui.FingerprintDialogActivity.connectObservers.9.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((Unit) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(Unit unit, Continuation<? super Unit> continuation) {
                        FingerprintDialogActivity fingerprintDialogActivity2 = fingerprintDialogActivity;
                        fingerprintDialogActivity2.showFingerprintErrorMessage(fingerprintDialogActivity2.getString(R.string.fingerprint_not_recognized));
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

    private final void showAuthentication() {
        FingerprintManagerCompat fingerprintManagerCompatFrom = FingerprintManagerCompat.from(getApplicationContext());
        Intrinsics.checkNotNullExpressionValue(fingerprintManagerCompatFrom, "from(...)");
        int iIntValue = getFingerprintDialogViewModel().getFingerprintPreAuthChecker().invoke(fingerprintManagerCompatFrom).intValue();
        if (iIntValue != 0) {
            getAuthenticationViewModel().setAuthenticationError(new BiometricErrorData(iIntValue, ErrorUtils.getFingerprintErrorString(getApplicationContext(), iIntValue)));
            return;
        }
        getFingerprintDialogViewModel().setDismissedInstantly(true);
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        authenticateWithFingerprint(fingerprintManagerCompatFrom, applicationContext);
    }

    private final void authenticateWithFingerprint(FingerprintManagerCompat fingerprintManager, Context context) {
        FingerprintManagerCompat.CryptoObject cryptoObjectWrapForFingerprintManager = CryptoObjectUtils.wrapForFingerprintManager(getAuthenticationViewModel().getCryptoObject());
        CancellationSignal fingerprintCancellationSignal = getAuthenticationViewModel().getCancellationSignalProvider().getFingerprintCancellationSignal();
        Intrinsics.checkNotNullExpressionValue(fingerprintCancellationSignal, "getFingerprintCancellationSignal(...)");
        try {
            fingerprintManager.authenticate(cryptoObjectWrapForFingerprintManager, 0, fingerprintCancellationSignal, getAuthenticationViewModel().getAuthenticationCallbackProvider().getFingerprintCallback(), (Handler) null);
        } catch (NullPointerException e) {
            Log.e(TAG, "Got NPE while authenticating with fingerprint.", e);
            getAuthenticationViewModel().setAuthenticationError(new BiometricErrorData(1, ErrorUtils.getFingerprintErrorString(this, 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showFingerprintErrorMessage(CharSequence errorMessage) {
        if (errorMessage == null) {
            String string = getString(R.string.default_error_msg);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            errorMessage = string;
        }
        getFingerprintDialogViewModel().setState(FingerprintDialogState.FINGERPRINT_ERROR, errorMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetDialog() {
        FingerprintDialogViewModel fingerprintDialogViewModel = getFingerprintDialogViewModel();
        String string = getString(R.string.fingerprint_dialog_touch_sensor);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        fingerprintDialogViewModel.resetState(string);
    }
}
