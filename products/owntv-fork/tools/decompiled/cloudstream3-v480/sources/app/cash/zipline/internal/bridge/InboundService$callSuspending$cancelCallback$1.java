package app.cash.zipline.internal.bridge;

import app.cash.zipline.ZiplineService;
import kotlin.Metadata;
import kotlin.Result;
import kotlinx.coroutines.Deferred;

/* JADX INFO: compiled from: InboundService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\n\u0010\u000b\u001a\u00020\u0004H\u0096\u0080\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"app/cash/zipline/internal/bridge/InboundService$callSuspending$cancelCallback$1", "Lapp/cash/zipline/internal/bridge/CancelCallback;", "Lapp/cash/zipline/internal/bridge/HasPassByReferenceName;", "passByReferenceName", "", "getPassByReferenceName", "()Ljava/lang/String;", "setPassByReferenceName", "(Ljava/lang/String;)V", "cancel", "", "toString", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class InboundService$callSuspending$cancelCallback$1 implements CancelCallback, HasPassByReferenceName {
    final /* synthetic */ Deferred<Result<Object>> $deferred;
    final /* synthetic */ InternalCall $internalCall;
    private String passByReferenceName;

    /* JADX WARN: Multi-variable type inference failed */
    InboundService$callSuspending$cancelCallback$1(Deferred<? extends Result<? extends Object>> deferred, InternalCall internalCall) {
        this.$deferred = deferred;
        this.$internalCall = internalCall;
    }

    @Override // app.cash.zipline.ZiplineService, java.lang.AutoCloseable
    public /* bridge */ void close() {
        ZiplineService.CC.$default$close(this);
    }

    @Override // app.cash.zipline.internal.bridge.HasPassByReferenceName
    public String getPassByReferenceName() {
        return this.passByReferenceName;
    }

    @Override // app.cash.zipline.internal.bridge.HasPassByReferenceName
    public void setPassByReferenceName(String str) {
        this.passByReferenceName = str;
    }

    @Override // app.cash.zipline.internal.bridge.CancelCallback
    public void cancel() {
        this.$deferred.cancel(ThrowablesKt.getTheOnlyCancellationException());
    }

    public String toString() {
        return "CancelCallback/" + this.$internalCall;
    }
}
