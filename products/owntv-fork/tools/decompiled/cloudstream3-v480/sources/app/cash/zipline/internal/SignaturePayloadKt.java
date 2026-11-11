package app.cash.zipline.internal;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;

/* JADX INFO: compiled from: signaturePayload.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¨\u0006\u0005"}, d2 = {"signaturePayload", "Lkotlinx/serialization/json/JsonElement;", "manifest", "", "manifestJson", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SignaturePayloadKt {
    public static final JsonElement signaturePayload(JsonElement manifest) {
        Intrinsics.checkNotNullParameter(manifest, "manifest");
        Map mutableMap = MapsKt.toMutableMap(JsonElementKt.getJsonObject(manifest));
        mutableMap.remove("unsigned");
        return new JsonObject(mutableMap);
    }

    public static final String signaturePayload(String manifestJson) {
        Intrinsics.checkNotNullParameter(manifestJson, "manifestJson");
        return Json.INSTANCE.encodeToString(JsonElement.INSTANCE.serializer(), signaturePayload(Json.INSTANCE.parseToJsonElement(manifestJson)));
    }
}
