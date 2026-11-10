package okhttp3.g0;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final String f183a;

    public b(String str, Object... objArr) {
        this.f183a = e.r(str, objArr);
    }

    protected abstract void k();

    @Override // java.lang.Runnable
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f183a);
        try {
            k();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
