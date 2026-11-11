package kotlinx.datetime.internal.format;

import androidx.exifinterface.media.ExifInterface;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: Predicate.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\nR\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlinx/datetime/internal/format/ConjunctionPredicate;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/datetime/internal/format/Predicate;", "predicates", "", "<init>", "(Ljava/util/List;)V", "test", "", ES6Iterator.VALUE_PROPERTY, Signatures.MATH_TO_BOOLEAN, "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class ConjunctionPredicate<T> implements Predicate<T> {
    private final List<Predicate<T>> predicates;

    /* JADX WARN: Multi-variable type inference failed */
    public ConjunctionPredicate(List<? extends Predicate<? super T>> predicates) {
        Intrinsics.checkNotNullParameter(predicates, "predicates");
        this.predicates = predicates;
    }

    @Override // kotlinx.datetime.internal.format.Predicate
    public boolean test(T value) {
        List<Predicate<T>> list = this.predicates;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (!((Predicate) it.next()).test(value)) {
                return false;
            }
        }
        return true;
    }
}
