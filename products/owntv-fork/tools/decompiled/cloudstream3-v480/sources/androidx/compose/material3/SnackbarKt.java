package androidx.compose.material3;

import androidx.compose.foundation.layout.AlignmentLineKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.tokens.SnackbarTokens;
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
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Snackbar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001ae\u0010\n\u001a\u00020\u000b2\u0011\u0010\f\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u000e2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u000e2\u0013\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001ag\u0010\u0018\u001a\u00020\u000b2\u0011\u0010\f\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u000e2\u0013\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u000e2\u0013\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0014H\u0003ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u0017\u001aj\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u00142\b\b\u0002\u0010&\u001a\u00020\u00142\b\b\u0002\u0010'\u001a\u00020\u00142\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010)\u001a\u0099\u0001\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020 2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u000e2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u000e2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u00142\b\b\u0002\u0010&\u001a\u00020\u00142\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u0011\u0010*\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010,\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006-"}, d2 = {"ContainerMaxWidth", "Landroidx/compose/ui/unit/Dp;", "F", "HeightToFirstLine", "HorizontalSpacing", "HorizontalSpacingButtonSide", "LongButtonVerticalOffset", "SeparateButtonExtraY", "SnackbarVerticalPadding", "TextEndExtraSpacing", "NewLineButtonSnackbar", "", "text", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "action", "dismissAction", "actionTextStyle", "Landroidx/compose/ui/text/TextStyle;", "actionContentColor", "Landroidx/compose/ui/graphics/Color;", "dismissActionContentColor", "NewLineButtonSnackbar-kKq0p4A", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;JJLandroidx/compose/runtime/Composer;I)V", "OneRowSnackbar", "actionTextColor", "dismissActionColor", "OneRowSnackbar-kKq0p4A", "Snackbar", "snackbarData", "Landroidx/compose/material3/SnackbarData;", "modifier", "Landroidx/compose/ui/Modifier;", "actionOnNewLine", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "contentColor", "actionColor", "Snackbar-sDKtq54", "(Landroidx/compose/material3/SnackbarData;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJJJJLandroidx/compose/runtime/Composer;II)V", "content", "Snackbar-eQBnUkQ", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/graphics/Shape;JJJJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SnackbarKt {
    private static final float ContainerMaxWidth = Dp.m6890constructorimpl(600.0f);
    private static final float HeightToFirstLine = Dp.m6890constructorimpl(30.0f);
    private static final float HorizontalSpacing = Dp.m6890constructorimpl(16.0f);
    private static final float HorizontalSpacingButtonSide = Dp.m6890constructorimpl(8.0f);
    private static final float SeparateButtonExtraY = Dp.m6890constructorimpl(2.0f);
    private static final float SnackbarVerticalPadding = Dp.m6890constructorimpl(6.0f);
    private static final float TextEndExtraSpacing = Dp.m6890constructorimpl(8.0f);
    private static final float LongButtonVerticalOffset = Dp.m6890constructorimpl(12.0f);

    /* JADX WARN: Code duplicated, block: B:100:0x0117  */
    /* JADX WARN: Code duplicated, block: B:101:0x011a  */
    /* JADX WARN: Code duplicated, block: B:103:0x011e  */
    /* JADX WARN: Code duplicated, block: B:105:0x0126  */
    /* JADX WARN: Code duplicated, block: B:106:0x0129  */
    /* JADX WARN: Code duplicated, block: B:111:0x013a  */
    /* JADX WARN: Code duplicated, block: B:115:0x0154  */
    /* JADX WARN: Code duplicated, block: B:117:0x016a  */
    /* JADX WARN: Code duplicated, block: B:136:0x019f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:137:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:138:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:141:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:144:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:146:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:147:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:150:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:151:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:154:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:155:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:158:0x01d9  */
    /* JADX WARN: Code duplicated, block: B:159:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:162:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:163:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:166:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:167:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:170:0x020a  */
    /* JADX WARN: Code duplicated, block: B:173:0x026d  */
    /* JADX WARN: Code duplicated, block: B:177:0x0281  */
    /* JADX WARN: Code duplicated, block: B:179:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0050  */
    /* JADX WARN: Code duplicated, block: B:27:0x0053  */
    /* JADX WARN: Code duplicated, block: B:29:0x0057  */
    /* JADX WARN: Code duplicated, block: B:31:0x005f  */
    /* JADX WARN: Code duplicated, block: B:32:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x006c  */
    /* JADX WARN: Code duplicated, block: B:38:0x006f  */
    /* JADX WARN: Code duplicated, block: B:40:0x0073  */
    /* JADX WARN: Code duplicated, block: B:42:0x007b  */
    /* JADX WARN: Code duplicated, block: B:43:0x007e  */
    /* JADX WARN: Code duplicated, block: B:48:0x0088  */
    /* JADX WARN: Code duplicated, block: B:50:0x008c  */
    /* JADX WARN: Code duplicated, block: B:52:0x0094  */
    /* JADX WARN: Code duplicated, block: B:53:0x0097  */
    /* JADX WARN: Code duplicated, block: B:56:0x009d  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:72:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:90:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:92:0x0102  */
    /* JADX WARN: Code duplicated, block: B:95:0x010b  */
    /* JADX WARN: Code duplicated, block: B:97:0x010f  */
    /* JADX INFO: renamed from: Snackbar-eQBnUkQ, reason: not valid java name */
    public static final void m2246SnackbareQBnUkQ(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, boolean z, Shape shape, long j, long j2, long j3, long j4, final Function2<? super Composer, ? super Integer, Unit> function4, Composer composer, final int i, final int i2) {
        int i3;
        Function2<? super Composer, ? super Integer, Unit> function5;
        int i4;
        Function2<? super Composer, ? super Integer, Unit> function6;
        int i5;
        int i6;
        boolean z2;
        int i7;
        Shape shape2;
        long j5;
        int i8;
        final Modifier.Companion companion;
        Function2<? super Composer, ? super Integer, Unit> function7;
        boolean z3;
        Shape shape3;
        long color;
        long contentColor;
        long actionContentColor;
        long dismissActionContentColor;
        Composer composer2;
        final boolean z4;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Shape shape4;
        final long j6;
        final long j7;
        final long j8;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final long j9;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i9;
        int i10;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1235788955);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Snackbar)P(8!1,6,2,9,3:c#ui.graphics.Color,5:c#ui.graphics.Color,1:c#ui.graphics.Color,7:c#ui.graphics.Color)107@5066L5,108@5118L5,109@5168L12,110@5231L18,111@5307L25,120@5574L1123,114@5378L1319:Snackbar.kt#uh7d8r");
        int i12 = i2 & 1;
        if (i12 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i13 = i2 & 2;
        if (i13 == 0) {
            if ((i & 48) == 0) {
                function5 = function2;
                i3 |= composerStartRestartGroup.changedInstance(function5) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    function6 = function3;
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 8;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    if ((i & 24576) == 0) {
                        if ((i2 & 16) == 0) {
                            shape2 = shape;
                            int i14 = composerStartRestartGroup.changed(shape2) ? 16384 : 8192;
                            i3 |= i14;
                        } else {
                            shape2 = shape;
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    if ((196608 & i) == 0) {
                        if ((i2 & 32) == 0) {
                            j5 = j;
                            int i15 = composerStartRestartGroup.changed(j5) ? 131072 : 65536;
                            i3 |= i15;
                        } else {
                            j5 = j;
                        }
                        i3 |= i15;
                    } else {
                        j5 = j;
                    }
                    if ((i & 1572864) != 0) {
                        if ((i2 & 64) == 0 || !composerStartRestartGroup.changed(j2)) {
                            i11 = 524288;
                        } else {
                            i11 = 1048576;
                        }
                        i3 |= i11;
                    }
                    if ((i & 12582912) != 0) {
                        if ((i2 & 128) == 0 || !composerStartRestartGroup.changed(j3)) {
                            i10 = 4194304;
                        } else {
                            i10 = 8388608;
                        }
                        i3 |= i10;
                    }
                    if ((100663296 & i) != 0) {
                        if ((i2 & 256) == 0 || !composerStartRestartGroup.changed(j4)) {
                            i9 = 33554432;
                        } else {
                            i9 = 67108864;
                        }
                        i3 |= i9;
                    }
                    if ((i2 & 512) != 0) {
                        if ((i & BaseAdapter.HEADER) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i8 = 536870912;
                            } else {
                                i8 = 268435456;
                            }
                            i3 |= i8;
                        }
                        if ((i3 & 306783379) == 306783378 || !composerStartRestartGroup.getSkipping()) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i12 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier;
                                }
                                if (i13 != 0) {
                                    function5 = null;
                                }
                                function7 = i4 == 0 ? function6 : null;
                                if (i6 != 0) {
                                    z3 = false;
                                } else {
                                    z3 = z2;
                                }
                                if ((i2 & 16) != 0) {
                                    shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    i3 &= -57345;
                                } else {
                                    shape3 = shape2;
                                }
                                if ((i2 & 32) != 0) {
                                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                    i3 &= -458753;
                                } else {
                                    color = j5;
                                }
                                if ((i2 & 64) != 0) {
                                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                    i3 &= -3670017;
                                } else {
                                    contentColor = j2;
                                }
                                if ((i2 & 128) != 0) {
                                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                    i3 = (-29360129) & i3;
                                } else {
                                    actionContentColor = j3;
                                }
                                if ((i2 & 256) != 0) {
                                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                    i3 &= -234881025;
                                } else {
                                    dismissActionContentColor = j4;
                                }
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
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
                                companion = modifier;
                                actionContentColor = j3;
                                dismissActionContentColor = j4;
                                function7 = function6;
                                z3 = z2;
                                shape3 = shape2;
                                color = j5;
                                contentColor = j2;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                            }
                            final boolean z5 = z3;
                            final Function2<? super Composer, ? super Integer, Unit> function10 = function5;
                            final long j10 = actionContentColor;
                            final Function2<? super Composer, ? super Integer, Unit> function11 = function7;
                            final long j11 = dismissActionContentColor;
                            int i16 = (i3 & 14) | 12779520;
                            int i17 = i3 >> 9;
                            SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                                    ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                                    if ((i18 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1829663446, i18, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                        }
                                        TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                        final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                        ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                        final boolean z6 = z5;
                                        final Function2<Composer, Integer, Unit> function12 = function10;
                                        final Function2<Composer, Integer, Unit> function13 = function4;
                                        final Function2<Composer, Integer, Unit> function14 = function11;
                                        final long j12 = j10;
                                        final long j13 = j11;
                                        CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                            public final void invoke(Composer composer4, int i19) {
                                                ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                                if ((i19 & 3) != 2 || !composer4.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(835891690, i19, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                                    }
                                                    if (z6 && function12 != null) {
                                                        composer4.startReplaceGroup(-810715387);
                                                        ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                        SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function13, function12, function14, value2, j12, j13, composer4, 0);
                                                        composer4.endReplaceGroup();
                                                    } else {
                                                        composer4.startReplaceGroup(-810701708);
                                                        ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                        SnackbarKt.m2245OneRowSnackbarkKq0p4A(function13, function12, function14, value2, j12, j13, composer4, 0);
                                                        composer4.endReplaceGroup();
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer4.skipToGroupEnd();
                                            }
                                        }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, i16 | (i17 & 112) | (i17 & 896) | (i17 & 7168), 80);
                            composer2 = composerStartRestartGroup;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z4 = z3;
                            function8 = function5;
                            shape4 = shape3;
                            j6 = color;
                            j7 = contentColor;
                            j8 = actionContentColor;
                            function9 = function7;
                            j9 = dismissActionContentColor;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            companion = modifier;
                            composer2 = composerStartRestartGroup;
                            function8 = function5;
                            function9 = function6;
                            z4 = z2;
                            shape4 = shape2;
                            j6 = j5;
                            j7 = j2;
                            j8 = j3;
                            j9 = j4;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                                    SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                }
                            });
                        }
                    }
                    i3 |= BaseAdapter.HEADER;
                    if ((i3 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        } else {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                        }
                        final boolean z6 = z3;
                        final Function2<? super Composer, ? super Integer, Unit> function12 = function5;
                        final long j12 = actionContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function13 = function7;
                        final long j13 = dismissActionContentColor;
                        int i18 = (i3 & 14) | 12779520;
                        int i19 = i3 >> 9;
                        SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                            public final void invoke(Composer composer3, int i110) {
                                ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                                if ((i110 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1829663446, i110, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                    }
                                    TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                    final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                    ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                    final boolean z7 = z6;
                                    final Function2<? super Composer, ? super Integer, Unit> function14 = function12;
                                    final Function2<? super Composer, ? super Integer, Unit> function15 = function4;
                                    final Function2<? super Composer, ? super Integer, Unit> function16 = function13;
                                    final long j14 = j12;
                                    final long j15 = j13;
                                    CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                        public final void invoke(Composer composer4, int i111) {
                                            ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                            if ((i111 & 3) != 2 || !composer4.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(835891690, i111, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                                }
                                                if (z7 && function14 != null) {
                                                    composer4.startReplaceGroup(-810715387);
                                                    ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                    SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function15, function14, function16, value2, j14, j15, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                } else {
                                                    composer4.startReplaceGroup(-810701708);
                                                    ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                    SnackbarKt.m2245OneRowSnackbarkKq0p4A(function15, function14, function16, value2, j14, j15, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer4.skipToGroupEnd();
                                        }
                                    }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, i18 | (i19 & 112) | (i19 & 896) | (i19 & 7168), 80);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z3;
                        function8 = function5;
                        shape4 = shape3;
                        j6 = color;
                        j7 = contentColor;
                        j8 = actionContentColor;
                        function9 = function7;
                        j9 = dismissActionContentColor;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        } else {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                        }
                        final boolean z7 = z3;
                        final Function2<? super Composer, ? super Integer, Unit> function14 = function5;
                        final long j14 = actionContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function15 = function7;
                        final long j15 = dismissActionContentColor;
                        int i110 = (i3 & 14) | 12779520;
                        int i111 = i3 >> 9;
                        SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                                ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                                if ((i112 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1829663446, i112, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                    }
                                    TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                    final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                    ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                    final boolean z8 = z7;
                                    final Function2<? super Composer, ? super Integer, Unit> function16 = function14;
                                    final Function2<? super Composer, ? super Integer, Unit> function17 = function4;
                                    final Function2<? super Composer, ? super Integer, Unit> function18 = function15;
                                    final long j16 = j14;
                                    final long j17 = j15;
                                    CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                        public final void invoke(Composer composer4, int i113) {
                                            ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                            if ((i113 & 3) != 2 || !composer4.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(835891690, i113, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                                }
                                                if (z8 && function16 != null) {
                                                    composer4.startReplaceGroup(-810715387);
                                                    ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                    SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function17, function16, function18, value2, j16, j17, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                } else {
                                                    composer4.startReplaceGroup(-810701708);
                                                    ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                    SnackbarKt.m2245OneRowSnackbarkKq0p4A(function17, function16, function18, value2, j16, j17, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer4.skipToGroupEnd();
                                        }
                                    }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, i110 | (i111 & 112) | (i111 & 896) | (i111 & 7168), 80);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z3;
                        function8 = function5;
                        shape4 = shape3;
                        j6 = color;
                        j7 = contentColor;
                        j8 = actionContentColor;
                        function9 = function7;
                        j9 = dismissActionContentColor;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                                SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 3072;
                z2 = z;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        j5 = j;
                        if (composerStartRestartGroup.changed(j5)) {
                        }
                        i3 |= i15;
                    } else {
                        j5 = j;
                    }
                    i3 |= i15;
                } else {
                    j5 = j;
                }
                if ((i & 1572864) != 0) {
                    if ((i2 & 64) == 0) {
                        i11 = 524288;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0) {
                        i10 = 4194304;
                    } else {
                        i10 = 4194304;
                    }
                    i3 |= i10;
                }
                if ((100663296 & i) != 0) {
                    if ((i2 & 256) == 0) {
                        i9 = 33554432;
                    } else {
                        i9 = 33554432;
                    }
                    i3 |= i9;
                }
                if ((i2 & 512) != 0) {
                    if ((i & BaseAdapter.HEADER) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i8 = 536870912;
                        } else {
                            i8 = 268435456;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        } else {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                        }
                        final boolean z8 = z3;
                        final Function2<? super Composer, ? super Integer, Unit> function16 = function5;
                        final long j16 = actionContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function17 = function7;
                        final long j17 = dismissActionContentColor;
                        int i112 = (i3 & 14) | 12779520;
                        int i113 = i3 >> 9;
                        SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                            public final void invoke(Composer composer3, int i114) {
                                ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                                if ((i114 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1829663446, i114, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                    }
                                    TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                    final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                    ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                    final boolean z9 = z8;
                                    final Function2<? super Composer, ? super Integer, Unit> function18 = function16;
                                    final Function2<? super Composer, ? super Integer, Unit> function19 = function4;
                                    final Function2<? super Composer, ? super Integer, Unit> function110 = function17;
                                    final long j18 = j16;
                                    final long j19 = j17;
                                    CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                        public final void invoke(Composer composer4, int i115) {
                                            ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                            if ((i115 & 3) != 2 || !composer4.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(835891690, i115, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                                }
                                                if (z9 && function18 != null) {
                                                    composer4.startReplaceGroup(-810715387);
                                                    ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                    SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function19, function18, function110, value2, j18, j19, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                } else {
                                                    composer4.startReplaceGroup(-810701708);
                                                    ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                    SnackbarKt.m2245OneRowSnackbarkKq0p4A(function19, function18, function110, value2, j18, j19, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer4.skipToGroupEnd();
                                        }
                                    }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, i112 | (i113 & 112) | (i113 & 896) | (i113 & 7168), 80);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z3;
                        function8 = function5;
                        shape4 = shape3;
                        j6 = color;
                        j7 = contentColor;
                        j8 = actionContentColor;
                        function9 = function7;
                        j9 = dismissActionContentColor;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        } else {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                        }
                        final boolean z9 = z3;
                        final Function2<? super Composer, ? super Integer, Unit> function18 = function5;
                        final long j18 = actionContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function19 = function7;
                        final long j19 = dismissActionContentColor;
                        int i114 = (i3 & 14) | 12779520;
                        int i115 = i3 >> 9;
                        SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                                ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                                if ((i116 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1829663446, i116, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                    }
                                    TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                    final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                    ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                    final boolean z10 = z9;
                                    final Function2<? super Composer, ? super Integer, Unit> function110 = function18;
                                    final Function2<? super Composer, ? super Integer, Unit> function111 = function4;
                                    final Function2<? super Composer, ? super Integer, Unit> function112 = function19;
                                    final long j110 = j18;
                                    final long j111 = j19;
                                    CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                        public final void invoke(Composer composer4, int i117) {
                                            ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                            if ((i117 & 3) != 2 || !composer4.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(835891690, i117, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                                }
                                                if (z10 && function110 != null) {
                                                    composer4.startReplaceGroup(-810715387);
                                                    ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                    SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function111, function110, function112, value2, j110, j111, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                } else {
                                                    composer4.startReplaceGroup(-810701708);
                                                    ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                    SnackbarKt.m2245OneRowSnackbarkKq0p4A(function111, function110, function112, value2, j110, j111, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer4.skipToGroupEnd();
                                        }
                                    }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, i114 | (i115 & 112) | (i115 & 896) | (i115 & 7168), 80);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z3;
                        function8 = function5;
                        shape4 = shape3;
                        j6 = color;
                        j7 = contentColor;
                        j8 = actionContentColor;
                        function9 = function7;
                        j9 = dismissActionContentColor;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                                SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= BaseAdapter.HEADER;
                if ((i3 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                    }
                    final boolean z10 = z3;
                    final Function2<? super Composer, ? super Integer, Unit> function110 = function5;
                    final long j110 = actionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function111 = function7;
                    final long j111 = dismissActionContentColor;
                    int i116 = (i3 & 14) | 12779520;
                    int i117 = i3 >> 9;
                    SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                        public final void invoke(Composer composer3, int i118) {
                            ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                            if ((i118 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1829663446, i118, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                }
                                TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z11 = z10;
                                final Function2<? super Composer, ? super Integer, Unit> function112 = function110;
                                final Function2<? super Composer, ? super Integer, Unit> function113 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function114 = function111;
                                final long j112 = j110;
                                final long j113 = j111;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                    public final void invoke(Composer composer4, int i119) {
                                        ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                        if ((i119 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(835891690, i119, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                            }
                                            if (z11 && function112 != null) {
                                                composer4.startReplaceGroup(-810715387);
                                                ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function113, function112, function114, value2, j112, j113, composer4, 0);
                                                composer4.endReplaceGroup();
                                            } else {
                                                composer4.startReplaceGroup(-810701708);
                                                ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                SnackbarKt.m2245OneRowSnackbarkKq0p4A(function113, function112, function114, value2, j112, j113, composer4, 0);
                                                composer4.endReplaceGroup();
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, i116 | (i117 & 112) | (i117 & 896) | (i117 & 7168), 80);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z3;
                    function8 = function5;
                    shape4 = shape3;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    function9 = function7;
                    j9 = dismissActionContentColor;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                    }
                    final boolean z11 = z3;
                    final Function2<? super Composer, ? super Integer, Unit> function112 = function5;
                    final long j112 = actionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function113 = function7;
                    final long j113 = dismissActionContentColor;
                    int i118 = (i3 & 14) | 12779520;
                    int i119 = i3 >> 9;
                    SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                            ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                            if ((i1110 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1829663446, i1110, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                }
                                TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z12 = z11;
                                final Function2<? super Composer, ? super Integer, Unit> function114 = function112;
                                final Function2<? super Composer, ? super Integer, Unit> function115 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function116 = function113;
                                final long j114 = j112;
                                final long j115 = j113;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                    public final void invoke(Composer composer4, int i1111) {
                                        ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                        if ((i1111 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(835891690, i1111, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                            }
                                            if (z12 && function114 != null) {
                                                composer4.startReplaceGroup(-810715387);
                                                ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function115, function114, function116, value2, j114, j115, composer4, 0);
                                                composer4.endReplaceGroup();
                                            } else {
                                                composer4.startReplaceGroup(-810701708);
                                                ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                SnackbarKt.m2245OneRowSnackbarkKq0p4A(function115, function114, function116, value2, j114, j115, composer4, 0);
                                                composer4.endReplaceGroup();
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, i118 | (i119 & 112) | (i119 & 896) | (i119 & 7168), 80);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z3;
                    function8 = function5;
                    shape4 = shape3;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    function9 = function7;
                    j9 = dismissActionContentColor;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                            SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            function6 = function3;
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        j5 = j;
                        if (composerStartRestartGroup.changed(j5)) {
                        }
                        i3 |= i15;
                    } else {
                        j5 = j;
                    }
                    i3 |= i15;
                } else {
                    j5 = j;
                }
                if ((i & 1572864) != 0) {
                    if ((i2 & 64) == 0) {
                        i11 = 524288;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0) {
                        i10 = 4194304;
                    } else {
                        i10 = 4194304;
                    }
                    i3 |= i10;
                }
                if ((100663296 & i) != 0) {
                    if ((i2 & 256) == 0) {
                        i9 = 33554432;
                    } else {
                        i9 = 33554432;
                    }
                    i3 |= i9;
                }
                if ((i2 & 512) != 0) {
                    if ((i & BaseAdapter.HEADER) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i8 = 536870912;
                        } else {
                            i8 = 268435456;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        } else {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                        }
                        final boolean z12 = z3;
                        final Function2<? super Composer, ? super Integer, Unit> function114 = function5;
                        final long j114 = actionContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function115 = function7;
                        final long j115 = dismissActionContentColor;
                        int i1110 = (i3 & 14) | 12779520;
                        int i1111 = i3 >> 9;
                        SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                            public final void invoke(Composer composer3, int i1112) {
                                ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                                if ((i1112 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1829663446, i1112, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                    }
                                    TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                    final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                    ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                    final boolean z13 = z12;
                                    final Function2<? super Composer, ? super Integer, Unit> function116 = function114;
                                    final Function2<? super Composer, ? super Integer, Unit> function117 = function4;
                                    final Function2<? super Composer, ? super Integer, Unit> function118 = function115;
                                    final long j116 = j114;
                                    final long j117 = j115;
                                    CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                        public final void invoke(Composer composer4, int i1113) {
                                            ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                            if ((i1113 & 3) != 2 || !composer4.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(835891690, i1113, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                                }
                                                if (z13 && function116 != null) {
                                                    composer4.startReplaceGroup(-810715387);
                                                    ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                    SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function117, function116, function118, value2, j116, j117, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                } else {
                                                    composer4.startReplaceGroup(-810701708);
                                                    ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                    SnackbarKt.m2245OneRowSnackbarkKq0p4A(function117, function116, function118, value2, j116, j117, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer4.skipToGroupEnd();
                                        }
                                    }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, i1110 | (i1111 & 112) | (i1111 & 896) | (i1111 & 7168), 80);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z3;
                        function8 = function5;
                        shape4 = shape3;
                        j6 = color;
                        j7 = contentColor;
                        j8 = actionContentColor;
                        function9 = function7;
                        j9 = dismissActionContentColor;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        } else {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                        }
                        final boolean z13 = z3;
                        final Function2<? super Composer, ? super Integer, Unit> function116 = function5;
                        final long j116 = actionContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function117 = function7;
                        final long j117 = dismissActionContentColor;
                        int i1112 = (i3 & 14) | 12779520;
                        int i1113 = i3 >> 9;
                        SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                                ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                                if ((i1114 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1829663446, i1114, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                    }
                                    TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                    final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                    ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                    final boolean z14 = z13;
                                    final Function2<? super Composer, ? super Integer, Unit> function118 = function116;
                                    final Function2<? super Composer, ? super Integer, Unit> function119 = function4;
                                    final Function2<? super Composer, ? super Integer, Unit> function1110 = function117;
                                    final long j118 = j116;
                                    final long j119 = j117;
                                    CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                        public final void invoke(Composer composer4, int i1115) {
                                            ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                            if ((i1115 & 3) != 2 || !composer4.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(835891690, i1115, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                                }
                                                if (z14 && function118 != null) {
                                                    composer4.startReplaceGroup(-810715387);
                                                    ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                    SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function119, function118, function1110, value2, j118, j119, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                } else {
                                                    composer4.startReplaceGroup(-810701708);
                                                    ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                    SnackbarKt.m2245OneRowSnackbarkKq0p4A(function119, function118, function1110, value2, j118, j119, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer4.skipToGroupEnd();
                                        }
                                    }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, i1112 | (i1113 & 112) | (i1113 & 896) | (i1113 & 7168), 80);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z3;
                        function8 = function5;
                        shape4 = shape3;
                        j6 = color;
                        j7 = contentColor;
                        j8 = actionContentColor;
                        function9 = function7;
                        j9 = dismissActionContentColor;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                                SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= BaseAdapter.HEADER;
                if ((i3 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                    }
                    final boolean z14 = z3;
                    final Function2<? super Composer, ? super Integer, Unit> function118 = function5;
                    final long j118 = actionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function119 = function7;
                    final long j119 = dismissActionContentColor;
                    int i1114 = (i3 & 14) | 12779520;
                    int i1115 = i3 >> 9;
                    SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                        public final void invoke(Composer composer3, int i1116) {
                            ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                            if ((i1116 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1829663446, i1116, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                }
                                TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z15 = z14;
                                final Function2<? super Composer, ? super Integer, Unit> function1110 = function118;
                                final Function2<? super Composer, ? super Integer, Unit> function1111 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function1112 = function119;
                                final long j1110 = j118;
                                final long j1111 = j119;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                    public final void invoke(Composer composer4, int i1117) {
                                        ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                        if ((i1117 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(835891690, i1117, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                            }
                                            if (z15 && function1110 != null) {
                                                composer4.startReplaceGroup(-810715387);
                                                ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function1111, function1110, function1112, value2, j1110, j1111, composer4, 0);
                                                composer4.endReplaceGroup();
                                            } else {
                                                composer4.startReplaceGroup(-810701708);
                                                ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                SnackbarKt.m2245OneRowSnackbarkKq0p4A(function1111, function1110, function1112, value2, j1110, j1111, composer4, 0);
                                                composer4.endReplaceGroup();
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, i1114 | (i1115 & 112) | (i1115 & 896) | (i1115 & 7168), 80);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z3;
                    function8 = function5;
                    shape4 = shape3;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    function9 = function7;
                    j9 = dismissActionContentColor;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                    }
                    final boolean z15 = z3;
                    final Function2<? super Composer, ? super Integer, Unit> function1110 = function5;
                    final long j1110 = actionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function1111 = function7;
                    final long j1111 = dismissActionContentColor;
                    int i1116 = (i3 & 14) | 12779520;
                    int i1117 = i3 >> 9;
                    SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                            ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                            if ((i1118 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1829663446, i1118, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                }
                                TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z16 = z15;
                                final Function2<? super Composer, ? super Integer, Unit> function1112 = function1110;
                                final Function2<? super Composer, ? super Integer, Unit> function1113 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function1114 = function1111;
                                final long j1112 = j1110;
                                final long j1113 = j1111;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                    public final void invoke(Composer composer4, int i1119) {
                                        ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                        if ((i1119 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(835891690, i1119, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                            }
                                            if (z16 && function1112 != null) {
                                                composer4.startReplaceGroup(-810715387);
                                                ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function1113, function1112, function1114, value2, j1112, j1113, composer4, 0);
                                                composer4.endReplaceGroup();
                                            } else {
                                                composer4.startReplaceGroup(-810701708);
                                                ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                SnackbarKt.m2245OneRowSnackbarkKq0p4A(function1113, function1112, function1114, value2, j1112, j1113, composer4, 0);
                                                composer4.endReplaceGroup();
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, i1116 | (i1117 & 112) | (i1117 & 896) | (i1117 & 7168), 80);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z3;
                    function8 = function5;
                    shape4 = shape3;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    function9 = function7;
                    j9 = dismissActionContentColor;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                            SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                i3 |= i14;
            } else {
                shape2 = shape;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    j5 = j;
                    if (composerStartRestartGroup.changed(j5)) {
                    }
                    i3 |= i15;
                } else {
                    j5 = j;
                }
                i3 |= i15;
            } else {
                j5 = j;
            }
            if ((i & 1572864) != 0) {
                if ((i2 & 64) == 0) {
                    i11 = 524288;
                } else {
                    i11 = 524288;
                }
                i3 |= i11;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i10 = 4194304;
                } else {
                    i10 = 4194304;
                }
                i3 |= i10;
            }
            if ((100663296 & i) != 0) {
                if ((i2 & 256) == 0) {
                    i9 = 33554432;
                } else {
                    i9 = 33554432;
                }
                i3 |= i9;
            }
            if ((i2 & 512) != 0) {
                if ((i & BaseAdapter.HEADER) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i8 = 536870912;
                    } else {
                        i8 = 268435456;
                    }
                    i3 |= i8;
                }
                if ((i3 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                    }
                    final boolean z16 = z3;
                    final Function2<? super Composer, ? super Integer, Unit> function1112 = function5;
                    final long j1112 = actionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function1113 = function7;
                    final long j1113 = dismissActionContentColor;
                    int i1118 = (i3 & 14) | 12779520;
                    int i1119 = i3 >> 9;
                    SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                        public final void invoke(Composer composer3, int i11110) {
                            ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                            if ((i11110 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1829663446, i11110, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                }
                                TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z17 = z16;
                                final Function2<? super Composer, ? super Integer, Unit> function1114 = function1112;
                                final Function2<? super Composer, ? super Integer, Unit> function1115 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function1116 = function1113;
                                final long j1114 = j1112;
                                final long j1115 = j1113;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                    public final void invoke(Composer composer4, int i11111) {
                                        ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                        if ((i11111 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(835891690, i11111, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                            }
                                            if (z17 && function1114 != null) {
                                                composer4.startReplaceGroup(-810715387);
                                                ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function1115, function1114, function1116, value2, j1114, j1115, composer4, 0);
                                                composer4.endReplaceGroup();
                                            } else {
                                                composer4.startReplaceGroup(-810701708);
                                                ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                SnackbarKt.m2245OneRowSnackbarkKq0p4A(function1115, function1114, function1116, value2, j1114, j1115, composer4, 0);
                                                composer4.endReplaceGroup();
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, i1118 | (i1119 & 112) | (i1119 & 896) | (i1119 & 7168), 80);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z3;
                    function8 = function5;
                    shape4 = shape3;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    function9 = function7;
                    j9 = dismissActionContentColor;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                    }
                    final boolean z17 = z3;
                    final Function2<? super Composer, ? super Integer, Unit> function1114 = function5;
                    final long j1114 = actionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function1115 = function7;
                    final long j1115 = dismissActionContentColor;
                    int i11110 = (i3 & 14) | 12779520;
                    int i11111 = i3 >> 9;
                    SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                            ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                            if ((i11112 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1829663446, i11112, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                }
                                TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z18 = z17;
                                final Function2<? super Composer, ? super Integer, Unit> function1116 = function1114;
                                final Function2<? super Composer, ? super Integer, Unit> function1117 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function1118 = function1115;
                                final long j1116 = j1114;
                                final long j1117 = j1115;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                    public final void invoke(Composer composer4, int i11113) {
                                        ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                        if ((i11113 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(835891690, i11113, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                            }
                                            if (z18 && function1116 != null) {
                                                composer4.startReplaceGroup(-810715387);
                                                ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function1117, function1116, function1118, value2, j1116, j1117, composer4, 0);
                                                composer4.endReplaceGroup();
                                            } else {
                                                composer4.startReplaceGroup(-810701708);
                                                ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                SnackbarKt.m2245OneRowSnackbarkKq0p4A(function1117, function1116, function1118, value2, j1116, j1117, composer4, 0);
                                                composer4.endReplaceGroup();
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, i11110 | (i11111 & 112) | (i11111 & 896) | (i11111 & 7168), 80);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z3;
                    function8 = function5;
                    shape4 = shape3;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    function9 = function7;
                    j9 = dismissActionContentColor;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                            SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= BaseAdapter.HEADER;
            if ((i3 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                }
                final boolean z18 = z3;
                final Function2<? super Composer, ? super Integer, Unit> function1116 = function5;
                final long j1116 = actionContentColor;
                final Function2<? super Composer, ? super Integer, Unit> function1117 = function7;
                final long j1117 = dismissActionContentColor;
                int i11112 = (i3 & 14) | 12779520;
                int i11113 = i3 >> 9;
                SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                    public final void invoke(Composer composer3, int i11114) {
                        ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                        if ((i11114 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1829663446, i11114, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                            }
                            TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z19 = z18;
                            final Function2<? super Composer, ? super Integer, Unit> function1118 = function1116;
                            final Function2<? super Composer, ? super Integer, Unit> function1119 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function11110 = function1117;
                            final long j1118 = j1116;
                            final long j1119 = j1117;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                public final void invoke(Composer composer4, int i11115) {
                                    ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                    if ((i11115 & 3) != 2 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(835891690, i11115, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                        }
                                        if (z19 && function1118 != null) {
                                            composer4.startReplaceGroup(-810715387);
                                            ComposerKt.sourceInformation(composer4, "126@5873L383");
                                            SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function1119, function1118, function11110, value2, j1118, j1119, composer4, 0);
                                            composer4.endReplaceGroup();
                                        } else {
                                            composer4.startReplaceGroup(-810701708);
                                            ComposerKt.sourceInformation(composer4, "135@6301L366");
                                            SnackbarKt.m2245OneRowSnackbarkKq0p4A(function1119, function1118, function11110, value2, j1118, j1119, composer4, 0);
                                            composer4.endReplaceGroup();
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, i11112 | (i11113 & 112) | (i11113 & 896) | (i11113 & 7168), 80);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z3;
                function8 = function5;
                shape4 = shape3;
                j6 = color;
                j7 = contentColor;
                j8 = actionContentColor;
                function9 = function7;
                j9 = dismissActionContentColor;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                }
                final boolean z19 = z3;
                final Function2<? super Composer, ? super Integer, Unit> function1118 = function5;
                final long j1118 = actionContentColor;
                final Function2<? super Composer, ? super Integer, Unit> function1119 = function7;
                final long j1119 = dismissActionContentColor;
                int i11114 = (i3 & 14) | 12779520;
                int i11115 = i3 >> 9;
                SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                        ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                        if ((i11116 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1829663446, i11116, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                            }
                            TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z110 = z19;
                            final Function2<? super Composer, ? super Integer, Unit> function11110 = function1118;
                            final Function2<? super Composer, ? super Integer, Unit> function11111 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function11112 = function1119;
                            final long j11110 = j1118;
                            final long j11111 = j1119;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                public final void invoke(Composer composer4, int i11117) {
                                    ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                    if ((i11117 & 3) != 2 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(835891690, i11117, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                        }
                                        if (z110 && function11110 != null) {
                                            composer4.startReplaceGroup(-810715387);
                                            ComposerKt.sourceInformation(composer4, "126@5873L383");
                                            SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function11111, function11110, function11112, value2, j11110, j11111, composer4, 0);
                                            composer4.endReplaceGroup();
                                        } else {
                                            composer4.startReplaceGroup(-810701708);
                                            ComposerKt.sourceInformation(composer4, "135@6301L366");
                                            SnackbarKt.m2245OneRowSnackbarkKq0p4A(function11111, function11110, function11112, value2, j11110, j11111, composer4, 0);
                                            composer4.endReplaceGroup();
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, i11114 | (i11115 & 112) | (i11115 & 896) | (i11115 & 7168), 80);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z3;
                function8 = function5;
                shape4 = shape3;
                j6 = color;
                j7 = contentColor;
                j8 = actionContentColor;
                function9 = function7;
                j9 = dismissActionContentColor;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                        SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        function5 = function2;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                function6 = function3;
                if (composerStartRestartGroup.changedInstance(function6)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i3 |= i14;
                    } else {
                        shape2 = shape;
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        j5 = j;
                        if (composerStartRestartGroup.changed(j5)) {
                        }
                        i3 |= i15;
                    } else {
                        j5 = j;
                    }
                    i3 |= i15;
                } else {
                    j5 = j;
                }
                if ((i & 1572864) != 0) {
                    if ((i2 & 64) == 0) {
                        i11 = 524288;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0) {
                        i10 = 4194304;
                    } else {
                        i10 = 4194304;
                    }
                    i3 |= i10;
                }
                if ((100663296 & i) != 0) {
                    if ((i2 & 256) == 0) {
                        i9 = 33554432;
                    } else {
                        i9 = 33554432;
                    }
                    i3 |= i9;
                }
                if ((i2 & 512) != 0) {
                    if ((i & BaseAdapter.HEADER) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i8 = 536870912;
                        } else {
                            i8 = 268435456;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        } else {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                        }
                        final boolean z110 = z3;
                        final Function2<? super Composer, ? super Integer, Unit> function11110 = function5;
                        final long j11110 = actionContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function11111 = function7;
                        final long j11111 = dismissActionContentColor;
                        int i11116 = (i3 & 14) | 12779520;
                        int i11117 = i3 >> 9;
                        SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                            public final void invoke(Composer composer3, int i11118) {
                                ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                                if ((i11118 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1829663446, i11118, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                    }
                                    TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                    final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                    ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                    final boolean z111 = z110;
                                    final Function2<? super Composer, ? super Integer, Unit> function11112 = function11110;
                                    final Function2<? super Composer, ? super Integer, Unit> function11113 = function4;
                                    final Function2<? super Composer, ? super Integer, Unit> function11114 = function11111;
                                    final long j11112 = j11110;
                                    final long j11113 = j11111;
                                    CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                        public final void invoke(Composer composer4, int i11119) {
                                            ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                            if ((i11119 & 3) != 2 || !composer4.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(835891690, i11119, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                                }
                                                if (z111 && function11112 != null) {
                                                    composer4.startReplaceGroup(-810715387);
                                                    ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                    SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function11113, function11112, function11114, value2, j11112, j11113, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                } else {
                                                    composer4.startReplaceGroup(-810701708);
                                                    ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                    SnackbarKt.m2245OneRowSnackbarkKq0p4A(function11113, function11112, function11114, value2, j11112, j11113, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer4.skipToGroupEnd();
                                        }
                                    }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, i11116 | (i11117 & 112) | (i11117 & 896) | (i11117 & 7168), 80);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z3;
                        function8 = function5;
                        shape4 = shape3;
                        j6 = color;
                        j7 = contentColor;
                        j8 = actionContentColor;
                        function9 = function7;
                        j9 = dismissActionContentColor;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        } else {
                            if (i12 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i13 != 0) {
                                function5 = null;
                            }
                            if (i4 == 0) {
                            }
                            if (i6 != 0) {
                                z3 = false;
                            } else {
                                z3 = z2;
                            }
                            if ((i2 & 16) != 0) {
                                shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i2 & 32) != 0) {
                                color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                                i3 &= -458753;
                            } else {
                                color = j5;
                            }
                            if ((i2 & 64) != 0) {
                                contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                                i3 &= -3670017;
                            } else {
                                contentColor = j2;
                            }
                            if ((i2 & 128) != 0) {
                                actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                                i3 = (-29360129) & i3;
                            } else {
                                actionContentColor = j3;
                            }
                            if ((i2 & 256) != 0) {
                                dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                                i3 &= -234881025;
                            } else {
                                dismissActionContentColor = j4;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                        }
                        final boolean z111 = z3;
                        final Function2<? super Composer, ? super Integer, Unit> function11112 = function5;
                        final long j11112 = actionContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function11113 = function7;
                        final long j11113 = dismissActionContentColor;
                        int i11118 = (i3 & 14) | 12779520;
                        int i11119 = i3 >> 9;
                        SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                                ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                                if ((i111110 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1829663446, i111110, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                    }
                                    TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                    final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                    ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                    final boolean z112 = z111;
                                    final Function2<? super Composer, ? super Integer, Unit> function11114 = function11112;
                                    final Function2<? super Composer, ? super Integer, Unit> function11115 = function4;
                                    final Function2<? super Composer, ? super Integer, Unit> function11116 = function11113;
                                    final long j11114 = j11112;
                                    final long j11115 = j11113;
                                    CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                        public final void invoke(Composer composer4, int i111111) {
                                            ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                            if ((i111111 & 3) != 2 || !composer4.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(835891690, i111111, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                                }
                                                if (z112 && function11114 != null) {
                                                    composer4.startReplaceGroup(-810715387);
                                                    ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                    SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function11115, function11114, function11116, value2, j11114, j11115, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                } else {
                                                    composer4.startReplaceGroup(-810701708);
                                                    ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                    SnackbarKt.m2245OneRowSnackbarkKq0p4A(function11115, function11114, function11116, value2, j11114, j11115, composer4, 0);
                                                    composer4.endReplaceGroup();
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer4.skipToGroupEnd();
                                        }
                                    }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, i11118 | (i11119 & 112) | (i11119 & 896) | (i11119 & 7168), 80);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z3;
                        function8 = function5;
                        shape4 = shape3;
                        j6 = color;
                        j7 = contentColor;
                        j8 = actionContentColor;
                        function9 = function7;
                        j9 = dismissActionContentColor;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                                SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= BaseAdapter.HEADER;
                if ((i3 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                    }
                    final boolean z112 = z3;
                    final Function2<? super Composer, ? super Integer, Unit> function11114 = function5;
                    final long j11114 = actionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function11115 = function7;
                    final long j11115 = dismissActionContentColor;
                    int i111110 = (i3 & 14) | 12779520;
                    int i111111 = i3 >> 9;
                    SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                        public final void invoke(Composer composer3, int i111112) {
                            ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                            if ((i111112 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1829663446, i111112, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                }
                                TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z113 = z112;
                                final Function2<? super Composer, ? super Integer, Unit> function11116 = function11114;
                                final Function2<? super Composer, ? super Integer, Unit> function11117 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function11118 = function11115;
                                final long j11116 = j11114;
                                final long j11117 = j11115;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                    public final void invoke(Composer composer4, int i111113) {
                                        ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                        if ((i111113 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(835891690, i111113, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                            }
                                            if (z113 && function11116 != null) {
                                                composer4.startReplaceGroup(-810715387);
                                                ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function11117, function11116, function11118, value2, j11116, j11117, composer4, 0);
                                                composer4.endReplaceGroup();
                                            } else {
                                                composer4.startReplaceGroup(-810701708);
                                                ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                SnackbarKt.m2245OneRowSnackbarkKq0p4A(function11117, function11116, function11118, value2, j11116, j11117, composer4, 0);
                                                composer4.endReplaceGroup();
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, i111110 | (i111111 & 112) | (i111111 & 896) | (i111111 & 7168), 80);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z3;
                    function8 = function5;
                    shape4 = shape3;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    function9 = function7;
                    j9 = dismissActionContentColor;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                    }
                    final boolean z113 = z3;
                    final Function2<? super Composer, ? super Integer, Unit> function11116 = function5;
                    final long j11116 = actionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function11117 = function7;
                    final long j11117 = dismissActionContentColor;
                    int i111112 = (i3 & 14) | 12779520;
                    int i111113 = i3 >> 9;
                    SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                            ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                            if ((i111114 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1829663446, i111114, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                }
                                TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z114 = z113;
                                final Function2<? super Composer, ? super Integer, Unit> function11118 = function11116;
                                final Function2<? super Composer, ? super Integer, Unit> function11119 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function111110 = function11117;
                                final long j11118 = j11116;
                                final long j11119 = j11117;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                    public final void invoke(Composer composer4, int i111115) {
                                        ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                        if ((i111115 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(835891690, i111115, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                            }
                                            if (z114 && function11118 != null) {
                                                composer4.startReplaceGroup(-810715387);
                                                ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function11119, function11118, function111110, value2, j11118, j11119, composer4, 0);
                                                composer4.endReplaceGroup();
                                            } else {
                                                composer4.startReplaceGroup(-810701708);
                                                ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                SnackbarKt.m2245OneRowSnackbarkKq0p4A(function11119, function11118, function111110, value2, j11118, j11119, composer4, 0);
                                                composer4.endReplaceGroup();
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, i111112 | (i111113 & 112) | (i111113 & 896) | (i111113 & 7168), 80);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z3;
                    function8 = function5;
                    shape4 = shape3;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    function9 = function7;
                    j9 = dismissActionContentColor;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                            SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                i3 |= i14;
            } else {
                shape2 = shape;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    j5 = j;
                    if (composerStartRestartGroup.changed(j5)) {
                    }
                    i3 |= i15;
                } else {
                    j5 = j;
                }
                i3 |= i15;
            } else {
                j5 = j;
            }
            if ((i & 1572864) != 0) {
                if ((i2 & 64) == 0) {
                    i11 = 524288;
                } else {
                    i11 = 524288;
                }
                i3 |= i11;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i10 = 4194304;
                } else {
                    i10 = 4194304;
                }
                i3 |= i10;
            }
            if ((100663296 & i) != 0) {
                if ((i2 & 256) == 0) {
                    i9 = 33554432;
                } else {
                    i9 = 33554432;
                }
                i3 |= i9;
            }
            if ((i2 & 512) != 0) {
                if ((i & BaseAdapter.HEADER) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i8 = 536870912;
                    } else {
                        i8 = 268435456;
                    }
                    i3 |= i8;
                }
                if ((i3 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                    }
                    final boolean z114 = z3;
                    final Function2<? super Composer, ? super Integer, Unit> function11118 = function5;
                    final long j11118 = actionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function11119 = function7;
                    final long j11119 = dismissActionContentColor;
                    int i111114 = (i3 & 14) | 12779520;
                    int i111115 = i3 >> 9;
                    SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                        public final void invoke(Composer composer3, int i111116) {
                            ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                            if ((i111116 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1829663446, i111116, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                }
                                TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z115 = z114;
                                final Function2<? super Composer, ? super Integer, Unit> function111110 = function11118;
                                final Function2<? super Composer, ? super Integer, Unit> function111111 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function111112 = function11119;
                                final long j111110 = j11118;
                                final long j111111 = j11119;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                    public final void invoke(Composer composer4, int i111117) {
                                        ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                        if ((i111117 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(835891690, i111117, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                            }
                                            if (z115 && function111110 != null) {
                                                composer4.startReplaceGroup(-810715387);
                                                ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function111111, function111110, function111112, value2, j111110, j111111, composer4, 0);
                                                composer4.endReplaceGroup();
                                            } else {
                                                composer4.startReplaceGroup(-810701708);
                                                ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                SnackbarKt.m2245OneRowSnackbarkKq0p4A(function111111, function111110, function111112, value2, j111110, j111111, composer4, 0);
                                                composer4.endReplaceGroup();
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, i111114 | (i111115 & 112) | (i111115 & 896) | (i111115 & 7168), 80);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z3;
                    function8 = function5;
                    shape4 = shape3;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    function9 = function7;
                    j9 = dismissActionContentColor;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                    }
                    final boolean z115 = z3;
                    final Function2<? super Composer, ? super Integer, Unit> function111110 = function5;
                    final long j111110 = actionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function111111 = function7;
                    final long j111111 = dismissActionContentColor;
                    int i111116 = (i3 & 14) | 12779520;
                    int i111117 = i3 >> 9;
                    SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                            ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                            if ((i111118 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1829663446, i111118, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                }
                                TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z116 = z115;
                                final Function2<? super Composer, ? super Integer, Unit> function111112 = function111110;
                                final Function2<? super Composer, ? super Integer, Unit> function111113 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function111114 = function111111;
                                final long j111112 = j111110;
                                final long j111113 = j111111;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                    public final void invoke(Composer composer4, int i111119) {
                                        ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                        if ((i111119 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(835891690, i111119, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                            }
                                            if (z116 && function111112 != null) {
                                                composer4.startReplaceGroup(-810715387);
                                                ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function111113, function111112, function111114, value2, j111112, j111113, composer4, 0);
                                                composer4.endReplaceGroup();
                                            } else {
                                                composer4.startReplaceGroup(-810701708);
                                                ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                SnackbarKt.m2245OneRowSnackbarkKq0p4A(function111113, function111112, function111114, value2, j111112, j111113, composer4, 0);
                                                composer4.endReplaceGroup();
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, i111116 | (i111117 & 112) | (i111117 & 896) | (i111117 & 7168), 80);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z3;
                    function8 = function5;
                    shape4 = shape3;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    function9 = function7;
                    j9 = dismissActionContentColor;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                            SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= BaseAdapter.HEADER;
            if ((i3 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                }
                final boolean z116 = z3;
                final Function2<? super Composer, ? super Integer, Unit> function111112 = function5;
                final long j111112 = actionContentColor;
                final Function2<? super Composer, ? super Integer, Unit> function111113 = function7;
                final long j111113 = dismissActionContentColor;
                int i111118 = (i3 & 14) | 12779520;
                int i111119 = i3 >> 9;
                SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                    public final void invoke(Composer composer3, int i1111110) {
                        ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                        if ((i1111110 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1829663446, i1111110, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                            }
                            TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z117 = z116;
                            final Function2<? super Composer, ? super Integer, Unit> function111114 = function111112;
                            final Function2<? super Composer, ? super Integer, Unit> function111115 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function111116 = function111113;
                            final long j111114 = j111112;
                            final long j111115 = j111113;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                public final void invoke(Composer composer4, int i1111111) {
                                    ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                    if ((i1111111 & 3) != 2 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(835891690, i1111111, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                        }
                                        if (z117 && function111114 != null) {
                                            composer4.startReplaceGroup(-810715387);
                                            ComposerKt.sourceInformation(composer4, "126@5873L383");
                                            SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function111115, function111114, function111116, value2, j111114, j111115, composer4, 0);
                                            composer4.endReplaceGroup();
                                        } else {
                                            composer4.startReplaceGroup(-810701708);
                                            ComposerKt.sourceInformation(composer4, "135@6301L366");
                                            SnackbarKt.m2245OneRowSnackbarkKq0p4A(function111115, function111114, function111116, value2, j111114, j111115, composer4, 0);
                                            composer4.endReplaceGroup();
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, i111118 | (i111119 & 112) | (i111119 & 896) | (i111119 & 7168), 80);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z3;
                function8 = function5;
                shape4 = shape3;
                j6 = color;
                j7 = contentColor;
                j8 = actionContentColor;
                function9 = function7;
                j9 = dismissActionContentColor;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                }
                final boolean z117 = z3;
                final Function2<? super Composer, ? super Integer, Unit> function111114 = function5;
                final long j111114 = actionContentColor;
                final Function2<? super Composer, ? super Integer, Unit> function111115 = function7;
                final long j111115 = dismissActionContentColor;
                int i1111110 = (i3 & 14) | 12779520;
                int i1111111 = i3 >> 9;
                SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                        ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                        if ((i1111112 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1829663446, i1111112, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                            }
                            TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z118 = z117;
                            final Function2<? super Composer, ? super Integer, Unit> function111116 = function111114;
                            final Function2<? super Composer, ? super Integer, Unit> function111117 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function111118 = function111115;
                            final long j111116 = j111114;
                            final long j111117 = j111115;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                public final void invoke(Composer composer4, int i1111113) {
                                    ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                    if ((i1111113 & 3) != 2 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(835891690, i1111113, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                        }
                                        if (z118 && function111116 != null) {
                                            composer4.startReplaceGroup(-810715387);
                                            ComposerKt.sourceInformation(composer4, "126@5873L383");
                                            SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function111117, function111116, function111118, value2, j111116, j111117, composer4, 0);
                                            composer4.endReplaceGroup();
                                        } else {
                                            composer4.startReplaceGroup(-810701708);
                                            ComposerKt.sourceInformation(composer4, "135@6301L366");
                                            SnackbarKt.m2245OneRowSnackbarkKq0p4A(function111117, function111116, function111118, value2, j111116, j111117, composer4, 0);
                                            composer4.endReplaceGroup();
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, i1111110 | (i1111111 & 112) | (i1111111 & 896) | (i1111111 & 7168), 80);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z3;
                function8 = function5;
                shape4 = shape3;
                j6 = color;
                j7 = contentColor;
                j8 = actionContentColor;
                function9 = function7;
                j9 = dismissActionContentColor;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                        SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        function6 = function3;
        i6 = i2 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i14;
                } else {
                    shape2 = shape;
                }
                i3 |= i14;
            } else {
                shape2 = shape;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    j5 = j;
                    if (composerStartRestartGroup.changed(j5)) {
                    }
                    i3 |= i15;
                } else {
                    j5 = j;
                }
                i3 |= i15;
            } else {
                j5 = j;
            }
            if ((i & 1572864) != 0) {
                if ((i2 & 64) == 0) {
                    i11 = 524288;
                } else {
                    i11 = 524288;
                }
                i3 |= i11;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i10 = 4194304;
                } else {
                    i10 = 4194304;
                }
                i3 |= i10;
            }
            if ((100663296 & i) != 0) {
                if ((i2 & 256) == 0) {
                    i9 = 33554432;
                } else {
                    i9 = 33554432;
                }
                i3 |= i9;
            }
            if ((i2 & 512) != 0) {
                if ((i & BaseAdapter.HEADER) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i8 = 536870912;
                    } else {
                        i8 = 268435456;
                    }
                    i3 |= i8;
                }
                if ((i3 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                    }
                    final boolean z118 = z3;
                    final Function2<? super Composer, ? super Integer, Unit> function111116 = function5;
                    final long j111116 = actionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function111117 = function7;
                    final long j111117 = dismissActionContentColor;
                    int i1111112 = (i3 & 14) | 12779520;
                    int i1111113 = i3 >> 9;
                    SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                        public final void invoke(Composer composer3, int i1111114) {
                            ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                            if ((i1111114 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1829663446, i1111114, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                }
                                TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z119 = z118;
                                final Function2<? super Composer, ? super Integer, Unit> function111118 = function111116;
                                final Function2<? super Composer, ? super Integer, Unit> function111119 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function1111110 = function111117;
                                final long j111118 = j111116;
                                final long j111119 = j111117;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                    public final void invoke(Composer composer4, int i1111115) {
                                        ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                        if ((i1111115 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(835891690, i1111115, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                            }
                                            if (z119 && function111118 != null) {
                                                composer4.startReplaceGroup(-810715387);
                                                ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function111119, function111118, function1111110, value2, j111118, j111119, composer4, 0);
                                                composer4.endReplaceGroup();
                                            } else {
                                                composer4.startReplaceGroup(-810701708);
                                                ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                SnackbarKt.m2245OneRowSnackbarkKq0p4A(function111119, function111118, function1111110, value2, j111118, j111119, composer4, 0);
                                                composer4.endReplaceGroup();
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, i1111112 | (i1111113 & 112) | (i1111113 & 896) | (i1111113 & 7168), 80);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z3;
                    function8 = function5;
                    shape4 = shape3;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    function9 = function7;
                    j9 = dismissActionContentColor;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    } else {
                        if (i12 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i13 != 0) {
                            function5 = null;
                        }
                        if (i4 == 0) {
                        }
                        if (i6 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 16) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 32) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        } else {
                            color = j5;
                        }
                        if ((i2 & 64) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i3 &= -3670017;
                        } else {
                            contentColor = j2;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i3 = (-29360129) & i3;
                        } else {
                            actionContentColor = j3;
                        }
                        if ((i2 & 256) != 0) {
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            i3 &= -234881025;
                        } else {
                            dismissActionContentColor = j4;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                    }
                    final boolean z119 = z3;
                    final Function2<? super Composer, ? super Integer, Unit> function111118 = function5;
                    final long j111118 = actionContentColor;
                    final Function2<? super Composer, ? super Integer, Unit> function111119 = function7;
                    final long j111119 = dismissActionContentColor;
                    int i1111114 = (i3 & 14) | 12779520;
                    int i1111115 = i3 >> 9;
                    SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                            ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                            if ((i1111116 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1829663446, i1111116, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                                }
                                TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                                final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                                ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                                final boolean z1110 = z119;
                                final Function2<? super Composer, ? super Integer, Unit> function1111110 = function111118;
                                final Function2<? super Composer, ? super Integer, Unit> function1111111 = function4;
                                final Function2<? super Composer, ? super Integer, Unit> function1111112 = function111119;
                                final long j1111110 = j111118;
                                final long j1111111 = j111119;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                    public final void invoke(Composer composer4, int i1111117) {
                                        ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                        if ((i1111117 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(835891690, i1111117, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                            }
                                            if (z1110 && function1111110 != null) {
                                                composer4.startReplaceGroup(-810715387);
                                                ComposerKt.sourceInformation(composer4, "126@5873L383");
                                                SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function1111111, function1111110, function1111112, value2, j1111110, j1111111, composer4, 0);
                                                composer4.endReplaceGroup();
                                            } else {
                                                composer4.startReplaceGroup(-810701708);
                                                ComposerKt.sourceInformation(composer4, "135@6301L366");
                                                SnackbarKt.m2245OneRowSnackbarkKq0p4A(function1111111, function1111110, function1111112, value2, j1111110, j1111111, composer4, 0);
                                                composer4.endReplaceGroup();
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, i1111114 | (i1111115 & 112) | (i1111115 & 896) | (i1111115 & 7168), 80);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z3;
                    function8 = function5;
                    shape4 = shape3;
                    j6 = color;
                    j7 = contentColor;
                    j8 = actionContentColor;
                    function9 = function7;
                    j9 = dismissActionContentColor;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                            SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= BaseAdapter.HEADER;
            if ((i3 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                }
                final boolean z1110 = z3;
                final Function2<? super Composer, ? super Integer, Unit> function1111110 = function5;
                final long j1111110 = actionContentColor;
                final Function2<? super Composer, ? super Integer, Unit> function1111111 = function7;
                final long j1111111 = dismissActionContentColor;
                int i1111116 = (i3 & 14) | 12779520;
                int i1111117 = i3 >> 9;
                SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                    public final void invoke(Composer composer3, int i1111118) {
                        ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                        if ((i1111118 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1829663446, i1111118, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                            }
                            TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z1111 = z1110;
                            final Function2<? super Composer, ? super Integer, Unit> function1111112 = function1111110;
                            final Function2<? super Composer, ? super Integer, Unit> function1111113 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function1111114 = function1111111;
                            final long j1111112 = j1111110;
                            final long j1111113 = j1111111;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                public final void invoke(Composer composer4, int i1111119) {
                                    ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                    if ((i1111119 & 3) != 2 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(835891690, i1111119, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                        }
                                        if (z1111 && function1111112 != null) {
                                            composer4.startReplaceGroup(-810715387);
                                            ComposerKt.sourceInformation(composer4, "126@5873L383");
                                            SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function1111113, function1111112, function1111114, value2, j1111112, j1111113, composer4, 0);
                                            composer4.endReplaceGroup();
                                        } else {
                                            composer4.startReplaceGroup(-810701708);
                                            ComposerKt.sourceInformation(composer4, "135@6301L366");
                                            SnackbarKt.m2245OneRowSnackbarkKq0p4A(function1111113, function1111112, function1111114, value2, j1111112, j1111113, composer4, 0);
                                            composer4.endReplaceGroup();
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, i1111116 | (i1111117 & 112) | (i1111117 & 896) | (i1111117 & 7168), 80);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z3;
                function8 = function5;
                shape4 = shape3;
                j6 = color;
                j7 = contentColor;
                j8 = actionContentColor;
                function9 = function7;
                j9 = dismissActionContentColor;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                }
                final boolean z1111 = z3;
                final Function2<? super Composer, ? super Integer, Unit> function1111112 = function5;
                final long j1111112 = actionContentColor;
                final Function2<? super Composer, ? super Integer, Unit> function1111113 = function7;
                final long j1111113 = dismissActionContentColor;
                int i1111118 = (i3 & 14) | 12779520;
                int i1111119 = i3 >> 9;
                SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                        ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                        if ((i11111110 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1829663446, i11111110, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                            }
                            TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z1112 = z1111;
                            final Function2<? super Composer, ? super Integer, Unit> function1111114 = function1111112;
                            final Function2<? super Composer, ? super Integer, Unit> function1111115 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function1111116 = function1111113;
                            final long j1111114 = j1111112;
                            final long j1111115 = j1111113;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                public final void invoke(Composer composer4, int i11111111) {
                                    ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                    if ((i11111111 & 3) != 2 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(835891690, i11111111, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                        }
                                        if (z1112 && function1111114 != null) {
                                            composer4.startReplaceGroup(-810715387);
                                            ComposerKt.sourceInformation(composer4, "126@5873L383");
                                            SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function1111115, function1111114, function1111116, value2, j1111114, j1111115, composer4, 0);
                                            composer4.endReplaceGroup();
                                        } else {
                                            composer4.startReplaceGroup(-810701708);
                                            ComposerKt.sourceInformation(composer4, "135@6301L366");
                                            SnackbarKt.m2245OneRowSnackbarkKq0p4A(function1111115, function1111114, function1111116, value2, j1111114, j1111115, composer4, 0);
                                            composer4.endReplaceGroup();
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, i1111118 | (i1111119 & 112) | (i1111119 & 896) | (i1111119 & 7168), 80);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z3;
                function8 = function5;
                shape4 = shape3;
                j6 = color;
                j7 = contentColor;
                j8 = actionContentColor;
                function9 = function7;
                j9 = dismissActionContentColor;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                        SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                shape2 = shape;
                if (composerStartRestartGroup.changed(shape2)) {
                }
                i3 |= i14;
            } else {
                shape2 = shape;
            }
            i3 |= i14;
        } else {
            shape2 = shape;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                j5 = j;
                if (composerStartRestartGroup.changed(j5)) {
                }
                i3 |= i15;
            } else {
                j5 = j;
            }
            i3 |= i15;
        } else {
            j5 = j;
        }
        if ((i & 1572864) != 0) {
            if ((i2 & 64) == 0) {
                i11 = 524288;
            } else {
                i11 = 524288;
            }
            i3 |= i11;
        }
        if ((i & 12582912) != 0) {
            if ((i2 & 128) == 0) {
                i10 = 4194304;
            } else {
                i10 = 4194304;
            }
            i3 |= i10;
        }
        if ((100663296 & i) != 0) {
            if ((i2 & 256) == 0) {
                i9 = 33554432;
            } else {
                i9 = 33554432;
            }
            i3 |= i9;
        }
        if ((i2 & 512) != 0) {
            if ((i & BaseAdapter.HEADER) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i8 = 536870912;
                } else {
                    i8 = 268435456;
                }
                i3 |= i8;
            }
            if ((i3 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                }
                final boolean z1112 = z3;
                final Function2<? super Composer, ? super Integer, Unit> function1111114 = function5;
                final long j1111114 = actionContentColor;
                final Function2<? super Composer, ? super Integer, Unit> function1111115 = function7;
                final long j1111115 = dismissActionContentColor;
                int i11111110 = (i3 & 14) | 12779520;
                int i11111111 = i3 >> 9;
                SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                    public final void invoke(Composer composer3, int i11111112) {
                        ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                        if ((i11111112 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1829663446, i11111112, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                            }
                            TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z1113 = z1112;
                            final Function2<? super Composer, ? super Integer, Unit> function1111116 = function1111114;
                            final Function2<? super Composer, ? super Integer, Unit> function1111117 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function1111118 = function1111115;
                            final long j1111116 = j1111114;
                            final long j1111117 = j1111115;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                public final void invoke(Composer composer4, int i11111113) {
                                    ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                    if ((i11111113 & 3) != 2 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(835891690, i11111113, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                        }
                                        if (z1113 && function1111116 != null) {
                                            composer4.startReplaceGroup(-810715387);
                                            ComposerKt.sourceInformation(composer4, "126@5873L383");
                                            SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function1111117, function1111116, function1111118, value2, j1111116, j1111117, composer4, 0);
                                            composer4.endReplaceGroup();
                                        } else {
                                            composer4.startReplaceGroup(-810701708);
                                            ComposerKt.sourceInformation(composer4, "135@6301L366");
                                            SnackbarKt.m2245OneRowSnackbarkKq0p4A(function1111117, function1111116, function1111118, value2, j1111116, j1111117, composer4, 0);
                                            composer4.endReplaceGroup();
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, i11111110 | (i11111111 & 112) | (i11111111 & 896) | (i11111111 & 7168), 80);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z3;
                function8 = function5;
                shape4 = shape3;
                j6 = color;
                j7 = contentColor;
                j8 = actionContentColor;
                function9 = function7;
                j9 = dismissActionContentColor;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                } else {
                    if (i12 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i13 != 0) {
                        function5 = null;
                    }
                    if (i4 == 0) {
                    }
                    if (i6 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 16) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 32) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        color = j5;
                    }
                    if ((i2 & 64) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i3 &= -3670017;
                    } else {
                        contentColor = j2;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i3 = (-29360129) & i3;
                    } else {
                        actionContentColor = j3;
                    }
                    if ((i2 & 256) != 0) {
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        i3 &= -234881025;
                    } else {
                        dismissActionContentColor = j4;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
                }
                final boolean z1113 = z3;
                final Function2<? super Composer, ? super Integer, Unit> function1111116 = function5;
                final long j1111116 = actionContentColor;
                final Function2<? super Composer, ? super Integer, Unit> function1111117 = function7;
                final long j1111117 = dismissActionContentColor;
                int i11111112 = (i3 & 14) | 12779520;
                int i11111113 = i3 >> 9;
                SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                        ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                        if ((i11111114 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1829663446, i11111114, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                            }
                            TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                            final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                            ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                            final boolean z1114 = z1113;
                            final Function2<? super Composer, ? super Integer, Unit> function1111118 = function1111116;
                            final Function2<? super Composer, ? super Integer, Unit> function1111119 = function4;
                            final Function2<? super Composer, ? super Integer, Unit> function11111110 = function1111117;
                            final long j1111118 = j1111116;
                            final long j1111119 = j1111117;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                                public final void invoke(Composer composer4, int i11111115) {
                                    ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                    if ((i11111115 & 3) != 2 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(835891690, i11111115, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                        }
                                        if (z1114 && function1111118 != null) {
                                            composer4.startReplaceGroup(-810715387);
                                            ComposerKt.sourceInformation(composer4, "126@5873L383");
                                            SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function1111119, function1111118, function11111110, value2, j1111118, j1111119, composer4, 0);
                                            composer4.endReplaceGroup();
                                        } else {
                                            composer4.startReplaceGroup(-810701708);
                                            ComposerKt.sourceInformation(composer4, "135@6301L366");
                                            SnackbarKt.m2245OneRowSnackbarkKq0p4A(function1111119, function1111118, function11111110, value2, j1111118, j1111119, composer4, 0);
                                            composer4.endReplaceGroup();
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, i11111112 | (i11111113 & 112) | (i11111113 & 896) | (i11111113 & 7168), 80);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z3;
                function8 = function5;
                shape4 = shape3;
                j6 = color;
                j7 = contentColor;
                j8 = actionContentColor;
                function9 = function7;
                j9 = dismissActionContentColor;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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
                        SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= BaseAdapter.HEADER;
        if ((i3 & 306783379) == 306783378) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier;
                }
                if (i13 != 0) {
                    function5 = null;
                }
                if (i4 == 0) {
                }
                if (i6 != 0) {
                    z3 = false;
                } else {
                    z3 = z2;
                }
                if ((i2 & 16) != 0) {
                    shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 32) != 0) {
                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    i3 &= -458753;
                } else {
                    color = j5;
                }
                if ((i2 & 64) != 0) {
                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    contentColor = j2;
                }
                if ((i2 & 128) != 0) {
                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                    i3 = (-29360129) & i3;
                } else {
                    actionContentColor = j3;
                }
                if ((i2 & 256) != 0) {
                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    i3 &= -234881025;
                } else {
                    dismissActionContentColor = j4;
                }
            } else {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier;
                }
                if (i13 != 0) {
                    function5 = null;
                }
                if (i4 == 0) {
                }
                if (i6 != 0) {
                    z3 = false;
                } else {
                    z3 = z2;
                }
                if ((i2 & 16) != 0) {
                    shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 32) != 0) {
                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    i3 &= -458753;
                } else {
                    color = j5;
                }
                if ((i2 & 64) != 0) {
                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    contentColor = j2;
                }
                if ((i2 & 128) != 0) {
                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                    i3 = (-29360129) & i3;
                } else {
                    actionContentColor = j3;
                }
                if ((i2 & 256) != 0) {
                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    i3 &= -234881025;
                } else {
                    dismissActionContentColor = j4;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
            }
            final boolean z1114 = z3;
            final Function2<? super Composer, ? super Integer, Unit> function1111118 = function5;
            final long j1111118 = actionContentColor;
            final Function2<? super Composer, ? super Integer, Unit> function1111119 = function7;
            final long j1111119 = dismissActionContentColor;
            int i11111114 = (i3 & 14) | 12779520;
            int i11111115 = i3 >> 9;
            SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                public final void invoke(Composer composer3, int i11111116) {
                    ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                    if ((i11111116 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1829663446, i11111116, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                        }
                        TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                        final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                        ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                        final boolean z1115 = z1114;
                        final Function2<? super Composer, ? super Integer, Unit> function11111110 = function1111118;
                        final Function2<? super Composer, ? super Integer, Unit> function11111111 = function4;
                        final Function2<? super Composer, ? super Integer, Unit> function11111112 = function1111119;
                        final long j11111110 = j1111118;
                        final long j11111111 = j1111119;
                        CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                            public final void invoke(Composer composer4, int i11111117) {
                                ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                if ((i11111117 & 3) != 2 || !composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(835891690, i11111117, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                    }
                                    if (z1115 && function11111110 != null) {
                                        composer4.startReplaceGroup(-810715387);
                                        ComposerKt.sourceInformation(composer4, "126@5873L383");
                                        SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function11111111, function11111110, function11111112, value2, j11111110, j11111111, composer4, 0);
                                        composer4.endReplaceGroup();
                                    } else {
                                        composer4.startReplaceGroup(-810701708);
                                        ComposerKt.sourceInformation(composer4, "135@6301L366");
                                        SnackbarKt.m2245OneRowSnackbarkKq0p4A(function11111111, function11111110, function11111112, value2, j11111110, j11111111, composer4, 0);
                                        composer4.endReplaceGroup();
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer4.skipToGroupEnd();
                            }
                        }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, i11111114 | (i11111115 & 112) | (i11111115 & 896) | (i11111115 & 7168), 80);
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z4 = z3;
            function8 = function5;
            shape4 = shape3;
            j6 = color;
            j7 = contentColor;
            j8 = actionContentColor;
            function9 = function7;
            j9 = dismissActionContentColor;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier;
                }
                if (i13 != 0) {
                    function5 = null;
                }
                if (i4 == 0) {
                }
                if (i6 != 0) {
                    z3 = false;
                } else {
                    z3 = z2;
                }
                if ((i2 & 16) != 0) {
                    shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 32) != 0) {
                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    i3 &= -458753;
                } else {
                    color = j5;
                }
                if ((i2 & 64) != 0) {
                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    contentColor = j2;
                }
                if ((i2 & 128) != 0) {
                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                    i3 = (-29360129) & i3;
                } else {
                    actionContentColor = j3;
                }
                if ((i2 & 256) != 0) {
                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    i3 &= -234881025;
                } else {
                    dismissActionContentColor = j4;
                }
            } else {
                if (i12 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier;
                }
                if (i13 != 0) {
                    function5 = null;
                }
                if (i4 == 0) {
                }
                if (i6 != 0) {
                    z3 = false;
                } else {
                    z3 = z2;
                }
                if ((i2 & 16) != 0) {
                    shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 32) != 0) {
                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    i3 &= -458753;
                } else {
                    color = j5;
                }
                if ((i2 & 64) != 0) {
                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i3 &= -3670017;
                } else {
                    contentColor = j2;
                }
                if ((i2 & 128) != 0) {
                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                    i3 = (-29360129) & i3;
                } else {
                    actionContentColor = j3;
                }
                if ((i2 & 256) != 0) {
                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    i3 &= -234881025;
                } else {
                    dismissActionContentColor = j4;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1235788955, i3, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
            }
            final boolean z1115 = z3;
            final Function2<? super Composer, ? super Integer, Unit> function11111110 = function5;
            final long j11111110 = actionContentColor;
            final Function2<? super Composer, ? super Integer, Unit> function11111111 = function7;
            final long j11111111 = dismissActionContentColor;
            int i11111116 = (i3 & 14) | 12779520;
            int i11111117 = i3 >> 9;
            SurfaceKt.m2261SurfaceT9BRK9s(companion, shape3, color, contentColor, 0.0f, SnackbarTokens.INSTANCE.m3250getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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

                public final void invoke(Composer composer3, int i11111118) {
                    ComposerKt.sourceInformation(composer3, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                    if ((i11111118 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1829663446, i11111118, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                        }
                        TextStyle value = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), composer3, 6);
                        final TextStyle value2 = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), composer3, 6);
                        ProvidedValue<TextStyle> providedValueProvides = TextKt.getLocalTextStyle().provides(value);
                        final boolean z1116 = z1115;
                        final Function2<? super Composer, ? super Integer, Unit> function11111112 = function11111110;
                        final Function2<? super Composer, ? super Integer, Unit> function11111113 = function4;
                        final Function2<? super Composer, ? super Integer, Unit> function11111114 = function11111111;
                        final long j11111112 = j11111110;
                        final long j11111113 = j11111111;
                        CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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

                            public final void invoke(Composer composer4, int i11111119) {
                                ComposerKt.sourceInformation(composer4, "C:Snackbar.kt#uh7d8r");
                                if ((i11111119 & 3) != 2 || !composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(835891690, i11111119, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                    }
                                    if (z1116 && function11111112 != null) {
                                        composer4.startReplaceGroup(-810715387);
                                        ComposerKt.sourceInformation(composer4, "126@5873L383");
                                        SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function11111113, function11111112, function11111114, value2, j11111112, j11111113, composer4, 0);
                                        composer4.endReplaceGroup();
                                    } else {
                                        composer4.startReplaceGroup(-810701708);
                                        ComposerKt.sourceInformation(composer4, "135@6301L366");
                                        SnackbarKt.m2245OneRowSnackbarkKq0p4A(function11111113, function11111112, function11111114, value2, j11111112, j11111113, composer4, 0);
                                        composer4.endReplaceGroup();
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer4.skipToGroupEnd();
                            }
                        }, composer3, 54), composer3, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, i11111116 | (i11111117 & 112) | (i11111117 & 896) | (i11111117 & 7168), 80);
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z4 = z3;
            function8 = function5;
            shape4 = shape3;
            j6 = color;
            j7 = contentColor;
            j8 = actionContentColor;
            function9 = function7;
            j9 = dismissActionContentColor;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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

                public final void invoke(Composer composer3, int i11111118) {
                    SnackbarKt.m2246SnackbareQBnUkQ(companion, function8, function9, z4, shape4, j6, j7, j8, j9, function4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0119  */
    /* JADX WARN: Code duplicated, block: B:105:0x0132  */
    /* JADX WARN: Code duplicated, block: B:107:0x0148  */
    /* JADX WARN: Code duplicated, block: B:129:0x0185 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:130:0x0187  */
    /* JADX WARN: Code duplicated, block: B:131:0x018c  */
    /* JADX WARN: Code duplicated, block: B:133:0x018f  */
    /* JADX WARN: Code duplicated, block: B:134:0x0191  */
    /* JADX WARN: Code duplicated, block: B:137:0x0197  */
    /* JADX WARN: Code duplicated, block: B:138:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:141:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:142:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:145:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:146:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:149:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:150:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:153:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:154:0x01d9  */
    /* JADX WARN: Code duplicated, block: B:157:0x01df  */
    /* JADX WARN: Code duplicated, block: B:158:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:161:0x020c  */
    /* JADX WARN: Code duplicated, block: B:164:0x022e  */
    /* JADX WARN: Code duplicated, block: B:165:0x023f  */
    /* JADX WARN: Code duplicated, block: B:168:0x0259  */
    /* JADX WARN: Code duplicated, block: B:171:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:175:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:177:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004b  */
    /* JADX WARN: Code duplicated, block: B:27:0x004e  */
    /* JADX WARN: Code duplicated, block: B:29:0x0052  */
    /* JADX WARN: Code duplicated, block: B:31:0x005a  */
    /* JADX WARN: Code duplicated, block: B:32:0x005d  */
    /* JADX WARN: Code duplicated, block: B:37:0x0067  */
    /* JADX WARN: Code duplicated, block: B:39:0x006b  */
    /* JADX WARN: Code duplicated, block: B:41:0x0073  */
    /* JADX WARN: Code duplicated, block: B:42:0x0076  */
    /* JADX WARN: Code duplicated, block: B:45:0x007c  */
    /* JADX WARN: Code duplicated, block: B:48:0x0082  */
    /* JADX WARN: Code duplicated, block: B:50:0x0086  */
    /* JADX WARN: Code duplicated, block: B:52:0x008e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0091  */
    /* JADX WARN: Code duplicated, block: B:56:0x0097  */
    /* JADX WARN: Code duplicated, block: B:59:0x009e  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:63:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:70:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:72:0x00be  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:91:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:93:0x0101  */
    /* JADX WARN: Code duplicated, block: B:96:0x010a  */
    /* JADX WARN: Code duplicated, block: B:98:0x010e  */
    /* JADX INFO: renamed from: Snackbar-sDKtq54, reason: not valid java name */
    public static final void m2247SnackbarsDKtq54(final SnackbarData snackbarData, Modifier modifier, boolean z, Shape shape, long j, long j2, long j3, long j4, long j5, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        boolean z2;
        int i5;
        Shape shape2;
        long j6;
        long j7;
        int i6;
        int i7;
        final Modifier.Companion companion;
        boolean z3;
        Shape shape3;
        long color;
        long contentColor;
        long actionColor;
        long actionContentColor;
        long dismissActionContentColor;
        long j8;
        final long j9;
        long j10;
        boolean z4;
        Shape shape4;
        long j11;
        final String actionLabel;
        ComposableLambda composableLambdaRememberComposableLambda;
        Composer composer2;
        final long j12;
        final boolean z5;
        final Shape shape5;
        final long j13;
        final long j14;
        final long j15;
        final long j16;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i8;
        int i9;
        int i10;
        Composer composerStartRestartGroup = composer.startRestartGroup(274621471);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Snackbar)P(8,6,2,7,3:c#ui.graphics.Color,4:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,5:c#ui.graphics.Color)205@9602L5,206@9654L5,207@9704L12,208@9760L11,209@9822L18,210@9898L25,251@11371L38,241@10959L456:Snackbar.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(snackbarData) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i11 = i2 & 2;
        if (i11 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        shape2 = shape;
                        int i12 = composerStartRestartGroup.changed(shape2) ? 2048 : 1024;
                        i3 |= i12;
                    } else {
                        shape2 = shape;
                    }
                    i3 |= i12;
                } else {
                    shape2 = shape;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        j6 = j;
                        int i13 = composerStartRestartGroup.changed(j6) ? 16384 : 8192;
                        i3 |= i13;
                    } else {
                        j6 = j;
                    }
                    i3 |= i13;
                } else {
                    j6 = j;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        j7 = j2;
                        int i14 = composerStartRestartGroup.changed(j7) ? 131072 : 65536;
                        i3 |= i14;
                    } else {
                        j7 = j2;
                    }
                    i3 |= i14;
                } else {
                    j7 = j2;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        i10 = i3;
                        i7 = i11;
                        int i15 = composerStartRestartGroup.changed(j3) ? 1048576 : 524288;
                        i6 = i10 | i15;
                    } else {
                        i10 = i3;
                        i7 = i11;
                    }
                    i6 = i10 | i15;
                } else {
                    i6 = i3;
                    i7 = i11;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0 || !composerStartRestartGroup.changed(j4)) {
                        i9 = 4194304;
                    } else {
                        i9 = 8388608;
                    }
                    i6 |= i9;
                }
                if ((100663296 & i) != 0) {
                    if ((i2 & 256) == 0 || !composerStartRestartGroup.changed(j5)) {
                        i8 = 33554432;
                    } else {
                        i8 = 67108864;
                    }
                    i6 |= i8;
                }
                if ((38347923 & i6) == 38347922 || !composerStartRestartGroup.getSkipping()) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i7 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i4 != 0) {
                            z3 = false;
                        } else {
                            z3 = z2;
                        }
                        if ((i2 & 8) != 0) {
                            shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            i6 &= -7169;
                        } else {
                            shape3 = shape2;
                        }
                        if ((i2 & 16) != 0) {
                            color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                            i6 &= -57345;
                        } else {
                            color = j6;
                        }
                        if ((i2 & 32) != 0) {
                            contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                            i6 &= -458753;
                        } else {
                            contentColor = j7;
                        }
                        if ((i2 & 64) != 0) {
                            actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                            i6 &= -3670017;
                        } else {
                            actionColor = j3;
                        }
                        if ((i2 & 128) != 0) {
                            actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                            i6 = (-29360129) & i6;
                        } else {
                            actionContentColor = j4;
                        }
                        if ((i2 & 256) != 0) {
                            i6 &= -234881025;
                            j9 = actionColor;
                            j10 = actionContentColor;
                            dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                            z4 = z3;
                            shape4 = shape3;
                            j11 = color;
                            j8 = contentColor;
                        } else {
                            dismissActionContentColor = j5;
                            j8 = contentColor;
                            j9 = actionColor;
                            j10 = actionContentColor;
                            z4 = z3;
                            shape4 = shape3;
                            j11 = color;
                        }
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 8) != 0) {
                            i6 &= -7169;
                        }
                        if ((i2 & 16) != 0) {
                            i6 &= -57345;
                        }
                        if ((i2 & 32) != 0) {
                            i6 &= -458753;
                        }
                        if ((i2 & 64) != 0) {
                            i6 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i6 &= -29360129;
                        }
                        if ((i2 & 256) != 0) {
                            i6 &= -234881025;
                        }
                        j9 = j3;
                        j10 = j4;
                        dismissActionContentColor = j5;
                        companion = modifier2;
                        z4 = z2;
                        shape4 = shape2;
                        j11 = j6;
                        j8 = j7;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(274621471, i6, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:211)");
                    }
                    actionLabel = snackbarData.getVisuals().getActionLabel();
                    composerStartRestartGroup.startReplaceGroup(1561344786);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "215@10097L267");
                    if (actionLabel != null) {
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1378313599, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1
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
                                ComposerKt.sourceInformation(composer3, "C217@10171L44,218@10247L32,219@10311L21,216@10115L235:Snackbar.kt#uh7d8r");
                                if ((i16 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1378313599, i16, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:216)");
                                    }
                                    ButtonColors buttonColorsM1537textButtonColorsro_MJ88 = ButtonDefaults.INSTANCE.m1537textButtonColorsro_MJ88(0L, j9, 0L, 0L, composer3, 24576, 13);
                                    ComposerKt.sourceInformationMarkerStart(composer3, 642119911, "CC(remember):Snackbar.kt#9igjgp");
                                    boolean zChanged = composer3.changed(snackbarData);
                                    final SnackbarData snackbarData2 = snackbarData;
                                    Object objRememberedValue = composer3.rememberedValue();
                                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1$1$1
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
                                                snackbarData2.performAction();
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    final String str = actionLabel;
                                    ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, buttonColorsM1537textButtonColorsro_MJ88, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(521110564, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1.2
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer4, Integer num) {
                                            invoke(rowScope, composer4, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(RowScope rowScope, Composer composer4, int i17) {
                                            ComposerKt.sourceInformation(composer4, "C219@10313L17:Snackbar.kt#uh7d8r");
                                            if ((i17 & 17) == 16 && composer4.getSkipping()) {
                                                composer4.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(521110564, i17, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:219)");
                                            }
                                            TextKt.m2411Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer4, 0, 0, 131070);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    }, composer3, 54), composer3, BaseAdapter.HEADER, 494);
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
                    composerStartRestartGroup.startReplaceGroup(1561358724);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "227@10548L362");
                    ComposableLambda composableLambdaRememberComposableLambda2 = snackbarData.getVisuals().getWithDismissAction() ? ComposableLambdaKt.rememberComposableLambda(-1812633777, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$dismissActionComposable$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i16) {
                            ComposerKt.sourceInformation(composer3, "C229@10608L26,228@10566L330:Snackbar.kt#uh7d8r");
                            if ((i16 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1812633777, i16, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:228)");
                                }
                                ComposerKt.sourceInformationMarkerStart(composer3, 642131457, "CC(remember):Snackbar.kt#9igjgp");
                                boolean zChanged = composer3.changed(snackbarData);
                                final SnackbarData snackbarData2 = snackbarData;
                                Object objRememberedValue = composer3.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$dismissActionComposable$1$1$1
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
                                            snackbarData2.dismiss();
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, ComposableSingletons$SnackbarKt.INSTANCE.m1699getLambda1$material3_release(), composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, composerStartRestartGroup, 54) : null;
                    composerStartRestartGroup.endReplaceGroup();
                    int i16 = i6 << 3;
                    composer2 = composerStartRestartGroup;
                    m2246SnackbareQBnUkQ(PaddingKt.m720padding3ABfNKs(companion, Dp.m6890constructorimpl(12.0f)), composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, z4, shape4, j11, j8, j10, dismissActionContentColor, ComposableLambdaKt.rememberComposableLambda(-1266389126, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$3
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i17) {
                            ComposerKt.sourceInformation(composer3, "C251@11373L34:Snackbar.kt#uh7d8r");
                            if ((i17 & 3) == 2 && composer3.getSkipping()) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1266389126, i17, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:251)");
                            }
                            TextKt.m2411Text4IGK_g(snackbarData.getVisuals().getMessage(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composerStartRestartGroup, 54), composer2, (i16 & 3670016) | (i16 & 7168) | BaseAdapter.HEADER | (57344 & i16) | (458752 & i16) | (29360128 & i6) | (234881024 & i6), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j12 = j9;
                    z5 = z4;
                    shape5 = shape4;
                    j13 = j11;
                    j14 = j8;
                    j15 = j10;
                    j16 = dismissActionContentColor;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    composer2 = composerStartRestartGroup;
                    companion = modifier2;
                    z5 = z2;
                    shape5 = shape2;
                    j13 = j6;
                    j14 = j7;
                    j12 = j3;
                    j15 = j4;
                    j16 = j5;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$4
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
                            SnackbarKt.m2247SnackbarsDKtq54(snackbarData, companion, z5, shape5, j13, j14, j12, j15, j16, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            z2 = z;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i12;
                } else {
                    shape2 = shape;
                }
                i3 |= i12;
            } else {
                shape2 = shape;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    j6 = j;
                    if (composerStartRestartGroup.changed(j6)) {
                    }
                    i3 |= i13;
                } else {
                    j6 = j;
                }
                i3 |= i13;
            } else {
                j6 = j;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    j7 = j2;
                    if (composerStartRestartGroup.changed(j7)) {
                    }
                    i3 |= i14;
                } else {
                    j7 = j2;
                }
                i3 |= i14;
            } else {
                j7 = j2;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    i10 = i3;
                    i7 = i11;
                    if (composerStartRestartGroup.changed(j3)) {
                    }
                    i6 = i10 | i15;
                } else {
                    i10 = i3;
                    i7 = i11;
                }
                i6 = i10 | i15;
            } else {
                i6 = i3;
                i7 = i11;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i9 = 4194304;
                } else {
                    i9 = 4194304;
                }
                i6 |= i9;
            }
            if ((100663296 & i) != 0) {
                if ((i2 & 256) == 0) {
                    i8 = 33554432;
                } else {
                    i8 = 33554432;
                }
                i6 |= i8;
            }
            if ((38347923 & i6) == 38347922) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 8) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i6 &= -7169;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i6 &= -57345;
                    } else {
                        color = j6;
                    }
                    if ((i2 & 32) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i6 &= -458753;
                    } else {
                        contentColor = j7;
                    }
                    if ((i2 & 64) != 0) {
                        actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                        i6 &= -3670017;
                    } else {
                        actionColor = j3;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i6 = (-29360129) & i6;
                    } else {
                        actionContentColor = j4;
                    }
                    if ((i2 & 256) != 0) {
                        i6 &= -234881025;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                        j8 = contentColor;
                    } else {
                        dismissActionContentColor = j5;
                        j8 = contentColor;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                    }
                } else {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 8) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i6 &= -7169;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i6 &= -57345;
                    } else {
                        color = j6;
                    }
                    if ((i2 & 32) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i6 &= -458753;
                    } else {
                        contentColor = j7;
                    }
                    if ((i2 & 64) != 0) {
                        actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                        i6 &= -3670017;
                    } else {
                        actionColor = j3;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i6 = (-29360129) & i6;
                    } else {
                        actionContentColor = j4;
                    }
                    if ((i2 & 256) != 0) {
                        i6 &= -234881025;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                        j8 = contentColor;
                    } else {
                        dismissActionContentColor = j5;
                        j8 = contentColor;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(274621471, i6, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:211)");
                }
                actionLabel = snackbarData.getVisuals().getActionLabel();
                composerStartRestartGroup.startReplaceGroup(1561344786);
                ComposerKt.sourceInformation(composerStartRestartGroup, "215@10097L267");
                if (actionLabel != null) {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1378313599, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1
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
                            ComposerKt.sourceInformation(composer3, "C217@10171L44,218@10247L32,219@10311L21,216@10115L235:Snackbar.kt#uh7d8r");
                            if ((i17 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1378313599, i17, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:216)");
                                }
                                ButtonColors buttonColorsM1537textButtonColorsro_MJ88 = ButtonDefaults.INSTANCE.m1537textButtonColorsro_MJ88(0L, j9, 0L, 0L, composer3, 24576, 13);
                                ComposerKt.sourceInformationMarkerStart(composer3, 642119911, "CC(remember):Snackbar.kt#9igjgp");
                                boolean zChanged = composer3.changed(snackbarData);
                                final SnackbarData snackbarData2 = snackbarData;
                                Object objRememberedValue = composer3.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1$1$1
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
                                            snackbarData2.performAction();
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                final String str = actionLabel;
                                ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, buttonColorsM1537textButtonColorsro_MJ88, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(521110564, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer4, Integer num) {
                                        invoke(rowScope, composer4, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(RowScope rowScope, Composer composer4, int i18) {
                                        ComposerKt.sourceInformation(composer4, "C219@10313L17:Snackbar.kt#uh7d8r");
                                        if ((i18 & 17) == 16 && composer4.getSkipping()) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(521110564, i18, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:219)");
                                        }
                                        TextKt.m2411Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer4, 0, 0, 131070);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer3, 54), composer3, BaseAdapter.HEADER, 494);
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
                composerStartRestartGroup.startReplaceGroup(1561358724);
                ComposerKt.sourceInformation(composerStartRestartGroup, "227@10548L362");
                if (snackbarData.getVisuals().getWithDismissAction()) {
                }
                composerStartRestartGroup.endReplaceGroup();
                int i17 = i6 << 3;
                composer2 = composerStartRestartGroup;
                m2246SnackbareQBnUkQ(PaddingKt.m720padding3ABfNKs(companion, Dp.m6890constructorimpl(12.0f)), composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, z4, shape4, j11, j8, j10, dismissActionContentColor, ComposableLambdaKt.rememberComposableLambda(-1266389126, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$3
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i18) {
                        ComposerKt.sourceInformation(composer3, "C251@11373L34:Snackbar.kt#uh7d8r");
                        if ((i18 & 3) == 2 && composer3.getSkipping()) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1266389126, i18, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:251)");
                        }
                        TextKt.m2411Text4IGK_g(snackbarData.getVisuals().getMessage(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composer2, (i17 & 3670016) | (i17 & 7168) | BaseAdapter.HEADER | (57344 & i17) | (458752 & i17) | (29360128 & i6) | (234881024 & i6), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j12 = j9;
                z5 = z4;
                shape5 = shape4;
                j13 = j11;
                j14 = j8;
                j15 = j10;
                j16 = dismissActionContentColor;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 8) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i6 &= -7169;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i6 &= -57345;
                    } else {
                        color = j6;
                    }
                    if ((i2 & 32) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i6 &= -458753;
                    } else {
                        contentColor = j7;
                    }
                    if ((i2 & 64) != 0) {
                        actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                        i6 &= -3670017;
                    } else {
                        actionColor = j3;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i6 = (-29360129) & i6;
                    } else {
                        actionContentColor = j4;
                    }
                    if ((i2 & 256) != 0) {
                        i6 &= -234881025;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                        j8 = contentColor;
                    } else {
                        dismissActionContentColor = j5;
                        j8 = contentColor;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                    }
                } else {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 8) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i6 &= -7169;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i6 &= -57345;
                    } else {
                        color = j6;
                    }
                    if ((i2 & 32) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i6 &= -458753;
                    } else {
                        contentColor = j7;
                    }
                    if ((i2 & 64) != 0) {
                        actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                        i6 &= -3670017;
                    } else {
                        actionColor = j3;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i6 = (-29360129) & i6;
                    } else {
                        actionContentColor = j4;
                    }
                    if ((i2 & 256) != 0) {
                        i6 &= -234881025;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                        j8 = contentColor;
                    } else {
                        dismissActionContentColor = j5;
                        j8 = contentColor;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(274621471, i6, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:211)");
                }
                actionLabel = snackbarData.getVisuals().getActionLabel();
                composerStartRestartGroup.startReplaceGroup(1561344786);
                ComposerKt.sourceInformation(composerStartRestartGroup, "215@10097L267");
                if (actionLabel != null) {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1378313599, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1
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
                            ComposerKt.sourceInformation(composer3, "C217@10171L44,218@10247L32,219@10311L21,216@10115L235:Snackbar.kt#uh7d8r");
                            if ((i18 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1378313599, i18, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:216)");
                                }
                                ButtonColors buttonColorsM1537textButtonColorsro_MJ88 = ButtonDefaults.INSTANCE.m1537textButtonColorsro_MJ88(0L, j9, 0L, 0L, composer3, 24576, 13);
                                ComposerKt.sourceInformationMarkerStart(composer3, 642119911, "CC(remember):Snackbar.kt#9igjgp");
                                boolean zChanged = composer3.changed(snackbarData);
                                final SnackbarData snackbarData2 = snackbarData;
                                Object objRememberedValue = composer3.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1$1$1
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
                                            snackbarData2.performAction();
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                final String str = actionLabel;
                                ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, buttonColorsM1537textButtonColorsro_MJ88, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(521110564, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer4, Integer num) {
                                        invoke(rowScope, composer4, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(RowScope rowScope, Composer composer4, int i19) {
                                        ComposerKt.sourceInformation(composer4, "C219@10313L17:Snackbar.kt#uh7d8r");
                                        if ((i19 & 17) == 16 && composer4.getSkipping()) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(521110564, i19, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:219)");
                                        }
                                        TextKt.m2411Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer4, 0, 0, 131070);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer3, 54), composer3, BaseAdapter.HEADER, 494);
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
                composerStartRestartGroup.startReplaceGroup(1561358724);
                ComposerKt.sourceInformation(composerStartRestartGroup, "227@10548L362");
                if (snackbarData.getVisuals().getWithDismissAction()) {
                }
                composerStartRestartGroup.endReplaceGroup();
                int i18 = i6 << 3;
                composer2 = composerStartRestartGroup;
                m2246SnackbareQBnUkQ(PaddingKt.m720padding3ABfNKs(companion, Dp.m6890constructorimpl(12.0f)), composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, z4, shape4, j11, j8, j10, dismissActionContentColor, ComposableLambdaKt.rememberComposableLambda(-1266389126, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$3
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i19) {
                        ComposerKt.sourceInformation(composer3, "C251@11373L34:Snackbar.kt#uh7d8r");
                        if ((i19 & 3) == 2 && composer3.getSkipping()) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1266389126, i19, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:251)");
                        }
                        TextKt.m2411Text4IGK_g(snackbarData.getVisuals().getMessage(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composer2, (i18 & 3670016) | (i18 & 7168) | BaseAdapter.HEADER | (57344 & i18) | (458752 & i18) | (29360128 & i6) | (234881024 & i6), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j12 = j9;
                z5 = z4;
                shape5 = shape4;
                j13 = j11;
                j14 = j8;
                j15 = j10;
                j16 = dismissActionContentColor;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$4
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
                        SnackbarKt.m2247SnackbarsDKtq54(snackbarData, companion, z5, shape5, j13, j14, j12, j15, j16, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i12;
                } else {
                    shape2 = shape;
                }
                i3 |= i12;
            } else {
                shape2 = shape;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    j6 = j;
                    if (composerStartRestartGroup.changed(j6)) {
                    }
                    i3 |= i13;
                } else {
                    j6 = j;
                }
                i3 |= i13;
            } else {
                j6 = j;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    j7 = j2;
                    if (composerStartRestartGroup.changed(j7)) {
                    }
                    i3 |= i14;
                } else {
                    j7 = j2;
                }
                i3 |= i14;
            } else {
                j7 = j2;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    i10 = i3;
                    i7 = i11;
                    if (composerStartRestartGroup.changed(j3)) {
                    }
                    i6 = i10 | i15;
                } else {
                    i10 = i3;
                    i7 = i11;
                }
                i6 = i10 | i15;
            } else {
                i6 = i3;
                i7 = i11;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i9 = 4194304;
                } else {
                    i9 = 4194304;
                }
                i6 |= i9;
            }
            if ((100663296 & i) != 0) {
                if ((i2 & 256) == 0) {
                    i8 = 33554432;
                } else {
                    i8 = 33554432;
                }
                i6 |= i8;
            }
            if ((38347923 & i6) == 38347922) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 8) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i6 &= -7169;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i6 &= -57345;
                    } else {
                        color = j6;
                    }
                    if ((i2 & 32) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i6 &= -458753;
                    } else {
                        contentColor = j7;
                    }
                    if ((i2 & 64) != 0) {
                        actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                        i6 &= -3670017;
                    } else {
                        actionColor = j3;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i6 = (-29360129) & i6;
                    } else {
                        actionContentColor = j4;
                    }
                    if ((i2 & 256) != 0) {
                        i6 &= -234881025;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                        j8 = contentColor;
                    } else {
                        dismissActionContentColor = j5;
                        j8 = contentColor;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                    }
                } else {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 8) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i6 &= -7169;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i6 &= -57345;
                    } else {
                        color = j6;
                    }
                    if ((i2 & 32) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i6 &= -458753;
                    } else {
                        contentColor = j7;
                    }
                    if ((i2 & 64) != 0) {
                        actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                        i6 &= -3670017;
                    } else {
                        actionColor = j3;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i6 = (-29360129) & i6;
                    } else {
                        actionContentColor = j4;
                    }
                    if ((i2 & 256) != 0) {
                        i6 &= -234881025;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                        j8 = contentColor;
                    } else {
                        dismissActionContentColor = j5;
                        j8 = contentColor;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(274621471, i6, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:211)");
                }
                actionLabel = snackbarData.getVisuals().getActionLabel();
                composerStartRestartGroup.startReplaceGroup(1561344786);
                ComposerKt.sourceInformation(composerStartRestartGroup, "215@10097L267");
                if (actionLabel != null) {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1378313599, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1
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
                            ComposerKt.sourceInformation(composer3, "C217@10171L44,218@10247L32,219@10311L21,216@10115L235:Snackbar.kt#uh7d8r");
                            if ((i19 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1378313599, i19, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:216)");
                                }
                                ButtonColors buttonColorsM1537textButtonColorsro_MJ88 = ButtonDefaults.INSTANCE.m1537textButtonColorsro_MJ88(0L, j9, 0L, 0L, composer3, 24576, 13);
                                ComposerKt.sourceInformationMarkerStart(composer3, 642119911, "CC(remember):Snackbar.kt#9igjgp");
                                boolean zChanged = composer3.changed(snackbarData);
                                final SnackbarData snackbarData2 = snackbarData;
                                Object objRememberedValue = composer3.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1$1$1
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
                                            snackbarData2.performAction();
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                final String str = actionLabel;
                                ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, buttonColorsM1537textButtonColorsro_MJ88, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(521110564, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer4, Integer num) {
                                        invoke(rowScope, composer4, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(RowScope rowScope, Composer composer4, int i110) {
                                        ComposerKt.sourceInformation(composer4, "C219@10313L17:Snackbar.kt#uh7d8r");
                                        if ((i110 & 17) == 16 && composer4.getSkipping()) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(521110564, i110, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:219)");
                                        }
                                        TextKt.m2411Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer4, 0, 0, 131070);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer3, 54), composer3, BaseAdapter.HEADER, 494);
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
                composerStartRestartGroup.startReplaceGroup(1561358724);
                ComposerKt.sourceInformation(composerStartRestartGroup, "227@10548L362");
                if (snackbarData.getVisuals().getWithDismissAction()) {
                }
                composerStartRestartGroup.endReplaceGroup();
                int i19 = i6 << 3;
                composer2 = composerStartRestartGroup;
                m2246SnackbareQBnUkQ(PaddingKt.m720padding3ABfNKs(companion, Dp.m6890constructorimpl(12.0f)), composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, z4, shape4, j11, j8, j10, dismissActionContentColor, ComposableLambdaKt.rememberComposableLambda(-1266389126, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$3
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i110) {
                        ComposerKt.sourceInformation(composer3, "C251@11373L34:Snackbar.kt#uh7d8r");
                        if ((i110 & 3) == 2 && composer3.getSkipping()) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1266389126, i110, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:251)");
                        }
                        TextKt.m2411Text4IGK_g(snackbarData.getVisuals().getMessage(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composer2, (i19 & 3670016) | (i19 & 7168) | BaseAdapter.HEADER | (57344 & i19) | (458752 & i19) | (29360128 & i6) | (234881024 & i6), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j12 = j9;
                z5 = z4;
                shape5 = shape4;
                j13 = j11;
                j14 = j8;
                j15 = j10;
                j16 = dismissActionContentColor;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 8) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i6 &= -7169;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i6 &= -57345;
                    } else {
                        color = j6;
                    }
                    if ((i2 & 32) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i6 &= -458753;
                    } else {
                        contentColor = j7;
                    }
                    if ((i2 & 64) != 0) {
                        actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                        i6 &= -3670017;
                    } else {
                        actionColor = j3;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i6 = (-29360129) & i6;
                    } else {
                        actionContentColor = j4;
                    }
                    if ((i2 & 256) != 0) {
                        i6 &= -234881025;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                        j8 = contentColor;
                    } else {
                        dismissActionContentColor = j5;
                        j8 = contentColor;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                    }
                } else {
                    if (i7 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i4 != 0) {
                        z3 = false;
                    } else {
                        z3 = z2;
                    }
                    if ((i2 & 8) != 0) {
                        shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i6 &= -7169;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                        i6 &= -57345;
                    } else {
                        color = j6;
                    }
                    if ((i2 & 32) != 0) {
                        contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i6 &= -458753;
                    } else {
                        contentColor = j7;
                    }
                    if ((i2 & 64) != 0) {
                        actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                        i6 &= -3670017;
                    } else {
                        actionColor = j3;
                    }
                    if ((i2 & 128) != 0) {
                        actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                        i6 = (-29360129) & i6;
                    } else {
                        actionContentColor = j4;
                    }
                    if ((i2 & 256) != 0) {
                        i6 &= -234881025;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                        j8 = contentColor;
                    } else {
                        dismissActionContentColor = j5;
                        j8 = contentColor;
                        j9 = actionColor;
                        j10 = actionContentColor;
                        z4 = z3;
                        shape4 = shape3;
                        j11 = color;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(274621471, i6, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:211)");
                }
                actionLabel = snackbarData.getVisuals().getActionLabel();
                composerStartRestartGroup.startReplaceGroup(1561344786);
                ComposerKt.sourceInformation(composerStartRestartGroup, "215@10097L267");
                if (actionLabel != null) {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1378313599, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1
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
                            ComposerKt.sourceInformation(composer3, "C217@10171L44,218@10247L32,219@10311L21,216@10115L235:Snackbar.kt#uh7d8r");
                            if ((i110 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1378313599, i110, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:216)");
                                }
                                ButtonColors buttonColorsM1537textButtonColorsro_MJ88 = ButtonDefaults.INSTANCE.m1537textButtonColorsro_MJ88(0L, j9, 0L, 0L, composer3, 24576, 13);
                                ComposerKt.sourceInformationMarkerStart(composer3, 642119911, "CC(remember):Snackbar.kt#9igjgp");
                                boolean zChanged = composer3.changed(snackbarData);
                                final SnackbarData snackbarData2 = snackbarData;
                                Object objRememberedValue = composer3.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1$1$1
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
                                            snackbarData2.performAction();
                                        }
                                    };
                                    composer3.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                final String str = actionLabel;
                                ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, buttonColorsM1537textButtonColorsro_MJ88, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(521110564, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer4, Integer num) {
                                        invoke(rowScope, composer4, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(RowScope rowScope, Composer composer4, int i111) {
                                        ComposerKt.sourceInformation(composer4, "C219@10313L17:Snackbar.kt#uh7d8r");
                                        if ((i111 & 17) == 16 && composer4.getSkipping()) {
                                            composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(521110564, i111, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:219)");
                                        }
                                        TextKt.m2411Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer4, 0, 0, 131070);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, composer3, 54), composer3, BaseAdapter.HEADER, 494);
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
                composerStartRestartGroup.startReplaceGroup(1561358724);
                ComposerKt.sourceInformation(composerStartRestartGroup, "227@10548L362");
                if (snackbarData.getVisuals().getWithDismissAction()) {
                }
                composerStartRestartGroup.endReplaceGroup();
                int i110 = i6 << 3;
                composer2 = composerStartRestartGroup;
                m2246SnackbareQBnUkQ(PaddingKt.m720padding3ABfNKs(companion, Dp.m6890constructorimpl(12.0f)), composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, z4, shape4, j11, j8, j10, dismissActionContentColor, ComposableLambdaKt.rememberComposableLambda(-1266389126, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$3
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i111) {
                        ComposerKt.sourceInformation(composer3, "C251@11373L34:Snackbar.kt#uh7d8r");
                        if ((i111 & 3) == 2 && composer3.getSkipping()) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1266389126, i111, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:251)");
                        }
                        TextKt.m2411Text4IGK_g(snackbarData.getVisuals().getMessage(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composer2, (i110 & 3670016) | (i110 & 7168) | BaseAdapter.HEADER | (57344 & i110) | (458752 & i110) | (29360128 & i6) | (234881024 & i6), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j12 = j9;
                z5 = z4;
                shape5 = shape4;
                j13 = j11;
                j14 = j8;
                j15 = j10;
                j16 = dismissActionContentColor;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$4
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
                        SnackbarKt.m2247SnackbarsDKtq54(snackbarData, companion, z5, shape5, j13, j14, j12, j15, j16, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        z2 = z;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                shape2 = shape;
                if (composerStartRestartGroup.changed(shape2)) {
                }
                i3 |= i12;
            } else {
                shape2 = shape;
            }
            i3 |= i12;
        } else {
            shape2 = shape;
        }
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                j6 = j;
                if (composerStartRestartGroup.changed(j6)) {
                }
                i3 |= i13;
            } else {
                j6 = j;
            }
            i3 |= i13;
        } else {
            j6 = j;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                j7 = j2;
                if (composerStartRestartGroup.changed(j7)) {
                }
                i3 |= i14;
            } else {
                j7 = j2;
            }
            i3 |= i14;
        } else {
            j7 = j2;
        }
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                i10 = i3;
                i7 = i11;
                if (composerStartRestartGroup.changed(j3)) {
                }
                i6 = i10 | i15;
            } else {
                i10 = i3;
                i7 = i11;
            }
            i6 = i10 | i15;
        } else {
            i6 = i3;
            i7 = i11;
        }
        if ((i & 12582912) != 0) {
            if ((i2 & 128) == 0) {
                i9 = 4194304;
            } else {
                i9 = 4194304;
            }
            i6 |= i9;
        }
        if ((100663296 & i) != 0) {
            if ((i2 & 256) == 0) {
                i8 = 33554432;
            } else {
                i8 = 33554432;
            }
            i6 |= i8;
        }
        if ((38347923 & i6) == 38347922) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i7 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    z3 = false;
                } else {
                    z3 = z2;
                }
                if ((i2 & 8) != 0) {
                    shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i6 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 16) != 0) {
                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    i6 &= -57345;
                } else {
                    color = j6;
                }
                if ((i2 & 32) != 0) {
                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i6 &= -458753;
                } else {
                    contentColor = j7;
                }
                if ((i2 & 64) != 0) {
                    actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                    i6 &= -3670017;
                } else {
                    actionColor = j3;
                }
                if ((i2 & 128) != 0) {
                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                    i6 = (-29360129) & i6;
                } else {
                    actionContentColor = j4;
                }
                if ((i2 & 256) != 0) {
                    i6 &= -234881025;
                    j9 = actionColor;
                    j10 = actionContentColor;
                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    z4 = z3;
                    shape4 = shape3;
                    j11 = color;
                    j8 = contentColor;
                } else {
                    dismissActionContentColor = j5;
                    j8 = contentColor;
                    j9 = actionColor;
                    j10 = actionContentColor;
                    z4 = z3;
                    shape4 = shape3;
                    j11 = color;
                }
            } else {
                if (i7 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    z3 = false;
                } else {
                    z3 = z2;
                }
                if ((i2 & 8) != 0) {
                    shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i6 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 16) != 0) {
                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    i6 &= -57345;
                } else {
                    color = j6;
                }
                if ((i2 & 32) != 0) {
                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i6 &= -458753;
                } else {
                    contentColor = j7;
                }
                if ((i2 & 64) != 0) {
                    actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                    i6 &= -3670017;
                } else {
                    actionColor = j3;
                }
                if ((i2 & 128) != 0) {
                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                    i6 = (-29360129) & i6;
                } else {
                    actionContentColor = j4;
                }
                if ((i2 & 256) != 0) {
                    i6 &= -234881025;
                    j9 = actionColor;
                    j10 = actionContentColor;
                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    z4 = z3;
                    shape4 = shape3;
                    j11 = color;
                    j8 = contentColor;
                } else {
                    dismissActionContentColor = j5;
                    j8 = contentColor;
                    j9 = actionColor;
                    j10 = actionContentColor;
                    z4 = z3;
                    shape4 = shape3;
                    j11 = color;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(274621471, i6, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:211)");
            }
            actionLabel = snackbarData.getVisuals().getActionLabel();
            composerStartRestartGroup.startReplaceGroup(1561344786);
            ComposerKt.sourceInformation(composerStartRestartGroup, "215@10097L267");
            if (actionLabel != null) {
                composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1378313599, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1
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
                        ComposerKt.sourceInformation(composer3, "C217@10171L44,218@10247L32,219@10311L21,216@10115L235:Snackbar.kt#uh7d8r");
                        if ((i111 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1378313599, i111, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:216)");
                            }
                            ButtonColors buttonColorsM1537textButtonColorsro_MJ88 = ButtonDefaults.INSTANCE.m1537textButtonColorsro_MJ88(0L, j9, 0L, 0L, composer3, 24576, 13);
                            ComposerKt.sourceInformationMarkerStart(composer3, 642119911, "CC(remember):Snackbar.kt#9igjgp");
                            boolean zChanged = composer3.changed(snackbarData);
                            final SnackbarData snackbarData2 = snackbarData;
                            Object objRememberedValue = composer3.rememberedValue();
                            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1$1$1
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
                                        snackbarData2.performAction();
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            final String str = actionLabel;
                            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, buttonColorsM1537textButtonColorsro_MJ88, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(521110564, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer4, Integer num) {
                                    invoke(rowScope, composer4, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(RowScope rowScope, Composer composer4, int i112) {
                                    ComposerKt.sourceInformation(composer4, "C219@10313L17:Snackbar.kt#uh7d8r");
                                    if ((i112 & 17) == 16 && composer4.getSkipping()) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(521110564, i112, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:219)");
                                    }
                                    TextKt.m2411Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer4, 0, 0, 131070);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer3, 54), composer3, BaseAdapter.HEADER, 494);
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
            composerStartRestartGroup.startReplaceGroup(1561358724);
            ComposerKt.sourceInformation(composerStartRestartGroup, "227@10548L362");
            if (snackbarData.getVisuals().getWithDismissAction()) {
            }
            composerStartRestartGroup.endReplaceGroup();
            int i111 = i6 << 3;
            composer2 = composerStartRestartGroup;
            m2246SnackbareQBnUkQ(PaddingKt.m720padding3ABfNKs(companion, Dp.m6890constructorimpl(12.0f)), composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, z4, shape4, j11, j8, j10, dismissActionContentColor, ComposableLambdaKt.rememberComposableLambda(-1266389126, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$3
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i112) {
                    ComposerKt.sourceInformation(composer3, "C251@11373L34:Snackbar.kt#uh7d8r");
                    if ((i112 & 3) == 2 && composer3.getSkipping()) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1266389126, i112, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:251)");
                    }
                    TextKt.m2411Text4IGK_g(snackbarData.getVisuals().getMessage(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 0, 0, 131070);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composer2, (i111 & 3670016) | (i111 & 7168) | BaseAdapter.HEADER | (57344 & i111) | (458752 & i111) | (29360128 & i6) | (234881024 & i6), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j12 = j9;
            z5 = z4;
            shape5 = shape4;
            j13 = j11;
            j14 = j8;
            j15 = j10;
            j16 = dismissActionContentColor;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i7 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    z3 = false;
                } else {
                    z3 = z2;
                }
                if ((i2 & 8) != 0) {
                    shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i6 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 16) != 0) {
                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    i6 &= -57345;
                } else {
                    color = j6;
                }
                if ((i2 & 32) != 0) {
                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i6 &= -458753;
                } else {
                    contentColor = j7;
                }
                if ((i2 & 64) != 0) {
                    actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                    i6 &= -3670017;
                } else {
                    actionColor = j3;
                }
                if ((i2 & 128) != 0) {
                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                    i6 = (-29360129) & i6;
                } else {
                    actionContentColor = j4;
                }
                if ((i2 & 256) != 0) {
                    i6 &= -234881025;
                    j9 = actionColor;
                    j10 = actionContentColor;
                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    z4 = z3;
                    shape4 = shape3;
                    j11 = color;
                    j8 = contentColor;
                } else {
                    dismissActionContentColor = j5;
                    j8 = contentColor;
                    j9 = actionColor;
                    j10 = actionContentColor;
                    z4 = z3;
                    shape4 = shape3;
                    j11 = color;
                }
            } else {
                if (i7 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i4 != 0) {
                    z3 = false;
                } else {
                    z3 = z2;
                }
                if ((i2 & 8) != 0) {
                    shape3 = SnackbarDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i6 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 16) != 0) {
                    color = SnackbarDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    i6 &= -57345;
                } else {
                    color = j6;
                }
                if ((i2 & 32) != 0) {
                    contentColor = SnackbarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i6 &= -458753;
                } else {
                    contentColor = j7;
                }
                if ((i2 & 64) != 0) {
                    actionColor = SnackbarDefaults.INSTANCE.getActionColor(composerStartRestartGroup, 6);
                    i6 &= -3670017;
                } else {
                    actionColor = j3;
                }
                if ((i2 & 128) != 0) {
                    actionContentColor = SnackbarDefaults.INSTANCE.getActionContentColor(composerStartRestartGroup, 6);
                    i6 = (-29360129) & i6;
                } else {
                    actionContentColor = j4;
                }
                if ((i2 & 256) != 0) {
                    i6 &= -234881025;
                    j9 = actionColor;
                    j10 = actionContentColor;
                    dismissActionContentColor = SnackbarDefaults.INSTANCE.getDismissActionContentColor(composerStartRestartGroup, 6);
                    z4 = z3;
                    shape4 = shape3;
                    j11 = color;
                    j8 = contentColor;
                } else {
                    dismissActionContentColor = j5;
                    j8 = contentColor;
                    j9 = actionColor;
                    j10 = actionContentColor;
                    z4 = z3;
                    shape4 = shape3;
                    j11 = color;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(274621471, i6, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:211)");
            }
            actionLabel = snackbarData.getVisuals().getActionLabel();
            composerStartRestartGroup.startReplaceGroup(1561344786);
            ComposerKt.sourceInformation(composerStartRestartGroup, "215@10097L267");
            if (actionLabel != null) {
                composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1378313599, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1
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
                        ComposerKt.sourceInformation(composer3, "C217@10171L44,218@10247L32,219@10311L21,216@10115L235:Snackbar.kt#uh7d8r");
                        if ((i112 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1378313599, i112, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:216)");
                            }
                            ButtonColors buttonColorsM1537textButtonColorsro_MJ88 = ButtonDefaults.INSTANCE.m1537textButtonColorsro_MJ88(0L, j9, 0L, 0L, composer3, 24576, 13);
                            ComposerKt.sourceInformationMarkerStart(composer3, 642119911, "CC(remember):Snackbar.kt#9igjgp");
                            boolean zChanged = composer3.changed(snackbarData);
                            final SnackbarData snackbarData2 = snackbarData;
                            Object objRememberedValue = composer3.rememberedValue();
                            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1$1$1
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
                                        snackbarData2.performAction();
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            final String str = actionLabel;
                            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, buttonColorsM1537textButtonColorsro_MJ88, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(521110564, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer4, Integer num) {
                                    invoke(rowScope, composer4, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(RowScope rowScope, Composer composer4, int i113) {
                                    ComposerKt.sourceInformation(composer4, "C219@10313L17:Snackbar.kt#uh7d8r");
                                    if ((i113 & 17) == 16 && composer4.getSkipping()) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(521110564, i113, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:219)");
                                    }
                                    TextKt.m2411Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer4, 0, 0, 131070);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer3, 54), composer3, BaseAdapter.HEADER, 494);
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
            composerStartRestartGroup.startReplaceGroup(1561358724);
            ComposerKt.sourceInformation(composerStartRestartGroup, "227@10548L362");
            if (snackbarData.getVisuals().getWithDismissAction()) {
            }
            composerStartRestartGroup.endReplaceGroup();
            int i112 = i6 << 3;
            composer2 = composerStartRestartGroup;
            m2246SnackbareQBnUkQ(PaddingKt.m720padding3ABfNKs(companion, Dp.m6890constructorimpl(12.0f)), composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, z4, shape4, j11, j8, j10, dismissActionContentColor, ComposableLambdaKt.rememberComposableLambda(-1266389126, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$3
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i113) {
                    ComposerKt.sourceInformation(composer3, "C251@11373L34:Snackbar.kt#uh7d8r");
                    if ((i113 & 3) == 2 && composer3.getSkipping()) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1266389126, i113, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:251)");
                    }
                    TextKt.m2411Text4IGK_g(snackbarData.getVisuals().getMessage(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 0, 0, 131070);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composer2, (i112 & 3670016) | (i112 & 7168) | BaseAdapter.HEADER | (57344 & i112) | (458752 & i112) | (29360128 & i6) | (234881024 & i6), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j12 = j9;
            z5 = z4;
            shape5 = shape4;
            j13 = j11;
            j14 = j8;
            j15 = j10;
            j16 = dismissActionContentColor;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$4
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
                    SnackbarKt.m2247SnackbarsDKtq54(snackbarData, companion, z5, shape5, j13, j14, j12, j15, j16, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: NewLineButtonSnackbar-kKq0p4A, reason: not valid java name */
    public static final void m2244NewLineButtonSnackbarkKq0p4A(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final TextStyle textStyle, final long j, final long j2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1332496681);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(NewLineButtonSnackbar)P(5!1,3,2,1:c#ui.graphics.Color,4:c#ui.graphics.Color)264@11690L1175:Snackbar.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function4) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(textStyle) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(j2) ? 131072 : 65536;
        }
        if ((74899 & i2) != 74898 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1332496681, i2, -1, "androidx.compose.material3.NewLineButtonSnackbar (Snackbar.kt:263)");
            }
            Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(SizeKt.m772widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, ContainerMaxWidth, 1, null), 0.0f, 1, null), HorizontalSpacing, 0.0f, 0.0f, SeparateButtonExtraY, 6, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM724paddingqDBjuR0$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i3 = i2;
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -376152340, "C272@11979L191,279@12180L679:Snackbar.kt#uh7d8r");
            Modifier modifierM570paddingFromBaselineVpY3zN4 = AlignmentLineKt.m570paddingFromBaselineVpY3zN4(Modifier.INSTANCE, HeightToFirstLine, LongButtonVerticalOffset);
            float fM6890constructorimpl = HorizontalSpacingButtonSide;
            Modifier modifierM724paddingqDBjuR0$default2 = PaddingKt.m724paddingqDBjuR0$default(modifierM570paddingFromBaselineVpY3zN4, 0.0f, 0.0f, fM6890constructorimpl, 0.0f, 11, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM724paddingqDBjuR0$default2);
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
            Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1482962025, "C276@12154L6:Snackbar.kt#uh7d8r");
            function2.invoke(composerStartRestartGroup, Integer.valueOf(i3 & 14));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierAlign = columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getEnd());
            if (function4 != null) {
                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
            }
            Modifier modifierM724paddingqDBjuR0$default3 = PaddingKt.m724paddingqDBjuR0$default(modifierAlign, 0.0f, 0.0f, fM6890constructorimpl, 0.0f, 11, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM724paddingqDBjuR0$default3);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM3454constructorimpl3 = Updater.m3454constructorimpl(composerStartRestartGroup);
            Updater.m3461setimpl(composerM3454constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3454constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM3454constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM3454constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m3461setimpl(composerM3454constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1482754232, "C283@12348L501:Snackbar.kt#uh7d8r");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor4);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM3454constructorimpl4 = Updater.m3454constructorimpl(composerStartRestartGroup);
            Updater.m3461setimpl(composerM3454constructorimpl4, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3454constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM3454constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM3454constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m3461setimpl(composerM3454constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1996615437, "C284@12370L208:Snackbar.kt#uh7d8r");
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m4142boximpl(j)), TextKt.getLocalTextStyle().provides(textStyle)}, function3, composerStartRestartGroup, ProvidedValue.$stable | (i3 & 112));
            composerStartRestartGroup.startReplaceGroup(618603253);
            ComposerKt.sourceInformation(composerStartRestartGroup, "290@12644L173");
            if (function4 != null) {
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4142boximpl(j2)), function4, composerStartRestartGroup, ProvidedValue.$stable | ((i3 >> 3) & 112));
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$NewLineButtonSnackbar$2
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
                    SnackbarKt.m2244NewLineButtonSnackbarkKq0p4A(function2, function3, function4, textStyle, j, j2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: OneRowSnackbar-kKq0p4A, reason: not valid java name */
    public static final void m2245OneRowSnackbarkKq0p4A(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final TextStyle textStyle, final long j, final long j2, Composer composer, final int i) {
        int i2;
        float fM6890constructorimpl;
        Composer composerStartRestartGroup = composer.startRestartGroup(-903235475);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(OneRowSnackbar)P(5!1,3,2,1:c#ui.graphics.Color,4:c#ui.graphics.Color)338@14229L3580,312@13223L4586:Snackbar.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function4) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(textStyle) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(j2) ? 131072 : 65536;
        }
        if ((74899 & i2) != 74898 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-903235475, i2, -1, "androidx.compose.material3.OneRowSnackbar (Snackbar.kt:308)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            float f = HorizontalSpacing;
            if (function4 == null) {
                fM6890constructorimpl = HorizontalSpacingButtonSide;
            } else {
                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
            }
            Modifier modifierM724paddingqDBjuR0$default = PaddingKt.m724paddingqDBjuR0$default(companion, f, 0.0f, fM6890constructorimpl, 0.0f, 10, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1386942712, "CC(remember):Snackbar.kt#9igjgp");
            MeasurePolicy measurePolicyRememberedValue = composerStartRestartGroup.rememberedValue();
            final String str = "text";
            final String str2 = "action";
            int i3 = i2;
            final String str3 = "dismissAction";
            if (measurePolicyRememberedValue == Composer.INSTANCE.getEmpty()) {
                measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.SnackbarKt$OneRowSnackbar$2$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i4) {
                        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i4);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i4) {
                        return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i4);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i4) {
                        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i4);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i4) {
                        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i4);
                    }

                    /* JADX WARN: Code duplicated, block: B:63:0x0152 A[PHI: r3 r5
                      0x0152: PHI (r3v9 int) = (r3v8 int), (r3v17 int), (r3v17 int) binds: [B:61:0x0147, B:56:0x0119, B:58:0x0125] A[DONT_GENERATE, DONT_INLINE]
                      0x0152: PHI (r5v10 int) = (r5v9 int), (r5v16 int), (r5v16 int) binds: [B:61:0x0147, B:56:0x0119, B:58:0x0125] A[DONT_GENERATE, DONT_INLINE]] */
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo72measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j3) {
                        Measurable measurable;
                        Measurable measurable2;
                        int height;
                        int iMax;
                        int height2;
                        int i4;
                        MeasureScope measureScope2 = measureScope;
                        int iMin = Math.min(Constraints.m6833getMaxWidthimpl(j3), measureScope2.mo400roundToPx0680j_4(SnackbarKt.ContainerMaxWidth));
                        String str4 = str2;
                        int size = list.size();
                        int i5 = 0;
                        while (true) {
                            if (i5 >= size) {
                                measurable = null;
                                break;
                            }
                            measurable = list.get(i5);
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), str4)) {
                                break;
                            }
                            i5++;
                        }
                        Measurable measurable3 = measurable;
                        Placeable placeableMo5581measureBRTryo0 = measurable3 != null ? measurable3.mo5581measureBRTryo0(j3) : null;
                        String str5 = str3;
                        int size2 = list.size();
                        int i6 = 0;
                        while (true) {
                            if (i6 >= size2) {
                                measurable2 = null;
                                break;
                            }
                            measurable2 = list.get(i6);
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), str5)) {
                                break;
                            }
                            i6++;
                        }
                        Measurable measurable4 = measurable2;
                        final Placeable placeableMo5581measureBRTryo1 = measurable4 != null ? measurable4.mo5581measureBRTryo0(j3) : null;
                        int width = placeableMo5581measureBRTryo0 != null ? placeableMo5581measureBRTryo0.getWidth() : 0;
                        int height3 = placeableMo5581measureBRTryo0 != null ? placeableMo5581measureBRTryo0.getHeight() : 0;
                        int width2 = placeableMo5581measureBRTryo1 != null ? placeableMo5581measureBRTryo1.getWidth() : 0;
                        int height4 = placeableMo5581measureBRTryo1 != null ? placeableMo5581measureBRTryo1.getHeight() : 0;
                        int iCoerceAtLeast = RangesKt.coerceAtLeast(((iMin - width) - width2) - (width2 == 0 ? measureScope2.mo400roundToPx0680j_4(SnackbarKt.TextEndExtraSpacing) : 0), Constraints.m6835getMinWidthimpl(j3));
                        String str6 = str;
                        int size3 = list.size();
                        int i7 = 0;
                        while (i7 < size3) {
                            Measurable measurable5 = list.get(i7);
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable5), str6)) {
                                int i8 = height4;
                                final Placeable placeableMo5581measureBRTryo2 = measurable5.mo5581measureBRTryo0(Constraints.m6823copyZbe2FdA$default(j3, 0, iCoerceAtLeast, 0, 0, 9, null));
                                int i9 = placeableMo5581measureBRTryo2.get(androidx.compose.ui.layout.AlignmentLineKt.getFirstBaseline());
                                int i10 = placeableMo5581measureBRTryo2.get(androidx.compose.ui.layout.AlignmentLineKt.getLastBaseline());
                                boolean z = true;
                                boolean z2 = (i9 == Integer.MIN_VALUE || i10 == Integer.MIN_VALUE) ? false : true;
                                if (i9 != i10 && z2) {
                                    z = false;
                                }
                                final int i11 = iMin - width2;
                                final int i12 = i11 - width;
                                if (!z) {
                                    height = measureScope2.mo400roundToPx0680j_4(SnackbarKt.HeightToFirstLine) - i9;
                                    iMax = Math.max(measureScope2.mo400roundToPx0680j_4(SnackbarTokens.INSTANCE.m3253getTwoLinesContainerHeightD9Ej5fM()), placeableMo5581measureBRTryo2.getHeight() + height);
                                    if (placeableMo5581measureBRTryo0 != null) {
                                        height2 = (iMax - placeableMo5581measureBRTryo0.getHeight()) / 2;
                                    } else {
                                        height2 = 0;
                                    }
                                } else {
                                    iMax = Math.max(measureScope2.mo400roundToPx0680j_4(SnackbarTokens.INSTANCE.m3252getSingleLineContainerHeightD9Ej5fM()), Math.max(height3, i8));
                                    height = (iMax - placeableMo5581measureBRTryo2.getHeight()) / 2;
                                    if (placeableMo5581measureBRTryo0 == null || (i4 = placeableMo5581measureBRTryo0.get(androidx.compose.ui.layout.AlignmentLineKt.getFirstBaseline())) == Integer.MIN_VALUE) {
                                        height2 = 0;
                                    } else {
                                        height2 = (i9 + height) - i4;
                                    }
                                }
                                final int i13 = height2;
                                int i14 = iMax;
                                final int i15 = height;
                                final int height5 = placeableMo5581measureBRTryo1 != null ? (i14 - placeableMo5581measureBRTryo1.getHeight()) / 2 : 0;
                                final Placeable placeable = placeableMo5581measureBRTryo0;
                                return MeasureScope.CC.layout$default(measureScope2, iMin, i14, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.SnackbarKt$OneRowSnackbar$2$1.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                        invoke2(placementScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Placeable.PlacementScope placementScope) {
                                        Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo5581measureBRTryo2, 0, i15, 0.0f, 4, null);
                                        Placeable placeable2 = placeableMo5581measureBRTryo1;
                                        if (placeable2 != null) {
                                            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i11, height5, 0.0f, 4, null);
                                        }
                                        Placeable placeable3 = placeable;
                                        if (placeable3 != null) {
                                            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, i12, i13, 0.0f, 4, null);
                                        }
                                    }
                                }, 4, null);
                            }
                            i7++;
                            height4 = height4;
                            measureScope2 = measureScope;
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                composerStartRestartGroup.updateRememberedValue(measurePolicyRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) measurePolicyRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM724paddingqDBjuR0$default);
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
            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2016566027, "C314@13253L86:Snackbar.kt#uh7d8r");
            Modifier modifierM722paddingVpY3zN4$default = PaddingKt.m722paddingVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "text"), 0.0f, SnackbarVerticalPadding, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM722paddingVpY3zN4$default);
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
            Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3454constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1308937155, "C314@13331L6:Snackbar.kt#uh7d8r");
            function2.invoke(composerStartRestartGroup, Integer.valueOf(i3 & 14));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.startReplaceGroup(-904778058);
            ComposerKt.sourceInformation(composerStartRestartGroup, "316@13390L295");
            if (function3 != null) {
                Modifier modifierLayoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "action");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierLayoutId);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM3454constructorimpl3 = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM3454constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    composerM3454constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3454constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m3461setimpl(composerM3454constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1309057900, "C317@13446L221:Snackbar.kt#uh7d8r");
                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m4142boximpl(j)), TextKt.getLocalTextStyle().provides(textStyle)}, function3, composerStartRestartGroup, ProvidedValue.$stable | (i3 & 112));
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            }
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(-904766579);
            ComposerKt.sourceInformation(composerStartRestartGroup, "325@13757L247");
            if (function4 != null) {
                Modifier modifierLayoutId2 = LayoutIdKt.layoutId(Modifier.INSTANCE, "dismissAction");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierLayoutId2);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor4);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM3454constructorimpl4 = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM3454constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    composerM3454constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    composerM3454constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                }
                Updater.m3461setimpl(composerM3454constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1309427203, "C326@13820L166:Snackbar.kt#uh7d8r");
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4142boximpl(j2)), function4, composerStartRestartGroup, ProvidedValue.$stable | ((i3 >> 3) & 112));
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            }
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$OneRowSnackbar$3
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
                    SnackbarKt.m2245OneRowSnackbarkKq0p4A(function2, function3, function4, textStyle, j, j2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }
}
