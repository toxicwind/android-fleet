package org.mozilla.javascript;

import j$.util.function.Function$CC;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: classes8.dex */
public class JavaToJSONConverters {
    public static final UnaryOperator<Object> STRING = new UnaryOperator() { // from class: org.mozilla.javascript.JavaToJSONConverters$$ExternalSyntheticLambda0
        @Override // java.util.function.Function
        public /* synthetic */ java.util.function.Function andThen(java.util.function.Function function) {
            return Function$CC.$default$andThen(this, function);
        }

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return obj.toString();
        }

        public /* synthetic */ java.util.function.Function compose(java.util.function.Function function) {
            return Function$CC.$default$compose(this, function);
        }
    };
    public static final UnaryOperator<Object> UNDEFINED = new UnaryOperator() { // from class: org.mozilla.javascript.JavaToJSONConverters$$ExternalSyntheticLambda1
        @Override // java.util.function.Function
        public /* synthetic */ java.util.function.Function andThen(java.util.function.Function function) {
            return Function$CC.$default$andThen(this, function);
        }

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Undefined.instance;
        }

        public /* synthetic */ java.util.function.Function compose(java.util.function.Function function) {
            return Function$CC.$default$compose(this, function);
        }
    };
    public static final UnaryOperator<Object> EMPTY_OBJECT = new UnaryOperator() { // from class: org.mozilla.javascript.JavaToJSONConverters$$ExternalSyntheticLambda2
        @Override // java.util.function.Function
        public /* synthetic */ java.util.function.Function andThen(java.util.function.Function function) {
            return Function$CC.$default$andThen(this, function);
        }

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Collections.EMPTY_MAP;
        }

        public /* synthetic */ java.util.function.Function compose(java.util.function.Function function) {
            return Function$CC.$default$compose(this, function);
        }
    };
    public static final UnaryOperator<Object> THROW_TYPE_ERROR = new UnaryOperator() { // from class: org.mozilla.javascript.JavaToJSONConverters$$ExternalSyntheticLambda3
        @Override // java.util.function.Function
        public /* synthetic */ java.util.function.Function andThen(java.util.function.Function function) {
            return Function$CC.$default$andThen(this, function);
        }

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return JavaToJSONConverters.lambda$static$3(obj);
        }

        public /* synthetic */ java.util.function.Function compose(java.util.function.Function function) {
            return Function$CC.$default$compose(this, function);
        }
    };
    public static final UnaryOperator<Object> BEAN = new UnaryOperator() { // from class: org.mozilla.javascript.JavaToJSONConverters$$ExternalSyntheticLambda4
        @Override // java.util.function.Function
        public /* synthetic */ java.util.function.Function andThen(java.util.function.Function function) {
            return Function$CC.$default$andThen(this, function);
        }

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return JavaToJSONConverters.lambda$static$4(obj);
        }

        public /* synthetic */ java.util.function.Function compose(java.util.function.Function function) {
            return Function$CC.$default$compose(this, function);
        }
    };

    private JavaToJSONConverters() {
    }

    static /* synthetic */ Object lambda$static$3(Object obj) {
        throw ScriptRuntime.typeErrorById("msg.json.cant.serialize", obj.getClass().getName());
    }

    static /* synthetic */ Object lambda$static$4(Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                if (propertyDescriptor.getReadMethod() != null) {
                    try {
                        linkedHashMap.put(propertyDescriptor.getName(), propertyDescriptor.getReadMethod().invoke(obj, null));
                    } catch (Exception unused) {
                    }
                }
            }
            if (linkedHashMap.size() == 0) {
                return null;
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.put("beanClass", beanInfo.getBeanDescriptor().getBeanClass().getName());
            linkedHashMap2.put("properties", linkedHashMap);
            return linkedHashMap2;
        } catch (IntrospectionException unused2) {
            return null;
        }
    }
}
