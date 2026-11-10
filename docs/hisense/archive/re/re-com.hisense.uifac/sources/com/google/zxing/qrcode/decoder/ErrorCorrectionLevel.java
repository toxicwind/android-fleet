package com.google.zxing.qrcode.decoder;

/* JADX INFO: loaded from: classes.dex */
public enum ErrorCorrectionLevel {
    L(1),
    M(0),
    Q(3),
    H(2);

    private final int bits;

    ErrorCorrectionLevel(int i) {
        this.bits = i;
    }

    public int getBits() {
        return this.bits;
    }
}
