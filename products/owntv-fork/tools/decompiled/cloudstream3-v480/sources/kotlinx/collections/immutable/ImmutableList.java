package kotlinx.collections.immutable;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.collections.immutable.internal.ListImplementation;

/* JADX INFO: compiled from: ImmutableList.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\bJ\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\t"}, d2 = {"Lkotlinx/collections/immutable/ImmutableList;", ExifInterface.LONGITUDE_EAST, "", "Lkotlinx/collections/immutable/ImmutableCollection;", "subList", "fromIndex", "", "toIndex", "SubList", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface ImmutableList<E> extends List<E>, ImmutableCollection<E>, KMappedMarker {
    @Override // java.util.List
    ImmutableList<E> subList(int fromIndex, int toIndex);

    /* JADX INFO: compiled from: ImmutableList.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <E> ImmutableList<E> subList(ImmutableList<? extends E> immutableList, int i, int i2) {
            return new SubList(immutableList, i, i2);
        }
    }

    /* JADX INFO: compiled from: ImmutableList.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u000b\u001a\u00028\u00012\u0006\u0010\f\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\rJ\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lkotlinx/collections/immutable/ImmutableList$SubList;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/collections/immutable/ImmutableList;", "Lkotlin/collections/AbstractList;", "source", "fromIndex", "", "toIndex", "<init>", "(Lkotlinx/collections/immutable/ImmutableList;II)V", "_size", "get", "index", "(I)Ljava/lang/Object;", ContentDisposition.Parameters.Size, "getSize", "()I", "subList", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class SubList<E> extends AbstractList<E> implements ImmutableList<E> {
        private int _size;
        private final int fromIndex;
        private final ImmutableList<E> source;
        private final int toIndex;

        /* JADX WARN: Multi-variable type inference failed */
        public SubList(ImmutableList<? extends E> source, int i, int i2) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.source = source;
            this.fromIndex = i;
            this.toIndex = i2;
            ListImplementation.checkRangeIndexes$kotlinx_collections_immutable(i, i2, source.size());
            this._size = i2 - i;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public E get(int index) {
            ListImplementation.checkElementIndex$kotlinx_collections_immutable(index, this._size);
            return this.source.get(this.fromIndex + index);
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        /* JADX INFO: renamed from: getSize, reason: from getter */
        public int get_size() {
            return this._size;
        }

        @Override // kotlin.collections.AbstractList, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableList
        public ImmutableList<E> subList(int fromIndex, int toIndex) {
            ListImplementation.checkRangeIndexes$kotlinx_collections_immutable(fromIndex, toIndex, this._size);
            ImmutableList<E> immutableList = this.source;
            int i = this.fromIndex;
            return new SubList(immutableList, fromIndex + i, i + toIndex);
        }
    }
}
