package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.upstream.BandwidthMeter;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface FormatEvaluator {

    public static final class Evaluation {
        public Format format;
        public int queueSize;
        public int trigger = 1;
    }

    void disable();

    void enable();

    void evaluate(List<? extends MediaChunk> list, long j, Format[] formatArr, Evaluation evaluation);

    public static final class AdaptiveEvaluator implements FormatEvaluator {
        private final float bandwidthFraction;
        private final BandwidthMeter bandwidthMeter;
        private final long maxDurationForQualityDecreaseUs;
        private final int maxInitialBitrate;
        private final long minDurationForQualityIncreaseUs;
        private final long minDurationToRetainAfterDiscardUs;
        private boolean smoothChangeFormat;

        @Override // com.google.android.exoplayer.chunk.FormatEvaluator
        public void disable() {
        }

        @Override // com.google.android.exoplayer.chunk.FormatEvaluator
        public void enable() {
        }

        public AdaptiveEvaluator(BandwidthMeter bandwidthMeter) {
            this(bandwidthMeter, 800000, 10000, 25000, 25000, 0.75f);
        }

        public AdaptiveEvaluator(BandwidthMeter bandwidthMeter, int i, int i2, int i3, int i4, float f) {
            this.bandwidthMeter = bandwidthMeter;
            this.maxInitialBitrate = i;
            this.minDurationForQualityIncreaseUs = ((long) i2) * 1000;
            this.maxDurationForQualityDecreaseUs = ((long) i3) * 1000;
            this.minDurationToRetainAfterDiscardUs = ((long) i4) * 1000;
            this.bandwidthFraction = f;
            this.smoothChangeFormat = false;
        }

        /* JADX WARN: Code duplicated, block: B:45:0x0091  */
        @Override // com.google.android.exoplayer.chunk.FormatEvaluator
        public void evaluate(List<? extends MediaChunk> list, long j, Format[] formatArr, Evaluation evaluation) {
            int i;
            long j2 = list.isEmpty() ? 0L : list.get(list.size() - 1).endTimeUs - j;
            Format format = evaluation.format;
            Format formatDetermineIdealFormat = determineIdealFormat(formatArr, this.bandwidthMeter.getBitrateEstimate());
            boolean z = false;
            boolean z2 = (formatDetermineIdealFormat == null || format == null || formatDetermineIdealFormat.bitrate <= format.bitrate) ? false : true;
            if (formatDetermineIdealFormat != null && format != null && formatDetermineIdealFormat.bitrate < format.bitrate) {
                z = true;
            }
            if (z2) {
                if (this.smoothChangeFormat) {
                    formatDetermineIdealFormat = smoothChangeFormat(formatArr, format, formatDetermineIdealFormat);
                }
                if (j2 < this.minDurationForQualityIncreaseUs) {
                    formatDetermineIdealFormat = format;
                } else if (j2 >= this.minDurationToRetainAfterDiscardUs) {
                    for (int i2 = 1; i2 < list.size(); i2++) {
                        MediaChunk mediaChunk = list.get(i2);
                        if (mediaChunk.startTimeUs - j >= this.minDurationToRetainAfterDiscardUs) {
                            Format format2 = mediaChunk.format;
                            if (format2.bitrate < formatDetermineIdealFormat.bitrate && (i = format2.height) < formatDetermineIdealFormat.height && i < 720 && format2.width < 1280) {
                                evaluation.queueSize = i2;
                                break;
                            }
                        }
                    }
                }
            } else if (z && format != null && j2 >= this.maxDurationForQualityDecreaseUs) {
                formatDetermineIdealFormat = format;
            }
            if (format != null && formatDetermineIdealFormat != format) {
                evaluation.trigger = 3;
            }
            evaluation.format = formatDetermineIdealFormat;
        }

        private Format determineIdealFormat(Format[] formatArr, long j) {
            long j2 = j == -1 ? this.maxInitialBitrate : (long) (j * this.bandwidthFraction);
            for (Format format : formatArr) {
                if (format.bitrate <= j2) {
                    return format;
                }
            }
            return formatArr[formatArr.length - 1];
        }

        private Format smoothChangeFormat(Format[] formatArr, Format format, Format format2) {
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < formatArr.length; i3++) {
                if (formatArr[i3].bitrate == format2.bitrate) {
                    i2 = i3;
                }
                if (formatArr[i3].bitrate == format.bitrate) {
                    i = i3;
                }
            }
            return i - i2 >= 2 ? formatArr[i - 2] : format2;
        }

        public void setSmoothChangeFormat(boolean z) {
            this.smoothChangeFormat = z;
        }
    }
}
