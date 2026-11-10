package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Add missing generic type declarations: [A, ResultT] */
/* JADX INFO: loaded from: classes.dex */
public final class zacj<A, ResultT> extends TaskApiCall<A, ResultT> {
    public final /* synthetic */ TaskApiCall.Builder zakq;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zacj(TaskApiCall.Builder builder, Feature[] featureArr, boolean z) {
        super(featureArr, z, null);
        this.zakq = builder;
    }

    /* JADX WARN: Incorrect types in method signature: (TA;Lcom/google/android/gms/tasks/TaskCompletionSource<TResultT;>;)V */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    public final void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        this.zakq.zakp.accept((A) anyClient, (TaskCompletionSource<ResultT>) taskCompletionSource);
    }
}
