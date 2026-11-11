package dev.whyoleg.cryptography.serialization.pem;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.io.Sink;
import kotlinx.io.Source;
import kotlinx.io.bytestring.Base64Kt;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.ByteStringKt;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: PemDocument.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\bV\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\tJ\n\u0010\u000f\u001a\u00020\u0010H\u0086\u0080\u0004J\n\u0010\u0011\u001a\u00020\bH\u0086\u0080\u0004J\n\u0010\u0012\u001a\u00020\u0005H\u0086\u0080\u0004J\u0012\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0086\u0080\u0004J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\u001a\u001a\u00020\u001bH\u0096\u0080\u0004J\n\u0010\u001c\u001a\u00020\u0010H\u0096\u0080\u0004R\u0017\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001e"}, d2 = {"Ldev/whyoleg/cryptography/serialization/pem/PemDocument;", "", "label", "Ldev/whyoleg/cryptography/serialization/pem/PemLabel;", "content", "Lkotlinx/io/bytestring/ByteString;", "<init>", "(Ljava/lang/String;Lkotlinx/io/bytestring/ByteString;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "", "(Ljava/lang/String;[BLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getLabel-2EFq_Wg", "()Ljava/lang/String;", "Ljava/lang/String;", "getContent", "()Lkotlinx/io/bytestring/ByteString;", "encodeToString", "", "encodeToByteArray", "encodeToByteString", "encodeToSink", "", "sink", "Lkotlinx/io/Sink;", "equals", "", "other", "hashCode", "", "toString", "Companion", "cryptography-serialization-pem"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PemDocument {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ByteString content;
    private final String label;

    public /* synthetic */ PemDocument(String str, ByteString byteString, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, byteString);
    }

    public /* synthetic */ PemDocument(String str, byte[] bArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, bArr);
    }

    private PemDocument(String label, ByteString content) {
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(content, "content");
        this.label = label;
        this.content = content;
    }

    /* JADX INFO: renamed from: getLabel-2EFq_Wg, reason: not valid java name and from getter */
    public final String getLabel() {
        return this.label;
    }

    public final ByteString getContent() {
        return this.content;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    private PemDocument(String label, byte[] content) {
        this(label, new ByteString(content, 0, 0, 6, null), (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(content, "content");
    }

    public final String encodeToString() {
        return StringsKt.decodeToString(PemDocumentKt.encodeToByteArrayImpl(this));
    }

    public final byte[] encodeToByteArray() {
        return PemDocumentKt.encodeToByteArrayImpl(this);
    }

    public final ByteString encodeToByteString() {
        return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(PemDocumentKt.encodeToByteArrayImpl(this));
    }

    public final void encodeToSink(Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Sink.CC.write$default(sink, PemDocumentKt.encodeToByteArrayImpl(this), 0, 0, 6, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PemDocument)) {
            return false;
        }
        PemDocument pemDocument = (PemDocument) other;
        return PemLabel.m8804equalsimpl0(this.label, pemDocument.label) && Intrinsics.areEqual(this.content, pemDocument.content);
    }

    public int hashCode() {
        return (PemLabel.m8805hashCodeimpl(this.label) * 31) + this.content.hashCode();
    }

    public String toString() {
        return "PemDocument(label=" + this.label + ", content=" + this.content + ')';
    }

    /* JADX INFO: compiled from: PemDocument.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0080\u0004J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0080\u0004J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\fH\u0086\u0080\u0004J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\n\u001a\u00020\fH\u0086\u0080\u0004J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0080\u0004J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0080\u0004¨\u0006\u000f"}, d2 = {"Ldev/whyoleg/cryptography/serialization/pem/PemDocument$Companion;", "", "<init>", "()V", "decode", "Ldev/whyoleg/cryptography/serialization/pem/PemDocument;", "text", "", "decodeToSequence", "Lkotlin/sequences/Sequence;", "bytes", "", "Lkotlinx/io/bytestring/ByteString;", "source", "Lkotlinx/io/Source;", "cryptography-serialization-pem"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Sequence<PemDocument> decodeToSequence(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return SequencesKt.sequence(new PemDocument$Companion$decodeToSequence$1(text, null));
        }

        public final PemDocument decode(byte[] bytes) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            return decode(UnsafeByteStringOperations.INSTANCE.wrapUnsafe(bytes));
        }

        public final Sequence<PemDocument> decodeToSequence(byte[] bytes) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            return decodeToSequence(UnsafeByteStringOperations.INSTANCE.wrapUnsafe(bytes));
        }

        public final Sequence<PemDocument> decodeToSequence(ByteString bytes) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            return SequencesKt.sequence(new PemDocument$Companion$decodeToSequence$2(bytes, null));
        }

        public final PemDocument decode(Source source) {
            Intrinsics.checkNotNullParameter(source, "source");
            PemDocument pemDocumentTryDecodeFromSource = PemDocumentKt.tryDecodeFromSource(source);
            if (pemDocumentTryDecodeFromSource != null) {
                return pemDocumentTryDecodeFromSource;
            }
            PemDocumentKt.throwPemMissingBeginLabel();
            throw new KotlinNothingValueException();
        }

        public final Sequence<PemDocument> decodeToSequence(Source source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return SequencesKt.sequence(new PemDocument$Companion$decodeToSequence$3(source, null));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final PemDocument decode(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            String str = text;
            int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, "-----BEGIN ", 0, false, 4, (Object) null);
            PemDocument pemDocument = null;
            Object[] objArr = 0;
            if (iIndexOf$default != -1) {
                int i = iIndexOf$default + 11;
                int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str, '\n', i, false, 4, (Object) null);
                if (iIndexOf$default2 == -1) {
                    PemDocumentKt.throwPemMissingNewLineAfterBeginLabel();
                    throw new KotlinNothingValueException();
                }
                int iIndexOf$default3 = StringsKt.indexOf$default((CharSequence) str, "-----", i, false, 4, (Object) null);
                if (iIndexOf$default3 == -1 || iIndexOf$default3 > iIndexOf$default2) {
                    PemDocumentKt.throwPemMissingBeginLabelSuffix();
                    throw new KotlinNothingValueException();
                }
                String strSubstring = text.substring(i, iIndexOf$default3);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                int iIndexOf$default4 = StringsKt.indexOf$default((CharSequence) str, "-----END ", iIndexOf$default2, false, 4, (Object) null);
                if (iIndexOf$default4 == -1) {
                    PemDocumentKt.throwPemMissingEndLabel();
                    throw new KotlinNothingValueException();
                }
                int i2 = iIndexOf$default4 + 9;
                int iIndexOf$default5 = StringsKt.indexOf$default((CharSequence) str, '\n', i2, false, 4, (Object) null);
                int iIndexOf$default6 = StringsKt.indexOf$default((CharSequence) str, "-----", i2, false, 4, (Object) null);
                if (iIndexOf$default6 == -1 || (iIndexOf$default5 != -1 && iIndexOf$default6 > iIndexOf$default5)) {
                    PemDocumentKt.throwPemMissingEndLabelSuffix();
                    throw new KotlinNothingValueException();
                }
                String strSubstring2 = text.substring(i2, iIndexOf$default6);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                if (!Intrinsics.areEqual(strSubstring2, strSubstring)) {
                    PemDocumentKt.throwPemBeginEndLabelMismatch(strSubstring, strSubstring2);
                    throw new KotlinNothingValueException();
                }
                pemDocument = new PemDocument(PemLabel.m8802constructorimpl(strSubstring), Base64Kt.decodeToByteString(Base64.INSTANCE.getPem(), str, iIndexOf$default2 + 1, iIndexOf$default4), (DefaultConstructorMarker) (objArr == true ? 1 : 0));
            }
            if (pemDocument != null) {
                return pemDocument;
            }
            PemDocumentKt.throwPemMissingBeginLabel();
            throw new KotlinNothingValueException();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final PemDocument decode(ByteString bytes) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            int iIndexOf = ByteStringKt.indexOf(bytes, PemDocumentKt.BEGIN_BYTES, 0);
            PemDocument pemDocument = null;
            Object[] objArr = 0;
            if (iIndexOf != -1) {
                int iIndexOf2 = ByteStringKt.indexOf(bytes, (byte) 10, PemDocumentKt.BEGIN_BYTES.length + iIndexOf);
                if (iIndexOf2 != -1) {
                    int iIndexOf3 = ByteStringKt.indexOf(bytes, PemDocumentKt.SUFFIX_BYTES, PemDocumentKt.BEGIN_BYTES.length + iIndexOf);
                    if (iIndexOf3 == -1 || iIndexOf3 > iIndexOf2) {
                        PemDocumentKt.throwPemMissingBeginLabelSuffix();
                        throw new KotlinNothingValueException();
                    }
                    ByteString byteStringSubstring = bytes.substring(iIndexOf + PemDocumentKt.BEGIN_BYTES.length, iIndexOf3);
                    int iIndexOf4 = ByteStringKt.indexOf(bytes, PemDocumentKt.END_BYTES, iIndexOf2);
                    if (iIndexOf4 != -1) {
                        int iIndexOf5 = ByteStringKt.indexOf(bytes, (byte) 10, PemDocumentKt.END_BYTES.length + iIndexOf4);
                        int iIndexOf6 = ByteStringKt.indexOf(bytes, PemDocumentKt.SUFFIX_BYTES, PemDocumentKt.END_BYTES.length + iIndexOf4);
                        if (iIndexOf6 == -1 || (iIndexOf5 != -1 && iIndexOf6 > iIndexOf5)) {
                            PemDocumentKt.throwPemMissingEndLabelSuffix();
                            throw new KotlinNothingValueException();
                        }
                        ByteString byteStringSubstring2 = bytes.substring(PemDocumentKt.END_BYTES.length + iIndexOf4, iIndexOf6);
                        if (!Intrinsics.areEqual(byteStringSubstring2, byteStringSubstring)) {
                            PemDocumentKt.throwPemBeginEndLabelMismatch(ByteStringKt.decodeToString(byteStringSubstring), ByteStringKt.decodeToString(byteStringSubstring2));
                            throw new KotlinNothingValueException();
                        }
                        if (iIndexOf5 == -1) {
                            int length = PemDocumentKt.SUFFIX_BYTES.length;
                        }
                        pemDocument = new PemDocument(PemLabel.m8802constructorimpl(ByteStringKt.decodeToString(byteStringSubstring)), Base64Kt.decodeToByteString(Base64.INSTANCE.getPem(), bytes, iIndexOf2 + 1, iIndexOf4), (DefaultConstructorMarker) (objArr == true ? 1 : 0));
                    } else {
                        PemDocumentKt.throwPemMissingEndLabel();
                        throw new KotlinNothingValueException();
                    }
                } else {
                    PemDocumentKt.throwPemMissingNewLineAfterBeginLabel();
                    throw new KotlinNothingValueException();
                }
            }
            if (pemDocument != null) {
                return pemDocument;
            }
            PemDocumentKt.throwPemMissingBeginLabel();
            throw new KotlinNothingValueException();
        }
    }
}
