package com.ju.lib.utils.string;

import b.a.a.a.a;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class StringEscapeUtil {
    public static boolean appendLong(char c2, Appendable appendable) throws IOException {
        if (c2 == '\"') {
            appendable.append('\\');
            appendable.append('\"');
            return true;
        }
        if (c2 == '\'' || c2 == '/' || c2 != '\\') {
            appendable.append(c2);
            return false;
        }
        appendable.append('\\');
        appendable.append('\\');
        return true;
    }

    public static void appendShort(char c2, Appendable appendable) throws IOException {
        switch (c2) {
            case '\b':
                appendable.append('\\');
                appendable.append('b');
                break;
            case '\t':
                appendable.append('\\');
                appendable.append('t');
                break;
            case '\n':
                appendable.append('\\');
                appendable.append('n');
                break;
            case 11:
            default:
                if (c2 <= 15) {
                    StringBuilder sbA = a.a("\\u000");
                    sbA.append(Integer.toHexString(c2).toUpperCase());
                    appendable.append(sbA.toString());
                } else {
                    StringBuilder sbA2 = a.a("\\u00");
                    sbA2.append(Integer.toHexString(c2).toUpperCase());
                    appendable.append(sbA2.toString());
                }
                break;
            case '\f':
                appendable.append('\\');
                appendable.append('f');
                break;
            case '\r':
                appendable.append('\\');
                appendable.append('r');
                break;
        }
    }

    public static String escapeJava(String str) {
        return escapeJavaStyleString(str);
    }

    public static String escapeJavaStyleString(String str) {
        if (str == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder(str.length() * 2);
            return escapeJavaStyleString(str, sb) ? sb.toString() : str;
        } catch (IOException | IllegalArgumentException unused) {
            return str;
        }
    }

    public static boolean escapeJavaStyleString(String str, Appendable appendable) throws IOException {
        if (appendable == null) {
            throw new IllegalArgumentException("The Appendable must not be null");
        }
        if (str == null) {
            return false;
        }
        int length = str.length();
        boolean zAppendLong = false;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < ' ') {
                appendShort(cCharAt, appendable);
            } else {
                if (cCharAt <= 255) {
                    zAppendLong = appendLong(cCharAt, appendable);
                } else if (cCharAt > 4095) {
                    appendable.append("\\u").append(Integer.toHexString(cCharAt).toUpperCase());
                } else {
                    appendable.append("\\u0").append(Integer.toHexString(cCharAt).toUpperCase());
                }
            }
            zAppendLong = true;
        }
        return zAppendLong;
    }
}
