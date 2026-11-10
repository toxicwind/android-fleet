package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.SignInClientImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
@KeepForSdk
public final class ClientSettings {
    public static final String KEY_CLIENT_SESSION_ID = "com.google.android.gms.common.internal.ClientSettings.sessionId";
    public final Account account;
    public final Set<Scope> zaof;
    public final Set<Scope> zaog;
    public final Map<Api<?>, OptionalApiSettings> zaoh;
    public final int zaoi;
    public final View zaoj;
    public final String zaok;
    public final String zaol;
    public final SignInOptions zaom;
    public final boolean zaon;
    public Integer zaoo;

    @KeepForSdk
    public static final class Builder {
        public Account account;
        public Map<Api<?>, OptionalApiSettings> zaoh;
        public View zaoj;
        public String zaok;
        public String zaol;
        public ArraySet<Scope> zaop;
        public boolean zaoq;
        public int zaoi = 0;
        public SignInOptions zaom = SignInOptions.DEFAULT;

        public final Builder addAllRequiredScopes(Collection<Scope> collection) {
            if (this.zaop == null) {
                this.zaop = new ArraySet<>(0);
            }
            this.zaop.addAll(collection);
            return this;
        }

        public final Builder addRequiredScope(Scope scope) {
            if (this.zaop == null) {
                this.zaop = new ArraySet<>(0);
            }
            this.zaop.add(scope);
            return this;
        }

        @KeepForSdk
        public final ClientSettings build() {
            return new ClientSettings(this.account, this.zaop, this.zaoh, this.zaoi, this.zaoj, this.zaok, this.zaol, this.zaom, this.zaoq);
        }

        public final Builder enableSignInClientDisconnectFix() {
            this.zaoq = true;
            return this;
        }

        public final Builder setAccount(Account account) {
            this.account = account;
            return this;
        }

        public final Builder setGravityForPopups(int i) {
            this.zaoi = i;
            return this;
        }

        public final Builder setOptionalApiSettingsMap(Map<Api<?>, OptionalApiSettings> map) {
            this.zaoh = map;
            return this;
        }

        public final Builder setRealClientClassName(String str) {
            this.zaol = str;
            return this;
        }

        @KeepForSdk
        public final Builder setRealClientPackageName(String str) {
            this.zaok = str;
            return this;
        }

        public final Builder setSignInOptions(SignInOptions signInOptions) {
            this.zaom = signInOptions;
            return this;
        }

        public final Builder setViewForPopups(View view) {
            this.zaoj = view;
            return this;
        }
    }

    public static final class OptionalApiSettings {
        public final Set<Scope> mScopes;

        public OptionalApiSettings(Set<Scope> set) {
            Preconditions.checkNotNull(set);
            this.mScopes = Collections.unmodifiableSet(set);
        }
    }

    @KeepForSdk
    public ClientSettings(Account account, Set<Scope> set, Map<Api<?>, OptionalApiSettings> map, int i, View view, String str, String str2, SignInOptions signInOptions) {
        this(account, set, map, i, view, str, str2, signInOptions, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @KeepForSdk
    public static ClientSettings createDefault(Context context) {
        HashSet hashSet = new HashSet();
        new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.zaao;
        Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> abstractClientBuilder = com.google.android.gms.signin.zab.zapv;
        new ArrayList();
        new ArrayList();
        context.getMainLooper();
        String packageName = context.getPackageName();
        String name = context.getClass().getName();
        SignInOptions signInOptions = SignInOptions.DEFAULT;
        if (arrayMap2.containsKey(com.google.android.gms.signin.zab.API)) {
            signInOptions = (SignInOptions) arrayMap2.get(com.google.android.gms.signin.zab.API);
        }
        return new ClientSettings(null, hashSet, arrayMap, 0, null, packageName, name, signInOptions, false);
    }

    @KeepForSdk
    public final Account getAccount() {
        return this.account;
    }

    @KeepForSdk
    @Deprecated
    public final String getAccountName() {
        Account account = this.account;
        if (account != null) {
            return account.name;
        }
        return null;
    }

    @KeepForSdk
    public final Account getAccountOrDefault() {
        Account account = this.account;
        return account != null ? account : new Account("<<default account>>", AccountType.GOOGLE);
    }

    @KeepForSdk
    public final Set<Scope> getAllRequestedScopes() {
        return this.zaog;
    }

    @KeepForSdk
    public final Set<Scope> getApplicableScopes(Api<?> api) {
        OptionalApiSettings optionalApiSettings = this.zaoh.get(api);
        if (optionalApiSettings == null || optionalApiSettings.mScopes.isEmpty()) {
            return this.zaof;
        }
        HashSet hashSet = new HashSet(this.zaof);
        hashSet.addAll(optionalApiSettings.mScopes);
        return hashSet;
    }

    public final Integer getClientSessionId() {
        return this.zaoo;
    }

    @KeepForSdk
    public final int getGravityForPopups() {
        return this.zaoi;
    }

    public final Map<Api<?>, OptionalApiSettings> getOptionalApiSettings() {
        return this.zaoh;
    }

    public final String getRealClientClassName() {
        return this.zaol;
    }

    @KeepForSdk
    public final String getRealClientPackageName() {
        return this.zaok;
    }

    @KeepForSdk
    public final Set<Scope> getRequiredScopes() {
        return this.zaof;
    }

    public final SignInOptions getSignInOptions() {
        return this.zaom;
    }

    @KeepForSdk
    public final View getViewForPopups() {
        return this.zaoj;
    }

    public final boolean isSignInClientDisconnectFixEnabled() {
        return this.zaon;
    }

    public final void setClientSessionId(Integer num) {
        this.zaoo = num;
    }

    public ClientSettings(Account account, Set<Scope> set, Map<Api<?>, OptionalApiSettings> map, int i, View view, String str, String str2, SignInOptions signInOptions, boolean z) {
        this.account = account;
        this.zaof = set == null ? Collections.emptySet() : Collections.unmodifiableSet(set);
        this.zaoh = map == null ? Collections.emptyMap() : map;
        this.zaoj = view;
        this.zaoi = i;
        this.zaok = str;
        this.zaol = str2;
        this.zaom = signInOptions;
        this.zaon = z;
        HashSet hashSet = new HashSet(this.zaof);
        Iterator<OptionalApiSettings> it = this.zaoh.values().iterator();
        while (it.hasNext()) {
            hashSet.addAll(it.next().mScopes);
        }
        this.zaog = Collections.unmodifiableSet(hashSet);
    }
}
