package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zaaq extends zaau {
    public final /* synthetic */ zaak zafz;
    public final ArrayList<Api.Client> zags;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaaq(zaak zaakVar, ArrayList<Api.Client> arrayList) {
        super(zaakVar, null);
        this.zafz = zaakVar;
        this.zags = arrayList;
    }

    @Override // com.google.android.gms.common.api.internal.zaau
    @WorkerThread
    public final void zaal() {
        zaak zaakVar = this.zafz;
        zaakVar.zafv.zaeh.zahe = zaakVar.zaar();
        ArrayList<Api.Client> arrayList = this.zags;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Api.Client client = arrayList.get(i);
            i++;
            zaak zaakVar2 = this.zafz;
            client.getRemoteService(zaakVar2.zagj, zaakVar2.zafv.zaeh.zahe);
        }
    }
}
