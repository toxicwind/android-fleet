package b.d.a.a;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class e implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f189a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f190b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f191c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f192d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final transient Object f193e;

    static {
        new e("N/A", -1L, -1L, -1, -1);
    }

    public e(Object obj, long j, int i, int i2) {
        this.f193e = obj;
        this.f189a = -1L;
        this.f190b = j;
        this.f191c = i;
        this.f192d = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        Object obj2 = this.f193e;
        if (obj2 == null) {
            if (eVar.f193e != null) {
                return false;
            }
        } else if (!obj2.equals(eVar.f193e)) {
            return false;
        }
        return this.f191c == eVar.f191c && this.f192d == eVar.f192d && this.f190b == eVar.f190b && this.f189a == eVar.f189a;
    }

    public int hashCode() {
        Object obj = this.f193e;
        return ((((obj == null ? 1 : obj.hashCode()) ^ this.f191c) + this.f192d) ^ ((int) this.f190b)) + ((int) this.f189a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(80);
        sb.append("[Source: ");
        Object obj = this.f193e;
        if (obj == null) {
            sb.append("UNKNOWN");
        } else {
            sb.append(obj.toString());
        }
        sb.append("; line: ");
        sb.append(this.f191c);
        sb.append(", column: ");
        sb.append(this.f192d);
        sb.append(']');
        return sb.toString();
    }

    public e(Object obj, long j, long j2, int i, int i2) {
        this.f193e = obj;
        this.f189a = j;
        this.f190b = j2;
        this.f191c = i;
        this.f192d = i2;
    }
}
