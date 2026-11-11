package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.jvm.internal.EqualityMode;
import kotlin.reflect.jvm.internal.types.KTypeSubstitutor;

/* JADX INFO: compiled from: fakeOverrides.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u000f\b\u0080\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003Bm\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\n\u0012\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\n\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\n\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00028\u0000¢\u0006\u0004\b\u0015\u0010\u0016J#\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0000\"\b\b\u0001\u0010\u0001*\u00020\u00022\u0006\u0010\u0014\u001a\u0002H\u0001¢\u0006\u0002\u0010&J\n\u0010'\u001a\u00020(H\u0096\u0080\u0004J\u0014\u0010)\u001a\u00020\u00132\b\u0010*\u001a\u0004\u0018\u00010\u0003H\u0096\u0082\u0004J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0007HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\r0\nHÆ\u0003J\u0013\u00100\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\nHÆ\u0003J\u000f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00110\nHÆ\u0003J\t\u00102\u001a\u00020\u0013HÆ\u0003J\u000e\u00103\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010#J\u008c\u0001\u00104\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\n2\u0012\b\u0002\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\n2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\n2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u00105J\n\u00106\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\n¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u001b\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\n¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\n¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010!R\u0013\u0010\u0014\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#¨\u00067"}, d2 = {"Lkotlin/reflect/jvm/internal/EquatableCallableSignature;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/reflect/jvm/internal/EqualityMode;", "", "kind", "Lkotlin/reflect/jvm/internal/SignatureKind;", "name", "", "jvmNameIfFunction", "typeParameters", "", "Lkotlin/reflect/KTypeParameter;", "kotlinParameterTypes", "Lkotlin/reflect/KType;", "javaParameterTypesIfFunction", "Ljava/lang/Class;", "javaGenericParameterTypesIfFunction", "Ljava/lang/reflect/Type;", "isStatic", "", "equalityMode", "<init>", "(Lkotlin/reflect/jvm/internal/SignatureKind;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLkotlin/reflect/jvm/internal/EqualityMode;)V", "getKind", "()Lkotlin/reflect/jvm/internal/SignatureKind;", "getName", "()Ljava/lang/String;", "getJvmNameIfFunction", "getTypeParameters", "()Ljava/util/List;", "getKotlinParameterTypes", "getJavaParameterTypesIfFunction", "getJavaGenericParameterTypesIfFunction", "()Z", "getEqualityMode", "()Lkotlin/reflect/jvm/internal/EqualityMode;", "Lkotlin/reflect/jvm/internal/EqualityMode;", "withEqualityMode", "(Lkotlin/reflect/jvm/internal/EqualityMode;)Lkotlin/reflect/jvm/internal/EquatableCallableSignature;", "hashCode", "", "equals", "other", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Lkotlin/reflect/jvm/internal/SignatureKind;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLkotlin/reflect/jvm/internal/EqualityMode;)Lkotlin/reflect/jvm/internal/EquatableCallableSignature;", "toString", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EquatableCallableSignature<T extends EqualityMode> {
    private final T equalityMode;
    private final boolean isStatic;
    private final List<Type> javaGenericParameterTypesIfFunction;
    private final List<Class<?>> javaParameterTypesIfFunction;
    private final String jvmNameIfFunction;
    private final SignatureKind kind;
    private final List<KType> kotlinParameterTypes;
    private final String name;
    private final List<KTypeParameter> typeParameters;

    public String toString() {
        return "EquatableCallableSignature(kind=" + this.kind + ", name=" + this.name + ", jvmNameIfFunction=" + this.jvmNameIfFunction + ", typeParameters=" + this.typeParameters + ", kotlinParameterTypes=" + this.kotlinParameterTypes + ", javaParameterTypesIfFunction=" + this.javaParameterTypesIfFunction + ", javaGenericParameterTypesIfFunction=" + this.javaGenericParameterTypesIfFunction + ", isStatic=" + this.isStatic + ", equalityMode=" + this.equalityMode + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EquatableCallableSignature(SignatureKind kind, String name, String str, List<? extends KTypeParameter> typeParameters, List<? extends KType> kotlinParameterTypes, List<? extends Class<?>> javaParameterTypesIfFunction, List<? extends Type> javaGenericParameterTypesIfFunction, boolean z, T equalityMode) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(typeParameters, "typeParameters");
        Intrinsics.checkNotNullParameter(kotlinParameterTypes, "kotlinParameterTypes");
        Intrinsics.checkNotNullParameter(javaParameterTypesIfFunction, "javaParameterTypesIfFunction");
        Intrinsics.checkNotNullParameter(javaGenericParameterTypesIfFunction, "javaGenericParameterTypesIfFunction");
        Intrinsics.checkNotNullParameter(equalityMode, "equalityMode");
        this.kind = kind;
        this.name = name;
        this.jvmNameIfFunction = str;
        this.typeParameters = typeParameters;
        this.kotlinParameterTypes = kotlinParameterTypes;
        this.javaParameterTypesIfFunction = javaParameterTypesIfFunction;
        this.javaGenericParameterTypesIfFunction = javaGenericParameterTypesIfFunction;
        this.isStatic = z;
        this.equalityMode = equalityMode;
        if (kind != SignatureKind.FIELD_IN_JAVA_CLASS || (kotlinParameterTypes.isEmpty() && typeParameters.isEmpty() && javaParameterTypesIfFunction.isEmpty())) {
            if (javaParameterTypesIfFunction.size() == javaGenericParameterTypesIfFunction.size()) {
                return;
            }
            throw new IllegalStateException(("javaParameterTypesIfFunction.size (" + javaParameterTypesIfFunction.size() + ") and javaGenericParameterTypesIfFunction.size (" + javaGenericParameterTypesIfFunction.size() + ") must be equal. For member: '" + name + '\'').toString());
        }
        throw new IllegalStateException(("Inconsistent combination of EquatableCallableSignature values. kind: " + kind + ", kotlinParameterTypes.isEmpty(): " + kotlinParameterTypes.isEmpty() + ",typeParameters.isEmpty(): " + typeParameters.isEmpty() + ", javaParameterTypesIfFunction.isEmpty(): " + javaParameterTypesIfFunction.isEmpty() + ".For member: '" + name + '\'').toString());
    }

    public final <T extends EqualityMode> EquatableCallableSignature<T> withEqualityMode(T equalityMode) {
        Intrinsics.checkNotNullParameter(equalityMode, "equalityMode");
        return new EquatableCallableSignature<>(this.kind, this.name, this.jvmNameIfFunction, this.typeParameters, this.kotlinParameterTypes, this.javaParameterTypesIfFunction, this.javaGenericParameterTypesIfFunction, this.isStatic, equalityMode);
    }

    public int hashCode() {
        boolean z = Intrinsics.areEqual(this.equalityMode, EqualityMode.JavaSignature.INSTANCE) && this.kind == SignatureKind.FUNCTION;
        if (!z) {
            if (z) {
                throw new NoWhenBranchMatchedException();
            }
            return Arrays.hashCode(new Object[]{this.kind, Integer.valueOf(this.kotlinParameterTypes.size()), Boolean.valueOf(this.isStatic), this.name});
        }
        SignatureKind signatureKind = this.kind;
        Integer numValueOf = Integer.valueOf(this.kotlinParameterTypes.size());
        Boolean boolValueOf = Boolean.valueOf(this.isStatic);
        String str = this.jvmNameIfFunction;
        if (str == null) {
            str = "";
        }
        return Arrays.hashCode(new Object[]{signatureKind, numValueOf, boolValueOf, str});
    }

    public boolean equals(Object other) {
        KTypeSubstitutor kTypeSubstitutorSubstitutedWith;
        if (this == other) {
            return true;
        }
        if (!(other instanceof EquatableCallableSignature)) {
            return false;
        }
        EquatableCallableSignature equatableCallableSignature = (EquatableCallableSignature) other;
        if (!Intrinsics.areEqual(this.equalityMode, equatableCallableSignature.equalityMode)) {
            throw new IllegalStateException(("Equality modes must be the same for member '" + this.name + "'. Please recreate signatures on inheritance").toString());
        }
        if (this.kind != equatableCallableSignature.kind || this.isStatic != equatableCallableSignature.isStatic || this.kotlinParameterTypes.size() != equatableCallableSignature.kotlinParameterTypes.size()) {
            return false;
        }
        if (Intrinsics.areEqual(this.equalityMode, EqualityMode.JavaSignature.INSTANCE) && this.kind == SignatureKind.FUNCTION) {
            if (!Intrinsics.areEqual(this.jvmNameIfFunction, equatableCallableSignature.jvmNameIfFunction) || this.javaParameterTypesIfFunction.size() != equatableCallableSignature.javaParameterTypesIfFunction.size()) {
                return false;
            }
            int size = this.javaParameterTypesIfFunction.size();
            int size2 = this.kotlinParameterTypes.size();
            List<Class<?>> list = this.javaParameterTypesIfFunction;
            if (size != size2) {
                throw new IllegalStateException(("javaParameterTypesIfFunction.size (" + list.size() + ") and kotlinParameterTypes.size (" + this.kotlinParameterTypes.size() + ") must be equal for member '" + this.name + '\'').toString());
            }
            int size3 = list.size();
            for (int i = 0; i < size3; i++) {
                Type type = this.javaGenericParameterTypesIfFunction.get(i);
                Class<?> cls = this.javaParameterTypesIfFunction.get(i);
                Type type2 = equatableCallableSignature.javaGenericParameterTypesIfFunction.get(i);
                Class<?> cls2 = equatableCallableSignature.javaParameterTypesIfFunction.get(i);
                TypeVariable typeVariable = type instanceof TypeVariable ? (TypeVariable) type : null;
                boolean z = (typeVariable != null ? typeVariable.getGenericDeclaration() : null) instanceof Class;
                TypeVariable typeVariable2 = type2 instanceof TypeVariable ? (TypeVariable) type2 : null;
                boolean z2 = (typeVariable2 != null ? typeVariable2.getGenericDeclaration() : null) instanceof Class;
                if (z || z2) {
                    if (cls.isPrimitive() != cls2.isPrimitive() || !FakeOverridesKt.areEqualKTypes(FakeOverridesKt.coerceFlexibleTypesAndMutabilityRecursive(this.kotlinParameterTypes.get(i), this.name), FakeOverridesKt.coerceFlexibleTypesAndMutabilityRecursive(equatableCallableSignature.kotlinParameterTypes.get(i), equatableCallableSignature.name))) {
                        return false;
                    }
                } else if (!Intrinsics.areEqual(cls, cls2)) {
                    return false;
                }
            }
        } else {
            if (!Intrinsics.areEqual(this.name, equatableCallableSignature.name) || (kTypeSubstitutorSubstitutedWith = FakeOverridesKt.substitutedWith(this.typeParameters, equatableCallableSignature.typeParameters)) == null) {
                return false;
            }
            int size4 = this.typeParameters.size();
            for (int i2 = 0; i2 < size4; i2++) {
                KTypeParameter kTypeParameter = this.typeParameters.get(i2);
                KTypeParameter kTypeParameter2 = equatableCallableSignature.typeParameters.get(i2);
                if (kTypeParameter.getUpperBounds().size() != kTypeParameter2.getUpperBounds().size()) {
                    return false;
                }
                List<KType> upperBounds = kTypeParameter.getUpperBounds();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(upperBounds, 10));
                Iterator<T> it = upperBounds.iterator();
                while (it.hasNext()) {
                    KType type3 = KTypeSubstitutor.substitute$default(kTypeSubstitutorSubstitutedWith, (KType) it.next(), null, 2, null).getType();
                    if (type3 == null) {
                        FakeOverridesKt.starProjectionInTopLevelTypeIsNotPossible(this.name);
                        throw new KotlinNothingValueException();
                    }
                    arrayList.add(type3);
                }
                List<Pair> listZip = CollectionsKt.zip(FakeOverridesKt.sortedUpperBounds(arrayList, this.name), FakeOverridesKt.sortedUpperBounds(kTypeParameter2.getUpperBounds(), equatableCallableSignature.name));
                if (!(listZip instanceof Collection) || !listZip.isEmpty()) {
                    for (Pair pair : listZip) {
                        if (!FakeOverridesKt.areEqualKTypes((KType) pair.getFirst(), (KType) pair.getSecond())) {
                            return false;
                        }
                    }
                }
            }
            int size5 = this.kotlinParameterTypes.size();
            for (int i3 = 0; i3 < size5; i3++) {
                KType type4 = KTypeSubstitutor.substitute$default(kTypeSubstitutorSubstitutedWith, this.kotlinParameterTypes.get(i3), null, 2, null).getType();
                if (type4 == null) {
                    FakeOverridesKt.starProjectionInTopLevelTypeIsNotPossible(this.name);
                    throw new KotlinNothingValueException();
                }
                if (!FakeOverridesKt.areEqualKTypes(type4, equatableCallableSignature.kotlinParameterTypes.get(i3))) {
                    return false;
                }
            }
        }
        return true;
    }
}
