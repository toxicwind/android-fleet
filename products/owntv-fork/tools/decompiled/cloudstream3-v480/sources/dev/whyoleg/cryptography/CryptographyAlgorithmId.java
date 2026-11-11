package dev.whyoleg.cryptography;

import androidx.exifinterface.media.ExifInterface;
import dev.whyoleg.cryptography.CryptographyAlgorithm;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CryptographyAlgorithm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "", "name", "", "<init>", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class CryptographyAlgorithmId<A extends CryptographyAlgorithm> {
    private final String name;

    public CryptographyAlgorithmId(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }
}
