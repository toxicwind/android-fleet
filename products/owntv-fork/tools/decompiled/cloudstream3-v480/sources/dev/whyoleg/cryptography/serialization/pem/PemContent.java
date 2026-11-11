package dev.whyoleg.cryptography.serialization.pem;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: PemContent.kt */
/* JADX INFO: loaded from: classes7.dex */
@Deprecated(level = DeprecationLevel.ERROR, message = "Migrate to `PemDocument`")
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\bV\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nR\u0017\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\b\u001a\u00020\t8FX\u0086\u0084\b¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Ldev/whyoleg/cryptography/serialization/pem/PemContent;", "", "label", "Ldev/whyoleg/cryptography/serialization/pem/PemLabel;", "bytes", "", "<init>", "(Ljava/lang/String;[BLkotlin/jvm/internal/DefaultConstructorMarker;)V", "byteString", "Lkotlinx/io/bytestring/ByteString;", "(Ljava/lang/String;Lkotlinx/io/bytestring/ByteString;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getLabel-2EFq_Wg", "()Ljava/lang/String;", "Ljava/lang/String;", "getBytes", "()[B", "getByteString$annotations", "()V", "getByteString", "()Lkotlinx/io/bytestring/ByteString;", "cryptography-serialization-pem"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PemContent {
    private final byte[] bytes;
    private final String label;

    public /* synthetic */ PemContent(String str, ByteString byteString, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, byteString);
    }

    public /* synthetic */ PemContent(String str, byte[] bArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, bArr);
    }

    public static /* synthetic */ void getByteString$annotations() {
    }

    private PemContent(String label, byte[] bytes) {
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        this.label = label;
        this.bytes = bytes;
    }

    /* JADX INFO: renamed from: getLabel-2EFq_Wg, reason: not valid java name and from getter */
    public final String getLabel() {
        return this.label;
    }

    public final byte[] getBytes() {
        return this.bytes;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    private PemContent(String label, ByteString byteString) {
        this(label, ByteString.toByteArray$default(byteString, 0, 0, 3, null), (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
    }

    public final ByteString getByteString() {
        return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(this.bytes);
    }
}
