package kotlin.reflect.jvm.internal.types;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import kotlin.reflect.full.KClassifiers;
import kotlin.reflect.jvm.internal.KTypeParameterImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.StandardKTypes;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: compiled from: MutableCollectionKClass.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0000\u001a\u0015\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0005\"\u0006\b\u0000\u0010\u0007\u0018\u0001H\u0082\b¨\u0006\b"}, d2 = {"getMutableCollectionKClass", "Lkotlin/reflect/jvm/internal/types/MutableCollectionKClass;", "mutableFqName", "Lkotlin/reflect/jvm/internal/impl/name/FqName;", "readonlyKClass", "Lkotlin/reflect/KClass;", "mutableClassOf", ExifInterface.GPS_DIRECTION_TRUE, "kotlin-reflection"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MutableCollectionKClassKt {
    public static final MutableCollectionKClass<?> getMutableCollectionKClass(final FqName mutableFqName, final KClass<?> readonlyKClass) {
        Intrinsics.checkNotNullParameter(mutableFqName, "mutableFqName");
        Intrinsics.checkNotNullParameter(readonlyKClass, "readonlyKClass");
        return new MutableCollectionKClass<>(readonlyKClass, mutableFqName.asString(), new Function1(readonlyKClass, mutableFqName) { // from class: kotlin.reflect.jvm.internal.types.MutableCollectionKClassKt$$Lambda$0
            private final KClass arg$0;
            private final FqName arg$1;

            {
                this.arg$0 = readonlyKClass;
                this.arg$1 = mutableFqName;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return MutableCollectionKClassKt.getMutableCollectionKClass$lambda$0(this.arg$0, this.arg$1, (MutableCollectionKClass) obj);
            }
        }, new Function1(mutableFqName, readonlyKClass) { // from class: kotlin.reflect.jvm.internal.types.MutableCollectionKClassKt$$Lambda$1
            private final FqName arg$0;
            private final KClass arg$1;

            {
                this.arg$0 = mutableFqName;
                this.arg$1 = readonlyKClass;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return MutableCollectionKClassKt.getMutableCollectionKClass$lambda$1(this.arg$0, this.arg$1, (MutableCollectionKClass) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getMutableCollectionKClass$lambda$0(KClass kClass, FqName fqName, MutableCollectionKClass klass) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        List<KTypeParameter> typeParameters = kClass.getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            KTypeParameterImpl kTypeParameterImpl = new KTypeParameterImpl(klass, ((KTypeParameter) it.next()).getName(), (Intrinsics.areEqual(fqName, StandardNames.FqNames.mutableIterable) || Intrinsics.areEqual(fqName, StandardNames.FqNames.mutableIterator)) ? KVariance.OUT : KVariance.INVARIANT, false);
            kTypeParameterImpl.setUpperBounds(CollectionsKt.listOf(StandardKTypes.INSTANCE.getNULLABLE_ANY()));
            arrayList.add(kTypeParameterImpl);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getMutableCollectionKClass$lambda$1(FqName fqName, KClass kClass, MutableCollectionKClass klass) {
        KClass<?> mutableCollectionClass;
        Intrinsics.checkNotNullParameter(klass, "klass");
        if (!Intrinsics.areEqual(fqName, StandardNames.FqNames.mutableCollection)) {
            if (!Intrinsics.areEqual(fqName, StandardNames.FqNames.mutableList)) {
                if (!Intrinsics.areEqual(fqName, StandardNames.FqNames.mutableSet)) {
                    if (Intrinsics.areEqual(fqName, StandardNames.FqNames.mutableListIterator)) {
                        KType kTypeMutableCollectionType = Reflection.mutableCollectionType(Reflection.typeOf(Iterator.class, KTypeProjection.INSTANCE.getSTAR()));
                        Intrinsics.checkNotNull(kTypeMutableCollectionType, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.types.AbstractKType");
                        mutableCollectionClass = ((AbstractKType) kTypeMutableCollectionType).getMutableCollectionClass();
                        if (mutableCollectionClass == null) {
                            throw new KotlinReflectionInternalError("No mutable collection class found: " + Reflection.getOrCreateKotlinClass(Iterator.class));
                        }
                    } else {
                        mutableCollectionClass = null;
                    }
                } else {
                    KType kTypeMutableCollectionType2 = Reflection.mutableCollectionType(Reflection.typeOf(Collection.class, KTypeProjection.INSTANCE.getSTAR()));
                    Intrinsics.checkNotNull(kTypeMutableCollectionType2, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.types.AbstractKType");
                    mutableCollectionClass = ((AbstractKType) kTypeMutableCollectionType2).getMutableCollectionClass();
                    if (mutableCollectionClass == null) {
                        throw new KotlinReflectionInternalError("No mutable collection class found: " + Reflection.getOrCreateKotlinClass(Collection.class));
                    }
                }
            } else {
                KType kTypeMutableCollectionType3 = Reflection.mutableCollectionType(Reflection.typeOf(Collection.class, KTypeProjection.INSTANCE.getSTAR()));
                Intrinsics.checkNotNull(kTypeMutableCollectionType3, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.types.AbstractKType");
                mutableCollectionClass = ((AbstractKType) kTypeMutableCollectionType3).getMutableCollectionClass();
                if (mutableCollectionClass == null) {
                    throw new KotlinReflectionInternalError("No mutable collection class found: " + Reflection.getOrCreateKotlinClass(Collection.class));
                }
            }
        } else {
            KType kTypeMutableCollectionType4 = Reflection.mutableCollectionType(Reflection.typeOf(Iterable.class, KTypeProjection.INSTANCE.getSTAR()));
            Intrinsics.checkNotNull(kTypeMutableCollectionType4, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.types.AbstractKType");
            mutableCollectionClass = ((AbstractKType) kTypeMutableCollectionType4).getMutableCollectionClass();
            if (mutableCollectionClass == null) {
                throw new KotlinReflectionInternalError("No mutable collection class found: " + Reflection.getOrCreateKotlinClass(Iterable.class));
            }
        }
        List<KTypeParameter> typeParameters = klass.getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(KTypeProjection.INSTANCE.invariant(KClassifiers.createType$default((KTypeParameter) it.next(), null, false, null, 7, null)));
        }
        ArrayList arrayList2 = arrayList;
        List listListOfNotNull = CollectionsKt.listOfNotNull((Object[]) new KClass[]{kClass, mutableCollectionClass});
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOfNotNull, 10));
        Iterator it2 = listListOfNotNull.iterator();
        while (it2.hasNext()) {
            arrayList3.add(KClassifiers.createType$default((KClass) it2.next(), arrayList2, false, null, 6, null));
        }
        return arrayList3;
    }
}
