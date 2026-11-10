package com.google.android.exoplayer.extractor.wav;

import android.util.Log;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class WavHeaderReader {
    public static WavHeader peek(ExtractorInput extractorInput) throws InterruptedException, IOException {
        Assertions.checkNotNull(extractorInput);
        ParsableByteArray parsableByteArray = new ParsableByteArray(16);
        if (ChunkHeader.peek(extractorInput, parsableByteArray).id != Util.getIntegerCodeForString("RIFF")) {
            return null;
        }
        extractorInput.peekFully(parsableByteArray.data, 0, 4);
        parsableByteArray.setPosition(0);
        int i = parsableByteArray.readInt();
        if (i != Util.getIntegerCodeForString("WAVE")) {
            Log.e("WavHeaderReader", "Unsupported RIFF format: " + i);
            return null;
        }
        ChunkHeader chunkHeaderPeek = ChunkHeader.peek(extractorInput, parsableByteArray);
        while (chunkHeaderPeek.id != Util.getIntegerCodeForString("fmt ")) {
            extractorInput.advancePeekPosition((int) chunkHeaderPeek.size);
            chunkHeaderPeek = ChunkHeader.peek(extractorInput, parsableByteArray);
        }
        Assertions.checkState(chunkHeaderPeek.size >= 16);
        extractorInput.peekFully(parsableByteArray.data, 0, 16);
        parsableByteArray.setPosition(0);
        int littleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
        int littleEndianUnsignedShort2 = parsableByteArray.readLittleEndianUnsignedShort();
        int littleEndianUnsignedIntToInt = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int littleEndianUnsignedIntToInt2 = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int littleEndianUnsignedShort3 = parsableByteArray.readLittleEndianUnsignedShort();
        int littleEndianUnsignedShort4 = parsableByteArray.readLittleEndianUnsignedShort();
        int i2 = (littleEndianUnsignedShort2 * littleEndianUnsignedShort4) / 8;
        if (littleEndianUnsignedShort3 != i2) {
            throw new ParserException("Expected block alignment: " + i2 + "; got: " + littleEndianUnsignedShort3);
        }
        int pcmEncoding = Util.getPcmEncoding(littleEndianUnsignedShort4);
        if (pcmEncoding == 0) {
            Log.e("WavHeaderReader", "Unsupported WAV bit depth: " + littleEndianUnsignedShort4);
            return null;
        }
        if (littleEndianUnsignedShort != 1 && littleEndianUnsignedShort != 65534) {
            Log.e("WavHeaderReader", "Unsupported WAV format type: " + littleEndianUnsignedShort);
            return null;
        }
        extractorInput.advancePeekPosition(((int) chunkHeaderPeek.size) - 16);
        return new WavHeader(littleEndianUnsignedShort2, littleEndianUnsignedIntToInt, littleEndianUnsignedIntToInt2, littleEndianUnsignedShort3, littleEndianUnsignedShort4, pcmEncoding);
    }

    public static void skipToData(ExtractorInput extractorInput, WavHeader wavHeader) throws InterruptedException, IOException {
        Assertions.checkNotNull(extractorInput);
        Assertions.checkNotNull(wavHeader);
        extractorInput.resetPeekPosition();
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        ChunkHeader chunkHeaderPeek = ChunkHeader.peek(extractorInput, parsableByteArray);
        while (chunkHeaderPeek.id != Util.getIntegerCodeForString("data")) {
            Log.w("WavHeaderReader", "Ignoring unknown WAV chunk: " + chunkHeaderPeek.id);
            long j = chunkHeaderPeek.size + 8;
            if (chunkHeaderPeek.id == Util.getIntegerCodeForString("RIFF")) {
                j = 12;
            }
            if (j > 2147483647L) {
                throw new ParserException("Chunk is too large (~2GB+) to skip; id: " + chunkHeaderPeek.id);
            }
            extractorInput.skipFully((int) j);
            chunkHeaderPeek = ChunkHeader.peek(extractorInput, parsableByteArray);
        }
        extractorInput.skipFully(8);
        wavHeader.setDataBounds(extractorInput.getPosition(), chunkHeaderPeek.size);
    }

    private static final class ChunkHeader {
        public final int id;
        public final long size;

        private ChunkHeader(int i, long j) {
            this.id = i;
            this.size = j;
        }

        public static ChunkHeader peek(ExtractorInput extractorInput, ParsableByteArray parsableByteArray) throws InterruptedException, IOException {
            extractorInput.peekFully(parsableByteArray.data, 0, 8);
            parsableByteArray.setPosition(0);
            return new ChunkHeader(parsableByteArray.readInt(), parsableByteArray.readLittleEndianUnsignedInt());
        }
    }
}
