package com.google.android.exoplayer.upstream;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class AssetDataSource implements UriDataSource {
    private final AssetManager assetManager;
    private long bytesRemaining;
    private InputStream inputStream;
    private final TransferListener listener;
    private boolean opened;
    private String uriString;

    public static final class AssetDataSourceException extends IOException {
        public AssetDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public AssetDataSource(Context context, TransferListener transferListener) {
        this.assetManager = context.getAssets();
        this.listener = transferListener;
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public long open(DataSpec dataSpec) throws AssetDataSourceException {
        try {
            this.uriString = dataSpec.uri.toString();
            String path = dataSpec.uri.getPath();
            if (path.startsWith("/android_asset/")) {
                path = path.substring(15);
            } else if (path.startsWith("/")) {
                path = path.substring(1);
            }
            this.uriString = dataSpec.uri.toString();
            InputStream inputStreamOpen = this.assetManager.open(path, 1);
            this.inputStream = inputStreamOpen;
            if (inputStreamOpen.skip(dataSpec.position) < dataSpec.position) {
                throw new EOFException();
            }
            if (dataSpec.length != -1) {
                this.bytesRemaining = dataSpec.length;
            } else {
                long jAvailable = this.inputStream.available();
                this.bytesRemaining = jAvailable;
                if (jAvailable == 2147483647L) {
                    this.bytesRemaining = -1L;
                }
            }
            this.opened = true;
            TransferListener transferListener = this.listener;
            if (transferListener != null) {
                transferListener.onTransferStart();
            }
            return this.bytesRemaining;
        } catch (IOException e) {
            throw new AssetDataSourceException(e);
        }
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws AssetDataSourceException {
        long j = this.bytesRemaining;
        if (j == 0) {
            return -1;
        }
        if (j != -1) {
            try {
                i2 = (int) Math.min(j, i2);
            } catch (IOException e) {
                throw new AssetDataSourceException(e);
            }
        }
        int i3 = this.inputStream.read(bArr, i, i2);
        if (i3 > 0) {
            long j2 = this.bytesRemaining;
            if (j2 != -1) {
                this.bytesRemaining = j2 - ((long) i3);
            }
            TransferListener transferListener = this.listener;
            if (transferListener != null) {
                transferListener.onBytesTransferred(i3);
            }
        }
        return i3;
    }

    @Override // com.google.android.exoplayer.upstream.UriDataSource
    public String getUri() {
        return this.uriString;
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public void close() throws AssetDataSourceException {
        this.uriString = null;
        InputStream inputStream = this.inputStream;
        if (inputStream != null) {
            try {
                try {
                    inputStream.close();
                    this.inputStream = null;
                    if (this.opened) {
                        this.opened = false;
                        TransferListener transferListener = this.listener;
                        if (transferListener != null) {
                            transferListener.onTransferEnd();
                        }
                    }
                } catch (IOException e) {
                    throw new AssetDataSourceException(e);
                }
            } catch (Throwable th) {
                this.inputStream = null;
                if (this.opened) {
                    this.opened = false;
                    TransferListener transferListener2 = this.listener;
                    if (transferListener2 != null) {
                        transferListener2.onTransferEnd();
                    }
                }
                throw th;
            }
        }
    }
}
