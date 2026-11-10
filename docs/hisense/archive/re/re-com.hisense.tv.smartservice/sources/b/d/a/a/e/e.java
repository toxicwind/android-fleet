package b.d.a.a.e;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class e extends ConcurrentHashMap<String, String> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f204a = new e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f205b;

    public e() {
        super(180, 0.8f, 4);
        this.f205b = new Object();
    }

    public String a(String str) {
        String str2 = get(str);
        if (str2 != null) {
            return str2;
        }
        if (size() >= 180) {
            synchronized (this.f205b) {
                if (size() >= 180) {
                    clear();
                }
            }
        }
        String strIntern = str.intern();
        put(strIntern, strIntern);
        return strIntern;
    }
}
