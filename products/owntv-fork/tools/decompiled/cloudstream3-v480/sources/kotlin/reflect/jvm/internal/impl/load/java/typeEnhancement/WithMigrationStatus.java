package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WithMigrationStatus.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class WithMigrationStatus<T> {
    private final boolean isForWarningOnly;
    private final T qualifier;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WithMigrationStatus copy$default(WithMigrationStatus withMigrationStatus, Object obj, boolean z, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = withMigrationStatus.qualifier;
        }
        if ((i & 2) != 0) {
            z = withMigrationStatus.isForWarningOnly;
        }
        return withMigrationStatus.copy(obj, z);
    }

    public final WithMigrationStatus<T> copy(T t, boolean z) {
        return new WithMigrationStatus<>(t, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WithMigrationStatus)) {
            return false;
        }
        WithMigrationStatus withMigrationStatus = (WithMigrationStatus) obj;
        return Intrinsics.areEqual(this.qualifier, withMigrationStatus.qualifier) && this.isForWarningOnly == withMigrationStatus.isForWarningOnly;
    }

    public int hashCode() {
        T t = this.qualifier;
        return ((t == null ? 0 : t.hashCode()) * 31) + UByte$$ExternalSyntheticBackport0.m(this.isForWarningOnly);
    }

    public String toString() {
        return "WithMigrationStatus(qualifier=" + this.qualifier + ", isForWarningOnly=" + this.isForWarningOnly + ')';
    }

    public WithMigrationStatus(T t, boolean z) {
        this.qualifier = t;
        this.isForWarningOnly = z;
    }

    public /* synthetic */ WithMigrationStatus(Object obj, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? false : z);
    }

    public final T getQualifier() {
        return this.qualifier;
    }

    public final boolean isForWarningOnly() {
        return this.isForWarningOnly;
    }
}
