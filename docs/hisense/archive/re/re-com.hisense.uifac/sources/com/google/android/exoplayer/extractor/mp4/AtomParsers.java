package com.google.android.exoplayer.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.GaplessInfo;
import com.google.android.exoplayer.util.Ac3Util;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.CodecSpecificDataUtil;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class AtomParsers {
    private static final int TYPE_cenc = Util.getIntegerCodeForString("cenc");
    private static byte[] psshKid;
    private static int psshKidLen;

    private interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    public static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j, boolean z) {
        Atom.ContainerAtom containerAtomOfType = containerAtom.getContainerAtomOfType(Atom.TYPE_mdia);
        int hdlr = parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        if (hdlr != Track.TYPE_soun && hdlr != Track.TYPE_vide && hdlr != Track.TYPE_text && hdlr != Track.TYPE_sbtl && hdlr != Track.TYPE_subt && hdlr != Track.TYPE_meta) {
            return null;
        }
        TkhdData tkhd = parseTkhd(containerAtom.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        long j2 = j == -1 ? tkhd.duration : j;
        long mvhd = parseMvhd(leafAtom.data);
        long jScaleLargeTimestamp = j2 != -1 ? Util.scaleLargeTimestamp(j2, 1000000L, mvhd) : -1L;
        Atom.ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair<Long, String> mdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData stsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, tkhd.id, jScaleLargeTimestamp, tkhd.rotationDegrees, (String) mdhd.second, z);
        Pair<long[], long[]> edts = parseEdts(containerAtom.getContainerAtomOfType(Atom.TYPE_edts));
        if (stsd.mediaFormat == null) {
            return null;
        }
        return new Track(tkhd.id, hdlr, ((Long) mdhd.first).longValue(), mvhd, jScaleLargeTimestamp, stsd.mediaFormat, stsd.trackEncryptionBoxes, stsd.nalUnitLengthFieldLength, (long[]) edts.first, (long[]) edts.second);
    }

    /* JADX WARN: Code duplicated, block: B:103:0x0242  */
    /* JADX WARN: Code duplicated, block: B:105:0x025a  */
    /* JADX WARN: Code duplicated, block: B:107:0x025e  */
    /* JADX WARN: Code duplicated, block: B:109:0x0266  */
    /* JADX WARN: Code duplicated, block: B:112:0x026b A[LOOP:4: B:110:0x0268->B:112:0x026b, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:118:0x02a0  */
    /* JADX WARN: Code duplicated, block: B:120:0x02a8  */
    /* JADX WARN: Code duplicated, block: B:122:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:123:0x02c8  */
    /* JADX WARN: Code duplicated, block: B:128:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:129:0x02d4  */
    /* JADX WARN: Code duplicated, block: B:132:0x02d8  */
    /* JADX WARN: Code duplicated, block: B:133:0x02db  */
    /* JADX WARN: Code duplicated, block: B:135:0x02de  */
    /* JADX WARN: Code duplicated, block: B:136:0x02e1  */
    /* JADX WARN: Code duplicated, block: B:138:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:140:0x02e8  */
    /* JADX WARN: Code duplicated, block: B:141:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:145:0x02f7  */
    /* JADX WARN: Code duplicated, block: B:147:0x0305  */
    /* JADX WARN: Code duplicated, block: B:149:0x0326  */
    /* JADX WARN: Code duplicated, block: B:150:0x0338  */
    /* JADX WARN: Code duplicated, block: B:153:0x0342  */
    /* JADX WARN: Code duplicated, block: B:160:0x0387  */
    /* JADX WARN: Code duplicated, block: B:168:0x03b6  */
    /* JADX WARN: Code duplicated, block: B:169:0x03b8  */
    /* JADX WARN: Code duplicated, block: B:172:0x03bf  */
    /* JADX WARN: Code duplicated, block: B:174:0x03cf  */
    /* JADX WARN: Code duplicated, block: B:188:0x02ce A[EDGE_INSN: B:188:0x02ce->B:126:0x02ce BREAK  A[LOOP:5: B:116:0x0299->B:125:0x02cb], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:190:0x02cb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:191:0x03a7 A[EDGE_INSN: B:191:0x03a7->B:162:0x03a7 BREAK  A[LOOP:6: B:143:0x02f2->B:161:0x0396], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:46:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:48:0x00ed A[ADDED_TO_REGION, LOOP:1: B:48:0x00ed->B:49:0x00ef, LOOP_START, PHI: r13 r14 r21 r24
      0x00ed: PHI (r13v10 int) = (r13v9 int), (r13v18 int) binds: [B:47:0x00eb, B:49:0x00ef] A[DONT_GENERATE, DONT_INLINE]
      0x00ed: PHI (r14v13 int) = (r14v12 int), (r14v16 int) binds: [B:47:0x00eb, B:49:0x00ef] A[DONT_GENERATE, DONT_INLINE]
      0x00ed: PHI (r21v6 int) = (r21v5 int), (r21v9 int) binds: [B:47:0x00eb, B:49:0x00ef] A[DONT_GENERATE, DONT_INLINE]
      0x00ed: PHI (r24v8 long) = (r24v7 long), (r24v10 long) binds: [B:47:0x00eb, B:49:0x00ef] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:49:0x00ef A[LOOP:1: B:48:0x00ed->B:49:0x00ef, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:52:0x010f A[ADDED_TO_REGION, LOOP:2: B:52:0x010f->B:54:0x0113, LOOP_START, PHI: r13 r20 r23
      0x010f: PHI (r13v14 int) = (r13v11 int), (r13v15 int) binds: [B:51:0x010d, B:54:0x0113] A[DONT_GENERATE, DONT_INLINE]
      0x010f: PHI (r20v13 int) = (r20v11 int), (r20v15 int) binds: [B:51:0x010d, B:54:0x0113] A[DONT_GENERATE, DONT_INLINE]
      0x010f: PHI (r23v10 int) = (r23v4 int), (r23v11 int) binds: [B:51:0x010d, B:54:0x0113] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:58:0x0130  */
    /* JADX WARN: Code duplicated, block: B:59:0x0136  */
    /* JADX WARN: Code duplicated, block: B:62:0x0140  */
    /* JADX WARN: Code duplicated, block: B:63:0x0142  */
    /* JADX WARN: Code duplicated, block: B:66:0x0147  */
    /* JADX WARN: Code duplicated, block: B:68:0x014e  */
    /* JADX WARN: Code duplicated, block: B:73:0x016d  */
    /* JADX WARN: Code duplicated, block: B:77:0x019f  */
    /* JADX WARN: Code duplicated, block: B:78:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:81:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:83:0x01af  */
    /* JADX WARN: Code duplicated, block: B:84:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:93:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:96:0x020e  */
    /* JADX WARN: Code duplicated, block: B:99:0x021e A[LOOP:9: B:97:0x0218->B:99:0x021e, LOOP_END] */
    public static TrackSampleTable parseStbl(Track track, Atom.ContainerAtom containerAtom) throws ParserException {
        SampleSizeBox stz2SampleSizeBox;
        boolean z;
        int unsignedIntToInt;
        int unsignedIntToInt2;
        boolean z2;
        long j;
        int i;
        Track track2;
        long[] jArr;
        int[] iArr;
        long[] jArr2;
        int[] iArr2;
        int i2;
        long[] jArr3;
        int[] iArr3;
        int i3;
        long[] jArr4;
        int i4;
        boolean z3;
        int i5;
        int i6;
        long[] jArr5;
        long j2;
        boolean z4;
        boolean z5;
        long[] jArr6;
        int[] iArr4;
        int[] iArr5;
        long[] jArr7;
        int i7;
        int i8;
        int i9;
        long[] jArr8;
        int[] iArr6;
        int[] iArr7;
        int i10;
        boolean z6;
        boolean z7;
        int[] iArr8;
        int[] iArr9;
        long j3;
        long j4;
        long[] jArr9;
        long[] jArr10;
        int[] iArr10;
        int[] iArr11;
        int[] iArr12;
        int[] iArr13;
        int[] iArr14;
        int iBinarySearchCeil;
        int iBinarySearchCeil2;
        long j5;
        int iBinarySearchCeil3;
        boolean z8;
        char c;
        int i11;
        long[] jArr11;
        long[] jArr12;
        int[] iArr15;
        long j6;
        long j7;
        int i12;
        int unsignedIntToInt3;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        boolean z9;
        int i19;
        int i20;
        boolean z10;
        int i21;
        int i22;
        int i23;
        int i24;
        int unsignedIntToInt4;
        int unsignedIntToInt5;
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_stsz);
        if (leafAtomOfType != null) {
            stz2SampleSizeBox = new StszSampleSizeBox(leafAtomOfType);
        } else {
            Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_stz2);
            if (leafAtomOfType2 == null) {
                throw new ParserException("Track has no sample table size information");
            }
            stz2SampleSizeBox = new Stz2SampleSizeBox(leafAtomOfType2);
        }
        int sampleCount = stz2SampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            return new TrackSampleTable(new long[0], new int[0], 0, new long[0], new int[0]);
        }
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_stco);
        if (leafAtomOfType3 == null) {
            leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_co64);
            z = true;
        } else {
            z = false;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType3.data;
        ParsableByteArray parsableByteArray2 = containerAtom.getLeafAtomOfType(Atom.TYPE_stsc).data;
        ParsableByteArray parsableByteArray3 = containerAtom.getLeafAtomOfType(Atom.TYPE_stts).data;
        Atom.LeafAtom leafAtomOfType4 = containerAtom.getLeafAtomOfType(Atom.TYPE_stss);
        ParsableByteArray parsableByteArray4 = null;
        ParsableByteArray parsableByteArray5 = leafAtomOfType4 != null ? leafAtomOfType4.data : null;
        Atom.LeafAtom leafAtomOfType5 = containerAtom.getLeafAtomOfType(Atom.TYPE_ctts);
        ParsableByteArray parsableByteArray6 = leafAtomOfType5 != null ? leafAtomOfType5.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(parsableByteArray2, parsableByteArray, z);
        parsableByteArray3.setPosition(12);
        int unsignedIntToInt6 = parsableByteArray3.readUnsignedIntToInt() - 1;
        int unsignedIntToInt7 = parsableByteArray3.readUnsignedIntToInt();
        int unsignedIntToInt8 = parsableByteArray3.readUnsignedIntToInt();
        if (parsableByteArray6 != null) {
            parsableByteArray6.setPosition(12);
            unsignedIntToInt = parsableByteArray6.readUnsignedIntToInt();
        } else {
            unsignedIntToInt = 0;
        }
        int unsignedIntToInt9 = -1;
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(12);
            unsignedIntToInt2 = parsableByteArray5.readUnsignedIntToInt();
            unsignedIntToInt9 = unsignedIntToInt2 > 0 ? parsableByteArray5.readUnsignedIntToInt() - 1 : -1;
            if (!stz2SampleSizeBox.isFixedSampleSize() && "audio/raw".equals(track.mediaFormat.mimeType) && unsignedIntToInt6 == 0 && unsignedIntToInt == 0 && unsignedIntToInt2 == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            j = 0;
            if (!z2) {
                jArr11 = new long[sampleCount];
                iArr2 = new int[sampleCount];
                jArr12 = new long[sampleCount];
                int i25 = unsignedIntToInt2;
                iArr15 = new int[sampleCount];
                j6 = 0;
                j7 = 0;
                i12 = 0;
                i2 = 0;
                unsignedIntToInt3 = 0;
                i13 = 0;
                i14 = 0;
                i15 = i25;
                i16 = unsignedIntToInt;
                i17 = unsignedIntToInt8;
                i18 = unsignedIntToInt7;
                int i26 = unsignedIntToInt6;
                while (i12 < sampleCount) {
                    while (i13 == 0) {
                        Assertions.checkState(chunkIterator.moveNext());
                        j6 = chunkIterator.offset;
                        i13 = chunkIterator.numSamples;
                        i18 = i18;
                        i17 = i17;
                    }
                    int i27 = i18;
                    i21 = i17;
                    i22 = i16;
                    if (parsableByteArray6 != null) {
                        while (unsignedIntToInt3 == 0 && i22 > 0) {
                            unsignedIntToInt3 = parsableByteArray6.readUnsignedIntToInt();
                            i14 = parsableByteArray6.readInt();
                            i22--;
                        }
                        unsignedIntToInt3--;
                    }
                    int i28 = i14;
                    jArr11[i12] = j6;
                    iArr2[i12] = stz2SampleSizeBox.readNextSampleSize();
                    long[] jArr13 = jArr11;
                    if (iArr2[i12] > i2) {
                        i2 = iArr2[i12];
                    }
                    SampleSizeBox sampleSizeBox = stz2SampleSizeBox;
                    jArr12[i12] = j7 + ((long) i28);
                    if (parsableByteArray4 == null) {
                        i23 = 1;
                    } else {
                        i23 = 0;
                    }
                    iArr15[i12] = i23;
                    if (i12 == unsignedIntToInt9) {
                        iArr15[i12] = 1;
                        i15--;
                        if (i15 > 0) {
                            unsignedIntToInt9 = parsableByteArray4.readUnsignedIntToInt() - 1;
                        }
                    }
                    long[] jArr14 = jArr12;
                    int[] iArr16 = iArr15;
                    j7 += (long) i21;
                    i24 = i27 - 1;
                    if (i24 == 0 || i26 <= 0) {
                        unsignedIntToInt4 = i21;
                        unsignedIntToInt5 = i24;
                    } else {
                        unsignedIntToInt5 = parsableByteArray3.readUnsignedIntToInt();
                        unsignedIntToInt4 = parsableByteArray3.readUnsignedIntToInt();
                        i26--;
                    }
                    int i29 = unsignedIntToInt5;
                    j6 += (long) iArr2[i12];
                    i13--;
                    i12++;
                    stz2SampleSizeBox = sampleSizeBox;
                    jArr11 = jArr13;
                    sampleCount = sampleCount;
                    jArr12 = jArr14;
                    i14 = i28;
                    i17 = unsignedIntToInt4;
                    iArr15 = iArr16;
                    int i30 = i22;
                    i18 = i29;
                    i16 = i30;
                }
                i = sampleCount;
                long[] jArr15 = jArr11;
                int[] iArr17 = iArr15;
                long[] jArr16 = jArr12;
                int i31 = i18;
                if (unsignedIntToInt3 == 0) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                Assertions.checkArgument(z9);
                for (i19 = i16; i19 > 0; i19--) {
                    if (parsableByteArray6.readUnsignedIntToInt() == 0) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    Assertions.checkArgument(z10);
                    parsableByteArray6.readInt();
                }
                if (i15 == 0 || i31 != 0) {
                    i20 = i13;
                } else {
                    i20 = i13;
                    if (i20 == 0 && i26 == 0) {
                        track2 = track;
                    }
                    jArr2 = jArr15;
                    iArr3 = iArr17;
                    jArr3 = jArr16;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Inconsistent stbl box for track ");
                track2 = track;
                sb.append(track2.id);
                sb.append(": remainingSynchronizationSamples ");
                sb.append(i15);
                sb.append(", remainingSamplesAtTimestampDelta ");
                sb.append(i31);
                sb.append(", remainingSamplesInChunk ");
                sb.append(i20);
                sb.append(", remainingTimestampDeltaChanges ");
                sb.append(i26);
                Log.w("AtomParsers", sb.toString());
                jArr2 = jArr15;
                iArr3 = iArr17;
                jArr3 = jArr16;
            } else {
                i = sampleCount;
                SampleSizeBox sampleSizeBox2 = stz2SampleSizeBox;
                track2 = track;
                int i32 = chunkIterator.length;
                jArr = new long[i32];
                iArr = new int[i32];
                while (chunkIterator.moveNext()) {
                    int i33 = chunkIterator.index;
                    jArr[i33] = chunkIterator.offset;
                    iArr[i33] = chunkIterator.numSamples;
                }
                FixedSampleSizeRechunker.Results resultsRechunk = FixedSampleSizeRechunker.rechunk(sampleSizeBox2.readNextSampleSize(), jArr, iArr, unsignedIntToInt8);
                jArr2 = resultsRechunk.offsets;
                iArr2 = resultsRechunk.sizes;
                i2 = resultsRechunk.maximumSize;
                jArr3 = resultsRechunk.timestamps;
                iArr3 = resultsRechunk.flags;
            }
            i3 = i2;
            jArr4 = track2.editListDurations;
            if (jArr4 == null) {
                Util.scaleLargeTimestampsInPlace(jArr3, 1000000L, track2.timescale);
                return new TrackSampleTable(jArr2, iArr2, i3, jArr3, iArr3);
            }
            if (jArr4.length == 1) {
                c = 0;
                if (jArr4[0] == 0) {
                    i11 = 0;
                    while (i11 < jArr3.length) {
                        jArr3[i11] = Util.scaleLargeTimestamp(jArr3[i11] - track2.editListMediaTimes[c], 1000000L, track2.timescale);
                        i11++;
                        c = 0;
                    }
                    return new TrackSampleTable(jArr2, iArr2, i3, jArr3, iArr3);
                }
            }
            i4 = 0;
            z3 = false;
            i5 = 0;
            i6 = 0;
            while (true) {
                jArr5 = track2.editListDurations;
                j2 = -1;
                if (i4 < jArr5.length) {
                    break;
                }
                j5 = track2.editListMediaTimes[i4];
                if (j5 != -1) {
                    long jScaleLargeTimestamp = Util.scaleLargeTimestamp(jArr5[i4], track2.timescale, track2.movieTimescale);
                    iBinarySearchCeil3 = Util.binarySearchCeil(jArr3, j5, true, true);
                    int iBinarySearchCeil4 = Util.binarySearchCeil(jArr3, j5 + jScaleLargeTimestamp, true, false);
                    i5 += iBinarySearchCeil4 - iBinarySearchCeil3;
                    if (i6 != iBinarySearchCeil3) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    z3 |= z8;
                    i6 = iBinarySearchCeil4;
                }
                i4++;
            }
            if (i5 != i) {
                z4 = true;
            } else {
                z4 = false;
            }
            z5 = z4 | z3;
            if (z5) {
                jArr6 = new long[i5];
            } else {
                jArr6 = jArr2;
            }
            if (z5) {
                iArr4 = new int[i5];
            } else {
                iArr4 = iArr2;
            }
            if (z5) {
                i3 = 0;
            }
            if (z5) {
                iArr5 = new int[i5];
            } else {
                iArr5 = iArr3;
            }
            jArr7 = new long[i5];
            i7 = i3;
            i8 = 0;
            i9 = 0;
            while (true) {
                jArr8 = track2.editListDurations;
                if (i8 < jArr8.length) {
                    break;
                }
                iArr8 = iArr4;
                iArr9 = iArr5;
                j3 = track2.editListMediaTimes[i8];
                j4 = jArr8[i8];
                if (j3 != j2) {
                    iArr14 = iArr2;
                    long jScaleLargeTimestamp2 = Util.scaleLargeTimestamp(j4, track2.timescale, track2.movieTimescale) + j3;
                    iBinarySearchCeil = Util.binarySearchCeil(jArr3, j3, true, true);
                    iBinarySearchCeil2 = Util.binarySearchCeil(jArr3, jScaleLargeTimestamp2, true, false);
                    if (z5) {
                        int i34 = iBinarySearchCeil2 - iBinarySearchCeil;
                        System.arraycopy(jArr2, iBinarySearchCeil, jArr6, i9, i34);
                        iArr10 = iArr14;
                        iArr11 = iArr8;
                        System.arraycopy(iArr10, iBinarySearchCeil, iArr11, i9, i34);
                        iArr12 = iArr9;
                        System.arraycopy(iArr3, iBinarySearchCeil, iArr12, i9, i34);
                    } else {
                        iArr10 = iArr14;
                        iArr11 = iArr8;
                        iArr12 = iArr9;
                    }
                    int i35 = i7;
                    while (iBinarySearchCeil < iBinarySearchCeil2) {
                        long[] jArr17 = jArr2;
                        int[] iArr18 = iArr3;
                        long[] jArr18 = jArr3;
                        long j8 = j3;
                        jArr7[i9] = Util.scaleLargeTimestamp(j, 1000000L, track2.movieTimescale) + Util.scaleLargeTimestamp(jArr3[iBinarySearchCeil] - j3, 1000000L, track2.timescale);
                        if (!z5 && iArr11[i9] > i35) {
                            i35 = iArr10[iBinarySearchCeil];
                        }
                        i9++;
                        iBinarySearchCeil++;
                        jArr2 = jArr17;
                        jArr3 = jArr18;
                        j3 = j8;
                        iArr3 = iArr18;
                    }
                    jArr9 = jArr2;
                    iArr13 = iArr3;
                    jArr10 = jArr3;
                    i7 = i35;
                } else {
                    jArr9 = jArr2;
                    jArr10 = jArr3;
                    iArr10 = iArr2;
                    iArr11 = iArr8;
                    iArr12 = iArr9;
                    iArr13 = iArr3;
                }
                j += j4;
                i8++;
                jArr2 = jArr9;
                iArr2 = iArr10;
                iArr5 = iArr12;
                iArr4 = iArr11;
                jArr3 = jArr10;
                iArr3 = iArr13;
                j2 = -1;
            }
            iArr6 = iArr4;
            iArr7 = iArr5;
            z6 = false;
            for (i10 = 0; i10 < iArr7.length && !z6; i10++) {
                if ((iArr7[i10] & 1) != 0) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                z6 |= z7;
            }
            if (z6) {
                throw new ParserException("The edited sample sequence does not contain a sync sample.");
            }
            return new TrackSampleTable(jArr6, iArr6, i7, jArr7, iArr7);
        }
        unsignedIntToInt2 = 0;
        parsableByteArray4 = parsableByteArray5;
        if (!stz2SampleSizeBox.isFixedSampleSize()) {
            z2 = false;
        } else {
            z2 = false;
        }
        j = 0;
        if (!z2) {
            jArr11 = new long[sampleCount];
            iArr2 = new int[sampleCount];
            jArr12 = new long[sampleCount];
            int i210 = unsignedIntToInt2;
            iArr15 = new int[sampleCount];
            j6 = 0;
            j7 = 0;
            i12 = 0;
            i2 = 0;
            unsignedIntToInt3 = 0;
            i13 = 0;
            i14 = 0;
            i15 = i210;
            i16 = unsignedIntToInt;
            i17 = unsignedIntToInt8;
            i18 = unsignedIntToInt7;
            int i211 = unsignedIntToInt6;
            while (i12 < sampleCount) {
                while (i13 == 0) {
                    Assertions.checkState(chunkIterator.moveNext());
                    j6 = chunkIterator.offset;
                    i13 = chunkIterator.numSamples;
                    i18 = i18;
                    i17 = i17;
                }
                int i212 = i18;
                i21 = i17;
                i22 = i16;
                if (parsableByteArray6 != null) {
                    while (unsignedIntToInt3 == 0) {
                        unsignedIntToInt3 = parsableByteArray6.readUnsignedIntToInt();
                        i14 = parsableByteArray6.readInt();
                        i22--;
                    }
                    unsignedIntToInt3--;
                }
                int i213 = i14;
                jArr11[i12] = j6;
                iArr2[i12] = stz2SampleSizeBox.readNextSampleSize();
                long[] jArr19 = jArr11;
                if (iArr2[i12] > i2) {
                    i2 = iArr2[i12];
                }
                SampleSizeBox sampleSizeBox3 = stz2SampleSizeBox;
                jArr12[i12] = j7 + ((long) i213);
                if (parsableByteArray4 == null) {
                    i23 = 1;
                } else {
                    i23 = 0;
                }
                iArr15[i12] = i23;
                if (i12 == unsignedIntToInt9) {
                    iArr15[i12] = 1;
                    i15--;
                    if (i15 > 0) {
                        unsignedIntToInt9 = parsableByteArray4.readUnsignedIntToInt() - 1;
                    }
                }
                long[] jArr110 = jArr12;
                int[] iArr19 = iArr15;
                j7 += (long) i21;
                i24 = i212 - 1;
                if (i24 == 0) {
                    unsignedIntToInt4 = i21;
                    unsignedIntToInt5 = i24;
                } else {
                    unsignedIntToInt4 = i21;
                    unsignedIntToInt5 = i24;
                }
                int i214 = unsignedIntToInt5;
                j6 += (long) iArr2[i12];
                i13--;
                i12++;
                stz2SampleSizeBox = sampleSizeBox3;
                jArr11 = jArr19;
                sampleCount = sampleCount;
                jArr12 = jArr110;
                i14 = i213;
                i17 = unsignedIntToInt4;
                iArr15 = iArr19;
                int i36 = i22;
                i18 = i214;
                i16 = i36;
            }
            i = sampleCount;
            long[] jArr111 = jArr11;
            int[] iArr110 = iArr15;
            long[] jArr112 = jArr12;
            int i37 = i18;
            if (unsignedIntToInt3 == 0) {
                z9 = true;
            } else {
                z9 = false;
            }
            Assertions.checkArgument(z9);
            while (i19 > 0) {
                if (parsableByteArray6.readUnsignedIntToInt() == 0) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                Assertions.checkArgument(z10);
                parsableByteArray6.readInt();
            }
            if (i15 == 0) {
                i20 = i13;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Inconsistent stbl box for track ");
                track2 = track;
                sb2.append(track2.id);
                sb2.append(": remainingSynchronizationSamples ");
                sb2.append(i15);
                sb2.append(", remainingSamplesAtTimestampDelta ");
                sb2.append(i37);
                sb2.append(", remainingSamplesInChunk ");
                sb2.append(i20);
                sb2.append(", remainingTimestampDeltaChanges ");
                sb2.append(i211);
                Log.w("AtomParsers", sb2.toString());
            } else {
                i20 = i13;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Inconsistent stbl box for track ");
                track2 = track;
                sb3.append(track2.id);
                sb3.append(": remainingSynchronizationSamples ");
                sb3.append(i15);
                sb3.append(", remainingSamplesAtTimestampDelta ");
                sb3.append(i37);
                sb3.append(", remainingSamplesInChunk ");
                sb3.append(i20);
                sb3.append(", remainingTimestampDeltaChanges ");
                sb3.append(i211);
                Log.w("AtomParsers", sb3.toString());
            }
            jArr2 = jArr111;
            iArr3 = iArr110;
            jArr3 = jArr112;
        } else {
            i = sampleCount;
            SampleSizeBox sampleSizeBox4 = stz2SampleSizeBox;
            track2 = track;
            int i38 = chunkIterator.length;
            jArr = new long[i38];
            iArr = new int[i38];
            while (chunkIterator.moveNext()) {
                int i39 = chunkIterator.index;
                jArr[i39] = chunkIterator.offset;
                iArr[i39] = chunkIterator.numSamples;
            }
            FixedSampleSizeRechunker.Results resultsRechunk2 = FixedSampleSizeRechunker.rechunk(sampleSizeBox4.readNextSampleSize(), jArr, iArr, unsignedIntToInt8);
            jArr2 = resultsRechunk2.offsets;
            iArr2 = resultsRechunk2.sizes;
            i2 = resultsRechunk2.maximumSize;
            jArr3 = resultsRechunk2.timestamps;
            iArr3 = resultsRechunk2.flags;
        }
        i3 = i2;
        jArr4 = track2.editListDurations;
        if (jArr4 == null) {
            Util.scaleLargeTimestampsInPlace(jArr3, 1000000L, track2.timescale);
            return new TrackSampleTable(jArr2, iArr2, i3, jArr3, iArr3);
        }
        if (jArr4.length == 1) {
            c = 0;
            if (jArr4[0] == 0) {
                i11 = 0;
                while (i11 < jArr3.length) {
                    jArr3[i11] = Util.scaleLargeTimestamp(jArr3[i11] - track2.editListMediaTimes[c], 1000000L, track2.timescale);
                    i11++;
                    c = 0;
                }
                return new TrackSampleTable(jArr2, iArr2, i3, jArr3, iArr3);
            }
        }
        i4 = 0;
        z3 = false;
        i5 = 0;
        i6 = 0;
        while (true) {
            jArr5 = track2.editListDurations;
            j2 = -1;
            if (i4 < jArr5.length) {
                break;
                break;
            }
            j5 = track2.editListMediaTimes[i4];
            if (j5 != -1) {
                long jScaleLargeTimestamp3 = Util.scaleLargeTimestamp(jArr5[i4], track2.timescale, track2.movieTimescale);
                iBinarySearchCeil3 = Util.binarySearchCeil(jArr3, j5, true, true);
                int iBinarySearchCeil5 = Util.binarySearchCeil(jArr3, j5 + jScaleLargeTimestamp3, true, false);
                i5 += iBinarySearchCeil5 - iBinarySearchCeil3;
                if (i6 != iBinarySearchCeil3) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                z3 |= z8;
                i6 = iBinarySearchCeil5;
            }
            i4++;
        }
        if (i5 != i) {
            z4 = true;
        } else {
            z4 = false;
        }
        z5 = z4 | z3;
        if (z5) {
            jArr6 = new long[i5];
        } else {
            jArr6 = jArr2;
        }
        if (z5) {
            iArr4 = new int[i5];
        } else {
            iArr4 = iArr2;
        }
        if (z5) {
            i3 = 0;
        }
        if (z5) {
            iArr5 = new int[i5];
        } else {
            iArr5 = iArr3;
        }
        jArr7 = new long[i5];
        i7 = i3;
        i8 = 0;
        i9 = 0;
        while (true) {
            jArr8 = track2.editListDurations;
            if (i8 < jArr8.length) {
                break;
                break;
            }
            iArr8 = iArr4;
            iArr9 = iArr5;
            j3 = track2.editListMediaTimes[i8];
            j4 = jArr8[i8];
            if (j3 != j2) {
                iArr14 = iArr2;
                long jScaleLargeTimestamp4 = Util.scaleLargeTimestamp(j4, track2.timescale, track2.movieTimescale) + j3;
                iBinarySearchCeil = Util.binarySearchCeil(jArr3, j3, true, true);
                iBinarySearchCeil2 = Util.binarySearchCeil(jArr3, jScaleLargeTimestamp4, true, false);
                if (z5) {
                    int i310 = iBinarySearchCeil2 - iBinarySearchCeil;
                    System.arraycopy(jArr2, iBinarySearchCeil, jArr6, i9, i310);
                    iArr10 = iArr14;
                    iArr11 = iArr8;
                    System.arraycopy(iArr10, iBinarySearchCeil, iArr11, i9, i310);
                    iArr12 = iArr9;
                    System.arraycopy(iArr3, iBinarySearchCeil, iArr12, i9, i310);
                } else {
                    iArr10 = iArr14;
                    iArr11 = iArr8;
                    iArr12 = iArr9;
                }
                int i311 = i7;
                while (iBinarySearchCeil < iBinarySearchCeil2) {
                    long[] jArr113 = jArr2;
                    int[] iArr111 = iArr3;
                    long[] jArr114 = jArr3;
                    long j9 = j3;
                    jArr7[i9] = Util.scaleLargeTimestamp(j, 1000000L, track2.movieTimescale) + Util.scaleLargeTimestamp(jArr3[iBinarySearchCeil] - j3, 1000000L, track2.timescale);
                    if (!z5) {
                    }
                    i9++;
                    iBinarySearchCeil++;
                    jArr2 = jArr113;
                    jArr3 = jArr114;
                    j3 = j9;
                    iArr3 = iArr111;
                }
                jArr9 = jArr2;
                iArr13 = iArr3;
                jArr10 = jArr3;
                i7 = i311;
            } else {
                jArr9 = jArr2;
                jArr10 = jArr3;
                iArr10 = iArr2;
                iArr11 = iArr8;
                iArr12 = iArr9;
                iArr13 = iArr3;
            }
            j += j4;
            i8++;
            jArr2 = jArr9;
            iArr2 = iArr10;
            iArr5 = iArr12;
            iArr4 = iArr11;
            jArr3 = jArr10;
            iArr3 = iArr13;
            j2 = -1;
        }
        iArr6 = iArr4;
        iArr7 = iArr5;
        z6 = false;
        while (i10 < iArr7.length) {
            if ((iArr7[i10] & 1) != 0) {
                z7 = true;
            } else {
                z7 = false;
            }
            z6 |= z7;
        }
        if (z6) {
            throw new ParserException("The edited sample sequence does not contain a sync sample.");
        }
        return new TrackSampleTable(jArr6, iArr6, i7, jArr7, iArr7);
    }

    public static GaplessInfo parseUdta(Atom.LeafAtom leafAtom, boolean z) {
        if (z) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int i = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_meta) {
                parsableByteArray.setPosition(parsableByteArray.getPosition() - 8);
                parsableByteArray.setLimit(parsableByteArray.getPosition() + i);
                return parseMetaAtom(parsableByteArray);
            }
            parsableByteArray.skipBytes(i - 8);
        }
        return null;
    }

    private static GaplessInfo parseMetaAtom(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(12);
        ParsableByteArray parsableByteArray2 = new ParsableByteArray();
        while (parsableByteArray.bytesLeft() >= 8) {
            int i = parsableByteArray.readInt() - 8;
            if (parsableByteArray.readInt() == Atom.TYPE_ilst) {
                parsableByteArray2.reset(parsableByteArray.data, parsableByteArray.getPosition() + i);
                parsableByteArray2.setPosition(parsableByteArray.getPosition());
                GaplessInfo ilst = parseIlst(parsableByteArray2);
                if (ilst != null) {
                    return ilst;
                }
            }
            parsableByteArray.skipBytes(i);
        }
        return null;
    }

    private static GaplessInfo parseIlst(ParsableByteArray parsableByteArray) {
        while (true) {
            String string = null;
            if (parsableByteArray.bytesLeft() <= 0) {
                return null;
            }
            int position = parsableByteArray.getPosition() + parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_DASHES) {
                String string2 = null;
                String string3 = null;
                while (parsableByteArray.getPosition() < position) {
                    int i = parsableByteArray.readInt() - 12;
                    int i2 = parsableByteArray.readInt();
                    parsableByteArray.skipBytes(4);
                    if (i2 == Atom.TYPE_mean) {
                        string3 = parsableByteArray.readString(i);
                    } else if (i2 == Atom.TYPE_name) {
                        string = parsableByteArray.readString(i);
                    } else if (i2 == Atom.TYPE_data) {
                        parsableByteArray.skipBytes(4);
                        string2 = parsableByteArray.readString(i - 4);
                    } else {
                        parsableByteArray.skipBytes(i);
                    }
                }
                if (string != null && string2 != null && "com.apple.iTunes".equals(string3)) {
                    return GaplessInfo.createFromComment(string, string2);
                }
            } else {
                parsableByteArray.setPosition(position);
            }
        }
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        parsableByteArray.skipBytes(Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0 ? 16 : 8);
        return parsableByteArray.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        boolean z;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 8 : 16);
        int i = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        int i2 = fullAtomVersion == 0 ? 4 : 8;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= i2) {
                z = true;
                break;
            }
            if (parsableByteArray.data[position + i4] != -1) {
                z = false;
                break;
            }
            i4++;
        }
        long j = -1;
        if (z) {
            parsableByteArray.skipBytes(i2);
        } else {
            long unsignedInt = fullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (unsignedInt != 0) {
                j = unsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        int i5 = parsableByteArray.readInt();
        int i6 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int i7 = parsableByteArray.readInt();
        int i8 = parsableByteArray.readInt();
        if (i5 == 0 && i6 == 65536 && i7 == -65536 && i8 == 0) {
            i3 = 90;
        } else if (i5 == 0 && i6 == -65536 && i7 == 65536 && i8 == 0) {
            i3 = 270;
        } else if (i5 == -65536 && i6 == 0 && i7 == 0 && i8 == -65536) {
            i3 = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_EMPTY;
        }
        return new TkhdData(i, j, i3);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 8 : 16);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 4 : 8);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        return Pair.create(Long.valueOf(unsignedInt), "" + ((char) (((unsignedShort >> 10) & 31) + 96)) + ((char) (((unsignedShort >> 5) & 31) + 96)) + ((char) ((unsignedShort & 31) + 96)));
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, long j, int i2, String str, boolean z) {
        parsableByteArray.setPosition(12);
        int i3 = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(i3);
        for (int i4 = 0; i4 < i3; i4++) {
            int position = parsableByteArray.getPosition();
            int i5 = parsableByteArray.readInt();
            Assertions.checkArgument(i5 > 0, "childAtomSize should be positive");
            int i6 = parsableByteArray.readInt();
            if (i6 == Atom.TYPE_avc1 || i6 == Atom.TYPE_avc3 || i6 == Atom.TYPE_encv || i6 == Atom.TYPE_mp4v || i6 == Atom.TYPE_hvc1 || i6 == Atom.TYPE_hev1 || i6 == Atom.TYPE_s263 || i6 == Atom.TYPE_vp08 || i6 == Atom.TYPE_vp09 || i6 == Atom.TYPE_wvc1) {
                parseVideoSampleEntry(parsableByteArray, i6, position, i5, i, j, i2, stsdData, i4);
            } else if (i6 == Atom.TYPE_mp4a || i6 == Atom.TYPE_enca || i6 == Atom.TYPE_ac_3 || i6 == Atom.TYPE_ec_3 || i6 == Atom.TYPE_dtsc || i6 == Atom.TYPE_dtse || i6 == Atom.TYPE_dtsh || i6 == Atom.TYPE_dtsl || i6 == Atom.TYPE_samr || i6 == Atom.TYPE_sawb || i6 == Atom.TYPE_lpcm || i6 == Atom.TYPE_sowt || i6 == Atom.TYPE__mp3) {
                parseAudioSampleEntry(parsableByteArray, i6, position, i5, i, j, str, z, stsdData, i4);
            } else if (i6 == Atom.TYPE_TTML) {
                stsdData.mediaFormat = MediaFormat.createTextFormat(Integer.toString(i), "application/ttml+xml", -1, j, str);
            } else if (i6 == Atom.TYPE_tx3g) {
                stsdData.mediaFormat = MediaFormat.createTextFormat(Integer.toString(i), "application/x-quicktime-tx3g", -1, j, str);
            } else if (i6 == Atom.TYPE_wvtt) {
                stsdData.mediaFormat = MediaFormat.createTextFormat(Integer.toString(i), "application/x-mp4vtt", -1, j, str);
            } else if (i6 == Atom.TYPE_stpp) {
                stsdData.mediaFormat = MediaFormat.createTextFormat(Integer.toString(i), "application/ttml+xml", -1, j, str, 0L);
            } else if (i6 == Atom.TYPE_wma || i6 == Atom.TYPE_WMA || i6 == Atom.TYPE_wma1 || i6 == Atom.TYPE_WMA1 || i6 == Atom.TYPE_wma2 || i6 == Atom.TYPE_WMA2 || i6 == Atom.TYPE_wma3 || i6 == Atom.TYPE_WMA3 || i6 == Atom.TYPE_wmap || i6 == Atom.TYPE_WMAP) {
                parseAudioSampleEntry(parsableByteArray, i6, position, i5, i, j, str, false, stsdData, i4);
            } else if (i6 == Atom.TYPE_camm) {
                stsdData.mediaFormat = MediaFormat.createFormatForMimeType(Integer.toString(i), "application/x-camera-motion", -1, j);
            }
            parsableByteArray.setPosition(position + i5);
        }
        return stsdData;
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, long j, int i5, StsdData stsdData, int i6) {
        parsableByteArray.setPosition(i2 + 8);
        parsableByteArray.skipBytes(24);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(50);
        int position = parsableByteArray.getPosition();
        if (i == Atom.TYPE_encv) {
            parseSampleEntryEncryptionData(parsableByteArray, i2, i3, stsdData, i6);
            parsableByteArray.setPosition(position);
        }
        String str = null;
        int i7 = -1;
        List<byte[]> dvc1FromParent = null;
        byte[] projFromParent = null;
        float paspFromParent = 1.0f;
        boolean z = false;
        while (position - i2 < i3) {
            parsableByteArray.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int i8 = parsableByteArray.readInt();
            if (i8 == 0 && parsableByteArray.getPosition() - i2 == i3) {
                break;
            }
            Assertions.checkArgument(i8 > 0, "childAtomSize should be positive");
            int i9 = parsableByteArray.readInt();
            if (i9 == Atom.TYPE_avcC) {
                Assertions.checkState(str == null);
                AvcCData avcCFromParent = parseAvcCFromParent(parsableByteArray, position2);
                dvc1FromParent = avcCFromParent.initializationData;
                stsdData.nalUnitLengthFieldLength = avcCFromParent.nalUnitLengthFieldLength;
                if (!z) {
                    paspFromParent = avcCFromParent.pixelWidthAspectRatio;
                }
                str = "video/avc";
            } else if (i9 == Atom.TYPE_hvcC) {
                Assertions.checkState(str == null);
                Pair<List<byte[]>, Integer> hvcCFromParent = parseHvcCFromParent(parsableByteArray, position2);
                dvc1FromParent = (List) hvcCFromParent.first;
                stsdData.nalUnitLengthFieldLength = ((Integer) hvcCFromParent.second).intValue();
                str = "video/hevc";
            } else if (i9 == Atom.TYPE_d263) {
                Assertions.checkState(str == null);
                str = "video/3gpp";
            } else if (i9 == Atom.TYPE_esds) {
                Assertions.checkState(str == null);
                Pair<String, byte[]> esdsFromParent = parseEsdsFromParent(parsableByteArray, position2);
                String str2 = (String) esdsFromParent.first;
                dvc1FromParent = Collections.singletonList((byte[]) esdsFromParent.second);
                str = str2;
            } else if (i9 == Atom.TYPE_pasp) {
                paspFromParent = parsePaspFromParent(parsableByteArray, position2);
                z = true;
            } else if (i9 == Atom.TYPE_dvc1) {
                dvc1FromParent = parseDvc1FromParent(parsableByteArray, position2, unsignedShort, unsignedShort2);
                str = "video/wvc1";
            } else if (i9 == Atom.TYPE_vpcC) {
                Assertions.checkState(str == null);
                str = i == Atom.TYPE_vp08 ? "video/x-vnd.on2.vp8" : "video/x-vnd.on2.vp9";
            } else if (i9 == Atom.TYPE_sv3d) {
                projFromParent = parseProjFromParent(parsableByteArray, position2, i8);
            } else if (i9 == Atom.TYPE_st3d) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                parsableByteArray.skipBytes(3);
                if (unsignedByte == 0) {
                    int unsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (unsignedByte2 == 0) {
                        i7 = 0;
                    } else if (unsignedByte2 == 1) {
                        i7 = 1;
                    } else if (unsignedByte2 == 2) {
                        i7 = 2;
                    } else if (unsignedByte2 == 3) {
                        i7 = 3;
                    }
                }
            }
            position += i8;
        }
        if (str == null) {
            return;
        }
        stsdData.mediaFormat = MediaFormat.createVideoFormat(Integer.toString(i4), str, -1, -1, j, unsignedShort, unsignedShort2, dvc1FromParent, i5, paspFromParent, projFromParent, i7);
    }

    private static AvcCData parseAvcCFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 4);
        int unsignedByte = (parsableByteArray.readUnsignedByte() & 3) + 1;
        if (unsignedByte == 3) {
            throw new IllegalStateException();
        }
        ArrayList arrayList = new ArrayList();
        float f = 1.0f;
        int unsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
        for (int i2 = 0; i2 < unsignedByte2; i2++) {
            arrayList.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
        }
        int unsignedByte3 = parsableByteArray.readUnsignedByte();
        for (int i3 = 0; i3 < unsignedByte3; i3++) {
            arrayList.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
        }
        if (unsignedByte2 > 0) {
            ParsableBitArray parsableBitArray = new ParsableBitArray((byte[]) arrayList.get(0));
            parsableBitArray.setPosition((unsignedByte + 1) * 8);
            f = NalUnitUtil.parseSpsNalUnit(parsableBitArray).pixelWidthAspectRatio;
        }
        return new AvcCData(arrayList, unsignedByte, f);
    }

    private static Pair<List<byte[]>, Integer> parseHvcCFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 21);
        int unsignedByte = parsableByteArray.readUnsignedByte() & 3;
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        int position = parsableByteArray.getPosition();
        int i2 = 0;
        for (int i3 = 0; i3 < unsignedByte2; i3++) {
            parsableByteArray.skipBytes(1);
            int unsignedShort = parsableByteArray.readUnsignedShort();
            for (int i4 = 0; i4 < unsignedShort; i4++) {
                int unsignedShort2 = parsableByteArray.readUnsignedShort();
                i2 += unsignedShort2 + 4;
                parsableByteArray.skipBytes(unsignedShort2);
            }
        }
        parsableByteArray.setPosition(position);
        byte[] bArr = new byte[i2];
        int i5 = 0;
        for (int i6 = 0; i6 < unsignedByte2; i6++) {
            parsableByteArray.skipBytes(1);
            int unsignedShort3 = parsableByteArray.readUnsignedShort();
            for (int i7 = 0; i7 < unsignedShort3; i7++) {
                int unsignedShort4 = parsableByteArray.readUnsignedShort();
                byte[] bArr2 = NalUnitUtil.NAL_START_CODE;
                System.arraycopy(bArr2, 0, bArr, i5, bArr2.length);
                int length = i5 + NalUnitUtil.NAL_START_CODE.length;
                System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), bArr, length, unsignedShort4);
                i5 = length + unsignedShort4;
                parsableByteArray.skipBytes(unsignedShort4);
            }
        }
        return Pair.create(i2 == 0 ? null : Collections.singletonList(bArr), Integer.valueOf(unsignedByte + 1));
    }

    private static Pair<long[], long[]> parseEdts(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType;
        if (containerAtom == null || (leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_elst)) == null) {
            return Pair.create(null, null);
        }
        ParsableByteArray parsableByteArray = leafAtomOfType.data;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[unsignedIntToInt];
        long[] jArr2 = new long[unsignedIntToInt];
        for (int i = 0; i < unsignedIntToInt; i++) {
            jArr[i] = fullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = fullAtomVersion == 1 ? parsableByteArray.readLong() : parsableByteArray.readInt();
            if (parsableByteArray.readShort() != 1) {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
            parsableByteArray.skipBytes(2);
        }
        return Pair.create(jArr, jArr2);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return parsableByteArray.readUnsignedIntToInt() / parsableByteArray.readUnsignedIntToInt();
    }

    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, long j, String str, boolean z, StsdData stsdData, int i5) {
        int unsignedShort;
        int unsignedFixedPoint1616;
        int unsignedIntToInt;
        String str2;
        int i6;
        char c;
        String str3;
        StsdData stsdData2;
        boolean z2;
        int i7;
        int i8;
        int iFindEsdsPosition;
        String str4;
        int i9;
        int i10;
        String str5;
        int i11;
        int i12;
        int i13 = i3;
        StsdData stsdData3 = stsdData;
        parsableByteArray.setPosition(i2 + 8);
        char c2 = '\b';
        if (z) {
            parsableByteArray.skipBytes(8);
            unsignedShort = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
        } else {
            parsableByteArray.skipBytes(16);
            unsignedShort = 0;
        }
        int i14 = 2;
        boolean z3 = true;
        if (unsignedShort == 0 || unsignedShort == 1) {
            int unsignedShort2 = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
            unsignedFixedPoint1616 = parsableByteArray.readUnsignedFixedPoint1616();
            if (unsignedShort == 1) {
                parsableByteArray.skipBytes(16);
            }
            unsignedIntToInt = unsignedShort2;
        } else {
            if (unsignedShort != 2) {
                return;
            }
            parsableByteArray.skipBytes(16);
            unsignedFixedPoint1616 = (int) Math.round(parsableByteArray.readDouble());
            unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray.skipBytes(20);
        }
        int position = parsableByteArray.getPosition();
        int i15 = i;
        if (i15 == Atom.TYPE_enca) {
            int sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i2, i13, stsdData3, i5);
            parsableByteArray.setPosition(position);
            i15 = sampleEntryEncryptionData;
        }
        String str6 = "audio/raw";
        if (i15 == Atom.TYPE_ac_3) {
            str2 = "audio/ac3";
        } else if (i15 == Atom.TYPE_ec_3) {
            str2 = "audio/eac3";
        } else if (i15 == Atom.TYPE_dtsc) {
            str2 = "audio/vnd.dts";
        } else if (i15 == Atom.TYPE_dtsh || i15 == Atom.TYPE_dtsl) {
            str2 = "audio/vnd.dts.hd";
        } else if (i15 == Atom.TYPE_dtse) {
            str2 = "audio/vnd.dts.hd;profile=lbr";
        } else if (i15 == Atom.TYPE_samr) {
            str2 = "audio/3gpp";
        } else if (i15 == Atom.TYPE_sawb) {
            str2 = "audio/amr-wb";
        } else if (i15 == Atom.TYPE_lpcm || i15 == Atom.TYPE_sowt) {
            str2 = "audio/raw";
        } else {
            str2 = i15 == Atom.TYPE__mp3 ? "audio/mpeg" : null;
        }
        int iIntValue = unsignedFixedPoint1616;
        int iIntValue2 = unsignedIntToInt;
        byte[] bArr = null;
        while (position - i2 < i13) {
            parsableByteArray.setPosition(position);
            int i16 = parsableByteArray.readInt();
            if (i16 <= 0) {
                z3 = false;
            }
            Assertions.checkArgument(z3, "childAtomSize should be positive");
            int i17 = parsableByteArray.readInt();
            String str7 = "audio/x-ms-wma";
            if (i17 == Atom.TYPE_esds || (z && i17 == Atom.TYPE_wave)) {
                int i18 = position;
                String str8 = str2;
                i6 = i15;
                c = c2;
                str3 = str6;
                stsdData2 = stsdData3;
                z2 = true;
                i7 = 2;
                if (i17 == Atom.TYPE_esds) {
                    i8 = i18;
                    iFindEsdsPosition = i8;
                } else {
                    i8 = i18;
                    iFindEsdsPosition = findEsdsPosition(parsableByteArray, i8, i16);
                }
                if (iFindEsdsPosition != -1) {
                    Pair<String, byte[]> esdsFromParent = parseEsdsFromParent(parsableByteArray, iFindEsdsPosition);
                    str4 = (String) esdsFromParent.first;
                    bArr = (byte[]) esdsFromParent.second;
                    if ("audio/mp4a-latm".equals(str4)) {
                        Pair<Integer, Integer> aacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArr);
                        iIntValue = ((Integer) aacAudioSpecificConfig.first).intValue();
                        iIntValue2 = ((Integer) aacAudioSpecificConfig.second).intValue();
                    }
                } else {
                    str4 = str8;
                }
                str7 = str4;
            } else {
                if (i17 == Atom.TYPE_dac3) {
                    parsableByteArray.setPosition(position + 8);
                    stsdData3.mediaFormat = Ac3Util.parseAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), j, str);
                } else if (i17 == Atom.TYPE_dec3) {
                    parsableByteArray.setPosition(position + 8);
                    stsdData3.mediaFormat = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), j, str);
                } else {
                    if (i17 == Atom.TYPE_ddts) {
                        i9 = i16;
                        i10 = position;
                        str5 = str2;
                        z2 = true;
                        i11 = i15;
                        i7 = 2;
                        c = c2;
                        str3 = str6;
                        stsdData2 = stsdData3;
                        stsdData2.mediaFormat = MediaFormat.createAudioFormat(Integer.toString(i4), str2, -1, -1, j, iIntValue2, iIntValue, null, str);
                    } else {
                        i9 = i16;
                        i10 = position;
                        str5 = str2;
                        i11 = i15;
                        c = c2;
                        str3 = str6;
                        stsdData2 = stsdData3;
                        z2 = true;
                        i7 = 2;
                        if (i17 == Atom.TYPE_wfex || i17 == Atom.TYPE_WFEX) {
                            i16 = i9;
                            int i19 = i16 - 8;
                            bArr = new byte[i19];
                            System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), bArr, 0, i19);
                            i6 = i11;
                            if (i6 != Atom.TYPE_wma1 && i6 != Atom.TYPE_WMA1 && i6 != Atom.TYPE_wma2 && i6 != Atom.TYPE_WMA2) {
                                if (i6 == Atom.TYPE_wma3 || i6 == Atom.TYPE_WMA3) {
                                    str7 = "audio/x-ms-wmapro";
                                } else if ((i6 == Atom.TYPE_wma || i6 == Atom.TYPE_WMA) && (i12 = (bArr[0] & 255) | ((bArr[1] & 255) << 8)) != 352 && i12 != 353 && i12 == 354) {
                                    str7 = "audio/x-ms-wmapro";
                                }
                            }
                            i8 = i10;
                        }
                    }
                    i16 = i9;
                    i8 = i10;
                    str7 = str5;
                    i6 = i11;
                }
                i9 = i16;
                i10 = position;
                str5 = str2;
                i11 = i15;
                c = c2;
                str3 = str6;
                stsdData2 = stsdData3;
                z2 = true;
                i7 = 2;
                i16 = i9;
                i8 = i10;
                str7 = str5;
                i6 = i11;
            }
            position = i8 + i16;
            stsdData3 = stsdData2;
            i15 = i6;
            z3 = z2;
            i14 = i7;
            str2 = str7;
            c2 = c;
            str6 = str3;
            i13 = i3;
        }
        String str9 = str2;
        int i20 = i14;
        String str10 = str6;
        StsdData stsdData4 = stsdData3;
        if (stsdData4.mediaFormat != null || str9 == null) {
            return;
        }
        stsdData4.mediaFormat = MediaFormat.createAudioFormat(Integer.toString(i4), str9, -1, -1, j, iIntValue2, iIntValue, bArr == null ? null : Collections.singletonList(bArr), str, str10.equals(str9) ? i20 : -1);
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int i3 = parsableByteArray.readInt();
            Assertions.checkArgument(i3 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_esds) {
                return position;
            }
            position += i3;
        }
        return -1;
    }

    /* JADX WARN: Code duplicated, block: B:38:0x0090  */
    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if ((unsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((unsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((unsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        String str = null;
        if (unsignedByte2 == 32) {
            str = "video/mp4v-es";
        } else if (unsignedByte2 == 33) {
            str = "video/avc";
        } else if (unsignedByte2 == 35) {
            str = "video/hevc";
        } else if (unsignedByte2 == 64) {
            str = "audio/mp4a-latm";
        } else {
            if (unsignedByte2 == 107) {
                return Pair.create("audio/mpeg", null);
            }
            if (unsignedByte2 == 165) {
                str = "audio/ac3";
            } else if (unsignedByte2 != 166) {
                switch (unsignedByte2) {
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MENU_PRESNT /* 96 */:
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_EOS /* 97 */:
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_AS_CODEC_NOT_SUPPORT /* 98 */:
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VS_CODEC_NOT_SUPPORT /* 99 */:
                    case 100:
                    case 101:
                        parsableByteArray.setPosition(i);
                        int i2 = parsableByteArray.readInt();
                        parsableByteArray.skipBytes(4);
                        int i3 = i2 - 8;
                        byte[] bArr = new byte[i3];
                        parsableByteArray.readBytes(bArr, 0, i3);
                        return Pair.create("video/mpeg2", bArr);
                    case 102:
                    case 103:
                    case 104:
                        str = "audio/mp4a-latm";
                        break;
                    default:
                        switch (unsignedByte2) {
                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_ANA_AUTO_DETECTED_AUD_SYS /* 169 */:
                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_UNDERFLOW /* 172 */:
                                return Pair.create("audio/vnd.dts", null);
                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BUF_READY /* 170 */:
                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_TIME_UPDATE /* 171 */:
                                return Pair.create("audio/vnd.dts.hd", null);
                        }
                }
            } else {
                str = "audio/eac3";
            }
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        int expandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr2 = new byte[expandableClassSize];
        parsableByteArray.readBytes(bArr2, 0, expandableClassSize);
        return Pair.create(str, bArr2);
    }

    private static int parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2, StsdData stsdData, int i3) {
        Pair<Integer, TrackEncryptionBox> sinfFromParent;
        int position = parsableByteArray.getPosition();
        while (true) {
            if (position - i >= i2) {
                return 0;
            }
            parsableByteArray.setPosition(position);
            int i4 = parsableByteArray.readInt();
            Assertions.checkArgument(i4 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_sinf && (sinfFromParent = parseSinfFromParent(parsableByteArray, position, i4)) != null) {
                stsdData.trackEncryptionBoxes[i3] = (TrackEncryptionBox) sinfFromParent.second;
                return ((Integer) sinfFromParent.first).intValue();
            }
            position += i4;
        }
    }

    private static Pair<Integer, TrackEncryptionBox> parseSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        Integer numValueOf = null;
        TrackEncryptionBox schiFromParent = null;
        boolean z = false;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i4 = parsableByteArray.readInt();
            int i5 = parsableByteArray.readInt();
            if (i5 == Atom.TYPE_frma) {
                numValueOf = Integer.valueOf(parsableByteArray.readInt());
            } else if (i5 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                z = parsableByteArray.readInt() == TYPE_cenc;
            } else if (i5 == Atom.TYPE_schi) {
                schiFromParent = parseSchiFromParent(parsableByteArray, i3, i4);
            }
            i3 += i4;
        }
        if (!z) {
            return null;
        }
        Assertions.checkArgument(numValueOf != null, "frma atom is mandatory");
        Assertions.checkArgument(schiFromParent != null, "schi->tenc atom is mandatory");
        return Pair.create(numValueOf, schiFromParent);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i4 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                parsableByteArray.skipBytes(6);
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int unsignedByte = parsableByteArray.readUnsignedByte();
                byte[] bArr = new byte[16];
                parsableByteArray.readBytes(bArr, 0, 16);
                return new TrackEncryptionBox(z, unsignedByte, bArr);
            }
            i3 += i4;
        }
        return null;
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i4 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, i4 + i3);
            }
            i3 += i4;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i = unsignedByte & MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_UNBLOCKED;
        while ((unsignedByte & 128) == 128) {
            unsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (unsignedByte & MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_UNBLOCKED);
        }
        return i;
    }

    private static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            Assertions.checkState(parsableByteArray.readInt() == 1, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            this.offset = this.chunkOffsetsAreLongs ? this.chunkOffsets.readUnsignedLongToLong() : this.chunkOffsets.readUnsignedInt();
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    private static final class TkhdData {
        private final long duration;
        private final int id;
        private final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    private static final class StsdData {
        public MediaFormat mediaFormat;
        public int nalUnitLengthFieldLength = -1;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    private static final class AvcCData {
        public final List<byte[]> initializationData;
        public final int nalUnitLengthFieldLength;
        public final float pixelWidthAspectRatio;

        public AvcCData(List<byte[]> list, int i, float f) {
            this.initializationData = list;
            this.nalUnitLengthFieldLength = i;
            this.pixelWidthAspectRatio = f;
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fixedSampleSize = this.data.readUnsignedIntToInt();
            this.sampleCount = this.data.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == 0 ? this.data.readUnsignedIntToInt() : i;
        }

        @Override // com.google.android.exoplayer.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        @Override // com.google.android.exoplayer.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fieldSize = this.data.readUnsignedIntToInt() & 255;
            this.sampleCount = this.data.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fieldSize;
            if (i == 8) {
                return this.data.readUnsignedByte();
            }
            if (i == 16) {
                return this.data.readUnsignedShort();
            }
            int i2 = this.sampleIndex;
            this.sampleIndex = i2 + 1;
            if (i2 % 2 == 0) {
                int unsignedByte = this.data.readUnsignedByte();
                this.currentByte = unsignedByte;
                return (unsignedByte & 240) >> 4;
            }
            return this.currentByte & 15;
        }
    }

    public static boolean parseKidFromPssh(byte[] bArr, int i) {
        if (bArr[4] == 109 && bArr[5] == 97 && bArr[6] == 114 && bArr[7] == 108 && ((bArr[23] > 0 || bArr[22] > 0) && bArr[12] == 109 && bArr[13] == 107 && bArr[14] == 105 && bArr[15] == 100)) {
            int i2 = ((bArr[24] & 255) << 24) | ((bArr[25] & 255) << 16) | ((bArr[26] & 255) << 8) | (bArr[27] & 255);
            if (((bArr[23] & 255) | ((bArr[20] & 255) << 24) | ((bArr[21] & 255) << 16) | ((bArr[22] & 255) << 8)) == 1) {
                int i3 = i2 - 16;
                psshKidLen = i3;
                byte[] bArr2 = new byte[i3];
                psshKid = bArr2;
                System.arraycopy(bArr, 44, bArr2, 0, i3);
                return true;
            }
        }
        return false;
    }

    private static List<byte[]> parseDvc1FromParent(ParsableByteArray parsableByteArray, int i, int i2, int i3) {
        parsableByteArray.setPosition(i);
        int i4 = parsableByteArray.readInt() - 16;
        byte[] bArr = new byte[i4];
        parsableByteArray.skipBytes(12);
        System.arraycopy(parsableByteArray.data, i + 16, bArr, 0, i4);
        return new BitmapInfoHeader(i4 + 40, i2, i3, (short) 1, (short) 24, Util.getIntegerCodeForString("WVC1"), 0, 0, 0, 0, 0, bArr).constructBitMapinfoHeader();
    }
}
