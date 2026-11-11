package io.ktor.util.logging;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.slf4j.Logger;

/* JADX INFO: compiled from: Logger.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u001d\u0010\u0005\u001a\u00020\u0004*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a)\u0010\n\u001a\u00020\u0004*\u00060\u0000j\u0002`\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086\bø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a)\u0010\f\u001a\u00020\u0004*\u00060\u0000j\u0002`\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\u000b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "", "exception", "", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "(Lorg/slf4j/Logger;Ljava/lang/Throwable;)V", "Lkotlin/Function0;", "", ContentType.Message.TYPE, "trace", "(Lorg/slf4j/Logger;Lkotlin/jvm/functions/Function0;)V", "debug", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LoggerKt {
    public static final void error(Logger logger, Throwable exception) {
        Intrinsics.checkNotNullParameter(logger, "<this>");
        Intrinsics.checkNotNullParameter(exception, "exception");
        String message = exception.getMessage();
        if (message == null) {
            message = "Exception of type " + Reflection.getOrCreateKotlinClass(exception.getClass());
        }
        logger.error(message, exception);
    }

    public static final void trace(Logger logger, Function0<String> message) {
        Intrinsics.checkNotNullParameter(logger, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        if (LoggerJvmKt.isTraceEnabled(logger)) {
            logger.trace(message.invoke());
        }
    }

    public static final void debug(Logger logger, Function0<String> message) {
        Intrinsics.checkNotNullParameter(logger, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        if (LoggerJvmKt.isDebugEnabled(logger)) {
            logger.debug(message.invoke());
        }
    }
}
