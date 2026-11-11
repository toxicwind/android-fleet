package com.lagradost.cloudstream3.extractors.helper;

import androidx.autofill.HintConstants;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import dev.whyoleg.cryptography.CryptographyProvider;
import dev.whyoleg.cryptography.algorithms.AES;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.MD5;
import dev.whyoleg.cryptography.operations.HashFunction;
import dev.whyoleg.cryptography.operations.Hasher;
import dev.whyoleg.cryptography.operations.IvCipher;
import dev.whyoleg.cryptography.operations.UpdateFunction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: AesHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011H\u0007J*\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\fJH\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u0018J\n\u0010\u001c\u001a\u00020\u000f*\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/AesHelper;", "", "<init>", "()V", "provider", "Ldev/whyoleg/cryptography/CryptographyProvider;", "aesCbc", "Ldev/whyoleg/cryptography/algorithms/AES$CBC;", "md5Hasher", "Ldev/whyoleg/cryptography/operations/Hasher;", "getMd5Hasher$annotations", "cryptoAESHandler", "", "data", "pass", "", "encrypt", "", "padding", "generateKeyAndIv", "Lkotlin/Pair;", HintConstants.AUTOFILL_HINT_PASSWORD, "salt", "keyLength", "", "ivLength", "saltLength", "iterations", "hexToByteArray", "AesData", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AesHelper {
    public static final AesHelper INSTANCE = new AesHelper();
    private static final AES.CBC aesCbc;
    private static final Hasher md5Hasher;
    private static final CryptographyProvider provider;

    private static /* synthetic */ void getMd5Hasher$annotations() {
    }

    private AesHelper() {
    }

    static {
        CryptographyProvider cryptographyProvider = CryptographyProvider.INSTANCE.getDefault();
        provider = cryptographyProvider;
        aesCbc = (AES.CBC) cryptographyProvider.get(AES.CBC.INSTANCE);
        md5Hasher = ((Digest) cryptographyProvider.get(MD5.INSTANCE)).hasher();
    }

    public static /* synthetic */ String cryptoAESHandler$default(AesHelper aesHelper, String str, byte[] bArr, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            z2 = true;
        }
        return aesHelper.cryptoAESHandler(str, bArr, z, z2);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0057  */
    /* JADX WARN: Code duplicated, block: B:23:0x006d A[Catch: Exception -> 0x007f, TRY_LEAVE, TryCatch #0 {Exception -> 0x007f, blocks: (B:7:0x0026, B:14:0x0051, B:17:0x0058, B:23:0x006d, B:22:0x0068, B:13:0x0047, B:6:0x001c, B:10:0x002d, B:19:0x005c, B:3:0x000e), top: B:38:0x000e, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final String cryptoAESHandler(String data, byte[] pass, boolean encrypt, boolean padding) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Pair pairGenerateKeyAndIv$default;
        KSerializer kSerializer;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(pass, "pass");
        AppUtils appUtils = AppUtils.INSTANCE;
        try {
            try {
                Result.Companion companion = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(AesData.INSTANCE.serializer());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                    objM8936constructorimpl = null;
                }
                kSerializer = (KSerializer) objM8936constructorimpl;
                if (kSerializer != null) {
                    try {
                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, data);
                    } catch (SerializationException e) {
                        ArchComponentExtKt.logError(e);
                        objDecodeFromString = MainAPIKt.getMapper().readValue(data, new TypeReference<AesData>() { // from class: com.lagradost.cloudstream3.extractors.helper.AesHelper$cryptoAESHandler$$inlined$tryParseJson$1
                        });
                    } catch (Throwable unused) {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(data, new TypeReference<AesData>() { // from class: com.lagradost.cloudstream3.extractors.helper.AesHelper$cryptoAESHandler$$inlined$tryParseJson$1
                        });
                    }
                } else {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(data, new TypeReference<AesData>() { // from class: com.lagradost.cloudstream3.extractors.helper.AesHelper$cryptoAESHandler$$inlined$tryParseJson$1
                    });
                }
            } else {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(AesData.class), null, 2, null));
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                }
                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                    objM8936constructorimpl = null;
                }
                kSerializer = (KSerializer) objM8936constructorimpl;
                if (kSerializer != null) {
                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, data);
                } else {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(data, new TypeReference<AesData>() { // from class: com.lagradost.cloudstream3.extractors.helper.AesHelper$cryptoAESHandler$$inlined$tryParseJson$1
                    });
                }
            }
        } catch (Exception unused2) {
            objDecodeFromString = null;
        }
        AesData aesData = (AesData) objDecodeFromString;
        if (aesData == null || (pairGenerateKeyAndIv$default = generateKeyAndIv$default(this, pass, hexToByteArray(aesData.getS()), 0, aesData.getIv().length() / 2, aesData.getS().length() / 2, 0, 36, null)) == null) {
            return null;
        }
        byte[] bArr = (byte[]) pairGenerateKeyAndIv$default.component1();
        byte[] bArr2 = (byte[]) pairGenerateKeyAndIv$default.component2();
        IvCipher ivCipherCipher = aesCbc.keyDecoder().decodeFromByteArrayBlocking(AES.Key.Format.RAW, bArr).cipher(padding);
        if (!encrypt) {
            return StringsKt.decodeToString(ivCipherCipher.decryptWithIvBlocking(bArr2, MainAPIKt.base64DecodeArray(aesData.getCt())));
        }
        return MainAPIKt.base64Encode(ivCipherCipher.encryptWithIvBlocking(bArr2, StringsKt.encodeToByteArray(aesData.getCt())));
    }

    public static /* synthetic */ String cryptoAESHandler$default(AesHelper aesHelper, String str, byte[] bArr, boolean z, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return aesHelper.cryptoAESHandler(str, bArr, z, str2);
    }

    public final String cryptoAESHandler(String data, byte[] pass, boolean encrypt, String padding) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(pass, "pass");
        Intrinsics.checkNotNullParameter(padding, "padding");
        return cryptoAESHandler(data, pass, encrypt, !StringsKt.endsWith$default(padding, "NoPadding", false, 2, (Object) null));
    }

    public static /* synthetic */ Pair generateKeyAndIv$default(AesHelper aesHelper, byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            i = 32;
        }
        if ((i5 & 32) != 0) {
            i4 = 1;
        }
        return aesHelper.generateKeyAndIv(bArr, bArr2, i, i2, i3, i4);
    }

    public final Pair<byte[], byte[]> generateKeyAndIv(byte[] password, byte[] salt, int keyLength, int ivLength, int saltLength, int iterations) {
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(salt, "salt");
        int i = keyLength + ivLength;
        try {
            byte[] bArr = new byte[((i + 15) / 16) * 16];
            for (int i2 = 0; i2 < i; i2 += 16) {
                HashFunction hashFunctionCreateHashFunction = md5Hasher.createHashFunction();
                if (i2 > 0) {
                    hashFunctionCreateHashFunction.update(bArr, i2 - 16, i2);
                }
                UpdateFunction.CC.update$default(hashFunctionCreateHashFunction, password, 0, 0, 6, (Object) null);
                hashFunctionCreateHashFunction.update(salt, 0, saltLength);
                ArraysKt.copyInto$default(hashFunctionCreateHashFunction.hashToByteArray(), bArr, i2, 0, 0, 12, (Object) null);
                for (int i3 = 1; i3 < iterations; i3++) {
                    HashFunction hashFunctionCreateHashFunction2 = md5Hasher.createHashFunction();
                    hashFunctionCreateHashFunction2.update(bArr, i2, i2 + 16);
                    ArraysKt.copyInto$default(hashFunctionCreateHashFunction2.hashToByteArray(), bArr, i2, 0, 0, 12, (Object) null);
                }
            }
            return TuplesKt.to(ArraysKt.copyOfRange(bArr, 0, keyLength), ArraysKt.copyOfRange(bArr, keyLength, i));
        } catch (Exception unused) {
            return null;
        }
    }

    public final byte[] hexToByteArray(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (str.length() % 2 != 0) {
            throw new IllegalStateException("Must have an even length".toString());
        }
        List<String> listChunked = StringsKt.chunked(str, 2);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listChunked, 10));
        Iterator<T> it = listChunked.iterator();
        while (it.hasNext()) {
            arrayList.add(Byte.valueOf((byte) Integer.parseInt((String) it.next(), CharsKt.checkRadix(16))));
        }
        return CollectionsKt.toByteArray(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AesHelper.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\b\u0018\u0000 '2\u00020\u0001:\u0002&'B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007B9\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J'\u0010\u0018\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001c\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/AesHelper$AesData;", "", "ct", "", "iv", CmcdData.STREAMING_FORMAT_SS, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getCt$annotations", "()V", "getCt", "()Ljava/lang/String;", "getIv$annotations", "getIv", "getS$annotations", "getS", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    static final /* data */ class AesData {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("ct")
        private final String ct;

        @JsonProperty("iv")
        private final String iv;

        @JsonProperty(CmcdData.STREAMING_FORMAT_SS)
        private final String s;

        public static /* synthetic */ AesData copy$default(AesData aesData, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = aesData.ct;
            }
            if ((i & 2) != 0) {
                str2 = aesData.iv;
            }
            if ((i & 4) != 0) {
                str3 = aesData.s;
            }
            return aesData.copy(str, str2, str3);
        }

        @SerialName("ct")
        public static /* synthetic */ void getCt$annotations() {
        }

        @SerialName("iv")
        public static /* synthetic */ void getIv$annotations() {
        }

        @SerialName(CmcdData.STREAMING_FORMAT_SS)
        public static /* synthetic */ void getS$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getCt() {
            return this.ct;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getIv() {
            return this.iv;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getS() {
            return this.s;
        }

        public final AesData copy(@JsonProperty("ct") String ct, @JsonProperty("iv") String iv, @JsonProperty(CmcdData.STREAMING_FORMAT_SS) String s) {
            Intrinsics.checkNotNullParameter(ct, "ct");
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(s, "s");
            return new AesData(ct, iv, s);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AesData)) {
                return false;
            }
            AesData aesData = (AesData) other;
            return Intrinsics.areEqual(this.ct, aesData.ct) && Intrinsics.areEqual(this.iv, aesData.iv) && Intrinsics.areEqual(this.s, aesData.s);
        }

        public int hashCode() {
            return (((this.ct.hashCode() * 31) + this.iv.hashCode()) * 31) + this.s.hashCode();
        }

        public String toString() {
            return "AesData(ct=" + this.ct + ", iv=" + this.iv + ", s=" + this.s + ')';
        }

        /* JADX INFO: compiled from: AesHelper.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/AesHelper$AesData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/extractors/helper/AesHelper$AesData;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<AesData> serializer() {
                return AesHelper$AesData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ AesData(int i, String str, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AesHelper$AesData$$serializer.INSTANCE.getDescriptor());
            }
            this.ct = str;
            this.iv = str2;
            this.s = str3;
        }

        public AesData(@JsonProperty("ct") String ct, @JsonProperty("iv") String iv, @JsonProperty(CmcdData.STREAMING_FORMAT_SS) String s) {
            Intrinsics.checkNotNullParameter(ct, "ct");
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(s, "s");
            this.ct = ct;
            this.iv = iv;
            this.s = s;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(AesData self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.ct);
            output.encodeStringElement(serialDesc, 1, self.iv);
            output.encodeStringElement(serialDesc, 2, self.s);
        }

        public final String getCt() {
            return this.ct;
        }

        public final String getIv() {
            return this.iv;
        }

        public final String getS() {
            return this.s;
        }
    }
}
