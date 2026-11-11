package androidx.media3.extractor.metadata.id3;

import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public final class Id3Decoder extends SimpleMetadataDecoder {
    private static final int FRAME_FLAG_V3_HAS_GROUP_IDENTIFIER = 32;
    private static final int FRAME_FLAG_V3_IS_COMPRESSED = 128;
    private static final int FRAME_FLAG_V3_IS_ENCRYPTED = 64;
    private static final int FRAME_FLAG_V4_HAS_DATA_LENGTH = 1;
    private static final int FRAME_FLAG_V4_HAS_GROUP_IDENTIFIER = 64;
    private static final int FRAME_FLAG_V4_IS_COMPRESSED = 8;
    private static final int FRAME_FLAG_V4_IS_ENCRYPTED = 4;
    private static final int FRAME_FLAG_V4_IS_UNSYNCHRONIZED = 2;
    public static final int ID3_HEADER_LENGTH = 10;
    public static final int ID3_TAG = 4801587;
    private static final int ID3_TEXT_ENCODING_ISO_8859_1 = 0;
    private static final int ID3_TEXT_ENCODING_UTF_16 = 1;
    private static final int ID3_TEXT_ENCODING_UTF_16BE = 2;
    private static final int ID3_TEXT_ENCODING_UTF_8 = 3;
    public static final FramePredicate NO_FRAMES_PREDICATE = new FramePredicate() { // from class: androidx.media3.extractor.metadata.id3.Id3Decoder$$ExternalSyntheticLambda0
        @Override // androidx.media3.extractor.metadata.id3.Id3Decoder.FramePredicate
        public final boolean evaluate(int i, int i2, int i3, int i4, int i5) {
            return Id3Decoder.lambda$static$0(i, i2, i3, i4, i5);
        }
    };
    private static final String TAG = "Id3Decoder";
    private final FramePredicate framePredicate;

    public interface FramePredicate {
        boolean evaluate(int i, int i2, int i3, int i4, int i5);
    }

    private static int delimiterLength(int i) {
        return (i == 0 || i == 3) ? 1 : 2;
    }

    static /* synthetic */ boolean lambda$static$0(int i, int i2, int i3, int i4, int i5) {
        return false;
    }

    public Id3Decoder() {
        this(null);
    }

    public Id3Decoder(FramePredicate framePredicate) {
        this.framePredicate = framePredicate;
    }

    @Override // androidx.media3.extractor.metadata.SimpleMetadataDecoder
    protected Metadata decode(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer) {
        return decode(byteBuffer.array(), byteBuffer.limit());
    }

    public Metadata decode(byte[] bArr, int i) throws Throwable {
        ArrayList arrayList = new ArrayList();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i);
        Id3Header id3HeaderDecodeHeader = decodeHeader(parsableByteArray);
        if (id3HeaderDecodeHeader == null) {
            return null;
        }
        int position = parsableByteArray.getPosition();
        int i2 = id3HeaderDecodeHeader.majorVersion == 2 ? 6 : 10;
        int iRemoveUnsynchronization = id3HeaderDecodeHeader.framesSize;
        if (id3HeaderDecodeHeader.isUnsynchronized) {
            iRemoveUnsynchronization = removeUnsynchronization(parsableByteArray, id3HeaderDecodeHeader.framesSize);
        }
        parsableByteArray.setLimit(position + iRemoveUnsynchronization);
        boolean z = false;
        if (!validateFrames(parsableByteArray, id3HeaderDecodeHeader.majorVersion, i2, false)) {
            if (id3HeaderDecodeHeader.majorVersion != 4 || !validateFrames(parsableByteArray, 4, i2, true)) {
                Log.w(TAG, "Failed to validate ID3 tag with majorVersion=" + id3HeaderDecodeHeader.majorVersion);
                return null;
            }
            z = true;
        }
        while (parsableByteArray.bytesLeft() >= i2) {
            Id3Frame id3FrameDecodeFrame = decodeFrame(id3HeaderDecodeHeader.majorVersion, parsableByteArray, z, i2, this.framePredicate);
            if (id3FrameDecodeFrame != null) {
                arrayList.add(id3FrameDecodeFrame);
            }
        }
        return new Metadata(arrayList);
    }

    private static Id3Header decodeHeader(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() < 10) {
            Log.w(TAG, "Data too short to be an ID3 tag");
            return null;
        }
        int unsignedInt24 = parsableByteArray.readUnsignedInt24();
        boolean z = false;
        if (unsignedInt24 != 4801587) {
            Log.w(TAG, "Unexpected first three bytes of ID3 tag header: 0x" + String.format("%06X", Integer.valueOf(unsignedInt24)));
            return null;
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        parsableByteArray.skipBytes(1);
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        int synchSafeInt = parsableByteArray.readSynchSafeInt();
        if (unsignedByte == 2) {
            if ((unsignedByte2 & 64) != 0) {
                Log.w(TAG, "Skipped ID3 tag with majorVersion=2 and undefined compression scheme");
                return null;
            }
        } else if (unsignedByte == 3) {
            if ((unsignedByte2 & 64) != 0) {
                int i = parsableByteArray.readInt();
                parsableByteArray.skipBytes(i);
                synchSafeInt -= i + 4;
            }
        } else {
            if (unsignedByte != 4) {
                Log.w(TAG, "Skipped ID3 tag with unsupported majorVersion=" + unsignedByte);
                return null;
            }
            if ((unsignedByte2 & 64) != 0) {
                int synchSafeInt2 = parsableByteArray.readSynchSafeInt();
                parsableByteArray.skipBytes(synchSafeInt2 - 4);
                synchSafeInt -= synchSafeInt2;
            }
            if ((unsignedByte2 & 16) != 0) {
                synchSafeInt -= 10;
            }
        }
        if (unsignedByte < 4 && (unsignedByte2 & 128) != 0) {
            z = true;
        }
        return new Id3Header(unsignedByte, z, synchSafeInt);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0079 A[PHI: r3
      0x0079: PHI (r3v16 int) = (r3v5 int), (r3v19 int) binds: [B:40:0x0086, B:31:0x0076] A[DONT_GENERATE, DONT_INLINE]] */
    private static boolean validateFrames(ParsableByteArray parsableByteArray, int i, int i2, boolean z) {
        int unsignedInt24;
        long unsignedInt25;
        int unsignedShort;
        int i3;
        int position = parsableByteArray.getPosition();
        while (true) {
            try {
                boolean z2 = true;
                if (parsableByteArray.bytesLeft() < i2) {
                    parsableByteArray.setPosition(position);
                    return true;
                }
                if (i >= 3) {
                    unsignedInt24 = parsableByteArray.readInt();
                    unsignedInt25 = parsableByteArray.readUnsignedInt();
                    unsignedShort = parsableByteArray.readUnsignedShort();
                } else {
                    unsignedInt24 = parsableByteArray.readUnsignedInt24();
                    unsignedInt25 = parsableByteArray.readUnsignedInt24();
                    unsignedShort = 0;
                }
                if (unsignedInt24 == 0 && unsignedInt25 == 0 && unsignedShort == 0) {
                    parsableByteArray.setPosition(position);
                    return true;
                }
                if (i == 4 && !z) {
                    if ((8421504 & unsignedInt25) != 0) {
                        parsableByteArray.setPosition(position);
                        return false;
                    }
                    unsignedInt25 = (((unsignedInt25 >> 24) & 255) << 21) | (unsignedInt25 & 255) | (((unsignedInt25 >> 8) & 255) << 7) | (((unsignedInt25 >> 16) & 255) << 14);
                }
                if (i == 4) {
                    i3 = (unsignedShort & 64) != 0 ? 1 : 0;
                    if ((unsignedShort & 1) == 0) {
                        z2 = false;
                    }
                } else if (i == 3) {
                    i3 = (unsignedShort & 32) != 0 ? 1 : 0;
                    if ((unsignedShort & 128) == 0) {
                        z2 = false;
                    }
                } else {
                    i3 = 0;
                    z2 = false;
                }
                if (z2) {
                    i3 += 4;
                }
                if (unsignedInt25 < i3) {
                    parsableByteArray.setPosition(position);
                    return false;
                }
                if (parsableByteArray.bytesLeft() >= unsignedInt25) {
                    parsableByteArray.skipBytes((int) unsignedInt25);
                } else {
                    parsableByteArray.setPosition(position);
                    return false;
                }
            } catch (Throwable th) {
                parsableByteArray.setPosition(position);
                throw th;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:190:0x0239  */
    /* JADX WARN: Instruction removed from duplicated block: B:190:0x0239, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v2, types: [androidx.media3.extractor.metadata.id3.Id3Frame] */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10, types: [androidx.media3.common.util.ParsableByteArray] */
    /* JADX WARN: Type inference failed for: r1v11, types: [androidx.media3.common.util.ParsableByteArray] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [int] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v29, types: [androidx.media3.common.util.ParsableByteArray] */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r6v0, types: [androidx.media3.common.util.ParsableByteArray] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12, types: [int] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14, types: [int] */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v9 */
    private static Id3Frame decodeFrame(int i, ParsableByteArray parsableByteArray, boolean z, int i2, FramePredicate framePredicate) throws Throwable {
        int unsignedInt24;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        Throwable th;
        int i3;
        ?? r12;
        ?? r8;
        Id3Frame id3FrameDecodeBinaryFrame;
        int i4 = i;
        ?? r6 = parsableByteArray;
        int unsignedByte = r6.readUnsignedByte();
        int unsignedByte2 = r6.readUnsignedByte();
        int unsignedByte3 = r6.readUnsignedByte();
        boolean z6 = false;
        int unsignedByte4 = i4 >= 3 ? r6.readUnsignedByte() : 0;
        if (i4 == 4) {
            unsignedInt24 = r6.readUnsignedIntToInt();
            if (!z) {
                unsignedInt24 = (((unsignedInt24 >> 24) & 255) << 21) | (unsignedInt24 & 255) | (((unsignedInt24 >> 8) & 255) << 7) | (((unsignedInt24 >> 16) & 255) << 14);
            }
        } else if (i4 == 3) {
            unsignedInt24 = r6.readUnsignedIntToInt();
        } else {
            unsignedInt24 = r6.readUnsignedInt24();
        }
        int iRemoveUnsynchronization = unsignedInt24;
        int unsignedShort = i4 >= 3 ? r6.readUnsignedShort() : 0;
        if (unsignedByte == 0 && unsignedByte2 == 0 && unsignedByte3 == 0 && unsignedByte4 == 0 && iRemoveUnsynchronization == 0 && unsignedShort == 0) {
            r6.setPosition(r6.limit());
            return null;
        }
        int position = r6.getPosition() + iRemoveUnsynchronization;
        if (position > r6.limit()) {
            Log.w(TAG, "Frame size exceeds remaining tag data");
            r6.setPosition(r6.limit());
            return null;
        }
        if (framePredicate != null) {
            boolean zEvaluate = framePredicate.evaluate(i4, unsignedByte, unsignedByte2, unsignedByte3, unsignedByte4);
            r6 = unsignedByte;
            iRemoveUnsynchronization = unsignedByte2;
            if (!zEvaluate) {
                i4 = i4;
                r6.setPosition(position);
                return null;
            }
        } else {
            iRemoveUnsynchronization = unsignedByte2;
            r6 = unsignedByte;
        }
        i4 = i4;
        if (i4 == 3) {
            z2 = (unsignedShort & 128) != 0;
            z4 = (unsignedShort & 64) != 0;
            z3 = (unsignedShort & 32) != 0;
            z5 = false;
            z6 = z2;
        } else if (i4 == 4) {
            boolean z7 = (unsignedShort & 64) != 0;
            boolean z8 = (unsignedShort & 8) != 0;
            boolean z9 = (unsignedShort & 4) != 0;
            z5 = (unsignedShort & 2) != 0;
            z6 = (unsignedShort & 1) != 0;
            z3 = z7;
            z2 = z6;
            z6 = z8;
            z4 = z9;
        } else {
            z2 = false;
            z3 = false;
            z4 = false;
            z5 = false;
        }
        if (z6 || z4) {
            Log.w(TAG, "Skipping unsupported compressed or encrypted frame");
            r6.setPosition(position);
            return null;
        }
        if (z3) {
            iRemoveUnsynchronization--;
            r6.skipBytes(1);
        }
        if (z2) {
            iRemoveUnsynchronization -= 4;
            r6.skipBytes(4);
        }
        if (z5) {
            iRemoveUnsynchronization = removeUnsynchronization(r6, iRemoveUnsynchronization);
        }
        try {
            try {
                if (r6 == 84 && iRemoveUnsynchronization == 88 && unsignedByte3 == 88 && (i4 == 2 || unsignedByte4 == 88)) {
                    id3FrameDecodeBinaryFrame = decodeTxxxFrame(r6, iRemoveUnsynchronization);
                } else if (r6 == 84) {
                    id3FrameDecodeBinaryFrame = decodeTextInformationFrame(r6, iRemoveUnsynchronization, getFrameId(i4, r6, iRemoveUnsynchronization, unsignedByte3, unsignedByte4));
                } else if (r6 == 87 && iRemoveUnsynchronization == 88 && unsignedByte3 == 88 && (i4 == 2 || unsignedByte4 == 88)) {
                    id3FrameDecodeBinaryFrame = decodeWxxxFrame(r6, iRemoveUnsynchronization);
                } else if (r6 == 87) {
                    id3FrameDecodeBinaryFrame = decodeUrlLinkFrame(r6, iRemoveUnsynchronization, getFrameId(i4, r6, iRemoveUnsynchronization, unsignedByte3, unsignedByte4));
                } else if (r6 == 80 && iRemoveUnsynchronization == 82 && unsignedByte3 == 73 && unsignedByte4 == 86) {
                    id3FrameDecodeBinaryFrame = decodePrivFrame(r6, iRemoveUnsynchronization);
                } else {
                    if (r6 == 71 && iRemoveUnsynchronization == 69 && unsignedByte3 == 79 && (unsignedByte4 == 66 || i4 == 2)) {
                        id3FrameDecodeBinaryFrame = decodeGeobFrame(r6, iRemoveUnsynchronization);
                    } else {
                        th = null;
                        try {
                            if (i4 != 2 ? r6 == 65 && iRemoveUnsynchronization == 80 && unsignedByte3 == 73 && unsignedByte4 == 67 : r6 == 80 && iRemoveUnsynchronization == 73 && unsignedByte3 == 67) {
                                id3FrameDecodeBinaryFrame = decodeApicFrame(r6, iRemoveUnsynchronization, i4);
                            } else {
                                if (r6 != 67 || iRemoveUnsynchronization != 79 || unsignedByte3 != 77 || (unsignedByte4 != 77 && i4 != 2)) {
                                    if (r6 == 67 && iRemoveUnsynchronization == 72 && unsignedByte3 == 65 && unsignedByte4 == 80) {
                                        r6 = r6;
                                        iRemoveUnsynchronization = iRemoveUnsynchronization;
                                        unsignedByte4 = unsignedByte4;
                                        iRemoveUnsynchronization = iRemoveUnsynchronization;
                                        i3 = unsignedByte3;
                                        try {
                                            id3FrameDecodeBinaryFrame = decodeChapterFrame(r6, iRemoveUnsynchronization, i4, z, i2, framePredicate);
                                            i4 = i;
                                            r6 = parsableByteArray;
                                        } catch (Exception e) {
                                            e = e;
                                            i4 = i;
                                            r6 = parsableByteArray;
                                            r6.setPosition(position);
                                            r12 = th;
                                            r8 = r6;
                                        } catch (OutOfMemoryError e2) {
                                            e = e2;
                                            i4 = i;
                                            r6 = parsableByteArray;
                                            r6.setPosition(position);
                                            r12 = th;
                                            r8 = r6;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            r6 = parsableByteArray;
                                            r6.setPosition(position);
                                            throw th;
                                        }
                                    } else {
                                        r6 = r6;
                                        iRemoveUnsynchronization = iRemoveUnsynchronization;
                                        unsignedByte4 = unsignedByte4;
                                        iRemoveUnsynchronization = iRemoveUnsynchronization;
                                        i3 = unsignedByte3;
                                        try {
                                            if (r6 == 67 && iRemoveUnsynchronization == 84 && i3 == 79 && unsignedByte4 == 67) {
                                                i4 = i;
                                                ParsableByteArray parsableByteArray2 = parsableByteArray;
                                                id3FrameDecodeBinaryFrame = decodeChapterTOCFrame(parsableByteArray2, iRemoveUnsynchronization, i4, z, i2, framePredicate);
                                                r6 = parsableByteArray2;
                                            } else {
                                                i4 = i;
                                                ParsableByteArray parsableByteArray3 = parsableByteArray;
                                                if (r6 == 77 && iRemoveUnsynchronization == 76 && i3 == 76 && unsignedByte4 == 84) {
                                                    id3FrameDecodeBinaryFrame = decodeMlltFrame(parsableByteArray3, iRemoveUnsynchronization);
                                                    r6 = parsableByteArray3;
                                                } else {
                                                    id3FrameDecodeBinaryFrame = decodeBinaryFrame(parsableByteArray3, iRemoveUnsynchronization, getFrameId(i4, r6, iRemoveUnsynchronization, i3, unsignedByte4));
                                                    r6 = parsableByteArray3;
                                                }
                                            }
                                        } catch (Exception e3) {
                                            e = e3;
                                            r6.setPosition(position);
                                            r12 = th;
                                            r8 = r6;
                                        } catch (OutOfMemoryError e4) {
                                            e = e4;
                                            r6.setPosition(position);
                                            r12 = th;
                                            r8 = r6;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            r6.setPosition(position);
                                            throw th;
                                        }
                                    }
                                    if (r12 == 0) {
                                        Log.w(TAG, "Failed to decode frame: id=" + getFrameId(i4, r8, iRemoveUnsynchronization, i3, unsignedByte4) + ", frameSize=" + iRemoveUnsynchronization, e);
                                    }
                                    return r12;
                                }
                                id3FrameDecodeBinaryFrame = decodeCommentFrame(r6, iRemoveUnsynchronization);
                            }
                            r6 = r6;
                            iRemoveUnsynchronization = iRemoveUnsynchronization;
                            r6 = r6;
                            i3 = unsignedByte3;
                        } catch (Exception e5) {
                            e = e5;
                            r6 = r6;
                            iRemoveUnsynchronization = iRemoveUnsynchronization;
                            i3 = unsignedByte3;
                            r6.setPosition(position);
                            r12 = th;
                            r8 = r6;
                            if (r12 == 0) {
                                Log.w(TAG, "Failed to decode frame: id=" + getFrameId(i4, r8, iRemoveUnsynchronization, i3, unsignedByte4) + ", frameSize=" + iRemoveUnsynchronization, e);
                            }
                            return r12;
                        } catch (OutOfMemoryError e6) {
                            e = e6;
                            r6 = r6;
                            iRemoveUnsynchronization = iRemoveUnsynchronization;
                            i3 = unsignedByte3;
                            r6.setPosition(position);
                            r12 = th;
                            r8 = r6;
                            if (r12 == 0) {
                                Log.w(TAG, "Failed to decode frame: id=" + getFrameId(i4, r8, iRemoveUnsynchronization, i3, unsignedByte4) + ", frameSize=" + iRemoveUnsynchronization, e);
                            }
                            return r12;
                        }
                    }
                    r6.setPosition(position);
                    r12 = id3FrameDecodeBinaryFrame;
                    e = th;
                    r8 = r6;
                    if (r12 == 0) {
                        Log.w(TAG, "Failed to decode frame: id=" + getFrameId(i4, r8, iRemoveUnsynchronization, i3, unsignedByte4) + ", frameSize=" + iRemoveUnsynchronization, e);
                    }
                    return r12;
                }
                r6 = r6;
                iRemoveUnsynchronization = iRemoveUnsynchronization;
                th = null;
                r6 = r6;
                i3 = unsignedByte3;
                r6.setPosition(position);
                r12 = id3FrameDecodeBinaryFrame;
                e = th;
                r8 = r6;
            } catch (Throwable th4) {
                th = th4;
                r6 = r6;
            }
        } catch (Exception e7) {
            e = e7;
            r6 = r6;
            iRemoveUnsynchronization = iRemoveUnsynchronization;
            th = null;
            i3 = unsignedByte3;
            r6.setPosition(position);
            r12 = th;
            r8 = r6;
            if (r12 == 0) {
                Log.w(TAG, "Failed to decode frame: id=" + getFrameId(i4, r8, iRemoveUnsynchronization, i3, unsignedByte4) + ", frameSize=" + iRemoveUnsynchronization, e);
            }
            return r12;
        } catch (OutOfMemoryError e8) {
            e = e8;
            r6 = r6;
            iRemoveUnsynchronization = iRemoveUnsynchronization;
            th = null;
            i3 = unsignedByte3;
            r6.setPosition(position);
            r12 = th;
            r8 = r6;
            if (r12 == 0) {
                Log.w(TAG, "Failed to decode frame: id=" + getFrameId(i4, r8, iRemoveUnsynchronization, i3, unsignedByte4) + ", frameSize=" + iRemoveUnsynchronization, e);
            }
            return r12;
        }
        if (r12 == 0) {
            Log.w(TAG, "Failed to decode frame: id=" + getFrameId(i4, r8, iRemoveUnsynchronization, i3, unsignedByte4) + ", frameSize=" + iRemoveUnsynchronization, e);
        }
        return r12;
    }

    private static TextInformationFrame decodeTxxxFrame(ParsableByteArray parsableByteArray, int i) {
        if (i < 1) {
            return null;
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int iIndexOfTerminator = indexOfTerminator(bArr, 0, unsignedByte);
        return new TextInformationFrame("TXXX", new String(bArr, 0, iIndexOfTerminator, getCharset(unsignedByte)), decodeTextInformationFrameValues(bArr, unsignedByte, iIndexOfTerminator + delimiterLength(unsignedByte)));
    }

    private static TextInformationFrame decodeTextInformationFrame(ParsableByteArray parsableByteArray, int i, String str) {
        if (i < 1) {
            return null;
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        return new TextInformationFrame(str, (String) null, decodeTextInformationFrameValues(bArr, unsignedByte, 0));
    }

    private static ImmutableList<String> decodeTextInformationFrameValues(byte[] bArr, int i, int i2) {
        if (i2 >= bArr.length) {
            return ImmutableList.of("");
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        int iIndexOfTerminator = indexOfTerminator(bArr, i2, i);
        while (i2 < iIndexOfTerminator) {
            builder.add(new String(bArr, i2, iIndexOfTerminator - i2, getCharset(i)));
            i2 = delimiterLength(i) + iIndexOfTerminator;
            iIndexOfTerminator = indexOfTerminator(bArr, i2, i);
        }
        ImmutableList<String> immutableListBuild = builder.build();
        return immutableListBuild.isEmpty() ? ImmutableList.of("") : immutableListBuild;
    }

    private static UrlLinkFrame decodeWxxxFrame(ParsableByteArray parsableByteArray, int i) {
        if (i < 1) {
            return null;
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int iIndexOfTerminator = indexOfTerminator(bArr, 0, unsignedByte);
        String str = new String(bArr, 0, iIndexOfTerminator, getCharset(unsignedByte));
        int iDelimiterLength = iIndexOfTerminator + delimiterLength(unsignedByte);
        return new UrlLinkFrame("WXXX", str, decodeStringIfValid(bArr, iDelimiterLength, indexOfZeroByte(bArr, iDelimiterLength), StandardCharsets.ISO_8859_1));
    }

    private static UrlLinkFrame decodeUrlLinkFrame(ParsableByteArray parsableByteArray, int i, String str) {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        return new UrlLinkFrame(str, null, new String(bArr, 0, indexOfZeroByte(bArr, 0), StandardCharsets.ISO_8859_1));
    }

    private static PrivFrame decodePrivFrame(ParsableByteArray parsableByteArray, int i) {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        int iIndexOfZeroByte = indexOfZeroByte(bArr, 0);
        return new PrivFrame(new String(bArr, 0, iIndexOfZeroByte, StandardCharsets.ISO_8859_1), copyOfRangeIfValid(bArr, iIndexOfZeroByte + 1, i));
    }

    private static GeobFrame decodeGeobFrame(ParsableByteArray parsableByteArray, int i) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        Charset charset = getCharset(unsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int iIndexOfZeroByte = indexOfZeroByte(bArr, 0);
        String strNormalizeMimeType = MimeTypes.normalizeMimeType(new String(bArr, 0, iIndexOfZeroByte, StandardCharsets.ISO_8859_1));
        int i3 = iIndexOfZeroByte + 1;
        int iIndexOfTerminator = indexOfTerminator(bArr, i3, unsignedByte);
        String strDecodeStringIfValid = decodeStringIfValid(bArr, i3, iIndexOfTerminator, charset);
        int iDelimiterLength = iIndexOfTerminator + delimiterLength(unsignedByte);
        int iIndexOfTerminator2 = indexOfTerminator(bArr, iDelimiterLength, unsignedByte);
        return new GeobFrame(strNormalizeMimeType, strDecodeStringIfValid, decodeStringIfValid(bArr, iDelimiterLength, iIndexOfTerminator2, charset), copyOfRangeIfValid(bArr, iIndexOfTerminator2 + delimiterLength(unsignedByte), i2));
    }

    private static ApicFrame decodeApicFrame(ParsableByteArray parsableByteArray, int i, int i2) {
        int iIndexOfZeroByte;
        String str;
        int unsignedByte = parsableByteArray.readUnsignedByte();
        Charset charset = getCharset(unsignedByte);
        int i3 = i - 1;
        byte[] bArr = new byte[i3];
        parsableByteArray.readBytes(bArr, 0, i3);
        if (i2 == 2) {
            str = "image/" + Ascii.toLowerCase(new String(bArr, 0, 3, StandardCharsets.ISO_8859_1));
            if ("image/jpg".equals(str)) {
                str = "image/jpeg";
            }
            iIndexOfZeroByte = 2;
        } else {
            iIndexOfZeroByte = indexOfZeroByte(bArr, 0);
            String lowerCase = Ascii.toLowerCase(new String(bArr, 0, iIndexOfZeroByte, StandardCharsets.ISO_8859_1));
            if (lowerCase.indexOf(47) == -1) {
                str = "image/" + lowerCase;
            } else {
                str = lowerCase;
            }
        }
        int i4 = bArr[iIndexOfZeroByte + 1] & 255;
        int i5 = iIndexOfZeroByte + 2;
        int iIndexOfTerminator = indexOfTerminator(bArr, i5, unsignedByte);
        return new ApicFrame(str, new String(bArr, i5, iIndexOfTerminator - i5, charset), i4, copyOfRangeIfValid(bArr, iIndexOfTerminator + delimiterLength(unsignedByte), i3));
    }

    private static CommentFrame decodeCommentFrame(ParsableByteArray parsableByteArray, int i) {
        if (i < 4) {
            return null;
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        Charset charset = getCharset(unsignedByte);
        byte[] bArr = new byte[3];
        parsableByteArray.readBytes(bArr, 0, 3);
        String str = new String(bArr, 0, 3);
        int i2 = i - 4;
        byte[] bArr2 = new byte[i2];
        parsableByteArray.readBytes(bArr2, 0, i2);
        int iIndexOfTerminator = indexOfTerminator(bArr2, 0, unsignedByte);
        String str2 = new String(bArr2, 0, iIndexOfTerminator, charset);
        int iDelimiterLength = iIndexOfTerminator + delimiterLength(unsignedByte);
        return new CommentFrame(str, str2, decodeStringIfValid(bArr2, iDelimiterLength, indexOfTerminator(bArr2, iDelimiterLength, unsignedByte), charset));
    }

    private static ChapterFrame decodeChapterFrame(ParsableByteArray parsableByteArray, int i, int i2, boolean z, int i3, FramePredicate framePredicate) throws Throwable {
        int position = parsableByteArray.getPosition();
        int iIndexOfZeroByte = indexOfZeroByte(parsableByteArray.getData(), position);
        String str = new String(parsableByteArray.getData(), position, iIndexOfZeroByte - position, StandardCharsets.ISO_8859_1);
        parsableByteArray.setPosition(iIndexOfZeroByte + 1);
        int i4 = parsableByteArray.readInt();
        int i5 = parsableByteArray.readInt();
        long unsignedInt = parsableByteArray.readUnsignedInt();
        if (unsignedInt == 4294967295L) {
            unsignedInt = -1;
        }
        long unsignedInt2 = parsableByteArray.readUnsignedInt();
        long j = unsignedInt2 == 4294967295L ? -1L : unsignedInt2;
        ArrayList arrayList = new ArrayList();
        int i6 = position + i;
        while (parsableByteArray.getPosition() < i6) {
            Id3Frame id3FrameDecodeFrame = decodeFrame(i2, parsableByteArray, z, i3, framePredicate);
            if (id3FrameDecodeFrame != null) {
                arrayList.add(id3FrameDecodeFrame);
            }
        }
        return new ChapterFrame(str, i4, i5, unsignedInt, j, (Id3Frame[]) arrayList.toArray(new Id3Frame[0]));
    }

    private static ChapterTocFrame decodeChapterTOCFrame(ParsableByteArray parsableByteArray, int i, int i2, boolean z, int i3, FramePredicate framePredicate) throws Throwable {
        int position = parsableByteArray.getPosition();
        int iIndexOfZeroByte = indexOfZeroByte(parsableByteArray.getData(), position);
        String str = new String(parsableByteArray.getData(), position, iIndexOfZeroByte - position, StandardCharsets.ISO_8859_1);
        parsableByteArray.setPosition(iIndexOfZeroByte + 1);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        boolean z2 = (unsignedByte & 2) != 0;
        boolean z3 = (unsignedByte & 1) != 0;
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        String[] strArr = new String[unsignedByte2];
        for (int i4 = 0; i4 < unsignedByte2; i4++) {
            int position2 = parsableByteArray.getPosition();
            int iIndexOfZeroByte2 = indexOfZeroByte(parsableByteArray.getData(), position2);
            strArr[i4] = new String(parsableByteArray.getData(), position2, iIndexOfZeroByte2 - position2, StandardCharsets.ISO_8859_1);
            parsableByteArray.setPosition(iIndexOfZeroByte2 + 1);
        }
        ArrayList arrayList = new ArrayList();
        int i5 = position + i;
        while (parsableByteArray.getPosition() < i5) {
            Id3Frame id3FrameDecodeFrame = decodeFrame(i2, parsableByteArray, z, i3, framePredicate);
            if (id3FrameDecodeFrame != null) {
                arrayList.add(id3FrameDecodeFrame);
            }
        }
        return new ChapterTocFrame(str, z2, z3, strArr, (Id3Frame[]) arrayList.toArray(new Id3Frame[0]));
    }

    private static MlltFrame decodeMlltFrame(ParsableByteArray parsableByteArray, int i) {
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedInt24 = parsableByteArray.readUnsignedInt24();
        int unsignedInt25 = parsableByteArray.readUnsignedInt24();
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        ParsableBitArray parsableBitArray = new ParsableBitArray();
        parsableBitArray.reset(parsableByteArray);
        int i2 = ((i - 10) * 8) / (unsignedByte + unsignedByte2);
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int bits = parsableBitArray.readBits(unsignedByte);
            int bits2 = parsableBitArray.readBits(unsignedByte2);
            iArr[i3] = bits;
            iArr2[i3] = bits2;
        }
        return new MlltFrame(unsignedShort, unsignedInt24, unsignedInt25, iArr, iArr2);
    }

    private static BinaryFrame decodeBinaryFrame(ParsableByteArray parsableByteArray, int i, String str) {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        return new BinaryFrame(str, bArr);
    }

    private static int removeUnsynchronization(ParsableByteArray parsableByteArray, int i) {
        byte[] data = parsableByteArray.getData();
        int position = parsableByteArray.getPosition();
        int i2 = position;
        while (true) {
            int i3 = i2 + 1;
            if (i3 >= position + i) {
                return i;
            }
            if ((data[i2] & 255) == 255 && data[i3] == 0) {
                System.arraycopy(data, i2 + 2, data, i3, (i - (i2 - position)) - 2);
                i--;
            }
            i2 = i3;
        }
    }

    private static Charset getCharset(int i) {
        if (i == 1) {
            return StandardCharsets.UTF_16;
        }
        if (i == 2) {
            return StandardCharsets.UTF_16BE;
        }
        if (i == 3) {
            return StandardCharsets.UTF_8;
        }
        return StandardCharsets.ISO_8859_1;
    }

    private static String getFrameId(int i, int i2, int i3, int i4, int i5) {
        return i == 2 ? String.format(Locale.US, "%c%c%c", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)) : String.format(Locale.US, "%c%c%c%c", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
    }

    private static int indexOfTerminator(byte[] bArr, int i, int i2) {
        int iIndexOfZeroByte = indexOfZeroByte(bArr, i);
        if (i2 == 0 || i2 == 3) {
            return iIndexOfZeroByte;
        }
        while (iIndexOfZeroByte < bArr.length - 1) {
            if ((iIndexOfZeroByte - i) % 2 == 0 && bArr[iIndexOfZeroByte + 1] == 0) {
                return iIndexOfZeroByte;
            }
            iIndexOfZeroByte = indexOfZeroByte(bArr, iIndexOfZeroByte + 1);
        }
        return bArr.length;
    }

    private static int indexOfZeroByte(byte[] bArr, int i) {
        while (i < bArr.length) {
            if (bArr[i] == 0) {
                return i;
            }
            i++;
        }
        return bArr.length;
    }

    private static byte[] copyOfRangeIfValid(byte[] bArr, int i, int i2) {
        if (i2 <= i) {
            return Util.EMPTY_BYTE_ARRAY;
        }
        return Arrays.copyOfRange(bArr, i, i2);
    }

    private static String decodeStringIfValid(byte[] bArr, int i, int i2, Charset charset) {
        if (i2 <= i || i2 > bArr.length) {
            return "";
        }
        return new String(bArr, i, i2 - i, charset);
    }

    private static final class Id3Header {
        private final int framesSize;
        private final boolean isUnsynchronized;
        private final int majorVersion;

        public Id3Header(int i, boolean z, int i2) {
            this.majorVersion = i;
            this.isUnsynchronized = z;
            this.framesSize = i2;
        }
    }
}
