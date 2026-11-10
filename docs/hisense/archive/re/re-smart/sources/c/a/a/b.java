package c.a.a;

import android.annotation.TargetApi;

/* JADX INFO: loaded from: classes2.dex */
public class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d.a.C0006a f862a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ d.a f863b;

    public b(d.a aVar, d.a.C0006a c0006a) {
        this.f863b = aVar;
        this.f862a = c0006a;
    }

    @Override // java.lang.Runnable
    @TargetApi(21)
    public void run() {
        this.f863b.f870c.unregisterNetworkCallback(this.f862a);
    }
}
