package kotlin.jvm.internal;

import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: localVariableReferences.kt */
/* JADX INFO: loaded from: classes.dex */
public class LocalVariableReference extends PropertyReference0 {
    @Override // kotlin.jvm.internal.CallableReference
    @NotNull
    public KDeclarationContainer getOwner() {
        LocalVariableReferencesKt.notSupportedError();
        throw null;
    }

    @Override // kotlin.jvm.internal.PropertyReference0
    @Nullable
    public Object get() {
        LocalVariableReferencesKt.notSupportedError();
        throw null;
    }
}
