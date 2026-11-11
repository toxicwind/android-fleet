package app.cash.zipline.internal.bridge;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: calls.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B%\u0012\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lapp/cash/zipline/internal/bridge/ResultOrCallback;", ExifInterface.GPS_DIRECTION_TRUE, "", "result", "Lkotlin/Result;", "callback", "Lapp/cash/zipline/internal/bridge/CancelCallback;", "<init>", "(Lkotlin/Result;Lapp/cash/zipline/internal/bridge/CancelCallback;)V", "getResult-xLWZpok", "()Lkotlin/Result;", "getCallback", "()Lapp/cash/zipline/internal/bridge/CancelCallback;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ResultOrCallback<T> {
    private final CancelCallback callback;
    private final Result<T> result;

    /* JADX WARN: Multi-variable type inference failed */
    public ResultOrCallback() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ResultOrCallback(Result<? extends T> result, CancelCallback cancelCallback) {
        this.result = result;
        this.callback = cancelCallback;
        if ((cancelCallback != null) == (result != 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ ResultOrCallback(Result result, CancelCallback cancelCallback, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : result, (i & 2) != 0 ? null : cancelCallback);
    }

    /* JADX INFO: renamed from: getResult-xLWZpok, reason: not valid java name */
    public final Result<T> m8087getResultxLWZpok() {
        return this.result;
    }

    public final CancelCallback getCallback() {
        return this.callback;
    }
}
