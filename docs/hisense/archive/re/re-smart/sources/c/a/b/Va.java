package c.a.b;

import c.a.C0086b;
import c.a.C0238w;
import c.a.C0240y;
import c.a.InterfaceC0233q;
import com.google.common.base.MoreObjects;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Va implements M {
    @Override // c.a.b.Md
    public void a(InputStream inputStream) {
        ((Hb) this).f992a.a(inputStream);
    }

    @Override // c.a.b.Md
    public void b(int i) {
        ((Hb) this).f992a.b(i);
    }

    @Override // c.a.b.M
    public void c(int i) {
        ((Hb) this).f992a.c(i);
    }

    @Override // c.a.b.M
    public void d(int i) {
        ((Hb) this).f992a.d(i);
    }

    @Override // c.a.b.Md
    public void flush() {
        ((Hb) this).f992a.flush();
    }

    @Override // c.a.b.M
    public C0086b getAttributes() {
        return ((Hb) this).f992a.getAttributes();
    }

    @Override // c.a.b.Md
    public boolean isReady() {
        return ((Hb) this).f992a.isReady();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", ((Hb) this).f992a).toString();
    }

    @Override // c.a.b.Md
    public void a() {
        ((Hb) this).f992a.a();
    }

    @Override // c.a.b.M
    public void b() {
        ((Hb) this).f992a.b();
    }

    @Override // c.a.b.Md
    public void a(InterfaceC0233q interfaceC0233q) {
        ((Hb) this).f992a.a(interfaceC0233q);
    }

    @Override // c.a.b.M
    public void b(boolean z) {
        ((Hb) this).f992a.b(z);
    }

    @Override // c.a.b.Md
    public void a(boolean z) {
        ((Hb) this).f992a.a(z);
    }

    @Override // c.a.b.M
    public void a(c.a.za zaVar) {
        ((Hb) this).f992a.a(zaVar);
    }

    @Override // c.a.b.M
    public void a(String str) {
        ((Hb) this).f992a.a(str);
    }

    @Override // c.a.b.M
    public void a(C0240y c0240y) {
        ((Hb) this).f992a.a(c0240y);
    }

    @Override // c.a.b.M
    public void a(C0238w c0238w) {
        ((Hb) this).f992a.a(c0238w);
    }

    @Override // c.a.b.M
    public void a(C0198wb c0198wb) {
        ((Hb) this).f992a.a(c0198wb);
    }
}
