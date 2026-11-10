package com.google.android.exoplayer.text.pgs;

import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PgsParserImpl {
    private FileBuffer buffer;
    private int forcedFrameCount;
    private boolean skipHeader;
    private List<SubPictureBD> subPictures = new ArrayList();

    private static class PCSSegment {
        int offset;
        long pts;
        int size;
        int type;

        private PCSSegment() {
        }

        /* synthetic */ PCSSegment(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public PgsParserImpl(InputStream inputStream, boolean z) throws Exception {
        this.skipHeader = false;
        this.skipHeader = z;
        try {
            this.buffer = new FileBuffer(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        parse();
    }

    public PgsParserImpl(byte[] bArr, boolean z) throws Exception {
        this.skipHeader = false;
        this.skipHeader = z;
        try {
            this.buffer = new FileBuffer(bArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        parse();
    }

    /* JADX WARN: Code duplicated, block: B:190:0x06d0 A[Catch: Exception -> 0x06e2, TryCatch #8 {Exception -> 0x06e2, blocks: (B:188:0x06cc, B:190:0x06d0, B:192:0x06d5, B:191:0x06d3), top: B:225:0x06cc }] */
    /* JADX WARN: Code duplicated, block: B:191:0x06d3 A[Catch: Exception -> 0x06e2, TryCatch #8 {Exception -> 0x06e2, blocks: (B:188:0x06cc, B:190:0x06d0, B:192:0x06d5, B:191:0x06d3), top: B:225:0x06cc }] */
    /* JADX WARN: Code duplicated, block: B:204:0x06fa  */
    /* JADX WARN: Code duplicated, block: B:205:0x0718  */
    /* JADX WARN: Instruction removed from duplicated block: B:204:0x06fa, please report this as an issue */
    private void parse() throws Exception {
        String str;
        int i;
        int i2;
        int i3;
        long j;
        SubPictureBD subPictureBD;
        int i4;
        SubPictureBD subPictureBD2;
        long startTime;
        SubPictureBD subPictureBD3;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        String str2 = "compositionState: ";
        FileBuffer fileBuffer = this.buffer;
        if (fileBuffer == null) {
            return;
        }
        long size = fileBuffer.getSize();
        PgsCompositionState pgsCompositionState = PgsCompositionState.INVALID;
        byte b = -1;
        int i10 = 0;
        SubPictureBD subPictureBD4 = null;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = -1;
        boolean z = false;
        char c = 0;
        SubPictureBD subPictureBD5 = null;
        long j2 = 0;
        SubPictureBD subPictureBD6 = null;
        int i15 = 0;
        int i16 = -1;
        while (true) {
            long j3 = i11;
            if (j3 < size) {
                i10 = i10;
                try {
                    StringBuilder sb = new StringBuilder();
                    pgsCompositionState = pgsCompositionState;
                    sb.append("bufferSize = ");
                    sb.append(size);
                    MtkDebugLogUtil.log("PGSParserLog", sb.toString(), 16);
                    PCSSegment pCSSegment = readPCSSegment(i11);
                    MtkDebugLogUtil.log("PGSParserLog", "segment.type = " + pCSSegment.type, 16);
                    int i17 = pCSSegment.type;
                    int i18 = i11;
                    if (i17 != 128) {
                        int i19 = i14;
                        i = i13;
                        switch (i17) {
                            case 20:
                                str = str2;
                                i7 = i16;
                                i14 = i19;
                                i8 = i12;
                                StringBuffer stringBuffer = new StringBuffer("PDS offset: ");
                                stringBuffer.append(ToolBox.toHexLeftZeroPadded(j3, 8));
                                stringBuffer.append(", size: ");
                                stringBuffer.append(ToolBox.toHexLeftZeroPadded(pCSSegment.size, 4));
                                try {
                                    if (i14 == i7) {
                                        MtkDebugLogUtil.log("PGSParserLog", ((Object) stringBuffer) + ", composition number unchanged -> ignored\n", 16);
                                    } else {
                                        if (subPictureBD4 != null) {
                                            StringBuffer stringBuffer2 = new StringBuffer();
                                            int pds = parsePDS(pCSSegment, subPictureBD4, stringBuffer2);
                                            if (pds >= 0) {
                                                MtkDebugLogUtil.log("PGSParserLog", ((Object) stringBuffer) + ", " + ((Object) stringBuffer2) + "\n", 16);
                                                if (pds > 0) {
                                                    i9 = i + 1;
                                                }
                                                i12 = i8;
                                                i10 = i10;
                                                pgsCompositionState = pgsCompositionState;
                                                i16 = i7;
                                                i13 = i9;
                                            } else {
                                                MtkDebugLogUtil.log("PGSParserLog", ((Object) stringBuffer) + "\n", 16);
                                                MtkDebugLogUtil.log("PGSParserLog", ((Object) stringBuffer2) + "\n", 16);
                                            }
                                            i9 = i;
                                            i12 = i8;
                                            i10 = i10;
                                            pgsCompositionState = pgsCompositionState;
                                            i16 = i7;
                                            i13 = i9;
                                        } else {
                                            MtkDebugLogUtil.log("PGSParserLog", ((Object) stringBuffer) + "\n", 16);
                                            MtkDebugLogUtil.log("PGSParserLog", "Missing PTS start -> ignored\n", 16);
                                        }
                                        if (this.skipHeader) {
                                            i5 = i18 + 3;
                                        } else {
                                            i5 = i18 + 13;
                                        }
                                        i11 = i5 + pCSSegment.size;
                                        size = this.buffer.getSize();
                                        str2 = str;
                                    }
                                    if (this.skipHeader) {
                                        i5 = i18 + 3;
                                    } else {
                                        i5 = i18 + 13;
                                    }
                                    i11 = i5 + pCSSegment.size;
                                    size = this.buffer.getSize();
                                    str2 = str;
                                } catch (Exception e) {
                                    e = e;
                                }
                                i12 = i8;
                                i16 = i7;
                                i13 = i;
                                break;
                            case 21:
                                str = str2;
                                i7 = i16;
                                i8 = i12;
                                StringBuffer stringBuffer3 = new StringBuffer("ODS offset: ");
                                stringBuffer3.append(ToolBox.toHexLeftZeroPadded(j3, 8));
                                stringBuffer3.append(", size: ");
                                stringBuffer3.append(ToolBox.toHexLeftZeroPadded(pCSSegment.size, 4));
                                i14 = i19;
                                if (i14 == i7) {
                                    MtkDebugLogUtil.log("PGSParserLog", ((Object) stringBuffer3) + ", composition number unchanged -> ignored\n", 16);
                                } else if (z) {
                                    MtkDebugLogUtil.log("PGSParserLog", ((Object) stringBuffer3) + "\n", 16);
                                    MtkDebugLogUtil.log("PGSParserLog", "palette update only -> ignored\n", 16);
                                } else {
                                    if (subPictureBD4 != null) {
                                        StringBuffer stringBuffer4 = new StringBuffer();
                                        i12 = parseODS(pCSSegment, subPictureBD4, stringBuffer4) ? i8 + 1 : i8;
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append((Object) stringBuffer3);
                                        sb2.append(", img size: ");
                                        sb2.append(subPictureBD4.getImageWidth());
                                        sb2.append("*");
                                        sb2.append(subPictureBD4.getImageHeight());
                                        sb2.append(stringBuffer4.length() == 0 ? "\n" : ", " + ((Object) stringBuffer4));
                                        sb2.append("\n");
                                        MtkDebugLogUtil.log("PGSParserLog", sb2.toString(), 16);
                                        i16 = i7;
                                    } else {
                                        MtkDebugLogUtil.log("PGSParserLog", ((Object) stringBuffer3) + "\n", 16);
                                        MtkDebugLogUtil.log("PGSParserLog", "missing PTS start -> ignored\n", 16);
                                    }
                                    i13 = i;
                                    if (this.skipHeader) {
                                        i5 = i18 + 3;
                                    } else {
                                        i5 = i18 + 13;
                                    }
                                    i11 = i5 + pCSSegment.size;
                                    size = this.buffer.getSize();
                                    str2 = str;
                                }
                                i12 = i8;
                                i16 = i7;
                                i13 = i;
                                if (this.skipHeader) {
                                    i5 = i18 + 3;
                                } else {
                                    i5 = i18 + 13;
                                }
                                i11 = i5 + pCSSegment.size;
                                size = this.buffer.getSize();
                                str2 = str;
                                break;
                            case 22:
                                try {
                                    int compositionNumber = getCompositionNumber(pCSSegment);
                                    PgsCompositionState compositionState = getCompositionState(pCSSegment);
                                    boolean paletteUpdateFlag = getPaletteUpdateFlag(pCSSegment);
                                    MtkDebugLogUtil.log("PGSParserLog", str2 + compositionState, 16);
                                    j2 = pCSSegment.pts;
                                    MtkDebugLogUtil.log("PGSParserLog", str2 + compositionState + ", epoch start: " + PgsCompositionState.EPOCH_START + "\n", 16);
                                    c = pCSSegment.size >= 19 ? (char) 1 : (char) 0;
                                    if (compositionState == PgsCompositionState.INVALID) {
                                        MtkDebugLogUtil.log("PGSParserLog", "Illegal composition state at offset " + ToolBox.toHexLeftZeroPadded(j3, 8) + "\n", 16);
                                        str = str2;
                                    } else {
                                        str = str2;
                                        if (compositionState == PgsCompositionState.EPOCH_START || (compositionState == PgsCompositionState.ACQU_POINT && b == -1)) {
                                            int i20 = i16;
                                            if (this.subPictures.size() <= 0 || !(i12 == 0 || i == 0)) {
                                                i6 = i20;
                                            } else {
                                                MtkDebugLogUtil.log("PGSParserLog", "Missing PDS/ODS: last epoch is discarded\n", 16);
                                                this.subPictures.remove(this.subPictures.size() - 1);
                                                i6 = compositionNumber - 1;
                                                subPictureBD4 = this.subPictures.size() > 0 ? this.subPictures.get(this.subPictures.size() - 1) : null;
                                            }
                                            SubPictureBD subPictureBD7 = new SubPictureBD();
                                            this.subPictures.add(subPictureBD7);
                                            int i21 = i12;
                                            try {
                                                subPictureBD7.setStartTime(pCSSegment.pts);
                                                MtkDebugLogUtil.log("PGSParserLog", "#> " + this.subPictures.size() + " (" + TimeUtils.ptsToTimeStr(subPictureBD7.getStartTime()) + ")\n", 16);
                                                StringBuffer stringBuffer5 = new StringBuffer();
                                                parsePCS(pCSSegment, subPictureBD7, stringBuffer5);
                                                if (subPictureBD4 != null && subPictureBD4.getEndTime() == 0) {
                                                    subPictureBD4.setEndTime(subPictureBD7.getStartTime());
                                                }
                                                StringBuffer stringBuffer6 = new StringBuffer("PCS offset: ");
                                                stringBuffer6.append(ToolBox.toHexLeftZeroPadded(j3, 8));
                                                stringBuffer6.append(", START, size: ");
                                                stringBuffer6.append(ToolBox.toHexLeftZeroPadded(pCSSegment.size, 4));
                                                stringBuffer6.append(", composition number: ");
                                                stringBuffer6.append(compositionNumber);
                                                stringBuffer6.append(", forced: ");
                                                stringBuffer6.append(subPictureBD7.isForced());
                                                stringBuffer6.append(stringBuffer5.length() == 0 ? "\n" : ", " + ((Object) stringBuffer5) + "\n");
                                                stringBuffer6.append("PTS start: ");
                                                stringBuffer6.append(TimeUtils.ptsToTimeStr(subPictureBD7.getStartTime()));
                                                stringBuffer6.append(", screen size: ");
                                                stringBuffer6.append(subPictureBD7.getWidth());
                                                stringBuffer6.append("*");
                                                stringBuffer6.append(subPictureBD7.getHeight());
                                                stringBuffer6.append("\n");
                                                MtkDebugLogUtil.log("PGSParserLog", stringBuffer6.toString(), 16);
                                                i14 = compositionNumber;
                                                i16 = i6;
                                                subPictureBD5 = subPictureBD4;
                                                pgsCompositionState = compositionState;
                                                z = paletteUpdateFlag;
                                                b = 0;
                                                i12 = 0;
                                                i13 = 0;
                                                subPictureBD6 = null;
                                                i15 = 0;
                                                subPictureBD4 = subPictureBD7;
                                                i10 = 0;
                                            } catch (Exception e2) {
                                                e = e2;
                                                i12 = i21;
                                                i13 = i;
                                            }
                                            break;
                                        } else if (subPictureBD4 == null) {
                                            MtkDebugLogUtil.log("PGSParserLog", "Missing start of epoch at offset " + ToolBox.toHexLeftZeroPadded(j3, 8) + "\n", 16);
                                        } else {
                                            StringBuffer stringBuffer7 = new StringBuffer("PCS offset: ");
                                            stringBuffer7.append(ToolBox.toHexLeftZeroPadded(j3, 8));
                                            stringBuffer7.append(", ");
                                            int i22 = AnonymousClass1.$SwitchMap$com$google$android$exoplayer$text$pgs$PgsCompositionState[compositionState.ordinal()];
                                            if (i22 == 1) {
                                                stringBuffer7.append("CONT, ");
                                            } else if (i22 == 2) {
                                                stringBuffer7.append("ACQU, ");
                                            } else if (i22 == 3) {
                                                stringBuffer7.append("NORM, ");
                                            }
                                            stringBuffer7.append(" size: ");
                                            stringBuffer7.append(ToolBox.toHexLeftZeroPadded(pCSSegment.size, 4));
                                            stringBuffer7.append(", composition number: ");
                                            stringBuffer7.append(compositionNumber);
                                            stringBuffer7.append(", forced: ");
                                            stringBuffer7.append(subPictureBD4.isForced());
                                            StringBuffer stringBuffer8 = new StringBuffer();
                                            int i23 = i16;
                                            if (compositionNumber != i23) {
                                                SubPictureBD subPictureBD8 = new SubPictureBD(subPictureBD4);
                                                parsePCS(pCSSegment, subPictureBD4, stringBuffer8);
                                                subPictureBD6 = subPictureBD8;
                                            }
                                            if (stringBuffer8.length() > 0) {
                                                stringBuffer7.append(", ");
                                                stringBuffer7.append(stringBuffer8);
                                            }
                                            stringBuffer7.append(", pal update: ");
                                            stringBuffer7.append(paletteUpdateFlag);
                                            stringBuffer7.append("\n");
                                            stringBuffer7.append("PTS: ");
                                            stringBuffer7.append(TimeUtils.ptsToTimeStr(pCSSegment.pts));
                                            stringBuffer7.append("\n");
                                            MtkDebugLogUtil.log("PGSParserLog", stringBuffer7.toString(), 16);
                                            i16 = i23;
                                            i14 = compositionNumber;
                                            pgsCompositionState = compositionState;
                                            i10 = i10;
                                            i13 = i;
                                            z = paletteUpdateFlag;
                                        }
                                        if (this.skipHeader) {
                                            i5 = i18 + 3;
                                        } else {
                                            i5 = i18 + 13;
                                        }
                                        i11 = i5 + pCSSegment.size;
                                        size = this.buffer.getSize();
                                        str2 = str;
                                    }
                                    i14 = compositionNumber;
                                    pgsCompositionState = compositionState;
                                    z = paletteUpdateFlag;
                                    i10 = i10;
                                    i13 = i;
                                    if (this.skipHeader) {
                                        i5 = i18 + 3;
                                    } else {
                                        i5 = i18 + 13;
                                    }
                                    i11 = i5 + pCSSegment.size;
                                    size = this.buffer.getSize();
                                    str2 = str;
                                } catch (Exception e3) {
                                    e = e3;
                                }
                                break;
                            case 23:
                                StringBuffer stringBuffer9 = new StringBuffer("WDS offset: ");
                                stringBuffer9.append(ToolBox.toHexLeftZeroPadded(j3, 8));
                                stringBuffer9.append(", size: ");
                                stringBuffer9.append(ToolBox.toHexLeftZeroPadded(pCSSegment.size, 4));
                                if (subPictureBD4 != null) {
                                    parseWDS(pCSSegment, subPictureBD4);
                                    MtkDebugLogUtil.log("PGSParserLog", ((Object) stringBuffer9) + ", dim: " + subPictureBD4.getWindowWidth() + "*" + subPictureBD4.getWindowHeight() + "\n", 16);
                                } else {
                                    MtkDebugLogUtil.log("PGSParserLog", ((Object) stringBuffer9) + "\n", 16);
                                    MtkDebugLogUtil.log("PGSParserLog", "Missing PTS start -> ignored\n", 16);
                                }
                                str = str2;
                                i7 = i16;
                                i14 = i19;
                                i8 = i12;
                                i12 = i8;
                                i16 = i7;
                                i13 = i;
                                if (this.skipHeader) {
                                    i5 = i18 + 3;
                                } else {
                                    i5 = i18 + 13;
                                }
                                i11 = i5 + pCSSegment.size;
                                size = this.buffer.getSize();
                                str2 = str;
                                break;
                            default:
                                try {
                                    MtkDebugLogUtil.log("PGSParserLog", "<unknown> " + ToolBox.toHexLeftZeroPadded(pCSSegment.type, 2) + " ofs:" + ToolBox.toHexLeftZeroPadded(j3, 8) + "\n", 16);
                                    str = str2;
                                    i7 = i16;
                                    i14 = i19;
                                    i8 = i12;
                                    i12 = i8;
                                    i16 = i7;
                                    i13 = i;
                                    if (this.skipHeader) {
                                        i5 = i18 + 3;
                                    } else {
                                        i5 = i18 + 13;
                                    }
                                    i11 = i5 + pCSSegment.size;
                                    size = this.buffer.getSize();
                                    str2 = str;
                                } catch (Exception e4) {
                                    e = e4;
                                    i13 = i;
                                }
                                break;
                        }
                    } else {
                        str = str2;
                        i = i13;
                        int i24 = i16;
                        int i25 = i12;
                        try {
                            MtkDebugLogUtil.log("PGSParserLog", "END offset: " + ToolBox.toHexLeftZeroPadded(j3, 8) + "\n", 16);
                            pgsCompositionState = pgsCompositionState;
                            if (pgsCompositionState == PgsCompositionState.EPOCH_START || (pgsCompositionState == PgsCompositionState.ACQU_POINT && b == 0)) {
                                j = j2;
                                subPictureBD = subPictureBD6;
                                i2 = i25;
                                i4 = i10;
                                i3 = i;
                                if (c > 0 && i2 > i4 && i14 != i24 && subPictureBD4 != null) {
                                    subPictureBD2 = subPictureBD5;
                                    if (subPictureBD4.isMergableWith(subPictureBD2)) {
                                        this.subPictures.remove(this.subPictures.size() - 1);
                                        subPictureBD5 = this.subPictures.size() > 0 ? this.subPictures.get(this.subPictures.size() - 1) : null;
                                        MtkDebugLogUtil.log("PGSParserLog", "#< caption merged\n", 16);
                                        subPictureBD4 = subPictureBD2;
                                    }
                                    i10 = i4;
                                    subPictureBD6 = subPictureBD;
                                    i12 = i2;
                                    i13 = i3;
                                    i15 = i13;
                                    j2 = j;
                                    i16 = i14;
                                    b = -1;
                                    if (this.skipHeader) {
                                        i5 = i18 + 3;
                                    } else {
                                        i5 = i18 + 13;
                                    }
                                    i11 = i5 + pCSSegment.size;
                                    size = this.buffer.getSize();
                                    str2 = str;
                                }
                                subPictureBD5 = subPictureBD2;
                                i10 = i4;
                                subPictureBD6 = subPictureBD;
                                i12 = i2;
                                i13 = i3;
                                i15 = i13;
                                j2 = j;
                                i16 = i14;
                                b = -1;
                                if (this.skipHeader) {
                                    i5 = i18 + 3;
                                } else {
                                    i5 = i18 + 13;
                                }
                                i11 = i5 + pCSSegment.size;
                                size = this.buffer.getSize();
                                str2 = str;
                            } else {
                                if (subPictureBD4 != null) {
                                    startTime = subPictureBD4.getStartTime();
                                    j = j2;
                                    subPictureBD4.setStartTime(j);
                                } else {
                                    j = j2;
                                    startTime = 0;
                                }
                                if (c > 0) {
                                    i2 = i25;
                                    i4 = i10;
                                    if (i2 <= i4 || i14 == i24) {
                                        subPictureBD3 = subPictureBD6;
                                    } else {
                                        if (subPictureBD4 != null) {
                                            subPictureBD3 = subPictureBD6;
                                            try {
                                                if (!subPictureBD4.isMergableWith(subPictureBD3)) {
                                                }
                                            } catch (Exception e5) {
                                                e = e5;
                                                i12 = i2;
                                                i13 = i;
                                            }
                                        } else {
                                            subPictureBD3 = subPictureBD6;
                                        }
                                        if (i2 - i4 > 1 || i - i15 > 1) {
                                            MtkDebugLogUtil.log("PGSParserLog", "Multiple PDS/ODS definitions: result may be erratic\n", 16);
                                        }
                                        this.subPictures.set(this.subPictures.size() - 1, subPictureBD3);
                                        this.subPictures.add(subPictureBD4);
                                        MtkDebugLogUtil.log("PGSParserLog", "#< " + this.subPictures.size() + " (" + TimeUtils.ptsToTimeStr(subPictureBD4.getStartTime()) + ")\n", 16);
                                        i10 = i2;
                                        subPictureBD = subPictureBD3;
                                        subPictureBD5 = subPictureBD;
                                        i3 = i;
                                        subPictureBD6 = subPictureBD;
                                        i12 = i2;
                                        i13 = i3;
                                        i15 = i13;
                                        j2 = j;
                                        i16 = i14;
                                        b = -1;
                                        if (this.skipHeader) {
                                            i5 = i18 + 3;
                                        } else {
                                            i5 = i18 + 13;
                                        }
                                        i11 = i5 + pCSSegment.size;
                                        size = this.buffer.getSize();
                                        str2 = str;
                                    }
                                } else {
                                    subPictureBD3 = subPictureBD6;
                                    i2 = i25;
                                    i4 = i10;
                                }
                                if (subPictureBD4 != null) {
                                    try {
                                        subPictureBD4.setStartTime(startTime);
                                        subPictureBD4.setEndTime(j);
                                        if (subPictureBD3 != null && subPictureBD3.isForced()) {
                                            subPictureBD4.setForced(true);
                                        }
                                        i3 = i;
                                        if (i3 > i15 || z) {
                                            try {
                                                MtkDebugLogUtil.log("PGSParserLog", "Palette animation: result may be erratic\n", 16);
                                            } catch (Exception e6) {
                                                e = e6;
                                                i12 = i2;
                                                i13 = i3;
                                            }
                                        }
                                    } catch (Exception e7) {
                                        e = e7;
                                        i3 = i;
                                        i12 = i2;
                                        i13 = i3;
                                        e.printStackTrace();
                                        if (this.subPictures.size() != 0) {
                                            throw e;
                                        }
                                        MtkDebugLogUtil.log("PGSParserLog", e.getMessage() + "\n", 16);
                                        MtkDebugLogUtil.log("PGSParserLog", "Probably not all caption imported due to error.\n", 16);
                                        removeLastFrameIfInvalid(i12, i13);
                                        countForcedFrames();
                                        return;
                                    }
                                } else {
                                    i3 = i;
                                    MtkDebugLogUtil.log("PGSParserLog", "End without at least one epoch start\n", 16);
                                }
                                subPictureBD = subPictureBD3;
                            }
                            subPictureBD2 = subPictureBD5;
                            subPictureBD5 = subPictureBD2;
                            i10 = i4;
                            subPictureBD6 = subPictureBD;
                            i12 = i2;
                            i13 = i3;
                            i15 = i13;
                            j2 = j;
                            i16 = i14;
                            b = -1;
                            if (this.skipHeader) {
                                i5 = i18 + 3;
                            } else {
                                i5 = i18 + 13;
                            }
                            i11 = i5 + pCSSegment.size;
                            size = this.buffer.getSize();
                            str2 = str;
                        } catch (Exception e8) {
                            e = e8;
                            i2 = i25;
                        }
                    }
                } catch (Exception e9) {
                    e = e9;
                }
                e.printStackTrace();
                if (this.subPictures.size() != 0) {
                    throw e;
                }
                MtkDebugLogUtil.log("PGSParserLog", e.getMessage() + "\n", 16);
                MtkDebugLogUtil.log("PGSParserLog", "Probably not all caption imported due to error.\n", 16);
            }
            removeLastFrameIfInvalid(i12, i13);
            countForcedFrames();
            return;
        }
    }

    /* JADX INFO: renamed from: com.google.android.exoplayer.text.pgs.PgsParserImpl$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$exoplayer$text$pgs$PgsCompositionState;

        static {
            int[] iArr = new int[PgsCompositionState.values().length];
            $SwitchMap$com$google$android$exoplayer$text$pgs$PgsCompositionState = iArr;
            try {
                iArr[PgsCompositionState.EPOCH_CONTINUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$android$exoplayer$text$pgs$PgsCompositionState[PgsCompositionState.ACQU_POINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$android$exoplayer$text$pgs$PgsCompositionState[PgsCompositionState.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void removeLastFrameIfInvalid(int i, int i2) {
        if (this.subPictures.size() > 0) {
            if (i == 0 || i2 == 0) {
                MtkDebugLogUtil.log("PGSParserLog", "Missing PDS/ODS: last epoch is discarded\n", 16);
                List<SubPictureBD> list = this.subPictures;
                list.remove(list.size() - 1);
            }
        }
    }

    private void countForcedFrames() {
        this.forcedFrameCount = 0;
        Iterator<SubPictureBD> it = this.subPictures.iterator();
        while (it.hasNext()) {
            if (it.next().isForced()) {
                this.forcedFrameCount++;
            }
        }
        MtkDebugLogUtil.log("PGSParserLog", "\nDetected " + this.forcedFrameCount + " forced captions.\n", 16);
    }

    private PCSSegment readPCSSegment(int i) throws Exception {
        PCSSegment pCSSegment = new PCSSegment(null);
        if (this.skipHeader) {
            pCSSegment.type = this.buffer.getByte(i);
            int i2 = i + 1;
            pCSSegment.size = this.buffer.getWord(i2);
            pCSSegment.offset = i2 + 2;
            return pCSSegment;
        }
        long j = i;
        if (this.buffer.getWord(j) != 20551) {
            throw new Exception("PG missing at index " + ToolBox.toHexLeftZeroPadded(j, 8) + "\n");
        }
        int i3 = i + 2;
        pCSSegment.pts = this.buffer.getDWord(i3);
        int i4 = i3 + 4 + 4;
        pCSSegment.type = this.buffer.getByte(i4);
        int i5 = i4 + 1;
        pCSSegment.size = this.buffer.getWord(i5);
        pCSSegment.offset = i5 + 2;
        return pCSSegment;
    }

    private int getCompositionNumber(PCSSegment pCSSegment) throws Exception {
        return this.buffer.getWord(pCSSegment.offset + 5);
    }

    private PgsCompositionState getCompositionState(PCSSegment pCSSegment) throws Exception {
        MtkDebugLogUtil.log("PGSParserLog", "segment.offset: " + pCSSegment.offset + "\n", 16);
        MtkDebugLogUtil.log("PGSParserLog", "buffer: " + this.buffer.toString() + "\n", 16);
        int i = this.buffer.getByte((long) (pCSSegment.offset + 7));
        for (PgsCompositionState pgsCompositionState : PgsCompositionState.values()) {
            if (i == pgsCompositionState.getType()) {
                return pgsCompositionState;
            }
        }
        return PgsCompositionState.INVALID;
    }

    private boolean getPaletteUpdateFlag(PCSSegment pCSSegment) throws Exception {
        return this.buffer.getByte((long) (pCSSegment.offset + 8)) == 128;
    }

    private void parsePCS(PCSSegment pCSSegment, SubPictureBD subPictureBD, StringBuffer stringBuffer) throws Exception {
        ImageObject imageObject;
        int i = pCSSegment.offset;
        if (pCSSegment.size >= 4) {
            subPictureBD.setWidth(this.buffer.getWord(i));
            subPictureBD.setHeight(this.buffer.getWord(i + 2));
            int i2 = this.buffer.getByte(i + 4);
            int word = this.buffer.getWord(i + 5);
            int i3 = this.buffer.getByte(i + 9);
            if (this.buffer.getByte(i + 10) > 0) {
                int word2 = this.buffer.getWord(i + 11);
                stringBuffer.append("paletteId: ");
                stringBuffer.append(i3);
                stringBuffer.append(", objectId: ");
                stringBuffer.append(word2);
                if (word2 >= subPictureBD.getImageObjectList().size()) {
                    imageObject = new ImageObject();
                    subPictureBD.getImageObjectList().add(imageObject);
                } else {
                    imageObject = subPictureBD.getImageObject(word2);
                }
                imageObject.setPaletteID(i3);
                subPictureBD.setObjectID(word2);
                if (pCSSegment.size >= 19) {
                    subPictureBD.setType(i2);
                    int i4 = this.buffer.getByte(i + 14);
                    subPictureBD.setCompositionNumber(word);
                    subPictureBD.setForced((i4 & 64) == 64);
                    imageObject.setXOffset(this.buffer.getWord(i + 15));
                    imageObject.setYOffset(this.buffer.getWord(i + 17));
                }
            }
        }
    }

    private void parseWDS(PCSSegment pCSSegment, SubPictureBD subPictureBD) throws Exception {
        int i = pCSSegment.offset;
        if (pCSSegment.size >= 10) {
            subPictureBD.setXWindowOffset(this.buffer.getWord(i + 2));
            subPictureBD.setYWindowOffset(this.buffer.getWord(i + 4));
            subPictureBD.setWindowWidth(this.buffer.getWord(i + 6));
            subPictureBD.setWindowHeight(this.buffer.getWord(i + 8));
        }
    }

    private boolean parseODS(PCSSegment pCSSegment, SubPictureBD subPictureBD, StringBuffer stringBuffer) throws Exception {
        ImageObject imageObject;
        int i = pCSSegment.offset;
        int word = this.buffer.getWord(i);
        int i2 = this.buffer.getByte(i + 1);
        int i3 = this.buffer.getByte(i + 3);
        boolean z = (i3 & 128) == 128;
        boolean z2 = (i3 & 64) == 64;
        if (word >= subPictureBD.getImageObjectList().size()) {
            ImageObject imageObject2 = new ImageObject();
            subPictureBD.getImageObjectList().add(imageObject2);
            imageObject = imageObject2;
        } else {
            imageObject = subPictureBD.getImageObject(word);
        }
        if (imageObject.getFragmentList().isEmpty() || z) {
            int word2 = this.buffer.getWord(i + 7);
            int word3 = this.buffer.getWord(i + 9);
            if (word2 <= subPictureBD.getWidth() && word3 <= subPictureBD.getHeight()) {
                int i4 = i + 11;
                boolean z3 = z2;
                ImageObjectFragment imageObjectFragment = new ImageObjectFragment(i4, pCSSegment.size - (i4 - pCSSegment.offset));
                imageObject.getFragmentList().add(imageObjectFragment);
                imageObject.setBufferSize(imageObjectFragment.getImagePacketSize());
                imageObject.setHeight(word3);
                imageObject.setWidth(word2);
                stringBuffer.append("ID: ");
                stringBuffer.append(word);
                stringBuffer.append(", update: ");
                stringBuffer.append(i2);
                stringBuffer.append(", seq: ");
                stringBuffer.append(z ? "first" : "");
                stringBuffer.append((z && z3) ? "/" : "");
                stringBuffer.append(z3 ? "last" : "");
                return true;
            }
            MtkDebugLogUtil.log("PGSParserLog", "Invalid image size - ignored\n", 16);
            return false;
        }
        int i5 = i + 4;
        ImageObjectFragment imageObjectFragment2 = new ImageObjectFragment(i5, pCSSegment.size - (i5 - pCSSegment.offset));
        imageObject.getFragmentList().add(imageObjectFragment2);
        imageObject.setBufferSize(imageObject.getBufferSize() + imageObjectFragment2.getImagePacketSize());
        stringBuffer.append("ID: ");
        stringBuffer.append(word);
        stringBuffer.append(", update: ");
        stringBuffer.append(i2);
        stringBuffer.append(", seq: ");
        stringBuffer.append("");
        stringBuffer.append("");
        stringBuffer.append(z2 ? "last" : "");
        return false;
    }

    private int parsePDS(PCSSegment pCSSegment, SubPictureBD subPictureBD, StringBuffer stringBuffer) throws Exception {
        int i = pCSSegment.offset;
        long j = i;
        int i2 = this.buffer.getByte(j);
        int i3 = this.buffer.getByte(i + 1);
        if (i2 > 7) {
            stringBuffer.append("Illegal palette id at offset ");
            stringBuffer.append(ToolBox.toHexLeftZeroPadded(j, 8));
            return -1;
        }
        PaletteInfo paletteInfo = new PaletteInfo(i + 2, (pCSSegment.size - 2) / 5);
        subPictureBD.getPalettes().get(i2).add(paletteInfo);
        stringBuffer.append("ID: ");
        stringBuffer.append(i2);
        stringBuffer.append(", update: ");
        stringBuffer.append(i3);
        stringBuffer.append(", ");
        stringBuffer.append(paletteInfo.getPaletteSize());
        stringBuffer.append(" entries");
        return paletteInfo.getPaletteSize();
    }

    public FileBuffer getBuffer() {
        return this.buffer;
    }

    public List<SubPictureBD> getSubPictures() {
        return this.subPictures;
    }

    public int getForcedFrameCount() {
        return this.forcedFrameCount;
    }
}
