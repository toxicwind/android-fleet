package org.slf4j.event;

import java.util.List;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes8.dex */
public interface LoggingEvent {

    /* JADX INFO: renamed from: org.slf4j.event.LoggingEvent$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static String $default$getCallerBoundary(LoggingEvent _this) {
            return null;
        }
    }

    Object[] getArgumentArray();

    List<Object> getArguments();

    String getCallerBoundary();

    List<KeyValuePair> getKeyValuePairs();

    Level getLevel();

    String getLoggerName();

    List<Marker> getMarkers();

    String getMessage();

    String getThreadName();

    Throwable getThrowable();

    long getTimeStamp();
}
