package c.a.b;

import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class Fa implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ InputStream f963a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Na f964b;

    public Fa(Na na, InputStream inputStream) {
        this.f964b = na;
        this.f963a = inputStream;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f964b.f1088c.a(this.f963a);
    }
}
