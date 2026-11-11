package com.lagradost.cloudstream3.utils;

import kotlin.Metadata;

/* JADX INFO: compiled from: AppDebug.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/lagradost/cloudstream3/utils/AppDebug;", "", "<init>", "()V", "isDebug", "", "()Z", "setDebug", "(Z)V", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AppDebug {
    public static final AppDebug INSTANCE = new AppDebug();
    private static volatile boolean isDebug;

    private AppDebug() {
    }

    public final boolean isDebug() {
        return isDebug;
    }

    public final void setDebug(boolean z) {
        isDebug = z;
    }
}
