package kotlin.reflect.jvm.internal.types;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.SystemPropertiesKt;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: typeOfImpl.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\u0007H\u0002\u001a\u0010\u0010\b\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000¨\u0006\t"}, d2 = {"createPlatformKType", "Lkotlin/reflect/KType;", "lowerBound", "upperBound", "createMutableCollectionKType", "type", "readOnlyToMutable", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "createNothingType", "kotlin-reflection"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TypeOfImplKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final KType createPlatformKType(KType lowerBound, KType upperBound) {
        Intrinsics.checkNotNullParameter(lowerBound, "lowerBound");
        Intrinsics.checkNotNullParameter(upperBound, "upperBound");
        if (SystemPropertiesKt.getUseK1Implementation()) {
            KotlinType type = ((DescriptorKType) lowerBound).getType();
            Intrinsics.checkNotNull(type, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
            KotlinType type2 = ((DescriptorKType) upperBound).getType();
            Intrinsics.checkNotNull(type2, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
            return new DescriptorKType(KotlinTypeFactory.flexibleType((SimpleType) type, (SimpleType) type2), null, 2, 0 == true ? 1 : 0);
        }
        return FlexibleKType.Companion.create$default(FlexibleKType.INSTANCE, (AbstractKType) lowerBound, (AbstractKType) upperBound, false, null, 8, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final KType createMutableCollectionKType(KType type) {
        String qualifiedName;
        Intrinsics.checkNotNullParameter(type, "type");
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        if (SystemPropertiesKt.getUseK1Implementation()) {
            KotlinType type2 = ((DescriptorKType) type).getType();
            if (!(type2 instanceof SimpleType)) {
                throw new IllegalArgumentException(("Non-simple type cannot be a mutable collection type: " + type).toString());
            }
            ClassifierDescriptor classifierDescriptorMo10246getDeclarationDescriptor = type2.getConstructor().mo10246getDeclarationDescriptor();
            ClassDescriptor classDescriptor = classifierDescriptorMo10246getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo10246getDeclarationDescriptor : null;
            if (classDescriptor == null) {
                throw new IllegalArgumentException("Non-class type cannot be a mutable collection type: " + type);
            }
            SimpleType simpleType = (SimpleType) type2;
            TypeConstructor typeConstructor = readOnlyToMutable(classDescriptor).getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(typeConstructor, "getTypeConstructor(...)");
            return new DescriptorKType(KotlinTypeFactory.simpleType$default(simpleType, (TypeAttributes) null, typeConstructor, (List) null, false, 26, (Object) null), objArr2 == true ? 1 : 0, 2, objArr == true ? 1 : 0);
        }
        SimpleKType simpleKType = (SimpleKType) type;
        KClassifier classifier = simpleKType.getClassifier();
        KClass kClass = classifier instanceof KClass ? (KClass) classifier : null;
        if (kClass == null || (qualifiedName = kClass.getQualifiedName()) == null) {
            throw new KotlinReflectionInternalError("Non-class type cannot be a mutable collection type: " + type);
        }
        FqName onlyToMutable = JavaToKotlinClassMap.INSTANCE.readOnlyToMutable(new FqNameUnsafe(qualifiedName));
        if (onlyToMutable == null) {
            throw new IllegalArgumentException("Not a readonly collection: " + type);
        }
        return new SimpleKType(simpleKType.getClassifier(), simpleKType.getArguments(), simpleKType.getIsMarkedNullable(), simpleKType.getAnnotations(), simpleKType.getAbbreviation(), simpleKType.getIsDefinitelyNotNullType(), simpleKType.getIsNothingType(), simpleKType.getIsSuspendFunctionType(), MutableCollectionKClassKt.getMutableCollectionKClass(onlyToMutable, (KClass) classifier), null, 512, null);
    }

    private static final ClassDescriptor readOnlyToMutable(ClassDescriptor classDescriptor) {
        ClassDescriptor classDescriptor2 = classDescriptor;
        FqName onlyToMutable = JavaToKotlinClassMap.INSTANCE.readOnlyToMutable(DescriptorUtilsKt.getFqNameUnsafe(classDescriptor2));
        if (onlyToMutable == null) {
            throw new IllegalArgumentException("Not a readonly collection: " + classDescriptor);
        }
        ClassDescriptor builtInClassByFqName = DescriptorUtilsKt.getBuiltIns(classDescriptor2).getBuiltInClassByFqName(onlyToMutable);
        Intrinsics.checkNotNullExpressionValue(builtInClassByFqName, "getBuiltInClassByFqName(...)");
        return builtInClassByFqName;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final KType createNothingType(KType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (SystemPropertiesKt.getUseK1Implementation()) {
            KotlinType type2 = ((DescriptorKType) type).getType();
            if (!(type2 instanceof SimpleType)) {
                throw new IllegalArgumentException(("Non-simple type cannot be a Nothing type: " + type).toString());
            }
            TypeConstructor typeConstructor = TypeUtilsKt.getBuiltIns(type2).getNothing().getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(typeConstructor, "getTypeConstructor(...)");
            return new DescriptorKType(KotlinTypeFactory.simpleType$default((SimpleType) type2, (TypeAttributes) null, typeConstructor, (List) null, false, 26, (Object) null), null, 2, 0 == true ? 1 : 0);
        }
        SimpleKType simpleKType = (SimpleKType) type;
        if (!Intrinsics.areEqual(simpleKType.getClassifier(), Reflection.getOrCreateKotlinClass(Void.class))) {
            throw new IllegalArgumentException(("Nothing type's classifier must be Void::class: " + type).toString());
        }
        return new SimpleKType(simpleKType.getClassifier(), simpleKType.getArguments(), simpleKType.getIsMarkedNullable(), simpleKType.getAnnotations(), simpleKType.getAbbreviation(), simpleKType.getIsDefinitelyNotNullType(), true, simpleKType.getIsSuspendFunctionType(), simpleKType.getMutableCollectionClass(), null, 512, null);
    }
}
