package kotlin.reflect.jvm.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ReflectKParameter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a\b\u0010\u0005\u001a\u00020\u0006H\u0002\"\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"javaParameter", "Lkotlin/reflect/jvm/internal/JavaParameter;", "Lkotlin/reflect/jvm/internal/ReflectKParameter;", "getJavaParameter", "(Lkotlin/reflect/jvm/internal/ReflectKParameter;)Lkotlin/reflect/jvm/internal/JavaParameter;", "isJdk8", "", "kotlin-reflection"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ReflectKParameterKt {
    public static final JavaParameter getJavaParameter(ReflectKParameter reflectKParameter) {
        int length;
        Intrinsics.checkNotNullParameter(reflectKParameter, "<this>");
        Member memberMo10234getMember = reflectKParameter.getCallable().getCaller().mo10234getMember();
        if (memberMo10234getMember instanceof Method) {
            if (!Modifier.isStatic(((Method) memberMo10234getMember).getModifiers())) {
                throw new IllegalArgumentException(("Only static methods are supported for now: " + memberMo10234getMember).toString());
            }
            return new JavaParameter(memberMo10234getMember, reflectKParameter.getIndex());
        }
        if (memberMo10234getMember instanceof Constructor) {
            Constructor constructor = (Constructor) memberMo10234getMember;
            Class declaringClass = constructor.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
            if (JvmClassMappingKt.getKotlinClass(declaringClass).isInner() && isJdk8()) {
                length = -1;
            } else {
                length = constructor.getDeclaringClass().isEnum() ? (constructor.getParameterAnnotations().length - constructor.getParameterTypes().length) + 2 : 0;
            }
            return new JavaParameter(memberMo10234getMember, reflectKParameter.getIndex() + length);
        }
        throw new KotlinReflectionInternalError("Unsupported parameter owner: " + memberMo10234getMember);
    }

    private static final boolean isJdk8() {
        String property = System.getProperty("java.version");
        return property != null && StringsKt.startsWith$default(property, "1.", false, 2, (Object) null);
    }
}
