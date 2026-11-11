package androidx.media3.common.util;

import android.graphics.Color;
import android.text.TextUtils;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.schabi.newpipe.extractor.stream.Stream;
import qrcode.color.Colors;

/* JADX INFO: loaded from: classes4.dex */
public final class ColorParser {
    private static final Map<String, Integer> COLOR_MAP;
    private static final String RGB = "rgb";
    private static final String RGBA = "rgba";
    private static final Pattern RGB_PATTERN = Pattern.compile("^rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
    private static final Pattern RGBA_PATTERN_INT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
    private static final Pattern RGBA_PATTERN_FLOAT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d*\\.?\\d*?)\\)$");

    static {
        HashMap map = new HashMap();
        COLOR_MAP = map;
        map.put("aliceblue", Integer.valueOf(Colors.ALICE_BLUE));
        map.put("antiquewhite", Integer.valueOf(Colors.ANTIQUE_WHITE));
        map.put("aqua", -16711681);
        map.put("aquamarine", Integer.valueOf(Colors.AQUAMARINE));
        map.put("azure", Integer.valueOf(Colors.AZURE));
        map.put("beige", Integer.valueOf(Colors.BEIGE));
        map.put("bisque", Integer.valueOf(Colors.BISQUE));
        map.put("black", -16777216);
        map.put("blanchedalmond", Integer.valueOf(Colors.BLANCHED_ALMOND));
        map.put("blue", Integer.valueOf(Colors.BLUE));
        map.put("blueviolet", Integer.valueOf(Colors.BLUE_VIOLET));
        map.put("brown", Integer.valueOf(Colors.BROWN));
        map.put("burlywood", Integer.valueOf(Colors.BURLY_WOOD));
        map.put("cadetblue", Integer.valueOf(Colors.CADET_BLUE));
        map.put("chartreuse", Integer.valueOf(Colors.CHARTREUSE));
        map.put("chocolate", Integer.valueOf(Colors.CHOCOLATE));
        map.put("coral", Integer.valueOf(Colors.CORAL));
        map.put("cornflowerblue", Integer.valueOf(Colors.CORNFLOWER_BLUE));
        map.put("cornsilk", Integer.valueOf(Colors.CORNSILK));
        map.put("crimson", Integer.valueOf(Colors.CRIMSON));
        map.put("cyan", -16711681);
        map.put("darkblue", Integer.valueOf(Colors.DARK_BLUE));
        map.put("darkcyan", Integer.valueOf(Colors.DARK_CYAN));
        map.put("darkgoldenrod", Integer.valueOf(Colors.DARK_GOLDEN_ROD));
        map.put("darkgray", -5658199);
        map.put("darkgreen", Integer.valueOf(Colors.DARK_GREEN));
        map.put("darkgrey", -5658199);
        map.put("darkkhaki", Integer.valueOf(Colors.DARK_KHAKI));
        map.put("darkmagenta", Integer.valueOf(Colors.DARK_MAGENTA));
        map.put("darkolivegreen", Integer.valueOf(Colors.DARK_OLIVE_GREEN));
        map.put("darkorange", Integer.valueOf(Colors.DARK_ORANGE));
        map.put("darkorchid", Integer.valueOf(Colors.DARK_ORCHID));
        map.put("darkred", Integer.valueOf(Colors.DARK_RED));
        map.put("darksalmon", Integer.valueOf(Colors.DARK_SALMON));
        map.put("darkseagreen", Integer.valueOf(Colors.DARK_SEA_GREEN));
        map.put("darkslateblue", Integer.valueOf(Colors.DARK_SLATE_BLUE));
        map.put("darkslategray", -13676721);
        map.put("darkslategrey", -13676721);
        map.put("darkturquoise", Integer.valueOf(Colors.DARK_TURQUOISE));
        map.put("darkviolet", Integer.valueOf(Colors.DARK_VIOLET));
        map.put("deeppink", Integer.valueOf(Colors.DEEP_PINK));
        map.put("deepskyblue", Integer.valueOf(Colors.DEEP_SKY_BLUE));
        map.put("dimgray", -9868951);
        map.put("dimgrey", -9868951);
        map.put("dodgerblue", Integer.valueOf(Colors.DODGER_BLUE));
        map.put("firebrick", Integer.valueOf(Colors.FIRE_BRICK));
        map.put("floralwhite", Integer.valueOf(Colors.FLORAL_WHITE));
        map.put("forestgreen", Integer.valueOf(Colors.FOREST_GREEN));
        map.put("fuchsia", -65281);
        map.put("gainsboro", Integer.valueOf(Colors.GAINSBORO));
        map.put("ghostwhite", Integer.valueOf(Colors.GHOST_WHITE));
        map.put("gold", Integer.valueOf(Colors.GOLD));
        map.put("goldenrod", Integer.valueOf(Colors.GOLDEN_ROD));
        map.put("gray", -8355712);
        map.put("green", Integer.valueOf(Colors.GREEN));
        map.put("greenyellow", Integer.valueOf(Colors.GREEN_YELLOW));
        map.put("grey", -8355712);
        map.put("honeydew", Integer.valueOf(Colors.HONEY_DEW));
        map.put("hotpink", Integer.valueOf(Colors.HOT_PINK));
        map.put("indianred", Integer.valueOf(Colors.INDIAN_RED));
        map.put("indigo", Integer.valueOf(Colors.INDIGO));
        map.put("ivory", -16);
        map.put("khaki", Integer.valueOf(Colors.KHAKI));
        map.put("lavender", Integer.valueOf(Colors.LAVENDER));
        map.put("lavenderblush", Integer.valueOf(Colors.LAVENDER_BLUSH));
        map.put("lawngreen", Integer.valueOf(Colors.LAWN_GREEN));
        map.put("lemonchiffon", Integer.valueOf(Colors.LEMON_CHIFFON));
        map.put("lightblue", Integer.valueOf(Colors.LIGHT_BLUE));
        map.put("lightcoral", Integer.valueOf(Colors.LIGHT_CORAL));
        map.put("lightcyan", Integer.valueOf(Colors.LIGHT_CYAN));
        map.put("lightgoldenrodyellow", Integer.valueOf(Colors.LIGHT_GOLDEN_ROD_YELLOW));
        map.put("lightgray", -2894893);
        map.put("lightgreen", Integer.valueOf(Colors.LIGHT_GREEN));
        map.put("lightgrey", -2894893);
        map.put("lightpink", Integer.valueOf(Colors.LIGHT_PINK));
        map.put("lightsalmon", Integer.valueOf(Colors.LIGHT_SALMON));
        map.put("lightseagreen", Integer.valueOf(Colors.LIGHT_SEA_GREEN));
        map.put("lightskyblue", Integer.valueOf(Colors.LIGHT_SKY_BLUE));
        map.put("lightslategray", -8943463);
        map.put("lightslategrey", -8943463);
        map.put("lightsteelblue", Integer.valueOf(Colors.LIGHT_STEEL_BLUE));
        map.put("lightyellow", -32);
        map.put("lime", Integer.valueOf(Colors.LIME));
        map.put("limegreen", Integer.valueOf(Colors.LIME_GREEN));
        map.put("linen", Integer.valueOf(Colors.LINEN));
        map.put("magenta", -65281);
        map.put("maroon", Integer.valueOf(Colors.MAROON));
        map.put("mediumaquamarine", Integer.valueOf(Colors.MEDIUM_AQUA_MARINE));
        map.put("mediumblue", Integer.valueOf(Colors.MEDIUM_BLUE));
        map.put("mediumorchid", Integer.valueOf(Colors.MEDIUM_ORCHID));
        map.put("mediumpurple", Integer.valueOf(Colors.MEDIUM_PURPLE));
        map.put("mediumseagreen", Integer.valueOf(Colors.MEDIUM_SEA_GREEN));
        map.put("mediumslateblue", Integer.valueOf(Colors.MEDIUM_SLATE_BLUE));
        map.put("mediumspringgreen", Integer.valueOf(Colors.MEDIUM_SPRING_GREEN));
        map.put("mediumturquoise", Integer.valueOf(Colors.MEDIUM_TURQUOISE));
        map.put("mediumvioletred", Integer.valueOf(Colors.MEDIUM_VIOLET_RED));
        map.put("midnightblue", Integer.valueOf(Colors.MIDNIGHT_BLUE));
        map.put("mintcream", Integer.valueOf(Colors.MINT_CREAM));
        map.put("mistyrose", Integer.valueOf(Colors.MISTY_ROSE));
        map.put("moccasin", Integer.valueOf(Colors.MOCCASIN));
        map.put("navajowhite", Integer.valueOf(Colors.NAVAJO_WHITE));
        map.put("navy", Integer.valueOf(Colors.NAVY));
        map.put("oldlace", Integer.valueOf(Colors.OLD_LACE));
        map.put("olive", Integer.valueOf(Colors.OLIVE));
        map.put("olivedrab", Integer.valueOf(Colors.OLIVE_DRAB));
        map.put("orange", Integer.valueOf(Colors.ORANGE));
        map.put("orangered", Integer.valueOf(Colors.ORANGE_RED));
        map.put("orchid", Integer.valueOf(Colors.ORCHID));
        map.put("palegoldenrod", Integer.valueOf(Colors.PALE_GOLDEN_ROD));
        map.put("palegreen", Integer.valueOf(Colors.PALE_GREEN));
        map.put("paleturquoise", Integer.valueOf(Colors.PALE_TURQUOISE));
        map.put("palevioletred", Integer.valueOf(Colors.PALE_VIOLET_RED));
        map.put("papayawhip", Integer.valueOf(Colors.PAPAYA_WHIP));
        map.put("peachpuff", Integer.valueOf(Colors.PEACH_PUFF));
        map.put("peru", Integer.valueOf(Colors.PERU));
        map.put("pink", Integer.valueOf(Colors.PINK));
        map.put("plum", Integer.valueOf(Colors.PLUM));
        map.put("powderblue", Integer.valueOf(Colors.POWDER_BLUE));
        map.put("purple", Integer.valueOf(Colors.PURPLE));
        map.put("rebeccapurple", Integer.valueOf(Colors.REBECCA_PURPLE));
        map.put("red", -65536);
        map.put("rosybrown", Integer.valueOf(Colors.ROSY_BROWN));
        map.put("royalblue", Integer.valueOf(Colors.ROYAL_BLUE));
        map.put("saddlebrown", Integer.valueOf(Colors.SADDLE_BROWN));
        map.put("salmon", Integer.valueOf(Colors.SALMON));
        map.put("sandybrown", Integer.valueOf(Colors.SANDY_BROWN));
        map.put("seagreen", Integer.valueOf(Colors.SEA_GREEN));
        map.put("seashell", Integer.valueOf(Colors.SEA_SHELL));
        map.put("sienna", Integer.valueOf(Colors.SIENNA));
        map.put("silver", Integer.valueOf(Colors.SILVER));
        map.put("skyblue", Integer.valueOf(Colors.SKY_BLUE));
        map.put("slateblue", Integer.valueOf(Colors.SLATE_BLUE));
        map.put("slategray", -9404272);
        map.put("slategrey", -9404272);
        map.put("snow", Integer.valueOf(Colors.SNOW));
        map.put("springgreen", Integer.valueOf(Colors.SPRING_GREEN));
        map.put("steelblue", Integer.valueOf(Colors.STEEL_BLUE));
        map.put("tan", Integer.valueOf(Colors.TAN));
        map.put("teal", Integer.valueOf(Colors.TEAL));
        map.put("thistle", Integer.valueOf(Colors.THISTLE));
        map.put("tomato", Integer.valueOf(Colors.TOMATO));
        map.put("transparent", 0);
        map.put("turquoise", Integer.valueOf(Colors.TURQUOISE));
        map.put("violet", Integer.valueOf(Colors.VIOLET));
        map.put("wheat", Integer.valueOf(Colors.WHEAT));
        map.put("white", -1);
        map.put("whitesmoke", Integer.valueOf(Colors.WHITE_SMOKE));
        map.put("yellow", -256);
        map.put("yellowgreen", Integer.valueOf(Colors.YELLOW_GREEN));
    }

    public static int parseTtmlColor(String str) {
        return parseColorInternal(str, false);
    }

    public static int parseCssColor(String str) {
        return parseColorInternal(str, true);
    }

    private static int parseColorInternal(String str, boolean z) {
        int i;
        Preconditions.checkArgument(!TextUtils.isEmpty(str));
        String strReplace = str.replace(Stream.ID_UNKNOWN, "");
        if (strReplace.charAt(0) == '#') {
            int i2 = (int) Long.parseLong(strReplace.substring(1), 16);
            if (strReplace.length() == 7) {
                return (-16777216) | i2;
            }
            if (strReplace.length() == 9) {
                return ((i2 & 255) << 24) | (i2 >>> 8);
            }
            throw new IllegalArgumentException();
        }
        if (strReplace.startsWith(RGBA)) {
            Matcher matcher = (z ? RGBA_PATTERN_FLOAT_ALPHA : RGBA_PATTERN_INT_ALPHA).matcher(strReplace);
            if (matcher.matches()) {
                if (z) {
                    i = (int) (Float.parseFloat((String) Preconditions.checkNotNull(matcher.group(4))) * 255.0f);
                } else {
                    i = Integer.parseInt((String) Preconditions.checkNotNull(matcher.group(4)), 10);
                }
                return Color.argb(i, Integer.parseInt((String) Preconditions.checkNotNull(matcher.group(1)), 10), Integer.parseInt((String) Preconditions.checkNotNull(matcher.group(2)), 10), Integer.parseInt((String) Preconditions.checkNotNull(matcher.group(3)), 10));
            }
        } else if (strReplace.startsWith(RGB)) {
            Matcher matcher2 = RGB_PATTERN.matcher(strReplace);
            if (matcher2.matches()) {
                return Color.rgb(Integer.parseInt((String) Preconditions.checkNotNull(matcher2.group(1)), 10), Integer.parseInt((String) Preconditions.checkNotNull(matcher2.group(2)), 10), Integer.parseInt((String) Preconditions.checkNotNull(matcher2.group(3)), 10));
            }
        } else {
            Integer num = COLOR_MAP.get(Ascii.toLowerCase(strReplace));
            if (num != null) {
                return num.intValue();
            }
        }
        throw new IllegalArgumentException();
    }

    private ColorParser() {
    }
}
