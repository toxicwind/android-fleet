package kotlin.reflect.jvm.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KParameter;

/* JADX INFO: compiled from: ReflectKParameter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b \u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0013\u001a\u00020\t2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0086\u0082\u0004J\n\u0010\u0016\u001a\u00020\u0017H\u0086\u0080\u0004J\n\u0010\u0018\u001a\u00020\u0019H\u0086\u0080\u0004R\u0016\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR!\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8VX\u0096\u0084\n¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lkotlin/reflect/jvm/internal/ReflectKParameter;", "Lkotlin/reflect/KParameter;", "<init>", "()V", "callable", "Lkotlin/reflect/jvm/internal/ReflectKCallable;", "getCallable", "()Lkotlin/reflect/jvm/internal/ReflectKCallable;", "declaresDefaultValue", "", "getDeclaresDefaultValue", "()Z", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "annotations$delegate", "Lkotlin/Lazy;", "equals", "other", "", "hashCode", "", "toString", "", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class ReflectKParameter implements KParameter {

    /* JADX INFO: renamed from: annotations$delegate, reason: from kotlin metadata */
    private final Lazy annotations = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.ReflectKParameter$$Lambda$0
        private final ReflectKParameter arg$0;

        {
            this.arg$0 = this;
        }

        @Override // kotlin.jvm.functions.Function0
        public Object invoke() {
            return ReflectKParameter.annotations_delegate$lambda$0(this.arg$0);
        }
    });

    public abstract ReflectKCallable<?> getCallable();

    public abstract boolean getDeclaresDefaultValue();

    @Override // kotlin.reflect.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        return (List) this.annotations.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List annotations_delegate$lambda$0(ReflectKParameter reflectKParameter) {
        List listEmptyList;
        JavaParameter javaParameter = ReflectKParameterKt.getJavaParameter(reflectKParameter);
        Member callable = javaParameter != null ? javaParameter.getCallable() : null;
        if (callable instanceof Method) {
            Annotation[] annotationArr = ((Method) callable).getParameterAnnotations()[javaParameter.getIndex()];
            Intrinsics.checkNotNullExpressionValue(annotationArr, "get(...)");
            listEmptyList = ArraysKt.toList(annotationArr);
        } else if (callable instanceof Constructor) {
            Annotation[] annotationArr2 = ((Constructor) callable).getParameterAnnotations()[javaParameter.getIndex()];
            Intrinsics.checkNotNullExpressionValue(annotationArr2, "get(...)");
            listEmptyList = ArraysKt.toList(annotationArr2);
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        return UtilKt.unwrapKotlinRepeatableAnnotations(listEmptyList);
    }

    public final boolean equals(Object other) {
        if (!(other instanceof ReflectKParameter)) {
            return false;
        }
        ReflectKParameter reflectKParameter = (ReflectKParameter) other;
        return Intrinsics.areEqual(getCallable(), reflectKParameter.getCallable()) && getIndex() == reflectKParameter.getIndex();
    }

    public final int hashCode() {
        return (getCallable().hashCode() * 31) + getIndex();
    }

    public final String toString() {
        return ReflectionObjectRenderer.INSTANCE.renderParameter(this);
    }
}
