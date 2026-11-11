package kotlin.reflect.jvm.internal.types;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: CapturedKType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\u001a\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u0006\u0012\u0002\b\u00030\u0006H\u0000\u001a\b\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\t"}, d2 = {"captureKTypeFromArguments", "Lkotlin/reflect/KType;", "type", "allTypeParameters", "", "Lkotlin/reflect/KTypeParameter;", "Lkotlin/reflect/KClass;", "javaTypeNotSupported", "", "kotlin-reflection"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CapturedKTypeKt {
    public static final KType captureKTypeFromArguments(KType type) {
        int i;
        Intrinsics.checkNotNullParameter(type, "type");
        KClassifier classifier = type.getClassifier();
        KClass<?> kClass = classifier instanceof KClass ? (KClass) classifier : null;
        if (kClass == null) {
            return null;
        }
        List<KTypeProjection> arguments = type.getArguments();
        List<KTypeProjection> list = arguments;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (((KTypeProjection) it.next()).getVariance() != KVariance.INVARIANT) {
                    List<KTypeParameter> listAllTypeParameters = allTypeParameters(kClass);
                    if (listAllTypeParameters.size() != arguments.size()) {
                        return null;
                    }
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator<T> it2 = list.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        KTypeProjection kTypeProjectionInvariant = (KTypeProjection) it2.next();
                        if (kTypeProjectionInvariant.getVariance() != KVariance.INVARIANT) {
                            KType type2 = kTypeProjectionInvariant.getType();
                            if (kTypeProjectionInvariant.getVariance() != KVariance.IN) {
                                type2 = null;
                            }
                            kTypeProjectionInvariant = KTypeProjection.INSTANCE.invariant(new CapturedKType(type2, new CapturedKTypeConstructor(kTypeProjectionInvariant), false));
                        }
                        arrayList.add(kTypeProjectionInvariant);
                    }
                    ArrayList arrayList2 = arrayList;
                    KTypeSubstitutor kTypeSubstitutorCreate = KTypeSubstitutor.INSTANCE.create(kClass, arrayList2, false);
                    int size = arguments.size();
                    for (i = 0; i < size; i++) {
                        KTypeProjection kTypeProjection = arguments.get(i);
                        if (kTypeProjection.getVariance() != KVariance.INVARIANT) {
                            List<KType> upperBounds = listAllTypeParameters.get(i).getUpperBounds();
                            ArrayList arrayList3 = new ArrayList();
                            Iterator<T> it3 = upperBounds.iterator();
                            while (it3.hasNext()) {
                                KType type3 = KTypeSubstitutor.substitute$default(kTypeSubstitutorCreate, (KType) it3.next(), null, 2, null).getType();
                                Intrinsics.checkNotNull(type3);
                                arrayList3.add(type3);
                            }
                            ArrayList arrayList4 = arrayList3;
                            if (kTypeProjection.getVariance() == KVariance.OUT) {
                                KType type4 = kTypeProjection.getType();
                                Intrinsics.checkNotNull(type4);
                                arrayList4.add(type4);
                            }
                            KType type5 = ((KTypeProjection) arrayList2.get(i)).getType();
                            Intrinsics.checkNotNull(type5, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.types.CapturedKType");
                            ((CapturedKType) type5).getTypeConstructor().setSupertypes(arrayList4);
                        }
                    }
                    KClass<?> kClass2 = kClass;
                    boolean isMarkedNullable = type.getIsMarkedNullable();
                    List<Annotation> annotations = type.getAnnotations();
                    boolean z = type instanceof AbstractKType;
                    AbstractKType abstractKType = z ? (AbstractKType) type : null;
                    KType abbreviation = abstractKType != null ? abstractKType.getAbbreviation() : null;
                    AbstractKType abstractKType2 = z ? (AbstractKType) type : null;
                    return new SimpleKType(kClass2, arrayList2, isMarkedNullable, annotations, abbreviation, false, false, false, abstractKType2 != null ? abstractKType2.getMutableCollectionClass() : null, null, 512, null);
                }
            }
        }
        return null;
    }

    public static final List<KTypeParameter> allTypeParameters(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return SequencesKt.toList(SequencesKt.flatMapIterable(SequencesKt.generateSequence(kClass, new Function1() { // from class: kotlin.reflect.jvm.internal.types.CapturedKTypeKt$$Lambda$0
            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return CapturedKTypeKt.allTypeParameters$lambda$0((KClass) obj);
            }
        }), new Function1() { // from class: kotlin.reflect.jvm.internal.types.CapturedKTypeKt$$Lambda$1
            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return CapturedKTypeKt.allTypeParameters$lambda$1((KClass) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KClass allTypeParameters$lambda$0(KClass it) {
        Class<?> declaringClass;
        Intrinsics.checkNotNullParameter(it, "it");
        if (!it.isInner() || (declaringClass = JvmClassMappingKt.getJavaClass(it).getDeclaringClass()) == null) {
            return null;
        }
        return JvmClassMappingKt.getKotlinClass(declaringClass);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Iterable allTypeParameters$lambda$1(KClass it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getTypeParameters();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void javaTypeNotSupported() {
        throw new KotlinReflectionInternalError("javaType for captured types is not supported");
    }
}
