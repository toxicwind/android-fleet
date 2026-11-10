package c.a.c;

import c.a.b.Ud;

/* JADX INFO: loaded from: classes2.dex */
public class w implements Ud {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d.g f1750a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1751b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1752c;

    public w(d.g gVar, int i) {
        this.f1750a = gVar;
        this.f1751b = i;
    }

    public void a(byte[] bArr, int i, int i2) {
        this.f1750a.write(bArr, i, i2);
        this.f1751b -= i2;
        this.f1752c += i2;
    }
}
