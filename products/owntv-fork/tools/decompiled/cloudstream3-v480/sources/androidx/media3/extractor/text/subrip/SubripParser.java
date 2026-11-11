package androidx.media3.extractor.text.subrip;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.time.DurationKt;

/* JADX INFO: loaded from: classes4.dex */
public final class SubripParser implements SubtitleParser {
    private static final String ALIGN_BOTTOM_LEFT = "{\\an1}";
    private static final String ALIGN_BOTTOM_MID = "{\\an2}";
    private static final String ALIGN_BOTTOM_RIGHT = "{\\an3}";
    private static final String ALIGN_MID_LEFT = "{\\an4}";
    private static final String ALIGN_MID_MID = "{\\an5}";
    private static final String ALIGN_MID_RIGHT = "{\\an6}";
    private static final String ALIGN_TOP_LEFT = "{\\an7}";
    private static final String ALIGN_TOP_MID = "{\\an8}";
    private static final String ALIGN_TOP_RIGHT = "{\\an9}";
    public static final int CUE_REPLACEMENT_BEHAVIOR = 1;
    private static final float END_FRACTION = 0.92f;
    private static final float MID_FRACTION = 0.5f;
    private static final float START_FRACTION = 0.08f;
    private static final String SUBRIP_ALIGNMENT_TAG = "\\{\\\\an[1-9]\\}";
    private static final String SUBRIP_TIMECODE = "(?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d{3}))?";
    private static final String TAG = "SubripParser";
    private static final Pattern SUBRIP_TIMING_LINE = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d{3}))?)\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d{3}))?)\\s*");
    private static final Pattern SUBRIP_TAG_PATTERN = Pattern.compile("\\{\\\\.*?\\}");
    private final StringBuilder textBuilder = new StringBuilder();
    private final ArrayList<String> tags = new ArrayList<>();
    private final ParsableByteArray parsableByteArray = new ParsableByteArray();

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 1;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public /* synthetic */ void parse(byte[] bArr, SubtitleParser.OutputOptions outputOptions, Consumer consumer) {
        parse(bArr, 0, bArr.length, outputOptions, consumer);
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public /* synthetic */ Subtitle parseToLegacySubtitle(byte[] bArr, int i, int i2) {
        return SubtitleParser.CC.$default$parseToLegacySubtitle(this, bArr, i, i2);
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public /* synthetic */ void reset() {
        SubtitleParser.CC.$default$reset(this);
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] bArr, int i, int i2, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        long j;
        StringBuilder sb;
        String str;
        this.parsableByteArray.reset(bArr, i + i2);
        this.parsableByteArray.setPosition(i);
        Charset charsetDetectUtfCharset = detectUtfCharset(this.parsableByteArray);
        long j2 = outputOptions.startTimeUs;
        long j3 = C.TIME_UNSET;
        ArrayList arrayList = (j2 == C.TIME_UNSET || !outputOptions.outputAllCues) ? null : new ArrayList();
        while (true) {
            String line = this.parsableByteArray.readLine(charsetDetectUtfCharset);
            if (line == null) {
                break;
            }
            if (!line.isEmpty()) {
                try {
                    Integer.parseInt(line);
                    String line2 = this.parsableByteArray.readLine(charsetDetectUtfCharset);
                    if (line2 == null) {
                        Log.w(TAG, "Unexpected end");
                        break;
                    }
                    Matcher matcher = SUBRIP_TIMING_LINE.matcher(line2);
                    if (matcher.matches()) {
                        long timecode = parseTimecode(matcher, 1);
                        long timecode2 = parseTimecode(matcher, 6);
                        int i3 = 0;
                        this.textBuilder.setLength(0);
                        this.tags.clear();
                        String line3 = this.parsableByteArray.readLine(charsetDetectUtfCharset);
                        while (true) {
                            boolean zIsEmpty = TextUtils.isEmpty(line3);
                            sb = this.textBuilder;
                            if (zIsEmpty) {
                                break;
                            }
                            if (sb.length() > 0) {
                                this.textBuilder.append("<br>");
                            }
                            this.textBuilder.append(processLine(line3, this.tags));
                            line3 = this.parsableByteArray.readLine(charsetDetectUtfCharset);
                        }
                        Spanned spannedFromHtml = Html.fromHtml(sb.toString());
                        while (true) {
                            if (i3 >= this.tags.size()) {
                                str = null;
                                break;
                            }
                            str = this.tags.get(i3);
                            if (str.matches(SUBRIP_ALIGNMENT_TAG)) {
                                break;
                            } else {
                                i3++;
                            }
                        }
                        j = j3;
                        if (outputOptions.startTimeUs == j || timecode2 >= outputOptions.startTimeUs) {
                            consumer.accept(new CuesWithTiming(ImmutableList.of(buildCue(spannedFromHtml, str)), timecode, timecode2 - timecode));
                        } else if (arrayList != null) {
                            arrayList.add(new CuesWithTiming(ImmutableList.of(buildCue(spannedFromHtml, str)), timecode, timecode2 - timecode));
                        }
                    } else {
                        j = j3;
                        Log.w(TAG, "Skipping invalid timing: " + line2);
                    }
                    j3 = j;
                } catch (NumberFormatException unused) {
                    j = j3;
                    Log.w(TAG, "Skipping invalid index: " + line);
                }
            }
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                consumer.accept((CuesWithTiming) it.next());
            }
        }
    }

    private Charset detectUtfCharset(ParsableByteArray parsableByteArray) {
        Charset utfCharsetFromBom = parsableByteArray.readUtfCharsetFromBom();
        return utfCharsetFromBom != null ? utfCharsetFromBom : StandardCharsets.UTF_8;
    }

    private String processLine(String str, ArrayList<String> arrayList) {
        String strTrim = str.trim();
        StringBuilder sb = new StringBuilder(strTrim);
        Matcher matcher = SUBRIP_TAG_PATTERN.matcher(strTrim);
        int i = 0;
        while (matcher.find()) {
            String strGroup = matcher.group();
            arrayList.add(strGroup);
            int iStart = matcher.start() - i;
            int length = strGroup.length();
            sb.replace(iStart, iStart + length, "");
            i += length;
        }
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:25:0x0062  */
    /* JADX WARN: Code duplicated, block: B:29:0x0071  */
    /* JADX WARN: Code duplicated, block: B:30:0x0075  */
    /* JADX WARN: Code duplicated, block: B:42:0x0094  */
    /* JADX WARN: Code duplicated, block: B:54:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:55:0x00bf  */
    private Cue buildCue(Spanned spanned, String str) {
        Cue.Builder text = new Cue.Builder().setText(spanned);
        if (str == null) {
            return text.build();
        }
        switch (str.hashCode()) {
            case -685620710:
                if (str.equals(ALIGN_BOTTOM_LEFT)) {
                    text.setPositionAnchor(0);
                } else {
                    text.setPositionAnchor(1);
                }
                break;
            case -685620679:
                str.equals(ALIGN_BOTTOM_MID);
                text.setPositionAnchor(1);
                break;
            case -685620648:
                if (str.equals(ALIGN_BOTTOM_RIGHT)) {
                    text.setPositionAnchor(2);
                } else {
                    text.setPositionAnchor(1);
                }
                break;
            case -685620617:
                if (str.equals(ALIGN_MID_LEFT)) {
                    text.setPositionAnchor(0);
                } else {
                    text.setPositionAnchor(1);
                }
                break;
            case -685620586:
                str.equals(ALIGN_MID_MID);
                text.setPositionAnchor(1);
                break;
            case -685620555:
                if (str.equals(ALIGN_MID_RIGHT)) {
                    text.setPositionAnchor(2);
                } else {
                    text.setPositionAnchor(1);
                }
                break;
            case -685620524:
                if (str.equals(ALIGN_TOP_LEFT)) {
                    text.setPositionAnchor(0);
                } else {
                    text.setPositionAnchor(1);
                }
                break;
            case -685620493:
                str.equals(ALIGN_TOP_MID);
                text.setPositionAnchor(1);
                break;
            case -685620462:
                if (str.equals(ALIGN_TOP_RIGHT)) {
                    text.setPositionAnchor(2);
                } else {
                    text.setPositionAnchor(1);
                }
                break;
            default:
                text.setPositionAnchor(1);
                break;
        }
        switch (str.hashCode()) {
            case -685620710:
                if (str.equals(ALIGN_BOTTOM_LEFT)) {
                    text.setLineAnchor(2);
                } else {
                    text.setLineAnchor(1);
                }
                break;
            case -685620679:
                if (str.equals(ALIGN_BOTTOM_MID)) {
                    text.setLineAnchor(2);
                } else {
                    text.setLineAnchor(1);
                }
                break;
            case -685620648:
                if (str.equals(ALIGN_BOTTOM_RIGHT)) {
                    text.setLineAnchor(2);
                } else {
                    text.setLineAnchor(1);
                }
                break;
            case -685620617:
                str.equals(ALIGN_MID_LEFT);
                text.setLineAnchor(1);
                break;
            case -685620586:
                str.equals(ALIGN_MID_MID);
                text.setLineAnchor(1);
                break;
            case -685620555:
                str.equals(ALIGN_MID_RIGHT);
                text.setLineAnchor(1);
                break;
            case -685620524:
                if (str.equals(ALIGN_TOP_LEFT)) {
                    text.setLineAnchor(0);
                } else {
                    text.setLineAnchor(1);
                }
                break;
            case -685620493:
                if (str.equals(ALIGN_TOP_MID)) {
                    text.setLineAnchor(0);
                } else {
                    text.setLineAnchor(1);
                }
                break;
            case -685620462:
                if (str.equals(ALIGN_TOP_RIGHT)) {
                    text.setLineAnchor(0);
                } else {
                    text.setLineAnchor(1);
                }
                break;
            default:
                text.setLineAnchor(1);
                break;
        }
        return text.setPosition(getFractionalPositionForAnchorType(text.getPositionAnchor())).setLine(getFractionalPositionForAnchorType(text.getLineAnchor()), 0).build();
    }

    private static long parseTimecode(Matcher matcher, int i) {
        String strGroup = matcher.group(i + 1);
        long j = (strGroup != null ? Long.parseLong(strGroup) * DurationKt.MILLIS_IN_HOUR : 0L) + (Long.parseLong((String) Preconditions.checkNotNull(matcher.group(i + 2))) * 60000) + (Long.parseLong((String) Preconditions.checkNotNull(matcher.group(i + 3))) * 1000);
        String strGroup2 = matcher.group(i + 4);
        if (strGroup2 != null) {
            j += Long.parseLong(strGroup2);
        }
        return j * 1000;
    }

    public static float getFractionalPositionForAnchorType(int i) {
        if (i == 0) {
            return 0.08f;
        }
        if (i == 1) {
            return 0.5f;
        }
        if (i == 2) {
            return END_FRACTION;
        }
        throw new IllegalArgumentException();
    }
}
