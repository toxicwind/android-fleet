package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.tokens.ProgressIndicatorTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ProgressIndicator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0010\u001aR\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/H\u0007ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a\\\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00102\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b3\u00104\u001a0\u0010%\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001aD\u0010%\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/H\u0007ø\u0001\u0000¢\u0006\u0004\b7\u00108\u001a8\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00012\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001aL\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00012\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/H\u0007ø\u0001\u0000¢\u0006\u0004\b0\u0010;\u001aH\u0010<\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/H\u0007ø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001am\u0010<\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00102\u001a\u00020\u00052\u0019\b\u0002\u0010?\u001a\u0013\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020&0@¢\u0006\u0002\bBH\u0007ø\u0001\u0000¢\u0006\u0004\bC\u0010D\u001a0\u0010<\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+H\u0007ø\u0001\u0000¢\u0006\u0004\bE\u0010F\u001a:\u0010<\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/H\u0007ø\u0001\u0000¢\u0006\u0004\bG\u0010H\u001aD\u0010<\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00102\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\bI\u0010J\u001a8\u0010<\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00012\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+H\u0007ø\u0001\u0000¢\u0006\u0004\bK\u0010L\u001aB\u0010<\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00012\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/H\u0007ø\u0001\u0000¢\u0006\u0004\b=\u0010M\u001a6\u0010N\u001a\u00020&*\u00020A2\u0006\u0010O\u001a\u00020\u00012\u0006\u0010P\u001a\u00020\u00012\u0006\u0010*\u001a\u00020+2\u0006\u0010Q\u001a\u00020RH\u0002ø\u0001\u0000¢\u0006\u0004\bS\u0010T\u001a&\u0010U\u001a\u00020&*\u00020A2\u0006\u0010*\u001a\u00020+2\u0006\u0010Q\u001a\u00020RH\u0002ø\u0001\u0000¢\u0006\u0004\bV\u0010W\u001a6\u0010X\u001a\u00020&*\u00020A2\u0006\u0010O\u001a\u00020\u00012\u0006\u0010P\u001a\u00020\u00012\u0006\u0010*\u001a\u00020+2\u0006\u0010Q\u001a\u00020RH\u0002ø\u0001\u0000¢\u0006\u0004\bY\u0010T\u001a>\u0010Z\u001a\u00020&*\u00020A2\u0006\u0010O\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u00012\u0006\u0010*\u001a\u00020+2\u0006\u0010Q\u001a\u00020RH\u0002ø\u0001\u0000¢\u0006\u0004\b[\u0010\\\u001a>\u0010]\u001a\u00020&*\u00020A2\u0006\u0010^\u001a\u00020\u00012\u0006\u0010_\u001a\u00020\u00012\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00012\u0006\u0010.\u001a\u00020/H\u0002ø\u0001\u0000¢\u0006\u0004\b`\u0010a\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\"\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0016\u001a\u00020\u0005X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0017\u0010\u0007\"\u0016\u0010\u0018\u001a\u00020\u0005X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0019\u0010\u0007\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010 \u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010!\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\"\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010#\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u000e\u0010$\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006b"}, d2 = {"BaseRotationAngle", "", "CircularEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "CircularIndicatorDiameter", "Landroidx/compose/ui/unit/Dp;", "getCircularIndicatorDiameter", "()F", "F", "FirstLineHeadDelay", "", "FirstLineHeadDuration", "FirstLineHeadEasing", "FirstLineTailDelay", "FirstLineTailDuration", "FirstLineTailEasing", "HeadAndTailAnimationDuration", "HeadAndTailDelayDuration", "IncreaseSemanticsBounds", "Landroidx/compose/ui/Modifier;", "JumpRotationAngle", "LinearAnimationDuration", "LinearIndicatorHeight", "getLinearIndicatorHeight", "LinearIndicatorWidth", "getLinearIndicatorWidth", "RotationAngleOffset", "RotationDuration", "RotationsPerCycle", "SecondLineHeadDelay", "SecondLineHeadDuration", "SecondLineHeadEasing", "SecondLineTailDelay", "SecondLineTailDuration", "SecondLineTailEasing", "SemanticsBoundsPadding", "StartAngleOffset", "CircularProgressIndicator", "", "progress", "Lkotlin/Function0;", "modifier", "color", "Landroidx/compose/ui/graphics/Color;", "strokeWidth", "trackColor", "strokeCap", "Landroidx/compose/ui/graphics/StrokeCap;", "CircularProgressIndicator-DUhRLBM", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "gapSize", "CircularProgressIndicator-IyT6zlY", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JFJIFLandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-aM-cp0Q", "(Landroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-LxG7B9w", "(Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-MBs18nI", "(FLandroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "(FLandroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator", "LinearProgressIndicator-_5eSR-E", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "drawStopIndicator", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lkotlin/ExtensionFunctionType;", "LinearProgressIndicator-GJbTh5U", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JJIFLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-RIQooxk", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-2cYBFYY", "(Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-rIrjwxo", "(Landroidx/compose/ui/Modifier;JJIFLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-eaDK9VM", "(FLandroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "(FLandroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "drawCircularIndicator", "startAngle", "sweep", "stroke", "Landroidx/compose/ui/graphics/drawscope/Stroke;", "drawCircularIndicator-42QJj7c", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawCircularIndicatorTrack", "drawCircularIndicatorTrack-bw27NRU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawDeterminateCircularIndicator", "drawDeterminateCircularIndicator-42QJj7c", "drawIndeterminateCircularIndicator", "drawIndeterminateCircularIndicator-hrjfTZI", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawLinearIndicator", "startFraction", "endFraction", "drawLinearIndicator-qYKTg0g", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJFI)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ProgressIndicatorKt {
    private static final float BaseRotationAngle = 286.0f;
    private static final CubicBezierEasing CircularEasing;
    private static final float CircularIndicatorDiameter;
    private static final int FirstLineHeadDelay = 0;
    private static final int FirstLineHeadDuration = 750;
    private static final CubicBezierEasing FirstLineHeadEasing;
    private static final int FirstLineTailDelay = 333;
    private static final int FirstLineTailDuration = 850;
    private static final CubicBezierEasing FirstLineTailEasing;
    private static final int HeadAndTailAnimationDuration = 666;
    private static final int HeadAndTailDelayDuration = 666;
    private static final Modifier IncreaseSemanticsBounds;
    private static final float JumpRotationAngle = 290.0f;
    private static final int LinearAnimationDuration = 1800;
    private static final float LinearIndicatorHeight;
    private static final float LinearIndicatorWidth;
    private static final float RotationAngleOffset = 216.0f;
    private static final int RotationDuration = 1332;
    private static final int RotationsPerCycle = 5;
    private static final int SecondLineHeadDelay = 1000;
    private static final int SecondLineHeadDuration = 567;
    private static final CubicBezierEasing SecondLineHeadEasing;
    private static final int SecondLineTailDelay = 1267;
    private static final int SecondLineTailDuration = 533;
    private static final CubicBezierEasing SecondLineTailEasing;
    private static final float SemanticsBoundsPadding;
    private static final float StartAngleOffset = -90.0f;

    /* JADX WARN: Code duplicated, block: B:26:0x004b  */
    /* JADX WARN: Code duplicated, block: B:28:0x004f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0057  */
    /* JADX WARN: Code duplicated, block: B:31:0x005a  */
    /* JADX WARN: Code duplicated, block: B:34:0x0060  */
    /* JADX WARN: Code duplicated, block: B:37:0x0066  */
    /* JADX WARN: Code duplicated, block: B:42:0x0075  */
    /* JADX WARN: Code duplicated, block: B:44:0x0079  */
    /* JADX WARN: Code duplicated, block: B:47:0x007f  */
    /* JADX WARN: Code duplicated, block: B:48:0x0082  */
    /* JADX WARN: Code duplicated, block: B:50:0x0086  */
    /* JADX WARN: Code duplicated, block: B:52:0x008e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0091  */
    /* JADX WARN: Code duplicated, block: B:58:0x009d  */
    /* JADX WARN: Code duplicated, block: B:62:0x00af  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:75:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:79:0x00de  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:83:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:86:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:90:0x010c  */
    /* JADX WARN: Code duplicated, block: B:93:0x013b  */
    /* JADX WARN: Code duplicated, block: B:97:0x0148  */
    /* JADX WARN: Code duplicated, block: B:99:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize` and `drawStopIndicator`, see `LegacyLinearProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(progress, modifier, color, trackColor, strokeCap, gapSize, drawStopIndicator)", imports = {}))
    /* JADX INFO: renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    public static final /* synthetic */ void m2092LinearProgressIndicator_5eSRE(final Function0 function0, Modifier modifier, long j, long j2, int i, Composer composer, final int i2, final int i3) {
        Function0 function1;
        int i4;
        Modifier modifier2;
        long j3;
        long linearTrackColor;
        int i5;
        int i6;
        int i7;
        Modifier.Companion companion;
        long linearColor;
        int iM2080getLinearStrokeCapKaPHkGw;
        Modifier modifier3;
        long j4;
        Composer composer2;
        final Modifier modifier4;
        final long j5;
        final long j6;
        final int i8;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i9;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1796992155);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LinearProgressIndicator)P(2,1,0:c#ui.graphics.Color,4:c#ui.graphics.Color,3:c#ui.graphics.StrokeCap)96@4380L11,97@4443L16,100@4539L192:ProgressIndicator.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i2 & 6) == 0) {
                i4 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i2;
            } else {
                i4 = i2;
            }
        }
        int i10 = i3 & 2;
        if (i10 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if ((i3 & 4) == 0) {
                    j3 = j;
                    int i11 = composerStartRestartGroup.changed(j3) ? 256 : 128;
                    i4 |= i11;
                } else {
                    j3 = j;
                }
                i4 |= i11;
            } else {
                j3 = j;
            }
            if ((i2 & 3072) == 0) {
                linearTrackColor = j2;
                if ((i3 & 8) == 0 || !composerStartRestartGroup.changed(linearTrackColor)) {
                    i9 = 1024;
                } else {
                    i9 = 2048;
                }
                i4 |= i9;
            } else {
                linearTrackColor = j2;
            }
            i5 = i3 & 16;
            if (i5 != 0) {
                if ((i2 & 24576) == 0) {
                    i6 = i;
                    if (composerStartRestartGroup.changed(i6)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i4 |= i7;
                }
                if ((i4 & 9363) == 9362 || !composerStartRestartGroup.getSkipping()) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        } else {
                            linearColor = j3;
                        }
                        if ((i3 & 8) != 0) {
                            linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                            i4 &= -7169;
                        }
                        if (i5 != 0) {
                            iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                        } else {
                            iM2080getLinearStrokeCapKaPHkGw = i6;
                        }
                        modifier3 = companion;
                        j4 = linearColor;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                        }
                        linearTrackColor = linearTrackColor;
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                        modifier3 = modifier2;
                        j4 = j3;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1796992155, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:99)");
                    }
                    composer2 = composerStartRestartGroup;
                    m2089LinearProgressIndicatorGJbTh5U(function1, modifier3, j4, linearTrackColor, iM2080getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (i4 & 57344), 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    j5 = j4;
                    j6 = linearTrackColor;
                    i8 = iM2080getLinearStrokeCapKaPHkGw;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    composer2 = composerStartRestartGroup;
                    modifier4 = modifier2;
                    j5 = j3;
                    j6 = linearTrackColor;
                    i8 = i6;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i12) {
                            ProgressIndicatorKt.m2092LinearProgressIndicator_5eSRE(function0, modifier4, j5, j6, i8, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 24576;
            i6 = i;
            if ((i4 & 9363) == 9362) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    modifier3 = companion;
                    j4 = linearColor;
                } else {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    modifier3 = companion;
                    j4 = linearColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1796992155, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:99)");
                }
                composer2 = composerStartRestartGroup;
                m2089LinearProgressIndicatorGJbTh5U(function1, modifier3, j4, linearTrackColor, iM2080getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (i4 & 57344), 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j5 = j4;
                j6 = linearTrackColor;
                i8 = iM2080getLinearStrokeCapKaPHkGw;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    modifier3 = companion;
                    j4 = linearColor;
                } else {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    modifier3 = companion;
                    j4 = linearColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1796992155, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:99)");
                }
                composer2 = composerStartRestartGroup;
                m2089LinearProgressIndicatorGJbTh5U(function1, modifier3, j4, linearTrackColor, iM2080getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (i4 & 57344), 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j5 = j4;
                j6 = linearTrackColor;
                i8 = iM2080getLinearStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i12) {
                        ProgressIndicatorKt.m2092LinearProgressIndicator_5eSRE(function0, modifier4, j5, j6, i8, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i3 & 4) == 0) {
                j3 = j;
                if (composerStartRestartGroup.changed(j3)) {
                }
                i4 |= i11;
            } else {
                j3 = j;
            }
            i4 |= i11;
        } else {
            j3 = j;
        }
        if ((i2 & 3072) == 0) {
            linearTrackColor = j2;
            if ((i3 & 8) == 0) {
                i9 = 1024;
            } else {
                i9 = 1024;
            }
            i4 |= i9;
        } else {
            linearTrackColor = j2;
        }
        i5 = i3 & 16;
        if (i5 != 0) {
            if ((i2 & 24576) == 0) {
                i6 = i;
                if (composerStartRestartGroup.changed(i6)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i4 |= i7;
            }
            if ((i4 & 9363) == 9362) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    modifier3 = companion;
                    j4 = linearColor;
                } else {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    modifier3 = companion;
                    j4 = linearColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1796992155, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:99)");
                }
                composer2 = composerStartRestartGroup;
                m2089LinearProgressIndicatorGJbTh5U(function1, modifier3, j4, linearTrackColor, iM2080getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (i4 & 57344), 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j5 = j4;
                j6 = linearTrackColor;
                i8 = iM2080getLinearStrokeCapKaPHkGw;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    modifier3 = companion;
                    j4 = linearColor;
                } else {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    modifier3 = companion;
                    j4 = linearColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1796992155, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:99)");
                }
                composer2 = composerStartRestartGroup;
                m2089LinearProgressIndicatorGJbTh5U(function1, modifier3, j4, linearTrackColor, iM2080getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (i4 & 57344), 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j5 = j4;
                j6 = linearTrackColor;
                i8 = iM2080getLinearStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i12) {
                        ProgressIndicatorKt.m2092LinearProgressIndicator_5eSRE(function0, modifier4, j5, j6, i8, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 24576;
        i6 = i;
        if ((i4 & 9363) == 9362) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i10 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                } else {
                    linearColor = j3;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i6;
                }
                modifier3 = companion;
                j4 = linearColor;
            } else {
                if (i10 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                } else {
                    linearColor = j3;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i6;
                }
                modifier3 = companion;
                j4 = linearColor;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1796992155, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:99)");
            }
            composer2 = composerStartRestartGroup;
            m2089LinearProgressIndicatorGJbTh5U(function1, modifier3, j4, linearTrackColor, iM2080getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (i4 & 57344), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j5 = j4;
            j6 = linearTrackColor;
            i8 = iM2080getLinearStrokeCapKaPHkGw;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i10 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                } else {
                    linearColor = j3;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i6;
                }
                modifier3 = companion;
                j4 = linearColor;
            } else {
                if (i10 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                } else {
                    linearColor = j3;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i6;
                }
                modifier3 = companion;
                j4 = linearColor;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1796992155, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:99)");
            }
            composer2 = composerStartRestartGroup;
            m2089LinearProgressIndicatorGJbTh5U(function1, modifier3, j4, linearTrackColor, iM2080getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (i4 & 57344), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j5 = j4;
            j6 = linearTrackColor;
            i8 = iM2080getLinearStrokeCapKaPHkGw;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i12) {
                    ProgressIndicatorKt.m2092LinearProgressIndicator_5eSRE(function0, modifier4, j5, j6, i8, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0127  */
    /* JADX WARN: Code duplicated, block: B:104:0x0133  */
    /* JADX WARN: Code duplicated, block: B:106:0x013d  */
    /* JADX WARN: Code duplicated, block: B:107:0x0144  */
    /* JADX WARN: Code duplicated, block: B:109:0x0147  */
    /* JADX WARN: Code duplicated, block: B:112:0x0151  */
    /* JADX WARN: Code duplicated, block: B:114:0x015f  */
    /* JADX WARN: Code duplicated, block: B:116:0x0165  */
    /* JADX WARN: Code duplicated, block: B:122:0x0174  */
    /* JADX WARN: Code duplicated, block: B:123:0x0176  */
    /* JADX WARN: Code duplicated, block: B:126:0x017e  */
    /* JADX WARN: Code duplicated, block: B:128:0x0186  */
    /* JADX WARN: Code duplicated, block: B:130:0x019c  */
    /* JADX WARN: Code duplicated, block: B:134:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:137:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:138:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:141:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:143:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:146:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:148:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:151:0x0228  */
    /* JADX WARN: Code duplicated, block: B:152:0x022a  */
    /* JADX WARN: Code duplicated, block: B:155:0x0232  */
    /* JADX WARN: Code duplicated, block: B:156:0x0234  */
    /* JADX WARN: Code duplicated, block: B:159:0x0243  */
    /* JADX WARN: Code duplicated, block: B:161:0x0249  */
    /* JADX WARN: Code duplicated, block: B:167:0x0259  */
    /* JADX WARN: Code duplicated, block: B:169:0x025f  */
    /* JADX WARN: Code duplicated, block: B:175:0x0270  */
    /* JADX WARN: Code duplicated, block: B:177:0x0276  */
    /* JADX WARN: Code duplicated, block: B:183:0x0283  */
    /* JADX WARN: Code duplicated, block: B:187:0x0293  */
    /* JADX WARN: Code duplicated, block: B:190:0x02b6  */
    /* JADX WARN: Code duplicated, block: B:194:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:196:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0049  */
    /* JADX WARN: Code duplicated, block: B:31:0x0058  */
    /* JADX WARN: Code duplicated, block: B:33:0x005c  */
    /* JADX WARN: Code duplicated, block: B:36:0x0062  */
    /* JADX WARN: Code duplicated, block: B:41:0x0071  */
    /* JADX WARN: Code duplicated, block: B:43:0x0075  */
    /* JADX WARN: Code duplicated, block: B:46:0x007b  */
    /* JADX WARN: Code duplicated, block: B:47:0x007e  */
    /* JADX WARN: Code duplicated, block: B:49:0x0082  */
    /* JADX WARN: Code duplicated, block: B:51:0x008a  */
    /* JADX WARN: Code duplicated, block: B:52:0x008d  */
    /* JADX WARN: Code duplicated, block: B:57:0x009a  */
    /* JADX WARN: Code duplicated, block: B:58:0x009f  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:69:0x00be  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:74:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:83:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:96:0x011a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:97:0x011c  */
    /* JADX WARN: Code duplicated, block: B:98:0x0121  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: LinearProgressIndicator-GJbTh5U, reason: not valid java name */
    public static final void m2089LinearProgressIndicatorGJbTh5U(final Function0<Float> function0, Modifier modifier, long j, long j2, int i, float f, Function1<? super DrawScope, Unit> function1, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        final long linearColor;
        long linearTrackColor;
        int i5;
        int i6;
        int i7;
        int i8;
        float fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
        int i9;
        Function1<? super DrawScope, Unit> function2;
        Modifier.Companion companion;
        final int iM2080getLinearStrokeCapKaPHkGw;
        Function1<? super DrawScope, Unit> function3;
        final float f2;
        final int i10;
        boolean z;
        boolean z2;
        Object objRememberedValue;
        boolean z3;
        Object objRememberedValue2;
        final Function0 function4;
        boolean zChanged;
        Object objRememberedValue3;
        boolean z4;
        boolean z5;
        boolean z6;
        Object objRememberedValue4;
        final Function1<? super DrawScope, Unit> function5;
        final long j3;
        final long j4;
        final int i11;
        final float f3;
        final long j5;
        final Function1<? super DrawScope, Unit> function6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i12;
        int i13;
        int i14;
        Composer composerStartRestartGroup = composer.startRestartGroup(-339970038);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LinearProgressIndicator)P(4,3,0:c#ui.graphics.Color,6:c#ui.graphics.Color,5:c#ui.graphics.StrokeCap,2:c#ui.unit.Dp)140@6419L11,141@6482L16,144@6689L214,153@6935L31,157@7087L102,161@7259L806,154@6971L1094:ProgressIndicator.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i15 = i3 & 2;
        if (i15 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                linearColor = j;
                if ((i3 & 4) == 0 || !composerStartRestartGroup.changed(linearColor)) {
                    i14 = 128;
                } else {
                    i14 = 256;
                }
                i4 |= i14;
            } else {
                linearColor = j;
            }
            if ((i2 & 3072) == 0) {
                linearTrackColor = j2;
                if ((i3 & 8) == 0 || !composerStartRestartGroup.changed(linearTrackColor)) {
                    i13 = 1024;
                } else {
                    i13 = 2048;
                }
                i4 |= i13;
            } else {
                linearTrackColor = j2;
            }
            i5 = i3 & 16;
            if (i5 != 0) {
                if ((i2 & 24576) == 0) {
                    i6 = i;
                    if (composerStartRestartGroup.changed(i6)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i4 |= i7;
                }
                i8 = i3 & 32;
                if (i8 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f;
                } else {
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f;
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i4 |= i9;
                    }
                }
                if ((i2 & 1572864) == 0) {
                    function2 = function1;
                    if ((i3 & 64) == 0 || !composerStartRestartGroup.changedInstance(function2)) {
                        i12 = 524288;
                    } else {
                        i12 = 1048576;
                    }
                    i4 |= i12;
                } else {
                    function2 = function1;
                }
                if ((i4 & 599187) == 599186 || !composerStartRestartGroup.getSkipping()) {
                    composerStartRestartGroup.startDefaults();
                    boolean z7 = true;
                    if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i15 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                            i4 &= -7169;
                        }
                        if (i5 != 0) {
                            iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                        } else {
                            iM2080getLinearStrokeCapKaPHkGw = i6;
                        }
                        if (i8 != 0) {
                            fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                        }
                        if ((i3 & 64) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                            boolean z8 = (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(linearColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256;
                            if ((57344 & i4) == 16384) {
                                z = true;
                            } else {
                                z = false;
                            }
                            z2 = z8 | z;
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                        ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function3 = (Function1) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i4 &= -3670017;
                        } else {
                            function3 = function1;
                        }
                        f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                        i10 = iM2080getLinearStrokeCapKaPHkGw;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                        }
                        function3 = function1;
                        f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                        companion = modifier2;
                        i10 = i6;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-339970038, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:152)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145005305, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((i4 & 14) == 4) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z3 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function4 = (Function0) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen = companion.then(IncreaseSemanticsBounds);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145010240, "CC(remember):ProgressIndicator.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(function4);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function4.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierM767sizeVpY3zN4 = SizeKt.m767sizeVpY3zN4(SemanticsModifierKt.semantics(modifierThen, true, (Function1) objRememberedValue3), LinearIndicatorWidth, LinearIndicatorHeight);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145016448, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((57344 & i4) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if ((458752 & i4) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    boolean zChanged2 = z4 | z5 | composerStartRestartGroup.changed(function4) | ((((i4 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(linearTrackColor)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(linearColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
                    if ((((3670016 & i4) ^ 1572864) > 1048576 || !composerStartRestartGroup.changed(function3)) && (i4 & 1572864) != 1048576) {
                    }
                    z6 = z7 | zChanged2;
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (!z6 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        function5 = function3;
                        j3 = linearColor;
                        j4 = linearTrackColor;
                        objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                                float fM6890constructorimpl;
                                DrawScope drawScope2;
                                float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                                if (StrokeCap.m4527equalsimpl0(i10, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                    fM6890constructorimpl = f2;
                                } else {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(f2 + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                                }
                                float f4 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                                float fFloatValue = function4.invoke().floatValue();
                                float fMin = fFloatValue + Math.min(fFloatValue, f4);
                                if (fMin <= 1.0f) {
                                    drawScope2 = drawScope;
                                    ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, fMin, 1.0f, j4, fM3936getHeightimpl, i10);
                                } else {
                                    drawScope2 = drawScope;
                                }
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, fFloatValue, j3, fM3936getHeightimpl, i10);
                                function5.invoke(drawScope2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        function5 = function3;
                        j3 = linearColor;
                        j4 = linearTrackColor;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CanvasKt.Canvas(modifierM767sizeVpY3zN4, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    i11 = i10;
                    f3 = f2;
                    linearTrackColor = j4;
                    j5 = j3;
                    function6 = function5;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    f3 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                    companion = modifier2;
                    i11 = i6;
                    j5 = linearColor;
                    function6 = function2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier3 = companion;
                    final long j6 = linearTrackColor;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i16) {
                            ProgressIndicatorKt.m2089LinearProgressIndicatorGJbTh5U(function0, modifier3, j5, j6, i11, f3, function6, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 24576;
            i6 = i;
            i8 = i3 & 32;
            if (i8 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f;
            } else {
                fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f;
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
            }
            if ((i2 & 1572864) == 0) {
                function2 = function1;
                if ((i3 & 64) == 0) {
                    i12 = 524288;
                } else {
                    i12 = 524288;
                }
                i4 |= i12;
            } else {
                function2 = function1;
            }
            if ((i4 & 599187) == 599186) {
                composerStartRestartGroup.startDefaults();
                boolean z9 = true;
                if ((i2 & 1) != 0) {
                    if (i15 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    if (i8 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    }
                    if ((i3 & 64) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                        if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                        }
                        if ((57344 & i4) == 16384) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = z8 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function3 = (Function1) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i4 &= -3670017;
                    } else {
                        function3 = function1;
                    }
                    f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                    i10 = iM2080getLinearStrokeCapKaPHkGw;
                } else {
                    if (i15 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    if (i8 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    }
                    if ((i3 & 64) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                        if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                        }
                        if ((57344 & i4) == 16384) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = z8 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function3 = (Function1) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i4 &= -3670017;
                    } else {
                        function3 = function1;
                    }
                    f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                    i10 = iM2080getLinearStrokeCapKaPHkGw;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-339970038, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:152)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145005305, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    objRememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                function4 = (Function0) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen2 = companion.then(IncreaseSemanticsBounds);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145010240, "CC(remember):ProgressIndicator.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(function4);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function4.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function4.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierM767sizeVpY3zN5 = SizeKt.m767sizeVpY3zN4(SemanticsModifierKt.semantics(modifierThen2, true, (Function1) objRememberedValue3), LinearIndicatorWidth, LinearIndicatorHeight);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145016448, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((57344 & i4) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if ((458752 & i4) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                boolean zChanged3 = z4 | z5 | composerStartRestartGroup.changed(function4) | ((((i4 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(linearTrackColor)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(linearColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
                z9 = ((3670016 & i4) ^ 1572864) > 1048576 ? false : false;
                z6 = z9 | zChanged3;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    function5 = function3;
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i10, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = f2;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(f2 + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f4 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            float fFloatValue = function4.invoke().floatValue();
                            float fMin = fFloatValue + Math.min(fFloatValue, f4);
                            if (fMin <= 1.0f) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, fMin, 1.0f, j4, fM3936getHeightimpl, i10);
                            } else {
                                drawScope2 = drawScope;
                            }
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, fFloatValue, j3, fM3936getHeightimpl, i10);
                            function5.invoke(drawScope2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    function5 = function3;
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i10, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = f2;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(f2 + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f4 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            float fFloatValue = function4.invoke().floatValue();
                            float fMin = fFloatValue + Math.min(fFloatValue, f4);
                            if (fMin <= 1.0f) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, fMin, 1.0f, j4, fM3936getHeightimpl, i10);
                            } else {
                                drawScope2 = drawScope;
                            }
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, fFloatValue, j3, fM3936getHeightimpl, i10);
                            function5.invoke(drawScope2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM767sizeVpY3zN5, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                i11 = i10;
                f3 = f2;
                linearTrackColor = j4;
                j5 = j3;
                function6 = function5;
            } else {
                composerStartRestartGroup.startDefaults();
                boolean z10 = true;
                if ((i2 & 1) != 0) {
                    if (i15 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    if (i8 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    }
                    if ((i3 & 64) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                        if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                        }
                        if ((57344 & i4) == 16384) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = z8 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function3 = (Function1) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i4 &= -3670017;
                    } else {
                        function3 = function1;
                    }
                    f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                    i10 = iM2080getLinearStrokeCapKaPHkGw;
                } else {
                    if (i15 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    if (i8 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    }
                    if ((i3 & 64) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                        if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                        }
                        if ((57344 & i4) == 16384) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = z8 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function3 = (Function1) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i4 &= -3670017;
                    } else {
                        function3 = function1;
                    }
                    f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                    i10 = iM2080getLinearStrokeCapKaPHkGw;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-339970038, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:152)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145005305, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    objRememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                function4 = (Function0) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen3 = companion.then(IncreaseSemanticsBounds);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145010240, "CC(remember):ProgressIndicator.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(function4);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function4.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function4.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierM767sizeVpY3zN6 = SizeKt.m767sizeVpY3zN4(SemanticsModifierKt.semantics(modifierThen3, true, (Function1) objRememberedValue3), LinearIndicatorWidth, LinearIndicatorHeight);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145016448, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((57344 & i4) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if ((458752 & i4) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                boolean zChanged4 = z4 | z5 | composerStartRestartGroup.changed(function4) | ((((i4 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(linearTrackColor)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(linearColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
                if (((3670016 & i4) ^ 1572864) > 1048576) {
                }
                z6 = z10 | zChanged4;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    function5 = function3;
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i10, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = f2;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(f2 + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f4 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            float fFloatValue = function4.invoke().floatValue();
                            float fMin = fFloatValue + Math.min(fFloatValue, f4);
                            if (fMin <= 1.0f) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, fMin, 1.0f, j4, fM3936getHeightimpl, i10);
                            } else {
                                drawScope2 = drawScope;
                            }
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, fFloatValue, j3, fM3936getHeightimpl, i10);
                            function5.invoke(drawScope2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    function5 = function3;
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i10, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = f2;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(f2 + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f4 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            float fFloatValue = function4.invoke().floatValue();
                            float fMin = fFloatValue + Math.min(fFloatValue, f4);
                            if (fMin <= 1.0f) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, fMin, 1.0f, j4, fM3936getHeightimpl, i10);
                            } else {
                                drawScope2 = drawScope;
                            }
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, fFloatValue, j3, fM3936getHeightimpl, i10);
                            function5.invoke(drawScope2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM767sizeVpY3zN6, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                i11 = i10;
                f3 = f2;
                linearTrackColor = j4;
                j5 = j3;
                function6 = function5;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier4 = companion;
                final long j7 = linearTrackColor;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i16) {
                        ProgressIndicatorKt.m2089LinearProgressIndicatorGJbTh5U(function0, modifier4, j5, j7, i11, f3, function6, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            linearColor = j;
            if ((i3 & 4) == 0) {
                i14 = 128;
            } else {
                i14 = 128;
            }
            i4 |= i14;
        } else {
            linearColor = j;
        }
        if ((i2 & 3072) == 0) {
            linearTrackColor = j2;
            if ((i3 & 8) == 0) {
                i13 = 1024;
            } else {
                i13 = 1024;
            }
            i4 |= i13;
        } else {
            linearTrackColor = j2;
        }
        i5 = i3 & 16;
        if (i5 != 0) {
            if ((i2 & 24576) == 0) {
                i6 = i;
                if (composerStartRestartGroup.changed(i6)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i4 |= i7;
            }
            i8 = i3 & 32;
            if (i8 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f;
            } else {
                fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f;
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
            }
            if ((i2 & 1572864) == 0) {
                function2 = function1;
                if ((i3 & 64) == 0) {
                    i12 = 524288;
                } else {
                    i12 = 524288;
                }
                i4 |= i12;
            } else {
                function2 = function1;
            }
            if ((i4 & 599187) == 599186) {
                composerStartRestartGroup.startDefaults();
                boolean z11 = true;
                if ((i2 & 1) != 0) {
                    if (i15 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    if (i8 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    }
                    if ((i3 & 64) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                        if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                        }
                        if ((57344 & i4) == 16384) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = z8 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function3 = (Function1) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i4 &= -3670017;
                    } else {
                        function3 = function1;
                    }
                    f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                    i10 = iM2080getLinearStrokeCapKaPHkGw;
                } else {
                    if (i15 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    if (i8 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    }
                    if ((i3 & 64) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                        if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                        }
                        if ((57344 & i4) == 16384) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = z8 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function3 = (Function1) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i4 &= -3670017;
                    } else {
                        function3 = function1;
                    }
                    f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                    i10 = iM2080getLinearStrokeCapKaPHkGw;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-339970038, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:152)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145005305, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    objRememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                function4 = (Function0) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen4 = companion.then(IncreaseSemanticsBounds);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145010240, "CC(remember):ProgressIndicator.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(function4);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function4.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function4.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierM767sizeVpY3zN7 = SizeKt.m767sizeVpY3zN4(SemanticsModifierKt.semantics(modifierThen4, true, (Function1) objRememberedValue3), LinearIndicatorWidth, LinearIndicatorHeight);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145016448, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((57344 & i4) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if ((458752 & i4) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                boolean zChanged5 = z4 | z5 | composerStartRestartGroup.changed(function4) | ((((i4 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(linearTrackColor)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(linearColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
                if (((3670016 & i4) ^ 1572864) > 1048576) {
                }
                z6 = z11 | zChanged5;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    function5 = function3;
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i10, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = f2;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(f2 + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f4 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            float fFloatValue = function4.invoke().floatValue();
                            float fMin = fFloatValue + Math.min(fFloatValue, f4);
                            if (fMin <= 1.0f) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, fMin, 1.0f, j4, fM3936getHeightimpl, i10);
                            } else {
                                drawScope2 = drawScope;
                            }
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, fFloatValue, j3, fM3936getHeightimpl, i10);
                            function5.invoke(drawScope2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    function5 = function3;
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i10, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = f2;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(f2 + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f4 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            float fFloatValue = function4.invoke().floatValue();
                            float fMin = fFloatValue + Math.min(fFloatValue, f4);
                            if (fMin <= 1.0f) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, fMin, 1.0f, j4, fM3936getHeightimpl, i10);
                            } else {
                                drawScope2 = drawScope;
                            }
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, fFloatValue, j3, fM3936getHeightimpl, i10);
                            function5.invoke(drawScope2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM767sizeVpY3zN7, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                i11 = i10;
                f3 = f2;
                linearTrackColor = j4;
                j5 = j3;
                function6 = function5;
            } else {
                composerStartRestartGroup.startDefaults();
                boolean z12 = true;
                if ((i2 & 1) != 0) {
                    if (i15 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    if (i8 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    }
                    if ((i3 & 64) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                        if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                        }
                        if ((57344 & i4) == 16384) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = z8 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function3 = (Function1) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i4 &= -3670017;
                    } else {
                        function3 = function1;
                    }
                    f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                    i10 = iM2080getLinearStrokeCapKaPHkGw;
                } else {
                    if (i15 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                    if (i8 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    }
                    if ((i3 & 64) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                        if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                        }
                        if ((57344 & i4) == 16384) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z2 = z8 | z;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function3 = (Function1) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i4 &= -3670017;
                    } else {
                        function3 = function1;
                    }
                    f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                    i10 = iM2080getLinearStrokeCapKaPHkGw;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-339970038, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:152)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145005305, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    objRememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                function4 = (Function0) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen5 = companion.then(IncreaseSemanticsBounds);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145010240, "CC(remember):ProgressIndicator.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(function4);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function4.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function4.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierM767sizeVpY3zN8 = SizeKt.m767sizeVpY3zN4(SemanticsModifierKt.semantics(modifierThen5, true, (Function1) objRememberedValue3), LinearIndicatorWidth, LinearIndicatorHeight);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145016448, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((57344 & i4) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if ((458752 & i4) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                boolean zChanged6 = z4 | z5 | composerStartRestartGroup.changed(function4) | ((((i4 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(linearTrackColor)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(linearColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
                if (((3670016 & i4) ^ 1572864) > 1048576) {
                }
                z6 = z12 | zChanged6;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    function5 = function3;
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i10, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = f2;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(f2 + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f4 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            float fFloatValue = function4.invoke().floatValue();
                            float fMin = fFloatValue + Math.min(fFloatValue, f4);
                            if (fMin <= 1.0f) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, fMin, 1.0f, j4, fM3936getHeightimpl, i10);
                            } else {
                                drawScope2 = drawScope;
                            }
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, fFloatValue, j3, fM3936getHeightimpl, i10);
                            function5.invoke(drawScope2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    function5 = function3;
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i10, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = f2;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(f2 + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f4 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            float fFloatValue = function4.invoke().floatValue();
                            float fMin = fFloatValue + Math.min(fFloatValue, f4);
                            if (fMin <= 1.0f) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, fMin, 1.0f, j4, fM3936getHeightimpl, i10);
                            } else {
                                drawScope2 = drawScope;
                            }
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, fFloatValue, j3, fM3936getHeightimpl, i10);
                            function5.invoke(drawScope2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM767sizeVpY3zN8, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                i11 = i10;
                f3 = f2;
                linearTrackColor = j4;
                j5 = j3;
                function6 = function5;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier5 = companion;
                final long j8 = linearTrackColor;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i16) {
                        ProgressIndicatorKt.m2089LinearProgressIndicatorGJbTh5U(function0, modifier5, j5, j8, i11, f3, function6, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 24576;
        i6 = i;
        i8 = i3 & 32;
        if (i8 != 0) {
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f;
        } else {
            fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f;
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
        }
        if ((i2 & 1572864) == 0) {
            function2 = function1;
            if ((i3 & 64) == 0) {
                i12 = 524288;
            } else {
                i12 = 524288;
            }
            i4 |= i12;
        } else {
            function2 = function1;
        }
        if ((i4 & 599187) == 599186) {
            composerStartRestartGroup.startDefaults();
            boolean z13 = true;
            if ((i2 & 1) != 0) {
                if (i15 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i6;
                }
                if (i8 != 0) {
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                }
                if ((i3 & 64) != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((57344 & i4) == 16384) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = z8 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function3 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    i4 &= -3670017;
                } else {
                    function3 = function1;
                }
                f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                i10 = iM2080getLinearStrokeCapKaPHkGw;
            } else {
                if (i15 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i6;
                }
                if (i8 != 0) {
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                }
                if ((i3 & 64) != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((57344 & i4) == 16384) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = z8 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function3 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    i4 &= -3670017;
                } else {
                    function3 = function1;
                }
                f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                i10 = iM2080getLinearStrokeCapKaPHkGw;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-339970038, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:152)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145005305, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 14) == 4) {
                z3 = true;
            } else {
                z3 = false;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z3) {
                objRememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            function4 = (Function0) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierThen6 = companion.then(IncreaseSemanticsBounds);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145010240, "CC(remember):ProgressIndicator.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(function4);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function4.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function4.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM767sizeVpY3zN9 = SizeKt.m767sizeVpY3zN4(SemanticsModifierKt.semantics(modifierThen6, true, (Function1) objRememberedValue3), LinearIndicatorWidth, LinearIndicatorHeight);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145016448, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((57344 & i4) == 16384) {
                z4 = true;
            } else {
                z4 = false;
            }
            if ((458752 & i4) == 131072) {
                z5 = true;
            } else {
                z5 = false;
            }
            boolean zChanged7 = z4 | z5 | composerStartRestartGroup.changed(function4) | ((((i4 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(linearTrackColor)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(linearColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
            if (((3670016 & i4) ^ 1572864) > 1048576) {
            }
            z6 = z13 | zChanged7;
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z6) {
                function5 = function3;
                j3 = linearColor;
                j4 = linearTrackColor;
                objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                        float fM6890constructorimpl;
                        DrawScope drawScope2;
                        float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                        if (StrokeCap.m4527equalsimpl0(i10, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                            fM6890constructorimpl = f2;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(f2 + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                        }
                        float f4 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                        float fFloatValue = function4.invoke().floatValue();
                        float fMin = fFloatValue + Math.min(fFloatValue, f4);
                        if (fMin <= 1.0f) {
                            drawScope2 = drawScope;
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, fMin, 1.0f, j4, fM3936getHeightimpl, i10);
                        } else {
                            drawScope2 = drawScope;
                        }
                        ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, fFloatValue, j3, fM3936getHeightimpl, i10);
                        function5.invoke(drawScope2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                function5 = function3;
                j3 = linearColor;
                j4 = linearTrackColor;
                objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                        float fM6890constructorimpl;
                        DrawScope drawScope2;
                        float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                        if (StrokeCap.m4527equalsimpl0(i10, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                            fM6890constructorimpl = f2;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(f2 + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                        }
                        float f4 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                        float fFloatValue = function4.invoke().floatValue();
                        float fMin = fFloatValue + Math.min(fFloatValue, f4);
                        if (fMin <= 1.0f) {
                            drawScope2 = drawScope;
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, fMin, 1.0f, j4, fM3936getHeightimpl, i10);
                        } else {
                            drawScope2 = drawScope;
                        }
                        ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, fFloatValue, j3, fM3936getHeightimpl, i10);
                        function5.invoke(drawScope2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CanvasKt.Canvas(modifierM767sizeVpY3zN9, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            i11 = i10;
            f3 = f2;
            linearTrackColor = j4;
            j5 = j3;
            function6 = function5;
        } else {
            composerStartRestartGroup.startDefaults();
            boolean z14 = true;
            if ((i2 & 1) != 0) {
                if (i15 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i6;
                }
                if (i8 != 0) {
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                }
                if ((i3 & 64) != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((57344 & i4) == 16384) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = z8 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function3 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    i4 &= -3670017;
                } else {
                    function3 = function1;
                }
                f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                i10 = iM2080getLinearStrokeCapKaPHkGw;
            } else {
                if (i15 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i6;
                }
                if (i8 != 0) {
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                }
                if ((i3 & 64) != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((57344 & i4) == 16384) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = z8 | z;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                ProgressIndicatorDefaults.INSTANCE.m2074drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2081getLinearTrackStopIndicatorSizeD9Ej5fM(), linearColor, iM2080getLinearStrokeCapKaPHkGw);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function3 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    i4 &= -3670017;
                } else {
                    function3 = function1;
                }
                f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                i10 = iM2080getLinearStrokeCapKaPHkGw;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-339970038, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:152)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145005305, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 14) == 4) {
                z3 = true;
            } else {
                z3 = false;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z3) {
                objRememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            function4 = (Function0) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierThen7 = companion.then(IncreaseSemanticsBounds);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145010240, "CC(remember):ProgressIndicator.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(function4);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function4.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function4.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM767sizeVpY3zN10 = SizeKt.m767sizeVpY3zN4(SemanticsModifierKt.semantics(modifierThen7, true, (Function1) objRememberedValue3), LinearIndicatorWidth, LinearIndicatorHeight);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145016448, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((57344 & i4) == 16384) {
                z4 = true;
            } else {
                z4 = false;
            }
            if ((458752 & i4) == 131072) {
                z5 = true;
            } else {
                z5 = false;
            }
            boolean zChanged8 = z4 | z5 | composerStartRestartGroup.changed(function4) | ((((i4 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(linearTrackColor)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(linearColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
            if (((3670016 & i4) ^ 1572864) > 1048576) {
            }
            z6 = z14 | zChanged8;
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z6) {
                function5 = function3;
                j3 = linearColor;
                j4 = linearTrackColor;
                objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                        float fM6890constructorimpl;
                        DrawScope drawScope2;
                        float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                        if (StrokeCap.m4527equalsimpl0(i10, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                            fM6890constructorimpl = f2;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(f2 + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                        }
                        float f4 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                        float fFloatValue = function4.invoke().floatValue();
                        float fMin = fFloatValue + Math.min(fFloatValue, f4);
                        if (fMin <= 1.0f) {
                            drawScope2 = drawScope;
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, fMin, 1.0f, j4, fM3936getHeightimpl, i10);
                        } else {
                            drawScope2 = drawScope;
                        }
                        ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, fFloatValue, j3, fM3936getHeightimpl, i10);
                        function5.invoke(drawScope2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                function5 = function3;
                j3 = linearColor;
                j4 = linearTrackColor;
                objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                        float fM6890constructorimpl;
                        DrawScope drawScope2;
                        float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                        if (StrokeCap.m4527equalsimpl0(i10, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                            fM6890constructorimpl = f2;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(f2 + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                        }
                        float f4 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                        float fFloatValue = function4.invoke().floatValue();
                        float fMin = fFloatValue + Math.min(fFloatValue, f4);
                        if (fMin <= 1.0f) {
                            drawScope2 = drawScope;
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, fMin, 1.0f, j4, fM3936getHeightimpl, i10);
                        } else {
                            drawScope2 = drawScope;
                        }
                        ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, fFloatValue, j3, fM3936getHeightimpl, i10);
                        function5.invoke(drawScope2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CanvasKt.Canvas(modifierM767sizeVpY3zN10, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            i11 = i10;
            f3 = f2;
            linearTrackColor = j4;
            j5 = j3;
            function6 = function5;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = companion;
            final long j9 = linearTrackColor;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i16) {
                    ProgressIndicatorKt.m2089LinearProgressIndicatorGJbTh5U(function0, modifier6, j5, j9, i11, f3, function6, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:51:0x0094  */
    /* JADX WARN: Code duplicated, block: B:53:0x009b  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:65:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:66:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:72:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:74:0x00da  */
    /* JADX WARN: Code duplicated, block: B:75:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:79:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:82:0x0115  */
    /* JADX WARN: Code duplicated, block: B:86:0x0122  */
    /* JADX WARN: Code duplicated, block: B:88:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize`, see `LegacyIndeterminateLinearProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(modifier, color, trackColor, strokeCap, gapSize)", imports = {}))
    /* JADX INFO: renamed from: LinearProgressIndicator-2cYBFYY, reason: not valid java name */
    public static final /* synthetic */ void m2088LinearProgressIndicator2cYBFYY(Modifier modifier, long j, long j2, int i, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        long linearColor;
        long linearTrackColor;
        int i5;
        Modifier.Companion companion;
        int iM2080getLinearStrokeCapKaPHkGw;
        Modifier modifier3;
        final Modifier modifier4;
        final long j3;
        final long j4;
        final int i6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-476865359);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LinearProgressIndicator)P(1,0:c#ui.graphics.Color,3:c#ui.graphics.Color,2:c#ui.graphics.StrokeCap)214@9497L11,215@9560L16,218@9656L175:ProgressIndicator.kt#uh7d8r");
        int i7 = i3 & 1;
        if (i7 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            if ((i3 & 2) == 0) {
                linearColor = j;
                int i8 = composerStartRestartGroup.changed(linearColor) ? 32 : 16;
                i4 |= i8;
            } else {
                linearColor = j;
            }
            i4 |= i8;
        } else {
            linearColor = j;
        }
        if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            linearTrackColor = j2;
            i4 |= ((i3 & 4) == 0 && composerStartRestartGroup.changed(linearTrackColor)) ? 256 : 128;
        } else {
            linearTrackColor = j2;
        }
        int i9 = i3 & 8;
        if (i9 == 0) {
            if ((i2 & 3072) == 0) {
                i5 = i;
                i4 |= composerStartRestartGroup.changed(i5) ? 2048 : 1024;
            }
            if ((i4 & 1171) == 1170 || !composerStartRestartGroup.getSkipping()) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i9 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i5;
                    }
                    modifier3 = companion;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i3 & 2) != 0) {
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                    }
                    linearTrackColor = linearTrackColor;
                    iM2080getLinearStrokeCapKaPHkGw = i5;
                    modifier3 = modifier2;
                }
                long j5 = linearColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-476865359, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:217)");
                }
                m2094LinearProgressIndicatorrIrjwxo(modifier3, j5, linearTrackColor, iM2080getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM(), composerStartRestartGroup, (i4 & 14) | 24576 | (i4 & 112) | (i4 & 896) | (i4 & 7168), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j3 = j5;
                j4 = linearTrackColor;
                i6 = iM2080getLinearStrokeCapKaPHkGw;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier4 = modifier2;
                j3 = linearColor;
                j4 = linearTrackColor;
                i6 = i5;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$6
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i10) {
                        ProgressIndicatorKt.m2088LinearProgressIndicator2cYBFYY(modifier4, j3, j4, i6, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 3072;
        i5 = i;
        if ((i4 & 1171) == 1170) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i7 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i9 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i5;
                }
                modifier3 = companion;
            } else {
                if (i7 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i9 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i5;
                }
                modifier3 = companion;
            }
            long j6 = linearColor;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-476865359, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:217)");
            }
            m2094LinearProgressIndicatorrIrjwxo(modifier3, j6, linearTrackColor, iM2080getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM(), composerStartRestartGroup, (i4 & 14) | 24576 | (i4 & 112) | (i4 & 896) | (i4 & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j3 = j6;
            j4 = linearTrackColor;
            i6 = iM2080getLinearStrokeCapKaPHkGw;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i7 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i9 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i5;
                }
                modifier3 = companion;
            } else {
                if (i7 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i9 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i5;
                }
                modifier3 = companion;
            }
            long j7 = linearColor;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-476865359, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:217)");
            }
            m2094LinearProgressIndicatorrIrjwxo(modifier3, j7, linearTrackColor, iM2080getLinearStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM(), composerStartRestartGroup, (i4 & 14) | 24576 | (i4 & 112) | (i4 & 896) | (i4 & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j3 = j7;
            j4 = linearTrackColor;
            i6 = iM2080getLinearStrokeCapKaPHkGw;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i10) {
                    ProgressIndicatorKt.m2088LinearProgressIndicator2cYBFYY(modifier4, j3, j4, i6, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x026f  */
    /* JADX WARN: Code duplicated, block: B:103:0x0275  */
    /* JADX WARN: Code duplicated, block: B:109:0x028a  */
    /* JADX WARN: Code duplicated, block: B:111:0x0290  */
    /* JADX WARN: Code duplicated, block: B:117:0x02a8  */
    /* JADX WARN: Code duplicated, block: B:121:0x02b6  */
    /* JADX WARN: Code duplicated, block: B:124:0x02dd  */
    /* JADX WARN: Code duplicated, block: B:128:0x02ef  */
    /* JADX WARN: Code duplicated, block: B:130:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:46:0x0080  */
    /* JADX WARN: Code duplicated, block: B:47:0x0083  */
    /* JADX WARN: Code duplicated, block: B:49:0x0087  */
    /* JADX WARN: Code duplicated, block: B:51:0x008f  */
    /* JADX WARN: Code duplicated, block: B:52:0x0092  */
    /* JADX WARN: Code duplicated, block: B:57:0x009f  */
    /* JADX WARN: Code duplicated, block: B:61:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:73:0x00ce A[PHI: r2 r4 r9 r11 r14
      0x00ce: PHI (r2v8 androidx.compose.ui.Modifier) = (r2v3 androidx.compose.ui.Modifier), (r2v11 androidx.compose.ui.Modifier) binds: [B:86:0x00fe, B:72:0x00cd] A[DONT_GENERATE, DONT_INLINE]
      0x00ce: PHI (r4v30 int) = (r4v14 int), (r4v32 int) binds: [B:86:0x00fe, B:72:0x00cd] A[DONT_GENERATE, DONT_INLINE]
      0x00ce: PHI (r9v5 long) = (r9v2 long), (r9v1 long) binds: [B:86:0x00fe, B:72:0x00cd] A[DONT_GENERATE, DONT_INLINE]
      0x00ce: PHI (r11v5 long) = (r11v2 long), (r11v1 long) binds: [B:86:0x00fe, B:72:0x00cd] A[DONT_GENERATE, DONT_INLINE]
      0x00ce: PHI (r14v41 int) = (r14v3 int), (r14v2 int) binds: [B:86:0x00fe, B:72:0x00cd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:75:0x00d3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:76:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:77:0x00da  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:83:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:87:0x0100  */
    /* JADX WARN: Code duplicated, block: B:90:0x0112  */
    /* JADX WARN: Code duplicated, block: B:93:0x0253  */
    /* JADX WARN: Code duplicated, block: B:94:0x0255  */
    /* JADX WARN: Code duplicated, block: B:97:0x025e  */
    /* JADX WARN: Code duplicated, block: B:98:0x0260  */
    /* JADX INFO: renamed from: LinearProgressIndicator-rIrjwxo, reason: not valid java name */
    public static final void m2094LinearProgressIndicatorrIrjwxo(Modifier modifier, long j, long j2, int i, float f, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        long linearColor;
        long linearTrackColor;
        int iM2080getLinearStrokeCapKaPHkGw;
        int i5;
        final float f2;
        int i6;
        Modifier.Companion companion;
        final float fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
        final int i7;
        final State<Float> stateAnimateFloat;
        final State<Float> stateAnimateFloat2;
        final State<Float> stateAnimateFloat3;
        final State<Float> stateAnimateFloat4;
        Composer composer2;
        boolean z;
        boolean z2;
        boolean zChanged;
        Object objRememberedValue;
        final long j3;
        final long j4;
        final Modifier modifier3;
        final int i8;
        final long j5;
        final long j6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(567589233);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LinearProgressIndicator)P(2,0:c#ui.graphics.Color,4:c#ui.graphics.Color,3:c#ui.graphics.StrokeCap,1:c#ui.unit.Dp)249@11000L11,250@11063L16,254@11257L28,259@11547L396,272@11995L396,285@12444L400,298@12897L400,315@13472L1839,310@13302L2009:ProgressIndicator.kt#uh7d8r");
        int i9 = i3 & 1;
        if (i9 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            linearColor = j;
            i4 |= ((i3 & 2) == 0 && composerStartRestartGroup.changed(linearColor)) ? 32 : 16;
        } else {
            linearColor = j;
        }
        if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            linearTrackColor = j2;
            i4 |= ((i3 & 4) == 0 && composerStartRestartGroup.changed(linearTrackColor)) ? 256 : 128;
        } else {
            linearTrackColor = j2;
        }
        int i10 = i3 & 8;
        if (i10 == 0) {
            if ((i2 & 3072) == 0) {
                iM2080getLinearStrokeCapKaPHkGw = i;
                i4 |= composerStartRestartGroup.changed(iM2080getLinearStrokeCapKaPHkGw) ? 2048 : 1024;
            }
            i5 = i3 & 16;
            if (i5 != 0) {
                if ((i2 & 24576) == 0) {
                    f2 = f;
                    if (composerStartRestartGroup.changed(f2)) {
                        i6 = 16384;
                    } else {
                        i6 = 8192;
                    }
                    i4 |= i6;
                }
                if ((i4 & 9363) == 9362 || !composerStartRestartGroup.getSkipping()) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 2) != 0) {
                            i4 &= -113;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        companion = modifier2;
                    } else {
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 2) != 0) {
                            linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                            i4 &= -113;
                        }
                        if ((i3 & 4) != 0) {
                            linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i10 != 0) {
                            iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                        }
                        if (i5 != 0) {
                            fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                        }
                        i7 = iM2080getLinearStrokeCapKaPHkGw;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:253)");
                        }
                        InfiniteTransition infiniteTransitionRememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
                        stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                invoke2(keyframesSpecConfig);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                keyframesSpecConfig.setDurationMillis(1800);
                                keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.FirstLineHeadEasing);
                                keyframesSpecConfig.at(Float.valueOf(1.0f), 750);
                            }
                        }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                        stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                invoke2(keyframesSpecConfig);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                keyframesSpecConfig.setDurationMillis(1800);
                                keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 333), ProgressIndicatorKt.FirstLineTailEasing);
                                keyframesSpecConfig.at(Float.valueOf(1.0f), 1183);
                            }
                        }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                        stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                invoke2(keyframesSpecConfig);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                keyframesSpecConfig.setDurationMillis(1800);
                                keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1000), ProgressIndicatorKt.SecondLineHeadEasing);
                                keyframesSpecConfig.at(Float.valueOf(1.0f), 1567);
                            }
                        }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                        stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                invoke2(keyframesSpecConfig);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                keyframesSpecConfig.setDurationMillis(1800);
                                keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1267), ProgressIndicatorKt.SecondLineTailEasing);
                                keyframesSpecConfig.at(Float.valueOf(1.0f), 1800);
                            }
                        }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                        composer2 = composerStartRestartGroup;
                        Modifier modifierM767sizeVpY3zN4 = SizeKt.m767sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion.then(IncreaseSemanticsBounds)), LinearIndicatorWidth, LinearIndicatorHeight);
                        ComposerKt.sourceInformationMarkerStart(composer2, 1145216297, "CC(remember):ProgressIndicator.kt#9igjgp");
                        if ((i4 & 7168) == 2048) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if ((57344 & i4) == 16384) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        zChanged = z2 | z | composer2.changed(stateAnimateFloat) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composer2.changed(linearTrackColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composer2.changed(stateAnimateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(linearColor)) || (i4 & 48) == 32) | composer2.changed(stateAnimateFloat3) | composer2.changed(stateAnimateFloat4);
                        objRememberedValue = composer2.rememberedValue();
                        if (!zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            j3 = linearColor;
                            j4 = linearTrackColor;
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    float fM6890constructorimpl;
                                    DrawScope drawScope2;
                                    float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                                    if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                        fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                                    } else {
                                        fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                                    }
                                    float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                                    if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                                        drawScope2 = drawScope;
                                        ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                                    } else {
                                        drawScope2 = drawScope;
                                    }
                                    if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                                        ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                                    }
                                    if (stateAnimateFloat2.getValue().floatValue() > f3) {
                                        ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                                    }
                                    if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                                        ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                                    }
                                    if (stateAnimateFloat4.getValue().floatValue() > f3) {
                                        ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                                    }
                                }
                            };
                            composer2.updateRememberedValue(objRememberedValue);
                        } else {
                            j3 = linearColor;
                            j4 = linearTrackColor;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        CanvasKt.Canvas(modifierM767sizeVpY3zN4, (Function1) objRememberedValue, composer2, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = companion;
                        i8 = i7;
                        f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                        j5 = j4;
                        j6 = j3;
                    }
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    i7 = iM2080getLinearStrokeCapKaPHkGw;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:253)");
                    }
                    InfiniteTransition infiniteTransitionRememberInfiniteTransition2 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
                    stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            invoke2(keyframesSpecConfig);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            keyframesSpecConfig.setDurationMillis(1800);
                            keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.FirstLineHeadEasing);
                            keyframesSpecConfig.at(Float.valueOf(1.0f), 750);
                        }
                    }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            invoke2(keyframesSpecConfig);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            keyframesSpecConfig.setDurationMillis(1800);
                            keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 333), ProgressIndicatorKt.FirstLineTailEasing);
                            keyframesSpecConfig.at(Float.valueOf(1.0f), 1183);
                        }
                    }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            invoke2(keyframesSpecConfig);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            keyframesSpecConfig.setDurationMillis(1800);
                            keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1000), ProgressIndicatorKt.SecondLineHeadEasing);
                            keyframesSpecConfig.at(Float.valueOf(1.0f), 1567);
                        }
                    }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            invoke2(keyframesSpecConfig);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            keyframesSpecConfig.setDurationMillis(1800);
                            keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1267), ProgressIndicatorKt.SecondLineTailEasing);
                            keyframesSpecConfig.at(Float.valueOf(1.0f), 1800);
                        }
                    }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    composer2 = composerStartRestartGroup;
                    Modifier modifierM767sizeVpY3zN5 = SizeKt.m767sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion.then(IncreaseSemanticsBounds)), LinearIndicatorWidth, LinearIndicatorHeight);
                    ComposerKt.sourceInformationMarkerStart(composer2, 1145216297, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((i4 & 7168) == 2048) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if ((57344 & i4) == 16384) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    zChanged = z2 | z | composer2.changed(stateAnimateFloat) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composer2.changed(linearTrackColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composer2.changed(stateAnimateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(linearColor)) || (i4 & 48) == 32) | composer2.changed(stateAnimateFloat3) | composer2.changed(stateAnimateFloat4);
                    objRememberedValue = composer2.rememberedValue();
                    if (zChanged) {
                        j3 = linearColor;
                        j4 = linearTrackColor;
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                float fM6890constructorimpl;
                                DrawScope drawScope2;
                                float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                                if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                    fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                                } else {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                                }
                                float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                                if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                                    drawScope2 = drawScope;
                                    ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                                } else {
                                    drawScope2 = drawScope;
                                }
                                if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                                    ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                                }
                                if (stateAnimateFloat2.getValue().floatValue() > f3) {
                                    ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                                }
                                if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                                    ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                                }
                                if (stateAnimateFloat4.getValue().floatValue() > f3) {
                                    ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                                }
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue);
                    } else {
                        j3 = linearColor;
                        j4 = linearTrackColor;
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                float fM6890constructorimpl;
                                DrawScope drawScope2;
                                float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                                if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                    fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                                } else {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                                }
                                float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                                if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                                    drawScope2 = drawScope;
                                    ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                                } else {
                                    drawScope2 = drawScope;
                                }
                                if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                                    ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                                }
                                if (stateAnimateFloat2.getValue().floatValue() > f3) {
                                    ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                                }
                                if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                                    ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                                }
                                if (stateAnimateFloat4.getValue().floatValue() > f3) {
                                    ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                                }
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    CanvasKt.Canvas(modifierM767sizeVpY3zN5, (Function1) objRememberedValue, composer2, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = companion;
                    i8 = i7;
                    f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                    j5 = j4;
                    j6 = j3;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    composer2 = composerStartRestartGroup;
                    modifier3 = modifier2;
                    j6 = linearColor;
                    j5 = linearTrackColor;
                    i8 = iM2080getLinearStrokeCapKaPHkGw;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$8
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11) {
                            ProgressIndicatorKt.m2094LinearProgressIndicatorrIrjwxo(modifier3, j6, j5, i8, f2, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 24576;
            f2 = f;
            if ((i4 & 9363) == 9362) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) == 0) {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i10 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    }
                    if (i5 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    }
                } else {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i10 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    }
                    if (i5 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    }
                }
                i7 = iM2080getLinearStrokeCapKaPHkGw;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:253)");
                }
                InfiniteTransition infiniteTransitionRememberInfiniteTransition3 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
                stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.FirstLineHeadEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 750);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 333), ProgressIndicatorKt.FirstLineTailEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 1183);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1000), ProgressIndicatorKt.SecondLineHeadEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 1567);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1267), ProgressIndicatorKt.SecondLineTailEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 1800);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                composer2 = composerStartRestartGroup;
                Modifier modifierM767sizeVpY3zN6 = SizeKt.m767sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion.then(IncreaseSemanticsBounds)), LinearIndicatorWidth, LinearIndicatorHeight);
                ComposerKt.sourceInformationMarkerStart(composer2, 1145216297, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 7168) == 2048) {
                    z = true;
                } else {
                    z = false;
                }
                if ((57344 & i4) == 16384) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                zChanged = z2 | z | composer2.changed(stateAnimateFloat) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composer2.changed(linearTrackColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composer2.changed(stateAnimateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(linearColor)) || (i4 & 48) == 32) | composer2.changed(stateAnimateFloat3) | composer2.changed(stateAnimateFloat4);
                objRememberedValue = composer2.rememberedValue();
                if (zChanged) {
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                            } else {
                                drawScope2 = drawScope;
                            }
                            if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat2.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat4.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                } else {
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                            } else {
                                drawScope2 = drawScope;
                            }
                            if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat2.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat4.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                CanvasKt.Canvas(modifierM767sizeVpY3zN6, (Function1) objRememberedValue, composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = companion;
                i8 = i7;
                f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                j5 = j4;
                j6 = j3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) == 0) {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i10 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    }
                    if (i5 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    }
                } else {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i10 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    }
                    if (i5 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    }
                }
                i7 = iM2080getLinearStrokeCapKaPHkGw;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:253)");
                }
                InfiniteTransition infiniteTransitionRememberInfiniteTransition4 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
                stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.FirstLineHeadEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 750);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 333), ProgressIndicatorKt.FirstLineTailEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 1183);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1000), ProgressIndicatorKt.SecondLineHeadEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 1567);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1267), ProgressIndicatorKt.SecondLineTailEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 1800);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                composer2 = composerStartRestartGroup;
                Modifier modifierM767sizeVpY3zN7 = SizeKt.m767sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion.then(IncreaseSemanticsBounds)), LinearIndicatorWidth, LinearIndicatorHeight);
                ComposerKt.sourceInformationMarkerStart(composer2, 1145216297, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 7168) == 2048) {
                    z = true;
                } else {
                    z = false;
                }
                if ((57344 & i4) == 16384) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                zChanged = z2 | z | composer2.changed(stateAnimateFloat) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composer2.changed(linearTrackColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composer2.changed(stateAnimateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(linearColor)) || (i4 & 48) == 32) | composer2.changed(stateAnimateFloat3) | composer2.changed(stateAnimateFloat4);
                objRememberedValue = composer2.rememberedValue();
                if (zChanged) {
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                            } else {
                                drawScope2 = drawScope;
                            }
                            if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat2.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat4.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                } else {
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                            } else {
                                drawScope2 = drawScope;
                            }
                            if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat2.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat4.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                CanvasKt.Canvas(modifierM767sizeVpY3zN7, (Function1) objRememberedValue, composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = companion;
                i8 = i7;
                f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                j5 = j4;
                j6 = j3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$8
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i11) {
                        ProgressIndicatorKt.m2094LinearProgressIndicatorrIrjwxo(modifier3, j6, j5, i8, f2, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 3072;
        iM2080getLinearStrokeCapKaPHkGw = i;
        i5 = i3 & 16;
        if (i5 != 0) {
            if ((i2 & 24576) == 0) {
                f2 = f;
                if (composerStartRestartGroup.changed(f2)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i4 |= i6;
            }
            if ((i4 & 9363) == 9362) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) == 0) {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i10 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    }
                    if (i5 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    }
                } else {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i10 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    }
                    if (i5 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    }
                }
                i7 = iM2080getLinearStrokeCapKaPHkGw;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:253)");
                }
                InfiniteTransition infiniteTransitionRememberInfiniteTransition5 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
                stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition5, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.FirstLineHeadEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 750);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition5, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 333), ProgressIndicatorKt.FirstLineTailEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 1183);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition5, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1000), ProgressIndicatorKt.SecondLineHeadEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 1567);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition5, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1267), ProgressIndicatorKt.SecondLineTailEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 1800);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                composer2 = composerStartRestartGroup;
                Modifier modifierM767sizeVpY3zN8 = SizeKt.m767sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion.then(IncreaseSemanticsBounds)), LinearIndicatorWidth, LinearIndicatorHeight);
                ComposerKt.sourceInformationMarkerStart(composer2, 1145216297, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 7168) == 2048) {
                    z = true;
                } else {
                    z = false;
                }
                if ((57344 & i4) == 16384) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                zChanged = z2 | z | composer2.changed(stateAnimateFloat) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composer2.changed(linearTrackColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composer2.changed(stateAnimateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(linearColor)) || (i4 & 48) == 32) | composer2.changed(stateAnimateFloat3) | composer2.changed(stateAnimateFloat4);
                objRememberedValue = composer2.rememberedValue();
                if (zChanged) {
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                            } else {
                                drawScope2 = drawScope;
                            }
                            if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat2.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat4.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                } else {
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                            } else {
                                drawScope2 = drawScope;
                            }
                            if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat2.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat4.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                CanvasKt.Canvas(modifierM767sizeVpY3zN8, (Function1) objRememberedValue, composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = companion;
                i8 = i7;
                f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                j5 = j4;
                j6 = j3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) == 0) {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i10 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    }
                    if (i5 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    }
                } else {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i10 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    }
                    if (i5 != 0) {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    }
                }
                i7 = iM2080getLinearStrokeCapKaPHkGw;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:253)");
                }
                InfiniteTransition infiniteTransitionRememberInfiniteTransition6 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
                stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition6, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.FirstLineHeadEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 750);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition6, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 333), ProgressIndicatorKt.FirstLineTailEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 1183);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition6, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1000), ProgressIndicatorKt.SecondLineHeadEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 1567);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition6, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1800);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1267), ProgressIndicatorKt.SecondLineTailEasing);
                        keyframesSpecConfig.at(Float.valueOf(1.0f), 1800);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                composer2 = composerStartRestartGroup;
                Modifier modifierM767sizeVpY3zN9 = SizeKt.m767sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion.then(IncreaseSemanticsBounds)), LinearIndicatorWidth, LinearIndicatorHeight);
                ComposerKt.sourceInformationMarkerStart(composer2, 1145216297, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 7168) == 2048) {
                    z = true;
                } else {
                    z = false;
                }
                if ((57344 & i4) == 16384) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                zChanged = z2 | z | composer2.changed(stateAnimateFloat) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composer2.changed(linearTrackColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composer2.changed(stateAnimateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(linearColor)) || (i4 & 48) == 32) | composer2.changed(stateAnimateFloat3) | composer2.changed(stateAnimateFloat4);
                objRememberedValue = composer2.rememberedValue();
                if (zChanged) {
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                            } else {
                                drawScope2 = drawScope;
                            }
                            if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat2.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat4.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                } else {
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            DrawScope drawScope2;
                            float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                            if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                            }
                            float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                            if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                            } else {
                                drawScope2 = drawScope;
                            }
                            if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat2.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                            }
                            if (stateAnimateFloat4.getValue().floatValue() > f3) {
                                ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                            }
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                CanvasKt.Canvas(modifierM767sizeVpY3zN9, (Function1) objRememberedValue, composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = companion;
                i8 = i7;
                f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                j5 = j4;
                j6 = j3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$8
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i11) {
                        ProgressIndicatorKt.m2094LinearProgressIndicatorrIrjwxo(modifier3, j6, j5, i8, f2, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 24576;
        f2 = f;
        if ((i4 & 9363) == 9362) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) == 0) {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i10 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                }
                if (i5 != 0) {
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                }
            } else {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i10 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                }
                if (i5 != 0) {
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                }
            }
            i7 = iM2080getLinearStrokeCapKaPHkGw;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:253)");
            }
            InfiniteTransition infiniteTransitionRememberInfiniteTransition7 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
            stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition7, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    keyframesSpecConfig.setDurationMillis(1800);
                    keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.FirstLineHeadEasing);
                    keyframesSpecConfig.at(Float.valueOf(1.0f), 750);
                }
            }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition7, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    keyframesSpecConfig.setDurationMillis(1800);
                    keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 333), ProgressIndicatorKt.FirstLineTailEasing);
                    keyframesSpecConfig.at(Float.valueOf(1.0f), 1183);
                }
            }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition7, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    keyframesSpecConfig.setDurationMillis(1800);
                    keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1000), ProgressIndicatorKt.SecondLineHeadEasing);
                    keyframesSpecConfig.at(Float.valueOf(1.0f), 1567);
                }
            }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition7, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    keyframesSpecConfig.setDurationMillis(1800);
                    keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1267), ProgressIndicatorKt.SecondLineTailEasing);
                    keyframesSpecConfig.at(Float.valueOf(1.0f), 1800);
                }
            }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            composer2 = composerStartRestartGroup;
            Modifier modifierM767sizeVpY3zN10 = SizeKt.m767sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion.then(IncreaseSemanticsBounds)), LinearIndicatorWidth, LinearIndicatorHeight);
            ComposerKt.sourceInformationMarkerStart(composer2, 1145216297, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 7168) == 2048) {
                z = true;
            } else {
                z = false;
            }
            if ((57344 & i4) == 16384) {
                z2 = true;
            } else {
                z2 = false;
            }
            zChanged = z2 | z | composer2.changed(stateAnimateFloat) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composer2.changed(linearTrackColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composer2.changed(stateAnimateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(linearColor)) || (i4 & 48) == 32) | composer2.changed(stateAnimateFloat3) | composer2.changed(stateAnimateFloat4);
            objRememberedValue = composer2.rememberedValue();
            if (zChanged) {
                j3 = linearColor;
                j4 = linearTrackColor;
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        float fM6890constructorimpl;
                        DrawScope drawScope2;
                        float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                        if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                            fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                        }
                        float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                        if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                            drawScope2 = drawScope;
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                        } else {
                            drawScope2 = drawScope;
                        }
                        if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                        }
                        if (stateAnimateFloat2.getValue().floatValue() > f3) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                        }
                        if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                        }
                        if (stateAnimateFloat4.getValue().floatValue() > f3) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                        }
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            } else {
                j3 = linearColor;
                j4 = linearTrackColor;
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        float fM6890constructorimpl;
                        DrawScope drawScope2;
                        float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                        if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                            fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                        }
                        float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                        if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                            drawScope2 = drawScope;
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                        } else {
                            drawScope2 = drawScope;
                        }
                        if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                        }
                        if (stateAnimateFloat2.getValue().floatValue() > f3) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                        }
                        if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                        }
                        if (stateAnimateFloat4.getValue().floatValue() > f3) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                        }
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            CanvasKt.Canvas(modifierM767sizeVpY3zN10, (Function1) objRememberedValue, composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = companion;
            i8 = i7;
            f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
            j5 = j4;
            j6 = j3;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) == 0) {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i10 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                }
                if (i5 != 0) {
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                }
            } else {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i10 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                }
                if (i5 != 0) {
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2079getLinearIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM2079getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                }
            }
            i7 = iM2080getLinearStrokeCapKaPHkGw;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:253)");
            }
            InfiniteTransition infiniteTransitionRememberInfiniteTransition8 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
            stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition8, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    keyframesSpecConfig.setDurationMillis(1800);
                    keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.FirstLineHeadEasing);
                    keyframesSpecConfig.at(Float.valueOf(1.0f), 750);
                }
            }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition8, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    keyframesSpecConfig.setDurationMillis(1800);
                    keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 333), ProgressIndicatorKt.FirstLineTailEasing);
                    keyframesSpecConfig.at(Float.valueOf(1.0f), 1183);
                }
            }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition8, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    keyframesSpecConfig.setDurationMillis(1800);
                    keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1000), ProgressIndicatorKt.SecondLineHeadEasing);
                    keyframesSpecConfig.at(Float.valueOf(1.0f), 1567);
                }
            }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition8, 0.0f, 1.0f, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    keyframesSpecConfig.setDurationMillis(1800);
                    keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 1267), ProgressIndicatorKt.SecondLineTailEasing);
                    keyframesSpecConfig.at(Float.valueOf(1.0f), 1800);
                }
            }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            composer2 = composerStartRestartGroup;
            Modifier modifierM767sizeVpY3zN11 = SizeKt.m767sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion.then(IncreaseSemanticsBounds)), LinearIndicatorWidth, LinearIndicatorHeight);
            ComposerKt.sourceInformationMarkerStart(composer2, 1145216297, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 7168) == 2048) {
                z = true;
            } else {
                z = false;
            }
            if ((57344 & i4) == 16384) {
                z2 = true;
            } else {
                z2 = false;
            }
            zChanged = z2 | z | composer2.changed(stateAnimateFloat) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composer2.changed(linearTrackColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composer2.changed(stateAnimateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(linearColor)) || (i4 & 48) == 32) | composer2.changed(stateAnimateFloat3) | composer2.changed(stateAnimateFloat4);
            objRememberedValue = composer2.rememberedValue();
            if (zChanged) {
                j3 = linearColor;
                j4 = linearTrackColor;
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        float fM6890constructorimpl;
                        DrawScope drawScope2;
                        float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                        if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                            fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                        }
                        float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                        if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                            drawScope2 = drawScope;
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                        } else {
                            drawScope2 = drawScope;
                        }
                        if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                        }
                        if (stateAnimateFloat2.getValue().floatValue() > f3) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                        }
                        if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                        }
                        if (stateAnimateFloat4.getValue().floatValue() > f3) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                        }
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            } else {
                j3 = linearColor;
                j4 = linearTrackColor;
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        float fM6890constructorimpl;
                        DrawScope drawScope2;
                        float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
                        if (StrokeCap.m4527equalsimpl0(i7, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                            fM6890constructorimpl = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(fM2079getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo402toDpu2uoSUM(fM3936getHeightimpl));
                        }
                        float f3 = fM6890constructorimpl / drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()));
                        if (stateAnimateFloat.getValue().floatValue() < 1.0f - f3) {
                            drawScope2 = drawScope;
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue() > 0.0f ? stateAnimateFloat.getValue().floatValue() + f3 : 0.0f, 1.0f, j4, fM3936getHeightimpl, i7);
                        } else {
                            drawScope2 = drawScope;
                        }
                        if (stateAnimateFloat.getValue().floatValue() - stateAnimateFloat2.getValue().floatValue() > 0.0f) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat.getValue().floatValue(), stateAnimateFloat2.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                        }
                        if (stateAnimateFloat2.getValue().floatValue() > f3) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue() > 0.0f ? stateAnimateFloat3.getValue().floatValue() + f3 : 0.0f, stateAnimateFloat2.getValue().floatValue() < 1.0f ? stateAnimateFloat2.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                        }
                        if (stateAnimateFloat3.getValue().floatValue() - stateAnimateFloat4.getValue().floatValue() > 0.0f) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, stateAnimateFloat3.getValue().floatValue(), stateAnimateFloat4.getValue().floatValue(), j3, fM3936getHeightimpl, i7);
                        }
                        if (stateAnimateFloat4.getValue().floatValue() > f3) {
                            ProgressIndicatorKt.m2104drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, stateAnimateFloat4.getValue().floatValue() < 1.0f ? stateAnimateFloat4.getValue().floatValue() - f3 : 1.0f, j4, fM3936getHeightimpl, i7);
                        }
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            CanvasKt.Canvas(modifierM767sizeVpY3zN11, (Function1) objRememberedValue, composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = companion;
            i8 = i7;
            f2 = fM2079getLinearIndicatorTrackGapSizeD9Ej5fM;
            j5 = j4;
            j6 = j3;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$8
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i11) {
                    ProgressIndicatorKt.m2094LinearProgressIndicatorrIrjwxo(modifier3, j6, j5, i8, f2, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0158  */
    /* JADX WARN: Code duplicated, block: B:104:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004a  */
    /* JADX WARN: Code duplicated, block: B:31:0x0059  */
    /* JADX WARN: Code duplicated, block: B:33:0x005d  */
    /* JADX WARN: Code duplicated, block: B:36:0x0063  */
    /* JADX WARN: Code duplicated, block: B:41:0x0072  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:46:0x007c  */
    /* JADX WARN: Code duplicated, block: B:47:0x007f  */
    /* JADX WARN: Code duplicated, block: B:49:0x0083  */
    /* JADX WARN: Code duplicated, block: B:51:0x008b  */
    /* JADX WARN: Code duplicated, block: B:52:0x008e  */
    /* JADX WARN: Code duplicated, block: B:57:0x009a  */
    /* JADX WARN: Code duplicated, block: B:61:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c9 A[PHI: r3 r6 r9 r11
      0x00c9: PHI (r3v22 int) = (r3v17 int), (r3v23 int), (r3v24 int) binds: [B:82:0x00ee, B:70:0x00c5, B:71:0x00c7] A[DONT_GENERATE, DONT_INLINE]
      0x00c9: PHI (r6v7 androidx.compose.ui.Modifier) = (r6v3 androidx.compose.ui.Modifier), (r6v2 androidx.compose.ui.Modifier), (r6v2 androidx.compose.ui.Modifier) binds: [B:82:0x00ee, B:70:0x00c5, B:71:0x00c7] A[DONT_GENERATE, DONT_INLINE]
      0x00c9: PHI (r9v7 long) = (r9v2 long), (r9v1 long), (r9v1 long) binds: [B:82:0x00ee, B:70:0x00c5, B:71:0x00c7] A[DONT_GENERATE, DONT_INLINE]
      0x00c9: PHI (r11v6 long) = (r11v2 long), (r11v1 long), (r11v1 long) binds: [B:82:0x00ee, B:70:0x00c5, B:71:0x00c7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:74:0x00ce A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:75:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:78:0x00da  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:83:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:86:0x0101  */
    /* JADX WARN: Code duplicated, block: B:89:0x0113  */
    /* JADX WARN: Code duplicated, block: B:90:0x0115  */
    /* JADX WARN: Code duplicated, block: B:93:0x011c  */
    /* JADX WARN: Code duplicated, block: B:95:0x0124  */
    /* JADX WARN: Code duplicated, block: B:98:0x014b  */
    @Deprecated(message = "Use the overload that takes `progress` as a lambda", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(\nprogress = { progress },\nmodifier = modifier,\ncolor = color,\ntrackColor = trackColor,\nstrokeCap = strokeCap,\n)", imports = {}))
    /* JADX INFO: renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    public static final void m2091LinearProgressIndicator_5eSRE(final float f, Modifier modifier, long j, long j2, int i, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long linearColor;
        long linearTrackColor;
        int i5;
        int i6;
        int i7;
        int iM2080getLinearStrokeCapKaPHkGw;
        boolean z;
        Object objRememberedValue;
        Composer composer2;
        final Modifier modifier3;
        final long j3;
        final long j4;
        final int i8;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i9;
        int i10;
        Composer composerStartRestartGroup = composer.startRestartGroup(905419617);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LinearProgressIndicator)P(2,1,0:c#ui.graphics.Color,4:c#ui.graphics.Color,3:c#ui.graphics.StrokeCap)385@15871L11,386@15934L16,390@16074L12,389@16030L179:ProgressIndicator.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(f) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i11 = i3 & 2;
        if (i11 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                linearColor = j;
                if ((i3 & 4) == 0 || !composerStartRestartGroup.changed(linearColor)) {
                    i10 = 128;
                } else {
                    i10 = 256;
                }
                i4 |= i10;
            } else {
                linearColor = j;
            }
            if ((i2 & 3072) == 0) {
                linearTrackColor = j2;
                if ((i3 & 8) == 0 || !composerStartRestartGroup.changed(linearTrackColor)) {
                    i9 = 1024;
                } else {
                    i9 = 2048;
                }
                i4 |= i9;
            } else {
                linearTrackColor = j2;
            }
            i5 = i3 & 16;
            if (i5 != 0) {
                if ((i2 & 24576) == 0) {
                    i6 = i;
                    if (composerStartRestartGroup.changed(i6)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i4 |= i7;
                }
                if ((i4 & 9363) == 9362 || !composerStartRestartGroup.getSkipping()) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                            i4 &= -7169;
                        }
                        if (i5 != 0) {
                            iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                        }
                        long j5 = linearTrackColor;
                        long j6 = linearColor;
                        Modifier modifier4 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:389)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145297734, "CC(remember):ProgressIndicator.kt#9igjgp");
                        if ((i4 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Float invoke() {
                                    return Float.valueOf(f);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composer2 = composerStartRestartGroup;
                        m2089LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue, modifier4, j6, j5, iM2080getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        j3 = j6;
                        j4 = j5;
                        i8 = iM2080getLinearStrokeCapKaPHkGw;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                        }
                    }
                    iM2080getLinearStrokeCapKaPHkGw = i6;
                    long j7 = linearTrackColor;
                    long j8 = linearColor;
                    Modifier modifier5 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:389)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145297734, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((i4 & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z) {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    m2089LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue, modifier5, j8, j7, iM2080getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier5;
                    j3 = j8;
                    j4 = j7;
                    i8 = iM2080getLinearStrokeCapKaPHkGw;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    composer2 = composerStartRestartGroup;
                    modifier3 = modifier2;
                    j3 = linearColor;
                    j4 = linearTrackColor;
                    i8 = i6;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$10
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i12) {
                            ProgressIndicatorKt.m2091LinearProgressIndicator_5eSRE(f, modifier3, j3, j4, i8, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 24576;
            i6 = i;
            if ((i4 & 9363) == 9362) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                } else {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                }
                long j9 = linearTrackColor;
                long j10 = linearColor;
                Modifier modifier6 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:389)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145297734, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                m2089LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue, modifier6, j10, j9, iM2080getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier6;
                j3 = j10;
                j4 = j9;
                i8 = iM2080getLinearStrokeCapKaPHkGw;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                } else {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                }
                long j11 = linearTrackColor;
                long j12 = linearColor;
                Modifier modifier7 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:389)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145297734, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                m2089LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue, modifier7, j12, j11, iM2080getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier7;
                j3 = j12;
                j4 = j11;
                i8 = iM2080getLinearStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$10
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i12) {
                        ProgressIndicatorKt.m2091LinearProgressIndicator_5eSRE(f, modifier3, j3, j4, i8, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            linearColor = j;
            if ((i3 & 4) == 0) {
                i10 = 128;
            } else {
                i10 = 128;
            }
            i4 |= i10;
        } else {
            linearColor = j;
        }
        if ((i2 & 3072) == 0) {
            linearTrackColor = j2;
            if ((i3 & 8) == 0) {
                i9 = 1024;
            } else {
                i9 = 1024;
            }
            i4 |= i9;
        } else {
            linearTrackColor = j2;
        }
        i5 = i3 & 16;
        if (i5 != 0) {
            if ((i2 & 24576) == 0) {
                i6 = i;
                if (composerStartRestartGroup.changed(i6)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i4 |= i7;
            }
            if ((i4 & 9363) == 9362) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                } else {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                }
                long j13 = linearTrackColor;
                long j14 = linearColor;
                Modifier modifier8 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:389)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145297734, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                m2089LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue, modifier8, j14, j13, iM2080getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier8;
                j3 = j14;
                j4 = j13;
                i8 = iM2080getLinearStrokeCapKaPHkGw;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                } else {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    }
                    if (i5 != 0) {
                        iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                    } else {
                        iM2080getLinearStrokeCapKaPHkGw = i6;
                    }
                }
                long j15 = linearTrackColor;
                long j16 = linearColor;
                Modifier modifier9 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:389)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145297734, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                m2089LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue, modifier9, j16, j15, iM2080getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier9;
                j3 = j16;
                j4 = j15;
                i8 = iM2080getLinearStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$10
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i12) {
                        ProgressIndicatorKt.m2091LinearProgressIndicator_5eSRE(f, modifier3, j3, j4, i8, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 24576;
        i6 = i;
        if ((i4 & 9363) == 9362) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i6;
                }
            } else {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i6;
                }
            }
            long j17 = linearTrackColor;
            long j18 = linearColor;
            Modifier modifier10 = modifier2;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:389)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145297734, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 14) == 4) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z) {
                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(f);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(f);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            m2089LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue, modifier10, j18, j17, iM2080getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier10;
            j3 = j18;
            j4 = j17;
            i8 = iM2080getLinearStrokeCapKaPHkGw;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i6;
                }
            } else {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i4 &= -7169;
                }
                if (i5 != 0) {
                    iM2080getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw();
                } else {
                    iM2080getLinearStrokeCapKaPHkGw = i6;
                }
            }
            long j19 = linearTrackColor;
            long j110 = linearColor;
            Modifier modifier11 = modifier2;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:389)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1145297734, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 14) == 4) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z) {
                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(f);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(f);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            m2089LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue, modifier11, j110, j19, iM2080getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier11;
            j3 = j110;
            j4 = j19;
            i8 = iM2080getLinearStrokeCapKaPHkGw;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$10
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i12) {
                    ProgressIndicatorKt.m2091LinearProgressIndicator_5eSRE(f, modifier3, j3, j4, i8, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x004b  */
    /* JADX WARN: Code duplicated, block: B:28:0x004f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0057  */
    /* JADX WARN: Code duplicated, block: B:31:0x005a  */
    /* JADX WARN: Code duplicated, block: B:34:0x0060  */
    /* JADX WARN: Code duplicated, block: B:37:0x0066  */
    /* JADX WARN: Code duplicated, block: B:42:0x0075  */
    /* JADX WARN: Code duplicated, block: B:44:0x0079  */
    /* JADX WARN: Code duplicated, block: B:51:0x0090  */
    /* JADX WARN: Code duplicated, block: B:53:0x0097  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:64:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:68:0x00be  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:72:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:73:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:76:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:79:0x0109  */
    /* JADX WARN: Code duplicated, block: B:83:0x0115  */
    /* JADX WARN: Code duplicated, block: B:85:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: LinearProgressIndicator-eaDK9VM, reason: not valid java name */
    public static final /* synthetic */ void m2093LinearProgressIndicatoreaDK9VM(final float f, Modifier modifier, long j, long j2, Composer composer, final int i, final int i2) {
        float f2;
        int i3;
        Modifier modifier2;
        long j3;
        long j4;
        Modifier.Companion companion;
        long linearColor;
        long linearTrackColor;
        Modifier modifier3;
        long j5;
        final Modifier modifier4;
        final long j6;
        final long j7;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-372717133);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LinearProgressIndicator)P(2,1,0:c#ui.graphics.Color,3:c#ui.graphics.Color)403@16462L11,404@16525L16,406@16551L164:ProgressIndicator.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            f2 = f;
        } else {
            f2 = f;
            if ((i & 6) == 0) {
                i3 = (composerStartRestartGroup.changed(f2) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        int i5 = i2 & 2;
        if (i5 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if ((i2 & 4) == 0) {
                    j3 = j;
                    int i6 = composerStartRestartGroup.changed(j3) ? 256 : 128;
                    i3 |= i6;
                } else {
                    j3 = j;
                }
                i3 |= i6;
            } else {
                j3 = j;
            }
            if ((i & 3072) == 0) {
                j4 = j2;
                if ((i2 & 8) == 0 || !composerStartRestartGroup.changed(j4)) {
                    i4 = 1024;
                } else {
                    i4 = 2048;
                }
                i3 |= i4;
            } else {
                j4 = j2;
            }
            if ((i3 & 1171) == 1170 || !composerStartRestartGroup.getSkipping()) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i5 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    } else {
                        linearColor = j3;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        modifier3 = companion;
                        j5 = linearColor;
                        linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    } else {
                        linearTrackColor = j4;
                        modifier3 = companion;
                        j5 = linearColor;
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                    }
                    linearTrackColor = j4;
                    modifier3 = modifier2;
                    j5 = j3;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-372717133, i3, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:406)");
                }
                m2091LinearProgressIndicator_5eSRE(f2, modifier3, j5, linearTrackColor, ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j6 = j5;
                j7 = linearTrackColor;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier4 = modifier2;
                j6 = j3;
                j7 = j4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$11
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i7) {
                        ProgressIndicatorKt.m2093LinearProgressIndicatoreaDK9VM(f, modifier4, j6, j7, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i2 & 4) == 0) {
                j3 = j;
                if (composerStartRestartGroup.changed(j3)) {
                }
                i3 |= i6;
            } else {
                j3 = j;
            }
            i3 |= i6;
        } else {
            j3 = j;
        }
        if ((i & 3072) == 0) {
            j4 = j2;
            if ((i2 & 8) == 0) {
                i4 = 1024;
            } else {
                i4 = 1024;
            }
            i3 |= i4;
        } else {
            j4 = j2;
        }
        if ((i3 & 1171) == 1170) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i5 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    linearColor = j3;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    modifier3 = companion;
                    j5 = linearColor;
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                } else {
                    linearTrackColor = j4;
                    modifier3 = companion;
                    j5 = linearColor;
                }
            } else {
                if (i5 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    linearColor = j3;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    modifier3 = companion;
                    j5 = linearColor;
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                } else {
                    linearTrackColor = j4;
                    modifier3 = companion;
                    j5 = linearColor;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-372717133, i3, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:406)");
            }
            m2091LinearProgressIndicator_5eSRE(f2, modifier3, j5, linearTrackColor, ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j6 = j5;
            j7 = linearTrackColor;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i5 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    linearColor = j3;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    modifier3 = companion;
                    j5 = linearColor;
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                } else {
                    linearTrackColor = j4;
                    modifier3 = companion;
                    j5 = linearColor;
                }
            } else {
                if (i5 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    linearColor = j3;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    modifier3 = companion;
                    j5 = linearColor;
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                } else {
                    linearTrackColor = j4;
                    modifier3 = companion;
                    j5 = linearColor;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-372717133, i3, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:406)");
            }
            m2091LinearProgressIndicator_5eSRE(f2, modifier3, j5, linearTrackColor, ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j6 = j5;
            j7 = linearTrackColor;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$11
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i7) {
                    ProgressIndicatorKt.m2093LinearProgressIndicatoreaDK9VM(f, modifier4, j6, j7, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: LinearProgressIndicator-RIQooxk, reason: not valid java name */
    public static final /* synthetic */ void m2090LinearProgressIndicatorRIQooxk(Modifier modifier, long j, long j2, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long linearColor;
        long linearTrackColor;
        long j3;
        Modifier modifier3;
        final Modifier modifier4;
        final long j4;
        final long j5;
        Composer composerStartRestartGroup = composer.startRestartGroup(585576195);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LinearProgressIndicator)P(1,0:c#ui.graphics.Color,2:c#ui.graphics.Color)418@16922L11,419@16985L16,421@17011L146:ProgressIndicator.kt#uh7d8r");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                linearColor = j;
                int i5 = composerStartRestartGroup.changed(linearColor) ? 32 : 16;
                i3 |= i5;
            } else {
                linearColor = j;
            }
            i3 |= i5;
        } else {
            linearColor = j;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i2 & 4) == 0) {
                linearTrackColor = j2;
                int i6 = composerStartRestartGroup.changed(linearTrackColor) ? 256 : 128;
                i3 |= i6;
            } else {
                linearTrackColor = j2;
            }
            i3 |= i6;
        } else {
            linearTrackColor = j2;
        }
        if ((i3 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier4 = modifier2;
            j4 = linearColor;
            j5 = linearTrackColor;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                Modifier.Companion companion = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i2 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                }
                j3 = linearTrackColor;
                modifier3 = companion;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 2) != 0) {
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
                j3 = linearTrackColor;
                modifier3 = modifier2;
            }
            long j6 = linearColor;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(585576195, i3, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:421)");
            }
            m2094LinearProgressIndicatorrIrjwxo(modifier3, j6, j3, ProgressIndicatorDefaults.INSTANCE.m2080getLinearStrokeCapKaPHkGw(), 0.0f, composerStartRestartGroup, (i3 & 14) | 3072 | (i3 & 112) | (i3 & 896), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j4 = j6;
            j5 = j3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$12
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i7) {
                    ProgressIndicatorKt.m2090LinearProgressIndicatorRIQooxk(modifier4, j4, j5, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawLinearIndicator-qYKTg0g, reason: not valid java name */
    public static final void m2104drawLinearIndicatorqYKTg0g(DrawScope drawScope, float f, float f2, long j, float f3, int i) {
        float fM3939getWidthimpl = Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc());
        float fM3936getHeightimpl = Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc());
        float f4 = fM3936getHeightimpl / 2.0f;
        boolean z = drawScope.getLayoutDirection() == LayoutDirection.Ltr;
        float f5 = (z ? f : 1.0f - f2) * fM3939getWidthimpl;
        float f6 = (z ? f2 : 1.0f - f) * fM3939getWidthimpl;
        if (StrokeCap.m4527equalsimpl0(i, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || fM3936getHeightimpl > fM3939getWidthimpl) {
            DrawScope.CC.m4724drawLineNGM6Ib0$default(drawScope, j, OffsetKt.Offset(f5, f4), OffsetKt.Offset(f6, f4), f3, 0, null, 0.0f, null, 0, 496, null);
            return;
        }
        float f7 = f3 / 2.0f;
        ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo = RangesKt.rangeTo(f7, fM3939getWidthimpl - f7);
        float fFloatValue = ((Number) RangesKt.coerceIn(Float.valueOf(f5), closedFloatingPointRangeRangeTo)).floatValue();
        float fFloatValue2 = ((Number) RangesKt.coerceIn(Float.valueOf(f6), closedFloatingPointRangeRangeTo)).floatValue();
        if (Math.abs(f2 - f) > 0.0f) {
            DrawScope.CC.m4724drawLineNGM6Ib0$default(drawScope, j, OffsetKt.Offset(fFloatValue, f4), OffsetKt.Offset(fFloatValue2, f4), f3, i, null, 0.0f, null, 0, 480, null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:103:0x0145  */
    /* JADX WARN: Code duplicated, block: B:106:0x0176  */
    /* JADX WARN: Code duplicated, block: B:110:0x0185  */
    /* JADX WARN: Code duplicated, block: B:112:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004b  */
    /* JADX WARN: Code duplicated, block: B:28:0x004f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0057  */
    /* JADX WARN: Code duplicated, block: B:31:0x005a  */
    /* JADX WARN: Code duplicated, block: B:34:0x0060  */
    /* JADX WARN: Code duplicated, block: B:37:0x0066  */
    /* JADX WARN: Code duplicated, block: B:38:0x0069  */
    /* JADX WARN: Code duplicated, block: B:40:0x006d  */
    /* JADX WARN: Code duplicated, block: B:42:0x0075  */
    /* JADX WARN: Code duplicated, block: B:43:0x0078  */
    /* JADX WARN: Code duplicated, block: B:48:0x0082  */
    /* JADX WARN: Code duplicated, block: B:50:0x0086  */
    /* JADX WARN: Code duplicated, block: B:52:0x008e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0091  */
    /* JADX WARN: Code duplicated, block: B:56:0x0097  */
    /* JADX WARN: Code duplicated, block: B:59:0x009f  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:65:0x00af  */
    /* JADX WARN: Code duplicated, block: B:70:0x00be  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:76:0x00db  */
    /* JADX WARN: Code duplicated, block: B:86:0x00fa A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:87:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:88:0x0101  */
    /* JADX WARN: Code duplicated, block: B:91:0x0107  */
    /* JADX WARN: Code duplicated, block: B:93:0x0111  */
    /* JADX WARN: Code duplicated, block: B:96:0x011c  */
    /* JADX WARN: Code duplicated, block: B:98:0x0126  */
    /* JADX WARN: Code duplicated, block: B:99:0x0133  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize`, see `LegacyCircularProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "CircularProgressIndicator(progress, modifier, color, strokeWidth, trackColor, strokeCap, gapSize)", imports = {}))
    /* JADX INFO: renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    public static final /* synthetic */ void m2083CircularProgressIndicatorDUhRLBM(final Function0 function0, Modifier modifier, long j, float f, long j2, int i, Composer composer, final int i2, final int i3) {
        Function0 function1;
        int i4;
        Modifier modifier2;
        long circularColor;
        int i5;
        float fM2078getCircularStrokeWidthD9Ej5fM;
        int i6;
        long circularDeterminateTrackColor;
        int i7;
        int i8;
        int i9;
        Modifier.Companion companion;
        int iM2075getCircularDeterminateStrokeCapKaPHkGw;
        int i10;
        Composer composer2;
        final Modifier modifier3;
        final long j3;
        final float f2;
        final long j4;
        final int i11;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-761680467);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CircularProgressIndicator)P(2,1,0:c#ui.graphics.Color,4:c#ui.unit.Dp,5:c#ui.graphics.Color,3:c#ui.graphics.StrokeCap)529@21735L13,531@21869L29,534@21991L217:ProgressIndicator.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i2 & 6) == 0) {
                i4 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i2;
            } else {
                i4 = i2;
            }
        }
        int i12 = i3 & 2;
        if (i12 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if ((i3 & 4) == 0) {
                    circularColor = j;
                    int i13 = composerStartRestartGroup.changed(circularColor) ? 256 : 128;
                    i4 |= i13;
                } else {
                    circularColor = j;
                }
                i4 |= i13;
            } else {
                circularColor = j;
            }
            i5 = i3 & 8;
            if (i5 != 0) {
                if ((i2 & 3072) == 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = f;
                    if (composerStartRestartGroup.changed(fM2078getCircularStrokeWidthD9Ej5fM)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i4 |= i6;
                }
                if ((i2 & 24576) == 0) {
                    if ((i3 & 16) == 0) {
                        circularDeterminateTrackColor = j2;
                        int i14 = composerStartRestartGroup.changed(circularDeterminateTrackColor) ? 16384 : 8192;
                        i4 |= i14;
                    } else {
                        circularDeterminateTrackColor = j2;
                    }
                    i4 |= i14;
                } else {
                    circularDeterminateTrackColor = j2;
                }
                i7 = i3 & 32;
                if (i7 != 0) {
                    if ((196608 & i2) == 0) {
                        i8 = i;
                        if (composerStartRestartGroup.changed(i8)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i4 |= i9;
                    }
                    if ((74899 & i4) == 74898 || !composerStartRestartGroup.getSkipping()) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if ((i3 & 4) != 0) {
                                circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                                i4 &= -897;
                            }
                            if (i5 != 0) {
                                fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                            }
                            if ((i3 & 16) != 0) {
                                i4 &= -57345;
                                circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                            }
                            if (i7 != 0) {
                                iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                            } else {
                                iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            }
                            i10 = -761680467;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                            }
                            if ((i3 & 16) != 0) {
                                i4 &= -57345;
                            }
                            circularDeterminateTrackColor = circularDeterminateTrackColor;
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i10 = -761680467;
                            companion = modifier2;
                            circularColor = circularColor;
                            fM2078getCircularStrokeWidthD9Ej5fM = fM2078getCircularStrokeWidthD9Ej5fM;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                        }
                        composer2 = composerStartRestartGroup;
                        m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = companion;
                        j3 = circularColor;
                        f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j4 = circularDeterminateTrackColor;
                        i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        composer2 = composerStartRestartGroup;
                        modifier3 = modifier2;
                        j3 = circularColor;
                        f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j4 = circularDeterminateTrackColor;
                        i11 = i8;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i15) {
                                ProgressIndicatorKt.m2083CircularProgressIndicatorDUhRLBM(function0, modifier3, j3, f2, j4, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                            }
                        });
                    }
                }
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                i8 = i;
                if ((74899 & i4) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i10 = -761680467;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i10 = -761680467;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                    }
                    composer2 = composerStartRestartGroup;
                    m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = companion;
                    j3 = circularColor;
                    f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                    j4 = circularDeterminateTrackColor;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i10 = -761680467;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i10 = -761680467;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                    }
                    composer2 = composerStartRestartGroup;
                    m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = companion;
                    j3 = circularColor;
                    f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                    j4 = circularDeterminateTrackColor;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i15) {
                            ProgressIndicatorKt.m2083CircularProgressIndicatorDUhRLBM(function0, modifier3, j3, f2, j4, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 3072;
            fM2078getCircularStrokeWidthD9Ej5fM = f;
            if ((i2 & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    circularDeterminateTrackColor = j2;
                    if (composerStartRestartGroup.changed(circularDeterminateTrackColor)) {
                    }
                    i4 |= i14;
                } else {
                    circularDeterminateTrackColor = j2;
                }
                i4 |= i14;
            } else {
                circularDeterminateTrackColor = j2;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                if ((196608 & i2) == 0) {
                    i8 = i;
                    if (composerStartRestartGroup.changed(i8)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((74899 & i4) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i10 = -761680467;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i10 = -761680467;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                    }
                    composer2 = composerStartRestartGroup;
                    m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = companion;
                    j3 = circularColor;
                    f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                    j4 = circularDeterminateTrackColor;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i10 = -761680467;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i10 = -761680467;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                    }
                    composer2 = composerStartRestartGroup;
                    m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = companion;
                    j3 = circularColor;
                    f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                    j4 = circularDeterminateTrackColor;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i15) {
                            ProgressIndicatorKt.m2083CircularProgressIndicatorDUhRLBM(function0, modifier3, j3, f2, j4, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i8 = i;
            if ((74899 & i4) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i10 = -761680467;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i10 = -761680467;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                }
                composer2 = composerStartRestartGroup;
                m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = companion;
                j3 = circularColor;
                f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                j4 = circularDeterminateTrackColor;
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i10 = -761680467;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i10 = -761680467;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                }
                composer2 = composerStartRestartGroup;
                m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = companion;
                j3 = circularColor;
                f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                j4 = circularDeterminateTrackColor;
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i15) {
                        ProgressIndicatorKt.m2083CircularProgressIndicatorDUhRLBM(function0, modifier3, j3, f2, j4, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i3 & 4) == 0) {
                circularColor = j;
                if (composerStartRestartGroup.changed(circularColor)) {
                }
                i4 |= i13;
            } else {
                circularColor = j;
            }
            i4 |= i13;
        } else {
            circularColor = j;
        }
        i5 = i3 & 8;
        if (i5 != 0) {
            if ((i2 & 3072) == 0) {
                fM2078getCircularStrokeWidthD9Ej5fM = f;
                if (composerStartRestartGroup.changed(fM2078getCircularStrokeWidthD9Ej5fM)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i4 |= i6;
            }
            if ((i2 & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    circularDeterminateTrackColor = j2;
                    if (composerStartRestartGroup.changed(circularDeterminateTrackColor)) {
                    }
                    i4 |= i14;
                } else {
                    circularDeterminateTrackColor = j2;
                }
                i4 |= i14;
            } else {
                circularDeterminateTrackColor = j2;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                if ((196608 & i2) == 0) {
                    i8 = i;
                    if (composerStartRestartGroup.changed(i8)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((74899 & i4) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i10 = -761680467;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i10 = -761680467;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                    }
                    composer2 = composerStartRestartGroup;
                    m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = companion;
                    j3 = circularColor;
                    f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                    j4 = circularDeterminateTrackColor;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i10 = -761680467;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        } else {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        }
                        i10 = -761680467;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                    }
                    composer2 = composerStartRestartGroup;
                    m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = companion;
                    j3 = circularColor;
                    f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                    j4 = circularDeterminateTrackColor;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i15) {
                            ProgressIndicatorKt.m2083CircularProgressIndicatorDUhRLBM(function0, modifier3, j3, f2, j4, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i8 = i;
            if ((74899 & i4) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i10 = -761680467;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i10 = -761680467;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                }
                composer2 = composerStartRestartGroup;
                m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = companion;
                j3 = circularColor;
                f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                j4 = circularDeterminateTrackColor;
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i10 = -761680467;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i10 = -761680467;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                }
                composer2 = composerStartRestartGroup;
                m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = companion;
                j3 = circularColor;
                f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                j4 = circularDeterminateTrackColor;
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i15) {
                        ProgressIndicatorKt.m2083CircularProgressIndicatorDUhRLBM(function0, modifier3, j3, f2, j4, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 3072;
        fM2078getCircularStrokeWidthD9Ej5fM = f;
        if ((i2 & 24576) == 0) {
            if ((i3 & 16) == 0) {
                circularDeterminateTrackColor = j2;
                if (composerStartRestartGroup.changed(circularDeterminateTrackColor)) {
                }
                i4 |= i14;
            } else {
                circularDeterminateTrackColor = j2;
            }
            i4 |= i14;
        } else {
            circularDeterminateTrackColor = j2;
        }
        i7 = i3 & 32;
        if (i7 != 0) {
            if ((196608 & i2) == 0) {
                i8 = i;
                if (composerStartRestartGroup.changed(i8)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
            if ((74899 & i4) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i10 = -761680467;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i10 = -761680467;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                }
                composer2 = composerStartRestartGroup;
                m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = companion;
                j3 = circularColor;
                f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                j4 = circularDeterminateTrackColor;
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i10 = -761680467;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    } else {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    }
                    i10 = -761680467;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                }
                composer2 = composerStartRestartGroup;
                m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = companion;
                j3 = circularColor;
                f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                j4 = circularDeterminateTrackColor;
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i15) {
                        ProgressIndicatorKt.m2083CircularProgressIndicatorDUhRLBM(function0, modifier3, j3, f2, j4, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        i8 = i;
        if ((74899 & i4) == 74898) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    i4 &= -57345;
                    circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                }
                if (i7 != 0) {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                } else {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                }
                i10 = -761680467;
            } else {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    i4 &= -57345;
                    circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                }
                if (i7 != 0) {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                } else {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                }
                i10 = -761680467;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
            }
            composer2 = composerStartRestartGroup;
            m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = companion;
            j3 = circularColor;
            f2 = fM2078getCircularStrokeWidthD9Ej5fM;
            j4 = circularDeterminateTrackColor;
            i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    i4 &= -57345;
                    circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                }
                if (i7 != 0) {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                } else {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                }
                i10 = -761680467;
            } else {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    i4 &= -57345;
                    circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                }
                if (i7 != 0) {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                } else {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                }
                i10 = -761680467;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
            }
            composer2 = composerStartRestartGroup;
            m2084CircularProgressIndicatorIyT6zlY(function1, companion, circularColor, fM2078getCircularStrokeWidthD9Ej5fM, circularDeterminateTrackColor, iM2075getCircularDeterminateStrokeCapKaPHkGw, ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = companion;
            j3 = circularColor;
            f2 = fM2078getCircularStrokeWidthD9Ej5fM;
            j4 = circularDeterminateTrackColor;
            i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i15) {
                    ProgressIndicatorKt.m2083CircularProgressIndicatorDUhRLBM(function0, modifier3, j3, f2, j4, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0127  */
    /* JADX WARN: Code duplicated, block: B:104:0x0132  */
    /* JADX WARN: Code duplicated, block: B:105:0x013c  */
    /* JADX WARN: Code duplicated, block: B:107:0x013f  */
    /* JADX WARN: Code duplicated, block: B:109:0x0148  */
    /* JADX WARN: Code duplicated, block: B:112:0x015a  */
    /* JADX WARN: Code duplicated, block: B:115:0x0171  */
    /* JADX WARN: Code duplicated, block: B:116:0x0173  */
    /* JADX WARN: Code duplicated, block: B:119:0x017a  */
    /* JADX WARN: Code duplicated, block: B:121:0x0182  */
    /* JADX WARN: Code duplicated, block: B:124:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:126:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:129:0x0210  */
    /* JADX WARN: Code duplicated, block: B:130:0x0212  */
    /* JADX WARN: Code duplicated, block: B:133:0x021b  */
    /* JADX WARN: Code duplicated, block: B:134:0x021d  */
    /* JADX WARN: Code duplicated, block: B:137:0x0225  */
    /* JADX WARN: Code duplicated, block: B:138:0x0227  */
    /* JADX WARN: Code duplicated, block: B:141:0x0233  */
    /* JADX WARN: Code duplicated, block: B:143:0x0239  */
    /* JADX WARN: Code duplicated, block: B:149:0x024e  */
    /* JADX WARN: Code duplicated, block: B:151:0x0254  */
    /* JADX WARN: Code duplicated, block: B:157:0x0263  */
    /* JADX WARN: Code duplicated, block: B:161:0x0273  */
    /* JADX WARN: Code duplicated, block: B:164:0x0298  */
    /* JADX WARN: Code duplicated, block: B:168:0x02a7  */
    /* JADX WARN: Code duplicated, block: B:170:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0049  */
    /* JADX WARN: Code duplicated, block: B:31:0x0058  */
    /* JADX WARN: Code duplicated, block: B:33:0x005c  */
    /* JADX WARN: Code duplicated, block: B:36:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x0069  */
    /* JADX WARN: Code duplicated, block: B:41:0x0071  */
    /* JADX WARN: Code duplicated, block: B:42:0x0074  */
    /* JADX WARN: Code duplicated, block: B:47:0x007e  */
    /* JADX WARN: Code duplicated, block: B:49:0x0084  */
    /* JADX WARN: Code duplicated, block: B:52:0x008d  */
    /* JADX WARN: Code duplicated, block: B:54:0x0092  */
    /* JADX WARN: Code duplicated, block: B:57:0x009a  */
    /* JADX WARN: Code duplicated, block: B:58:0x009f  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:68:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:73:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:77:0x00da  */
    /* JADX WARN: Code duplicated, block: B:81:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:83:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:93:0x010c A[PHI: r3 r6 r7 r11 r13 r15
      0x010c: PHI (r3v30 int) = (r3v21 int), (r3v32 int) binds: [B:108:0x0146, B:92:0x010b] A[DONT_GENERATE, DONT_INLINE]
      0x010c: PHI (r6v6 androidx.compose.ui.Modifier) = (r6v3 androidx.compose.ui.Modifier), (r6v2 androidx.compose.ui.Modifier) binds: [B:108:0x0146, B:92:0x010b] A[DONT_GENERATE, DONT_INLINE]
      0x010c: PHI (r7v10 long) = (r7v7 long), (r7v11 long) binds: [B:108:0x0146, B:92:0x010b] A[DONT_GENERATE, DONT_INLINE]
      0x010c: PHI (r11v5 long) = (r11v2 long), (r11v1 long) binds: [B:108:0x0146, B:92:0x010b] A[DONT_GENERATE, DONT_INLINE]
      0x010c: PHI (r13v6 float) = (r13v3 float), (r13v2 float) binds: [B:108:0x0146, B:92:0x010b] A[DONT_GENERATE, DONT_INLINE]
      0x010c: PHI (r15v20 int) = (r15v2 int), (r15v1 int) binds: [B:108:0x0146, B:92:0x010b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:95:0x0111 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:96:0x0113  */
    /* JADX WARN: Code duplicated, block: B:99:0x011d  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: CircularProgressIndicator-IyT6zlY, reason: not valid java name */
    public static final void m2084CircularProgressIndicatorIyT6zlY(final Function0<Float> function0, Modifier modifier, long j, float f, long j2, int i, float f2, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long circularColor;
        int i5;
        float fM2078getCircularStrokeWidthD9Ej5fM;
        int i6;
        long j3;
        int i7;
        int iM2075getCircularDeterminateStrokeCapKaPHkGw;
        int i8;
        int i9;
        float f3;
        int i10;
        long circularDeterminateTrackColor;
        final float fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
        final int i11;
        boolean z;
        Object objRememberedValue;
        final Function0 function1;
        final Stroke stroke;
        boolean zChanged;
        Object objRememberedValue2;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean zChangedInstance;
        Object objRememberedValue3;
        final long j4;
        final long j5;
        final float f4;
        final float f5;
        final long j6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i12;
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1798883595);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CircularProgressIndicator)P(3,2,0:c#ui.graphics.Color,5:c#ui.unit.Dp,6:c#ui.graphics.Color,4:c#ui.graphics.StrokeCap,1:c#ui.unit.Dp)575@23884L13,577@24018L29,581@24237L31,*582@24304L7,585@24446L102,589@24600L709,583@24373L936:ProgressIndicator.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i14 = i3 & 2;
        if (i14 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                circularColor = j;
                if ((i3 & 4) == 0 || !composerStartRestartGroup.changed(circularColor)) {
                    i13 = 128;
                } else {
                    i13 = 256;
                }
                i4 |= i13;
            } else {
                circularColor = j;
            }
            i5 = i3 & 8;
            if (i5 != 0) {
                if ((i2 & 3072) == 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = f;
                    if (composerStartRestartGroup.changed(fM2078getCircularStrokeWidthD9Ej5fM)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i4 |= i6;
                }
                if ((i2 & 24576) == 0) {
                    j3 = j2;
                    if ((i3 & 16) == 0 || !composerStartRestartGroup.changed(j3)) {
                        i12 = 8192;
                    } else {
                        i12 = 16384;
                    }
                    i4 |= i12;
                } else {
                    j3 = j2;
                }
                i7 = i3 & 32;
                if (i7 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = i;
                } else {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = i;
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(iM2075getCircularDeterminateStrokeCapKaPHkGw)) {
                            i8 = 131072;
                        } else {
                            i8 = 65536;
                        }
                        i4 |= i8;
                    }
                }
                i9 = i3 & 64;
                if (i9 != 0) {
                    i4 |= 1572864;
                    f3 = f2;
                } else {
                    f3 = f2;
                    if ((i2 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i4 |= i10;
                    }
                }
                if ((i4 & 599187) == 599186 || !composerStartRestartGroup.getSkipping()) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                        }
                        circularDeterminateTrackColor = j3;
                    } else {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            circularDeterminateTrackColor = j3;
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        }
                        if (i9 != 0) {
                            fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM();
                        }
                        i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:580)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291619137, "CC(remember):ProgressIndicator.kt#9igjgp");
                        if ((i4 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Float invoke() {
                                    return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function1 = (Function0) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume = composerStartRestartGroup.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        stroke = new Stroke(((Density) objConsume).mo406toPx0680j_4(fM2078getCircularStrokeWidthD9Ej5fM), 0.0f, i11, 0, null, 26, null);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291612378, "CC(remember):ProgressIndicator.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(function1);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifierM765size3ABfNKs = SizeKt.m765size3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) objRememberedValue2), CircularIndicatorDiameter);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291606843, "CC(remember):ProgressIndicator.kt#9igjgp");
                        boolean zChanged2 = composerStartRestartGroup.changed(function1);
                        if ((458752 & i4) == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        boolean z5 = zChanged2 | z2;
                        if ((3670016 & i4) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        boolean z6 = z5 | z3;
                        if ((i4 & 7168) == 2048) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        zChangedInstance = z6 | z4 | ((((57344 & i4) ^ 24576) <= 16384 && composerStartRestartGroup.changed(circularDeterminateTrackColor)) || (i4 & 24576) == 16384) | composerStartRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(circularColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            j4 = circularDeterminateTrackColor;
                            j5 = circularColor;
                            f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                            objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    float fM6890constructorimpl;
                                    float fFloatValue = function1.invoke().floatValue() * 360.0f;
                                    if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                        fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                                    } else {
                                        fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                                    }
                                    float f6 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                                    ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f6), (360.0f - fFloatValue) - (Math.min(fFloatValue, f6) * 2.0f), j4, stroke);
                                    ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            j4 = circularDeterminateTrackColor;
                            j5 = circularColor;
                            f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CanvasKt.Canvas(modifierM765size3ABfNKs, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        f5 = f4;
                        j3 = j4;
                        j6 = j5;
                    }
                    fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:580)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291619137, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((i4 & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function1 = (Function0) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume2 = composerStartRestartGroup.consume(localDensity2);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    stroke = new Stroke(((Density) objConsume2).mo406toPx0680j_4(fM2078getCircularStrokeWidthD9Ej5fM), 0.0f, i11, 0, null, 26, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291612378, "CC(remember):ProgressIndicator.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(function1);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierM765size3ABfNKs2 = SizeKt.m765size3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) objRememberedValue2), CircularIndicatorDiameter);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291606843, "CC(remember):ProgressIndicator.kt#9igjgp");
                    boolean zChanged3 = composerStartRestartGroup.changed(function1);
                    if ((458752 & i4) == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z7 = zChanged3 | z2;
                    if ((3670016 & i4) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z8 = z7 | z3;
                    if ((i4 & 7168) == 2048) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    zChangedInstance = z8 | z4 | ((((57344 & i4) ^ 24576) <= 16384 && composerStartRestartGroup.changed(circularDeterminateTrackColor)) || (i4 & 24576) == 16384) | composerStartRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(circularColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance) {
                        j4 = circularDeterminateTrackColor;
                        j5 = circularColor;
                        f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                        objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                float fM6890constructorimpl;
                                float fFloatValue = function1.invoke().floatValue() * 360.0f;
                                if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                    fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                                } else {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                                }
                                float f6 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                                ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f6), (360.0f - fFloatValue) - (Math.min(fFloatValue, f6) * 2.0f), j4, stroke);
                                ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        j4 = circularDeterminateTrackColor;
                        j5 = circularColor;
                        f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                        objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                float fM6890constructorimpl;
                                float fFloatValue = function1.invoke().floatValue() * 360.0f;
                                if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                    fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                                } else {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                                }
                                float f6 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                                ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f6), (360.0f - fFloatValue) - (Math.min(fFloatValue, f6) * 2.0f), j4, stroke);
                                ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CanvasKt.Canvas(modifierM765size3ABfNKs2, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f5 = f4;
                    j3 = j4;
                    j6 = j5;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    j6 = circularColor;
                    f5 = fM2078getCircularStrokeWidthD9Ej5fM;
                    fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier3 = modifier2;
                    final long j7 = j3;
                    final int i15 = i11;
                    final float f6 = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i16) {
                            ProgressIndicatorKt.m2084CircularProgressIndicatorIyT6zlY(function0, modifier3, j6, f5, j7, i15, f6, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 3072;
            fM2078getCircularStrokeWidthD9Ej5fM = f;
            if ((i2 & 24576) == 0) {
                j3 = j2;
                if ((i3 & 16) == 0) {
                    i12 = 8192;
                } else {
                    i12 = 8192;
                }
                i4 |= i12;
            } else {
                j3 = j2;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                iM2075getCircularDeterminateStrokeCapKaPHkGw = i;
            } else {
                iM2075getCircularDeterminateStrokeCapKaPHkGw = i;
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(iM2075getCircularDeterminateStrokeCapKaPHkGw)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i4 |= i8;
                }
            }
            i9 = i3 & 64;
            if (i9 != 0) {
                i4 |= 1572864;
                f3 = f2;
            } else {
                f3 = f2;
                if ((i2 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i4 |= i10;
                }
            }
            if ((i4 & 599187) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) == 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        circularDeterminateTrackColor = j3;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    }
                    if (i9 != 0) {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        circularDeterminateTrackColor = j3;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    }
                    if (i9 != 0) {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                }
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:580)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291619137, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                function1 = (Function0) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume3 = composerStartRestartGroup.consume(localDensity3);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                stroke = new Stroke(((Density) objConsume3).mo406toPx0680j_4(fM2078getCircularStrokeWidthD9Ej5fM), 0.0f, i11, 0, null, 26, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291612378, "CC(remember):ProgressIndicator.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(function1);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierM765size3ABfNKs3 = SizeKt.m765size3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) objRememberedValue2), CircularIndicatorDiameter);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291606843, "CC(remember):ProgressIndicator.kt#9igjgp");
                boolean zChanged4 = composerStartRestartGroup.changed(function1);
                if ((458752 & i4) == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z9 = zChanged4 | z2;
                if ((3670016 & i4) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z10 = z9 | z3;
                if ((i4 & 7168) == 2048) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                zChangedInstance = z10 | z4 | ((((57344 & i4) ^ 24576) <= 16384 && composerStartRestartGroup.changed(circularDeterminateTrackColor)) || (i4 & 24576) == 16384) | composerStartRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(circularColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    j4 = circularDeterminateTrackColor;
                    j5 = circularColor;
                    f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            float fFloatValue = function1.invoke().floatValue() * 360.0f;
                            if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                            }
                            float f7 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                            ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f7), (360.0f - fFloatValue) - (Math.min(fFloatValue, f7) * 2.0f), j4, stroke);
                            ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    j4 = circularDeterminateTrackColor;
                    j5 = circularColor;
                    f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            float fFloatValue = function1.invoke().floatValue() * 360.0f;
                            if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                            }
                            float f7 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                            ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f7), (360.0f - fFloatValue) - (Math.min(fFloatValue, f7) * 2.0f), j4, stroke);
                            ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM765size3ABfNKs3, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f5 = f4;
                j3 = j4;
                j6 = j5;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) == 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        circularDeterminateTrackColor = j3;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    }
                    if (i9 != 0) {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        circularDeterminateTrackColor = j3;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    }
                    if (i9 != 0) {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                }
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:580)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291619137, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                function1 = (Function0) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume4 = composerStartRestartGroup.consume(localDensity4);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                stroke = new Stroke(((Density) objConsume4).mo406toPx0680j_4(fM2078getCircularStrokeWidthD9Ej5fM), 0.0f, i11, 0, null, 26, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291612378, "CC(remember):ProgressIndicator.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(function1);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierM765size3ABfNKs4 = SizeKt.m765size3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) objRememberedValue2), CircularIndicatorDiameter);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291606843, "CC(remember):ProgressIndicator.kt#9igjgp");
                boolean zChanged5 = composerStartRestartGroup.changed(function1);
                if ((458752 & i4) == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z11 = zChanged5 | z2;
                if ((3670016 & i4) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z12 = z11 | z3;
                if ((i4 & 7168) == 2048) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                zChangedInstance = z12 | z4 | ((((57344 & i4) ^ 24576) <= 16384 && composerStartRestartGroup.changed(circularDeterminateTrackColor)) || (i4 & 24576) == 16384) | composerStartRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(circularColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    j4 = circularDeterminateTrackColor;
                    j5 = circularColor;
                    f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            float fFloatValue = function1.invoke().floatValue() * 360.0f;
                            if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                            }
                            float f7 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                            ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f7), (360.0f - fFloatValue) - (Math.min(fFloatValue, f7) * 2.0f), j4, stroke);
                            ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    j4 = circularDeterminateTrackColor;
                    j5 = circularColor;
                    f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            float fFloatValue = function1.invoke().floatValue() * 360.0f;
                            if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                            }
                            float f7 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                            ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f7), (360.0f - fFloatValue) - (Math.min(fFloatValue, f7) * 2.0f), j4, stroke);
                            ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM765size3ABfNKs4, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f5 = f4;
                j3 = j4;
                j6 = j5;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier4 = modifier2;
                final long j8 = j3;
                final int i16 = i11;
                final float f7 = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i17) {
                        ProgressIndicatorKt.m2084CircularProgressIndicatorIyT6zlY(function0, modifier4, j6, f5, j8, i16, f7, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            circularColor = j;
            if ((i3 & 4) == 0) {
                i13 = 128;
            } else {
                i13 = 128;
            }
            i4 |= i13;
        } else {
            circularColor = j;
        }
        i5 = i3 & 8;
        if (i5 != 0) {
            if ((i2 & 3072) == 0) {
                fM2078getCircularStrokeWidthD9Ej5fM = f;
                if (composerStartRestartGroup.changed(fM2078getCircularStrokeWidthD9Ej5fM)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i4 |= i6;
            }
            if ((i2 & 24576) == 0) {
                j3 = j2;
                if ((i3 & 16) == 0) {
                    i12 = 8192;
                } else {
                    i12 = 8192;
                }
                i4 |= i12;
            } else {
                j3 = j2;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                iM2075getCircularDeterminateStrokeCapKaPHkGw = i;
            } else {
                iM2075getCircularDeterminateStrokeCapKaPHkGw = i;
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(iM2075getCircularDeterminateStrokeCapKaPHkGw)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i4 |= i8;
                }
            }
            i9 = i3 & 64;
            if (i9 != 0) {
                i4 |= 1572864;
                f3 = f2;
            } else {
                f3 = f2;
                if ((i2 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i4 |= i10;
                }
            }
            if ((i4 & 599187) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) == 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        circularDeterminateTrackColor = j3;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    }
                    if (i9 != 0) {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        circularDeterminateTrackColor = j3;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    }
                    if (i9 != 0) {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                }
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:580)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291619137, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                function1 = (Function0) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume5 = composerStartRestartGroup.consume(localDensity5);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                stroke = new Stroke(((Density) objConsume5).mo406toPx0680j_4(fM2078getCircularStrokeWidthD9Ej5fM), 0.0f, i11, 0, null, 26, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291612378, "CC(remember):ProgressIndicator.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(function1);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierM765size3ABfNKs5 = SizeKt.m765size3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) objRememberedValue2), CircularIndicatorDiameter);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291606843, "CC(remember):ProgressIndicator.kt#9igjgp");
                boolean zChanged6 = composerStartRestartGroup.changed(function1);
                if ((458752 & i4) == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z13 = zChanged6 | z2;
                if ((3670016 & i4) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z14 = z13 | z3;
                if ((i4 & 7168) == 2048) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                zChangedInstance = z14 | z4 | ((((57344 & i4) ^ 24576) <= 16384 && composerStartRestartGroup.changed(circularDeterminateTrackColor)) || (i4 & 24576) == 16384) | composerStartRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(circularColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    j4 = circularDeterminateTrackColor;
                    j5 = circularColor;
                    f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            float fFloatValue = function1.invoke().floatValue() * 360.0f;
                            if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                            }
                            float f8 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                            ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f8), (360.0f - fFloatValue) - (Math.min(fFloatValue, f8) * 2.0f), j4, stroke);
                            ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    j4 = circularDeterminateTrackColor;
                    j5 = circularColor;
                    f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            float fFloatValue = function1.invoke().floatValue() * 360.0f;
                            if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                            }
                            float f8 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                            ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f8), (360.0f - fFloatValue) - (Math.min(fFloatValue, f8) * 2.0f), j4, stroke);
                            ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM765size3ABfNKs5, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f5 = f4;
                j3 = j4;
                j6 = j5;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) == 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        circularDeterminateTrackColor = j3;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    }
                    if (i9 != 0) {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        circularDeterminateTrackColor = j3;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    }
                    if (i9 != 0) {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM();
                    } else {
                        fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                    }
                }
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:580)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291619137, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                function1 = (Function0) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume6 = composerStartRestartGroup.consume(localDensity6);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                stroke = new Stroke(((Density) objConsume6).mo406toPx0680j_4(fM2078getCircularStrokeWidthD9Ej5fM), 0.0f, i11, 0, null, 26, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291612378, "CC(remember):ProgressIndicator.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(function1);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierM765size3ABfNKs6 = SizeKt.m765size3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) objRememberedValue2), CircularIndicatorDiameter);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291606843, "CC(remember):ProgressIndicator.kt#9igjgp");
                boolean zChanged7 = composerStartRestartGroup.changed(function1);
                if ((458752 & i4) == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z15 = zChanged7 | z2;
                if ((3670016 & i4) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z16 = z15 | z3;
                if ((i4 & 7168) == 2048) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                zChangedInstance = z16 | z4 | ((((57344 & i4) ^ 24576) <= 16384 && composerStartRestartGroup.changed(circularDeterminateTrackColor)) || (i4 & 24576) == 16384) | composerStartRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(circularColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    j4 = circularDeterminateTrackColor;
                    j5 = circularColor;
                    f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            float fFloatValue = function1.invoke().floatValue() * 360.0f;
                            if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                            }
                            float f8 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                            ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f8), (360.0f - fFloatValue) - (Math.min(fFloatValue, f8) * 2.0f), j4, stroke);
                            ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    j4 = circularDeterminateTrackColor;
                    j5 = circularColor;
                    f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                    objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            float fM6890constructorimpl;
                            float fFloatValue = function1.invoke().floatValue() * 360.0f;
                            if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                                fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                            }
                            float f8 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                            ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f8), (360.0f - fFloatValue) - (Math.min(fFloatValue, f8) * 2.0f), j4, stroke);
                            ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM765size3ABfNKs6, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f5 = f4;
                j3 = j4;
                j6 = j5;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier5 = modifier2;
                final long j9 = j3;
                final int i17 = i11;
                final float f8 = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i18) {
                        ProgressIndicatorKt.m2084CircularProgressIndicatorIyT6zlY(function0, modifier5, j6, f5, j9, i17, f8, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 3072;
        fM2078getCircularStrokeWidthD9Ej5fM = f;
        if ((i2 & 24576) == 0) {
            j3 = j2;
            if ((i3 & 16) == 0) {
                i12 = 8192;
            } else {
                i12 = 8192;
            }
            i4 |= i12;
        } else {
            j3 = j2;
        }
        i7 = i3 & 32;
        if (i7 != 0) {
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            iM2075getCircularDeterminateStrokeCapKaPHkGw = i;
        } else {
            iM2075getCircularDeterminateStrokeCapKaPHkGw = i;
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(iM2075getCircularDeterminateStrokeCapKaPHkGw)) {
                    i8 = 131072;
                } else {
                    i8 = 65536;
                }
                i4 |= i8;
            }
        }
        i9 = i3 & 64;
        if (i9 != 0) {
            i4 |= 1572864;
            f3 = f2;
        } else {
            f3 = f2;
            if ((i2 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(f3)) {
                    i10 = 1048576;
                } else {
                    i10 = 524288;
                }
                i4 |= i10;
            }
        }
        if ((i4 & 599187) == 599186) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) == 0) {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    i4 &= -57345;
                } else {
                    circularDeterminateTrackColor = j3;
                }
                if (i7 != 0) {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                }
                if (i9 != 0) {
                    fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                }
            } else {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    i4 &= -57345;
                } else {
                    circularDeterminateTrackColor = j3;
                }
                if (i7 != 0) {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                }
                if (i9 != 0) {
                    fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                }
            }
            i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:580)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291619137, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 14) == 4) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            function1 = (Function0) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume7 = composerStartRestartGroup.consume(localDensity7);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            stroke = new Stroke(((Density) objConsume7).mo406toPx0680j_4(fM2078getCircularStrokeWidthD9Ej5fM), 0.0f, i11, 0, null, 26, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291612378, "CC(remember):ProgressIndicator.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(function1);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM765size3ABfNKs7 = SizeKt.m765size3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) objRememberedValue2), CircularIndicatorDiameter);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291606843, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean zChanged8 = composerStartRestartGroup.changed(function1);
            if ((458752 & i4) == 131072) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z17 = zChanged8 | z2;
            if ((3670016 & i4) == 1048576) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z18 = z17 | z3;
            if ((i4 & 7168) == 2048) {
                z4 = true;
            } else {
                z4 = false;
            }
            zChangedInstance = z18 | z4 | ((((57344 & i4) ^ 24576) <= 16384 && composerStartRestartGroup.changed(circularDeterminateTrackColor)) || (i4 & 24576) == 16384) | composerStartRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(circularColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                j4 = circularDeterminateTrackColor;
                j5 = circularColor;
                f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        float fM6890constructorimpl;
                        float fFloatValue = function1.invoke().floatValue() * 360.0f;
                        if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                            fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                        }
                        float f9 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                        ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f9), (360.0f - fFloatValue) - (Math.min(fFloatValue, f9) * 2.0f), j4, stroke);
                        ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                j4 = circularDeterminateTrackColor;
                j5 = circularColor;
                f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        float fM6890constructorimpl;
                        float fFloatValue = function1.invoke().floatValue() * 360.0f;
                        if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                            fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                        }
                        float f9 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                        ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f9), (360.0f - fFloatValue) - (Math.min(fFloatValue, f9) * 2.0f), j4, stroke);
                        ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CanvasKt.Canvas(modifierM765size3ABfNKs7, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            f5 = f4;
            j3 = j4;
            j6 = j5;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) == 0) {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    i4 &= -57345;
                } else {
                    circularDeterminateTrackColor = j3;
                }
                if (i7 != 0) {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                }
                if (i9 != 0) {
                    fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                }
            } else {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    circularDeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(composerStartRestartGroup, 6);
                    i4 &= -57345;
                } else {
                    circularDeterminateTrackColor = j3;
                }
                if (i7 != 0) {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                }
                if (i9 != 0) {
                    fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2077getCircularIndicatorTrackGapSizeD9Ej5fM();
                } else {
                    fM2077getCircularIndicatorTrackGapSizeD9Ej5fM = f3;
                }
            }
            i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:580)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291619137, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 14) == 4) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            function1 = (Function0) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<Density> localDensity8 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume8 = composerStartRestartGroup.consume(localDensity8);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            stroke = new Stroke(((Density) objConsume8).mo406toPx0680j_4(fM2078getCircularStrokeWidthD9Ej5fM), 0.0f, i11, 0, null, 26, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291612378, "CC(remember):ProgressIndicator.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(function1);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function1.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM765size3ABfNKs8 = SizeKt.m765size3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) objRememberedValue2), CircularIndicatorDiameter);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291606843, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean zChanged9 = composerStartRestartGroup.changed(function1);
            if ((458752 & i4) == 131072) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z19 = zChanged9 | z2;
            if ((3670016 & i4) == 1048576) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z110 = z19 | z3;
            if ((i4 & 7168) == 2048) {
                z4 = true;
            } else {
                z4 = false;
            }
            zChangedInstance = z110 | z4 | ((((57344 & i4) ^ 24576) <= 16384 && composerStartRestartGroup.changed(circularDeterminateTrackColor)) || (i4 & 24576) == 16384) | composerStartRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(circularColor)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                j4 = circularDeterminateTrackColor;
                j5 = circularColor;
                f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        float fM6890constructorimpl;
                        float fFloatValue = function1.invoke().floatValue() * 360.0f;
                        if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                            fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                        }
                        float f9 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                        ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f9), (360.0f - fFloatValue) - (Math.min(fFloatValue, f9) * 2.0f), j4, stroke);
                        ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                j4 = circularDeterminateTrackColor;
                j5 = circularColor;
                f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                objRememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        float fM6890constructorimpl;
                        float fFloatValue = function1.invoke().floatValue() * 360.0f;
                        if (StrokeCap.m4527equalsimpl0(i11, StrokeCap.INSTANCE.m4531getButtKaPHkGw()) || Size.m3936getHeightimpl(drawScope.mo4647getSizeNHjbRc()) > Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc())) {
                            fM6890constructorimpl = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(fM2077getCircularIndicatorTrackGapSizeD9Ej5fM + f4);
                        }
                        float f9 = (fM6890constructorimpl / ((float) (((double) drawScope.mo402toDpu2uoSUM(Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()))) * 3.141592653589793d))) * 360.0f;
                        ProgressIndicatorKt.m2100drawCircularIndicator42QJj7c(drawScope, 270.0f + fFloatValue + Math.min(fFloatValue, f9), (360.0f - fFloatValue) - (Math.min(fFloatValue, f9) * 2.0f), j4, stroke);
                        ProgressIndicatorKt.m2102drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, fFloatValue, j5, stroke);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CanvasKt.Canvas(modifierM765size3ABfNKs8, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            f5 = f4;
            j3 = j4;
            j6 = j5;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier2;
            final long j10 = j3;
            final int i18 = i11;
            final float f9 = fM2077getCircularIndicatorTrackGapSizeD9Ej5fM;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i19) {
                    ProgressIndicatorKt.m2084CircularProgressIndicatorIyT6zlY(function0, modifier6, j6, f5, j10, i18, f9, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x02b6  */
    /* JADX WARN: Code duplicated, block: B:102:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:105:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:107:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:113:0x02d8  */
    /* JADX WARN: Code duplicated, block: B:117:0x02e6  */
    /* JADX WARN: Code duplicated, block: B:120:0x030f  */
    /* JADX WARN: Code duplicated, block: B:124:0x0322  */
    /* JADX WARN: Code duplicated, block: B:126:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0067  */
    /* JADX WARN: Code duplicated, block: B:41:0x0076  */
    /* JADX WARN: Code duplicated, block: B:43:0x007a  */
    /* JADX WARN: Code duplicated, block: B:46:0x0080  */
    /* JADX WARN: Code duplicated, block: B:47:0x0083  */
    /* JADX WARN: Code duplicated, block: B:49:0x0087  */
    /* JADX WARN: Code duplicated, block: B:51:0x008f  */
    /* JADX WARN: Code duplicated, block: B:52:0x0092  */
    /* JADX WARN: Code duplicated, block: B:57:0x009f  */
    /* JADX WARN: Code duplicated, block: B:61:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:73:0x00ce A[PHI: r1 r4 r9 r11 r13
      0x00ce: PHI (r1v23 androidx.compose.ui.Modifier) = (r1v4 androidx.compose.ui.Modifier), (r1v26 androidx.compose.ui.Modifier) binds: [B:86:0x0100, B:72:0x00cd] A[DONT_GENERATE, DONT_INLINE]
      0x00ce: PHI (r4v29 int) = (r4v14 int), (r4v31 int) binds: [B:86:0x0100, B:72:0x00cd] A[DONT_GENERATE, DONT_INLINE]
      0x00ce: PHI (r9v11 long) = (r9v2 long), (r9v1 long) binds: [B:86:0x0100, B:72:0x00cd] A[DONT_GENERATE, DONT_INLINE]
      0x00ce: PHI (r11v24 float) = (r11v3 float), (r11v2 float) binds: [B:86:0x0100, B:72:0x00cd] A[DONT_GENERATE, DONT_INLINE]
      0x00ce: PHI (r13v35 long) = (r13v2 long), (r13v1 long) binds: [B:86:0x0100, B:72:0x00cd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:75:0x00d5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:76:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:77:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:82:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:87:0x0102  */
    /* JADX WARN: Code duplicated, block: B:90:0x0115  */
    /* JADX WARN: Code duplicated, block: B:93:0x0289  */
    /* JADX WARN: Code duplicated, block: B:95:0x028f  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: CircularProgressIndicator-LxG7B9w, reason: not valid java name */
    public static final void m2085CircularProgressIndicatorLxG7B9w(Modifier modifier, long j, float f, long j2, int i, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        long circularColor;
        float fM2078getCircularStrokeWidthD9Ej5fM;
        long circularIndeterminateTrackColor;
        int i5;
        int i6;
        int i7;
        Modifier.Companion companion;
        int iM2076getCircularIndeterminateStrokeCapKaPHkGw;
        int i8;
        long j3;
        final float f2;
        long j4;
        final Stroke stroke;
        final State stateAnimateValue;
        final State<Float> stateAnimateFloat;
        final State<Float> stateAnimateFloat2;
        final State<Float> stateAnimateFloat3;
        boolean z;
        boolean z2;
        Object objRememberedValue;
        final long j5;
        final long j6;
        final long j7;
        final float f3;
        final long j8;
        final int i9;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i10;
        Composer composerStartRestartGroup = composer.startRestartGroup(-115871647);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CircularProgressIndicator)P(1,0:c#ui.graphics.Color,3:c#ui.unit.Dp,4:c#ui.graphics.Color,2:c#ui.graphics.StrokeCap)633@26429L13,635@26563L31,*638@26720L7,640@26807L28,643@26972L350,657@27444L208,666@27777L422,679@28240L431,691@28745L628,691@28676L697:ProgressIndicator.kt#uh7d8r");
        int i11 = i3 & 1;
        if (i11 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            circularColor = j;
            i4 |= ((i3 & 2) == 0 && composerStartRestartGroup.changed(circularColor)) ? 32 : 16;
        } else {
            circularColor = j;
        }
        int i12 = i3 & 4;
        if (i12 == 0) {
            if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                fM2078getCircularStrokeWidthD9Ej5fM = f;
                i4 |= composerStartRestartGroup.changed(fM2078getCircularStrokeWidthD9Ej5fM) ? 256 : 128;
            }
            if ((i2 & 3072) == 0) {
                circularIndeterminateTrackColor = j2;
                if ((i3 & 8) == 0 || !composerStartRestartGroup.changed(circularIndeterminateTrackColor)) {
                    i10 = 1024;
                } else {
                    i10 = 2048;
                }
                i4 |= i10;
            } else {
                circularIndeterminateTrackColor = j2;
            }
            i5 = i3 & 16;
            if (i5 != 0) {
                if ((i2 & 24576) == 0) {
                    i6 = i;
                    if (composerStartRestartGroup.changed(i6)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i4 |= i7;
                }
                if ((i4 & 9363) == 9362 || !composerStartRestartGroup.getSkipping()) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i3 & 2) != 0) {
                            i4 &= -113;
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                        }
                        companion = modifier2;
                    } else {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i3 & 2) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -113;
                        }
                        if (i12 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                            circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                        }
                        if (i5 != 0) {
                            iM2076getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw();
                            i8 = i4;
                        }
                        j3 = circularColor;
                        f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j4 = circularIndeterminateTrackColor;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-115871647, i8, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:637)");
                        }
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume = composerStartRestartGroup.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        stroke = new Stroke(((Density) objConsume).mo406toPx0680j_4(f2), 0.0f, iM2076getCircularIndeterminateStrokeCapKaPHkGw, 0, null, 26, null);
                        int i13 = iM2076getCircularIndeterminateStrokeCapKaPHkGw;
                        InfiniteTransition infiniteTransitionRememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
                        Modifier modifier4 = companion;
                        boolean z3 = true;
                        stateAnimateValue = InfiniteTransitionKt.animateValue(infiniteTransitionRememberInfiniteTransition, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 12), 16);
                        stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, BaseRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                        stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$endAngle$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                invoke2(keyframesSpecConfig);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                keyframesSpecConfig.setDurationMillis(1332);
                                keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.CircularEasing);
                                keyframesSpecConfig.at(Float.valueOf(290.0f), 666);
                            }
                        }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                        stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$startAngle$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                invoke2(keyframesSpecConfig);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                keyframesSpecConfig.setDurationMillis(1332);
                                keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 666), ProgressIndicatorKt.CircularEasing);
                                keyframesSpecConfig.at(Float.valueOf(290.0f), keyframesSpecConfig.getDurationMillis());
                            }
                        }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                        Modifier modifierM765size3ABfNKs = SizeKt.m765size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier4), CircularIndicatorDiameter);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291474284, "CC(remember):ProgressIndicator.kt#9igjgp");
                        boolean zChangedInstance = ((((i8 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(j4)) || (i8 & 3072) == 2048) | composerStartRestartGroup.changedInstance(stroke) | composerStartRestartGroup.changed(stateAnimateValue) | composerStartRestartGroup.changed(stateAnimateFloat2) | composerStartRestartGroup.changed(stateAnimateFloat3) | composerStartRestartGroup.changed(stateAnimateFloat);
                        if ((i8 & 896) == 256) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean z4 = z | zChangedInstance;
                        if ((((i8 & 112) ^ 48) > 32 || !composerStartRestartGroup.changed(j3)) && (i8 & 48) != 32) {
                        }
                        z2 = z4 | z3;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            j5 = j3;
                            j6 = j4;
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                                    ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            j5 = j3;
                            j6 = j4;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CanvasKt.Canvas(modifierM765size3ABfNKs, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j7 = j6;
                        f3 = f2;
                        j8 = j5;
                        i9 = i13;
                        modifier3 = modifier4;
                    }
                    i8 = i4;
                    iM2076getCircularIndeterminateStrokeCapKaPHkGw = i6;
                    j3 = circularColor;
                    f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                    j4 = circularIndeterminateTrackColor;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-115871647, i8, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:637)");
                    }
                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume2 = composerStartRestartGroup.consume(localDensity2);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    stroke = new Stroke(((Density) objConsume2).mo406toPx0680j_4(f2), 0.0f, iM2076getCircularIndeterminateStrokeCapKaPHkGw, 0, null, 26, null);
                    int i14 = iM2076getCircularIndeterminateStrokeCapKaPHkGw;
                    InfiniteTransition infiniteTransitionRememberInfiniteTransition2 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
                    Modifier modifier5 = companion;
                    boolean z5 = true;
                    stateAnimateValue = InfiniteTransitionKt.animateValue(infiniteTransitionRememberInfiniteTransition2, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 12), 16);
                    stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.0f, BaseRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$endAngle$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            invoke2(keyframesSpecConfig);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            keyframesSpecConfig.setDurationMillis(1332);
                            keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.CircularEasing);
                            keyframesSpecConfig.at(Float.valueOf(290.0f), 666);
                        }
                    }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition2, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$startAngle$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            invoke2(keyframesSpecConfig);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            keyframesSpecConfig.setDurationMillis(1332);
                            keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 666), ProgressIndicatorKt.CircularEasing);
                            keyframesSpecConfig.at(Float.valueOf(290.0f), keyframesSpecConfig.getDurationMillis());
                        }
                    }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    Modifier modifierM765size3ABfNKs2 = SizeKt.m765size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier5), CircularIndicatorDiameter);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291474284, "CC(remember):ProgressIndicator.kt#9igjgp");
                    boolean zChangedInstance2 = ((((i8 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(j4)) || (i8 & 3072) == 2048) | composerStartRestartGroup.changedInstance(stroke) | composerStartRestartGroup.changed(stateAnimateValue) | composerStartRestartGroup.changed(stateAnimateFloat2) | composerStartRestartGroup.changed(stateAnimateFloat3) | composerStartRestartGroup.changed(stateAnimateFloat);
                    if ((i8 & 896) == 256) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z6 = z | zChangedInstance2;
                    z5 = ((i8 & 112) ^ 48) > 32 ? false : false;
                    z2 = z6 | z5;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        j5 = j3;
                        j6 = j4;
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                                ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        j5 = j3;
                        j6 = j4;
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                                ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CanvasKt.Canvas(modifierM765size3ABfNKs2, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j7 = j6;
                    f3 = f2;
                    j8 = j5;
                    i9 = i14;
                    modifier3 = modifier5;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    i9 = i6;
                    j8 = circularColor;
                    f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                    j7 = circularIndeterminateTrackColor;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i15) {
                            ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(modifier3, j8, f3, j7, i9, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 24576;
            i6 = i;
            if ((i4 & 9363) == 9362) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) == 0) {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i12 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        i4 &= -7169;
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i5 != 0) {
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw();
                        i8 = i4;
                    } else {
                        i8 = i4;
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = i6;
                    }
                } else {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i12 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        i4 &= -7169;
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i5 != 0) {
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw();
                        i8 = i4;
                    } else {
                        i8 = i4;
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = i6;
                    }
                }
                j3 = circularColor;
                f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                j4 = circularIndeterminateTrackColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-115871647, i8, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:637)");
                }
                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume3 = composerStartRestartGroup.consume(localDensity3);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                stroke = new Stroke(((Density) objConsume3).mo406toPx0680j_4(f2), 0.0f, iM2076getCircularIndeterminateStrokeCapKaPHkGw, 0, null, 26, null);
                int i15 = iM2076getCircularIndeterminateStrokeCapKaPHkGw;
                InfiniteTransition infiniteTransitionRememberInfiniteTransition3 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
                Modifier modifier6 = companion;
                boolean z7 = true;
                stateAnimateValue = InfiniteTransitionKt.animateValue(infiniteTransitionRememberInfiniteTransition3, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 12), 16);
                stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.0f, BaseRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$endAngle$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1332);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.CircularEasing);
                        keyframesSpecConfig.at(Float.valueOf(290.0f), 666);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition3, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$startAngle$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1332);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 666), ProgressIndicatorKt.CircularEasing);
                        keyframesSpecConfig.at(Float.valueOf(290.0f), keyframesSpecConfig.getDurationMillis());
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                Modifier modifierM765size3ABfNKs3 = SizeKt.m765size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier6), CircularIndicatorDiameter);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291474284, "CC(remember):ProgressIndicator.kt#9igjgp");
                boolean zChangedInstance3 = ((((i8 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(j4)) || (i8 & 3072) == 2048) | composerStartRestartGroup.changedInstance(stroke) | composerStartRestartGroup.changed(stateAnimateValue) | composerStartRestartGroup.changed(stateAnimateFloat2) | composerStartRestartGroup.changed(stateAnimateFloat3) | composerStartRestartGroup.changed(stateAnimateFloat);
                if ((i8 & 896) == 256) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z8 = z | zChangedInstance3;
                if (((i8 & 112) ^ 48) > 32) {
                }
                z2 = z8 | z7;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    j5 = j3;
                    j6 = j4;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                            ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    j5 = j3;
                    j6 = j4;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                            ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM765size3ABfNKs3, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j7 = j6;
                f3 = f2;
                j8 = j5;
                i9 = i15;
                modifier3 = modifier6;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) == 0) {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i12 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        i4 &= -7169;
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i5 != 0) {
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw();
                        i8 = i4;
                    } else {
                        i8 = i4;
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = i6;
                    }
                } else {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i12 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        i4 &= -7169;
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i5 != 0) {
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw();
                        i8 = i4;
                    } else {
                        i8 = i4;
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = i6;
                    }
                }
                j3 = circularColor;
                f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                j4 = circularIndeterminateTrackColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-115871647, i8, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:637)");
                }
                ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume4 = composerStartRestartGroup.consume(localDensity4);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                stroke = new Stroke(((Density) objConsume4).mo406toPx0680j_4(f2), 0.0f, iM2076getCircularIndeterminateStrokeCapKaPHkGw, 0, null, 26, null);
                int i16 = iM2076getCircularIndeterminateStrokeCapKaPHkGw;
                InfiniteTransition infiniteTransitionRememberInfiniteTransition4 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
                Modifier modifier7 = companion;
                boolean z9 = true;
                stateAnimateValue = InfiniteTransitionKt.animateValue(infiniteTransitionRememberInfiniteTransition4, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 12), 16);
                stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.0f, BaseRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$endAngle$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1332);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.CircularEasing);
                        keyframesSpecConfig.at(Float.valueOf(290.0f), 666);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition4, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$startAngle$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1332);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 666), ProgressIndicatorKt.CircularEasing);
                        keyframesSpecConfig.at(Float.valueOf(290.0f), keyframesSpecConfig.getDurationMillis());
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                Modifier modifierM765size3ABfNKs4 = SizeKt.m765size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier7), CircularIndicatorDiameter);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291474284, "CC(remember):ProgressIndicator.kt#9igjgp");
                boolean zChangedInstance4 = ((((i8 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(j4)) || (i8 & 3072) == 2048) | composerStartRestartGroup.changedInstance(stroke) | composerStartRestartGroup.changed(stateAnimateValue) | composerStartRestartGroup.changed(stateAnimateFloat2) | composerStartRestartGroup.changed(stateAnimateFloat3) | composerStartRestartGroup.changed(stateAnimateFloat);
                if ((i8 & 896) == 256) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z10 = z | zChangedInstance4;
                if (((i8 & 112) ^ 48) > 32) {
                }
                z2 = z10 | z9;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    j5 = j3;
                    j6 = j4;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                            ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    j5 = j3;
                    j6 = j4;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                            ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM765size3ABfNKs4, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j7 = j6;
                f3 = f2;
                j8 = j5;
                i9 = i16;
                modifier3 = modifier7;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$6
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i17) {
                        ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(modifier3, j8, f3, j7, i9, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        fM2078getCircularStrokeWidthD9Ej5fM = f;
        if ((i2 & 3072) == 0) {
            circularIndeterminateTrackColor = j2;
            if ((i3 & 8) == 0) {
                i10 = 1024;
            } else {
                i10 = 1024;
            }
            i4 |= i10;
        } else {
            circularIndeterminateTrackColor = j2;
        }
        i5 = i3 & 16;
        if (i5 != 0) {
            if ((i2 & 24576) == 0) {
                i6 = i;
                if (composerStartRestartGroup.changed(i6)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i4 |= i7;
            }
            if ((i4 & 9363) == 9362) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) == 0) {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i12 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        i4 &= -7169;
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i5 != 0) {
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw();
                        i8 = i4;
                    } else {
                        i8 = i4;
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = i6;
                    }
                } else {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i12 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        i4 &= -7169;
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i5 != 0) {
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw();
                        i8 = i4;
                    } else {
                        i8 = i4;
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = i6;
                    }
                }
                j3 = circularColor;
                f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                j4 = circularIndeterminateTrackColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-115871647, i8, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:637)");
                }
                ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume5 = composerStartRestartGroup.consume(localDensity5);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                stroke = new Stroke(((Density) objConsume5).mo406toPx0680j_4(f2), 0.0f, iM2076getCircularIndeterminateStrokeCapKaPHkGw, 0, null, 26, null);
                int i17 = iM2076getCircularIndeterminateStrokeCapKaPHkGw;
                InfiniteTransition infiniteTransitionRememberInfiniteTransition5 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
                Modifier modifier8 = companion;
                boolean z11 = true;
                stateAnimateValue = InfiniteTransitionKt.animateValue(infiniteTransitionRememberInfiniteTransition5, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 12), 16);
                stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition5, 0.0f, BaseRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition5, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$endAngle$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1332);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.CircularEasing);
                        keyframesSpecConfig.at(Float.valueOf(290.0f), 666);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition5, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$startAngle$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1332);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 666), ProgressIndicatorKt.CircularEasing);
                        keyframesSpecConfig.at(Float.valueOf(290.0f), keyframesSpecConfig.getDurationMillis());
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                Modifier modifierM765size3ABfNKs5 = SizeKt.m765size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier8), CircularIndicatorDiameter);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291474284, "CC(remember):ProgressIndicator.kt#9igjgp");
                boolean zChangedInstance5 = ((((i8 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(j4)) || (i8 & 3072) == 2048) | composerStartRestartGroup.changedInstance(stroke) | composerStartRestartGroup.changed(stateAnimateValue) | composerStartRestartGroup.changed(stateAnimateFloat2) | composerStartRestartGroup.changed(stateAnimateFloat3) | composerStartRestartGroup.changed(stateAnimateFloat);
                if ((i8 & 896) == 256) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z12 = z | zChangedInstance5;
                if (((i8 & 112) ^ 48) > 32) {
                }
                z2 = z12 | z11;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    j5 = j3;
                    j6 = j4;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                            ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    j5 = j3;
                    j6 = j4;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                            ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM765size3ABfNKs5, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j7 = j6;
                f3 = f2;
                j8 = j5;
                i9 = i17;
                modifier3 = modifier8;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) == 0) {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i12 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        i4 &= -7169;
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i5 != 0) {
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw();
                        i8 = i4;
                    } else {
                        i8 = i4;
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = i6;
                    }
                } else {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if (i12 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 8) != 0) {
                        i4 &= -7169;
                        circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                    }
                    if (i5 != 0) {
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw();
                        i8 = i4;
                    } else {
                        i8 = i4;
                        iM2076getCircularIndeterminateStrokeCapKaPHkGw = i6;
                    }
                }
                j3 = circularColor;
                f2 = fM2078getCircularStrokeWidthD9Ej5fM;
                j4 = circularIndeterminateTrackColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-115871647, i8, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:637)");
                }
                ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume6 = composerStartRestartGroup.consume(localDensity6);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                stroke = new Stroke(((Density) objConsume6).mo406toPx0680j_4(f2), 0.0f, iM2076getCircularIndeterminateStrokeCapKaPHkGw, 0, null, 26, null);
                int i18 = iM2076getCircularIndeterminateStrokeCapKaPHkGw;
                InfiniteTransition infiniteTransitionRememberInfiniteTransition6 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
                Modifier modifier9 = companion;
                boolean z13 = true;
                stateAnimateValue = InfiniteTransitionKt.animateValue(infiniteTransitionRememberInfiniteTransition6, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 12), 16);
                stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition6, 0.0f, BaseRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition6, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$endAngle$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1332);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.CircularEasing);
                        keyframesSpecConfig.at(Float.valueOf(290.0f), 666);
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition6, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$startAngle$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        invoke2(keyframesSpecConfig);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                        keyframesSpecConfig.setDurationMillis(1332);
                        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 666), ProgressIndicatorKt.CircularEasing);
                        keyframesSpecConfig.at(Float.valueOf(290.0f), keyframesSpecConfig.getDurationMillis());
                    }
                }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                Modifier modifierM765size3ABfNKs6 = SizeKt.m765size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier9), CircularIndicatorDiameter);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291474284, "CC(remember):ProgressIndicator.kt#9igjgp");
                boolean zChangedInstance6 = ((((i8 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(j4)) || (i8 & 3072) == 2048) | composerStartRestartGroup.changedInstance(stroke) | composerStartRestartGroup.changed(stateAnimateValue) | composerStartRestartGroup.changed(stateAnimateFloat2) | composerStartRestartGroup.changed(stateAnimateFloat3) | composerStartRestartGroup.changed(stateAnimateFloat);
                if ((i8 & 896) == 256) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z14 = z | zChangedInstance6;
                if (((i8 & 112) ^ 48) > 32) {
                }
                z2 = z14 | z13;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    j5 = j3;
                    j6 = j4;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                            ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    j5 = j3;
                    j6 = j4;
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                            ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM765size3ABfNKs6, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j7 = j6;
                f3 = f2;
                j8 = j5;
                i9 = i18;
                modifier3 = modifier9;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$6
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i19) {
                        ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(modifier3, j8, f3, j7, i9, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 24576;
        i6 = i;
        if ((i4 & 9363) == 9362) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) == 0) {
                if (i11 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if (i12 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 8) != 0) {
                    i4 &= -7169;
                    circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                }
                if (i5 != 0) {
                    iM2076getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw();
                    i8 = i4;
                } else {
                    i8 = i4;
                    iM2076getCircularIndeterminateStrokeCapKaPHkGw = i6;
                }
            } else {
                if (i11 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if (i12 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 8) != 0) {
                    i4 &= -7169;
                    circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                }
                if (i5 != 0) {
                    iM2076getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw();
                    i8 = i4;
                } else {
                    i8 = i4;
                    iM2076getCircularIndeterminateStrokeCapKaPHkGw = i6;
                }
            }
            j3 = circularColor;
            f2 = fM2078getCircularStrokeWidthD9Ej5fM;
            j4 = circularIndeterminateTrackColor;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-115871647, i8, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:637)");
            }
            ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume7 = composerStartRestartGroup.consume(localDensity7);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            stroke = new Stroke(((Density) objConsume7).mo406toPx0680j_4(f2), 0.0f, iM2076getCircularIndeterminateStrokeCapKaPHkGw, 0, null, 26, null);
            int i19 = iM2076getCircularIndeterminateStrokeCapKaPHkGw;
            InfiniteTransition infiniteTransitionRememberInfiniteTransition7 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
            Modifier modifier10 = companion;
            boolean z15 = true;
            stateAnimateValue = InfiniteTransitionKt.animateValue(infiniteTransitionRememberInfiniteTransition7, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 12), 16);
            stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition7, 0.0f, BaseRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition7, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$endAngle$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    keyframesSpecConfig.setDurationMillis(1332);
                    keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.CircularEasing);
                    keyframesSpecConfig.at(Float.valueOf(290.0f), 666);
                }
            }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition7, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$startAngle$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    keyframesSpecConfig.setDurationMillis(1332);
                    keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 666), ProgressIndicatorKt.CircularEasing);
                    keyframesSpecConfig.at(Float.valueOf(290.0f), keyframesSpecConfig.getDurationMillis());
                }
            }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            Modifier modifierM765size3ABfNKs7 = SizeKt.m765size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier10), CircularIndicatorDiameter);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291474284, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean zChangedInstance7 = ((((i8 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(j4)) || (i8 & 3072) == 2048) | composerStartRestartGroup.changedInstance(stroke) | composerStartRestartGroup.changed(stateAnimateValue) | composerStartRestartGroup.changed(stateAnimateFloat2) | composerStartRestartGroup.changed(stateAnimateFloat3) | composerStartRestartGroup.changed(stateAnimateFloat);
            if ((i8 & 896) == 256) {
                z = true;
            } else {
                z = false;
            }
            boolean z16 = z | zChangedInstance7;
            if (((i8 & 112) ^ 48) > 32) {
            }
            z2 = z16 | z15;
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2) {
                j5 = j3;
                j6 = j4;
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                        ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                j5 = j3;
                j6 = j4;
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                        ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CanvasKt.Canvas(modifierM765size3ABfNKs7, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j7 = j6;
            f3 = f2;
            j8 = j5;
            i9 = i19;
            modifier3 = modifier10;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) == 0) {
                if (i11 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if (i12 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 8) != 0) {
                    i4 &= -7169;
                    circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                }
                if (i5 != 0) {
                    iM2076getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw();
                    i8 = i4;
                } else {
                    i8 = i4;
                    iM2076getCircularIndeterminateStrokeCapKaPHkGw = i6;
                }
            } else {
                if (i11 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if (i12 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 8) != 0) {
                    i4 &= -7169;
                    circularIndeterminateTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(composerStartRestartGroup, 6);
                }
                if (i5 != 0) {
                    iM2076getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw();
                    i8 = i4;
                } else {
                    i8 = i4;
                    iM2076getCircularIndeterminateStrokeCapKaPHkGw = i6;
                }
            }
            j3 = circularColor;
            f2 = fM2078getCircularStrokeWidthD9Ej5fM;
            j4 = circularIndeterminateTrackColor;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-115871647, i8, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:637)");
            }
            ProvidableCompositionLocal<Density> localDensity8 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume8 = composerStartRestartGroup.consume(localDensity8);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            stroke = new Stroke(((Density) objConsume8).mo406toPx0680j_4(f2), 0.0f, iM2076getCircularIndeterminateStrokeCapKaPHkGw, 0, null, 26, null);
            int i110 = iM2076getCircularIndeterminateStrokeCapKaPHkGw;
            InfiniteTransition infiniteTransitionRememberInfiniteTransition8 = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
            Modifier modifier11 = companion;
            boolean z17 = true;
            stateAnimateValue = InfiniteTransitionKt.animateValue(infiniteTransitionRememberInfiniteTransition8, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 12), 16);
            stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition8, 0.0f, BaseRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition8, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$endAngle$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    keyframesSpecConfig.setDurationMillis(1332);
                    keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.CircularEasing);
                    keyframesSpecConfig.at(Float.valueOf(290.0f), 666);
                }
            }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition8, 0.0f, JumpRotationAngle, AnimationSpecKt.m171infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$startAngle$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    keyframesSpecConfig.setDurationMillis(1332);
                    keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(0.0f), 666), ProgressIndicatorKt.CircularEasing);
                    keyframesSpecConfig.at(Float.valueOf(290.0f), keyframesSpecConfig.getDurationMillis());
                }
            }), null, 0L, 6, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            Modifier modifierM765size3ABfNKs8 = SizeKt.m765size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier11), CircularIndicatorDiameter);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291474284, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean zChangedInstance8 = ((((i8 & 7168) ^ 3072) <= 2048 && composerStartRestartGroup.changed(j4)) || (i8 & 3072) == 2048) | composerStartRestartGroup.changedInstance(stroke) | composerStartRestartGroup.changed(stateAnimateValue) | composerStartRestartGroup.changed(stateAnimateFloat2) | composerStartRestartGroup.changed(stateAnimateFloat3) | composerStartRestartGroup.changed(stateAnimateFloat);
            if ((i8 & 896) == 256) {
                z = true;
            } else {
                z = false;
            }
            boolean z18 = z | zChangedInstance8;
            if (((i8 & 112) ^ 48) > 32) {
            }
            z2 = z18 | z17;
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2) {
                j5 = j3;
                j6 = j4;
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                        ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                j5 = j3;
                j6 = j4;
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        ProgressIndicatorKt.m2101drawCircularIndicatorTrackbw27NRU(drawScope, j6, stroke);
                        ProgressIndicatorKt.m2103drawIndeterminateCircularIndicatorhrjfTZI(drawScope, stateAnimateFloat3.getValue().floatValue() + (((stateAnimateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + stateAnimateFloat.getValue().floatValue(), f2, Math.abs(stateAnimateFloat2.getValue().floatValue() - stateAnimateFloat3.getValue().floatValue()), j5, stroke);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CanvasKt.Canvas(modifierM765size3ABfNKs8, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j7 = j6;
            f3 = f2;
            j8 = j5;
            i9 = i110;
            modifier3 = modifier11;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i111) {
                    ProgressIndicatorKt.m2085CircularProgressIndicatorLxG7B9w(modifier3, j8, f3, j7, i9, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x013e  */
    /* JADX WARN: Code duplicated, block: B:104:0x0151  */
    /* JADX WARN: Code duplicated, block: B:105:0x0153  */
    /* JADX WARN: Code duplicated, block: B:108:0x015a  */
    /* JADX WARN: Code duplicated, block: B:110:0x0162  */
    /* JADX WARN: Code duplicated, block: B:113:0x0187  */
    /* JADX WARN: Code duplicated, block: B:117:0x0196  */
    /* JADX WARN: Code duplicated, block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0049  */
    /* JADX WARN: Code duplicated, block: B:28:0x004d  */
    /* JADX WARN: Code duplicated, block: B:30:0x0055  */
    /* JADX WARN: Code duplicated, block: B:31:0x0058  */
    /* JADX WARN: Code duplicated, block: B:34:0x005e  */
    /* JADX WARN: Code duplicated, block: B:37:0x0064  */
    /* JADX WARN: Code duplicated, block: B:38:0x0067  */
    /* JADX WARN: Code duplicated, block: B:40:0x006b  */
    /* JADX WARN: Code duplicated, block: B:42:0x0073  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:48:0x0080  */
    /* JADX WARN: Code duplicated, block: B:50:0x0084  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:53:0x008f  */
    /* JADX WARN: Code duplicated, block: B:56:0x0095  */
    /* JADX WARN: Code duplicated, block: B:59:0x009d  */
    /* JADX WARN: Code duplicated, block: B:60:0x009f  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:64:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:70:0x00be  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:76:0x00db  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f0 A[PHI: r3 r6 r7 r11 r12
      0x00f0: PHI (r3v24 int) = (r3v19 int), (r3v25 int), (r3v26 int) binds: [B:97:0x0124, B:83:0x00ed, B:84:0x00ef] A[DONT_GENERATE, DONT_INLINE]
      0x00f0: PHI (r6v6 androidx.compose.ui.Modifier) = (r6v3 androidx.compose.ui.Modifier), (r6v2 androidx.compose.ui.Modifier), (r6v2 androidx.compose.ui.Modifier) binds: [B:97:0x0124, B:83:0x00ed, B:84:0x00ef] A[DONT_GENERATE, DONT_INLINE]
      0x00f0: PHI (r7v10 long) = (r7v7 long), (r7v6 long), (r7v6 long) binds: [B:97:0x0124, B:83:0x00ed, B:84:0x00ef] A[DONT_GENERATE, DONT_INLINE]
      0x00f0: PHI (r11v7 float) = (r11v3 float), (r11v2 float), (r11v2 float) binds: [B:97:0x0124, B:83:0x00ed, B:84:0x00ef] A[DONT_GENERATE, DONT_INLINE]
      0x00f0: PHI (r12v10 long) = (r12v7 long), (r12v6 long), (r12v6 long) binds: [B:97:0x0124, B:83:0x00ed, B:84:0x00ef] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:87:0x00fa A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:91:0x0106  */
    /* JADX WARN: Code duplicated, block: B:93:0x0110  */
    /* JADX WARN: Code duplicated, block: B:96:0x011b  */
    /* JADX WARN: Code duplicated, block: B:98:0x0126  */
    @Deprecated(message = "Use the overload that takes `progress` as a lambda", replaceWith = @ReplaceWith(expression = "CircularProgressIndicator(\nprogress = { progress },\nmodifier = modifier,\ncolor = color,\nstrokeWidth = strokeWidth,\ntrackColor = trackColor,\nstrokeCap = strokeCap,\n)", imports = {}))
    /* JADX INFO: renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    public static final void m2082CircularProgressIndicatorDUhRLBM(final float f, Modifier modifier, long j, float f2, long j2, int i, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long circularColor;
        int i5;
        float fM2078getCircularStrokeWidthD9Ej5fM;
        int i6;
        long circularTrackColor;
        int i7;
        int i8;
        int i9;
        int iM2075getCircularDeterminateStrokeCapKaPHkGw;
        float f3;
        long j3;
        int i10;
        Modifier modifier3;
        boolean z;
        Object objRememberedValue;
        Composer composer2;
        final Modifier modifier4;
        final long j4;
        final float f4;
        final long j5;
        final int i11;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1472321743);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CircularProgressIndicator)P(2,1,0:c#ui.graphics.Color,4:c#ui.unit.Dp,5:c#ui.graphics.Color,3:c#ui.graphics.StrokeCap)730@30011L13,732@30145L18,736@30302L12,735@30256L216:ProgressIndicator.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(f) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i12 = i3 & 2;
        if (i12 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if ((i3 & 4) == 0) {
                    circularColor = j;
                    int i13 = composerStartRestartGroup.changed(circularColor) ? 256 : 128;
                    i4 |= i13;
                } else {
                    circularColor = j;
                }
                i4 |= i13;
            } else {
                circularColor = j;
            }
            i5 = i3 & 8;
            if (i5 != 0) {
                if ((i2 & 3072) == 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = f2;
                    if (composerStartRestartGroup.changed(fM2078getCircularStrokeWidthD9Ej5fM)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i4 |= i6;
                }
                if ((i2 & 24576) == 0) {
                    if ((i3 & 16) == 0) {
                        circularTrackColor = j2;
                        int i14 = composerStartRestartGroup.changed(circularTrackColor) ? 16384 : 8192;
                        i4 |= i14;
                    } else {
                        circularTrackColor = j2;
                    }
                    i4 |= i14;
                } else {
                    circularTrackColor = j2;
                }
                i7 = i3 & 32;
                if (i7 != 0) {
                    if ((196608 & i2) == 0) {
                        i8 = i;
                        if (composerStartRestartGroup.changed(i8)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i4 |= i9;
                    }
                    if ((i4 & 74899) == 74898 || !composerStartRestartGroup.getSkipping()) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i12 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                                i4 &= -897;
                            }
                            if (i5 != 0) {
                                fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                            }
                            if ((i3 & 16) != 0) {
                                circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if (i7 != 0) {
                                iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                                f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                                j3 = circularTrackColor;
                                i10 = -1472321743;
                                modifier3 = modifier2;
                            }
                            long j6 = circularColor;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                            if ((i4 & 14) == 4) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Float invoke() {
                                        return Float.valueOf(f);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            composer2 = composerStartRestartGroup;
                            m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j6, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier4 = modifier3;
                            j4 = j6;
                            f4 = f3;
                            j5 = j3;
                            i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                            }
                            if ((i3 & 16) != 0) {
                                i4 &= -57345;
                            }
                        }
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                        j3 = circularTrackColor;
                        long j7 = circularColor;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                        if ((i4 & 14) == 4) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z) {
                            objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Float invoke() {
                                    return Float.valueOf(f);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Float invoke() {
                                    return Float.valueOf(f);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composer2 = composerStartRestartGroup;
                        m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j7, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier3;
                        j4 = j7;
                        f4 = f3;
                        j5 = j3;
                        i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        composer2 = composerStartRestartGroup;
                        modifier4 = modifier2;
                        j4 = circularColor;
                        f4 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j5 = circularTrackColor;
                        i11 = i8;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$8
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i15) {
                                ProgressIndicatorKt.m2082CircularProgressIndicatorDUhRLBM(f, modifier4, j4, f4, j5, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                            }
                        });
                    }
                }
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                i8 = i;
                if ((i4 & 74899) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            j3 = circularTrackColor;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                        } else {
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                            j3 = circularTrackColor;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            j3 = circularTrackColor;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                        } else {
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                            j3 = circularTrackColor;
                        }
                    }
                    long j8 = circularColor;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((i4 & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z) {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j8, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    j4 = j8;
                    f4 = f3;
                    j5 = j3;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            j3 = circularTrackColor;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                        } else {
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                            j3 = circularTrackColor;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            j3 = circularTrackColor;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                        } else {
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                            j3 = circularTrackColor;
                        }
                    }
                    long j9 = circularColor;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((i4 & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z) {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j9, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    j4 = j9;
                    f4 = f3;
                    j5 = j3;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$8
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i15) {
                            ProgressIndicatorKt.m2082CircularProgressIndicatorDUhRLBM(f, modifier4, j4, f4, j5, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 3072;
            fM2078getCircularStrokeWidthD9Ej5fM = f2;
            if ((i2 & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    circularTrackColor = j2;
                    if (composerStartRestartGroup.changed(circularTrackColor)) {
                    }
                    i4 |= i14;
                } else {
                    circularTrackColor = j2;
                }
                i4 |= i14;
            } else {
                circularTrackColor = j2;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                if ((196608 & i2) == 0) {
                    i8 = i;
                    if (composerStartRestartGroup.changed(i8)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((i4 & 74899) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            j3 = circularTrackColor;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                        } else {
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                            j3 = circularTrackColor;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            j3 = circularTrackColor;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                        } else {
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                            j3 = circularTrackColor;
                        }
                    }
                    long j10 = circularColor;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((i4 & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z) {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j10, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    j4 = j10;
                    f4 = f3;
                    j5 = j3;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            j3 = circularTrackColor;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                        } else {
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                            j3 = circularTrackColor;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            j3 = circularTrackColor;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                        } else {
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                            j3 = circularTrackColor;
                        }
                    }
                    long j11 = circularColor;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((i4 & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z) {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j11, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    j4 = j11;
                    f4 = f3;
                    j5 = j3;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$8
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i15) {
                            ProgressIndicatorKt.m2082CircularProgressIndicatorDUhRLBM(f, modifier4, j4, f4, j5, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i8 = i;
            if ((i4 & 74899) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j3 = circularTrackColor;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                    } else {
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                        j3 = circularTrackColor;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j3 = circularTrackColor;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                    } else {
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                        j3 = circularTrackColor;
                    }
                }
                long j12 = circularColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j12, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j4 = j12;
                f4 = f3;
                j5 = j3;
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j3 = circularTrackColor;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                    } else {
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                        j3 = circularTrackColor;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j3 = circularTrackColor;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                    } else {
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                        j3 = circularTrackColor;
                    }
                }
                long j13 = circularColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j13, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j4 = j13;
                f4 = f3;
                j5 = j3;
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$8
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i15) {
                        ProgressIndicatorKt.m2082CircularProgressIndicatorDUhRLBM(f, modifier4, j4, f4, j5, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i3 & 4) == 0) {
                circularColor = j;
                if (composerStartRestartGroup.changed(circularColor)) {
                }
                i4 |= i13;
            } else {
                circularColor = j;
            }
            i4 |= i13;
        } else {
            circularColor = j;
        }
        i5 = i3 & 8;
        if (i5 != 0) {
            if ((i2 & 3072) == 0) {
                fM2078getCircularStrokeWidthD9Ej5fM = f2;
                if (composerStartRestartGroup.changed(fM2078getCircularStrokeWidthD9Ej5fM)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i4 |= i6;
            }
            if ((i2 & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    circularTrackColor = j2;
                    if (composerStartRestartGroup.changed(circularTrackColor)) {
                    }
                    i4 |= i14;
                } else {
                    circularTrackColor = j2;
                }
                i4 |= i14;
            } else {
                circularTrackColor = j2;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                if ((196608 & i2) == 0) {
                    i8 = i;
                    if (composerStartRestartGroup.changed(i8)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((i4 & 74899) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            j3 = circularTrackColor;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                        } else {
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                            j3 = circularTrackColor;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            j3 = circularTrackColor;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                        } else {
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                            j3 = circularTrackColor;
                        }
                    }
                    long j14 = circularColor;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((i4 & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z) {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j14, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    j4 = j14;
                    f4 = f3;
                    j5 = j3;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            j3 = circularTrackColor;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                        } else {
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                            j3 = circularTrackColor;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if (i7 != 0) {
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            j3 = circularTrackColor;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                        } else {
                            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                            iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                            i10 = -1472321743;
                            modifier3 = modifier2;
                            j3 = circularTrackColor;
                        }
                    }
                    long j15 = circularColor;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((i4 & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z) {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j15, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    j4 = j15;
                    f4 = f3;
                    j5 = j3;
                    i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$8
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i15) {
                            ProgressIndicatorKt.m2082CircularProgressIndicatorDUhRLBM(f, modifier4, j4, f4, j5, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i8 = i;
            if ((i4 & 74899) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j3 = circularTrackColor;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                    } else {
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                        j3 = circularTrackColor;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j3 = circularTrackColor;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                    } else {
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                        j3 = circularTrackColor;
                    }
                }
                long j16 = circularColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j16, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j4 = j16;
                f4 = f3;
                j5 = j3;
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j3 = circularTrackColor;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                    } else {
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                        j3 = circularTrackColor;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j3 = circularTrackColor;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                    } else {
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                        j3 = circularTrackColor;
                    }
                }
                long j17 = circularColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j17, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j4 = j17;
                f4 = f3;
                j5 = j3;
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$8
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i15) {
                        ProgressIndicatorKt.m2082CircularProgressIndicatorDUhRLBM(f, modifier4, j4, f4, j5, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 3072;
        fM2078getCircularStrokeWidthD9Ej5fM = f2;
        if ((i2 & 24576) == 0) {
            if ((i3 & 16) == 0) {
                circularTrackColor = j2;
                if (composerStartRestartGroup.changed(circularTrackColor)) {
                }
                i4 |= i14;
            } else {
                circularTrackColor = j2;
            }
            i4 |= i14;
        } else {
            circularTrackColor = j2;
        }
        i7 = i3 & 32;
        if (i7 != 0) {
            if ((196608 & i2) == 0) {
                i8 = i;
                if (composerStartRestartGroup.changed(i8)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
            if ((i4 & 74899) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j3 = circularTrackColor;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                    } else {
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                        j3 = circularTrackColor;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j3 = circularTrackColor;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                    } else {
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                        j3 = circularTrackColor;
                    }
                }
                long j18 = circularColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j18, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j4 = j18;
                f4 = f3;
                j5 = j3;
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j3 = circularTrackColor;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                    } else {
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                        j3 = circularTrackColor;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    }
                    if ((i3 & 16) != 0) {
                        circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if (i7 != 0) {
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        j3 = circularTrackColor;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                    } else {
                        f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                        iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                        i10 = -1472321743;
                        modifier3 = modifier2;
                        j3 = circularTrackColor;
                    }
                }
                long j19 = circularColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z) {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j19, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j4 = j19;
                f4 = f3;
                j5 = j3;
                i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$8
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i15) {
                        ProgressIndicatorKt.m2082CircularProgressIndicatorDUhRLBM(f, modifier4, j4, f4, j5, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        i8 = i;
        if ((i4 & 74899) == 74898) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                    i4 &= -57345;
                }
                if (i7 != 0) {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                    j3 = circularTrackColor;
                    i10 = -1472321743;
                    modifier3 = modifier2;
                } else {
                    f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    i10 = -1472321743;
                    modifier3 = modifier2;
                    j3 = circularTrackColor;
                }
            } else {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                    i4 &= -57345;
                }
                if (i7 != 0) {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                    j3 = circularTrackColor;
                    i10 = -1472321743;
                    modifier3 = modifier2;
                } else {
                    f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    i10 = -1472321743;
                    modifier3 = modifier2;
                    j3 = circularTrackColor;
                }
            }
            long j110 = circularColor;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 14) == 4) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z) {
                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(f);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(f);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j110, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j4 = j110;
            f4 = f3;
            j5 = j3;
            i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                    i4 &= -57345;
                }
                if (i7 != 0) {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                    j3 = circularTrackColor;
                    i10 = -1472321743;
                    modifier3 = modifier2;
                } else {
                    f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    i10 = -1472321743;
                    modifier3 = modifier2;
                    j3 = circularTrackColor;
                }
            } else {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                }
                if ((i3 & 16) != 0) {
                    circularTrackColor = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6);
                    i4 &= -57345;
                }
                if (i7 != 0) {
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw();
                    f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                    j3 = circularTrackColor;
                    i10 = -1472321743;
                    modifier3 = modifier2;
                } else {
                    f3 = fM2078getCircularStrokeWidthD9Ej5fM;
                    iM2075getCircularDeterminateStrokeCapKaPHkGw = i8;
                    i10 = -1472321743;
                    modifier3 = modifier2;
                    j3 = circularTrackColor;
                }
            }
            long j111 = circularColor;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i10, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 14) == 4) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z) {
                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(f);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(f);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            m2084CircularProgressIndicatorIyT6zlY((Function0) objRememberedValue, modifier3, j111, f3, j3, iM2075getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j4 = j111;
            f4 = f3;
            j5 = j3;
            i11 = iM2075getCircularDeterminateStrokeCapKaPHkGw;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$8
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i15) {
                    ProgressIndicatorKt.m2082CircularProgressIndicatorDUhRLBM(f, modifier4, j4, f4, j5, i11, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x004e  */
    /* JADX WARN: Code duplicated, block: B:31:0x005d  */
    /* JADX WARN: Code duplicated, block: B:33:0x0061  */
    /* JADX WARN: Code duplicated, block: B:36:0x0067  */
    /* JADX WARN: Code duplicated, block: B:37:0x006a  */
    /* JADX WARN: Code duplicated, block: B:39:0x006e  */
    /* JADX WARN: Code duplicated, block: B:41:0x0076  */
    /* JADX WARN: Code duplicated, block: B:42:0x0079  */
    /* JADX WARN: Code duplicated, block: B:47:0x0085  */
    /* JADX WARN: Code duplicated, block: B:51:0x0094  */
    /* JADX WARN: Code duplicated, block: B:53:0x009c  */
    /* JADX WARN: Code duplicated, block: B:60:0x00b3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:61:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:65:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:67:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:68:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:72:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:75:0x010a  */
    /* JADX WARN: Code duplicated, block: B:79:0x0116  */
    /* JADX WARN: Code duplicated, block: B:81:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: CircularProgressIndicator-MBs18nI, reason: not valid java name */
    public static final /* synthetic */ void m2086CircularProgressIndicatorMBs18nI(final float f, Modifier modifier, long j, float f2, Composer composer, final int i, final int i2) {
        float f3;
        int i3;
        Modifier modifier2;
        long circularColor;
        int i4;
        float f4;
        int i5;
        Modifier.Companion companion;
        float fM2078getCircularStrokeWidthD9Ej5fM;
        long j2;
        Modifier modifier3;
        final Modifier modifier4;
        final long j3;
        final float f5;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i6;
        Composer composerStartRestartGroup = composer.startRestartGroup(402841196);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CircularProgressIndicator)P(2,1,0:c#ui.graphics.Color,3:c#ui.unit.Dp)750@30727L13,758@30964L18,753@30818L247:ProgressIndicator.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            f3 = f;
        } else if ((i & 6) == 0) {
            f3 = f;
            i3 = (composerStartRestartGroup.changed(f3) ? 4 : 2) | i;
        } else {
            f3 = f;
            i3 = i;
        }
        int i7 = i2 & 2;
        if (i7 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                circularColor = j;
                if ((i2 & 4) == 0 || !composerStartRestartGroup.changed(circularColor)) {
                    i6 = 128;
                } else {
                    i6 = 256;
                }
                i3 |= i6;
            } else {
                circularColor = j;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    f4 = f2;
                    if (composerStartRestartGroup.changed(f4)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i3 & 1171) == 1170 || !composerStartRestartGroup.getSkipping()) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i7 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if ((i2 & 4) != 0) {
                            circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                            i3 &= -897;
                        }
                        if (i4 != 0) {
                            fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                        } else {
                            fM2078getCircularStrokeWidthD9Ej5fM = f4;
                        }
                        j2 = circularColor;
                        modifier3 = companion;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        modifier3 = modifier2;
                        fM2078getCircularStrokeWidthD9Ej5fM = f4;
                        j2 = circularColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(402841196, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:753)");
                    }
                    float f6 = fM2078getCircularStrokeWidthD9Ej5fM;
                    m2082CircularProgressIndicatorDUhRLBM(f3, modifier3, j2, f6, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    j3 = j2;
                    f5 = f6;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier4 = modifier2;
                    j3 = circularColor;
                    f5 = f4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$9
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i8) {
                            ProgressIndicatorKt.m2086CircularProgressIndicatorMBs18nI(f, modifier4, j3, f5, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            f4 = f2;
            if ((i3 & 1171) == 1170) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    }
                    if (i4 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM2078getCircularStrokeWidthD9Ej5fM = f4;
                    }
                    j2 = circularColor;
                    modifier3 = companion;
                } else {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    }
                    if (i4 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM2078getCircularStrokeWidthD9Ej5fM = f4;
                    }
                    j2 = circularColor;
                    modifier3 = companion;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(402841196, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:753)");
                }
                float f7 = fM2078getCircularStrokeWidthD9Ej5fM;
                m2082CircularProgressIndicatorDUhRLBM(f3, modifier3, j2, f7, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j3 = j2;
                f5 = f7;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    }
                    if (i4 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM2078getCircularStrokeWidthD9Ej5fM = f4;
                    }
                    j2 = circularColor;
                    modifier3 = companion;
                } else {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    }
                    if (i4 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM2078getCircularStrokeWidthD9Ej5fM = f4;
                    }
                    j2 = circularColor;
                    modifier3 = companion;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(402841196, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:753)");
                }
                float f8 = fM2078getCircularStrokeWidthD9Ej5fM;
                m2082CircularProgressIndicatorDUhRLBM(f3, modifier3, j2, f8, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j3 = j2;
                f5 = f8;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$9
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i8) {
                        ProgressIndicatorKt.m2086CircularProgressIndicatorMBs18nI(f, modifier4, j3, f5, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            circularColor = j;
            if ((i2 & 4) == 0) {
                i6 = 128;
            } else {
                i6 = 128;
            }
            i3 |= i6;
        } else {
            circularColor = j;
        }
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                f4 = f2;
                if (composerStartRestartGroup.changed(f4)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i3 & 1171) == 1170) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    }
                    if (i4 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM2078getCircularStrokeWidthD9Ej5fM = f4;
                    }
                    j2 = circularColor;
                    modifier3 = companion;
                } else {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    }
                    if (i4 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM2078getCircularStrokeWidthD9Ej5fM = f4;
                    }
                    j2 = circularColor;
                    modifier3 = companion;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(402841196, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:753)");
                }
                float f9 = fM2078getCircularStrokeWidthD9Ej5fM;
                m2082CircularProgressIndicatorDUhRLBM(f3, modifier3, j2, f9, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j3 = j2;
                f5 = f9;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    }
                    if (i4 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM2078getCircularStrokeWidthD9Ej5fM = f4;
                    }
                    j2 = circularColor;
                    modifier3 = companion;
                } else {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -897;
                    }
                    if (i4 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM2078getCircularStrokeWidthD9Ej5fM = f4;
                    }
                    j2 = circularColor;
                    modifier3 = companion;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(402841196, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:753)");
                }
                float f10 = fM2078getCircularStrokeWidthD9Ej5fM;
                m2082CircularProgressIndicatorDUhRLBM(f3, modifier3, j2, f10, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j3 = j2;
                f5 = f10;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$9
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i8) {
                        ProgressIndicatorKt.m2086CircularProgressIndicatorMBs18nI(f, modifier4, j3, f5, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        f4 = f2;
        if ((i3 & 1171) == 1170) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i7 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                }
                if (i4 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                } else {
                    fM2078getCircularStrokeWidthD9Ej5fM = f4;
                }
                j2 = circularColor;
                modifier3 = companion;
            } else {
                if (i7 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                }
                if (i4 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                } else {
                    fM2078getCircularStrokeWidthD9Ej5fM = f4;
                }
                j2 = circularColor;
                modifier3 = companion;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(402841196, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:753)");
            }
            float f11 = fM2078getCircularStrokeWidthD9Ej5fM;
            m2082CircularProgressIndicatorDUhRLBM(f3, modifier3, j2, f11, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j3 = j2;
            f5 = f11;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i7 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                }
                if (i4 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                } else {
                    fM2078getCircularStrokeWidthD9Ej5fM = f4;
                }
                j2 = circularColor;
                modifier3 = companion;
            } else {
                if (i7 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i3 &= -897;
                }
                if (i4 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                } else {
                    fM2078getCircularStrokeWidthD9Ej5fM = f4;
                }
                j2 = circularColor;
                modifier3 = companion;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(402841196, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:753)");
            }
            float f12 = fM2078getCircularStrokeWidthD9Ej5fM;
            m2082CircularProgressIndicatorDUhRLBM(f3, modifier3, j2, f12, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2075getCircularDeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j3 = j2;
            f5 = f12;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$9
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i8) {
                    ProgressIndicatorKt.m2086CircularProgressIndicatorMBs18nI(f, modifier4, j3, f5, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0078  */
    /* JADX WARN: Code duplicated, block: B:42:0x0080  */
    /* JADX WARN: Code duplicated, block: B:49:0x0094 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:50:0x0096  */
    /* JADX WARN: Code duplicated, block: B:51:0x009b  */
    /* JADX WARN: Code duplicated, block: B:54:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:56:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:57:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:61:0x00be  */
    /* JADX WARN: Code duplicated, block: B:64:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:68:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:70:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: CircularProgressIndicator-aM-cp0Q, reason: not valid java name */
    public static final /* synthetic */ void m2087CircularProgressIndicatoraMcp0Q(Modifier modifier, long j, float f, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long circularColor;
        float f2;
        Modifier.Companion companion;
        float fM2078getCircularStrokeWidthD9Ej5fM;
        long j2;
        Modifier modifier3;
        final Modifier modifier4;
        final long j3;
        final float f3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(947193756);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CircularProgressIndicator)P(1,0:c#ui.graphics.Color,2:c#ui.unit.Dp)767@31299L13,774@31518L18,770@31390L231:ProgressIndicator.kt#uh7d8r");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & 48) == 0) {
            circularColor = j;
            i3 |= ((i2 & 2) == 0 && composerStartRestartGroup.changed(circularColor)) ? 32 : 16;
        } else {
            circularColor = j;
        }
        int i5 = i2 & 4;
        if (i5 == 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                f2 = f;
                i3 |= composerStartRestartGroup.changed(f2) ? 256 : 128;
            }
            if ((i3 & 147) == 146 || !composerStartRestartGroup.getSkipping()) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                        i3 &= -113;
                    }
                    if (i5 != 0) {
                        fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                    } else {
                        fM2078getCircularStrokeWidthD9Ej5fM = f2;
                    }
                    j2 = circularColor;
                    modifier3 = companion;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 2) != 0) {
                        i3 &= -113;
                    }
                    fM2078getCircularStrokeWidthD9Ej5fM = f2;
                    j2 = circularColor;
                    modifier3 = modifier2;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(947193756, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:770)");
                }
                m2085CircularProgressIndicatorLxG7B9w(modifier3, j2, fM2078getCircularStrokeWidthD9Ej5fM, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j3 = j2;
                f3 = fM2078getCircularStrokeWidthD9Ej5fM;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier4 = modifier2;
                j3 = circularColor;
                f3 = f2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$10
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i6) {
                        ProgressIndicatorKt.m2087CircularProgressIndicatoraMcp0Q(modifier4, j3, f3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        f2 = f;
        if ((i3 & 147) == 146) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                } else {
                    fM2078getCircularStrokeWidthD9Ej5fM = f2;
                }
                j2 = circularColor;
                modifier3 = companion;
            } else {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                } else {
                    fM2078getCircularStrokeWidthD9Ej5fM = f2;
                }
                j2 = circularColor;
                modifier3 = companion;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(947193756, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:770)");
            }
            m2085CircularProgressIndicatorLxG7B9w(modifier3, j2, fM2078getCircularStrokeWidthD9Ej5fM, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j3 = j2;
            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                } else {
                    fM2078getCircularStrokeWidthD9Ej5fM = f2;
                }
                j2 = circularColor;
                modifier3 = companion;
            } else {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    circularColor = ProgressIndicatorDefaults.INSTANCE.getCircularColor(composerStartRestartGroup, 6);
                    i3 &= -113;
                }
                if (i5 != 0) {
                    fM2078getCircularStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2078getCircularStrokeWidthD9Ej5fM();
                } else {
                    fM2078getCircularStrokeWidthD9Ej5fM = f2;
                }
                j2 = circularColor;
                modifier3 = companion;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(947193756, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:770)");
            }
            m2085CircularProgressIndicatorLxG7B9w(modifier3, j2, fM2078getCircularStrokeWidthD9Ej5fM, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(composerStartRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2076getCircularIndeterminateStrokeCapKaPHkGw(), composerStartRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j3 = j2;
            f3 = fM2078getCircularStrokeWidthD9Ej5fM;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$10
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i6) {
                    ProgressIndicatorKt.m2087CircularProgressIndicatoraMcp0Q(modifier4, j3, f3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawCircularIndicator-42QJj7c, reason: not valid java name */
    public static final void m2100drawCircularIndicator42QJj7c(DrawScope drawScope, float f, float f2, long j, Stroke stroke) {
        float width = stroke.getWidth() / 2.0f;
        float fM3939getWidthimpl = Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc()) - (2.0f * width);
        DrawScope.CC.m4717drawArcyD3GUKo$default(drawScope, j, f, f2, false, OffsetKt.Offset(width, width), androidx.compose.ui.geometry.SizeKt.Size(fM3939getWidthimpl, fM3939getWidthimpl), 0.0f, stroke, null, 0, 832, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawCircularIndicatorTrack-bw27NRU, reason: not valid java name */
    public static final void m2101drawCircularIndicatorTrackbw27NRU(DrawScope drawScope, long j, Stroke stroke) {
        m2100drawCircularIndicator42QJj7c(drawScope, 0.0f, 360.0f, j, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawDeterminateCircularIndicator-42QJj7c, reason: not valid java name */
    public static final void m2102drawDeterminateCircularIndicator42QJj7c(DrawScope drawScope, float f, float f2, long j, Stroke stroke) {
        m2100drawCircularIndicator42QJj7c(drawScope, f, f2, j, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawIndeterminateCircularIndicator-hrjfTZI, reason: not valid java name */
    public static final void m2103drawIndeterminateCircularIndicatorhrjfTZI(DrawScope drawScope, float f, float f2, float f3, long j, Stroke stroke) {
        m2100drawCircularIndicator42QJj7c(drawScope, f + (StrokeCap.m4527equalsimpl0(stroke.getCap(), StrokeCap.INSTANCE.m4531getButtKaPHkGw()) ? 0.0f : ((f2 / Dp.m6890constructorimpl(CircularIndicatorDiameter / 2.0f)) * 57.29578f) / 2.0f), Math.max(f3, 0.1f), j, stroke);
    }

    public static final float getLinearIndicatorWidth() {
        return LinearIndicatorWidth;
    }

    public static final float getLinearIndicatorHeight() {
        return LinearIndicatorHeight;
    }

    public static final float getCircularIndicatorDiameter() {
        return CircularIndicatorDiameter;
    }

    static {
        float fM6890constructorimpl = Dp.m6890constructorimpl(10.0f);
        SemanticsBoundsPadding = fM6890constructorimpl;
        IncreaseSemanticsBounds = PaddingKt.m722paddingVpY3zN4$default(SemanticsModifierKt.semantics(LayoutModifierKt.layout(Modifier.INSTANCE, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.material3.ProgressIndicatorKt$IncreaseSemanticsBounds$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                return m2105invoke3p2s80s(measureScope, measurable, constraints.getValue());
            }

            /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
            public final MeasureResult m2105invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                final int i = measureScope.mo400roundToPx0680j_4(ProgressIndicatorKt.SemanticsBoundsPadding);
                int i2 = i * 2;
                final Placeable placeableMo5581measureBRTryo0 = measurable.mo5581measureBRTryo0(ConstraintsKt.m6852offsetNN6EwU(j, 0, i2));
                return MeasureScope.CC.layout$default(measureScope, placeableMo5581measureBRTryo0.getWidth(), placeableMo5581measureBRTryo0.getHeight() - i2, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$IncreaseSemanticsBounds$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                        invoke2(placementScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Placeable.PlacementScope placementScope) {
                        Placeable.PlacementScope.place$default(placementScope, placeableMo5581measureBRTryo0, 0, -i, 0.0f, 4, null);
                    }
                }, 4, null);
            }
        }), true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$IncreaseSemanticsBounds$2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }
        }), 0.0f, fM6890constructorimpl, 1, null);
        LinearIndicatorWidth = Dp.m6890constructorimpl(240.0f);
        LinearIndicatorHeight = ProgressIndicatorTokens.INSTANCE.m3216getTrackThicknessD9Ej5fM();
        CircularIndicatorDiameter = Dp.m6890constructorimpl(ProgressIndicatorTokens.INSTANCE.m3213getSizeD9Ej5fM() - Dp.m6890constructorimpl(ProgressIndicatorTokens.INSTANCE.m3216getTrackThicknessD9Ej5fM() * 2.0f));
        FirstLineHeadEasing = new CubicBezierEasing(0.2f, 0.0f, 0.8f, 1.0f);
        FirstLineTailEasing = new CubicBezierEasing(0.4f, 0.0f, 1.0f, 1.0f);
        SecondLineHeadEasing = new CubicBezierEasing(0.0f, 0.0f, 0.65f, 1.0f);
        SecondLineTailEasing = new CubicBezierEasing(0.1f, 0.0f, 0.45f, 1.0f);
        CircularEasing = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);
    }
}
