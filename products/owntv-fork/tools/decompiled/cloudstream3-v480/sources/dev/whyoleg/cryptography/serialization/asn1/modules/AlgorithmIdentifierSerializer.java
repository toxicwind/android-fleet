package dev.whyoleg.cryptography.serialization.asn1.modules;

import androidx.exifinterface.media.ExifInterface;
import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: AlgorithmIdentifierSerializer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001,B\t\bF¢\u0006\u0004\b\u0003\u0010\u0004J-\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u000f\u001a\u00020\bH\u0082\u0080\u0004¢\u0006\u0004\b\u0010\u0010\u0011J[\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u00022\u0006\u0010\r\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00130\u00072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00012\u0014\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u0001H\u0012\u0012\u0004\u0012\u0002H\u00130\u0016H\u0084\u0080\u0004¢\u0006\u0004\b\u0017\u0010\u0018JA\u0010\u000b\u001a\u00020\f\"\b\b\u0000\u0010\u0013*\u00020\u00022\u0006\u0010\r\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00130\u00072\u0006\u0010\u0019\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0084\u0080\u0004¢\u0006\u0004\b\u0017\u0010\u001cJ5\u0010\u000b\u001a\u00020\f\"\n\b\u0000\u0010\u0013\u0018\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u0019\u001a\u0002H\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0084\u0088\u0004¢\u0006\u0004\b\u0010\u0010\u001dJH\u0010\u000b\u001a\u00020\f\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\n\b\u0001\u0010\u0013\u0018\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\n2\u0016\b\b\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u0001H\u0012\u0012\u0004\u0012\u0002H\u00130\u0016H\u0084\u0088\u0004ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0002H\u0086\u0080\u0004J\u0012\u0010)\u001a\u00020\u00022\u0006\u0010*\u001a\u00020+H\u0086\u0080\u0004R\u001f\u0010\u0005\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u001b\u0010 \u001a\u00020!X\u0086\u0084\b¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0004\u001a\u0004\b#\u0010$\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006-"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifierSerializer;", "Lkotlinx/serialization/KSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "<init>", "()V", "byClass", "", "Lkotlin/reflect/KClass;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifierSerializer$AlgorithmEntry;", "byOid", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "algorithm", "", "oid", "cls", "entry", "algorithm-MkjCZII", "(Ljava/lang/String;Lkotlin/reflect/KClass;Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifierSerializer$AlgorithmEntry;)V", "P", ExifInterface.GPS_DIRECTION_TRUE, "parametersSerializer", "factory", "Lkotlin/Function1;", "algorithm-kjyjcj8", "(Ljava/lang/String;Lkotlin/reflect/KClass;Lkotlinx/serialization/KSerializer;Lkotlin/jvm/functions/Function1;)V", "instance", "encodeNull", "", "(Ljava/lang/String;Lkotlin/reflect/KClass;Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;Z)V", "(Ljava/lang/String;Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;Z)V", "algorithm-4RESAxk", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor$annotations", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialize", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "AlgorithmEntry", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ExperimentalSerializationApi
public abstract class AlgorithmIdentifierSerializer implements KSerializer<AlgorithmIdentifier> {
    private final Map<KClass<?>, AlgorithmEntry> byClass = new LinkedHashMap();
    private final Map<ObjectIdentifier, AlgorithmEntry> byOid = new LinkedHashMap();
    private final SerialDescriptor descriptor = SerialDescriptorsKt.buildSerialDescriptor("AlgorithmIdentifier", PolymorphicKind.OPEN.INSTANCE, new SerialDescriptor[0], new Function1() { // from class: dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifierSerializer$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return AlgorithmIdentifierSerializer.descriptor$lambda$0((ClassSerialDescriptorBuilder) obj);
        }
    });

    public static /* synthetic */ void getDescriptor$annotations() {
    }

    /* JADX INFO: renamed from: algorithm-MkjCZII, reason: not valid java name */
    private final void m8770algorithmMkjCZII(String oid, KClass<?> cls, AlgorithmEntry entry) {
        this.byClass.put(cls, entry);
        this.byOid.put(ObjectIdentifier.m8755boximpl(oid), entry);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: algorithm-kjyjcj8, reason: not valid java name */
    public final <P, T extends AlgorithmIdentifier> void m8774algorithmkjyjcj8(String oid, KClass<T> cls, KSerializer<P> parametersSerializer, Function1<? super P, ? extends T> factory) {
        Intrinsics.checkNotNullParameter(oid, "oid");
        Intrinsics.checkNotNullParameter(cls, "cls");
        Intrinsics.checkNotNullParameter(parametersSerializer, "parametersSerializer");
        Intrinsics.checkNotNullParameter(factory, "factory");
        m8770algorithmMkjCZII(oid, (KClass<?>) cls, new AlgorithmEntry(parametersSerializer, (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(factory, 1)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: algorithm-kjyjcj8, reason: not valid java name */
    public final <T extends AlgorithmIdentifier> void m8773algorithmkjyjcj8(String oid, KClass<T> cls, final T instance, boolean encodeNull) {
        Intrinsics.checkNotNullParameter(oid, "oid");
        Intrinsics.checkNotNullParameter(cls, "cls");
        Intrinsics.checkNotNullParameter(instance, "instance");
        m8774algorithmkjyjcj8(oid, (KClass) cls, (KSerializer) (encodeNull ? BuiltinSerializersKt.NothingSerializer() : (KSerializer) AbsentNothingSerializer.INSTANCE), new Function1() { // from class: dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifierSerializer$algorithm$1
            /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/Void;)TT; */
            @Override // kotlin.jvm.functions.Function1
            public final AlgorithmIdentifier invoke(Void r1) {
                return instance;
            }
        });
    }

    /* JADX INFO: renamed from: algorithm-MkjCZII, reason: not valid java name */
    protected final /* synthetic */ <T extends AlgorithmIdentifier> void m8772algorithmMkjCZII(String oid, T instance, boolean encodeNull) {
        Intrinsics.checkNotNullParameter(oid, "oid");
        Intrinsics.checkNotNullParameter(instance, "instance");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        m8773algorithmkjyjcj8(oid, Reflection.getOrCreateKotlinClass(AlgorithmIdentifier.class), instance, encodeNull);
    }

    /* JADX INFO: renamed from: algorithm-4RESAxk, reason: not valid java name */
    protected final /* synthetic */ <P, T extends AlgorithmIdentifier> void m8771algorithm4RESAxk(String oid, Function1<? super P, ? extends T> factory) {
        Intrinsics.checkNotNullParameter(oid, "oid");
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(AlgorithmIdentifier.class);
        Intrinsics.reifiedOperationMarker(6, "P");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
        m8774algorithmkjyjcj8(oid, orCreateKotlinClass, SerializersKt.serializer((KType) null), factory);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    static final Unit descriptor$lambda$0(ClassSerialDescriptorBuilder buildSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "algorithm", ObjectIdentifier.INSTANCE.serializer().getDescriptor(), null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "parameters", SerialDescriptorsKt.buildSerialDescriptor$default("Any", SerialKind.CONTEXTUAL.INSTANCE, new SerialDescriptor[0], null, 8, null), null, false, 12, null);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, AlgorithmIdentifier value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = this.descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        compositeEncoderBeginStructure.encodeSerializableElement(getDescriptor(), 0, ObjectIdentifier.INSTANCE.serializer(), ObjectIdentifier.m8755boximpl(value.getAlgorithm()));
        SerialDescriptor descriptor = getDescriptor();
        AlgorithmEntry algorithmEntry = (AlgorithmEntry) this.byClass.get(Reflection.getOrCreateKotlinClass(value.getClass()));
        KSerializer<? extends Object> serializer = algorithmEntry != null ? algorithmEntry.getSerializer() : null;
        KSerializer<? extends Object> kSerializer = serializer instanceof KSerializer ? serializer : null;
        if (kSerializer != null) {
            compositeEncoderBeginStructure.encodeNullableSerializableElement(descriptor, 1, kSerializer, value.getParameters());
            compositeEncoderBeginStructure.endStructure(serialDescriptor);
        } else {
            throw new IllegalStateException(("No serializer for " + Reflection.getOrCreateKotlinClass(value.getClass())).toString());
        }
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final AlgorithmIdentifier deserialize(Decoder decoder) {
        Object objDecodeNullableSerializableElement$default;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = this.descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        if (compositeDecoderBeginStructure.decodeElementIndex(getDescriptor()) != 0) {
            throw new IllegalStateException("Check failed.");
        }
        String strM8761unboximpl = ((ObjectIdentifier) CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), 0, ObjectIdentifier.INSTANCE.serializer(), null, 8, null)).m8761unboximpl();
        AlgorithmEntry algorithmEntry = (AlgorithmEntry) this.byOid.get(ObjectIdentifier.m8755boximpl(strM8761unboximpl));
        if (algorithmEntry == null) {
            throw new IllegalStateException(("Unknown algorithm: " + ((Object) ObjectIdentifier.m8760toStringimpl(strM8761unboximpl))).toString());
        }
        int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(getDescriptor());
        if (iDecodeElementIndex == -1) {
            objDecodeNullableSerializableElement$default = null;
        } else if (iDecodeElementIndex == 1) {
            objDecodeNullableSerializableElement$default = CompositeDecoder.CC.decodeNullableSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), 1, algorithmEntry.getSerializer(), null, 8, null);
            if (compositeDecoderBeginStructure.decodeElementIndex(getDescriptor()) != -1) {
                throw new IllegalStateException("Check failed.");
            }
        } else {
            throw new IllegalStateException(("Unexpected element index: " + iDecodeElementIndex).toString());
        }
        AlgorithmIdentifier algorithmIdentifierInvoke = algorithmEntry.getFactory().invoke(objDecodeNullableSerializableElement$default);
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return algorithmIdentifierInvoke;
    }

    /* JADX INFO: compiled from: AlgorithmIdentifierSerializer.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B1\bF\u0012\u0010\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u001f\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR#\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifierSerializer$AlgorithmEntry;", "", "serializer", "Lkotlinx/serialization/KSerializer;", "factory", "Lkotlin/Function1;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "<init>", "(Lkotlinx/serialization/KSerializer;Lkotlin/jvm/functions/Function1;)V", "getSerializer", "()Lkotlinx/serialization/KSerializer;", "getFactory", "()Lkotlin/jvm/functions/Function1;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class AlgorithmEntry {
        private final Function1<Object, AlgorithmIdentifier> factory;
        private final KSerializer<? extends Object> serializer;

        /* JADX WARN: Multi-variable type inference failed */
        public AlgorithmEntry(KSerializer<? extends Object> serializer, Function1<Object, ? extends AlgorithmIdentifier> factory) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            Intrinsics.checkNotNullParameter(factory, "factory");
            this.serializer = serializer;
            this.factory = factory;
        }

        public final KSerializer<? extends Object> getSerializer() {
            return this.serializer;
        }

        public final Function1<Object, AlgorithmIdentifier> getFactory() {
            return this.factory;
        }
    }
}
