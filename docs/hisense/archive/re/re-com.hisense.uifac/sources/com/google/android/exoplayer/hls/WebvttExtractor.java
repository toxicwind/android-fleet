package com.google.android.exoplayer.hls;

import android.text.TextUtils;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.extractor.ts.PtsTimestampAdjuster;
import com.google.android.exoplayer.text.webvtt.WebvttCueParser;
import com.google.android.exoplayer.text.webvtt.WebvttParserUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
final class WebvttExtractor implements Extractor {
    private static final Pattern LOCAL_TIMESTAMP = Pattern.compile("LOCAL:([^,]+)");
    private static final Pattern MEDIA_TIMESTAMP = Pattern.compile("MPEGTS:(\\d+)");
    private ExtractorOutput output;
    private final PtsTimestampAdjuster ptsTimestampAdjuster;
    private int sampleSize;
    private final ParsableByteArray sampleDataWrapper = new ParsableByteArray();
    private byte[] sampleData = new byte[1024];

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void release() {
    }

    public WebvttExtractor(PtsTimestampAdjuster ptsTimestampAdjuster) {
        this.ptsTimestampAdjuster = ptsTimestampAdjuster;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        throw new IllegalStateException();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.output = extractorOutput;
        extractorOutput.seekMap(SeekMap.UNSEEKABLE);
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void seek() {
        throw new IllegalStateException();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        int length = (int) extractorInput.getLength();
        int i = this.sampleSize;
        byte[] bArr = this.sampleData;
        if (i == bArr.length) {
            this.sampleData = Arrays.copyOf(bArr, ((length != -1 ? length : bArr.length) * 3) / 2);
        }
        byte[] bArr2 = this.sampleData;
        int i2 = this.sampleSize;
        int i3 = extractorInput.read(bArr2, i2, bArr2.length - i2);
        if (i3 != -1) {
            int i4 = this.sampleSize + i3;
            this.sampleSize = i4;
            if (length == -1 || i4 != length) {
                return 0;
            }
        }
        processSample();
        return -1;
    }

    private void processSample() throws ParserException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.sampleData);
        WebvttParserUtil.validateWebvttHeaderLine(parsableByteArray);
        long jPtsToUs = 0;
        long timestampUs = 0;
        while (true) {
            String line = parsableByteArray.readLine();
            if (!TextUtils.isEmpty(line)) {
                if (line.startsWith("X-TIMESTAMP-MAP")) {
                    Matcher matcher = LOCAL_TIMESTAMP.matcher(line);
                    if (!matcher.find()) {
                        throw new ParserException("X-TIMESTAMP-MAP doesn't contain local timestamp: " + line);
                    }
                    Matcher matcher2 = MEDIA_TIMESTAMP.matcher(line);
                    if (!matcher2.find()) {
                        throw new ParserException("X-TIMESTAMP-MAP doesn't contain media timestamp: " + line);
                    }
                    timestampUs = WebvttParserUtil.parseTimestampUs(matcher.group(1));
                    jPtsToUs = PtsTimestampAdjuster.ptsToUs(Long.parseLong(matcher2.group(1)));
                }
            } else {
                Matcher matcherFindNextCueHeader = WebvttCueParser.findNextCueHeader(parsableByteArray);
                if (matcherFindNextCueHeader == null) {
                    buildTrackOutput(0L);
                    return;
                }
                long timestampUs2 = WebvttParserUtil.parseTimestampUs(matcherFindNextCueHeader.group(1));
                long jAdjustTimestamp = this.ptsTimestampAdjuster.adjustTimestamp(PtsTimestampAdjuster.usToPts((jPtsToUs + timestampUs2) - timestampUs));
                TrackOutput trackOutputBuildTrackOutput = buildTrackOutput(jAdjustTimestamp - timestampUs2);
                this.sampleDataWrapper.reset(this.sampleData, this.sampleSize);
                trackOutputBuildTrackOutput.sampleData(this.sampleDataWrapper, this.sampleSize);
                trackOutputBuildTrackOutput.sampleMetadata(jAdjustTimestamp, 1, this.sampleSize, 0, null);
                return;
            }
        }
    }

    private TrackOutput buildTrackOutput(long j) {
        TrackOutput trackOutputTrack = this.output.track(0);
        trackOutputTrack.format(MediaFormat.createTextFormat("id", "text/vtt", -1, -1L, "en", j));
        this.output.endTracks();
        return trackOutputTrack;
    }
}
