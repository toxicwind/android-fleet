package com.google.android.exoplayer.demo;

import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class DemoUtil {
    public static final String[] SUBTITLE_SUPPORT = {".ass", ".idx", ".sami", ".smi", ".srt", ".ssa", ".sub", ".sup", ".ttml", ".txt", ".webvtt"};

    public static String getSubtitleMimeType(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.endsWith(".smi") || lowerCase.endsWith(".sami")) {
            return "text/ext/smi";
        }
        if (lowerCase.endsWith(".srt")) {
            return "text/ext/srt";
        }
        if (lowerCase.endsWith(".ssa") || lowerCase.endsWith(".ass")) {
            return "text/ext/ssa";
        }
        if (lowerCase.endsWith(".sub")) {
            return "text/ext/sub";
        }
        if (lowerCase.endsWith(".sup")) {
            return "text/ext/pgs";
        }
        if (lowerCase.endsWith(".ttml")) {
            return "application/ttml+xml";
        }
        if (lowerCase.endsWith(".txt")) {
            return "text/ext/txt";
        }
        if (lowerCase.endsWith(".webvtt")) {
            return "text/vtt";
        }
        if (lowerCase.endsWith(".idx")) {
            return "text/ext/vobsub";
        }
        return null;
    }

    public static String[] scanExternalSubtitle(Uri uri) {
        String strDecode;
        File file;
        if ("file".equals(uri.getScheme())) {
            strDecode = uri.getPath();
        } else {
            try {
                strDecode = URLDecoder.decode(uri.toString(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                strDecode = null;
            }
        }
        if (strDecode == null) {
            return null;
        }
        Log.d("DemoUtil", "[scanExternalSubtitle] Uri path = " + strDecode);
        File file2 = new File(strDecode);
        File parentFile = file2.getParentFile();
        String strSubstring = file2.getName().substring(0, file2.getName().lastIndexOf("."));
        ArrayList arrayList = new ArrayList();
        if (parentFile.exists()) {
            Log.d("DemoUtil", "[scanExternalSubtitle] Parent file exists: " + parentFile);
            String[] strArr = SUBTITLE_SUPPORT;
            int length = strArr.length;
            boolean z = false;
            for (int i = 0; i < length; i++) {
                String str = strArr[i];
                File[] fileArrListFiles = parentFile.listFiles();
                int length2 = fileArrListFiles.length;
                int i2 = 0;
                while (i2 < length2) {
                    File file3 = fileArrListFiles[i2];
                    if (file3.isFile() && file3.getName().toLowerCase().endsWith(str)) {
                        file = parentFile;
                        String strSubstring2 = file3.getName().substring(0, file3.getName().lastIndexOf("."));
                        if (strSubstring2 != null && strSubstring2.startsWith(strSubstring)) {
                            Log.d("DemoUtil", "[scanExternalSubtitle] subtitleFile = " + file3 + " suffix = " + str);
                            if (".idx".equals(str)) {
                                z = true;
                            }
                            if (!z || !".sub".equals(str)) {
                                arrayList.add(Uri.fromFile(file3).toString());
                            }
                        }
                    } else {
                        file = parentFile;
                    }
                    i2++;
                    parentFile = file;
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
