package kotlinx.collections.immutable.implementations.immutableList;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ImmutableList;
import kotlinx.collections.immutable.PersistentList;
import kotlinx.collections.immutable.internal.CommonFunctionsKt;
import kotlinx.collections.immutable.internal.ListImplementation;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: SmallPersistentVector.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010*\n\u0002\b\u0005\b\u0000\u0018\u0000 )*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001)B\u0017\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\u000fJ\u001b\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0006\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016J\"\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016J$\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0006\u0010\u001b\u001a\u00020\u000b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016J#\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0006\u0010\u001b\u001a\u00020\u000bH\u0016J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000 H\u0016J\u0015\u0010!\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\"J\u0015\u0010#\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\"J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%2\u0006\u0010\u001b\u001a\u00020\u000bH\u0016J\u0016\u0010&\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u000bH\u0096\u0002¢\u0006\u0002\u0010'J#\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dR\u0018\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006*"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableList/SmallPersistentVector;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/collections/immutable/ImmutableList;", "Lkotlinx/collections/immutable/implementations/immutableList/AbstractPersistentList;", "buffer", "", "", "<init>", "([Ljava/lang/Object;)V", "[Ljava/lang/Object;", ContentDisposition.Parameters.Size, "", "getSize", "()I", "bufferOfSize", "(I)[Ljava/lang/Object;", "add", "Lkotlinx/collections/immutable/PersistentList;", "element", "(Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentList;", "addAll", "elements", "", "removeAll", "predicate", "Lkotlin/Function1;", "", "index", "c", "(ILjava/lang/Object;)Lkotlinx/collections/immutable/PersistentList;", "removeAt", "builder", "Lkotlinx/collections/immutable/PersistentList$Builder;", "indexOf", Signatures.MATH_TO_INT32, "lastIndexOf", "listIterator", "", "get", "(I)Ljava/lang/Object;", "set", "Companion", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SmallPersistentVector<E> extends AbstractPersistentList<E> implements ImmutableList<E> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final SmallPersistentVector EMPTY = new SmallPersistentVector(new Object[0]);
    private final Object[] buffer;

    public SmallPersistentVector(Object[] buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.buffer = buffer;
        CommonFunctionsKt.m10467assert(buffer.length <= 32);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.buffer.length;
    }

    private final Object[] bufferOfSize(int size) {
        return new Object[size];
    }

    @Override // java.util.Collection, java.util.List, kotlinx.collections.immutable.PersistentCollection
    public PersistentList<E> add(E element) {
        if (size() < 32) {
            Object[] objArrCopyOf = Arrays.copyOf(this.buffer, size() + 1);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
            objArrCopyOf[size()] = element;
            return new SmallPersistentVector(objArrCopyOf);
        }
        return new PersistentVector(this.buffer, UtilsKt.presizedBufferWith(element), size() + 1, 0);
    }

    @Override // kotlinx.collections.immutable.implementations.immutableList.AbstractPersistentList, java.util.Collection, java.util.List, kotlinx.collections.immutable.PersistentCollection
    public PersistentList<E> addAll(Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return this;
        }
        if (size() + elements.size() <= 32) {
            Object[] objArrCopyOf = Arrays.copyOf(this.buffer, size() + elements.size());
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
            int size = size();
            Iterator<? extends E> it = elements.iterator();
            while (it.hasNext()) {
                objArrCopyOf[size] = it.next();
                size++;
            }
            return new SmallPersistentVector(objArrCopyOf);
        }
        PersistentList.Builder<E> builder = builder();
        builder.addAll(elements);
        return builder.build();
    }

    @Override // kotlinx.collections.immutable.PersistentCollection
    public PersistentList<E> removeAll(Function1<? super E, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int size = size();
        int size2 = size();
        int i = 0;
        for (int i2 = 0; i2 < size2; i2++) {
            if (predicate.invoke(this.buffer[i2]).booleanValue()) {
                size--;
                i |= 1 << i2;
            }
        }
        if (size == size()) {
            return this;
        }
        if (size == 0) {
            return EMPTY;
        }
        Object[] objArrCopyOf = Arrays.copyOf(this.buffer, size);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
        int size3 = size();
        for (int i3 = iNumberOfTrailingZeros + 1; i3 < size3; i3++) {
            if (((i >>> i3) & 1) == 0) {
                objArrCopyOf[iNumberOfTrailingZeros] = this.buffer[i3];
                iNumberOfTrailingZeros++;
            }
        }
        return new SmallPersistentVector(objArrCopyOf);
    }

    @Override // kotlinx.collections.immutable.implementations.immutableList.AbstractPersistentList, java.util.List, kotlinx.collections.immutable.PersistentList
    public PersistentList<E> addAll(int index, Collection<? extends E> c) {
        Intrinsics.checkNotNullParameter(c, "c");
        ListImplementation.checkPositionIndex$kotlinx_collections_immutable(index, size());
        if (c.isEmpty()) {
            return this;
        }
        if (size() + c.size() <= 32) {
            Object[] objArrBufferOfSize = bufferOfSize(size() + c.size());
            ArraysKt.copyInto$default(this.buffer, objArrBufferOfSize, 0, 0, index, 6, (Object) null);
            ArraysKt.copyInto(this.buffer, objArrBufferOfSize, c.size() + index, index, size());
            Iterator<? extends E> it = c.iterator();
            int i = index;
            while (it.hasNext()) {
                objArrBufferOfSize[i] = it.next();
                i++;
            }
            return new SmallPersistentVector(objArrBufferOfSize);
        }
        PersistentList.Builder<E> builder = builder();
        builder.addAll(index, c);
        return builder.build();
    }

    @Override // java.util.List, kotlinx.collections.immutable.PersistentList
    public PersistentList<E> add(int index, E element) {
        ListImplementation.checkPositionIndex$kotlinx_collections_immutable(index, size());
        if (index == size()) {
            return add((Object) element);
        }
        if (size() < 32) {
            Object[] objArrBufferOfSize = bufferOfSize(size() + 1);
            ArraysKt.copyInto$default(this.buffer, objArrBufferOfSize, 0, 0, index, 6, (Object) null);
            ArraysKt.copyInto(this.buffer, objArrBufferOfSize, index + 1, index, size());
            objArrBufferOfSize[index] = element;
            return new SmallPersistentVector(objArrBufferOfSize);
        }
        Object[] objArr = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        ArraysKt.copyInto(this.buffer, objArrCopyOf, index + 1, index, size() - 1);
        objArrCopyOf[index] = element;
        return new PersistentVector(objArrCopyOf, UtilsKt.presizedBufferWith(this.buffer[31]), size() + 1, 0);
    }

    @Override // kotlinx.collections.immutable.PersistentList
    public PersistentList<E> removeAt(int index) {
        ListImplementation.checkElementIndex$kotlinx_collections_immutable(index, size());
        if (size() == 1) {
            return EMPTY;
        }
        Object[] objArrCopyOf = Arrays.copyOf(this.buffer, size() - 1);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        ArraysKt.copyInto(this.buffer, objArrCopyOf, index, index + 1, size());
        return new SmallPersistentVector(objArrCopyOf);
    }

    @Override // kotlinx.collections.immutable.PersistentCollection
    public PersistentList.Builder<E> builder() {
        return new PersistentVectorBuilder(this, null, this.buffer, 0);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public int indexOf(Object element) {
        return ArraysKt.indexOf(this.buffer, element);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public int lastIndexOf(Object element) {
        return ArraysKt.lastIndexOf(this.buffer, element);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public ListIterator<E> listIterator(int index) {
        ListImplementation.checkPositionIndex$kotlinx_collections_immutable(index, size());
        return new BufferIterator(this.buffer, index, size());
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public E get(int index) {
        ListImplementation.checkElementIndex$kotlinx_collections_immutable(index, size());
        return (E) this.buffer[index];
    }

    @Override // kotlin.collections.AbstractList, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public PersistentList<E> set(int index, E element) {
        ListImplementation.checkElementIndex$kotlinx_collections_immutable(index, size());
        Object[] objArr = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        objArrCopyOf[index] = element;
        return new SmallPersistentVector(objArrCopyOf);
    }

    /* JADX INFO: compiled from: SmallPersistentVector.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableList/SmallPersistentVector$Companion;", "", "<init>", "()V", "EMPTY", "Lkotlinx/collections/immutable/implementations/immutableList/SmallPersistentVector;", "", "getEMPTY", "()Lkotlinx/collections/immutable/implementations/immutableList/SmallPersistentVector;", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SmallPersistentVector getEMPTY() {
            return SmallPersistentVector.EMPTY;
        }
    }
}
