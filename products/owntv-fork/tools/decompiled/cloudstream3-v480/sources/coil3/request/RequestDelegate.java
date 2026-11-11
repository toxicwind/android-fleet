package coil3.request;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: RequestDelegate.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u000e\u0010\u0005\u001a\u00020\u0003H\u0096@¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lcoil3/request/RequestDelegate;", "", "assertActive", "", "start", "awaitStarted", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "complete", "dispose", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface RequestDelegate {
    void assertActive();

    Object awaitStarted(Continuation<? super Unit> continuation);

    void complete();

    void dispose();

    void start();

    /* JADX INFO: renamed from: coil3.request.RequestDelegate$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: RequestDelegate.kt */
    public final /* synthetic */ class CC {
        public static void $default$assertActive(RequestDelegate _this) {
        }

        public static void $default$complete(RequestDelegate _this) {
        }

        public static void $default$dispose(RequestDelegate _this) {
        }

        public static void $default$start(RequestDelegate _this) {
        }

        public static /* synthetic */ Object awaitStarted$suspendImpl(RequestDelegate requestDelegate, Continuation<? super Unit> continuation) {
            return Unit.INSTANCE;
        }
    }
}
