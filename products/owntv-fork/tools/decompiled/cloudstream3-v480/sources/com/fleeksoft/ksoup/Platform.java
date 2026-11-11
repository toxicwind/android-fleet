package com.fleeksoft.ksoup;

import kotlin.Metadata;

/* JADX INFO: compiled from: Platform.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/fleeksoft/ksoup/Platform;", "", "<init>", "()V", "current", "Lcom/fleeksoft/ksoup/PlatformType;", "getCurrent", "()Lcom/fleeksoft/ksoup/PlatformType;", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Platform {
    public static final Platform INSTANCE = new Platform();

    private Platform() {
    }

    public final PlatformType getCurrent() {
        return PlatformType.ANDROID;
    }
}
