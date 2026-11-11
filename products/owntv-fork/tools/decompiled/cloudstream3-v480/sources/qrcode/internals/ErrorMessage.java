package qrcode.internals;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ErrorMessage.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lqrcode/internals/ErrorMessage;", "", "<init>", "()V", "BUG_REPORT_URL", "", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, TypedValues.Custom.S_STRING, "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ErrorMessage {
    private static final String BUG_REPORT_URL = "https://github.com/g0dkar/qrcode-kotlin/issues/new?assignees=g0dkar&labels=bug&template=bug_report.md&title=";
    public static final ErrorMessage INSTANCE = new ErrorMessage();

    private ErrorMessage() {
    }

    public final String error(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        return string + " - Please, report this error via this URL: https://github.com/g0dkar/qrcode-kotlin/issues/new?assignees=g0dkar&labels=bug&template=bug_report.md&title=";
    }
}
