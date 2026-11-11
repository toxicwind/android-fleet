package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.InputChipTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;

/* JADX INFO: compiled from: Chip.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J^\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u001b2\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u0011H\u0007¢\u0006\u0002\u0010$J\u0094\u0001\u0010#\u001a\u00020\u00112\b\b\u0002\u0010%\u001a\u00020\u001b2\b\b\u0002\u0010&\u001a\u00020\u001b2\b\b\u0002\u0010'\u001a\u00020\u001b2\b\b\u0002\u0010(\u001a\u00020\u001b2\b\b\u0002\u0010)\u001a\u00020\u001b2\b\b\u0002\u0010*\u001a\u00020\u001b2\b\b\u0002\u0010+\u001a\u00020\u001b2\b\b\u0002\u0010,\u001a\u00020\u001b2\b\b\u0002\u0010-\u001a\u00020\u001b2\b\b\u0002\u0010.\u001a\u00020\u001b2\b\b\u0002\u0010/\u001a\u00020\u001b2\b\b\u0002\u00100\u001a\u00020\u001b2\b\b\u0002\u00101\u001a\u00020\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b2\u00103JN\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u00020\u00042\b\b\u0002\u00107\u001a\u00020\u00042\b\b\u0002\u00108\u001a\u00020\u00042\b\b\u0002\u00109\u001a\u00020\u00042\b\b\u0002\u0010:\u001a\u00020\u00042\b\b\u0002\u0010;\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b<\u0010=R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u0011\u0010\f\u001a\u00020\r8G¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u00020\u0011*\u00020\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006>"}, d2 = {"Landroidx/compose/material3/InputChipDefaults;", "", "()V", "AvatarSize", "Landroidx/compose/ui/unit/Dp;", "getAvatarSize-D9Ej5fM", "()F", "F", "Height", "getHeight-D9Ej5fM", "IconSize", "getIconSize-D9Ej5fM", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "defaultInputChipColors", "Landroidx/compose/material3/SelectableChipColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultInputChipColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/SelectableChipColors;", "inputChipBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "selected", "borderColor", "Landroidx/compose/ui/graphics/Color;", "selectedBorderColor", "disabledBorderColor", "disabledSelectedBorderColor", "borderWidth", "selectedBorderWidth", "inputChipBorder-_7El2pE", "(ZZJJJJFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BorderStroke;", "inputChipColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/SelectableChipColors;", "containerColor", "labelColor", "leadingIconColor", "trailingIconColor", "disabledContainerColor", "disabledLabelColor", "disabledLeadingIconColor", "disabledTrailingIconColor", "selectedContainerColor", "disabledSelectedContainerColor", "selectedLabelColor", "selectedLeadingIconColor", "selectedTrailingIconColor", "inputChipColors-kwJvTHA", "(JJJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/SelectableChipColors;", "inputChipElevation", "Landroidx/compose/material3/SelectableChipElevation;", "elevation", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "inputChipElevation-aqJV_2Y", "(FFFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SelectableChipElevation;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class InputChipDefaults {
    public static final int $stable = 0;
    public static final InputChipDefaults INSTANCE = new InputChipDefaults();
    private static final float Height = InputChipTokens.INSTANCE.m3042getContainerHeightD9Ej5fM();
    private static final float IconSize = InputChipTokens.INSTANCE.m3044getLeadingIconSizeD9Ej5fM();
    private static final float AvatarSize = InputChipTokens.INSTANCE.m3040getAvatarSizeD9Ej5fM();

    private InputChipDefaults() {
    }

    /* JADX INFO: renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public final float m1880getHeightD9Ej5fM() {
        return Height;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1881getIconSizeD9Ej5fM() {
        return IconSize;
    }

    /* JADX INFO: renamed from: getAvatarSize-D9Ej5fM, reason: not valid java name */
    public final float m1879getAvatarSizeD9Ej5fM() {
        return AvatarSize;
    }

    public final SelectableChipColors inputChipColors(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -770375587, "C(inputChipColors)1568@77247L11:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-770375587, i, -1, "androidx.compose.material3.InputChipDefaults.inputChipColors (Chip.kt:1568)");
        }
        SelectableChipColors defaultInputChipColors$material3_release = getDefaultInputChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultInputChipColors$material3_release;
    }

    /* JADX INFO: renamed from: inputChipColors-kwJvTHA, reason: not valid java name */
    public final SelectableChipColors m1883inputChipColorskwJvTHA(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, Composer composer, int i, int i2, int i3) {
        ComposerKt.sourceInformationMarkerStart(composer, 1312840646, "C(inputChipColors)P(0:c#ui.graphics.Color,6:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color,5:c#ui.graphics.Color,8:c#ui.graphics.Color,4:c#ui.graphics.Color,9:c#ui.graphics.Color,10:c#ui.graphics.Color,11:c#ui.graphics.Color)1605@79387L11:Chip.kt#uh7d8r");
        long jM4188getUnspecified0d7_KjU = (i3 & 1) != 0 ? Color.INSTANCE.m4188getUnspecified0d7_KjU() : j;
        long jM4188getUnspecified0d7_KjU2 = (i3 & 2) != 0 ? Color.INSTANCE.m4188getUnspecified0d7_KjU() : j2;
        long jM4188getUnspecified0d7_KjU3 = (i3 & 4) != 0 ? Color.INSTANCE.m4188getUnspecified0d7_KjU() : j3;
        long jM4188getUnspecified0d7_KjU4 = (i3 & 8) != 0 ? Color.INSTANCE.m4188getUnspecified0d7_KjU() : j4;
        long jM4188getUnspecified0d7_KjU5 = (i3 & 16) != 0 ? Color.INSTANCE.m4188getUnspecified0d7_KjU() : j5;
        long jM4188getUnspecified0d7_KjU6 = (i3 & 32) != 0 ? Color.INSTANCE.m4188getUnspecified0d7_KjU() : j6;
        long jM4188getUnspecified0d7_KjU7 = (i3 & 64) != 0 ? Color.INSTANCE.m4188getUnspecified0d7_KjU() : j7;
        long jM4188getUnspecified0d7_KjU8 = (i3 & 128) != 0 ? Color.INSTANCE.m4188getUnspecified0d7_KjU() : j8;
        long jM4188getUnspecified0d7_KjU9 = (i3 & 256) != 0 ? Color.INSTANCE.m4188getUnspecified0d7_KjU() : j9;
        long jM4188getUnspecified0d7_KjU10 = (i3 & 512) != 0 ? Color.INSTANCE.m4188getUnspecified0d7_KjU() : j10;
        long jM4188getUnspecified0d7_KjU11 = (i3 & 1024) != 0 ? Color.INSTANCE.m4188getUnspecified0d7_KjU() : j11;
        long jM4188getUnspecified0d7_KjU12 = (i3 & 2048) != 0 ? Color.INSTANCE.m4188getUnspecified0d7_KjU() : j12;
        long jM4188getUnspecified0d7_KjU13 = (i3 & 4096) != 0 ? Color.INSTANCE.m4188getUnspecified0d7_KjU() : j13;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1312840646, i, i2, "androidx.compose.material3.InputChipDefaults.inputChipColors (Chip.kt:1605)");
        }
        SelectableChipColors selectableChipColorsM2178copydaRQuJA = getDefaultInputChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m2178copydaRQuJA(jM4188getUnspecified0d7_KjU, jM4188getUnspecified0d7_KjU2, jM4188getUnspecified0d7_KjU3, jM4188getUnspecified0d7_KjU4, jM4188getUnspecified0d7_KjU5, jM4188getUnspecified0d7_KjU6, jM4188getUnspecified0d7_KjU7, jM4188getUnspecified0d7_KjU8, jM4188getUnspecified0d7_KjU9, jM4188getUnspecified0d7_KjU10, jM4188getUnspecified0d7_KjU11, jM4188getUnspecified0d7_KjU12, jM4188getUnspecified0d7_KjU13);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return selectableChipColorsM2178copydaRQuJA;
    }

    public final SelectableChipColors getDefaultInputChipColors$material3_release(ColorScheme colorScheme) {
        SelectableChipColors defaultInputChipColorsCached = colorScheme.getDefaultInputChipColorsCached();
        if (defaultInputChipColorsCached != null) {
            return defaultInputChipColorsCached;
        }
        SelectableChipColors selectableChipColors = new SelectableChipColors(Color.INSTANCE.m4187getTransparent0d7_KjU(), ColorSchemeKt.fromToken(colorScheme, InputChipTokens.INSTANCE.getUnselectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, InputChipTokens.INSTANCE.getUnselectedLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, InputChipTokens.INSTANCE.getUnselectedTrailingIconColor()), Color.INSTANCE.m4187getTransparent0d7_KjU(), Color.m4151copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, InputChipTokens.INSTANCE.getDisabledLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), Color.m4151copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, InputChipTokens.INSTANCE.getDisabledLeadingIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), Color.m4151copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, InputChipTokens.INSTANCE.getDisabledTrailingIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, InputChipTokens.INSTANCE.getSelectedContainerColor()), Color.m4151copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, InputChipTokens.INSTANCE.getDisabledSelectedContainerColor()), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, InputChipTokens.INSTANCE.getSelectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, InputChipTokens.INSTANCE.getSelectedLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, InputChipTokens.INSTANCE.getSelectedTrailingIconColor()), null);
        colorScheme.setDefaultInputChipColorsCached$material3_release(selectableChipColors);
        return selectableChipColors;
    }

    /* JADX INFO: renamed from: inputChipElevation-aqJV_2Y, reason: not valid java name */
    public final SelectableChipElevation m1884inputChipElevationaqJV_2Y(float f, float f2, float f3, float f4, float f5, float f6, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 1745270109, "C(inputChipElevation)P(2:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,1:c#ui.unit.Dp,0:c#ui.unit.Dp):Chip.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            f = InputChipTokens.INSTANCE.m3041getContainerElevationD9Ej5fM();
        }
        float f7 = f;
        float f8 = (i2 & 2) != 0 ? f7 : f2;
        float f9 = (i2 & 4) != 0 ? f7 : f3;
        float f10 = (i2 & 8) != 0 ? f7 : f4;
        float fM3043getDraggedContainerElevationD9Ej5fM = (i2 & 16) != 0 ? InputChipTokens.INSTANCE.m3043getDraggedContainerElevationD9Ej5fM() : f5;
        float f11 = (i2 & 32) != 0 ? f7 : f6;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1745270109, i, -1, "androidx.compose.material3.InputChipDefaults.inputChipElevation (Chip.kt:1672)");
        }
        SelectableChipElevation selectableChipElevation = new SelectableChipElevation(f7, f8, f9, f10, fM3043getDraggedContainerElevationD9Ej5fM, f11, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return selectableChipElevation;
    }

    /* JADX INFO: renamed from: inputChipBorder-_7El2pE, reason: not valid java name */
    public final BorderStroke m1882inputChipBorder_7El2pE(boolean z, boolean z2, long j, long j2, long j3, long j4, float f, float f2, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 2050575347, "C(inputChipBorder)P(4,5,0:c#ui.graphics.Color,6:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color,1:c#ui.unit.Dp,7:c#ui.unit.Dp)1700@84707L5,1703@84866L5:Chip.kt#uh7d8r");
        long value = (i2 & 4) != 0 ? ColorSchemeKt.getValue(InputChipTokens.INSTANCE.getUnselectedOutlineColor(), composer, 6) : j;
        long jM4187getTransparent0d7_KjU = (i2 & 8) != 0 ? Color.INSTANCE.m4187getTransparent0d7_KjU() : j2;
        long jM4151copywmQWz5c$default = (i2 & 16) != 0 ? Color.m4151copywmQWz5c$default(ColorSchemeKt.getValue(InputChipTokens.INSTANCE.getDisabledUnselectedOutlineColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j3;
        long jM4187getTransparent0d7_KjU2 = (i2 & 32) != 0 ? Color.INSTANCE.m4187getTransparent0d7_KjU() : j4;
        float fM3047getUnselectedOutlineWidthD9Ej5fM = (i2 & 64) != 0 ? InputChipTokens.INSTANCE.m3047getUnselectedOutlineWidthD9Ej5fM() : f;
        float fM3045getSelectedOutlineWidthD9Ej5fM = (i2 & 128) != 0 ? InputChipTokens.INSTANCE.m3045getSelectedOutlineWidthD9Ej5fM() : f2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2050575347, i, -1, "androidx.compose.material3.InputChipDefaults.inputChipBorder (Chip.kt:1709)");
        }
        if (!z) {
            value = z2 ? jM4187getTransparent0d7_KjU2 : jM4151copywmQWz5c$default;
        } else if (z2) {
            value = jM4187getTransparent0d7_KjU;
        }
        if (z2) {
            fM3047getUnselectedOutlineWidthD9Ej5fM = fM3045getSelectedOutlineWidthD9Ej5fM;
        }
        BorderStroke borderStrokeM296BorderStrokecXLIe8U = BorderStrokeKt.m296BorderStrokecXLIe8U(fM3047getUnselectedOutlineWidthD9Ej5fM, value);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return borderStrokeM296BorderStrokecXLIe8U;
    }

    public final Shape getShape(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1052444143, "C1721@85639L5:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1052444143, i, -1, "androidx.compose.material3.InputChipDefaults.<get-shape> (Chip.kt:1721)");
        }
        Shape value = ShapesKt.getValue(InputChipTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return value;
    }
}
