package com.lagradost.cloudstream3.ui.player;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.common.Format;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.dvb.DvbParser;
import androidx.media3.extractor.text.pgs.PgsParser;
import androidx.media3.extractor.text.ssa.SsaParser;
import androidx.media3.extractor.text.ttml.TtmlParser;
import androidx.media3.extractor.text.tx3g.Tx3gParser;
import androidx.media3.extractor.text.webvtt.Mp4WebvttParser;
import androidx.media3.extractor.text.webvtt.WebvttParser;
import androidx.preference.PreferenceManager;
import com.google.common.collect.ImmutableList;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.subtitles.SaveCaptionStyle;
import com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment;
import io.ktor.http.LinkHeader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import org.mozilla.universalchardet.UniversalDetector;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: CustomSubtitleDecoderFactory.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000e\u001a\u00020\tH\u0002J6\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0016J\b\u0010\u001e\u001a\u00020\u0017H\u0016J\b\u0010\u001f\u001a\u00020\u0015H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/CustomDecoder;", "Landroidx/media3/extractor/text/SubtitleParser;", "fallbackFormat", "Landroidx/media3/common/Format;", "<init>", "(Landroidx/media3/common/Format;)V", "realDecoder", "getStr", "Lkotlin/Pair;", "", "Ljava/nio/charset/Charset;", "byteArray", "", "getSubtitleParser", "data", "currentSubtitleCues", "", "Lcom/lagradost/cloudstream3/ui/player/SubtitleCue;", "getCurrentSubtitleCues", "()Ljava/util/List;", "parse", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "length", "outputOptions", "Landroidx/media3/extractor/text/SubtitleParser$OutputOptions;", "output", "Landroidx/media3/common/util/Consumer;", "Landroidx/media3/extractor/text/CuesWithTiming;", "getCueReplacementBehavior", "reset", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CustomDecoder implements SubtitleParser {
    private static final float DEFAULT_MARGIN = 0.05f;
    public static final int SSA_ALIGNMENT_BOTTOM_CENTER = 2;
    public static final int SSA_ALIGNMENT_BOTTOM_LEFT = 1;
    public static final int SSA_ALIGNMENT_BOTTOM_RIGHT = 3;
    public static final int SSA_ALIGNMENT_MIDDLE_CENTER = 5;
    public static final int SSA_ALIGNMENT_MIDDLE_LEFT = 4;
    public static final int SSA_ALIGNMENT_MIDDLE_RIGHT = 6;
    public static final int SSA_ALIGNMENT_TOP_CENTER = 8;
    public static final int SSA_ALIGNMENT_TOP_LEFT = 7;
    public static final int SSA_ALIGNMENT_TOP_RIGHT = 9;
    private static final String TAG = "CustomDecoder";
    private static final String UTF_8 = "UTF-8";
    private static String overrideEncoding;
    private static long subtitleOffset;
    private final List<SubtitleCue> currentSubtitleCues = new ArrayList();
    private final Format fallbackFormat;
    private SubtitleParser realDecoder;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Regex locationRegex = new Regex("\\{\\\\an(\\d+)\\}", RegexOption.IGNORE_CASE);
    private static final List<Regex> bloatRegex = CollectionsKt.listOf((Object[]) new Regex[]{new Regex("Support\\s+us\\s+and\\s+become\\s+VIP\\s+member\\s+to\\s+remove\\s+all\\s+ads\\s+from\\s+(www\\.|)OpenSubtitles(\\.org|)", RegexOption.IGNORE_CASE), new Regex("Please\\s+rate\\s+this\\s+subtitle\\s+at\\s+.*\\s+Help\\s+other\\s+users\\s+to\\s+choose\\s+the\\s+best\\s+subtitles", RegexOption.IGNORE_CASE), new Regex("Contact\\s(www\\.|)OpenSubtitles(\\.org|)\\s+today", RegexOption.IGNORE_CASE), new Regex("Advertise\\s+your\\s+product\\s+or\\s+brand\\s+here", RegexOption.IGNORE_CASE)});

    @Override // androidx.media3.extractor.text.SubtitleParser
    public /* synthetic */ void parse(byte[] bArr, SubtitleParser.OutputOptions outputOptions, Consumer consumer) {
        parse(bArr, 0, bArr.length, outputOptions, consumer);
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public /* synthetic */ Subtitle parseToLegacySubtitle(byte[] bArr, int i, int i2) {
        return SubtitleParser.CC.$default$parseToLegacySubtitle(this, bArr, i, i2);
    }

    public CustomDecoder(Format format) {
        this.fallbackFormat = format;
    }

    /* JADX INFO: compiled from: CustomSubtitleDecoderFactory.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010(\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\u001bJ\u0010\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\u000bH\u0002J\n\u0010,\u001a\u00020-*\u00020-J\u0019\u0010.\u001a\u00020-*\u00020-2\b\u0010/\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u00100R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001e\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020#0%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u00061"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/CustomDecoder$Companion;", "", "<init>", "()V", "updateForcedEncoding", "", "context", "Landroid/content/Context;", "DEFAULT_MARGIN", "", "SSA_ALIGNMENT_BOTTOM_LEFT", "", "SSA_ALIGNMENT_BOTTOM_CENTER", "SSA_ALIGNMENT_BOTTOM_RIGHT", "SSA_ALIGNMENT_MIDDLE_LEFT", "SSA_ALIGNMENT_MIDDLE_CENTER", "SSA_ALIGNMENT_MIDDLE_RIGHT", "SSA_ALIGNMENT_TOP_LEFT", "SSA_ALIGNMENT_TOP_CENTER", "SSA_ALIGNMENT_TOP_RIGHT", "subtitleOffset", "", "getSubtitleOffset", "()J", "setSubtitleOffset", "(J)V", "UTF_8", "", "TAG", "overrideEncoding", "style", "Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle;", "getStyle", "()Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle;", "locationRegex", "Lkotlin/text/Regex;", "bloatRegex", "", "getBloatRegex", "()Ljava/util/List;", "trimStr", TypedValues.Custom.S_STRING, "computeDefaultLineOrPosition", LinkHeader.Parameters.Anchor, "fixSubtitleAlignment", "Landroidx/media3/common/text/Cue$Builder;", "setSubtitleAlignment", "alignment", "(Landroidx/media3/common/text/Cue$Builder;Ljava/lang/Integer;)Landroidx/media3/common/text/Cue$Builder;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final float computeDefaultLineOrPosition(int anchor) {
            if (anchor == Integer.MIN_VALUE) {
                return -3.4028235E38f;
            }
            if (anchor == 0) {
                return 0.05f;
            }
            if (anchor != 1) {
                return anchor != 2 ? -3.4028235E38f : 0.95f;
            }
            return 0.5f;
        }

        private Companion() {
        }

        public final void updateForcedEncoding(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String str = null;
            String string = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.subtitles_encoding_key), null);
            String str2 = string;
            if (str2 != null && !StringsKt.isBlank(str2)) {
                str = string;
            }
            CustomDecoder.overrideEncoding = str;
        }

        public final long getSubtitleOffset() {
            return CustomDecoder.subtitleOffset;
        }

        public final void setSubtitleOffset(long j) {
            CustomDecoder.subtitleOffset = j;
        }

        public final SaveCaptionStyle getStyle() {
            return SubtitlesFragment.INSTANCE.getCurrentSavedStyle();
        }

        public final List<Regex> getBloatRegex() {
            return CustomDecoder.bloatRegex;
        }

        public final String trimStr(String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            return new Regex("[ \u2000\u2001\u2002\u2003\u2004\u2005\u2006 \u2008\u2009\u200a\u205f]").replace(StringsKt.trim(StringsKt.trimStart((CharSequence) string).toString(), 65279, 8203), Stream.ID_UNKNOWN);
        }

        public final Cue.Builder fixSubtitleAlignment(Cue.Builder builder) {
            CharSequence charSequenceTrim;
            List<String> groupValues;
            String str;
            Integer intOrNull;
            Intrinsics.checkNotNullParameter(builder, "<this>");
            CharSequence text = builder.getText();
            if (text != null && (charSequenceTrim = StringsKt.trim(text)) != null) {
                MatchResult matchResultFind$default = Regex.find$default(CustomDecoder.locationRegex, charSequenceTrim, 0, 2, null);
                if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str = groupValues.get(1)) != null && (intOrNull = StringsKt.toIntOrNull(str)) != null) {
                    CustomDecoder.INSTANCE.setSubtitleAlignment(builder, Integer.valueOf(intOrNull.intValue()));
                }
                builder.setText(CustomDecoder.locationRegex.replace(charSequenceTrim, ""));
            }
            return builder;
        }

        public final Cue.Builder setSubtitleAlignment(Cue.Builder builder, Integer num) {
            Integer num2;
            Integer num3;
            Intrinsics.checkNotNullParameter(builder, "<this>");
            if (num != null) {
                Layout.Alignment alignment = null;
                switch (num.intValue()) {
                    case 1:
                    case 2:
                    case 3:
                        num2 = 2;
                        break;
                    case 4:
                    case 5:
                    case 6:
                        num2 = 1;
                        break;
                    case 7:
                    case 8:
                    case 9:
                        num2 = 0;
                        break;
                    default:
                        num2 = null;
                        break;
                }
                if (num2 != null) {
                    int iIntValue = num2.intValue();
                    builder.setLineAnchor(iIntValue);
                    builder.setLine(CustomDecoder.INSTANCE.computeDefaultLineOrPosition(iIntValue), 0);
                }
                switch (num.intValue()) {
                    case 1:
                    case 4:
                    case 7:
                        num3 = 0;
                        break;
                    case 2:
                    case 5:
                    case 8:
                        num3 = 1;
                        break;
                    case 3:
                    case 6:
                    case 9:
                        num3 = 2;
                        break;
                    default:
                        num3 = null;
                        break;
                }
                if (num3 != null) {
                    int iIntValue2 = num3.intValue();
                    builder.setPositionAnchor(iIntValue2);
                    builder.setPosition(CustomDecoder.INSTANCE.computeDefaultLineOrPosition(iIntValue2));
                }
                switch (num.intValue()) {
                    case 1:
                    case 4:
                    case 7:
                        alignment = Layout.Alignment.ALIGN_NORMAL;
                        break;
                    case 2:
                    case 5:
                    case 8:
                        alignment = Layout.Alignment.ALIGN_CENTER;
                        break;
                    case 3:
                    case 6:
                    case 9:
                        alignment = Layout.Alignment.ALIGN_OPPOSITE;
                        break;
                }
                if (alignment != null) {
                    builder.setTextAlignment(alignment);
                }
            }
            return builder;
        }
    }

    private final Pair<String, Charset> getStr(byte[] byteArray) {
        String detectedCharset;
        try {
            detectedCharset = overrideEncoding;
            if (detectedCharset == null) {
                CustomDecoder customDecoder = this;
                UniversalDetector universalDetector = new UniversalDetector();
                universalDetector.handleData(byteArray, 0, byteArray.length);
                universalDetector.dataEnd();
                detectedCharset = universalDetector.getDetectedCharset();
            }
            Log.i(TAG, "Detected encoding with charset " + detectedCharset + " and override = " + overrideEncoding);
            if (detectedCharset == null) {
                detectedCharset = UTF_8;
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to detect encoding throwing error");
            ArchComponentExtKt.logError(e);
        }
        try {
            Charset charsetForName = Charset.forName(detectedCharset);
            Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(...)");
            return new Pair<>(new String(byteArray, charsetForName), charsetForName);
        } catch (Exception e2) {
            Log.e(TAG, "Failed to parse using encoding " + detectedCharset);
            ArchComponentExtKt.logError(e2);
            String strDecodeToString = StringsKt.decodeToString(byteArray);
            Charset charsetForName2 = Charset.forName(UTF_8);
            Intrinsics.checkNotNullExpressionValue(charsetForName2, "forName(...)");
            return new Pair<>(strDecodeToString, charsetForName2);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final SubtitleParser getSubtitleParser(String data) {
        String strSubSequence;
        String str;
        Regex regex = new Regex("[\\p{Cntrl}\\p{Cf}]");
        String str2 = data;
        int length = str2.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            char cCharAt = str2.charAt(i);
            if (!CharsKt.isWhitespace(cCharAt) && !regex.matches(String.valueOf(cCharAt))) {
                strSubSequence = str2.subSequence(i, str2.length());
                break;
            }
            i++;
        }
        String string = strSubSequence.toString();
        String strSubstring = string.substring(0, 10);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        if (StringsKt.contains((CharSequence) strSubstring, (CharSequence) "WEBVTT", true)) {
            return new WebvttParser();
        }
        if (StringsKt.startsWith(string, "<?xml version=\"", true)) {
            return new TtmlParser();
        }
        if (StringsKt.startsWith(string, "[Script Info]", true) || StringsKt.startsWith(string, "Title:", true)) {
            Format format = this.fallbackFormat;
            return new SsaParser(format != null ? format.initializationData : null);
        }
        if (StringsKt.startsWith(string, "1", true)) {
            return new CustomSubripParser();
        }
        Format format2 = this.fallbackFormat;
        if (format2 != null && (str = format2.sampleMimeType) != null) {
            switch (str.hashCode()) {
                case -1351681404:
                    if (str.equals("application/dvbsubs")) {
                        return new DvbParser(this.fallbackFormat.initializationData);
                    }
                    break;
                case -1248334819:
                    if (str.equals("application/pgs")) {
                        return new PgsParser();
                    }
                    break;
                case -1026075066:
                    if (str.equals("application/x-mp4-vtt")) {
                        return new Mp4WebvttParser();
                    }
                    break;
                case -1004728940:
                    if (str.equals("text/vtt")) {
                        return new WebvttParser();
                    }
                    break;
                case 691401887:
                    if (str.equals("application/x-quicktime-tx3g")) {
                        return new Tx3gParser(this.fallbackFormat.initializationData);
                    }
                    break;
                case 822864842:
                    if (str.equals("text/x-ssa")) {
                        return new SsaParser(this.fallbackFormat.initializationData);
                    }
                    break;
                case 1668750253:
                    if (str.equals("application/x-subrip")) {
                        return new CustomSubripParser();
                    }
                    break;
                case 1693976202:
                    if (str.equals("application/ttml+xml")) {
                        return new TtmlParser();
                    }
                    break;
            }
        }
        return null;
    }

    public final List<SubtitleCue> getCurrentSubtitleCues() {
        return this.currentSubtitleCues;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] data, int offset, int length, SubtitleParser.OutputOptions outputOptions, final Consumer<CuesWithTiming> output) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(outputOptions, "outputOptions");
        Intrinsics.checkNotNullParameter(output, "output");
        Companion companion = INSTANCE;
        SaveCaptionStyle style = companion.getStyle();
        Consumer<CuesWithTiming> consumer = new Consumer() { // from class: com.lagradost.cloudstream3.ui.player.CustomDecoder$$ExternalSyntheticLambda0
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                CustomDecoder.parse$lambda$0(this.f$0, output, (CuesWithTiming) obj);
            }
        };
        Log.i(TAG, "Parse subtitle, current parser: " + this.realDecoder);
        try {
            String first = getStr(data).getFirst();
            StringBuilder sb = new StringBuilder("Subtitle preview: ");
            String strSubstring = first.substring(0, 30);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            sb.append(strSubstring);
            Log.i(TAG, sb.toString());
            if (StringsKt.isBlank(first)) {
                return;
            }
            String strTrimStr = companion.trimStr(first);
            SubtitleParser subtitleParser = this.realDecoder;
            if (subtitleParser == null) {
                subtitleParser = getSubtitleParser(first);
            }
            this.realDecoder = subtitleParser;
            Log.i(TAG, "Parser selected: " + this.realDecoder);
            SubtitleParser subtitleParser2 = this.realDecoder;
            if (subtitleParser2 != null && !(subtitleParser2 instanceof SsaParser)) {
                if (style.getRemoveBloat()) {
                    Iterator<T> it = bloatRegex.iterator();
                    while (it.hasNext()) {
                        strTrimStr = ((Regex) it.next()).replace(strTrimStr, "\n");
                    }
                }
                if (style.getUpperCase()) {
                    String upperCase = strTrimStr.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                    strTrimStr = upperCase;
                }
            }
            byte[] bytes = strTrimStr.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            SubtitleParser subtitleParser3 = this.realDecoder;
            if (subtitleParser3 != null) {
                subtitleParser3.parse(bytes, Math.min(bytes.length, offset), Math.min(bytes.length, length), outputOptions, consumer);
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    static final void parse$lambda$0(CustomDecoder customDecoder, Consumer consumer, CuesWithTiming cue) {
        Intrinsics.checkNotNullParameter(cue, "cue");
        CuesWithTiming cuesWithTiming = new CuesWithTiming(cue.cues, cue.startTimeUs, cue.durationUs);
        List<SubtitleCue> list = customDecoder.currentSubtitleCues;
        long j = cuesWithTiming.startTimeUs / 1000;
        long j2 = cuesWithTiming.durationUs / 1000;
        ImmutableList<Cue> cues = cuesWithTiming.cues;
        Intrinsics.checkNotNullExpressionValue(cues, "cues");
        ImmutableList<Cue> immutableList = cues;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(immutableList, 10));
        Iterator<Cue> it = immutableList.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(it.next().text));
        }
        list.add(new SubtitleCue(j, j2, arrayList));
        consumer.accept(new CuesWithTiming(cuesWithTiming.cues, cuesWithTiming.startTimeUs - (subtitleOffset * 1000), cuesWithTiming.durationUs));
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        SubtitleParser subtitleParser = this.realDecoder;
        if (subtitleParser != null) {
            return subtitleParser.getCueReplacementBehavior();
        }
        return 2;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void reset() {
        this.currentSubtitleCues.clear();
        SubtitleParser.CC.$default$reset(this);
    }
}
