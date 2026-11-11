package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RenderingUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class RenderingUtilsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String renderFlexibleMutabilityOrArrayElementVarianceType$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it;
    }

    public static /* synthetic */ String render$default(Name name, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return render(name, z);
    }

    public static final String render(Name name, boolean z) {
        Intrinsics.checkNotNullParameter(name, "<this>");
        String strAsStringStripSpecialMarkers = z ? name.asStringStripSpecialMarkers() : name.asString();
        Intrinsics.checkNotNull(strAsStringStripSpecialMarkers);
        if ((z && name.isSpecial()) || !shouldBeEscaped(strAsStringStripSpecialMarkers)) {
            return strAsStringStripSpecialMarkers;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("`" + strAsStringStripSpecialMarkers);
        sb.append('`');
        return sb.toString();
    }

    private static final boolean shouldBeEscaped(String str) {
        if (KeywordStringsGenerated.KEYWORDS.contains(str)) {
            return true;
        }
        String str2 = str;
        for (int i = 0; i < str2.length(); i++) {
            char cCharAt = str2.charAt(i);
            if (!Character.isLetterOrDigit(cCharAt) && cCharAt != '_') {
                return true;
            }
        }
        return str2.length() == 0 || !Character.isJavaIdentifierStart(str.codePointAt(0));
    }

    public static final String render(FqNameUnsafe fqNameUnsafe) {
        Intrinsics.checkNotNullParameter(fqNameUnsafe, "<this>");
        return renderFqName(fqNameUnsafe.pathSegments());
    }

    public static final String renderFqName(List<Name> pathSegments) {
        Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
        StringBuilder sb = new StringBuilder();
        for (Name name : pathSegments) {
            if (sb.length() > 0) {
                sb.append(".");
            }
            sb.append(render$default(name, false, 1, null));
        }
        return sb.toString();
    }

    public static /* synthetic */ String renderFlexibleMutabilityOrArrayElementVarianceType$default(String str, String str2, Function0 function0, Function0 function1, Function1 function2, int i, Object obj) {
        if ((i & 16) != 0) {
            function2 = new Function1() { // from class: kotlin.reflect.jvm.internal.impl.renderer.RenderingUtilsKt$$Lambda$0
                @Override // kotlin.jvm.functions.Function1
                public Object invoke(Object obj2) {
                    return RenderingUtilsKt.renderFlexibleMutabilityOrArrayElementVarianceType$lambda$0((String) obj2);
                }
            };
        }
        return renderFlexibleMutabilityOrArrayElementVarianceType(str, str2, function0, function1, function2);
    }

    public static final String renderFlexibleMutabilityOrArrayElementVarianceType(String lowerRendered, String upperRendered, Function0<String> renderKotlinCollectionsPrefix, Function0<String> renderKotlinPrefix, Function1<? super String, String> escape) {
        Intrinsics.checkNotNullParameter(lowerRendered, "lowerRendered");
        Intrinsics.checkNotNullParameter(upperRendered, "upperRendered");
        Intrinsics.checkNotNullParameter(renderKotlinCollectionsPrefix, "renderKotlinCollectionsPrefix");
        Intrinsics.checkNotNullParameter(renderKotlinPrefix, "renderKotlinPrefix");
        Intrinsics.checkNotNullParameter(escape, "escape");
        String strInvoke = renderKotlinCollectionsPrefix.invoke();
        String strReplacePrefixesInTypeRepresentations = replacePrefixesInTypeRepresentations(lowerRendered, strInvoke + "Mutable", upperRendered, strInvoke, strInvoke + "(Mutable)");
        if (strReplacePrefixesInTypeRepresentations != null) {
            return strReplacePrefixesInTypeRepresentations;
        }
        String strReplacePrefixesInTypeRepresentations2 = replacePrefixesInTypeRepresentations(lowerRendered, strInvoke + "MutableMap.MutableEntry", upperRendered, strInvoke + "Map.Entry", strInvoke + "(Mutable)Map.(Mutable)Entry");
        if (strReplacePrefixesInTypeRepresentations2 != null) {
            return strReplacePrefixesInTypeRepresentations2;
        }
        String strInvoke2 = renderKotlinPrefix.invoke();
        String strReplacePrefixesInTypeRepresentations3 = replacePrefixesInTypeRepresentations(lowerRendered, strInvoke2 + escape.invoke("Array<"), upperRendered, strInvoke2 + escape.invoke("Array<out "), strInvoke2 + escape.invoke("Array<(out) "));
        if (strReplacePrefixesInTypeRepresentations3 != null) {
            return strReplacePrefixesInTypeRepresentations3;
        }
        return null;
    }

    public static final String replacePrefixesInTypeRepresentations(String lowerRendered, String lowerPrefix, String upperRendered, String upperPrefix, String foldedPrefix) {
        Intrinsics.checkNotNullParameter(lowerRendered, "lowerRendered");
        Intrinsics.checkNotNullParameter(lowerPrefix, "lowerPrefix");
        Intrinsics.checkNotNullParameter(upperRendered, "upperRendered");
        Intrinsics.checkNotNullParameter(upperPrefix, "upperPrefix");
        Intrinsics.checkNotNullParameter(foldedPrefix, "foldedPrefix");
        if (StringsKt.startsWith$default(lowerRendered, lowerPrefix, false, 2, (Object) null) && StringsKt.startsWith$default(upperRendered, upperPrefix, false, 2, (Object) null)) {
            String strSubstring = lowerRendered.substring(lowerPrefix.length());
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            String strSubstring2 = upperRendered.substring(upperPrefix.length());
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            String str = foldedPrefix + strSubstring;
            if (Intrinsics.areEqual(strSubstring, strSubstring2)) {
                return str;
            }
            if (typeStringsDifferOnlyInNullability(strSubstring, strSubstring2)) {
                return str + '!';
            }
        }
        return null;
    }

    public static final boolean typeStringsDifferOnlyInNullability(String lower, String upper) {
        Intrinsics.checkNotNullParameter(lower, "lower");
        Intrinsics.checkNotNullParameter(upper, "upper");
        if (Intrinsics.areEqual(lower, StringsKt.replace$default(upper, "?", "", false, 4, (Object) null))) {
            return true;
        }
        if (StringsKt.endsWith$default(upper, "?", false, 2, (Object) null)) {
            if (Intrinsics.areEqual(lower + '?', upper)) {
                return true;
            }
        }
        StringBuilder sb = new StringBuilder("(");
        sb.append(lower);
        sb.append(")?");
        return Intrinsics.areEqual(sb.toString(), upper);
    }
}
