package com.google.android.exoplayer.dash.mpd;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class UrlTemplate {
    private final int identifierCount;
    private final String[] identifierFormatTags;
    private final int[] identifiers;
    private final String[] urlPieces;

    public static UrlTemplate compile(String str) {
        String[] strArr = new String[5];
        int[] iArr = new int[4];
        String[] strArr2 = new String[4];
        return new UrlTemplate(strArr, iArr, strArr2, parseTemplate(str, strArr, iArr, strArr2));
    }

    private UrlTemplate(String[] strArr, int[] iArr, String[] strArr2, int i) {
        this.urlPieces = strArr;
        this.identifiers = iArr;
        this.identifierFormatTags = strArr2;
        this.identifierCount = i;
    }

    public String buildUri(String str, int i, int i2, long j) {
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        while (true) {
            int i4 = this.identifierCount;
            if (i3 < i4) {
                sb.append(this.urlPieces[i3]);
                int[] iArr = this.identifiers;
                if (iArr[i3] == 1) {
                    sb.append(str);
                } else if (iArr[i3] == 2) {
                    sb.append(String.format(Locale.US, this.identifierFormatTags[i3], Integer.valueOf(i)));
                } else if (iArr[i3] == 3) {
                    sb.append(String.format(Locale.US, this.identifierFormatTags[i3], Integer.valueOf(i2)));
                } else if (iArr[i3] == 4) {
                    sb.append(String.format(Locale.US, this.identifierFormatTags[i3], Long.valueOf(j)));
                }
                i3++;
            } else {
                sb.append(this.urlPieces[i4]);
                return sb.toString();
            }
        }
    }

    private static int parseTemplate(String str, String[] strArr, int[] iArr, String[] strArr2) {
        String strSubstring;
        strArr[0] = "";
        int length = 0;
        int i = 0;
        while (length < str.length()) {
            int iIndexOf = str.indexOf("$", length);
            if (iIndexOf == -1) {
                strArr[i] = strArr[i] + str.substring(length);
                length = str.length();
            } else if (iIndexOf != length) {
                strArr[i] = strArr[i] + str.substring(length, iIndexOf);
                length = iIndexOf;
            } else if (str.startsWith("$$", length)) {
                strArr[i] = strArr[i] + "$";
                length += 2;
            } else {
                int i2 = length + 1;
                int iIndexOf2 = str.indexOf("$", i2);
                String strSubstring2 = str.substring(i2, iIndexOf2);
                if (strSubstring2.equals("RepresentationID")) {
                    iArr[i] = 1;
                } else {
                    int iIndexOf3 = strSubstring2.indexOf("%0");
                    if (iIndexOf3 != -1) {
                        strSubstring = strSubstring2.substring(iIndexOf3);
                        if (!strSubstring.endsWith("d")) {
                            strSubstring = strSubstring + "d";
                        }
                        strSubstring2 = strSubstring2.substring(0, iIndexOf3);
                    } else {
                        strSubstring = "%01d";
                    }
                    if (strSubstring2.equals("Number")) {
                        iArr[i] = 2;
                    } else if (strSubstring2.equals("Bandwidth")) {
                        iArr[i] = 3;
                    } else if (strSubstring2.equals("Time")) {
                        iArr[i] = 4;
                    } else {
                        throw new IllegalArgumentException("Invalid template: " + str);
                    }
                    strArr2[i] = strSubstring;
                }
                i++;
                strArr[i] = "";
                length = iIndexOf2 + 1;
            }
        }
        return i;
    }
}
