package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: Iterators.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FloatIterator implements Iterator<Float>, KMappedMarker {
    public abstract float nextFloat();

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    @NotNull
    public final Float next() {
        return Float.valueOf(nextFloat());
    }
}
