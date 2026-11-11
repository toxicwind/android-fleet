package com.hippo.unifile;

import android.text.TextUtils;
import android.webkit.MimeTypeMap;

/* JADX INFO: loaded from: classes6.dex */
class Utils {
    private Utils() {
    }

    static String getTypeForName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str.substring(iLastIndexOf + 1).toLowerCase());
            return !TextUtils.isEmpty(mimeTypeFromExtension) ? mimeTypeFromExtension : "application/octet-stream";
        }
        return "application/octet-stream";
    }

    static String normalize(String str) {
        int length = str.length();
        char[] charArray = str.toCharArray();
        int i = 0;
        char c = 0;
        int i2 = 0;
        while (i < length) {
            char c2 = charArray[i];
            if (c2 != '/' || c != '/') {
                charArray[i2] = c2;
                i2++;
            }
            i++;
            c = c2;
        }
        if (c == '/' && length > 1) {
            i2--;
        }
        return i2 != length ? new String(charArray, 0, i2) : str;
    }

    static String resolve(String str, String str2) {
        if (str2.length() == 0 || str2.equals("/")) {
            return str;
        }
        if (str2.charAt(0) == '/') {
            if (str.equals("/")) {
                return str2;
            }
            return str + str2;
        }
        if (str.equals("/")) {
            return str + str2;
        }
        return str + '/' + str2;
    }

    static boolean equals(String str, String str2, boolean z) {
        if (z) {
            return str.equalsIgnoreCase(str2);
        }
        return str.equals(str2);
    }
}
