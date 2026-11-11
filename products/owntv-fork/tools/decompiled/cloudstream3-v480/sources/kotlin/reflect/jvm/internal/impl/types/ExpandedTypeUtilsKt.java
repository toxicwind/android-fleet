package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSubstitutorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;

/* JADX INFO: compiled from: expandedTypeUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class ExpandedTypeUtilsKt {

    /* JADX INFO: compiled from: expandedTypeUtils.kt */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TypeVariance.values().length];
            try {
                iArr[TypeVariance.IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final KotlinTypeMarker computeExpandedTypeForInlineClass(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker inlineClassType) {
        Intrinsics.checkNotNullParameter(typeSystemCommonBackendContext, "<this>");
        Intrinsics.checkNotNullParameter(inlineClassType, "inlineClassType");
        return computeExpandedTypeInner(typeSystemCommonBackendContext, inlineClassType, new HashSet());
    }

    private static final KotlinTypeMarker computeExpandedTypeInner(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker, HashSet<TypeConstructorMarker> hashSet) {
        KotlinTypeMarker kotlinTypeMarkerComputeExpandedTypeInner;
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemCommonBackendContext.typeConstructor(kotlinTypeMarker);
        if (!hashSet.add(typeConstructorMarkerTypeConstructor)) {
            return null;
        }
        TypeParameterMarker typeParameterClassifier = typeSystemCommonBackendContext.getTypeParameterClassifier(typeConstructorMarkerTypeConstructor);
        if (typeParameterClassifier != null) {
            KotlinTypeMarker representativeUpperBound = typeSystemCommonBackendContext.getRepresentativeUpperBound(typeParameterClassifier);
            KotlinTypeMarker kotlinTypeMarkerComputeExpandedTypeInner2 = computeExpandedTypeInner(typeSystemCommonBackendContext, representativeUpperBound, hashSet);
            if (kotlinTypeMarkerComputeExpandedTypeInner2 == null) {
                return null;
            }
            boolean z = typeSystemCommonBackendContext.isInlineClass(typeSystemCommonBackendContext.typeConstructor(representativeUpperBound)) || ((representativeUpperBound instanceof SimpleTypeMarker) && typeSystemCommonBackendContext.isPrimitiveType((SimpleTypeMarker) representativeUpperBound));
            if ((kotlinTypeMarkerComputeExpandedTypeInner2 instanceof SimpleTypeMarker) && typeSystemCommonBackendContext.isPrimitiveType((SimpleTypeMarker) kotlinTypeMarkerComputeExpandedTypeInner2) && typeSystemCommonBackendContext.isNullableType(kotlinTypeMarker) && z) {
                return typeSystemCommonBackendContext.makeNullable(representativeUpperBound);
            }
            return (typeSystemCommonBackendContext.isNullableType(kotlinTypeMarkerComputeExpandedTypeInner2) || !typeSystemCommonBackendContext.isMarkedNullable(kotlinTypeMarker)) ? kotlinTypeMarkerComputeExpandedTypeInner2 : typeSystemCommonBackendContext.makeNullable(kotlinTypeMarkerComputeExpandedTypeInner2);
        }
        if (typeSystemCommonBackendContext.isInlineClass(typeConstructorMarkerTypeConstructor)) {
            KotlinTypeMarker substitutedUnderlyingType = getSubstitutedUnderlyingType(typeSystemCommonBackendContext, kotlinTypeMarker);
            if (substitutedUnderlyingType == null || (kotlinTypeMarkerComputeExpandedTypeInner = computeExpandedTypeInner(typeSystemCommonBackendContext, substitutedUnderlyingType, hashSet)) == null) {
                return null;
            }
            if (!typeSystemCommonBackendContext.isNullableType(kotlinTypeMarker)) {
                return kotlinTypeMarkerComputeExpandedTypeInner;
            }
            if (!typeSystemCommonBackendContext.isNullableType(kotlinTypeMarkerComputeExpandedTypeInner) && (!(kotlinTypeMarkerComputeExpandedTypeInner instanceof SimpleTypeMarker) || !typeSystemCommonBackendContext.isPrimitiveType((SimpleTypeMarker) kotlinTypeMarkerComputeExpandedTypeInner))) {
                return typeSystemCommonBackendContext.makeNullable(kotlinTypeMarkerComputeExpandedTypeInner);
            }
        }
        return kotlinTypeMarker;
    }

    private static final KotlinTypeMarker getSubstitutedUnderlyingType(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker) {
        List<TypeParameterMarker> parameters = typeSystemCommonBackendContext.getParameters(typeSystemCommonBackendContext.typeConstructor(kotlinTypeMarker));
        List<TypeArgumentMarker> arguments = typeSystemCommonBackendContext.getArguments(kotlinTypeMarker);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        int i = 0;
        for (Object obj : arguments) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KotlinTypeMarker type = typeSystemCommonBackendContext.getType((TypeArgumentMarker) obj);
            if (type == null) {
                type = typeSystemCommonBackendContext.getRepresentativeUpperBound(parameters.get(i));
            }
            arrayList.add(type);
            i = i2;
        }
        ArrayList arrayList2 = arrayList;
        List<TypeParameterMarker> list = parameters;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList3.add(typeSystemCommonBackendContext.getTypeConstructor((TypeParameterMarker) it.next()));
        }
        TypeSubstitutorMarker typeSubstitutorMarkerTypeSubstitutorForUnderlyingType = typeSystemCommonBackendContext.typeSubstitutorForUnderlyingType(MapsKt.toMap(CollectionsKt.zip(arrayList3, arrayList2)));
        KotlinTypeMarker unsubstitutedUnderlyingType = typeSystemCommonBackendContext.getUnsubstitutedUnderlyingType(kotlinTypeMarker);
        if (unsubstitutedUnderlyingType == null) {
            return null;
        }
        TypeParameterMarker typeParameterMarkerAsTypeParameterOrArrayThereof = asTypeParameterOrArrayThereof(typeSystemCommonBackendContext, unsubstitutedUnderlyingType);
        if (typeParameterMarkerAsTypeParameterOrArrayThereof == null) {
            return typeSystemCommonBackendContext.safeSubstitute(typeSubstitutorMarkerTypeSubstitutorForUnderlyingType, unsubstitutedUnderlyingType);
        }
        return substituteUpperBound(typeSystemCommonBackendContext, unsubstitutedUnderlyingType, typeSystemCommonBackendContext.safeSubstitute(typeSubstitutorMarkerTypeSubstitutorForUnderlyingType, typeSystemCommonBackendContext.getRepresentativeUpperBound(typeParameterMarkerAsTypeParameterOrArrayThereof)));
    }

    private static final TypeParameterMarker asTypeParameter(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker) {
        return typeSystemCommonBackendContext.getTypeParameterClassifier(typeSystemCommonBackendContext.typeConstructor(kotlinTypeMarker));
    }

    private static final TypeParameterMarker asTypeParameterOrArrayThereof(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker) {
        KotlinTypeMarker type;
        TypeParameterMarker typeParameterMarkerAsTypeParameter = asTypeParameter(typeSystemCommonBackendContext, kotlinTypeMarker);
        if (typeParameterMarkerAsTypeParameter != null) {
            return typeParameterMarkerAsTypeParameter;
        }
        if (typeSystemCommonBackendContext.isArrayOrNullableArray(kotlinTypeMarker) && (type = typeSystemCommonBackendContext.getType((TypeArgumentMarker) CollectionsKt.single((List) typeSystemCommonBackendContext.getArguments(kotlinTypeMarker)))) != null) {
            return asTypeParameterOrArrayThereof(typeSystemCommonBackendContext, type);
        }
        return null;
    }

    private static final KotlinTypeMarker substituteUpperBound(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2) {
        SimpleTypeMarker simpleTypeMarkerSubstituteUpperBound;
        if (asTypeParameter(typeSystemCommonBackendContext, kotlinTypeMarker) != null) {
            return typeSystemCommonBackendContext.isNullableType(kotlinTypeMarker) ? typeSystemCommonBackendContext.makeNullable(kotlinTypeMarker2) : kotlinTypeMarker2;
        }
        TypeArgumentMarker typeArgumentMarker = (TypeArgumentMarker) CollectionsKt.single((List) typeSystemCommonBackendContext.getArguments(kotlinTypeMarker));
        if (WhenMappings.$EnumSwitchMapping$0[typeSystemCommonBackendContext.getVariance(typeArgumentMarker).ordinal()] == 1) {
            simpleTypeMarkerSubstituteUpperBound = typeSystemCommonBackendContext.nullableAnyType();
        } else {
            KotlinTypeMarker type = typeSystemCommonBackendContext.getType(typeArgumentMarker);
            Intrinsics.checkNotNull(type);
            simpleTypeMarkerSubstituteUpperBound = substituteUpperBound(typeSystemCommonBackendContext, type, kotlinTypeMarker2);
        }
        SimpleTypeMarker simpleTypeMarkerArrayType = typeSystemCommonBackendContext.arrayType(simpleTypeMarkerSubstituteUpperBound);
        return typeSystemCommonBackendContext.isNullableType(kotlinTypeMarker) ? typeSystemCommonBackendContext.makeNullable(simpleTypeMarkerArrayType) : simpleTypeMarkerArrayType;
    }
}
