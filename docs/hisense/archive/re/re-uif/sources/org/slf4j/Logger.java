package org.slf4j;

/* JADX INFO: loaded from: classes.dex */
public interface Logger {
    void error(String str);

    void error(String str, Throwable th);

    String getName();

    boolean isTraceEnabled();

    void trace(String str);

    void trace(String str, Object obj);

    void trace(String str, Object obj, Object obj2);

    void trace(String str, Throwable th);
}
