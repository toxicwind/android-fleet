package com.google.android.gms.signin;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.signin.internal.SignInClientImpl;

/* JADX INFO: loaded from: classes.dex */
public final class zab {
    public static final Api.ClientKey<SignInClientImpl> CLIENT_KEY = new Api.ClientKey<>();

    @ShowFirstParty
    public static final Api.ClientKey<SignInClientImpl> zasj = new Api.ClientKey<>();
    public static final Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> zapv = new zaa();
    public static final Api.AbstractClientBuilder<SignInClientImpl, Object> zask = new zad();
    public static final Scope zar = new Scope(1, Scopes.PROFILE);
    public static final Scope zas = new Scope(1, "email");
    public static final Api<SignInOptions> API = new Api<>("SignIn.API", zapv, CLIENT_KEY);
    public static final Api<Object> zasl = new Api<>("SignIn.INTERNAL_API", zask, zasj);
}
