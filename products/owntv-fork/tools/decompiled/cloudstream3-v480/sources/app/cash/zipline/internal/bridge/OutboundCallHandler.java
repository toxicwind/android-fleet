package app.cash.zipline.internal.bridge;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.Call;
import app.cash.zipline.CallResult;
import app.cash.zipline.ZiplineApiMismatchException;
import app.cash.zipline.ZiplineFunction;
import app.cash.zipline.ZiplineScope;
import app.cash.zipline.ZiplineScoped;
import app.cash.zipline.ZiplineService;
import app.cash.zipline.ZiplineServiceType;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.serialization.KSerializer;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: OutboundCallHandler.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u0001:\u0002=>BA\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u0015\u0010\u001b\u001a\u0002H\u001c\"\b\b\u0000\u0010\u001c*\u00020\u001d¢\u0006\u0002\u0010\u001eJ5\u0010\u001f\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"2\u0016\u0010#\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010$\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010%JQ\u0010&\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020\u001d2\n\u0010'\u001a\u0006\u0012\u0002\b\u00030(2\u0006\u0010)\u001a\u00020*2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030,2\u0016\u0010#\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010$\"\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0004\b-\u0010.J8\u0010/\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"2\u0016\u0010#\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010$\"\u0004\u0018\u00010\u0001H\u0086@¢\u0006\u0002\u00100J^\u00101\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020\u001d2\n\u0010'\u001a\u0006\u0012\u0002\b\u00030(2\u0006\u0010)\u001a\u00020*2\n\u00102\u001a\u0006\u0012\u0002\b\u00030,2\n\u00103\u001a\u0006\u0012\u0002\b\u0003042\u0016\u0010#\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010$\"\u0004\u0018\u00010\u0001H\u0080@¢\u0006\u0004\b5\u00106J\n\u00107\u001a\u00020\u0005H\u0096\u0080\u0004J1\u00108\u001a\b\u0012\u0004\u0012\u0002H\u001c09\"\u0004\b\u0000\u0010\u001c*\b\u0012\u0004\u0012\u0002H\u001c092\n\u0010:\u001a\u0006\u0012\u0002\b\u00030(H\u0002¢\u0006\u0004\b;\u0010<R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\f\u001a\u00020\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006?"}, d2 = {"Lapp/cash/zipline/internal/bridge/OutboundCallHandler;", "", "sourceType", "Lapp/cash/zipline/ZiplineServiceType;", "serviceName", "", "endpoint", "Lapp/cash/zipline/internal/bridge/Endpoint;", "adapter", "Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;", "scope", "Lapp/cash/zipline/ZiplineScope;", "serviceState", "Lapp/cash/zipline/internal/bridge/OutboundCallHandler$ServiceState;", "<init>", "(Lapp/cash/zipline/ZiplineServiceType;Ljava/lang/String;Lapp/cash/zipline/internal/bridge/Endpoint;Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;Lapp/cash/zipline/ZiplineScope;Lapp/cash/zipline/internal/bridge/OutboundCallHandler$ServiceState;)V", "getSourceType", "()Lapp/cash/zipline/ZiplineServiceType;", "getScope$zipline_release", "()Lapp/cash/zipline/ZiplineScope;", "getServiceState$zipline_release", "()Lapp/cash/zipline/internal/bridge/OutboundCallHandler$ServiceState;", "withScope", "targetType", "Lapp/cash/zipline/internal/bridge/SerializableZiplineServiceType;", "getTargetType", "()Lapp/cash/zipline/internal/bridge/SerializableZiplineServiceType;", "outboundService", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "()Lapp/cash/zipline/ZiplineService;", NotificationCompat.CATEGORY_CALL, NotificationCompat.CATEGORY_SERVICE, "functionIndex", "", "args", "", "(Lapp/cash/zipline/ZiplineService;I[Ljava/lang/Object;)Ljava/lang/Object;", "callInternal", "function", "Lapp/cash/zipline/ZiplineFunction;", "argsListSerializer", "Lapp/cash/zipline/internal/bridge/ArgsListSerializer;", "resultSerializer", "Lapp/cash/zipline/internal/bridge/ResultOrCallbackSerializer;", "callInternal$zipline_release", "(Lapp/cash/zipline/ZiplineService;Lapp/cash/zipline/ZiplineFunction;Lapp/cash/zipline/internal/bridge/ArgsListSerializer;Lapp/cash/zipline/internal/bridge/ResultOrCallbackSerializer;[Ljava/lang/Object;)Ljava/lang/Object;", "callSuspending", "(Lapp/cash/zipline/ZiplineService;I[Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callSuspendingInternal", "resultOrCallbackSerializer", "suspendCallbackSerializer", "Lkotlinx/serialization/KSerializer;", "callSuspendingInternal$zipline_release", "(Lapp/cash/zipline/ZiplineService;Lapp/cash/zipline/ZiplineFunction;Lapp/cash/zipline/internal/bridge/ArgsListSerializer;Lapp/cash/zipline/internal/bridge/ResultOrCallbackSerializer;Lkotlinx/serialization/KSerializer;[Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toString", "withApiMismatchMessage", "Lkotlin/Result;", "called", "withApiMismatchMessage-KWTtemM", "(Ljava/lang/Object;Lapp/cash/zipline/ZiplineFunction;)Ljava/lang/Object;", "ServiceState", "RealSuspendCallback", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class OutboundCallHandler {
    private final ZiplineServiceAdapter<?> adapter;
    private final Endpoint endpoint;
    private final ZiplineScope scope;
    private final String serviceName;
    private final ServiceState serviceState;
    private final ZiplineServiceType<?> sourceType;

    public OutboundCallHandler(ZiplineServiceType<?> sourceType, String serviceName, Endpoint endpoint, ZiplineServiceAdapter<?> adapter, ZiplineScope scope, ServiceState serviceState) {
        Intrinsics.checkNotNullParameter(sourceType, "sourceType");
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(serviceState, "serviceState");
        this.sourceType = sourceType;
        this.serviceName = serviceName;
        this.endpoint = endpoint;
        this.adapter = adapter;
        this.scope = scope;
        this.serviceState = serviceState;
    }

    public final ZiplineServiceType<?> getSourceType() {
        return this.sourceType;
    }

    /* JADX INFO: renamed from: getScope$zipline_release, reason: from getter */
    public final ZiplineScope getScope() {
        return this.scope;
    }

    public /* synthetic */ OutboundCallHandler(ZiplineServiceType ziplineServiceType, String str, Endpoint endpoint, ZiplineServiceAdapter ziplineServiceAdapter, ZiplineScope ziplineScope, ServiceState serviceState, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(ziplineServiceType, str, endpoint, ziplineServiceAdapter, ziplineScope, (i & 32) != 0 ? new ServiceState() : serviceState);
    }

    /* JADX INFO: renamed from: getServiceState$zipline_release, reason: from getter */
    public final ServiceState getServiceState() {
        return this.serviceState;
    }

    public final OutboundCallHandler withScope(ZiplineScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        return new OutboundCallHandler(this.sourceType, this.serviceName, this.endpoint, this.adapter, scope, this.serviceState);
    }

    public final SerializableZiplineServiceType getTargetType() {
        return this.endpoint.getOpposite$zipline_release().serviceType(this.serviceName);
    }

    public final <T extends ZiplineService> T outboundService() {
        T t = (T) this.adapter.outboundService(this);
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of app.cash.zipline.internal.bridge.OutboundCallHandler.outboundService");
        return t;
    }

    public final Object call(ZiplineService service, int functionIndex, Object... args) {
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(args, "args");
        Object obj = this.sourceType.getFunctions().get(functionIndex);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type app.cash.zipline.internal.bridge.ReturningZiplineFunction<*>");
        ReturningZiplineFunction returningZiplineFunction = (ReturningZiplineFunction) obj;
        return callInternal$zipline_release(service, returningZiplineFunction, returningZiplineFunction.getArgsListSerializer(), returningZiplineFunction.getResultSerializer(), Arrays.copyOf(args, args.length));
    }

    public final Object callInternal$zipline_release(final ZiplineService service, final ZiplineFunction<?> function, ArgsListSerializer argsListSerializer, final ResultOrCallbackSerializer<?> resultSerializer, Object... args) {
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(argsListSerializer, "argsListSerializer");
        Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
        Intrinsics.checkNotNullParameter(args, "args");
        boolean zIsClose = function.isClose();
        ServiceState serviceState = this.serviceState;
        if (zIsClose) {
            if (serviceState.getClosed()) {
                return Unit.INSTANCE;
            }
            this.serviceState.setClosed(true);
            this.scope.remove$zipline_release(this);
        } else if (serviceState.getClosed()) {
            throw new IllegalStateException(StringsKt.trimMargin$default("\n        |" + this.adapter + Stream.ID_UNKNOWN + this.serviceName + " is closed, failed to call:\n        |  " + function + "\n        ", null, 1, null).toString());
        }
        final Call callEncodeCall$zipline_release = this.endpoint.getCallCodec$zipline_release().encodeCall$zipline_release(new InternalCall(this.serviceName, argsListSerializer, null, null, function, null, ArraysKt.toList(args), 44, null), service);
        final Object objCallStart = !(service instanceof SuspendCallback) ? this.endpoint.getEventListener$zipline_release().callStart(callEncodeCall$zipline_release) : Unit.INSTANCE;
        final String strCall = this.endpoint.getOutboundChannel$zipline_release().call(callEncodeCall$zipline_release.getEncodedCall());
        return this.endpoint.withTakeScope$zipline_release(this.scope, new Function0() { // from class: app.cash.zipline.internal.bridge.OutboundCallHandler$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OutboundCallHandler.callInternal$lambda$1(this.f$0, resultSerializer, strCall, service, callEncodeCall$zipline_release, objCallStart, function);
            }
        });
    }

    static final Object callInternal$lambda$1(OutboundCallHandler outboundCallHandler, ResultOrCallbackSerializer resultOrCallbackSerializer, String str, ZiplineService ziplineService, Call call, Object obj, ZiplineFunction ziplineFunction) {
        CallResult callResultDecodeResult$zipline_release = outboundCallHandler.endpoint.getCallCodec$zipline_release().decodeResult$zipline_release(resultOrCallbackSerializer, str);
        if (!(ziplineService instanceof SuspendCallback)) {
            outboundCallHandler.endpoint.getEventListener$zipline_release().callEnd(call, callResultDecodeResult$zipline_release, obj);
        }
        Object objM8086withApiMismatchMessageKWTtemM = outboundCallHandler.m8086withApiMismatchMessageKWTtemM(callResultDecodeResult$zipline_release.getResult(), ziplineFunction);
        ResultKt.throwOnFailure(objM8086withApiMismatchMessageKWTtemM);
        return objM8086withApiMismatchMessageKWTtemM;
    }

    public final Object callSuspending(ZiplineService ziplineService, int i, Object[] objArr, Continuation<Object> continuation) {
        Object obj = this.sourceType.getFunctions().get(i);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type app.cash.zipline.internal.bridge.SuspendingZiplineFunction<*>");
        SuspendingZiplineFunction suspendingZiplineFunction = (SuspendingZiplineFunction) obj;
        return callSuspendingInternal$zipline_release(ziplineService, suspendingZiplineFunction, suspendingZiplineFunction.getArgsListSerializer(), suspendingZiplineFunction.getResultOrCallbackSerializer(), suspendingZiplineFunction.getSuspendCallbackSerializer(), Arrays.copyOf(objArr, objArr.length), continuation);
    }

    public final Object callSuspendingInternal$zipline_release(ZiplineService ziplineService, ZiplineFunction<?> ziplineFunction, ArgsListSerializer argsListSerializer, final ResultOrCallbackSerializer<?> resultOrCallbackSerializer, KSerializer<?> kSerializer, Object[] objArr, Continuation<Object> continuation) {
        CoroutineScopeKt.ensureActive(this.endpoint.getScope$zipline_release());
        if (this.serviceState.getClosed()) {
            throw new IllegalStateException(StringsKt.trimMargin$default("\n      |" + this.adapter + Stream.ID_UNKNOWN + this.serviceName + " is closed, failed to call:\n      |  " + ziplineFunction + "\n      ", null, 1, null).toString());
        }
        List list = ArraysKt.toList(objArr);
        final RealSuspendCallback realSuspendCallback = new RealSuspendCallback();
        InternalCall internalCall = new InternalCall(this.serviceName, argsListSerializer, kSerializer, null, ziplineFunction, realSuspendCallback, list, 8, null);
        realSuspendCallback.setInternalCall(internalCall);
        Call callEncodeCall$zipline_release = this.endpoint.getCallCodec$zipline_release().encodeCall$zipline_release(internalCall, ziplineService);
        realSuspendCallback.setExternalCall(callEncodeCall$zipline_release);
        realSuspendCallback.setCallStart(this.endpoint.getEventListener$zipline_release().callStart(callEncodeCall$zipline_release));
        final String strCall = this.endpoint.getOutboundChannel$zipline_release().call(callEncodeCall$zipline_release.getEncodedCall());
        EncodedResultOrCallback encodedResultOrCallback = (EncodedResultOrCallback) this.endpoint.withTakeScope$zipline_release(this.scope, new Function0() { // from class: app.cash.zipline.internal.bridge.OutboundCallHandler$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.endpoint.getCallCodec$zipline_release().decodeResultOrCallback$zipline_release(resultOrCallbackSerializer, strCall);
            }
        });
        final CancelCallback callback = encodedResultOrCallback.getValue().getCallback();
        if (callback == null) {
            CallResult callResult = encodedResultOrCallback.getCallResult();
            Intrinsics.checkNotNull(callResult);
            String passByReferenceName = realSuspendCallback.getPassByReferenceName();
            if (passByReferenceName != null) {
                this.endpoint.remove(passByReferenceName);
            }
            this.endpoint.getEventListener$zipline_release().callEnd(callEncodeCall$zipline_release, callResult, realSuspendCallback.getCallStart());
            Object objM8086withApiMismatchMessageKWTtemM = m8086withApiMismatchMessageKWTtemM(callResult.getResult(), ziplineFunction);
            ResultKt.throwOnFailure(objM8086withApiMismatchMessageKWTtemM);
            return objM8086withApiMismatchMessageKWTtemM;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        realSuspendCallback.setContinuation(cancellableContinuationImpl2);
        this.endpoint.getIncompleteContinuations$zipline_release().add(cancellableContinuationImpl2);
        cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: app.cash.zipline.internal.bridge.OutboundCallHandler$callSuspendingInternal$3$1

            /* JADX INFO: renamed from: app.cash.zipline.internal.bridge.OutboundCallHandler$callSuspendingInternal$3$1$1, reason: invalid class name */
            /* JADX INFO: compiled from: OutboundCallHandler.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
            @DebugMetadata(c = "app.cash.zipline.internal.bridge.OutboundCallHandler$callSuspendingInternal$3$1$1", f = "OutboundCallHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ CancelCallback $cancelCallback;
                final /* synthetic */ OutboundCallHandler.RealSuspendCallback<Object> $suspendCallback;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(OutboundCallHandler.RealSuspendCallback<Object> realSuspendCallback, CancelCallback cancelCallback, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$suspendCallback = realSuspendCallback;
                    this.$cancelCallback = cancelCallback;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$suspendCallback, this.$cancelCallback, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    if (!this.$suspendCallback.getCompleted()) {
                        this.$cancelCallback.cancel();
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                BuildersKt__Builders_commonKt.launch$default(this.this$0.endpoint.getScope$zipline_release(), null, null, new AnonymousClass1(realSuspendCallback, callback, null), 3, null);
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX INFO: renamed from: toString, reason: from getter */
    public String getServiceName() {
        return this.serviceName;
    }

    /* JADX INFO: compiled from: OutboundCallHandler.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lapp/cash/zipline/internal/bridge/OutboundCallHandler$ServiceState;", "", "<init>", "()V", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ServiceState {
        private boolean closed;

        public final boolean getClosed() {
            return this.closed;
        }

        public final void setClosed(boolean z) {
            this.closed = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: OutboundCallHandler.kt */
    @Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u00032\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010/\u001a\u0002002\u0006\u00101\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001eJ\u0010\u00102\u001a\u0002002\u0006\u00101\u001a\u000203H\u0016J\u001b\u00104\u001a\u0002002\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u000005H\u0002¢\u0006\u0002\u0010\u001eJ\n\u00106\u001a\u00020 H\u0096\u0080\u0004R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020&8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u00067"}, d2 = {"Lapp/cash/zipline/internal/bridge/OutboundCallHandler$RealSuspendCallback;", "R", "Lapp/cash/zipline/internal/bridge/SuspendCallback;", "Lapp/cash/zipline/internal/bridge/HasPassByReferenceName;", "Lapp/cash/zipline/ZiplineScoped;", "<init>", "(Lapp/cash/zipline/internal/bridge/OutboundCallHandler;)V", "internalCall", "Lapp/cash/zipline/internal/bridge/InternalCall;", "getInternalCall", "()Lapp/cash/zipline/internal/bridge/InternalCall;", "setInternalCall", "(Lapp/cash/zipline/internal/bridge/InternalCall;)V", "externalCall", "Lapp/cash/zipline/Call;", "getExternalCall", "()Lapp/cash/zipline/Call;", "setExternalCall", "(Lapp/cash/zipline/Call;)V", "continuation", "Lkotlin/coroutines/Continuation;", "getContinuation", "()Lkotlin/coroutines/Continuation;", "setContinuation", "(Lkotlin/coroutines/Continuation;)V", "callStart", "", "getCallStart", "()Ljava/lang/Object;", "setCallStart", "(Ljava/lang/Object;)V", "passByReferenceName", "", "getPassByReferenceName", "()Ljava/lang/String;", "setPassByReferenceName", "(Ljava/lang/String;)V", "scope", "Lapp/cash/zipline/ZiplineScope;", "getScope", "()Lapp/cash/zipline/ZiplineScope;", "completed", "", "getCompleted", "()Z", "setCompleted", "(Z)V", "success", "", "result", "failure", "", NotificationCompat.CATEGORY_CALL, "Lkotlin/Result;", "toString", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class RealSuspendCallback<R> implements SuspendCallback<R>, HasPassByReferenceName, ZiplineScoped {
        private Object callStart;
        private boolean completed;
        public Continuation<? super R> continuation;
        public Call externalCall;
        public InternalCall internalCall;
        private String passByReferenceName;

        public RealSuspendCallback() {
        }

        @Override // app.cash.zipline.ZiplineService, java.lang.AutoCloseable
        public /* bridge */ void close() {
            ZiplineService.CC.$default$close(this);
        }

        public final InternalCall getInternalCall() {
            InternalCall internalCall = this.internalCall;
            if (internalCall != null) {
                return internalCall;
            }
            Intrinsics.throwUninitializedPropertyAccessException("internalCall");
            return null;
        }

        public final void setInternalCall(InternalCall internalCall) {
            Intrinsics.checkNotNullParameter(internalCall, "<set-?>");
            this.internalCall = internalCall;
        }

        public final Call getExternalCall() {
            Call call = this.externalCall;
            if (call != null) {
                return call;
            }
            Intrinsics.throwUninitializedPropertyAccessException("externalCall");
            return null;
        }

        public final void setExternalCall(Call call) {
            Intrinsics.checkNotNullParameter(call, "<set-?>");
            this.externalCall = call;
        }

        public final Continuation<R> getContinuation() {
            Continuation<? super R> continuation = this.continuation;
            if (continuation != null) {
                return continuation;
            }
            Intrinsics.throwUninitializedPropertyAccessException("continuation");
            return null;
        }

        public final void setContinuation(Continuation<? super R> continuation) {
            Intrinsics.checkNotNullParameter(continuation, "<set-?>");
            this.continuation = continuation;
        }

        public final Object getCallStart() {
            return this.callStart;
        }

        public final void setCallStart(Object obj) {
            this.callStart = obj;
        }

        @Override // app.cash.zipline.internal.bridge.HasPassByReferenceName
        public String getPassByReferenceName() {
            return this.passByReferenceName;
        }

        @Override // app.cash.zipline.internal.bridge.HasPassByReferenceName
        public void setPassByReferenceName(String str) {
            this.passByReferenceName = str;
        }

        @Override // app.cash.zipline.ZiplineScoped
        public ZiplineScope getScope() {
            return OutboundCallHandler.this.getScope();
        }

        public final boolean getCompleted() {
            return this.completed;
        }

        public final void setCompleted(boolean z) {
            this.completed = z;
        }

        @Override // app.cash.zipline.internal.bridge.SuspendCallback
        public void success(R result) {
            Result.Companion companion = Result.INSTANCE;
            call(Result.m8936constructorimpl(result));
        }

        @Override // app.cash.zipline.internal.bridge.SuspendCallback
        public void failure(Throwable result) {
            Intrinsics.checkNotNullParameter(result, "result");
            Result.Companion companion = Result.INSTANCE;
            call(Result.m8936constructorimpl(ResultKt.createFailure(result)));
        }

        private final void call(Object result) {
            Call lastInboundCall = OutboundCallHandler.this.endpoint.getCallCodec$zipline_release().getLastInboundCall();
            Intrinsics.checkNotNull(lastInboundCall);
            CallResult callResult = new CallResult(result, lastInboundCall.getEncodedCall(), lastInboundCall.getServiceNames());
            this.completed = true;
            String passByReferenceName = getPassByReferenceName();
            if (passByReferenceName != null) {
                OutboundCallHandler.this.endpoint.remove(passByReferenceName);
            }
            OutboundCallHandler.this.endpoint.getIncompleteContinuations$zipline_release().remove(getContinuation());
            OutboundCallHandler.this.endpoint.getEventListener$zipline_release().callEnd(getExternalCall(), callResult, this.callStart);
            getContinuation().resumeWith(result);
        }

        public String toString() {
            return "SuspendCallback/" + getInternalCall();
        }
    }

    /* JADX INFO: renamed from: withApiMismatchMessage-KWTtemM, reason: not valid java name */
    private final <T> Object m8086withApiMismatchMessageKWTtemM(Object obj, ZiplineFunction<?> ziplineFunction) {
        if (!Result.m8943isSuccessimpl(obj)) {
            Throwable thM8939exceptionOrNullimpl = Result.m8939exceptionOrNullimpl(obj);
            Intrinsics.checkNotNull(thM8939exceptionOrNullimpl);
            if (thM8939exceptionOrNullimpl instanceof ZiplineApiMismatchException) {
                try {
                    if (StringsKt.contains$default((CharSequence) ((ZiplineApiMismatchException) thM8939exceptionOrNullimpl).getMessage(), (CharSequence) ZiplineApiMismatchException.UNKNOWN_FUNCTION, false, 2, (Object) null)) {
                        SerializableZiplineServiceType targetType = getTargetType();
                        if (targetType != null) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("no such method (incompatible API versions?)\n\tcalled service:\n\t\t");
                            sb.append(this.serviceName);
                            sb.append("\n\tcalled function:\n\t\t");
                            sb.append(ziplineFunction.getSignature());
                            sb.append("\n\tavailable functions:\n");
                            CollectionsKt.joinTo(targetType.getFunctions(), sb, (60 & 2) != 0 ? ", " : "\n", (60 & 4) != 0 ? "" : null, (60 & 8) != 0 ? "" : null, (60 & 16) != 0 ? -1 : 0, (60 & 32) != 0 ? "..." : null, (60 & 64) != 0 ? null : new Function1() { // from class: app.cash.zipline.internal.bridge.OutboundCallHandler$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return OutboundCallHandler.withApiMismatchMessage_KWTtemM$lambda$0$0((SerializableZiplineFunction) obj2);
                                }
                            });
                            String string = sb.toString();
                            Result.Companion companion = Result.INSTANCE;
                            return Result.m8936constructorimpl(ResultKt.createFailure(new ZiplineApiMismatchException(string)));
                        }
                    } else if (StringsKt.contains$default((CharSequence) ((ZiplineApiMismatchException) thM8939exceptionOrNullimpl).getMessage(), (CharSequence) ZiplineApiMismatchException.UNKNOWN_SERVICE, false, 2, (Object) null)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("no such service (service closed?)\n\tcalled service:\n\t\t");
                        sb2.append(this.serviceName);
                        sb2.append("\n\tavailable services:\n");
                        CollectionsKt.joinTo(this.endpoint.getOpposite$zipline_release().getServiceNames(), sb2, (60 & 2) != 0 ? ", " : "\n", (60 & 4) != 0 ? "" : null, (60 & 8) != 0 ? "" : null, (60 & 16) != 0 ? -1 : 0, (60 & 32) != 0 ? "..." : null, (60 & 64) != 0 ? null : new Function1() { // from class: app.cash.zipline.internal.bridge.OutboundCallHandler$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return OutboundCallHandler.withApiMismatchMessage_KWTtemM$lambda$1$0((String) obj2);
                            }
                        });
                        String string2 = sb2.toString();
                        Result.Companion companion2 = Result.INSTANCE;
                        return Result.m8936constructorimpl(ResultKt.createFailure(new ZiplineApiMismatchException(string2)));
                    }
                } catch (Exception unused) {
                }
            }
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence withApiMismatchMessage_KWTtemM$lambda$0$0(SerializableZiplineFunction it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "\t\t" + it.getSignature();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence withApiMismatchMessage_KWTtemM$lambda$1$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "\t\t" + it;
    }
}
