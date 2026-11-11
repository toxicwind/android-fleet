package androidx.compose.material3;

import androidx.compose.material3.tokens.ColorDarkTokens;
import androidx.compose.material3.tokens.ColorLightTokens;
import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.material3.tokens.PaletteTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ColorScheme.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b5\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u001a\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a´\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010&\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b2\b\b\u0002\u0010)\u001a\u00020\u000b2\b\b\u0002\u0010*\u001a\u00020\u000b2\b\b\u0002\u0010+\u001a\u00020\u000b2\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u000b2\b\b\u0002\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010/\u001a\u00020\u000b2\b\b\u0002\u00100\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001aø\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010&\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b2\b\b\u0002\u0010)\u001a\u00020\u000b2\b\b\u0002\u0010*\u001a\u00020\u000b2\b\b\u0002\u0010+\u001a\u00020\u000b2\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u000b2\b\b\u0002\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010/\u001a\u00020\u000b2\b\b\u0002\u00100\u001a\u00020\u000b2\b\b\u0002\u00103\u001a\u00020\u000b2\b\b\u0002\u00104\u001a\u00020\u000b2\b\b\u0002\u00105\u001a\u00020\u000b2\b\b\u0002\u00106\u001a\u00020\u000b2\b\b\u0002\u00107\u001a\u00020\u000b2\b\b\u0002\u00108\u001a\u00020\u000b2\b\b\u0002\u00109\u001a\u00020\u000bø\u0001\u0000¢\u0006\u0004\b:\u0010;\u001a\b\u0010<\u001a\u00020\u0004H\u0000\u001a´\u0002\u0010=\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010&\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b2\b\b\u0002\u0010)\u001a\u00020\u000b2\b\b\u0002\u0010*\u001a\u00020\u000b2\b\b\u0002\u0010+\u001a\u00020\u000b2\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u000b2\b\b\u0002\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010/\u001a\u00020\u000b2\b\b\u0002\u00100\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b>\u00102\u001aø\u0002\u0010=\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010&\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b2\b\b\u0002\u0010)\u001a\u00020\u000b2\b\b\u0002\u0010*\u001a\u00020\u000b2\b\b\u0002\u0010+\u001a\u00020\u000b2\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u000b2\b\b\u0002\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010/\u001a\u00020\u000b2\b\b\u0002\u00100\u001a\u00020\u000b2\b\b\u0002\u00103\u001a\u00020\u000b2\b\b\u0002\u00104\u001a\u00020\u000b2\b\b\u0002\u00105\u001a\u00020\u000b2\b\b\u0002\u00106\u001a\u00020\u000b2\b\b\u0002\u00107\u001a\u00020\u000b2\b\b\u0002\u00108\u001a\u00020\u000b2\b\b\u0002\u00109\u001a\u00020\u000bø\u0001\u0000¢\u0006\u0004\b?\u0010;\u001a&\u0010@\u001a\u00020\u000b*\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010A\u001a\u00020BH\u0001ø\u0001\u0000¢\u0006\u0004\bC\u0010D\u001a\u001e\u0010\u000f\u001a\u00020\u000b*\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\bE\u0010F\u001a\u0019\u0010G\u001a\u00020\u000b*\u00020\u00042\u0006\u0010\n\u001a\u00020\fH\u0001¢\u0006\u0002\u0010H\u001a\u001e\u0010I\u001a\u00020\u000b*\u00020\u00042\u0006\u0010A\u001a\u00020BH\u0007ø\u0001\u0000¢\u0006\u0004\bJ\u0010K\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0006\"\u0018\u0010\n\u001a\u00020\u000b*\u00020\f8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006L"}, d2 = {"DisabledAlpha", "", "LocalColorScheme", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/ColorScheme;", "getLocalColorScheme", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalTonalElevationEnabled", "", "getLocalTonalElevationEnabled", ES6Iterator.VALUE_PROPERTY, "Landroidx/compose/ui/graphics/Color;", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getValue", "(Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;Landroidx/compose/runtime/Composer;I)J", "contentColorFor", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "contentColorFor-ek8zF_U", "(JLandroidx/compose/runtime/Composer;I)J", "darkColorScheme", "primary", "onPrimary", "primaryContainer", "onPrimaryContainer", "inversePrimary", "secondary", "onSecondary", "secondaryContainer", "onSecondaryContainer", "tertiary", "onTertiary", "tertiaryContainer", "onTertiaryContainer", "background", "onBackground", "surface", "onSurface", "surfaceVariant", "onSurfaceVariant", "surfaceTint", "inverseSurface", "inverseOnSurface", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "onError", "errorContainer", "onErrorContainer", "outline", "outlineVariant", "scrim", "darkColorScheme-G1PFc-w", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJ)Landroidx/compose/material3/ColorScheme;", "surfaceBright", "surfaceContainer", "surfaceContainerHigh", "surfaceContainerHighest", "surfaceContainerLow", "surfaceContainerLowest", "surfaceDim", "darkColorScheme-C-Xl9yA", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ)Landroidx/compose/material3/ColorScheme;", "expressiveLightColorScheme", "lightColorScheme", "lightColorScheme-G1PFc-w", "lightColorScheme-C-Xl9yA", "applyTonalElevation", "elevation", "Landroidx/compose/ui/unit/Dp;", "applyTonalElevation-RFCenO8", "(Landroidx/compose/material3/ColorScheme;JFLandroidx/compose/runtime/Composer;I)J", "contentColorFor-4WTKRHQ", "(Landroidx/compose/material3/ColorScheme;J)J", "fromToken", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;)J", "surfaceColorAtElevation", "surfaceColorAtElevation-3ABfNKs", "(Landroidx/compose/material3/ColorScheme;F)J", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ColorSchemeKt {
    public static final float DisabledAlpha = 0.38f;
    private static final ProvidableCompositionLocal<ColorScheme> LocalColorScheme = CompositionLocalKt.staticCompositionLocalOf(new Function0<ColorScheme>() { // from class: androidx.compose.material3.ColorSchemeKt$LocalColorScheme$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ColorScheme invoke() {
            return ColorSchemeKt.m1653lightColorSchemeCXl9yA$default(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -1, 15, null);
        }
    });
    private static final ProvidableCompositionLocal<Boolean> LocalTonalElevationEnabled = CompositionLocalKt.staticCompositionLocalOf(new Function0<Boolean>() { // from class: androidx.compose.material3.ColorSchemeKt$LocalTonalElevationEnabled$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return true;
        }
    });

    /* JADX INFO: compiled from: ColorScheme.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ColorSchemeKeyTokens.values().length];
            try {
                iArr[ColorSchemeKeyTokens.Background.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Error.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ColorSchemeKeyTokens.ErrorContainer.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ColorSchemeKeyTokens.InverseOnSurface.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ColorSchemeKeyTokens.InversePrimary.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ColorSchemeKeyTokens.InverseSurface.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnBackground.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnError.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnErrorContainer.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnPrimary.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnPrimaryContainer.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSecondary.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSecondaryContainer.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSurface.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSurfaceVariant.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceTint.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnTertiary.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnTertiaryContainer.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Outline.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OutlineVariant.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Primary.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr[ColorSchemeKeyTokens.PrimaryContainer.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Scrim.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Secondary.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SecondaryContainer.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Surface.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceVariant.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceBright.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainer.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerHigh.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerHighest.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerLow.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerLowest.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceDim.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Tertiary.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                iArr[ColorSchemeKeyTokens.TertiaryContainer.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: lightColorScheme-C-Xl9yA$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m1653lightColorSchemeCXl9yA$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, int i, int i2, Object obj) {
        long jM2859getPrimary0d7_KjU = (i & 1) != 0 ? ColorLightTokens.INSTANCE.m2859getPrimary0d7_KjU() : j;
        long jM2843getOnPrimary0d7_KjU = (i & 2) != 0 ? ColorLightTokens.INSTANCE.m2843getOnPrimary0d7_KjU() : j2;
        long jM2860getPrimaryContainer0d7_KjU = (i & 4) != 0 ? ColorLightTokens.INSTANCE.m2860getPrimaryContainer0d7_KjU() : j3;
        long jM2844getOnPrimaryContainer0d7_KjU = (i & 8) != 0 ? ColorLightTokens.INSTANCE.m2844getOnPrimaryContainer0d7_KjU() : j4;
        long jM2838getInversePrimary0d7_KjU = (i & 16) != 0 ? ColorLightTokens.INSTANCE.m2838getInversePrimary0d7_KjU() : j5;
        long jM2864getSecondary0d7_KjU = (i & 32) != 0 ? ColorLightTokens.INSTANCE.m2864getSecondary0d7_KjU() : j6;
        long jM2847getOnSecondary0d7_KjU = (i & 64) != 0 ? ColorLightTokens.INSTANCE.m2847getOnSecondary0d7_KjU() : j7;
        long jM2865getSecondaryContainer0d7_KjU = (i & 128) != 0 ? ColorLightTokens.INSTANCE.m2865getSecondaryContainer0d7_KjU() : j8;
        long j37 = jM2859getPrimary0d7_KjU;
        long jM2848getOnSecondaryContainer0d7_KjU = (i & 256) != 0 ? ColorLightTokens.INSTANCE.m2848getOnSecondaryContainer0d7_KjU() : j9;
        long jM2878getTertiary0d7_KjU = (i & 512) != 0 ? ColorLightTokens.INSTANCE.m2878getTertiary0d7_KjU() : j10;
        long jM2853getOnTertiary0d7_KjU = (i & 1024) != 0 ? ColorLightTokens.INSTANCE.m2853getOnTertiary0d7_KjU() : j11;
        long jM2879getTertiaryContainer0d7_KjU = (i & 2048) != 0 ? ColorLightTokens.INSTANCE.m2879getTertiaryContainer0d7_KjU() : j12;
        long jM2854getOnTertiaryContainer0d7_KjU = (i & 4096) != 0 ? ColorLightTokens.INSTANCE.m2854getOnTertiaryContainer0d7_KjU() : j13;
        long jM2834getBackground0d7_KjU = (i & 8192) != 0 ? ColorLightTokens.INSTANCE.m2834getBackground0d7_KjU() : j14;
        long jM2840getOnBackground0d7_KjU = (i & 16384) != 0 ? ColorLightTokens.INSTANCE.m2840getOnBackground0d7_KjU() : j15;
        long jM2868getSurface0d7_KjU = (i & 32768) != 0 ? ColorLightTokens.INSTANCE.m2868getSurface0d7_KjU() : j16;
        long jM2851getOnSurface0d7_KjU = (i & 65536) != 0 ? ColorLightTokens.INSTANCE.m2851getOnSurface0d7_KjU() : j17;
        long jM2877getSurfaceVariant0d7_KjU = (i & 131072) != 0 ? ColorLightTokens.INSTANCE.m2877getSurfaceVariant0d7_KjU() : j18;
        long jM2852getOnSurfaceVariant0d7_KjU = (i & 262144) != 0 ? ColorLightTokens.INSTANCE.m2852getOnSurfaceVariant0d7_KjU() : j19;
        long j38 = (i & 524288) != 0 ? j37 : j20;
        long jM2839getInverseSurface0d7_KjU = (i & 1048576) != 0 ? ColorLightTokens.INSTANCE.m2839getInverseSurface0d7_KjU() : j21;
        long jM2837getInverseOnSurface0d7_KjU = (i & 2097152) != 0 ? ColorLightTokens.INSTANCE.m2837getInverseOnSurface0d7_KjU() : j22;
        long jM2835getError0d7_KjU = (i & 4194304) != 0 ? ColorLightTokens.INSTANCE.m2835getError0d7_KjU() : j23;
        long jM2841getOnError0d7_KjU = (i & 8388608) != 0 ? ColorLightTokens.INSTANCE.m2841getOnError0d7_KjU() : j24;
        long jM2836getErrorContainer0d7_KjU = (i & 16777216) != 0 ? ColorLightTokens.INSTANCE.m2836getErrorContainer0d7_KjU() : j25;
        long jM2842getOnErrorContainer0d7_KjU = (i & 33554432) != 0 ? ColorLightTokens.INSTANCE.m2842getOnErrorContainer0d7_KjU() : j26;
        long jM2857getOutline0d7_KjU = (i & 67108864) != 0 ? ColorLightTokens.INSTANCE.m2857getOutline0d7_KjU() : j27;
        long jM2858getOutlineVariant0d7_KjU = (i & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? ColorLightTokens.INSTANCE.m2858getOutlineVariant0d7_KjU() : j28;
        long jM2863getScrim0d7_KjU = (i & 268435456) != 0 ? ColorLightTokens.INSTANCE.m2863getScrim0d7_KjU() : j29;
        long jM2869getSurfaceBright0d7_KjU = (i & 536870912) != 0 ? ColorLightTokens.INSTANCE.m2869getSurfaceBright0d7_KjU() : j30;
        long jM2870getSurfaceContainer0d7_KjU = (i & 1073741824) != 0 ? ColorLightTokens.INSTANCE.m2870getSurfaceContainer0d7_KjU() : j31;
        long jM2871getSurfaceContainerHigh0d7_KjU = (i & Integer.MIN_VALUE) != 0 ? ColorLightTokens.INSTANCE.m2871getSurfaceContainerHigh0d7_KjU() : j32;
        long jM2872getSurfaceContainerHighest0d7_KjU = (i2 & 1) != 0 ? ColorLightTokens.INSTANCE.m2872getSurfaceContainerHighest0d7_KjU() : j33;
        long jM2873getSurfaceContainerLow0d7_KjU = (i2 & 2) != 0 ? ColorLightTokens.INSTANCE.m2873getSurfaceContainerLow0d7_KjU() : j34;
        long jM2874getSurfaceContainerLowest0d7_KjU = (i2 & 4) != 0 ? ColorLightTokens.INSTANCE.m2874getSurfaceContainerLowest0d7_KjU() : j35;
        if ((i2 & 8) != 0) {
            j36 = ColorLightTokens.INSTANCE.m2875getSurfaceDim0d7_KjU();
        }
        return m1652lightColorSchemeCXl9yA(j37, jM2843getOnPrimary0d7_KjU, jM2860getPrimaryContainer0d7_KjU, jM2844getOnPrimaryContainer0d7_KjU, jM2838getInversePrimary0d7_KjU, jM2864getSecondary0d7_KjU, jM2847getOnSecondary0d7_KjU, jM2865getSecondaryContainer0d7_KjU, jM2848getOnSecondaryContainer0d7_KjU, jM2878getTertiary0d7_KjU, jM2853getOnTertiary0d7_KjU, jM2879getTertiaryContainer0d7_KjU, jM2854getOnTertiaryContainer0d7_KjU, jM2834getBackground0d7_KjU, jM2840getOnBackground0d7_KjU, jM2868getSurface0d7_KjU, jM2851getOnSurface0d7_KjU, jM2877getSurfaceVariant0d7_KjU, jM2852getOnSurfaceVariant0d7_KjU, j38, jM2839getInverseSurface0d7_KjU, jM2837getInverseOnSurface0d7_KjU, jM2835getError0d7_KjU, jM2841getOnError0d7_KjU, jM2836getErrorContainer0d7_KjU, jM2842getOnErrorContainer0d7_KjU, jM2857getOutline0d7_KjU, jM2858getOutlineVariant0d7_KjU, jM2863getScrim0d7_KjU, jM2869getSurfaceBright0d7_KjU, jM2870getSurfaceContainer0d7_KjU, jM2871getSurfaceContainerHigh0d7_KjU, jM2872getSurfaceContainerHighest0d7_KjU, jM2873getSurfaceContainerLow0d7_KjU, jM2874getSurfaceContainerLowest0d7_KjU, j36);
    }

    /* JADX INFO: renamed from: lightColorScheme-C-Xl9yA, reason: not valid java name */
    public static final ColorScheme m1652lightColorSchemeCXl9yA(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36) {
        return new ColorScheme(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j36, j31, j32, j33, j34, j35, null);
    }

    /* JADX INFO: renamed from: lightColorScheme-G1PFc-w$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m1655lightColorSchemeG1PFcw$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, int i, Object obj) {
        long jM2859getPrimary0d7_KjU = (i & 1) != 0 ? ColorLightTokens.INSTANCE.m2859getPrimary0d7_KjU() : j;
        long jM2843getOnPrimary0d7_KjU = (i & 2) != 0 ? ColorLightTokens.INSTANCE.m2843getOnPrimary0d7_KjU() : j2;
        long jM2860getPrimaryContainer0d7_KjU = (i & 4) != 0 ? ColorLightTokens.INSTANCE.m2860getPrimaryContainer0d7_KjU() : j3;
        long jM2844getOnPrimaryContainer0d7_KjU = (i & 8) != 0 ? ColorLightTokens.INSTANCE.m2844getOnPrimaryContainer0d7_KjU() : j4;
        long jM2838getInversePrimary0d7_KjU = (i & 16) != 0 ? ColorLightTokens.INSTANCE.m2838getInversePrimary0d7_KjU() : j5;
        long jM2864getSecondary0d7_KjU = (i & 32) != 0 ? ColorLightTokens.INSTANCE.m2864getSecondary0d7_KjU() : j6;
        long jM2847getOnSecondary0d7_KjU = (i & 64) != 0 ? ColorLightTokens.INSTANCE.m2847getOnSecondary0d7_KjU() : j7;
        long jM2865getSecondaryContainer0d7_KjU = (i & 128) != 0 ? ColorLightTokens.INSTANCE.m2865getSecondaryContainer0d7_KjU() : j8;
        long j30 = jM2859getPrimary0d7_KjU;
        long jM2848getOnSecondaryContainer0d7_KjU = (i & 256) != 0 ? ColorLightTokens.INSTANCE.m2848getOnSecondaryContainer0d7_KjU() : j9;
        long jM2878getTertiary0d7_KjU = (i & 512) != 0 ? ColorLightTokens.INSTANCE.m2878getTertiary0d7_KjU() : j10;
        long jM2853getOnTertiary0d7_KjU = (i & 1024) != 0 ? ColorLightTokens.INSTANCE.m2853getOnTertiary0d7_KjU() : j11;
        long jM2879getTertiaryContainer0d7_KjU = (i & 2048) != 0 ? ColorLightTokens.INSTANCE.m2879getTertiaryContainer0d7_KjU() : j12;
        long jM2854getOnTertiaryContainer0d7_KjU = (i & 4096) != 0 ? ColorLightTokens.INSTANCE.m2854getOnTertiaryContainer0d7_KjU() : j13;
        long jM2834getBackground0d7_KjU = (i & 8192) != 0 ? ColorLightTokens.INSTANCE.m2834getBackground0d7_KjU() : j14;
        long jM2840getOnBackground0d7_KjU = (i & 16384) != 0 ? ColorLightTokens.INSTANCE.m2840getOnBackground0d7_KjU() : j15;
        long jM2868getSurface0d7_KjU = (i & 32768) != 0 ? ColorLightTokens.INSTANCE.m2868getSurface0d7_KjU() : j16;
        long jM2851getOnSurface0d7_KjU = (i & 65536) != 0 ? ColorLightTokens.INSTANCE.m2851getOnSurface0d7_KjU() : j17;
        long jM2877getSurfaceVariant0d7_KjU = (i & 131072) != 0 ? ColorLightTokens.INSTANCE.m2877getSurfaceVariant0d7_KjU() : j18;
        long jM2852getOnSurfaceVariant0d7_KjU = (i & 262144) != 0 ? ColorLightTokens.INSTANCE.m2852getOnSurfaceVariant0d7_KjU() : j19;
        long j31 = (i & 524288) != 0 ? j30 : j20;
        long jM2839getInverseSurface0d7_KjU = (i & 1048576) != 0 ? ColorLightTokens.INSTANCE.m2839getInverseSurface0d7_KjU() : j21;
        long jM2837getInverseOnSurface0d7_KjU = (i & 2097152) != 0 ? ColorLightTokens.INSTANCE.m2837getInverseOnSurface0d7_KjU() : j22;
        long jM2835getError0d7_KjU = (i & 4194304) != 0 ? ColorLightTokens.INSTANCE.m2835getError0d7_KjU() : j23;
        long jM2841getOnError0d7_KjU = (i & 8388608) != 0 ? ColorLightTokens.INSTANCE.m2841getOnError0d7_KjU() : j24;
        long jM2836getErrorContainer0d7_KjU = (i & 16777216) != 0 ? ColorLightTokens.INSTANCE.m2836getErrorContainer0d7_KjU() : j25;
        long jM2842getOnErrorContainer0d7_KjU = (i & 33554432) != 0 ? ColorLightTokens.INSTANCE.m2842getOnErrorContainer0d7_KjU() : j26;
        long jM2857getOutline0d7_KjU = (i & 67108864) != 0 ? ColorLightTokens.INSTANCE.m2857getOutline0d7_KjU() : j27;
        long jM2858getOutlineVariant0d7_KjU = (i & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? ColorLightTokens.INSTANCE.m2858getOutlineVariant0d7_KjU() : j28;
        if ((i & 268435456) != 0) {
            j29 = ColorLightTokens.INSTANCE.m2863getScrim0d7_KjU();
        }
        return m1654lightColorSchemeG1PFcw(j30, jM2843getOnPrimary0d7_KjU, jM2860getPrimaryContainer0d7_KjU, jM2844getOnPrimaryContainer0d7_KjU, jM2838getInversePrimary0d7_KjU, jM2864getSecondary0d7_KjU, jM2847getOnSecondary0d7_KjU, jM2865getSecondaryContainer0d7_KjU, jM2848getOnSecondaryContainer0d7_KjU, jM2878getTertiary0d7_KjU, jM2853getOnTertiary0d7_KjU, jM2879getTertiaryContainer0d7_KjU, jM2854getOnTertiaryContainer0d7_KjU, jM2834getBackground0d7_KjU, jM2840getOnBackground0d7_KjU, jM2868getSurface0d7_KjU, jM2851getOnSurface0d7_KjU, jM2877getSurfaceVariant0d7_KjU, jM2852getOnSurfaceVariant0d7_KjU, j31, jM2839getInverseSurface0d7_KjU, jM2837getInverseOnSurface0d7_KjU, jM2835getError0d7_KjU, jM2841getOnError0d7_KjU, jM2836getErrorContainer0d7_KjU, jM2842getOnErrorContainer0d7_KjU, jM2857getOutline0d7_KjU, jM2858getOutlineVariant0d7_KjU, j29);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with additional surface roles instead")
    /* JADX INFO: renamed from: lightColorScheme-G1PFc-w, reason: not valid java name */
    public static final /* synthetic */ ColorScheme m1654lightColorSchemeG1PFcw(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29) {
        return m1653lightColorSchemeCXl9yA$default(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -536870912, 15, null);
    }

    /* JADX INFO: renamed from: darkColorScheme-C-Xl9yA$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m1649darkColorSchemeCXl9yA$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, int i, int i2, Object obj) {
        long jM2811getPrimary0d7_KjU = (i & 1) != 0 ? ColorDarkTokens.INSTANCE.m2811getPrimary0d7_KjU() : j;
        long jM2795getOnPrimary0d7_KjU = (i & 2) != 0 ? ColorDarkTokens.INSTANCE.m2795getOnPrimary0d7_KjU() : j2;
        long jM2812getPrimaryContainer0d7_KjU = (i & 4) != 0 ? ColorDarkTokens.INSTANCE.m2812getPrimaryContainer0d7_KjU() : j3;
        long jM2796getOnPrimaryContainer0d7_KjU = (i & 8) != 0 ? ColorDarkTokens.INSTANCE.m2796getOnPrimaryContainer0d7_KjU() : j4;
        long jM2790getInversePrimary0d7_KjU = (i & 16) != 0 ? ColorDarkTokens.INSTANCE.m2790getInversePrimary0d7_KjU() : j5;
        long jM2816getSecondary0d7_KjU = (i & 32) != 0 ? ColorDarkTokens.INSTANCE.m2816getSecondary0d7_KjU() : j6;
        long jM2799getOnSecondary0d7_KjU = (i & 64) != 0 ? ColorDarkTokens.INSTANCE.m2799getOnSecondary0d7_KjU() : j7;
        long jM2817getSecondaryContainer0d7_KjU = (i & 128) != 0 ? ColorDarkTokens.INSTANCE.m2817getSecondaryContainer0d7_KjU() : j8;
        long j37 = jM2811getPrimary0d7_KjU;
        long jM2800getOnSecondaryContainer0d7_KjU = (i & 256) != 0 ? ColorDarkTokens.INSTANCE.m2800getOnSecondaryContainer0d7_KjU() : j9;
        long jM2830getTertiary0d7_KjU = (i & 512) != 0 ? ColorDarkTokens.INSTANCE.m2830getTertiary0d7_KjU() : j10;
        long jM2805getOnTertiary0d7_KjU = (i & 1024) != 0 ? ColorDarkTokens.INSTANCE.m2805getOnTertiary0d7_KjU() : j11;
        long jM2831getTertiaryContainer0d7_KjU = (i & 2048) != 0 ? ColorDarkTokens.INSTANCE.m2831getTertiaryContainer0d7_KjU() : j12;
        long jM2806getOnTertiaryContainer0d7_KjU = (i & 4096) != 0 ? ColorDarkTokens.INSTANCE.m2806getOnTertiaryContainer0d7_KjU() : j13;
        long jM2786getBackground0d7_KjU = (i & 8192) != 0 ? ColorDarkTokens.INSTANCE.m2786getBackground0d7_KjU() : j14;
        long jM2792getOnBackground0d7_KjU = (i & 16384) != 0 ? ColorDarkTokens.INSTANCE.m2792getOnBackground0d7_KjU() : j15;
        long jM2820getSurface0d7_KjU = (i & 32768) != 0 ? ColorDarkTokens.INSTANCE.m2820getSurface0d7_KjU() : j16;
        long jM2803getOnSurface0d7_KjU = (i & 65536) != 0 ? ColorDarkTokens.INSTANCE.m2803getOnSurface0d7_KjU() : j17;
        long jM2829getSurfaceVariant0d7_KjU = (i & 131072) != 0 ? ColorDarkTokens.INSTANCE.m2829getSurfaceVariant0d7_KjU() : j18;
        long jM2804getOnSurfaceVariant0d7_KjU = (i & 262144) != 0 ? ColorDarkTokens.INSTANCE.m2804getOnSurfaceVariant0d7_KjU() : j19;
        long j38 = (i & 524288) != 0 ? j37 : j20;
        long jM2791getInverseSurface0d7_KjU = (i & 1048576) != 0 ? ColorDarkTokens.INSTANCE.m2791getInverseSurface0d7_KjU() : j21;
        long jM2789getInverseOnSurface0d7_KjU = (i & 2097152) != 0 ? ColorDarkTokens.INSTANCE.m2789getInverseOnSurface0d7_KjU() : j22;
        long jM2787getError0d7_KjU = (i & 4194304) != 0 ? ColorDarkTokens.INSTANCE.m2787getError0d7_KjU() : j23;
        long jM2793getOnError0d7_KjU = (i & 8388608) != 0 ? ColorDarkTokens.INSTANCE.m2793getOnError0d7_KjU() : j24;
        long jM2788getErrorContainer0d7_KjU = (i & 16777216) != 0 ? ColorDarkTokens.INSTANCE.m2788getErrorContainer0d7_KjU() : j25;
        long jM2794getOnErrorContainer0d7_KjU = (i & 33554432) != 0 ? ColorDarkTokens.INSTANCE.m2794getOnErrorContainer0d7_KjU() : j26;
        long jM2809getOutline0d7_KjU = (i & 67108864) != 0 ? ColorDarkTokens.INSTANCE.m2809getOutline0d7_KjU() : j27;
        long jM2810getOutlineVariant0d7_KjU = (i & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? ColorDarkTokens.INSTANCE.m2810getOutlineVariant0d7_KjU() : j28;
        long jM2815getScrim0d7_KjU = (i & 268435456) != 0 ? ColorDarkTokens.INSTANCE.m2815getScrim0d7_KjU() : j29;
        long jM2821getSurfaceBright0d7_KjU = (i & 536870912) != 0 ? ColorDarkTokens.INSTANCE.m2821getSurfaceBright0d7_KjU() : j30;
        long jM2822getSurfaceContainer0d7_KjU = (i & 1073741824) != 0 ? ColorDarkTokens.INSTANCE.m2822getSurfaceContainer0d7_KjU() : j31;
        long jM2823getSurfaceContainerHigh0d7_KjU = (i & Integer.MIN_VALUE) != 0 ? ColorDarkTokens.INSTANCE.m2823getSurfaceContainerHigh0d7_KjU() : j32;
        long jM2824getSurfaceContainerHighest0d7_KjU = (i2 & 1) != 0 ? ColorDarkTokens.INSTANCE.m2824getSurfaceContainerHighest0d7_KjU() : j33;
        long jM2825getSurfaceContainerLow0d7_KjU = (i2 & 2) != 0 ? ColorDarkTokens.INSTANCE.m2825getSurfaceContainerLow0d7_KjU() : j34;
        long jM2826getSurfaceContainerLowest0d7_KjU = (i2 & 4) != 0 ? ColorDarkTokens.INSTANCE.m2826getSurfaceContainerLowest0d7_KjU() : j35;
        if ((i2 & 8) != 0) {
            j36 = ColorDarkTokens.INSTANCE.m2827getSurfaceDim0d7_KjU();
        }
        return m1648darkColorSchemeCXl9yA(j37, jM2795getOnPrimary0d7_KjU, jM2812getPrimaryContainer0d7_KjU, jM2796getOnPrimaryContainer0d7_KjU, jM2790getInversePrimary0d7_KjU, jM2816getSecondary0d7_KjU, jM2799getOnSecondary0d7_KjU, jM2817getSecondaryContainer0d7_KjU, jM2800getOnSecondaryContainer0d7_KjU, jM2830getTertiary0d7_KjU, jM2805getOnTertiary0d7_KjU, jM2831getTertiaryContainer0d7_KjU, jM2806getOnTertiaryContainer0d7_KjU, jM2786getBackground0d7_KjU, jM2792getOnBackground0d7_KjU, jM2820getSurface0d7_KjU, jM2803getOnSurface0d7_KjU, jM2829getSurfaceVariant0d7_KjU, jM2804getOnSurfaceVariant0d7_KjU, j38, jM2791getInverseSurface0d7_KjU, jM2789getInverseOnSurface0d7_KjU, jM2787getError0d7_KjU, jM2793getOnError0d7_KjU, jM2788getErrorContainer0d7_KjU, jM2794getOnErrorContainer0d7_KjU, jM2809getOutline0d7_KjU, jM2810getOutlineVariant0d7_KjU, jM2815getScrim0d7_KjU, jM2821getSurfaceBright0d7_KjU, jM2822getSurfaceContainer0d7_KjU, jM2823getSurfaceContainerHigh0d7_KjU, jM2824getSurfaceContainerHighest0d7_KjU, jM2825getSurfaceContainerLow0d7_KjU, jM2826getSurfaceContainerLowest0d7_KjU, j36);
    }

    /* JADX INFO: renamed from: darkColorScheme-C-Xl9yA, reason: not valid java name */
    public static final ColorScheme m1648darkColorSchemeCXl9yA(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36) {
        return new ColorScheme(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j36, j31, j32, j33, j34, j35, null);
    }

    /* JADX INFO: renamed from: darkColorScheme-G1PFc-w$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m1651darkColorSchemeG1PFcw$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, int i, Object obj) {
        long jM2811getPrimary0d7_KjU = (i & 1) != 0 ? ColorDarkTokens.INSTANCE.m2811getPrimary0d7_KjU() : j;
        long jM2795getOnPrimary0d7_KjU = (i & 2) != 0 ? ColorDarkTokens.INSTANCE.m2795getOnPrimary0d7_KjU() : j2;
        long jM2812getPrimaryContainer0d7_KjU = (i & 4) != 0 ? ColorDarkTokens.INSTANCE.m2812getPrimaryContainer0d7_KjU() : j3;
        long jM2796getOnPrimaryContainer0d7_KjU = (i & 8) != 0 ? ColorDarkTokens.INSTANCE.m2796getOnPrimaryContainer0d7_KjU() : j4;
        long jM2790getInversePrimary0d7_KjU = (i & 16) != 0 ? ColorDarkTokens.INSTANCE.m2790getInversePrimary0d7_KjU() : j5;
        long jM2816getSecondary0d7_KjU = (i & 32) != 0 ? ColorDarkTokens.INSTANCE.m2816getSecondary0d7_KjU() : j6;
        long jM2799getOnSecondary0d7_KjU = (i & 64) != 0 ? ColorDarkTokens.INSTANCE.m2799getOnSecondary0d7_KjU() : j7;
        long jM2817getSecondaryContainer0d7_KjU = (i & 128) != 0 ? ColorDarkTokens.INSTANCE.m2817getSecondaryContainer0d7_KjU() : j8;
        long j30 = jM2811getPrimary0d7_KjU;
        long jM2800getOnSecondaryContainer0d7_KjU = (i & 256) != 0 ? ColorDarkTokens.INSTANCE.m2800getOnSecondaryContainer0d7_KjU() : j9;
        long jM2830getTertiary0d7_KjU = (i & 512) != 0 ? ColorDarkTokens.INSTANCE.m2830getTertiary0d7_KjU() : j10;
        long jM2805getOnTertiary0d7_KjU = (i & 1024) != 0 ? ColorDarkTokens.INSTANCE.m2805getOnTertiary0d7_KjU() : j11;
        long jM2831getTertiaryContainer0d7_KjU = (i & 2048) != 0 ? ColorDarkTokens.INSTANCE.m2831getTertiaryContainer0d7_KjU() : j12;
        long jM2806getOnTertiaryContainer0d7_KjU = (i & 4096) != 0 ? ColorDarkTokens.INSTANCE.m2806getOnTertiaryContainer0d7_KjU() : j13;
        long jM2786getBackground0d7_KjU = (i & 8192) != 0 ? ColorDarkTokens.INSTANCE.m2786getBackground0d7_KjU() : j14;
        long jM2792getOnBackground0d7_KjU = (i & 16384) != 0 ? ColorDarkTokens.INSTANCE.m2792getOnBackground0d7_KjU() : j15;
        long jM2820getSurface0d7_KjU = (i & 32768) != 0 ? ColorDarkTokens.INSTANCE.m2820getSurface0d7_KjU() : j16;
        long jM2803getOnSurface0d7_KjU = (i & 65536) != 0 ? ColorDarkTokens.INSTANCE.m2803getOnSurface0d7_KjU() : j17;
        long jM2829getSurfaceVariant0d7_KjU = (i & 131072) != 0 ? ColorDarkTokens.INSTANCE.m2829getSurfaceVariant0d7_KjU() : j18;
        long jM2804getOnSurfaceVariant0d7_KjU = (i & 262144) != 0 ? ColorDarkTokens.INSTANCE.m2804getOnSurfaceVariant0d7_KjU() : j19;
        long j31 = (i & 524288) != 0 ? j30 : j20;
        long jM2791getInverseSurface0d7_KjU = (i & 1048576) != 0 ? ColorDarkTokens.INSTANCE.m2791getInverseSurface0d7_KjU() : j21;
        long jM2789getInverseOnSurface0d7_KjU = (i & 2097152) != 0 ? ColorDarkTokens.INSTANCE.m2789getInverseOnSurface0d7_KjU() : j22;
        long jM2787getError0d7_KjU = (i & 4194304) != 0 ? ColorDarkTokens.INSTANCE.m2787getError0d7_KjU() : j23;
        long jM2793getOnError0d7_KjU = (i & 8388608) != 0 ? ColorDarkTokens.INSTANCE.m2793getOnError0d7_KjU() : j24;
        long jM2788getErrorContainer0d7_KjU = (i & 16777216) != 0 ? ColorDarkTokens.INSTANCE.m2788getErrorContainer0d7_KjU() : j25;
        long jM2794getOnErrorContainer0d7_KjU = (i & 33554432) != 0 ? ColorDarkTokens.INSTANCE.m2794getOnErrorContainer0d7_KjU() : j26;
        long jM2809getOutline0d7_KjU = (i & 67108864) != 0 ? ColorDarkTokens.INSTANCE.m2809getOutline0d7_KjU() : j27;
        long jM2810getOutlineVariant0d7_KjU = (i & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? ColorDarkTokens.INSTANCE.m2810getOutlineVariant0d7_KjU() : j28;
        if ((i & 268435456) != 0) {
            j29 = ColorDarkTokens.INSTANCE.m2815getScrim0d7_KjU();
        }
        return m1650darkColorSchemeG1PFcw(j30, jM2795getOnPrimary0d7_KjU, jM2812getPrimaryContainer0d7_KjU, jM2796getOnPrimaryContainer0d7_KjU, jM2790getInversePrimary0d7_KjU, jM2816getSecondary0d7_KjU, jM2799getOnSecondary0d7_KjU, jM2817getSecondaryContainer0d7_KjU, jM2800getOnSecondaryContainer0d7_KjU, jM2830getTertiary0d7_KjU, jM2805getOnTertiary0d7_KjU, jM2831getTertiaryContainer0d7_KjU, jM2806getOnTertiaryContainer0d7_KjU, jM2786getBackground0d7_KjU, jM2792getOnBackground0d7_KjU, jM2820getSurface0d7_KjU, jM2803getOnSurface0d7_KjU, jM2829getSurfaceVariant0d7_KjU, jM2804getOnSurfaceVariant0d7_KjU, j31, jM2791getInverseSurface0d7_KjU, jM2789getInverseOnSurface0d7_KjU, jM2787getError0d7_KjU, jM2793getOnError0d7_KjU, jM2788getErrorContainer0d7_KjU, jM2794getOnErrorContainer0d7_KjU, jM2809getOutline0d7_KjU, jM2810getOutlineVariant0d7_KjU, j29);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with additional surface roles instead")
    /* JADX INFO: renamed from: darkColorScheme-G1PFc-w, reason: not valid java name */
    public static final /* synthetic */ ColorScheme m1650darkColorSchemeG1PFcw(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29) {
        return m1649darkColorSchemeCXl9yA$default(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -536870912, 15, null);
    }

    /* JADX INFO: renamed from: contentColorFor-4WTKRHQ, reason: not valid java name */
    public static final long m1646contentColorFor4WTKRHQ(ColorScheme colorScheme, long j) {
        if (Color.m4153equalsimpl0(j, colorScheme.getPrimary())) {
            return colorScheme.getOnPrimary();
        }
        if (Color.m4153equalsimpl0(j, colorScheme.getSecondary())) {
            return colorScheme.getOnSecondary();
        }
        if (Color.m4153equalsimpl0(j, colorScheme.getTertiary())) {
            return colorScheme.getOnTertiary();
        }
        if (Color.m4153equalsimpl0(j, colorScheme.getBackground())) {
            return colorScheme.getOnBackground();
        }
        if (Color.m4153equalsimpl0(j, colorScheme.getError())) {
            return colorScheme.getOnError();
        }
        if (Color.m4153equalsimpl0(j, colorScheme.getPrimaryContainer())) {
            return colorScheme.getOnPrimaryContainer();
        }
        if (Color.m4153equalsimpl0(j, colorScheme.getSecondaryContainer())) {
            return colorScheme.getOnSecondaryContainer();
        }
        if (Color.m4153equalsimpl0(j, colorScheme.getTertiaryContainer())) {
            return colorScheme.getOnTertiaryContainer();
        }
        if (Color.m4153equalsimpl0(j, colorScheme.getErrorContainer())) {
            return colorScheme.getOnErrorContainer();
        }
        if (Color.m4153equalsimpl0(j, colorScheme.getInverseSurface())) {
            return colorScheme.getInverseOnSurface();
        }
        if (Color.m4153equalsimpl0(j, colorScheme.getSurface())) {
            return colorScheme.getOnSurface();
        }
        if (Color.m4153equalsimpl0(j, colorScheme.getSurfaceVariant())) {
            return colorScheme.getOnSurfaceVariant();
        }
        if (!Color.m4153equalsimpl0(j, colorScheme.getSurfaceBright()) && !Color.m4153equalsimpl0(j, colorScheme.getSurfaceContainer()) && !Color.m4153equalsimpl0(j, colorScheme.getSurfaceContainerHigh()) && !Color.m4153equalsimpl0(j, colorScheme.getSurfaceContainerHighest()) && !Color.m4153equalsimpl0(j, colorScheme.getSurfaceContainerLow()) && !Color.m4153equalsimpl0(j, colorScheme.getSurfaceContainerLowest())) {
            return Color.INSTANCE.m4188getUnspecified0d7_KjU();
        }
        return colorScheme.getOnSurface();
    }

    /* JADX INFO: renamed from: contentColorFor-ek8zF_U, reason: not valid java name */
    public static final long m1647contentColorForek8zF_U(long j, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 509589638, "C(contentColorFor)P(0:c#ui.graphics.Color):ColorScheme.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(509589638, i, -1, "androidx.compose.material3.contentColorFor (ColorScheme.kt:878)");
        }
        composer.startReplaceGroup(-1680936624);
        ComposerKt.sourceInformation(composer, "*878@39525L11,879@39609L7");
        long jM1646contentColorFor4WTKRHQ = m1646contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColorScheme(composer, 6), j);
        if (jM1646contentColorFor4WTKRHQ == 16) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd(composer);
            jM1646contentColorFor4WTKRHQ = ((Color) objConsume).m4162unboximpl();
        }
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return jM1646contentColorFor4WTKRHQ;
    }

    /* JADX INFO: renamed from: applyTonalElevation-RFCenO8, reason: not valid java name */
    public static final long m1645applyTonalElevationRFCenO8(ColorScheme colorScheme, long j, float f, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -1610977682, "C(applyTonalElevation)P(0:c#ui.graphics.Color,1:c#ui.unit.Dp)896@40465L7:ColorScheme.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1610977682, i, -1, "androidx.compose.material3.applyTonalElevation (ColorScheme.kt:895)");
        }
        ProvidableCompositionLocal<Boolean> providableCompositionLocal = LocalTonalElevationEnabled;
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(providableCompositionLocal);
        ComposerKt.sourceInformationMarkerEnd(composer);
        boolean zBooleanValue = ((Boolean) objConsume).booleanValue();
        if (Color.m4153equalsimpl0(j, colorScheme.getSurface()) && zBooleanValue) {
            j = m1656surfaceColorAtElevation3ABfNKs(colorScheme, f);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return j;
    }

    public static final ColorScheme expressiveLightColorScheme() {
        return m1653lightColorSchemeCXl9yA$default(0L, 0L, 0L, PaletteTokens.INSTANCE.m3170getPrimary300d7_KjU(), 0L, 0L, 0L, 0L, PaletteTokens.INSTANCE.m3183getSecondary300d7_KjU(), 0L, 0L, 0L, PaletteTokens.INSTANCE.m3183getSecondary300d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -4361, 15, null);
    }

    public static final long fromToken(ColorScheme colorScheme, ColorSchemeKeyTokens colorSchemeKeyTokens) {
        switch (WhenMappings.$EnumSwitchMapping$0[colorSchemeKeyTokens.ordinal()]) {
            case 1:
                return colorScheme.getBackground();
            case 2:
                return colorScheme.getError();
            case 3:
                return colorScheme.getErrorContainer();
            case 4:
                return colorScheme.getInverseOnSurface();
            case 5:
                return colorScheme.getInversePrimary();
            case 6:
                return colorScheme.getInverseSurface();
            case 7:
                return colorScheme.getOnBackground();
            case 8:
                return colorScheme.getOnError();
            case 9:
                return colorScheme.getOnErrorContainer();
            case 10:
                return colorScheme.getOnPrimary();
            case 11:
                return colorScheme.getOnPrimaryContainer();
            case 12:
                return colorScheme.getOnSecondary();
            case 13:
                return colorScheme.getOnSecondaryContainer();
            case 14:
                return colorScheme.getOnSurface();
            case 15:
                return colorScheme.getOnSurfaceVariant();
            case 16:
                return colorScheme.getSurfaceTint();
            case 17:
                return colorScheme.getOnTertiary();
            case 18:
                return colorScheme.getOnTertiaryContainer();
            case 19:
                return colorScheme.getOutline();
            case 20:
                return colorScheme.getOutlineVariant();
            case 21:
                return colorScheme.getPrimary();
            case 22:
                return colorScheme.getPrimaryContainer();
            case 23:
                return colorScheme.getScrim();
            case 24:
                return colorScheme.getSecondary();
            case 25:
                return colorScheme.getSecondaryContainer();
            case 26:
                return colorScheme.getSurface();
            case 27:
                return colorScheme.getSurfaceVariant();
            case 28:
                return colorScheme.getSurfaceBright();
            case 29:
                return colorScheme.getSurfaceContainer();
            case 30:
                return colorScheme.getSurfaceContainerHigh();
            case 31:
                return colorScheme.getSurfaceContainerHighest();
            case 32:
                return colorScheme.getSurfaceContainerLow();
            case 33:
                return colorScheme.getSurfaceContainerLowest();
            case 34:
                return colorScheme.getSurfaceDim();
            case 35:
                return colorScheme.getTertiary();
            case 36:
                return colorScheme.getTertiaryContainer();
            default:
                return Color.INSTANCE.m4188getUnspecified0d7_KjU();
        }
    }

    public static final ProvidableCompositionLocal<ColorScheme> getLocalColorScheme() {
        return LocalColorScheme;
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalTonalElevationEnabled() {
        return LocalTonalElevationEnabled;
    }

    public static final long getValue(ColorSchemeKeyTokens colorSchemeKeyTokens, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -810780884, "C1009@45379L11:ColorScheme.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-810780884, i, -1, "androidx.compose.material3.<get-value> (ColorScheme.kt:1009)");
        }
        long jFromToken = fromToken(MaterialTheme.INSTANCE.getColorScheme(composer, 6), colorSchemeKeyTokens);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return jFromToken;
    }

    /* JADX INFO: renamed from: surfaceColorAtElevation-3ABfNKs, reason: not valid java name */
    public static final long m1656surfaceColorAtElevation3ABfNKs(ColorScheme colorScheme, float f) {
        if (Dp.m6895equalsimpl0(f, Dp.m6890constructorimpl(0.0f))) {
            return colorScheme.getSurface();
        }
        return ColorKt.m4197compositeOverOWjLjI(Color.m4151copywmQWz5c$default(colorScheme.getSurfaceTint(), ((((float) Math.log(f + 1.0f)) * 4.5f) + 2.0f) / 100.0f, 0.0f, 0.0f, 0.0f, 14, null), colorScheme.getSurface());
    }
}
