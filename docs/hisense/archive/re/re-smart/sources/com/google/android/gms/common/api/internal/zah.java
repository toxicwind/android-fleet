package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes.dex */
public final class zah extends zae<Boolean> {
    public final ListenerHolder.ListenerKey<?> zacv;

    public zah(ListenerHolder.ListenerKey<?> listenerKey, TaskCompletionSource<Boolean> taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zacv = listenerKey;
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
        zabv zabvVar = zaaVar.zabi().get(this.zacv);
        return zabvVar != null && zabvVar.zakc.shouldAutoResolveMissingFeatures();
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
    /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r0v5 boolean
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.lambda$visit$0(FinishTypeInference.java:27)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.visit(FinishTypeInference.java:22)
        */
    @Override // com.google.android.gms.common.api.internal.zae
    public final void zad(com.google.android.gms.common.api.internal.GoogleApiManager.zaa<?> r4) {
        /*
            r3 = this;
            java.util.Map r0 = r4.zabi()
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey<?> r1 = r3.zacv
            java.lang.Object r0 = r0.remove(r1)
            com.google.android.gms.common.api.internal.zabv r0 = (com.google.android.gms.common.api.internal.zabv) r0
            if (r0 == 0) goto L1f
            com.google.android.gms.common.api.internal.UnregisterListenerMethod<com.google.android.gms.common.api.Api$AnyClient, ?> r1 = r0.zakd
            com.google.android.gms.common.api.Api$Client r4 = r4.zaad()
            com.google.android.gms.tasks.TaskCompletionSource<T> r2 = r3.zacq
            r1.unregisterListener(r4, r2)
            com.google.android.gms.common.api.internal.RegisterListenerMethod<com.google.android.gms.common.api.Api$AnyClient, ?> r4 = r0.zakc
            r4.clearListener()
            return
        L1f:
            com.google.android.gms.tasks.TaskCompletionSource<T> r4 = r3.zacq
            r0 = 0
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r4.trySetResult(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zah.zad(com.google.android.gms.common.api.internal.GoogleApiManager$zaa):void");
    }

    @Override // com.google.android.gms.common.api.internal.zae, com.google.android.gms.common.api.internal.zac
    public final void zaa(@NonNull RuntimeException runtimeException) {
        this.zacq.trySetException(runtimeException);
    }

    @Override // com.google.android.gms.common.api.internal.zab
    @Nullable
    public final Feature[] zaa(GoogleApiManager.zaa<?> zaaVar) {
        zabv zabvVar = zaaVar.zabi().get(this.zacv);
        if (zabvVar == null) {
            return null;
        }
        return zabvVar.zakc.getRequiredFeatures();
    }
}
