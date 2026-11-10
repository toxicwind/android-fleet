package c.a.b;

import c.a.C0236u;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Z implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0236u f1203a;

    public Z(C0236u c0236u) {
        this.f1203a = c0236u;
    }

    public abstract void a();

    @Override // java.lang.Runnable
    public final void run() {
        C0236u c0236uA = this.f1203a.a();
        try {
            a();
        } finally {
            this.f1203a.a(c0236uA);
        }
    }
}
