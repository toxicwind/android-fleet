package com.google.android.gms.common.data;

import b.a.a.a.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class DataBufferIterator<T> implements Iterator<T> {
    public final DataBuffer<T> zaln;
    public int zalo;

    public DataBufferIterator(DataBuffer<T> dataBuffer) {
        Preconditions.checkNotNull(dataBuffer);
        this.zaln = dataBuffer;
        this.zalo = -1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.zalo < this.zaln.getCount() - 1;
    }

    @Override // java.util.Iterator
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException(a.a(46, "Cannot advance the iterator beyond ", this.zalo));
        }
        DataBuffer<T> dataBuffer = this.zaln;
        int i = this.zalo + 1;
        this.zalo = i;
        return dataBuffer.get(i);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
