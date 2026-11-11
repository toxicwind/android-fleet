package kotlinx.serialization.json.internal;

import androidx.collection.SieveCacheKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.PolymorphicSerializerKt;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.NamedValueDecoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonDecodingException;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: TreeJsonDecoder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u00012\u00020\u0002B%\bD\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\n\u0010\u0017\u001a\u00020\u0006H\u0084\u0080\u0004J\u0012\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0086\u0080\u0004J\n\u0010\u001a\u001a\u00020\u0006H\u0096\u0080\u0004J#\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001c0\u001eH\u0096\u0080\u0004¢\u0006\u0002\u0010\u001fJ\u001a\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\bH\u0094\u0080\u0004J\u0012\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0096\u0080\u0004J+\u0010'\u001a\u0002H\u001c\"\n\b\u0000\u0010\u001c\u0018\u0001*\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&H\u0086\u0088\u0004¢\u0006\u0002\u0010(J3\u0010'\u001a\u0002H\u001c\"\n\b\u0000\u0010\u001c\u0018\u0001*\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\bH\u0086\u0088\u0004¢\u0006\u0002\u0010+J\u0012\u0010,\u001a\u00020-2\u0006\u0010%\u001a\u00020&H\u0096\u0080\u0004J\n\u0010.\u001a\u00020/H\u0096\u0080\u0004J\u001a\u00100\u001a\u0002012\u0006\u0010*\u001a\u00020\b2\u0006\u0010%\u001a\u00020&H\u0084\u0088\u0004JD\u00100\u001a\u0002H\u001c\"\b\b\u0000\u0010\u001c*\u0002022\u0006\u0010*\u001a\u00020\b2\u0006\u00103\u001a\u00020\b2\u0019\u00104\u001a\u0015\u0012\u0004\u0012\u000201\u0012\u0006\u0012\u0004\u0018\u0001H\u001c05¢\u0006\u0002\b6H\u0082\u0088\u0004¢\u0006\u0002\u00107J\"\u00108\u001a\u0002092\u0006\u0010:\u001a\u0002012\u0006\u0010;\u001a\u00020\b2\u0006\u0010*\u001a\u00020\bH\u0082\u0080\u0004J\u0012\u0010<\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\bH¤\u0080\u0004J\u001a\u0010=\u001a\u00020>2\u0006\u0010*\u001a\u00020\b2\u0006\u0010?\u001a\u00020&H\u0094\u0080\u0004J\u0014\u0010@\u001a\u0004\u0018\u0001092\u0006\u0010*\u001a\u00020\bH\u0094\u0080\u0004J\u0012\u0010A\u001a\u00020/2\u0006\u0010*\u001a\u00020\bH\u0094\u0080\u0004J\u0012\u0010B\u001a\u00020/2\u0006\u0010*\u001a\u00020\bH\u0094\u0080\u0004J\u0012\u0010C\u001a\u00020D2\u0006\u0010*\u001a\u00020\bH\u0094\u0080\u0004J\u0012\u0010E\u001a\u00020F2\u0006\u0010*\u001a\u00020\bH\u0094\u0080\u0004J\u0012\u0010G\u001a\u00020>2\u0006\u0010*\u001a\u00020\bH\u0094\u0080\u0004J\u0012\u0010H\u001a\u00020I2\u0006\u0010*\u001a\u00020\bH\u0094\u0080\u0004J\u0012\u0010J\u001a\u00020K2\u0006\u0010*\u001a\u00020\bH\u0094\u0080\u0004J\u0012\u0010L\u001a\u00020M2\u0006\u0010*\u001a\u00020\bH\u0094\u0080\u0004J\u0012\u0010N\u001a\u00020O2\u0006\u0010*\u001a\u00020\bH\u0094\u0080\u0004J\u0012\u0010P\u001a\u00020\b2\u0006\u0010*\u001a\u00020\bH\u0094\u0080\u0004J\u001a\u0010Q\u001a\u00020R2\u0006\u0010*\u001a\u00020\b2\u0006\u0010S\u001a\u00020&H\u0094\u0080\u0004J\u0012\u0010T\u001a\u00020R2\u0006\u0010%\u001a\u00020&H\u0096\u0080\u0004R\u0015\u0010\u0003\u001a\u00020\u0004X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0005\u001a\u00020\u0006X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0011\u001a\u00020\u00128VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00168\u0004X\u0085\u0084\b¢\u0006\u0002\n\u0000\u0082\u0001\u0003UVW¨\u0006X"}, d2 = {"Lkotlinx/serialization/json/internal/AbstractJsonTreeDecoder;", "Lkotlinx/serialization/internal/NamedValueDecoder;", "Lkotlinx/serialization/json/JsonDecoder;", "json", "Lkotlinx/serialization/json/Json;", ES6Iterator.VALUE_PROPERTY, "Lkotlinx/serialization/json/JsonElement;", "polymorphicDiscriminator", "", "<init>", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonElement;Ljava/lang/String;)V", "getJson", "()Lkotlinx/serialization/json/Json;", "getValue", "()Lkotlinx/serialization/json/JsonElement;", "getPolymorphicDiscriminator", "()Ljava/lang/String;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "currentObject", "renderTagStack", "currentTag", "decodeJsonElement", "decodeSerializableValue", ExifInterface.GPS_DIRECTION_TRUE, "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "composeName", "parentName", "childName", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "cast", "(Lkotlinx/serialization/json/JsonElement;Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/json/JsonElement;", "serialName", "tag", "(Lkotlinx/serialization/json/JsonElement;Ljava/lang/String;Ljava/lang/String;)Lkotlinx/serialization/json/JsonElement;", "endStructure", "", "decodeNotNullMark", "", "getPrimitiveValue", "Lkotlinx/serialization/json/JsonPrimitive;", "", "primitiveName", "convert", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "unparsedPrimitive", "", "literal", TreeJsonEncoderKt.PRIMITIVE_TAG, "currentElement", "decodeTaggedEnum", "", "enumDescriptor", "decodeTaggedNull", "decodeTaggedNotNullMark", "decodeTaggedBoolean", "decodeTaggedByte", "", "decodeTaggedShort", "", "decodeTaggedInt", "decodeTaggedLong", "", "decodeTaggedFloat", "", "decodeTaggedDouble", "", "decodeTaggedChar", "", "decodeTaggedString", "decodeTaggedInline", "Lkotlinx/serialization/encoding/Decoder;", "inlineDescriptor", "decodeInline", "Lkotlinx/serialization/json/internal/JsonPrimitiveDecoder;", "Lkotlinx/serialization/json/internal/JsonTreeDecoder;", "Lkotlinx/serialization/json/internal/JsonTreeListDecoder;", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
abstract class AbstractJsonTreeDecoder extends NamedValueDecoder implements JsonDecoder {
    protected final JsonConfiguration configuration;
    private final Json json;
    private final String polymorphicDiscriminator;
    private final JsonElement value;

    public /* synthetic */ AbstractJsonTreeDecoder(Json json, JsonElement jsonElement, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, jsonElement, str);
    }

    @Override // kotlinx.serialization.internal.NamedValueDecoder
    protected String composeName(String parentName, String childName) {
        Intrinsics.checkNotNullParameter(parentName, "parentName");
        Intrinsics.checkNotNullParameter(childName, "childName");
        return childName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract JsonElement currentElement(String tag);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public Void decodeTaggedNull(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return null;
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
    }

    private AbstractJsonTreeDecoder(Json json, JsonElement jsonElement, String str) {
        this.json = json;
        this.value = jsonElement;
        this.polymorphicDiscriminator = str;
        this.configuration = getJson().getConfiguration();
    }

    public /* synthetic */ AbstractJsonTreeDecoder(Json json, JsonElement jsonElement, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, jsonElement, (i & 4) != 0 ? null : str, null);
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public Json getJson() {
        return this.json;
    }

    public JsonElement getValue() {
        return this.value;
    }

    protected final String getPolymorphicDiscriminator() {
        return this.polymorphicDiscriminator;
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder, kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return getJson().getSerializersModule();
    }

    protected final JsonElement currentObject() {
        JsonElement jsonElementCurrentElement;
        String currentTagOrNull = getCurrentTagOrNull();
        return (currentTagOrNull == null || (jsonElementCurrentElement = currentElement(currentTagOrNull)) == null) ? getValue() : jsonElementCurrentElement;
    }

    public final String renderTagStack(String currentTag) {
        Intrinsics.checkNotNullParameter(currentTag, "currentTag");
        return renderTagStack() + '.' + currentTag;
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public JsonElement decodeJsonElement() {
        return currentObject();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<? extends T> deserializer) {
        JsonPrimitive jsonPrimitive;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        if (!(deserializer instanceof AbstractPolymorphicSerializer) || abstractJsonTreeDecoder.getJson().getConfiguration().getUseArrayPolymorphism()) {
            return deserializer.deserialize(abstractJsonTreeDecoder);
        }
        AbstractPolymorphicSerializer abstractPolymorphicSerializer = (AbstractPolymorphicSerializer) deserializer;
        String strClassDiscriminator = PolymorphicKt.classDiscriminator(abstractPolymorphicSerializer.getDescriptor(), abstractJsonTreeDecoder.getJson());
        JsonElement jsonElementDecodeJsonElement = abstractJsonTreeDecoder.decodeJsonElement();
        String serialName = abstractPolymorphicSerializer.getDescriptor().getSerialName();
        if (jsonElementDecodeJsonElement instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) jsonElementDecodeJsonElement;
            JsonElement jsonElement = (JsonElement) jsonObject.get((Object) strClassDiscriminator);
            try {
                DeserializationStrategy deserializationStrategyFindPolymorphicSerializer = PolymorphicSerializerKt.findPolymorphicSerializer((AbstractPolymorphicSerializer) deserializer, abstractJsonTreeDecoder, (jsonElement == null || (jsonPrimitive = JsonElementKt.getJsonPrimitive(jsonElement)) == null) ? null : JsonElementKt.getContentOrNull(jsonPrimitive));
                Intrinsics.checkNotNull(deserializationStrategyFindPolymorphicSerializer, "null cannot be cast to non-null type kotlinx.serialization.DeserializationStrategy<T of kotlinx.serialization.json.internal.PolymorphicKt.decodeSerializableValuePolymorphic>");
                return (T) TreeJsonDecoderKt.readPolymorphicJson(abstractJsonTreeDecoder.getJson(), strClassDiscriminator, jsonObject, deserializationStrategyFindPolymorphicSerializer);
            } catch (SerializationException e) {
                String message = e.getMessage();
                Intrinsics.checkNotNull(message);
                String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonObject.toString(), 0, 1, null).toString() : null;
                throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, message, null, null, string), message, -1, null, string, null);
            }
        }
        String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementDecodeJsonElement.getClass()).getSimpleName() + " as the serialized body of " + serialName;
        String strRenderTagStack = renderTagStack();
        String string2 = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementDecodeJsonElement.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string2), str, -1, strRenderTagStack, string2, null);
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        String string;
        CompositeDecoder jsonTreeMapDecoder;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        JsonElement jsonElementCurrentObject = currentObject();
        SerialKind kind = descriptor.getKind();
        if (!Intrinsics.areEqual(kind, StructureKind.LIST.INSTANCE) && !(kind instanceof PolymorphicKind)) {
            if (Intrinsics.areEqual(kind, StructureKind.MAP.INSTANCE)) {
                Json json = getJson();
                SerialDescriptor serialDescriptorCarrierDescriptor = WriteModeKt.carrierDescriptor(descriptor.getElementDescriptor(0), json.getSerializersModule());
                SerialKind kind2 = serialDescriptorCarrierDescriptor.getKind();
                if ((kind2 instanceof PrimitiveKind) || Intrinsics.areEqual(kind2, SerialKind.ENUM.INSTANCE)) {
                    Json json2 = getJson();
                    AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
                    String serialName = descriptor.getSerialName();
                    if (jsonElementCurrentObject instanceof JsonObject) {
                        jsonTreeMapDecoder = new JsonTreeMapDecoder(json2, (JsonObject) jsonElementCurrentObject);
                    } else {
                        String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentObject.getClass()).getSimpleName() + " as the serialized body of " + serialName;
                        String strRenderTagStack = renderTagStack();
                        string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentObject.toString(), 0, 1, null).toString() : null;
                        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string), str, -1, strRenderTagStack, string, null);
                    }
                } else if (json.getConfiguration().getAllowStructuredMapKeys()) {
                    Json json3 = getJson();
                    AbstractJsonTreeDecoder abstractJsonTreeDecoder2 = this;
                    String serialName2 = descriptor.getSerialName();
                    if (jsonElementCurrentObject instanceof JsonArray) {
                        jsonTreeMapDecoder = new JsonTreeListDecoder(json3, (JsonArray) jsonElementCurrentObject);
                    } else {
                        String str2 = "Expected " + Reflection.getOrCreateKotlinClass(JsonArray.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentObject.getClass()).getSimpleName() + " as the serialized body of " + serialName2;
                        String strRenderTagStack2 = renderTagStack();
                        string = abstractJsonTreeDecoder2.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentObject.toString(), 0, 1, null).toString() : null;
                        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str2, strRenderTagStack2, null, string), str2, -1, strRenderTagStack2, string, null);
                    }
                } else {
                    throw JsonExceptionsKt.InvalidKeyKindException(serialDescriptorCarrierDescriptor);
                }
                return jsonTreeMapDecoder;
            }
            Json json4 = getJson();
            AbstractJsonTreeDecoder abstractJsonTreeDecoder3 = this;
            String serialName3 = descriptor.getSerialName();
            if (jsonElementCurrentObject instanceof JsonObject) {
                return new JsonTreeDecoder(json4, (JsonObject) jsonElementCurrentObject, this.polymorphicDiscriminator, null, 8, null);
            }
            String str3 = "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentObject.getClass()).getSimpleName() + " as the serialized body of " + serialName3;
            String strRenderTagStack3 = renderTagStack();
            string = abstractJsonTreeDecoder3.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentObject.toString(), 0, 1, null).toString() : null;
            throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str3, strRenderTagStack3, null, string), str3, -1, strRenderTagStack3, string, null);
        }
        Json json5 = getJson();
        AbstractJsonTreeDecoder abstractJsonTreeDecoder4 = this;
        String serialName4 = descriptor.getSerialName();
        if (jsonElementCurrentObject instanceof JsonArray) {
            return new JsonTreeListDecoder(json5, (JsonArray) jsonElementCurrentObject);
        }
        String str4 = "Expected " + Reflection.getOrCreateKotlinClass(JsonArray.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentObject.getClass()).getSimpleName() + " as the serialized body of " + serialName4;
        String strRenderTagStack4 = renderTagStack();
        string = abstractJsonTreeDecoder4.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentObject.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str4, strRenderTagStack4, null, string), str4, -1, strRenderTagStack4, string, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ <T extends JsonElement> T cast(JsonElement value, SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        String serialName = descriptor.getSerialName();
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
        String strRenderTagStack = renderTagStack();
        String string2 = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(value.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, string, strRenderTagStack, null, string2), string, -1, strRenderTagStack, string2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ <T extends JsonElement> T cast(JsonElement value, String serialName, String tag) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(tag, "tag");
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
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
        String strRenderTagStack = renderTagStack(tag);
        String string2 = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(value.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, string, strRenderTagStack, null, string2), string, -1, strRenderTagStack, string2, null);
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return !(currentObject() instanceof JsonNull);
    }

    protected final JsonPrimitive getPrimitiveValue(String tag, SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        String serialName = descriptor.getSerialName();
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            return (JsonPrimitive) jsonElementCurrentElement;
        }
        String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentElement.getClass()).getSimpleName() + " as the serialized body of " + serialName;
        String strRenderTagStack = renderTagStack(tag);
        String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentElement.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string), str, -1, strRenderTagStack, string, null);
    }

    private final <T> T getPrimitiveValue(String tag, String primitiveName, Function1<? super JsonPrimitive, ? extends T> convert) {
        JsonElement jsonElementCurrentElement = currentElement(tag);
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
            try {
                T tInvoke = convert.invoke(jsonPrimitive);
                if (tInvoke != null) {
                    return tInvoke;
                }
                unparsedPrimitive(jsonPrimitive, primitiveName, tag);
                throw new KotlinNothingValueException();
            } catch (IllegalArgumentException unused) {
                unparsedPrimitive(jsonPrimitive, primitiveName, tag);
                throw new KotlinNothingValueException();
            }
        }
        String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentElement.getClass()).getSimpleName() + " as the serialized body of " + primitiveName;
        String strRenderTagStack = renderTagStack(tag);
        String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentElement.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string), str, -1, strRenderTagStack, string, null);
    }

    private final Void unparsedPrimitive(JsonPrimitive literal, String primitive, String tag) {
        StringBuilder sb = StringsKt.startsWith$default(primitive, CmcdData.OBJECT_TYPE_INIT_SEGMENT, false, 2, (Object) null) ? new StringBuilder("an ") : new StringBuilder("a ");
        sb.append(primitive);
        String str = "Failed to parse literal '" + literal + "' as " + sb.toString() + " value";
        String strRenderTagStack = renderTagStack(tag);
        String string = getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(currentObject().toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string), str, -1, strRenderTagStack, string, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public int decodeTaggedEnum(String tag, SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        Json json = getJson();
        JsonElement jsonElementCurrentElement = currentElement(tag);
        String serialName = enumDescriptor.getSerialName();
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            return JsonNamesMapKt.getJsonNameIndexOrThrow$default(enumDescriptor, json, ((JsonPrimitive) jsonElementCurrentElement).getContent(), null, 4, null);
        }
        String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentElement.getClass()).getSimpleName() + " as the serialized body of " + serialName;
        String strRenderTagStack = renderTagStack(tag);
        String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentElement.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string), str, -1, strRenderTagStack, string, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public boolean decodeTaggedNotNullMark(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return currentElement(tag) != JsonNull.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public boolean decodeTaggedBoolean(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
            try {
                Boolean booleanOrNull = JsonElementKt.getBooleanOrNull(jsonPrimitive);
                if (booleanOrNull == null) {
                    unparsedPrimitive(jsonPrimitive, TypedValues.Custom.S_BOOLEAN, tag);
                    throw new KotlinNothingValueException();
                }
                return booleanOrNull.booleanValue();
            } catch (IllegalArgumentException unused) {
                unparsedPrimitive(jsonPrimitive, TypedValues.Custom.S_BOOLEAN, tag);
                throw new KotlinNothingValueException();
            }
        }
        String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentElement.getClass()).getSimpleName() + " as the serialized body of boolean";
        String strRenderTagStack = renderTagStack(tag);
        String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentElement.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string), str, -1, strRenderTagStack, string, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public byte decodeTaggedByte(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        Object objValueOf = null;
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
            try {
                long longImpl = JsonElementKt.parseLongImpl(jsonPrimitive);
                if (-128 <= longImpl && longImpl <= 127) {
                    objValueOf = Byte.valueOf((byte) longImpl);
                }
                if (objValueOf == null) {
                    unparsedPrimitive(jsonPrimitive, "byte", tag);
                    throw new KotlinNothingValueException();
                }
                return ((Number) objValueOf).byteValue();
            } catch (IllegalArgumentException unused) {
                unparsedPrimitive(jsonPrimitive, "byte", tag);
                throw new KotlinNothingValueException();
            }
        }
        String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentElement.getClass()).getSimpleName() + " as the serialized body of byte";
        String strRenderTagStack = renderTagStack(tag);
        String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentElement.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string), str, -1, strRenderTagStack, string, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public short decodeTaggedShort(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        Object objValueOf = null;
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
            try {
                long longImpl = JsonElementKt.parseLongImpl(jsonPrimitive);
                if (-32768 <= longImpl && longImpl <= 32767) {
                    objValueOf = Short.valueOf((short) longImpl);
                }
                if (objValueOf == null) {
                    unparsedPrimitive(jsonPrimitive, "short", tag);
                    throw new KotlinNothingValueException();
                }
                return ((Number) objValueOf).shortValue();
            } catch (IllegalArgumentException unused) {
                unparsedPrimitive(jsonPrimitive, "short", tag);
                throw new KotlinNothingValueException();
            }
        }
        String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentElement.getClass()).getSimpleName() + " as the serialized body of short";
        String strRenderTagStack = renderTagStack(tag);
        String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentElement.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string), str, -1, strRenderTagStack, string, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public int decodeTaggedInt(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        Object objValueOf = null;
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
            try {
                long longImpl = JsonElementKt.parseLongImpl(jsonPrimitive);
                if (SieveCacheKt.NodeMetaAndPreviousMask <= longImpl && longImpl <= SieveCacheKt.NodeLinkMask) {
                    objValueOf = Integer.valueOf((int) longImpl);
                }
                if (objValueOf == null) {
                    unparsedPrimitive(jsonPrimitive, "int", tag);
                    throw new KotlinNothingValueException();
                }
                return ((Number) objValueOf).intValue();
            } catch (IllegalArgumentException unused) {
                unparsedPrimitive(jsonPrimitive, "int", tag);
                throw new KotlinNothingValueException();
            }
        }
        String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentElement.getClass()).getSimpleName() + " as the serialized body of int";
        String strRenderTagStack = renderTagStack(tag);
        String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentElement.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string), str, -1, strRenderTagStack, string, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public String decodeTaggedString(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
            if (!(jsonPrimitive instanceof JsonLiteral)) {
                String str = "Expected string value for a non-null key '" + tag + "', got null literal instead";
                String strRenderTagStack = renderTagStack(tag);
                String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(currentObject().toString(), 0, 1, null).toString() : null;
                throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, AbstractJsonLexerKt.coerceInputValuesHint, string), str, -1, strRenderTagStack, string, AbstractJsonLexerKt.coerceInputValuesHint);
            }
            JsonLiteral jsonLiteral = (JsonLiteral) jsonPrimitive;
            if (!jsonLiteral.getIsString() && !getJson().getConfiguration().getIsLenient()) {
                String str2 = "String literal for value of key '" + tag + "' should be quoted";
                String strRenderTagStack2 = renderTagStack(tag);
                String string2 = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(currentObject().toString(), 0, 1, null).toString() : null;
                throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str2, strRenderTagStack2, AbstractJsonLexerKt.lenientHint, string2), str2, -1, strRenderTagStack2, string2, AbstractJsonLexerKt.lenientHint);
            }
            return jsonLiteral.getContent();
        }
        String str3 = "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentElement.getClass()).getSimpleName() + " as the serialized body of string";
        String strRenderTagStack3 = renderTagStack(tag);
        String string3 = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentElement.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str3, strRenderTagStack3, null, string3), str3, -1, strRenderTagStack3, string3, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public Decoder decodeTaggedInline(String tag, SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        if (StreamingJsonEncoderKt.isUnsignedNumber(inlineDescriptor)) {
            Json json = getJson();
            JsonElement jsonElementCurrentElement = currentElement(tag);
            String serialName = inlineDescriptor.getSerialName();
            AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
            if (jsonElementCurrentElement instanceof JsonPrimitive) {
                return new JsonDecoderForUnsignedTypes(StringJsonLexerKt.StringJsonLexer(json, ((JsonPrimitive) jsonElementCurrentElement).getContent()), getJson());
            }
            String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentElement.getClass()).getSimpleName() + " as the serialized body of " + serialName;
            String strRenderTagStack = renderTagStack(tag);
            String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentElement.toString(), 0, 1, null).toString() : null;
            throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string), str, -1, strRenderTagStack, string, null);
        }
        return super.decodeTaggedInline(tag, inlineDescriptor);
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public Decoder decodeInline(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return getCurrentTagOrNull() != null ? super.decodeInline(descriptor) : new JsonPrimitiveDecoder(getJson(), getValue(), this.polymorphicDiscriminator).decodeInline(descriptor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public long decodeTaggedLong(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
            try {
                return JsonElementKt.parseLongImpl(jsonPrimitive);
            } catch (IllegalArgumentException unused) {
                unparsedPrimitive(jsonPrimitive, "long", tag);
                throw new KotlinNothingValueException();
            }
        }
        String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentElement.getClass()).getSimpleName() + " as the serialized body of long";
        String strRenderTagStack = renderTagStack(tag);
        String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentElement.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string), str, -1, strRenderTagStack, string, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public float decodeTaggedFloat(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
            try {
                float f = JsonElementKt.getFloat(jsonPrimitive);
                if (getJson().getConfiguration().getAllowSpecialFloatingPointValues() || Math.abs(f) <= Float.MAX_VALUE) {
                    return f;
                }
                String strNonFiniteFpMessage = JsonExceptionsKt.nonFiniteFpMessage(Float.valueOf(f), tag);
                String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(currentObject().toString(), 0, 1, null).toString() : null;
                throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, strNonFiniteFpMessage, null, AbstractJsonLexerKt.specialFlowingValuesHint, string), strNonFiniteFpMessage, -1, null, string, AbstractJsonLexerKt.specialFlowingValuesHint);
            } catch (IllegalArgumentException unused) {
                unparsedPrimitive(jsonPrimitive, TypedValues.Custom.S_FLOAT, tag);
                throw new KotlinNothingValueException();
            }
        }
        String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentElement.getClass()).getSimpleName() + " as the serialized body of float";
        String strRenderTagStack = renderTagStack(tag);
        String string2 = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentElement.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string2), str, -1, strRenderTagStack, string2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public double decodeTaggedDouble(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
            try {
                double d = JsonElementKt.getDouble(jsonPrimitive);
                if (getJson().getConfiguration().getAllowSpecialFloatingPointValues() || Math.abs(d) <= Double.MAX_VALUE) {
                    return d;
                }
                String strNonFiniteFpMessage = JsonExceptionsKt.nonFiniteFpMessage(Double.valueOf(d), tag);
                String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(currentObject().toString(), 0, 1, null).toString() : null;
                throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, strNonFiniteFpMessage, null, AbstractJsonLexerKt.specialFlowingValuesHint, string), strNonFiniteFpMessage, -1, null, string, AbstractJsonLexerKt.specialFlowingValuesHint);
            } catch (IllegalArgumentException unused) {
                unparsedPrimitive(jsonPrimitive, "double", tag);
                throw new KotlinNothingValueException();
            }
        }
        String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentElement.getClass()).getSimpleName() + " as the serialized body of double";
        String strRenderTagStack = renderTagStack(tag);
        String string2 = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentElement.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string2), str, -1, strRenderTagStack, string2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public char decodeTaggedChar(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        if (jsonElementCurrentElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElementCurrentElement;
            try {
                return StringsKt.single(jsonPrimitive.getContent());
            } catch (IllegalArgumentException unused) {
                unparsedPrimitive(jsonPrimitive, "char", tag);
                throw new KotlinNothingValueException();
            }
        }
        String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentElement.getClass()).getSimpleName() + " as the serialized body of char";
        String strRenderTagStack = renderTagStack(tag);
        String string = abstractJsonTreeDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementCurrentElement.toString(), 0, 1, null).toString() : null;
        throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, strRenderTagStack, null, string), str, -1, strRenderTagStack, string, null);
    }
}
