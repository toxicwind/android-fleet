package com.google.android.gms.signin;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class SignInOptions implements Api.ApiOptions.Optional {
    public static final SignInOptions DEFAULT = new SignInOptions(false, false, null, false, null, null, false, null, null);
    public final boolean zasm = false;
    public final boolean zasn = false;
    public final String zaso = null;
    public final boolean zasp = false;
    public final boolean zass = false;
    public final String zasq = null;
    public final String zasr = null;
    public final Long zast = null;
    public final Long zasu = null;

    public static final class zaa {
    }

    public SignInOptions(boolean z, boolean z2, String str, boolean z3, String str2, String str3, boolean z4, Long l, Long l2) {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SignInOptions)) {
            return false;
        }
        SignInOptions signInOptions = (SignInOptions) obj;
        return this.zasm == signInOptions.zasm && this.zasn == signInOptions.zasn && Objects.equal(this.zaso, signInOptions.zaso) && this.zasp == signInOptions.zasp && this.zass == signInOptions.zass && Objects.equal(this.zasq, signInOptions.zasq) && Objects.equal(this.zasr, signInOptions.zasr) && Objects.equal(this.zast, signInOptions.zast) && Objects.equal(this.zasu, signInOptions.zasu);
    }

    @Nullable
    public final Long getAuthApiSignInModuleVersion() {
        return this.zast;
    }

    @Nullable
    public final String getHostedDomain() {
        return this.zasq;
    }

    @Nullable
    public final String getLogSessionId() {
        return this.zasr;
    }

    @Nullable
    public final Long getRealClientLibraryVersion() {
        return this.zasu;
    }

    public final String getServerClientId() {
        return this.zaso;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.zasm), Boolean.valueOf(this.zasn), this.zaso, Boolean.valueOf(this.zasp), Boolean.valueOf(this.zass), this.zasq, this.zasr, this.zast, this.zasu});
    }

    public final boolean isForceCodeForRefreshToken() {
        return this.zasp;
    }

    public final boolean isIdTokenRequested() {
        return this.zasn;
    }

    public final boolean isOfflineAccessRequested() {
        return this.zasm;
    }

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", this.zasm);
        bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", this.zasn);
        bundle.putString("com.google.android.gms.signin.internal.serverClientId", this.zaso);
        bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
        bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", this.zasp);
        bundle.putString("com.google.android.gms.signin.internal.hostedDomain", this.zasq);
        bundle.putString("com.google.android.gms.signin.internal.logSessionId", this.zasr);
        bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", this.zass);
        Long l = this.zast;
        if (l != null) {
            bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", l.longValue());
        }
        Long l2 = this.zasu;
        if (l2 != null) {
            bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", l2.longValue());
        }
        return bundle;
    }

    public final boolean waitForAccessTokenRefresh() {
        return this.zass;
    }
}
