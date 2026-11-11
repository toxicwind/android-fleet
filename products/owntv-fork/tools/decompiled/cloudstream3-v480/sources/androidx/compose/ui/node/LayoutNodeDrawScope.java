package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LayoutNodeDrawScope.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0088\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J4\u0010\r\u001a\u00020\f*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u0012¢\u0006\u0002\b\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001c\u0010\u0016\u001a\u00020\f*\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u000eJ9\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\u001e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0004\b\u001f\u0010 J9\u0010!\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0004\b\"\u0010#Jd\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\u001b2\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\b5\u00106Jd\u0010$\u001a\u00020\f2\u0006\u00107\u001a\u0002082\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\u001b2\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\b9\u0010:JL\u0010;\u001a\u00020\f2\u0006\u0010%\u001a\u00020&2\u0006\u0010<\u001a\u00020(2\u0006\u0010=\u001a\u00020-2\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\b>\u0010?JL\u0010;\u001a\u00020\f2\u0006\u00107\u001a\u0002082\u0006\u0010<\u001a\u00020(2\u0006\u0010=\u001a\u00020-2\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\b@\u0010AJD\u0010B\u001a\u00020\f2\u0006\u0010C\u001a\u00020D2\u0006\u0010,\u001a\u00020-2\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\bE\u0010FJ\\\u0010B\u001a\u00020\f2\u0006\u0010C\u001a\u00020D2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\u00102\u0006\u0010J\u001a\u00020H2\u0006\u0010K\u001a\u00020\u00102\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0097\u0001¢\u0006\u0004\bL\u0010MJd\u0010B\u001a\u00020\f2\u0006\u0010C\u001a\u00020D2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\u00102\u0006\u0010J\u001a\u00020H2\u0006\u0010K\u001a\u00020\u00102\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002042\u0006\u0010N\u001a\u00020OH\u0096\u0001¢\u0006\u0004\bP\u0010QJ^\u0010R\u001a\u00020\f2\u0006\u0010%\u001a\u00020&2\u0006\u0010S\u001a\u00020-2\u0006\u0010T\u001a\u00020-2\u0006\u0010U\u001a\u00020(2\u0006\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010Y2\b\b\u0001\u0010.\u001a\u00020(2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\bZ\u0010[J^\u0010R\u001a\u00020\f2\u0006\u00107\u001a\u0002082\u0006\u0010S\u001a\u00020-2\u0006\u0010T\u001a\u00020-2\u0006\u0010U\u001a\u00020(2\u0006\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010Y2\b\b\u0001\u0010.\u001a\u00020(2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\b\\\u0010]JL\u0010^\u001a\u00020\f2\u0006\u0010%\u001a\u00020&2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\u001b2\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\b_\u0010`JL\u0010^\u001a\u00020\f2\u0006\u00107\u001a\u0002082\u0006\u0010,\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\u001b2\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\ba\u0010bJD\u0010c\u001a\u00020\f2\u0006\u0010d\u001a\u00020e2\u0006\u0010%\u001a\u00020&2\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\bf\u0010gJD\u0010c\u001a\u00020\f2\u0006\u0010d\u001a\u00020e2\u0006\u00107\u001a\u0002082\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\bh\u0010iJd\u0010j\u001a\u00020\f2\f\u0010k\u001a\b\u0012\u0004\u0012\u00020-0l2\u0006\u0010m\u001a\u00020n2\u0006\u0010%\u001a\u00020&2\u0006\u0010U\u001a\u00020(2\u0006\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010Y2\b\b\u0001\u0010.\u001a\u00020(2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\bo\u0010pJd\u0010j\u001a\u00020\f2\f\u0010k\u001a\b\u0012\u0004\u0012\u00020-0l2\u0006\u0010m\u001a\u00020n2\u0006\u00107\u001a\u0002082\u0006\u0010U\u001a\u00020(2\u0006\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010Y2\b\b\u0001\u0010.\u001a\u00020(2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\bq\u0010rJL\u0010s\u001a\u00020\f2\u0006\u0010%\u001a\u00020&2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\u001b2\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\bt\u0010`JL\u0010s\u001a\u00020\f2\u0006\u00107\u001a\u0002082\u0006\u0010,\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\u001b2\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\bu\u0010bJT\u0010v\u001a\u00020\f2\u0006\u0010%\u001a\u00020&2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\u001b2\u0006\u0010w\u001a\u00020x2\b\b\u0001\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\by\u0010zJT\u0010v\u001a\u00020\f2\u0006\u00107\u001a\u0002082\u0006\u0010,\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\u001b2\u0006\u0010w\u001a\u00020x2\u0006\u0010/\u001a\u0002002\b\b\u0001\u0010.\u001a\u00020(2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0096\u0001¢\u0006\u0004\b{\u0010|J\u0016\u0010}\u001a\u00020~*\u00020\u007fH\u0097\u0001¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001J\u0017\u0010}\u001a\u00020~*\u00030\u0082\u0001H\u0097\u0001¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J\u0017\u0010\u0085\u0001\u001a\u00020\u007f*\u00020~H\u0097\u0001¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u0017\u0010\u0085\u0001\u001a\u00020\u007f*\u00020(H\u0097\u0001¢\u0006\u0006\b\u0086\u0001\u0010\u0088\u0001J\u0018\u0010\u0085\u0001\u001a\u00020\u007f*\u00030\u0082\u0001H\u0097\u0001¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J\u0018\u0010\u008b\u0001\u001a\u00030\u008c\u0001*\u00020\u001bH\u0097\u0001¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001J\u0017\u0010\u008f\u0001\u001a\u00020(*\u00020\u007fH\u0097\u0001¢\u0006\u0006\b\u0090\u0001\u0010\u0088\u0001J\u0018\u0010\u008f\u0001\u001a\u00020(*\u00030\u0082\u0001H\u0097\u0001¢\u0006\u0006\b\u0091\u0001\u0010\u008a\u0001J\u0010\u0010\u0092\u0001\u001a\u00030\u0093\u0001*\u00030\u0094\u0001H\u0097\u0001J\u0018\u0010\u0095\u0001\u001a\u00020\u001b*\u00030\u008c\u0001H\u0097\u0001¢\u0006\u0006\b\u0096\u0001\u0010\u008e\u0001J\u0018\u0010\u0097\u0001\u001a\u00030\u0082\u0001*\u00020~H\u0097\u0001¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001J\u0018\u0010\u0097\u0001\u001a\u00030\u0082\u0001*\u00020(H\u0097\u0001¢\u0006\u0006\b\u0098\u0001\u0010\u009a\u0001J\u0018\u0010\u0097\u0001\u001a\u00030\u0082\u0001*\u00020\u007fH\u0097\u0001¢\u0006\u0006\b\u009b\u0001\u0010\u009a\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010=\u001a\u00020-8VX\u0096\u0005¢\u0006\b\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0017\u0010\u009e\u0001\u001a\u00020(8\u0016X\u0097\u0005¢\u0006\b\u001a\u0006\b\u009f\u0001\u0010 \u0001R\u0016\u0010¡\u0001\u001a\u00030¢\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b£\u0001\u0010¤\u0001R\u0017\u0010¥\u0001\u001a\u00020(8\u0016X\u0097\u0005¢\u0006\b\u001a\u0006\b¦\u0001\u0010 \u0001R\u0016\u0010§\u0001\u001a\u00030¨\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b©\u0001\u0010ª\u0001R\u0016\u0010\u000f\u001a\u00020\u001b8VX\u0096\u0005¢\u0006\b\u001a\u0006\b«\u0001\u0010\u009d\u0001¨\u0006¬\u0001"}, d2 = {"Landroidx/compose/ui/node/LayoutNodeDrawScope;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "canvasDrawScope", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "<init>", "(Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;)V", "getCanvasDrawScope", "()Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "drawNode", "Landroidx/compose/ui/node/DrawModifierNode;", "drawContent", "", "record", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/unit/IntSize;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "record-JVtK1S4", "(Landroidx/compose/ui/graphics/layer/GraphicsLayer;JLkotlin/jvm/functions/Function1;)V", "performDraw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "layer", "draw", "Landroidx/compose/ui/geometry/Size;", "coordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "Landroidx/compose/ui/Modifier$Node;", "draw-eZhPAX0$ui_release", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/node/NodeCoordinator;Landroidx/compose/ui/Modifier$Node;Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "drawDirect", "drawDirect-eZhPAX0$ui_release", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/node/NodeCoordinator;Landroidx/compose/ui/node/DrawModifierNode;Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "drawArc", "brush", "Landroidx/compose/ui/graphics/Brush;", "startAngle", "", "sweepAngle", "useCenter", "", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "alpha", "style", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "drawArc-illE91I", "(Landroidx/compose/ui/graphics/Brush;FFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "color", "Landroidx/compose/ui/graphics/Color;", "drawArc-yD3GUKo", "(JFFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawCircle", "radius", TtmlNode.CENTER, "drawCircle-V9BoPsw", "(Landroidx/compose/ui/graphics/Brush;FJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawCircle-VaOC9Bg", "(JFJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawImage", "image", "Landroidx/compose/ui/graphics/ImageBitmap;", "drawImage-gbVJVH8", "(Landroidx/compose/ui/graphics/ImageBitmap;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "srcOffset", "Landroidx/compose/ui/unit/IntOffset;", "srcSize", "dstOffset", "dstSize", "drawImage-9jGpkUE", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "drawImage-AZ2fEMs", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;II)V", "drawLine", "start", TtmlNode.END, "strokeWidth", "cap", "Landroidx/compose/ui/graphics/StrokeCap;", "pathEffect", "Landroidx/compose/ui/graphics/PathEffect;", "drawLine-1RTmtNc", "(Landroidx/compose/ui/graphics/Brush;JJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawLine-NGM6Ib0", "(JJJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawOval", "drawOval-AsUm42w", "(Landroidx/compose/ui/graphics/Brush;JJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawOval-n-J9OG0", "(JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPath", "path", "Landroidx/compose/ui/graphics/Path;", "drawPath-GBMwjPU", "(Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPath-LG529CI", "(Landroidx/compose/ui/graphics/Path;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPoints", "points", "", "pointMode", "Landroidx/compose/ui/graphics/PointMode;", "drawPoints-Gsft0Ws", "(Ljava/util/List;ILandroidx/compose/ui/graphics/Brush;FILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawPoints-F8ZwMP8", "(Ljava/util/List;IJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawRect", "drawRect-AsUm42w", "drawRect-n-J9OG0", "drawRoundRect", "cornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "drawRoundRect-ZuiqVtQ", "(Landroidx/compose/ui/graphics/Brush;JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawRoundRect-u-Aw5IA", "(JJJJLandroidx/compose/ui/graphics/drawscope/DrawStyle;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "roundToPx", "", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-u2uoSUM", "(I)F", "(F)F", "toDp-GaN1DYA", "(J)F", "toDpSize", "Landroidx/compose/ui/unit/DpSize;", "toDpSize-k-rfVVM", "(J)J", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "toSize-XkaWNTQ", "toSp", "toSp-kPz2Gy4", "(I)J", "(F)J", "toSp-0xMU5do", "getCenter-F1C5BW0", "()J", "density", "getDensity", "()F", "drawContext", "Landroidx/compose/ui/graphics/drawscope/DrawContext;", "getDrawContext", "()Landroidx/compose/ui/graphics/drawscope/DrawContext;", "fontScale", "getFontScale", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "getSize-NH-jbRc", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LayoutNodeDrawScope implements DrawScope, ContentDrawScope {
    public static final int $stable = 0;
    private final CanvasDrawScope canvasDrawScope;
    private DrawModifierNode drawNode;

    /* JADX WARN: Multi-variable type inference failed */
    public LayoutNodeDrawScope() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawArc-illE91I */
    public void mo4627drawArcillE91I(Brush brush, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4627drawArcillE91I(brush, startAngle, sweepAngle, useCenter, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawArc-yD3GUKo */
    public void mo4628drawArcyD3GUKo(long color, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4628drawArcyD3GUKo(color, startAngle, sweepAngle, useCenter, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawCircle-V9BoPsw */
    public void mo4629drawCircleV9BoPsw(Brush brush, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4629drawCircleV9BoPsw(brush, radius, center, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawCircle-VaOC9Bg */
    public void mo4630drawCircleVaOC9Bg(long color, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4630drawCircleVaOC9Bg(color, radius, center, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Prefer usage of drawImage that consumes an optional FilterQuality parameter", replaceWith = @ReplaceWith(expression = "drawImage(image, srcOffset, srcSize, dstOffset, dstSize, alpha, style, colorFilter, blendMode, FilterQuality.Low)", imports = {"androidx.compose.ui.graphics.drawscope", "androidx.compose.ui.graphics.FilterQuality"}))
    /* JADX INFO: renamed from: drawImage-9jGpkUE */
    public /* synthetic */ void mo4631drawImage9jGpkUE(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4631drawImage9jGpkUE(image, srcOffset, srcSize, dstOffset, dstSize, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawImage-AZ2fEMs */
    public void mo4632drawImageAZ2fEMs(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode, int filterQuality) {
        this.canvasDrawScope.mo4632drawImageAZ2fEMs(image, srcOffset, srcSize, dstOffset, dstSize, alpha, style, colorFilter, blendMode, filterQuality);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawImage-gbVJVH8 */
    public void mo4633drawImagegbVJVH8(ImageBitmap image, long topLeft, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4633drawImagegbVJVH8(image, topLeft, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawLine-1RTmtNc */
    public void mo4634drawLine1RTmtNc(Brush brush, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4634drawLine1RTmtNc(brush, start, end, strokeWidth, cap, pathEffect, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawLine-NGM6Ib0 */
    public void mo4635drawLineNGM6Ib0(long color, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4635drawLineNGM6Ib0(color, start, end, strokeWidth, cap, pathEffect, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawOval-AsUm42w */
    public void mo4636drawOvalAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4636drawOvalAsUm42w(brush, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawOval-n-J9OG0 */
    public void mo4637drawOvalnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4637drawOvalnJ9OG0(color, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPath-GBMwjPU */
    public void mo4638drawPathGBMwjPU(Path path, Brush brush, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4638drawPathGBMwjPU(path, brush, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPath-LG529CI */
    public void mo4639drawPathLG529CI(Path path, long color, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4639drawPathLG529CI(path, color, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPoints-F8ZwMP8 */
    public void mo4640drawPointsF8ZwMP8(List<Offset> points, int pointMode, long color, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4640drawPointsF8ZwMP8(points, pointMode, color, strokeWidth, cap, pathEffect, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPoints-Gsft0Ws */
    public void mo4641drawPointsGsft0Ws(List<Offset> points, int pointMode, Brush brush, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4641drawPointsGsft0Ws(points, pointMode, brush, strokeWidth, cap, pathEffect, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRect-AsUm42w */
    public void mo4642drawRectAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4642drawRectAsUm42w(brush, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRect-n-J9OG0 */
    public void mo4643drawRectnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4643drawRectnJ9OG0(color, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRoundRect-ZuiqVtQ */
    public void mo4644drawRoundRectZuiqVtQ(Brush brush, long topLeft, long size, long cornerRadius, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4644drawRoundRectZuiqVtQ(brush, topLeft, size, cornerRadius, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRoundRect-u-Aw5IA */
    public void mo4645drawRoundRectuAw5IA(long color, long topLeft, long size, long cornerRadius, DrawStyle style, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4645drawRoundRectuAw5IA(color, topLeft, size, cornerRadius, style, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: getCenter-F1C5BW0 */
    public long mo4646getCenterF1C5BW0() {
        return this.canvasDrawScope.mo4646getCenterF1C5BW0();
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.canvasDrawScope.getDensity();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public DrawContext getDrawContext() {
        return this.canvasDrawScope.getDrawContext();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.canvasDrawScope.getFontScale();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public LayoutDirection getLayoutDirection() {
        return this.canvasDrawScope.getLayoutDirection();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: getSize-NH-jbRc */
    public long mo4647getSizeNHjbRc() {
        return this.canvasDrawScope.mo4647getSizeNHjbRc();
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    public int mo399roundToPxR2X_6o(long j) {
        return this.canvasDrawScope.mo399roundToPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public int mo400roundToPx0680j_4(float f) {
        return this.canvasDrawScope.mo400roundToPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public float mo401toDpGaN1DYA(long j) {
        return this.canvasDrawScope.mo401toDpGaN1DYA(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public float mo402toDpu2uoSUM(float f) {
        return this.canvasDrawScope.mo402toDpu2uoSUM(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public float mo403toDpu2uoSUM(int i) {
        return this.canvasDrawScope.mo403toDpu2uoSUM(i);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public long mo404toDpSizekrfVVM(long j) {
        return this.canvasDrawScope.mo404toDpSizekrfVVM(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public float mo405toPxR2X_6o(long j) {
        return this.canvasDrawScope.mo405toPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public float mo406toPx0680j_4(float f) {
        return this.canvasDrawScope.mo406toPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    public Rect toRect(DpRect dpRect) {
        return this.canvasDrawScope.toRect(dpRect);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public long mo407toSizeXkaWNTQ(long j) {
        return this.canvasDrawScope.mo407toSizeXkaWNTQ(j);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public long mo408toSp0xMU5do(float f) {
        return this.canvasDrawScope.mo408toSp0xMU5do(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public long mo409toSpkPz2Gy4(float f) {
        return this.canvasDrawScope.mo409toSpkPz2Gy4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public long mo410toSpkPz2Gy4(int i) {
        return this.canvasDrawScope.mo410toSpkPz2Gy4(i);
    }

    public LayoutNodeDrawScope(CanvasDrawScope canvasDrawScope) {
        this.canvasDrawScope = canvasDrawScope;
    }

    public /* synthetic */ LayoutNodeDrawScope(CanvasDrawScope canvasDrawScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new CanvasDrawScope() : canvasDrawScope);
    }

    public final CanvasDrawScope getCanvasDrawScope() {
        return this.canvasDrawScope;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v8 */
    @Override // androidx.compose.ui.graphics.drawscope.ContentDrawScope
    public void drawContent() {
        Canvas canvas = getDrawContext().getCanvas();
        DrawModifierNode drawModifierNode = this.drawNode;
        if (drawModifierNode != null) {
            DrawModifierNode drawModifierNode2 = drawModifierNode;
            Modifier.Node nodeNextDrawNode = LayoutNodeDrawScopeKt.nextDrawNode(drawModifierNode2);
            if (nodeNextDrawNode == 0) {
                NodeCoordinator nodeCoordinatorM5734requireCoordinator64DMado = DelegatableNodeKt.m5734requireCoordinator64DMado(drawModifierNode2, NodeKind.m5881constructorimpl(4));
                if (nodeCoordinatorM5734requireCoordinator64DMado.getTail() == drawModifierNode.getNode()) {
                    nodeCoordinatorM5734requireCoordinator64DMado = nodeCoordinatorM5734requireCoordinator64DMado.getWrapped();
                    Intrinsics.checkNotNull(nodeCoordinatorM5734requireCoordinator64DMado);
                }
                nodeCoordinatorM5734requireCoordinator64DMado.performDraw(canvas, getDrawContext().getGraphicsLayer());
                return;
            }
            int iM5881constructorimpl = NodeKind.m5881constructorimpl(4);
            MutableVector mutableVector = null;
            while (nodeNextDrawNode != 0) {
                if (nodeNextDrawNode instanceof DrawModifierNode) {
                    performDraw((DrawModifierNode) nodeNextDrawNode, canvas, getDrawContext().getGraphicsLayer());
                } else if ((nodeNextDrawNode.getKindSet() & iM5881constructorimpl) != 0 && (nodeNextDrawNode instanceof DelegatingNode)) {
                    Modifier.Node delegate = ((DelegatingNode) nodeNextDrawNode).getDelegate();
                    int i = 0;
                    nodeNextDrawNode = nodeNextDrawNode;
                    while (delegate != null) {
                        if ((delegate.getKindSet() & iM5881constructorimpl) != 0) {
                            i++;
                            if (i == 1) {
                                nodeNextDrawNode = delegate;
                            } else {
                                if (mutableVector == null) {
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (nodeNextDrawNode != 0) {
                                    if (mutableVector != null) {
                                        mutableVector.add(nodeNextDrawNode);
                                    }
                                    nodeNextDrawNode = 0;
                                }
                                if (mutableVector != null) {
                                    mutableVector.add(delegate);
                                }
                            }
                        }
                        delegate = delegate.getChild();
                        nodeNextDrawNode = nodeNextDrawNode;
                    }
                    if (i == 1) {
                    }
                }
                nodeNextDrawNode = DelegatableNodeKt.pop(mutableVector);
            }
            return;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Attempting to drawContent for a `null` node. This usually means that a call to ContentDrawScope#drawContent() has been captured inside a lambda, and is being invoked outside of the draw pass. Capturing the scope this way is unsupported - if you are trying to record drawContent with graphicsLayer.record(), make sure you are using the GraphicsLayer#record function within DrawScope, instead of the member function on GraphicsLayer.");
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: record-JVtK1S4 */
    public void mo4648recordJVtK1S4(GraphicsLayer graphicsLayer, long j, final Function1<? super DrawScope, Unit> function1) {
        final DrawModifierNode drawModifierNode = this.drawNode;
        graphicsLayer.m4829recordmLhObY(this, getLayoutDirection(), j, new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.node.LayoutNodeDrawScope$record$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) throws Throwable {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v0, types: [androidx.compose.ui.node.DrawModifierNode] */
            /* JADX WARN: Type inference failed for: r2v1 */
            /* JADX WARN: Type inference failed for: r2v2, types: [androidx.compose.ui.node.DrawModifierNode] */
            /* JADX WARN: Type inference failed for: r2v3 */
            /* JADX WARN: Type inference failed for: r2v4, types: [androidx.compose.ui.graphics.drawscope.DrawContext] */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope drawScope) throws Throwable {
                ?? drawContext = this.this$0.drawNode;
                this.this$0.drawNode = drawModifierNode;
                try {
                    LayoutNodeDrawScope layoutNodeDrawScope = this.this$0;
                    Density density = drawScope.getDrawContext().getDensity();
                    LayoutDirection layoutDirection = drawScope.getDrawContext().getLayoutDirection();
                    Canvas canvas = drawScope.getDrawContext().getCanvas();
                    long jMo4654getSizeNHjbRc = drawScope.getDrawContext().mo4654getSizeNHjbRc();
                    GraphicsLayer graphicsLayer2 = drawScope.getDrawContext().getGraphicsLayer();
                    Function1<DrawScope, Unit> function2 = function1;
                    Density density2 = layoutNodeDrawScope.getDrawContext().getDensity();
                    LayoutDirection layoutDirection2 = layoutNodeDrawScope.getDrawContext().getLayoutDirection();
                    Canvas canvas2 = layoutNodeDrawScope.getDrawContext().getCanvas();
                    long jMo4654getSizeNHjbRc2 = layoutNodeDrawScope.getDrawContext().mo4654getSizeNHjbRc();
                    GraphicsLayer graphicsLayer3 = layoutNodeDrawScope.getDrawContext().getGraphicsLayer();
                    try {
                        drawContext = layoutNodeDrawScope.getDrawContext();
                        drawContext.setDensity(density);
                        drawContext.setLayoutDirection(layoutDirection);
                        drawContext.setCanvas(canvas);
                        drawContext.mo4655setSizeuvyYCjk(jMo4654getSizeNHjbRc);
                        drawContext.setGraphicsLayer(graphicsLayer2);
                        canvas.save();
                        try {
                            function2.invoke(layoutNodeDrawScope);
                            canvas.restore();
                            DrawContext drawContext2 = layoutNodeDrawScope.getDrawContext();
                            drawContext2.setDensity(density2);
                            drawContext2.setLayoutDirection(layoutDirection2);
                            drawContext2.setCanvas(canvas2);
                            drawContext2.mo4655setSizeuvyYCjk(jMo4654getSizeNHjbRc2);
                            drawContext2.setGraphicsLayer(graphicsLayer3);
                            this.this$0.drawNode = drawContext;
                        } catch (Throwable th) {
                            canvas.restore();
                            DrawContext drawContext3 = layoutNodeDrawScope.getDrawContext();
                            drawContext3.setDensity(density2);
                            drawContext3.setLayoutDirection(layoutDirection2);
                            drawContext3.setCanvas(canvas2);
                            drawContext3.mo4655setSizeuvyYCjk(jMo4654getSizeNHjbRc2);
                            drawContext3.setGraphicsLayer(graphicsLayer3);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        drawContext = drawContext;
                        this.this$0.drawNode = drawContext;
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        });
    }

    public final void performDraw(DrawModifierNode drawModifierNode, Canvas canvas, GraphicsLayer graphicsLayer) {
        NodeCoordinator nodeCoordinatorM5734requireCoordinator64DMado = DelegatableNodeKt.m5734requireCoordinator64DMado(drawModifierNode, NodeKind.m5881constructorimpl(4));
        nodeCoordinatorM5734requireCoordinator64DMado.getLayoutNode().getMDrawScope$ui_release().m5804drawDirecteZhPAX0$ui_release(canvas, IntSizeKt.m7077toSizeozmzZPI(nodeCoordinatorM5734requireCoordinator64DMado.mo5583getSizeYbymL2g()), nodeCoordinatorM5734requireCoordinator64DMado, drawModifierNode, graphicsLayer);
    }

    /* JADX INFO: renamed from: drawDirect-eZhPAX0$ui_release, reason: not valid java name */
    public final void m5804drawDirecteZhPAX0$ui_release(Canvas canvas, long size, NodeCoordinator coordinator, DrawModifierNode drawNode, GraphicsLayer layer) {
        DrawModifierNode drawModifierNode = this.drawNode;
        this.drawNode = drawNode;
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        LayoutDirection layoutDirection = coordinator.getLayoutDirection();
        Density density = canvasDrawScope.getDrawContext().getDensity();
        LayoutDirection layoutDirection2 = canvasDrawScope.getDrawContext().getLayoutDirection();
        Canvas canvas2 = canvasDrawScope.getDrawContext().getCanvas();
        long jMo4654getSizeNHjbRc = canvasDrawScope.getDrawContext().mo4654getSizeNHjbRc();
        GraphicsLayer graphicsLayer = canvasDrawScope.getDrawContext().getGraphicsLayer();
        DrawContext drawContext = canvasDrawScope.getDrawContext();
        drawContext.setDensity(coordinator);
        drawContext.setLayoutDirection(layoutDirection);
        drawContext.setCanvas(canvas);
        drawContext.mo4655setSizeuvyYCjk(size);
        drawContext.setGraphicsLayer(layer);
        canvas.save();
        try {
            drawNode.draw(this);
            canvas.restore();
            DrawContext drawContext2 = canvasDrawScope.getDrawContext();
            drawContext2.setDensity(density);
            drawContext2.setLayoutDirection(layoutDirection2);
            drawContext2.setCanvas(canvas2);
            drawContext2.mo4655setSizeuvyYCjk(jMo4654getSizeNHjbRc);
            drawContext2.setGraphicsLayer(graphicsLayer);
            this.drawNode = drawModifierNode;
        } catch (Throwable th) {
            canvas.restore();
            DrawContext drawContext3 = canvasDrawScope.getDrawContext();
            drawContext3.setDensity(density);
            drawContext3.setLayoutDirection(layoutDirection2);
            drawContext3.setCanvas(canvas2);
            drawContext3.mo4655setSizeuvyYCjk(jMo4654getSizeNHjbRc);
            drawContext3.setGraphicsLayer(graphicsLayer);
            throw th;
        }
    }

    /* JADX INFO: renamed from: draw-eZhPAX0$ui_release, reason: not valid java name */
    public final void m5803draweZhPAX0$ui_release(Canvas canvas, long size, NodeCoordinator coordinator, Modifier.Node drawNode, GraphicsLayer layer) {
        int iM5881constructorimpl = NodeKind.m5881constructorimpl(4);
        Modifier.Node nodePop = drawNode;
        MutableVector mutableVector = null;
        while (nodePop != null) {
            if (nodePop instanceof DrawModifierNode) {
                m5804drawDirecteZhPAX0$ui_release(canvas, size, coordinator, (DrawModifierNode) nodePop, layer);
            } else if ((nodePop.getKindSet() & iM5881constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                int i = 0;
                for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                    if ((delegate.getKindSet() & iM5881constructorimpl) != 0) {
                        i++;
                        if (i == 1) {
                            nodePop = delegate;
                        } else {
                            if (mutableVector == null) {
                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                            }
                            if (nodePop != null) {
                                if (mutableVector != null) {
                                    mutableVector.add(nodePop);
                                }
                                nodePop = null;
                            }
                            if (mutableVector != null) {
                                mutableVector.add(delegate);
                            }
                        }
                    }
                }
                if (i == 1) {
                }
            }
            nodePop = DelegatableNodeKt.pop(mutableVector);
        }
    }
}
