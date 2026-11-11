package kotlinx.datetime.internal.format;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: Add missing generic type declarations: [T, E] */
/* JADX INFO: compiled from: FormatStructure.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
public /* synthetic */ class OptionalFormatStructure$PropertyWithDefault$isDefaultComparisonPredicate$1<E, T> extends FunctionReferenceImpl implements Function1<T, E> {
    public OptionalFormatStructure$PropertyWithDefault$isDefaultComparisonPredicate$1(Object obj) {
        super(1, obj, Accessor.class, "getter", "getter(Ljava/lang/Object;)Ljava/lang/Object;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final E invoke(T t) {
        return (E) ((Accessor) this.receiver).getter(t);
    }
}
