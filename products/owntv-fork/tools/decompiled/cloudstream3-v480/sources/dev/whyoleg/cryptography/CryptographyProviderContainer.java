package dev.whyoleg.cryptography;

import kotlin.Lazy;
import kotlin.Metadata;

/* JADX INFO: compiled from: CryptographySystem.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001R\u0013\u0010\u0002\u001a\u00020\u0003X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/CryptographyProviderContainer;", "", "priority", "", "getPriority", "()I", "provider", "Lkotlin/Lazy;", "Ldev/whyoleg/cryptography/CryptographyProvider;", "getProvider", "()Lkotlin/Lazy;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface CryptographyProviderContainer {
    int getPriority();

    Lazy<CryptographyProvider> getProvider();
}
