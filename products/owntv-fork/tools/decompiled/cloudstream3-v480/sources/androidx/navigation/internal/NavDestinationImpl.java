package androidx.navigation.internal;

import android.net.Uri;
import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.navigation.NavArgument;
import androidx.navigation.NavArgumentKt;
import androidx.navigation.NavDeepLink;
import androidx.navigation.NavDeepLinkRequest;
import androidx.navigation.NavDestination;
import androidx.navigation.NavUriKt;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: NavDestinationImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0010H\u0000¢\u0006\u0002\b(J\u0017\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010 \u001a\u00020\tH\u0000¢\u0006\u0002\b+J\u0017\u0010,\u001a\u0004\u0018\u00010*2\u0006\u0010-\u001a\u00020.H\u0000¢\u0006\u0002\b/J9\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00102\u000e\u00103\u001a\n\u0018\u000104j\u0004\u0018\u0001`52\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001506H\u0002¢\u0006\u0002\u00107J%\u00108\u001a\u0002012\u0006\u0010 \u001a\u00020\t2\u000e\u0010\u0013\u001a\n\u0018\u000109j\u0004\u0018\u0001`:H\u0000¢\u0006\u0002\b;J\u001d\u0010<\u001a\u00020&2\u0006\u0010=\u001a\u00020\t2\u0006\u0010>\u001a\u00020\u0015H\u0000¢\u0006\u0002\b?J\u0015\u0010@\u001a\u00020&2\u0006\u0010=\u001a\u00020\tH\u0000¢\u0006\u0002\bAJ%\u0010B\u001a\n\u0018\u000109j\u0004\u0018\u0001`:2\u000e\u0010C\u001a\n\u0018\u000109j\u0004\u0018\u0001`:H\u0000¢\u0006\u0002\bDR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R&\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00150\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u001b@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR(\u0010 \u001a\u0004\u0018\u00010\t2\b\u0010 \u001a\u0004\u0018\u00010\t@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u000b\"\u0004\b\"\u0010\rR\u0016\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Landroidx/navigation/internal/NavDestinationImpl;", "", "destination", "Landroidx/navigation/NavDestination;", "<init>", "(Landroidx/navigation/NavDestination;)V", "getDestination", "()Landroidx/navigation/NavDestination;", "idName", "", "getIdName$navigation_common_release", "()Ljava/lang/String;", "setIdName$navigation_common_release", "(Ljava/lang/String;)V", "deepLinks", "", "Landroidx/navigation/NavDeepLink;", "getDeepLinks$navigation_common_release", "()Ljava/util/List;", "arguments", "", "Landroidx/navigation/NavArgument;", "getArguments$navigation_common_release", "()Ljava/util/Map;", "setArguments$navigation_common_release", "(Ljava/util/Map;)V", "id", "", "getId$navigation_common_release", "()I", "setId$navigation_common_release", "(I)V", "route", "getRoute$navigation_common_release", "setRoute$navigation_common_release", "routeDeepLink", "Lkotlin/Lazy;", "addDeepLink", "", "navDeepLink", "addDeepLink$navigation_common_release", "matchRoute", "Landroidx/navigation/NavDestination$DeepLinkMatch;", "matchRoute$navigation_common_release", "matchDeepLink", "navDeepLinkRequest", "Landroidx/navigation/NavDeepLinkRequest;", "matchDeepLink$navigation_common_release", "hasRequiredArguments", "", "deepLink", "uri", "Landroid/net/Uri;", "Landroidx/navigation/NavUri;", "", "(Landroidx/navigation/NavDeepLink;Landroid/net/Uri;Ljava/util/Map;)Z", "hasRoute", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "hasRoute$navigation_common_release", "addArgument", "argumentName", "argument", "addArgument$navigation_common_release", "removeArgument", "removeArgument$navigation_common_release", "addInDefaultArgs", "args", "addInDefaultArgs$navigation_common_release", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NavDestinationImpl {
    private Map<String, NavArgument> arguments;
    private final List<NavDeepLink> deepLinks;
    private final NavDestination destination;
    private int id;
    private String idName;
    private String route;
    private Lazy<NavDeepLink> routeDeepLink;

    public NavDestinationImpl(NavDestination destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        this.destination = destination;
        this.deepLinks = new ArrayList();
        this.arguments = new LinkedHashMap();
    }

    public final NavDestination getDestination() {
        return this.destination;
    }

    /* JADX INFO: renamed from: getIdName$navigation_common_release, reason: from getter */
    public final String getIdName() {
        return this.idName;
    }

    public final void setIdName$navigation_common_release(String str) {
        this.idName = str;
    }

    public final List<NavDeepLink> getDeepLinks$navigation_common_release() {
        return this.deepLinks;
    }

    public final Map<String, NavArgument> getArguments$navigation_common_release() {
        return this.arguments;
    }

    public final void setArguments$navigation_common_release(Map<String, NavArgument> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.arguments = map;
    }

    /* JADX INFO: renamed from: getId$navigation_common_release, reason: from getter */
    public final int getId() {
        return this.id;
    }

    public final void setId$navigation_common_release(int i) {
        this.id = i;
        this.idName = null;
    }

    /* JADX INFO: renamed from: getRoute$navigation_common_release, reason: from getter */
    public final String getRoute() {
        return this.route;
    }

    public final void setRoute$navigation_common_release(String str) {
        if (str == null) {
            setId$navigation_common_release(0);
        } else {
            if (StringsKt.isBlank(str)) {
                throw new IllegalArgumentException("Cannot have an empty route".toString());
            }
            final String strCreateRoute = NavDestination.INSTANCE.createRoute(str);
            final NavDeepLink navDeepLinkBuild = new NavDeepLink.Builder().setUriPattern(strCreateRoute).build();
            List<String> listMissingRequiredArguments = NavArgumentKt.missingRequiredArguments(this.arguments, new Function1() { // from class: androidx.navigation.internal.NavDestinationImpl$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(NavDestinationImpl._set_route_$lambda$1(navDeepLinkBuild, (String) obj));
                }
            });
            if (!listMissingRequiredArguments.isEmpty()) {
                throw new IllegalArgumentException(("Cannot set route \"" + str + "\" for destination " + this.destination + ". Following required arguments are missing: " + listMissingRequiredArguments).toString());
            }
            this.routeDeepLink = LazyKt.lazy(new Function0() { // from class: androidx.navigation.internal.NavDestinationImpl$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return new NavDeepLink.Builder().setUriPattern(strCreateRoute).build();
                }
            });
            setId$navigation_common_release(strCreateRoute.hashCode());
        }
        this.route = str;
    }

    static final boolean _set_route_$lambda$1(NavDeepLink navDeepLink, String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return !navDeepLink.getArgumentsNames$navigation_common_release().contains(key);
    }

    static final boolean addDeepLink$lambda$4(NavDeepLink navDeepLink, String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return !navDeepLink.getArgumentsNames$navigation_common_release().contains(key);
    }

    public final void addDeepLink$navigation_common_release(final NavDeepLink navDeepLink) {
        Intrinsics.checkNotNullParameter(navDeepLink, "navDeepLink");
        List<String> listMissingRequiredArguments = NavArgumentKt.missingRequiredArguments(this.arguments, new Function1() { // from class: androidx.navigation.internal.NavDestinationImpl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(NavDestinationImpl.addDeepLink$lambda$4(navDeepLink, (String) obj));
            }
        });
        if (!listMissingRequiredArguments.isEmpty()) {
            throw new IllegalArgumentException(("Deep link " + navDeepLink.getUriPattern() + " can't be used to open destination " + this.destination + ".\nFollowing required arguments are missing: " + listMissingRequiredArguments).toString());
        }
        this.deepLinks.add(navDeepLink);
    }

    public final NavDestination.DeepLinkMatch matchRoute$navigation_common_release(String route) {
        NavDeepLink value;
        Uri uriNavUri;
        Bundle matchingArguments;
        Intrinsics.checkNotNullParameter(route, "route");
        Lazy<NavDeepLink> lazy = this.routeDeepLink;
        if (lazy == null || (value = lazy.getValue()) == null || (matchingArguments = value.getMatchingArguments((uriNavUri = NavUriKt.NavUri(NavDestination.INSTANCE.createRoute(route))), this.arguments)) == null) {
            return null;
        }
        return new NavDestination.DeepLinkMatch(this.destination, matchingArguments, value.getIsExactDeepLink(), value.calculateMatchingPathSegments$navigation_common_release(uriNavUri), false, -1);
    }

    public final NavDestination.DeepLinkMatch matchDeepLink$navigation_common_release(NavDeepLinkRequest navDeepLinkRequest) {
        Intrinsics.checkNotNullParameter(navDeepLinkRequest, "navDeepLinkRequest");
        if (this.deepLinks.isEmpty()) {
            return null;
        }
        NavDestination.DeepLinkMatch deepLinkMatch = null;
        for (NavDeepLink navDeepLink : this.deepLinks) {
            Uri uri = navDeepLinkRequest.getUri();
            if (navDeepLink.matches$navigation_common_release(navDeepLinkRequest)) {
                Bundle matchingArguments = uri != null ? navDeepLink.getMatchingArguments(uri, this.arguments) : null;
                int iCalculateMatchingPathSegments$navigation_common_release = navDeepLink.calculateMatchingPathSegments$navigation_common_release(uri);
                String action = navDeepLinkRequest.getAction();
                boolean z = action != null && Intrinsics.areEqual(action, navDeepLink.getAction());
                String mimeType = navDeepLinkRequest.getMimeType();
                int mimeTypeMatchRating = mimeType != null ? navDeepLink.getMimeTypeMatchRating(mimeType) : -1;
                if (matchingArguments == null) {
                    if (z || mimeTypeMatchRating > -1) {
                        if (hasRequiredArguments(navDeepLink, uri, this.arguments)) {
                        }
                    }
                }
                NavDestination.DeepLinkMatch deepLinkMatch2 = new NavDestination.DeepLinkMatch(this.destination, matchingArguments, navDeepLink.getIsExactDeepLink(), iCalculateMatchingPathSegments$navigation_common_release, z, mimeTypeMatchRating);
                if (deepLinkMatch == null || deepLinkMatch2.compareTo(deepLinkMatch) > 0) {
                    deepLinkMatch = deepLinkMatch2;
                }
            }
        }
        return deepLinkMatch;
    }

    private final boolean hasRequiredArguments(NavDeepLink deepLink, Uri uri, Map<String, NavArgument> arguments) {
        final Bundle matchingPathAndQueryArgs$navigation_common_release = deepLink.getMatchingPathAndQueryArgs$navigation_common_release(uri, arguments);
        return NavArgumentKt.missingRequiredArguments(arguments, new Function1() { // from class: androidx.navigation.internal.NavDestinationImpl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(NavDestinationImpl.hasRequiredArguments$lambda$7(matchingPathAndQueryArgs$navigation_common_release, (String) obj));
            }
        }).isEmpty();
    }

    public final boolean hasRoute$navigation_common_release(String route, Bundle arguments) {
        Intrinsics.checkNotNullParameter(route, "route");
        if (Intrinsics.areEqual(this.route, route)) {
            return true;
        }
        NavDestination.DeepLinkMatch deepLinkMatchMatchRoute$navigation_common_release = matchRoute$navigation_common_release(route);
        if (Intrinsics.areEqual(this.destination, deepLinkMatchMatchRoute$navigation_common_release != null ? deepLinkMatchMatchRoute$navigation_common_release.getDestination() : null)) {
            return deepLinkMatchMatchRoute$navigation_common_release.hasMatchingArgs(arguments);
        }
        return false;
    }

    public final void addArgument$navigation_common_release(String argumentName, NavArgument argument) {
        Intrinsics.checkNotNullParameter(argumentName, "argumentName");
        Intrinsics.checkNotNullParameter(argument, "argument");
        this.arguments.put(argumentName, argument);
    }

    public final void removeArgument$navigation_common_release(String argumentName) {
        Intrinsics.checkNotNullParameter(argumentName, "argumentName");
        this.arguments.remove(argumentName);
    }

    public final Bundle addInDefaultArgs$navigation_common_release(Bundle args) {
        Pair[] pairArr;
        if (args == null && this.arguments.isEmpty()) {
            return null;
        }
        Map mapEmptyMap = MapsKt.emptyMap();
        if (mapEmptyMap.isEmpty()) {
            pairArr = new Pair[0];
        } else {
            ArrayList arrayList = new ArrayList(mapEmptyMap.size());
            for (Map.Entry entry : mapEmptyMap.entrySet()) {
                arrayList.add(TuplesKt.to((String) entry.getKey(), entry.getValue()));
            }
            pairArr = (Pair[]) arrayList.toArray(new Pair[0]);
        }
        Bundle bundleBundleOf = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        SavedStateWriter.m8016constructorimpl(bundleBundleOf);
        for (Map.Entry<String, NavArgument> entry2 : this.arguments.entrySet()) {
            entry2.getValue().putDefaultValue(entry2.getKey(), bundleBundleOf);
        }
        if (args != null) {
            SavedStateWriter.m8020putAllimpl(SavedStateWriter.m8016constructorimpl(bundleBundleOf), args);
            for (Map.Entry<String, NavArgument> entry3 : this.arguments.entrySet()) {
                String key = entry3.getKey();
                NavArgument value = entry3.getValue();
                if (!value.getIsDefaultValueUnknown() && !value.verify(key, bundleBundleOf)) {
                    throw new IllegalArgumentException(("Wrong argument type for '" + key + "' in argument savedState. " + value.getType().getName() + " expected.").toString());
                }
            }
        }
        return bundleBundleOf;
    }

    static final boolean hasRequiredArguments$lambda$7(Bundle bundle, String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return !SavedStateReader.m7931containsimpl(SavedStateReader.m7930constructorimpl(bundle), key);
    }
}
