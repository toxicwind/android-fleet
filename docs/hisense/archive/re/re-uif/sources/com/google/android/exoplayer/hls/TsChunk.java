package com.google.android.exoplayer.hls;

import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.chunk.MediaChunk;
import com.google.android.exoplayer.extractor.DefaultExtractorInput;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class TsChunk extends MediaChunk {
    private long adjustedEndTimeUs;
    private int bytesLoaded;
    public final int discontinuitySequenceNumber;
    public final HlsExtractorWrapper extractorWrapper;
    private final boolean isEncrypted;
    private volatile boolean loadCanceled;

    public TsChunk(DataSource dataSource, DataSpec dataSpec, int i, Format format, long j, long j2, int i2, int i3, HlsExtractorWrapper hlsExtractorWrapper, byte[] bArr, byte[] bArr2) {
        super(buildDataSource(dataSource, bArr, bArr2), dataSpec, i, format, j, j2, i2);
        this.discontinuitySequenceNumber = i3;
        this.extractorWrapper = hlsExtractorWrapper;
        this.isEncrypted = this.dataSource instanceof Aes128DataSource;
        this.adjustedEndTimeUs = j2;
    }

    @Override // com.google.android.exoplayer.chunk.Chunk
    public long bytesLoaded() {
        return this.bytesLoaded;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public boolean isLoadCanceled() {
        return this.loadCanceled;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0028 A[Catch: all -> 0x0068, TRY_LEAVE, TryCatch #0 {all -> 0x0068, blocks: (B:9:0x0016, B:11:0x0028, B:19:0x0048, B:23:0x005b, B:24:0x0067, B:13:0x002f, B:15:0x0033, B:16:0x003a, B:18:0x0046), top: B:28:0x0016, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:15:0x0033 A[Catch: all -> 0x005a, LOOP:0: B:12:0x002d->B:15:0x0033, LOOP_END, TryCatch #1 {all -> 0x005a, blocks: (B:13:0x002f, B:15:0x0033, B:16:0x003a, B:18:0x0046), top: B:29:0x002f, outer: #0 }] */
    /* JADX WARN: Code duplicated, block: B:18:0x0046 A[Catch: all -> 0x005a, TRY_LEAVE, TryCatch #1 {all -> 0x005a, blocks: (B:13:0x002f, B:15:0x0033, B:16:0x003a, B:18:0x0046), top: B:29:0x002f, outer: #0 }] */
    /* JADX WARN: Code duplicated, block: B:29:0x002f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:32:0x003a A[EDGE_INSN: B:32:0x003a->B:16:0x003a BREAK  A[LOOP:0: B:12:0x002d->B:15:0x0033], SYNTHETIC] */
    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public void load() throws InterruptedException, IOException {
        DataSpec remainderDataSpec;
        boolean z;
        DefaultExtractorInput defaultExtractorInput;
        long adjustedEndTimeUs;
        int i = 0;
        try {
            if (this.isEncrypted) {
                remainderDataSpec = this.dataSpec;
                if (this.bytesLoaded != 0) {
                    z = true;
                }
                defaultExtractorInput = new DefaultExtractorInput(this.dataSource, remainderDataSpec.absoluteStreamPosition, this.dataSource.open(remainderDataSpec));
                if (z) {
                    defaultExtractorInput.skipFully(this.bytesLoaded);
                }
                while (i == 0) {
                    try {
                        if (!this.loadCanceled) {
                            break;
                        } else {
                            i = this.extractorWrapper.read(defaultExtractorInput);
                        }
                    } catch (Throwable th) {
                        this.bytesLoaded = (int) (defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
                        throw th;
                    }
                }
                adjustedEndTimeUs = this.extractorWrapper.getAdjustedEndTimeUs();
                if (adjustedEndTimeUs != Long.MIN_VALUE) {
                    this.adjustedEndTimeUs = adjustedEndTimeUs;
                }
                this.bytesLoaded = (int) (defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
                Util.closeQuietly(this.dataSource);
                return;
            }
            remainderDataSpec = Util.getRemainderDataSpec(this.dataSpec, this.bytesLoaded);
            defaultExtractorInput = new DefaultExtractorInput(this.dataSource, remainderDataSpec.absoluteStreamPosition, this.dataSource.open(remainderDataSpec));
            if (z) {
                defaultExtractorInput.skipFully(this.bytesLoaded);
            }
            while (i == 0) {
                if (!this.loadCanceled) {
                    break;
                    break;
                }
                i = this.extractorWrapper.read(defaultExtractorInput);
            }
            adjustedEndTimeUs = this.extractorWrapper.getAdjustedEndTimeUs();
            if (adjustedEndTimeUs != Long.MIN_VALUE) {
                this.adjustedEndTimeUs = adjustedEndTimeUs;
            }
            this.bytesLoaded = (int) (defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
            Util.closeQuietly(this.dataSource);
            return;
        } catch (Throwable th2) {
            Util.closeQuietly(this.dataSource);
            throw th2;
        }
        z = false;
    }

    public long getAdjustedEndTimeUs() {
        return this.adjustedEndTimeUs;
    }

    private static DataSource buildDataSource(DataSource dataSource, byte[] bArr, byte[] bArr2) {
        return (bArr == null || bArr2 == null) ? dataSource : new Aes128DataSource(dataSource, bArr, bArr2);
    }
}
