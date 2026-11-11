package io.ktor.util.logging;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: KtorSimpleLoggerJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0019\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"", "name", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "KtorSimpleLogger", "(Ljava/lang/String;)Lorg/slf4j/Logger;", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class KtorSimpleLoggerJvmKt {
    public static final Logger KtorSimpleLogger(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Logger logger = LoggerFactory.getLogger(name);
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(...)");
        return logger;
    }
}
