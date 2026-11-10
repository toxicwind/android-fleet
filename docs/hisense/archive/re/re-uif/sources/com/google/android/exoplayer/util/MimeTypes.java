package com.google.android.exoplayer.util;

/* JADX INFO: loaded from: classes.dex */
public final class MimeTypes {
    public static String TEXT_M4T;

    public static boolean isAudio(String str) {
        return getTopLevelType(str).equals("audio");
    }

    public static boolean isVideo(String str) {
        return getTopLevelType(str).equals("video");
    }

    public static boolean isText(String str) {
        return getTopLevelType(str).equals("text");
    }

    private static String getTopLevelType(String str) {
        int iIndexOf = str.indexOf(47);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("Invalid mime type: " + str);
        }
        return str.substring(0, iIndexOf);
    }

    public static String getVideoMediaMimeType(String str) {
        if (str == null) {
            return "video/x-unknown";
        }
        for (String str2 : str.split(",")) {
            String strTrim = str2.trim();
            if (strTrim.startsWith("avc1") || strTrim.startsWith("avc3")) {
                return "video/avc";
            }
            if (strTrim.startsWith("hev1") || strTrim.startsWith("hvc1")) {
                return "video/hevc";
            }
            if (strTrim.startsWith("vp9")) {
                return "video/x-vnd.on2.vp9";
            }
            if (strTrim.startsWith("vp8")) {
                return "video/x-vnd.on2.vp8";
            }
        }
        return "video/x-unknown";
    }

    public static String getAudioMediaMimeType(String str) {
        if (str == null) {
            return "audio/x-unknown";
        }
        for (String str2 : str.split(",")) {
            String strTrim = str2.trim();
            if (strTrim.startsWith("mp4a")) {
                return "audio/mp4a-latm";
            }
            if (strTrim.startsWith("ac-3") || strTrim.startsWith("dac3")) {
                return "audio/ac3";
            }
            if (strTrim.startsWith("ec-3") || strTrim.startsWith("dec3")) {
                return "audio/eac3";
            }
            if (strTrim.startsWith("dtsc")) {
                return "audio/vnd.dts";
            }
            if (strTrim.startsWith("dtsh") || strTrim.startsWith("dtsl")) {
                return "audio/vnd.dts.hd";
            }
            if (strTrim.startsWith("dtse")) {
                return "audio/vnd.dts.hd;profile=lbr";
            }
            if (strTrim.startsWith("opus")) {
                return "audio/opus";
            }
            if (strTrim.startsWith("vorbis")) {
                return "audio/vorbis";
            }
            if (strTrim.startsWith("ac4")) {
                return "audio/ac4";
            }
        }
        return "audio/x-unknown";
    }

    public static boolean isTsSi(String str) {
        return str.equals("application/ts-si");
    }
}
