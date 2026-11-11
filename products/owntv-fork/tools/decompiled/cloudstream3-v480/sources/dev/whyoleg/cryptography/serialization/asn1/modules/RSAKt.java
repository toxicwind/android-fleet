package dev.whyoleg.cryptography.serialization.asn1.modules;

import com.google.android.gms.cast.MediaError;
import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import io.ktor.http.ContentType;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RSA.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004*J\b\u0007\u0010\u0005\"\u00020\u00062\u00020\u0006B<\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\"\b\n\u0012\u001e\b\u000bB\u001a\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\f\b\u000e\u0012\b\b\fJ\u0004\b\b(\u000f\u0012\n\b\u0010\u0012\u0006\b\n0\u00118\u0012¨\u0006\u0013"}, d2 = {"RSA", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier$Companion;", "getRSA", "(Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier$Companion;)Ljava/lang/String;", "RsaKeyAlgorithmIdentifier", "Ldev/whyoleg/cryptography/serialization/asn1/modules/RsaAlgorithmIdentifier;", "Lkotlin/Deprecated;", ContentType.Message.TYPE, "Use RsaAlgorithmIdentifier instead", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "RsaAlgorithmIdentifier", "imports", "dev.whyoleg.cryptography.serialization.asn1.modules.RsaAlgorithmIdentifier", "level", "Lkotlin/DeprecationLevel;", MediaError.ERROR_TYPE_ERROR, "cryptography-serialization-asn1-modules"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class RSAKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use RsaAlgorithmIdentifier instead", replaceWith = @ReplaceWith(expression = "RsaAlgorithmIdentifier", imports = {"dev.whyoleg.cryptography.serialization.asn1.modules.RsaAlgorithmIdentifier"}))
    public static /* synthetic */ void RsaKeyAlgorithmIdentifier$annotations() {
    }

    public static final String getRSA(ObjectIdentifier.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ObjectIdentifier.m8756constructorimpl("1.2.840.113549.1.1.1");
    }
}
