package com.google.android.exoplayer.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.ChunkIndex;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.extractor.SyncFrameExtractor;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.UUID;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public class FragmentedMp4Extractor implements Extractor, SyncFrameExtractor {
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private final Stack<Atom.ContainerAtom> containerAtoms;
    private TrackBundle currentTrackBundle;
    private final ParsableByteArray encryptionSignalByte;
    private long endOfMdatPosition;
    private final byte[] extendedTypeScratch;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private boolean haveOutputSeekMap;
    private boolean isDashInitStage;
    private boolean isMoofDetected;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private boolean pushIFrame;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleSize;
    private ChunkIndex segIndex;
    private final Track sideloadedTrack;
    private final SparseArray<TrackBundle> trackBundles;
    private static final int SAMPLE_GROUP_TYPE_seig = Util.getIntegerCodeForString("seig");
    private static final byte[] PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE = {-94, 57, 79, 82, 90, -101, 79, 20, -94, 68, 108, 66, 124, 100, -115, -12};

    protected void parseEmsg(ParsableByteArray parsableByteArray, long j) throws ParserException {
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public final void release() {
    }

    public FragmentedMp4Extractor() {
        this(0);
    }

    public FragmentedMp4Extractor(int i) {
        this(i, (Track) null);
    }

    public FragmentedMp4Extractor(int i, Track track) {
        this.sideloadedTrack = track;
        this.flags = i | (track != null ? 4 : 0);
        this.atomHeader = new ParsableByteArray(16);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.encryptionSignalByte = new ParsableByteArray(1);
        this.extendedTypeScratch = new byte[16];
        this.containerAtoms = new Stack<>();
        this.trackBundles = new SparseArray<>();
        enterReadingAtomHeaderState();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public final boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return Sniffer.sniffFragmented(extractorInput);
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public final void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        if (this.sideloadedTrack != null) {
            TrackBundle trackBundle = new TrackBundle(extractorOutput.track(0));
            trackBundle.init(this.sideloadedTrack, new DefaultSampleValues(0, 0, 0, 0));
            this.trackBundles.put(0, trackBundle);
            this.extractorOutput.endTracks();
        }
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public final void seek() {
        int size = this.trackBundles.size();
        for (int i = 0; i < size; i++) {
            this.trackBundles.valueAt(i).reset();
        }
        this.containerAtoms.clear();
        enterReadingAtomHeaderState();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        while (true) {
            if (this.isDashInitStage && this.isMoofDetected) {
                return -1;
            }
            int i = this.parserState;
            if (i != 0) {
                if (i == 1) {
                    readAtomPayload(extractorInput);
                } else if (i == 2) {
                    readEncryptionData(extractorInput);
                } else if (readSample(extractorInput)) {
                    return 0;
                }
            } else if (!readAtomHeader(extractorInput)) {
                return -1;
            }
        }
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
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
        if (this.atomSize < this.atomHeaderBytesRead) {
            throw new ParserException("Atom size less than header length (unsupported).");
        }
        long position = extractorInput.getPosition() - ((long) this.atomHeaderBytesRead);
        if (this.atomType == Atom.TYPE_moof) {
            int size = this.trackBundles.size();
            for (int i = 0; i < size; i++) {
                TrackFragment trackFragment = this.trackBundles.valueAt(i).fragment;
                trackFragment.auxiliaryDataPosition = position;
                trackFragment.dataPosition = position;
                trackFragment.hasDataOffset = false;
            }
        }
        int i2 = this.atomType;
        if (i2 == Atom.TYPE_mdat) {
            this.currentTrackBundle = null;
            this.endOfMdatPosition = position + this.atomSize;
            if (!this.haveOutputSeekMap) {
                this.extractorOutput.seekMap(SeekMap.UNSEEKABLE);
                this.haveOutputSeekMap = true;
            }
            this.parserState = 2;
            return true;
        }
        if (shouldParseContainerAtom(i2)) {
            long position2 = (extractorInput.getPosition() + this.atomSize) - 8;
            this.containerAtoms.add(new Atom.ContainerAtom(this.atomType, position2));
            if (this.atomSize == this.atomHeaderBytesRead) {
                processAtomEnded(position2);
            } else {
                enterReadingAtomHeaderState();
            }
        } else if (shouldParseLeafAtom(this.atomType)) {
            if (this.atomHeaderBytesRead != 8) {
                throw new ParserException("Leaf atom defines extended atom size (unsupported).");
            }
            long j = this.atomSize;
            if (j > 2147483647L) {
                throw new ParserException("Leaf atom with length > 2147483647 (unsupported).");
            }
            ParsableByteArray parsableByteArray = new ParsableByteArray((int) j);
            this.atomData = parsableByteArray;
            System.arraycopy(this.atomHeader.data, 0, parsableByteArray.data, 0, 8);
            this.parserState = 1;
        } else {
            if (this.atomSize > 2147483647L) {
                throw new ParserException("Skipping atom with length > 2147483647 (unsupported).");
            }
            this.atomData = null;
            this.parserState = 1;
        }
        return true;
    }

    private void readAtomPayload(ExtractorInput extractorInput) throws InterruptedException, IOException {
        int i = ((int) this.atomSize) - this.atomHeaderBytesRead;
        ParsableByteArray parsableByteArray = this.atomData;
        if (parsableByteArray != null) {
            extractorInput.readFully(parsableByteArray.data, 8, i);
            onLeafAtomRead(new Atom.LeafAtom(this.atomType, this.atomData), extractorInput.getPosition());
        } else {
            try {
                extractorInput.skipFully(i);
            } catch (EOFException unused) {
                Log.e("FragmentedMp4Extractor", "catch EOFException, continue play from next atom");
            } catch (IOException unused2) {
                Log.e("FragmentedMp4Extractor", "catch IOException, continue play from next atom");
            }
        }
        processAtomEnded(extractorInput.getPosition());
    }

    private void processAtomEnded(long j) throws ParserException {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j) {
            onContainerAtomRead(this.containerAtoms.pop());
        }
        enterReadingAtomHeaderState();
    }

    private void onLeafAtomRead(Atom.LeafAtom leafAtom, long j) throws ParserException {
        if (!this.containerAtoms.isEmpty()) {
            this.containerAtoms.peek().add(leafAtom);
            return;
        }
        int i = leafAtom.type;
        if (i == Atom.TYPE_sidx) {
            ChunkIndex sidx = parseSidx(leafAtom.data, j);
            if (sidx == null) {
                return;
            }
            ChunkIndex chunkIndex = this.segIndex;
            if (chunkIndex == null) {
                this.segIndex = sidx;
                this.extractorOutput.seekMap(sidx);
                this.haveOutputSeekMap = true;
                return;
            }
            int[] iArr = chunkIndex.sizes;
            int[] iArr2 = new int[iArr.length + sidx.sizes.length];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            int[] iArr3 = sidx.sizes;
            System.arraycopy(iArr3, 0, iArr2, chunkIndex.sizes.length, iArr3.length);
            long[] jArr = chunkIndex.offsets;
            long[] jArr2 = new long[jArr.length + sidx.offsets.length];
            System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
            long[] jArr3 = sidx.offsets;
            System.arraycopy(jArr3, 0, jArr2, chunkIndex.offsets.length, jArr3.length);
            long[] jArr4 = chunkIndex.durationsUs;
            long[] jArr5 = new long[jArr4.length + sidx.durationsUs.length];
            System.arraycopy(jArr4, 0, jArr5, 0, jArr4.length);
            long[] jArr6 = sidx.durationsUs;
            System.arraycopy(jArr6, 0, jArr5, chunkIndex.durationsUs.length, jArr6.length);
            long[] jArr7 = chunkIndex.timesUs;
            long[] jArr8 = new long[jArr7.length + sidx.timesUs.length];
            System.arraycopy(jArr7, 0, jArr8, 0, jArr7.length);
            long[] jArr9 = sidx.timesUs;
            System.arraycopy(jArr9, 0, jArr8, chunkIndex.timesUs.length, jArr9.length);
            ChunkIndex chunkIndex2 = new ChunkIndex(iArr2, jArr2, jArr5, jArr8);
            this.segIndex = chunkIndex2;
            this.extractorOutput.seekMap(chunkIndex2);
            this.haveOutputSeekMap = true;
            return;
        }
        if (i == Atom.TYPE_emsg) {
            parseEmsg(leafAtom.data, j);
        }
    }

    private void onContainerAtomRead(Atom.ContainerAtom containerAtom) throws ParserException {
        int i = containerAtom.type;
        if (i == Atom.TYPE_moov) {
            onMoovContainerAtomRead(containerAtom);
            return;
        }
        if (i == Atom.TYPE_moof) {
            if (this.isDashInitStage) {
                this.isMoofDetected = true;
                return;
            } else {
                onMoofContainerAtomRead(containerAtom);
                return;
            }
        }
        if (this.containerAtoms.isEmpty()) {
            return;
        }
        this.containerAtoms.peek().add(containerAtom);
    }

    private void onMoovContainerAtomRead(Atom.ContainerAtom containerAtom) {
        Track trak;
        Assertions.checkState(this.sideloadedTrack == null, "Unexpected moov box.");
        DrmInitData.Mapped drmInitDataFromAtoms = getDrmInitDataFromAtoms(containerAtom.leafChildren);
        if (drmInitDataFromAtoms != null) {
            this.extractorOutput.drmInitData(drmInitDataFromAtoms);
        }
        Atom.ContainerAtom containerAtomOfType = containerAtom.getContainerAtomOfType(Atom.TYPE_mvex);
        SparseArray sparseArray = new SparseArray();
        long mehd = -1;
        int size = containerAtomOfType.leafChildren.size();
        for (int i = 0; i < size; i++) {
            Atom.LeafAtom leafAtom = containerAtomOfType.leafChildren.get(i);
            int i2 = leafAtom.type;
            if (i2 == Atom.TYPE_trex) {
                Pair<Integer, DefaultSampleValues> trex = parseTrex(leafAtom.data);
                sparseArray.put(((Integer) trex.first).intValue(), (DefaultSampleValues) trex.second);
            } else if (i2 == Atom.TYPE_mehd) {
                mehd = parseMehd(leafAtom.data);
            }
        }
        SparseArray sparseArray2 = new SparseArray();
        int size2 = containerAtom.containerChildren.size();
        for (int i3 = 0; i3 < size2; i3++) {
            Atom.ContainerAtom containerAtom2 = containerAtom.containerChildren.get(i3);
            if (containerAtom2.type == Atom.TYPE_trak && (trak = AtomParsers.parseTrak(containerAtom2, containerAtom.getLeafAtomOfType(Atom.TYPE_mvhd), mehd, false)) != null) {
                sparseArray2.put(trak.id, trak);
            }
        }
        int size3 = sparseArray2.size();
        if (this.trackBundles.size() == 0) {
            for (int i4 = 0; i4 < size3; i4++) {
                this.trackBundles.put(((Track) sparseArray2.valueAt(i4)).id, new TrackBundle(this.extractorOutput.track(i4)));
            }
            this.extractorOutput.endTracks();
        } else {
            Assertions.checkState(this.trackBundles.size() == size3);
        }
        for (int i5 = 0; i5 < size3; i5++) {
            Track track = (Track) sparseArray2.valueAt(i5);
            this.trackBundles.get(track.id).init(track, (DefaultSampleValues) sparseArray.get(track.id));
        }
    }

    private void onMoofContainerAtomRead(Atom.ContainerAtom containerAtom) throws ParserException {
        parseMoof(containerAtom, this.trackBundles, this.flags, this.extendedTypeScratch);
        DrmInitData.Mapped drmInitDataFromAtoms = getDrmInitDataFromAtoms(containerAtom.leafChildren);
        if (drmInitDataFromAtoms != null) {
            this.extractorOutput.drmInitData(drmInitDataFromAtoms);
        }
    }

    private static Pair<Integer, DefaultSampleValues> parseTrex(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(12);
        return Pair.create(Integer.valueOf(parsableByteArray.readInt()), new DefaultSampleValues(parsableByteArray.readUnsignedIntToInt() - 1, parsableByteArray.readUnsignedIntToInt(), parsableByteArray.readUnsignedIntToInt(), parsableByteArray.readInt()));
    }

    private static long parseMehd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return Atom.parseFullAtomVersion(parsableByteArray.readInt()) == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
    }

    private static void parseMoof(Atom.ContainerAtom containerAtom, SparseArray<TrackBundle> sparseArray, int i, byte[] bArr) throws ParserException {
        int size = containerAtom.containerChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            Atom.ContainerAtom containerAtom2 = containerAtom.containerChildren.get(i2);
            if (containerAtom2.type == Atom.TYPE_traf) {
                parseTraf(containerAtom2, sparseArray, i, bArr);
            }
        }
    }

    private static void parseTraf(Atom.ContainerAtom containerAtom, SparseArray<TrackBundle> sparseArray, int i, byte[] bArr) throws ParserException {
        if (containerAtom.getChildAtomOfTypeCount(Atom.TYPE_trun) != 1) {
            throw new ParserException("Trun count in traf != 1 (unsupported).");
        }
        TrackBundle tfhd = parseTfhd(containerAtom.getLeafAtomOfType(Atom.TYPE_tfhd).data, sparseArray, i);
        if (tfhd == null) {
            return;
        }
        TrackFragment trackFragment = tfhd.fragment;
        long tfdt = trackFragment.nextFragmentDecodeTime;
        tfhd.reset();
        if (containerAtom.getLeafAtomOfType(Atom.TYPE_tfdt) != null && (i & 2) == 0) {
            tfdt = parseTfdt(containerAtom.getLeafAtomOfType(Atom.TYPE_tfdt).data);
        }
        parseTrun(tfhd, tfdt, i, containerAtom.getLeafAtomOfType(Atom.TYPE_trun).data);
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_saiz);
        if (leafAtomOfType != null) {
            parseSaiz(tfhd.track.sampleDescriptionEncryptionBoxes[trackFragment.header.sampleDescriptionIndex], leafAtomOfType.data, trackFragment);
        }
        Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_saio);
        if (leafAtomOfType2 != null) {
            parseSaio(leafAtomOfType2.data, trackFragment);
        }
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_senc);
        if (leafAtomOfType3 != null) {
            parseSenc(leafAtomOfType3.data, trackFragment);
        }
        Atom.LeafAtom leafAtomOfType4 = containerAtom.getLeafAtomOfType(Atom.TYPE_sbgp);
        Atom.LeafAtom leafAtomOfType5 = containerAtom.getLeafAtomOfType(Atom.TYPE_sgpd);
        if (leafAtomOfType4 != null && leafAtomOfType5 != null) {
            parseSgpd(leafAtomOfType4.data, leafAtomOfType5.data, trackFragment);
        }
        int size = containerAtom.leafChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            Atom.LeafAtom leafAtom = containerAtom.leafChildren.get(i2);
            if (leafAtom.type == Atom.TYPE_uuid) {
                parseUuid(leafAtom.data, trackFragment, bArr);
            }
        }
    }

    private static void parseSaiz(TrackEncryptionBox trackEncryptionBox, ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        int i;
        int i2 = trackEncryptionBox.initializationVectorSize;
        parsableByteArray.setPosition(8);
        if ((Atom.parseFullAtomFlags(parsableByteArray.readInt()) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt != trackFragment.length) {
            throw new ParserException("Length mismatch: " + unsignedIntToInt + ", " + trackFragment.length);
        }
        if (unsignedByte == 0) {
            boolean[] zArr = trackFragment.sampleHasSubsampleEncryptionTable;
            i = 0;
            for (int i3 = 0; i3 < unsignedIntToInt; i3++) {
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                i += unsignedByte2;
                zArr[i3] = unsignedByte2 > i2;
            }
        } else {
            i = (unsignedByte * unsignedIntToInt) + 0;
            Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, unsignedIntToInt, unsignedByte > i2);
        }
        trackFragment.initEncryptionData(i);
    }

    private static void parseSaio(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        parsableByteArray.setPosition(8);
        int i = parsableByteArray.readInt();
        if ((Atom.parseFullAtomFlags(i) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt != 1) {
            throw new ParserException("Unexpected saio entry count: " + unsignedIntToInt);
        }
        trackFragment.auxiliaryDataPosition += Atom.parseFullAtomVersion(i) == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
    }

    private static TrackBundle parseTfhd(ParsableByteArray parsableByteArray, SparseArray<TrackBundle> sparseArray, int i) {
        parsableByteArray.setPosition(8);
        int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        int i2 = parsableByteArray.readInt();
        if ((i & 4) != 0) {
            i2 = 0;
        }
        TrackBundle trackBundle = sparseArray.get(i2);
        if (trackBundle == null) {
            return null;
        }
        if ((fullAtomFlags & 1) != 0) {
            long unsignedLongToLong = parsableByteArray.readUnsignedLongToLong();
            TrackFragment trackFragment = trackBundle.fragment;
            trackFragment.dataPosition = unsignedLongToLong;
            trackFragment.hasDataOffset = true;
            trackFragment.auxiliaryDataPosition = unsignedLongToLong;
        }
        DefaultSampleValues defaultSampleValues = trackBundle.defaultSampleValues;
        trackBundle.fragment.header = new DefaultSampleValues((fullAtomFlags & 2) != 0 ? parsableByteArray.readUnsignedIntToInt() - 1 : defaultSampleValues.sampleDescriptionIndex, (fullAtomFlags & 8) != 0 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.duration, (fullAtomFlags & 16) != 0 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.size, (fullAtomFlags & 32) != 0 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.flags);
        return trackBundle;
    }

    private static long parseTfdt(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return Atom.parseFullAtomVersion(parsableByteArray.readInt()) == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
    }

    private static void parseTrun(TrackBundle trackBundle, long j, int i, ParsableByteArray parsableByteArray) {
        int unsignedIntToInt;
        int i2;
        boolean z;
        parsableByteArray.setPosition(8);
        int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        Track track = trackBundle.track;
        TrackFragment trackFragment = trackBundle.fragment;
        DefaultSampleValues defaultSampleValues = trackFragment.header;
        int unsignedIntToInt2 = parsableByteArray.readUnsignedIntToInt();
        if ((fullAtomFlags & 1) != 0) {
            trackFragment.dataPosition += (long) parsableByteArray.readInt();
            trackFragment.hasDataOffset = true;
        }
        boolean z2 = (fullAtomFlags & 4) != 0;
        int unsignedIntToInt3 = defaultSampleValues.flags;
        if (z2) {
            unsignedIntToInt3 = parsableByteArray.readUnsignedIntToInt();
        }
        boolean z3 = (fullAtomFlags & 256) != 0;
        boolean z4 = (fullAtomFlags & 512) != 0;
        boolean z5 = (fullAtomFlags & 1024) != 0;
        boolean z6 = (fullAtomFlags & 2048) != 0;
        long[] jArr = track.editListDurations;
        long jScaleLargeTimestamp = (jArr != null && jArr.length == 1 && jArr[0] == 0) ? Util.scaleLargeTimestamp(track.editListMediaTimes[0], 1000L, track.timescale) : 0L;
        trackFragment.initTables(unsignedIntToInt2);
        int[] iArr = trackFragment.sampleSizeTable;
        int[] iArr2 = trackFragment.sampleCompositionTimeOffsetTable;
        long[] jArr2 = trackFragment.sampleDecodingTimeTable;
        boolean[] zArr = trackFragment.sampleIsSyncFrameTable;
        long j2 = jScaleLargeTimestamp;
        long j3 = track.timescale;
        int i3 = unsignedIntToInt3;
        boolean z7 = track.type == Track.TYPE_vide && (i & 1) != 0;
        long j4 = j;
        int i4 = 0;
        while (i4 < unsignedIntToInt2) {
            if (z3) {
                unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            } else {
                unsignedIntToInt = defaultSampleValues.duration;
            }
            int unsignedIntToInt4 = z4 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.size;
            if (i4 == 0 && z2) {
                i2 = i3;
            } else {
                i2 = z5 ? parsableByteArray.readInt() : defaultSampleValues.flags;
            }
            if (z6) {
                iArr2[i4] = (int) (((long) (parsableByteArray.readInt() * 1000)) / j3);
                z = false;
            } else {
                z = false;
                iArr2[i4] = 0;
            }
            jArr2[i4] = Util.scaleLargeTimestamp(j4, 1000L, j3) - j2;
            iArr[i4] = unsignedIntToInt4;
            zArr[i4] = (((i2 >> 16) & 1) != 0 || (z7 && i4 != 0)) ? z : true;
            if (track.type == Track.TYPE_soun) {
                zArr[i4] = true;
            }
            j4 += (long) unsignedIntToInt;
            i4++;
            z3 = z3;
            z4 = z4;
            z2 = z2;
            z6 = z6;
            defaultSampleValues = defaultSampleValues;
            unsignedIntToInt2 = unsignedIntToInt2;
        }
        int i5 = unsignedIntToInt2;
        long j5 = j4;
        long j6 = j3 > 0 ? (j5 - j) / j3 : 0L;
        track.mediaFormat.frameRate = Long.valueOf(j6 > 0 ? ((long) i5) / j6 : 0L).intValue();
        trackFragment.nextFragmentDecodeTime = j5;
    }

    private static void parseUuid(ParsableByteArray parsableByteArray, TrackFragment trackFragment, byte[] bArr) throws ParserException {
        parsableByteArray.setPosition(8);
        parsableByteArray.readBytes(bArr, 0, 16);
        if (Arrays.equals(bArr, PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE)) {
            parseSenc(parsableByteArray, 16, trackFragment);
        }
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        parseSenc(parsableByteArray, 0, trackFragment);
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, int i, TrackFragment trackFragment) throws ParserException {
        parsableByteArray.setPosition(i + 8);
        int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        if ((fullAtomFlags & 1) != 0) {
            throw new ParserException("Overriding TrackEncryptionBox parameters is unsupported.");
        }
        boolean z = (fullAtomFlags & 2) != 0;
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt != trackFragment.length) {
            throw new ParserException("Length mismatch: " + unsignedIntToInt + ", " + trackFragment.length);
        }
        Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, unsignedIntToInt, z);
        trackFragment.initEncryptionData(parsableByteArray.bytesLeft());
        trackFragment.fillEncryptionData(parsableByteArray);
    }

    private static void parseSgpd(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, TrackFragment trackFragment) throws ParserException {
        parsableByteArray.setPosition(8);
        int i = parsableByteArray.readInt();
        if (parsableByteArray.readInt() != SAMPLE_GROUP_TYPE_seig) {
            return;
        }
        if (Atom.parseFullAtomVersion(i) == 1) {
            parsableByteArray.skipBytes(4);
        }
        if (parsableByteArray.readInt() != 1) {
            throw new ParserException("Entry count in sbgp != 1 (unsupported).");
        }
        parsableByteArray2.setPosition(8);
        int i2 = parsableByteArray2.readInt();
        if (parsableByteArray2.readInt() != SAMPLE_GROUP_TYPE_seig) {
            return;
        }
        int fullAtomVersion = Atom.parseFullAtomVersion(i2);
        if (fullAtomVersion == 1) {
            if (parsableByteArray2.readUnsignedInt() == 0) {
                throw new ParserException("Variable length decription in sgpd found (unsupported)");
            }
        } else if (fullAtomVersion >= 2) {
            parsableByteArray2.skipBytes(4);
        }
        if (parsableByteArray2.readUnsignedInt() != 1) {
            throw new ParserException("Entry count in sgpd != 1 (unsupported).");
        }
        parsableByteArray2.skipBytes(2);
        boolean z = parsableByteArray2.readUnsignedByte() == 1;
        if (z) {
            int unsignedByte = parsableByteArray2.readUnsignedByte();
            byte[] bArr = new byte[16];
            parsableByteArray2.readBytes(bArr, 0, 16);
            trackFragment.definesEncryptionData = true;
            trackFragment.trackEncryptionBox = new TrackEncryptionBox(z, unsignedByte, bArr);
        }
    }

    private static ChunkIndex parseSidx(ParsableByteArray parsableByteArray, long j) throws ParserException {
        long unsignedLongToLong;
        long unsignedLongToLong2;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(4);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        if (fullAtomVersion == 0) {
            unsignedLongToLong = parsableByteArray.readUnsignedInt();
            unsignedLongToLong2 = parsableByteArray.readUnsignedInt();
        } else {
            unsignedLongToLong = parsableByteArray.readUnsignedLongToLong();
            unsignedLongToLong2 = parsableByteArray.readUnsignedLongToLong();
        }
        long j2 = j + unsignedLongToLong2;
        long j3 = unsignedLongToLong;
        parsableByteArray.skipBytes(2);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int[] iArr = new int[unsignedShort];
        long[] jArr = new long[unsignedShort];
        long[] jArr2 = new long[unsignedShort];
        long[] jArr3 = new long[unsignedShort];
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(j3, 1000000L, unsignedInt);
        long j4 = j2;
        int i = 0;
        long j5 = j3;
        while (i < unsignedShort) {
            int i2 = parsableByteArray.readInt();
            if ((Integer.MIN_VALUE & i2) != 0) {
                return null;
            }
            long unsignedInt2 = parsableByteArray.readUnsignedInt();
            iArr[i] = i2 & IntCompanionObject.MAX_VALUE;
            jArr[i] = j4;
            jArr3[i] = jScaleLargeTimestamp;
            long j6 = j5 + unsignedInt2;
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(j6, 1000000L, unsignedInt);
            jArr2[i] = jScaleLargeTimestamp - jArr3[i];
            parsableByteArray.skipBytes(4);
            j4 += (long) iArr[i];
            i++;
            j5 = j6;
        }
        return new ChunkIndex(iArr, jArr, jArr2, jArr3);
    }

    private void readEncryptionData(ExtractorInput extractorInput) throws InterruptedException, IOException {
        int size = this.trackBundles.size();
        TrackBundle trackBundleValueAt = null;
        long j = LongCompanionObject.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            TrackFragment trackFragment = this.trackBundles.valueAt(i).fragment;
            if (trackFragment.sampleEncryptionDataNeedsFill) {
                long j2 = trackFragment.auxiliaryDataPosition;
                if (j2 < j) {
                    trackBundleValueAt = this.trackBundles.valueAt(i);
                    j = j2;
                }
            }
        }
        if (trackBundleValueAt == null) {
            this.parserState = 3;
            return;
        }
        int position = (int) (j - extractorInput.getPosition());
        if (position < 0) {
            throw new ParserException("Offset to encryption data was negative.");
        }
        extractorInput.skipFully(position);
        trackBundleValueAt.fragment.fillEncryptionData(extractorInput);
    }

    private boolean readSample(ExtractorInput extractorInput) throws InterruptedException, IOException {
        byte[] bArr;
        byte[] bArr2;
        if (this.parserState == 3) {
            if (this.currentTrackBundle == null) {
                TrackBundle nextFragmentRun = getNextFragmentRun(this.trackBundles);
                this.currentTrackBundle = nextFragmentRun;
                if (nextFragmentRun == null) {
                    int position = (int) (this.endOfMdatPosition - extractorInput.getPosition());
                    if (position < 0) {
                        throw new ParserException("Offset to end of mdat was negative.");
                    }
                    extractorInput.skipFully(position);
                    enterReadingAtomHeaderState();
                    return false;
                }
                int position2 = (int) (nextFragmentRun.fragment.dataPosition - extractorInput.getPosition());
                if (position2 < 0) {
                    Log.w("FragmentedMp4Extractor", "Ignoring negative offset to sample data.");
                    position2 = 0;
                }
                extractorInput.skipFully(position2);
            }
            if (this.pushIFrame) {
                TrackBundle trackBundle = this.currentTrackBundle;
                if (trackBundle.track.type == Track.TYPE_vide) {
                    int i = trackBundle.currentSampleIndex;
                    TrackFragment trackFragment = trackBundle.fragment;
                    int i2 = i;
                    while (i2 < trackFragment.length && !trackFragment.sampleIsSyncFrameTable[i2]) {
                        i2++;
                    }
                    if (i2 >= trackFragment.length) {
                        int position3 = (int) (this.endOfMdatPosition - extractorInput.getPosition());
                        if (position3 < 0) {
                            throw new ParserException("Offset to end of mdat was negative.");
                        }
                        extractorInput.skipFully(position3);
                        enterReadingAtomHeaderState();
                        return false;
                    }
                    int i3 = 0;
                    while (i < i2) {
                        i3 += trackFragment.sampleSizeTable[i];
                        i++;
                    }
                    if (i3 > 0) {
                        extractorInput.skipFully(i3);
                    }
                }
            }
            TrackBundle trackBundle2 = this.currentTrackBundle;
            TrackFragment trackFragment2 = trackBundle2.fragment;
            this.sampleSize = trackFragment2.sampleSizeTable[trackBundle2.currentSampleIndex];
            if (trackFragment2.definesEncryptionData) {
                int iAppendSampleEncryptionData = appendSampleEncryptionData(trackBundle2);
                this.sampleBytesWritten = iAppendSampleEncryptionData;
                this.sampleSize += iAppendSampleEncryptionData;
            } else {
                this.sampleBytesWritten = 0;
            }
            this.parserState = 4;
            this.sampleCurrentNalBytesRemaining = 0;
        }
        TrackBundle trackBundle3 = this.currentTrackBundle;
        TrackFragment trackFragment3 = trackBundle3.fragment;
        Track track = trackBundle3.track;
        TrackOutput trackOutput = trackBundle3.output;
        int i4 = trackBundle3.currentSampleIndex;
        int i5 = track.nalUnitLengthFieldLength;
        if (i5 != -1) {
            byte[] bArr3 = this.nalLength.data;
            bArr3[0] = 0;
            bArr3[1] = 0;
            bArr3[2] = 0;
            int i6 = 4 - i5;
            while (this.sampleBytesWritten < this.sampleSize) {
                int i7 = this.sampleCurrentNalBytesRemaining;
                if (i7 == 0) {
                    extractorInput.readFully(this.nalLength.data, i6, i5);
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    trackOutput.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                    this.sampleSize += i6;
                } else {
                    int iSampleData = trackOutput.sampleData(extractorInput, i7, false);
                    this.sampleBytesWritten += iSampleData;
                    this.sampleCurrentNalBytesRemaining -= iSampleData;
                }
            }
        } else if (!track.mediaFormat.mimeType.equals("video/wvc1")) {
            while (true) {
                int i8 = this.sampleBytesWritten;
                int i9 = this.sampleSize;
                if (i8 >= i9) {
                    break;
                }
                this.sampleBytesWritten += trackOutput.sampleData(extractorInput, i9 - i8, false);
            }
        } else {
            trackOutput.sampleData(new ParsableByteArray(Mp4Extractor.VC1_START_CODE), 4);
            this.sampleBytesWritten += 4;
            this.sampleSize += 4;
            while (true) {
                int i10 = this.sampleBytesWritten;
                int i11 = this.sampleSize;
                if (i10 >= i11) {
                    break;
                }
                this.sampleBytesWritten += trackOutput.sampleData(extractorInput, i11 - i10, false);
            }
        }
        long samplePresentationTime = trackFragment3.getSamplePresentationTime(i4) * 1000;
        int i12 = (trackFragment3.definesEncryptionData ? 2 : 0) | (trackFragment3.sampleIsSyncFrameTable[i4] ? 1 : 0);
        int i13 = trackFragment3.header.sampleDescriptionIndex;
        if (trackFragment3.definesEncryptionData) {
            TrackEncryptionBox trackEncryptionBox = trackFragment3.trackEncryptionBox;
            if (trackEncryptionBox != null) {
                bArr2 = trackEncryptionBox.keyId;
            } else {
                bArr2 = track.sampleDescriptionEncryptionBoxes[i13].keyId;
            }
            bArr = bArr2;
        } else {
            bArr = null;
        }
        trackOutput.sampleMetadata(samplePresentationTime, i12, this.sampleSize, 0, bArr);
        TrackBundle trackBundle4 = this.currentTrackBundle;
        int i14 = trackBundle4.currentSampleIndex + 1;
        trackBundle4.currentSampleIndex = i14;
        if (i14 == trackFragment3.length) {
            this.currentTrackBundle = null;
        }
        this.parserState = 3;
        return true;
    }

    private static TrackBundle getNextFragmentRun(SparseArray<TrackBundle> sparseArray) {
        int size = sparseArray.size();
        TrackBundle trackBundle = null;
        long j = LongCompanionObject.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            TrackBundle trackBundleValueAt = sparseArray.valueAt(i);
            int i2 = trackBundleValueAt.currentSampleIndex;
            TrackFragment trackFragment = trackBundleValueAt.fragment;
            if (i2 != trackFragment.length) {
                long j2 = trackFragment.dataPosition;
                if (j2 < j) {
                    trackBundle = trackBundleValueAt;
                    j = j2;
                }
            }
        }
        return trackBundle;
    }

    private int appendSampleEncryptionData(TrackBundle trackBundle) {
        TrackFragment trackFragment = trackBundle.fragment;
        ParsableByteArray parsableByteArray = trackFragment.sampleEncryptionData;
        int i = trackFragment.header.sampleDescriptionIndex;
        TrackEncryptionBox trackEncryptionBox = trackFragment.trackEncryptionBox;
        if (trackEncryptionBox == null) {
            trackEncryptionBox = trackBundle.track.sampleDescriptionEncryptionBoxes[i];
        }
        int i2 = trackEncryptionBox.initializationVectorSize;
        boolean z = trackFragment.sampleHasSubsampleEncryptionTable[trackBundle.currentSampleIndex];
        this.encryptionSignalByte.data[0] = (byte) ((z ? 128 : 0) | i2);
        this.encryptionSignalByte.setPosition(0);
        TrackOutput trackOutput = trackBundle.output;
        trackOutput.sampleData(this.encryptionSignalByte, 1);
        trackOutput.sampleData(parsableByteArray, i2);
        if (!z) {
            return i2 + 1;
        }
        int unsignedShort = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(-2);
        int i3 = (unsignedShort * 6) + 2;
        trackOutput.sampleData(parsableByteArray, i3);
        return i2 + 1 + i3;
    }

    private static DrmInitData.Mapped getDrmInitDataFromAtoms(List<Atom.LeafAtom> list) {
        int size = list.size();
        DrmInitData.Mapped mapped = null;
        for (int i = 0; i < size; i++) {
            Atom.LeafAtom leafAtom = list.get(i);
            if (leafAtom.type == Atom.TYPE_pssh) {
                if (mapped == null) {
                    mapped = new DrmInitData.Mapped();
                }
                byte[] bArr = leafAtom.data.data;
                UUID uuid = PsshAtomUtil.parseUuid(bArr);
                byte[] schemeSpecificData = PsshAtomUtil.parseSchemeSpecificData(bArr, uuid);
                if (schemeSpecificData != null) {
                    AtomParsers.parseKidFromPssh(schemeSpecificData, schemeSpecificData.length);
                } else {
                    Log.e("FragmentedMp4Extractor", "SchemeSpecificData is null!");
                }
                if (uuid == null) {
                    Log.w("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
                } else {
                    mapped.put(PsshAtomUtil.parseUuid(bArr), new DrmInitData.SchemeInitData("video/mp4", bArr));
                }
            }
        }
        return mapped;
    }

    private static boolean shouldParseLeafAtom(int i) {
        return i == Atom.TYPE_hdlr || i == Atom.TYPE_mdhd || i == Atom.TYPE_mvhd || i == Atom.TYPE_sidx || i == Atom.TYPE_stsd || i == Atom.TYPE_tfdt || i == Atom.TYPE_tfhd || i == Atom.TYPE_tkhd || i == Atom.TYPE_trex || i == Atom.TYPE_trun || i == Atom.TYPE_pssh || i == Atom.TYPE_saiz || i == Atom.TYPE_saio || i == Atom.TYPE_senc || i == Atom.TYPE_sbgp || i == Atom.TYPE_sgpd || i == Atom.TYPE_uuid || i == Atom.TYPE_elst || i == Atom.TYPE_mehd || i == Atom.TYPE_emsg;
    }

    private static boolean shouldParseContainerAtom(int i) {
        return i == Atom.TYPE_moov || i == Atom.TYPE_trak || i == Atom.TYPE_mdia || i == Atom.TYPE_minf || i == Atom.TYPE_stbl || i == Atom.TYPE_moof || i == Atom.TYPE_traf || i == Atom.TYPE_mvex || i == Atom.TYPE_edts;
    }

    private static final class TrackBundle {
        public int currentSampleIndex;
        public DefaultSampleValues defaultSampleValues;
        public final TrackFragment fragment = new TrackFragment();
        public final TrackOutput output;
        public Track track;

        public TrackBundle(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        public void init(Track track, DefaultSampleValues defaultSampleValues) {
            Assertions.checkNotNull(track);
            this.track = track;
            Assertions.checkNotNull(defaultSampleValues);
            this.defaultSampleValues = defaultSampleValues;
            this.output.format(track.mediaFormat);
            reset();
        }

        public void reset() {
            this.fragment.reset();
            this.currentSampleIndex = 0;
        }
    }

    public FragmentedMp4Extractor(String str) {
        this(0, str);
    }

    public FragmentedMp4Extractor(int i, String str) {
        this(i, (Track) null);
        str.toLowerCase();
    }

    public void setDashInitStage(boolean z) {
        this.isDashInitStage = z;
    }

    @Override // com.google.android.exoplayer.extractor.SyncFrameExtractor
    public void enableSyncFrameMode(boolean z) {
        this.pushIFrame = z;
    }
}
