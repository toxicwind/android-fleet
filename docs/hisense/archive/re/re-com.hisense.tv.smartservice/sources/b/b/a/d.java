package b.b.a;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static d f28a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f30c;
    public String g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f29b = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f31d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f32e = 1;
    public int h = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List<g> f33f = new ArrayList();

    public static d a() {
        if (f28a == null) {
            synchronized (d.class) {
                if (f28a == null) {
                    f28a = new d();
                }
            }
        }
        return f28a;
    }

    public d a(Class<? extends g>... clsArr) {
        for (Class<? extends g> cls : clsArr) {
            try {
                this.f33f.add(0, cls.newInstance());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return this;
    }
}
