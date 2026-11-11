package dev.whyoleg.cryptography;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CryptographySystem.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\u0080\u0004\u001a\u0010\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0082\u0080\u0004¨\u0006\u0006"}, d2 = {"loadProviders", "", "Ldev/whyoleg/cryptography/CryptographySystem;", "loadViaServiceLoader", "", "Ldev/whyoleg/cryptography/CryptographyProviderContainer;", "cryptography-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CryptographySystem_jvmKt {
    public static final void loadProviders(CryptographySystem cryptographySystem) {
        Intrinsics.checkNotNullParameter(cryptographySystem, "<this>");
        for (CryptographyProviderContainer cryptographyProviderContainer : loadViaServiceLoader()) {
            cryptographySystem.registerProvider(cryptographyProviderContainer.getProvider(), cryptographyProviderContainer.getPriority());
        }
    }

    private static final Iterable<CryptographyProviderContainer> loadViaServiceLoader() {
        return new CryptographySystem_jvmKt$loadViaServiceLoader$$inlined$Iterable$1();
    }
}
