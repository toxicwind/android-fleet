package androidx.core.text.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.net.MailTo;
import androidx.core.util.PatternsCompat;
import b.a.a.a.a;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class LinkifyCompat {
    public static final String[] EMPTY_STRING = new String[0];
    public static final Comparator<LinkSpec> COMPARATOR = new Comparator<LinkSpec>() { // from class: androidx.core.text.util.LinkifyCompat.1
        @Override // java.util.Comparator
        public int compare(LinkSpec a2, LinkSpec b2) {
            int i;
            int i2;
            int i3 = a2.start;
            int i4 = b2.start;
            if (i3 < i4) {
                return -1;
            }
            if (i3 <= i4 && (i = a2.end) >= (i2 = b2.end)) {
                return i > i2 ? -1 : 0;
            }
            return 1;
        }
    };

    private static class LinkSpec {
        public int end;
        public URLSpan frameworkAddedSpan;
        public int start;
        public String url;
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface LinkifyMask {
    }

    public static void addLinkMovementMethod(@NonNull TextView t) {
        if ((t.getMovementMethod() instanceof LinkMovementMethod) || !t.getLinksClickable()) {
            return;
        }
        t.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static boolean addLinks(@NonNull Spannable text, int mask) {
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(text, mask);
        }
        if (mask == 0) {
            return false;
        }
        URLSpan[] uRLSpanArr = (URLSpan[]) text.getSpans(0, text.length(), URLSpan.class);
        for (int length = uRLSpanArr.length - 1; length >= 0; length--) {
            text.removeSpan(uRLSpanArr[length]);
        }
        if ((mask & 4) != 0) {
            Linkify.addLinks(text, 4);
        }
        ArrayList<LinkSpec> arrayList = new ArrayList();
        if ((mask & 1) != 0) {
            gatherLinks(arrayList, text, PatternsCompat.AUTOLINK_WEB_URL, new String[]{"http://", "https://", "rtsp://"}, Linkify.sUrlMatchFilter, null);
        }
        if ((mask & 2) != 0) {
            gatherLinks(arrayList, text, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[]{MailTo.MAILTO_SCHEME}, null, null);
        }
        if ((mask & 8) != 0) {
            gatherMapLinks(arrayList, text);
        }
        pruneOverlaps(arrayList, text);
        if (arrayList.size() == 0) {
            return false;
        }
        for (LinkSpec linkSpec : arrayList) {
            if (linkSpec.frameworkAddedSpan == null) {
                applyLink(linkSpec.url, linkSpec.start, linkSpec.end, text);
            }
        }
        return true;
    }

    public static void applyLink(String url, int start, int end, Spannable text) {
        text.setSpan(new URLSpan(url), start, end, 33);
    }

    public static String findAddress(String addr) {
        return Build.VERSION.SDK_INT >= 28 ? WebView.findAddress(addr) : FindAddress.findAddress(addr);
    }

    public static void gatherLinks(ArrayList<LinkSpec> links, Spannable s, Pattern pattern, String[] schemes, Linkify.MatchFilter matchFilter, Linkify.TransformFilter transformFilter) {
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            int iStart = matcher.start();
            int iEnd = matcher.end();
            if (matchFilter == null || matchFilter.acceptMatch(s, iStart, iEnd)) {
                LinkSpec linkSpec = new LinkSpec();
                linkSpec.url = makeUrl(matcher.group(0), schemes, matcher, transformFilter);
                linkSpec.start = iStart;
                linkSpec.end = iEnd;
                links.add(linkSpec);
            }
        }
    }

    public static void gatherMapLinks(ArrayList<LinkSpec> links, Spannable s) {
        int iIndexOf;
        String string = s.toString();
        int i = 0;
        while (true) {
            try {
                String strFindAddress = findAddress(string);
                if (strFindAddress != null && (iIndexOf = string.indexOf(strFindAddress)) >= 0) {
                    LinkSpec linkSpec = new LinkSpec();
                    int length = strFindAddress.length() + iIndexOf;
                    linkSpec.start = iIndexOf + i;
                    i += length;
                    linkSpec.end = i;
                    string = string.substring(length);
                    try {
                        linkSpec.url = "geo:0,0?q=" + URLEncoder.encode(strFindAddress, "UTF-8");
                        links.add(linkSpec);
                    } catch (UnsupportedEncodingException unused) {
                    }
                }
                return;
            } catch (UnsupportedOperationException unused2) {
                return;
            }
        }
    }

    public static String makeUrl(@NonNull String url, @NonNull String[] prefixes, Matcher matcher, @Nullable Linkify.TransformFilter filter) {
        boolean z;
        if (filter != null) {
            url = filter.transformUrl(matcher, url);
        }
        int i = 0;
        while (true) {
            z = true;
            if (i >= prefixes.length) {
                z = false;
                break;
            }
            if (url.regionMatches(true, 0, prefixes[i], 0, prefixes[i].length())) {
                if (!url.regionMatches(false, 0, prefixes[i], 0, prefixes[i].length())) {
                    url = prefixes[i] + url.substring(prefixes[i].length());
                    break;
                }
                break;
            }
            i++;
        }
        return (z || prefixes.length <= 0) ? url : a.a(new StringBuilder(), prefixes[0], url);
    }

    public static void pruneOverlaps(ArrayList<LinkSpec> links, Spannable text) {
        int i;
        int i2 = 0;
        URLSpan[] uRLSpanArr = (URLSpan[]) text.getSpans(0, text.length(), URLSpan.class);
        for (int i3 = 0; i3 < uRLSpanArr.length; i3++) {
            LinkSpec linkSpec = new LinkSpec();
            linkSpec.frameworkAddedSpan = uRLSpanArr[i3];
            linkSpec.start = text.getSpanStart(uRLSpanArr[i3]);
            linkSpec.end = text.getSpanEnd(uRLSpanArr[i3]);
            links.add(linkSpec);
        }
        Collections.sort(links, COMPARATOR);
        int size = links.size();
        while (i2 < size - 1) {
            LinkSpec linkSpec2 = links.get(i2);
            int i4 = i2 + 1;
            LinkSpec linkSpec3 = links.get(i4);
            int i5 = linkSpec2.start;
            int i6 = linkSpec3.start;
            if (i5 <= i6 && (i = linkSpec2.end) > i6) {
                int i7 = linkSpec3.end;
                int i8 = (i7 > i && i - i5 <= i7 - i6) ? i - i5 < i7 - i6 ? i2 : -1 : i4;
                if (i8 != -1) {
                    Object obj = links.get(i8).frameworkAddedSpan;
                    if (obj != null) {
                        text.removeSpan(obj);
                    }
                    links.remove(i8);
                    size--;
                }
            }
            i2 = i4;
        }
    }

    public static boolean shouldAddLinksFallbackToFramework() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public static boolean addLinks(@NonNull TextView text, int mask) {
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(text, mask);
        }
        if (mask == 0) {
            return false;
        }
        CharSequence text2 = text.getText();
        if (text2 instanceof Spannable) {
            if (!addLinks((Spannable) text2, mask)) {
                return false;
            }
            addLinkMovementMethod(text);
            return true;
        }
        SpannableString spannableStringValueOf = SpannableString.valueOf(text2);
        if (!addLinks(spannableStringValueOf, mask)) {
            return false;
        }
        addLinkMovementMethod(text);
        text.setText(spannableStringValueOf);
        return true;
    }

    public static void addLinks(@NonNull TextView text, @NonNull Pattern pattern, @Nullable String scheme) {
        if (shouldAddLinksFallbackToFramework()) {
            Linkify.addLinks(text, pattern, scheme);
        } else {
            addLinks(text, pattern, scheme, (String[]) null, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
        }
    }

    public static void addLinks(@NonNull TextView text, @NonNull Pattern pattern, @Nullable String scheme, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        if (shouldAddLinksFallbackToFramework()) {
            Linkify.addLinks(text, pattern, scheme, matchFilter, transformFilter);
        } else {
            addLinks(text, pattern, scheme, (String[]) null, matchFilter, transformFilter);
        }
    }

    @SuppressLint({"NewApi"})
    public static void addLinks(@NonNull TextView text, @NonNull Pattern pattern, @Nullable String defaultScheme, @Nullable String[] schemes, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        if (shouldAddLinksFallbackToFramework()) {
            Linkify.addLinks(text, pattern, defaultScheme, schemes, matchFilter, transformFilter);
            return;
        }
        SpannableString spannableStringValueOf = SpannableString.valueOf(text.getText());
        if (addLinks(spannableStringValueOf, pattern, defaultScheme, schemes, matchFilter, transformFilter)) {
            text.setText(spannableStringValueOf);
            addLinkMovementMethod(text);
        }
    }

    public static boolean addLinks(@NonNull Spannable text, @NonNull Pattern pattern, @Nullable String scheme) {
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(text, pattern, scheme);
        }
        return addLinks(text, pattern, scheme, (String[]) null, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
    }

    public static boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String scheme, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(spannable, pattern, scheme, matchFilter, transformFilter);
        }
        return addLinks(spannable, pattern, scheme, (String[]) null, matchFilter, transformFilter);
    }

    @SuppressLint({"NewApi"})
    public static boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String defaultScheme, @Nullable String[] schemes, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(spannable, pattern, defaultScheme, schemes, matchFilter, transformFilter);
        }
        if (defaultScheme == null) {
            defaultScheme = "";
        }
        if (schemes == null || schemes.length < 1) {
            schemes = EMPTY_STRING;
        }
        String[] strArr = new String[schemes.length + 1];
        strArr[0] = defaultScheme.toLowerCase(Locale.ROOT);
        int i = 0;
        while (i < schemes.length) {
            String str = schemes[i];
            i++;
            strArr[i] = str == null ? "" : str.toLowerCase(Locale.ROOT);
        }
        Matcher matcher = pattern.matcher(spannable);
        boolean z = false;
        while (matcher.find()) {
            int iStart = matcher.start();
            int iEnd = matcher.end();
            if (matchFilter != null ? matchFilter.acceptMatch(spannable, iStart, iEnd) : true) {
                applyLink(makeUrl(matcher.group(0), strArr, matcher, transformFilter), iStart, iEnd, spannable);
                z = true;
            }
        }
        return z;
    }
}
