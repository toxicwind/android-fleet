package com.google.android.exoplayer.extractor.webm;

import android.util.Pair;
import android.util.SparseArray;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.ChunkIndex;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.LongArray;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public final class WebmExtractor implements Extractor {
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final byte[] SUBRIP_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private long blockDurationUs;
    private int blockFlags;
    private int blockLacingSampleCount;
    private int blockLacingSampleIndex;
    private int[] blockLacingSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private boolean sampleRead;
    private boolean sampleSeenReferenceBlock;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentDrmInitData;
    private boolean sentSeekMap;
    private final ParsableByteArray subripSample;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    int getElementType(int i) {
        switch (i) {
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB /* 131 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BUF_FULL /* 155 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BITRATE_TOO_HIGH /* 159 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GET_IFRAME /* 176 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_FULL /* 179 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CERT_EXPIRED /* 186 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PSI_TABLE_UPDATE /* 215 */:
            case 231:
            case 241:
            case 251:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 22186:
            case 22203:
            case 25188:
            case 2352003:
            case 2807729:
                return 2;
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_INP_SIGNAL_UNKNOWN /* 134 */:
            case 17026:
            case 2274716:
                return 3;
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_NOT_SUPPORTED /* 160 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_CLEAR_EOF /* 174 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LIC_EXPIRATION_ERR /* 183 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LIC_NOT_ALLOW /* 187 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN /* 224 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_NAUTILUS_MARLIN /* 225 */:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 25152:
            case 28032:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STRG_ERROR /* 161 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BAD_TICK /* 163 */:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
                return 5;
            default:
                return 0;
        }
    }

    boolean isLevel1Element(int i) {
        return i == 357149030 || i == 524531317 || i == 475249515 || i == 374648427;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void release() {
    }

    public WebmExtractor() {
        this(new DefaultEbmlReader());
    }

    WebmExtractor(EbmlReader ebmlReader) {
        this.segmentContentPosition = -1L;
        this.segmentContentSize = -1L;
        this.timecodeScale = -1L;
        this.durationTimecode = -1L;
        this.durationUs = -1L;
        this.cuesContentPosition = -1L;
        this.seekPositionAfterBuildingCues = -1L;
        this.clusterTimecodeUs = -1L;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlReaderOutput());
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subripSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return new Sniffer().sniff(extractorInput);
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void seek() {
        this.clusterTimecodeUs = -1L;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetSample();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        this.sampleRead = false;
        boolean z = true;
        while (z && !this.sampleRead) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        return z ? 0 : -1;
    }

    void startMasterElement(int i, long j, long j2) throws ParserException {
        if (i == 160) {
            this.sampleSeenReferenceBlock = false;
            return;
        }
        if (i == 174) {
            this.currentTrack = new Track();
            return;
        }
        if (i == 187) {
            this.seenClusterPositionForCurrentCuePoint = false;
            return;
        }
        if (i == 19899) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1L;
            return;
        }
        if (i == 20533) {
            this.currentTrack.hasContentEncryption = true;
            return;
        }
        if (i == 408125543) {
            long j3 = this.segmentContentPosition;
            if (j3 != -1 && j3 != j) {
                throw new ParserException("Multiple Segment elements not supported");
            }
            this.segmentContentPosition = j;
            this.segmentContentSize = j2;
            return;
        }
        if (i == 475249515) {
            this.cueTimesUs = new LongArray();
            this.cueClusterPositions = new LongArray();
        } else if (i == 524531317 && !this.sentSeekMap) {
            if (this.cuesContentPosition != -1) {
                this.seekForCues = true;
            } else {
                this.extractorOutput.seekMap(SeekMap.UNSEEKABLE);
                this.sentSeekMap = true;
            }
        }
    }

    void endMasterElement(int i) throws ParserException {
        if (i == 160) {
            if (this.blockState != 2) {
                return;
            }
            if (!this.sampleSeenReferenceBlock) {
                this.blockFlags |= 1;
            }
            commitSampleToOutput(this.tracks.get(this.blockTrackNumber), this.blockTimeUs);
            this.blockState = 0;
            return;
        }
        if (i == 174) {
            if (isCodecSupported(this.currentTrack.codecId)) {
                Track track = this.currentTrack;
                track.initializeOutput(this.extractorOutput, track.number, this.durationUs);
                SparseArray<Track> sparseArray = this.tracks;
                Track track2 = this.currentTrack;
                sparseArray.put(track2.number, track2);
            }
            this.currentTrack = null;
            return;
        }
        if (i == 19899) {
            int i2 = this.seekEntryId;
            if (i2 != -1) {
                long j = this.seekEntryPosition;
                if (j != -1) {
                    if (i2 == 475249515) {
                        this.cuesContentPosition = j;
                        return;
                    }
                    return;
                }
            }
            throw new ParserException("Mandatory element SeekID or SeekPosition not found");
        }
        if (i == 25152) {
            Track track3 = this.currentTrack;
            if (track3.hasContentEncryption) {
                byte[] bArr = track3.encryptionKeyId;
                if (bArr == null) {
                    throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
                }
                if (this.sentDrmInitData) {
                    return;
                }
                this.extractorOutput.drmInitData(new DrmInitData.Universal(new DrmInitData.SchemeInitData("video/webm", bArr)));
                this.sentDrmInitData = true;
                return;
            }
            return;
        }
        if (i == 28032) {
            Track track4 = this.currentTrack;
            if (track4.hasContentEncryption && track4.sampleStrippedBytes != null) {
                throw new ParserException("Combining encryption and compression is not supported");
            }
            return;
        }
        if (i == 357149030) {
            if (this.timecodeScale == -1) {
                this.timecodeScale = 1000000L;
            }
            long j2 = this.durationTimecode;
            if (j2 != -1) {
                this.durationUs = scaleTimecodeToUs(j2);
                return;
            }
            return;
        }
        if (i != 374648427) {
            if (i == 475249515 && !this.sentSeekMap) {
                this.extractorOutput.seekMap(buildSeekMap());
                this.sentSeekMap = true;
                return;
            }
            return;
        }
        if (this.tracks.size() == 0) {
            throw new ParserException("No valid tracks were found");
        }
        this.extractorOutput.endTracks();
    }

    void integerElement(int i, long j) throws ParserException {
        switch (i) {
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB /* 131 */:
                this.currentTrack.type = (int) j;
                return;
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BUF_FULL /* 155 */:
                this.blockDurationUs = scaleTimecodeToUs(j);
                return;
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BITRATE_TOO_HIGH /* 159 */:
                this.currentTrack.channelCount = (int) j;
                return;
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GET_IFRAME /* 176 */:
                this.currentTrack.width = (int) j;
                return;
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_FULL /* 179 */:
                this.cueTimesUs.add(scaleTimecodeToUs(j));
                return;
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CERT_EXPIRED /* 186 */:
                this.currentTrack.height = (int) j;
                return;
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PSI_TABLE_UPDATE /* 215 */:
                this.currentTrack.number = (int) j;
                return;
            case 231:
                this.clusterTimecodeUs = scaleTimecodeToUs(j);
                return;
            case 241:
                if (this.seenClusterPositionForCurrentCuePoint) {
                    return;
                }
                this.cueClusterPositions.add(j);
                this.seenClusterPositionForCurrentCuePoint = true;
                return;
            case 251:
                this.sampleSeenReferenceBlock = true;
                return;
            case 16980:
                if (j == 3) {
                    return;
                }
                throw new ParserException("ContentCompAlgo " + j + " not supported");
            case 17029:
                if (j < 1 || j > 2) {
                    throw new ParserException("DocTypeReadVersion " + j + " not supported");
                }
                return;
            case 17143:
                if (j == 1) {
                    return;
                }
                throw new ParserException("EBMLReadVersion " + j + " not supported");
            case 18401:
                if (j == 5) {
                    return;
                }
                throw new ParserException("ContentEncAlgo " + j + " not supported");
            case 18408:
                if (j == 1) {
                    return;
                }
                throw new ParserException("AESSettingsCipherMode " + j + " not supported");
            case 20529:
                if (j == 0) {
                    return;
                }
                throw new ParserException("ContentEncodingOrder " + j + " not supported");
            case 20530:
                if (j == 1) {
                    return;
                }
                throw new ParserException("ContentEncodingScope " + j + " not supported");
            case 21420:
                this.seekEntryPosition = j + this.segmentContentPosition;
                return;
            case 21432:
                int i2 = (int) j;
                if (i2 == 0) {
                    this.currentTrack.stereoMode = 0;
                    return;
                }
                if (i2 == 1) {
                    this.currentTrack.stereoMode = 2;
                    return;
                } else if (i2 == 3) {
                    this.currentTrack.stereoMode = 1;
                    return;
                } else {
                    if (i2 != 15) {
                        return;
                    }
                    this.currentTrack.stereoMode = 3;
                    return;
                }
            case 21680:
                this.currentTrack.displayWidth = (int) j;
                return;
            case 21682:
                this.currentTrack.displayUnit = (int) j;
                return;
            case 21690:
                this.currentTrack.displayHeight = (int) j;
                return;
            case 22186:
                this.currentTrack.codecDelayNs = j;
                return;
            case 22203:
                this.currentTrack.seekPreRollNs = j;
                return;
            case 25188:
                this.currentTrack.audioBitDepth = (int) j;
                return;
            case 2352003:
                this.currentTrack.defaultSampleDurationNs = (int) j;
                return;
            case 2807729:
                this.timecodeScale = j;
                return;
            default:
                return;
        }
    }

    void floatElement(int i, double d) {
        if (i == 181) {
            this.currentTrack.sampleRate = (int) d;
        } else {
            if (i != 17545) {
                return;
            }
            this.durationTimecode = (long) d;
        }
    }

    void stringElement(int i, String str) throws ParserException {
        if (i == 134) {
            this.currentTrack.codecId = str;
            return;
        }
        if (i != 17026) {
            if (i != 2274716) {
                return;
            }
            this.currentTrack.language = str;
        } else {
            if ("webm".equals(str) || "matroska".equals(str)) {
                return;
            }
            throw new ParserException("DocType " + str + " not supported");
        }
    }

    void binaryElement(int i, int i2, ExtractorInput extractorInput) throws InterruptedException, IOException {
        int i3;
        int i4;
        int i5;
        int i6;
        int[] iArr;
        if (i != 161 && i != 163) {
            if (i == 16981) {
                byte[] bArr = new byte[i2];
                this.currentTrack.sampleStrippedBytes = bArr;
                extractorInput.readFully(bArr, 0, i2);
                return;
            }
            if (i == 18402) {
                byte[] bArr2 = new byte[i2];
                this.currentTrack.encryptionKeyId = bArr2;
                extractorInput.readFully(bArr2, 0, i2);
                return;
            }
            if (i == 21419) {
                Arrays.fill(this.seekEntryIdBytes.data, (byte) 0);
                extractorInput.readFully(this.seekEntryIdBytes.data, 4 - i2, i2);
                this.seekEntryIdBytes.setPosition(0);
                this.seekEntryId = (int) this.seekEntryIdBytes.readUnsignedInt();
                return;
            }
            if (i == 25506) {
                byte[] bArr3 = new byte[i2];
                this.currentTrack.codecPrivate = bArr3;
                extractorInput.readFully(bArr3, 0, i2);
                return;
            } else if (i == 30322) {
                byte[] bArr4 = new byte[i2];
                this.currentTrack.projectionData = bArr4;
                extractorInput.readFully(bArr4, 0, i2);
                return;
            } else {
                throw new ParserException("Unexpected id: " + i);
            }
        }
        int i7 = 1;
        if (this.blockState == 0) {
            this.blockTrackNumber = (int) this.varintReader.readUnsignedVarint(extractorInput, false, true, 8);
            this.blockTrackNumberLength = this.varintReader.getLastLength();
            this.blockDurationUs = -1L;
            this.blockState = 1;
            this.scratch.reset();
        }
        Track track = this.tracks.get(this.blockTrackNumber);
        if (track == null) {
            extractorInput.skipFully(i2 - this.blockTrackNumberLength);
            this.blockState = 0;
            return;
        }
        if (this.blockState == 1) {
            readScratch(extractorInput, 3);
            int i8 = (this.scratch.data[2] & 6) >> 1;
            byte b = 255;
            if (i8 == 0) {
                this.blockLacingSampleCount = 1;
                int[] iArrEnsureArrayCapacity = ensureArrayCapacity(this.blockLacingSampleSizes, 1);
                this.blockLacingSampleSizes = iArrEnsureArrayCapacity;
                iArrEnsureArrayCapacity[0] = (i2 - this.blockTrackNumberLength) - 3;
            } else {
                if (i != 163) {
                    throw new ParserException("Lacing only supported in SimpleBlocks.");
                }
                int i9 = 4;
                readScratch(extractorInput, 4);
                int i10 = (this.scratch.data[3] & 255) + 1;
                this.blockLacingSampleCount = i10;
                int[] iArrEnsureArrayCapacity2 = ensureArrayCapacity(this.blockLacingSampleSizes, i10);
                this.blockLacingSampleSizes = iArrEnsureArrayCapacity2;
                if (i8 == 2) {
                    int i11 = (i2 - this.blockTrackNumberLength) - 4;
                    int i12 = this.blockLacingSampleCount;
                    Arrays.fill(iArrEnsureArrayCapacity2, 0, i12, i11 / i12);
                } else if (i8 == 1) {
                    int i13 = 0;
                    int i14 = 0;
                    while (true) {
                        i5 = this.blockLacingSampleCount;
                        if (i13 >= i5 - 1) {
                            break;
                        }
                        this.blockLacingSampleSizes[i13] = 0;
                        do {
                            i9++;
                            readScratch(extractorInput, i9);
                            i6 = this.scratch.data[i9 - 1] & 255;
                            iArr = this.blockLacingSampleSizes;
                            iArr[i13] = iArr[i13] + i6;
                        } while (i6 == 255);
                        i14 += iArr[i13];
                        i13++;
                    }
                    this.blockLacingSampleSizes[i5 - 1] = ((i2 - this.blockTrackNumberLength) - i9) - i14;
                } else {
                    if (i8 != 3) {
                        throw new ParserException("Unexpected lacing value: " + i8);
                    }
                    int i15 = 0;
                    int i16 = 0;
                    while (true) {
                        int i17 = this.blockLacingSampleCount;
                        if (i15 < i17 - 1) {
                            this.blockLacingSampleSizes[i15] = 0;
                            i9++;
                            readScratch(extractorInput, i9);
                            int i18 = i9 - 1;
                            if (this.scratch.data[i18] == 0) {
                                throw new ParserException("No valid varint length mask found");
                            }
                            long j = 0;
                            int i19 = 0;
                            while (true) {
                                if (i19 >= 8) {
                                    i4 = i15;
                                    break;
                                }
                                int i20 = i7 << (7 - i19);
                                if ((this.scratch.data[i18] & i20) != 0) {
                                    i9 += i19;
                                    readScratch(extractorInput, i9);
                                    i4 = i15;
                                    long j2 = (~i20) & this.scratch.data[i18] & b;
                                    int i21 = i18 + 1;
                                    while (true) {
                                        j = j2;
                                        if (i21 >= i9) {
                                            break;
                                        }
                                        j2 = (j << 8) | ((long) (this.scratch.data[i21] & b));
                                        i21++;
                                        b = 255;
                                    }
                                    if (i4 <= 0) {
                                        break;
                                    }
                                    j -= (1 << ((i19 * 7) + 6)) - 1;
                                    break;
                                }
                                i19++;
                                i7 = 1;
                                b = 255;
                            }
                            long j3 = j;
                            if (j3 < -2147483648L || j3 > 2147483647L) {
                                throw new ParserException("EBML lacing sample size out of range.");
                            }
                            int i22 = (int) j3;
                            int[] iArr2 = this.blockLacingSampleSizes;
                            if (i4 != 0) {
                                i22 += iArr2[i4 - 1];
                            }
                            iArr2[i4] = i22;
                            i16 += this.blockLacingSampleSizes[i4];
                            i15 = i4 + 1;
                            i7 = 1;
                            b = 255;
                        } else {
                            i7 = 1;
                            this.blockLacingSampleSizes[i17 - 1] = ((i2 - this.blockTrackNumberLength) - i9) - i16;
                            break;
                        }
                    }
                }
            }
            byte[] bArr5 = this.scratch.data;
            this.blockTimeUs = this.clusterTimecodeUs + scaleTimecodeToUs((bArr5[i7] & 255) | (bArr5[0] << 8));
            this.blockFlags = ((track.type == 2 || (i == 163 && (this.scratch.data[2] & ByteCompanionObject.MIN_VALUE) == 128)) ? 1 : 0) | ((this.scratch.data[2] & 8) == 8 ? 134217728 : 0);
            this.blockState = 2;
            this.blockLacingSampleIndex = 0;
            i3 = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BAD_TICK;
        } else {
            i3 = 163;
        }
        if (i != i3) {
            writeSampleData(extractorInput, track, this.blockLacingSampleSizes[0]);
            return;
        }
        while (true) {
            int i23 = this.blockLacingSampleIndex;
            if (i23 < this.blockLacingSampleCount) {
                writeSampleData(extractorInput, track, this.blockLacingSampleSizes[i23]);
                commitSampleToOutput(track, this.blockTimeUs + ((long) ((this.blockLacingSampleIndex * track.defaultSampleDurationNs) / 1000)));
                this.blockLacingSampleIndex++;
            } else {
                this.blockState = 0;
                return;
            }
        }
    }

    private void commitSampleToOutput(Track track, long j) {
        if ("S_TEXT/UTF8".equals(track.codecId)) {
            writeSubripSample(track);
        }
        track.output.sampleMetadata(j, this.blockFlags, this.sampleBytesWritten, 0, track.encryptionKeyId);
        this.sampleRead = true;
        resetSample();
    }

    private void resetSample() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = (byte) 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }

    private void readScratch(ExtractorInput extractorInput, int i) throws InterruptedException, IOException {
        if (this.scratch.limit() >= i) {
            return;
        }
        if (this.scratch.capacity() < i) {
            ParsableByteArray parsableByteArray = this.scratch;
            byte[] bArr = parsableByteArray.data;
            parsableByteArray.reset(Arrays.copyOf(bArr, Math.max(bArr.length * 2, i)), this.scratch.limit());
        }
        ParsableByteArray parsableByteArray2 = this.scratch;
        extractorInput.readFully(parsableByteArray2.data, parsableByteArray2.limit(), i - this.scratch.limit());
        this.scratch.setLimit(i);
    }

    private void writeSampleData(ExtractorInput extractorInput, Track track, int i) throws InterruptedException, IOException {
        int i2;
        byte[] bArr = SUBRIP_PREFIX;
        if ("S_TEXT/UTF8".equals(track.codecId)) {
            int length = bArr.length + i;
            if (this.subripSample.capacity() < length) {
                this.subripSample.data = Arrays.copyOf(bArr, length + i);
            }
            extractorInput.readFully(this.subripSample.data, bArr.length, i);
            this.subripSample.setPosition(0);
            this.subripSample.setLimit(length);
            return;
        }
        TrackOutput trackOutput = track.output;
        if (!this.sampleEncodingHandled) {
            if (track.hasContentEncryption) {
                this.blockFlags &= -3;
                if (!this.sampleSignalByteRead) {
                    extractorInput.readFully(this.scratch.data, 0, 1);
                    this.sampleBytesRead++;
                    byte[] bArr2 = this.scratch.data;
                    if ((bArr2[0] & ByteCompanionObject.MIN_VALUE) == 128) {
                        throw new ParserException("Extension bit is set in signal byte");
                    }
                    this.sampleSignalByte = bArr2[0];
                    this.sampleSignalByteRead = true;
                }
                if ((this.sampleSignalByte & 1) == 1) {
                    boolean z = (this.sampleSignalByte & 2) == 2;
                    this.blockFlags |= 2;
                    if (!this.sampleInitializationVectorRead) {
                        extractorInput.readFully(this.encryptionInitializationVector.data, 0, 8);
                        this.sampleBytesRead += 8;
                        this.sampleInitializationVectorRead = true;
                        this.scratch.data[0] = (byte) ((z ? 128 : 0) | 8);
                        this.scratch.setPosition(0);
                        trackOutput.sampleData(this.scratch, 1);
                        this.sampleBytesWritten++;
                        this.encryptionInitializationVector.setPosition(0);
                        trackOutput.sampleData(this.encryptionInitializationVector, 8);
                        this.sampleBytesWritten += 8;
                    }
                    if (z) {
                        if (!this.samplePartitionCountRead) {
                            extractorInput.readFully(this.scratch.data, 0, 1);
                            this.sampleBytesRead++;
                            this.scratch.setPosition(0);
                            this.samplePartitionCount = this.scratch.readUnsignedByte();
                            this.samplePartitionCountRead = true;
                        }
                        int i3 = this.samplePartitionCount * 4;
                        if (this.scratch.limit() < i3) {
                            this.scratch.reset(new byte[i3], i3);
                        }
                        extractorInput.readFully(this.scratch.data, 0, i3);
                        this.sampleBytesRead += i3;
                        this.scratch.setPosition(0);
                        this.scratch.setLimit(i3);
                        short s = (short) ((this.samplePartitionCount / 2) + 1);
                        int i4 = (s * 6) + 2;
                        ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                        if (byteBuffer == null || byteBuffer.capacity() < i4) {
                            this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i4);
                        }
                        this.encryptionSubsampleDataBuffer.position(0);
                        this.encryptionSubsampleDataBuffer.putShort(s);
                        int i5 = 0;
                        int i6 = 0;
                        while (true) {
                            i2 = this.samplePartitionCount;
                            if (i5 >= i2) {
                                break;
                            }
                            int unsignedIntToInt = this.scratch.readUnsignedIntToInt();
                            if (i5 % 2 == 0) {
                                this.encryptionSubsampleDataBuffer.putShort((short) (unsignedIntToInt - i6));
                            } else {
                                this.encryptionSubsampleDataBuffer.putInt(unsignedIntToInt - i6);
                            }
                            i5++;
                            i6 = unsignedIntToInt;
                        }
                        int i7 = (i - this.sampleBytesRead) - i6;
                        if (i2 % 2 == 1) {
                            this.encryptionSubsampleDataBuffer.putInt(i7);
                        } else {
                            this.encryptionSubsampleDataBuffer.putShort((short) i7);
                            this.encryptionSubsampleDataBuffer.putInt(0);
                        }
                        this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i4);
                        trackOutput.sampleData(this.encryptionSubsampleData, i4);
                        this.sampleBytesWritten += i4;
                    }
                }
            } else {
                byte[] bArr3 = track.sampleStrippedBytes;
                if (bArr3 != null) {
                    this.sampleStrippedBytes.reset(bArr3, bArr3.length);
                }
            }
            this.sampleEncodingHandled = true;
        }
        int iLimit = i + this.sampleStrippedBytes.limit();
        if (!"V_MPEG4/ISO/AVC".equals(track.codecId) && !"V_MPEGH/ISO/HEVC".equals(track.codecId)) {
            while (true) {
                int i8 = this.sampleBytesRead;
                if (i8 >= iLimit) {
                    break;
                } else {
                    readToOutput(extractorInput, trackOutput, iLimit - i8);
                }
            }
        } else {
            byte[] bArr4 = this.nalLength.data;
            bArr4[0] = 0;
            bArr4[1] = 0;
            bArr4[2] = 0;
            int i9 = track.nalUnitLengthFieldLength;
            int i10 = 4 - i9;
            while (this.sampleBytesRead < iLimit) {
                int i11 = this.sampleCurrentNalBytesRemaining;
                if (i11 == 0) {
                    readToTarget(extractorInput, bArr4, i10, i9);
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    trackOutput.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                } else {
                    this.sampleCurrentNalBytesRemaining = i11 - readToOutput(extractorInput, trackOutput, i11);
                }
            }
        }
        if ("A_VORBIS".equals(track.codecId)) {
            this.vorbisNumPageSamples.setPosition(0);
            trackOutput.sampleData(this.vorbisNumPageSamples, 4);
            this.sampleBytesWritten += 4;
        }
    }

    private void writeSubripSample(Track track) {
        setSubripSampleEndTimecode(this.subripSample.data, this.blockDurationUs);
        TrackOutput trackOutput = track.output;
        ParsableByteArray parsableByteArray = this.subripSample;
        trackOutput.sampleData(parsableByteArray, parsableByteArray.limit());
        this.sampleBytesWritten += this.subripSample.limit();
    }

    private static void setSubripSampleEndTimecode(byte[] bArr, long j) {
        byte[] bytes;
        if (j == -1) {
            bytes = SUBRIP_TIMECODE_EMPTY;
        } else {
            int i = (int) (j / 3600000000L);
            long j2 = j - (((long) i) * 3600000000L);
            int i2 = (int) (j2 / 60000000);
            long j3 = j2 - (((long) i2) * 60000000);
            int i3 = (int) (j3 / 1000000);
            bytes = String.format(Locale.US, "%02d:%02d:%02d,%03d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) ((j3 - (((long) i3) * 1000000)) / 1000))).getBytes();
        }
        System.arraycopy(bytes, 0, bArr, 19, 12);
    }

    private void readToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        int iMin = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + iMin, i2 - iMin);
        if (iMin > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, iMin);
        }
        this.sampleBytesRead += i2;
    }

    private int readToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws InterruptedException, IOException {
        int iSampleData;
        int iBytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (iBytesLeft > 0) {
            iSampleData = Math.min(i, iBytesLeft);
            trackOutput.sampleData(this.sampleStrippedBytes, iSampleData);
        } else {
            iSampleData = trackOutput.sampleData(extractorInput, i, false);
        }
        this.sampleBytesRead += iSampleData;
        this.sampleBytesWritten += iSampleData;
        return iSampleData;
    }

    private SeekMap buildSeekMap() {
        LongArray longArray;
        LongArray longArray2;
        if (this.segmentContentPosition == -1 || this.durationUs == -1 || (longArray = this.cueTimesUs) == null || longArray.size() == 0 || (longArray2 = this.cueClusterPositions) == null || longArray2.size() != this.cueTimesUs.size()) {
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
            return SeekMap.UNSEEKABLE;
        }
        int size = this.cueTimesUs.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jArr3[i2] = this.cueTimesUs.get(i2);
            jArr[i2] = this.segmentContentPosition + this.cueClusterPositions.get(i2);
        }
        while (true) {
            int i3 = size - 1;
            if (i < i3) {
                int i4 = i + 1;
                iArr[i] = (int) (jArr[i4] - jArr[i]);
                jArr2[i] = jArr3[i4] - jArr3[i];
                i = i4;
            } else {
                iArr[i3] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i3]);
                jArr2[i3] = this.durationUs - jArr3[i3];
                this.cueTimesUs = null;
                this.cueClusterPositions = null;
                return new ChunkIndex(iArr, jArr, jArr2, jArr3);
            }
        }
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (positionHolder == null) {
            return false;
        }
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j2 = this.seekPositionAfterBuildingCues;
            if (j2 != -1) {
                positionHolder.position = j2;
                this.seekPositionAfterBuildingCues = -1L;
                return true;
            }
        }
        return false;
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        long j2 = this.timecodeScale;
        if (j2 == -1) {
            throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
        }
        return Util.scaleLargeTimestamp(j, j2, 1000L);
    }

    private static boolean isCodecSupported(String str) {
        return "V_VP8".equals(str) || "V_VP9".equals(str) || "V_MPEG2".equals(str) || "V_MPEG4/ISO/SP".equals(str) || "V_MPEG4/ISO/ASP".equals(str) || "V_MPEG4/ISO/AP".equals(str) || "V_MPEG4/ISO/AVC".equals(str) || "V_MPEGH/ISO/HEVC".equals(str) || "V_MS/VFW/FOURCC".equals(str) || "A_OPUS".equals(str) || "A_VORBIS".equals(str) || "A_AAC".equals(str) || "A_MPEG/L3".equals(str) || "A_AC3".equals(str) || "A_EAC3".equals(str) || "A_TRUEHD".equals(str) || "A_DTS".equals(str) || "A_DTS/EXPRESS".equals(str) || "A_DTS/LOSSLESS".equals(str) || "A_FLAC".equals(str) || "A_MS/ACM".equals(str) || "A_PCM/INT/LIT".equals(str) || "S_TEXT/UTF8".equals(str) || "S_VOBSUB".equals(str) || "S_HDMV/PGS".equals(str);
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        return iArr.length >= i ? iArr : new int[Math.max(iArr.length * 2, i)];
    }

    private final class InnerEbmlReaderOutput implements EbmlReaderOutput {
        private InnerEbmlReaderOutput() {
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public int getElementType(int i) {
            return WebmExtractor.this.getElementType(i);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public boolean isLevel1Element(int i) {
            return WebmExtractor.this.isLevel1Element(i);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public void startMasterElement(int i, long j, long j2) throws ParserException {
            WebmExtractor.this.startMasterElement(i, j, j2);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public void endMasterElement(int i) throws ParserException {
            WebmExtractor.this.endMasterElement(i);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public void integerElement(int i, long j) throws ParserException {
            WebmExtractor.this.integerElement(i, j);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public void floatElement(int i, double d) throws ParserException {
            WebmExtractor.this.floatElement(i, d);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public void stringElement(int i, String str) throws ParserException {
            WebmExtractor.this.stringElement(i, str);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws InterruptedException, IOException {
            WebmExtractor.this.binaryElement(i, i2, extractorInput);
        }
    }

    private static final class Track {
        public int audioBitDepth;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public byte[] encryptionKeyId;
        public boolean hasContentEncryption;
        public int height;
        private String language;
        public int nalUnitLengthFieldLength;
        public int number;
        public TrackOutput output;
        public byte[] projectionData;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int stereoMode;
        public int type;
        public int width;

        private Track() {
            this.width = -1;
            this.height = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.projectionData = null;
            this.stereoMode = -1;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0L;
            this.seekPreRollNs = 0L;
            this.language = MtkTvConfigTypeBase.S639_CFG_LANG_ENG;
        }

        /* JADX WARN: Code duplicated, block: B:127:0x0297  */
        /* JADX WARN: Code duplicated, block: B:128:0x02b3  */
        /* JADX WARN: Code duplicated, block: B:130:0x02b9  */
        /* JADX WARN: Code duplicated, block: B:132:0x02bd  */
        /* JADX WARN: Code duplicated, block: B:134:0x02c1  */
        /* JADX WARN: Code duplicated, block: B:137:0x02c9  */
        /* JADX WARN: Code duplicated, block: B:141:0x02d3  */
        /* JADX WARN: Code duplicated, block: B:145:0x0304  */
        /* JADX WARN: Code duplicated, block: B:147:0x030a  */
        /* JADX WARN: Code duplicated, block: B:148:0x031d  */
        /* JADX WARN: Code duplicated, block: B:150:0x0323  */
        public void initializeOutput(ExtractorOutput extractorOutput, int i, long j) throws ParserException {
            byte b;
            int i2;
            int i3;
            int i4;
            int pcmEncoding;
            List list;
            MediaFormat mediaFormatCreateImageFormat;
            float f;
            int i5;
            int i6;
            int i7;
            int i8;
            switch (this.codecId) {
                case "V_MPEG4/ISO/AP":
                    b = 5;
                    break;
                case "V_MPEG4/ISO/SP":
                    b = 3;
                    break;
                case "A_MS/ACM":
                    b = 20;
                    break;
                case "A_TRUEHD":
                    b = 15;
                    break;
                case "A_VORBIS":
                    b = 9;
                    break;
                case "A_MPEG/L3":
                    b = 12;
                    break;
                case "V_MS/VFW/FOURCC":
                    b = 8;
                    break;
                case "V_MPEG4/ISO/ASP":
                    b = 4;
                    break;
                case "V_MPEG4/ISO/AVC":
                    b = 6;
                    break;
                case "S_VOBSUB":
                    b = 23;
                    break;
                case "A_DTS/LOSSLESS":
                    b = 18;
                    break;
                case "A_AAC":
                    b = 11;
                    break;
                case "A_AC3":
                    b = 13;
                    break;
                case "A_DTS":
                    b = 16;
                    break;
                case "V_VP8":
                    b = 0;
                    break;
                case "V_VP9":
                    b = 1;
                    break;
                case "S_HDMV/PGS":
                    b = 24;
                    break;
                case "A_DTS/EXPRESS":
                    b = 17;
                    break;
                case "A_PCM/INT/LIT":
                    b = 21;
                    break;
                case "V_MPEGH/ISO/HEVC":
                    b = 7;
                    break;
                case "S_TEXT/UTF8":
                    b = 22;
                    break;
                case "V_MPEG2":
                    b = 2;
                    break;
                case "A_EAC3":
                    b = 14;
                    break;
                case "A_FLAC":
                    b = 19;
                    break;
                case "A_OPUS":
                    b = 10;
                    break;
                default:
                    b = -1;
                    break;
            }
            String str = "audio/raw";
            List listSingletonList = null;
            switch (b) {
                case 0:
                    str = "video/x-vnd.on2.vp8";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else if (MimeTypes.isVideo(str)) {
                        if (this.displayUnit == 0) {
                            i7 = this.displayWidth;
                            if (i7 == -1) {
                                i7 = this.width;
                            }
                            this.displayWidth = i7;
                            i8 = this.displayHeight;
                            if (i8 == -1) {
                                i8 = this.height;
                            }
                            this.displayHeight = i8;
                        }
                        f = -1.0f;
                        i5 = this.displayWidth;
                        if (i5 != -1 && (i6 = this.displayHeight) != -1) {
                            f = (this.height * i5) / (this.width * i6);
                        }
                        mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                    } else if ("application/x-subrip".equals(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                    } else if (!"application/vobsub".equals(str) || "application/pgs".equals(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                    } else {
                        throw new ParserException("Unexpected MIME type.");
                    }
                    TrackOutput trackOutputTrack = extractorOutput.track(this.number);
                    this.output = trackOutputTrack;
                    trackOutputTrack.format(mediaFormatCreateImageFormat);
                    return;
                case 1:
                    str = "video/x-vnd.on2.vp9";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack2 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack2;
                    trackOutputTrack2.format(mediaFormatCreateImageFormat);
                    return;
                case 2:
                    str = "video/mpeg2";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack3 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack3;
                    trackOutputTrack3.format(mediaFormatCreateImageFormat);
                    return;
                case 3:
                case 4:
                case 5:
                    byte[] bArr = this.codecPrivate;
                    listSingletonList = bArr != null ? Collections.singletonList(bArr) : null;
                    str = "video/mp4v-es";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack4 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack4;
                    trackOutputTrack4.format(mediaFormatCreateImageFormat);
                    return;
                case 6:
                    Pair<List<byte[]>, Integer> avcCodecPrivate = parseAvcCodecPrivate(new ParsableByteArray(this.codecPrivate));
                    listSingletonList = (List) avcCodecPrivate.first;
                    this.nalUnitLengthFieldLength = ((Integer) avcCodecPrivate.second).intValue();
                    str = "video/avc";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack5 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack5;
                    trackOutputTrack5.format(mediaFormatCreateImageFormat);
                    return;
                case 7:
                    Pair<List<byte[]>, Integer> hevcCodecPrivate = parseHevcCodecPrivate(new ParsableByteArray(this.codecPrivate));
                    listSingletonList = (List) hevcCodecPrivate.first;
                    this.nalUnitLengthFieldLength = ((Integer) hevcCodecPrivate.second).intValue();
                    str = "video/hevc";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack6 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack6;
                    trackOutputTrack6.format(mediaFormatCreateImageFormat);
                    return;
                case 8:
                    listSingletonList = parseFourCcVc1Private(new ParsableByteArray(this.codecPrivate));
                    str = "video/wvc1";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack7 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack7;
                    trackOutputTrack7.format(mediaFormatCreateImageFormat);
                    return;
                case 9:
                    i2 = 8192;
                    listSingletonList = parseVorbisCodecPrivate(this.codecPrivate);
                    str = "audio/vorbis";
                    i3 = i2;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack8 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack8;
                    trackOutputTrack8.format(mediaFormatCreateImageFormat);
                    return;
                case 10:
                    i2 = 5760;
                    listSingletonList = new ArrayList(3);
                    listSingletonList.add(this.codecPrivate);
                    listSingletonList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.codecDelayNs).array());
                    listSingletonList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.seekPreRollNs).array());
                    str = "audio/opus";
                    i3 = i2;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack9 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack9;
                    trackOutputTrack9.format(mediaFormatCreateImageFormat);
                    return;
                case 11:
                    listSingletonList = Collections.singletonList(this.codecPrivate);
                    str = "audio/mp4a-latm";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack10 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack10;
                    trackOutputTrack10.format(mediaFormatCreateImageFormat);
                    return;
                case 12:
                    i2 = 4096;
                    str = "audio/mpeg";
                    i3 = i2;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack11 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack11;
                    trackOutputTrack11.format(mediaFormatCreateImageFormat);
                    return;
                case 13:
                    str = "audio/ac3";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack12 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack12;
                    trackOutputTrack12.format(mediaFormatCreateImageFormat);
                    return;
                case 14:
                    str = "audio/eac3";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack13 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack13;
                    trackOutputTrack13.format(mediaFormatCreateImageFormat);
                    return;
                case 15:
                    str = "audio/true-hd";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack14 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack14;
                    trackOutputTrack14.format(mediaFormatCreateImageFormat);
                    return;
                case 16:
                case 17:
                    str = "audio/vnd.dts";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack15 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack15;
                    trackOutputTrack15.format(mediaFormatCreateImageFormat);
                    return;
                case 18:
                    str = "audio/vnd.dts.hd";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack16 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack16;
                    trackOutputTrack16.format(mediaFormatCreateImageFormat);
                    return;
                case 19:
                    listSingletonList = Collections.singletonList(this.codecPrivate);
                    str = "audio/x-flac";
                    i3 = -1;
                    i4 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack17 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack17;
                    trackOutputTrack17.format(mediaFormatCreateImageFormat);
                    return;
                case 20:
                    if (!parseMsAcmCodecPrivate(new ParsableByteArray(this.codecPrivate))) {
                        throw new ParserException("Non-PCM MS/ACM is unsupported");
                    }
                    pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                    if (pcmEncoding == 0) {
                        throw new ParserException("Unsupported PCM bit depth: " + this.audioBitDepth);
                    }
                    i4 = pcmEncoding;
                    i3 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack18 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack18;
                    trackOutputTrack18.format(mediaFormatCreateImageFormat);
                    return;
                case 21:
                    pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                    if (pcmEncoding == 0) {
                        throw new ParserException("Unsupported PCM bit depth: " + this.audioBitDepth);
                    }
                    i4 = pcmEncoding;
                    i3 = -1;
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack19 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack19;
                    trackOutputTrack19.format(mediaFormatCreateImageFormat);
                    return;
                case 22:
                    i3 = -1;
                    i4 = -1;
                    str = "application/x-subrip";
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack110 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack110;
                    trackOutputTrack110.format(mediaFormatCreateImageFormat);
                    return;
                case 23:
                    listSingletonList = Collections.singletonList(this.codecPrivate);
                    i3 = -1;
                    i4 = -1;
                    str = "application/vobsub";
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack111 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack111;
                    trackOutputTrack111.format(mediaFormatCreateImageFormat);
                    return;
                case 24:
                    i3 = -1;
                    i4 = -1;
                    str = "application/pgs";
                    list = listSingletonList;
                    if (MimeTypes.isAudio(str)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str, -1, i3, j, this.channelCount, this.sampleRate, list, this.language, i4);
                    } else {
                        if (MimeTypes.isVideo(str)) {
                            if (this.displayUnit == 0) {
                                i7 = this.displayWidth;
                                if (i7 == -1) {
                                    i7 = this.width;
                                }
                                this.displayWidth = i7;
                                i8 = this.displayHeight;
                                if (i8 == -1) {
                                    i8 = this.height;
                                }
                                this.displayHeight = i8;
                            }
                            f = -1.0f;
                            i5 = this.displayWidth;
                            if (i5 != -1) {
                                f = (this.height * i5) / (this.width * i6);
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str, -1, i3, j, this.width, this.height, list, -1, f, this.projectionData, this.stereoMode);
                        } else if ("application/x-subrip".equals(str)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str, -1, j, list, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack112 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack112;
                    trackOutputTrack112.format(mediaFormatCreateImageFormat);
                    return;
                default:
                    throw new ParserException("Unrecognized codec identifier.");
            }
        }

        private static List<byte[]> parseFourCcVc1Private(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long littleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (littleEndianUnsignedInt != 826496599) {
                    throw new ParserException("Unsupported FourCC compression type: " + littleEndianUnsignedInt);
                }
                byte[] bArr = parsableByteArray.data;
                for (int position = parsableByteArray.getPosition() + 20; position < bArr.length - 4; position++) {
                    if (bArr[position] == 0 && bArr[position + 1] == 0 && bArr[position + 2] == 1 && bArr[position + 3] == 15) {
                        return Collections.singletonList(Arrays.copyOfRange(bArr, position, bArr.length));
                    }
                }
                throw new ParserException("Failed to find FourCC VC1 initialization data");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing FourCC VC1 codec private");
            }
        }

        private static Pair<List<byte[]>, Integer> parseAvcCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.setPosition(4);
                int unsignedByte = (parsableByteArray.readUnsignedByte() & 3) + 1;
                if (unsignedByte == 3) {
                    throw new ParserException();
                }
                ArrayList arrayList = new ArrayList();
                int unsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
                for (int i = 0; i < unsignedByte2; i++) {
                    arrayList.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
                }
                int unsignedByte3 = parsableByteArray.readUnsignedByte();
                for (int i2 = 0; i2 < unsignedByte3; i2++) {
                    arrayList.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
                }
                return Pair.create(arrayList, Integer.valueOf(unsignedByte));
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing AVC codec private");
            }
        }

        private static Pair<List<byte[]>, Integer> parseHevcCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.setPosition(21);
                int unsignedByte = parsableByteArray.readUnsignedByte() & 3;
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                int position = parsableByteArray.getPosition();
                int i = 0;
                for (int i2 = 0; i2 < unsignedByte2; i2++) {
                    parsableByteArray.skipBytes(1);
                    int unsignedShort = parsableByteArray.readUnsignedShort();
                    for (int i3 = 0; i3 < unsignedShort; i3++) {
                        int unsignedShort2 = parsableByteArray.readUnsignedShort();
                        i += unsignedShort2 + 4;
                        parsableByteArray.skipBytes(unsignedShort2);
                    }
                }
                parsableByteArray.setPosition(position);
                byte[] bArr = new byte[i];
                int i4 = 0;
                for (int i5 = 0; i5 < unsignedByte2; i5++) {
                    parsableByteArray.skipBytes(1);
                    int unsignedShort3 = parsableByteArray.readUnsignedShort();
                    for (int i6 = 0; i6 < unsignedShort3; i6++) {
                        int unsignedShort4 = parsableByteArray.readUnsignedShort();
                        System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, bArr, i4, NalUnitUtil.NAL_START_CODE.length);
                        int length = i4 + NalUnitUtil.NAL_START_CODE.length;
                        System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), bArr, length, unsignedShort4);
                        i4 = length + unsignedShort4;
                        parsableByteArray.skipBytes(unsignedShort4);
                    }
                }
                return Pair.create(i == 0 ? null : Collections.singletonList(bArr), Integer.valueOf(unsignedByte + 1));
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing HEVC codec private");
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            try {
                if (bArr[0] != 2) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                int i = 0;
                int i2 = 1;
                while (bArr[i2] == -1) {
                    i += 255;
                    i2++;
                }
                int i3 = i2 + 1;
                int i4 = i + bArr[i2];
                int i5 = 0;
                while (bArr[i3] == -1) {
                    i5 += 255;
                    i3++;
                }
                int i6 = i3 + 1;
                int i7 = i5 + bArr[i3];
                if (bArr[i6] != 1) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] bArr2 = new byte[i4];
                System.arraycopy(bArr, i6, bArr2, 0, i4);
                int i8 = i6 + i4;
                if (bArr[i8] != 3) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                int i9 = i8 + i7;
                if (bArr[i9] != 5) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] bArr3 = new byte[bArr.length - i9];
                System.arraycopy(bArr, i9, bArr3, 0, bArr.length - i9);
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(bArr2);
                arrayList.add(bArr3);
                return arrayList;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int littleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (littleEndianUnsignedShort == 1) {
                    return true;
                }
                if (littleEndianUnsignedShort != 65534) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                return parsableByteArray.readLong() == WebmExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == WebmExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits();
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }
    }
}
