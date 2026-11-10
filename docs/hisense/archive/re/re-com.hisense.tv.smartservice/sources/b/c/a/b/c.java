package b.c.a.b;

import java.text.DateFormat;

/* JADX INFO: loaded from: classes.dex */
public class c extends ThreadLocal<DateFormat> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d f47a;

    public c(d dVar) {
        this.f47a = dVar;
    }

    @Override // java.lang.ThreadLocal
    public DateFormat initialValue() {
        return this.f47a.a();
    }
}
