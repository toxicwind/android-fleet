package kotlin.reflect.jvm.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.full.IllegalCallableAccessException;
import kotlin.reflect.jvm.KTypesJvm;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.ValueClassAwareCallerKt;

/* JADX INFO: compiled from: ReflectKCallable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\b\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\u00052\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0002\u001a\u001d\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000b*\u0006\u0012\u0002\b\u00030\u0002H\u0000¢\u0006\u0002\u0010\f\u001aA\u0010\r\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00102\f\u0010\u0012\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0013H\u0000¢\u0006\u0002\u0010\u0014\u001a3\u0010\u0015\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0010H\u0000¢\u0006\u0002\u0010\u0016\u001a\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u0002\"\u001c\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u0006\u0012\u0002\b\u00030\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\"\u001c\u0010\u001a\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0003\"\u001c\u0010\u001b\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0003¨\u0006\u001c"}, d2 = {"isBound", "", "Lkotlin/reflect/jvm/internal/ReflectKCallable;", "(Lkotlin/reflect/jvm/internal/ReflectKCallable;)Z", "boundReceiver", "", "getBoundReceiver", "(Lkotlin/reflect/jvm/internal/ReflectKCallable;)Ljava/lang/Object;", "coerceToExpectedReceiverType", "callable", "computeAbsentArguments", "", "(Lkotlin/reflect/jvm/internal/ReflectKCallable;)[Ljava/lang/Object;", "callDefaultMethod", "R", "args", "", "Lkotlin/reflect/KParameter;", "continuationArgument", "Lkotlin/coroutines/Continuation;", "(Lkotlin/reflect/jvm/internal/ReflectKCallable;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callAnnotationConstructor", "(Lkotlin/reflect/jvm/internal/ReflectKCallable;Ljava/util/Map;)Ljava/lang/Object;", "defaultEmptyArray", "type", "Lkotlin/reflect/KType;", "isConstructor", "isAnnotationConstructor", "kotlin-reflection"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ReflectKCallableKt {
    public static final boolean isBound(ReflectKCallable<?> reflectKCallable) {
        Intrinsics.checkNotNullParameter(reflectKCallable, "<this>");
        return reflectKCallable.getRawBoundReceiver() != CallableReference.NO_RECEIVER;
    }

    public static final Object getBoundReceiver(ReflectKCallable<?> reflectKCallable) {
        Intrinsics.checkNotNullParameter(reflectKCallable, "<this>");
        return coerceToExpectedReceiverType(reflectKCallable.getRawBoundReceiver(), reflectKCallable);
    }

    private static final Object coerceToExpectedReceiverType(Object obj, ReflectKCallable<?> reflectKCallable) {
        Class<?> inlineClass;
        Method inlineClassUnboxMethod;
        if ((reflectKCallable instanceof ReflectKProperty) && ValueClassAwareCallerKt.isUnderlyingPropertyOfValueClass((ReflectKProperty) reflectKCallable)) {
            return obj;
        }
        Iterator<T> it = reflectKCallable.getAllParameters().iterator();
        boolean z = false;
        Object obj2 = null;
        while (true) {
            if (!it.hasNext()) {
                if (!z) {
                    break;
                }
                break;
            }
            Object next = it.next();
            if (((KParameter) next).getKind() != KParameter.Kind.VALUE) {
                if (!z) {
                    z = true;
                    obj2 = next;
                }
            }
            obj2 = null;
            break;
        }
        KParameter kParameter = (KParameter) obj2;
        KType type = kParameter != null ? kParameter.getType() : null;
        return (type == null || (inlineClass = ValueClassAwareCallerKt.toInlineClass(type)) == null || (inlineClassUnboxMethod = ValueClassAwareCallerKt.getInlineClassUnboxMethod(inlineClass, reflectKCallable)) == null) ? obj : inlineClassUnboxMethod.invoke(obj, null);
    }

    public static final Object[] computeAbsentArguments(ReflectKCallable<?> reflectKCallable) {
        int i;
        Intrinsics.checkNotNullParameter(reflectKCallable, "<this>");
        List<KParameter> parameters = reflectKCallable.getParameters();
        int size = parameters.size() + (reflectKCallable.isSuspend() ? 1 : 0);
        List<KParameter> list = parameters;
        if ((list instanceof Collection) && list.isEmpty()) {
            i = 0;
        } else {
            i = 0;
            for (KParameter kParameter : list) {
                if (kParameter.getKind() == KParameter.Kind.VALUE || kParameter.getKind() == KParameter.Kind.CONTEXT) {
                    i++;
                    if (i < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
        }
        int i2 = (i + 31) / 32;
        Object[] objArr = new Object[size + i2 + 1];
        for (KParameter kParameter2 : list) {
            if (kParameter2.isOptional() && !UtilKt.isInlineClassType(kParameter2.getType())) {
                objArr[kParameter2.getIndex()] = UtilKt.defaultPrimitiveValue(ReflectJvmMapping.getJavaType(kParameter2.getType()));
            } else if (kParameter2.isVararg()) {
                objArr[kParameter2.getIndex()] = defaultEmptyArray(kParameter2.getType());
            }
        }
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[size + i3] = 0;
        }
        return objArr;
    }

    public static final <R> R callDefaultMethod(ReflectKCallable<? extends R> reflectKCallable, Map<KParameter, ? extends Object> args, Continuation<?> continuation) throws IllegalCallableAccessException {
        Intrinsics.checkNotNullParameter(reflectKCallable, "<this>");
        Intrinsics.checkNotNullParameter(args, "args");
        List<KParameter> parameters = reflectKCallable.getParameters();
        boolean z = false;
        if (!parameters.isEmpty()) {
            int size = parameters.size() + (reflectKCallable.isSuspend() ? 1 : 0);
            Object[] absentArguments = reflectKCallable.getAbsentArguments();
            if (reflectKCallable.isSuspend()) {
                absentArguments[parameters.size()] = continuation;
            }
            int i = 0;
            for (KParameter kParameter : parameters) {
                if (args.containsKey(kParameter)) {
                    absentArguments[kParameter.getIndex()] = args.get(kParameter);
                } else if (kParameter.isOptional()) {
                    int i2 = (i / 32) + size;
                    Object obj = absentArguments[i2];
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
                    absentArguments[i2] = Integer.valueOf(((Integer) obj).intValue() | (1 << (i % 32)));
                    z = true;
                } else if (!kParameter.isVararg()) {
                    throw new IllegalArgumentException("No argument provided for a required parameter: " + kParameter);
                }
                if (kParameter.getKind() == KParameter.Kind.VALUE || kParameter.getKind() == KParameter.Kind.CONTEXT) {
                    i++;
                }
            }
            if (!z) {
                try {
                    Caller<?> caller = reflectKCallable.getCaller();
                    Object[] objArrCopyOf = Arrays.copyOf(absentArguments, size);
                    Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
                    return (R) caller.call(objArrCopyOf);
                } catch (IllegalAccessException e) {
                    throw new IllegalCallableAccessException(e);
                }
            }
            Caller<?> defaultCaller = reflectKCallable.getDefaultCaller();
            if (defaultCaller == null) {
                throw new KotlinReflectionInternalError("This callable does not support a default call: " + reflectKCallable);
            }
            try {
                return (R) defaultCaller.call(absentArguments);
            } catch (IllegalAccessException e2) {
                throw new IllegalCallableAccessException(e2);
            }
        }
        try {
            return (R) reflectKCallable.getCaller().call(reflectKCallable.isSuspend() ? new Continuation[]{continuation} : new Continuation[0]);
        } catch (IllegalAccessException e3) {
            throw new IllegalCallableAccessException(e3);
        }
    }

    public static final <R> R callAnnotationConstructor(ReflectKCallable<? extends R> reflectKCallable, Map<KParameter, ? extends Object> args) throws IllegalCallableAccessException {
        Object objDefaultEmptyArray;
        Intrinsics.checkNotNullParameter(reflectKCallable, "<this>");
        Intrinsics.checkNotNullParameter(args, "args");
        List<KParameter> parameters = reflectKCallable.getParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
        for (KParameter kParameter : parameters) {
            if (args.containsKey(kParameter)) {
                objDefaultEmptyArray = args.get(kParameter);
                if (objDefaultEmptyArray == null) {
                    throw new IllegalArgumentException("Annotation argument value cannot be null (" + kParameter + ')');
                }
            } else if (kParameter.isOptional()) {
                objDefaultEmptyArray = null;
            } else {
                if (!kParameter.isVararg()) {
                    throw new IllegalArgumentException("No argument provided for a required parameter: " + kParameter);
                }
                objDefaultEmptyArray = defaultEmptyArray(kParameter.getType());
            }
            arrayList.add(objDefaultEmptyArray);
        }
        ArrayList arrayList2 = arrayList;
        Caller<?> defaultCaller = reflectKCallable.getDefaultCaller();
        if (defaultCaller == null) {
            throw new KotlinReflectionInternalError("This callable does not support a default call: " + reflectKCallable);
        }
        try {
            return (R) defaultCaller.call(arrayList2.toArray(new Object[0]));
        } catch (IllegalAccessException e) {
            throw new IllegalCallableAccessException(e);
        }
    }

    private static final Object defaultEmptyArray(KType kType) {
        Class javaClass = JvmClassMappingKt.getJavaClass((KClass) KTypesJvm.getJvmErasure(kType));
        if (javaClass.isArray()) {
            Object objNewInstance = Array.newInstance(javaClass.getComponentType(), 0);
            Intrinsics.checkNotNullExpressionValue(objNewInstance, "run(...)");
            return objNewInstance;
        }
        throw new KotlinReflectionInternalError("Cannot instantiate the default empty array of type " + javaClass.getSimpleName() + ", because it is not an array type");
    }

    public static final boolean isConstructor(ReflectKCallable<?> reflectKCallable) {
        Intrinsics.checkNotNullParameter(reflectKCallable, "<this>");
        return Intrinsics.areEqual(reflectKCallable.getName(), "<init>");
    }

    public static final boolean isAnnotationConstructor(ReflectKCallable<?> reflectKCallable) {
        Intrinsics.checkNotNullParameter(reflectKCallable, "<this>");
        return isConstructor(reflectKCallable) && reflectKCallable.getContainer().getJClass().isAnnotation();
    }
}
