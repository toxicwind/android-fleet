package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.lang.reflect.Method;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: KotlinNamesAnnotationIntrospector.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0003¨\u0006\u0003"}, d2 = {"isInlineClass", "", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMethod;", "jackson-module-kotlin"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class KotlinNamesAnnotationIntrospectorKt {
    @Deprecated(message = "To be removed in 2.14", replaceWith = @ReplaceWith(expression = "with(receiver) { declaringClass.declaredMethods.any { it.name.contains('-') } }", imports = {}))
    private static final boolean isInlineClass(AnnotatedMethod annotatedMethod) {
        Method[] declaredMethods = annotatedMethod.getDeclaringClass().getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(declaredMethods, "declaringClass.declaredMethods");
        for (Method method : declaredMethods) {
            String name = method.getName();
            Intrinsics.checkNotNullExpressionValue(name, "it.name");
            if (StringsKt.contains$default((CharSequence) name, '-', false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }
}
