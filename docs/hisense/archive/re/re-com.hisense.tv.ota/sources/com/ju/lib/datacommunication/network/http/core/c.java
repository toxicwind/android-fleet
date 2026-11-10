package com.ju.lib.datacommunication.network.http.core;

import com.ju.lib.datacommunication.network.http.HttpConfig$PauseNetworkRequestConfig$Mode;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f85a;

    static {
        int[] iArr = new int[HttpConfig$PauseNetworkRequestConfig$Mode.values().length];
        f85a = iArr;
        try {
            iArr[HttpConfig$PauseNetworkRequestConfig$Mode.Pause.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f85a[HttpConfig$PauseNetworkRequestConfig$Mode.PauseWhenStartup.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f85a[HttpConfig$PauseNetworkRequestConfig$Mode.NotPause.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
