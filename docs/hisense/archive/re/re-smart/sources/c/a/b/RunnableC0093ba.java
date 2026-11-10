package c.a.b;

import c.a.AbstractC0226j;
import c.a.C0219ea;

/* JADX INFO: renamed from: c.a.b.ba, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0093ba implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AbstractC0226j.a f1230a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0219ea f1231b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ C0138ka f1232c;

    public RunnableC0093ba(C0138ka c0138ka, AbstractC0226j.a aVar, C0219ea c0219ea) {
        this.f1232c = c0138ka;
        this.f1230a = aVar;
        this.f1231b = c0219ea;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1232c.h.start(this.f1230a, this.f1231b);
    }
}
