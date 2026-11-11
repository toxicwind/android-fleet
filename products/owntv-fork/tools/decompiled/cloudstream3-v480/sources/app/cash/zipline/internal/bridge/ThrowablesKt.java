package app.cash.zipline.internal.bridge;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;

/* JADX INFO: compiled from: throwables.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"theOnlyCancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "getTheOnlyCancellationException", "()Ljava/util/concurrent/CancellationException;", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ThrowablesKt {
    private static final CancellationException theOnlyCancellationException = new CancellationException("canceled");

    public static final CancellationException getTheOnlyCancellationException() {
        return theOnlyCancellationException;
    }
}
