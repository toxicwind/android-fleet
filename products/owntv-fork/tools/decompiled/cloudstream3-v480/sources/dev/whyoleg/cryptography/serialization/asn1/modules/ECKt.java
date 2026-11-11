package dev.whyoleg.cryptography.serialization.asn1.modules;

import com.google.android.gms.cast.MediaError;
import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import io.ktor.http.ContentType;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EC.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0019\u0010\u0013\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0004\"\u0019\u0010\u0015\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0004\"\u0019\u0010\u0017\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0004*J\b\u0007\u0010\u0005\"\u00020\u00062\u00020\u0006B<\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\"\b\n\u0012\u001e\b\u000bB\u001a\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\f\b\u000e\u0012\b\b\fJ\u0004\b\b(\u000f\u0012\n\b\u0010\u0012\u0006\b\n0\u00118\u0012¨\u0006\u0019"}, d2 = {"EC", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier$Companion;", "getEC", "(Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier$Companion;)Ljava/lang/String;", "EcKeyAlgorithmIdentifier", "Ldev/whyoleg/cryptography/serialization/asn1/modules/EcAlgorithmIdentifier;", "Lkotlin/Deprecated;", ContentType.Message.TYPE, "Use EcAlgorithmIdentifier instead", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "EcAlgorithmIdentifier", "imports", "dev.whyoleg.cryptography.serialization.asn1.modules.EcAlgorithmIdentifier", "level", "Lkotlin/DeprecationLevel;", MediaError.ERROR_TYPE_ERROR, "secp256r1", "getSecp256r1", "secp384r1", "getSecp384r1", "secp521r1", "getSecp521r1", "cryptography-serialization-asn1-modules"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ECKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use EcAlgorithmIdentifier instead", replaceWith = @ReplaceWith(expression = "EcAlgorithmIdentifier", imports = {"dev.whyoleg.cryptography.serialization.asn1.modules.EcAlgorithmIdentifier"}))
    public static /* synthetic */ void EcKeyAlgorithmIdentifier$annotations() {
    }

    public static final String getEC(ObjectIdentifier.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ObjectIdentifier.m8756constructorimpl("1.2.840.10045.2.1");
    }

    public static final String getSecp256r1(ObjectIdentifier.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ObjectIdentifier.m8756constructorimpl("1.2.840.10045.3.1.7");
    }

    public static final String getSecp384r1(ObjectIdentifier.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ObjectIdentifier.m8756constructorimpl("1.3.132.0.34");
    }

    public static final String getSecp521r1(ObjectIdentifier.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ObjectIdentifier.m8756constructorimpl("1.3.132.0.35");
    }
}
