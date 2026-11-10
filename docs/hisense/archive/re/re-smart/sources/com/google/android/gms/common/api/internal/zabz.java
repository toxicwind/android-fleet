package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Add missing generic type declarations: [A, L] */
/* JADX INFO: loaded from: classes.dex */
public final class zabz<A, L> extends UnregisterListenerMethod<A, L> {
    public final /* synthetic */ RegistrationMethods.Builder zakk;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zabz(RegistrationMethods.Builder builder, ListenerHolder.ListenerKey listenerKey) {
        super(listenerKey);
        this.zakk = builder;
    }

    /* JADX WARN: Incorrect types in method signature: (TA;Lcom/google/android/gms/tasks/TaskCompletionSource<Ljava/lang/Boolean;>;)V */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    public final void unregisterListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        this.zakk.zakf.accept((A) anyClient, (TaskCompletionSource<Boolean>) taskCompletionSource);
    }
}
