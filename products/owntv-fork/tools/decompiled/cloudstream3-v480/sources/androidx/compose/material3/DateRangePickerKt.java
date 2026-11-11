package androidx.compose.material3;

import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.CalendarModel_androidKt;
import androidx.compose.material3.internal.CalendarMonth;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DateRangePicker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¼\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001ak\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\n\u0018\u00010\u0012¢\u0006\u0002\b\u00132\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\n\u0018\u00010\u0012¢\u0006\u0002\b\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a°\u0001\u0010\u001a\u001a\u00020\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u001c2:\u0010\u001f\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\n0 2!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\n0&2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0017\u001a\u00020\u0018H\u0003¢\u0006\u0002\u0010.\u001a`\u0010/\u001a\u00020\f2\n\u00100\u001a\u000601j\u0002`22\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u0010,\u001a\u00020-H\u0007ø\u0001\u0000¢\u0006\u0004\b8\u00109\u001a½\u0001\u0010:\u001a\u00020\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010;\u001a\u0002072:\u0010\u001f\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\n0 2!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\n0&2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0017\u001a\u00020\u0018H\u0003ø\u0001\u0000¢\u0006\u0004\b<\u0010=\u001a°\u0001\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020@2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2:\u0010\u001f\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\n0 2!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\n0&2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0017\u001a\u00020\u0018H\u0003¢\u0006\u0002\u0010A\u001a.\u0010B\u001a\b\u0012\u0004\u0012\u00020D0C2\u0006\u0010\u000b\u001a\u00020@2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020HH\u0002\u001aT\u0010J\u001a\u00020\f2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u0010,\u001a\u00020-H\u0007ø\u0001\u0000¢\u0006\u0004\bK\u0010L\u001ae\u0010M\u001a\u00020\n2\u0006\u0010N\u001a\u00020\u001c2\b\u0010O\u001a\u0004\u0018\u00010\u001c2\b\u0010P\u001a\u0004\u0018\u00010\u001c2:\u0010\u001f\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\n0 H\u0002¢\u0006\u0002\u0010Q\u001a&\u0010R\u001a\u00020\n*\u00020S2\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020WH\u0000ø\u0001\u0000¢\u0006\u0004\bX\u0010Y\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006Z"}, d2 = {"CalendarMonthSubheadPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getCalendarMonthSubheadPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "DateRangePickerHeadlinePadding", "DateRangePickerTitlePadding", "HeaderHeightOffset", "Landroidx/compose/ui/unit/Dp;", "F", "DateRangePicker", "", "state", "Landroidx/compose/material3/DateRangePickerState;", "modifier", "Landroidx/compose/ui/Modifier;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "title", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "headline", "showModeToggle", "", "colors", "Landroidx/compose/material3/DatePickerColors;", "(Landroidx/compose/material3/DateRangePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DatePickerFormatter;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;II)V", "DateRangePickerContent", "selectedStartDateMillis", "", "selectedEndDateMillis", "displayedMonthMillis", "onDatesSelectionChange", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "startDateMillis", "endDateMillis", "onDisplayedMonthChange", "Lkotlin/Function1;", "monthInMillis", "calendarModel", "Landroidx/compose/material3/internal/CalendarModel;", "yearRange", "Lkotlin/ranges/IntRange;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "(Ljava/lang/Long;Ljava/lang/Long;JLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "DateRangePickerState", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "initialSelectedStartDateMillis", "initialSelectedEndDateMillis", "initialDisplayedMonthMillis", "initialDisplayMode", "Landroidx/compose/material3/DisplayMode;", "DateRangePickerState-HVP43zI", "(Ljava/util/Locale;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;)Landroidx/compose/material3/DateRangePickerState;", "SwitchableDateEntryContent", "displayMode", "SwitchableDateEntryContent-RN-2D1Q", "(Ljava/lang/Long;Ljava/lang/Long;JILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;II)V", "VerticalMonthsList", "lazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "(Landroidx/compose/foundation/lazy/LazyListState;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "customScrollActions", "", "Landroidx/compose/ui/semantics/CustomAccessibilityAction;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "scrollUpLabel", "", "scrollDownLabel", "rememberDateRangePickerState", "rememberDateRangePickerState-IlFM19s", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DateRangePickerState;", "updateDateSelection", "dateInMillis", "currentStartDateMillis", "currentEndDateMillis", "(JLjava/lang/Long;Ljava/lang/Long;Lkotlin/jvm/functions/Function2;)V", "drawRangeBackground", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "selectedRangeInfo", "Landroidx/compose/material3/SelectedRangeInfo;", "color", "Landroidx/compose/ui/graphics/Color;", "drawRangeBackground-mxwnekA", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;Landroidx/compose/material3/SelectedRangeInfo;J)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DateRangePickerKt {
    private static final PaddingValues CalendarMonthSubheadPadding = PaddingKt.m717PaddingValuesa9UjIt4$default(Dp.m6890constructorimpl(24.0f), Dp.m6890constructorimpl(20.0f), 0.0f, Dp.m6890constructorimpl(8.0f), 4, null);
    private static final PaddingValues DateRangePickerTitlePadding = PaddingKt.m717PaddingValuesa9UjIt4$default(Dp.m6890constructorimpl(64.0f), 0.0f, Dp.m6890constructorimpl(12.0f), 0.0f, 10, null);
    private static final PaddingValues DateRangePickerHeadlinePadding = PaddingKt.m717PaddingValuesa9UjIt4$default(Dp.m6890constructorimpl(64.0f), 0.0f, Dp.m6890constructorimpl(12.0f), Dp.m6890constructorimpl(12.0f), 2, null);
    private static final float HeaderHeightOffset = Dp.m6890constructorimpl(60.0f);

    /* JADX WARN: Code duplicated, block: B:101:0x011d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:102:0x011f  */
    /* JADX WARN: Code duplicated, block: B:105:0x0128  */
    /* JADX WARN: Code duplicated, block: B:107:0x013a  */
    /* JADX WARN: Code duplicated, block: B:109:0x0155  */
    /* JADX WARN: Code duplicated, block: B:111:0x0159  */
    /* JADX WARN: Code duplicated, block: B:112:0x016f  */
    /* JADX WARN: Code duplicated, block: B:114:0x0173  */
    /* JADX WARN: Code duplicated, block: B:116:0x0185  */
    /* JADX WARN: Code duplicated, block: B:119:0x018a  */
    /* JADX WARN: Code duplicated, block: B:122:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:125:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:127:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:130:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:131:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:134:0x0244  */
    /* JADX WARN: Code duplicated, block: B:138:0x0254  */
    /* JADX WARN: Code duplicated, block: B:140:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004b  */
    /* JADX WARN: Code duplicated, block: B:28:0x004f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0053  */
    /* JADX WARN: Code duplicated, block: B:31:0x0058  */
    /* JADX WARN: Code duplicated, block: B:33:0x005e  */
    /* JADX WARN: Code duplicated, block: B:34:0x0061  */
    /* JADX WARN: Code duplicated, block: B:38:0x0068  */
    /* JADX WARN: Code duplicated, block: B:39:0x006b  */
    /* JADX WARN: Code duplicated, block: B:41:0x006f  */
    /* JADX WARN: Code duplicated, block: B:43:0x0077  */
    /* JADX WARN: Code duplicated, block: B:44:0x007a  */
    /* JADX WARN: Code duplicated, block: B:49:0x0084  */
    /* JADX WARN: Code duplicated, block: B:50:0x0087  */
    /* JADX WARN: Code duplicated, block: B:52:0x008b  */
    /* JADX WARN: Code duplicated, block: B:54:0x0093  */
    /* JADX WARN: Code duplicated, block: B:55:0x0096  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:65:0x00af  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:76:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:82:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:89:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:99:0x0116 A[PHI: r2 r4 r6 r9 r11 r13
      0x0116: PHI (r2v15 androidx.compose.material3.DatePickerFormatter) = (r2v5 androidx.compose.material3.DatePickerFormatter), (r2v19 androidx.compose.material3.DatePickerFormatter) binds: [B:118:0x0188, B:98:0x0114] A[DONT_GENERATE, DONT_INLINE]
      0x0116: PHI (r4v26 int) = (r4v20 int), (r4v29 int) binds: [B:118:0x0188, B:98:0x0114] A[DONT_GENERATE, DONT_INLINE]
      0x0116: PHI (r6v18 androidx.compose.ui.Modifier) = (r6v3 androidx.compose.ui.Modifier), (r6v2 androidx.compose.ui.Modifier) binds: [B:118:0x0188, B:98:0x0114] A[DONT_GENERATE, DONT_INLINE]
      0x0116: PHI (r9v7 kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>) = 
      (r9v3 kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>)
      (r9v2 kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>)
     binds: [B:118:0x0188, B:98:0x0114] A[DONT_GENERATE, DONT_INLINE]
      0x0116: PHI (r11v6 kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>) = 
      (r11v3 kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>)
      (r11v2 kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>)
     binds: [B:118:0x0188, B:98:0x0114] A[DONT_GENERATE, DONT_INLINE]
      0x0116: PHI (r13v8 boolean) = (r13v4 boolean), (r13v3 boolean) binds: [B:118:0x0188, B:98:0x0114] A[DONT_GENERATE, DONT_INLINE]] */
    public static final void DateRangePicker(final DateRangePickerState dateRangePickerState, Modifier modifier, DatePickerFormatter datePickerFormatter, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, boolean z, DatePickerColors datePickerColors, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda;
        int i5;
        int i6;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda2;
        int i7;
        int i8;
        boolean z2;
        int i9;
        DatePickerColors datePickerColors2;
        final DatePickerFormatter datePickerFormatter2;
        int i10;
        int i11;
        boolean z3;
        final DatePickerColors datePickerColorsColors;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Object objRememberedValue;
        Locale localeDefaultLocale;
        boolean zChanged;
        Object objRememberedValue2;
        ComposableLambda composableLambdaRememberComposableLambda;
        Composer composer2;
        final DatePickerFormatter datePickerFormatter3;
        final boolean z4;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        final DatePickerColors datePickerColors3;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i12;
        boolean zChangedInstance;
        Composer composerStartRestartGroup = composer.startRestartGroup(650830774);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DateRangePicker)P(5,3,1,6,2,4)95@4412L47,96@4500L185,102@4729L352,112@5169L8,114@5206L15,115@5246L62,132@5955L5,136@6111L1139,116@5313L1937:DateRangePicker.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(dateRangePickerState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i13 = i2 & 2;
        if (i13 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if ((i2 & 4) != 0) {
                    i12 = 128;
                } else {
                    if ((i & 512) == 0) {
                        zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
                    } else {
                        zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
                    }
                    if (zChangedInstance) {
                        i12 = 256;
                    } else {
                        i12 = 128;
                    }
                }
                i3 |= i12;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    function2RememberComposableLambda = function2;
                    if (composerStartRestartGroup.changedInstance(function2RememberComposableLambda)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 16;
                if (i6 != 0) {
                    if ((i & 24576) == 0) {
                        function2RememberComposableLambda2 = function3;
                        if (composerStartRestartGroup.changedInstance(function2RememberComposableLambda2)) {
                            i7 = 16384;
                        } else {
                            i7 = 8192;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 32;
                    if (i8 != 0) {
                        if ((196608 & i) == 0) {
                            z2 = z;
                            if (composerStartRestartGroup.changed(z2)) {
                                i9 = 131072;
                            } else {
                                i9 = 65536;
                            }
                            i3 |= i9;
                        }
                        if ((1572864 & i) == 0) {
                            if ((i2 & 64) == 0) {
                                datePickerColors2 = datePickerColors;
                                int i14 = composerStartRestartGroup.changed(datePickerColors2) ? 1048576 : 524288;
                                i3 |= i14;
                            } else {
                                datePickerColors2 = datePickerColors;
                            }
                            i3 |= i14;
                        } else {
                            datePickerColors2 = datePickerColors;
                        }
                        if ((599187 & i3) == 599186 || !composerStartRestartGroup.getSkipping()) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 4) != 0) {
                                    i3 &= -897;
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                }
                                datePickerFormatter2 = datePickerFormatter;
                            } else {
                                if (i13 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i2 & 4) != 0) {
                                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                    }
                                    datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                    i3 &= -897;
                                } else {
                                    datePickerFormatter2 = datePickerFormatter;
                                }
                                if (i4 != 0) {
                                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                            invoke(composer3, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer3, int i15) {
                                            ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                            if ((i15 & 3) != 2 || !composer3.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-162164694, i15, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                                }
                                                DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer3.skipToGroupEnd();
                                        }
                                    }, composerStartRestartGroup, 54);
                                    i10 = 54;
                                } else {
                                    i10 = 54;
                                }
                                if (i6 != 0) {
                                    function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
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
                                            ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                            if ((i15 & 3) != 2 || !composer3.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-185279404, i15, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                                }
                                                DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer3.skipToGroupEnd();
                                        }
                                    }, composerStartRestartGroup, i10);
                                }
                                if (i8 != 0) {
                                    z2 = true;
                                }
                                if ((i2 & 64) != 0) {
                                    i11 = i3 & (-3670017);
                                    z3 = z2;
                                    datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    function4 = function2RememberComposableLambda;
                                }
                                Modifier modifier4 = modifier2;
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                                }
                                localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                                zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (!zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                final CalendarModel calendarModel = (CalendarModel) objRememberedValue2;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                composerStartRestartGroup.startReplaceGroup(-1454747621);
                                ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                                if (z3) {
                                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                            invoke(composer3, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer3, int i15) {
                                            ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                            if ((i15 & 3) != 2 || !composer3.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1490010652, i15, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                                }
                                                Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                                ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                                Object objRememberedValue3 = composer3.rememberedValue();
                                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                                    objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                                        {
                                                            super(1);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function1
                                                        public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                            m1766invokevCnGnXg(displayMode.getValue());
                                                            return Unit.INSTANCE;
                                                        }

                                                        /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                                        public final void m1766invokevCnGnXg(int i16) {
                                                            dateRangePickerState2.mo1769setDisplayModevCnGnXg(i16);
                                                        }
                                                    };
                                                    composer3.updateRememberedValue(objRememberedValue3);
                                                }
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer3.skipToGroupEnd();
                                        }
                                    }, composerStartRestartGroup, 54);
                                } else {
                                    composableLambdaRememberComposableLambda = null;
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                int i15 = i11 >> 6;
                                composer2 = composerStartRestartGroup;
                                DatePickerKt.m1743DateEntryContainerau3_HiA(modifier4, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i16) {
                                        ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                                        if ((i16 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-57534331, i16, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                            }
                                            Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                            Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                            long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                            int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                            ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                            boolean zChanged2 = composer3.changed(dateRangePickerState);
                                            final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                            Object objRememberedValue3 = composer3.rememberedValue();
                                            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                                objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                                    {
                                                        super(2);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                                        invoke2(l, l2);
                                                        return Unit.INSTANCE;
                                                    }

                                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                    public final void invoke2(Long l, Long l2) {
                                                        try {
                                                            dateRangePickerState2.setSelection(l, l2);
                                                        } catch (IllegalArgumentException unused) {
                                                        }
                                                    }
                                                };
                                                composer3.updateRememberedValue(objRememberedValue3);
                                            }
                                            Function2 function7 = (Function2) objRememberedValue3;
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                            boolean zChanged3 = composer3.changed(dateRangePickerState);
                                            final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                            Object objRememberedValue4 = composer3.rememberedValue();
                                            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                                objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                                    {
                                                        super(1);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function1
                                                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                                        invoke(l.longValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(long j) {
                                                        dateRangePickerState3.setDisplayedMonthMillis(j);
                                                    }
                                                };
                                                composer3.updateRememberedValue(objRememberedValue4);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i15 & 112) | (i15 & 896) | (i15 & 57344));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                datePickerFormatter3 = datePickerFormatter2;
                                z4 = z3;
                                modifier3 = modifier4;
                                function5 = function4;
                                datePickerColors3 = datePickerColorsColors;
                            }
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                            Modifier modifier5 = modifier2;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                            }
                            localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                            zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!zChanged) {
                                objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            final CalendarModel calendarModel2 = (CalendarModel) objRememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            composerStartRestartGroup.startReplaceGroup(-1454747621);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                            if (z3) {
                                composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i16) {
                                        ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                        if ((i16 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1490010652, i16, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                            }
                                            Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                            int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                            ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                            boolean zChanged2 = composer3.changed(dateRangePickerState);
                                            final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                            Object objRememberedValue3 = composer3.rememberedValue();
                                            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                                objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                                    {
                                                        super(1);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function1
                                                    public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                        m1766invokevCnGnXg(displayMode.getValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                                    public final void m1766invokevCnGnXg(int i17) {
                                                        dateRangePickerState2.mo1769setDisplayModevCnGnXg(i17);
                                                    }
                                                };
                                                composer3.updateRememberedValue(objRememberedValue3);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                            } else {
                                composableLambdaRememberComposableLambda = null;
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            int i16 = i11 >> 6;
                            composer2 = composerStartRestartGroup;
                            DatePickerKt.m1743DateEntryContainerau3_HiA(modifier5, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i17) {
                                    ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                                    if ((i17 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-57534331, i17, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                        }
                                        Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                        Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                        long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                        int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                        boolean zChanged2 = composer3.changed(dateRangePickerState);
                                        final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                        Object objRememberedValue3 = composer3.rememberedValue();
                                        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                                    invoke2(l, l2);
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(Long l, Long l2) {
                                                    try {
                                                        dateRangePickerState2.setSelection(l, l2);
                                                    } catch (IllegalArgumentException unused) {
                                                    }
                                                }
                                            };
                                            composer3.updateRememberedValue(objRememberedValue3);
                                        }
                                        Function2 function7 = (Function2) objRememberedValue3;
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                        boolean zChanged3 = composer3.changed(dateRangePickerState);
                                        final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                        Object objRememberedValue4 = composer3.rememberedValue();
                                        if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                                    invoke(l.longValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(long j) {
                                                    dateRangePickerState3.setDisplayedMonthMillis(j);
                                                }
                                            };
                                            composer3.updateRememberedValue(objRememberedValue4);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel2, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i16 & 112) | (i16 & 896) | (i16 & 57344));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            datePickerFormatter3 = datePickerFormatter2;
                            z4 = z3;
                            modifier3 = modifier5;
                            function5 = function4;
                            datePickerColors3 = datePickerColorsColors;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            composer2 = composerStartRestartGroup;
                            modifier3 = modifier2;
                            function5 = function2RememberComposableLambda;
                            z4 = z2;
                            datePickerColors3 = datePickerColors2;
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        function6 = function2RememberComposableLambda2;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                                public final void invoke(Composer composer3, int i17) {
                                    DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                }
                            });
                        }
                    }
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    z2 = z;
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            datePickerColors2 = datePickerColors;
                            if (composerStartRestartGroup.changed(datePickerColors2)) {
                            }
                            i3 |= i14;
                        } else {
                            datePickerColors2 = datePickerColors;
                        }
                        i3 |= i14;
                    } else {
                        datePickerColors2 = datePickerColors;
                    }
                    if ((599187 & i3) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i17) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i17 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i17, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i17) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i17 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i17, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i17) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i17 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i17, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i17) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i17 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i17, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        }
                        Modifier modifier6 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                        }
                        localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final CalendarModel calendarModel3 = (CalendarModel) objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composerStartRestartGroup.startReplaceGroup(-1454747621);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                        if (z3) {
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i17) {
                                    ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                    if ((i17 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1490010652, i17, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                        }
                                        Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                        int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                        boolean zChanged2 = composer3.changed(dateRangePickerState);
                                        final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                        Object objRememberedValue3 = composer3.rememberedValue();
                                        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                    m1766invokevCnGnXg(displayMode.getValue());
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                                public final void m1766invokevCnGnXg(int i18) {
                                                    dateRangePickerState2.mo1769setDisplayModevCnGnXg(i18);
                                                }
                                            };
                                            composer3.updateRememberedValue(objRememberedValue3);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                        } else {
                            composableLambdaRememberComposableLambda = null;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i17 = i11 >> 6;
                        composer2 = composerStartRestartGroup;
                        DatePickerKt.m1743DateEntryContainerau3_HiA(modifier6, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i18) {
                                ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                                if ((i18 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-57534331, i18, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                    }
                                    Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                    Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                    long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                                invoke2(l, l2);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Long l, Long l2) {
                                                try {
                                                    dateRangePickerState2.setSelection(l, l2);
                                                } catch (IllegalArgumentException unused) {
                                                }
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    Function2 function7 = (Function2) objRememberedValue3;
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged3 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                    Object objRememberedValue4 = composer3.rememberedValue();
                                    if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                                invoke(l.longValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(long j) {
                                                dateRangePickerState3.setDisplayedMonthMillis(j);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue4);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel3, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i17 & 112) | (i17 & 896) | (i17 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter3 = datePickerFormatter2;
                        z4 = z3;
                        modifier3 = modifier6;
                        function5 = function4;
                        datePickerColors3 = datePickerColorsColors;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i18) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i18 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i18, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i18) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i18 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i18, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i18) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i18 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i18, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i18) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i18 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i18, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        }
                        Modifier modifier7 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                        }
                        localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final CalendarModel calendarModel4 = (CalendarModel) objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composerStartRestartGroup.startReplaceGroup(-1454747621);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                        if (z3) {
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i18) {
                                    ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                    if ((i18 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1490010652, i18, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                        }
                                        Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                        int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                        boolean zChanged2 = composer3.changed(dateRangePickerState);
                                        final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                        Object objRememberedValue3 = composer3.rememberedValue();
                                        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                    m1766invokevCnGnXg(displayMode.getValue());
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                                public final void m1766invokevCnGnXg(int i19) {
                                                    dateRangePickerState2.mo1769setDisplayModevCnGnXg(i19);
                                                }
                                            };
                                            composer3.updateRememberedValue(objRememberedValue3);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                        } else {
                            composableLambdaRememberComposableLambda = null;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i18 = i11 >> 6;
                        composer2 = composerStartRestartGroup;
                        DatePickerKt.m1743DateEntryContainerau3_HiA(modifier7, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i19) {
                                ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                                if ((i19 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-57534331, i19, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                    }
                                    Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                    Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                    long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                                invoke2(l, l2);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Long l, Long l2) {
                                                try {
                                                    dateRangePickerState2.setSelection(l, l2);
                                                } catch (IllegalArgumentException unused) {
                                                }
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    Function2 function7 = (Function2) objRememberedValue3;
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged3 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                    Object objRememberedValue4 = composer3.rememberedValue();
                                    if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                                invoke(l.longValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(long j) {
                                                dateRangePickerState3.setDisplayedMonthMillis(j);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue4);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel4, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i18 & 112) | (i18 & 896) | (i18 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter3 = datePickerFormatter2;
                        z4 = z3;
                        modifier3 = modifier7;
                        function5 = function4;
                        datePickerColors3 = datePickerColorsColors;
                    }
                    function6 = function2RememberComposableLambda2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                            public final void invoke(Composer composer3, int i19) {
                                DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 24576;
                function2RememberComposableLambda2 = function3;
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            datePickerColors2 = datePickerColors;
                            if (composerStartRestartGroup.changed(datePickerColors2)) {
                            }
                            i3 |= i14;
                        } else {
                            datePickerColors2 = datePickerColors;
                        }
                        i3 |= i14;
                    } else {
                        datePickerColors2 = datePickerColors;
                    }
                    if ((599187 & i3) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i19) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i19 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i19, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i19) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i19 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i19, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i19) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i19 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i19, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i19) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i19 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i19, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        }
                        Modifier modifier8 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                        }
                        localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final CalendarModel calendarModel5 = (CalendarModel) objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composerStartRestartGroup.startReplaceGroup(-1454747621);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                        if (z3) {
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i19) {
                                    ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                    if ((i19 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1490010652, i19, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                        }
                                        Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                        int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                        boolean zChanged2 = composer3.changed(dateRangePickerState);
                                        final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                        Object objRememberedValue3 = composer3.rememberedValue();
                                        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                    m1766invokevCnGnXg(displayMode.getValue());
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                                public final void m1766invokevCnGnXg(int i110) {
                                                    dateRangePickerState2.mo1769setDisplayModevCnGnXg(i110);
                                                }
                                            };
                                            composer3.updateRememberedValue(objRememberedValue3);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                        } else {
                            composableLambdaRememberComposableLambda = null;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i19 = i11 >> 6;
                        composer2 = composerStartRestartGroup;
                        DatePickerKt.m1743DateEntryContainerau3_HiA(modifier8, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i110) {
                                ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                                if ((i110 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-57534331, i110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                    }
                                    Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                    Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                    long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                                invoke2(l, l2);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Long l, Long l2) {
                                                try {
                                                    dateRangePickerState2.setSelection(l, l2);
                                                } catch (IllegalArgumentException unused) {
                                                }
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    Function2 function7 = (Function2) objRememberedValue3;
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged3 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                    Object objRememberedValue4 = composer3.rememberedValue();
                                    if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                                invoke(l.longValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(long j) {
                                                dateRangePickerState3.setDisplayedMonthMillis(j);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue4);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel5, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i19 & 112) | (i19 & 896) | (i19 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter3 = datePickerFormatter2;
                        z4 = z3;
                        modifier3 = modifier8;
                        function5 = function4;
                        datePickerColors3 = datePickerColorsColors;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i110) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i110 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i110) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i110 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i110) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i110 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i110) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i110 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        }
                        Modifier modifier9 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                        }
                        localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final CalendarModel calendarModel6 = (CalendarModel) objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composerStartRestartGroup.startReplaceGroup(-1454747621);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                        if (z3) {
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i110) {
                                    ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                    if ((i110 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1490010652, i110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                        }
                                        Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                        int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                        boolean zChanged2 = composer3.changed(dateRangePickerState);
                                        final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                        Object objRememberedValue3 = composer3.rememberedValue();
                                        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                    m1766invokevCnGnXg(displayMode.getValue());
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                                public final void m1766invokevCnGnXg(int i111) {
                                                    dateRangePickerState2.mo1769setDisplayModevCnGnXg(i111);
                                                }
                                            };
                                            composer3.updateRememberedValue(objRememberedValue3);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                        } else {
                            composableLambdaRememberComposableLambda = null;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i110 = i11 >> 6;
                        composer2 = composerStartRestartGroup;
                        DatePickerKt.m1743DateEntryContainerau3_HiA(modifier9, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i111) {
                                ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                                if ((i111 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-57534331, i111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                    }
                                    Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                    Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                    long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                                invoke2(l, l2);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Long l, Long l2) {
                                                try {
                                                    dateRangePickerState2.setSelection(l, l2);
                                                } catch (IllegalArgumentException unused) {
                                                }
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    Function2 function7 = (Function2) objRememberedValue3;
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged3 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                    Object objRememberedValue4 = composer3.rememberedValue();
                                    if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                                invoke(l.longValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(long j) {
                                                dateRangePickerState3.setDisplayedMonthMillis(j);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue4);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel6, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i110 & 112) | (i110 & 896) | (i110 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter3 = datePickerFormatter2;
                        z4 = z3;
                        modifier3 = modifier9;
                        function5 = function4;
                        datePickerColors3 = datePickerColorsColors;
                    }
                    function6 = function2RememberComposableLambda2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                            public final void invoke(Composer composer3, int i111) {
                                DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                z2 = z;
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        datePickerColors2 = datePickerColors;
                        if (composerStartRestartGroup.changed(datePickerColors2)) {
                        }
                        i3 |= i14;
                    } else {
                        datePickerColors2 = datePickerColors;
                    }
                    i3 |= i14;
                } else {
                    datePickerColors2 = datePickerColors;
                }
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i111) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i111 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i111) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i111 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i111) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i111 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i111) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i111 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    }
                    Modifier modifier10 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                    }
                    localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final CalendarModel calendarModel7 = (CalendarModel) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.startReplaceGroup(-1454747621);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                    if (z3) {
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i111) {
                                ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                if ((i111 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1490010652, i111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                    }
                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                m1766invokevCnGnXg(displayMode.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                            public final void m1766invokevCnGnXg(int i112) {
                                                dateRangePickerState2.mo1769setDisplayModevCnGnXg(i112);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                    } else {
                        composableLambdaRememberComposableLambda = null;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i111 = i11 >> 6;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m1743DateEntryContainerau3_HiA(modifier10, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i112) {
                            ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                            if ((i112 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-57534331, i112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                }
                                Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                            invoke2(l, l2);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Long l, Long l2) {
                                            try {
                                                dateRangePickerState2.setSelection(l, l2);
                                            } catch (IllegalArgumentException unused) {
                                            }
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                Function2 function7 = (Function2) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged3 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                Object objRememberedValue4 = composer3.rememberedValue();
                                if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                            invoke(l.longValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(long j) {
                                            dateRangePickerState3.setDisplayedMonthMillis(j);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel7, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i111 & 112) | (i111 & 896) | (i111 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter3 = datePickerFormatter2;
                    z4 = z3;
                    modifier3 = modifier10;
                    function5 = function4;
                    datePickerColors3 = datePickerColorsColors;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i112) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i112 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i112) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i112 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i112) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i112 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i112) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i112 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    }
                    Modifier modifier11 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                    }
                    localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final CalendarModel calendarModel8 = (CalendarModel) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.startReplaceGroup(-1454747621);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                    if (z3) {
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i112) {
                                ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                if ((i112 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1490010652, i112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                    }
                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                m1766invokevCnGnXg(displayMode.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                            public final void m1766invokevCnGnXg(int i113) {
                                                dateRangePickerState2.mo1769setDisplayModevCnGnXg(i113);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                    } else {
                        composableLambdaRememberComposableLambda = null;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i112 = i11 >> 6;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m1743DateEntryContainerau3_HiA(modifier11, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i113) {
                            ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                            if ((i113 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-57534331, i113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                }
                                Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                            invoke2(l, l2);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Long l, Long l2) {
                                            try {
                                                dateRangePickerState2.setSelection(l, l2);
                                            } catch (IllegalArgumentException unused) {
                                            }
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                Function2 function7 = (Function2) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged3 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                Object objRememberedValue4 = composer3.rememberedValue();
                                if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                            invoke(l.longValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(long j) {
                                            dateRangePickerState3.setDisplayedMonthMillis(j);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel8, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i112 & 112) | (i112 & 896) | (i112 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter3 = datePickerFormatter2;
                    z4 = z3;
                    modifier3 = modifier11;
                    function5 = function4;
                    datePickerColors3 = datePickerColorsColors;
                }
                function6 = function2RememberComposableLambda2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                        public final void invoke(Composer composer3, int i113) {
                            DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            function2RememberComposableLambda = function2;
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    function2RememberComposableLambda2 = function3;
                    if (composerStartRestartGroup.changedInstance(function2RememberComposableLambda2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            datePickerColors2 = datePickerColors;
                            if (composerStartRestartGroup.changed(datePickerColors2)) {
                            }
                            i3 |= i14;
                        } else {
                            datePickerColors2 = datePickerColors;
                        }
                        i3 |= i14;
                    } else {
                        datePickerColors2 = datePickerColors;
                    }
                    if ((599187 & i3) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i113) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i113 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i113) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i113 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i113) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i113 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i113) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i113 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        }
                        Modifier modifier12 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                        }
                        localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final CalendarModel calendarModel9 = (CalendarModel) objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composerStartRestartGroup.startReplaceGroup(-1454747621);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                        if (z3) {
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i113) {
                                    ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                    if ((i113 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1490010652, i113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                        }
                                        Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                        int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                        boolean zChanged2 = composer3.changed(dateRangePickerState);
                                        final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                        Object objRememberedValue3 = composer3.rememberedValue();
                                        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                    m1766invokevCnGnXg(displayMode.getValue());
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                                public final void m1766invokevCnGnXg(int i114) {
                                                    dateRangePickerState2.mo1769setDisplayModevCnGnXg(i114);
                                                }
                                            };
                                            composer3.updateRememberedValue(objRememberedValue3);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                        } else {
                            composableLambdaRememberComposableLambda = null;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i113 = i11 >> 6;
                        composer2 = composerStartRestartGroup;
                        DatePickerKt.m1743DateEntryContainerau3_HiA(modifier12, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i114) {
                                ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                                if ((i114 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-57534331, i114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                    }
                                    Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                    Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                    long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                                invoke2(l, l2);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Long l, Long l2) {
                                                try {
                                                    dateRangePickerState2.setSelection(l, l2);
                                                } catch (IllegalArgumentException unused) {
                                                }
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    Function2 function7 = (Function2) objRememberedValue3;
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged3 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                    Object objRememberedValue4 = composer3.rememberedValue();
                                    if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                                invoke(l.longValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(long j) {
                                                dateRangePickerState3.setDisplayedMonthMillis(j);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue4);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel9, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i113 & 112) | (i113 & 896) | (i113 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter3 = datePickerFormatter2;
                        z4 = z3;
                        modifier3 = modifier12;
                        function5 = function4;
                        datePickerColors3 = datePickerColorsColors;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i114) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i114 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i114) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i114 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i114) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i114 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i114) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i114 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        }
                        Modifier modifier13 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                        }
                        localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final CalendarModel calendarModel10 = (CalendarModel) objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composerStartRestartGroup.startReplaceGroup(-1454747621);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                        if (z3) {
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i114) {
                                    ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                    if ((i114 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1490010652, i114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                        }
                                        Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                        int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                        boolean zChanged2 = composer3.changed(dateRangePickerState);
                                        final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                        Object objRememberedValue3 = composer3.rememberedValue();
                                        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                    m1766invokevCnGnXg(displayMode.getValue());
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                                public final void m1766invokevCnGnXg(int i115) {
                                                    dateRangePickerState2.mo1769setDisplayModevCnGnXg(i115);
                                                }
                                            };
                                            composer3.updateRememberedValue(objRememberedValue3);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                        } else {
                            composableLambdaRememberComposableLambda = null;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i114 = i11 >> 6;
                        composer2 = composerStartRestartGroup;
                        DatePickerKt.m1743DateEntryContainerau3_HiA(modifier13, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i115) {
                                ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                                if ((i115 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-57534331, i115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                    }
                                    Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                    Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                    long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                                invoke2(l, l2);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Long l, Long l2) {
                                                try {
                                                    dateRangePickerState2.setSelection(l, l2);
                                                } catch (IllegalArgumentException unused) {
                                                }
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    Function2 function7 = (Function2) objRememberedValue3;
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged3 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                    Object objRememberedValue4 = composer3.rememberedValue();
                                    if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                                invoke(l.longValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(long j) {
                                                dateRangePickerState3.setDisplayedMonthMillis(j);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue4);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel10, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i114 & 112) | (i114 & 896) | (i114 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter3 = datePickerFormatter2;
                        z4 = z3;
                        modifier3 = modifier13;
                        function5 = function4;
                        datePickerColors3 = datePickerColorsColors;
                    }
                    function6 = function2RememberComposableLambda2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                            public final void invoke(Composer composer3, int i115) {
                                DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                z2 = z;
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        datePickerColors2 = datePickerColors;
                        if (composerStartRestartGroup.changed(datePickerColors2)) {
                        }
                        i3 |= i14;
                    } else {
                        datePickerColors2 = datePickerColors;
                    }
                    i3 |= i14;
                } else {
                    datePickerColors2 = datePickerColors;
                }
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i115) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i115 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i115) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i115 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i115) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i115 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i115) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i115 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    }
                    Modifier modifier14 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                    }
                    localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final CalendarModel calendarModel11 = (CalendarModel) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.startReplaceGroup(-1454747621);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                    if (z3) {
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i115) {
                                ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                if ((i115 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1490010652, i115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                    }
                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                m1766invokevCnGnXg(displayMode.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                            public final void m1766invokevCnGnXg(int i116) {
                                                dateRangePickerState2.mo1769setDisplayModevCnGnXg(i116);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                    } else {
                        composableLambdaRememberComposableLambda = null;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i115 = i11 >> 6;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m1743DateEntryContainerau3_HiA(modifier14, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i116) {
                            ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                            if ((i116 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-57534331, i116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                }
                                Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                            invoke2(l, l2);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Long l, Long l2) {
                                            try {
                                                dateRangePickerState2.setSelection(l, l2);
                                            } catch (IllegalArgumentException unused) {
                                            }
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                Function2 function7 = (Function2) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged3 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                Object objRememberedValue4 = composer3.rememberedValue();
                                if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                            invoke(l.longValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(long j) {
                                            dateRangePickerState3.setDisplayedMonthMillis(j);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel11, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i115 & 112) | (i115 & 896) | (i115 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter3 = datePickerFormatter2;
                    z4 = z3;
                    modifier3 = modifier14;
                    function5 = function4;
                    datePickerColors3 = datePickerColorsColors;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i116) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i116 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i116) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i116 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i116) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i116 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i116) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i116 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    }
                    Modifier modifier15 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                    }
                    localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final CalendarModel calendarModel12 = (CalendarModel) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.startReplaceGroup(-1454747621);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                    if (z3) {
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i116) {
                                ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                if ((i116 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1490010652, i116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                    }
                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                m1766invokevCnGnXg(displayMode.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                            public final void m1766invokevCnGnXg(int i117) {
                                                dateRangePickerState2.mo1769setDisplayModevCnGnXg(i117);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                    } else {
                        composableLambdaRememberComposableLambda = null;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i116 = i11 >> 6;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m1743DateEntryContainerau3_HiA(modifier15, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i117) {
                            ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                            if ((i117 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-57534331, i117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                }
                                Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                            invoke2(l, l2);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Long l, Long l2) {
                                            try {
                                                dateRangePickerState2.setSelection(l, l2);
                                            } catch (IllegalArgumentException unused) {
                                            }
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                Function2 function7 = (Function2) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged3 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                Object objRememberedValue4 = composer3.rememberedValue();
                                if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                            invoke(l.longValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(long j) {
                                            dateRangePickerState3.setDisplayedMonthMillis(j);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel12, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i116 & 112) | (i116 & 896) | (i116 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter3 = datePickerFormatter2;
                    z4 = z3;
                    modifier3 = modifier15;
                    function5 = function4;
                    datePickerColors3 = datePickerColorsColors;
                }
                function6 = function2RememberComposableLambda2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                        public final void invoke(Composer composer3, int i117) {
                            DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 24576;
            function2RememberComposableLambda2 = function3;
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        datePickerColors2 = datePickerColors;
                        if (composerStartRestartGroup.changed(datePickerColors2)) {
                        }
                        i3 |= i14;
                    } else {
                        datePickerColors2 = datePickerColors;
                    }
                    i3 |= i14;
                } else {
                    datePickerColors2 = datePickerColors;
                }
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i117) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i117 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i117) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i117 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i117) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i117 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i117) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i117 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    }
                    Modifier modifier16 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                    }
                    localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final CalendarModel calendarModel13 = (CalendarModel) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.startReplaceGroup(-1454747621);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                    if (z3) {
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i117) {
                                ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                if ((i117 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1490010652, i117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                    }
                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                m1766invokevCnGnXg(displayMode.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                            public final void m1766invokevCnGnXg(int i118) {
                                                dateRangePickerState2.mo1769setDisplayModevCnGnXg(i118);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                    } else {
                        composableLambdaRememberComposableLambda = null;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i117 = i11 >> 6;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m1743DateEntryContainerau3_HiA(modifier16, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i118) {
                            ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                            if ((i118 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-57534331, i118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                }
                                Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                            invoke2(l, l2);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Long l, Long l2) {
                                            try {
                                                dateRangePickerState2.setSelection(l, l2);
                                            } catch (IllegalArgumentException unused) {
                                            }
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                Function2 function7 = (Function2) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged3 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                Object objRememberedValue4 = composer3.rememberedValue();
                                if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                            invoke(l.longValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(long j) {
                                            dateRangePickerState3.setDisplayedMonthMillis(j);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel13, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i117 & 112) | (i117 & 896) | (i117 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter3 = datePickerFormatter2;
                    z4 = z3;
                    modifier3 = modifier16;
                    function5 = function4;
                    datePickerColors3 = datePickerColorsColors;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i118) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i118 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i118) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i118 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i118) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i118 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i118) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i118 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    }
                    Modifier modifier17 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                    }
                    localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final CalendarModel calendarModel14 = (CalendarModel) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.startReplaceGroup(-1454747621);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                    if (z3) {
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i118) {
                                ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                if ((i118 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1490010652, i118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                    }
                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                m1766invokevCnGnXg(displayMode.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                            public final void m1766invokevCnGnXg(int i119) {
                                                dateRangePickerState2.mo1769setDisplayModevCnGnXg(i119);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                    } else {
                        composableLambdaRememberComposableLambda = null;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i118 = i11 >> 6;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m1743DateEntryContainerau3_HiA(modifier17, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i119) {
                            ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                            if ((i119 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-57534331, i119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                }
                                Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                            invoke2(l, l2);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Long l, Long l2) {
                                            try {
                                                dateRangePickerState2.setSelection(l, l2);
                                            } catch (IllegalArgumentException unused) {
                                            }
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                Function2 function7 = (Function2) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged3 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                Object objRememberedValue4 = composer3.rememberedValue();
                                if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                            invoke(l.longValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(long j) {
                                            dateRangePickerState3.setDisplayedMonthMillis(j);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel14, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i118 & 112) | (i118 & 896) | (i118 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter3 = datePickerFormatter2;
                    z4 = z3;
                    modifier3 = modifier17;
                    function5 = function4;
                    datePickerColors3 = datePickerColorsColors;
                }
                function6 = function2RememberComposableLambda2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                        public final void invoke(Composer composer3, int i119) {
                            DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z2 = z;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    datePickerColors2 = datePickerColors;
                    if (composerStartRestartGroup.changed(datePickerColors2)) {
                    }
                    i3 |= i14;
                } else {
                    datePickerColors2 = datePickerColors;
                }
                i3 |= i14;
            } else {
                datePickerColors2 = datePickerColors;
            }
            if ((599187 & i3) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i119) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i119 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i119) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i119 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                } else {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i119) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i119 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i119) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i119 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                }
                Modifier modifier18 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                }
                localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final CalendarModel calendarModel15 = (CalendarModel) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.startReplaceGroup(-1454747621);
                ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                if (z3) {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i119) {
                            ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                            if ((i119 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1490010652, i119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                }
                                Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                            m1766invokevCnGnXg(displayMode.getValue());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                        public final void m1766invokevCnGnXg(int i1110) {
                                            dateRangePickerState2.mo1769setDisplayModevCnGnXg(i1110);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54);
                } else {
                    composableLambdaRememberComposableLambda = null;
                }
                composerStartRestartGroup.endReplaceGroup();
                int i119 = i11 >> 6;
                composer2 = composerStartRestartGroup;
                DatePickerKt.m1743DateEntryContainerau3_HiA(modifier18, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i1110) {
                        ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                        if ((i1110 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-57534331, i1110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                            }
                            Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                            Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                            long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                            int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged2 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                            Object objRememberedValue3 = composer3.rememberedValue();
                            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                        invoke2(l, l2);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Long l, Long l2) {
                                        try {
                                            dateRangePickerState2.setSelection(l, l2);
                                        } catch (IllegalArgumentException unused) {
                                        }
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue3);
                            }
                            Function2 function7 = (Function2) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged3 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                            Object objRememberedValue4 = composer3.rememberedValue();
                            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                        invoke(l.longValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(long j) {
                                        dateRangePickerState3.setDisplayedMonthMillis(j);
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel15, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i119 & 112) | (i119 & 896) | (i119 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter3 = datePickerFormatter2;
                z4 = z3;
                modifier3 = modifier18;
                function5 = function4;
                datePickerColors3 = datePickerColorsColors;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1110) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i1110 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i1110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1110) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i1110 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i1110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                } else {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1110) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i1110 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i1110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1110) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i1110 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i1110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                }
                Modifier modifier19 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                }
                localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final CalendarModel calendarModel16 = (CalendarModel) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.startReplaceGroup(-1454747621);
                ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                if (z3) {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i1110) {
                            ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                            if ((i1110 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1490010652, i1110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                }
                                Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                            m1766invokevCnGnXg(displayMode.getValue());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                        public final void m1766invokevCnGnXg(int i1111) {
                                            dateRangePickerState2.mo1769setDisplayModevCnGnXg(i1111);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54);
                } else {
                    composableLambdaRememberComposableLambda = null;
                }
                composerStartRestartGroup.endReplaceGroup();
                int i1110 = i11 >> 6;
                composer2 = composerStartRestartGroup;
                DatePickerKt.m1743DateEntryContainerau3_HiA(modifier19, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i1111) {
                        ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                        if ((i1111 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-57534331, i1111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                            }
                            Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                            Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                            long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                            int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged2 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                            Object objRememberedValue3 = composer3.rememberedValue();
                            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                        invoke2(l, l2);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Long l, Long l2) {
                                        try {
                                            dateRangePickerState2.setSelection(l, l2);
                                        } catch (IllegalArgumentException unused) {
                                        }
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue3);
                            }
                            Function2 function7 = (Function2) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged3 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                            Object objRememberedValue4 = composer3.rememberedValue();
                            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                        invoke(l.longValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(long j) {
                                        dateRangePickerState3.setDisplayedMonthMillis(j);
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel16, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i1110 & 112) | (i1110 & 896) | (i1110 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter3 = datePickerFormatter2;
                z4 = z3;
                modifier3 = modifier19;
                function5 = function4;
                datePickerColors3 = datePickerColorsColors;
            }
            function6 = function2RememberComposableLambda2;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                    public final void invoke(Composer composer3, int i1111) {
                        DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i2 & 4) != 0) {
                i12 = 128;
            } else {
                if ((i & 512) == 0) {
                    zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
                } else {
                    zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
                }
                if (zChangedInstance) {
                    i12 = 256;
                } else {
                    i12 = 128;
                }
            }
            i3 |= i12;
        }
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                function2RememberComposableLambda = function2;
                if (composerStartRestartGroup.changedInstance(function2RememberComposableLambda)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    function2RememberComposableLambda2 = function3;
                    if (composerStartRestartGroup.changedInstance(function2RememberComposableLambda2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            datePickerColors2 = datePickerColors;
                            if (composerStartRestartGroup.changed(datePickerColors2)) {
                            }
                            i3 |= i14;
                        } else {
                            datePickerColors2 = datePickerColors;
                        }
                        i3 |= i14;
                    } else {
                        datePickerColors2 = datePickerColors;
                    }
                    if ((599187 & i3) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i1111) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i1111 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i1111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i1111) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i1111 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i1111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i1111) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i1111 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i1111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i1111) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i1111 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i1111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        }
                        Modifier modifier110 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                        }
                        localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final CalendarModel calendarModel17 = (CalendarModel) objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composerStartRestartGroup.startReplaceGroup(-1454747621);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                        if (z3) {
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1111) {
                                    ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                    if ((i1111 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1490010652, i1111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                        }
                                        Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                        int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                        boolean zChanged2 = composer3.changed(dateRangePickerState);
                                        final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                        Object objRememberedValue3 = composer3.rememberedValue();
                                        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                    m1766invokevCnGnXg(displayMode.getValue());
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                                public final void m1766invokevCnGnXg(int i1112) {
                                                    dateRangePickerState2.mo1769setDisplayModevCnGnXg(i1112);
                                                }
                                            };
                                            composer3.updateRememberedValue(objRememberedValue3);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                        } else {
                            composableLambdaRememberComposableLambda = null;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i1111 = i11 >> 6;
                        composer2 = composerStartRestartGroup;
                        DatePickerKt.m1743DateEntryContainerau3_HiA(modifier110, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1112) {
                                ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                                if ((i1112 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-57534331, i1112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                    }
                                    Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                    Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                    long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                                invoke2(l, l2);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Long l, Long l2) {
                                                try {
                                                    dateRangePickerState2.setSelection(l, l2);
                                                } catch (IllegalArgumentException unused) {
                                                }
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    Function2 function7 = (Function2) objRememberedValue3;
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged3 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                    Object objRememberedValue4 = composer3.rememberedValue();
                                    if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                                invoke(l.longValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(long j) {
                                                dateRangePickerState3.setDisplayedMonthMillis(j);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue4);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel17, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i1111 & 112) | (i1111 & 896) | (i1111 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter3 = datePickerFormatter2;
                        z4 = z3;
                        modifier3 = modifier110;
                        function5 = function4;
                        datePickerColors3 = datePickerColorsColors;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i1112) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i1112 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i1112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i1112) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i1112 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i1112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        } else {
                            if (i13 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                i3 &= -897;
                            } else {
                                datePickerFormatter2 = datePickerFormatter;
                            }
                            if (i4 != 0) {
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i1112) {
                                        ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                        if ((i1112 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-162164694, i1112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, 54);
                                i10 = 54;
                            } else {
                                i10 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i1112) {
                                        ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                        if ((i1112 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-185279404, i1112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, composerStartRestartGroup, i10);
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 64) != 0) {
                                i11 = i3 & (-3670017);
                                z3 = z2;
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                function4 = function2RememberComposableLambda;
                            } else {
                                i11 = i3;
                                function4 = function2RememberComposableLambda;
                                z3 = z2;
                                datePickerColorsColors = datePickerColors2;
                            }
                        }
                        Modifier modifier111 = modifier2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                        }
                        localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final CalendarModel calendarModel18 = (CalendarModel) objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composerStartRestartGroup.startReplaceGroup(-1454747621);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                        if (z3) {
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1112) {
                                    ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                    if ((i1112 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1490010652, i1112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                        }
                                        Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                        int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                        boolean zChanged2 = composer3.changed(dateRangePickerState);
                                        final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                        Object objRememberedValue3 = composer3.rememberedValue();
                                        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                    m1766invokevCnGnXg(displayMode.getValue());
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                                public final void m1766invokevCnGnXg(int i1113) {
                                                    dateRangePickerState2.mo1769setDisplayModevCnGnXg(i1113);
                                                }
                                            };
                                            composer3.updateRememberedValue(objRememberedValue3);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                        } else {
                            composableLambdaRememberComposableLambda = null;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i1112 = i11 >> 6;
                        composer2 = composerStartRestartGroup;
                        DatePickerKt.m1743DateEntryContainerau3_HiA(modifier111, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1113) {
                                ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                                if ((i1113 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-57534331, i1113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                    }
                                    Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                    Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                    long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                                invoke2(l, l2);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Long l, Long l2) {
                                                try {
                                                    dateRangePickerState2.setSelection(l, l2);
                                                } catch (IllegalArgumentException unused) {
                                                }
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    Function2 function7 = (Function2) objRememberedValue3;
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged3 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                    Object objRememberedValue4 = composer3.rememberedValue();
                                    if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                                invoke(l.longValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(long j) {
                                                dateRangePickerState3.setDisplayedMonthMillis(j);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue4);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel18, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i1112 & 112) | (i1112 & 896) | (i1112 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter3 = datePickerFormatter2;
                        z4 = z3;
                        modifier3 = modifier111;
                        function5 = function4;
                        datePickerColors3 = datePickerColorsColors;
                    }
                    function6 = function2RememberComposableLambda2;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                            public final void invoke(Composer composer3, int i1113) {
                                DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                z2 = z;
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        datePickerColors2 = datePickerColors;
                        if (composerStartRestartGroup.changed(datePickerColors2)) {
                        }
                        i3 |= i14;
                    } else {
                        datePickerColors2 = datePickerColors;
                    }
                    i3 |= i14;
                } else {
                    datePickerColors2 = datePickerColors;
                }
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1113) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i1113 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i1113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1113) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i1113 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i1113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1113) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i1113 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i1113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1113) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i1113 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i1113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    }
                    Modifier modifier112 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                    }
                    localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final CalendarModel calendarModel19 = (CalendarModel) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.startReplaceGroup(-1454747621);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                    if (z3) {
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1113) {
                                ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                if ((i1113 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1490010652, i1113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                    }
                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                m1766invokevCnGnXg(displayMode.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                            public final void m1766invokevCnGnXg(int i1114) {
                                                dateRangePickerState2.mo1769setDisplayModevCnGnXg(i1114);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                    } else {
                        composableLambdaRememberComposableLambda = null;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i1113 = i11 >> 6;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m1743DateEntryContainerau3_HiA(modifier112, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i1114) {
                            ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                            if ((i1114 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-57534331, i1114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                }
                                Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                            invoke2(l, l2);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Long l, Long l2) {
                                            try {
                                                dateRangePickerState2.setSelection(l, l2);
                                            } catch (IllegalArgumentException unused) {
                                            }
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                Function2 function7 = (Function2) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged3 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                Object objRememberedValue4 = composer3.rememberedValue();
                                if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                            invoke(l.longValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(long j) {
                                            dateRangePickerState3.setDisplayedMonthMillis(j);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel19, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i1113 & 112) | (i1113 & 896) | (i1113 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter3 = datePickerFormatter2;
                    z4 = z3;
                    modifier3 = modifier112;
                    function5 = function4;
                    datePickerColors3 = datePickerColorsColors;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1114) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i1114 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i1114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1114) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i1114 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i1114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1114) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i1114 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i1114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1114) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i1114 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i1114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    }
                    Modifier modifier113 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                    }
                    localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final CalendarModel calendarModel110 = (CalendarModel) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.startReplaceGroup(-1454747621);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                    if (z3) {
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1114) {
                                ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                if ((i1114 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1490010652, i1114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                    }
                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                m1766invokevCnGnXg(displayMode.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                            public final void m1766invokevCnGnXg(int i1115) {
                                                dateRangePickerState2.mo1769setDisplayModevCnGnXg(i1115);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                    } else {
                        composableLambdaRememberComposableLambda = null;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i1114 = i11 >> 6;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m1743DateEntryContainerau3_HiA(modifier113, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i1115) {
                            ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                            if ((i1115 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-57534331, i1115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                }
                                Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                            invoke2(l, l2);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Long l, Long l2) {
                                            try {
                                                dateRangePickerState2.setSelection(l, l2);
                                            } catch (IllegalArgumentException unused) {
                                            }
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                Function2 function7 = (Function2) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged3 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                Object objRememberedValue4 = composer3.rememberedValue();
                                if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                            invoke(l.longValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(long j) {
                                            dateRangePickerState3.setDisplayedMonthMillis(j);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel110, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i1114 & 112) | (i1114 & 896) | (i1114 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter3 = datePickerFormatter2;
                    z4 = z3;
                    modifier3 = modifier113;
                    function5 = function4;
                    datePickerColors3 = datePickerColorsColors;
                }
                function6 = function2RememberComposableLambda2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                        public final void invoke(Composer composer3, int i1115) {
                            DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 24576;
            function2RememberComposableLambda2 = function3;
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        datePickerColors2 = datePickerColors;
                        if (composerStartRestartGroup.changed(datePickerColors2)) {
                        }
                        i3 |= i14;
                    } else {
                        datePickerColors2 = datePickerColors;
                    }
                    i3 |= i14;
                } else {
                    datePickerColors2 = datePickerColors;
                }
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1115) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i1115 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i1115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1115) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i1115 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i1115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1115) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i1115 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i1115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1115) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i1115 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i1115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    }
                    Modifier modifier114 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                    }
                    localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final CalendarModel calendarModel111 = (CalendarModel) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.startReplaceGroup(-1454747621);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                    if (z3) {
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1115) {
                                ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                if ((i1115 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1490010652, i1115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                    }
                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                m1766invokevCnGnXg(displayMode.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                            public final void m1766invokevCnGnXg(int i1116) {
                                                dateRangePickerState2.mo1769setDisplayModevCnGnXg(i1116);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                    } else {
                        composableLambdaRememberComposableLambda = null;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i1115 = i11 >> 6;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m1743DateEntryContainerau3_HiA(modifier114, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i1116) {
                            ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                            if ((i1116 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-57534331, i1116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                }
                                Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                            invoke2(l, l2);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Long l, Long l2) {
                                            try {
                                                dateRangePickerState2.setSelection(l, l2);
                                            } catch (IllegalArgumentException unused) {
                                            }
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                Function2 function7 = (Function2) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged3 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                Object objRememberedValue4 = composer3.rememberedValue();
                                if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                            invoke(l.longValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(long j) {
                                            dateRangePickerState3.setDisplayedMonthMillis(j);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel111, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i1115 & 112) | (i1115 & 896) | (i1115 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter3 = datePickerFormatter2;
                    z4 = z3;
                    modifier3 = modifier114;
                    function5 = function4;
                    datePickerColors3 = datePickerColorsColors;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1116) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i1116 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i1116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1116) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i1116 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i1116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1116) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i1116 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i1116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1116) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i1116 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i1116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    }
                    Modifier modifier115 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                    }
                    localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final CalendarModel calendarModel112 = (CalendarModel) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.startReplaceGroup(-1454747621);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                    if (z3) {
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1116) {
                                ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                if ((i1116 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1490010652, i1116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                    }
                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                m1766invokevCnGnXg(displayMode.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                            public final void m1766invokevCnGnXg(int i1117) {
                                                dateRangePickerState2.mo1769setDisplayModevCnGnXg(i1117);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                    } else {
                        composableLambdaRememberComposableLambda = null;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i1116 = i11 >> 6;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m1743DateEntryContainerau3_HiA(modifier115, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i1117) {
                            ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                            if ((i1117 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-57534331, i1117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                }
                                Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                            invoke2(l, l2);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Long l, Long l2) {
                                            try {
                                                dateRangePickerState2.setSelection(l, l2);
                                            } catch (IllegalArgumentException unused) {
                                            }
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                Function2 function7 = (Function2) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged3 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                Object objRememberedValue4 = composer3.rememberedValue();
                                if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                            invoke(l.longValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(long j) {
                                            dateRangePickerState3.setDisplayedMonthMillis(j);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel112, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i1116 & 112) | (i1116 & 896) | (i1116 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter3 = datePickerFormatter2;
                    z4 = z3;
                    modifier3 = modifier115;
                    function5 = function4;
                    datePickerColors3 = datePickerColorsColors;
                }
                function6 = function2RememberComposableLambda2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                        public final void invoke(Composer composer3, int i1117) {
                            DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z2 = z;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    datePickerColors2 = datePickerColors;
                    if (composerStartRestartGroup.changed(datePickerColors2)) {
                    }
                    i3 |= i14;
                } else {
                    datePickerColors2 = datePickerColors;
                }
                i3 |= i14;
            } else {
                datePickerColors2 = datePickerColors;
            }
            if ((599187 & i3) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1117) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i1117 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i1117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1117) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i1117 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i1117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                } else {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1117) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i1117 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i1117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1117) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i1117 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i1117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                }
                Modifier modifier116 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                }
                localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final CalendarModel calendarModel113 = (CalendarModel) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.startReplaceGroup(-1454747621);
                ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                if (z3) {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i1117) {
                            ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                            if ((i1117 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1490010652, i1117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                }
                                Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                            m1766invokevCnGnXg(displayMode.getValue());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                        public final void m1766invokevCnGnXg(int i1118) {
                                            dateRangePickerState2.mo1769setDisplayModevCnGnXg(i1118);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54);
                } else {
                    composableLambdaRememberComposableLambda = null;
                }
                composerStartRestartGroup.endReplaceGroup();
                int i1117 = i11 >> 6;
                composer2 = composerStartRestartGroup;
                DatePickerKt.m1743DateEntryContainerau3_HiA(modifier116, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i1118) {
                        ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                        if ((i1118 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-57534331, i1118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                            }
                            Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                            Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                            long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                            int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged2 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                            Object objRememberedValue3 = composer3.rememberedValue();
                            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                        invoke2(l, l2);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Long l, Long l2) {
                                        try {
                                            dateRangePickerState2.setSelection(l, l2);
                                        } catch (IllegalArgumentException unused) {
                                        }
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue3);
                            }
                            Function2 function7 = (Function2) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged3 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                            Object objRememberedValue4 = composer3.rememberedValue();
                            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                        invoke(l.longValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(long j) {
                                        dateRangePickerState3.setDisplayedMonthMillis(j);
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel113, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i1117 & 112) | (i1117 & 896) | (i1117 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter3 = datePickerFormatter2;
                z4 = z3;
                modifier3 = modifier116;
                function5 = function4;
                datePickerColors3 = datePickerColorsColors;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1118) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i1118 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i1118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1118) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i1118 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i1118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                } else {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1118) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i1118 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i1118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1118) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i1118 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i1118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                }
                Modifier modifier117 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                }
                localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final CalendarModel calendarModel114 = (CalendarModel) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.startReplaceGroup(-1454747621);
                ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                if (z3) {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i1118) {
                            ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                            if ((i1118 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1490010652, i1118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                }
                                Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                            m1766invokevCnGnXg(displayMode.getValue());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                        public final void m1766invokevCnGnXg(int i1119) {
                                            dateRangePickerState2.mo1769setDisplayModevCnGnXg(i1119);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54);
                } else {
                    composableLambdaRememberComposableLambda = null;
                }
                composerStartRestartGroup.endReplaceGroup();
                int i1118 = i11 >> 6;
                composer2 = composerStartRestartGroup;
                DatePickerKt.m1743DateEntryContainerau3_HiA(modifier117, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i1119) {
                        ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                        if ((i1119 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-57534331, i1119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                            }
                            Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                            Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                            long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                            int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged2 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                            Object objRememberedValue3 = composer3.rememberedValue();
                            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                        invoke2(l, l2);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Long l, Long l2) {
                                        try {
                                            dateRangePickerState2.setSelection(l, l2);
                                        } catch (IllegalArgumentException unused) {
                                        }
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue3);
                            }
                            Function2 function7 = (Function2) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged3 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                            Object objRememberedValue4 = composer3.rememberedValue();
                            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                        invoke(l.longValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(long j) {
                                        dateRangePickerState3.setDisplayedMonthMillis(j);
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel114, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i1118 & 112) | (i1118 & 896) | (i1118 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter3 = datePickerFormatter2;
                z4 = z3;
                modifier3 = modifier117;
                function5 = function4;
                datePickerColors3 = datePickerColorsColors;
            }
            function6 = function2RememberComposableLambda2;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                    public final void invoke(Composer composer3, int i1119) {
                        DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        function2RememberComposableLambda = function2;
        i6 = i2 & 16;
        if (i6 != 0) {
            if ((i & 24576) == 0) {
                function2RememberComposableLambda2 = function3;
                if (composerStartRestartGroup.changedInstance(function2RememberComposableLambda2)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i3 |= i7;
            }
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        datePickerColors2 = datePickerColors;
                        if (composerStartRestartGroup.changed(datePickerColors2)) {
                        }
                        i3 |= i14;
                    } else {
                        datePickerColors2 = datePickerColors;
                    }
                    i3 |= i14;
                } else {
                    datePickerColors2 = datePickerColors;
                }
                if ((599187 & i3) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1119) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i1119 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i1119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1119) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i1119 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i1119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1119) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i1119 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i1119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i1119) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i1119 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i1119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    }
                    Modifier modifier118 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                    }
                    localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final CalendarModel calendarModel115 = (CalendarModel) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.startReplaceGroup(-1454747621);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                    if (z3) {
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1119) {
                                ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                if ((i1119 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1490010652, i1119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                    }
                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                m1766invokevCnGnXg(displayMode.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                            public final void m1766invokevCnGnXg(int i11110) {
                                                dateRangePickerState2.mo1769setDisplayModevCnGnXg(i11110);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                    } else {
                        composableLambdaRememberComposableLambda = null;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i1119 = i11 >> 6;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m1743DateEntryContainerau3_HiA(modifier118, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11110) {
                            ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                            if ((i11110 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-57534331, i11110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                }
                                Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                            invoke2(l, l2);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Long l, Long l2) {
                                            try {
                                                dateRangePickerState2.setSelection(l, l2);
                                            } catch (IllegalArgumentException unused) {
                                            }
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                Function2 function7 = (Function2) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged3 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                Object objRememberedValue4 = composer3.rememberedValue();
                                if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                            invoke(l.longValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(long j) {
                                            dateRangePickerState3.setDisplayedMonthMillis(j);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel115, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i1119 & 112) | (i1119 & 896) | (i1119 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter3 = datePickerFormatter2;
                    z4 = z3;
                    modifier3 = modifier118;
                    function5 = function4;
                    datePickerColors3 = datePickerColorsColors;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i11110) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i11110 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i11110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i11110) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i11110 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i11110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    } else {
                        if (i13 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            i3 &= -897;
                        } else {
                            datePickerFormatter2 = datePickerFormatter;
                        }
                        if (i4 != 0) {
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i11110) {
                                    ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                    if ((i11110 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-162164694, i11110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54);
                            i10 = 54;
                        } else {
                            i10 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i11110) {
                                    ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                    if ((i11110 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-185279404, i11110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, i10);
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 64) != 0) {
                            i11 = i3 & (-3670017);
                            z3 = z2;
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            function4 = function2RememberComposableLambda;
                        } else {
                            i11 = i3;
                            function4 = function2RememberComposableLambda;
                            z3 = z2;
                            datePickerColorsColors = datePickerColors2;
                        }
                    }
                    Modifier modifier119 = modifier2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                    }
                    localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final CalendarModel calendarModel116 = (CalendarModel) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.startReplaceGroup(-1454747621);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                    if (z3) {
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11110) {
                                ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                                if ((i11110 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1490010652, i11110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                    }
                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                    int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                    boolean zChanged2 = composer3.changed(dateRangePickerState);
                                    final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                    Object objRememberedValue3 = composer3.rememberedValue();
                                    if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                                m1766invokevCnGnXg(displayMode.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                            public final void m1766invokevCnGnXg(int i11111) {
                                                dateRangePickerState2.mo1769setDisplayModevCnGnXg(i11111);
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                    } else {
                        composableLambdaRememberComposableLambda = null;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i11110 = i11 >> 6;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m1743DateEntryContainerau3_HiA(modifier119, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11111) {
                            ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                            if ((i11111 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-57534331, i11111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                                }
                                Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                                Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                                long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                            invoke2(l, l2);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Long l, Long l2) {
                                            try {
                                                dateRangePickerState2.setSelection(l, l2);
                                            } catch (IllegalArgumentException unused) {
                                            }
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                Function2 function7 = (Function2) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged3 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                                Object objRememberedValue4 = composer3.rememberedValue();
                                if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                            invoke(l.longValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(long j) {
                                            dateRangePickerState3.setDisplayedMonthMillis(j);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel116, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i11110 & 112) | (i11110 & 896) | (i11110 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter3 = datePickerFormatter2;
                    z4 = z3;
                    modifier3 = modifier119;
                    function5 = function4;
                    datePickerColors3 = datePickerColorsColors;
                }
                function6 = function2RememberComposableLambda2;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                        public final void invoke(Composer composer3, int i11111) {
                            DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z2 = z;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    datePickerColors2 = datePickerColors;
                    if (composerStartRestartGroup.changed(datePickerColors2)) {
                    }
                    i3 |= i14;
                } else {
                    datePickerColors2 = datePickerColors;
                }
                i3 |= i14;
            } else {
                datePickerColors2 = datePickerColors;
            }
            if ((599187 & i3) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11111) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i11111 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i11111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11111) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i11111 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i11111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                } else {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11111) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i11111 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i11111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11111) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i11111 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i11111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                }
                Modifier modifier1110 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                }
                localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final CalendarModel calendarModel117 = (CalendarModel) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.startReplaceGroup(-1454747621);
                ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                if (z3) {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11111) {
                            ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                            if ((i11111 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1490010652, i11111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                }
                                Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                            m1766invokevCnGnXg(displayMode.getValue());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                        public final void m1766invokevCnGnXg(int i11112) {
                                            dateRangePickerState2.mo1769setDisplayModevCnGnXg(i11112);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54);
                } else {
                    composableLambdaRememberComposableLambda = null;
                }
                composerStartRestartGroup.endReplaceGroup();
                int i11111 = i11 >> 6;
                composer2 = composerStartRestartGroup;
                DatePickerKt.m1743DateEntryContainerau3_HiA(modifier1110, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i11112) {
                        ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                        if ((i11112 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-57534331, i11112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                            }
                            Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                            Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                            long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                            int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged2 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                            Object objRememberedValue3 = composer3.rememberedValue();
                            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                        invoke2(l, l2);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Long l, Long l2) {
                                        try {
                                            dateRangePickerState2.setSelection(l, l2);
                                        } catch (IllegalArgumentException unused) {
                                        }
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue3);
                            }
                            Function2 function7 = (Function2) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged3 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                            Object objRememberedValue4 = composer3.rememberedValue();
                            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                        invoke(l.longValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(long j) {
                                        dateRangePickerState3.setDisplayedMonthMillis(j);
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel117, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i11111 & 112) | (i11111 & 896) | (i11111 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter3 = datePickerFormatter2;
                z4 = z3;
                modifier3 = modifier1110;
                function5 = function4;
                datePickerColors3 = datePickerColorsColors;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11112) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i11112 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i11112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11112) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i11112 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i11112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                } else {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11112) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i11112 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i11112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11112) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i11112 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i11112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                }
                Modifier modifier1111 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                }
                localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final CalendarModel calendarModel118 = (CalendarModel) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.startReplaceGroup(-1454747621);
                ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                if (z3) {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11112) {
                            ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                            if ((i11112 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1490010652, i11112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                }
                                Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                            m1766invokevCnGnXg(displayMode.getValue());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                        public final void m1766invokevCnGnXg(int i11113) {
                                            dateRangePickerState2.mo1769setDisplayModevCnGnXg(i11113);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54);
                } else {
                    composableLambdaRememberComposableLambda = null;
                }
                composerStartRestartGroup.endReplaceGroup();
                int i11112 = i11 >> 6;
                composer2 = composerStartRestartGroup;
                DatePickerKt.m1743DateEntryContainerau3_HiA(modifier1111, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i11113) {
                        ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                        if ((i11113 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-57534331, i11113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                            }
                            Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                            Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                            long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                            int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged2 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                            Object objRememberedValue3 = composer3.rememberedValue();
                            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                        invoke2(l, l2);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Long l, Long l2) {
                                        try {
                                            dateRangePickerState2.setSelection(l, l2);
                                        } catch (IllegalArgumentException unused) {
                                        }
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue3);
                            }
                            Function2 function7 = (Function2) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged3 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                            Object objRememberedValue4 = composer3.rememberedValue();
                            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                        invoke(l.longValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(long j) {
                                        dateRangePickerState3.setDisplayedMonthMillis(j);
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel118, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i11112 & 112) | (i11112 & 896) | (i11112 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter3 = datePickerFormatter2;
                z4 = z3;
                modifier3 = modifier1111;
                function5 = function4;
                datePickerColors3 = datePickerColorsColors;
            }
            function6 = function2RememberComposableLambda2;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                    public final void invoke(Composer composer3, int i11113) {
                        DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 24576;
        function2RememberComposableLambda2 = function3;
        i8 = i2 & 32;
        if (i8 != 0) {
            if ((196608 & i) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i3 |= i9;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    datePickerColors2 = datePickerColors;
                    if (composerStartRestartGroup.changed(datePickerColors2)) {
                    }
                    i3 |= i14;
                } else {
                    datePickerColors2 = datePickerColors;
                }
                i3 |= i14;
            } else {
                datePickerColors2 = datePickerColors;
            }
            if ((599187 & i3) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11113) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i11113 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i11113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11113) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i11113 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i11113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                } else {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11113) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i11113 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i11113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11113) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i11113 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i11113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                }
                Modifier modifier1112 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                }
                localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final CalendarModel calendarModel119 = (CalendarModel) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.startReplaceGroup(-1454747621);
                ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                if (z3) {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11113) {
                            ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                            if ((i11113 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1490010652, i11113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                }
                                Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                            m1766invokevCnGnXg(displayMode.getValue());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                        public final void m1766invokevCnGnXg(int i11114) {
                                            dateRangePickerState2.mo1769setDisplayModevCnGnXg(i11114);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54);
                } else {
                    composableLambdaRememberComposableLambda = null;
                }
                composerStartRestartGroup.endReplaceGroup();
                int i11113 = i11 >> 6;
                composer2 = composerStartRestartGroup;
                DatePickerKt.m1743DateEntryContainerau3_HiA(modifier1112, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i11114) {
                        ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                        if ((i11114 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-57534331, i11114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                            }
                            Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                            Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                            long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                            int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged2 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                            Object objRememberedValue3 = composer3.rememberedValue();
                            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                        invoke2(l, l2);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Long l, Long l2) {
                                        try {
                                            dateRangePickerState2.setSelection(l, l2);
                                        } catch (IllegalArgumentException unused) {
                                        }
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue3);
                            }
                            Function2 function7 = (Function2) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged3 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                            Object objRememberedValue4 = composer3.rememberedValue();
                            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                        invoke(l.longValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(long j) {
                                        dateRangePickerState3.setDisplayedMonthMillis(j);
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel119, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i11113 & 112) | (i11113 & 896) | (i11113 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter3 = datePickerFormatter2;
                z4 = z3;
                modifier3 = modifier1112;
                function5 = function4;
                datePickerColors3 = datePickerColorsColors;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11114) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i11114 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i11114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11114) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i11114 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i11114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                } else {
                    if (i13 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        i3 &= -897;
                    } else {
                        datePickerFormatter2 = datePickerFormatter;
                    }
                    if (i4 != 0) {
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11114) {
                                ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                                if ((i11114 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-162164694, i11114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54);
                        i10 = 54;
                    } else {
                        i10 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11114) {
                                ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                                if ((i11114 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-185279404, i11114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, i10);
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 64) != 0) {
                        i11 = i3 & (-3670017);
                        z3 = z2;
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        function4 = function2RememberComposableLambda;
                    } else {
                        i11 = i3;
                        function4 = function2RememberComposableLambda;
                        z3 = z2;
                        datePickerColorsColors = datePickerColors2;
                    }
                }
                Modifier modifier1113 = modifier2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
                }
                localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final CalendarModel calendarModel1110 = (CalendarModel) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.startReplaceGroup(-1454747621);
                ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
                if (z3) {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11114) {
                            ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                            if ((i11114 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1490010652, i11114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                                }
                                Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                                int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                                ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                                boolean zChanged2 = composer3.changed(dateRangePickerState);
                                final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                                Object objRememberedValue3 = composer3.rememberedValue();
                                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                            m1766invokevCnGnXg(displayMode.getValue());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                        public final void m1766invokevCnGnXg(int i11115) {
                                            dateRangePickerState2.mo1769setDisplayModevCnGnXg(i11115);
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue3);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54);
                } else {
                    composableLambdaRememberComposableLambda = null;
                }
                composerStartRestartGroup.endReplaceGroup();
                int i11114 = i11 >> 6;
                composer2 = composerStartRestartGroup;
                DatePickerKt.m1743DateEntryContainerau3_HiA(modifier1113, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i11115) {
                        ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                        if ((i11115 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-57534331, i11115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                            }
                            Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                            Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                            long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                            int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged2 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                            Object objRememberedValue3 = composer3.rememberedValue();
                            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                        invoke2(l, l2);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Long l, Long l2) {
                                        try {
                                            dateRangePickerState2.setSelection(l, l2);
                                        } catch (IllegalArgumentException unused) {
                                        }
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue3);
                            }
                            Function2 function7 = (Function2) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged3 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                            Object objRememberedValue4 = composer3.rememberedValue();
                            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                        invoke(l.longValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(long j) {
                                        dateRangePickerState3.setDisplayedMonthMillis(j);
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel1110, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i11114 & 112) | (i11114 & 896) | (i11114 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter3 = datePickerFormatter2;
                z4 = z3;
                modifier3 = modifier1113;
                function5 = function4;
                datePickerColors3 = datePickerColorsColors;
            }
            function6 = function2RememberComposableLambda2;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                    public final void invoke(Composer composer3, int i11115) {
                        DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        z2 = z;
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                datePickerColors2 = datePickerColors;
                if (composerStartRestartGroup.changed(datePickerColors2)) {
                }
                i3 |= i14;
            } else {
                datePickerColors2 = datePickerColors;
            }
            i3 |= i14;
        } else {
            datePickerColors2 = datePickerColors;
        }
        if ((599187 & i3) == 599186) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i13 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 4) != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    i3 &= -897;
                } else {
                    datePickerFormatter2 = datePickerFormatter;
                }
                if (i4 != 0) {
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11115) {
                            ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                            if ((i11115 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-162164694, i11115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                }
                                DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54);
                    i10 = 54;
                } else {
                    i10 = 54;
                }
                if (i6 != 0) {
                    function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11115) {
                            ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                            if ((i11115 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-185279404, i11115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                }
                                DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, i10);
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if ((i2 & 64) != 0) {
                    i11 = i3 & (-3670017);
                    z3 = z2;
                    datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    function4 = function2RememberComposableLambda;
                } else {
                    i11 = i3;
                    function4 = function2RememberComposableLambda;
                    z3 = z2;
                    datePickerColorsColors = datePickerColors2;
                }
            } else {
                if (i13 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 4) != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    i3 &= -897;
                } else {
                    datePickerFormatter2 = datePickerFormatter;
                }
                if (i4 != 0) {
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11115) {
                            ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                            if ((i11115 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-162164694, i11115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                }
                                DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54);
                    i10 = 54;
                } else {
                    i10 = 54;
                }
                if (i6 != 0) {
                    function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11115) {
                            ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                            if ((i11115 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-185279404, i11115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                }
                                DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, i10);
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if ((i2 & 64) != 0) {
                    i11 = i3 & (-3670017);
                    z3 = z2;
                    datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    function4 = function2RememberComposableLambda;
                } else {
                    i11 = i3;
                    function4 = function2RememberComposableLambda;
                    z3 = z2;
                    datePickerColorsColors = datePickerColors2;
                }
            }
            Modifier modifier1114 = modifier2;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
            }
            localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final CalendarModel calendarModel1111 = (CalendarModel) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.startReplaceGroup(-1454747621);
            ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
            if (z3) {
                composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i11115) {
                        ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                        if ((i11115 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1490010652, i11115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                            }
                            Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                            int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged2 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                            Object objRememberedValue3 = composer3.rememberedValue();
                            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                        m1766invokevCnGnXg(displayMode.getValue());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                    public final void m1766invokevCnGnXg(int i11116) {
                                        dateRangePickerState2.mo1769setDisplayModevCnGnXg(i11116);
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54);
            } else {
                composableLambdaRememberComposableLambda = null;
            }
            composerStartRestartGroup.endReplaceGroup();
            int i11115 = i11 >> 6;
            composer2 = composerStartRestartGroup;
            DatePickerKt.m1743DateEntryContainerau3_HiA(modifier1114, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i11116) {
                    ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                    if ((i11116 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-57534331, i11116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                        }
                        Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                        Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                        long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                        int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                        ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                        boolean zChanged2 = composer3.changed(dateRangePickerState);
                        final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                        Object objRememberedValue3 = composer3.rememberedValue();
                        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                    invoke2(l, l2);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Long l, Long l2) {
                                    try {
                                        dateRangePickerState2.setSelection(l, l2);
                                    } catch (IllegalArgumentException unused) {
                                    }
                                }
                            };
                            composer3.updateRememberedValue(objRememberedValue3);
                        }
                        Function2 function7 = (Function2) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                        boolean zChanged3 = composer3.changed(dateRangePickerState);
                        final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                        Object objRememberedValue4 = composer3.rememberedValue();
                        if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                    invoke(l.longValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(long j) {
                                    dateRangePickerState3.setDisplayedMonthMillis(j);
                                }
                            };
                            composer3.updateRememberedValue(objRememberedValue4);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel1111, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i11115 & 112) | (i11115 & 896) | (i11115 & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            datePickerFormatter3 = datePickerFormatter2;
            z4 = z3;
            modifier3 = modifier1114;
            function5 = function4;
            datePickerColors3 = datePickerColorsColors;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i13 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 4) != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    i3 &= -897;
                } else {
                    datePickerFormatter2 = datePickerFormatter;
                }
                if (i4 != 0) {
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11116) {
                            ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                            if ((i11116 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-162164694, i11116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                }
                                DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54);
                    i10 = 54;
                } else {
                    i10 = 54;
                }
                if (i6 != 0) {
                    function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11116) {
                            ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                            if ((i11116 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-185279404, i11116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                }
                                DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, i10);
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if ((i2 & 64) != 0) {
                    i11 = i3 & (-3670017);
                    z3 = z2;
                    datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    function4 = function2RememberComposableLambda;
                } else {
                    i11 = i3;
                    function4 = function2RememberComposableLambda;
                    z3 = z2;
                    datePickerColorsColors = datePickerColors2;
                }
            } else {
                if (i13 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 4) != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454781303, "CC(remember):DateRangePicker.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    datePickerFormatter2 = (DatePickerFormatter) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    i3 &= -897;
                } else {
                    datePickerFormatter2 = datePickerFormatter;
                }
                if (i4 != 0) {
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-162164694, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11116) {
                            ComposerKt.sourceInformation(composer3, "C97@4534L145:DateRangePicker.kt#uh7d8r");
                            if ((i11116 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-162164694, i11116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:97)");
                                }
                                DateRangePickerDefaults.INSTANCE.m1759DateRangePickerTitlehOD91z4(dateRangePickerState.mo1768getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), composer3, 432, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54);
                    i10 = 54;
                } else {
                    i10 = 54;
                }
                if (i6 != 0) {
                    function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-185279404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11116) {
                            ComposerKt.sourceInformation(composer3, "C103@4763L312:DateRangePicker.kt#uh7d8r");
                            if ((i11116 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-185279404, i11116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:103)");
                                }
                                DateRangePickerDefaults.INSTANCE.m1758DateRangePickerHeadlinev84Udv0(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo1768getDisplayModejFl4v0(), datePickerFormatter2, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), composer3, 221184, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, i10);
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if ((i2 & 64) != 0) {
                    i11 = i3 & (-3670017);
                    z3 = z2;
                    datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    function4 = function2RememberComposableLambda;
                } else {
                    i11 = i3;
                    function4 = function2RememberComposableLambda;
                    z3 = z2;
                    datePickerColorsColors = datePickerColors2;
                }
            }
            Modifier modifier1115 = modifier2;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(650830774, i11, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:113)");
            }
            localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1454754600, "CC(remember):DateRangePicker.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = CalendarModel_androidKt.createCalendarModel(localeDefaultLocale);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final CalendarModel calendarModel1112 = (CalendarModel) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.startReplaceGroup(-1454747621);
            ComposerKt.sourceInformation(composerStartRestartGroup, "122@5491L323");
            if (z3) {
                composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1490010652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.4
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i11116) {
                        ComposerKt.sourceInformation(composer3, "C126@5723L50,123@5513L283:DateRangePicker.kt#uh7d8r");
                        if ((i11116 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1490010652, i11116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:123)");
                            }
                            Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                            int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                            ComposerKt.sourceInformationMarkerStart(composer3, 1752251243, "CC(remember):DateRangePicker.kt#9igjgp");
                            boolean zChanged2 = composer3.changed(dateRangePickerState);
                            final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                            Object objRememberedValue3 = composer3.rememberedValue();
                            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = (Function1) new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$4$1$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                        m1766invokevCnGnXg(displayMode.getValue());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                    public final void m1766invokevCnGnXg(int i11117) {
                                        dateRangePickerState2.mo1769setDisplayModevCnGnXg(i11117);
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            DatePickerKt.m1747DisplayModeToggleButtontER2X8s(modifierPadding, iMo1768getDisplayModejFl4v0, (Function1) objRememberedValue3, composer3, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54);
            } else {
                composableLambdaRememberComposableLambda = null;
            }
            composerStartRestartGroup.endReplaceGroup();
            int i11116 = i11 >> 6;
            composer2 = composerStartRestartGroup;
            DatePickerKt.m1743DateEntryContainerau3_HiA(modifier1115, function4, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, datePickerColorsColors, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6), Dp.m6890constructorimpl(DatePickerModalTokens.INSTANCE.m2899getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset), ComposableLambdaKt.rememberComposableLambda(-57534331, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i11117) {
                    ComposerKt.sourceInformation(composer3, "C142@6428L467,153@6934L91,137@6121L1123:DateRangePicker.kt#uh7d8r");
                    if ((i11117 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-57534331, i11117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:137)");
                        }
                        Long selectedStartDateMillis = dateRangePickerState.getSelectedStartDateMillis();
                        Long selectedEndDateMillis = dateRangePickerState.getSelectedEndDateMillis();
                        long displayedMonthMillis = dateRangePickerState.getDisplayedMonthMillis();
                        int iMo1768getDisplayModejFl4v0 = dateRangePickerState.mo1768getDisplayModejFl4v0();
                        ComposerKt.sourceInformationMarkerStart(composer3, 1752274220, "CC(remember):DateRangePicker.kt#9igjgp");
                        boolean zChanged2 = composer3.changed(dateRangePickerState);
                        final DateRangePickerState dateRangePickerState2 = dateRangePickerState;
                        Object objRememberedValue3 = composer3.rememberedValue();
                        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = (Function2) new Function2<Long, Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$1$1
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
                                    invoke2(l, l2);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Long l, Long l2) {
                                    try {
                                        dateRangePickerState2.setSelection(l, l2);
                                    } catch (IllegalArgumentException unused) {
                                    }
                                }
                            };
                            composer3.updateRememberedValue(objRememberedValue3);
                        }
                        Function2 function7 = (Function2) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerStart(composer3, 1752290036, "CC(remember):DateRangePicker.kt#9igjgp");
                        boolean zChanged3 = composer3.changed(dateRangePickerState);
                        final DateRangePickerState dateRangePickerState3 = dateRangePickerState;
                        Object objRememberedValue4 = composer3.rememberedValue();
                        if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5$2$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                    invoke(l.longValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(long j) {
                                    dateRangePickerState3.setDisplayedMonthMillis(j);
                                }
                            };
                            composer3.updateRememberedValue(objRememberedValue4);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo1768getDisplayModejFl4v0, function7, (Function1) objRememberedValue4, calendarModel1112, dateRangePickerState.getYearRange(), datePickerFormatter2, dateRangePickerState.getSelectableDates(), datePickerColorsColors, composer3, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composer2, ((i11 >> 3) & 14) | 14155776 | (i11116 & 112) | (i11116 & 896) | (i11116 & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            datePickerFormatter3 = datePickerFormatter2;
            z4 = z3;
            modifier3 = modifier1115;
            function5 = function4;
            datePickerColors3 = datePickerColorsColors;
        }
        function6 = function2RememberComposableLambda2;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.6
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

                public final void invoke(Composer composer3, int i11117) {
                    DateRangePickerKt.DateRangePicker(dateRangePickerState, modifier3, datePickerFormatter3, function5, function6, z4, datePickerColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX INFO: renamed from: rememberDateRangePickerState-IlFM19s, reason: not valid java name */
    public static final DateRangePickerState m1765rememberDateRangePickerStateIlFM19s(Long l, Long l2, Long l3, IntRange intRange, int i, SelectableDates selectableDates, Composer composer, int i2, int i3) {
        ComposerKt.sourceInformationMarkerStart(composer, -2012087461, "C(rememberDateRangePickerState)P(3,2,1,5,0:c#material3.DisplayMode)262@11757L15,*263@11866L475,263@11784L557:DateRangePicker.kt#uh7d8r");
        final Long l4 = (i3 & 1) != 0 ? null : l;
        final Long l5 = (i3 & 2) != 0 ? null : l2;
        final Long l6 = (i3 & 4) != 0 ? l4 : l3;
        final IntRange yearRange = (i3 & 8) != 0 ? DatePickerDefaults.INSTANCE.getYearRange() : intRange;
        final int iM1787getPickerjFl4v0 = (i3 & 16) != 0 ? DisplayMode.INSTANCE.m1787getPickerjFl4v0() : i;
        final SelectableDates allDates = (i3 & 32) != 0 ? DatePickerDefaults.INSTANCE.getAllDates() : selectableDates;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2012087461, i2, -1, "androidx.compose.material3.rememberDateRangePickerState (DateRangePicker.kt:261)");
        }
        final Locale localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composer, 0);
        Object[] objArr = new Object[0];
        Saver<DateRangePickerStateImpl, Object> Saver = DateRangePickerStateImpl.INSTANCE.Saver(allDates, localeDefaultLocale);
        ComposerKt.sourceInformationMarkerStart(composer, -250595201, "CC(remember):DateRangePicker.kt#9igjgp");
        boolean zChangedInstance = ((((i2 & 112) ^ 48) > 32 && composer.changed(l5)) || (i2 & 48) == 32) | ((((i2 & 14) ^ 6) > 4 && composer.changed(l4)) || (i2 & 6) == 4) | ((((i2 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(l6)) || (i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composer.changedInstance(yearRange) | ((((57344 & i2) ^ 24576) > 16384 && composer.changed(iM1787getPickerjFl4v0)) || (i2 & 24576) == 16384) | ((((458752 & i2) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072 && composer.changed(allDates)) || (i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) | composer.changedInstance(localeDefaultLocale);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (Function0) new Function0<DateRangePickerStateImpl>() { // from class: androidx.compose.material3.DateRangePickerKt$rememberDateRangePickerState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final DateRangePickerStateImpl invoke() {
                    return new DateRangePickerStateImpl(l4, l5, l6, yearRange, iM1787getPickerjFl4v0, allDates, localeDefaultLocale, null);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        DateRangePickerStateImpl dateRangePickerStateImpl = (DateRangePickerStateImpl) RememberSaveableKt.m3571rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) objRememberedValue, composer, 0, 4);
        dateRangePickerStateImpl.setSelectableDates(allDates);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return dateRangePickerStateImpl;
    }

    /* JADX INFO: renamed from: DateRangePickerState-HVP43zI$default, reason: not valid java name */
    public static /* synthetic */ DateRangePickerState m1761DateRangePickerStateHVP43zI$default(Locale locale, Long l, Long l2, Long l3, IntRange intRange, int i, SelectableDates selectableDates, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            l = null;
        }
        if ((i2 & 4) != 0) {
            l2 = null;
        }
        if ((i2 & 8) != 0) {
            l3 = l;
        }
        if ((i2 & 16) != 0) {
            intRange = DatePickerDefaults.INSTANCE.getYearRange();
        }
        if ((i2 & 32) != 0) {
            i = DisplayMode.INSTANCE.m1787getPickerjFl4v0();
        }
        if ((i2 & 64) != 0) {
            selectableDates = DatePickerDefaults.INSTANCE.getAllDates();
        }
        return m1760DateRangePickerStateHVP43zI(locale, l, l2, l3, intRange, i, selectableDates);
    }

    /* JADX INFO: renamed from: DateRangePickerState-HVP43zI, reason: not valid java name */
    public static final DateRangePickerState m1760DateRangePickerStateHVP43zI(Locale locale, Long l, Long l2, Long l3, IntRange intRange, int i, SelectableDates selectableDates) {
        return new DateRangePickerStateImpl(l, l2, l3, intRange, i, selectableDates, locale, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: SwitchableDateEntryContent-RN-2D1Q, reason: not valid java name */
    public static final void m1762SwitchableDateEntryContentRN2D1Q(final Long l, final Long l2, final long j, final int i, final Function2<? super Long, ? super Long, Unit> function2, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange intRange, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, Composer composer, final int i2, final int i3) {
        int i4;
        Long l3;
        long j2;
        Function2<? super Long, ? super Long, Unit> function3;
        Function1<? super Long, Unit> function4;
        CalendarModel calendarModel2;
        IntRange intRange2;
        int i5;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-532789335);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SwitchableDateEntryContent)P(9,8,4,3:c#material3.DisplayMode,5,6!1,10,2,7)676@30273L1249,668@30045L1477:DateRangePicker.kt#uh7d8r");
        if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(l) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            l3 = l2;
            i4 |= composerStartRestartGroup.changed(l3) ? 32 : 16;
        } else {
            l3 = l2;
        }
        if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            j2 = j;
            i4 |= composerStartRestartGroup.changed(j2) ? 256 : 128;
        } else {
            j2 = j;
        }
        if ((i2 & 3072) == 0) {
            i4 |= composerStartRestartGroup.changed(i) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            function3 = function2;
            i4 |= composerStartRestartGroup.changedInstance(function3) ? 16384 : 8192;
        } else {
            function3 = function2;
        }
        if ((196608 & i2) == 0) {
            function4 = function1;
            i4 |= composerStartRestartGroup.changedInstance(function4) ? 131072 : 65536;
        } else {
            function4 = function1;
        }
        if ((1572864 & i2) == 0) {
            calendarModel2 = calendarModel;
            i4 |= composerStartRestartGroup.changedInstance(calendarModel2) ? 1048576 : 524288;
        } else {
            calendarModel2 = calendarModel;
        }
        if ((12582912 & i2) == 0) {
            intRange2 = intRange;
            i4 |= composerStartRestartGroup.changedInstance(intRange2) ? 8388608 : 4194304;
        } else {
            intRange2 = intRange;
        }
        if ((i2 & 100663296) == 0) {
            i4 |= (i2 & C.BUFFER_FLAG_FIRST_SAMPLE) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 67108864 : 33554432;
        }
        if ((i2 & BaseAdapter.HEADER) == 0) {
            i4 |= composerStartRestartGroup.changed(selectableDates) ? 536870912 : 268435456;
        }
        if ((i3 & 6) == 0) {
            i5 = i3 | (composerStartRestartGroup.changed(datePickerColors) ? 4 : 2);
        } else {
            i5 = i3;
        }
        if ((i4 & 306783379) != 306783378 || (i5 & 3) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-532789335, i4, i5, "androidx.compose.material3.SwitchableDateEntryContent (DateRangePicker.kt:665)");
            }
            final Long l4 = l3;
            final Function2<? super Long, ? super Long, Unit> function5 = function3;
            final IntRange intRange3 = intRange2;
            final long j3 = j2;
            final Function1<? super Long, Unit> function6 = function4;
            final CalendarModel calendarModel3 = calendarModel2;
            composer2 = composerStartRestartGroup;
            CrossfadeKt.Crossfade(DisplayMode.m1779boximpl(i), SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$SwitchableDateEntryContent$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    SemanticsPropertiesKt.setContainer(semanticsPropertyReceiver, true);
                }
            }, 1, null), AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null), (String) null, ComposableLambdaKt.rememberComposableLambda(-1026642619, true, new Function3<DisplayMode, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$SwitchableDateEntryContent$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode, Composer composer3, Integer num) {
                    m1767invokeQujVXRc(displayMode.getValue(), composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-QujVXRc, reason: not valid java name */
                public final void m1767invokeQujVXRc(int i6, Composer composer3, int i7) {
                    int i8;
                    ComposerKt.sourceInformation(composer3, "CP(0:c#material3.DisplayMode):DateRangePicker.kt#uh7d8r");
                    if ((i7 & 6) == 0) {
                        i8 = i7 | (composer3.changed(i6) ? 4 : 2);
                    } else {
                        i8 = i7;
                    }
                    if ((i8 & 19) != 18 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1026642619, i8, -1, "androidx.compose.material3.SwitchableDateEntryContent.<anonymous> (DateRangePicker.kt:677)");
                        }
                        if (DisplayMode.m1782equalsimpl0(i6, DisplayMode.INSTANCE.m1787getPickerjFl4v0())) {
                            composer3.startReplaceGroup(-1871299185);
                            ComposerKt.sourceInformation(composer3, "679@30355L618");
                            DateRangePickerKt.DateRangePickerContent(l, l4, j3, function5, function6, calendarModel3, intRange3, datePickerFormatter, selectableDates, datePickerColors, composer3, 0);
                            composer3.endReplaceGroup();
                        } else if (DisplayMode.m1782equalsimpl0(i6, DisplayMode.INSTANCE.m1786getInputjFl4v0())) {
                            composer3.startReplaceGroup(-1871277944);
                            ComposerKt.sourceInformation(composer3, "692@31023L483");
                            DateRangeInputKt.DateRangeInputContent(l, l4, function5, calendarModel3, intRange3, datePickerFormatter, selectableDates, datePickerColors, composer3, 0);
                            composer3.endReplaceGroup();
                        } else {
                            composer3.startReplaceGroup(2120399965);
                            composer3.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composer2, ((i4 >> 9) & 14) | 24960, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$SwitchableDateEntryContent$3
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

                public final void invoke(Composer composer3, int i6) {
                    DateRangePickerKt.m1762SwitchableDateEntryContentRN2D1Q(l, l2, j, i, function2, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DateRangePickerContent(final Long l, final Long l2, final long j, final Function2<? super Long, ? super Long, Unit> function2, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange intRange, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, Composer composer, final int i) {
        int i2;
        Long l3;
        Function2<? super Long, ? super Long, Unit> function3;
        Function1<? super Long, Unit> function4;
        SelectableDates selectableDates2;
        Object obj;
        DateRangePickerKt$DateRangePickerContent$1$1 dateRangePickerKt$DateRangePickerContent$1$1;
        Composer composerStartRestartGroup = composer.startRestartGroup(-787063721);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DateRangePickerContent)P(8,7,3,4,5!1,9,2,6)722@32197L64,725@32351L309,725@32324L336,733@32666L648:DateRangePicker.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(l) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            l3 = l2;
            i2 |= composerStartRestartGroup.changed(l3) ? 32 : 16;
        } else {
            l3 = l2;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            function3 = function2;
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 2048 : 1024;
        } else {
            function3 = function2;
        }
        if ((i & 24576) == 0) {
            function4 = function1;
            i2 |= composerStartRestartGroup.changedInstance(function4) ? 16384 : 8192;
        } else {
            function4 = function1;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(calendarModel) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(intRange) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= (16777216 & i) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            selectableDates2 = selectableDates;
            i2 |= composerStartRestartGroup.changed(selectableDates2) ? 67108864 : 33554432;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((i & BaseAdapter.HEADER) == 0) {
            i2 |= composerStartRestartGroup.changed(datePickerColors) ? 536870912 : 268435456;
        }
        if ((i2 & 306783379) != 306783378 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-787063721, i2, -1, "androidx.compose.material3.DateRangePickerContent (DateRangePicker.kt:719)");
            }
            int iCoerceAtLeast = RangesKt.coerceAtLeast(calendarModel.getMonth(j).indexIn(intRange), 0);
            LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(iCoerceAtLeast, 0, composerStartRestartGroup, 0, 2);
            Integer numValueOf = Integer.valueOf(iCoerceAtLeast);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1097467628, "CC(remember):DateRangePicker.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(lazyListStateRememberLazyListState) | composerStartRestartGroup.changed(iCoerceAtLeast);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = null;
                dateRangePickerKt$DateRangePickerContent$1$1 = new DateRangePickerKt$DateRangePickerContent$1$1(lazyListStateRememberLazyListState, iCoerceAtLeast, null);
                composerStartRestartGroup.updateRememberedValue(dateRangePickerKt$DateRangePickerContent$1$1);
            } else {
                dateRangePickerKt$DateRangePickerContent$1$1 = objRememberedValue;
                obj = null;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(numValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) dateRangePickerKt$DateRangePickerContent$1$1, composerStartRestartGroup, 0);
            Modifier modifierM722paddingVpY3zN4$default = PaddingKt.m722paddingVpY3zN4$default(Modifier.INSTANCE, DatePickerKt.getDatePickerHorizontalPadding(), 0.0f, 2, obj);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM722paddingVpY3zN4$default);
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
            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 556963099, "C734@32754L31,735@32794L514:DateRangePicker.kt#uh7d8r");
            DatePickerKt.WeekDays(datePickerColors, calendarModel, composerStartRestartGroup, ((i2 >> 27) & 14) | ((i2 >> 12) & 112));
            VerticalMonthsList(lazyListStateRememberLazyListState, l, l3, function3, function4, calendarModel, intRange, datePickerFormatter, selectableDates2, datePickerColors, composerStartRestartGroup, ((i2 << 3) & 1008) | (i2 & 7168) | (57344 & i2) | (458752 & i2) | (3670016 & i2) | (29360128 & i2) | (234881024 & i2) | (1879048192 & i2));
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePickerContent.3
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

                public final void invoke(Composer composer2, int i3) {
                    DateRangePickerKt.DateRangePickerContent(l, l2, j, function2, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerticalMonthsList(LazyListState lazyListState, final Long l, final Long l2, final Function2<? super Long, ? super Long, Unit> function2, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange intRange, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, Composer composer, final int i) {
        int i2;
        Long l3;
        Long l4;
        Function2<? super Long, ? super Long, Unit> function3;
        DateRangePickerKt$VerticalMonthsList$2$1 dateRangePickerKt$VerticalMonthsList$2$1;
        final LazyListState lazyListState2 = lazyListState;
        Composer composerStartRestartGroup = composer.startRestartGroup(1257365001);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(VerticalMonthsList)P(3,8,7,4,5!1,9,2,6)770@34042L158,776@34262L5,776@34269L3748,776@34205L3812,853@38052L228,853@38022L258:DateRangePicker.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(lazyListState2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            l3 = l;
            i2 |= composerStartRestartGroup.changed(l3) ? 32 : 16;
        } else {
            l3 = l;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            l4 = l2;
            i2 |= composerStartRestartGroup.changed(l4) ? 256 : 128;
        } else {
            l4 = l2;
        }
        if ((i & 3072) == 0) {
            function3 = function2;
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 2048 : 1024;
        } else {
            function3 = function2;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(calendarModel) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(intRange) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= (16777216 & i) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(selectableDates) ? 67108864 : 33554432;
        }
        if ((805306368 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(datePickerColors) ? 536870912 : 268435456;
        }
        if ((i2 & 306783379) != 306783378 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1257365001, i2, -1, "androidx.compose.material3.VerticalMonthsList (DateRangePicker.kt:767)");
            }
            final CalendarDate today = calendarModel.getToday();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2140145208, "CC(remember):DateRangePicker.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(intRange);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = calendarModel.getMonth(intRange.getFirst(), 1);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final CalendarMonth calendarMonth = (CalendarMonth) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Long l5 = l3;
            final Function2<? super Long, ? super Long, Unit> function4 = function3;
            int i3 = i2;
            final Long l6 = l4;
            TextKt.ProvideTextStyle(TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getDateLabelTextFont(), composerStartRestartGroup, 6), ComposableLambdaKt.rememberComposableLambda(1090773432, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.VerticalMonthsList.1
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
                    ComposerKt.sourceInformation(composer2, "C777@34300L24,778@34366L59,779@34463L55,783@34699L317,808@35694L2317,800@35319L2692:DateRangePicker.kt#uh7d8r");
                    if ((i4 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1090773432, i4, -1, "androidx.compose.material3.VerticalMonthsList.<anonymous> (DateRangePicker.kt:777)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composer2, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composer2, -954363344, "CC(remember):Effects.kt#9igjgp");
                        Object objRememberedValue2 = composer2.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer2));
                            composer2.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                            objRememberedValue2 = compositionScopedCoroutineScopeCanceller;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        Strings.Companion companion = Strings.INSTANCE;
                        String strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_scroll_to_previous_month), composer2, 0);
                        Strings.Companion companion2 = Strings.INSTANCE;
                        String strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_scroll_to_next_month), composer2, 0);
                        ComposerKt.sourceInformationMarkerStart(composer2, -522190970, "CC(remember):DateRangePicker.kt#9igjgp");
                        boolean zChanged2 = composer2.changed(l5) | composer2.changed(l6) | composer2.changed(function4);
                        final Long l7 = l5;
                        final Long l8 = l6;
                        final Function2<Long, Long, Unit> function5 = function4;
                        Object objRememberedValue3 = composer2.rememberedValue();
                        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = (Function1) new Function1<Long, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1$onDateSelectionChange$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Long l9) {
                                    invoke(l9.longValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(long j) {
                                    DateRangePickerKt.updateDateSelection(j, l7, l8, function5);
                                }
                            };
                            composer2.updateRememberedValue(objRememberedValue3);
                        }
                        final Function1 function6 = (Function1) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        final List listCustomScrollActions = DateRangePickerKt.customScrollActions(lazyListState2, coroutineScope, strM2723getString2EP1pXo, strM2723getString2EP1pXo2);
                        Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.VerticalMonthsList.1.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                SemanticsPropertiesKt.setVerticalScrollAxisRange(semanticsPropertyReceiver, new ScrollAxisRange(new Function0<Float>() { // from class: androidx.compose.material3.DateRangePickerKt.VerticalMonthsList.1.1.1
                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Float invoke() {
                                        return Float.valueOf(0.0f);
                                    }
                                }, new Function0<Float>() { // from class: androidx.compose.material3.DateRangePickerKt.VerticalMonthsList.1.1.2
                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Float invoke() {
                                        return Float.valueOf(0.0f);
                                    }
                                }, false, 4, null));
                            }
                        }, 1, null);
                        LazyListState lazyListState3 = lazyListState2;
                        ComposerKt.sourceInformationMarkerStart(composer2, -522157130, "CC(remember):DateRangePicker.kt#9igjgp");
                        boolean zChangedInstance = composer2.changedInstance(intRange) | composer2.changedInstance(calendarModel) | composer2.changed(calendarMonth) | composer2.changedInstance(datePickerFormatter) | composer2.changedInstance(listCustomScrollActions) | composer2.changed(datePickerColors) | composer2.changed(l5) | composer2.changed(l6) | composer2.changed(function6) | composer2.changed(today) | composer2.changed(selectableDates);
                        final IntRange intRange2 = intRange;
                        final CalendarModel calendarModel2 = calendarModel;
                        final CalendarMonth calendarMonth2 = calendarMonth;
                        final Long l9 = l5;
                        final Long l10 = l6;
                        final CalendarDate calendarDate = today;
                        final DatePickerFormatter datePickerFormatter2 = datePickerFormatter;
                        final SelectableDates selectableDates2 = selectableDates;
                        final DatePickerColors datePickerColors2 = datePickerColors;
                        Object objRememberedValue4 = composer2.rememberedValue();
                        if (zChangedInstance || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = (Function1) new Function1<LazyListScope, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(LazyListScope lazyListScope) {
                                    invoke2(lazyListScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(LazyListScope lazyListScope) {
                                    int iNumberOfMonthsInRange = DatePickerKt.numberOfMonthsInRange(intRange2);
                                    final CalendarModel calendarModel3 = calendarModel2;
                                    final CalendarMonth calendarMonth3 = calendarMonth2;
                                    final Long l11 = l9;
                                    final Long l12 = l10;
                                    final Function1<Long, Unit> function7 = function6;
                                    final CalendarDate calendarDate2 = calendarDate;
                                    final DatePickerFormatter datePickerFormatter3 = datePickerFormatter2;
                                    final SelectableDates selectableDates3 = selectableDates2;
                                    final DatePickerColors datePickerColors3 = datePickerColors2;
                                    final List<CustomAccessibilityAction> list = listCustomScrollActions;
                                    LazyListScope.CC.items$default(lazyListScope, iNumberOfMonthsInRange, null, null, ComposableLambdaKt.composableLambdaInstance(-1413501381, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1$2$1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(4);
                                        }

                                        @Override // kotlin.jvm.functions.Function4
                                        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer3, Integer num2) {
                                            invoke(lazyItemScope, num.intValue(), composer3, num2.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(LazyItemScope lazyItemScope, int i5, Composer composer3, int i6) {
                                            int i7;
                                            SelectedRangeInfo selectedRangeInfo;
                                            ComposerKt.sourceInformation(composer3, "C811@35861L2126:DateRangePicker.kt#uh7d8r");
                                            if ((i6 & 6) == 0) {
                                                i7 = i6 | (composer3.changed(lazyItemScope) ? 4 : 2);
                                            } else {
                                                i7 = i6;
                                            }
                                            if ((i6 & 48) == 0) {
                                                i7 |= composer3.changed(i5) ? 32 : 16;
                                            }
                                            if ((i7 & 147) != 146 || !composer3.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1413501381, i7, -1, "androidx.compose.material3.VerticalMonthsList.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DateRangePicker.kt:810)");
                                                }
                                                final CalendarMonth calendarMonthPlusMonths = calendarModel3.plusMonths(calendarMonth3, i5);
                                                Modifier modifierFillParentMaxWidth$default = LazyItemScope.CC.fillParentMaxWidth$default(lazyItemScope, Modifier.INSTANCE, 0.0f, 1, null);
                                                Long l13 = l11;
                                                Long l14 = l12;
                                                Function1<Long, Unit> function8 = function7;
                                                CalendarDate calendarDate3 = calendarDate2;
                                                final DatePickerFormatter datePickerFormatter4 = datePickerFormatter3;
                                                SelectableDates selectableDates4 = selectableDates3;
                                                final DatePickerColors datePickerColors4 = datePickerColors3;
                                                final List<CustomAccessibilityAction> list2 = list;
                                                CalendarModel calendarModel4 = calendarModel3;
                                                ComposerKt.sourceInformationMarkerStart(composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                                                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                                                ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillParentMaxWidth$default);
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
                                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                                }
                                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                                ComposerKt.sourceInformationMarkerStart(composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                                                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart(composer3, 1460125673, "C812@36002L5,812@36009L623,812@35932L700,838@37423L546:DateRangePicker.kt#uh7d8r");
                                                TextKt.ProvideTextStyle(TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionMonthSubheadFont(), composer3, 6), ComposableLambdaKt.rememberComposableLambda(1622100276, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1$2$1$1$1$1
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(2);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                                        invoke(composer4, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(Composer composer4, int i8) {
                                                        ComposerKt.sourceInformation(composer4, "C817@36237L15,821@36475L45,813@36035L575:DateRangePicker.kt#uh7d8r");
                                                        if ((i8 & 3) != 2 || !composer4.getSkipping()) {
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(1622100276, i8, -1, "androidx.compose.material3.VerticalMonthsList.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DateRangePicker.kt:813)");
                                                            }
                                                            String monthYear = datePickerFormatter4.formatMonthYear(Long.valueOf(calendarMonthPlusMonths.getStartUtcTimeMillis()), CalendarLocale_androidKt.defaultLocale(composer4, 0));
                                                            if (monthYear == null) {
                                                                monthYear = "-";
                                                            }
                                                            Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.getCalendarMonthSubheadPadding());
                                                            ComposerKt.sourceInformationMarkerStart(composer4, -77497871, "CC(remember):DateRangePicker.kt#9igjgp");
                                                            boolean zChangedInstance2 = composer4.changedInstance(list2);
                                                            final List<CustomAccessibilityAction> list3 = list2;
                                                            Object objRememberedValue5 = composer4.rememberedValue();
                                                            if (zChangedInstance2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                                                objRememberedValue5 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1$2$1$1$1$1$1$1
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
                                                                        SemanticsPropertiesKt.setCustomActions(semanticsPropertyReceiver, list3);
                                                                    }
                                                                };
                                                                composer4.updateRememberedValue(objRememberedValue5);
                                                            }
                                                            ComposerKt.sourceInformationMarkerEnd(composer4);
                                                            TextKt.m2411Text4IGK_g(monthYear, SemanticsModifierKt.semantics$default(modifierPadding, false, (Function1) objRememberedValue5, 1, null), datePickerColors4.getSubheadContentColor(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer4, 0, 0, 131064);
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventEnd();
                                                                return;
                                                            }
                                                            return;
                                                        }
                                                        composer4.skipToGroupEnd();
                                                    }
                                                }, composer3, 54), composer3, 48);
                                                composer3.startReplaceGroup(2125334733);
                                                ComposerKt.sourceInformation(composer3, "827@36822L488");
                                                if (l13 == null || l14 == null) {
                                                    selectedRangeInfo = null;
                                                } else {
                                                    ComposerKt.sourceInformationMarkerStart(composer3, 2125337741, "CC(remember):DateRangePicker.kt#9igjgp");
                                                    boolean zChanged3 = composer3.changed(l13) | composer3.changed(l14);
                                                    Object objRememberedValue5 = composer3.rememberedValue();
                                                    if (zChanged3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                                        objRememberedValue5 = SelectedRangeInfo.INSTANCE.calculateRangeInfo(calendarMonthPlusMonths, calendarModel4.getCanonicalDate(l13.longValue()), calendarModel4.getCanonicalDate(l14.longValue()));
                                                        composer3.updateRememberedValue(objRememberedValue5);
                                                    }
                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                    selectedRangeInfo = (SelectedRangeInfo) objRememberedValue5;
                                                }
                                                composer3.endReplaceGroup();
                                                DatePickerKt.Month(calendarMonthPlusMonths, function8, calendarDate3.getUtcTimeMillis(), l13, l14, selectedRangeInfo, datePickerFormatter4, selectableDates4, datePickerColors4, composer3, 0);
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
                                    }), 6, null);
                                }
                            };
                            composer2.updateRememberedValue(objRememberedValue4);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        LazyDslKt.LazyColumn(modifierSemantics$default, lazyListState3, null, false, null, null, null, false, (Function1) objRememberedValue4, composer2, 0, 252);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2140016818, "CC(remember):DateRangePicker.kt#9igjgp");
            int i4 = i3 & 14;
            boolean zChangedInstance = (i4 == 4) | ((57344 & i3) == 16384) | composerStartRestartGroup.changedInstance(calendarModel) | composerStartRestartGroup.changedInstance(intRange);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                lazyListState2 = lazyListState;
                dateRangePickerKt$VerticalMonthsList$2$1 = new DateRangePickerKt$VerticalMonthsList$2$1(lazyListState2, function1, calendarModel, intRange, null);
                composerStartRestartGroup.updateRememberedValue(dateRangePickerKt$VerticalMonthsList$2$1);
            } else {
                dateRangePickerKt$VerticalMonthsList$2$1 = objRememberedValue2;
                lazyListState2 = lazyListState;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(lazyListState2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) dateRangePickerKt$VerticalMonthsList$2$1, composerStartRestartGroup, i4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.VerticalMonthsList.3
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

                public final void invoke(Composer composer2, int i5) {
                    DateRangePickerKt.VerticalMonthsList(lazyListState2, l, l2, function2, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateDateSelection(long j, Long l, Long l2, Function2<? super Long, ? super Long, Unit> function2) {
        if ((l == null && l2 == null) || (l != null && l2 != null)) {
            function2.invoke(Long.valueOf(j), null);
        } else if (l != null && j >= l.longValue()) {
            function2.invoke(l, Long.valueOf(j));
        } else {
            function2.invoke(Long.valueOf(j), null);
        }
    }

    public static final PaddingValues getCalendarMonthSubheadPadding() {
        return CalendarMonthSubheadPadding;
    }

    /* JADX INFO: renamed from: drawRangeBackground-mxwnekA, reason: not valid java name */
    public static final void m1764drawRangeBackgroundmxwnekA(ContentDrawScope contentDrawScope, SelectedRangeInfo selectedRangeInfo, long j) {
        float fM3939getWidthimpl;
        float f = contentDrawScope.mo406toPx0680j_4(DatePickerKt.getRecommendedSizeForAccessibility());
        float f2 = contentDrawScope.mo406toPx0680j_4(DatePickerKt.getRecommendedSizeForAccessibility());
        float f3 = contentDrawScope.mo406toPx0680j_4(DatePickerModalTokens.INSTANCE.m2892getDateStateLayerHeightD9Ej5fM());
        float f4 = (f2 - f3) / 2.0f;
        float fM3939getWidthimpl2 = (Size.m3939getWidthimpl(contentDrawScope.mo4647getSizeNHjbRc()) - (7.0f * f)) / 7.0f;
        long gridStartCoordinates = selectedRangeInfo.getGridStartCoordinates();
        int iM7022getXimpl = IntOffset.m7022getXimpl(gridStartCoordinates);
        int iM7023getYimpl = IntOffset.m7023getYimpl(gridStartCoordinates);
        long gridEndCoordinates = selectedRangeInfo.getGridEndCoordinates();
        int iM7022getXimpl2 = IntOffset.m7022getXimpl(gridEndCoordinates);
        int iM7023getYimpl2 = IntOffset.m7023getYimpl(gridEndCoordinates);
        float f5 = f + fM3939getWidthimpl2;
        float f6 = fM3939getWidthimpl2 / 2.0f;
        float fM3939getWidthimpl3 = (iM7022getXimpl * f5) + (selectedRangeInfo.getFirstIsSelectionStart() ? f / 2.0f : 0.0f) + f6;
        float f7 = (iM7023getYimpl * f2) + f4;
        float f8 = iM7022getXimpl2 * f5;
        if (selectedRangeInfo.getLastIsSelectionEnd()) {
            f /= 2.0f;
        }
        float fM3939getWidthimpl4 = f8 + f + f6;
        float f9 = (iM7023getYimpl2 * f2) + f4;
        boolean z = contentDrawScope.getLayoutDirection() == LayoutDirection.Rtl;
        if (z) {
            fM3939getWidthimpl3 = Size.m3939getWidthimpl(contentDrawScope.mo4647getSizeNHjbRc()) - fM3939getWidthimpl3;
            fM3939getWidthimpl4 = Size.m3939getWidthimpl(contentDrawScope.mo4647getSizeNHjbRc()) - fM3939getWidthimpl4;
        }
        ContentDrawScope contentDrawScope2 = contentDrawScope;
        long jOffset = OffsetKt.Offset(fM3939getWidthimpl3, f7);
        if (iM7023getYimpl == iM7023getYimpl2) {
            fM3939getWidthimpl = fM3939getWidthimpl4 - fM3939getWidthimpl3;
        } else {
            fM3939getWidthimpl = z ? -fM3939getWidthimpl3 : Size.m3939getWidthimpl(contentDrawScope.mo4647getSizeNHjbRc()) - fM3939getWidthimpl3;
        }
        DrawScope.CC.m4732drawRectnJ9OG0$default(contentDrawScope2, j, jOffset, SizeKt.Size(fM3939getWidthimpl, f3), 0.0f, null, null, 0, 120, null);
        if (iM7023getYimpl != iM7023getYimpl2) {
            for (int i = (iM7023getYimpl2 - iM7023getYimpl) - 1; i > 0; i--) {
                DrawScope.CC.m4732drawRectnJ9OG0$default(contentDrawScope2, j, OffsetKt.Offset(0.0f, (i * f2) + f7), SizeKt.Size(Size.m3939getWidthimpl(contentDrawScope.mo4647getSizeNHjbRc()), f3), 0.0f, null, null, 0, 120, null);
            }
            long jOffset2 = OffsetKt.Offset(contentDrawScope.getLayoutDirection() != LayoutDirection.Ltr ? Size.m3939getWidthimpl(contentDrawScope.mo4647getSizeNHjbRc()) : 0.0f, f9);
            if (z) {
                fM3939getWidthimpl4 -= Size.m3939getWidthimpl(contentDrawScope.mo4647getSizeNHjbRc());
            }
            DrawScope.CC.m4732drawRectnJ9OG0$default(contentDrawScope2, j, jOffset2, SizeKt.Size(fM3939getWidthimpl4, f3), 0.0f, null, null, 0, 120, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<CustomAccessibilityAction> customScrollActions(final LazyListState lazyListState, final CoroutineScope coroutineScope, String str, String str2) {
        return CollectionsKt.listOf((Object[]) new CustomAccessibilityAction[]{new CustomAccessibilityAction(str, new Function0<Boolean>() { // from class: androidx.compose.material3.DateRangePickerKt$customScrollActions$scrollUpAction$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                boolean z;
                if (lazyListState.getCanScrollBackward()) {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(lazyListState, null), 3, null);
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }

            /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$customScrollActions$scrollUpAction$1$1, reason: invalid class name */
            /* JADX INFO: compiled from: DateRangePicker.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.DateRangePickerKt$customScrollActions$scrollUpAction$1$1", f = "DateRangePicker.kt", i = {}, l = {1046}, m = "invokeSuspend", n = {}, s = {})
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ LazyListState $state;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(LazyListState lazyListState, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$state = lazyListState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$state, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        LazyListState lazyListState = this.$state;
                        this.label = 1;
                        if (LazyListState.scrollToItem$default(lazyListState, lazyListState.getFirstVisibleItemIndex() - 1, 0, this, 2, null) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }
        }), new CustomAccessibilityAction(str2, new Function0<Boolean>() { // from class: androidx.compose.material3.DateRangePickerKt$customScrollActions$scrollDownAction$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                boolean z;
                if (lazyListState.getCanScrollForward()) {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(lazyListState, null), 3, null);
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }

            /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$customScrollActions$scrollDownAction$1$1, reason: invalid class name */
            /* JADX INFO: compiled from: DateRangePicker.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.DateRangePickerKt$customScrollActions$scrollDownAction$1$1", f = "DateRangePicker.kt", i = {}, l = {1054}, m = "invokeSuspend", n = {}, s = {})
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ LazyListState $state;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(LazyListState lazyListState, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$state = lazyListState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$state, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        LazyListState lazyListState = this.$state;
                        this.label = 1;
                        if (LazyListState.scrollToItem$default(lazyListState, lazyListState.getFirstVisibleItemIndex() + 1, 0, this, 2, null) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }
        })});
    }
}
