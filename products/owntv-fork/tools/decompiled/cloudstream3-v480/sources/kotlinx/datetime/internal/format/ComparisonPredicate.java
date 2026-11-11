package kotlinx.datetime.internal.format;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: Predicate.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B%\u0012\u0006\u0010\u0004\u001a\u00028\u0001\u0012\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rR\u0010\u0010\u0004\u001a\u00028\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u001c\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/datetime/internal/format/ComparisonPredicate;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.LONGITUDE_EAST, "Lkotlinx/datetime/internal/format/Predicate;", "expectedValue", "getter", "Lkotlin/Function1;", "<init>", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "Ljava/lang/Object;", "test", "", ES6Iterator.VALUE_PROPERTY, Signatures.MATH_TO_BOOLEAN, "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ComparisonPredicate<T, E> implements Predicate<T> {
    private final E expectedValue;
    private final Function1<T, E> getter;

    /* JADX WARN: Multi-variable type inference failed */
    public ComparisonPredicate(E e, Function1<? super T, ? extends E> getter) {
        Intrinsics.checkNotNullParameter(getter, "getter");
        this.expectedValue = e;
        this.getter = getter;
    }

    @Override // kotlinx.datetime.internal.format.Predicate
    public boolean test(T value) {
        return Intrinsics.areEqual(this.getter.invoke(value), this.expectedValue);
    }
}
