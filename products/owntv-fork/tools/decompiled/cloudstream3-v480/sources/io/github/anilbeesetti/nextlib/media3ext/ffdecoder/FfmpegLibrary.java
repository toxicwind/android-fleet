package io.github.anilbeesetti.nextlib.media3ext.ffdecoder;

import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.LibraryLoader;
import androidx.media3.common.util.Log;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.common.base.Ascii;

/* JADX INFO: loaded from: classes7.dex */
public final class FfmpegLibrary {
    private static final LibraryLoader LOADER;
    private static final String TAG = "FfmpegLibrary";
    private static int inputBufferPaddingSize;
    private static String version;

    private static native int ffmpegGetInputBufferPaddingSize();

    private static native String ffmpegGetVersion();

    private static native boolean ffmpegHasDecoder(String str);

    static {
        MediaLibraryInfo.registerModule("media3.decoder.ffmpeg");
        LOADER = new LibraryLoader("media3ext") { // from class: io.github.anilbeesetti.nextlib.media3ext.ffdecoder.FfmpegLibrary.1
            @Override // androidx.media3.common.util.LibraryLoader
            protected void loadLibrary(String str) {
                System.loadLibrary(str);
            }
        };
        inputBufferPaddingSize = -1;
    }

    private FfmpegLibrary() {
    }

    public static void setLibraries(String... strArr) {
        LOADER.setLibraries(strArr);
    }

    public static boolean isAvailable() {
        return LOADER.isAvailable();
    }

    public static String getVersion() {
        if (!isAvailable()) {
            return null;
        }
        if (version == null) {
            version = ffmpegGetVersion();
        }
        return version;
    }

    public static int getInputBufferPaddingSize() {
        if (!isAvailable()) {
            return -1;
        }
        if (inputBufferPaddingSize == -1) {
            inputBufferPaddingSize = ffmpegGetInputBufferPaddingSize();
        }
        return inputBufferPaddingSize;
    }

    public static boolean supportsFormat(String str) {
        String codecName;
        if (!isAvailable() || (codecName = getCodecName(str)) == null) {
            return false;
        }
        if (ffmpegHasDecoder(codecName)) {
            return true;
        }
        Log.w(TAG, "No " + codecName + " decoder available. Check the FFmpeg build configuration.");
        return false;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    static String getCodecName(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -2123537834:
                if (str.equals("audio/eac3-joc")) {
                    b = 0;
                }
                break;
            case -1662541442:
                if (str.equals("video/hevc")) {
                    b = 1;
                }
                break;
            case -1662382439:
                if (str.equals("video/mpeg")) {
                    b = 2;
                }
                break;
            case -1606874997:
                if (str.equals("audio/amr-wb")) {
                    b = 3;
                }
                break;
            case -1095064472:
                if (str.equals("audio/vnd.dts")) {
                    b = 4;
                }
                break;
            case -1003765268:
                if (str.equals("audio/vorbis")) {
                    b = 5;
                }
                break;
            case -432837260:
                if (str.equals("audio/mpeg-L1")) {
                    b = 6;
                }
                break;
            case -432837259:
                if (str.equals("audio/mpeg-L2")) {
                    b = 7;
                }
                break;
            case -53558318:
                if (str.equals("audio/mp4a-latm")) {
                    b = 8;
                }
                break;
            case 5751993:
                if (str.equals("video/mpeg2")) {
                    b = 9;
                }
                break;
            case 187078296:
                if (str.equals("audio/ac3")) {
                    b = 10;
                }
                break;
            case 1331836730:
                if (str.equals("video/avc")) {
                    b = 11;
                }
                break;
            case 1503095341:
                if (str.equals("audio/3gpp")) {
                    b = Ascii.FF;
                }
                break;
            case 1504470054:
                if (str.equals("audio/alac")) {
                    b = Ascii.CR;
                }
                break;
            case 1504578661:
                if (str.equals("audio/eac3")) {
                    b = Ascii.SO;
                }
                break;
            case 1504619009:
                if (str.equals("audio/flac")) {
                    b = Ascii.SI;
                }
                break;
            case 1504831518:
                if (str.equals("audio/mpeg")) {
                    b = Ascii.DLE;
                }
                break;
            case 1504891608:
                if (str.equals("audio/opus")) {
                    b = 17;
                }
                break;
            case 1505942594:
                if (str.equals("audio/vnd.dts.hd")) {
                    b = Ascii.DC2;
                }
                break;
            case 1556697186:
                if (str.equals("audio/true-hd")) {
                    b = 19;
                }
                break;
            case 1599127256:
                if (str.equals("video/x-vnd.on2.vp8")) {
                    b = Ascii.DC4;
                }
                break;
            case 1599127257:
                if (str.equals("video/x-vnd.on2.vp9")) {
                    b = Ascii.NAK;
                }
                break;
            case 1903231877:
                if (str.equals("audio/g711-alaw")) {
                    b = Ascii.SYN;
                }
                break;
            case 1903589369:
                if (str.equals("audio/g711-mlaw")) {
                    b = Ascii.ETB;
                }
                break;
        }
        switch (b) {
            case 0:
            case 14:
                return "eac3";
            case 1:
                return "hevc";
            case 2:
                return "mpegvideo";
            case 3:
                return "amrwb";
            case 4:
            case 18:
                return "dca";
            case 5:
                return "vorbis";
            case 6:
            case 7:
            case 16:
                return HlsSegmentFormat.MP3;
            case 8:
                return HlsSegmentFormat.AAC;
            case 9:
                return "mpeg2video";
            case 10:
                return HlsSegmentFormat.AC3;
            case 11:
                return "h264";
            case 12:
                return "amrnb";
            case 13:
                return "alac";
            case 15:
                return "flac";
            case 17:
                return "opus";
            case 19:
                return "truehd";
            case 20:
                return "libvpx";
            case 21:
                return "libvpx-vp9";
            case 22:
                return "pcm_alaw";
            case 23:
                return "pcm_mulaw";
            default:
                return null;
        }
    }
}
