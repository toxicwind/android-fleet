package com.google.android.exoplayer.text.eia608;

/* JADX INFO: loaded from: classes.dex */
final class ClosedCaptionCtrl extends ClosedCaption {
    public final byte cc1;
    public final byte cc2;

    protected ClosedCaptionCtrl(byte b, byte b2) {
        super(0);
        this.cc1 = b;
        this.cc2 = b2;
    }

    public boolean isMiscCode() {
        byte b;
        byte b2 = this.cc1;
        return (b2 == 20 || b2 == 28) && (b = this.cc2) >= 32 && b <= 47;
    }

    public boolean isPreambleAddressCode() {
        byte b;
        byte b2 = this.cc1;
        return b2 >= 16 && b2 <= 31 && (b = this.cc2) >= 64 && b <= 127;
    }

    public boolean isRepeatable() {
        byte b = this.cc1;
        return b >= 16 && b <= 31;
    }
}
