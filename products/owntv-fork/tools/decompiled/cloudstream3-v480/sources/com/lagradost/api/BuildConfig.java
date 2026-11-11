package com.lagradost.api;

import kotlin.Metadata;

/* JADX INFO: compiled from: BuildConfig.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/lagradost/api/BuildConfig;", "", "<init>", "()V", "MDL_API_KEY", "", "getMDL_API_KEY", "()Ljava/lang/String;", "TRAKT_CLIENT_ID", "getTRAKT_CLIENT_ID", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class BuildConfig {
    public static final BuildConfig INSTANCE = new BuildConfig();
    private static final String MDL_API_KEY = "bac0925df628dce7841a1d4e8d474c2e63fb818b";
    private static final String TRAKT_CLIENT_ID = "194bf8145e3d1ab961c7bbc9c2ddec004decbc2d2a4d3c1e0702db1b327975c5";

    private BuildConfig() {
    }

    public final String getMDL_API_KEY() {
        return MDL_API_KEY;
    }

    public final String getTRAKT_CLIENT_ID() {
        return TRAKT_CLIENT_ID;
    }
}
