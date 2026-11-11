package com.lagradost.cloudstream3.mvvm;

import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArchComponentExt.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/mvvm/DebugException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", ContentType.Message.TYPE, "", "<init>", "(Ljava/lang/String;)V", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DebugException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DebugException(String message) {
        super("THIS IS A DEBUG EXCEPTION!\n" + message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
