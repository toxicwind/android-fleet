package com.fleeksoft.charset.spi;

import com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CharsetServiceProvider.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcom/fleeksoft/charset/spi/CharsetProviderRegistry;", "", "<init>", "()V", QuickSearchFragment.PROVIDER_KEY, "", "Lcom/fleeksoft/charset/spi/CharsetServiceProviderLoader;", "getProviders", "()Ljava/util/List;", "register", "", "provider", "charset_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CharsetProviderRegistry {
    public static final CharsetProviderRegistry INSTANCE = new CharsetProviderRegistry();

    private CharsetProviderRegistry() {
    }

    public final List<CharsetServiceProviderLoader> getProviders() {
        throw new UnsupportedOperationException("This is not supported for JVM");
    }

    public final void register(CharsetServiceProviderLoader provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        throw new UnsupportedOperationException("This is not supported for JVM");
    }
}
