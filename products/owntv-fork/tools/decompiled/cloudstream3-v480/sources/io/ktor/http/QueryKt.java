package io.ktor.http;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.actions.SearchIntents;
import io.ktor.util.StringValues;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Query.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\r\n\u0002\b\u0007\u001a3\u0010\b\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\t\u001a3\u0010\f\u001a\u00020\u000b*\u00020\n2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\f\u0010\r\u001a;\u0010\u0011\u001a\u00020\u000b*\u00020\n2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0011\u0010\u0012\u001a'\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a'\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0019\u0010\u0018\u001a\u0013\u0010\u001a\u001a\u00020\u0007*\u00020\u0007H\u0007¢\u0006\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"", SearchIntents.EXTRA_QUERY, "", "startIndex", "limit", "", "decode", "Lio/ktor/http/Parameters;", "parseQueryString", "(Ljava/lang/String;IIZ)Lio/ktor/http/Parameters;", "Lio/ktor/http/ParametersBuilder;", "", "parse", "(Lio/ktor/http/ParametersBuilder;Ljava/lang/String;IIZ)V", "nameIndex", "equalIndex", "endIndex", "appendParam", "(Lio/ktor/http/ParametersBuilder;Ljava/lang/String;IIIZ)V", "start", TtmlNode.END, "", "text", "trimEnd", "(IILjava/lang/CharSequence;)I", "trimStart", "withEmptyStringForValuelessKeys", "(Lio/ktor/http/Parameters;)Lio/ktor/http/Parameters;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class QueryKt {
    public static /* synthetic */ Parameters parseQueryString$default(String str, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = 1000;
        }
        if ((i3 & 8) != 0) {
            z = true;
        }
        return parseQueryString(str, i, i2, z);
    }

    public static final Parameters parseQueryString(String query, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(query, "query");
        if (i > StringsKt.getLastIndex(query)) {
            return Parameters.INSTANCE.getEmpty();
        }
        Parameters.Companion companion = Parameters.INSTANCE;
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        parse(parametersBuilderParametersBuilder$default, query, i, i2, z);
        return parametersBuilderParametersBuilder$default.build();
    }

    private static final void parse(ParametersBuilder parametersBuilder, String str, int i, int i2, boolean z) {
        int i3;
        int lastIndex = StringsKt.getLastIndex(str);
        int i4 = 0;
        int i5 = i;
        if (i <= lastIndex) {
            int i6 = i5;
            int i7 = 0;
            i3 = -1;
            while (i7 != i2) {
                char cCharAt = str.charAt(i6);
                if (cCharAt == '&') {
                    appendParam(parametersBuilder, str, i5, i3, i6, z);
                    i7++;
                    i5 = i6 + 1;
                    i3 = -1;
                } else if (cCharAt == '=' && i3 == -1) {
                    i3 = i6;
                }
                if (i6 != lastIndex) {
                    i6++;
                } else {
                    i4 = i7;
                }
            }
            return;
        }
        i3 = -1;
        if (i4 == i2) {
            return;
        }
        appendParam(parametersBuilder, str, i5, i3, str.length(), z);
    }

    private static final void appendParam(ParametersBuilder parametersBuilder, String str, int i, int i2, int i3, boolean z) {
        String strSubstring;
        String strSubstring2;
        String strSubstring3;
        if (i2 == -1) {
            String str2 = str;
            int iTrimStart = trimStart(i, i3, str2);
            int iTrimEnd = trimEnd(iTrimStart, i3, str2);
            if (iTrimEnd > iTrimStart) {
                if (z) {
                    strSubstring3 = CodecsKt.decodeURLQueryComponent$default(str, iTrimStart, iTrimEnd, false, null, 12, null);
                } else {
                    strSubstring3 = str.substring(iTrimStart, iTrimEnd);
                    Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                }
                parametersBuilder.appendAll(strSubstring3, CollectionsKt.emptyList());
                return;
            }
            return;
        }
        String str3 = str;
        int iTrimStart2 = trimStart(i, i2, str3);
        int iTrimEnd2 = trimEnd(iTrimStart2, i2, str3);
        if (iTrimEnd2 > iTrimStart2) {
            if (z) {
                strSubstring = CodecsKt.decodeURLQueryComponent$default(str, iTrimStart2, iTrimEnd2, false, null, 12, null);
            } else {
                strSubstring = str.substring(iTrimStart2, iTrimEnd2);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            }
            int iTrimStart3 = trimStart(i2 + 1, i3, str3);
            int iTrimEnd3 = trimEnd(iTrimStart3, i3, str3);
            if (z) {
                strSubstring2 = CodecsKt.decodeURLQueryComponent$default(str, iTrimStart3, iTrimEnd3, true, null, 8, null);
            } else {
                strSubstring2 = str.substring(iTrimStart3, iTrimEnd3);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            }
            parametersBuilder.append(strSubstring, strSubstring2);
        }
    }

    private static final int trimEnd(int i, int i2, CharSequence charSequence) {
        while (i2 > i && CharsKt.isWhitespace(charSequence.charAt(i2 - 1))) {
            i2--;
        }
        return i2;
    }

    private static final int trimStart(int i, int i2, CharSequence charSequence) {
        while (i < i2 && CharsKt.isWhitespace(charSequence.charAt(i))) {
            i++;
        }
        return i;
    }

    public static final Parameters withEmptyStringForValuelessKeys(final Parameters parameters) {
        Intrinsics.checkNotNullParameter(parameters, "<this>");
        Set<Map.Entry<String, List<String>>> setEntries = parameters.entries();
        if ((setEntries instanceof Collection) && setEntries.isEmpty()) {
            return parameters;
        }
        Iterator<T> it = setEntries.iterator();
        while (it.hasNext()) {
            if (((List) ((Map.Entry) it.next()).getValue()).isEmpty()) {
                return new Parameters() { // from class: io.ktor.http.QueryKt$withEmptyStringForValuelessKeys$2$1
                    @Override // io.ktor.util.StringValues
                    public /* bridge */ boolean contains(String str) {
                        return StringValues.CC.$default$contains(this, str);
                    }

                    @Override // io.ktor.util.StringValues
                    public /* bridge */ boolean contains(String str, String str2) {
                        return StringValues.CC.$default$contains(this, str, str2);
                    }

                    @Override // io.ktor.util.StringValues
                    public /* bridge */ void forEach(Function2<? super String, ? super List<String>, Unit> function2) {
                        StringValues.CC.$default$forEach(this, function2);
                    }

                    @Override // io.ktor.util.StringValues
                    public String get(String name) {
                        Intrinsics.checkNotNullParameter(name, "name");
                        List<String> all = getAll(name);
                        if (all == null) {
                            return null;
                        }
                        return all.isEmpty() ? "" : (String) CollectionsKt.first((List) all);
                    }

                    @Override // io.ktor.util.StringValues
                    public boolean getCaseInsensitiveName() {
                        return parameters.getCaseInsensitiveName();
                    }

                    @Override // io.ktor.util.StringValues
                    public List<String> getAll(String name) {
                        Intrinsics.checkNotNullParameter(name, "name");
                        return parameters.getAll(name);
                    }

                    @Override // io.ktor.util.StringValues
                    public Set<String> names() {
                        return parameters.names();
                    }

                    @Override // io.ktor.util.StringValues
                    public Set<Map.Entry<String, List<String>>> entries() {
                        return parameters.entries();
                    }

                    @Override // io.ktor.util.StringValues
                    public boolean isEmpty() {
                        return parameters.isEmpty();
                    }
                };
            }
        }
        return parameters;
    }
}
