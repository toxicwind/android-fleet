package kotlinx.collections.immutable.implementations.immutableSet;

import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.collections.immutable.internal.CommonFunctionsKt;

/* JADX INFO: compiled from: PersistentHashSetIterator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\rH\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\t\u0010\u0012\u001a\u00020\u0013H\u0096\u0002J\u000e\u0010\u001a\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001bJ\r\u0010\u001c\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u001bR \u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t0\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\u00020\u00138\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0015¨\u0006\u001d"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableSet/PersistentHashSetIterator;", ExifInterface.LONGITUDE_EAST, "", "node", "Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "<init>", "(Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;)V", "path", "", "Lkotlinx/collections/immutable/implementations/immutableSet/TrieNodeIterator;", "getPath", "()Ljava/util/List;", "pathLastIndex", "", "getPathLastIndex", "()I", "setPathLastIndex", "(I)V", "hasNext", "", "getHasNext$annotations", "()V", "moveToNextNodeWithData", "pathIndex", "ensureNextElementIsReady", "", "next", "()Ljava/lang/Object;", "currentElement", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class PersistentHashSetIterator<E> implements Iterator<E>, KMappedMarker {
    private boolean hasNext;
    private final List<TrieNodeIterator<E>> path;
    private int pathLastIndex;

    private static /* synthetic */ void getHasNext$annotations() {
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public PersistentHashSetIterator(TrieNode<E> node) {
        Intrinsics.checkNotNullParameter(node, "node");
        List<TrieNodeIterator<E>> listMutableListOf = CollectionsKt.mutableListOf(new TrieNodeIterator());
        this.path = listMutableListOf;
        this.hasNext = true;
        TrieNodeIterator.reset$default(listMutableListOf.get(0), node.getBuffer(), 0, 2, null);
        this.pathLastIndex = 0;
        ensureNextElementIsReady();
    }

    protected final List<TrieNodeIterator<E>> getPath() {
        return this.path;
    }

    protected final int getPathLastIndex() {
        return this.pathLastIndex;
    }

    protected final void setPathLastIndex(int i) {
        this.pathLastIndex = i;
    }

    private final int moveToNextNodeWithData(int pathIndex) {
        if (this.path.get(pathIndex).hasNextElement()) {
            return pathIndex;
        }
        if (!this.path.get(pathIndex).hasNextNode()) {
            return -1;
        }
        TrieNode<? extends E> trieNodeCurrentNode = this.path.get(pathIndex).currentNode();
        int i = pathIndex + 1;
        if (i == this.path.size()) {
            this.path.add(new TrieNodeIterator<>());
        }
        TrieNodeIterator.reset$default(this.path.get(i), trieNodeCurrentNode.getBuffer(), 0, 2, null);
        return moveToNextNodeWithData(i);
    }

    private final void ensureNextElementIsReady() {
        if (this.path.get(this.pathLastIndex).hasNextElement()) {
            return;
        }
        for (int i = this.pathLastIndex; -1 < i; i--) {
            int iMoveToNextNodeWithData = moveToNextNodeWithData(i);
            if (iMoveToNextNodeWithData == -1 && this.path.get(i).hasNextCell()) {
                this.path.get(i).moveToNextCell();
                iMoveToNextNodeWithData = moveToNextNodeWithData(i);
            }
            if (iMoveToNextNodeWithData != -1) {
                this.pathLastIndex = iMoveToNextNodeWithData;
                return;
            }
            if (i > 0) {
                this.path.get(i - 1).moveToNextCell();
            }
            this.path.get(i).reset(TrieNode.INSTANCE.getEMPTY$kotlinx_collections_immutable().getBuffer(), 0);
        }
        this.hasNext = false;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // java.util.Iterator
    public E next() {
        if (!this.hasNext) {
            throw new NoSuchElementException();
        }
        E eNextElement = this.path.get(this.pathLastIndex).nextElement();
        ensureNextElementIsReady();
        return eNextElement;
    }

    protected final E currentElement() {
        CommonFunctionsKt.m10467assert(hasNext());
        return this.path.get(this.pathLastIndex).currentElement();
    }
}
