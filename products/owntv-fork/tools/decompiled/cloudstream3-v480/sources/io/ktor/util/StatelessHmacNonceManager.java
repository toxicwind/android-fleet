package io.ktor.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.HexExtensionsKt;
import kotlin.text.HexFormat;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: StatelessHmacNonceManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0004\b\n\u0010\u000bB5\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0004\b\n\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004H\u0096@¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0004H\u0096@¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lio/ktor/util/StatelessHmacNonceManager;", "Lio/ktor/util/NonceManager;", "Ljavax/crypto/spec/SecretKeySpec;", "keySpec", "", "algorithm", "", "timeoutMillis", "Lkotlin/Function0;", "nonceGenerator", "<init>", "(Ljavax/crypto/spec/SecretKeySpec;Ljava/lang/String;JLkotlin/jvm/functions/Function0;)V", "", "key", "([BLjava/lang/String;JLkotlin/jvm/functions/Function0;)V", "newNonce", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "nonce", "", "verifyNonce", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Ljavax/crypto/spec/SecretKeySpec;", "getKeySpec", "()Ljavax/crypto/spec/SecretKeySpec;", "Ljava/lang/String;", "getAlgorithm", "()Ljava/lang/String;", "J", "getTimeoutMillis", "()J", "Lkotlin/jvm/functions/Function0;", "getNonceGenerator", "()Lkotlin/jvm/functions/Function0;", "", "macLength", "I", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class StatelessHmacNonceManager implements NonceManager {
    private final String algorithm;
    private final SecretKeySpec keySpec;
    private final int macLength;
    private final Function0<String> nonceGenerator;
    private final long timeoutMillis;

    public StatelessHmacNonceManager(SecretKeySpec keySpec, String algorithm, long j, Function0<String> nonceGenerator) throws NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(keySpec, "keySpec");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(nonceGenerator, "nonceGenerator");
        this.keySpec = keySpec;
        this.algorithm = algorithm;
        this.timeoutMillis = j;
        this.nonceGenerator = nonceGenerator;
        Mac mac = Mac.getInstance(algorithm);
        mac.init(keySpec);
        this.macLength = mac.getMacLength();
    }

    public final SecretKeySpec getKeySpec() {
        return this.keySpec;
    }

    public /* synthetic */ StatelessHmacNonceManager(SecretKeySpec secretKeySpec, String str, long j, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(secretKeySpec, (i & 2) != 0 ? "HmacSHA256" : str, (i & 4) != 0 ? 60000L : j, (Function0<String>) ((i & 8) != 0 ? new Function0() { // from class: io.ktor.util.StatelessHmacNonceManager$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CryptoKt__CryptoJvmKt.generateNonceBlocking$default(0, 1, null);
            }
        } : function0));
    }

    public final String getAlgorithm() {
        return this.algorithm;
    }

    public final long getTimeoutMillis() {
        return this.timeoutMillis;
    }

    public final Function0<String> getNonceGenerator() {
        return this.nonceGenerator;
    }

    public /* synthetic */ StatelessHmacNonceManager(byte[] bArr, String str, long j, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, (i & 2) != 0 ? "HmacSHA256" : str, (i & 4) != 0 ? 60000L : j, (Function0<String>) ((i & 8) != 0 ? new Function0() { // from class: io.ktor.util.StatelessHmacNonceManager$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CryptoKt__CryptoJvmKt.generateNonceBlocking$default(0, 1, null);
            }
        } : function0));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StatelessHmacNonceManager(byte[] key, String algorithm, long j, Function0<String> nonceGenerator) {
        this(new SecretKeySpec(key, algorithm), algorithm, j, nonceGenerator);
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(nonceGenerator, "nonceGenerator");
    }

    @Override // io.ktor.util.NonceManager
    public Object newNonce(Continuation<? super String> continuation) throws NoSuchAlgorithmException, InvalidKeyException {
        String strInvoke = this.nonceGenerator.invoke();
        String string = Long.toString(System.nanoTime(), CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String strPadStart = StringsKt.padStart(string, 16, '0');
        Mac mac = Mac.getInstance(this.algorithm);
        mac.init(this.keySpec);
        byte[] bytes = (strInvoke + AbstractJsonLexerKt.COLON + strPadStart).getBytes(Charsets.ISO_8859_1);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        mac.update(bytes);
        byte[] bArrDoFinal = mac.doFinal();
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
        return strInvoke + '+' + strPadStart + '+' + HexExtensionsKt.toHexString$default(bArrDoFinal, (HexFormat) null, 1, (Object) null);
    }

    @Override // io.ktor.util.NonceManager
    public Object verifyNonce(String str, Continuation<? super Boolean> continuation) throws NoSuchAlgorithmException, InvalidKeyException {
        List listSplit$default = StringsKt.split$default((CharSequence) str, new char[]{'+'}, false, 0, 6, (Object) null);
        if (listSplit$default.size() != 3) {
            return Boxing.boxBoolean(false);
        }
        String str2 = (String) listSplit$default.get(0);
        String str3 = (String) listSplit$default.get(1);
        String str4 = (String) listSplit$default.get(2);
        if (str2.length() >= 8 && str4.length() == this.macLength * 2 && str3.length() == 16 && Long.parseLong(str3, CharsKt.checkRadix(16)) + TimeUnit.MILLISECONDS.toNanos(this.timeoutMillis) >= System.nanoTime()) {
            Mac mac = Mac.getInstance(this.algorithm);
            mac.init(this.keySpec);
            byte[] bytes = (str2 + AbstractJsonLexerKt.COLON + str3).getBytes(Charsets.ISO_8859_1);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            mac.update(bytes);
            byte[] bArrDoFinal = mac.doFinal();
            Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
            String hexString$default = HexExtensionsKt.toHexString$default(bArrDoFinal, (HexFormat) null, 1, (Object) null);
            int iMin = Math.min(hexString$default.length(), str4.length());
            int i = 0;
            for (int i2 = 0; i2 < iMin; i2++) {
                if (hexString$default.charAt(i2) == str4.charAt(i2)) {
                    i++;
                }
            }
            return Boxing.boxBoolean(i == this.macLength * 2);
        }
        return Boxing.boxBoolean(false);
    }
}
