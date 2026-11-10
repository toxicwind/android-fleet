package com.google.android.exoplayer.extractor.mp4;

/* JADX INFO: loaded from: classes.dex */
public final class TrackEncryptionBox {
    public final int initializationVectorSize;
    public final byte[] keyId;

    public TrackEncryptionBox(boolean z, int i, byte[] bArr) {
        this.initializationVectorSize = i;
        this.keyId = bArr;
    }
}
