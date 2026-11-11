package kotlin.reflect.jvm.internal;

import java.lang.reflect.Type;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.impl.km.Attributes;
import kotlin.reflect.jvm.internal.impl.km.KmType;
import kotlin.reflect.jvm.internal.impl.km.KmValueParameter;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: KotlinKParameter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B3\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rR\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0006\u001a\u00020\u0007X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\b\u001a\u00020\tX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00198VX\u0096\u0084\n¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u0015\u0010\u001e\u001a\u00020\u001f8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001e\u0010 R\u0014\u0010!\u001a\u00020\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010 R\u0015\u0010#\u001a\u00020\u001f8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b#\u0010 ¨\u0006$"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKParameter;", "Lkotlin/reflect/jvm/internal/ReflectKParameter;", "callable", "Lkotlin/reflect/jvm/internal/KotlinKCallable;", "kmParameter", "Lkotlin/reflect/jvm/internal/impl/km/KmValueParameter;", "index", "", "kind", "Lkotlin/reflect/KParameter$Kind;", "typeParameterTable", "Lkotlin/reflect/jvm/internal/TypeParameterTable;", "<init>", "(Lkotlin/reflect/jvm/internal/KotlinKCallable;Lkotlin/metadata/KmValueParameter;ILkotlin/reflect/KParameter$Kind;Lkotlin/reflect/jvm/internal/TypeParameterTable;)V", "getCallable", "()Lkotlin/reflect/jvm/internal/KotlinKCallable;", "getIndex", "()I", "getKind", "()Lkotlin/reflect/KParameter$Kind;", "name", "", "getName", "()Ljava/lang/String;", "type", "Lkotlin/reflect/KType;", "getType", "()Lkotlin/reflect/KType;", "type$delegate", "Lkotlin/Lazy;", "isOptional", "", "()Z", "declaresDefaultValue", "getDeclaresDefaultValue", "isVararg", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class KotlinKParameter extends ReflectKParameter {
    private final KotlinKCallable<?> callable;
    private final int index;
    private final KParameter.Kind kind;
    private final KmValueParameter kmParameter;
    private final String name;

    /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
    private final Lazy type;

    public KotlinKParameter(KotlinKCallable<?> callable, KmValueParameter kmParameter, int i, KParameter.Kind kind, final TypeParameterTable typeParameterTable) {
        Intrinsics.checkNotNullParameter(callable, "callable");
        Intrinsics.checkNotNullParameter(kmParameter, "kmParameter");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(typeParameterTable, "typeParameterTable");
        this.callable = callable;
        this.kmParameter = kmParameter;
        this.index = i;
        this.kind = kind;
        String name = kmParameter.getName();
        this.name = StringsKt.startsWith$default(name, "<", false, 2, (Object) null) ? null : name;
        this.type = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this, typeParameterTable) { // from class: kotlin.reflect.jvm.internal.KotlinKParameter$$Lambda$0
            private final KotlinKParameter arg$0;
            private final TypeParameterTable arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = typeParameterTable;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKParameter.type_delegate$lambda$0(this.arg$0, this.arg$1);
            }
        });
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKParameter
    public KotlinKCallable<?> getCallable() {
        return this.callable;
    }

    @Override // kotlin.reflect.KParameter
    public int getIndex() {
        return this.index;
    }

    @Override // kotlin.reflect.KParameter
    public KParameter.Kind getKind() {
        return this.kind;
    }

    @Override // kotlin.reflect.KParameter
    public String getName() {
        return this.name;
    }

    @Override // kotlin.reflect.KParameter
    public KType getType() {
        return (KType) this.type.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KType type_delegate$lambda$0(final KotlinKParameter kotlinKParameter, TypeParameterTable typeParameterTable) {
        KmType type = kotlinKParameter.kmParameter.getType();
        ClassLoader classLoader = kotlinKParameter.getCallable().getContainer().getJClass().getClassLoader();
        Intrinsics.checkNotNullExpressionValue(classLoader, "getClassLoader(...)");
        return ConvertFromMetadataKt.toKType(type, classLoader, typeParameterTable, new Function0(kotlinKParameter) { // from class: kotlin.reflect.jvm.internal.KotlinKParameter$$Lambda$1
            private final KotlinKParameter arg$0;

            {
                this.arg$0 = kotlinKParameter;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKParameter.type_delegate$lambda$0$0(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Type type_delegate$lambda$0$0(KotlinKParameter kotlinKParameter) {
        if (!(kotlinKParameter.getCallable().getContainer() instanceof KPackageImpl) && !ReflectKCallableKt.isConstructor(kotlinKParameter.getCallable())) {
            throw new IllegalArgumentException(("Only constructors and top-level callables are supported for now: " + kotlinKParameter.getCallable()).toString());
        }
        return kotlinKParameter.getCallable().getCaller().getParameterTypes().get(kotlinKParameter.getIndex());
    }

    @Override // kotlin.reflect.KParameter
    public boolean isOptional() {
        if (!(getCallable() instanceof KotlinKProperty) && !(getCallable().getContainer() instanceof KPackageImpl) && !ReflectKCallableKt.isConstructor(getCallable())) {
            throw new IllegalArgumentException(("Only constructors and top-level callables are supported for now: " + getCallable()).toString());
        }
        return Attributes.getDeclaresDefaultValue(this.kmParameter);
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKParameter
    public boolean getDeclaresDefaultValue() {
        return Attributes.getDeclaresDefaultValue(this.kmParameter);
    }

    @Override // kotlin.reflect.KParameter
    public boolean isVararg() {
        return this.kmParameter.getVarargElementType() != null;
    }
}
