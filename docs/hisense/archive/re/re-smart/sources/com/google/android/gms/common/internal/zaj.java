package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;

/* JADX INFO: Add missing generic type declarations: [R, T] */
/* JADX INFO: loaded from: classes.dex */
public final class zaj<R, T> implements PendingResultUtil.ResultConverter<R, T> {
    public final /* synthetic */ Response zapg;

    public zaj(Response response) {
        this.zapg = response;
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Object convert(Result result) {
        this.zapg.setResult(result);
        return this.zapg;
    }
}
