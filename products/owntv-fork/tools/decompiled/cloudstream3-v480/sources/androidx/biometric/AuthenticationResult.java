package androidx.biometric;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthenticationResult.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bv\u0018\u00002\u00020\u0001:\u0003\f\r\u000eJ\b\u0010\u0002\u001a\u00020\u0003H\u0016J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016\u0082\u0001\u0003\u000b\b\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Landroidx/biometric/AuthenticationResult;", "", "isSuccess", "", "success", "Landroidx/biometric/AuthenticationResult$Success;", "isError", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "Landroidx/biometric/AuthenticationResult$Error;", "isCustomFallbackSelected", "customFallbackSelected", "Landroidx/biometric/AuthenticationResult$CustomFallbackSelected;", "Success", "Error", "CustomFallbackSelected", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface AuthenticationResult {
    CustomFallbackSelected customFallbackSelected();

    Error error();

    boolean isCustomFallbackSelected();

    boolean isError();

    boolean isSuccess();

    Success success();

    /* JADX INFO: compiled from: AuthenticationResult.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\u0000H\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Landroidx/biometric/AuthenticationResult$Success;", "Landroidx/biometric/AuthenticationResult;", "crypto", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "authType", "", "<init>", "(Landroidx/biometric/BiometricPrompt$CryptoObject;I)V", "getCrypto", "()Landroidx/biometric/BiometricPrompt$CryptoObject;", "getAuthType", "()I", "success", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Success implements AuthenticationResult {
        private final int authType;
        private final BiometricPrompt.CryptoObject crypto;

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ CustomFallbackSelected customFallbackSelected() {
            return CC.$default$customFallbackSelected(this);
        }

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ Error error() {
            return CC.$default$error(this);
        }

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ boolean isCustomFallbackSelected() {
            return CC.$default$isCustomFallbackSelected(this);
        }

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ boolean isError() {
            return CC.$default$isError(this);
        }

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ boolean isSuccess() {
            return CC.$default$isSuccess(this);
        }

        @Override // androidx.biometric.AuthenticationResult
        public Success success() {
            return this;
        }

        public Success(BiometricPrompt.CryptoObject cryptoObject, int i) {
            this.crypto = cryptoObject;
            this.authType = i;
        }

        public final BiometricPrompt.CryptoObject getCrypto() {
            return this.crypto;
        }

        public final int getAuthType() {
            return this.authType;
        }
    }

    /* JADX INFO: compiled from: AuthenticationResult.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\u0000H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Landroidx/biometric/AuthenticationResult$Error;", "Landroidx/biometric/AuthenticationResult;", "errorCode", "", "errString", "", "<init>", "(ILjava/lang/CharSequence;)V", "getErrorCode", "()I", "getErrString", "()Ljava/lang/CharSequence;", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Error implements AuthenticationResult {
        private final CharSequence errString;
        private final int errorCode;

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ CustomFallbackSelected customFallbackSelected() {
            return CC.$default$customFallbackSelected(this);
        }

        @Override // androidx.biometric.AuthenticationResult
        public Error error() {
            return this;
        }

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ boolean isCustomFallbackSelected() {
            return CC.$default$isCustomFallbackSelected(this);
        }

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ boolean isError() {
            return CC.$default$isError(this);
        }

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ boolean isSuccess() {
            return CC.$default$isSuccess(this);
        }

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ Success success() {
            return CC.$default$success(this);
        }

        public Error(int i, CharSequence errString) {
            Intrinsics.checkNotNullParameter(errString, "errString");
            this.errorCode = i;
            this.errString = errString;
        }

        public final int getErrorCode() {
            return this.errorCode;
        }

        public final CharSequence getErrString() {
            return this.errString;
        }
    }

    /* JADX INFO: compiled from: AuthenticationResult.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\u0000H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Landroidx/biometric/AuthenticationResult$CustomFallbackSelected;", "Landroidx/biometric/AuthenticationResult;", "fallback", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;", "<init>", "(Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;)V", "getFallback", "()Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;", "customFallbackSelected", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CustomFallbackSelected implements AuthenticationResult {
        private final AuthenticationRequest.Biometric.Fallback.CustomOption fallback;

        @Override // androidx.biometric.AuthenticationResult
        public CustomFallbackSelected customFallbackSelected() {
            return this;
        }

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ Error error() {
            return CC.$default$error(this);
        }

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ boolean isCustomFallbackSelected() {
            return CC.$default$isCustomFallbackSelected(this);
        }

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ boolean isError() {
            return CC.$default$isError(this);
        }

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ boolean isSuccess() {
            return CC.$default$isSuccess(this);
        }

        @Override // androidx.biometric.AuthenticationResult
        public /* synthetic */ Success success() {
            return CC.$default$success(this);
        }

        public CustomFallbackSelected(AuthenticationRequest.Biometric.Fallback.CustomOption fallback) {
            Intrinsics.checkNotNullParameter(fallback, "fallback");
            this.fallback = fallback;
        }

        public final AuthenticationRequest.Biometric.Fallback.CustomOption getFallback() {
            return this.fallback;
        }
    }

    /* JADX INFO: renamed from: androidx.biometric.AuthenticationResult$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticationResult.kt */
    public final /* synthetic */ class CC {
        public static CustomFallbackSelected $default$customFallbackSelected(AuthenticationResult _this) {
            return null;
        }

        public static Error $default$error(AuthenticationResult _this) {
            return null;
        }

        public static Success $default$success(AuthenticationResult _this) {
            return null;
        }

        public static boolean $default$isSuccess(AuthenticationResult _this) {
            return _this instanceof Success;
        }

        public static boolean $default$isError(AuthenticationResult _this) {
            return _this instanceof Error;
        }

        public static boolean $default$isCustomFallbackSelected(AuthenticationResult _this) {
            return _this instanceof CustomFallbackSelected;
        }
    }
}
