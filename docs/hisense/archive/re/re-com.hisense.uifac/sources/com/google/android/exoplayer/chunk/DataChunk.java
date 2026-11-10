package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class DataChunk extends Chunk {
    private byte[] data;
    private int limit;
    private volatile boolean loadCanceled;

    protected abstract void consume(byte[] bArr, int i) throws IOException;

    public DataChunk(DataSource dataSource, DataSpec dataSpec, int i, int i2, Format format, int i3, byte[] bArr) {
        super(dataSource, dataSpec, i, i2, format, i3);
        this.data = bArr;
    }

    public byte[] getDataHolder() {
        return this.data;
    }

    @Override // com.google.android.exoplayer.chunk.Chunk
    public long bytesLoaded() {
        return this.limit;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public final void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public final boolean isLoadCanceled() {
        return this.loadCanceled;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public final void load() throws InterruptedException, IOException {
        try {
            this.dataSource.open(this.dataSpec);
            int i = 0;
            this.limit = 0;
            while (i != -1 && !this.loadCanceled) {
                maybeExpandData();
                i = this.dataSource.read(this.data, this.limit, 16384);
                if (i != -1) {
                    this.limit += i;
                }
            }
            if (!this.loadCanceled) {
                consume(this.data, this.limit);
            }
        } finally {
            Util.closeQuietly(this.dataSource);
        }
    }

    private void maybeExpandData() {
        byte[] bArr = this.data;
        if (bArr == null) {
            this.data = new byte[16384];
        } else if (bArr.length < this.limit + 16384) {
            this.data = Arrays.copyOf(bArr, bArr.length + 16384);
        }
    }
}
