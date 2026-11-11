package androidx.navigation;

import android.net.Uri;
import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.navigation.serialization.RouteSerializerKt;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateWriter;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlinx.serialization.SerializersKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: NavDeepLink.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001a\u0018\u0000 v2\u00020\u0001:\u0004stuvB'\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0017\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\tJ*\u0010<\u001a\u00020=2\u0006\u0010\b\u001a\u00020\u00032\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f2\n\u0010?\u001a\u00060@j\u0002`AH\u0002J\u001b\u0010B\u001a\u00020\u00182\n\u0010\b\u001a\u00060Cj\u0002`DH\u0000¢\u0006\u0004\bE\u0010FJ\u0015\u0010B\u001a\u00020\u00182\u0006\u0010G\u001a\u00020HH\u0000¢\u0006\u0002\bEJ\u001d\u0010I\u001a\u00020\u00182\u000e\u0010\b\u001a\n\u0018\u00010Cj\u0004\u0018\u0001`DH\u0002¢\u0006\u0002\u0010FJ\u0012\u0010J\u001a\u00020\u00182\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0002J\u0012\u0010K\u001a\u00020\u00182\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u0002J\u0010\u0010L\u001a\u00020M2\u0006\u0010\u0005\u001a\u00020\u0003H\u0007J7\u0010N\u001a\n\u0018\u00010Oj\u0004\u0018\u0001`P2\n\u0010Q\u001a\u00060Cj\u0002`D2\u0014\u0010R\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010T0SH\u0007¢\u0006\u0002\u0010UJ9\u0010V\u001a\u00060Oj\u0002`P2\u000e\u0010Q\u001a\n\u0018\u00010Cj\u0004\u0018\u0001`D2\u0014\u0010R\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010T0SH\u0000¢\u0006\u0004\bW\u0010UJ4\u0010X\u001a\u00020=2\b\u0010Y\u001a\u0004\u0018\u00010\u00032\n\u0010Z\u001a\u00060Oj\u0002`P2\u0014\u0010R\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010T0SH\u0002J2\u0010[\u001a\u00020\u00182\u0006\u0010\\\u001a\u00020]2\n\u0010Z\u001a\u00060Oj\u0002`P2\u0014\u0010R\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010T0SH\u0002J;\u0010^\u001a\u00020\u00182\n\u0010Q\u001a\u00060Cj\u0002`D2\n\u0010Z\u001a\u00060Oj\u0002`P2\u0014\u0010R\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010T0SH\u0002¢\u0006\u0002\u0010_J@\u0010`\u001a\u00020\u00182\f\u0010a\u001a\b\u0012\u0004\u0012\u00020\u0003062\u0006\u0010b\u001a\u00020\u001d2\n\u0010Z\u001a\u00060Oj\u0002`P2\u0014\u0010R\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010T0SH\u0002J\u001f\u0010c\u001a\u00020M2\u000e\u0010d\u001a\n\u0018\u00010Cj\u0004\u0018\u0001`DH\u0000¢\u0006\u0004\be\u0010fJ.\u0010g\u001a\u00020=2\n\u0010Z\u001a\u00060Oj\u0002`P2\u0006\u0010h\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u00032\b\u0010i\u001a\u0004\u0018\u00010TH\u0002J0\u0010j\u001a\u00020\u00182\n\u0010Z\u001a\u00060Oj\u0002`P2\u0006\u0010h\u001a\u00020\u00032\b\u00108\u001a\u0004\u0018\u00010\u00032\b\u0010i\u001a\u0004\u0018\u00010TH\u0002J\u0013\u0010k\u001a\u00020\u00182\b\u0010l\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010m\u001a\u00020MH\u0016J\b\u0010n\u001a\u00020=H\u0002J\u0014\u0010o\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J\u001c\u0010p\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010#H\u0002J\b\u0010q\u001a\u00020=H\u0002J\f\u0010r\u001a\u00020\u0003*\u00020\u0003H\u0002R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u0004\u0018\u00010\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u0017\u0010\u0019R'\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001d0\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u0016\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010!\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R/\u0010\"\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010#8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\u0016\u001a\u0004\b$\u0010%R!\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\u0016\u001a\u0004\b(\u0010)R\u001d\u0010+\u001a\u0004\u0018\u00010\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\u0016\u001a\u0004\b,\u0010\u000bR\u001d\u0010.\u001a\u0004\u0018\u00010\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\u0016\u001a\u0004\b/\u0010\u0014R\u0010\u00101\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u00102\u001a\u0004\u0018\u00010\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b4\u0010\u0016\u001a\u0004\b3\u0010\u0014R\u001a\u00105\u001a\b\u0012\u0004\u0012\u00020\u0003068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b7\u0010)R&\u00109\u001a\u00020\u00182\u0006\u00108\u001a\u00020\u00188G@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0019\"\u0004\b:\u0010;¨\u0006w"}, d2 = {"Landroidx/navigation/NavDeepLink;", "", "uriPattern", "", "action", "mimeType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "uri", "(Ljava/lang/String;)V", "getUriPattern", "()Ljava/lang/String;", "getAction", "getMimeType", "pathArgs", "", "pathRegex", "pathPattern", "Lkotlin/text/Regex;", "getPathPattern", "()Lkotlin/text/Regex;", "pathPattern$delegate", "Lkotlin/Lazy;", "isParameterizedQuery", "", "()Z", "isParameterizedQuery$delegate", "queryArgsMap", "", "Landroidx/navigation/NavDeepLink$ParamQuery;", "getQueryArgsMap", "()Ljava/util/Map;", "queryArgsMap$delegate", "isSingleQueryParamValueOnly", "fragArgsAndRegex", "Lkotlin/Pair;", "getFragArgsAndRegex", "()Lkotlin/Pair;", "fragArgsAndRegex$delegate", "fragArgs", "getFragArgs", "()Ljava/util/List;", "fragArgs$delegate", "fragRegex", "getFragRegex", "fragRegex$delegate", "fragPattern", "getFragPattern", "fragPattern$delegate", "mimeTypeRegex", "mimeTypePattern", "getMimeTypePattern", "mimeTypePattern$delegate", "argumentsNames", "", "getArgumentsNames$navigation_common_release", ES6Iterator.VALUE_PROPERTY, "isExactDeepLink", "setExactDeepLink$navigation_common_release", "(Z)V", "buildRegex", "", "args", "uriRegex", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "matches", "Landroid/net/Uri;", "Landroidx/navigation/NavUri;", "matches$navigation_common_release", "(Landroid/net/Uri;)Z", "deepLinkRequest", "Landroidx/navigation/NavDeepLinkRequest;", "matchUri", "matchAction", "matchMimeType", "getMimeTypeMatchRating", "", "getMatchingArguments", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "deepLink", "arguments", "", "Landroidx/navigation/NavArgument;", "(Landroid/net/Uri;Ljava/util/Map;)Landroid/os/Bundle;", "getMatchingPathAndQueryArgs", "getMatchingPathAndQueryArgs$navigation_common_release", "getMatchingUriFragment", "fragment", "savedState", "getMatchingPathArguments", "result", "Lkotlin/text/MatchResult;", "getMatchingQueryArguments", "(Landroid/net/Uri;Landroid/os/Bundle;Ljava/util/Map;)Z", "parseInputParams", "inputParams", "storedParam", "calculateMatchingPathSegments", "requestedLink", "calculateMatchingPathSegments$navigation_common_release", "(Landroid/net/Uri;)I", "parseArgument", "name", "argument", "parseArgumentForRepeatedParam", "equals", "other", "hashCode", "parsePath", "parseQuery", "parseFragment", "parseMime", "saveWildcardInRegex", "ParamQuery", "MimeType", "Builder", "Companion", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NavDeepLink {
    private static final String ANY_SYMBOLS_IN_THE_TAIL = "([\\s\\S]+?)?";
    private final String action;

    /* JADX INFO: renamed from: fragArgs$delegate, reason: from kotlin metadata */
    private final Lazy fragArgs;

    /* JADX INFO: renamed from: fragArgsAndRegex$delegate, reason: from kotlin metadata */
    private final Lazy fragArgsAndRegex;

    /* JADX INFO: renamed from: fragPattern$delegate, reason: from kotlin metadata */
    private final Lazy fragPattern;

    /* JADX INFO: renamed from: fragRegex$delegate, reason: from kotlin metadata */
    private final Lazy fragRegex;
    private boolean isExactDeepLink;

    /* JADX INFO: renamed from: isParameterizedQuery$delegate, reason: from kotlin metadata */
    private final Lazy isParameterizedQuery;
    private boolean isSingleQueryParamValueOnly;
    private final String mimeType;

    /* JADX INFO: renamed from: mimeTypePattern$delegate, reason: from kotlin metadata */
    private final Lazy mimeTypePattern;
    private String mimeTypeRegex;
    private final List<String> pathArgs;

    /* JADX INFO: renamed from: pathPattern$delegate, reason: from kotlin metadata */
    private final Lazy pathPattern;
    private String pathRegex;

    /* JADX INFO: renamed from: queryArgsMap$delegate, reason: from kotlin metadata */
    private final Lazy queryArgsMap;
    private final String uriPattern;
    private static final Companion Companion = new Companion(null);
    private static final Regex SCHEME_PATTERN = new Regex("^[a-zA-Z]+[+\\w\\-.]*:");
    private static final Regex FILL_IN_PATTERN = new Regex("\\{(.+?)\\}");
    private static final Regex SCHEME_REGEX = new Regex("http[s]?://");
    private static final Regex WILDCARD_REGEX = new Regex(".*");
    private static final Regex PATH_REGEX = new Regex("([^/]*?|)");
    private static final Regex QUERY_PATTERN = new Regex("^[^?#]+\\?([^#]*).*");

    public NavDeepLink(String str, String str2, String str3) {
        this.uriPattern = str;
        this.action = str2;
        this.mimeType = str3;
        this.pathArgs = new ArrayList();
        this.pathPattern = LazyKt.lazy(new Function0() { // from class: androidx.navigation.NavDeepLink$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NavDeepLink.pathPattern_delegate$lambda$1(this.f$0);
            }
        });
        this.isParameterizedQuery = LazyKt.lazy(new Function0() { // from class: androidx.navigation.NavDeepLink$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(NavDeepLink.isParameterizedQuery_delegate$lambda$2(this.f$0));
            }
        });
        this.queryArgsMap = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.navigation.NavDeepLink$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.parseQuery();
            }
        });
        this.fragArgsAndRegex = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.navigation.NavDeepLink$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.parseFragment();
            }
        });
        this.fragArgs = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.navigation.NavDeepLink$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NavDeepLink.fragArgs_delegate$lambda$5(this.f$0);
            }
        });
        this.fragRegex = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.navigation.NavDeepLink$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NavDeepLink.fragRegex_delegate$lambda$6(this.f$0);
            }
        });
        this.fragPattern = LazyKt.lazy(new Function0() { // from class: androidx.navigation.NavDeepLink$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NavDeepLink.fragPattern_delegate$lambda$8(this.f$0);
            }
        });
        this.mimeTypePattern = LazyKt.lazy(new Function0() { // from class: androidx.navigation.NavDeepLink$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NavDeepLink.mimeTypePattern_delegate$lambda$10(this.f$0);
            }
        });
        parsePath();
        parseMime();
    }

    public final String getUriPattern() {
        return this.uriPattern;
    }

    public final String getAction() {
        return this.action;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    private final Regex getPathPattern() {
        return (Regex) this.pathPattern.getValue();
    }

    static final Regex pathPattern_delegate$lambda$1(NavDeepLink navDeepLink) {
        String str = navDeepLink.pathRegex;
        if (str != null) {
            return new Regex(str, RegexOption.IGNORE_CASE);
        }
        return null;
    }

    private final boolean isParameterizedQuery() {
        return ((Boolean) this.isParameterizedQuery.getValue()).booleanValue();
    }

    static final boolean isParameterizedQuery_delegate$lambda$2(NavDeepLink navDeepLink) {
        String str = navDeepLink.uriPattern;
        return str != null && QUERY_PATTERN.matches(str);
    }

    private final Map<String, ParamQuery> getQueryArgsMap() {
        return (Map) this.queryArgsMap.getValue();
    }

    private final Pair<List<String>, String> getFragArgsAndRegex() {
        return (Pair) this.fragArgsAndRegex.getValue();
    }

    static final List fragArgs_delegate$lambda$5(NavDeepLink navDeepLink) {
        List<String> first;
        Pair<List<String>, String> fragArgsAndRegex = navDeepLink.getFragArgsAndRegex();
        return (fragArgsAndRegex == null || (first = fragArgsAndRegex.getFirst()) == null) ? new ArrayList() : first;
    }

    private final List<String> getFragArgs() {
        return (List) this.fragArgs.getValue();
    }

    static final String fragRegex_delegate$lambda$6(NavDeepLink navDeepLink) {
        Pair<List<String>, String> fragArgsAndRegex = navDeepLink.getFragArgsAndRegex();
        if (fragArgsAndRegex != null) {
            return fragArgsAndRegex.getSecond();
        }
        return null;
    }

    private final String getFragRegex() {
        return (String) this.fragRegex.getValue();
    }

    static final Regex fragPattern_delegate$lambda$8(NavDeepLink navDeepLink) {
        String fragRegex = navDeepLink.getFragRegex();
        if (fragRegex != null) {
            return new Regex(fragRegex, RegexOption.IGNORE_CASE);
        }
        return null;
    }

    private final Regex getFragPattern() {
        return (Regex) this.fragPattern.getValue();
    }

    private final Regex getMimeTypePattern() {
        return (Regex) this.mimeTypePattern.getValue();
    }

    static final Regex mimeTypePattern_delegate$lambda$10(NavDeepLink navDeepLink) {
        String str = navDeepLink.mimeTypeRegex;
        if (str != null) {
            return new Regex(str);
        }
        return null;
    }

    public final List<String> getArgumentsNames$navigation_common_release() {
        List<String> list = this.pathArgs;
        Collection<ParamQuery> collectionValues = getQueryArgsMap().values();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((ParamQuery) it.next()).getArguments());
        }
        return CollectionsKt.plus((Collection) CollectionsKt.plus((Collection) list, (Iterable) arrayList), (Iterable) getFragArgs());
    }

    /* JADX INFO: renamed from: isExactDeepLink, reason: from getter */
    public final boolean getIsExactDeepLink() {
        return this.isExactDeepLink;
    }

    public final void setExactDeepLink$navigation_common_release(boolean z) {
        this.isExactDeepLink = z;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NavDeepLink(String uri) {
        this(uri, null, null);
        Intrinsics.checkNotNullParameter(uri, "uri");
    }

    private final void buildRegex(String uri, List<String> args, StringBuilder uriRegex) {
        int last = 0;
        for (MatchResult matchResultFind$default = Regex.find$default(FILL_IN_PATTERN, uri, 0, 2, null); matchResultFind$default != null; matchResultFind$default = matchResultFind$default.next()) {
            MatchGroup matchGroup = matchResultFind$default.getGroups().get(1);
            Intrinsics.checkNotNull(matchGroup);
            args.add(matchGroup.getValue());
            if (matchResultFind$default.getRange().getFirst() > last) {
                Regex.Companion companion = Regex.INSTANCE;
                String strSubstring = uri.substring(last, matchResultFind$default.getRange().getFirst());
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                uriRegex.append(companion.escape(strSubstring));
            }
            uriRegex.append(PATH_REGEX.getPattern());
            last = matchResultFind$default.getRange().getLast() + 1;
        }
        if (last < uri.length()) {
            Regex.Companion companion2 = Regex.INSTANCE;
            String strSubstring2 = uri.substring(last);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            uriRegex.append(companion2.escape(strSubstring2));
        }
    }

    public final boolean matches$navigation_common_release(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return matches$navigation_common_release(new NavDeepLinkRequest(uri, null, null));
    }

    public final boolean matches$navigation_common_release(NavDeepLinkRequest deepLinkRequest) {
        Intrinsics.checkNotNullParameter(deepLinkRequest, "deepLinkRequest");
        return matchUri(deepLinkRequest.getUri()) && matchAction(deepLinkRequest.getAction()) && matchMimeType(deepLinkRequest.getMimeType());
    }

    private final boolean matchUri(Uri uri) {
        if (getPathPattern() == null) {
            return true;
        }
        if (uri == null) {
            return false;
        }
        Regex pathPattern = getPathPattern();
        Intrinsics.checkNotNull(pathPattern);
        return pathPattern.matches(uri.toString());
    }

    private final boolean matchAction(String action) {
        String str = this.action;
        if (str == null) {
            return true;
        }
        if (action == null) {
            return false;
        }
        return Intrinsics.areEqual(str, action);
    }

    private final boolean matchMimeType(String mimeType) {
        if (this.mimeType == null) {
            return true;
        }
        if (mimeType == null) {
            return false;
        }
        Regex mimeTypePattern = getMimeTypePattern();
        Intrinsics.checkNotNull(mimeTypePattern);
        return mimeTypePattern.matches(mimeType);
    }

    public final int getMimeTypeMatchRating(String mimeType) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        if (this.mimeType == null) {
            return -1;
        }
        Regex mimeTypePattern = getMimeTypePattern();
        Intrinsics.checkNotNull(mimeTypePattern);
        if (mimeTypePattern.matches(mimeType)) {
            return new MimeType(this.mimeType).compareTo(new MimeType(mimeType));
        }
        return -1;
    }

    public final Bundle getMatchingArguments(Uri deepLink, Map<String, NavArgument> arguments) {
        MatchResult matchResultMatchEntire;
        Pair[] pairArr;
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Regex pathPattern = getPathPattern();
        if (pathPattern == null || (matchResultMatchEntire = pathPattern.matchEntire(deepLink.toString())) == null) {
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
        final Bundle bundleBundleOf = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        SavedStateWriter.m8016constructorimpl(bundleBundleOf);
        if (!getMatchingPathArguments(matchResultMatchEntire, bundleBundleOf, arguments)) {
            return null;
        }
        if (isParameterizedQuery() && !getMatchingQueryArguments(deepLink, bundleBundleOf, arguments)) {
            return null;
        }
        getMatchingUriFragment(deepLink.getFragment(), bundleBundleOf, arguments);
        if (NavArgumentKt.missingRequiredArguments(arguments, new Function1() { // from class: androidx.navigation.NavDeepLink$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(NavDeepLink.getMatchingArguments$lambda$13(bundleBundleOf, (String) obj));
            }
        }).isEmpty()) {
            return bundleBundleOf;
        }
        return null;
    }

    private final void getMatchingUriFragment(String fragment, Bundle savedState, Map<String, NavArgument> arguments) {
        MatchResult matchResultMatchEntire;
        String value;
        Regex fragPattern = getFragPattern();
        if (fragPattern == null || (matchResultMatchEntire = fragPattern.matchEntire(String.valueOf(fragment))) == null) {
            return;
        }
        List<String> fragArgs = getFragArgs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(fragArgs, 10));
        int i = 0;
        for (Object obj : fragArgs) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            MatchGroup matchGroup = matchResultMatchEntire.getGroups().get(i2);
            String strDecode = (matchGroup == null || (value = matchGroup.getValue()) == null) ? null : NavUriUtils.INSTANCE.decode(value);
            if (strDecode == null) {
                strDecode = "";
            }
            try {
                parseArgument(savedState, str, strDecode, arguments.get(str));
                arrayList.add(Unit.INSTANCE);
                i = i2;
            } catch (IllegalArgumentException unused) {
                return;
            }
        }
    }

    private final boolean getMatchingPathArguments(MatchResult result, Bundle savedState, Map<String, NavArgument> arguments) {
        String value;
        List<String> list = this.pathArgs;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            MatchGroup matchGroup = result.getGroups().get(i2);
            String strDecode = (matchGroup == null || (value = matchGroup.getValue()) == null) ? null : NavUriUtils.INSTANCE.decode(value);
            if (strDecode == null) {
                strDecode = "";
            }
            try {
                parseArgument(savedState, str, strDecode, arguments.get(str));
                arrayList.add(Unit.INSTANCE);
                i = i2;
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }
        return true;
    }

    private final boolean getMatchingQueryArguments(Uri deepLink, Bundle savedState, Map<String, NavArgument> arguments) {
        String query;
        for (Map.Entry<String, ParamQuery> entry : getQueryArgsMap().entrySet()) {
            String key = entry.getKey();
            ParamQuery value = entry.getValue();
            List<String> queryParameters = deepLink.getQueryParameters(key);
            if (this.isSingleQueryParamValueOnly && (query = deepLink.getQuery()) != null && !Intrinsics.areEqual(query, deepLink.toString())) {
                queryParameters = CollectionsKt.listOf(query);
            }
            if (!parseInputParams(queryParameters, value, savedState, arguments)) {
                return false;
            }
        }
        return true;
    }

    public final int calculateMatchingPathSegments$navigation_common_release(Uri requestedLink) {
        if (requestedLink == null || this.uriPattern == null) {
            return 0;
        }
        return CollectionsKt.intersect(requestedLink.getPathSegments(), NavUriUtils.INSTANCE.parse(this.uriPattern).getPathSegments()).size();
    }

    private final void parseArgument(Bundle savedState, String name, String value, NavArgument argument) {
        if (argument != null) {
            argument.getType().parseAndPut(savedState, name, value);
        } else {
            SavedStateWriter.m8049putStringimpl(SavedStateWriter.m8016constructorimpl(savedState), name, value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: NavDeepLink.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0015"}, d2 = {"Landroidx/navigation/NavDeepLink$ParamQuery;", "", "<init>", "()V", "paramRegex", "", "getParamRegex", "()Ljava/lang/String;", "setParamRegex", "(Ljava/lang/String;)V", "arguments", "", "getArguments", "()Ljava/util/List;", "addArgumentName", "", "name", "getArgumentName", "index", "", ContentDisposition.Parameters.Size, "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final class ParamQuery {
        private final List<String> arguments = new ArrayList();
        private String paramRegex;

        public final String getParamRegex() {
            return this.paramRegex;
        }

        public final void setParamRegex(String str) {
            this.paramRegex = str;
        }

        public final List<String> getArguments() {
            return this.arguments;
        }

        public final void addArgumentName(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.arguments.add(name);
        }

        public final String getArgumentName(int index) {
            return this.arguments.get(index);
        }

        public final int size() {
            return this.arguments.size();
        }
    }

    /* JADX INFO: compiled from: NavDeepLink.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0000H\u0096\u0002R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0005R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\u0005¨\u0006\u0010"}, d2 = {"Landroidx/navigation/NavDeepLink$MimeType;", "", "mimeType", "", "<init>", "(Ljava/lang/String;)V", "type", "getType", "()Ljava/lang/String;", "setType", "subType", "getSubType", "setSubType", "compareTo", "", "other", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class MimeType implements Comparable<MimeType> {
        private String subType;
        private String type;

        public MimeType(String mimeType) {
            List listEmptyList;
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            List<String> listSplit = new Regex("/").split(mimeType, 0);
            if (!listSplit.isEmpty()) {
                ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
                while (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() != 0) {
                        listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                    }
                }
                listEmptyList = CollectionsKt.emptyList();
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
            this.type = (String) listEmptyList.get(0);
            this.subType = (String) listEmptyList.get(1);
        }

        public final String getType() {
            return this.type;
        }

        public final void setType(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.type = str;
        }

        public final String getSubType() {
            return this.subType;
        }

        public final void setSubType(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.subType = str;
        }

        @Override // java.lang.Comparable
        public int compareTo(MimeType other) {
            Intrinsics.checkNotNullParameter(other, "other");
            int i = Intrinsics.areEqual(this.type, other.type) ? 2 : 0;
            return Intrinsics.areEqual(this.subType, other.subType) ? i + 1 : i;
        }
    }

    public boolean equals(Object other) {
        if (other != null && (other instanceof NavDeepLink)) {
            NavDeepLink navDeepLink = (NavDeepLink) other;
            if (Intrinsics.areEqual(this.uriPattern, navDeepLink.uriPattern) && Intrinsics.areEqual(this.action, navDeepLink.action) && Intrinsics.areEqual(this.mimeType, navDeepLink.mimeType)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.uriPattern;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.action;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.mimeType;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    /* JADX INFO: compiled from: NavDeepLink.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\t\b\u0017¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J<\u0010\b\u001a\u00020\u0000\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u00012\u0006\u0010\n\u001a\u00020\u00052\u001d\b\u0002\u0010\u000b\u001a\u0017\u0012\u0004\u0012\u00020\r\u0012\r\u0012\u000b\u0012\u0002\b\u00030\u000e¢\u0006\u0002\b\u000f0\fH\u0086\bJG\u0010\b\u001a\u00020\u0000\"\b\b\u0000\u0010\t*\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\t0\u00112\u0006\u0010\n\u001a\u00020\u00052\u001d\b\u0002\u0010\u000b\u001a\u0017\u0012\u0004\u0012\u00020\r\u0012\r\u0012\u000b\u0012\u0002\b\u00030\u000e¢\u0006\u0002\b\u000f0\fH\u0007J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0005J\u0006\u0010\u0014\u001a\u00020\u0015R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/navigation/NavDeepLink$Builder;", "", "<init>", "()V", "uriPattern", "", "action", "mimeType", "setUriPattern", ExifInterface.GPS_DIRECTION_TRUE, "basePath", "typeMap", "", "Lkotlin/reflect/KType;", "Landroidx/navigation/NavType;", "Lkotlin/jvm/JvmSuppressWildcards;", "route", "Lkotlin/reflect/KClass;", "setAction", "setMimeType", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Landroidx/navigation/NavDeepLink;", "Companion", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Builder {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private String action;
        private String mimeType;
        private String uriPattern;

        @JvmStatic
        public static final Builder fromAction(String str) {
            return INSTANCE.fromAction(str);
        }

        @JvmStatic
        public static final Builder fromMimeType(String str) {
            return INSTANCE.fromMimeType(str);
        }

        @JvmStatic
        public static final Builder fromUriPattern(String str) {
            return INSTANCE.fromUriPattern(str);
        }

        public final <T> Builder setUriPattern(KClass<T> route, String basePath) {
            Intrinsics.checkNotNullParameter(route, "route");
            Intrinsics.checkNotNullParameter(basePath, "basePath");
            return setUriPattern$default(this, route, basePath, null, 4, null);
        }

        public final Builder setUriPattern(String uriPattern) {
            Intrinsics.checkNotNullParameter(uriPattern, "uriPattern");
            this.uriPattern = uriPattern;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Builder setUriPattern$default(Builder builder, String basePath, Map typeMap, int i, Object obj) {
            if ((i & 2) != 0) {
                typeMap = MapsKt.emptyMap();
            }
            Intrinsics.checkNotNullParameter(basePath, "basePath");
            Intrinsics.checkNotNullParameter(typeMap, "typeMap");
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return builder.setUriPattern(Reflection.getOrCreateKotlinClass(Object.class), basePath, typeMap);
        }

        public final /* synthetic */ <T> Builder setUriPattern(String basePath, Map<KType, NavType<?>> typeMap) {
            Intrinsics.checkNotNullParameter(basePath, "basePath");
            Intrinsics.checkNotNullParameter(typeMap, "typeMap");
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return setUriPattern(Reflection.getOrCreateKotlinClass(Object.class), basePath, typeMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Builder setUriPattern$default(Builder builder, KClass kClass, String str, Map map, int i, Object obj) {
            if ((i & 4) != 0) {
                map = MapsKt.emptyMap();
            }
            return builder.setUriPattern(kClass, str, map);
        }

        public final <T> Builder setUriPattern(KClass<T> route, String basePath, Map<KType, NavType<?>> typeMap) {
            Intrinsics.checkNotNullParameter(route, "route");
            Intrinsics.checkNotNullParameter(basePath, "basePath");
            Intrinsics.checkNotNullParameter(typeMap, "typeMap");
            this.uriPattern = RouteSerializerKt.generateRoutePattern(SerializersKt.serializer(route), typeMap, basePath);
            return this;
        }

        public final Builder setAction(String action) {
            Intrinsics.checkNotNullParameter(action, "action");
            if (action.length() <= 0) {
                throw new IllegalArgumentException("The NavDeepLink cannot have an empty action.".toString());
            }
            this.action = action;
            return this;
        }

        public final Builder setMimeType(String mimeType) {
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            this.mimeType = mimeType;
            return this;
        }

        public final NavDeepLink build() {
            return new NavDeepLink(this.uriPattern, this.action, this.mimeType);
        }

        /* JADX INFO: compiled from: NavDeepLink.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J<\u0010\u0004\u001a\u00020\u0005\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u00012\u0006\u0010\t\u001a\u00020\u00072\u001d\b\u0002\u0010\n\u001a\u0017\u0012\u0004\u0012\u00020\f\u0012\r\u0012\u000b\u0012\u0002\b\u00030\r¢\u0006\u0002\b\u000e0\u000bH\u0087\bJ\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0007H\u0007J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0007H\u0007¨\u0006\u0013"}, d2 = {"Landroidx/navigation/NavDeepLink$Builder$Companion;", "", "<init>", "()V", "fromUriPattern", "Landroidx/navigation/NavDeepLink$Builder;", "uriPattern", "", ExifInterface.GPS_DIRECTION_TRUE, "basePath", "typeMap", "", "Lkotlin/reflect/KType;", "Landroidx/navigation/NavType;", "Lkotlin/jvm/JvmSuppressWildcards;", "fromAction", "action", "fromMimeType", "mimeType", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Builder fromUriPattern(String uriPattern) {
                Intrinsics.checkNotNullParameter(uriPattern, "uriPattern");
                Builder builder = new Builder();
                builder.setUriPattern(uriPattern);
                return builder;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Builder fromUriPattern$default(Companion companion, String basePath, Map typeMap, int i, Object obj) {
                if ((i & 2) != 0) {
                    typeMap = MapsKt.emptyMap();
                }
                Intrinsics.checkNotNullParameter(basePath, "basePath");
                Intrinsics.checkNotNullParameter(typeMap, "typeMap");
                Builder builder = new Builder();
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                builder.setUriPattern(Reflection.getOrCreateKotlinClass(Object.class), basePath, typeMap);
                return builder;
            }

            @JvmStatic
            public final /* synthetic */ <T> Builder fromUriPattern(String basePath, Map<KType, NavType<?>> typeMap) {
                Intrinsics.checkNotNullParameter(basePath, "basePath");
                Intrinsics.checkNotNullParameter(typeMap, "typeMap");
                Builder builder = new Builder();
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                builder.setUriPattern(Reflection.getOrCreateKotlinClass(Object.class), basePath, typeMap);
                return builder;
            }

            @JvmStatic
            public final Builder fromAction(String action) {
                Intrinsics.checkNotNullParameter(action, "action");
                if (action.length() <= 0) {
                    throw new IllegalArgumentException("The NavDeepLink cannot have an empty action.".toString());
                }
                Builder builder = new Builder();
                builder.setAction(action);
                return builder;
            }

            @JvmStatic
            public final Builder fromMimeType(String mimeType) {
                Intrinsics.checkNotNullParameter(mimeType, "mimeType");
                Builder builder = new Builder();
                builder.setMimeType(mimeType);
                return builder;
            }
        }
    }

    /* JADX INFO: compiled from: NavDeepLink.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/navigation/NavDeepLink$Companion;", "", "<init>", "()V", "SCHEME_PATTERN", "Lkotlin/text/Regex;", "FILL_IN_PATTERN", "SCHEME_REGEX", "WILDCARD_REGEX", "PATH_REGEX", "QUERY_PATTERN", "ANY_SYMBOLS_IN_THE_TAIL", "", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final void parsePath() {
        if (this.uriPattern == null) {
            return;
        }
        StringBuilder sb = new StringBuilder("^");
        if (!SCHEME_PATTERN.containsMatchIn(this.uriPattern)) {
            sb.append(SCHEME_REGEX.getPattern());
        }
        boolean z = false;
        MatchResult matchResultFind$default = Regex.find$default(new Regex("(\\?|#|$)"), this.uriPattern, 0, 2, null);
        if (matchResultFind$default != null) {
            String strSubstring = this.uriPattern.substring(0, matchResultFind$default.getRange().getFirst());
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            buildRegex(strSubstring, this.pathArgs, sb);
            StringBuilder sb2 = sb;
            if (!WILDCARD_REGEX.containsMatchIn(sb2) && !PATH_REGEX.containsMatchIn(sb2)) {
                z = true;
            }
            this.isExactDeepLink = z;
            sb.append("($|(\\?(.)*)|(#(.)*))");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        this.pathRegex = saveWildcardInRegex(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<String, ParamQuery> parseQuery() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (isParameterizedQuery()) {
            NavUriUtils navUriUtils = NavUriUtils.INSTANCE;
            String str = this.uriPattern;
            Intrinsics.checkNotNull(str);
            Uri uri = navUriUtils.parse(str);
            for (String str2 : uri.getQueryParameterNames()) {
                StringBuilder sb = new StringBuilder();
                List<String> queryParameters = uri.getQueryParameters(str2);
                if (queryParameters.size() > 1) {
                    throw new IllegalArgumentException(("Query parameter " + str2 + " must only be present once in " + this.uriPattern + ". To support repeated query parameters, use an array type for your argument and the pattern provided in your URI will be used to parse each query parameter instance.").toString());
                }
                String str3 = (String) CollectionsKt.firstOrNull((List) queryParameters);
                if (str3 == null) {
                    this.isSingleQueryParamValueOnly = true;
                    str3 = str2;
                }
                int last = 0;
                ParamQuery paramQuery = new ParamQuery();
                for (MatchResult matchResultFind$default = Regex.find$default(FILL_IN_PATTERN, str3, 0, 2, null); matchResultFind$default != null; matchResultFind$default = matchResultFind$default.next()) {
                    MatchGroup matchGroup = matchResultFind$default.getGroups().get(1);
                    Intrinsics.checkNotNull(matchGroup);
                    paramQuery.addArgumentName(matchGroup.getValue());
                    if (matchResultFind$default.getRange().getFirst() > last) {
                        String strSubstring = str3.substring(last, matchResultFind$default.getRange().getFirst());
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                        sb.append(Regex.INSTANCE.escape(strSubstring));
                    }
                    sb.append(ANY_SYMBOLS_IN_THE_TAIL);
                    last = matchResultFind$default.getRange().getLast() + 1;
                }
                if (last < str3.length()) {
                    Regex.Companion companion = Regex.INSTANCE;
                    String strSubstring2 = str3.substring(last);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                    sb.append(companion.escape(strSubstring2));
                }
                sb.append("$");
                String string = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                paramQuery.setParamRegex(saveWildcardInRegex(string));
                linkedHashMap.put(str2, paramQuery);
            }
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<List<String>, String> parseFragment() {
        if (this.uriPattern == null || NavUriUtils.INSTANCE.parse(this.uriPattern).getFragment() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        String fragment = NavUriUtils.INSTANCE.parse(this.uriPattern).getFragment();
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNull(fragment);
        buildRegex(fragment, arrayList, sb);
        return TuplesKt.to(arrayList, sb.toString());
    }

    private final void parseMime() {
        if (this.mimeType == null) {
            return;
        }
        if (!new Regex("^[\\s\\S]+/[\\s\\S]+$").matches(this.mimeType)) {
            throw new IllegalArgumentException(("The given mimeType " + this.mimeType + " does not match to required \"type/subtype\" format").toString());
        }
        MimeType mimeType = new MimeType(this.mimeType);
        this.mimeTypeRegex = StringsKt.replace$default("^(" + mimeType.getType() + "|[*]+)/(" + mimeType.getSubType() + "|[*]+)$", "*|[*]", "[\\s\\S]", false, 4, (Object) null);
    }

    private final String saveWildcardInRegex(String str) {
        String str2 = str;
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "\\Q", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) str2, (CharSequence) "\\E", false, 2, (Object) null)) {
            return StringsKt.replace$default(str, ".*", "\\E.*\\Q", false, 4, (Object) null);
        }
        return StringsKt.contains$default((CharSequence) str2, (CharSequence) "\\.\\*", false, 2, (Object) null) ? StringsKt.replace$default(str, "\\.\\*", ".*", false, 4, (Object) null) : str;
    }

    public final Bundle getMatchingPathAndQueryArgs$navigation_common_release(Uri deepLink, Map<String, NavArgument> arguments) {
        Pair[] pairArr;
        Regex pathPattern;
        MatchResult matchResultMatchEntire;
        Intrinsics.checkNotNullParameter(arguments, "arguments");
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
        if (deepLink != null && (pathPattern = getPathPattern()) != null && (matchResultMatchEntire = pathPattern.matchEntire(deepLink.toString())) != null) {
            getMatchingPathArguments(matchResultMatchEntire, bundleBundleOf, arguments);
            if (isParameterizedQuery()) {
                getMatchingQueryArguments(deepLink, bundleBundleOf, arguments);
            }
        }
        return bundleBundleOf;
    }

    private final boolean parseInputParams(List<String> inputParams, ParamQuery storedParam, Bundle savedState, Map<String, NavArgument> arguments) {
        Pair[] pairArr;
        Object objValueOf;
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
        Iterator<T> it = storedParam.getArguments().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String str = (String) it.next();
            NavArgument navArgument = arguments.get(str);
            NavType<Object> type = navArgument != null ? navArgument.getType() : null;
            if ((type instanceof CollectionNavType) && !navArgument.getIsDefaultValuePresent()) {
                CollectionNavType collectionNavType = (CollectionNavType) type;
                collectionNavType.put(bundleBundleOf, str, collectionNavType.emptyCollection());
            }
        }
        for (String str2 : inputParams) {
            String paramRegex = storedParam.getParamRegex();
            MatchResult matchResultMatchEntire = paramRegex != null ? new Regex(paramRegex).matchEntire(str2) : null;
            if (matchResultMatchEntire == null) {
                return false;
            }
            List<String> arguments2 = storedParam.getArguments();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments2, 10));
            int i = 0;
            for (Object obj : arguments2) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str3 = (String) obj;
                MatchGroup matchGroup = matchResultMatchEntire.getGroups().get(i2);
                String value = matchGroup != null ? matchGroup.getValue() : null;
                if (value == null) {
                    value = "";
                }
                NavArgument navArgument2 = arguments.get(str3);
                try {
                    if (!SavedStateReader.m7931containsimpl(SavedStateReader.m7930constructorimpl(bundleBundleOf), str3)) {
                        parseArgument(bundleBundleOf, str3, value, navArgument2);
                        objValueOf = Unit.INSTANCE;
                    } else {
                        objValueOf = Boolean.valueOf(parseArgumentForRepeatedParam(bundleBundleOf, str3, value, navArgument2));
                    }
                } catch (IllegalArgumentException unused) {
                    objValueOf = Unit.INSTANCE;
                }
                arrayList2.add(objValueOf);
                i = i2;
            }
        }
        SavedStateWriter.m8020putAllimpl(SavedStateWriter.m8016constructorimpl(savedState), bundleBundleOf);
        return true;
    }

    private final boolean parseArgumentForRepeatedParam(Bundle savedState, String name, String value, NavArgument argument) {
        if (!SavedStateReader.m7931containsimpl(SavedStateReader.m7930constructorimpl(savedState), name)) {
            return true;
        }
        if (argument == null) {
            return false;
        }
        NavType<Object> type = argument.getType();
        type.parseAndPut(savedState, name, value, type.get(savedState, name));
        return false;
    }

    static final boolean getMatchingArguments$lambda$13(Bundle bundle, String argName) {
        Intrinsics.checkNotNullParameter(argName, "argName");
        return !SavedStateReader.m7931containsimpl(SavedStateReader.m7930constructorimpl(bundle), argName);
    }
}
