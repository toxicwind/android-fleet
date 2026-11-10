package com.google.android.exoplayer.text.ttml;

import android.text.Layout;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.ParserUtil;
import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public final class TtmlParser implements SubtitleParser {
    private final XmlPullParserFactory xmlParserFactory;
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);

    public TtmlParser() {
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            this.xmlParserFactory = xmlPullParserFactoryNewInstance;
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        String str2;
        String str3;
        StringBuilder sb = new StringBuilder();
        sb.append("canParse mimeType = ");
        sb.append(str);
        sb.append(", result = ");
        sb.append("application/ttml+xml".equals(str) || ((str3 = MimeTypes.TEXT_M4T) != null && str3.equals(str)));
        MtkDebugLogUtil.log("TtmlParser", sb.toString(), 16);
        return "application/ttml+xml".equals(str) || ((str2 = MimeTypes.TEXT_M4T) != null && str2.equals(str));
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public TtmlSubtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        ByteArrayInputStream byteArrayInputStream;
        TtmlSubtitle ttmlSubtitle = null;
        try {
            try {
                XmlPullParser xmlPullParserNewPullParser = this.xmlParserFactory.newPullParser();
                Map<String, TtmlStyle> map = new HashMap<>();
                ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr, i, i2);
                try {
                    Map<String, TtmlRegion> map2 = new HashMap<>();
                    map2.put("", new TtmlRegion());
                    byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
                    try {
                        xmlPullParserNewPullParser.setInput(byteArrayInputStream, null);
                        LinkedList linkedList = new LinkedList();
                        int i3 = 0;
                        FrameAndTickRate frameAndTickRates = DEFAULT_FRAME_AND_TICK_RATE;
                        for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.getEventType()) {
                            TtmlNode ttmlNode = (TtmlNode) linkedList.peekLast();
                            if (i3 == 0) {
                                String name = xmlPullParserNewPullParser.getName();
                                if (eventType == 2) {
                                    if ("tt".equals(name)) {
                                        frameAndTickRates = parseFrameAndTickRates(xmlPullParserNewPullParser);
                                    }
                                    if (!isSupportedTag(name)) {
                                        Log.i("TtmlParser", "Ignoring unsupported tag: " + xmlPullParserNewPullParser.getName());
                                    } else if ("head".equals(name)) {
                                        parseHeader(xmlPullParserNewPullParser, map, map2);
                                    } else {
                                        try {
                                            TtmlNode node = parseNode(xmlPullParserNewPullParser, ttmlNode, map2, frameAndTickRates);
                                            linkedList.addLast(node);
                                            if (ttmlNode != null) {
                                                ttmlNode.addChild(node);
                                            }
                                        } catch (ParserException e) {
                                            Log.w("TtmlParser", "Suppressing parser error", e);
                                            i3++;
                                        }
                                    }
                                    i3++;
                                } else if (eventType == 4) {
                                    ttmlNode.addChild(TtmlNode.buildTextNode(xmlPullParserNewPullParser.getText()));
                                } else if (eventType == 3) {
                                    if (xmlPullParserNewPullParser.getName().equals("tt")) {
                                        ttmlSubtitle = new TtmlSubtitle((TtmlNode) linkedList.getLast(), map, map2);
                                    }
                                    linkedList.removeLast();
                                }
                            } else if (eventType == 2) {
                                i3++;
                            } else if (eventType == 3) {
                                i3--;
                            }
                            xmlPullParserNewPullParser.next();
                        }
                        return ttmlSubtitle;
                    } catch (XmlPullParserException e2) {
                        e = e2;
                        if (ttmlSubtitle != null && ttmlSubtitle.hasImage()) {
                            try {
                                byteArrayInputStream.reset();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                            ttmlSubtitle.setInputStream(byteArrayInputStream);
                            return ttmlSubtitle;
                        }
                        throw new ParserException("Unable to parse source", e);
                    }
                } catch (XmlPullParserException e4) {
                    e = e4;
                    byteArrayInputStream = byteArrayInputStream2;
                }
            } catch (IOException e5) {
                throw new IllegalStateException("Unexpected error when reading input.", e5);
            }
        } catch (XmlPullParserException e6) {
            e = e6;
            byteArrayInputStream = null;
        }
    }

    private FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlPullParser) throws ParserException {
        String attributeValue = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRate");
        int i = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        float f = 1.0f;
        String attributeValue2 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] strArrSplit = attributeValue2.split(" ");
            if (strArrSplit.length != 2) {
                throw new ParserException("frameRateMultiplier doesn't have 2 parts");
            }
            f = Integer.parseInt(strArrSplit[0]) / Integer.parseInt(strArrSplit[1]);
        }
        int i2 = DEFAULT_FRAME_AND_TICK_RATE.subFrameRate;
        String attributeValue3 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "subFrameRate");
        if (attributeValue3 != null) {
            i2 = Integer.parseInt(attributeValue3);
        }
        int i3 = DEFAULT_FRAME_AND_TICK_RATE.tickRate;
        String attributeValue4 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "tickRate");
        if (attributeValue4 != null) {
            i3 = Integer.parseInt(attributeValue4);
        }
        return new FrameAndTickRate(i * f, i2, i3);
    }

    private Map<String, TtmlStyle> parseHeader(XmlPullParser xmlPullParser, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2) throws XmlPullParserException, IOException {
        Pair<String, TtmlRegion> regionAttributes;
        do {
            try {
                MtkDebugLogUtil.log("TtmlParser", "parseHeader 1: " + xmlPullParser, 16);
                xmlPullParser.next();
                MtkDebugLogUtil.log("TtmlParser", "parseHeader 2: " + xmlPullParser, 16);
                if (ParserUtil.isStartTag(xmlPullParser, "style")) {
                    String attributeValue = ParserUtil.getAttributeValue(xmlPullParser, "style");
                    TtmlStyle styleAttributes = parseStyleAttributes(xmlPullParser, new TtmlStyle());
                    if (attributeValue != null) {
                        for (String str : parseStyleIds(attributeValue)) {
                            styleAttributes.chain(map.get(str));
                        }
                    }
                    if (styleAttributes.getId() != null) {
                        map.put(styleAttributes.getId(), styleAttributes);
                    }
                } else if (ParserUtil.isStartTag(xmlPullParser, "region") && (regionAttributes = parseRegionAttributes(xmlPullParser)) != null) {
                    map2.put((String) regionAttributes.first, (TtmlRegion) regionAttributes.second);
                }
            } catch (XmlPullParserException e) {
                MtkDebugLogUtil.log("TtmlParser", "parse head fail. xppe" + e, 16);
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "head"));
        return map;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x008b  */
    private Pair<String, TtmlRegion> parseRegionAttributes(XmlPullParser xmlPullParser) {
        float f;
        float f2;
        float f3;
        String attributeValue = ParserUtil.getAttributeValue(xmlPullParser, "id");
        String attributeValue2 = ParserUtil.getAttributeValue(xmlPullParser, "origin");
        String attributeValue3 = ParserUtil.getAttributeValue(xmlPullParser, "extent");
        if (attributeValue2 == null || attributeValue == null) {
            return null;
        }
        Matcher matcher = PERCENTAGE_COORDINATES.matcher(attributeValue2);
        if (matcher.matches()) {
            try {
                f = Float.parseFloat(matcher.group(1)) / 100.0f;
                f2 = Float.parseFloat(matcher.group(2)) / 100.0f;
            } catch (NumberFormatException e) {
                Log.w("TtmlParser", "Ignoring region with malformed origin: '" + attributeValue2 + "'", e);
                f2 = Float.MIN_VALUE;
                f = Float.MIN_VALUE;
            }
        } else {
            f2 = Float.MIN_VALUE;
            f = Float.MIN_VALUE;
        }
        if (attributeValue3 != null) {
            Matcher matcher2 = PERCENTAGE_COORDINATES.matcher(attributeValue3);
            if (matcher2.matches()) {
                try {
                    f3 = Float.parseFloat(matcher2.group(1)) / 100.0f;
                } catch (NumberFormatException e2) {
                    Log.w("TtmlParser", "Ignoring malformed region extent: '" + attributeValue3 + "'", e2);
                    f3 = Float.MIN_VALUE;
                }
            } else {
                f3 = Float.MIN_VALUE;
            }
        } else {
            f3 = Float.MIN_VALUE;
        }
        if (f != Float.MIN_VALUE) {
            return new Pair<>(attributeValue, new TtmlRegion(f, f2, 0, f3));
        }
        return null;
    }

    private String[] parseStyleIds(String str) {
        return str.split("\\s+");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private TtmlStyle parseStyleAttributes(XmlPullParser xmlPullParser, TtmlStyle ttmlStyle) {
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            String attributeValue = xmlPullParser.getAttributeValue(i);
            byte b = -1;
            switch (xmlPullParser.getAttributeName(i)) {
                case "id":
                    if (!"style".equals(xmlPullParser.getName())) {
                        break;
                    } else {
                        ttmlStyle = createIfNull(ttmlStyle);
                        ttmlStyle.setId(attributeValue);
                        break;
                    }
                    break;
                case "backgroundColor":
                    ttmlStyle = createIfNull(ttmlStyle);
                    try {
                        ttmlStyle.setBackgroundColor(TtmlColorParser.parseColor(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused) {
                        Log.w("TtmlParser", "failed parsing background value: '" + attributeValue + "'");
                        break;
                    }
                    break;
                case "color":
                    ttmlStyle = createIfNull(ttmlStyle);
                    try {
                        ttmlStyle.setFontColor(TtmlColorParser.parseColor(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused2) {
                        Log.w("TtmlParser", "failed parsing color value: '" + attributeValue + "'");
                        break;
                    }
                    break;
                case "fontFamily":
                    ttmlStyle = createIfNull(ttmlStyle);
                    ttmlStyle.setFontFamily(attributeValue);
                    break;
                case "fontSize":
                    try {
                        ttmlStyle = createIfNull(ttmlStyle);
                        parseFontSize(attributeValue, ttmlStyle);
                        break;
                    } catch (ParserException unused3) {
                        Log.w("TtmlParser", "failed parsing fontSize value: '" + attributeValue + "'");
                        break;
                    }
                    break;
                case "fontWeight":
                    ttmlStyle = createIfNull(ttmlStyle);
                    ttmlStyle.setBold("bold".equalsIgnoreCase(attributeValue));
                    break;
                case "fontStyle":
                    ttmlStyle = createIfNull(ttmlStyle);
                    ttmlStyle.setItalic("italic".equalsIgnoreCase(attributeValue));
                    break;
                case "textAlign":
                    String lowerInvariant = Util.toLowerInvariant(attributeValue);
                    switch (lowerInvariant.hashCode()) {
                        case -1364013995:
                            if (lowerInvariant.equals("center")) {
                                b = 4;
                            }
                            break;
                        case 100571:
                            if (lowerInvariant.equals("end")) {
                                b = 3;
                            }
                            break;
                        case 3317767:
                            if (lowerInvariant.equals("left")) {
                                b = 0;
                            }
                            break;
                        case 108511772:
                            if (lowerInvariant.equals("right")) {
                                b = 2;
                            }
                            break;
                        case 109757538:
                            if (lowerInvariant.equals("start")) {
                                b = 1;
                            }
                            break;
                    }
                    if (b == 0) {
                        ttmlStyle = createIfNull(ttmlStyle);
                        ttmlStyle.setTextAlign(Layout.Alignment.ALIGN_NORMAL);
                        break;
                    } else {
                        if (b == 1) {
                            ttmlStyle = createIfNull(ttmlStyle);
                            ttmlStyle.setTextAlign(Layout.Alignment.ALIGN_NORMAL);
                        } else if (b == 2) {
                            ttmlStyle = createIfNull(ttmlStyle);
                            ttmlStyle.setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
                        } else if (b == 3) {
                            ttmlStyle = createIfNull(ttmlStyle);
                            ttmlStyle.setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
                        } else if (b == 4) {
                            ttmlStyle = createIfNull(ttmlStyle);
                            ttmlStyle.setTextAlign(Layout.Alignment.ALIGN_CENTER);
                        }
                        break;
                    }
                    break;
                case "textDecoration":
                    String lowerInvariant2 = Util.toLowerInvariant(attributeValue);
                    switch (lowerInvariant2.hashCode()) {
                        case -1461280213:
                            if (lowerInvariant2.equals("nounderline")) {
                                b = 3;
                            }
                            break;
                        case -1026963764:
                            if (lowerInvariant2.equals("underline")) {
                                b = 2;
                            }
                            break;
                        case 913457136:
                            if (lowerInvariant2.equals("nolinethrough")) {
                                b = 1;
                            }
                            break;
                        case 1679736913:
                            if (lowerInvariant2.equals("linethrough")) {
                                b = 0;
                            }
                            break;
                    }
                    if (b == 0) {
                        ttmlStyle = createIfNull(ttmlStyle);
                        ttmlStyle.setLinethrough(true);
                        break;
                    } else {
                        if (b == 1) {
                            ttmlStyle = createIfNull(ttmlStyle);
                            ttmlStyle.setLinethrough(false);
                        } else if (b == 2) {
                            ttmlStyle = createIfNull(ttmlStyle);
                            ttmlStyle.setUnderline(true);
                        } else if (b == 3) {
                            ttmlStyle = createIfNull(ttmlStyle);
                            ttmlStyle.setUnderline(false);
                        }
                        break;
                    }
                    break;
            }
        }
        return ttmlStyle;
    }

    private TtmlStyle createIfNull(TtmlStyle ttmlStyle) {
        return ttmlStyle == null ? new TtmlStyle() : ttmlStyle;
    }

    /* JADX WARN: Code duplicated, block: B:58:0x00fd  */
    private TtmlNode parseNode(XmlPullParser xmlPullParser, TtmlNode ttmlNode, Map<String, TtmlRegion> map, FrameAndTickRate frameAndTickRate) throws ParserException {
        long j;
        long j2;
        int attributeCount = xmlPullParser.getAttributeCount();
        TtmlStyle styleAttributes = parseStyleAttributes(xmlPullParser, null);
        int i = -1;
        long timeExpression = -1;
        long timeExpression2 = -1;
        long timeExpression3 = 0;
        String[] strArr = null;
        for (int i2 = 0; i2 < attributeCount; i2++) {
            String attributeName = xmlPullParser.getAttributeName(i2);
            String attributeValue = xmlPullParser.getAttributeValue(i2);
            MtkDebugLogUtil.log("TtmlParser", "attr = " + attributeName + "value = " + attributeValue, 16);
            if ("begin".equals(attributeName)) {
                timeExpression = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if ("end".equals(attributeName)) {
                timeExpression2 = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if ("dur".equals(attributeName)) {
                timeExpression3 = parseTimeExpression(attributeValue, frameAndTickRate);
            } else {
                if ("style".equals(attributeName)) {
                    String[] styleIds = parseStyleIds(attributeValue);
                    if (styleIds.length > 0) {
                        strArr = styleIds;
                    }
                } else if (attributeName.equals("backgroundImage")) {
                    try {
                        String strReplaceFirst = attributeValue.replaceFirst("^.*:", "");
                        if (strReplaceFirst.endsWith(".png")) {
                            try {
                                i = Integer.parseInt(strReplaceFirst.substring(0, strReplaceFirst.length() - 4));
                            } catch (Exception e) {
                                e = e;
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                } else if ("region".equals(attributeName)) {
                    map.containsKey(attributeValue);
                }
            }
        }
        if (ttmlNode != null) {
            long j3 = ttmlNode.startTimeUs;
            j = -1;
            if (j3 != -1) {
                if (timeExpression != -1) {
                    timeExpression += j3;
                }
                if (timeExpression2 != -1) {
                    timeExpression2 += ttmlNode.startTimeUs;
                }
            }
        } else {
            j = -1;
        }
        long j4 = timeExpression;
        if (timeExpression2 != j) {
            j2 = timeExpression2;
        } else if (timeExpression3 > 0) {
            j2 = j4 + timeExpression3;
        } else if (ttmlNode != null) {
            long j5 = ttmlNode.endTimeUs;
            if (j5 != j) {
                j2 = j5;
            } else {
                j2 = timeExpression2;
            }
        } else {
            j2 = timeExpression2;
        }
        MtkDebugLogUtil.log("TtmlParser", "getName = " + xmlPullParser.getName() + "startTime = " + j4 + " endTime = " + j2 + "pngIndex = " + i, 16);
        return TtmlNode.buildNode(xmlPullParser.getName(), j4, j2, styleAttributes, strArr, i);
    }

    private static boolean isSupportedTag(String str) {
        return str.equals("tt") || str.equals("head") || str.equals("body") || str.equals(MtkTvConfigTypeBase.S639_CFG_LANG_DIV) || str.equals("p") || str.equals("span") || str.equals("br") || str.equals("style") || str.equals("styling") || str.equals("layout") || str.equals("region") || str.equals("metadata") || str.equals("smpte:image") || str.equals("smpte:data") || str.equals("smpte:information");
    }

    private static void parseFontSize(String str, TtmlStyle ttmlStyle) throws ParserException {
        Matcher matcher;
        String[] strArrSplit = str.split("\\s+");
        if (strArrSplit.length == 1) {
            matcher = FONT_SIZE.matcher(str);
        } else if (strArrSplit.length == 2) {
            matcher = FONT_SIZE.matcher(strArrSplit[1]);
            Log.w("TtmlParser", "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            throw new ParserException("Invalid number of entries for fontSize: " + strArrSplit.length + ".");
        }
        if (matcher.matches()) {
            String strGroup = matcher.group(3);
            byte b = -1;
            int iHashCode = strGroup.hashCode();
            if (iHashCode != 37) {
                if (iHashCode != 3240) {
                    if (iHashCode == 3592 && strGroup.equals("px")) {
                        b = 0;
                    }
                } else if (strGroup.equals("em")) {
                    b = 1;
                }
            } else if (strGroup.equals("%")) {
                b = 2;
            }
            if (b == 0) {
                ttmlStyle.setFontSizeUnit(1);
            } else if (b == 1) {
                ttmlStyle.setFontSizeUnit(2);
            } else if (b == 2) {
                ttmlStyle.setFontSizeUnit(3);
            } else {
                throw new ParserException("Invalid unit for fontSize: '" + strGroup + "'.");
            }
            ttmlStyle.setFontSize(Float.valueOf(matcher.group(1)).floatValue());
            return;
        }
        throw new ParserException("Invalid expression for fontSize: '" + str + "'.");
    }

    private static long parseTimeExpression(String str, FrameAndTickRate frameAndTickRate) throws ParserException {
        double d;
        double d2;
        double d3;
        Matcher matcher = CLOCK_TIME.matcher(str);
        if (matcher.matches()) {
            double d4 = (Long.parseLong(matcher.group(1)) * 3600) + (Long.parseLong(matcher.group(2)) * 60) + Long.parseLong(matcher.group(3));
            String strGroup = matcher.group(4);
            double d5 = d4 + (strGroup != null ? Double.parseDouble(strGroup) : 0.0d);
            String strGroup2 = matcher.group(5);
            double d6 = d5 + (strGroup2 != null ? Long.parseLong(strGroup2) / frameAndTickRate.effectiveFrameRate : 0.0d);
            String strGroup3 = matcher.group(6);
            d = d6 + (strGroup3 != null ? (Long.parseLong(strGroup3) / ((double) frameAndTickRate.subFrameRate)) / ((double) frameAndTickRate.effectiveFrameRate) : 0.0d);
        } else {
            Matcher matcher2 = OFFSET_TIME.matcher(str);
            if (matcher2.matches()) {
                d = Double.parseDouble(matcher2.group(1));
                String strGroup4 = matcher2.group(2);
                if (strGroup4.equals("h")) {
                    d3 = 3600.0d;
                } else if (strGroup4.equals("m")) {
                    d3 = 60.0d;
                } else if (!strGroup4.equals("s")) {
                    if (strGroup4.equals("ms")) {
                        d2 = 1000.0d;
                    } else if (strGroup4.equals("f")) {
                        d2 = frameAndTickRate.effectiveFrameRate;
                    } else if (strGroup4.equals("t")) {
                        d2 = frameAndTickRate.tickRate;
                    }
                    d /= d2;
                }
                d *= d3;
            } else {
                throw new ParserException("Malformed time expression: " + str);
            }
        }
        return (long) (d * 1000000.0d);
    }

    private static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        FrameAndTickRate(float f, int i, int i2) {
            this.effectiveFrameRate = f;
            this.subFrameRate = i;
            this.tickRate = i2;
        }
    }
}
