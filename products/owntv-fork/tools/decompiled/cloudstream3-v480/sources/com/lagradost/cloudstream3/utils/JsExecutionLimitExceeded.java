package com.lagradost.cloudstream3.utils;

import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/lagradost/cloudstream3/utils/JsExecutionLimitExceeded;", "", ContentType.Message.TYPE, "", "<init>", "(Ljava/lang/String;)V", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class JsExecutionLimitExceeded extends Throwable {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsExecutionLimitExceeded(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
