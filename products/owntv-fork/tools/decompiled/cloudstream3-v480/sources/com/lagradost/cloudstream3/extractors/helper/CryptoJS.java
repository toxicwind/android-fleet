package com.lagradost.cloudstream3.extractors.helper;

import androidx.autofill.HintConstants;
import com.lagradost.cloudstream3.MainAPIKt;
import dev.whyoleg.cryptography.CryptographyProvider;
import dev.whyoleg.cryptography.algorithms.AES;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.MD5;
import dev.whyoleg.cryptography.operations.HashFunction;
import dev.whyoleg.cryptography.operations.Hasher;
import dev.whyoleg.cryptography.operations.UpdateFunction;
import dev.whyoleg.cryptography.random.CryptographyRandom;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CryptoJSHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\bJ\u0016\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bJ@\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0003¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/CryptoJS;", "", "<init>", "()V", "KEY_SIZE", "", "IV_SIZE", "APPEND", "", "provider", "Ldev/whyoleg/cryptography/CryptographyProvider;", "aesCbc", "Ldev/whyoleg/cryptography/algorithms/AES$CBC;", "md5Hasher", "Ldev/whyoleg/cryptography/operations/Hasher;", "getMd5Hasher$annotations", "encrypt", HintConstants.AUTOFILL_HINT_PASSWORD, "plainText", "decrypt", "cipherText", "evpkdf", "", "keySize", "ivSize", "salt", "iterations", "resultKey", "resultIv", "generateSalt", "length", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CryptoJS {
    private static final String APPEND = "Salted__";
    public static final CryptoJS INSTANCE = new CryptoJS();
    private static final int IV_SIZE = 128;
    private static final int KEY_SIZE = 256;
    private static final AES.CBC aesCbc;
    private static final Hasher md5Hasher;
    private static final CryptographyProvider provider;

    private static /* synthetic */ void getMd5Hasher$annotations() {
    }

    private CryptoJS() {
    }

    static {
        CryptographyProvider cryptographyProvider = CryptographyProvider.INSTANCE.getDefault();
        provider = cryptographyProvider;
        aesCbc = (AES.CBC) cryptographyProvider.get(AES.CBC.INSTANCE);
        md5Hasher = ((Digest) cryptographyProvider.get(MD5.INSTANCE)).hasher();
    }

    public final String encrypt(String password, String plainText) {
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(plainText, "plainText");
        byte[] bArrGenerateSalt = generateSalt(8);
        byte[] bArr = new byte[32];
        byte[] bArr2 = new byte[16];
        evpkdf(StringsKt.encodeToByteArray(password), 256, 128, bArrGenerateSalt, 1, bArr, bArr2);
        byte[] bArrEncryptWithIvBlocking = aesCbc.keyDecoder().decodeFromByteArrayBlocking(AES.Key.Format.RAW, bArr).cipher(true).encryptWithIvBlocking(bArr2, StringsKt.encodeToByteArray(plainText));
        byte[] bArrEncodeToByteArray = StringsKt.encodeToByteArray(APPEND);
        byte[] bArr3 = new byte[bArrEncodeToByteArray.length + bArrGenerateSalt.length + bArrEncryptWithIvBlocking.length];
        ArraysKt.copyInto$default(bArrEncodeToByteArray, bArr3, 0, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(bArrGenerateSalt, bArr3, bArrEncodeToByteArray.length, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(bArrEncryptWithIvBlocking, bArr3, bArrEncodeToByteArray.length + bArrGenerateSalt.length, 0, 0, 12, (Object) null);
        return MainAPIKt.base64Encode(bArr3);
    }

    public final String decrypt(String password, String cipherText) {
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(cipherText, "cipherText");
        byte[] bArrBase64DecodeArray = MainAPIKt.base64DecodeArray(cipherText);
        byte[] bArrCopyOfRange = ArraysKt.copyOfRange(bArrBase64DecodeArray, 8, 16);
        byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(bArrBase64DecodeArray, 16, bArrBase64DecodeArray.length);
        byte[] bArr = new byte[32];
        byte[] bArr2 = new byte[16];
        evpkdf(StringsKt.encodeToByteArray(password), 256, 128, bArrCopyOfRange, 1, bArr, bArr2);
        return StringsKt.decodeToString(aesCbc.keyDecoder().decodeFromByteArrayBlocking(AES.Key.Format.RAW, bArr).cipher(true).decryptWithIvBlocking(bArr2, bArrCopyOfRange2));
    }

    private final byte[] evpkdf(byte[] password, int keySize, int ivSize, byte[] salt, int iterations, byte[] resultKey, byte[] resultIv) {
        int i = keySize / 32;
        int i2 = (ivSize / 32) + i;
        int i3 = i2 * 4;
        byte[] bArr = new byte[i3];
        byte[] bArr2 = null;
        int length = 0;
        while (length < i2) {
            HashFunction hashFunctionCreateHashFunction = md5Hasher.createHashFunction();
            if (bArr2 != null) {
                UpdateFunction.CC.update$default(hashFunctionCreateHashFunction, bArr2, 0, 0, 6, (Object) null);
            }
            HashFunction hashFunction = hashFunctionCreateHashFunction;
            UpdateFunction.CC.update$default(hashFunction, password, 0, 0, 6, (Object) null);
            UpdateFunction.CC.update$default(hashFunction, salt, 0, 0, 6, (Object) null);
            byte[] bArrHashToByteArray = hashFunctionCreateHashFunction.hashToByteArray();
            for (int i4 = 1; i4 < iterations; i4++) {
                HashFunction hashFunctionCreateHashFunction2 = md5Hasher.createHashFunction();
                Intrinsics.checkNotNull(bArrHashToByteArray);
                UpdateFunction.CC.update$default(hashFunctionCreateHashFunction2, bArrHashToByteArray, 0, 0, 6, (Object) null);
                bArrHashToByteArray = hashFunctionCreateHashFunction2.hashToByteArray();
            }
            ArraysKt.copyInto(bArrHashToByteArray, bArr, length * 4, 0, Math.min(bArrHashToByteArray.length, (i2 - length) * 4));
            length += bArrHashToByteArray.length / 4;
            bArr2 = bArrHashToByteArray;
        }
        int i5 = i * 4;
        ArraysKt.copyInto(bArr, resultKey, 0, 0, i5);
        ArraysKt.copyInto(bArr, resultIv, 0, i5, i3);
        return bArr;
    }

    private final byte[] generateSalt(int length) {
        return CryptographyRandom.INSTANCE.nextBytes(length);
    }
}
