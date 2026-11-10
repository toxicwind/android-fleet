package com.google.android.exoplayer.text.ttml;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.Util;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class TtmlSubtitle implements Subtitle {
    Bitmap bmp;
    private final long[] eventTimesUs;
    private final Map<String, TtmlStyle> globalStyles;
    private InputStream inputstream;
    List<BitmapPos> plist;
    private final Map<String, TtmlRegion> regionMap;
    private final TtmlNode root;

    public TtmlSubtitle(TtmlNode ttmlNode, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2) {
        this.root = ttmlNode;
        this.regionMap = map2;
        this.globalStyles = map != null ? Collections.unmodifiableMap(map) : Collections.emptyMap();
        this.eventTimesUs = ttmlNode.getEventTimesUs();
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        int iBinarySearchCeil = Util.binarySearchCeil(this.eventTimesUs, j, false, false);
        if (iBinarySearchCeil < this.eventTimesUs.length) {
            return iBinarySearchCeil;
        }
        return -1;
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public int getEventTimeCount() {
        return this.eventTimesUs.length;
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public long getEventTime(int i) {
        return this.eventTimesUs[i];
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public List<Cue> getCues(long j) throws Exception {
        int bitmapIndex = this.root.getBitmapIndex(j);
        if (bitmapIndex > 0) {
            try {
                Bitmap bitmapCreateBitmap = createBitmap(this.inputstream, this.plist.get(bitmapIndex - 1));
                this.bmp = bitmapCreateBitmap;
                if (bitmapCreateBitmap != null) {
                    return Collections.singletonList(new Cue(bitmapCreateBitmap));
                }
                return Collections.emptyList();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
        return this.root.getCues(j, this.globalStyles, this.regionMap);
    }

    public static class BitmapPos {
        public int end;
        public int start;

        public String toString() {
            return "BitmapPos [start=" + this.start + ", end=" + this.end + "]";
        }
    }

    private Bitmap createBitmap(InputStream inputStream, BitmapPos bitmapPos) {
        if (inputStream == null) {
            return null;
        }
        try {
            inputStream.mark(1048576);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            byte[] bArr = new byte[bitmapPos.end - bitmapPos.start];
            long jSkip = inputStream.skip(bitmapPos.start);
            int i = inputStream.read(bArr);
            MtkDebugLogUtil.log("TtmlSubtitle", "bpos.end - bpos.start = " + (bitmapPos.end - bitmapPos.start) + " len = " + i + " ,skipped = " + jSkip, 16);
            return BitmapFactory.decodeByteArray(bArr, 0, i);
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                inputStream.reset();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            return null;
        }
    }

    public boolean hasImage() {
        return this.root.hasImage();
    }

    public void setInputStream(InputStream inputStream) {
        this.inputstream = inputStream;
        this.plist = getBitmapInfo(inputStream);
    }

    public List<BitmapPos> getBitmapInfo(InputStream inputStream) {
        ArrayList<BitmapPos> arrayList = new ArrayList();
        try {
            inputStream.mark(1048576);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("begin~~");
            byte[] bArr = new byte[1048576];
            for (boolean z = false; !z; z = true) {
                int i = inputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                byte[] bArr2 = {-119, 80, 78, 71, 13, 10, 26, 10};
                byte[] bArr3 = {73, 69, 78, 68};
                BitmapPos bitmapPos = null;
                System.out.println(" len = " + i);
                boolean z2 = false;
                for (int i2 = 0; i2 < 1048576; i2++) {
                    int i3 = 0;
                    int i4 = i2;
                    while (i3 < 8) {
                        int i5 = i4 + 1;
                        if ((bArr[i4] & 255) != (bArr2[i3] & 255)) {
                            z2 = false;
                            i4 = i5;
                            break;
                        }
                        i3++;
                        z2 = true;
                        i4 = i5;
                    }
                    if (z2 && i4 == i2 + 8) {
                        bitmapPos = new BitmapPos();
                        bitmapPos.start = i2;
                    } else {
                        int i6 = 0;
                        int i7 = i2;
                        while (i6 < 4) {
                            int i8 = i7 + 1;
                            if ((bArr[i7] & 255) != (bArr3[i6] & 255)) {
                                z2 = false;
                                i7 = i8;
                                break;
                            }
                            i6++;
                            z2 = true;
                            i7 = i8;
                        }
                        if (z2 && i7 == i2 + 4 && bitmapPos != null) {
                            bitmapPos.end = i2 + 8;
                            arrayList.add(bitmapPos);
                        }
                    }
                }
                for (BitmapPos bitmapPos2 : arrayList) {
                    System.out.println("bp = " + bitmapPos2);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            break;
        }
        try {
            inputStream.reset();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return arrayList;
    }
}
