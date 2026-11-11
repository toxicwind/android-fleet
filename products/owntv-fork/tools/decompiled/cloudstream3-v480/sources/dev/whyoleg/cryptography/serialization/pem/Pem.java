package dev.whyoleg.cryptography.serialization.pem;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.ByteStringKt;

/* JADX INFO: compiled from: Pem.kt */
/* JADX INFO: loaded from: classes7.dex */
@Deprecated(level = DeprecationLevel.ERROR, message = "Migrate to `PemDocument`")
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0080\u0004J\u0012\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0080\u0004J\u0012\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0080\u0004J\u0012\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0005H\u0086\u0080\u0004J\u0012\u0010\f\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\tH\u0086\u0080\u0004J\u0012\u0010\f\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000bH\u0086\u0080\u0004¨\u0006\u0010"}, d2 = {"Ldev/whyoleg/cryptography/serialization/pem/Pem;", "", "<init>", "()V", "encodeToByteString", "Lkotlinx/io/bytestring/ByteString;", "content", "Ldev/whyoleg/cryptography/serialization/pem/PemContent;", "encodeToByteArray", "", "encode", "", "decode", "byteString", "bytes", TypedValues.Custom.S_STRING, "cryptography-serialization-pem"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Pem {
    public static final Pem INSTANCE = new Pem();

    private Pem() {
    }

    public final ByteString encodeToByteString(PemContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        return ByteStringKt.encodeToByteString(encode(content));
    }

    public final byte[] encodeToByteArray(PemContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        return StringsKt.encodeToByteArray(encode(content));
    }

    public final String encode(PemContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        return new PemDocument(content.getLabel(), content.getByteString(), (DefaultConstructorMarker) null).encodeToString();
    }

    public final PemContent decode(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        return decode(ByteStringKt.decodeToString(byteString));
    }

    public final PemContent decode(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return decode(StringsKt.decodeToString(bytes));
    }

    public final PemContent decode(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        PemDocument pemDocumentDecode = PemDocument.INSTANCE.decode(string);
        return new PemContent(pemDocumentDecode.getLabel(), pemDocumentDecode.getContent(), (DefaultConstructorMarker) null);
    }
}
