package dev.whyoleg.cryptography.providers.jdk;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import java.security.Provider;
import kotlin.Lazy;
import kotlin.Metadata;

/* JADX INFO: compiled from: JdkCryptographyProvider.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001R\u001d\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/DefaultJdkSecurityProvider;", "", "provider", "Lkotlin/Lazy;", "Ljava/security/Provider;", "Ldev/whyoleg/cryptography/providers/jdk/JProvider;", "getProvider", "()Lkotlin/Lazy;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface DefaultJdkSecurityProvider {
    Lazy<Provider> getProvider();
}
