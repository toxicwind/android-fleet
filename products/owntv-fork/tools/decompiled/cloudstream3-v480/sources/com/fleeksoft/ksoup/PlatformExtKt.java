package com.fleeksoft.ksoup;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlatformExt.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\b\u001a\u00020\u0001*\u00020\u0002¨\u0006\t"}, d2 = {"isApple", "", "Lcom/fleeksoft/ksoup/Platform;", "isWindows", "isLinux", "isJvmOrAndroid", "isJvm", "isJsOrWasm", "isWasmJs", "ksoup_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PlatformExtKt {
    public static final boolean isApple(Platform platform) {
        Intrinsics.checkNotNullParameter(platform, "<this>");
        return platform.getCurrent() == PlatformType.IOS || platform.getCurrent() == PlatformType.MAC;
    }

    public static final boolean isWindows(Platform platform) {
        Intrinsics.checkNotNullParameter(platform, "<this>");
        return platform.getCurrent() == PlatformType.WINDOWS;
    }

    public static final boolean isLinux(Platform platform) {
        Intrinsics.checkNotNullParameter(platform, "<this>");
        return platform.getCurrent() == PlatformType.LINUX;
    }

    public static final boolean isJvmOrAndroid(Platform platform) {
        Intrinsics.checkNotNullParameter(platform, "<this>");
        return platform.getCurrent() == PlatformType.JVM || platform.getCurrent() == PlatformType.ANDROID;
    }

    public static final boolean isJvm(Platform platform) {
        Intrinsics.checkNotNullParameter(platform, "<this>");
        return platform.getCurrent() == PlatformType.JVM;
    }

    public static final boolean isJsOrWasm(Platform platform) {
        Intrinsics.checkNotNullParameter(platform, "<this>");
        return platform.getCurrent() == PlatformType.JS || platform.getCurrent() == PlatformType.WASM_JS;
    }

    public static final boolean isWasmJs(Platform platform) {
        Intrinsics.checkNotNullParameter(platform, "<this>");
        return platform.getCurrent() == PlatformType.WASM_JS;
    }
}
