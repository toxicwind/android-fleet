package kotlin.reflect;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: KType.kt */
/* JADX INFO: loaded from: classes.dex */
public final class KTypeProjection {

    @Nullable
    private final KType type;

    @Nullable
    private final KVariance variance;

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KTypeProjection)) {
            return false;
        }
        KTypeProjection kTypeProjection = (KTypeProjection) obj;
        return Intrinsics.areEqual(this.variance, kTypeProjection.variance) && Intrinsics.areEqual(this.type, kTypeProjection.type);
    }

    public int hashCode() {
        KVariance kVariance = this.variance;
        int iHashCode = (kVariance != null ? kVariance.hashCode() : 0) * 31;
        KType kType = this.type;
        return iHashCode + (kType != null ? kType.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "KTypeProjection(variance=" + this.variance + ", type=" + this.type + ")";
    }

    @Nullable
    public final KVariance getVariance() {
        return this.variance;
    }

    @Nullable
    public final KType getType() {
        return this.type;
    }
}
