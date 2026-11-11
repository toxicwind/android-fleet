package kotlin.reflect.full;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.jvm.internal.KClassImpl;
import kotlin.reflect.jvm.internal.SystemPropertiesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.types.CapturedKTypeKt;
import kotlin.reflect.jvm.internal.types.SimpleKType;

/* JADX INFO: compiled from: KClassifiers.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u001a6\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004H\u0007\u001aF\u0010\n\u001a\u00020\u0001*\u00020\u00022\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\fH\u0000\u001a\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0000\"\u001e\u0010\u0012\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"createType", "Lkotlin/reflect/KType;", "Lkotlin/reflect/KClassifier;", "arguments", "", "Lkotlin/reflect/KTypeProjection;", "nullable", "", "annotations", "", "createTypeImpl", "mutableCollectionClass", "Lkotlin/reflect/KClass;", "checkArgumentsSize", "", "parametersSize", "", "argumentsSize", "starProjectedType", "getStarProjectedType$annotations", "(Lkotlin/reflect/KClassifier;)V", "getStarProjectedType", "(Lkotlin/reflect/KClassifier;)Lkotlin/reflect/KType;", "kotlin-reflection"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class KClassifiers {
    public static /* synthetic */ void getStarProjectedType$annotations(KClassifier kClassifier) {
    }

    public static /* synthetic */ KType createType$default(KClassifier kClassifier, List list, boolean z, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        return createType(kClassifier, list, z, list2);
    }

    public static final KType createType(KClassifier kClassifier, List<KTypeProjection> arguments, boolean z, List<? extends Annotation> annotations) {
        Intrinsics.checkNotNullParameter(kClassifier, "<this>");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        return createTypeImpl$default(kClassifier, arguments, z, annotations, null, 8, null);
    }

    public static /* synthetic */ KType createTypeImpl$default(KClassifier kClassifier, List list, boolean z, List list2, KClass kClass, int i, Object obj) {
        if ((i & 1) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        if ((i & 8) != 0) {
            kClass = null;
        }
        return createTypeImpl(kClassifier, list, z, list2, kClass);
    }

    public static final KType createTypeImpl(KClassifier kClassifier, List<KTypeProjection> arguments, boolean z, List<? extends Annotation> annotations, KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClassifier, "<this>");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        if (SystemPropertiesKt.getUseK1Implementation()) {
            return K1ImplementationKt.createK1KType(kClassifier, arguments, z);
        }
        KClass kClass2 = kClassifier instanceof KClass ? (KClass) kClassifier : null;
        List<KTypeParameter> listAllTypeParameters = kClass2 != null ? CapturedKTypeKt.allTypeParameters(kClass2) : null;
        if (listAllTypeParameters == null) {
            listAllTypeParameters = CollectionsKt.emptyList();
        }
        checkArgumentsSize(listAllTypeParameters.size(), arguments.size());
        return new SimpleKType(kClassifier, arguments, z, annotations, null, false, false, false, kClass, null, 512, null);
    }

    public static final void checkArgumentsSize(int i, int i2) {
        if (i == i2) {
            return;
        }
        throw new IllegalArgumentException("Class declares " + i + " type parameters, but " + i2 + " were provided.");
    }

    public static final KType getStarProjectedType(KClassifier kClassifier) {
        ClassDescriptor descriptor;
        Intrinsics.checkNotNullParameter(kClassifier, "<this>");
        KClassImpl kClassImpl = kClassifier instanceof KClassImpl ? (KClassImpl) kClassifier : null;
        if (kClassImpl == null || (descriptor = kClassImpl.getDescriptor()) == null) {
            return createType$default(kClassifier, null, false, null, 7, null);
        }
        List<TypeParameterDescriptor> parameters = descriptor.getTypeConstructor().getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "getParameters(...)");
        if (parameters.isEmpty()) {
            return createType$default(kClassifier, null, false, null, 7, null);
        }
        List<TypeParameterDescriptor> list = parameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (TypeParameterDescriptor typeParameterDescriptor : list) {
            arrayList.add(KTypeProjection.INSTANCE.getSTAR());
        }
        return createType$default(kClassifier, arrayList, false, null, 6, null);
    }
}
