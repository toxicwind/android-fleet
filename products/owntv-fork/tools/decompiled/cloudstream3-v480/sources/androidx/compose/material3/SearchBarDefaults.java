package androidx.compose.material3;

import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsets_androidKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.FilledTextFieldTokens;
import androidx.compose.material3.tokens.SearchBarTokens;
import androidx.compose.material3.tokens.SearchViewTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.profileinstaller.ProfileVerifier;
import com.google.android.gms.actions.SearchIntents;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SearchBar.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0011\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JÈ\u0001\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001c0 2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001c0 2\u0006\u0010\"\u001a\u00020#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u001c0 2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020#2\u0015\b\u0002\u0010(\u001a\u000f\u0012\u0004\u0012\u00020\u001c\u0018\u00010)¢\u0006\u0002\b*2\u0015\b\u0002\u0010+\u001a\u000f\u0012\u0004\u0012\u00020\u001c\u0018\u00010)¢\u0006\u0002\b*2\u0015\b\u0002\u0010,\u001a\u000f\u0012\u0004\u0012\u00020\u001c\u0018\u00010)¢\u0006\u0002\b*2\b\b\u0002\u0010-\u001a\u00020.2\n\b\u0002\u0010/\u001a\u0004\u0018\u000100H\u0007¢\u0006\u0002\u00101J&\u0010-\u001a\u0002022\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u000204H\u0007ø\u0001\u0000¢\u0006\u0004\b6\u00107J0\u0010-\u001a\u0002022\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002042\b\b\u0002\u00108\u001a\u00020.H\u0007ø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u008a\u0001\u00108\u001a\u00020.2\b\b\u0002\u0010;\u001a\u0002042\b\b\u0002\u0010<\u001a\u0002042\b\b\u0002\u0010=\u001a\u0002042\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u0002042\b\b\u0002\u0010A\u001a\u0002042\b\b\u0002\u0010B\u001a\u0002042\b\b\u0002\u0010C\u001a\u0002042\b\b\u0002\u0010D\u001a\u0002042\b\b\u0002\u0010E\u001a\u0002042\b\b\u0002\u0010F\u001a\u0002042\b\b\u0002\u0010G\u001a\u000204H\u0007ø\u0001\u0000¢\u0006\u0004\bH\u0010IJ\u009e\u0001\u00108\u001a\u00020.2\b\b\u0002\u0010J\u001a\u0002042\b\b\u0002\u0010K\u001a\u0002042\b\b\u0002\u0010<\u001a\u0002042\b\b\u0002\u0010=\u001a\u0002042\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u0002042\b\b\u0002\u0010A\u001a\u0002042\b\b\u0002\u0010B\u001a\u0002042\b\b\u0002\u0010C\u001a\u0002042\b\b\u0002\u0010D\u001a\u0002042\b\b\u0002\u0010E\u001a\u0002042\b\b\u0002\u0010L\u001a\u0002042\b\b\u0002\u0010M\u001a\u0002042\b\b\u0002\u0010G\u001a\u000204H\u0007ø\u0001\u0000¢\u0006\u0004\bN\u0010OR$\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\t\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0019\u0010\u000b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0019\u0010\r\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0011\u0010\u000f\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\u0017\u001a\u00020\u00188G¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006P"}, d2 = {"Landroidx/compose/material3/SearchBarDefaults;", "", "()V", "Elevation", "Landroidx/compose/ui/unit/Dp;", "getElevation-D9Ej5fM$annotations", "getElevation-D9Ej5fM", "()F", "F", "InputFieldHeight", "getInputFieldHeight-D9Ej5fM", "ShadowElevation", "getShadowElevation-D9Ej5fM", "TonalElevation", "getTonalElevation-D9Ej5fM", "dockedShape", "Landroidx/compose/ui/graphics/Shape;", "getDockedShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "fullScreenShape", "getFullScreenShape", "inputFieldShape", "getInputFieldShape", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "InputField", "", SearchIntents.EXTRA_QUERY, "", "onQueryChange", "Lkotlin/Function1;", "onSearch", "expanded", "", "onExpandedChange", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "placeholder", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "leadingIcon", "trailingIcon", "colors", "Landroidx/compose/material3/TextFieldColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/material3/SearchBarColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "dividerColor", "colors-dgg9oW8", "(JJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SearchBarColors;", "inputFieldColors", "colors-Klgx-Pg", "(JJLandroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SearchBarColors;", "textColor", "disabledTextColor", "cursorColor", "selectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "placeholderColor", "disabledPlaceholderColor", "inputFieldColors--u-KgnY", "(JJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/TextFieldColors;", "focusedTextColor", "unfocusedTextColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "inputFieldColors-ITpI4ow", "(JJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/TextFieldColors;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SearchBarDefaults {
    public static final int $stable = 0;
    private static final float Elevation;
    public static final SearchBarDefaults INSTANCE = new SearchBarDefaults();
    private static final float InputFieldHeight;
    private static final float ShadowElevation;
    private static final float TonalElevation;

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to TonalElevation. Not to be confused with ShadowElevation.", replaceWith = @ReplaceWith(expression = "TonalElevation", imports = {}))
    /* JADX INFO: renamed from: getElevation-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m2132getElevationD9Ej5fM$annotations() {
    }

    private SearchBarDefaults() {
    }

    static {
        float fM2921getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m2921getLevel0D9Ej5fM();
        TonalElevation = fM2921getLevel0D9Ej5fM;
        ShadowElevation = ElevationTokens.INSTANCE.m2921getLevel0D9Ej5fM();
        Elevation = fM2921getLevel0D9Ej5fM;
        InputFieldHeight = SearchBarTokens.INSTANCE.m3222getContainerHeightD9Ej5fM();
    }

    /* JADX INFO: renamed from: getTonalElevation-D9Ej5fM, reason: not valid java name */
    public final float m2138getTonalElevationD9Ej5fM() {
        return TonalElevation;
    }

    /* JADX INFO: renamed from: getShadowElevation-D9Ej5fM, reason: not valid java name */
    public final float m2137getShadowElevationD9Ej5fM() {
        return ShadowElevation;
    }

    /* JADX INFO: renamed from: getElevation-D9Ej5fM, reason: not valid java name */
    public final float m2135getElevationD9Ej5fM() {
        return Elevation;
    }

    /* JADX INFO: renamed from: getInputFieldHeight-D9Ej5fM, reason: not valid java name */
    public final float m2136getInputFieldHeightD9Ej5fM() {
        return InputFieldHeight;
    }

    public final Shape getInputFieldShape(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -971556142, "C349@15770L5:SearchBar.android.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-971556142, i, -1, "androidx.compose.material3.SearchBarDefaults.<get-inputFieldShape> (SearchBar.android.kt:349)");
        }
        Shape value = ShapesKt.getValue(SearchBarTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return value;
    }

    public final Shape getFullScreenShape(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1665502056, "C353@15944L5:SearchBar.android.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1665502056, i, -1, "androidx.compose.material3.SearchBarDefaults.<get-fullScreenShape> (SearchBar.android.kt:353)");
        }
        Shape value = ShapesKt.getValue(SearchViewTokens.INSTANCE.getFullScreenContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return value;
    }

    public final Shape getDockedShape(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1006952150, "C357@16094L5:SearchBar.android.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1006952150, i, -1, "androidx.compose.material3.SearchBarDefaults.<get-dockedShape> (SearchBar.android.kt:357)");
        }
        Shape value = ShapesKt.getValue(SearchViewTokens.INSTANCE.getDockedContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return value;
    }

    public final WindowInsets getWindowInsets(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 2112270157, "C361@16229L10:SearchBar.android.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2112270157, i, -1, "androidx.compose.material3.SearchBarDefaults.<get-windowInsets> (SearchBar.android.kt:361)");
        }
        WindowInsets statusBars = WindowInsets_androidKt.getStatusBars(WindowInsets.INSTANCE, composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return statusBars;
    }

    /* JADX INFO: renamed from: colors-dgg9oW8, reason: not valid java name */
    public final SearchBarColors m2134colorsdgg9oW8(long j, long j2, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -1507037523, "C(colors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color)375@16778L5,376@16845L5,381@17019L18:SearchBar.android.kt#uh7d8r");
        long value = (i2 & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getContainerColor(), composer, 6) : j;
        long value2 = (i2 & 2) != 0 ? ColorSchemeKt.getValue(SearchViewTokens.INSTANCE.getDividerColor(), composer, 6) : j2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1507037523, i, -1, "androidx.compose.material3.SearchBarDefaults.colors (SearchBar.android.kt:378)");
        }
        SearchBarColors searchBarColors = new SearchBarColors(value, value2, m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 0, (i << 6) & 57344, 16383), null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return searchBarColors;
    }

    /* JADX INFO: renamed from: inputFieldColors-ITpI4ow, reason: not valid java name */
    public final TextFieldColors m2140inputFieldColorsITpI4ow(long j, long j2, long j3, long j4, TextSelectionColors textSelectionColors, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, Composer composer, int i, int i2, int i3) {
        TextSelectionColors textSelectionColors2;
        ComposerKt.sourceInformationMarkerStart(composer, -602148837, "C(inputFieldColors)P(7:c#ui.graphics.Color,12:c#ui.graphics.Color,3:c#ui.graphics.Color,0:c#ui.graphics.Color,9,5:c#ui.graphics.Color,10:c#ui.graphics.Color,1:c#ui.graphics.Color,8:c#ui.graphics.Color,13:c#ui.graphics.Color,4:c#ui.graphics.Color,6:c#ui.graphics.Color,11:c#ui.graphics.Color,2:c#ui.graphics.Color)410@18829L5,411@18903L5,413@18998L5,416@19154L5,417@19233L7,418@19316L5,419@19399L5,421@19507L5,424@19683L5,425@19768L5,427@19878L5,430@20056L5,431@20142L5,433@20244L5,437@20389L847:SearchBar.android.kt#uh7d8r");
        long value = (i3 & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), composer, 6) : j;
        long value2 = (i3 & 2) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), composer, 6) : j2;
        long jM4151copywmQWz5c$default = (i3 & 4) != 0 ? Color.m4151copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), 0.0f, 0.0f, 0.0f, 14, null) : j3;
        long value3 = (i3 & 8) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getCaretColor(), composer, 6) : j4;
        if ((i3 & 16) != 0) {
            ProvidableCompositionLocal<TextSelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd(composer);
            textSelectionColors2 = (TextSelectionColors) objConsume;
        } else {
            textSelectionColors2 = textSelectionColors;
        }
        long value4 = (i3 & 32) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), composer, 6) : j5;
        long value5 = (i3 & 64) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), composer, 6) : j6;
        long jM4151copywmQWz5c$default2 = (i3 & 128) != 0 ? Color.m4151copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), composer, 6), FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null) : j7;
        long value6 = (i3 & 256) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), composer, 6) : j8;
        long value7 = (i3 & 512) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), composer, 6) : j9;
        long jM4151copywmQWz5c$default3 = (i3 & 1024) != 0 ? Color.m4151copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), composer, 6), FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null) : j10;
        long value8 = (i3 & 2048) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), composer, 6) : j11;
        long value9 = (i3 & 4096) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), composer, 6) : j12;
        long jM4151copywmQWz5c$default4 = (i3 & 8192) != 0 ? Color.m4151copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), 0.0f, 0.0f, 0.0f, 14, null) : j13;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-602148837, i, i2, "androidx.compose.material3.SearchBarDefaults.inputFieldColors (SearchBar.android.kt:437)");
        }
        int i4 = i2 << 18;
        TextFieldColors textFieldColorsM2392colors0hiis_0 = TextFieldDefaults.INSTANCE.m2392colors0hiis_0(value, value2, jM4151copywmQWz5c$default, 0L, 0L, 0L, 0L, 0L, value3, 0L, textSelectionColors2, 0L, 0L, 0L, 0L, value4, value5, jM4151copywmQWz5c$default2, 0L, value6, value7, jM4151copywmQWz5c$default3, 0L, 0L, 0L, 0L, 0L, value8, value9, jM4151copywmQWz5c$default4, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, (i & AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED) | ((i << 15) & 234881024), ((i >> 12) & 14) | (458752 & i) | (3670016 & i) | (i & 29360128) | ((i << 3) & 1879048192), ((i >> 27) & 14) | ((i2 << 3) & 112) | (i4 & 29360128) | (i4 & 234881024) | (i4 & 1879048192), 0, 3072, 1204058872, 4095);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return textFieldColorsM2392colors0hiis_0;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0125  */
    /* JADX WARN: Code duplicated, block: B:102:0x0128  */
    /* JADX WARN: Code duplicated, block: B:107:0x0135  */
    /* JADX WARN: Code duplicated, block: B:109:0x0139  */
    /* JADX WARN: Code duplicated, block: B:112:0x0144 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:115:0x014b  */
    /* JADX WARN: Code duplicated, block: B:118:0x0155  */
    /* JADX WARN: Code duplicated, block: B:119:0x015e  */
    /* JADX WARN: Code duplicated, block: B:121:0x0162  */
    /* JADX WARN: Code duplicated, block: B:123:0x016c  */
    /* JADX WARN: Code duplicated, block: B:124:0x016f  */
    /* JADX WARN: Code duplicated, block: B:126:0x0174  */
    /* JADX WARN: Code duplicated, block: B:130:0x0180  */
    /* JADX WARN: Code duplicated, block: B:131:0x0183  */
    /* JADX WARN: Code duplicated, block: B:133:0x0187  */
    /* JADX WARN: Code duplicated, block: B:135:0x018f  */
    /* JADX WARN: Code duplicated, block: B:140:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:146:0x01be  */
    /* JADX WARN: Code duplicated, block: B:148:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:156:0x01f6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:157:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:158:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:160:0x0200  */
    /* JADX WARN: Code duplicated, block: B:161:0x0203  */
    /* JADX WARN: Code duplicated, block: B:163:0x0207  */
    /* JADX WARN: Code duplicated, block: B:164:0x020a  */
    /* JADX WARN: Code duplicated, block: B:166:0x020e  */
    /* JADX WARN: Code duplicated, block: B:167:0x0211  */
    /* JADX WARN: Code duplicated, block: B:169:0x0215  */
    /* JADX WARN: Code duplicated, block: B:170:0x0218  */
    /* JADX WARN: Code duplicated, block: B:173:0x021e  */
    /* JADX WARN: Code duplicated, block: B:174:0x0276  */
    /* JADX WARN: Code duplicated, block: B:176:0x0280  */
    /* JADX WARN: Code duplicated, block: B:177:0x0285  */
    /* JADX WARN: Code duplicated, block: B:180:0x0292  */
    /* JADX WARN: Code duplicated, block: B:183:0x02a9  */
    /* JADX WARN: Code duplicated, block: B:185:0x02bb  */
    /* JADX WARN: Code duplicated, block: B:187:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:190:0x02f2  */
    /* JADX WARN: Code duplicated, block: B:194:0x0355  */
    /* JADX WARN: Code duplicated, block: B:197:0x0392  */
    /* JADX WARN: Code duplicated, block: B:198:0x0394  */
    /* JADX WARN: Code duplicated, block: B:201:0x039b  */
    /* JADX WARN: Code duplicated, block: B:203:0x03a3  */
    /* JADX WARN: Code duplicated, block: B:206:0x03c7  */
    /* JADX WARN: Code duplicated, block: B:207:0x03c9  */
    /* JADX WARN: Code duplicated, block: B:210:0x03d6  */
    /* JADX WARN: Code duplicated, block: B:212:0x03de  */
    /* JADX WARN: Code duplicated, block: B:215:0x047a  */
    /* JADX WARN: Code duplicated, block: B:216:0x047c  */
    /* JADX WARN: Code duplicated, block: B:219:0x0482  */
    /* JADX WARN: Code duplicated, block: B:220:0x0484  */
    /* JADX WARN: Code duplicated, block: B:223:0x048c  */
    /* JADX WARN: Code duplicated, block: B:225:0x0494  */
    /* JADX WARN: Code duplicated, block: B:228:0x0524 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:230:0x0529  */
    /* JADX WARN: Code duplicated, block: B:233:0x0548  */
    /* JADX WARN: Code duplicated, block: B:235:0x0550  */
    /* JADX WARN: Code duplicated, block: B:238:0x056c  */
    /* JADX WARN: Code duplicated, block: B:242:0x0584  */
    /* JADX WARN: Code duplicated, block: B:244:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0055  */
    /* JADX WARN: Code duplicated, block: B:27:0x0058  */
    /* JADX WARN: Code duplicated, block: B:29:0x005c  */
    /* JADX WARN: Code duplicated, block: B:31:0x0062  */
    /* JADX WARN: Code duplicated, block: B:32:0x0065  */
    /* JADX WARN: Code duplicated, block: B:36:0x006c  */
    /* JADX WARN: Code duplicated, block: B:37:0x006f  */
    /* JADX WARN: Code duplicated, block: B:39:0x0073  */
    /* JADX WARN: Code duplicated, block: B:41:0x0079  */
    /* JADX WARN: Code duplicated, block: B:42:0x007c  */
    /* JADX WARN: Code duplicated, block: B:46:0x0083  */
    /* JADX WARN: Code duplicated, block: B:47:0x0086  */
    /* JADX WARN: Code duplicated, block: B:49:0x008a  */
    /* JADX WARN: Code duplicated, block: B:51:0x0090  */
    /* JADX WARN: Code duplicated, block: B:52:0x0093  */
    /* JADX WARN: Code duplicated, block: B:56:0x009c  */
    /* JADX WARN: Code duplicated, block: B:57:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:61:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:62:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:67:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:71:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:72:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:77:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:79:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:82:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:87:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:89:0x0101  */
    /* JADX WARN: Code duplicated, block: B:91:0x0107  */
    /* JADX WARN: Code duplicated, block: B:92:0x010a  */
    /* JADX WARN: Code duplicated, block: B:96:0x0114  */
    /* JADX WARN: Code duplicated, block: B:97:0x0117  */
    /* JADX WARN: Code duplicated, block: B:99:0x011b  */
    public final void InputField(final String str, final Function1<? super String, Unit> function1, final Function1<? super String, Unit> function2, final boolean z, final Function1<? super Boolean, Unit> function3, Modifier modifier, boolean z2, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, TextFieldColors textFieldColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        Modifier modifier2;
        int i9;
        int i10;
        boolean z3;
        int i11;
        int i12;
        Function2<? super Composer, ? super Integer, Unit> function7;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        MutableInteractionSource mutableInteractionSource2;
        int i21;
        int i22;
        int i23;
        Modifier.Companion companion;
        boolean z4;
        int i24;
        TextFieldColors textFieldColorsM2140inputFieldColorsITpI4ow;
        int i25;
        MutableInteractionSource mutableInteractionSource3;
        boolean zBooleanValue;
        Object objRememberedValue;
        final FocusRequester focusRequester;
        FocusManager focusManager;
        final String strM2723getString2EP1pXo;
        final String strM2723getString2EP1pXo2;
        long jM6374getColor0d7_KjU;
        boolean z5;
        Object objRememberedValue2;
        boolean z6;
        boolean zChanged;
        Object objRememberedValue3;
        boolean z7;
        int i26;
        boolean z8;
        boolean z9;
        Object objRememberedValue4;
        Composer composer2;
        boolean z10;
        boolean zChanged2;
        Object objRememberedValue5;
        Composer composer3;
        final MutableInteractionSource mutableInteractionSource4;
        final Modifier modifier3;
        final TextFieldColors textFieldColors2;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final boolean z11;
        Object objRememberedValue6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(1451366815);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(InputField)P(10,7,8,2,6,5,1,9,4,11)492@23517L18,498@23776L25,499@23837L29,500@23912L7,502@23951L34,503@24030L39,*506@24122L7,521@24685L44,522@24761L378,534@25242L7,537@25496L19,540@25625L1172,510@24250L2557,566@26894L320,566@26869L345:SearchBar.android.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i | 6;
        } else if ((i & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        if ((i3 & 2) == 0) {
            if ((i & 48) == 0) {
                i4 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
            }
            if ((i3 & 4) != 0) {
                i4 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            } else if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i4 |= i5;
            }
            if ((i3 & 8) != 0) {
                i4 |= 3072;
            } else if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changed(z)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i4 |= i6;
            }
            if ((i3 & 16) != 0) {
                i4 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i4 |= i7;
            }
            i8 = i3 & 32;
            if (i8 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                modifier2 = modifier;
            } else {
                modifier2 = modifier;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
            }
            i10 = i3 & 64;
            if (i10 != 0) {
                i4 |= 1572864;
                z3 = z2;
            } else {
                z3 = z2;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(z3)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i4 |= i11;
                }
            }
            i12 = i3 & 128;
            if (i12 != 0) {
                i4 |= 12582912;
                function7 = function4;
            } else {
                function7 = function4;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function7)) {
                        i13 = 8388608;
                    } else {
                        i13 = 4194304;
                    }
                    i4 |= i13;
                }
            }
            i14 = i3 & 256;
            if (i14 != 0) {
                i4 |= 100663296;
            } else if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i15 = 67108864;
                } else {
                    i15 = 33554432;
                }
                i4 |= i15;
            }
            i16 = i3 & 512;
            if (i16 != 0) {
                if ((i & BaseAdapter.HEADER) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i17 = 536870912;
                    } else {
                        i17 = 268435456;
                    }
                    i4 |= i17;
                }
                if ((i2 & 6) == 0) {
                    i18 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(textFieldColors)) ? 2 : 4);
                } else {
                    i18 = i2;
                }
                i19 = i4;
                i20 = i3 & 2048;
                if (i20 != 0) {
                    i20 = i20;
                    i21 = i18 | 48;
                    mutableInteractionSource2 = mutableInteractionSource;
                } else {
                    if ((i2 & 48) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i22 = 32;
                        } else {
                            i22 = 16;
                        }
                        i18 |= i22;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    i21 = i18;
                }
                if ((i3 & 4096) != 0) {
                    if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                        i21 |= composerStartRestartGroup.changed(this) ? 256 : 128;
                    }
                    if ((i19 & 306783379) == 306783378 || (i21 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
                        composerStartRestartGroup.startDefaults();
                        i23 = i21;
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i8 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i10 != 0) {
                                z4 = true;
                            } else {
                                z4 = z3;
                            }
                            if (i12 != 0) {
                                function7 = null;
                            } else {
                                function7 = function7;
                            }
                            if (i14 != 0) {
                                function5 = null;
                            } else {
                                function5 = function5;
                            }
                            if (i16 != 0) {
                                function6 = null;
                            } else {
                                function6 = function6;
                            }
                            if ((i3 & 1024) != 0) {
                                i24 = i20;
                                i19 = i19;
                                textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                                i25 = i23 & (-15);
                            } else {
                                i19 = i19;
                                i24 = i20;
                                textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                                i25 = i23;
                            }
                            if (i24 != 0) {
                                composerStartRestartGroup = composerStartRestartGroup;
                                z3 = z4;
                                mutableInteractionSource2 = null;
                            } else {
                                composerStartRestartGroup = composerStartRestartGroup;
                                mutableInteractionSource2 = mutableInteractionSource;
                                z3 = z4;
                            }
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i3 & 1024) != 0) {
                                Modifier modifier4 = modifier2;
                                i25 = i23 & (-15);
                                companion = modifier4;
                            } else {
                                companion = modifier2;
                                i25 = i23;
                            }
                            textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
                        }
                        composerStartRestartGroup.startReplaceGroup(-320443616);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
                        if (mutableInteractionSource2 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        focusRequester = (FocusRequester) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ProvidableCompositionLocal<FocusManager> localFocusManager = CompositionLocalsKt.getLocalFocusManager();
                        Modifier modifier5 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume = composerStartRestartGroup.consume(localFocusManager);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        focusManager = (FocusManager) objConsume;
                        Strings.Companion companion2 = Strings.INSTANCE;
                        strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
                        Strings.Companion companion3 = Strings.INSTANCE;
                        strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
                        ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                        final MutableInteractionSource mutableInteractionSource5 = mutableInteractionSource3;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = composerStartRestartGroup.consume(localTextStyle);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        jM6374getColor0d7_KjU = ((TextStyle) objConsume2).m6374getColor0d7_KjU();
                        if (jM6374getColor0d7_KjU == 16) {
                            jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
                        }
                        long j = jM6374getColor0d7_KjU;
                        Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier5, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
                        final boolean z12 = z3;
                        if ((57344 & i19) == 16384) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z5 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                    invoke2(focusState);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(FocusState focusState) {
                                    if (focusState.isFocused()) {
                                        function3.invoke(true);
                                    }
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifierOnFocusChanged = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester, (Function1) objRememberedValue2);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
                        boolean zChanged3 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
                        if ((i19 & 7168) == 2048) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        zChanged = z6 | zChanged3 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                                    SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                                    if (z) {
                                        SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                                    }
                                    final FocusRequester focusRequester2 = focusRequester;
                                    SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Boolean invoke() {
                                            focusRequester2.requestFocus();
                                            return true;
                                        }
                                    }, 1, null);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierOnFocusChanged, false, (Function1) objRememberedValue3, 1, null);
                        ProvidableCompositionLocal<TextStyle> localTextStyle2 = TextKt.getLocalTextStyle();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = composerStartRestartGroup.consume(localTextStyle2);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        TextStyle textStyleMerge = ((TextStyle) objConsume3).merge(new TextStyle(j, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                        SolidColor solidColor = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
                        KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
                        if ((i19 & 896) == 256) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        i26 = i19 & 14;
                        if (i26 == 4) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        z9 = z7 | z8;
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (!z9 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                    invoke2(keyboardActionScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyboardActionScope keyboardActionScope) {
                                    function2.invoke(str);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        final TextFieldColors textFieldColors3 = textFieldColorsM2140inputFieldColorsITpI4ow;
                        final Function2<? super Composer, ? super Integer, Unit> function11 = function7;
                        final Function2<? super Composer, ? super Integer, Unit> function12 = function5;
                        final Function2<? super Composer, ? super Integer, Unit> function13 = function6;
                        int i27 = i26 | 102236160 | (i19 & 112);
                        int i28 = i19 >> 9;
                        composer2 = composerStartRestartGroup;
                        BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default, z12, false, textStyleMerge, keyboardOptions, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource5, (Brush) solidColor, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function14, Composer composer4, Integer num) {
                                invoke((Function2<? super Composer, ? super Integer, Unit>) function14, composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Function2<? super Composer, ? super Integer, Unit> function14, Composer composer4, int i29) {
                                Function2<? super Composer, ? super Integer, Unit> function15;
                                int i30;
                                ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                                if ((i29 & 6) == 0) {
                                    function15 = function14;
                                    i30 = i29 | (composer4.changedInstance(function15) ? 4 : 2);
                                } else {
                                    function15 = function14;
                                    i30 = i29;
                                }
                                if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                                    }
                                    TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                                    String str2 = str;
                                    int i31 = i30;
                                    boolean z13 = z12;
                                    VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                                    MutableInteractionSource mutableInteractionSource6 = mutableInteractionSource5;
                                    Function2<Composer, Integer, Unit> function16 = function11;
                                    final Function2<Composer, Integer, Unit> function17 = function12;
                                    composer4.startReplaceGroup(-1102017390);
                                    ComposerKt.sourceInformation(composer4, "*551@26196L64");
                                    ComposableLambda composableLambdaRememberComposableLambda = function17 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                            invoke(composer5, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer5, int i32) {
                                            ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                            if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                                composer5.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                            }
                                            Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                            Function2<Composer, Integer, Unit> function18 = function17;
                                            ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                            ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                            CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                            ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                            if (!(composer5.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer5.startReusableNode();
                                            if (composer5.getInserting()) {
                                                composer5.createNode(constructor);
                                            } else {
                                                composer5.useNode();
                                            }
                                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                            }
                                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                            ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                            function18.invoke(composer5, 0);
                                            ComposerKt.sourceInformationMarkerEnd(composer5);
                                            ComposerKt.sourceInformationMarkerEnd(composer5);
                                            composer5.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(composer5);
                                            ComposerKt.sourceInformationMarkerEnd(composer5);
                                            ComposerKt.sourceInformationMarkerEnd(composer5);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    }, composer4, 54);
                                    composer4.endReplaceGroup();
                                    final Function2<Composer, Integer, Unit> function18 = function13;
                                    composer4.startReplaceGroup(-1102010155);
                                    ComposerKt.sourceInformation(composer4, "*555@26423L66");
                                    ComposableLambda composableLambdaRememberComposableLambda2 = function18 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                            invoke(composer5, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer5, int i32) {
                                            ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                            if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                                composer5.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                            }
                                            Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                            Function2<Composer, Integer, Unit> function19 = function18;
                                            ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                            ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                            CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                            ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                            if (!(composer5.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer5.startReusableNode();
                                            if (composer5.getInserting()) {
                                                composer5.createNode(constructor);
                                            } else {
                                                composer5.useNode();
                                            }
                                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                            }
                                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                            ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                            function19.invoke(composer5, 0);
                                            ComposerKt.sourceInformationMarkerEnd(composer5);
                                            ComposerKt.sourceInformationMarkerEnd(composer5);
                                            composer5.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(composer5);
                                            ComposerKt.sourceInformationMarkerEnd(composer5);
                                            ComposerKt.sourceInformationMarkerEnd(composer5);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    }, composer4, 54) : null;
                                    composer4.endReplaceGroup();
                                    textFieldDefaults.DecorationBox(str2, function15, z13, true, none, mutableInteractionSource6, false, null, function16, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors3, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer4.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composer2, i27 | (i28 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                        if (z && zBooleanValue) {
                            z10 = true;
                        } else {
                            z10 = false;
                        }
                        Boolean boolValueOf = Boolean.valueOf(z);
                        ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
                        zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
                        objRememberedValue5 = composer2.rememberedValue();
                        if (!zChanged2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                            composer2.updateRememberedValue(objRememberedValue5);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i28 & 14);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer3 = composer2;
                        mutableInteractionSource4 = mutableInteractionSource2;
                        modifier3 = modifier5;
                        textFieldColors2 = textFieldColors3;
                        function8 = function7;
                        function9 = function5;
                        function10 = function6;
                        z11 = z12;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        textFieldColors2 = textFieldColors;
                        composer3 = composerStartRestartGroup;
                        mutableInteractionSource4 = mutableInteractionSource2;
                        modifier3 = modifier2;
                        z11 = z3;
                        function8 = function7;
                        function9 = function5;
                        function10 = function6;
                    }
                    scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.6
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                invoke(composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i29) {
                                SearchBarDefaults.this.InputField(str, function1, function2, z, function3, modifier3, z11, function8, function9, function10, textFieldColors2, mutableInteractionSource4, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i21 |= RendererCapabilities.DECODER_SUPPORT_MASK;
                if ((i19 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    i23 = i21;
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i10 != 0) {
                            z4 = true;
                        } else {
                            z4 = z3;
                        }
                        if (i12 != 0) {
                            function7 = null;
                        } else {
                            function7 = function7;
                        }
                        if (i14 != 0) {
                            function5 = null;
                        } else {
                            function5 = function5;
                        }
                        if (i16 != 0) {
                            function6 = null;
                        } else {
                            function6 = function6;
                        }
                        if ((i3 & 1024) != 0) {
                            i24 = i20;
                            i19 = i19;
                            textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                            i25 = i23 & (-15);
                        } else {
                            i19 = i19;
                            i24 = i20;
                            textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                            i25 = i23;
                        }
                        if (i24 != 0) {
                            composerStartRestartGroup = composerStartRestartGroup;
                            z3 = z4;
                            mutableInteractionSource2 = null;
                        } else {
                            composerStartRestartGroup = composerStartRestartGroup;
                            mutableInteractionSource2 = mutableInteractionSource;
                            z3 = z4;
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i10 != 0) {
                            z4 = true;
                        } else {
                            z4 = z3;
                        }
                        if (i12 != 0) {
                            function7 = null;
                        } else {
                            function7 = function7;
                        }
                        if (i14 != 0) {
                            function5 = null;
                        } else {
                            function5 = function5;
                        }
                        if (i16 != 0) {
                            function6 = null;
                        } else {
                            function6 = function6;
                        }
                        if ((i3 & 1024) != 0) {
                            i24 = i20;
                            i19 = i19;
                            textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                            i25 = i23 & (-15);
                        } else {
                            i19 = i19;
                            i24 = i20;
                            textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                            i25 = i23;
                        }
                        if (i24 != 0) {
                            composerStartRestartGroup = composerStartRestartGroup;
                            z3 = z4;
                            mutableInteractionSource2 = null;
                        } else {
                            composerStartRestartGroup = composerStartRestartGroup;
                            mutableInteractionSource2 = mutableInteractionSource;
                            z3 = z4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
                    }
                    composerStartRestartGroup.startReplaceGroup(-320443616);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
                    if (mutableInteractionSource2 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new FocusRequester();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    focusRequester = (FocusRequester) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<FocusManager> localFocusManager2 = CompositionLocalsKt.getLocalFocusManager();
                    Modifier modifier6 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume4 = composerStartRestartGroup.consume(localFocusManager2);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    focusManager = (FocusManager) objConsume4;
                    Strings.Companion companion4 = Strings.INSTANCE;
                    strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
                    Strings.Companion companion5 = Strings.INSTANCE;
                    strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
                    ProvidableCompositionLocal<TextStyle> localTextStyle3 = TextKt.getLocalTextStyle();
                    final MutableInteractionSource mutableInteractionSource6 = mutableInteractionSource3;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume5 = composerStartRestartGroup.consume(localTextStyle3);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    jM6374getColor0d7_KjU = ((TextStyle) objConsume5).m6374getColor0d7_KjU();
                    if (jM6374getColor0d7_KjU == 16) {
                        jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
                    }
                    long j2 = jM6374getColor0d7_KjU;
                    Modifier modifierFocusRequester2 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier6, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
                    final boolean z13 = z3;
                    if ((57344 & i19) == 16384) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z5) {
                        objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                invoke2(focusState);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(FocusState focusState) {
                                if (focusState.isFocused()) {
                                    function3.invoke(true);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                invoke2(focusState);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(FocusState focusState) {
                                if (focusState.isFocused()) {
                                    function3.invoke(true);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierOnFocusChanged2 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester2, (Function1) objRememberedValue2);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
                    boolean zChanged4 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
                    if ((i19 & 7168) == 2048) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    zChanged = z6 | zChanged4 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                                if (z) {
                                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                                }
                                final FocusRequester focusRequester2 = focusRequester;
                                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        focusRequester2.requestFocus();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                                if (z) {
                                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                                }
                                final FocusRequester focusRequester2 = focusRequester;
                                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        focusRequester2.requestFocus();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierSemantics$default2 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged2, false, (Function1) objRememberedValue3, 1, null);
                    ProvidableCompositionLocal<TextStyle> localTextStyle4 = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume6 = composerStartRestartGroup.consume(localTextStyle4);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    TextStyle textStyleMerge2 = ((TextStyle) objConsume6).merge(new TextStyle(j2, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                    SolidColor solidColor2 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
                    KeyboardOptions keyboardOptions2 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
                    if ((i19 & 896) == 256) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    i26 = i19 & 14;
                    if (i26 == 4) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    z9 = z7 | z8;
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (!z9) {
                        objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope keyboardActionScope) {
                                function2.invoke(str);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope keyboardActionScope) {
                                function2.invoke(str);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    final TextFieldColors textFieldColors4 = textFieldColorsM2140inputFieldColorsITpI4ow;
                    final Function2<? super Composer, ? super Integer, Unit> function14 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function15 = function5;
                    final Function2<? super Composer, ? super Integer, Unit> function16 = function6;
                    int i29 = i26 | 102236160 | (i19 & 112);
                    int i210 = i19 >> 9;
                    composer2 = composerStartRestartGroup;
                    BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default2, z13, false, textStyleMerge2, keyboardOptions2, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource6, (Brush) solidColor2, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function17, Composer composer4, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function17, composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function17, Composer composer4, int i211) {
                            Function2<? super Composer, ? super Integer, Unit> function18;
                            int i30;
                            ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                            if ((i211 & 6) == 0) {
                                function18 = function17;
                                i30 = i211 | (composer4.changedInstance(function18) ? 4 : 2);
                            } else {
                                function18 = function17;
                                i30 = i211;
                            }
                            if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                                }
                                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                                String str2 = str;
                                int i31 = i30;
                                boolean z14 = z13;
                                VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                                MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource6;
                                Function2<Composer, Integer, Unit> function19 = function14;
                                final Function2<? super Composer, ? super Integer, Unit> function110 = function15;
                                composer4.startReplaceGroup(-1102017390);
                                ComposerKt.sourceInformation(composer4, "*551@26196L64");
                                ComposableLambda composableLambdaRememberComposableLambda = function110 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i32) {
                                        ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                        if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                            composer5.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                        }
                                        Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function111 = function110;
                                        ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer5.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer5.startReusableNode();
                                        if (composer5.getInserting()) {
                                            composer5.createNode(constructor);
                                        } else {
                                            composer5.useNode();
                                        }
                                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                        function111.invoke(composer5, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        composer5.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer4, 54);
                                composer4.endReplaceGroup();
                                final Function2<? super Composer, ? super Integer, Unit> function111 = function16;
                                composer4.startReplaceGroup(-1102010155);
                                ComposerKt.sourceInformation(composer4, "*555@26423L66");
                                ComposableLambda composableLambdaRememberComposableLambda2 = function111 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i32) {
                                        ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                        if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                            composer5.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                        }
                                        Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function112 = function111;
                                        ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer5.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer5.startReusableNode();
                                        if (composer5.getInserting()) {
                                            composer5.createNode(constructor);
                                        } else {
                                            composer5.useNode();
                                        }
                                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                        function112.invoke(composer5, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        composer5.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer4, 54) : null;
                                composer4.endReplaceGroup();
                                textFieldDefaults.DecorationBox(str2, function18, z14, true, none, mutableInteractionSource7, false, null, function19, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors4, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer4.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, i29 | (i210 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                    if (z) {
                        z10 = false;
                    } else {
                        z10 = false;
                    }
                    Boolean boolValueOf2 = Boolean.valueOf(z);
                    ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
                    zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
                    objRememberedValue5 = composer2.rememberedValue();
                    if (!zChanged2) {
                        objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                        composer2.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                        composer2.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    EffectsKt.LaunchedEffect(boolValueOf2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i210 & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    modifier3 = modifier6;
                    textFieldColors2 = textFieldColors4;
                    function8 = function7;
                    function9 = function5;
                    function10 = function6;
                    z11 = z13;
                } else {
                    composerStartRestartGroup.startDefaults();
                    i23 = i21;
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i10 != 0) {
                            z4 = true;
                        } else {
                            z4 = z3;
                        }
                        if (i12 != 0) {
                            function7 = null;
                        } else {
                            function7 = function7;
                        }
                        if (i14 != 0) {
                            function5 = null;
                        } else {
                            function5 = function5;
                        }
                        if (i16 != 0) {
                            function6 = null;
                        } else {
                            function6 = function6;
                        }
                        if ((i3 & 1024) != 0) {
                            i24 = i20;
                            i19 = i19;
                            textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                            i25 = i23 & (-15);
                        } else {
                            i19 = i19;
                            i24 = i20;
                            textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                            i25 = i23;
                        }
                        if (i24 != 0) {
                            composerStartRestartGroup = composerStartRestartGroup;
                            z3 = z4;
                            mutableInteractionSource2 = null;
                        } else {
                            composerStartRestartGroup = composerStartRestartGroup;
                            mutableInteractionSource2 = mutableInteractionSource;
                            z3 = z4;
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i10 != 0) {
                            z4 = true;
                        } else {
                            z4 = z3;
                        }
                        if (i12 != 0) {
                            function7 = null;
                        } else {
                            function7 = function7;
                        }
                        if (i14 != 0) {
                            function5 = null;
                        } else {
                            function5 = function5;
                        }
                        if (i16 != 0) {
                            function6 = null;
                        } else {
                            function6 = function6;
                        }
                        if ((i3 & 1024) != 0) {
                            i24 = i20;
                            i19 = i19;
                            textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                            i25 = i23 & (-15);
                        } else {
                            i19 = i19;
                            i24 = i20;
                            textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                            i25 = i23;
                        }
                        if (i24 != 0) {
                            composerStartRestartGroup = composerStartRestartGroup;
                            z3 = z4;
                            mutableInteractionSource2 = null;
                        } else {
                            composerStartRestartGroup = composerStartRestartGroup;
                            mutableInteractionSource2 = mutableInteractionSource;
                            z3 = z4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
                    }
                    composerStartRestartGroup.startReplaceGroup(-320443616);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
                    if (mutableInteractionSource2 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new FocusRequester();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    focusRequester = (FocusRequester) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<FocusManager> localFocusManager3 = CompositionLocalsKt.getLocalFocusManager();
                    Modifier modifier7 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume7 = composerStartRestartGroup.consume(localFocusManager3);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    focusManager = (FocusManager) objConsume7;
                    Strings.Companion companion6 = Strings.INSTANCE;
                    strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
                    Strings.Companion companion7 = Strings.INSTANCE;
                    strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
                    ProvidableCompositionLocal<TextStyle> localTextStyle5 = TextKt.getLocalTextStyle();
                    final MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume8 = composerStartRestartGroup.consume(localTextStyle5);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    jM6374getColor0d7_KjU = ((TextStyle) objConsume8).m6374getColor0d7_KjU();
                    if (jM6374getColor0d7_KjU == 16) {
                        jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
                    }
                    long j3 = jM6374getColor0d7_KjU;
                    Modifier modifierFocusRequester3 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier7, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
                    final boolean z14 = z3;
                    if ((57344 & i19) == 16384) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z5) {
                        objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                invoke2(focusState);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(FocusState focusState) {
                                if (focusState.isFocused()) {
                                    function3.invoke(true);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                invoke2(focusState);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(FocusState focusState) {
                                if (focusState.isFocused()) {
                                    function3.invoke(true);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierOnFocusChanged3 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester3, (Function1) objRememberedValue2);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
                    boolean zChanged5 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
                    if ((i19 & 7168) == 2048) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    zChanged = z6 | zChanged5 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                                if (z) {
                                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                                }
                                final FocusRequester focusRequester2 = focusRequester;
                                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        focusRequester2.requestFocus();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                                if (z) {
                                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                                }
                                final FocusRequester focusRequester2 = focusRequester;
                                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        focusRequester2.requestFocus();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierSemantics$default3 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged3, false, (Function1) objRememberedValue3, 1, null);
                    ProvidableCompositionLocal<TextStyle> localTextStyle6 = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume9 = composerStartRestartGroup.consume(localTextStyle6);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    TextStyle textStyleMerge3 = ((TextStyle) objConsume9).merge(new TextStyle(j3, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                    SolidColor solidColor3 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
                    KeyboardOptions keyboardOptions3 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
                    if ((i19 & 896) == 256) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    i26 = i19 & 14;
                    if (i26 == 4) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    z9 = z7 | z8;
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (!z9) {
                        objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope keyboardActionScope) {
                                function2.invoke(str);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope keyboardActionScope) {
                                function2.invoke(str);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    final TextFieldColors textFieldColors5 = textFieldColorsM2140inputFieldColorsITpI4ow;
                    final Function2<? super Composer, ? super Integer, Unit> function17 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function18 = function5;
                    final Function2<? super Composer, ? super Integer, Unit> function19 = function6;
                    int i211 = i26 | 102236160 | (i19 & 112);
                    int i212 = i19 >> 9;
                    composer2 = composerStartRestartGroup;
                    BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default3, z14, false, textStyleMerge3, keyboardOptions3, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, (Brush) solidColor3, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function110, Composer composer4, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function110, composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function110, Composer composer4, int i213) {
                            Function2<? super Composer, ? super Integer, Unit> function111;
                            int i30;
                            ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                            if ((i213 & 6) == 0) {
                                function111 = function110;
                                i30 = i213 | (composer4.changedInstance(function111) ? 4 : 2);
                            } else {
                                function111 = function110;
                                i30 = i213;
                            }
                            if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                                }
                                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                                String str2 = str;
                                int i31 = i30;
                                boolean z15 = z14;
                                VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                                MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource7;
                                Function2<Composer, Integer, Unit> function112 = function17;
                                final Function2<? super Composer, ? super Integer, Unit> function113 = function18;
                                composer4.startReplaceGroup(-1102017390);
                                ComposerKt.sourceInformation(composer4, "*551@26196L64");
                                ComposableLambda composableLambdaRememberComposableLambda = function113 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i32) {
                                        ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                        if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                            composer5.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                        }
                                        Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function114 = function113;
                                        ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer5.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer5.startReusableNode();
                                        if (composer5.getInserting()) {
                                            composer5.createNode(constructor);
                                        } else {
                                            composer5.useNode();
                                        }
                                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                        function114.invoke(composer5, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        composer5.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer4, 54);
                                composer4.endReplaceGroup();
                                final Function2<? super Composer, ? super Integer, Unit> function114 = function19;
                                composer4.startReplaceGroup(-1102010155);
                                ComposerKt.sourceInformation(composer4, "*555@26423L66");
                                ComposableLambda composableLambdaRememberComposableLambda2 = function114 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i32) {
                                        ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                        if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                            composer5.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                        }
                                        Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function115 = function114;
                                        ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer5.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer5.startReusableNode();
                                        if (composer5.getInserting()) {
                                            composer5.createNode(constructor);
                                        } else {
                                            composer5.useNode();
                                        }
                                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                        function115.invoke(composer5, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        composer5.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer4, 54) : null;
                                composer4.endReplaceGroup();
                                textFieldDefaults.DecorationBox(str2, function111, z15, true, none, mutableInteractionSource8, false, null, function112, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors5, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer4.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, i211 | (i212 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                    if (z) {
                        z10 = false;
                    } else {
                        z10 = false;
                    }
                    Boolean boolValueOf3 = Boolean.valueOf(z);
                    ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
                    zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
                    objRememberedValue5 = composer2.rememberedValue();
                    if (!zChanged2) {
                        objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                        composer2.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                        composer2.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    EffectsKt.LaunchedEffect(boolValueOf3, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i212 & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    modifier3 = modifier7;
                    textFieldColors2 = textFieldColors5;
                    function8 = function7;
                    function9 = function5;
                    function10 = function6;
                    z11 = z14;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i213) {
                            SearchBarDefaults.this.InputField(str, function1, function2, z, function3, modifier3, z11, function8, function9, function10, textFieldColors2, mutableInteractionSource4, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= BaseAdapter.HEADER;
            if ((i2 & 6) == 0) {
                i18 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(textFieldColors)) ? 2 : 4);
            } else {
                i18 = i2;
            }
            i19 = i4;
            i20 = i3 & 2048;
            if (i20 != 0) {
                i20 = i20;
                i21 = i18 | 48;
                mutableInteractionSource2 = mutableInteractionSource;
            } else {
                if ((i2 & 48) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i22 = 32;
                    } else {
                        i22 = 16;
                    }
                    i18 |= i22;
                } else {
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                i21 = i18;
            }
            if ((i3 & 4096) != 0) {
                if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    i21 |= composerStartRestartGroup.changed(this) ? 256 : 128;
                }
                if ((i19 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    i23 = i21;
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i10 != 0) {
                            z4 = true;
                        } else {
                            z4 = z3;
                        }
                        if (i12 != 0) {
                            function7 = null;
                        } else {
                            function7 = function7;
                        }
                        if (i14 != 0) {
                            function5 = null;
                        } else {
                            function5 = function5;
                        }
                        if (i16 != 0) {
                            function6 = null;
                        } else {
                            function6 = function6;
                        }
                        if ((i3 & 1024) != 0) {
                            i24 = i20;
                            i19 = i19;
                            textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                            i25 = i23 & (-15);
                        } else {
                            i19 = i19;
                            i24 = i20;
                            textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                            i25 = i23;
                        }
                        if (i24 != 0) {
                            composerStartRestartGroup = composerStartRestartGroup;
                            z3 = z4;
                            mutableInteractionSource2 = null;
                        } else {
                            composerStartRestartGroup = composerStartRestartGroup;
                            mutableInteractionSource2 = mutableInteractionSource;
                            z3 = z4;
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i10 != 0) {
                            z4 = true;
                        } else {
                            z4 = z3;
                        }
                        if (i12 != 0) {
                            function7 = null;
                        } else {
                            function7 = function7;
                        }
                        if (i14 != 0) {
                            function5 = null;
                        } else {
                            function5 = function5;
                        }
                        if (i16 != 0) {
                            function6 = null;
                        } else {
                            function6 = function6;
                        }
                        if ((i3 & 1024) != 0) {
                            i24 = i20;
                            i19 = i19;
                            textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                            i25 = i23 & (-15);
                        } else {
                            i19 = i19;
                            i24 = i20;
                            textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                            i25 = i23;
                        }
                        if (i24 != 0) {
                            composerStartRestartGroup = composerStartRestartGroup;
                            z3 = z4;
                            mutableInteractionSource2 = null;
                        } else {
                            composerStartRestartGroup = composerStartRestartGroup;
                            mutableInteractionSource2 = mutableInteractionSource;
                            z3 = z4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
                    }
                    composerStartRestartGroup.startReplaceGroup(-320443616);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
                    if (mutableInteractionSource2 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new FocusRequester();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    focusRequester = (FocusRequester) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<FocusManager> localFocusManager4 = CompositionLocalsKt.getLocalFocusManager();
                    Modifier modifier8 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume10 = composerStartRestartGroup.consume(localFocusManager4);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    focusManager = (FocusManager) objConsume10;
                    Strings.Companion companion8 = Strings.INSTANCE;
                    strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
                    Strings.Companion companion9 = Strings.INSTANCE;
                    strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
                    ProvidableCompositionLocal<TextStyle> localTextStyle7 = TextKt.getLocalTextStyle();
                    final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume11 = composerStartRestartGroup.consume(localTextStyle7);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    jM6374getColor0d7_KjU = ((TextStyle) objConsume11).m6374getColor0d7_KjU();
                    if (jM6374getColor0d7_KjU == 16) {
                        jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
                    }
                    long j4 = jM6374getColor0d7_KjU;
                    Modifier modifierFocusRequester4 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier8, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
                    final boolean z15 = z3;
                    if ((57344 & i19) == 16384) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z5) {
                        objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                invoke2(focusState);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(FocusState focusState) {
                                if (focusState.isFocused()) {
                                    function3.invoke(true);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                invoke2(focusState);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(FocusState focusState) {
                                if (focusState.isFocused()) {
                                    function3.invoke(true);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierOnFocusChanged4 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester4, (Function1) objRememberedValue2);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
                    boolean zChanged6 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
                    if ((i19 & 7168) == 2048) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    zChanged = z6 | zChanged6 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                                if (z) {
                                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                                }
                                final FocusRequester focusRequester2 = focusRequester;
                                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        focusRequester2.requestFocus();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                                if (z) {
                                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                                }
                                final FocusRequester focusRequester2 = focusRequester;
                                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        focusRequester2.requestFocus();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierSemantics$default4 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged4, false, (Function1) objRememberedValue3, 1, null);
                    ProvidableCompositionLocal<TextStyle> localTextStyle8 = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume12 = composerStartRestartGroup.consume(localTextStyle8);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    TextStyle textStyleMerge4 = ((TextStyle) objConsume12).merge(new TextStyle(j4, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                    SolidColor solidColor4 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
                    KeyboardOptions keyboardOptions4 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
                    if ((i19 & 896) == 256) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    i26 = i19 & 14;
                    if (i26 == 4) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    z9 = z7 | z8;
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (!z9) {
                        objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope keyboardActionScope) {
                                function2.invoke(str);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope keyboardActionScope) {
                                function2.invoke(str);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    final TextFieldColors textFieldColors6 = textFieldColorsM2140inputFieldColorsITpI4ow;
                    final Function2<? super Composer, ? super Integer, Unit> function110 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function111 = function5;
                    final Function2<? super Composer, ? super Integer, Unit> function112 = function6;
                    int i213 = i26 | 102236160 | (i19 & 112);
                    int i214 = i19 >> 9;
                    composer2 = composerStartRestartGroup;
                    BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default4, z15, false, textStyleMerge4, keyboardOptions4, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource8, (Brush) solidColor4, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function113, Composer composer4, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function113, composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function113, Composer composer4, int i215) {
                            Function2<? super Composer, ? super Integer, Unit> function114;
                            int i30;
                            ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                            if ((i215 & 6) == 0) {
                                function114 = function113;
                                i30 = i215 | (composer4.changedInstance(function114) ? 4 : 2);
                            } else {
                                function114 = function113;
                                i30 = i215;
                            }
                            if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                                }
                                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                                String str2 = str;
                                int i31 = i30;
                                boolean z16 = z15;
                                VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                                MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                Function2<Composer, Integer, Unit> function115 = function110;
                                final Function2<? super Composer, ? super Integer, Unit> function116 = function111;
                                composer4.startReplaceGroup(-1102017390);
                                ComposerKt.sourceInformation(composer4, "*551@26196L64");
                                ComposableLambda composableLambdaRememberComposableLambda = function116 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i32) {
                                        ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                        if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                            composer5.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                        }
                                        Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function117 = function116;
                                        ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer5.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer5.startReusableNode();
                                        if (composer5.getInserting()) {
                                            composer5.createNode(constructor);
                                        } else {
                                            composer5.useNode();
                                        }
                                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                        function117.invoke(composer5, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        composer5.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer4, 54);
                                composer4.endReplaceGroup();
                                final Function2<? super Composer, ? super Integer, Unit> function117 = function112;
                                composer4.startReplaceGroup(-1102010155);
                                ComposerKt.sourceInformation(composer4, "*555@26423L66");
                                ComposableLambda composableLambdaRememberComposableLambda2 = function117 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i32) {
                                        ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                        if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                            composer5.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                        }
                                        Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function118 = function117;
                                        ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer5.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer5.startReusableNode();
                                        if (composer5.getInserting()) {
                                            composer5.createNode(constructor);
                                        } else {
                                            composer5.useNode();
                                        }
                                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                        function118.invoke(composer5, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        composer5.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer4, 54) : null;
                                composer4.endReplaceGroup();
                                textFieldDefaults.DecorationBox(str2, function114, z16, true, none, mutableInteractionSource9, false, null, function115, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors6, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer4.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, i213 | (i214 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                    if (z) {
                        z10 = false;
                    } else {
                        z10 = false;
                    }
                    Boolean boolValueOf4 = Boolean.valueOf(z);
                    ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
                    zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
                    objRememberedValue5 = composer2.rememberedValue();
                    if (!zChanged2) {
                        objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                        composer2.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                        composer2.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    EffectsKt.LaunchedEffect(boolValueOf4, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i214 & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    modifier3 = modifier8;
                    textFieldColors2 = textFieldColors6;
                    function8 = function7;
                    function9 = function5;
                    function10 = function6;
                    z11 = z15;
                } else {
                    composerStartRestartGroup.startDefaults();
                    i23 = i21;
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i10 != 0) {
                            z4 = true;
                        } else {
                            z4 = z3;
                        }
                        if (i12 != 0) {
                            function7 = null;
                        } else {
                            function7 = function7;
                        }
                        if (i14 != 0) {
                            function5 = null;
                        } else {
                            function5 = function5;
                        }
                        if (i16 != 0) {
                            function6 = null;
                        } else {
                            function6 = function6;
                        }
                        if ((i3 & 1024) != 0) {
                            i24 = i20;
                            i19 = i19;
                            textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                            i25 = i23 & (-15);
                        } else {
                            i19 = i19;
                            i24 = i20;
                            textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                            i25 = i23;
                        }
                        if (i24 != 0) {
                            composerStartRestartGroup = composerStartRestartGroup;
                            z3 = z4;
                            mutableInteractionSource2 = null;
                        } else {
                            composerStartRestartGroup = composerStartRestartGroup;
                            mutableInteractionSource2 = mutableInteractionSource;
                            z3 = z4;
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i10 != 0) {
                            z4 = true;
                        } else {
                            z4 = z3;
                        }
                        if (i12 != 0) {
                            function7 = null;
                        } else {
                            function7 = function7;
                        }
                        if (i14 != 0) {
                            function5 = null;
                        } else {
                            function5 = function5;
                        }
                        if (i16 != 0) {
                            function6 = null;
                        } else {
                            function6 = function6;
                        }
                        if ((i3 & 1024) != 0) {
                            i24 = i20;
                            i19 = i19;
                            textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                            i25 = i23 & (-15);
                        } else {
                            i19 = i19;
                            i24 = i20;
                            textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                            i25 = i23;
                        }
                        if (i24 != 0) {
                            composerStartRestartGroup = composerStartRestartGroup;
                            z3 = z4;
                            mutableInteractionSource2 = null;
                        } else {
                            composerStartRestartGroup = composerStartRestartGroup;
                            mutableInteractionSource2 = mutableInteractionSource;
                            z3 = z4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
                    }
                    composerStartRestartGroup.startReplaceGroup(-320443616);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
                    if (mutableInteractionSource2 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new FocusRequester();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    focusRequester = (FocusRequester) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<FocusManager> localFocusManager5 = CompositionLocalsKt.getLocalFocusManager();
                    Modifier modifier9 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume13 = composerStartRestartGroup.consume(localFocusManager5);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    focusManager = (FocusManager) objConsume13;
                    Strings.Companion companion10 = Strings.INSTANCE;
                    strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
                    Strings.Companion companion11 = Strings.INSTANCE;
                    strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
                    ProvidableCompositionLocal<TextStyle> localTextStyle9 = TextKt.getLocalTextStyle();
                    final MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource3;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume14 = composerStartRestartGroup.consume(localTextStyle9);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    jM6374getColor0d7_KjU = ((TextStyle) objConsume14).m6374getColor0d7_KjU();
                    if (jM6374getColor0d7_KjU == 16) {
                        jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
                    }
                    long j5 = jM6374getColor0d7_KjU;
                    Modifier modifierFocusRequester5 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier9, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
                    final boolean z16 = z3;
                    if ((57344 & i19) == 16384) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z5) {
                        objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                invoke2(focusState);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(FocusState focusState) {
                                if (focusState.isFocused()) {
                                    function3.invoke(true);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                invoke2(focusState);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(FocusState focusState) {
                                if (focusState.isFocused()) {
                                    function3.invoke(true);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierOnFocusChanged5 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester5, (Function1) objRememberedValue2);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
                    boolean zChanged7 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
                    if ((i19 & 7168) == 2048) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    zChanged = z6 | zChanged7 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                                if (z) {
                                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                                }
                                final FocusRequester focusRequester2 = focusRequester;
                                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        focusRequester2.requestFocus();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                                if (z) {
                                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                                }
                                final FocusRequester focusRequester2 = focusRequester;
                                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        focusRequester2.requestFocus();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierSemantics$default5 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged5, false, (Function1) objRememberedValue3, 1, null);
                    ProvidableCompositionLocal<TextStyle> localTextStyle10 = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume15 = composerStartRestartGroup.consume(localTextStyle10);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    TextStyle textStyleMerge5 = ((TextStyle) objConsume15).merge(new TextStyle(j5, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                    SolidColor solidColor5 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
                    KeyboardOptions keyboardOptions5 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
                    if ((i19 & 896) == 256) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    i26 = i19 & 14;
                    if (i26 == 4) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    z9 = z7 | z8;
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (!z9) {
                        objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope keyboardActionScope) {
                                function2.invoke(str);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope keyboardActionScope) {
                                function2.invoke(str);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    final TextFieldColors textFieldColors7 = textFieldColorsM2140inputFieldColorsITpI4ow;
                    final Function2<? super Composer, ? super Integer, Unit> function113 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function114 = function5;
                    final Function2<? super Composer, ? super Integer, Unit> function115 = function6;
                    int i215 = i26 | 102236160 | (i19 & 112);
                    int i216 = i19 >> 9;
                    composer2 = composerStartRestartGroup;
                    BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default5, z16, false, textStyleMerge5, keyboardOptions5, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource9, (Brush) solidColor5, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function116, Composer composer4, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function116, composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function116, Composer composer4, int i217) {
                            Function2<? super Composer, ? super Integer, Unit> function117;
                            int i30;
                            ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                            if ((i217 & 6) == 0) {
                                function117 = function116;
                                i30 = i217 | (composer4.changedInstance(function117) ? 4 : 2);
                            } else {
                                function117 = function116;
                                i30 = i217;
                            }
                            if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                                }
                                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                                String str2 = str;
                                int i31 = i30;
                                boolean z17 = z16;
                                VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                                MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource9;
                                Function2<Composer, Integer, Unit> function118 = function113;
                                final Function2<? super Composer, ? super Integer, Unit> function119 = function114;
                                composer4.startReplaceGroup(-1102017390);
                                ComposerKt.sourceInformation(composer4, "*551@26196L64");
                                ComposableLambda composableLambdaRememberComposableLambda = function119 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i32) {
                                        ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                        if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                            composer5.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                        }
                                        Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function1110 = function119;
                                        ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer5.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer5.startReusableNode();
                                        if (composer5.getInserting()) {
                                            composer5.createNode(constructor);
                                        } else {
                                            composer5.useNode();
                                        }
                                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                        function1110.invoke(composer5, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        composer5.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer4, 54);
                                composer4.endReplaceGroup();
                                final Function2<? super Composer, ? super Integer, Unit> function1110 = function115;
                                composer4.startReplaceGroup(-1102010155);
                                ComposerKt.sourceInformation(composer4, "*555@26423L66");
                                ComposableLambda composableLambdaRememberComposableLambda2 = function1110 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i32) {
                                        ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                        if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                            composer5.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                        }
                                        Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function1111 = function1110;
                                        ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer5.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer5.startReusableNode();
                                        if (composer5.getInserting()) {
                                            composer5.createNode(constructor);
                                        } else {
                                            composer5.useNode();
                                        }
                                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                        function1111.invoke(composer5, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        composer5.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer4, 54) : null;
                                composer4.endReplaceGroup();
                                textFieldDefaults.DecorationBox(str2, function117, z17, true, none, mutableInteractionSource10, false, null, function118, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors7, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer4.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, i215 | (i216 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                    if (z) {
                        z10 = false;
                    } else {
                        z10 = false;
                    }
                    Boolean boolValueOf5 = Boolean.valueOf(z);
                    ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
                    zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
                    objRememberedValue5 = composer2.rememberedValue();
                    if (!zChanged2) {
                        objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                        composer2.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                        composer2.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    EffectsKt.LaunchedEffect(boolValueOf5, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i216 & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    modifier3 = modifier9;
                    textFieldColors2 = textFieldColors7;
                    function8 = function7;
                    function9 = function5;
                    function10 = function6;
                    z11 = z16;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i217) {
                            SearchBarDefaults.this.InputField(str, function1, function2, z, function3, modifier3, z11, function8, function9, function10, textFieldColors2, mutableInteractionSource4, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i21 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            if ((i19 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                i23 = i21;
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i10 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i12 != 0) {
                        function7 = null;
                    } else {
                        function7 = function7;
                    }
                    if (i14 != 0) {
                        function5 = null;
                    } else {
                        function5 = function5;
                    }
                    if (i16 != 0) {
                        function6 = null;
                    } else {
                        function6 = function6;
                    }
                    if ((i3 & 1024) != 0) {
                        i24 = i20;
                        i19 = i19;
                        textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                        i25 = i23 & (-15);
                    } else {
                        i19 = i19;
                        i24 = i20;
                        textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                        i25 = i23;
                    }
                    if (i24 != 0) {
                        composerStartRestartGroup = composerStartRestartGroup;
                        z3 = z4;
                        mutableInteractionSource2 = null;
                    } else {
                        composerStartRestartGroup = composerStartRestartGroup;
                        mutableInteractionSource2 = mutableInteractionSource;
                        z3 = z4;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i10 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i12 != 0) {
                        function7 = null;
                    } else {
                        function7 = function7;
                    }
                    if (i14 != 0) {
                        function5 = null;
                    } else {
                        function5 = function5;
                    }
                    if (i16 != 0) {
                        function6 = null;
                    } else {
                        function6 = function6;
                    }
                    if ((i3 & 1024) != 0) {
                        i24 = i20;
                        i19 = i19;
                        textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                        i25 = i23 & (-15);
                    } else {
                        i19 = i19;
                        i24 = i20;
                        textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                        i25 = i23;
                    }
                    if (i24 != 0) {
                        composerStartRestartGroup = composerStartRestartGroup;
                        z3 = z4;
                        mutableInteractionSource2 = null;
                    } else {
                        composerStartRestartGroup = composerStartRestartGroup;
                        mutableInteractionSource2 = mutableInteractionSource;
                        z3 = z4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
                }
                composerStartRestartGroup.startReplaceGroup(-320443616);
                ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
                if (mutableInteractionSource2 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                composerStartRestartGroup.endReplaceGroup();
                zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new FocusRequester();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                focusRequester = (FocusRequester) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<FocusManager> localFocusManager6 = CompositionLocalsKt.getLocalFocusManager();
                Modifier modifier10 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume16 = composerStartRestartGroup.consume(localFocusManager6);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                focusManager = (FocusManager) objConsume16;
                Strings.Companion companion12 = Strings.INSTANCE;
                strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
                Strings.Companion companion13 = Strings.INSTANCE;
                strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
                ProvidableCompositionLocal<TextStyle> localTextStyle11 = TextKt.getLocalTextStyle();
                final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource3;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume17 = composerStartRestartGroup.consume(localTextStyle11);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                jM6374getColor0d7_KjU = ((TextStyle) objConsume17).m6374getColor0d7_KjU();
                if (jM6374getColor0d7_KjU == 16) {
                    jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
                }
                long j6 = jM6374getColor0d7_KjU;
                Modifier modifierFocusRequester6 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier10, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
                final boolean z17 = z3;
                if ((57344 & i19) == 16384) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z5) {
                    objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState focusState) {
                            if (focusState.isFocused()) {
                                function3.invoke(true);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState focusState) {
                            if (focusState.isFocused()) {
                                function3.invoke(true);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierOnFocusChanged6 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester6, (Function1) objRememberedValue2);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
                boolean zChanged8 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
                if ((i19 & 7168) == 2048) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                zChanged = z6 | zChanged8 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                            if (z) {
                                SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                            }
                            final FocusRequester focusRequester2 = focusRequester;
                            SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    focusRequester2.requestFocus();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                            if (z) {
                                SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                            }
                            final FocusRequester focusRequester2 = focusRequester;
                            SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    focusRequester2.requestFocus();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierSemantics$default6 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged6, false, (Function1) objRememberedValue3, 1, null);
                ProvidableCompositionLocal<TextStyle> localTextStyle12 = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume18 = composerStartRestartGroup.consume(localTextStyle12);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                TextStyle textStyleMerge6 = ((TextStyle) objConsume18).merge(new TextStyle(j6, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                SolidColor solidColor6 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
                KeyboardOptions keyboardOptions6 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
                if ((i19 & 896) == 256) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                i26 = i19 & 14;
                if (i26 == 4) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                z9 = z7 | z8;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!z9) {
                    objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                            invoke2(keyboardActionScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyboardActionScope keyboardActionScope) {
                            function2.invoke(str);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                            invoke2(keyboardActionScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyboardActionScope keyboardActionScope) {
                            function2.invoke(str);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final TextFieldColors textFieldColors8 = textFieldColorsM2140inputFieldColorsITpI4ow;
                final Function2<? super Composer, ? super Integer, Unit> function116 = function7;
                final Function2<? super Composer, ? super Integer, Unit> function117 = function5;
                final Function2<? super Composer, ? super Integer, Unit> function118 = function6;
                int i217 = i26 | 102236160 | (i19 & 112);
                int i218 = i19 >> 9;
                composer2 = composerStartRestartGroup;
                BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default6, z17, false, textStyleMerge6, keyboardOptions6, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource10, (Brush) solidColor6, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function119, Composer composer4, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function119, composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function119, Composer composer4, int i219) {
                        Function2<? super Composer, ? super Integer, Unit> function1110;
                        int i30;
                        ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                        if ((i219 & 6) == 0) {
                            function1110 = function119;
                            i30 = i219 | (composer4.changedInstance(function1110) ? 4 : 2);
                        } else {
                            function1110 = function119;
                            i30 = i219;
                        }
                        if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            String str2 = str;
                            int i31 = i30;
                            boolean z18 = z17;
                            VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                            MutableInteractionSource mutableInteractionSource11 = mutableInteractionSource10;
                            Function2<Composer, Integer, Unit> function1111 = function116;
                            final Function2<? super Composer, ? super Integer, Unit> function1112 = function117;
                            composer4.startReplaceGroup(-1102017390);
                            ComposerKt.sourceInformation(composer4, "*551@26196L64");
                            ComposableLambda composableLambdaRememberComposableLambda = function1112 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                    invoke(composer5, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer5, int i32) {
                                    ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                    if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                        composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                    }
                                    Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function1113 = function1112;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer5.startReusableNode();
                                    if (composer5.getInserting()) {
                                        composer5.createNode(constructor);
                                    } else {
                                        composer5.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                    function1113.invoke(composer5, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer4, 54);
                            composer4.endReplaceGroup();
                            final Function2<? super Composer, ? super Integer, Unit> function1113 = function118;
                            composer4.startReplaceGroup(-1102010155);
                            ComposerKt.sourceInformation(composer4, "*555@26423L66");
                            ComposableLambda composableLambdaRememberComposableLambda2 = function1113 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                    invoke(composer5, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer5, int i32) {
                                    ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                    if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                        composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                    }
                                    Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function1114 = function1113;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer5.startReusableNode();
                                    if (composer5.getInserting()) {
                                        composer5.createNode(constructor);
                                    } else {
                                        composer5.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                    function1114.invoke(composer5, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer4, 54) : null;
                            composer4.endReplaceGroup();
                            textFieldDefaults.DecorationBox(str2, function1110, z18, true, none, mutableInteractionSource11, false, null, function1111, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors8, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer4.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, i217 | (i218 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                if (z) {
                    z10 = false;
                } else {
                    z10 = false;
                }
                Boolean boolValueOf6 = Boolean.valueOf(z);
                ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
                zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
                objRememberedValue5 = composer2.rememberedValue();
                if (!zChanged2) {
                    objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                    composer2.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                    composer2.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                EffectsKt.LaunchedEffect(boolValueOf6, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i218 & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                mutableInteractionSource4 = mutableInteractionSource2;
                modifier3 = modifier10;
                textFieldColors2 = textFieldColors8;
                function8 = function7;
                function9 = function5;
                function10 = function6;
                z11 = z17;
            } else {
                composerStartRestartGroup.startDefaults();
                i23 = i21;
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i10 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i12 != 0) {
                        function7 = null;
                    } else {
                        function7 = function7;
                    }
                    if (i14 != 0) {
                        function5 = null;
                    } else {
                        function5 = function5;
                    }
                    if (i16 != 0) {
                        function6 = null;
                    } else {
                        function6 = function6;
                    }
                    if ((i3 & 1024) != 0) {
                        i24 = i20;
                        i19 = i19;
                        textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                        i25 = i23 & (-15);
                    } else {
                        i19 = i19;
                        i24 = i20;
                        textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                        i25 = i23;
                    }
                    if (i24 != 0) {
                        composerStartRestartGroup = composerStartRestartGroup;
                        z3 = z4;
                        mutableInteractionSource2 = null;
                    } else {
                        composerStartRestartGroup = composerStartRestartGroup;
                        mutableInteractionSource2 = mutableInteractionSource;
                        z3 = z4;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i10 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i12 != 0) {
                        function7 = null;
                    } else {
                        function7 = function7;
                    }
                    if (i14 != 0) {
                        function5 = null;
                    } else {
                        function5 = function5;
                    }
                    if (i16 != 0) {
                        function6 = null;
                    } else {
                        function6 = function6;
                    }
                    if ((i3 & 1024) != 0) {
                        i24 = i20;
                        i19 = i19;
                        textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                        i25 = i23 & (-15);
                    } else {
                        i19 = i19;
                        i24 = i20;
                        textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                        i25 = i23;
                    }
                    if (i24 != 0) {
                        composerStartRestartGroup = composerStartRestartGroup;
                        z3 = z4;
                        mutableInteractionSource2 = null;
                    } else {
                        composerStartRestartGroup = composerStartRestartGroup;
                        mutableInteractionSource2 = mutableInteractionSource;
                        z3 = z4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
                }
                composerStartRestartGroup.startReplaceGroup(-320443616);
                ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
                if (mutableInteractionSource2 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                composerStartRestartGroup.endReplaceGroup();
                zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new FocusRequester();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                focusRequester = (FocusRequester) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<FocusManager> localFocusManager7 = CompositionLocalsKt.getLocalFocusManager();
                Modifier modifier11 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume19 = composerStartRestartGroup.consume(localFocusManager7);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                focusManager = (FocusManager) objConsume19;
                Strings.Companion companion14 = Strings.INSTANCE;
                strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
                Strings.Companion companion15 = Strings.INSTANCE;
                strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
                ProvidableCompositionLocal<TextStyle> localTextStyle13 = TextKt.getLocalTextStyle();
                final MutableInteractionSource mutableInteractionSource11 = mutableInteractionSource3;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume110 = composerStartRestartGroup.consume(localTextStyle13);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                jM6374getColor0d7_KjU = ((TextStyle) objConsume110).m6374getColor0d7_KjU();
                if (jM6374getColor0d7_KjU == 16) {
                    jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
                }
                long j7 = jM6374getColor0d7_KjU;
                Modifier modifierFocusRequester7 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier11, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
                final boolean z18 = z3;
                if ((57344 & i19) == 16384) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z5) {
                    objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState focusState) {
                            if (focusState.isFocused()) {
                                function3.invoke(true);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState focusState) {
                            if (focusState.isFocused()) {
                                function3.invoke(true);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierOnFocusChanged7 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester7, (Function1) objRememberedValue2);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
                boolean zChanged9 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
                if ((i19 & 7168) == 2048) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                zChanged = z6 | zChanged9 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                            if (z) {
                                SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                            }
                            final FocusRequester focusRequester2 = focusRequester;
                            SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    focusRequester2.requestFocus();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                            if (z) {
                                SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                            }
                            final FocusRequester focusRequester2 = focusRequester;
                            SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    focusRequester2.requestFocus();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierSemantics$default7 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged7, false, (Function1) objRememberedValue3, 1, null);
                ProvidableCompositionLocal<TextStyle> localTextStyle14 = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume111 = composerStartRestartGroup.consume(localTextStyle14);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                TextStyle textStyleMerge7 = ((TextStyle) objConsume111).merge(new TextStyle(j7, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                SolidColor solidColor7 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
                KeyboardOptions keyboardOptions7 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
                if ((i19 & 896) == 256) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                i26 = i19 & 14;
                if (i26 == 4) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                z9 = z7 | z8;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!z9) {
                    objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                            invoke2(keyboardActionScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyboardActionScope keyboardActionScope) {
                            function2.invoke(str);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                            invoke2(keyboardActionScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyboardActionScope keyboardActionScope) {
                            function2.invoke(str);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final TextFieldColors textFieldColors9 = textFieldColorsM2140inputFieldColorsITpI4ow;
                final Function2<? super Composer, ? super Integer, Unit> function119 = function7;
                final Function2<? super Composer, ? super Integer, Unit> function1110 = function5;
                final Function2<? super Composer, ? super Integer, Unit> function1111 = function6;
                int i219 = i26 | 102236160 | (i19 & 112);
                int i2110 = i19 >> 9;
                composer2 = composerStartRestartGroup;
                BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default7, z18, false, textStyleMerge7, keyboardOptions7, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource11, (Brush) solidColor7, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function1112, Composer composer4, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function1112, composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function1112, Composer composer4, int i2111) {
                        Function2<? super Composer, ? super Integer, Unit> function1113;
                        int i30;
                        ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                        if ((i2111 & 6) == 0) {
                            function1113 = function1112;
                            i30 = i2111 | (composer4.changedInstance(function1113) ? 4 : 2);
                        } else {
                            function1113 = function1112;
                            i30 = i2111;
                        }
                        if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            String str2 = str;
                            int i31 = i30;
                            boolean z19 = z18;
                            VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                            MutableInteractionSource mutableInteractionSource12 = mutableInteractionSource11;
                            Function2<Composer, Integer, Unit> function1114 = function119;
                            final Function2<? super Composer, ? super Integer, Unit> function1115 = function1110;
                            composer4.startReplaceGroup(-1102017390);
                            ComposerKt.sourceInformation(composer4, "*551@26196L64");
                            ComposableLambda composableLambdaRememberComposableLambda = function1115 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                    invoke(composer5, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer5, int i32) {
                                    ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                    if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                        composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                    }
                                    Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function1116 = function1115;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer5.startReusableNode();
                                    if (composer5.getInserting()) {
                                        composer5.createNode(constructor);
                                    } else {
                                        composer5.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                    function1116.invoke(composer5, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer4, 54);
                            composer4.endReplaceGroup();
                            final Function2<? super Composer, ? super Integer, Unit> function1116 = function1111;
                            composer4.startReplaceGroup(-1102010155);
                            ComposerKt.sourceInformation(composer4, "*555@26423L66");
                            ComposableLambda composableLambdaRememberComposableLambda2 = function1116 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                    invoke(composer5, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer5, int i32) {
                                    ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                    if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                        composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                    }
                                    Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function1117 = function1116;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer5.startReusableNode();
                                    if (composer5.getInserting()) {
                                        composer5.createNode(constructor);
                                    } else {
                                        composer5.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                    function1117.invoke(composer5, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer4, 54) : null;
                            composer4.endReplaceGroup();
                            textFieldDefaults.DecorationBox(str2, function1113, z19, true, none, mutableInteractionSource12, false, null, function1114, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors9, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer4.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, i219 | (i2110 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                if (z) {
                    z10 = false;
                } else {
                    z10 = false;
                }
                Boolean boolValueOf7 = Boolean.valueOf(z);
                ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
                zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
                objRememberedValue5 = composer2.rememberedValue();
                if (!zChanged2) {
                    objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                    composer2.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                    composer2.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                EffectsKt.LaunchedEffect(boolValueOf7, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i2110 & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                mutableInteractionSource4 = mutableInteractionSource2;
                modifier3 = modifier11;
                textFieldColors2 = textFieldColors9;
                function8 = function7;
                function9 = function5;
                function10 = function6;
                z11 = z18;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.6
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i2111) {
                        SearchBarDefaults.this.InputField(str, function1, function2, z, function3, modifier3, z11, function8, function9, function10, textFieldColors2, mutableInteractionSource4, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                    }
                });
            }
        }
        i4 |= 48;
        if ((i3 & 4) != 0) {
            i4 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if (composerStartRestartGroup.changedInstance(function2)) {
                i5 = 256;
            } else {
                i5 = 128;
            }
            i4 |= i5;
        }
        if ((i3 & 8) != 0) {
            i4 |= 3072;
        } else if ((i & 3072) == 0) {
            if (composerStartRestartGroup.changed(z)) {
                i6 = 2048;
            } else {
                i6 = 1024;
            }
            i4 |= i6;
        }
        if ((i3 & 16) != 0) {
            i4 |= 24576;
        } else if ((i & 24576) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i7 = 16384;
            } else {
                i7 = 8192;
            }
            i4 |= i7;
        }
        i8 = i3 & 32;
        if (i8 != 0) {
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
        } else {
            modifier2 = modifier;
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(modifier2)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
        }
        i10 = i3 & 64;
        if (i10 != 0) {
            i4 |= 1572864;
            z3 = z2;
        } else {
            z3 = z2;
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(z3)) {
                    i11 = 1048576;
                } else {
                    i11 = 524288;
                }
                i4 |= i11;
            }
        }
        i12 = i3 & 128;
        if (i12 != 0) {
            i4 |= 12582912;
            function7 = function4;
        } else {
            function7 = function4;
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function7)) {
                    i13 = 8388608;
                } else {
                    i13 = 4194304;
                }
                i4 |= i13;
            }
        }
        i14 = i3 & 256;
        if (i14 != 0) {
            i4 |= 100663296;
        } else if ((i & 100663296) == 0) {
            if (composerStartRestartGroup.changedInstance(function5)) {
                i15 = 67108864;
            } else {
                i15 = 33554432;
            }
            i4 |= i15;
        }
        i16 = i3 & 512;
        if (i16 != 0) {
            if ((i & BaseAdapter.HEADER) == 0) {
                if (composerStartRestartGroup.changedInstance(function6)) {
                    i17 = 536870912;
                } else {
                    i17 = 268435456;
                }
                i4 |= i17;
            }
            if ((i2 & 6) == 0) {
                i18 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(textFieldColors)) ? 2 : 4);
            } else {
                i18 = i2;
            }
            i19 = i4;
            i20 = i3 & 2048;
            if (i20 != 0) {
                i20 = i20;
                i21 = i18 | 48;
                mutableInteractionSource2 = mutableInteractionSource;
            } else {
                if ((i2 & 48) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i22 = 32;
                    } else {
                        i22 = 16;
                    }
                    i18 |= i22;
                } else {
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                i21 = i18;
            }
            if ((i3 & 4096) != 0) {
                if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    i21 |= composerStartRestartGroup.changed(this) ? 256 : 128;
                }
                if ((i19 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    i23 = i21;
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i10 != 0) {
                            z4 = true;
                        } else {
                            z4 = z3;
                        }
                        if (i12 != 0) {
                            function7 = null;
                        } else {
                            function7 = function7;
                        }
                        if (i14 != 0) {
                            function5 = null;
                        } else {
                            function5 = function5;
                        }
                        if (i16 != 0) {
                            function6 = null;
                        } else {
                            function6 = function6;
                        }
                        if ((i3 & 1024) != 0) {
                            i24 = i20;
                            i19 = i19;
                            textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                            i25 = i23 & (-15);
                        } else {
                            i19 = i19;
                            i24 = i20;
                            textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                            i25 = i23;
                        }
                        if (i24 != 0) {
                            composerStartRestartGroup = composerStartRestartGroup;
                            z3 = z4;
                            mutableInteractionSource2 = null;
                        } else {
                            composerStartRestartGroup = composerStartRestartGroup;
                            mutableInteractionSource2 = mutableInteractionSource;
                            z3 = z4;
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i10 != 0) {
                            z4 = true;
                        } else {
                            z4 = z3;
                        }
                        if (i12 != 0) {
                            function7 = null;
                        } else {
                            function7 = function7;
                        }
                        if (i14 != 0) {
                            function5 = null;
                        } else {
                            function5 = function5;
                        }
                        if (i16 != 0) {
                            function6 = null;
                        } else {
                            function6 = function6;
                        }
                        if ((i3 & 1024) != 0) {
                            i24 = i20;
                            i19 = i19;
                            textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                            i25 = i23 & (-15);
                        } else {
                            i19 = i19;
                            i24 = i20;
                            textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                            i25 = i23;
                        }
                        if (i24 != 0) {
                            composerStartRestartGroup = composerStartRestartGroup;
                            z3 = z4;
                            mutableInteractionSource2 = null;
                        } else {
                            composerStartRestartGroup = composerStartRestartGroup;
                            mutableInteractionSource2 = mutableInteractionSource;
                            z3 = z4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
                    }
                    composerStartRestartGroup.startReplaceGroup(-320443616);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
                    if (mutableInteractionSource2 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new FocusRequester();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    focusRequester = (FocusRequester) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<FocusManager> localFocusManager8 = CompositionLocalsKt.getLocalFocusManager();
                    Modifier modifier12 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume112 = composerStartRestartGroup.consume(localFocusManager8);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    focusManager = (FocusManager) objConsume112;
                    Strings.Companion companion16 = Strings.INSTANCE;
                    strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
                    Strings.Companion companion17 = Strings.INSTANCE;
                    strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
                    ProvidableCompositionLocal<TextStyle> localTextStyle15 = TextKt.getLocalTextStyle();
                    final MutableInteractionSource mutableInteractionSource12 = mutableInteractionSource3;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume113 = composerStartRestartGroup.consume(localTextStyle15);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    jM6374getColor0d7_KjU = ((TextStyle) objConsume113).m6374getColor0d7_KjU();
                    if (jM6374getColor0d7_KjU == 16) {
                        jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
                    }
                    long j8 = jM6374getColor0d7_KjU;
                    Modifier modifierFocusRequester8 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier12, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
                    final boolean z19 = z3;
                    if ((57344 & i19) == 16384) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z5) {
                        objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                invoke2(focusState);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(FocusState focusState) {
                                if (focusState.isFocused()) {
                                    function3.invoke(true);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                invoke2(focusState);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(FocusState focusState) {
                                if (focusState.isFocused()) {
                                    function3.invoke(true);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierOnFocusChanged8 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester8, (Function1) objRememberedValue2);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
                    boolean zChanged10 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
                    if ((i19 & 7168) == 2048) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    zChanged = z6 | zChanged10 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                                if (z) {
                                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                                }
                                final FocusRequester focusRequester2 = focusRequester;
                                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        focusRequester2.requestFocus();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                                if (z) {
                                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                                }
                                final FocusRequester focusRequester2 = focusRequester;
                                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        focusRequester2.requestFocus();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierSemantics$default8 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged8, false, (Function1) objRememberedValue3, 1, null);
                    ProvidableCompositionLocal<TextStyle> localTextStyle16 = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume114 = composerStartRestartGroup.consume(localTextStyle16);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    TextStyle textStyleMerge8 = ((TextStyle) objConsume114).merge(new TextStyle(j8, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                    SolidColor solidColor8 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
                    KeyboardOptions keyboardOptions8 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
                    if ((i19 & 896) == 256) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    i26 = i19 & 14;
                    if (i26 == 4) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    z9 = z7 | z8;
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (!z9) {
                        objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope keyboardActionScope) {
                                function2.invoke(str);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope keyboardActionScope) {
                                function2.invoke(str);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    final TextFieldColors textFieldColors10 = textFieldColorsM2140inputFieldColorsITpI4ow;
                    final Function2<? super Composer, ? super Integer, Unit> function1112 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function1113 = function5;
                    final Function2<? super Composer, ? super Integer, Unit> function1114 = function6;
                    int i2111 = i26 | 102236160 | (i19 & 112);
                    int i2112 = i19 >> 9;
                    composer2 = composerStartRestartGroup;
                    BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default8, z19, false, textStyleMerge8, keyboardOptions8, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource12, (Brush) solidColor8, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function1115, Composer composer4, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function1115, composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function1115, Composer composer4, int i2113) {
                            Function2<? super Composer, ? super Integer, Unit> function1116;
                            int i30;
                            ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                            if ((i2113 & 6) == 0) {
                                function1116 = function1115;
                                i30 = i2113 | (composer4.changedInstance(function1116) ? 4 : 2);
                            } else {
                                function1116 = function1115;
                                i30 = i2113;
                            }
                            if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                                }
                                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                                String str2 = str;
                                int i31 = i30;
                                boolean z110 = z19;
                                VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                                MutableInteractionSource mutableInteractionSource13 = mutableInteractionSource12;
                                Function2<Composer, Integer, Unit> function1117 = function1112;
                                final Function2<? super Composer, ? super Integer, Unit> function1118 = function1113;
                                composer4.startReplaceGroup(-1102017390);
                                ComposerKt.sourceInformation(composer4, "*551@26196L64");
                                ComposableLambda composableLambdaRememberComposableLambda = function1118 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i32) {
                                        ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                        if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                            composer5.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                        }
                                        Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function1119 = function1118;
                                        ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer5.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer5.startReusableNode();
                                        if (composer5.getInserting()) {
                                            composer5.createNode(constructor);
                                        } else {
                                            composer5.useNode();
                                        }
                                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                        function1119.invoke(composer5, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        composer5.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer4, 54);
                                composer4.endReplaceGroup();
                                final Function2<? super Composer, ? super Integer, Unit> function1119 = function1114;
                                composer4.startReplaceGroup(-1102010155);
                                ComposerKt.sourceInformation(composer4, "*555@26423L66");
                                ComposableLambda composableLambdaRememberComposableLambda2 = function1119 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i32) {
                                        ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                        if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                            composer5.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                        }
                                        Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function11110 = function1119;
                                        ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer5.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer5.startReusableNode();
                                        if (composer5.getInserting()) {
                                            composer5.createNode(constructor);
                                        } else {
                                            composer5.useNode();
                                        }
                                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                        function11110.invoke(composer5, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        composer5.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer4, 54) : null;
                                composer4.endReplaceGroup();
                                textFieldDefaults.DecorationBox(str2, function1116, z110, true, none, mutableInteractionSource13, false, null, function1117, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors10, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer4.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, i2111 | (i2112 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                    if (z) {
                        z10 = false;
                    } else {
                        z10 = false;
                    }
                    Boolean boolValueOf8 = Boolean.valueOf(z);
                    ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
                    zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
                    objRememberedValue5 = composer2.rememberedValue();
                    if (!zChanged2) {
                        objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                        composer2.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                        composer2.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    EffectsKt.LaunchedEffect(boolValueOf8, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i2112 & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    modifier3 = modifier12;
                    textFieldColors2 = textFieldColors10;
                    function8 = function7;
                    function9 = function5;
                    function10 = function6;
                    z11 = z19;
                } else {
                    composerStartRestartGroup.startDefaults();
                    i23 = i21;
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i10 != 0) {
                            z4 = true;
                        } else {
                            z4 = z3;
                        }
                        if (i12 != 0) {
                            function7 = null;
                        } else {
                            function7 = function7;
                        }
                        if (i14 != 0) {
                            function5 = null;
                        } else {
                            function5 = function5;
                        }
                        if (i16 != 0) {
                            function6 = null;
                        } else {
                            function6 = function6;
                        }
                        if ((i3 & 1024) != 0) {
                            i24 = i20;
                            i19 = i19;
                            textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                            i25 = i23 & (-15);
                        } else {
                            i19 = i19;
                            i24 = i20;
                            textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                            i25 = i23;
                        }
                        if (i24 != 0) {
                            composerStartRestartGroup = composerStartRestartGroup;
                            z3 = z4;
                            mutableInteractionSource2 = null;
                        } else {
                            composerStartRestartGroup = composerStartRestartGroup;
                            mutableInteractionSource2 = mutableInteractionSource;
                            z3 = z4;
                        }
                    } else {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i10 != 0) {
                            z4 = true;
                        } else {
                            z4 = z3;
                        }
                        if (i12 != 0) {
                            function7 = null;
                        } else {
                            function7 = function7;
                        }
                        if (i14 != 0) {
                            function5 = null;
                        } else {
                            function5 = function5;
                        }
                        if (i16 != 0) {
                            function6 = null;
                        } else {
                            function6 = function6;
                        }
                        if ((i3 & 1024) != 0) {
                            i24 = i20;
                            i19 = i19;
                            textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                            i25 = i23 & (-15);
                        } else {
                            i19 = i19;
                            i24 = i20;
                            textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                            i25 = i23;
                        }
                        if (i24 != 0) {
                            composerStartRestartGroup = composerStartRestartGroup;
                            z3 = z4;
                            mutableInteractionSource2 = null;
                        } else {
                            composerStartRestartGroup = composerStartRestartGroup;
                            mutableInteractionSource2 = mutableInteractionSource;
                            z3 = z4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
                    }
                    composerStartRestartGroup.startReplaceGroup(-320443616);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
                    if (mutableInteractionSource2 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new FocusRequester();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    focusRequester = (FocusRequester) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ProvidableCompositionLocal<FocusManager> localFocusManager9 = CompositionLocalsKt.getLocalFocusManager();
                    Modifier modifier13 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume115 = composerStartRestartGroup.consume(localFocusManager9);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    focusManager = (FocusManager) objConsume115;
                    Strings.Companion companion18 = Strings.INSTANCE;
                    strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
                    Strings.Companion companion19 = Strings.INSTANCE;
                    strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
                    ProvidableCompositionLocal<TextStyle> localTextStyle17 = TextKt.getLocalTextStyle();
                    final MutableInteractionSource mutableInteractionSource13 = mutableInteractionSource3;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume116 = composerStartRestartGroup.consume(localTextStyle17);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    jM6374getColor0d7_KjU = ((TextStyle) objConsume116).m6374getColor0d7_KjU();
                    if (jM6374getColor0d7_KjU == 16) {
                        jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
                    }
                    long j9 = jM6374getColor0d7_KjU;
                    Modifier modifierFocusRequester9 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier13, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
                    final boolean z110 = z3;
                    if ((57344 & i19) == 16384) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z5) {
                        objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                invoke2(focusState);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(FocusState focusState) {
                                if (focusState.isFocused()) {
                                    function3.invoke(true);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                                invoke2(focusState);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(FocusState focusState) {
                                if (focusState.isFocused()) {
                                    function3.invoke(true);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierOnFocusChanged9 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester9, (Function1) objRememberedValue2);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
                    boolean zChanged11 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
                    if ((i19 & 7168) == 2048) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    zChanged = z6 | zChanged11 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                                if (z) {
                                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                                }
                                final FocusRequester focusRequester2 = focusRequester;
                                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        focusRequester2.requestFocus();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                                if (z) {
                                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                                }
                                final FocusRequester focusRequester2 = focusRequester;
                                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        focusRequester2.requestFocus();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierSemantics$default9 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged9, false, (Function1) objRememberedValue3, 1, null);
                    ProvidableCompositionLocal<TextStyle> localTextStyle18 = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume117 = composerStartRestartGroup.consume(localTextStyle18);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    TextStyle textStyleMerge9 = ((TextStyle) objConsume117).merge(new TextStyle(j9, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                    SolidColor solidColor9 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
                    KeyboardOptions keyboardOptions9 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
                    if ((i19 & 896) == 256) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    i26 = i19 & 14;
                    if (i26 == 4) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    z9 = z7 | z8;
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (!z9) {
                        objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope keyboardActionScope) {
                                function2.invoke(str);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope keyboardActionScope) {
                                function2.invoke(str);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    final TextFieldColors textFieldColors11 = textFieldColorsM2140inputFieldColorsITpI4ow;
                    final Function2<? super Composer, ? super Integer, Unit> function1115 = function7;
                    final Function2<? super Composer, ? super Integer, Unit> function1116 = function5;
                    final Function2<? super Composer, ? super Integer, Unit> function1117 = function6;
                    int i2113 = i26 | 102236160 | (i19 & 112);
                    int i2114 = i19 >> 9;
                    composer2 = composerStartRestartGroup;
                    BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default9, z110, false, textStyleMerge9, keyboardOptions9, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource13, (Brush) solidColor9, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function1118, Composer composer4, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function1118, composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function1118, Composer composer4, int i2115) {
                            Function2<? super Composer, ? super Integer, Unit> function1119;
                            int i30;
                            ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                            if ((i2115 & 6) == 0) {
                                function1119 = function1118;
                                i30 = i2115 | (composer4.changedInstance(function1119) ? 4 : 2);
                            } else {
                                function1119 = function1118;
                                i30 = i2115;
                            }
                            if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                                }
                                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                                String str2 = str;
                                int i31 = i30;
                                boolean z111 = z110;
                                VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                                MutableInteractionSource mutableInteractionSource14 = mutableInteractionSource13;
                                Function2<Composer, Integer, Unit> function11110 = function1115;
                                final Function2<? super Composer, ? super Integer, Unit> function11111 = function1116;
                                composer4.startReplaceGroup(-1102017390);
                                ComposerKt.sourceInformation(composer4, "*551@26196L64");
                                ComposableLambda composableLambdaRememberComposableLambda = function11111 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i32) {
                                        ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                        if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                            composer5.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                        }
                                        Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function11112 = function11111;
                                        ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer5.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer5.startReusableNode();
                                        if (composer5.getInserting()) {
                                            composer5.createNode(constructor);
                                        } else {
                                            composer5.useNode();
                                        }
                                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                        function11112.invoke(composer5, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        composer5.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer4, 54);
                                composer4.endReplaceGroup();
                                final Function2<? super Composer, ? super Integer, Unit> function11112 = function1117;
                                composer4.startReplaceGroup(-1102010155);
                                ComposerKt.sourceInformation(composer4, "*555@26423L66");
                                ComposableLambda composableLambdaRememberComposableLambda2 = function11112 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i32) {
                                        ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                        if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                            composer5.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                        }
                                        Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function11113 = function11112;
                                        ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer5.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer5.startReusableNode();
                                        if (composer5.getInserting()) {
                                            composer5.createNode(constructor);
                                        } else {
                                            composer5.useNode();
                                        }
                                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                        function11113.invoke(composer5, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        composer5.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        ComposerKt.sourceInformationMarkerEnd(composer5);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer4, 54) : null;
                                composer4.endReplaceGroup();
                                textFieldDefaults.DecorationBox(str2, function1119, z111, true, none, mutableInteractionSource14, false, null, function11110, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors11, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer4.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, i2113 | (i2114 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                    if (z) {
                        z10 = false;
                    } else {
                        z10 = false;
                    }
                    Boolean boolValueOf9 = Boolean.valueOf(z);
                    ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
                    zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
                    objRememberedValue5 = composer2.rememberedValue();
                    if (!zChanged2) {
                        objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                        composer2.updateRememberedValue(objRememberedValue5);
                    } else {
                        objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                        composer2.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    EffectsKt.LaunchedEffect(boolValueOf9, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i2114 & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    modifier3 = modifier13;
                    textFieldColors2 = textFieldColors11;
                    function8 = function7;
                    function9 = function5;
                    function10 = function6;
                    z11 = z110;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i2115) {
                            SearchBarDefaults.this.InputField(str, function1, function2, z, function3, modifier3, z11, function8, function9, function10, textFieldColors2, mutableInteractionSource4, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i21 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            if ((i19 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                i23 = i21;
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i10 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i12 != 0) {
                        function7 = null;
                    } else {
                        function7 = function7;
                    }
                    if (i14 != 0) {
                        function5 = null;
                    } else {
                        function5 = function5;
                    }
                    if (i16 != 0) {
                        function6 = null;
                    } else {
                        function6 = function6;
                    }
                    if ((i3 & 1024) != 0) {
                        i24 = i20;
                        i19 = i19;
                        textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                        i25 = i23 & (-15);
                    } else {
                        i19 = i19;
                        i24 = i20;
                        textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                        i25 = i23;
                    }
                    if (i24 != 0) {
                        composerStartRestartGroup = composerStartRestartGroup;
                        z3 = z4;
                        mutableInteractionSource2 = null;
                    } else {
                        composerStartRestartGroup = composerStartRestartGroup;
                        mutableInteractionSource2 = mutableInteractionSource;
                        z3 = z4;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i10 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i12 != 0) {
                        function7 = null;
                    } else {
                        function7 = function7;
                    }
                    if (i14 != 0) {
                        function5 = null;
                    } else {
                        function5 = function5;
                    }
                    if (i16 != 0) {
                        function6 = null;
                    } else {
                        function6 = function6;
                    }
                    if ((i3 & 1024) != 0) {
                        i24 = i20;
                        i19 = i19;
                        textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                        i25 = i23 & (-15);
                    } else {
                        i19 = i19;
                        i24 = i20;
                        textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                        i25 = i23;
                    }
                    if (i24 != 0) {
                        composerStartRestartGroup = composerStartRestartGroup;
                        z3 = z4;
                        mutableInteractionSource2 = null;
                    } else {
                        composerStartRestartGroup = composerStartRestartGroup;
                        mutableInteractionSource2 = mutableInteractionSource;
                        z3 = z4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
                }
                composerStartRestartGroup.startReplaceGroup(-320443616);
                ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
                if (mutableInteractionSource2 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                composerStartRestartGroup.endReplaceGroup();
                zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new FocusRequester();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                focusRequester = (FocusRequester) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<FocusManager> localFocusManager10 = CompositionLocalsKt.getLocalFocusManager();
                Modifier modifier14 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume118 = composerStartRestartGroup.consume(localFocusManager10);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                focusManager = (FocusManager) objConsume118;
                Strings.Companion companion110 = Strings.INSTANCE;
                strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
                Strings.Companion companion111 = Strings.INSTANCE;
                strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
                ProvidableCompositionLocal<TextStyle> localTextStyle19 = TextKt.getLocalTextStyle();
                final MutableInteractionSource mutableInteractionSource14 = mutableInteractionSource3;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume119 = composerStartRestartGroup.consume(localTextStyle19);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                jM6374getColor0d7_KjU = ((TextStyle) objConsume119).m6374getColor0d7_KjU();
                if (jM6374getColor0d7_KjU == 16) {
                    jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
                }
                long j10 = jM6374getColor0d7_KjU;
                Modifier modifierFocusRequester10 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier14, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
                final boolean z111 = z3;
                if ((57344 & i19) == 16384) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z5) {
                    objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState focusState) {
                            if (focusState.isFocused()) {
                                function3.invoke(true);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState focusState) {
                            if (focusState.isFocused()) {
                                function3.invoke(true);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierOnFocusChanged10 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester10, (Function1) objRememberedValue2);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
                boolean zChanged12 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
                if ((i19 & 7168) == 2048) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                zChanged = z6 | zChanged12 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                            if (z) {
                                SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                            }
                            final FocusRequester focusRequester2 = focusRequester;
                            SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    focusRequester2.requestFocus();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                            if (z) {
                                SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                            }
                            final FocusRequester focusRequester2 = focusRequester;
                            SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    focusRequester2.requestFocus();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierSemantics$default10 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged10, false, (Function1) objRememberedValue3, 1, null);
                ProvidableCompositionLocal<TextStyle> localTextStyle110 = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1110 = composerStartRestartGroup.consume(localTextStyle110);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                TextStyle textStyleMerge10 = ((TextStyle) objConsume1110).merge(new TextStyle(j10, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                SolidColor solidColor10 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
                KeyboardOptions keyboardOptions10 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
                if ((i19 & 896) == 256) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                i26 = i19 & 14;
                if (i26 == 4) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                z9 = z7 | z8;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!z9) {
                    objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                            invoke2(keyboardActionScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyboardActionScope keyboardActionScope) {
                            function2.invoke(str);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                            invoke2(keyboardActionScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyboardActionScope keyboardActionScope) {
                            function2.invoke(str);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final TextFieldColors textFieldColors12 = textFieldColorsM2140inputFieldColorsITpI4ow;
                final Function2<? super Composer, ? super Integer, Unit> function1118 = function7;
                final Function2<? super Composer, ? super Integer, Unit> function1119 = function5;
                final Function2<? super Composer, ? super Integer, Unit> function11110 = function6;
                int i2115 = i26 | 102236160 | (i19 & 112);
                int i2116 = i19 >> 9;
                composer2 = composerStartRestartGroup;
                BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default10, z111, false, textStyleMerge10, keyboardOptions10, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource14, (Brush) solidColor10, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function11111, Composer composer4, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function11111, composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function11111, Composer composer4, int i2117) {
                        Function2<? super Composer, ? super Integer, Unit> function11112;
                        int i30;
                        ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                        if ((i2117 & 6) == 0) {
                            function11112 = function11111;
                            i30 = i2117 | (composer4.changedInstance(function11112) ? 4 : 2);
                        } else {
                            function11112 = function11111;
                            i30 = i2117;
                        }
                        if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            String str2 = str;
                            int i31 = i30;
                            boolean z112 = z111;
                            VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                            MutableInteractionSource mutableInteractionSource15 = mutableInteractionSource14;
                            Function2<Composer, Integer, Unit> function11113 = function1118;
                            final Function2<? super Composer, ? super Integer, Unit> function11114 = function1119;
                            composer4.startReplaceGroup(-1102017390);
                            ComposerKt.sourceInformation(composer4, "*551@26196L64");
                            ComposableLambda composableLambdaRememberComposableLambda = function11114 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                    invoke(composer5, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer5, int i32) {
                                    ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                    if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                        composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                    }
                                    Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function11115 = function11114;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer5.startReusableNode();
                                    if (composer5.getInserting()) {
                                        composer5.createNode(constructor);
                                    } else {
                                        composer5.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                    function11115.invoke(composer5, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer4, 54);
                            composer4.endReplaceGroup();
                            final Function2<? super Composer, ? super Integer, Unit> function11115 = function11110;
                            composer4.startReplaceGroup(-1102010155);
                            ComposerKt.sourceInformation(composer4, "*555@26423L66");
                            ComposableLambda composableLambdaRememberComposableLambda2 = function11115 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                    invoke(composer5, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer5, int i32) {
                                    ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                    if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                        composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                    }
                                    Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function11116 = function11115;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer5.startReusableNode();
                                    if (composer5.getInserting()) {
                                        composer5.createNode(constructor);
                                    } else {
                                        composer5.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                    function11116.invoke(composer5, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer4, 54) : null;
                            composer4.endReplaceGroup();
                            textFieldDefaults.DecorationBox(str2, function11112, z112, true, none, mutableInteractionSource15, false, null, function11113, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors12, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer4.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, i2115 | (i2116 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                if (z) {
                    z10 = false;
                } else {
                    z10 = false;
                }
                Boolean boolValueOf10 = Boolean.valueOf(z);
                ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
                zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
                objRememberedValue5 = composer2.rememberedValue();
                if (!zChanged2) {
                    objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                    composer2.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                    composer2.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                EffectsKt.LaunchedEffect(boolValueOf10, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i2116 & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                mutableInteractionSource4 = mutableInteractionSource2;
                modifier3 = modifier14;
                textFieldColors2 = textFieldColors12;
                function8 = function7;
                function9 = function5;
                function10 = function6;
                z11 = z111;
            } else {
                composerStartRestartGroup.startDefaults();
                i23 = i21;
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i10 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i12 != 0) {
                        function7 = null;
                    } else {
                        function7 = function7;
                    }
                    if (i14 != 0) {
                        function5 = null;
                    } else {
                        function5 = function5;
                    }
                    if (i16 != 0) {
                        function6 = null;
                    } else {
                        function6 = function6;
                    }
                    if ((i3 & 1024) != 0) {
                        i24 = i20;
                        i19 = i19;
                        textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                        i25 = i23 & (-15);
                    } else {
                        i19 = i19;
                        i24 = i20;
                        textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                        i25 = i23;
                    }
                    if (i24 != 0) {
                        composerStartRestartGroup = composerStartRestartGroup;
                        z3 = z4;
                        mutableInteractionSource2 = null;
                    } else {
                        composerStartRestartGroup = composerStartRestartGroup;
                        mutableInteractionSource2 = mutableInteractionSource;
                        z3 = z4;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i10 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i12 != 0) {
                        function7 = null;
                    } else {
                        function7 = function7;
                    }
                    if (i14 != 0) {
                        function5 = null;
                    } else {
                        function5 = function5;
                    }
                    if (i16 != 0) {
                        function6 = null;
                    } else {
                        function6 = function6;
                    }
                    if ((i3 & 1024) != 0) {
                        i24 = i20;
                        i19 = i19;
                        textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                        i25 = i23 & (-15);
                    } else {
                        i19 = i19;
                        i24 = i20;
                        textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                        i25 = i23;
                    }
                    if (i24 != 0) {
                        composerStartRestartGroup = composerStartRestartGroup;
                        z3 = z4;
                        mutableInteractionSource2 = null;
                    } else {
                        composerStartRestartGroup = composerStartRestartGroup;
                        mutableInteractionSource2 = mutableInteractionSource;
                        z3 = z4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
                }
                composerStartRestartGroup.startReplaceGroup(-320443616);
                ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
                if (mutableInteractionSource2 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                composerStartRestartGroup.endReplaceGroup();
                zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new FocusRequester();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                focusRequester = (FocusRequester) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<FocusManager> localFocusManager11 = CompositionLocalsKt.getLocalFocusManager();
                Modifier modifier15 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1111 = composerStartRestartGroup.consume(localFocusManager11);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                focusManager = (FocusManager) objConsume1111;
                Strings.Companion companion112 = Strings.INSTANCE;
                strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
                Strings.Companion companion113 = Strings.INSTANCE;
                strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
                ProvidableCompositionLocal<TextStyle> localTextStyle111 = TextKt.getLocalTextStyle();
                final MutableInteractionSource mutableInteractionSource15 = mutableInteractionSource3;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1112 = composerStartRestartGroup.consume(localTextStyle111);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                jM6374getColor0d7_KjU = ((TextStyle) objConsume1112).m6374getColor0d7_KjU();
                if (jM6374getColor0d7_KjU == 16) {
                    jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
                }
                long j11 = jM6374getColor0d7_KjU;
                Modifier modifierFocusRequester11 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier15, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
                final boolean z112 = z3;
                if ((57344 & i19) == 16384) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z5) {
                    objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState focusState) {
                            if (focusState.isFocused()) {
                                function3.invoke(true);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState focusState) {
                            if (focusState.isFocused()) {
                                function3.invoke(true);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierOnFocusChanged11 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester11, (Function1) objRememberedValue2);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
                boolean zChanged13 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
                if ((i19 & 7168) == 2048) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                zChanged = z6 | zChanged13 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                            if (z) {
                                SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                            }
                            final FocusRequester focusRequester2 = focusRequester;
                            SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    focusRequester2.requestFocus();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                            if (z) {
                                SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                            }
                            final FocusRequester focusRequester2 = focusRequester;
                            SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    focusRequester2.requestFocus();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierSemantics$default11 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged11, false, (Function1) objRememberedValue3, 1, null);
                ProvidableCompositionLocal<TextStyle> localTextStyle112 = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1113 = composerStartRestartGroup.consume(localTextStyle112);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                TextStyle textStyleMerge11 = ((TextStyle) objConsume1113).merge(new TextStyle(j11, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                SolidColor solidColor11 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
                KeyboardOptions keyboardOptions11 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
                if ((i19 & 896) == 256) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                i26 = i19 & 14;
                if (i26 == 4) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                z9 = z7 | z8;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!z9) {
                    objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                            invoke2(keyboardActionScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyboardActionScope keyboardActionScope) {
                            function2.invoke(str);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                            invoke2(keyboardActionScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyboardActionScope keyboardActionScope) {
                            function2.invoke(str);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final TextFieldColors textFieldColors13 = textFieldColorsM2140inputFieldColorsITpI4ow;
                final Function2<? super Composer, ? super Integer, Unit> function11111 = function7;
                final Function2<? super Composer, ? super Integer, Unit> function11112 = function5;
                final Function2<? super Composer, ? super Integer, Unit> function11113 = function6;
                int i2117 = i26 | 102236160 | (i19 & 112);
                int i2118 = i19 >> 9;
                composer2 = composerStartRestartGroup;
                BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default11, z112, false, textStyleMerge11, keyboardOptions11, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource15, (Brush) solidColor11, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function11114, Composer composer4, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function11114, composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function11114, Composer composer4, int i2119) {
                        Function2<? super Composer, ? super Integer, Unit> function11115;
                        int i30;
                        ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                        if ((i2119 & 6) == 0) {
                            function11115 = function11114;
                            i30 = i2119 | (composer4.changedInstance(function11115) ? 4 : 2);
                        } else {
                            function11115 = function11114;
                            i30 = i2119;
                        }
                        if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            String str2 = str;
                            int i31 = i30;
                            boolean z113 = z112;
                            VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                            MutableInteractionSource mutableInteractionSource16 = mutableInteractionSource15;
                            Function2<Composer, Integer, Unit> function11116 = function11111;
                            final Function2<? super Composer, ? super Integer, Unit> function11117 = function11112;
                            composer4.startReplaceGroup(-1102017390);
                            ComposerKt.sourceInformation(composer4, "*551@26196L64");
                            ComposableLambda composableLambdaRememberComposableLambda = function11117 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                    invoke(composer5, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer5, int i32) {
                                    ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                    if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                        composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                    }
                                    Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function11118 = function11117;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer5.startReusableNode();
                                    if (composer5.getInserting()) {
                                        composer5.createNode(constructor);
                                    } else {
                                        composer5.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                    function11118.invoke(composer5, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer4, 54);
                            composer4.endReplaceGroup();
                            final Function2<? super Composer, ? super Integer, Unit> function11118 = function11113;
                            composer4.startReplaceGroup(-1102010155);
                            ComposerKt.sourceInformation(composer4, "*555@26423L66");
                            ComposableLambda composableLambdaRememberComposableLambda2 = function11118 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                    invoke(composer5, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer5, int i32) {
                                    ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                    if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                        composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                    }
                                    Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function11119 = function11118;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer5.startReusableNode();
                                    if (composer5.getInserting()) {
                                        composer5.createNode(constructor);
                                    } else {
                                        composer5.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                    function11119.invoke(composer5, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer4, 54) : null;
                            composer4.endReplaceGroup();
                            textFieldDefaults.DecorationBox(str2, function11115, z113, true, none, mutableInteractionSource16, false, null, function11116, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors13, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer4.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, i2117 | (i2118 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                if (z) {
                    z10 = false;
                } else {
                    z10 = false;
                }
                Boolean boolValueOf11 = Boolean.valueOf(z);
                ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
                zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
                objRememberedValue5 = composer2.rememberedValue();
                if (!zChanged2) {
                    objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                    composer2.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                    composer2.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                EffectsKt.LaunchedEffect(boolValueOf11, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i2118 & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                mutableInteractionSource4 = mutableInteractionSource2;
                modifier3 = modifier15;
                textFieldColors2 = textFieldColors13;
                function8 = function7;
                function9 = function5;
                function10 = function6;
                z11 = z112;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.6
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i2119) {
                        SearchBarDefaults.this.InputField(str, function1, function2, z, function3, modifier3, z11, function8, function9, function10, textFieldColors2, mutableInteractionSource4, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                    }
                });
            }
        }
        i4 |= BaseAdapter.HEADER;
        if ((i2 & 6) == 0) {
            i18 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(textFieldColors)) ? 2 : 4);
        } else {
            i18 = i2;
        }
        i19 = i4;
        i20 = i3 & 2048;
        if (i20 != 0) {
            i20 = i20;
            i21 = i18 | 48;
            mutableInteractionSource2 = mutableInteractionSource;
        } else {
            if ((i2 & 48) == 0) {
                mutableInteractionSource2 = mutableInteractionSource;
                if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                    i22 = 32;
                } else {
                    i22 = 16;
                }
                i18 |= i22;
            } else {
                mutableInteractionSource2 = mutableInteractionSource;
            }
            i21 = i18;
        }
        if ((i3 & 4096) != 0) {
            if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                i21 |= composerStartRestartGroup.changed(this) ? 256 : 128;
            }
            if ((i19 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                i23 = i21;
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i10 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i12 != 0) {
                        function7 = null;
                    } else {
                        function7 = function7;
                    }
                    if (i14 != 0) {
                        function5 = null;
                    } else {
                        function5 = function5;
                    }
                    if (i16 != 0) {
                        function6 = null;
                    } else {
                        function6 = function6;
                    }
                    if ((i3 & 1024) != 0) {
                        i24 = i20;
                        i19 = i19;
                        textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                        i25 = i23 & (-15);
                    } else {
                        i19 = i19;
                        i24 = i20;
                        textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                        i25 = i23;
                    }
                    if (i24 != 0) {
                        composerStartRestartGroup = composerStartRestartGroup;
                        z3 = z4;
                        mutableInteractionSource2 = null;
                    } else {
                        composerStartRestartGroup = composerStartRestartGroup;
                        mutableInteractionSource2 = mutableInteractionSource;
                        z3 = z4;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i10 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i12 != 0) {
                        function7 = null;
                    } else {
                        function7 = function7;
                    }
                    if (i14 != 0) {
                        function5 = null;
                    } else {
                        function5 = function5;
                    }
                    if (i16 != 0) {
                        function6 = null;
                    } else {
                        function6 = function6;
                    }
                    if ((i3 & 1024) != 0) {
                        i24 = i20;
                        i19 = i19;
                        textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                        i25 = i23 & (-15);
                    } else {
                        i19 = i19;
                        i24 = i20;
                        textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                        i25 = i23;
                    }
                    if (i24 != 0) {
                        composerStartRestartGroup = composerStartRestartGroup;
                        z3 = z4;
                        mutableInteractionSource2 = null;
                    } else {
                        composerStartRestartGroup = composerStartRestartGroup;
                        mutableInteractionSource2 = mutableInteractionSource;
                        z3 = z4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
                }
                composerStartRestartGroup.startReplaceGroup(-320443616);
                ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
                if (mutableInteractionSource2 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                composerStartRestartGroup.endReplaceGroup();
                zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new FocusRequester();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                focusRequester = (FocusRequester) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<FocusManager> localFocusManager12 = CompositionLocalsKt.getLocalFocusManager();
                Modifier modifier16 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1114 = composerStartRestartGroup.consume(localFocusManager12);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                focusManager = (FocusManager) objConsume1114;
                Strings.Companion companion114 = Strings.INSTANCE;
                strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
                Strings.Companion companion115 = Strings.INSTANCE;
                strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
                ProvidableCompositionLocal<TextStyle> localTextStyle113 = TextKt.getLocalTextStyle();
                final MutableInteractionSource mutableInteractionSource16 = mutableInteractionSource3;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1115 = composerStartRestartGroup.consume(localTextStyle113);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                jM6374getColor0d7_KjU = ((TextStyle) objConsume1115).m6374getColor0d7_KjU();
                if (jM6374getColor0d7_KjU == 16) {
                    jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
                }
                long j12 = jM6374getColor0d7_KjU;
                Modifier modifierFocusRequester12 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier16, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
                final boolean z113 = z3;
                if ((57344 & i19) == 16384) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z5) {
                    objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState focusState) {
                            if (focusState.isFocused()) {
                                function3.invoke(true);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState focusState) {
                            if (focusState.isFocused()) {
                                function3.invoke(true);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierOnFocusChanged12 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester12, (Function1) objRememberedValue2);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
                boolean zChanged14 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
                if ((i19 & 7168) == 2048) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                zChanged = z6 | zChanged14 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                            if (z) {
                                SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                            }
                            final FocusRequester focusRequester2 = focusRequester;
                            SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    focusRequester2.requestFocus();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                            if (z) {
                                SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                            }
                            final FocusRequester focusRequester2 = focusRequester;
                            SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    focusRequester2.requestFocus();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierSemantics$default12 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged12, false, (Function1) objRememberedValue3, 1, null);
                ProvidableCompositionLocal<TextStyle> localTextStyle114 = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1116 = composerStartRestartGroup.consume(localTextStyle114);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                TextStyle textStyleMerge12 = ((TextStyle) objConsume1116).merge(new TextStyle(j12, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                SolidColor solidColor12 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
                KeyboardOptions keyboardOptions12 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
                if ((i19 & 896) == 256) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                i26 = i19 & 14;
                if (i26 == 4) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                z9 = z7 | z8;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!z9) {
                    objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                            invoke2(keyboardActionScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyboardActionScope keyboardActionScope) {
                            function2.invoke(str);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                            invoke2(keyboardActionScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyboardActionScope keyboardActionScope) {
                            function2.invoke(str);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final TextFieldColors textFieldColors14 = textFieldColorsM2140inputFieldColorsITpI4ow;
                final Function2<? super Composer, ? super Integer, Unit> function11114 = function7;
                final Function2<? super Composer, ? super Integer, Unit> function11115 = function5;
                final Function2<? super Composer, ? super Integer, Unit> function11116 = function6;
                int i2119 = i26 | 102236160 | (i19 & 112);
                int i21110 = i19 >> 9;
                composer2 = composerStartRestartGroup;
                BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default12, z113, false, textStyleMerge12, keyboardOptions12, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource16, (Brush) solidColor12, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function11117, Composer composer4, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function11117, composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function11117, Composer composer4, int i21111) {
                        Function2<? super Composer, ? super Integer, Unit> function11118;
                        int i30;
                        ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                        if ((i21111 & 6) == 0) {
                            function11118 = function11117;
                            i30 = i21111 | (composer4.changedInstance(function11118) ? 4 : 2);
                        } else {
                            function11118 = function11117;
                            i30 = i21111;
                        }
                        if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            String str2 = str;
                            int i31 = i30;
                            boolean z114 = z113;
                            VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                            MutableInteractionSource mutableInteractionSource17 = mutableInteractionSource16;
                            Function2<Composer, Integer, Unit> function11119 = function11114;
                            final Function2<? super Composer, ? super Integer, Unit> function111110 = function11115;
                            composer4.startReplaceGroup(-1102017390);
                            ComposerKt.sourceInformation(composer4, "*551@26196L64");
                            ComposableLambda composableLambdaRememberComposableLambda = function111110 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                    invoke(composer5, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer5, int i32) {
                                    ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                    if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                        composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                    }
                                    Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function111111 = function111110;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer5.startReusableNode();
                                    if (composer5.getInserting()) {
                                        composer5.createNode(constructor);
                                    } else {
                                        composer5.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                    function111111.invoke(composer5, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer4, 54);
                            composer4.endReplaceGroup();
                            final Function2<? super Composer, ? super Integer, Unit> function111111 = function11116;
                            composer4.startReplaceGroup(-1102010155);
                            ComposerKt.sourceInformation(composer4, "*555@26423L66");
                            ComposableLambda composableLambdaRememberComposableLambda2 = function111111 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                    invoke(composer5, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer5, int i32) {
                                    ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                    if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                        composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                    }
                                    Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function111112 = function111111;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer5.startReusableNode();
                                    if (composer5.getInserting()) {
                                        composer5.createNode(constructor);
                                    } else {
                                        composer5.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                    function111112.invoke(composer5, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer4, 54) : null;
                            composer4.endReplaceGroup();
                            textFieldDefaults.DecorationBox(str2, function11118, z114, true, none, mutableInteractionSource17, false, null, function11119, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors14, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer4.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, i2119 | (i21110 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                if (z) {
                    z10 = false;
                } else {
                    z10 = false;
                }
                Boolean boolValueOf12 = Boolean.valueOf(z);
                ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
                zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
                objRememberedValue5 = composer2.rememberedValue();
                if (!zChanged2) {
                    objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                    composer2.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                    composer2.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                EffectsKt.LaunchedEffect(boolValueOf12, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i21110 & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                mutableInteractionSource4 = mutableInteractionSource2;
                modifier3 = modifier16;
                textFieldColors2 = textFieldColors14;
                function8 = function7;
                function9 = function5;
                function10 = function6;
                z11 = z113;
            } else {
                composerStartRestartGroup.startDefaults();
                i23 = i21;
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i10 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i12 != 0) {
                        function7 = null;
                    } else {
                        function7 = function7;
                    }
                    if (i14 != 0) {
                        function5 = null;
                    } else {
                        function5 = function5;
                    }
                    if (i16 != 0) {
                        function6 = null;
                    } else {
                        function6 = function6;
                    }
                    if ((i3 & 1024) != 0) {
                        i24 = i20;
                        i19 = i19;
                        textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                        i25 = i23 & (-15);
                    } else {
                        i19 = i19;
                        i24 = i20;
                        textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                        i25 = i23;
                    }
                    if (i24 != 0) {
                        composerStartRestartGroup = composerStartRestartGroup;
                        z3 = z4;
                        mutableInteractionSource2 = null;
                    } else {
                        composerStartRestartGroup = composerStartRestartGroup;
                        mutableInteractionSource2 = mutableInteractionSource;
                        z3 = z4;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i10 != 0) {
                        z4 = true;
                    } else {
                        z4 = z3;
                    }
                    if (i12 != 0) {
                        function7 = null;
                    } else {
                        function7 = function7;
                    }
                    if (i14 != 0) {
                        function5 = null;
                    } else {
                        function5 = function5;
                    }
                    if (i16 != 0) {
                        function6 = null;
                    } else {
                        function6 = function6;
                    }
                    if ((i3 & 1024) != 0) {
                        i24 = i20;
                        i19 = i19;
                        textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                        i25 = i23 & (-15);
                    } else {
                        i19 = i19;
                        i24 = i20;
                        textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                        i25 = i23;
                    }
                    if (i24 != 0) {
                        composerStartRestartGroup = composerStartRestartGroup;
                        z3 = z4;
                        mutableInteractionSource2 = null;
                    } else {
                        composerStartRestartGroup = composerStartRestartGroup;
                        mutableInteractionSource2 = mutableInteractionSource;
                        z3 = z4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
                }
                composerStartRestartGroup.startReplaceGroup(-320443616);
                ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
                if (mutableInteractionSource2 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                composerStartRestartGroup.endReplaceGroup();
                zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new FocusRequester();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                focusRequester = (FocusRequester) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProvidableCompositionLocal<FocusManager> localFocusManager13 = CompositionLocalsKt.getLocalFocusManager();
                Modifier modifier17 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1117 = composerStartRestartGroup.consume(localFocusManager13);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                focusManager = (FocusManager) objConsume1117;
                Strings.Companion companion116 = Strings.INSTANCE;
                strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
                Strings.Companion companion117 = Strings.INSTANCE;
                strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
                ProvidableCompositionLocal<TextStyle> localTextStyle115 = TextKt.getLocalTextStyle();
                final MutableInteractionSource mutableInteractionSource17 = mutableInteractionSource3;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1118 = composerStartRestartGroup.consume(localTextStyle115);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                jM6374getColor0d7_KjU = ((TextStyle) objConsume1118).m6374getColor0d7_KjU();
                if (jM6374getColor0d7_KjU == 16) {
                    jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
                }
                long j13 = jM6374getColor0d7_KjU;
                Modifier modifierFocusRequester13 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier17, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
                final boolean z114 = z3;
                if ((57344 & i19) == 16384) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z5) {
                    objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState focusState) {
                            if (focusState.isFocused()) {
                                function3.invoke(true);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState focusState) {
                            if (focusState.isFocused()) {
                                function3.invoke(true);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierOnFocusChanged13 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester13, (Function1) objRememberedValue2);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
                boolean zChanged15 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
                if ((i19 & 7168) == 2048) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                zChanged = z6 | zChanged15 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                            if (z) {
                                SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                            }
                            final FocusRequester focusRequester2 = focusRequester;
                            SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    focusRequester2.requestFocus();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                            if (z) {
                                SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                            }
                            final FocusRequester focusRequester2 = focusRequester;
                            SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    focusRequester2.requestFocus();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierSemantics$default13 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged13, false, (Function1) objRememberedValue3, 1, null);
                ProvidableCompositionLocal<TextStyle> localTextStyle116 = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume1119 = composerStartRestartGroup.consume(localTextStyle116);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                TextStyle textStyleMerge13 = ((TextStyle) objConsume1119).merge(new TextStyle(j13, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                SolidColor solidColor13 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
                KeyboardOptions keyboardOptions13 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
                if ((i19 & 896) == 256) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                i26 = i19 & 14;
                if (i26 == 4) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                z9 = z7 | z8;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!z9) {
                    objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                            invoke2(keyboardActionScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyboardActionScope keyboardActionScope) {
                            function2.invoke(str);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                            invoke2(keyboardActionScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyboardActionScope keyboardActionScope) {
                            function2.invoke(str);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final TextFieldColors textFieldColors15 = textFieldColorsM2140inputFieldColorsITpI4ow;
                final Function2<? super Composer, ? super Integer, Unit> function11117 = function7;
                final Function2<? super Composer, ? super Integer, Unit> function11118 = function5;
                final Function2<? super Composer, ? super Integer, Unit> function11119 = function6;
                int i21111 = i26 | 102236160 | (i19 & 112);
                int i21112 = i19 >> 9;
                composer2 = composerStartRestartGroup;
                BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default13, z114, false, textStyleMerge13, keyboardOptions13, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource17, (Brush) solidColor13, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function111110, Composer composer4, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function111110, composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function111110, Composer composer4, int i21113) {
                        Function2<? super Composer, ? super Integer, Unit> function111111;
                        int i30;
                        ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                        if ((i21113 & 6) == 0) {
                            function111111 = function111110;
                            i30 = i21113 | (composer4.changedInstance(function111111) ? 4 : 2);
                        } else {
                            function111111 = function111110;
                            i30 = i21113;
                        }
                        if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            String str2 = str;
                            int i31 = i30;
                            boolean z115 = z114;
                            VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                            MutableInteractionSource mutableInteractionSource18 = mutableInteractionSource17;
                            Function2<Composer, Integer, Unit> function111112 = function11117;
                            final Function2<? super Composer, ? super Integer, Unit> function111113 = function11118;
                            composer4.startReplaceGroup(-1102017390);
                            ComposerKt.sourceInformation(composer4, "*551@26196L64");
                            ComposableLambda composableLambdaRememberComposableLambda = function111113 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                    invoke(composer5, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer5, int i32) {
                                    ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                    if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                        composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                    }
                                    Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function111114 = function111113;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer5.startReusableNode();
                                    if (composer5.getInserting()) {
                                        composer5.createNode(constructor);
                                    } else {
                                        composer5.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                    function111114.invoke(composer5, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer4, 54);
                            composer4.endReplaceGroup();
                            final Function2<? super Composer, ? super Integer, Unit> function111114 = function11119;
                            composer4.startReplaceGroup(-1102010155);
                            ComposerKt.sourceInformation(composer4, "*555@26423L66");
                            ComposableLambda composableLambdaRememberComposableLambda2 = function111114 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                    invoke(composer5, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer5, int i32) {
                                    ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                    if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                        composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                    }
                                    Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function111115 = function111114;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer5.startReusableNode();
                                    if (composer5.getInserting()) {
                                        composer5.createNode(constructor);
                                    } else {
                                        composer5.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                    function111115.invoke(composer5, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer4, 54) : null;
                            composer4.endReplaceGroup();
                            textFieldDefaults.DecorationBox(str2, function111111, z115, true, none, mutableInteractionSource18, false, null, function111112, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors15, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer4.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, i21111 | (i21112 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                if (z) {
                    z10 = false;
                } else {
                    z10 = false;
                }
                Boolean boolValueOf13 = Boolean.valueOf(z);
                ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
                zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
                objRememberedValue5 = composer2.rememberedValue();
                if (!zChanged2) {
                    objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                    composer2.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                    composer2.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                EffectsKt.LaunchedEffect(boolValueOf13, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i21112 & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                mutableInteractionSource4 = mutableInteractionSource2;
                modifier3 = modifier17;
                textFieldColors2 = textFieldColors15;
                function8 = function7;
                function9 = function5;
                function10 = function6;
                z11 = z114;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.6
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i21113) {
                        SearchBarDefaults.this.InputField(str, function1, function2, z, function3, modifier3, z11, function8, function9, function10, textFieldColors2, mutableInteractionSource4, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                    }
                });
            }
        }
        i21 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        if ((i19 & 306783379) == 306783378) {
            composerStartRestartGroup.startDefaults();
            i23 = i21;
            if ((i & 1) != 0) {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i10 != 0) {
                    z4 = true;
                } else {
                    z4 = z3;
                }
                if (i12 != 0) {
                    function7 = null;
                } else {
                    function7 = function7;
                }
                if (i14 != 0) {
                    function5 = null;
                } else {
                    function5 = function5;
                }
                if (i16 != 0) {
                    function6 = null;
                } else {
                    function6 = function6;
                }
                if ((i3 & 1024) != 0) {
                    i24 = i20;
                    i19 = i19;
                    textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                    i25 = i23 & (-15);
                } else {
                    i19 = i19;
                    i24 = i20;
                    textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                    i25 = i23;
                }
                if (i24 != 0) {
                    composerStartRestartGroup = composerStartRestartGroup;
                    z3 = z4;
                    mutableInteractionSource2 = null;
                } else {
                    composerStartRestartGroup = composerStartRestartGroup;
                    mutableInteractionSource2 = mutableInteractionSource;
                    z3 = z4;
                }
            } else {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i10 != 0) {
                    z4 = true;
                } else {
                    z4 = z3;
                }
                if (i12 != 0) {
                    function7 = null;
                } else {
                    function7 = function7;
                }
                if (i14 != 0) {
                    function5 = null;
                } else {
                    function5 = function5;
                }
                if (i16 != 0) {
                    function6 = null;
                } else {
                    function6 = function6;
                }
                if ((i3 & 1024) != 0) {
                    i24 = i20;
                    i19 = i19;
                    textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                    i25 = i23 & (-15);
                } else {
                    i19 = i19;
                    i24 = i20;
                    textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                    i25 = i23;
                }
                if (i24 != 0) {
                    composerStartRestartGroup = composerStartRestartGroup;
                    z3 = z4;
                    mutableInteractionSource2 = null;
                } else {
                    composerStartRestartGroup = composerStartRestartGroup;
                    mutableInteractionSource2 = mutableInteractionSource;
                    z3 = z4;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
            }
            composerStartRestartGroup.startReplaceGroup(-320443616);
            ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
            if (mutableInteractionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
            } else {
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            composerStartRestartGroup.endReplaceGroup();
            zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new FocusRequester();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            focusRequester = (FocusRequester) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<FocusManager> localFocusManager14 = CompositionLocalsKt.getLocalFocusManager();
            Modifier modifier18 = companion;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume11110 = composerStartRestartGroup.consume(localFocusManager14);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            focusManager = (FocusManager) objConsume11110;
            Strings.Companion companion118 = Strings.INSTANCE;
            strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
            Strings.Companion companion119 = Strings.INSTANCE;
            strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
            ProvidableCompositionLocal<TextStyle> localTextStyle117 = TextKt.getLocalTextStyle();
            final MutableInteractionSource mutableInteractionSource18 = mutableInteractionSource3;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume11111 = composerStartRestartGroup.consume(localTextStyle117);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            jM6374getColor0d7_KjU = ((TextStyle) objConsume11111).m6374getColor0d7_KjU();
            if (jM6374getColor0d7_KjU == 16) {
                jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
            }
            long j14 = jM6374getColor0d7_KjU;
            Modifier modifierFocusRequester14 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier18, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
            final boolean z115 = z3;
            if ((57344 & i19) == 16384) {
                z5 = true;
            } else {
                z5 = false;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z5) {
                objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                        invoke2(focusState);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FocusState focusState) {
                        if (focusState.isFocused()) {
                            function3.invoke(true);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                        invoke2(focusState);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FocusState focusState) {
                        if (focusState.isFocused()) {
                            function3.invoke(true);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierOnFocusChanged14 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester14, (Function1) objRememberedValue2);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
            boolean zChanged16 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
            if ((i19 & 7168) == 2048) {
                z6 = true;
            } else {
                z6 = false;
            }
            zChanged = z6 | zChanged16 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                        if (z) {
                            SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                        }
                        final FocusRequester focusRequester2 = focusRequester;
                        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                focusRequester2.requestFocus();
                                return true;
                            }
                        }, 1, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                        if (z) {
                            SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                        }
                        final FocusRequester focusRequester2 = focusRequester;
                        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                focusRequester2.requestFocus();
                                return true;
                            }
                        }, 1, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierSemantics$default14 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged14, false, (Function1) objRememberedValue3, 1, null);
            ProvidableCompositionLocal<TextStyle> localTextStyle118 = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume11112 = composerStartRestartGroup.consume(localTextStyle118);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            TextStyle textStyleMerge14 = ((TextStyle) objConsume11112).merge(new TextStyle(j14, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            SolidColor solidColor14 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
            KeyboardOptions keyboardOptions14 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
            if ((i19 & 896) == 256) {
                z7 = true;
            } else {
                z7 = false;
            }
            i26 = i19 & 14;
            if (i26 == 4) {
                z8 = true;
            } else {
                z8 = false;
            }
            z9 = z7 | z8;
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (!z9) {
                objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                        invoke2(keyboardActionScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyboardActionScope keyboardActionScope) {
                        function2.invoke(str);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                        invoke2(keyboardActionScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyboardActionScope keyboardActionScope) {
                        function2.invoke(str);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final TextFieldColors textFieldColors16 = textFieldColorsM2140inputFieldColorsITpI4ow;
            final Function2<? super Composer, ? super Integer, Unit> function111110 = function7;
            final Function2<? super Composer, ? super Integer, Unit> function111111 = function5;
            final Function2<? super Composer, ? super Integer, Unit> function111112 = function6;
            int i21113 = i26 | 102236160 | (i19 & 112);
            int i21114 = i19 >> 9;
            composer2 = composerStartRestartGroup;
            BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default14, z115, false, textStyleMerge14, keyboardOptions14, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource18, (Brush) solidColor14, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function111113, Composer composer4, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function111113, composer4, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> function111113, Composer composer4, int i21115) {
                    Function2<? super Composer, ? super Integer, Unit> function111114;
                    int i30;
                    ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                    if ((i21115 & 6) == 0) {
                        function111114 = function111113;
                        i30 = i21115 | (composer4.changedInstance(function111114) ? 4 : 2);
                    } else {
                        function111114 = function111113;
                        i30 = i21115;
                    }
                    if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                        }
                        TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                        String str2 = str;
                        int i31 = i30;
                        boolean z116 = z115;
                        VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                        MutableInteractionSource mutableInteractionSource19 = mutableInteractionSource18;
                        Function2<Composer, Integer, Unit> function111115 = function111110;
                        final Function2<? super Composer, ? super Integer, Unit> function111116 = function111111;
                        composer4.startReplaceGroup(-1102017390);
                        ComposerKt.sourceInformation(composer4, "*551@26196L64");
                        ComposableLambda composableLambdaRememberComposableLambda = function111116 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                invoke(composer5, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer5, int i32) {
                                ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                    composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                }
                                Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                Function2<Composer, Integer, Unit> function111117 = function111116;
                                ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer5.startReusableNode();
                                if (composer5.getInserting()) {
                                    composer5.createNode(constructor);
                                } else {
                                    composer5.useNode();
                                }
                                Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                function111117.invoke(composer5, 0);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer4, 54);
                        composer4.endReplaceGroup();
                        final Function2<? super Composer, ? super Integer, Unit> function111117 = function111112;
                        composer4.startReplaceGroup(-1102010155);
                        ComposerKt.sourceInformation(composer4, "*555@26423L66");
                        ComposableLambda composableLambdaRememberComposableLambda2 = function111117 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                invoke(composer5, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer5, int i32) {
                                ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                    composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                }
                                Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                Function2<Composer, Integer, Unit> function111118 = function111117;
                                ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer5.startReusableNode();
                                if (composer5.getInserting()) {
                                    composer5.createNode(constructor);
                                } else {
                                    composer5.useNode();
                                }
                                Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                function111118.invoke(composer5, 0);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer4, 54) : null;
                        composer4.endReplaceGroup();
                        textFieldDefaults.DecorationBox(str2, function111114, z116, true, none, mutableInteractionSource19, false, null, function111115, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors16, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer4.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composer2, i21113 | (i21114 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
            if (z) {
                z10 = false;
            } else {
                z10 = false;
            }
            Boolean boolValueOf14 = Boolean.valueOf(z);
            ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
            zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
            objRememberedValue5 = composer2.rememberedValue();
            if (!zChanged2) {
                objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                composer2.updateRememberedValue(objRememberedValue5);
            } else {
                objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                composer2.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            EffectsKt.LaunchedEffect(boolValueOf14, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i21114 & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer3 = composer2;
            mutableInteractionSource4 = mutableInteractionSource2;
            modifier3 = modifier18;
            textFieldColors2 = textFieldColors16;
            function8 = function7;
            function9 = function5;
            function10 = function6;
            z11 = z115;
        } else {
            composerStartRestartGroup.startDefaults();
            i23 = i21;
            if ((i & 1) != 0) {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i10 != 0) {
                    z4 = true;
                } else {
                    z4 = z3;
                }
                if (i12 != 0) {
                    function7 = null;
                } else {
                    function7 = function7;
                }
                if (i14 != 0) {
                    function5 = null;
                } else {
                    function5 = function5;
                }
                if (i16 != 0) {
                    function6 = null;
                } else {
                    function6 = function6;
                }
                if ((i3 & 1024) != 0) {
                    i24 = i20;
                    i19 = i19;
                    textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                    i25 = i23 & (-15);
                } else {
                    i19 = i19;
                    i24 = i20;
                    textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                    i25 = i23;
                }
                if (i24 != 0) {
                    composerStartRestartGroup = composerStartRestartGroup;
                    z3 = z4;
                    mutableInteractionSource2 = null;
                } else {
                    composerStartRestartGroup = composerStartRestartGroup;
                    mutableInteractionSource2 = mutableInteractionSource;
                    z3 = z4;
                }
            } else {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i10 != 0) {
                    z4 = true;
                } else {
                    z4 = z3;
                }
                if (i12 != 0) {
                    function7 = null;
                } else {
                    function7 = function7;
                }
                if (i14 != 0) {
                    function5 = null;
                } else {
                    function5 = function5;
                }
                if (i16 != 0) {
                    function6 = null;
                } else {
                    function6 = function6;
                }
                if ((i3 & 1024) != 0) {
                    i24 = i20;
                    i19 = i19;
                    textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, (i23 << 6) & 57344, 16383);
                    i25 = i23 & (-15);
                } else {
                    i19 = i19;
                    i24 = i20;
                    textFieldColorsM2140inputFieldColorsITpI4ow = textFieldColors;
                    i25 = i23;
                }
                if (i24 != 0) {
                    composerStartRestartGroup = composerStartRestartGroup;
                    z3 = z4;
                    mutableInteractionSource2 = null;
                } else {
                    composerStartRestartGroup = composerStartRestartGroup;
                    mutableInteractionSource2 = mutableInteractionSource;
                    z3 = z4;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1451366815, i19, i25, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.android.kt:494)");
            }
            composerStartRestartGroup.startReplaceGroup(-320443616);
            ComposerKt.sourceInformation(composerStartRestartGroup, "496@23695L39");
            if (mutableInteractionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320442965, "CC(remember):SearchBar.android.kt#9igjgp");
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue6;
            } else {
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            composerStartRestartGroup.endReplaceGroup();
            zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 0).getValue().booleanValue();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320438431, "CC(remember):SearchBar.android.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new FocusRequester();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            focusRequester = (FocusRequester) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<FocusManager> localFocusManager15 = CompositionLocalsKt.getLocalFocusManager();
            Modifier modifier19 = companion;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume11113 = composerStartRestartGroup.consume(localFocusManager15);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            focusManager = (FocusManager) objConsume11113;
            Strings.Companion companion1110 = Strings.INSTANCE;
            strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_search_bar_search), composerStartRestartGroup, 0);
            Strings.Companion companion1111 = Strings.INSTANCE;
            strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_suggestions_available), composerStartRestartGroup, 0);
            ProvidableCompositionLocal<TextStyle> localTextStyle119 = TextKt.getLocalTextStyle();
            final MutableInteractionSource mutableInteractionSource19 = mutableInteractionSource3;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume11114 = composerStartRestartGroup.consume(localTextStyle119);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            jM6374getColor0d7_KjU = ((TextStyle) objConsume11114).m6374getColor0d7_KjU();
            if (jM6374getColor0d7_KjU == 16) {
                jM6374getColor0d7_KjU = textFieldColorsM2140inputFieldColorsITpI4ow.m2380textColorXeAY9LY$material3_release(z3, false, zBooleanValue);
            }
            long j15 = jM6374getColor0d7_KjU;
            Modifier modifierFocusRequester15 = FocusRequesterModifierKt.focusRequester(SizeKt.m769sizeInqDBjuR0$default(modifier19, SearchBar_androidKt.getSearchBarMinWidth(), InputFieldHeight, SearchBar_androidKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320411280, "CC(remember):SearchBar.android.kt#9igjgp");
            final boolean z116 = z3;
            if ((57344 & i19) == 16384) {
                z5 = true;
            } else {
                z5 = false;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z5) {
                objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                        invoke2(focusState);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FocusState focusState) {
                        if (focusState.isFocused()) {
                            function3.invoke(true);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                        invoke2(focusState);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FocusState focusState) {
                        if (focusState.isFocused()) {
                            function3.invoke(true);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierOnFocusChanged15 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester15, (Function1) objRememberedValue2);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320408514, "CC(remember):SearchBar.android.kt#9igjgp");
            boolean zChanged17 = composerStartRestartGroup.changed(strM2723getString2EP1pXo);
            if ((i19 & 7168) == 2048) {
                z6 = true;
            } else {
                z6 = false;
            }
            zChanged = z6 | zChanged17 | composerStartRestartGroup.changed(strM2723getString2EP1pXo2);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                        if (z) {
                            SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                        }
                        final FocusRequester focusRequester2 = focusRequester;
                        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                focusRequester2.requestFocus();
                                return true;
                            }
                        }, 1, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1
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
                        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo);
                        if (z) {
                            SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, strM2723getString2EP1pXo2);
                        }
                        final FocusRequester focusRequester2 = focusRequester;
                        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$2$1.1
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                focusRequester2.requestFocus();
                                return true;
                            }
                        }, 1, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierSemantics$default15 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged15, false, (Function1) objRememberedValue3, 1, null);
            ProvidableCompositionLocal<TextStyle> localTextStyle1110 = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume11115 = composerStartRestartGroup.consume(localTextStyle1110);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            TextStyle textStyleMerge15 = ((TextStyle) objConsume11115).merge(new TextStyle(j15, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            SolidColor solidColor15 = new SolidColor(textFieldColorsM2140inputFieldColorsITpI4ow.m2330cursorColorvNxB06k$material3_release(false), null);
            KeyboardOptions keyboardOptions15 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m6532getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -320385353, "CC(remember):SearchBar.android.kt#9igjgp");
            if ((i19 & 896) == 256) {
                z7 = true;
            } else {
                z7 = false;
            }
            i26 = i19 & 14;
            if (i26 == 4) {
                z8 = true;
            } else {
                z8 = false;
            }
            z9 = z7 | z8;
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (!z9) {
                objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                        invoke2(keyboardActionScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyboardActionScope keyboardActionScope) {
                        function2.invoke(str);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                objRememberedValue4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                        invoke2(keyboardActionScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KeyboardActionScope keyboardActionScope) {
                        function2.invoke(str);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final TextFieldColors textFieldColors17 = textFieldColorsM2140inputFieldColorsITpI4ow;
            final Function2<? super Composer, ? super Integer, Unit> function111113 = function7;
            final Function2<? super Composer, ? super Integer, Unit> function111114 = function5;
            final Function2<? super Composer, ? super Integer, Unit> function111115 = function6;
            int i21115 = i26 | 102236160 | (i19 & 112);
            int i21116 = i19 >> 9;
            composer2 = composerStartRestartGroup;
            BasicTextFieldKt.BasicTextField(str, function1, modifierSemantics$default15, z116, false, textStyleMerge15, keyboardOptions15, new KeyboardActions(null, null, null, null, (Function1) objRememberedValue4, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource19, (Brush) solidColor15, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2029278807, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function111116, Composer composer4, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function111116, composer4, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> function111116, Composer composer4, int i21117) {
                    Function2<? super Composer, ? super Integer, Unit> function111117;
                    int i30;
                    ComposerKt.sourceInformation(composer4, "C557@26571L15,541@25683L1096:SearchBar.android.kt#uh7d8r");
                    if ((i21117 & 6) == 0) {
                        function111117 = function111116;
                        i30 = i21117 | (composer4.changedInstance(function111117) ? 4 : 2);
                    } else {
                        function111117 = function111116;
                        i30 = i21117;
                    }
                    if ((i30 & 19) != 18 || !composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2029278807, i30, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.android.kt:541)");
                        }
                        TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                        String str2 = str;
                        int i31 = i30;
                        boolean z117 = z116;
                        VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                        MutableInteractionSource mutableInteractionSource110 = mutableInteractionSource19;
                        Function2<Composer, Integer, Unit> function111118 = function111113;
                        final Function2<? super Composer, ? super Integer, Unit> function111119 = function111114;
                        composer4.startReplaceGroup(-1102017390);
                        ComposerKt.sourceInformation(composer4, "*551@26196L64");
                        ComposableLambda composableLambdaRememberComposableLambda = function111119 == null ? null : ComposableLambdaKt.rememberComposableLambda(-1401341985, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                invoke(composer5, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer5, int i32) {
                                ComposerKt.sourceInformation(composer5, "C551@26198L60:SearchBar.android.kt#uh7d8r");
                                if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                    composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1401341985, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:551)");
                                }
                                Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, SearchBar_androidKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                Function2<Composer, Integer, Unit> function1111110 = function111119;
                                ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer5.startReusableNode();
                                if (composer5.getInserting()) {
                                    composer5.createNode(constructor);
                                } else {
                                    composer5.useNode();
                                }
                                Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer5, -127763558, "C551@26247L9:SearchBar.android.kt#uh7d8r");
                                function1111110.invoke(composer5, 0);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer4, 54);
                        composer4.endReplaceGroup();
                        final Function2<? super Composer, ? super Integer, Unit> function1111110 = function111115;
                        composer4.startReplaceGroup(-1102010155);
                        ComposerKt.sourceInformation(composer4, "*555@26423L66");
                        ComposableLambda composableLambdaRememberComposableLambda2 = function1111110 != null ? ComposableLambdaKt.rememberComposableLambda(907752083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$4$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                invoke(composer5, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer5, int i32) {
                                ComposerKt.sourceInformation(composer5, "C555@26425L62:SearchBar.android.kt#uh7d8r");
                                if ((i32 & 3) == 2 && composer5.getSkipping()) {
                                    composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(907752083, i32, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:555)");
                                }
                                Modifier modifierM681offsetVpY3zN4$default = OffsetKt.m681offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m6890constructorimpl(-SearchBar_androidKt.SearchBarIconOffsetX), 0.0f, 2, null);
                                Function2<Composer, Integer, Unit> function1111111 = function1111110;
                                ComposerKt.sourceInformationMarkerStart(composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierM681offsetVpY3zN4$default);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer5.startReusableNode();
                                if (composer5.getInserting()) {
                                    composer5.createNode(constructor);
                                } else {
                                    composer5.useNode();
                                }
                                Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer5);
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer5, -127537351, "C555@26475L10:SearchBar.android.kt#uh7d8r");
                                function1111111.invoke(composer5, 0);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer4, 54) : null;
                        composer4.endReplaceGroup();
                        textFieldDefaults.DecorationBox(str2, function111117, z117, true, none, mutableInteractionSource110, false, null, function111118, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer4, 6), textFieldColors17, TextFieldDefaults.m2383contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1695getLambda1$material3_release(), composer4, ((i31 << 3) & 112) | 27648, 113246208, 14528);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer4.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composer2, i21115 | (i21116 & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
            if (z) {
                z10 = false;
            } else {
                z10 = false;
            }
            Boolean boolValueOf15 = Boolean.valueOf(z);
            ComposerKt.sourceInformationMarkerStart(composer2, -320340316, "CC(remember):SearchBar.android.kt#9igjgp");
            zChanged2 = composer2.changed(z10) | composer2.changedInstance(focusManager);
            objRememberedValue5 = composer2.rememberedValue();
            if (!zChanged2) {
                objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                composer2.updateRememberedValue(objRememberedValue5);
            } else {
                objRememberedValue5 = (Function2) new SearchBarDefaults$InputField$5$1(z10, focusManager, null);
                composer2.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            EffectsKt.LaunchedEffect(boolValueOf15, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, i21116 & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer3 = composer2;
            mutableInteractionSource4 = mutableInteractionSource2;
            modifier3 = modifier19;
            textFieldColors2 = textFieldColors17;
            function8 = function7;
            function9 = function5;
            function10 = function6;
            z11 = z116;
        }
        scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                    invoke(composer4, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer4, int i21117) {
                    SearchBarDefaults.this.InputField(str, function1, function2, z, function3, modifier3, z11, function8, function9, function10, textFieldColors2, mutableInteractionSource4, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                }
            });
        }
    }

    @Deprecated(message = "Search bars now take the input field as a parameter. `inputFieldColors` should be passed explicitly to the input field. This parameter will be removed in a future version of the library.", replaceWith = @ReplaceWith(expression = "colors(containerColor, dividerColor)", imports = {}))
    /* JADX INFO: renamed from: colors-Klgx-Pg, reason: not valid java name */
    public final SearchBarColors m2133colorsKlgxPg(long j, long j2, TextFieldColors textFieldColors, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -1216168196, "C(colors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color)586@27706L5,587@27773L5,588@27824L18:SearchBar.android.kt#uh7d8r");
        long value = (i2 & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getContainerColor(), composer, 6) : j;
        long value2 = (i2 & 2) != 0 ? ColorSchemeKt.getValue(SearchViewTokens.INSTANCE.getDividerColor(), composer, 6) : j2;
        TextFieldColors textFieldColorsM2140inputFieldColorsITpI4ow = (i2 & 4) != 0 ? m2140inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 0, (i << 3) & 57344, 16383) : textFieldColors;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1216168196, i, -1, "androidx.compose.material3.SearchBarDefaults.colors (SearchBar.android.kt:590)");
        }
        SearchBarColors searchBarColors = new SearchBarColors(value, value2, textFieldColorsM2140inputFieldColorsITpI4ow, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return searchBarColors;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: inputFieldColors--u-KgnY, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m2139inputFieldColorsuKgnY(long j, long j2, long j3, TextSelectionColors textSelectionColors, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, Composer composer, int i, int i2, int i3) {
        TextSelectionColors textSelectionColors2;
        ComposerKt.sourceInformationMarkerStart(composer, 355927049, "C(inputFieldColors)P(9:c#ui.graphics.Color,3:c#ui.graphics.Color,0:c#ui.graphics.Color,8,5:c#ui.graphics.Color,10:c#ui.graphics.Color,1:c#ui.graphics.Color,6:c#ui.graphics.Color,11:c#ui.graphics.Color,4:c#ui.graphics.Color,7:c#ui.graphics.Color,2:c#ui.graphics.Color)599@28228L5,601@28323L5,604@28479L5,605@28558L7,606@28641L5,607@28724L5,609@28832L5,612@29008L5,613@29093L5,615@29203L5,618@29374L5,620@29476L5,624@29586L825:SearchBar.android.kt#uh7d8r");
        long value = (i3 & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), composer, 6) : j;
        long jM4151copywmQWz5c$default = (i3 & 2) != 0 ? Color.m4151copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), 0.0f, 0.0f, 0.0f, 14, null) : j2;
        long value2 = (i3 & 4) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getCaretColor(), composer, 6) : j3;
        if ((i3 & 8) != 0) {
            ProvidableCompositionLocal<TextSelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd(composer);
            textSelectionColors2 = (TextSelectionColors) objConsume;
        } else {
            textSelectionColors2 = textSelectionColors;
        }
        long value3 = (i3 & 16) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), composer, 6) : j4;
        long value4 = (i3 & 32) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), composer, 6) : j5;
        long jM4151copywmQWz5c$default2 = (i3 & 64) != 0 ? Color.m4151copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), composer, 6), FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null) : j6;
        long value5 = (i3 & 128) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), composer, 6) : j7;
        long value6 = (i3 & 256) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), composer, 6) : j8;
        long jM4151copywmQWz5c$default3 = (i3 & 512) != 0 ? Color.m4151copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), composer, 6), FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null) : j9;
        long value7 = (i3 & 1024) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), composer, 6) : j10;
        long jM4151copywmQWz5c$default4 = (i3 & 2048) != 0 ? Color.m4151copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), 0.0f, 0.0f, 0.0f, 14, null) : j11;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(355927049, i, i2, "androidx.compose.material3.SearchBarDefaults.inputFieldColors (SearchBar.android.kt:624)");
        }
        int i4 = i << 3;
        int i5 = i2 << 6;
        int i6 = ((i >> 27) & 14) | ((i2 << 3) & 112) | (i5 & 896) | (i5 & 7168) | (i5 & 57344);
        long j12 = value;
        long j13 = value2;
        long j14 = value3;
        long j15 = value4;
        long j16 = jM4151copywmQWz5c$default2;
        long j17 = value6;
        long j18 = value7;
        TextFieldColors textFieldColorsM2140inputFieldColorsITpI4ow = m2140inputFieldColorsITpI4ow(j12, j12, jM4151copywmQWz5c$default, j13, textSelectionColors2, j14, j15, j16, value5, j17, jM4151copywmQWz5c$default3, j18, j18, jM4151copywmQWz5c$default4, composer, (i & 14) | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (i4 & 57344) | (i4 & 458752) | (i4 & 3670016) | (i4 & 29360128) | (i4 & 234881024) | (i4 & 1879048192), i6, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return textFieldColorsM2140inputFieldColorsITpI4ow;
    }
}
