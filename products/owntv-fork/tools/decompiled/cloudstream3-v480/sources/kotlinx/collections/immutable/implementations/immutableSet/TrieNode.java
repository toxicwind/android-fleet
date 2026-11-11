package kotlinx.collections.immutable.implementations.immutableSet;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.internal.CommonFunctionsKt;
import kotlinx.collections.immutable.internal.DeltaCounter;
import kotlinx.collections.immutable.internal.MutabilityOwnership;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: TrieNode.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0000\u0018\u0000 _*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001_B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006¢\u0006\u0004\b\t\u0010\u000bJ\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0015\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u001dJ\u0015\u0010\u001e\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010 J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J-\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u00002\b\u0010$\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u0010%J5\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010'\u001a\u00020\u00042\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00062\b\u0010$\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u0010)J.\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010+\u001a\u00020\u00042\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010$\u001a\u0004\u0018\u00010\bH\u0002J*\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010.\u001a\u00020\u00042\b\u0010/\u001a\u0004\u0018\u00010\u00022\b\u0010$\u001a\u0004\u0018\u00010\bH\u0002J=\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00028\u00002\u0006\u00104\u001a\u00020\u00042\b\u0010$\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u00105J=\u00106\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00028\u00002\u0006\u00104\u001a\u00020\u00042\b\u0010$\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u00105JE\u00107\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00028\u00002\u0006\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00028\u00002\u0006\u00104\u001a\u00020\u00042\b\u0010$\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u0010<J(\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010.\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\b\u0010$\u001a\u0004\u0018\u00010\bH\u0002J \u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010?\u001a\u00020\u00042\b\u0010$\u001a\u0004\u0018\u00010\bH\u0002J\u0015\u0010@\u001a\u00020\u001a2\u0006\u0010#\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010AJ\u001b\u0010B\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010#\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010CJ'\u0010D\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010#\u001a\u00028\u00002\n\u0010E\u001a\u0006\u0012\u0002\b\u00030FH\u0002¢\u0006\u0002\u0010GJ\u001b\u0010H\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010#\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010CJ'\u0010I\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010#\u001a\u00028\u00002\n\u0010E\u001a\u0006\u0012\u0002\b\u00030FH\u0002¢\u0006\u0002\u0010GJ,\u0010J\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010L\u001a\u00020M2\u0006\u0010$\u001a\u00020\bH\u0002J(\u0010N\u001a\u0004\u0018\u00010\u00022\f\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010L\u001a\u00020M2\u0006\u0010$\u001a\u00020\bH\u0002J(\u0010O\u001a\u0004\u0018\u00010\u00022\f\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010L\u001a\u00020M2\u0006\u0010$\u001a\u00020\bH\u0002J\b\u0010P\u001a\u00020\u0004H\u0002J\u0016\u0010Q\u001a\u00020\u001a2\f\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002J#\u0010R\u001a\u00020\u001a2\u0006\u0010S\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u00002\u0006\u00104\u001a\u00020\u0004¢\u0006\u0002\u0010TJ6\u0010U\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00104\u001a\u00020\u00042\u0006\u0010L\u001a\u00020M2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030FJ2\u0010V\u001a\u0004\u0018\u00010\u00022\f\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00104\u001a\u00020\u00042\u0006\u0010L\u001a\u00020M2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030FJ2\u0010W\u001a\u0004\u0018\u00010\u00022\f\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00104\u001a\u00020\u00042\u0006\u0010L\u001a\u00020M2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030FJ\u001c\u0010X\u001a\u00020\u001a2\f\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00104\u001a\u00020\u0004J)\u0010Y\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010S\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u00002\u0006\u00104\u001a\u00020\u0004¢\u0006\u0002\u0010ZJ5\u0010[\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010S\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u00002\u0006\u00104\u001a\u00020\u00042\n\u0010E\u001a\u0006\u0012\u0002\b\u00030F¢\u0006\u0002\u0010\\J)\u0010]\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010S\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u00002\u0006\u00104\u001a\u00020\u0004¢\u0006\u0002\u0010ZJ5\u0010^\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010S\u001a\u00020\u00042\u0006\u0010#\u001a\u00028\u00002\u0006\u00104\u001a\u00020\u00042\n\u0010E\u001a\u0006\u0012\u0002\b\u00030F¢\u0006\u0002\u0010\\R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006`"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", ExifInterface.LONGITUDE_EAST, "", "bitmap", "", "buffer", "", "ownedBy", "Lkotlinx/collections/immutable/internal/MutabilityOwnership;", "<init>", "(I[Ljava/lang/Object;Lkotlinx/collections/immutable/internal/MutabilityOwnership;)V", "(I[Ljava/lang/Object;)V", "getBitmap", "()I", "setBitmap", "(I)V", "getBuffer", "()[Ljava/lang/Object;", "setBuffer", "([Ljava/lang/Object;)V", "[Ljava/lang/Object;", "getOwnedBy", "()Lkotlinx/collections/immutable/internal/MutabilityOwnership;", "setOwnedBy", "(Lkotlinx/collections/immutable/internal/MutabilityOwnership;)V", "hasNoCellAt", "", "positionMask", "indexOfCellAt", "indexOfCellAt$kotlinx_collections_immutable", "elementAtIndex", "index", "(I)Ljava/lang/Object;", "nodeAtIndex", "addElementAt", "element", "owner", "(ILjava/lang/Object;Lkotlinx/collections/immutable/internal/MutabilityOwnership;)Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "setProperties", "newBitmap", "newBuffer", "(I[Ljava/lang/Object;Lkotlinx/collections/immutable/internal/MutabilityOwnership;)Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "canonicalizeNodeAtIndex", "nodeIndex", "newNode", "setCellAtIndex", "cellIndex", "newCell", "makeNodeAtIndex", "elementIndex", "newElementHash", "newElement", "shift", "(IILjava/lang/Object;ILkotlinx/collections/immutable/internal/MutabilityOwnership;)Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "moveElementToNode", "makeNode", "elementHash1", "element1", "elementHash2", "element2", "(ILjava/lang/Object;ILjava/lang/Object;ILkotlinx/collections/immutable/internal/MutabilityOwnership;)Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "removeCellAtIndex", "collisionRemoveElementAtIndex", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "collisionContainsElement", Signatures.MATH_TO_BOOLEAN, "collisionAdd", "(Ljava/lang/Object;)Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "mutableCollisionAdd", "mutator", "Lkotlinx/collections/immutable/implementations/immutableSet/PersistentHashSetBuilder;", "(Ljava/lang/Object;Lkotlinx/collections/immutable/implementations/immutableSet/PersistentHashSetBuilder;)Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "collisionRemove", "mutableCollisionRemove", "mutableCollisionAddAll", "otherNode", "intersectionSizeRef", "Lkotlinx/collections/immutable/internal/DeltaCounter;", "mutableCollisionRetainAll", "mutableCollisionRemoveAll", "calculateSize", "elementsIdentityEquals", "contains", "elementHash", "(ILjava/lang/Object;I)Z", "mutableAddAll", "mutableRetainAll", "mutableRemoveAll", "containsAll", "add", "(ILjava/lang/Object;I)Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "mutableAdd", "(ILjava/lang/Object;ILkotlinx/collections/immutable/implementations/immutableSet/PersistentHashSetBuilder;)Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "remove", "mutableRemove", "Companion", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TrieNode<E> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final TrieNode EMPTY = new TrieNode(0, new Object[0]);
    private int bitmap;
    private Object[] buffer;
    private MutabilityOwnership ownedBy;

    public TrieNode(int i, Object[] buffer, MutabilityOwnership mutabilityOwnership) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.bitmap = i;
        this.buffer = buffer;
        this.ownedBy = mutabilityOwnership;
    }

    public final int getBitmap() {
        return this.bitmap;
    }

    public final void setBitmap(int i) {
        this.bitmap = i;
    }

    public final Object[] getBuffer() {
        return this.buffer;
    }

    public final void setBuffer(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<set-?>");
        this.buffer = objArr;
    }

    public final MutabilityOwnership getOwnedBy() {
        return this.ownedBy;
    }

    public final void setOwnedBy(MutabilityOwnership mutabilityOwnership) {
        this.ownedBy = mutabilityOwnership;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TrieNode(int i, Object[] buffer) {
        this(i, buffer, null);
        Intrinsics.checkNotNullParameter(buffer, "buffer");
    }

    private final boolean hasNoCellAt(int positionMask) {
        return (this.bitmap & positionMask) == 0;
    }

    public final int indexOfCellAt$kotlinx_collections_immutable(int positionMask) {
        return Integer.bitCount(this.bitmap & (positionMask - 1));
    }

    private final E elementAtIndex(int index) {
        return (E) this.buffer[index];
    }

    private final TrieNode<E> nodeAtIndex(int index) {
        Object obj = this.buffer[index];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
        return (TrieNode) obj;
    }

    private final TrieNode<E> addElementAt(int positionMask, E element, MutabilityOwnership owner) {
        return setProperties(positionMask | this.bitmap, TrieNodeKt.addElementAtIndex(this.buffer, indexOfCellAt$kotlinx_collections_immutable(positionMask), element), owner);
    }

    private final TrieNode<E> setProperties(int newBitmap, Object[] newBuffer, MutabilityOwnership owner) {
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        if (mutabilityOwnership != null && mutabilityOwnership == owner) {
            this.bitmap = newBitmap;
            this.buffer = newBuffer;
            return this;
        }
        return new TrieNode<>(newBitmap, newBuffer, owner);
    }

    private final TrieNode<E> canonicalizeNodeAtIndex(int nodeIndex, TrieNode<E> newNode, MutabilityOwnership owner) {
        Object[] objArr = newNode.buffer;
        if (objArr.length == 1) {
            Object obj = objArr[0];
            if (!(obj instanceof TrieNode)) {
                if (this.buffer.length == 1) {
                    newNode.bitmap = this.bitmap;
                    return newNode;
                }
                newNode = (TrieNode<E>) obj;
            }
        }
        return setCellAtIndex(nodeIndex, newNode, owner);
    }

    private final TrieNode<E> setCellAtIndex(int cellIndex, Object newCell, MutabilityOwnership owner) {
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        if (mutabilityOwnership != null && mutabilityOwnership == owner) {
            this.buffer[cellIndex] = newCell;
            return this;
        }
        Object[] objArr = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        objArrCopyOf[cellIndex] = newCell;
        return new TrieNode<>(this.bitmap, objArrCopyOf, owner);
    }

    private final TrieNode<E> makeNodeAtIndex(int elementIndex, int newElementHash, E newElement, int shift, MutabilityOwnership owner) {
        E eElementAtIndex = elementAtIndex(elementIndex);
        return makeNode(eElementAtIndex != null ? eElementAtIndex.hashCode() : 0, eElementAtIndex, newElementHash, newElement, shift + 5, owner);
    }

    private final TrieNode<E> moveElementToNode(int elementIndex, int newElementHash, E newElement, int shift, MutabilityOwnership owner) {
        return setCellAtIndex(elementIndex, makeNodeAtIndex(elementIndex, newElementHash, newElement, shift, owner), owner);
    }

    private final TrieNode<E> makeNode(int elementHash1, E element1, int elementHash2, E element2, int shift, MutabilityOwnership owner) {
        Object[] objArr;
        if (shift > 30) {
            return new TrieNode<>(0, new Object[]{element1, element2}, owner);
        }
        int iIndexSegment = TrieNodeKt.indexSegment(elementHash1, shift);
        int iIndexSegment2 = TrieNodeKt.indexSegment(elementHash2, shift);
        if (iIndexSegment != iIndexSegment2) {
            if (iIndexSegment < iIndexSegment2) {
                objArr = new Object[]{element1, element2};
            } else {
                objArr = new Object[]{element2, element1};
            }
            return new TrieNode<>((1 << iIndexSegment) | (1 << iIndexSegment2), objArr, owner);
        }
        return new TrieNode<>(1 << iIndexSegment, new Object[]{makeNode(elementHash1, element1, elementHash2, element2, shift + 5, owner)}, owner);
    }

    private final TrieNode<E> removeCellAtIndex(int cellIndex, int positionMask, MutabilityOwnership owner) {
        return setProperties(positionMask ^ this.bitmap, TrieNodeKt.removeCellAtIndex(this.buffer, cellIndex), owner);
    }

    private final TrieNode<E> collisionRemoveElementAtIndex(int i, MutabilityOwnership owner) {
        return setProperties(0, TrieNodeKt.removeCellAtIndex(this.buffer, i), owner);
    }

    private final boolean collisionContainsElement(E element) {
        return ArraysKt.contains((E[]) this.buffer, element);
    }

    private final TrieNode<E> collisionAdd(E element) {
        return collisionContainsElement(element) ? this : setProperties(0, TrieNodeKt.addElementAtIndex(this.buffer, 0, element), null);
    }

    private final TrieNode<E> mutableCollisionAdd(E element, PersistentHashSetBuilder<?> mutator) {
        if (collisionContainsElement(element)) {
            return this;
        }
        mutator.setSize(mutator.size() + 1);
        return setProperties(0, TrieNodeKt.addElementAtIndex(this.buffer, 0, element), mutator.getOwnership());
    }

    private final TrieNode<E> collisionRemove(E element) {
        int iIndexOf = ArraysKt.indexOf((E[]) this.buffer, element);
        return iIndexOf != -1 ? collisionRemoveElementAtIndex(iIndexOf, null) : this;
    }

    private final TrieNode<E> mutableCollisionRemove(E element, PersistentHashSetBuilder<?> mutator) {
        int iIndexOf = ArraysKt.indexOf((E[]) this.buffer, element);
        if (iIndexOf == -1) {
            return this;
        }
        mutator.setSize(mutator.size() - 1);
        return collisionRemoveElementAtIndex(iIndexOf, mutator.getOwnership());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final TrieNode<E> mutableCollisionAddAll(TrieNode<E> otherNode, DeltaCounter intersectionSizeRef, MutabilityOwnership owner) {
        Object[] objArr = this.buffer;
        if (this == otherNode) {
            intersectionSizeRef.plusAssign(objArr.length);
            return this;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length + otherNode.buffer.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        Object[] objArr2 = otherNode.buffer;
        int length = this.buffer.length;
        int i = 0;
        int i2 = 0;
        while (i < objArr2.length) {
            CommonFunctionsKt.m10467assert(i2 <= i);
            if (!collisionContainsElement(objArr2[i])) {
                objArrCopyOf[length + i2] = objArr2[i];
                i2++;
                CommonFunctionsKt.m10467assert(length + i2 <= objArrCopyOf.length);
            }
            i++;
        }
        int length2 = i2 + this.buffer.length;
        intersectionSizeRef.plusAssign(objArrCopyOf.length - length2);
        if (length2 == this.buffer.length) {
            return this;
        }
        if (length2 == otherNode.buffer.length) {
            return otherNode;
        }
        if (length2 != objArrCopyOf.length) {
            objArrCopyOf = Arrays.copyOf(objArrCopyOf, length2);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        }
        return setProperties(0, objArrCopyOf, owner);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object mutableCollisionRetainAll(TrieNode<E> otherNode, DeltaCounter intersectionSizeRef, MutabilityOwnership owner) {
        if (this == otherNode) {
            intersectionSizeRef.plusAssign(this.buffer.length);
            return this;
        }
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (owner != mutabilityOwnership) {
            objArr = new Object[Math.min(objArr.length, otherNode.buffer.length)];
        }
        Object[] objArr2 = this.buffer;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= objArr2.length) {
                break;
            }
            CommonFunctionsKt.m10467assert(i2 <= i);
            if (otherNode.collisionContainsElement(objArr2[i])) {
                objArr[i2] = objArr2[i];
                i2++;
                CommonFunctionsKt.m10467assert(i2 <= objArr.length);
            }
            i++;
        }
        intersectionSizeRef.plusAssign(i2);
        if (i2 == 0) {
            return EMPTY;
        }
        if (i2 == 1) {
            return objArr[0];
        }
        if (i2 == this.buffer.length) {
            return this;
        }
        if (i2 == otherNode.buffer.length) {
            return otherNode;
        }
        if (i2 == objArr.length) {
            return setProperties(0, objArr, owner);
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, i2);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        return setProperties(0, objArrCopyOf, owner);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object mutableCollisionRemoveAll(TrieNode<E> otherNode, DeltaCounter intersectionSizeRef, MutabilityOwnership owner) {
        if (this == otherNode) {
            intersectionSizeRef.plusAssign(this.buffer.length);
            return EMPTY;
        }
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (owner != mutabilityOwnership) {
            objArr = new Object[objArr.length];
        }
        Object[] objArr2 = this.buffer;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= objArr2.length) {
                break;
            }
            CommonFunctionsKt.m10467assert(i2 <= i);
            if (!otherNode.collisionContainsElement(objArr2[i])) {
                objArr[i2] = objArr2[i];
                i2++;
                CommonFunctionsKt.m10467assert(i2 <= objArr.length);
            }
            i++;
        }
        intersectionSizeRef.plusAssign(this.buffer.length - i2);
        if (i2 == 0) {
            return EMPTY;
        }
        if (i2 == 1) {
            return objArr[0];
        }
        if (i2 == this.buffer.length) {
            return this;
        }
        if (i2 == objArr.length) {
            return setProperties(0, objArr, owner);
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, i2);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        return setProperties(0, objArrCopyOf, owner);
    }

    private final int calculateSize() {
        int i = this.bitmap;
        Object[] objArr = this.buffer;
        if (i == 0) {
            return objArr.length;
        }
        int iCalculateSize = 0;
        for (Object obj : objArr) {
            iCalculateSize += obj instanceof TrieNode ? ((TrieNode) obj).calculateSize() : 1;
        }
        return iCalculateSize;
    }

    private final boolean elementsIdentityEquals(TrieNode<E> otherNode) {
        if (this == otherNode) {
            return true;
        }
        if (this.bitmap != otherNode.bitmap) {
            return false;
        }
        int length = this.buffer.length;
        for (int i = 0; i < length; i++) {
            if (this.buffer[i] != otherNode.buffer[i]) {
                return false;
            }
        }
        return true;
    }

    public final boolean contains(int elementHash, E element, int shift) {
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(iIndexSegment)) {
            return false;
        }
        int iIndexOfCellAt$kotlinx_collections_immutable = indexOfCellAt$kotlinx_collections_immutable(iIndexSegment);
        Object obj = this.buffer[iIndexOfCellAt$kotlinx_collections_immutable];
        if (obj instanceof TrieNode) {
            TrieNode<E> trieNodeNodeAtIndex = nodeAtIndex(iIndexOfCellAt$kotlinx_collections_immutable);
            if (shift == 30) {
                return trieNodeNodeAtIndex.collisionContainsElement(element);
            }
            return trieNodeNodeAtIndex.contains(elementHash, element, shift + 5);
        }
        return Intrinsics.areEqual(element, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final TrieNode<E> mutableAddAll(TrieNode<E> otherNode, int shift, DeltaCounter intersectionSizeRef, PersistentHashSetBuilder<?> mutator) {
        Object objMakeNode;
        Object[] objArr;
        Intrinsics.checkNotNullParameter(otherNode, "otherNode");
        Intrinsics.checkNotNullParameter(intersectionSizeRef, "intersectionSizeRef");
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        if (this == otherNode) {
            intersectionSizeRef.setCount(intersectionSizeRef.getCount() + calculateSize());
            return this;
        }
        if (shift > 30) {
            return mutableCollisionAddAll(otherNode, intersectionSizeRef, mutator.getOwnership());
        }
        int i = this.bitmap;
        int i2 = otherNode.bitmap | i;
        TrieNode<E> trieNode = (i2 == i && Intrinsics.areEqual(this.ownedBy, mutator.getOwnership())) ? this : new TrieNode<>(i2, new Object[Integer.bitCount(i2)], mutator.getOwnership());
        int i3 = i2;
        int i4 = 0;
        while (i3 != 0) {
            int iLowestOneBit = Integer.lowestOneBit(i3);
            int iIndexOfCellAt$kotlinx_collections_immutable = indexOfCellAt$kotlinx_collections_immutable(iLowestOneBit);
            int iIndexOfCellAt$kotlinx_collections_immutable2 = otherNode.indexOfCellAt$kotlinx_collections_immutable(iLowestOneBit);
            Object[] objArr2 = trieNode.buffer;
            if (hasNoCellAt(iLowestOneBit)) {
                objMakeNode = otherNode.buffer[iIndexOfCellAt$kotlinx_collections_immutable2];
            } else {
                boolean zHasNoCellAt = otherNode.hasNoCellAt(iLowestOneBit);
                Object[] objArr3 = this.buffer;
                if (zHasNoCellAt) {
                    objMakeNode = objArr3[iIndexOfCellAt$kotlinx_collections_immutable];
                } else {
                    objMakeNode = objArr3[iIndexOfCellAt$kotlinx_collections_immutable];
                    Object obj = otherNode.buffer[iIndexOfCellAt$kotlinx_collections_immutable2];
                    boolean z = objMakeNode instanceof TrieNode;
                    boolean z2 = obj instanceof TrieNode;
                    if (z && z2) {
                        Intrinsics.checkNotNull(objMakeNode, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                        objMakeNode = ((TrieNode) objMakeNode).mutableAddAll((TrieNode) obj, shift + 5, intersectionSizeRef, mutator);
                    } else if (z) {
                        Intrinsics.checkNotNull(objMakeNode, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                        TrieNode trieNode2 = (TrieNode) objMakeNode;
                        int size = mutator.size();
                        objMakeNode = trieNode2.mutableAdd(obj != null ? obj.hashCode() : 0, obj, shift + 5, mutator);
                        if (mutator.size() == size) {
                            intersectionSizeRef.setCount(intersectionSizeRef.getCount() + 1);
                        }
                        Unit unit = Unit.INSTANCE;
                    } else if (z2) {
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                        TrieNode trieNode3 = (TrieNode) obj;
                        int size2 = mutator.size();
                        objMakeNode = trieNode3.mutableAdd(objMakeNode != null ? objMakeNode.hashCode() : 0, objMakeNode, shift + 5, mutator);
                        if (mutator.size() == size2) {
                            intersectionSizeRef.setCount(intersectionSizeRef.getCount() + 1);
                        }
                        Unit unit2 = Unit.INSTANCE;
                    } else {
                        if (Intrinsics.areEqual(objMakeNode, obj)) {
                            intersectionSizeRef.setCount(intersectionSizeRef.getCount() + 1);
                            Unit unit3 = Unit.INSTANCE;
                        } else {
                            objArr = objArr2;
                            objMakeNode = makeNode(objMakeNode != null ? objMakeNode.hashCode() : 0, objMakeNode, obj != null ? obj.hashCode() : 0, obj, shift + 5, mutator.getOwnership());
                        }
                        objArr[i4] = objMakeNode;
                        i4++;
                        i3 ^= iLowestOneBit;
                    }
                }
            }
            objArr = objArr2;
            objArr[i4] = objMakeNode;
            i4++;
            i3 ^= iLowestOneBit;
        }
        if (elementsIdentityEquals(trieNode)) {
            return this;
        }
        return otherNode.elementsIdentityEquals(trieNode) ? otherNode : trieNode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object mutableRetainAll(TrieNode<E> otherNode, int shift, DeltaCounter intersectionSizeRef, PersistentHashSetBuilder<?> mutator) {
        Intrinsics.checkNotNullParameter(otherNode, "otherNode");
        Intrinsics.checkNotNullParameter(intersectionSizeRef, "intersectionSizeRef");
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        if (this == otherNode) {
            intersectionSizeRef.plusAssign(calculateSize());
            return this;
        }
        if (shift > 30) {
            return mutableCollisionRetainAll(otherNode, intersectionSizeRef, mutator.getOwnership());
        }
        int i = this.bitmap & otherNode.bitmap;
        if (i == 0) {
            return EMPTY;
        }
        TrieNode<E> trieNode = (Intrinsics.areEqual(this.ownedBy, mutator.getOwnership()) && i == this.bitmap) ? this : new TrieNode<>(i, new Object[Integer.bitCount(i)], mutator.getOwnership());
        int i2 = i;
        int i3 = 0;
        int i4 = 0;
        while (i2 != 0) {
            int iLowestOneBit = Integer.lowestOneBit(i2);
            int iIndexOfCellAt$kotlinx_collections_immutable = indexOfCellAt$kotlinx_collections_immutable(iLowestOneBit);
            int iIndexOfCellAt$kotlinx_collections_immutable2 = otherNode.indexOfCellAt$kotlinx_collections_immutable(iLowestOneBit);
            Object objMutableRetainAll = this.buffer[iIndexOfCellAt$kotlinx_collections_immutable];
            Object obj = otherNode.buffer[iIndexOfCellAt$kotlinx_collections_immutable2];
            boolean z = objMutableRetainAll instanceof TrieNode;
            boolean z2 = obj instanceof TrieNode;
            if (z && z2) {
                Intrinsics.checkNotNull(objMutableRetainAll, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                objMutableRetainAll = ((TrieNode) objMutableRetainAll).mutableRetainAll((TrieNode) obj, shift + 5, intersectionSizeRef, mutator);
            } else if (z) {
                Intrinsics.checkNotNull(objMutableRetainAll, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                if (((TrieNode) objMutableRetainAll).contains(obj != null ? obj.hashCode() : 0, obj, shift + 5)) {
                    intersectionSizeRef.plusAssign(1);
                    objMutableRetainAll = obj;
                } else {
                    objMutableRetainAll = EMPTY;
                }
            } else if (z2) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                if (((TrieNode) obj).contains(objMutableRetainAll != null ? objMutableRetainAll.hashCode() : 0, objMutableRetainAll, shift + 5)) {
                    intersectionSizeRef.plusAssign(1);
                } else {
                    objMutableRetainAll = EMPTY;
                }
            } else if (Intrinsics.areEqual(objMutableRetainAll, obj)) {
                intersectionSizeRef.plusAssign(1);
            } else {
                objMutableRetainAll = EMPTY;
            }
            if (objMutableRetainAll != EMPTY) {
                i3 |= iLowestOneBit;
            }
            trieNode.buffer[i4] = objMutableRetainAll;
            i4++;
            i2 ^= iLowestOneBit;
        }
        int iBitCount = Integer.bitCount(i3);
        if (i3 == 0) {
            return EMPTY;
        }
        if (i3 == i) {
            if (trieNode.elementsIdentityEquals(this)) {
                return this;
            }
            return trieNode.elementsIdentityEquals(otherNode) ? otherNode : trieNode;
        }
        if (iBitCount == 1 && shift != 0) {
            Object obj2 = trieNode.buffer[trieNode.indexOfCellAt$kotlinx_collections_immutable(i3)];
            return obj2 instanceof TrieNode ? new TrieNode(i3, new Object[]{obj2}, mutator.getOwnership()) : obj2;
        }
        Object[] objArr = new Object[iBitCount];
        Object[] objArr2 = trieNode.buffer;
        int i5 = 0;
        int i6 = 0;
        while (i5 < objArr2.length) {
            CommonFunctionsKt.m10467assert(i6 <= i5);
            if (objArr2[i5] != INSTANCE.getEMPTY$kotlinx_collections_immutable()) {
                objArr[i6] = objArr2[i5];
                i6++;
                CommonFunctionsKt.m10467assert(i6 <= iBitCount);
            }
            i5++;
        }
        return new TrieNode(i3, objArr, mutator.getOwnership());
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00c9  */
    /* JADX WARN: Multi-variable type inference failed */
    public final Object mutableRemoveAll(TrieNode<E> otherNode, int shift, DeltaCounter intersectionSizeRef, PersistentHashSetBuilder<?> mutator) {
        TrieNode<E> trieNode;
        Intrinsics.checkNotNullParameter(otherNode, "otherNode");
        Intrinsics.checkNotNullParameter(intersectionSizeRef, "intersectionSizeRef");
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        if (this == otherNode) {
            intersectionSizeRef.plusAssign(calculateSize());
            return EMPTY;
        }
        if (shift > 30) {
            return mutableCollisionRemoveAll(otherNode, intersectionSizeRef, mutator.getOwnership());
        }
        int i = this.bitmap & otherNode.bitmap;
        if (i != 0) {
            if (Intrinsics.areEqual(this.ownedBy, mutator.getOwnership())) {
                trieNode = this;
            } else {
                int i2 = this.bitmap;
                Object[] objArr = this.buffer;
                Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
                trieNode = new TrieNode<>(i2, objArrCopyOf, mutator.getOwnership());
            }
            int i3 = this.bitmap;
            while (i != 0) {
                int iLowestOneBit = Integer.lowestOneBit(i);
                int iIndexOfCellAt$kotlinx_collections_immutable = indexOfCellAt$kotlinx_collections_immutable(iLowestOneBit);
                int iIndexOfCellAt$kotlinx_collections_immutable2 = otherNode.indexOfCellAt$kotlinx_collections_immutable(iLowestOneBit);
                Object objMutableRemoveAll = this.buffer[iIndexOfCellAt$kotlinx_collections_immutable];
                Object obj = otherNode.buffer[iIndexOfCellAt$kotlinx_collections_immutable2];
                boolean z = objMutableRemoveAll instanceof TrieNode;
                boolean z2 = obj instanceof TrieNode;
                if (z && z2) {
                    Intrinsics.checkNotNull(objMutableRemoveAll, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                    objMutableRemoveAll = ((TrieNode) objMutableRemoveAll).mutableRemoveAll((TrieNode) obj, shift + 5, intersectionSizeRef, mutator);
                } else if (z) {
                    Intrinsics.checkNotNull(objMutableRemoveAll, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                    TrieNode trieNode2 = (TrieNode) objMutableRemoveAll;
                    int size = mutator.size();
                    TrieNode trieNodeMutableRemove = trieNode2.mutableRemove(obj != null ? obj.hashCode() : 0, obj, shift + 5, mutator);
                    if (size != mutator.size()) {
                        intersectionSizeRef.plusAssign(1);
                        Object[] objArr2 = trieNodeMutableRemove.buffer;
                        if (objArr2.length == 1) {
                            objMutableRemoveAll = objArr2[0];
                            if (objMutableRemoveAll instanceof TrieNode) {
                                objMutableRemoveAll = trieNodeMutableRemove;
                            }
                        } else {
                            objMutableRemoveAll = trieNodeMutableRemove;
                        }
                    }
                } else if (z2) {
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                    if (((TrieNode) obj).contains(objMutableRemoveAll != null ? objMutableRemoveAll.hashCode() : 0, objMutableRemoveAll, shift + 5)) {
                        intersectionSizeRef.plusAssign(1);
                        objMutableRemoveAll = EMPTY;
                    }
                } else if (Intrinsics.areEqual(objMutableRemoveAll, obj)) {
                    intersectionSizeRef.plusAssign(1);
                    objMutableRemoveAll = EMPTY;
                }
                if (objMutableRemoveAll == EMPTY) {
                    i3 ^= iLowestOneBit;
                }
                trieNode.buffer[iIndexOfCellAt$kotlinx_collections_immutable] = objMutableRemoveAll;
                i ^= iLowestOneBit;
            }
            int iBitCount = Integer.bitCount(i3);
            if (i3 == 0) {
                return EMPTY;
            }
            if (iBitCount == 1 && shift != 0) {
                Object obj2 = trieNode.buffer[trieNode.indexOfCellAt$kotlinx_collections_immutable(i3)];
                return obj2 instanceof TrieNode ? new TrieNode(i3, new Object[]{obj2}, mutator.getOwnership()) : obj2;
            }
            if (i3 == this.bitmap) {
                if (!trieNode.elementsIdentityEquals(this)) {
                    return trieNode;
                }
            } else {
                Object[] objArr3 = new Object[iBitCount];
                Object[] objArr4 = trieNode.buffer;
                int i4 = 0;
                int i5 = 0;
                while (i4 < objArr4.length) {
                    CommonFunctionsKt.m10467assert(i5 <= i4);
                    if (objArr4[i4] != INSTANCE.getEMPTY$kotlinx_collections_immutable()) {
                        objArr3[i5] = objArr4[i4];
                        i5++;
                        CommonFunctionsKt.m10467assert(i5 <= iBitCount);
                    }
                    i4++;
                }
                return new TrieNode(i3, objArr3, mutator.getOwnership());
            }
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean containsAll(TrieNode<E> otherNode, int shift) {
        Intrinsics.checkNotNullParameter(otherNode, "otherNode");
        if (this == otherNode) {
            return true;
        }
        if (shift > 30) {
            for (Object obj : otherNode.buffer) {
                if (!ArraysKt.contains(this.buffer, obj)) {
                    return false;
                }
            }
            return true;
        }
        int i = this.bitmap;
        int i2 = otherNode.bitmap;
        int i3 = i & i2;
        if (i3 != i2) {
            return false;
        }
        while (i3 != 0) {
            int iLowestOneBit = Integer.lowestOneBit(i3);
            int iIndexOfCellAt$kotlinx_collections_immutable = indexOfCellAt$kotlinx_collections_immutable(iLowestOneBit);
            int iIndexOfCellAt$kotlinx_collections_immutable2 = otherNode.indexOfCellAt$kotlinx_collections_immutable(iLowestOneBit);
            Object obj2 = this.buffer[iIndexOfCellAt$kotlinx_collections_immutable];
            Object obj3 = otherNode.buffer[iIndexOfCellAt$kotlinx_collections_immutable2];
            boolean z = obj2 instanceof TrieNode;
            boolean z2 = obj3 instanceof TrieNode;
            if (z && z2) {
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                if (!((TrieNode) obj2).containsAll((TrieNode) obj3, shift + 5)) {
                    return false;
                }
            } else if (z) {
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
                if (!((TrieNode) obj2).contains(obj3 != null ? obj3.hashCode() : 0, obj3, shift + 5)) {
                    return false;
                }
            } else if (z2 || !Intrinsics.areEqual(obj2, obj3)) {
                return false;
            }
            i3 ^= iLowestOneBit;
        }
        return true;
    }

    public final TrieNode<E> add(int elementHash, E element, int shift) {
        TrieNode<E> trieNodeAdd;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(iIndexSegment)) {
            return addElementAt(iIndexSegment, element, null);
        }
        int iIndexOfCellAt$kotlinx_collections_immutable = indexOfCellAt$kotlinx_collections_immutable(iIndexSegment);
        Object obj = this.buffer[iIndexOfCellAt$kotlinx_collections_immutable];
        if (obj instanceof TrieNode) {
            TrieNode<E> trieNodeNodeAtIndex = nodeAtIndex(iIndexOfCellAt$kotlinx_collections_immutable);
            if (shift == 30) {
                trieNodeAdd = trieNodeNodeAtIndex.collisionAdd(element);
            } else {
                trieNodeAdd = trieNodeNodeAtIndex.add(elementHash, element, shift + 5);
            }
            if (trieNodeNodeAtIndex != trieNodeAdd) {
                return setCellAtIndex(iIndexOfCellAt$kotlinx_collections_immutable, trieNodeAdd, null);
            }
        } else if (!Intrinsics.areEqual(element, obj)) {
            return moveElementToNode(iIndexOfCellAt$kotlinx_collections_immutable, elementHash, element, shift, null);
        }
        return this;
    }

    public final TrieNode<E> mutableAdd(int elementHash, E element, int shift, PersistentHashSetBuilder<?> mutator) {
        TrieNode<E> trieNodeMutableAdd;
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(iIndexSegment)) {
            mutator.setSize(mutator.size() + 1);
            return addElementAt(iIndexSegment, element, mutator.getOwnership());
        }
        int iIndexOfCellAt$kotlinx_collections_immutable = indexOfCellAt$kotlinx_collections_immutable(iIndexSegment);
        Object obj = this.buffer[iIndexOfCellAt$kotlinx_collections_immutable];
        if (obj instanceof TrieNode) {
            TrieNode<E> trieNodeNodeAtIndex = nodeAtIndex(iIndexOfCellAt$kotlinx_collections_immutable);
            if (shift == 30) {
                trieNodeMutableAdd = trieNodeNodeAtIndex.mutableCollisionAdd(element, mutator);
            } else {
                trieNodeMutableAdd = trieNodeNodeAtIndex.mutableAdd(elementHash, element, shift + 5, mutator);
            }
            if (trieNodeNodeAtIndex != trieNodeMutableAdd) {
                return setCellAtIndex(iIndexOfCellAt$kotlinx_collections_immutable, trieNodeMutableAdd, mutator.getOwnership());
            }
        } else if (!Intrinsics.areEqual(element, obj)) {
            mutator.setSize(mutator.size() + 1);
            return moveElementToNode(iIndexOfCellAt$kotlinx_collections_immutable, elementHash, element, shift, mutator.getOwnership());
        }
        return this;
    }

    public final TrieNode<E> remove(int elementHash, E element, int shift) {
        TrieNode<E> trieNodeRemove;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(iIndexSegment)) {
            return this;
        }
        int iIndexOfCellAt$kotlinx_collections_immutable = indexOfCellAt$kotlinx_collections_immutable(iIndexSegment);
        Object obj = this.buffer[iIndexOfCellAt$kotlinx_collections_immutable];
        if (!(obj instanceof TrieNode)) {
            return Intrinsics.areEqual(element, obj) ? removeCellAtIndex(iIndexOfCellAt$kotlinx_collections_immutable, iIndexSegment, null) : this;
        }
        TrieNode<E> trieNodeNodeAtIndex = nodeAtIndex(iIndexOfCellAt$kotlinx_collections_immutable);
        if (shift == 30) {
            trieNodeRemove = trieNodeNodeAtIndex.collisionRemove(element);
        } else {
            trieNodeRemove = trieNodeNodeAtIndex.remove(elementHash, element, shift + 5);
        }
        return trieNodeNodeAtIndex == trieNodeRemove ? this : canonicalizeNodeAtIndex(iIndexOfCellAt$kotlinx_collections_immutable, trieNodeRemove, null);
    }

    public final TrieNode<E> mutableRemove(int elementHash, E element, int shift, PersistentHashSetBuilder<?> mutator) {
        TrieNode<E> trieNodeMutableRemove;
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(iIndexSegment)) {
            return this;
        }
        int iIndexOfCellAt$kotlinx_collections_immutable = indexOfCellAt$kotlinx_collections_immutable(iIndexSegment);
        Object obj = this.buffer[iIndexOfCellAt$kotlinx_collections_immutable];
        if (obj instanceof TrieNode) {
            TrieNode<E> trieNodeNodeAtIndex = nodeAtIndex(iIndexOfCellAt$kotlinx_collections_immutable);
            if (shift == 30) {
                trieNodeMutableRemove = trieNodeNodeAtIndex.mutableCollisionRemove(element, mutator);
            } else {
                trieNodeMutableRemove = trieNodeNodeAtIndex.mutableRemove(elementHash, element, shift + 5, mutator);
            }
            return (trieNodeNodeAtIndex.ownedBy == mutator.getOwnership() || trieNodeNodeAtIndex != trieNodeMutableRemove) ? canonicalizeNodeAtIndex(iIndexOfCellAt$kotlinx_collections_immutable, trieNodeMutableRemove, mutator.getOwnership()) : this;
        }
        if (!Intrinsics.areEqual(element, obj)) {
            return this;
        }
        mutator.setSize(mutator.size() - 1);
        return removeCellAtIndex(iIndexOfCellAt$kotlinx_collections_immutable, iIndexSegment, mutator.getOwnership());
    }

    /* JADX INFO: compiled from: TrieNode.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode$Companion;", "", "<init>", "()V", "EMPTY", "Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "", "getEMPTY$kotlinx_collections_immutable", "()Lkotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TrieNode getEMPTY$kotlinx_collections_immutable() {
            return TrieNode.EMPTY;
        }
    }
}
