package org.schabi.newpipe.extractor.stream;

import java.io.Serializable;
import java.util.List;
import kotlin.UByte$$ExternalSyntheticBackport0;

/* JADX INFO: loaded from: classes8.dex */
public final class Frameset implements Serializable {
    private final int durationPerFrame;
    private final int frameHeight;
    private final int frameWidth;
    private final int framesPerPageX;
    private final int framesPerPageY;
    private final int totalCount;
    private final List<String> urls;

    public Frameset(List<String> list, int i, int i2, int i3, int i4, int i5, int i6) {
        this.urls = list;
        this.totalCount = i3;
        this.durationPerFrame = i4;
        this.frameWidth = i;
        this.frameHeight = i2;
        this.framesPerPageX = i5;
        this.framesPerPageY = i6;
    }

    public List<String> getUrls() {
        return this.urls;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getFramesPerPageX() {
        return this.framesPerPageX;
    }

    public int getFramesPerPageY() {
        return this.framesPerPageY;
    }

    public int getFrameWidth() {
        return this.frameWidth;
    }

    public int getFrameHeight() {
        return this.frameHeight;
    }

    public int getDurationPerFrame() {
        return this.durationPerFrame;
    }

    public int[] getFrameBoundsAt(long j) {
        if (j >= 0) {
            int i = this.totalCount;
            int i2 = this.durationPerFrame;
            if (j <= ((long) (i + 1)) * ((long) i2)) {
                int i3 = this.framesPerPageX * this.framesPerPageY;
                int iMin = Math.min((int) (j / ((long) i2)), i);
                int i4 = iMin % i3;
                int iM$4 = UByte$$ExternalSyntheticBackport0.m$4(i4, this.framesPerPageX);
                int i5 = i4 % this.framesPerPageY;
                int iM$5 = UByte$$ExternalSyntheticBackport0.m$4(iMin, i3);
                int i6 = this.frameWidth;
                int i7 = this.frameHeight;
                return new int[]{iM$5, i5 * i6, iM$4 * i7, (i5 * i6) + i6, (iM$4 * i7) + i7};
            }
        }
        return new int[]{0, 0, 0, this.frameWidth, this.frameHeight};
    }
}
