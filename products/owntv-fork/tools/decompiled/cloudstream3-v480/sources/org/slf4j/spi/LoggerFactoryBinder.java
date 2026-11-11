package org.slf4j.spi;

import org.slf4j.ILoggerFactory;

/* JADX INFO: loaded from: classes8.dex */
public interface LoggerFactoryBinder {
    ILoggerFactory getLoggerFactory();

    String getLoggerFactoryClassStr();
}
