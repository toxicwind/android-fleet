package qrcode.color;

import androidx.core.view.ViewCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Colors.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0006\n\u0003\b\u0098\u0001\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J*\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u0005H\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0007J\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007J\u0018\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0007R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010k\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010o\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010p\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010s\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010v\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010w\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010y\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010z\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010{\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010|\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010}\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010~\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u007f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0080\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0081\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0082\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0083\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0084\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0085\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0086\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0087\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0088\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0089\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u008a\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u008b\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u008c\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u008d\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u008e\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u008f\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0090\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0091\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0092\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0093\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0094\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0095\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0096\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0097\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0098\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0099\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u009a\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u009b\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u009c\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u009d\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u009e\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u009f\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010 \u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010¡\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010¢\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010£\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010¤\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010¥\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010¦\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010§\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010¨\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010©\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010ª\u0001\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006«\u0001"}, d2 = {"Lqrcode/color/Colors;", "", "<init>", "()V", "css", "", "str", "", "rgba", "r", "g", "b", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "getRGBA", "", "color", "getRGBAPercentages", "", "maxValue", "", "withAlpha", "alpha", "TRANSPARENT", "ALICE_BLUE", "ANTIQUE_WHITE", "AQUA", "AQUAMARINE", "AZURE", "BEIGE", "BISQUE", "BLACK", "BLANCHED_ALMOND", "BLUE", "BLUE_VIOLET", "BROWN", "BURLY_WOOD", "CADET_BLUE", "CHARTREUSE", "CHOCOLATE", "CORAL", "CORNFLOWER_BLUE", "CORNSILK", "CRIMSON", "CYAN", "DARK_BLUE", "DARK_CYAN", "DARK_GOLDEN_ROD", "DARK_GRAY", "DARK_GREY", "DARK_GREEN", "DARK_KHAKI", "DARK_MAGENTA", "DARK_OLIVE_GREEN", "DARK_ORANGE", "DARK_ORCHID", "DARK_RED", "DARK_SALMON", "DARK_SEA_GREEN", "DARK_SLATE_BLUE", "DARK_SLATE_GRAY", "DARK_SLATE_GREY", "DARK_TURQUOISE", "DARK_VIOLET", "DEEP_PINK", "DEEP_SKY_BLUE", "DIM_GRAY", "DIM_GREY", "DODGER_BLUE", "FIRE_BRICK", "FLORAL_WHITE", "FOREST_GREEN", "FUCHSIA", "GAINSBORO", "GHOST_WHITE", "GOLD", "GOLDEN_ROD", "GRAY", "GREY", "GREEN", "GREEN_YELLOW", "HONEY_DEW", "HOT_PINK", "INDIAN_RED", "INDIGO", "IVORY", "KHAKI", "LAVENDER", "LAVENDER_BLUSH", "LAWN_GREEN", "LEMON_CHIFFON", "LIGHT_BLUE", "LIGHT_CORAL", "LIGHT_CYAN", "LIGHT_GOLDEN_ROD_YELLOW", "LIGHT_GRAY", "LIGHT_GREY", "LIGHT_GREEN", "LIGHT_PINK", "LIGHT_SALMON", "LIGHT_SEA_GREEN", "LIGHT_SKY_BLUE", "LIGHT_SLATE_GRAY", "LIGHT_SLATE_GREY", "LIGHT_STEEL_BLUE", "LIGHT_YELLOW", "LIME", "LIME_GREEN", "LINEN", "MAGENTA", "MAROON", "MEDIUM_AQUA_MARINE", "MEDIUM_BLUE", "MEDIUM_ORCHID", "MEDIUM_PURPLE", "MEDIUM_SEA_GREEN", "MEDIUM_SLATE_BLUE", "MEDIUM_SPRING_GREEN", "MEDIUM_TURQUOISE", "MEDIUM_VIOLET_RED", "MIDNIGHT_BLUE", "MINT_CREAM", "MISTY_ROSE", "MOCCASIN", "NAVAJO_WHITE", "NAVY", "OLD_LACE", "OLIVE", "OLIVE_DRAB", "ORANGE", "ORANGE_RED", "ORCHID", "PALE_GOLDEN_ROD", "PALE_GREEN", "PALE_TURQUOISE", "PALE_VIOLET_RED", "PAPAYA_WHIP", "PEACH_PUFF", "PERU", "PINK", "PLUM", "POWDER_BLUE", "PURPLE", "REBECCA_PURPLE", "RED", "ROSY_BROWN", "ROYAL_BLUE", "SADDLE_BROWN", "SALMON", "SANDY_BROWN", "SEA_GREEN", "SEA_SHELL", "SIENNA", "SILVER", "SKY_BLUE", "SLATE_BLUE", "SLATE_GRAY", "SLATE_GREY", "SNOW", "SPRING_GREEN", "STEEL_BLUE", "TAN", "TEAL", "THISTLE", "TOMATO", "TURQUOISE", "VIOLET", "WHEAT", "WHITE", "WHITE_SMOKE", "YELLOW", "YELLOW_GREEN", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Colors {
    public static final int ALICE_BLUE = -984833;
    public static final int ANTIQUE_WHITE = -332841;
    public static final int AQUA = -16711681;
    public static final int AQUAMARINE = -8388652;
    public static final int AZURE = -983041;
    public static final int BEIGE = -657956;
    public static final int BISQUE = -6972;
    public static final int BLACK = -16777216;
    public static final int BLANCHED_ALMOND = -5171;
    public static final int BLUE = -16776961;
    public static final int BLUE_VIOLET = -7722014;
    public static final int BROWN = -5952982;
    public static final int BURLY_WOOD = -2180985;
    public static final int CADET_BLUE = -10510688;
    public static final int CHARTREUSE = -8388864;
    public static final int CHOCOLATE = -2987746;
    public static final int CORAL = -32944;
    public static final int CORNFLOWER_BLUE = -10185235;
    public static final int CORNSILK = -1828;
    public static final int CRIMSON = -2354116;
    public static final int CYAN = -16711681;
    public static final int DARK_BLUE = -16777077;
    public static final int DARK_CYAN = -16741493;
    public static final int DARK_GOLDEN_ROD = -4684277;
    public static final int DARK_GRAY = -5658199;
    public static final int DARK_GREEN = -16751616;
    public static final int DARK_GREY = -5658199;
    public static final int DARK_KHAKI = -4343957;
    public static final int DARK_MAGENTA = -7667573;
    public static final int DARK_OLIVE_GREEN = -11179217;
    public static final int DARK_ORANGE = -29696;
    public static final int DARK_ORCHID = -6737204;
    public static final int DARK_RED = -7667712;
    public static final int DARK_SALMON = -1468806;
    public static final int DARK_SEA_GREEN = -7357297;
    public static final int DARK_SLATE_BLUE = -12042869;
    public static final int DARK_SLATE_GRAY = -13676721;
    public static final int DARK_SLATE_GREY = -13676721;
    public static final int DARK_TURQUOISE = -16724271;
    public static final int DARK_VIOLET = -7077677;
    public static final int DEEP_PINK = -60269;
    public static final int DEEP_SKY_BLUE = -16728065;
    public static final int DIM_GRAY = -9868951;
    public static final int DIM_GREY = -9868951;
    public static final int DODGER_BLUE = -14774017;
    public static final int FIRE_BRICK = -5103070;
    public static final int FLORAL_WHITE = -1296;
    public static final int FOREST_GREEN = -14513374;
    public static final int FUCHSIA = -65281;
    public static final int GAINSBORO = -2302756;
    public static final int GHOST_WHITE = -460545;
    public static final int GOLD = -10496;
    public static final int GOLDEN_ROD = -2448096;
    public static final int GRAY = -8355712;
    public static final int GREEN = -16744448;
    public static final int GREEN_YELLOW = -5374161;
    public static final int GREY = -8355712;
    public static final int HONEY_DEW = -983056;
    public static final int HOT_PINK = -38476;
    public static final int INDIAN_RED = -3318692;
    public static final int INDIGO = -11861886;
    public static final Colors INSTANCE = new Colors();
    public static final int IVORY = -16;
    public static final int KHAKI = -989556;
    public static final int LAVENDER = -1644806;
    public static final int LAVENDER_BLUSH = -3851;
    public static final int LAWN_GREEN = -8586240;
    public static final int LEMON_CHIFFON = -1331;
    public static final int LIGHT_BLUE = -5383962;
    public static final int LIGHT_CORAL = -1015680;
    public static final int LIGHT_CYAN = -2031617;
    public static final int LIGHT_GOLDEN_ROD_YELLOW = -329006;
    public static final int LIGHT_GRAY = -2894893;
    public static final int LIGHT_GREEN = -7278960;
    public static final int LIGHT_GREY = -2894893;
    public static final int LIGHT_PINK = -18751;
    public static final int LIGHT_SALMON = -24454;
    public static final int LIGHT_SEA_GREEN = -14634326;
    public static final int LIGHT_SKY_BLUE = -7876870;
    public static final int LIGHT_SLATE_GRAY = -8943463;
    public static final int LIGHT_SLATE_GREY = -8943463;
    public static final int LIGHT_STEEL_BLUE = -5192482;
    public static final int LIGHT_YELLOW = -32;
    public static final int LIME = -16711936;
    public static final int LIME_GREEN = -13447886;
    public static final int LINEN = -331546;
    public static final int MAGENTA = -65281;
    public static final int MAROON = -8388608;
    public static final int MEDIUM_AQUA_MARINE = -10039894;
    public static final int MEDIUM_BLUE = -16777011;
    public static final int MEDIUM_ORCHID = -4565549;
    public static final int MEDIUM_PURPLE = -7114533;
    public static final int MEDIUM_SEA_GREEN = -12799119;
    public static final int MEDIUM_SLATE_BLUE = -8689426;
    public static final int MEDIUM_SPRING_GREEN = -16713062;
    public static final int MEDIUM_TURQUOISE = -12004916;
    public static final int MEDIUM_VIOLET_RED = -3730043;
    public static final int MIDNIGHT_BLUE = -15132304;
    public static final int MINT_CREAM = -655366;
    public static final int MISTY_ROSE = -6943;
    public static final int MOCCASIN = -6987;
    public static final int NAVAJO_WHITE = -8531;
    public static final int NAVY = -16777088;
    public static final int OLD_LACE = -133658;
    public static final int OLIVE = -8355840;
    public static final int OLIVE_DRAB = -9728477;
    public static final int ORANGE = -23296;
    public static final int ORANGE_RED = -47872;
    public static final int ORCHID = -2461482;
    public static final int PALE_GOLDEN_ROD = -1120086;
    public static final int PALE_GREEN = -6751336;
    public static final int PALE_TURQUOISE = -5247250;
    public static final int PALE_VIOLET_RED = -2396013;
    public static final int PAPAYA_WHIP = -4139;
    public static final int PEACH_PUFF = -9543;
    public static final int PERU = -3308225;
    public static final int PINK = -16181;
    public static final int PLUM = -2252579;
    public static final int POWDER_BLUE = -5185306;
    public static final int PURPLE = -8388480;
    public static final int REBECCA_PURPLE = -10079335;
    public static final int RED = -65536;
    public static final int ROSY_BROWN = -4419697;
    public static final int ROYAL_BLUE = -12490271;
    public static final int SADDLE_BROWN = -7650029;
    public static final int SALMON = -360334;
    public static final int SANDY_BROWN = -744352;
    public static final int SEA_GREEN = -13726889;
    public static final int SEA_SHELL = -2578;
    public static final int SIENNA = -6270419;
    public static final int SILVER = -4144960;
    public static final int SKY_BLUE = -7876885;
    public static final int SLATE_BLUE = -9807155;
    public static final int SLATE_GRAY = -9404272;
    public static final int SLATE_GREY = -9404272;
    public static final int SNOW = -1286;
    public static final int SPRING_GREEN = -16711809;
    public static final int STEEL_BLUE = -12156236;
    public static final int TAN = -2968436;
    public static final int TEAL = -16744320;
    public static final int THISTLE = -2572328;
    public static final int TOMATO = -40121;
    public static final int TRANSPARENT = 0;
    public static final int TURQUOISE = -12525360;
    public static final int VIOLET = -1146130;
    public static final int WHEAT = -663885;
    public static final int WHITE = -1;
    public static final int WHITE_SMOKE = -657931;
    public static final int YELLOW = -256;
    public static final int YELLOW_GREEN = -6632142;

    private Colors() {
    }

    @JvmStatic
    public static final int css(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        return Integer.parseInt(StringsKt.substring(str, new IntRange(1, 6)), CharsKt.checkRadix(16)) | (-16777216);
    }

    public static /* synthetic */ int rgba$default(int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i4 = 255;
        }
        return rgba(i, i2, i3, i4);
    }

    @JvmStatic
    public static final int rgba(int r, int g, int b, int a) {
        return ((RangesKt.coerceIn(r, (ClosedRange<Integer>) new IntRange(0, 255)) & 255) << 16) | ((RangesKt.coerceIn(a, (ClosedRange<Integer>) new IntRange(0, 255)) & 255) << 24) | ((RangesKt.coerceIn(g, (ClosedRange<Integer>) new IntRange(0, 255)) & 255) << 8) | (RangesKt.coerceIn(b, (ClosedRange<Integer>) new IntRange(0, 255)) & 255);
    }

    @JvmStatic
    public static final int[] getRGBA(int color) {
        return new int[]{(color >> 16) & 255, (color >> 8) & 255, color & 255, (color >> 24) & 255};
    }

    public static /* synthetic */ double[] getRGBAPercentages$default(int i, double d, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            d = 255.0d;
        }
        return getRGBAPercentages(i, d);
    }

    @JvmStatic
    public static final double[] getRGBAPercentages(int color, double maxValue) {
        return new double[]{((double) ((color >> 16) & 255)) / maxValue, ((double) ((color >> 8) & 255)) / maxValue, ((double) (color & 255)) / maxValue, ((double) ((color >> 24) & 255)) / maxValue};
    }

    @JvmStatic
    public static final int withAlpha(int color, int alpha) {
        return (RangesKt.coerceIn(alpha, (ClosedRange<Integer>) new IntRange(0, 255)) << 24) + (color & ViewCompat.MEASURED_SIZE_MASK);
    }
}
