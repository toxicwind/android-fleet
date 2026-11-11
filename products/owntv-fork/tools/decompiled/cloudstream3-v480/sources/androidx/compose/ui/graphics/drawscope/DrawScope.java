package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.FontScaling;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: DrawScope.kt */
/* JADX INFO: loaded from: classes.dex */
@DrawScopeMarker
@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u0000 u2\u00020\u0001:\u0001uJg\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b\"\u0010#Jg\u0010\u0011\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b&\u0010'JU\u0010(\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b,\u0010-JU\u0010(\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b.\u0010/JK\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b3\u00104Ji\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u0002062\b\b\u0002\u0010:\u001a\u0002082\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H'¢\u0006\u0004\b;\u0010<Js\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u0002062\b\b\u0002\u0010:\u001a\u0002082\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010=\u001a\u00020>H\u0016¢\u0006\u0004\b?\u0010@J_\u0010A\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010B\u001a\u00020C2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bD\u0010EJ_\u0010A\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010B\u001a\u00020C2\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bF\u0010GJU\u0010H\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010I\u001a\u00020\u00182\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bJ\u0010KJU\u0010H\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010I\u001a\u00020\u00182\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bL\u0010MJU\u0010N\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bO\u0010-JU\u0010N\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bP\u0010/Jm\u0010Q\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010R\u001a\u00020\u00182\u0006\u0010S\u001a\u00020\u00182\u0006\u0010T\u001a\u00020U2\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bV\u0010WJm\u0010Q\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\u0006\u0010R\u001a\u00020\u00182\u0006\u0010S\u001a\u00020\u00182\u0006\u0010T\u001a\u00020U2\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bX\u0010YJI\u0010Z\u001a\u00020\u00122\u0006\u0010[\u001a\u00020\\2\u0006\u0010$\u001a\u00020%2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b]\u0010^JI\u0010Z\u001a\u00020\u00122\u0006\u0010[\u001a\u00020\\2\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b_\u0010`Jm\u0010a\u001a\u00020\u00122\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00070c2\u0006\u0010d\u001a\u00020e2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bf\u0010gJm\u0010a\u001a\u00020\u00122\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00070c2\u0006\u0010d\u001a\u00020e2\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bh\u0010iJ6\u0010j\u001a\u00020\u0012*\u00020k2\b\b\u0002\u0010\n\u001a\u0002082\u0017\u0010l\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00120m¢\u0006\u0002\bnH\u0016¢\u0006\u0004\bo\u0010pJ\u001b\u0010q\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010r\u001a\u00020\u0007H\u0002¢\u0006\u0004\bs\u0010tR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\tR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006vÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/unit/Density;", "drawContext", "Landroidx/compose/ui/graphics/drawscope/DrawContext;", "getDrawContext", "()Landroidx/compose/ui/graphics/drawscope/DrawContext;", TtmlNode.CENTER, "Landroidx/compose/ui/geometry/Offset;", "getCenter-F1C5BW0", "()J", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/geometry/Size;", "getSize-NH-jbRc", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "drawLine", "", "brush", "Landroidx/compose/ui/graphics/Brush;", "start", TtmlNode.END, "strokeWidth", "", "cap", "Landroidx/compose/ui/graphics/StrokeCap;", "pathEffect", "Landroidx/compose/ui/graphics/PathEffect;", "alpha", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "drawLine-1RTmtNc", "(Landroidx/compose/ui/graphics/Brush;JJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "color", "Landroidx/compose/ui/graphics/Color;", "drawLine-NGM6Ib0", "(JJJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawRect", "topLeft", "style", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "drawRect-AsUm42w", "(Landroidx/compose/ui/graphics/Brush;JJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawRect-n-J9OG0", "(JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawImage", "image", "Landroidx/compose/ui/graphics/ImageBitmap;", "drawImage-gbVJVH8", "(Landroidx/compose/ui/graphics/ImageBitmap;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "srcOffset", "Landroidx/compose/ui/unit/IntOffset;", "srcSize", "Landroidx/compose/ui/unit/IntSize;", "dstOffset", "dstSize", "drawImage-9jGpkUE", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "drawImage-AZ2fEMs", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;II)V", "drawRoundRect", "cornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "drawRoundRect-ZuiqVtQ", "(Landroidx/compose/ui/graphics/Brush;JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawRoundRect-u-Aw5IA", "(JJJJLandroidx/compose/ui/graphics/drawscope/DrawStyle;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawCircle", "radius", "drawCircle-V9BoPsw", "(Landroidx/compose/ui/graphics/Brush;FJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawCircle-VaOC9Bg", "(JFJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawOval", "drawOval-AsUm42w", "drawOval-n-J9OG0", "drawArc", "startAngle", "sweepAngle", "useCenter", "", "drawArc-illE91I", "(Landroidx/compose/ui/graphics/Brush;FFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawArc-yD3GUKo", "(JFFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPath", "path", "Landroidx/compose/ui/graphics/Path;", "drawPath-LG529CI", "(Landroidx/compose/ui/graphics/Path;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPath-GBMwjPU", "(Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPoints", "points", "", "pointMode", "Landroidx/compose/ui/graphics/PointMode;", "drawPoints-F8ZwMP8", "(Ljava/util/List;IJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawPoints-Gsft0Ws", "(Ljava/util/List;ILandroidx/compose/ui/graphics/Brush;FILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "record", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "record-JVtK1S4", "(Landroidx/compose/ui/graphics/layer/GraphicsLayer;JLkotlin/jvm/functions/Function1;)V", "offsetSize", TypedValues.CycleType.S_WAVE_OFFSET, "offsetSize-PENXr5M", "(JJ)J", "Companion", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface DrawScope extends Density {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: drawArc-illE91I */
    void mo4627drawArcillE91I(Brush brush, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawArc-yD3GUKo */
    void mo4628drawArcyD3GUKo(long color, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawCircle-V9BoPsw */
    void mo4629drawCircleV9BoPsw(Brush brush, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawCircle-VaOC9Bg */
    void mo4630drawCircleVaOC9Bg(long color, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Prefer usage of drawImage that consumes an optional FilterQuality parameter", replaceWith = @ReplaceWith(expression = "drawImage(image, srcOffset, srcSize, dstOffset, dstSize, alpha, style, colorFilter, blendMode, FilterQuality.Low)", imports = {"androidx.compose.ui.graphics.drawscope", "androidx.compose.ui.graphics.FilterQuality"}))
    /* JADX INFO: renamed from: drawImage-9jGpkUE */
    /* synthetic */ void mo4631drawImage9jGpkUE(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawImage-AZ2fEMs */
    void mo4632drawImageAZ2fEMs(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode, int filterQuality);

    /* JADX INFO: renamed from: drawImage-gbVJVH8 */
    void mo4633drawImagegbVJVH8(ImageBitmap image, long topLeft, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawLine-1RTmtNc */
    void mo4634drawLine1RTmtNc(Brush brush, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawLine-NGM6Ib0 */
    void mo4635drawLineNGM6Ib0(long color, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawOval-AsUm42w */
    void mo4636drawOvalAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawOval-n-J9OG0 */
    void mo4637drawOvalnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawPath-GBMwjPU */
    void mo4638drawPathGBMwjPU(Path path, Brush brush, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawPath-LG529CI */
    void mo4639drawPathLG529CI(Path path, long color, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawPoints-F8ZwMP8 */
    void mo4640drawPointsF8ZwMP8(List<Offset> points, int pointMode, long color, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawPoints-Gsft0Ws */
    void mo4641drawPointsGsft0Ws(List<Offset> points, int pointMode, Brush brush, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawRect-AsUm42w */
    void mo4642drawRectAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawRect-n-J9OG0 */
    void mo4643drawRectnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawRoundRect-ZuiqVtQ */
    void mo4644drawRoundRectZuiqVtQ(Brush brush, long topLeft, long size, long cornerRadius, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawRoundRect-u-Aw5IA */
    void mo4645drawRoundRectuAw5IA(long color, long topLeft, long size, long cornerRadius, DrawStyle style, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: getCenter-F1C5BW0 */
    long mo4646getCenterF1C5BW0();

    DrawContext getDrawContext();

    LayoutDirection getLayoutDirection();

    /* JADX INFO: renamed from: getSize-NH-jbRc */
    long mo4647getSizeNHjbRc();

    /* JADX INFO: renamed from: record-JVtK1S4 */
    void mo4648recordJVtK1S4(GraphicsLayer graphicsLayer, long j, Function1<? super DrawScope, Unit> function1);

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.drawscope.DrawScope$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: DrawScope.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = DrawScope.INSTANCE;
        }

        /* JADX INFO: renamed from: $default$getCenter-F1C5BW0, reason: not valid java name */
        public static long m4696$default$getCenterF1C5BW0(DrawScope _this) {
            return SizeKt.m3949getCenteruvyYCjk(_this.getDrawContext().mo4654getSizeNHjbRc());
        }

        /* JADX INFO: renamed from: $default$getSize-NH-jbRc, reason: not valid java name */
        public static long m4697$default$getSizeNHjbRc(DrawScope _this) {
            return _this.getDrawContext().mo4654getSizeNHjbRc();
        }

        /* JADX INFO: renamed from: drawLine-1RTmtNc$default, reason: not valid java name */
        public static /* synthetic */ void m4723drawLine1RTmtNc$default(DrawScope drawScope, Brush brush, long j, long j2, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawLine-1RTmtNc");
            }
            drawScope.mo4634drawLine1RTmtNc(brush, j, j2, (i3 & 8) != 0 ? 0.0f : f, (i3 & 16) != 0 ? Stroke.INSTANCE.m4806getDefaultCapKaPHkGw() : i, (i3 & 32) != 0 ? null : pathEffect, (i3 & 64) != 0 ? 1.0f : f2, (i3 & 128) != 0 ? null : colorFilter, (i3 & 256) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i2);
        }

        /* JADX INFO: renamed from: drawLine-NGM6Ib0$default, reason: not valid java name */
        public static /* synthetic */ void m4724drawLineNGM6Ib0$default(DrawScope drawScope, long j, long j2, long j3, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawLine-NGM6Ib0");
            }
            drawScope.mo4635drawLineNGM6Ib0(j, j2, j3, (i3 & 8) != 0 ? 0.0f : f, (i3 & 16) != 0 ? Stroke.INSTANCE.m4806getDefaultCapKaPHkGw() : i, (i3 & 32) != 0 ? null : pathEffect, (i3 & 64) != 0 ? 1.0f : f2, (i3 & 128) != 0 ? null : colorFilter, (i3 & 256) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i2);
        }

        /* JADX INFO: renamed from: drawRect-AsUm42w$default, reason: not valid java name */
        public static /* synthetic */ void m4731drawRectAsUm42w$default(DrawScope drawScope, Brush brush, long j, long j2, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRect-AsUm42w");
            }
            long jM3886getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m3886getZeroF1C5BW0() : j;
            drawScope.mo4642drawRectAsUm42w(brush, jM3886getZeroF1C5BW0, (i2 & 4) != 0 ? m4699$private$offsetSizePENXr5M(drawScope, drawScope.mo4647getSizeNHjbRc(), jM3886getZeroF1C5BW0) : j2, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawRect-n-J9OG0$default, reason: not valid java name */
        public static /* synthetic */ void m4732drawRectnJ9OG0$default(DrawScope drawScope, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRect-n-J9OG0");
            }
            long jM3886getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m3886getZeroF1C5BW0() : j2;
            drawScope.mo4643drawRectnJ9OG0(j, jM3886getZeroF1C5BW0, (i2 & 4) != 0 ? m4699$private$offsetSizePENXr5M(drawScope, drawScope.mo4647getSizeNHjbRc(), jM3886getZeroF1C5BW0) : j3, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawImage-gbVJVH8$default, reason: not valid java name */
        public static /* synthetic */ void m4722drawImagegbVJVH8$default(DrawScope drawScope, ImageBitmap imageBitmap, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImage-gbVJVH8");
            }
            drawScope.mo4633drawImagegbVJVH8(imageBitmap, (i2 & 2) != 0 ? Offset.INSTANCE.m3886getZeroF1C5BW0() : j, (i2 & 4) != 0 ? 1.0f : f, (i2 & 8) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 16) != 0 ? null : colorFilter, (i2 & 32) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawImage-9jGpkUE$default, reason: not valid java name */
        public static /* synthetic */ void m4720drawImage9jGpkUE$default(DrawScope drawScope, ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            long jM7060constructorimpl;
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImage-9jGpkUE");
            }
            long jM7033getZeronOccac = (i2 & 2) != 0 ? IntOffset.INSTANCE.m7033getZeronOccac() : j;
            if ((i2 & 4) != 0) {
                jM7060constructorimpl = IntSize.m7060constructorimpl((((long) imageBitmap.getHeight()) & 4294967295L) | (((long) imageBitmap.getWidth()) << 32));
            } else {
                jM7060constructorimpl = j2;
            }
            drawScope.mo4631drawImage9jGpkUE(imageBitmap, jM7033getZeronOccac, jM7060constructorimpl, (i2 & 8) != 0 ? IntOffset.INSTANCE.m7033getZeronOccac() : j3, (i2 & 16) != 0 ? jM7060constructorimpl : j4, (i2 & 32) != 0 ? 1.0f : f, (i2 & 64) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 128) != 0 ? null : colorFilter, (i2 & 256) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawImage-AZ2fEMs$default, reason: not valid java name */
        public static /* synthetic */ void m4721drawImageAZ2fEMs$default(DrawScope drawScope, ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, int i3, Object obj) {
            long jM7060constructorimpl;
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImage-AZ2fEMs");
            }
            long jM7033getZeronOccac = (i3 & 2) != 0 ? IntOffset.INSTANCE.m7033getZeronOccac() : j;
            if ((i3 & 4) != 0) {
                jM7060constructorimpl = IntSize.m7060constructorimpl((((long) imageBitmap.getHeight()) & 4294967295L) | (((long) imageBitmap.getWidth()) << 32));
            } else {
                jM7060constructorimpl = j2;
            }
            drawScope.mo4632drawImageAZ2fEMs(imageBitmap, jM7033getZeronOccac, jM7060constructorimpl, (i3 & 8) != 0 ? IntOffset.INSTANCE.m7033getZeronOccac() : j3, (i3 & 16) != 0 ? jM7060constructorimpl : j4, (i3 & 32) != 0 ? 1.0f : f, (i3 & 64) != 0 ? Fill.INSTANCE : drawStyle, (i3 & 128) != 0 ? null : colorFilter, (i3 & 256) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i, (i3 & 512) != 0 ? DrawScope.INSTANCE.m4737getDefaultFilterQualityfv9h1I() : i2);
        }

        /* JADX INFO: renamed from: drawRoundRect-ZuiqVtQ$default, reason: not valid java name */
        public static /* synthetic */ void m4733drawRoundRectZuiqVtQ$default(DrawScope drawScope, Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRoundRect-ZuiqVtQ");
            }
            long jM3886getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m3886getZeroF1C5BW0() : j;
            drawScope.mo4644drawRoundRectZuiqVtQ(brush, jM3886getZeroF1C5BW0, (i2 & 4) != 0 ? m4699$private$offsetSizePENXr5M(drawScope, drawScope.mo4647getSizeNHjbRc(), jM3886getZeroF1C5BW0) : j2, (i2 & 8) != 0 ? CornerRadius.INSTANCE.m3842getZerokKHJgLs() : j3, (i2 & 16) != 0 ? 1.0f : f, (i2 & 32) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 64) != 0 ? null : colorFilter, (i2 & 128) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawRoundRect-u-Aw5IA$default, reason: not valid java name */
        public static /* synthetic */ void m4734drawRoundRectuAw5IA$default(DrawScope drawScope, long j, long j2, long j3, long j4, DrawStyle drawStyle, float f, ColorFilter colorFilter, int i, int i2, Object obj) {
            DrawScope drawScope2;
            long jM4699$private$offsetSizePENXr5M;
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRoundRect-u-Aw5IA");
            }
            long jM3886getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m3886getZeroF1C5BW0() : j2;
            if ((i2 & 4) != 0) {
                drawScope2 = drawScope;
                jM4699$private$offsetSizePENXr5M = m4699$private$offsetSizePENXr5M(drawScope2, drawScope.mo4647getSizeNHjbRc(), jM3886getZeroF1C5BW0);
            } else {
                drawScope2 = drawScope;
                jM4699$private$offsetSizePENXr5M = j3;
            }
            drawScope2.mo4645drawRoundRectuAw5IA(j, jM3886getZeroF1C5BW0, jM4699$private$offsetSizePENXr5M, (i2 & 8) != 0 ? CornerRadius.INSTANCE.m3842getZerokKHJgLs() : j4, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? 1.0f : f, (i2 & 64) != 0 ? null : colorFilter, (i2 & 128) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawCircle-V9BoPsw$default, reason: not valid java name */
        public static /* synthetic */ void m4718drawCircleV9BoPsw$default(DrawScope drawScope, Brush brush, float f, long j, float f2, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawCircle-V9BoPsw");
            }
            drawScope.mo4629drawCircleV9BoPsw(brush, (i2 & 2) != 0 ? Size.m3938getMinDimensionimpl(drawScope.mo4647getSizeNHjbRc()) / 2.0f : f, (i2 & 4) != 0 ? drawScope.mo4646getCenterF1C5BW0() : j, (i2 & 8) != 0 ? 1.0f : f2, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawCircle-VaOC9Bg$default, reason: not valid java name */
        public static /* synthetic */ void m4719drawCircleVaOC9Bg$default(DrawScope drawScope, long j, float f, long j2, float f2, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawCircle-VaOC9Bg");
            }
            if ((i2 & 2) != 0) {
                f = Size.m3938getMinDimensionimpl(drawScope.mo4647getSizeNHjbRc()) / 2.0f;
            }
            drawScope.mo4630drawCircleVaOC9Bg(j, f, (i2 & 4) != 0 ? drawScope.mo4646getCenterF1C5BW0() : j2, (i2 & 8) != 0 ? 1.0f : f2, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawOval-AsUm42w$default, reason: not valid java name */
        public static /* synthetic */ void m4725drawOvalAsUm42w$default(DrawScope drawScope, Brush brush, long j, long j2, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawOval-AsUm42w");
            }
            long jM3886getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m3886getZeroF1C5BW0() : j;
            drawScope.mo4636drawOvalAsUm42w(brush, jM3886getZeroF1C5BW0, (i2 & 4) != 0 ? m4699$private$offsetSizePENXr5M(drawScope, drawScope.mo4647getSizeNHjbRc(), jM3886getZeroF1C5BW0) : j2, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawOval-n-J9OG0$default, reason: not valid java name */
        public static /* synthetic */ void m4726drawOvalnJ9OG0$default(DrawScope drawScope, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawOval-n-J9OG0");
            }
            long jM3886getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m3886getZeroF1C5BW0() : j2;
            drawScope.mo4637drawOvalnJ9OG0(j, jM3886getZeroF1C5BW0, (i2 & 4) != 0 ? m4699$private$offsetSizePENXr5M(drawScope, drawScope.mo4647getSizeNHjbRc(), jM3886getZeroF1C5BW0) : j3, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawArc-illE91I$default, reason: not valid java name */
        public static /* synthetic */ void m4716drawArcillE91I$default(DrawScope drawScope, Brush brush, float f, float f2, boolean z, long j, long j2, float f3, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            DrawScope drawScope2;
            long jM4699$private$offsetSizePENXr5M;
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawArc-illE91I");
            }
            long jM3886getZeroF1C5BW0 = (i2 & 16) != 0 ? Offset.INSTANCE.m3886getZeroF1C5BW0() : j;
            if ((i2 & 32) != 0) {
                drawScope2 = drawScope;
                jM4699$private$offsetSizePENXr5M = m4699$private$offsetSizePENXr5M(drawScope2, drawScope.mo4647getSizeNHjbRc(), jM3886getZeroF1C5BW0);
            } else {
                drawScope2 = drawScope;
                jM4699$private$offsetSizePENXr5M = j2;
            }
            drawScope2.mo4627drawArcillE91I(brush, f, f2, z, jM3886getZeroF1C5BW0, jM4699$private$offsetSizePENXr5M, (i2 & 64) != 0 ? 1.0f : f3, (i2 & 128) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 256) != 0 ? null : colorFilter, (i2 & 512) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawArc-yD3GUKo$default, reason: not valid java name */
        public static /* synthetic */ void m4717drawArcyD3GUKo$default(DrawScope drawScope, long j, float f, float f2, boolean z, long j2, long j3, float f3, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            DrawScope drawScope2;
            long jM4699$private$offsetSizePENXr5M;
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawArc-yD3GUKo");
            }
            long jM3886getZeroF1C5BW0 = (i2 & 16) != 0 ? Offset.INSTANCE.m3886getZeroF1C5BW0() : j2;
            if ((i2 & 32) != 0) {
                drawScope2 = drawScope;
                jM4699$private$offsetSizePENXr5M = m4699$private$offsetSizePENXr5M(drawScope2, drawScope.mo4647getSizeNHjbRc(), jM3886getZeroF1C5BW0);
            } else {
                drawScope2 = drawScope;
                jM4699$private$offsetSizePENXr5M = j3;
            }
            drawScope2.mo4628drawArcyD3GUKo(j, f, f2, z, jM3886getZeroF1C5BW0, jM4699$private$offsetSizePENXr5M, (i2 & 64) != 0 ? 1.0f : f3, (i2 & 128) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 256) != 0 ? null : colorFilter, (i2 & 512) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawPath-LG529CI$default, reason: not valid java name */
        public static /* synthetic */ void m4728drawPathLG529CI$default(DrawScope drawScope, Path path, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPath-LG529CI");
            }
            if ((i2 & 4) != 0) {
                f = 1.0f;
            }
            float f2 = f;
            if ((i2 & 8) != 0) {
                drawStyle = Fill.INSTANCE;
            }
            DrawStyle drawStyle2 = drawStyle;
            if ((i2 & 16) != 0) {
                colorFilter = null;
            }
            drawScope.mo4639drawPathLG529CI(path, j, f2, drawStyle2, colorFilter, (i2 & 32) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawPath-GBMwjPU$default, reason: not valid java name */
        public static /* synthetic */ void m4727drawPathGBMwjPU$default(DrawScope drawScope, Path path, Brush brush, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPath-GBMwjPU");
            }
            if ((i2 & 4) != 0) {
                f = 1.0f;
            }
            float f2 = f;
            if ((i2 & 8) != 0) {
                drawStyle = Fill.INSTANCE;
            }
            DrawStyle drawStyle2 = drawStyle;
            if ((i2 & 16) != 0) {
                colorFilter = null;
            }
            ColorFilter colorFilter2 = colorFilter;
            if ((i2 & 32) != 0) {
                i = DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU();
            }
            drawScope.mo4638drawPathGBMwjPU(path, brush, f2, drawStyle2, colorFilter2, i);
        }

        /* JADX INFO: renamed from: drawPoints-F8ZwMP8$default, reason: not valid java name */
        public static /* synthetic */ void m4729drawPointsF8ZwMP8$default(DrawScope drawScope, List list, int i, long j, float f, int i2, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i3, int i4, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPoints-F8ZwMP8");
            }
            drawScope.mo4640drawPointsF8ZwMP8(list, i, j, (i4 & 8) != 0 ? 0.0f : f, (i4 & 16) != 0 ? StrokeCap.INSTANCE.m4531getButtKaPHkGw() : i2, (i4 & 32) != 0 ? null : pathEffect, (i4 & 64) != 0 ? 1.0f : f2, (i4 & 128) != 0 ? null : colorFilter, (i4 & 256) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i3);
        }

        /* JADX INFO: renamed from: drawPoints-Gsft0Ws$default, reason: not valid java name */
        public static /* synthetic */ void m4730drawPointsGsft0Ws$default(DrawScope drawScope, List list, int i, Brush brush, float f, int i2, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i3, int i4, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPoints-Gsft0Ws");
            }
            drawScope.mo4641drawPointsGsft0Ws(list, i, brush, (i4 & 8) != 0 ? 0.0f : f, (i4 & 16) != 0 ? StrokeCap.INSTANCE.m4531getButtKaPHkGw() : i2, (i4 & 32) != 0 ? null : pathEffect, (i4 & 64) != 0 ? 1.0f : f2, (i4 & 128) != 0 ? null : colorFilter, (i4 & 256) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i3);
        }

        /* JADX INFO: renamed from: record-JVtK1S4$default, reason: not valid java name */
        public static /* synthetic */ void m4735recordJVtK1S4$default(DrawScope drawScope, GraphicsLayer graphicsLayer, long j, Function1 function1, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: record-JVtK1S4");
            }
            if ((i & 1) != 0) {
                j = IntSizeKt.m7076toIntSizeuvyYCjk(drawScope.mo4647getSizeNHjbRc());
            }
            drawScope.mo4648recordJVtK1S4(graphicsLayer, j, function1);
        }

        /* JADX INFO: renamed from: $default$record-JVtK1S4, reason: not valid java name */
        public static void m4698$default$recordJVtK1S4(final DrawScope _this, GraphicsLayer graphicsLayer, long j, final Function1 function1) {
            graphicsLayer.m4829recordmLhObY(_this, _this.getLayoutDirection(), j, new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.graphics.drawscope.DrawScope$record$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope drawScope) {
                    DrawScope drawScope2 = _this;
                    Density density = drawScope.getDrawContext().getDensity();
                    LayoutDirection layoutDirection = drawScope.getDrawContext().getLayoutDirection();
                    Canvas canvas = drawScope.getDrawContext().getCanvas();
                    long jMo4654getSizeNHjbRc = drawScope.getDrawContext().mo4654getSizeNHjbRc();
                    GraphicsLayer graphicsLayer2 = drawScope.getDrawContext().getGraphicsLayer();
                    Function1<DrawScope, Unit> function2 = function1;
                    Density density2 = drawScope2.getDrawContext().getDensity();
                    LayoutDirection layoutDirection2 = drawScope2.getDrawContext().getLayoutDirection();
                    Canvas canvas2 = drawScope2.getDrawContext().getCanvas();
                    long jMo4654getSizeNHjbRc2 = drawScope2.getDrawContext().mo4654getSizeNHjbRc();
                    GraphicsLayer graphicsLayer3 = drawScope2.getDrawContext().getGraphicsLayer();
                    DrawContext drawContext = drawScope2.getDrawContext();
                    drawContext.setDensity(density);
                    drawContext.setLayoutDirection(layoutDirection);
                    drawContext.setCanvas(canvas);
                    drawContext.mo4655setSizeuvyYCjk(jMo4654getSizeNHjbRc);
                    drawContext.setGraphicsLayer(graphicsLayer2);
                    canvas.save();
                    try {
                        function2.invoke(drawScope2);
                    } finally {
                        canvas.restore();
                        DrawContext drawContext2 = drawScope2.getDrawContext();
                        drawContext2.setDensity(density2);
                        drawContext2.setLayoutDirection(layoutDirection2);
                        drawContext2.setCanvas(canvas2);
                        drawContext2.mo4655setSizeuvyYCjk(jMo4654getSizeNHjbRc2);
                        drawContext2.setGraphicsLayer(graphicsLayer3);
                    }
                }
            });
        }

        /* JADX INFO: renamed from: $private$offsetSize-PENXr5M, reason: not valid java name */
        public static long m4699$private$offsetSizePENXr5M(DrawScope _this, long j, long j2) {
            float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) - Float.intBitsToFloat((int) (j2 >> 32));
            float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L)) - Float.intBitsToFloat((int) (j2 & 4294967295L));
            return Size.m3930constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L));
        }
    }

    /* JADX INFO: compiled from: DrawScope.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m4762roundToPxR2X_6o(DrawScope drawScope, long j) {
            return Density.CC.m6854$default$roundToPxR2X_6o(drawScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m4763roundToPx0680j_4(DrawScope drawScope, float f) {
            return Density.CC.m6855$default$roundToPx0680j_4(drawScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m4764toDpGaN1DYA(DrawScope drawScope, long j) {
            return FontScaling.CC.m6999$default$toDpGaN1DYA(drawScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4765toDpu2uoSUM(DrawScope drawScope, float f) {
            return Density.CC.m6856$default$toDpu2uoSUM(drawScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4766toDpu2uoSUM(DrawScope drawScope, int i) {
            return Density.CC.m6857$default$toDpu2uoSUM((Density) drawScope, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m4767toDpSizekrfVVM(DrawScope drawScope, long j) {
            return Density.CC.m6858$default$toDpSizekrfVVM(drawScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m4768toPxR2X_6o(DrawScope drawScope, long j) {
            return Density.CC.m6859$default$toPxR2X_6o(drawScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m4769toPx0680j_4(DrawScope drawScope, float f) {
            return Density.CC.m6860$default$toPx0680j_4(drawScope, f);
        }

        @Deprecated
        public static Rect toRect(DrawScope drawScope, DpRect dpRect) {
            return Density.CC.$default$toRect(drawScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m4770toSizeXkaWNTQ(DrawScope drawScope, long j) {
            return Density.CC.m6861$default$toSizeXkaWNTQ(drawScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m4771toSp0xMU5do(DrawScope drawScope, float f) {
            return FontScaling.CC.m7000$default$toSp0xMU5do(drawScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4772toSpkPz2Gy4(DrawScope drawScope, float f) {
            return Density.CC.m6862$default$toSpkPz2Gy4(drawScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4773toSpkPz2Gy4(DrawScope drawScope, int i) {
            return Density.CC.m6863$default$toSpkPz2Gy4((Density) drawScope, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: getCenter-F1C5BW0, reason: not valid java name */
        public static long m4758getCenterF1C5BW0(DrawScope drawScope) {
            return CC.m4696$default$getCenterF1C5BW0(drawScope);
        }

        @Deprecated
        /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
        public static long m4759getSizeNHjbRc(DrawScope drawScope) {
            return CC.m4697$default$getSizeNHjbRc(drawScope);
        }

        @Deprecated
        /* JADX INFO: renamed from: drawImage-AZ2fEMs, reason: not valid java name */
        public static void m4743drawImageAZ2fEMs(DrawScope drawScope, ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2) {
            CC.m4721drawImageAZ2fEMs$default(drawScope, imageBitmap, j, j2, j3, j4, f, drawStyle, colorFilter, i, 0, 512, null);
        }

        @Deprecated
        /* JADX INFO: renamed from: record-JVtK1S4, reason: not valid java name */
        public static void m4760recordJVtK1S4(DrawScope drawScope, GraphicsLayer graphicsLayer, long j, Function1<? super DrawScope, Unit> function1) {
            CC.m4698$default$recordJVtK1S4(drawScope, graphicsLayer, j, function1);
        }
    }

    /* JADX INFO: compiled from: DrawScope.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\n¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000b\u0010\u0007¨\u0006\f"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope$Companion;", "", "<init>", "()V", "DefaultBlendMode", "Landroidx/compose/ui/graphics/BlendMode;", "getDefaultBlendMode-0nO6VwU", "()I", "I", "DefaultFilterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "getDefaultFilterQuality-f-v9h1I", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final int DefaultBlendMode = BlendMode.INSTANCE.m4093getSrcOver0nO6VwU();
        private static final int DefaultFilterQuality = FilterQuality.INSTANCE.m4252getLowfv9h1I();

        private Companion() {
        }

        /* JADX INFO: renamed from: getDefaultBlendMode-0nO6VwU, reason: not valid java name */
        public final int m4736getDefaultBlendMode0nO6VwU() {
            return DefaultBlendMode;
        }

        /* JADX INFO: renamed from: getDefaultFilterQuality-f-v9h1I, reason: not valid java name */
        public final int m4737getDefaultFilterQualityfv9h1I() {
            return DefaultFilterQuality;
        }
    }
}
