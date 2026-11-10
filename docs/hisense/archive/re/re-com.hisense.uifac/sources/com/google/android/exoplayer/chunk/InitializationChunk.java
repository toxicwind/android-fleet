package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.DefaultExtractorInput;
import com.google.android.exoplayer.extractor.DefaultTrackOutput;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import kotlin.jvm.internal.IntCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public class InitializationChunk extends Chunk implements ChunkExtractorWrapper.SingleTrackOutput {
    private volatile int bytesLoaded;
    private DrmInitData drmInitData;
    private final ChunkExtractorWrapper extractorWrapper;
    private volatile boolean isDashInit;
    private volatile boolean loadCanceled;
    private MediaFormat mediaFormat;
    private DefaultTrackOutput output;
    private SeekMap seekMap;

    @Override // com.google.android.exoplayer.chunk.Chunk
    public long bytesLoaded() {
        return this.bytesLoaded;
    }

    public boolean hasFormat() {
        return this.mediaFormat != null;
    }

    public MediaFormat getFormat() {
        return this.mediaFormat;
    }

    public boolean hasDrmInitData() {
        return this.drmInitData != null;
    }

    public DrmInitData getDrmInitData() {
        return this.drmInitData;
    }

    public boolean hasSeekMap() {
        return this.seekMap != null;
    }

    public SeekMap getSeekMap() {
        return this.seekMap;
    }

    @Override // com.google.android.exoplayer.chunk.ChunkExtractorWrapper.SingleTrackOutput
    public void seekMap(SeekMap seekMap) {
        this.seekMap = seekMap;
    }

    @Override // com.google.android.exoplayer.chunk.ChunkExtractorWrapper.SingleTrackOutput
    public void drmInitData(DrmInitData drmInitData) {
        this.drmInitData = drmInitData;
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public void format(MediaFormat mediaFormat) {
        this.mediaFormat = mediaFormat;
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public int sampleData(ExtractorInput extractorInput, int i, boolean z) throws InterruptedException, IOException {
        throw new IllegalStateException("Unexpected sample data in initialization chunk");
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public void sampleData(ParsableByteArray parsableByteArray, int i) {
        throw new IllegalStateException("Unexpected sample data in initialization chunk");
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public void sampleMetadata(long j, int i, int i2, int i3, byte[] bArr) {
        throw new IllegalStateException("Unexpected sample data in initialization chunk");
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public boolean isLoadCanceled() {
        return this.loadCanceled;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public void load() throws InterruptedException, IOException {
        int iSampleData = 0;
        if (this.extractorWrapper == null) {
            Format format = this.format;
            this.mediaFormat = MediaFormat.createTextFormat(format.id, format.mimeType, format.bitrate, 0L, format.language);
            try {
                this.dataSource.open(Util.getRemainderDataSpec(this.dataSpec, this.bytesLoaded));
                int i = 0;
                while (iSampleData != -1) {
                    i += iSampleData;
                    this.bytesLoaded = i;
                    iSampleData = this.output.sampleData(this.dataSource, IntCompanionObject.MAX_VALUE, true);
                }
                this.output.sampleMetadata(0L, 1, i, 0, null);
                return;
            } finally {
                this.dataSource.close();
            }
        }
        if (this.isDashInit) {
            this.extractorWrapper.setDashInitStage(true);
        }
        DataSpec remainderDataSpecEnhance = Util.getRemainderDataSpecEnhance(this.dataSpec, this.bytesLoaded);
        MtkDebugLogUtil.log("InitializationChunk", "start loading loadDataSpec " + remainderDataSpecEnhance + "  bytesLoaded " + this.bytesLoaded, 4);
        try {
            DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(this.dataSource, remainderDataSpecEnhance.absoluteStreamPosition, this.dataSource.open(remainderDataSpecEnhance));
            if (this.bytesLoaded == 0) {
                this.extractorWrapper.init(this);
            }
            int i2 = 0;
            while (i2 == 0) {
                try {
                    if (this.loadCanceled) {
                        break;
                    } else {
                        i2 = this.extractorWrapper.read(defaultExtractorInput);
                    }
                } catch (Throwable th) {
                    this.bytesLoaded = (int) (defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
                    throw th;
                }
            }
            this.bytesLoaded = (int) (defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
            if (this.isDashInit) {
                this.extractorWrapper.setDashInitStage(false);
            }
            MtkDebugLogUtil.log("InitializationChunk", "end loading loadDataSpec " + remainderDataSpecEnhance + "  bytesLoaded " + this.bytesLoaded, 4);
            Util.closeQuietly(this.dataSource);
        } catch (Throwable th2) {
            if (this.isDashInit) {
                this.extractorWrapper.setDashInitStage(false);
            }
            MtkDebugLogUtil.log("InitializationChunk", "end loading loadDataSpec " + remainderDataSpecEnhance + "  bytesLoaded " + this.bytesLoaded, 4);
            Util.closeQuietly(this.dataSource);
            throw th2;
        }
    }

    public InitializationChunk(DataSource dataSource, DataSpec dataSpec, int i, Format format, ChunkExtractorWrapper chunkExtractorWrapper, int i2, boolean z) {
        super(dataSource, dataSpec, 2, i, format, i2);
        this.extractorWrapper = chunkExtractorWrapper;
        this.isDashInit = z;
    }

    public void initTrackOutput(DefaultTrackOutput defaultTrackOutput) {
        this.output = defaultTrackOutput;
    }
}
