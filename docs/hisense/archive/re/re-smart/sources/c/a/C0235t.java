package c.a;

import com.google.common.base.Preconditions;

/* JADX INFO: renamed from: c.a.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0235t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final EnumC0234s f1906a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final za f1907b;

    public C0235t(EnumC0234s enumC0234s, za zaVar) {
        Preconditions.checkNotNull(enumC0234s, "state is null");
        this.f1906a = enumC0234s;
        Preconditions.checkNotNull(zaVar, "status is null");
        this.f1907b = zaVar;
    }

    public static C0235t a(EnumC0234s enumC0234s) {
        Preconditions.checkArgument(enumC0234s != EnumC0234s.TRANSIENT_FAILURE, "state is TRANSIENT_ERROR. Use forError() instead");
        return new C0235t(enumC0234s, za.f1936c);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0235t)) {
            return false;
        }
        C0235t c0235t = (C0235t) obj;
        return this.f1906a.equals(c0235t.f1906a) && this.f1907b.equals(c0235t.f1907b);
    }

    public int hashCode() {
        return this.f1906a.hashCode() ^ this.f1907b.hashCode();
    }

    public String toString() {
        if (this.f1907b.c()) {
            return this.f1906a.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1906a);
        sb.append("(");
        return b.a.a.a.a.a(sb, this.f1907b, ")");
    }
}
