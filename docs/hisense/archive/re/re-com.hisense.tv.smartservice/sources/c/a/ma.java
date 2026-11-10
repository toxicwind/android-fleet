package c.a;

import com.google.common.base.MoreObjects;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ma<ReqT, RespT> extends AbstractC0226j<ReqT, RespT> {
    @Override // c.a.AbstractC0226j
    public abstract void cancel(String str, Throwable th);

    public abstract AbstractC0226j<?, ?> delegate();

    @Override // c.a.AbstractC0226j
    public C0086b getAttributes() {
        return delegate().getAttributes();
    }

    @Override // c.a.AbstractC0226j
    public void halfClose() {
        delegate().halfClose();
    }

    @Override // c.a.AbstractC0226j
    public boolean isReady() {
        return delegate().isReady();
    }

    @Override // c.a.AbstractC0226j
    public abstract void request(int i);

    @Override // c.a.AbstractC0226j
    public abstract void setMessageCompression(boolean z);

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
