package c.a.c.a;

/* JADX INFO: loaded from: classes2.dex */
public enum k {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2");


    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f1666f;

    k(String str) {
        this.f1666f = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.f1666f;
    }
}
