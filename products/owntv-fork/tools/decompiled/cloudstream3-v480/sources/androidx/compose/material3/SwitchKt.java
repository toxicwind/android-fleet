package androidx.compose.material3;

import androidx.compose.animation.core.SnapSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.ToggleableKt;
import androidx.compose.material3.tokens.SwitchTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Switch.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001al\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0018¢\u0006\u0002\b\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007¢\u0006\u0002\u0010\u001f\u001aR\u0010 \u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001c2\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0018¢\u0006\u0002\b\u00192\u0006\u0010\u001d\u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0003¢\u0006\u0002\u0010$\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007\"\u0010\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007\"\u0016\u0010\t\u001a\u00020\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\n\u0010\u000b\"\u0010\u0010\f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007\"\u0016\u0010\r\u001a\u00020\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000e\u0010\u000b¨\u0006%"}, d2 = {"AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "SnapSpec", "Landroidx/compose/animation/core/SnapSpec;", "SwitchHeight", "Landroidx/compose/ui/unit/Dp;", "F", "SwitchWidth", "ThumbDiameter", "getThumbDiameter", "()F", "ThumbPadding", "UncheckedThumbDiameter", "getUncheckedThumbDiameter", "Switch", "", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "thumbContent", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "enabled", "colors", "Landroidx/compose/material3/SwitchColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/SwitchColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "SwitchImpl", "Landroidx/compose/foundation/interaction/InteractionSource;", "thumbShape", "Landroidx/compose/ui/graphics/Shape;", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/material3/SwitchColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwitchKt {
    private static final TweenSpec<Float> AnimationSpec;
    private static final SnapSpec<Float> SnapSpec;
    private static final float SwitchHeight;
    private static final float SwitchWidth;
    private static final float ThumbDiameter;
    private static final float ThumbPadding;
    private static final float UncheckedThumbDiameter;

    /* JADX WARN: Code duplicated, block: B:102:0x0127  */
    /* JADX WARN: Code duplicated, block: B:104:0x0132  */
    /* JADX WARN: Code duplicated, block: B:105:0x0138  */
    /* JADX WARN: Code duplicated, block: B:109:0x0148  */
    /* JADX WARN: Code duplicated, block: B:112:0x015e  */
    /* JADX WARN: Code duplicated, block: B:114:0x0172  */
    /* JADX WARN: Code duplicated, block: B:116:0x0180  */
    /* JADX WARN: Code duplicated, block: B:119:0x0187  */
    /* JADX WARN: Code duplicated, block: B:120:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:123:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:127:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:129:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0067  */
    /* JADX WARN: Code duplicated, block: B:37:0x006a  */
    /* JADX WARN: Code duplicated, block: B:39:0x006e  */
    /* JADX WARN: Code duplicated, block: B:41:0x0076  */
    /* JADX WARN: Code duplicated, block: B:42:0x0079  */
    /* JADX WARN: Code duplicated, block: B:47:0x0083  */
    /* JADX WARN: Code duplicated, block: B:48:0x0086  */
    /* JADX WARN: Code duplicated, block: B:50:0x008a  */
    /* JADX WARN: Code duplicated, block: B:52:0x0092  */
    /* JADX WARN: Code duplicated, block: B:53:0x0095  */
    /* JADX WARN: Code duplicated, block: B:58:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:63:0x00af  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:70:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:80:0x00de  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:86:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:93:0x0114 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:94:0x0116  */
    /* JADX WARN: Code duplicated, block: B:95:0x011b  */
    /* JADX WARN: Code duplicated, block: B:97:0x011e  */
    /* JADX WARN: Code duplicated, block: B:99:0x0121  */
    public static final void Switch(final boolean z, final Function1<? super Boolean, Unit> function1, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, boolean z2, SwitchColors switchColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        boolean z3;
        int i3;
        Modifier modifier2;
        int i4;
        Function2<? super Composer, ? super Integer, Unit> function3;
        int i5;
        int i6;
        boolean z4;
        int i7;
        SwitchColors switchColorsColors;
        int i8;
        MutableInteractionSource mutableInteractionSource2;
        int i9;
        Modifier.Companion companion;
        MutableInteractionSource mutableInteractionSource3;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Modifier modifier3;
        MutableInteractionSource mutableInteractionSource4;
        Modifier.Companion companionM983toggleableO2vRcR0;
        Composer composer2;
        final Modifier modifier4;
        final boolean z5;
        final SwitchColors switchColors2;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        final MutableInteractionSource mutableInteractionSource5;
        Object objRememberedValue;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(1580463220);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Switch)P(!1,5,4,6,2)97@4514L8,129@5619L5,119@5244L424:Switch.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            z3 = z;
        } else if ((i & 6) == 0) {
            z3 = z;
            i3 = (composerStartRestartGroup.changed(z3) ? 4 : 2) | i;
        } else {
            z3 = z;
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i10 = i2 & 4;
        if (i10 == 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    function3 = function2;
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 16;
                if (i6 != 0) {
                    if ((i & 24576) == 0) {
                        z4 = z2;
                        if (composerStartRestartGroup.changed(z4)) {
                            i7 = 16384;
                        } else {
                            i7 = 8192;
                        }
                        i3 |= i7;
                    }
                    if ((196608 & i) == 0) {
                        if ((i2 & 32) == 0) {
                            switchColorsColors = switchColors;
                            int i11 = composerStartRestartGroup.changed(switchColorsColors) ? 131072 : 65536;
                            i3 |= i11;
                        } else {
                            switchColorsColors = switchColors;
                        }
                        i3 |= i11;
                    } else {
                        switchColorsColors = switchColors;
                    }
                    i8 = i2 & 64;
                    if (i8 != 0) {
                        if ((1572864 & i) == 0) {
                            mutableInteractionSource2 = mutableInteractionSource;
                            if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                                i9 = 1048576;
                            } else {
                                i9 = 524288;
                            }
                            i3 |= i9;
                        }
                        if ((i3 & 599187) == 599186 || !composerStartRestartGroup.getSkipping()) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i10 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier2;
                                }
                                if (i4 != 0) {
                                    function3 = null;
                                }
                                if (i6 != 0) {
                                    z4 = true;
                                }
                                if ((i2 & 32) != 0) {
                                    i3 &= -458753;
                                    switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                }
                                if (i8 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource2;
                                }
                                function4 = function3;
                                modifier3 = companion;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 32) != 0) {
                                    i3 &= -458753;
                                }
                                i3 = i3;
                                z4 = z4;
                                switchColorsColors = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                function4 = function3;
                                modifier3 = modifier2;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                            }
                            composerStartRestartGroup.startReplaceGroup(783532531);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                            if (mutableInteractionSource3 == null) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                            if (function1 != null) {
                                companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                            } else {
                                companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                            }
                            int i12 = i3 << 3;
                            int i13 = i3 >> 6;
                            composer2 = composerStartRestartGroup;
                            Modifier modifier5 = modifier3;
                            SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i12 & 112) | (i13 & 896) | (i13 & 7168) | (i12 & 57344));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier4 = modifier5;
                            z5 = z4;
                            switchColors2 = switchColorsColors;
                            function5 = function4;
                            mutableInteractionSource5 = mutableInteractionSource3;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            modifier4 = modifier2;
                            composer2 = composerStartRestartGroup;
                            function5 = function3;
                            z5 = z4;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource5 = mutableInteractionSource2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                                public final void invoke(Composer composer3, int i14) {
                                    SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    mutableInteractionSource2 = mutableInteractionSource;
                    if ((i3 & 599187) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        } else {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                        if (function1 != null) {
                            companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                        } else {
                            companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i14 = i3 << 3;
                        int i15 = i3 >> 6;
                        composer2 = composerStartRestartGroup;
                        Modifier modifier6 = modifier3;
                        SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i14 & 112) | (i15 & 896) | (i15 & 7168) | (i14 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier6;
                        z5 = z4;
                        switchColors2 = switchColorsColors;
                        function5 = function4;
                        mutableInteractionSource5 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        } else {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                        if (function1 != null) {
                            companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                        } else {
                            companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i16 = i3 << 3;
                        int i17 = i3 >> 6;
                        composer2 = composerStartRestartGroup;
                        Modifier modifier7 = modifier3;
                        SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i16 & 112) | (i17 & 896) | (i17 & 7168) | (i16 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier7;
                        z5 = z4;
                        switchColors2 = switchColorsColors;
                        function5 = function4;
                        mutableInteractionSource5 = mutableInteractionSource3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                            public final void invoke(Composer composer3, int i18) {
                                SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 24576;
                z4 = z2;
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        switchColorsColors = switchColors;
                        if (composerStartRestartGroup.changed(switchColorsColors)) {
                        }
                        i3 |= i11;
                    } else {
                        switchColorsColors = switchColors;
                    }
                    i3 |= i11;
                } else {
                    switchColorsColors = switchColors;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 599187) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        } else {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                        if (function1 != null) {
                            companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                        } else {
                            companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i18 = i3 << 3;
                        int i19 = i3 >> 6;
                        composer2 = composerStartRestartGroup;
                        Modifier modifier8 = modifier3;
                        SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i18 & 112) | (i19 & 896) | (i19 & 7168) | (i18 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier8;
                        z5 = z4;
                        switchColors2 = switchColorsColors;
                        function5 = function4;
                        mutableInteractionSource5 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        } else {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                        if (function1 != null) {
                            companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                        } else {
                            companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i110 = i3 << 3;
                        int i111 = i3 >> 6;
                        composer2 = composerStartRestartGroup;
                        Modifier modifier9 = modifier3;
                        SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i110 & 112) | (i111 & 896) | (i111 & 7168) | (i110 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier9;
                        z5 = z4;
                        switchColors2 = switchColorsColors;
                        function5 = function4;
                        mutableInteractionSource5 = mutableInteractionSource3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                            public final void invoke(Composer composer3, int i112) {
                                SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 1572864;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((i3 & 599187) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    } else {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                    if (function1 != null) {
                        companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                    } else {
                        companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i112 = i3 << 3;
                    int i113 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier10 = modifier3;
                    SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i112 & 112) | (i113 & 896) | (i113 & 7168) | (i112 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier10;
                    z5 = z4;
                    switchColors2 = switchColorsColors;
                    function5 = function4;
                    mutableInteractionSource5 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    } else {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                    if (function1 != null) {
                        companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                    } else {
                        companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i114 = i3 << 3;
                    int i115 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier11 = modifier3;
                    SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i114 & 112) | (i115 & 896) | (i115 & 7168) | (i114 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier11;
                    z5 = z4;
                    switchColors2 = switchColorsColors;
                    function5 = function4;
                    mutableInteractionSource5 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                        public final void invoke(Composer composer3, int i116) {
                            SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            function3 = function2;
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        switchColorsColors = switchColors;
                        if (composerStartRestartGroup.changed(switchColorsColors)) {
                        }
                        i3 |= i11;
                    } else {
                        switchColorsColors = switchColors;
                    }
                    i3 |= i11;
                } else {
                    switchColorsColors = switchColors;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 599187) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        } else {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                        if (function1 != null) {
                            companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                        } else {
                            companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i116 = i3 << 3;
                        int i117 = i3 >> 6;
                        composer2 = composerStartRestartGroup;
                        Modifier modifier12 = modifier3;
                        SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i116 & 112) | (i117 & 896) | (i117 & 7168) | (i116 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier12;
                        z5 = z4;
                        switchColors2 = switchColorsColors;
                        function5 = function4;
                        mutableInteractionSource5 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        } else {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                        if (function1 != null) {
                            companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                        } else {
                            companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i118 = i3 << 3;
                        int i119 = i3 >> 6;
                        composer2 = composerStartRestartGroup;
                        Modifier modifier13 = modifier3;
                        SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i118 & 112) | (i119 & 896) | (i119 & 7168) | (i118 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier13;
                        z5 = z4;
                        switchColors2 = switchColorsColors;
                        function5 = function4;
                        mutableInteractionSource5 = mutableInteractionSource3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                            public final void invoke(Composer composer3, int i1110) {
                                SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 1572864;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((i3 & 599187) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    } else {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                    if (function1 != null) {
                        companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                    } else {
                        companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i1110 = i3 << 3;
                    int i1111 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier14 = modifier3;
                    SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1110 & 112) | (i1111 & 896) | (i1111 & 7168) | (i1110 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier14;
                    z5 = z4;
                    switchColors2 = switchColorsColors;
                    function5 = function4;
                    mutableInteractionSource5 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    } else {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                    if (function1 != null) {
                        companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                    } else {
                        companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i1112 = i3 << 3;
                    int i1113 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier15 = modifier3;
                    SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1112 & 112) | (i1113 & 896) | (i1113 & 7168) | (i1112 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier15;
                    z5 = z4;
                    switchColors2 = switchColorsColors;
                    function5 = function4;
                    mutableInteractionSource5 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                        public final void invoke(Composer composer3, int i1114) {
                            SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 24576;
            z4 = z2;
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    switchColorsColors = switchColors;
                    if (composerStartRestartGroup.changed(switchColorsColors)) {
                    }
                    i3 |= i11;
                } else {
                    switchColorsColors = switchColors;
                }
                i3 |= i11;
            } else {
                switchColorsColors = switchColors;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i3 & 599187) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    } else {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                    if (function1 != null) {
                        companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                    } else {
                        companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i1114 = i3 << 3;
                    int i1115 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier16 = modifier3;
                    SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1114 & 112) | (i1115 & 896) | (i1115 & 7168) | (i1114 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier16;
                    z5 = z4;
                    switchColors2 = switchColorsColors;
                    function5 = function4;
                    mutableInteractionSource5 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    } else {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                    if (function1 != null) {
                        companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                    } else {
                        companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i1116 = i3 << 3;
                    int i1117 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier17 = modifier3;
                    SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1116 & 112) | (i1117 & 896) | (i1117 & 7168) | (i1116 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier17;
                    z5 = z4;
                    switchColors2 = switchColorsColors;
                    function5 = function4;
                    mutableInteractionSource5 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                        public final void invoke(Composer composer3, int i1118) {
                            SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i3 & 599187) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                } else {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                if (function1 != null) {
                    companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                } else {
                    companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i1118 = i3 << 3;
                int i1119 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                Modifier modifier18 = modifier3;
                SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1118 & 112) | (i1119 & 896) | (i1119 & 7168) | (i1118 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier18;
                z5 = z4;
                switchColors2 = switchColorsColors;
                function5 = function4;
                mutableInteractionSource5 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                } else {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                if (function1 != null) {
                    companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                } else {
                    companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i11110 = i3 << 3;
                int i11111 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                Modifier modifier19 = modifier3;
                SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11110 & 112) | (i11111 & 896) | (i11111 & 7168) | (i11110 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier19;
                z5 = z4;
                switchColors2 = switchColorsColors;
                function5 = function4;
                mutableInteractionSource5 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                    public final void invoke(Composer composer3, int i11112) {
                        SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                function3 = function2;
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        switchColorsColors = switchColors;
                        if (composerStartRestartGroup.changed(switchColorsColors)) {
                        }
                        i3 |= i11;
                    } else {
                        switchColorsColors = switchColors;
                    }
                    i3 |= i11;
                } else {
                    switchColorsColors = switchColors;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 599187) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        } else {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                        if (function1 != null) {
                            companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                        } else {
                            companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i11112 = i3 << 3;
                        int i11113 = i3 >> 6;
                        composer2 = composerStartRestartGroup;
                        Modifier modifier110 = modifier3;
                        SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11112 & 112) | (i11113 & 896) | (i11113 & 7168) | (i11112 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier110;
                        z5 = z4;
                        switchColors2 = switchColorsColors;
                        function5 = function4;
                        mutableInteractionSource5 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        } else {
                            if (i10 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            function4 = function3;
                            modifier3 = companion;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                        if (function1 != null) {
                            companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                        } else {
                            companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i11114 = i3 << 3;
                        int i11115 = i3 >> 6;
                        composer2 = composerStartRestartGroup;
                        Modifier modifier111 = modifier3;
                        SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11114 & 112) | (i11115 & 896) | (i11115 & 7168) | (i11114 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier111;
                        z5 = z4;
                        switchColors2 = switchColorsColors;
                        function5 = function4;
                        mutableInteractionSource5 = mutableInteractionSource3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                            public final void invoke(Composer composer3, int i11116) {
                                SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 1572864;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((i3 & 599187) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    } else {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                    if (function1 != null) {
                        companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                    } else {
                        companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i11116 = i3 << 3;
                    int i11117 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier112 = modifier3;
                    SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11116 & 112) | (i11117 & 896) | (i11117 & 7168) | (i11116 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier112;
                    z5 = z4;
                    switchColors2 = switchColorsColors;
                    function5 = function4;
                    mutableInteractionSource5 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    } else {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                    if (function1 != null) {
                        companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                    } else {
                        companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i11118 = i3 << 3;
                    int i11119 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier113 = modifier3;
                    SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11118 & 112) | (i11119 & 896) | (i11119 & 7168) | (i11118 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier113;
                    z5 = z4;
                    switchColors2 = switchColorsColors;
                    function5 = function4;
                    mutableInteractionSource5 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                        public final void invoke(Composer composer3, int i111110) {
                            SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 24576;
            z4 = z2;
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    switchColorsColors = switchColors;
                    if (composerStartRestartGroup.changed(switchColorsColors)) {
                    }
                    i3 |= i11;
                } else {
                    switchColorsColors = switchColors;
                }
                i3 |= i11;
            } else {
                switchColorsColors = switchColors;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i3 & 599187) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    } else {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                    if (function1 != null) {
                        companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                    } else {
                        companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i111110 = i3 << 3;
                    int i111111 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier114 = modifier3;
                    SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i111110 & 112) | (i111111 & 896) | (i111111 & 7168) | (i111110 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier114;
                    z5 = z4;
                    switchColors2 = switchColorsColors;
                    function5 = function4;
                    mutableInteractionSource5 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    } else {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                    if (function1 != null) {
                        companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                    } else {
                        companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i111112 = i3 << 3;
                    int i111113 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier115 = modifier3;
                    SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i111112 & 112) | (i111113 & 896) | (i111113 & 7168) | (i111112 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier115;
                    z5 = z4;
                    switchColors2 = switchColorsColors;
                    function5 = function4;
                    mutableInteractionSource5 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                        public final void invoke(Composer composer3, int i111114) {
                            SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i3 & 599187) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                } else {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                if (function1 != null) {
                    companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                } else {
                    companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i111114 = i3 << 3;
                int i111115 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                Modifier modifier116 = modifier3;
                SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i111114 & 112) | (i111115 & 896) | (i111115 & 7168) | (i111114 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier116;
                z5 = z4;
                switchColors2 = switchColorsColors;
                function5 = function4;
                mutableInteractionSource5 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                } else {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                if (function1 != null) {
                    companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                } else {
                    companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i111116 = i3 << 3;
                int i111117 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                Modifier modifier117 = modifier3;
                SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i111116 & 112) | (i111117 & 896) | (i111117 & 7168) | (i111116 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier117;
                z5 = z4;
                switchColors2 = switchColorsColors;
                function5 = function4;
                mutableInteractionSource5 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                    public final void invoke(Composer composer3, int i111118) {
                        SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        function3 = function2;
        i6 = i2 & 16;
        if (i6 != 0) {
            if ((i & 24576) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i3 |= i7;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    switchColorsColors = switchColors;
                    if (composerStartRestartGroup.changed(switchColorsColors)) {
                    }
                    i3 |= i11;
                } else {
                    switchColorsColors = switchColors;
                }
                i3 |= i11;
            } else {
                switchColorsColors = switchColors;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i3 & 599187) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    } else {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                    if (function1 != null) {
                        companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                    } else {
                        companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i111118 = i3 << 3;
                    int i111119 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier118 = modifier3;
                    SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i111118 & 112) | (i111119 & 896) | (i111119 & 7168) | (i111118 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier118;
                    z5 = z4;
                    switchColors2 = switchColorsColors;
                    function5 = function4;
                    mutableInteractionSource5 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    } else {
                        if (i10 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        function4 = function3;
                        modifier3 = companion;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                    if (function1 != null) {
                        companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                    } else {
                        companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i1111110 = i3 << 3;
                    int i1111111 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier119 = modifier3;
                    SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1111110 & 112) | (i1111111 & 896) | (i1111111 & 7168) | (i1111110 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier119;
                    z5 = z4;
                    switchColors2 = switchColorsColors;
                    function5 = function4;
                    mutableInteractionSource5 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                        public final void invoke(Composer composer3, int i1111112) {
                            SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i3 & 599187) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                } else {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                if (function1 != null) {
                    companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                } else {
                    companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i1111112 = i3 << 3;
                int i1111113 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                Modifier modifier1110 = modifier3;
                SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1111112 & 112) | (i1111113 & 896) | (i1111113 & 7168) | (i1111112 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier1110;
                z5 = z4;
                switchColors2 = switchColorsColors;
                function5 = function4;
                mutableInteractionSource5 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                } else {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                if (function1 != null) {
                    companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                } else {
                    companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i1111114 = i3 << 3;
                int i1111115 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                Modifier modifier1111 = modifier3;
                SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1111114 & 112) | (i1111115 & 896) | (i1111115 & 7168) | (i1111114 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier1111;
                z5 = z4;
                switchColors2 = switchColorsColors;
                function5 = function4;
                mutableInteractionSource5 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                    public final void invoke(Composer composer3, int i1111116) {
                        SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 24576;
        z4 = z2;
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                switchColorsColors = switchColors;
                if (composerStartRestartGroup.changed(switchColorsColors)) {
                }
                i3 |= i11;
            } else {
                switchColorsColors = switchColors;
            }
            i3 |= i11;
        } else {
            switchColorsColors = switchColors;
        }
        i8 = i2 & 64;
        if (i8 != 0) {
            if ((1572864 & i) == 0) {
                mutableInteractionSource2 = mutableInteractionSource;
                if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            if ((i3 & 599187) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                } else {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                if (function1 != null) {
                    companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                } else {
                    companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i1111116 = i3 << 3;
                int i1111117 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                Modifier modifier1112 = modifier3;
                SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1111116 & 112) | (i1111117 & 896) | (i1111117 & 7168) | (i1111116 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier1112;
                z5 = z4;
                switchColors2 = switchColorsColors;
                function5 = function4;
                mutableInteractionSource5 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                } else {
                    if (i10 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    function4 = function3;
                    modifier3 = companion;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
                if (function1 != null) {
                    companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
                } else {
                    companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i1111118 = i3 << 3;
                int i1111119 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                Modifier modifier1113 = modifier3;
                SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1111118 & 112) | (i1111119 & 896) | (i1111119 & 7168) | (i1111118 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier1113;
                z5 = z4;
                switchColors2 = switchColorsColors;
                function5 = function4;
                mutableInteractionSource5 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                    public final void invoke(Composer composer3, int i11111110) {
                        SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 1572864;
        mutableInteractionSource2 = mutableInteractionSource;
        if ((i3 & 599187) == 599186) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i10 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    function3 = null;
                }
                if (i6 != 0) {
                    z4 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i8 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                function4 = function3;
                modifier3 = companion;
            } else {
                if (i10 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    function3 = null;
                }
                if (i6 != 0) {
                    z4 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i8 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                function4 = function3;
                modifier3 = companion;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
            }
            composerStartRestartGroup.startReplaceGroup(783532531);
            ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
            if (mutableInteractionSource3 == null) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
            if (function1 != null) {
                companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
            } else {
                companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
            }
            int i11111110 = i3 << 3;
            int i11111111 = i3 >> 6;
            composer2 = composerStartRestartGroup;
            Modifier modifier1114 = modifier3;
            SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11111110 & 112) | (i11111111 & 896) | (i11111111 & 7168) | (i11111110 & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier1114;
            z5 = z4;
            switchColors2 = switchColorsColors;
            function5 = function4;
            mutableInteractionSource5 = mutableInteractionSource3;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i10 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    function3 = null;
                }
                if (i6 != 0) {
                    z4 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i8 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                function4 = function3;
                modifier3 = companion;
            } else {
                if (i10 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    function3 = null;
                }
                if (i6 != 0) {
                    z4 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i8 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                function4 = function3;
                modifier3 = companion;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
            }
            composerStartRestartGroup.startReplaceGroup(783532531);
            ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
            if (mutableInteractionSource3 == null) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
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
            if (function1 != null) {
                companionM983toggleableO2vRcR0 = ToggleableKt.m983toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource4, null, z4, Role.m6108boximpl(Role.INSTANCE.m6121getSwitcho7Vup1c()), function1);
            } else {
                companionM983toggleableO2vRcR0 = Modifier.INSTANCE;
            }
            int i11111112 = i3 << 3;
            int i11111113 = i3 >> 6;
            composer2 = composerStartRestartGroup;
            Modifier modifier1115 = modifier3;
            SwitchImpl(SizeKt.m759requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM983toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), z, z4, switchColorsColors, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11111112 & 112) | (i11111113 & 896) | (i11111113 & 7168) | (i11111112 & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier1115;
            z5 = z4;
            switchColors2 = switchColorsColors;
            function5 = function4;
            mutableInteractionSource5 = mutableInteractionSource3;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
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

                public final void invoke(Composer composer3, int i11111114) {
                    SwitchKt.Switch(z, function1, modifier4, function5, z5, switchColors2, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SwitchImpl(final Modifier modifier, boolean z, boolean z2, SwitchColors switchColors, Function2<? super Composer, ? super Integer, Unit> function2, final InteractionSource interactionSource, final Shape shape, Composer composer, final int i) {
        int i2;
        final boolean z3 = z;
        final boolean z4 = z2;
        final SwitchColors switchColors2 = switchColors;
        final Function2<? super Composer, ? super Integer, Unit> function3 = function2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1594099146);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SwitchImpl)P(4!1,2!1,5)147@6165L5,149@6176L1114:Switch.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(z3) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changed(z4) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(switchColors2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(interactionSource) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(shape) ? 1048576 : 524288;
        }
        if ((599187 & i2) != 599186 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1594099146, i2, -1, "androidx.compose.material3.SwitchImpl (Switch.kt:144)");
            }
            long jM2292trackColorWaAFU9c$material3_release = switchColors2.m2292trackColorWaAFU9c$material3_release(z4, z3);
            long jM2291thumbColorWaAFU9c$material3_release = switchColors2.m2291thumbColorWaAFU9c$material3_release(z4, z3);
            Shape value = ShapesKt.getValue(SwitchTokens.INSTANCE.getTrackShape(), composerStartRestartGroup, 6);
            int i3 = i2;
            Modifier modifierM268backgroundbw27NRU = BackgroundKt.m268backgroundbw27NRU(BorderKt.m280borderxT4_qwU(modifier, SwitchTokens.INSTANCE.m3273getTrackOutlineWidthD9Ej5fM(), switchColors2.m2272borderColorWaAFU9c$material3_release(z4, z3), value), jM2292trackColorWaAFU9c$material3_release, value);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM268backgroundbw27NRU);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -249502072, "C161@6662L183,154@6351L933:Switch.kt#uh7d8r");
            Modifier modifierM268backgroundbw27NRU2 = BackgroundKt.m268backgroundbw27NRU(IndicationKt.indication(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart()).then(new ThumbElement(interactionSource, z3)), interactionSource, RippleKt.m2125rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6890constructorimpl(SwitchTokens.INSTANCE.m3271getStateLayerSizeD9Ej5fM() / 2.0f), 0L, composerStartRestartGroup, 54, 4)), jM2291thumbColorWaAFU9c$material3_release, shape);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM268backgroundbw27NRU2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composerStartRestartGroup);
            Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1707453249, "C:Switch.kt#uh7d8r");
            composerStartRestartGroup.startReplaceGroup(1163457794);
            ComposerKt.sourceInformation(composerStartRestartGroup, "171@7116L144");
            z3 = z;
            z4 = z2;
            switchColors2 = switchColors;
            if (function2 != null) {
                function3 = function2;
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4142boximpl(switchColors2.m2290iconColorWaAFU9c$material3_release(z4, z3))), function3, composerStartRestartGroup, ProvidedValue.$stable | ((i3 >> 9) & 112));
            } else {
                function3 = function2;
            }
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.SwitchImpl.2
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

                public final void invoke(Composer composer2, int i4) {
                    SwitchKt.SwitchImpl(modifier, z3, z4, switchColors2, function3, interactionSource, shape, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    static {
        float fM3269getSelectedHandleWidthD9Ej5fM = SwitchTokens.INSTANCE.m3269getSelectedHandleWidthD9Ej5fM();
        ThumbDiameter = fM3269getSelectedHandleWidthD9Ej5fM;
        UncheckedThumbDiameter = SwitchTokens.INSTANCE.m3276getUnselectedHandleWidthD9Ej5fM();
        SwitchWidth = SwitchTokens.INSTANCE.m3274getTrackWidthD9Ej5fM();
        float fM3272getTrackHeightD9Ej5fM = SwitchTokens.INSTANCE.m3272getTrackHeightD9Ej5fM();
        SwitchHeight = fM3272getTrackHeightD9Ej5fM;
        ThumbPadding = Dp.m6890constructorimpl(Dp.m6890constructorimpl(fM3272getTrackHeightD9Ej5fM - fM3269getSelectedHandleWidthD9Ej5fM) / 2.0f);
        SnapSpec = new SnapSpec<>(0, 1, null);
        AnimationSpec = new TweenSpec<>(100, 0, null, 6, null);
    }

    public static final float getThumbDiameter() {
        return ThumbDiameter;
    }

    public static final float getUncheckedThumbDiameter() {
        return UncheckedThumbDiameter;
    }
}
