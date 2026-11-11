package com.lagradost.cloudstream3.utils.serializers;

import androidx.exifinterface.media.ExifInterface;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonTransformingSerializer;

/* JADX INFO: compiled from: WriteOnlySerializer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0094\u0080\u0004R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/lagradost/cloudstream3/utils/serializers/WriteOnlySerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/json/JsonTransformingSerializer;", "tSerializer", "Lkotlinx/serialization/KSerializer;", "keysToIgnore", "", "", "<init>", "(Lkotlinx/serialization/KSerializer;Ljava/util/Set;)V", "transformSerialize", "Lkotlinx/serialization/json/JsonElement;", "element", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class WriteOnlySerializer<T> extends JsonTransformingSerializer<T> {
    private final Set<String> keysToIgnore;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WriteOnlySerializer(KSerializer<T> tSerializer, Set<String> keysToIgnore) {
        super(tSerializer);
        Intrinsics.checkNotNullParameter(tSerializer, "tSerializer");
        Intrinsics.checkNotNullParameter(keysToIgnore, "keysToIgnore");
        this.keysToIgnore = keysToIgnore;
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
            if (!this.keysToIgnore.contains((String) entry.getKey())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return new JsonObject(linkedHashMap);
    }
}
