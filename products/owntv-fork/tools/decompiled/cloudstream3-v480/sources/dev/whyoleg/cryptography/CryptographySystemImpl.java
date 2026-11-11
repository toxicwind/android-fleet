package dev.whyoleg.cryptography;

import androidx.exifinterface.media.ExifInterface;
import com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment;
import dev.whyoleg.cryptography.random.CryptographyRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;

/* JADX INFO: compiled from: CryptographySystem.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001:\u0001\u001aB\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0010\u001a\u00020\bH\u0086\u0080\u0004J\u0012\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\bH\u0086\u0080\u0004J\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0086\u0080\u0004J \u0010\u0015\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0016\u001a\u00020\u0006H\u0086\u0080\u0004J\u0012\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u000eH\u0086\u0080\u0004J\n\u0010\u0019\u001a\u00020\u000eH\u0086\u0080\u0004R!\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\n0\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Ldev/whyoleg/cryptography/CryptographySystemImpl;", "", "<init>", "()V", "registeredProviders", "", "", "Lkotlin/Lazy;", "Ldev/whyoleg/cryptography/CryptographyProvider;", "lazyRegisteredProviders", "", "defaultProvider", "lazyDefaultProvider", "defaultRandom", "Ldev/whyoleg/cryptography/random/CryptographyRandom;", "lazyDefaultRandom", "getDefaultProvider", "setDefaultProvider", "", "provider", "getRegisteredProviders", "registerProvider", "priority", "setDefaultRandom", "random", "getDefaultRandom", "CompositeProvider", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CryptographySystemImpl {
    private CryptographyProvider defaultProvider;
    private CryptographyRandom defaultRandom;
    private final Map<Integer, Lazy<CryptographyProvider>> registeredProviders = new LinkedHashMap();
    private final Lazy<List<CryptographyProvider>> lazyRegisteredProviders = LazyKt.lazy(new Function0() { // from class: dev.whyoleg.cryptography.CryptographySystemImpl$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CryptographySystemImpl.lazyRegisteredProviders$lambda$0(this.f$0);
        }
    });
    private final Lazy<CryptographyProvider> lazyDefaultProvider = LazyKt.lazy(new Function0() { // from class: dev.whyoleg.cryptography.CryptographySystemImpl$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CryptographySystemImpl.lazyDefaultProvider$lambda$0(this.f$0);
        }
    });
    private final Lazy<CryptographyRandom> lazyDefaultRandom = LazyKt.lazy(new Function0() { // from class: dev.whyoleg.cryptography.CryptographySystemImpl$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CryptographySystemImpl.lazyDefaultRandom$lambda$0(this.f$0);
        }
    });

    static final List lazyRegisteredProviders$lambda$0(CryptographySystemImpl cryptographySystemImpl) {
        List listSortedWith = CollectionsKt.sortedWith(cryptographySystemImpl.registeredProviders.entrySet(), new Comparator() { // from class: dev.whyoleg.cryptography.CryptographySystemImpl$lazyRegisteredProviders$lambda$0$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues((Integer) ((Map.Entry) t).getKey(), (Integer) ((Map.Entry) t2).getKey());
            }
        });
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSortedWith, 10));
        Iterator it = listSortedWith.iterator();
        while (it.hasNext()) {
            arrayList.add((CryptographyProvider) ((Lazy) ((Map.Entry) it.next()).getValue()).getValue());
        }
        return arrayList;
    }

    static final CryptographyProvider lazyDefaultProvider$lambda$0(CryptographySystemImpl cryptographySystemImpl) {
        CryptographyProvider cryptographyProvider = cryptographySystemImpl.defaultProvider;
        if (cryptographyProvider != null) {
            return cryptographyProvider;
        }
        List<CryptographyProvider> value = cryptographySystemImpl.lazyRegisteredProviders.getValue();
        int size = value.size();
        if (size == 0) {
            throw new IllegalStateException("No providers registered. Please provide a dependency or register provider explicitly".toString());
        }
        if (size == 1) {
            return (CryptographyProvider) CollectionsKt.first((List) value);
        }
        return new CompositeProvider(value);
    }

    static final CryptographyRandom lazyDefaultRandom$lambda$0(CryptographySystemImpl cryptographySystemImpl) {
        CryptographyRandom cryptographyRandom = cryptographySystemImpl.defaultRandom;
        return cryptographyRandom == null ? CryptographyRandom.INSTANCE : cryptographyRandom;
    }

    public final CryptographyProvider getDefaultProvider() {
        return this.lazyDefaultProvider.getValue();
    }

    public final void setDefaultProvider(CryptographyProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        if (this.lazyDefaultProvider.isInitialized()) {
            throw new IllegalStateException("Cannot set default provider after `getDefaultProvider` was called".toString());
        }
        if (this.defaultProvider != null) {
            throw new IllegalStateException("Default provider already set".toString());
        }
        this.defaultProvider = provider;
    }

    public final List<CryptographyProvider> getRegisteredProviders() {
        return this.lazyRegisteredProviders.getValue();
    }

    public final void registerProvider(Lazy<? extends CryptographyProvider> provider, int priority) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        if (priority < 0) {
            throw new IllegalArgumentException("Priority must be greater or equal to 0".toString());
        }
        if (this.registeredProviders.containsKey(Integer.valueOf(priority))) {
            throw new IllegalArgumentException(("Provider with priority " + priority + " already registered. Every registered provider should have unique priority.").toString());
        }
        if (this.lazyRegisteredProviders.isInitialized()) {
            throw new IllegalStateException("Cannot register provider after `getRegisteredProviders` was called".toString());
        }
        this.registeredProviders.put(Integer.valueOf(priority), provider);
    }

    public final void setDefaultRandom(CryptographyRandom random) {
        Intrinsics.checkNotNullParameter(random, "random");
        if (this.lazyDefaultRandom.isInitialized()) {
            throw new IllegalStateException("Cannot set default random after `getDefaultRandom` was called".toString());
        }
        if (this.defaultRandom != null) {
            throw new IllegalStateException("Default random already set".toString());
        }
        this.defaultRandom = random;
    }

    public final CryptographyRandom getDefaultRandom() {
        return this.lazyDefaultRandom.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: CryptographySystem.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\bF\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J)\u0010\n\u001a\u0004\u0018\u0001H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000eH\u0096\u0080\u0004¢\u0006\u0002\u0010\u000fR\u0015\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0006\u001a\u00020\u00078VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Ldev/whyoleg/cryptography/CryptographySystemImpl$CompositeProvider;", "Ldev/whyoleg/cryptography/CryptographyProvider;", QuickSearchFragment.PROVIDER_KEY, "", "<init>", "(Ljava/util/List;)V", "name", "", "getName", "()Ljava/lang/String;", "getOrNull", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "identifier", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "(Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final class CompositeProvider extends CryptographyProvider {
        private final List<CryptographyProvider> providers;

        /* JADX WARN: Multi-variable type inference failed */
        public CompositeProvider(List<? extends CryptographyProvider> providers) {
            Intrinsics.checkNotNullParameter(providers, "providers");
            this.providers = providers;
        }

        @Override // dev.whyoleg.cryptography.CryptographyProvider
        public String getName() {
            return CollectionsKt.joinToString$default(this.providers, ",", "Composite(", ")", 0, null, new PropertyReference1Impl() { // from class: dev.whyoleg.cryptography.CryptographySystemImpl$CompositeProvider$name$1
                @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
                public Object get(Object obj) {
                    return ((CryptographyProvider) obj).getName();
                }
            }, 24, null);
        }

        @Override // dev.whyoleg.cryptography.CryptographyProvider
        public <A extends CryptographyAlgorithm> A getOrNull(CryptographyAlgorithmId<A> identifier) {
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            Iterator<T> it = this.providers.iterator();
            while (it.hasNext()) {
                A a = (A) ((CryptographyProvider) it.next()).getOrNull(identifier);
                if (a != null) {
                    return a;
                }
            }
            return null;
        }
    }
}
