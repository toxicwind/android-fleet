package dev.whyoleg.cryptography.providers.jdk;

import dev.whyoleg.cryptography.CryptographyProvider;
import dev.whyoleg.cryptography.random.CryptographyRandom;
import dev.whyoleg.cryptography.random.CryptographyRandom_jvmKt;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkCryptographyProvider.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0086\u0080\u0004\u001a\u0016\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004\u001a\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\u0080\u0004\u001a \u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\u0080\u0004\u001a \u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\u0080\u0004\u001a\u0016\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0087\u0080\u0004\u001a\u001e\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0087\u0080\u0004\u001a\u001e\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0087\u0080\u0004\u001a\u0010\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0082\u0080\u0004\"\u0015\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000\"\u001f\u0010\u0003\u001a\u00020\u0004*\u00020\u00058FX\u0086\u0084\n¢\u0006\f\u001a\u0004\b\b\u0010\t*\u0004\b\u0006\u0010\u0007¨\u0006\u0015"}, d2 = {"defaultProvider", "Lkotlin/Lazy;", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyProvider;", "JDK", "Ldev/whyoleg/cryptography/CryptographyProvider;", "Ldev/whyoleg/cryptography/CryptographyProvider$Companion;", "getJDK$delegate", "(Ldev/whyoleg/cryptography/CryptographyProvider$Companion;)Ljava/lang/Object;", "getJDK", "(Ldev/whyoleg/cryptography/CryptographyProvider$Companion;)Ldev/whyoleg/cryptography/CryptographyProvider;", "provider", "Ljava/security/Provider;", "cryptographyRandom", "Ldev/whyoleg/cryptography/random/CryptographyRandom;", "providerName", "", "secureRandom", "Ljava/security/SecureRandom;", "loadViaServiceLoader", "", "Ldev/whyoleg/cryptography/providers/jdk/DefaultJdkSecurityProvider;", "cryptography-provider-jdk"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class JdkCryptographyProviderKt {
    private static final Lazy<JdkCryptographyProvider> defaultProvider = LazyKt.lazy(new Function0() { // from class: dev.whyoleg.cryptography.providers.jdk.JdkCryptographyProviderKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return JdkCryptographyProviderKt.defaultProvider$lambda$0();
        }
    });

    static final JdkCryptographyProvider defaultProvider$lambda$0() {
        Lazy<Provider> provider;
        List list = CollectionsKt.toList(loadViaServiceLoader());
        if (list.size() > 1) {
            throw new IllegalStateException(("Multiple default JDK security providers found: " + list).toString());
        }
        DefaultJdkSecurityProvider defaultJdkSecurityProvider = (DefaultJdkSecurityProvider) CollectionsKt.singleOrNull(list);
        return new JdkCryptographyProvider((defaultJdkSecurityProvider == null || (provider = defaultJdkSecurityProvider.getProvider()) == null) ? null : provider.getValue());
    }

    public static final CryptographyProvider getJDK(CryptographyProvider.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return defaultProvider.getValue();
    }

    public static final CryptographyProvider JDK(CryptographyProvider.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return new JdkCryptographyProvider(null);
    }

    public static final CryptographyProvider JDK(CryptographyProvider.Companion companion, Provider provider) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(provider, "provider");
        return new JdkCryptographyProvider(provider);
    }

    public static /* synthetic */ CryptographyProvider JDK$default(CryptographyProvider.Companion companion, CryptographyRandom cryptographyRandom, int i, Object obj) {
        if ((i & 1) != 0) {
            cryptographyRandom = CryptographyRandom.INSTANCE;
        }
        return JDK(companion, cryptographyRandom);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Secure random should be provided via CryptographySystem.setDefaultRandom", replaceWith = @ReplaceWith(expression = "CryptographyProvider.JDK", imports = {}))
    public static final CryptographyProvider JDK(CryptographyProvider.Companion companion, CryptographyRandom cryptographyRandom) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(cryptographyRandom, "cryptographyRandom");
        return JDK(companion, CryptographyRandom_jvmKt.asSecureRandom(cryptographyRandom));
    }

    public static /* synthetic */ CryptographyProvider JDK$default(CryptographyProvider.Companion companion, Provider provider, CryptographyRandom cryptographyRandom, int i, Object obj) {
        if ((i & 2) != 0) {
            cryptographyRandom = CryptographyRandom.INSTANCE;
        }
        return JDK(companion, provider, cryptographyRandom);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Secure random should be provided via CryptographySystem.setDefaultRandom", replaceWith = @ReplaceWith(expression = "CryptographyProvider.JDK(provider)", imports = {}))
    public static final CryptographyProvider JDK(CryptographyProvider.Companion companion, Provider provider, CryptographyRandom cryptographyRandom) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(cryptographyRandom, "cryptographyRandom");
        return JDK(companion, provider, CryptographyRandom_jvmKt.asSecureRandom(cryptographyRandom));
    }

    public static /* synthetic */ CryptographyProvider JDK$default(CryptographyProvider.Companion companion, String str, CryptographyRandom cryptographyRandom, int i, Object obj) {
        if ((i & 2) != 0) {
            cryptographyRandom = CryptographyRandom.INSTANCE;
        }
        return JDK(companion, str, cryptographyRandom);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Overload with `provideName` is deprecated, use Security.getProvider(providerName) instead", replaceWith = @ReplaceWith(expression = "CryptographyProvider.JDK(checkNotNull(Security.getProvider(providerName)))", imports = {}))
    public static final CryptographyProvider JDK(CryptographyProvider.Companion companion, String providerName, CryptographyRandom cryptographyRandom) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(providerName, "providerName");
        Intrinsics.checkNotNullParameter(cryptographyRandom, "cryptographyRandom");
        return JDK(companion, providerName, CryptographyRandom_jvmKt.asSecureRandom(cryptographyRandom));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Secure random should be provided via CryptographySystem.setDefaultRandom", replaceWith = @ReplaceWith(expression = "CryptographyProvider.JDK(provider)", imports = {}))
    public static final CryptographyProvider JDK(CryptographyProvider.Companion companion, SecureRandom secureRandom) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(secureRandom, "secureRandom");
        return new JdkCryptographyProvider(null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Secure random should be provided via CryptographySystem.setDefaultRandom", replaceWith = @ReplaceWith(expression = "CryptographyProvider.JDK(provider)", imports = {}))
    public static final CryptographyProvider JDK(CryptographyProvider.Companion companion, Provider provider, SecureRandom secureRandom) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(secureRandom, "secureRandom");
        return new JdkCryptographyProvider(provider);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Overload with `provideName` is deprecated, use Security.getProvider(providerName) instead", replaceWith = @ReplaceWith(expression = "CryptographyProvider.JDK(checkNotNull(Security.getProvider(providerName)))", imports = {}))
    public static final CryptographyProvider JDK(CryptographyProvider.Companion companion, String providerName, SecureRandom secureRandom) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(providerName, "providerName");
        Intrinsics.checkNotNullParameter(secureRandom, "secureRandom");
        Provider provider = Security.getProvider(providerName);
        if (provider == null) {
            throw new IllegalStateException(("No provider with name: " + providerName).toString());
        }
        return new JdkCryptographyProvider(provider);
    }

    private static final Iterable<DefaultJdkSecurityProvider> loadViaServiceLoader() {
        return new JdkCryptographyProviderKt$loadViaServiceLoader$$inlined$Iterable$1();
    }
}
