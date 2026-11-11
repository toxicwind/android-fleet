package androidx.navigation;

import kotlin.Metadata;
import kotlin.reflect.KClass;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"androidx/navigation/NavigatorProviderKt__NavigatorProviderKt", "androidx/navigation/NavigatorProviderKt__NavigatorProvider_androidKt"}, k = 4, mv = {2, 0, 0}, xi = 48)
public final class NavigatorProviderKt {
    public static final <T extends Navigator<? extends NavDestination>> T get(NavigatorProvider navigatorProvider, String str) {
        return (T) NavigatorProviderKt__NavigatorProviderKt.get(navigatorProvider, str);
    }

    public static final <T extends Navigator<? extends NavDestination>> T get(NavigatorProvider navigatorProvider, KClass<T> kClass) {
        return (T) NavigatorProviderKt__NavigatorProvider_androidKt.get(navigatorProvider, kClass);
    }

    public static final void plusAssign(NavigatorProvider navigatorProvider, Navigator<? extends NavDestination> navigator) {
        NavigatorProviderKt__NavigatorProviderKt.plusAssign(navigatorProvider, navigator);
    }

    public static final Navigator<? extends NavDestination> set(NavigatorProvider navigatorProvider, String str, Navigator<? extends NavDestination> navigator) {
        return NavigatorProviderKt__NavigatorProviderKt.set(navigatorProvider, str, navigator);
    }
}
