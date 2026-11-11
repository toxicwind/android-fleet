package io.ktor.util;

import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: StringValues.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\n\b\u0016\u0018\u0000 =2\u00020\u0001:\u0002>=B-\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u0004¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0005H\u0096\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u000f\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u000f\u0010\u0012J\u0015\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J'\u0010\u0019\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00180\u0013H\u0016¢\u0006\u0004\b\u0019\u0010\u0015J/\u0010\u001d\u001a\u00020\u001b2\u001e\u0010\u001c\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0006\u0012\u0004\u0012\u00020\u001b0\u001aH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u001f\u0010\u000eJ\u0017\u0010\"\u001a\u00020!2\u0006\u0010 \u001a\u00020\u0005H\u0002¢\u0006\u0004\b\"\u0010#J\u0011\u0010$\u001a\u00020\u0005H\u0096\u0080\u0004¢\u0006\u0004\b$\u0010%J\u001b\u0010(\u001a\u00020\u00022\b\u0010'\u001a\u0004\u0018\u00010&H\u0096\u0082\u0004¢\u0006\u0004\b(\u0010)J\u0011\u0010*\u001a\u00020!H\u0096\u0080\u0004¢\u0006\u0004\b*\u0010+R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010,\u001a\u0004\b-\u0010\u0017R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00050.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R \u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u0014\u00103\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0014\u00106\u001a\u0002058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u0014\u00108\u001a\u0002058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00107R,\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00048DX\u0084\u0004¢\u0006\f\u0012\u0004\b;\u0010<\u001a\u0004\b9\u0010:¨\u0006?"}, d2 = {"Lio/ktor/util/StringValuesImpl;", "Lio/ktor/util/StringValues;", "", "caseInsensitiveName", "", "", "", "values", "<init>", "(ZLjava/util/Map;)V", "name", "get", "(Ljava/lang/String;)Ljava/lang/String;", "getAll", "(Ljava/lang/String;)Ljava/util/List;", "contains", "(Ljava/lang/String;)Z", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/String;Ljava/lang/String;)Z", "", "names", "()Ljava/util/Set;", "isEmpty", "()Z", "", "entries", "Lkotlin/Function2;", "", TtmlNode.TAG_BODY, "forEach", "(Lkotlin/jvm/functions/Function2;)V", "listForKey", "key", "", "computeHash", "(Ljava/lang/String;)I", "toString", "()Ljava/lang/String;", "", "other", "equals", Signatures.MATH_TO_BOOLEAN, "hashCode", "()I", "Z", "getCaseInsensitiveName", "", "keyStorage", "[Ljava/lang/String;", "valueStorage", "[Ljava/util/List;", "entryCount", "I", "", "hashBuckets", "[I", "hashNext", "getValues", "()Ljava/util/Map;", "getValues$annotations", "()V", "Companion", "StringValuesEntry", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class StringValuesImpl implements StringValues {
    private static final Companion Companion = new Companion(null);
    private final boolean caseInsensitiveName;
    private final int entryCount;
    private final int[] hashBuckets;
    private final int[] hashNext;
    private final String[] keyStorage;
    private final List<String>[] valueStorage;

    /* JADX WARN: Multi-variable type inference failed */
    public StringValuesImpl() {
        this(false, null, 3, 0 == true ? 1 : 0);
    }

    protected static /* synthetic */ void getValues$annotations() {
    }

    public StringValuesImpl(boolean z, Map<String, ? extends List<String>> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        this.caseInsensitiveName = z;
        if (values.isEmpty()) {
            this.entryCount = 0;
            this.keyStorage = new String[0];
            this.valueStorage = new List[0];
            this.hashBuckets = new int[0];
            this.hashNext = new int[0];
            return;
        }
        if (!z) {
            int size = values.size();
            this.entryCount = size;
            this.keyStorage = new String[size];
            this.valueStorage = new List[size];
            int iTableSizeFor = Companion.tableSizeFor(size);
            int[] iArr = new int[iTableSizeFor];
            for (int i = 0; i < iTableSizeFor; i++) {
                iArr[i] = -1;
            }
            this.hashBuckets = iArr;
            int i2 = this.entryCount;
            int[] iArr2 = new int[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                iArr2[i3] = -1;
            }
            this.hashNext = iArr2;
            int i4 = 0;
            for (Map.Entry<String, ? extends List<String>> entry : values.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                this.keyStorage[i4] = key;
                List<String>[] listArr = this.valueStorage;
                int size2 = value.size();
                ArrayList arrayList = new ArrayList(size2);
                for (int i5 = 0; i5 < size2; i5++) {
                    arrayList.add(value.get(i5));
                }
                listArr[i4] = arrayList;
                int iComputeHash = computeHash(key) & (iTableSizeFor - 1);
                int[] iArr3 = this.hashNext;
                int[] iArr4 = this.hashBuckets;
                iArr3[i4] = iArr4[iComputeHash];
                iArr4[iComputeHash] = i4;
                i4++;
            }
            return;
        }
        Map mapCaseInsensitiveMap = CollectionsKt.caseInsensitiveMap();
        for (Map.Entry<String, ? extends List<String>> entry2 : values.entrySet()) {
            String key2 = entry2.getKey();
            List<String> value2 = entry2.getValue();
            List list = (List) mapCaseInsensitiveMap.get(key2);
            if (list != null) {
                mapCaseInsensitiveMap.put(key2, kotlin.collections.CollectionsKt.plus((Collection) list, (Iterable) value2));
            } else {
                mapCaseInsensitiveMap.put(key2, value2);
            }
        }
        int size3 = mapCaseInsensitiveMap.size();
        this.entryCount = size3;
        this.keyStorage = new String[size3];
        this.valueStorage = new List[size3];
        int iTableSizeFor2 = Companion.tableSizeFor(size3);
        int[] iArr5 = new int[iTableSizeFor2];
        for (int i6 = 0; i6 < iTableSizeFor2; i6++) {
            iArr5[i6] = -1;
        }
        this.hashBuckets = iArr5;
        int i7 = this.entryCount;
        int[] iArr6 = new int[i7];
        for (int i8 = 0; i8 < i7; i8++) {
            iArr6[i8] = -1;
        }
        this.hashNext = iArr6;
        int i9 = 0;
        for (Map.Entry entry3 : mapCaseInsensitiveMap.entrySet()) {
            String str = (String) entry3.getKey();
            List list2 = (List) entry3.getValue();
            this.keyStorage[i9] = str;
            List<String>[] listArr2 = this.valueStorage;
            int size4 = list2.size();
            ArrayList arrayList2 = new ArrayList(size4);
            for (int i10 = 0; i10 < size4; i10++) {
                arrayList2.add((String) list2.get(i10));
            }
            listArr2[i9] = arrayList2;
            int iComputeHash2 = computeHash(str) & (iTableSizeFor2 - 1);
            int[] iArr7 = this.hashNext;
            int[] iArr8 = this.hashBuckets;
            iArr7[i9] = iArr8[iComputeHash2];
            iArr8[iComputeHash2] = i9;
            i9++;
        }
    }

    @Override // io.ktor.util.StringValues
    public final boolean getCaseInsensitiveName() {
        return this.caseInsensitiveName;
    }

    public /* synthetic */ StringValuesImpl(boolean z, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? MapsKt.emptyMap() : map);
    }

    protected final Map<String, List<String>> getValues() {
        if (this.entryCount == 0) {
            return MapsKt.emptyMap();
        }
        LinkedHashMap linkedHashMapCaseInsensitiveMap = this.caseInsensitiveName ? CollectionsKt.caseInsensitiveMap() : new LinkedHashMap(this.entryCount);
        int i = this.entryCount;
        for (int i2 = 0; i2 < i; i2++) {
            linkedHashMapCaseInsensitiveMap.put(this.keyStorage[i2], this.valueStorage[i2]);
        }
        return linkedHashMapCaseInsensitiveMap;
    }

    @Override // io.ktor.util.StringValues
    public String get(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        List<String> listListForKey = listForKey(name);
        if (listListForKey != null) {
            return (String) kotlin.collections.CollectionsKt.firstOrNull((List) listListForKey);
        }
        return null;
    }

    @Override // io.ktor.util.StringValues
    public List<String> getAll(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return listForKey(name);
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return listForKey(name) != null;
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(String name, String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        List<String> listListForKey = listForKey(name);
        if (listListForKey != null) {
            return listListForKey.contains(value);
        }
        return false;
    }

    @Override // io.ktor.util.StringValues
    public Set<String> names() {
        if (this.entryCount == 0) {
            return SetsKt.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i = this.entryCount;
        for (int i2 = 0; i2 < i; i2++) {
            linkedHashSet.add(this.keyStorage[i2]);
        }
        return linkedHashSet;
    }

    @Override // io.ktor.util.StringValues
    public boolean isEmpty() {
        return this.entryCount == 0;
    }

    @Override // io.ktor.util.StringValues
    public Set<Map.Entry<String, List<String>>> entries() {
        if (this.entryCount == 0) {
            return SetsKt.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i = this.entryCount;
        for (int i2 = 0; i2 < i; i2++) {
            linkedHashSet.add(new StringValuesEntry(this.keyStorage[i2], this.valueStorage[i2]));
        }
        return linkedHashSet;
    }

    @Override // io.ktor.util.StringValues
    public void forEach(Function2<? super String, ? super List<String>, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        int i = this.entryCount;
        for (int i2 = 0; i2 < i; i2++) {
            body.invoke(this.keyStorage[i2], this.valueStorage[i2]);
        }
    }

    private final List<String> listForKey(String name) {
        if (this.entryCount == 0) {
            return null;
        }
        int iComputeHash = computeHash(name);
        int[] iArr = this.hashBuckets;
        int i = iArr[iComputeHash & (iArr.length - 1)];
        while (i >= 0) {
            if (StringsKt.equals(this.keyStorage[i], name, this.caseInsensitiveName)) {
                return this.valueStorage[i];
            }
            i = this.hashNext[i];
        }
        return null;
    }

    private final int computeHash(String key) {
        if (!this.caseInsensitiveName) {
            return key.hashCode();
        }
        return Companion.caseInsensitiveHashCode(key);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StringValues(case=");
        sb.append(!this.caseInsensitiveName);
        sb.append(") ");
        sb.append(entries());
        return sb.toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StringValues)) {
            return false;
        }
        StringValues stringValues = (StringValues) other;
        if (this.caseInsensitiveName != stringValues.getCaseInsensitiveName()) {
            return false;
        }
        return StringValuesKt.entriesEquals(entries(), stringValues.entries());
    }

    public int hashCode() {
        return StringValuesKt.entriesHashCode(entries(), UByte$$ExternalSyntheticBackport0.m(this.caseInsensitiveName) * 31);
    }

    /* JADX INFO: compiled from: StringValues.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\u0002\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0096\u0082\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0011\u0010\u000e\u001a\u00020\rH\u0096\u0080\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0010\u001a\u00020\u0002H\u0096\u0080\u0004¢\u0006\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0004\u001a\u00020\u00028\u0016X\u0096\u0084\b¢\u0006\f\n\u0004\b\u0004\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00038\u0016X\u0096\u0084\b¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lio/ktor/util/StringValuesImpl$StringValuesEntry;", "", "", "", "key", ES6Iterator.VALUE_PROPERTY, "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getKey", "Ljava/util/List;", "getValue", "()Ljava/util/List;", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class StringValuesEntry implements Map.Entry<String, List<? extends String>>, KMappedMarker {
        private final String key;
        private final List<String> value;

        @Override // java.util.Map.Entry
        public /* bridge */ /* synthetic */ List<? extends String> setValue(List<? extends String> list) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        /* JADX INFO: renamed from: setValue, reason: avoid collision after fix types in other method */
        public List<String> setValue2(List<String> list) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public StringValuesEntry(String key, List<String> value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            this.key = key;
            this.value = value;
        }

        @Override // java.util.Map.Entry
        public String getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public List<? extends String> getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object other) {
            if (!(other instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) other;
            return Intrinsics.areEqual(entry.getKey(), getKey()) && Intrinsics.areEqual(entry.getValue(), getValue());
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return getValue().hashCode() ^ getKey().hashCode();
        }

        public String toString() {
            return getKey() + '=' + getValue();
        }
    }

    /* JADX INFO: compiled from: StringValues.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/util/StringValuesImpl$Companion;", "", "<init>", "()V", "", ContentDisposition.Parameters.Size, "tableSizeFor", "(I)I", "", CmcdData.STREAMING_FORMAT_SS, "caseInsensitiveHashCode", "(Ljava/lang/String;)I", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int tableSizeFor(int size) {
            int i = size - 1;
            int i2 = (i >>> 1) | i;
            int i3 = i2 | (i2 >>> 2);
            int i4 = i3 | (i3 >>> 4);
            int i5 = i4 | (i4 >>> 8);
            int i6 = i5 | (i5 >>> 16);
            if (i6 < 4) {
                return 4;
            }
            return i6 + 1;
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int caseInsensitiveHashCode(String s) {
            int length = s.length();
            int lowerCase = 0;
            for (int i = 0; i < length; i++) {
                lowerCase = (lowerCase * 31) + Character.toLowerCase(s.charAt(i));
            }
            return lowerCase;
        }
    }
}
