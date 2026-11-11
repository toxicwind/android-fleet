package com.lagradost.cloudstream3.utils;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableList;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: AtomicList.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\t\n\u0002\u0010)\n\u0000\n\u0002\u0010+\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0017\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H\u0096\u0080\b¢\u0006\u0002\u0010\nJ\u001f\u0010\u0007\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0096\u0080\bJ \u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0096\u0080\bJ\u0017\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H\u0096\u0080\b¢\u0006\u0002\u0010\nJ\u0017\u0010\u0013\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\rH\u0096\u0080\b¢\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0096\u0080\bJ\u0018\u0010\u0016\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0096\u0080\bJ\u001f\u0010\u0017\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00028\u0000H\u0096\u0082\b¢\u0006\u0002\u0010\u0018J\n\u0010\u0019\u001a\u00020\u000bH\u0096\u0080\u0004J\u0010\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH\u0096\u0082\u0004J\u0010\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0096\u0080\u0004J\u0018\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001d2\u0006\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J \u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\rH\u0096\u0080\u0004R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/utils/AtomicMutableList;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/lagradost/cloudstream3/utils/AtomicList;", "", "mutableDelegate", "<init>", "(Ljava/util/List;)V", "add", "", "element", Signatures.MATH_TO_BOOLEAN, "", "index", "", "(ILjava/lang/Object;)V", "addAll", "elements", "", "remove", "removeAt", "(I)Ljava/lang/Object;", "removeAll", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "clear", "iterator", "", "listIterator", "", "subList", "fromIndex", "toIndex", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AtomicMutableList<T> extends AtomicList<T> implements List<T>, KMutableList, j$.util.List {
    private final List<T> mutableDelegate;

    /* JADX WARN: Multi-variable type inference failed */
    public AtomicMutableList() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AtomicMutableList(List<T> mutableDelegate) {
        super(mutableDelegate);
        Intrinsics.checkNotNullParameter(mutableDelegate, "mutableDelegate");
        this.mutableDelegate = mutableDelegate;
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List
    public final /* bridge */ T remove(int i) {
        return removeAt(i);
    }

    public /* synthetic */ AtomicMutableList(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : arrayList);
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List, java.util.Collection
    public boolean add(T element) {
        boolean zAdd;
        synchronized (this) {
            zAdd = this.mutableDelegate.add(element);
        }
        return zAdd;
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List
    public void add(int index, T element) {
        synchronized (this) {
            this.mutableDelegate.add(index, element);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends T> elements) {
        boolean zAddAll;
        Intrinsics.checkNotNullParameter(elements, "elements");
        synchronized (this) {
            zAddAll = this.mutableDelegate.addAll(elements);
        }
        return zAddAll;
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List
    public boolean addAll(int index, Collection<? extends T> elements) {
        boolean zAddAll;
        Intrinsics.checkNotNullParameter(elements, "elements");
        synchronized (this) {
            zAddAll = this.mutableDelegate.addAll(index, elements);
        }
        return zAddAll;
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List, java.util.Collection
    public boolean remove(Object element) {
        boolean zRemove;
        synchronized (this) {
            zRemove = this.mutableDelegate.remove(element);
        }
        return zRemove;
    }

    public T removeAt(int index) {
        T tRemove;
        synchronized (this) {
            tRemove = this.mutableDelegate.remove(index);
        }
        return tRemove;
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> elements) {
        boolean zRemoveAll;
        Intrinsics.checkNotNullParameter(elements, "elements");
        synchronized (this) {
            zRemoveAll = this.mutableDelegate.removeAll(elements);
        }
        return zRemoveAll;
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> elements) {
        boolean zRetainAll;
        Intrinsics.checkNotNullParameter(elements, "elements");
        synchronized (this) {
            zRetainAll = this.mutableDelegate.retainAll(elements);
        }
        return zRetainAll;
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List
    public T set(int index, T element) {
        T t;
        synchronized (this) {
            t = this.mutableDelegate.set(index, element);
        }
        return t;
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List, java.util.Collection
    public void clear() {
        synchronized (this) {
            this.mutableDelegate.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return this.mutableDelegate.iterator();
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List
    public ListIterator<T> listIterator() {
        return this.mutableDelegate.listIterator();
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List
    public ListIterator<T> listIterator(int index) {
        return this.mutableDelegate.listIterator(index);
    }

    @Override // com.lagradost.cloudstream3.utils.AtomicList, java.util.List
    public List<T> subList(int fromIndex, int toIndex) {
        return this.mutableDelegate.subList(fromIndex, toIndex);
    }
}
