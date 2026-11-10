package okhttp3.internal.http2;

import okio.ByteString;

/* JADX INFO: renamed from: okhttp3.internal.http2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0039b {
    public static final ByteString d = ByteString.h(":");
    public static final ByteString e = ByteString.h(":status");
    public static final ByteString f = ByteString.h(":method");
    public static final ByteString g = ByteString.h(":path");
    public static final ByteString h = ByteString.h(":scheme");
    public static final ByteString i = ByteString.h(":authority");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ByteString f236a;
    public final ByteString b;
    final int c;

    public C0039b(String str, String str2) {
        this(ByteString.h(str), ByteString.h(str2));
    }

    public C0039b(ByteString byteString, String str) {
        this(byteString, ByteString.h(str));
    }

    public C0039b(ByteString byteString, ByteString byteString2) {
        this.f236a = byteString;
        this.b = byteString2;
        this.c = byteString.r() + 32 + byteString2.r();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0039b)) {
            return false;
        }
        C0039b c0039b = (C0039b) obj;
        return this.f236a.equals(c0039b.f236a) && this.b.equals(c0039b.b);
    }

    public int hashCode() {
        return ((527 + this.f236a.hashCode()) * 31) + this.b.hashCode();
    }

    public String toString() {
        return okhttp3.g0.e.r("%s: %s", this.f236a.w(), this.b.w());
    }
}
