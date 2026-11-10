package b.e.b.d.f;

import c.a.X;
import com.google.firebase.firestore.remote.GrpcCallProvider;

/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class u implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ GrpcCallProvider f525a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final /* synthetic */ X f526b;

    public /* synthetic */ u(GrpcCallProvider grpcCallProvider, X x) {
        this.f525a = grpcCallProvider;
        this.f526b = x;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f525a.e(this.f526b);
    }
}
