package c.a.b;

import c.a.C0238w;

/* JADX INFO: loaded from: classes2.dex */
public class Ca implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0238w f920a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Na f921b;

    public Ca(Na na, C0238w c0238w) {
        this.f921b = na;
        this.f920a = c0238w;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f921b.f1088c.a(this.f920a);
    }
}
