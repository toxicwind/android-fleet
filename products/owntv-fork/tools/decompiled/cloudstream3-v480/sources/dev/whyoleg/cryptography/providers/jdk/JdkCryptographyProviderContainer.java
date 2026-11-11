package dev.whyoleg.cryptography.providers.jdk;

import dev.whyoleg.cryptography.CryptographyProvider;
import dev.whyoleg.cryptography.CryptographyProviderContainer;
import kotlin.Lazy;
import kotlin.Metadata;

/* JADX INFO: compiled from: JdkCryptographyProvider.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyProviderContainer;", "Ldev/whyoleg/cryptography/CryptographyProviderContainer;", "<init>", "()V", "priority", "", "getPriority", "()I", "provider", "Lkotlin/Lazy;", "Ldev/whyoleg/cryptography/CryptographyProvider;", "getProvider", "()Lkotlin/Lazy;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkCryptographyProviderContainer implements CryptographyProviderContainer {
    @Override // dev.whyoleg.cryptography.CryptographyProviderContainer
    public int getPriority() {
        return 100;
    }

    @Override // dev.whyoleg.cryptography.CryptographyProviderContainer
    public Lazy<CryptographyProvider> getProvider() {
        return JdkCryptographyProviderKt.defaultProvider;
    }
}
