package com.ju.lib.datacommunication.network.http.address;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public class AppKeySignTool {
    public static final String TAG = "AppKeySignTool";

    public static String getNoSignData(String str, String str2) {
        String strSubstring;
        int iIndexOf;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            int iIndexOf2 = str.indexOf(str2);
            if (iIndexOf2 == -1) {
                return "";
            }
            String strSubstring2 = str.substring(0, iIndexOf2 - 2);
            String strSubstring3 = str.substring(iIndexOf2 + str2.length() + 1);
            int iIndexOf3 = strSubstring3.indexOf("\"");
            if (iIndexOf3 == -1 || (iIndexOf = (strSubstring = strSubstring3.substring(iIndexOf3 + 1)).indexOf("\"")) == -1) {
                return "";
            }
            return strSubstring2 + strSubstring.substring(iIndexOf + 1);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getSignValue(String str, String str2) {
        String strSubstring;
        int iIndexOf;
        String strSubstring2;
        int iIndexOf2;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            int iIndexOf3 = str.indexOf(str2);
            return (iIndexOf3 == -1 || (iIndexOf = (strSubstring = str.substring((iIndexOf3 + str2.length()) + 1)).indexOf("\"")) == -1 || (iIndexOf2 = (strSubstring2 = strSubstring.substring(iIndexOf + 1)).indexOf("\"")) == -1) ? "" : strSubstring2.substring(0, iIndexOf2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
