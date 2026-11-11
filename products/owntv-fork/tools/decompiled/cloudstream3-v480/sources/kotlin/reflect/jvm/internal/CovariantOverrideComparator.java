package kotlin.reflect.jvm.internal;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.Comparator;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.full.KTypes;
import kotlin.reflect.jvm.internal.types.AbstractKType;
import kotlin.reflect.jvm.internal.types.KTypeSubstitutor;
import kotlin.reflect.jvm.internal.types.ReflectTypeSystemContext;

/* JADX INFO: compiled from: fakeOverrides.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00022\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0016¨\u0006\n"}, d2 = {"Lkotlin/reflect/jvm/internal/CovariantOverrideComparator;", "Ljava/util/Comparator;", "Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "Lkotlin/Comparator;", "<init>", "()V", "compare", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class CovariantOverrideComparator implements Comparator<DescriptorKCallable<?>> {
    public static final CovariantOverrideComparator INSTANCE = new CovariantOverrideComparator();

    private CovariantOverrideComparator() {
    }

    @Override // java.util.Comparator
    public int compare(DescriptorKCallable<?> a, DescriptorKCallable<?> b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        KTypeSubstitutor kTypeSubstitutorSubstitutedWith = FakeOverridesKt.substitutedWith(a.getTypeParameters(), b.getTypeParameters());
        if (kTypeSubstitutorSubstitutedWith == null) {
            throw new IllegalStateException(("Intersection overrides can't have different type parameters sizes. It must have been reported by the compiler. The following members appear to be violating intersection overrides: '" + a + "' '" + b + '\'').toString());
        }
        KType type = KTypeSubstitutor.substitute$default(kTypeSubstitutorSubstitutedWith, a.getReturnType(), null, 2, null).getType();
        if (type == null) {
            FakeOverridesKt.starProjectionInTopLevelTypeIsNotPossible(a.getName());
            throw new KotlinNothingValueException();
        }
        KType returnType = b.getReturnType();
        boolean zIsSubtypeOf = KTypes.isSubtypeOf(type, returnType);
        boolean zIsSubtypeOf2 = KTypes.isSubtypeOf(returnType, type);
        if (zIsSubtypeOf && !zIsSubtypeOf2) {
            return -1;
        }
        if (zIsSubtypeOf2 && !zIsSubtypeOf) {
            return 1;
        }
        ReflectTypeSystemContext reflectTypeSystemContext = ReflectTypeSystemContext.INSTANCE;
        AbstractKType abstractKType = type instanceof AbstractKType ? (AbstractKType) type : null;
        boolean z = abstractKType != null && reflectTypeSystemContext.isFlexible(abstractKType);
        ReflectTypeSystemContext reflectTypeSystemContext2 = ReflectTypeSystemContext.INSTANCE;
        AbstractKType abstractKType2 = returnType instanceof AbstractKType ? (AbstractKType) returnType : null;
        boolean z2 = abstractKType2 != null && reflectTypeSystemContext2.isFlexible(abstractKType2);
        if (!z2 || z) {
            return (!z || z2) ? 0 : 1;
        }
        return -1;
    }
}
