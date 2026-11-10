package com.google.android.exoplayer.extractor.mp4;

import android.util.SparseArray;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.GaplessInfo;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.extractor.SyncFrameExtractor;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public final class Mp4Extractor implements Extractor, SeekMap, SyncFrameExtractor {
    private static final int BRAND_QUICKTIME = Util.getIntegerCodeForString("qt  ");
    static final byte[] VC1_START_CODE = {0, 0, 1, 13};
    private ParsableByteArray atomData;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private ExtractorOutput extractorOutput;
    private boolean isQuickTime;
    private SparseArray<MediaFormat> mediaFormatQueues;
    private boolean needParseMimeTypeFromFrame;
    private int parserState;
    private boolean pushIFrame;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleSize;
    private Mp4Track[] tracks;
    private long trickVideoTimeUs;
    private final ParsableByteArray atomHeader = new ParsableByteArray(16);
    private final Stack<Atom.ContainerAtom> containerAtoms = new Stack<>();
    private final ParsableByteArray nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
    private final ParsableByteArray nalLength = new ParsableByteArray(4);

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void release() {
    }

    public Mp4Extractor() {
        enterReadingAtomHeaderState();
        this.mediaFormatQueues = new SparseArray<>();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return Sniffer.sniffUnfragmented(extractorInput);
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void seek() {
        this.containerAtoms.clear();
        this.atomHeaderBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.parserState = 0;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        while (true) {
            int i = this.parserState;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        if (readAtomPayload(extractorInput, positionHolder)) {
                            return 1;
                        }
                    } else {
                        return readSample(extractorInput, positionHolder);
                    }
                } else if (!readAtomHeader(extractorInput)) {
                    return -1;
                }
            } else if (extractorInput.getPosition() == 0) {
                enterReadingAtomHeaderState();
            } else {
                this.parserState = 3;
            }
        }
    }

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public long getPosition(long j) {
        long j2 = LongCompanionObject.MAX_VALUE;
        int i = 0;
        while (true) {
            Mp4Track[] mp4TrackArr = this.tracks;
            if (i >= mp4TrackArr.length) {
                return j2;
            }
            TrackSampleTable trackSampleTable = mp4TrackArr[i].sampleTable;
            int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j);
            if (indexOfEarlierOrEqualSynchronizationSample == -1) {
                indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j);
            }
            this.tracks[i].sampleIndex = indexOfEarlierOrEqualSynchronizationSample;
            long j3 = trackSampleTable.offsets[indexOfEarlierOrEqualSynchronizationSample];
            if (j3 < j2) {
                j2 = j3;
            }
            i++;
        }
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 1;
        this.atomHeaderBytesRead = 0;
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) throws InterruptedException, IOException {
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.data, 0, 8, true)) {
                return false;
            }
            this.atomHeaderBytesRead = 8;
            this.atomHeader.setPosition(0);
            this.atomSize = this.atomHeader.readUnsignedInt();
            this.atomType = this.atomHeader.readInt();
        }
        if (this.atomSize == 1) {
            extractorInput.readFully(this.atomHeader.data, 8, 8);
            this.atomHeaderBytesRead += 8;
            this.atomSize = this.atomHeader.readUnsignedLongToLong();
        }
        if (shouldParseContainerAtom(this.atomType)) {
            long position = (extractorInput.getPosition() + this.atomSize) - ((long) this.atomHeaderBytesRead);
            this.containerAtoms.add(new Atom.ContainerAtom(this.atomType, position));
            if (this.atomSize == this.atomHeaderBytesRead) {
                processAtomEnded(position);
            } else {
                enterReadingAtomHeaderState();
            }
        } else if (shouldParseLeafAtom(this.atomType)) {
            Assertions.checkState(this.atomHeaderBytesRead == 8);
            Assertions.checkState(this.atomSize <= 2147483647L);
            ParsableByteArray parsableByteArray = new ParsableByteArray((int) this.atomSize);
            this.atomData = parsableByteArray;
            System.arraycopy(this.atomHeader.data, 0, parsableByteArray.data, 0, 8);
            this.parserState = 2;
        } else {
            this.atomData = null;
            this.parserState = 2;
        }
        return true;
    }

    private boolean readAtomPayload(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        boolean z;
        long j = this.atomSize - ((long) this.atomHeaderBytesRead);
        long position = extractorInput.getPosition() + j;
        ParsableByteArray parsableByteArray = this.atomData;
        if (parsableByteArray != null) {
            extractorInput.readFully(parsableByteArray.data, this.atomHeaderBytesRead, (int) j);
            if (this.atomType == Atom.TYPE_ftyp) {
                this.isQuickTime = processFtypAtom(this.atomData);
            } else if (!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(new Atom.LeafAtom(this.atomType, this.atomData));
            }
        } else if (j < 262144) {
            extractorInput.skipFully((int) j);
        } else {
            if (positionHolder != null) {
                positionHolder.position = extractorInput.getPosition() + j;
                z = true;
            }
            processAtomEnded(position);
            return (z || this.parserState == 3) ? false : true;
        }
        z = false;
        processAtomEnded(position);
        if (z) {
        }
    }

    private void processAtomEnded(long j) throws ParserException {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j) {
            Atom.ContainerAtom containerAtomPop = this.containerAtoms.pop();
            if (containerAtomPop.type == Atom.TYPE_moov) {
                processMoovAtom(containerAtomPop);
                this.containerAtoms.clear();
                this.parserState = 3;
            } else if (!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(containerAtomPop);
            }
        }
        if (this.parserState != 3) {
            enterReadingAtomHeaderState();
        }
    }

    private static boolean processFtypAtom(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        if (parsableByteArray.readInt() == BRAND_QUICKTIME) {
            return true;
        }
        parsableByteArray.skipBytes(4);
        while (parsableByteArray.bytesLeft() > 0) {
            if (parsableByteArray.readInt() == BRAND_QUICKTIME) {
                return true;
            }
        }
        return false;
    }

    private void processMoovAtom(Atom.ContainerAtom containerAtom) throws ParserException {
        Track trak;
        TrackSampleTable stbl;
        int i;
        ArrayList arrayList = new ArrayList();
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_udta);
        GaplessInfo udta = leafAtomOfType != null ? AtomParsers.parseUdta(leafAtomOfType, this.isQuickTime) : null;
        long j = LongCompanionObject.MAX_VALUE;
        for (int i2 = 0; i2 < containerAtom.containerChildren.size(); i2++) {
            Atom.ContainerAtom containerAtom2 = containerAtom.containerChildren.get(i2);
            if (containerAtom2.type == Atom.TYPE_trak && (trak = AtomParsers.parseTrak(containerAtom2, containerAtom.getLeafAtomOfType(Atom.TYPE_mvhd), -1L, this.isQuickTime)) != null && (i = (stbl = AtomParsers.parseStbl(trak, containerAtom2.getContainerAtomOfType(Atom.TYPE_mdia).getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl))).sampleCount) != 0) {
                long j2 = trak.durationUs;
                trak.mediaFormat.frameRate = Long.valueOf(j2 > 0 ? ((long) i) / (j2 / 1000000) : 0L).intValue();
                Mp4Track mp4Track = new Mp4Track(trak, stbl, this.extractorOutput.track(i2));
                int i3 = stbl.maximumSize + 30;
                if (trak.type == Track.TYPE_soun && trak.mediaFormat.mimeType.equals("audio/mpeg")) {
                    if (this.mediaFormatQueues.get(i2) == null) {
                        this.mediaFormatQueues.put(i2, trak.mediaFormat.copyWithMaxInputSize(i3));
                    }
                    this.needParseMimeTypeFromFrame = true;
                } else {
                    mp4Track.trackOutput.format(trak.mediaFormat.copyWithMaxInputSize(i3));
                }
                MediaFormat mediaFormatCopyWithMaxInputSize = trak.mediaFormat.copyWithMaxInputSize(i3);
                if (udta != null) {
                    mediaFormatCopyWithMaxInputSize = mediaFormatCopyWithMaxInputSize.copyWithGaplessInfo(udta.encoderDelay, udta.encoderPadding);
                }
                mp4Track.trackOutput.format(mediaFormatCopyWithMaxInputSize);
                arrayList.add(mp4Track);
                long j3 = stbl.offsets[0];
                if (j3 < j) {
                    j = j3;
                }
            }
        }
        this.tracks = (Mp4Track[]) arrayList.toArray(new Mp4Track[0]);
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(this);
    }

    private int readSample(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        int trackIndexOfOnlyVideoTrack;
        if (this.needParseMimeTypeFromFrame) {
            return updateMimeTypeFromFirstFrame(extractorInput, positionHolder);
        }
        if (!this.pushIFrame) {
            trackIndexOfOnlyVideoTrack = getTrackIndexOfEarliestCurrentSample();
            if (trackIndexOfOnlyVideoTrack == -1) {
                return -1;
            }
        } else {
            trackIndexOfOnlyVideoTrack = getTrackIndexOfOnlyVideoTrack();
            if (trackIndexOfOnlyVideoTrack == -1) {
                return -1;
            }
        }
        Mp4Track mp4Track = this.tracks[trackIndexOfOnlyVideoTrack];
        TrackOutput trackOutput = mp4Track.trackOutput;
        int syncSampleIndex = mp4Track.sampleIndex;
        if (this.pushIFrame) {
            syncSampleIndex = mp4Track.sampleTable.getSyncSampleIndex(syncSampleIndex);
            if (syncSampleIndex == -1) {
                return -1;
            }
            mp4Track.sampleIndex = syncSampleIndex;
            this.trickVideoTimeUs = mp4Track.sampleTable.timestampsUs[syncSampleIndex];
        }
        long j = mp4Track.sampleTable.offsets[syncSampleIndex];
        long position = (j - extractorInput.getPosition()) + ((long) this.sampleBytesWritten);
        if (position < 0 || position >= 262144) {
            if (positionHolder != null) {
                positionHolder.position = j;
            }
            return 1;
        }
        extractorInput.skipFully((int) position);
        this.sampleSize = mp4Track.sampleTable.sizes[syncSampleIndex];
        Track track = mp4Track.track;
        int i = track.nalUnitLengthFieldLength;
        if (i != -1) {
            byte[] bArr = this.nalLength.data;
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            int i2 = 4 - i;
            while (this.sampleBytesWritten < this.sampleSize) {
                int i3 = this.sampleCurrentNalBytesRemaining;
                if (i3 == 0) {
                    extractorInput.readFully(this.nalLength.data, i2, i);
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    trackOutput.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                    this.sampleSize += i2;
                } else {
                    int iSampleData = trackOutput.sampleData(extractorInput, i3, false);
                    this.sampleBytesWritten += iSampleData;
                    this.sampleCurrentNalBytesRemaining -= iSampleData;
                }
            }
        } else if (!track.mediaFormat.mimeType.equals("video/wvc1")) {
            while (true) {
                int i4 = this.sampleBytesWritten;
                int i5 = this.sampleSize;
                if (i4 >= i5) {
                    break;
                }
                int iSampleData2 = trackOutput.sampleData(extractorInput, i5 - i4, false);
                this.sampleBytesWritten += iSampleData2;
                this.sampleCurrentNalBytesRemaining -= iSampleData2;
            }
        } else {
            mp4Track.trackOutput.sampleData(new ParsableByteArray(VC1_START_CODE), 4);
            this.sampleBytesWritten += 4;
            this.sampleSize += 4;
            while (true) {
                int i6 = this.sampleBytesWritten;
                int i7 = this.sampleSize;
                if (i6 >= i7) {
                    break;
                }
                this.sampleBytesWritten += mp4Track.trackOutput.sampleData(extractorInput, i7 - i6, false);
            }
        }
        TrackSampleTable trackSampleTable = mp4Track.sampleTable;
        trackOutput.sampleMetadata(trackSampleTable.timestampsUs[syncSampleIndex], trackSampleTable.flags[syncSampleIndex], this.sampleSize, 0, null);
        mp4Track.sampleIndex++;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        return 0;
    }

    private int getTrackIndexOfEarliestCurrentSample() {
        int i = -1;
        long j = LongCompanionObject.MAX_VALUE;
        int i2 = 0;
        while (true) {
            Mp4Track[] mp4TrackArr = this.tracks;
            if (i2 >= mp4TrackArr.length) {
                return i;
            }
            Mp4Track mp4Track = mp4TrackArr[i2];
            int i3 = mp4Track.sampleIndex;
            TrackSampleTable trackSampleTable = mp4Track.sampleTable;
            if (i3 != trackSampleTable.sampleCount) {
                long j2 = trackSampleTable.offsets[i3];
                if (j2 < j) {
                    i = i2;
                    j = j2;
                }
            }
            i2++;
        }
    }

    private static boolean shouldParseLeafAtom(int i) {
        return i == Atom.TYPE_mdhd || i == Atom.TYPE_mvhd || i == Atom.TYPE_hdlr || i == Atom.TYPE_stsd || i == Atom.TYPE_stts || i == Atom.TYPE_stss || i == Atom.TYPE_ctts || i == Atom.TYPE_elst || i == Atom.TYPE_stsc || i == Atom.TYPE_stsz || i == Atom.TYPE_stz2 || i == Atom.TYPE_stco || i == Atom.TYPE_co64 || i == Atom.TYPE_tkhd || i == Atom.TYPE_ftyp || i == Atom.TYPE_udta;
    }

    private static boolean shouldParseContainerAtom(int i) {
        return i == Atom.TYPE_moov || i == Atom.TYPE_trak || i == Atom.TYPE_mdia || i == Atom.TYPE_minf || i == Atom.TYPE_stbl || i == Atom.TYPE_edts;
    }

    private static final class Mp4Track {
        public int sampleIndex;
        public final TrackSampleTable sampleTable;
        public final Track track;
        public final TrackOutput trackOutput;

        public Mp4Track(Track track, TrackSampleTable trackSampleTable, TrackOutput trackOutput) {
            this.track = track;
            this.sampleTable = trackSampleTable;
            this.trackOutput = trackOutput;
        }
    }

    private void adjustTrackIndexAfterTrick(long j) {
        int i = 0;
        while (true) {
            Mp4Track[] mp4TrackArr = this.tracks;
            if (i >= mp4TrackArr.length) {
                return;
            }
            TrackSampleTable trackSampleTable = mp4TrackArr[i].sampleTable;
            int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j);
            if (indexOfEarlierOrEqualSynchronizationSample == -1) {
                indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j);
            }
            this.tracks[i].sampleIndex = indexOfEarlierOrEqualSynchronizationSample;
            i++;
        }
    }

    private int getTrackIndexOfOnlyVideoTrack() {
        int i = -1;
        int i2 = 0;
        while (true) {
            Mp4Track[] mp4TrackArr = this.tracks;
            if (i2 >= mp4TrackArr.length) {
                return i;
            }
            Mp4Track mp4Track = mp4TrackArr[i2];
            int i3 = mp4Track.sampleIndex;
            if (mp4Track.track.type == Track.TYPE_vide && i3 != mp4Track.sampleTable.sampleCount) {
                i = i2;
            }
            i2++;
        }
    }

    private int updateMimeTypeFromFirstFrame(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        int i;
        String[] strArr = {"audio/mpeg-L1", "audio/mpeg-L2", "audio/mpeg"};
        while (this.mediaFormatQueues.size() > 0) {
            Mp4Track mp4Track = this.tracks[this.mediaFormatQueues.keyAt(0)];
            TrackSampleTable trackSampleTable = mp4Track.sampleTable;
            long j = trackSampleTable.offsets[0];
            int i2 = trackSampleTable.sizes[0];
            long position = j - extractorInput.getPosition();
            if (position < 0 || position >= 262144) {
                if (positionHolder == null) {
                    return 1;
                }
                positionHolder.position = j;
                return 1;
            }
            if (position >= 0 && i2 >= 4) {
                extractorInput.skipFully((int) position);
                ParsableByteArray parsableByteArray = new ParsableByteArray(4);
                extractorInput.readFully(parsableByteArray.data, 0, 4);
                parsableByteArray.setPosition(0);
                int i3 = parsableByteArray.readInt();
                if ((i3 & (-2097152)) == -2097152 && (i = (i3 >>> 17) & 3) != 0) {
                    this.mediaFormatQueues.valueAt(0).setMimeType(strArr[3 - i]);
                }
            }
            mp4Track.trackOutput.format(this.mediaFormatQueues.valueAt(0));
            this.mediaFormatQueues.removeAt(0);
        }
        this.needParseMimeTypeFromFrame = false;
        return 0;
    }

    @Override // com.google.android.exoplayer.extractor.SyncFrameExtractor
    public void enableSyncFrameMode(boolean z) {
        this.pushIFrame = z;
        if (z) {
            return;
        }
        adjustTrackIndexAfterTrick(this.trickVideoTimeUs);
    }
}
