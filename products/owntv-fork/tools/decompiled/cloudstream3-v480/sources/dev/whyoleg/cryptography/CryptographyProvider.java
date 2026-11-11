package dev.whyoleg.cryptography;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: CryptographyProvider.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u0000 \u00102\u00020\u0001:\u0002\u000f\u0010B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\b\u001a\u0004\u0018\u0001H\t\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH¦\u0080\u0004¢\u0006\u0002\u0010\rJ'\u0010\u000e\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH\u0096\u0080\u0004¢\u0006\u0002\u0010\rR\u0013\u0010\u0004\u001a\u00020\u0005X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Ldev/whyoleg/cryptography/CryptographyProvider;", "", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "getOrNull", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "identifier", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "(Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "get", "Registry", "Companion", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class CryptographyProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public abstract String getName();

    public abstract <A extends CryptographyAlgorithm> A getOrNull(CryptographyAlgorithmId<A> identifier);

    public <A extends CryptographyAlgorithm> A get(CryptographyAlgorithmId<A> identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        A a = (A) getOrNull(identifier);
        if (a != null) {
            return a;
        }
        throw new IllegalStateException("Algorithm not found: " + identifier);
    }

    /* JADX INFO: compiled from: CryptographyProvider.kt */
    @CryptographyProviderApi
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0087\u0080\u0004J\u0018\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0087\u0080\u0004R!\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\b¢\u0006\f\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/CryptographyProvider$Registry;", "", "<init>", "()V", "registeredProviders", "Lkotlin/sequences/Sequence;", "Ldev/whyoleg/cryptography/CryptographyProvider;", "getRegisteredProviders$annotations", "getRegisteredProviders", "()Lkotlin/sequences/Sequence;", "registerProvider", "", "provider", "Lkotlin/Lazy;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced by CryptographySystem")
    public static final class Registry {
        public static final Registry INSTANCE = new Registry();

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use CryptographySystem::getRegisteredProviders", replaceWith = @ReplaceWith(expression = "CryptographySystem.getRegisteredProviders().asSequence()", imports = {"dev.whyoleg.cryptography.CryptographySystem"}))
        public static /* synthetic */ void getRegisteredProviders$annotations() {
        }

        private Registry() {
        }

        public final Sequence<CryptographyProvider> getRegisteredProviders() {
            return CollectionsKt.asSequence(CryptographySystem.INSTANCE.getRegisteredProviders());
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use CryptographySystem::registerProvider", replaceWith = @ReplaceWith(expression = "CryptographySystem.registerProvider(lazyOf(provider), 0)", imports = {"dev.whyoleg.cryptography.CryptographySystem"}))
        public final void registerProvider(CryptographyProvider provider) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            CryptographySystem.INSTANCE.registerProvider(LazyKt.lazyOf(provider), 0);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use CryptographySystem::registerProvider", replaceWith = @ReplaceWith(expression = "CryptographySystem.registerProvider(provider, 0)", imports = {"dev.whyoleg.cryptography.CryptographySystem"}))
        public final void registerProvider(Lazy<? extends CryptographyProvider> provider) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            CryptographySystem.INSTANCE.registerProvider(provider, 0);
        }
    }

    /* JADX INFO: compiled from: CryptographyProvider.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/CryptographyProvider$Companion;", "", "<init>", "()V", "Default", "Ldev/whyoleg/cryptography/CryptographyProvider;", "getDefault", "()Ldev/whyoleg/cryptography/CryptographyProvider;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CryptographyProvider getDefault() {
            return CryptographySystem.INSTANCE.getDefaultProvider();
        }
    }
}
