package c.a.b;

import c.a.AbstractC0226j;
import c.a.C0219ea;

/* JADX INFO: Add missing generic type declarations: [ReqT, RespT] */
/* JADX INFO: renamed from: c.a.b.sc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0179sc<ReqT, RespT> extends AbstractC0226j<ReqT, RespT> {
    public C0179sc(C0130ic.j jVar) {
    }

    @Override // c.a.AbstractC0226j
    public void cancel(String str, Throwable th) {
    }

    @Override // c.a.AbstractC0226j
    public void halfClose() {
    }

    @Override // c.a.AbstractC0226j
    public void request(int i) {
    }

    @Override // c.a.AbstractC0226j
    public void sendMessage(ReqT reqt) {
    }

    @Override // c.a.AbstractC0226j
    public void start(AbstractC0226j.a<RespT> aVar, C0219ea c0219ea) {
        aVar.onClose(C0130ic.f1274d, new C0219ea());
    }
}
