package org.schabi.newpipe.extractor.utils;

/* JADX INFO: loaded from: classes8.dex */
public interface Logger {
    void debug(String str, String str2);

    void debug(String str, String str2, Throwable th);

    void error(String str, String str2);

    void error(String str, String str2, Throwable th);

    void warn(String str, String str2);

    void warn(String str, String str2, Throwable th);
}
