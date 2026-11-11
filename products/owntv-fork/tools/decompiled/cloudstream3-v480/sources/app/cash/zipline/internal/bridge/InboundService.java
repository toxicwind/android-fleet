package app.cash.zipline.internal.bridge;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.Call;
import app.cash.zipline.CallResult;
import app.cash.zipline.ZiplineFunction;
import app.cash.zipline.ZiplineService;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;

/* JADX INFO: compiled from: InboundService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J&\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u000e\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0018J\n\u0010\u0019\u001a\u00020\u0011H\u0096\u0080\u0004R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u00028\u0000X\u0080\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lapp/cash/zipline/internal/bridge/InboundService;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "", "type", "Lapp/cash/zipline/internal/bridge/RealZiplineServiceType;", NotificationCompat.CATEGORY_SERVICE, "endpoint", "Lapp/cash/zipline/internal/bridge/Endpoint;", "<init>", "(Lapp/cash/zipline/internal/bridge/RealZiplineServiceType;Lapp/cash/zipline/ZiplineService;Lapp/cash/zipline/internal/bridge/Endpoint;)V", "getType$zipline_release", "()Lapp/cash/zipline/internal/bridge/RealZiplineServiceType;", "getService$zipline_release", "()Lapp/cash/zipline/ZiplineService;", "Lapp/cash/zipline/ZiplineService;", NotificationCompat.CATEGORY_CALL, "", "internalCall", "Lapp/cash/zipline/internal/bridge/InternalCall;", "externalCall", "Lapp/cash/zipline/Call;", "callSuspending", "suspendCallback", "Lapp/cash/zipline/internal/bridge/SuspendCallback;", "toString", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class InboundService<T extends ZiplineService> {
    private final Endpoint endpoint;
    private final T service;
    private final RealZiplineServiceType<T> type;

    public InboundService(RealZiplineServiceType<T> type, T service, Endpoint endpoint) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        this.type = type;
        this.service = service;
        this.endpoint = endpoint;
    }

    public final RealZiplineServiceType<T> getType$zipline_release() {
        return this.type;
    }

    public final T getService$zipline_release() {
        return this.service;
    }

    public final String call(InternalCall internalCall, Call externalCall) {
        Object objM8936constructorimpl;
        Intrinsics.checkNotNullParameter(internalCall, "internalCall");
        Intrinsics.checkNotNullParameter(externalCall, "externalCall");
        ZiplineFunction<?> function = internalCall.getFunction();
        Intrinsics.checkNotNull(function, "null cannot be cast to non-null type app.cash.zipline.internal.bridge.ReturningZiplineFunction<app.cash.zipline.ZiplineService>");
        ReturningZiplineFunction<?> returningZiplineFunction = (ReturningZiplineFunction) function;
        if (returningZiplineFunction.isClose()) {
            this.endpoint.remove(internalCall.getServiceName());
        }
        Object objCallStart = !(externalCall.getService() instanceof SuspendCallback) ? this.endpoint.getEventListener().callStart(externalCall) : Unit.INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            InboundService<T> inboundService = this;
            objM8936constructorimpl = Result.m8936constructorimpl(returningZiplineFunction.call(this.service, internalCall.getArgs()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        CallResult callResultEncodeResult$zipline_release = this.endpoint.getCallCodec().encodeResult$zipline_release(returningZiplineFunction, objM8936constructorimpl);
        if (!(externalCall.getService() instanceof SuspendCallback)) {
            this.endpoint.getEventListener().callEnd(externalCall, callResultEncodeResult$zipline_release, objCallStart);
        }
        return callResultEncodeResult$zipline_release.getEncodedResult();
    }

    public final String callSuspending(InternalCall internalCall, final Call externalCall, final SuspendCallback<Object> suspendCallback) {
        Object value;
        Intrinsics.checkNotNullParameter(internalCall, "internalCall");
        Intrinsics.checkNotNullParameter(externalCall, "externalCall");
        Intrinsics.checkNotNullParameter(suspendCallback, "suspendCallback");
        ZiplineFunction<?> function = internalCall.getFunction();
        Intrinsics.checkNotNull(function, "null cannot be cast to non-null type app.cash.zipline.internal.bridge.SuspendingZiplineFunction<app.cash.zipline.ZiplineService>");
        SuspendingZiplineFunction<?> suspendingZiplineFunction = (SuspendingZiplineFunction) function;
        final Object objCallStart = this.endpoint.getEventListener().callStart(externalCall);
        final Deferred deferredAsync$default = BuildersKt__Builders_commonKt.async$default(this.endpoint.getScope(), null, CoroutineStart.UNDISPATCHED, new InboundService$callSuspending$deferred$1(internalCall, suspendingZiplineFunction, this, null), 1, null);
        if (deferredAsync$default.isActive()) {
            final InboundService$callSuspending$cancelCallback$1 inboundService$callSuspending$cancelCallback$1 = new InboundService$callSuspending$cancelCallback$1(deferredAsync$default, internalCall);
            deferredAsync$default.invokeOnCompletion(new Function1() { // from class: app.cash.zipline.internal.bridge.InboundService$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return InboundService.callSuspending$lambda$0(inboundService$callSuspending$cancelCallback$1, this, deferredAsync$default, suspendCallback, externalCall, objCallStart, (Throwable) obj);
                }
            });
            return this.endpoint.getCallCodec().encodeResultOrCallback$zipline_release(suspendingZiplineFunction, new ResultOrCallback<>(null, inboundService$callSuspending$cancelCallback$1, 1, null)).getJson();
        }
        Throwable completionExceptionOrNull = deferredAsync$default.getCompletionExceptionOrNull();
        if (completionExceptionOrNull != null) {
            Result.Companion companion = Result.INSTANCE;
            value = Result.m8936constructorimpl(ResultKt.createFailure(completionExceptionOrNull));
        } else {
            value = ((Result) deferredAsync$default.getCompleted()).getValue();
        }
        EncodedResultOrCallback encodedResultOrCallbackEncodeResultOrCallback$zipline_release = this.endpoint.getCallCodec().encodeResultOrCallback$zipline_release(suspendingZiplineFunction, new ResultOrCallback<>(Result.m8935boximpl(value), null, 2, null));
        Endpoint.EventListener eventListener$zipline_release = this.endpoint.getEventListener();
        CallResult callResult = encodedResultOrCallbackEncodeResultOrCallback$zipline_release.getCallResult();
        Intrinsics.checkNotNull(callResult);
        eventListener$zipline_release.callEnd(externalCall, callResult, objCallStart);
        return encodedResultOrCallbackEncodeResultOrCallback$zipline_release.getJson();
    }

    static final Unit callSuspending$lambda$0(InboundService$callSuspending$cancelCallback$1 inboundService$callSuspending$cancelCallback$1, final InboundService inboundService, Deferred deferred, SuspendCallback suspendCallback, final Call call, final Object obj, Throwable th) {
        final Object value;
        String passByReferenceName = inboundService$callSuspending$cancelCallback$1.getPassByReferenceName();
        if (passByReferenceName != null) {
            inboundService.endpoint.remove(passByReferenceName);
        }
        Throwable completionExceptionOrNull = deferred.getCompletionExceptionOrNull();
        if (completionExceptionOrNull != null) {
            Result.Companion companion = Result.INSTANCE;
            value = Result.m8936constructorimpl(ResultKt.createFailure(completionExceptionOrNull));
        } else {
            value = ((Result) deferred.getCompleted()).getValue();
        }
        if (!CoroutineScopeKt.isActive(inboundService.endpoint.getScope())) {
            return Unit.INSTANCE;
        }
        inboundService.endpoint.getCallCodec().setNextOutboundCallCallback(new Function1() { // from class: app.cash.zipline.internal.bridge.InboundService$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return InboundService.callSuspending$lambda$0$0(this.f$0, call, value, obj, (Call) obj2);
            }
        });
        if (Result.m8942isFailureimpl(value)) {
            Throwable thM8939exceptionOrNullimpl = Result.m8939exceptionOrNullimpl(value);
            Intrinsics.checkNotNull(thM8939exceptionOrNullimpl);
            suspendCallback.failure(thM8939exceptionOrNullimpl);
        } else {
            if (Result.m8942isFailureimpl(value)) {
                value = null;
            }
            suspendCallback.success(value);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callSuspending$lambda$0$0(InboundService inboundService, Call call, Object obj, Object obj2, Call callbackCall) {
        Intrinsics.checkNotNullParameter(callbackCall, "callbackCall");
        inboundService.endpoint.getEventListener().callEnd(call, new CallResult(obj, callbackCall.getEncodedCall(), callbackCall.getServiceNames()), obj2);
        return Unit.INSTANCE;
    }

    public String toString() {
        return this.service.toString();
    }
}
