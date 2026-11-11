package androidx.navigation;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NavGraph.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0086\n\u001a\u0017\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0086\u0002¨\u0006\u0007"}, d2 = {"get", "Landroidx/navigation/NavDestination;", "Landroidx/navigation/NavGraph;", "id", "", "contains", "", "navigation-common_release"}, k = 5, mv = {2, 0, 0}, xi = 48, xs = "androidx/navigation/NavGraphKt")
final /* synthetic */ class NavGraphKt__NavGraph_androidKt {
    public static final NavDestination get(NavGraph navGraph, int i) {
        Intrinsics.checkNotNullParameter(navGraph, "<this>");
        NavDestination navDestinationFindNode = navGraph.findNode(i);
        if (navDestinationFindNode != null) {
            return navDestinationFindNode;
        }
        throw new IllegalArgumentException("No destination for " + i + " was found in " + navGraph);
    }

    public static final boolean contains(NavGraph navGraph, int i) {
        Intrinsics.checkNotNullParameter(navGraph, "<this>");
        return navGraph.findNode(i) != null;
    }
}
