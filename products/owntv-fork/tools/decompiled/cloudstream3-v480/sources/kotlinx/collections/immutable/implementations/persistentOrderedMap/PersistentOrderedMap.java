package kotlinx.collections.immutable.implementations.persistentOrderedMap;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import io.ktor.http.ContentDisposition;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractMap;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ImmutableCollection;
import kotlinx.collections.immutable.ImmutableSet;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder;
import kotlinx.collections.immutable.internal.EndOfChain;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: PersistentOrderedMap.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 7*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004:\u00017B5\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010!\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f0\u0017H\u0002J\u001a\u0010 \u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f0\"H\u0001J\u0015\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010&J\u0018\u0010'\u001a\u0004\u0018\u00018\u00012\u0006\u0010%\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010(J)\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010%\u001a\u00028\u00002\u0006\u0010*\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010+J!\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010%\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010-J)\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010%\u001a\u00028\u00002\u0006\u0010*\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010+J*\u0010.\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042\u0014\u0010/\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0016J\u0014\u00101\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0016J\u0014\u00102\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000103H\u0016J\u0013\u00104\u001a\u00020$2\b\u00105\u001a\u0004\u0018\u00010\u0006H\u0096\u0002J\b\u00106\u001a\u00020\u0013H\u0016R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR&\u0010\b\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\n0\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR&\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f0\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0019¨\u00068"}, d2 = {"Lkotlinx/collections/immutable/implementations/persistentOrderedMap/PersistentOrderedMap;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/collections/AbstractMap;", "Lkotlinx/collections/immutable/PersistentMap;", "firstKey", "", "lastKey", "hashMap", "Lkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMap;", "Lkotlinx/collections/immutable/implementations/persistentOrderedMap/LinkedValue;", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMap;)V", "getFirstKey$kotlinx_collections_immutable", "()Ljava/lang/Object;", "getLastKey$kotlinx_collections_immutable", "getHashMap$kotlinx_collections_immutable", "()Lkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMap;", ContentDisposition.Parameters.Size, "", "getSize", "()I", "keys", "Lkotlinx/collections/immutable/ImmutableSet;", "getKeys", "()Lkotlinx/collections/immutable/ImmutableSet;", "values", "Lkotlinx/collections/immutable/ImmutableCollection;", "getValues", "()Lkotlinx/collections/immutable/ImmutableCollection;", "entries", "", "getEntries", "createEntries", "", "containsKey", "", "key", Signatures.MATH_TO_BOOLEAN, "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "put", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlinx/collections/immutable/implementations/persistentOrderedMap/PersistentOrderedMap;", "remove", "(Ljava/lang/Object;)Lkotlinx/collections/immutable/implementations/persistentOrderedMap/PersistentOrderedMap;", "putAll", CmcdData.OBJECT_TYPE_MANIFEST, "", "clear", "builder", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "equals", "other", "hashCode", "Companion", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PersistentOrderedMap<K, V> extends AbstractMap<K, V> implements PersistentMap<K, V> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final PersistentOrderedMap EMPTY = new PersistentOrderedMap(EndOfChain.INSTANCE, EndOfChain.INSTANCE, PersistentHashMap.INSTANCE.emptyOf$kotlinx_collections_immutable());
    private final Object firstKey;
    private final PersistentHashMap<K, LinkedValue<V>> hashMap;
    private final Object lastKey;

    @Override // kotlin.collections.AbstractMap, java.util.Map
    public final /* bridge */ ImmutableSet<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // kotlin.collections.AbstractMap, java.util.Map
    public final /* bridge */ ImmutableSet<K> keySet() {
        return getKeys();
    }

    @Override // kotlin.collections.AbstractMap, java.util.Map
    public final /* bridge */ ImmutableCollection<V> values() {
        return getValues();
    }

    /* JADX INFO: renamed from: getFirstKey$kotlinx_collections_immutable, reason: from getter */
    public final Object getFirstKey() {
        return this.firstKey;
    }

    /* JADX INFO: renamed from: getLastKey$kotlinx_collections_immutable, reason: from getter */
    public final Object getLastKey() {
        return this.lastKey;
    }

    public final PersistentHashMap<K, LinkedValue<V>> getHashMap$kotlinx_collections_immutable() {
        return this.hashMap;
    }

    public PersistentOrderedMap(Object obj, Object obj2, PersistentHashMap<K, LinkedValue<V>> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "hashMap");
        this.firstKey = obj;
        this.lastKey = obj2;
        this.hashMap = hashMap;
    }

    @Override // kotlin.collections.AbstractMap
    public int getSize() {
        return this.hashMap.size();
    }

    @Override // kotlin.collections.AbstractMap, androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableMap
    public ImmutableSet<K> getKeys() {
        return new PersistentOrderedMapKeys(this);
    }

    @Override // kotlin.collections.AbstractMap, androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableMap
    public ImmutableCollection<V> getValues() {
        return new PersistentOrderedMapValues(this);
    }

    @Override // kotlin.collections.AbstractMap, androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableMap
    public ImmutableSet<Map.Entry<K, V>> getEntries() {
        return createEntries();
    }

    private final ImmutableSet<Map.Entry<K, V>> createEntries() {
        return new PersistentOrderedMapEntries(this);
    }

    @Override // kotlin.collections.AbstractMap, androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableMap
    public final Set<Map.Entry<K, V>> getEntries() {
        return createEntries();
    }

    @Override // kotlin.collections.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        return this.hashMap.containsKey(key);
    }

    @Override // kotlin.collections.AbstractMap, java.util.Map
    public V get(Object key) {
        LinkedValue<V> linkedValue = this.hashMap.get(key);
        if (linkedValue != null) {
            return linkedValue.getValue();
        }
        return null;
    }

    @Override // kotlin.collections.AbstractMap, java.util.Map, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap
    public PersistentOrderedMap<K, V> put(K key, V value) {
        boolean zIsEmpty = isEmpty();
        PersistentHashMap<K, LinkedValue<V>> persistentHashMap = this.hashMap;
        if (zIsEmpty) {
            return new PersistentOrderedMap<>(key, key, persistentHashMap.put((Object) key, new LinkedValue<>(value)));
        }
        LinkedValue<V> linkedValue = persistentHashMap.get(key);
        if (linkedValue != null) {
            if (linkedValue.getValue() == value) {
                return this;
            }
            return new PersistentOrderedMap<>(this.firstKey, this.lastKey, this.hashMap.put((Object) key, linkedValue.withValue(value)));
        }
        Object obj = this.lastKey;
        LinkedValue<V> linkedValue2 = this.hashMap.get(obj);
        Intrinsics.checkNotNull(linkedValue2);
        return new PersistentOrderedMap<>(this.firstKey, key, this.hashMap.put((Object) obj, linkedValue2.withNext(key)).put((Object) key, new LinkedValue<>(value, obj)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r5v1, types: [kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2, types: [kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap] */
    /* JADX WARN: Type inference failed for: r5v3, types: [kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    @Override // kotlin.collections.AbstractMap, java.util.Map, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap
    public PersistentOrderedMap<K, V> remove(K key) {
        ?? r5;
        LinkedValue<V> linkedValue = this.hashMap.get(key);
        if (linkedValue == null) {
            return this;
        }
        PersistentHashMap<K, LinkedValue<V>> persistentHashMapRemove = this.hashMap.remove((Object) key);
        if (linkedValue.getHasPrevious()) {
            r5 = persistentHashMapRemove;
            Object obj = persistentHashMapRemove.get(linkedValue.getPrevious());
            Intrinsics.checkNotNull(obj);
            r5 = (PersistentHashMap<K, LinkedValue<V>>) persistentHashMapRemove.put(linkedValue.getPrevious(), ((LinkedValue) obj).withNext(linkedValue.getNext()));
        }
        r5 = persistentHashMapRemove;
        ?? Put = r5;
        if (linkedValue.getHasNext()) {
            Object obj2 = ((Map) r5).get(linkedValue.getNext());
            Intrinsics.checkNotNull(obj2);
            Put = r5.put(linkedValue.getNext(), ((LinkedValue) obj2).withPrevious(linkedValue.getPrevious()));
        }
        return new PersistentOrderedMap<>(!linkedValue.getHasPrevious() ? linkedValue.getNext() : this.firstKey, !linkedValue.getHasNext() ? linkedValue.getPrevious() : this.lastKey, Put);
    }

    @Override // java.util.Map, kotlinx.collections.immutable.PersistentMap
    public PersistentOrderedMap<K, V> remove(K key, V value) {
        LinkedValue<V> linkedValue = this.hashMap.get(key);
        return (linkedValue != null && Intrinsics.areEqual(linkedValue.getValue(), value)) ? remove((Object) key) : this;
    }

    @Override // java.util.Map, kotlinx.collections.immutable.PersistentMap
    public PersistentMap<K, V> putAll(Map<? extends K, ? extends V> m) {
        Intrinsics.checkNotNullParameter(m, "m");
        if (m.isEmpty()) {
            return this;
        }
        PersistentOrderedMap<K, V> persistentOrderedMap = this;
        Intrinsics.checkNotNull(persistentOrderedMap, "null cannot be cast to non-null type kotlinx.collections.immutable.PersistentMap<K of kotlinx.collections.immutable.ExtensionsKt.mutate, V of kotlinx.collections.immutable.ExtensionsKt.mutate>");
        PersistentMap.Builder<K, V> builder = persistentOrderedMap.builder();
        builder.putAll(m);
        return builder.build();
    }

    @Override // java.util.Map, kotlinx.collections.immutable.PersistentMap
    public PersistentMap<K, V> clear() {
        return INSTANCE.emptyOf$kotlinx_collections_immutable();
    }

    @Override // kotlinx.collections.immutable.PersistentMap
    public PersistentMap.Builder<K, V> builder() {
        return new PersistentOrderedMapBuilder(this);
    }

    @Override // kotlin.collections.AbstractMap, java.util.Map
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Map)) {
            return false;
        }
        Map map = (Map) other;
        if (size() != map.size()) {
            return false;
        }
        if (map instanceof PersistentOrderedMap) {
            return this.hashMap.getNode$kotlinx_collections_immutable().equalsWith$kotlinx_collections_immutable(((PersistentOrderedMap) other).hashMap.getNode$kotlinx_collections_immutable(), new Function2<LinkedValue<V>, ?, Boolean>() { // from class: kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMap.equals.1
                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(LinkedValue<V> a, LinkedValue<? extends Object> b) {
                    Intrinsics.checkNotNullParameter(a, "a");
                    Intrinsics.checkNotNullParameter(b, "b");
                    return Boolean.valueOf(Intrinsics.areEqual(a.getValue(), b.getValue()));
                }
            });
        }
        if (map instanceof PersistentOrderedMapBuilder) {
            return this.hashMap.getNode$kotlinx_collections_immutable().equalsWith$kotlinx_collections_immutable(((PersistentOrderedMapBuilder) other).getHashMapBuilder$kotlinx_collections_immutable().getNode$kotlinx_collections_immutable(), new Function2<LinkedValue<V>, ?, Boolean>() { // from class: kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMap.equals.2
                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(LinkedValue<V> a, LinkedValue<? extends Object> b) {
                    Intrinsics.checkNotNullParameter(a, "a");
                    Intrinsics.checkNotNullParameter(b, "b");
                    return Boolean.valueOf(Intrinsics.areEqual(a.getValue(), b.getValue()));
                }
            });
        }
        if (map instanceof PersistentHashMap) {
            return this.hashMap.getNode$kotlinx_collections_immutable().equalsWith$kotlinx_collections_immutable(((PersistentHashMap) other).getNode$kotlinx_collections_immutable(), new Function2<LinkedValue<V>, ?, Boolean>() { // from class: kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMap.equals.3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Boolean invoke(Object obj, Object obj2) {
                    return invoke((LinkedValue) obj, (Object) obj2);
                }

                public final Boolean invoke(LinkedValue<V> a, Object obj) {
                    Intrinsics.checkNotNullParameter(a, "a");
                    return Boolean.valueOf(Intrinsics.areEqual(a.getValue(), obj));
                }
            });
        }
        if (map instanceof PersistentHashMapBuilder) {
            return this.hashMap.getNode$kotlinx_collections_immutable().equalsWith$kotlinx_collections_immutable(((PersistentHashMapBuilder) other).getNode$kotlinx_collections_immutable(), new Function2<LinkedValue<V>, ?, Boolean>() { // from class: kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMap.equals.4
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Boolean invoke(Object obj, Object obj2) {
                    return invoke((LinkedValue) obj, (Object) obj2);
                }

                public final Boolean invoke(LinkedValue<V> a, Object obj) {
                    Intrinsics.checkNotNullParameter(a, "a");
                    return Boolean.valueOf(Intrinsics.areEqual(a.getValue(), obj));
                }
            });
        }
        return super.equals(other);
    }

    @Override // kotlin.collections.AbstractMap, java.util.Map
    public int hashCode() {
        return super.hashCode();
    }

    /* JADX INFO: compiled from: PersistentOrderedMap.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t0\u0005\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\tH\u0000¢\u0006\u0002\b\nR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlinx/collections/immutable/implementations/persistentOrderedMap/PersistentOrderedMap$Companion;", "", "<init>", "()V", "EMPTY", "Lkotlinx/collections/immutable/implementations/persistentOrderedMap/PersistentOrderedMap;", "", "emptyOf", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "emptyOf$kotlinx_collections_immutable", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <K, V> PersistentOrderedMap<K, V> emptyOf$kotlinx_collections_immutable() {
            PersistentOrderedMap<K, V> persistentOrderedMap = PersistentOrderedMap.EMPTY;
            Intrinsics.checkNotNull(persistentOrderedMap, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMap<K of kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMap.Companion.emptyOf, V of kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMap.Companion.emptyOf>");
            return persistentOrderedMap;
        }
    }
}
