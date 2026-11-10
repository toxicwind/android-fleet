package com.ju.lib.datacommunication.network.http.debug;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f103a;

    static {
        int[] iArr = new int[DebugCheckType.values().length];
        f103a = iArr;
        try {
            iArr[DebugCheckType.Param.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f103a[DebugCheckType.RespBody.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f103a[DebugCheckType.RespCode.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f103a[DebugCheckType.Url.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
