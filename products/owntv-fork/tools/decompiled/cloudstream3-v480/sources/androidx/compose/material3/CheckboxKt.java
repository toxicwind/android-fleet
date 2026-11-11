package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.ToggleableKt;
import androidx.compose.material3.tokens.CheckboxTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.state.ToggleableStateKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Checkbox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aU\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a-\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0003¢\u0006\u0002\u0010\u001b\u001aO\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001a2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0007¢\u0006\u0002\u0010 \u001a6\u0010!\u001a\u00020\u000b*\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'H\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a>\u0010+\u001a\u00020\u000b*\u00020\"2\u0006\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020'2\u0006\u0010.\u001a\u00020'2\u0006\u0010/\u001a\u00020'2\u0006\u00100\u001a\u000201H\u0002ø\u0001\u0000¢\u0006\u0004\b2\u00103\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u0010\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u0010\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u0010\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00064"}, d2 = {"BoxInDuration", "", "BoxOutDuration", "CheckAnimationDuration", "CheckboxDefaultPadding", "Landroidx/compose/ui/unit/Dp;", "F", "CheckboxSize", "RadiusSize", "StrokeWidth", "Checkbox", "", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "colors", "Landroidx/compose/material3/CheckboxColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/CheckboxColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "CheckboxImpl", ES6Iterator.VALUE_PROPERTY, "Landroidx/compose/ui/state/ToggleableState;", "(ZLandroidx/compose/ui/state/ToggleableState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/CheckboxColors;Landroidx/compose/runtime/Composer;I)V", "TriStateCheckbox", "state", "onClick", "Lkotlin/Function0;", "(Landroidx/compose/ui/state/ToggleableState;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/CheckboxColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "drawBox", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "boxColor", "Landroidx/compose/ui/graphics/Color;", "borderColor", "radius", "", "strokeWidth", "drawBox-1wkBAMs", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJFF)V", "drawCheck", "checkColor", "checkFraction", "crossCenterGravitation", "strokeWidthPx", "drawingCache", "Landroidx/compose/material3/CheckDrawingCache;", "drawCheck-3IgeMak", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFFFLandroidx/compose/material3/CheckDrawingCache;)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CheckboxKt {
    private static final int BoxInDuration = 50;
    private static final int BoxOutDuration = 100;
    private static final int CheckAnimationDuration = 100;
    private static final float CheckboxDefaultPadding = Dp.m6890constructorimpl(2.0f);
    private static final float CheckboxSize = Dp.m6890constructorimpl(20.0f);
    private static final float StrokeWidth = Dp.m6890constructorimpl(2.0f);
    private static final float RadiusSize = Dp.m6890constructorimpl(2.0f);

    /* JADX INFO: compiled from: Checkbox.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToggleableState.values().length];
            try {
                iArr[ToggleableState.On.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ToggleableState.Off.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ToggleableState.Indeterminate.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0137  */
    /* JADX WARN: Code duplicated, block: B:101:0x013a  */
    /* JADX WARN: Code duplicated, block: B:105:0x0141  */
    /* JADX WARN: Code duplicated, block: B:108:0x014b  */
    /* JADX WARN: Code duplicated, block: B:110:0x0153  */
    /* JADX WARN: Code duplicated, block: B:114:0x0178  */
    /* JADX WARN: Code duplicated, block: B:118:0x0186  */
    /* JADX WARN: Code duplicated, block: B:120:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0063  */
    /* JADX WARN: Code duplicated, block: B:37:0x0066  */
    /* JADX WARN: Code duplicated, block: B:39:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x0072  */
    /* JADX WARN: Code duplicated, block: B:42:0x0075  */
    /* JADX WARN: Code duplicated, block: B:47:0x007f  */
    /* JADX WARN: Code duplicated, block: B:49:0x0083  */
    /* JADX WARN: Code duplicated, block: B:51:0x008b  */
    /* JADX WARN: Code duplicated, block: B:52:0x008e  */
    /* JADX WARN: Code duplicated, block: B:55:0x0094  */
    /* JADX WARN: Code duplicated, block: B:58:0x009c  */
    /* JADX WARN: Code duplicated, block: B:59:0x009e  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e8 A[PHI: r3 r8 r10 r11
      0x00e8: PHI (r3v24 int) = (r3v20 int), (r3v19 int), (r3v25 int) binds: [B:91:0x0105, B:80:0x00e4, B:81:0x00e6] A[DONT_GENERATE, DONT_INLINE]
      0x00e8: PHI (r8v7 androidx.compose.ui.Modifier) = (r8v3 androidx.compose.ui.Modifier), (r8v2 androidx.compose.ui.Modifier), (r8v2 androidx.compose.ui.Modifier) binds: [B:91:0x0105, B:80:0x00e4, B:81:0x00e6] A[DONT_GENERATE, DONT_INLINE]
      0x00e8: PHI (r10v7 boolean) = (r10v3 boolean), (r10v2 boolean), (r10v2 boolean) binds: [B:91:0x0105, B:80:0x00e4, B:81:0x00e6] A[DONT_GENERATE, DONT_INLINE]
      0x00e8: PHI (r11v10 androidx.compose.material3.CheckboxColors) = 
      (r11v7 androidx.compose.material3.CheckboxColors)
      (r11v6 androidx.compose.material3.CheckboxColors)
      (r11v6 androidx.compose.material3.CheckboxColors)
     binds: [B:91:0x0105, B:80:0x00e4, B:81:0x00e6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:84:0x00ec A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:85:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:90:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:92:0x0107  */
    /* JADX WARN: Code duplicated, block: B:95:0x0113  */
    /* JADX WARN: Code duplicated, block: B:98:0x012a  */
    /* JADX WARN: Instruction removed from duplicated block: B:74:0x00cb, please report this as an issue */
    public static final void Checkbox(final boolean z, final Function1<? super Boolean, Unit> function1, Modifier modifier, boolean z2, CheckboxColors checkboxColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        boolean z3;
        int i5;
        CheckboxColors checkboxColorsColors;
        int i6;
        MutableInteractionSource mutableInteractionSource2;
        int i7;
        Function0 function0;
        CheckboxColors checkboxColors2;
        final Modifier modifier3;
        final boolean z4;
        final CheckboxColors checkboxColors3;
        boolean z5;
        boolean z6;
        Object objRememberedValue;
        final MutableInteractionSource mutableInteractionSource3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1406741137);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Checkbox)P(!1,5,4,2)96@4296L8,99@4370L356:Checkbox.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i8 = i2 & 4;
        if (i8 == 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z3 = z2;
                    if (composerStartRestartGroup.changed(z3)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        checkboxColorsColors = checkboxColors;
                        int i9 = composerStartRestartGroup.changed(checkboxColorsColors) ? 16384 : 8192;
                        i3 |= i9;
                    } else {
                        checkboxColorsColors = checkboxColors;
                    }
                    i3 |= i9;
                } else {
                    checkboxColorsColors = checkboxColors;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    if ((74899 & i3) == 74898 || !composerStartRestartGroup.getSkipping()) {
                        composerStartRestartGroup.startDefaults();
                        function0 = null;
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i8 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                checkboxColors2 = checkboxColorsColors;
                                mutableInteractionSource2 = null;
                            }
                            boolean z7 = z3;
                            Modifier modifier4 = modifier2;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                            }
                            ToggleableState ToggleableState = ToggleableStateKt.ToggleableState(z);
                            composerStartRestartGroup.startReplaceGroup(1046936362);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                            if (function1 != null) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                                if ((i3 & 112) == 32) {
                                    z5 = true;
                                } else {
                                    z5 = false;
                                }
                                z6 = z5 | ((i3 & 14) == 4);
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (!z6 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            function1.invoke(Boolean.valueOf(!z));
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function0 = (Function0) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            TriStateCheckbox(ToggleableState, function0, modifier4, z7, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            z4 = z7;
                            checkboxColors3 = checkboxColors2;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                        }
                        checkboxColors2 = checkboxColorsColors;
                        boolean z8 = z3;
                        Modifier modifier5 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                        }
                        ToggleableState ToggleableState2 = ToggleableStateKt.ToggleableState(z);
                        composerStartRestartGroup.startReplaceGroup(1046936362);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                        if (function1 != null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                            if ((i3 & 112) == 32) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            z6 = z5 | ((i3 & 14) == 4);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (!z6) {
                                objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        function1.invoke(Boolean.valueOf(!z));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            } else {
                                objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        function1.invoke(Boolean.valueOf(!z));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function0 = (Function0) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        TriStateCheckbox(ToggleableState2, function0, modifier5, z8, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier5;
                        z4 = z8;
                        checkboxColors3 = checkboxColors2;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        z4 = z3;
                        checkboxColors3 = checkboxColorsColors;
                    }
                    mutableInteractionSource3 = mutableInteractionSource2;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.Checkbox.2
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

                            public final void invoke(Composer composer2, int i10) {
                                CheckboxKt.Checkbox(z, function1, modifier3, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((74899 & i3) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    function0 = null;
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = null;
                        } else {
                            checkboxColors2 = checkboxColorsColors;
                        }
                    } else {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = null;
                        } else {
                            checkboxColors2 = checkboxColorsColors;
                        }
                    }
                    boolean z9 = z3;
                    Modifier modifier6 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                    }
                    ToggleableState ToggleableState3 = ToggleableStateKt.ToggleableState(z);
                    composerStartRestartGroup.startReplaceGroup(1046936362);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                    if (function1 != null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                        if ((i3 & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z5 | ((i3 & 14) == 4);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function1.invoke(Boolean.valueOf(!z));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function1.invoke(Boolean.valueOf(!z));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function0 = (Function0) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    TriStateCheckbox(ToggleableState3, function0, modifier6, z9, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier6;
                    z4 = z9;
                    checkboxColors3 = checkboxColors2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    function0 = null;
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = null;
                        } else {
                            checkboxColors2 = checkboxColorsColors;
                        }
                    } else {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = null;
                        } else {
                            checkboxColors2 = checkboxColorsColors;
                        }
                    }
                    boolean z10 = z3;
                    Modifier modifier7 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                    }
                    ToggleableState ToggleableState4 = ToggleableStateKt.ToggleableState(z);
                    composerStartRestartGroup.startReplaceGroup(1046936362);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                    if (function1 != null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                        if ((i3 & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z5 | ((i3 & 14) == 4);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function1.invoke(Boolean.valueOf(!z));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function1.invoke(Boolean.valueOf(!z));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function0 = (Function0) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    TriStateCheckbox(ToggleableState4, function0, modifier7, z10, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier7;
                    z4 = z10;
                    checkboxColors3 = checkboxColors2;
                }
                mutableInteractionSource3 = mutableInteractionSource2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.Checkbox.2
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

                        public final void invoke(Composer composer2, int i10) {
                            CheckboxKt.Checkbox(z, function1, modifier3, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            z3 = z2;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    checkboxColorsColors = checkboxColors;
                    if (composerStartRestartGroup.changed(checkboxColorsColors)) {
                    }
                    i3 |= i9;
                } else {
                    checkboxColorsColors = checkboxColors;
                }
                i3 |= i9;
            } else {
                checkboxColorsColors = checkboxColors;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((74899 & i3) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    function0 = null;
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = null;
                        } else {
                            checkboxColors2 = checkboxColorsColors;
                        }
                    } else {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = null;
                        } else {
                            checkboxColors2 = checkboxColorsColors;
                        }
                    }
                    boolean z11 = z3;
                    Modifier modifier8 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                    }
                    ToggleableState ToggleableState5 = ToggleableStateKt.ToggleableState(z);
                    composerStartRestartGroup.startReplaceGroup(1046936362);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                    if (function1 != null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                        if ((i3 & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z5 | ((i3 & 14) == 4);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function1.invoke(Boolean.valueOf(!z));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function1.invoke(Boolean.valueOf(!z));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function0 = (Function0) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    TriStateCheckbox(ToggleableState5, function0, modifier8, z11, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier8;
                    z4 = z11;
                    checkboxColors3 = checkboxColors2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    function0 = null;
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = null;
                        } else {
                            checkboxColors2 = checkboxColorsColors;
                        }
                    } else {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = null;
                        } else {
                            checkboxColors2 = checkboxColorsColors;
                        }
                    }
                    boolean z12 = z3;
                    Modifier modifier9 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                    }
                    ToggleableState ToggleableState6 = ToggleableStateKt.ToggleableState(z);
                    composerStartRestartGroup.startReplaceGroup(1046936362);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                    if (function1 != null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                        if ((i3 & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z5 | ((i3 & 14) == 4);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function1.invoke(Boolean.valueOf(!z));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function1.invoke(Boolean.valueOf(!z));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function0 = (Function0) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    TriStateCheckbox(ToggleableState6, function0, modifier9, z12, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier9;
                    z4 = z12;
                    checkboxColors3 = checkboxColors2;
                }
                mutableInteractionSource3 = mutableInteractionSource2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.Checkbox.2
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

                        public final void invoke(Composer composer2, int i10) {
                            CheckboxKt.Checkbox(z, function1, modifier3, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((74899 & i3) == 74898) {
                composerStartRestartGroup.startDefaults();
                function0 = null;
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = null;
                    } else {
                        checkboxColors2 = checkboxColorsColors;
                    }
                } else {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = null;
                    } else {
                        checkboxColors2 = checkboxColorsColors;
                    }
                }
                boolean z13 = z3;
                Modifier modifier10 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                }
                ToggleableState ToggleableState7 = ToggleableStateKt.ToggleableState(z);
                composerStartRestartGroup.startReplaceGroup(1046936362);
                ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                if (function1 != null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                    if ((i3 & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z5 | ((i3 & 14) == 4);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke(Boolean.valueOf(!z));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke(Boolean.valueOf(!z));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function0 = (Function0) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                }
                composerStartRestartGroup.endReplaceGroup();
                TriStateCheckbox(ToggleableState7, function0, modifier10, z13, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier10;
                z4 = z13;
                checkboxColors3 = checkboxColors2;
            } else {
                composerStartRestartGroup.startDefaults();
                function0 = null;
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = null;
                    } else {
                        checkboxColors2 = checkboxColorsColors;
                    }
                } else {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = null;
                    } else {
                        checkboxColors2 = checkboxColorsColors;
                    }
                }
                boolean z14 = z3;
                Modifier modifier11 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                }
                ToggleableState ToggleableState8 = ToggleableStateKt.ToggleableState(z);
                composerStartRestartGroup.startReplaceGroup(1046936362);
                ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                if (function1 != null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                    if ((i3 & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z5 | ((i3 & 14) == 4);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke(Boolean.valueOf(!z));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke(Boolean.valueOf(!z));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function0 = (Function0) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                }
                composerStartRestartGroup.endReplaceGroup();
                TriStateCheckbox(ToggleableState8, function0, modifier11, z14, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier11;
                z4 = z14;
                checkboxColors3 = checkboxColors2;
            }
            mutableInteractionSource3 = mutableInteractionSource2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.Checkbox.2
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

                    public final void invoke(Composer composer2, int i10) {
                        CheckboxKt.Checkbox(z, function1, modifier3, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z3 = z2;
                if (composerStartRestartGroup.changed(z3)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    checkboxColorsColors = checkboxColors;
                    if (composerStartRestartGroup.changed(checkboxColorsColors)) {
                    }
                    i3 |= i9;
                } else {
                    checkboxColorsColors = checkboxColors;
                }
                i3 |= i9;
            } else {
                checkboxColorsColors = checkboxColors;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((74899 & i3) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    function0 = null;
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = null;
                        } else {
                            checkboxColors2 = checkboxColorsColors;
                        }
                    } else {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = null;
                        } else {
                            checkboxColors2 = checkboxColorsColors;
                        }
                    }
                    boolean z15 = z3;
                    Modifier modifier12 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                    }
                    ToggleableState ToggleableState9 = ToggleableStateKt.ToggleableState(z);
                    composerStartRestartGroup.startReplaceGroup(1046936362);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                    if (function1 != null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                        if ((i3 & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z5 | ((i3 & 14) == 4);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function1.invoke(Boolean.valueOf(!z));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function1.invoke(Boolean.valueOf(!z));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function0 = (Function0) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    TriStateCheckbox(ToggleableState9, function0, modifier12, z15, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier12;
                    z4 = z15;
                    checkboxColors3 = checkboxColors2;
                } else {
                    composerStartRestartGroup.startDefaults();
                    function0 = null;
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = null;
                        } else {
                            checkboxColors2 = checkboxColorsColors;
                        }
                    } else {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = null;
                        } else {
                            checkboxColors2 = checkboxColorsColors;
                        }
                    }
                    boolean z16 = z3;
                    Modifier modifier13 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                    }
                    ToggleableState ToggleableState10 = ToggleableStateKt.ToggleableState(z);
                    composerStartRestartGroup.startReplaceGroup(1046936362);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                    if (function1 != null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                        if ((i3 & 112) == 32) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z5 | ((i3 & 14) == 4);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function1.invoke(Boolean.valueOf(!z));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    function1.invoke(Boolean.valueOf(!z));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function0 = (Function0) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    TriStateCheckbox(ToggleableState10, function0, modifier13, z16, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier13;
                    z4 = z16;
                    checkboxColors3 = checkboxColors2;
                }
                mutableInteractionSource3 = mutableInteractionSource2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.Checkbox.2
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

                        public final void invoke(Composer composer2, int i10) {
                            CheckboxKt.Checkbox(z, function1, modifier3, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((74899 & i3) == 74898) {
                composerStartRestartGroup.startDefaults();
                function0 = null;
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = null;
                    } else {
                        checkboxColors2 = checkboxColorsColors;
                    }
                } else {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = null;
                    } else {
                        checkboxColors2 = checkboxColorsColors;
                    }
                }
                boolean z17 = z3;
                Modifier modifier14 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                }
                ToggleableState ToggleableState11 = ToggleableStateKt.ToggleableState(z);
                composerStartRestartGroup.startReplaceGroup(1046936362);
                ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                if (function1 != null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                    if ((i3 & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z5 | ((i3 & 14) == 4);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke(Boolean.valueOf(!z));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke(Boolean.valueOf(!z));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function0 = (Function0) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                }
                composerStartRestartGroup.endReplaceGroup();
                TriStateCheckbox(ToggleableState11, function0, modifier14, z17, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier14;
                z4 = z17;
                checkboxColors3 = checkboxColors2;
            } else {
                composerStartRestartGroup.startDefaults();
                function0 = null;
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = null;
                    } else {
                        checkboxColors2 = checkboxColorsColors;
                    }
                } else {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = null;
                    } else {
                        checkboxColors2 = checkboxColorsColors;
                    }
                }
                boolean z18 = z3;
                Modifier modifier15 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                }
                ToggleableState ToggleableState12 = ToggleableStateKt.ToggleableState(z);
                composerStartRestartGroup.startReplaceGroup(1046936362);
                ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                if (function1 != null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                    if ((i3 & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z5 | ((i3 & 14) == 4);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke(Boolean.valueOf(!z));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke(Boolean.valueOf(!z));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function0 = (Function0) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                }
                composerStartRestartGroup.endReplaceGroup();
                TriStateCheckbox(ToggleableState12, function0, modifier15, z18, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier15;
                z4 = z18;
                checkboxColors3 = checkboxColors2;
            }
            mutableInteractionSource3 = mutableInteractionSource2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.Checkbox.2
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

                    public final void invoke(Composer composer2, int i10) {
                        CheckboxKt.Checkbox(z, function1, modifier3, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        z3 = z2;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                checkboxColorsColors = checkboxColors;
                if (composerStartRestartGroup.changed(checkboxColorsColors)) {
                }
                i3 |= i9;
            } else {
                checkboxColorsColors = checkboxColors;
            }
            i3 |= i9;
        } else {
            checkboxColorsColors = checkboxColors;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                mutableInteractionSource2 = mutableInteractionSource;
                if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            if ((74899 & i3) == 74898) {
                composerStartRestartGroup.startDefaults();
                function0 = null;
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = null;
                    } else {
                        checkboxColors2 = checkboxColorsColors;
                    }
                } else {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = null;
                    } else {
                        checkboxColors2 = checkboxColorsColors;
                    }
                }
                boolean z19 = z3;
                Modifier modifier16 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                }
                ToggleableState ToggleableState13 = ToggleableStateKt.ToggleableState(z);
                composerStartRestartGroup.startReplaceGroup(1046936362);
                ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                if (function1 != null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                    if ((i3 & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z5 | ((i3 & 14) == 4);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke(Boolean.valueOf(!z));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke(Boolean.valueOf(!z));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function0 = (Function0) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                }
                composerStartRestartGroup.endReplaceGroup();
                TriStateCheckbox(ToggleableState13, function0, modifier16, z19, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier16;
                z4 = z19;
                checkboxColors3 = checkboxColors2;
            } else {
                composerStartRestartGroup.startDefaults();
                function0 = null;
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = null;
                    } else {
                        checkboxColors2 = checkboxColorsColors;
                    }
                } else {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = null;
                    } else {
                        checkboxColors2 = checkboxColorsColors;
                    }
                }
                boolean z110 = z3;
                Modifier modifier17 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                }
                ToggleableState ToggleableState14 = ToggleableStateKt.ToggleableState(z);
                composerStartRestartGroup.startReplaceGroup(1046936362);
                ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
                if (function1 != null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                    if ((i3 & 112) == 32) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z5 | ((i3 & 14) == 4);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke(Boolean.valueOf(!z));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function1.invoke(Boolean.valueOf(!z));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function0 = (Function0) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                }
                composerStartRestartGroup.endReplaceGroup();
                TriStateCheckbox(ToggleableState14, function0, modifier17, z110, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier17;
                z4 = z110;
                checkboxColors3 = checkboxColors2;
            }
            mutableInteractionSource3 = mutableInteractionSource2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.Checkbox.2
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

                    public final void invoke(Composer composer2, int i10) {
                        CheckboxKt.Checkbox(z, function1, modifier3, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        mutableInteractionSource2 = mutableInteractionSource;
        if ((74899 & i3) == 74898) {
            composerStartRestartGroup.startDefaults();
            function0 = null;
            if ((i & 1) != 0) {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z3 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i6 != 0) {
                    checkboxColors2 = checkboxColorsColors;
                    mutableInteractionSource2 = null;
                } else {
                    checkboxColors2 = checkboxColorsColors;
                }
            } else {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z3 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i6 != 0) {
                    checkboxColors2 = checkboxColorsColors;
                    mutableInteractionSource2 = null;
                } else {
                    checkboxColors2 = checkboxColorsColors;
                }
            }
            boolean z111 = z3;
            Modifier modifier18 = modifier2;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
            }
            ToggleableState ToggleableState15 = ToggleableStateKt.ToggleableState(z);
            composerStartRestartGroup.startReplaceGroup(1046936362);
            ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
            if (function1 != null) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                if ((i3 & 112) == 32) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                z6 = z5 | ((i3 & 14) == 4);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z6) {
                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function1.invoke(Boolean.valueOf(!z));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function1.invoke(Boolean.valueOf(!z));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                function0 = (Function0) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            }
            composerStartRestartGroup.endReplaceGroup();
            TriStateCheckbox(ToggleableState15, function0, modifier18, z111, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier18;
            z4 = z111;
            checkboxColors3 = checkboxColors2;
        } else {
            composerStartRestartGroup.startDefaults();
            function0 = null;
            if ((i & 1) != 0) {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z3 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i6 != 0) {
                    checkboxColors2 = checkboxColorsColors;
                    mutableInteractionSource2 = null;
                } else {
                    checkboxColors2 = checkboxColorsColors;
                }
            } else {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z3 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i6 != 0) {
                    checkboxColors2 = checkboxColorsColors;
                    mutableInteractionSource2 = null;
                } else {
                    checkboxColors2 = checkboxColorsColors;
                }
            }
            boolean z112 = z3;
            Modifier modifier19 = modifier2;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
            }
            ToggleableState ToggleableState16 = ToggleableStateKt.ToggleableState(z);
            composerStartRestartGroup.startReplaceGroup(1046936362);
            ComposerKt.sourceInformation(composerStartRestartGroup, "103@4507L29");
            if (function1 != null) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                if ((i3 & 112) == 32) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                z6 = z5 | ((i3 & 14) == 4);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z6) {
                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function1.invoke(Boolean.valueOf(!z));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function1.invoke(Boolean.valueOf(!z));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                function0 = (Function0) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            }
            composerStartRestartGroup.endReplaceGroup();
            TriStateCheckbox(ToggleableState16, function0, modifier19, z112, checkboxColors2, mutableInteractionSource2, composerStartRestartGroup, i3 & 524160, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier19;
            z4 = z112;
            checkboxColors3 = checkboxColors2;
        }
        mutableInteractionSource3 = mutableInteractionSource2;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.Checkbox.2
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

                public final void invoke(Composer composer2, int i10) {
                    CheckboxKt.Checkbox(z, function1, modifier3, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x012e  */
    /* JADX WARN: Code duplicated, block: B:101:0x015e  */
    /* JADX WARN: Code duplicated, block: B:104:0x016a  */
    /* JADX WARN: Code duplicated, block: B:105:0x0173  */
    /* JADX WARN: Code duplicated, block: B:108:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:112:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0067  */
    /* JADX WARN: Code duplicated, block: B:37:0x006a  */
    /* JADX WARN: Code duplicated, block: B:39:0x006e  */
    /* JADX WARN: Code duplicated, block: B:41:0x0076  */
    /* JADX WARN: Code duplicated, block: B:42:0x0079  */
    /* JADX WARN: Code duplicated, block: B:47:0x0083  */
    /* JADX WARN: Code duplicated, block: B:49:0x0087  */
    /* JADX WARN: Code duplicated, block: B:51:0x008f  */
    /* JADX WARN: Code duplicated, block: B:52:0x0092  */
    /* JADX WARN: Code duplicated, block: B:55:0x0098  */
    /* JADX WARN: Code duplicated, block: B:58:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:74:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:84:0x00ee A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:85:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:91:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:93:0x0109  */
    /* JADX WARN: Code duplicated, block: B:94:0x0110  */
    /* JADX WARN: Code duplicated, block: B:97:0x011b  */
    public static final void TriStateCheckbox(final ToggleableState toggleableState, final Function0<Unit> function0, Modifier modifier, boolean z, CheckboxColors checkboxColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        ToggleableState toggleableState2;
        int i3;
        Modifier modifier2;
        int i4;
        boolean z2;
        int i5;
        CheckboxColors checkboxColorsColors;
        int i6;
        MutableInteractionSource mutableInteractionSource2;
        int i7;
        Modifier.Companion companion;
        Modifier modifier3;
        boolean z3;
        CheckboxColors checkboxColors2;
        Modifier.Companion companionM987triStateToggleableO2vRcR0;
        Modifier.Companion companionMinimumInteractiveComponentSize;
        final CheckboxColors checkboxColors3;
        final Modifier modifier4;
        final boolean z4;
        final MutableInteractionSource mutableInteractionSource3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1608358065);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(TriStateCheckbox)P(5,4,3,1)149@6731L8,169@7373L460:Checkbox.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            toggleableState2 = toggleableState;
        } else if ((i & 6) == 0) {
            toggleableState2 = toggleableState;
            i3 = (composerStartRestartGroup.changed(toggleableState2) ? 4 : 2) | i;
        } else {
            toggleableState2 = toggleableState;
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        int i8 = i2 & 4;
        if (i8 == 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        checkboxColorsColors = checkboxColors;
                        int i9 = composerStartRestartGroup.changed(checkboxColorsColors) ? 16384 : 8192;
                        i3 |= i9;
                    } else {
                        checkboxColorsColors = checkboxColors;
                    }
                    i3 |= i9;
                } else {
                    checkboxColorsColors = checkboxColors;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    if ((74899 & i3) == 74898 || !composerStartRestartGroup.getSkipping()) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i8 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i6 != 0) {
                                modifier3 = companion;
                                mutableInteractionSource2 = null;
                                z3 = z2;
                                checkboxColors2 = checkboxColorsColors;
                            } else {
                                modifier3 = companion;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                            }
                            composerStartRestartGroup.startReplaceGroup(-97239746);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                            if (function0 != null) {
                                companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                            } else {
                                companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            if (function0 != null) {
                                companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                            } else {
                                companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                            }
                            CheckboxColors checkboxColors4 = checkboxColors2;
                            CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors4, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            checkboxColors3 = checkboxColors4;
                            modifier4 = modifier3;
                            z4 = z3;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                            modifier3 = modifier2;
                        }
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                        }
                        composerStartRestartGroup.startReplaceGroup(-97239746);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                        if (function0 != null) {
                            companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                        } else {
                            companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        if (function0 != null) {
                            companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                        } else {
                            companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                        }
                        CheckboxColors checkboxColors5 = checkboxColors2;
                        CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors5, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        checkboxColors3 = checkboxColors5;
                        modifier4 = modifier3;
                        z4 = z3;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier4 = modifier2;
                        z4 = z2;
                        checkboxColors3 = checkboxColorsColors;
                    }
                    mutableInteractionSource3 = mutableInteractionSource2;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.TriStateCheckbox.1
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
                                CheckboxKt.TriStateCheckbox(toggleableState, function0, modifier4, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((74899 & i3) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            modifier3 = companion;
                            mutableInteractionSource2 = null;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                        } else {
                            modifier3 = companion;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource2;
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            modifier3 = companion;
                            mutableInteractionSource2 = null;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                        } else {
                            modifier3 = companion;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                    }
                    composerStartRestartGroup.startReplaceGroup(-97239746);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                    if (function0 != null) {
                        companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                    } else {
                        companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function0 != null) {
                        companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    CheckboxColors checkboxColors6 = checkboxColors2;
                    CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors6, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    checkboxColors3 = checkboxColors6;
                    modifier4 = modifier3;
                    z4 = z3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            modifier3 = companion;
                            mutableInteractionSource2 = null;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                        } else {
                            modifier3 = companion;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource2;
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            modifier3 = companion;
                            mutableInteractionSource2 = null;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                        } else {
                            modifier3 = companion;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                    }
                    composerStartRestartGroup.startReplaceGroup(-97239746);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                    if (function0 != null) {
                        companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                    } else {
                        companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function0 != null) {
                        companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    CheckboxColors checkboxColors7 = checkboxColors2;
                    CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors7, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    checkboxColors3 = checkboxColors7;
                    modifier4 = modifier3;
                    z4 = z3;
                }
                mutableInteractionSource3 = mutableInteractionSource2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.TriStateCheckbox.1
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
                            CheckboxKt.TriStateCheckbox(toggleableState, function0, modifier4, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    checkboxColorsColors = checkboxColors;
                    if (composerStartRestartGroup.changed(checkboxColorsColors)) {
                    }
                    i3 |= i9;
                } else {
                    checkboxColorsColors = checkboxColors;
                }
                i3 |= i9;
            } else {
                checkboxColorsColors = checkboxColors;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((74899 & i3) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            modifier3 = companion;
                            mutableInteractionSource2 = null;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                        } else {
                            modifier3 = companion;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource2;
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            modifier3 = companion;
                            mutableInteractionSource2 = null;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                        } else {
                            modifier3 = companion;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                    }
                    composerStartRestartGroup.startReplaceGroup(-97239746);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                    if (function0 != null) {
                        companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                    } else {
                        companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function0 != null) {
                        companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    CheckboxColors checkboxColors8 = checkboxColors2;
                    CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors8, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    checkboxColors3 = checkboxColors8;
                    modifier4 = modifier3;
                    z4 = z3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            modifier3 = companion;
                            mutableInteractionSource2 = null;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                        } else {
                            modifier3 = companion;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource2;
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            modifier3 = companion;
                            mutableInteractionSource2 = null;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                        } else {
                            modifier3 = companion;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                    }
                    composerStartRestartGroup.startReplaceGroup(-97239746);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                    if (function0 != null) {
                        companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                    } else {
                        companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function0 != null) {
                        companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    CheckboxColors checkboxColors9 = checkboxColors2;
                    CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors9, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    checkboxColors3 = checkboxColors9;
                    modifier4 = modifier3;
                    z4 = z3;
                }
                mutableInteractionSource3 = mutableInteractionSource2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.TriStateCheckbox.1
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
                            CheckboxKt.TriStateCheckbox(toggleableState, function0, modifier4, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((74899 & i3) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        modifier3 = companion;
                        mutableInteractionSource2 = null;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                    } else {
                        modifier3 = companion;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource2;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        modifier3 = companion;
                        mutableInteractionSource2 = null;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                    } else {
                        modifier3 = companion;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                }
                composerStartRestartGroup.startReplaceGroup(-97239746);
                ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                if (function0 != null) {
                    companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                } else {
                    companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function0 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                CheckboxColors checkboxColors10 = checkboxColors2;
                CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors10, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                checkboxColors3 = checkboxColors10;
                modifier4 = modifier3;
                z4 = z3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        modifier3 = companion;
                        mutableInteractionSource2 = null;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                    } else {
                        modifier3 = companion;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource2;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        modifier3 = companion;
                        mutableInteractionSource2 = null;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                    } else {
                        modifier3 = companion;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                }
                composerStartRestartGroup.startReplaceGroup(-97239746);
                ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                if (function0 != null) {
                    companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                } else {
                    companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function0 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                CheckboxColors checkboxColors11 = checkboxColors2;
                CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors11, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                checkboxColors3 = checkboxColors11;
                modifier4 = modifier3;
                z4 = z3;
            }
            mutableInteractionSource3 = mutableInteractionSource2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.TriStateCheckbox.1
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
                        CheckboxKt.TriStateCheckbox(toggleableState, function0, modifier4, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    checkboxColorsColors = checkboxColors;
                    if (composerStartRestartGroup.changed(checkboxColorsColors)) {
                    }
                    i3 |= i9;
                } else {
                    checkboxColorsColors = checkboxColors;
                }
                i3 |= i9;
            } else {
                checkboxColorsColors = checkboxColors;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((74899 & i3) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            modifier3 = companion;
                            mutableInteractionSource2 = null;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                        } else {
                            modifier3 = companion;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource2;
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            modifier3 = companion;
                            mutableInteractionSource2 = null;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                        } else {
                            modifier3 = companion;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                    }
                    composerStartRestartGroup.startReplaceGroup(-97239746);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                    if (function0 != null) {
                        companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                    } else {
                        companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function0 != null) {
                        companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    CheckboxColors checkboxColors12 = checkboxColors2;
                    CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors12, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    checkboxColors3 = checkboxColors12;
                    modifier4 = modifier3;
                    z4 = z3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            modifier3 = companion;
                            mutableInteractionSource2 = null;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                        } else {
                            modifier3 = companion;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource2;
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i6 != 0) {
                            modifier3 = companion;
                            mutableInteractionSource2 = null;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                        } else {
                            modifier3 = companion;
                            z3 = z2;
                            checkboxColors2 = checkboxColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                    }
                    composerStartRestartGroup.startReplaceGroup(-97239746);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                    if (function0 != null) {
                        companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                    } else {
                        companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function0 != null) {
                        companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    CheckboxColors checkboxColors13 = checkboxColors2;
                    CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors13, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    checkboxColors3 = checkboxColors13;
                    modifier4 = modifier3;
                    z4 = z3;
                }
                mutableInteractionSource3 = mutableInteractionSource2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.TriStateCheckbox.1
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
                            CheckboxKt.TriStateCheckbox(toggleableState, function0, modifier4, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((74899 & i3) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        modifier3 = companion;
                        mutableInteractionSource2 = null;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                    } else {
                        modifier3 = companion;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource2;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        modifier3 = companion;
                        mutableInteractionSource2 = null;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                    } else {
                        modifier3 = companion;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                }
                composerStartRestartGroup.startReplaceGroup(-97239746);
                ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                if (function0 != null) {
                    companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                } else {
                    companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function0 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                CheckboxColors checkboxColors14 = checkboxColors2;
                CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors14, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                checkboxColors3 = checkboxColors14;
                modifier4 = modifier3;
                z4 = z3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        modifier3 = companion;
                        mutableInteractionSource2 = null;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                    } else {
                        modifier3 = companion;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource2;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        modifier3 = companion;
                        mutableInteractionSource2 = null;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                    } else {
                        modifier3 = companion;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                }
                composerStartRestartGroup.startReplaceGroup(-97239746);
                ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                if (function0 != null) {
                    companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                } else {
                    companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function0 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                CheckboxColors checkboxColors15 = checkboxColors2;
                CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors15, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                checkboxColors3 = checkboxColors15;
                modifier4 = modifier3;
                z4 = z3;
            }
            mutableInteractionSource3 = mutableInteractionSource2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.TriStateCheckbox.1
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
                        CheckboxKt.TriStateCheckbox(toggleableState, function0, modifier4, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                checkboxColorsColors = checkboxColors;
                if (composerStartRestartGroup.changed(checkboxColorsColors)) {
                }
                i3 |= i9;
            } else {
                checkboxColorsColors = checkboxColors;
            }
            i3 |= i9;
        } else {
            checkboxColorsColors = checkboxColors;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                mutableInteractionSource2 = mutableInteractionSource;
                if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            if ((74899 & i3) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        modifier3 = companion;
                        mutableInteractionSource2 = null;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                    } else {
                        modifier3 = companion;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource2;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        modifier3 = companion;
                        mutableInteractionSource2 = null;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                    } else {
                        modifier3 = companion;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                }
                composerStartRestartGroup.startReplaceGroup(-97239746);
                ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                if (function0 != null) {
                    companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                } else {
                    companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function0 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                CheckboxColors checkboxColors16 = checkboxColors2;
                CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors16, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                checkboxColors3 = checkboxColors16;
                modifier4 = modifier3;
                z4 = z3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        modifier3 = companion;
                        mutableInteractionSource2 = null;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                    } else {
                        modifier3 = companion;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource2;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i6 != 0) {
                        modifier3 = companion;
                        mutableInteractionSource2 = null;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                    } else {
                        modifier3 = companion;
                        z3 = z2;
                        checkboxColors2 = checkboxColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                }
                composerStartRestartGroup.startReplaceGroup(-97239746);
                ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
                if (function0 != null) {
                    companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
                } else {
                    companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function0 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                CheckboxColors checkboxColors17 = checkboxColors2;
                CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors17, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                checkboxColors3 = checkboxColors17;
                modifier4 = modifier3;
                z4 = z3;
            }
            mutableInteractionSource3 = mutableInteractionSource2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.TriStateCheckbox.1
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
                        CheckboxKt.TriStateCheckbox(toggleableState, function0, modifier4, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        mutableInteractionSource2 = mutableInteractionSource;
        if ((74899 & i3) == 74898) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i6 != 0) {
                    modifier3 = companion;
                    mutableInteractionSource2 = null;
                    z3 = z2;
                    checkboxColors2 = checkboxColorsColors;
                } else {
                    modifier3 = companion;
                    z3 = z2;
                    checkboxColors2 = checkboxColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource2;
                }
            } else {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i6 != 0) {
                    modifier3 = companion;
                    mutableInteractionSource2 = null;
                    z3 = z2;
                    checkboxColors2 = checkboxColorsColors;
                } else {
                    modifier3 = companion;
                    z3 = z2;
                    checkboxColors2 = checkboxColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
            }
            composerStartRestartGroup.startReplaceGroup(-97239746);
            ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
            if (function0 != null) {
                companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
            } else {
                companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
            }
            composerStartRestartGroup.endReplaceGroup();
            if (function0 != null) {
                companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
            } else {
                companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
            }
            CheckboxColors checkboxColors18 = checkboxColors2;
            CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors18, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            checkboxColors3 = checkboxColors18;
            modifier4 = modifier3;
            z4 = z3;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i6 != 0) {
                    modifier3 = companion;
                    mutableInteractionSource2 = null;
                    z3 = z2;
                    checkboxColors2 = checkboxColorsColors;
                } else {
                    modifier3 = companion;
                    z3 = z2;
                    checkboxColors2 = checkboxColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource2;
                }
            } else {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    checkboxColorsColors = CheckboxDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i6 != 0) {
                    modifier3 = companion;
                    mutableInteractionSource2 = null;
                    z3 = z2;
                    checkboxColors2 = checkboxColorsColors;
                } else {
                    modifier3 = companion;
                    z3 = z2;
                    checkboxColors2 = checkboxColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1608358065, i3, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
            }
            composerStartRestartGroup.startReplaceGroup(-97239746);
            ComposerKt.sourceInformation(composerStartRestartGroup, "161@7145L161");
            if (function0 != null) {
                companionM987triStateToggleableO2vRcR0 = ToggleableKt.m987triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource2, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(CheckboxTokens.INSTANCE.m2777getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z3, Role.m6108boximpl(Role.INSTANCE.m6117getCheckboxo7Vup1c()), function0);
            } else {
                companionM987triStateToggleableO2vRcR0 = Modifier.INSTANCE;
            }
            composerStartRestartGroup.endReplaceGroup();
            if (function0 != null) {
                companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
            } else {
                companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
            }
            CheckboxColors checkboxColors19 = checkboxColors2;
            CheckboxImpl(z3, toggleableState, PaddingKt.m720padding3ABfNKs(modifier3.then(companionMinimumInteractiveComponentSize).then(companionM987triStateToggleableO2vRcR0), CheckboxDefaultPadding), checkboxColors19, composerStartRestartGroup, ((i3 >> 9) & 14) | ((i3 << 3) & 112) | ((i3 >> 3) & 7168));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            checkboxColors3 = checkboxColors19;
            modifier4 = modifier3;
            z4 = z3;
        }
        mutableInteractionSource3 = mutableInteractionSource2;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.TriStateCheckbox.1
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
                    CheckboxKt.TriStateCheckbox(toggleableState, function0, modifier4, z4, checkboxColors3, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:100:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:101:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:105:0x0204  */
    /* JADX WARN: Code duplicated, block: B:108:0x0243  */
    /* JADX WARN: Code duplicated, block: B:113:0x02b4  */
    /* JADX WARN: Code duplicated, block: B:116:0x02d9  */
    /* JADX WARN: Code duplicated, block: B:74:0x0135  */
    /* JADX WARN: Code duplicated, block: B:77:0x019c  */
    /* JADX WARN: Code duplicated, block: B:78:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:88:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:91:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:94:0x01e2  */
    public static final void CheckboxImpl(final boolean z, final ToggleableState toggleableState, final Modifier modifier, final CheckboxColors checkboxColors, Composer composer, final int i) {
        int i2;
        float f;
        char c;
        float f2;
        final State stateCreateTransitionAnimation;
        int i3;
        int i4;
        int i5;
        float f3;
        int i6;
        final State stateCreateTransitionAnimation2;
        Object objRememberedValue;
        final CheckDrawingCache checkDrawingCache;
        final State<Color> stateCheckmarkColor$material3_release;
        final State<Color> stateBoxColor$material3_release;
        final State<Color> stateBorderColor$material3_release;
        boolean zChanged;
        Object objRememberedValue2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2007131616);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CheckboxImpl)P(1,3,2)272@12420L23,274@12491L499,291@13057L514,306@13593L32,307@13654L21,308@13702L24,309@13756L27,310@13866L538,310@13788L616:Checkbox.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(toggleableState) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(checkboxColors) ? 2048 : 1024;
        }
        int i7 = i2;
        if ((i7 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2007131616, i7, -1, "androidx.compose.material3.CheckboxImpl (Checkbox.kt:271)");
            }
            int i8 = i7 >> 3;
            int i9 = i8 & 14;
            Transition transitionUpdateTransition = TransitionKt.updateTransition(toggleableState, (String) null, composerStartRestartGroup, i9, 2);
            CheckboxKt$CheckboxImpl$checkDrawFraction$1 checkboxKt$CheckboxImpl$checkDrawFraction$1 = new Function3<Transition.Segment<ToggleableState>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$checkDrawFraction$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, Integer num) {
                    return invoke(segment, composer2, num.intValue());
                }

                public final FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, int i10) {
                    SpringSpec springSpecSnap;
                    composer2.startReplaceGroup(1373301606);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1373301606, i10, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:276)");
                    }
                    if (segment.getInitialState() == ToggleableState.Off) {
                        springSpecSnap = AnimationSpecKt.tween$default(100, 0, null, 6, null);
                    } else {
                        springSpecSnap = segment.getTargetState() == ToggleableState.Off ? AnimationSpecKt.snap(100) : AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return springSpecSnap;
                }
            };
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
            ToggleableState toggleableState2 = (ToggleableState) transitionUpdateTransition.getCurrentState();
            composerStartRestartGroup.startReplaceGroup(1800065638);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C:Checkbox.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1800065638, 0, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:283)");
            }
            int i10 = WhenMappings.$EnumSwitchMapping$0[toggleableState2.ordinal()];
            float f4 = 0.0f;
            if (i10 == 1) {
                f = 1.0f;
            } else if (i10 != 2) {
                if (i10 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            Float fValueOf = Float.valueOf(f);
            ToggleableState toggleableState3 = (ToggleableState) transitionUpdateTransition.getTargetState();
            composerStartRestartGroup.startReplaceGroup(1800065638);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C:Checkbox.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1800065638, 0, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:283)");
            }
            int i11 = WhenMappings.$EnumSwitchMapping$0[toggleableState3.ordinal()];
            if (i11 != 1) {
                if (i11 != 2) {
                    c = 3;
                    if (i11 != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                } else {
                    c = 3;
                    f2 = 0.0f;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composerStartRestartGroup.endReplaceGroup();
                stateCreateTransitionAnimation = TransitionKt.createTransitionAnimation(transitionUpdateTransition, fValueOf, Float.valueOf(f2), checkboxKt$CheckboxImpl$checkDrawFraction$1.invoke(transitionUpdateTransition.getSegment(), composerStartRestartGroup, 0), vectorConverter, "FloatAnimation", composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1 checkboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1 = new Function3<Transition.Segment<ToggleableState>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, Integer num) {
                        return invoke(segment, composer2, num.intValue());
                    }

                    public final FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, int i12) {
                        TweenSpec tweenSpecSnap;
                        composer2.startReplaceGroup(-1324481169);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1324481169, i12, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:293)");
                        }
                        if (segment.getInitialState() == ToggleableState.Off) {
                            tweenSpecSnap = AnimationSpecKt.snap$default(0, 1, null);
                        } else {
                            tweenSpecSnap = segment.getTargetState() == ToggleableState.Off ? AnimationSpecKt.snap(100) : AnimationSpecKt.tween$default(100, 0, null, 6, null);
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer2.endReplaceGroup();
                        return tweenSpecSnap;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                ToggleableState toggleableState4 = (ToggleableState) transitionUpdateTransition.getCurrentState();
                composerStartRestartGroup.startReplaceGroup(-1426969489);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C:Checkbox.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    i3 = -1;
                    ComposerKt.traceEventStart(-1426969489, 0, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:300)");
                } else {
                    i3 = -1;
                }
                i4 = WhenMappings.$EnumSwitchMapping$0[toggleableState4.ordinal()];
                if (i4 != 1 || i4 == 2) {
                    i5 = 3;
                    f3 = 0.0f;
                } else {
                    i5 = 3;
                    if (i4 != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    f3 = 1.0f;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composerStartRestartGroup.endReplaceGroup();
                Float fValueOf2 = Float.valueOf(f3);
                ToggleableState toggleableState5 = (ToggleableState) transitionUpdateTransition.getTargetState();
                composerStartRestartGroup.startReplaceGroup(-1426969489);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C:Checkbox.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1426969489, 0, i3, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:300)");
                }
                i6 = WhenMappings.$EnumSwitchMapping$0[toggleableState5.ordinal()];
                if (i6 != 1 && i6 != 2) {
                    if (i6 == i5) {
                        throw new NoWhenBranchMatchedException();
                    }
                    f4 = 1.0f;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composerStartRestartGroup.endReplaceGroup();
                stateCreateTransitionAnimation2 = TransitionKt.createTransitionAnimation(transitionUpdateTransition, fValueOf2, Float.valueOf(f4), checkboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1.invoke(transitionUpdateTransition.getSegment(), composerStartRestartGroup, 0), vectorConverter2, "FloatAnimation", composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 974828454, "CC(remember):Checkbox.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new CheckDrawingCache(null, null, null, 7, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                checkDrawingCache = (CheckDrawingCache) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                stateCheckmarkColor$material3_release = checkboxColors.checkmarkColor$material3_release(toggleableState, composerStartRestartGroup, i9 | ((i7 >> 6) & 112));
                int i12 = (i8 & 896) | (i7 & 126);
                stateBoxColor$material3_release = checkboxColors.boxColor$material3_release(z, toggleableState, composerStartRestartGroup, i12);
                stateBorderColor$material3_release = checkboxColors.borderColor$material3_release(z, toggleableState, composerStartRestartGroup, i12);
                Modifier modifierM757requiredSize3ABfNKs = SizeKt.m757requiredSize3ABfNKs(SizeKt.wrapContentSize$default(modifier, Alignment.INSTANCE.getCenter(), false, 2, null), CheckboxSize);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 974837696, "CC(remember):Checkbox.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(stateBoxColor$material3_release) | composerStartRestartGroup.changed(stateBorderColor$material3_release) | composerStartRestartGroup.changed(stateCheckmarkColor$material3_release) | composerStartRestartGroup.changed(stateCreateTransitionAnimation) | composerStartRestartGroup.changed(stateCreateTransitionAnimation2);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$1$1
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
                            float fFloor = (float) Math.floor(drawScope.mo406toPx0680j_4(CheckboxKt.StrokeWidth));
                            CheckboxKt.m1569drawBox1wkBAMs(drawScope, stateBoxColor$material3_release.getValue().m4162unboximpl(), stateBorderColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(CheckboxKt.RadiusSize), fFloor);
                            CheckboxKt.m1570drawCheck3IgeMak(drawScope, stateCheckmarkColor$material3_release.getValue().m4162unboximpl(), stateCreateTransitionAnimation.getValue().floatValue(), stateCreateTransitionAnimation2.getValue().floatValue(), fFloor, checkDrawingCache);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM757requiredSize3ABfNKs, (Function1) objRememberedValue2, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                c = 3;
            }
            f2 = 1.0f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            stateCreateTransitionAnimation = TransitionKt.createTransitionAnimation(transitionUpdateTransition, fValueOf, Float.valueOf(f2), checkboxKt$CheckboxImpl$checkDrawFraction$1.invoke(transitionUpdateTransition.getSegment(), composerStartRestartGroup, 0), vectorConverter, "FloatAnimation", composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1 checkboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$2 = new Function3<Transition.Segment<ToggleableState>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, Integer num) {
                    return invoke(segment, composer2, num.intValue());
                }

                public final FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, int i13) {
                    TweenSpec tweenSpecSnap;
                    composer2.startReplaceGroup(-1324481169);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1324481169, i13, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:293)");
                    }
                    if (segment.getInitialState() == ToggleableState.Off) {
                        tweenSpecSnap = AnimationSpecKt.snap$default(0, 1, null);
                    } else {
                        tweenSpecSnap = segment.getTargetState() == ToggleableState.Off ? AnimationSpecKt.snap(100) : AnimationSpecKt.tween$default(100, 0, null, 6, null);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return tweenSpecSnap;
                }
            };
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter3 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
            ToggleableState toggleableState6 = (ToggleableState) transitionUpdateTransition.getCurrentState();
            composerStartRestartGroup.startReplaceGroup(-1426969489);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C:Checkbox.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                i3 = -1;
                ComposerKt.traceEventStart(-1426969489, 0, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:300)");
            } else {
                i3 = -1;
            }
            i4 = WhenMappings.$EnumSwitchMapping$0[toggleableState6.ordinal()];
            if (i4 != 1) {
                i5 = 3;
                f3 = 0.0f;
            } else {
                i5 = 3;
                f3 = 0.0f;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            Float fValueOf3 = Float.valueOf(f3);
            ToggleableState toggleableState7 = (ToggleableState) transitionUpdateTransition.getTargetState();
            composerStartRestartGroup.startReplaceGroup(-1426969489);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C:Checkbox.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1426969489, 0, i3, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:300)");
            }
            i6 = WhenMappings.$EnumSwitchMapping$0[toggleableState7.ordinal()];
            if (i6 != 1) {
                if (i6 == i5) {
                    throw new NoWhenBranchMatchedException();
                }
                f4 = 1.0f;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            stateCreateTransitionAnimation2 = TransitionKt.createTransitionAnimation(transitionUpdateTransition, fValueOf3, Float.valueOf(f4), checkboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$2.invoke(transitionUpdateTransition.getSegment(), composerStartRestartGroup, 0), vectorConverter3, "FloatAnimation", composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 974828454, "CC(remember):Checkbox.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new CheckDrawingCache(null, null, null, 7, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            checkDrawingCache = (CheckDrawingCache) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            stateCheckmarkColor$material3_release = checkboxColors.checkmarkColor$material3_release(toggleableState, composerStartRestartGroup, i9 | ((i7 >> 6) & 112));
            int i13 = (i8 & 896) | (i7 & 126);
            stateBoxColor$material3_release = checkboxColors.boxColor$material3_release(z, toggleableState, composerStartRestartGroup, i13);
            stateBorderColor$material3_release = checkboxColors.borderColor$material3_release(z, toggleableState, composerStartRestartGroup, i13);
            Modifier modifierM757requiredSize3ABfNKs2 = SizeKt.m757requiredSize3ABfNKs(SizeKt.wrapContentSize$default(modifier, Alignment.INSTANCE.getCenter(), false, 2, null), CheckboxSize);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 974837696, "CC(remember):Checkbox.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(stateBoxColor$material3_release) | composerStartRestartGroup.changed(stateBorderColor$material3_release) | composerStartRestartGroup.changed(stateCheckmarkColor$material3_release) | composerStartRestartGroup.changed(stateCreateTransitionAnimation) | composerStartRestartGroup.changed(stateCreateTransitionAnimation2);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue2 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$1$1
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
                        float fFloor = (float) Math.floor(drawScope.mo406toPx0680j_4(CheckboxKt.StrokeWidth));
                        CheckboxKt.m1569drawBox1wkBAMs(drawScope, stateBoxColor$material3_release.getValue().m4162unboximpl(), stateBorderColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(CheckboxKt.RadiusSize), fFloor);
                        CheckboxKt.m1570drawCheck3IgeMak(drawScope, stateCheckmarkColor$material3_release.getValue().m4162unboximpl(), stateCreateTransitionAnimation.getValue().floatValue(), stateCreateTransitionAnimation2.getValue().floatValue(), fFloor, checkDrawingCache);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$1$1
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
                        float fFloor = (float) Math.floor(drawScope.mo406toPx0680j_4(CheckboxKt.StrokeWidth));
                        CheckboxKt.m1569drawBox1wkBAMs(drawScope, stateBoxColor$material3_release.getValue().m4162unboximpl(), stateBorderColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(CheckboxKt.RadiusSize), fFloor);
                        CheckboxKt.m1570drawCheck3IgeMak(drawScope, stateCheckmarkColor$material3_release.getValue().m4162unboximpl(), stateCreateTransitionAnimation.getValue().floatValue(), stateCreateTransitionAnimation2.getValue().floatValue(), fFloor, checkDrawingCache);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CanvasKt.Canvas(modifierM757requiredSize3ABfNKs2, (Function1) objRememberedValue2, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt.CheckboxImpl.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i14) {
                    CheckboxKt.CheckboxImpl(z, toggleableState, modifier, checkboxColors, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawBox-1wkBAMs, reason: not valid java name */
    public static final void m1569drawBox1wkBAMs(DrawScope drawScope, long j, long j2, float f, float f2) {
        float f3 = f2 / 2.0f;
        Stroke stroke = new Stroke(f2, 0.0f, 0, 0, null, 30, null);
        float fM3939getWidthimpl = Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc());
        if (Color.m4153equalsimpl0(j, j2)) {
            DrawScope.CC.m4734drawRoundRectuAw5IA$default(drawScope, j, 0L, androidx.compose.ui.geometry.SizeKt.Size(fM3939getWidthimpl, fM3939getWidthimpl), CornerRadiusKt.CornerRadius$default(f, 0.0f, 2, null), Fill.INSTANCE, 0.0f, null, 0, 226, null);
            return;
        }
        float f4 = fM3939getWidthimpl - (f2 * 2.0f);
        DrawScope.CC.m4734drawRoundRectuAw5IA$default(drawScope, j, OffsetKt.Offset(f2, f2), androidx.compose.ui.geometry.SizeKt.Size(f4, f4), CornerRadiusKt.CornerRadius$default(Math.max(0.0f, f - f2), 0.0f, 2, null), Fill.INSTANCE, 0.0f, null, 0, 224, null);
        float f5 = fM3939getWidthimpl - f2;
        DrawScope.CC.m4734drawRoundRectuAw5IA$default(drawScope, j2, OffsetKt.Offset(f3, f3), androidx.compose.ui.geometry.SizeKt.Size(f5, f5), CornerRadiusKt.CornerRadius$default(f - f3, 0.0f, 2, null), stroke, 0.0f, null, 0, 224, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawCheck-3IgeMak, reason: not valid java name */
    public static final void m1570drawCheck3IgeMak(DrawScope drawScope, long j, float f, float f2, float f3, CheckDrawingCache checkDrawingCache) {
        Stroke stroke = new Stroke(f3, 0.0f, StrokeCap.INSTANCE.m4533getSquareKaPHkGw(), 0, null, 26, null);
        float fM3939getWidthimpl = Size.m3939getWidthimpl(drawScope.mo4647getSizeNHjbRc());
        float fLerp = MathHelpersKt.lerp(0.4f, 0.5f, f2);
        float fLerp2 = MathHelpersKt.lerp(0.7f, 0.5f, f2);
        float fLerp3 = MathHelpersKt.lerp(0.5f, 0.5f, f2);
        float fLerp4 = MathHelpersKt.lerp(0.3f, 0.5f, f2);
        checkDrawingCache.getCheckPath().reset();
        checkDrawingCache.getCheckPath().moveTo(0.2f * fM3939getWidthimpl, fLerp3 * fM3939getWidthimpl);
        checkDrawingCache.getCheckPath().lineTo(fLerp * fM3939getWidthimpl, fLerp2 * fM3939getWidthimpl);
        checkDrawingCache.getCheckPath().lineTo(0.8f * fM3939getWidthimpl, fM3939getWidthimpl * fLerp4);
        checkDrawingCache.getPathMeasure().setPath(checkDrawingCache.getCheckPath(), false);
        checkDrawingCache.getPathToDraw().reset();
        checkDrawingCache.getPathMeasure().getSegment(0.0f, checkDrawingCache.getPathMeasure().getLength() * f, checkDrawingCache.getPathToDraw(), true);
        DrawScope.CC.m4728drawPathLG529CI$default(drawScope, checkDrawingCache.getPathToDraw(), j, 0.0f, stroke, null, 0, 52, null);
    }
}
