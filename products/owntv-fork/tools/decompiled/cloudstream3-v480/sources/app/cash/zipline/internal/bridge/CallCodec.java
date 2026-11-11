package app.cash.zipline.internal.bridge;

import androidx.core.app.NotificationCompat;
import app.cash.zipline.Call;
import app.cash.zipline.CallResult;
import app.cash.zipline.ZiplineService;
import app.cash.zipline.internal.JsonEngineKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;

/* JADX INFO: compiled from: CallCodec.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0000¢\u0006\u0002\b!J\u0015\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\nH\u0000¢\u0006\u0002\b$J+\u0010%\u001a\u00020&2\n\u0010'\u001a\u0006\u0012\u0002\b\u00030(2\u000e\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010*H\u0000¢\u0006\u0004\b+\u0010,J%\u0010-\u001a\u00020.2\n\u0010'\u001a\u0006\u0012\u0002\b\u00030/2\n\u0010)\u001a\u0006\u0012\u0002\b\u000300H\u0000¢\u0006\u0002\b1J!\u00102\u001a\u00020&2\n\u00103\u001a\u0006\u0012\u0002\b\u0003042\u0006\u00105\u001a\u00020\nH\u0000¢\u0006\u0002\b6J!\u00107\u001a\u00020.2\n\u00103\u001a\u0006\u0012\u0002\b\u0003042\u0006\u00108\u001a\u00020\nH\u0000¢\u0006\u0002\b9R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R(\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006:"}, d2 = {"Lapp/cash/zipline/internal/bridge/CallCodec;", "", "endpoint", "Lapp/cash/zipline/internal/bridge/Endpoint;", "<init>", "(Lapp/cash/zipline/internal/bridge/Endpoint;)V", "callSerializer", "Lapp/cash/zipline/internal/bridge/RealCallSerializer;", "decodedServiceNames", "", "", "getDecodedServiceNames", "()Ljava/util/List;", "encodedServiceNames", "getEncodedServiceNames", "lastInboundCall", "Lapp/cash/zipline/Call;", "getLastInboundCall", "()Lapp/cash/zipline/Call;", "setLastInboundCall", "(Lapp/cash/zipline/Call;)V", "nextOutboundCallCallback", "Lkotlin/Function1;", "", "getNextOutboundCallCallback", "()Lkotlin/jvm/functions/Function1;", "setNextOutboundCallCallback", "(Lkotlin/jvm/functions/Function1;)V", "encodeCall", "internalCall", "Lapp/cash/zipline/internal/bridge/InternalCall;", NotificationCompat.CATEGORY_SERVICE, "Lapp/cash/zipline/ZiplineService;", "encodeCall$zipline_release", "decodeCall", "callJson", "decodeCall$zipline_release", "encodeResult", "Lapp/cash/zipline/CallResult;", "function", "Lapp/cash/zipline/internal/bridge/ReturningZiplineFunction;", "result", "Lkotlin/Result;", "encodeResult$zipline_release", "(Lapp/cash/zipline/internal/bridge/ReturningZiplineFunction;Ljava/lang/Object;)Lapp/cash/zipline/CallResult;", "encodeResultOrCallback", "Lapp/cash/zipline/internal/bridge/EncodedResultOrCallback;", "Lapp/cash/zipline/internal/bridge/SuspendingZiplineFunction;", "Lapp/cash/zipline/internal/bridge/ResultOrCallback;", "encodeResultOrCallback$zipline_release", "decodeResult", "serializer", "Lapp/cash/zipline/internal/bridge/ResultOrCallbackSerializer;", "resultJson", "decodeResult$zipline_release", "decodeResultOrCallback", "resultOrCallbackJson", "decodeResultOrCallback$zipline_release", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CallCodec {
    private final RealCallSerializer callSerializer;
    private final List<String> decodedServiceNames;
    private final List<String> encodedServiceNames;
    private final Endpoint endpoint;
    private Call lastInboundCall;
    private Function1<? super Call, Unit> nextOutboundCallCallback;

    public CallCodec(Endpoint endpoint) {
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        this.endpoint = endpoint;
        this.callSerializer = new RealCallSerializer(endpoint);
        this.decodedServiceNames = new ArrayList();
        this.encodedServiceNames = new ArrayList();
    }

    public final List<String> getDecodedServiceNames() {
        return this.decodedServiceNames;
    }

    public final List<String> getEncodedServiceNames() {
        return this.encodedServiceNames;
    }

    public final Call getLastInboundCall() {
        return this.lastInboundCall;
    }

    public final void setLastInboundCall(Call call) {
        this.lastInboundCall = call;
    }

    public final Function1<Call, Unit> getNextOutboundCallCallback() {
        return this.nextOutboundCallCallback;
    }

    public final void setNextOutboundCallCallback(Function1<? super Call, Unit> function1) {
        this.nextOutboundCallCallback = function1;
    }

    public final Call encodeCall$zipline_release(InternalCall internalCall, ZiplineService service) {
        Intrinsics.checkNotNullParameter(internalCall, "internalCall");
        Intrinsics.checkNotNullParameter(service, "service");
        this.encodedServiceNames.clear();
        Call call = new Call(internalCall.getServiceName(), service, internalCall.getFunction(), internalCall.getArgs(), JsonEngineKt.encodeToStringFast(this.endpoint.getJson(), this.callSerializer, internalCall), this.encodedServiceNames);
        Function1<? super Call, Unit> function1 = this.nextOutboundCallCallback;
        if (function1 != null) {
            function1.invoke(call);
            this.nextOutboundCallCallback = null;
        }
        return call;
    }

    public final InternalCall decodeCall$zipline_release(String callJson) {
        Intrinsics.checkNotNullParameter(callJson, "callJson");
        this.decodedServiceNames.clear();
        InternalCall internalCall = (InternalCall) JsonEngineKt.decodeFromStringFast(this.endpoint.getJson(), this.callSerializer, callJson);
        InboundService<?> inboundService = internalCall.getInboundService();
        if (inboundService == null) {
            throw new IllegalStateException(("no handler for " + internalCall.getServiceName()).toString());
        }
        this.lastInboundCall = new Call(internalCall.getServiceName(), inboundService.getService$zipline_release(), internalCall.getFunction(), internalCall.getArgs(), callJson, this.decodedServiceNames);
        return internalCall;
    }

    public final CallResult encodeResult$zipline_release(ReturningZiplineFunction<?> function, Object result) {
        Intrinsics.checkNotNullParameter(function, "function");
        this.encodedServiceNames.clear();
        Json json$zipline_release = this.endpoint.getJson();
        ResultOrCallbackSerializer<? extends Object> resultSerializer = function.getResultSerializer();
        Intrinsics.checkNotNull(resultSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any?>");
        return new CallResult(result, JsonEngineKt.encodeToStringFast(json$zipline_release, resultSerializer, new ResultOrCallback(Result.m8935boximpl(result), null, 2, null)), this.encodedServiceNames);
    }

    public final EncodedResultOrCallback encodeResultOrCallback$zipline_release(SuspendingZiplineFunction<?> function, ResultOrCallback<?> result) {
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(result, "result");
        this.encodedServiceNames.clear();
        Json json$zipline_release = this.endpoint.getJson();
        ResultOrCallbackSerializer<? extends Object> resultOrCallbackSerializer = function.getResultOrCallbackSerializer();
        Intrinsics.checkNotNull(resultOrCallbackSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any?>");
        return new EncodedResultOrCallback(result, JsonEngineKt.encodeToStringFast(json$zipline_release, resultOrCallbackSerializer, result), this.encodedServiceNames);
    }

    public final CallResult decodeResult$zipline_release(ResultOrCallbackSerializer<?> serializer, String resultJson) {
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(resultJson, "resultJson");
        this.decodedServiceNames.clear();
        Result resultM8087getResultxLWZpok = ((ResultOrCallback) JsonEngineKt.decodeFromStringFast(this.endpoint.getJson(), serializer, resultJson)).m8087getResultxLWZpok();
        Intrinsics.checkNotNull(resultM8087getResultxLWZpok);
        return new CallResult(resultM8087getResultxLWZpok.getValue(), resultJson, this.decodedServiceNames);
    }

    public final EncodedResultOrCallback decodeResultOrCallback$zipline_release(ResultOrCallbackSerializer<?> serializer, String resultOrCallbackJson) {
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(resultOrCallbackJson, "resultOrCallbackJson");
        this.decodedServiceNames.clear();
        return new EncodedResultOrCallback((ResultOrCallback) JsonEngineKt.decodeFromStringFast(this.endpoint.getJson(), serializer, resultOrCallbackJson), resultOrCallbackJson, this.decodedServiceNames);
    }
}
