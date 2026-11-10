package com.ju.lib.datacommunication.network.http;

import b.a.a.a.a;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;

/* JADX INFO: loaded from: classes2.dex */
public final class Version {
    public static final String VersionName = "1.0";
    public static final String UserAgent = a.a("okhttp/3.12.12", MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING, VersionName);

    public static String userAgent() {
        return UserAgent;
    }

    public static String versionName() {
        return VersionName;
    }
}
