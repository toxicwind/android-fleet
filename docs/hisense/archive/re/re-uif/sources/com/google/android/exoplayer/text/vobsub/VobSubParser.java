package com.google.android.exoplayer.text.vobsub;

import android.graphics.Bitmap;
import android.util.Log;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.DefaultSubtitle;
import com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.ParserUtil;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public class VobSubParser implements SubtitleParser, ExternalSubtitleCharsetEncoding {
    private String encoding;
    private int mCurrentSubtitleIndex;
    private String mDataPath;
    private static final Pattern sizePat = Pattern.compile("(\\d+)x(\\d+)");
    private static final Pattern sizeLinePat = Pattern.compile("^size: (\\d+)x(\\d+)$");
    private static final Pattern hexPat = Pattern.compile("[0-9a-fA-F]{6}");
    private static final Pattern palettePat = Pattern.compile("^palette:( [0-9a-fA-F]{6},){15} [0-9a-fA-F]{6}");
    private static final Pattern lanHeadPat = Pattern.compile("^id: (.*?), index: \\d+$");
    private static final Pattern timestampPat = Pattern.compile("\\d{2}:[0-5]\\d:[0-5]\\d:\\d{3}");
    private static final Pattern positionPat = Pattern.compile("[0-9a-fA-F]{9}");
    private static final Pattern contentPat = Pattern.compile("^timestamp: \\d{2}:[0-5]\\d:[0-5]\\d:\\d{3}, filepos: [0-9a-fA-F]{9}$");

    public VobSubParser() {
        this("");
    }

    public VobSubParser(String str) {
        this.mCurrentSubtitleIndex = 0;
        this.mDataPath = str;
    }

    private int[] parseBaseRect(String str) {
        Matcher matcher = sizePat.matcher(str);
        int[] iArr = new int[2];
        if (matcher.find()) {
            try {
                int i = 0;
                for (String str2 : matcher.group().split("x")) {
                    iArr[i] = Integer.parseInt(str2);
                    MtkDebugLogUtil.log("VobSubParser", "parseBaseRect index = " + i + " val = " + iArr[i], 16);
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return iArr;
    }

    private int[] parsePalette(String str) {
        int[] iArr = new int[16];
        Matcher matcher = hexPat.matcher(str);
        int i = 0;
        while (matcher.find()) {
            iArr[i] = Integer.parseInt(matcher.group(), 16);
            MtkDebugLogUtil.log("VobSubParser", "parsePalette index = " + i + " val = " + Integer.toHexString(iArr[i]), 16);
            i++;
        }
        return iArr;
    }

    /* JADX WARN: Code duplicated, block: B:41:0x017f  */
    @Override // com.google.android.exoplayer.text.SubtitleParser
    public Subtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        ArrayList arrayList;
        int i3;
        String strTrim;
        int i4;
        int[] palette;
        int[] baseRect;
        int i5;
        String str;
        int i6;
        String str2;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
        if (this.encoding == null) {
            this.encoding = ParserUtil.getCharset(bArr, i, i2);
        }
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream, this.encoding));
            while (true) {
                try {
                    try {
                        String line = bufferedReader2.readLine();
                        str = "VobSubParser";
                        if (line == null) {
                            break;
                        }
                        String strTrim2 = line.trim();
                        MtkDebugLogUtil.log("VobSubParser", "parse head line = " + strTrim2, i3);
                        if (!strTrim2.startsWith("#")) {
                            Matcher matcher = sizeLinePat.matcher(strTrim2);
                            if (matcher.find()) {
                                baseRect = parseBaseRect(matcher.group());
                            } else {
                                Matcher matcher2 = palettePat.matcher(strTrim2);
                                if (matcher2.find()) {
                                    palette = parsePalette(matcher2.group());
                                } else {
                                    Matcher matcher3 = lanHeadPat.matcher(strTrim2);
                                    if (matcher3.find()) {
                                        for (String str3 : matcher3.group().split("\\,")) {
                                            String[] strArrSplit = str3.split(":");
                                            if (strArrSplit[0].trim().equals("id")) {
                                                strTrim = strArrSplit[1].trim();
                                                MtkDebugLogUtil.log("VobSubParser", "parse ID = " + strTrim, 16);
                                            }
                                            if (strArrSplit[0].trim().equals("index")) {
                                                i5 = Integer.parseInt(strArrSplit[1].trim());
                                                i4++;
                                                MtkDebugLogUtil.log("VobSubParser", "parse vobIndex = " + i5, 16);
                                            }
                                        }
                                        Log.d("VobSubParser", "currentSubtitleIndex==" + i4 + ",mCurrentSubtitleIndex==" + this.mCurrentSubtitleIndex);
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("vobIndex==");
                                        sb.append(i5);
                                        Log.d("VobSubParser", sb.toString());
                                        if (i4 == this.mCurrentSubtitleIndex) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        i3 = 16;
                    } catch (IOException e) {
                        throw new ParserException(e);
                    }
                } catch (Throwable th) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused) {
                    }
                    throw th;
                }
            }
            while (true) {
                String line2 = bufferedReader2.readLine();
                if (line2 != null) {
                    String strTrim3 = line2.trim();
                    MtkDebugLogUtil.log(str, "parse content line = " + strTrim3, 16);
                    if (!strTrim3.startsWith("#")) {
                        if (lanHeadPat.matcher(strTrim3).find()) {
                            long[] jArr = new long[arrayList.size() * 2];
                            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                                int i8 = i7 * 2;
                                VobCue vobCue = (VobCue) arrayList.get(i7);
                                jArr[i8] = vobCue.startTime;
                                jArr[i8 + 1] = vobCue.endTime;
                            }
                            VobSubSubtitle vobSubSubtitle = new VobSubSubtitle(0L, jArr, str2, i6, arrayList);
                            try {
                                bufferedReader2.close();
                            } catch (IOException unused2) {
                            }
                            return vobSubSubtitle;
                        }
                        Matcher matcher4 = contentPat.matcher(strTrim3);
                        if (matcher4.find()) {
                            String strGroup = matcher4.group();
                            arrayList.add(new VobCue(this, parseTimestampUs(strGroup), parsePosition(strGroup), baseRect, palette, i6));
                        }
                    }
                    str = str;
                } else {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused3) {
                    }
                    if (arrayList.size() > 0) {
                        long[] jArr2 = new long[arrayList.size() * 2];
                        for (int i9 = 0; i9 < arrayList.size(); i9++) {
                            int i10 = i9 * 2;
                            VobCue vobCue2 = (VobCue) arrayList.get(i9);
                            jArr2[i10] = vobCue2.startTime;
                            jArr2[i10 + 1] = vobCue2.endTime;
                        }
                        return new VobSubSubtitle(0L, jArr2, str2, i6, arrayList);
                    }
                    return new DefaultSubtitle();
                }
                bufferedReader2.close();
                throw th;
            }
        } catch (UnsupportedEncodingException unused4) {
            bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
        }
        bufferedReader2 = bufferedReader;
        arrayList = new ArrayList();
        i3 = 16;
        strTrim = null;
        i4 = -1;
        palette = new int[16];
        baseRect = new int[2];
        i5 = -1;
        i6 = i5;
        str2 = strTrim;
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        boolean zEquals = "text/ext/vobsub".equals(str);
        MtkDebugLogUtil.log("VobSubParser", "canParse  type = " + str + " can = " + zEquals, 16);
        return zEquals;
    }

    @Override // com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding
    public void setCharsetEncoding(String str) {
        this.encoding = str;
    }

    private static long parseTimestampUs(String str) throws NumberFormatException {
        MtkDebugLogUtil.log("VobSubParser", "parseTimestampUs line = " + str, 16);
        Matcher matcher = timestampPat.matcher(str);
        if (matcher.find()) {
            String[] strArrSplit = matcher.group().split(":", 4);
            long j = ((((((Long.parseLong(strArrSplit[0]) * 60) + Long.parseLong(strArrSplit[1])) * 60) + Long.parseLong(strArrSplit[2])) * 1000) + Long.parseLong(strArrSplit[3])) * 1000;
            MtkDebugLogUtil.log("VobSubParser", "parseTimestampUs value = " + j, 16);
            return j;
        }
        throw new NumberFormatException("has invalid format");
    }

    private static int parsePosition(String str) {
        MtkDebugLogUtil.log("VobSubParser", "parsePosition line = " + str, 16);
        Matcher matcher = positionPat.matcher(str);
        if (matcher.find()) {
            int i = Integer.parseInt(matcher.group(), 16);
            MtkDebugLogUtil.log("VobSubParser", "parsePosition value = " + i, 16);
            return i;
        }
        throw new NumberFormatException("has invalid format");
    }

    public void enableSubtitleIndex(int i) {
        this.mCurrentSubtitleIndex = i;
        Log.d("VobSubParser", "enableSubtitleIndex,mCurrentSubtitleIndex==" + this.mCurrentSubtitleIndex);
    }

    public class VobCue {
        private Bitmap bitmapCache;
        private boolean debug = false;
        public long endTime;
        public String filePath;
        public int offsetPos;
        public long offsetTime;
        public int[] pat;
        public long startTime;
        public int vobIndex;
        private VobSubBitmapFactory vobsubBitmapFactory;

        public VobCue(VobSubParser vobSubParser, long j, int i, int[] iArr, int[] iArr2, int i2) throws Throwable {
            this.offsetTime = j;
            this.offsetPos = i;
            this.pat = iArr2;
            this.vobIndex = i2;
            if (vobSubParser.mDataPath != null && vobSubParser.mDataPath.length() > 0) {
                this.filePath = vobSubParser.mDataPath.substring(0, vobSubParser.mDataPath.lastIndexOf(".")) + ".sub";
            }
            init();
            MtkDebugLogUtil.log("VobSubParser", "filePath = " + this.filePath, 16);
        }

        private void init() throws Throwable {
            BufferedInputStream bufferedInputStream = null;
            File file = this.filePath != null ? new File(this.filePath) : null;
            if (file == null) {
                return;
            }
            try {
                if (!file.exists()) {
                    return;
                }
                try {
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                    try {
                        byte[] bArr = new byte[2048];
                        long jSkip = bufferedInputStream2.skip(this.offsetPos);
                        if (this.debug) {
                            MtkDebugLogUtil.log("VobSubParser", "skip offset = " + this.offsetPos + " real = " + jSkip, 16);
                            int[] iArr = this.pat;
                            int length = iArr.length;
                            for (int i = 0; i < length; i++) {
                                MtkDebugLogUtil.log("VobSubParser", "color = " + iArr[i], 16);
                            }
                        }
                        int i2 = bufferedInputStream2.read(bArr, 0, 2048);
                        if (-1 == i2) {
                            Log.d("VobSubParser", "read pslen is -1");
                        }
                        if (this.debug) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("(resultOut[0x00] != 0x00)= ");
                            sb.append(bArr[0] != 0);
                            sb.append("  (resultOut[0x01] != 0x00) ");
                            sb.append(bArr[1] != 0);
                            sb.append("  (resultOut[0x02] != 0x01) ");
                            sb.append(bArr[2] != 1);
                            sb.append("  (resultOut[0x03] != 0xba) ");
                            sb.append((bArr[3] & 186) != 186);
                            sb.append("  (resultOut[0x0e] != 0x00) ");
                            sb.append(bArr[14] != 0);
                            sb.append("  (resultOut[0x0f] != 0x00) ");
                            sb.append(bArr[15] != 0);
                            sb.append("  (resultOut[0x10] != 0x01) ");
                            sb.append(bArr[16] != 1);
                            sb.append("  (resultOut[0x11] != 0xbd) ");
                            sb.append((bArr[17] & 189) != 189);
                            sb.append("  (resultOut[0x15] & 0x80) != 0x80 ");
                            sb.append((bArr[21] & ByteCompanionObject.MIN_VALUE) != 128);
                            sb.append("  (resultOut[0x17] & 0xf0) != 0x20 ");
                            sb.append((bArr[23] & 240) != 32);
                            sb.append("  (resultOut[resultOut[0x16] + 0x17] & 0xe0) != 0x20 ");
                            sb.append((bArr[bArr[22] + 23] & 224) != 32);
                            sb.append("  ((resultOut[resultOut[0x16] + 0x17] & 0x1f) != vobIndex) ");
                            sb.append((bArr[bArr[22] + 23] & 31) != this.vobIndex);
                            MtkDebugLogUtil.log("VobSubParser", sb.toString(), 16);
                        }
                        if (bArr[0] != 0 || bArr[1] != 0 || bArr[2] != 1 || (bArr[3] & 186) != 186 || bArr[14] != 0 || bArr[15] != 0 || bArr[16] != 1 || (bArr[17] & 189) != 189 || (bArr[21] & ByteCompanionObject.MIN_VALUE) != 128 || (bArr[23] & 240) != 32 || (bArr[bArr[22] + 23] & 224) != 32 || (bArr[bArr[22] + 23] & 31) != this.vobIndex) {
                            throw new Exception("not PS header~~ ");
                        }
                        int iBytesToInt = VobSubBitmapFactory.bytesToInt(bArr, bArr[22] + 24, 2);
                        int iBytesToInt2 = VobSubBitmapFactory.bytesToInt(bArr, bArr[22] + 26, 2);
                        if (this.debug) {
                            MtkDebugLogUtil.log("VobSubParser", "resultOut[0x16]  = " + ((int) bArr[22]), 16);
                            MtkDebugLogUtil.log("VobSubParser", "packetSize  = " + iBytesToInt + " dataSize = " + iBytesToInt2, 16);
                        }
                        byte[] bArr2 = new byte[iBytesToInt];
                        int i3 = iBytesToInt;
                        int i4 = 0;
                        while (i4 < iBytesToInt) {
                            int i5 = bArr[22] + 24;
                            int iMin = Math.min(i3, 2048 - i5);
                            System.arraycopy(bArr, i5, bArr2, i4, iMin);
                            if (iMin != i3) {
                                do {
                                    i2 = bufferedInputStream2.read(bArr, 0, 2048);
                                    if (i2 == -1) {
                                        break;
                                    } else if (i2 == 0) {
                                        throw new Exception("read pslen is 0 ");
                                    }
                                } while ((bArr[bArr[22] + 23] & 31) != this.vobIndex);
                            }
                            i4 += iMin;
                            i3 -= iMin;
                        }
                        if (i4 != iBytesToInt || i3 > 0) {
                            throw new Exception("ui4_packetsize not fill done");
                        }
                        VobSubBitmapFactory vobSubBitmapFactory = new VobSubBitmapFactory(this.pat, this.offsetTime, bArr2);
                        this.vobsubBitmapFactory = vobSubBitmapFactory;
                        this.startTime = vobSubBitmapFactory.startTime;
                        this.endTime = vobSubBitmapFactory.endTime;
                        if (this.debug) {
                            MtkDebugLogUtil.log("VobSubParser", " startTime = " + this.vobsubBitmapFactory.startTime + " end time = " + this.vobsubBitmapFactory.endTime, 16);
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(" inputLen = ");
                            sb2.append(i2);
                            MtkDebugLogUtil.log("VobSubParser", sb2.toString(), 16);
                        }
                        bufferedInputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        bufferedInputStream = bufferedInputStream2;
                        e.printStackTrace();
                        bufferedInputStream.close();
                    } catch (Throwable th) {
                        th = th;
                        bufferedInputStream = bufferedInputStream2;
                        try {
                            bufferedInputStream.close();
                        } catch (Exception unused) {
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception unused2) {
            }
        }

        public Bitmap createBitmap() {
            Bitmap bitmap = this.bitmapCache;
            if (bitmap != null) {
                return bitmap;
            }
            VobSubBitmapFactory vobSubBitmapFactory = this.vobsubBitmapFactory;
            if (vobSubBitmapFactory == null) {
                return null;
            }
            this.bitmapCache = vobSubBitmapFactory.createBitmap();
            if (this.debug) {
                MtkDebugLogUtil.log("VobSubParser", " bm = " + this.bitmapCache, 16);
            }
            return this.bitmapCache;
        }
    }
}
