package com.google.android.exoplayer;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import com.google.android.exoplayer.util.Util;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public final class DecoderInfo {
    public final boolean adaptive;
    public final MediaCodecInfo.CodecCapabilities capabilities;
    public final String name;
    public final boolean tunneled;

    DecoderInfo(String str, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        this.name = str;
        this.capabilities = codecCapabilities;
        this.adaptive = isAdaptive(codecCapabilities);
        this.tunneled = isTunneled(codecCapabilities);
    }

    private static boolean isAdaptive(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities != null && Util.SDK_INT >= 19 && isAdaptiveV19(codecCapabilities);
    }

    @TargetApi(19)
    private static boolean isAdaptiveV19(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("adaptive-playback");
    }

    private static boolean isTunneled(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities != null && Util.SDK_INT >= 21 && isTunneledV21(codecCapabilities);
    }

    @TargetApi(21)
    private static boolean isTunneledV21(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("tunneled-playback");
    }
}
