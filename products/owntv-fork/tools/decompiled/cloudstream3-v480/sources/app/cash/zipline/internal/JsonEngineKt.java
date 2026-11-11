package app.cash.zipline.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.json.Json;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: jsonEngine.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u001a-\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\u0010\u0007\u001a-\u0010\b\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0006\u0010\n\u001a\u0002H\u0001H\u0000¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"decodeFromStringFast", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/serialization/json/Json;", "deserializer", "Lkotlinx/serialization/KSerializer;", TypedValues.Custom.S_STRING, "", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/KSerializer;Ljava/lang/String;)Ljava/lang/Object;", "encodeToStringFast", "serializer", ES6Iterator.VALUE_PROPERTY, "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/KSerializer;Ljava/lang/Object;)Ljava/lang/String;", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class JsonEngineKt {
    public static final <T> T decodeFromStringFast(Json json, KSerializer<T> deserializer, String string) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(string, "string");
        return (T) json.decodeFromString(deserializer, string);
    }

    public static final <T> String encodeToStringFast(Json json, KSerializer<T> serializer, T t) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        return json.encodeToString(serializer, t);
    }
}
