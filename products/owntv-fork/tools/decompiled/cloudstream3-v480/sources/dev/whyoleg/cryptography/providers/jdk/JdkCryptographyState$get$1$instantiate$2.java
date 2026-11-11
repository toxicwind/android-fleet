package dev.whyoleg.cryptography.providers.jdk;

import java.security.Provider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: JdkCryptographyState.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class JdkCryptographyState$get$1$instantiate$2<T> implements Function0<T> {
    final /* synthetic */ String $algorithm;
    final /* synthetic */ Function2<String, Provider, T> $fromProvider;
    final /* synthetic */ Provider $provider;

    /* JADX WARN: Multi-variable type inference failed */
    public JdkCryptographyState$get$1$instantiate$2(Function2<? super String, ? super Provider, ? extends T> function2, String str, Provider provider) {
        this.$fromProvider = function2;
        this.$algorithm = str;
        this.$provider = provider;
    }

    @Override // kotlin.jvm.functions.Function0
    public final T invoke() {
        return this.$fromProvider.invoke(this.$algorithm, this.$provider);
    }
}
