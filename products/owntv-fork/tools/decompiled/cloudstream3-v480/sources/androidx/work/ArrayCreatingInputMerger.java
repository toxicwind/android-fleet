package androidx.work;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArrayCreatingInputMerger.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0002J&\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0002J\u001e\u0010\u0011\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0002¨\u0006\u0012"}, d2 = {"Landroidx/work/ArrayCreatingInputMerger;", "Landroidx/work/InputMerger;", "<init>", "()V", "merge", "Landroidx/work/Data;", "inputs", "", "concatenateArrays", "", "array1", "array2", "concatenateArrayAndNonArray", "array", "obj", "valueClass", "Ljava/lang/Class;", "createArrayFor", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ArrayCreatingInputMerger extends InputMerger {
    @Override // androidx.work.InputMerger
    public Data merge(List<Data> inputs) {
        Class<?> cls;
        Intrinsics.checkNotNullParameter(inputs, "inputs");
        Data.Builder builder = new Data.Builder();
        HashMap map = new HashMap();
        Iterator<Data> it = inputs.iterator();
        while (it.hasNext()) {
            for (Map.Entry<String, Object> entry : it.next().getKeyValueMap().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value == null || (cls = value.getClass()) == null) {
                    cls = String.class;
                }
                Object obj = map.get(key);
                if (obj == null) {
                    if (cls.isArray()) {
                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Any");
                    } else {
                        value = createArrayFor(value, cls);
                    }
                } else {
                    Class<?> cls2 = obj.getClass();
                    if (Intrinsics.areEqual(cls2, cls)) {
                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Any");
                        value = concatenateArrays(obj, value);
                    } else if (Intrinsics.areEqual(cls2.getComponentType(), cls)) {
                        value = concatenateArrayAndNonArray(obj, value, cls);
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                map.put(key, value);
            }
        }
        builder.putAll(map);
        return builder.build();
    }

    private final Object concatenateArrays(Object array1, Object array2) {
        int length = Array.getLength(array1);
        int length2 = Array.getLength(array2);
        Class<?> componentType = array1.getClass().getComponentType();
        Intrinsics.checkNotNull(componentType);
        Object objNewInstance = Array.newInstance(componentType, length + length2);
        System.arraycopy(array1, 0, objNewInstance, 0, length);
        System.arraycopy(array2, 0, objNewInstance, length, length2);
        Intrinsics.checkNotNull(objNewInstance);
        return objNewInstance;
    }

    private final Object concatenateArrayAndNonArray(Object array, Object obj, Class<?> valueClass) {
        int length = Array.getLength(array);
        Object objNewInstance = Array.newInstance(valueClass, length + 1);
        System.arraycopy(array, 0, objNewInstance, 0, length);
        Array.set(objNewInstance, length, obj);
        Intrinsics.checkNotNull(objNewInstance);
        return objNewInstance;
    }

    private final Object createArrayFor(Object obj, Class<?> valueClass) {
        Object objNewInstance = Array.newInstance(valueClass, 1);
        Array.set(objNewInstance, 0, obj);
        Intrinsics.checkNotNull(objNewInstance);
        return objNewInstance;
    }
}
