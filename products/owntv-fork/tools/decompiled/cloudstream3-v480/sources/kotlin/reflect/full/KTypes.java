package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.SystemPropertiesKt;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypePreparator;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.types.AbstractKType;
import kotlin.reflect.jvm.internal.types.DescriptorKType;
import kotlin.reflect.jvm.internal.types.ReflectTypeSystemContext;

/* JADX INFO: compiled from: KTypes.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\u0006\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0007¨\u0006\u0007"}, d2 = {"withNullability", "Lkotlin/reflect/KType;", "nullable", "", "isSubtypeOf", "other", "isSupertypeOf", "kotlin-reflection"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class KTypes {
    public static final KType withNullability(KType kType, boolean z) {
        Intrinsics.checkNotNullParameter(kType, "<this>");
        return ((AbstractKType) kType).makeNullableAsSpecified(z);
    }

    public static final boolean isSubtypeOf(KType kType, KType other) {
        Intrinsics.checkNotNullParameter(kType, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        if (SystemPropertiesKt.getUseK1Implementation()) {
            return TypeUtilsKt.isSubtypeOf(((DescriptorKType) kType).getType(), ((DescriptorKType) other).getType());
        }
        return AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, new TypeCheckerState(false, false, false, false, ReflectTypeSystemContext.INSTANCE, AbstractTypePreparator.Default.INSTANCE, AbstractTypeRefiner.Default.INSTANCE), (AbstractKType) kType, (AbstractKType) other, false, 8, null);
    }

    public static final boolean isSupertypeOf(KType kType, KType other) {
        Intrinsics.checkNotNullParameter(kType, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return isSubtypeOf(other, kType);
    }
}
