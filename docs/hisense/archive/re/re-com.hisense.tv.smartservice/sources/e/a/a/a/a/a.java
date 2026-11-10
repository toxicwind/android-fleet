package e.a.a.a.a;

import java.io.Serializable;
import java.io.Writer;

/* JADX INFO: loaded from: classes2.dex */
public class a extends Writer implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final StringBuilder f2146a;

    public a(int i) {
        this.f2146a = new StringBuilder(i);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c2) {
        this.f2146a.append(c2);
        return this;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
    }

    public String toString() {
        return this.f2146a.toString();
    }

    @Override // java.io.Writer
    public void write(String str) {
        if (str != null) {
            this.f2146a.append(str);
        }
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Appendable append(char c2) {
        this.f2146a.append(c2);
        return this;
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        if (cArr != null) {
            this.f2146a.append(cArr, i, i2);
        }
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence) {
        this.f2146a.append(charSequence);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Appendable append(CharSequence charSequence) {
        this.f2146a.append(charSequence);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence, int i, int i2) {
        this.f2146a.append(charSequence, i, i2);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Appendable append(CharSequence charSequence, int i, int i2) {
        this.f2146a.append(charSequence, i, i2);
        return this;
    }
}
