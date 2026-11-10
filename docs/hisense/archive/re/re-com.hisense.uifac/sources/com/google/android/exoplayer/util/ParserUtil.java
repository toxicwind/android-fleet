package com.google.android.exoplayer.util;

import android.util.Log;
import com.ibm.icu.text.CharsetDetector;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class ParserUtil {
    public static boolean isEndTag(XmlPullParser xmlPullParser, String str) throws XmlPullParserException {
        return isEndTag(xmlPullParser) && str.equals(xmlPullParser.getName());
    }

    public static boolean isEndTag(XmlPullParser xmlPullParser) throws XmlPullParserException {
        return 3 == xmlPullParser.getEventType();
    }

    public static boolean isStartTag(XmlPullParser xmlPullParser, String str) throws XmlPullParserException {
        return isStartTag(xmlPullParser) && str.equals(xmlPullParser.getName());
    }

    public static boolean isStartTag(XmlPullParser xmlPullParser) throws XmlPullParserException {
        return 2 == xmlPullParser.getEventType();
    }

    public static String getAttributeValue(XmlPullParser xmlPullParser, String str) {
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (str.equals(xmlPullParser.getAttributeName(i))) {
                return xmlPullParser.getAttributeValue(i);
            }
        }
        return null;
    }

    public static String getCharset(byte[] bArr, int i, int i2) {
        CharsetDetector charsetDetector = new CharsetDetector();
        charsetDetector.setText(bArr);
        String name = charsetDetector.detect().getName();
        Log.d("ParserUtil", "getCharset : " + name);
        return name;
    }
}
