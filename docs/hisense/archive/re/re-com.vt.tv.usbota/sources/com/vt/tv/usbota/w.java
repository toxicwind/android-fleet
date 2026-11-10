package com.vt.tv.usbota;

/* JADX INFO: loaded from: classes.dex */
class w implements j {
    private com.vt.tv.usbota.F.n a;
    final /* synthetic */ E b;

    w(E e) {
        this.b = e;
    }

    @Override // com.vt.tv.usbota.j
    public void a(int i) {
        if (this.a != null) {
            this.b.b.post(new v(this));
        }
    }

    public void c(com.vt.tv.usbota.F.n nVar) {
        this.a = nVar;
    }
}
