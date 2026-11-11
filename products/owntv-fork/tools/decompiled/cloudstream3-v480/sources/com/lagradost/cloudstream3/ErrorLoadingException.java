package com.lagradost.cloudstream3;

import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/ErrorLoadingException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", ContentType.Message.TYPE, "", "<init>", "(Ljava/lang/String;)V", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ErrorLoadingException extends Exception {
    /* JADX WARN: Multi-variable type inference failed */
    public ErrorLoadingException() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ErrorLoadingException(String str) {
        super(str);
    }

    public /* synthetic */ ErrorLoadingException(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str);
    }
}
