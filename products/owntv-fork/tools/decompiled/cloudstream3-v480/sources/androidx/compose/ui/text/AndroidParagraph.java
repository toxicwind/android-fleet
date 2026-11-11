package androidx.compose.ui.text;

import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.android.selection.WordBoundary_androidKt;
import androidx.compose.ui.text.android.selection.WordIterator;
import androidx.compose.ui.text.android.style.PlaceholderSpan;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.compose.ui.text.platform.AndroidParagraphHelper_androidKt;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import androidx.compose.ui.text.platform.style.ShaderBrushSpan;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: AndroidParagraph.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0098\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bBk\b\u0016\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0014\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u00120\u0011\u0012\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120\u0011\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\b\n\u0010\u001aJ\u0010\u0010N\u001a\u00020\u00052\u0006\u0010O\u001a\u00020-H\u0016J\u0017\u0010P\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020RH\u0016¢\u0006\u0004\bS\u0010TJ'\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020F2\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020[H\u0016¢\u0006\u0004\b\\\u0010]J\u0010\u0010^\u001a\u00020F2\u0006\u0010_\u001a\u00020\u0005H\u0016J)\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020V2\u0006\u0010c\u001a\u00020d2\b\b\u0001\u0010e\u001a\u00020\u0005H\u0016¢\u0006\u0004\bf\u0010gJ\u0018\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020\u00052\u0006\u0010k\u001a\u00020\u0005H\u0016J\u0010\u0010l\u001a\u00020F2\u0006\u0010_\u001a\u00020\u0005H\u0016J\u0017\u0010m\u001a\u00020V2\u0006\u0010_\u001a\u00020\u0005H\u0016¢\u0006\u0004\bn\u0010oJ\u0010\u0010p\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0010\u0010r\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0010\u0010s\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0015\u0010t\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0000¢\u0006\u0002\buJ\u0010\u0010v\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0015\u0010w\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0000¢\u0006\u0002\bxJ\u0010\u0010y\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0010\u0010z\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0010\u0010{\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0010\u0010|\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0018\u0010}\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u00052\u0006\u0010~\u001a\u00020;H\u0016J\u0010\u0010\u007f\u001a\u00020;2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0017\u0010\u0080\u0001\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u0005H\u0000¢\u0006\u0003\b\u0081\u0001J\u0017\u0010\u0082\u0001\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u0005H\u0000¢\u0006\u0003\b\u0083\u0001J\u0011\u0010\u0084\u0001\u001a\u00020\u00052\u0006\u0010_\u001a\u00020\u0005H\u0016J\u001a\u0010\u0085\u0001\u001a\u00020-2\u0006\u0010_\u001a\u00020\u00052\u0007\u0010\u0086\u0001\u001a\u00020;H\u0016J\u0012\u0010\u0087\u0001\u001a\u00030\u0088\u00012\u0006\u0010_\u001a\u00020\u0005H\u0016J\u0012\u0010\u0089\u0001\u001a\u00030\u0088\u00012\u0006\u0010_\u001a\u00020\u0005H\u0016J\u001d\u0010\u008a\u0001\u001a\f\u0012\u0005\u0012\u00030\u008c\u0001\u0018\u00010\u008b\u0001*\u00020%H\u0002¢\u0006\u0003\u0010\u008d\u0001J\u001c\u0010\u008e\u0001\u001a\u00020;*\u00030\u008f\u00012\f\u0010\u0090\u0001\u001a\u0007\u0012\u0002\b\u00030\u0091\u0001H\u0002J>\u0010\u0092\u0001\u001a\u00020a2\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\b\u0010\u0095\u0001\u001a\u00030\u0096\u00012\n\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u0098\u00012\n\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u009a\u0001H\u0016¢\u0006\u0006\b\u009b\u0001\u0010\u009c\u0001JT\u0010\u0092\u0001\u001a\u00020a2\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\b\u0010\u0095\u0001\u001a\u00030\u0096\u00012\n\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u0098\u00012\n\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u009a\u00012\n\u0010\u009d\u0001\u001a\u0005\u0018\u00010\u009e\u00012\b\u0010\u009f\u0001\u001a\u00030 \u0001H\u0016¢\u0006\u0006\b¡\u0001\u0010¢\u0001J]\u0010\u0092\u0001\u001a\u00020a2\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\b\u0010£\u0001\u001a\u00030¤\u00012\u0007\u0010¥\u0001\u001a\u00020-2\n\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u0098\u00012\n\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u009a\u00012\n\u0010\u009d\u0001\u001a\u0005\u0018\u00010\u009e\u00012\b\u0010\u009f\u0001\u001a\u00030 \u0001H\u0016¢\u0006\u0006\b¦\u0001\u0010§\u0001J\u0013\u0010\u0092\u0001\u001a\u00020a2\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0002J]\u0010¨\u0001\u001a\u00020%2\u0007\u0010©\u0001\u001a\u00020\u00052\u0007\u0010ª\u0001\u001a\u00020\u00052\n\u0010«\u0001\u001a\u0005\u0018\u00010¬\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0007\u0010\u00ad\u0001\u001a\u00020\u00052\u0007\u0010®\u0001\u001a\u00020\u00052\u0007\u0010¯\u0001\u001a\u00020\u00052\u0007\u0010°\u0001\u001a\u00020\u00052\b\b\u0002\u0010&\u001a\u00020'H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0006\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001f\u0010\u001eR\u0013\u0010\b\u001a\u00020\t¢\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010&\u001a\u00020'8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0014\u00100\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u0010/R\u0014\u00102\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u0010/R\u0014\u00104\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u0010/R\u0014\u00106\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u0010/R\u0014\u00108\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010/R\u0014\u0010:\u001a\u00020;8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R\u001a\u0010>\u001a\u00020?8@X\u0081\u0004¢\u0006\f\u0012\u0004\b@\u0010)\u001a\u0004\bA\u0010BR\u0014\u0010C\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bD\u0010\u001eR\u001c\u0010E\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010F0\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u001a\u0010I\u001a\u00020J8@X\u0081\u0004¢\u0006\f\u0012\u0004\bK\u0010)\u001a\u0004\bL\u0010M¨\u0006±\u0001"}, d2 = {"Landroidx/compose/ui/text/AndroidParagraph;", "Landroidx/compose/ui/text/Paragraph;", "paragraphIntrinsics", "Landroidx/compose/ui/text/platform/AndroidParagraphIntrinsics;", "maxLines", "", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "<init>", "(Landroidx/compose/ui/text/platform/AndroidParagraphIntrinsics;IIJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "text", "", "style", "Landroidx/compose/ui/text/TextStyle;", "annotations", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/AnnotatedString$Annotation;", "placeholders", "Landroidx/compose/ui/text/Placeholder;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "density", "Landroidx/compose/ui/unit/Density;", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Ljava/util/List;Ljava/util/List;IIJLandroidx/compose/ui/text/font/FontFamily$Resolver;Landroidx/compose/ui/unit/Density;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getParagraphIntrinsics", "()Landroidx/compose/ui/text/platform/AndroidParagraphIntrinsics;", "getMaxLines", "()I", "getOverflow-gIe3tQ8", "I", "getConstraints-msEJaDk", "()J", "J", TtmlNode.TAG_LAYOUT, "Landroidx/compose/ui/text/android/TextLayout;", "charSequence", "", "getCharSequence$ui_text$annotations", "()V", "getCharSequence$ui_text", "()Ljava/lang/CharSequence;", "width", "", "getWidth", "()F", "height", "getHeight", "maxIntrinsicWidth", "getMaxIntrinsicWidth", "minIntrinsicWidth", "getMinIntrinsicWidth", "firstBaseline", "getFirstBaseline", "lastBaseline", "getLastBaseline", "didExceedMaxLines", "", "getDidExceedMaxLines", "()Z", "textLocale", "Ljava/util/Locale;", "getTextLocale$ui_text$annotations", "getTextLocale$ui_text", "()Ljava/util/Locale;", "lineCount", "getLineCount", "placeholderRects", "Landroidx/compose/ui/geometry/Rect;", "getPlaceholderRects", "()Ljava/util/List;", "textPaint", "Landroidx/compose/ui/text/platform/AndroidTextPaint;", "getTextPaint$ui_text$annotations", "getTextPaint$ui_text", "()Landroidx/compose/ui/text/platform/AndroidTextPaint;", "getLineForVerticalPosition", "vertical", "getOffsetForPosition", "position", "Landroidx/compose/ui/geometry/Offset;", "getOffsetForPosition-k-4lQ0M", "(J)I", "getRangeForRect", "Landroidx/compose/ui/text/TextRange;", "rect", "granularity", "Landroidx/compose/ui/text/TextGranularity;", "inclusionStrategy", "Landroidx/compose/ui/text/TextInclusionStrategy;", "getRangeForRect-8-6BmAI", "(Landroidx/compose/ui/geometry/Rect;ILandroidx/compose/ui/text/TextInclusionStrategy;)J", "getBoundingBox", TypedValues.CycleType.S_WAVE_OFFSET, "fillBoundingBoxes", "", "range", "array", "", "arrayStart", "fillBoundingBoxes-8ffj60Q", "(J[FI)V", "getPathForRange", "Landroidx/compose/ui/graphics/Path;", "start", TtmlNode.END, "getCursorRect", "getWordBoundary", "getWordBoundary--jx7JFs", "(I)J", "getLineLeft", "lineIndex", "getLineRight", "getLineTop", "getLineAscent", "getLineAscent$ui_text", "getLineBaseline", "getLineDescent", "getLineDescent$ui_text", "getLineBottom", "getLineHeight", "getLineWidth", "getLineStart", "getLineEnd", "visibleEnd", "isLineEllipsized", "getLineEllipsisOffset", "getLineEllipsisOffset$ui_text", "getLineEllipsisCount", "getLineEllipsisCount$ui_text", "getLineForOffset", "getHorizontalPosition", "usePrimaryDirection", "getParagraphDirection", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getBidiRunDirection", "getShaderBrushSpans", "", "Landroidx/compose/ui/text/platform/style/ShaderBrushSpan;", "(Landroidx/compose/ui/text/android/TextLayout;)[Landroidx/compose/ui/text/platform/style/ShaderBrushSpan;", "hasSpan", "Landroid/text/Spanned;", "clazz", "Ljava/lang/Class;", "paint", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "color", "Landroidx/compose/ui/graphics/Color;", "shadow", "Landroidx/compose/ui/graphics/Shadow;", TtmlNode.ATTR_TTS_TEXT_DECORATION, "Landroidx/compose/ui/text/style/TextDecoration;", "paint-RPmYEkk", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;)V", "drawStyle", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "paint-LG529CI", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", "paint-hn5TExg", "(Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "constructTextLayout", "alignment", "justificationMode", "ellipsize", "Landroid/text/TextUtils$TruncateAt;", "hyphens", "breakStrategy", "lineBreakStyle", "lineBreakWordStyle", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AndroidParagraph implements Paragraph {
    public static final int $stable = 8;
    private final CharSequence charSequence;
    private final long constraints;
    private final TextLayout layout;
    private final int maxLines;
    private final int overflow;
    private final AndroidParagraphIntrinsics paragraphIntrinsics;
    private final List<Rect> placeholderRects;

    /* JADX INFO: compiled from: AndroidParagraph.android.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ResolvedTextDirection.values().length];
            try {
                iArr[ResolvedTextDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ResolvedTextDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ AndroidParagraph(AndroidParagraphIntrinsics androidParagraphIntrinsics, int i, int i2, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(androidParagraphIntrinsics, i, i2, j);
    }

    public /* synthetic */ AndroidParagraph(String str, TextStyle textStyle, List list, List list2, int i, int i2, long j, FontFamily.Resolver resolver, Density density, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, list, list2, i, i2, j, resolver, density);
    }

    public static /* synthetic */ void getCharSequence$ui_text$annotations() {
    }

    public static /* synthetic */ void getTextLocale$ui_text$annotations() {
    }

    public static /* synthetic */ void getTextPaint$ui_text$annotations() {
    }

    /* JADX WARN: Code duplicated, block: B:105:0x0309  */
    /* JADX WARN: Code duplicated, block: B:42:0x0153  */
    /* JADX WARN: Code duplicated, block: B:54:0x0191  */
    /* JADX WARN: Code duplicated, block: B:57:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:60:0x01d7 A[LOOP:0: B:58:0x01d1->B:60:0x01d7, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:63:0x0200  */
    /* JADX WARN: Code duplicated, block: B:64:0x0206  */
    /* JADX WARN: Code duplicated, block: B:66:0x0220  */
    /* JADX WARN: Code duplicated, block: B:68:0x0236  */
    /* JADX WARN: Code duplicated, block: B:69:0x0238  */
    /* JADX WARN: Code duplicated, block: B:75:0x024b  */
    /* JADX WARN: Code duplicated, block: B:78:0x0254  */
    /* JADX WARN: Code duplicated, block: B:79:0x0256  */
    /* JADX WARN: Instruction removed from duplicated block: B:64:0x0206, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v33 */
    /* JADX WARN: Type inference failed for: r0v34 */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.compose.ui.text.AndroidParagraph] */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v16 */
    /* JADX WARN: Type inference failed for: r11v23 */
    /* JADX WARN: Type inference failed for: r13v29 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    private AndroidParagraph(AndroidParagraphIntrinsics androidParagraphIntrinsics, int i, int i2, long j) {
        CharSequence charSequence;
        TextUtils.TruncateAt truncateAt;
        TextLayout textLayoutConstructTextLayout$default;
        int i3;
        ?? r15;
        int i4;
        ?? r0;
        ShaderBrushSpan[] shaderBrushSpans;
        CharSequence charSequence2;
        Spanned spanned;
        ArrayList arrayList;
        int i5;
        ArrayList arrayListEmptyList;
        int spanEnd;
        int lineForOffset;
        ?? r11;
        ?? r13;
        ?? r9;
        Rect rect;
        float horizontalPosition;
        float lineBaseline;
        int heightPx;
        float lineTop;
        float heightPx2;
        float lineBaseline2;
        Iterator it;
        AndroidParagraph androidParagraph;
        this.paragraphIntrinsics = androidParagraphIntrinsics;
        this.maxLines = i;
        this.overflow = i2;
        this.constraints = j;
        if (!(Constraints.m6834getMinHeightimpl(j) == 0 && Constraints.m6835getMinWidthimpl(j) == 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead.");
        }
        if (!(i >= 1)) {
            InlineClassHelperKt.throwIllegalArgumentException("maxLines should be greater than 0");
        }
        TextStyle style = androidParagraphIntrinsics.getStyle();
        if (AndroidParagraph_androidKt.shouldAttachIndentationFixSpan(style, TextOverflow.m6806equalsimpl0(i2, TextOverflow.INSTANCE.m6816getEllipsisgIe3tQ8()))) {
            charSequence = AndroidParagraph_androidKt.attachIndentationFixSpan(androidParagraphIntrinsics.getCharSequence());
        } else {
            charSequence = androidParagraphIntrinsics.getCharSequence();
        }
        this.charSequence = charSequence;
        int iM6173toLayoutAlignaXe7zB0 = AndroidParagraph_androidKt.m6173toLayoutAlignaXe7zB0(style.m6385getTextAligne0LSkKk());
        boolean zM6760equalsimpl0 = TextAlign.m6760equalsimpl0(style.m6385getTextAligne0LSkKk(), TextAlign.INSTANCE.m6766getJustifye0LSkKk());
        int iM6175toLayoutHyphenationFrequency3fSNIE = AndroidParagraph_androidKt.m6175toLayoutHyphenationFrequency3fSNIE(style.getParagraphStyle().getHyphens());
        int iM6174toLayoutBreakStrategyxImikfE = AndroidParagraph_androidKt.m6174toLayoutBreakStrategyxImikfE(LineBreak.m6672getStrategyfcGXIks(style.m6382getLineBreakrAG3T2k()));
        int iM6176toLayoutLineBreakStylehpcqdu8 = AndroidParagraph_androidKt.m6176toLayoutLineBreakStylehpcqdu8(LineBreak.m6673getStrictnessusljTpc(style.m6382getLineBreakrAG3T2k()));
        int iM6177toLayoutLineBreakWordStylewPN0Rpw = AndroidParagraph_androidKt.m6177toLayoutLineBreakWordStylewPN0Rpw(LineBreak.m6674getWordBreakjp8hJ3c(style.m6382getLineBreakrAG3T2k()));
        if (TextOverflow.m6806equalsimpl0(i2, TextOverflow.INSTANCE.m6816getEllipsisgIe3tQ8())) {
            truncateAt = TextUtils.TruncateAt.END;
        } else {
            if (!TextOverflow.m6806equalsimpl0(i2, TextOverflow.INSTANCE.m6817getMiddleEllipsisgIe3tQ8())) {
                if (TextOverflow.m6806equalsimpl0(i2, TextOverflow.INSTANCE.m6818getStartEllipsisgIe3tQ8())) {
                    truncateAt = TextUtils.TruncateAt.START;
                } else {
                    truncateAt = null;
                }
                TextUtils.TruncateAt truncateAt2 = truncateAt;
                CharSequence charSequence3 = charSequence;
                textLayoutConstructTextLayout$default = constructTextLayout$default(this, iM6173toLayoutAlignaXe7zB0, zM6760equalsimpl0, truncateAt2, i, iM6175toLayoutHyphenationFrequency3fSNIE, iM6174toLayoutBreakStrategyxImikfE, iM6176toLayoutLineBreakStylehpcqdu8, iM6177toLayoutLineBreakWordStylewPN0Rpw, null, 256, null);
                if (Build.VERSION.SDK_INT < 35 || getTextPaint$ui_text().getLetterSpacing() == 0.0f || (!(TextOverflow.m6806equalsimpl0(i2, TextOverflow.INSTANCE.m6818getStartEllipsisgIe3tQ8()) || TextOverflow.m6806equalsimpl0(i2, TextOverflow.INSTANCE.m6817getMiddleEllipsisgIe3tQ8())) || textLayoutConstructTextLayout$default.getLineEllipsisCount(0) <= 0)) {
                    i3 = i;
                    r15 = 1;
                } else {
                    int lineEllipsisOffset = textLayoutConstructTextLayout$default.getLineEllipsisOffset(0);
                    r15 = 1;
                    i3 = i;
                    textLayoutConstructTextLayout$default = constructTextLayout(iM6173toLayoutAlignaXe7zB0, zM6760equalsimpl0, truncateAt2, i3, iM6175toLayoutHyphenationFrequency3fSNIE, iM6174toLayoutBreakStrategyxImikfE, iM6176toLayoutLineBreakStylehpcqdu8, iM6177toLayoutLineBreakWordStylewPN0Rpw, TextUtils.concat(charSequence3.subSequence(0, lineEllipsisOffset), "…", charSequence3.subSequence(textLayoutConstructTextLayout$default.getLineEllipsisCount(0) + lineEllipsisOffset, charSequence3.length())));
                }
                if (!TextOverflow.m6806equalsimpl0(i2, TextOverflow.INSTANCE.m6816getEllipsisgIe3tQ8()) && textLayoutConstructTextLayout$default.getHeight() > Constraints.m6832getMaxHeightimpl(j) && i3 > r15) {
                    int iNumberOfLinesThatFitMaxHeight = AndroidParagraph_androidKt.numberOfLinesThatFitMaxHeight(textLayoutConstructTextLayout$default, Constraints.m6832getMaxHeightimpl(j));
                    if (iNumberOfLinesThatFitMaxHeight < 0 || iNumberOfLinesThatFitMaxHeight == i3) {
                        androidParagraph = this;
                        i4 = 2;
                    } else {
                        int iCoerceAtLeast = RangesKt.coerceAtLeast(iNumberOfLinesThatFitMaxHeight, (int) r15);
                        i4 = 2;
                        AndroidParagraph androidParagraph2 = this;
                        textLayoutConstructTextLayout$default = constructTextLayout$default(androidParagraph2, iM6173toLayoutAlignaXe7zB0, zM6760equalsimpl0, truncateAt2, iCoerceAtLeast, iM6175toLayoutHyphenationFrequency3fSNIE, iM6174toLayoutBreakStrategyxImikfE, iM6176toLayoutLineBreakStylehpcqdu8, iM6177toLayoutLineBreakWordStylewPN0Rpw, null, 256, null);
                        androidParagraph = androidParagraph2;
                    }
                    androidParagraph.layout = textLayoutConstructTextLayout$default;
                    r0 = androidParagraph;
                } else {
                    AndroidParagraph androidParagraph3 = this;
                    i4 = 2;
                    androidParagraph3.layout = textLayoutConstructTextLayout$default;
                    r0 = androidParagraph3;
                }
                r0.getTextPaint$ui_text().m6612setBrush12SF9DM(style.getBrush(), Size.m3930constructorimpl((((long) Float.floatToRawIntBits(r0.getHeight())) & 4294967295L) | (((long) Float.floatToRawIntBits(r0.getWidth())) << 32)), style.getAlpha());
                shaderBrushSpans = r0.getShaderBrushSpans(r0.layout);
                if (shaderBrushSpans != null) {
                    it = ArrayIteratorKt.iterator(shaderBrushSpans);
                    while (it.hasNext()) {
                        ((ShaderBrushSpan) it.next()).m6635setSizeuvyYCjk(Size.m3930constructorimpl((((long) Float.floatToRawIntBits(r0.getHeight())) & 4294967295L) | (((long) Float.floatToRawIntBits(r0.getWidth())) << 32)));
                    }
                }
                charSequence2 = r0.charSequence;
                if (charSequence2 instanceof Spanned) {
                    spanned = (Spanned) charSequence2;
                    Object[] spans = spanned.getSpans(0, charSequence2.length(), PlaceholderSpan.class);
                    arrayList = new ArrayList(spans.length);
                    for (Object obj : spans) {
                        PlaceholderSpan placeholderSpan = (PlaceholderSpan) obj;
                        int spanStart = spanned.getSpanStart(placeholderSpan);
                        spanEnd = spanned.getSpanEnd(placeholderSpan);
                        lineForOffset = r0.layout.getLineForOffset(spanStart);
                        if (lineForOffset >= r0.maxLines) {
                            r11 = r15;
                        } else {
                            r11 = 0;
                        }
                        if (r0.layout.getLineEllipsisCount(lineForOffset) > 0 || spanEnd <= r0.layout.getLineEllipsisOffset(lineForOffset)) {
                            r13 = 0;
                        } else {
                            r13 = r15;
                        }
                        if (spanEnd > r0.layout.getLineEnd(lineForOffset)) {
                            r9 = r15;
                        } else {
                            r9 = 0;
                        }
                        if (r13 != 0 && r9 == 0 && r11 == 0) {
                            int i6 = WhenMappings.$EnumSwitchMapping$0[r0.getBidiRunDirection(spanStart).ordinal()];
                            if (i6 == r15) {
                                horizontalPosition = r0.getHorizontalPosition(spanStart, r15);
                            } else {
                                if (i6 != i4) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                horizontalPosition = r0.getHorizontalPosition(spanStart, r15) - placeholderSpan.getWidthPx();
                            }
                            float widthPx = placeholderSpan.getWidthPx() + horizontalPosition;
                            TextLayout textLayout = r0.layout;
                            switch (placeholderSpan.getVerticalAlign()) {
                                case 0:
                                    lineBaseline = textLayout.getLineBaseline(lineForOffset);
                                    heightPx = placeholderSpan.getHeightPx();
                                    lineTop = lineBaseline - heightPx;
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                case 1:
                                    lineTop = textLayout.getLineTop(lineForOffset);
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                case 2:
                                    lineBaseline = textLayout.getLineBottom(lineForOffset);
                                    heightPx = placeholderSpan.getHeightPx();
                                    lineTop = lineBaseline - heightPx;
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                case 3:
                                    lineTop = ((textLayout.getLineTop(lineForOffset) + textLayout.getLineBottom(lineForOffset)) - placeholderSpan.getHeightPx()) / 2.0f;
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                case 4:
                                    heightPx2 = placeholderSpan.getFontMetrics().ascent;
                                    lineBaseline2 = textLayout.getLineBaseline(lineForOffset);
                                    lineTop = heightPx2 + lineBaseline2;
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                case 5:
                                    lineTop = (placeholderSpan.getFontMetrics().descent + textLayout.getLineBaseline(lineForOffset)) - placeholderSpan.getHeightPx();
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                case 6:
                                    Paint.FontMetricsInt fontMetrics = placeholderSpan.getFontMetrics();
                                    heightPx2 = ((fontMetrics.ascent + fontMetrics.descent) - placeholderSpan.getHeightPx()) / i4;
                                    lineBaseline2 = textLayout.getLineBaseline(lineForOffset);
                                    lineTop = heightPx2 + lineBaseline2;
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                default:
                                    throw new IllegalStateException("unexpected verticalAlignment");
                            }
                        } else {
                            rect = null;
                        }
                        arrayList.add(rect);
                    }
                    arrayListEmptyList = arrayList;
                } else {
                    arrayListEmptyList = CollectionsKt.emptyList();
                }
                r0.placeholderRects = arrayListEmptyList;
            }
            truncateAt = TextUtils.TruncateAt.MIDDLE;
        }
        TextUtils.TruncateAt truncateAt3 = truncateAt;
        CharSequence charSequence4 = charSequence;
        textLayoutConstructTextLayout$default = constructTextLayout$default(this, iM6173toLayoutAlignaXe7zB0, zM6760equalsimpl0, truncateAt3, i, iM6175toLayoutHyphenationFrequency3fSNIE, iM6174toLayoutBreakStrategyxImikfE, iM6176toLayoutLineBreakStylehpcqdu8, iM6177toLayoutLineBreakWordStylewPN0Rpw, null, 256, null);
        if (Build.VERSION.SDK_INT < 35) {
            i3 = i;
            r15 = 1;
        } else {
            i3 = i;
            r15 = 1;
        }
        if (!TextOverflow.m6806equalsimpl0(i2, TextOverflow.INSTANCE.m6816getEllipsisgIe3tQ8())) {
            AndroidParagraph androidParagraph4 = this;
            i4 = 2;
            androidParagraph4.layout = textLayoutConstructTextLayout$default;
            r0 = androidParagraph4;
        } else {
            AndroidParagraph androidParagraph5 = this;
            i4 = 2;
            androidParagraph5.layout = textLayoutConstructTextLayout$default;
            r0 = androidParagraph5;
        }
        r0.getTextPaint$ui_text().m6612setBrush12SF9DM(style.getBrush(), Size.m3930constructorimpl((((long) Float.floatToRawIntBits(r0.getHeight())) & 4294967295L) | (((long) Float.floatToRawIntBits(r0.getWidth())) << 32)), style.getAlpha());
        shaderBrushSpans = r0.getShaderBrushSpans(r0.layout);
        if (shaderBrushSpans != null) {
            it = ArrayIteratorKt.iterator(shaderBrushSpans);
            while (it.hasNext()) {
                ((ShaderBrushSpan) it.next()).m6635setSizeuvyYCjk(Size.m3930constructorimpl((((long) Float.floatToRawIntBits(r0.getHeight())) & 4294967295L) | (((long) Float.floatToRawIntBits(r0.getWidth())) << 32)));
            }
        }
        charSequence2 = r0.charSequence;
        if (charSequence2 instanceof Spanned) {
            arrayListEmptyList = CollectionsKt.emptyList();
        } else {
            spanned = (Spanned) charSequence2;
            Object[] spans2 = spanned.getSpans(0, charSequence2.length(), PlaceholderSpan.class);
            arrayList = new ArrayList(spans2.length);
            while (i5 < r5) {
                PlaceholderSpan placeholderSpan2 = (PlaceholderSpan) obj;
                int spanStart2 = spanned.getSpanStart(placeholderSpan2);
                spanEnd = spanned.getSpanEnd(placeholderSpan2);
                lineForOffset = r0.layout.getLineForOffset(spanStart2);
                if (lineForOffset >= r0.maxLines) {
                    r11 = r15;
                } else {
                    r11 = 0;
                }
                if (r0.layout.getLineEllipsisCount(lineForOffset) > 0) {
                    r13 = 0;
                } else {
                    r13 = 0;
                }
                if (spanEnd > r0.layout.getLineEnd(lineForOffset)) {
                    r9 = r15;
                } else {
                    r9 = 0;
                }
                if (r13 != 0) {
                    rect = null;
                } else {
                    rect = null;
                }
                arrayList.add(rect);
            }
            arrayListEmptyList = arrayList;
        }
        r0.placeholderRects = arrayListEmptyList;
    }

    public final AndroidParagraphIntrinsics getParagraphIntrinsics() {
        return this.paragraphIntrinsics;
    }

    public final int getMaxLines() {
        return this.maxLines;
    }

    /* JADX INFO: renamed from: getOverflow-gIe3tQ8, reason: not valid java name and from getter */
    public final int getOverflow() {
        return this.overflow;
    }

    /* JADX INFO: renamed from: getConstraints-msEJaDk, reason: not valid java name and from getter */
    public final long getConstraints() {
        return this.constraints;
    }

    private AndroidParagraph(String str, TextStyle textStyle, List<? extends AnnotatedString.Range<? extends AnnotatedString.Annotation>> list, List<AnnotatedString.Range<Placeholder>> list2, int i, int i2, long j, FontFamily.Resolver resolver, Density density) {
        this(new AndroidParagraphIntrinsics(str, textStyle, list, list2, resolver, density), i, i2, j, null);
    }

    /* JADX INFO: renamed from: getCharSequence$ui_text, reason: from getter */
    public final CharSequence getCharSequence() {
        return this.charSequence;
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getWidth() {
        return Constraints.m6833getMaxWidthimpl(this.constraints);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getHeight() {
        return this.layout.getHeight();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getMaxIntrinsicWidth() {
        return this.paragraphIntrinsics.getMaxIntrinsicWidth();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getMinIntrinsicWidth() {
        return this.paragraphIntrinsics.getMinIntrinsicWidth();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getFirstBaseline() {
        return getLineBaseline(0);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLastBaseline() {
        return getLineBaseline(getLineCount() - 1);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public boolean getDidExceedMaxLines() {
        return this.layout.getDidExceedMaxLines();
    }

    public final Locale getTextLocale$ui_text() {
        return this.paragraphIntrinsics.getTextPaint().getTextLocale();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineCount() {
        return this.layout.getLineCount();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public List<Rect> getPlaceholderRects() {
        return this.placeholderRects;
    }

    public final AndroidTextPaint getTextPaint$ui_text() {
        return this.paragraphIntrinsics.getTextPaint();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineForVerticalPosition(float vertical) {
        return this.layout.getLineForVertical((int) vertical);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: getOffsetForPosition-k-4lQ0M, reason: not valid java name */
    public int mo6160getOffsetForPositionk4lQ0M(long position) {
        return this.layout.getOffsetForHorizontal(this.layout.getLineForVertical((int) Float.intBitsToFloat((int) (4294967295L & position))), Float.intBitsToFloat((int) (position >> 32)));
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: getRangeForRect-8-6BmAI, reason: not valid java name */
    public long mo6162getRangeForRect86BmAI(Rect rect, int granularity, final TextInclusionStrategy inclusionStrategy) {
        int[] rangeForRect = this.layout.getRangeForRect(RectHelper_androidKt.toAndroidRectF(rect), AndroidParagraph_androidKt.m6178toLayoutTextGranularityduNsdkg(granularity), new Function2() { // from class: androidx.compose.ui.text.AndroidParagraph$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(inclusionStrategy.isIncluded(RectHelper_androidKt.toComposeRect((RectF) obj), RectHelper_androidKt.toComposeRect((RectF) obj2)));
            }
        });
        if (rangeForRect == null) {
            return TextRange.INSTANCE.m6349getZerod9O1mEE();
        }
        return TextRangeKt.TextRange(rangeForRect[0], rangeForRect[1]);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public Rect getBoundingBox(int offset) {
        boolean z = false;
        if (offset >= 0 && offset < this.charSequence.length()) {
            z = true;
        }
        if (!z) {
            InlineClassHelperKt.throwIllegalArgumentException("offset(" + offset + ") is out of bounds [0," + this.charSequence.length() + ')');
        }
        RectF boundingBox = this.layout.getBoundingBox(offset);
        return new Rect(boundingBox.left, boundingBox.top, boundingBox.right, boundingBox.bottom);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: fillBoundingBoxes-8ffj60Q, reason: not valid java name */
    public void mo6158fillBoundingBoxes8ffj60Q(long range, float[] array, int arrayStart) {
        this.layout.fillBoundingBoxes(TextRange.m6342getMinimpl(range), TextRange.m6341getMaximpl(range), array, arrayStart);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public Path getPathForRange(int start, int end) {
        if (!(start >= 0 && start <= end && end <= this.charSequence.length())) {
            InlineClassHelperKt.throwIllegalArgumentException("start(" + start + ") or end(" + end + ") is out of range [0.." + this.charSequence.length() + "], or start > end!");
        }
        android.graphics.Path path = new android.graphics.Path();
        this.layout.getSelectionPath(start, end, path);
        return AndroidPath_androidKt.asComposePath(path);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public Rect getCursorRect(int offset) {
        if (!(offset >= 0 && offset <= this.charSequence.length())) {
            InlineClassHelperKt.throwIllegalArgumentException("offset(" + offset + ") is out of bounds [0," + this.charSequence.length() + AbstractJsonLexerKt.END_LIST);
        }
        float primaryHorizontal$default = TextLayout.getPrimaryHorizontal$default(this.layout, offset, false, 2, null);
        int lineForOffset = this.layout.getLineForOffset(offset);
        return new Rect(primaryHorizontal$default, this.layout.getLineTop(lineForOffset), primaryHorizontal$default, this.layout.getLineBottom(lineForOffset));
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: getWordBoundary--jx7JFs, reason: not valid java name */
    public long mo6163getWordBoundaryjx7JFs(int offset) {
        WordIterator wordIterator = this.layout.getWordIterator();
        return TextRangeKt.TextRange(WordBoundary_androidKt.getWordStart(wordIterator, offset), WordBoundary_androidKt.getWordEnd(wordIterator, offset));
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineLeft(int lineIndex) {
        return this.layout.getLineLeft(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineRight(int lineIndex) {
        return this.layout.getLineRight(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineTop(int lineIndex) {
        return this.layout.getLineTop(lineIndex);
    }

    public final float getLineAscent$ui_text(int lineIndex) {
        return this.layout.getLineAscent(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineBaseline(int lineIndex) {
        return this.layout.getLineBaseline(lineIndex);
    }

    public final float getLineDescent$ui_text(int lineIndex) {
        return this.layout.getLineDescent(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineBottom(int lineIndex) {
        return this.layout.getLineBottom(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineHeight(int lineIndex) {
        return this.layout.getLineHeight(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineWidth(int lineIndex) {
        return this.layout.getLineWidth(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineStart(int lineIndex) {
        return this.layout.getLineStart(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineEnd(int lineIndex, boolean visibleEnd) {
        TextLayout textLayout = this.layout;
        if (visibleEnd) {
            return textLayout.getLineVisibleEnd(lineIndex);
        }
        return textLayout.getLineEnd(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public boolean isLineEllipsized(int lineIndex) {
        return this.layout.isLineEllipsized(lineIndex);
    }

    public final int getLineEllipsisOffset$ui_text(int lineIndex) {
        return this.layout.getLineEllipsisOffset(lineIndex);
    }

    public final int getLineEllipsisCount$ui_text(int lineIndex) {
        return this.layout.getLineEllipsisCount(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineForOffset(int offset) {
        return this.layout.getLineForOffset(offset);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getHorizontalPosition(int offset, boolean usePrimaryDirection) {
        TextLayout textLayout = this.layout;
        if (usePrimaryDirection) {
            return TextLayout.getPrimaryHorizontal$default(textLayout, offset, false, 2, null);
        }
        return TextLayout.getSecondaryHorizontal$default(textLayout, offset, false, 2, null);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public ResolvedTextDirection getParagraphDirection(int offset) {
        return this.layout.getParagraphDirection(this.layout.getLineForOffset(offset)) == 1 ? ResolvedTextDirection.Ltr : ResolvedTextDirection.Rtl;
    }

    @Override // androidx.compose.ui.text.Paragraph
    public ResolvedTextDirection getBidiRunDirection(int offset) {
        return this.layout.isRtlCharAt(offset) ? ResolvedTextDirection.Rtl : ResolvedTextDirection.Ltr;
    }

    private final ShaderBrushSpan[] getShaderBrushSpans(TextLayout textLayout) {
        if (!(textLayout.getText() instanceof Spanned)) {
            return null;
        }
        CharSequence text = textLayout.getText();
        Intrinsics.checkNotNull(text, "null cannot be cast to non-null type android.text.Spanned");
        if (!hasSpan((Spanned) text, ShaderBrushSpan.class)) {
            return null;
        }
        CharSequence text2 = textLayout.getText();
        Intrinsics.checkNotNull(text2, "null cannot be cast to non-null type android.text.Spanned");
        return (ShaderBrushSpan[]) ((Spanned) text2).getSpans(0, textLayout.getText().length(), ShaderBrushSpan.class);
    }

    private final boolean hasSpan(Spanned spanned, Class<?> cls) {
        return spanned.nextSpanTransition(-1, spanned.length(), cls) != spanned.length();
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: paint-RPmYEkk, reason: not valid java name */
    public void mo6165paintRPmYEkk(Canvas canvas, long color, Shadow shadow, TextDecoration textDecoration) {
        AndroidTextPaint textPaint$ui_text = getTextPaint$ui_text();
        textPaint$ui_text.m6614setColor8_81llA(color);
        textPaint$ui_text.setShadow(shadow);
        textPaint$ui_text.setTextDecoration(textDecoration);
        paint(canvas);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: paint-LG529CI, reason: not valid java name */
    public void mo6164paintLG529CI(Canvas canvas, long color, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int blendMode) {
        int backingBlendMode = getTextPaint$ui_text().getBackingBlendMode();
        AndroidTextPaint textPaint$ui_text = getTextPaint$ui_text();
        textPaint$ui_text.m6614setColor8_81llA(color);
        textPaint$ui_text.setShadow(shadow);
        textPaint$ui_text.setTextDecoration(textDecoration);
        textPaint$ui_text.setDrawStyle(drawStyle);
        textPaint$ui_text.m6611setBlendModes9anfk8(blendMode);
        paint(canvas);
        getTextPaint$ui_text().m6611setBlendModes9anfk8(backingBlendMode);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: paint-hn5TExg, reason: not valid java name */
    public void mo6166painthn5TExg(Canvas canvas, Brush brush, float alpha, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int blendMode) {
        int backingBlendMode = getTextPaint$ui_text().getBackingBlendMode();
        AndroidTextPaint textPaint$ui_text = getTextPaint$ui_text();
        float width = getWidth();
        textPaint$ui_text.m6612setBrush12SF9DM(brush, Size.m3930constructorimpl((((long) Float.floatToRawIntBits(getHeight())) & 4294967295L) | (Float.floatToRawIntBits(width) << 32)), alpha);
        textPaint$ui_text.setShadow(shadow);
        textPaint$ui_text.setTextDecoration(textDecoration);
        textPaint$ui_text.setDrawStyle(drawStyle);
        textPaint$ui_text.m6611setBlendModes9anfk8(blendMode);
        paint(canvas);
        getTextPaint$ui_text().m6611setBlendModes9anfk8(backingBlendMode);
    }

    private final void paint(Canvas canvas) {
        android.graphics.Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        if (getDidExceedMaxLines()) {
            nativeCanvas.save();
            nativeCanvas.clipRect(0.0f, 0.0f, getWidth(), getHeight());
        }
        this.layout.paint(nativeCanvas);
        if (getDidExceedMaxLines()) {
            nativeCanvas.restore();
        }
    }

    static /* synthetic */ TextLayout constructTextLayout$default(AndroidParagraph androidParagraph, int i, int i2, TextUtils.TruncateAt truncateAt, int i3, int i4, int i5, int i6, int i7, CharSequence charSequence, int i8, Object obj) {
        return androidParagraph.constructTextLayout(i, i2, truncateAt, i3, i4, i5, i6, i7, (i8 & 256) != 0 ? androidParagraph.charSequence : charSequence);
    }

    private final TextLayout constructTextLayout(int alignment, int justificationMode, TextUtils.TruncateAt ellipsize, int maxLines, int hyphens, int breakStrategy, int lineBreakStyle, int lineBreakWordStyle, CharSequence charSequence) {
        float width = getWidth();
        AndroidTextPaint textPaint$ui_text = getTextPaint$ui_text();
        return new TextLayout(charSequence, width, textPaint$ui_text, alignment, ellipsize, this.paragraphIntrinsics.getTextDirectionHeuristic(), 1.0f, 0.0f, AndroidParagraphHelper_androidKt.isIncludeFontPaddingEnabled(this.paragraphIntrinsics.getStyle()), true, maxLines, breakStrategy, lineBreakStyle, lineBreakWordStyle, hyphens, justificationMode, null, null, this.paragraphIntrinsics.getLayoutIntrinsics(), 196736, null);
    }
}
