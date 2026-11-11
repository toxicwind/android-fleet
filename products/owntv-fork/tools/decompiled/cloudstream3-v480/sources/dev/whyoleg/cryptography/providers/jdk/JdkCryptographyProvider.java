package dev.whyoleg.cryptography.providers.jdk;

import androidx.exifinterface.media.ExifInterface;
import dev.whyoleg.cryptography.CryptographyAlgorithm;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.CryptographyProvider;
import dev.whyoleg.cryptography.algorithms.AES;
import dev.whyoleg.cryptography.algorithms.ChaCha20Poly1305;
import dev.whyoleg.cryptography.algorithms.DH;
import dev.whyoleg.cryptography.algorithms.DSA;
import dev.whyoleg.cryptography.algorithms.ECDH;
import dev.whyoleg.cryptography.algorithms.ECDSA;
import dev.whyoleg.cryptography.algorithms.EdDSA;
import dev.whyoleg.cryptography.algorithms.HKDF;
import dev.whyoleg.cryptography.algorithms.HMAC;
import dev.whyoleg.cryptography.algorithms.MD5;
import dev.whyoleg.cryptography.algorithms.PBKDF2;
import dev.whyoleg.cryptography.algorithms.RIPEMD160;
import dev.whyoleg.cryptography.algorithms.RSA;
import dev.whyoleg.cryptography.algorithms.SHA1;
import dev.whyoleg.cryptography.algorithms.SHA224;
import dev.whyoleg.cryptography.algorithms.SHA256;
import dev.whyoleg.cryptography.algorithms.SHA384;
import dev.whyoleg.cryptography.algorithms.SHA3_224;
import dev.whyoleg.cryptography.algorithms.SHA3_256;
import dev.whyoleg.cryptography.algorithms.SHA3_384;
import dev.whyoleg.cryptography.algorithms.SHA3_512;
import dev.whyoleg.cryptography.algorithms.SHA512;
import dev.whyoleg.cryptography.algorithms.XDH;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkAesCbc;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkAesCcm;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkAesCfb;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkAesCfb8;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkAesCmac;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkAesCtr;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkAesEcb;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkAesGcm;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkAesOfb;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkChaCha20Poly1305;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkDh;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkDigest;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkDsa;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkEcdh;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkEcdsa;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkEdDsa;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkHkdf;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkHmac;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkPbkdf2;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsaOaep;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsaPkcs1;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsaPss;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsaRaw;
import dev.whyoleg.cryptography.providers.jdk.algorithms.JdkXdh;
import j$.util.concurrent.ConcurrentHashMap;
import java.security.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkCryptographyProvider.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J)\u0010\n\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0007*\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R&\u0010\u0015\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyProvider;", "Ldev/whyoleg/cryptography/CryptographyProvider;", "Ljava/security/Provider;", "provider", "<init>", "(Ljava/security/Provider;)V", "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "identifier", "getOrNull", "(Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "j$/util/concurrent/ConcurrentHashMap", "cache", "Lj$/util/concurrent/ConcurrentHashMap;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkCryptographyProvider extends CryptographyProvider {
    private final ConcurrentHashMap<CryptographyAlgorithmId<?>, CryptographyAlgorithm> cache;
    private final String name;
    private final JdkCryptographyState state;

    public JdkCryptographyProvider(Provider provider) {
        String str;
        this.state = new JdkCryptographyState(provider);
        if (provider == null) {
            str = "JDK";
        } else {
            str = "JDK (" + provider.getName() + ')';
        }
        this.name = str;
        this.cache = new ConcurrentHashMap<>();
    }

    @Override // dev.whyoleg.cryptography.CryptographyProvider
    public String getName() {
        return this.name;
    }

    @Override // dev.whyoleg.cryptography.CryptographyProvider
    public <A extends CryptographyAlgorithm> A getOrNull(CryptographyAlgorithmId<A> identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        ConcurrentHashMap<CryptographyAlgorithmId<?>, CryptographyAlgorithm> concurrentHashMap = this.cache;
        JdkHkdf jdkHkdf = concurrentHashMap.get(identifier);
        if (jdkHkdf == null) {
            if (Intrinsics.areEqual(identifier, MD5.INSTANCE)) {
                jdkHkdf = new JdkDigest(this.state, "MD5", MD5.INSTANCE);
            } else if (Intrinsics.areEqual(identifier, SHA1.INSTANCE)) {
                jdkHkdf = new JdkDigest(this.state, "SHA-1", SHA1.INSTANCE);
            } else if (Intrinsics.areEqual(identifier, SHA224.INSTANCE)) {
                jdkHkdf = new JdkDigest(this.state, "SHA-224", SHA224.INSTANCE);
            } else if (Intrinsics.areEqual(identifier, SHA256.INSTANCE)) {
                jdkHkdf = new JdkDigest(this.state, "SHA-256", SHA256.INSTANCE);
            } else if (Intrinsics.areEqual(identifier, SHA384.INSTANCE)) {
                jdkHkdf = new JdkDigest(this.state, "SHA-384", SHA384.INSTANCE);
            } else if (Intrinsics.areEqual(identifier, SHA512.INSTANCE)) {
                jdkHkdf = new JdkDigest(this.state, "SHA-512", SHA512.INSTANCE);
            } else if (Intrinsics.areEqual(identifier, SHA3_224.INSTANCE)) {
                jdkHkdf = new JdkDigest(this.state, "SHA3-224", SHA3_224.INSTANCE);
            } else if (Intrinsics.areEqual(identifier, SHA3_256.INSTANCE)) {
                jdkHkdf = new JdkDigest(this.state, "SHA3-256", SHA3_256.INSTANCE);
            } else if (Intrinsics.areEqual(identifier, SHA3_384.INSTANCE)) {
                jdkHkdf = new JdkDigest(this.state, "SHA3-384", SHA3_384.INSTANCE);
            } else if (Intrinsics.areEqual(identifier, SHA3_512.INSTANCE)) {
                jdkHkdf = new JdkDigest(this.state, "SHA3-512", SHA3_512.INSTANCE);
            } else if (Intrinsics.areEqual(identifier, RIPEMD160.INSTANCE)) {
                jdkHkdf = new JdkDigest(this.state, "RIPEMD160", RIPEMD160.INSTANCE);
            } else if (Intrinsics.areEqual(identifier, HMAC.INSTANCE)) {
                jdkHkdf = new JdkHmac(this.state);
            } else if (Intrinsics.areEqual(identifier, AES.CBC.INSTANCE)) {
                jdkHkdf = new JdkAesCbc(this.state);
            } else if (Intrinsics.areEqual(identifier, AES.CFB8.INSTANCE)) {
                jdkHkdf = new JdkAesCfb8(this.state);
            } else if (Intrinsics.areEqual(identifier, AES.CMAC.INSTANCE)) {
                jdkHkdf = new JdkAesCmac(this.state);
            } else if (Intrinsics.areEqual(identifier, AES.CTR.INSTANCE)) {
                jdkHkdf = new JdkAesCtr(this.state);
            } else if (Intrinsics.areEqual(identifier, AES.CFB.INSTANCE)) {
                jdkHkdf = new JdkAesCfb(this.state);
            } else if (Intrinsics.areEqual(identifier, AES.OFB.INSTANCE)) {
                jdkHkdf = new JdkAesOfb(this.state);
            } else if (Intrinsics.areEqual(identifier, AES.ECB.INSTANCE)) {
                jdkHkdf = new JdkAesEcb(this.state);
            } else if (Intrinsics.areEqual(identifier, AES.GCM.INSTANCE)) {
                jdkHkdf = new JdkAesGcm(this.state);
            } else if (Intrinsics.areEqual(identifier, AES.CCM.INSTANCE)) {
                jdkHkdf = new JdkAesCcm(this.state);
            } else if (Intrinsics.areEqual(identifier, ChaCha20Poly1305.INSTANCE)) {
                jdkHkdf = new JdkChaCha20Poly1305(this.state);
            } else if (Intrinsics.areEqual(identifier, RSA.OAEP.INSTANCE)) {
                jdkHkdf = new JdkRsaOaep(this.state);
            } else if (Intrinsics.areEqual(identifier, RSA.PSS.INSTANCE)) {
                jdkHkdf = new JdkRsaPss(this.state);
            } else if (Intrinsics.areEqual(identifier, RSA.PKCS1.INSTANCE)) {
                jdkHkdf = new JdkRsaPkcs1(this.state);
            } else if (Intrinsics.areEqual(identifier, RSA.RAW.INSTANCE)) {
                jdkHkdf = new JdkRsaRaw(this.state);
            } else if (Intrinsics.areEqual(identifier, ECDSA.INSTANCE)) {
                jdkHkdf = new JdkEcdsa(this.state);
            } else if (Intrinsics.areEqual(identifier, DSA.INSTANCE)) {
                jdkHkdf = new JdkDsa(this.state);
            } else if (Intrinsics.areEqual(identifier, EdDSA.INSTANCE)) {
                jdkHkdf = new JdkEdDsa(this.state);
            } else if (Intrinsics.areEqual(identifier, ECDH.INSTANCE)) {
                jdkHkdf = new JdkEcdh(this.state);
            } else if (Intrinsics.areEqual(identifier, XDH.INSTANCE)) {
                jdkHkdf = new JdkXdh(this.state);
            } else if (Intrinsics.areEqual(identifier, DH.INSTANCE)) {
                jdkHkdf = new JdkDh(this.state);
            } else if (Intrinsics.areEqual(identifier, PBKDF2.INSTANCE)) {
                jdkHkdf = new JdkPbkdf2(this.state);
            } else {
                jdkHkdf = Intrinsics.areEqual(identifier, HKDF.INSTANCE) ? new JdkHkdf(this.state, this) : null;
            }
            CryptographyAlgorithm cryptographyAlgorithmPutIfAbsent = concurrentHashMap.putIfAbsent(identifier, jdkHkdf);
            if (cryptographyAlgorithmPutIfAbsent != null) {
                jdkHkdf = cryptographyAlgorithmPutIfAbsent;
            }
        }
        return (A) jdkHkdf;
    }
}
