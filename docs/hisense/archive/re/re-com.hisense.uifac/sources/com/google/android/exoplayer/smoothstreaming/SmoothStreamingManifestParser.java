package com.google.android.exoplayer.smoothstreaming;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer.upstream.UriLoadable;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.CodecSpecificDataUtil;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public class SmoothStreamingManifestParser implements UriLoadable.Parser<SmoothStreamingManifest> {
    public static String drmHeader;
    private final XmlPullParserFactory xmlParserFactory;

    public SmoothStreamingManifestParser() {
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer.upstream.UriLoadable.Parser
    public SmoothStreamingManifest parse(String str, InputStream inputStream) throws IOException {
        try {
            XmlPullParser xmlPullParserNewPullParser = this.xmlParserFactory.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStream, null);
            return (SmoothStreamingManifest) new SmoothStreamMediaParser(null, str).parse(xmlPullParserNewPullParser);
        } catch (XmlPullParserException e) {
            throw new ParserException(e);
        }
    }

    public static class MissingFieldException extends ParserException {
        public MissingFieldException(String str) {
            super("Missing required field: " + str);
        }
    }

    private static abstract class ElementParser {
        private final String baseUri;
        private final List<Pair<String, Object>> normalizedAttributes = new LinkedList();
        private final ElementParser parent;
        private final String tag;

        protected void addChild(Object obj) {
        }

        protected abstract Object build();

        protected boolean handleChildInline(String str) {
            return false;
        }

        protected void parseEndTag(XmlPullParser xmlPullParser) throws ParserException {
        }

        protected abstract void parseStartTag(XmlPullParser xmlPullParser) throws ParserException;

        protected void parseText(XmlPullParser xmlPullParser) throws ParserException {
        }

        public ElementParser(ElementParser elementParser, String str, String str2) {
            this.parent = elementParser;
            this.baseUri = str;
            this.tag = str2;
        }

        public final Object parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            boolean z = false;
            int i = 0;
            while (true) {
                int eventType = xmlPullParser.getEventType();
                if (eventType == 1) {
                    return null;
                }
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    if (this.tag.equals(name)) {
                        parseStartTag(xmlPullParser);
                        z = true;
                    } else if (z) {
                        if (i > 0) {
                            i++;
                        } else if (handleChildInline(name)) {
                            parseStartTag(xmlPullParser);
                        } else {
                            ElementParser elementParserNewChildParser = newChildParser(this, name, this.baseUri);
                            if (elementParserNewChildParser == null) {
                                i = 1;
                            } else {
                                addChild(elementParserNewChildParser.parse(xmlPullParser));
                            }
                        }
                    }
                } else if (eventType != 3) {
                    if (eventType == 4 && z && i == 0) {
                        parseText(xmlPullParser);
                    }
                } else if (!z) {
                    continue;
                } else if (i > 0) {
                    i--;
                } else {
                    String name2 = xmlPullParser.getName();
                    parseEndTag(xmlPullParser);
                    if (!handleChildInline(name2)) {
                        return build();
                    }
                }
                xmlPullParser.next();
            }
        }

        private ElementParser newChildParser(ElementParser elementParser, String str, String str2) {
            if ("QualityLevel".equals(str)) {
                return new TrackElementParser(elementParser, str2);
            }
            if ("Protection".equals(str)) {
                return new ProtectionElementParser(elementParser, str2);
            }
            if ("StreamIndex".equals(str)) {
                return new StreamElementParser(elementParser, str2);
            }
            return null;
        }

        protected final void putNormalizedAttribute(String str, Object obj) {
            this.normalizedAttributes.add(Pair.create(str, obj));
        }

        protected final Object getNormalizedAttribute(String str) {
            for (int i = 0; i < this.normalizedAttributes.size(); i++) {
                Pair<String, Object> pair = this.normalizedAttributes.get(i);
                if (((String) pair.first).equals(str)) {
                    return pair.second;
                }
            }
            ElementParser elementParser = this.parent;
            if (elementParser == null) {
                return null;
            }
            return elementParser.getNormalizedAttribute(str);
        }

        protected final String parseRequiredString(XmlPullParser xmlPullParser, String str) throws MissingFieldException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                return attributeValue;
            }
            throw new MissingFieldException(str);
        }

        protected final int parseInt(XmlPullParser xmlPullParser, String str, int i) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue == null) {
                return i;
            }
            try {
                return Integer.parseInt(attributeValue);
            } catch (NumberFormatException e) {
                throw new ParserException(e);
            }
        }

        protected final int parseRequiredInt(XmlPullParser xmlPullParser, String str) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                try {
                    return Integer.parseInt(attributeValue);
                } catch (NumberFormatException e) {
                    throw new ParserException(e);
                }
            }
            throw new MissingFieldException(str);
        }

        protected final long parseLong(XmlPullParser xmlPullParser, String str, long j) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue == null) {
                return j;
            }
            try {
                return Long.parseLong(attributeValue);
            } catch (NumberFormatException e) {
                throw new ParserException(e);
            }
        }

        protected final long parseRequiredLong(XmlPullParser xmlPullParser, String str) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                try {
                    return Long.parseLong(attributeValue);
                } catch (NumberFormatException e) {
                    throw new ParserException(e);
                }
            }
            throw new MissingFieldException(str);
        }

        protected final boolean parseBoolean(XmlPullParser xmlPullParser, String str, boolean z) {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            return attributeValue != null ? Boolean.parseBoolean(attributeValue) : z;
        }
    }

    private static class SmoothStreamMediaParser extends ElementParser {
        private long duration;
        private long dvrWindowLength;
        private boolean isLive;
        private int lookAheadCount;
        private int majorVersion;
        private int minorVersion;
        private SmoothStreamingManifest.ProtectionElement protectionElement;
        private List<SmoothStreamingManifest.StreamElement> streamElements;
        private long timescale;

        public SmoothStreamMediaParser(ElementParser elementParser, String str) {
            super(elementParser, str, "SmoothStreamingMedia");
            this.lookAheadCount = -1;
            this.protectionElement = null;
            this.streamElements = new LinkedList();
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public void parseStartTag(XmlPullParser xmlPullParser) throws ParserException {
            this.majorVersion = parseRequiredInt(xmlPullParser, "MajorVersion");
            this.minorVersion = parseRequiredInt(xmlPullParser, "MinorVersion");
            this.timescale = parseLong(xmlPullParser, "TimeScale", 10000000L);
            this.duration = parseRequiredLong(xmlPullParser, "Duration");
            this.dvrWindowLength = parseLong(xmlPullParser, "DVRWindowLength", 0L);
            this.lookAheadCount = parseInt(xmlPullParser, "LookaheadCount", -1);
            this.isLive = parseBoolean(xmlPullParser, "IsLive", false);
            putNormalizedAttribute("TimeScale", Long.valueOf(this.timescale));
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public void addChild(Object obj) {
            if (obj instanceof SmoothStreamingManifest.StreamElement) {
                this.streamElements.add((SmoothStreamingManifest.StreamElement) obj);
            } else if (obj instanceof SmoothStreamingManifest.ProtectionElement) {
                Assertions.checkState(this.protectionElement == null);
                this.protectionElement = (SmoothStreamingManifest.ProtectionElement) obj;
            }
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public Object build() {
            SmoothStreamingManifest.StreamElement[] streamElementArr = new SmoothStreamingManifest.StreamElement[this.streamElements.size()];
            this.streamElements.toArray(streamElementArr);
            return new SmoothStreamingManifest(this.majorVersion, this.minorVersion, this.timescale, this.duration, this.dvrWindowLength, this.lookAheadCount, this.isLive, this.protectionElement, streamElementArr);
        }
    }

    private static class ProtectionElementParser extends ElementParser {
        private boolean inProtectionHeader;
        private byte[] initData;
        private UUID uuid;

        public ProtectionElementParser(ElementParser elementParser, String str) {
            super(elementParser, str, "Protection");
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public boolean handleChildInline(String str) {
            return "ProtectionHeader".equals(str);
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public void parseStartTag(XmlPullParser xmlPullParser) {
            if ("ProtectionHeader".equals(xmlPullParser.getName())) {
                this.inProtectionHeader = true;
                this.uuid = UUID.fromString(stripRedundantChar(stripCurlyBraces(xmlPullParser.getAttributeValue(null, "SystemID"))));
            }
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public void parseText(XmlPullParser xmlPullParser) {
            if (this.inProtectionHeader) {
                this.initData = Base64.decode(xmlPullParser.getText(), 0);
                if (TextUtils.isEmpty(SmoothStreamingManifestParser.drmHeader)) {
                    return;
                }
                int length = SmoothStreamingManifestParser.drmHeader.length();
                byte[] bytes = SmoothStreamingManifestParser.drmHeader.getBytes();
                this.initData = new byte[length * 2];
                for (int i = 0; i < length; i++) {
                    byte[] bArr = this.initData;
                    int i2 = i * 2;
                    bArr[i2] = bytes[i];
                    bArr[i2 + 1] = 0;
                }
            }
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public void parseEndTag(XmlPullParser xmlPullParser) {
            if ("ProtectionHeader".equals(xmlPullParser.getName())) {
                this.inProtectionHeader = false;
            }
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public Object build() {
            UUID uuid = this.uuid;
            return new SmoothStreamingManifest.ProtectionElement(uuid, PsshAtomUtil.buildPsshAtom(uuid, this.initData));
        }

        private static String stripCurlyBraces(String str) {
            return (str.charAt(0) == '{' && str.charAt(str.length() - 1) == '}') ? str.substring(1, str.length() - 1) : str;
        }

        private static String stripRedundantChar(String str) {
            return (str.charAt(str.length() + (-1)) == 'L' || str.charAt(str.length() + (-1)) == 'l') ? str.substring(0, str.length() - 1) : str;
        }
    }

    private static class StreamElementParser extends ElementParser {
        private final String baseUri;
        private int displayHeight;
        private int displayWidth;
        private String language;
        private long lastChunkDuration;
        private int maxHeight;
        private int maxWidth;
        private String name;
        private int qualityLevels;
        private ArrayList<Long> startTimes;
        private String subType;
        private long timescale;
        private final List<SmoothStreamingManifest.TrackElement> tracks;
        private int type;
        private String url;

        public StreamElementParser(ElementParser elementParser, String str) {
            super(elementParser, str, "StreamIndex");
            this.baseUri = str;
            this.tracks = new LinkedList();
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public boolean handleChildInline(String str) {
            return "c".equals(str);
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public void parseStartTag(XmlPullParser xmlPullParser) throws ParserException {
            if ("c".equals(xmlPullParser.getName())) {
                parseStreamFragmentStartTag(xmlPullParser);
            } else {
                parseStreamElementStartTag(xmlPullParser);
            }
        }

        private void parseStreamFragmentStartTag(XmlPullParser xmlPullParser) throws ParserException {
            int size = this.startTimes.size();
            long jLongValue = parseLong(xmlPullParser, "t", -1L);
            int i = 1;
            if (jLongValue == -1) {
                if (size == 0) {
                    jLongValue = 0;
                } else if (this.lastChunkDuration != -1) {
                    jLongValue = this.lastChunkDuration + this.startTimes.get(size - 1).longValue();
                } else {
                    throw new ParserException("Unable to infer start time");
                }
            }
            this.startTimes.add(Long.valueOf(jLongValue));
            this.lastChunkDuration = parseLong(xmlPullParser, "d", -1L);
            long j = parseLong(xmlPullParser, "r", 1L);
            if (j > 1 && this.lastChunkDuration == -1) {
                throw new ParserException("Repeated chunk with unspecified duration");
            }
            while (true) {
                long j2 = i;
                if (j2 >= j) {
                    return;
                }
                this.startTimes.add(Long.valueOf((this.lastChunkDuration * j2) + jLongValue));
                i++;
            }
        }

        private void parseStreamElementStartTag(XmlPullParser xmlPullParser) throws ParserException {
            int type = parseType(xmlPullParser);
            this.type = type;
            putNormalizedAttribute("Type", Integer.valueOf(type));
            if (this.type == 2) {
                this.subType = parseRequiredString(xmlPullParser, "Subtype");
            } else {
                this.subType = xmlPullParser.getAttributeValue(null, "Subtype");
            }
            this.name = xmlPullParser.getAttributeValue(null, "Name");
            this.qualityLevels = parseInt(xmlPullParser, "QualityLevels", -1);
            this.url = parseRequiredString(xmlPullParser, "Url");
            this.maxWidth = parseInt(xmlPullParser, "MaxWidth", -1);
            this.maxHeight = parseInt(xmlPullParser, "MaxHeight", -1);
            this.displayWidth = parseInt(xmlPullParser, "DisplayWidth", -1);
            this.displayHeight = parseInt(xmlPullParser, "DisplayHeight", -1);
            String attributeValue = xmlPullParser.getAttributeValue(null, "Language");
            this.language = attributeValue;
            putNormalizedAttribute("Language", attributeValue);
            long j = parseInt(xmlPullParser, "TimeScale", -1);
            this.timescale = j;
            if (j == -1) {
                this.timescale = ((Long) getNormalizedAttribute("TimeScale")).longValue();
            }
            this.startTimes = new ArrayList<>();
        }

        private int parseType(XmlPullParser xmlPullParser) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, "Type");
            if (attributeValue != null) {
                if ("audio".equalsIgnoreCase(attributeValue)) {
                    return 0;
                }
                if ("video".equalsIgnoreCase(attributeValue)) {
                    return 1;
                }
                if ("text".equalsIgnoreCase(attributeValue)) {
                    return 2;
                }
                throw new ParserException("Invalid key value[" + attributeValue + "]");
            }
            throw new MissingFieldException("Type");
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public void addChild(Object obj) {
            if (obj instanceof SmoothStreamingManifest.TrackElement) {
                this.tracks.add((SmoothStreamingManifest.TrackElement) obj);
            }
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public Object build() {
            SmoothStreamingManifest.TrackElement[] trackElementArr = new SmoothStreamingManifest.TrackElement[this.tracks.size()];
            this.tracks.toArray(trackElementArr);
            return new SmoothStreamingManifest.StreamElement(this.baseUri, this.url, this.type, this.subType, this.timescale, this.name, this.qualityLevels, this.maxWidth, this.maxHeight, this.displayWidth, this.displayHeight, this.language, trackElementArr, this.startTimes, this.lastChunkDuration);
        }
    }

    private static class TrackElementParser extends ElementParser {
        private int bitrate;
        private int channels;
        private final List<byte[]> csd;
        private int index;
        private String language;
        private int maxHeight;
        private int maxWidth;
        private String mimeType;
        private int samplingRate;

        public TrackElementParser(ElementParser elementParser, String str) {
            super(elementParser, str, "QualityLevel");
            this.csd = new LinkedList();
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public void parseStartTag(XmlPullParser xmlPullParser) throws ParserException {
            String strFourCCToMimeType;
            int iIntValue = ((Integer) getNormalizedAttribute("Type")).intValue();
            this.index = parseInt(xmlPullParser, "Index", -1);
            this.bitrate = parseRequiredInt(xmlPullParser, "Bitrate");
            this.language = (String) getNormalizedAttribute("Language");
            if (iIntValue == 1) {
                this.maxHeight = parseRequiredInt(xmlPullParser, "MaxHeight");
                this.maxWidth = parseRequiredInt(xmlPullParser, "MaxWidth");
                this.mimeType = fourCCToMimeType(parseRequiredString(xmlPullParser, "FourCC"));
            } else {
                this.maxHeight = -1;
                this.maxWidth = -1;
                String attributeValue = xmlPullParser.getAttributeValue(null, "FourCC");
                if (attributeValue != null) {
                    strFourCCToMimeType = fourCCToMimeType(attributeValue);
                } else {
                    strFourCCToMimeType = iIntValue == 0 ? "audio/mp4a-latm" : null;
                }
                this.mimeType = strFourCCToMimeType;
            }
            if (iIntValue == 0) {
                this.samplingRate = parseRequiredInt(xmlPullParser, "SamplingRate");
                this.channels = parseRequiredInt(xmlPullParser, "Channels");
            } else {
                this.samplingRate = -1;
                this.channels = -1;
            }
            String attributeValue2 = xmlPullParser.getAttributeValue(null, "CodecPrivateData");
            if (attributeValue2 == null || attributeValue2.length() <= 0) {
                return;
            }
            byte[] bytesFromHexString = Util.getBytesFromHexString(attributeValue2);
            byte[][] bArrSplitNalUnits = CodecSpecificDataUtil.splitNalUnits(bytesFromHexString);
            if (bArrSplitNalUnits == null) {
                this.csd.add(bytesFromHexString);
                return;
            }
            for (byte[] bArr : bArrSplitNalUnits) {
                this.csd.add(bArr);
            }
        }

        @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser.ElementParser
        public Object build() {
            byte[][] bArr;
            if (this.csd.isEmpty()) {
                bArr = null;
            } else {
                bArr = new byte[this.csd.size()][];
                this.csd.toArray(bArr);
            }
            return new SmoothStreamingManifest.TrackElement(this.index, this.bitrate, this.mimeType, bArr, this.maxWidth, this.maxHeight, this.samplingRate, this.channels, this.language);
        }

        private static String fourCCToMimeType(String str) {
            if (str.equalsIgnoreCase("H264") || str.equalsIgnoreCase("X264") || str.equalsIgnoreCase("AVC1") || str.equalsIgnoreCase("DAVC")) {
                return "video/avc";
            }
            if (str.equalsIgnoreCase("AAC") || str.equalsIgnoreCase("AACL") || str.equalsIgnoreCase("AACH") || str.equalsIgnoreCase("AACP")) {
                return "audio/mp4a-latm";
            }
            if (str.equalsIgnoreCase("TTML")) {
                return "application/ttml+xml";
            }
            if (str.equalsIgnoreCase("ac-3") || str.equalsIgnoreCase("dac3")) {
                return "audio/ac3";
            }
            if (str.equalsIgnoreCase("ec-3") || str.equalsIgnoreCase("dec3")) {
                return "audio/eac3";
            }
            if (str.equalsIgnoreCase("dtsc")) {
                return "audio/vnd.dts";
            }
            if (str.equalsIgnoreCase("dtsh") || str.equalsIgnoreCase("dtsl")) {
                return "audio/vnd.dts.hd";
            }
            if (str.equalsIgnoreCase("dtse")) {
                return "audio/vnd.dts.hd;profile=lbr";
            }
            if (str.equalsIgnoreCase("opus")) {
                return "audio/opus";
            }
            if (str.equalsIgnoreCase("WVC1")) {
                return "video/wvc1";
            }
            if (str.equalsIgnoreCase("EAC3")) {
                return "audio/eac3";
            }
            if (str.equalsIgnoreCase("WMAP")) {
                return "audio/x-ms-wmapro";
            }
            return null;
        }
    }

    public SmoothStreamingManifestParser(String str) {
        this();
        drmHeader = str;
    }
}
