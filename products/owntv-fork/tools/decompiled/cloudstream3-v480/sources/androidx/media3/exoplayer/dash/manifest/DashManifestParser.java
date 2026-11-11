package androidx.media3.exoplayer.dash.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import android.util.Xml;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Label;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.common.util.XmlPullParserUtil;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.extractor.metadata.emsg.EventMessage;
import androidx.media3.extractor.mp4.PsshAtomUtil;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.ktor.http.LinkHeader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.services.youtube.dashmanifestcreators.YoutubeDashManifestCreatorsUtils;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes4.dex */
public class DashManifestParser extends DefaultHandler implements ParsingLoadable.Parser<DashManifest> {
    private static final String TAG = "MpdParser";
    private final XmlPullParserFactory xmlParserFactory;
    private static final Pattern FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final Pattern CEA_608_ACCESSIBILITY_PATTERN = Pattern.compile("CC([1-4])=.*");
    private static final Pattern CEA_708_ACCESSIBILITY_PATTERN = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private static final int[] DOLBY_AC4_CHANNEL_CONFIGURATION_MAPPING = {2, 1, 2, 2, 2, 2, 1, 2, 2, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2};
    private static final int[] MPEG_CHANNEL_CONFIGURATION_MAPPING = {-1, 1, 2, 3, 4, 5, 6, 8, 2, 3, 4, 7, 8, 24, 8, 12, 10, 12, 14, 12, 14};

    private static long getFinalAvailabilityTimeOffset(long j, long j2) {
        if (j2 != C.TIME_UNSET) {
            j = j2;
        }
        return j == Long.MAX_VALUE ? C.TIME_UNSET : j;
    }

    public DashManifestParser() {
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
    public DashManifest parse(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser xmlPullParserNewPullParser = this.xmlParserFactory.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStream, null);
            if (xmlPullParserNewPullParser.next() != 2 || !YoutubeDashManifestCreatorsUtils.MPD.equals(xmlPullParserNewPullParser.getName())) {
                throw ParserException.createForMalformedManifest("inputStream does not contain a valid media presentation description", null);
            }
            return parseMediaPresentationDescription(xmlPullParserNewPullParser, uri);
        } catch (XmlPullParserException e) {
            if (e.getDetail() instanceof IOException) {
                throw ((IOException) e.getDetail());
            }
            throw ParserException.createForMalformedManifest(null, e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:66:0x019f  */
    /* JADX WARN: Code duplicated, block: B:68:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:69:0x01a6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:71:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:73:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:76:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:78:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:80:0x01df A[LOOP:0: B:24:0x00a8->B:80:0x01df, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:82:0x019b A[SYNTHETIC] */
    protected DashManifest parseMediaPresentationDescription(XmlPullParser xmlPullParser, Uri uri) throws XmlPullParserException, IOException {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        long j;
        ArrayList arrayList5;
        long j2;
        Throwable th;
        long j3;
        DashManifestParser dashManifestParser = this;
        boolean zIsDvbProfileDeclared = dashManifestParser.isDvbProfileDeclared(dashManifestParser.parseProfiles(xmlPullParser, "profiles", new String[0]));
        long dateTime = parseDateTime(xmlPullParser, "availabilityStartTime", C.TIME_UNSET);
        long duration = parseDuration(xmlPullParser, "mediaPresentationDuration", C.TIME_UNSET);
        long duration2 = parseDuration(xmlPullParser, "minBufferTime", C.TIME_UNSET);
        boolean zEquals = "dynamic".equals(xmlPullParser.getAttributeValue(null, "type"));
        long duration3 = zEquals ? parseDuration(xmlPullParser, "minimumUpdatePeriod", C.TIME_UNSET) : -9223372036854775807L;
        long duration4 = zEquals ? parseDuration(xmlPullParser, "timeShiftBufferDepth", C.TIME_UNSET) : -9223372036854775807L;
        long duration5 = zEquals ? parseDuration(xmlPullParser, "suggestedPresentationDelay", C.TIME_UNSET) : -9223372036854775807L;
        long dateTime2 = parseDateTime(xmlPullParser, "publishTime", C.TIME_UNSET);
        long j4 = zEquals ? 0L : -9223372036854775807L;
        boolean z = false;
        ArrayList arrayListNewArrayList = Lists.newArrayList(new BaseUrl(uri.toString(), uri.toString(), zIsDvbProfileDeclared ? 1 : Integer.MIN_VALUE, 1));
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        long j5 = zEquals ? -9223372036854775807L : 0L;
        ArrayList arrayList8 = arrayListNewArrayList;
        long availabilityTimeOffsetUs = j4;
        boolean z2 = false;
        ProgramInformation programInformation = null;
        UtcTimingElement utcTiming = null;
        Uri uriResolveToUri = null;
        ServiceDescriptionElement serviceDescription = null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, YoutubeDashManifestCreatorsUtils.BASE_URL)) {
                if (!z) {
                    availabilityTimeOffsetUs = dashManifestParser.parseAvailabilityTimeOffsetUs(xmlPullParser, availabilityTimeOffsetUs);
                    z = true;
                }
                arrayList = arrayList8;
                arrayList2 = arrayList6;
                arrayList7.addAll(dashManifestParser.parseBaseUrl(xmlPullParser, arrayList, zIsDvbProfileDeclared));
            } else {
                arrayList = arrayList8;
                arrayList2 = arrayList6;
                if (XmlPullParserUtil.isStartTag(xmlPullParser, "ProgramInformation")) {
                    programInformation = parseProgramInformation(xmlPullParser);
                } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "UTCTiming")) {
                    utcTiming = parseUtcTiming(xmlPullParser);
                } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "Location")) {
                    uriResolveToUri = UriUtil.resolveToUri(uri.toString(), xmlPullParser.nextText());
                } else {
                    if (XmlPullParserUtil.isStartTag(xmlPullParser, "ServiceDescription")) {
                        serviceDescription = parseServiceDescription(xmlPullParser);
                    } else if (XmlPullParserUtil.isStartTag(xmlPullParser, YoutubeDashManifestCreatorsUtils.PERIOD) && !z2) {
                        if (arrayList7.isEmpty()) {
                            arrayList3 = arrayList7;
                            arrayList5 = arrayList;
                            arrayList4 = arrayList2;
                            arrayList8 = arrayList5;
                        } else {
                            arrayList5 = arrayList7;
                            arrayList3 = arrayList5;
                            arrayList8 = arrayList;
                            arrayList4 = arrayList2;
                        }
                        Pair<Period, Long> period = parsePeriod(xmlPullParser, arrayList5, j5, availabilityTimeOffsetUs, dateTime, duration4, zIsDvbProfileDeclared);
                        Period period2 = (Period) period.first;
                        if (period2.startMs != C.TIME_UNSET) {
                            long jLongValue = ((Long) period.second).longValue();
                            j2 = jLongValue == C.TIME_UNSET ? -9223372036854775807L : period2.startMs + jLongValue;
                            arrayList4.add(period2);
                        } else {
                            if (!zEquals) {
                                throw ParserException.createForMalformedManifest("Unable to determine start of period " + arrayList4.size(), null);
                            }
                            j2 = j5;
                            z2 = true;
                        }
                        j = availabilityTimeOffsetUs;
                        j5 = j2;
                    } else {
                        arrayList3 = arrayList7;
                        arrayList8 = arrayList;
                        long j6 = j5;
                        long j7 = availabilityTimeOffsetUs;
                        arrayList4 = arrayList2;
                        maybeSkipTag(xmlPullParser);
                        j5 = j6;
                        j = j7;
                    }
                    if (XmlPullParserUtil.isEndTag(xmlPullParser, YoutubeDashManifestCreatorsUtils.MPD)) {
                        if (duration != C.TIME_UNSET) {
                            th = null;
                            j3 = duration;
                        } else if (j5 != C.TIME_UNSET) {
                            j3 = j5;
                            th = null;
                        } else {
                            if (!zEquals) {
                                throw ParserException.createForMalformedManifest("Unable to determine duration of static manifest.", null);
                            }
                            th = null;
                            j3 = duration;
                        }
                        if (!arrayList4.isEmpty()) {
                            throw ParserException.createForMalformedManifest("No periods found.", th);
                        }
                        return buildMediaPresentationDescription(dateTime, j3, duration2, zEquals, duration3, duration4, duration5, dateTime2, programInformation, utcTiming, serviceDescription, uriResolveToUri, arrayList4);
                    }
                    long j8 = duration4;
                    long j9 = duration2;
                    ArrayList arrayList9 = arrayList4;
                    dashManifestParser = this;
                    zEquals = zEquals;
                    duration3 = duration3;
                    availabilityTimeOffsetUs = j;
                    arrayList6 = arrayList9;
                    arrayList7 = arrayList3;
                    duration2 = j9;
                    duration4 = j8;
                    duration = duration;
                }
            }
            arrayList3 = arrayList7;
            j = availabilityTimeOffsetUs;
            arrayList4 = arrayList2;
            arrayList8 = arrayList;
            if (XmlPullParserUtil.isEndTag(xmlPullParser, YoutubeDashManifestCreatorsUtils.MPD)) {
                if (duration != C.TIME_UNSET) {
                    th = null;
                    j3 = duration;
                } else if (j5 != C.TIME_UNSET) {
                    j3 = j5;
                    th = null;
                } else {
                    if (!zEquals) {
                        throw ParserException.createForMalformedManifest("Unable to determine duration of static manifest.", null);
                    }
                    th = null;
                    j3 = duration;
                }
                if (!arrayList4.isEmpty()) {
                    throw ParserException.createForMalformedManifest("No periods found.", th);
                }
                return buildMediaPresentationDescription(dateTime, j3, duration2, zEquals, duration3, duration4, duration5, dateTime2, programInformation, utcTiming, serviceDescription, uriResolveToUri, arrayList4);
            }
            long j10 = duration4;
            long j11 = duration2;
            ArrayList arrayList10 = arrayList4;
            dashManifestParser = this;
            zEquals = zEquals;
            duration3 = duration3;
            availabilityTimeOffsetUs = j;
            arrayList6 = arrayList10;
            arrayList7 = arrayList3;
            duration2 = j11;
            duration4 = j10;
            duration = duration;
        }
    }

    protected DashManifest buildMediaPresentationDescription(long j, long j2, long j3, boolean z, long j4, long j5, long j6, long j7, ProgramInformation programInformation, UtcTimingElement utcTimingElement, ServiceDescriptionElement serviceDescriptionElement, Uri uri, List<Period> list) {
        return new DashManifest(j, j2, j3, z, j4, j5, j6, j7, programInformation, utcTimingElement, serviceDescriptionElement, uri, list);
    }

    protected UtcTimingElement parseUtcTiming(XmlPullParser xmlPullParser) {
        return buildUtcTimingElement(xmlPullParser.getAttributeValue(null, "schemeIdUri"), xmlPullParser.getAttributeValue(null, ES6Iterator.VALUE_PROPERTY));
    }

    protected UtcTimingElement buildUtcTimingElement(String str, String str2) {
        return new UtcTimingElement(str, str2);
    }

    protected ServiceDescriptionElement parseServiceDescription(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        long j = -9223372036854775807L;
        long j2 = -9223372036854775807L;
        long j3 = -9223372036854775807L;
        float f = -3.4028235E38f;
        float f2 = -3.4028235E38f;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Latency")) {
                j = parseLong(xmlPullParser, TypedValues.AttributesType.S_TARGET, C.TIME_UNSET);
                j2 = parseLong(xmlPullParser, "min", C.TIME_UNSET);
                j3 = parseLong(xmlPullParser, "max", C.TIME_UNSET);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "PlaybackRate")) {
                f = parseFloat(xmlPullParser, "min", -3.4028235E38f);
                f2 = parseFloat(xmlPullParser, "max", -3.4028235E38f);
            }
            long j4 = j;
            long j5 = j2;
            long j6 = j3;
            float f3 = f;
            float f4 = f2;
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "ServiceDescription")) {
                return new ServiceDescriptionElement(j4, j5, j6, f3, f4);
            }
            j = j4;
            j2 = j5;
            j3 = j6;
            f = f3;
            f2 = f4;
        }
    }

    protected Pair<Period, Long> parsePeriod(XmlPullParser xmlPullParser, List<BaseUrl> list, long j, long j2, long j3, long j4, boolean z) throws XmlPullParserException, IOException {
        long availabilityTimeOffsetUs;
        ArrayList arrayList;
        ArrayList arrayList2;
        long j5;
        Object obj;
        long j6;
        SegmentBase segmentTemplate;
        long j7;
        long j8;
        SegmentBase segmentBase;
        ArrayList arrayList3;
        boolean z2;
        long j9;
        ArrayList arrayList4;
        ArrayList arrayList5;
        ArrayList arrayList6;
        List list2;
        ArrayList arrayList7;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        String attributeValue = xmlPullParser2.getAttributeValue(null, "id");
        long duration = parseDuration(xmlPullParser2, "start", j);
        long j10 = j3 != C.TIME_UNSET ? j3 + duration : -9223372036854775807L;
        long duration2 = parseDuration(xmlPullParser2, TypedValues.TransitionType.S_DURATION, C.TIME_UNSET);
        ArrayList arrayList8 = new ArrayList();
        long j11 = -9223372036854775807L;
        ArrayList arrayList9 = new ArrayList();
        boolean z3 = false;
        Descriptor descriptor = null;
        long availabilityTimeOffsetUs2 = j2;
        ArrayList arrayList10 = arrayList8;
        SegmentBase segmentBase2 = null;
        long j12 = -9223372036854775807L;
        ArrayList arrayList11 = new ArrayList();
        while (true) {
            xmlPullParser2.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.BASE_URL)) {
                if (!z3) {
                    availabilityTimeOffsetUs2 = parseAvailabilityTimeOffsetUs(xmlPullParser2, availabilityTimeOffsetUs2);
                    z3 = true;
                }
                arrayList11.addAll(parseBaseUrl(xmlPullParser2, list, z));
                arrayList2 = arrayList10;
                j7 = availabilityTimeOffsetUs2;
                arrayList = arrayList9;
                z2 = z3;
                j5 = j11;
                obj = null;
                duration2 = duration2;
                arrayList7 = arrayList11;
                descriptor = descriptor;
                xmlPullParser2 = xmlPullParser2;
                segmentBase = segmentBase2;
                j9 = j12;
                j10 = j10;
            } else {
                ArrayList arrayList12 = arrayList10;
                if (XmlPullParserUtil.isStartTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.ADAPTATION_SET)) {
                    if (arrayList11.isEmpty()) {
                        arrayList6 = arrayList11;
                        list2 = list;
                    } else {
                        ArrayList arrayList13 = arrayList11;
                        arrayList6 = arrayList13;
                        list2 = arrayList13;
                    }
                    long j13 = availabilityTimeOffsetUs2;
                    long j14 = j10;
                    long j15 = duration2;
                    AdaptationSet adaptationSet = parseAdaptationSet(xmlPullParser2, list2, segmentBase2, j15, j13, j12, j14, j4, z);
                    j10 = j14;
                    availabilityTimeOffsetUs = j12;
                    arrayList12.add(adaptationSet);
                    duration2 = j15;
                    arrayList = arrayList9;
                    obj = null;
                    j6 = j13;
                    arrayList2 = arrayList12;
                    j5 = C.TIME_UNSET;
                    arrayList5 = arrayList6;
                } else {
                    ArrayList arrayList14 = arrayList11;
                    ArrayList arrayList15 = arrayList9;
                    long j16 = availabilityTimeOffsetUs2;
                    availabilityTimeOffsetUs = j12;
                    if (XmlPullParserUtil.isStartTag(xmlPullParser2, "EventStream")) {
                        arrayList15.add(parseEventStream(xmlPullParser));
                        duration2 = duration2;
                        arrayList = arrayList15;
                        arrayList2 = arrayList12;
                        j5 = C.TIME_UNSET;
                        obj = null;
                        j6 = j16;
                        arrayList5 = arrayList14;
                    } else {
                        if (XmlPullParserUtil.isStartTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.SEGMENT_BASE)) {
                            segmentTemplate = parseSegmentBase(xmlPullParser2, null);
                            j7 = j16;
                            obj = null;
                            arrayList = arrayList15;
                            arrayList2 = arrayList12;
                            j5 = C.TIME_UNSET;
                        } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentList")) {
                            long j17 = j10;
                            long j18 = duration2;
                            long availabilityTimeOffsetUs3 = parseAvailabilityTimeOffsetUs(xmlPullParser2, C.TIME_UNSET);
                            arrayList = arrayList15;
                            arrayList2 = arrayList12;
                            j5 = -9223372036854775807L;
                            SegmentBase.SegmentList segmentList = parseSegmentList(xmlPullParser2, null, j17, j18, j16, availabilityTimeOffsetUs3, j4);
                            j7 = j16;
                            obj = null;
                            j10 = j17;
                            duration2 = j18;
                            descriptor = descriptor;
                            xmlPullParser2 = xmlPullParser2;
                            j8 = availabilityTimeOffsetUs3;
                            segmentBase = segmentList;
                            arrayList3 = arrayList14;
                        } else {
                            segmentBase2 = segmentBase2;
                            arrayList = arrayList15;
                            arrayList2 = arrayList12;
                            j5 = C.TIME_UNSET;
                            if (XmlPullParserUtil.isStartTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.SEGMENT_TEMPLATE)) {
                                availabilityTimeOffsetUs = parseAvailabilityTimeOffsetUs(xmlPullParser2, C.TIME_UNSET);
                                obj = null;
                                segmentTemplate = parseSegmentTemplate(xmlPullParser2, null, ImmutableList.of(), j10, duration2, j16, availabilityTimeOffsetUs, j4);
                                j7 = j16;
                            } else {
                                obj = null;
                                duration2 = duration2;
                                xmlPullParser2 = xmlPullParser2;
                                j6 = j16;
                                j10 = j10;
                                if (XmlPullParserUtil.isStartTag(xmlPullParser2, "AssetIdentifier")) {
                                    descriptor = parseDescriptor(xmlPullParser2, "AssetIdentifier");
                                    arrayList4 = arrayList14;
                                } else {
                                    maybeSkipTag(xmlPullParser2);
                                    arrayList4 = arrayList14;
                                }
                                j7 = j6;
                                descriptor = descriptor;
                                j8 = availabilityTimeOffsetUs;
                                segmentBase = segmentBase2;
                                arrayList3 = arrayList4;
                            }
                        }
                        j8 = availabilityTimeOffsetUs;
                        segmentBase = segmentTemplate;
                        arrayList3 = arrayList14;
                    }
                    z2 = z3;
                    j9 = j8;
                    arrayList7 = arrayList3;
                }
                j10 = j10;
                arrayList4 = arrayList5;
                j7 = j6;
                descriptor = descriptor;
                j8 = availabilityTimeOffsetUs;
                segmentBase = segmentBase2;
                arrayList3 = arrayList4;
                z2 = z3;
                j9 = j8;
                arrayList7 = arrayList3;
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.PERIOD)) {
                return Pair.create(buildPeriod(attributeValue, duration, arrayList2, arrayList, descriptor), Long.valueOf(duration2));
            }
            xmlPullParser2 = xmlPullParser2;
            j10 = j10;
            segmentBase2 = segmentBase;
            j12 = j9;
            arrayList11 = arrayList7;
            arrayList10 = arrayList2;
            descriptor = descriptor;
            z3 = z2;
            duration2 = duration2;
            j11 = j5;
            availabilityTimeOffsetUs2 = j7;
            arrayList9 = arrayList;
        }
    }

    protected Period buildPeriod(String str, long j, List<AdaptationSet> list, List<EventStream> list2, Descriptor descriptor) {
        return new Period(str, j, list, list2, descriptor);
    }

    /* JADX WARN: Code duplicated, block: B:75:0x03d0 A[LOOP:0: B:3:0x00a3->B:75:0x03d0, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:76:0x0380 A[EDGE_INSN: B:76:0x0380->B:69:0x0380 BREAK  A[LOOP:0: B:3:0x00a3->B:75:0x03d0], SYNTHETIC] */
    protected AdaptationSet parseAdaptationSet(XmlPullParser xmlPullParser, List<BaseUrl> list, SegmentBase segmentBase, long j, long j2, long j3, long j4, long j5, boolean z) throws XmlPullParserException, IOException {
        String str;
        int i;
        int iCheckContentTypeConsistency;
        long j6;
        ArrayList arrayList;
        String str2;
        long j7;
        int i2;
        List<Label> list2;
        ArrayList arrayList2;
        List<BaseUrl> list3;
        ArrayList arrayList3;
        ArrayList arrayList4;
        ArrayList arrayList5;
        ArrayList arrayList6;
        ArrayList arrayList7;
        ArrayList arrayList8;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        long j8 = parseLong(xmlPullParser2, "id", -1L);
        int contentType = parseContentType(xmlPullParser);
        String attributeValue = xmlPullParser2.getAttributeValue(null, "mimeType");
        String attributeValue2 = xmlPullParser2.getAttributeValue(null, "codecs");
        String attributeValue3 = xmlPullParser2.getAttributeValue(null, "scte214:supplementalCodecs");
        String attributeValue4 = xmlPullParser2.getAttributeValue(null, "scte214:supplementalProfiles");
        int i3 = parseInt(xmlPullParser2, "width", -1);
        int i4 = parseInt(xmlPullParser2, "height", -1);
        float frameRate = parseFrameRate(xmlPullParser2, -1.0f);
        int i5 = parseInt(xmlPullParser2, "audioSamplingRate", -1);
        String str3 = "lang";
        String attributeValue5 = xmlPullParser2.getAttributeValue(null, "lang");
        String attributeValue6 = xmlPullParser2.getAttributeValue(null, "label");
        List<Label> arrayList9 = new ArrayList<>();
        ArrayList<DrmInitData.SchemeData> arrayList10 = new ArrayList<>();
        ArrayList<Descriptor> arrayList11 = new ArrayList<>();
        List<Label> list4 = arrayList9;
        ArrayList arrayList12 = new ArrayList();
        ArrayList arrayList13 = new ArrayList();
        ArrayList arrayList14 = new ArrayList();
        ArrayList<Descriptor> arrayList15 = arrayList11;
        ArrayList arrayList16 = new ArrayList();
        String str4 = attributeValue;
        ArrayList arrayList17 = new ArrayList();
        long j9 = j3;
        String str5 = attributeValue4;
        int i6 = i3;
        int i7 = i4;
        float f = frameRate;
        int iCheckContentTypeConsistency2 = contentType;
        String str6 = attributeValue5;
        boolean z2 = false;
        int audioChannelConfiguration = -1;
        String str7 = null;
        SegmentBase segmentTemplate = segmentBase;
        String str8 = attributeValue3;
        long availabilityTimeOffsetUs = j2;
        ArrayList arrayList18 = new ArrayList();
        while (true) {
            xmlPullParser2.next();
            audioChannelConfiguration = audioChannelConfiguration;
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.BASE_URL)) {
                if (!z2) {
                    availabilityTimeOffsetUs = parseAvailabilityTimeOffsetUs(xmlPullParser2, availabilityTimeOffsetUs);
                    z2 = true;
                }
                arrayList18.addAll(parseBaseUrl(xmlPullParser2, list, z));
                List<Label> list5 = list4;
                arrayList14 = arrayList14;
                list2 = list5;
                audioChannelConfiguration = audioChannelConfiguration;
                availabilityTimeOffsetUs = availabilityTimeOffsetUs;
                arrayList7 = arrayList18;
                arrayList10 = arrayList10;
                str3 = str3;
                arrayList12 = arrayList12;
                segmentTemplate = segmentTemplate;
                arrayList17 = arrayList17;
                arrayList16 = arrayList16;
                i5 = i5;
                arrayList15 = arrayList15;
            } else {
                availabilityTimeOffsetUs = availabilityTimeOffsetUs;
                if (XmlPullParserUtil.isStartTag(xmlPullParser2, "ContentProtection")) {
                    Pair<String, DrmInitData.SchemeData> contentProtection = parseContentProtection(xmlPullParser);
                    arrayList6 = arrayList18;
                    if (contentProtection.first != null) {
                        str7 = (String) contentProtection.first;
                    }
                    if (contentProtection.second != null) {
                        arrayList10.add((DrmInitData.SchemeData) contentProtection.second);
                    }
                    List<Label> list6 = list4;
                    arrayList14 = arrayList14;
                    list2 = list6;
                    audioChannelConfiguration = audioChannelConfiguration;
                } else {
                    ArrayList arrayList19 = arrayList18;
                    if (XmlPullParserUtil.isStartTag(xmlPullParser2, "ContentComponent")) {
                        String strCheckLanguageConsistency = checkLanguageConsistency(str6, xmlPullParser2.getAttributeValue(null, str3));
                        iCheckContentTypeConsistency2 = checkContentTypeConsistency(iCheckContentTypeConsistency2, parseContentType(xmlPullParser));
                        list2 = list4;
                        audioChannelConfiguration = audioChannelConfiguration;
                        str6 = strCheckLanguageConsistency;
                    } else {
                        if (XmlPullParserUtil.isStartTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.ROLE)) {
                            arrayList13.add(parseDescriptor(xmlPullParser2, YoutubeDashManifestCreatorsUtils.ROLE));
                        } else {
                            if (XmlPullParserUtil.isStartTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.AUDIO_CHANNEL_CONFIGURATION)) {
                                list2 = list4;
                                audioChannelConfiguration = parseAudioChannelConfiguration(xmlPullParser2, attributeValue2);
                            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Accessibility")) {
                                arrayList12.add(parseDescriptor(xmlPullParser2, "Accessibility"));
                            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "EssentialProperty")) {
                                arrayList14.add(parseDescriptor(xmlPullParser2, "EssentialProperty"));
                            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SupplementalProperty")) {
                                arrayList16.add(parseDescriptor(xmlPullParser2, "SupplementalProperty"));
                            } else {
                                if (XmlPullParserUtil.isStartTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.REPRESENTATION)) {
                                    if (arrayList19.isEmpty()) {
                                        list3 = list;
                                        arrayList3 = arrayList19;
                                    } else {
                                        ArrayList arrayList20 = arrayList19;
                                        arrayList3 = arrayList20;
                                        list3 = arrayList20;
                                    }
                                    RepresentationInfo representation = parseRepresentation(xmlPullParser2, list3, str4, attributeValue2, str8, str5, i6, i7, f, audioChannelConfiguration, i5, str6, arrayList13, arrayList12, arrayList14, arrayList16, segmentTemplate, j4, j, availabilityTimeOffsetUs, j9, j5, z);
                                    str4 = str4;
                                    str8 = str8;
                                    str5 = str5;
                                    i7 = i7;
                                    f = f;
                                    segmentTemplate = segmentTemplate;
                                    arrayList13 = arrayList13;
                                    arrayList14 = arrayList14;
                                    str2 = attributeValue2;
                                    arrayList12 = arrayList12;
                                    i5 = i5;
                                    i6 = i6;
                                    iCheckContentTypeConsistency = checkContentTypeConsistency(iCheckContentTypeConsistency2, MimeTypes.getTrackType(representation.format.sampleMimeType));
                                    ArrayList arrayList21 = arrayList17;
                                    arrayList21.add(representation);
                                    xmlPullParser2 = xmlPullParser2;
                                    availabilityTimeOffsetUs = availabilityTimeOffsetUs;
                                    arrayList17 = arrayList21;
                                    arrayList16 = arrayList16;
                                    str6 = str6;
                                    arrayList2 = arrayList3;
                                } else {
                                    str3 = str3;
                                    arrayList12 = arrayList12;
                                    list4 = list4;
                                    arrayList15 = arrayList15;
                                    ArrayList arrayList22 = arrayList16;
                                    arrayList14 = arrayList14;
                                    str = str6;
                                    i5 = i5;
                                    ArrayList arrayList23 = arrayList17;
                                    i = audioChannelConfiguration;
                                    XmlPullParser xmlPullParser3 = xmlPullParser2;
                                    iCheckContentTypeConsistency = iCheckContentTypeConsistency2;
                                    j6 = availabilityTimeOffsetUs;
                                    arrayList10 = arrayList10;
                                    segmentTemplate = segmentTemplate;
                                    arrayList = arrayList19;
                                    arrayList13 = arrayList13;
                                    str2 = attributeValue2;
                                    j7 = j9;
                                    if (XmlPullParserUtil.isStartTag(xmlPullParser3, YoutubeDashManifestCreatorsUtils.SEGMENT_BASE)) {
                                        xmlPullParser2 = xmlPullParser3;
                                        segmentTemplate = parseSegmentBase(xmlPullParser3, (SegmentBase.SingleSegmentBase) segmentTemplate);
                                        j9 = j7;
                                        availabilityTimeOffsetUs = j6;
                                        arrayList17 = arrayList23;
                                        arrayList16 = arrayList22;
                                        str6 = str;
                                        audioChannelConfiguration = i;
                                        arrayList2 = arrayList;
                                    } else if (XmlPullParserUtil.isStartTag(xmlPullParser3, "SegmentList")) {
                                        long availabilityTimeOffsetUs2 = parseAvailabilityTimeOffsetUs(xmlPullParser3, j7);
                                        arrayList17 = arrayList23;
                                        xmlPullParser2 = xmlPullParser;
                                        segmentTemplate = parseSegmentList(xmlPullParser2, (SegmentBase.SegmentList) segmentTemplate, j4, j, j6, availabilityTimeOffsetUs2, j5);
                                        availabilityTimeOffsetUs = j6;
                                        arrayList16 = arrayList22;
                                        audioChannelConfiguration = i;
                                        iCheckContentTypeConsistency2 = iCheckContentTypeConsistency;
                                        arrayList15 = arrayList15;
                                        j9 = availabilityTimeOffsetUs2;
                                        str6 = str;
                                        list2 = list4;
                                        arrayList8 = arrayList;
                                    } else {
                                        i2 = iCheckContentTypeConsistency;
                                        xmlPullParser2 = xmlPullParser3;
                                        arrayList17 = arrayList23;
                                        if (XmlPullParserUtil.isStartTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.SEGMENT_TEMPLATE)) {
                                            long availabilityTimeOffsetUs3 = parseAvailabilityTimeOffsetUs(xmlPullParser2, j7);
                                            arrayList16 = arrayList22;
                                            segmentTemplate = parseSegmentTemplate(xmlPullParser2, (SegmentBase.SegmentTemplate) segmentTemplate, arrayList16, j4, j, j6, availabilityTimeOffsetUs3, j5);
                                            availabilityTimeOffsetUs = j6;
                                            iCheckContentTypeConsistency2 = i2;
                                            list2 = list4;
                                            arrayList15 = arrayList15;
                                            j9 = availabilityTimeOffsetUs3;
                                            arrayList4 = arrayList;
                                        } else {
                                            arrayList16 = arrayList22;
                                            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "InbandEventStream")) {
                                                arrayList15 = arrayList15;
                                                arrayList15.add(parseDescriptor(xmlPullParser2, "InbandEventStream"));
                                                list2 = list4;
                                                arrayList5 = arrayList;
                                            } else {
                                                arrayList15 = arrayList15;
                                                if (XmlPullParserUtil.isStartTag(xmlPullParser2, TextFieldImplKt.LabelId)) {
                                                    list2 = list4;
                                                    list2.add(parseLabel(xmlPullParser));
                                                    arrayList5 = arrayList;
                                                } else {
                                                    list2 = list4;
                                                    if (XmlPullParserUtil.isStartTag(xmlPullParser2)) {
                                                        arrayList5 = arrayList;
                                                        parseAdaptationSetChild(xmlPullParser);
                                                        arrayList5 = arrayList;
                                                    }
                                                }
                                            }
                                            arrayList5 = arrayList;
                                            iCheckContentTypeConsistency2 = i2;
                                            j9 = j7;
                                            availabilityTimeOffsetUs = j6;
                                            arrayList4 = arrayList5;
                                        }
                                        str6 = str;
                                        audioChannelConfiguration = i;
                                        arrayList8 = arrayList4;
                                    }
                                }
                                list2 = list4;
                                arrayList15 = arrayList15;
                                iCheckContentTypeConsistency2 = iCheckContentTypeConsistency;
                                arrayList8 = arrayList2;
                            }
                            if (XmlPullParserUtil.isEndTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.ADAPTATION_SET)) {
                                break;
                            }
                            ArrayList arrayList24 = arrayList14;
                            list4 = list2;
                            arrayList14 = arrayList24;
                            arrayList17 = arrayList17;
                            arrayList15 = arrayList15;
                            arrayList16 = arrayList16;
                            attributeValue2 = str2;
                            i5 = i5;
                            arrayList12 = arrayList12;
                            arrayList13 = arrayList13;
                            arrayList18 = arrayList8;
                            str3 = str3;
                            segmentTemplate = segmentTemplate;
                            arrayList10 = arrayList10;
                        }
                        arrayList10 = arrayList10;
                        segmentTemplate = segmentTemplate;
                        arrayList13 = arrayList13;
                        str2 = attributeValue2;
                        j7 = j9;
                        i2 = iCheckContentTypeConsistency2;
                        j6 = availabilityTimeOffsetUs;
                        List<Label> list7 = list4;
                        arrayList14 = arrayList14;
                        list2 = list7;
                        str3 = str3;
                        arrayList12 = arrayList12;
                        arrayList17 = arrayList17;
                        arrayList5 = arrayList19;
                        i = audioChannelConfiguration;
                        arrayList16 = arrayList16;
                        i5 = i5;
                        arrayList15 = arrayList15;
                        str = str6;
                        arrayList5 = arrayList;
                        iCheckContentTypeConsistency2 = i2;
                        j9 = j7;
                        availabilityTimeOffsetUs = j6;
                        arrayList4 = arrayList5;
                        str6 = str;
                        audioChannelConfiguration = i;
                        arrayList8 = arrayList4;
                        if (XmlPullParserUtil.isEndTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.ADAPTATION_SET)) {
                            break;
                            break;
                        }
                        ArrayList arrayList25 = arrayList14;
                        list4 = list2;
                        arrayList14 = arrayList25;
                        arrayList17 = arrayList17;
                        arrayList15 = arrayList15;
                        arrayList16 = arrayList16;
                        attributeValue2 = str2;
                        i5 = i5;
                        arrayList12 = arrayList12;
                        arrayList13 = arrayList13;
                        arrayList18 = arrayList8;
                        str3 = str3;
                        segmentTemplate = segmentTemplate;
                        arrayList10 = arrayList10;
                    }
                    arrayList6 = arrayList19;
                }
                arrayList7 = arrayList6;
            }
            str2 = attributeValue2;
            arrayList8 = arrayList7;
            if (XmlPullParserUtil.isEndTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.ADAPTATION_SET)) {
                break;
                break;
            }
            ArrayList arrayList26 = arrayList14;
            list4 = list2;
            arrayList14 = arrayList26;
            arrayList17 = arrayList17;
            arrayList15 = arrayList15;
            arrayList16 = arrayList16;
            attributeValue2 = str2;
            i5 = i5;
            arrayList12 = arrayList12;
            arrayList13 = arrayList13;
            arrayList18 = arrayList8;
            str3 = str3;
            segmentTemplate = segmentTemplate;
            arrayList10 = arrayList10;
        }
        List<Representation> arrayList27 = new ArrayList<>(arrayList17.size());
        int i8 = 0;
        while (i8 < arrayList17.size()) {
            ArrayList<Descriptor> arrayList28 = arrayList15;
            List<Label> list8 = list2;
            String str9 = attributeValue6;
            arrayList27.add(buildRepresentation((RepresentationInfo) arrayList17.get(i8), str9, list8, str7, arrayList10, arrayList28));
            i8++;
            attributeValue6 = str9;
            arrayList15 = arrayList28;
            list2 = list8;
        }
        return buildAdaptationSet(j8, iCheckContentTypeConsistency2, arrayList27, arrayList12, arrayList14, arrayList16);
    }

    protected AdaptationSet buildAdaptationSet(long j, int i, List<Representation> list, List<Descriptor> list2, List<Descriptor> list3, List<Descriptor> list4) {
        return new AdaptationSet(j, i, list, list2, list3, list4);
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
            return 2;
        }
        if ("text".equals(attributeValue)) {
            return 3;
        }
        return "image".equals(attributeValue) ? 4 : -1;
    }

    /* JADX WARN: Code duplicated, block: B:39:0x009f  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v29 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.util.UUID] */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.util.UUID] */
    protected Pair<String, DrmInitData.SchemeData> parseContentProtection(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String attributeValue;
        byte[] bArrBuildPsshAtom;
        ?? text;
        ?? r4;
        UUID uuid;
        UUID uuid2;
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "schemeIdUri");
        if (attributeValue2 != null) {
            String lowerCase = Ascii.toLowerCase(attributeValue2);
            lowerCase.hashCode();
            switch (lowerCase) {
                case "urn:uuid:e2719d58-a985-b3c9-781a-b030af78d30e":
                    uuid2 = C.CLEARKEY_UUID;
                    attributeValue = null;
                    bArrBuildPsshAtom = null;
                    text = 0;
                    r4 = uuid2;
                    break;
                case "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95":
                    uuid2 = C.PLAYREADY_UUID;
                    attributeValue = null;
                    bArrBuildPsshAtom = null;
                    text = 0;
                    r4 = uuid2;
                    break;
                case "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed":
                    uuid2 = C.WIDEVINE_UUID;
                    attributeValue = null;
                    bArrBuildPsshAtom = null;
                    text = 0;
                    r4 = uuid2;
                    break;
                case "urn:mpeg:dash:mp4protection:2011":
                    attributeValue = xmlPullParser.getAttributeValue(null, ES6Iterator.VALUE_PROPERTY);
                    String attributeValueIgnorePrefix = XmlPullParserUtil.getAttributeValueIgnorePrefix(xmlPullParser, "default_KID");
                    if (TextUtils.isEmpty(attributeValueIgnorePrefix) || "00000000-0000-0000-0000-000000000000".equals(attributeValueIgnorePrefix)) {
                        Log.w(TAG, "Ignoring <ContentProtection> with schemeIdUri=\"urn:mpeg:dash:mp4protection:2011\" (ClearKey) due to missing required default_KID attribute.");
                        bArrBuildPsshAtom = null;
                        byte[] bArr = bArrBuildPsshAtom;
                        text = bArr;
                        r4 = bArr;
                        break;
                    } else {
                        String[] strArrSplit = attributeValueIgnorePrefix.split("\\s+");
                        UUID[] uuidArr = new UUID[strArrSplit.length];
                        for (int i = 0; i < strArrSplit.length; i++) {
                            uuidArr[i] = UUID.fromString(strArrSplit[i]);
                        }
                        bArrBuildPsshAtom = PsshAtomUtil.buildPsshAtom(C.COMMON_PSSH_UUID, uuidArr, null);
                        text = 0;
                        r4 = C.COMMON_PSSH_UUID;
                        break;
                    }
                    break;
                default:
                    attributeValue = null;
                    bArrBuildPsshAtom = null;
                    byte[] bArr2 = bArrBuildPsshAtom;
                    text = bArr2;
                    r4 = bArr2;
                    break;
            }
        } else {
            attributeValue = null;
            bArrBuildPsshAtom = null;
            byte[] bArr3 = bArrBuildPsshAtom;
            text = bArr3;
            r4 = bArr3;
        }
        do {
            xmlPullParser.next();
            if ((XmlPullParserUtil.isStartTag(xmlPullParser, "clearkey:Laurl") || XmlPullParserUtil.isStartTag(xmlPullParser, "dashif:Laurl")) && xmlPullParser.next() == 4) {
                r4 = r4;
                text = xmlPullParser.getText();
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "ms:laurl")) {
                r4 = r4;
                text = xmlPullParser.getAttributeValue(null, "licenseUrl");
            } else if (bArrBuildPsshAtom == null && XmlPullParserUtil.isStartTagIgnorePrefix(xmlPullParser, "pssh") && xmlPullParser.next() == 4) {
                bArrBuildPsshAtom = Base64.decode(xmlPullParser.getText(), 0);
                uuid = PsshAtomUtil.parseUuid(bArrBuildPsshAtom);
                if (uuid == null) {
                    r4 = uuid;
                    text = text;
                    Log.w(TAG, "Skipping malformed cenc:pssh data");
                    bArrBuildPsshAtom = null;
                    r4 = uuid;
                    text = text;
                }
            } else if (bArrBuildPsshAtom == null && C.PLAYREADY_UUID.equals(r4) && XmlPullParserUtil.isStartTag(xmlPullParser, "mspr:pro") && xmlPullParser.next() == 4) {
                bArrBuildPsshAtom = PsshAtomUtil.buildPsshAtom(C.PLAYREADY_UUID, Base64.decode(xmlPullParser.getText(), 0));
                r4 = r4;
                text = text;
            } else {
                maybeSkipTag(xmlPullParser);
                r4 = r4;
                text = text;
            }
            r4 = uuid;
            text = text;
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "ContentProtection"));
        return Pair.create(attributeValue, r4 != 0 ? new DrmInitData.SchemeData(r4, text, "video/mp4", bArrBuildPsshAtom) : null);
    }

    protected void parseAdaptationSetChild(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        maybeSkipTag(xmlPullParser);
    }

    /* JADX WARN: Code duplicated, block: B:56:0x0263 A[LOOP:0: B:3:0x007b->B:56:0x0263, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:57:0x0208 A[EDGE_INSN: B:57:0x0208->B:46:0x0208 BREAK  A[LOOP:0: B:3:0x007b->B:56:0x0263], SYNTHETIC] */
    protected RepresentationInfo parseRepresentation(XmlPullParser xmlPullParser, List<BaseUrl> list, String str, String str2, String str3, String str4, int i, int i2, float f, int i3, int i4, String str5, List<Descriptor> list2, List<Descriptor> list3, List<Descriptor> list4, List<Descriptor> list5, SegmentBase segmentBase, long j, long j2, long j3, long j4, long j5, boolean z) throws XmlPullParserException, IOException {
        ArrayList arrayList;
        String str6;
        long j6;
        int audioChannelConfiguration;
        SegmentBase segmentBase2;
        ArrayList arrayList2;
        ArrayList arrayList3;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        String attributeValue = xmlPullParser2.getAttributeValue(null, "id");
        int i5 = parseInt(xmlPullParser2, "bandwidth", -1);
        String string = parseString(xmlPullParser2, "mimeType", str);
        String string2 = parseString(xmlPullParser2, "codecs", str2);
        String string3 = parseString(xmlPullParser2, "scte214:supplementalCodecs", str3);
        String string4 = parseString(xmlPullParser2, "scte214:supplementalProfiles", str4);
        int i6 = parseInt(xmlPullParser2, "width", i);
        int i7 = parseInt(xmlPullParser2, "height", i2);
        float frameRate = parseFrameRate(xmlPullParser2, f);
        int i8 = parseInt(xmlPullParser2, "audioSamplingRate", i4);
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList(list4);
        ArrayList arrayList7 = new ArrayList(list5);
        int i9 = i3;
        long j7 = j4;
        String str7 = null;
        boolean z2 = false;
        SegmentBase segmentBase3 = segmentBase;
        long availabilityTimeOffsetUs = j3;
        ArrayList arrayList8 = new ArrayList();
        while (true) {
            xmlPullParser2.next();
            arrayList = arrayList4;
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.BASE_URL)) {
                if (!z2) {
                    availabilityTimeOffsetUs = parseAvailabilityTimeOffsetUs(xmlPullParser2, availabilityTimeOffsetUs);
                    z2 = true;
                }
                str6 = attributeValue;
                arrayList8.addAll(parseBaseUrl(xmlPullParser2, list, z));
                audioChannelConfiguration = i9;
                z2 = z2;
                segmentBase2 = segmentBase3;
            } else {
                SegmentBase segmentBase4 = segmentBase3;
                str6 = attributeValue;
                if (XmlPullParserUtil.isStartTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.AUDIO_CHANNEL_CONFIGURATION)) {
                    segmentBase2 = segmentBase4;
                    audioChannelConfiguration = parseAudioChannelConfiguration(xmlPullParser2, string2);
                    z2 = z2;
                } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.SEGMENT_BASE)) {
                    arrayList = arrayList;
                    xmlPullParser2 = xmlPullParser2;
                    arrayList6 = arrayList6;
                    arrayList3 = arrayList8;
                    j7 = j7;
                    string2 = string2;
                    i5 = i5;
                    audioChannelConfiguration = i9;
                    str7 = str7;
                    z2 = z2;
                    segmentBase2 = parseSegmentBase(xmlPullParser2, (SegmentBase.SingleSegmentBase) segmentBase4);
                    arrayList7 = arrayList7;
                    j6 = availabilityTimeOffsetUs;
                    arrayList2 = arrayList3;
                } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentList")) {
                    long j8 = availabilityTimeOffsetUs;
                    long availabilityTimeOffsetUs2 = parseAvailabilityTimeOffsetUs(xmlPullParser2, j7);
                    arrayList = arrayList;
                    arrayList2 = arrayList8;
                    i5 = i5;
                    arrayList5 = arrayList5;
                    SegmentBase.SegmentList segmentList = parseSegmentList(xmlPullParser2, (SegmentBase.SegmentList) segmentBase4, j, j2, j8, availabilityTimeOffsetUs2, j5);
                    xmlPullParser2 = xmlPullParser2;
                    string2 = string2;
                    str7 = str7;
                    z2 = z2;
                    arrayList7 = arrayList7;
                    j6 = j8;
                    audioChannelConfiguration = i9;
                    segmentBase2 = segmentList;
                    arrayList6 = arrayList6;
                    j7 = availabilityTimeOffsetUs2;
                } else {
                    arrayList = arrayList;
                    ArrayList arrayList9 = arrayList6;
                    ArrayList arrayList10 = arrayList7;
                    ArrayList arrayList11 = arrayList8;
                    j6 = availabilityTimeOffsetUs;
                    i5 = i5;
                    arrayList5 = arrayList5;
                    if (XmlPullParserUtil.isStartTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.SEGMENT_TEMPLATE)) {
                        long availabilityTimeOffsetUs3 = parseAvailabilityTimeOffsetUs(xmlPullParser2, j7);
                        string2 = string2;
                        SegmentBase.SegmentTemplate segmentTemplate = parseSegmentTemplate(xmlPullParser2, (SegmentBase.SegmentTemplate) segmentBase4, list5, j, j2, j6, availabilityTimeOffsetUs3, j5);
                        xmlPullParser2 = xmlPullParser2;
                        str7 = str7;
                        z2 = z2;
                        arrayList7 = arrayList10;
                        j6 = j6;
                        audioChannelConfiguration = i9;
                        segmentBase2 = segmentTemplate;
                        arrayList6 = arrayList9;
                        j7 = availabilityTimeOffsetUs3;
                        arrayList2 = arrayList11;
                    } else {
                        xmlPullParser2 = xmlPullParser2;
                        string2 = string2;
                        if (XmlPullParserUtil.isStartTag(xmlPullParser2, "ContentProtection")) {
                            Pair<String, DrmInitData.SchemeData> contentProtection = parseContentProtection(xmlPullParser);
                            if (contentProtection.first != null) {
                                str7 = (String) contentProtection.first;
                            }
                            if (contentProtection.second != null) {
                                arrayList.add((DrmInitData.SchemeData) contentProtection.second);
                            }
                            str7 = str7;
                            arrayList6 = arrayList9;
                            arrayList7 = arrayList10;
                        } else {
                            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "InbandEventStream")) {
                                arrayList5.add(parseDescriptor(xmlPullParser2, "InbandEventStream"));
                                arrayList6 = arrayList9;
                            } else {
                                if (XmlPullParserUtil.isStartTag(xmlPullParser2, "EssentialProperty")) {
                                    arrayList6 = arrayList9;
                                    arrayList6.add(parseDescriptor(xmlPullParser2, "EssentialProperty"));
                                } else {
                                    arrayList6 = arrayList9;
                                    if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SupplementalProperty")) {
                                        arrayList7 = arrayList10;
                                        arrayList7.add(parseDescriptor(xmlPullParser2, "SupplementalProperty"));
                                    } else {
                                        arrayList7 = arrayList10;
                                        maybeSkipTag(xmlPullParser2);
                                    }
                                }
                                str7 = str7;
                            }
                            arrayList7 = arrayList10;
                            str7 = str7;
                        }
                        audioChannelConfiguration = i9;
                        segmentBase2 = segmentBase4;
                        arrayList2 = arrayList11;
                    }
                }
                if (XmlPullParserUtil.isEndTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.REPRESENTATION)) {
                    break;
                }
                int i10 = i6;
                ArrayList arrayList12 = arrayList5;
                String str8 = string4;
                i6 = i10;
                arrayList5 = arrayList12;
                arrayList6 = arrayList6;
                arrayList7 = arrayList7;
                arrayList4 = arrayList;
                segmentBase3 = segmentBase2;
                availabilityTimeOffsetUs = j6;
                j7 = j7;
                arrayList8 = arrayList2;
                string2 = string2;
                z2 = z2;
                str7 = str7;
                i9 = audioChannelConfiguration;
                string3 = string3;
                string4 = str8;
                attributeValue = str6;
                i5 = i5;
                xmlPullParser2 = xmlPullParser2;
            }
            arrayList3 = arrayList8;
            j6 = availabilityTimeOffsetUs;
            arrayList2 = arrayList3;
            if (XmlPullParserUtil.isEndTag(xmlPullParser2, YoutubeDashManifestCreatorsUtils.REPRESENTATION)) {
                break;
                break;
            }
            int i11 = i6;
            ArrayList arrayList13 = arrayList5;
            String str9 = string4;
            i6 = i11;
            arrayList5 = arrayList13;
            arrayList6 = arrayList6;
            arrayList7 = arrayList7;
            arrayList4 = arrayList;
            segmentBase3 = segmentBase2;
            availabilityTimeOffsetUs = j6;
            j7 = j7;
            arrayList8 = arrayList2;
            string2 = string2;
            z2 = z2;
            str7 = str7;
            i9 = audioChannelConfiguration;
            string3 = string3;
            string4 = str9;
            attributeValue = str6;
            i5 = i5;
            xmlPullParser2 = xmlPullParser2;
        }
        ArrayList arrayList14 = arrayList5;
        String str10 = string4;
        ArrayList arrayList15 = arrayList;
        String str11 = string3;
        List<Descriptor> list6 = arrayList7;
        List<Descriptor> list7 = arrayList6;
        Format formatBuildFormat = buildFormat(str6, string, i6, i7, frameRate, audioChannelConfiguration, i8, i5, str5, list2, list3, string2, str11, str10, list7, list6);
        if (segmentBase2 == null) {
            segmentBase2 = new SegmentBase.SingleSegmentBase();
        }
        boolean zIsEmpty = arrayList2.isEmpty();
        List list8 = arrayList2;
        if (zIsEmpty) {
            list8 = list;
        }
        return new RepresentationInfo(formatBuildFormat, list8, segmentBase2, str7, arrayList15, arrayList14, list7, list6, -1L);
    }

    protected Format buildFormat(String str, String str2, int i, int i2, float f, int i3, int i4, int i5, String str3, List<Descriptor> list, List<Descriptor> list2, String str4, String str5, String str6, List<Descriptor> list3, List<Descriptor> list4) {
        String str7 = str5;
        String str8 = str4;
        String sampleMimeType = getSampleMimeType(str2, str8);
        if ("audio/eac3".equals(sampleMimeType)) {
            sampleMimeType = parseEac3SupplementalProperties(list4);
            if ("audio/eac3-joc".equals(sampleMimeType)) {
                str8 = "ec+3";
            }
        }
        if (MimeTypes.isDolbyVisionCodec(str8, str7)) {
            if (str7 == null) {
                str7 = str8;
            }
            sampleMimeType = "video/dolby-vision";
            str8 = str7;
        }
        int selectionFlagsFromRoleDescriptors = parseSelectionFlagsFromRoleDescriptors(list);
        int roleFlagsFromRoleDescriptors = parseRoleFlagsFromRoleDescriptors(list) | parseRoleFlagsFromAccessibilityDescriptors(list2) | parseRoleFlagsFromProperties(list3) | parseRoleFlagsFromProperties(list4);
        Pair<Integer, Integer> tileCountFromProperties = parseTileCountFromProperties(list3);
        Format.Builder language = new Format.Builder().setId(str).setContainerMimeType(str2).setSampleMimeType(sampleMimeType).setCodecs(str8).setPeakBitrate(i5).setSelectionFlags(selectionFlagsFromRoleDescriptors).setRoleFlags(roleFlagsFromRoleDescriptors).setLanguage(str3);
        int cea708AccessibilityChannel = -1;
        Format.Builder tileCountVertical = language.setTileCountHorizontal(tileCountFromProperties != null ? ((Integer) tileCountFromProperties.first).intValue() : -1).setTileCountVertical(tileCountFromProperties != null ? ((Integer) tileCountFromProperties.second).intValue() : -1);
        if (MimeTypes.isVideo(sampleMimeType)) {
            tileCountVertical.setWidth(i).setHeight(i2).setFrameRate(f);
        } else if (MimeTypes.isAudio(sampleMimeType)) {
            tileCountVertical.setChannelCount(i3).setSampleRate(i4);
        } else if (MimeTypes.isText(sampleMimeType)) {
            if ("application/cea-608".equals(sampleMimeType)) {
                cea708AccessibilityChannel = parseCea608AccessibilityChannel(list2);
            } else if ("application/cea-708".equals(sampleMimeType)) {
                cea708AccessibilityChannel = parseCea708AccessibilityChannel(list2);
            }
            tileCountVertical.setAccessibilityChannel(cea708AccessibilityChannel);
        } else if (MimeTypes.isImage(sampleMimeType)) {
            tileCountVertical.setWidth(i).setHeight(i2);
        }
        return tileCountVertical.build();
    }

    protected Representation buildRepresentation(RepresentationInfo representationInfo, String str, List<Label> list, String str2, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2) {
        Format.Builder builderBuildUpon = representationInfo.format.buildUpon();
        if (str != null && list.isEmpty()) {
            builderBuildUpon.setLabel(str);
        } else {
            builderBuildUpon.setLabels(list);
        }
        String str3 = representationInfo.drmSchemeType;
        if (str3 != null) {
            str2 = str3;
        }
        ArrayList<DrmInitData.SchemeData> arrayList3 = representationInfo.drmSchemeDatas;
        arrayList3.addAll(arrayList);
        if (!arrayList3.isEmpty()) {
            fillInClearKeyInformation(arrayList3);
            filterRedundantIncompleteSchemeDatas(arrayList3);
            builderBuildUpon.setDrmInitData(new DrmInitData(str2, arrayList3));
        }
        ArrayList<Descriptor> arrayList4 = representationInfo.inbandEventStreams;
        arrayList4.addAll(arrayList2);
        return Representation.newInstance(representationInfo.revisionId, builderBuildUpon.build(), representationInfo.baseUrls, representationInfo.segmentBase, arrayList4, representationInfo.essentialProperties, representationInfo.supplementalProperties, null);
    }

    protected SegmentBase.SingleSegmentBase parseSegmentBase(XmlPullParser xmlPullParser, SegmentBase.SingleSegmentBase singleSegmentBase) throws XmlPullParserException, IOException {
        long j = parseLong(xmlPullParser, "timescale", singleSegmentBase != null ? singleSegmentBase.timescale : 1L);
        long j2 = parseLong(xmlPullParser, "presentationTimeOffset", singleSegmentBase != null ? singleSegmentBase.presentationTimeOffset : 0L);
        long j3 = singleSegmentBase != null ? singleSegmentBase.indexStart : 0L;
        long j4 = singleSegmentBase != null ? singleSegmentBase.indexLength : 0L;
        String attributeValue = xmlPullParser.getAttributeValue(null, "indexRange");
        if (attributeValue != null) {
            String[] strArrSplit = attributeValue.split("-");
            j3 = Long.parseLong(strArrSplit[0]);
            j4 = (Long.parseLong(strArrSplit[1]) - j3) + 1;
        }
        long j5 = j4;
        RangedUri initialization = singleSegmentBase != null ? singleSegmentBase.initialization : null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, YoutubeDashManifestCreatorsUtils.INITIALIZATION)) {
                initialization = parseInitialization(xmlPullParser);
            } else {
                maybeSkipTag(xmlPullParser);
            }
            RangedUri rangedUri = initialization;
            if (XmlPullParserUtil.isEndTag(xmlPullParser, YoutubeDashManifestCreatorsUtils.SEGMENT_BASE)) {
                return buildSingleSegmentBase(rangedUri, j, j2, j3, j5);
            }
            initialization = rangedUri;
        }
    }

    protected SegmentBase.SingleSegmentBase buildSingleSegmentBase(RangedUri rangedUri, long j, long j2, long j3, long j4) {
        return new SegmentBase.SingleSegmentBase(rangedUri, j, j2, j3, j4);
    }

    protected SegmentBase.SegmentList parseSegmentList(XmlPullParser xmlPullParser, SegmentBase.SegmentList segmentList, long j, long j2, long j3, long j4, long j5) throws XmlPullParserException, IOException {
        long j6;
        long j7 = parseLong(xmlPullParser, "timescale", segmentList != null ? segmentList.timescale : 1L);
        long j8 = parseLong(xmlPullParser, "presentationTimeOffset", segmentList != null ? segmentList.presentationTimeOffset : 0L);
        long j9 = parseLong(xmlPullParser, TypedValues.TransitionType.S_DURATION, segmentList != null ? segmentList.duration : C.TIME_UNSET);
        long j10 = parseLong(xmlPullParser, "startNumber", segmentList != null ? segmentList.startNumber : 1L);
        long finalAvailabilityTimeOffset = getFinalAvailabilityTimeOffset(j3, j4);
        List<RangedUri> arrayList = null;
        RangedUri initialization = null;
        List<SegmentBase.SegmentTimelineElement> segmentTimeline = null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, YoutubeDashManifestCreatorsUtils.INITIALIZATION)) {
                initialization = parseInitialization(xmlPullParser);
                j6 = j7;
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, YoutubeDashManifestCreatorsUtils.SEGMENT_TIMELINE)) {
                j6 = j7;
                segmentTimeline = parseSegmentTimeline(xmlPullParser, j6, j2);
            } else {
                j6 = j7;
                if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentURL")) {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    arrayList.add(parseSegmentUrl(xmlPullParser));
                } else {
                    maybeSkipTag(xmlPullParser);
                }
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "SegmentList")) {
                break;
            }
            j7 = j6;
        }
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
        return buildSegmentList(initialization, j6, j8, j10, j9, segmentTimeline, finalAvailabilityTimeOffset, arrayList, j5, j);
    }

    protected SegmentBase.SegmentList buildSegmentList(RangedUri rangedUri, long j, long j2, long j3, long j4, List<SegmentBase.SegmentTimelineElement> list, long j5, List<RangedUri> list2, long j6, long j7) {
        return new SegmentBase.SegmentList(rangedUri, j, j2, j3, j4, list, j5, list2, Util.msToUs(j6), Util.msToUs(j7));
    }

    protected SegmentBase.SegmentTemplate parseSegmentTemplate(XmlPullParser xmlPullParser, SegmentBase.SegmentTemplate segmentTemplate, List<Descriptor> list, long j, long j2, long j3, long j4, long j5) throws XmlPullParserException, IOException {
        long j6;
        DashManifestParser dashManifestParser = this;
        long j7 = parseLong(xmlPullParser, "timescale", segmentTemplate != null ? segmentTemplate.timescale : 1L);
        long j8 = parseLong(xmlPullParser, "presentationTimeOffset", segmentTemplate != null ? segmentTemplate.presentationTimeOffset : 0L);
        long j9 = parseLong(xmlPullParser, TypedValues.TransitionType.S_DURATION, segmentTemplate != null ? segmentTemplate.duration : C.TIME_UNSET);
        long j10 = parseLong(xmlPullParser, "startNumber", segmentTemplate != null ? segmentTemplate.startNumber : 1L);
        long lastSegmentNumberSupplementalProperty = parseLastSegmentNumberSupplementalProperty(list);
        long finalAvailabilityTimeOffset = getFinalAvailabilityTimeOffset(j3, j4);
        UrlTemplate urlTemplate = dashManifestParser.parseUrlTemplate(xmlPullParser, LinkHeader.Parameters.Media, segmentTemplate != null ? segmentTemplate.mediaTemplate : null);
        UrlTemplate urlTemplate2 = dashManifestParser.parseUrlTemplate(xmlPullParser, "initialization", segmentTemplate != null ? segmentTemplate.initializationTemplate : null);
        RangedUri initialization = null;
        List<SegmentBase.SegmentTimelineElement> segmentTimeline = null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, YoutubeDashManifestCreatorsUtils.INITIALIZATION)) {
                initialization = parseInitialization(xmlPullParser);
                j6 = j7;
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, YoutubeDashManifestCreatorsUtils.SEGMENT_TIMELINE)) {
                j6 = j7;
                segmentTimeline = dashManifestParser.parseSegmentTimeline(xmlPullParser, j6, j2);
            } else {
                j6 = j7;
                maybeSkipTag(xmlPullParser);
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser, YoutubeDashManifestCreatorsUtils.SEGMENT_TEMPLATE)) {
                break;
            }
            dashManifestParser = this;
            finalAvailabilityTimeOffset = finalAvailabilityTimeOffset;
            lastSegmentNumberSupplementalProperty = lastSegmentNumberSupplementalProperty;
            j10 = j10;
            j8 = j8;
            j7 = j6;
        }
        if (segmentTemplate != null) {
            if (initialization == null) {
                initialization = segmentTemplate.initialization;
            }
            if (segmentTimeline == null) {
                segmentTimeline = segmentTemplate.segmentTimeline;
            }
        }
        return buildSegmentTemplate(initialization, j6, j8, j10, lastSegmentNumberSupplementalProperty, j9, segmentTimeline, finalAvailabilityTimeOffset, urlTemplate2, urlTemplate, j5, j);
    }

    protected SegmentBase.SegmentTemplate buildSegmentTemplate(RangedUri rangedUri, long j, long j2, long j3, long j4, long j5, List<SegmentBase.SegmentTimelineElement> list, long j6, UrlTemplate urlTemplate, UrlTemplate urlTemplate2, long j7, long j8) {
        return new SegmentBase.SegmentTemplate(rangedUri, j, j2, j3, j4, j5, list, j6, urlTemplate, urlTemplate2, Util.msToUs(j7), Util.msToUs(j8));
    }

    protected EventStream parseEventStream(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        DashManifestParser dashManifestParser;
        long j;
        String str;
        String str2;
        XmlPullParser xmlPullParser2;
        String string = parseString(xmlPullParser, "schemeIdUri", "");
        String string2 = parseString(xmlPullParser, ES6Iterator.VALUE_PROPERTY, "");
        long j2 = parseLong(xmlPullParser, "timescale", 1L);
        long j3 = parseLong(xmlPullParser, "presentationTimeOffset", 0L);
        ArrayList arrayList = new ArrayList();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Event")) {
                dashManifestParser = this;
                j = j2;
                str = string2;
                str2 = string;
                xmlPullParser2 = xmlPullParser;
                arrayList.add(dashManifestParser.parseEvent(xmlPullParser2, str2, str, j, j3, byteArrayOutputStream));
            } else {
                dashManifestParser = this;
                j = j2;
                str = string2;
                str2 = string;
                xmlPullParser2 = xmlPullParser;
                maybeSkipTag(xmlPullParser2);
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser2, "EventStream")) {
                break;
            }
            this = dashManifestParser;
            xmlPullParser = xmlPullParser2;
            string = str2;
            string2 = str;
            j2 = j;
        }
        long[] jArr = new long[arrayList.size()];
        EventMessage[] eventMessageArr = new EventMessage[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            Pair pair = (Pair) arrayList.get(i);
            jArr[i] = ((Long) pair.first).longValue();
            eventMessageArr[i] = (EventMessage) pair.second;
        }
        return dashManifestParser.buildEventStream(str2, str, j, jArr, eventMessageArr);
    }

    protected EventStream buildEventStream(String str, String str2, long j, long[] jArr, EventMessage[] eventMessageArr) {
        return new EventStream(str, str2, j, jArr, eventMessageArr);
    }

    protected Pair<Long, EventMessage> parseEvent(XmlPullParser xmlPullParser, String str, String str2, long j, long j2, ByteArrayOutputStream byteArrayOutputStream) throws XmlPullParserException, IOException {
        long j3 = parseLong(xmlPullParser, "id", 0L);
        long j4 = parseLong(xmlPullParser, TypedValues.TransitionType.S_DURATION, C.TIME_UNSET);
        long j5 = parseLong(xmlPullParser, "presentationTime", 0L);
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(j4, 1000L, j);
        long jScaleLargeTimestamp2 = Util.scaleLargeTimestamp(j5 - j2, 1000000L, j);
        String string = parseString(xmlPullParser, "messageData", null);
        byte[] eventObject = parseEventObject(xmlPullParser, byteArrayOutputStream);
        Long lValueOf = Long.valueOf(jScaleLargeTimestamp2);
        if (string != null) {
            eventObject = Util.getUtf8Bytes(string);
        }
        return Pair.create(lValueOf, buildEvent(str, str2, j3, jScaleLargeTimestamp, eventObject));
    }

    protected byte[] parseEventObject(XmlPullParser xmlPullParser, ByteArrayOutputStream byteArrayOutputStream) throws XmlPullParserException, IOException {
        byteArrayOutputStream.reset();
        XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
        xmlSerializerNewSerializer.setOutput(byteArrayOutputStream, StandardCharsets.UTF_8.name());
        xmlPullParser.nextToken();
        while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Event")) {
            switch (xmlPullParser.getEventType()) {
                case 0:
                    xmlSerializerNewSerializer.startDocument(null, false);
                    break;
                case 1:
                    xmlSerializerNewSerializer.endDocument();
                    break;
                case 2:
                    xmlSerializerNewSerializer.startTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                        xmlSerializerNewSerializer.attribute(xmlPullParser.getAttributeNamespace(i), xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
                    }
                    break;
                case 3:
                    xmlSerializerNewSerializer.endTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    break;
                case 4:
                    xmlSerializerNewSerializer.text(xmlPullParser.getText());
                    break;
                case 5:
                    xmlSerializerNewSerializer.cdsect(xmlPullParser.getText());
                    break;
                case 6:
                    xmlSerializerNewSerializer.entityRef(xmlPullParser.getText());
                    break;
                case 7:
                    xmlSerializerNewSerializer.ignorableWhitespace(xmlPullParser.getText());
                    break;
                case 8:
                    xmlSerializerNewSerializer.processingInstruction(xmlPullParser.getText());
                    break;
                case 9:
                    xmlSerializerNewSerializer.comment(xmlPullParser.getText());
                    break;
                case 10:
                    xmlSerializerNewSerializer.docdecl(xmlPullParser.getText());
                    break;
            }
            xmlPullParser.nextToken();
        }
        xmlSerializerNewSerializer.flush();
        return byteArrayOutputStream.toByteArray();
    }

    protected EventMessage buildEvent(String str, String str2, long j, long j2, byte[] bArr) {
        return new EventMessage(str, str2, j2, j, bArr);
    }

    protected List<SegmentBase.SegmentTimelineElement> parseSegmentTimeline(XmlPullParser xmlPullParser, long j, long j2) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        long jAddSegmentTimelineElementsToList = 0;
        long j3 = -9223372036854775807L;
        boolean z = false;
        int i = 0;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, ExifInterface.LATITUDE_SOUTH)) {
                long j4 = parseLong(xmlPullParser, "t", C.TIME_UNSET);
                if (z) {
                    ArrayList arrayList2 = arrayList;
                    jAddSegmentTimelineElementsToList = addSegmentTimelineElementsToList(arrayList2, jAddSegmentTimelineElementsToList, j3, i, j4);
                    arrayList = arrayList2;
                }
                if (j4 != C.TIME_UNSET) {
                    jAddSegmentTimelineElementsToList = j4;
                }
                j3 = parseLong(xmlPullParser, "d", C.TIME_UNSET);
                i = parseInt(xmlPullParser, "r", 0);
                z = true;
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, YoutubeDashManifestCreatorsUtils.SEGMENT_TIMELINE));
        if (z) {
            addSegmentTimelineElementsToList(arrayList, jAddSegmentTimelineElementsToList, j3, i, Util.scaleLargeTimestamp(j2, j, 1000L));
        }
        return arrayList;
    }

    private long addSegmentTimelineElementsToList(List<SegmentBase.SegmentTimelineElement> list, long j, long j2, int i, long j3) {
        int iCeilDivide = i >= 0 ? i + 1 : (int) Util.ceilDivide(j3 - j, j2);
        for (int i2 = 0; i2 < iCeilDivide; i2++) {
            list.add(buildSegmentTimelineElement(j, j2));
            j += j2;
        }
        return j;
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
        return parseRangedUrl(xmlPullParser, LinkHeader.Parameters.Media, "mediaRange");
    }

    protected RangedUri parseRangedUrl(XmlPullParser xmlPullParser, String str, String str2) {
        long j;
        long j2;
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, str2);
        if (attributeValue2 != null) {
            String[] strArrSplit = attributeValue2.split("-");
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

    protected ProgramInformation parseProgramInformation(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String strNextText = null;
        String string = parseString(xmlPullParser, "moreInformationURL", null);
        String string2 = parseString(xmlPullParser, "lang", null);
        String strNextText2 = null;
        String strNextText3 = null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Title")) {
                strNextText = xmlPullParser.nextText();
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "Source")) {
                strNextText2 = xmlPullParser.nextText();
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, ExifInterface.TAG_COPYRIGHT)) {
                strNextText3 = xmlPullParser.nextText();
            } else {
                maybeSkipTag(xmlPullParser);
            }
            String str = strNextText2;
            String str2 = strNextText;
            String str3 = strNextText3;
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "ProgramInformation")) {
                return new ProgramInformation(str2, str, str3, string, string2);
            }
            strNextText = str2;
            strNextText2 = str;
            strNextText3 = str3;
        }
    }

    protected Label parseLabel(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return new Label(xmlPullParser.getAttributeValue(null, "lang"), parseText(xmlPullParser, TextFieldImplKt.LabelId));
    }

    protected List<BaseUrl> parseBaseUrl(XmlPullParser xmlPullParser, List<BaseUrl> list, boolean z) throws XmlPullParserException, IOException {
        int i;
        String attributeValue = xmlPullParser.getAttributeValue(null, "dvb:priority");
        if (attributeValue != null) {
            i = Integer.parseInt(attributeValue);
        } else {
            i = z ? 1 : Integer.MIN_VALUE;
        }
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "dvb:weight");
        int i2 = attributeValue2 != null ? Integer.parseInt(attributeValue2) : 1;
        String attributeValue3 = xmlPullParser.getAttributeValue(null, "serviceLocation");
        String text = parseText(xmlPullParser, YoutubeDashManifestCreatorsUtils.BASE_URL);
        if (UriUtil.isAbsolute(text)) {
            if (attributeValue3 == null) {
                attributeValue3 = text;
            }
            return Lists.newArrayList(new BaseUrl(text, attributeValue3, i, i2));
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < list.size(); i3++) {
            BaseUrl baseUrl = list.get(i3);
            String strResolve = UriUtil.resolve(baseUrl.url, text);
            String str = attributeValue3 == null ? strResolve : attributeValue3;
            if (z) {
                i = baseUrl.priority;
                i2 = baseUrl.weight;
                str = baseUrl.serviceLocation;
            }
            arrayList.add(new BaseUrl(strResolve, str, i, i2));
        }
        return arrayList;
    }

    protected long parseAvailabilityTimeOffsetUs(XmlPullParser xmlPullParser, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "availabilityTimeOffset");
        if (attributeValue == null) {
            return j;
        }
        if ("INF".equals(attributeValue)) {
            return Long.MAX_VALUE;
        }
        return (long) (Float.parseFloat(attributeValue) * 1000000.0f);
    }

    protected int parseAudioChannelConfiguration(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String string = parseString(xmlPullParser, "schemeIdUri", null);
        string.hashCode();
        int dtsChannelConfiguration = -1;
        switch (string) {
            case "urn:dts:dash:audio_channel_configuration:2012":
            case "tag:dts.com,2014:dash:audio_channel_configuration:2012":
                dtsChannelConfiguration = parseDtsChannelConfiguration(xmlPullParser);
                break;
            case "tag:dolby.com,2015:dash:audio_channel_configuration:2015":
                dtsChannelConfiguration = parseDolbyAC4ChannelConfiguration(xmlPullParser, str);
                break;
            case "urn:mpeg:dash:23003:3:audio_channel_configuration:2011":
                dtsChannelConfiguration = parseInt(xmlPullParser, ES6Iterator.VALUE_PROPERTY, -1);
                break;
            case "tag:dolby.com,2014:dash:audio_channel_configuration:2011":
            case "urn:dolby:dash:audio_channel_configuration:2011":
                dtsChannelConfiguration = parseDolbyChannelConfiguration(xmlPullParser);
                break;
            case "urn:mpeg:mpegB:cicp:ChannelConfiguration":
                dtsChannelConfiguration = parseMpegChannelConfiguration(xmlPullParser);
                break;
            case "tag:dts.com,2018:uhd:audio_channel_configuration":
                dtsChannelConfiguration = parseDtsxChannelConfiguration(xmlPullParser);
                break;
        }
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, YoutubeDashManifestCreatorsUtils.AUDIO_CHANNEL_CONFIGURATION));
        return dtsChannelConfiguration;
    }

    protected int parseSelectionFlagsFromRoleDescriptors(List<Descriptor> list) {
        int selectionFlagsFromDashRoleScheme = 0;
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if (Ascii.equalsIgnoreCase("urn:mpeg:dash:role:2011", descriptor.schemeIdUri)) {
                selectionFlagsFromDashRoleScheme |= parseSelectionFlagsFromDashRoleScheme(descriptor.value);
            }
        }
        return selectionFlagsFromDashRoleScheme;
    }

    protected int parseSelectionFlagsFromDashRoleScheme(String str) {
        if (str == null) {
            return 0;
        }
        str.hashCode();
        return (str.equals(MediaTrack.ROLE_FORCED_SUBTITLE) || str.equals("forced-subtitle")) ? 2 : 0;
    }

    protected int parseRoleFlagsFromRoleDescriptors(List<Descriptor> list) {
        int roleFlagsFromDashRoleScheme = 0;
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if (Ascii.equalsIgnoreCase("urn:mpeg:dash:role:2011", descriptor.schemeIdUri)) {
                roleFlagsFromDashRoleScheme |= parseRoleFlagsFromDashRoleScheme(descriptor.value);
            }
        }
        return roleFlagsFromDashRoleScheme;
    }

    protected int parseRoleFlagsFromAccessibilityDescriptors(List<Descriptor> list) {
        int tvaAudioPurposeCsValue;
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Descriptor descriptor = list.get(i2);
            if (Ascii.equalsIgnoreCase("urn:mpeg:dash:role:2011", descriptor.schemeIdUri)) {
                tvaAudioPurposeCsValue = parseRoleFlagsFromDashRoleScheme(descriptor.value);
            } else {
                if (Ascii.equalsIgnoreCase("urn:tva:metadata:cs:AudioPurposeCS:2007", descriptor.schemeIdUri)) {
                    tvaAudioPurposeCsValue = parseTvaAudioPurposeCsValue(descriptor.value);
                }
            }
            i |= tvaAudioPurposeCsValue;
        }
        return i;
    }

    protected int parseRoleFlagsFromProperties(List<Descriptor> list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (Ascii.equalsIgnoreCase("http://dashif.org/guidelines/trickmode", list.get(i2).schemeIdUri)) {
                i = 16384;
            }
        }
        return i;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    protected int parseRoleFlagsFromDashRoleScheme(String str) {
        if (str == null) {
            return 0;
        }
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -2060497896:
                if (str.equals(MediaTrack.ROLE_SUBTITLE)) {
                    b = 0;
                }
                break;
            case -1724546052:
                if (str.equals("description")) {
                    b = 1;
                }
                break;
            case -1580883024:
                if (str.equals("enhanced-audio-intelligibility")) {
                    b = 2;
                }
                break;
            case -1574842690:
                if (str.equals(MediaTrack.ROLE_FORCED_SUBTITLE)) {
                    b = 3;
                }
                break;
            case -1408024454:
                if (str.equals("alternate")) {
                    b = 4;
                }
                break;
            case -1396432756:
                if (str.equals("forced-subtitle")) {
                    b = 5;
                }
                break;
            case 99825:
                if (str.equals(MediaTrack.ROLE_DUB)) {
                    b = 6;
                }
                break;
            case 3343801:
                if (str.equals(MediaTrack.ROLE_MAIN)) {
                    b = 7;
                }
                break;
            case 3530173:
                if (str.equals(MediaTrack.ROLE_SIGN)) {
                    b = 8;
                }
                break;
            case 552573414:
                if (str.equals(MediaTrack.ROLE_CAPTION)) {
                    b = 9;
                }
                break;
            case 899152809:
                if (str.equals(MediaTrack.ROLE_COMMENTARY)) {
                    b = 10;
                }
                break;
            case 1629013393:
                if (str.equals(MediaTrack.ROLE_EMERGENCY)) {
                    b = 11;
                }
                break;
            case 1855372047:
                if (str.equals(MediaTrack.ROLE_SUPPLEMENTARY)) {
                    b = Ascii.FF;
                }
                break;
        }
        switch (b) {
            case 0:
            case 3:
            case 5:
                return 128;
            case 1:
                return 512;
            case 2:
                return 2048;
            case 4:
                return 2;
            case 6:
                return 16;
            case 7:
                return 1;
            case 8:
                return 256;
            case 9:
                return 64;
            case 10:
                return 8;
            case 11:
                return 32;
            case 12:
                return 4;
            default:
                return 0;
        }
    }

    protected int parseTvaAudioPurposeCsValue(String str) {
        if (str == null) {
            return 0;
        }
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    b = 0;
                }
                break;
            case 50:
                if (str.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    b = 1;
                }
                break;
            case 51:
                if (str.equals("3")) {
                    b = 2;
                }
                break;
            case 52:
                if (str.equals("4")) {
                    b = 3;
                }
                break;
            case 54:
                if (str.equals("6")) {
                    b = 4;
                }
                break;
        }
        switch (b) {
            case 0:
                return 512;
            case 1:
                return 2048;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 1;
            default:
                return 0;
        }
    }

    protected String[] parseProfiles(XmlPullParser xmlPullParser, String str, String[] strArr) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? strArr : attributeValue.split(",");
    }

    protected Pair<Integer, Integer> parseTileCountFromProperties(List<Descriptor> list) {
        char c = 0;
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ((Ascii.equalsIgnoreCase("http://dashif.org/thumbnail_tile", descriptor.schemeIdUri) || Ascii.equalsIgnoreCase("http://dashif.org/guidelines/thumbnail_tile", descriptor.schemeIdUri)) && descriptor.value != null) {
                String[] strArrSplit = Util.split(descriptor.value, "x");
                if (strArrSplit.length != 2) {
                    continue;
                } else {
                    try {
                        return Pair.create(Integer.valueOf(Integer.parseInt(strArrSplit[c])), Integer.valueOf(Integer.parseInt(strArrSplit[1])));
                    } catch (NumberFormatException unused) {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    public static void maybeSkipTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (XmlPullParserUtil.isStartTag(xmlPullParser)) {
            int i = 1;
            while (i != 0) {
                xmlPullParser.next();
                if (XmlPullParserUtil.isStartTag(xmlPullParser)) {
                    i++;
                } else if (XmlPullParserUtil.isEndTag(xmlPullParser)) {
                    i--;
                }
            }
        }
    }

    private static void filterRedundantIncompleteSchemeDatas(ArrayList<DrmInitData.SchemeData> arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            DrmInitData.SchemeData schemeData = arrayList.get(size);
            if (!schemeData.hasData()) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).canReplace(schemeData)) {
                        arrayList.remove(size);
                        break;
                    }
                }
            }
        }
    }

    private static void fillInClearKeyInformation(ArrayList<DrmInitData.SchemeData> arrayList) {
        String str;
        int i = 0;
        while (true) {
            if (i >= arrayList.size()) {
                str = null;
                break;
            }
            DrmInitData.SchemeData schemeData = arrayList.get(i);
            if (C.CLEARKEY_UUID.equals(schemeData.uuid) && schemeData.licenseServerUrl != null) {
                str = schemeData.licenseServerUrl;
                arrayList.remove(i);
                break;
            }
            i++;
        }
        if (str == null) {
            return;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            DrmInitData.SchemeData schemeData2 = arrayList.get(i2);
            if (C.COMMON_PSSH_UUID.equals(schemeData2.uuid) && schemeData2.licenseServerUrl == null) {
                arrayList.set(i2, new DrmInitData.SchemeData(C.CLEARKEY_UUID, str, schemeData2.mimeType, schemeData2.data));
            }
        }
    }

    private static String getSampleMimeType(String str, String str2) {
        if (MimeTypes.isAudio(str)) {
            return MimeTypes.getAudioMediaMimeType(str2);
        }
        if (MimeTypes.isVideo(str)) {
            return MimeTypes.getVideoMediaMimeType(str2);
        }
        if (MimeTypes.isText(str) || MimeTypes.isImage(str)) {
            return str;
        }
        if (!"application/mp4".equals(str)) {
            return null;
        }
        String mediaMimeType = MimeTypes.getMediaMimeType(str2);
        return "text/vtt".equals(mediaMimeType) ? "application/x-mp4-vtt" : mediaMimeType;
    }

    private static String checkLanguageConsistency(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        Preconditions.checkState(str.equals(str2));
        return str;
    }

    private static int checkContentTypeConsistency(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        Preconditions.checkState(i == i2);
        return i;
    }

    protected static Descriptor parseDescriptor(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String string = parseString(xmlPullParser, "schemeIdUri", "");
        String string2 = parseString(xmlPullParser, ES6Iterator.VALUE_PROPERTY, null);
        String string3 = parseString(xmlPullParser, "id", null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, str));
        return new Descriptor(string, string2, string3);
    }

    protected static int parseCea608AccessibilityChannel(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher matcher = CEA_608_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-608 channel number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static int parseCea708AccessibilityChannel(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher matcher = CEA_708_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-708 service block number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static String parseEac3SupplementalProperties(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            String str = descriptor.schemeIdUri;
            if (!"tag:dolby.com,2018:dash:EC3_ExtensionType:2018".equals(str) || !"JOC".equals(descriptor.value)) {
                if ("tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014".equals(str) && "ec+3".equals(descriptor.value)) {
                    return "audio/eac3-joc";
                }
            } else {
                return "audio/eac3-joc";
            }
        }
        return "audio/eac3";
    }

    protected static float parseFrameRate(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "frameRate");
        if (attributeValue != null) {
            Matcher matcher = FRAME_RATE_PATTERN.matcher(attributeValue);
            if (matcher.matches()) {
                int i = Integer.parseInt(matcher.group(1));
                String strGroup = matcher.group(2);
                return !TextUtils.isEmpty(strGroup) ? i / Integer.parseInt(strGroup) : i;
            }
        }
        return f;
    }

    protected static long parseDuration(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Util.parseXsDuration(attributeValue);
    }

    protected static long parseDateTime(XmlPullParser xmlPullParser, String str, long j) throws ParserException {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Util.parseXsDateTime(attributeValue);
    }

    protected static String parseText(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String text = "";
        do {
            xmlPullParser.next();
            if (xmlPullParser.getEventType() == 4) {
                text = xmlPullParser.getText();
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, str));
        return text;
    }

    protected static int parseInt(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? i : Integer.parseInt(attributeValue);
    }

    protected static long parseLong(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Long.parseLong(attributeValue);
    }

    protected static float parseFloat(XmlPullParser xmlPullParser, String str, float f) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? f : Float.parseFloat(attributeValue);
    }

    protected static String parseString(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    protected static int parseMpegChannelConfiguration(XmlPullParser xmlPullParser) {
        int i = parseInt(xmlPullParser, ES6Iterator.VALUE_PROPERTY, -1);
        if (i >= 0) {
            int[] iArr = MPEG_CHANNEL_CONFIGURATION_MAPPING;
            if (i < iArr.length) {
                return iArr[i];
            }
        }
        return -1;
    }

    protected static int parseDtsChannelConfiguration(XmlPullParser xmlPullParser) {
        int i = parseInt(xmlPullParser, ES6Iterator.VALUE_PROPERTY, -1);
        if (i <= 0 || i >= 33) {
            return -1;
        }
        return i;
    }

    protected static int parseDtsxChannelConfiguration(XmlPullParser xmlPullParser) {
        int iBitCount;
        String attributeValue = xmlPullParser.getAttributeValue(null, ES6Iterator.VALUE_PROPERTY);
        if (attributeValue == null || (iBitCount = Integer.bitCount(Integer.parseInt(attributeValue, 16))) == 0) {
            return -1;
        }
        return iBitCount;
    }

    protected static int parseDolbyChannelConfiguration(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(null, ES6Iterator.VALUE_PROPERTY);
        if (attributeValue == null) {
            return -1;
        }
        String lowerCase = Ascii.toLowerCase(attributeValue);
        lowerCase.hashCode();
        switch (lowerCase) {
            case "4000":
                return 1;
            case "a000":
                return 2;
            case "f800":
                return 5;
            case "f801":
                return 6;
            case "fa01":
                return 8;
            default:
                return -1;
        }
    }

    protected static int parseDolbyAC4ChannelConfiguration(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue(null, ES6Iterator.VALUE_PROPERTY);
        if (attributeValue == null || attributeValue.length() != 6) {
            return -1;
        }
        int i = Integer.parseInt(attributeValue, 16);
        if ((8388608 & i) != 0) {
            return parseDolbyAc4ObjectBasedChannelConfiguration(str);
        }
        return parseDolbyAc4ChannelBasedChannelConfiguration(i);
    }

    private static int parseDolbyAc4ObjectBasedChannelConfiguration(String str) {
        String[] strArrSplitCodecs = Util.splitCodecs(str);
        if (strArrSplitCodecs.length == 0) {
            return -1;
        }
        List<String> listSplitToList = Splitter.on('.').splitToList(Ascii.toLowerCase(strArrSplitCodecs[0].trim()));
        if (listSplitToList.size() != 4 || !listSplitToList.get(0).equals("ac-4")) {
            return -1;
        }
        String str2 = listSplitToList.get(3);
        str2.hashCode();
        if (str2.equals("03")) {
            return 18;
        }
        return !str2.equals("04") ? -1 : 21;
    }

    private static int parseDolbyAc4ChannelBasedChannelConfiguration(int i) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = DOLBY_AC4_CHANNEL_CONFIGURATION_MAPPING;
            if (i2 >= iArr.length) {
                break;
            }
            i3 += ((i >> i2) & 1) * iArr[i2];
            i2++;
        }
        if (i3 == 0) {
            return -1;
        }
        return i3;
    }

    protected static long parseLastSegmentNumberSupplementalProperty(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if (Ascii.equalsIgnoreCase("http://dashif.org/guidelines/last-segment-number", descriptor.schemeIdUri)) {
                return Long.parseLong(descriptor.value);
            }
        }
        return -1L;
    }

    private boolean isDvbProfileDeclared(String[] strArr) {
        for (String str : strArr) {
            if (str.startsWith("urn:dvb:dash:profile:dvb-dash:")) {
                return true;
            }
        }
        return false;
    }

    protected static final class RepresentationInfo {
        public final ImmutableList<BaseUrl> baseUrls;
        public final ArrayList<DrmInitData.SchemeData> drmSchemeDatas;
        public final String drmSchemeType;
        public final List<Descriptor> essentialProperties;
        public final Format format;
        public final ArrayList<Descriptor> inbandEventStreams;
        public final long revisionId;
        public final SegmentBase segmentBase;
        public final List<Descriptor> supplementalProperties;

        public RepresentationInfo(Format format, List<BaseUrl> list, SegmentBase segmentBase, String str, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2, List<Descriptor> list2, List<Descriptor> list3, long j) {
            this.format = format;
            this.baseUrls = ImmutableList.copyOf((Collection) list);
            this.segmentBase = segmentBase;
            this.drmSchemeType = str;
            this.drmSchemeDatas = arrayList;
            this.inbandEventStreams = arrayList2;
            this.essentialProperties = list2;
            this.supplementalProperties = list3;
            this.revisionId = j;
        }
    }
}
