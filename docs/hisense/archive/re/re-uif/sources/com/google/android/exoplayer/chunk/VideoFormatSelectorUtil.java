package com.google.android.exoplayer.chunk;

import android.content.Context;
import android.graphics.Point;
import com.google.android.exoplayer.MediaCodecUtil;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.Util;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.IntCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public final class VideoFormatSelectorUtil {
    public static int[] selectVideoFormatsForDefaultDisplay(Context context, List<? extends FormatWrapper> list, String[] strArr, boolean z) throws MediaCodecUtil.DecoderQueryException {
        Point physicalDisplaySize = Util.getPhysicalDisplaySize(context);
        return selectVideoFormats(list, strArr, z, true, false, physicalDisplaySize.x, physicalDisplaySize.y);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0061  */
    public static int[] selectVideoFormats(List<? extends FormatWrapper> list, String[] strArr, boolean z, boolean z2, boolean z3, int i, int i2) throws MediaCodecUtil.DecoderQueryException {
        int i3;
        int i4;
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        int i5 = IntCompanionObject.MAX_VALUE;
        for (int i6 = 0; i6 < size; i6++) {
            Format format = list.get(i6).getFormat();
            if (isFormatPlayable(format, strArr, z, z3)) {
                arrayList.add(Integer.valueOf(i6));
                int i7 = format.width;
                if (i7 > 0 && (i4 = format.height) > 0 && i > 0 && i2 > 0) {
                    Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(z2, i, i2, i7, i4);
                    int i8 = format.width;
                    int i9 = format.height;
                    int i10 = i8 * i9;
                    if (i8 >= ((int) (maxVideoSizeInViewport.x * 0.98f)) && i9 >= ((int) (maxVideoSizeInViewport.y * 0.98f)) && i10 < i5) {
                        i5 = i10;
                    }
                }
            }
        }
        if (i5 != Integer.MAX_VALUE) {
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                Format format2 = list.get(((Integer) arrayList.get(size2)).intValue()).getFormat();
                int i11 = format2.width;
                if (i11 > 0 && (i3 = format2.height) > 0 && i11 * i3 > i5) {
                    arrayList.remove(size2);
                }
            }
        }
        return Util.toArray(arrayList);
    }

    private static boolean isFormatPlayable(Format format, String[] strArr, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException {
        int i;
        if (strArr != null && !Util.contains(strArr, format.mimeType)) {
            return false;
        }
        if (z && (format.width >= 1280 || format.height >= 720)) {
            return false;
        }
        int i2 = format.width;
        if (i2 <= 0 || (i = format.height) <= 0) {
            return true;
        }
        if (Util.SDK_INT < 21) {
            return i2 * i <= MediaCodecUtil.maxH264DecodableFrameSize();
        }
        String videoMediaMimeType = MimeTypes.getVideoMediaMimeType(format.codecs);
        if ("video/x-unknown".equals(videoMediaMimeType)) {
            videoMediaMimeType = "video/avc";
        }
        String str = videoMediaMimeType;
        float f = format.frameRate;
        if (f > 0.0f) {
            return MediaCodecUtil.isSizeAndRateSupportedV21(str, z2, format.width, format.height, f);
        }
        return MediaCodecUtil.isSizeSupportedV21(str, z2, format.width, format.height);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0010  */
    private static Point getMaxVideoSizeInViewport(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            if ((i3 > i4) == (i > i2)) {
                i2 = i;
                i = i2;
            }
        } else {
            i2 = i;
            i = i2;
        }
        int i5 = i3 * i;
        int i6 = i4 * i2;
        if (i5 >= i6) {
            return new Point(i2, Util.ceilDivide(i6, i3));
        }
        return new Point(Util.ceilDivide(i5, i4), i);
    }
}
