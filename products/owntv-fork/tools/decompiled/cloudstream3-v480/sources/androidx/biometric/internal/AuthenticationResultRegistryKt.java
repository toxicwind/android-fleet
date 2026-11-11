package androidx.biometric.internal;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import androidx.biometric.AuthenticationRequest;
import androidx.biometric.AuthenticationResult;
import androidx.biometric.AuthenticationResultCallback;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.PromptContentItem;
import androidx.biometric.PromptContentViewWithMoreOptionsButton;
import androidx.biometric.PromptVerticalListContentView;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.cast.MediaTrack;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthenticationResultRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u008a\u0001\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dH\u0003\u001a\u0014\u0010\u001f\u001a\u00020\u0019*\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dH\u0000¨\u0006 "}, d2 = {"onLaunch", "", "Landroidx/biometric/BiometricPrompt;", "context", "Landroid/content/Context;", TvContractCompat.PARAM_INPUT, "Landroidx/biometric/AuthenticationRequest;", "createAuthenticationCallback", "Landroidx/biometric/BiometricPrompt$AuthenticationCallback;", "resultCallback", "Landroidx/biometric/AuthenticationResultCallback;", "authInternal", "title", "", MediaTrack.ROLE_SUBTITLE, "content", "Landroidx/biometric/AuthenticationRequest$BodyContent;", "logoBitmap", "Landroid/graphics/Bitmap;", "logoRes", "", "logoDescription", "minBiometricStrength", "Landroidx/biometric/AuthenticationRequest$Biometric$Strength;", "isConfirmationRequired", "", "cryptoObjectForCredentialOnly", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "authFallbacks", "", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;", "multipleFallbackOptionsValid", "biometric"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AuthenticationResultRegistryKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void onLaunch(BiometricPrompt biometricPrompt, Context context, AuthenticationRequest authenticationRequest) {
        if (authenticationRequest instanceof AuthenticationRequest.Biometric) {
            AuthenticationRequest.Biometric biometric = (AuthenticationRequest.Biometric) authenticationRequest;
            authInternal$default(biometricPrompt, context, biometric.getTitle(), biometric.getSubtitle(), biometric.getContent(), biometric.getLogoBitmap(), biometric.getLogoRes(), biometric.getLogoDescription(), biometric.getMinStrength(), biometric.getIsConfirmationRequired(), null, biometric.getAuthFallbacks(), 512, null);
        } else if (authenticationRequest instanceof AuthenticationRequest.Credential) {
            AuthenticationRequest.Credential credential = (AuthenticationRequest.Credential) authenticationRequest;
            authInternal$default(biometricPrompt, context, credential.getTitle(), credential.getSubtitle(), credential.getContent(), null, 0, null, null, false, credential.getCryptoObject(), null, 1520, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BiometricPrompt.AuthenticationCallback createAuthenticationCallback(final AuthenticationResultCallback authenticationResultCallback) {
        return new BiometricPrompt.AuthenticationCallback() { // from class: androidx.biometric.internal.AuthenticationResultRegistryKt.createAuthenticationCallback.1
            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationFailed() {
                authenticationResultCallback.onAuthAttemptFailed();
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                authenticationResultCallback.onAuthResult(new AuthenticationResult.Success(result.getCryptoObject(), result.getAuthenticationType()));
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                Intrinsics.checkNotNullParameter(errString, "errString");
                authenticationResultCallback.onAuthResult(new AuthenticationResult.Error(errorCode, errString));
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onFallbackSelected(AuthenticationRequest.Biometric.Fallback.CustomOption fallback) {
                Intrinsics.checkNotNullParameter(fallback, "fallback");
                authenticationResultCallback.onAuthResult(new AuthenticationResult.CustomFallbackSelected(fallback));
            }
        };
    }

    static /* synthetic */ void authInternal$default(BiometricPrompt biometricPrompt, Context context, String str, String str2, AuthenticationRequest.BodyContent bodyContent, Bitmap bitmap, int i, String str3, AuthenticationRequest.Biometric.Strength strength, boolean z, BiometricPrompt.CryptoObject cryptoObject, List list, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str2 = null;
        }
        if ((i2 & 8) != 0) {
            bodyContent = null;
        }
        if ((i2 & 16) != 0) {
            bitmap = null;
        }
        if ((i2 & 32) != 0) {
            i = 0;
        }
        if ((i2 & 64) != 0) {
            str3 = null;
        }
        if ((i2 & 128) != 0) {
            strength = null;
        }
        if ((i2 & 256) != 0) {
            z = true;
        }
        if ((i2 & 512) != 0) {
            cryptoObject = null;
        }
        if ((i2 & 1024) != 0) {
            list = null;
        }
        authInternal(biometricPrompt, context, str, str2, bodyContent, bitmap, i, str3, strength, z, cryptoObject, list);
    }

    private static final void authInternal(BiometricPrompt biometricPrompt, Context context, String str, String str2, AuthenticationRequest.BodyContent bodyContent, Bitmap bitmap, int i, String str3, AuthenticationRequest.Biometric.Strength strength, boolean z, BiometricPrompt.CryptoObject cryptoObject, List<? extends AuthenticationRequest.Biometric.Fallback> list) {
        BiometricPrompt.PromptInfo.Builder builder = new BiometricPrompt.PromptInfo.Builder();
        int authenticationType$biometric = strength != null ? strength.toAuthenticationType$biometric() : 32768;
        if (strength != null) {
            strength.toAuthenticationType$biometric();
            if (multipleFallbackOptionsValid(list)) {
                Intrinsics.checkNotNull(list);
            } else {
                List<? extends AuthenticationRequest.Biometric.Fallback> list2 = list;
                list = (list2 == null || list2.isEmpty()) ? CollectionsKt.listOf(authInternal$lambda$0$0$defaultCancelButton(context)) : CollectionsKt.listOf(CollectionsKt.first((List) list));
            }
            for (AuthenticationRequest.Biometric.Fallback fallback : list) {
                if (fallback instanceof AuthenticationRequest.Biometric.Fallback.DeviceCredential) {
                    authenticationType$biometric |= 32768;
                } else {
                    builder.addFallbackOption(fallback);
                }
            }
        }
        builder.setAllowedAuthenticators(authenticationType$biometric);
        if (bodyContent instanceof AuthenticationRequest.BodyContent.PlainText) {
            builder.setDescription(((AuthenticationRequest.BodyContent.PlainText) bodyContent).getDescription());
        } else if (bodyContent instanceof AuthenticationRequest.BodyContent.VerticalList) {
            PromptVerticalListContentView.Builder builder2 = new PromptVerticalListContentView.Builder();
            AuthenticationRequest.BodyContent.VerticalList verticalList = (AuthenticationRequest.BodyContent.VerticalList) bodyContent;
            Iterator<T> it = verticalList.getItems().iterator();
            while (it.hasNext()) {
                builder2.addListItem((PromptContentItem) it.next());
            }
            if (verticalList.getDescription() != null) {
                builder2.setDescription(verticalList.getDescription());
            }
            builder.setContentView(builder2.build());
        } else if (bodyContent instanceof AuthenticationRequest.BodyContent.ContentViewWithMoreOptionsButton) {
            PromptContentViewWithMoreOptionsButton.Builder builder3 = new PromptContentViewWithMoreOptionsButton.Builder();
            AuthenticationRequest.BodyContent.ContentViewWithMoreOptionsButton contentViewWithMoreOptionsButton = (AuthenticationRequest.BodyContent.ContentViewWithMoreOptionsButton) bodyContent;
            if (contentViewWithMoreOptionsButton.getDescription() != null) {
                builder3.setDescription(contentViewWithMoreOptionsButton.getDescription());
            }
            builder.setContentView(builder3.build());
        }
        if (i != 0) {
            builder.setLogoRes(i);
        } else if (bitmap != null) {
            builder.setLogoBitmap(bitmap);
        }
        if (str3 != null) {
            builder.setLogoDescription(str3);
        }
        builder.setTitle(str);
        builder.setSubtitle(str2);
        builder.setConfirmationRequired(z);
        if (strength instanceof AuthenticationRequest.Biometric.Strength.Class3) {
            cryptoObject = ((AuthenticationRequest.Biometric.Strength.Class3) strength).getCryptoObject();
        } else if (strength != null) {
            cryptoObject = null;
        }
        if (cryptoObject == null) {
            biometricPrompt.authenticate(builder.build());
        } else {
            biometricPrompt.authenticate(builder.build(), cryptoObject);
        }
    }

    private static final AuthenticationRequest.Biometric.Fallback authInternal$lambda$0$0$defaultCancelButton(Context context) {
        String string = context.getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return new AuthenticationRequest.Biometric.Fallback.DefaultCancel(string);
    }

    public static final boolean multipleFallbackOptionsValid(List<? extends AuthenticationRequest.Biometric.Fallback> list) {
        return Build.VERSION.SDK_INT >= 36 && AuthenticationResultRegistryKt$$ExternalSyntheticBackportWithForwarding1.m() >= 3600001 && list != null && list.size() > 1;
    }
}
