package com.lagradost.cloudstream3.utils;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ConsistentLiveData.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\b\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\tJ\u0017\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u0005J\u0017\u0010\u000f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00018\u0000H\u0017¢\u0006\u0002\u0010\u0005R\u0012\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u0013\u0010\n\u001a\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ConsistentLiveData;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/LiveData;", "initValue", "<init>", "(Ljava/lang/Object;)V", "internalValue", "Ljava/lang/Object;", "getValue", "()Ljava/lang/Object;", "postedValue", "getPostedValue", "postValue", "", ES6Iterator.VALUE_PROPERTY, "setValue", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class ConsistentLiveData<T> extends LiveData<T> {
    private volatile T internalValue;

    /* JADX WARN: Illegal instructions before constructor call */
    public ConsistentLiveData() {
        DefaultConstructorMarker defaultConstructorMarker = null;
        this(defaultConstructorMarker, 1, defaultConstructorMarker);
    }

    public ConsistentLiveData(T t) {
        super(t);
        this.internalValue = t;
    }

    public /* synthetic */ ConsistentLiveData(Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : obj);
    }

    @Override // androidx.lifecycle.LiveData
    public T getValue() {
        return this.internalValue;
    }

    public final T getPostedValue() {
        return (T) super.getValue();
    }

    @Override // androidx.lifecycle.LiveData
    public void postValue(T value) {
        super.postValue(value);
        this.internalValue = value;
    }

    @Override // androidx.lifecycle.LiveData
    public void setValue(T value) {
        super.setValue(value);
        this.internalValue = value;
    }
}
