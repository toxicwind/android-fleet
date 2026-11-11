package kotlin.reflect.jvm.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KParameter;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.calls.InternalUnderlyingValOfInlineClass;
import kotlin.reflect.jvm.internal.calls.ThrowingCaller;
import kotlin.reflect.jvm.internal.calls.ValueClassAwareCallerKt;
import kotlin.reflect.jvm.internal.impl.km.ClassKind;
import kotlin.reflect.jvm.internal.impl.km.KmProperty;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmAttributes;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmExtensionsKt;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmMethodSignature;

/* JADX INFO: compiled from: KotlinKProperty.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u0010\u0010\t\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\nH\u0002\"\"\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u000b"}, d2 = {"boundReceiver", "", "Lkotlin/reflect/jvm/internal/KotlinKProperty$Accessor;", "getBoundReceiver", "(Lkotlin/reflect/jvm/internal/KotlinKProperty$Accessor;)Ljava/lang/Object;", "computeCallerForAccessor", "Lkotlin/reflect/jvm/internal/calls/Caller;", "isGetter", "", "isJvmFieldPropertyInCompanionObject", "Lkotlin/reflect/jvm/internal/KotlinKProperty;", "kotlin-reflection"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class KotlinKPropertyKt {
    public static final Object getBoundReceiver(KotlinKProperty.Accessor<?, ?> accessor) {
        Intrinsics.checkNotNullParameter(accessor, "<this>");
        return ReflectKCallableKt.getBoundReceiver(accessor.getProperty());
    }

    public static final Caller<?> computeCallerForAccessor(KotlinKProperty.Accessor<?, ?> accessor, boolean z) {
        Caller boundStatic;
        Method inlineClassUnboxMethod;
        Intrinsics.checkNotNullParameter(accessor, "<this>");
        KotlinKProperty<?> property = accessor.getProperty();
        KotlinKProperty<?> kotlinKProperty = property;
        if (ReflectKPropertyKt.isLocalDelegated(kotlinKProperty)) {
            return ThrowingCaller.INSTANCE;
        }
        KmProperty kmProperty = property.getKmProperty();
        JvmMethodSignature getterSignature = z ? JvmExtensionsKt.getGetterSignature(kmProperty) : JvmExtensionsKt.getSetterSignature(kmProperty);
        Method methodFindMethodBySignature = getterSignature != null ? property.getContainer().findMethodBySignature(getterSignature.getName(), getterSignature.getDescriptor()) : null;
        if (methodFindMethodBySignature == null) {
            if (ValueClassAwareCallerKt.isUnderlyingPropertyOfValueClass(kotlinKProperty) && property.getVisibility() == KVisibility.INTERNAL) {
                Class<?> inlineClass = ValueClassAwareCallerKt.toInlineClass(((KParameter) CollectionsKt.single((List) property.getParameters())).getType());
                if (inlineClass == null || (inlineClassUnboxMethod = ValueClassAwareCallerKt.getInlineClassUnboxMethod(inlineClass, property)) == null) {
                    throw new KotlinReflectionInternalError("Underlying property of inline class " + property + " should have a field");
                }
                boundStatic = ReflectKCallableKt.isBound(accessor) ? new InternalUnderlyingValOfInlineClass.Bound(inlineClassUnboxMethod, getBoundReceiver(accessor)) : new InternalUnderlyingValOfInlineClass.Unbound(inlineClassUnboxMethod);
            } else {
                Field javaField = property.getJavaField();
                if (javaField == null) {
                    throw new KotlinReflectionInternalError("No accessors or field is found for property " + property);
                }
                boundStatic = computeCallerForAccessor$computeFieldCaller(property, z, accessor, javaField);
            }
        } else if (!Modifier.isStatic(methodFindMethodBySignature.getModifiers())) {
            boundStatic = ReflectKCallableKt.isBound(accessor) ? new CallerImpl.Method.BoundInstance(methodFindMethodBySignature, getBoundReceiver(accessor)) : new CallerImpl.Method.Instance(methodFindMethodBySignature);
        } else if (computeCallerForAccessor$isJvmStaticProperty(accessor)) {
            boundStatic = ReflectKCallableKt.isBound(accessor) ? new CallerImpl.Method.BoundJvmStaticInObject(methodFindMethodBySignature) : new CallerImpl.Method.JvmStaticInObject(methodFindMethodBySignature);
        } else {
            boundStatic = ReflectKCallableKt.isBound(accessor) ? new CallerImpl.Method.BoundStatic(methodFindMethodBySignature, false, getBoundReceiver(accessor)) : new CallerImpl.Method.Static(methodFindMethodBySignature);
        }
        return ValueClassAwareCallerKt.createValueClassAwareCallerIfNeeded(boundStatic, accessor, false, CollectionsKt.emptyList());
    }

    private static final boolean computeCallerForAccessor$isJvmStaticProperty(KotlinKProperty.Accessor<?, ?> accessor) {
        if (accessor.getContainer() instanceof KPackageImpl) {
            return false;
        }
        throw new IllegalArgumentException(("Only top-level properties are supported for now: " + accessor).toString());
    }

    private static final boolean computeCallerForAccessor$isNotNullProperty(KotlinKProperty<? extends Object> kotlinKProperty) {
        return !UtilKt.isNullableType(kotlinKProperty.getReturnType());
    }

    private static final CallerImpl<Field> computeCallerForAccessor$computeFieldCaller(KotlinKProperty<? extends Object> kotlinKProperty, boolean z, KotlinKProperty.Accessor<?, ?> accessor, Field field) {
        if (isJvmFieldPropertyInCompanionObject(kotlinKProperty) || !Modifier.isStatic(field.getModifiers())) {
            if (z) {
                return ReflectKCallableKt.isBound(accessor) ? new CallerImpl.FieldGetter.BoundInstance(field, getBoundReceiver(accessor)) : new CallerImpl.FieldGetter.Instance(field);
            }
            return ReflectKCallableKt.isBound(accessor) ? new CallerImpl.FieldSetter.BoundInstance(field, computeCallerForAccessor$isNotNullProperty(kotlinKProperty), getBoundReceiver(accessor)) : new CallerImpl.FieldSetter.Instance(field, computeCallerForAccessor$isNotNullProperty(kotlinKProperty));
        }
        if (computeCallerForAccessor$isJvmStaticProperty(accessor)) {
            if (z) {
                return ReflectKCallableKt.isBound(accessor) ? new CallerImpl.FieldGetter.BoundJvmStaticInObject(field) : new CallerImpl.FieldGetter.JvmStaticInObject(field);
            }
            return ReflectKCallableKt.isBound(accessor) ? new CallerImpl.FieldSetter.BoundJvmStaticInObject(field, computeCallerForAccessor$isNotNullProperty(kotlinKProperty)) : new CallerImpl.FieldSetter.JvmStaticInObject(field, computeCallerForAccessor$isNotNullProperty(kotlinKProperty));
        }
        if (z) {
            return new CallerImpl.FieldGetter.Static(field);
        }
        return new CallerImpl.FieldSetter.Static(field, computeCallerForAccessor$isNotNullProperty(kotlinKProperty));
    }

    private static final boolean isJvmFieldPropertyInCompanionObject(KotlinKProperty<?> kotlinKProperty) {
        ClassBasedDeclarationContainer container = kotlinKProperty.getContainer();
        if (!(container instanceof KClassImpl) || ((KClassImpl) container).getClassKind$kotlin_reflection() != ClassKind.COMPANION_OBJECT) {
            return false;
        }
        Class<?> enclosingClass = JvmClassMappingKt.getJavaClass((KClass) container).getEnclosingClass();
        Intrinsics.checkNotNullExpressionValue(enclosingClass, "getEnclosingClass(...)");
        KClass kotlinClass = JvmClassMappingKt.getKotlinClass(enclosingClass);
        KClassImpl kClassImpl = kotlinClass instanceof KClassImpl ? (KClassImpl) kotlinClass : null;
        if (kClassImpl == null) {
            return false;
        }
        if (kClassImpl.getClassKind$kotlin_reflection() == ClassKind.INTERFACE || kClassImpl.getClassKind$kotlin_reflection() == ClassKind.ANNOTATION_CLASS) {
            return JvmAttributes.isMovedFromInterfaceCompanion(kotlinKProperty.getKmProperty());
        }
        return true;
    }
}
