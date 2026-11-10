package c.a.a;

import android.annotation.TargetApi;

/* JADX INFO: loaded from: classes2.dex */
public class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d.a.b f864a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ d.a f865b;

    public c(d.a aVar, d.a.b bVar) {
        this.f865b = aVar;
        this.f864a = bVar;
    }

    @Override // java.lang.Runnable
    @TargetApi(21)
    public void run() {
        this.f865b.f869b.unregisterReceiver(this.f864a);
    }
}
