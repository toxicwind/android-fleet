package app.cash.zipline.internal.bridge;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineApiMismatchException;
import app.cash.zipline.ZiplineFunction;
import app.cash.zipline.ZiplineScope;
import app.cash.zipline.ZiplineScoped;
import app.cash.zipline.ZiplineService;
import io.ktor.http.ContentType;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.json.JsonDecoder;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: calls.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0096\u0080\u0004J\u0012\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\f\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002J:\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0016\"\b\b\u0000\u0010\u0017*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0010\u0010\u001b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001d\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00020\bX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001f"}, d2 = {"Lapp/cash/zipline/internal/bridge/RealCallSerializer;", "Lkotlinx/serialization/KSerializer;", "Lapp/cash/zipline/internal/bridge/InternalCall;", "endpoint", "Lapp/cash/zipline/internal/bridge/Endpoint;", "<init>", "(Lapp/cash/zipline/internal/bridge/Endpoint;)V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "unknownService", "Lapp/cash/zipline/internal/bridge/InboundService;", "unknownFunction", "Lapp/cash/zipline/ZiplineFunction;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "functionId", "", "suspendCallback", "Lapp/cash/zipline/internal/bridge/SuspendCallback;", "", ContentType.Message.TYPE, "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RealCallSerializer implements KSerializer<InternalCall> {
    private final SerialDescriptor descriptor;
    private final Endpoint endpoint;

    public RealCallSerializer(Endpoint endpoint) {
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        this.endpoint = endpoint;
        this.descriptor = SerialDescriptorsKt.buildClassSerialDescriptor("RealCall", new SerialDescriptor[0], new Function1() { // from class: app.cash.zipline.internal.bridge.RealCallSerializer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RealCallSerializer.descriptor$lambda$0((ClassSerialDescriptorBuilder) obj);
            }
        });
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    static final Unit descriptor$lambda$0(ClassSerialDescriptorBuilder buildClassSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildClassSerialDescriptor, "$this$buildClassSerialDescriptor");
        ClassSerialDescriptorBuilder.element$default(buildClassSerialDescriptor, NotificationCompat.CATEGORY_SERVICE, BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE).getDescriptor(), null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildClassSerialDescriptor, "function", BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE).getDescriptor(), null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildClassSerialDescriptor, "callback", BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE).getDescriptor(), null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildClassSerialDescriptor, "args", CallsKt.argsListDescriptor, null, false, 12, null);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, InternalCall value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor);
        compositeEncoderBeginStructure.encodeStringElement(getDescriptor(), 0, value.getServiceName());
        compositeEncoderBeginStructure.encodeStringElement(getDescriptor(), 1, value.getFunction().getId());
        if (value.getSuspendCallback() != null) {
            SerialDescriptor descriptor2 = getDescriptor();
            KSerializer<?> suspendCallbackSerializer = value.getSuspendCallbackSerializer();
            Intrinsics.checkNotNull(suspendCallbackSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any?>");
            compositeEncoderBeginStructure.encodeSerializableElement(descriptor2, 2, suspendCallbackSerializer, value.getSuspendCallback());
        }
        SerialDescriptor descriptor3 = getDescriptor();
        ArgsListSerializer argsListSerializer = value.getArgsListSerializer();
        Intrinsics.checkNotNull(argsListSerializer);
        compositeEncoderBeginStructure.encodeSerializableElement(descriptor3, 3, argsListSerializer, value.getArgs());
        compositeEncoderBeginStructure.endStructure(descriptor);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public InternalCall deserialize(Decoder decoder) {
        String str;
        SuspendCallback suspendCallback;
        RealZiplineServiceType<T> type$zipline_release;
        Map functionsById;
        ArgsListSerializer argsListSerializer;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        ZiplineScope takeScope = this.endpoint.getTakeScope();
        try {
            SerialDescriptor descriptor = getDescriptor();
            CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor);
            List listEmptyList = CollectionsKt.emptyList();
            String strDecodeStringElement = "";
            String strDecodeStringElement2 = strDecodeStringElement;
            ZiplineFunction ziplineFunctionUnknownFunction = null;
            InboundService<?> inboundService = null;
            SuspendCallback suspendCallback2 = null;
            while (true) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(getDescriptor());
                if (iDecodeElementIndex == -1) {
                    SuspendCallback suspendCallback3 = suspendCallback2;
                    InboundService<?> inboundServiceUnknownService = inboundService == null ? unknownService() : inboundService;
                    if (ziplineFunctionUnknownFunction == null) {
                        if (inboundService == null) {
                            str = ZiplineApiMismatchException.UNKNOWN_SERVICE;
                        } else {
                            str = ZiplineApiMismatchException.UNKNOWN_FUNCTION;
                        }
                        ziplineFunctionUnknownFunction = unknownFunction(strDecodeStringElement, suspendCallback3, str);
                    }
                    InternalCall internalCall = new InternalCall(strDecodeStringElement2, null, null, inboundServiceUnknownService, ziplineFunctionUnknownFunction, suspendCallback3, listEmptyList, 6, null);
                    compositeDecoderBeginStructure.endStructure(descriptor);
                    this.endpoint.setTakeScope$zipline_release(takeScope);
                    return internalCall;
                }
                if (iDecodeElementIndex == 0) {
                    suspendCallback = suspendCallback2;
                    strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(getDescriptor(), iDecodeElementIndex);
                    inboundService = this.endpoint.getInboundServices$zipline_release().get(strDecodeStringElement2);
                    Endpoint endpoint = this.endpoint;
                    ZiplineService service$zipline_release = inboundService != null ? inboundService.getService$zipline_release() : null;
                    ZiplineScoped ziplineScoped = service$zipline_release instanceof ZiplineScoped ? (ZiplineScoped) service$zipline_release : null;
                    endpoint.setTakeScope$zipline_release(ziplineScoped != null ? ziplineScoped.getScope() : null);
                } else if (iDecodeElementIndex == 1) {
                    suspendCallback = suspendCallback2;
                    strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(getDescriptor(), iDecodeElementIndex);
                    ziplineFunctionUnknownFunction = (inboundService == null || (type$zipline_release = inboundService.getType$zipline_release()) == 0 || (functionsById = type$zipline_release.getFunctionsById()) == null) ? null : (ZiplineFunction) functionsById.get(strDecodeStringElement);
                } else if (iDecodeElementIndex == 2) {
                    KSerializer<?> suspendCallbackSerializer = ziplineFunctionUnknownFunction instanceof SuspendingZiplineFunction ? ((SuspendingZiplineFunction) ziplineFunctionUnknownFunction).getSuspendCallbackSerializer() : CallsKt.getFailureSuspendCallbackSerializer();
                    Intrinsics.checkNotNull(suspendCallbackSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<app.cash.zipline.internal.bridge.SuspendCallback<kotlin.Any?>>");
                    suspendCallback2 = (SuspendCallback) CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), iDecodeElementIndex, suspendCallbackSerializer, null, 8, null);
                } else if (iDecodeElementIndex == 3) {
                    if (ziplineFunctionUnknownFunction instanceof SuspendingZiplineFunction) {
                        argsListSerializer = ((SuspendingZiplineFunction) ziplineFunctionUnknownFunction).getArgsListSerializer();
                    } else {
                        argsListSerializer = ziplineFunctionUnknownFunction instanceof ReturningZiplineFunction ? ((ReturningZiplineFunction) ziplineFunctionUnknownFunction).getArgsListSerializer() : null;
                    }
                    if (argsListSerializer != null) {
                        suspendCallback = suspendCallback2;
                        listEmptyList = (List) CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), iDecodeElementIndex, argsListSerializer, null, 8, null);
                    } else {
                        suspendCallback = suspendCallback2;
                        ((JsonDecoder) decoder).decodeJsonElement();
                    }
                } else {
                    throw new IllegalStateException(("Unexpected index: " + iDecodeElementIndex).toString());
                }
                suspendCallback2 = suspendCallback;
            }
        } catch (Throwable th) {
            this.endpoint.setTakeScope$zipline_release(takeScope);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InboundService<?> unknownService() {
        return new InboundService<>(new RealZiplineServiceType("Unknown", CollectionsKt.emptyList()), new ZiplineService() { // from class: app.cash.zipline.internal.bridge.RealCallSerializer.unknownService.1
            @Override // app.cash.zipline.ZiplineService, java.lang.AutoCloseable
            public /* bridge */ void close() {
                ZiplineService.CC.$default$close(this);
            }
        }, this.endpoint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T extends ZiplineService> ZiplineFunction<T> unknownFunction(String functionId, SuspendCallback<Object> suspendCallback, final String message) {
        if (suspendCallback != null) {
            return new SuspendingZiplineFunction<T>(functionId, CollectionsKt.emptyList(), BuiltinSerializersKt.serializer(IntCompanionObject.INSTANCE), CallsKt.getFailureSuspendCallbackSerializer()) { // from class: app.cash.zipline.internal.bridge.RealCallSerializer.unknownFunction.1
                /* JADX WARN: Incorrect types in method signature: (TT;Ljava/util/List<*>;Lkotlin/coroutines/Continuation<*>;)Ljava/lang/Object; */
                @Override // app.cash.zipline.internal.bridge.SuspendingZiplineFunction
                public Object callSuspending(ZiplineService ziplineService, List list, Continuation continuation) throws ZiplineApiMismatchException {
                    throw new ZiplineApiMismatchException(message);
                }
            };
        }
        return new ReturningZiplineFunction<T>(functionId, CollectionsKt.emptyList(), BuiltinSerializersKt.serializer(IntCompanionObject.INSTANCE)) { // from class: app.cash.zipline.internal.bridge.RealCallSerializer.unknownFunction.2
            /* JADX WARN: Incorrect types in method signature: (TT;Ljava/util/List<*>;)Ljava/lang/Void; */
            @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
            public Void call(ZiplineService service, List args) throws ZiplineApiMismatchException {
                Intrinsics.checkNotNullParameter(service, "service");
                Intrinsics.checkNotNullParameter(args, "args");
                throw new ZiplineApiMismatchException(message);
            }
        };
    }
}
