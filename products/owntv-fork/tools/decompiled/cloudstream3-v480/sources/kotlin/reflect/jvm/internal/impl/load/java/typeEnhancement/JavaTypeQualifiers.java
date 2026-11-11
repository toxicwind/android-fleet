package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: typeQualifiers.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class JavaTypeQualifiers {
    public static final Companion Companion = new Companion(null);
    private static final JavaTypeQualifiers NONE = new JavaTypeQualifiers(null, null, false, false, false, 24, null);
    private final boolean definitelyNotNull;
    private final boolean isMutabilityQualifierForWarning;
    private final boolean isNullabilityQualifierForWarning;
    private final MutabilityQualifier mutability;
    private final NullabilityQualifier nullability;

    public static /* synthetic */ JavaTypeQualifiers copy$default(JavaTypeQualifiers javaTypeQualifiers, NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            nullabilityQualifier = javaTypeQualifiers.nullability;
        }
        if ((i & 2) != 0) {
            mutabilityQualifier = javaTypeQualifiers.mutability;
        }
        if ((i & 4) != 0) {
            z = javaTypeQualifiers.definitelyNotNull;
        }
        if ((i & 8) != 0) {
            z2 = javaTypeQualifiers.isNullabilityQualifierForWarning;
        }
        if ((i & 16) != 0) {
            z3 = javaTypeQualifiers.isMutabilityQualifierForWarning;
        }
        boolean z4 = z3;
        boolean z5 = z;
        return javaTypeQualifiers.copy(nullabilityQualifier, mutabilityQualifier, z5, z2, z4);
    }

    public final JavaTypeQualifiers copy(NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2, boolean z3) {
        return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, z, z2, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JavaTypeQualifiers)) {
            return false;
        }
        JavaTypeQualifiers javaTypeQualifiers = (JavaTypeQualifiers) obj;
        return this.nullability == javaTypeQualifiers.nullability && this.mutability == javaTypeQualifiers.mutability && this.definitelyNotNull == javaTypeQualifiers.definitelyNotNull && this.isNullabilityQualifierForWarning == javaTypeQualifiers.isNullabilityQualifierForWarning && this.isMutabilityQualifierForWarning == javaTypeQualifiers.isMutabilityQualifierForWarning;
    }

    public int hashCode() {
        NullabilityQualifier nullabilityQualifier = this.nullability;
        int iHashCode = (nullabilityQualifier == null ? 0 : nullabilityQualifier.hashCode()) * 31;
        MutabilityQualifier mutabilityQualifier = this.mutability;
        return ((((((iHashCode + (mutabilityQualifier != null ? mutabilityQualifier.hashCode() : 0)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.definitelyNotNull)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.isNullabilityQualifierForWarning)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.isMutabilityQualifierForWarning);
    }

    public String toString() {
        return "JavaTypeQualifiers(nullability=" + this.nullability + ", mutability=" + this.mutability + ", definitelyNotNull=" + this.definitelyNotNull + ", isNullabilityQualifierForWarning=" + this.isNullabilityQualifierForWarning + ", isMutabilityQualifierForWarning=" + this.isMutabilityQualifierForWarning + ')';
    }

    public JavaTypeQualifiers(NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2, boolean z3) {
        this.nullability = nullabilityQualifier;
        this.mutability = mutabilityQualifier;
        this.definitelyNotNull = z;
        this.isNullabilityQualifierForWarning = z2;
        this.isMutabilityQualifierForWarning = z3;
    }

    public /* synthetic */ JavaTypeQualifiers(NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nullabilityQualifier, mutabilityQualifier, z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? false : z3);
    }

    public final NullabilityQualifier getNullability() {
        return this.nullability;
    }

    public final MutabilityQualifier getMutability() {
        return this.mutability;
    }

    public final boolean getDefinitelyNotNull() {
        return this.definitelyNotNull;
    }

    public final boolean isNullabilityQualifierForWarning() {
        return this.isNullabilityQualifierForWarning;
    }

    public final boolean isMutabilityQualifierForWarning() {
        return this.isMutabilityQualifierForWarning;
    }

    /* JADX INFO: compiled from: typeQualifiers.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final JavaTypeQualifiers getNONE() {
            return JavaTypeQualifiers.NONE;
        }
    }
}
