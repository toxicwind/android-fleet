package com.google.android.exoplayer.extractor.ogg;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.FlacSeekTable;
import com.google.android.exoplayer.util.FlacStreamInfo;
import com.google.android.exoplayer.util.FlacUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes.dex */
final class FlacReader extends StreamReader {
    private boolean firstAudioPacketProcessed;
    private FlacSeekTable seekTable;
    private FlacStreamInfo streamInfo;

    FlacReader() {
    }

    static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        return parsableByteArray.readUnsignedByte() == 127 && parsableByteArray.readUnsignedInt() == 1179402563;
    }

    @Override // com.google.android.exoplayer.extractor.ogg.StreamReader
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        long position = extractorInput.getPosition();
        if (!this.oggParser.readPacket(extractorInput, this.scratch)) {
            return -1;
        }
        ParsableByteArray parsableByteArray = this.scratch;
        byte[] bArr = parsableByteArray.data;
        FlacStreamInfo flacStreamInfo = this.streamInfo;
        if (flacStreamInfo == null) {
            this.streamInfo = new FlacStreamInfo(bArr, 17);
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 9, this.scratch.limit());
            bArrCopyOfRange[4] = ByteCompanionObject.MIN_VALUE;
            List listSingletonList = Collections.singletonList(bArrCopyOfRange);
            int iBitRate = this.streamInfo.bitRate();
            long jDurationUs = this.streamInfo.durationUs();
            FlacStreamInfo flacStreamInfo2 = this.streamInfo;
            this.trackOutput.format(MediaFormat.createAudioFormat(null, "audio/x-flac", iBitRate, -1, jDurationUs, flacStreamInfo2.channels, flacStreamInfo2.sampleRate, listSingletonList, null));
        } else if (bArr[0] == -1) {
            if (!this.firstAudioPacketProcessed) {
                FlacSeekTable flacSeekTable = this.seekTable;
                if (flacSeekTable != null) {
                    this.extractorOutput.seekMap(flacSeekTable.createSeekMap(position, flacStreamInfo.sampleRate));
                    this.seekTable = null;
                } else {
                    this.extractorOutput.seekMap(SeekMap.UNSEEKABLE);
                }
                this.firstAudioPacketProcessed = true;
            }
            TrackOutput trackOutput = this.trackOutput;
            ParsableByteArray parsableByteArray2 = this.scratch;
            trackOutput.sampleData(parsableByteArray2, parsableByteArray2.limit());
            this.scratch.setPosition(0);
            this.trackOutput.sampleMetadata(FlacUtil.extractSampleTimestamp(this.streamInfo, this.scratch), 1, this.scratch.limit(), 0, null);
        } else if ((bArr[0] & ByteCompanionObject.MAX_VALUE) == 3 && this.seekTable == null) {
            this.seekTable = FlacSeekTable.parseSeekTable(parsableByteArray);
        }
        this.scratch.reset();
        return 0;
    }
}
