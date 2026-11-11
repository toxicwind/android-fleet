package androidx.navigation;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* JADX INFO: compiled from: NavDeepLinkDslBuilder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"androidx/navigation/NavDeepLinkDslBuilderKt__NavDeepLinkDslBuilderKt"}, k = 4, mv = {2, 0, 0}, xi = 48)
public final class NavDeepLinkDslBuilderKt {
    public static final NavDeepLink navDeepLink(Function1<? super NavDeepLinkDslBuilder, Unit> function1) {
        return NavDeepLinkDslBuilderKt__NavDeepLinkDslBuilderKt.navDeepLink(function1);
    }

    public static final <T> NavDeepLink navDeepLink(KClass<T> kClass, String str, Map<KType, NavType<?>> map, Function1<? super NavDeepLinkDslBuilder, Unit> function1) {
        return NavDeepLinkDslBuilderKt__NavDeepLinkDslBuilderKt.navDeepLink(kClass, str, map, function1);
    }

    public static final <T> NavDeepLink navDeepLink(KClass<T> kClass, String str, Function1<? super NavDeepLinkDslBuilder, Unit> function1) {
        return NavDeepLinkDslBuilderKt__NavDeepLinkDslBuilderKt.navDeepLink(kClass, str, function1);
    }
}
