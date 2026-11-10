package com.google.android.exoplayer.upstream;

/* JADX INFO: loaded from: classes.dex */
public interface TransferListener {
    void onBytesTransferred(int i);

    void onTransferEnd();

    void onTransferStart();
}
