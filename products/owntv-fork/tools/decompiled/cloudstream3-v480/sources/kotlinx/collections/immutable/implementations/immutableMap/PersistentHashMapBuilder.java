package kotlinx.collections.immutable.implementations.immutableMap;

import androidx.constraintlayout.core.motion.utils.TypedValues;
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
import kotlinx.collections.immutable.implementations.persistentOrderedMap.LinkedValue;
import kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMap;
import kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMapBuilder;
import kotlinx.collections.immutable.internal.DeltaCounter;
import kotlinx.collections.immutable.internal.MapImplementation;
import kotlinx.collections.immutable.internal.MutabilityOwnership;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: PersistentHashMapBuilder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u001b\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010&\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006H\u0016J\u0015\u00102\u001a\u0002032\u0006\u00104\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00105J\u0018\u00106\u001a\u0004\u0018\u00018\u00012\u0006\u00104\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u00107J\u001f\u00108\u001a\u0004\u0018\u00018\u00012\u0006\u00104\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00109J\u001e\u0010:\u001a\u00020;2\u0014\u0010<\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010=H\u0016J\u0017\u0010>\u001a\u0004\u0018\u00018\u00012\u0006\u00104\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00107J\u001b\u0010>\u001a\u0002032\u0006\u00104\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0001¢\u0006\u0002\u0010?J\b\u0010@\u001a\u00020;H\u0016J\u0013\u0010A\u001a\u0002032\b\u0010B\u001a\u0004\u0018\u00010CH\u0096\u0002J\b\u0010D\u001a\u00020\u001eH\u0016R:\u0010\n\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00062\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\r@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R<\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00112\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0011@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u0004\u0018\u00018\u0001X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001d\u001a\u00020\u001eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010#\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020\u001e@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"R&\u0010'\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010)0(8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000(8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010+R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00010/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u00101¨\u0006E"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlinx/collections/immutable/PersistentMap$Builder;", "Lkotlin/collections/AbstractMutableMap;", "map", "Lkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMap;", "<init>", "(Lkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMap;)V", ES6Iterator.VALUE_PROPERTY, "builtMap", "getBuiltMap$kotlinx_collections_immutable", "()Lkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMap;", "Lkotlinx/collections/immutable/internal/MutabilityOwnership;", "ownership", "getOwnership$kotlinx_collections_immutable", "()Lkotlinx/collections/immutable/internal/MutabilityOwnership;", "Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "node", "getNode$kotlinx_collections_immutable", "()Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "setNode$kotlinx_collections_immutable", "(Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;)V", "operationResult", "getOperationResult$kotlinx_collections_immutable", "()Ljava/lang/Object;", "setOperationResult$kotlinx_collections_immutable", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "modCount", "", "getModCount$kotlinx_collections_immutable", "()I", "setModCount$kotlinx_collections_immutable", "(I)V", ContentDisposition.Parameters.Size, "getSize", "setSize", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "values", "", "getValues", "()Ljava/util/Collection;", "containsKey", "", "key", Signatures.MATH_TO_BOOLEAN, "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putAll", "", TypedValues.TransitionType.S_FROM, "", "remove", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "clear", "equals", "other", "", "hashCode", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PersistentHashMapBuilder<K, V> extends AbstractMutableMap<K, V> implements PersistentMap.Builder<K, V>, Map {
    private PersistentHashMap<K, V> builtMap;
    private int modCount;
    private TrieNode<K, V> node;
    private V operationResult;
    private MutabilityOwnership ownership;
    private int size;

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

    public PersistentHashMapBuilder(PersistentHashMap<K, V> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        this.builtMap = map;
        this.ownership = new MutabilityOwnership();
        this.node = map.getNode$kotlinx_collections_immutable();
        this.size = map.size();
    }

    public final PersistentHashMap<K, V> getBuiltMap$kotlinx_collections_immutable() {
        return this.builtMap;
    }

    /* JADX INFO: renamed from: getOwnership$kotlinx_collections_immutable, reason: from getter */
    public final MutabilityOwnership getOwnership() {
        return this.ownership;
    }

    public final TrieNode<K, V> getNode$kotlinx_collections_immutable() {
        return this.node;
    }

    public final void setNode$kotlinx_collections_immutable(TrieNode<K, V> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value != this.node) {
            this.node = value;
            this.builtMap = null;
        }
    }

    public final V getOperationResult$kotlinx_collections_immutable() {
        return this.operationResult;
    }

    public final void setOperationResult$kotlinx_collections_immutable(V v) {
        this.operationResult = v;
    }

    /* JADX INFO: renamed from: getModCount$kotlinx_collections_immutable, reason: from getter */
    public final int getModCount() {
        return this.modCount;
    }

    public final void setModCount$kotlinx_collections_immutable(int i) {
        this.modCount = i;
    }

    @Override // kotlin.collections.AbstractMutableMap
    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
        this.modCount++;
    }

    @Override // kotlinx.collections.immutable.PersistentMap.Builder
    public PersistentHashMap<K, V> build() {
        PersistentHashMap<K, V> persistentHashMap = this.builtMap;
        if (persistentHashMap != null) {
            return persistentHashMap;
        }
        PersistentHashMap<K, V> persistentHashMap2 = new PersistentHashMap<>(this.node, size());
        this.builtMap = persistentHashMap2;
        this.ownership = new MutabilityOwnership();
        return persistentHashMap2;
    }

    @Override // kotlin.collections.AbstractMutableMap
    public Set<java.util.Map.Entry<K, V>> getEntries() {
        return new PersistentHashMapBuilderEntries(this);
    }

    @Override // kotlin.collections.AbstractMutableMap
    public Set<K> getKeys() {
        return new PersistentHashMapBuilderKeys(this);
    }

    @Override // kotlin.collections.AbstractMutableMap
    public Collection<V> getValues() {
        return new PersistentHashMapBuilderValues(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        return this.node.containsKey(key != null ? key.hashCode() : 0, key, 0);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object key) {
        return this.node.get(key != null ? key.hashCode() : 0, key, 0);
    }

    @Override // kotlin.collections.AbstractMutableMap, java.util.AbstractMap, java.util.Map
    public V put(K key, V value) {
        this.operationResult = null;
        setNode$kotlinx_collections_immutable(this.node.mutablePut(key != null ? key.hashCode() : 0, key, value, 0, this));
        return this.operationResult;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(java.util.Map<? extends K, ? extends V> from) {
        Intrinsics.checkNotNullParameter(from, "from");
        if (from.isEmpty()) {
            return;
        }
        PersistentHashMap<K, V> persistentHashMapBuild = from instanceof PersistentHashMap ? (PersistentHashMap) from : null;
        if (persistentHashMapBuild == null) {
            PersistentHashMapBuilder persistentHashMapBuilder = from instanceof PersistentHashMapBuilder ? (PersistentHashMapBuilder) from : null;
            persistentHashMapBuild = persistentHashMapBuilder != null ? persistentHashMapBuilder.build() : null;
        }
        if (persistentHashMapBuild != null) {
            DeltaCounter deltaCounter = new DeltaCounter(0, 1, null);
            int size = size();
            TrieNode<K, V> trieNode = this.node;
            TrieNode<K, V> node$kotlinx_collections_immutable = persistentHashMapBuild.getNode$kotlinx_collections_immutable();
            Intrinsics.checkNotNull(node$kotlinx_collections_immutable, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, V of kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder>");
            setNode$kotlinx_collections_immutable(trieNode.mutablePutAll(node$kotlinx_collections_immutable, 0, deltaCounter, this));
            int size2 = (persistentHashMapBuild.size() + size) - deltaCounter.getCount();
            if (size != size2) {
                setSize(size2);
                return;
            }
            return;
        }
        super.putAll(from);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object key) {
        this.operationResult = null;
        TrieNode trieNodeMutableRemove = this.node.mutableRemove(key != null ? key.hashCode() : 0, key, 0, this);
        if (trieNodeMutableRemove == null) {
            trieNodeMutableRemove = TrieNode.INSTANCE.getEMPTY$kotlinx_collections_immutable();
            Intrinsics.checkNotNull(trieNodeMutableRemove, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, V of kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder>");
        }
        setNode$kotlinx_collections_immutable(trieNodeMutableRemove);
        return this.operationResult;
    }

    @Override // java.util.Map, j$.util.Map
    public final boolean remove(Object key, Object value) {
        int size = size();
        TrieNode trieNodeMutableRemove = this.node.mutableRemove(key != null ? key.hashCode() : 0, key, value, 0, this);
        if (trieNodeMutableRemove == null) {
            trieNodeMutableRemove = TrieNode.INSTANCE.getEMPTY$kotlinx_collections_immutable();
            Intrinsics.checkNotNull(trieNodeMutableRemove, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, V of kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder>");
        }
        setNode$kotlinx_collections_immutable(trieNodeMutableRemove);
        return size != size();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        TrieNode eMPTY$kotlinx_collections_immutable = TrieNode.INSTANCE.getEMPTY$kotlinx_collections_immutable();
        Intrinsics.checkNotNull(eMPTY$kotlinx_collections_immutable, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, V of kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder>");
        setNode$kotlinx_collections_immutable(eMPTY$kotlinx_collections_immutable);
        setSize(0);
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
        if (map instanceof PersistentHashMap) {
            return this.node.equalsWith$kotlinx_collections_immutable(((PersistentHashMap) other).getNode$kotlinx_collections_immutable(), new Function2<V, ?, Boolean>() { // from class: kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder.equals.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(V v, Object obj) {
                    return Boolean.valueOf(Intrinsics.areEqual(v, obj));
                }
            });
        }
        if (map instanceof PersistentHashMapBuilder) {
            return this.node.equalsWith$kotlinx_collections_immutable(((PersistentHashMapBuilder) other).node, new Function2<V, ?, Boolean>() { // from class: kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder.equals.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(V v, Object obj) {
                    return Boolean.valueOf(Intrinsics.areEqual(v, obj));
                }
            });
        }
        if (map instanceof PersistentOrderedMap) {
            return this.node.equalsWith$kotlinx_collections_immutable(((PersistentOrderedMap) other).getHashMap$kotlinx_collections_immutable().getNode$kotlinx_collections_immutable(), new Function2<V, ?, Boolean>() { // from class: kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder.equals.3
                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(V v, LinkedValue<? extends Object> b) {
                    Intrinsics.checkNotNullParameter(b, "b");
                    return Boolean.valueOf(Intrinsics.areEqual(v, b.getValue()));
                }
            });
        }
        if (map instanceof PersistentOrderedMapBuilder) {
            return this.node.equalsWith$kotlinx_collections_immutable(((PersistentOrderedMapBuilder) other).getHashMapBuilder$kotlinx_collections_immutable().node, new Function2<V, ?, Boolean>() { // from class: kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder.equals.4
                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(V v, LinkedValue<? extends Object> b) {
                    Intrinsics.checkNotNullParameter(b, "b");
                    return Boolean.valueOf(Intrinsics.areEqual(v, b.getValue()));
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
