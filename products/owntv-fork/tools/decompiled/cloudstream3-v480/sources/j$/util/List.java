package j$.util;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: classes3.dex */
public interface List<E> extends Collection<E> {

    /* JADX INFO: renamed from: j$.util.List$-EL, reason: invalid class name */
    public final /* synthetic */ class EL {
        public static /* synthetic */ void replaceAll(java.util.List list, UnaryOperator unaryOperator) {
            if (list instanceof List) {
                ((List) list).replaceAll(unaryOperator);
            } else {
                CC.$default$replaceAll(list, unaryOperator);
            }
        }

        public static /* synthetic */ void sort(java.util.List list, java.util.Comparator comparator) {
            if (list instanceof List) {
                ((List) list).sort(comparator);
            } else {
                CC.$default$sort(list, comparator);
            }
        }
    }

    void replaceAll(UnaryOperator<E> unaryOperator);

    void sort(java.util.Comparator<? super E> comparator);

    @Override // java.util.Collection, java.lang.Iterable, j$.util.List, j$.util.Collection, j$.lang.Iterable
    Spliterator<E> spliterator();

    /* JADX INFO: renamed from: j$.util.List$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$replaceAll(java.util.List list, UnaryOperator unaryOperator) {
            Objects.requireNonNull(unaryOperator);
            ListIterator listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                listIterator.set(unaryOperator.apply(listIterator.next()));
            }
        }

        public static void $default$sort(java.util.List list, java.util.Comparator comparator) {
            Object[] array = list.toArray();
            Arrays.sort(array, comparator);
            ListIterator<E> listIterator = list.listIterator();
            for (Object obj : array) {
                listIterator.next();
                listIterator.set(obj);
            }
        }

        public static Spliterator $default$spliterator(java.util.List list) {
            if (list instanceof RandomAccess) {
                return new AbstractList.RandomAccessSpliterator(list);
            }
            return Spliterators.spliterator(list, 16);
        }
    }
}
