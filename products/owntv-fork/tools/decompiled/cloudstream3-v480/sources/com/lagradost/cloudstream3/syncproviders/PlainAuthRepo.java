package com.lagradost.cloudstream3.syncproviders;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthRepo.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/PlainAuthRepo;", "Lcom/lagradost/cloudstream3/syncproviders/AuthRepo;", "api", "Lcom/lagradost/cloudstream3/syncproviders/AuthAPI;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/AuthAPI;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PlainAuthRepo extends AuthRepo {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlainAuthRepo(AuthAPI api) {
        super(api);
        Intrinsics.checkNotNullParameter(api, "api");
    }
}
