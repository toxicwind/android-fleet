package com.google.android.gms.common.data;

import b.a.a.a.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class SingleRefDataBufferIterator<T> extends DataBufferIterator<T> {
    public T zams;

    public SingleRefDataBufferIterator(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    @Override // com.google.android.gms.common.data.DataBufferIterator, java.util.Iterator
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException(a.a(46, "Cannot advance the iterator beyond ", this.zalo));
        }
        this.zalo++;
        int i = this.zalo;
        if (i == 0) {
            this.zams = this.zaln.get(0);
            T t = this.zams;
            if (!(t instanceof DataBufferRef)) {
                String strValueOf = String.valueOf(t.getClass());
                throw new IllegalStateException(a.a(strValueOf.length() + 44, "DataBuffer reference of type ", strValueOf, " is not movable"));
            }
        } else {
            ((DataBufferRef) this.zams).zag(i);
        }
        return this.zams;
    }
}
