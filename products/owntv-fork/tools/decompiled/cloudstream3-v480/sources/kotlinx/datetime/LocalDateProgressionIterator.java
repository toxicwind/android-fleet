package kotlinx.datetime;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.LongIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: LocalDateRange.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\t\u0010\t\u001a\u00020\u0002H\u0096\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lkotlinx/datetime/LocalDateProgressionIterator;", "", "Lkotlinx/datetime/LocalDate;", "iterator", "Lkotlin/collections/LongIterator;", "<init>", "(Lkotlin/collections/LongIterator;)V", "hasNext", "", "next", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class LocalDateProgressionIterator implements Iterator<LocalDate>, KMappedMarker {
    private final LongIterator iterator;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public LocalDateProgressionIterator(LongIterator iterator) {
        Intrinsics.checkNotNullParameter(iterator, "iterator");
        this.iterator = iterator;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public LocalDate next() {
        return LocalDate.INSTANCE.fromEpochDays(this.iterator.nextLong());
    }
}
