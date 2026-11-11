package app.cash.zipline.internal.bridge;

import app.cash.zipline.ZiplineFunction;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: calls.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0012\b\u0000\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t\u0012\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b\u0012\u0012\b\u0002\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\r\u0012\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\n\u0010 \u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0015\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006!"}, d2 = {"Lapp/cash/zipline/internal/bridge/InternalCall;", "", "serviceName", "", "argsListSerializer", "Lapp/cash/zipline/internal/bridge/ArgsListSerializer;", "suspendCallbackSerializer", "Lkotlinx/serialization/KSerializer;", "inboundService", "Lapp/cash/zipline/internal/bridge/InboundService;", "function", "Lapp/cash/zipline/ZiplineFunction;", "suspendCallback", "Lapp/cash/zipline/internal/bridge/SuspendCallback;", "args", "", "<init>", "(Ljava/lang/String;Lapp/cash/zipline/internal/bridge/ArgsListSerializer;Lkotlinx/serialization/KSerializer;Lapp/cash/zipline/internal/bridge/InboundService;Lapp/cash/zipline/ZiplineFunction;Lapp/cash/zipline/internal/bridge/SuspendCallback;Ljava/util/List;)V", "getServiceName", "()Ljava/lang/String;", "getArgsListSerializer", "()Lapp/cash/zipline/internal/bridge/ArgsListSerializer;", "getSuspendCallbackSerializer", "()Lkotlinx/serialization/KSerializer;", "getInboundService", "()Lapp/cash/zipline/internal/bridge/InboundService;", "getFunction", "()Lapp/cash/zipline/ZiplineFunction;", "getSuspendCallback", "()Lapp/cash/zipline/internal/bridge/SuspendCallback;", "getArgs", "()Ljava/util/List;", "toString", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class InternalCall {
    private final List<?> args;
    private final ArgsListSerializer argsListSerializer;
    private final ZiplineFunction<?> function;
    private final InboundService<?> inboundService;
    private final String serviceName;
    private final SuspendCallback<Object> suspendCallback;
    private final KSerializer<?> suspendCallbackSerializer;

    public InternalCall(String serviceName, ArgsListSerializer argsListSerializer, KSerializer<?> kSerializer, InboundService<?> inboundService, ZiplineFunction<?> function, SuspendCallback<Object> suspendCallback, List<?> args) {
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(args, "args");
        this.serviceName = serviceName;
        this.argsListSerializer = argsListSerializer;
        this.suspendCallbackSerializer = kSerializer;
        this.inboundService = inboundService;
        this.function = function;
        this.suspendCallback = suspendCallback;
        this.args = args;
    }

    public /* synthetic */ InternalCall(String str, ArgsListSerializer argsListSerializer, KSerializer kSerializer, InboundService inboundService, ZiplineFunction ziplineFunction, SuspendCallback suspendCallback, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : argsListSerializer, (i & 4) != 0 ? null : kSerializer, (i & 8) != 0 ? null : inboundService, ziplineFunction, (i & 32) != 0 ? null : suspendCallback, list);
    }

    public final String getServiceName() {
        return this.serviceName;
    }

    public final ArgsListSerializer getArgsListSerializer() {
        return this.argsListSerializer;
    }

    public final KSerializer<?> getSuspendCallbackSerializer() {
        return this.suspendCallbackSerializer;
    }

    public final InboundService<?> getInboundService() {
        return this.inboundService;
    }

    public final ZiplineFunction<?> getFunction() {
        return this.function;
    }

    public final SuspendCallback<Object> getSuspendCallback() {
        return this.suspendCallback;
    }

    public final List<?> getArgs() {
        return this.args;
    }

    public String toString() {
        return "Call(receiver=" + this.serviceName + ", function=" + this.function.getSignature() + ", args=" + this.args + ")";
    }
}
