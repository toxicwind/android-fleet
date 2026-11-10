package c.a;

import com.google.common.base.Objects;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

/* JADX INFO: renamed from: c.a.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0086b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0086b f876a = new C0086b(Collections.emptyMap());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ boolean f877b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<C0007b<?>, Object> f878c;

    /* JADX INFO: renamed from: c.a.b$b, reason: collision with other inner class name */
    public static final class C0007b<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f881a;

        public C0007b(String str) {
            this.f881a = str;
        }

        public String toString() {
            return this.f881a;
        }
    }

    public C0086b(Map<C0007b<?>, Object> map) {
        if (!f877b && map == null) {
            throw new AssertionError();
        }
        this.f878c = map;
    }

    public a b() {
        return new a(this, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0086b.class != obj.getClass()) {
            return false;
        }
        C0086b c0086b = (C0086b) obj;
        if (this.f878c.size() != c0086b.f878c.size()) {
            return false;
        }
        for (Map.Entry<C0007b<?>, Object> entry : this.f878c.entrySet()) {
            if (!c0086b.f878c.containsKey(entry.getKey()) || !Objects.equal(entry.getValue(), c0086b.f878c.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int iHashCode = 0;
        for (Map.Entry<C0007b<?>, Object> entry : this.f878c.entrySet()) {
            iHashCode += Arrays.hashCode(new Object[]{entry.getKey(), entry.getValue()});
        }
        return iHashCode;
    }

    public String toString() {
        return this.f878c.toString();
    }

    public <T> T a(C0007b<T> c0007b) {
        return (T) this.f878c.get(c0007b);
    }

    public static a a() {
        return new a(f876a, null);
    }

    /* JADX INFO: renamed from: c.a.b$a */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public C0086b f879a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Map<C0007b<?>, Object> f880b;

        public /* synthetic */ a(C0086b c0086b, C0084a c0084a) {
            this.f879a = c0086b;
        }

        public <T> a a(C0007b<T> c0007b, T t) {
            if (this.f880b == null) {
                this.f880b = new IdentityHashMap(1);
            }
            this.f880b.put(c0007b, t);
            return this;
        }

        public <T> a a(C0007b<T> c0007b) {
            if (this.f879a.f878c.containsKey(c0007b)) {
                IdentityHashMap identityHashMap = new IdentityHashMap(this.f879a.f878c);
                identityHashMap.remove(c0007b);
                this.f879a = new C0086b(identityHashMap, null);
            }
            Map<C0007b<?>, Object> map = this.f880b;
            if (map != null) {
                map.remove(c0007b);
            }
            return this;
        }

        public C0086b a() {
            if (this.f880b != null) {
                for (Map.Entry entry : this.f879a.f878c.entrySet()) {
                    if (!this.f880b.containsKey(entry.getKey())) {
                        this.f880b.put((C0007b<?>) entry.getKey(), entry.getValue());
                    }
                }
                this.f879a = new C0086b(this.f880b, null);
                this.f880b = null;
            }
            return this.f879a;
        }
    }

    public /* synthetic */ C0086b(Map map, C0084a c0084a) {
        if (!f877b && map == null) {
            throw new AssertionError();
        }
        this.f878c = map;
    }
}
