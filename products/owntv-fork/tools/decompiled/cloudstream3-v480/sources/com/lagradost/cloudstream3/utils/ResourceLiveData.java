package com.lagradost.cloudstream3.utils;

import androidx.exifinterface.media.ExifInterface;
import com.lagradost.cloudstream3.mvvm.Resource;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ConsistentLiveData.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0019\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R(\u0010\b\u001a\u0004\u0018\u00018\u00002\b\u0010\u0007\u001a\u0004\u0018\u00018\u00008F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ResourceLiveData;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/lagradost/cloudstream3/utils/ConsistentLiveData;", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "initValue", "<init>", "(Lcom/lagradost/cloudstream3/mvvm/Resource;)V", ES6Iterator.VALUE_PROPERTY, "success", "getSuccess", "()Ljava/lang/Object;", "setSuccess", "(Ljava/lang/Object;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ResourceLiveData<T> extends ConsistentLiveData<Resource<? extends T>> {
    /* JADX WARN: Multi-variable type inference failed */
    public ResourceLiveData() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ResourceLiveData(Resource<? extends T> resource) {
        super(resource);
    }

    public /* synthetic */ ResourceLiveData(Resource resource, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : resource);
    }

    public final T getSuccess() {
        Resource resource = (Resource) getValue();
        if (resource instanceof Resource.Success) {
            return (T) ((Resource.Success) resource).getValue();
        }
        return null;
    }

    public final void setSuccess(T t) {
        postValue(t != null ? new Resource.Success(t) : null);
    }
}
