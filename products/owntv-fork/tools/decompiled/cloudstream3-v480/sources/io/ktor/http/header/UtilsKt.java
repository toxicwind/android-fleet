package io.ktor.http.header;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: utils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u001a3\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a-\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"", "", "values", "", "separator", "", "splitInsideQuotes", "splitHeaderValues", "(Ljava/util/List;CZ)Ljava/util/List;", ES6Iterator.VALUE_PROPERTY, "splitHeaderValue", "(Ljava/lang/String;CZ)Ljava/util/List;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class UtilsKt {
    public static final List<String> splitHeaderValues(List<String> values, char c, boolean z) {
        Intrinsics.checkNotNullParameter(values, "values");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = values.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, splitHeaderValue((String) it.next(), c, z));
        }
        return arrayList;
    }

    private static final List<String> splitHeaderValue(String str, char c, boolean z) {
        if (z) {
            List listSplit$default = StringsKt.split$default((CharSequence) str, new char[]{c}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
            Iterator it = listSplit$default.iterator();
            while (it.hasNext()) {
                arrayList.add(StringsKt.trim((CharSequence) it.next()).toString());
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (((String) obj).length() > 0) {
                    arrayList2.add(obj);
                }
            }
            return arrayList2;
        }
        ArrayList arrayList3 = new ArrayList();
        int i = 0;
        int i2 = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (z2) {
                if (z3) {
                    z3 = false;
                } else if (cCharAt == '\\') {
                    z3 = true;
                } else if (cCharAt == '\"') {
                    z2 = false;
                }
            } else if (cCharAt == '\"') {
                i++;
                z2 = true;
            } else if (cCharAt == c) {
                splitHeaderValue$emit(str, arrayList3, i2, i);
                i2 = i + 1;
                i = i2;
            }
            i++;
        }
        splitHeaderValue$emit(str, arrayList3, i2, str.length());
        return arrayList3;
    }

    private static final void splitHeaderValue$emit(String str, List<String> list, int i, int i2) {
        String strSubstring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        String string = StringsKt.trim((CharSequence) strSubstring).toString();
        if (string.length() > 0) {
            list.add(string);
        }
    }
}
