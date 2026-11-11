package com.lagradost.cloudstream3.ui.player;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
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
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.time.DurationKt;

/* JADX INFO: compiled from: CustomSubripParser.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J6\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0011\u001a\u00020\fH\u0002J(\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\t2\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002J\u001a\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\tH\u0002R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/CustomSubripParser;", "Landroidx/media3/extractor/text/SubtitleParser;", "<init>", "()V", "textBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "tags", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "parsableByteArray", "Landroidx/media3/common/util/ParsableByteArray;", "getCueReplacementBehavior", "", "parse", "", "data", "", TypedValues.CycleType.S_WAVE_OFFSET, "length", "outputOptions", "Landroidx/media3/extractor/text/SubtitleParser$OutputOptions;", "output", "Landroidx/media3/common/util/Consumer;", "Landroidx/media3/extractor/text/CuesWithTiming;", "detectUtfCharset", "Ljava/nio/charset/Charset;", "processLine", "line", "buildCue", "Landroidx/media3/common/text/Cue;", "text", "Landroid/text/Spanned;", "alignmentTag", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CustomSubripParser implements SubtitleParser {
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

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final float END_FRACTION = 0.92f;
    private static final float MID_FRACTION = 0.5f;
    private static final float START_FRACTION = 0.08f;
    private static final String SUBRIP_ALIGNMENT_TAG = "\\{\\\\an[1-9]\\}";
    private static final Pattern SUBRIP_TAG_PATTERN;
    private static final String SUBRIP_TIMECODE = "(?:(\\d+):)?(\\d+):(\\d+)(?:[,.](\\d+))?";
    private static final Pattern SUBRIP_TIMING_LINE;
    private static final String TAG = "SubripParser";
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
    public void parse(byte[] data, int offset, int length, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> output) {
        Charset charset;
        StringBuilder sb;
        long j;
        String str;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(outputOptions, "outputOptions");
        Intrinsics.checkNotNullParameter(output, "output");
        this.parsableByteArray.reset(data, offset + length);
        this.parsableByteArray.setPosition(offset);
        Charset charsetDetectUtfCharset = detectUtfCharset(this.parsableByteArray);
        long j2 = outputOptions.startTimeUs;
        long j3 = C.TIME_UNSET;
        ArrayList arrayList = (j2 == C.TIME_UNSET || !outputOptions.outputAllCues) ? null : new ArrayList();
        while (true) {
            String line = this.parsableByteArray.readLine(charsetDetectUtfCharset);
            if (line == null) {
                break;
            }
            Intrinsics.checkNotNull(line);
            if (line.length() != 0) {
                try {
                    Integer.parseInt(line);
                    String line2 = this.parsableByteArray.readLine(charsetDetectUtfCharset);
                    if (line2 == null) {
                        Log.w(TAG, "Unexpected end");
                        break;
                    }
                    Matcher matcher = SUBRIP_TIMING_LINE.matcher(line2);
                    if (matcher.matches()) {
                        Companion companion = INSTANCE;
                        Intrinsics.checkNotNull(matcher);
                        long timecode = companion.parseTimecode(matcher, 1);
                        long timecode2 = companion.parseTimecode(matcher, 6);
                        int i = 0;
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
                            StringBuilder sb2 = this.textBuilder;
                            Intrinsics.checkNotNull(line3);
                            sb2.append(processLine(line3, this.tags));
                            line3 = this.parsableByteArray.readLine(charsetDetectUtfCharset);
                        }
                        Spanned spannedFromHtml = Html.fromHtml(sb.toString());
                        int size = this.tags.size();
                        while (true) {
                            if (i >= size) {
                                charset = charsetDetectUtfCharset;
                                j = j3;
                                str = null;
                                break;
                            }
                            String str2 = this.tags.get(i);
                            j = j3;
                            Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
                            str = str2;
                            charset = charsetDetectUtfCharset;
                            if (new Regex(SUBRIP_ALIGNMENT_TAG).matches(str)) {
                                break;
                            }
                            i++;
                            j3 = j;
                            charsetDetectUtfCharset = charset;
                        }
                        if (outputOptions.startTimeUs == j || timecode2 >= outputOptions.startTimeUs) {
                            Intrinsics.checkNotNull(spannedFromHtml);
                            output.accept(new CuesWithTiming(ImmutableList.of(buildCue(spannedFromHtml, str)), timecode, timecode2 - timecode));
                        } else if (arrayList != null) {
                            Intrinsics.checkNotNull(spannedFromHtml);
                            arrayList.add(new CuesWithTiming(ImmutableList.of(buildCue(spannedFromHtml, str)), timecode, timecode2 - timecode));
                        }
                        j3 = j;
                    } else {
                        charset = charsetDetectUtfCharset;
                        Log.w(TAG, "Skipping invalid timing: " + line2);
                    }
                    charsetDetectUtfCharset = charset;
                } catch (NumberFormatException unused) {
                    charset = charsetDetectUtfCharset;
                    Log.w(TAG, "Skipping invalid index: " + line);
                }
            }
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                output.accept((CuesWithTiming) it.next());
            }
        }
    }

    private final Charset detectUtfCharset(ParsableByteArray data) {
        Charset utfCharsetFromBom = data.readUtfCharsetFromBom();
        if (utfCharsetFromBom != null) {
            return utfCharsetFromBom;
        }
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        return UTF_8;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:30:0x006a  */
    /* JADX WARN: Code duplicated, block: B:34:0x0075  */
    /* JADX WARN: Code duplicated, block: B:38:0x0080  */
    /* JADX WARN: Code duplicated, block: B:39:0x0084  */
    /* JADX WARN: Code duplicated, block: B:52:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:62:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:72:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:73:0x00da  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because "arg" is null
    	at jadx.core.dex.instructions.args.RegisterArg.sameCodeVar(RegisterArg.java:193)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.collectPart1RegionCases(SwitchOverStringVisitor.java:214)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:108)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:72)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:140)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:47)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:66)
     */
    private final Cue buildCue(Spanned text, String alignmentTag) {
        Cue.Builder text2 = new Cue.Builder().setText(text);
        Intrinsics.checkNotNullExpressionValue(text2, "setText(...)");
        if (alignmentTag == null) {
            Cue cueBuild = text2.build();
            Intrinsics.checkNotNullExpressionValue(cueBuild, "build(...)");
            return cueBuild;
        }
        switch (alignmentTag.hashCode()) {
            case -685620710:
                if (alignmentTag.equals(ALIGN_BOTTOM_LEFT)) {
                    text2.setPositionAnchor(0);
                } else {
                    text2.setPositionAnchor(1);
                }
                break;
            case -685620679:
                if (alignmentTag.equals(ALIGN_BOTTOM_MID)) {
                    text2.setPositionAnchor(1);
                } else {
                    text2.setPositionAnchor(1);
                }
                break;
            case -685620648:
                if (alignmentTag.equals(ALIGN_BOTTOM_RIGHT)) {
                    text2.setPositionAnchor(2);
                } else {
                    text2.setPositionAnchor(1);
                }
                break;
            case -685620617:
                if (alignmentTag.equals(ALIGN_MID_LEFT)) {
                    text2.setPositionAnchor(0);
                } else {
                    text2.setPositionAnchor(1);
                }
                break;
            case -685620586:
                if (alignmentTag.equals(ALIGN_MID_MID)) {
                    text2.setPositionAnchor(1);
                } else {
                    text2.setPositionAnchor(1);
                }
                break;
            case -685620555:
                if (alignmentTag.equals(ALIGN_MID_RIGHT)) {
                    text2.setPositionAnchor(2);
                } else {
                    text2.setPositionAnchor(1);
                }
                break;
            case -685620524:
                if (alignmentTag.equals(ALIGN_TOP_LEFT)) {
                    text2.setPositionAnchor(0);
                } else {
                    text2.setPositionAnchor(1);
                }
                break;
            case -685620493:
                if (alignmentTag.equals(ALIGN_TOP_MID)) {
                    text2.setPositionAnchor(1);
                } else {
                    text2.setPositionAnchor(1);
                }
                break;
            case -685620462:
                if (alignmentTag.equals(ALIGN_TOP_RIGHT)) {
                    text2.setPositionAnchor(2);
                } else {
                    text2.setPositionAnchor(1);
                }
                break;
            default:
                text2.setPositionAnchor(1);
                break;
        }
        switch (alignmentTag) {
            case "{\an1}":
            case "{\an2}":
            case "{\an3}":
                text2.setLineAnchor(2);
                break;
            case "{\an4}":
            case "{\an5}":
            case "{\an6}":
                text2.setLineAnchor(1);
                break;
            case "{\an7}":
            case "{\an8}":
            case "{\an9}":
                text2.setLineAnchor(0);
                break;
            default:
                text2.setLineAnchor(1);
                break;
        }
        Companion companion = INSTANCE;
        Cue cueBuild2 = text2.setPosition(companion.getFractionalPositionForAnchorType(text2.getPositionAnchor())).setLine(companion.getFractionalPositionForAnchorType(text2.getLineAnchor()), 0).build();
        Intrinsics.checkNotNullExpressionValue(cueBuild2, "build(...)");
        return cueBuild2;
    }

    /* JADX INFO: compiled from: CustomSubripParser.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0005H\u0002J\u0010\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/CustomSubripParser$Companion;", "", "<init>", "()V", "CUE_REPLACEMENT_BEHAVIOR", "", "START_FRACTION", "", "END_FRACTION", "MID_FRACTION", "TAG", "", "SUBRIP_TIMECODE", "SUBRIP_TIMING_LINE", "Ljava/util/regex/Pattern;", "SUBRIP_TAG_PATTERN", "SUBRIP_ALIGNMENT_TAG", "ALIGN_BOTTOM_LEFT", "ALIGN_BOTTOM_MID", "ALIGN_BOTTOM_RIGHT", "ALIGN_MID_LEFT", "ALIGN_MID_MID", "ALIGN_MID_RIGHT", "ALIGN_TOP_LEFT", "ALIGN_TOP_MID", "ALIGN_TOP_RIGHT", "parseTimecode", "", "matcher", "Ljava/util/regex/Matcher;", "groupOffset", "getFractionalPositionForAnchorType", "anchorType", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long parseTimecode(Matcher matcher, int groupOffset) {
            long j;
            long j2;
            String strGroup = matcher.group(groupOffset + 1);
            long j3 = 0;
            long j4 = strGroup != null ? Long.parseLong(strGroup) * DurationKt.MILLIS_IN_HOUR : 0L;
            Object objCheckNotNull = Preconditions.checkNotNull(matcher.group(groupOffset + 2));
            Intrinsics.checkNotNullExpressionValue(objCheckNotNull, "checkNotNull(...)");
            long j5 = j4 + (Long.parseLong((String) objCheckNotNull) * 60000);
            Object objCheckNotNull2 = Preconditions.checkNotNull(matcher.group(groupOffset + 3));
            Intrinsics.checkNotNullExpressionValue(objCheckNotNull2, "checkNotNull(...)");
            long j6 = j5 + (Long.parseLong((String) objCheckNotNull2) * 1000);
            String strGroup2 = matcher.group(groupOffset + 4);
            Integer numValueOf = strGroup2 != null ? Integer.valueOf(strGroup2.length()) : null;
            if (numValueOf != null) {
                if (numValueOf.intValue() == 1) {
                    j = Long.parseLong(strGroup2);
                    j2 = 100;
                } else if (numValueOf.intValue() == 2) {
                    j = Long.parseLong(strGroup2);
                    j2 = 10;
                } else if (numValueOf.intValue() == 3) {
                    j3 = Long.parseLong(strGroup2);
                } else {
                    String strSubstring = strGroup2.substring(0, 3);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    j3 = Long.parseLong(strSubstring);
                }
                j3 = j2 * j;
            }
            return (j6 + j3) * 1000;
        }

        public final float getFractionalPositionForAnchorType(int anchorType) {
            if (anchorType == Integer.MIN_VALUE) {
                throw new IllegalArgumentException();
            }
            if (anchorType == 0) {
                return 0.08f;
            }
            if (anchorType == 1) {
                return 0.5f;
            }
            if (anchorType == 2) {
                return CustomSubripParser.END_FRACTION;
            }
            throw new IllegalArgumentException();
        }
    }

    static {
        Pattern patternCompile = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:[,.](\\d+))?)\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:[,.](\\d+))?)\\s*");
        Intrinsics.checkNotNullExpressionValue(patternCompile, "compile(...)");
        SUBRIP_TIMING_LINE = patternCompile;
        Pattern patternCompile2 = Pattern.compile("\\{\\\\.*?\\}");
        Intrinsics.checkNotNullExpressionValue(patternCompile2, "compile(...)");
        SUBRIP_TAG_PATTERN = patternCompile2;
    }

    private final String processLine(String line, ArrayList<String> tags) {
        String str = line;
        int length = str.length() - 1;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (i2 <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i2 : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i2++;
            } else {
                z = true;
            }
        }
        String string = str.subSequence(i2, length + 1).toString();
        StringBuilder sb = new StringBuilder(string);
        Matcher matcher = SUBRIP_TAG_PATTERN.matcher(string);
        while (matcher.find()) {
            String strGroup = matcher.group();
            tags.add(strGroup);
            int iStart = matcher.start() - i;
            int length2 = strGroup.length();
            sb.replace(iStart, iStart + length2, "");
            i += length2;
        }
        String string2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        return string2;
    }
}
