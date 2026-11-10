package okhttp3.internal.connection;

import okhttp3.N;
import okhttp3.O;
import okhttp3.T;
import okhttp3.Y;
import okhttp3.c0;
import okhttp3.g0.h.i;

/* JADX INFO: loaded from: classes.dex */
public final class a implements O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final T f219a;

    public a(T t) {
        this.f219a = t;
    }

    @Override // okhttp3.O
    public c0 a(N n) {
        i iVar = (i) n;
        Y yA = iVar.a();
        h hVarK = iVar.k();
        return iVar.j(yA, hVarK, hVarK.i(this.f219a, n, !yA.g().equals("GET")), hVarK.d());
    }
}
