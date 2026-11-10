package com.google.android.exoplayer.text.webvtt;

import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class WebvttCueParser {
    private final StringBuilder textBuilder = new StringBuilder();
    public static final Pattern CUE_HEADER_PATTERN = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
    private static final Pattern COMMENT = Pattern.compile("^NOTE(( |\t).*)?$");
    private static final Pattern CUE_SETTING_PATTERN = Pattern.compile("(\\S+?):(\\S+)");

    boolean parseNextValidCue(ParsableByteArray parsableByteArray, WebvttCue.Builder builder) {
        Matcher matcherFindNextCueHeader;
        do {
            matcherFindNextCueHeader = findNextCueHeader(parsableByteArray);
            if (matcherFindNextCueHeader == null) {
                return false;
            }
        } while (!parseCue(matcherFindNextCueHeader, parsableByteArray, builder, this.textBuilder));
        return true;
    }

    static void parseCueSettingsList(String str, WebvttCue.Builder builder) {
        Matcher matcher = CUE_SETTING_PATTERN.matcher(str);
        while (matcher.find()) {
            String strGroup = matcher.group(1);
            String strGroup2 = matcher.group(2);
            try {
                if ("line".equals(strGroup)) {
                    parseLineAttribute(strGroup2, builder);
                } else if ("align".equals(strGroup)) {
                    builder.setTextAlignment(parseTextAlignment(strGroup2));
                } else if ("position".equals(strGroup)) {
                    parsePositionAttribute(strGroup2, builder);
                } else if ("size".equals(strGroup)) {
                    builder.setWidth(WebvttParserUtil.parsePercentage(strGroup2));
                } else {
                    Log.w("WebvttCueParser", "Unknown cue setting " + strGroup + ":" + strGroup2);
                }
            } catch (NumberFormatException unused) {
                Log.w("WebvttCueParser", "Skipping bad cue setting: " + matcher.group());
            }
        }
    }

    public static Matcher findNextCueHeader(ParsableByteArray parsableByteArray) {
        String line;
        while (true) {
            String line2 = parsableByteArray.readLine();
            if (line2 == null) {
                return null;
            }
            if (COMMENT.matcher(line2).matches()) {
                do {
                    line = parsableByteArray.readLine();
                    if (line == null) {
                        break;
                    }
                } while (!line.isEmpty());
            } else {
                Matcher matcher = CUE_HEADER_PATTERN.matcher(line2);
                if (matcher.matches()) {
                    return matcher;
                }
            }
        }
    }

    static void parseCueText(String str, WebvttCue.Builder builder) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Stack stack = new Stack();
        int i = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '&') {
                i++;
                int iIndexOf = str.indexOf(59, i);
                int iIndexOf2 = str.indexOf(32, i);
                if (iIndexOf == -1) {
                    iIndexOf = iIndexOf2;
                } else if (iIndexOf2 != -1) {
                    iIndexOf = Math.min(iIndexOf, iIndexOf2);
                }
                if (iIndexOf != -1) {
                    applyEntity(str.substring(i, iIndexOf), spannableStringBuilder);
                    if (iIndexOf == iIndexOf2) {
                        spannableStringBuilder.append((CharSequence) " ");
                    }
                    i = iIndexOf + 1;
                } else {
                    spannableStringBuilder.append(cCharAt);
                }
            } else if (cCharAt == '<') {
                int iFindEndOfTag = i + 1;
                if (iFindEndOfTag < str.length()) {
                    boolean z = str.charAt(iFindEndOfTag) == '/';
                    iFindEndOfTag = findEndOfTag(str, iFindEndOfTag);
                    int i2 = iFindEndOfTag - 2;
                    boolean z2 = str.charAt(i2) == '/';
                    int i3 = i + (z ? 2 : 1);
                    if (!z2) {
                        i2 = iFindEndOfTag - 1;
                    }
                    String[] strArr = tokenizeTag(str.substring(i3, i2));
                    if (strArr != null && isSupportedTag(strArr[0])) {
                        if (z) {
                            while (!stack.isEmpty()) {
                                StartTag startTag = (StartTag) stack.pop();
                                applySpansForTag(startTag, spannableStringBuilder);
                                if (startTag.name.equals(strArr[0])) {
                                    break;
                                }
                            }
                        } else if (!z2) {
                            stack.push(new StartTag(strArr[0], spannableStringBuilder.length()));
                        }
                    }
                }
                i = iFindEndOfTag;
            } else {
                spannableStringBuilder.append(cCharAt);
                i++;
            }
        }
        while (!stack.isEmpty()) {
            applySpansForTag((StartTag) stack.pop(), spannableStringBuilder);
        }
        builder.setText(spannableStringBuilder);
    }

    private static boolean parseCue(Matcher matcher, ParsableByteArray parsableByteArray, WebvttCue.Builder builder, StringBuilder sb) {
        try {
            builder.setStartTime(WebvttParserUtil.parseTimestampUs(matcher.group(1)));
            builder.setEndTime(WebvttParserUtil.parseTimestampUs(matcher.group(2)));
            parseCueSettingsList(matcher.group(3), builder);
            sb.setLength(0);
            while (true) {
                String line = parsableByteArray.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(line.trim());
            }
            parseCueText(sb.toString(), builder);
            return true;
        } catch (NumberFormatException unused) {
            Log.w("WebvttCueParser", "Skipping cue with bad header: " + matcher.group());
            return false;
        }
    }

    private static void parseLineAttribute(String str, WebvttCue.Builder builder) throws NumberFormatException {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf != -1) {
            builder.setLineAnchor(parsePositionAnchor(str.substring(iIndexOf + 1)));
            str = str.substring(0, iIndexOf);
        } else {
            builder.setLineAnchor(Integer.MIN_VALUE);
        }
        if (str.endsWith("%")) {
            builder.setLine(WebvttParserUtil.parsePercentage(str));
            builder.setLineType(0);
        } else {
            builder.setLine(Integer.parseInt(str));
            builder.setLineType(1);
        }
    }

    private static void parsePositionAttribute(String str, WebvttCue.Builder builder) throws NumberFormatException {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf != -1) {
            builder.setPositionAnchor(parsePositionAnchor(str.substring(iIndexOf + 1)));
            str = str.substring(0, iIndexOf);
        } else {
            builder.setPositionAnchor(Integer.MIN_VALUE);
        }
        builder.setPosition(WebvttParserUtil.parsePercentage(str));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x0034  */
    private static int parsePositionAnchor(String str) {
        byte b;
        switch (str) {
            case "center":
                b = 1;
                break;
            case "middle":
                b = 2;
                break;
            case "end":
                b = 3;
                break;
            case "start":
                b = 0;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0) {
            return 0;
        }
        if (b == 1 || b == 2) {
            return 1;
        }
        if (b == 3) {
            return 2;
        }
        Log.w("WebvttCueParser", "Invalid anchor value: " + str);
        return Integer.MIN_VALUE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:23:0x0049  */
    private static Layout.Alignment parseTextAlignment(String str) {
        byte b;
        switch (str) {
            case "center":
                b = 2;
                break;
            case "middle":
                b = 3;
                break;
            case "end":
                b = 4;
                break;
            case "left":
                b = 1;
                break;
            case "right":
                b = 5;
                break;
            case "start":
                b = 0;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0 || b == 1) {
            return Layout.Alignment.ALIGN_NORMAL;
        }
        if (b == 2 || b == 3) {
            return Layout.Alignment.ALIGN_CENTER;
        }
        if (b == 4 || b == 5) {
            return Layout.Alignment.ALIGN_OPPOSITE;
        }
        Log.w("WebvttCueParser", "Invalid alignment value: " + str);
        return null;
    }

    private static int findEndOfTag(String str, int i) {
        int iIndexOf = str.indexOf(62, i);
        return iIndexOf == -1 ? str.length() : iIndexOf + 1;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0042  */
    private static void applyEntity(String str, SpannableStringBuilder spannableStringBuilder) {
        byte b;
        int iHashCode = str.hashCode();
        if (iHashCode != 3309) {
            if (iHashCode != 3464) {
                if (iHashCode != 96708) {
                    if (iHashCode == 3374865 && str.equals("nbsp")) {
                        b = 2;
                    } else {
                        b = -1;
                    }
                } else if (str.equals("amp")) {
                    b = 3;
                } else {
                    b = -1;
                }
            } else if (str.equals("lt")) {
                b = 0;
            } else {
                b = -1;
            }
        } else if (str.equals("gt")) {
            b = 1;
        } else {
            b = -1;
        }
        if (b == 0) {
            spannableStringBuilder.append('<');
            return;
        }
        if (b == 1) {
            spannableStringBuilder.append('>');
            return;
        }
        if (b == 2) {
            spannableStringBuilder.append(' ');
            return;
        }
        if (b == 3) {
            spannableStringBuilder.append('&');
            return;
        }
        Log.w("WebvttCueParser", "ignoring unsupported entity: '&" + str + ";'");
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0060  */
    private static boolean isSupportedTag(String str) {
        byte b;
        int iHashCode = str.hashCode();
        if (iHashCode != 98) {
            if (iHashCode != 99) {
                if (iHashCode != 105) {
                    if (iHashCode != 3314158) {
                        if (iHashCode != 117) {
                            if (iHashCode == 118 && str.equals("v")) {
                                b = 5;
                            } else {
                                b = -1;
                            }
                        } else if (str.equals("u")) {
                            b = 4;
                        } else {
                            b = -1;
                        }
                    } else if (str.equals("lang")) {
                        b = 3;
                    } else {
                        b = -1;
                    }
                } else if (str.equals("i")) {
                    b = 2;
                } else {
                    b = -1;
                }
            } else if (str.equals("c")) {
                b = 1;
            } else {
                b = -1;
            }
        } else if (str.equals("b")) {
            b = 0;
        } else {
            b = -1;
        }
        return b == 0 || b == 1 || b == 2 || b == 3 || b == 4 || b == 5;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0033  */
    private static void applySpansForTag(StartTag startTag, SpannableStringBuilder spannableStringBuilder) {
        byte b;
        String str = startTag.name;
        int iHashCode = str.hashCode();
        if (iHashCode != 98) {
            if (iHashCode != 105) {
                if (iHashCode == 117 && str.equals("u")) {
                    b = 2;
                } else {
                    b = -1;
                }
            } else if (str.equals("i")) {
                b = 1;
            } else {
                b = -1;
            }
        } else if (str.equals("b")) {
            b = 0;
        } else {
            b = -1;
        }
        if (b == 0) {
            spannableStringBuilder.setSpan(new StyleSpan(1), startTag.position, spannableStringBuilder.length(), 33);
        } else if (b == 1) {
            spannableStringBuilder.setSpan(new StyleSpan(2), startTag.position, spannableStringBuilder.length(), 33);
        } else {
            if (b != 2) {
                return;
            }
            spannableStringBuilder.setSpan(new UnderlineSpan(), startTag.position, spannableStringBuilder.length(), 33);
        }
    }

    private static String[] tokenizeTag(String str) {
        String strTrim = str.replace("\\s+", " ").trim();
        if (strTrim.length() == 0) {
            return null;
        }
        if (strTrim.contains(" ")) {
            strTrim = strTrim.substring(0, strTrim.indexOf(" "));
        }
        return strTrim.split("\\.");
    }

    private static final class StartTag {
        public final String name;
        public final int position;

        public StartTag(String str, int i) {
            this.position = i;
            this.name = str;
        }
    }
}
