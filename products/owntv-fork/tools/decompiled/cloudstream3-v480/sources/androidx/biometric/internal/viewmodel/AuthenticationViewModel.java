package androidx.biometric.internal.viewmodel;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import androidx.biometric.AuthenticationRequest;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.PromptContentView;
import androidx.biometric.internal.data.AuthenticationStateRepository;
import androidx.biometric.internal.data.CanceledFrom;
import androidx.biometric.internal.data.PromptConfigRepository;
import androidx.biometric.utils.AuthenticationCallbackProvider;
import androidx.biometric.utils.AuthenticatorUtils;
import androidx.biometric.utils.BiometricErrorData;
import androidx.biometric.utils.CancellationSignalProvider;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.media3.exoplayer.RendererCapabilities;
import com.google.android.gms.cast.MediaTrack;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: AuthenticationViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u000f\b\u0000\u0018\u00002\u00020\u0001:\b\u0095\u0001\u0096\u0001\u0097\u0001\u0098\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010i\u001a\u00020e2\u0006\u0010j\u001a\u00020kJ\u0007\u0010\u0082\u0001\u001a\u00020\tJ\u0007\u0010\u0083\u0001\u001a\u00020zJ\u0011\u0010\u0084\u0001\u001a\u00020z2\b\u0010,\u001a\u0004\u0018\u00010-J\u0012\u0010\u0085\u0001\u001a\u00020z2\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010AJ\u001a\u0010\u0087\u0001\u001a\u00020z2\u0007\u0010\u0088\u0001\u001a\u00020\u001b2\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001J\u001a\u0010\u008b\u0001\u001a\u00020z2\u0007\u0010\u008c\u0001\u001a\u00020\u001b2\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001J\u0011\u0010\u008d\u0001\u001a\u00020z2\b\u0010o\u001a\u0004\u0018\u00010qJ\u0011\u0010\u008e\u0001\u001a\u00020z2\b\u0010t\u001a\u0004\u0018\u00010uJ\u0011\u0010\u008f\u0001\u001a\u00020z2\b\u0010w\u001a\u0004\u0018\u00010AJ\u0007\u0010\u0090\u0001\u001a\u00020zJ\u0007\u0010\u0091\u0001\u001a\u00020zJ\u0010\u0010\u0092\u0001\u001a\u00020z2\u0007\u0010\u0093\u0001\u001a\u00020kJ\u0007\u0010\u0094\u0001\u001a\u00020zR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR(\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\u000f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\u00158F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\u001b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010 \u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\u001b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b \u0010\u001d\"\u0004\b!\u0010\u001fR$\u0010\"\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\u001b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\"\u0010\u001d\"\u0004\b#\u0010\u001fR$\u0010$\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\u001b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b$\u0010\u001d\"\u0004\b%\u0010\u001fR$\u0010&\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\u001b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b&\u0010\u001d\"\u0004\b'\u0010\u001fR$\u0010(\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\u001b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b(\u0010\u001d\"\u0004\b)\u0010\u001fR$\u0010*\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\u001b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b*\u0010\u001d\"\u0004\b+\u0010\u001fR\u0016\u0010,\u001a\u0004\u0018\u00010-8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0011\u00100\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b1\u0010\fR\u0017\u00102\u001a\u00020\t8F¢\u0006\f\u0012\u0004\b3\u00104\u001a\u0004\b5\u0010\fR\u0019\u00106\u001a\u0004\u0018\u0001078F¢\u0006\f\u0012\u0004\b8\u00104\u001a\u0004\b9\u0010:R\u0019\u0010;\u001a\u0004\u0018\u00010<8F¢\u0006\f\u0012\u0004\b=\u00104\u001a\u0004\b>\u0010?R\u0013\u0010@\u001a\u0004\u0018\u00010A8F¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0013\u0010D\u001a\u0004\u0018\u00010A8F¢\u0006\u0006\u001a\u0004\bE\u0010CR\u0013\u0010F\u001a\u0004\u0018\u00010A8F¢\u0006\u0006\u001a\u0004\bG\u0010CR\u0013\u0010H\u001a\u0004\u0018\u00010I8F¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0011\u0010L\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\bL\u0010\u001dR\u001a\u0010M\u001a\b\u0012\u0004\u0012\u00020O0N8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0019\u0010R\u001a\n\u0012\u0004\u0012\u00020O\u0018\u00010N8F¢\u0006\u0006\u001a\u0004\bS\u0010QR\u0013\u0010T\u001a\u0004\u0018\u00010O8F¢\u0006\u0006\u001a\u0004\bU\u0010VR\u0011\u0010W\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\bW\u0010\u001dR\u0013\u0010X\u001a\u0004\u0018\u00010A8F¢\u0006\u0006\u001a\u0004\bY\u0010CR\u0011\u0010Z\u001a\u00020[8F¢\u0006\u0006\u001a\u0004\b\\\u0010]R\u001b\u0010^\u001a\u00020_8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bb\u0010c\u001a\u0004\b`\u0010aR\u001b\u0010d\u001a\u00020e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bh\u0010c\u001a\u0004\bf\u0010gR\u001b\u0010l\u001a\u00020e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bn\u0010c\u001a\u0004\bm\u0010gR\u0017\u0010o\u001a\b\u0012\u0004\u0012\u00020q0p8F¢\u0006\u0006\u001a\u0004\br\u0010sR\u0017\u0010t\u001a\b\u0012\u0004\u0012\u00020u0p8F¢\u0006\u0006\u001a\u0004\bv\u0010sR\u0019\u0010w\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010A0p8F¢\u0006\u0006\u001a\u0004\bx\u0010sR\u0017\u0010y\u001a\b\u0012\u0004\u0012\u00020z0p8F¢\u0006\u0006\u001a\u0004\by\u0010sR\u0017\u0010{\u001a\b\u0012\u0004\u0012\u00020z0p8F¢\u0006\u0006\u001a\u0004\b{\u0010sR\u0017\u0010|\u001a\b\u0012\u0004\u0012\u00020k0p8F¢\u0006\u0006\u001a\u0004\b|\u0010sR\u0017\u0010}\u001a\b\u0012\u0004\u0012\u00020z0p8F¢\u0006\u0006\u001a\u0004\b}\u0010sR\u0014\u0010~\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u007f\u0010\fR\u0010\u0010\u0080\u0001\u001a\u00030\u0081\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0099\u0001"}, d2 = {"Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "Landroidx/lifecycle/ViewModel;", "promptConfigRepository", "Landroidx/biometric/internal/data/PromptConfigRepository;", "authenticationStateRepository", "Landroidx/biometric/internal/data/AuthenticationStateRepository;", "<init>", "(Landroidx/biometric/internal/data/PromptConfigRepository;Landroidx/biometric/internal/data/AuthenticationStateRepository;)V", ES6Iterator.VALUE_PROPERTY, "", "currentAuthenticationKey", "getCurrentAuthenticationKey", "()I", "setCurrentAuthenticationKey", "(I)V", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "cryptoObject", "getCryptoObject", "()Landroidx/biometric/BiometricPrompt$CryptoObject;", "setCryptoObject", "(Landroidx/biometric/BiometricPrompt$CryptoObject;)V", "Landroidx/biometric/internal/data/CanceledFrom;", "canceledFrom", "getCanceledFrom", "()Landroidx/biometric/internal/data/CanceledFrom;", "setCanceledFrom", "(Landroidx/biometric/internal/data/CanceledFrom;)V", "", "isPromptShowing", "()Z", "setPromptShowing", "(Z)V", "isAwaitingResult", "setAwaitingResult", "isConfirmingDeviceCredential", "setConfirmingDeviceCredential", "isDelayingPrompt", "setDelayingPrompt", "isIgnoringCancel", "setIgnoringCancel", "isUsingKeyguardManagerForBiometricAndCredential", "setUsingKeyguardManagerForBiometricAndCredential", "isIdentityCheckAvailable", "setIdentityCheckAvailable", "promptInfo", "Landroidx/biometric/BiometricPrompt$PromptInfo;", "getPromptInfo", "()Landroidx/biometric/BiometricPrompt$PromptInfo;", "allowedAuthenticators", "getAllowedAuthenticators", "logoRes", "getLogoRes$annotations", "()V", "getLogoRes", "logoBitmap", "Landroid/graphics/Bitmap;", "getLogoBitmap$annotations", "getLogoBitmap", "()Landroid/graphics/Bitmap;", "logoDescription", "", "getLogoDescription$annotations", "getLogoDescription", "()Ljava/lang/String;", "title", "", "getTitle", "()Ljava/lang/CharSequence;", MediaTrack.ROLE_SUBTITLE, "getSubtitle", "description", "getDescription", "contentView", "Landroidx/biometric/PromptContentView;", "getContentView", "()Landroidx/biometric/PromptContentView;", "isConfirmationRequired", "fallbackOptions", "", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;", "getFallbackOptions", "()Ljava/util/List;", "multipleFallbackOptionList", "getMultipleFallbackOptionList", "singleFallbackOption", "getSingleFallbackOption", "()Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;", "isOverriddenDeviceCredential", "singleFallbackOptionText", "getSingleFallbackOptionText", "cancellationSignalProvider", "Landroidx/biometric/utils/CancellationSignalProvider;", "getCancellationSignalProvider", "()Landroidx/biometric/utils/CancellationSignalProvider;", "authenticationCallbackProvider", "Landroidx/biometric/utils/AuthenticationCallbackProvider;", "getAuthenticationCallbackProvider", "()Landroidx/biometric/utils/AuthenticationCallbackProvider;", "authenticationCallbackProvider$delegate", "Lkotlin/Lazy;", "negativeButtonListener", "Landroid/content/DialogInterface$OnClickListener;", "getNegativeButtonListener", "()Landroid/content/DialogInterface$OnClickListener;", "negativeButtonListener$delegate", "fallbackOptionListener", "fallback", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;", "moreOptionsButtonListener", "getMoreOptionsButtonListener", "moreOptionsButtonListener$delegate", "authenticationResult", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/biometric/BiometricPrompt$AuthenticationResult;", "getAuthenticationResult", "()Lkotlinx/coroutines/flow/Flow;", "authenticationError", "Landroidx/biometric/utils/BiometricErrorData;", "getAuthenticationError", "authenticationHelpMessage", "getAuthenticationHelpMessage", "isAuthenticationFailurePending", "", "isNegativeButtonPressPending", "isFallbackOptionPressPending", "isMoreOptionsButtonPressPending", "inferredAuthenticationResultType", "getInferredAuthenticationResultType", "authHandlerKey", "Ljava/util/concurrent/atomic/AtomicInteger;", "generateNextHandlerKey", "resetHandlerKey", "setPromptInfo", "setNegativeButtonTextOverride", "negativeButtonTextOverride", "setDelayedDelayingPrompt", "delayingPrompt", "delayedTime", "", "setDelayedIgnoringCancel", "ignoringCancel", "setAuthenticationResult", "setAuthenticationError", "setAuthenticationHelpMessage", "setAuthenticationFailurePending", "setNegativeButtonPressPending", "setFallbackOptionPressPending", "fallbackOption", "setMoreOptionsButtonPressPending", "CallbackListener", "NegativeButtonListener", "FallbackOptionListener", "MoreOptionsButtonListener", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AuthenticationViewModel extends ViewModel {
    private final AtomicInteger authHandlerKey;

    /* JADX INFO: renamed from: authenticationCallbackProvider$delegate, reason: from kotlin metadata */
    private final Lazy authenticationCallbackProvider;
    private final AuthenticationStateRepository authenticationStateRepository;

    /* JADX INFO: renamed from: moreOptionsButtonListener$delegate, reason: from kotlin metadata */
    private final Lazy moreOptionsButtonListener;

    /* JADX INFO: renamed from: negativeButtonListener$delegate, reason: from kotlin metadata */
    private final Lazy negativeButtonListener;
    private final PromptConfigRepository promptConfigRepository;

    public static /* synthetic */ void getLogoBitmap$annotations() {
    }

    public static /* synthetic */ void getLogoDescription$annotations() {
    }

    public static /* synthetic */ void getLogoRes$annotations() {
    }

    public AuthenticationViewModel(PromptConfigRepository promptConfigRepository, AuthenticationStateRepository authenticationStateRepository) {
        Intrinsics.checkNotNullParameter(promptConfigRepository, "promptConfigRepository");
        Intrinsics.checkNotNullParameter(authenticationStateRepository, "authenticationStateRepository");
        this.promptConfigRepository = promptConfigRepository;
        this.authenticationStateRepository = authenticationStateRepository;
        this.authenticationCallbackProvider = LazyKt.lazy(new Function0() { // from class: androidx.biometric.internal.viewmodel.AuthenticationViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AuthenticationViewModel.authenticationCallbackProvider_delegate$lambda$0(this.f$0);
            }
        });
        this.negativeButtonListener = LazyKt.lazy(new Function0() { // from class: androidx.biometric.internal.viewmodel.AuthenticationViewModel$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AuthenticationViewModel.negativeButtonListener_delegate$lambda$0(this.f$0);
            }
        });
        this.moreOptionsButtonListener = LazyKt.lazy(new Function0() { // from class: androidx.biometric.internal.viewmodel.AuthenticationViewModel$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AuthenticationViewModel.moreOptionsButtonListener_delegate$lambda$0(this.f$0);
            }
        });
        this.authHandlerKey = new AtomicInteger();
    }

    public final int getCurrentAuthenticationKey() {
        return this.promptConfigRepository.getCurrentAuthenticationKey();
    }

    public final void setCurrentAuthenticationKey(int i) {
        this.promptConfigRepository.setCurrentAuthenticationKey(i);
    }

    public final BiometricPrompt.CryptoObject getCryptoObject() {
        return this.promptConfigRepository.getCryptoObject();
    }

    public final void setCryptoObject(BiometricPrompt.CryptoObject cryptoObject) {
        this.promptConfigRepository.setCryptoObject(cryptoObject);
    }

    public final CanceledFrom getCanceledFrom() {
        return this.authenticationStateRepository.getCanceledFrom();
    }

    public final void setCanceledFrom(CanceledFrom value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.authenticationStateRepository.setCanceledFrom(value);
    }

    public final boolean isPromptShowing() {
        return this.promptConfigRepository.getIsPromptShowing();
    }

    public final void setPromptShowing(boolean z) {
        this.promptConfigRepository.setPromptShowing(z);
    }

    public final boolean isAwaitingResult() {
        return this.authenticationStateRepository.isAwaitingResult();
    }

    public final void setAwaitingResult(boolean z) {
        this.authenticationStateRepository.setAwaitingResult(z);
    }

    public final boolean isConfirmingDeviceCredential() {
        return this.promptConfigRepository.getIsConfirmingDeviceCredential();
    }

    public final void setConfirmingDeviceCredential(boolean z) {
        this.promptConfigRepository.setConfirmingDeviceCredential(z);
    }

    public final boolean isDelayingPrompt() {
        return this.promptConfigRepository.getIsDelayingPrompt();
    }

    public final void setDelayingPrompt(boolean z) {
        this.promptConfigRepository.setDelayingPrompt(z);
    }

    public final boolean isIgnoringCancel() {
        return this.authenticationStateRepository.isIgnoringCancel();
    }

    public final void setIgnoringCancel(boolean z) {
        this.authenticationStateRepository.setIgnoringCancel(z);
    }

    public final boolean isUsingKeyguardManagerForBiometricAndCredential() {
        return this.promptConfigRepository.getIsUsingKeyguardManagerForBiometricAndCredential();
    }

    public final void setUsingKeyguardManagerForBiometricAndCredential(boolean z) {
        this.promptConfigRepository.setUsingKeyguardManagerForBiometricAndCredential(z);
    }

    public final boolean isIdentityCheckAvailable() {
        return this.promptConfigRepository.getIsIdentityCheckAvailable();
    }

    public final void setIdentityCheckAvailable(boolean z) {
        this.promptConfigRepository.setIdentityCheckAvailable(z);
    }

    private final BiometricPrompt.PromptInfo getPromptInfo() {
        return this.promptConfigRepository.getPromptInfo();
    }

    public final int getAllowedAuthenticators() {
        return this.promptConfigRepository.get_allowedAuthenticators();
    }

    public final int getLogoRes() {
        BiometricPrompt.PromptInfo promptInfo = getPromptInfo();
        if (promptInfo != null) {
            return promptInfo.getLogoRes();
        }
        return -1;
    }

    public final Bitmap getLogoBitmap() {
        BiometricPrompt.PromptInfo promptInfo = getPromptInfo();
        if (promptInfo != null) {
            return promptInfo.getLogoBitmap();
        }
        return null;
    }

    public final String getLogoDescription() {
        BiometricPrompt.PromptInfo promptInfo = getPromptInfo();
        if (promptInfo != null) {
            return promptInfo.getLogoDescription();
        }
        return null;
    }

    public final CharSequence getTitle() {
        BiometricPrompt.PromptInfo promptInfo = getPromptInfo();
        if (promptInfo != null) {
            return promptInfo.getTitle();
        }
        return null;
    }

    public final CharSequence getSubtitle() {
        BiometricPrompt.PromptInfo promptInfo = getPromptInfo();
        if (promptInfo != null) {
            return promptInfo.getSubtitle();
        }
        return null;
    }

    public final CharSequence getDescription() {
        BiometricPrompt.PromptInfo promptInfo = getPromptInfo();
        if (promptInfo != null) {
            return promptInfo.getDescription();
        }
        return null;
    }

    public final PromptContentView getContentView() {
        BiometricPrompt.PromptInfo promptInfo = getPromptInfo();
        if (promptInfo != null) {
            return promptInfo.getContentView();
        }
        return null;
    }

    public final boolean isConfirmationRequired() {
        BiometricPrompt.PromptInfo promptInfo = getPromptInfo();
        if (promptInfo != null) {
            return promptInfo.isConfirmationRequired();
        }
        return true;
    }

    private final List<AuthenticationRequest.Biometric.Fallback> getFallbackOptions() {
        return this.promptConfigRepository.getFallbackOptionList();
    }

    public final List<AuthenticationRequest.Biometric.Fallback> getMultipleFallbackOptionList() {
        List<AuthenticationRequest.Biometric.Fallback> fallbackOptions = getFallbackOptions();
        if (fallbackOptions.size() > 1) {
            return fallbackOptions;
        }
        return null;
    }

    public final AuthenticationRequest.Biometric.Fallback getSingleFallbackOption() {
        return (AuthenticationRequest.Biometric.Fallback) CollectionsKt.singleOrNull((List) getFallbackOptions());
    }

    public final boolean isOverriddenDeviceCredential() {
        return getSingleFallbackOption() instanceof AuthenticationRequest.Biometric.Fallback.OverriddenDeviceCredential;
    }

    public final CharSequence getSingleFallbackOptionText() {
        AuthenticationRequest.Biometric.Fallback singleFallbackOption = getSingleFallbackOption();
        if (singleFallbackOption instanceof AuthenticationRequest.Biometric.Fallback.OverriddenDeviceCredential) {
            return ((AuthenticationRequest.Biometric.Fallback.OverriddenDeviceCredential) singleFallbackOption).getText();
        }
        if (singleFallbackOption instanceof AuthenticationRequest.Biometric.Fallback.CustomOption) {
            return ((AuthenticationRequest.Biometric.Fallback.CustomOption) singleFallbackOption).getText();
        }
        if (singleFallbackOption instanceof AuthenticationRequest.Biometric.Fallback.DefaultCancel) {
            return ((AuthenticationRequest.Biometric.Fallback.DefaultCancel) singleFallbackOption).getText();
        }
        return null;
    }

    public final CancellationSignalProvider getCancellationSignalProvider() {
        return this.authenticationStateRepository.getCancellationSignalProvider();
    }

    public final AuthenticationCallbackProvider getAuthenticationCallbackProvider() {
        return (AuthenticationCallbackProvider) this.authenticationCallbackProvider.getValue();
    }

    static final AuthenticationCallbackProvider authenticationCallbackProvider_delegate$lambda$0(AuthenticationViewModel authenticationViewModel) {
        return new AuthenticationCallbackProvider(new CallbackListener(authenticationViewModel));
    }

    public final DialogInterface.OnClickListener getNegativeButtonListener() {
        return (DialogInterface.OnClickListener) this.negativeButtonListener.getValue();
    }

    static final NegativeButtonListener negativeButtonListener_delegate$lambda$0(AuthenticationViewModel authenticationViewModel) {
        return new NegativeButtonListener(authenticationViewModel);
    }

    public final DialogInterface.OnClickListener fallbackOptionListener(AuthenticationRequest.Biometric.Fallback.CustomOption fallback) {
        Intrinsics.checkNotNullParameter(fallback, "fallback");
        return new FallbackOptionListener(this, fallback);
    }

    public final DialogInterface.OnClickListener getMoreOptionsButtonListener() {
        return (DialogInterface.OnClickListener) this.moreOptionsButtonListener.getValue();
    }

    static final MoreOptionsButtonListener moreOptionsButtonListener_delegate$lambda$0(AuthenticationViewModel authenticationViewModel) {
        return new MoreOptionsButtonListener(authenticationViewModel);
    }

    public final Flow<BiometricPrompt.AuthenticationResult> getAuthenticationResult() {
        return this.authenticationStateRepository.getAuthenticationResult();
    }

    public final Flow<BiometricErrorData> getAuthenticationError() {
        return this.authenticationStateRepository.getAuthenticationError();
    }

    public final Flow<CharSequence> getAuthenticationHelpMessage() {
        return this.authenticationStateRepository.getAuthenticationHelpMessage();
    }

    public final Flow<Unit> isAuthenticationFailurePending() {
        return this.authenticationStateRepository.isAuthenticationFailurePending();
    }

    public final Flow<Unit> isNegativeButtonPressPending() {
        return this.authenticationStateRepository.isNegativeButtonPressPending();
    }

    public final Flow<AuthenticationRequest.Biometric.Fallback.CustomOption> isFallbackOptionPressPending() {
        return this.authenticationStateRepository.isFallbackOptionPressPending();
    }

    public final Flow<Unit> isMoreOptionsButtonPressPending() {
        return this.authenticationStateRepository.isMoreOptionsButtonPressPending();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getInferredAuthenticationResultType() {
        return (!AuthenticatorUtils.isSomeBiometricAllowed(getAllowedAuthenticators()) || AuthenticatorUtils.isDeviceCredentialAllowed(getAllowedAuthenticators())) ? -1 : 2;
    }

    public final int generateNextHandlerKey() {
        return this.authHandlerKey.incrementAndGet();
    }

    public final void resetHandlerKey() {
        this.authHandlerKey.set(0);
    }

    public final void setPromptInfo(BiometricPrompt.PromptInfo promptInfo) {
        this.promptConfigRepository.setPromptInfo(promptInfo);
    }

    public final void setNegativeButtonTextOverride(CharSequence negativeButtonTextOverride) {
        this.promptConfigRepository.setNegativeButtonTextOverride(negativeButtonTextOverride);
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.viewmodel.AuthenticationViewModel$setDelayedDelayingPrompt$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthenticationViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.viewmodel.AuthenticationViewModel$setDelayedDelayingPrompt$1", f = "AuthenticationViewModel.kt", i = {}, l = {328}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $delayedTime;
        final /* synthetic */ boolean $delayingPrompt;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02011(boolean z, long j, Continuation<? super C02011> continuation) {
            super(2, continuation);
            this.$delayingPrompt = z;
            this.$delayedTime = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AuthenticationViewModel.this.new C02011(this.$delayingPrompt, this.$delayedTime, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AuthenticationViewModel.this.promptConfigRepository.setDelayedDelayingPrompt(this.$delayingPrompt, this.$delayedTime, this) == coroutine_suspended) {
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

    public final void setDelayedDelayingPrompt(boolean delayingPrompt, long delayedTime) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C02011(delayingPrompt, delayedTime, null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.viewmodel.AuthenticationViewModel$setDelayedIgnoringCancel$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthenticationViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.viewmodel.AuthenticationViewModel$setDelayedIgnoringCancel$1", f = "AuthenticationViewModel.kt", i = {}, l = {341}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $delayedTime;
        final /* synthetic */ boolean $ignoringCancel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02021(boolean z, long j, Continuation<? super C02021> continuation) {
            super(2, continuation);
            this.$ignoringCancel = z;
            this.$delayedTime = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AuthenticationViewModel.this.new C02021(this.$ignoringCancel, this.$delayedTime, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AuthenticationViewModel.this.authenticationStateRepository.setDelayedIgnoringCancel(this.$ignoringCancel, this.$delayedTime, this) == coroutine_suspended) {
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

    public final void setDelayedIgnoringCancel(boolean ignoringCancel, long delayedTime) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C02021(ignoringCancel, delayedTime, null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.viewmodel.AuthenticationViewModel$setAuthenticationResult$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthenticationViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.viewmodel.AuthenticationViewModel$setAuthenticationResult$1", f = "AuthenticationViewModel.kt", i = {}, l = {348}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BiometricPrompt.AuthenticationResult $authenticationResult;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02001(BiometricPrompt.AuthenticationResult authenticationResult, Continuation<? super C02001> continuation) {
            super(2, continuation);
            this.$authenticationResult = authenticationResult;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AuthenticationViewModel.this.new C02001(this.$authenticationResult, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AuthenticationViewModel.this.authenticationStateRepository.setAuthenticationResult(this.$authenticationResult, this) == coroutine_suspended) {
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

    public final void setAuthenticationResult(BiometricPrompt.AuthenticationResult authenticationResult) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C02001(authenticationResult, null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.viewmodel.AuthenticationViewModel$setAuthenticationError$1, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticationViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.viewmodel.AuthenticationViewModel$setAuthenticationError$1", f = "AuthenticationViewModel.kt", i = {}, l = {355}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BiometricErrorData $authenticationError;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(BiometricErrorData biometricErrorData, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$authenticationError = biometricErrorData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AuthenticationViewModel.this.new AnonymousClass1(this.$authenticationError, continuation);
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
                if (AuthenticationViewModel.this.authenticationStateRepository.setAuthenticationError(this.$authenticationError, this) == coroutine_suspended) {
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

    public final void setAuthenticationError(BiometricErrorData authenticationError) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(authenticationError, null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.viewmodel.AuthenticationViewModel$setAuthenticationHelpMessage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthenticationViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.viewmodel.AuthenticationViewModel$setAuthenticationHelpMessage$1", f = "AuthenticationViewModel.kt", i = {}, l = {362}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01991 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CharSequence $authenticationHelpMessage;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01991(CharSequence charSequence, Continuation<? super C01991> continuation) {
            super(2, continuation);
            this.$authenticationHelpMessage = charSequence;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AuthenticationViewModel.this.new C01991(this.$authenticationHelpMessage, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01991) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AuthenticationViewModel.this.authenticationStateRepository.setAuthenticationHelpMessage(this.$authenticationHelpMessage, this) == coroutine_suspended) {
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

    public final void setAuthenticationHelpMessage(CharSequence authenticationHelpMessage) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C01991(authenticationHelpMessage, null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.viewmodel.AuthenticationViewModel$setAuthenticationFailurePending$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthenticationViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.viewmodel.AuthenticationViewModel$setAuthenticationFailurePending$1", f = "AuthenticationViewModel.kt", i = {}, l = {368}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01981 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C01981(Continuation<? super C01981> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AuthenticationViewModel.this.new C01981(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01981) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AuthenticationViewModel.this.authenticationStateRepository.setAuthenticationFailurePending(this) == coroutine_suspended) {
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

    public final void setAuthenticationFailurePending() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C01981(null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.viewmodel.AuthenticationViewModel$setNegativeButtonPressPending$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthenticationViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.viewmodel.AuthenticationViewModel$setNegativeButtonPressPending$1", f = "AuthenticationViewModel.kt", i = {}, l = {373}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C02051(Continuation<? super C02051> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AuthenticationViewModel.this.new C02051(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AuthenticationViewModel.this.authenticationStateRepository.setNegativeButtonPressPending(this) == coroutine_suspended) {
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

    public final void setNegativeButtonPressPending() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C02051(null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.viewmodel.AuthenticationViewModel$setFallbackOptionPressPending$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthenticationViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.viewmodel.AuthenticationViewModel$setFallbackOptionPressPending$1", f = "AuthenticationViewModel.kt", i = {}, l = {378}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AuthenticationRequest.Biometric.Fallback.CustomOption $fallbackOption;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02031(AuthenticationRequest.Biometric.Fallback.CustomOption customOption, Continuation<? super C02031> continuation) {
            super(2, continuation);
            this.$fallbackOption = customOption;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AuthenticationViewModel.this.new C02031(this.$fallbackOption, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AuthenticationViewModel.this.authenticationStateRepository.setFallbackOptionPressPending(this.$fallbackOption, this) == coroutine_suspended) {
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

    public final void setFallbackOptionPressPending(AuthenticationRequest.Biometric.Fallback.CustomOption fallbackOption) {
        Intrinsics.checkNotNullParameter(fallbackOption, "fallbackOption");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C02031(fallbackOption, null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.viewmodel.AuthenticationViewModel$setMoreOptionsButtonPressPending$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthenticationViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.viewmodel.AuthenticationViewModel$setMoreOptionsButtonPressPending$1", f = "AuthenticationViewModel.kt", i = {}, l = {RendererCapabilities.DECODER_SUPPORT_MASK}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02041 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C02041(Continuation<? super C02041> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AuthenticationViewModel.this.new C02041(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02041) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AuthenticationViewModel.this.authenticationStateRepository.setMoreOptionsButtonPressPending(this) == coroutine_suspended) {
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

    public final void setMoreOptionsButtonPressPending() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C02041(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AuthenticationViewModel.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001a\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0013\u001a\u00020\tH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/biometric/internal/viewmodel/AuthenticationViewModel$CallbackListener;", "Landroidx/biometric/utils/AuthenticationCallbackProvider$Listener;", "viewModel", "Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "<init>", "(Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;)V", "viewModelRef", "Ljava/lang/ref/WeakReference;", "onSuccess", "", "result", "Landroidx/biometric/BiometricPrompt$AuthenticationResult;", "onError", "errorCode", "", "errorMessage", "", "onHelp", "helpMessage", "onFailure", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    static final class CallbackListener extends AuthenticationCallbackProvider.Listener {
        private final WeakReference<AuthenticationViewModel> viewModelRef;

        public CallbackListener(AuthenticationViewModel authenticationViewModel) {
            this.viewModelRef = new WeakReference<>(authenticationViewModel);
        }

        @Override // androidx.biometric.utils.AuthenticationCallbackProvider.Listener
        public void onSuccess(BiometricPrompt.AuthenticationResult result) {
            Intrinsics.checkNotNullParameter(result, "result");
            AuthenticationViewModel authenticationViewModel = this.viewModelRef.get();
            if (authenticationViewModel == null || !authenticationViewModel.isAwaitingResult()) {
                return;
            }
            if (result.getAuthenticationType() == -1) {
                result = new BiometricPrompt.AuthenticationResult(result.getCryptoObject(), authenticationViewModel.getInferredAuthenticationResultType());
            }
            authenticationViewModel.setAuthenticationResult(result);
        }

        @Override // androidx.biometric.utils.AuthenticationCallbackProvider.Listener
        public void onError(int errorCode, CharSequence errorMessage) {
            AuthenticationViewModel authenticationViewModel = this.viewModelRef.get();
            if (authenticationViewModel == null || authenticationViewModel.isConfirmingDeviceCredential() || !authenticationViewModel.isAwaitingResult()) {
                return;
            }
            authenticationViewModel.setAuthenticationError(new BiometricErrorData(errorCode, errorMessage));
        }

        @Override // androidx.biometric.utils.AuthenticationCallbackProvider.Listener
        public void onHelp(CharSequence helpMessage) {
            AuthenticationViewModel authenticationViewModel = this.viewModelRef.get();
            if (authenticationViewModel != null) {
                authenticationViewModel.setAuthenticationHelpMessage(helpMessage);
            }
        }

        @Override // androidx.biometric.utils.AuthenticationCallbackProvider.Listener
        public void onFailure() {
            AuthenticationViewModel authenticationViewModel = this.viewModelRef.get();
            if (authenticationViewModel == null || !authenticationViewModel.isAwaitingResult()) {
                return;
            }
            authenticationViewModel.setAuthenticationFailurePending();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AuthenticationViewModel.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/biometric/internal/viewmodel/AuthenticationViewModel$NegativeButtonListener;", "Landroid/content/DialogInterface$OnClickListener;", "viewModel", "Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "<init>", "(Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;)V", "viewModelRef", "Ljava/lang/ref/WeakReference;", "onClick", "", "dialogInterface", "Landroid/content/DialogInterface;", "which", "", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    static final class NegativeButtonListener implements DialogInterface.OnClickListener {
        private final WeakReference<AuthenticationViewModel> viewModelRef;

        public NegativeButtonListener(AuthenticationViewModel authenticationViewModel) {
            this.viewModelRef = new WeakReference<>(authenticationViewModel);
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int which) {
            AuthenticationViewModel authenticationViewModel = this.viewModelRef.get();
            if (authenticationViewModel != null) {
                authenticationViewModel.setNegativeButtonPressPending();
            }
        }
    }

    /* JADX INFO: compiled from: AuthenticationViewModel.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/biometric/internal/viewmodel/AuthenticationViewModel$FallbackOptionListener;", "Landroid/content/DialogInterface$OnClickListener;", "viewModel", "Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "fallbackOption", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;", "<init>", "(Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;)V", "getFallbackOption", "()Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;", "viewModelRef", "Ljava/lang/ref/WeakReference;", "onClick", "", "dialogInterface", "Landroid/content/DialogInterface;", "which", "", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class FallbackOptionListener implements DialogInterface.OnClickListener {
        private final AuthenticationRequest.Biometric.Fallback.CustomOption fallbackOption;
        private final WeakReference<AuthenticationViewModel> viewModelRef;

        public FallbackOptionListener(AuthenticationViewModel authenticationViewModel, AuthenticationRequest.Biometric.Fallback.CustomOption fallbackOption) {
            Intrinsics.checkNotNullParameter(fallbackOption, "fallbackOption");
            this.fallbackOption = fallbackOption;
            this.viewModelRef = new WeakReference<>(authenticationViewModel);
        }

        public final AuthenticationRequest.Biometric.Fallback.CustomOption getFallbackOption() {
            return this.fallbackOption;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int which) {
            AuthenticationViewModel authenticationViewModel = this.viewModelRef.get();
            if (authenticationViewModel != null) {
                authenticationViewModel.setFallbackOptionPressPending(this.fallbackOption);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AuthenticationViewModel.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/biometric/internal/viewmodel/AuthenticationViewModel$MoreOptionsButtonListener;", "Landroid/content/DialogInterface$OnClickListener;", "viewModel", "Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "<init>", "(Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;)V", "viewModelRef", "Ljava/lang/ref/WeakReference;", "onClick", "", "dialogInterface", "Landroid/content/DialogInterface;", "which", "", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    static final class MoreOptionsButtonListener implements DialogInterface.OnClickListener {
        private final WeakReference<AuthenticationViewModel> viewModelRef;

        public MoreOptionsButtonListener(AuthenticationViewModel authenticationViewModel) {
            this.viewModelRef = new WeakReference<>(authenticationViewModel);
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int which) {
            AuthenticationViewModel authenticationViewModel = this.viewModelRef.get();
            if (authenticationViewModel != null) {
                authenticationViewModel.setMoreOptionsButtonPressPending();
            }
        }
    }
}
