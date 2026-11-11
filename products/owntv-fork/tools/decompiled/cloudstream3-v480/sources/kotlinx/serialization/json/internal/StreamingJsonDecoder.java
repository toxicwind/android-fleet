package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.PolymorphicSerializerKt;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.encoding.ChunkedDecoder;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.JsonInternalDependenciesKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonDecodingException;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: StreamingJsonDecoder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u000e\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001UB3\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\u0012\u001a\u00020\u0013*\u0004\u0018\u00010\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0082\u0080\u0004J\n\u0010 \u001a\u00020!H\u0096\u0080\u0004J#\u0010\"\u001a\u0002H#\"\u0004\b\u0000\u0010#2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H#0%H\u0096\u0080\u0004¢\u0006\u0002\u0010&J\u0012\u0010'\u001a\u00020(2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\u0012\u0010)\u001a\u00020*2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\u0012\u0010+\u001a\u00020*2\u0006\u0010\n\u001a\u00020\u000bH\u0082\u0080\u0004J\n\u0010,\u001a\u00020\u0013H\u0096\u0080\u0004J\f\u0010-\u001a\u0004\u0018\u00010.H\u0096\u0080\u0004J\n\u0010/\u001a\u00020*H\u0082\u0080\u0004J=\u00100\u001a\u0002H#\"\u0004\b\u0000\u0010#2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u001b2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H#0%2\b\u00102\u001a\u0004\u0018\u0001H#H\u0096\u0080\u0004¢\u0006\u0002\u00103J\u0012\u00104\u001a\u00020\u001b2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\n\u00105\u001a\u00020\u001bH\u0082\u0080\u0004J\u001a\u00106\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u001bH\u0082\u0080\u0004J\u0012\u00107\u001a\u00020\u001b2\u0006\u0010\n\u001a\u00020\u000bH\u0082\u0080\u0004J\u001a\u00108\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00109\u001a\u00020\u0015H\u0082\u0080\u0004J\n\u0010:\u001a\u00020\u001bH\u0082\u0080\u0004J\n\u0010;\u001a\u00020\u0013H\u0096\u0080\u0004J\n\u0010<\u001a\u00020=H\u0096\u0080\u0004J\n\u0010>\u001a\u00020?H\u0096\u0080\u0004J\n\u0010@\u001a\u00020\u001bH\u0096\u0080\u0004J\n\u0010A\u001a\u00020BH\u0096\u0080\u0004J\n\u0010C\u001a\u00020DH\u0096\u0080\u0004J\n\u0010E\u001a\u00020FH\u0096\u0080\u0004J\n\u0010G\u001a\u00020HH\u0096\u0080\u0004J\n\u0010I\u001a\u00020\u0015H\u0082\u0080\u0004J\n\u0010J\u001a\u00020\u0015H\u0096\u0080\u0004J-\u0010K\u001a\u00020*2!\u0010L\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\bN\u0012\b\bO\u0012\u0004\b\b(P\u0012\u0004\u0012\u00020*0MH\u0096\u0080\u0004J\u0012\u0010Q\u001a\u00020R2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\u0012\u0010S\u001a\u00020\u001b2\u0006\u0010T\u001a\u00020\u000bH\u0096\u0080\u0004R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8\u0000X\u0081\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0016\u001a\u00020\u0017X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000f\u0010\u001a\u001a\u00020\u001bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u001c\u001a\u00020\u001dX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006V"}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonDecoder;", "Lkotlinx/serialization/json/JsonDecoder;", "Lkotlinx/serialization/encoding/ChunkedDecoder;", "Lkotlinx/serialization/encoding/AbstractDecoder;", "json", "Lkotlinx/serialization/json/Json;", "mode", "Lkotlinx/serialization/json/internal/WriteMode;", "lexer", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "discriminatorHolder", "Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;", "<init>", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/WriteMode;Lkotlinx/serialization/json/internal/AbstractJsonLexer;Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;)V", "getJson", "()Lkotlinx/serialization/json/Json;", "trySkip", "", "unknownKey", "", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "currentIndex", "", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "elementMarker", "Lkotlinx/serialization/json/internal/JsonElementMarker;", "decodeJsonElement", "Lkotlinx/serialization/json/JsonElement;", "decodeSerializableValue", ExifInterface.GPS_DIRECTION_TRUE, "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "endStructure", "", "skipLeftoverElements", "decodeNotNullMark", "decodeNull", "", "checkLeadingComma", "decodeSerializableElement", "index", "previousValue", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;", "decodeElementIndex", "decodeMapIndex", "coerceInputValue", "decodeObjectIndex", "handleUnknown", "key", "decodeListIndex", "decodeBoolean", "decodeByte", "", "decodeShort", "", "decodeInt", "decodeLong", "", "decodeFloat", "", "decodeDouble", "", "decodeChar", "", "decodeStringKey", "decodeString", "decodeStringChunked", "consumeChunk", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "chunk", "decodeInline", "Lkotlinx/serialization/encoding/Decoder;", "decodeEnum", "enumDescriptor", "DiscriminatorHolder", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class StreamingJsonDecoder extends AbstractDecoder implements JsonDecoder, ChunkedDecoder {
    private final JsonConfiguration configuration;
    private int currentIndex;
    private DiscriminatorHolder discriminatorHolder;
    private final JsonElementMarker elementMarker;
    private final Json json;
    public final AbstractJsonLexer lexer;
    private final WriteMode mode;
    private final SerializersModule serializersModule;

    /* JADX INFO: compiled from: StreamingJsonDecoder.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WriteMode.values().length];
            try {
                iArr[WriteMode.LIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WriteMode.MAP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[WriteMode.POLY_OBJ.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[WriteMode.OBJ.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Void decodeNull() {
        return null;
    }

    public StreamingJsonDecoder(Json json, WriteMode mode, AbstractJsonLexer lexer, SerialDescriptor descriptor, DiscriminatorHolder discriminatorHolder) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(lexer, "lexer");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.json = json;
        this.mode = mode;
        this.lexer = lexer;
        this.serializersModule = json.getSerializersModule();
        this.currentIndex = -1;
        this.discriminatorHolder = discriminatorHolder;
        JsonConfiguration configuration = json.getConfiguration();
        this.configuration = configuration;
        this.elementMarker = configuration.getExplicitNulls() ? null : new JsonElementMarker(descriptor);
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public final Json getJson() {
        return this.json;
    }

    /* JADX INFO: compiled from: StreamingJsonDecoder.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0013\bF\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;", "", "discriminatorToSkip", "", "<init>", "(Ljava/lang/String;)V", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class DiscriminatorHolder {
        public String discriminatorToSkip;

        public DiscriminatorHolder(String str) {
            this.discriminatorToSkip = str;
        }
    }

    private final boolean trySkip(DiscriminatorHolder discriminatorHolder, String str) {
        if (discriminatorHolder == null || !Intrinsics.areEqual(discriminatorHolder.discriminatorToSkip, str)) {
            return false;
        }
        discriminatorHolder.discriminatorToSkip = null;
        return true;
    }

    @Override // kotlinx.serialization.encoding.Decoder, kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public JsonElement decodeJsonElement() {
        return new JsonTreeReader(this.json.getConfiguration(), this.lexer).read();
    }

    /* JADX WARN: Code duplicated, block: B:53:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:54:0x01ba  */
    /* JADX WARN: Instruction removed from duplicated block: B:54:0x01ba, please report this as an issue */
    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<? extends T> deserializer) {
        String message;
        JsonPrimitive jsonPrimitive;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        try {
            if ((deserializer instanceof AbstractPolymorphicSerializer) && !this.json.getConfiguration().getUseArrayPolymorphism()) {
                String strClassDiscriminator = PolymorphicKt.classDiscriminator(((AbstractPolymorphicSerializer) deserializer).getDescriptor(), this.json);
                String strPeekLeadingMatchingValue = this.lexer.peekLeadingMatchingValue(strClassDiscriminator, this.configuration.getIsLenient());
                if (strPeekLeadingMatchingValue != null) {
                    try {
                        DeserializationStrategy deserializationStrategyFindPolymorphicSerializer = PolymorphicSerializerKt.findPolymorphicSerializer((AbstractPolymorphicSerializer) deserializer, this, strPeekLeadingMatchingValue);
                        Intrinsics.checkNotNull(deserializationStrategyFindPolymorphicSerializer, "null cannot be cast to non-null type kotlinx.serialization.DeserializationStrategy<T of kotlinx.serialization.json.internal.StreamingJsonDecoder.decodeSerializableValue>");
                        this.discriminatorHolder = new DiscriminatorHolder(strClassDiscriminator);
                        return (T) deserializationStrategyFindPolymorphicSerializer.deserialize(this);
                    } catch (SerializationException e) {
                        String message2 = e.getMessage();
                        Intrinsics.checkNotNull(message2);
                        String strRemoveSuffix = StringsKt.removeSuffix(StringsKt.substringBefore$default(message2, '\n', (String) null, 2, (Object) null), (CharSequence) ".");
                        String message3 = e.getMessage();
                        Intrinsics.checkNotNull(message3);
                        AbstractJsonLexer.fail$default(this.lexer, strRemoveSuffix, 0, StringsKt.substringAfter(message3, '\n', ""), 2, null);
                        throw new KotlinNothingValueException();
                    }
                }
                StreamingJsonDecoder streamingJsonDecoder = this;
                if ((deserializer instanceof AbstractPolymorphicSerializer) && !streamingJsonDecoder.getJson().getConfiguration().getUseArrayPolymorphism()) {
                    String strClassDiscriminator2 = PolymorphicKt.classDiscriminator(((AbstractPolymorphicSerializer) deserializer).getDescriptor(), streamingJsonDecoder.getJson());
                    JsonElement jsonElementDecodeJsonElement = streamingJsonDecoder.decodeJsonElement();
                    String serialName = ((AbstractPolymorphicSerializer) deserializer).getDescriptor().getSerialName();
                    if (jsonElementDecodeJsonElement instanceof JsonObject) {
                        JsonObject jsonObject = (JsonObject) jsonElementDecodeJsonElement;
                        JsonElement jsonElement = (JsonElement) jsonObject.get((Object) strClassDiscriminator2);
                        try {
                            DeserializationStrategy deserializationStrategyFindPolymorphicSerializer2 = PolymorphicSerializerKt.findPolymorphicSerializer((AbstractPolymorphicSerializer) deserializer, streamingJsonDecoder, (jsonElement == null || (jsonPrimitive = JsonElementKt.getJsonPrimitive(jsonElement)) == null) ? null : JsonElementKt.getContentOrNull(jsonPrimitive));
                            Intrinsics.checkNotNull(deserializationStrategyFindPolymorphicSerializer2, "null cannot be cast to non-null type kotlinx.serialization.DeserializationStrategy<T of kotlinx.serialization.json.internal.PolymorphicKt.decodeSerializableValuePolymorphic>");
                            return (T) TreeJsonDecoderKt.readPolymorphicJson(streamingJsonDecoder.getJson(), strClassDiscriminator2, jsonObject, deserializationStrategyFindPolymorphicSerializer2);
                        } catch (SerializationException e2) {
                            String message4 = e2.getMessage();
                            Intrinsics.checkNotNull(message4);
                            String string = streamingJsonDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonObject.toString(), 0, 1, null).toString() : null;
                            throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, message4, null, null, string), message4, -1, null, string, null);
                        }
                    }
                    String str = "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementDecodeJsonElement.getClass()).getSimpleName() + " as the serialized body of " + serialName;
                    String path = this.lexer.path.getPath();
                    String string2 = streamingJsonDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? JsonExceptionsKt.minify$default(jsonElementDecodeJsonElement.toString(), 0, 1, null).toString() : null;
                    throw new JsonDecodingException(JsonExceptionsKt.formatDecodingException(-1, str, path, null, string2), str, -1, path, string2, null);
                }
                return deserializer.deserialize(streamingJsonDecoder);
                message = e.getMessage();
                Intrinsics.checkNotNull(message);
                if (StringsKt.contains$default((CharSequence) message, (CharSequence) "at path", false, 2, (Object) null)) {
                    throw e;
                }
                throw JsonInternalDependenciesKt.missingFieldExceptionWithNewMessage(e, e.getMessage() + " at path: " + this.lexer.path.getPath());
            }
            return deserializer.deserialize(this);
        } catch (MissingFieldException e3) {
            message = e3.getMessage();
            Intrinsics.checkNotNull(message);
            if (StringsKt.contains$default((CharSequence) message, (CharSequence) "at path", false, 2, (Object) null)) {
                throw e3;
            }
            throw JsonInternalDependenciesKt.missingFieldExceptionWithNewMessage(e3, e3.getMessage() + " at path: " + this.lexer.path.getPath());
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        WriteMode writeModeSwitchMode = WriteModeKt.switchMode(this.json, descriptor);
        this.lexer.path.pushDescriptor(descriptor);
        this.lexer.consumeNextToken(writeModeSwitchMode.begin);
        checkLeadingComma();
        int i = WhenMappings.$EnumSwitchMapping$0[writeModeSwitchMode.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return new StreamingJsonDecoder(this.json, writeModeSwitchMode, this.lexer, descriptor, this.discriminatorHolder);
        }
        if (this.mode != writeModeSwitchMode || !this.json.getConfiguration().getExplicitNulls()) {
            this = new StreamingJsonDecoder(this.json, writeModeSwitchMode, this.lexer, descriptor, this.discriminatorHolder);
        }
        return this;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (descriptor.getElementsCount() == 0 && JsonNamesMapKt.ignoreUnknownKeys(descriptor, this.json)) {
            skipLeftoverElements(descriptor);
        }
        if (this.lexer.tryConsumeComma() && !this.json.getConfiguration().getAllowTrailingComma()) {
            JsonExceptionsKt.invalidTrailingComma(this.lexer, "");
            throw new KotlinNothingValueException();
        }
        this.lexer.consumeNextToken(this.mode.end);
        this.lexer.path.popDescriptor();
    }

    private final void skipLeftoverElements(SerialDescriptor descriptor) {
        while (decodeElementIndex(descriptor) != -1) {
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        JsonElementMarker jsonElementMarker = this.elementMarker;
        return ((jsonElementMarker != null ? jsonElementMarker.getIsUnmarkedNull() : false) || AbstractJsonLexer.tryConsumeNull$default(this.lexer, false, 1, null)) ? false : true;
    }

    private final void checkLeadingComma() {
        if (this.lexer.peekNextToken() != 4) {
            return;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public <T> T decodeSerializableElement(SerialDescriptor descriptor, int index, DeserializationStrategy<? extends T> deserializer, T previousValue) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        boolean z = this.mode == WriteMode.MAP && (index & 1) == 0;
        if (z) {
            this.lexer.path.resetCurrentMapKey();
        }
        T t = (T) super.decodeSerializableElement(descriptor, index, deserializer, previousValue);
        if (z) {
            this.lexer.path.updateCurrentMapKey(t);
        }
        return t;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor descriptor) {
        int iDecodeMapIndex;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        int i = WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()];
        if (i == 2) {
            iDecodeMapIndex = decodeMapIndex();
        } else if (i == 4) {
            iDecodeMapIndex = decodeObjectIndex(descriptor);
        } else {
            iDecodeMapIndex = decodeListIndex();
        }
        if (this.mode != WriteMode.MAP) {
            this.lexer.path.updateDescriptorIndex(iDecodeMapIndex);
        }
        return iDecodeMapIndex;
    }

    private final int decodeMapIndex() {
        int i = this.currentIndex;
        boolean zTryConsumeComma = false;
        boolean z = i % 2 != 0;
        if (!z) {
            this.lexer.consumeNextToken(AbstractJsonLexerKt.COLON);
        } else if (i != -1) {
            zTryConsumeComma = this.lexer.tryConsumeComma();
        }
        if (this.lexer.canConsumeValue()) {
            if (z) {
                int i2 = this.currentIndex;
                AbstractJsonLexer abstractJsonLexer = this.lexer;
                if (i2 == -1) {
                    int i3 = abstractJsonLexer.currentPosition;
                    if (zTryConsumeComma) {
                        AbstractJsonLexer.fail$default(abstractJsonLexer, "Unexpected leading comma", i3, null, 4, null);
                        throw new KotlinNothingValueException();
                    }
                } else {
                    int i4 = abstractJsonLexer.currentPosition;
                    if (!zTryConsumeComma) {
                        AbstractJsonLexer.fail$default(abstractJsonLexer, "Expected comma after the key-value pair", i4, null, 4, null);
                        throw new KotlinNothingValueException();
                    }
                }
            }
            int i5 = this.currentIndex + 1;
            this.currentIndex = i5;
            return i5;
        }
        if (!zTryConsumeComma || this.json.getConfiguration().getAllowTrailingComma()) {
            return -1;
        }
        JsonExceptionsKt.invalidTrailingComma$default(this.lexer, null, 1, null);
        throw new KotlinNothingValueException();
    }

    private final boolean coerceInputValue(SerialDescriptor descriptor, int index) {
        String strPeekString;
        Json json = this.json;
        boolean zIsElementOptional = descriptor.isElementOptional(index);
        SerialDescriptor elementDescriptor = descriptor.getElementDescriptor(index);
        if (zIsElementOptional && !elementDescriptor.isNullable() && this.lexer.tryConsumeNull(true)) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || ((elementDescriptor.isNullable() && this.lexer.tryConsumeNull(false)) || (strPeekString = this.lexer.peekString(this.configuration.getIsLenient())) == null)) {
            return false;
        }
        int jsonNameIndex = JsonNamesMapKt.getJsonNameIndex(elementDescriptor, json, strPeekString);
        boolean z = !json.getConfiguration().getExplicitNulls() && elementDescriptor.isNullable();
        if (jsonNameIndex == -3 && (zIsElementOptional || z)) {
            this.lexer.consumeString();
            return true;
        }
        return false;
    }

    private final int decodeObjectIndex(SerialDescriptor descriptor) {
        boolean zTryConsumeComma;
        boolean zTryConsumeComma2 = this.lexer.tryConsumeComma();
        while (true) {
            boolean z = true;
            if (this.lexer.canConsumeValue()) {
                String strDecodeStringKey = decodeStringKey();
                this.lexer.consumeNextToken(AbstractJsonLexerKt.COLON);
                int jsonNameIndex = JsonNamesMapKt.getJsonNameIndex(descriptor, this.json, strDecodeStringKey);
                if (jsonNameIndex == -3) {
                    zTryConsumeComma = false;
                } else if (this.configuration.getCoerceInputValues() && coerceInputValue(descriptor, jsonNameIndex)) {
                    zTryConsumeComma = this.lexer.tryConsumeComma();
                    z = false;
                } else {
                    JsonElementMarker jsonElementMarker = this.elementMarker;
                    if (jsonElementMarker != null) {
                        jsonElementMarker.mark$kotlinx_serialization_json(jsonNameIndex);
                    }
                    return jsonNameIndex;
                }
                zTryConsumeComma2 = z ? handleUnknown(descriptor, strDecodeStringKey) : zTryConsumeComma;
            } else {
                if (zTryConsumeComma2 && !this.json.getConfiguration().getAllowTrailingComma()) {
                    JsonExceptionsKt.invalidTrailingComma$default(this.lexer, null, 1, null);
                    throw new KotlinNothingValueException();
                }
                JsonElementMarker jsonElementMarker2 = this.elementMarker;
                if (jsonElementMarker2 != null) {
                    return jsonElementMarker2.nextUnmarkedIndex$kotlinx_serialization_json();
                }
                return -1;
            }
        }
    }

    private final boolean handleUnknown(SerialDescriptor descriptor, String key) {
        if (JsonNamesMapKt.ignoreUnknownKeys(descriptor, this.json) || trySkip(this.discriminatorHolder, key)) {
            this.lexer.skipElement(this.configuration.getIsLenient());
        } else {
            this.lexer.path.popDescriptor();
            this.lexer.failOnUnknownKey(key);
        }
        return this.lexer.tryConsumeComma();
    }

    private final int decodeListIndex() {
        boolean zTryConsumeComma = this.lexer.tryConsumeComma();
        if (this.lexer.canConsumeValue()) {
            int i = this.currentIndex;
            if (i != -1 && !zTryConsumeComma) {
                AbstractJsonLexer.fail$default(this.lexer, "Expected end of the array or comma", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            int i2 = i + 1;
            this.currentIndex = i2;
            return i2;
        }
        if (!zTryConsumeComma || this.json.getConfiguration().getAllowTrailingComma()) {
            return -1;
        }
        JsonExceptionsKt.invalidTrailingComma(this.lexer, "array");
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        return this.lexer.consumeBooleanLenient();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public byte decodeByte() {
        long jConsumeNumericLiteral = this.lexer.consumeNumericLiteral();
        byte b = (byte) jConsumeNumericLiteral;
        if (jConsumeNumericLiteral == b) {
            return b;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Failed to parse byte for input '" + jConsumeNumericLiteral + '\'', 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public short decodeShort() {
        long jConsumeNumericLiteral = this.lexer.consumeNumericLiteral();
        short s = (short) jConsumeNumericLiteral;
        if (jConsumeNumericLiteral == s) {
            return s;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Failed to parse short for input '" + jConsumeNumericLiteral + '\'', 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeInt() {
        long jConsumeNumericLiteral = this.lexer.consumeNumericLiteral();
        int i = (int) jConsumeNumericLiteral;
        if (jConsumeNumericLiteral == i) {
            return i;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Failed to parse int for input '" + jConsumeNumericLiteral + '\'', 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public long decodeLong() {
        return this.lexer.consumeNumericLiteral();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public float decodeFloat() {
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        String strConsumeStringLenient = abstractJsonLexer.consumeStringLenient();
        try {
            float f = Float.parseFloat(strConsumeStringLenient);
            if (this.json.getConfiguration().getAllowSpecialFloatingPointValues() || Math.abs(f) <= Float.MAX_VALUE) {
                return f;
            }
            JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Float.valueOf(f));
            throw new KotlinNothingValueException();
        } catch (IllegalArgumentException unused) {
            AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse type 'float' for input '" + strConsumeStringLenient + '\'', 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public double decodeDouble() {
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        String strConsumeStringLenient = abstractJsonLexer.consumeStringLenient();
        try {
            double d = Double.parseDouble(strConsumeStringLenient);
            if (this.json.getConfiguration().getAllowSpecialFloatingPointValues() || Math.abs(d) <= Double.MAX_VALUE) {
                return d;
            }
            JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Double.valueOf(d));
            throw new KotlinNothingValueException();
        } catch (IllegalArgumentException unused) {
            AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse type 'double' for input '" + strConsumeStringLenient + '\'', 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public char decodeChar() {
        String strConsumeStringLenient = this.lexer.consumeStringLenient();
        if (strConsumeStringLenient.length() != 1) {
            AbstractJsonLexer.fail$default(this.lexer, "Expected single char, but got '" + strConsumeStringLenient + '\'', 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        return strConsumeStringLenient.charAt(0);
    }

    private final String decodeStringKey() {
        boolean isLenient = this.configuration.getIsLenient();
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        if (isLenient) {
            return abstractJsonLexer.consumeStringLenientNotNull();
        }
        return abstractJsonLexer.consumeKeyString();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public String decodeString() {
        boolean isLenient = this.configuration.getIsLenient();
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        if (isLenient) {
            return abstractJsonLexer.consumeStringLenientNotNull();
        }
        return abstractJsonLexer.consumeString();
    }

    @Override // kotlinx.serialization.encoding.ChunkedDecoder
    public void decodeStringChunked(Function1<? super String, Unit> consumeChunk) {
        Intrinsics.checkNotNullParameter(consumeChunk, "consumeChunk");
        this.lexer.consumeStringChunked(this.configuration.getIsLenient(), consumeChunk);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Decoder decodeInline(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return StreamingJsonEncoderKt.isUnsignedNumber(descriptor) ? new JsonDecoderForUnsignedTypes(this.lexer, this.json) : super.decodeInline(descriptor);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeEnum(SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        return JsonNamesMapKt.getJsonNameIndexOrThrow(enumDescriptor, this.json, decodeString(), " at path " + this.lexer.path.getPath());
    }
}
