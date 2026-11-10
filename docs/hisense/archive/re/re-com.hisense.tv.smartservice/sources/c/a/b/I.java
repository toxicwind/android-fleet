package c.a.b;

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

    /* JADX WARN: Bottom block not found for handler: Throwable -> 0x0034 */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x004b, code lost:
    
        return;
     */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b() {
        /*
            r3 = this;
            c.a.b.L$a r0 = r3.f1006d
            c.a.za r0 = r0.f1059b
            if (r0 == 0) goto Lc
            c.a.b.Nd$a r0 = r3.f1005c
            c.a.b.C0149mb.a(r0)
            return
        Lc:
            c.a.b.Nd$a r0 = r3.f1005c     // Catch: java.lang.Throwable -> L34
            java.io.InputStream r0 = r0.next()     // Catch: java.lang.Throwable -> L34
            if (r0 == 0) goto L4b
            c.a.b.L$a r1 = r3.f1006d     // Catch: java.lang.Throwable -> L2f
            c.a.j$a<RespT> r1 = r1.f1058a     // Catch: java.lang.Throwable -> L2f
            c.a.b.L$a r2 = r3.f1006d     // Catch: java.lang.Throwable -> L2f
            c.a.b.L r2 = c.a.b.L.this     // Catch: java.lang.Throwable -> L2f
            c.a.ga r2 = c.a.b.L.h(r2)     // Catch: java.lang.Throwable -> L2f
            c.a.ga$b<RespT> r2 = r2.f1836e     // Catch: java.lang.Throwable -> L2f
            c.a.d.a.b$a r2 = (c.a.d.a.b.a) r2     // Catch: java.lang.Throwable -> L2f
            java.lang.Object r2 = r2.a(r0)     // Catch: java.lang.Throwable -> L2f
            r1.onMessage(r2)     // Catch: java.lang.Throwable -> L2f
            r0.close()     // Catch: java.lang.Throwable -> L34
            goto Lc
        L2f:
            r1 = move-exception
            c.a.b.C0149mb.a(r0)     // Catch: java.lang.Throwable -> L34
            throw r1     // Catch: java.lang.Throwable -> L34
        L34:
            r0 = move-exception
            c.a.b.Nd$a r1 = r3.f1005c
            c.a.b.C0149mb.a(r1)
            c.a.b.L$a r1 = r3.f1006d
            c.a.za r2 = c.a.za.f1937d
            c.a.za r0 = r2.b(r0)
            java.lang.String r2 = "Failed to read message."
            c.a.za r0 = r0.b(r2)
            c.a.b.L.a.a(r1, r0)
        L4b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.b.I.b():void");
    }
}
