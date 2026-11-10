package b.c.a;

import b.c.a.a.a.e;
import b.c.a.a.a.f;
import b.c.a.a.a.g;
import b.c.a.a.a.i;
import b.c.a.b.h;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f40a = new e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final g f41b = new g();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Map<Class, a> f42c = new ConcurrentHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ConcurrentHashMap<d, a> f43d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b.c.a.c.a<Class, h> f44e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b.d.a.a.b f45f;

    static {
        f42c.put(String.class, new i());
        f42c.put(Integer.class, new b.c.a.a.a.d());
        f42c.put(Long.class, new f());
        f42c.put(Float.class, new b.c.a.a.a.c());
        f42c.put(Double.class, new b.c.a.a.a.b());
        f42c.put(Boolean.class, new b.c.a.a.a.a());
        f42c.put(Object.class, new b.c.a.a.a.h());
        f42c.put(List.class, f40a);
        f42c.put(ArrayList.class, f40a);
        f42c.put(Map.class, f41b);
        f42c.put(HashMap.class, f41b);
        f43d = new ConcurrentHashMap<>();
        f44e = new b.c.a.c.a<>();
        f44e.a(Date.class, new b.c.a.b.f());
        f44e.a(Calendar.class, new b.c.a.b.e());
        f45f = new b.d.a.a.b();
    }

    public static <E> String a(E e2) {
        return b(e2.getClass()).serialize(e2);
    }

    public static <E> a<E> b(Class<E> cls) {
        a<E> aVarA = a((Class) cls);
        if (aVarA != null) {
            return aVarA;
        }
        throw new c(cls);
    }

    public static <E> a<E> a(Class<E> cls) {
        a<E> aVar = f42c.get(cls);
        if (aVar != null) {
            return aVar;
        }
        try {
            a<E> aVar2 = (a) Class.forName(cls.getName() + "$$JsonObjectMapper", true, cls.getClassLoader()).newInstance();
            try {
                f42c.put(cls, aVar2);
            } catch (Exception unused) {
            }
            return aVar2;
        } catch (Exception unused2) {
            return aVar;
        }
    }

    public static a a() {
        a aVarA;
        d dVar = null;
        if (dVar.f56b.size() == 0) {
            aVarA = a(dVar.f55a);
        } else {
            b.c.a.c.a aVar = new b.c.a.c.a();
            int i = 0;
            if (aVar.a((Object) null) >= 0) {
                int iA = aVar.a((Object) null);
                aVarA = (a) (iA >= 0 ? aVar.h[(iA << 1) + 1] : null);
            } else if (f43d.containsKey(null)) {
                aVarA = f43d.get(null);
            } else {
                try {
                    Constructor<?> constructor = Class.forName(dVar.f55a.getName() + "$$JsonObjectMapper", true, dVar.f55a.getClassLoader()).getDeclaredConstructors()[0];
                    Object[] objArr = new Object[dVar.f56b.size() + 2];
                    objArr[0] = null;
                    objArr[objArr.length - 1] = aVar;
                    while (i < dVar.f56b.size()) {
                        int i2 = i + 1;
                        objArr[i2] = dVar.f56b.get(i);
                        i = i2;
                    }
                    aVarA = (a) constructor.newInstance(objArr);
                    f43d.put(null, aVarA);
                } catch (Exception unused) {
                    aVarA = null;
                }
            }
        }
        if (aVarA != null) {
            return aVarA;
        }
        throw new c(dVar.f55a);
    }
}
