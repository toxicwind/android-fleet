package okhttp3.g0.g;

import java.io.IOException;
import okio.A;

/* JADX INFO: loaded from: classes.dex */
class g extends m {
    final /* synthetic */ l c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    g(l lVar, A a2) {
        super(a2);
        this.c = lVar;
    }

    @Override // okhttp3.g0.g.m
    protected void a(IOException iOException) {
        this.c.m = true;
    }
}
