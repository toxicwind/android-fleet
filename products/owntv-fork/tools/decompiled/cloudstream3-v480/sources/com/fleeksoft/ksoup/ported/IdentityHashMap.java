package com.fleeksoft.ksoup.ported;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableMap;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: IdentityHashMap.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003:\u0002()B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00028\u0001H\u0096\u0080\u0004¢\u0006\u0002\u0010\u000fJ\u0019\u0010\u0012\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000e\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0013J\n\u0010\u0014\u001a\u00020\rH\u0096\u0080\u0004J\n\u0010 \u001a\u00020!H\u0096\u0080\u0004J!\u0010\"\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001H\u0096\u0080\b¢\u0006\u0002\u0010#J \u0010$\u001a\u00020!2\u0014\u0010%\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010&H\u0096\u0080\u0004J\u0019\u0010'\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000e\u001a\u00028\u0000H\u0096\u0080\b¢\u0006\u0002\u0010\u0013R \u0010\u0006\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0004\u0012\u00028\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\b\u001a\u00020\t8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR'\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00170\u00168VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u00168VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u001b\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00010\u001d8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006*"}, d2 = {"Lcom/fleeksoft/ksoup/ported/IdentityHashMap;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "<init>", "()V", "delegate", "Lcom/fleeksoft/ksoup/ported/IdentityHashMap$IdentityWrapper;", ContentDisposition.Parameters.Size, "", "getSize", "()I", "containsKey", "", "key", Signatures.MATH_TO_BOOLEAN, "containsValue", ES6Iterator.VALUE_PROPERTY, "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "isEmpty", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "values", "", "getValues", "()Ljava/util/Collection;", "clear", "", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putAll", TypedValues.TransitionType.S_FROM, "", "remove", "IdentityWrapper", "IdentityEntry", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class IdentityHashMap<K, V> implements Map<K, V>, KMutableMap {
    private final Map<IdentityWrapper<K>, V> delegate = new LinkedHashMap();

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return getValues();
    }

    /* JADX INFO: compiled from: IdentityHashMap.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0002\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\t\u001a\u00020\nH\u0096\u0080\u0004J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0096\u0082\u0004R\u0013\u0010\u0003\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/ported/IdentityHashMap$IdentityWrapper;", ExifInterface.GPS_DIRECTION_TRUE, "", ES6Iterator.VALUE_PROPERTY, "<init>", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "hashCode", "", "equals", "", "other", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class IdentityWrapper<T> {
        private final T value;

        public IdentityWrapper(T t) {
            this.value = t;
        }

        public final T getValue() {
            return this.value;
        }

        public int hashCode() {
            T t = this.value;
            if (t != null) {
                return t.hashCode();
            }
            return 0;
        }

        public boolean equals(Object other) {
            return (other instanceof IdentityWrapper) && ((IdentityWrapper) other).value == this.value;
        }
    }

    /* JADX INFO: compiled from: IdentityHashMap.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010'\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0002\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B!\u0012\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u0005\u0012\u0004\u0012\u00028\u00030\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\r\u001a\u00028\u00032\u0006\u0010\u000e\u001a\u00028\u0003H\u0096\u0080\b¢\u0006\u0002\u0010\u000fR \u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u0005\u0012\u0004\u0012\u00028\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\b\u001a\u00028\u00028VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0015\u0010\u000b\u001a\u00028\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/fleeksoft/ksoup/ported/IdentityHashMap$IdentityEntry;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "original", "Lcom/fleeksoft/ksoup/ported/IdentityHashMap$IdentityWrapper;", "<init>", "(Ljava/util/Map$Entry;)V", "key", "getKey", "()Ljava/lang/Object;", ES6Iterator.VALUE_PROPERTY, "getValue", "setValue", "newValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class IdentityEntry<K, V> implements Map.Entry<K, V>, KMutableMap.Entry {
        private final Map.Entry<IdentityWrapper<K>, V> original;

        public IdentityEntry(Map.Entry<IdentityWrapper<K>, V> original) {
            Intrinsics.checkNotNullParameter(original, "original");
            this.original = original;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.original.getKey().getValue();
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.original.getValue();
        }

        @Override // java.util.Map.Entry
        public V setValue(V newValue) {
            return this.original.setValue(newValue);
        }
    }

    public int getSize() {
        return this.delegate.size();
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        return this.delegate.containsKey(new IdentityWrapper(key));
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        return this.delegate.containsValue(value);
    }

    @Override // java.util.Map
    public V get(Object key) {
        return this.delegate.get(new IdentityWrapper(key));
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    public Set<Map.Entry<K, V>> getEntries() {
        Set<Map.Entry<IdentityWrapper<K>, V>> setEntrySet = this.delegate.entrySet();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setEntrySet, 10));
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            arrayList.add(new IdentityEntry((Map.Entry) it.next()));
        }
        return CollectionsKt.toMutableSet(arrayList);
    }

    public Set<K> getKeys() {
        Set<IdentityWrapper<K>> setKeySet = this.delegate.keySet();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setKeySet, 10));
        Iterator<T> it = setKeySet.iterator();
        while (it.hasNext()) {
            arrayList.add(((IdentityWrapper) it.next()).getValue());
        }
        return CollectionsKt.toMutableSet(arrayList);
    }

    public Collection<V> getValues() {
        return this.delegate.values();
    }

    @Override // java.util.Map
    public void clear() {
        this.delegate.clear();
    }

    @Override // java.util.Map
    public V put(K key, V value) {
        return this.delegate.put(new IdentityWrapper<>(key), value);
    }

    @Override // java.util.Map
    public V remove(Object key) {
        return this.delegate.remove(new IdentityWrapper(key));
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> from) {
        Intrinsics.checkNotNullParameter(from, "from");
        for (Map.Entry<? extends K, ? extends V> entry : from.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }
}
