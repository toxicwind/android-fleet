package androidx.navigation;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.core.app.TaskStackBuilder;
import androidx.exifinterface.media.ExifInterface;
import androidx.navigation.internal.NavContext;
import androidx.savedstate.SavedStateReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: NavDeepLinkBuilder.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u000234B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bJ\u0018\u0010\u0019\u001a\u00020\u00002\u0010\u0010\u001a\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000e0\u001bJ\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\u00002\b\b\u0001\u0010\u001f\u001a\u00020 J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0012J$\u0010\"\u001a\u00020\u00002\b\b\u0001\u0010#\u001a\u00020 2\u0010\b\u0002\u0010$\u001a\n\u0018\u00010\u0017j\u0004\u0018\u0001`\u0018H\u0007J\"\u0010\"\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0010\b\u0002\u0010$\u001a\n\u0018\u00010\u0017j\u0004\u0018\u0001`\u0018H\u0007J$\u0010'\u001a\u00020\u00002\b\b\u0001\u0010#\u001a\u00020 2\u0010\b\u0002\u0010$\u001a\n\u0018\u00010\u0017j\u0004\u0018\u0001`\u0018H\u0007J\"\u0010'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020&2\u0010\b\u0002\u0010$\u001a\n\u0018\u00010\u0017j\u0004\u0018\u0001`\u0018H\u0007J\u0014\u0010)\u001a\u0004\u0018\u00010*2\b\b\u0001\u0010#\u001a\u00020 H\u0002J\b\u0010+\u001a\u00020,H\u0002J\b\u0010-\u001a\u00020,H\u0002J\u0016\u0010.\u001a\u00020\u00002\u000e\u0010$\u001a\n\u0018\u00010\u0017j\u0004\u0018\u0001`\u0018J\u0006\u0010/\u001a\u000200J\u0006\u00101\u001a\u000202R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0018\u00010\u0017j\u0004\u0018\u0001`\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Landroidx/navigation/NavDeepLinkBuilder;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "navController", "Landroidx/navigation/NavController;", "(Landroidx/navigation/NavController;)V", "navContext", "Landroidx/navigation/internal/NavContext;", "getNavContext$navigation_runtime_release", "()Landroidx/navigation/internal/NavContext;", "activity", "Landroid/app/Activity;", "intent", "Landroid/content/Intent;", "graph", "Landroidx/navigation/NavGraph;", "destinations", "", "Landroidx/navigation/NavDeepLinkBuilder$DeepLinkDestination;", "globalArgs", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "setComponentName", "activityClass", "Ljava/lang/Class;", "componentName", "Landroid/content/ComponentName;", "setGraph", "navGraphId", "", "navGraph", "setDestination", "destId", "args", "destRoute", "", "addDestination", "route", "findDestination", "Landroidx/navigation/NavDestination;", "verifyAllDestinations", "", "fillInIntent", "setArguments", "createTaskStackBuilder", "Landroidx/core/app/TaskStackBuilder;", "createPendingIntent", "Landroid/app/PendingIntent;", "DeepLinkDestination", "PermissiveNavigatorProvider", "navigation-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NavDeepLinkBuilder {
    private final Activity activity;
    private final Context context;
    private final List<DeepLinkDestination> destinations;
    private Bundle globalArgs;
    private NavGraph graph;
    private final Intent intent;
    private final NavContext navContext;

    public final NavDeepLinkBuilder addDestination(int i) {
        return addDestination$default(this, i, (Bundle) null, 2, (Object) null);
    }

    public final NavDeepLinkBuilder addDestination(String route) {
        Intrinsics.checkNotNullParameter(route, "route");
        return addDestination$default(this, route, (Bundle) null, 2, (Object) null);
    }

    public final NavDeepLinkBuilder setDestination(int i) {
        return setDestination$default(this, i, (Bundle) null, 2, (Object) null);
    }

    public final NavDeepLinkBuilder setDestination(String destRoute) {
        Intrinsics.checkNotNullParameter(destRoute, "destRoute");
        return setDestination$default(this, destRoute, (Bundle) null, 2, (Object) null);
    }

    public NavDeepLinkBuilder(Context context) {
        Intent launchIntentForPackage;
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.navContext = new NavContext(context);
        Activity activity = (Activity) SequencesKt.firstOrNull(SequencesKt.mapNotNull(SequencesKt.generateSequence(context, (Function1<? super Context, ? extends Context>) new Function1() { // from class: androidx.navigation.NavDeepLinkBuilder$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavDeepLinkBuilder.activity$lambda$0((Context) obj);
            }
        }), new Function1() { // from class: androidx.navigation.NavDeepLinkBuilder$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavDeepLinkBuilder.activity$lambda$1((Context) obj);
            }
        }));
        this.activity = activity;
        if (activity != null) {
            launchIntentForPackage = new Intent(context, activity.getClass());
        } else {
            launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            if (launchIntentForPackage == null) {
                launchIntentForPackage = new Intent();
            }
        }
        launchIntentForPackage.addFlags(268468224);
        this.intent = launchIntentForPackage;
        this.destinations = new ArrayList();
    }

    /* JADX INFO: renamed from: getNavContext$navigation_runtime_release, reason: from getter */
    public final NavContext getNavContext() {
        return this.navContext;
    }

    /* JADX INFO: compiled from: NavDeepLinkBuilder.android.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/navigation/NavDeepLinkBuilder$DeepLinkDestination;", "", "destinationId", "", "arguments", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "<init>", "(ILandroid/os/Bundle;)V", "getDestinationId", "()I", "getArguments", "()Landroid/os/Bundle;", "navigation-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class DeepLinkDestination {
        private final Bundle arguments;
        private final int destinationId;

        public DeepLinkDestination(int i, Bundle bundle) {
            this.destinationId = i;
            this.arguments = bundle;
        }

        public final Bundle getArguments() {
            return this.arguments;
        }

        public final int getDestinationId() {
            return this.destinationId;
        }
    }

    static final Context activity$lambda$0(Context it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ContextWrapper contextWrapper = it instanceof ContextWrapper ? (ContextWrapper) it : null;
        if (contextWrapper != null) {
            return contextWrapper.getBaseContext();
        }
        return null;
    }

    static final Activity activity$lambda$1(Context it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it instanceof Activity) {
            return (Activity) it;
        }
        return null;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NavDeepLinkBuilder(NavController navController) {
        this(navController.getContext());
        Intrinsics.checkNotNullParameter(navController, "navController");
        this.graph = navController.getGraph();
    }

    public final NavDeepLinkBuilder setComponentName(Class<? extends Activity> activityClass) {
        Intrinsics.checkNotNullParameter(activityClass, "activityClass");
        return setComponentName(new ComponentName(this.context, activityClass));
    }

    public final NavDeepLinkBuilder setComponentName(ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        this.intent.setComponent(componentName);
        return this;
    }

    public final NavDeepLinkBuilder setGraph(int navGraphId) {
        return setGraph(new NavInflater(this.context, new PermissiveNavigatorProvider()).inflate(navGraphId));
    }

    public final NavDeepLinkBuilder setGraph(NavGraph navGraph) {
        Intrinsics.checkNotNullParameter(navGraph, "navGraph");
        this.graph = navGraph;
        verifyAllDestinations();
        return this;
    }

    public static /* synthetic */ NavDeepLinkBuilder setDestination$default(NavDeepLinkBuilder navDeepLinkBuilder, int i, Bundle bundle, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bundle = null;
        }
        return navDeepLinkBuilder.setDestination(i, bundle);
    }

    public final NavDeepLinkBuilder setDestination(int destId, Bundle args) {
        this.destinations.clear();
        this.destinations.add(new DeepLinkDestination(destId, args));
        if (this.graph != null) {
            verifyAllDestinations();
        }
        return this;
    }

    public static /* synthetic */ NavDeepLinkBuilder setDestination$default(NavDeepLinkBuilder navDeepLinkBuilder, String str, Bundle bundle, int i, Object obj) {
        if ((i & 2) != 0) {
            bundle = null;
        }
        return navDeepLinkBuilder.setDestination(str, bundle);
    }

    public final NavDeepLinkBuilder setDestination(String destRoute, Bundle args) {
        Intrinsics.checkNotNullParameter(destRoute, "destRoute");
        this.destinations.clear();
        this.destinations.add(new DeepLinkDestination(NavDestination.INSTANCE.createRoute(destRoute).hashCode(), args));
        if (this.graph != null) {
            verifyAllDestinations();
        }
        return this;
    }

    public static /* synthetic */ NavDeepLinkBuilder addDestination$default(NavDeepLinkBuilder navDeepLinkBuilder, int i, Bundle bundle, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bundle = null;
        }
        return navDeepLinkBuilder.addDestination(i, bundle);
    }

    public final NavDeepLinkBuilder addDestination(int destId, Bundle args) {
        this.destinations.add(new DeepLinkDestination(destId, args));
        if (this.graph != null) {
            verifyAllDestinations();
        }
        return this;
    }

    public static /* synthetic */ NavDeepLinkBuilder addDestination$default(NavDeepLinkBuilder navDeepLinkBuilder, String str, Bundle bundle, int i, Object obj) {
        if ((i & 2) != 0) {
            bundle = null;
        }
        return navDeepLinkBuilder.addDestination(str, bundle);
    }

    public final NavDeepLinkBuilder addDestination(String route, Bundle args) {
        Intrinsics.checkNotNullParameter(route, "route");
        this.destinations.add(new DeepLinkDestination(NavDestination.INSTANCE.createRoute(route).hashCode(), args));
        if (this.graph != null) {
            verifyAllDestinations();
        }
        return this;
    }

    private final NavDestination findDestination(int destId) {
        ArrayDeque arrayDeque = new ArrayDeque();
        NavGraph navGraph = this.graph;
        Intrinsics.checkNotNull(navGraph);
        arrayDeque.add(navGraph);
        while (!arrayDeque.isEmpty()) {
            NavDestination navDestination = (NavDestination) arrayDeque.removeFirst();
            if (navDestination.getId() == destId) {
                return navDestination;
            }
            if (navDestination instanceof NavGraph) {
                Iterator<NavDestination> it = ((NavGraph) navDestination).iterator();
                while (it.hasNext()) {
                    arrayDeque.add(it.next());
                }
            }
        }
        return null;
    }

    private final void verifyAllDestinations() {
        Iterator<DeepLinkDestination> it = this.destinations.iterator();
        while (it.hasNext()) {
            int destinationId = it.next().getDestinationId();
            if (findDestination(destinationId) == null) {
                throw new IllegalArgumentException("Navigation destination " + NavDestination.INSTANCE.getDisplayName(this.navContext, destinationId) + " cannot be found in the navigation graph " + this.graph);
            }
        }
    }

    private final void fillInIntent() {
        ArrayList arrayList = new ArrayList();
        ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
        NavDestination navDestination = null;
        for (DeepLinkDestination deepLinkDestination : this.destinations) {
            int destinationId = deepLinkDestination.getDestinationId();
            Bundle arguments = deepLinkDestination.getArguments();
            NavDestination navDestinationFindDestination = findDestination(destinationId);
            if (navDestinationFindDestination == null) {
                throw new IllegalArgumentException("Navigation destination " + NavDestination.INSTANCE.getDisplayName(this.navContext, destinationId) + " cannot be found in the navigation graph " + this.graph);
            }
            for (int i : navDestinationFindDestination.buildDeepLinkIds(navDestination)) {
                arrayList.add(Integer.valueOf(i));
                arrayList2.add(arguments);
            }
            navDestination = navDestinationFindDestination;
        }
        this.intent.putExtra(NavController.KEY_DEEP_LINK_IDS, CollectionsKt.toIntArray(arrayList));
        this.intent.putParcelableArrayListExtra(NavController.KEY_DEEP_LINK_ARGS, arrayList2);
    }

    public final NavDeepLinkBuilder setArguments(Bundle args) {
        this.globalArgs = args;
        this.intent.putExtra(NavController.KEY_DEEP_LINK_EXTRAS, args);
        return this;
    }

    public final TaskStackBuilder createTaskStackBuilder() {
        if (this.graph == null) {
            throw new IllegalStateException("You must call setGraph() before constructing the deep link".toString());
        }
        if (this.destinations.isEmpty()) {
            throw new IllegalStateException("You must call setDestination() or addDestination() before constructing the deep link".toString());
        }
        fillInIntent();
        TaskStackBuilder taskStackBuilderAddNextIntentWithParentStack = TaskStackBuilder.create(this.context).addNextIntentWithParentStack(new Intent(this.intent));
        Intrinsics.checkNotNullExpressionValue(taskStackBuilderAddNextIntentWithParentStack, "addNextIntentWithParentStack(...)");
        int intentCount = taskStackBuilderAddNextIntentWithParentStack.getIntentCount();
        for (int i = 0; i < intentCount; i++) {
            Intent intentEditIntentAt = taskStackBuilderAddNextIntentWithParentStack.editIntentAt(i);
            if (intentEditIntentAt != null) {
                intentEditIntentAt.putExtra(NavController.KEY_DEEP_LINK_INTENT, this.intent);
            }
        }
        return taskStackBuilderAddNextIntentWithParentStack;
    }

    public final PendingIntent createPendingIntent() {
        Bundle bundle = this.globalArgs;
        int iM7933contentDeepHashCodeimpl = bundle != null ? SavedStateReader.m7933contentDeepHashCodeimpl(SavedStateReader.m7930constructorimpl(bundle)) : 0;
        for (DeepLinkDestination deepLinkDestination : this.destinations) {
            iM7933contentDeepHashCodeimpl = (iM7933contentDeepHashCodeimpl * 31) + deepLinkDestination.getDestinationId();
            Bundle arguments = deepLinkDestination.getArguments();
            Integer numValueOf = arguments != null ? Integer.valueOf(SavedStateReader.m7933contentDeepHashCodeimpl(SavedStateReader.m7930constructorimpl(arguments))) : null;
            if (numValueOf != null) {
                iM7933contentDeepHashCodeimpl = (iM7933contentDeepHashCodeimpl * 31) + numValueOf.intValue();
            }
        }
        PendingIntent pendingIntent = createTaskStackBuilder().getPendingIntent(iM7933contentDeepHashCodeimpl, 201326592);
        Intrinsics.checkNotNull(pendingIntent);
        return pendingIntent;
    }

    /* JADX INFO: compiled from: NavDeepLinkBuilder.android.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0007\u001a\u0002H\b\"\u0010\b\u0000\u0010\b*\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/navigation/NavDeepLinkBuilder$PermissiveNavigatorProvider;", "Landroidx/navigation/NavigatorProvider;", "<init>", "()V", "mDestNavigator", "Landroidx/navigation/Navigator;", "Landroidx/navigation/NavDestination;", "getNavigator", ExifInterface.GPS_DIRECTION_TRUE, "name", "", "(Ljava/lang/String;)Landroidx/navigation/Navigator;", "navigation-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class PermissiveNavigatorProvider extends NavigatorProvider {
        private final Navigator<NavDestination> mDestNavigator = new Navigator<NavDestination>() { // from class: androidx.navigation.NavDeepLinkBuilder$PermissiveNavigatorProvider$mDestNavigator$1
            @Override // androidx.navigation.Navigator
            public NavDestination createDestination() {
                return new NavDestination("permissive");
            }

            @Override // androidx.navigation.Navigator
            public NavDestination navigate(NavDestination destination, Bundle args, NavOptions navOptions, Navigator.Extras navigatorExtras) {
                Intrinsics.checkNotNullParameter(destination, "destination");
                throw new IllegalStateException("navigate is not supported");
            }

            @Override // androidx.navigation.Navigator
            public boolean popBackStack() {
                throw new IllegalStateException("popBackStack is not supported");
            }
        };

        public PermissiveNavigatorProvider() {
            addNavigator(new NavGraphNavigator(this));
        }

        @Override // androidx.navigation.NavigatorProvider
        public <T extends Navigator<? extends NavDestination>> T getNavigator(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            try {
                return (T) super.getNavigator(name);
            } catch (IllegalStateException unused) {
                Navigator<NavDestination> navigator = this.mDestNavigator;
                Intrinsics.checkNotNull(navigator, "null cannot be cast to non-null type T of androidx.navigation.NavDeepLinkBuilder.PermissiveNavigatorProvider.getNavigator");
                return navigator;
            }
        }
    }
}
