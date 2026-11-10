package c.a.b;

import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
final class I extends Z {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ c.b.b f1004b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Nd.a f1005c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ L.a f1006d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(L.a aVar, c.b.b bVar, Nd.a aVar2) {
        super(L.this.h);
        this.f1006d = aVar;
        this.f1004b = bVar;
        this.f1005c = aVar2;
    }

    @Override // c.a.b.Z
    public void a() {
        c.b.c.f1949a.b("ClientCall$Listener.messagesAvailable", L.this.f1055d);
        c.b.c.f1949a.a(this.f1004b);
        try {
            b();
        } finally {
            c.b.c.f1949a.c("ClientCall$Listener.messagesAvailable", L.this.f1055d);
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void b() {
        if (this.f1006d.f1059b != null) {
            C0149mb.a(this.f1005c);
            return;
        }
        while (true) {
            try {
                InputStream next = this.f1005c.next();
                if (next == null) {
                    return;
                }
                try {
                    this.f1006d.f1058a.onMessage((RespT) ((c.a.d.a.b.a) L.this.f1054c.f1836e).a(next));
                    next.close();
                } catch (Throwable th) {
                    C0149mb.a(next);
                    throw th;
                }
            } catch (Throwable th2) {
                C0149mb.a(this.f1005c);
                L.a.a(this.f1006d, c.a.za.f1937d.b(th2).b("Failed to read message."));
                return;
            }
        }
    }
}
