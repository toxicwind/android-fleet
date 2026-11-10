package c.a;

import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes2.dex */
public final class O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicLong f836a = new AtomicLong();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f837b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f838c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f839d;

    public O(String str, String str2, long j) {
        Preconditions.checkNotNull(str, "typeName");
        Preconditions.checkArgument(!str.isEmpty(), "empty type");
        this.f837b = str;
        this.f838c = str2;
        this.f839d = j;
    }

    public static O a(String str, String str2) {
        return new O(str, str2, f836a.incrementAndGet());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f837b + "<" + this.f839d + ">");
        if (this.f838c != null) {
            sb.append(": (");
            sb.append(this.f838c);
            sb.append(')');
        }
        return sb.toString();
    }

    public static O a(Class<?> cls, String str) {
        Preconditions.checkNotNull(cls, "type");
        String simpleName = cls.getSimpleName();
        if (simpleName.isEmpty()) {
            simpleName = cls.getName().substring(cls.getPackage().getName().length() + 1);
        }
        return a(simpleName, str);
    }
}
