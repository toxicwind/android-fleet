package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Batch extends BasePendingResult<BatchResult> {
    public final Object mLock;
    public int zabc;
    public boolean zabd;
    public boolean zabe;
    public final PendingResult<?>[] zabf;

    public static final class Builder {
        public List<PendingResult<?>> zabg = new ArrayList();
        public GoogleApiClient zabh;

        public Builder(GoogleApiClient googleApiClient) {
            this.zabh = googleApiClient;
        }

        public final <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.zabg.size());
            this.zabg.add(pendingResult);
            return batchResultToken;
        }

        public final Batch build() {
            return new Batch(this.zabg, this.zabh);
        }
    }

    public Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.mLock = new Object();
        this.zabc = list.size();
        this.zabf = new PendingResult[this.zabc];
        if (list.isEmpty()) {
            setResult(new BatchResult(Status.RESULT_SUCCESS, this.zabf));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            PendingResult<?> pendingResult = list.get(i);
            this.zabf[i] = pendingResult;
            pendingResult.addStatusListener(new zaa(this));
        }
    }

    public static /* synthetic */ boolean zab(Batch batch, boolean z) {
        batch.zabd = true;
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult, com.google.android.gms.common.api.PendingResult
    public final void cancel() {
        super.cancel();
        for (PendingResult<?> pendingResult : this.zabf) {
            pendingResult.cancel();
        }
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final BatchResult createFailedResult(Status status) {
        return new BatchResult(status, this.zabf);
    }

    public static /* synthetic */ boolean zaa(Batch batch, boolean z) {
        batch.zabe = true;
        return true;
    }

    public static /* synthetic */ int zab(Batch batch) {
        int i = batch.zabc;
        batch.zabc = i - 1;
        return i;
    }

    public /* synthetic */ Batch(List list, GoogleApiClient googleApiClient, zaa zaaVar) {
        this(list, googleApiClient);
    }
}
