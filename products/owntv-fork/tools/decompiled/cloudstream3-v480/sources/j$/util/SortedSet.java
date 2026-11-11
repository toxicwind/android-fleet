package j$.util;

/* JADX INFO: loaded from: classes3.dex */
public interface SortedSet<E> extends Set<E> {

    /* JADX INFO: renamed from: j$.util.SortedSet$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static Spliterator $default$spliterator(final java.util.SortedSet sortedSet) {
            return new Spliterators.IteratorSpliterator(sortedSet, 21) { // from class: j$.util.SortedSet.1
                @Override // j$.util.Spliterators.IteratorSpliterator, j$.util.Spliterator
                public java.util.Comparator getComparator() {
                    return sortedSet.comparator();
                }
            };
        }
    }
}
