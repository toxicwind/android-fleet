package org.slf4j;

import org.slf4j.event.Level;
import org.slf4j.helpers.CheckReturnValue;
import org.slf4j.spi.DefaultLoggingEventBuilder;
import org.slf4j.spi.LoggingEventBuilder;
import org.slf4j.spi.NOPLoggingEventBuilder;

/* JADX INFO: loaded from: classes8.dex */
public interface Logger {
    public static final String ROOT_LOGGER_NAME = "ROOT";

    @CheckReturnValue
    LoggingEventBuilder atDebug();

    @CheckReturnValue
    LoggingEventBuilder atError();

    @CheckReturnValue
    LoggingEventBuilder atInfo();

    @CheckReturnValue
    LoggingEventBuilder atLevel(Level level);

    @CheckReturnValue
    LoggingEventBuilder atTrace();

    @CheckReturnValue
    LoggingEventBuilder atWarn();

    void debug(String str);

    void debug(String str, Object obj);

    void debug(String str, Object obj, Object obj2);

    void debug(String str, Throwable th);

    void debug(String str, Object... objArr);

    void debug(Marker marker, String str);

    void debug(Marker marker, String str, Object obj);

    void debug(Marker marker, String str, Object obj, Object obj2);

    void debug(Marker marker, String str, Throwable th);

    void debug(Marker marker, String str, Object... objArr);

    void error(String str);

    void error(String str, Object obj);

    void error(String str, Object obj, Object obj2);

    void error(String str, Throwable th);

    void error(String str, Object... objArr);

    void error(Marker marker, String str);

    void error(Marker marker, String str, Object obj);

    void error(Marker marker, String str, Object obj, Object obj2);

    void error(Marker marker, String str, Throwable th);

    void error(Marker marker, String str, Object... objArr);

    String getName();

    void info(String str);

    void info(String str, Object obj);

    void info(String str, Object obj, Object obj2);

    void info(String str, Throwable th);

    void info(String str, Object... objArr);

    void info(Marker marker, String str);

    void info(Marker marker, String str, Object obj);

    void info(Marker marker, String str, Object obj, Object obj2);

    void info(Marker marker, String str, Throwable th);

    void info(Marker marker, String str, Object... objArr);

    boolean isDebugEnabled();

    boolean isDebugEnabled(Marker marker);

    boolean isEnabledForLevel(Level level);

    boolean isErrorEnabled();

    boolean isErrorEnabled(Marker marker);

    boolean isInfoEnabled();

    boolean isInfoEnabled(Marker marker);

    boolean isTraceEnabled();

    boolean isTraceEnabled(Marker marker);

    boolean isWarnEnabled();

    boolean isWarnEnabled(Marker marker);

    LoggingEventBuilder makeLoggingEventBuilder(Level level);

    void trace(String str);

    void trace(String str, Object obj);

    void trace(String str, Object obj, Object obj2);

    void trace(String str, Throwable th);

    void trace(String str, Object... objArr);

    void trace(Marker marker, String str);

    void trace(Marker marker, String str, Object obj);

    void trace(Marker marker, String str, Object obj, Object obj2);

    void trace(Marker marker, String str, Throwable th);

    void trace(Marker marker, String str, Object... objArr);

    void warn(String str);

    void warn(String str, Object obj);

    void warn(String str, Object obj, Object obj2);

    void warn(String str, Throwable th);

    void warn(String str, Object... objArr);

    void warn(Marker marker, String str);

    void warn(Marker marker, String str, Object obj);

    void warn(Marker marker, String str, Object obj, Object obj2);

    void warn(Marker marker, String str, Throwable th);

    void warn(Marker marker, String str, Object... objArr);

    /* JADX INFO: renamed from: org.slf4j.Logger$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static LoggingEventBuilder $default$makeLoggingEventBuilder(Logger _this, Level level) {
            return new DefaultLoggingEventBuilder(_this, level);
        }

        @CheckReturnValue
        public static LoggingEventBuilder $default$atLevel(Logger _this, Level level) {
            if (_this.isEnabledForLevel(level)) {
                return _this.makeLoggingEventBuilder(level);
            }
            return NOPLoggingEventBuilder.singleton();
        }

        public static boolean $default$isEnabledForLevel(Logger _this, Level level) {
            int i = level.toInt();
            if (i == 0) {
                return _this.isTraceEnabled();
            }
            if (i == 10) {
                return _this.isDebugEnabled();
            }
            if (i == 20) {
                return _this.isInfoEnabled();
            }
            if (i == 30) {
                return _this.isWarnEnabled();
            }
            if (i == 40) {
                return _this.isErrorEnabled();
            }
            throw new IllegalArgumentException("Level [" + level + "] not recognized.");
        }

        @CheckReturnValue
        public static LoggingEventBuilder $default$atTrace(Logger _this) {
            if (_this.isTraceEnabled()) {
                return _this.makeLoggingEventBuilder(Level.TRACE);
            }
            return NOPLoggingEventBuilder.singleton();
        }

        @CheckReturnValue
        public static LoggingEventBuilder $default$atDebug(Logger _this) {
            if (_this.isDebugEnabled()) {
                return _this.makeLoggingEventBuilder(Level.DEBUG);
            }
            return NOPLoggingEventBuilder.singleton();
        }

        @CheckReturnValue
        public static LoggingEventBuilder $default$atInfo(Logger _this) {
            if (_this.isInfoEnabled()) {
                return _this.makeLoggingEventBuilder(Level.INFO);
            }
            return NOPLoggingEventBuilder.singleton();
        }

        @CheckReturnValue
        public static LoggingEventBuilder $default$atWarn(Logger _this) {
            if (_this.isWarnEnabled()) {
                return _this.makeLoggingEventBuilder(Level.WARN);
            }
            return NOPLoggingEventBuilder.singleton();
        }

        @CheckReturnValue
        public static LoggingEventBuilder $default$atError(Logger _this) {
            if (_this.isErrorEnabled()) {
                return _this.makeLoggingEventBuilder(Level.ERROR);
            }
            return NOPLoggingEventBuilder.singleton();
        }
    }
}
