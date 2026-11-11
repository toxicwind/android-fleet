package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
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
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.LiveRegionMode;
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
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DateRangePicker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J@\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0087\u0001\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00040\u0014¢\u0006\u0002\b\u00152\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u00040\u0014¢\u0006\u0002\b\u00152\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00040\u0014¢\u0006\u0002\b\u0015H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J$\u0010\u001a\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"Landroidx/compose/material3/DateRangePickerDefaults;", "", "()V", "DateRangePickerHeadline", "", "selectedStartDateMillis", "", "selectedEndDateMillis", "displayMode", "Landroidx/compose/material3/DisplayMode;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "modifier", "Landroidx/compose/ui/Modifier;", "DateRangePickerHeadline-v84Udv0", "(Ljava/lang/Long;Ljava/lang/Long;ILandroidx/compose/material3/DatePickerFormatter;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "startDateText", "", "endDateText", "startDatePlaceholder", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "endDatePlaceholder", "datesDelimiter", "DateRangePickerHeadline-0YIUgSQ", "(Ljava/lang/Long;Ljava/lang/Long;ILandroidx/compose/material3/DatePickerFormatter;Landroidx/compose/ui/Modifier;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "DateRangePickerTitle", "DateRangePickerTitle-hOD91z4", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DateRangePickerDefaults {
    public static final int $stable = 0;
    public static final DateRangePickerDefaults INSTANCE = new DateRangePickerDefaults();

    private DateRangePickerDefaults() {
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0059 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:31:0x005b  */
    /* JADX WARN: Code duplicated, block: B:32:0x0060  */
    /* JADX WARN: Code duplicated, block: B:35:0x0067  */
    /* JADX WARN: Code duplicated, block: B:38:0x007a  */
    /* JADX WARN: Code duplicated, block: B:39:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:41:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:42:0x010b  */
    /* JADX WARN: Code duplicated, block: B:45:0x011a  */
    /* JADX WARN: Code duplicated, block: B:49:0x0124  */
    /* JADX WARN: Code duplicated, block: B:51:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: DateRangePickerTitle-hOD91z4, reason: not valid java name */
    public final void m1759DateRangePickerTitlehOD91z4(final int i, Modifier modifier, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        Modifier.Companion companion;
        Composer composer2;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1412719908);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DateRangePickerTitle)P(0:c#material3.DisplayMode):DateRangePicker.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = i2 | (composerStartRestartGroup.changed(i) ? 4 : 2);
        } else {
            i4 = i2;
        }
        int i5 = i3 & 2;
        if (i5 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i4 & 19) == 18 || !composerStartRestartGroup.getSkipping()) {
                if (i5 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1412719908, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerTitle (DateRangePicker.kt:338)");
                }
                if (DisplayMode.m1782equalsimpl0(i, DisplayMode.INSTANCE.m1787getPickerjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(980469064);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "341@15673L48,341@15668L75");
                    Strings.Companion companion2 = Strings.INSTANCE;
                    TextKt.m2411Text4IGK_g(Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_title), composerStartRestartGroup, 0), companion, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, i4 & 112, 0, 131068);
                    composer2 = composerStartRestartGroup;
                    composer2.endReplaceGroup();
                } else {
                    composer2 = composerStartRestartGroup;
                    if (DisplayMode.m1782equalsimpl0(i, DisplayMode.INSTANCE.m1786getInputjFl4v0())) {
                        composer2.startReplaceGroup(980473063);
                        ComposerKt.sourceInformation(composer2, "343@15798L47,343@15793L74");
                        Strings.Companion companion3 = Strings.INSTANCE;
                        TextKt.m2411Text4IGK_g(Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_input_title), composer2, 0), companion, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, i4 & 112, 0, 131068);
                        composer2.endReplaceGroup();
                    } else {
                        composer2.startReplaceGroup(329974917);
                        composer2.endReplaceGroup();
                    }
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = companion;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                composer2 = composerStartRestartGroup;
                modifier3 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerTitle$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i6) {
                        this.$tmp0_rcvr.m1759DateRangePickerTitlehOD91z4(i, modifier3, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i4 & 19) == 18) {
            if (i5 != 0) {
                companion = Modifier.INSTANCE;
            } else {
                companion = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1412719908, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerTitle (DateRangePicker.kt:338)");
            }
            if (DisplayMode.m1782equalsimpl0(i, DisplayMode.INSTANCE.m1787getPickerjFl4v0())) {
                composerStartRestartGroup.startReplaceGroup(980469064);
                ComposerKt.sourceInformation(composerStartRestartGroup, "341@15673L48,341@15668L75");
                Strings.Companion companion4 = Strings.INSTANCE;
                TextKt.m2411Text4IGK_g(Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_title), composerStartRestartGroup, 0), companion, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, i4 & 112, 0, 131068);
                composer2 = composerStartRestartGroup;
                composer2.endReplaceGroup();
            } else {
                composer2 = composerStartRestartGroup;
                if (DisplayMode.m1782equalsimpl0(i, DisplayMode.INSTANCE.m1786getInputjFl4v0())) {
                    composer2.startReplaceGroup(980473063);
                    ComposerKt.sourceInformation(composer2, "343@15798L47,343@15793L74");
                    Strings.Companion companion5 = Strings.INSTANCE;
                    TextKt.m2411Text4IGK_g(Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_input_title), composer2, 0), companion, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, i4 & 112, 0, 131068);
                    composer2.endReplaceGroup();
                } else {
                    composer2.startReplaceGroup(329974917);
                    composer2.endReplaceGroup();
                }
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = companion;
        } else {
            if (i5 != 0) {
                companion = Modifier.INSTANCE;
            } else {
                companion = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1412719908, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerTitle (DateRangePicker.kt:338)");
            }
            if (DisplayMode.m1782equalsimpl0(i, DisplayMode.INSTANCE.m1787getPickerjFl4v0())) {
                composerStartRestartGroup.startReplaceGroup(980469064);
                ComposerKt.sourceInformation(composerStartRestartGroup, "341@15673L48,341@15668L75");
                Strings.Companion companion6 = Strings.INSTANCE;
                TextKt.m2411Text4IGK_g(Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_title), composerStartRestartGroup, 0), companion, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, i4 & 112, 0, 131068);
                composer2 = composerStartRestartGroup;
                composer2.endReplaceGroup();
            } else {
                composer2 = composerStartRestartGroup;
                if (DisplayMode.m1782equalsimpl0(i, DisplayMode.INSTANCE.m1786getInputjFl4v0())) {
                    composer2.startReplaceGroup(980473063);
                    ComposerKt.sourceInformation(composer2, "343@15798L47,343@15793L74");
                    Strings.Companion companion7 = Strings.INSTANCE;
                    TextKt.m2411Text4IGK_g(Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_input_title), composer2, 0), companion, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, i4 & 112, 0, 131068);
                    composer2.endReplaceGroup();
                } else {
                    composer2.startReplaceGroup(329974917);
                    composer2.endReplaceGroup();
                }
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = companion;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerTitle$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i6) {
                    this.$tmp0_rcvr.m1759DateRangePickerTitlehOD91z4(i, modifier3, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0050  */
    /* JADX WARN: Code duplicated, block: B:27:0x0053  */
    /* JADX WARN: Code duplicated, block: B:29:0x0057  */
    /* JADX WARN: Code duplicated, block: B:31:0x005f  */
    /* JADX WARN: Code duplicated, block: B:32:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x006c  */
    /* JADX WARN: Code duplicated, block: B:38:0x006f  */
    /* JADX WARN: Code duplicated, block: B:40:0x0073  */
    /* JADX WARN: Code duplicated, block: B:42:0x0077  */
    /* JADX WARN: Code duplicated, block: B:43:0x007c  */
    /* JADX WARN: Code duplicated, block: B:45:0x0082  */
    /* JADX WARN: Code duplicated, block: B:46:0x0085  */
    /* JADX WARN: Code duplicated, block: B:50:0x008c  */
    /* JADX WARN: Code duplicated, block: B:51:0x008f  */
    /* JADX WARN: Code duplicated, block: B:53:0x0093  */
    /* JADX WARN: Code duplicated, block: B:55:0x009b  */
    /* JADX WARN: Code duplicated, block: B:56:0x009e  */
    /* JADX WARN: Code duplicated, block: B:61:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:67:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:72:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:77:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:83:0x0156  */
    /* JADX WARN: Code duplicated, block: B:87:0x0160  */
    /* JADX WARN: Code duplicated, block: B:89:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: DateRangePickerHeadline-v84Udv0, reason: not valid java name */
    public final void m1758DateRangePickerHeadlinev84Udv0(final Long l, final Long l2, final int i, final DatePickerFormatter datePickerFormatter, Modifier modifier, Composer composer, final int i2, final int i3) {
        Long l3;
        int i4;
        Long l4;
        int i5;
        int i6;
        boolean zChangedInstance;
        int i7;
        int i8;
        Modifier modifier2;
        int i9;
        DateRangePickerDefaults dateRangePickerDefaults;
        int i10;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1611069472);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DateRangePickerHeadline)P(4,3,1:c#material3.DisplayMode)367@16887L47,368@16961L45,377@17394L30,378@17459L28,369@17015L534:DateRangePicker.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
            l3 = l;
        } else if ((i2 & 6) == 0) {
            l3 = l;
            i4 = (composerStartRestartGroup.changed(l3) ? 4 : 2) | i2;
        } else {
            l3 = l;
            i4 = i2;
        }
        if ((i3 & 2) == 0) {
            if ((i2 & 48) == 0) {
                l4 = l2;
                i4 |= composerStartRestartGroup.changed(l4) ? 32 : 16;
            }
            if ((i3 & 4) != 0) {
                if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    i5 = i;
                    if (composerStartRestartGroup.changed(i5)) {
                        i6 = 256;
                    } else {
                        i6 = 128;
                    }
                    i4 |= i6;
                }
                if ((i3 & 8) != 0) {
                    i4 |= 3072;
                } else if ((i2 & 3072) == 0) {
                    if ((i2 & 4096) == 0) {
                        zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
                    } else {
                        zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
                    }
                    if (zChangedInstance) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i4 |= i7;
                }
                i8 = i3 & 16;
                if (i8 != 0) {
                    if ((i2 & 24576) == 0) {
                        modifier2 = modifier;
                        if (composerStartRestartGroup.changed(modifier2)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i4 |= i9;
                    }
                    if ((i3 & 32) != 0) {
                        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            dateRangePickerDefaults = this;
                            if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                                i10 = 131072;
                            } else {
                                i10 = 65536;
                            }
                            i4 |= i10;
                        }
                        if ((74899 & i4) == 74898 || !composerStartRestartGroup.getSkipping()) {
                            if (i8 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                            }
                            Strings.Companion companion = Strings.INSTANCE;
                            final String strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                            Strings.Companion companion2 = Strings.INSTANCE;
                            final String strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                            int i11 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                            int i12 = (i4 >> 15) & 14;
                            Long l5 = l4;
                            int i13 = i5;
                            Modifier modifier4 = modifier2;
                            dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l5, i13, datePickerFormatter, modifier4, strM2723getString2EP1pXo, strM2723getString2EP1pXo2, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
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
                                    ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                                    if ((i14 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(482821121, i14, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                                    }
                                    TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
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
                                    ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                                    if ((i14 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1522669758, i14, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                                    }
                                    TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo2, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i11, i12);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            modifier3 = modifier2;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
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
                                    this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                                }
                            });
                        }
                    }
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    dateRangePickerDefaults = this;
                    if ((74899 & i4) == 74898) {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                        }
                        Strings.Companion companion3 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo3 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                        Strings.Companion companion4 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo4 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                        int i14 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                        int i15 = (i4 >> 15) & 14;
                        Long l6 = l4;
                        int i16 = i5;
                        Modifier modifier5 = modifier2;
                        dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l6, i16, datePickerFormatter, modifier5, strM2723getString2EP1pXo3, strM2723getString2EP1pXo4, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
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
                                ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                                if ((i17 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(482821121, i17, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo3, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
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
                                ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                                if ((i17 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1522669758, i17, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo4, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i14, i15);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier5;
                    } else {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                        }
                        Strings.Companion companion5 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo5 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                        Strings.Companion companion6 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo6 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                        int i17 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                        int i18 = (i4 >> 15) & 14;
                        Long l7 = l4;
                        int i19 = i5;
                        Modifier modifier6 = modifier2;
                        dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l7, i19, datePickerFormatter, modifier6, strM2723getString2EP1pXo5, strM2723getString2EP1pXo6, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i110) {
                                ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                                if ((i110 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(482821121, i110, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo5, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i110) {
                                ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                                if ((i110 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1522669758, i110, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo6, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i17, i18);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier6;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i110) {
                                this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                            }
                        });
                    }
                }
                i4 |= 24576;
                modifier2 = modifier;
                if ((i3 & 32) != 0) {
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        dateRangePickerDefaults = this;
                        if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                            i10 = 131072;
                        } else {
                            i10 = 65536;
                        }
                        i4 |= i10;
                    }
                    if ((74899 & i4) == 74898) {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                        }
                        Strings.Companion companion7 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo7 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                        Strings.Companion companion8 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo8 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                        int i110 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                        int i111 = (i4 >> 15) & 14;
                        Long l8 = l4;
                        int i112 = i5;
                        Modifier modifier7 = modifier2;
                        dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l8, i112, datePickerFormatter, modifier7, strM2723getString2EP1pXo7, strM2723getString2EP1pXo8, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i113) {
                                ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                                if ((i113 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(482821121, i113, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo7, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i113) {
                                ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                                if ((i113 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1522669758, i113, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo8, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i110, i111);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier7;
                    } else {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                        }
                        Strings.Companion companion9 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo9 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                        Strings.Companion companion10 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo10 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                        int i113 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                        int i114 = (i4 >> 15) & 14;
                        Long l9 = l4;
                        int i115 = i5;
                        Modifier modifier8 = modifier2;
                        dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l9, i115, datePickerFormatter, modifier8, strM2723getString2EP1pXo9, strM2723getString2EP1pXo10, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i116) {
                                ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                                if ((i116 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(482821121, i116, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo9, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i116) {
                                ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                                if ((i116 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1522669758, i116, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo10, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i113, i114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier8;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i116) {
                                this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                            }
                        });
                    }
                }
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                dateRangePickerDefaults = this;
                if ((74899 & i4) == 74898) {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                    }
                    Strings.Companion companion11 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo11 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    Strings.Companion companion12 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo12 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    int i116 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                    int i117 = (i4 >> 15) & 14;
                    Long l10 = l4;
                    int i118 = i5;
                    Modifier modifier9 = modifier2;
                    dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l10, i118, datePickerFormatter, modifier9, strM2723getString2EP1pXo11, strM2723getString2EP1pXo12, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i119) {
                            ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                            if ((i119 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(482821121, i119, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo11, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i119) {
                            ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                            if ((i119 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1522669758, i119, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo12, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i116, i117);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier9;
                } else {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                    }
                    Strings.Companion companion13 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo13 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    Strings.Companion companion14 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo14 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    int i119 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                    int i1110 = (i4 >> 15) & 14;
                    Long l11 = l4;
                    int i1111 = i5;
                    Modifier modifier10 = modifier2;
                    dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l11, i1111, datePickerFormatter, modifier10, strM2723getString2EP1pXo13, strM2723getString2EP1pXo14, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i1112) {
                            ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                            if ((i1112 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(482821121, i1112, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo13, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i1112) {
                            ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                            if ((i1112 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1522669758, i1112, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo14, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i119, i1110);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier10;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i1112) {
                            this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            i5 = i;
            if ((i3 & 8) != 0) {
                i4 |= 3072;
            } else if ((i2 & 3072) == 0) {
                if ((i2 & 4096) == 0) {
                    zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
                } else {
                    zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
                }
                if (zChangedInstance) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i4 |= i7;
            }
            i8 = i3 & 16;
            if (i8 != 0) {
                if ((i2 & 24576) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i4 |= i9;
                }
                if ((i3 & 32) != 0) {
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        dateRangePickerDefaults = this;
                        if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                            i10 = 131072;
                        } else {
                            i10 = 65536;
                        }
                        i4 |= i10;
                    }
                    if ((74899 & i4) == 74898) {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                        }
                        Strings.Companion companion15 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo15 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                        Strings.Companion companion16 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo16 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                        int i1112 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                        int i1113 = (i4 >> 15) & 14;
                        Long l12 = l4;
                        int i1114 = i5;
                        Modifier modifier11 = modifier2;
                        dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l12, i1114, datePickerFormatter, modifier11, strM2723getString2EP1pXo15, strM2723getString2EP1pXo16, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i1115) {
                                ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                                if ((i1115 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(482821121, i1115, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo15, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i1115) {
                                ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                                if ((i1115 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1522669758, i1115, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo16, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i1112, i1113);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier11;
                    } else {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                        }
                        Strings.Companion companion17 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo17 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                        Strings.Companion companion18 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo18 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                        int i1115 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                        int i1116 = (i4 >> 15) & 14;
                        Long l13 = l4;
                        int i1117 = i5;
                        Modifier modifier12 = modifier2;
                        dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l13, i1117, datePickerFormatter, modifier12, strM2723getString2EP1pXo17, strM2723getString2EP1pXo18, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i1118) {
                                ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                                if ((i1118 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(482821121, i1118, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo17, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i1118) {
                                ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                                if ((i1118 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1522669758, i1118, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo18, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i1115, i1116);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier12;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i1118) {
                                this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                            }
                        });
                    }
                }
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                dateRangePickerDefaults = this;
                if ((74899 & i4) == 74898) {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                    }
                    Strings.Companion companion19 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo19 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    Strings.Companion companion110 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo110 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    int i1118 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                    int i1119 = (i4 >> 15) & 14;
                    Long l14 = l4;
                    int i11110 = i5;
                    Modifier modifier13 = modifier2;
                    dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l14, i11110, datePickerFormatter, modifier13, strM2723getString2EP1pXo19, strM2723getString2EP1pXo110, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11111) {
                            ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                            if ((i11111 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(482821121, i11111, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo19, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11111) {
                            ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                            if ((i11111 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1522669758, i11111, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo110, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i1118, i1119);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier13;
                } else {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                    }
                    Strings.Companion companion111 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo111 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    Strings.Companion companion112 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo112 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    int i11111 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                    int i11112 = (i4 >> 15) & 14;
                    Long l15 = l4;
                    int i11113 = i5;
                    Modifier modifier14 = modifier2;
                    dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l15, i11113, datePickerFormatter, modifier14, strM2723getString2EP1pXo111, strM2723getString2EP1pXo112, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11114) {
                            ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                            if ((i11114 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(482821121, i11114, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo111, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11114) {
                            ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                            if ((i11114 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1522669758, i11114, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo112, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i11111, i11112);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier14;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11114) {
                            this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 24576;
            modifier2 = modifier;
            if ((i3 & 32) != 0) {
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    dateRangePickerDefaults = this;
                    if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                        i10 = 131072;
                    } else {
                        i10 = 65536;
                    }
                    i4 |= i10;
                }
                if ((74899 & i4) == 74898) {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                    }
                    Strings.Companion companion113 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo113 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    Strings.Companion companion114 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo114 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    int i11114 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                    int i11115 = (i4 >> 15) & 14;
                    Long l16 = l4;
                    int i11116 = i5;
                    Modifier modifier15 = modifier2;
                    dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l16, i11116, datePickerFormatter, modifier15, strM2723getString2EP1pXo113, strM2723getString2EP1pXo114, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11117) {
                            ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                            if ((i11117 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(482821121, i11117, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo113, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11117) {
                            ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                            if ((i11117 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1522669758, i11117, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo114, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i11114, i11115);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier15;
                } else {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                    }
                    Strings.Companion companion115 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo115 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    Strings.Companion companion116 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo116 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    int i11117 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                    int i11118 = (i4 >> 15) & 14;
                    Long l17 = l4;
                    int i11119 = i5;
                    Modifier modifier16 = modifier2;
                    dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l17, i11119, datePickerFormatter, modifier16, strM2723getString2EP1pXo115, strM2723getString2EP1pXo116, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i111110) {
                            ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                            if ((i111110 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(482821121, i111110, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo115, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i111110) {
                            ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                            if ((i111110 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1522669758, i111110, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo116, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i11117, i11118);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier16;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i111110) {
                            this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            dateRangePickerDefaults = this;
            if ((74899 & i4) == 74898) {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                }
                Strings.Companion companion117 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo117 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                Strings.Companion companion118 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo118 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                int i111110 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                int i111111 = (i4 >> 15) & 14;
                Long l18 = l4;
                int i111112 = i5;
                Modifier modifier17 = modifier2;
                dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l18, i111112, datePickerFormatter, modifier17, strM2723getString2EP1pXo117, strM2723getString2EP1pXo118, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i111113) {
                        ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                        if ((i111113 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(482821121, i111113, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo117, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i111113) {
                        ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                        if ((i111113 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1522669758, i111113, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo118, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i111110, i111111);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier17;
            } else {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                }
                Strings.Companion companion119 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo119 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                Strings.Companion companion1110 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo1110 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                int i111113 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                int i111114 = (i4 >> 15) & 14;
                Long l19 = l4;
                int i111115 = i5;
                Modifier modifier18 = modifier2;
                dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l19, i111115, datePickerFormatter, modifier18, strM2723getString2EP1pXo119, strM2723getString2EP1pXo1110, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i111116) {
                        ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                        if ((i111116 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(482821121, i111116, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo119, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i111116) {
                        ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                        if ((i111116 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1522669758, i111116, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo1110, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i111113, i111114);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier18;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i111116) {
                        this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 48;
        l4 = l2;
        if ((i3 & 4) != 0) {
            if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                i5 = i;
                if (composerStartRestartGroup.changed(i5)) {
                    i6 = 256;
                } else {
                    i6 = 128;
                }
                i4 |= i6;
            }
            if ((i3 & 8) != 0) {
                i4 |= 3072;
            } else if ((i2 & 3072) == 0) {
                if ((i2 & 4096) == 0) {
                    zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
                } else {
                    zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
                }
                if (zChangedInstance) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i4 |= i7;
            }
            i8 = i3 & 16;
            if (i8 != 0) {
                if ((i2 & 24576) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i4 |= i9;
                }
                if ((i3 & 32) != 0) {
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        dateRangePickerDefaults = this;
                        if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                            i10 = 131072;
                        } else {
                            i10 = 65536;
                        }
                        i4 |= i10;
                    }
                    if ((74899 & i4) == 74898) {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                        }
                        Strings.Companion companion1111 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo1111 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                        Strings.Companion companion1112 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo1112 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                        int i111116 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                        int i111117 = (i4 >> 15) & 14;
                        Long l110 = l4;
                        int i111118 = i5;
                        Modifier modifier19 = modifier2;
                        dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l110, i111118, datePickerFormatter, modifier19, strM2723getString2EP1pXo1111, strM2723getString2EP1pXo1112, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i111119) {
                                ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                                if ((i111119 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(482821121, i111119, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo1111, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i111119) {
                                ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                                if ((i111119 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1522669758, i111119, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo1112, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i111116, i111117);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier19;
                    } else {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                        }
                        Strings.Companion companion1113 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo1113 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                        Strings.Companion companion1114 = Strings.INSTANCE;
                        final String strM2723getString2EP1pXo1114 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                        int i111119 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                        int i1111110 = (i4 >> 15) & 14;
                        Long l111 = l4;
                        int i1111111 = i5;
                        Modifier modifier110 = modifier2;
                        dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l111, i1111111, datePickerFormatter, modifier110, strM2723getString2EP1pXo1113, strM2723getString2EP1pXo1114, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i1111112) {
                                ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                                if ((i1111112 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(482821121, i1111112, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo1113, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i1111112) {
                                ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                                if ((i1111112 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1522669758, i1111112, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                                }
                                TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo1114, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i111119, i1111110);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier110;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i1111112) {
                                this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                            }
                        });
                    }
                }
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                dateRangePickerDefaults = this;
                if ((74899 & i4) == 74898) {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                    }
                    Strings.Companion companion1115 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo1115 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    Strings.Companion companion1116 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo1116 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    int i1111112 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                    int i1111113 = (i4 >> 15) & 14;
                    Long l112 = l4;
                    int i1111114 = i5;
                    Modifier modifier111 = modifier2;
                    dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l112, i1111114, datePickerFormatter, modifier111, strM2723getString2EP1pXo1115, strM2723getString2EP1pXo1116, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i1111115) {
                            ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                            if ((i1111115 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(482821121, i1111115, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo1115, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i1111115) {
                            ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                            if ((i1111115 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1522669758, i1111115, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo1116, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i1111112, i1111113);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier111;
                } else {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                    }
                    Strings.Companion companion1117 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo1117 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    Strings.Companion companion1118 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo1118 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    int i1111115 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                    int i1111116 = (i4 >> 15) & 14;
                    Long l113 = l4;
                    int i1111117 = i5;
                    Modifier modifier112 = modifier2;
                    dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l113, i1111117, datePickerFormatter, modifier112, strM2723getString2EP1pXo1117, strM2723getString2EP1pXo1118, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i1111118) {
                            ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                            if ((i1111118 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(482821121, i1111118, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo1117, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i1111118) {
                            ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                            if ((i1111118 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1522669758, i1111118, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo1118, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i1111115, i1111116);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier112;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i1111118) {
                            this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 24576;
            modifier2 = modifier;
            if ((i3 & 32) != 0) {
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    dateRangePickerDefaults = this;
                    if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                        i10 = 131072;
                    } else {
                        i10 = 65536;
                    }
                    i4 |= i10;
                }
                if ((74899 & i4) == 74898) {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                    }
                    Strings.Companion companion1119 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo1119 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    Strings.Companion companion11110 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo11110 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    int i1111118 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                    int i1111119 = (i4 >> 15) & 14;
                    Long l114 = l4;
                    int i11111110 = i5;
                    Modifier modifier113 = modifier2;
                    dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l114, i11111110, datePickerFormatter, modifier113, strM2723getString2EP1pXo1119, strM2723getString2EP1pXo11110, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11111111) {
                            ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                            if ((i11111111 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(482821121, i11111111, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo1119, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11111111) {
                            ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                            if ((i11111111 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1522669758, i11111111, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo11110, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i1111118, i1111119);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier113;
                } else {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                    }
                    Strings.Companion companion11111 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo11111 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    Strings.Companion companion11112 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo11112 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    int i11111111 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                    int i11111112 = (i4 >> 15) & 14;
                    Long l115 = l4;
                    int i11111113 = i5;
                    Modifier modifier114 = modifier2;
                    dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l115, i11111113, datePickerFormatter, modifier114, strM2723getString2EP1pXo11111, strM2723getString2EP1pXo11112, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11111114) {
                            ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                            if ((i11111114 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(482821121, i11111114, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo11111, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11111114) {
                            ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                            if ((i11111114 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1522669758, i11111114, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo11112, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i11111111, i11111112);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier114;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11111114) {
                            this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            dateRangePickerDefaults = this;
            if ((74899 & i4) == 74898) {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                }
                Strings.Companion companion11113 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo11113 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                Strings.Companion companion11114 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo11114 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                int i11111114 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                int i11111115 = (i4 >> 15) & 14;
                Long l116 = l4;
                int i11111116 = i5;
                Modifier modifier115 = modifier2;
                dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l116, i11111116, datePickerFormatter, modifier115, strM2723getString2EP1pXo11113, strM2723getString2EP1pXo11114, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i11111117) {
                        ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                        if ((i11111117 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(482821121, i11111117, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo11113, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i11111117) {
                        ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                        if ((i11111117 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1522669758, i11111117, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo11114, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i11111114, i11111115);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier115;
            } else {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                }
                Strings.Companion companion11115 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo11115 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                Strings.Companion companion11116 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo11116 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                int i11111117 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                int i11111118 = (i4 >> 15) & 14;
                Long l117 = l4;
                int i11111119 = i5;
                Modifier modifier116 = modifier2;
                dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l117, i11111119, datePickerFormatter, modifier116, strM2723getString2EP1pXo11115, strM2723getString2EP1pXo11116, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i111111110) {
                        ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                        if ((i111111110 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(482821121, i111111110, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo11115, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i111111110) {
                        ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                        if ((i111111110 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1522669758, i111111110, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo11116, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i11111117, i11111118);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier116;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i111111110) {
                        this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        i5 = i;
        if ((i3 & 8) != 0) {
            i4 |= 3072;
        } else if ((i2 & 3072) == 0) {
            if ((i2 & 4096) == 0) {
                zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
            } else {
                zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
            }
            if (zChangedInstance) {
                i7 = 2048;
            } else {
                i7 = 1024;
            }
            i4 |= i7;
        }
        i8 = i3 & 16;
        if (i8 != 0) {
            if ((i2 & 24576) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i9 = 16384;
                } else {
                    i9 = 8192;
                }
                i4 |= i9;
            }
            if ((i3 & 32) != 0) {
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    dateRangePickerDefaults = this;
                    if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                        i10 = 131072;
                    } else {
                        i10 = 65536;
                    }
                    i4 |= i10;
                }
                if ((74899 & i4) == 74898) {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                    }
                    Strings.Companion companion11117 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo11117 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    Strings.Companion companion11118 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo11118 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    int i111111110 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                    int i111111111 = (i4 >> 15) & 14;
                    Long l118 = l4;
                    int i111111112 = i5;
                    Modifier modifier117 = modifier2;
                    dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l118, i111111112, datePickerFormatter, modifier117, strM2723getString2EP1pXo11117, strM2723getString2EP1pXo11118, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i111111113) {
                            ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                            if ((i111111113 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(482821121, i111111113, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo11117, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i111111113) {
                            ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                            if ((i111111113 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1522669758, i111111113, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo11118, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i111111110, i111111111);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier117;
                } else {
                    if (i8 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                    }
                    Strings.Companion companion11119 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo11119 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    Strings.Companion companion111110 = Strings.INSTANCE;
                    final String strM2723getString2EP1pXo111110 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    int i111111113 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                    int i111111114 = (i4 >> 15) & 14;
                    Long l119 = l4;
                    int i111111115 = i5;
                    Modifier modifier118 = modifier2;
                    dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l119, i111111115, datePickerFormatter, modifier118, strM2723getString2EP1pXo11119, strM2723getString2EP1pXo111110, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i111111116) {
                            ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                            if ((i111111116 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(482821121, i111111116, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo11119, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i111111116) {
                            ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                            if ((i111111116 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1522669758, i111111116, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                            }
                            TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo111110, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i111111113, i111111114);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier118;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i111111116) {
                            this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            dateRangePickerDefaults = this;
            if ((74899 & i4) == 74898) {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                }
                Strings.Companion companion111111 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo111111 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                Strings.Companion companion111112 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo111112 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                int i111111116 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                int i111111117 = (i4 >> 15) & 14;
                Long l1110 = l4;
                int i111111118 = i5;
                Modifier modifier119 = modifier2;
                dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l1110, i111111118, datePickerFormatter, modifier119, strM2723getString2EP1pXo111111, strM2723getString2EP1pXo111112, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i111111119) {
                        ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                        if ((i111111119 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(482821121, i111111119, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo111111, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i111111119) {
                        ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                        if ((i111111119 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1522669758, i111111119, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo111112, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i111111116, i111111117);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier119;
            } else {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                }
                Strings.Companion companion111113 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo111113 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                Strings.Companion companion111114 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo111114 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                int i111111119 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                int i1111111110 = (i4 >> 15) & 14;
                Long l1111 = l4;
                int i1111111111 = i5;
                Modifier modifier1110 = modifier2;
                dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l1111, i1111111111, datePickerFormatter, modifier1110, strM2723getString2EP1pXo111113, strM2723getString2EP1pXo111114, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i1111111112) {
                        ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                        if ((i1111111112 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(482821121, i1111111112, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo111113, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i1111111112) {
                        ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                        if ((i1111111112 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1522669758, i1111111112, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo111114, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i111111119, i1111111110);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier1110;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i1111111112) {
                        this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 24576;
        modifier2 = modifier;
        if ((i3 & 32) != 0) {
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                dateRangePickerDefaults = this;
                if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                    i10 = 131072;
                } else {
                    i10 = 65536;
                }
                i4 |= i10;
            }
            if ((74899 & i4) == 74898) {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                }
                Strings.Companion companion111115 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo111115 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                Strings.Companion companion111116 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo111116 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                int i1111111112 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                int i1111111113 = (i4 >> 15) & 14;
                Long l1112 = l4;
                int i1111111114 = i5;
                Modifier modifier1111 = modifier2;
                dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l1112, i1111111114, datePickerFormatter, modifier1111, strM2723getString2EP1pXo111115, strM2723getString2EP1pXo111116, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i1111111115) {
                        ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                        if ((i1111111115 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(482821121, i1111111115, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo111115, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i1111111115) {
                        ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                        if ((i1111111115 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1522669758, i1111111115, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo111116, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i1111111112, i1111111113);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier1111;
            } else {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
                }
                Strings.Companion companion111117 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo111117 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                Strings.Companion companion111118 = Strings.INSTANCE;
                final String strM2723getString2EP1pXo111118 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                int i1111111115 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
                int i1111111116 = (i4 >> 15) & 14;
                Long l1113 = l4;
                int i1111111117 = i5;
                Modifier modifier1112 = modifier2;
                dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l1113, i1111111117, datePickerFormatter, modifier1112, strM2723getString2EP1pXo111117, strM2723getString2EP1pXo111118, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i1111111118) {
                        ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                        if ((i1111111118 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(482821121, i1111111118, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo111117, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i1111111118) {
                        ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                        if ((i1111111118 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1522669758, i1111111118, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                        }
                        TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo111118, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i1111111115, i1111111116);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier1112;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i1111111118) {
                        this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        dateRangePickerDefaults = this;
        if ((74899 & i4) == 74898) {
            if (i8 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
            }
            Strings.Companion companion111119 = Strings.INSTANCE;
            final String strM2723getString2EP1pXo111119 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
            Strings.Companion companion1111110 = Strings.INSTANCE;
            final String strM2723getString2EP1pXo1111110 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
            int i1111111118 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
            int i1111111119 = (i4 >> 15) & 14;
            Long l1114 = l4;
            int i11111111110 = i5;
            Modifier modifier1113 = modifier2;
            dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l1114, i11111111110, datePickerFormatter, modifier1113, strM2723getString2EP1pXo111119, strM2723getString2EP1pXo1111110, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i11111111111) {
                    ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                    if ((i11111111111 & 3) == 2 && composer2.getSkipping()) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(482821121, i11111111111, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                    }
                    TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo111119, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i11111111111) {
                    ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                    if ((i11111111111 & 3) == 2 && composer2.getSkipping()) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1522669758, i11111111111, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                    }
                    TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo1111110, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i1111111118, i1111111119);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier1113;
        } else {
            if (i8 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1611069472, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:366)");
            }
            Strings.Companion companion1111111 = Strings.INSTANCE;
            final String strM2723getString2EP1pXo1111111 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
            Strings.Companion companion1111112 = Strings.INSTANCE;
            final String strM2723getString2EP1pXo1111112 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
            int i11111111111 = (i4 & 14) | 918552576 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4);
            int i11111111112 = (i4 >> 15) & 14;
            Long l1115 = l4;
            int i11111111113 = i5;
            Modifier modifier1114 = modifier2;
            dateRangePickerDefaults.m1756DateRangePickerHeadline0YIUgSQ(l3, l1115, i11111111113, datePickerFormatter, modifier1114, strM2723getString2EP1pXo1111111, strM2723getString2EP1pXo1111112, ComposableLambdaKt.rememberComposableLambda(482821121, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i11111111114) {
                    ComposerKt.sourceInformation(composer2, "C377@17396L26:DateRangePicker.kt#uh7d8r");
                    if ((i11111111114 & 3) == 2 && composer2.getSkipping()) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(482821121, i11111111114, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:377)");
                    }
                    TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo1111111, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1522669758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i11111111114) {
                    ComposerKt.sourceInformation(composer2, "C378@17461L24:DateRangePicker.kt#uh7d8r");
                    if ((i11111111114 & 3) == 2 && composer2.getSkipping()) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1522669758, i11111111114, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:378)");
                    }
                    TextKt.m2411Text4IGK_g(strM2723getString2EP1pXo1111112, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), ComposableSingletons$DateRangePickerKt.INSTANCE.m1682getLambda1$material3_release(), composerStartRestartGroup, i11111111111, i11111111112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier1114;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i11111111114) {
                    this.$tmp0_rcvr.m1758DateRangePickerHeadlinev84Udv0(l, l2, i, datePickerFormatter, modifier3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: DateRangePickerHeadline-0YIUgSQ, reason: not valid java name */
    public final void m1756DateRangePickerHeadline0YIUgSQ(Long l, final Long l2, final int i, final DatePickerFormatter datePickerFormatter, final Modifier modifier, final String str, final String str2, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, Composer composer, final int i2, final int i3) {
        int i4;
        Composer composer2;
        Long l3;
        String str3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-820363420);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DateRangePickerHeadline)P(7,6,2:c#material3.DisplayMode!1,5,9,4,8,3)420@19570L15,456@21103L168,454@21029L748:DateRangePicker.kt#uh7d8r");
        if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(l) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(l2) ? 32 : 16;
        }
        if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i4 |= composerStartRestartGroup.changed(i) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i4 |= (i2 & 4096) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i4 |= composerStartRestartGroup.changed(modifier) ? 16384 : 8192;
        }
        if ((196608 & i2) == 0) {
            i4 |= composerStartRestartGroup.changed(str) ? 131072 : 65536;
        }
        if ((1572864 & i2) == 0) {
            i4 |= composerStartRestartGroup.changed(str2) ? 1048576 : 524288;
        }
        if ((12582912 & i2) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function2) ? 8388608 : 4194304;
        }
        if ((100663296 & i2) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function3) ? 67108864 : 33554432;
        }
        if ((805306368 & i2) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function4) ? 536870912 : 268435456;
        }
        if ((306783379 & i4) != 306783378 || (i3 & 1) != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-820363420, i4, i3, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:419)");
            }
            Locale localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
            int i5 = i4;
            composer2 = composerStartRestartGroup;
            String date$default = DatePickerFormatter.CC.formatDate$default(datePickerFormatter, l, localeDefaultLocale, false, 4, null);
            l3 = l;
            String date$default2 = DatePickerFormatter.CC.formatDate$default(datePickerFormatter, l2, localeDefaultLocale, false, 4, null);
            String date = datePickerFormatter.formatDate(l3, localeDefaultLocale, true);
            composer2.startReplaceGroup(1063158288);
            String strM2723getString2EP1pXo = "";
            ComposerKt.sourceInformation(composer2, "");
            if (date != null) {
                str3 = date$default2;
            } else if (DisplayMode.m1782equalsimpl0(i, DisplayMode.INSTANCE.m1787getPickerjFl4v0())) {
                composer2.startReplaceGroup(1063166242);
                ComposerKt.sourceInformation(composer2, "434@20163L51");
                Strings.Companion companion = Strings.INSTANCE;
                str3 = date$default2;
                date = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_picker_no_selection_description), composer2, 0);
                composer2.endReplaceGroup();
            } else {
                str3 = date$default2;
                if (DisplayMode.m1782equalsimpl0(i, DisplayMode.INSTANCE.m1786getInputjFl4v0())) {
                    composer2.startReplaceGroup(1063169213);
                    ComposerKt.sourceInformation(composer2, "435@20256L46");
                    Strings.Companion companion2 = Strings.INSTANCE;
                    String strM2723getString2EP1pXo2 = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_input_no_input_description), composer2, 0);
                    composer2.endReplaceGroup();
                    date = strM2723getString2EP1pXo2;
                } else {
                    composer2.startReplaceGroup(-1401419729);
                    composer2.endReplaceGroup();
                    date = "";
                }
            }
            composer2.endReplaceGroup();
            String date2 = datePickerFormatter.formatDate(l2, localeDefaultLocale, true);
            composer2.startReplaceGroup(1063174382);
            ComposerKt.sourceInformation(composer2, "");
            if (date2 != null) {
                strM2723getString2EP1pXo = date2;
            } else if (DisplayMode.m1782equalsimpl0(i, DisplayMode.INSTANCE.m1787getPickerjFl4v0())) {
                composer2.startReplaceGroup(1063182274);
                ComposerKt.sourceInformation(composer2, "446@20664L51");
                Strings.Companion companion3 = Strings.INSTANCE;
                strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_picker_no_selection_description), composer2, 0);
                composer2.endReplaceGroup();
            } else if (DisplayMode.m1782equalsimpl0(i, DisplayMode.INSTANCE.m1786getInputjFl4v0())) {
                composer2.startReplaceGroup(1063185245);
                ComposerKt.sourceInformation(composer2, "447@20757L46");
                Strings.Companion companion4 = Strings.INSTANCE;
                strM2723getString2EP1pXo = Strings_androidKt.m2723getString2EP1pXo(Strings.m2653constructorimpl(R.string.m3c_date_input_no_input_description), composer2, 0);
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(-1400922737);
                composer2.endReplaceGroup();
            }
            composer2.endReplaceGroup();
            final String str4 = str + ": " + date;
            final String str5 = str2 + ": " + strM2723getString2EP1pXo;
            ComposerKt.sourceInformationMarkerStart(composer2, 1063196439, "CC(remember):DateRangePicker.kt#9igjgp");
            boolean zChanged = composer2.changed(str4) | composer2.changed(str5);
            Object objRememberedValue = composer2.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$4$1
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
                        SemanticsPropertiesKt.m6133setLiveRegionhR3wRGc(semanticsPropertyReceiver, LiveRegionMode.INSTANCE.m6107getPolite0phEisY());
                        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str4 + ", " + str5);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(modifier, (Function1) objRememberedValue);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM596spacedBy0680j_4 = Arrangement.INSTANCE.m596spacedBy0680j_4(Dp.m6890constructorimpl(4.0f));
            ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM596spacedBy0680j_4, centerVertically, composer2, 54);
            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierClearAndSetSemantics);
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
            ComposerKt.sourceInformationMarkerStart(composer2, 303513083, "C468@21589L16:DateRangePicker.kt#uh7d8r");
            if (date$default != null) {
                composer2.startReplaceGroup(303536053);
                ComposerKt.sourceInformation(composer2, "464@21471L31");
                TextKt.m2411Text4IGK_g(date$default, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(303604222);
                ComposerKt.sourceInformation(composer2, "466@21540L22");
                function2.invoke(composer2, Integer.valueOf((i5 >> 21) & 14));
                composer2.endReplaceGroup();
            }
            function4.invoke(composer2, Integer.valueOf((i5 >> 27) & 14));
            if (str3 != null) {
                composer2.startReplaceGroup(303729431);
                ComposerKt.sourceInformation(composer2, "470@21666L29");
                TextKt.m2411Text4IGK_g(str3, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(303795616);
                ComposerKt.sourceInformation(composer2, "472@21733L20");
                function3.invoke(composer2, Integer.valueOf((i5 >> 24) & 14));
                composer2.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            l3 = l;
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Long l4 = l3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$6
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
                    this.$tmp7_rcvr.m1756DateRangePickerHeadline0YIUgSQ(l4, l2, i, datePickerFormatter, modifier, str, str2, function2, function3, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
                }
            });
        }
    }
}
