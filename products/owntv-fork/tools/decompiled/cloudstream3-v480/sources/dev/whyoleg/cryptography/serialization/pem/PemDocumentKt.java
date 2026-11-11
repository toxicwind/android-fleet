package dev.whyoleg.cryptography.serialization.pem;

import io.ktor.http.ContentType;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.encoding.Base64;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.io.ByteStringsKt;
import kotlinx.io.CoreKt;
import kotlinx.io.Source;
import kotlinx.io.SourcesKt;
import kotlinx.io.bytestring.Base64Kt;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.ByteStringKt;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: PemDocument.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\n\u001a\u000e\u0010\f\u001a\u00020\t*\u00020\rH\u0082\u0080\u0004\u001a?\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u0013H\u0082\u0088\u0004\u001a?\u0010\u0018\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u00112!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u0013H\u0082\u0088\u0004\u001a\u0014\u0010\u001b\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001c\u001a\u00020\u001dH\u0082\u0080\u0004\u001a\u0012\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0003H\u0082\u0080\u0004\u001a\n\u0010!\u001a\u00020\u001fH\u0082\u0080\u0004\u001a\n\u0010\"\u001a\u00020\u001fH\u0082\u0080\u0004\u001a\n\u0010#\u001a\u00020\u001fH\u0082\u0080\u0004\u001a\n\u0010$\u001a\u00020\u001fH\u0082\u0080\u0004\u001a\n\u0010%\u001a\u00020\u001fH\u0082\u0080\u0004\u001a\u001a\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u0003H\u0082\u0080\u0004\"\u000f\u0010\u0000\u001a\u00020\u0001X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0002\u001a\u00020\u0003X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0004\u001a\u00020\u0003X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0005\u001a\u00020\u0003X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0006\u001a\u00020\u0007X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\b\u001a\u00020\tX\u0082\u0084\b¢\u0006\u0002\n\u0000\"\u000f\u0010\n\u001a\u00020\tX\u0082\u0084\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u000b\u001a\u00020\tX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"NEW_LINE", "", "BEGIN_PREFIX", "", "END_PREFIX", "SUFFIX", "NEW_LINE_BYTE", "", "BEGIN_BYTES", "", "END_BYTES", "SUFFIX_BYTES", "encodeToByteArrayImpl", "Ldev/whyoleg/cryptography/serialization/pem/PemDocument;", "tryDecodeFromString", "text", "startIndex", "", "saveEndIndex", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "endIndex", "", "tryDecodeFromByteString", "bytes", "Lkotlinx/io/bytestring/ByteString;", "tryDecodeFromSource", "source", "Lkotlinx/io/Source;", "throwPemInvalid", "", ContentType.Message.TYPE, "throwPemMissingBeginLabel", "throwPemMissingNewLineAfterBeginLabel", "throwPemMissingBeginLabelSuffix", "throwPemMissingEndLabel", "throwPemMissingEndLabelSuffix", "throwPemBeginEndLabelMismatch", "beginLabel", "endLabel", "cryptography-serialization-pem"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PemDocumentKt {
    private static final char NEW_LINE = '\n';
    private static final byte NEW_LINE_BYTE = 10;
    private static final String BEGIN_PREFIX = "-----BEGIN ";
    private static final byte[] BEGIN_BYTES = StringsKt.encodeToByteArray(BEGIN_PREFIX);
    private static final String END_PREFIX = "-----END ";
    private static final byte[] END_BYTES = StringsKt.encodeToByteArray(END_PREFIX);
    private static final String SUFFIX = "-----";
    private static final byte[] SUFFIX_BYTES = StringsKt.encodeToByteArray(SUFFIX);

    private static final int encodeToByteArrayImpl$base64EncodedSize(int i) {
        int i2 = (i / 3) * 4;
        if (i % 3 != 0) {
            i2 += 4;
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Input is too big");
        }
        int i3 = i2 + (((i2 - 1) / 64) * 2);
        if (i3 >= 0) {
            return i3;
        }
        throw new IllegalArgumentException("Input is too big");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] encodeToByteArrayImpl(PemDocument pemDocument) {
        byte[] bArrEncodeToByteArray = StringsKt.encodeToByteArray(pemDocument.getLabel());
        int iEncodeToByteArrayImpl$base64EncodedSize = encodeToByteArrayImpl$base64EncodedSize(pemDocument.getContent().getSize());
        byte[] bArr = BEGIN_BYTES;
        int length = bArr.length + bArrEncodeToByteArray.length;
        byte[] bArr2 = SUFFIX_BYTES;
        int length2 = length + bArr2.length + 1 + iEncodeToByteArrayImpl$base64EncodedSize + 1;
        byte[] bArr3 = END_BYTES;
        byte[] bArr4 = new byte[length2 + bArr3.length + bArrEncodeToByteArray.length + bArr2.length + 1];
        ArraysKt.copyInto$default(bArr, bArr4, 0, 0, 0, 14, (Object) null);
        ArraysKt.copyInto$default(bArrEncodeToByteArray, bArr4, bArr.length, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(bArr2, bArr4, bArr.length + bArrEncodeToByteArray.length, 0, 0, 12, (Object) null);
        bArr4[bArr.length + bArrEncodeToByteArray.length + bArr2.length] = 10;
        Base64Kt.encodeIntoByteArray$default(Base64.INSTANCE.getPem(), pemDocument.getContent(), bArr4, bArr.length + bArrEncodeToByteArray.length + bArr2.length + 1, 0, 0, 24, null);
        bArr4[bArr.length + bArrEncodeToByteArray.length + bArr2.length + 1 + iEncodeToByteArrayImpl$base64EncodedSize] = 10;
        ArraysKt.copyInto$default(bArr3, bArr4, bArr.length + bArrEncodeToByteArray.length + bArr2.length + 1 + iEncodeToByteArrayImpl$base64EncodedSize + 1, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(bArrEncodeToByteArray, bArr4, bArr.length + bArrEncodeToByteArray.length + bArr2.length + 1 + iEncodeToByteArrayImpl$base64EncodedSize + 1 + bArr3.length, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(bArr2, bArr4, bArr.length + bArrEncodeToByteArray.length + bArr2.length + 1 + iEncodeToByteArrayImpl$base64EncodedSize + 1 + bArr3.length + bArrEncodeToByteArray.length, 0, 0, 12, (Object) null);
        bArr4[ArraysKt.getLastIndex(bArr4)] = 10;
        return bArr4;
    }

    private static final PemDocument tryDecodeFromString(String str, int i, Function1<? super Integer, Unit> function1) {
        String str2 = str;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str2, BEGIN_PREFIX, i, false, 4, (Object) null);
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (iIndexOf$default == -1) {
            return null;
        }
        int i2 = iIndexOf$default + 11;
        int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str2, NEW_LINE, i2, false, 4, (Object) null);
        if (iIndexOf$default2 == -1) {
            throwPemMissingNewLineAfterBeginLabel();
            throw new KotlinNothingValueException();
        }
        int iIndexOf$default3 = StringsKt.indexOf$default((CharSequence) str2, SUFFIX, i2, false, 4, (Object) null);
        if (iIndexOf$default3 == -1 || iIndexOf$default3 > iIndexOf$default2) {
            throwPemMissingBeginLabelSuffix();
            throw new KotlinNothingValueException();
        }
        String strSubstring = str.substring(i2, iIndexOf$default3);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        int iIndexOf$default4 = StringsKt.indexOf$default((CharSequence) str2, END_PREFIX, iIndexOf$default2, false, 4, (Object) null);
        if (iIndexOf$default4 == -1) {
            throwPemMissingEndLabel();
            throw new KotlinNothingValueException();
        }
        int i3 = iIndexOf$default4 + 9;
        int iIndexOf$default5 = StringsKt.indexOf$default((CharSequence) str2, NEW_LINE, i3, false, 4, (Object) null);
        int iIndexOf$default6 = StringsKt.indexOf$default((CharSequence) str2, SUFFIX, i3, false, 4, (Object) null);
        if (iIndexOf$default6 == -1 || (iIndexOf$default5 != -1 && iIndexOf$default6 > iIndexOf$default5)) {
            throwPemMissingEndLabelSuffix();
            throw new KotlinNothingValueException();
        }
        String strSubstring2 = str.substring(i3, iIndexOf$default6);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        if (!Intrinsics.areEqual(strSubstring2, strSubstring)) {
            throwPemBeginEndLabelMismatch(strSubstring, strSubstring2);
            throw new KotlinNothingValueException();
        }
        function1.invoke(Integer.valueOf(iIndexOf$default5 == -1 ? iIndexOf$default6 + 5 : iIndexOf$default5 + 1));
        return new PemDocument(PemLabel.m8802constructorimpl(strSubstring), Base64Kt.decodeToByteString(Base64.INSTANCE.getPem(), str2, iIndexOf$default2 + 1, iIndexOf$default4), defaultConstructorMarker);
    }

    private static final PemDocument tryDecodeFromByteString(ByteString byteString, int i, Function1<? super Integer, Unit> function1) {
        int iIndexOf = ByteStringKt.indexOf(byteString, BEGIN_BYTES, i);
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (iIndexOf == -1) {
            return null;
        }
        int iIndexOf2 = ByteStringKt.indexOf(byteString, (byte) 10, BEGIN_BYTES.length + iIndexOf);
        if (iIndexOf2 != -1) {
            int iIndexOf3 = ByteStringKt.indexOf(byteString, SUFFIX_BYTES, BEGIN_BYTES.length + iIndexOf);
            if (iIndexOf3 == -1 || iIndexOf3 > iIndexOf2) {
                throwPemMissingBeginLabelSuffix();
                throw new KotlinNothingValueException();
            }
            ByteString byteStringSubstring = byteString.substring(iIndexOf + BEGIN_BYTES.length, iIndexOf3);
            int iIndexOf4 = ByteStringKt.indexOf(byteString, END_BYTES, iIndexOf2);
            if (iIndexOf4 != -1) {
                int iIndexOf5 = ByteStringKt.indexOf(byteString, (byte) 10, END_BYTES.length + iIndexOf4);
                int iIndexOf6 = ByteStringKt.indexOf(byteString, SUFFIX_BYTES, END_BYTES.length + iIndexOf4);
                if (iIndexOf6 == -1 || (iIndexOf5 != -1 && iIndexOf6 > iIndexOf5)) {
                    throwPemMissingEndLabelSuffix();
                    throw new KotlinNothingValueException();
                }
                ByteString byteStringSubstring2 = byteString.substring(END_BYTES.length + iIndexOf4, iIndexOf6);
                if (!Intrinsics.areEqual(byteStringSubstring2, byteStringSubstring)) {
                    throwPemBeginEndLabelMismatch(ByteStringKt.decodeToString(byteStringSubstring), ByteStringKt.decodeToString(byteStringSubstring2));
                    throw new KotlinNothingValueException();
                }
                function1.invoke(Integer.valueOf(iIndexOf5 == -1 ? iIndexOf6 + SUFFIX_BYTES.length : iIndexOf5 + 1));
                return new PemDocument(PemLabel.m8802constructorimpl(ByteStringKt.decodeToString(byteStringSubstring)), Base64Kt.decodeToByteString(Base64.INSTANCE.getPem(), byteString, iIndexOf2 + 1, iIndexOf4), defaultConstructorMarker);
            }
            throwPemMissingEndLabel();
            throw new KotlinNothingValueException();
        }
        throwPemMissingNewLineAfterBeginLabel();
        throw new KotlinNothingValueException();
    }

    static /* synthetic */ long tryDecodeFromSource$indexOf$default(Source source, byte[] bArr, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        return tryDecodeFromSource$indexOf(source, bArr, j);
    }

    private static final long tryDecodeFromSource$indexOf(Source source, byte[] bArr, long j) {
        return ByteStringsKt.indexOf(source, UnsafeByteStringOperations.INSTANCE.wrapUnsafe(bArr), j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PemDocument tryDecodeFromSource(Source source) {
        byte[] bArr = BEGIN_BYTES;
        long jTryDecodeFromSource$indexOf$default = tryDecodeFromSource$indexOf$default(source, bArr, 0L, 2, null);
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (jTryDecodeFromSource$indexOf$default == -1) {
            source.transferTo(CoreKt.discardingSink());
            return null;
        }
        source.skip(jTryDecodeFromSource$indexOf$default + ((long) bArr.length));
        long jIndexOf$default = SourcesKt.indexOf$default(source, (byte) 10, 0L, 0L, 6, null);
        if (jIndexOf$default == -1) {
            throwPemMissingNewLineAfterBeginLabel();
            throw new KotlinNothingValueException();
        }
        byte[] bArr2 = SUFFIX_BYTES;
        long jTryDecodeFromSource$indexOf$default2 = tryDecodeFromSource$indexOf$default(source, bArr2, 0L, 2, null);
        if (jTryDecodeFromSource$indexOf$default2 == -1 || jTryDecodeFromSource$indexOf$default2 > jIndexOf$default) {
            throwPemMissingBeginLabelSuffix();
            throw new KotlinNothingValueException();
        }
        ByteString byteString = ByteStringsKt.readByteString(source, (int) jTryDecodeFromSource$indexOf$default2);
        source.skip((jIndexOf$default + 1) - jTryDecodeFromSource$indexOf$default2);
        byte[] bArr3 = END_BYTES;
        long jTryDecodeFromSource$indexOf$default3 = tryDecodeFromSource$indexOf$default(source, bArr3, 0L, 2, null);
        if (jTryDecodeFromSource$indexOf$default3 == -1) {
            throwPemMissingEndLabel();
            throw new KotlinNothingValueException();
        }
        ByteString byteString2 = ByteStringsKt.readByteString(source, (int) jTryDecodeFromSource$indexOf$default3);
        source.skip(bArr3.length);
        long jIndexOf$default2 = SourcesKt.indexOf$default(source, (byte) 10, 0L, 0L, 6, null);
        long jTryDecodeFromSource$indexOf$default4 = tryDecodeFromSource$indexOf$default(source, bArr2, 0L, 2, null);
        if (jTryDecodeFromSource$indexOf$default4 == -1 || (jIndexOf$default2 != -1 && jTryDecodeFromSource$indexOf$default4 > jIndexOf$default2)) {
            throwPemMissingEndLabelSuffix();
            throw new KotlinNothingValueException();
        }
        ByteString byteString3 = ByteStringsKt.readByteString(source, (int) jTryDecodeFromSource$indexOf$default4);
        if (jIndexOf$default2 == -1) {
            source.skip(bArr2.length);
        } else {
            source.skip((jIndexOf$default2 + 1) - jTryDecodeFromSource$indexOf$default4);
        }
        if (!Intrinsics.areEqual(byteString3, byteString)) {
            throwPemBeginEndLabelMismatch(ByteStringKt.decodeToString(byteString), ByteStringKt.decodeToString(byteString3));
            throw new KotlinNothingValueException();
        }
        return new PemDocument(PemLabel.m8802constructorimpl(ByteStringKt.decodeToString(byteString)), Base64Kt.decodeToByteString$default(Base64.INSTANCE.getPem(), byteString2, 0, 0, 6, (Object) null), defaultConstructorMarker);
    }

    private static final Void throwPemInvalid(String str) {
        throw new IllegalArgumentException("Invalid PEM format: " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void throwPemMissingBeginLabel() {
        throwPemInvalid("missing BEGIN label");
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void throwPemMissingNewLineAfterBeginLabel() {
        throwPemInvalid("missing new line after BEGIN label");
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void throwPemMissingBeginLabelSuffix() {
        throwPemInvalid("missing BEGIN label suffix");
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void throwPemMissingEndLabel() {
        throwPemInvalid("missing END label");
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void throwPemMissingEndLabelSuffix() {
        throwPemInvalid("missing END label suffix");
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void throwPemBeginEndLabelMismatch(String str, String str2) {
        throwPemInvalid("BEGIN(" + str + ") and END(" + str2 + ") labels mismatch");
        throw new KotlinNothingValueException();
    }
}
