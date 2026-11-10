package c.a.c;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d f1677a;

    public c(d dVar) {
        this.f1677a = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1677a.f1679b.close();
        try {
            if (this.f1677a.h != null) {
                this.f1677a.h.close();
            }
        } catch (IOException e2) {
            ((r) this.f1677a.f1681d).a(e2);
        }
        try {
            if (this.f1677a.i != null) {
                this.f1677a.i.close();
            }
        } catch (IOException e3) {
            ((r) this.f1677a.f1681d).a(e3);
        }
    }
}
