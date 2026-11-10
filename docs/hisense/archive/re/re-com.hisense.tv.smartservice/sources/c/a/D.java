package c.a;

/* JADX INFO: loaded from: classes2.dex */
public abstract class D<ReqT, RespT> extends ma<ReqT, RespT> {
    @Override // c.a.ma, c.a.AbstractC0226j
    public void cancel(String str, Throwable th) {
        delegate().cancel(str, th);
    }

    @Override // c.a.ma
    public abstract AbstractC0226j<ReqT, RespT> delegate();

    @Override // c.a.ma, c.a.AbstractC0226j
    public /* bridge */ /* synthetic */ C0086b getAttributes() {
        return super.getAttributes();
    }

    @Override // c.a.ma, c.a.AbstractC0226j
    public /* bridge */ /* synthetic */ void halfClose() {
        super.halfClose();
    }

    @Override // c.a.ma, c.a.AbstractC0226j
    public /* bridge */ /* synthetic */ boolean isReady() {
        return super.isReady();
    }

    @Override // c.a.ma, c.a.AbstractC0226j
    public void request(int i) {
        delegate().request(i);
    }

    @Override // c.a.AbstractC0226j
    public void sendMessage(ReqT reqt) {
        delegate().sendMessage(reqt);
    }

    @Override // c.a.ma, c.a.AbstractC0226j
    public void setMessageCompression(boolean z) {
        delegate().setMessageCompression(z);
    }

    @Override // c.a.AbstractC0226j
    public void start(AbstractC0226j.a<RespT> aVar, C0219ea c0219ea) {
        delegate().start(aVar, c0219ea);
    }

    @Override // c.a.ma
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
