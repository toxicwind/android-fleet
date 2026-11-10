package com.google.android.exoplayer.dash.mpd;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.drm.StreamingDrmSessionManager;
import com.google.android.exoplayer.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer.upstream.UriLoadable;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.ParserUtil;
import com.google.android.exoplayer.util.UriUtil;
import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public class MediaPresentationDescriptionParser extends DefaultHandler implements UriLoadable.Parser<MediaPresentationDescription> {
    private static final Pattern FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private final byte[] actionToken;
    private final String contentId;
    private HashMap<String, String> marlinContentIds = new HashMap<>();
    private long singlePeriodDurationMs = 0;
    private final XmlPullParserFactory xmlParserFactory;

    protected void parseAdaptationSetChild(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer.upstream.UriLoadable.Parser
    public MediaPresentationDescription parse(String str, InputStream inputStream) throws IOException {
        try {
            XmlPullParser xmlPullParserNewPullParser = this.xmlParserFactory.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStream, null);
            if (xmlPullParserNewPullParser.next() != 2 || !"MPD".equals(xmlPullParserNewPullParser.getName())) {
                throw new ParserException("inputStream does not contain a valid media presentation description");
            }
            return parseMediaPresentationDescription(xmlPullParserNewPullParser, str);
        } catch (ParseException e) {
            throw new ParserException(e);
        } catch (XmlPullParserException e2) {
            throw new ParserException(e2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x007c  */
    protected MediaPresentationDescription parseMediaPresentationDescription(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException, ParseException {
        MediaPresentationDescriptionParser mediaPresentationDescriptionParser = this;
        long dateTime = parseDateTime(xmlPullParser, "availabilityStartTime", -1L);
        long duration = parseDuration(xmlPullParser, "mediaPresentationDuration", -1L);
        mediaPresentationDescriptionParser.singlePeriodDurationMs = duration;
        long duration2 = parseDuration(xmlPullParser, "minBufferTime", -1L);
        String attributeValue = xmlPullParser.getAttributeValue(null, "type");
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "profiles");
        boolean zEquals = attributeValue != null ? attributeValue.equals("dynamic") : false;
        long duration3 = zEquals ? parseDuration(xmlPullParser, "minimumUpdatePeriod", -1L) : -1L;
        long duration4 = zEquals ? parseDuration(xmlPullParser, "timeShiftBufferDepth", -1L) : -1L;
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        boolean z2 = false;
        long j = zEquals ? -1L : 0L;
        UtcTimingElement utcTiming = null;
        String strNextText = null;
        String baseUrl = str;
        boolean z3 = false;
        while (true) {
            xmlPullParser.next();
            String str2 = attributeValue2;
            if (ParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                if (z3) {
                    baseUrl = baseUrl;
                    j = j;
                } else {
                    baseUrl = parseBaseUrl(xmlPullParser, baseUrl);
                    duration3 = duration3;
                    z3 = true;
                }
            } else if (ParserUtil.isStartTag(xmlPullParser, "UTCTiming")) {
                if (z) {
                    baseUrl = baseUrl;
                    j = j;
                } else {
                    duration3 = duration3;
                    utcTiming = parseUtcTiming(xmlPullParser);
                    z = true;
                }
            } else if (ParserUtil.isStartTag(xmlPullParser, "Location")) {
                duration3 = duration3;
                strNextText = xmlPullParser.nextText();
            } else {
                if (!ParserUtil.isStartTag(xmlPullParser, "Period") || z2) {
                    baseUrl = baseUrl;
                } else {
                    Pair<Period, Long> period = mediaPresentationDescriptionParser.parsePeriod(xmlPullParser, baseUrl, j);
                    Period period2 = (Period) period.first;
                    if (period2.startMs != -1) {
                        long jLongValue = ((Long) period.second).longValue();
                        duration3 = duration3;
                        j = jLongValue == -1 ? -1L : jLongValue + period2.startMs;
                        arrayList.add(period2);
                        baseUrl = baseUrl;
                    } else {
                        if (!zEquals) {
                            throw new ParserException("Unable to determine start of period " + arrayList.size());
                        }
                        z2 = true;
                    }
                }
                j = j;
            }
            if (ParserUtil.isEndTag(xmlPullParser, "MPD")) {
                if (duration != -1) {
                    j = duration;
                } else if (j == -1) {
                    if (!zEquals) {
                        throw new ParserException("Unable to determine duration of static manifest.");
                    }
                    j = duration;
                }
                if (arrayList.isEmpty()) {
                    throw new ParserException("No periods found.");
                }
                return buildMediaPresentationDescription(dateTime, j, duration2, zEquals, duration3, duration4, utcTiming, strNextText, arrayList, str2);
            }
            mediaPresentationDescriptionParser = this;
            attributeValue2 = str2;
            duration3 = duration3;
        }
    }

    protected MediaPresentationDescription buildMediaPresentationDescription(long j, long j2, long j3, boolean z, long j4, long j5, UtcTimingElement utcTimingElement, String str, List<Period> list, String str2) {
        return new MediaPresentationDescription(j, j2, j3, z, j4, j5, utcTimingElement, str, list, str2, this.marlinContentIds);
    }

    protected UtcTimingElement parseUtcTiming(XmlPullParser xmlPullParser) {
        return buildUtcTimingElement(xmlPullParser.getAttributeValue(null, "schemeIdUri"), xmlPullParser.getAttributeValue(null, "value"));
    }

    protected UtcTimingElement buildUtcTimingElement(String str, String str2) {
        return new UtcTimingElement(str, str2);
    }

    protected Pair<Period, Long> parsePeriod(XmlPullParser xmlPullParser, String str, long j) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "id");
        long duration = parseDuration(xmlPullParser, "start", j);
        long duration2 = parseDuration(xmlPullParser, "duration", -1L);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        SegmentBase segmentTemplate = null;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                if (!z) {
                    str = parseBaseUrl(xmlPullParser, str);
                    z = true;
                }
            } else if (ParserUtil.isStartTag(xmlPullParser, "AdaptationSet")) {
                arrayList.add(parseAdaptationSet(xmlPullParser, str, segmentTemplate));
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                segmentTemplate = parseSegmentBase(xmlPullParser, null);
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                segmentTemplate = parseSegmentList(xmlPullParser, null);
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                segmentTemplate = parseSegmentTemplate(xmlPullParser, null);
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "Period"));
        return Pair.create(buildPeriod(attributeValue, duration, arrayList), Long.valueOf(duration2));
    }

    protected Period buildPeriod(String str, long j, List<AdaptationSet> list) {
        return new Period(str, j, list);
    }

    protected AdaptationSet parseAdaptationSet(XmlPullParser xmlPullParser, String str, SegmentBase segmentBase) throws XmlPullParserException, IOException {
        String strCheckLanguageConsistency;
        String str2;
        ContentProtectionsBuilder contentProtectionsBuilder;
        String str3;
        String str4;
        int i;
        int i2;
        XmlPullParser xmlPullParser2;
        ArrayList arrayList;
        ArrayList arrayList2;
        SegmentBase segmentTemplate;
        XmlPullParser xmlPullParser3 = xmlPullParser;
        int i3 = parseInt(xmlPullParser3, "id", -1);
        int contentType = parseContentType(xmlPullParser);
        String str5 = null;
        String attributeValue = xmlPullParser3.getAttributeValue(null, "mimeType");
        String attributeValue2 = xmlPullParser3.getAttributeValue(null, "codecs");
        int i4 = parseInt(xmlPullParser3, "width", -1);
        int i5 = parseInt(xmlPullParser3, "height", -1);
        float frameRate = parseFrameRate(xmlPullParser3, -1.0f);
        int i6 = parseInt(xmlPullParser3, "audioSamplingRate", -1);
        String str6 = "lang";
        String attributeValue3 = xmlPullParser3.getAttributeValue(null, "lang");
        ContentProtectionsBuilder contentProtectionsBuilder2 = new ContentProtectionsBuilder();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        String baseUrl = str;
        SegmentBase segmentBase2 = segmentBase;
        int iCheckContentTypeConsistency = contentType;
        int audioChannelConfiguration = -1;
        String str7 = attributeValue3;
        boolean z = false;
        while (true) {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser3, "BaseURL")) {
                if (!z) {
                    baseUrl = parseBaseUrl(xmlPullParser3, baseUrl);
                    z = true;
                    strCheckLanguageConsistency = str7;
                    arrayList2 = arrayList4;
                    arrayList = arrayList3;
                    contentProtectionsBuilder = contentProtectionsBuilder2;
                    str3 = str6;
                    str4 = str5;
                    xmlPullParser2 = xmlPullParser3;
                    i = i3;
                }
                strCheckLanguageConsistency = str7;
                str2 = baseUrl;
                arrayList2 = arrayList4;
                arrayList = arrayList3;
                contentProtectionsBuilder = contentProtectionsBuilder2;
                str3 = str6;
                str4 = str5;
                xmlPullParser2 = xmlPullParser3;
                i = i3;
                i2 = iCheckContentTypeConsistency;
                iCheckContentTypeConsistency = i2;
                baseUrl = str2;
            } else if (ParserUtil.isStartTag(xmlPullParser3, "ContentProtection")) {
                ContentProtection contentProtection = parseContentProtection(xmlPullParser);
                if (contentProtection != null) {
                    contentProtectionsBuilder2.addAdaptationSetProtection(contentProtection);
                }
                strCheckLanguageConsistency = str7;
                str2 = baseUrl;
                arrayList2 = arrayList4;
                arrayList = arrayList3;
                contentProtectionsBuilder = contentProtectionsBuilder2;
                str3 = str6;
                str4 = str5;
                xmlPullParser2 = xmlPullParser3;
                i = i3;
                i2 = iCheckContentTypeConsistency;
                iCheckContentTypeConsistency = i2;
                baseUrl = str2;
            } else if (ParserUtil.isStartTag(xmlPullParser3, "ContentComponent")) {
                strCheckLanguageConsistency = checkLanguageConsistency(str7, xmlPullParser3.getAttributeValue(str5, str6));
                iCheckContentTypeConsistency = checkContentTypeConsistency(iCheckContentTypeConsistency, parseContentType(xmlPullParser));
                arrayList2 = arrayList4;
                arrayList = arrayList3;
                contentProtectionsBuilder = contentProtectionsBuilder2;
                str3 = str6;
                str4 = str5;
                xmlPullParser2 = xmlPullParser3;
                i = i3;
            } else if (ParserUtil.isStartTag(xmlPullParser3, "Representation")) {
                strCheckLanguageConsistency = str7;
                i = i3;
                ArrayList arrayList5 = arrayList3;
                contentProtectionsBuilder = contentProtectionsBuilder2;
                str3 = str6;
                str4 = str5;
                Representation representation = parseRepresentation(xmlPullParser, baseUrl, attributeValue, attributeValue2, i4, i5, frameRate, audioChannelConfiguration, i6, strCheckLanguageConsistency, segmentBase2, contentProtectionsBuilder);
                contentProtectionsBuilder.endRepresentation();
                int iCheckContentTypeConsistency2 = checkContentTypeConsistency(iCheckContentTypeConsistency, getContentType(representation));
                arrayList5.add(representation);
                iCheckContentTypeConsistency = iCheckContentTypeConsistency2;
                arrayList = arrayList5;
                baseUrl = baseUrl;
                arrayList2 = arrayList4;
                xmlPullParser2 = xmlPullParser;
            } else {
                strCheckLanguageConsistency = str7;
                str2 = baseUrl;
                ArrayList arrayList6 = arrayList4;
                contentProtectionsBuilder = contentProtectionsBuilder2;
                str3 = str6;
                str4 = str5;
                i = i3;
                i2 = iCheckContentTypeConsistency;
                xmlPullParser2 = xmlPullParser;
                arrayList = arrayList3;
                if (ParserUtil.isStartTag(xmlPullParser2, "AudioChannelConfiguration")) {
                    audioChannelConfiguration = parseAudioChannelConfiguration(xmlPullParser);
                } else {
                    if (ParserUtil.isStartTag(xmlPullParser2, "SegmentBase")) {
                        segmentTemplate = parseSegmentBase(xmlPullParser2, (SegmentBase.SingleSegmentBase) segmentBase2);
                    } else if (ParserUtil.isStartTag(xmlPullParser2, "SegmentList")) {
                        segmentTemplate = parseSegmentList(xmlPullParser2, (SegmentBase.SegmentList) segmentBase2);
                    } else if (ParserUtil.isStartTag(xmlPullParser2, "SegmentTemplate")) {
                        segmentTemplate = parseSegmentTemplate(xmlPullParser2, (SegmentBase.SegmentTemplate) segmentBase2);
                    } else {
                        if (ParserUtil.isStartTag(xmlPullParser)) {
                            parseAdaptationSetChild(xmlPullParser);
                        } else {
                            if (ParserUtil.isStartTag(xmlPullParser2, "Role")) {
                                arrayList2 = arrayList6;
                                arrayList2.add(new Role(xmlPullParser2));
                            }
                            iCheckContentTypeConsistency = i2;
                            baseUrl = str2;
                        }
                        arrayList2 = arrayList6;
                        iCheckContentTypeConsistency = i2;
                        baseUrl = str2;
                    }
                    segmentBase2 = segmentTemplate;
                }
                iCheckContentTypeConsistency = i2;
                baseUrl = str2;
                arrayList2 = arrayList6;
            }
            if (ParserUtil.isEndTag(xmlPullParser2, "AdaptationSet")) {
                return buildAdaptationSet(i, iCheckContentTypeConsistency, arrayList, contentProtectionsBuilder.build(), arrayList2, strCheckLanguageConsistency);
            }
            xmlPullParser3 = xmlPullParser2;
            arrayList3 = arrayList;
            arrayList4 = arrayList2;
            str7 = strCheckLanguageConsistency;
            i3 = i;
            contentProtectionsBuilder2 = contentProtectionsBuilder;
            str6 = str3;
            str5 = str4;
        }
    }

    protected AdaptationSet buildAdaptationSet(int i, int i2, List<Representation> list, List<ContentProtection> list2, List<Role> list3, String str) {
        return new AdaptationSet(i, i2, list, list2, list3, str);
    }

    protected int parseContentType(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "contentType");
        if (TextUtils.isEmpty(attributeValue)) {
            return -1;
        }
        if ("audio".equals(attributeValue)) {
            return 1;
        }
        if ("video".equals(attributeValue)) {
            return 0;
        }
        return ("text".equals(attributeValue) || "image".equals(attributeValue)) ? 2 : -1;
    }

    protected int getContentType(Representation representation) {
        String str = representation.format.mimeType;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (MimeTypes.isVideo(str)) {
            return 0;
        }
        if (MimeTypes.isAudio(str)) {
            return 1;
        }
        if (MimeTypes.isText(str) || "application/ttml+xml".equals(str)) {
            return 2;
        }
        if ("application/mp4".equals(str)) {
            String str2 = representation.format.codecs;
            if ("stpp".equals(str2) || "wvtt".equals(str2)) {
                return 2;
            }
        }
        return -1;
    }

    protected ContentProtection parseContentProtection(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "schemeIdUri");
        UUID uuidFromString = null;
        DrmInitData.SchemeInitData schemeInitData = null;
        boolean z = false;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "cenc:pssh") && xmlPullParser.next() == 4) {
                z = true;
                schemeInitData = new DrmInitData.SchemeInitData("video/mp4", Base64.decode(xmlPullParser.getText(), 0));
                uuidFromString = PsshAtomUtil.parseUuid(schemeInitData.data);
            }
            if (ParserUtil.isStartTag(xmlPullParser, "mspr:pro") && xmlPullParser.next() == 4) {
                schemeInitData = new DrmInitData.SchemeInitData("video/mp4", Base64.decode(xmlPullParser.getText(), 0));
                uuidFromString = UUID.fromString(attributeValue.substring(9));
            }
            if (ParserUtil.isStartTag(xmlPullParser, "mas:MarlinContentId") && xmlPullParser.next() == 4 && !this.marlinContentIds.containsValue(xmlPullParser.getText().trim())) {
                HashMap<String, String> map = this.marlinContentIds;
                map.put(String.valueOf(map.size()), xmlPullParser.getText().trim());
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "ContentProtection"));
        byte[] bArr = this.actionToken;
        if (bArr != null) {
            schemeInitData = new DrmInitData.SchemeInitData("video/mp4", bArr);
            if (attributeValue.startsWith("urn:uuid:")) {
                return buildContentProtection(attributeValue, UUID.fromString(attributeValue.substring(9)), schemeInitData);
            }
            byte[] bArr2 = this.actionToken;
            if (bArr2[0] == 60 && bArr2[2] == 87 && bArr2[4] == 82) {
                return buildContentProtection(attributeValue, StreamingDrmSessionManager.PLAYREADY_UUID, schemeInitData);
            }
        }
        if (z && uuidFromString == null) {
            Log.w("MPDParser", "Skipped unsupported ContentProtection element");
            return null;
        }
        return buildContentProtection(attributeValue, uuidFromString, schemeInitData);
    }

    protected ContentProtection buildContentProtection(String str, UUID uuid, DrmInitData.SchemeInitData schemeInitData) {
        return new ContentProtection(str, uuid, schemeInitData);
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0110 A[LOOP:0: B:3:0x0047->B:37:0x0110, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:38:0x00b5 A[EDGE_INSN: B:38:0x00b5->B:27:0x00b5 BREAK  A[LOOP:0: B:3:0x0047->B:37:0x0110], SYNTHETIC] */
    protected Representation parseRepresentation(XmlPullParser xmlPullParser, String str, String str2, String str3, int i, int i2, float f, int i3, int i4, String str4, SegmentBase segmentBase, ContentProtectionsBuilder contentProtectionsBuilder) throws XmlPullParserException, IOException {
        ContentProtection contentProtection;
        String str5;
        int i5;
        String attributeValue = xmlPullParser.getAttributeValue(null, "id");
        int i6 = parseInt(xmlPullParser, "bandwidth");
        String string = parseString(xmlPullParser, "mimeType", str2);
        String string2 = parseString(xmlPullParser, "codecs", str3);
        int i7 = parseInt(xmlPullParser, "width", i);
        int i8 = parseInt(xmlPullParser, "height", i2);
        float frameRate = parseFrameRate(xmlPullParser, f);
        int i9 = parseInt(xmlPullParser, "audioSamplingRate", i4);
        int audioChannelConfiguration = i3;
        SegmentBase segmentTemplate = segmentBase;
        boolean z = false;
        String baseUrl = str;
        while (true) {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                if (!z) {
                    baseUrl = parseBaseUrl(xmlPullParser, baseUrl);
                    z = true;
                }
            } else if (ParserUtil.isStartTag(xmlPullParser, "AudioChannelConfiguration")) {
                audioChannelConfiguration = parseAudioChannelConfiguration(xmlPullParser);
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                segmentTemplate = parseSegmentBase(xmlPullParser, (SegmentBase.SingleSegmentBase) segmentTemplate);
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                segmentTemplate = parseSegmentList(xmlPullParser, (SegmentBase.SegmentList) segmentTemplate);
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                segmentTemplate = parseSegmentTemplate(xmlPullParser, (SegmentBase.SegmentTemplate) segmentTemplate);
            } else {
                if (ParserUtil.isStartTag(xmlPullParser, "ContentProtection") && (contentProtection = parseContentProtection(xmlPullParser)) != null) {
                    contentProtectionsBuilder.addAdaptationSetProtection(contentProtection);
                }
                str5 = baseUrl;
                i5 = audioChannelConfiguration;
                if (ParserUtil.isEndTag(xmlPullParser, "Representation")) {
                    break;
                }
                baseUrl = str5;
                audioChannelConfiguration = i5;
            }
            str5 = baseUrl;
            i5 = audioChannelConfiguration;
            if (ParserUtil.isEndTag(xmlPullParser, "Representation")) {
                break;
                break;
            }
            baseUrl = str5;
            audioChannelConfiguration = i5;
        }
        if (segmentTemplate == null && getContentType(string) != 2) {
            segmentTemplate = buildSingleSegmentBase(buildRangedUri(str5, 0L, 1024L), 1L, 0L, 1024L, 524288L);
        }
        SegmentBase segmentBase2 = segmentTemplate;
        return buildRepresentation(this.contentId, -1, buildFormat(attributeValue, string, i7, i8, frameRate, i5, i9, i6, str4, string2), segmentBase2 != null ? segmentBase2 : new SegmentBase.SingleSegmentBase(), str5);
    }

    protected Format buildFormat(String str, String str2, int i, int i2, float f, int i3, int i4, int i5, String str3, String str4) {
        return new Format(str, str2, i, i2, f, i3, i4, i5, str3, str4);
    }

    protected Representation buildRepresentation(String str, int i, Format format, SegmentBase segmentBase, String str2) {
        return Representation.newInstance(str, i, format, str2, segmentBase, null);
    }

    protected SegmentBase.SingleSegmentBase parseSegmentBase(XmlPullParser xmlPullParser, SegmentBase.SingleSegmentBase singleSegmentBase) throws XmlPullParserException, IOException {
        long j;
        long j2;
        long j3 = parseLong(xmlPullParser, "timescale", singleSegmentBase != null ? singleSegmentBase.timescale : 1L);
        long j4 = parseLong(xmlPullParser, "presentationTimeOffset", singleSegmentBase != null ? singleSegmentBase.presentationTimeOffset : 0L);
        long j5 = singleSegmentBase != null ? singleSegmentBase.indexStart : 0L;
        long j6 = singleSegmentBase != null ? singleSegmentBase.indexLength : -1L;
        String attributeValue = xmlPullParser.getAttributeValue(null, "indexRange");
        if (attributeValue != null) {
            String[] strArrSplit = attributeValue.split(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING);
            long j7 = Long.parseLong(strArrSplit[0]);
            j2 = (Long.parseLong(strArrSplit[1]) - j7) + 1;
            j = j7;
        } else {
            j = j5;
            j2 = j6;
        }
        RangedUri rangedUriBuildRangedUri = singleSegmentBase != null ? singleSegmentBase.initialization : null;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                rangedUriBuildRangedUri = parseInitialization(xmlPullParser);
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "SegmentBase"));
        if (rangedUriBuildRangedUri == null) {
            rangedUriBuildRangedUri = buildRangedUri(null, 0L, j);
        }
        return buildSingleSegmentBase(rangedUriBuildRangedUri, j3, j4, j, j2);
    }

    protected SegmentBase.SingleSegmentBase buildSingleSegmentBase(RangedUri rangedUri, long j, long j2, long j3, long j4) {
        return new SegmentBase.SingleSegmentBase(rangedUri, j, j2, j3, j4);
    }

    protected SegmentBase.SegmentList parseSegmentList(XmlPullParser xmlPullParser, SegmentBase.SegmentList segmentList) throws XmlPullParserException, IOException {
        long j = parseLong(xmlPullParser, "timescale", segmentList != null ? segmentList.timescale : 1L);
        long j2 = parseLong(xmlPullParser, "presentationTimeOffset", segmentList != null ? segmentList.presentationTimeOffset : 0L);
        long j3 = parseLong(xmlPullParser, "duration", segmentList != null ? segmentList.duration : -1L);
        int i = parseInt(xmlPullParser, "startNumber", segmentList != null ? segmentList.startNumber : 1);
        List<RangedUri> arrayList = null;
        RangedUri initialization = null;
        List<SegmentBase.SegmentTimelineElement> segmentTimeline = null;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                initialization = parseInitialization(xmlPullParser);
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentTimeline")) {
                segmentTimeline = parseSegmentTimeline(xmlPullParser, j);
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentURL")) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(parseSegmentUrl(xmlPullParser));
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "SegmentList"));
        if (segmentList != null) {
            if (initialization == null) {
                initialization = segmentList.initialization;
            }
            if (segmentTimeline == null) {
                segmentTimeline = segmentList.segmentTimeline;
            }
            if (arrayList == null) {
                arrayList = segmentList.mediaSegments;
            }
        }
        return buildSegmentList(initialization, j, j2, i, j3, segmentTimeline, arrayList);
    }

    protected SegmentBase.SegmentList buildSegmentList(RangedUri rangedUri, long j, long j2, int i, long j3, List<SegmentBase.SegmentTimelineElement> list, List<RangedUri> list2) {
        return new SegmentBase.SegmentList(rangedUri, j, j2, i, j3, list, list2);
    }

    protected SegmentBase.SegmentTemplate parseSegmentTemplate(XmlPullParser xmlPullParser, SegmentBase.SegmentTemplate segmentTemplate) throws XmlPullParserException, IOException {
        long j = parseLong(xmlPullParser, "timescale", segmentTemplate != null ? segmentTemplate.timescale : 1L);
        long j2 = parseLong(xmlPullParser, "presentationTimeOffset", segmentTemplate != null ? segmentTemplate.presentationTimeOffset : 0L);
        long j3 = parseLong(xmlPullParser, "duration", segmentTemplate != null ? segmentTemplate.duration : -1L);
        int i = parseInt(xmlPullParser, "startNumber", segmentTemplate != null ? segmentTemplate.startNumber : 1);
        RangedUri initialization = null;
        UrlTemplate urlTemplate = parseUrlTemplate(xmlPullParser, "media", segmentTemplate != null ? segmentTemplate.mediaTemplate : null);
        UrlTemplate urlTemplate2 = parseUrlTemplate(xmlPullParser, "initialization", segmentTemplate != null ? segmentTemplate.initializationTemplate : null);
        List<SegmentBase.SegmentTimelineElement> segmentTimeline = null;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                initialization = parseInitialization(xmlPullParser);
            } else if (ParserUtil.isStartTag(xmlPullParser, "SegmentTimeline")) {
                segmentTimeline = parseSegmentTimeline(xmlPullParser, j);
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "SegmentTemplate"));
        if (segmentTemplate != null) {
            if (initialization == null) {
                initialization = segmentTemplate.initialization;
            }
            if (segmentTimeline == null) {
                segmentTimeline = segmentTemplate.segmentTimeline;
            }
        }
        return buildSegmentTemplate(initialization, j, j2, i, j3, segmentTimeline, urlTemplate2, urlTemplate);
    }

    protected SegmentBase.SegmentTemplate buildSegmentTemplate(RangedUri rangedUri, long j, long j2, int i, long j3, List<SegmentBase.SegmentTimelineElement> list, UrlTemplate urlTemplate, UrlTemplate urlTemplate2) {
        return new SegmentBase.SegmentTemplate(rangedUri, j, j2, i, j3, list, urlTemplate, urlTemplate2);
    }

    protected SegmentBase.SegmentTimelineElement buildSegmentTimelineElement(long j, long j2) {
        return new SegmentBase.SegmentTimelineElement(j, j2);
    }

    protected UrlTemplate parseUrlTemplate(XmlPullParser xmlPullParser, String str, UrlTemplate urlTemplate) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue != null ? UrlTemplate.compile(attributeValue) : urlTemplate;
    }

    protected RangedUri parseInitialization(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "sourceURL", "range");
    }

    protected RangedUri parseSegmentUrl(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "media", "mediaRange");
    }

    protected RangedUri parseRangedUrl(XmlPullParser xmlPullParser, String str, String str2) {
        long j;
        long j2;
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, str2);
        if (attributeValue2 != null) {
            String[] strArrSplit = attributeValue2.split(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING);
            j = Long.parseLong(strArrSplit[0]);
            if (strArrSplit.length == 2) {
                j2 = (Long.parseLong(strArrSplit[1]) - j) + 1;
            }
            return buildRangedUri(attributeValue, j, j2);
        }
        j = 0;
        j2 = -1;
        return buildRangedUri(attributeValue, j, j2);
    }

    protected RangedUri buildRangedUri(String str, long j, long j2) {
        return new RangedUri(str, j, j2);
    }

    protected int parseAudioChannelConfiguration(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i = "urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(parseString(xmlPullParser, "schemeIdUri", null)) ? parseInt(xmlPullParser, "value") : -1;
        do {
            xmlPullParser.next();
        } while (!ParserUtil.isEndTag(xmlPullParser, "AudioChannelConfiguration"));
        return i;
    }

    private static String checkLanguageConsistency(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        Assertions.checkState(str.equals(str2));
        return str;
    }

    private static int checkContentTypeConsistency(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        Assertions.checkState(i == i2);
        return i;
    }

    protected static float parseFrameRate(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = FRAME_RATE_PATTERN.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int i = Integer.parseInt(matcher.group(1));
        String strGroup = matcher.group(2);
        return !TextUtils.isEmpty(strGroup) ? i / Integer.parseInt(strGroup) : i;
    }

    protected static long parseDuration(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Util.parseXsDuration(attributeValue);
    }

    protected static long parseDateTime(XmlPullParser xmlPullParser, String str, long j) throws ParseException {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Util.parseXsDateTime(attributeValue);
    }

    protected static String parseBaseUrl(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.next();
        return UriUtil.resolve(str, xmlPullParser.getText());
    }

    protected static int parseInt(XmlPullParser xmlPullParser, String str) {
        return parseInt(xmlPullParser, str, -1);
    }

    protected static int parseInt(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? i : Integer.parseInt(attributeValue);
    }

    protected static long parseLong(XmlPullParser xmlPullParser, String str) {
        return parseLong(xmlPullParser, str, -1L);
    }

    protected static long parseLong(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Long.parseLong(attributeValue);
    }

    protected static String parseString(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    protected static final class ContentProtectionsBuilder implements Comparator<ContentProtection> {
        private ArrayList<ContentProtection> adaptationSetProtections;
        private ArrayList<ContentProtection> currentRepresentationProtections;
        private ArrayList<ContentProtection> representationProtections;
        private boolean representationProtectionsSet;

        protected ContentProtectionsBuilder() {
        }

        public void addAdaptationSetProtection(ContentProtection contentProtection) {
            if (this.adaptationSetProtections == null) {
                this.adaptationSetProtections = new ArrayList<>();
            }
            maybeAddContentProtection(this.adaptationSetProtections, contentProtection);
        }

        public void endRepresentation() {
            if (!this.representationProtectionsSet) {
                ArrayList<ContentProtection> arrayList = this.currentRepresentationProtections;
                if (arrayList != null) {
                    Collections.sort(arrayList, this);
                }
                this.representationProtections = this.currentRepresentationProtections;
                this.representationProtectionsSet = true;
            } else {
                ArrayList<ContentProtection> arrayList2 = this.currentRepresentationProtections;
                if (arrayList2 == null) {
                    Assertions.checkState(this.representationProtections == null);
                } else {
                    Collections.sort(arrayList2, this);
                    Assertions.checkState(this.currentRepresentationProtections.equals(this.representationProtections));
                }
            }
            this.currentRepresentationProtections = null;
        }

        public ArrayList<ContentProtection> build() {
            ArrayList<ContentProtection> arrayList = this.adaptationSetProtections;
            if (arrayList == null) {
                return this.representationProtections;
            }
            if (this.representationProtections == null) {
                return arrayList;
            }
            for (int i = 0; i < this.representationProtections.size(); i++) {
                maybeAddContentProtection(this.adaptationSetProtections, this.representationProtections.get(i));
            }
            return this.adaptationSetProtections;
        }

        private void maybeAddContentProtection(List<ContentProtection> list, ContentProtection contentProtection) {
            if (list.contains(contentProtection)) {
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                Assertions.checkState(!list.get(i).schemeUriId.equals(contentProtection.schemeUriId));
            }
            list.add(contentProtection);
        }

        @Override // java.util.Comparator
        public int compare(ContentProtection contentProtection, ContentProtection contentProtection2) {
            return contentProtection.schemeUriId.compareTo(contentProtection2.schemeUriId);
        }
    }

    protected List<SegmentBase.SegmentTimelineElement> parseSegmentTimeline(XmlPullParser xmlPullParser, long j) throws XmlPullParserException, IOException {
        int iRound;
        ArrayList arrayList = new ArrayList();
        long j2 = 0;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, MtkTvRatingConvert2Goo.SUB_RATING_STR_S)) {
                j2 = parseLong(xmlPullParser, "t", j2);
                long j3 = parseLong(xmlPullParser, "d");
                if (parseInt(xmlPullParser, "r", 0) < 0) {
                    iRound = Math.round((this.singlePeriodDurationMs / Math.round(j3 / j)) / 1000.0f);
                } else {
                    iRound = parseInt(xmlPullParser, "r", 0) + 1;
                }
                for (int i = 0; i < iRound; i++) {
                    arrayList.add(buildSegmentTimelineElement(j2, j3));
                    j2 += j3;
                }
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "SegmentTimeline"));
        return arrayList;
    }

    public MediaPresentationDescriptionParser(String str, byte[] bArr) {
        this.contentId = str;
        this.actionToken = bArr;
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    protected int getContentType(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (MimeTypes.isVideo(str)) {
            return 0;
        }
        if (MimeTypes.isAudio(str)) {
            return 1;
        }
        if (MimeTypes.isText(str) || "application/ttml+xml".equals(str)) {
            return 2;
        }
        "application/mp4".equals(str);
        return -1;
    }
}
