package com.google.android.exoplayer.text.smi;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.DefaultSubtitle;
import com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.ParserUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public class SmiParser implements SubtitleParser, ExternalSubtitleCharsetEncoding {
    private String encodingType;
    private final boolean filterTags;
    private final XmlPullParserFactory xmlParserFactory;

    public SmiParser() {
        this(true);
    }

    public SmiParser(boolean z) {
        this.filterTags = z;
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public Subtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        if (this.encodingType == null) {
            this.encodingType = ParserUtil.getCharset(bArr, i, i2);
        }
        ArrayList arrayList = new ArrayList();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
        try {
            try {
                try {
                    XmlPullParser xmlPullParserNewPullParser = this.xmlParserFactory.newPullParser();
                    xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#relaxed", true);
                    xmlPullParserNewPullParser.setInput(byteArrayInputStream, this.encodingType);
                    SmiCue smiCue = null;
                    boolean z = false;
                    for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.getEventType()) {
                        String name = xmlPullParserNewPullParser.getName();
                        if (eventType == 2) {
                            if ("sync".equalsIgnoreCase(name)) {
                                MtkDebugLogUtil.log("SmiParser", "smicue = " + smiCue, 16);
                                if (!z) {
                                    MtkDebugLogUtil.log("SmiParser", "warning this sync is not in body tag!!!!! ", 16);
                                }
                                if (smiCue != null) {
                                    arrayList.add(smiCue);
                                }
                                smiCue = new SmiCue();
                                int attributeCount = xmlPullParserNewPullParser.getAttributeCount();
                                for (int i3 = 0; i3 < attributeCount; i3++) {
                                    String attributeName = xmlPullParserNewPullParser.getAttributeName(i3);
                                    String attributeValue = xmlPullParserNewPullParser.getAttributeValue(i3);
                                    if (attributeName.equalsIgnoreCase("start")) {
                                        smiCue.startTime = parseTimestampUs(attributeValue);
                                    } else if (attributeName.equalsIgnoreCase("end")) {
                                        smiCue.endTime = parseTimestampUs(attributeValue);
                                    }
                                }
                            } else if ("p".equalsIgnoreCase(name)) {
                                MtkDebugLogUtil.log("SmiParser", "P tag: start ~~", 16);
                            } else if ("body".equalsIgnoreCase(name)) {
                                z = true;
                            } else if (!"br".equalsIgnoreCase(name)) {
                                MtkDebugLogUtil.log("SmiParser", "Ignoring unsupported tag: " + xmlPullParserNewPullParser.getName(), 16);
                            } else if (smiCue != null && smiCue.text != null && !smiCue.text.isEmpty()) {
                                smiCue.text += "\n";
                            }
                        } else if (eventType == 4) {
                            if (smiCue != null) {
                                smiCue.text += processCueText(xmlPullParserNewPullParser.getText());
                            }
                        } else if (eventType == 3) {
                            if ("body".equalsIgnoreCase(name)) {
                                if (smiCue != null) {
                                    arrayList.add(smiCue);
                                }
                                smiCue = null;
                                z = false;
                            } else if ("sync".equalsIgnoreCase(name)) {
                                if (smiCue != null) {
                                    arrayList.add(smiCue);
                                }
                                smiCue = null;
                            }
                            MtkDebugLogUtil.log("SmiParser", "end TAG : " + name, 16);
                        }
                        xmlPullParserNewPullParser.next();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
                try {
                    byteArrayInputStream.close();
                } catch (IOException unused) {
                }
                if (arrayList.size() > 0) {
                    String[] strArr = new String[arrayList.size()];
                    long[] jArr = new long[arrayList.size() * 2];
                    for (int i4 = 0; i4 < arrayList.size(); i4++) {
                        int i5 = i4 * 2;
                        SmiCue smiCue2 = (SmiCue) arrayList.get(i4);
                        jArr[i5] = smiCue2.startTime;
                        long j = smiCue2.endTime;
                        if (j != -1) {
                            jArr[i5 + 1] = j;
                        } else {
                            int i6 = i4 + 1;
                            if (i6 >= arrayList.size()) {
                                jArr[i5 + 1] = smiCue2.startTime + 1000000;
                            } else {
                                jArr[i5 + 1] = ((SmiCue) arrayList.get(i6)).startTime;
                            }
                        }
                        strArr[i4] = smiCue2.text;
                    }
                    return new DefaultSubtitle(strArr, 0L, jArr);
                }
                return new DefaultSubtitle();
            } catch (IOException e2) {
                throw new ParserException(e2);
            }
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (IOException unused2) {
            }
            throw th;
        }
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        boolean z = "text/ext/smi".equals(str) || "text/smi".equals(str);
        MtkDebugLogUtil.log("SmiParser", "canParse  type = " + str + " can = " + z, 16);
        return z;
    }

    protected String processCueText(String str) {
        if (this.filterTags) {
            return str.replaceAll("\\<.*?>", "").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&nbsp;", " ").replaceAll("&amp;", "&").replaceAll("&quota;", "\"").replaceAll("\n", "").trim();
        }
        return str.trim();
    }

    private static long parseTimestampUs(String str) throws NumberFormatException {
        MtkDebugLogUtil.log("SmiParser", " parseTimestampUs  = " + str, 16);
        return Long.parseLong(str) * 1000;
    }

    private static class SmiCue {
        public long endTime;
        public long startTime;
        public String text;

        public SmiCue() {
            this(-1L, -1L, "");
        }

        public SmiCue(long j, long j2, String str) {
            this.startTime = j;
            this.endTime = j2;
            this.text = str;
        }

        public String toString() {
            return "SmiCue [startTime=" + this.startTime + ", endTime=" + this.endTime + ", text=" + this.text + "]";
        }
    }

    @Override // com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding
    public void setCharsetEncoding(String str) {
        this.encodingType = str;
    }
}
