package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Platform {
    public static final ThreadLocal<char[]> DEST_TL = new ThreadLocal<char[]>() { // from class: com.google.common.escape.Platform.1
        @Override // java.lang.ThreadLocal
        public char[] initialValue() {
            return new char[1024];
        }
    };

    public static char[] charBufferFromThreadLocal() {
        return DEST_TL.get();
    }
}
