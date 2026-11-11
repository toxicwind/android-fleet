package androidx.navigation;

import android.content.Intent;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"androidx/navigation/NavControllerKt__NavControllerKt", "androidx/navigation/NavControllerKt__NavController_androidKt"}, k = 4, mv = {2, 0, 0}, xi = 48)
public final class NavControllerKt {
    public static final NavDeepLinkRequest NavDeepLinkRequest(Intent intent) {
        return NavControllerKt__NavController_androidKt.NavDeepLinkRequest(intent);
    }

    @Deprecated(message = "Use routes to create your NavGraph instead", replaceWith = @ReplaceWith(expression = "createGraph(startDestination = startDestination.toString(), route = id.toString()) { builder.invoke() }", imports = {}))
    public static final NavGraph createGraph(NavController navController, int i, int i2, Function1<? super NavGraphBuilder, Unit> function1) {
        return NavControllerKt__NavController_androidKt.createGraph(navController, i, i2, function1);
    }

    public static final NavGraph createGraph(NavController navController, Object obj, KClass<?> kClass, Map<KType, NavType<?>> map, Function1<? super NavGraphBuilder, Unit> function1) {
        return NavControllerKt__NavControllerKt.createGraph(navController, obj, kClass, map, function1);
    }

    public static final NavGraph createGraph(NavController navController, String str, String str2, Function1<? super NavGraphBuilder, Unit> function1) {
        return NavControllerKt__NavControllerKt.createGraph(navController, str, str2, function1);
    }

    public static final NavGraph createGraph(NavController navController, KClass<?> kClass, KClass<?> kClass2, Map<KType, NavType<?>> map, Function1<? super NavGraphBuilder, Unit> function1) {
        return NavControllerKt__NavControllerKt.createGraph(navController, kClass, kClass2, map, function1);
    }
}
