package com.fleeksoft.ksoup.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: URLUtil.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0002J\u0018\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0002J\u001a\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\tH\u0002J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0005H\u0002¨\u0006\u0015"}, d2 = {"Lcom/fleeksoft/ksoup/internal/URLUtil;", "", "<init>", "()V", "resolve", "", TtmlNode.RUBY_BASE, "relative", "isAbsoluteUrl", "", ImagesContract.URL, "mergePaths", "basePath", "relativePath", "normalizePath", "path", "addRoot", "stripQueryAndFragment", "parseUrl", "Lcom/fleeksoft/ksoup/internal/URLUtil$ParsedUrl;", "ParsedUrl", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class URLUtil {
    public static final URLUtil INSTANCE = new URLUtil();

    private URLUtil() {
    }

    public final String resolve(String base, String relative) {
        Intrinsics.checkNotNullParameter(base, "base");
        Intrinsics.checkNotNullParameter(relative, "relative");
        if (relative.length() == 0) {
            return base;
        }
        if (isAbsoluteUrl(relative)) {
            return relative;
        }
        if (!isAbsoluteUrl(base)) {
            return "";
        }
        ParsedUrl url = parseUrl(base);
        String strSubstring = null;
        if (StringsKt.startsWith$default(relative, "//", false, 2, (Object) null)) {
            return url.getScheme() + ":" + relative;
        }
        if (StringsKt.startsWith$default(relative, "?", false, 2, (Object) null)) {
            return url.getScheme() + ":" + url.getSchemeSeparator() + url.getAuthority() + url.getPath() + relative;
        }
        if (StringsKt.startsWith$default(relative, "#", false, 2, (Object) null)) {
            String scheme = url.getScheme();
            String schemeSeparator = url.getSchemeSeparator();
            String authority = url.getAuthority();
            String path = url.getPath();
            String query = url.getQuery();
            return scheme + ":" + schemeSeparator + authority + path + (query != null ? query : "") + relative;
        }
        if (!StringsKt.startsWith$default(relative, "/", false, 2, (Object) null)) {
            relative = mergePaths(stripQueryAndFragment(url.getPath()), relative);
        }
        String str = relative;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, "?", 0, false, 6, (Object) null);
        int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str, "#", 0, false, 6, (Object) null);
        if (iIndexOf$default != -1 && iIndexOf$default2 != -1) {
            iIndexOf$default = Math.min(iIndexOf$default, iIndexOf$default2);
        } else if (iIndexOf$default2 != -1) {
            iIndexOf$default = iIndexOf$default2;
        }
        if (iIndexOf$default != -1) {
            strSubstring = relative.substring(iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            relative = relative.substring(0, iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(relative, "substring(...)");
        }
        String strNormalizePath = normalizePath(relative, url.getAuthority().length() > 0);
        if (strSubstring != null) {
            strNormalizePath = strNormalizePath + strSubstring;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(url.getScheme() + ":" + url.getSchemeSeparator() + url.getAuthority() + strNormalizePath);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final boolean isAbsoluteUrl(String url) {
        return url.length() > 2 && StringsKt.contains$default((CharSequence) url, (CharSequence) ":", false, 2, (Object) null);
    }

    private final String mergePaths(String basePath, String relativePath) {
        if (!StringsKt.endsWith$default(basePath, "/", false, 2, (Object) null)) {
            basePath = basePath.substring(0, StringsKt.lastIndexOf$default((CharSequence) basePath, '/', 0, false, 6, (Object) null) + 1);
            Intrinsics.checkNotNullExpressionValue(basePath, "substring(...)");
        }
        return basePath + relativePath;
    }

    static /* synthetic */ String normalizePath$default(URLUtil uRLUtil, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return uRLUtil.normalizePath(str, z);
    }

    private final String normalizePath(String path, boolean addRoot) {
        int i = 0;
        List mutableList = CollectionsKt.toMutableList((Collection) StringsKt.split$default((CharSequence) path, new String[]{"/"}, false, 0, 6, (Object) null));
        ArrayList arrayList = new ArrayList();
        Iterator it = mutableList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) next;
            if (str.length() == 0 || Intrinsics.areEqual(str, ".")) {
                if (i == mutableList.size() - 1) {
                    arrayList.add("");
                }
            } else if (Intrinsics.areEqual(str, "..")) {
                if (!arrayList.isEmpty()) {
                    arrayList.remove(arrayList.size() - 1);
                }
            } else {
                arrayList.add(str);
            }
            i = i2;
        }
        return (addRoot ? "/" : "") + CollectionsKt.joinToString$default(arrayList, "/", null, null, 0, null, null, 62, null);
    }

    private final String stripQueryAndFragment(String path) {
        String str = path;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, '?', 0, false, 6, (Object) null);
        int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str, '#', 0, false, 6, (Object) null);
        if (iIndexOf$default != -1) {
            String strSubstring = path.substring(0, iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return strSubstring;
        }
        if (iIndexOf$default2 == -1) {
            return path;
        }
        String strSubstring2 = path.substring(0, iIndexOf$default2);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        return strSubstring2;
    }

    /* JADX INFO: compiled from: URLUtil.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003JI\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/fleeksoft/ksoup/internal/URLUtil$ParsedUrl;", "", "scheme", "", "schemeSeparator", "authority", "path", SearchIntents.EXTRA_QUERY, "fragment", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getScheme", "()Ljava/lang/String;", "getSchemeSeparator", "getAuthority", "getPath", "getQuery", "getFragment", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final /* data */ class ParsedUrl {
        private final String authority;
        private final String fragment;
        private final String path;
        private final String query;
        private final String scheme;
        private final String schemeSeparator;

        public static /* synthetic */ ParsedUrl copy$default(ParsedUrl parsedUrl, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
            if ((i & 1) != 0) {
                str = parsedUrl.scheme;
            }
            if ((i & 2) != 0) {
                str2 = parsedUrl.schemeSeparator;
            }
            if ((i & 4) != 0) {
                str3 = parsedUrl.authority;
            }
            if ((i & 8) != 0) {
                str4 = parsedUrl.path;
            }
            if ((i & 16) != 0) {
                str5 = parsedUrl.query;
            }
            if ((i & 32) != 0) {
                str6 = parsedUrl.fragment;
            }
            String str7 = str5;
            String str8 = str6;
            return parsedUrl.copy(str, str2, str3, str4, str7, str8);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getScheme() {
            return this.scheme;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSchemeSeparator() {
            return this.schemeSeparator;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getAuthority() {
            return this.authority;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getPath() {
            return this.path;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getQuery() {
            return this.query;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getFragment() {
            return this.fragment;
        }

        public final ParsedUrl copy(String scheme, String schemeSeparator, String authority, String path, String query, String fragment) {
            Intrinsics.checkNotNullParameter(scheme, "scheme");
            Intrinsics.checkNotNullParameter(schemeSeparator, "schemeSeparator");
            Intrinsics.checkNotNullParameter(authority, "authority");
            Intrinsics.checkNotNullParameter(path, "path");
            return new ParsedUrl(scheme, schemeSeparator, authority, path, query, fragment);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ParsedUrl)) {
                return false;
            }
            ParsedUrl parsedUrl = (ParsedUrl) other;
            return Intrinsics.areEqual(this.scheme, parsedUrl.scheme) && Intrinsics.areEqual(this.schemeSeparator, parsedUrl.schemeSeparator) && Intrinsics.areEqual(this.authority, parsedUrl.authority) && Intrinsics.areEqual(this.path, parsedUrl.path) && Intrinsics.areEqual(this.query, parsedUrl.query) && Intrinsics.areEqual(this.fragment, parsedUrl.fragment);
        }

        public int hashCode() {
            int iHashCode = ((((((this.scheme.hashCode() * 31) + this.schemeSeparator.hashCode()) * 31) + this.authority.hashCode()) * 31) + this.path.hashCode()) * 31;
            String str = this.query;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.fragment;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "ParsedUrl(scheme=" + this.scheme + ", schemeSeparator=" + this.schemeSeparator + ", authority=" + this.authority + ", path=" + this.path + ", query=" + this.query + ", fragment=" + this.fragment + ")";
        }

        public ParsedUrl(String scheme, String schemeSeparator, String authority, String path, String str, String str2) {
            Intrinsics.checkNotNullParameter(scheme, "scheme");
            Intrinsics.checkNotNullParameter(schemeSeparator, "schemeSeparator");
            Intrinsics.checkNotNullParameter(authority, "authority");
            Intrinsics.checkNotNullParameter(path, "path");
            this.scheme = scheme;
            this.schemeSeparator = schemeSeparator;
            this.authority = authority;
            this.path = path;
            this.query = str;
            this.fragment = str2;
        }

        public /* synthetic */ ParsedUrl(String str, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6);
        }

        public final String getScheme() {
            return this.scheme;
        }

        public final String getSchemeSeparator() {
            return this.schemeSeparator;
        }

        public final String getAuthority() {
            return this.authority;
        }

        public final String getPath() {
            return this.path;
        }

        public final String getQuery() {
            return this.query;
        }

        public final String getFragment() {
            return this.fragment;
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x007a A[PHI: r1
      0x007a: PHI (r1v29 java.lang.Integer) = (r1v19 java.lang.Integer), (r1v22 java.lang.Integer), (r1v25 java.lang.Integer) binds: [B:19:0x0078, B:25:0x009c, B:31:0x00bc] A[DONT_GENERATE, DONT_INLINE]] */
    private final ParsedUrl parseUrl(String url) {
        String strSubstring;
        int length;
        String strSubstring2;
        String strSubstring3;
        String strSubstring4 = url;
        String str = strSubstring4;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, ":", 0, false, 6, (Object) null);
        String str2 = "//";
        if (iIndexOf$default != -1) {
            if (StringsKt.indexOf$default((CharSequence) str, "://", 0, false, 6, (Object) null) == -1) {
                str2 = StringsKt.indexOf$default((CharSequence) str, ":/", 0, false, 6, (Object) null) != -1 ? "/" : "";
            }
            strSubstring = strSubstring4.substring(0, iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            strSubstring4 = strSubstring4.substring(iIndexOf$default + str2.length() + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring4, "substring(...)");
        } else {
            strSubstring = "https";
        }
        String str3 = strSubstring;
        String str4 = str2;
        String strSubstring5 = null;
        if (Intrinsics.areEqual(str4, "/")) {
            length = -1;
        } else {
            String str5 = strSubstring4;
            Integer numValueOf = Integer.valueOf(StringsKt.indexOf$default((CharSequence) str5, '/', 0, false, 6, (Object) null));
            if (numValueOf.intValue() == -1) {
                numValueOf = null;
            }
            if (numValueOf != null) {
                length = numValueOf.intValue();
            } else {
                numValueOf = Integer.valueOf(StringsKt.indexOf$default((CharSequence) str5, '?', 0, false, 6, (Object) null));
                if (numValueOf.intValue() == -1) {
                    numValueOf = null;
                }
                if (numValueOf != null) {
                    length = numValueOf.intValue();
                } else {
                    numValueOf = Integer.valueOf(StringsKt.indexOf$default((CharSequence) str5, '#', 0, false, 6, (Object) null));
                    if (numValueOf.intValue() == -1) {
                        numValueOf = null;
                    }
                    if (numValueOf != null) {
                        length = numValueOf.intValue();
                    } else {
                        length = strSubstring4.length();
                    }
                }
            }
        }
        if (length != -1) {
            strSubstring2 = strSubstring4.substring(0, length);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        } else {
            strSubstring2 = null;
        }
        if (length != -1) {
            strSubstring4 = strSubstring4.substring(length);
            Intrinsics.checkNotNullExpressionValue(strSubstring4, "substring(...)");
        }
        String str6 = strSubstring4;
        Integer numValueOf2 = Integer.valueOf(StringsKt.indexOfAny$default((CharSequence) str6, new char[]{'?', '#'}, 0, false, 6, (Object) null));
        if (numValueOf2.intValue() == -1) {
            numValueOf2 = null;
        }
        String strSubstring6 = strSubstring4.substring(0, numValueOf2 != null ? numValueOf2.intValue() : strSubstring4.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring6, "substring(...)");
        Integer numValueOf3 = Integer.valueOf(StringsKt.indexOf$default((CharSequence) str6, '?', 0, false, 6, (Object) null));
        if (numValueOf3.intValue() == -1) {
            numValueOf3 = null;
        }
        int iIntValue = numValueOf3 != null ? numValueOf3.intValue() : strSubstring4.length();
        Integer numValueOf4 = Integer.valueOf(StringsKt.indexOf$default((CharSequence) str6, '#', 0, false, 6, (Object) null));
        if (numValueOf4.intValue() == -1) {
            numValueOf4 = null;
        }
        int iIntValue2 = numValueOf4 != null ? numValueOf4.intValue() : strSubstring4.length();
        if (iIntValue != strSubstring4.length()) {
            strSubstring3 = strSubstring4.substring(iIntValue, iIntValue2);
            Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
        } else {
            strSubstring3 = null;
        }
        if (iIntValue2 != strSubstring4.length()) {
            strSubstring5 = strSubstring4.substring(iIntValue2);
            Intrinsics.checkNotNullExpressionValue(strSubstring5, "substring(...)");
        }
        String str7 = strSubstring5;
        String str8 = strSubstring2;
        if (str8 == null) {
            str8 = "";
        }
        return new ParsedUrl(str3, str4, str8, strSubstring6, strSubstring3, str7);
    }
}
