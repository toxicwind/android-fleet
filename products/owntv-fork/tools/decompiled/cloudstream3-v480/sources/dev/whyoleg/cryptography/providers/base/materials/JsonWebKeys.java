package dev.whyoleg.cryptography.providers.base.materials;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.algorithms.AES;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.RSA;
import dev.whyoleg.cryptography.algorithms.SHA1;
import dev.whyoleg.cryptography.algorithms.SHA256;
import dev.whyoleg.cryptography.algorithms.SHA384;
import dev.whyoleg.cryptography.algorithms.SHA512;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.encoding.Base64;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import kotlinx.serialization.json.JsonPrimitive;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: jwk.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bÇ\u0002\u0018\u00002\u00020\u0001:\u0004\\]^_B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0086\u0080\u0004J\u001e\u0010\t\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\n\u001a\u00020\u0005H\u0086\u0080\u0004J'\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0082\u0080\u0004¢\u0006\u0002\u0010\u000fJ \u0010\u0010\u001a\u00020\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0086\u0080\u0004J \u0010\u0013\u001a\u00020\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00072\u0006\u0010\n\u001a\u00020\u0005H\u0086\u0080\u0004J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007H\u0082\u0080\u0004J)\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0005H\u0086\u0080\u0004¢\u0006\u0004\b\u001a\u0010\u001bJ1\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0086\u0080\u0004¢\u0006\u0004\b\u001e\u0010\u001fJ)\u0010 \u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u0005H\u0086\u0080\u0004¢\u0006\u0004\b!\u0010\u001bJ)\u0010\"\u001a\u00020#2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u0005H\u0086\u0080\u0004¢\u0006\u0004\b$\u0010%J3\u0010&\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00052\b\u0010'\u001a\u0004\u0018\u00010\u0005H\u0082\u0080\u0004¢\u0006\u0004\b(\u0010\u001fJi\u0010)\u001a\u0002H*\"\u0004\b\u0000\u0010*2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000528\u0010+\u001a4\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b('\u0012\u0004\u0012\u0002H*0,H\u0082\u0088\u0004¢\u0006\u0004\b/\u00100J\u001a\u00101\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0005H\u0086\u0080\u0004J\u001a\u00102\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u0005H\u0086\u0080\u0004J\"\u00103\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0086\u0080\u0004J\u001a\u00104\u001a\u0002052\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u0005H\u0086\u0080\u0004J6\u00106\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00072\u0006\u00107\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u0005H\u0086\u0080\u0004Jf\u00109\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00072\u0006\u00107\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u00052\u0006\u0010:\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\u00052\u0006\u0010<\u001a\u00020\u00052\u0006\u0010=\u001a\u00020\u00052\u0006\u0010>\u001a\u00020\u0005H\u0086\u0080\u0004J.\u0010?\u001a\u00020@2\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u0005H\u0086\u0080\u0004J.\u0010A\u001a\u00020B2\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u0005H\u0086\u0080\u0004J(\u0010C\u001a\u0004\u0018\u00010\f2\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0007H\u0082\u0080\u0004J\u001a\u0010D\u001a\u00020E2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\fH\u0082\u0080\u0004J\"\u0010G\u001a\u00020E2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\f2\u0006\u0010H\u001a\u00020\fH\u0082\u0080\u0004J$\u0010I\u001a\u00020E2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\f2\b\u0010J\u001a\u0004\u0018\u00010\fH\u0082\u0080\u0004J\u001c\u0010K\u001a\u00020\u00052\b\u0010L\u001a\u0004\u0018\u00010\f2\u0006\u0010\b\u001a\u00020\u0005H\u0082\u0080\u0004J\u001c\u0010M\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\b\u0010J\u001a\u0004\u0018\u00010\fH\u0082\u0080\u0004J5\u0010N\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\f2\b\u0010L\u001a\u0004\u0018\u00010\f2\u0017\u0010P\u001a\u0013\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020S0Q¢\u0006\u0002\bTH\u0082\u0088\u0004J \u0010W\u001a\u00020S*\u00020R2\u0006\u0010X\u001a\u00020\f2\b\u0010Y\u001a\u0004\u0018\u00010\u0005H\u0082\u0080\u0004J\u0018\u0010Z\u001a\u0004\u0018\u00010\u0005*\u00020E2\u0006\u0010X\u001a\u00020\fH\u0082\u0080\u0004J\u0018\u0010[\u001a\u0004\u0018\u00010\f*\u00020E2\u0006\u0010X\u001a\u00020\fH\u0082\u0080\u0004R\u000f\u0010U\u001a\u00020VX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006`"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/materials/JsonWebKeys;", "", "<init>", "()V", "encodeSymmetricKey", "", "algorithmId", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "rawKey", "decodeSymmetricKey", "jwkKey", "symmetricKeyJwkAlgorithm", "", "keySizeBytes", "", "(Ldev/whyoleg/cryptography/CryptographyAlgorithmId;Ljava/lang/Integer;)Ljava/lang/String;", "encodeHmacKey", "digest", "Ldev/whyoleg/cryptography/algorithms/Digest;", "decodeHmacKey", "hmacJwkAlgorithm", "encodeEcPublicKey", "curve", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "orderSize", "publicKey", "encodeEcPublicKey-XWxlsIE", "(Ljava/lang/String;I[B)[B", "encodeEcPrivateKey", "privateKey", "encodeEcPrivateKey-DFwOJLc", "(Ljava/lang/String;I[B[B)[B", "decodeEcPublicKey", "decodeEcPublicKey-XWxlsIE", "decodeEcPrivateKey", "Ldev/whyoleg/cryptography/providers/base/materials/JsonWebKeys$EcPrivateKeyComponents;", "decodeEcPrivateKey-XWxlsIE", "(Ljava/lang/String;I[B)Ldev/whyoleg/cryptography/providers/base/materials/JsonWebKeys$EcPrivateKeyComponents;", "encodeEcKey", "d", "encodeEcKey-DFwOJLc", "decodeEcKey", ExifInterface.GPS_DIRECTION_TRUE, "transform", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "decodeEcKey-DFwOJLc", "(Ljava/lang/String;I[BLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "encodeOkpPublicKey", "decodeOkpPublicKey", "encodeOkpPrivateKey", "decodeOkpPrivateKey", "Ldev/whyoleg/cryptography/providers/base/materials/JsonWebKeys$OkpPrivateKeyComponents;", "encodeRsaPublicKey", "n", "e", "encodeRsaPrivateKey", TtmlNode.TAG_P, "q", "dp", "dq", "qi", "decodeRsaPublicKey", "Ldev/whyoleg/cryptography/providers/base/materials/JsonWebKeys$RsaPublicKeyComponents;", "decodeRsaPrivateKey", "Ldev/whyoleg/cryptography/providers/base/materials/JsonWebKeys$RsaPrivateKeyComponents;", "rsaJwkAlgorithm", "parseJwk", "Lkotlinx/serialization/json/JsonObject;", "expectedKty", "parseJwkWithCurve", "expectedCurve", "parseJwkWithAlgorithm", "expectedAlg", "encodeOctKey", "alg", "decodeOctKey", "jwk", "kty", "builderAction", "Lkotlin/Function1;", "Lkotlinx/serialization/json/JsonObjectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "base64Url", "Lkotlin/io/encoding/Base64;", "put", "key", ES6Iterator.VALUE_PROPERTY, "getByteArray", "getString", "EcPrivateKeyComponents", "OkpPrivateKeyComponents", "RsaPublicKeyComponents", "RsaPrivateKeyComponents", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JsonWebKeys {
    public static final JsonWebKeys INSTANCE = new JsonWebKeys();
    private static final Base64 base64Url = Base64.INSTANCE.getUrlSafe().withPadding(Base64.PaddingOption.ABSENT_OPTIONAL);

    private JsonWebKeys() {
    }

    public final byte[] encodeSymmetricKey(CryptographyAlgorithmId<?> algorithmId, byte[] rawKey) {
        Intrinsics.checkNotNullParameter(algorithmId, "algorithmId");
        Intrinsics.checkNotNullParameter(rawKey, "rawKey");
        return encodeOctKey(symmetricKeyJwkAlgorithm(algorithmId, Integer.valueOf(rawKey.length)), rawKey);
    }

    public final byte[] decodeSymmetricKey(CryptographyAlgorithmId<?> algorithmId, byte[] jwkKey) {
        Intrinsics.checkNotNullParameter(algorithmId, "algorithmId");
        Intrinsics.checkNotNullParameter(jwkKey, "jwkKey");
        return decodeOctKey(jwkKey, symmetricKeyJwkAlgorithm(algorithmId, null));
    }

    private final String symmetricKeyJwkAlgorithm(CryptographyAlgorithmId<?> algorithmId, Integer keySizeBytes) {
        String str;
        String str2;
        if (Intrinsics.areEqual(algorithmId, AES.GCM.INSTANCE)) {
            str = "GCM";
        } else if (Intrinsics.areEqual(algorithmId, AES.CBC.INSTANCE)) {
            str = "CBC";
        } else {
            if (!Intrinsics.areEqual(algorithmId, AES.CTR.INSTANCE)) {
                return null;
            }
            str = "CTR";
        }
        if (keySizeBytes == null) {
            return null;
        }
        int iIntValue = keySizeBytes.intValue();
        if (iIntValue == 16) {
            str2 = "A128";
        } else if (iIntValue == 24) {
            str2 = "A192";
        } else {
            if (iIntValue != 32) {
                return null;
            }
            str2 = "A256";
        }
        return str2.concat(str);
    }

    public final byte[] encodeHmacKey(CryptographyAlgorithmId<Digest> digest, byte[] rawKey) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        Intrinsics.checkNotNullParameter(rawKey, "rawKey");
        return encodeOctKey(hmacJwkAlgorithm(digest), rawKey);
    }

    public final byte[] decodeHmacKey(CryptographyAlgorithmId<Digest> digest, byte[] jwkKey) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        Intrinsics.checkNotNullParameter(jwkKey, "jwkKey");
        return decodeOctKey(jwkKey, hmacJwkAlgorithm(digest));
    }

    private final String hmacJwkAlgorithm(CryptographyAlgorithmId<Digest> digest) {
        if (Intrinsics.areEqual(digest, SHA256.INSTANCE)) {
            return "HS256";
        }
        if (Intrinsics.areEqual(digest, SHA384.INSTANCE)) {
            return "HS384";
        }
        if (Intrinsics.areEqual(digest, SHA512.INSTANCE)) {
            return "HS512";
        }
        return null;
    }

    /* JADX INFO: renamed from: encodeEcPublicKey-XWxlsIE, reason: not valid java name */
    public final byte[] m8742encodeEcPublicKeyXWxlsIE(String curve, int orderSize, byte[] publicKey) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        return m8738encodeEcKeyDFwOJLc(curve, orderSize, publicKey, null);
    }

    /* JADX INFO: renamed from: encodeEcPrivateKey-DFwOJLc, reason: not valid java name */
    public final byte[] m8741encodeEcPrivateKeyDFwOJLc(String curve, int orderSize, byte[] publicKey, byte[] privateKey) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        return m8738encodeEcKeyDFwOJLc(curve, orderSize, publicKey, privateKey);
    }

    /* JADX INFO: compiled from: jwk.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/materials/JsonWebKeys$EcPrivateKeyComponents;", "", "publicKey", "", "privateKey", "<init>", "([B[B)V", "getPublicKey", "()[B", "getPrivateKey", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class EcPrivateKeyComponents {
        private final byte[] privateKey;
        private final byte[] publicKey;

        public EcPrivateKeyComponents(byte[] publicKey, byte[] privateKey) {
            Intrinsics.checkNotNullParameter(publicKey, "publicKey");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public final byte[] getPublicKey() {
            return this.publicKey;
        }

        public final byte[] getPrivateKey() {
            return this.privateKey;
        }
    }

    /* JADX INFO: renamed from: encodeEcKey-DFwOJLc, reason: not valid java name */
    private final byte[] m8738encodeEcKeyDFwOJLc(String curve, int orderSize, byte[] publicKey, byte[] d) {
        if (publicKey[0] != 4) {
            throw new IllegalStateException("Expected uncompressed public key".toString());
        }
        int i = orderSize + 1;
        byte[] bArrCopyOfRange = ArraysKt.copyOfRange(publicKey, 1, i);
        byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(publicKey, i, (orderSize * 2) + 1);
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "kty", "EC");
        JsonElementBuildersKt.put(jsonObjectBuilder, "crv", curve);
        JsonWebKeys jsonWebKeys = INSTANCE;
        jsonWebKeys.put(jsonObjectBuilder, "x", bArrCopyOfRange);
        jsonWebKeys.put(jsonObjectBuilder, "y", bArrCopyOfRange2);
        if (d != null) {
            jsonWebKeys.put(jsonObjectBuilder, "d", d);
        }
        return StringsKt.encodeToByteArray(jsonObjectBuilder.build().toString());
    }

    /* JADX INFO: renamed from: decodeEcKey-DFwOJLc, reason: not valid java name */
    private final <T> T m8737decodeEcKeyDFwOJLc(String curve, int orderSize, byte[] jwkKey, Function2<? super byte[], ? super byte[], ? extends T> transform) {
        JsonObject jwkWithCurve = parseJwkWithCurve(jwkKey, "EC", curve);
        byte[] byteArray = getByteArray(jwkWithCurve, "x");
        if (byteArray == null) {
            throw new IllegalArgumentException("'x' is required".toString());
        }
        byte[] byteArray2 = getByteArray(jwkWithCurve, "y");
        if (byteArray2 == null) {
            throw new IllegalArgumentException("'y' is required".toString());
        }
        byte[] bArr = new byte[(orderSize * 2) + 1];
        bArr[0] = 4;
        ArraysKt.copyInto$default(byteArray, bArr, 1, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(byteArray2, bArr, orderSize + 1, 0, 0, 12, (Object) null);
        return transform.invoke(bArr, getByteArray(jwkWithCurve, "d"));
    }

    public final byte[] decodeOkpPublicKey(String curve, byte[] jwkKey) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        Intrinsics.checkNotNullParameter(jwkKey, "jwkKey");
        byte[] byteArray = getByteArray(parseJwkWithCurve(jwkKey, "OKP", curve), "x");
        if (byteArray != null) {
            return byteArray;
        }
        throw new IllegalArgumentException("'x' is required".toString());
    }

    public final OkpPrivateKeyComponents decodeOkpPrivateKey(String curve, byte[] jwkKey) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        Intrinsics.checkNotNullParameter(jwkKey, "jwkKey");
        JsonObject jwkWithCurve = parseJwkWithCurve(jwkKey, "OKP", curve);
        byte[] byteArray = getByteArray(jwkWithCurve, "x");
        if (byteArray == null) {
            throw new IllegalArgumentException("'x' is required".toString());
        }
        byte[] byteArray2 = getByteArray(jwkWithCurve, "d");
        if (byteArray2 != null) {
            return new OkpPrivateKeyComponents(byteArray, byteArray2);
        }
        throw new IllegalArgumentException("'d' is required for private key".toString());
    }

    /* JADX INFO: compiled from: jwk.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/materials/JsonWebKeys$OkpPrivateKeyComponents;", "", "publicKey", "", "privateKey", "<init>", "([B[B)V", "getPublicKey", "()[B", "getPrivateKey", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class OkpPrivateKeyComponents {
        private final byte[] privateKey;
        private final byte[] publicKey;

        public OkpPrivateKeyComponents(byte[] publicKey, byte[] privateKey) {
            Intrinsics.checkNotNullParameter(publicKey, "publicKey");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public final byte[] getPublicKey() {
            return this.publicKey;
        }

        public final byte[] getPrivateKey() {
            return this.privateKey;
        }
    }

    public final byte[] encodeRsaPublicKey(CryptographyAlgorithmId<?> algorithmId, CryptographyAlgorithmId<Digest> digest, byte[] n, byte[] e) {
        Intrinsics.checkNotNullParameter(algorithmId, "algorithmId");
        Intrinsics.checkNotNullParameter(n, "n");
        Intrinsics.checkNotNullParameter(e, "e");
        String strRsaJwkAlgorithm = rsaJwkAlgorithm(algorithmId, digest);
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "kty", "RSA");
        if (strRsaJwkAlgorithm != null) {
            JsonElementBuildersKt.put(jsonObjectBuilder, "alg", strRsaJwkAlgorithm);
        }
        JsonWebKeys jsonWebKeys = INSTANCE;
        jsonWebKeys.put(jsonObjectBuilder, "n", n);
        jsonWebKeys.put(jsonObjectBuilder, "e", e);
        return StringsKt.encodeToByteArray(jsonObjectBuilder.build().toString());
    }

    public final byte[] encodeRsaPrivateKey(CryptographyAlgorithmId<?> algorithmId, CryptographyAlgorithmId<Digest> digest, byte[] n, byte[] e, byte[] d, byte[] p, byte[] q, byte[] dp, byte[] dq, byte[] qi) {
        Intrinsics.checkNotNullParameter(algorithmId, "algorithmId");
        Intrinsics.checkNotNullParameter(n, "n");
        Intrinsics.checkNotNullParameter(e, "e");
        Intrinsics.checkNotNullParameter(d, "d");
        Intrinsics.checkNotNullParameter(p, "p");
        Intrinsics.checkNotNullParameter(q, "q");
        Intrinsics.checkNotNullParameter(dp, "dp");
        Intrinsics.checkNotNullParameter(dq, "dq");
        Intrinsics.checkNotNullParameter(qi, "qi");
        String strRsaJwkAlgorithm = rsaJwkAlgorithm(algorithmId, digest);
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "kty", "RSA");
        if (strRsaJwkAlgorithm != null) {
            JsonElementBuildersKt.put(jsonObjectBuilder, "alg", strRsaJwkAlgorithm);
        }
        JsonWebKeys jsonWebKeys = INSTANCE;
        jsonWebKeys.put(jsonObjectBuilder, "n", n);
        jsonWebKeys.put(jsonObjectBuilder, "e", e);
        jsonWebKeys.put(jsonObjectBuilder, "d", d);
        jsonWebKeys.put(jsonObjectBuilder, TtmlNode.TAG_P, p);
        jsonWebKeys.put(jsonObjectBuilder, "q", q);
        jsonWebKeys.put(jsonObjectBuilder, "dp", dp);
        jsonWebKeys.put(jsonObjectBuilder, "dq", dq);
        jsonWebKeys.put(jsonObjectBuilder, "qi", qi);
        return StringsKt.encodeToByteArray(jsonObjectBuilder.build().toString());
    }

    public final RsaPublicKeyComponents decodeRsaPublicKey(CryptographyAlgorithmId<?> algorithmId, CryptographyAlgorithmId<Digest> digest, byte[] jwkKey) {
        Intrinsics.checkNotNullParameter(algorithmId, "algorithmId");
        Intrinsics.checkNotNullParameter(jwkKey, "jwkKey");
        JsonObject jwkWithAlgorithm = parseJwkWithAlgorithm(jwkKey, "RSA", rsaJwkAlgorithm(algorithmId, digest));
        byte[] byteArray = getByteArray(jwkWithAlgorithm, "n");
        if (byteArray == null) {
            throw new IllegalArgumentException("'n' is required".toString());
        }
        byte[] byteArray2 = getByteArray(jwkWithAlgorithm, "e");
        if (byteArray2 != null) {
            return new RsaPublicKeyComponents(byteArray, byteArray2);
        }
        throw new IllegalArgumentException("'e' is required".toString());
    }

    public final RsaPrivateKeyComponents decodeRsaPrivateKey(CryptographyAlgorithmId<?> algorithmId, CryptographyAlgorithmId<Digest> digest, byte[] jwkKey) {
        Intrinsics.checkNotNullParameter(algorithmId, "algorithmId");
        Intrinsics.checkNotNullParameter(jwkKey, "jwkKey");
        JsonObject jwkWithAlgorithm = parseJwkWithAlgorithm(jwkKey, "RSA", rsaJwkAlgorithm(algorithmId, digest));
        byte[] byteArray = getByteArray(jwkWithAlgorithm, "n");
        if (byteArray == null) {
            throw new IllegalArgumentException("'n' is required".toString());
        }
        byte[] byteArray2 = getByteArray(jwkWithAlgorithm, "e");
        if (byteArray2 == null) {
            throw new IllegalArgumentException("'e' is required".toString());
        }
        byte[] byteArray3 = getByteArray(jwkWithAlgorithm, "d");
        if (byteArray3 == null) {
            throw new IllegalArgumentException("'d' is required".toString());
        }
        byte[] byteArray4 = getByteArray(jwkWithAlgorithm, TtmlNode.TAG_P);
        if (byteArray4 == null) {
            throw new IllegalArgumentException("'p' is required".toString());
        }
        byte[] byteArray5 = getByteArray(jwkWithAlgorithm, "q");
        if (byteArray5 == null) {
            throw new IllegalArgumentException("'q' is required".toString());
        }
        byte[] byteArray6 = getByteArray(jwkWithAlgorithm, "dp");
        if (byteArray6 == null) {
            throw new IllegalArgumentException("'dp' is required".toString());
        }
        byte[] byteArray7 = getByteArray(jwkWithAlgorithm, "dq");
        if (byteArray7 == null) {
            throw new IllegalArgumentException("'dq' is required".toString());
        }
        byte[] byteArray8 = getByteArray(jwkWithAlgorithm, "qi");
        if (byteArray8 != null) {
            return new RsaPrivateKeyComponents(byteArray, byteArray2, byteArray3, byteArray4, byteArray5, byteArray6, byteArray7, byteArray8);
        }
        throw new IllegalArgumentException("'qi' is required".toString());
    }

    /* JADX INFO: compiled from: jwk.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/materials/JsonWebKeys$RsaPublicKeyComponents;", "", "n", "", "e", "<init>", "([B[B)V", "getN", "()[B", "getE", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class RsaPublicKeyComponents {
        private final byte[] e;
        private final byte[] n;

        public RsaPublicKeyComponents(byte[] n, byte[] e) {
            Intrinsics.checkNotNullParameter(n, "n");
            Intrinsics.checkNotNullParameter(e, "e");
            this.n = n;
            this.e = e;
        }

        public final byte[] getN() {
            return this.n;
        }

        public final byte[] getE() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: jwk.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0013\u0018\u00002\u00020\u0001BI\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fR\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0004\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0015\u0010\u0005\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0015\u0010\u0006\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0015\u0010\u0007\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0015\u0010\b\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0015\u0010\t\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0015\u0010\n\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006\u0016"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/materials/JsonWebKeys$RsaPrivateKeyComponents;", "", "n", "", "e", "d", TtmlNode.TAG_P, "q", "dp", "dq", "qi", "<init>", "([B[B[B[B[B[B[B[B)V", "getN", "()[B", "getE", "getD", "getP", "getQ", "getDp", "getDq", "getQi", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class RsaPrivateKeyComponents {
        private final byte[] d;
        private final byte[] dp;
        private final byte[] dq;
        private final byte[] e;
        private final byte[] n;
        private final byte[] p;
        private final byte[] q;
        private final byte[] qi;

        public RsaPrivateKeyComponents(byte[] n, byte[] e, byte[] d, byte[] p, byte[] q, byte[] dp, byte[] dq, byte[] qi) {
            Intrinsics.checkNotNullParameter(n, "n");
            Intrinsics.checkNotNullParameter(e, "e");
            Intrinsics.checkNotNullParameter(d, "d");
            Intrinsics.checkNotNullParameter(p, "p");
            Intrinsics.checkNotNullParameter(q, "q");
            Intrinsics.checkNotNullParameter(dp, "dp");
            Intrinsics.checkNotNullParameter(dq, "dq");
            Intrinsics.checkNotNullParameter(qi, "qi");
            this.n = n;
            this.e = e;
            this.d = d;
            this.p = p;
            this.q = q;
            this.dp = dp;
            this.dq = dq;
            this.qi = qi;
        }

        public final byte[] getN() {
            return this.n;
        }

        public final byte[] getE() {
            return this.e;
        }

        public final byte[] getD() {
            return this.d;
        }

        public final byte[] getP() {
            return this.p;
        }

        public final byte[] getQ() {
            return this.q;
        }

        public final byte[] getDp() {
            return this.dp;
        }

        public final byte[] getDq() {
            return this.dq;
        }

        public final byte[] getQi() {
            return this.qi;
        }
    }

    private final String rsaJwkAlgorithm(CryptographyAlgorithmId<?> algorithmId, CryptographyAlgorithmId<Digest> digest) {
        if (Intrinsics.areEqual(algorithmId, RSA.OAEP.INSTANCE)) {
            if (Intrinsics.areEqual(digest, SHA1.INSTANCE)) {
                return "RSA-OAEP";
            }
            if (Intrinsics.areEqual(digest, SHA256.INSTANCE)) {
                return "RSA-OAEP-256";
            }
            if (Intrinsics.areEqual(digest, SHA384.INSTANCE)) {
                return "RSA-OAEP-384";
            }
            if (Intrinsics.areEqual(digest, SHA512.INSTANCE)) {
                return "RSA-OAEP-512";
            }
            return null;
        }
        if (Intrinsics.areEqual(algorithmId, RSA.PSS.INSTANCE)) {
            if (Intrinsics.areEqual(digest, SHA256.INSTANCE)) {
                return "PS256";
            }
            if (Intrinsics.areEqual(digest, SHA384.INSTANCE)) {
                return "PS384";
            }
            if (Intrinsics.areEqual(digest, SHA512.INSTANCE)) {
                return "PS512";
            }
            return null;
        }
        if (Intrinsics.areEqual(algorithmId, RSA.PKCS1.INSTANCE)) {
            if (Intrinsics.areEqual(digest, SHA256.INSTANCE)) {
                return "RS256";
            }
            if (Intrinsics.areEqual(digest, SHA384.INSTANCE)) {
                return "RS384";
            }
            if (Intrinsics.areEqual(digest, SHA512.INSTANCE)) {
                return "RS512";
            }
        }
        return null;
    }

    private final JsonObject parseJwk(byte[] jwkKey, String expectedKty) {
        JsonObject jsonObject = (JsonObject) Json.INSTANCE.decodeFromString(JsonObject.INSTANCE.serializer(), StringsKt.decodeToString(jwkKey));
        if (Intrinsics.areEqual(getString(jsonObject, "kty"), expectedKty)) {
            return jsonObject;
        }
        throw new IllegalArgumentException(("'kty' should be '" + expectedKty + '\'').toString());
    }

    private final JsonObject parseJwkWithCurve(byte[] jwkKey, String expectedKty, String expectedCurve) {
        JsonObject jwk = parseJwk(jwkKey, expectedKty);
        if (Intrinsics.areEqual(getString(jwk, "crv"), expectedCurve)) {
            return jwk;
        }
        throw new IllegalArgumentException(("'crv' should be '" + expectedCurve + '\'').toString());
    }

    private final JsonObject parseJwkWithAlgorithm(byte[] jwkKey, String expectedKty, String expectedAlg) {
        String string;
        JsonObject jwk = parseJwk(jwkKey, expectedKty);
        if (expectedAlg == null || (string = getString(jwk, "alg")) == null || Intrinsics.areEqual(string, expectedAlg)) {
            return jwk;
        }
        throw new IllegalArgumentException(("Wrong 'alg': expected '" + expectedAlg + "', actual '" + string + '\'').toString());
    }

    private final byte[] decodeOctKey(byte[] jwkKey, String expectedAlg) {
        String string;
        JsonObject jwk = parseJwk(jwkKey, "oct");
        byte[] byteArray = getByteArray(jwk, "k");
        if (byteArray == null) {
            throw new IllegalArgumentException("'k' was not found".toString());
        }
        if (expectedAlg == null || (string = getString(jwk, "alg")) == null || Intrinsics.areEqual(string, expectedAlg)) {
            return byteArray;
        }
        throw new IllegalArgumentException(("Wrong 'alg': expected '" + expectedAlg + "', actual '" + string + '\'').toString());
    }

    private final void put(JsonObjectBuilder jsonObjectBuilder, String str, byte[] bArr) {
        JsonElementBuildersKt.put(jsonObjectBuilder, str, bArr != null ? Base64.encode$default(base64Url, bArr, 0, 0, 6, null) : null);
    }

    private final byte[] getByteArray(JsonObject jsonObject, String str) {
        JsonPrimitive jsonPrimitive;
        String contentOrNull;
        JsonElement jsonElement = (JsonElement) jsonObject.get((Object) str);
        if (jsonElement == null || (jsonPrimitive = JsonElementKt.getJsonPrimitive(jsonElement)) == null || (contentOrNull = JsonElementKt.getContentOrNull(jsonPrimitive)) == null) {
            return null;
        }
        return Base64.decode$default(base64Url, contentOrNull, 0, 0, 6, (Object) null);
    }

    private final String getString(JsonObject jsonObject, String str) {
        JsonPrimitive jsonPrimitive;
        JsonElement jsonElement = (JsonElement) jsonObject.get((Object) str);
        if (jsonElement == null || (jsonPrimitive = JsonElementKt.getJsonPrimitive(jsonElement)) == null) {
            return null;
        }
        return JsonElementKt.getContentOrNull(jsonPrimitive);
    }

    /* JADX INFO: renamed from: decodeEcPublicKey-XWxlsIE, reason: not valid java name */
    public final byte[] m8740decodeEcPublicKeyXWxlsIE(String curve, int orderSize, byte[] jwkKey) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        Intrinsics.checkNotNullParameter(jwkKey, "jwkKey");
        JsonObject jwkWithCurve = parseJwkWithCurve(jwkKey, "EC", curve);
        byte[] byteArray = getByteArray(jwkWithCurve, "x");
        if (byteArray == null) {
            throw new IllegalArgumentException("'x' is required".toString());
        }
        byte[] byteArray2 = getByteArray(jwkWithCurve, "y");
        if (byteArray2 == null) {
            throw new IllegalArgumentException("'y' is required".toString());
        }
        byte[] bArr = new byte[(orderSize * 2) + 1];
        bArr[0] = 4;
        ArraysKt.copyInto$default(byteArray, bArr, 1, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(byteArray2, bArr, orderSize + 1, 0, 0, 12, (Object) null);
        getByteArray(jwkWithCurve, "d");
        return bArr;
    }

    /* JADX INFO: renamed from: decodeEcPrivateKey-XWxlsIE, reason: not valid java name */
    public final EcPrivateKeyComponents m8739decodeEcPrivateKeyXWxlsIE(String curve, int orderSize, byte[] jwkKey) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        Intrinsics.checkNotNullParameter(jwkKey, "jwkKey");
        JsonObject jwkWithCurve = parseJwkWithCurve(jwkKey, "EC", curve);
        byte[] byteArray = getByteArray(jwkWithCurve, "x");
        if (byteArray == null) {
            throw new IllegalArgumentException("'x' is required".toString());
        }
        byte[] byteArray2 = getByteArray(jwkWithCurve, "y");
        if (byteArray2 == null) {
            throw new IllegalArgumentException("'y' is required".toString());
        }
        byte[] bArr = new byte[(orderSize * 2) + 1];
        bArr[0] = 4;
        ArraysKt.copyInto$default(byteArray, bArr, 1, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(byteArray2, bArr, orderSize + 1, 0, 0, 12, (Object) null);
        byte[] byteArray3 = getByteArray(jwkWithCurve, "d");
        if (byteArray3 != null) {
            return new EcPrivateKeyComponents(bArr, byteArray3);
        }
        throw new IllegalArgumentException("'d' is required for private key".toString());
    }

    public final byte[] encodeOkpPublicKey(String curve, byte[] publicKey) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "kty", "OKP");
        JsonElementBuildersKt.put(jsonObjectBuilder, "crv", curve);
        INSTANCE.put(jsonObjectBuilder, "x", publicKey);
        return StringsKt.encodeToByteArray(jsonObjectBuilder.build().toString());
    }

    public final byte[] encodeOkpPrivateKey(String curve, byte[] publicKey, byte[] privateKey) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "kty", "OKP");
        JsonElementBuildersKt.put(jsonObjectBuilder, "crv", curve);
        JsonWebKeys jsonWebKeys = INSTANCE;
        jsonWebKeys.put(jsonObjectBuilder, "x", publicKey);
        jsonWebKeys.put(jsonObjectBuilder, "d", privateKey);
        return StringsKt.encodeToByteArray(jsonObjectBuilder.build().toString());
    }

    private final byte[] encodeOctKey(String alg, byte[] rawKey) {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "kty", "oct");
        if (alg != null) {
            JsonElementBuildersKt.put(jsonObjectBuilder, "alg", alg);
        }
        INSTANCE.put(jsonObjectBuilder, "k", rawKey);
        return StringsKt.encodeToByteArray(jsonObjectBuilder.build().toString());
    }

    private final byte[] jwk(String kty, String alg, Function1<? super JsonObjectBuilder, Unit> builderAction) {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "kty", kty);
        if (alg != null) {
            JsonElementBuildersKt.put(jsonObjectBuilder, "alg", alg);
        }
        builderAction.invoke(jsonObjectBuilder);
        return StringsKt.encodeToByteArray(jsonObjectBuilder.build().toString());
    }
}
