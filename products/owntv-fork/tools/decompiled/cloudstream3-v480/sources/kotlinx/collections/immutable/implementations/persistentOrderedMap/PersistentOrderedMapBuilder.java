package kotlinx.collections.immutable.implementations.persistentOrderedMap;

import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.http.ContentDisposition;
import j$.util.Map;
import java.util.Collection;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableMap;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder;
import kotlinx.collections.immutable.internal.CommonFunctionsKt;
import kotlinx.collections.immutable.internal.EndOfChain;
import kotlinx.collections.immutable.internal.MapImplementation;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: PersistentOrderedMapBuilder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u001b\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001aH\u0016J\u0015\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010)J\u0018\u0010*\u001a\u0004\u0018\u00018\u00012\u0006\u0010(\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010+J\u001f\u0010,\u001a\u0004\u0018\u00018\u00012\u0006\u0010(\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010-J\u0017\u0010.\u001a\u0004\u0018\u00018\u00012\u0006\u0010(\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010+J\u001b\u0010.\u001a\u00020'2\u0006\u0010(\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0001¢\u0006\u0002\u0010/J\b\u00100\u001a\u000201H\u0016J\u0013\u00102\u001a\u00020'2\b\u00103\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J\b\u00104\u001a\u00020\u0016H\u0016R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00120\u0011X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R&\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001d0\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u001fR\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00010#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u00065"}, d2 = {"Lkotlinx/collections/immutable/implementations/persistentOrderedMap/PersistentOrderedMapBuilder;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/collections/AbstractMutableMap;", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "map", "Lkotlinx/collections/immutable/implementations/persistentOrderedMap/PersistentOrderedMap;", "<init>", "(Lkotlinx/collections/immutable/implementations/persistentOrderedMap/PersistentOrderedMap;)V", "builtMap", ES6Iterator.VALUE_PROPERTY, "", "firstKey", "getFirstKey$kotlinx_collections_immutable", "()Ljava/lang/Object;", "lastKey", "hashMapBuilder", "Lkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;", "Lkotlinx/collections/immutable/implementations/persistentOrderedMap/LinkedValue;", "getHashMapBuilder$kotlinx_collections_immutable", "()Lkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;", ContentDisposition.Parameters.Size, "", "getSize", "()I", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lkotlinx/collections/immutable/PersistentMap;", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "values", "", "getValues", "()Ljava/util/Collection;", "containsKey", "", "key", Signatures.MATH_TO_BOOLEAN, "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "clear", "", "equals", "other", "hashCode", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PersistentOrderedMapBuilder<K, V> extends AbstractMutableMap<K, V> implements PersistentMap.Builder<K, V>, Map {
    private PersistentOrderedMap<K, V> builtMap;
    private Object firstKey;
    private final PersistentHashMapBuilder<K, LinkedValue<V>> hashMapBuilder;
    private Object lastKey;

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object compute(Object obj, BiFunction biFunction) {
        return Map.CC.$default$compute(this, obj, biFunction);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfAbsent(Object obj, Function function) {
        return Map.CC.$default$computeIfAbsent(this, obj, function);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfPresent(Object obj, BiFunction biFunction) {
        return Map.CC.$default$computeIfPresent(this, obj, biFunction);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ void forEach(BiConsumer biConsumer) {
        Map.CC.$default$forEach(this, biConsumer);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object getOrDefault(Object obj, Object obj2) {
        return Map.CC.$default$getOrDefault(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map.CC.$default$merge(this, obj, obj2, biFunction);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object putIfAbsent(Object obj, Object obj2) {
        return Map.CC.$default$putIfAbsent(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object replace(Object obj, Object obj2) {
        return Map.CC.$default$replace(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ boolean replace(Object obj, Object obj2, Object obj3) {
        return Map.CC.$default$replace(this, obj, obj2, obj3);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ void replaceAll(BiFunction biFunction) {
        Map.CC.$default$replaceAll(this, biFunction);
    }

    public PersistentOrderedMapBuilder(PersistentOrderedMap<K, V> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        this.builtMap = map;
        this.firstKey = map.getFirstKey();
        this.lastKey = map.getLastKey();
        this.hashMapBuilder = map.getHashMap$kotlinx_collections_immutable().builder();
    }

    /* JADX INFO: renamed from: getFirstKey$kotlinx_collections_immutable, reason: from getter */
    public final Object getFirstKey() {
        return this.firstKey;
    }

    public final PersistentHashMapBuilder<K, LinkedValue<V>> getHashMapBuilder$kotlinx_collections_immutable() {
        return this.hashMapBuilder;
    }

    @Override // kotlin.collections.AbstractMutableMap
    public int getSize() {
        return this.hashMapBuilder.size();
    }

    @Override // kotlinx.collections.immutable.PersistentMap.Builder
    public PersistentMap<K, V> build() {
        PersistentOrderedMap<K, V> persistentOrderedMap = this.builtMap;
        if (persistentOrderedMap != null) {
            CommonFunctionsKt.m10467assert(this.hashMapBuilder.getBuiltMap$kotlinx_collections_immutable() != null);
            CommonFunctionsKt.m10467assert(this.firstKey == persistentOrderedMap.getFirstKey());
            CommonFunctionsKt.m10467assert(this.lastKey == persistentOrderedMap.getLastKey());
            return persistentOrderedMap;
        }
        CommonFunctionsKt.m10467assert(this.hashMapBuilder.getBuiltMap$kotlinx_collections_immutable() == null);
        PersistentOrderedMap<K, V> persistentOrderedMap2 = new PersistentOrderedMap<>(this.firstKey, this.lastKey, this.hashMapBuilder.build());
        this.builtMap = persistentOrderedMap2;
        return persistentOrderedMap2;
    }

    @Override // kotlin.collections.AbstractMutableMap
    public Set<java.util.Map.Entry<K, V>> getEntries() {
        return new PersistentOrderedMapBuilderEntries(this);
    }

    @Override // kotlin.collections.AbstractMutableMap
    public Set<K> getKeys() {
        return new PersistentOrderedMapBuilderKeys(this);
    }

    @Override // kotlin.collections.AbstractMutableMap
    public Collection<V> getValues() {
        return new PersistentOrderedMapBuilderValues(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        return this.hashMapBuilder.containsKey(key);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object key) {
        LinkedValue<V> linkedValue = this.hashMapBuilder.get(key);
        if (linkedValue != null) {
            return linkedValue.getValue();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractMutableMap, java.util.AbstractMap, java.util.Map
    public V put(K key, V value) {
        LinkedValue<V> linkedValue = this.hashMapBuilder.get(key);
        if (linkedValue != null) {
            if (linkedValue.getValue() == value) {
                return value;
            }
            this.builtMap = null;
            this.hashMapBuilder.put(key, linkedValue.withValue(value));
            return linkedValue.getValue();
        }
        this.builtMap = null;
        if (isEmpty()) {
            this.firstKey = key;
            this.lastKey = key;
            this.hashMapBuilder.put(key, new LinkedValue<>(value));
        } else {
            Object obj = this.lastKey;
            LinkedValue<V> linkedValue2 = this.hashMapBuilder.get(obj);
            Intrinsics.checkNotNull(linkedValue2);
            LinkedValue<V> linkedValue3 = linkedValue2;
            CommonFunctionsKt.m10467assert(!linkedValue3.getHasNext());
            this.hashMapBuilder.put(obj, linkedValue3.withNext(key));
            this.hashMapBuilder.put(key, new LinkedValue<>(value, obj));
            this.lastKey = key;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object key) {
        LinkedValue<V> linkedValueRemove = this.hashMapBuilder.remove(key);
        if (linkedValueRemove == null) {
            return null;
        }
        this.builtMap = null;
        if (linkedValueRemove.getHasPrevious()) {
            LinkedValue<V> linkedValue = this.hashMapBuilder.get(linkedValueRemove.getPrevious());
            Intrinsics.checkNotNull(linkedValue);
            this.hashMapBuilder.put(linkedValueRemove.getPrevious(), linkedValue.withNext(linkedValueRemove.getNext()));
        } else {
            this.firstKey = linkedValueRemove.getNext();
        }
        if (linkedValueRemove.getHasNext()) {
            LinkedValue<V> linkedValue2 = this.hashMapBuilder.get(linkedValueRemove.getNext());
            Intrinsics.checkNotNull(linkedValue2);
            this.hashMapBuilder.put(linkedValueRemove.getNext(), linkedValue2.withPrevious(linkedValueRemove.getPrevious()));
        } else {
            this.lastKey = linkedValueRemove.getPrevious();
        }
        return linkedValueRemove.getValue();
    }

    @Override // java.util.Map, j$.util.Map
    public final boolean remove(Object key, Object value) {
        LinkedValue<V> linkedValue = this.hashMapBuilder.get(key);
        if (linkedValue == null || !Intrinsics.areEqual(linkedValue.getValue(), value)) {
            return false;
        }
        remove(key);
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        if (!this.hashMapBuilder.isEmpty()) {
            this.builtMap = null;
        }
        this.hashMapBuilder.clear();
        this.firstKey = EndOfChain.INSTANCE;
        this.lastKey = EndOfChain.INSTANCE;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof java.util.Map)) {
            return false;
        }
        java.util.Map<?, ?> map = (java.util.Map) other;
        if (size() != map.size()) {
            return false;
        }
        if (map instanceof PersistentOrderedMap) {
            return this.hashMapBuilder.getNode$kotlinx_collections_immutable().equalsWith$kotlinx_collections_immutable(((PersistentOrderedMap) other).getHashMap$kotlinx_collections_immutable().getNode$kotlinx_collections_immutable(), new Function2<LinkedValue<V>, ?, Boolean>() { // from class: kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMapBuilder.equals.1
                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(LinkedValue<V> a, LinkedValue<? extends Object> b) {
                    Intrinsics.checkNotNullParameter(a, "a");
                    Intrinsics.checkNotNullParameter(b, "b");
                    return Boolean.valueOf(Intrinsics.areEqual(a.getValue(), b.getValue()));
                }
            });
        }
        if (map instanceof PersistentOrderedMapBuilder) {
            return this.hashMapBuilder.getNode$kotlinx_collections_immutable().equalsWith$kotlinx_collections_immutable(((PersistentOrderedMapBuilder) other).hashMapBuilder.getNode$kotlinx_collections_immutable(), new Function2<LinkedValue<V>, ?, Boolean>() { // from class: kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMapBuilder.equals.2
                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(LinkedValue<V> a, LinkedValue<? extends Object> b) {
                    Intrinsics.checkNotNullParameter(a, "a");
                    Intrinsics.checkNotNullParameter(b, "b");
                    return Boolean.valueOf(Intrinsics.areEqual(a.getValue(), b.getValue()));
                }
            });
        }
        if (map instanceof PersistentHashMap) {
            return this.hashMapBuilder.getNode$kotlinx_collections_immutable().equalsWith$kotlinx_collections_immutable(((PersistentHashMap) other).getNode$kotlinx_collections_immutable(), new Function2<LinkedValue<V>, ?, Boolean>() { // from class: kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMapBuilder.equals.3
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
            return this.hashMapBuilder.getNode$kotlinx_collections_immutable().equalsWith$kotlinx_collections_immutable(((PersistentHashMapBuilder) other).getNode$kotlinx_collections_immutable(), new Function2<LinkedValue<V>, ?, Boolean>() { // from class: kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMapBuilder.equals.4
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
        return MapImplementation.INSTANCE.equals$kotlinx_collections_immutable(this, map);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        return MapImplementation.INSTANCE.hashCode$kotlinx_collections_immutable(this);
    }
}
