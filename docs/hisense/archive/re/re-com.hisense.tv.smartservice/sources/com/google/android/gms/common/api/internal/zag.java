package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes.dex */
public final class zag extends zae<Void> {
    public final RegisterListenerMethod<Api.AnyClient, ?> zact;
    public final UnregisterListenerMethod<Api.AnyClient, ?> zacu;

    public zag(zabv zabvVar, TaskCompletionSource<Void> taskCompletionSource) {
        super(3, taskCompletionSource);
        this.zact = zabvVar.zakc;
        this.zacu = zabvVar.zakd;
    }

    @Override // com.google.android.gms.common.api.internal.zae, com.google.android.gms.common.api.internal.zac
    public final void zaa(@NonNull Status status) {
        this.zacq.trySetException(new ApiException(status));
    }

    @Override // com.google.android.gms.common.api.internal.zae, com.google.android.gms.common.api.internal.zac
    public final /* bridge */ /* synthetic */ void zaa(@NonNull zaz zazVar, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final boolean zab(GoogleApiManager.zaa<?> zaaVar) {
        return this.zact.shouldAutoResolveMissingFeatures();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.google.android.gms.common.api.internal.zae
    public final void zad(GoogleApiManager.zaa<?> zaaVar) {
        this.zact.registerListener(zaaVar.zaad(), this.zacq);
        if (this.zact.getListenerKey() != null) {
            zaaVar.zabi().put(this.zact.getListenerKey(), new zabv(this.zact, this.zacu));
        }
    }

    @Override // com.google.android.gms.common.api.internal.zae, com.google.android.gms.common.api.internal.zac
    public final void zaa(@NonNull RuntimeException runtimeException) {
        this.zacq.trySetException(runtimeException);
    }

    @Override // com.google.android.gms.common.api.internal.zab
    @Nullable
    public final Feature[] zaa(GoogleApiManager.zaa<?> zaaVar) {
        return this.zact.getRequiredFeatures();
    }
}
