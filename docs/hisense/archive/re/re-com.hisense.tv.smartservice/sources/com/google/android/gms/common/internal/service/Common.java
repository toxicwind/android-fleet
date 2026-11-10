package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

/* JADX INFO: loaded from: classes.dex */
public final class Common {

    @KeepForSdk
    public static final Api.ClientKey<zah> CLIENT_KEY = new Api.ClientKey<>();
    public static final Api.AbstractClientBuilder<zah, Api.ApiOptions.NoOptions> zapv = new zac();

    @KeepForSdk
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Common.API", zapv, CLIENT_KEY);
    public static final zab zapw = new zae();
}
