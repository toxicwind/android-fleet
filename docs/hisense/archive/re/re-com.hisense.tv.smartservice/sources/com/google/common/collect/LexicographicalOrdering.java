package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(serializable = true)
public final class LexicographicalOrdering<T> extends Ordering<Iterable<T>> implements Serializable {
    public static final long serialVersionUID = 0;
    public final Comparator<? super T> elementOrder;

    public LexicographicalOrdering(Comparator<? super T> comparator) {
        this.elementOrder = comparator;
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LexicographicalOrdering) {
            return this.elementOrder.equals(((LexicographicalOrdering) obj).elementOrder);
        }
        return false;
    }

    public int hashCode() {
        return this.elementOrder.hashCode() ^ 2075626741;
    }

    public String toString() {
        String strValueOf = String.valueOf(this.elementOrder);
        return a.a(strValueOf.length() + 18, strValueOf, ".lexicographical()");
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Iterable<T> iterable, Iterable<T> iterable2) {
        Iterator<T> it = iterable.iterator();
        Iterator<T> it2 = iterable2.iterator();
        while (it.hasNext()) {
            if (!it2.hasNext()) {
                return 1;
            }
            int iCompare = this.elementOrder.compare(it.next(), it2.next());
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return it2.hasNext() ? -1 : 0;
    }
}
