package kotlinx.collections.immutable.implementations.immutableSet;

import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentSet;
import kotlinx.collections.immutable.internal.DeltaCounter;
import kotlinx.collections.immutable.internal.MutabilityOwnership;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: PersistentHashSetBuilder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010)\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016J\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010 J\u0015\u0010!\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010 J\u0016\u0010\"\u001a\u00020\u001e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$H\u0016J\u0016\u0010%\u001a\u00020\u001e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$H\u0016J\u0016\u0010&\u001a\u00020\u001e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$H\u0016J\u0016\u0010'\u001a\u00020\u001e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$H\u0016J\u0015\u0010(\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010 J\b\u0010)\u001a\u00020*H\u0016J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000,H\u0096\u0002R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR0\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e@BX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0017\"\u0004\b\u001a\u0010\u001b¨\u0006-"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableSet/PersistentHashSetBuilder;", ExifInterface.LONGITUDE_EAST, "Lkotlin/collections/AbstractMutableSet;", "Lkotlinx/collections/immutable/PersistentSet$Builder;", "set", "Lkotlinx/collections/immutable/implementations/immutableSet/PersistentHashSet;", "<init>", "(Lkotlinx/collections/immutable/implementations/immutableSet/PersistentHashSet;)V", "builtSet", ES6Iterator.VALUE_PROPERTY, "Lkotlinx/collections/immutable/internal/MutabilityOwnership;", "ownership", "getOwnership$kotlinx_collections_immutable", "()Lkotlinx/collections/immutable/internal/MutabilityOwnership;", "Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "node", "getNode$kotlinx_collections_immutable", "()Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "setNode", "(Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;)V", "", "modCount", "getModCount$kotlinx_collections_immutable", "()I", ContentDisposition.Parameters.Size, "getSize", "setSize", "(I)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "contains", "", "element", Signatures.MATH_TO_BOOLEAN, "add", "addAll", "elements", "", "retainAll", "removeAll", "containsAll", "remove", "clear", "", "iterator", "", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PersistentHashSetBuilder<E> extends AbstractMutableSet<E> implements PersistentSet.Builder<E> {
    private PersistentHashSet<E> builtSet;
    private int modCount;
    private TrieNode<E> node;
    private MutabilityOwnership ownership;
    private int size;

    public PersistentHashSetBuilder(PersistentHashSet<E> set) {
        Intrinsics.checkNotNullParameter(set, "set");
        this.builtSet = set;
        this.ownership = new MutabilityOwnership();
        this.node = set.getNode$kotlinx_collections_immutable();
        this.size = set.size();
    }

    /* JADX INFO: renamed from: getOwnership$kotlinx_collections_immutable, reason: from getter */
    public final MutabilityOwnership getOwnership() {
        return this.ownership;
    }

    public final TrieNode<E> getNode$kotlinx_collections_immutable() {
        return this.node;
    }

    private final void setNode(TrieNode<E> trieNode) {
        if (trieNode != this.node) {
            this.builtSet = null;
            this.node = trieNode;
        }
    }

    /* JADX INFO: renamed from: getModCount$kotlinx_collections_immutable, reason: from getter */
    public final int getModCount() {
        return this.modCount;
    }

    @Override // kotlin.collections.AbstractMutableSet
    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
        this.modCount++;
    }

    @Override // kotlinx.collections.immutable.PersistentSet.Builder, kotlinx.collections.immutable.PersistentCollection.Builder
    public PersistentHashSet<E> build() {
        PersistentHashSet<E> persistentHashSet = this.builtSet;
        if (persistentHashSet != null) {
            return persistentHashSet;
        }
        PersistentHashSet<E> persistentHashSet2 = new PersistentHashSet<>(this.node, size());
        this.ownership = new MutabilityOwnership();
        this.builtSet = persistentHashSet2;
        return persistentHashSet2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object element) {
        return this.node.contains(element != null ? element.hashCode() : 0, element, 0);
    }

    @Override // kotlin.collections.AbstractMutableSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(E element) {
        int size = size();
        setNode(this.node.mutableAdd(element != null ? element.hashCode() : 0, element, 0, this));
        return size != size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return false;
        }
        PersistentHashSet<E> persistentHashSetBuild = elements instanceof PersistentHashSet ? (PersistentHashSet) elements : null;
        if (persistentHashSetBuild == null) {
            PersistentHashSetBuilder persistentHashSetBuilder = elements instanceof PersistentHashSetBuilder ? (PersistentHashSetBuilder) elements : null;
            persistentHashSetBuild = persistentHashSetBuilder != null ? persistentHashSetBuilder.build() : null;
        }
        if (persistentHashSetBuild != null) {
            DeltaCounter deltaCounter = new DeltaCounter(0, 1, null);
            int size = size();
            TrieNode<E> trieNodeMutableAddAll = this.node.mutableAddAll(persistentHashSetBuild.getNode$kotlinx_collections_immutable(), 0, deltaCounter, this);
            int size2 = (elements.size() + size) - deltaCounter.getCount();
            if (size != size2) {
                setNode(trieNodeMutableAddAll);
                setSize(size2);
            }
            return size != size();
        }
        return super.addAll(elements);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        PersistentHashSet<E> persistentHashSetBuild = elements instanceof PersistentHashSet ? (PersistentHashSet) elements : null;
        if (persistentHashSetBuild == null) {
            PersistentHashSetBuilder persistentHashSetBuilder = elements instanceof PersistentHashSetBuilder ? (PersistentHashSetBuilder) elements : null;
            persistentHashSetBuild = persistentHashSetBuilder != null ? persistentHashSetBuilder.build() : null;
        }
        if (persistentHashSetBuild != null) {
            DeltaCounter deltaCounter = new DeltaCounter(0, 1, null);
            int size = size();
            Object objMutableRetainAll = this.node.mutableRetainAll(persistentHashSetBuild.getNode$kotlinx_collections_immutable(), 0, deltaCounter, this);
            int count = deltaCounter.getCount();
            if (count == 0) {
                clear();
            } else if (count != size) {
                Intrinsics.checkNotNull(objMutableRetainAll, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.PersistentHashSetBuilder>");
                setNode((TrieNode) objMutableRetainAll);
                setSize(count);
            }
            return size != size();
        }
        return super.retainAll(elements);
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return false;
        }
        PersistentHashSet<E> persistentHashSetBuild = elements instanceof PersistentHashSet ? (PersistentHashSet) elements : null;
        if (persistentHashSetBuild == null) {
            PersistentHashSetBuilder persistentHashSetBuilder = elements instanceof PersistentHashSetBuilder ? (PersistentHashSetBuilder) elements : null;
            persistentHashSetBuild = persistentHashSetBuilder != null ? persistentHashSetBuilder.build() : null;
        }
        if (persistentHashSetBuild != null) {
            DeltaCounter deltaCounter = new DeltaCounter(0, 1, null);
            int size = size();
            Object objMutableRemoveAll = this.node.mutableRemoveAll(persistentHashSetBuild.getNode$kotlinx_collections_immutable(), 0, deltaCounter, this);
            int count = size - deltaCounter.getCount();
            if (count == 0) {
                clear();
            } else if (count != size) {
                Intrinsics.checkNotNull(objMutableRemoveAll, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.PersistentHashSetBuilder>");
                setNode((TrieNode) objMutableRemoveAll);
                setSize(count);
            }
            return size != size();
        }
        return super.removeAll(elements);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements instanceof PersistentHashSet) {
            return this.node.containsAll(((PersistentHashSet) elements).getNode$kotlinx_collections_immutable(), 0);
        }
        if (elements instanceof PersistentHashSetBuilder) {
            return this.node.containsAll(((PersistentHashSetBuilder) elements).node, 0);
        }
        return super.containsAll(elements);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object element) {
        int size = size();
        setNode(this.node.mutableRemove(element != null ? element.hashCode() : 0, element, 0, this));
        return size != size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        TrieNode eMPTY$kotlinx_collections_immutable = TrieNode.INSTANCE.getEMPTY$kotlinx_collections_immutable();
        Intrinsics.checkNotNull(eMPTY$kotlinx_collections_immutable, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.PersistentHashSetBuilder>");
        setNode(eMPTY$kotlinx_collections_immutable);
        setSize(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return new PersistentHashSetMutableIterator(this);
    }
}
