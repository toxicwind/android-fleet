package io.ktor.util;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: StringValues.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0010\u001c\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\u001aO\u0010\b\u001a\u00020\u000726\u0010\u0004\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u00010\u0000\"\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\f\u001a-\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00022\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\u000e\u001a\r\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\u000f\u001a1\u0010\b\u001a\u00020\u00072\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00110\u00102\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\u0013\u001a#\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0010*\u00020\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a#\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00010\u0003*\u00020\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001a+\u0010\u001b\u001a\u00020\u0019*\u00020\u00072\u0018\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0004\b\u001b\u0010\u001c\u001a5\u0010\u001f\u001a\u00020\u0007*\u00020\u00072\b\b\u0002\u0010\u001d\u001a\u00020\u00052\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0018¢\u0006\u0004\b\u001f\u0010 \u001a=\u0010#\u001a\u00020\u0019*\u00020!2\u0006\u0010\"\u001a\u00020\u00072\b\b\u0002\u0010\u001d\u001a\u00020\u00052\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0018¢\u0006\u0004\b#\u0010$\u001a\u0019\u0010&\u001a\u00020!*\u00020!2\u0006\u0010%\u001a\u00020!¢\u0006\u0004\b&\u0010'\u001a!\u0010(\u001a\u00020!*\u00020!2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b(\u0010)\u001a!\u0010*\u001a\u00020!*\u00020!2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b*\u0010)\u001a=\u0010&\u001a\u00020!*\u00020!2*\u0010\r\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00010\u0000\"\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0004\b&\u0010+\u001aK\u0010&\u001a\u00020!*\u00020!26\u0010\r\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00110\u00010\u0000\"\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00110\u0001H\u0007¢\u0006\u0004\b,\u0010+\u001a-\u0010&\u001a\u00020!*\u00020!2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00110\u0010H\u0007¢\u0006\u0004\b,\u0010-\u001a%\u0010&\u001a\u00020!*\u00020!2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0010¢\u0006\u0004\b&\u0010-\u001aO\u00102\u001a\u00020\u00052\u001e\u00100\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030/0.2\u001e\u00101\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030/0.H\u0002¢\u0006\u0004\b2\u00103\u001a7\u00107\u001a\u0002052\u001e\u00104\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030/0.2\u0006\u00106\u001a\u000205H\u0002¢\u0006\u0004\b7\u00108¨\u00069"}, d2 = {"", "Lkotlin/Pair;", "", "", "pairs", "", "caseInsensitiveKey", "Lio/ktor/util/StringValues;", "valuesOf", "([Lkotlin/Pair;Z)Lio/ktor/util/StringValues;", "name", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/String;Ljava/lang/String;Z)Lio/ktor/util/StringValues;", "values", "(Ljava/lang/String;Ljava/util/List;Z)Lio/ktor/util/StringValues;", "()Lio/ktor/util/StringValues;", "", "", "map", "(Ljava/util/Map;Z)Lio/ktor/util/StringValues;", "toMap", "(Lio/ktor/util/StringValues;)Ljava/util/Map;", "flattenEntries", "(Lio/ktor/util/StringValues;)Ljava/util/List;", "Lkotlin/Function2;", "", "block", "flattenForEach", "(Lio/ktor/util/StringValues;Lkotlin/jvm/functions/Function2;)V", "keepEmpty", "predicate", "filter", "(Lio/ktor/util/StringValues;ZLkotlin/jvm/functions/Function2;)Lio/ktor/util/StringValues;", "Lio/ktor/util/StringValuesBuilder;", "source", "appendFiltered", "(Lio/ktor/util/StringValuesBuilder;Lio/ktor/util/StringValues;ZLkotlin/jvm/functions/Function2;)V", "builder", "appendAll", "(Lio/ktor/util/StringValuesBuilder;Lio/ktor/util/StringValuesBuilder;)Lio/ktor/util/StringValuesBuilder;", "appendIfNameAbsent", "(Lio/ktor/util/StringValuesBuilder;Ljava/lang/String;Ljava/lang/String;)Lio/ktor/util/StringValuesBuilder;", "appendIfNameAndValueAbsent", "(Lio/ktor/util/StringValuesBuilder;[Lkotlin/Pair;)Lio/ktor/util/StringValuesBuilder;", "appendAllIterable", "(Lio/ktor/util/StringValuesBuilder;Ljava/util/Map;)Lio/ktor/util/StringValuesBuilder;", "", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "entriesEquals", "(Ljava/util/Set;Ljava/util/Set;)Z", "entries", "", "seed", "entriesHashCode", "(Ljava/util/Set;I)I", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class StringValuesKt {
    public static /* synthetic */ StringValues valuesOf$default(Pair[] pairArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return valuesOf((Pair<String, ? extends List<String>>[]) pairArr, z);
    }

    public static final StringValues valuesOf(Pair<String, ? extends List<String>>[] pairs, boolean z) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        return new StringValuesImpl(z, MapsKt.toMap(ArraysKt.asList(pairs)));
    }

    public static /* synthetic */ StringValues valuesOf$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return valuesOf(str, str2, z);
    }

    public static final StringValues valuesOf(String name, String value, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        return new StringValuesSingleImpl(z, name, kotlin.collections.CollectionsKt.listOf(value));
    }

    public static /* synthetic */ StringValues valuesOf$default(String str, List list, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return valuesOf(str, (List<String>) list, z);
    }

    public static final StringValues valuesOf(String name, List<String> values, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(values, "values");
        return new StringValuesSingleImpl(z, name, values);
    }

    public static final StringValues valuesOf() {
        return StringValues.INSTANCE.getEmpty();
    }

    public static /* synthetic */ StringValues valuesOf$default(Map map, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return valuesOf((Map<String, ? extends Iterable<String>>) map, z);
    }

    public static final StringValues valuesOf(Map<String, ? extends Iterable<String>> map, boolean z) {
        Intrinsics.checkNotNullParameter(map, "map");
        int size = map.size();
        if (size == 1) {
            Map.Entry entry = (Map.Entry) kotlin.collections.CollectionsKt.single(map.entrySet());
            return new StringValuesSingleImpl(z, (String) entry.getKey(), kotlin.collections.CollectionsKt.toList((Iterable) entry.getValue()));
        }
        LinkedHashMap linkedHashMapCaseInsensitiveMap = z ? CollectionsKt.caseInsensitiveMap() : new LinkedHashMap(size);
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it.next();
            linkedHashMapCaseInsensitiveMap.put(entry2.getKey(), kotlin.collections.CollectionsKt.toList((Iterable) entry2.getValue()));
        }
        return new StringValuesImpl(z, linkedHashMapCaseInsensitiveMap);
    }

    public static final Map<String, List<String>> toMap(StringValues stringValues) {
        Intrinsics.checkNotNullParameter(stringValues, "<this>");
        Set<Map.Entry<String, List<String>>> setEntries = stringValues.entries();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> it = setEntries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put((String) entry.getKey(), kotlin.collections.CollectionsKt.toList((Iterable) entry.getValue()));
        }
        return linkedHashMap;
    }

    public static final List<Pair<String, String>> flattenEntries(StringValues stringValues) {
        Intrinsics.checkNotNullParameter(stringValues, "<this>");
        Set<Map.Entry<String, List<String>>> setEntries = stringValues.entries();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = setEntries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Iterable iterable = (Iterable) entry.getValue();
            ArrayList arrayList2 = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator it2 = iterable.iterator();
            while (it2.hasNext()) {
                arrayList2.add(TuplesKt.to(entry.getKey(), (String) it2.next()));
            }
            kotlin.collections.CollectionsKt.addAll(arrayList, arrayList2);
        }
        return arrayList;
    }

    public static final void flattenForEach(StringValues stringValues, final Function2<? super String, ? super String, Unit> block) {
        Intrinsics.checkNotNullParameter(stringValues, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        stringValues.forEach(new Function2() { // from class: io.ktor.util.StringValuesKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return StringValuesKt.flattenForEach$lambda$0(block, (String) obj, (List) obj2);
            }
        });
    }

    static final Unit flattenForEach$lambda$0(Function2 function2, String name, List items) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(items, "items");
        Iterator it = items.iterator();
        while (it.hasNext()) {
            function2.invoke(name, (String) it.next());
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ StringValues filter$default(StringValues stringValues, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return filter(stringValues, z, function2);
    }

    public static final StringValues filter(StringValues stringValues, boolean z, Function2<? super String, ? super String, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(stringValues, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Set<Map.Entry<String, List<String>>> setEntries = stringValues.entries();
        LinkedHashMap linkedHashMapCaseInsensitiveMap = stringValues.getCaseInsensitiveName() ? CollectionsKt.caseInsensitiveMap() : new LinkedHashMap(setEntries.size());
        Iterator<T> it = setEntries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Iterable iterable = (Iterable) entry.getValue();
            ArrayList arrayList = new ArrayList(((List) entry.getValue()).size());
            for (Object obj : iterable) {
                if (predicate.invoke(entry.getKey(), (String) obj).booleanValue()) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = arrayList;
            if (z || !arrayList2.isEmpty()) {
                linkedHashMapCaseInsensitiveMap.put(entry.getKey(), arrayList2);
            }
        }
        return new StringValuesImpl(stringValues.getCaseInsensitiveName(), linkedHashMapCaseInsensitiveMap);
    }

    public static /* synthetic */ void appendFiltered$default(StringValuesBuilder stringValuesBuilder, StringValues stringValues, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        appendFiltered(stringValuesBuilder, stringValues, z, function2);
    }

    public static final void appendFiltered(final StringValuesBuilder stringValuesBuilder, StringValues source, final boolean z, final Function2<? super String, ? super String, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(stringValuesBuilder, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        source.forEach(new Function2() { // from class: io.ktor.util.StringValuesKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return StringValuesKt.appendFiltered$lambda$0(z, stringValuesBuilder, predicate, (String) obj, (List) obj2);
            }
        });
    }

    static final Unit appendFiltered$lambda$0(boolean z, StringValuesBuilder stringValuesBuilder, Function2 function2, String name, List value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        ArrayList arrayList = new ArrayList(value.size());
        for (Object obj : value) {
            if (((Boolean) function2.invoke(name, (String) obj)).booleanValue()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (z || !arrayList2.isEmpty()) {
            stringValuesBuilder.appendAll(name, arrayList2);
        }
        return Unit.INSTANCE;
    }

    public static final StringValuesBuilder appendAll(StringValuesBuilder stringValuesBuilder, StringValuesBuilder builder) {
        Intrinsics.checkNotNullParameter(stringValuesBuilder, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        Iterator<T> it = builder.entries().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            stringValuesBuilder.appendAll((String) entry.getKey(), (List) entry.getValue());
        }
        return stringValuesBuilder;
    }

    public static final StringValuesBuilder appendIfNameAbsent(StringValuesBuilder stringValuesBuilder, String name, String value) {
        Intrinsics.checkNotNullParameter(stringValuesBuilder, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        if (!stringValuesBuilder.contains(name)) {
            stringValuesBuilder.append(name, value);
        }
        return stringValuesBuilder;
    }

    public static final StringValuesBuilder appendIfNameAndValueAbsent(StringValuesBuilder stringValuesBuilder, String name, String value) {
        Intrinsics.checkNotNullParameter(stringValuesBuilder, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        if (!stringValuesBuilder.contains(name, value)) {
            stringValuesBuilder.append(name, value);
        }
        return stringValuesBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean entriesEquals(Set<? extends Map.Entry<String, ? extends List<String>>> set, Set<? extends Map.Entry<String, ? extends List<String>>> set2) {
        return Intrinsics.areEqual(set, set2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int entriesHashCode(Set<? extends Map.Entry<String, ? extends List<String>>> set, int i) {
        return (i * 31) + set.hashCode();
    }

    public static final StringValuesBuilder appendAll(StringValuesBuilder stringValuesBuilder, Pair<String, String>... values) {
        Intrinsics.checkNotNullParameter(stringValuesBuilder, "<this>");
        Intrinsics.checkNotNullParameter(values, "values");
        for (Pair<String, String> pair : values) {
            stringValuesBuilder.append(pair.component1(), pair.component2());
        }
        return stringValuesBuilder;
    }

    public static final StringValuesBuilder appendAllIterable(StringValuesBuilder stringValuesBuilder, Pair<String, ? extends Iterable<String>>... values) {
        Intrinsics.checkNotNullParameter(stringValuesBuilder, "<this>");
        Intrinsics.checkNotNullParameter(values, "values");
        for (Pair<String, ? extends Iterable<String>> pair : values) {
            stringValuesBuilder.appendAll(pair.component1(), pair.component2());
        }
        return stringValuesBuilder;
    }

    public static final StringValuesBuilder appendAllIterable(StringValuesBuilder stringValuesBuilder, Map<String, ? extends Iterable<String>> values) {
        Intrinsics.checkNotNullParameter(stringValuesBuilder, "<this>");
        Intrinsics.checkNotNullParameter(values, "values");
        for (Map.Entry<String, ? extends Iterable<String>> entry : values.entrySet()) {
            stringValuesBuilder.appendAll(entry.getKey(), entry.getValue());
        }
        return stringValuesBuilder;
    }

    public static final StringValuesBuilder appendAll(StringValuesBuilder stringValuesBuilder, Map<String, String> values) {
        Intrinsics.checkNotNullParameter(stringValuesBuilder, "<this>");
        Intrinsics.checkNotNullParameter(values, "values");
        for (Map.Entry<String, String> entry : values.entrySet()) {
            stringValuesBuilder.append(entry.getKey(), entry.getValue());
        }
        return stringValuesBuilder;
    }
}
