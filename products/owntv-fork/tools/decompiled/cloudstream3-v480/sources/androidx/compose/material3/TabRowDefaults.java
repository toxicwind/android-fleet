package androidx.compose.material3;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.tokens.PrimaryNavigationTabTokens;
import androidx.compose.material3.tokens.SecondaryNavigationTabTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: TabRow.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00042\b\b\u0002\u0010\u001d\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJD\u0010 \u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u00042\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\"\u001a\u00020#H\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%J0\u0010&\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00042\b\b\u0002\u0010\u001d\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b'\u0010\u001fJ\u0012\u0010(\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010)\u001a\u00020*R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R \u0010\b\u001a\u00020\t8GX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\fR \u0010\r\u001a\u00020\t8GX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u000e\u0010\u0002\u001a\u0004\b\u000f\u0010\fR\u0017\u0010\u0010\u001a\u00020\t8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0011\u0010\fR\u0017\u0010\u0012\u001a\u00020\t8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0013\u0010\fR\u0017\u0010\u0014\u001a\u00020\t8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\fR\u0017\u0010\u0016\u001a\u00020\t8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0017\u0010\f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006+²\u0006\n\u0010,\u001a\u00020\u0004X\u008a\u0084\u0002²\u0006\n\u0010-\u001a\u00020\u0004X\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/TabRowDefaults;", "", "()V", "ScrollableTabRowEdgeStartPadding", "Landroidx/compose/ui/unit/Dp;", "getScrollableTabRowEdgeStartPadding-D9Ej5fM", "()F", "F", "containerColor", "Landroidx/compose/ui/graphics/Color;", "getContainerColor$annotations", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "contentColor", "getContentColor$annotations", "getContentColor", "primaryContainerColor", "getPrimaryContainerColor", "primaryContentColor", "getPrimaryContentColor", "secondaryContainerColor", "getSecondaryContainerColor", "secondaryContentColor", "getSecondaryContentColor", "Indicator", "", "modifier", "Landroidx/compose/ui/Modifier;", "height", "color", "Indicator-9IZ8Weo", "(Landroidx/compose/ui/Modifier;FJLandroidx/compose/runtime/Composer;II)V", "PrimaryIndicator", "width", "shape", "Landroidx/compose/ui/graphics/Shape;", "PrimaryIndicator-10LGxhE", "(Landroidx/compose/ui/Modifier;FFJLandroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)V", "SecondaryIndicator", "SecondaryIndicator-9IZ8Weo", "tabIndicatorOffset", "currentTabPosition", "Landroidx/compose/material3/TabPosition;", "material3_release", "currentTabWidth", "indicatorOffset"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TabRowDefaults {
    public static final int $stable = 0;
    public static final TabRowDefaults INSTANCE = new TabRowDefaults();
    private static final float ScrollableTabRowEdgeStartPadding = Dp.m6890constructorimpl(52.0f);

    @Deprecated(message = "Use TabRowDefaults.primaryContainerColor instead", replaceWith = @ReplaceWith(expression = "primaryContainerColor", imports = {}))
    public static /* synthetic */ void getContainerColor$annotations() {
    }

    @Deprecated(message = "Use TabRowDefaults.primaryContentColor instead", replaceWith = @ReplaceWith(expression = "primaryContentColor", imports = {}))
    public static /* synthetic */ void getContentColor$annotations() {
    }

    private TabRowDefaults() {
    }

    /* JADX INFO: renamed from: getScrollableTabRowEdgeStartPadding-D9Ej5fM, reason: not valid java name */
    public final float m2307getScrollableTabRowEdgeStartPaddingD9Ej5fM() {
        return ScrollableTabRowEdgeStartPadding;
    }

    public final long getContainerColor(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -2026555673, "C1159@49855L5:TabRow.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2026555673, i, -1, "androidx.compose.material3.TabRowDefaults.<get-containerColor> (TabRow.kt:1159)");
        }
        long value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return value;
    }

    public final long getPrimaryContainerColor(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -2069154037, "C1163@50026L5:TabRow.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2069154037, i, -1, "androidx.compose.material3.TabRowDefaults.<get-primaryContainerColor> (TabRow.kt:1163)");
        }
        long value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return value;
    }

    public final long getSecondaryContainerColor(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -1938007129, "C1167@50203L5:TabRow.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1938007129, i, -1, "androidx.compose.material3.TabRowDefaults.<get-secondaryContainerColor> (TabRow.kt:1167)");
        }
        long value = ColorSchemeKt.getValue(SecondaryNavigationTabTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return value;
    }

    public final long getContentColor(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1163072359, "C1175@50509L5:TabRow.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1163072359, i, -1, "androidx.compose.material3.TabRowDefaults.<get-contentColor> (TabRow.kt:1175)");
        }
        long value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveLabelTextColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return value;
    }

    public final long getPrimaryContentColor(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1410362619, "C1179@50682L5:TabRow.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1410362619, i, -1, "androidx.compose.material3.TabRowDefaults.<get-primaryContentColor> (TabRow.kt:1179)");
        }
        long value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveLabelTextColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return value;
    }

    public final long getSecondaryContentColor(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1166419479, "C1183@50861L5:TabRow.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1166419479, i, -1, "androidx.compose.material3.TabRowDefaults.<get-secondaryContentColor> (TabRow.kt:1183)");
        }
        long value = ColorSchemeKt.getValue(SecondaryNavigationTabTokens.INSTANCE.getActiveLabelTextColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return value;
    }

    /* JADX WARN: Code duplicated, block: B:58:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:61:0x00d6  */
    @Deprecated(message = "Use SecondaryIndicator instead.", replaceWith = @ReplaceWith(expression = "SecondaryIndicator(modifier, height, color)", imports = {}))
    /* JADX INFO: renamed from: Indicator-9IZ8Weo, reason: not valid java name */
    public final void m2304Indicator9IZ8Weo(Modifier modifier, float f, long j, Composer composer, final int i, final int i2) {
        int i3;
        long j2;
        long jFromToken;
        final long j3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1454716052);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Indicator)P(2,1:c#ui.unit.Dp,0:c#ui.graphics.Color)1202@51501L11,1204@51588L69:TabRow.kt#uh7d8r");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i5 = i2 & 2;
        if (i5 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            j2 = j;
            i3 |= ((i2 & 4) == 0 && composerStartRestartGroup.changed(j2)) ? 256 : 128;
        } else {
            j2 = j;
        }
        if ((i3 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
            } else {
                if (i4 != 0) {
                    modifier = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    f = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    jFromToken = ColorSchemeKt.fromToken(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6), PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor());
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1454716052, i3, -1, "androidx.compose.material3.TabRowDefaults.Indicator (TabRow.kt:1203)");
                }
                BoxKt.Box(BackgroundKt.m269backgroundbw27NRU$default(SizeKt.m751height3ABfNKs(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), f), jFromToken, null, 2, null), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = jFromToken;
            }
            jFromToken = j2;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1454716052, i3, -1, "androidx.compose.material3.TabRowDefaults.Indicator (TabRow.kt:1203)");
            }
            BoxKt.Box(BackgroundKt.m269backgroundbw27NRU$default(SizeKt.m751height3ABfNKs(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), f), jFromToken, null, 2, null), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = jFromToken;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            j3 = j2;
        }
        final Modifier modifier2 = modifier;
        final float f2 = f;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowDefaults$Indicator$1
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
                    this.$tmp0_rcvr.m2304Indicator9IZ8Weo(modifier2, f2, j3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x004e  */
    /* JADX WARN: Code duplicated, block: B:27:0x0051  */
    /* JADX WARN: Code duplicated, block: B:29:0x0055  */
    /* JADX WARN: Code duplicated, block: B:31:0x005d  */
    /* JADX WARN: Code duplicated, block: B:32:0x0060  */
    /* JADX WARN: Code duplicated, block: B:37:0x006a  */
    /* JADX WARN: Code duplicated, block: B:39:0x006e  */
    /* JADX WARN: Code duplicated, block: B:41:0x0076  */
    /* JADX WARN: Code duplicated, block: B:42:0x0079  */
    /* JADX WARN: Code duplicated, block: B:45:0x007f  */
    /* JADX WARN: Code duplicated, block: B:48:0x0085  */
    /* JADX WARN: Code duplicated, block: B:49:0x0088  */
    /* JADX WARN: Code duplicated, block: B:51:0x008c  */
    /* JADX WARN: Code duplicated, block: B:53:0x0094  */
    /* JADX WARN: Code duplicated, block: B:54:0x0097  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:66:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:73:0x00cd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:74:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:79:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:82:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:84:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:87:0x010c  */
    /* JADX WARN: Code duplicated, block: B:90:0x0128  */
    /* JADX WARN: Code duplicated, block: B:93:0x0132  */
    /* JADX WARN: Code duplicated, block: B:95:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: PrimaryIndicator-10LGxhE, reason: not valid java name */
    public final void m2305PrimaryIndicator10LGxhE(Modifier modifier, float f, float f2, long j, Shape shape, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        float fM6890constructorimpl;
        int i4;
        float fM3206getActiveIndicatorHeightD9Ej5fM;
        int i5;
        long value;
        int i6;
        Shape activeIndicatorShape;
        int i7;
        final Modifier.Companion companion;
        final float f3;
        final float f4;
        final long j2;
        final Shape shape2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1895596205);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PrimaryIndicator)P(2,4:c#ui.unit.Dp,1:c#ui.unit.Dp,0:c#ui.graphics.Color)1222@52283L5,1225@52377L174:TabRow.kt#uh7d8r");
        int i8 = i2 & 1;
        if (i8 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        int i9 = i2 & 2;
        if (i9 == 0) {
            if ((i & 48) == 0) {
                fM6890constructorimpl = f;
                i3 |= composerStartRestartGroup.changed(fM6890constructorimpl) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    fM3206getActiveIndicatorHeightD9Ej5fM = f2;
                    if (composerStartRestartGroup.changed(fM3206getActiveIndicatorHeightD9Ej5fM)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        value = j;
                        int i10 = composerStartRestartGroup.changed(value) ? 2048 : 1024;
                        i3 |= i10;
                    } else {
                        value = j;
                    }
                    i3 |= i10;
                } else {
                    value = j;
                }
                i6 = i2 & 16;
                if (i6 != 0) {
                    if ((i & 24576) == 0) {
                        activeIndicatorShape = shape;
                        if (composerStartRestartGroup.changed(activeIndicatorShape)) {
                            i7 = 16384;
                        } else {
                            i7 = 8192;
                        }
                        i3 |= i7;
                    }
                    if ((i3 & 9363) == 9362 || !composerStartRestartGroup.getSkipping()) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i8 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i9 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                            }
                            if (i4 != 0) {
                                fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                            }
                            if ((i2 & 8) != 0) {
                                value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                                i3 &= -7169;
                            }
                            if (i6 != 0) {
                                activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                            }
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                            }
                            companion = modifier2;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
                        }
                        SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        companion = modifier2;
                    }
                    f3 = fM6890constructorimpl;
                    f4 = fM3206getActiveIndicatorHeightD9Ej5fM;
                    j2 = value;
                    shape2 = activeIndicatorShape;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowDefaults$PrimaryIndicator$1
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
                                this.$tmp0_rcvr.m2305PrimaryIndicator10LGxhE(companion, f3, f4, j2, shape2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 24576;
                activeIndicatorShape = shape;
                if ((i3 & 9363) == 9362) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i9 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i9 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
                    }
                    SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i9 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i9 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
                    }
                    SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                f3 = fM6890constructorimpl;
                f4 = fM3206getActiveIndicatorHeightD9Ej5fM;
                j2 = value;
                shape2 = activeIndicatorShape;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowDefaults$PrimaryIndicator$1
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
                            this.$tmp0_rcvr.m2305PrimaryIndicator10LGxhE(companion, f3, f4, j2, shape2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            fM3206getActiveIndicatorHeightD9Ej5fM = f2;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    value = j;
                    if (composerStartRestartGroup.changed(value)) {
                    }
                    i3 |= i10;
                } else {
                    value = j;
                }
                i3 |= i10;
            } else {
                value = j;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    activeIndicatorShape = shape;
                    if (composerStartRestartGroup.changed(activeIndicatorShape)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                if ((i3 & 9363) == 9362) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i9 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i9 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
                    }
                    SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i9 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i9 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
                    }
                    SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                f3 = fM6890constructorimpl;
                f4 = fM3206getActiveIndicatorHeightD9Ej5fM;
                j2 = value;
                shape2 = activeIndicatorShape;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowDefaults$PrimaryIndicator$1
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
                            this.$tmp0_rcvr.m2305PrimaryIndicator10LGxhE(companion, f3, f4, j2, shape2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 24576;
            activeIndicatorShape = shape;
            if ((i3 & 9363) == 9362) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
                }
                SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
                }
                SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            f3 = fM6890constructorimpl;
            f4 = fM3206getActiveIndicatorHeightD9Ej5fM;
            j2 = value;
            shape2 = activeIndicatorShape;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowDefaults$PrimaryIndicator$1
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
                        this.$tmp0_rcvr.m2305PrimaryIndicator10LGxhE(companion, f3, f4, j2, shape2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        fM6890constructorimpl = f;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                fM3206getActiveIndicatorHeightD9Ej5fM = f2;
                if (composerStartRestartGroup.changed(fM3206getActiveIndicatorHeightD9Ej5fM)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    value = j;
                    if (composerStartRestartGroup.changed(value)) {
                    }
                    i3 |= i10;
                } else {
                    value = j;
                }
                i3 |= i10;
            } else {
                value = j;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    activeIndicatorShape = shape;
                    if (composerStartRestartGroup.changed(activeIndicatorShape)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                if ((i3 & 9363) == 9362) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i9 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i9 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
                    }
                    SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i9 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i9 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
                    }
                    SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                f3 = fM6890constructorimpl;
                f4 = fM3206getActiveIndicatorHeightD9Ej5fM;
                j2 = value;
                shape2 = activeIndicatorShape;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowDefaults$PrimaryIndicator$1
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
                            this.$tmp0_rcvr.m2305PrimaryIndicator10LGxhE(companion, f3, f4, j2, shape2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 24576;
            activeIndicatorShape = shape;
            if ((i3 & 9363) == 9362) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
                }
                SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
                }
                SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            f3 = fM6890constructorimpl;
            f4 = fM3206getActiveIndicatorHeightD9Ej5fM;
            j2 = value;
            shape2 = activeIndicatorShape;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowDefaults$PrimaryIndicator$1
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
                        this.$tmp0_rcvr.m2305PrimaryIndicator10LGxhE(companion, f3, f4, j2, shape2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        fM3206getActiveIndicatorHeightD9Ej5fM = f2;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                value = j;
                if (composerStartRestartGroup.changed(value)) {
                }
                i3 |= i10;
            } else {
                value = j;
            }
            i3 |= i10;
        } else {
            value = j;
        }
        i6 = i2 & 16;
        if (i6 != 0) {
            if ((i & 24576) == 0) {
                activeIndicatorShape = shape;
                if (composerStartRestartGroup.changed(activeIndicatorShape)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i3 |= i7;
            }
            if ((i3 & 9363) == 9362) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
                }
                SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
                }
                SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            f3 = fM6890constructorimpl;
            f4 = fM3206getActiveIndicatorHeightD9Ej5fM;
            j2 = value;
            shape2 = activeIndicatorShape;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowDefaults$PrimaryIndicator$1
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
                        this.$tmp0_rcvr.m2305PrimaryIndicator10LGxhE(companion, f3, f4, j2, shape2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 24576;
        activeIndicatorShape = shape;
        if ((i3 & 9363) == 9362) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i9 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                }
                if (i4 != 0) {
                    fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                }
                if ((i2 & 8) != 0) {
                    value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                    i3 &= -7169;
                }
                if (i6 != 0) {
                    activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                }
            } else {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i9 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                }
                if (i4 != 0) {
                    fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                }
                if ((i2 & 8) != 0) {
                    value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                    i3 &= -7169;
                }
                if (i6 != 0) {
                    activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
            }
            SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i9 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                }
                if (i4 != 0) {
                    fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                }
                if ((i2 & 8) != 0) {
                    value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                    i3 &= -7169;
                }
                if (i6 != 0) {
                    activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                }
            } else {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i9 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(24.0f);
                }
                if (i4 != 0) {
                    fM3206getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                }
                if ((i2 & 8) != 0) {
                    value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                    i3 &= -7169;
                }
                if (i6 != 0) {
                    activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1224)");
            }
            SpacerKt.Spacer(BackgroundKt.m268backgroundbw27NRU(SizeKt.m762requiredWidth3ABfNKs(SizeKt.m754requiredHeight3ABfNKs(companion, fM3206getActiveIndicatorHeightD9Ej5fM), fM6890constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        f3 = fM6890constructorimpl;
        f4 = fM3206getActiveIndicatorHeightD9Ej5fM;
        j2 = value;
        shape2 = activeIndicatorShape;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowDefaults$PrimaryIndicator$1
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
                    this.$tmp0_rcvr.m2305PrimaryIndicator10LGxhE(companion, f3, f4, j2, shape2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:58:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:61:0x00d0  */
    /* JADX INFO: renamed from: SecondaryIndicator-9IZ8Weo, reason: not valid java name */
    public final void m2306SecondaryIndicator9IZ8Weo(Modifier modifier, float f, long j, Composer composer, final int i, final int i2) {
        int i3;
        long j2;
        long value;
        final long j3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1498258020);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SecondaryIndicator)P(2,1:c#ui.unit.Dp,0:c#ui.graphics.Color)1245@53068L5,1247@53090L69:TabRow.kt#uh7d8r");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i5 = i2 & 2;
        if (i5 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            j2 = j;
            i3 |= ((i2 & 4) == 0 && composerStartRestartGroup.changed(j2)) ? 256 : 128;
        } else {
            j2 = j;
        }
        if ((i3 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
            } else {
                if (i4 != 0) {
                    modifier = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    f = PrimaryNavigationTabTokens.INSTANCE.m3206getActiveIndicatorHeightD9Ej5fM();
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1498258020, i3, -1, "androidx.compose.material3.TabRowDefaults.SecondaryIndicator (TabRow.kt:1246)");
                }
                BoxKt.Box(BackgroundKt.m269backgroundbw27NRU$default(SizeKt.m751height3ABfNKs(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), f), value, null, 2, null), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = value;
            }
            value = j2;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1498258020, i3, -1, "androidx.compose.material3.TabRowDefaults.SecondaryIndicator (TabRow.kt:1246)");
            }
            BoxKt.Box(BackgroundKt.m269backgroundbw27NRU$default(SizeKt.m751height3ABfNKs(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), f), value, null, 2, null), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = value;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            j3 = j2;
        }
        final Modifier modifier2 = modifier;
        final float f2 = f;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowDefaults$SecondaryIndicator$1
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
                    this.$tmp0_rcvr.m2306SecondaryIndicator9IZ8Weo(modifier2, f2, j3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TabRowDefaults$tabIndicatorOffset$2, reason: invalid class name */
    /* JADX INFO: compiled from: TabRow.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    static final class AnonymousClass2 extends Lambda implements Function3<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ TabPosition $currentTabPosition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(TabPosition tabPosition) {
            super(3);
            this.$currentTabPosition = tabPosition;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
            return invoke(modifier, composer, num.intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i) {
            composer.startReplaceGroup(-1541271084);
            ComposerKt.sourceInformation(composer, "C1266@53909L151,1271@54112L150,1277@54370L53:TabRow.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1541271084, i, -1, "androidx.compose.material3.TabRowDefaults.tabIndicatorOffset.<anonymous> (TabRow.kt:1265)");
            }
            State<Dp> stateM160animateDpAsStateAjpBEmI = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(this.$currentTabPosition.getWidth(), TabRowKt.TabRowIndicatorSpec, null, null, composer, 0, 12);
            final State<Dp> stateM160animateDpAsStateAjpBEmI2 = AnimateAsStateKt.m160animateDpAsStateAjpBEmI(this.$currentTabPosition.getLeft(), TabRowKt.TabRowIndicatorSpec, null, null, composer, 0, 12);
            Modifier modifierWrapContentSize$default = SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), Alignment.INSTANCE.getBottomStart(), false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, -1825077707, "CC(remember):TabRow.kt#9igjgp");
            boolean zChanged = composer.changed(stateM160animateDpAsStateAjpBEmI2);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material3.TabRowDefaults$tabIndicatorOffset$2$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ IntOffset invoke(Density density) {
                        return IntOffset.m7013boximpl(m2308invokeBjo55l4(density));
                    }

                    /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                    public final long m2308invokeBjo55l4(Density density) {
                        return IntOffsetKt.IntOffset(density.mo400roundToPx0680j_4(TabRowDefaults.AnonymousClass2.invoke$lambda$1(stateM160animateDpAsStateAjpBEmI2)), 0);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            Modifier modifierM770width3ABfNKs = SizeKt.m770width3ABfNKs(OffsetKt.offset(modifierWrapContentSize$default, (Function1) objRememberedValue), invoke$lambda$0(stateM160animateDpAsStateAjpBEmI));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceGroup();
            return modifierM770width3ABfNKs;
        }

        private static final float invoke$lambda$0(State<Dp> state) {
            return state.getValue().m6904unboximpl();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float invoke$lambda$1(State<Dp> state) {
            return state.getValue().m6904unboximpl();
        }
    }

    public final Modifier tabIndicatorOffset(Modifier modifier, final TabPosition tabPosition) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TabRowDefaults$tabIndicatorOffset$$inlined$debugInspectorInfo$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("tabIndicatorOffset");
                inspectorInfo.setValue(tabPosition);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new AnonymousClass2(tabPosition));
    }
}
