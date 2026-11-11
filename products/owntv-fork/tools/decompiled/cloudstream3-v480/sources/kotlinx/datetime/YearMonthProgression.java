package kotlinx.datetime;

import io.ktor.http.ContentDisposition;
import io.ktor.util.date.GMTDateParser;
import j$.lang.Iterable;
import j$.util.Spliterator;
import j$.util.stream.Stream;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.LongProgression;
import kotlinx.datetime.internal.MathKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: YearMonthRange.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0016\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001%B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006B!\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0016\u0010\u001d\u001a\u00020\u00162\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016J\u0011\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u0002H\u0096\u0002J\u0013\u0010!\u001a\u00020\u00162\b\u0010\"\u001a\u0004\u0018\u00010#H\u0096\u0002J\b\u0010$\u001a\u00020\u001aH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006&"}, d2 = {"Lkotlinx/datetime/YearMonthProgression;", "", "Lkotlinx/datetime/YearMonth;", "longProgression", "Lkotlin/ranges/LongProgression;", "<init>", "(Lkotlin/ranges/LongProgression;)V", "start", "endInclusive", "step", "", "(Lkotlinx/datetime/YearMonth;Lkotlinx/datetime/YearMonth;J)V", "getLongProgression$kotlinx_datetime", "()Lkotlin/ranges/LongProgression;", "first", "getFirst", "()Lkotlinx/datetime/YearMonth;", "last", "getLast", "iterator", "", "isEmpty", "", "toString", "", ContentDisposition.Parameters.Size, "", "getSize", "()I", "containsAll", "elements", "contains", ES6Iterator.VALUE_PROPERTY, "equals", "other", "", "hashCode", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class YearMonthProgression implements Collection<YearMonth>, KMappedMarker, j$.util.Collection {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final YearMonth first;
    private final YearMonth last;
    private final LongProgression longProgression;

    @Override // java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(YearMonth yearMonth) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX INFO: renamed from: add, reason: avoid collision after fix types in other method */
    public boolean add2(YearMonth yearMonth) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends YearMonth> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.lang.Iterable, j$.util.Collection, j$.lang.Iterable
    public /* synthetic */ void forEach(Consumer consumer) {
        Iterable.CC.$default$forEach(this, consumer);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ Stream parallelStream() {
        return j$.util.Collection.CC.$default$parallelStream(this);
    }

    @Override // java.util.Collection
    public /* synthetic */ java.util.stream.Stream<YearMonth> parallelStream() {
        return Stream.Wrapper.convert(parallelStream());
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, j$.util.Collection
    public boolean removeIf(Predicate<? super YearMonth> predicate) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.lang.Iterable, j$.util.Collection, j$.lang.Iterable
    public /* synthetic */ Spliterator spliterator() {
        return j$.util.Collection.CC.$default$spliterator(this);
    }

    @Override // java.util.Collection, java.lang.Iterable
    public /* synthetic */ java.util.Spliterator spliterator() {
        return Spliterator.Wrapper.convert(spliterator());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ Stream stream() {
        return j$.util.Collection.CC.$default$stream(this);
    }

    @Override // java.util.Collection
    public /* synthetic */ java.util.stream.Stream<YearMonth> stream() {
        return Stream.Wrapper.convert(stream());
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ Object[] toArray(IntFunction intFunction) {
        return toArray((Object[]) intFunction.apply(0));
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) CollectionToArray.toArray(this, array);
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof YearMonth) {
            return contains((YearMonth) obj);
        }
        return false;
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    public YearMonthProgression(LongProgression longProgression) {
        Intrinsics.checkNotNullParameter(longProgression, "longProgression");
        this.longProgression = longProgression;
        this.first = YearMonthKt.fromProlepticMonth(YearMonth.INSTANCE, longProgression.getFirst());
        this.last = YearMonthKt.fromProlepticMonth(YearMonth.INSTANCE, longProgression.getLast());
    }

    /* JADX INFO: renamed from: getLongProgression$kotlinx_datetime, reason: from getter */
    public final LongProgression getLongProgression() {
        return this.longProgression;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public YearMonthProgression(YearMonth start, YearMonth endInclusive, long j) {
        this(LongProgression.INSTANCE.fromClosedRange(YearMonthKt.getProlepticMonth(start), YearMonthKt.getProlepticMonth(endInclusive), j));
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(endInclusive, "endInclusive");
    }

    public final YearMonth getFirst() {
        return this.first;
    }

    public final YearMonth getLast() {
        return this.last;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.collections.LongIterator] */
    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<YearMonth> iterator() {
        return new YearMonthProgressionIterator(this.longProgression.iterator());
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.longProgression.isEmpty();
    }

    public String toString() {
        long step = this.longProgression.getStep();
        YearMonth yearMonth = this.first;
        if (step > 0) {
            return yearMonth + ".." + this.last + " step " + this.longProgression.getStep() + GMTDateParser.MONTH;
        }
        return yearMonth + " downTo " + this.last + " step " + this.longProgression.getStep() + GMTDateParser.MONTH;
    }

    public int getSize() {
        return MathKt.getSizeUnsafe(this.longProgression);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Collection<?> collection = elements;
        if (collection.isEmpty()) {
            return true;
        }
        for (Object obj : collection) {
            if (!(obj instanceof YearMonth) || !contains(obj)) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(YearMonth value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return MathKt.containsUnsafe(this.longProgression, YearMonthKt.getProlepticMonth(value));
    }

    @Override // java.util.Collection
    public boolean equals(Object other) {
        return (other instanceof YearMonthProgression) && Intrinsics.areEqual(this.longProgression, ((YearMonthProgression) other).longProgression);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return this.longProgression.hashCode();
    }

    /* JADX INFO: compiled from: YearMonthRange.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\r¨\u0006\u000e"}, d2 = {"Lkotlinx/datetime/YearMonthProgression$Companion;", "", "<init>", "()V", "fromClosedRange", "Lkotlinx/datetime/YearMonthProgression;", "rangeStart", "Lkotlinx/datetime/YearMonth;", "rangeEnd", "stepValue", "", "stepUnit", "Lkotlinx/datetime/DateTimeUnit$MonthBased;", "fromClosedRange$kotlinx_datetime", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final YearMonthProgression fromClosedRange$kotlinx_datetime(YearMonth rangeStart, YearMonth rangeEnd, long stepValue, DateTimeUnit.MonthBased stepUnit) {
            Intrinsics.checkNotNullParameter(rangeStart, "rangeStart");
            Intrinsics.checkNotNullParameter(rangeEnd, "rangeEnd");
            Intrinsics.checkNotNullParameter(stepUnit, "stepUnit");
            return new YearMonthProgression(rangeStart, rangeEnd, MathKt.safeMultiplyOrClamp(stepValue, stepUnit.getMonths()));
        }
    }
}
