package dev.whyoleg.cryptography.serialization.asn1;

import androidx.exifinterface.media.ExifInterface;
import dev.whyoleg.cryptography.serialization.asn1.internal.ByteArrayInput;
import dev.whyoleg.cryptography.serialization.asn1.internal.ByteArrayOutput;
import dev.whyoleg.cryptography.serialization.asn1.internal.DerDecoder;
import dev.whyoleg.cryptography.serialization.asn1.internal.DerEncoder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.BinaryFormat;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuildersKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Der.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00142\u00020\u0001:\u0002\u0014\u0015B\u0011\bD\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J+\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\f2\u0006\u0010\r\u001a\u0002H\nH\u0096\u0080\u0004¢\u0006\u0002\u0010\u000eJ+\u0010\u000f\u001a\u0002H\n\"\u0004\b\u0000\u0010\n2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\n0\u00112\u0006\u0010\u0012\u001a\u00020\tH\u0096\u0080\u0004¢\u0006\u0002\u0010\u0013R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\u0016\u0017¨\u0006\u0018"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/Der;", "Lkotlinx/serialization/BinaryFormat;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "<init>", "(Lkotlinx/serialization/modules/SerializersModule;)V", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "encodeToByteArray", "", ExifInterface.GPS_DIRECTION_TRUE, "serializer", "Lkotlinx/serialization/SerializationStrategy;", ES6Iterator.VALUE_PROPERTY, "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)[B", "decodeFromByteArray", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "bytes", "(Lkotlinx/serialization/DeserializationStrategy;[B)Ljava/lang/Object;", "Default", "Builder", "Ldev/whyoleg/cryptography/serialization/asn1/Der$Default;", "Ldev/whyoleg/cryptography/serialization/asn1/DerImpl;", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class Der implements BinaryFormat {

    /* JADX INFO: renamed from: Default, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final SerializersModule serializersModule;

    public /* synthetic */ Der(SerializersModule serializersModule, DefaultConstructorMarker defaultConstructorMarker) {
        this(serializersModule);
    }

    private Der(SerializersModule serializersModule) {
        this.serializersModule = serializersModule;
    }

    @Override // kotlinx.serialization.SerialFormat
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.serialization.asn1.Der$Default, reason: from kotlin metadata */
    /* JADX INFO: compiled from: Der.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/Der$Default;", "Ldev/whyoleg/cryptography/serialization/asn1/Der;", "<init>", "()V", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion extends Der {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
            super(SerializersModuleBuildersKt.EmptySerializersModule(), null);
        }
    }

    @Override // kotlinx.serialization.BinaryFormat
    public <T> byte[] encodeToByteArray(SerializationStrategy<? super T> serializer, T value) {
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        ByteArrayOutput byteArrayOutput = new ByteArrayOutput();
        new DerEncoder(this, byteArrayOutput, null, 4, null).encodeSerializableValue(serializer, value);
        return byteArrayOutput.toByteArray();
    }

    @Override // kotlinx.serialization.BinaryFormat
    public <T> T decodeFromByteArray(DeserializationStrategy<? extends T> deserializer, byte[] bytes) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return (T) new DerDecoder(this, new ByteArrayInput(bytes, 0, 0, 6, null)).decodeSerializableValue(deserializer);
    }

    /* JADX INFO: compiled from: Der.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\b@\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001b\u0010\u0006\u001a\u00020\u0007X\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/Der$Builder;", "", "der", "Ldev/whyoleg/cryptography/serialization/asn1/Der;", "<init>", "(Ldev/whyoleg/cryptography/serialization/asn1/Der;)V", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "setSerializersModule", "(Lkotlinx/serialization/modules/SerializersModule;)V", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Builder {
        private SerializersModule serializersModule;

        public Builder(Der der) {
            Intrinsics.checkNotNullParameter(der, "der");
            this.serializersModule = der.getSerializersModule();
        }

        public final SerializersModule getSerializersModule() {
            return this.serializersModule;
        }

        public final void setSerializersModule(SerializersModule serializersModule) {
            Intrinsics.checkNotNullParameter(serializersModule, "<set-?>");
            this.serializersModule = serializersModule;
        }
    }
}
