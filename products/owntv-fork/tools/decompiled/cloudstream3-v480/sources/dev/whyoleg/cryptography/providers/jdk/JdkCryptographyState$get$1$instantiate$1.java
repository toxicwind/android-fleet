package dev.whyoleg.cryptography.providers.jdk;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: JdkCryptographyState.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class JdkCryptographyState$get$1$instantiate$1<T> implements Function0<T> {
    final /* synthetic */ String $algorithm;
    final /* synthetic */ Function1<String, T> $fromDefault;

    /* JADX WARN: Multi-variable type inference failed */
    public JdkCryptographyState$get$1$instantiate$1(Function1<? super String, ? extends T> function1, String str) {
        this.$fromDefault = function1;
        this.$algorithm = str;
    }

    @Override // kotlin.jvm.functions.Function0
    public final T invoke() {
        return this.$fromDefault.invoke(this.$algorithm);
    }
}
