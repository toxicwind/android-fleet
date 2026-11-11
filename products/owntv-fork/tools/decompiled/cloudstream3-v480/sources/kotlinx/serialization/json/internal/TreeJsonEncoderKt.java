package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonDecodingException;
import kotlinx.serialization.json.JsonElement;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: TreeJsonEncoder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087\u0080\u0004¢\u0006\u0002\u0010\b\u001a@\u0010\u0010\u001a\u0002H\u0002\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0001*\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0014H\u0080\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0015\"\u0019\u0010\t\u001a\u00020\n*\u00020\u000b8BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u000f\u0010\u000e\u001a\u00020\u000fX\u0080Ô\b¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0016"}, d2 = {"writeJson", "Lkotlinx/serialization/json/JsonElement;", ExifInterface.GPS_DIRECTION_TRUE, "json", "Lkotlinx/serialization/json/Json;", ES6Iterator.VALUE_PROPERTY, "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/json/Json;Ljava/lang/Object;Lkotlinx/serialization/SerializationStrategy;)Lkotlinx/serialization/json/JsonElement;", "requiresTopLevelTag", "", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getRequiresTopLevelTag", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)Z", "PRIMITIVE_TAG", "", "cast", "Lkotlinx/serialization/json/JsonDecoder;", "serialName", "path", "Lkotlin/Function0;", "(Lkotlinx/serialization/json/JsonDecoder;Lkotlinx/serialization/json/JsonElement;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Lkotlinx/serialization/json/JsonElement;", "kotlinx-serialization-json"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TreeJsonEncoderKt {
    public static final String PRIMITIVE_TAG = "primitive";

    @JsonFriendModuleApi
    public static final <T> JsonElement writeJson(Json json, T t, SerializationStrategy<? super T> serializer) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        new JsonTreeEncoder(json, new Function1() { // from class: kotlinx.serialization.json.internal.TreeJsonEncoderKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TreeJsonEncoderKt.writeJson$lambda$0(objectRef, (JsonElement) obj);
            }
        }).encodeSerializableValue(serializer, t);
        if (objectRef.element != null) {
            return (JsonElement) objectRef.element;
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final Unit writeJson$lambda$0(Ref.ObjectRef objectRef, JsonElement it) {
        Intrinsics.checkNotNullParameter(it, "it");
        objectRef.element = it;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getRequiresTopLevelTag(SerialDescriptor serialDescriptor) {
        return (serialDescriptor.getKind() instanceof PrimitiveKind) || serialDescriptor.getKind() == SerialKind.ENUM.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final /* synthetic */ <T extends JsonElement> T cast(JsonDecoder jsonDecoder, JsonElement value, String serialName, Function0<String> path) {
        Intrinsics.checkNotNullParameter(jsonDecoder, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
        if (value instanceof JsonElement) {
            return value;
        }
        StringBuilder sb = new StringBuilder("Expected ");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        sb.append(Reflection.getOrCreateKotlinClass(JsonElement.class).getSimpleName());
        sb.append(", but had ");
        sb.append(Reflection.getOrCreateKotlinClass(value.getClass()).getSimpleName());
        sb.append(" as the serialized body of ");
        sb.append(serialName);
        String string = sb.toString();
        String strInvoke = path.invoke();
        String string2 = jsonDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(value.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, string, strInvoke, null, string2), string, -1, strInvoke, string2, null);
    }
}
