package androidx.compose.material3;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.ExtendedFabPrimaryTokens;
import androidx.compose.material3.tokens.FabPrimaryLargeTokens;
import androidx.compose.material3.tokens.FabPrimarySmallTokens;
import androidx.compose.material3.tokens.FabPrimaryTokens;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FloatingActionButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\u001a|\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u001c\u0010\u0019\u001a\u0018\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000b0\u001a¢\u0006\u0002\b\u001c¢\u0006\u0002\b\u001dH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u008e\u0001\u0010\n\u001a\u00020\u000b2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u001c2\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u001c2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001aq\u0010&\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u001cH\u0007ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001aq\u0010)\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u001cH\u0007ø\u0001\u0000¢\u0006\u0004\b*\u0010(\u001aq\u0010+\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u001cH\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010(\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006-"}, d2 = {"ExtendedFabCollapseAnimation", "Landroidx/compose/animation/ExitTransition;", "ExtendedFabEndIconPadding", "Landroidx/compose/ui/unit/Dp;", "F", "ExtendedFabExpandAnimation", "Landroidx/compose/animation/EnterTransition;", "ExtendedFabMinimumWidth", "ExtendedFabStartIconPadding", "ExtendedFabTextPadding", "ExtendedFloatingActionButton", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "elevation", "Landroidx/compose/material3/FloatingActionButtonElevation;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "ExtendedFloatingActionButton-X-z6DiA", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material3/FloatingActionButtonElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "text", "icon", "expanded", "", "ExtendedFloatingActionButton-ElI5-7k", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material3/FloatingActionButtonElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "FloatingActionButton", "FloatingActionButton-X-z6DiA", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material3/FloatingActionButtonElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "LargeFloatingActionButton", "LargeFloatingActionButton-X-z6DiA", "SmallFloatingActionButton", "SmallFloatingActionButton-X-z6DiA", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FloatingActionButtonKt {
    private static final float ExtendedFabStartIconPadding = Dp.m6890constructorimpl(16.0f);
    private static final float ExtendedFabEndIconPadding = Dp.m6890constructorimpl(12.0f);
    private static final float ExtendedFabTextPadding = Dp.m6890constructorimpl(20.0f);
    private static final float ExtendedFabMinimumWidth = Dp.m6890constructorimpl(80.0f);
    private static final ExitTransition ExtendedFabCollapseAnimation = EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(100, 0, MotionTokens.INSTANCE.getEasingLinearCubicBezier(), 2, null), 0.0f, 2, null).plus(EnterExitTransitionKt.shrinkHorizontally$default(AnimationSpecKt.tween$default(500, 0, MotionTokens.INSTANCE.getEasingEmphasizedCubicBezier(), 2, null), Alignment.INSTANCE.getStart(), false, null, 12, null));
    private static final EnterTransition ExtendedFabExpandAnimation = EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween(200, 100, MotionTokens.INSTANCE.getEasingLinearCubicBezier()), 0.0f, 2, null).plus(EnterExitTransitionKt.expandHorizontally$default(AnimationSpecKt.tween$default(500, 0, MotionTokens.INSTANCE.getEasingEmphasizedCubicBezier(), 2, null), Alignment.INSTANCE.getStart(), false, null, 12, null));

    /* JADX WARN: Code duplicated, block: B:110:0x0146 A[PHI: r0 r2 r3 r4 r5 r14 r16 r17
      0x0146: PHI (r0v21 androidx.compose.foundation.interaction.MutableInteractionSource) = 
      (r0v14 androidx.compose.foundation.interaction.MutableInteractionSource)
      (r0v27 androidx.compose.foundation.interaction.MutableInteractionSource)
     binds: [B:130:0x01ba, B:109:0x013d] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r2v12 int) = (r2v3 int), (r2v13 int) binds: [B:130:0x01ba, B:109:0x013d] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r3v19 androidx.compose.ui.Modifier) = (r3v9 androidx.compose.ui.Modifier), (r3v2 androidx.compose.ui.Modifier) binds: [B:130:0x01ba, B:109:0x013d] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r4v34 int) = (r4v8 int), (r4v35 int) binds: [B:130:0x01ba, B:109:0x013d] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r5v10 androidx.compose.material3.FloatingActionButtonElevation) = 
      (r5v6 androidx.compose.material3.FloatingActionButtonElevation)
      (r5v5 androidx.compose.material3.FloatingActionButtonElevation)
     binds: [B:130:0x01ba, B:109:0x013d] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r14v6 long) = (r14v2 long), (r14v1 long) binds: [B:130:0x01ba, B:109:0x013d] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r16v4 androidx.compose.ui.graphics.Shape) = (r16v2 androidx.compose.ui.graphics.Shape), (r16v5 androidx.compose.ui.graphics.Shape) binds: [B:130:0x01ba, B:109:0x013d] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r17v8 long) = (r17v6 long), (r17v9 long) binds: [B:130:0x01ba, B:109:0x013d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:111:0x014a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:112:0x014c  */
    /* JADX WARN: Code duplicated, block: B:113:0x0151  */
    /* JADX WARN: Code duplicated, block: B:116:0x0157  */
    /* JADX WARN: Code duplicated, block: B:117:0x0162  */
    /* JADX WARN: Code duplicated, block: B:120:0x0168  */
    /* JADX WARN: Code duplicated, block: B:121:0x0176  */
    /* JADX WARN: Code duplicated, block: B:124:0x017c  */
    /* JADX WARN: Code duplicated, block: B:127:0x018d  */
    /* JADX WARN: Code duplicated, block: B:128:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:131:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:134:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:137:0x01d9  */
    /* JADX WARN: Code duplicated, block: B:139:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:141:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:144:0x025d  */
    /* JADX WARN: Code duplicated, block: B:148:0x0271  */
    /* JADX WARN: Code duplicated, block: B:150:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004f  */
    /* JADX WARN: Code duplicated, block: B:28:0x0053  */
    /* JADX WARN: Code duplicated, block: B:30:0x005b  */
    /* JADX WARN: Code duplicated, block: B:31:0x005e  */
    /* JADX WARN: Code duplicated, block: B:34:0x0064  */
    /* JADX WARN: Code duplicated, block: B:37:0x006a  */
    /* JADX WARN: Code duplicated, block: B:42:0x0079  */
    /* JADX WARN: Code duplicated, block: B:44:0x007d  */
    /* JADX WARN: Code duplicated, block: B:47:0x0083  */
    /* JADX WARN: Code duplicated, block: B:52:0x0092  */
    /* JADX WARN: Code duplicated, block: B:54:0x0096  */
    /* JADX WARN: Code duplicated, block: B:57:0x009d  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:68:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:73:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:79:0x00db  */
    /* JADX WARN: Code duplicated, block: B:81:0x00df  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:92:0x010e  */
    /* JADX WARN: Code duplicated, block: B:94:0x011b  */
    /* JADX INFO: renamed from: FloatingActionButton-X-z6DiA, reason: not valid java name */
    public static final void m1843FloatingActionButtonXz6DiA(final Function0<Unit> function0, Modifier modifier, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        Function0<Unit> function1;
        int i3;
        Modifier modifier2;
        Shape shape2;
        long j3;
        final long j4;
        FloatingActionButtonElevation floatingActionButtonElevationM1835elevationxZ9QkE;
        int i4;
        int i5;
        int i6;
        Modifier.Companion companion;
        Shape shape3;
        int i7;
        long containerColor;
        int i8;
        long j5;
        MutableInteractionSource mutableInteractionSource2;
        int i9;
        int i10;
        Shape shape4;
        long j6;
        int i11;
        MutableInteractionSource mutableInteractionSource3;
        MutableInteractionSource mutableInteractionSource4;
        final MutableInteractionSource mutableInteractionSource5;
        final Modifier modifier3;
        final FloatingActionButtonElevation floatingActionButtonElevation2;
        final Shape shape5;
        final long j7;
        final long j8;
        Object objRememberedValue;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i12;
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(-731723913);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(FloatingActionButton)P(6,5,7,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3,4)100@4948L5,101@5012L14,102@5054L31,103@5163L11,116@5678L54,118@5792L536,109@5399L929:FloatingActionButton.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i & 6) == 0) {
                i3 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        int i14 = i2 & 2;
        if (i14 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if ((i2 & 4) == 0) {
                    shape2 = shape;
                    int i15 = composerStartRestartGroup.changed(shape2) ? 256 : 128;
                    i3 |= i15;
                } else {
                    shape2 = shape;
                }
                i3 |= i15;
            } else {
                shape2 = shape;
            }
            if ((i & 3072) == 0) {
                j3 = j;
                if ((i2 & 8) == 0 || !composerStartRestartGroup.changed(j3)) {
                    i13 = 1024;
                } else {
                    i13 = 2048;
                }
                i3 |= i13;
            } else {
                j3 = j;
            }
            if ((i & 24576) == 0) {
                j4 = j2;
                if ((i2 & 16) == 0 || !composerStartRestartGroup.changed(j4)) {
                    i12 = 8192;
                } else {
                    i12 = 16384;
                }
                i3 |= i12;
            } else {
                j4 = j2;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                    int i16 = composerStartRestartGroup.changed(floatingActionButtonElevationM1835elevationxZ9QkE) ? 131072 : 65536;
                    i3 |= i16;
                } else {
                    floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                }
                i3 |= i16;
            } else {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
            }
            i4 = i2 & 64;
            if (i4 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i5 = 1048576;
                } else {
                    i5 = 524288;
                }
                i3 |= i5;
            }
            if ((i2 & 128) != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i6 = 8388608;
                } else {
                    i6 = 4194304;
                }
                i3 |= i6;
            }
            if ((4793491 & i3) == 4793490 || !composerStartRestartGroup.getSkipping()) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i14 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        shape3 = FloatingActionButtonDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        mutableInteractionSource2 = null;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i9 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        mutableInteractionSource2 = null;
                        i9 = i8;
                    }
                    modifier2 = companion;
                    i10 = i9;
                    shape4 = shape3;
                    j6 = j5;
                    i11 = -731723913;
                    if (i4 != 0) {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i11, i10, -1, "androidx.compose.material3.FloatingActionButton (FloatingActionButton.kt:106)");
                    }
                    composerStartRestartGroup.startReplaceGroup(519755085);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "108@5355L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 519755736, "CC(remember):FloatingActionButton.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i17 = i10 & 14;
                    int i18 = i10 << 3;
                    long j9 = j4;
                    SurfaceKt.m2264Surfaceo_FOJdg(function1, SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.m6134setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m6115getButtono7Vup1c());
                        }
                    }, 1, mutableInteractionSource2), false, shape4, j6, j9, floatingActionButtonElevationM1835elevationxZ9QkE.getDefaultElevation(), floatingActionButtonElevationM1835elevationxZ9QkE.shadowElevation$material3_release(mutableInteractionSource4, composerStartRestartGroup, (i10 >> 12) & 112).getValue().m6904unboximpl(), null, mutableInteractionSource4, ComposableLambdaKt.rememberComposableLambda(1249316354, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$2
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

                        public final void invoke(Composer composer2, int i19) {
                            ComposerKt.sourceInformation(composer2, "C121@5936L5,122@5952L370,119@5802L520:FloatingActionButton.kt#uh7d8r");
                            if ((i19 & 3) != 2 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1249316354, i19, -1, "androidx.compose.material3.FloatingActionButton.<anonymous> (FloatingActionButton.kt:119)");
                                }
                                long j10 = j4;
                                TextStyle value = TypographyKt.getValue(ExtendedFabPrimaryTokens.INSTANCE.getLabelTextFont(), composer2, 6);
                                final Function2<Composer, Integer, Unit> function3 = function2;
                                ProvideContentColorTextStyleKt.m2651ProvideContentColorTextStyle3JVO9M(j10, value, ComposableLambdaKt.rememberComposableLambda(-1771489750, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$2.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i20) {
                                        ComposerKt.sourceInformation(composer3, "C123@5966L346:FloatingActionButton.kt#uh7d8r");
                                        if ((i20 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1771489750, i20, -1, "androidx.compose.material3.FloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:123)");
                                            }
                                            Modifier modifierM749defaultMinSizeVpY3zN4 = SizeKt.m749defaultMinSizeVpY3zN4(Modifier.INSTANCE, FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM(), FabPrimaryTokens.INSTANCE.m2960getContainerHeightD9Ej5fM());
                                            Alignment center = Alignment.INSTANCE.getCenter();
                                            Function2<Composer, Integer, Unit> function4 = function3;
                                            ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                                            ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierM749defaultMinSizeVpY3zN4);
                                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                            ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                            if (!(composer3.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer3.startReusableNode();
                                            if (composer3.getInserting()) {
                                                composer3.createNode(constructor);
                                            } else {
                                                composer3.useNode();
                                            }
                                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer3);
                                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                            }
                                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                            ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(composer3, -1494899604, "C131@6289L9:FloatingActionButton.kt#uh7d8r");
                                            function4.invoke(composer3, 0);
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            composer3.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composer2, 54), composer2, RendererCapabilities.DECODER_SUPPORT_MASK);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, i17 | (i18 & 7168) | (57344 & i18) | (i18 & 458752), 6, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource5 = mutableInteractionSource3;
                    modifier3 = modifier2;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    shape5 = shape4;
                    j7 = j6;
                    j8 = j9;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                    }
                    i10 = i3;
                    shape4 = shape2;
                    j6 = j3;
                    mutableInteractionSource2 = null;
                    i11 = -731723913;
                }
                mutableInteractionSource3 = mutableInteractionSource;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i11, i10, -1, "androidx.compose.material3.FloatingActionButton (FloatingActionButton.kt:106)");
                }
                composerStartRestartGroup.startReplaceGroup(519755085);
                ComposerKt.sourceInformation(composerStartRestartGroup, "108@5355L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 519755736, "CC(remember):FloatingActionButton.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                composerStartRestartGroup.endReplaceGroup();
                int i19 = i10 & 14;
                int i110 = i10 << 3;
                long j10 = j4;
                SurfaceKt.m2264Surfaceo_FOJdg(function1, SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        SemanticsPropertiesKt.m6134setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m6115getButtono7Vup1c());
                    }
                }, 1, mutableInteractionSource2), false, shape4, j6, j10, floatingActionButtonElevationM1835elevationxZ9QkE.getDefaultElevation(), floatingActionButtonElevationM1835elevationxZ9QkE.shadowElevation$material3_release(mutableInteractionSource4, composerStartRestartGroup, (i10 >> 12) & 112).getValue().m6904unboximpl(), null, mutableInteractionSource4, ComposableLambdaKt.rememberComposableLambda(1249316354, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$2
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

                    public final void invoke(Composer composer2, int i111) {
                        ComposerKt.sourceInformation(composer2, "C121@5936L5,122@5952L370,119@5802L520:FloatingActionButton.kt#uh7d8r");
                        if ((i111 & 3) != 2 || !composer2.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1249316354, i111, -1, "androidx.compose.material3.FloatingActionButton.<anonymous> (FloatingActionButton.kt:119)");
                            }
                            long j11 = j4;
                            TextStyle value = TypographyKt.getValue(ExtendedFabPrimaryTokens.INSTANCE.getLabelTextFont(), composer2, 6);
                            final Function2<? super Composer, ? super Integer, Unit> function3 = function2;
                            ProvideContentColorTextStyleKt.m2651ProvideContentColorTextStyle3JVO9M(j11, value, ComposableLambdaKt.rememberComposableLambda(-1771489750, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$2.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i20) {
                                    ComposerKt.sourceInformation(composer3, "C123@5966L346:FloatingActionButton.kt#uh7d8r");
                                    if ((i20 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1771489750, i20, -1, "androidx.compose.material3.FloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:123)");
                                        }
                                        Modifier modifierM749defaultMinSizeVpY3zN4 = SizeKt.m749defaultMinSizeVpY3zN4(Modifier.INSTANCE, FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM(), FabPrimaryTokens.INSTANCE.m2960getContainerHeightD9Ej5fM());
                                        Alignment center = Alignment.INSTANCE.getCenter();
                                        Function2<Composer, Integer, Unit> function4 = function3;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierM749defaultMinSizeVpY3zN4);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer3.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            composer3.createNode(constructor);
                                        } else {
                                            composer3.useNode();
                                        }
                                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer3);
                                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1494899604, "C131@6289L9:FloatingActionButton.kt#uh7d8r");
                                        function4.invoke(composer3, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        composer3.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composer2, 54), composer2, RendererCapabilities.DECODER_SUPPORT_MASK);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer2.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, i19 | (i110 & 7168) | (57344 & i110) | (i110 & 458752), 6, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource5 = mutableInteractionSource3;
                modifier3 = modifier2;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                shape5 = shape4;
                j7 = j6;
                j8 = j10;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                mutableInteractionSource5 = mutableInteractionSource;
                modifier3 = modifier2;
                shape5 = shape2;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                j7 = j3;
                j8 = j4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$3
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

                    public final void invoke(Composer composer2, int i20) {
                        FloatingActionButtonKt.m1843FloatingActionButtonXz6DiA(function0, modifier3, shape5, j7, j8, floatingActionButtonElevation2, mutableInteractionSource5, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i2 & 4) == 0) {
                shape2 = shape;
                if (composerStartRestartGroup.changed(shape2)) {
                }
                i3 |= i15;
            } else {
                shape2 = shape;
            }
            i3 |= i15;
        } else {
            shape2 = shape;
        }
        if ((i & 3072) == 0) {
            j3 = j;
            if ((i2 & 8) == 0) {
                i13 = 1024;
            } else {
                i13 = 1024;
            }
            i3 |= i13;
        } else {
            j3 = j;
        }
        if ((i & 24576) == 0) {
            j4 = j2;
            if ((i2 & 16) == 0) {
                i12 = 8192;
            } else {
                i12 = 8192;
            }
            i3 |= i12;
        } else {
            j4 = j2;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                if (composerStartRestartGroup.changed(floatingActionButtonElevationM1835elevationxZ9QkE)) {
                }
                i3 |= i16;
            } else {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
            }
            i3 |= i16;
        } else {
            floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
        }
        i4 = i2 & 64;
        if (i4 != 0) {
            i3 |= 1572864;
        } else if ((i & 1572864) == 0) {
            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                i5 = 1048576;
            } else {
                i5 = 524288;
            }
            i3 |= i5;
        }
        if ((i2 & 128) != 0) {
            i3 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changedInstance(function2)) {
                i6 = 8388608;
            } else {
                i6 = 4194304;
            }
            i3 |= i6;
        }
        if ((4793491 & i3) == 4793490) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i14 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U2 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U2;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    mutableInteractionSource2 = null;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i9 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    mutableInteractionSource2 = null;
                    i9 = i8;
                }
                modifier2 = companion;
                i10 = i9;
                shape4 = shape3;
                j6 = j5;
                i11 = -731723913;
                if (i4 != 0) {
                    mutableInteractionSource3 = mutableInteractionSource2;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
            } else {
                if (i14 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U3 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U3;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    mutableInteractionSource2 = null;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i9 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    mutableInteractionSource2 = null;
                    i9 = i8;
                }
                modifier2 = companion;
                i10 = i9;
                shape4 = shape3;
                j6 = j5;
                i11 = -731723913;
                if (i4 != 0) {
                    mutableInteractionSource3 = mutableInteractionSource2;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i11, i10, -1, "androidx.compose.material3.FloatingActionButton (FloatingActionButton.kt:106)");
            }
            composerStartRestartGroup.startReplaceGroup(519755085);
            ComposerKt.sourceInformation(composerStartRestartGroup, "108@5355L39");
            if (mutableInteractionSource3 == null) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 519755736, "CC(remember):FloatingActionButton.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
            } else {
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            composerStartRestartGroup.endReplaceGroup();
            int i111 = i10 & 14;
            int i112 = i10 << 3;
            long j11 = j4;
            SurfaceKt.m2264Surfaceo_FOJdg(function1, SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    SemanticsPropertiesKt.m6134setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m6115getButtono7Vup1c());
                }
            }, 1, mutableInteractionSource2), false, shape4, j6, j11, floatingActionButtonElevationM1835elevationxZ9QkE.getDefaultElevation(), floatingActionButtonElevationM1835elevationxZ9QkE.shadowElevation$material3_release(mutableInteractionSource4, composerStartRestartGroup, (i10 >> 12) & 112).getValue().m6904unboximpl(), null, mutableInteractionSource4, ComposableLambdaKt.rememberComposableLambda(1249316354, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$2
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

                public final void invoke(Composer composer2, int i113) {
                    ComposerKt.sourceInformation(composer2, "C121@5936L5,122@5952L370,119@5802L520:FloatingActionButton.kt#uh7d8r");
                    if ((i113 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1249316354, i113, -1, "androidx.compose.material3.FloatingActionButton.<anonymous> (FloatingActionButton.kt:119)");
                        }
                        long j12 = j4;
                        TextStyle value = TypographyKt.getValue(ExtendedFabPrimaryTokens.INSTANCE.getLabelTextFont(), composer2, 6);
                        final Function2<? super Composer, ? super Integer, Unit> function3 = function2;
                        ProvideContentColorTextStyleKt.m2651ProvideContentColorTextStyle3JVO9M(j12, value, ComposableLambdaKt.rememberComposableLambda(-1771489750, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i20) {
                                ComposerKt.sourceInformation(composer3, "C123@5966L346:FloatingActionButton.kt#uh7d8r");
                                if ((i20 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1771489750, i20, -1, "androidx.compose.material3.FloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:123)");
                                    }
                                    Modifier modifierM749defaultMinSizeVpY3zN4 = SizeKt.m749defaultMinSizeVpY3zN4(Modifier.INSTANCE, FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM(), FabPrimaryTokens.INSTANCE.m2960getContainerHeightD9Ej5fM());
                                    Alignment center = Alignment.INSTANCE.getCenter();
                                    Function2<Composer, Integer, Unit> function4 = function3;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierM749defaultMinSizeVpY3zN4);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer3);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1494899604, "C131@6289L9:FloatingActionButton.kt#uh7d8r");
                                    function4.invoke(composer3, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composer2, 54), composer2, RendererCapabilities.DECODER_SUPPORT_MASK);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, i111 | (i112 & 7168) | (57344 & i112) | (i112 & 458752), 6, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            mutableInteractionSource5 = mutableInteractionSource3;
            modifier3 = modifier2;
            floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
            shape5 = shape4;
            j7 = j6;
            j8 = j11;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i14 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U4 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U4;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    mutableInteractionSource2 = null;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i9 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    mutableInteractionSource2 = null;
                    i9 = i8;
                }
                modifier2 = companion;
                i10 = i9;
                shape4 = shape3;
                j6 = j5;
                i11 = -731723913;
                if (i4 != 0) {
                    mutableInteractionSource3 = mutableInteractionSource2;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
            } else {
                if (i14 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U5 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U5;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    mutableInteractionSource2 = null;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i9 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    mutableInteractionSource2 = null;
                    i9 = i8;
                }
                modifier2 = companion;
                i10 = i9;
                shape4 = shape3;
                j6 = j5;
                i11 = -731723913;
                if (i4 != 0) {
                    mutableInteractionSource3 = mutableInteractionSource2;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i11, i10, -1, "androidx.compose.material3.FloatingActionButton (FloatingActionButton.kt:106)");
            }
            composerStartRestartGroup.startReplaceGroup(519755085);
            ComposerKt.sourceInformation(composerStartRestartGroup, "108@5355L39");
            if (mutableInteractionSource3 == null) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 519755736, "CC(remember):FloatingActionButton.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
            } else {
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            composerStartRestartGroup.endReplaceGroup();
            int i113 = i10 & 14;
            int i114 = i10 << 3;
            long j12 = j4;
            SurfaceKt.m2264Surfaceo_FOJdg(function1, SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    SemanticsPropertiesKt.m6134setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m6115getButtono7Vup1c());
                }
            }, 1, mutableInteractionSource2), false, shape4, j6, j12, floatingActionButtonElevationM1835elevationxZ9QkE.getDefaultElevation(), floatingActionButtonElevationM1835elevationxZ9QkE.shadowElevation$material3_release(mutableInteractionSource4, composerStartRestartGroup, (i10 >> 12) & 112).getValue().m6904unboximpl(), null, mutableInteractionSource4, ComposableLambdaKt.rememberComposableLambda(1249316354, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$2
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

                public final void invoke(Composer composer2, int i115) {
                    ComposerKt.sourceInformation(composer2, "C121@5936L5,122@5952L370,119@5802L520:FloatingActionButton.kt#uh7d8r");
                    if ((i115 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1249316354, i115, -1, "androidx.compose.material3.FloatingActionButton.<anonymous> (FloatingActionButton.kt:119)");
                        }
                        long j13 = j4;
                        TextStyle value = TypographyKt.getValue(ExtendedFabPrimaryTokens.INSTANCE.getLabelTextFont(), composer2, 6);
                        final Function2<? super Composer, ? super Integer, Unit> function3 = function2;
                        ProvideContentColorTextStyleKt.m2651ProvideContentColorTextStyle3JVO9M(j13, value, ComposableLambdaKt.rememberComposableLambda(-1771489750, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i20) {
                                ComposerKt.sourceInformation(composer3, "C123@5966L346:FloatingActionButton.kt#uh7d8r");
                                if ((i20 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1771489750, i20, -1, "androidx.compose.material3.FloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:123)");
                                    }
                                    Modifier modifierM749defaultMinSizeVpY3zN4 = SizeKt.m749defaultMinSizeVpY3zN4(Modifier.INSTANCE, FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM(), FabPrimaryTokens.INSTANCE.m2960getContainerHeightD9Ej5fM());
                                    Alignment center = Alignment.INSTANCE.getCenter();
                                    Function2<Composer, Integer, Unit> function4 = function3;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierM749defaultMinSizeVpY3zN4);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer3);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1494899604, "C131@6289L9:FloatingActionButton.kt#uh7d8r");
                                    function4.invoke(composer3, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composer2, 54), composer2, RendererCapabilities.DECODER_SUPPORT_MASK);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, i113 | (i114 & 7168) | (57344 & i114) | (i114 & 458752), 6, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            mutableInteractionSource5 = mutableInteractionSource3;
            modifier3 = modifier2;
            floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
            shape5 = shape4;
            j7 = j6;
            j8 = j12;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$3
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

                public final void invoke(Composer composer2, int i20) {
                    FloatingActionButtonKt.m1843FloatingActionButtonXz6DiA(function0, modifier3, shape5, j7, j8, floatingActionButtonElevation2, mutableInteractionSource5, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:112:0x014d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:113:0x014f  */
    /* JADX WARN: Code duplicated, block: B:114:0x0154  */
    /* JADX WARN: Code duplicated, block: B:117:0x015c  */
    /* JADX WARN: Code duplicated, block: B:118:0x0167  */
    /* JADX WARN: Code duplicated, block: B:121:0x016d  */
    /* JADX WARN: Code duplicated, block: B:122:0x017b  */
    /* JADX WARN: Code duplicated, block: B:125:0x0181  */
    /* JADX WARN: Code duplicated, block: B:128:0x0192  */
    /* JADX WARN: Code duplicated, block: B:129:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:131:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:132:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:136:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:139:0x0213  */
    /* JADX WARN: Code duplicated, block: B:143:0x0228  */
    /* JADX WARN: Code duplicated, block: B:145:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004d  */
    /* JADX WARN: Code duplicated, block: B:28:0x0051  */
    /* JADX WARN: Code duplicated, block: B:30:0x0059  */
    /* JADX WARN: Code duplicated, block: B:31:0x005c  */
    /* JADX WARN: Code duplicated, block: B:34:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x0068  */
    /* JADX WARN: Code duplicated, block: B:42:0x0077  */
    /* JADX WARN: Code duplicated, block: B:44:0x007b  */
    /* JADX WARN: Code duplicated, block: B:47:0x0081  */
    /* JADX WARN: Code duplicated, block: B:52:0x0090  */
    /* JADX WARN: Code duplicated, block: B:54:0x0094  */
    /* JADX WARN: Code duplicated, block: B:57:0x009b  */
    /* JADX WARN: Code duplicated, block: B:59:0x009f  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:62:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:69:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:82:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:90:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:94:0x0111  */
    /* JADX WARN: Code duplicated, block: B:96:0x011e  */
    /* JADX INFO: renamed from: SmallFloatingActionButton-X-z6DiA, reason: not valid java name */
    public static final void m1845SmallFloatingActionButtonXz6DiA(final Function0<Unit> function0, Modifier modifier, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        Function0<Unit> function1;
        int i3;
        Modifier.Companion companion;
        Shape shape2;
        long j3;
        long j4;
        FloatingActionButtonElevation floatingActionButtonElevationM1835elevationxZ9QkE;
        int i4;
        MutableInteractionSource mutableInteractionSource2;
        int i5;
        int i6;
        Shape smallShape;
        int i7;
        long containerColor;
        int i8;
        long j5;
        int i9;
        MutableInteractionSource mutableInteractionSource3;
        long j6;
        final Modifier modifier2;
        final Shape shape3;
        final long j7;
        final long j8;
        final FloatingActionButtonElevation floatingActionButtonElevation2;
        final MutableInteractionSource mutableInteractionSource4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i10;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(1444748300);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SmallFloatingActionButton)P(6,5,7,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3,4)170@8224L10,171@8293L14,172@8335L31,173@8444L11,177@8559L455:FloatingActionButton.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i & 6) == 0) {
                i3 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        int i12 = i2 & 2;
        if (i12 == 0) {
            if ((i & 48) == 0) {
                companion = modifier;
                i3 |= composerStartRestartGroup.changed(companion) ? 32 : 16;
            }
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if ((i2 & 4) == 0) {
                    shape2 = shape;
                    int i13 = composerStartRestartGroup.changed(shape2) ? 256 : 128;
                    i3 |= i13;
                } else {
                    shape2 = shape;
                }
                i3 |= i13;
            } else {
                shape2 = shape;
            }
            if ((i & 3072) == 0) {
                j3 = j;
                if ((i2 & 8) == 0 || !composerStartRestartGroup.changed(j3)) {
                    i11 = 1024;
                } else {
                    i11 = 2048;
                }
                i3 |= i11;
            } else {
                j3 = j;
            }
            if ((i & 24576) == 0) {
                j4 = j2;
                if ((i2 & 16) == 0 || !composerStartRestartGroup.changed(j4)) {
                    i10 = 8192;
                } else {
                    i10 = 16384;
                }
                i3 |= i10;
            } else {
                j4 = j2;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                    int i14 = composerStartRestartGroup.changed(floatingActionButtonElevationM1835elevationxZ9QkE) ? 131072 : 65536;
                    i3 |= i14;
                } else {
                    floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                }
                i3 |= i14;
            } else {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
            }
            i4 = i2 & 64;
            if (i4 != 0) {
                if ((1572864 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i5 = 1048576;
                    } else {
                        i5 = 524288;
                    }
                    i3 |= i5;
                }
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i6 = 8388608;
                        } else {
                            i6 = 4194304;
                        }
                        i3 |= i6;
                    }
                    if ((i3 & 4793491) == 4793490 || !composerStartRestartGroup.getSkipping()) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = companion;
                            }
                            if ((i2 & 4) != 0) {
                                i3 &= -897;
                                smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                            } else {
                                smallShape = shape2;
                            }
                            if ((i2 & 8) != 0) {
                                i7 = i3 & (-7169);
                                containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                i7 = i3;
                                containerColor = j3;
                            }
                            if ((i2 & 16) != 0) {
                                long jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                                i7 &= -57345;
                                j4 = jM1647contentColorForek8zF_U;
                            }
                            i8 = i7;
                            if ((i2 & 32) != 0) {
                                j5 = containerColor;
                                floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                                i3 = i8 & (-458753);
                            } else {
                                j5 = containerColor;
                                i3 = i8;
                            }
                            if (i4 != 0) {
                                i9 = 1444748300;
                                mutableInteractionSource3 = null;
                            } else {
                                i9 = 1444748300;
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            j6 = j4;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 4) != 0) {
                                i3 &= -897;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                            }
                            floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevationM1835elevationxZ9QkE;
                            j5 = j3;
                            j6 = j4;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            i9 = 1444748300;
                            smallShape = shape2;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
                        }
                        m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = companion;
                        shape3 = smallShape;
                        j7 = j5;
                        j8 = j6;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        mutableInteractionSource4 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier2 = companion;
                        shape3 = shape2;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j7 = j3;
                        j8 = j4;
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$SmallFloatingActionButton$1
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

                            public final void invoke(Composer composer2, int i15) {
                                FloatingActionButtonKt.m1845SmallFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i3 & 4793491) == 4793490) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                        } else {
                            smallShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U2 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U2;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = 1444748300;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = 1444748300;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                        } else {
                            smallShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U3 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U3;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = 1444748300;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = 1444748300;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
                    }
                    m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = companion;
                    shape3 = smallShape;
                    j7 = j5;
                    j8 = j6;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    mutableInteractionSource4 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                        } else {
                            smallShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U4 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U4;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = 1444748300;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = 1444748300;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                        } else {
                            smallShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U5 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U5;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = 1444748300;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = 1444748300;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
                    }
                    m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = companion;
                    shape3 = smallShape;
                    j7 = j5;
                    j8 = j6;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$SmallFloatingActionButton$1
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

                        public final void invoke(Composer composer2, int i15) {
                            FloatingActionButtonKt.m1845SmallFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i6 = 8388608;
                    } else {
                        i6 = 4194304;
                    }
                    i3 |= i6;
                }
                if ((i3 & 4793491) == 4793490) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                        } else {
                            smallShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U6 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U6;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = 1444748300;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = 1444748300;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                        } else {
                            smallShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U7 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U7;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = 1444748300;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = 1444748300;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
                    }
                    m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = companion;
                    shape3 = smallShape;
                    j7 = j5;
                    j8 = j6;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    mutableInteractionSource4 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                        } else {
                            smallShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U8 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U8;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = 1444748300;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = 1444748300;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                        } else {
                            smallShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U9 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U9;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = 1444748300;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = 1444748300;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
                    }
                    m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = companion;
                    shape3 = smallShape;
                    j7 = j5;
                    j8 = j6;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$SmallFloatingActionButton$1
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

                        public final void invoke(Composer composer2, int i15) {
                            FloatingActionButtonKt.m1845SmallFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i3 & 4793491) == 4793490) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                    } else {
                        smallShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U10 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U10;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = 1444748300;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = 1444748300;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                    } else {
                        smallShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U11 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U11;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = 1444748300;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = 1444748300;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
                }
                m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = companion;
                shape3 = smallShape;
                j7 = j5;
                j8 = j6;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                mutableInteractionSource4 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                    } else {
                        smallShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U12 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U12;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = 1444748300;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = 1444748300;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                    } else {
                        smallShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U13 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U13;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = 1444748300;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = 1444748300;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
                }
                m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = companion;
                shape3 = smallShape;
                j7 = j5;
                j8 = j6;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$SmallFloatingActionButton$1
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

                    public final void invoke(Composer composer2, int i15) {
                        FloatingActionButtonKt.m1845SmallFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        companion = modifier;
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i2 & 4) == 0) {
                shape2 = shape;
                if (composerStartRestartGroup.changed(shape2)) {
                }
                i3 |= i13;
            } else {
                shape2 = shape;
            }
            i3 |= i13;
        } else {
            shape2 = shape;
        }
        if ((i & 3072) == 0) {
            j3 = j;
            if ((i2 & 8) == 0) {
                i11 = 1024;
            } else {
                i11 = 1024;
            }
            i3 |= i11;
        } else {
            j3 = j;
        }
        if ((i & 24576) == 0) {
            j4 = j2;
            if ((i2 & 16) == 0) {
                i10 = 8192;
            } else {
                i10 = 8192;
            }
            i3 |= i10;
        } else {
            j4 = j2;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                if (composerStartRestartGroup.changed(floatingActionButtonElevationM1835elevationxZ9QkE)) {
                }
                i3 |= i14;
            } else {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
            }
            i3 |= i14;
        } else {
            floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
        }
        i4 = i2 & 64;
        if (i4 != 0) {
            if ((1572864 & i) == 0) {
                mutableInteractionSource2 = mutableInteractionSource;
                if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                    i5 = 1048576;
                } else {
                    i5 = 524288;
                }
                i3 |= i5;
            }
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i6 = 8388608;
                    } else {
                        i6 = 4194304;
                    }
                    i3 |= i6;
                }
                if ((i3 & 4793491) == 4793490) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                        } else {
                            smallShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U14 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U14;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = 1444748300;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = 1444748300;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                        } else {
                            smallShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U15 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U15;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = 1444748300;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = 1444748300;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
                    }
                    m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = companion;
                    shape3 = smallShape;
                    j7 = j5;
                    j8 = j6;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    mutableInteractionSource4 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                        } else {
                            smallShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U16 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U16;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = 1444748300;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = 1444748300;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                        } else {
                            smallShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U17 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U17;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = 1444748300;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = 1444748300;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
                    }
                    m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = companion;
                    shape3 = smallShape;
                    j7 = j5;
                    j8 = j6;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$SmallFloatingActionButton$1
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

                        public final void invoke(Composer composer2, int i15) {
                            FloatingActionButtonKt.m1845SmallFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i3 & 4793491) == 4793490) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                    } else {
                        smallShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U18 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U18;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = 1444748300;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = 1444748300;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                    } else {
                        smallShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U19 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U19;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = 1444748300;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = 1444748300;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
                }
                m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = companion;
                shape3 = smallShape;
                j7 = j5;
                j8 = j6;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                mutableInteractionSource4 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                    } else {
                        smallShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U110 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U110;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = 1444748300;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = 1444748300;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                    } else {
                        smallShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U111 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U111;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = 1444748300;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = 1444748300;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
                }
                m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = companion;
                shape3 = smallShape;
                j7 = j5;
                j8 = j6;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$SmallFloatingActionButton$1
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

                    public final void invoke(Composer composer2, int i15) {
                        FloatingActionButtonKt.m1845SmallFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 1572864;
        mutableInteractionSource2 = mutableInteractionSource;
        if ((i2 & 128) != 0) {
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i6 = 8388608;
                } else {
                    i6 = 4194304;
                }
                i3 |= i6;
            }
            if ((i3 & 4793491) == 4793490) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                    } else {
                        smallShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U112 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U112;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = 1444748300;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = 1444748300;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                    } else {
                        smallShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U113 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U113;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = 1444748300;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = 1444748300;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
                }
                m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = companion;
                shape3 = smallShape;
                j7 = j5;
                j8 = j6;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                mutableInteractionSource4 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                    } else {
                        smallShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U114 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U114;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = 1444748300;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = 1444748300;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                    } else {
                        smallShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U115 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U115;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = 1444748300;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = 1444748300;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
                }
                m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = companion;
                shape3 = smallShape;
                j7 = j5;
                j8 = j6;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$SmallFloatingActionButton$1
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

                    public final void invoke(Composer composer2, int i15) {
                        FloatingActionButtonKt.m1845SmallFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 12582912;
        if ((i3 & 4793491) == 4793490) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = companion;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                } else {
                    smallShape = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U116 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U116;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i3 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    i3 = i8;
                }
                if (i4 != 0) {
                    i9 = 1444748300;
                    mutableInteractionSource3 = null;
                } else {
                    i9 = 1444748300;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                j6 = j4;
            } else {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = companion;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                } else {
                    smallShape = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U117 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U117;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i3 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    i3 = i8;
                }
                if (i4 != 0) {
                    i9 = 1444748300;
                    mutableInteractionSource3 = null;
                } else {
                    i9 = 1444748300;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                j6 = j4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
            }
            m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = companion;
            shape3 = smallShape;
            j7 = j5;
            j8 = j6;
            floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
            mutableInteractionSource4 = mutableInteractionSource3;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = companion;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                } else {
                    smallShape = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U118 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U118;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i3 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    i3 = i8;
                }
                if (i4 != 0) {
                    i9 = 1444748300;
                    mutableInteractionSource3 = null;
                } else {
                    i9 = 1444748300;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                j6 = j4;
            } else {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = companion;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    smallShape = FloatingActionButtonDefaults.INSTANCE.getSmallShape(composerStartRestartGroup, 6);
                } else {
                    smallShape = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U119 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U119;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i3 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    i3 = i8;
                }
                if (i4 != 0) {
                    i9 = 1444748300;
                    mutableInteractionSource3 = null;
                } else {
                    i9 = 1444748300;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                j6 = j4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:176)");
            }
            m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimarySmallTokens.INSTANCE.m2950getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2949getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), smallShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = companion;
            shape3 = smallShape;
            j7 = j5;
            j8 = j6;
            floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
            mutableInteractionSource4 = mutableInteractionSource3;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$SmallFloatingActionButton$1
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

                public final void invoke(Composer composer2, int i15) {
                    FloatingActionButtonKt.m1845SmallFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:112:0x014d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:113:0x014f  */
    /* JADX WARN: Code duplicated, block: B:114:0x0154  */
    /* JADX WARN: Code duplicated, block: B:117:0x015c  */
    /* JADX WARN: Code duplicated, block: B:118:0x0167  */
    /* JADX WARN: Code duplicated, block: B:121:0x016d  */
    /* JADX WARN: Code duplicated, block: B:122:0x017b  */
    /* JADX WARN: Code duplicated, block: B:125:0x0181  */
    /* JADX WARN: Code duplicated, block: B:128:0x0192  */
    /* JADX WARN: Code duplicated, block: B:129:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:131:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:132:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:136:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:139:0x0213  */
    /* JADX WARN: Code duplicated, block: B:143:0x0228  */
    /* JADX WARN: Code duplicated, block: B:145:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004d  */
    /* JADX WARN: Code duplicated, block: B:28:0x0051  */
    /* JADX WARN: Code duplicated, block: B:30:0x0059  */
    /* JADX WARN: Code duplicated, block: B:31:0x005c  */
    /* JADX WARN: Code duplicated, block: B:34:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x0068  */
    /* JADX WARN: Code duplicated, block: B:42:0x0077  */
    /* JADX WARN: Code duplicated, block: B:44:0x007b  */
    /* JADX WARN: Code duplicated, block: B:47:0x0081  */
    /* JADX WARN: Code duplicated, block: B:52:0x0090  */
    /* JADX WARN: Code duplicated, block: B:54:0x0094  */
    /* JADX WARN: Code duplicated, block: B:57:0x009b  */
    /* JADX WARN: Code duplicated, block: B:59:0x009f  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:62:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:69:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:82:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:90:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:94:0x0111  */
    /* JADX WARN: Code duplicated, block: B:96:0x011e  */
    /* JADX INFO: renamed from: LargeFloatingActionButton-X-z6DiA, reason: not valid java name */
    public static final void m1844LargeFloatingActionButtonXz6DiA(final Function0<Unit> function0, Modifier modifier, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        Function0<Unit> function1;
        int i3;
        Modifier.Companion companion;
        Shape shape2;
        long j3;
        long j4;
        FloatingActionButtonElevation floatingActionButtonElevationM1835elevationxZ9QkE;
        int i4;
        MutableInteractionSource mutableInteractionSource2;
        int i5;
        int i6;
        Shape largeShape;
        int i7;
        long containerColor;
        int i8;
        long j5;
        int i9;
        MutableInteractionSource mutableInteractionSource3;
        long j6;
        final Modifier modifier2;
        final Shape shape3;
        final long j7;
        final long j8;
        final FloatingActionButtonElevation floatingActionButtonElevation2;
        final MutableInteractionSource mutableInteractionSource4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i10;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1650866856);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LargeFloatingActionButton)P(6,5,7,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3,4)226@10910L10,227@10979L14,228@11021L31,229@11130L11,233@11245L455:FloatingActionButton.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i & 6) == 0) {
                i3 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        int i12 = i2 & 2;
        if (i12 == 0) {
            if ((i & 48) == 0) {
                companion = modifier;
                i3 |= composerStartRestartGroup.changed(companion) ? 32 : 16;
            }
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if ((i2 & 4) == 0) {
                    shape2 = shape;
                    int i13 = composerStartRestartGroup.changed(shape2) ? 256 : 128;
                    i3 |= i13;
                } else {
                    shape2 = shape;
                }
                i3 |= i13;
            } else {
                shape2 = shape;
            }
            if ((i & 3072) == 0) {
                j3 = j;
                if ((i2 & 8) == 0 || !composerStartRestartGroup.changed(j3)) {
                    i11 = 1024;
                } else {
                    i11 = 2048;
                }
                i3 |= i11;
            } else {
                j3 = j;
            }
            if ((i & 24576) == 0) {
                j4 = j2;
                if ((i2 & 16) == 0 || !composerStartRestartGroup.changed(j4)) {
                    i10 = 8192;
                } else {
                    i10 = 16384;
                }
                i3 |= i10;
            } else {
                j4 = j2;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                    int i14 = composerStartRestartGroup.changed(floatingActionButtonElevationM1835elevationxZ9QkE) ? 131072 : 65536;
                    i3 |= i14;
                } else {
                    floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                }
                i3 |= i14;
            } else {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
            }
            i4 = i2 & 64;
            if (i4 != 0) {
                if ((1572864 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i5 = 1048576;
                    } else {
                        i5 = 524288;
                    }
                    i3 |= i5;
                }
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i6 = 8388608;
                        } else {
                            i6 = 4194304;
                        }
                        i3 |= i6;
                    }
                    if ((i3 & 4793491) == 4793490 || !composerStartRestartGroup.getSkipping()) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = companion;
                            }
                            if ((i2 & 4) != 0) {
                                i3 &= -897;
                                largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                            } else {
                                largeShape = shape2;
                            }
                            if ((i2 & 8) != 0) {
                                i7 = i3 & (-7169);
                                containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            } else {
                                i7 = i3;
                                containerColor = j3;
                            }
                            if ((i2 & 16) != 0) {
                                long jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                                i7 &= -57345;
                                j4 = jM1647contentColorForek8zF_U;
                            }
                            i8 = i7;
                            if ((i2 & 32) != 0) {
                                j5 = containerColor;
                                floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                                i3 = i8 & (-458753);
                            } else {
                                j5 = containerColor;
                                i3 = i8;
                            }
                            if (i4 != 0) {
                                i9 = -1650866856;
                                mutableInteractionSource3 = null;
                            } else {
                                i9 = -1650866856;
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            j6 = j4;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 4) != 0) {
                                i3 &= -897;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                            }
                            floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevationM1835elevationxZ9QkE;
                            j5 = j3;
                            j6 = j4;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            i9 = -1650866856;
                            largeShape = shape2;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
                        }
                        m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = companion;
                        shape3 = largeShape;
                        j7 = j5;
                        j8 = j6;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        mutableInteractionSource4 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier2 = companion;
                        shape3 = shape2;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j7 = j3;
                        j8 = j4;
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$LargeFloatingActionButton$1
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

                            public final void invoke(Composer composer2, int i15) {
                                FloatingActionButtonKt.m1844LargeFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((i3 & 4793491) == 4793490) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                        } else {
                            largeShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U2 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U2;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = -1650866856;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = -1650866856;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                        } else {
                            largeShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U3 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U3;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = -1650866856;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = -1650866856;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
                    }
                    m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = companion;
                    shape3 = largeShape;
                    j7 = j5;
                    j8 = j6;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    mutableInteractionSource4 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                        } else {
                            largeShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U4 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U4;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = -1650866856;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = -1650866856;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                        } else {
                            largeShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U5 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U5;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = -1650866856;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = -1650866856;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
                    }
                    m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = companion;
                    shape3 = largeShape;
                    j7 = j5;
                    j8 = j6;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$LargeFloatingActionButton$1
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

                        public final void invoke(Composer composer2, int i15) {
                            FloatingActionButtonKt.m1844LargeFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i6 = 8388608;
                    } else {
                        i6 = 4194304;
                    }
                    i3 |= i6;
                }
                if ((i3 & 4793491) == 4793490) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                        } else {
                            largeShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U6 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U6;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = -1650866856;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = -1650866856;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                        } else {
                            largeShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U7 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U7;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = -1650866856;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = -1650866856;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
                    }
                    m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = companion;
                    shape3 = largeShape;
                    j7 = j5;
                    j8 = j6;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    mutableInteractionSource4 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                        } else {
                            largeShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U8 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U8;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = -1650866856;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = -1650866856;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                        } else {
                            largeShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U9 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U9;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = -1650866856;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = -1650866856;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
                    }
                    m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = companion;
                    shape3 = largeShape;
                    j7 = j5;
                    j8 = j6;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$LargeFloatingActionButton$1
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

                        public final void invoke(Composer composer2, int i15) {
                            FloatingActionButtonKt.m1844LargeFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i3 & 4793491) == 4793490) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                    } else {
                        largeShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U10 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U10;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = -1650866856;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = -1650866856;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                    } else {
                        largeShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U11 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U11;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = -1650866856;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = -1650866856;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
                }
                m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = companion;
                shape3 = largeShape;
                j7 = j5;
                j8 = j6;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                mutableInteractionSource4 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                    } else {
                        largeShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U12 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U12;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = -1650866856;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = -1650866856;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                    } else {
                        largeShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U13 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U13;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = -1650866856;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = -1650866856;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
                }
                m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = companion;
                shape3 = largeShape;
                j7 = j5;
                j8 = j6;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$LargeFloatingActionButton$1
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

                    public final void invoke(Composer composer2, int i15) {
                        FloatingActionButtonKt.m1844LargeFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        companion = modifier;
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i2 & 4) == 0) {
                shape2 = shape;
                if (composerStartRestartGroup.changed(shape2)) {
                }
                i3 |= i13;
            } else {
                shape2 = shape;
            }
            i3 |= i13;
        } else {
            shape2 = shape;
        }
        if ((i & 3072) == 0) {
            j3 = j;
            if ((i2 & 8) == 0) {
                i11 = 1024;
            } else {
                i11 = 1024;
            }
            i3 |= i11;
        } else {
            j3 = j;
        }
        if ((i & 24576) == 0) {
            j4 = j2;
            if ((i2 & 16) == 0) {
                i10 = 8192;
            } else {
                i10 = 8192;
            }
            i3 |= i10;
        } else {
            j4 = j2;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                if (composerStartRestartGroup.changed(floatingActionButtonElevationM1835elevationxZ9QkE)) {
                }
                i3 |= i14;
            } else {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
            }
            i3 |= i14;
        } else {
            floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
        }
        i4 = i2 & 64;
        if (i4 != 0) {
            if ((1572864 & i) == 0) {
                mutableInteractionSource2 = mutableInteractionSource;
                if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                    i5 = 1048576;
                } else {
                    i5 = 524288;
                }
                i3 |= i5;
            }
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i6 = 8388608;
                    } else {
                        i6 = 4194304;
                    }
                    i3 |= i6;
                }
                if ((i3 & 4793491) == 4793490) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                        } else {
                            largeShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U14 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U14;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = -1650866856;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = -1650866856;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                        } else {
                            largeShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U15 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U15;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = -1650866856;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = -1650866856;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
                    }
                    m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = companion;
                    shape3 = largeShape;
                    j7 = j5;
                    j8 = j6;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    mutableInteractionSource4 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                        } else {
                            largeShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U16 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U16;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = -1650866856;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = -1650866856;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = companion;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                        } else {
                            largeShape = shape2;
                        }
                        if ((i2 & 8) != 0) {
                            i7 = i3 & (-7169);
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        } else {
                            i7 = i3;
                            containerColor = j3;
                        }
                        if ((i2 & 16) != 0) {
                            long jM1647contentColorForek8zF_U17 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                            i7 &= -57345;
                            j4 = jM1647contentColorForek8zF_U17;
                        }
                        i8 = i7;
                        if ((i2 & 32) != 0) {
                            j5 = containerColor;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            i3 = i8 & (-458753);
                        } else {
                            j5 = containerColor;
                            i3 = i8;
                        }
                        if (i4 != 0) {
                            i9 = -1650866856;
                            mutableInteractionSource3 = null;
                        } else {
                            i9 = -1650866856;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        j6 = j4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
                    }
                    m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = companion;
                    shape3 = largeShape;
                    j7 = j5;
                    j8 = j6;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$LargeFloatingActionButton$1
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

                        public final void invoke(Composer composer2, int i15) {
                            FloatingActionButtonKt.m1844LargeFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((i3 & 4793491) == 4793490) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                    } else {
                        largeShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U18 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U18;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = -1650866856;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = -1650866856;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                    } else {
                        largeShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U19 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U19;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = -1650866856;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = -1650866856;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
                }
                m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = companion;
                shape3 = largeShape;
                j7 = j5;
                j8 = j6;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                mutableInteractionSource4 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                    } else {
                        largeShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U110 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U110;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = -1650866856;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = -1650866856;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                    } else {
                        largeShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U111 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U111;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = -1650866856;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = -1650866856;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
                }
                m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = companion;
                shape3 = largeShape;
                j7 = j5;
                j8 = j6;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$LargeFloatingActionButton$1
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

                    public final void invoke(Composer composer2, int i15) {
                        FloatingActionButtonKt.m1844LargeFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 1572864;
        mutableInteractionSource2 = mutableInteractionSource;
        if ((i2 & 128) != 0) {
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i6 = 8388608;
                } else {
                    i6 = 4194304;
                }
                i3 |= i6;
            }
            if ((i3 & 4793491) == 4793490) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                    } else {
                        largeShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U112 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U112;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = -1650866856;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = -1650866856;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                    } else {
                        largeShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U113 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U113;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = -1650866856;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = -1650866856;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
                }
                m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = companion;
                shape3 = largeShape;
                j7 = j5;
                j8 = j6;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                mutableInteractionSource4 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                    } else {
                        largeShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U114 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U114;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = -1650866856;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = -1650866856;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = companion;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                    } else {
                        largeShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U115 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U115;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        i9 = -1650866856;
                        mutableInteractionSource3 = null;
                    } else {
                        i9 = -1650866856;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    j6 = j4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
                }
                m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = companion;
                shape3 = largeShape;
                j7 = j5;
                j8 = j6;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$LargeFloatingActionButton$1
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

                    public final void invoke(Composer composer2, int i15) {
                        FloatingActionButtonKt.m1844LargeFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 12582912;
        if ((i3 & 4793491) == 4793490) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = companion;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                } else {
                    largeShape = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U116 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U116;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i3 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    i3 = i8;
                }
                if (i4 != 0) {
                    i9 = -1650866856;
                    mutableInteractionSource3 = null;
                } else {
                    i9 = -1650866856;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                j6 = j4;
            } else {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = companion;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                } else {
                    largeShape = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U117 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U117;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i3 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    i3 = i8;
                }
                if (i4 != 0) {
                    i9 = -1650866856;
                    mutableInteractionSource3 = null;
                } else {
                    i9 = -1650866856;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                j6 = j4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
            }
            m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = companion;
            shape3 = largeShape;
            j7 = j5;
            j8 = j6;
            floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
            mutableInteractionSource4 = mutableInteractionSource3;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = companion;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                } else {
                    largeShape = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U118 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U118;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i3 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    i3 = i8;
                }
                if (i4 != 0) {
                    i9 = -1650866856;
                    mutableInteractionSource3 = null;
                } else {
                    i9 = -1650866856;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                j6 = j4;
            } else {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = companion;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    largeShape = FloatingActionButtonDefaults.INSTANCE.getLargeShape(composerStartRestartGroup, 6);
                } else {
                    largeShape = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U119 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U119;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i3 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    i3 = i8;
                }
                if (i4 != 0) {
                    i9 = -1650866856;
                    mutableInteractionSource3 = null;
                } else {
                    i9 = -1650866856;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                j6 = j4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:232)");
            }
            m1843FloatingActionButtonXz6DiA(function1, SizeKt.m769sizeInqDBjuR0$default(companion, FabPrimaryLargeTokens.INSTANCE.m2939getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2938getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), largeShape, j5, j6, floatingActionButtonElevationM1835elevationxZ9QkE, mutableInteractionSource3, function2, composerStartRestartGroup, i3 & 33554318, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = companion;
            shape3 = largeShape;
            j7 = j5;
            j8 = j6;
            floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
            mutableInteractionSource4 = mutableInteractionSource3;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$LargeFloatingActionButton$1
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

                public final void invoke(Composer composer2, int i15) {
                    FloatingActionButtonKt.m1844LargeFloatingActionButtonXz6DiA(function0, modifier2, shape3, j7, j8, floatingActionButtonElevation2, mutableInteractionSource4, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:110:0x014d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:111:0x014f  */
    /* JADX WARN: Code duplicated, block: B:112:0x0154  */
    /* JADX WARN: Code duplicated, block: B:115:0x015c  */
    /* JADX WARN: Code duplicated, block: B:116:0x0167  */
    /* JADX WARN: Code duplicated, block: B:119:0x016d  */
    /* JADX WARN: Code duplicated, block: B:120:0x017b  */
    /* JADX WARN: Code duplicated, block: B:123:0x0181  */
    /* JADX WARN: Code duplicated, block: B:126:0x0192  */
    /* JADX WARN: Code duplicated, block: B:127:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:129:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:130:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:133:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:136:0x0220  */
    /* JADX WARN: Code duplicated, block: B:140:0x0235  */
    /* JADX WARN: Code duplicated, block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004f  */
    /* JADX WARN: Code duplicated, block: B:28:0x0053  */
    /* JADX WARN: Code duplicated, block: B:30:0x005b  */
    /* JADX WARN: Code duplicated, block: B:31:0x005e  */
    /* JADX WARN: Code duplicated, block: B:34:0x0064  */
    /* JADX WARN: Code duplicated, block: B:37:0x006a  */
    /* JADX WARN: Code duplicated, block: B:42:0x0079  */
    /* JADX WARN: Code duplicated, block: B:44:0x007d  */
    /* JADX WARN: Code duplicated, block: B:47:0x0083  */
    /* JADX WARN: Code duplicated, block: B:52:0x0092  */
    /* JADX WARN: Code duplicated, block: B:54:0x0096  */
    /* JADX WARN: Code duplicated, block: B:57:0x009d  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:68:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:73:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:79:0x00db  */
    /* JADX WARN: Code duplicated, block: B:81:0x00df  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:92:0x010e  */
    /* JADX WARN: Code duplicated, block: B:94:0x011b  */
    /* JADX INFO: renamed from: ExtendedFloatingActionButton-X-z6DiA, reason: not valid java name */
    public static final void m1842ExtendedFloatingActionButtonXz6DiA(final Function0<Unit> function0, Modifier modifier, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        Function0<Unit> function1;
        int i3;
        Modifier modifier2;
        Shape shape2;
        long j3;
        long j4;
        FloatingActionButtonElevation floatingActionButtonElevationM1835elevationxZ9QkE;
        int i4;
        int i5;
        int i6;
        Modifier.Companion companion;
        Shape extendedFabShape;
        int i7;
        long containerColor;
        int i8;
        long j5;
        Shape shape3;
        long j6;
        Modifier modifier3;
        FloatingActionButtonElevation floatingActionButtonElevation2;
        long j7;
        int i9;
        MutableInteractionSource mutableInteractionSource2;
        final Modifier modifier4;
        final Shape shape4;
        final long j8;
        final long j9;
        final FloatingActionButtonElevation floatingActionButtonElevation3;
        final MutableInteractionSource mutableInteractionSource3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i10;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-326283107);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ExtendedFloatingActionButton)P(6,5,7,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3,4)285@13734L16,286@13809L14,287@13851L31,288@13960L11,300@14347L335,292@14084L598:FloatingActionButton.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i & 6) == 0) {
                i3 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        int i12 = i2 & 2;
        if (i12 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if ((i2 & 4) == 0) {
                    shape2 = shape;
                    int i13 = composerStartRestartGroup.changed(shape2) ? 256 : 128;
                    i3 |= i13;
                } else {
                    shape2 = shape;
                }
                i3 |= i13;
            } else {
                shape2 = shape;
            }
            if ((i & 3072) == 0) {
                j3 = j;
                if ((i2 & 8) == 0 || !composerStartRestartGroup.changed(j3)) {
                    i11 = 1024;
                } else {
                    i11 = 2048;
                }
                i3 |= i11;
            } else {
                j3 = j;
            }
            if ((i & 24576) == 0) {
                j4 = j2;
                if ((i2 & 16) == 0 || !composerStartRestartGroup.changed(j4)) {
                    i10 = 8192;
                } else {
                    i10 = 16384;
                }
                i3 |= i10;
            } else {
                j4 = j2;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                    int i14 = composerStartRestartGroup.changed(floatingActionButtonElevationM1835elevationxZ9QkE) ? 131072 : 65536;
                    i3 |= i14;
                } else {
                    floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                }
                i3 |= i14;
            } else {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
            }
            i4 = i2 & 64;
            if (i4 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i5 = 1048576;
                } else {
                    i5 = 524288;
                }
                i3 |= i5;
            }
            if ((i2 & 128) != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i6 = 8388608;
                } else {
                    i6 = 4194304;
                }
                i3 |= i6;
            }
            if ((4793491 & i3) == 4793490 || !composerStartRestartGroup.getSkipping()) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                    } else {
                        extendedFabShape = shape2;
                    }
                    if ((i2 & 8) != 0) {
                        i7 = i3 & (-7169);
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    } else {
                        i7 = i3;
                        containerColor = j3;
                    }
                    if ((i2 & 16) != 0) {
                        long jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                        i7 &= -57345;
                        j4 = jM1647contentColorForek8zF_U;
                    }
                    i8 = i7;
                    if ((i2 & 32) != 0) {
                        j5 = containerColor;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        i3 = i8 & (-458753);
                    } else {
                        j5 = containerColor;
                        i3 = i8;
                    }
                    if (i4 != 0) {
                        shape3 = extendedFabShape;
                        j6 = j4;
                        modifier3 = companion;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j7 = j5;
                        mutableInteractionSource2 = null;
                        i9 = -326283107;
                    } else {
                        shape3 = extendedFabShape;
                        j6 = j4;
                        modifier3 = companion;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j7 = j5;
                        i9 = -326283107;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                    }
                    mutableInteractionSource2 = mutableInteractionSource;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j6 = j4;
                    i9 = -326283107;
                    modifier3 = modifier2;
                    shape3 = shape2;
                    j7 = j3;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:291)");
                }
                m1843FloatingActionButtonXz6DiA(function1, modifier3, shape3, j7, j6, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(398457247, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$1
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

                    public final void invoke(Composer composer2, int i15) {
                        ComposerKt.sourceInformation(composer2, "C301@14357L319:FloatingActionButton.kt#uh7d8r");
                        if ((i15 & 3) != 2 || !composer2.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(398457247, i15, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:301)");
                            }
                            Modifier modifierM722paddingVpY3zN4$default = PaddingKt.m722paddingVpY3zN4$default(SizeKt.m769sizeInqDBjuR0$default(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabMinimumWidth, 0.0f, 0.0f, 0.0f, 14, null), FloatingActionButtonKt.ExtendedFabTextPadding, 0.0f, 2, null);
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function4 = function3;
                            ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer2, 54);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM722paddingVpY3zN4$default);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                            function4.invoke(RowScopeInstance.INSTANCE, composer2, 6);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer2.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, 12582912 | (i3 & 14) | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (i3 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                shape4 = shape3;
                j8 = j7;
                j9 = j6;
                floatingActionButtonElevation3 = floatingActionButtonElevation2;
                mutableInteractionSource3 = mutableInteractionSource2;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                mutableInteractionSource3 = mutableInteractionSource;
                modifier4 = modifier2;
                shape4 = shape2;
                floatingActionButtonElevation3 = floatingActionButtonElevationM1835elevationxZ9QkE;
                j8 = j3;
                j9 = j4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$2
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

                    public final void invoke(Composer composer2, int i15) {
                        FloatingActionButtonKt.m1842ExtendedFloatingActionButtonXz6DiA(function0, modifier4, shape4, j8, j9, floatingActionButtonElevation3, mutableInteractionSource3, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i2 & 4) == 0) {
                shape2 = shape;
                if (composerStartRestartGroup.changed(shape2)) {
                }
                i3 |= i13;
            } else {
                shape2 = shape;
            }
            i3 |= i13;
        } else {
            shape2 = shape;
        }
        if ((i & 3072) == 0) {
            j3 = j;
            if ((i2 & 8) == 0) {
                i11 = 1024;
            } else {
                i11 = 1024;
            }
            i3 |= i11;
        } else {
            j3 = j;
        }
        if ((i & 24576) == 0) {
            j4 = j2;
            if ((i2 & 16) == 0) {
                i10 = 8192;
            } else {
                i10 = 8192;
            }
            i3 |= i10;
        } else {
            j4 = j2;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                if (composerStartRestartGroup.changed(floatingActionButtonElevationM1835elevationxZ9QkE)) {
                }
                i3 |= i14;
            } else {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
            }
            i3 |= i14;
        } else {
            floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
        }
        i4 = i2 & 64;
        if (i4 != 0) {
            i3 |= 1572864;
        } else if ((i & 1572864) == 0) {
            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                i5 = 1048576;
            } else {
                i5 = 524288;
            }
            i3 |= i5;
        }
        if ((i2 & 128) != 0) {
            i3 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i6 = 8388608;
            } else {
                i6 = 4194304;
            }
            i3 |= i6;
        }
        if ((4793491 & i3) == 4793490) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                } else {
                    extendedFabShape = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U2 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U2;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i3 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    i3 = i8;
                }
                if (i4 != 0) {
                    shape3 = extendedFabShape;
                    j6 = j4;
                    modifier3 = companion;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j7 = j5;
                    mutableInteractionSource2 = null;
                    i9 = -326283107;
                } else {
                    shape3 = extendedFabShape;
                    j6 = j4;
                    modifier3 = companion;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j7 = j5;
                    i9 = -326283107;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
            } else {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                } else {
                    extendedFabShape = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U3 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U3;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i3 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    i3 = i8;
                }
                if (i4 != 0) {
                    shape3 = extendedFabShape;
                    j6 = j4;
                    modifier3 = companion;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j7 = j5;
                    mutableInteractionSource2 = null;
                    i9 = -326283107;
                } else {
                    shape3 = extendedFabShape;
                    j6 = j4;
                    modifier3 = companion;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j7 = j5;
                    i9 = -326283107;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:291)");
            }
            m1843FloatingActionButtonXz6DiA(function1, modifier3, shape3, j7, j6, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(398457247, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$1
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

                public final void invoke(Composer composer2, int i15) {
                    ComposerKt.sourceInformation(composer2, "C301@14357L319:FloatingActionButton.kt#uh7d8r");
                    if ((i15 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(398457247, i15, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:301)");
                        }
                        Modifier modifierM722paddingVpY3zN4$default = PaddingKt.m722paddingVpY3zN4$default(SizeKt.m769sizeInqDBjuR0$default(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabMinimumWidth, 0.0f, 0.0f, 0.0f, 14, null), FloatingActionButtonKt.ExtendedFabTextPadding, 0.0f, 2, null);
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                        Function3<RowScope, Composer, Integer, Unit> function4 = function3;
                        ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer2, 54);
                        ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM722paddingVpY3zN4$default);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                        function4.invoke(RowScopeInstance.INSTANCE, composer2, 6);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 12582912 | (i3 & 14) | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (i3 & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            shape4 = shape3;
            j8 = j7;
            j9 = j6;
            floatingActionButtonElevation3 = floatingActionButtonElevation2;
            mutableInteractionSource3 = mutableInteractionSource2;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                } else {
                    extendedFabShape = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U4 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U4;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i3 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    i3 = i8;
                }
                if (i4 != 0) {
                    shape3 = extendedFabShape;
                    j6 = j4;
                    modifier3 = companion;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j7 = j5;
                    mutableInteractionSource2 = null;
                    i9 = -326283107;
                } else {
                    shape3 = extendedFabShape;
                    j6 = j4;
                    modifier3 = companion;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j7 = j5;
                    i9 = -326283107;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
            } else {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                } else {
                    extendedFabShape = shape2;
                }
                if ((i2 & 8) != 0) {
                    i7 = i3 & (-7169);
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                } else {
                    i7 = i3;
                    containerColor = j3;
                }
                if ((i2 & 16) != 0) {
                    long jM1647contentColorForek8zF_U5 = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i7 >> 9) & 14);
                    i7 &= -57345;
                    j4 = jM1647contentColorForek8zF_U5;
                }
                i8 = i7;
                if ((i2 & 32) != 0) {
                    j5 = containerColor;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i3 = i8 & (-458753);
                } else {
                    j5 = containerColor;
                    i3 = i8;
                }
                if (i4 != 0) {
                    shape3 = extendedFabShape;
                    j6 = j4;
                    modifier3 = companion;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j7 = j5;
                    mutableInteractionSource2 = null;
                    i9 = -326283107;
                } else {
                    shape3 = extendedFabShape;
                    j6 = j4;
                    modifier3 = companion;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j7 = j5;
                    i9 = -326283107;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i9, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:291)");
            }
            m1843FloatingActionButtonXz6DiA(function1, modifier3, shape3, j7, j6, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(398457247, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$1
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

                public final void invoke(Composer composer2, int i15) {
                    ComposerKt.sourceInformation(composer2, "C301@14357L319:FloatingActionButton.kt#uh7d8r");
                    if ((i15 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(398457247, i15, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:301)");
                        }
                        Modifier modifierM722paddingVpY3zN4$default = PaddingKt.m722paddingVpY3zN4$default(SizeKt.m769sizeInqDBjuR0$default(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabMinimumWidth, 0.0f, 0.0f, 0.0f, 14, null), FloatingActionButtonKt.ExtendedFabTextPadding, 0.0f, 2, null);
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                        Function3<RowScope, Composer, Integer, Unit> function4 = function3;
                        ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer2, 54);
                        ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM722paddingVpY3zN4$default);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                        function4.invoke(RowScopeInstance.INSTANCE, composer2, 6);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 12582912 | (i3 & 14) | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (i3 & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            shape4 = shape3;
            j8 = j7;
            j9 = j6;
            floatingActionButtonElevation3 = floatingActionButtonElevation2;
            mutableInteractionSource3 = mutableInteractionSource2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$2
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

                public final void invoke(Composer composer2, int i15) {
                    FloatingActionButtonKt.m1842ExtendedFloatingActionButtonXz6DiA(function0, modifier4, shape4, j8, j9, floatingActionButtonElevation3, mutableInteractionSource3, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0113  */
    /* JADX WARN: Code duplicated, block: B:101:0x0115  */
    /* JADX WARN: Code duplicated, block: B:103:0x0118  */
    /* JADX WARN: Code duplicated, block: B:105:0x0120  */
    /* JADX WARN: Code duplicated, block: B:106:0x0123  */
    /* JADX WARN: Code duplicated, block: B:111:0x0136  */
    /* JADX WARN: Code duplicated, block: B:115:0x014b  */
    /* JADX WARN: Code duplicated, block: B:117:0x015f  */
    /* JADX WARN: Code duplicated, block: B:133:0x018d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:134:0x018f  */
    /* JADX WARN: Code duplicated, block: B:136:0x0196  */
    /* JADX WARN: Code duplicated, block: B:139:0x019c  */
    /* JADX WARN: Code duplicated, block: B:142:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:145:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:146:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:149:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:151:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:152:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:156:0x0203  */
    /* JADX WARN: Code duplicated, block: B:159:0x024e  */
    /* JADX WARN: Code duplicated, block: B:163:0x0264  */
    /* JADX WARN: Code duplicated, block: B:165:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:46:0x007f  */
    /* JADX WARN: Code duplicated, block: B:47:0x0082  */
    /* JADX WARN: Code duplicated, block: B:49:0x0086  */
    /* JADX WARN: Code duplicated, block: B:51:0x008e  */
    /* JADX WARN: Code duplicated, block: B:52:0x0091  */
    /* JADX WARN: Code duplicated, block: B:57:0x009c  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:70:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:76:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:81:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:86:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:91:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:93:0x0101  */
    /* JADX WARN: Code duplicated, block: B:94:0x0104  */
    /* JADX WARN: Code duplicated, block: B:97:0x010b  */
    /* JADX INFO: renamed from: ExtendedFloatingActionButton-ElI5-7k, reason: not valid java name */
    public static final void m1841ExtendedFloatingActionButtonElI57k(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function0<Unit> function0, Modifier modifier, boolean z, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        int i3;
        Function0<Unit> function1;
        Modifier modifier2;
        int i4;
        final boolean z2;
        int i5;
        Shape extendedFabShape;
        long containerColor;
        FloatingActionButtonElevation floatingActionButtonElevationM1835elevationxZ9QkE;
        int i6;
        int i7;
        long jM1647contentColorForek8zF_U;
        MutableInteractionSource mutableInteractionSource2;
        Shape shape2;
        FloatingActionButtonElevation floatingActionButtonElevation2;
        long j3;
        long j4;
        final boolean z3;
        final Modifier modifier3;
        final Shape shape3;
        final long j5;
        final long j6;
        final FloatingActionButtonElevation floatingActionButtonElevation3;
        final MutableInteractionSource mutableInteractionSource3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i8;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1387401842);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ExtendedFloatingActionButton)P(9,4,7,6,3,8,0:c#ui.graphics.Color,1:c#ui.graphics.Color)359@17300L16,360@17375L14,361@17417L31,362@17526L11,373@17867L1053,365@17604L1316:FloatingActionButton.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
            }
        }
        int i9 = i2 & 8;
        if (i9 == 0) {
            if ((i & 3072) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 2048 : 1024;
            }
            i4 = i2 & 16;
            if (i4 != 0) {
                if ((i & 24576) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 16384;
                    } else {
                        i5 = 8192;
                    }
                    i3 |= i5;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        extendedFabShape = shape;
                        int i10 = composerStartRestartGroup.changed(extendedFabShape) ? 131072 : 65536;
                        i3 |= i10;
                    } else {
                        extendedFabShape = shape;
                    }
                    i3 |= i10;
                } else {
                    extendedFabShape = shape;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        containerColor = j;
                        int i11 = composerStartRestartGroup.changed(containerColor) ? 1048576 : 524288;
                        i3 |= i11;
                    } else {
                        containerColor = j;
                    }
                    i3 |= i11;
                } else {
                    containerColor = j;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0 || !composerStartRestartGroup.changed(j2)) {
                        i8 = 4194304;
                    } else {
                        i8 = 8388608;
                    }
                    i3 |= i8;
                }
                if ((i & 100663296) == 0) {
                    if ((i2 & 256) == 0) {
                        floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                        int i12 = composerStartRestartGroup.changed(floatingActionButtonElevationM1835elevationxZ9QkE) ? 67108864 : 33554432;
                        i3 |= i12;
                    } else {
                        floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                    }
                    i3 |= i12;
                } else {
                    floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                }
                i6 = i2 & 512;
                if (i6 != 0) {
                    if ((805306368 & i) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i7 = 536870912;
                        } else {
                            i7 = 268435456;
                        }
                        i3 |= i7;
                    }
                    if ((i3 & 306783379) == 306783378 || !composerStartRestartGroup.getSkipping()) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 64) != 0) {
                                containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                                i3 &= -29360129;
                            } else {
                                jM1647contentColorForek8zF_U = j2;
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                                floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                            }
                            if (i6 != 0) {
                                mutableInteractionSource2 = null;
                            } else {
                                mutableInteractionSource2 = mutableInteractionSource;
                            }
                            shape2 = extendedFabShape;
                            floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                            j3 = jM1647contentColorForek8zF_U;
                            j4 = containerColor;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                            }
                            if ((i2 & 64) != 0) {
                                i3 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i3 &= -29360129;
                            }
                            if ((i2 & 256) != 0) {
                                i3 &= -234881025;
                            }
                            j3 = j2;
                            mutableInteractionSource2 = mutableInteractionSource;
                            shape2 = extendedFabShape;
                            j4 = containerColor;
                            floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
                        }
                        int i13 = i3 >> 6;
                        int i14 = i3 >> 9;
                        Modifier modifier4 = modifier2;
                        m1843FloatingActionButtonXz6DiA(function1, modifier4, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                            public final void invoke(Composer composer2, int i15) {
                                float fM6890constructorimpl;
                                float fM6890constructorimpl2;
                                float fM2961getContainerWidthD9Ej5fM;
                                ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                                if ((i15 & 3) != 2 || !composer2.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1172118032, i15, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                                    }
                                    if (z2) {
                                        fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                                    } else {
                                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                    }
                                    float f = fM6890constructorimpl;
                                    if (z2) {
                                        fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                                    } else {
                                        fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                                    }
                                    float f2 = fM6890constructorimpl2;
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    if (z2) {
                                        fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                                    } else {
                                        fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                                    }
                                    Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                    Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                                    Function2<Composer, Integer, Unit> function4 = function3;
                                    boolean z4 = z2;
                                    final Function2<Composer, Integer, Unit> function5 = function2;
                                    ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                                    ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer2.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer2.startReusableNode();
                                    if (composer2.getInserting()) {
                                        composer2.createNode(constructor);
                                    } else {
                                        composer2.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                                    function4.invoke(composer2, 0);
                                    AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                            invoke(animatedVisibilityScope, composer3, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i16) {
                                            ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(176242764, i16, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                            }
                                            Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                    invoke2(semanticsPropertyReceiver);
                                                    return Unit.INSTANCE;
                                                }
                                            });
                                            Function2<Composer, Integer, Unit> function6 = function5;
                                            ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                            ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                            CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                            ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                            if (!(composer3.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer3.startReusableNode();
                                            if (composer3.getInserting()) {
                                                composer3.createNode(constructor2);
                                            } else {
                                                composer3.useNode();
                                            }
                                            Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                            Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                                composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                                composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                            }
                                            Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                            ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                            SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                            function6.invoke(composer3, 0);
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            composer3.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    }, composer2, 54), composer2, 1600518, 18);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer2.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i13 & 112) | (i13 & 14) | 12582912 | (i14 & 896) | (i14 & 7168) | (57344 & i14) | (458752 & i14) | (i14 & 3670016), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z3 = z2;
                        modifier3 = modifier4;
                        shape3 = shape2;
                        j5 = j4;
                        j6 = j3;
                        floatingActionButtonElevation3 = floatingActionButtonElevation2;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        z3 = z2;
                        shape3 = extendedFabShape;
                        j5 = containerColor;
                        floatingActionButtonElevation3 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j6 = j2;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$4
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

                            public final void invoke(Composer composer2, int i15) {
                                FloatingActionButtonKt.m1841ExtendedFloatingActionButtonElI57k(function2, function3, function0, modifier3, z3, shape3, j5, j6, floatingActionButtonElevation3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= BaseAdapter.HEADER;
                if ((i3 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -29360129;
                        } else {
                            jM1647contentColorForek8zF_U = j2;
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        }
                        if (i6 != 0) {
                            mutableInteractionSource2 = null;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        shape2 = extendedFabShape;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j3 = jM1647contentColorForek8zF_U;
                        j4 = containerColor;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -29360129;
                        } else {
                            jM1647contentColorForek8zF_U = j2;
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        }
                        if (i6 != 0) {
                            mutableInteractionSource2 = null;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        shape2 = extendedFabShape;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j3 = jM1647contentColorForek8zF_U;
                        j4 = containerColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
                    }
                    int i15 = i3 >> 6;
                    int i16 = i3 >> 9;
                    Modifier modifier5 = modifier2;
                    m1843FloatingActionButtonXz6DiA(function1, modifier5, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                        public final void invoke(Composer composer2, int i17) {
                            float fM6890constructorimpl;
                            float fM6890constructorimpl2;
                            float fM2961getContainerWidthD9Ej5fM;
                            ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                            if ((i17 & 3) != 2 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1172118032, i17, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                                }
                                if (z2) {
                                    fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                                } else {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                }
                                float f = fM6890constructorimpl;
                                if (z2) {
                                    fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                                } else {
                                    fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                                }
                                float f2 = fM6890constructorimpl2;
                                Modifier.Companion companion = Modifier.INSTANCE;
                                if (z2) {
                                    fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                                } else {
                                    fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                                }
                                Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                                Function2<Composer, Integer, Unit> function4 = function3;
                                boolean z4 = z2;
                                final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                                ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    composer2.createNode(constructor);
                                } else {
                                    composer2.useNode();
                                }
                                Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                                function4.invoke(composer2, 0);
                                AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                        invoke(animatedVisibilityScope, composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i18) {
                                        ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(176242764, i18, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                        }
                                        Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                invoke2(semanticsPropertyReceiver);
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        Function2<Composer, Integer, Unit> function6 = function5;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                        MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                        CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer3.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            composer3.createNode(constructor2);
                                        } else {
                                            composer3.useNode();
                                        }
                                        Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                        Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                            composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                            composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                        RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                        SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                        function6.invoke(composer3, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        composer3.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer2, 54), composer2, 1600518, 18);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i15 & 112) | (i15 & 14) | 12582912 | (i16 & 896) | (i16 & 7168) | (57344 & i16) | (458752 & i16) | (i16 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z3 = z2;
                    modifier3 = modifier5;
                    shape3 = shape2;
                    j5 = j4;
                    j6 = j3;
                    floatingActionButtonElevation3 = floatingActionButtonElevation2;
                    mutableInteractionSource3 = mutableInteractionSource2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -29360129;
                        } else {
                            jM1647contentColorForek8zF_U = j2;
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        }
                        if (i6 != 0) {
                            mutableInteractionSource2 = null;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        shape2 = extendedFabShape;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j3 = jM1647contentColorForek8zF_U;
                        j4 = containerColor;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -29360129;
                        } else {
                            jM1647contentColorForek8zF_U = j2;
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        }
                        if (i6 != 0) {
                            mutableInteractionSource2 = null;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        shape2 = extendedFabShape;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j3 = jM1647contentColorForek8zF_U;
                        j4 = containerColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
                    }
                    int i17 = i3 >> 6;
                    int i18 = i3 >> 9;
                    Modifier modifier6 = modifier2;
                    m1843FloatingActionButtonXz6DiA(function1, modifier6, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                        public final void invoke(Composer composer2, int i19) {
                            float fM6890constructorimpl;
                            float fM6890constructorimpl2;
                            float fM2961getContainerWidthD9Ej5fM;
                            ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                            if ((i19 & 3) != 2 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1172118032, i19, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                                }
                                if (z2) {
                                    fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                                } else {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                }
                                float f = fM6890constructorimpl;
                                if (z2) {
                                    fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                                } else {
                                    fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                                }
                                float f2 = fM6890constructorimpl2;
                                Modifier.Companion companion = Modifier.INSTANCE;
                                if (z2) {
                                    fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                                } else {
                                    fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                                }
                                Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                                Function2<Composer, Integer, Unit> function4 = function3;
                                boolean z4 = z2;
                                final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                                ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    composer2.createNode(constructor);
                                } else {
                                    composer2.useNode();
                                }
                                Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                                function4.invoke(composer2, 0);
                                AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                        invoke(animatedVisibilityScope, composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i110) {
                                        ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(176242764, i110, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                        }
                                        Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                invoke2(semanticsPropertyReceiver);
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        Function2<Composer, Integer, Unit> function6 = function5;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                        MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                        CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer3.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            composer3.createNode(constructor2);
                                        } else {
                                            composer3.useNode();
                                        }
                                        Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                        Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                            composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                            composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                        RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                        SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                        function6.invoke(composer3, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        composer3.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer2, 54), composer2, 1600518, 18);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i17 & 112) | (i17 & 14) | 12582912 | (i18 & 896) | (i18 & 7168) | (57344 & i18) | (458752 & i18) | (i18 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z3 = z2;
                    modifier3 = modifier6;
                    shape3 = shape2;
                    j5 = j4;
                    j6 = j3;
                    floatingActionButtonElevation3 = floatingActionButtonElevation2;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$4
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

                        public final void invoke(Composer composer2, int i19) {
                            FloatingActionButtonKt.m1841ExtendedFloatingActionButtonElI57k(function2, function3, function0, modifier3, z3, shape3, j5, j6, floatingActionButtonElevation3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 24576;
            z2 = z;
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    extendedFabShape = shape;
                    if (composerStartRestartGroup.changed(extendedFabShape)) {
                    }
                    i3 |= i10;
                } else {
                    extendedFabShape = shape;
                }
                i3 |= i10;
            } else {
                extendedFabShape = shape;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    containerColor = j;
                    if (composerStartRestartGroup.changed(containerColor)) {
                    }
                    i3 |= i11;
                } else {
                    containerColor = j;
                }
                i3 |= i11;
            } else {
                containerColor = j;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i8 = 4194304;
                } else {
                    i8 = 4194304;
                }
                i3 |= i8;
            }
            if ((i & 100663296) == 0) {
                if ((i2 & 256) == 0) {
                    floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                    if (composerStartRestartGroup.changed(floatingActionButtonElevationM1835elevationxZ9QkE)) {
                    }
                    i3 |= i12;
                } else {
                    floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                }
                i3 |= i12;
            } else {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
            }
            i6 = i2 & 512;
            if (i6 != 0) {
                if ((805306368 & i) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i7 = 536870912;
                    } else {
                        i7 = 268435456;
                    }
                    i3 |= i7;
                }
                if ((i3 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -29360129;
                        } else {
                            jM1647contentColorForek8zF_U = j2;
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        }
                        if (i6 != 0) {
                            mutableInteractionSource2 = null;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        shape2 = extendedFabShape;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j3 = jM1647contentColorForek8zF_U;
                        j4 = containerColor;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -29360129;
                        } else {
                            jM1647contentColorForek8zF_U = j2;
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        }
                        if (i6 != 0) {
                            mutableInteractionSource2 = null;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        shape2 = extendedFabShape;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j3 = jM1647contentColorForek8zF_U;
                        j4 = containerColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
                    }
                    int i19 = i3 >> 6;
                    int i110 = i3 >> 9;
                    Modifier modifier7 = modifier2;
                    m1843FloatingActionButtonXz6DiA(function1, modifier7, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                        public final void invoke(Composer composer2, int i111) {
                            float fM6890constructorimpl;
                            float fM6890constructorimpl2;
                            float fM2961getContainerWidthD9Ej5fM;
                            ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                            if ((i111 & 3) != 2 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1172118032, i111, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                                }
                                if (z2) {
                                    fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                                } else {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                }
                                float f = fM6890constructorimpl;
                                if (z2) {
                                    fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                                } else {
                                    fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                                }
                                float f2 = fM6890constructorimpl2;
                                Modifier.Companion companion = Modifier.INSTANCE;
                                if (z2) {
                                    fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                                } else {
                                    fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                                }
                                Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                                Function2<Composer, Integer, Unit> function4 = function3;
                                boolean z4 = z2;
                                final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                                ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    composer2.createNode(constructor);
                                } else {
                                    composer2.useNode();
                                }
                                Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                                function4.invoke(composer2, 0);
                                AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                        invoke(animatedVisibilityScope, composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i112) {
                                        ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(176242764, i112, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                        }
                                        Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                invoke2(semanticsPropertyReceiver);
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        Function2<Composer, Integer, Unit> function6 = function5;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                        MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                        CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer3.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            composer3.createNode(constructor2);
                                        } else {
                                            composer3.useNode();
                                        }
                                        Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                        Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                            composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                            composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                        RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                        SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                        function6.invoke(composer3, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        composer3.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer2, 54), composer2, 1600518, 18);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i19 & 112) | (i19 & 14) | 12582912 | (i110 & 896) | (i110 & 7168) | (57344 & i110) | (458752 & i110) | (i110 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z3 = z2;
                    modifier3 = modifier7;
                    shape3 = shape2;
                    j5 = j4;
                    j6 = j3;
                    floatingActionButtonElevation3 = floatingActionButtonElevation2;
                    mutableInteractionSource3 = mutableInteractionSource2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -29360129;
                        } else {
                            jM1647contentColorForek8zF_U = j2;
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        }
                        if (i6 != 0) {
                            mutableInteractionSource2 = null;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        shape2 = extendedFabShape;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j3 = jM1647contentColorForek8zF_U;
                        j4 = containerColor;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -29360129;
                        } else {
                            jM1647contentColorForek8zF_U = j2;
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        }
                        if (i6 != 0) {
                            mutableInteractionSource2 = null;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        shape2 = extendedFabShape;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j3 = jM1647contentColorForek8zF_U;
                        j4 = containerColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
                    }
                    int i111 = i3 >> 6;
                    int i112 = i3 >> 9;
                    Modifier modifier8 = modifier2;
                    m1843FloatingActionButtonXz6DiA(function1, modifier8, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                        public final void invoke(Composer composer2, int i113) {
                            float fM6890constructorimpl;
                            float fM6890constructorimpl2;
                            float fM2961getContainerWidthD9Ej5fM;
                            ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                            if ((i113 & 3) != 2 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1172118032, i113, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                                }
                                if (z2) {
                                    fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                                } else {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                }
                                float f = fM6890constructorimpl;
                                if (z2) {
                                    fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                                } else {
                                    fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                                }
                                float f2 = fM6890constructorimpl2;
                                Modifier.Companion companion = Modifier.INSTANCE;
                                if (z2) {
                                    fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                                } else {
                                    fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                                }
                                Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                                Function2<Composer, Integer, Unit> function4 = function3;
                                boolean z4 = z2;
                                final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                                ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    composer2.createNode(constructor);
                                } else {
                                    composer2.useNode();
                                }
                                Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                                function4.invoke(composer2, 0);
                                AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                        invoke(animatedVisibilityScope, composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i114) {
                                        ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(176242764, i114, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                        }
                                        Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                invoke2(semanticsPropertyReceiver);
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        Function2<Composer, Integer, Unit> function6 = function5;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                        MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                        CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer3.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            composer3.createNode(constructor2);
                                        } else {
                                            composer3.useNode();
                                        }
                                        Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                        Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                            composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                            composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                        RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                        SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                        function6.invoke(composer3, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        composer3.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer2, 54), composer2, 1600518, 18);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i111 & 112) | (i111 & 14) | 12582912 | (i112 & 896) | (i112 & 7168) | (57344 & i112) | (458752 & i112) | (i112 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z3 = z2;
                    modifier3 = modifier8;
                    shape3 = shape2;
                    j5 = j4;
                    j6 = j3;
                    floatingActionButtonElevation3 = floatingActionButtonElevation2;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$4
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

                        public final void invoke(Composer composer2, int i113) {
                            FloatingActionButtonKt.m1841ExtendedFloatingActionButtonElI57k(function2, function3, function0, modifier3, z3, shape3, j5, j6, floatingActionButtonElevation3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= BaseAdapter.HEADER;
            if ((i3 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -29360129;
                    } else {
                        jM1647contentColorForek8zF_U = j2;
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    }
                    if (i6 != 0) {
                        mutableInteractionSource2 = null;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    shape2 = extendedFabShape;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j3 = jM1647contentColorForek8zF_U;
                    j4 = containerColor;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -29360129;
                    } else {
                        jM1647contentColorForek8zF_U = j2;
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    }
                    if (i6 != 0) {
                        mutableInteractionSource2 = null;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    shape2 = extendedFabShape;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j3 = jM1647contentColorForek8zF_U;
                    j4 = containerColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
                }
                int i113 = i3 >> 6;
                int i114 = i3 >> 9;
                Modifier modifier9 = modifier2;
                m1843FloatingActionButtonXz6DiA(function1, modifier9, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                    public final void invoke(Composer composer2, int i115) {
                        float fM6890constructorimpl;
                        float fM6890constructorimpl2;
                        float fM2961getContainerWidthD9Ej5fM;
                        ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                        if ((i115 & 3) != 2 || !composer2.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1172118032, i115, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                            }
                            if (z2) {
                                fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            float f = fM6890constructorimpl;
                            if (z2) {
                                fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                            } else {
                                fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                            }
                            float f2 = fM6890constructorimpl2;
                            Modifier.Companion companion = Modifier.INSTANCE;
                            if (z2) {
                                fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                            } else {
                                fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                            }
                            Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                            Function2<Composer, Integer, Unit> function4 = function3;
                            boolean z4 = z2;
                            final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                            ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                            function4.invoke(composer2, 0);
                            AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                    invoke(animatedVisibilityScope, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i116) {
                                    ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(176242764, i116, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                    }
                                    Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            invoke2(semanticsPropertyReceiver);
                                            return Unit.INSTANCE;
                                        }
                                    });
                                    Function2<Composer, Integer, Unit> function6 = function5;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor2);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                    Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                        composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                        composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                    SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                    function6.invoke(composer3, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer2, 54), composer2, 1600518, 18);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer2.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i113 & 112) | (i113 & 14) | 12582912 | (i114 & 896) | (i114 & 7168) | (57344 & i114) | (458752 & i114) | (i114 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z3 = z2;
                modifier3 = modifier9;
                shape3 = shape2;
                j5 = j4;
                j6 = j3;
                floatingActionButtonElevation3 = floatingActionButtonElevation2;
                mutableInteractionSource3 = mutableInteractionSource2;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -29360129;
                    } else {
                        jM1647contentColorForek8zF_U = j2;
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    }
                    if (i6 != 0) {
                        mutableInteractionSource2 = null;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    shape2 = extendedFabShape;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j3 = jM1647contentColorForek8zF_U;
                    j4 = containerColor;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -29360129;
                    } else {
                        jM1647contentColorForek8zF_U = j2;
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    }
                    if (i6 != 0) {
                        mutableInteractionSource2 = null;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    shape2 = extendedFabShape;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j3 = jM1647contentColorForek8zF_U;
                    j4 = containerColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
                }
                int i115 = i3 >> 6;
                int i116 = i3 >> 9;
                Modifier modifier10 = modifier2;
                m1843FloatingActionButtonXz6DiA(function1, modifier10, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                    public final void invoke(Composer composer2, int i117) {
                        float fM6890constructorimpl;
                        float fM6890constructorimpl2;
                        float fM2961getContainerWidthD9Ej5fM;
                        ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                        if ((i117 & 3) != 2 || !composer2.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1172118032, i117, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                            }
                            if (z2) {
                                fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            float f = fM6890constructorimpl;
                            if (z2) {
                                fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                            } else {
                                fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                            }
                            float f2 = fM6890constructorimpl2;
                            Modifier.Companion companion = Modifier.INSTANCE;
                            if (z2) {
                                fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                            } else {
                                fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                            }
                            Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                            Function2<Composer, Integer, Unit> function4 = function3;
                            boolean z4 = z2;
                            final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                            ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                            function4.invoke(composer2, 0);
                            AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                    invoke(animatedVisibilityScope, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i118) {
                                    ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(176242764, i118, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                    }
                                    Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            invoke2(semanticsPropertyReceiver);
                                            return Unit.INSTANCE;
                                        }
                                    });
                                    Function2<Composer, Integer, Unit> function6 = function5;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor2);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                    Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                        composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                        composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                    SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                    function6.invoke(composer3, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer2, 54), composer2, 1600518, 18);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer2.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i115 & 112) | (i115 & 14) | 12582912 | (i116 & 896) | (i116 & 7168) | (57344 & i116) | (458752 & i116) | (i116 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z3 = z2;
                modifier3 = modifier10;
                shape3 = shape2;
                j5 = j4;
                j6 = j3;
                floatingActionButtonElevation3 = floatingActionButtonElevation2;
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$4
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

                    public final void invoke(Composer composer2, int i117) {
                        FloatingActionButtonKt.m1841ExtendedFloatingActionButtonElI57k(function2, function3, function0, modifier3, z3, shape3, j5, j6, floatingActionButtonElevation3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        modifier2 = modifier;
        i4 = i2 & 16;
        if (i4 != 0) {
            if ((i & 24576) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 16384;
                } else {
                    i5 = 8192;
                }
                i3 |= i5;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    extendedFabShape = shape;
                    if (composerStartRestartGroup.changed(extendedFabShape)) {
                    }
                    i3 |= i10;
                } else {
                    extendedFabShape = shape;
                }
                i3 |= i10;
            } else {
                extendedFabShape = shape;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    containerColor = j;
                    if (composerStartRestartGroup.changed(containerColor)) {
                    }
                    i3 |= i11;
                } else {
                    containerColor = j;
                }
                i3 |= i11;
            } else {
                containerColor = j;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i8 = 4194304;
                } else {
                    i8 = 4194304;
                }
                i3 |= i8;
            }
            if ((i & 100663296) == 0) {
                if ((i2 & 256) == 0) {
                    floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                    if (composerStartRestartGroup.changed(floatingActionButtonElevationM1835elevationxZ9QkE)) {
                    }
                    i3 |= i12;
                } else {
                    floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                }
                i3 |= i12;
            } else {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
            }
            i6 = i2 & 512;
            if (i6 != 0) {
                if ((805306368 & i) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i7 = 536870912;
                    } else {
                        i7 = 268435456;
                    }
                    i3 |= i7;
                }
                if ((i3 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -29360129;
                        } else {
                            jM1647contentColorForek8zF_U = j2;
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        }
                        if (i6 != 0) {
                            mutableInteractionSource2 = null;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        shape2 = extendedFabShape;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j3 = jM1647contentColorForek8zF_U;
                        j4 = containerColor;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -29360129;
                        } else {
                            jM1647contentColorForek8zF_U = j2;
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        }
                        if (i6 != 0) {
                            mutableInteractionSource2 = null;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        shape2 = extendedFabShape;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j3 = jM1647contentColorForek8zF_U;
                        j4 = containerColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
                    }
                    int i117 = i3 >> 6;
                    int i118 = i3 >> 9;
                    Modifier modifier11 = modifier2;
                    m1843FloatingActionButtonXz6DiA(function1, modifier11, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                        public final void invoke(Composer composer2, int i119) {
                            float fM6890constructorimpl;
                            float fM6890constructorimpl2;
                            float fM2961getContainerWidthD9Ej5fM;
                            ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                            if ((i119 & 3) != 2 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1172118032, i119, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                                }
                                if (z2) {
                                    fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                                } else {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                }
                                float f = fM6890constructorimpl;
                                if (z2) {
                                    fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                                } else {
                                    fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                                }
                                float f2 = fM6890constructorimpl2;
                                Modifier.Companion companion = Modifier.INSTANCE;
                                if (z2) {
                                    fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                                } else {
                                    fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                                }
                                Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                                Function2<Composer, Integer, Unit> function4 = function3;
                                boolean z4 = z2;
                                final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                                ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    composer2.createNode(constructor);
                                } else {
                                    composer2.useNode();
                                }
                                Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                                function4.invoke(composer2, 0);
                                AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                        invoke(animatedVisibilityScope, composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i1110) {
                                        ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(176242764, i1110, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                        }
                                        Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                invoke2(semanticsPropertyReceiver);
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        Function2<Composer, Integer, Unit> function6 = function5;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                        MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                        CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer3.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            composer3.createNode(constructor2);
                                        } else {
                                            composer3.useNode();
                                        }
                                        Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                        Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                            composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                            composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                        RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                        SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                        function6.invoke(composer3, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        composer3.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer2, 54), composer2, 1600518, 18);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i117 & 112) | (i117 & 14) | 12582912 | (i118 & 896) | (i118 & 7168) | (57344 & i118) | (458752 & i118) | (i118 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z3 = z2;
                    modifier3 = modifier11;
                    shape3 = shape2;
                    j5 = j4;
                    j6 = j3;
                    floatingActionButtonElevation3 = floatingActionButtonElevation2;
                    mutableInteractionSource3 = mutableInteractionSource2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -29360129;
                        } else {
                            jM1647contentColorForek8zF_U = j2;
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        }
                        if (i6 != 0) {
                            mutableInteractionSource2 = null;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        shape2 = extendedFabShape;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j3 = jM1647contentColorForek8zF_U;
                        j4 = containerColor;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 64) != 0) {
                            containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -29360129;
                        } else {
                            jM1647contentColorForek8zF_U = j2;
                        }
                        if ((i2 & 256) != 0) {
                            i3 &= -234881025;
                            floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                        }
                        if (i6 != 0) {
                            mutableInteractionSource2 = null;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        shape2 = extendedFabShape;
                        floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                        j3 = jM1647contentColorForek8zF_U;
                        j4 = containerColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
                    }
                    int i119 = i3 >> 6;
                    int i1110 = i3 >> 9;
                    Modifier modifier12 = modifier2;
                    m1843FloatingActionButtonXz6DiA(function1, modifier12, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                        public final void invoke(Composer composer2, int i1111) {
                            float fM6890constructorimpl;
                            float fM6890constructorimpl2;
                            float fM2961getContainerWidthD9Ej5fM;
                            ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                            if ((i1111 & 3) != 2 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1172118032, i1111, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                                }
                                if (z2) {
                                    fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                                } else {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                }
                                float f = fM6890constructorimpl;
                                if (z2) {
                                    fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                                } else {
                                    fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                                }
                                float f2 = fM6890constructorimpl2;
                                Modifier.Companion companion = Modifier.INSTANCE;
                                if (z2) {
                                    fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                                } else {
                                    fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                                }
                                Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                                Function2<Composer, Integer, Unit> function4 = function3;
                                boolean z4 = z2;
                                final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                                ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    composer2.createNode(constructor);
                                } else {
                                    composer2.useNode();
                                }
                                Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                                function4.invoke(composer2, 0);
                                AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                        invoke(animatedVisibilityScope, composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i1112) {
                                        ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(176242764, i1112, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                        }
                                        Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                invoke2(semanticsPropertyReceiver);
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        Function2<Composer, Integer, Unit> function6 = function5;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                        MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                        CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer3.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            composer3.createNode(constructor2);
                                        } else {
                                            composer3.useNode();
                                        }
                                        Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                        Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                            composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                            composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                        RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                        SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                        function6.invoke(composer3, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        composer3.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer2, 54), composer2, 1600518, 18);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i119 & 112) | (i119 & 14) | 12582912 | (i1110 & 896) | (i1110 & 7168) | (57344 & i1110) | (458752 & i1110) | (i1110 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z3 = z2;
                    modifier3 = modifier12;
                    shape3 = shape2;
                    j5 = j4;
                    j6 = j3;
                    floatingActionButtonElevation3 = floatingActionButtonElevation2;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$4
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

                        public final void invoke(Composer composer2, int i1111) {
                            FloatingActionButtonKt.m1841ExtendedFloatingActionButtonElI57k(function2, function3, function0, modifier3, z3, shape3, j5, j6, floatingActionButtonElevation3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= BaseAdapter.HEADER;
            if ((i3 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -29360129;
                    } else {
                        jM1647contentColorForek8zF_U = j2;
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    }
                    if (i6 != 0) {
                        mutableInteractionSource2 = null;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    shape2 = extendedFabShape;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j3 = jM1647contentColorForek8zF_U;
                    j4 = containerColor;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -29360129;
                    } else {
                        jM1647contentColorForek8zF_U = j2;
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    }
                    if (i6 != 0) {
                        mutableInteractionSource2 = null;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    shape2 = extendedFabShape;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j3 = jM1647contentColorForek8zF_U;
                    j4 = containerColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
                }
                int i1111 = i3 >> 6;
                int i1112 = i3 >> 9;
                Modifier modifier13 = modifier2;
                m1843FloatingActionButtonXz6DiA(function1, modifier13, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                    public final void invoke(Composer composer2, int i1113) {
                        float fM6890constructorimpl;
                        float fM6890constructorimpl2;
                        float fM2961getContainerWidthD9Ej5fM;
                        ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                        if ((i1113 & 3) != 2 || !composer2.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1172118032, i1113, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                            }
                            if (z2) {
                                fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            float f = fM6890constructorimpl;
                            if (z2) {
                                fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                            } else {
                                fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                            }
                            float f2 = fM6890constructorimpl2;
                            Modifier.Companion companion = Modifier.INSTANCE;
                            if (z2) {
                                fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                            } else {
                                fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                            }
                            Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                            Function2<Composer, Integer, Unit> function4 = function3;
                            boolean z4 = z2;
                            final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                            ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                            function4.invoke(composer2, 0);
                            AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                    invoke(animatedVisibilityScope, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i1114) {
                                    ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(176242764, i1114, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                    }
                                    Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            invoke2(semanticsPropertyReceiver);
                                            return Unit.INSTANCE;
                                        }
                                    });
                                    Function2<Composer, Integer, Unit> function6 = function5;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor2);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                    Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                        composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                        composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                    SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                    function6.invoke(composer3, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer2, 54), composer2, 1600518, 18);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer2.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i1111 & 112) | (i1111 & 14) | 12582912 | (i1112 & 896) | (i1112 & 7168) | (57344 & i1112) | (458752 & i1112) | (i1112 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z3 = z2;
                modifier3 = modifier13;
                shape3 = shape2;
                j5 = j4;
                j6 = j3;
                floatingActionButtonElevation3 = floatingActionButtonElevation2;
                mutableInteractionSource3 = mutableInteractionSource2;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -29360129;
                    } else {
                        jM1647contentColorForek8zF_U = j2;
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    }
                    if (i6 != 0) {
                        mutableInteractionSource2 = null;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    shape2 = extendedFabShape;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j3 = jM1647contentColorForek8zF_U;
                    j4 = containerColor;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -29360129;
                    } else {
                        jM1647contentColorForek8zF_U = j2;
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    }
                    if (i6 != 0) {
                        mutableInteractionSource2 = null;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    shape2 = extendedFabShape;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j3 = jM1647contentColorForek8zF_U;
                    j4 = containerColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
                }
                int i1113 = i3 >> 6;
                int i1114 = i3 >> 9;
                Modifier modifier14 = modifier2;
                m1843FloatingActionButtonXz6DiA(function1, modifier14, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                    public final void invoke(Composer composer2, int i1115) {
                        float fM6890constructorimpl;
                        float fM6890constructorimpl2;
                        float fM2961getContainerWidthD9Ej5fM;
                        ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                        if ((i1115 & 3) != 2 || !composer2.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1172118032, i1115, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                            }
                            if (z2) {
                                fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            float f = fM6890constructorimpl;
                            if (z2) {
                                fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                            } else {
                                fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                            }
                            float f2 = fM6890constructorimpl2;
                            Modifier.Companion companion = Modifier.INSTANCE;
                            if (z2) {
                                fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                            } else {
                                fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                            }
                            Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                            Function2<Composer, Integer, Unit> function4 = function3;
                            boolean z4 = z2;
                            final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                            ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                            function4.invoke(composer2, 0);
                            AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                    invoke(animatedVisibilityScope, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i1116) {
                                    ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(176242764, i1116, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                    }
                                    Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            invoke2(semanticsPropertyReceiver);
                                            return Unit.INSTANCE;
                                        }
                                    });
                                    Function2<Composer, Integer, Unit> function6 = function5;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor2);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                    Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                        composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                        composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                    SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                    function6.invoke(composer3, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer2, 54), composer2, 1600518, 18);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer2.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i1113 & 112) | (i1113 & 14) | 12582912 | (i1114 & 896) | (i1114 & 7168) | (57344 & i1114) | (458752 & i1114) | (i1114 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z3 = z2;
                modifier3 = modifier14;
                shape3 = shape2;
                j5 = j4;
                j6 = j3;
                floatingActionButtonElevation3 = floatingActionButtonElevation2;
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$4
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

                    public final void invoke(Composer composer2, int i1115) {
                        FloatingActionButtonKt.m1841ExtendedFloatingActionButtonElI57k(function2, function3, function0, modifier3, z3, shape3, j5, j6, floatingActionButtonElevation3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 24576;
        z2 = z;
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                extendedFabShape = shape;
                if (composerStartRestartGroup.changed(extendedFabShape)) {
                }
                i3 |= i10;
            } else {
                extendedFabShape = shape;
            }
            i3 |= i10;
        } else {
            extendedFabShape = shape;
        }
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                containerColor = j;
                if (composerStartRestartGroup.changed(containerColor)) {
                }
                i3 |= i11;
            } else {
                containerColor = j;
            }
            i3 |= i11;
        } else {
            containerColor = j;
        }
        if ((i & 12582912) != 0) {
            if ((i2 & 128) == 0) {
                i8 = 4194304;
            } else {
                i8 = 4194304;
            }
            i3 |= i8;
        }
        if ((i & 100663296) == 0) {
            if ((i2 & 256) == 0) {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
                if (composerStartRestartGroup.changed(floatingActionButtonElevationM1835elevationxZ9QkE)) {
                }
                i3 |= i12;
            } else {
                floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
            }
            i3 |= i12;
        } else {
            floatingActionButtonElevationM1835elevationxZ9QkE = floatingActionButtonElevation;
        }
        i6 = i2 & 512;
        if (i6 != 0) {
            if ((805306368 & i) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i7 = 536870912;
                } else {
                    i7 = 268435456;
                }
                i3 |= i7;
            }
            if ((i3 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -29360129;
                    } else {
                        jM1647contentColorForek8zF_U = j2;
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    }
                    if (i6 != 0) {
                        mutableInteractionSource2 = null;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    shape2 = extendedFabShape;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j3 = jM1647contentColorForek8zF_U;
                    j4 = containerColor;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -29360129;
                    } else {
                        jM1647contentColorForek8zF_U = j2;
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    }
                    if (i6 != 0) {
                        mutableInteractionSource2 = null;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    shape2 = extendedFabShape;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j3 = jM1647contentColorForek8zF_U;
                    j4 = containerColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
                }
                int i1115 = i3 >> 6;
                int i1116 = i3 >> 9;
                Modifier modifier15 = modifier2;
                m1843FloatingActionButtonXz6DiA(function1, modifier15, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                    public final void invoke(Composer composer2, int i1117) {
                        float fM6890constructorimpl;
                        float fM6890constructorimpl2;
                        float fM2961getContainerWidthD9Ej5fM;
                        ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                        if ((i1117 & 3) != 2 || !composer2.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1172118032, i1117, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                            }
                            if (z2) {
                                fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            float f = fM6890constructorimpl;
                            if (z2) {
                                fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                            } else {
                                fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                            }
                            float f2 = fM6890constructorimpl2;
                            Modifier.Companion companion = Modifier.INSTANCE;
                            if (z2) {
                                fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                            } else {
                                fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                            }
                            Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                            Function2<Composer, Integer, Unit> function4 = function3;
                            boolean z4 = z2;
                            final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                            ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                            function4.invoke(composer2, 0);
                            AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                    invoke(animatedVisibilityScope, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i1118) {
                                    ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(176242764, i1118, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                    }
                                    Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            invoke2(semanticsPropertyReceiver);
                                            return Unit.INSTANCE;
                                        }
                                    });
                                    Function2<Composer, Integer, Unit> function6 = function5;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor2);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                    Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                        composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                        composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                    SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                    function6.invoke(composer3, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer2, 54), composer2, 1600518, 18);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer2.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i1115 & 112) | (i1115 & 14) | 12582912 | (i1116 & 896) | (i1116 & 7168) | (57344 & i1116) | (458752 & i1116) | (i1116 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z3 = z2;
                modifier3 = modifier15;
                shape3 = shape2;
                j5 = j4;
                j6 = j3;
                floatingActionButtonElevation3 = floatingActionButtonElevation2;
                mutableInteractionSource3 = mutableInteractionSource2;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -29360129;
                    } else {
                        jM1647contentColorForek8zF_U = j2;
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    }
                    if (i6 != 0) {
                        mutableInteractionSource2 = null;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    shape2 = extendedFabShape;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j3 = jM1647contentColorForek8zF_U;
                    j4 = containerColor;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 64) != 0) {
                        containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -29360129;
                    } else {
                        jM1647contentColorForek8zF_U = j2;
                    }
                    if ((i2 & 256) != 0) {
                        i3 &= -234881025;
                        floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    }
                    if (i6 != 0) {
                        mutableInteractionSource2 = null;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    shape2 = extendedFabShape;
                    floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                    j3 = jM1647contentColorForek8zF_U;
                    j4 = containerColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
                }
                int i1117 = i3 >> 6;
                int i1118 = i3 >> 9;
                Modifier modifier16 = modifier2;
                m1843FloatingActionButtonXz6DiA(function1, modifier16, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                    public final void invoke(Composer composer2, int i1119) {
                        float fM6890constructorimpl;
                        float fM6890constructorimpl2;
                        float fM2961getContainerWidthD9Ej5fM;
                        ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                        if ((i1119 & 3) != 2 || !composer2.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1172118032, i1119, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                            }
                            if (z2) {
                                fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            float f = fM6890constructorimpl;
                            if (z2) {
                                fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                            } else {
                                fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                            }
                            float f2 = fM6890constructorimpl2;
                            Modifier.Companion companion = Modifier.INSTANCE;
                            if (z2) {
                                fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                            } else {
                                fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                            }
                            Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                            Function2<Composer, Integer, Unit> function4 = function3;
                            boolean z4 = z2;
                            final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                            ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                            function4.invoke(composer2, 0);
                            AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                    invoke(animatedVisibilityScope, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i11110) {
                                    ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(176242764, i11110, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                    }
                                    Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            invoke2(semanticsPropertyReceiver);
                                            return Unit.INSTANCE;
                                        }
                                    });
                                    Function2<Composer, Integer, Unit> function6 = function5;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor2);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                    Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                        composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                        composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                    SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                    function6.invoke(composer3, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer2, 54), composer2, 1600518, 18);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer2.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i1117 & 112) | (i1117 & 14) | 12582912 | (i1118 & 896) | (i1118 & 7168) | (57344 & i1118) | (458752 & i1118) | (i1118 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z3 = z2;
                modifier3 = modifier16;
                shape3 = shape2;
                j5 = j4;
                j6 = j3;
                floatingActionButtonElevation3 = floatingActionButtonElevation2;
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$4
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

                    public final void invoke(Composer composer2, int i1119) {
                        FloatingActionButtonKt.m1841ExtendedFloatingActionButtonElI57k(function2, function3, function0, modifier3, z3, shape3, j5, j6, floatingActionButtonElevation3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= BaseAdapter.HEADER;
        if ((i3 & 306783379) == 306783378) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 64) != 0) {
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                }
                if ((i2 & 128) != 0) {
                    jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                    i3 &= -29360129;
                } else {
                    jM1647contentColorForek8zF_U = j2;
                }
                if ((i2 & 256) != 0) {
                    i3 &= -234881025;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                }
                if (i6 != 0) {
                    mutableInteractionSource2 = null;
                } else {
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                shape2 = extendedFabShape;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                j3 = jM1647contentColorForek8zF_U;
                j4 = containerColor;
            } else {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 64) != 0) {
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                }
                if ((i2 & 128) != 0) {
                    jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                    i3 &= -29360129;
                } else {
                    jM1647contentColorForek8zF_U = j2;
                }
                if ((i2 & 256) != 0) {
                    i3 &= -234881025;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                }
                if (i6 != 0) {
                    mutableInteractionSource2 = null;
                } else {
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                shape2 = extendedFabShape;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                j3 = jM1647contentColorForek8zF_U;
                j4 = containerColor;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
            }
            int i1119 = i3 >> 6;
            int i11110 = i3 >> 9;
            Modifier modifier17 = modifier2;
            m1843FloatingActionButtonXz6DiA(function1, modifier17, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                public final void invoke(Composer composer2, int i11111) {
                    float fM6890constructorimpl;
                    float fM6890constructorimpl2;
                    float fM2961getContainerWidthD9Ej5fM;
                    ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                    if ((i11111 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1172118032, i11111, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                        }
                        if (z2) {
                            fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        float f = fM6890constructorimpl;
                        if (z2) {
                            fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                        } else {
                            fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                        }
                        float f2 = fM6890constructorimpl2;
                        Modifier.Companion companion = Modifier.INSTANCE;
                        if (z2) {
                            fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                        } else {
                            fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                        }
                        Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                        Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function4 = function3;
                        boolean z4 = z2;
                        final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                        ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                        ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                        function4.invoke(composer2, 0);
                        AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                invoke(animatedVisibilityScope, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i11112) {
                                ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(176242764, i11112, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                }
                                Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }
                                });
                                Function2<Composer, Integer, Unit> function6 = function5;
                                ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer3.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor2);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                    composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                    composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                function6.invoke(composer3, 0);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                composer3.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer2, 54), composer2, 1600518, 18);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i1119 & 112) | (i1119 & 14) | 12582912 | (i11110 & 896) | (i11110 & 7168) | (57344 & i11110) | (458752 & i11110) | (i11110 & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z3 = z2;
            modifier3 = modifier17;
            shape3 = shape2;
            j5 = j4;
            j6 = j3;
            floatingActionButtonElevation3 = floatingActionButtonElevation2;
            mutableInteractionSource3 = mutableInteractionSource2;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 64) != 0) {
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                }
                if ((i2 & 128) != 0) {
                    jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                    i3 &= -29360129;
                } else {
                    jM1647contentColorForek8zF_U = j2;
                }
                if ((i2 & 256) != 0) {
                    i3 &= -234881025;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                }
                if (i6 != 0) {
                    mutableInteractionSource2 = null;
                } else {
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                shape2 = extendedFabShape;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                j3 = jM1647contentColorForek8zF_U;
                j4 = containerColor;
            } else {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    extendedFabShape = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 64) != 0) {
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                }
                if ((i2 & 128) != 0) {
                    jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i3 >> 18) & 14);
                    i3 &= -29360129;
                } else {
                    jM1647contentColorForek8zF_U = j2;
                }
                if ((i2 & 256) != 0) {
                    i3 &= -234881025;
                    floatingActionButtonElevationM1835elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m1835elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                }
                if (i6 != 0) {
                    mutableInteractionSource2 = null;
                } else {
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                shape2 = extendedFabShape;
                floatingActionButtonElevation2 = floatingActionButtonElevationM1835elevationxZ9QkE;
                j3 = jM1647contentColorForek8zF_U;
                j4 = containerColor;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1387401842, i3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:364)");
            }
            int i11111 = i3 >> 6;
            int i11112 = i3 >> 9;
            Modifier modifier18 = modifier2;
            m1843FloatingActionButtonXz6DiA(function1, modifier18, shape2, j4, j3, floatingActionButtonElevation2, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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

                public final void invoke(Composer composer2, int i11113) {
                    float fM6890constructorimpl;
                    float fM6890constructorimpl2;
                    float fM2961getContainerWidthD9Ej5fM;
                    ComposerKt.sourceInformation(composer2, "C377@18029L885:FloatingActionButton.kt#uh7d8r");
                    if ((i11113 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1172118032, i11113, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:374)");
                        }
                        if (z2) {
                            fM6890constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        float f = fM6890constructorimpl;
                        if (z2) {
                            fM6890constructorimpl2 = FloatingActionButtonKt.ExtendedFabTextPadding;
                        } else {
                            fM6890constructorimpl2 = Dp.m6890constructorimpl(0.0f);
                        }
                        float f2 = fM6890constructorimpl2;
                        Modifier.Companion companion = Modifier.INSTANCE;
                        if (z2) {
                            fM2961getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                        } else {
                            fM2961getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2961getContainerWidthD9Ej5fM();
                        }
                        Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.m769sizeInqDBjuR0$default(companion, fM2961getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), f, 0.0f, f2, 0.0f, 10, null);
                        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                        Arrangement.HorizontalOrVertical start = z2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function4 = function3;
                        boolean z4 = z2;
                        final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                        ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer2, 48);
                        ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM724paddingqDBjuR0$default);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer2, 1618821151, "C388@18524L6,393@18718L186,389@18543L361:FloatingActionButton.kt#uh7d8r");
                        function4.invoke(composer2, 0);
                        AnimatedVisibilityKt.AnimatedVisibility(rowScopeInstance, z4, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.rememberComposableLambda(176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                invoke(animatedVisibilityScope, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i11114) {
                                ComposerKt.sourceInformation(composer3, "C394@18736L154:FloatingActionButton.kt#uh7d8r");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(176242764, i11114, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:394)");
                                }
                                Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1.1
                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }
                                });
                                Function2<Composer, Integer, Unit> function6 = function5;
                                ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierClearAndSetSemantics);
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer3.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor2);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer3);
                                Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                    composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                    composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer3, 1967858577, "C395@18796L49,396@18866L6:FloatingActionButton.kt#uh7d8r");
                                SpacerKt.Spacer(SizeKt.m770width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), composer3, 6);
                                function6.invoke(composer3, 0);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                composer3.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer2, 54), composer2, 1600518, 18);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i11111 & 112) | (i11111 & 14) | 12582912 | (i11112 & 896) | (i11112 & 7168) | (57344 & i11112) | (458752 & i11112) | (i11112 & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z3 = z2;
            modifier3 = modifier18;
            shape3 = shape2;
            j5 = j4;
            j6 = j3;
            floatingActionButtonElevation3 = floatingActionButtonElevation2;
            mutableInteractionSource3 = mutableInteractionSource2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$4
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

                public final void invoke(Composer composer2, int i11113) {
                    FloatingActionButtonKt.m1841ExtendedFloatingActionButtonElI57k(function2, function3, function0, modifier3, z3, shape3, j5, j6, floatingActionButtonElevation3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }
}
