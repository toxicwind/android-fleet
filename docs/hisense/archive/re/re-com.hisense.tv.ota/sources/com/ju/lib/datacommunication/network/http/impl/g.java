package com.ju.lib.datacommunication.network.http.impl;

import com.ju.lib.datacommunication.network.http.core.HiRequest$Method;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f147a;

    static {
        int[] iArr = new int[HiRequest$Method.values().length];
        f147a = iArr;
        try {
            iArr[HiRequest$Method.GET.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f147a[HiRequest$Method.POST.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f147a[HiRequest$Method.HEAD.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f147a[HiRequest$Method.PUT.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f147a[HiRequest$Method.DELETE.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            f147a[HiRequest$Method.PATCH.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
    }
}
