package b.g.a;

import com.google.firebase.storage.network.NetworkRequest;

/* JADX INFO: loaded from: classes2.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f771a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f772b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f773c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f774d;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f775a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f776b = NetworkRequest.GET;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b.a f777c = new b.a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Object f778d;
    }

    public /* synthetic */ g(a aVar, f fVar) {
        this.f771a = aVar.f775a;
        this.f772b = aVar.f776b;
        this.f773c = aVar.f777c.a();
        this.f774d = aVar.f778d != null ? aVar.f778d : this;
    }

    public String toString() {
        StringBuilder sbA = b.a.a.a.a.a("Request{method=");
        sbA.append(this.f772b);
        sbA.append(", url=");
        sbA.append(this.f771a);
        sbA.append(", tag=");
        Object obj = this.f774d;
        if (obj == this) {
            obj = null;
        }
        return b.a.a.a.a.a(sbA, obj, '}');
    }
}
