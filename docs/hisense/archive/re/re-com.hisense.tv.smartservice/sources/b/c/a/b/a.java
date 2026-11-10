package b.c.a.b;

import java.text.DateFormat;

/* JADX INFO: loaded from: classes.dex */
public class a extends ThreadLocal<DateFormat> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b f46a;

    public a(b bVar) {
        this.f46a = bVar;
    }

    @Override // java.lang.ThreadLocal
    public DateFormat initialValue() {
        return ((e) this.f46a).f48a;
    }
}
