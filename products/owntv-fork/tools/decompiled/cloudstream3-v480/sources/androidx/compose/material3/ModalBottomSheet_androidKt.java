package androidx.compose.material3;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.SecureFlagPolicy;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ModalBottomSheet.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a¹\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\r2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u001c\u0010\u0017\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001aJ\u0010\u001d\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f2\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0012H\u0001¢\u0006\u0002\u0010\"\u001a\f\u0010#\u001a\u00020$*\u00020%H\u0000\u001a\u0014\u0010&\u001a\u00020$*\u00020'2\u0006\u0010(\u001a\u00020$H\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006)²\u0006\u0015\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0012X\u008a\u0084\u0002"}, d2 = {"ModalBottomSheet", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "sheetState", "Landroidx/compose/material3/SheetState;", "sheetMaxWidth", "Landroidx/compose/ui/unit/Dp;", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "scrimColor", "dragHandle", "Landroidx/compose/runtime/Composable;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "properties", "Landroidx/compose/material3/ModalBottomSheetProperties;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "ModalBottomSheet-dYc4hso", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;FLandroidx/compose/ui/graphics/Shape;JJFJLkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/ModalBottomSheetProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "ModalBottomSheetDialog", "predictiveBackProgress", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/ModalBottomSheetProperties;Landroidx/compose/animation/core/Animatable;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "isFlagSecureEnabled", "", "Landroid/view/View;", "shouldApplySecureFlag", "Landroidx/compose/ui/window/SecureFlagPolicy;", "isSecureFlagSetOnParent", "material3_release", "currentContent"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModalBottomSheet_androidKt {

    /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SecureFlagPolicy.values().length];
            try {
                iArr[SecureFlagPolicy.SecureOff.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SecureFlagPolicy.SecureOn.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SecureFlagPolicy.Inherit.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x011d  */
    /* JADX WARN: Code duplicated, block: B:102:0x0121  */
    /* JADX WARN: Code duplicated, block: B:104:0x012b  */
    /* JADX WARN: Code duplicated, block: B:105:0x012e  */
    /* JADX WARN: Code duplicated, block: B:110:0x013b  */
    /* JADX WARN: Code duplicated, block: B:112:0x013f  */
    /* JADX WARN: Code duplicated, block: B:115:0x014a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:118:0x0151  */
    /* JADX WARN: Code duplicated, block: B:121:0x0159  */
    /* JADX WARN: Code duplicated, block: B:122:0x015e  */
    /* JADX WARN: Code duplicated, block: B:124:0x0162  */
    /* JADX WARN: Code duplicated, block: B:126:0x016c  */
    /* JADX WARN: Code duplicated, block: B:127:0x016f  */
    /* JADX WARN: Code duplicated, block: B:129:0x0174  */
    /* JADX WARN: Code duplicated, block: B:132:0x0180  */
    /* JADX WARN: Code duplicated, block: B:133:0x0183  */
    /* JADX WARN: Code duplicated, block: B:135:0x0187  */
    /* JADX WARN: Code duplicated, block: B:137:0x018f  */
    /* JADX WARN: Code duplicated, block: B:138:0x0192  */
    /* JADX WARN: Code duplicated, block: B:143:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:149:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:151:0x01da  */
    /* JADX WARN: Code duplicated, block: B:173:0x0223 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:174:0x0225  */
    /* JADX WARN: Code duplicated, block: B:177:0x022e  */
    /* JADX WARN: Code duplicated, block: B:179:0x023a  */
    /* JADX WARN: Code duplicated, block: B:180:0x0241  */
    /* JADX WARN: Code duplicated, block: B:183:0x024a  */
    /* JADX WARN: Code duplicated, block: B:186:0x0256  */
    /* JADX WARN: Code duplicated, block: B:187:0x025f  */
    /* JADX WARN: Code duplicated, block: B:190:0x0264  */
    /* JADX WARN: Code duplicated, block: B:192:0x0270  */
    /* JADX WARN: Code duplicated, block: B:193:0x0276  */
    /* JADX WARN: Code duplicated, block: B:196:0x027c  */
    /* JADX WARN: Code duplicated, block: B:197:0x0289  */
    /* JADX WARN: Code duplicated, block: B:199:0x028d  */
    /* JADX WARN: Code duplicated, block: B:200:0x0294  */
    /* JADX WARN: Code duplicated, block: B:203:0x029a  */
    /* JADX WARN: Code duplicated, block: B:204:0x02a4  */
    /* JADX WARN: Code duplicated, block: B:206:0x02a8  */
    /* JADX WARN: Code duplicated, block: B:207:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:211:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:212:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:215:0x02fa  */
    /* JADX WARN: Code duplicated, block: B:219:0x0310  */
    /* JADX WARN: Code duplicated, block: B:221:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0052  */
    /* JADX WARN: Code duplicated, block: B:28:0x0056  */
    /* JADX WARN: Code duplicated, block: B:30:0x005e  */
    /* JADX WARN: Code duplicated, block: B:31:0x0061  */
    /* JADX WARN: Code duplicated, block: B:34:0x0068  */
    /* JADX WARN: Code duplicated, block: B:37:0x006e  */
    /* JADX WARN: Code duplicated, block: B:38:0x0071  */
    /* JADX WARN: Code duplicated, block: B:40:0x0075  */
    /* JADX WARN: Code duplicated, block: B:42:0x007d  */
    /* JADX WARN: Code duplicated, block: B:43:0x0080  */
    /* JADX WARN: Code duplicated, block: B:48:0x008b  */
    /* JADX WARN: Code duplicated, block: B:50:0x008f  */
    /* JADX WARN: Code duplicated, block: B:52:0x0097  */
    /* JADX WARN: Code duplicated, block: B:53:0x009a  */
    /* JADX WARN: Code duplicated, block: B:56:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:61:0x00af  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:66:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:71:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:79:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:82:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:89:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:91:0x0105  */
    /* JADX WARN: Code duplicated, block: B:94:0x010e  */
    /* JADX WARN: Code duplicated, block: B:96:0x0112  */
    /* JADX WARN: Code duplicated, block: B:99:0x011a  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use constructor with contentWindowInsets parameter.", replaceWith = @ReplaceWith(expression = "ModalBottomSheet(onDismissRequest,modifier,sheetState,sheetMaxWidth,shape,containerColor,contentColor,tonalElevation,scrimColor,dragHandle,{ windowInsets },properties,content,)", imports = {}))
    /* JADX INFO: renamed from: ModalBottomSheet-dYc4hso, reason: not valid java name */
    public static final /* synthetic */ void m1966ModalBottomSheetdYc4hso(final Function0 function0, Modifier modifier, SheetState sheetState, float f, Shape shape, long j, long j2, float f2, long j3, Function2 function2, WindowInsets windowInsets, ModalBottomSheetProperties modalBottomSheetProperties, final Function3 function3, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        SheetState sheetStateRememberModalBottomSheetState;
        int i5;
        int i6;
        Shape expandedShape;
        long j4;
        long jM1647contentColorForek8zF_U;
        int i7;
        int i8;
        long j5;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        float fM1510getSheetMaxWidthD9Ej5fM;
        long containerColor;
        float fM6890constructorimpl;
        long scrimColor;
        Function2 function2M1689getLambda1$material3_release;
        WindowInsets windowInsets2;
        ModalBottomSheetProperties properties;
        SheetState sheetState2;
        float f3;
        int i16;
        final WindowInsets windowInsets3;
        Modifier modifier3;
        long j6;
        long j7;
        Function2 function4;
        int i17;
        Shape shape2;
        float f4;
        final Modifier modifier4;
        final SheetState sheetState3;
        final float f5;
        final Shape shape3;
        final long j8;
        final long j9;
        final float f6;
        final long j10;
        final Function2 function5;
        final ModalBottomSheetProperties modalBottomSheetProperties2;
        final WindowInsets windowInsets4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i18;
        int i19;
        int i20;
        Composer composerStartRestartGroup = composer.startRestartGroup(944867294);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ModalBottomSheet)P(5,4,10,9:c#ui.unit.Dp,8,0:c#ui.graphics.Color,2:c#ui.graphics.Color,11:c#ui.unit.Dp,7:c#ui.graphics.Color,3,12,6)235@10240L31,237@10371L13,238@10434L14,239@10476L31,241@10584L10,243@10731L12,247@10884L485:ModalBottomSheet.android.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i | 6;
        } else if ((i & 6) == 0) {
            i4 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        int i21 = i3 & 2;
        if (i21 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if ((i3 & 4) == 0) {
                    sheetStateRememberModalBottomSheetState = sheetState;
                    int i22 = composerStartRestartGroup.changed(sheetStateRememberModalBottomSheetState) ? 256 : 128;
                    i4 |= i22;
                } else {
                    sheetStateRememberModalBottomSheetState = sheetState;
                }
                i4 |= i22;
            } else {
                sheetStateRememberModalBottomSheetState = sheetState;
            }
            i5 = i3 & 8;
            if (i5 != 0) {
                if ((i & 3072) == 0) {
                    if (composerStartRestartGroup.changed(f)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i4 |= i6;
                }
                if ((i & 24576) == 0) {
                    if ((i3 & 16) == 0) {
                        expandedShape = shape;
                        int i23 = composerStartRestartGroup.changed(expandedShape) ? 16384 : 8192;
                        i4 |= i23;
                    } else {
                        expandedShape = shape;
                    }
                    i4 |= i23;
                } else {
                    expandedShape = shape;
                }
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    j4 = j;
                    if ((i3 & 32) == 0 || !composerStartRestartGroup.changed(j4)) {
                        i20 = 65536;
                    } else {
                        i20 = 131072;
                    }
                    i4 |= i20;
                } else {
                    j4 = j;
                }
                if ((i & 1572864) == 0) {
                    jM1647contentColorForek8zF_U = j2;
                    if ((i3 & 64) == 0 || !composerStartRestartGroup.changed(jM1647contentColorForek8zF_U)) {
                        i19 = 524288;
                    } else {
                        i19 = 1048576;
                    }
                    i4 |= i19;
                } else {
                    jM1647contentColorForek8zF_U = j2;
                }
                i7 = i3 & 128;
                if (i7 != 0) {
                    i4 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i4 |= i8;
                }
                if ((i & 100663296) == 0) {
                    j5 = j3;
                    if ((i3 & 256) == 0 || !composerStartRestartGroup.changed(j5)) {
                        i18 = 33554432;
                    } else {
                        i18 = 67108864;
                    }
                    i4 |= i18;
                } else {
                    j5 = j3;
                }
                i9 = i3 & 512;
                if (i9 != 0) {
                    if ((i & BaseAdapter.HEADER) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i10 = 536870912;
                        } else {
                            i10 = 268435456;
                        }
                        i4 |= i10;
                    }
                    if ((i2 & 6) == 0) {
                        i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
                    } else {
                        i11 = i2;
                    }
                    i12 = i3 & 2048;
                    if (i12 != 0) {
                        i11 |= 48;
                    } else if ((i2 & 48) != 0) {
                        if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                            i13 = 32;
                        } else {
                            i13 = 16;
                        }
                        i11 |= i13;
                    }
                    i14 = i11;
                    if ((i3 & 4096) != 0) {
                        if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i15 = 256;
                            } else {
                                i15 = 128;
                            }
                            i14 |= i15;
                        }
                        if ((i4 & 306783379) == 306783378 || (i14 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i21 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i3 & 4) != 0) {
                                    i4 &= -897;
                                    sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                                }
                                if (i5 != 0) {
                                    fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                                } else {
                                    fM1510getSheetMaxWidthD9Ej5fM = f;
                                }
                                if ((i3 & 16) != 0) {
                                    expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                    i4 &= -57345;
                                }
                                if ((i3 & 32) != 0) {
                                    containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -458753;
                                } else {
                                    containerColor = j4;
                                }
                                if ((i3 & 64) != 0) {
                                    jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                    i4 &= -3670017;
                                }
                                if (i7 != 0) {
                                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                                } else {
                                    fM6890constructorimpl = f2;
                                }
                                if ((i3 & 256) != 0) {
                                    scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                    i4 = (-234881025) & i4;
                                } else {
                                    scrimColor = j5;
                                }
                                if (i9 != 0) {
                                    function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                                } else {
                                    function2M1689getLambda1$material3_release = function2;
                                }
                                if ((i3 & 1024) != 0) {
                                    windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                    i14 &= -15;
                                } else {
                                    windowInsets2 = windowInsets;
                                }
                                if (i12 != 0) {
                                    properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                                } else {
                                    properties = modalBottomSheetProperties;
                                }
                                sheetState2 = sheetStateRememberModalBottomSheetState;
                                f3 = fM6890constructorimpl;
                                float f7 = fM1510getSheetMaxWidthD9Ej5fM;
                                i16 = i14;
                                windowInsets3 = windowInsets2;
                                modifier3 = modifier2;
                                j6 = jM1647contentColorForek8zF_U;
                                j7 = scrimColor;
                                function4 = function2M1689getLambda1$material3_release;
                                i17 = i4;
                                shape2 = expandedShape;
                                f4 = f7;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i3 & 4) != 0) {
                                    i4 &= -897;
                                }
                                if ((i3 & 16) != 0) {
                                    i4 &= -57345;
                                }
                                if ((i3 & 32) != 0) {
                                    i4 &= -458753;
                                }
                                if ((i3 & 64) != 0) {
                                    i4 &= -3670017;
                                }
                                if ((i3 & 256) != 0) {
                                    i4 &= -234881025;
                                }
                                if ((i3 & 1024) != 0) {
                                    i14 &= -15;
                                }
                                function4 = function2;
                                properties = modalBottomSheetProperties;
                                i16 = i14;
                                i17 = i4;
                                containerColor = j4;
                                windowInsets3 = windowInsets;
                                shape2 = expandedShape;
                                f4 = f;
                                SheetState sheetState4 = sheetStateRememberModalBottomSheetState;
                                f3 = f2;
                                long j11 = j5;
                                modifier3 = modifier2;
                                sheetState2 = sheetState4;
                                j6 = jM1647contentColorForek8zF_U;
                                j7 = j11;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                            }
                            ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                                    return invoke(composer2, num.intValue());
                                }

                                public final WindowInsets invoke(Composer composer2, int i24) {
                                    composer2.startReplaceGroup(-2061903609);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                                    }
                                    WindowInsets windowInsets5 = windowInsets3;
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    composer2.endReplaceGroup();
                                    return windowInsets5;
                                }
                            }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier4 = modifier3;
                            sheetState3 = sheetState2;
                            f5 = f4;
                            shape3 = shape2;
                            j8 = containerColor;
                            j9 = j6;
                            f6 = f3;
                            j10 = j7;
                            function5 = function4;
                            modalBottomSheetProperties2 = properties;
                            windowInsets4 = windowInsets3;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            composerStartRestartGroup = composerStartRestartGroup;
                            modifier4 = modifier2;
                            sheetState3 = sheetStateRememberModalBottomSheetState;
                            j9 = jM1647contentColorForek8zF_U;
                            f5 = f;
                            function5 = function2;
                            windowInsets4 = windowInsets;
                            modalBottomSheetProperties2 = modalBottomSheetProperties;
                            long j12 = j4;
                            f6 = f2;
                            j10 = j5;
                            shape3 = expandedShape;
                            j8 = j12;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                                public final void invoke(Composer composer2, int i24) {
                                    ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                                }
                            });
                        }
                    }
                    i14 |= RendererCapabilities.DECODER_SUPPORT_MASK;
                    if ((i4 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f8 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f8;
                        } else {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f9 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f9;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                        }
                        ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                                return invoke(composer2, num.intValue());
                            }

                            public final WindowInsets invoke(Composer composer2, int i24) {
                                composer2.startReplaceGroup(-2061903609);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                                }
                                WindowInsets windowInsets5 = windowInsets3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer2.endReplaceGroup();
                                return windowInsets5;
                            }
                        }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier3;
                        sheetState3 = sheetState2;
                        f5 = f4;
                        shape3 = shape2;
                        j8 = containerColor;
                        j9 = j6;
                        f6 = f3;
                        j10 = j7;
                        function5 = function4;
                        modalBottomSheetProperties2 = properties;
                        windowInsets4 = windowInsets3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f10 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f10;
                        } else {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f11 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f11;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                        }
                        ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                                return invoke(composer2, num.intValue());
                            }

                            public final WindowInsets invoke(Composer composer2, int i24) {
                                composer2.startReplaceGroup(-2061903609);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                                }
                                WindowInsets windowInsets5 = windowInsets3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer2.endReplaceGroup();
                                return windowInsets5;
                            }
                        }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier3;
                        sheetState3 = sheetState2;
                        f5 = f4;
                        shape3 = shape2;
                        j8 = containerColor;
                        j9 = j6;
                        f6 = f3;
                        j10 = j7;
                        function5 = function4;
                        modalBottomSheetProperties2 = properties;
                        windowInsets4 = windowInsets3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                            public final void invoke(Composer composer2, int i24) {
                                ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i4 |= BaseAdapter.HEADER;
                if ((i2 & 6) == 0) {
                    i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
                } else {
                    i11 = i2;
                }
                i12 = i3 & 2048;
                if (i12 != 0) {
                    i11 |= 48;
                } else if ((i2 & 48) != 0) {
                    if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                        i13 = 32;
                    } else {
                        i13 = 16;
                    }
                    i11 |= i13;
                }
                i14 = i11;
                if ((i3 & 4096) != 0) {
                    if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i15 = 256;
                        } else {
                            i15 = 128;
                        }
                        i14 |= i15;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f12 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f12;
                        } else {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f13 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f13;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                        }
                        ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                                return invoke(composer2, num.intValue());
                            }

                            public final WindowInsets invoke(Composer composer2, int i24) {
                                composer2.startReplaceGroup(-2061903609);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                                }
                                WindowInsets windowInsets5 = windowInsets3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer2.endReplaceGroup();
                                return windowInsets5;
                            }
                        }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier3;
                        sheetState3 = sheetState2;
                        f5 = f4;
                        shape3 = shape2;
                        j8 = containerColor;
                        j9 = j6;
                        f6 = f3;
                        j10 = j7;
                        function5 = function4;
                        modalBottomSheetProperties2 = properties;
                        windowInsets4 = windowInsets3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f14 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f14;
                        } else {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f15 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f15;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                        }
                        ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                                return invoke(composer2, num.intValue());
                            }

                            public final WindowInsets invoke(Composer composer2, int i24) {
                                composer2.startReplaceGroup(-2061903609);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                                }
                                WindowInsets windowInsets5 = windowInsets3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer2.endReplaceGroup();
                                return windowInsets5;
                            }
                        }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier3;
                        sheetState3 = sheetState2;
                        f5 = f4;
                        shape3 = shape2;
                        j8 = containerColor;
                        j9 = j6;
                        f6 = f3;
                        j10 = j7;
                        function5 = function4;
                        modalBottomSheetProperties2 = properties;
                        windowInsets4 = windowInsets3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                            public final void invoke(Composer composer2, int i24) {
                                ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i14 |= RendererCapabilities.DECODER_SUPPORT_MASK;
                if ((i4 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f16 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f16;
                    } else {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f17 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f17;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                    }
                    ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                            return invoke(composer2, num.intValue());
                        }

                        public final WindowInsets invoke(Composer composer2, int i24) {
                            composer2.startReplaceGroup(-2061903609);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                            }
                            WindowInsets windowInsets5 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2.endReplaceGroup();
                            return windowInsets5;
                        }
                    }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    sheetState3 = sheetState2;
                    f5 = f4;
                    shape3 = shape2;
                    j8 = containerColor;
                    j9 = j6;
                    f6 = f3;
                    j10 = j7;
                    function5 = function4;
                    modalBottomSheetProperties2 = properties;
                    windowInsets4 = windowInsets3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f18 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f18;
                    } else {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f19 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f19;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                    }
                    ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                            return invoke(composer2, num.intValue());
                        }

                        public final WindowInsets invoke(Composer composer2, int i24) {
                            composer2.startReplaceGroup(-2061903609);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                            }
                            WindowInsets windowInsets5 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2.endReplaceGroup();
                            return windowInsets5;
                        }
                    }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    sheetState3 = sheetState2;
                    f5 = f4;
                    shape3 = shape2;
                    j8 = containerColor;
                    j9 = j6;
                    f6 = f3;
                    j10 = j7;
                    function5 = function4;
                    modalBottomSheetProperties2 = properties;
                    windowInsets4 = windowInsets3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                        public final void invoke(Composer composer2, int i24) {
                            ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= 3072;
            if ((i & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    expandedShape = shape;
                    if (composerStartRestartGroup.changed(expandedShape)) {
                    }
                    i4 |= i23;
                } else {
                    expandedShape = shape;
                }
                i4 |= i23;
            } else {
                expandedShape = shape;
            }
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                j4 = j;
                if ((i3 & 32) == 0) {
                    i20 = 65536;
                } else {
                    i20 = 65536;
                }
                i4 |= i20;
            } else {
                j4 = j;
            }
            if ((i & 1572864) == 0) {
                jM1647contentColorForek8zF_U = j2;
                if ((i3 & 64) == 0) {
                    i19 = 524288;
                } else {
                    i19 = 524288;
                }
                i4 |= i19;
            } else {
                jM1647contentColorForek8zF_U = j2;
            }
            i7 = i3 & 128;
            if (i7 != 0) {
                i4 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i8 = 8388608;
                } else {
                    i8 = 4194304;
                }
                i4 |= i8;
            }
            if ((i & 100663296) == 0) {
                j5 = j3;
                if ((i3 & 256) == 0) {
                    i18 = 33554432;
                } else {
                    i18 = 33554432;
                }
                i4 |= i18;
            } else {
                j5 = j3;
            }
            i9 = i3 & 512;
            if (i9 != 0) {
                if ((i & BaseAdapter.HEADER) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i10 = 536870912;
                    } else {
                        i10 = 268435456;
                    }
                    i4 |= i10;
                }
                if ((i2 & 6) == 0) {
                    i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
                } else {
                    i11 = i2;
                }
                i12 = i3 & 2048;
                if (i12 != 0) {
                    i11 |= 48;
                } else if ((i2 & 48) != 0) {
                    if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                        i13 = 32;
                    } else {
                        i13 = 16;
                    }
                    i11 |= i13;
                }
                i14 = i11;
                if ((i3 & 4096) != 0) {
                    if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i15 = 256;
                        } else {
                            i15 = 128;
                        }
                        i14 |= i15;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f110 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f110;
                        } else {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f111 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f111;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                        }
                        ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                                return invoke(composer2, num.intValue());
                            }

                            public final WindowInsets invoke(Composer composer2, int i24) {
                                composer2.startReplaceGroup(-2061903609);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                                }
                                WindowInsets windowInsets5 = windowInsets3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer2.endReplaceGroup();
                                return windowInsets5;
                            }
                        }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier3;
                        sheetState3 = sheetState2;
                        f5 = f4;
                        shape3 = shape2;
                        j8 = containerColor;
                        j9 = j6;
                        f6 = f3;
                        j10 = j7;
                        function5 = function4;
                        modalBottomSheetProperties2 = properties;
                        windowInsets4 = windowInsets3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f112 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f112;
                        } else {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f113 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f113;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                        }
                        ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                                return invoke(composer2, num.intValue());
                            }

                            public final WindowInsets invoke(Composer composer2, int i24) {
                                composer2.startReplaceGroup(-2061903609);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                                }
                                WindowInsets windowInsets5 = windowInsets3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer2.endReplaceGroup();
                                return windowInsets5;
                            }
                        }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier3;
                        sheetState3 = sheetState2;
                        f5 = f4;
                        shape3 = shape2;
                        j8 = containerColor;
                        j9 = j6;
                        f6 = f3;
                        j10 = j7;
                        function5 = function4;
                        modalBottomSheetProperties2 = properties;
                        windowInsets4 = windowInsets3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                            public final void invoke(Composer composer2, int i24) {
                                ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i14 |= RendererCapabilities.DECODER_SUPPORT_MASK;
                if ((i4 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f114 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f114;
                    } else {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f115 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f115;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                    }
                    ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                            return invoke(composer2, num.intValue());
                        }

                        public final WindowInsets invoke(Composer composer2, int i24) {
                            composer2.startReplaceGroup(-2061903609);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                            }
                            WindowInsets windowInsets5 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2.endReplaceGroup();
                            return windowInsets5;
                        }
                    }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    sheetState3 = sheetState2;
                    f5 = f4;
                    shape3 = shape2;
                    j8 = containerColor;
                    j9 = j6;
                    f6 = f3;
                    j10 = j7;
                    function5 = function4;
                    modalBottomSheetProperties2 = properties;
                    windowInsets4 = windowInsets3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f116 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f116;
                    } else {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f117 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f117;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                    }
                    ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                            return invoke(composer2, num.intValue());
                        }

                        public final WindowInsets invoke(Composer composer2, int i24) {
                            composer2.startReplaceGroup(-2061903609);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                            }
                            WindowInsets windowInsets5 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2.endReplaceGroup();
                            return windowInsets5;
                        }
                    }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    sheetState3 = sheetState2;
                    f5 = f4;
                    shape3 = shape2;
                    j8 = containerColor;
                    j9 = j6;
                    f6 = f3;
                    j10 = j7;
                    function5 = function4;
                    modalBottomSheetProperties2 = properties;
                    windowInsets4 = windowInsets3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                        public final void invoke(Composer composer2, int i24) {
                            ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= BaseAdapter.HEADER;
            if ((i2 & 6) == 0) {
                i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
            } else {
                i11 = i2;
            }
            i12 = i3 & 2048;
            if (i12 != 0) {
                i11 |= 48;
            } else if ((i2 & 48) != 0) {
                if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                    i13 = 32;
                } else {
                    i13 = 16;
                }
                i11 |= i13;
            }
            i14 = i11;
            if ((i3 & 4096) != 0) {
                if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i15 = 256;
                    } else {
                        i15 = 128;
                    }
                    i14 |= i15;
                }
                if ((i4 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f118 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f118;
                    } else {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f119 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f119;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                    }
                    ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                            return invoke(composer2, num.intValue());
                        }

                        public final WindowInsets invoke(Composer composer2, int i24) {
                            composer2.startReplaceGroup(-2061903609);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                            }
                            WindowInsets windowInsets5 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2.endReplaceGroup();
                            return windowInsets5;
                        }
                    }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    sheetState3 = sheetState2;
                    f5 = f4;
                    shape3 = shape2;
                    j8 = containerColor;
                    j9 = j6;
                    f6 = f3;
                    j10 = j7;
                    function5 = function4;
                    modalBottomSheetProperties2 = properties;
                    windowInsets4 = windowInsets3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f1110 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f1110;
                    } else {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f1111 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f1111;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                    }
                    ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                            return invoke(composer2, num.intValue());
                        }

                        public final WindowInsets invoke(Composer composer2, int i24) {
                            composer2.startReplaceGroup(-2061903609);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                            }
                            WindowInsets windowInsets5 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2.endReplaceGroup();
                            return windowInsets5;
                        }
                    }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    sheetState3 = sheetState2;
                    f5 = f4;
                    shape3 = shape2;
                    j8 = containerColor;
                    j9 = j6;
                    f6 = f3;
                    j10 = j7;
                    function5 = function4;
                    modalBottomSheetProperties2 = properties;
                    windowInsets4 = windowInsets3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                        public final void invoke(Composer composer2, int i24) {
                            ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i14 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            if ((i4 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f1112 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f1112;
                } else {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f1113 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f1113;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                }
                ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                        return invoke(composer2, num.intValue());
                    }

                    public final WindowInsets invoke(Composer composer2, int i24) {
                        composer2.startReplaceGroup(-2061903609);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                        }
                        WindowInsets windowInsets5 = windowInsets3;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer2.endReplaceGroup();
                        return windowInsets5;
                    }
                }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                sheetState3 = sheetState2;
                f5 = f4;
                shape3 = shape2;
                j8 = containerColor;
                j9 = j6;
                f6 = f3;
                j10 = j7;
                function5 = function4;
                modalBottomSheetProperties2 = properties;
                windowInsets4 = windowInsets3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f1114 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f1114;
                } else {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f1115 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f1115;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                }
                ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                        return invoke(composer2, num.intValue());
                    }

                    public final WindowInsets invoke(Composer composer2, int i24) {
                        composer2.startReplaceGroup(-2061903609);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                        }
                        WindowInsets windowInsets5 = windowInsets3;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer2.endReplaceGroup();
                        return windowInsets5;
                    }
                }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                sheetState3 = sheetState2;
                f5 = f4;
                shape3 = shape2;
                j8 = containerColor;
                j9 = j6;
                f6 = f3;
                j10 = j7;
                function5 = function4;
                modalBottomSheetProperties2 = properties;
                windowInsets4 = windowInsets3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                    public final void invoke(Composer composer2, int i24) {
                        ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i3 & 4) == 0) {
                sheetStateRememberModalBottomSheetState = sheetState;
                if (composerStartRestartGroup.changed(sheetStateRememberModalBottomSheetState)) {
                }
                i4 |= i22;
            } else {
                sheetStateRememberModalBottomSheetState = sheetState;
            }
            i4 |= i22;
        } else {
            sheetStateRememberModalBottomSheetState = sheetState;
        }
        i5 = i3 & 8;
        if (i5 != 0) {
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changed(f)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i4 |= i6;
            }
            if ((i & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    expandedShape = shape;
                    if (composerStartRestartGroup.changed(expandedShape)) {
                    }
                    i4 |= i23;
                } else {
                    expandedShape = shape;
                }
                i4 |= i23;
            } else {
                expandedShape = shape;
            }
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                j4 = j;
                if ((i3 & 32) == 0) {
                    i20 = 65536;
                } else {
                    i20 = 65536;
                }
                i4 |= i20;
            } else {
                j4 = j;
            }
            if ((i & 1572864) == 0) {
                jM1647contentColorForek8zF_U = j2;
                if ((i3 & 64) == 0) {
                    i19 = 524288;
                } else {
                    i19 = 524288;
                }
                i4 |= i19;
            } else {
                jM1647contentColorForek8zF_U = j2;
            }
            i7 = i3 & 128;
            if (i7 != 0) {
                i4 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i8 = 8388608;
                } else {
                    i8 = 4194304;
                }
                i4 |= i8;
            }
            if ((i & 100663296) == 0) {
                j5 = j3;
                if ((i3 & 256) == 0) {
                    i18 = 33554432;
                } else {
                    i18 = 33554432;
                }
                i4 |= i18;
            } else {
                j5 = j3;
            }
            i9 = i3 & 512;
            if (i9 != 0) {
                if ((i & BaseAdapter.HEADER) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i10 = 536870912;
                    } else {
                        i10 = 268435456;
                    }
                    i4 |= i10;
                }
                if ((i2 & 6) == 0) {
                    i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
                } else {
                    i11 = i2;
                }
                i12 = i3 & 2048;
                if (i12 != 0) {
                    i11 |= 48;
                } else if ((i2 & 48) != 0) {
                    if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                        i13 = 32;
                    } else {
                        i13 = 16;
                    }
                    i11 |= i13;
                }
                i14 = i11;
                if ((i3 & 4096) != 0) {
                    if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i15 = 256;
                        } else {
                            i15 = 128;
                        }
                        i14 |= i15;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f1116 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f1116;
                        } else {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f1117 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f1117;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                        }
                        ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                                return invoke(composer2, num.intValue());
                            }

                            public final WindowInsets invoke(Composer composer2, int i24) {
                                composer2.startReplaceGroup(-2061903609);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                                }
                                WindowInsets windowInsets5 = windowInsets3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer2.endReplaceGroup();
                                return windowInsets5;
                            }
                        }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier3;
                        sheetState3 = sheetState2;
                        f5 = f4;
                        shape3 = shape2;
                        j8 = containerColor;
                        j9 = j6;
                        f6 = f3;
                        j10 = j7;
                        function5 = function4;
                        modalBottomSheetProperties2 = properties;
                        windowInsets4 = windowInsets3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f1118 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f1118;
                        } else {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM1510getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            } else {
                                containerColor = j4;
                            }
                            if ((i3 & 64) != 0) {
                                jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                            } else {
                                fM6890constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 = (-234881025) & i4;
                            } else {
                                scrimColor = j5;
                            }
                            if (i9 != 0) {
                                function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                            } else {
                                function2M1689getLambda1$material3_release = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets2 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            f3 = fM6890constructorimpl;
                            float f1119 = fM1510getSheetMaxWidthD9Ej5fM;
                            i16 = i14;
                            windowInsets3 = windowInsets2;
                            modifier3 = modifier2;
                            j6 = jM1647contentColorForek8zF_U;
                            j7 = scrimColor;
                            function4 = function2M1689getLambda1$material3_release;
                            i17 = i4;
                            shape2 = expandedShape;
                            f4 = f1119;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                        }
                        ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                                return invoke(composer2, num.intValue());
                            }

                            public final WindowInsets invoke(Composer composer2, int i24) {
                                composer2.startReplaceGroup(-2061903609);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                                }
                                WindowInsets windowInsets5 = windowInsets3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer2.endReplaceGroup();
                                return windowInsets5;
                            }
                        }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier3;
                        sheetState3 = sheetState2;
                        f5 = f4;
                        shape3 = shape2;
                        j8 = containerColor;
                        j9 = j6;
                        f6 = f3;
                        j10 = j7;
                        function5 = function4;
                        modalBottomSheetProperties2 = properties;
                        windowInsets4 = windowInsets3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                            public final void invoke(Composer composer2, int i24) {
                                ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                    }
                }
                i14 |= RendererCapabilities.DECODER_SUPPORT_MASK;
                if ((i4 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f11110 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f11110;
                    } else {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f11111 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f11111;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                    }
                    ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                            return invoke(composer2, num.intValue());
                        }

                        public final WindowInsets invoke(Composer composer2, int i24) {
                            composer2.startReplaceGroup(-2061903609);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                            }
                            WindowInsets windowInsets5 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2.endReplaceGroup();
                            return windowInsets5;
                        }
                    }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    sheetState3 = sheetState2;
                    f5 = f4;
                    shape3 = shape2;
                    j8 = containerColor;
                    j9 = j6;
                    f6 = f3;
                    j10 = j7;
                    function5 = function4;
                    modalBottomSheetProperties2 = properties;
                    windowInsets4 = windowInsets3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f11112 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f11112;
                    } else {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f11113 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f11113;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                    }
                    ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                            return invoke(composer2, num.intValue());
                        }

                        public final WindowInsets invoke(Composer composer2, int i24) {
                            composer2.startReplaceGroup(-2061903609);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                            }
                            WindowInsets windowInsets5 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2.endReplaceGroup();
                            return windowInsets5;
                        }
                    }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    sheetState3 = sheetState2;
                    f5 = f4;
                    shape3 = shape2;
                    j8 = containerColor;
                    j9 = j6;
                    f6 = f3;
                    j10 = j7;
                    function5 = function4;
                    modalBottomSheetProperties2 = properties;
                    windowInsets4 = windowInsets3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                        public final void invoke(Composer composer2, int i24) {
                            ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i4 |= BaseAdapter.HEADER;
            if ((i2 & 6) == 0) {
                i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
            } else {
                i11 = i2;
            }
            i12 = i3 & 2048;
            if (i12 != 0) {
                i11 |= 48;
            } else if ((i2 & 48) != 0) {
                if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                    i13 = 32;
                } else {
                    i13 = 16;
                }
                i11 |= i13;
            }
            i14 = i11;
            if ((i3 & 4096) != 0) {
                if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i15 = 256;
                    } else {
                        i15 = 128;
                    }
                    i14 |= i15;
                }
                if ((i4 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f11114 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f11114;
                    } else {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f11115 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f11115;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                    }
                    ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                            return invoke(composer2, num.intValue());
                        }

                        public final WindowInsets invoke(Composer composer2, int i24) {
                            composer2.startReplaceGroup(-2061903609);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                            }
                            WindowInsets windowInsets5 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2.endReplaceGroup();
                            return windowInsets5;
                        }
                    }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    sheetState3 = sheetState2;
                    f5 = f4;
                    shape3 = shape2;
                    j8 = containerColor;
                    j9 = j6;
                    f6 = f3;
                    j10 = j7;
                    function5 = function4;
                    modalBottomSheetProperties2 = properties;
                    windowInsets4 = windowInsets3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f11116 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f11116;
                    } else {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f11117 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f11117;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                    }
                    ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                            return invoke(composer2, num.intValue());
                        }

                        public final WindowInsets invoke(Composer composer2, int i24) {
                            composer2.startReplaceGroup(-2061903609);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                            }
                            WindowInsets windowInsets5 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2.endReplaceGroup();
                            return windowInsets5;
                        }
                    }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    sheetState3 = sheetState2;
                    f5 = f4;
                    shape3 = shape2;
                    j8 = containerColor;
                    j9 = j6;
                    f6 = f3;
                    j10 = j7;
                    function5 = function4;
                    modalBottomSheetProperties2 = properties;
                    windowInsets4 = windowInsets3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                        public final void invoke(Composer composer2, int i24) {
                            ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i14 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            if ((i4 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f11118 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f11118;
                } else {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f11119 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f11119;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                }
                ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                        return invoke(composer2, num.intValue());
                    }

                    public final WindowInsets invoke(Composer composer2, int i24) {
                        composer2.startReplaceGroup(-2061903609);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                        }
                        WindowInsets windowInsets5 = windowInsets3;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer2.endReplaceGroup();
                        return windowInsets5;
                    }
                }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                sheetState3 = sheetState2;
                f5 = f4;
                shape3 = shape2;
                j8 = containerColor;
                j9 = j6;
                f6 = f3;
                j10 = j7;
                function5 = function4;
                modalBottomSheetProperties2 = properties;
                windowInsets4 = windowInsets3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f111110 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f111110;
                } else {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f111111 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f111111;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                }
                ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                        return invoke(composer2, num.intValue());
                    }

                    public final WindowInsets invoke(Composer composer2, int i24) {
                        composer2.startReplaceGroup(-2061903609);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                        }
                        WindowInsets windowInsets5 = windowInsets3;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer2.endReplaceGroup();
                        return windowInsets5;
                    }
                }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                sheetState3 = sheetState2;
                f5 = f4;
                shape3 = shape2;
                j8 = containerColor;
                j9 = j6;
                f6 = f3;
                j10 = j7;
                function5 = function4;
                modalBottomSheetProperties2 = properties;
                windowInsets4 = windowInsets3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                    public final void invoke(Composer composer2, int i24) {
                        ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                    }
                });
            }
        }
        i4 |= 3072;
        if ((i & 24576) == 0) {
            if ((i3 & 16) == 0) {
                expandedShape = shape;
                if (composerStartRestartGroup.changed(expandedShape)) {
                }
                i4 |= i23;
            } else {
                expandedShape = shape;
            }
            i4 |= i23;
        } else {
            expandedShape = shape;
        }
        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            j4 = j;
            if ((i3 & 32) == 0) {
                i20 = 65536;
            } else {
                i20 = 65536;
            }
            i4 |= i20;
        } else {
            j4 = j;
        }
        if ((i & 1572864) == 0) {
            jM1647contentColorForek8zF_U = j2;
            if ((i3 & 64) == 0) {
                i19 = 524288;
            } else {
                i19 = 524288;
            }
            i4 |= i19;
        } else {
            jM1647contentColorForek8zF_U = j2;
        }
        i7 = i3 & 128;
        if (i7 != 0) {
            i4 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changed(f2)) {
                i8 = 8388608;
            } else {
                i8 = 4194304;
            }
            i4 |= i8;
        }
        if ((i & 100663296) == 0) {
            j5 = j3;
            if ((i3 & 256) == 0) {
                i18 = 33554432;
            } else {
                i18 = 33554432;
            }
            i4 |= i18;
        } else {
            j5 = j3;
        }
        i9 = i3 & 512;
        if (i9 != 0) {
            if ((i & BaseAdapter.HEADER) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i10 = 536870912;
                } else {
                    i10 = 268435456;
                }
                i4 |= i10;
            }
            if ((i2 & 6) == 0) {
                i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
            } else {
                i11 = i2;
            }
            i12 = i3 & 2048;
            if (i12 != 0) {
                i11 |= 48;
            } else if ((i2 & 48) != 0) {
                if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                    i13 = 32;
                } else {
                    i13 = 16;
                }
                i11 |= i13;
            }
            i14 = i11;
            if ((i3 & 4096) != 0) {
                if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i15 = 256;
                    } else {
                        i15 = 128;
                    }
                    i14 |= i15;
                }
                if ((i4 & 306783379) == 306783378) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f111112 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f111112;
                    } else {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f111113 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f111113;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                    }
                    ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                            return invoke(composer2, num.intValue());
                        }

                        public final WindowInsets invoke(Composer composer2, int i24) {
                            composer2.startReplaceGroup(-2061903609);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                            }
                            WindowInsets windowInsets5 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2.endReplaceGroup();
                            return windowInsets5;
                        }
                    }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    sheetState3 = sheetState2;
                    f5 = f4;
                    shape3 = shape2;
                    j8 = containerColor;
                    j9 = j6;
                    f6 = f3;
                    j10 = j7;
                    function5 = function4;
                    modalBottomSheetProperties2 = properties;
                    windowInsets4 = windowInsets3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f111114 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f111114;
                    } else {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM1510getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        } else {
                            containerColor = j4;
                        }
                        if ((i3 & 64) != 0) {
                            jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                        } else {
                            fM6890constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 = (-234881025) & i4;
                        } else {
                            scrimColor = j5;
                        }
                        if (i9 != 0) {
                            function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                        } else {
                            function2M1689getLambda1$material3_release = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets2 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        f3 = fM6890constructorimpl;
                        float f111115 = fM1510getSheetMaxWidthD9Ej5fM;
                        i16 = i14;
                        windowInsets3 = windowInsets2;
                        modifier3 = modifier2;
                        j6 = jM1647contentColorForek8zF_U;
                        j7 = scrimColor;
                        function4 = function2M1689getLambda1$material3_release;
                        i17 = i4;
                        shape2 = expandedShape;
                        f4 = f111115;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                    }
                    ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                            return invoke(composer2, num.intValue());
                        }

                        public final WindowInsets invoke(Composer composer2, int i24) {
                            composer2.startReplaceGroup(-2061903609);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                            }
                            WindowInsets windowInsets5 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2.endReplaceGroup();
                            return windowInsets5;
                        }
                    }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    sheetState3 = sheetState2;
                    f5 = f4;
                    shape3 = shape2;
                    j8 = containerColor;
                    j9 = j6;
                    f6 = f3;
                    j10 = j7;
                    function5 = function4;
                    modalBottomSheetProperties2 = properties;
                    windowInsets4 = windowInsets3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                        public final void invoke(Composer composer2, int i24) {
                            ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                        }
                    });
                }
            }
            i14 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            if ((i4 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f111116 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f111116;
                } else {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f111117 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f111117;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                }
                ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                        return invoke(composer2, num.intValue());
                    }

                    public final WindowInsets invoke(Composer composer2, int i24) {
                        composer2.startReplaceGroup(-2061903609);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                        }
                        WindowInsets windowInsets5 = windowInsets3;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer2.endReplaceGroup();
                        return windowInsets5;
                    }
                }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                sheetState3 = sheetState2;
                f5 = f4;
                shape3 = shape2;
                j8 = containerColor;
                j9 = j6;
                f6 = f3;
                j10 = j7;
                function5 = function4;
                modalBottomSheetProperties2 = properties;
                windowInsets4 = windowInsets3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f111118 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f111118;
                } else {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f111119 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f111119;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                }
                ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                        return invoke(composer2, num.intValue());
                    }

                    public final WindowInsets invoke(Composer composer2, int i24) {
                        composer2.startReplaceGroup(-2061903609);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                        }
                        WindowInsets windowInsets5 = windowInsets3;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer2.endReplaceGroup();
                        return windowInsets5;
                    }
                }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                sheetState3 = sheetState2;
                f5 = f4;
                shape3 = shape2;
                j8 = containerColor;
                j9 = j6;
                f6 = f3;
                j10 = j7;
                function5 = function4;
                modalBottomSheetProperties2 = properties;
                windowInsets4 = windowInsets3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                    public final void invoke(Composer composer2, int i24) {
                        ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                    }
                });
            }
        }
        i4 |= BaseAdapter.HEADER;
        if ((i2 & 6) == 0) {
            i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
        } else {
            i11 = i2;
        }
        i12 = i3 & 2048;
        if (i12 != 0) {
            i11 |= 48;
        } else if ((i2 & 48) != 0) {
            if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                i13 = 32;
            } else {
                i13 = 16;
            }
            i11 |= i13;
        }
        i14 = i11;
        if ((i3 & 4096) != 0) {
            if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i15 = 256;
                } else {
                    i15 = 128;
                }
                i14 |= i15;
            }
            if ((i4 & 306783379) == 306783378) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f1111110 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f1111110;
                } else {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f1111111 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f1111111;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                }
                ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                        return invoke(composer2, num.intValue());
                    }

                    public final WindowInsets invoke(Composer composer2, int i24) {
                        composer2.startReplaceGroup(-2061903609);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                        }
                        WindowInsets windowInsets5 = windowInsets3;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer2.endReplaceGroup();
                        return windowInsets5;
                    }
                }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                sheetState3 = sheetState2;
                f5 = f4;
                shape3 = shape2;
                j8 = containerColor;
                j9 = j6;
                f6 = f3;
                j10 = j7;
                function5 = function4;
                modalBottomSheetProperties2 = properties;
                windowInsets4 = windowInsets3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f1111112 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f1111112;
                } else {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM1510getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        containerColor = j4;
                    }
                    if ((i3 & 64) != 0) {
                        jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                    } else {
                        fM6890constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 = (-234881025) & i4;
                    } else {
                        scrimColor = j5;
                    }
                    if (i9 != 0) {
                        function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                    } else {
                        function2M1689getLambda1$material3_release = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    f3 = fM6890constructorimpl;
                    float f1111113 = fM1510getSheetMaxWidthD9Ej5fM;
                    i16 = i14;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    j6 = jM1647contentColorForek8zF_U;
                    j7 = scrimColor;
                    function4 = function2M1689getLambda1$material3_release;
                    i17 = i4;
                    shape2 = expandedShape;
                    f4 = f1111113;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
                }
                ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                        return invoke(composer2, num.intValue());
                    }

                    public final WindowInsets invoke(Composer composer2, int i24) {
                        composer2.startReplaceGroup(-2061903609);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                        }
                        WindowInsets windowInsets5 = windowInsets3;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer2.endReplaceGroup();
                        return windowInsets5;
                    }
                }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                sheetState3 = sheetState2;
                f5 = f4;
                shape3 = shape2;
                j8 = containerColor;
                j9 = j6;
                f6 = f3;
                j10 = j7;
                function5 = function4;
                modalBottomSheetProperties2 = properties;
                windowInsets4 = windowInsets3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                    public final void invoke(Composer composer2, int i24) {
                        ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                    }
                });
            }
        }
        i14 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        if ((i4 & 306783379) == 306783378) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i21 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                }
                if (i5 != 0) {
                    fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                } else {
                    fM1510getSheetMaxWidthD9Ej5fM = f;
                }
                if ((i3 & 16) != 0) {
                    expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                    i4 &= -57345;
                }
                if ((i3 & 32) != 0) {
                    containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i4 &= -458753;
                } else {
                    containerColor = j4;
                }
                if ((i3 & 64) != 0) {
                    jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                    i4 &= -3670017;
                }
                if (i7 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                } else {
                    fM6890constructorimpl = f2;
                }
                if ((i3 & 256) != 0) {
                    scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                    i4 = (-234881025) & i4;
                } else {
                    scrimColor = j5;
                }
                if (i9 != 0) {
                    function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                } else {
                    function2M1689getLambda1$material3_release = function2;
                }
                if ((i3 & 1024) != 0) {
                    windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    i14 &= -15;
                } else {
                    windowInsets2 = windowInsets;
                }
                if (i12 != 0) {
                    properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                } else {
                    properties = modalBottomSheetProperties;
                }
                sheetState2 = sheetStateRememberModalBottomSheetState;
                f3 = fM6890constructorimpl;
                float f1111114 = fM1510getSheetMaxWidthD9Ej5fM;
                i16 = i14;
                windowInsets3 = windowInsets2;
                modifier3 = modifier2;
                j6 = jM1647contentColorForek8zF_U;
                j7 = scrimColor;
                function4 = function2M1689getLambda1$material3_release;
                i17 = i4;
                shape2 = expandedShape;
                f4 = f1111114;
            } else {
                if (i21 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                }
                if (i5 != 0) {
                    fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                } else {
                    fM1510getSheetMaxWidthD9Ej5fM = f;
                }
                if ((i3 & 16) != 0) {
                    expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                    i4 &= -57345;
                }
                if ((i3 & 32) != 0) {
                    containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i4 &= -458753;
                } else {
                    containerColor = j4;
                }
                if ((i3 & 64) != 0) {
                    jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                    i4 &= -3670017;
                }
                if (i7 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                } else {
                    fM6890constructorimpl = f2;
                }
                if ((i3 & 256) != 0) {
                    scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                    i4 = (-234881025) & i4;
                } else {
                    scrimColor = j5;
                }
                if (i9 != 0) {
                    function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                } else {
                    function2M1689getLambda1$material3_release = function2;
                }
                if ((i3 & 1024) != 0) {
                    windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    i14 &= -15;
                } else {
                    windowInsets2 = windowInsets;
                }
                if (i12 != 0) {
                    properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                } else {
                    properties = modalBottomSheetProperties;
                }
                sheetState2 = sheetStateRememberModalBottomSheetState;
                f3 = fM6890constructorimpl;
                float f1111115 = fM1510getSheetMaxWidthD9Ej5fM;
                i16 = i14;
                windowInsets3 = windowInsets2;
                modifier3 = modifier2;
                j6 = jM1647contentColorForek8zF_U;
                j7 = scrimColor;
                function4 = function2M1689getLambda1$material3_release;
                i17 = i4;
                shape2 = expandedShape;
                f4 = f1111115;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
            }
            ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                    return invoke(composer2, num.intValue());
                }

                public final WindowInsets invoke(Composer composer2, int i24) {
                    composer2.startReplaceGroup(-2061903609);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                    }
                    WindowInsets windowInsets5 = windowInsets3;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return windowInsets5;
                }
            }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            sheetState3 = sheetState2;
            f5 = f4;
            shape3 = shape2;
            j8 = containerColor;
            j9 = j6;
            f6 = f3;
            j10 = j7;
            function5 = function4;
            modalBottomSheetProperties2 = properties;
            windowInsets4 = windowInsets3;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i21 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                }
                if (i5 != 0) {
                    fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                } else {
                    fM1510getSheetMaxWidthD9Ej5fM = f;
                }
                if ((i3 & 16) != 0) {
                    expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                    i4 &= -57345;
                }
                if ((i3 & 32) != 0) {
                    containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i4 &= -458753;
                } else {
                    containerColor = j4;
                }
                if ((i3 & 64) != 0) {
                    jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                    i4 &= -3670017;
                }
                if (i7 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                } else {
                    fM6890constructorimpl = f2;
                }
                if ((i3 & 256) != 0) {
                    scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                    i4 = (-234881025) & i4;
                } else {
                    scrimColor = j5;
                }
                if (i9 != 0) {
                    function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                } else {
                    function2M1689getLambda1$material3_release = function2;
                }
                if ((i3 & 1024) != 0) {
                    windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    i14 &= -15;
                } else {
                    windowInsets2 = windowInsets;
                }
                if (i12 != 0) {
                    properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                } else {
                    properties = modalBottomSheetProperties;
                }
                sheetState2 = sheetStateRememberModalBottomSheetState;
                f3 = fM6890constructorimpl;
                float f1111116 = fM1510getSheetMaxWidthD9Ej5fM;
                i16 = i14;
                windowInsets3 = windowInsets2;
                modifier3 = modifier2;
                j6 = jM1647contentColorForek8zF_U;
                j7 = scrimColor;
                function4 = function2M1689getLambda1$material3_release;
                i17 = i4;
                shape2 = expandedShape;
                f4 = f1111116;
            } else {
                if (i21 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                }
                if (i5 != 0) {
                    fM1510getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m1510getSheetMaxWidthD9Ej5fM();
                } else {
                    fM1510getSheetMaxWidthD9Ej5fM = f;
                }
                if ((i3 & 16) != 0) {
                    expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                    i4 &= -57345;
                }
                if ((i3 & 32) != 0) {
                    containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i4 &= -458753;
                } else {
                    containerColor = j4;
                }
                if ((i3 & 64) != 0) {
                    jM1647contentColorForek8zF_U = ColorSchemeKt.m1647contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                    i4 &= -3670017;
                }
                if (i7 != 0) {
                    fM6890constructorimpl = Dp.m6890constructorimpl(0.0f);
                } else {
                    fM6890constructorimpl = f2;
                }
                if ((i3 & 256) != 0) {
                    scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                    i4 = (-234881025) & i4;
                } else {
                    scrimColor = j5;
                }
                if (i9 != 0) {
                    function2M1689getLambda1$material3_release = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1689getLambda1$material3_release();
                } else {
                    function2M1689getLambda1$material3_release = function2;
                }
                if ((i3 & 1024) != 0) {
                    windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    i14 &= -15;
                } else {
                    windowInsets2 = windowInsets;
                }
                if (i12 != 0) {
                    properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                } else {
                    properties = modalBottomSheetProperties;
                }
                sheetState2 = sheetStateRememberModalBottomSheetState;
                f3 = fM6890constructorimpl;
                float f1111117 = fM1510getSheetMaxWidthD9Ej5fM;
                i16 = i14;
                windowInsets3 = windowInsets2;
                modifier3 = modifier2;
                j6 = jM1647contentColorForek8zF_U;
                j7 = scrimColor;
                function4 = function2M1689getLambda1$material3_release;
                i17 = i4;
                shape2 = expandedShape;
                f4 = f1111117;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(944867294, i17, i16, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
            }
            ModalBottomSheetKt.m1960ModalBottomSheetdYc4hso(function0, modifier3, sheetState2, f4, shape2, containerColor, j6, f3, j7, function4, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer2, Integer num) {
                    return invoke(composer2, num.intValue());
                }

                public final WindowInsets invoke(Composer composer2, int i24) {
                    composer2.startReplaceGroup(-2061903609);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2061903609, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                    }
                    WindowInsets windowInsets5 = windowInsets3;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return windowInsets5;
                }
            }, properties, function3, composerStartRestartGroup, i17 & 2147483646, i16 & 1008, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            sheetState3 = sheetState2;
            f5 = f4;
            shape3 = shape2;
            j8 = containerColor;
            j9 = j6;
            f6 = f3;
            j10 = j7;
            function5 = function4;
            modalBottomSheetProperties2 = properties;
            windowInsets4 = windowInsets3;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                public final void invoke(Composer composer2, int i24) {
                    ModalBottomSheet_androidKt.m1966ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f5, shape3, j8, j9, f6, j10, function5, windowInsets4, modalBottomSheetProperties2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void ModalBottomSheetDialog(final Function0<Unit> function0, final ModalBottomSheetProperties modalBottomSheetProperties, final Animatable<Float, AnimationVector1D> animatable, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composer2;
        int i3;
        final LayoutDirection layoutDirection;
        Composer composerStartRestartGroup = composer.startRestartGroup(1254951810);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ModalBottomSheetDialog)P(1,3,2)273@11822L7,274@11861L7,275@11916L7,276@11946L28,277@12001L29,278@12050L38,279@12105L24,280@12157L21,282@12204L697,305@12932L129,305@12907L154,314@13078L182,314@13067L193:ModalBottomSheet.android.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(modalBottomSheetProperties) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= (i & 512) == 0 ? composerStartRestartGroup.changed(animatable) : composerStartRestartGroup.changedInstance(animatable) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if ((i2 & 1171) != 1170 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1254951810, i2, -1, "androidx.compose.material3.ModalBottomSheetDialog (ModalBottomSheet.android.kt:272)");
            }
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localView);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            View view = (View) objConsume;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density = (Density) objConsume2;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LayoutDirection layoutDirection2 = (LayoutDirection) objConsume3;
            CompositionContext compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
            final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerStartRestartGroup, (i2 >> 9) & 14);
            composer2 = composerStartRestartGroup;
            UUID uuid = (UUID) RememberSaveableKt.m3571rememberSaveable(new Object[0], (Saver) null, (String) null, (Function0) new Function0<UUID>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetDialog$dialogId$1
                @Override // kotlin.jvm.functions.Function0
                public final UUID invoke() {
                    return UUID.randomUUID();
                }
            }, composerStartRestartGroup, 3072, 6);
            ComposerKt.sourceInformationMarkerStart(composer2, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composer2, -954363344, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue = composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer2));
                composer2.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                objRememberedValue = compositionScopedCoroutineScopeCanceller;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            boolean zIsSystemInDarkTheme = DarkThemeKt.isSystemInDarkTheme(composer2, 0);
            ComposerKt.sourceInformationMarkerStart(composer2, -1981517173, "CC(remember):ModalBottomSheet.android.kt#9igjgp");
            boolean zChanged = composer2.changed(view) | composer2.changed(density);
            Object objRememberedValue2 = composer2.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                i3 = 0;
                layoutDirection = layoutDirection2;
                ModalBottomSheetDialogWrapper modalBottomSheetDialogWrapper = new ModalBottomSheetDialogWrapper(function0, modalBottomSheetProperties, view, layoutDirection, density, uuid, animatable, coroutineScope, zIsSystemInDarkTheme);
                modalBottomSheetDialogWrapper.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-1560960657, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetDialog$dialog$1$1$1
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

                    public final void invoke(Composer composer3, int i4) {
                        ComposerKt.sourceInformation(composer3, "C296@12687L164:ModalBottomSheet.android.kt#uh7d8r");
                        if ((i4 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1560960657, i4, -1, "androidx.compose.material3.ModalBottomSheetDialog.<anonymous>.<anonymous>.<anonymous> (ModalBottomSheet.android.kt:296)");
                            }
                            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetDialog$dialog$1$1$1.1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    SemanticsPropertiesKt.dialog(semanticsPropertyReceiver);
                                }
                            }, 1, null);
                            State<Function2<Composer, Integer, Unit>> state = stateRememberUpdatedState;
                            ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierSemantics$default);
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
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer3, -490070203, "C299@12809L16:ModalBottomSheet.android.kt#uh7d8r");
                            ModalBottomSheet_androidKt.ModalBottomSheetDialog$lambda$0(state).invoke(composer3, 0);
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
                }));
                composer2.updateRememberedValue(modalBottomSheetDialogWrapper);
                objRememberedValue2 = modalBottomSheetDialogWrapper;
            } else {
                layoutDirection = layoutDirection2;
                i3 = 0;
            }
            final ModalBottomSheetDialogWrapper modalBottomSheetDialogWrapper2 = (ModalBottomSheetDialogWrapper) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerStart(composer2, -1981494445, "CC(remember):ModalBottomSheet.android.kt#9igjgp");
            boolean zChangedInstance = composer2.changedInstance(modalBottomSheetDialogWrapper2);
            Object objRememberedValue3 = composer2.rememberedValue();
            if (zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetDialog$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        modalBottomSheetDialogWrapper2.show();
                        final ModalBottomSheetDialogWrapper modalBottomSheetDialogWrapper3 = modalBottomSheetDialogWrapper2;
                        return new DisposableEffectResult() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetDialog$1$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                modalBottomSheetDialogWrapper3.dismiss();
                                modalBottomSheetDialogWrapper3.disposeComposition();
                            }
                        };
                    }
                };
                composer2.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            EffectsKt.DisposableEffect(modalBottomSheetDialogWrapper2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composer2, i3);
            ComposerKt.sourceInformationMarkerStart(composer2, -1981489720, "CC(remember):ModalBottomSheet.android.kt#9igjgp");
            int i4 = (composer2.changedInstance(modalBottomSheetDialogWrapper2) ? 1 : 0) | ((i2 & 14) == 4 ? 1 : i3) | ((i2 & 112) == 32 ? 1 : i3) | (composer2.changed(layoutDirection) ? 1 : 0);
            Object objRememberedValue4 = composer2.rememberedValue();
            if (i4 != 0 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetDialog$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        modalBottomSheetDialogWrapper2.updateParameters(function0, modalBottomSheetProperties, layoutDirection);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            EffectsKt.SideEffect((Function0) objRememberedValue4, composer2, i3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt.ModalBottomSheetDialog.3
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

                public final void invoke(Composer composer3, int i5) {
                    ModalBottomSheet_androidKt.ModalBottomSheetDialog(function0, modalBottomSheetProperties, animatable, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public static final boolean isFlagSecureEnabled(View view) {
        ViewGroup.LayoutParams layoutParams = view.getRootView().getLayoutParams();
        WindowManager.LayoutParams layoutParams2 = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        return (layoutParams2 == null || (layoutParams2.flags & 8192) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldApplySecureFlag(SecureFlagPolicy secureFlagPolicy, boolean z) {
        int i = WhenMappings.$EnumSwitchMapping$0[secureFlagPolicy.ordinal()];
        if (i == 1) {
            return false;
        }
        if (i == 2) {
            return true;
        }
        if (i == 3) {
            return z;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2<Composer, Integer, Unit> ModalBottomSheetDialog$lambda$0(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        return (Function2) state.getValue();
    }
}
