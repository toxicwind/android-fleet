package io.ktor.http;

import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HttpUrlEncoded.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0002\u001a+\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u00060\u0001j\u0002`\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a3\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u00060\u0001j\u0002`\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0007\u0010\u000b\u001a%\u0010\u000e\u001a\u00020\u0000*\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\r0\f¢\u0006\u0004\b\u000e\u0010\u000f\u001a1\u0010\u0014\u001a\u00020\u0013*\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\r0\f2\n\u0010\u0012\u001a\u00060\u0010j\u0002`\u0011¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0011\u0010\u000e\u001a\u00020\u0000*\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u0016\u001a\u001d\u0010\u0014\u001a\u00020\u0013*\u00020\u00062\n\u0010\u0012\u001a\u00060\u0010j\u0002`\u0011¢\u0006\u0004\b\u0014\u0010\u0017\u001a\u001f\u0010\u0014\u001a\u00020\u0013*\u00020\u00182\n\u0010\u0012\u001a\u00060\u0010j\u0002`\u0011H\u0000¢\u0006\u0004\b\u0014\u0010\u0019\u001a7\u0010\u0014\u001a\u00020\u0013*\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00000\f0\u001b0\u001a2\n\u0010\u0012\u001a\u00060\u0010j\u0002`\u0011H\u0000¢\u0006\u0004\b\u0014\u0010\u001c¨\u0006\u001d"}, d2 = {"", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "defaultEncoding", "", "limit", "Lio/ktor/http/Parameters;", "parseUrlEncodedParameters", "(Ljava/lang/String;Ljava/nio/charset/Charset;I)Lio/ktor/http/Parameters;", "", "plusIsSpace", "(Ljava/lang/String;Ljava/nio/charset/Charset;IZ)Lio/ktor/http/Parameters;", "", "Lkotlin/Pair;", "formUrlEncode", "(Ljava/util/List;)Ljava/lang/String;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "out", "", "formUrlEncodeTo", "(Ljava/util/List;Ljava/lang/Appendable;)V", "(Lio/ktor/http/Parameters;)Ljava/lang/String;", "(Lio/ktor/http/Parameters;Ljava/lang/Appendable;)V", "Lio/ktor/http/ParametersBuilder;", "(Lio/ktor/http/ParametersBuilder;Ljava/lang/Appendable;)V", "", "", "(Ljava/util/Set;Ljava/lang/Appendable;)V", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class HttpUrlEncodedKt {
    public static /* synthetic */ Parameters parseUrlEncodedParameters$default(String str, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = 1000;
        }
        return parseUrlEncodedParameters(str, charset, i);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public static final /* synthetic */ Parameters parseUrlEncodedParameters(String str, Charset defaultEncoding, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(defaultEncoding, "defaultEncoding");
        return parseUrlEncodedParameters$default(str, defaultEncoding, i, false, 4, null);
    }

    public static /* synthetic */ Parameters parseUrlEncodedParameters$default(String str, Charset charset, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = 1000;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return parseUrlEncodedParameters(str, charset, i, z);
    }

    public static final Parameters parseUrlEncodedParameters(String str, Charset defaultEncoding, int i, boolean z) {
        Object next;
        String name;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(defaultEncoding, "defaultEncoding");
        List<String> listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{"&"}, false, i, 2, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
        for (String str2 : listSplit$default) {
            arrayList.add(TuplesKt.to(StringsKt.substringBefore$default(str2, "=", (String) null, 2, (Object) null), StringsKt.substringAfter(str2, "=", "")));
        }
        ArrayList<Pair> arrayList2 = arrayList;
        Iterator it = arrayList2.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((Pair) next).getFirst(), "_charset_"));
        Pair pair = (Pair) next;
        if (pair == null || (name = (String) pair.getSecond()) == null) {
            name = CharsetJVMKt.getName(defaultEncoding);
        }
        Charset charsetForName = CharsetJVMKt.forName(Charsets.INSTANCE, name);
        Parameters.Companion companion = Parameters.INSTANCE;
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        for (Pair pair2 : arrayList2) {
            boolean z2 = z;
            parametersBuilderParametersBuilder$default.append(CodecsKt.decodeURLQueryComponent$default((String) pair2.component1(), 0, 0, z2, charsetForName, 3, null), CodecsKt.decodeURLQueryComponent$default((String) pair2.component2(), 0, 0, z2, charsetForName, 3, null));
            z = z2;
        }
        return parametersBuilderParametersBuilder$default.build();
    }

    public static final String formUrlEncode(List<Pair<String, String>> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        StringBuilder sb = new StringBuilder();
        formUrlEncodeTo(list, sb);
        return sb.toString();
    }

    public static final void formUrlEncodeTo(List<Pair<String, String>> list, Appendable out) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        CollectionsKt.joinTo(list, out, (60 & 2) != 0 ? ", " : "&", (60 & 4) != 0 ? "" : null, (60 & 8) != 0 ? "" : null, (60 & 16) != 0 ? -1 : 0, (60 & 32) != 0 ? "..." : null, (60 & 64) != 0 ? null : new Function1() { // from class: io.ktor.http.HttpUrlEncodedKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HttpUrlEncodedKt.formUrlEncodeTo$lambda$0((Pair) obj);
            }
        });
    }

    static final CharSequence formUrlEncodeTo$lambda$0(Pair it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String strEncodeURLParameter = CodecsKt.encodeURLParameter((String) it.getFirst(), true);
        if (it.getSecond() == null) {
            return strEncodeURLParameter;
        }
        return strEncodeURLParameter + '=' + CodecsKt.encodeURLParameterValue(String.valueOf(it.getSecond()));
    }

    public static final String formUrlEncode(Parameters parameters) {
        Intrinsics.checkNotNullParameter(parameters, "<this>");
        Set<Map.Entry<String, List<String>>> setEntries = parameters.entries();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = setEntries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Iterable iterable = (Iterable) entry.getValue();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator it2 = iterable.iterator();
            while (it2.hasNext()) {
                arrayList2.add(TuplesKt.to(entry.getKey(), (String) it2.next()));
            }
            CollectionsKt.addAll(arrayList, arrayList2);
        }
        return formUrlEncode(arrayList);
    }

    public static final void formUrlEncodeTo(Parameters parameters, Appendable out) {
        Intrinsics.checkNotNullParameter(parameters, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        formUrlEncodeTo(parameters.entries(), out);
    }

    public static final void formUrlEncodeTo(ParametersBuilder parametersBuilder, Appendable out) {
        Intrinsics.checkNotNullParameter(parametersBuilder, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        formUrlEncodeTo(parametersBuilder.entries(), out);
    }

    public static final void formUrlEncodeTo(Set<? extends Map.Entry<String, ? extends List<String>>> set, Appendable out) {
        ArrayList arrayListListOf;
        Intrinsics.checkNotNullParameter(set, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            if (list.isEmpty()) {
                arrayListListOf = CollectionsKt.listOf(TuplesKt.to(str, null));
            } else {
                List list2 = list;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator it2 = list2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(TuplesKt.to(str, (String) it2.next()));
                }
                arrayListListOf = arrayList2;
            }
            CollectionsKt.addAll(arrayList, arrayListListOf);
        }
        formUrlEncodeTo(arrayList, out);
    }
}
