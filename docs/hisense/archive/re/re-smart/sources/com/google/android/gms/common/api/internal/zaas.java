package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zaas extends zabd {
    public final /* synthetic */ zaak zagt;
    public final /* synthetic */ com.google.android.gms.signin.internal.zak zagu;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaas(zaap zaapVar, zabb zabbVar, zaak zaakVar, com.google.android.gms.signin.internal.zak zakVar) {
        super(zabbVar);
        this.zagt = zaakVar;
        this.zagu = zakVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final void zaal() {
        this.zagt.zaa(this.zagu);
    }
}
