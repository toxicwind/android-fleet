package kotlinx.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;

/* JADX INFO: compiled from: JsonElementBuilders.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\t\bA¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0087\u0080\bJ\u0018\u0010\n\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fH\u0087\u0080\bJ\n\u0010\r\u001a\u00020\u000eH\u0081\u0080\u0004R\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/serialization/json/JsonArrayBuilder;", "", "<init>", "()V", "content", "", "Lkotlinx/serialization/json/JsonElement;", "add", "", "element", "addAll", "elements", "", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lkotlinx/serialization/json/JsonArray;", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JsonDslMarker
public final class JsonArrayBuilder {
    private final List<JsonElement> content = new ArrayList();

    @IgnorableReturnValue
    public final boolean add(JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        this.content.add(element);
        return true;
    }

    @ExperimentalSerializationApi
    @IgnorableReturnValue
    public final boolean addAll(Collection<? extends JsonElement> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.content.addAll(elements);
    }

    public final JsonArray build() {
        return new JsonArray(this.content);
    }
}
