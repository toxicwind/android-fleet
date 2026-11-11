package com.lagradost.cloudstream3.utils;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: JsHunter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\b\u0010\b\u001a\u0004\u0018\u00010\u0003J\"\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\nH\u0002J(\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/utils/JsHunter;", "", "hunterJS", "", "<init>", "(Ljava/lang/String;)V", "detect", "", "dehunt", "duf", "", "d", "e", "f", "hunter", CmcdData.STREAMING_FORMAT_HLS, "n", "t", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JsHunter {
    private final String hunterJS;

    public JsHunter(String hunterJS) {
        Intrinsics.checkNotNullParameter(hunterJS, "hunterJS");
        this.hunterJS = hunterJS;
    }

    public final boolean detect() {
        return new Regex("eval\\(function\\(h,u,n,t,e,r\\)").containsMatchIn(this.hunterJS);
    }

    public final String dehunt() {
        try {
            MatchResult matchResultFind$default = Regex.find$default(new Regex("(?s)\\}\\(\"([^\"]+)\",[^,]+,\\s*\"([^\"]+)\",\\s*(\\d+),\\s*(\\d+)"), this.hunterJS, 0, 2, null);
            if (matchResultFind$default != null && matchResultFind$default.getGroupValues().size() == 5) {
                return hunter(matchResultFind$default.getGroupValues().get(1), matchResultFind$default.getGroupValues().get(2), Integer.parseInt(matchResultFind$default.getGroupValues().get(3)), Integer.parseInt(matchResultFind$default.getGroupValues().get(4)));
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        return null;
    }

    static /* synthetic */ int duf$default(JsHunter jsHunter, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = 10;
        }
        return jsHunter.duf(str, i, i2);
    }

    private final int duf(String d, int e, int f) {
        List<Character> list = StringsKt.toList("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+/");
        List listTake = CollectionsKt.take(list, e);
        List listTake2 = CollectionsKt.take(list, f);
        int i = 0;
        double dIndexOf = 0.0d;
        for (Character ch : StringsKt.toList(StringsKt.reversed((CharSequence) d).toString())) {
            int i2 = i + 1;
            ch.charValue();
            if (listTake.contains(ch)) {
                dIndexOf += ((double) listTake.indexOf(ch)) * Math.pow(e, i);
            }
            i = i2;
        }
        String str = "";
        while (dIndexOf > 0.0d) {
            double d2 = f;
            double d3 = dIndexOf % d2;
            str = ((Character) listTake2.get((int) d3)).charValue() + str;
            dIndexOf = (dIndexOf - d3) / d2;
        }
        Integer intOrNull = StringsKt.toIntOrNull(str);
        if (intOrNull != null) {
            return intOrNull.intValue();
        }
        return 0;
    }

    private final String hunter(String h, String n, int t, int e) {
        String string = "";
        int i = 0;
        while (i < h.length()) {
            String str = "";
            while (h.charAt(i) != n.charAt(e)) {
                str = str + h.charAt(i);
                i++;
            }
            String strReplace$default = str;
            for (int i2 = 0; i2 < n.length(); i2++) {
                strReplace$default = StringsKt.replace$default(strReplace$default, n.charAt(i2), CharsKt.digitToChar(i2), false, 4, (Object) null);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            String str2 = strReplace$default;
            JsHunter jsHunter = this;
            sb.append((char) (duf$default(jsHunter, str2, e, 0, 4, null) - t));
            string = sb.toString();
            i++;
            this = jsHunter;
        }
        return string;
    }
}
