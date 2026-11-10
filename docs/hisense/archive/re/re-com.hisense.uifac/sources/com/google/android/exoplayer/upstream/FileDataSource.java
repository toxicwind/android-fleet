package com.google.android.exoplayer.upstream;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes.dex */
public final class FileDataSource implements UriDataSource {
    private long bytesRemaining;
    private RandomAccessFile file;
    private final TransferListener listener;
    private boolean opened;
    private String uriString;

    public static class FileDataSourceException extends IOException {
        public FileDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public FileDataSource(TransferListener transferListener) {
        this.listener = transferListener;
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public long open(DataSpec dataSpec) throws FileDataSourceException {
        try {
            this.uriString = dataSpec.uri.toString();
            RandomAccessFile randomAccessFile = new RandomAccessFile(dataSpec.uri.getPath(), "r");
            this.file = randomAccessFile;
            randomAccessFile.seek(dataSpec.position);
            long length = dataSpec.length == -1 ? this.file.length() - dataSpec.position : dataSpec.length;
            this.bytesRemaining = length;
            if (length < 0) {
                throw new EOFException();
            }
            this.opened = true;
            TransferListener transferListener = this.listener;
            if (transferListener != null) {
                transferListener.onTransferStart();
            }
            return this.bytesRemaining;
        } catch (IOException e) {
            throw new FileDataSourceException(e);
        }
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws FileDataSourceException {
        long j = this.bytesRemaining;
        if (j == 0) {
            return -1;
        }
        try {
            int i3 = this.file.read(bArr, i, (int) Math.min(j, i2));
            if (i3 > 0) {
                this.bytesRemaining -= (long) i3;
                TransferListener transferListener = this.listener;
                if (transferListener != null) {
                    transferListener.onBytesTransferred(i3);
                }
            }
            return i3;
        } catch (IOException e) {
            throw new FileDataSourceException(e);
        }
    }

    @Override // com.google.android.exoplayer.upstream.UriDataSource
    public String getUri() {
        return this.uriString;
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public void close() throws FileDataSourceException {
        this.uriString = null;
        RandomAccessFile randomAccessFile = this.file;
        if (randomAccessFile != null) {
            try {
                try {
                    randomAccessFile.close();
                    this.file = null;
                    if (this.opened) {
                        this.opened = false;
                        TransferListener transferListener = this.listener;
                        if (transferListener != null) {
                            transferListener.onTransferEnd();
                        }
                    }
                } catch (IOException e) {
                    throw new FileDataSourceException(e);
                }
            } catch (Throwable th) {
                this.file = null;
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
