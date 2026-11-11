package dev.whyoleg.cryptography.serialization.asn1;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Der.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/DerImpl;", "Ldev/whyoleg/cryptography/serialization/asn1/Der;", "builder", "Ldev/whyoleg/cryptography/serialization/asn1/Der$Builder;", "<init>", "(Ldev/whyoleg/cryptography/serialization/asn1/Der$Builder;)V", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class DerImpl extends Der {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DerImpl(Der.Builder builder) {
        super(builder.getSerializersModule(), null);
        Intrinsics.checkNotNullParameter(builder, "builder");
    }
}
