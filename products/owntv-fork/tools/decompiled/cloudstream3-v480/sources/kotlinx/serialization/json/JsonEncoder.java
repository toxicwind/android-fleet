package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: JsonEncoder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u00012\u00020\u0002J\u0012\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH¦\u0080\u0004R\u0013\u0010\u0003\u001a\u00020\u0004X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lkotlinx/serialization/json/JsonEncoder;", "Lkotlinx/serialization/encoding/Encoder;", "Lkotlinx/serialization/encoding/CompositeEncoder;", "json", "Lkotlinx/serialization/json/Json;", "getJson", "()Lkotlinx/serialization/json/Json;", "encodeJsonElement", "", "element", "Lkotlinx/serialization/json/JsonElement;", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface JsonEncoder extends Encoder, CompositeEncoder {
    void encodeJsonElement(JsonElement element);

    Json getJson();

    /* JADX INFO: renamed from: kotlinx.serialization.json.JsonEncoder$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: JsonEncoder.kt */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: JsonEncoder.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static CompositeEncoder beginCollection(JsonEncoder jsonEncoder, SerialDescriptor descriptor, int i) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return Encoder.CC.$default$beginCollection(jsonEncoder, descriptor, i);
        }

        @ExperimentalSerializationApi
        @Deprecated
        public static void encodeNotNullMark(JsonEncoder jsonEncoder) {
            Encoder.CC.$default$encodeNotNullMark(jsonEncoder);
        }

        @ExperimentalSerializationApi
        @Deprecated
        public static <T> void encodeNullableSerializableValue(JsonEncoder jsonEncoder, SerializationStrategy<? super T> serializer, T t) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            Encoder.CC.$default$encodeNullableSerializableValue(jsonEncoder, serializer, t);
        }

        @Deprecated
        public static <T> void encodeSerializableValue(JsonEncoder jsonEncoder, SerializationStrategy<? super T> serializer, T t) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            Encoder.CC.$default$encodeSerializableValue(jsonEncoder, serializer, t);
        }

        @ExperimentalSerializationApi
        @Deprecated
        public static boolean shouldEncodeElementDefault(JsonEncoder jsonEncoder, SerialDescriptor descriptor, int i) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return CompositeEncoder.CC.$default$shouldEncodeElementDefault(jsonEncoder, descriptor, i);
        }
    }
}
