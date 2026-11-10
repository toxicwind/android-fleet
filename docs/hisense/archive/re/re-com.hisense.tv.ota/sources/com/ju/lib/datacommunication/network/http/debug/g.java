package com.ju.lib.datacommunication.network.http.debug;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f109a;

    static {
        int[] iArr = new int[DebugCheckType.values().length];
        f109a = iArr;
        try {
            iArr[DebugCheckType.Param.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f109a[DebugCheckType.RespBody.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f109a[DebugCheckType.RespCode.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f109a[DebugCheckType.Url.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
