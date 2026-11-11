package dev.whyoleg.cryptography.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import kotlin.Metadata;

/* JADX INFO: compiled from: Digest.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/SHA384;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SHA384 extends CryptographyAlgorithmId<Digest> {
    public static final SHA384 INSTANCE = new SHA384();

    private SHA384() {
        super("SHA-384");
    }
}
