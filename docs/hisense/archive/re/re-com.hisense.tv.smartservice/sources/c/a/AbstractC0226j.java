package c.a;

/* JADX INFO: renamed from: c.a.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0226j<ReqT, RespT> {

    /* JADX INFO: renamed from: c.a.j$a */
    public static abstract class a<T> {
        public void onClose(za zaVar, C0219ea c0219ea) {
        }

        public void onHeaders(C0219ea c0219ea) {
        }

        public void onMessage(T t) {
        }

        public void onReady() {
        }
    }

    public abstract void cancel(String str, Throwable th);

    public C0086b getAttributes() {
        return C0086b.f876a;
    }

    public abstract void halfClose();

    public boolean isReady() {
        return true;
    }

    public abstract void request(int i);

    public abstract void sendMessage(ReqT reqt);

    public void setMessageCompression(boolean z) {
    }

    public abstract void start(a<RespT> aVar, C0219ea c0219ea);
}
