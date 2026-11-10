package com.google.android.exoplayer.hls;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.upstream.UriLoadable;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class HlsPlaylistParser implements UriLoadable.Parser<HlsPlaylist> {
    private static final Pattern BANDWIDTH_ATTR_REGEX = Pattern.compile("BANDWIDTH=(\\d+)\\b");
    private static final Pattern CODECS_ATTR_REGEX = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern RESOLUTION_ATTR_REGEX = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern MEDIA_DURATION_REGEX = Pattern.compile("#EXTINF:([\\d.]+)\\b");
    private static final Pattern MEDIA_SEQUENCE_REGEX = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern TARGET_DURATION_REGEX = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern VERSION_REGEX = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern BYTERANGE_REGEX = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern METHOD_ATTR_REGEX = Pattern.compile("METHOD=(NONE|AES-128)");
    private static final Pattern URI_ATTR_REGEX = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern IV_ATTR_REGEX = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern TYPE_ATTR_REGEX = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern LANGUAGE_ATTR_REGEX = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern NAME_ATTR_REGEX = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern INSTREAM_ID_ATTR_REGEX = Pattern.compile("INSTREAM-ID=\"(.+?)\"");

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer.upstream.UriLoadable.Parser
    public HlsPlaylist parse(String str, InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        LinkedList linkedList = new LinkedList();
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line != null) {
                    String strTrim = line.trim();
                    if (!strTrim.isEmpty()) {
                        if (strTrim.startsWith("#EXT-X-STREAM-INF")) {
                            linkedList.add(strTrim);
                            HlsMasterPlaylist masterPlaylist = parseMasterPlaylist(new LineIterator(linkedList, bufferedReader), str);
                            Util.closeQuietly(bufferedReader);
                            return masterPlaylist;
                        }
                        if (!strTrim.startsWith("#EXT-X-TARGETDURATION") && !strTrim.startsWith("#EXT-X-MEDIA-SEQUENCE") && !strTrim.startsWith("#EXTINF") && !strTrim.startsWith("#EXT-X-KEY") && !strTrim.startsWith("#EXT-X-BYTERANGE") && !strTrim.equals("#EXT-X-DISCONTINUITY") && !strTrim.equals("#EXT-X-DISCONTINUITY-SEQUENCE") && !strTrim.equals("#EXT-X-ENDLIST")) {
                            linkedList.add(strTrim);
                        }
                        linkedList.add(strTrim);
                        HlsMediaPlaylist mediaPlaylist = parseMediaPlaylist(new LineIterator(linkedList, bufferedReader), str);
                        Util.closeQuietly(bufferedReader);
                        return mediaPlaylist;
                    }
                } else {
                    Util.closeQuietly(bufferedReader);
                    throw new ParserException("Failed to parse the playlist, could not identify any tags.");
                }
            } catch (Throwable th) {
                Util.closeQuietly(bufferedReader);
                throw th;
            }
        }
    }

    private static HlsMasterPlaylist parseMasterPlaylist(LineIterator lineIterator, String str) throws IOException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        boolean z = false;
        int intAttr = 0;
        String str2 = null;
        String optionalStringAttr = null;
        while (true) {
            String optionalStringAttr2 = null;
            int i = -1;
            int i2 = -1;
            String optionalStringAttr3 = null;
            while (lineIterator.hasNext()) {
                String next = lineIterator.next();
                MtkDebugLogUtil.log("HlsPlaylistParser", next.toString(), 4);
                if (next.startsWith("#EXT-X-MEDIA")) {
                    String stringAttr = HlsParserUtil.parseStringAttr(next, TYPE_ATTR_REGEX, "TYPE");
                    if ("CLOSED-CAPTIONS".equals(stringAttr)) {
                        if ("CC1".equals(HlsParserUtil.parseStringAttr(next, INSTREAM_ID_ATTR_REGEX, "INSTREAM-ID"))) {
                            optionalStringAttr = HlsParserUtil.parseOptionalStringAttr(next, LANGUAGE_ATTR_REGEX);
                        }
                    } else if ("SUBTITLES".equals(stringAttr)) {
                        arrayList3.add(new Variant(HlsParserUtil.parseStringAttr(next, URI_ATTR_REGEX, "URI"), new Format(HlsParserUtil.parseStringAttr(next, NAME_ATTR_REGEX, "NAME"), "application/x-mpegURL", -1, -1, -1.0f, -1, -1, -1, HlsParserUtil.parseOptionalStringAttr(next, LANGUAGE_ATTR_REGEX), optionalStringAttr2)));
                    } else if ("AUDIO".equals(stringAttr)) {
                        String optionalStringAttr4 = HlsParserUtil.parseOptionalStringAttr(next, LANGUAGE_ATTR_REGEX);
                        String optionalStringAttr5 = HlsParserUtil.parseOptionalStringAttr(next, URI_ATTR_REGEX);
                        if (optionalStringAttr5 != null) {
                            arrayList2.add(new Variant(optionalStringAttr5, new Format(HlsParserUtil.parseStringAttr(next, NAME_ATTR_REGEX, "NAME"), "application/x-mpegURL", -1, -1, -1.0f, -1, -1, -1, optionalStringAttr4, optionalStringAttr2)));
                        } else {
                            str2 = optionalStringAttr4;
                        }
                    }
                } else if (next.startsWith("#EXT-X-STREAM-INF")) {
                    intAttr = HlsParserUtil.parseIntAttr(next, BANDWIDTH_ATTR_REGEX, "BANDWIDTH");
                    optionalStringAttr2 = HlsParserUtil.parseOptionalStringAttr(next, CODECS_ATTR_REGEX);
                    optionalStringAttr3 = HlsParserUtil.parseOptionalStringAttr(next, NAME_ATTR_REGEX);
                    String optionalStringAttr6 = HlsParserUtil.parseOptionalStringAttr(next, RESOLUTION_ATTR_REGEX);
                    if (optionalStringAttr6 != null) {
                        String[] strArrSplit = optionalStringAttr6.split("x");
                        int i3 = Integer.parseInt(strArrSplit[0]);
                        if (i3 <= 0) {
                            i3 = -1;
                        }
                        int i4 = Integer.parseInt(strArrSplit[1]);
                        if (i4 <= 0) {
                            i4 = -1;
                        }
                        i2 = i4;
                        i = i3;
                    } else {
                        i = -1;
                        i2 = -1;
                    }
                    z = true;
                } else if (!next.startsWith("#") && z) {
                    arrayList.add(new Variant(next, new Format(optionalStringAttr3 == null ? Integer.toString(arrayList.size()) : optionalStringAttr3, "application/x-mpegURL", i, i2, -1.0f, -1, -1, intAttr, null, optionalStringAttr2)));
                    z = false;
                    intAttr = 0;
                }
            }
            return new HlsMasterPlaylist(str, arrayList, arrayList2, arrayList3, str2, optionalStringAttr);
        }
    }

    private static HlsMediaPlaylist parseMediaPlaylist(LineIterator lineIterator, String str) throws IOException {
        String hexString;
        ArrayList arrayList = new ArrayList();
        long j = -1;
        long j2 = -1;
        String optionalStringAttr = null;
        String str2 = null;
        int intAttr = 1;
        boolean z = true;
        int i = 0;
        boolean z2 = false;
        int i2 = 0;
        int intAttr2 = 0;
        boolean zEquals = false;
        int intAttr3 = 0;
        long jCompileProgramDateTime = 0;
        long j3 = 0;
        double doubleAttr = 0.0d;
        long j4 = 0;
        while (lineIterator.hasNext()) {
            String next = lineIterator.next();
            MtkDebugLogUtil.log("HlsPlaylistParser", next.toString(), 4);
            if (next.startsWith("#EXT-X-TARGETDURATION")) {
                intAttr2 = HlsParserUtil.parseIntAttr(next, TARGET_DURATION_REGEX, "#EXT-X-TARGETDURATION");
            } else if (next.startsWith("#EXT-X-MEDIA-SEQUENCE")) {
                if (i2 == 0) {
                    intAttr3 = HlsParserUtil.parseIntAttr(next, MEDIA_SEQUENCE_REGEX, "#EXT-X-MEDIA-SEQUENCE");
                    i2 = intAttr3;
                } else {
                    j = -1;
                }
            } else if (next.startsWith("#EXT-X-VERSION")) {
                intAttr = HlsParserUtil.parseIntAttr(next, VERSION_REGEX, "#EXT-X-VERSION");
            } else if (next.startsWith("#EXTINF")) {
                doubleAttr = HlsParserUtil.parseDoubleAttr(next, MEDIA_DURATION_REGEX, "#EXTINF");
                if (intAttr2 == 0 || intAttr2 < ((int) (doubleAttr / 1000000.0d))) {
                    intAttr2 = (int) (doubleAttr / 1000000.0d);
                }
            } else if (next.startsWith("#EXT-X-KEY")) {
                zEquals = "AES-128".equals(HlsParserUtil.parseStringAttr(next, METHOD_ATTR_REGEX, "METHOD"));
                if (zEquals) {
                    String stringAttr = HlsParserUtil.parseStringAttr(next, URI_ATTR_REGEX, "URI");
                    optionalStringAttr = HlsParserUtil.parseOptionalStringAttr(next, IV_ATTR_REGEX);
                    str2 = stringAttr;
                } else {
                    optionalStringAttr = null;
                    str2 = null;
                }
            } else if (next.startsWith("#EXT-X-BYTERANGE")) {
                String[] strArrSplit = HlsParserUtil.parseStringAttr(next, BYTERANGE_REGEX, "#EXT-X-BYTERANGE").split("@");
                j2 = Long.parseLong(strArrSplit[0]);
                if (strArrSplit.length > 1) {
                    j3 = Long.parseLong(strArrSplit[1]);
                }
            } else if (next.startsWith("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                i = Integer.parseInt(next.substring(next.indexOf(58) + 1));
            } else if (next.equals("#EXT-X-DISCONTINUITY")) {
                i++;
            } else if (next.startsWith("#EXT-X-PROGRAM-DATE-TIME")) {
                if (z2) {
                    j = -1;
                } else {
                    jCompileProgramDateTime = HlsParserUtil.compileProgramDateTime(next, "#EXTINF");
                    z2 = true;
                }
            } else if (!next.startsWith("#")) {
                if (zEquals) {
                    hexString = optionalStringAttr != null ? optionalStringAttr : Integer.toHexString(intAttr3);
                } else {
                    hexString = null;
                }
                long j5 = j2 == j ? 0L : j3;
                arrayList.add(new HlsMediaPlaylist.Segment(next, doubleAttr, i, j4, zEquals, str2, hexString, j5, j2, intAttr3));
                j4 += (long) (doubleAttr * 1000000.0d);
                if (j2 != j) {
                    j5 += j2;
                }
                j3 = j5;
                intAttr3++;
                j = -1;
                j2 = -1;
                doubleAttr = 0.0d;
            } else {
                if (next.equals("#EXT-X-ENDLIST")) {
                    z = false;
                }
                j = -1;
            }
        }
        return new HlsMediaPlaylist(str, i2, intAttr2, intAttr, z, Collections.unmodifiableList(arrayList), jCompileProgramDateTime);
    }

    private static class LineIterator {
        private final Queue<String> extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue<String> queue, BufferedReader bufferedReader) {
            this.extraLines = queue;
            this.reader = bufferedReader;
        }

        public boolean hasNext() throws IOException {
            String strTrim;
            if (this.next != null) {
                return true;
            }
            if (!this.extraLines.isEmpty()) {
                this.next = this.extraLines.poll();
                return true;
            }
            do {
                String line = this.reader.readLine();
                this.next = line;
                if (line == null) {
                    return false;
                }
                strTrim = line.trim();
                this.next = strTrim;
            } while (strTrim.isEmpty());
            return true;
        }

        public String next() throws IOException {
            if (!hasNext()) {
                return null;
            }
            String str = this.next;
            this.next = null;
            return str;
        }
    }
}
