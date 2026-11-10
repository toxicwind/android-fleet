package com.ju.lib.utils.base.delegate;

/* JADX INFO: loaded from: classes2.dex */
public class DataDelegate<T> implements IDataSource<T> {
    public T mData;

    @Override // com.ju.lib.utils.base.delegate.IDataSource
    public T getData() {
        return this.mData;
    }

    public void updateData(T t) {
        this.mData = t;
    }
}
