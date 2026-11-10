package okhttp3.internal.http2;

/* JADX INFO: loaded from: classes.dex */
final class H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final H[] f232a;
    final int b;
    final int c;

    H() {
        this.f232a = new H[256];
        this.b = 0;
        this.c = 0;
    }

    H(int i, int i2) {
        this.f232a = null;
        this.b = i;
        int i3 = i2 & 7;
        this.c = i3 == 0 ? 8 : i3;
    }
}
