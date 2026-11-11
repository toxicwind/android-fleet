package kotlin.reflect.jvm.internal.types;

import androidx.exifinterface.media.ExifInterface;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.internal.KTypeParameterOwnerImpl;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: MutableCollectionKClass.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u001b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u00042\u00020\u0005B]\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u001e\u0010\t\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n\u0012\u001e\u0010\r\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002H\u0096\u0082\u0004J\n\u0010\u001f\u001a\u00020 H\u0096\u0080\u0004J\n\u0010!\u001a\u00020\bH\u0096\u0080\u0004J\u0014\u0010\"\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010\u0002H\u0097\u0081\u0004R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0007\u001a\u00020\bX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0015\u0010\u001a\u001a\u00020\b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0014R\u0019\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u000bX\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b&\u0010\u0017R\u001f\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000)0(X\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0015\u0010,\u001a\u00020\u001d8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0015\u0010.\u001a\u00020\u001d8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b.\u0010-R\u0015\u0010/\u001a\u00020\u001d8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b/\u0010-R\u0015\u00100\u001a\u00020\u001d8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b0\u0010-R\u0015\u00101\u001a\u00020\u001d8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b1\u0010-R\u0015\u00102\u001a\u00020\u001d8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b2\u0010-R\u0015\u00103\u001a\u00020\u001d8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b3\u0010-R\u0015\u00104\u001a\u00020\u001d8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b4\u0010-R\u0015\u00105\u001a\u00020\u001d8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b5\u0010-R\u001d\u00106\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003070(X\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b8\u0010+R\u001d\u00109\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030(X\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b:\u0010+R\u0015\u0010;\u001a\u0004\u0018\u00018\u0000X\u0096\u0085\b¢\u0006\u0006\u001a\u0004\b<\u0010=R#\u0010>\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00030\u000b8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\b?\u0010\u0017R\u0017\u0010@\u001a\u0004\u0018\u00010A8\u0016X\u0097\u0085\b¢\u0006\u0006\u001a\u0004\bB\u0010C¨\u0006D"}, d2 = {"Lkotlin/reflect/jvm/internal/types/MutableCollectionKClass;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlin/reflect/KClass;", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeConstructorMarker;", "Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;", "klass", "qualifiedName", "", "createTypeParameters", "Lkotlin/Function1;", "", "Lkotlin/reflect/KTypeParameter;", "createSupertypes", "Lkotlin/reflect/KType;", "<init>", "(Lkotlin/reflect/KClass;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getKlass", "()Lkotlin/reflect/KClass;", "getQualifiedName", "()Ljava/lang/String;", "typeParameters", "getTypeParameters", "()Ljava/util/List;", "supertypes", "getSupertypes", "simpleName", "getSimpleName", "equals", "", "other", "hashCode", "", "toString", "isInstance", ES6Iterator.VALUE_PROPERTY, "annotations", "", "getAnnotations", "constructors", "", "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "isAbstract", "()Z", "isCompanion", "isData", "isFinal", "isFun", "isInner", "isOpen", "isSealed", "isValue", "members", "Lkotlin/reflect/KCallable;", "getMembers", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "sealedSubclasses", "getSealedSubclasses", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MutableCollectionKClass<T> implements KClass<T>, KTypeParameterOwnerImpl, TypeConstructorMarker {
    private final KClass<T> klass;
    private final String qualifiedName;
    private final List<KType> supertypes;
    private final List<KTypeParameter> typeParameters;

    @Override // kotlin.reflect.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        return this.klass.getAnnotations();
    }

    @Override // kotlin.reflect.KClass
    public Collection<KFunction<T>> getConstructors() {
        return this.klass.getConstructors();
    }

    @Override // kotlin.reflect.KClass, kotlin.reflect.KDeclarationContainer
    public Collection<KCallable<?>> getMembers() {
        return this.klass.getMembers();
    }

    @Override // kotlin.reflect.KClass
    public Collection<KClass<?>> getNestedClasses() {
        return this.klass.getNestedClasses();
    }

    @Override // kotlin.reflect.KClass
    public T getObjectInstance() {
        return this.klass.getObjectInstance();
    }

    @Override // kotlin.reflect.KClass
    public List<KClass<? extends T>> getSealedSubclasses() {
        return this.klass.getSealedSubclasses();
    }

    @Override // kotlin.reflect.KClass
    public KVisibility getVisibility() {
        return this.klass.getVisibility();
    }

    @Override // kotlin.reflect.KClass
    public boolean isAbstract() {
        return this.klass.isAbstract();
    }

    @Override // kotlin.reflect.KClass
    public boolean isCompanion() {
        return this.klass.isCompanion();
    }

    @Override // kotlin.reflect.KClass
    public boolean isData() {
        return this.klass.isData();
    }

    @Override // kotlin.reflect.KClass
    public boolean isFinal() {
        return this.klass.isFinal();
    }

    @Override // kotlin.reflect.KClass
    public boolean isFun() {
        return this.klass.isFun();
    }

    @Override // kotlin.reflect.KClass
    public boolean isInner() {
        return this.klass.isInner();
    }

    @Override // kotlin.reflect.KClass
    public boolean isInstance(Object value) {
        return this.klass.isInstance(value);
    }

    @Override // kotlin.reflect.KClass
    public boolean isOpen() {
        return this.klass.isOpen();
    }

    @Override // kotlin.reflect.KClass
    public boolean isSealed() {
        return this.klass.isSealed();
    }

    @Override // kotlin.reflect.KClass
    public boolean isValue() {
        return this.klass.isValue();
    }

    public MutableCollectionKClass(KClass<T> klass, String qualifiedName, Function1<? super MutableCollectionKClass<T>, ? extends List<? extends KTypeParameter>> createTypeParameters, Function1<? super MutableCollectionKClass<T>, ? extends List<? extends KType>> createSupertypes) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        Intrinsics.checkNotNullParameter(qualifiedName, "qualifiedName");
        Intrinsics.checkNotNullParameter(createTypeParameters, "createTypeParameters");
        Intrinsics.checkNotNullParameter(createSupertypes, "createSupertypes");
        this.klass = klass;
        this.qualifiedName = qualifiedName;
        this.typeParameters = (List) createTypeParameters.invoke(this);
        this.supertypes = (List) createSupertypes.invoke(this);
    }

    public final KClass<T> getKlass() {
        return this.klass;
    }

    @Override // kotlin.reflect.KClass
    public String getQualifiedName() {
        return this.qualifiedName;
    }

    @Override // kotlin.reflect.KClass
    public List<KTypeParameter> getTypeParameters() {
        return this.typeParameters;
    }

    @Override // kotlin.reflect.KClass
    public List<KType> getSupertypes() {
        return this.supertypes;
    }

    @Override // kotlin.reflect.KClass
    public String getSimpleName() {
        return StringsKt.substringAfterLast$default(getQualifiedName(), ".", (String) null, 2, (Object) null);
    }

    @Override // kotlin.reflect.KClass
    public boolean equals(Object other) {
        return (other instanceof MutableCollectionKClass) && Intrinsics.areEqual(this.klass, ((MutableCollectionKClass) other).klass);
    }

    @Override // kotlin.reflect.KClass
    public int hashCode() {
        return this.klass.hashCode();
    }

    public String toString() {
        return "MutableCollectionKClass(" + this.klass + ')';
    }
}
