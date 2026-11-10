package b.d.a.a;

/* JADX INFO: loaded from: classes.dex */
public enum a {
    UTF8("UTF-8", false, 8),
    UTF16_BE("UTF-16BE", true, 16),
    UTF16_LE("UTF-16LE", false, 16),
    UTF32_BE("UTF-32BE", true, 32),
    UTF32_LE("UTF-32LE", false, 32);

    public final String g;
    public final boolean h;
    public final int i;

    a(String str, boolean z, int i) {
        this.g = str;
        this.h = z;
        this.i = i;
    }

    public String a() {
        return this.g;
    }
}
