package j$.lang;

import j$.util.DesugarLinkedHashSet;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public interface Iterable<T> {

    /* JADX INFO: renamed from: j$.lang.Iterable$-EL, reason: invalid class name */
    public final /* synthetic */ class EL {
        public static /* synthetic */ void forEach(java.lang.Iterable iterable, Consumer consumer) {
            if (iterable instanceof Iterable) {
                ((Iterable) iterable).forEach(consumer);
            } else if (iterable instanceof Collection) {
                j$.util.Collection.CC.$default$forEach((Collection) iterable, consumer);
            } else {
                CC.$default$forEach(iterable, consumer);
            }
        }

        public static /* synthetic */ Spliterator spliterator(java.lang.Iterable iterable) {
            if (iterable instanceof Iterable) {
                return ((Iterable) iterable).spliterator();
            }
            if (iterable instanceof LinkedHashSet) {
                return DesugarLinkedHashSet.spliterator((LinkedHashSet) iterable);
            }
            if (iterable instanceof SortedSet) {
                return j$.util.SortedSet.CC.$default$spliterator((SortedSet) iterable);
            }
            if (iterable instanceof Set) {
                return Spliterators.spliterator((Set) iterable, 1);
            }
            if (iterable instanceof List) {
                return j$.util.List.CC.$default$spliterator((List) iterable);
            }
            return iterable instanceof Collection ? j$.util.Collection.CC.$default$spliterator((Collection) iterable) : CC.$default$spliterator(iterable);
        }
    }

    void forEach(Consumer<? super T> consumer);

    Spliterator<T> spliterator();

    /* JADX INFO: renamed from: j$.lang.Iterable$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$forEach(java.lang.Iterable iterable, Consumer consumer) {
            Objects.requireNonNull(consumer);
            Iterator<T> it = iterable.iterator();
            while (it.hasNext()) {
                consumer.accept(it.next());
            }
        }

        public static Spliterator $default$spliterator(java.lang.Iterable iterable) {
            return Spliterators.spliteratorUnknownSize(iterable.iterator(), 0);
        }
    }
}
