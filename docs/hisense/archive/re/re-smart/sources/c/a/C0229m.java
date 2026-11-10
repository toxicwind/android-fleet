package c.a;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: c.a.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0229m {

    /* JADX INFO: renamed from: c.a.m$a */
    private static class a extends AbstractC0222g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AbstractC0222g f1873a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final InterfaceC0227k f1874b;

        public /* synthetic */ a(AbstractC0222g abstractC0222g, InterfaceC0227k interfaceC0227k, C0228l c0228l) {
            this.f1873a = abstractC0222g;
            Preconditions.checkNotNull(interfaceC0227k, "interceptor");
            this.f1874b = interfaceC0227k;
        }

        @Override // c.a.AbstractC0222g
        public <ReqT, RespT> AbstractC0226j<ReqT, RespT> a(C0223ga<ReqT, RespT> c0223ga, C0220f c0220f) {
            return this.f1874b.a(c0223ga, c0220f, this.f1873a);
        }

        @Override // c.a.AbstractC0222g
        public String b() {
            return this.f1873a.b();
        }
    }

    public static AbstractC0222g a(AbstractC0222g abstractC0222g, List<? extends InterfaceC0227k> list) {
        Preconditions.checkNotNull(abstractC0222g, "channel");
        Iterator<? extends InterfaceC0227k> it = list.iterator();
        while (it.hasNext()) {
            abstractC0222g = new a(abstractC0222g, it.next(), null);
        }
        return abstractC0222g;
    }
}
