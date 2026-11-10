package com.vt.tv.usbota;

/* JADX INFO: loaded from: classes.dex */
class D implements com.vt.tv.usbota.G.d {
    private com.vt.tv.usbota.F.n a;
    final /* synthetic */ E b;

    D(E e) {
        this.b = e;
    }

    @Override // com.vt.tv.usbota.G.d
    public void a(int i, Object... objArr) {
        if (this.a != null) {
            this.b.b.post(new B(this));
        }
    }

    @Override // com.vt.tv.usbota.G.d
    public void b() {
        if (this.a != null) {
            this.b.b.post(new C(this));
        }
    }

    public void d(com.vt.tv.usbota.F.n nVar) {
        this.a = nVar;
    }

    @Override // com.vt.tv.usbota.G.d
    public void onProgress(int i) {
        com.vt.tv.usbota.F.n nVar = this.a;
        if (nVar != null) {
            nVar.a(i);
        }
    }
}
