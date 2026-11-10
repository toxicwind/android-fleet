package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
public final class SortedLists {

    enum KeyAbsentBehavior {
        NEXT_LOWER { // from class: com.google.common.collect.SortedLists.KeyAbsentBehavior.1
            @Override // com.google.common.collect.SortedLists.KeyAbsentBehavior
            public int resultIndex(int i) {
                return i - 1;
            }
        },
        NEXT_HIGHER { // from class: com.google.common.collect.SortedLists.KeyAbsentBehavior.2
            @Override // com.google.common.collect.SortedLists.KeyAbsentBehavior
            public int resultIndex(int i) {
                return i;
            }
        },
        INVERTED_INSERTION_INDEX { // from class: com.google.common.collect.SortedLists.KeyAbsentBehavior.3
            @Override // com.google.common.collect.SortedLists.KeyAbsentBehavior
            public int resultIndex(int i) {
                return ~i;
            }
        };

        public abstract int resultIndex(int i);

        /* synthetic */ KeyAbsentBehavior(AnonymousClass1 anonymousClass1) {
        }
    }

    enum KeyPresentBehavior {
        ANY_PRESENT { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.1
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            public <E> int resultIndex(Comparator<? super E> comparator, E e2, List<? extends E> list, int i) {
                return i;
            }
        },
        LAST_PRESENT { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            public <E> int resultIndex(Comparator<? super E> comparator, E e2, List<? extends E> list, int i) {
                int size = list.size() - 1;
                while (i < size) {
                    int i2 = ((i + size) + 1) >>> 1;
                    if (comparator.compare(list.get(i2), e2) > 0) {
                        size = i2 - 1;
                    } else {
                        i = i2;
                    }
                }
                return i;
            }
        },
        FIRST_PRESENT { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            public <E> int resultIndex(Comparator<? super E> comparator, E e2, List<? extends E> list, int i) {
                int i2 = 0;
                while (i2 < i) {
                    int i3 = (i2 + i) >>> 1;
                    if (comparator.compare(list.get(i3), e2) < 0) {
                        i2 = i3 + 1;
                    } else {
                        i = i3;
                    }
                }
                return i2;
            }
        },
        FIRST_AFTER { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.4
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            public <E> int resultIndex(Comparator<? super E> comparator, E e2, List<? extends E> list, int i) {
                return KeyPresentBehavior.LAST_PRESENT.resultIndex(comparator, e2, list, i) + 1;
            }
        },
        LAST_BEFORE { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.5
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            public <E> int resultIndex(Comparator<? super E> comparator, E e2, List<? extends E> list, int i) {
                return KeyPresentBehavior.FIRST_PRESENT.resultIndex(comparator, e2, list, i) - 1;
            }
        };

        public abstract <E> int resultIndex(Comparator<? super E> comparator, E e2, List<? extends E> list, int i);

        /* synthetic */ KeyPresentBehavior(AnonymousClass1 anonymousClass1) {
        }
    }

    public static <E extends Comparable> int binarySearch(List<? extends E> list, E e2, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        if (e2 != null) {
            return binarySearch(list, e2, Ordering.natural(), keyPresentBehavior, keyAbsentBehavior);
        }
        throw new NullPointerException();
    }

    public static <E, K extends Comparable> int binarySearch(List<E> list, Function<? super E, K> function, K k, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        return binarySearch(list, function, k, Ordering.natural(), keyPresentBehavior, keyAbsentBehavior);
    }

    public static <E, K> int binarySearch(List<E> list, Function<? super E, K> function, K k, Comparator<? super K> comparator, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        return binarySearch((List<? extends K>) Lists.transform(list, function), k, comparator, keyPresentBehavior, keyAbsentBehavior);
    }

    public static <E> int binarySearch(List<? extends E> list, E e2, Comparator<? super E> comparator, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        if (comparator == null) {
            throw new NullPointerException();
        }
        if (list == null) {
            throw new NullPointerException();
        }
        if (keyPresentBehavior == null) {
            throw new NullPointerException();
        }
        if (keyAbsentBehavior != null) {
            if (!(list instanceof RandomAccess)) {
                list = Lists.newArrayList(list);
            }
            int i = 0;
            int size = list.size() - 1;
            while (i <= size) {
                int i2 = (i + size) >>> 1;
                int iCompare = comparator.compare(e2, list.get(i2));
                if (iCompare < 0) {
                    size = i2 - 1;
                } else {
                    if (iCompare <= 0) {
                        return keyPresentBehavior.resultIndex(comparator, e2, list.subList(i, size + 1), i2 - i) + i;
                    }
                    i = i2 + 1;
                }
            }
            return keyAbsentBehavior.resultIndex(i);
        }
        throw new NullPointerException();
    }
}
