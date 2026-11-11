package androidx.navigation;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: NavGraph.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\n\u001a'\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0005\u0018\u0001*\u00020\u0006*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0086\n\u001a$\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u0002H\u0005H\u0086\n¢\u0006\u0002\u0010\b\u001a\u0015\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\u0002\u001a'\u0010\t\u001a\u00020\n\"\n\b\u0000\u0010\u0005\u0018\u0001*\u00020\u0006*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0086\n\u001a$\u0010\t\u001a\u00020\n\"\b\b\u0000\u0010\u0005*\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u0002H\u0005H\u0086\u0002¢\u0006\u0002\u0010\u000b\u001a\u0015\u0010\f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0002H\u0086\n\u001a\u0015\u0010\u0010\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0001H\u0086\n¨\u0006\u0011"}, d2 = {"get", "Landroidx/navigation/NavDestination;", "Landroidx/navigation/NavGraph;", "route", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlin/reflect/KClass;", "(Landroidx/navigation/NavGraph;Ljava/lang/Object;)Landroidx/navigation/NavDestination;", "contains", "", "(Landroidx/navigation/NavGraph;Ljava/lang/Object;)Z", "plusAssign", "", "node", "other", "minusAssign", "navigation-common_release"}, k = 5, mv = {2, 0, 0}, xi = 48, xs = "androidx/navigation/NavGraphKt")
final /* synthetic */ class NavGraphKt__NavGraphKt {
    public static final NavDestination get(NavGraph navGraph, String route) {
        Intrinsics.checkNotNullParameter(navGraph, "<this>");
        Intrinsics.checkNotNullParameter(route, "route");
        NavDestination navDestinationFindNode = navGraph.findNode(route);
        if (navDestinationFindNode != null) {
            return navDestinationFindNode;
        }
        throw new IllegalArgumentException("No destination for " + route + " was found in " + navGraph);
    }

    public static final <T> NavDestination get(NavGraph navGraph, T route) {
        Intrinsics.checkNotNullParameter(navGraph, "<this>");
        Intrinsics.checkNotNullParameter(route, "route");
        NavDestination navDestinationFindNode = navGraph.findNode(route);
        if (navDestinationFindNode != null) {
            return navDestinationFindNode;
        }
        throw new IllegalArgumentException("No destination for " + route + " was found in " + navGraph);
    }

    public static final boolean contains(NavGraph navGraph, String route) {
        Intrinsics.checkNotNullParameter(navGraph, "<this>");
        Intrinsics.checkNotNullParameter(route, "route");
        return navGraph.findNode(route) != null;
    }

    public static final <T> boolean contains(NavGraph navGraph, T route) {
        Intrinsics.checkNotNullParameter(navGraph, "<this>");
        Intrinsics.checkNotNullParameter(route, "route");
        return navGraph.findNode(route) != null;
    }

    public static final void plusAssign(NavGraph navGraph, NavDestination node) {
        Intrinsics.checkNotNullParameter(navGraph, "<this>");
        Intrinsics.checkNotNullParameter(node, "node");
        navGraph.addDestination(node);
    }

    public static final void plusAssign(NavGraph navGraph, NavGraph other) {
        Intrinsics.checkNotNullParameter(navGraph, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        navGraph.addAll(other);
    }

    public static final void minusAssign(NavGraph navGraph, NavDestination node) {
        Intrinsics.checkNotNullParameter(navGraph, "<this>");
        Intrinsics.checkNotNullParameter(node, "node");
        navGraph.remove(node);
    }

    public static final /* synthetic */ <T> NavDestination get(NavGraph navGraph, KClass<T> route) {
        Intrinsics.checkNotNullParameter(navGraph, "<this>");
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        NavDestination navDestinationFindNode = navGraph.findNode(Reflection.getOrCreateKotlinClass(Object.class));
        if (navDestinationFindNode != null) {
            return navDestinationFindNode;
        }
        throw new IllegalArgumentException("No destination for " + route + " was found in " + navGraph);
    }

    public static final /* synthetic */ <T> boolean contains(NavGraph navGraph, KClass<T> route) {
        Intrinsics.checkNotNullParameter(navGraph, "<this>");
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return navGraph.findNode(Reflection.getOrCreateKotlinClass(Object.class)) != null;
    }
}
