package com.lagradost.cloudstream3.utils.serializers;

import androidx.exifinterface.media.ExifInterface;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.json.JsonTransformingSerializer;

/* JADX INFO: compiled from: NonEmptySerializer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0094\u0080\u0004¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/utils/serializers/NonEmptySerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/json/JsonTransformingSerializer;", "tSerializer", "Lkotlinx/serialization/KSerializer;", "<init>", "(Lkotlinx/serialization/KSerializer;)V", "transformSerialize", "Lkotlinx/serialization/json/JsonElement;", "element", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class NonEmptySerializer<T> extends JsonTransformingSerializer<T> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NonEmptySerializer(KSerializer<T> tSerializer) {
        super(tSerializer);
        Intrinsics.checkNotNullParameter(tSerializer, "tSerializer");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.json.JsonTransformingSerializer
    protected JsonElement transformSerialize(JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        if (!(element instanceof JsonObject)) {
            return element;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : ((Map) element).entrySet()) {
            Object obj = (JsonElement) entry.getValue();
            if (obj instanceof JsonPrimitive) {
                if (((JsonPrimitive) obj).getContent().length() > 0) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            } else if (obj instanceof JsonArray) {
                if (!((Collection) obj).isEmpty()) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            } else if (!(obj instanceof JsonObject)) {
                if (!Intrinsics.areEqual(obj, JsonNull.INSTANCE)) {
                    throw new NoWhenBranchMatchedException();
                }
            } else if (!((Map) obj).isEmpty()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return new JsonObject(linkedHashMap);
    }
}
