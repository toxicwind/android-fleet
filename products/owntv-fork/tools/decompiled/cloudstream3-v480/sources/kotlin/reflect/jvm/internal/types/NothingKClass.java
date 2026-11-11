package kotlin.reflect.jvm.internal.types;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.internal.KTypeParameterOwnerImpl;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: NothingKClass.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0082\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010\u0013\u001a\u00020\bH\u0096\u0080\u0004J\u0014\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0010H\u0097\u0081\u0004R\u0015\u0010\u0007\u001a\u00020\b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0015\u0010\u000b\u001a\u00020\b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0019\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001f\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u001d0\u001cX\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0015\u0010 \u001a\u00020\u000e8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b \u0010!R\u0015\u0010\"\u001a\u00020\u000e8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b\"\u0010!R\u0015\u0010#\u001a\u00020\u000e8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b#\u0010!R\u0015\u0010$\u001a\u00020\u000e8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b$\u0010!R\u0015\u0010%\u001a\u00020\u000e8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b%\u0010!R\u0015\u0010&\u001a\u00020\u000e8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b&\u0010!R\u0015\u0010'\u001a\u00020\u000e8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b'\u0010!R\u0015\u0010(\u001a\u00020\u000e8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b(\u0010!R\u0015\u0010)\u001a\u00020\u000e8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b)\u0010!R\u001d\u0010*\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030+0\u001cX\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b,\u0010\u001fR\u001d\u0010-\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u001cX\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b.\u0010\u001fR\u0015\u0010/\u001a\u0004\u0018\u00010\u0002X\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b0\u00101R#\u00102\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00010\u00178\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b3\u0010\u001aR\u001b\u00104\u001a\b\u0012\u0004\u0012\u0002050\u00178\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b6\u0010\u001aR\u001b\u00107\u001a\b\u0012\u0004\u0012\u0002080\u00178\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b9\u0010\u001aR\u0017\u0010:\u001a\u0004\u0018\u00010;8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b<\u0010=¨\u0006>"}, d2 = {"Lkotlin/reflect/jvm/internal/types/NothingKClass;", "Lkotlin/reflect/KClass;", "Ljava/lang/Void;", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeConstructorMarker;", "Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;", "<init>", "()V", "simpleName", "", "getSimpleName", "()Ljava/lang/String;", "qualifiedName", "getQualifiedName", "equals", "", "other", "", "hashCode", "", "toString", "isInstance", ES6Iterator.VALUE_PROPERTY, "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "constructors", "", "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "isAbstract", "()Z", "isCompanion", "isData", "isFinal", "isFun", "isInner", "isOpen", "isSealed", "isValue", "members", "Lkotlin/reflect/KCallable;", "getMembers", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Void;", "sealedSubclasses", "getSealedSubclasses", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NothingKClass implements KClass<Void>, KTypeParameterOwnerImpl, TypeConstructorMarker {
    public static final NothingKClass INSTANCE = new NothingKClass();
    private final /* synthetic */ KClass<Void> $$delegate_0 = Reflection.getOrCreateKotlinClass(Void.class);

    @Override // kotlin.reflect.KClass
    public boolean equals(Object other) {
        return this == other;
    }

    @Override // kotlin.reflect.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        return this.$$delegate_0.getAnnotations();
    }

    @Override // kotlin.reflect.KClass
    public Collection<KFunction<Void>> getConstructors() {
        return this.$$delegate_0.getConstructors();
    }

    @Override // kotlin.reflect.KClass, kotlin.reflect.KDeclarationContainer
    public Collection<KCallable<?>> getMembers() {
        return this.$$delegate_0.getMembers();
    }

    @Override // kotlin.reflect.KClass
    public Collection<KClass<?>> getNestedClasses() {
        return this.$$delegate_0.getNestedClasses();
    }

    @Override // kotlin.reflect.KClass
    public Void getObjectInstance() {
        return this.$$delegate_0.getObjectInstance();
    }

    @Override // kotlin.reflect.KClass
    public List<KClass<? extends Void>> getSealedSubclasses() {
        return this.$$delegate_0.getSealedSubclasses();
    }

    @Override // kotlin.reflect.KClass
    public List<KType> getSupertypes() {
        return this.$$delegate_0.getSupertypes();
    }

    @Override // kotlin.reflect.KClass
    public List<KTypeParameter> getTypeParameters() {
        return this.$$delegate_0.getTypeParameters();
    }

    @Override // kotlin.reflect.KClass
    public KVisibility getVisibility() {
        return this.$$delegate_0.getVisibility();
    }

    @Override // kotlin.reflect.KClass
    public boolean isAbstract() {
        return this.$$delegate_0.isAbstract();
    }

    @Override // kotlin.reflect.KClass
    public boolean isCompanion() {
        return this.$$delegate_0.isCompanion();
    }

    @Override // kotlin.reflect.KClass
    public boolean isData() {
        return this.$$delegate_0.isData();
    }

    @Override // kotlin.reflect.KClass
    public boolean isFinal() {
        return this.$$delegate_0.isFinal();
    }

    @Override // kotlin.reflect.KClass
    public boolean isFun() {
        return this.$$delegate_0.isFun();
    }

    @Override // kotlin.reflect.KClass
    public boolean isInner() {
        return this.$$delegate_0.isInner();
    }

    @Override // kotlin.reflect.KClass
    public boolean isInstance(Object value) {
        return this.$$delegate_0.isInstance(value);
    }

    @Override // kotlin.reflect.KClass
    public boolean isOpen() {
        return this.$$delegate_0.isOpen();
    }

    @Override // kotlin.reflect.KClass
    public boolean isSealed() {
        return this.$$delegate_0.isSealed();
    }

    @Override // kotlin.reflect.KClass
    public boolean isValue() {
        return this.$$delegate_0.isValue();
    }

    private NothingKClass() {
    }

    @Override // kotlin.reflect.KClass
    public String getSimpleName() {
        return "Nothing";
    }

    @Override // kotlin.reflect.KClass
    public String getQualifiedName() {
        return "kotlin.Nothing";
    }

    @Override // kotlin.reflect.KClass
    public int hashCode() {
        return System.identityHashCode(this);
    }

    public String toString() {
        return "NothingKClass";
    }
}
