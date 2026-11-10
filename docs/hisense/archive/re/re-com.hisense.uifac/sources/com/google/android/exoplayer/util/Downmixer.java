package com.google.android.exoplayer.util;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class Downmixer {
    public static void downmix5point1ToStereo(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i) {
        int iPosition = byteBuffer.position();
        while (i > 0) {
            int i2 = (((byteBuffer.get(iPosition + 5) << 8) | (byteBuffer.get(iPosition + 4) & 255)) * 2896) + (((byteBuffer.get(iPosition + 7) << 8) | (byteBuffer.get(iPosition + 6) & 255)) * 2896);
            int i3 = (((byteBuffer.get(iPosition + 1) << 8) | (byteBuffer.get(iPosition) & 255)) << 12) + i2 + (((byteBuffer.get(iPosition + 9) << 8) | (byteBuffer.get(iPosition + 8) & 255)) << 12);
            int i4 = (((byteBuffer.get(iPosition + 3) << 8) | (byteBuffer.get(iPosition + 2) & 255)) << 12) + i2 + (((byteBuffer.get(iPosition + 11) << 8) | (byteBuffer.get(iPosition + 10) & 255)) << 12);
            int i5 = i3 >> 13;
            byteBuffer2.put((byte) (i5 & 255));
            byteBuffer2.put((byte) ((i5 & 65280) >> 8));
            int i6 = i4 >> 13;
            byteBuffer2.put((byte) (i6 & 255));
            byteBuffer2.put((byte) ((i6 & 65280) >> 8));
            i--;
            iPosition += 12;
        }
    }
}
