package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import b.a.a.a.a;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.installations.Utils;
import e.b.b;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class Storage {
    public static final Lock zaai = new ReentrantLock();
    public static Storage zaaj;
    public final Lock zaak = new ReentrantLock();
    public final SharedPreferences zaal;

    @VisibleForTesting
    public Storage(Context context) {
        this.zaal = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    @KeepForSdk
    public static Storage getInstance(Context context) {
        Preconditions.checkNotNull(context);
        zaai.lock();
        try {
            if (zaaj == null) {
                zaaj = new Storage(context.getApplicationContext());
            }
            return zaaj;
        } finally {
            zaai.unlock();
        }
    }

    private final void zaa(String str, String str2) {
        this.zaak.lock();
        try {
            this.zaal.edit().putString(str, str2).apply();
        } finally {
            this.zaak.unlock();
        }
    }

    public static String zab(String str, String str2) {
        return a.a(a.a((Object) str2, a.a((Object) str, 1)), str, Utils.APP_ID_IDENTIFICATION_SUBSTRING, str2);
    }

    @VisibleForTesting
    private final GoogleSignInAccount zad(String str) {
        String strZaf;
        if (!TextUtils.isEmpty(str) && (strZaf = zaf(zab("googleSignInAccount", str))) != null) {
            try {
                return GoogleSignInAccount.zaa(strZaf);
            } catch (b unused) {
            }
        }
        return null;
    }

    @VisibleForTesting
    private final GoogleSignInOptions zae(String str) {
        String strZaf;
        if (!TextUtils.isEmpty(str) && (strZaf = zaf(zab("googleSignInOptions", str))) != null) {
            try {
                return GoogleSignInOptions.zab(strZaf);
            } catch (b unused) {
            }
        }
        return null;
    }

    private final String zaf(String str) {
        this.zaak.lock();
        try {
            return this.zaal.getString(str, null);
        } finally {
            this.zaak.unlock();
        }
    }

    private final void zag(String str) {
        this.zaak.lock();
        try {
            this.zaal.edit().remove(str).apply();
        } finally {
            this.zaak.unlock();
        }
    }

    @KeepForSdk
    public void clear() {
        this.zaak.lock();
        try {
            this.zaal.edit().clear().apply();
        } finally {
            this.zaak.unlock();
        }
    }

    @KeepForSdk
    public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
        return zad(zaf("defaultGoogleSignInAccount"));
    }

    @KeepForSdk
    public GoogleSignInOptions getSavedDefaultGoogleSignInOptions() {
        return zae(zaf("defaultGoogleSignInAccount"));
    }

    @KeepForSdk
    public String getSavedRefreshToken() {
        return zaf("refreshToken");
    }

    @KeepForSdk
    public void saveDefaultGoogleSignInAccount(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        zaa("defaultGoogleSignInAccount", googleSignInAccount.zab());
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        String strZab = googleSignInAccount.zab();
        zaa(zab("googleSignInAccount", strZab), googleSignInAccount.zac());
        zaa(zab("googleSignInOptions", strZab), googleSignInOptions.zae());
    }

    public final void zaf() {
        String strZaf = zaf("defaultGoogleSignInAccount");
        zag("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(strZaf)) {
            return;
        }
        zag(zab("googleSignInAccount", strZaf));
        zag(zab("googleSignInOptions", strZaf));
    }
}
