package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.GmsClientEventManager;

/* JADX INFO: loaded from: classes.dex */
public final class zaav implements GmsClientEventManager.GmsClientEventState {
    public final /* synthetic */ zaaw zagv;

    public zaav(zaaw zaawVar) {
        this.zagv = zaawVar;
    }

    @Override // com.google.android.gms.common.internal.GmsClientEventManager.GmsClientEventState
    public final Bundle getConnectionHint() {
        return null;
    }

    @Override // com.google.android.gms.common.internal.GmsClientEventManager.GmsClientEventState
    public final boolean isConnected() {
        return this.zagv.isConnected();
    }
}
