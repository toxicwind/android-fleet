package io.ktor.http.content;

import io.ktor.http.HttpStatusCode;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: Versions.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lio/ktor/http/content/VersionCheckResult;", "", "Lio/ktor/http/HttpStatusCode;", "statusCode", "<init>", "(Ljava/lang/String;ILio/ktor/http/HttpStatusCode;)V", "Lio/ktor/http/HttpStatusCode;", "getStatusCode", "()Lio/ktor/http/HttpStatusCode;", "OK", "NOT_MODIFIED", "PRECONDITION_FAILED", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum VersionCheckResult {
    OK(HttpStatusCode.INSTANCE.getOK()),
    NOT_MODIFIED(HttpStatusCode.INSTANCE.getNotModified()),
    PRECONDITION_FAILED(HttpStatusCode.INSTANCE.getPreconditionFailed());

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final HttpStatusCode statusCode;

    public static EnumEntries<VersionCheckResult> getEntries() {
        return $ENTRIES;
    }

    VersionCheckResult(HttpStatusCode httpStatusCode) {
        this.statusCode = httpStatusCode;
    }

    public final HttpStatusCode getStatusCode() {
        return this.statusCode;
    }
}
