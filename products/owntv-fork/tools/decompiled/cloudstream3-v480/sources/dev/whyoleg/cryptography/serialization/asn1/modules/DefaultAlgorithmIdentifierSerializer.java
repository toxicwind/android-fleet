package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.builtins.BuiltinSerializersKt;

/* JADX INFO: compiled from: AlgorithmIdentifierSerializer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/DefaultAlgorithmIdentifierSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifierSerializer;", "<init>", "()V", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DefaultAlgorithmIdentifierSerializer extends AlgorithmIdentifierSerializer {
    public static final DefaultAlgorithmIdentifierSerializer INSTANCE;

    private DefaultAlgorithmIdentifierSerializer() {
    }

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.serialization.asn1.modules.DefaultAlgorithmIdentifierSerializer$1, reason: invalid class name */
    /* JADX INFO: compiled from: AlgorithmIdentifierSerializer.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<EcParameters, EcAlgorithmIdentifier> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, EcAlgorithmIdentifier.class, "<init>", "<init>(Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ EcAlgorithmIdentifier invoke(EcParameters ecParameters) {
            EcParameters ecParameters2 = ecParameters;
            return m8775invokeib7Hyj0(ecParameters2 != null ? ecParameters2.m8784unboximpl() : null);
        }

        /* JADX INFO: renamed from: invoke-ib7Hyj0, reason: not valid java name */
        public final EcAlgorithmIdentifier m8775invokeib7Hyj0(String str) {
            return new EcAlgorithmIdentifier(str, null);
        }
    }

    static {
        DefaultAlgorithmIdentifierSerializer defaultAlgorithmIdentifierSerializer = new DefaultAlgorithmIdentifierSerializer();
        INSTANCE = defaultAlgorithmIdentifierSerializer;
        defaultAlgorithmIdentifierSerializer.m8773algorithmkjyjcj8(RSAKt.getRSA(ObjectIdentifier.INSTANCE), (KClass<RsaAlgorithmIdentifier>) Reflection.getOrCreateKotlinClass(RsaAlgorithmIdentifier.class), RsaAlgorithmIdentifier.INSTANCE, true);
        defaultAlgorithmIdentifierSerializer.m8774algorithmkjyjcj8(ECKt.getEC(ObjectIdentifier.INSTANCE), Reflection.getOrCreateKotlinClass(EcAlgorithmIdentifier.class), BuiltinSerializersKt.getNullable(EcParameters.INSTANCE.serializer()), AnonymousClass1.INSTANCE);
        defaultAlgorithmIdentifierSerializer.m8774algorithmkjyjcj8(DHKt.getDH(ObjectIdentifier.INSTANCE), Reflection.getOrCreateKotlinClass(DhAlgorithmIdentifier.class), BuiltinSerializersKt.getNullable(DhParameters.INSTANCE.serializer()), AnonymousClass2.INSTANCE);
        defaultAlgorithmIdentifierSerializer.m8774algorithmkjyjcj8(DSAKt.getDSA(ObjectIdentifier.INSTANCE), Reflection.getOrCreateKotlinClass(DsaAlgorithmIdentifier.class), BuiltinSerializersKt.getNullable(DsaParameters.INSTANCE.serializer()), AnonymousClass3.INSTANCE);
        defaultAlgorithmIdentifierSerializer.m8773algorithmkjyjcj8(EdKt.getX25519(ObjectIdentifier.INSTANCE), (KClass<X25519AlgorithmIdentifier>) Reflection.getOrCreateKotlinClass(X25519AlgorithmIdentifier.class), X25519AlgorithmIdentifier.INSTANCE, false);
        defaultAlgorithmIdentifierSerializer.m8773algorithmkjyjcj8(EdKt.getX448(ObjectIdentifier.INSTANCE), (KClass<X448AlgorithmIdentifier>) Reflection.getOrCreateKotlinClass(X448AlgorithmIdentifier.class), X448AlgorithmIdentifier.INSTANCE, false);
        defaultAlgorithmIdentifierSerializer.m8773algorithmkjyjcj8(EdKt.getEd25519(ObjectIdentifier.INSTANCE), (KClass<Ed25519AlgorithmIdentifier>) Reflection.getOrCreateKotlinClass(Ed25519AlgorithmIdentifier.class), Ed25519AlgorithmIdentifier.INSTANCE, false);
        defaultAlgorithmIdentifierSerializer.m8773algorithmkjyjcj8(EdKt.getEd448(ObjectIdentifier.INSTANCE), (KClass<Ed448AlgorithmIdentifier>) Reflection.getOrCreateKotlinClass(Ed448AlgorithmIdentifier.class), Ed448AlgorithmIdentifier.INSTANCE, false);
    }

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.serialization.asn1.modules.DefaultAlgorithmIdentifierSerializer$2, reason: invalid class name */
    /* JADX INFO: compiled from: AlgorithmIdentifierSerializer.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<DhParameters, DhAlgorithmIdentifier> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(1, DhAlgorithmIdentifier.class, "<init>", "<init>(Ldev/whyoleg/cryptography/serialization/asn1/modules/DhParameters;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final DhAlgorithmIdentifier invoke(DhParameters dhParameters) {
            return new DhAlgorithmIdentifier(dhParameters);
        }
    }

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.serialization.asn1.modules.DefaultAlgorithmIdentifierSerializer$3, reason: invalid class name */
    /* JADX INFO: compiled from: AlgorithmIdentifierSerializer.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function1<DsaParameters, DsaAlgorithmIdentifier> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        AnonymousClass3() {
            super(1, DsaAlgorithmIdentifier.class, "<init>", "<init>(Ldev/whyoleg/cryptography/serialization/asn1/modules/DsaParameters;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final DsaAlgorithmIdentifier invoke(DsaParameters dsaParameters) {
            return new DsaAlgorithmIdentifier(dsaParameters);
        }
    }
}
