package app.cash.zipline.internal.bridge;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: calls.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u0005\u0010\u0006J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0080\u0004J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\t\u001a\u00020\nX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lapp/cash/zipline/internal/bridge/ResultOrCallbackSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/serialization/KSerializer;", "Lapp/cash/zipline/internal/bridge/ResultOrCallback;", "successSerializer", "<init>", "(Lkotlinx/serialization/KSerializer;)V", "getSuccessSerializer$zipline_release", "()Lkotlinx/serialization/KSerializer;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ResultOrCallbackSerializer<T> implements KSerializer<ResultOrCallback<T>> {
    private final SerialDescriptor descriptor;
    private final KSerializer<T> successSerializer;

    public ResultOrCallbackSerializer(KSerializer<T> successSerializer) {
        Intrinsics.checkNotNullParameter(successSerializer, "successSerializer");
        this.successSerializer = successSerializer;
        this.descriptor = SerialDescriptorsKt.buildClassSerialDescriptor("Result", new SerialDescriptor[0], new Function1() { // from class: app.cash.zipline.internal.bridge.ResultOrCallbackSerializer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultOrCallbackSerializer.descriptor$lambda$0(this.f$0, (ClassSerialDescriptorBuilder) obj);
            }
        });
    }

    public final KSerializer<T> getSuccessSerializer$zipline_release() {
        return this.successSerializer;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    static final Unit descriptor$lambda$0(ResultOrCallbackSerializer resultOrCallbackSerializer, ClassSerialDescriptorBuilder buildClassSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildClassSerialDescriptor, "$this$buildClassSerialDescriptor");
        ClassSerialDescriptorBuilder.element$default(buildClassSerialDescriptor, "cancelCallback", CallsKt.getCancelCallbackSerializer().getDescriptor(), null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildClassSerialDescriptor, "failure", ThrowableSerializer.INSTANCE.getDescriptor(), null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildClassSerialDescriptor, "success", resultOrCallbackSerializer.successSerializer.getDescriptor(), null, false, 12, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, ResultOrCallback<T> value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor);
        if (value.getCallback() != null) {
            compositeEncoderBeginStructure.encodeSerializableElement(getDescriptor(), 0, CallsKt.getCancelCallbackSerializer(), value.getCallback());
        } else {
            Result<T> resultM8087getResultxLWZpok = value.m8087getResultxLWZpok();
            Intrinsics.checkNotNull(resultM8087getResultxLWZpok);
            Object value2 = resultM8087getResultxLWZpok.getValue();
            Throwable thM8939exceptionOrNullimpl = Result.m8939exceptionOrNullimpl(value2);
            if (thM8939exceptionOrNullimpl != null) {
                compositeEncoderBeginStructure.encodeSerializableElement(getDescriptor(), 1, ThrowableSerializer.INSTANCE, thM8939exceptionOrNullimpl);
            } else {
                SerialDescriptor descriptor2 = getDescriptor();
                KSerializer<T> successSerializer$zipline_release = getSuccessSerializer$zipline_release();
                if (Result.m8942isFailureimpl(value2)) {
                    value2 = null;
                }
                compositeEncoderBeginStructure.encodeSerializableElement(descriptor2, 2, successSerializer$zipline_release, value2);
            }
        }
        compositeEncoderBeginStructure.endStructure(descriptor);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public ResultOrCallback<T> deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor);
        Result resultM8935boximpl = null;
        CancelCallback cancelCallback = null;
        while (true) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(getDescriptor());
            if (iDecodeElementIndex == -1) {
                ResultOrCallback<T> resultOrCallback = new ResultOrCallback<>(resultM8935boximpl, cancelCallback);
                compositeDecoderBeginStructure.endStructure(descriptor);
                return resultOrCallback;
            }
            if (iDecodeElementIndex == 0) {
                cancelCallback = (CancelCallback) CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), 0, CallsKt.getCancelCallbackSerializer(), null, 8, null);
            } else if (iDecodeElementIndex == 1) {
                Result.Companion companion = Result.INSTANCE;
                resultM8935boximpl = Result.m8935boximpl(Result.m8936constructorimpl(ResultKt.createFailure((Throwable) CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), 1, ThrowableSerializer.INSTANCE, null, 8, null))));
            } else if (iDecodeElementIndex == 2) {
                Result.Companion companion2 = Result.INSTANCE;
                resultM8935boximpl = Result.m8935boximpl(Result.m8936constructorimpl(CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), 2, getSuccessSerializer$zipline_release(), null, 8, null)));
            } else {
                throw new IllegalStateException(("Unexpected index: " + iDecodeElementIndex).toString());
            }
        }
    }
}
