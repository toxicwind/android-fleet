package com.google.android.exoplayer.text.vobsub;

import android.graphics.Bitmap;
import android.media.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.DefaultSubtitle;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.text.SubtitlePreInitParser;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public class InternalVobSubParser implements SubtitleParser, SubtitlePreInitParser {
    private MediaFormat mediaformat;
    private static final Pattern sizePat = Pattern.compile("(\\d+)x(\\d+)");
    private static final Pattern sizeLinePat = Pattern.compile("^size: (\\d+)x(\\d+)$");
    private static final Pattern hexPat = Pattern.compile("[0-9a-fA-F]{6}");
    private static final Pattern palettePat = Pattern.compile("^palette:( [0-9a-fA-F]{6},){15} [0-9a-fA-F]{6}");
    private static final int[] ZLIB_FLAG = {98, 64, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB, 80, 52, 128};
    private int[] palette = new int[16];
    private int[] rect = new int[2];
    private boolean isZlib = true;

    public static int toUInt(int i, int i2) {
        return i >= 0 ? i : i + ((int) Math.pow(2.0d, i2));
    }

    private int[] parseBaseRect(String str) {
        Matcher matcher = sizePat.matcher(str);
        int[] iArr = new int[2];
        if (matcher.find()) {
            try {
                int i = 0;
                for (String str2 : matcher.group().split("x")) {
                    iArr[i] = Integer.parseInt(str2);
                    MtkDebugLogUtil.log("InternalVobSubParser", "parseBaseRect index = " + i + " val = " + iArr[i], 16);
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
            MtkDebugLogUtil.log("InternalVobSubParser", "parsePalette index = " + i + " val = " + Integer.toHexString(iArr[i]), 16);
            i++;
        }
        return iArr;
    }

    private void parseHeader(byte[] bArr) throws IOException {
        MtkDebugLogUtil.log("InternalVobSubParser", "parseHeader inputLen = " + bArr.length + " ouput = " + new String(bArr, Charset.forName("UTF-8")), 16);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr), "UTF-8"));
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                String strTrim = line.trim();
                MtkDebugLogUtil.log("InternalVobSubParser", "parse head line = " + strTrim, 16);
                Matcher matcher = sizeLinePat.matcher(strTrim);
                if (matcher.find()) {
                    this.rect = parseBaseRect(matcher.group());
                } else {
                    Matcher matcher2 = palettePat.matcher(strTrim);
                    if (matcher2.find()) {
                        this.palette = parsePalette(matcher2.group());
                    }
                }
            } else {
                parseZlib(bArr);
                return;
            }
        }
    }

    private void parseZlib(byte[] bArr) {
        int length = bArr.length;
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, length - 6, length);
        MtkDebugLogUtil.log("InternalVobSubParser", "zlibStr  =" + new String(bArrCopyOfRange), 16);
        for (int i = 0; i < 6; i++) {
            MtkDebugLogUtil.log("InternalVobSubParser", " zlib [" + i + " ] = " + Integer.toHexString(toUInt(bArrCopyOfRange[i], 8)) + " ZLIB_FLAG[" + i + "] = " + Integer.toHexString(ZLIB_FLAG[i]), 16);
            if (toUInt(bArrCopyOfRange[i], 8) != ZLIB_FLAG[i]) {
                this.isZlib = false;
                return;
            }
        }
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public Subtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        byte[] bArrCopyOfRange;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
        Inflater inflater = new Inflater();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArrayInputStream);
        byte[] bArr2 = new byte[102400];
        byte[] bArr3 = new byte[204800];
        try {
            try {
                try {
                    if (bufferedInputStream.read(bArr2) != -1) {
                        MtkDebugLogUtil.log("InternalVobSubParser", "inputLen = 102400 isZlib = " + this.isZlib, 16);
                        try {
                            inflater.setInput(bArr2, 0, 102400);
                            bArrCopyOfRange = Arrays.copyOfRange(bArr3, 0, inflater.inflate(bArr3));
                            inflater.end();
                        } catch (Exception e) {
                            e.printStackTrace();
                            bArrCopyOfRange = Arrays.copyOfRange(bArr2, 0, 102400);
                        }
                        InternalVobSubSubtitle internalVobSubSubtitle = new InternalVobSubSubtitle(new VobCue(0L, this.rect, this.palette, bArrCopyOfRange));
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        return internalVobSubSubtitle;
                    }
                    bufferedInputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                    bufferedInputStream.close();
                }
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            return new DefaultSubtitle();
        } catch (Throwable th) {
            try {
                bufferedInputStream.close();
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            throw th;
        }
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        boolean zEquals = "text/vobsub".equals(str);
        MtkDebugLogUtil.log("InternalVobSubParser", "canParse  type = " + str + " can = " + zEquals, 16);
        return zEquals;
    }

    public static class VobCue {
        private Bitmap cache;
        public long endTime;
        public long startTime;
        public long startTimeUs;
        VobSubBitmapFactory vsbf;

        public VobCue(long j, int[] iArr, int[] iArr2, byte[] bArr) {
            this.startTimeUs = j;
            VobSubBitmapFactory vobSubBitmapFactory = new VobSubBitmapFactory(iArr2, j, bArr);
            this.vsbf = vobSubBitmapFactory;
            this.startTime = vobSubBitmapFactory.startTime;
            this.endTime = vobSubBitmapFactory.endTime;
        }

        public Bitmap createBitmap() {
            if (this.cache == null) {
                this.cache = this.vsbf.createBitmap();
            }
            return this.cache;
        }
    }

    @Override // com.google.android.exoplayer.text.SubtitlePreInitParser
    public void init(MediaFormat mediaFormat) {
        this.mediaformat = mediaFormat;
        try {
            parseHeader(mediaFormat.getByteBuffer("csd-0").array());
        } catch (Exception e) {
            e.printStackTrace();
        }
        MtkDebugLogUtil.log("InternalVobSubParser", "init this = " + this, 16);
    }

    public String toString() {
        return "InternalVobSubParser [palette=" + Arrays.toString(this.palette) + ", rect=" + Arrays.toString(this.rect) + ", isZlib=" + this.isZlib + ", mediaformat=" + this.mediaformat + "]";
    }
}
