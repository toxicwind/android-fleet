package kotlinx.collections.immutable.adapters;

import androidx.exifinterface.media.ExifInterface;
import j$.util.Set;
import j$.util.Spliterator;
import j$.util.Spliterators;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ImmutableSet;

/* JADX INFO: compiled from: ReadOnlyCollectionAdapters.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlinx/collections/immutable/adapters/ImmutableSetAdapter;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/collections/immutable/ImmutableSet;", "Lkotlinx/collections/immutable/adapters/ImmutableCollectionAdapter;", "impl", "", "<init>", "(Ljava/util/Set;)V", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ImmutableSetAdapter<E> extends ImmutableCollectionAdapter<E> implements ImmutableSet<E>, Set {
    @Override // kotlinx.collections.immutable.adapters.ImmutableCollectionAdapter, java.util.Collection, java.lang.Iterable, j$.util.Collection, j$.lang.Iterable
    public /* synthetic */ Spliterator spliterator() {
        return Spliterators.spliterator(this, 1);
    }

    @Override // kotlinx.collections.immutable.adapters.ImmutableCollectionAdapter, java.util.Collection, java.lang.Iterable
    public /* synthetic */ java.util.Spliterator spliterator() {
        return Spliterator.Wrapper.convert(spliterator());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImmutableSetAdapter(java.util.Set<? extends E> impl) {
        super(impl);
        Intrinsics.checkNotNullParameter(impl, "impl");
    }
}
