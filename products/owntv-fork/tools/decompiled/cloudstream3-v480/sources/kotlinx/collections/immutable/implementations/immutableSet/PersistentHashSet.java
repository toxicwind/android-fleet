package kotlinx.collections.immutable.implementations.immutableSet;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.AbstractSet;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentSet;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: PersistentHashSet.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \"*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\"B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0011J\u001b\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016J\u001b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016J\"\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000f0\u001aH\u0016J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016J\u0016\u0010\u001c\u001a\u00020\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0096\u0002J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000!H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006#"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableSet/PersistentHashSet;", ExifInterface.LONGITUDE_EAST, "Lkotlin/collections/AbstractSet;", "Lkotlinx/collections/immutable/PersistentSet;", "node", "Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", ContentDisposition.Parameters.Size, "", "<init>", "(Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;I)V", "getNode$kotlinx_collections_immutable", "()Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "getSize", "()I", "contains", "", "element", Signatures.MATH_TO_BOOLEAN, "add", "(Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentSet;", "addAll", "elements", "", "remove", "removeAll", "predicate", "Lkotlin/Function1;", "retainAll", "containsAll", "clear", "iterator", "", "builder", "Lkotlinx/collections/immutable/PersistentSet$Builder;", "Companion", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PersistentHashSet<E> extends AbstractSet<E> implements PersistentSet<E> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final PersistentHashSet EMPTY = new PersistentHashSet(TrieNode.INSTANCE.getEMPTY$kotlinx_collections_immutable(), 0);
    private final TrieNode<E> node;
    private final int size;

    public final TrieNode<E> getNode$kotlinx_collections_immutable() {
        return this.node;
    }

    public PersistentHashSet(TrieNode<E> node, int i) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.node = node;
        this.size = i;
    }

    @Override // kotlin.collections.AbstractCollection
    public int getSize() {
        return this.size;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object element) {
        return this.node.contains(element != null ? element.hashCode() : 0, element, 0);
    }

    @Override // java.util.Collection, java.util.Set, kotlinx.collections.immutable.PersistentCollection
    public PersistentSet<E> add(E element) {
        TrieNode<E> trieNodeAdd = this.node.add(element != null ? element.hashCode() : 0, element, 0);
        return this.node == trieNodeAdd ? this : new PersistentHashSet(trieNodeAdd, size() + 1);
    }

    @Override // java.util.Collection, java.util.Set, kotlinx.collections.immutable.PersistentCollection
    public PersistentSet<E> addAll(Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return this;
        }
        PersistentSet.Builder<E> builder = builder();
        builder.addAll(elements);
        return builder.build();
    }

    @Override // java.util.Collection, java.util.Set, kotlinx.collections.immutable.PersistentCollection
    public PersistentSet<E> remove(E element) {
        TrieNode<E> trieNodeRemove = this.node.remove(element != null ? element.hashCode() : 0, element, 0);
        return this.node == trieNodeRemove ? this : new PersistentHashSet(trieNodeRemove, size() - 1);
    }

    @Override // java.util.Collection, java.util.Set, kotlinx.collections.immutable.PersistentCollection
    public PersistentSet<E> removeAll(Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return this;
        }
        PersistentSet.Builder<E> builder = builder();
        builder.removeAll(elements);
        return builder.build();
    }

    @Override // kotlinx.collections.immutable.PersistentCollection
    public PersistentSet<E> removeAll(Function1<? super E, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        PersistentSet.Builder<E> builder = builder();
        CollectionsKt.removeAll(builder, predicate);
        return builder.build();
    }

    @Override // java.util.Collection, java.util.Set, kotlinx.collections.immutable.PersistentCollection
    public PersistentSet<E> retainAll(Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return INSTANCE.emptyOf$kotlinx_collections_immutable();
        }
        PersistentSet.Builder<E> builder = builder();
        builder.retainAll(elements);
        return builder.build();
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public boolean containsAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements instanceof PersistentHashSet) {
            return this.node.containsAll(((PersistentHashSet) elements).node, 0);
        }
        if (elements instanceof PersistentHashSetBuilder) {
            return this.node.containsAll(((PersistentHashSetBuilder) elements).getNode$kotlinx_collections_immutable(), 0);
        }
        return super.containsAll(elements);
    }

    @Override // java.util.Collection, java.util.Set, kotlinx.collections.immutable.PersistentCollection
    public PersistentSet<E> clear() {
        return INSTANCE.emptyOf$kotlinx_collections_immutable();
    }

    @Override // kotlin.collections.AbstractSet, kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return new PersistentHashSetIterator(this.node);
    }

    @Override // kotlinx.collections.immutable.PersistentCollection
    public PersistentSet.Builder<E> builder() {
        return new PersistentHashSetBuilder(this);
    }

    /* JADX INFO: compiled from: PersistentHashSet.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0001\u0010\tH\u0000¢\u0006\u0002\b\nR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableSet/PersistentHashSet$Companion;", "", "<init>", "()V", "EMPTY", "Lkotlinx/collections/immutable/implementations/immutableSet/PersistentHashSet;", "", "emptyOf", "Lkotlinx/collections/immutable/PersistentSet;", ExifInterface.LONGITUDE_EAST, "emptyOf$kotlinx_collections_immutable", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <E> PersistentSet<E> emptyOf$kotlinx_collections_immutable() {
            return PersistentHashSet.EMPTY;
        }
    }
}
