package com.google.android.exoplayer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaExtractor;
import android.util.Log;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.Util;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
@Deprecated
public final class FrameworkSampleSource implements SampleSource, SampleSource.SampleSourceReader {
    public static Class<?> DATA_SOURCE_INTERFACE;
    private static Method setDataSourceMethod;

    @SuppressLint({"InlinedApi"})
    public static MediaFormat createMediaFormat(android.media.MediaFormat mediaFormat) {
        String string = mediaFormat.getString("mime");
        String optionalStringV16 = getOptionalStringV16(mediaFormat, "language");
        int optionalIntegerV16 = getOptionalIntegerV16(mediaFormat, "max-input-size");
        int optionalIntegerV17 = getOptionalIntegerV16(mediaFormat, "width");
        int optionalIntegerV18 = getOptionalIntegerV16(mediaFormat, "height");
        int optionalIntegerV19 = getOptionalIntegerV16(mediaFormat, "rotation-degrees");
        int optionalIntegerV110 = getOptionalIntegerV16(mediaFormat, "channel-count");
        int optionalIntegerV111 = getOptionalIntegerV16(mediaFormat, "sample-rate");
        int optionalIntegerV112 = getOptionalIntegerV16(mediaFormat, "encoder-delay");
        int optionalIntegerV113 = getOptionalIntegerV16(mediaFormat, "encoder-padding");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (!mediaFormat.containsKey("csd-" + i2)) {
                break;
            }
            ByteBuffer byteBuffer = mediaFormat.getByteBuffer("csd-" + i2);
            byte[] bArr = new byte[byteBuffer.limit()];
            byteBuffer.get(bArr);
            arrayList.add(bArr);
            byteBuffer.flip();
            i2++;
        }
        long j = mediaFormat.containsKey("durationUs") ? mediaFormat.getLong("durationUs") : -1L;
        int i3 = "audio/raw".equals(string) ? 2 : -1;
        int optionalIntegerV114 = getOptionalIntegerV16(mediaFormat, "group");
        int optionalIntegerV115 = getOptionalIntegerV16(mediaFormat, "frame-rate");
        ArrayList arrayList2 = null;
        if (mediaFormat.containsKey("chapters")) {
            arrayList2 = new ArrayList();
            ByteBuffer byteBuffer2 = mediaFormat.getByteBuffer("chapters");
            byte[] bArr2 = new byte[byteBuffer2.limit()];
            byteBuffer2.get(bArr2);
            byteBuffer2.flip();
            byte b = bArr2[0];
            int i4 = 1;
            while (i < b) {
                byte b2 = b;
                int i5 = i4 + 1;
                byte b3 = bArr2[i4];
                ArrayList arrayList3 = arrayList;
                String str = new String(bArr2, i5, (int) b3);
                int i6 = i5 + b3;
                int i7 = i6 + 1;
                int i8 = optionalIntegerV111;
                long j2 = (((long) bArr2[i6]) << 56) & (-72057594037927936L);
                int i9 = i7 + 1;
                int i10 = optionalIntegerV18;
                long j3 = ((((long) bArr2[i7]) << 48) & 71776119061217280L) | j2;
                int i11 = i9 + 1;
                long j4 = j3 | ((((long) bArr2[i9]) << 40) & 280375465082880L);
                int i12 = i11 + 1;
                long j5 = j4 | ((((long) bArr2[i11]) << 32) & 1095216660480L);
                int i13 = i12 + 1;
                long j6 = j5 | ((((long) bArr2[i12]) << 24) & 4278190080L);
                int i14 = i13 + 1;
                long j7 = j6 | ((((long) bArr2[i13]) << 16) & 16711680);
                int i15 = i14 + 1;
                i4 = i15 + 1;
                arrayList2.add(new MediaFormat.Chapter(i, str, j7 | ((((long) bArr2[i14]) << 8) & 65280) | (((long) bArr2[i15]) & 255)));
                i++;
                b = b2;
                arrayList = arrayList3;
                optionalStringV16 = optionalStringV16;
                optionalIntegerV111 = i8;
                optionalIntegerV18 = i10;
                optionalIntegerV19 = optionalIntegerV19;
            }
        }
        MediaFormat mediaFormat2 = new MediaFormat(null, string, -1, optionalIntegerV16, j, optionalIntegerV17, optionalIntegerV18, optionalIntegerV19, -1.0f, optionalIntegerV110, optionalIntegerV111, optionalStringV16, LongCompanionObject.MAX_VALUE, arrayList, false, -1, -1, i3, optionalIntegerV112, optionalIntegerV113, null, -1, optionalIntegerV114, optionalIntegerV115, 0, arrayList2);
        mediaFormat2.setFrameworkFormatV16(mediaFormat);
        return mediaFormat2;
    }

    @TargetApi(16)
    private static final String getOptionalStringV16(android.media.MediaFormat mediaFormat, String str) {
        if (mediaFormat.containsKey(str)) {
            return mediaFormat.getString(str);
        }
        return null;
    }

    @TargetApi(16)
    private static final int getOptionalIntegerV16(android.media.MediaFormat mediaFormat, String str) {
        if (mediaFormat.containsKey(str)) {
            return mediaFormat.getInteger(str);
        }
        return -1;
    }

    static {
        try {
            Class<?> cls = Class.forName("android.media.DataSource");
            DATA_SOURCE_INTERFACE = cls;
            if (cls != null) {
                MtkDebugLogUtil.log("FrameworkSampleSource", "android.media.DataSource(API 22) found", 8);
            }
        } catch (ClassNotFoundException unused) {
            MtkDebugLogUtil.log("FrameworkSampleSource", "android.media.DataSource(API 22) not found", 8);
        }
        try {
            if (Util.SDK_INT == 22) {
                Method method = MediaExtractor.class.getMethod("setDataSource", DATA_SOURCE_INTERFACE);
                setDataSourceMethod = method;
                if (method != null) {
                    MtkDebugLogUtil.log("FrameworkSampleSource", "setDataSource(DataSource dataSource)(API 22) found", 8);
                    return;
                }
                return;
            }
            if (Util.SDK_INT >= 23) {
                Class<?> cls2 = null;
                try {
                    cls2 = Class.forName("android.media.MediaDataSource");
                } catch (ClassNotFoundException unused2) {
                    MtkDebugLogUtil.log("FrameworkSampleSource", "android.media.MediaDataSource(API 23) not found", 8);
                }
                if (cls2 != null) {
                    setDataSourceMethod = MediaExtractor.class.getMethod("setDataSource", cls2);
                }
                if (setDataSourceMethod != null) {
                    Log.d("FrameworkSampleSource", "setDataSource(MediaDataSource dataSource)(API 23) found");
                }
            }
        } catch (NoSuchMethodException unused3) {
            MtkDebugLogUtil.log("FrameworkSampleSource", "setDataSource method(API 22/23) not found", 8);
        }
    }
}
