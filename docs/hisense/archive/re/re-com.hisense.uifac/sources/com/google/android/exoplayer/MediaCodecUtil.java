package com.google.android.exoplayer;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public final class MediaCodecUtil {
    private static final DecoderInfo PASSTHROUGH_DECODER_INFO = new DecoderInfo("OMX.google.raw.decoder", null);
    private static final Map<CodecKey, List<DecoderInfo>> decoderInfosCache = new HashMap();
    private static int maxH264DecodableFrameSize = -1;

    private interface MediaCodecListCompat {
        int getCodecCount();

        MediaCodecInfo getCodecInfoAt(int i);

        boolean isSecurePlaybackSupported(String str, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean secureDecodersExplicit();
    }

    private static int avcLevelToMaxFrameSize(int i) {
        if (i == 1 || i == 2) {
            return 25344;
        }
        switch (i) {
            case 8:
            case 16:
            case 32:
                return 101376;
            case 64:
                return 202752;
            case 128:
            case 256:
                return 414720;
            case 512:
                return 921600;
            case 1024:
                return 1310720;
            case 2048:
            case 4096:
                return 2097152;
            case 8192:
                return 2228224;
            case 16384:
                return 5652480;
            case 32768:
                return 9437184;
            default:
                return -1;
        }
    }

    public static class DecoderQueryException extends IOException {
        private DecoderQueryException(Throwable th) {
            super("Failed to query underlying media codecs", th);
        }
    }

    public static DecoderInfo getPassthroughDecoderInfo() {
        return PASSTHROUGH_DECODER_INFO;
    }

    public static DecoderInfo getDecoderInfo(String str, boolean z) throws DecoderQueryException {
        List<DecoderInfo> decoderInfos = getDecoderInfos(str, z, false);
        if (decoderInfos.isEmpty()) {
            return null;
        }
        return decoderInfos.get(0);
    }

    public static synchronized List<DecoderInfo> getDecoderInfos(String str, boolean z, boolean z2) throws DecoderQueryException {
        CodecKey codecKey = new CodecKey(str, z, z2);
        List<DecoderInfo> list = decoderInfosCache.get(codecKey);
        if (list != null) {
            return list;
        }
        List<DecoderInfo> decoderInfosInternal = getDecoderInfosInternal(codecKey, Util.SDK_INT >= 21 ? new MediaCodecListCompatV21(z) : new MediaCodecListCompatV16());
        if (z && decoderInfosInternal.isEmpty() && 21 <= Util.SDK_INT && Util.SDK_INT <= 23) {
            decoderInfosInternal = getDecoderInfosInternal(codecKey, new MediaCodecListCompatV16());
            if (!decoderInfosInternal.isEmpty()) {
                Log.w("MediaCodecUtil", "MediaCodecList API didn't list secure decoder for: " + str + ". Assuming: " + decoderInfosInternal.get(0).name);
            }
        }
        List<DecoderInfo> listUnmodifiableList = Collections.unmodifiableList(decoderInfosInternal);
        decoderInfosCache.put(codecKey, listUnmodifiableList);
        return listUnmodifiableList;
    }

    private static List<DecoderInfo> getDecoderInfosInternal(CodecKey codecKey, MediaCodecListCompat mediaCodecListCompat) throws DecoderQueryException {
        String strSubstring;
        CodecKey codecKey2 = codecKey;
        try {
            ArrayList arrayList = new ArrayList();
            String strSubstring2 = codecKey2.mimeType;
            int i = 0;
            if (strSubstring2 == null || !strSubstring2.startsWith("video/dolby-vision") || strSubstring2.lastIndexOf("#") <= 0) {
                strSubstring = null;
            } else {
                strSubstring = strSubstring2.substring(strSubstring2.lastIndexOf("#") + 1);
                strSubstring2 = strSubstring2.substring(0, strSubstring2.lastIndexOf("#"));
            }
            int codecCount = mediaCodecListCompat.getCodecCount();
            boolean zSecureDecodersExplicit = mediaCodecListCompat.secureDecodersExplicit();
            int i2 = 0;
            while (i2 < codecCount) {
                MediaCodecInfo codecInfoAt = mediaCodecListCompat.getCodecInfoAt(i2);
                String name = codecInfoAt.getName();
                if ((name == null || !name.startsWith("OMX.google") || !codecKey2.preferHWDecoder) && ((name == null || strSubstring == null || name.equals(strSubstring)) && isCodecUsableDecoder(codecInfoAt, name, zSecureDecodersExplicit))) {
                    String[] supportedTypes = codecInfoAt.getSupportedTypes();
                    int length = supportedTypes.length;
                    int i3 = i;
                    while (i3 < length) {
                        String str = supportedTypes[i3];
                        if (str.equalsIgnoreCase(strSubstring2)) {
                            try {
                                MediaCodecInfo.CodecCapabilities capabilitiesForType = codecInfoAt.getCapabilitiesForType(str);
                                boolean zIsSecurePlaybackSupported = mediaCodecListCompat.isSecurePlaybackSupported(strSubstring2, capabilitiesForType);
                                if ((zSecureDecodersExplicit && codecKey2.secure == zIsSecurePlaybackSupported) || (!zSecureDecodersExplicit && !codecKey2.secure)) {
                                    arrayList.add(new DecoderInfo(name, capabilitiesForType));
                                } else if (!zSecureDecodersExplicit && zIsSecurePlaybackSupported) {
                                    arrayList.add(new DecoderInfo(name + ".secure", capabilitiesForType));
                                    return arrayList;
                                }
                            } catch (Exception e) {
                                if (Util.SDK_INT <= 23 && !arrayList.isEmpty()) {
                                    Log.e("MediaCodecUtil", "Skipping codec " + name + " (failed to query capabilities)");
                                } else {
                                    Log.e("MediaCodecUtil", "Failed to query codec " + name + " (" + str + ")");
                                    throw e;
                                }
                            }
                        }
                        i3++;
                        codecKey2 = codecKey;
                        mediaCodecListCompat = mediaCodecListCompat;
                    }
                }
                i2++;
                i = 0;
                codecKey2 = codecKey;
            }
            return arrayList;
        } catch (Exception e2) {
            throw new DecoderQueryException(e2);
        }
    }

    private static boolean isCodecUsableDecoder(MediaCodecInfo mediaCodecInfo, String str, boolean z) {
        String str2;
        String str3;
        if (mediaCodecInfo.isEncoder() || (!z && str.endsWith(".secure"))) {
            return false;
        }
        if (Util.SDK_INT < 21 && ("CIPAACDecoder".equals(str) || "CIPMP3Decoder".equals(str) || "CIPVorbisDecoder".equals(str) || "CIPAMRNBDecoder".equals(str) || "AACDecoder".equals(str) || "MP3Decoder".equals(str))) {
            return false;
        }
        if (Util.SDK_INT < 18 && "OMX.SEC.MP3.Decoder".equals(str)) {
            return false;
        }
        if (Util.SDK_INT < 18 && "OMX.MTK.AUDIO.DECODER.AAC".equals(str) && "a70".equals(Util.DEVICE)) {
            return false;
        }
        if (Util.SDK_INT == 16 && Util.DEVICE != null && "OMX.qcom.audio.decoder.mp3".equals(str) && ("dlxu".equals(Util.DEVICE) || "protou".equals(Util.DEVICE) || "ville".equals(Util.DEVICE) || "villeplus".equals(Util.DEVICE) || "villec2".equals(Util.DEVICE) || Util.DEVICE.startsWith("gee") || "C6602".equals(Util.DEVICE) || "C6603".equals(Util.DEVICE) || "C6606".equals(Util.DEVICE) || "C6616".equals(Util.DEVICE) || "L36h".equals(Util.DEVICE) || "SO-02E".equals(Util.DEVICE))) {
            return false;
        }
        if (Util.SDK_INT == 16 && "OMX.qcom.audio.decoder.aac".equals(str) && ("C1504".equals(Util.DEVICE) || "C1505".equals(Util.DEVICE) || "C1604".equals(Util.DEVICE) || "C1605".equals(Util.DEVICE))) {
            return false;
        }
        if (Util.SDK_INT > 19 || (str3 = Util.DEVICE) == null || !((str3.startsWith("d2") || Util.DEVICE.startsWith("serrano") || Util.DEVICE.startsWith("jflte") || Util.DEVICE.startsWith("santos")) && "samsung".equals(Util.MANUFACTURER) && str.equals("OMX.SEC.vp8.dec"))) {
            return Util.SDK_INT > 19 || (str2 = Util.DEVICE) == null || !str2.startsWith("jflte") || !"OMX.qcom.video.decoder.vp8".equals(str);
        }
        return false;
    }

    @TargetApi(21)
    public static boolean isSizeSupportedV21(String str, boolean z, int i, int i2) throws DecoderQueryException {
        Assertions.checkState(Util.SDK_INT >= 21);
        MediaCodecInfo.VideoCapabilities videoCapabilitiesV21 = getVideoCapabilitiesV21(str, z);
        return videoCapabilitiesV21 != null && videoCapabilitiesV21.isSizeSupported(i, i2);
    }

    @TargetApi(21)
    public static boolean isSizeAndRateSupportedV21(String str, boolean z, int i, int i2, double d) throws DecoderQueryException {
        Assertions.checkState(Util.SDK_INT >= 21);
        MediaCodecInfo.VideoCapabilities videoCapabilitiesV21 = getVideoCapabilitiesV21(str, z);
        return videoCapabilitiesV21 != null && videoCapabilitiesV21.areSizeAndRateSupported(i, i2, d);
    }

    public static int maxH264DecodableFrameSize() throws DecoderQueryException {
        if (maxH264DecodableFrameSize == -1) {
            int iMax = 0;
            DecoderInfo decoderInfo = getDecoderInfo("video/avc", false);
            if (decoderInfo != null) {
                MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr = decoderInfo.capabilities.profileLevels;
                int length = codecProfileLevelArr.length;
                int iMax2 = 0;
                while (iMax < length) {
                    iMax2 = Math.max(avcLevelToMaxFrameSize(codecProfileLevelArr[iMax].level), iMax2);
                    iMax++;
                }
                iMax = Math.max(iMax2, 172800);
            }
            maxH264DecodableFrameSize = iMax;
        }
        return maxH264DecodableFrameSize;
    }

    @TargetApi(21)
    private static MediaCodecInfo.VideoCapabilities getVideoCapabilitiesV21(String str, boolean z) throws DecoderQueryException {
        DecoderInfo decoderInfo = getDecoderInfo(str, z);
        if (decoderInfo == null) {
            return null;
        }
        return decoderInfo.capabilities.getVideoCapabilities();
    }

    @TargetApi(21)
    private static final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        private final int codecKind;
        private MediaCodecInfo[] mediaCodecInfos;

        @Override // com.google.android.exoplayer.MediaCodecUtil.MediaCodecListCompat
        public boolean secureDecodersExplicit() {
            return true;
        }

        public MediaCodecListCompatV21(boolean z) {
            this.codecKind = z ? 1 : 0;
        }

        @Override // com.google.android.exoplayer.MediaCodecUtil.MediaCodecListCompat
        public int getCodecCount() {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos.length;
        }

        @Override // com.google.android.exoplayer.MediaCodecUtil.MediaCodecListCompat
        public MediaCodecInfo getCodecInfoAt(int i) {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos[i];
        }

        @Override // com.google.android.exoplayer.MediaCodecUtil.MediaCodecListCompat
        public boolean isSecurePlaybackSupported(String str, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureSupported("secure-playback");
        }

        private void ensureMediaCodecInfosInitialized() {
            if (this.mediaCodecInfos == null) {
                this.mediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
            }
        }

        public String findDecoderForFormat(android.media.MediaFormat mediaFormat) {
            try {
                return new MediaCodecList(this.codecKind).findDecoderForFormat(mediaFormat);
            } catch (IllegalArgumentException | NullPointerException unused) {
                Log.e("MediaCodecUtil", "findDecoderForFormat with illegal format");
                return null;
            }
        }
    }

    private static final class MediaCodecListCompatV16 implements MediaCodecListCompat {
        @Override // com.google.android.exoplayer.MediaCodecUtil.MediaCodecListCompat
        public boolean secureDecodersExplicit() {
            return false;
        }

        private MediaCodecListCompatV16() {
        }

        @Override // com.google.android.exoplayer.MediaCodecUtil.MediaCodecListCompat
        public int getCodecCount() {
            return MediaCodecList.getCodecCount();
        }

        @Override // com.google.android.exoplayer.MediaCodecUtil.MediaCodecListCompat
        public MediaCodecInfo getCodecInfoAt(int i) {
            return MediaCodecList.getCodecInfoAt(i);
        }

        @Override // com.google.android.exoplayer.MediaCodecUtil.MediaCodecListCompat
        public boolean isSecurePlaybackSupported(String str, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return "video/avc".equals(str);
        }
    }

    private static final class CodecKey {
        public final String mimeType;
        public final boolean preferHWDecoder;
        public final boolean secure;

        public int hashCode() {
            String str = this.mimeType;
            return (((((str == null ? 0 : str.hashCode()) + 31) * 31) + (this.secure ? 1231 : 1237)) * 31) + (this.preferHWDecoder ? 1231 : 1237);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != CodecKey.class) {
                return false;
            }
            CodecKey codecKey = (CodecKey) obj;
            return TextUtils.equals(this.mimeType, codecKey.mimeType) && this.secure == codecKey.secure && this.preferHWDecoder == codecKey.preferHWDecoder;
        }

        public CodecKey(String str, boolean z, boolean z2) {
            this.mimeType = str;
            this.secure = z;
            this.preferHWDecoder = z2;
        }
    }

    public static DecoderInfo getDecoderInfo(String str, boolean z, boolean z2) throws DecoderQueryException {
        List<DecoderInfo> decoderInfos = getDecoderInfos(str, z, z2);
        if (decoderInfos.isEmpty()) {
            return null;
        }
        return decoderInfos.get(0);
    }

    @TargetApi(24)
    public static String findDecoderForFormat(android.media.MediaFormat mediaFormat, boolean z) {
        if (Util.SDK_INT >= 24) {
            return new MediaCodecListCompatV21(z).findDecoderForFormat(mediaFormat);
        }
        return null;
    }
}
