package dev.whyoleg.cryptography.providers.jdk.internal;

import dev.whyoleg.cryptography.algorithms.EdDSA;
import dev.whyoleg.cryptography.algorithms.XDH;
import io.ktor.util.NonceKt$$ExternalSyntheticApiModelOutline0;
import j$.util.Optional;
import j$.util.OptionalConversions;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.EdECPrivateKey;
import java.security.interfaces.XECPrivateKey;
import java.security.spec.ECPublicKeySpec;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.optionals.OptionalsKt;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed448PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X448PrivateKeyParameters;
import org.bouncycastle.jcajce.interfaces.EdDSAPrivateKey;
import org.bouncycastle.jcajce.interfaces.EdDSAPublicKey;
import org.bouncycastle.jcajce.interfaces.XDHPrivateKey;
import org.bouncycastle.jcajce.interfaces.XDHPublicKey;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.math.ec.ECPoint;

/* JADX INFO: compiled from: BouncyCastleBridge.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J6\u0010\f\u001a\u001a\u0012\f\u0012\n\u0018\u00010\u000ej\u0004\u0018\u0001`\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\r2\n\u0010\b\u001a\u00060\u0011j\u0002`\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0086\u0080\u0004J6\u0010\u0015\u001a\u001a\u0012\f\u0012\n\u0018\u00010\u000ej\u0004\u0018\u0001`\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\r2\n\u0010\b\u001a\u00060\u0011j\u0002`\u00122\u0006\u0010\u0013\u001a\u00020\u0016H\u0086\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/internal/BouncyCastleBridge;", "", "<init>", "()V", "isAvailable", "", "deriveEcPublicKey", "Ljava/security/spec/ECPublicKeySpec;", "privateKey", "Ljava/security/interfaces/ECPrivateKey;", "curveName", "", "deriveEdDsaPublicKey", "Lkotlin/Pair;", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;", "deriveXdhPublicKey", "Ldev/whyoleg/cryptography/algorithms/XDH$Curve;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class BouncyCastleBridge {
    public static final BouncyCastleBridge INSTANCE = new BouncyCastleBridge();
    private static final boolean isAvailable;

    /* JADX INFO: compiled from: BouncyCastleBridge.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[EdDSA.Curve.values().length];
            try {
                iArr[EdDSA.Curve.Ed25519.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EdDSA.Curve.Ed448.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[XDH.Curve.values().length];
            try {
                iArr2[XDH.Curve.X25519.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[XDH.Curve.X448.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private BouncyCastleBridge() {
    }

    static {
        boolean z;
        try {
            Class.forName("org.bouncycastle.jce.ECNamedCurveTable");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        isAvailable = z;
    }

    public final ECPublicKeySpec deriveEcPublicKey(ECPrivateKey privateKey, String curveName) {
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Intrinsics.checkNotNullParameter(curveName, "curveName");
        if (!isAvailable) {
            return null;
        }
        ECPoint eCPointNormalize = ECNamedCurveTable.getParameterSpec(curveName).getG().multiply(privateKey.getS()).normalize();
        return new ECPublicKeySpec(new java.security.spec.ECPoint(eCPointNormalize.getAffineXCoord().toBigInteger(), eCPointNormalize.getAffineYCoord().toBigInteger()), privateKey.getParams());
    }

    public final Pair<PublicKey, byte[]> deriveEdDsaPublicKey(PrivateKey privateKey, EdDSA.Curve curve) {
        Optional optionalConvert;
        byte[] bArr;
        byte[] encoded;
        EdDSAPublicKey publicKey;
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Intrinsics.checkNotNullParameter(curve, "curve");
        if (!isAvailable) {
            return null;
        }
        try {
            EdDSAPrivateKey edDSAPrivateKey = privateKey instanceof EdDSAPrivateKey ? (EdDSAPrivateKey) privateKey : null;
            if (edDSAPrivateKey != null && (publicKey = edDSAPrivateKey.getPublicKey()) != null) {
                return TuplesKt.to(publicKey, null);
            }
        } catch (NoClassDefFoundError unused) {
        }
        try {
            EdECPrivateKey edECPrivateKeyM8840m = NonceKt$$ExternalSyntheticApiModelOutline0.m$1(privateKey) ? NonceKt$$ExternalSyntheticApiModelOutline0.m8840m((Object) privateKey) : null;
            if (edECPrivateKeyM8840m != null && (optionalConvert = OptionalConversions.convert(edECPrivateKeyM8840m.getBytes())) != null && (bArr = (byte[]) OptionalsKt.getOrNull(optionalConvert)) != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[curve.ordinal()];
                if (i == 1) {
                    encoded = new Ed25519PrivateKeyParameters(bArr).generatePublicKey().getEncoded();
                } else {
                    if (i != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    encoded = new Ed448PrivateKeyParameters(bArr).generatePublicKey().getEncoded();
                }
                if (encoded != null) {
                    return TuplesKt.to(null, encoded);
                }
            }
        } catch (NoClassDefFoundError unused2) {
        }
        return null;
    }

    public final Pair<PublicKey, byte[]> deriveXdhPublicKey(PrivateKey privateKey, XDH.Curve curve) {
        Optional optionalConvert;
        byte[] bArr;
        byte[] encoded;
        XDHPublicKey publicKey;
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Intrinsics.checkNotNullParameter(curve, "curve");
        if (!isAvailable) {
            return null;
        }
        try {
            XDHPrivateKey xDHPrivateKey = privateKey instanceof XDHPrivateKey ? (XDHPrivateKey) privateKey : null;
            if (xDHPrivateKey != null && (publicKey = xDHPrivateKey.getPublicKey()) != null) {
                return TuplesKt.to(publicKey, null);
            }
        } catch (NoClassDefFoundError unused) {
        }
        try {
            XECPrivateKey xECPrivateKeyM8841m = NonceKt$$ExternalSyntheticApiModelOutline0.m$2(privateKey) ? NonceKt$$ExternalSyntheticApiModelOutline0.m8841m((Object) privateKey) : null;
            if (xECPrivateKeyM8841m != null && (optionalConvert = OptionalConversions.convert(xECPrivateKeyM8841m.getScalar())) != null && (bArr = (byte[]) OptionalsKt.getOrNull(optionalConvert)) != null) {
                int i = WhenMappings.$EnumSwitchMapping$1[curve.ordinal()];
                if (i == 1) {
                    encoded = new X25519PrivateKeyParameters(bArr).generatePublicKey().getEncoded();
                } else {
                    if (i != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    encoded = new X448PrivateKeyParameters(bArr).generatePublicKey().getEncoded();
                }
                if (encoded != null) {
                    return TuplesKt.to(null, encoded);
                }
            }
        } catch (NoClassDefFoundError unused2) {
        }
        return null;
    }
}
