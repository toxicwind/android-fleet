package kotlin.reflect.jvm.internal.impl.types.model;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TypeSystemContextContextual.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class TypeSystemContextContextualKt {
    public static final RigidTypeMarker asRigidType(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.asRigidType(kotlinTypeMarker);
    }

    public static final FlexibleTypeMarker asFlexibleType(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.asFlexibleType(kotlinTypeMarker);
    }

    public static final boolean isError(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.isError(kotlinTypeMarker);
    }

    public static final RigidTypeMarker lowerBound(TypeSystemContext c, FlexibleTypeMarker flexibleTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(flexibleTypeMarker, "<this>");
        return c.lowerBound(flexibleTypeMarker);
    }

    public static final CapturedTypeMarker asCapturedTypeUnwrappingDnn(TypeSystemContext c, RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return c.asCapturedTypeUnwrappingDnn(rigidTypeMarker);
    }

    public static final boolean isCapturedType(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.isCapturedType(kotlinTypeMarker);
    }

    public static final SimpleTypeMarker originalIfDefinitelyNotNullable(TypeSystemContext c, RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return c.originalIfDefinitelyNotNullable(rigidTypeMarker);
    }

    public static final KotlinTypeMarker makeDefinitelyNotNullOrNotNull(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.makeDefinitelyNotNullOrNotNull(kotlinTypeMarker);
    }

    public static final boolean isMarkedNullable(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.isMarkedNullable(kotlinTypeMarker);
    }

    public static final RigidTypeMarker withNullability(TypeSystemContext c, RigidTypeMarker rigidTypeMarker, boolean z) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return c.withNullability(rigidTypeMarker, z);
    }

    public static final TypeConstructorMarker typeConstructor(TypeSystemContext c, RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return c.typeConstructor(rigidTypeMarker);
    }

    public static final KotlinTypeMarker withNullability(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker, boolean z) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.withNullability(kotlinTypeMarker, z);
    }

    public static final boolean isOldCapturedType(TypeSystemContext c, CapturedTypeMarker capturedTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(capturedTypeMarker, "<this>");
        return c.isOldCapturedType(capturedTypeMarker);
    }

    public static final CapturedTypeConstructorMarker typeConstructor(TypeSystemContext c, CapturedTypeMarker capturedTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(capturedTypeMarker, "<this>");
        return c.typeConstructor(capturedTypeMarker);
    }

    public static final CaptureStatus captureStatus(TypeSystemContext c, CapturedTypeMarker capturedTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(capturedTypeMarker, "<this>");
        return c.captureStatus(capturedTypeMarker);
    }

    public static final TypeArgumentMarker projection(TypeSystemContext c, CapturedTypeConstructorMarker capturedTypeConstructorMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(capturedTypeConstructorMarker, "<this>");
        return c.projection(capturedTypeConstructorMarker);
    }

    public static final int argumentsCount(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.argumentsCount(kotlinTypeMarker);
    }

    public static final TypeArgumentMarker getArgument(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker, int i) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.getArgument(kotlinTypeMarker, i);
    }

    public static final TypeArgumentMarker getArgumentOrNull(TypeSystemContext c, RigidTypeMarker rigidTypeMarker, int i) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return c.getArgumentOrNull(rigidTypeMarker, i);
    }

    public static final boolean isStubType(TypeSystemContext c, RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return c.isStubType(rigidTypeMarker);
    }

    public static final boolean isStubTypeForBuilderInference(TypeSystemContext c, RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return c.isStubTypeForBuilderInference(rigidTypeMarker);
    }

    public static final TypeArgumentMarker asTypeArgument(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.asTypeArgument(kotlinTypeMarker);
    }

    public static final KotlinTypeMarker lowerType(TypeSystemContext c, CapturedTypeMarker capturedTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(capturedTypeMarker, "<this>");
        return c.lowerType(capturedTypeMarker);
    }

    public static final boolean isStarProjection(TypeSystemContext c, TypeArgumentMarker typeArgumentMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeArgumentMarker, "<this>");
        return c.isStarProjection(typeArgumentMarker);
    }

    public static final TypeVariance getVariance(TypeSystemContext c, TypeArgumentMarker typeArgumentMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeArgumentMarker, "<this>");
        return c.getVariance(typeArgumentMarker);
    }

    public static final KotlinTypeMarker getType(TypeSystemContext c, TypeArgumentMarker typeArgumentMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeArgumentMarker, "<this>");
        return c.getType(typeArgumentMarker);
    }

    public static final int parametersCount(TypeSystemContext c, TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return c.parametersCount(typeConstructorMarker);
    }

    public static final TypeParameterMarker getParameter(TypeSystemContext c, TypeConstructorMarker typeConstructorMarker, int i) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return c.getParameter(typeConstructorMarker, i);
    }

    public static final Collection<KotlinTypeMarker> supertypes(TypeSystemContext c, TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return c.supertypes(typeConstructorMarker);
    }

    public static final boolean isIntersection(TypeSystemContext c, TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return c.isIntersection(typeConstructorMarker);
    }

    public static final boolean isClassTypeConstructor(TypeSystemContext c, TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return c.isClassTypeConstructor(typeConstructorMarker);
    }

    public static final boolean isIntegerLiteralTypeConstructor(TypeSystemContext c, TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return c.isIntegerLiteralTypeConstructor(typeConstructorMarker);
    }

    public static final TypeParameterMarker getTypeParameter(TypeSystemContext c, TypeVariableTypeConstructorMarker typeVariableTypeConstructorMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeVariableTypeConstructorMarker, "<this>");
        return c.getTypeParameter(typeVariableTypeConstructorMarker);
    }

    public static final TypeVariance getVariance(TypeSystemContext c, TypeParameterMarker typeParameterMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeParameterMarker, "<this>");
        return c.getVariance(typeParameterMarker);
    }

    public static final boolean hasRecursiveBounds(TypeSystemContext c, TypeParameterMarker typeParameterMarker, TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeParameterMarker, "<this>");
        return c.hasRecursiveBounds(typeParameterMarker, typeConstructorMarker);
    }

    public static final boolean isDenotable(TypeSystemContext c, TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return c.isDenotable(typeConstructorMarker);
    }

    public static final RigidTypeMarker lowerBoundIfFlexible(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.lowerBoundIfFlexible(kotlinTypeMarker);
    }

    public static final RigidTypeMarker upperBoundIfFlexible(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.upperBoundIfFlexible(kotlinTypeMarker);
    }

    public static final boolean isFlexibleWithDifferentTypeConstructors(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.isFlexibleWithDifferentTypeConstructors(kotlinTypeMarker);
    }

    public static final boolean isFlexible(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.isFlexible(kotlinTypeMarker);
    }

    public static final boolean isDynamic(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.isDynamic(kotlinTypeMarker);
    }

    public static final boolean isDefinitelyNotNullType(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.isDefinitelyNotNullType(kotlinTypeMarker);
    }

    public static final boolean isDefinitelyNotNullType(TypeSystemContext c, RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return c.isDefinitelyNotNullType(rigidTypeMarker);
    }

    public static final boolean isNotNullTypeParameter(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.isNotNullTypeParameter(kotlinTypeMarker);
    }

    public static final TypeConstructorMarker typeConstructor(TypeSystemContext c, KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return c.typeConstructor(kotlinTypeMarker);
    }

    public static final boolean isClassType(TypeSystemContext c, RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return c.isClassType(rigidTypeMarker);
    }

    public static final List<SimpleTypeMarker> fastCorrespondingSupertypes(TypeSystemContext c, RigidTypeMarker rigidTypeMarker, TypeConstructorMarker constructor) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        return c.fastCorrespondingSupertypes(rigidTypeMarker, constructor);
    }

    public static final boolean isIntegerLiteralType(TypeSystemContext c, RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return c.isIntegerLiteralType(rigidTypeMarker);
    }

    public static final Collection<KotlinTypeMarker> possibleIntegerTypes(TypeSystemContext c, RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return c.possibleIntegerTypes(rigidTypeMarker);
    }

    public static final boolean isCommonFinalClassConstructor(TypeSystemContext c, TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return c.isCommonFinalClassConstructor(typeConstructorMarker);
    }

    public static final TypeArgumentListMarker asArgumentList(TypeSystemContext c, RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return c.asArgumentList(rigidTypeMarker);
    }

    public static final TypeArgumentMarker get(TypeSystemContext c, TypeArgumentListMarker typeArgumentListMarker, int i) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeArgumentListMarker, "<this>");
        return c.get(typeArgumentListMarker, i);
    }

    public static final int size(TypeSystemContext c, TypeArgumentListMarker typeArgumentListMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeArgumentListMarker, "<this>");
        return c.size(typeArgumentListMarker);
    }

    public static final boolean isAnyConstructor(TypeSystemContext c, TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return c.isAnyConstructor(typeConstructorMarker);
    }

    public static final boolean isNothingConstructor(TypeSystemContext c, TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeConstructorMarker, "<this>");
        return c.isNothingConstructor(typeConstructorMarker);
    }

    public static final boolean isSingleClassifierType(TypeSystemContext c, RigidTypeMarker rigidTypeMarker) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(rigidTypeMarker, "<this>");
        return c.isSingleClassifierType(rigidTypeMarker);
    }
}
