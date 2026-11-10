package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Add missing generic type declarations: [A, L] */
/* JADX INFO: loaded from: classes.dex */
public final class zaca<A, L> extends RegisterListenerMethod<A, L> {
    public final /* synthetic */ RegistrationMethods.Builder zakk;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaca(RegistrationMethods.Builder builder, ListenerHolder listenerHolder, Feature[] featureArr, boolean z) {
        super(listenerHolder, featureArr, z);
        this.zakk = builder;
    }

    /* JADX WARN: Incorrect types in method signature: (TA;Lcom/google/android/gms/tasks/TaskCompletionSource<Ljava/lang/Void;>;)V */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    public final void registerListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        this.zakk.zake.accept((A) anyClient, (TaskCompletionSource<Void>) taskCompletionSource);
    }
}
