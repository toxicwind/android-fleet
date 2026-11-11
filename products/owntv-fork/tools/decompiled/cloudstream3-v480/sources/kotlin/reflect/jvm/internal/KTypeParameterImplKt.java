package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KVariance;
import kotlin.reflect.jvm.ReflectedLambdaFakeContainerSource;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* JADX INFO: compiled from: KTypeParameterImpl.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0000\u001a\u0010\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007*\u00020\bH\u0002\u001a\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"toKVariance", "Lkotlin/reflect/KVariance;", "Lkotlin/reflect/jvm/internal/impl/types/Variance;", "toContainer", "Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;", "Lkotlin/reflect/jvm/internal/impl/descriptors/TypeParameterDescriptor;", "toKClassImpl", "Lkotlin/reflect/jvm/internal/KClassImpl;", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "getContainerOfDeserializedMember", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/descriptors/DeserializedMemberDescriptor;", "kotlin-reflection"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class KTypeParameterImplKt {

    /* JADX INFO: compiled from: KTypeParameterImpl.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            try {
                iArr[Variance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Variance.IN_VARIANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Variance.OUT_VARIANCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KVariance toKVariance(Variance variance) {
        int i = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
        if (i == 1) {
            return KVariance.INVARIANT;
        }
        if (i == 2) {
            return KVariance.IN;
        }
        if (i != 3) {
            throw new NoWhenBranchMatchedException();
        }
        return KVariance.OUT;
    }

    public static final KTypeParameterOwnerImpl toContainer(TypeParameterDescriptor typeParameterDescriptor) {
        KClassImpl<?> containerOfDeserializedMember;
        Intrinsics.checkNotNullParameter(typeParameterDescriptor, "<this>");
        DeclarationDescriptor containingDeclaration = typeParameterDescriptor.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "getContainingDeclaration(...)");
        if (containingDeclaration instanceof ClassDescriptor) {
            return toKClassImpl((ClassDescriptor) containingDeclaration);
        }
        if (containingDeclaration instanceof CallableMemberDescriptor) {
            DeclarationDescriptor containingDeclaration2 = ((CallableMemberDescriptor) containingDeclaration).getContainingDeclaration();
            Intrinsics.checkNotNullExpressionValue(containingDeclaration2, "getContainingDeclaration(...)");
            if (containingDeclaration2 instanceof ClassDescriptor) {
                containerOfDeserializedMember = toKClassImpl((ClassDescriptor) containingDeclaration2);
            } else {
                DeserializedMemberDescriptor deserializedMemberDescriptor = containingDeclaration instanceof DeserializedMemberDescriptor ? (DeserializedMemberDescriptor) containingDeclaration : null;
                if (deserializedMemberDescriptor == null) {
                    throw new KotlinReflectionInternalError("Non-class callable descriptor must be deserialized: " + containingDeclaration);
                }
                containerOfDeserializedMember = getContainerOfDeserializedMember(deserializedMemberDescriptor);
            }
            Object objAccept = containingDeclaration.accept(new CreateKCallableVisitor(containerOfDeserializedMember), Unit.INSTANCE);
            Intrinsics.checkNotNull(objAccept);
            return (KTypeParameterOwnerImpl) objAccept;
        }
        throw new KotlinReflectionInternalError("Unknown type parameter container: " + containingDeclaration);
    }

    private static final KClassImpl<?> toKClassImpl(ClassDescriptor classDescriptor) {
        Class<?> javaClass = UtilKt.toJavaClass(classDescriptor);
        KClassImpl<?> kClassImpl = (KClassImpl) (javaClass != null ? JvmClassMappingKt.getKotlinClass(javaClass) : null);
        if (kClassImpl != null) {
            return kClassImpl;
        }
        throw new KotlinReflectionInternalError("Type parameter container is not resolved: " + classDescriptor.getContainingDeclaration());
    }

    private static final KDeclarationContainerImpl getContainerOfDeserializedMember(DeserializedMemberDescriptor deserializedMemberDescriptor) {
        Class<?> klass;
        DeserializedContainerSource containerSource = deserializedMemberDescriptor.getContainerSource();
        if (containerSource instanceof JvmPackagePartSource) {
            JvmPackagePartSource jvmPackagePartSource = (JvmPackagePartSource) containerSource;
            KotlinJvmBinaryClass knownJvmBinaryClass = jvmPackagePartSource.getKnownJvmBinaryClass();
            ReflectKotlinClass reflectKotlinClass = knownJvmBinaryClass instanceof ReflectKotlinClass ? (ReflectKotlinClass) knownJvmBinaryClass : null;
            if (reflectKotlinClass != null && (klass = reflectKotlinClass.getKlass()) != null) {
                KDeclarationContainer orCreateKotlinPackage = Reflection.getOrCreateKotlinPackage(klass);
                Intrinsics.checkNotNull(orCreateKotlinPackage, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KPackageImpl");
                KPackageImpl kPackageImpl = (KPackageImpl) orCreateKotlinPackage;
                if (kPackageImpl != null) {
                    return kPackageImpl;
                }
            }
            throw new KotlinReflectionInternalError("Container of top-level deserialized member is not resolved: " + deserializedMemberDescriptor + " (" + jvmPackagePartSource.getKnownJvmBinaryClass());
        }
        if (containerSource instanceof LocalDelegatedPropertyFakeContainerSource) {
            return ((LocalDelegatedPropertyFakeContainerSource) containerSource).getContainer();
        }
        if (containerSource instanceof ReflectedLambdaFakeContainerSource) {
            return EmptyContainerForLocal.INSTANCE;
        }
        throw new KotlinReflectionInternalError("Container of deserialized member is not resolved: " + deserializedMemberDescriptor);
    }
}
