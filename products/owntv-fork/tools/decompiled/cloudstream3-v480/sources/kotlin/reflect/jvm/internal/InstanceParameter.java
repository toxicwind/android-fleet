package kotlin.reflect.jvm.internal;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;

/* JADX INFO: compiled from: ReflectKParameter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\n\u001a\u00020\u000b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0015\u0010\u000e\u001a\u00020\u000fX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\u0004\u0018\u00010\u00138VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\u0016\u001a\u00020\u00178VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001cR\u0015\u0010\u001d\u001a\u00020\u001b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001cR\u001b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u001c¨\u0006%"}, d2 = {"Lkotlin/reflect/jvm/internal/InstanceParameter;", "Lkotlin/reflect/jvm/internal/ReflectKParameter;", "callable", "Lkotlin/reflect/jvm/internal/ReflectKCallable;", "klass", "Lkotlin/reflect/KClass;", "<init>", "(Lkotlin/reflect/jvm/internal/ReflectKCallable;Lkotlin/reflect/KClass;)V", "getCallable", "()Lkotlin/reflect/jvm/internal/ReflectKCallable;", "index", "", "getIndex", "()I", "type", "Lkotlin/reflect/KType;", "getType", "()Lkotlin/reflect/KType;", "name", "", "getName", "()Ljava/lang/String;", "kind", "Lkotlin/reflect/KParameter$Kind;", "getKind", "()Lkotlin/reflect/KParameter$Kind;", "isOptional", "", "()Z", "isVararg", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "declaresDefaultValue", "getDeclaresDefaultValue", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class InstanceParameter extends ReflectKParameter {
    private final ReflectKCallable<?> callable;
    private final KType type;

    @Override // kotlin.reflect.jvm.internal.ReflectKParameter
    public boolean getDeclaresDefaultValue() {
        return false;
    }

    @Override // kotlin.reflect.KParameter
    public int getIndex() {
        return 0;
    }

    @Override // kotlin.reflect.KParameter
    public String getName() {
        return null;
    }

    @Override // kotlin.reflect.KParameter
    public boolean isOptional() {
        return false;
    }

    @Override // kotlin.reflect.KParameter
    public boolean isVararg() {
        return false;
    }

    public InstanceParameter(ReflectKCallable<?> callable, KClass<?> klass) {
        Intrinsics.checkNotNullParameter(callable, "callable");
        Intrinsics.checkNotNullParameter(klass, "klass");
        this.callable = callable;
        this.type = KClasses.createDefaultType(klass);
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKParameter
    public ReflectKCallable<?> getCallable() {
        return this.callable;
    }

    @Override // kotlin.reflect.KParameter
    public KType getType() {
        return this.type;
    }

    @Override // kotlin.reflect.KParameter
    public KParameter.Kind getKind() {
        return KParameter.Kind.INSTANCE;
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKParameter, kotlin.reflect.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        return CollectionsKt.emptyList();
    }
}
