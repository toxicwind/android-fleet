package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.PreconditionsKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;
import kotlin.ranges.RangesKt;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: SnapshotStateList.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0007\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010+\n\u0002\b\t\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B%\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016J\u0016\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u001bJ\u0015\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u0013H\u0016J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000 H\u0096\u0002J\u0015\u0010!\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dJ\u0015\u0010\"\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u001d\u0010\"\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010$J\u001e\u0010%\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00062\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016J\u0016\u0010%\u001a\u00020\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016J\b\u0010&\u001a\u00020#H\u0016J\u000e\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000(H\u0016J\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000(2\u0006\u0010\u001a\u001a\u00020\u0006H\u0016J\u0015\u0010)\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u0016\u0010*\u001a\u00020\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016J\u0015\u0010+\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0006H\u0016¢\u0006\u0002\u0010\u001bJ\u0016\u0010,\u001a\u00020\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016J\u001e\u0010-\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010.J\u001e\u0010/\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u00100\u001a\u00020#H\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u00061"}, d2 = {"Landroidx/compose/runtime/snapshots/SubList;", ExifInterface.GPS_DIRECTION_TRUE, "", "parentList", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "fromIndex", "", "toIndex", "<init>", "(Landroidx/compose/runtime/snapshots/SnapshotStateList;II)V", "getParentList", "()Landroidx/compose/runtime/snapshots/SnapshotStateList;", TypedValues.CycleType.S_WAVE_OFFSET, "structure", ES6Iterator.VALUE_PROPERTY, ContentDisposition.Parameters.Size, "getSize", "()I", "contains", "", "element", Signatures.MATH_TO_BOOLEAN, "containsAll", "elements", "", "get", "index", "(I)Ljava/lang/Object;", "indexOf", Signatures.MATH_TO_INT32, "isEmpty", "iterator", "", "lastIndexOf", "add", "", "(ILjava/lang/Object;)V", "addAll", "clear", "listIterator", "", "remove", "removeAll", "removeAt", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "subList", "validateModification", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SubList<T> implements List<T>, KMutableList {
    public static final int $stable = 8;
    private final int offset;
    private final SnapshotStateList<T> parentList;
    private int size;
    private int structure;

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    public SubList(SnapshotStateList<T> snapshotStateList, int i, int i2) {
        this.parentList = snapshotStateList;
        this.offset = i;
        this.structure = SnapshotStateListKt.getStructure(snapshotStateList);
        this.size = i2 - i;
    }

    public final SnapshotStateList<T> getParentList() {
        return this.parentList;
    }

    @Override // java.util.List
    public final /* bridge */ T remove(int i) {
        return removeAt(i);
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    public int getSize() {
        return this.size;
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object element) {
        return indexOf(element) >= 0;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> elements) {
        Collection<?> collection = elements;
        if ((collection instanceof Collection) && collection.isEmpty()) {
            return true;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List
    public T get(int index) {
        validateModification();
        SnapshotStateListKt.validateRange(index, size());
        return this.parentList.get(this.offset + index);
    }

    @Override // java.util.List
    public int indexOf(Object element) {
        validateModification();
        int i = this.offset;
        Iterator<Integer> it = RangesKt.until(i, size() + i).iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            if (Intrinsics.areEqual(element, this.parentList.get(iNextInt))) {
                return iNextInt - this.offset;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object element) {
        validateModification();
        int size = this.offset + size();
        do {
            size--;
            if (size < this.offset) {
                return -1;
            }
        } while (!Intrinsics.areEqual(element, this.parentList.get(size)));
        return size - this.offset;
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(T element) {
        validateModification();
        this.parentList.add(this.offset + size(), element);
        this.size = size() + 1;
        this.structure = SnapshotStateListKt.getStructure(this.parentList);
        return true;
    }

    @Override // java.util.List
    public void add(int index, T element) {
        validateModification();
        this.parentList.add(this.offset + index, element);
        this.size = size() + 1;
        this.structure = SnapshotStateListKt.getStructure(this.parentList);
    }

    @Override // java.util.List
    public boolean addAll(int index, Collection<? extends T> elements) {
        validateModification();
        boolean zAddAll = this.parentList.addAll(index + this.offset, elements);
        if (zAddAll) {
            this.size = size() + elements.size();
            this.structure = SnapshotStateListKt.getStructure(this.parentList);
        }
        return zAddAll;
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends T> elements) {
        return addAll(size(), elements);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        if (size() > 0) {
            validateModification();
            SnapshotStateList<T> snapshotStateList = this.parentList;
            int i = this.offset;
            snapshotStateList.removeRange(i, size() + i);
            this.size = 0;
            this.structure = SnapshotStateListKt.getStructure(this.parentList);
        }
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(int index) {
        validateModification();
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = index - 1;
        return new AnonymousClass1(intRef, this);
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.SubList$listIterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: SnapshotStateList.kt */
    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0010+\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\r\u0010\u0006\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0005H\u0016J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\fJ\t\u0010\r\u001a\u00020\u0003H\u0096\u0002J\u000e\u0010\u000e\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0007J\b\u0010\u000f\u001a\u00020\nH\u0016J\u0015\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\f¨\u0006\u0011"}, d2 = {"androidx/compose/runtime/snapshots/SubList$listIterator$1", "", "hasPrevious", "", "nextIndex", "", "previous", "()Ljava/lang/Object;", "previousIndex", "add", "", "element", "(Ljava/lang/Object;)Ljava/lang/Void;", "hasNext", "next", "remove", "set", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AnonymousClass1 implements ListIterator<T>, KMutableListIterator {
        final /* synthetic */ Ref.IntRef $current;
        final /* synthetic */ SubList<T> this$0;

        AnonymousClass1(Ref.IntRef intRef, SubList<T> subList) {
            this.$current = intRef;
            this.this$0 = subList;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.$current.element >= 0;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.$current.element + 1;
        }

        @Override // java.util.ListIterator
        public T previous() {
            int i = this.$current.element;
            SnapshotStateListKt.validateRange(i, this.this$0.size());
            this.$current.element = i - 1;
            return this.this$0.get(i);
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.$current.element;
        }

        @Override // java.util.ListIterator
        public Void add(T element) {
            SnapshotStateListKt.modificationError();
            throw new KotlinNothingValueException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.$current.element < this.this$0.size() - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public T next() {
            int i = this.$current.element + 1;
            SnapshotStateListKt.validateRange(i, this.this$0.size());
            this.$current.element = i;
            return this.this$0.get(i);
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public Void remove() {
            SnapshotStateListKt.modificationError();
            throw new KotlinNothingValueException();
        }

        @Override // java.util.ListIterator
        public Void set(T element) {
            SnapshotStateListKt.modificationError();
            throw new KotlinNothingValueException();
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object element) {
        int iIndexOf = indexOf(element);
        if (iIndexOf < 0) {
            return false;
        }
        remove(iIndexOf);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> elements) {
        Iterator<?> it = elements.iterator();
        while (true) {
            boolean z = false;
            while (it.hasNext()) {
                if (remove(it.next()) || z) {
                    z = true;
                }
            }
            return z;
        }
    }

    public T removeAt(int index) {
        validateModification();
        T tRemove = this.parentList.remove(this.offset + index);
        this.size = size() - 1;
        this.structure = SnapshotStateListKt.getStructure(this.parentList);
        return tRemove;
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> elements) {
        validateModification();
        SnapshotStateList<T> snapshotStateList = this.parentList;
        int i = this.offset;
        int iRetainAllInRange$runtime = snapshotStateList.retainAllInRange$runtime(elements, i, size() + i);
        if (iRetainAllInRange$runtime > 0) {
            this.structure = SnapshotStateListKt.getStructure(this.parentList);
            this.size = size() - iRetainAllInRange$runtime;
        }
        return iRetainAllInRange$runtime > 0;
    }

    @Override // java.util.List
    public T set(int index, T element) {
        SnapshotStateListKt.validateRange(index, size());
        validateModification();
        T t = this.parentList.set(index + this.offset, element);
        this.structure = SnapshotStateListKt.getStructure(this.parentList);
        return t;
    }

    @Override // java.util.List
    public List<T> subList(int fromIndex, int toIndex) {
        if (!(fromIndex >= 0 && fromIndex <= toIndex && toIndex <= size())) {
            PreconditionsKt.throwIllegalArgumentException("fromIndex or toIndex are out of bounds");
        }
        validateModification();
        SnapshotStateList<T> snapshotStateList = this.parentList;
        int i = this.offset;
        return new SubList(snapshotStateList, fromIndex + i, toIndex + i);
    }

    private final void validateModification() {
        if (SnapshotStateListKt.getStructure(this.parentList) != this.structure) {
            throw new ConcurrentModificationException();
        }
    }
}
