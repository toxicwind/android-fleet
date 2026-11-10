package okhttp3.g0.g;

import java.io.IOException;
import okio.A;

/* JADX INFO: loaded from: classes.dex */
class h extends m {
    final /* synthetic */ i c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    h(i iVar, A a2) {
        super(a2);
        this.c = iVar;
    }

    @Override // okhttp3.g0.g.m
    protected void a(IOException iOException) {
        synchronized (this.c.d) {
            this.c.c();
        }
    }
}
