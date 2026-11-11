package kotlinx.collections.immutable;

import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMutableCollection;

/* JADX INFO: compiled from: ImmutableCollection.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u0012J\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0004\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0005J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH&J\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0004\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0005J\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH&J\"\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\r0\fH&J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH&J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H&J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H&¨\u0006\u0013"}, d2 = {"Lkotlinx/collections/immutable/PersistentCollection;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/collections/immutable/ImmutableCollection;", "add", "element", "(Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentCollection;", "addAll", "elements", "", "remove", "removeAll", "predicate", "Lkotlin/Function1;", "", "retainAll", "clear", "builder", "Lkotlinx/collections/immutable/PersistentCollection$Builder;", "Builder", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface PersistentCollection<E> extends ImmutableCollection<E> {

    /* JADX INFO: compiled from: ImmutableCollection.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004H&¨\u0006\u0005"}, d2 = {"Lkotlinx/collections/immutable/PersistentCollection$Builder;", ExifInterface.LONGITUDE_EAST, "", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lkotlinx/collections/immutable/PersistentCollection;", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Builder<E> extends Collection<E>, KMutableCollection {
        PersistentCollection<E> build();
    }

    @Override // java.util.Collection
    PersistentCollection<E> add(E element);

    @Override // java.util.Collection
    PersistentCollection<E> addAll(Collection<? extends E> elements);

    Builder<E> builder();

    @Override // java.util.Collection
    PersistentCollection<E> clear();

    @Override // java.util.Collection
    PersistentCollection<E> remove(E element);

    @Override // java.util.Collection
    PersistentCollection<E> removeAll(Collection<? extends E> elements);

    PersistentCollection<E> removeAll(Function1<? super E, Boolean> predicate);

    @Override // java.util.Collection
    PersistentCollection<E> retainAll(Collection<? extends E> elements);
}
