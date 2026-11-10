package com.google.android.exoplayer.text.pgs.internal;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.DefaultSubtitle;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.text.pgs.PgsFactory;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public class IntPgsParser implements SubtitleParser {
    private boolean isEnd = true;
    private byte[] pendingData;

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public Subtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArrayInputStream);
        Inflater inflater = new Inflater();
        byte[] bArr2 = new byte[204800];
        byte[] bArr3 = new byte[512000];
        PgsFactory pgsFactory = null;
        while (true) {
            try {
                try {
                    try {
                        int i3 = bufferedInputStream.read(bArr2);
                        if (i3 == -1) {
                            break;
                        }
                        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr2, 0, i3);
                        if (!pendingParser(bArrCopyOfRange)) {
                            try {
                                inflater.setInput(bArr2, 0, i3);
                                bArrCopyOfRange = Arrays.copyOfRange(bArr3, 0, inflater.inflate(bArr3));
                                inflater.end();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            pgsFactory = new PgsFactory(bArrCopyOfRange, true);
                        } else if (addPendingData(bArrCopyOfRange) && this.isEnd) {
                            pgsFactory = new PgsFactory(this.pendingData, true);
                        } else {
                            Thread.sleep(1000L);
                        }
                    } catch (Throwable th) {
                        try {
                            bufferedInputStream.close();
                            byteArrayInputStream.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    bufferedInputStream.close();
                    byteArrayInputStream.close();
                }
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
        if (pgsFactory == null) {
            bufferedInputStream.close();
            byteArrayInputStream.close();
            return new DefaultSubtitle();
        }
        MtkDebugLogUtil.log("IntPgsParser", "sb  size = " + pgsFactory.getSubPictureList().size(), 16);
        if (pgsFactory.getSubPictureList().size() > 0) {
            PgsSubtitle pgsSubtitle = new PgsSubtitle(pgsFactory, 0L);
            try {
                bufferedInputStream.close();
                byteArrayInputStream.close();
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            return pgsSubtitle;
        }
        PgsSubtitle pgsSubtitle2 = new PgsSubtitle();
        try {
            bufferedInputStream.close();
            byteArrayInputStream.close();
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        return pgsSubtitle2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean addPendingData(byte[] bArr) {
        MtkDebugLogUtil.log("IntPgsParser", "addPendingData ps type = " + (bArr[0] & 255) + " size = " + bArr.length, 16);
        int i = bArr[0] & 255;
        if (i == 128) {
            if (!this.isEnd) {
                this.isEnd = true;
                this.pendingData = appendByte(this.pendingData, bArr);
                return true;
            }
        } else {
            switch (i) {
                case 20:
                case 21:
                case 23:
                    if (!this.isEnd) {
                        this.pendingData = appendByte(this.pendingData, bArr);
                        return true;
                    }
                    break;
                case 22:
                    if (this.isEnd) {
                        this.isEnd = false;
                        this.pendingData = Arrays.copyOf(bArr, bArr.length);
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    private byte[] appendByte(byte[] bArr, byte[] bArr2) {
        int length = bArr2.length;
        int length2 = bArr.length;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, length2 + length);
        System.arraycopy(bArr2, 0, bArrCopyOf, length2, length);
        return bArrCopyOf;
    }

    /* JADX WARN: Code duplicated, block: B:6:0x0047  */
    /* JADX WARN: Code duplicated, block: B:8:0x004c A[RETURN] */
    private boolean pendingParser(byte[] bArr) {
        int i = bArr[0] & 255;
        int i2 = (bArr[2] & 255) | ((bArr[1] & 255) << 8);
        MtkDebugLogUtil.log("IntPgsParser", "pendingParser bs = " + bArr.length + " type = " + Integer.toHexString(i) + " size = " + i2, 16);
        if (i != 128) {
            switch (i) {
                case 20:
                case 21:
                case 22:
                case 23:
                    if (bArr.length == i2 + 3) {
                        return true;
                    }
                default:
                    return false;
            }
        } else if (bArr.length == i2 + 3) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        boolean zEquals = "text/pgs".equals(str);
        MtkDebugLogUtil.log("IntPgsParser", "canParse  type = " + str + " can = " + zEquals, 16);
        return zEquals;
    }
}
