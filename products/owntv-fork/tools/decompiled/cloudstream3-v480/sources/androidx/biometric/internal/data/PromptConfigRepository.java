package androidx.biometric.internal.data;

import androidx.biometric.AuthenticationRequest;
import androidx.biometric.BiometricPrompt;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: PromptConfigRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b`\u0018\u0000 32\u00020\u0001:\u00013J\u0012\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H&J\u001e\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u00020\u00152\u0006\u00100\u001a\u000201H¦@¢\u0006\u0002\u00102R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00010\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000fX¦\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\u00020\u0015X¦\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\u00020\u0015X¦\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u0018\u0010\u001b\u001a\u00020\u0015X¦\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0018R\u0018\u0010\u001d\u001a\u00020\u0015X¦\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\u0018\u0010\u001f\u001a\u00020\u0003X¦\u0004¢\u0006\f\u0012\u0004\b \u0010!\u001a\u0004\b\"\u0010\u0005R\u0018\u0010#\u001a\u00020\u0015X¦\u000e¢\u0006\f\u001a\u0004\b#\u0010\u0016\"\u0004\b$\u0010\u0018R\u0018\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00064À\u0006\u0001"}, d2 = {"Landroidx/biometric/internal/data/PromptConfigRepository;", "", "currentAuthenticationKey", "", "getCurrentAuthenticationKey", "()I", "setCurrentAuthenticationKey", "(I)V", "promptInfo", "Landroidx/biometric/BiometricPrompt$PromptInfo;", "getPromptInfo", "()Landroidx/biometric/BiometricPrompt$PromptInfo;", "setPromptInfo", "(Landroidx/biometric/BiometricPrompt$PromptInfo;)V", "cryptoObject", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "getCryptoObject", "()Landroidx/biometric/BiometricPrompt$CryptoObject;", "setCryptoObject", "(Landroidx/biometric/BiometricPrompt$CryptoObject;)V", "isPromptShowing", "", "()Z", "setPromptShowing", "(Z)V", "isConfirmingDeviceCredential", "setConfirmingDeviceCredential", "isDelayingPrompt", "setDelayingPrompt", "isUsingKeyguardManagerForBiometricAndCredential", "setUsingKeyguardManagerForBiometricAndCredential", "allowedAuthenticators", "getAllowedAuthenticators$annotations", "()V", "getAllowedAuthenticators", "isIdentityCheckAvailable", "setIdentityCheckAvailable", "fallbackOptionList", "", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;", "getFallbackOptionList", "()Ljava/util/List;", "setNegativeButtonTextOverride", "", "negativeButtonTextOverride", "", "setDelayedDelayingPrompt", "delayingPrompt", "delayedTime", "", "(ZJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface PromptConfigRepository {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: androidx.biometric.internal.data.PromptConfigRepository$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: PromptConfigRepository.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = PromptConfigRepository.INSTANCE;
        }

        public static /* synthetic */ void getAllowedAuthenticators$annotations() {
        }
    }

    /* JADX INFO: renamed from: getAllowedAuthenticators */
    int get_allowedAuthenticators();

    BiometricPrompt.CryptoObject getCryptoObject();

    int getCurrentAuthenticationKey();

    List<AuthenticationRequest.Biometric.Fallback> getFallbackOptionList();

    BiometricPrompt.PromptInfo getPromptInfo();

    /* JADX INFO: renamed from: isConfirmingDeviceCredential */
    boolean getIsConfirmingDeviceCredential();

    /* JADX INFO: renamed from: isDelayingPrompt */
    boolean getIsDelayingPrompt();

    /* JADX INFO: renamed from: isIdentityCheckAvailable */
    boolean getIsIdentityCheckAvailable();

    /* JADX INFO: renamed from: isPromptShowing */
    boolean getIsPromptShowing();

    /* JADX INFO: renamed from: isUsingKeyguardManagerForBiometricAndCredential */
    boolean getIsUsingKeyguardManagerForBiometricAndCredential();

    void setConfirmingDeviceCredential(boolean z);

    void setCryptoObject(BiometricPrompt.CryptoObject cryptoObject);

    void setCurrentAuthenticationKey(int i);

    Object setDelayedDelayingPrompt(boolean z, long j, Continuation<? super Unit> continuation);

    void setDelayingPrompt(boolean z);

    void setIdentityCheckAvailable(boolean z);

    void setNegativeButtonTextOverride(CharSequence negativeButtonTextOverride);

    void setPromptInfo(BiometricPrompt.PromptInfo promptInfo);

    void setPromptShowing(boolean z);

    void setUsingKeyguardManagerForBiometricAndCredential(boolean z);

    /* JADX INFO: compiled from: PromptConfigRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/biometric/internal/data/PromptConfigRepository$Companion;", "", "<init>", "()V", "instance", "Landroidx/biometric/internal/data/PromptConfigRepository;", "getInstance", "()Landroidx/biometric/internal/data/PromptConfigRepository;", "instance$delegate", "Lkotlin/Lazy;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* JADX INFO: renamed from: instance$delegate, reason: from kotlin metadata */
        private static final Lazy<PromptConfigRepositoryImpl> instance = LazyKt.lazy(new Function0() { // from class: androidx.biometric.internal.data.PromptConfigRepository$Companion$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PromptConfigRepository.Companion.instance_delegate$lambda$0();
            }
        });

        private Companion() {
        }

        static final PromptConfigRepositoryImpl instance_delegate$lambda$0() {
            return new PromptConfigRepositoryImpl();
        }

        public final PromptConfigRepository getInstance() {
            return instance.getValue();
        }
    }
}
