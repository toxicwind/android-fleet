package com.lagradost.cloudstream3.syncproviders;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SyncRepo.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J&\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@¢\u0006\u0004\b\u0019\u0010\u001aJ \u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@¢\u0006\u0004\b\u001c\u0010\u001dJ \u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@¢\u0006\u0004\b \u0010\u001dJ\u0018\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u0014H\u0086@¢\u0006\u0004\b#\u0010$R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/SyncRepo;", "Lcom/lagradost/cloudstream3/syncproviders/AuthRepo;", "api", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/SyncAPI;)V", "getApi", "()Lcom/lagradost/cloudstream3/syncproviders/SyncAPI;", "syncIdName", "Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "getSyncIdName", "()Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", ES6Iterator.VALUE_PROPERTY, "", "requireLibraryRefresh", "getRequireLibraryRefresh", "()Z", "setRequireLibraryRefresh", "(Z)V", "updateStatus", "Lkotlin/Result;", "id", "", "newStatus", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;", "updateStatus-0E7RQCE", "(Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", NotificationCompat.CATEGORY_STATUS, "status-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncResult;", "load-gIAlu-s", "library", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryMetadata;", "library-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SyncRepo extends AuthRepo {
    private final SyncAPI api;
    private final SyncIdName syncIdName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SyncRepo(SyncAPI api) {
        super(api);
        Intrinsics.checkNotNullParameter(api, "api");
        this.api = api;
        this.syncIdName = getApi().getSyncIdName();
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthRepo
    public SyncAPI getApi() {
        return this.api;
    }

    public final SyncIdName getSyncIdName() {
        return this.syncIdName;
    }

    public final boolean getRequireLibraryRefresh() {
        return getApi().getRequireLibraryRefresh();
    }

    public final void setRequireLibraryRefresh(boolean z) {
        getApi().setRequireLibraryRefresh(z);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a0, code lost:
    
        if (r12 == r1) goto L30;
     */
    /* JADX INFO: renamed from: updateStatus-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m8428updateStatus0E7RQCE(java.lang.String r10, com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus r11, kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.Boolean>> r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof com.lagradost.cloudstream3.syncproviders.SyncRepo$updateStatus$1
            if (r0 == 0) goto L14
            r0 = r12
            com.lagradost.cloudstream3.syncproviders.SyncRepo$updateStatus$1 r0 = (com.lagradost.cloudstream3.syncproviders.SyncRepo$updateStatus$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            com.lagradost.cloudstream3.syncproviders.SyncRepo$updateStatus$1 r0 = new com.lagradost.cloudstream3.syncproviders.SyncRepo$updateStatus$1
            r0.<init>(r9, r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L61
            if (r2 == r5) goto L45
            if (r2 != r3) goto L3d
            int r9 = r0.I$0
            java.lang.Object r9 = r0.L$2
            com.lagradost.cloudstream3.syncproviders.SyncRepo r9 = (com.lagradost.cloudstream3.syncproviders.SyncRepo) r9
            java.lang.Object r10 = r0.L$1
            com.lagradost.cloudstream3.syncproviders.SyncAPI$AbstractSyncStatus r10 = (com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus) r10
            java.lang.Object r10 = r0.L$0
            java.lang.String r10 = (java.lang.String) r10
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> Lb8
            goto La3
        L3d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L45:
            int r9 = r0.I$0
            java.lang.Object r10 = r0.L$3
            com.lagradost.cloudstream3.syncproviders.SyncAPI r10 = (com.lagradost.cloudstream3.syncproviders.SyncAPI) r10
            java.lang.Object r11 = r0.L$2
            com.lagradost.cloudstream3.syncproviders.SyncRepo r11 = (com.lagradost.cloudstream3.syncproviders.SyncRepo) r11
            java.lang.Object r2 = r0.L$1
            com.lagradost.cloudstream3.syncproviders.SyncAPI$AbstractSyncStatus r2 = (com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus) r2
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> Lb8
            r8 = r10
            r10 = r9
            r9 = r11
            r11 = r2
            r2 = r12
            r12 = r8
            goto L82
        L61:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.Result$Companion r12 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> Lb8
            r12 = r9
            com.lagradost.cloudstream3.syncproviders.SyncRepo r12 = (com.lagradost.cloudstream3.syncproviders.SyncRepo) r12     // Catch: java.lang.Throwable -> Lb8
            com.lagradost.cloudstream3.syncproviders.SyncAPI r12 = r9.getApi()     // Catch: java.lang.Throwable -> Lb8
            r0.L$0 = r10     // Catch: java.lang.Throwable -> Lb8
            r0.L$1 = r11     // Catch: java.lang.Throwable -> Lb8
            r0.L$2 = r9     // Catch: java.lang.Throwable -> Lb8
            r0.L$3 = r12     // Catch: java.lang.Throwable -> Lb8
            r0.I$0 = r4     // Catch: java.lang.Throwable -> Lb8
            r0.label = r5     // Catch: java.lang.Throwable -> Lb8
            java.lang.Object r2 = r9.freshAuth(r0)     // Catch: java.lang.Throwable -> Lb8
            if (r2 != r1) goto L80
            goto La2
        L80:
            r6 = r10
            r10 = r4
        L82:
            com.lagradost.cloudstream3.syncproviders.AuthData r2 = (com.lagradost.cloudstream3.syncproviders.AuthData) r2     // Catch: java.lang.Throwable -> Lb8
            if (r2 != 0) goto L87
            goto Laf
        L87:
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)     // Catch: java.lang.Throwable -> Lb8
            r0.L$0 = r7     // Catch: java.lang.Throwable -> Lb8
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)     // Catch: java.lang.Throwable -> Lb8
            r0.L$1 = r7     // Catch: java.lang.Throwable -> Lb8
            r0.L$2 = r9     // Catch: java.lang.Throwable -> Lb8
            r7 = 0
            r0.L$3 = r7     // Catch: java.lang.Throwable -> Lb8
            r0.I$0 = r10     // Catch: java.lang.Throwable -> Lb8
            r0.label = r3     // Catch: java.lang.Throwable -> Lb8
            java.lang.Object r12 = r12.updateStatus(r2, r6, r11, r0)     // Catch: java.lang.Throwable -> Lb8
            if (r12 != r1) goto La3
        La2:
            return r1
        La3:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch: java.lang.Throwable -> Lb8
            boolean r10 = r12.booleanValue()     // Catch: java.lang.Throwable -> Lb8
            r9.setRequireLibraryRefresh(r5)     // Catch: java.lang.Throwable -> Lb8
            if (r10 == 0) goto Laf
            r4 = r5
        Laf:
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)     // Catch: java.lang.Throwable -> Lb8
            java.lang.Object r9 = kotlin.Result.m8936constructorimpl(r9)     // Catch: java.lang.Throwable -> Lb8
            return r9
        Lb8:
            r9 = move-exception
            kotlin.Result$Companion r10 = kotlin.Result.INSTANCE
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r9)
            java.lang.Object r9 = kotlin.Result.m8936constructorimpl(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.syncproviders.SyncRepo.m8428updateStatus0E7RQCE(java.lang.String, com.lagradost.cloudstream3.syncproviders.SyncAPI$AbstractSyncStatus, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0093, code lost:
    
        if (r9 == r1) goto L27;
     */
    /* JADX INFO: renamed from: status-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m8427statusgIAlus(java.lang.String r8, kotlin.coroutines.Continuation<? super kotlin.Result<? extends com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus>> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.lagradost.cloudstream3.syncproviders.SyncRepo$status$1
            if (r0 == 0) goto L14
            r0 = r9
            com.lagradost.cloudstream3.syncproviders.SyncRepo$status$1 r0 = (com.lagradost.cloudstream3.syncproviders.SyncRepo$status$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            com.lagradost.cloudstream3.syncproviders.SyncRepo$status$1 r0 = new com.lagradost.cloudstream3.syncproviders.SyncRepo$status$1
            r0.<init>(r7, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L54
            if (r2 == r4) goto L3f
            if (r2 != r3) goto L37
            int r7 = r0.I$0
            java.lang.Object r7 = r0.L$1
            com.lagradost.cloudstream3.syncproviders.SyncRepo r7 = (com.lagradost.cloudstream3.syncproviders.SyncRepo) r7
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L9d
            goto L96
        L37:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3f:
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$2
            com.lagradost.cloudstream3.syncproviders.SyncAPI r8 = (com.lagradost.cloudstream3.syncproviders.SyncAPI) r8
            java.lang.Object r2 = r0.L$1
            com.lagradost.cloudstream3.syncproviders.SyncRepo r2 = (com.lagradost.cloudstream3.syncproviders.SyncRepo) r2
            java.lang.Object r4 = r0.L$0
            java.lang.String r4 = (java.lang.String) r4
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L9d
            r6 = r2
            r2 = r7
            r7 = r6
            goto L7a
        L54:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.Result$Companion r9 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L9d
            r9 = r7
            com.lagradost.cloudstream3.syncproviders.SyncRepo r9 = (com.lagradost.cloudstream3.syncproviders.SyncRepo) r9     // Catch: java.lang.Throwable -> L9d
            com.lagradost.cloudstream3.syncproviders.SyncAPI r9 = r7.getApi()     // Catch: java.lang.Throwable -> L9d
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch: java.lang.Throwable -> L9d
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L9d
            r0.L$2 = r9     // Catch: java.lang.Throwable -> L9d
            r2 = 0
            r0.I$0 = r2     // Catch: java.lang.Throwable -> L9d
            r0.label = r4     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r4 = r7.freshAuth(r0)     // Catch: java.lang.Throwable -> L9d
            if (r4 != r1) goto L76
            goto L95
        L76:
            r6 = r4
            r4 = r8
            r8 = r9
            r9 = r6
        L7a:
            com.lagradost.cloudstream3.syncproviders.AuthData r9 = (com.lagradost.cloudstream3.syncproviders.AuthData) r9     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch: java.lang.Throwable -> L9d
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch: java.lang.Throwable -> L9d
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L9d
            r7 = 0
            r0.L$2 = r7     // Catch: java.lang.Throwable -> L9d
            r0.I$0 = r2     // Catch: java.lang.Throwable -> L9d
            r0.label = r3     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r9 = r8.status(r9, r4, r0)     // Catch: java.lang.Throwable -> L9d
            if (r9 != r1) goto L96
        L95:
            return r1
        L96:
            com.lagradost.cloudstream3.syncproviders.SyncAPI$AbstractSyncStatus r9 = (com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus) r9     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r7 = kotlin.Result.m8936constructorimpl(r9)     // Catch: java.lang.Throwable -> L9d
            return r7
        L9d:
            r7 = move-exception
            kotlin.Result$Companion r8 = kotlin.Result.INSTANCE
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m8936constructorimpl(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.syncproviders.SyncRepo.m8427statusgIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0093, code lost:
    
        if (r9 == r1) goto L27;
     */
    /* JADX INFO: renamed from: load-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m8426loadgIAlus(java.lang.String r8, kotlin.coroutines.Continuation<? super kotlin.Result<com.lagradost.cloudstream3.syncproviders.SyncAPI.SyncResult>> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.lagradost.cloudstream3.syncproviders.SyncRepo$load$1
            if (r0 == 0) goto L14
            r0 = r9
            com.lagradost.cloudstream3.syncproviders.SyncRepo$load$1 r0 = (com.lagradost.cloudstream3.syncproviders.SyncRepo$load$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            com.lagradost.cloudstream3.syncproviders.SyncRepo$load$1 r0 = new com.lagradost.cloudstream3.syncproviders.SyncRepo$load$1
            r0.<init>(r7, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L54
            if (r2 == r4) goto L3f
            if (r2 != r3) goto L37
            int r7 = r0.I$0
            java.lang.Object r7 = r0.L$1
            com.lagradost.cloudstream3.syncproviders.SyncRepo r7 = (com.lagradost.cloudstream3.syncproviders.SyncRepo) r7
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L9d
            goto L96
        L37:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3f:
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$2
            com.lagradost.cloudstream3.syncproviders.SyncAPI r8 = (com.lagradost.cloudstream3.syncproviders.SyncAPI) r8
            java.lang.Object r2 = r0.L$1
            com.lagradost.cloudstream3.syncproviders.SyncRepo r2 = (com.lagradost.cloudstream3.syncproviders.SyncRepo) r2
            java.lang.Object r4 = r0.L$0
            java.lang.String r4 = (java.lang.String) r4
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L9d
            r6 = r2
            r2 = r7
            r7 = r6
            goto L7a
        L54:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.Result$Companion r9 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L9d
            r9 = r7
            com.lagradost.cloudstream3.syncproviders.SyncRepo r9 = (com.lagradost.cloudstream3.syncproviders.SyncRepo) r9     // Catch: java.lang.Throwable -> L9d
            com.lagradost.cloudstream3.syncproviders.SyncAPI r9 = r7.getApi()     // Catch: java.lang.Throwable -> L9d
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch: java.lang.Throwable -> L9d
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L9d
            r0.L$2 = r9     // Catch: java.lang.Throwable -> L9d
            r2 = 0
            r0.I$0 = r2     // Catch: java.lang.Throwable -> L9d
            r0.label = r4     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r4 = r7.freshAuth(r0)     // Catch: java.lang.Throwable -> L9d
            if (r4 != r1) goto L76
            goto L95
        L76:
            r6 = r4
            r4 = r8
            r8 = r9
            r9 = r6
        L7a:
            com.lagradost.cloudstream3.syncproviders.AuthData r9 = (com.lagradost.cloudstream3.syncproviders.AuthData) r9     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch: java.lang.Throwable -> L9d
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch: java.lang.Throwable -> L9d
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L9d
            r7 = 0
            r0.L$2 = r7     // Catch: java.lang.Throwable -> L9d
            r0.I$0 = r2     // Catch: java.lang.Throwable -> L9d
            r0.label = r3     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r9 = r8.load(r9, r4, r0)     // Catch: java.lang.Throwable -> L9d
            if (r9 != r1) goto L96
        L95:
            return r1
        L96:
            com.lagradost.cloudstream3.syncproviders.SyncAPI$SyncResult r9 = (com.lagradost.cloudstream3.syncproviders.SyncAPI.SyncResult) r9     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r7 = kotlin.Result.m8936constructorimpl(r9)     // Catch: java.lang.Throwable -> L9d
            return r7
        L9d:
            r7 = move-exception
            kotlin.Result$Companion r8 = kotlin.Result.INSTANCE
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m8936constructorimpl(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.syncproviders.SyncRepo.m8426loadgIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0080, code lost:
    
        if (r7 == r1) goto L26;
     */
    /* JADX INFO: renamed from: library-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m8425libraryIoAF18A(kotlin.coroutines.Continuation<? super kotlin.Result<com.lagradost.cloudstream3.syncproviders.SyncAPI.LibraryMetadata>> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.lagradost.cloudstream3.syncproviders.SyncRepo$library$1
            if (r0 == 0) goto L14
            r0 = r7
            com.lagradost.cloudstream3.syncproviders.SyncRepo$library$1 r0 = (com.lagradost.cloudstream3.syncproviders.SyncRepo$library$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            com.lagradost.cloudstream3.syncproviders.SyncRepo$library$1 r0 = new com.lagradost.cloudstream3.syncproviders.SyncRepo$library$1
            r0.<init>(r6, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4d
            if (r2 == r4) goto L3b
            if (r2 != r3) goto L33
            int r6 = r0.I$0
            java.lang.Object r6 = r0.L$0
            com.lagradost.cloudstream3.syncproviders.SyncRepo r6 = (com.lagradost.cloudstream3.syncproviders.SyncRepo) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L8a
            goto L83
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            int r6 = r0.I$0
            java.lang.Object r2 = r0.L$1
            com.lagradost.cloudstream3.syncproviders.SyncAPI r2 = (com.lagradost.cloudstream3.syncproviders.SyncAPI) r2
            java.lang.Object r4 = r0.L$0
            com.lagradost.cloudstream3.syncproviders.SyncRepo r4 = (com.lagradost.cloudstream3.syncproviders.SyncRepo) r4
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L8a
            r5 = r7
            r7 = r6
            r6 = r4
            r4 = r5
            goto L6d
        L4d:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.Result$Companion r7 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L8a
            r7 = r6
            com.lagradost.cloudstream3.syncproviders.SyncRepo r7 = (com.lagradost.cloudstream3.syncproviders.SyncRepo) r7     // Catch: java.lang.Throwable -> L8a
            com.lagradost.cloudstream3.syncproviders.SyncAPI r2 = r6.getApi()     // Catch: java.lang.Throwable -> L8a
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)     // Catch: java.lang.Throwable -> L8a
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L8a
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L8a
            r7 = 0
            r0.I$0 = r7     // Catch: java.lang.Throwable -> L8a
            r0.label = r4     // Catch: java.lang.Throwable -> L8a
            java.lang.Object r4 = r6.freshAuth(r0)     // Catch: java.lang.Throwable -> L8a
            if (r4 != r1) goto L6d
            goto L82
        L6d:
            com.lagradost.cloudstream3.syncproviders.AuthData r4 = (com.lagradost.cloudstream3.syncproviders.AuthData) r4     // Catch: java.lang.Throwable -> L8a
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)     // Catch: java.lang.Throwable -> L8a
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L8a
            r6 = 0
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L8a
            r0.I$0 = r7     // Catch: java.lang.Throwable -> L8a
            r0.label = r3     // Catch: java.lang.Throwable -> L8a
            java.lang.Object r7 = r2.library(r4, r0)     // Catch: java.lang.Throwable -> L8a
            if (r7 != r1) goto L83
        L82:
            return r1
        L83:
            com.lagradost.cloudstream3.syncproviders.SyncAPI$LibraryMetadata r7 = (com.lagradost.cloudstream3.syncproviders.SyncAPI.LibraryMetadata) r7     // Catch: java.lang.Throwable -> L8a
            java.lang.Object r6 = kotlin.Result.m8936constructorimpl(r7)     // Catch: java.lang.Throwable -> L8a
            return r6
        L8a:
            r6 = move-exception
            kotlin.Result$Companion r7 = kotlin.Result.INSTANCE
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)
            java.lang.Object r6 = kotlin.Result.m8936constructorimpl(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.syncproviders.SyncRepo.m8425libraryIoAF18A(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
