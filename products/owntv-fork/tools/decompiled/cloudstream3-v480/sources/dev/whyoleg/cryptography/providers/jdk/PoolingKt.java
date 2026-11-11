package dev.whyoleg.cryptography.providers.jdk;

import kotlin.Metadata;

/* JADX INFO: compiled from: pooling.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\b\n\u0000\"\u000f\u0010\u0000\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0002"}, d2 = {"maxPooled", "", "cryptography-provider-jdk"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PoolingKt {
    private static final int maxPooled = Runtime.getRuntime().availableProcessors() + 2;
}
