package com.fleeksoft.ksoup;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: Platform.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/PlatformType;", "", "<init>", "(Ljava/lang/String;I)V", "ANDROID", "ANDROID_NATIVE", "JVM", "IOS", "LINUX", "JS", "MAC", "WINDOWS", "WASM_JS", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum PlatformType {
    ANDROID,
    ANDROID_NATIVE,
    JVM,
    IOS,
    LINUX,
    JS,
    MAC,
    WINDOWS,
    WASM_JS;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<PlatformType> getEntries() {
        return $ENTRIES;
    }
}
