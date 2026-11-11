package app.cash.zipline.internal;

import app.cash.zipline.ZiplineManifest;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonKt;

/* JADX INFO: compiled from: serializers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"jsonForManifest", "Lkotlinx/serialization/json/Json;", "getJsonForManifest", "()Lkotlinx/serialization/json/Json;", "MANIFEST_MAX_SIZE", "", "decodeToManifest", "Lapp/cash/zipline/ZiplineManifest;", "Lkotlinx/serialization/json/JsonElement;", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SerializersKt {
    public static final int MANIFEST_MAX_SIZE = 655360;
    private static final Json jsonForManifest = JsonKt.Json$default(null, new Function1() { // from class: app.cash.zipline.internal.SerializersKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SerializersKt.jsonForManifest$lambda$0((JsonBuilder) obj);
        }
    }, 1, null);

    public static final Json getJsonForManifest() {
        return jsonForManifest;
    }

    static final Unit jsonForManifest$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setIgnoreUnknownKeys(true);
        Json.setEncodeDefaults(true);
        return Unit.INSTANCE;
    }

    public static final ZiplineManifest decodeToManifest(JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(jsonElement, "<this>");
        Json json = jsonForManifest;
        json.getSerializersModule();
        return (ZiplineManifest) json.decodeFromJsonElement(ZiplineManifest.INSTANCE.serializer(), jsonElement);
    }
}
