package androidx.biometric.internal.data;

import android.os.Build;
import androidx.biometric.AuthenticationRequest;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.utils.AuthenticatorUtils;
import androidx.biometric.utils.CryptoObjectUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DelayKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: PromptConfigRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\r\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010(H\u0016J\u001e\u00101\u001a\u00020/2\u0006\u00102\u001a\u00020\u00182\u0006\u00103\u001a\u000204H\u0096@¢\u0006\u0002\u00105J\b\u00106\u001a\u00020/H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR(\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R(\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\n\u001a\u0004\u0018\u00010\u0011@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR\u001a\u0010\u001e\u001a\u00020\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0019\"\u0004\b\u001f\u0010\u001bR\u001a\u0010 \u001a\u00020\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001bR$\u0010\"\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u0018@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0019\"\u0004\b#\u0010\u001bR\u000e\u0010$\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0007R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u00067"}, d2 = {"Landroidx/biometric/internal/data/PromptConfigRepositoryImpl;", "Landroidx/biometric/internal/data/PromptConfigRepository;", "<init>", "()V", "currentAuthenticationKey", "", "getCurrentAuthenticationKey", "()I", "setCurrentAuthenticationKey", "(I)V", ES6Iterator.VALUE_PROPERTY, "Landroidx/biometric/BiometricPrompt$PromptInfo;", "promptInfo", "getPromptInfo", "()Landroidx/biometric/BiometricPrompt$PromptInfo;", "setPromptInfo", "(Landroidx/biometric/BiometricPrompt$PromptInfo;)V", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "cryptoObject", "getCryptoObject", "()Landroidx/biometric/BiometricPrompt$CryptoObject;", "setCryptoObject", "(Landroidx/biometric/BiometricPrompt$CryptoObject;)V", "isPromptShowing", "", "()Z", "setPromptShowing", "(Z)V", "isConfirmingDeviceCredential", "setConfirmingDeviceCredential", "isDelayingPrompt", "setDelayingPrompt", "isUsingKeyguardManagerForBiometricAndCredential", "setUsingKeyguardManagerForBiometricAndCredential", "isIdentityCheckAvailable", "setIdentityCheckAvailable", "_allowedAuthenticators", "allowedAuthenticators", "getAllowedAuthenticators", "_negativeButtonTextOverride", "", "fallbackOptionList", "", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;", "getFallbackOptionList", "()Ljava/util/List;", "setNegativeButtonTextOverride", "", "negativeButtonTextOverride", "setDelayedDelayingPrompt", "delayingPrompt", "delayedTime", "", "(ZJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateConsolidatedAuthenticators", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PromptConfigRepositoryImpl implements PromptConfigRepository {
    private int _allowedAuthenticators;
    private CharSequence _negativeButtonTextOverride;
    private BiometricPrompt.CryptoObject cryptoObject;
    private int currentAuthenticationKey;
    private boolean isConfirmingDeviceCredential;
    private boolean isDelayingPrompt;
    private boolean isIdentityCheckAvailable;
    private boolean isPromptShowing;
    private boolean isUsingKeyguardManagerForBiometricAndCredential;
    private BiometricPrompt.PromptInfo promptInfo;

    /* JADX INFO: renamed from: androidx.biometric.internal.data.PromptConfigRepositoryImpl$setDelayedDelayingPrompt$1, reason: invalid class name */
    /* JADX INFO: compiled from: PromptConfigRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.data.PromptConfigRepositoryImpl", f = "PromptConfigRepository.kt", i = {0}, l = {171}, m = "setDelayedDelayingPrompt", n = {"delayingPrompt"}, s = {"Z$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PromptConfigRepositoryImpl.this.setDelayedDelayingPrompt(false, 0L, this);
        }
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public int getCurrentAuthenticationKey() {
        return this.currentAuthenticationKey;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public void setCurrentAuthenticationKey(int i) {
        this.currentAuthenticationKey = i;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public BiometricPrompt.PromptInfo getPromptInfo() {
        return this.promptInfo;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public void setPromptInfo(BiometricPrompt.PromptInfo promptInfo) {
        this.promptInfo = promptInfo;
        updateConsolidatedAuthenticators();
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public BiometricPrompt.CryptoObject getCryptoObject() {
        return this.cryptoObject;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public void setCryptoObject(BiometricPrompt.CryptoObject cryptoObject) {
        this.cryptoObject = cryptoObject;
        if (Build.VERSION.SDK_INT < 30 && getPromptInfo() != null) {
            BiometricPrompt.PromptInfo promptInfo = getPromptInfo();
            Intrinsics.checkNotNull(promptInfo);
            if (AuthenticatorUtils.isAtLeastStrength(promptInfo.getAllowedAuthenticators(), 15) && cryptoObject == null) {
                this.cryptoObject = CryptoObjectUtils.createFakeCryptoObject();
            }
        }
        updateConsolidatedAuthenticators();
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    /* JADX INFO: renamed from: isPromptShowing, reason: from getter */
    public boolean getIsPromptShowing() {
        return this.isPromptShowing;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public void setPromptShowing(boolean z) {
        this.isPromptShowing = z;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    /* JADX INFO: renamed from: isConfirmingDeviceCredential, reason: from getter */
    public boolean getIsConfirmingDeviceCredential() {
        return this.isConfirmingDeviceCredential;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public void setConfirmingDeviceCredential(boolean z) {
        this.isConfirmingDeviceCredential = z;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    /* JADX INFO: renamed from: isDelayingPrompt, reason: from getter */
    public boolean getIsDelayingPrompt() {
        return this.isDelayingPrompt;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public void setDelayingPrompt(boolean z) {
        this.isDelayingPrompt = z;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    /* JADX INFO: renamed from: isUsingKeyguardManagerForBiometricAndCredential, reason: from getter */
    public boolean getIsUsingKeyguardManagerForBiometricAndCredential() {
        return this.isUsingKeyguardManagerForBiometricAndCredential;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public void setUsingKeyguardManagerForBiometricAndCredential(boolean z) {
        this.isUsingKeyguardManagerForBiometricAndCredential = z;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    /* JADX INFO: renamed from: isIdentityCheckAvailable, reason: from getter */
    public boolean getIsIdentityCheckAvailable() {
        return this.isIdentityCheckAvailable;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public void setIdentityCheckAvailable(boolean z) {
        this.isIdentityCheckAvailable = z;
        updateConsolidatedAuthenticators();
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    /* JADX INFO: renamed from: getAllowedAuthenticators, reason: from getter */
    public int get_allowedAuthenticators() {
        return this._allowedAuthenticators;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public List<AuthenticationRequest.Biometric.Fallback> getFallbackOptionList() {
        CharSequence negativeButtonText;
        List<AuthenticationRequest.Biometric.Fallback> listListOf;
        CharSequence charSequence = this._negativeButtonTextOverride;
        if (charSequence != null && (listListOf = CollectionsKt.listOf(new AuthenticationRequest.Biometric.Fallback.OverriddenDeviceCredential(charSequence.toString()))) != null) {
            return listListOf;
        }
        BiometricPrompt.PromptInfo promptInfo = getPromptInfo();
        if (promptInfo != null && (negativeButtonText = promptInfo.getNegativeButtonText()) != null) {
            if (negativeButtonText.length() == 0) {
                negativeButtonText = null;
            }
            if (negativeButtonText != null) {
                return CollectionsKt.listOf(new AuthenticationRequest.Biometric.Fallback.CustomOption(negativeButtonText.toString(), 0, 2, null));
            }
        }
        BiometricPrompt.PromptInfo promptInfo2 = getPromptInfo();
        List<AuthenticationRequest.Biometric.Fallback> fallbackOptionList = promptInfo2 != null ? promptInfo2.getFallbackOptionList() : null;
        return fallbackOptionList == null ? CollectionsKt.emptyList() : fallbackOptionList;
    }

    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public void setNegativeButtonTextOverride(CharSequence negativeButtonTextOverride) {
        this._negativeButtonTextOverride = negativeButtonTextOverride;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.biometric.internal.data.PromptConfigRepository
    public Object setDelayedDelayingPrompt(boolean z, long j, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
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
            anonymousClass1.Z$0 = z;
            anonymousClass1.label = 1;
            if (DelayKt.delay(j, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            z = anonymousClass1.Z$0;
            ResultKt.throwOnFailure(obj);
        }
        setDelayingPrompt(z);
        return Unit.INSTANCE;
    }

    private final void updateConsolidatedAuthenticators() {
        if (getPromptInfo() == null) {
            return;
        }
        BiometricPrompt.PromptInfo promptInfo = getPromptInfo();
        Intrinsics.checkNotNull(promptInfo);
        this._allowedAuthenticators = AuthenticatorUtils.getConsolidatedAuthenticators(promptInfo, getCryptoObject(), getIsIdentityCheckAvailable());
    }
}
