package com.google.android.exoplayer.text.dvb;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.google.android.exoplayer.text.ByteBuffer;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DvbParserFactory {
    Bitmap cacheBmp;
    private ByteBuffer source;
    int timeOff;

    interface ParserBuffer {
        long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception;
    }

    public DvbParserFactory(byte[] bArr) {
        try {
            this.source = new ByteBuffer(bArr);
            internalParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class PESPacketData implements ParserBuffer {
        int dataId;
        int endPESDataMarker;
        SubtitlingSegment segment;
        int subtileStreamId;

        PESPacketData() {
        }

        public Bitmap getImage() {
            return this.segment.getBitmap();
        }

        public int getTimeOffset() {
            return this.segment.getTimeOffset();
        }

        @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
        public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
            long j2 = j + 1;
            try {
                int i = byteBuffer.getByte(j);
                this.dataId = i;
                DvbParserFactory.this.checkEqual(32, i);
                long j3 = j2 + 1;
                int i2 = byteBuffer.getByte(j2);
                this.subtileStreamId = i2;
                DvbParserFactory.this.checkEqual(0, i2);
                SubtitlingSegment subtitlingSegment = DvbParserFactory.this.new SubtitlingSegment();
                this.segment = subtitlingSegment;
                long j4 = subtitlingSegment.parserBuffer(byteBuffer, j3);
                long j5 = 1 + j4;
                int i3 = byteBuffer.getByte(j4) & 63;
                this.endPESDataMarker = i3;
                DvbParserFactory.this.checkEqual(63, i3);
                MtkDebugLogUtil.log("PESPacketData", "" + this, 16);
                return j5;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

        public String toString() {
            return "PESPacketData [dataId=" + this.dataId + ", subtileStreamId=" + this.subtileStreamId + ", endPESDataMarker=" + this.endPESDataMarker + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkEqual(int i, int i2) {
        if (i2 == i) {
            return;
        }
        throw new RuntimeException("except = " + i + " but val = " + i2);
    }

    class SubtitlingSegment implements ParserBuffer {
        List<CLUTDefinitionSegment> clutdsList;
        DisplayDefinitionSegment dds;
        List<ObjectDataSegment> odsList;
        int pageId;
        List<PageCompositionSegment> pcsList;
        List<RegionCompositionSegment> rcsList;
        int segmentLen;
        int segmentType;
        int syncByte;

        public SubtitlingSegment() {
        }

        public SubtitlingSegment(SubtitlingSegment subtitlingSegment) {
            this.syncByte = subtitlingSegment.syncByte;
            this.segmentType = subtitlingSegment.segmentType;
            this.pageId = subtitlingSegment.pageId;
            this.segmentLen = subtitlingSegment.segmentLen;
        }

        public Bitmap getBitmap() {
            if (this.dds == null) {
                this.dds = new DisplayDefinitionSegment(DvbParserFactory.this);
            }
            MtkDebugLogUtil.log("SubtitlingSegment", "getBitmap  ~~", 16);
            return this.dds.getBitmap(this);
        }

        public int getTimeOffset() {
            List<PageCompositionSegment> list = this.pcsList;
            if (list == null || list.size() <= 0) {
                return 1;
            }
            return this.pcsList.get(0).pageTimeout;
        }

        @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
        public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
            boolean z = false;
            while (!z) {
                long j2 = j + 1;
                try {
                    int i = byteBuffer.getByte(j);
                    this.syncByte = i;
                    DvbParserFactory.this.checkEqual(15, i);
                    long j3 = 1 + j2;
                    this.segmentType = byteBuffer.getByte(j2);
                    this.pageId = byteBuffer.getWord(j3);
                    long j4 = j3 + 2;
                    this.segmentLen = byteBuffer.getWord(j4);
                    long j5 = j4 + 2;
                    MtkDebugLogUtil.log("SubtitlingSegment", "segmentType = " + this.segmentType + " pageId = " + this.pageId + " segmentLen = " + this.segmentLen, 16);
                    int i2 = this.segmentType;
                    if (i2 == 128) {
                        new EndOfDisplaySegment(DvbParserFactory.this, this).parserBuffer(byteBuffer, j5);
                        z = true;
                    } else if (i2 != 255) {
                        switch (i2) {
                            case 16:
                                PageCompositionSegment pageCompositionSegment = new PageCompositionSegment(DvbParserFactory.this, this);
                                j5 = pageCompositionSegment.parserBuffer(byteBuffer, j5);
                                if (this.pcsList == null) {
                                    this.pcsList = new ArrayList();
                                }
                                this.pcsList.add(pageCompositionSegment);
                                break;
                            case 17:
                                RegionCompositionSegment regionCompositionSegment = new RegionCompositionSegment(DvbParserFactory.this, this);
                                j5 = regionCompositionSegment.parserBuffer(byteBuffer, j5);
                                if (this.rcsList == null) {
                                    this.rcsList = new ArrayList();
                                }
                                this.rcsList.add(regionCompositionSegment);
                                break;
                            case 18:
                                CLUTDefinitionSegment cLUTDefinitionSegment = new CLUTDefinitionSegment(DvbParserFactory.this, this);
                                j5 = cLUTDefinitionSegment.parserBuffer(byteBuffer, j5);
                                if (this.clutdsList == null) {
                                    this.clutdsList = new ArrayList();
                                }
                                this.clutdsList.add(cLUTDefinitionSegment);
                                break;
                            case 19:
                                ObjectDataSegment objectDataSegment = new ObjectDataSegment(DvbParserFactory.this, this);
                                j5 = objectDataSegment.parserBuffer(byteBuffer, j5);
                                if (this.odsList == null) {
                                    this.odsList = new ArrayList();
                                }
                                this.odsList.add(objectDataSegment);
                                break;
                            case 20:
                                DisplayDefinitionSegment displayDefinitionSegment = new DisplayDefinitionSegment(DvbParserFactory.this, this);
                                this.dds = displayDefinitionSegment;
                                j5 = displayDefinitionSegment.parserBuffer(byteBuffer, j5);
                                break;
                            default:
                                if (this.segmentType >= 129 && this.segmentType <= 239) {
                                    MtkDebugLogUtil.log("SubtitlingSegment", "PRIVATE_DATA segmentType = " + this.segmentType, 16);
                                } else {
                                    MtkDebugLogUtil.log("SubtitlingSegment", "RESERVED FOR FUTURE USE segmentType = " + this.segmentType, 16);
                                }
                                break;
                        }
                    } else {
                        MtkDebugLogUtil.log("SubtitlingSegment", "STUFFING = 255", 16);
                    }
                    MtkDebugLogUtil.log("SubtitlingSegment", "" + this, 16);
                    j = j5;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            }
            return j;
        }

        public String toString() {
            return "SubtitlingSegment [syncByte=" + this.syncByte + ", segmentType=" + this.segmentType + ", pageId=" + this.pageId + ", segmentLen=" + this.segmentLen + "]";
        }
    }

    class DisplayDefinitionSegment extends SubtitlingSegment implements ParserBuffer {
        Bitmap bmp;
        int ddsVersionNum;
        int displayHeight;
        int displayWidth;
        int displayWindowFlag;
        int displayWindowHPMax;
        int displayWindowHPMin;
        int displayWindowVPMax;
        int displayWindowVPMin;
        boolean isObj;

        public DisplayDefinitionSegment(DvbParserFactory dvbParserFactory) {
            super();
            this.isObj = false;
            this.displayWidth = 720;
            this.displayHeight = 576;
            this.displayWindowFlag = 0;
            this.displayWindowHPMin = 0;
            this.displayWindowHPMax = 720;
            this.displayWindowVPMin = 0;
            this.displayWindowVPMax = 576;
        }

        public DisplayDefinitionSegment(DvbParserFactory dvbParserFactory, SubtitlingSegment subtitlingSegment) {
            super(subtitlingSegment);
            this.isObj = false;
        }

        public Bitmap getBitmap(SubtitlingSegment subtitlingSegment) {
            if (this.bmp == null) {
                this.bmp = Bitmap.createBitmap(this.displayWidth, this.displayHeight, Bitmap.Config.ARGB_8888);
                draw(subtitlingSegment, new Canvas(this.bmp));
            }
            if (this.isObj) {
                return this.bmp;
            }
            return null;
        }

        public void draw(SubtitlingSegment subtitlingSegment, Canvas canvas) {
            List<PageCompositionSegment> list = subtitlingSegment.pcsList;
            MtkDebugLogUtil.log("DisplayDefinitionSegment", "getBitmap displayWidth = " + this.displayWidth + " displayHeight = " + this.displayHeight, 16);
            if (this.displayWindowFlag == 1) {
                canvas.clipRect(new Rect(this.displayWindowHPMin, this.displayWindowVPMin, this.displayWindowHPMax, this.displayWindowVPMax));
            }
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<PageCompositionSegment> it = list.iterator();
            while (it.hasNext()) {
                it.next().draw(subtitlingSegment, canvas);
            }
        }

        @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.SubtitlingSegment, com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
        public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
            long j2 = 1 + j;
            try {
                int i = byteBuffer.getByte(j);
                this.ddsVersionNum = (i >> 4) & 15;
                this.displayWindowFlag = (i >> 3) & 1;
                this.displayWidth = byteBuffer.getWord(j2);
                long j3 = j2 + 2;
                this.displayHeight = byteBuffer.getWord(j3);
                long j4 = j3 + 2;
                if (this.displayWindowFlag == 1) {
                    this.displayWindowHPMin = byteBuffer.getWord(j4);
                    long j5 = j4 + 2;
                    this.displayWindowHPMax = byteBuffer.getWord(j5);
                    long j6 = j5 + 2;
                    this.displayWindowVPMin = byteBuffer.getWord(j6);
                    long j7 = j6 + 2;
                    this.displayWindowVPMax = byteBuffer.getWord(j7);
                    j4 = j7 + 2;
                }
                MtkDebugLogUtil.log("DisplayDefinitionSegment", "" + this, 16);
                return j4;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

        @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.SubtitlingSegment
        public String toString() {
            return "DisplayDefinitionSegment [ddsVersionNum=" + this.ddsVersionNum + ", displayWindowFlag=" + this.displayWindowFlag + ", displayWidth=" + this.displayWidth + ", displayHeight=" + this.displayHeight + ", displayWindowHPMin=" + this.displayWindowHPMin + ", displayWindowHPMax=" + this.displayWindowHPMax + ", displayWindowVPMin=" + this.displayWindowVPMin + ", displayWindowVPMax=" + this.displayWindowVPMax + "]";
        }
    }

    class PageCompositionSegment extends SubtitlingSegment implements ParserBuffer {
        int pageState;
        int pageTimeout;
        int pageVersionNum;
        List<RegionTable> regionList;

        public void draw(SubtitlingSegment subtitlingSegment, Canvas canvas) {
            List<RegionCompositionSegment> list = subtitlingSegment.rcsList;
            List<RegionTable> list2 = this.regionList;
            if (list2 == null || list2.size() <= 0) {
                return;
            }
            for (RegionTable regionTable : this.regionList) {
                if (list != null && list.size() > 0) {
                    for (RegionCompositionSegment regionCompositionSegment : list) {
                        if (regionCompositionSegment.regionId == regionTable.regionId) {
                            try {
                                canvas.save();
                                MtkDebugLogUtil.log("PageCompositionSegment", "getBitmap regionId = " + regionCompositionSegment.regionId + " region.regionHorizontalAddress = " + regionTable.regionHorizontalAddress + " region.regionVerticalAddress = " + regionTable.regionVerticalAddress, 16);
                                canvas.translate((float) regionTable.regionHorizontalAddress, (float) regionTable.regionVerticalAddress);
                                regionCompositionSegment.draw(subtitlingSegment, canvas);
                                canvas.restore();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        public PageCompositionSegment(DvbParserFactory dvbParserFactory, SubtitlingSegment subtitlingSegment) {
            super(subtitlingSegment);
        }

        @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.SubtitlingSegment, com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
        public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
            long j2 = j + 1;
            try {
                this.pageTimeout = byteBuffer.getByte(j);
                long j3 = 1 + j2;
                int i = byteBuffer.getByte(j2);
                this.pageVersionNum = (i >> 4) & 15;
                this.pageState = (i >> 2) & 3;
                int i2 = this.segmentLen - 2;
                this.segmentLen = i2;
                int i3 = i2 / 6;
                MtkDebugLogUtil.log("PageCompositionSegment", "PageCompositionSegment segmentLen =" + this.segmentLen + " segSize = " + i3, 16);
                if (i3 > 0) {
                    this.regionList = new ArrayList();
                    for (int i4 = 0; i4 < i3; i4++) {
                        RegionTable regionTable = new RegionTable(this);
                        j3 = regionTable.parserBuffer(byteBuffer, j3);
                        this.regionList.add(regionTable);
                    }
                }
                MtkDebugLogUtil.log("PageCompositionSegment", "" + this, 16);
                return j3;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

        @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.SubtitlingSegment
        public String toString() {
            return "PageCompositionSegment [pageTimeout=" + this.pageTimeout + ", pageVersionNum=" + this.pageVersionNum + ", pageState=" + this.pageState + "]";
        }

        class RegionTable implements ParserBuffer {
            int regionHorizontalAddress;
            int regionId;
            int regionVerticalAddress;
            int reserved;

            RegionTable(PageCompositionSegment pageCompositionSegment) {
            }

            @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
            public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
                long j2 = j + 1;
                try {
                    this.regionId = byteBuffer.getByte(j);
                    long j3 = 1 + j2;
                    this.reserved = byteBuffer.getByte(j2);
                    this.regionHorizontalAddress = byteBuffer.getWord(j3);
                    long j4 = j3 + 2;
                    this.regionVerticalAddress = byteBuffer.getWord(j4);
                    long j5 = j4 + 2;
                    MtkDebugLogUtil.log("Segment", "" + this, 16);
                    return j5;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            }

            public String toString() {
                return "Segment [regionId=" + this.regionId + ", reserved=" + this.reserved + ", regionHorizontalAddress=" + this.regionHorizontalAddress + ", regionVerticalAddress=" + this.regionVerticalAddress + "]";
            }
        }
    }

    class RegionCompositionSegment extends SubtitlingSegment implements ParserBuffer {
        int clutId;
        List<ObjectTable> objList;
        int region2bitPixel;
        int region4bitPixel;
        int region8bitPixel;
        int regionDepth;
        int regionFillFlag;
        int regionHeight;
        int regionId;
        int regionLevelOfCompatibility;
        int regionVerNum;
        int regionWidth;

        public void draw(SubtitlingSegment subtitlingSegment, Canvas canvas) {
            try {
                canvas.save();
                MtkDebugLogUtil.log("RegionCompositionSegment", "draw clipRect regionWidth = " + this.regionWidth + " regionHeight = " + this.regionHeight, 16);
                canvas.clipRect(new Rect(0, 0, this.regionWidth, this.regionHeight));
                List<ObjectDataSegment> list = subtitlingSegment.odsList;
                int[] colorTable = null;
                if (subtitlingSegment.clutdsList != null && subtitlingSegment.clutdsList.size() > 0) {
                    for (CLUTDefinitionSegment cLUTDefinitionSegment : subtitlingSegment.clutdsList) {
                        if (cLUTDefinitionSegment.clutId == this.clutId) {
                            colorTable = cLUTDefinitionSegment.getColorTable(this.regionDepth);
                        }
                    }
                }
                if (colorTable != null && this.objList != null && this.objList.size() > 0) {
                    for (ObjectTable objectTable : this.objList) {
                        if (list != null && list.size() > 0) {
                            for (ObjectDataSegment objectDataSegment : list) {
                                if (objectDataSegment.objectId == objectTable.objectId) {
                                    try {
                                        canvas.save();
                                        MtkDebugLogUtil.log("RegionCompositionSegment", "draw obj.objectHPosition = " + objectTable.objectHPosition + " obj.objectVPosition = " + objectTable.objectVPosition, 16);
                                        canvas.translate((float) objectTable.objectHPosition, (float) objectTable.objectVPosition);
                                        objectDataSegment.draw(subtitlingSegment, colorTable, canvas);
                                        canvas.restore();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
                canvas.restore();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public RegionCompositionSegment(DvbParserFactory dvbParserFactory, SubtitlingSegment subtitlingSegment) {
            super(subtitlingSegment);
        }

        @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.SubtitlingSegment, com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
        public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
            long j2 = j + 1;
            try {
                this.regionId = byteBuffer.getByte(j);
                long j3 = j2 + 1;
                int i = byteBuffer.getByte(j2);
                this.regionVerNum = (i >> 4) & 15;
                this.regionFillFlag = (i >> 3) & 1;
                this.regionWidth = byteBuffer.getWord(j3);
                long j4 = j3 + 2;
                this.regionHeight = byteBuffer.getWord(j4);
                long j5 = j4 + 2;
                long j6 = j5 + 1;
                int i2 = byteBuffer.getByte(j5);
                this.regionLevelOfCompatibility = (i2 >> 5) & 7;
                this.regionDepth = (i2 >> 2) & 7;
                long j7 = j6 + 1;
                this.clutId = byteBuffer.getByte(j6);
                long j8 = j7 + 1;
                this.region8bitPixel = byteBuffer.getByte(j7);
                long j9 = 1 + j8;
                int i3 = byteBuffer.getByte(j8);
                this.region4bitPixel = (i3 >> 4) & 15;
                this.region2bitPixel = (i3 >> 2) & 3;
                int i4 = this.segmentLen - 10;
                this.segmentLen = i4;
                int i5 = i4 / 6;
                MtkDebugLogUtil.log("RegionCompositionSegment", " segmentLen = " + this.segmentLen + " objectMaxSize = " + i5, 16);
                if (i5 > 0) {
                    this.objList = new ArrayList();
                    int i6 = 0;
                    long j10 = j9;
                    int i7 = 0;
                    while (i6 < this.segmentLen && i7 < i5) {
                        ObjectTable objectTable = new ObjectTable(this);
                        long j11 = objectTable.parserBuffer(byteBuffer, j10);
                        this.objList.add(objectTable);
                        i6 = (int) (((long) i6) + (j11 - j10));
                        i7++;
                        j10 = j11;
                    }
                    j9 = j10;
                }
                MtkDebugLogUtil.log("RegionCompositionSegment", "" + this, 16);
                return j9;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

        class ObjectTable implements ParserBuffer {
            int backPixel;
            int forePixel;
            int objectHPosition;
            int objectId;
            int objectProviderFlag;
            int objectType;
            int objectVPosition;

            ObjectTable(RegionCompositionSegment regionCompositionSegment) {
            }

            @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
            public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
                try {
                    this.objectId = byteBuffer.getWord(j);
                    long j2 = j + 2;
                    int word = byteBuffer.getWord(j2);
                    long j3 = j2 + 2;
                    this.objectType = (word >> 14) & 3;
                    this.objectProviderFlag = (word >> 12) & 3;
                    this.objectHPosition = word & 4095;
                    int word2 = byteBuffer.getWord(j3);
                    long j4 = j3 + 2;
                    this.objectVPosition = word2 & 4095;
                    if ((this.objectType == 1) | (this.objectType == 2)) {
                        long j5 = j4 + 1;
                        this.forePixel = byteBuffer.getByte(j4);
                        j4 = j5 + 1;
                        this.backPixel = byteBuffer.getByte(j5);
                    }
                    MtkDebugLogUtil.log("DvbObject", "" + this, 16);
                    return j4;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            }

            public String toString() {
                return "DvbObject [objectId=" + this.objectId + ", objectType=" + this.objectType + ", objectProviderFlag=" + this.objectProviderFlag + ", objectHPosition=" + this.objectHPosition + ", objectVPosition=" + this.objectVPosition + ", forePixel=" + this.forePixel + ", backPixel=" + this.backPixel + "]";
            }
        }

        @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.SubtitlingSegment
        public String toString() {
            return "RegionCompositionSegment [regionId=" + this.regionId + ", regionVerNum=" + this.regionVerNum + ", regionFillFlag=" + this.regionFillFlag + ", regionWidth=" + this.regionWidth + ", regionHeight=" + this.regionHeight + ", regionLevelOfCompatibility=" + this.regionLevelOfCompatibility + ", regionDepth=" + this.regionDepth + ", clutId=" + this.clutId + ", region8bitPixel=" + this.region8bitPixel + ", region4bitPixel=" + this.region4bitPixel + ", region2bitPixel=" + this.region2bitPixel + "]";
        }
    }

    class CLUTDefinitionSegment extends SubtitlingSegment implements ParserBuffer {
        int clutId;
        List<CLUTTable> clutList;
        int clutVerNum;
        int[] color2Table;
        int[] color4Table;
        int[] color8Table;

        public CLUTDefinitionSegment(DvbParserFactory dvbParserFactory, SubtitlingSegment subtitlingSegment) {
            super(subtitlingSegment);
        }

        public int[] getColorTable(int i) {
            if (i == 1) {
                return this.color2Table;
            }
            if (i == 2) {
                return this.color4Table;
            }
            if (i == 3) {
                return this.color8Table;
            }
            return this.color2Table;
        }

        @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.SubtitlingSegment, com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
        public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
            long j2 = j + 1;
            try {
                this.clutId = byteBuffer.getByte(j);
                long j3 = 1 + j2;
                this.clutVerNum = (byteBuffer.getByte(j2) >> 4) & 15;
                int i = 2;
                int i2 = this.segmentLen - 2;
                this.segmentLen = i2;
                int i3 = i2 / 4;
                MtkDebugLogUtil.log("CLUTDefinitionSegment", " segmentLen = " + this.segmentLen + " clutMaxSize = " + i3, 16);
                if (i3 > 0) {
                    this.clutList = new ArrayList();
                    int i4 = 0;
                    int i5 = 0;
                    while (i4 < this.segmentLen && i5 < i3) {
                        CLUTTable cLUTTable = new CLUTTable(this);
                        long j4 = cLUTTable.parserBuffer(byteBuffer, j3);
                        int i6 = cLUTTable.colorType;
                        if (i6 == i) {
                            if (this.color2Table == null) {
                                this.color2Table = new int[4];
                            }
                            this.color2Table[cLUTTable.clutEntryId] = cLUTTable.rgbColor;
                        } else if (i6 == 4) {
                            if (this.color4Table == null) {
                                this.color4Table = new int[16];
                            }
                            this.color4Table[cLUTTable.clutEntryId] = cLUTTable.rgbColor;
                        } else if (i6 == 8) {
                            if (this.color8Table == null) {
                                this.color8Table = new int[256];
                            }
                            this.color8Table[cLUTTable.clutEntryId] = cLUTTable.rgbColor;
                        } else {
                            MtkDebugLogUtil.log("CLUTDefinitionSegment", "colorType = " + cLUTTable.colorType, 16);
                        }
                        this.clutList.add(cLUTTable);
                        i4 = (int) (((long) i4) + (j4 - j3));
                        i5++;
                        j3 = j4;
                        i = 2;
                    }
                }
                MtkDebugLogUtil.log("CLUTDefinitionSegment", "" + this, 16);
                return j3;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

        class CLUTTable implements ParserBuffer {
            int clutEntryId;
            int clutFlag2bit;
            int clutFlag4bit;
            int clutFlag8bit;
            int colorType;
            int fullCb;
            int fullCr;
            int fullRangeFlag;
            int fullY;
            int rgbColor;

            CLUTTable(CLUTDefinitionSegment cLUTDefinitionSegment) {
            }

            @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
            public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
                long j2;
                long j3 = j + 1;
                try {
                    this.clutEntryId = byteBuffer.getByte(j);
                    long j4 = j3 + 1;
                    int i = byteBuffer.getByte(j3);
                    int i2 = (i >> 7) & 1;
                    this.clutFlag2bit = i2;
                    int i3 = (i >> 6) & 1;
                    this.clutFlag4bit = i3;
                    int i4 = (i >> 5) & 1;
                    this.clutFlag8bit = i4;
                    if (i2 == 1) {
                        this.colorType = 2;
                    } else if (i3 == 1) {
                        this.colorType = 4;
                    } else if (i4 == 1) {
                        this.colorType = 8;
                    }
                    int i5 = i & 1;
                    this.fullRangeFlag = i5;
                    if (i5 == 1) {
                        long j5 = j4 + 1;
                        this.fullY = byteBuffer.getByte(j4);
                        long j6 = j5 + 1;
                        this.fullCb = byteBuffer.getByte(j5);
                        long j7 = j6 + 1;
                        this.fullCr = byteBuffer.getByte(j6);
                        j2 = 1 + j7;
                        byteBuffer.getByte(j7);
                        this.rgbColor = ColorSpaceUtils.YCbCr2RGB(this.fullY, this.fullCb, this.fullCr, false);
                    } else {
                        byteBuffer.getWord(j4);
                        j2 = 2 + j4;
                        this.rgbColor = ColorSpaceUtils.YCbCr2RGB(this.fullY, this.fullCb, this.fullCr, false);
                    }
                    MtkDebugLogUtil.log("CLUTSegment", "" + this, 16);
                    return j2;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            }

            public String toString() {
                return "CLUTSegment [clutEntryId=" + this.clutEntryId + ", fullRangeFlag=" + this.fullRangeFlag + ", rgbColor=" + this.rgbColor + ", colorType=" + this.colorType + "]";
            }
        }

        @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.SubtitlingSegment
        public String toString() {
            return "CLUTDefinitionSegment [clutId=" + this.clutId + ", clutVerNum=" + this.clutVerNum + "]";
        }
    }

    class ObjectDataSegment extends SubtitlingSegment implements ParserBuffer {
        List<PixelDataSubBlock.ObjDataLine> bottomDataList;
        int bottomFieldDataBlockLen;
        List<PixelDataSubBlock> bottomList;
        int charCode;
        int[] chars;
        int codeNum;
        int objectCodingMethod;
        int objectId;
        int objectVerNum;
        int stuffBits;
        List<PixelDataSubBlock.ObjDataLine> topDataList;
        int topFieldDataBlockLen;
        List<PixelDataSubBlock> topList;

        public void draw(SubtitlingSegment subtitlingSegment, int[] iArr, Canvas canvas) {
            Bitmap bitmap = getBitmap(this.topDataList, iArr);
            if (bitmap != null) {
                subtitlingSegment.dds.isObj = true;
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint());
            }
        }

        private Bitmap getBitmap(List<PixelDataSubBlock.ObjDataLine> list, int[] iArr) {
            int len;
            int size;
            if (list == null || list.size() <= 0) {
                len = 0;
                size = 0;
            } else {
                len = list.get(0).getLen();
                size = list.size();
            }
            MtkDebugLogUtil.log("ObjectDataSegment", "getBitmap width = " + len + " height = " + size, 16);
            if (len <= 0 || size <= 0) {
                return null;
            }
            int[] iArr2 = new int[len * size];
            int i = 0;
            for (PixelDataSubBlock.ObjDataLine objDataLine : list) {
                if (objDataLine.getData() != null) {
                    int[] data = objDataLine.getData();
                    int length = data.length;
                    int i2 = 0;
                    while (i2 < length) {
                        iArr2[i] = iArr[data[i2]];
                        i2++;
                        i++;
                    }
                }
            }
            return Bitmap.createBitmap(iArr2, len, size, Bitmap.Config.ARGB_8888);
        }

        public ObjectDataSegment(DvbParserFactory dvbParserFactory, SubtitlingSegment subtitlingSegment) {
            super(subtitlingSegment);
        }

        @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.SubtitlingSegment, com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
        public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
            try {
                this.objectId = byteBuffer.getWord(j);
                long j2 = j + 2;
                long j3 = j2 + 1;
                int i = byteBuffer.getByte(j2);
                this.objectVerNum = (i >> 4) & 15;
                this.objectCodingMethod = (i >> 2) & 3;
                MtkDebugLogUtil.log("ObjectDataSegment", "objectCodingMethod = " + this.objectCodingMethod, 16);
                int i2 = this.objectCodingMethod;
                int i3 = 0;
                if (i2 == 0) {
                    this.topFieldDataBlockLen = byteBuffer.getWord(j3);
                    long j4 = j3 + 2;
                    this.bottomFieldDataBlockLen = byteBuffer.getWord(j4);
                    j3 = j4 + 2;
                    this.topList = new ArrayList();
                    this.bottomList = new ArrayList();
                    this.topDataList = new ArrayList();
                    this.bottomDataList = new ArrayList();
                    MtkDebugLogUtil.log("ObjectDataSegment", "topFieldDataBlockLen = " + this.topFieldDataBlockLen + "bottomFieldDataBlockLen = " + this.bottomFieldDataBlockLen, 16);
                    int i4 = 0;
                    while (i4 < this.topFieldDataBlockLen) {
                        PixelDataSubBlock pixelDataSubBlock = new PixelDataSubBlock(this);
                        long j5 = pixelDataSubBlock.parserBuffer(byteBuffer, j3);
                        if (pixelDataSubBlock.getWidth() > 0) {
                            this.topDataList.add(pixelDataSubBlock.pixelCodeString.dataLine);
                            this.topList.add(pixelDataSubBlock);
                        }
                        i4 = (int) (((long) i4) + (j5 - j3));
                        j3 = j5;
                    }
                    MtkDebugLogUtil.log("ObjectDataSegment", "top processLen = " + i4, 16);
                    while (i3 < this.bottomFieldDataBlockLen) {
                        PixelDataSubBlock pixelDataSubBlock2 = new PixelDataSubBlock(this);
                        long j6 = pixelDataSubBlock2.parserBuffer(byteBuffer, j3);
                        if (pixelDataSubBlock2.getWidth() > 0) {
                            this.bottomDataList.add(pixelDataSubBlock2.pixelCodeString.dataLine);
                            this.bottomList.add(pixelDataSubBlock2);
                        }
                        i3 = (int) (((long) i3) + (j6 - j3));
                        j3 = j6;
                    }
                    MtkDebugLogUtil.log("ObjectDataSegment", "bottom processLen = " + i3, 16);
                    MtkDebugLogUtil.log("ObjectDataSegment", "bottom total = " + (j3 - j), 16);
                    if (byteBuffer.getByte(j3) != 15) {
                        this.stuffBits = byteBuffer.getByte(j3);
                        MtkDebugLogUtil.log("ObjectDataSegment", " stuffBits = " + this.stuffBits, 16);
                        j3++;
                    }
                } else if (i2 == 1) {
                    long j7 = 1 + j3;
                    int i5 = byteBuffer.getByte(j3);
                    this.codeNum = i5;
                    if (i5 > 0) {
                        this.chars = new int[i5];
                        j3 = j7;
                        while (i3 < this.codeNum) {
                            this.chars[i3] = byteBuffer.getWord(j3);
                            j3 += 2;
                            i3++;
                        }
                        MtkDebugLogUtil.log("ObjectDataSegment", "codeNum = " + this.codeNum + " chars = " + this.chars, 16);
                    } else {
                        j3 = j7;
                    }
                } else {
                    MtkDebugLogUtil.log("ObjectDataSegment", "objectCodingMethod = " + this.objectCodingMethod, 16);
                }
                MtkDebugLogUtil.log("ObjectDataSegment", "" + this, 16);
                return j3;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

        class PixelDataSubBlock implements ParserBuffer {
            int dataType;
            int mapTable2To4;
            int mapTable2To8;
            int[] mapTable4To8 = new int[4];
            PixelCodeString pixelCodeString;

            PixelDataSubBlock(ObjectDataSegment objectDataSegment) {
            }

            public int getWidth() {
                PixelCodeString pixelCodeString = this.pixelCodeString;
                if (pixelCodeString != null) {
                    return pixelCodeString.getLen();
                }
                return 0;
            }

            @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
            public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
                long j2 = 1 + j;
                try {
                    int i = byteBuffer.getByte(j);
                    this.dataType = i;
                    if (i != 240) {
                        switch (i) {
                            case 16:
                                PixelCodeString2Bit pixelCodeString2Bit = new PixelCodeString2Bit();
                                this.pixelCodeString = pixelCodeString2Bit;
                                return pixelCodeString2Bit.parserBuffer(byteBuffer, j2);
                            case 17:
                                PixelCodeString4Bit pixelCodeString4Bit = new PixelCodeString4Bit();
                                this.pixelCodeString = pixelCodeString4Bit;
                                return pixelCodeString4Bit.parserBuffer(byteBuffer, j2);
                            case 18:
                                PixelCodeString8Bit pixelCodeString8Bit = new PixelCodeString8Bit();
                                this.pixelCodeString = pixelCodeString8Bit;
                                return pixelCodeString8Bit.parserBuffer(byteBuffer, j2);
                            default:
                                switch (i) {
                                    case 32:
                                        this.mapTable2To4 = byteBuffer.getWord(j2);
                                        return j2 + 2;
                                    case 33:
                                        this.mapTable2To8 = byteBuffer.getDWord(j2);
                                        break;
                                    case 34:
                                        this.mapTable4To8[0] = byteBuffer.getDWord(j2);
                                        long j3 = j2 + 4;
                                        this.mapTable4To8[1] = byteBuffer.getDWord(j3);
                                        long j4 = j3 + 4;
                                        this.mapTable4To8[2] = byteBuffer.getDWord(j4);
                                        j2 = j4 + 4;
                                        this.mapTable4To8[3] = byteBuffer.getDWord(j2);
                                        break;
                                    default:
                                        MtkDebugLogUtil.log("PixelDataSubBlock", "dataType = " + this.dataType, 16);
                                        return j2;
                                }
                                return j2 + 4;
                        }
                    }
                    MtkDebugLogUtil.log("PixelDataSubBlock", "END of Object line ~~ " + this.dataType, 16);
                    return j2;
                } catch (Exception e) {
                    e.printStackTrace();
                    return j;
                }
            }

            class ObjDataLine {
                int[] datas;
                int len = 0;
                List<Integer> runLens = new ArrayList();
                List<Integer> pixelCodes = new ArrayList();

                public ObjDataLine(PixelDataSubBlock pixelDataSubBlock, int i) {
                }

                public void append(int i, int i2) {
                    if (i > 0) {
                        this.runLens.add(Integer.valueOf(i));
                        this.pixelCodes.add(Integer.valueOf(i2));
                        this.len += i;
                    }
                }

                public int getLen() {
                    return this.len;
                }

                public int[] getData() {
                    int i;
                    int[] iArr = this.datas;
                    if (iArr == null || ((i = this.len) > 0 && iArr.length != i)) {
                        this.datas = new int[this.len];
                        int i2 = 0;
                        for (int i3 = 0; i3 < this.runLens.size(); i3++) {
                            int i4 = 0;
                            while (i4 < this.runLens.get(i3).intValue()) {
                                this.datas[i2] = this.pixelCodes.get(i3).intValue();
                                i4++;
                                i2++;
                            }
                        }
                        return this.datas;
                    }
                    if (this.len <= 0) {
                        return null;
                    }
                    return this.datas;
                }

                public String toString() {
                    return "ObjDataItem [runLen=" + this.runLens + ", pixelCode=" + this.pixelCodes + "]";
                }
            }

            abstract class PixelCodeString implements ParserBuffer {
                int curByte;
                ObjDataLine dataLine;
                int nextBits;
                int switch1;
                int switch2;
                int switch3;
                long off = 0;
                int byteLast = 0;

                PixelCodeString(PixelDataSubBlock pixelDataSubBlock) {
                }

                public int getLen() {
                    return this.dataLine.getLen();
                }

                protected int readNext(ByteBuffer byteBuffer, int i) throws Exception {
                    int i2;
                    while (true) {
                        i2 = this.byteLast;
                        if (i2 >= i) {
                            break;
                        }
                        int i3 = this.curByte << 8;
                        long j = this.off;
                        this.off = 1 + j;
                        this.curByte = i3 + byteBuffer.getByte(j);
                        this.byteLast += 8;
                    }
                    int i4 = this.curByte;
                    int i5 = i2 - i;
                    this.byteLast = i5;
                    int i6 = i4 >> i5;
                    int i7 = i4 - (i6 << i5);
                    this.curByte = i7;
                    if (i7 < 0 || i5 < 0) {
                        MtkDebugLogUtil.log("PixelDataSubBlock", "readNext error curByte = " + this.curByte + " byteLast = " + this.byteLast + "result = " + i6, 16);
                    }
                    return i6;
                }
            }

            class PixelCodeString2Bit extends PixelCodeString implements ParserBuffer {
                PixelCodeString2Bit() {
                    super(PixelDataSubBlock.this);
                }

                @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
                public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
                    this.off = j;
                    this.dataLine = new ObjDataLine(PixelDataSubBlock.this, 2);
                    boolean z = false;
                    while (!z) {
                        try {
                            int next = readNext(byteBuffer, 2);
                            this.nextBits = next;
                            if (next != 0) {
                                this.dataLine.append(1, next);
                            } else {
                                int next2 = readNext(byteBuffer, 1);
                                this.switch1 = next2;
                                if (next2 == 1) {
                                    this.dataLine.append(readNext(byteBuffer, 3) + 3, readNext(byteBuffer, 2));
                                } else {
                                    int next3 = readNext(byteBuffer, 1);
                                    this.switch2 = next3;
                                    if (next3 == 0) {
                                        int next4 = readNext(byteBuffer, 2);
                                        this.switch3 = next4;
                                        if (next4 == 0) {
                                            z = true;
                                        } else if (next4 == 1) {
                                            this.dataLine.append(2, 0);
                                        } else if (next4 == 2) {
                                            this.dataLine.append(readNext(byteBuffer, 4) + 12, readNext(byteBuffer, 2));
                                        } else if (next4 == 3) {
                                            this.dataLine.append(readNext(byteBuffer, 8) + 29, readNext(byteBuffer, 2));
                                        }
                                    } else {
                                        this.dataLine.append(1, 0);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw e;
                        }
                    }
                    MtkDebugLogUtil.log("PixelCodeString2Bit", "bitmapData hLen = " + this.dataLine.getLen(), 16);
                    return this.off;
                }
            }

            class PixelCodeString4Bit extends PixelCodeString implements ParserBuffer {
                PixelCodeString4Bit() {
                    super(PixelDataSubBlock.this);
                }

                @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
                public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
                    this.off = j;
                    this.dataLine = new ObjDataLine(PixelDataSubBlock.this, 4);
                    boolean z = false;
                    while (!z) {
                        try {
                            int next = readNext(byteBuffer, 4);
                            this.nextBits = next;
                            if (next != 0) {
                                this.dataLine.append(1, next);
                            } else {
                                int next2 = readNext(byteBuffer, 1);
                                this.switch1 = next2;
                                if (next2 == 0) {
                                    int next3 = readNext(byteBuffer, 3);
                                    if (next3 != 0) {
                                        this.dataLine.append(next3 + 2, 0);
                                    } else {
                                        MtkDebugLogUtil.log("PixelCodeString4Bit", "parserBuffer end ", 16);
                                        z = true;
                                    }
                                } else {
                                    int next4 = readNext(byteBuffer, 1);
                                    this.switch2 = next4;
                                    if (next4 == 0) {
                                        this.dataLine.append(readNext(byteBuffer, 2) + 4, readNext(byteBuffer, 4));
                                    } else {
                                        int next5 = readNext(byteBuffer, 2);
                                        this.switch3 = next5;
                                        if (next5 == 0) {
                                            this.dataLine.append(1, 0);
                                        } else if (next5 == 1) {
                                            this.dataLine.append(2, 0);
                                        } else if (next5 == 2) {
                                            this.dataLine.append(readNext(byteBuffer, 4) + 9, readNext(byteBuffer, 4));
                                        } else if (next5 == 3) {
                                            this.dataLine.append(readNext(byteBuffer, 8) + 25, readNext(byteBuffer, 4));
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw e;
                        }
                    }
                    MtkDebugLogUtil.log("PixelCodeString4Bit", "bitmapData total = " + this.dataLine.getLen() + " data = " + this.dataLine, 16);
                    return this.off;
                }
            }

            class PixelCodeString8Bit extends PixelCodeString implements ParserBuffer {
                PixelCodeString8Bit() {
                    super(PixelDataSubBlock.this);
                }

                @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
                public long parserBuffer(ByteBuffer byteBuffer, long j) throws Exception {
                    this.off = j;
                    this.dataLine = new ObjDataLine(PixelDataSubBlock.this, 8);
                    while (true) {
                        try {
                            int next = readNext(byteBuffer, 8);
                            this.nextBits = next;
                            if (next != 0) {
                                this.dataLine.append(1, next);
                            } else {
                                int next2 = readNext(byteBuffer, 1);
                                this.switch1 = next2;
                                if (next2 == 0) {
                                    int next3 = readNext(byteBuffer, 7);
                                    if (next3 != 0) {
                                        this.dataLine.append(next3, 0);
                                    } else {
                                        MtkDebugLogUtil.log("PixelCodeString8Bit", "bitmapData total = " + this.dataLine.getLen() + " data = " + this.dataLine, 16);
                                        return this.off;
                                    }
                                } else {
                                    int next4 = readNext(byteBuffer, 7);
                                    MtkDebugLogUtil.log("PixelCodeString8Bit", "runLen 3-127 not less than three val = " + next4, 16);
                                    this.dataLine.append(next4, readNext(byteBuffer, 8));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw e;
                        }
                    }
                }
            }

            public String toString() {
                return "PixelDataSubBlock [dataType=" + this.dataType + ", mapTable2To4=" + this.mapTable2To4 + ", mapTable2To8=" + this.mapTable2To8 + ", mapTable4To8=" + Arrays.toString(this.mapTable4To8) + ", pixelCodeString=" + this.pixelCodeString + "]";
            }
        }

        @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.SubtitlingSegment
        public String toString() {
            return "ObjectDataSegment [objectId=" + this.objectId + ", objectVerNum=" + this.objectVerNum + ", objectCodingMethod=" + this.objectCodingMethod + ", codeNum=" + this.codeNum + ", charCode=" + this.charCode + ", chars=" + Arrays.toString(this.chars) + "]";
        }
    }

    class EndOfDisplaySegment extends SubtitlingSegment implements ParserBuffer {
        public EndOfDisplaySegment(DvbParserFactory dvbParserFactory, SubtitlingSegment subtitlingSegment) {
            super(subtitlingSegment);
        }

        @Override // com.google.android.exoplayer.text.dvb.DvbParserFactory.SubtitlingSegment, com.google.android.exoplayer.text.dvb.DvbParserFactory.ParserBuffer
        public long parserBuffer(ByteBuffer byteBuffer, long j) {
            MtkDebugLogUtil.log("EndOfDisplaySegment", "offset = " + j, 16);
            return j;
        }
    }

    private void internalParser() {
        long size = this.source.getSize();
        long j = 0;
        while (j < size) {
            try {
                PESPacketData pESPacketData = new PESPacketData();
                j = pESPacketData.parserBuffer(this.source, j);
                MtkDebugLogUtil.log("DvbParserFactory", "PESPacketData = " + pESPacketData, 16);
                Bitmap image = pESPacketData.getImage();
                if (image != null) {
                    this.cacheBmp = image;
                    this.timeOff = pESPacketData.getTimeOffset();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
