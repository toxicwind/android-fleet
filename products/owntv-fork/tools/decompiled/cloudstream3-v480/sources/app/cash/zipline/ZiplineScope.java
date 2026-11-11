package app.cash.zipline;

import app.cash.zipline.internal.bridge.OutboundCallHandler;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ZiplineScope.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0010J\u0015\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0012J\b\u0010\u0013\u001a\u00020\u000eH\u0016R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lapp/cash/zipline/ZiplineScope;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", ES6Iterator.VALUE_PROPERTY, "", "closed", "getClosed$zipline_release", "()Z", "callHandlers", "", "Lapp/cash/zipline/internal/bridge/OutboundCallHandler;", "add", "", "callHandler", "add$zipline_release", "remove", "remove$zipline_release", "close", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ZiplineScope implements AutoCloseable {
    private final Set<OutboundCallHandler> callHandlers = new LinkedHashSet();
    private boolean closed;

    /* JADX INFO: renamed from: getClosed$zipline_release, reason: from getter */
    public final boolean getClosed() {
        return this.closed;
    }

    public final void add$zipline_release(OutboundCallHandler callHandler) {
        Intrinsics.checkNotNullParameter(callHandler, "callHandler");
        if (this.closed) {
            throw new IllegalStateException("Check failed.");
        }
        this.callHandlers.add(callHandler);
    }

    public final void remove$zipline_release(OutboundCallHandler callHandler) {
        Intrinsics.checkNotNullParameter(callHandler, "callHandler");
        this.callHandlers.remove(callHandler);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        OutboundCallHandler[] outboundCallHandlerArr = (OutboundCallHandler[]) this.callHandlers.toArray(new OutboundCallHandler[0]);
        this.callHandlers.clear();
        for (OutboundCallHandler outboundCallHandler : outboundCallHandlerArr) {
            outboundCallHandler.outboundService().close();
        }
    }
}
