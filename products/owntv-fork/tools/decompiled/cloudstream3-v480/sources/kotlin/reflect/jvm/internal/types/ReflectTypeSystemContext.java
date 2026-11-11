package kotlin.reflect.jvm.internal.types;

import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import kotlin.reflect.jvm.internal.ErrorTypeParameter;
import kotlin.reflect.jvm.internal.KClassImpl;
import kotlin.reflect.jvm.internal.KTypeParameterImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.km.ClassKind;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.model.ArgumentList;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DynamicTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.RigidTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSubstitutorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariableTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;

/* JADX INFO: compiled from: ReflectTypeSystemContext.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\u0010\u0000\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0006H\u0016J\u000e\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u0006H\u0016J\f\u0010\t\u001a\u00020\n*\u00020\u0006H\u0016J\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010\f\u001a\u00020\n*\u00020\u0006H\u0016J\u000e\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\bH\u0016J\f\u0010\u000f\u001a\u00020\n*\u00020\u0006H\u0016J\f\u0010\u0010\u001a\u00020\u0005*\u00020\bH\u0016J\f\u0010\u0011\u001a\u00020\u0005*\u00020\bH\u0016J\u000e\u0010\u0012\u001a\u0004\u0018\u00010\u0013*\u00020\u0014H\u0016J\u000e\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u0005H\u0016J\f\u0010\u0017\u001a\u00020\u0014*\u00020\u0016H\u0016J\u0014\u0010\u0018\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0019\u001a\u00020\nH\u0016J\f\u0010\u0018\u001a\u00020\u0005*\u00020\u0005H\u0016J\f\u0010\u001a\u001a\u00020\n*\u00020\u0006H\u0016J\u0014\u0010\u001b\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u001c\u001a\u00020\nH\u0016J\f\u0010\u001d\u001a\u00020\u000b*\u00020\u0005H\u0016J\u0014\u0010\u001b\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001c\u001a\u00020\nH\u0016J\f\u0010\u001e\u001a\u00020\n*\u00020\u0013H\u0016J\f\u0010\u001d\u001a\u00020\u001f*\u00020\u0013H\u0016J\f\u0010 \u001a\u00020!*\u00020\u0013H\u0016J\f\u0010\"\u001a\u00020\n*\u00020\u0013H\u0017J\f\u0010#\u001a\u00020$*\u00020\u001fH\u0016J\f\u0010%\u001a\u00020&*\u00020\u0006H\u0016J\u0014\u0010'\u001a\u00020$*\u00020\u00062\u0006\u0010(\u001a\u00020&H\u0016J\u0012\u0010)\u001a\b\u0012\u0004\u0012\u00020$0**\u00020\u0006H\u0016J\f\u0010+\u001a\u00020\n*\u00020\u0005H\u0016J\f\u0010,\u001a\u00020\n*\u00020\u0005H\u0016J\f\u0010-\u001a\u00020\n*\u00020\u0005H\u0016J\f\u0010.\u001a\u00020\u000b*\u00020\u000bH\u0016J\f\u0010/\u001a\u00020$*\u00020\u0006H\u0016J\u000e\u00100\u001a\u0004\u0018\u00010\u0006*\u00020\u0013H\u0016J\f\u00101\u001a\u00020\n*\u00020$H\u0016J\f\u00102\u001a\u000203*\u00020$H\u0016J\f\u00104\u001a\u000203*\u000205H\u0002J\u000e\u00106\u001a\u0004\u0018\u00010\u0006*\u00020$H\u0016J\u0014\u00107\u001a\u00020$*\u00020$2\u0006\u00108\u001a\u00020\u0006H\u0016J\f\u00109\u001a\u00020&*\u00020\u000bH\u0016J\u0014\u0010:\u001a\u00020;*\u00020\u000b2\u0006\u0010(\u001a\u00020&H\u0016J\u0012\u0010<\u001a\b\u0012\u0004\u0012\u00020;0**\u00020\u000bH\u0016J\u0012\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00060>*\u00020\u000bH\u0016J\f\u0010?\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010@\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010A\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010B\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010C\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010D\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010E\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010F\u001a\u00020\n*\u00020\u000bH\u0016J\u000e\u0010G\u001a\u0004\u0018\u00010;*\u00020\u000bH\u0016J\f\u0010H\u001a\u00020\n*\u00020\u000bH\u0016J\f\u00102\u001a\u000203*\u00020;H\u0016J\f\u0010M\u001a\u00020&*\u00020;H\u0016J\u0014\u0010N\u001a\u00020\u0006*\u00020;2\u0006\u0010(\u001a\u00020&H\u0016J\u0012\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00060**\u00020;H\u0016J\f\u0010P\u001a\u00020\u000b*\u00020;H\u0016J\u0016\u0010Q\u001a\u00020\n*\u00020;2\b\u0010R\u001a\u0004\u0018\u00010\u000bH\u0016J\u0018\u0010S\u001a\u00020\n2\u0006\u0010T\u001a\u00020\u000b2\u0006\u0010U\u001a\u00020\u000bH\u0016J\f\u0010V\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010W\u001a\u00020\n*\u00020\u0006H\u0016J\u0012\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00060>*\u00020\u0005H\u0016J\f\u0010Y\u001a\u00020\n*\u00020\u000bH\u0016J\u001a\u0010Z\u001a\u0004\u0018\u00010\u00052\u0006\u0010[\u001a\u00020\u00052\u0006\u0010\\\u001a\u00020!H\u0016J\u0012\u0010]\u001a\u0004\u0018\u00010\u00062\u0006\u0010[\u001a\u00020\u0006H\u0016J\f\u0010^\u001a\u00020_*\u00020\u0005H\u0016J\f\u0010`\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010a\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010b\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010c\u001a\u00020\n*\u00020\u0005H\u0016J\u0016\u0010d\u001a\u00020\u00062\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00060>H\u0016J\u0016\u0010d\u001a\u00020\u00142\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00140>H\u0016J\f\u0010f\u001a\u00020\n*\u00020\u0014H\u0016J\u0012\u0010g\u001a\b\u0012\u0004\u0012\u00020h0**\u00020\u0006H\u0016J\u0010\u0010i\u001a\u00020j2\u0006\u0010[\u001a\u00020\u0005H\u0016J\f\u0010k\u001a\u00020\n*\u00020\u0006H\u0016J\u001c\u0010l\u001a\u00020m2\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060oH\u0016J\b\u0010p\u001a\u00020mH\u0016J\u0014\u0010q\u001a\u00020\u0006*\u00020m2\u0006\u0010[\u001a\u00020\u0006H\u0016J\f\u0010r\u001a\u00020\n*\u00020\u0006H\u0016J\f\u0010s\u001a\u00020t*\u00020uH\u0002R\u001a\u0010I\u001a\u0004\u0018\u00010;*\u00020J8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bK\u0010L¨\u0006v"}, d2 = {"Lkotlin/reflect/jvm/internal/types/ReflectTypeSystemContext;", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeSystemContext;", "<init>", "()V", "asRigidType", "Lkotlin/reflect/jvm/internal/impl/types/model/RigidTypeMarker;", "Lkotlin/reflect/jvm/internal/impl/types/model/KotlinTypeMarker;", "asFlexibleType", "Lkotlin/reflect/jvm/internal/impl/types/model/FlexibleTypeMarker;", "isError", "", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeConstructorMarker;", "isUninferredParameter", "asDynamicType", "Lkotlin/reflect/jvm/internal/impl/types/model/DynamicTypeMarker;", "isRawType", "upperBound", "lowerBound", "asCapturedType", "Lkotlin/reflect/jvm/internal/impl/types/model/CapturedTypeMarker;", "Lkotlin/reflect/jvm/internal/impl/types/model/SimpleTypeMarker;", "asDefinitelyNotNullType", "Lkotlin/reflect/jvm/internal/impl/types/model/DefinitelyNotNullTypeMarker;", "original", "makeDefinitelyNotNullOrNotNull", "preserveAttributes", "isMarkedNullable", "withNullability", "nullable", "typeConstructor", "isOldCapturedType", "Lkotlin/reflect/jvm/internal/impl/types/model/CapturedTypeConstructorMarker;", "captureStatus", "Lkotlin/reflect/jvm/internal/impl/types/model/CaptureStatus;", "isProjectionNotNull", "projection", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeArgumentMarker;", "argumentsCount", "", "getArgument", "index", "getArguments", "", "isStubType", "isStubTypeForVariableInSubtyping", "isStubTypeForBuilderInference", "unwrapStubTypeVariableConstructor", "asTypeArgument", "lowerType", "isStarProjection", "getVariance", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeVariance;", "convertVariance", "Lkotlin/reflect/KVariance;", "getType", "replaceType", "newType", "parametersCount", "getParameter", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeParameterMarker;", "getParameters", "supertypes", "", "isIntersection", "isClassTypeConstructor", "isInterface", "isIntegerLiteralTypeConstructor", "isIntegerLiteralConstantTypeConstructor", "isIntegerConstantOperatorTypeConstructor", "isLocalType", "isAnonymous", "getTypeParameterClassifier", "isTypeParameterTypeConstructor", "typeParameter", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeVariableTypeConstructorMarker;", "getTypeParameter", "(Lorg/jetbrains/kotlin/types/model/TypeVariableTypeConstructorMarker;)Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "upperBoundCount", "getUpperBound", "getUpperBounds", "getTypeConstructor", "hasRecursiveBounds", "selfConstructor", "areEqualTypeConstructors", "c1", "c2", "isDenotable", "isNullableType", "possibleIntegerTypes", "isCommonFinalClassConstructor", "captureFromArguments", "type", NotificationCompat.CATEGORY_STATUS, "captureFromExpression", "asArgumentList", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeArgumentListMarker;", "isAnyConstructor", "isNothingConstructor", "isArrayConstructor", "isSingleClassifierType", "intersectTypes", "types", "isPrimitiveType", "getAttributes", "Lkotlin/reflect/jvm/internal/impl/types/model/AnnotationMarker;", "substitutionSupertypePolicy", "Lkotlin/reflect/jvm/internal/impl/types/TypeCheckerState$SupertypesPolicy;", "isTypeVariableType", "typeSubstitutorByTypeConstructor", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeSubstitutorMarker;", "map", "", "createEmptySubstitutor", "safeSubstitute", "isDynamic", "shouldNotBeCalled", "", "", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ReflectTypeSystemContext implements TypeSystemContext {
    public static final ReflectTypeSystemContext INSTANCE = new ReflectTypeSystemContext();

    /* JADX INFO: compiled from: ReflectTypeSystemContext.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KVariance.values().length];
            try {
                iArr[KVariance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KVariance.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KVariance.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public List<SimpleTypeMarker> default$fastCorrespondingSupertypes(RigidTypeMarker rigidTypeMarker, TypeConstructorMarker constructor) {
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        return null;
    }

    public boolean default$identicalArguments(RigidTypeMarker a, RigidTypeMarker b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return false;
    }

    public boolean default$isNotNullTypeParameter(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isDynamic(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isIntegerLiteralTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isIntersection(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isOldCapturedType(CapturedTypeMarker capturedTypeMarker) {
        Intrinsics.checkNotNullParameter(capturedTypeMarker, "<this>");
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isProjectionNotNull(CapturedTypeMarker capturedTypeMarker) {
        Intrinsics.checkNotNullParameter(capturedTypeMarker, "<this>");
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isStubType(RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isStubTypeForBuilderInference(RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return false;
    }

    private ReflectTypeSystemContext() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ CapturedTypeMarker asCapturedTypeUnwrappingDnn(RigidTypeMarker rigidTypeMarker) {
        return default$asCapturedTypeUnwrappingDnn(rigidTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ List<SimpleTypeMarker> fastCorrespondingSupertypes(RigidTypeMarker rigidTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        return default$fastCorrespondingSupertypes(rigidTypeMarker, typeConstructorMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ TypeArgumentMarker get(TypeArgumentListMarker typeArgumentListMarker, int i) {
        return default$get(typeArgumentListMarker, i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ TypeArgumentMarker getArgumentOrNull(RigidTypeMarker rigidTypeMarker, int i) {
        return default$getArgumentOrNull(rigidTypeMarker, i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ boolean hasFlexibleNullability(KotlinTypeMarker kotlinTypeMarker) {
        return default$hasFlexibleNullability(kotlinTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemOptimizationContext
    public /* bridge */ boolean identicalArguments(RigidTypeMarker rigidTypeMarker, RigidTypeMarker rigidTypeMarker2) {
        return default$identicalArguments(rigidTypeMarker, rigidTypeMarker2);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ boolean isCapturedType(KotlinTypeMarker kotlinTypeMarker) {
        return default$isCapturedType(kotlinTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ boolean isClassType(RigidTypeMarker rigidTypeMarker) {
        return default$isClassType(rigidTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ boolean isDefinitelyNotNullType(KotlinTypeMarker kotlinTypeMarker) {
        return default$isDefinitelyNotNullType(kotlinTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ boolean isDefinitelyNotNullType(RigidTypeMarker rigidTypeMarker) {
        return default$isDefinitelyNotNullType(rigidTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ boolean isFlexible(KotlinTypeMarker kotlinTypeMarker) {
        return default$isFlexible(kotlinTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ boolean isFlexibleWithDifferentTypeConstructors(KotlinTypeMarker kotlinTypeMarker) {
        return default$isFlexibleWithDifferentTypeConstructors(kotlinTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ boolean isIntegerLiteralType(RigidTypeMarker rigidTypeMarker) {
        return default$isIntegerLiteralType(rigidTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ boolean isNotNullTypeParameter(KotlinTypeMarker kotlinTypeMarker) {
        return default$isNotNullTypeParameter(kotlinTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ boolean isNothing(KotlinTypeMarker kotlinTypeMarker) {
        return default$isNothing(kotlinTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ RigidTypeMarker lowerBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker) {
        return default$lowerBoundIfFlexible(kotlinTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ KotlinTypeMarker makeDefinitelyNotNullOrNotNull(KotlinTypeMarker kotlinTypeMarker) {
        return default$makeDefinitelyNotNullOrNotNull(kotlinTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ SimpleTypeMarker originalIfDefinitelyNotNullable(RigidTypeMarker rigidTypeMarker) {
        return default$originalIfDefinitelyNotNullable(rigidTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ int size(TypeArgumentListMarker typeArgumentListMarker) {
        return default$size(typeArgumentListMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ TypeConstructorMarker typeConstructor(KotlinTypeMarker kotlinTypeMarker) {
        return default$typeConstructor(kotlinTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public /* bridge */ RigidTypeMarker upperBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker) {
        return default$upperBoundIfFlexible(kotlinTypeMarker);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public RigidTypeMarker asRigidType(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        if (asFlexibleType(kotlinTypeMarker) != null) {
            return null;
        }
        return (RigidTypeMarker) kotlinTypeMarker;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public FlexibleTypeMarker asFlexibleType(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        if (!(kotlinTypeMarker instanceof AbstractKType) || ((AbstractKType) kotlinTypeMarker).getLowerBound() == null) {
            return null;
        }
        return (FlexibleTypeMarker) kotlinTypeMarker;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isError(KotlinTypeMarker kotlinTypeMarker) {
        KotlinType type;
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        if (!(kotlinTypeMarker instanceof AbstractKType) || !(((AbstractKType) kotlinTypeMarker).getClassifier() instanceof ErrorTypeParameter)) {
            DescriptorKType descriptorKType = kotlinTypeMarker instanceof DescriptorKType ? (DescriptorKType) kotlinTypeMarker : null;
            if (descriptorKType == null || (type = descriptorKType.getType()) == null || !KotlinTypeKt.isError(type)) {
                return false;
            }
        }
        return true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public DynamicTypeMarker asDynamicType(FlexibleTypeMarker flexibleTypeMarker) {
        Intrinsics.checkNotNullParameter(flexibleTypeMarker, "<this>");
        shouldNotBeCalled(flexibleTypeMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isRawType(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        shouldNotBeCalled(kotlinTypeMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public RigidTypeMarker upperBound(FlexibleTypeMarker flexibleTypeMarker) {
        Intrinsics.checkNotNullParameter(flexibleTypeMarker, "<this>");
        AbstractKType upperBound = ((AbstractKType) flexibleTypeMarker).getUpperBound();
        Intrinsics.checkNotNull(upperBound, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.types.AbstractKType");
        return upperBound;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public RigidTypeMarker lowerBound(FlexibleTypeMarker flexibleTypeMarker) {
        Intrinsics.checkNotNullParameter(flexibleTypeMarker, "<this>");
        AbstractKType lowerBound = ((AbstractKType) flexibleTypeMarker).getLowerBound();
        Intrinsics.checkNotNull(lowerBound, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.types.AbstractKType");
        return lowerBound;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public CapturedTypeMarker asCapturedType(SimpleTypeMarker simpleTypeMarker) {
        Intrinsics.checkNotNullParameter(simpleTypeMarker, "<this>");
        if (simpleTypeMarker instanceof CapturedTypeMarker) {
            return (CapturedTypeMarker) simpleTypeMarker;
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public DefinitelyNotNullTypeMarker asDefinitelyNotNullType(RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        if ((rigidTypeMarker instanceof AbstractKType) && ((AbstractKType) rigidTypeMarker).getIsDefinitelyNotNullType()) {
            return (DefinitelyNotNullTypeMarker) rigidTypeMarker;
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker original(DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker) {
        Intrinsics.checkNotNullParameter(definitelyNotNullTypeMarker, "<this>");
        shouldNotBeCalled(definitelyNotNullTypeMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker makeDefinitelyNotNullOrNotNull(KotlinTypeMarker kotlinTypeMarker, boolean z) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        shouldNotBeCalled(kotlinTypeMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isMarkedNullable(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return ((KType) kotlinTypeMarker).getIsMarkedNullable();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public RigidTypeMarker withNullability(RigidTypeMarker rigidTypeMarker, boolean z) {
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return ((AbstractKType) rigidTypeMarker).makeNullableAsSpecified(z);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeConstructorMarker typeConstructor(RigidTypeMarker rigidTypeMarker) {
        Class<?> componentType;
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        if (rigidTypeMarker instanceof CapturedKType) {
            return ((CapturedKType) rigidTypeMarker).getTypeConstructor();
        }
        AbstractKType abstractKType = (AbstractKType) rigidTypeMarker;
        if (abstractKType.getIsNothingType()) {
            return NothingKClass.INSTANCE;
        }
        KClassifier classifier = abstractKType.getClassifier();
        KClassImpl kClassImpl = classifier instanceof KClassImpl ? (KClassImpl) classifier : null;
        if (kClassImpl != null && (componentType = JvmClassMappingKt.getJavaClass((KClass) kClassImpl).getComponentType()) != null && !componentType.isPrimitive()) {
            return (TypeConstructorMarker) Reflection.getOrCreateKotlinClass(Object[].class);
        }
        KClass<?> mutableCollectionClass = abstractKType.getMutableCollectionClass();
        KClass<?> classifier2 = mutableCollectionClass != null ? mutableCollectionClass : abstractKType.getClassifier();
        Intrinsics.checkNotNull(classifier2, "null cannot be cast to non-null type org.jetbrains.kotlin.types.model.TypeConstructorMarker");
        return (TypeConstructorMarker) classifier2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker withNullability(KotlinTypeMarker kotlinTypeMarker, boolean z) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        shouldNotBeCalled(kotlinTypeMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public CapturedTypeConstructorMarker typeConstructor(CapturedTypeMarker capturedTypeMarker) {
        Intrinsics.checkNotNullParameter(capturedTypeMarker, "<this>");
        return ((CapturedKType) capturedTypeMarker).getTypeConstructor();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public CaptureStatus captureStatus(CapturedTypeMarker capturedTypeMarker) {
        Intrinsics.checkNotNullParameter(capturedTypeMarker, "<this>");
        return CaptureStatus.FOR_SUBTYPING;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentMarker projection(CapturedTypeConstructorMarker capturedTypeConstructorMarker) {
        Intrinsics.checkNotNullParameter(capturedTypeConstructorMarker, "<this>");
        return new KTypeProjectionAsTypeArgumentMarker(((CapturedKTypeConstructor) capturedTypeConstructorMarker).getProjection());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int argumentsCount(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return ((KType) kotlinTypeMarker).getArguments().size();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentMarker getArgument(KotlinTypeMarker kotlinTypeMarker, int i) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return new KTypeProjectionAsTypeArgumentMarker(((KType) kotlinTypeMarker).getArguments().get(i));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public List<TypeArgumentMarker> getArguments(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        shouldNotBeCalled(kotlinTypeMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentMarker asTypeArgument(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        shouldNotBeCalled(kotlinTypeMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker lowerType(CapturedTypeMarker capturedTypeMarker) {
        Intrinsics.checkNotNullParameter(capturedTypeMarker, "<this>");
        return (KotlinTypeMarker) ((CapturedKType) capturedTypeMarker).getLowerType();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isStarProjection(TypeArgumentMarker typeArgumentMarker) {
        Intrinsics.checkNotNullParameter(typeArgumentMarker, "<this>");
        return Intrinsics.areEqual(((KTypeProjectionAsTypeArgumentMarker) typeArgumentMarker).getValue(), KTypeProjection.INSTANCE.getSTAR());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeVariance getVariance(TypeArgumentMarker typeArgumentMarker) {
        TypeVariance typeVarianceConvertVariance;
        Intrinsics.checkNotNullParameter(typeArgumentMarker, "<this>");
        KVariance variance = ((KTypeProjectionAsTypeArgumentMarker) typeArgumentMarker).getValue().getVariance();
        return (variance == null || (typeVarianceConvertVariance = convertVariance(variance)) == null) ? TypeVariance.OUT : typeVarianceConvertVariance;
    }

    private final TypeVariance convertVariance(KVariance kVariance) {
        int i = WhenMappings.$EnumSwitchMapping$0[kVariance.ordinal()];
        if (i == 1) {
            return TypeVariance.INV;
        }
        if (i == 2) {
            return TypeVariance.IN;
        }
        if (i != 3) {
            throw new NoWhenBranchMatchedException();
        }
        return TypeVariance.OUT;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker getType(TypeArgumentMarker typeArgumentMarker) {
        Intrinsics.checkNotNullParameter(typeArgumentMarker, "<this>");
        return (KotlinTypeMarker) ((KTypeProjectionAsTypeArgumentMarker) typeArgumentMarker).getValue().getType();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int parametersCount(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        if (typeConstructorMarker instanceof KClass) {
            return CapturedKTypeKt.allTypeParameters((KClass) typeConstructorMarker).size();
        }
        return 0;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeParameterMarker getParameter(TypeConstructorMarker typeConstructorMarker, int i) {
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        KTypeParameter kTypeParameter = CapturedKTypeKt.allTypeParameters((KClass) typeConstructorMarker).get(i);
        Intrinsics.checkNotNull(kTypeParameter, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeParameterImpl");
        return (KTypeParameterImpl) kTypeParameter;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public List<TypeParameterMarker> getParameters(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        shouldNotBeCalled(typeConstructorMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public Collection<KotlinTypeMarker> supertypes(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        if (!(typeConstructorMarker instanceof KClass)) {
            if (!(typeConstructorMarker instanceof KTypeParameter)) {
                if (!(typeConstructorMarker instanceof CapturedKTypeConstructor)) {
                    throw new IllegalStateException(("Unsupported type constructor: " + typeConstructorMarker + " (" + typeConstructorMarker.getClass().getName() + ')').toString());
                }
                List<KType> supertypes = ((CapturedKTypeConstructor) typeConstructorMarker).getSupertypes();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(supertypes, 10));
                for (KType kType : supertypes) {
                    Intrinsics.checkNotNull(kType, "null cannot be cast to non-null type org.jetbrains.kotlin.types.model.KotlinTypeMarker");
                    arrayList.add((KotlinTypeMarker) kType);
                }
                return arrayList;
            }
            List<KType> upperBounds = ((KTypeParameter) typeConstructorMarker).getUpperBounds();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(upperBounds, 10));
            for (KType kType2 : upperBounds) {
                Intrinsics.checkNotNull(kType2, "null cannot be cast to non-null type org.jetbrains.kotlin.types.model.KotlinTypeMarker");
                arrayList2.add((KotlinTypeMarker) kType2);
            }
            return arrayList2;
        }
        List<KType> supertypes2 = ((KClass) typeConstructorMarker).getSupertypes();
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(supertypes2, 10));
        for (KType kType3 : supertypes2) {
            Intrinsics.checkNotNull(kType3, "null cannot be cast to non-null type org.jetbrains.kotlin.types.model.KotlinTypeMarker");
            arrayList3.add((KotlinTypeMarker) kType3);
        }
        return arrayList3;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isClassTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return typeConstructorMarker instanceof KClass;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeParameterMarker getTypeParameterClassifier(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        shouldNotBeCalled(typeConstructorMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeParameterMarker getTypeParameter(TypeVariableTypeConstructorMarker typeVariableTypeConstructorMarker) {
        Intrinsics.checkNotNullParameter(typeVariableTypeConstructorMarker, "<this>");
        shouldNotBeCalled(typeVariableTypeConstructorMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeVariance getVariance(TypeParameterMarker typeParameterMarker) {
        Intrinsics.checkNotNullParameter(typeParameterMarker, "<this>");
        return convertVariance(((KTypeParameter) typeParameterMarker).getVariance());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public List<KotlinTypeMarker> getUpperBounds(TypeParameterMarker typeParameterMarker) {
        Intrinsics.checkNotNullParameter(typeParameterMarker, "<this>");
        shouldNotBeCalled(typeParameterMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeConstructorMarker getTypeConstructor(TypeParameterMarker typeParameterMarker) {
        Intrinsics.checkNotNullParameter(typeParameterMarker, "<this>");
        shouldNotBeCalled(typeParameterMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean hasRecursiveBounds(TypeParameterMarker typeParameterMarker, TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(typeParameterMarker, "<this>");
        shouldNotBeCalled(typeParameterMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean areEqualTypeConstructors(TypeConstructorMarker c1, TypeConstructorMarker c2) {
        Intrinsics.checkNotNullParameter(c1, "c1");
        Intrinsics.checkNotNullParameter(c2, "c2");
        return Intrinsics.areEqual(c1, c2);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isDenotable(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return !(typeConstructorMarker instanceof CapturedKTypeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isNullableType(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        shouldNotBeCalled(kotlinTypeMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public Collection<KotlinTypeMarker> possibleIntegerTypes(RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        shouldNotBeCalled(rigidTypeMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isCommonFinalClassConstructor(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        if (!(typeConstructorMarker instanceof KClassImpl)) {
            return false;
        }
        KClassImpl kClassImpl = (KClassImpl) typeConstructorMarker;
        return (!kClassImpl.isFinal() || kClassImpl.getClassKind$kotlin_reflection() == ClassKind.ENUM_CLASS || kClassImpl.getClassKind$kotlin_reflection() == ClassKind.ENUM_ENTRY || kClassImpl.getClassKind$kotlin_reflection() == ClassKind.ANNOTATION_CLASS) ? false : true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public RigidTypeMarker captureFromArguments(RigidTypeMarker type, CaptureStatus status) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(status, "status");
        return (AbstractKType) CapturedKTypeKt.captureKTypeFromArguments((KType) type);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentListMarker asArgumentList(RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return (TypeArgumentListMarker) rigidTypeMarker;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isAnyConstructor(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return Intrinsics.areEqual(typeConstructorMarker, Reflection.getOrCreateKotlinClass(Object.class));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isNothingConstructor(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return Intrinsics.areEqual(typeConstructorMarker, NothingKClass.INSTANCE);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isSingleClassifierType(RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        shouldNotBeCalled(rigidTypeMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker intersectTypes(Collection<? extends KotlinTypeMarker> types) {
        Intrinsics.checkNotNullParameter(types, "types");
        shouldNotBeCalled(this);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isPrimitiveType(SimpleTypeMarker simpleTypeMarker) {
        Intrinsics.checkNotNullParameter(simpleTypeMarker, "<this>");
        shouldNotBeCalled(simpleTypeMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeCheckerState.SupertypesPolicy substitutionSupertypePolicy(RigidTypeMarker type) {
        Intrinsics.checkNotNullParameter(type, "type");
        final KTypeSubstitutor kTypeSubstitutorCreate = KTypeSubstitutor.INSTANCE.create((KType) type);
        return new TypeCheckerState.SupertypesPolicy.DoCustomTransform() { // from class: kotlin.reflect.jvm.internal.types.ReflectTypeSystemContext.substitutionSupertypePolicy.1
            @Override // kotlin.reflect.jvm.internal.impl.types.TypeCheckerState.SupertypesPolicy
            /* JADX INFO: renamed from: transformType */
            public RigidTypeMarker mo10250transformType(TypeCheckerState state, KotlinTypeMarker type2) {
                Intrinsics.checkNotNullParameter(state, "state");
                Intrinsics.checkNotNullParameter(type2, "type");
                KTypeSubstitutor kTypeSubstitutor = kTypeSubstitutorCreate;
                RigidTypeMarker rigidTypeMarkerLowerBoundIfFlexible = ReflectTypeSystemContext.INSTANCE.lowerBoundIfFlexible(type2);
                Intrinsics.checkNotNull(rigidTypeMarkerLowerBoundIfFlexible, "null cannot be cast to non-null type kotlin.reflect.KType");
                KType type3 = KTypeSubstitutor.substitute$default(kTypeSubstitutor, (KType) rigidTypeMarkerLowerBoundIfFlexible, null, 2, null).getType();
                Intrinsics.checkNotNull(type3, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.types.AbstractKType");
                return (AbstractKType) type3;
            }
        };
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isTypeVariableType(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        shouldNotBeCalled(kotlinTypeMarker);
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker safeSubstitute(TypeSubstitutorMarker typeSubstitutorMarker, KotlinTypeMarker type) {
        Intrinsics.checkNotNullParameter(typeSubstitutorMarker, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        shouldNotBeCalled(typeSubstitutorMarker);
        throw new KotlinNothingValueException();
    }

    private final Void shouldNotBeCalled(Object obj) {
        throw new KotlinReflectionInternalError("This method should not be called on " + obj + " with a new kotlin-reflect implementation. Please file an issue at https://kotl.in/issue");
    }

    public CapturedTypeMarker default$asCapturedTypeUnwrappingDnn(RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return asCapturedType(originalIfDefinitelyNotNullable(rigidTypeMarker));
    }

    public boolean default$isCapturedType(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        RigidTypeMarker rigidTypeMarkerAsRigidType = asRigidType(kotlinTypeMarker);
        return (rigidTypeMarkerAsRigidType != null ? asCapturedTypeUnwrappingDnn(rigidTypeMarkerAsRigidType) : null) != null;
    }

    public SimpleTypeMarker default$originalIfDefinitelyNotNullable(RigidTypeMarker rigidTypeMarker) {
        SimpleTypeMarker simpleTypeMarkerOriginal;
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        DefinitelyNotNullTypeMarker definitelyNotNullTypeMarkerAsDefinitelyNotNullType = asDefinitelyNotNullType(rigidTypeMarker);
        return (definitelyNotNullTypeMarkerAsDefinitelyNotNullType == null || (simpleTypeMarkerOriginal = original(definitelyNotNullTypeMarkerAsDefinitelyNotNullType)) == null) ? (SimpleTypeMarker) rigidTypeMarker : simpleTypeMarkerOriginal;
    }

    public KotlinTypeMarker default$makeDefinitelyNotNullOrNotNull(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return makeDefinitelyNotNullOrNotNull(kotlinTypeMarker, false);
    }

    public TypeArgumentMarker default$getArgumentOrNull(RigidTypeMarker rigidTypeMarker, int i) {
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        if (i < 0 || i >= argumentsCount(rigidTypeMarker)) {
            return null;
        }
        return getArgument(rigidTypeMarker, i);
    }

    public RigidTypeMarker default$lowerBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker) {
        RigidTypeMarker rigidTypeMarkerLowerBound;
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        FlexibleTypeMarker flexibleTypeMarkerAsFlexibleType = asFlexibleType(kotlinTypeMarker);
        if (flexibleTypeMarkerAsFlexibleType != null && (rigidTypeMarkerLowerBound = lowerBound(flexibleTypeMarkerAsFlexibleType)) != null) {
            return rigidTypeMarkerLowerBound;
        }
        RigidTypeMarker rigidTypeMarkerAsRigidType = asRigidType(kotlinTypeMarker);
        Intrinsics.checkNotNull(rigidTypeMarkerAsRigidType);
        return rigidTypeMarkerAsRigidType;
    }

    public RigidTypeMarker default$upperBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker) {
        RigidTypeMarker rigidTypeMarkerUpperBound;
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        FlexibleTypeMarker flexibleTypeMarkerAsFlexibleType = asFlexibleType(kotlinTypeMarker);
        if (flexibleTypeMarkerAsFlexibleType != null && (rigidTypeMarkerUpperBound = upperBound(flexibleTypeMarkerAsFlexibleType)) != null) {
            return rigidTypeMarkerUpperBound;
        }
        RigidTypeMarker rigidTypeMarkerAsRigidType = asRigidType(kotlinTypeMarker);
        Intrinsics.checkNotNull(rigidTypeMarkerAsRigidType);
        return rigidTypeMarkerAsRigidType;
    }

    public boolean default$isFlexibleWithDifferentTypeConstructors(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return !Intrinsics.areEqual(typeConstructor(lowerBoundIfFlexible(kotlinTypeMarker)), typeConstructor(upperBoundIfFlexible(kotlinTypeMarker)));
    }

    public boolean default$isFlexible(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return asFlexibleType(kotlinTypeMarker) != null;
    }

    public boolean default$isDefinitelyNotNullType(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        RigidTypeMarker rigidTypeMarkerAsRigidType = asRigidType(kotlinTypeMarker);
        return (rigidTypeMarkerAsRigidType != null ? asDefinitelyNotNullType(rigidTypeMarkerAsRigidType) : null) != null;
    }

    public boolean default$isDefinitelyNotNullType(RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return asDefinitelyNotNullType(rigidTypeMarker) != null;
    }

    public boolean default$hasFlexibleNullability(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return isMarkedNullable(lowerBoundIfFlexible(kotlinTypeMarker)) != isMarkedNullable(upperBoundIfFlexible(kotlinTypeMarker));
    }

    public TypeConstructorMarker default$typeConstructor(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        RigidTypeMarker rigidTypeMarkerAsRigidType = asRigidType(kotlinTypeMarker);
        if (rigidTypeMarkerAsRigidType == null) {
            rigidTypeMarkerAsRigidType = lowerBoundIfFlexible(kotlinTypeMarker);
        }
        return typeConstructor(rigidTypeMarkerAsRigidType);
    }

    public boolean default$isNothing(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return isNothingConstructor(typeConstructor(kotlinTypeMarker)) && !isNullableType(kotlinTypeMarker);
    }

    public boolean default$isClassType(RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return isClassTypeConstructor(typeConstructor(rigidTypeMarker));
    }

    public boolean default$isIntegerLiteralType(RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return isIntegerLiteralTypeConstructor(typeConstructor(rigidTypeMarker));
    }

    public TypeArgumentMarker default$get(TypeArgumentListMarker typeArgumentListMarker, int i) {
        Intrinsics.checkNotNullParameter(typeArgumentListMarker, "<this>");
        if (typeArgumentListMarker instanceof SimpleTypeMarker) {
            return getArgument((KotlinTypeMarker) typeArgumentListMarker, i);
        }
        if (typeArgumentListMarker instanceof ArgumentList) {
            TypeArgumentMarker typeArgumentMarker = ((ArgumentList) typeArgumentListMarker).get(i);
            Intrinsics.checkNotNullExpressionValue(typeArgumentMarker, "get(...)");
            return typeArgumentMarker;
        }
        throw new IllegalStateException(("unknown type argument list type: " + typeArgumentListMarker + ", " + Reflection.getOrCreateKotlinClass(typeArgumentListMarker.getClass())).toString());
    }

    public int default$size(TypeArgumentListMarker typeArgumentListMarker) {
        Intrinsics.checkNotNullParameter(typeArgumentListMarker, "<this>");
        if (typeArgumentListMarker instanceof RigidTypeMarker) {
            return argumentsCount((KotlinTypeMarker) typeArgumentListMarker);
        }
        if (typeArgumentListMarker instanceof ArgumentList) {
            return ((ArgumentList) typeArgumentListMarker).size();
        }
        throw new IllegalStateException(("unknown type argument list type: " + typeArgumentListMarker + ", " + Reflection.getOrCreateKotlinClass(typeArgumentListMarker.getClass())).toString());
    }
}
