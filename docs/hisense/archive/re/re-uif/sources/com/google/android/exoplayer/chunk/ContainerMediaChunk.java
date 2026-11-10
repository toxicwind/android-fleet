package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.DefaultExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public class ContainerMediaChunk extends BaseMediaChunk implements ChunkExtractorWrapper.SingleTrackOutput {
    private final int adaptiveMaxHeight;
    private final int adaptiveMaxWidth;
    private volatile int bytesLoaded;
    private DrmInitData drmInitData;
    private final ChunkExtractorWrapper extractorWrapper;
    private volatile boolean loadCanceled;
    private MediaFormat mediaFormat;
    private final long sampleOffsetUs;

    @Override // com.google.android.exoplayer.chunk.ChunkExtractorWrapper.SingleTrackOutput
    public final void seekMap(SeekMap seekMap) {
    }

    public ContainerMediaChunk(DataSource dataSource, DataSpec dataSpec, int i, Format format, long j, long j2, int i2, long j3, ChunkExtractorWrapper chunkExtractorWrapper, MediaFormat mediaFormat, int i3, int i4, DrmInitData drmInitData, boolean z, int i5) {
        super(dataSource, dataSpec, i, format, j, j2, i2, z, i5);
        this.extractorWrapper = chunkExtractorWrapper;
        this.sampleOffsetUs = j3;
        this.adaptiveMaxWidth = i3;
        this.adaptiveMaxHeight = i4;
        this.mediaFormat = getAdjustedMediaFormat(mediaFormat, j3, i3, i4);
        this.drmInitData = drmInitData;
    }

    @Override // com.google.android.exoplayer.chunk.Chunk
    public final long bytesLoaded() {
        return this.bytesLoaded;
    }

    @Override // com.google.android.exoplayer.chunk.BaseMediaChunk
    public final MediaFormat getMediaFormat() {
        return this.mediaFormat;
    }

    @Override // com.google.android.exoplayer.chunk.BaseMediaChunk
    public final DrmInitData getDrmInitData() {
        return this.drmInitData;
    }

    @Override // com.google.android.exoplayer.chunk.ChunkExtractorWrapper.SingleTrackOutput
    public final void drmInitData(DrmInitData drmInitData) {
        this.drmInitData = drmInitData;
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public final void format(MediaFormat mediaFormat) {
        this.mediaFormat = getAdjustedMediaFormat(mediaFormat, this.sampleOffsetUs, this.adaptiveMaxWidth, this.adaptiveMaxHeight);
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public final int sampleData(ExtractorInput extractorInput, int i, boolean z) throws InterruptedException, IOException {
        return getOutput().sampleData(extractorInput, i, z);
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public final void sampleData(ParsableByteArray parsableByteArray, int i) {
        getOutput().sampleData(parsableByteArray, i);
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public final void sampleMetadata(long j, int i, int i2, int i3, byte[] bArr) {
        getOutput().sampleMetadata(this.sampleOffsetUs + j, i, i2, i3, bArr);
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
        DataSpec remainderDataSpec = Util.getRemainderDataSpec(this.dataSpec, this.bytesLoaded);
        try {
            DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(this.dataSource, remainderDataSpec.absoluteStreamPosition, this.dataSource.open(remainderDataSpec));
            if (this.bytesLoaded == 0) {
                this.extractorWrapper.init(this);
            }
            int i = 0;
            while (i == 0) {
                try {
                    if (this.loadCanceled) {
                        break;
                    } else {
                        i = this.extractorWrapper.read(defaultExtractorInput);
                    }
                } catch (Throwable th) {
                    this.bytesLoaded = (int) (defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
                    throw th;
                }
            }
            this.bytesLoaded = (int) (defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
            Util.closeQuietly(this.dataSource);
        } catch (Throwable th2) {
            Util.closeQuietly(this.dataSource);
            throw th2;
        }
    }

    private static MediaFormat getAdjustedMediaFormat(MediaFormat mediaFormat, long j, int i, int i2) {
        if (mediaFormat == null) {
            return null;
        }
        if (j != 0) {
            long j2 = mediaFormat.subsampleOffsetUs;
            if (j2 != LongCompanionObject.MAX_VALUE) {
                mediaFormat = mediaFormat.copyWithSubsampleOffsetUs(j2 + j);
            }
        }
        return (i == -1 && i2 == -1) ? mediaFormat : mediaFormat.copyWithMaxVideoDimensions(i, i2);
    }
}
