package dev.whyoleg.cryptography.providers.jdk.algorithms;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.spec.ECField;
import java.security.spec.ECFieldFp;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkEc.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\u0080\u0004\u001a\u0016\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0080\u0080\u0004\u001a\u0016\u0010\u0007\u001a\u00020\b*\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0080\u0080\u0004\u001a\u0012\u0010\n\u001a\u00020\u000b*\u00060\fj\u0002`\rH\u0082\u0080\u0004¨\u0006\u000e"}, d2 = {"curveOrderSize", "", "Ljava/security/spec/ECParameterSpec;", "decodePoint", "Ljava/security/spec/ECPoint;", "bytes", "", "modSqrt", "Ljava/math/BigInteger;", TtmlNode.TAG_P, "curveName", "", "Ljava/security/AlgorithmParameters;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameters;", "cryptography-provider-jdk"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class JdkEcKt {
    public static final int curveOrderSize(ECParameterSpec eCParameterSpec) {
        Intrinsics.checkNotNullParameter(eCParameterSpec, "<this>");
        return (eCParameterSpec.getCurve().getField().getFieldSize() + 7) / 8;
    }

    public static final ECPoint decodePoint(ECParameterSpec eCParameterSpec, byte[] bytes) {
        Intrinsics.checkNotNullParameter(eCParameterSpec, "<this>");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        int iCurveOrderSize = curveOrderSize(eCParameterSpec);
        byte b = bytes[0];
        if (b != 2 && b != 3) {
            if (b == 4) {
                if (bytes.length != (iCurveOrderSize * 2) + 1) {
                    throw new IllegalStateException(("Wrong uncompressed key size " + bytes.length).toString());
                }
                int i = iCurveOrderSize + 1;
                return new ECPoint(new BigInteger(1, ArraysKt.copyOfRange(bytes, 1, i)), new BigInteger(1, ArraysKt.copyOfRange(bytes, i, iCurveOrderSize + i)));
            }
            throw new IllegalStateException(("Unsupported key type " + ((int) bytes[0])).toString());
        }
        if (bytes.length != iCurveOrderSize + 1) {
            throw new IllegalStateException(("Wrong compressed key size " + bytes.length).toString());
        }
        ECField field = eCParameterSpec.getCurve().getField();
        Intrinsics.checkNotNull(field, "null cannot be cast to non-null type java.security.spec.ECFieldFp");
        BigInteger p = ((ECFieldFp) field).getP();
        BigInteger a = eCParameterSpec.getCurve().getA();
        BigInteger b2 = eCParameterSpec.getCurve().getB();
        BigInteger bigInteger = new BigInteger(1, ArraysKt.copyOfRange(bytes, 1, bytes.length));
        BigInteger bigIntegerMod = bigInteger.multiply(bigInteger).add(a).multiply(bigInteger).add(b2).mod(p);
        Intrinsics.checkNotNullExpressionValue(bigIntegerMod, "mod(...)");
        Intrinsics.checkNotNull(p);
        BigInteger bigIntegerModSqrt = modSqrt(bigIntegerMod, p);
        if (bigIntegerModSqrt.testBit(0) != (bytes[0] == 3)) {
            bigIntegerModSqrt = p.subtract(bigIntegerModSqrt);
            Intrinsics.checkNotNullExpressionValue(bigIntegerModSqrt, "subtract(...)");
        }
        return new ECPoint(bigInteger, bigIntegerModSqrt);
    }

    public static final BigInteger modSqrt(BigInteger bigInteger, BigInteger p) {
        Intrinsics.checkNotNullParameter(bigInteger, "<this>");
        Intrinsics.checkNotNullParameter(p, "p");
        if (!p.testBit(0) || !p.testBit(1)) {
            throw new IllegalStateException("Unsupported curve modulus".toString());
        }
        BigInteger bigIntegerModPow = bigInteger.modPow(p.add(BigInteger.ONE).shiftRight(2), p);
        Intrinsics.checkNotNullExpressionValue(bigIntegerModPow, "modPow(...)");
        return bigIntegerModPow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String curveName(AlgorithmParameters algorithmParameters) {
        String name = ((ECGenParameterSpec) algorithmParameters.getParameterSpec(ECGenParameterSpec.class)).getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return name;
    }
}
