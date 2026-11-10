package okio;

/* JADX INFO: renamed from: okio.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0068c extends Thread {
    C0068c() {
        super("Okio Watchdog");
        setDaemon(true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0015, code lost:
    
        r0.t();
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r2 = this;
        L0:
            java.lang.Class<okio.d> r2 = okio.C0069d.class
            monitor-enter(r2)     // Catch: java.lang.InterruptedException -> L0
            okio.d r0 = okio.C0069d.i()     // Catch: java.lang.Throwable -> L19
            if (r0 != 0) goto Lb
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L19
            goto L0
        Lb:
            okio.d r1 = okio.C0069d.j     // Catch: java.lang.Throwable -> L19
            if (r0 != r1) goto L14
            r0 = 0
            okio.C0069d.j = r0     // Catch: java.lang.Throwable -> L19
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L19
            return
        L14:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L19
            r0.t()     // Catch: java.lang.InterruptedException -> L0
            goto L0
        L19:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L19
            throw r0     // Catch: java.lang.InterruptedException -> L0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.C0068c.run():void");
    }
}
