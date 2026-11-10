package com.hmct.cloud.sdk.utils;

/* JADX INFO: loaded from: classes2.dex */
public class g extends e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f2090a;

    public g(long j) {
        this.f2090a = (byte) j;
    }

    @Override // com.hmct.cloud.sdk.utils.e
    public final long a() {
        return this.f2090a & 255;
    }

    public final byte b() {
        return this.f2090a;
    }

    public final void a(long j) {
        this.f2090a = (byte) j;
    }
}
