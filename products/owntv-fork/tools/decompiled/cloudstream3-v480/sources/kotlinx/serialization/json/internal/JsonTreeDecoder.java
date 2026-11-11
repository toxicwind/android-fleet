package kotlinx.serialization.json.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.internal.JsonInternalDependenciesKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonDecodingException;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonNamingStrategy;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.json.JsonSchemaCacheKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: TreeJsonDecoder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0012\u0018\u00002\u00020\u0001B1\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\tH\u0096\u0080\u0004J\u001a\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u000fH\u0082\u0080\u0004J\n\u0010\u0016\u001a\u00020\u0011H\u0096\u0080\u0004J\u001a\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u000fH\u0094\u0080\u0004J\u0012\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0094\u0080\u0004J\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0086\u0080\u0004J\u0012\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020\tH\u0096\u0080\u0004J\u0012\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0013\u001a\u00020\tH\u0096\u0080\u0004R\u0015\u0010\u0004\u001a\u00020\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000e\u001a\u00020\u000fX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0010\u001a\u00020\u0011X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lkotlinx/serialization/json/internal/JsonTreeDecoder;", "Lkotlinx/serialization/json/internal/AbstractJsonTreeDecoder;", "json", "Lkotlinx/serialization/json/Json;", ES6Iterator.VALUE_PROPERTY, "Lkotlinx/serialization/json/JsonObject;", "polymorphicDiscriminator", "", "polyDescriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "<init>", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonObject;Ljava/lang/String;Lkotlinx/serialization/descriptors/SerialDescriptor;)V", "getValue", "()Lkotlinx/serialization/json/JsonObject;", "position", "", "forceNull", "", "decodeElementIndex", "descriptor", "setForceNull", "index", "decodeNotNullMark", "elementName", "currentElement", "Lkotlinx/serialization/json/JsonElement;", "tag", "currentElementOrNull", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "endStructure", "", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
class JsonTreeDecoder extends AbstractJsonTreeDecoder {
    private boolean forceNull;
    private final SerialDescriptor polyDescriptor;
    private int position;
    private final JsonObject value;

    public /* synthetic */ JsonTreeDecoder(Json json, JsonObject jsonObject, String str, SerialDescriptor serialDescriptor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, jsonObject, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : serialDescriptor);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    public JsonObject getValue() {
        return this.value;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonTreeDecoder(Json json, JsonObject value, String str, SerialDescriptor serialDescriptor) {
        super(json, value, str, null);
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
        this.polyDescriptor = serialDescriptor;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        while (this.position < descriptor.getElementsCount()) {
            int i = this.position;
            this.position = i + 1;
            String tag = getTag(descriptor, i);
            int i2 = this.position - 1;
            this.forceNull = false;
            if (getValue().containsKey((Object) tag) || setForceNull(descriptor, i2)) {
                if (this.configuration.getCoerceInputValues()) {
                    Json json = getJson();
                    boolean zIsElementOptional = descriptor.isElementOptional(i2);
                    SerialDescriptor elementDescriptor = descriptor.getElementDescriptor(i2);
                    if (!zIsElementOptional || elementDescriptor.isNullable() || !(currentElementOrNull(tag) instanceof JsonNull)) {
                        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || (elementDescriptor.isNullable() && (currentElementOrNull(tag) instanceof JsonNull))) {
                            return i2;
                        }
                        JsonElement jsonElementCurrentElementOrNull = currentElementOrNull(tag);
                        JsonPrimitive jsonPrimitive = jsonElementCurrentElementOrNull instanceof JsonPrimitive ? (JsonPrimitive) jsonElementCurrentElementOrNull : null;
                        String contentOrNull = jsonPrimitive != null ? JsonElementKt.getContentOrNull(jsonPrimitive) : null;
                        if (contentOrNull == null) {
                            return i2;
                        }
                        int jsonNameIndex = JsonNamesMapKt.getJsonNameIndex(elementDescriptor, json, contentOrNull);
                        boolean z = !json.getConfiguration().getExplicitNulls() && elementDescriptor.isNullable();
                        if (jsonNameIndex != -3 || ((!zIsElementOptional && !z) || setForceNull(descriptor, i2))) {
                        }
                    }
                }
                return i2;
            }
        }
        return -1;
    }

    private final boolean setForceNull(SerialDescriptor descriptor, int index) {
        boolean z = (getJson().getConfiguration().getExplicitNulls() || descriptor.isElementOptional(index) || !descriptor.getElementDescriptor(index).isNullable()) ? false : true;
        this.forceNull = z;
        return z;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder, kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return !this.forceNull && super.decodeNotNullMark();
    }

    @Override // kotlinx.serialization.internal.NamedValueDecoder
    protected String elementName(SerialDescriptor descriptor, int index) {
        Object next;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        JsonNamingStrategy jsonNamingStrategyNamingStrategy = JsonNamesMapKt.namingStrategy(descriptor, getJson());
        String elementName = descriptor.getElementName(index);
        if (jsonNamingStrategyNamingStrategy != null || (this.configuration.getUseAlternativeNames() && !getValue().keySet().contains(elementName))) {
            Map<String, Integer> mapDeserializationNamesMap = JsonNamesMapKt.deserializationNamesMap(getJson(), descriptor);
            Iterator<T> it = getValue().keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                Integer num = mapDeserializationNamesMap.get((String) next);
                if (num != null && num.intValue() == index) {
                    break;
                }
            }
            String str = (String) next;
            if (str != null) {
                return str;
            }
            String strSerialNameForJson = jsonNamingStrategyNamingStrategy != null ? jsonNamingStrategyNamingStrategy.serialNameForJson(descriptor, index, elementName) : null;
            if (strSerialNameForJson != null) {
                return strSerialNameForJson;
            }
        }
        return elementName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    public JsonElement currentElement(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return (JsonElement) MapsKt.getValue(getValue(), tag);
    }

    public final JsonElement currentElementOrNull(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return (JsonElement) getValue().get((Object) tag);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder, kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (descriptor == this.polyDescriptor) {
            Json json = getJson();
            JsonTreeDecoder jsonTreeDecoder = this;
            JsonElement jsonElementCurrentObject = currentObject();
            JsonTreeDecoder jsonTreeDecoder2 = jsonTreeDecoder;
            String serialName = this.polyDescriptor.getSerialName();
            if (jsonElementCurrentObject instanceof JsonObject) {
                return new JsonTreeDecoder(json, (JsonObject) jsonElementCurrentObject, getPolymorphicDiscriminator(), this.polyDescriptor);
            }
            String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentObject.getClass()).getSimpleName() + " as the serialized body of " + serialName;
            String strRenderTagStack = jsonTreeDecoder.renderTagStack();
            String string = jsonTreeDecoder2.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentObject.toString(), 0, 1, null).toString() : null;
            throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string), str, -1, strRenderTagStack, string, null);
        }
        return super.beginStructure(descriptor);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder, kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor descriptor) {
        Set<String> setPlus;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (JsonNamesMapKt.ignoreUnknownKeys(descriptor, getJson()) || (descriptor.getKind() instanceof PolymorphicKind)) {
            return;
        }
        JsonNamingStrategy jsonNamingStrategyNamingStrategy = JsonNamesMapKt.namingStrategy(descriptor, getJson());
        if (jsonNamingStrategyNamingStrategy == null && !this.configuration.getUseAlternativeNames()) {
            setPlus = JsonInternalDependenciesKt.jsonCachedSerialNames(descriptor);
        } else if (jsonNamingStrategyNamingStrategy != null) {
            setPlus = JsonNamesMapKt.deserializationNamesMap(getJson(), descriptor).keySet();
        } else {
            Set<String> setJsonCachedSerialNames = JsonInternalDependenciesKt.jsonCachedSerialNames(descriptor);
            Map map = (Map) JsonSchemaCacheKt.getSchemaCache(getJson()).get(descriptor, JsonNamesMapKt.getJsonDeserializationNamesKey());
            Set setKeySet = map != null ? map.keySet() : null;
            if (setKeySet == null) {
                setKeySet = SetsKt.emptySet();
            }
            setPlus = SetsKt.plus((Set) setJsonCachedSerialNames, (Iterable) setKeySet);
        }
        for (String str : getValue().keySet()) {
            if (!setPlus.contains(str) && !Intrinsics.areEqual(str, getPolymorphicDiscriminator())) {
                String str2 = "Encountered an unknown key '" + str + '\'';
                String strRenderTagStack = renderTagStack();
                String string = getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(getValue().toString(), 0, 1, null).toString() : null;
                throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str2, strRenderTagStack, AbstractJsonLexerKt.ignoreUnknownKeysHint, string), str2, -1, strRenderTagStack, string, AbstractJsonLexerKt.ignoreUnknownKeysHint);
            }
        }
    }
}
