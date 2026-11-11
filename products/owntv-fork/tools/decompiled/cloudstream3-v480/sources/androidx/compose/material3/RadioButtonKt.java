package androidx.compose.material3;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.tokens.RadioButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: RadioButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aO\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007¢\u0006\u0002\u0010\u0014\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004¨\u0006\u0015"}, d2 = {"RadioAnimationDuration", "", "RadioButtonDotSize", "Landroidx/compose/ui/unit/Dp;", "F", "RadioButtonPadding", "RadioStrokeWidth", "RadioButton", "", "selected", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "colors", "Landroidx/compose/material3/RadioButtonColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/RadioButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RadioButtonKt {
    private static final int RadioAnimationDuration = 100;
    private static final float RadioButtonPadding = Dp.m6890constructorimpl(2.0f);
    private static final float RadioButtonDotSize = Dp.m6890constructorimpl(12.0f);
    private static final float RadioStrokeWidth = Dp.m6890constructorimpl(2.0f);

    /* JADX WARN: Code duplicated, block: B:100:0x011e  */
    /* JADX WARN: Code duplicated, block: B:101:0x0126  */
    /* JADX WARN: Code duplicated, block: B:104:0x0163  */
    /* JADX WARN: Code duplicated, block: B:105:0x0198  */
    /* JADX WARN: Code duplicated, block: B:108:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:109:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:112:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:114:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:117:0x020d  */
    /* JADX WARN: Code duplicated, block: B:121:0x021a  */
    /* JADX WARN: Code duplicated, block: B:123:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x0069  */
    /* JADX WARN: Code duplicated, block: B:41:0x0071  */
    /* JADX WARN: Code duplicated, block: B:42:0x0074  */
    /* JADX WARN: Code duplicated, block: B:47:0x007e  */
    /* JADX WARN: Code duplicated, block: B:49:0x0082  */
    /* JADX WARN: Code duplicated, block: B:51:0x008a  */
    /* JADX WARN: Code duplicated, block: B:52:0x008d  */
    /* JADX WARN: Code duplicated, block: B:55:0x0093  */
    /* JADX WARN: Code duplicated, block: B:58:0x009b  */
    /* JADX WARN: Code duplicated, block: B:59:0x009d  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:69:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:83:0x00eb A[PHI: r3 r4 r5 r6
      0x00eb: PHI (r3v34 boolean) = (r3v21 boolean), (r3v36 boolean) binds: [B:93:0x0108, B:82:0x00e7] A[DONT_GENERATE, DONT_INLINE]
      0x00eb: PHI (r4v15 androidx.compose.ui.Modifier) = (r4v8 androidx.compose.ui.Modifier), (r4v17 androidx.compose.ui.Modifier) binds: [B:93:0x0108, B:82:0x00e7] A[DONT_GENERATE, DONT_INLINE]
      0x00eb: PHI (r5v10 androidx.compose.material3.RadioButtonColors) = (r5v4 androidx.compose.material3.RadioButtonColors), (r5v11 androidx.compose.material3.RadioButtonColors) binds: [B:93:0x0108, B:82:0x00e7] A[DONT_GENERATE, DONT_INLINE]
      0x00eb: PHI (r6v13 int) = (r6v5 int), (r6v14 int) binds: [B:93:0x0108, B:82:0x00e7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:84:0x00ed A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:85:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:91:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:94:0x010a  */
    /* JADX WARN: Code duplicated, block: B:97:0x0114  */
    public static final void RadioButton(final boolean z, final Function0<Unit> function0, Modifier modifier, boolean z2, RadioButtonColors radioButtonColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        boolean z3;
        int i5;
        RadioButtonColors radioButtonColorsColors;
        int i6;
        MutableInteractionSource mutableInteractionSource2;
        int i7;
        Modifier.Companion companion;
        int i8;
        boolean z4;
        MutableInteractionSource mutableInteractionSource3;
        float fM6890constructorimpl;
        final State<Dp> stateM160animateDpAsStateAjpBEmI;
        final State<Color> stateRadioColor$material3_release;
        final boolean z5;
        Modifier.Companion companionM978selectableO2vRcR0;
        Modifier.Companion companionMinimumInteractiveComponentSize;
        boolean zChanged;
        Object objRememberedValue;
        final MutableInteractionSource mutableInteractionSource4;
        final Modifier modifier3;
        final RadioButtonColors radioButtonColors2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(408580840);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(RadioButton)P(5,4,3,1)80@3770L8,84@3868L176,88@4073L29,119@5097L415,106@4679L833:RadioButton.kt#uh7d8r");
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
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        int i9 = i2 & 4;
        if (i9 == 0) {
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
                        radioButtonColorsColors = radioButtonColors;
                        int i10 = composerStartRestartGroup.changed(radioButtonColorsColors) ? 16384 : 8192;
                        i3 |= i10;
                    } else {
                        radioButtonColorsColors = radioButtonColors;
                    }
                    i3 |= i10;
                } else {
                    radioButtonColorsColors = radioButtonColors;
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
                            if (i9 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i8 = i3;
                            z4 = z3;
                            radioButtonColorsColors = radioButtonColorsColors;
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                            }
                            if (z) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                            } else {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            }
                            stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                            stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                            composerStartRestartGroup.startReplaceGroup(1327106656);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                            if (function0 != null) {
                                z5 = z4;
                                stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                                companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                            } else {
                                z5 = z4;
                                companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            if (function0 != null) {
                                companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                            } else {
                                companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                            }
                            Modifier modifierM757requiredSize3ABfNKs = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                            zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (!zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                        float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                        float f2 = f / 2.0f;
                                        DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                        if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                        }
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            CanvasKt.Canvas(modifierM757requiredSize3ABfNKs, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            mutableInteractionSource4 = mutableInteractionSource3;
                            modifier3 = companion;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                            i8 = i3;
                            companion = modifier2;
                            z4 = z3;
                            radioButtonColorsColors = radioButtonColorsColors;
                        }
                        mutableInteractionSource3 = mutableInteractionSource2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                        }
                        if (z) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                        } else {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        }
                        stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                        stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                        composerStartRestartGroup.startReplaceGroup(1327106656);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                        if (function0 != null) {
                            z5 = z4;
                            stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                            companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                        } else {
                            z5 = z4;
                            companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        if (function0 != null) {
                            companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                        } else {
                            companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                        }
                        Modifier modifierM757requiredSize3ABfNKs2 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                    float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                    float f2 = f / 2.0f;
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                    if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                        DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                    }
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                    float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                    float f2 = f / 2.0f;
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                    if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                        DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                    }
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        CanvasKt.Canvas(modifierM757requiredSize3ABfNKs2, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource4 = mutableInteractionSource3;
                        modifier3 = companion;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        z5 = z3;
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    radioButtonColors2 = radioButtonColorsColors;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.RadioButtonKt.RadioButton.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i11) {
                                RadioButtonKt.RadioButton(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((74899 & i3) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z4 = z3;
                        radioButtonColorsColors = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                    } else {
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z4 = z3;
                        radioButtonColorsColors = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                    }
                    if (z) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                    } else {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                    stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                    composerStartRestartGroup.startReplaceGroup(1327106656);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                    if (function0 != null) {
                        z5 = z4;
                        stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                        companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                    } else {
                        z5 = z4;
                        companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function0 != null) {
                        companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    Modifier modifierM757requiredSize3ABfNKs3 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                float f2 = f / 2.0f;
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                float f2 = f / 2.0f;
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CanvasKt.Canvas(modifierM757requiredSize3ABfNKs3, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource3;
                    modifier3 = companion;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z4 = z3;
                        radioButtonColorsColors = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                    } else {
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z4 = z3;
                        radioButtonColorsColors = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                    }
                    if (z) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                    } else {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                    stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                    composerStartRestartGroup.startReplaceGroup(1327106656);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                    if (function0 != null) {
                        z5 = z4;
                        stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                        companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                    } else {
                        z5 = z4;
                        companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function0 != null) {
                        companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    Modifier modifierM757requiredSize3ABfNKs4 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                float f2 = f / 2.0f;
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                float f2 = f / 2.0f;
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CanvasKt.Canvas(modifierM757requiredSize3ABfNKs4, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource3;
                    modifier3 = companion;
                }
                radioButtonColors2 = radioButtonColorsColors;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.RadioButtonKt.RadioButton.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11) {
                            RadioButtonKt.RadioButton(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            z3 = z2;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    radioButtonColorsColors = radioButtonColors;
                    if (composerStartRestartGroup.changed(radioButtonColorsColors)) {
                    }
                    i3 |= i10;
                } else {
                    radioButtonColorsColors = radioButtonColors;
                }
                i3 |= i10;
            } else {
                radioButtonColorsColors = radioButtonColors;
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
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z4 = z3;
                        radioButtonColorsColors = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                    } else {
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z4 = z3;
                        radioButtonColorsColors = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                    }
                    if (z) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                    } else {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                    stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                    composerStartRestartGroup.startReplaceGroup(1327106656);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                    if (function0 != null) {
                        z5 = z4;
                        stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                        companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                    } else {
                        z5 = z4;
                        companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function0 != null) {
                        companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    Modifier modifierM757requiredSize3ABfNKs5 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                float f2 = f / 2.0f;
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                float f2 = f / 2.0f;
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CanvasKt.Canvas(modifierM757requiredSize3ABfNKs5, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource3;
                    modifier3 = companion;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z4 = z3;
                        radioButtonColorsColors = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                    } else {
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z4 = z3;
                        radioButtonColorsColors = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                    }
                    if (z) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                    } else {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                    stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                    composerStartRestartGroup.startReplaceGroup(1327106656);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                    if (function0 != null) {
                        z5 = z4;
                        stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                        companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                    } else {
                        z5 = z4;
                        companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function0 != null) {
                        companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    Modifier modifierM757requiredSize3ABfNKs6 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                float f2 = f / 2.0f;
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                float f2 = f / 2.0f;
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CanvasKt.Canvas(modifierM757requiredSize3ABfNKs6, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource3;
                    modifier3 = companion;
                }
                radioButtonColors2 = radioButtonColorsColors;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.RadioButtonKt.RadioButton.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11) {
                            RadioButtonKt.RadioButton(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((74899 & i3) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z4 = z3;
                    radioButtonColorsColors = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                } else {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z4 = z3;
                    radioButtonColorsColors = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                }
                if (z) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                } else {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                composerStartRestartGroup.startReplaceGroup(1327106656);
                ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                if (function0 != null) {
                    z5 = z4;
                    stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                    companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                } else {
                    z5 = z4;
                    companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function0 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                Modifier modifierM757requiredSize3ABfNKs7 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                            float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                            float f2 = f / 2.0f;
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                            if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                            float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                            float f2 = f / 2.0f;
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                            if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM757requiredSize3ABfNKs7, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource3;
                modifier3 = companion;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z4 = z3;
                    radioButtonColorsColors = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                } else {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z4 = z3;
                    radioButtonColorsColors = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                }
                if (z) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                } else {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                composerStartRestartGroup.startReplaceGroup(1327106656);
                ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                if (function0 != null) {
                    z5 = z4;
                    stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                    companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                } else {
                    z5 = z4;
                    companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function0 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                Modifier modifierM757requiredSize3ABfNKs8 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                            float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                            float f2 = f / 2.0f;
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                            if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                            float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                            float f2 = f / 2.0f;
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                            if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM757requiredSize3ABfNKs8, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource3;
                modifier3 = companion;
            }
            radioButtonColors2 = radioButtonColorsColors;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.RadioButtonKt.RadioButton.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i11) {
                        RadioButtonKt.RadioButton(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
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
                    radioButtonColorsColors = radioButtonColors;
                    if (composerStartRestartGroup.changed(radioButtonColorsColors)) {
                    }
                    i3 |= i10;
                } else {
                    radioButtonColorsColors = radioButtonColors;
                }
                i3 |= i10;
            } else {
                radioButtonColorsColors = radioButtonColors;
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
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z4 = z3;
                        radioButtonColorsColors = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                    } else {
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z4 = z3;
                        radioButtonColorsColors = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                    }
                    if (z) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                    } else {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                    stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                    composerStartRestartGroup.startReplaceGroup(1327106656);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                    if (function0 != null) {
                        z5 = z4;
                        stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                        companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                    } else {
                        z5 = z4;
                        companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function0 != null) {
                        companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    Modifier modifierM757requiredSize3ABfNKs9 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                float f2 = f / 2.0f;
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                float f2 = f / 2.0f;
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CanvasKt.Canvas(modifierM757requiredSize3ABfNKs9, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource3;
                    modifier3 = companion;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z4 = z3;
                        radioButtonColorsColors = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                    } else {
                        if (i9 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i8 = i3;
                        z4 = z3;
                        radioButtonColorsColors = radioButtonColorsColors;
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                    }
                    if (z) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                    } else {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    }
                    stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                    stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                    composerStartRestartGroup.startReplaceGroup(1327106656);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                    if (function0 != null) {
                        z5 = z4;
                        stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                        companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                    } else {
                        z5 = z4;
                        companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function0 != null) {
                        companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    Modifier modifierM757requiredSize3ABfNKs10 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                float f2 = f / 2.0f;
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                                float f2 = f / 2.0f;
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                    DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    CanvasKt.Canvas(modifierM757requiredSize3ABfNKs10, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource3;
                    modifier3 = companion;
                }
                radioButtonColors2 = radioButtonColorsColors;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.RadioButtonKt.RadioButton.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11) {
                            RadioButtonKt.RadioButton(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((74899 & i3) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z4 = z3;
                    radioButtonColorsColors = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                } else {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z4 = z3;
                    radioButtonColorsColors = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                }
                if (z) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                } else {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                composerStartRestartGroup.startReplaceGroup(1327106656);
                ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                if (function0 != null) {
                    z5 = z4;
                    stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                    companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                } else {
                    z5 = z4;
                    companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function0 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                Modifier modifierM757requiredSize3ABfNKs11 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                            float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                            float f2 = f / 2.0f;
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                            if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                            float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                            float f2 = f / 2.0f;
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                            if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM757requiredSize3ABfNKs11, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource3;
                modifier3 = companion;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z4 = z3;
                    radioButtonColorsColors = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                } else {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z4 = z3;
                    radioButtonColorsColors = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                }
                if (z) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                } else {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                composerStartRestartGroup.startReplaceGroup(1327106656);
                ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                if (function0 != null) {
                    z5 = z4;
                    stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                    companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                } else {
                    z5 = z4;
                    companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function0 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                Modifier modifierM757requiredSize3ABfNKs12 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                            float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                            float f2 = f / 2.0f;
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                            if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                            float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                            float f2 = f / 2.0f;
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                            if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM757requiredSize3ABfNKs12, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource3;
                modifier3 = companion;
            }
            radioButtonColors2 = radioButtonColorsColors;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.RadioButtonKt.RadioButton.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i11) {
                        RadioButtonKt.RadioButton(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        z3 = z2;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                radioButtonColorsColors = radioButtonColors;
                if (composerStartRestartGroup.changed(radioButtonColorsColors)) {
                }
                i3 |= i10;
            } else {
                radioButtonColorsColors = radioButtonColors;
            }
            i3 |= i10;
        } else {
            radioButtonColorsColors = radioButtonColors;
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
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z4 = z3;
                    radioButtonColorsColors = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                } else {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z4 = z3;
                    radioButtonColorsColors = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                }
                if (z) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                } else {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                composerStartRestartGroup.startReplaceGroup(1327106656);
                ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                if (function0 != null) {
                    z5 = z4;
                    stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                    companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                } else {
                    z5 = z4;
                    companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function0 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                Modifier modifierM757requiredSize3ABfNKs13 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                            float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                            float f2 = f / 2.0f;
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                            if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                            float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                            float f2 = f / 2.0f;
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                            if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM757requiredSize3ABfNKs13, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource3;
                modifier3 = companion;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z4 = z3;
                    radioButtonColorsColors = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                } else {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i8 = i3;
                    z4 = z3;
                    radioButtonColorsColors = radioButtonColorsColors;
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                }
                if (z) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
                } else {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                }
                stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
                stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
                composerStartRestartGroup.startReplaceGroup(1327106656);
                ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
                if (function0 != null) {
                    z5 = z4;
                    stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                    companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
                } else {
                    z5 = z4;
                    companionM978selectableO2vRcR0 = Modifier.INSTANCE;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function0 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                Modifier modifierM757requiredSize3ABfNKs14 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                            float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                            float f2 = f / 2.0f;
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                            if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                            float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                            float f2 = f / 2.0f;
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                            if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                                DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                CanvasKt.Canvas(modifierM757requiredSize3ABfNKs14, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource3;
                modifier3 = companion;
            }
            radioButtonColors2 = radioButtonColorsColors;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.RadioButtonKt.RadioButton.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i11) {
                        RadioButtonKt.RadioButton(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        mutableInteractionSource2 = mutableInteractionSource;
        if ((74899 & i3) == 74898) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    z3 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                i8 = i3;
                z4 = z3;
                radioButtonColorsColors = radioButtonColorsColors;
                if (i6 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
            } else {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    z3 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                i8 = i3;
                z4 = z3;
                radioButtonColorsColors = radioButtonColorsColors;
                if (i6 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
            }
            if (z) {
                fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
            } else {
                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
            }
            stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
            stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
            composerStartRestartGroup.startReplaceGroup(1327106656);
            ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
            if (function0 != null) {
                z5 = z4;
                stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
            } else {
                z5 = z4;
                companionM978selectableO2vRcR0 = Modifier.INSTANCE;
            }
            composerStartRestartGroup.endReplaceGroup();
            if (function0 != null) {
                companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
            } else {
                companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
            }
            Modifier modifierM757requiredSize3ABfNKs15 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                        float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                        float f2 = f / 2.0f;
                        DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                        if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                        float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                        float f2 = f / 2.0f;
                        DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                        if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CanvasKt.Canvas(modifierM757requiredSize3ABfNKs15, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            mutableInteractionSource4 = mutableInteractionSource3;
            modifier3 = companion;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    z3 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                i8 = i3;
                z4 = z3;
                radioButtonColorsColors = radioButtonColorsColors;
                if (i6 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
            } else {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    z3 = true;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    radioButtonColorsColors = RadioButtonDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                i8 = i3;
                z4 = z3;
                radioButtonColorsColors = radioButtonColorsColors;
                if (i6 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(408580840, i8, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
            }
            if (z) {
                fM6890constructorimpl = Dp.m6890constructorimpl(RadioButtonDotSize / 2.0f);
            } else {
                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
            }
            stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(fM6890constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composerStartRestartGroup, 48, 12);
            stateRadioColor$material3_release = radioButtonColorsColors.radioColor$material3_release(z4, z, composerStartRestartGroup, ((i8 >> 6) & 896) | ((i8 >> 9) & 14) | ((i8 << 3) & 112));
            composerStartRestartGroup.startReplaceGroup(1327106656);
            ComposerKt.sourceInformation(composerStartRestartGroup, "98@4448L164");
            if (function0 != null) {
                z5 = z4;
                stateM160animateDpAsStateAjpBEmI = stateM160animateDpAsStateAjpBEmI;
                companionM978selectableO2vRcR0 = SelectableKt.m978selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3218getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4), z5, Role.m6108boximpl(Role.INSTANCE.m6120getRadioButtono7Vup1c()), function0);
            } else {
                z5 = z4;
                companionM978selectableO2vRcR0 = Modifier.INSTANCE;
            }
            composerStartRestartGroup.endReplaceGroup();
            if (function0 != null) {
                companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
            } else {
                companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
            }
            Modifier modifierM757requiredSize3ABfNKs16 = SizeKt.m757requiredSize3ABfNKs(PaddingKt.m720padding3ABfNKs(SizeKt.wrapContentSize$default(companion.then(companionMinimumInteractiveComponentSize).then(companionM978selectableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(stateRadioColor$material3_release) | composerStartRestartGroup.changed(stateM160animateDpAsStateAjpBEmI);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                        float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                        float f2 = f / 2.0f;
                        DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                        if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                        float f = drawScope.mo406toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                        float f2 = f / 2.0f;
                        DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(Dp.m6890constructorimpl(RadioButtonTokens.INSTANCE.m3217getIconSizeD9Ej5fM() / 2.0f)) - f2, 0L, 0.0f, new Stroke(f, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                        if (Dp.m6889compareTo0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl(), Dp.m6890constructorimpl(0.0f)) > 0) {
                            DrawScope.CC.m4719drawCircleVaOC9Bg$default(drawScope, stateRadioColor$material3_release.getValue().m4162unboximpl(), drawScope.mo406toPx0680j_4(stateM160animateDpAsStateAjpBEmI.getValue().m6904unboximpl()) - f2, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CanvasKt.Canvas(modifierM757requiredSize3ABfNKs16, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            mutableInteractionSource4 = mutableInteractionSource3;
            modifier3 = companion;
        }
        radioButtonColors2 = radioButtonColorsColors;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.RadioButtonKt.RadioButton.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i11) {
                    RadioButtonKt.RadioButton(z, function0, modifier3, z5, radioButtonColors2, mutableInteractionSource4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }
}
