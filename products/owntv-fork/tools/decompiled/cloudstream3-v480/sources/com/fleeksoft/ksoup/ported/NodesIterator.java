package com.fleeksoft.ksoup.ported;

import androidx.exifinterface.media.ExifInterface;
import com.fleeksoft.ksoup.nodes.Node;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;

/* JADX INFO: compiled from: CustomMutableMapIterator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010)\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\b\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\tJ\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\n\u0010\f\u001a\u00020\rH\u0096\u0083\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/ported/NodesIterator;", ExifInterface.GPS_DIRECTION_TRUE, "", "iterator", "<init>", "(Ljava/util/Iterator;)V", "currentEntry", "Ljava/lang/Object;", "next", "()Ljava/lang/Object;", "remove", "", "hasNext", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NodesIterator<T> implements Iterator<T>, KMutableIterator {
    private T currentEntry;
    private final Iterator<T> iterator;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NodesIterator(Iterator<? extends T> iterator) {
        Intrinsics.checkNotNullParameter(iterator, "iterator");
        this.iterator = iterator;
    }

    @Override // java.util.Iterator
    public T next() {
        T next = this.iterator.next();
        this.currentEntry = next;
        Intrinsics.checkNotNull(next);
        return next;
    }

    @Override // java.util.Iterator
    public void remove() {
        this.iterator.remove();
        T t = this.currentEntry;
        if (t instanceof Node) {
            Node node = t instanceof Node ? (Node) t : null;
            if (node != null) {
                node.remove();
            }
        }
    }
}
