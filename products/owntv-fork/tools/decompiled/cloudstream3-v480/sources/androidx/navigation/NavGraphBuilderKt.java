package androidx.navigation;

import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"androidx/navigation/NavGraphBuilderKt__NavGraphBuilderKt", "androidx/navigation/NavGraphBuilderKt__NavGraphBuilder_androidKt"}, k = 4, mv = {2, 0, 0}, xi = 48)
public final class NavGraphBuilderKt {
    @Deprecated(message = "Use routes to build your NavGraph instead", replaceWith = @ReplaceWith(expression = "navigation(startDestination = startDestination.toString(), route = id.toString()) { builder.invoke() }", imports = {}))
    public static final NavGraph navigation(NavigatorProvider navigatorProvider, int i, int i2, Function1<? super NavGraphBuilder, Unit> function1) {
        return NavGraphBuilderKt__NavGraphBuilder_androidKt.navigation(navigatorProvider, i, i2, function1);
    }

    public static final NavGraph navigation(NavigatorProvider navigatorProvider, Object obj, KClass<?> kClass, Map<KType, NavType<?>> map, Function1<? super NavGraphBuilder, Unit> function1) {
        return NavGraphBuilderKt__NavGraphBuilderKt.navigation(navigatorProvider, obj, kClass, map, function1);
    }

    public static final NavGraph navigation(NavigatorProvider navigatorProvider, String str, String str2, Function1<? super NavGraphBuilder, Unit> function1) {
        return NavGraphBuilderKt__NavGraphBuilderKt.navigation(navigatorProvider, str, str2, function1);
    }

    public static final NavGraph navigation(NavigatorProvider navigatorProvider, KClass<?> kClass, KClass<?> kClass2, Map<KType, NavType<?>> map, Function1<? super NavGraphBuilder, Unit> function1) {
        return NavGraphBuilderKt__NavGraphBuilderKt.navigation(navigatorProvider, kClass, kClass2, map, function1);
    }

    @Deprecated(message = "Use routes to build your nested NavGraph instead", replaceWith = @ReplaceWith(expression = "navigation(startDestination = startDestination.toString(), route = id.toString()) { builder.invoke() }", imports = {}))
    public static final void navigation(NavGraphBuilder navGraphBuilder, int i, int i2, Function1<? super NavGraphBuilder, Unit> function1) {
        NavGraphBuilderKt__NavGraphBuilder_androidKt.navigation(navGraphBuilder, i, i2, function1);
    }

    public static final void navigation(NavGraphBuilder navGraphBuilder, String str, String str2, Function1<? super NavGraphBuilder, Unit> function1) {
        NavGraphBuilderKt__NavGraphBuilderKt.navigation(navGraphBuilder, str, str2, function1);
    }

    public static final <T> void navigation(NavGraphBuilder navGraphBuilder, KClass<T> kClass, Object obj, Map<KType, NavType<?>> map, Function1<? super NavGraphBuilder, Unit> function1) {
        NavGraphBuilderKt__NavGraphBuilderKt.navigation(navGraphBuilder, kClass, obj, map, function1);
    }

    public static final <T> void navigation(NavGraphBuilder navGraphBuilder, KClass<T> kClass, KClass<?> kClass2, Map<KType, NavType<?>> map, Function1<? super NavGraphBuilder, Unit> function1) {
        NavGraphBuilderKt__NavGraphBuilderKt.navigation(navGraphBuilder, (KClass) kClass, kClass2, map, function1);
    }
}
