package b.b.a;

/* JADX INFO: loaded from: classes.dex */
public class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static c f20a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f21b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f22c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f23d = "%d{yyyyMMdd}.txt";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f24e;

    public static c c() {
        if (f20a == null) {
            synchronized (c.class) {
                if (f20a == null) {
                    f20a = new c();
                }
            }
        }
        return f20a;
    }

    public void a() {
    }

    public void b() {
    }
}
