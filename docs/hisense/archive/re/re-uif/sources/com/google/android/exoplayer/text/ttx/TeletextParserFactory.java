package com.google.android.exoplayer.text.ttx;

import com.google.android.exoplayer.text.ByteBuffer;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TeletextParserFactory {
    String packetTxt;
    private ByteBuffer source;

    public TeletextParserFactory(byte[] bArr) throws Exception {
        this.source = new ByteBuffer(bArr);
        internalParser();
    }

    private void internalParser() throws Exception {
        PacketParser packetParser = new PacketParser();
        packetParser.parserBuffer(this.source, 0L);
        this.packetTxt = packetParser.packetTxt;
    }

    class PacketParser {
        int id;
        String packetTxt;

        PacketParser() {
        }

        public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
            MtkDebugLogUtil.log("PacketParser", "start PESPacketData offset = " + j, 16);
            long j2 = 1 + j;
            try {
                int i = byteBuffer.getByte(j);
                this.id = i;
                TeletextParserFactory.this.checkAround(16, 31, i & 255);
                Page page = TeletextParserFactory.this.new Page();
                long j3 = page.parserBuffer(byteBuffer, j2);
                this.packetTxt = page.txt;
                MtkDebugLogUtil.log("PacketParser", "page loop  offset = " + j3 + ", packetTxt: " + this.packetTxt, 16);
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(this);
                MtkDebugLogUtil.log("PacketParser", sb.toString(), 16);
                return j3;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkEqual(int i, int i2) throws Exception {
        if (i2 == i) {
            return;
        }
        throw new Exception("except = " + i + " but val = " + (i2 & 255));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAround(int i, int i2, int i3) throws Exception {
        if (i3 < i || i3 > i2) {
            throw new Exception("exceptMin = " + i + " exceptMax : " + i2 + " but val = " + (i3 & 255));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIn(int[] iArr, int i) throws Exception {
        for (int i2 : iArr) {
            if (i2 == i) {
                return;
            }
        }
        throw new Exception("excepts = " + Arrays.toString(iArr) + " but val = " + (i & 255));
    }

    class Page {
        int frammingCode;
        byte frammingCodeType;
        byte magNum;
        byte packetNum;
        int unitId;
        int unitLen;
        final int[] UNIT_IDS = {2, 3};
        String txt = "";
        boolean isSubtitle = false;

        public Page() {
        }

        public Page(Page page) {
            this.magNum = page.magNum;
            this.packetNum = page.packetNum;
            this.frammingCodeType = page.frammingCodeType;
        }

        public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
            MtkDebugLogUtil.log("Page", "page parserBuffer off = " + j, 16);
            long j2 = j;
            while (byteBuffer.getSize() > j2) {
                try {
                    long j3 = j2 + 1;
                    int i = byteBuffer.getByte(j2);
                    this.unitId = i;
                    TeletextParserFactory.this.checkIn(this.UNIT_IDS, i);
                    long j4 = j3 + 1;
                    int i2 = byteBuffer.getByte(j3);
                    this.unitLen = i2;
                    TeletextParserFactory.this.checkEqual(44, i2);
                    if (byteBuffer.getSize() < ((long) this.unitLen) + j4) {
                        return j4;
                    }
                    long j5 = j4 + 1;
                    long j6 = j5 + 1;
                    this.frammingCode = byteBuffer.getByte(j5);
                    MtkDebugLogUtil.log("Page", " frammingCode = " + this.frammingCode, 16);
                    int i3 = this.frammingCode;
                    if (i3 == 27) {
                        this.frammingCodeType = (byte) 2;
                    } else if (i3 == 39) {
                        this.frammingCodeType = (byte) 1;
                    } else if (i3 == 216) {
                        this.frammingCodeType = (byte) 3;
                    } else if (i3 == 228) {
                        this.frammingCodeType = (byte) 0;
                    } else {
                        j2 = ((long) (this.unitLen - 2)) + j6;
                    }
                    long j7 = j6 + 1;
                    byte bHamming84Data = FrammingCodeTableManager.hamming84Data(this.frammingCodeType, byteBuffer.getByte(j6));
                    this.magNum = (byte) (bHamming84Data & 7);
                    this.packetNum = (byte) ((bHamming84Data >> 3) & 1);
                    long j8 = 1 + j7;
                    this.packetNum = (byte) (this.packetNum + ((FrammingCodeTableManager.hamming84Data(this.frammingCodeType, byteBuffer.getByte(j7)) & 15) << 1));
                    MtkDebugLogUtil.log("Page", "this = " + this, 16);
                    if (this.packetNum == 0) {
                        PageHeader pageHeader = new PageHeader(TeletextParserFactory.this, this);
                        j2 = pageHeader.parserBuffer(byteBuffer, j8);
                        if (pageHeader.isSubtitle()) {
                            this.isSubtitle = true;
                        }
                    } else if (this.packetNum >= 1 && this.packetNum <= 23) {
                        PageDisplay pageDisplay = new PageDisplay(TeletextParserFactory.this, this);
                        j2 = pageDisplay.parserBuffer(byteBuffer, j8);
                        if (this.isSubtitle) {
                            String strTrim = new String(pageDisplay.datas).trim();
                            MtkDebugLogUtil.log("Page", "tmptxt: " + strTrim + ", txt: " + this.txt, 16);
                            if (!strTrim.isEmpty()) {
                                if (!this.txt.isEmpty()) {
                                    this.txt += "\n";
                                }
                                this.txt += new String(pageDisplay.datas);
                                MtkDebugLogUtil.log("Page", "txt: " + this.txt, 16);
                            }
                        }
                    } else {
                        j2 = j8 + ((long) (this.unitLen - 4));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return j;
                }
            }
            return j2;
        }

        public String toString() {
            return "Page [unitId=Integer.toHexString(" + this.unitId + "), unitLen=Integer.toHexString(" + this.unitLen + "), frammingCode=Integer.toHexString(" + this.frammingCode + "), magNum=Integer.toHexString(" + ((int) this.magNum) + "), packetNum=Integer.toHexString(" + ((int) this.packetNum) + "), frammingCodeType=Integer.toHexString(" + ((int) this.frammingCodeType) + "]";
        }
    }

    class PageHeader extends Page {
        byte c11_14;
        byte c4;
        byte c5_6;
        byte c7_10;
        byte[] datas;
        byte pageTens;
        byte pageUnits;
        byte subcodeS1;
        byte subcodeS2;
        byte subcodeS3;
        byte subcodeS4;

        public PageHeader(TeletextParserFactory teletextParserFactory, Page page) {
            super(page);
            this.datas = new byte[32];
        }

        public boolean isSubtitle() {
            return this.c5_6 == 2;
        }

        @Override // com.google.android.exoplayer.text.ttx.TeletextParserFactory.Page
        public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
            MtkDebugLogUtil.log("PageHeader", "page parserBuffer off = " + j, 16);
            try {
                long j2 = j + 1;
                this.pageUnits = FrammingCodeTableManager.hamming84Data(this.frammingCodeType, byteBuffer.getByte(j));
                long j3 = j2 + 1;
                this.pageTens = FrammingCodeTableManager.hamming84Data(this.frammingCodeType, byteBuffer.getByte(j2));
                long j4 = j3 + 1;
                this.subcodeS1 = FrammingCodeTableManager.hamming84Data(this.frammingCodeType, byteBuffer.getByte(j3));
                long j5 = j4 + 1;
                byte bHamming84Data = FrammingCodeTableManager.hamming84Data(this.frammingCodeType, byteBuffer.getByte(j4));
                this.subcodeS2 = (byte) (bHamming84Data & 7);
                this.c4 = (byte) ((bHamming84Data >> 3) & 1);
                long j6 = j5 + 1;
                this.subcodeS3 = FrammingCodeTableManager.hamming84Data(this.frammingCodeType, byteBuffer.getByte(j5));
                long j7 = j6 + 1;
                byte bHamming84Data2 = FrammingCodeTableManager.hamming84Data(this.frammingCodeType, byteBuffer.getByte(j6));
                this.subcodeS4 = (byte) (bHamming84Data2 & 3);
                this.c5_6 = (byte) ((bHamming84Data2 >> 2) & 3);
                long j8 = j7 + 1;
                this.c7_10 = FrammingCodeTableManager.hamming84Data(this.frammingCodeType, byteBuffer.getByte(j7));
                long j9 = j8 + 1;
                this.c11_14 = FrammingCodeTableManager.hamming84Data(this.frammingCodeType, byteBuffer.getByte(j8));
                MtkDebugLogUtil.log("PageHeader", " this = " + this, 16);
                int i = 0;
                while (i < 32) {
                    long j10 = j9 + 1;
                    this.datas[i] = FrammingCodeTableManager.oddParityData(this.frammingCodeType, byteBuffer.getByte(j9));
                    MtkDebugLogUtil.log("PageHeader", "head data " + i + " = " + ((int) this.datas[i]), 16);
                    i++;
                    j9 = j10;
                }
                return j9;
            } catch (Exception unused) {
                return j;
            }
        }

        @Override // com.google.android.exoplayer.text.ttx.TeletextParserFactory.Page
        public String toString() {
            return "PageHeader [pageUnits= " + Integer.toHexString(this.pageUnits) + ", pageTens=" + Integer.toHexString(this.pageTens) + ", subcodeS1=" + Integer.toHexString(this.subcodeS1) + ", subcodeS2=" + Integer.toHexString(this.subcodeS2) + ", subcodeS3=" + Integer.toHexString(this.subcodeS3) + ", subcodeS4=" + Integer.toHexString(this.subcodeS4) + ", C4=" + Integer.toHexString(this.c4) + ", C5_6=" + Integer.toHexString(this.c5_6) + ", C7_10=" + Integer.toHexString(this.c7_10) + ", C11_14=" + Integer.toHexString(this.c11_14) + "]";
        }
    }

    class PageDisplay extends Page {
        byte[] datas;

        public PageDisplay(TeletextParserFactory teletextParserFactory, Page page) {
            super(page);
            this.datas = new byte[40];
        }

        @Override // com.google.android.exoplayer.text.ttx.TeletextParserFactory.Page
        public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
            int i = 0;
            long j2 = j;
            while (i < 40) {
                try {
                    byte[] bArr = this.datas;
                    long j3 = 1 + j2;
                    byte bOddParityData = FrammingCodeTableManager.oddParityData(this.frammingCodeType, byteBuffer.getByte(j2));
                    if (bOddParityData < 32) {
                        bOddParityData = 32;
                    }
                    bArr[i] = bOddParityData;
                    MtkDebugLogUtil.log("PageDisplay", "display page data " + i + " = " + ((int) this.datas[i]), 16);
                    i++;
                    j2 = j3;
                } catch (Exception unused) {
                    return j;
                }
            }
            MtkDebugLogUtil.log("PageDisplay", "display page txt= " + new String(this.datas), 16);
            return j2;
        }
    }
}
