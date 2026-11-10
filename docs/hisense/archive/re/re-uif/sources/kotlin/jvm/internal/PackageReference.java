package kotlin.jvm.internal;

import java.util.Collection;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.reflect.KCallable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: PackageReference.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PackageReference implements ClassBasedDeclarationContainer {

    @NotNull
    private final Class<?> jClass;
    private final String moduleName;

    public PackageReference(@NotNull Class<?> jClass, @NotNull String moduleName) {
        Intrinsics.checkParameterIsNotNull(jClass, "jClass");
        Intrinsics.checkParameterIsNotNull(moduleName, "moduleName");
        this.jClass = jClass;
        this.moduleName = moduleName;
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    @NotNull
    public Class<?> getJClass() {
        return this.jClass;
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    @NotNull
    public Collection<KCallable<?>> getMembers() {
        throw new KotlinReflectionNotSupportedError();
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof PackageReference) && Intrinsics.areEqual(getJClass(), ((PackageReference) obj).getJClass());
    }

    public int hashCode() {
        return getJClass().hashCode();
    }

    @NotNull
    public String toString() {
        return getJClass().toString() + " (Kotlin reflection is not available)";
    }
}
