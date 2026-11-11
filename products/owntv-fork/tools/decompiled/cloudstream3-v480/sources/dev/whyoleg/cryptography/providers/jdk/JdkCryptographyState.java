package dev.whyoleg.cryptography.providers.jdk;

import androidx.exifinterface.media.ExifInterface;
import dev.whyoleg.cryptography.CryptographySystem;
import dev.whyoleg.cryptography.random.CryptographyRandom_jvmKt;
import j$.util.concurrent.ConcurrentHashMap;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkCryptographyState.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\u0010\u0004\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u0003¢\u0006\u0004\b\u0005\u0010\u0006Jz\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\n\"\u0004\b\u0000\u0010\u0007*\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n0\b2\u0006\u0010\u000b\u001a\u00020\t2\u0014\b\u0004\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00028\u00000\f2\u001e\b\u0004\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u0004\u0012\u00028\u00000\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u0082\b¢\u0006\u0004\b\u0012\u0010\u0013J)\u0010\u0016\u001a\f\u0012\b\u0012\u00060\u0014j\u0002`\u00150\n2\u0006\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u001a\u001a\f\u0012\b\u0012\u00060\u0018j\u0002`\u00190\n2\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001e\u001a\f\u0012\b\u0012\u00060\u001cj\u0002`\u001d0\n2\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b\u001e\u0010\u001bJ\u001f\u0010!\u001a\f\u0012\b\u0012\u00060\u001fj\u0002` 0\n2\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b!\u0010\u001bJ\u001f\u0010$\u001a\f\u0012\b\u0012\u00060\"j\u0002`#0\n2\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b$\u0010\u001bJ\u001f\u0010'\u001a\f\u0012\b\u0012\u00060%j\u0002`&0\n2\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b'\u0010\u001bJ\u001f\u0010*\u001a\f\u0012\b\u0012\u00060(j\u0002`)0\n2\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b*\u0010\u001bJ\u001f\u0010-\u001a\f\u0012\b\u0012\u00060+j\u0002`,0\n2\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b-\u0010\u001bJ\u0019\u00100\u001a\u00060.j\u0002`/2\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b0\u00101J\u001f\u00104\u001a\f\u0012\b\u0012\u000602j\u0002`30\n2\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b4\u0010\u001bJ\u001f\u00107\u001a\f\u0012\b\u0012\u000605j\u0002`60\n2\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b7\u0010\u001bR\u001c\u0010\u0004\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u00108R\u001b\u0010;\u001a\u000609j\u0002`:8\u0006¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R*\u0010?\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0014j\u0002`\u00150\n0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R*\u0010A\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0018j\u0002`\u00190\n0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010@R*\u0010B\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u001cj\u0002`\u001d0\n0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010@R*\u0010C\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u001fj\u0002` 0\n0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010@R*\u0010D\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\b\u0012\u00060\"j\u0002`#0\n0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010@R*\u0010E\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\b\u0012\u00060%j\u0002`&0\n0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010@R*\u0010F\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\b\u0012\u00060(j\u0002`)0\n0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010@R*\u0010G\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\b\u0012\u00060+j\u0002`,0\n0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bG\u0010@R*\u00100\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\b\u0012\u00060.j\u0002`/0\n0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u0010@R*\u0010H\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\b\u0012\u000602j\u0002`30\n0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010@R*\u0010I\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\b\u0012\u000605j\u0002`60\n0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010@¨\u0006J"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "", "Ljava/security/Provider;", "Ldev/whyoleg/cryptography/providers/jdk/JProvider;", "provider", "<init>", "(Ljava/security/Provider;)V", ExifInterface.GPS_DIRECTION_TRUE, "j$/util/concurrent/ConcurrentHashMap", "", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "algorithm", "Lkotlin/Function1;", "fromDefault", "Lkotlin/Function2;", "fromProvider", "", "cached", "get", "(Lj$/util/concurrent/ConcurrentHashMap;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Z)Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/Cipher;", "Ldev/whyoleg/cryptography/providers/jdk/JCipher;", "cipher", "(Ljava/lang/String;Z)Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljava/security/MessageDigest;", "Ldev/whyoleg/cryptography/providers/jdk/JMessageDigest;", "messageDigest", "(Ljava/lang/String;)Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/Mac;", "Ldev/whyoleg/cryptography/providers/jdk/JMac;", "mac", "Ljava/security/Signature;", "Ldev/whyoleg/cryptography/providers/jdk/JSignature;", "signature", "Ljavax/crypto/KeyGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyGenerator;", "keyGenerator", "Ljava/security/KeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPairGenerator;", "keyPairGenerator", "Ljava/security/KeyFactory;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyFactory;", "keyFactory", "Ljavax/crypto/SecretKeyFactory;", "Ldev/whyoleg/cryptography/providers/jdk/JSecretKeyFactory;", "secretKeyFactory", "Ljava/security/AlgorithmParameters;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameters;", "algorithmParameters", "(Ljava/lang/String;)Ljava/security/AlgorithmParameters;", "Ljava/security/AlgorithmParameterGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameterGenerator;", "algorithmParameterGenerator", "Ljavax/crypto/KeyAgreement;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyAgreement;", "keyAgreement", "Ljava/security/Provider;", "Ljava/security/SecureRandom;", "Ldev/whyoleg/cryptography/providers/jdk/JSecureRandom;", "secureRandom", "Ljava/security/SecureRandom;", "getSecureRandom", "()Ljava/security/SecureRandom;", "ciphers", "Lj$/util/concurrent/ConcurrentHashMap;", "messageDigests", "macs", "signatures", "keyGenerators", "keyPairGenerators", "keyFactories", "secretKeyFactories", "algorithmParameterGenerators", "keyAgreements", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkCryptographyState {
    private final Provider provider;
    private final SecureRandom secureRandom = CryptographyRandom_jvmKt.asSecureRandom(CryptographySystem.INSTANCE.getDefaultRandom());
    private final ConcurrentHashMap<String, Pooled<Cipher>> ciphers = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Pooled<MessageDigest>> messageDigests = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Pooled<Mac>> macs = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Pooled<Signature>> signatures = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Pooled<KeyGenerator>> keyGenerators = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Pooled<KeyPairGenerator>> keyPairGenerators = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Pooled<KeyFactory>> keyFactories = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Pooled<SecretKeyFactory>> secretKeyFactories = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Pooled<AlgorithmParameters>> algorithmParameters = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Pooled<AlgorithmParameterGenerator>> algorithmParameterGenerators = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Pooled<KeyAgreement>> keyAgreements = new ConcurrentHashMap<>();

    public JdkCryptographyState(Provider provider) {
        this.provider = provider;
    }

    public final SecureRandom getSecureRandom() {
        return this.secureRandom;
    }

    private final <T> Pooled<T> get(ConcurrentHashMap<String, Pooled<T>> concurrentHashMap, String str, Function1<? super String, ? extends T> function1, Function2<? super String, ? super Provider, ? extends T> function2, boolean z) {
        Pooled.Empty empty;
        ConcurrentHashMap<String, Pooled<T>> concurrentHashMap2 = concurrentHashMap;
        Pooled<T> pooled = concurrentHashMap2.get(str);
        if (pooled == null) {
            Provider provider = this.provider;
            JdkCryptographyState$get$1$instantiate$2 jdkCryptographyState$get$1$instantiate$1 = provider == null ? new JdkCryptographyState$get$1$instantiate$1(function1, str) : new JdkCryptographyState$get$1$instantiate$2(function2, str, provider);
            if (z) {
                empty = new Pooled.Cached(jdkCryptographyState$get$1$instantiate$1);
            } else {
                if (z) {
                    throw new NoWhenBranchMatchedException();
                }
                empty = new Pooled.Empty(jdkCryptographyState$get$1$instantiate$1);
            }
            Pooled<T> pooledPutIfAbsent = concurrentHashMap2.putIfAbsent(str, empty);
            pooled = pooledPutIfAbsent == null ? empty : pooledPutIfAbsent;
        }
        Intrinsics.checkNotNullExpressionValue(pooled, "getOrPut(...)");
        return pooled;
    }

    static /* synthetic */ Pooled get$default(JdkCryptographyState jdkCryptographyState, ConcurrentHashMap concurrentHashMap, String str, Function1 function1, Function2 function2, boolean z, int i, Object obj) {
        Pooled.Empty empty;
        if ((i & 8) != 0) {
            z = true;
        }
        ConcurrentHashMap concurrentHashMap2 = concurrentHashMap;
        Object obj2 = concurrentHashMap2.get(str);
        if (obj2 == null) {
            Provider provider = jdkCryptographyState.provider;
            JdkCryptographyState$get$1$instantiate$2 jdkCryptographyState$get$1$instantiate$1 = provider == null ? new JdkCryptographyState$get$1$instantiate$1(function1, str) : new JdkCryptographyState$get$1$instantiate$2(function2, str, provider);
            if (z) {
                empty = new Pooled.Cached(jdkCryptographyState$get$1$instantiate$1);
            } else {
                if (z) {
                    throw new NoWhenBranchMatchedException();
                }
                empty = new Pooled.Empty(jdkCryptographyState$get$1$instantiate$1);
            }
            Object objPutIfAbsent = concurrentHashMap2.putIfAbsent(str, empty);
            obj2 = objPutIfAbsent == null ? empty : objPutIfAbsent;
        }
        Intrinsics.checkNotNullExpressionValue(obj2, "getOrPut(...)");
        return (Pooled) obj2;
    }

    public static /* synthetic */ Pooled cipher$default(JdkCryptographyState jdkCryptographyState, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return jdkCryptographyState.cipher(str, z);
    }

    public final Pooled<Cipher> cipher(final String algorithm, boolean cached) {
        Pooled.Empty empty;
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        ConcurrentHashMap<String, Pooled<Cipher>> concurrentHashMap = this.ciphers;
        Pooled<Cipher> pooled = concurrentHashMap.get(algorithm);
        if (pooled == null) {
            final Provider provider = this.provider;
            Function0<Cipher> function0 = provider == null ? new Function0<Cipher>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$cipher$$inlined$get$1
                @Override // kotlin.jvm.functions.Function0
                public final Cipher invoke() {
                    return Cipher.getInstance(algorithm);
                }
            } : new Function0<Cipher>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$cipher$$inlined$get$2
                @Override // kotlin.jvm.functions.Function0
                public final Cipher invoke() {
                    return Cipher.getInstance(algorithm, provider);
                }
            };
            if (cached) {
                empty = new Pooled.Cached(function0);
            } else {
                if (cached) {
                    throw new NoWhenBranchMatchedException();
                }
                empty = new Pooled.Empty(function0);
            }
            Pooled<Cipher> pooledPutIfAbsent = concurrentHashMap.putIfAbsent(algorithm, empty);
            pooled = pooledPutIfAbsent == null ? empty : pooledPutIfAbsent;
        }
        Intrinsics.checkNotNullExpressionValue(pooled, "getOrPut(...)");
        return pooled;
    }

    public final Pooled<MessageDigest> messageDigest(final String algorithm) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        ConcurrentHashMap<String, Pooled<MessageDigest>> concurrentHashMap = this.messageDigests;
        Pooled.Cached cached = concurrentHashMap.get(algorithm);
        if (cached == null) {
            final Provider provider = this.provider;
            cached = new Pooled.Cached(provider == null ? new Function0<MessageDigest>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$messageDigest$$inlined$get$default$1
                @Override // kotlin.jvm.functions.Function0
                public final MessageDigest invoke() {
                    return MessageDigest.getInstance(algorithm);
                }
            } : new Function0<MessageDigest>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$messageDigest$$inlined$get$default$2
                @Override // kotlin.jvm.functions.Function0
                public final MessageDigest invoke() {
                    return MessageDigest.getInstance(algorithm, provider);
                }
            });
            Pooled<MessageDigest> pooledPutIfAbsent = concurrentHashMap.putIfAbsent(algorithm, cached);
            if (pooledPutIfAbsent != null) {
                cached = pooledPutIfAbsent;
            }
        }
        Intrinsics.checkNotNullExpressionValue(cached, "getOrPut(...)");
        return cached;
    }

    public final Pooled<Mac> mac(final String algorithm) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        ConcurrentHashMap<String, Pooled<Mac>> concurrentHashMap = this.macs;
        Pooled.Cached cached = concurrentHashMap.get(algorithm);
        if (cached == null) {
            final Provider provider = this.provider;
            cached = new Pooled.Cached(provider == null ? new Function0<Mac>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$mac$$inlined$get$default$1
                @Override // kotlin.jvm.functions.Function0
                public final Mac invoke() {
                    return Mac.getInstance(algorithm);
                }
            } : new Function0<Mac>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$mac$$inlined$get$default$2
                @Override // kotlin.jvm.functions.Function0
                public final Mac invoke() {
                    return Mac.getInstance(algorithm, provider);
                }
            });
            Pooled<Mac> pooledPutIfAbsent = concurrentHashMap.putIfAbsent(algorithm, cached);
            if (pooledPutIfAbsent != null) {
                cached = pooledPutIfAbsent;
            }
        }
        Intrinsics.checkNotNullExpressionValue(cached, "getOrPut(...)");
        return cached;
    }

    public final Pooled<Signature> signature(final String algorithm) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        ConcurrentHashMap<String, Pooled<Signature>> concurrentHashMap = this.signatures;
        Pooled.Empty empty = concurrentHashMap.get(algorithm);
        if (empty == null) {
            final Provider provider = this.provider;
            empty = new Pooled.Empty(provider == null ? new Function0<Signature>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$signature$$inlined$get$1
                @Override // kotlin.jvm.functions.Function0
                public final Signature invoke() {
                    return Signature.getInstance(algorithm);
                }
            } : new Function0<Signature>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$signature$$inlined$get$2
                @Override // kotlin.jvm.functions.Function0
                public final Signature invoke() {
                    return Signature.getInstance(algorithm, provider);
                }
            });
            Pooled<Signature> pooledPutIfAbsent = concurrentHashMap.putIfAbsent(algorithm, empty);
            if (pooledPutIfAbsent != null) {
                empty = pooledPutIfAbsent;
            }
        }
        Intrinsics.checkNotNullExpressionValue(empty, "getOrPut(...)");
        return empty;
    }

    public final Pooled<KeyGenerator> keyGenerator(final String algorithm) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        ConcurrentHashMap<String, Pooled<KeyGenerator>> concurrentHashMap = this.keyGenerators;
        Pooled.Cached cached = concurrentHashMap.get(algorithm);
        if (cached == null) {
            final Provider provider = this.provider;
            cached = new Pooled.Cached(provider == null ? new Function0<KeyGenerator>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$keyGenerator$$inlined$get$default$1
                @Override // kotlin.jvm.functions.Function0
                public final KeyGenerator invoke() {
                    return KeyGenerator.getInstance(algorithm);
                }
            } : new Function0<KeyGenerator>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$keyGenerator$$inlined$get$default$2
                @Override // kotlin.jvm.functions.Function0
                public final KeyGenerator invoke() {
                    return KeyGenerator.getInstance(algorithm, provider);
                }
            });
            Pooled<KeyGenerator> pooledPutIfAbsent = concurrentHashMap.putIfAbsent(algorithm, cached);
            if (pooledPutIfAbsent != null) {
                cached = pooledPutIfAbsent;
            }
        }
        Intrinsics.checkNotNullExpressionValue(cached, "getOrPut(...)");
        return cached;
    }

    public final Pooled<KeyPairGenerator> keyPairGenerator(final String algorithm) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        ConcurrentHashMap<String, Pooled<KeyPairGenerator>> concurrentHashMap = this.keyPairGenerators;
        Pooled.Cached cached = concurrentHashMap.get(algorithm);
        if (cached == null) {
            final Provider provider = this.provider;
            cached = new Pooled.Cached(provider == null ? new Function0<KeyPairGenerator>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$keyPairGenerator$$inlined$get$default$1
                @Override // kotlin.jvm.functions.Function0
                public final KeyPairGenerator invoke() {
                    return KeyPairGenerator.getInstance(algorithm);
                }
            } : new Function0<KeyPairGenerator>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$keyPairGenerator$$inlined$get$default$2
                @Override // kotlin.jvm.functions.Function0
                public final KeyPairGenerator invoke() {
                    return KeyPairGenerator.getInstance(algorithm, provider);
                }
            });
            Pooled<KeyPairGenerator> pooledPutIfAbsent = concurrentHashMap.putIfAbsent(algorithm, cached);
            if (pooledPutIfAbsent != null) {
                cached = pooledPutIfAbsent;
            }
        }
        Intrinsics.checkNotNullExpressionValue(cached, "getOrPut(...)");
        return cached;
    }

    public final Pooled<KeyFactory> keyFactory(final String algorithm) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        ConcurrentHashMap<String, Pooled<KeyFactory>> concurrentHashMap = this.keyFactories;
        Pooled.Cached cached = concurrentHashMap.get(algorithm);
        if (cached == null) {
            final Provider provider = this.provider;
            cached = new Pooled.Cached(provider == null ? new Function0<KeyFactory>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$keyFactory$$inlined$get$default$1
                @Override // kotlin.jvm.functions.Function0
                public final KeyFactory invoke() {
                    return KeyFactory.getInstance(algorithm);
                }
            } : new Function0<KeyFactory>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$keyFactory$$inlined$get$default$2
                @Override // kotlin.jvm.functions.Function0
                public final KeyFactory invoke() {
                    return KeyFactory.getInstance(algorithm, provider);
                }
            });
            Pooled<KeyFactory> pooledPutIfAbsent = concurrentHashMap.putIfAbsent(algorithm, cached);
            if (pooledPutIfAbsent != null) {
                cached = pooledPutIfAbsent;
            }
        }
        Intrinsics.checkNotNullExpressionValue(cached, "getOrPut(...)");
        return cached;
    }

    public final Pooled<SecretKeyFactory> secretKeyFactory(final String algorithm) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        ConcurrentHashMap<String, Pooled<SecretKeyFactory>> concurrentHashMap = this.secretKeyFactories;
        Pooled.Cached cached = concurrentHashMap.get(algorithm);
        if (cached == null) {
            final Provider provider = this.provider;
            cached = new Pooled.Cached(provider == null ? new Function0<SecretKeyFactory>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$secretKeyFactory$$inlined$get$default$1
                @Override // kotlin.jvm.functions.Function0
                public final SecretKeyFactory invoke() {
                    return SecretKeyFactory.getInstance(algorithm);
                }
            } : new Function0<SecretKeyFactory>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$secretKeyFactory$$inlined$get$default$2
                @Override // kotlin.jvm.functions.Function0
                public final SecretKeyFactory invoke() {
                    return SecretKeyFactory.getInstance(algorithm, provider);
                }
            });
            Pooled<SecretKeyFactory> pooledPutIfAbsent = concurrentHashMap.putIfAbsent(algorithm, cached);
            if (pooledPutIfAbsent != null) {
                cached = pooledPutIfAbsent;
            }
        }
        Intrinsics.checkNotNullExpressionValue(cached, "getOrPut(...)");
        return cached;
    }

    public final AlgorithmParameters algorithmParameters(final String algorithm) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        ConcurrentHashMap<String, Pooled<AlgorithmParameters>> concurrentHashMap = this.algorithmParameters;
        Pooled.Empty empty = concurrentHashMap.get(algorithm);
        if (empty == null) {
            final Provider provider = this.provider;
            empty = new Pooled.Empty(provider == null ? new Function0<AlgorithmParameters>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$algorithmParameters$$inlined$get$1
                @Override // kotlin.jvm.functions.Function0
                public final AlgorithmParameters invoke() {
                    return AlgorithmParameters.getInstance(algorithm);
                }
            } : new Function0<AlgorithmParameters>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$algorithmParameters$$inlined$get$2
                @Override // kotlin.jvm.functions.Function0
                public final AlgorithmParameters invoke() {
                    return AlgorithmParameters.getInstance(algorithm, provider);
                }
            });
            Pooled<AlgorithmParameters> pooledPutIfAbsent = concurrentHashMap.putIfAbsent(algorithm, empty);
            if (pooledPutIfAbsent != null) {
                empty = pooledPutIfAbsent;
            }
        }
        Intrinsics.checkNotNullExpressionValue(empty, "getOrPut(...)");
        Pooled<AlgorithmParameters> pooled = empty;
        Object objBorrow = pooled.borrow();
        try {
            return (AlgorithmParameters) objBorrow;
        } finally {
            pooled.recycle(objBorrow);
        }
    }

    public final Pooled<AlgorithmParameterGenerator> algorithmParameterGenerator(final String algorithm) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        ConcurrentHashMap<String, Pooled<AlgorithmParameterGenerator>> concurrentHashMap = this.algorithmParameterGenerators;
        Pooled.Cached cached = concurrentHashMap.get(algorithm);
        if (cached == null) {
            final Provider provider = this.provider;
            cached = new Pooled.Cached(provider == null ? new Function0<AlgorithmParameterGenerator>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$algorithmParameterGenerator$$inlined$get$default$1
                @Override // kotlin.jvm.functions.Function0
                public final AlgorithmParameterGenerator invoke() {
                    return AlgorithmParameterGenerator.getInstance(algorithm);
                }
            } : new Function0<AlgorithmParameterGenerator>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$algorithmParameterGenerator$$inlined$get$default$2
                @Override // kotlin.jvm.functions.Function0
                public final AlgorithmParameterGenerator invoke() {
                    return AlgorithmParameterGenerator.getInstance(algorithm, provider);
                }
            });
            Pooled<AlgorithmParameterGenerator> pooledPutIfAbsent = concurrentHashMap.putIfAbsent(algorithm, cached);
            if (pooledPutIfAbsent != null) {
                cached = pooledPutIfAbsent;
            }
        }
        Intrinsics.checkNotNullExpressionValue(cached, "getOrPut(...)");
        return cached;
    }

    public final Pooled<KeyAgreement> keyAgreement(final String algorithm) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        ConcurrentHashMap<String, Pooled<KeyAgreement>> concurrentHashMap = this.keyAgreements;
        Pooled.Cached cached = concurrentHashMap.get(algorithm);
        if (cached == null) {
            final Provider provider = this.provider;
            cached = new Pooled.Cached(provider == null ? new Function0<KeyAgreement>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$keyAgreement$$inlined$get$default$1
                @Override // kotlin.jvm.functions.Function0
                public final KeyAgreement invoke() {
                    return KeyAgreement.getInstance(algorithm);
                }
            } : new Function0<KeyAgreement>() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState$keyAgreement$$inlined$get$default$2
                @Override // kotlin.jvm.functions.Function0
                public final KeyAgreement invoke() {
                    return KeyAgreement.getInstance(algorithm, provider);
                }
            });
            Pooled<KeyAgreement> pooledPutIfAbsent = concurrentHashMap.putIfAbsent(algorithm, cached);
            if (pooledPutIfAbsent != null) {
                cached = pooledPutIfAbsent;
            }
        }
        Intrinsics.checkNotNullExpressionValue(cached, "getOrPut(...)");
        return cached;
    }
}
