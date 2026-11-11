package org.slf4j.helpers;

import java.io.ObjectStreamException;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.slf4j.spi.LoggingEventBuilder;

/* JADX INFO: loaded from: classes8.dex */
abstract class NamedLoggerBase implements Logger, Serializable {
    private static final long serialVersionUID = 7535258609338176893L;
    protected String name;

    @Override // org.slf4j.Logger
    public /* synthetic */ LoggingEventBuilder atDebug() {
        return Logger.CC.$default$atDebug(this);
    }

    @Override // org.slf4j.Logger
    public /* synthetic */ LoggingEventBuilder atError() {
        return Logger.CC.$default$atError(this);
    }

    @Override // org.slf4j.Logger
    public /* synthetic */ LoggingEventBuilder atInfo() {
        return Logger.CC.$default$atInfo(this);
    }

    @Override // org.slf4j.Logger
    public /* synthetic */ LoggingEventBuilder atLevel(Level level) {
        return Logger.CC.$default$atLevel(this, level);
    }

    @Override // org.slf4j.Logger
    public /* synthetic */ LoggingEventBuilder atTrace() {
        return Logger.CC.$default$atTrace(this);
    }

    @Override // org.slf4j.Logger
    public /* synthetic */ LoggingEventBuilder atWarn() {
        return Logger.CC.$default$atWarn(this);
    }

    @Override // org.slf4j.Logger
    public /* synthetic */ boolean isEnabledForLevel(Level level) {
        return Logger.CC.$default$isEnabledForLevel(this, level);
    }

    @Override // org.slf4j.Logger
    public /* synthetic */ LoggingEventBuilder makeLoggingEventBuilder(Level level) {
        return Logger.CC.$default$makeLoggingEventBuilder(this, level);
    }

    NamedLoggerBase() {
    }

    @Override // org.slf4j.Logger
    public String getName() {
        return this.name;
    }

    protected Object readResolve() throws ObjectStreamException {
        return LoggerFactory.getLogger(getName());
    }
}
