package dev.whyoleg.cryptography;

import dev.whyoleg.cryptography.random.CryptographyRandom;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CryptographySystem.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0086\u0080\u0004J\n\u0010\n\u001a\u00020\tH\u0086\u0080\u0004J \u0010\u000b\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0080\u0004J\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0010H\u0086\u0080\u0004J\u0012\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0086\u0080\u0004J\n\u0010\u0014\u001a\u00020\u0013H\u0086\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Ldev/whyoleg/cryptography/CryptographySystem;", "", "<init>", "()V", "impl", "Ldev/whyoleg/cryptography/CryptographySystemImpl;", "setDefaultProvider", "", "provider", "Ldev/whyoleg/cryptography/CryptographyProvider;", "getDefaultProvider", "registerProvider", "Lkotlin/Lazy;", "priority", "", "getRegisteredProviders", "", "setDefaultRandom", "random", "Ldev/whyoleg/cryptography/random/CryptographyRandom;", "getDefaultRandom", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CryptographySystem {
    public static final CryptographySystem INSTANCE;
    private static final CryptographySystemImpl impl;

    private CryptographySystem() {
    }

    static {
        CryptographySystem cryptographySystem = new CryptographySystem();
        INSTANCE = cryptographySystem;
        impl = new CryptographySystemImpl();
        CryptographySystem_jvmKt.loadProviders(cryptographySystem);
    }

    public final void setDefaultProvider(CryptographyProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        impl.setDefaultProvider(provider);
    }

    public final CryptographyProvider getDefaultProvider() {
        return impl.getDefaultProvider();
    }

    public final void registerProvider(Lazy<? extends CryptographyProvider> provider, int priority) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        impl.registerProvider(provider, priority);
    }

    public final List<CryptographyProvider> getRegisteredProviders() {
        return impl.getRegisteredProviders();
    }

    public final void setDefaultRandom(CryptographyRandom random) {
        Intrinsics.checkNotNullParameter(random, "random");
        impl.setDefaultRandom(random);
    }

    public final CryptographyRandom getDefaultRandom() {
        return impl.getDefaultRandom();
    }
}
