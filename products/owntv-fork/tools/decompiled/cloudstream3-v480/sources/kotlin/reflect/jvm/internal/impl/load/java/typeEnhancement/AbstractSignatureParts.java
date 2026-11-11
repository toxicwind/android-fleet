package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationQualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;

/* JADX INFO: compiled from: AbstractSignatureParts.kt */
/* JADX INFO: loaded from: classes7.dex */
public abstract class AbstractSignatureParts<TAnnotation> {
    public abstract boolean forceWarning(TAnnotation tannotation, KotlinTypeMarker kotlinTypeMarker);

    public abstract AbstractAnnotationTypeQualifierResolver<TAnnotation> getAnnotationTypeQualifierResolver();

    public abstract Iterable<TAnnotation> getAnnotations(KotlinTypeMarker kotlinTypeMarker);

    public abstract Iterable<TAnnotation> getContainerAnnotations();

    public abstract AnnotationQualifierApplicabilityType getContainerApplicabilityType();

    public abstract JavaTypeQualifiersByElementType getContainerDefaultTypeQualifiers();

    public abstract boolean getContainerIsVarargParameter();

    protected abstract WithMigrationStatus<NullabilityQualifier> getDefaultNullability(WithMigrationStatus<NullabilityQualifier> withMigrationStatus, JavaDefaultQualifiers javaDefaultQualifiers);

    public abstract boolean getEnableImprovementsInStrictMode();

    public abstract KotlinTypeMarker getEnhancedForWarnings(KotlinTypeMarker kotlinTypeMarker);

    public boolean getForceOnlyHeadTypeConstructor() {
        return false;
    }

    public abstract FqNameUnsafe getFqNameUnsafe(KotlinTypeMarker kotlinTypeMarker);

    public boolean getShouldPropagateBoundNullness(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return true;
    }

    public abstract boolean getSkipRawTypeArguments();

    public abstract TypeSystemContext getTypeSystem();

    public abstract boolean isArrayOrPrimitiveArray(KotlinTypeMarker kotlinTypeMarker);

    public abstract boolean isCovariant();

    public abstract boolean isEqual(KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2);

    public abstract boolean isFromJava(TypeParameterMarker typeParameterMarker);

    public abstract boolean isK2();

    public boolean isNotNullTypeParameterCompat(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return false;
    }

    private final NullabilityQualifier getNullabilityQualifier(KotlinTypeMarker kotlinTypeMarker) {
        TypeSystemContext typeSystem = getTypeSystem();
        if (typeSystem.isMarkedNullable(typeSystem.lowerBoundIfFlexible(kotlinTypeMarker))) {
            return NullabilityQualifier.NULLABLE;
        }
        if (typeSystem.isMarkedNullable(typeSystem.upperBoundIfFlexible(kotlinTypeMarker))) {
            return null;
        }
        return NullabilityQualifier.NOT_NULL;
    }

    private final MutabilityQualifier getMutabilityQualifier(KotlinTypeMarker kotlinTypeMarker) {
        TypeSystemContext typeSystem = getTypeSystem();
        if (JavaToKotlinClassMap.INSTANCE.isReadOnly(getFqNameUnsafe(typeSystem.lowerBoundIfFlexible(kotlinTypeMarker)))) {
            return MutabilityQualifier.READ_ONLY;
        }
        if (JavaToKotlinClassMap.INSTANCE.isMutable(getFqNameUnsafe(typeSystem.upperBoundIfFlexible(kotlinTypeMarker)))) {
            return MutabilityQualifier.MUTABLE;
        }
        return null;
    }

    private final JavaTypeQualifiers extractQualifiers(KotlinTypeMarker kotlinTypeMarker) {
        NullabilityQualifier nullabilityQualifier;
        boolean z;
        NullabilityQualifier nullabilityQualifier2 = getNullabilityQualifier(kotlinTypeMarker);
        MutabilityQualifier mutabilityQualifier = null;
        if (nullabilityQualifier2 == null) {
            KotlinTypeMarker enhancedForWarnings = getEnhancedForWarnings(kotlinTypeMarker);
            nullabilityQualifier = enhancedForWarnings != null ? getNullabilityQualifier(enhancedForWarnings) : null;
        } else {
            nullabilityQualifier = nullabilityQualifier2;
        }
        MutabilityQualifier mutabilityQualifier2 = getMutabilityQualifier(kotlinTypeMarker);
        MutabilityQualifier mutabilityQualifier3 = getMutabilityQualifier(kotlinTypeMarker);
        if (mutabilityQualifier3 == null) {
            KotlinTypeMarker enhancedForWarnings2 = getEnhancedForWarnings(kotlinTypeMarker);
            if (enhancedForWarnings2 != null) {
                mutabilityQualifier = getMutabilityQualifier(enhancedForWarnings2);
            }
        } else {
            mutabilityQualifier = mutabilityQualifier3;
        }
        boolean z2 = true;
        if (getTypeSystem().isDefinitelyNotNullType(kotlinTypeMarker) || isNotNullTypeParameterCompat(kotlinTypeMarker)) {
            z = false;
        } else {
            z = false;
            z2 = false;
        }
        return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier2, z2, nullabilityQualifier != nullabilityQualifier2 ? true : z, mutabilityQualifier != mutabilityQualifier2 ? z2 : z);
    }

    private final JavaTypeQualifiers extractQualifiersFromAnnotations(final TypeAndDefaultQualifiers typeAndDefaultQualifiers, JavaDefaultQualifiers javaDefaultQualifiers) {
        List listEmptyList;
        WithMigrationStatus<NullabilityQualifier> boundsNullability;
        KotlinTypeMarker type;
        TypeConstructorMarker typeConstructorMarkerTypeConstructor;
        if (typeAndDefaultQualifiers.getType() == null) {
            TypeSystemContext typeSystem = getTypeSystem();
            TypeParameterMarker typeParameterForArgument = typeAndDefaultQualifiers.getTypeParameterForArgument();
            if ((typeParameterForArgument != null ? typeSystem.getVariance(typeParameterForArgument) : null) == TypeVariance.IN) {
                return JavaTypeQualifiers.Companion.getNONE();
            }
        }
        boolean z = typeAndDefaultQualifiers.getTypeParameterForArgument() == null;
        KotlinTypeMarker type2 = typeAndDefaultQualifiers.getType();
        if (type2 == null || (listEmptyList = getAnnotations(type2)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        TypeSystemContext typeSystem2 = getTypeSystem();
        KotlinTypeMarker type3 = typeAndDefaultQualifiers.getType();
        TypeParameterMarker typeParameterClassifier = (type3 == null || (typeConstructorMarkerTypeConstructor = typeSystem2.typeConstructor(type3)) == null) ? null : typeSystem2.getTypeParameterClassifier(typeConstructorMarkerTypeConstructor);
        boolean z2 = getContainerApplicabilityType() == AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS;
        if (z) {
            if (!z2 && getEnableImprovementsInStrictMode() && (type = typeAndDefaultQualifiers.getType()) != null && isArrayOrPrimitiveArray(type)) {
                Iterable<TAnnotation> containerAnnotations = getContainerAnnotations();
                ArrayList arrayList = new ArrayList();
                for (TAnnotation tannotation : containerAnnotations) {
                    if (!getAnnotationTypeQualifierResolver().isTypeUseAnnotation(tannotation)) {
                        arrayList.add(tannotation);
                    }
                }
                listEmptyList = CollectionsKt.plus((Collection) arrayList, (Iterable) listEmptyList);
            } else {
                listEmptyList = CollectionsKt.plus((Iterable) getContainerAnnotations(), (Iterable) listEmptyList);
            }
        }
        WithMigrationStatus<MutabilityQualifier> withMigrationStatusExtractMutability = getAnnotationTypeQualifierResolver().extractMutability((Iterable) listEmptyList);
        WithMigrationStatus<NullabilityQualifier> withMigrationStatusExtractNullability = getAnnotationTypeQualifierResolver().extractNullability((Iterable) listEmptyList, (Function1) new Function1(this, typeAndDefaultQualifiers) { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$$Lambda$0
            private final AbstractSignatureParts arg$0;
            private final AbstractSignatureParts.TypeAndDefaultQualifiers arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = typeAndDefaultQualifiers;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return Boolean.valueOf(AbstractSignatureParts.extractQualifiersFromAnnotations$lambda$3(this.arg$0, this.arg$1, obj));
            }
        });
        if (withMigrationStatusExtractNullability != null) {
            return new JavaTypeQualifiers(withMigrationStatusExtractNullability.getQualifier(), withMigrationStatusExtractMutability != null ? withMigrationStatusExtractMutability.getQualifier() : null, withMigrationStatusExtractNullability.getQualifier() == NullabilityQualifier.NOT_NULL && typeParameterClassifier != null, withMigrationStatusExtractNullability.isForWarningOnly(), withMigrationStatusExtractMutability != null && withMigrationStatusExtractMutability.isForWarningOnly());
        }
        WithMigrationStatus<NullabilityQualifier> boundsNullability2 = typeParameterClassifier != null ? getBoundsNullability(typeParameterClassifier) : null;
        WithMigrationStatus<NullabilityQualifier> defaultNullability = getDefaultNullability(boundsNullability2, javaDefaultQualifiers);
        boolean z3 = (boundsNullability2 != null ? boundsNullability2.getQualifier() : null) == NullabilityQualifier.NOT_NULL || !(typeParameterClassifier == null || javaDefaultQualifiers == null || !javaDefaultQualifiers.getDefinitelyNotNull());
        TypeParameterMarker typeParameterForArgument2 = typeAndDefaultQualifiers.getTypeParameterForArgument();
        if (typeParameterForArgument2 == null || (boundsNullability = getBoundsNullability(typeParameterForArgument2)) == null) {
            boundsNullability = null;
        } else if (boundsNullability.getQualifier() == NullabilityQualifier.NULLABLE) {
            boundsNullability = WithMigrationStatus.copy$default(boundsNullability, NullabilityQualifier.FORCE_FLEXIBILITY, false, 2, null);
        }
        WithMigrationStatus<NullabilityQualifier> withMigrationStatusMostSpecific = mostSpecific(boundsNullability, defaultNullability);
        return new JavaTypeQualifiers(withMigrationStatusMostSpecific != null ? withMigrationStatusMostSpecific.getQualifier() : null, withMigrationStatusExtractMutability != null ? withMigrationStatusExtractMutability.getQualifier() : null, z3, withMigrationStatusMostSpecific != null && withMigrationStatusMostSpecific.isForWarningOnly(), withMigrationStatusExtractMutability != null && withMigrationStatusExtractMutability.isForWarningOnly());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean extractQualifiersFromAnnotations$lambda$3(AbstractSignatureParts abstractSignatureParts, TypeAndDefaultQualifiers typeAndDefaultQualifiers, Object extractNullability) {
        Intrinsics.checkNotNullParameter(extractNullability, "$this$extractNullability");
        return abstractSignatureParts.forceWarning(extractNullability, typeAndDefaultQualifiers.getType());
    }

    private final JavaDefaultQualifiers extractDefaultQualifier(TypeAndDefaultQualifiers typeAndDefaultQualifiers) {
        AnnotationQualifierApplicabilityType containerApplicabilityType;
        boolean z = typeAndDefaultQualifiers.getTypeParameterForArgument() == null;
        boolean z2 = getContainerApplicabilityType() == AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS;
        if (z || z2) {
            containerApplicabilityType = getContainerApplicabilityType();
        } else {
            containerApplicabilityType = AnnotationQualifierApplicabilityType.TYPE_USE;
        }
        JavaTypeQualifiersByElementType defaultQualifiers = typeAndDefaultQualifiers.getDefaultQualifiers();
        if (defaultQualifiers != null) {
            return defaultQualifiers.get(containerApplicabilityType);
        }
        return null;
    }

    private final WithMigrationStatus<NullabilityQualifier> mostSpecific(WithMigrationStatus<NullabilityQualifier> withMigrationStatus, WithMigrationStatus<NullabilityQualifier> withMigrationStatus2) {
        return (withMigrationStatus != null && (withMigrationStatus2 == null || ((!withMigrationStatus.isForWarningOnly() || withMigrationStatus2.isForWarningOnly()) && ((!withMigrationStatus.isForWarningOnly() && withMigrationStatus2.isForWarningOnly()) || (withMigrationStatus.getQualifier().compareTo(withMigrationStatus2.getQualifier()) >= 0 && withMigrationStatus.getQualifier().compareTo(withMigrationStatus2.getQualifier()) > 0))))) ? withMigrationStatus : withMigrationStatus2;
    }

    private final WithMigrationStatus<NullabilityQualifier> getBoundsNullability(TypeParameterMarker typeParameterMarker) {
        List<KotlinTypeMarker> list_get_boundsNullability_$lambda$0$3;
        NullabilityQualifier nullabilityQualifier;
        TypeSystemContext typeSystem = getTypeSystem();
        if (!isFromJava(typeParameterMarker)) {
            return null;
        }
        final List<KotlinTypeMarker> upperBounds = typeSystem.getUpperBounds(typeParameterMarker);
        List<KotlinTypeMarker> list = upperBounds;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (!typeSystem.isError((KotlinTypeMarker) it.next())) {
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : list) {
                        if (getNullabilityQualifier((KotlinTypeMarker) obj) != null) {
                            arrayList.add(obj);
                        }
                    }
                    ArrayList arrayList2 = arrayList;
                    Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0(upperBounds, this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$$Lambda$1
                        private final List arg$0;
                        private final AbstractSignatureParts arg$1;

                        {
                            this.arg$0 = upperBounds;
                            this.arg$1 = this;
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return AbstractSignatureParts._get_boundsNullability_$lambda$0$2(this.arg$0, this.arg$1);
                        }
                    });
                    if (!arrayList2.isEmpty()) {
                        ArrayList arrayList3 = arrayList2;
                        if (!(arrayList3 instanceof Collection) || !arrayList3.isEmpty()) {
                            Iterator it2 = arrayList3.iterator();
                            do {
                                if (it2.hasNext()) {
                                }
                            } while (!getShouldPropagateBoundNullness((KotlinTypeMarker) it2.next()));
                            list_get_boundsNullability_$lambda$0$3 = upperBounds;
                        }
                        return new WithMigrationStatus<>(NullabilityQualifier.FORCE_FLEXIBILITY, false);
                    }
                    if (_get_boundsNullability_$lambda$0$3(lazy).isEmpty()) {
                        break;
                    }
                    List<KotlinTypeMarker> list_get_boundsNullability_$lambda$0$4 = _get_boundsNullability_$lambda$0$3(lazy);
                    if (!(list_get_boundsNullability_$lambda$0$4 instanceof Collection) || !list_get_boundsNullability_$lambda$0$4.isEmpty()) {
                        Iterator<T> it3 = list_get_boundsNullability_$lambda$0$4.iterator();
                        do {
                            if (it3.hasNext()) {
                            }
                        } while (!getShouldPropagateBoundNullness((KotlinTypeMarker) it3.next()));
                        list_get_boundsNullability_$lambda$0$3 = _get_boundsNullability_$lambda$0$3(lazy);
                    }
                    return new WithMigrationStatus<>(NullabilityQualifier.FORCE_FLEXIBILITY, true);
                    List<KotlinTypeMarker> list2 = list_get_boundsNullability_$lambda$0$3;
                    if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                        Iterator<T> it4 = list2.iterator();
                        while (true) {
                            if (!it4.hasNext()) {
                                nullabilityQualifier = NullabilityQualifier.NULLABLE;
                                break;
                            }
                            if (!typeSystem.isNullableType((KotlinTypeMarker) it4.next())) {
                                nullabilityQualifier = NullabilityQualifier.NOT_NULL;
                                break;
                            }
                        }
                    } else {
                        nullabilityQualifier = NullabilityQualifier.NULLABLE;
                        break;
                    }
                    return new WithMigrationStatus<>(nullabilityQualifier, list_get_boundsNullability_$lambda$0$3 != upperBounds);
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List _get_boundsNullability_$lambda$0$2(List list, AbstractSignatureParts abstractSignatureParts) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            KotlinTypeMarker enhancedForWarnings = abstractSignatureParts.getEnhancedForWarnings((KotlinTypeMarker) it.next());
            if (enhancedForWarnings != null) {
                arrayList.add(enhancedForWarnings);
            }
        }
        return arrayList;
    }

    private static final List<KotlinTypeMarker> _get_boundsNullability_$lambda$0$3(Lazy<? extends List<? extends KotlinTypeMarker>> lazy) {
        return (List) lazy.getValue();
    }

    public final Function1<Integer, JavaTypeQualifiers> computeIndexedQualifiers(KotlinTypeMarker kotlinTypeMarker, Iterable<? extends KotlinTypeMarker> overrides, final TypeEnhancementInfo typeEnhancementInfo, boolean z) {
        boolean z2;
        JavaTypeQualifiers javaTypeQualifiersComputeQualifiersForOverride;
        KotlinTypeMarker type;
        JavaDefaultQualifiers javaDefaultQualifiersComputeIndexedQualifiers$lambda$3;
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        Intrinsics.checkNotNullParameter(overrides, "overrides");
        final List<TypeAndDefaultQualifiers> indexed = toIndexed(kotlinTypeMarker);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(overrides, 10));
        Iterator<? extends KotlinTypeMarker> it = overrides.iterator();
        while (it.hasNext()) {
            arrayList.add(toIndexed(it.next()));
        }
        ArrayList arrayList2 = arrayList;
        if (isCovariant() && (!(overrides instanceof Collection) || !((Collection) overrides).isEmpty())) {
            Iterator<? extends KotlinTypeMarker> it2 = overrides.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z2 = false;
                    break;
                }
                if (!isEqual(kotlinTypeMarker, it2.next())) {
                    z2 = true;
                    break;
                }
            }
        } else {
            z2 = false;
            break;
        }
        int size = getForceOnlyHeadTypeConstructor() ? 1 : indexed.size();
        final JavaTypeQualifiers[] javaTypeQualifiersArr = new JavaTypeQualifiers[size];
        final int i = 0;
        while (i < size) {
            Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0(this, indexed, i) { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$$Lambda$2
                private final AbstractSignatureParts arg$0;
                private final List arg$1;
                private final int arg$2;

                {
                    this.arg$0 = this;
                    this.arg$1 = indexed;
                    this.arg$2 = i;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return AbstractSignatureParts.computeIndexedQualifiers$lambda$2(this.arg$0, this.arg$1, this.arg$2);
                }
            });
            if (i > 0 && z2) {
                if (isK2() && (javaDefaultQualifiersComputeIndexedQualifiers$lambda$3 = computeIndexedQualifiers$lambda$3(lazy)) != null && javaDefaultQualifiersComputeIndexedQualifiers$lambda$3.getPreferQualifierOverSupertype()) {
                    javaTypeQualifiersComputeQualifiersForOverride = extractQualifiersFromAnnotations(indexed.get(i), computeIndexedQualifiers$lambda$3(lazy));
                } else {
                    javaTypeQualifiersComputeQualifiersForOverride = JavaTypeQualifiers.Companion.getNONE();
                }
            } else {
                JavaTypeQualifiers javaTypeQualifiersExtractQualifiersFromAnnotations = extractQualifiersFromAnnotations(indexed.get(i), computeIndexedQualifiers$lambda$3(lazy));
                ArrayList arrayList3 = new ArrayList();
                Iterator it3 = arrayList2.iterator();
                while (it3.hasNext()) {
                    TypeAndDefaultQualifiers typeAndDefaultQualifiers = (TypeAndDefaultQualifiers) CollectionsKt.getOrNull((List) it3.next(), i);
                    JavaTypeQualifiers javaTypeQualifiersExtractQualifiers = (typeAndDefaultQualifiers == null || (type = typeAndDefaultQualifiers.getType()) == null) ? null : extractQualifiers(type);
                    if (javaTypeQualifiersExtractQualifiers != null) {
                        arrayList3.add(javaTypeQualifiersExtractQualifiers);
                    }
                }
                javaTypeQualifiersComputeQualifiersForOverride = TypeEnhancementUtilsKt.computeQualifiersForOverride(javaTypeQualifiersExtractQualifiersFromAnnotations, arrayList3, i == 0 && isCovariant(), i == 0 && getContainerIsVarargParameter(), z);
            }
            javaTypeQualifiersArr[i] = javaTypeQualifiersComputeQualifiersForOverride;
            i++;
        }
        return new Function1(typeEnhancementInfo, javaTypeQualifiersArr) { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$$Lambda$3
            private final TypeEnhancementInfo arg$0;
            private final JavaTypeQualifiers[] arg$1;

            {
                this.arg$0 = typeEnhancementInfo;
                this.arg$1 = javaTypeQualifiersArr;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return AbstractSignatureParts.computeIndexedQualifiers$lambda$5(this.arg$0, this.arg$1, ((Number) obj).intValue());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JavaDefaultQualifiers computeIndexedQualifiers$lambda$2(AbstractSignatureParts abstractSignatureParts, List list, int i) {
        return abstractSignatureParts.extractDefaultQualifier((TypeAndDefaultQualifiers) list.get(i));
    }

    private static final JavaDefaultQualifiers computeIndexedQualifiers$lambda$3(Lazy<JavaDefaultQualifiers> lazy) {
        return lazy.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JavaTypeQualifiers computeIndexedQualifiers$lambda$5(TypeEnhancementInfo typeEnhancementInfo, JavaTypeQualifiers[] javaTypeQualifiersArr, int i) {
        Map<Integer, JavaTypeQualifiers> map;
        JavaTypeQualifiers javaTypeQualifiers;
        if (typeEnhancementInfo == null || (map = typeEnhancementInfo.getMap()) == null || (javaTypeQualifiers = map.get(Integer.valueOf(i))) == null) {
            return (i < 0 || i >= javaTypeQualifiersArr.length) ? JavaTypeQualifiers.Companion.getNONE() : javaTypeQualifiersArr[i];
        }
        return javaTypeQualifiers;
    }

    private final <T> void flattenTree(T t, List<T> list, Function1<? super T, ? extends Iterable<? extends T>> function1) {
        list.add(t);
        Iterable<? extends T> iterableInvoke = function1.invoke(t);
        if (iterableInvoke != null) {
            Iterator<? extends T> it = iterableInvoke.iterator();
            while (it.hasNext()) {
                flattenTree(it.next(), list, function1);
            }
        }
    }

    private final <T> List<T> flattenTree(T t, Function1<? super T, ? extends Iterable<? extends T>> function1) {
        ArrayList arrayList = new ArrayList(1);
        flattenTree(t, arrayList, function1);
        return arrayList;
    }

    private final JavaTypeQualifiersByElementType extractAndMergeDefaultQualifiers(KotlinTypeMarker kotlinTypeMarker, JavaTypeQualifiersByElementType javaTypeQualifiersByElementType) {
        return AbstractAnnotationTypeQualifierResolver.extractAndMergeDefaultQualifiers$default(getAnnotationTypeQualifierResolver(), javaTypeQualifiersByElementType, getAnnotations(kotlinTypeMarker), false, 4, null);
    }

    private final List<TypeAndDefaultQualifiers> toIndexed(KotlinTypeMarker kotlinTypeMarker) {
        final TypeSystemContext typeSystem = getTypeSystem();
        return flattenTree(new TypeAndDefaultQualifiers(kotlinTypeMarker, extractAndMergeDefaultQualifiers(kotlinTypeMarker, getContainerDefaultTypeQualifiers()), null), new Function1(this, typeSystem) { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$$Lambda$4
            private final AbstractSignatureParts arg$0;
            private final TypeSystemContext arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = typeSystem;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return AbstractSignatureParts.toIndexed$lambda$0$0(this.arg$0, this.arg$1, (AbstractSignatureParts.TypeAndDefaultQualifiers) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Iterable toIndexed$lambda$0$0(AbstractSignatureParts abstractSignatureParts, TypeSystemContext typeSystemContext, TypeAndDefaultQualifiers it) {
        TypeConstructorMarker typeConstructorMarkerTypeConstructor;
        List<TypeParameterMarker> parameters;
        TypeAndDefaultQualifiers typeAndDefaultQualifiers;
        KotlinTypeMarker type;
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = null;
        if (abstractSignatureParts.getSkipRawTypeArguments() && (type = it.getType()) != null && typeSystemContext.isRawType(type)) {
            return null;
        }
        KotlinTypeMarker type2 = it.getType();
        if (type2 != null && (typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(type2)) != null && (parameters = typeSystemContext.getParameters(typeConstructorMarkerTypeConstructor)) != null) {
            List<TypeParameterMarker> list = parameters;
            List<TypeArgumentMarker> arguments = typeSystemContext.getArguments(it.getType());
            Iterator<T> it2 = list.iterator();
            Iterator<T> it3 = arguments.iterator();
            ArrayList arrayList2 = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(list, 10), CollectionsKt.collectionSizeOrDefault(arguments, 10)));
            while (it2.hasNext() && it3.hasNext()) {
                TypeParameterMarker typeParameterMarker = (TypeParameterMarker) it2.next();
                KotlinTypeMarker type3 = typeSystemContext.getType((TypeArgumentMarker) it3.next());
                if (type3 == null) {
                    typeAndDefaultQualifiers = new TypeAndDefaultQualifiers(null, it.getDefaultQualifiers(), typeParameterMarker);
                } else {
                    typeAndDefaultQualifiers = new TypeAndDefaultQualifiers(type3, abstractSignatureParts.extractAndMergeDefaultQualifiers(type3, it.getDefaultQualifiers()), typeParameterMarker);
                }
                arrayList2.add(typeAndDefaultQualifiers);
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AbstractSignatureParts.kt */
    static final class TypeAndDefaultQualifiers {
        private final JavaTypeQualifiersByElementType defaultQualifiers;
        private final KotlinTypeMarker type;
        private final TypeParameterMarker typeParameterForArgument;

        public TypeAndDefaultQualifiers(KotlinTypeMarker kotlinTypeMarker, JavaTypeQualifiersByElementType javaTypeQualifiersByElementType, TypeParameterMarker typeParameterMarker) {
            this.type = kotlinTypeMarker;
            this.defaultQualifiers = javaTypeQualifiersByElementType;
            this.typeParameterForArgument = typeParameterMarker;
        }

        public final KotlinTypeMarker getType() {
            return this.type;
        }

        public final JavaTypeQualifiersByElementType getDefaultQualifiers() {
            return this.defaultQualifiers;
        }

        public final TypeParameterMarker getTypeParameterForArgument() {
            return this.typeParameterForArgument;
        }
    }
}
