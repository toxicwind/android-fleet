package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zaal extends zaau {
    public final /* synthetic */ zaak zafz;
    public final Map<Api.Client, zaam> zagn;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaal(zaak zaakVar, Map<Api.Client, zaam> map) {
        super(zaakVar, null);
        this.zafz = zaakVar;
        this.zagn = map;
    }

    @Override // com.google.android.gms.common.api.internal.zaau
    @WorkerThread
    public final void zaal() {
        com.google.android.gms.signin.zac zacVar;
        GoogleApiAvailabilityCache googleApiAvailabilityCache = new GoogleApiAvailabilityCache(this.zafz.zaey);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Api.Client client : this.zagn.keySet()) {
            if (!client.requiresGooglePlayServices() || this.zagn.get(client).zaee) {
                arrayList2.add(client);
            } else {
                arrayList.add(client);
            }
        }
        int clientAvailability = -1;
        int i = 0;
        if (!arrayList.isEmpty()) {
            int size = arrayList.size();
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                clientAvailability = googleApiAvailabilityCache.getClientAvailability(this.zafz.mContext, (Api.Client) obj);
                if (clientAvailability != 0) {
                    break;
                }
            }
        } else {
            int size2 = arrayList2.size();
            while (i < size2) {
                Object obj2 = arrayList2.get(i);
                i++;
                clientAvailability = googleApiAvailabilityCache.getClientAvailability(this.zafz.mContext, (Api.Client) obj2);
                if (clientAvailability == 0) {
                    break;
                }
            }
        }
        if (clientAvailability != 0) {
            ConnectionResult connectionResult = new ConnectionResult(clientAvailability, null);
            zaak zaakVar = this.zafz;
            zaakVar.zafv.zaa(new zaao(this, zaakVar, connectionResult));
            return;
        }
        zaak zaakVar2 = this.zafz;
        if (zaakVar2.zagh && (zacVar = zaakVar2.zagf) != null) {
            zacVar.connect();
        }
        for (Api.Client client2 : this.zagn.keySet()) {
            zaam zaamVar = this.zagn.get(client2);
            if (!client2.requiresGooglePlayServices() || googleApiAvailabilityCache.getClientAvailability(this.zafz.mContext, client2) == 0) {
                client2.connect(zaamVar);
            } else {
                zaak zaakVar3 = this.zafz;
                zaakVar3.zafv.zaa(new zaan(this, zaakVar3, zaamVar));
            }
        }
    }
}
