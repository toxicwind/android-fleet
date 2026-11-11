package kotlin.reflect.jvm.internal.impl.km.jvm;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.km.KmAnnotation;
import kotlin.reflect.jvm.internal.impl.km.KmClass;
import kotlin.reflect.jvm.internal.impl.km.KmConstructor;
import kotlin.reflect.jvm.internal.impl.km.KmFunction;
import kotlin.reflect.jvm.internal.impl.km.KmPackage;
import kotlin.reflect.jvm.internal.impl.km.KmProperty;
import kotlin.reflect.jvm.internal.impl.km.KmType;
import kotlin.reflect.jvm.internal.impl.km.jvm.internal.JvmExtensionNodesKt;

/* JADX INFO: compiled from: JvmExtensions.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class JvmExtensionsKt {
    public static final List<KmProperty> getLocalDelegatedProperties(KmClass kmClass) {
        Intrinsics.checkNotNullParameter(kmClass, "<this>");
        return JvmExtensionNodesKt.getJvm(kmClass).getLocalDelegatedProperties();
    }

    public static final String getModuleName(KmClass kmClass) {
        Intrinsics.checkNotNullParameter(kmClass, "<this>");
        return JvmExtensionNodesKt.getJvm(kmClass).getModuleName();
    }

    public static final List<KmProperty> getLocalDelegatedProperties(KmPackage kmPackage) {
        Intrinsics.checkNotNullParameter(kmPackage, "<this>");
        return JvmExtensionNodesKt.getJvm(kmPackage).getLocalDelegatedProperties();
    }

    public static final JvmMethodSignature getSignature(KmFunction kmFunction) {
        Intrinsics.checkNotNullParameter(kmFunction, "<this>");
        return JvmExtensionNodesKt.getJvm(kmFunction).getSignature();
    }

    public static final JvmFieldSignature getFieldSignature(KmProperty kmProperty) {
        Intrinsics.checkNotNullParameter(kmProperty, "<this>");
        return JvmExtensionNodesKt.getJvm(kmProperty).getFieldSignature();
    }

    public static final JvmMethodSignature getGetterSignature(KmProperty kmProperty) {
        Intrinsics.checkNotNullParameter(kmProperty, "<this>");
        return JvmExtensionNodesKt.getJvm(kmProperty).getGetterSignature();
    }

    public static final JvmMethodSignature getSetterSignature(KmProperty kmProperty) {
        Intrinsics.checkNotNullParameter(kmProperty, "<this>");
        return JvmExtensionNodesKt.getJvm(kmProperty).getSetterSignature();
    }

    public static final JvmMethodSignature getSyntheticMethodForAnnotations(KmProperty kmProperty) {
        Intrinsics.checkNotNullParameter(kmProperty, "<this>");
        return JvmExtensionNodesKt.getJvm(kmProperty).getSyntheticMethodForAnnotations();
    }

    public static final JvmMethodSignature getSyntheticMethodForDelegate(KmProperty kmProperty) {
        Intrinsics.checkNotNullParameter(kmProperty, "<this>");
        return JvmExtensionNodesKt.getJvm(kmProperty).getSyntheticMethodForDelegate();
    }

    public static final JvmMethodSignature getSignature(KmConstructor kmConstructor) {
        Intrinsics.checkNotNullParameter(kmConstructor, "<this>");
        return JvmExtensionNodesKt.getJvm(kmConstructor).getSignature();
    }

    public static final boolean isRaw(KmType kmType) {
        Intrinsics.checkNotNullParameter(kmType, "<this>");
        return JvmExtensionNodesKt.getJvm(kmType).isRaw();
    }

    public static final List<KmAnnotation> getAnnotations(KmType kmType) {
        Intrinsics.checkNotNullParameter(kmType, "<this>");
        return JvmExtensionNodesKt.getJvm(kmType).getAnnotations();
    }
}
