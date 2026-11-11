package androidx.compose.ui.window;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.RendererCapabilities;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AndroidPopup.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000n\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aR\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0004\b\f\u0010\r\u001aD\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\u0010\u001a \u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0015H\u0002\u001a(\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u001b2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bH\u0001¢\u0006\u0002\u0010 \u001a+\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0013\b\b\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bH\u0083\b¢\u0006\u0002\u0010$\u001a\f\u0010%\u001a\u00020\u0015*\u00020&H\u0000\u001a\u0014\u0010'\u001a\u00020\u0012*\u00020\t2\u0006\u0010(\u001a\u00020\u0015H\u0002\u001a\f\u0010)\u001a\u00020**\u00020+H\u0002\u001a\u001c\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020&2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u001bH\u0007\"\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006/²\u0006\u0015\u00100\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bX\u008a\u0084\u0002"}, d2 = {"Popup", "", "alignment", "Landroidx/compose/ui/Alignment;", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/unit/IntOffset;", "onDismissRequest", "Lkotlin/Function0;", "properties", "Landroidx/compose/ui/window/PopupProperties;", "content", "Landroidx/compose/runtime/Composable;", "Popup-K5zGePQ", "(Landroidx/compose/ui/Alignment;JLkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "popupPositionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "PopupPropertiesBaseFlags", "", "createFlags", "focusable", "", "securePolicy", "Landroidx/compose/ui/window/SecureFlagPolicy;", "clippingEnabled", "LocalPopupTestTag", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "", "getLocalPopupTestTag", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "PopupTestTag", "tag", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "SimpleStack", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "isFlagSecureEnabled", "Landroid/view/View;", "flagsWithSecureFlagInherited", "isParentFlagSecureEnabled", "toIntBounds", "Landroidx/compose/ui/unit/IntRect;", "Landroid/graphics/Rect;", "isPopupLayout", "view", "testTag", "ui_release", "currentContent"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AndroidPopup_androidKt {
    private static final ProvidableCompositionLocal<String> LocalPopupTestTag = CompositionLocalKt.compositionLocalOf$default(null, new Function0<String>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$LocalPopupTestTag$1
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return "DEFAULT_TEST_TAG";
        }
    }, 1, null);
    private static final int PopupPropertiesBaseFlags = 262144;

    /* JADX WARN: Code duplicated, block: B:100:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004e  */
    /* JADX WARN: Code duplicated, block: B:27:0x0051  */
    /* JADX WARN: Code duplicated, block: B:29:0x0055  */
    /* JADX WARN: Code duplicated, block: B:31:0x005d  */
    /* JADX WARN: Code duplicated, block: B:32:0x0060  */
    /* JADX WARN: Code duplicated, block: B:37:0x006a  */
    /* JADX WARN: Code duplicated, block: B:38:0x006d  */
    /* JADX WARN: Code duplicated, block: B:40:0x0071  */
    /* JADX WARN: Code duplicated, block: B:42:0x0079  */
    /* JADX WARN: Code duplicated, block: B:43:0x007c  */
    /* JADX WARN: Code duplicated, block: B:48:0x0086  */
    /* JADX WARN: Code duplicated, block: B:49:0x0089  */
    /* JADX WARN: Code duplicated, block: B:51:0x008d  */
    /* JADX WARN: Code duplicated, block: B:53:0x0095  */
    /* JADX WARN: Code duplicated, block: B:54:0x0098  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:60:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:64:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:65:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:67:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:68:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:71:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:72:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:75:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:78:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:81:0x0100  */
    /* JADX WARN: Code duplicated, block: B:82:0x0103  */
    /* JADX WARN: Code duplicated, block: B:85:0x010b  */
    /* JADX WARN: Code duplicated, block: B:88:0x0115  */
    /* JADX WARN: Code duplicated, block: B:90:0x011d  */
    /* JADX WARN: Code duplicated, block: B:93:0x013c  */
    /* JADX WARN: Code duplicated, block: B:95:0x0142  */
    /* JADX WARN: Code duplicated, block: B:98:0x014f  */
    /* JADX INFO: renamed from: Popup-K5zGePQ, reason: not valid java name */
    public static final void m7146PopupK5zGePQ(Alignment alignment, long j, Function0<Unit> function0, PopupProperties popupProperties, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        Alignment alignment2;
        int i3;
        long j2;
        int i4;
        Function0<Unit> function1;
        int i5;
        int i6;
        PopupProperties popupProperties2;
        int i7;
        Function2<? super Composer, ? super Integer, Unit> function3;
        int i8;
        boolean z;
        final Alignment topStart;
        final long jM7016constructorimpl;
        final Function0<Unit> function4;
        final PopupProperties popupProperties3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        DefaultConstructorMarker defaultConstructorMarker;
        Function0<Unit> function5;
        PopupProperties popupProperties4;
        boolean z2;
        boolean z3;
        Object objRememberedValue;
        Composer composerStartRestartGroup = composer.startRestartGroup(71005054);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Popup)P(!1,2:c#ui.unit.IntOffset,3,4)270@12403L82,272@12491L166:AndroidPopup.android.kt#2oxthz");
        int i9 = i2 & 1;
        if (i9 != 0) {
            i3 = i | 6;
            alignment2 = alignment;
        } else if ((i & 6) == 0) {
            alignment2 = alignment;
            i3 = (composerStartRestartGroup.changed(alignment2) ? 4 : 2) | i;
        } else {
            alignment2 = alignment;
            i3 = i;
        }
        int i10 = i2 & 2;
        if (i10 == 0) {
            if ((i & 48) == 0) {
                j2 = j;
                i3 |= composerStartRestartGroup.changed(j2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    function1 = function0;
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 8;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        popupProperties2 = popupProperties;
                        if (composerStartRestartGroup.changed(popupProperties2)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    if ((i2 & 16) != 0) {
                        if ((i & 24576) == 0) {
                            function3 = function2;
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i8 = 16384;
                            } else {
                                i8 = 8192;
                            }
                            i3 |= i8;
                        }
                        if ((i3 & 9363) != 9362) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                            composerStartRestartGroup.skipToGroupEnd();
                            topStart = alignment2;
                            jM7016constructorimpl = j2;
                            function4 = function1;
                            popupProperties3 = popupProperties2;
                        } else {
                            if (i9 != 0) {
                                topStart = Alignment.INSTANCE.getTopStart();
                            } else {
                                topStart = alignment2;
                            }
                            if (i10 != 0) {
                                jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                            } else {
                                jM7016constructorimpl = j2;
                            }
                            defaultConstructorMarker = null;
                            if (i4 != 0) {
                                function5 = null;
                            } else {
                                function5 = function1;
                            }
                            if (i6 != 0) {
                                popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                            } else {
                                popupProperties4 = popupProperties2;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                            if ((i3 & 14) == 4) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            z3 = z2 | ((i3 & 112) == 32);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (!z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function4 = function5;
                            popupProperties3 = popupProperties4;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                                public final void invoke(Composer composer2, int i11) {
                                    AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                }
                            });
                        }
                    }
                    i3 |= 24576;
                    function3 = function2;
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.skipToGroupEnd();
                        topStart = alignment2;
                        jM7016constructorimpl = j2;
                        function4 = function1;
                        popupProperties3 = popupProperties2;
                    } else {
                        if (i9 != 0) {
                            topStart = Alignment.INSTANCE.getTopStart();
                        } else {
                            topStart = alignment2;
                        }
                        if (i10 != 0) {
                            jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                        } else {
                            jM7016constructorimpl = j2;
                        }
                        defaultConstructorMarker = null;
                        if (i4 != 0) {
                            function5 = null;
                        } else {
                            function5 = function1;
                        }
                        if (i6 != 0) {
                            popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                        } else {
                            popupProperties4 = popupProperties2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z3 = z2 | ((i3 & 112) == 32);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function4 = function5;
                        popupProperties3 = popupProperties4;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                            public final void invoke(Composer composer2, int i11) {
                                AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 3072;
                popupProperties2 = popupProperties;
                if ((i2 & 16) != 0) {
                    if ((i & 24576) == 0) {
                        function3 = function2;
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.skipToGroupEnd();
                        topStart = alignment2;
                        jM7016constructorimpl = j2;
                        function4 = function1;
                        popupProperties3 = popupProperties2;
                    } else {
                        if (i9 != 0) {
                            topStart = Alignment.INSTANCE.getTopStart();
                        } else {
                            topStart = alignment2;
                        }
                        if (i10 != 0) {
                            jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                        } else {
                            jM7016constructorimpl = j2;
                        }
                        defaultConstructorMarker = null;
                        if (i4 != 0) {
                            function5 = null;
                        } else {
                            function5 = function1;
                        }
                        if (i6 != 0) {
                            popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                        } else {
                            popupProperties4 = popupProperties2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z3 = z2 | ((i3 & 112) == 32);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function4 = function5;
                        popupProperties3 = popupProperties4;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                            public final void invoke(Composer composer2, int i11) {
                                AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 24576;
                function3 = function2;
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    topStart = alignment2;
                    jM7016constructorimpl = j2;
                    function4 = function1;
                    popupProperties3 = popupProperties2;
                } else {
                    if (i9 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i10 != 0) {
                        jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                    } else {
                        jM7016constructorimpl = j2;
                    }
                    defaultConstructorMarker = null;
                    if (i4 != 0) {
                        function5 = null;
                    } else {
                        function5 = function1;
                    }
                    if (i6 != 0) {
                        popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                    } else {
                        popupProperties4 = popupProperties2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    if ((i3 & 14) == 4) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = z2 | ((i3 & 112) == 32);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function4 = function5;
                    popupProperties3 = popupProperties4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                        public final void invoke(Composer composer2, int i11) {
                            AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            function1 = function0;
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    popupProperties2 = popupProperties;
                    if (composerStartRestartGroup.changed(popupProperties2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i2 & 16) != 0) {
                    if ((i & 24576) == 0) {
                        function3 = function2;
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.skipToGroupEnd();
                        topStart = alignment2;
                        jM7016constructorimpl = j2;
                        function4 = function1;
                        popupProperties3 = popupProperties2;
                    } else {
                        if (i9 != 0) {
                            topStart = Alignment.INSTANCE.getTopStart();
                        } else {
                            topStart = alignment2;
                        }
                        if (i10 != 0) {
                            jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                        } else {
                            jM7016constructorimpl = j2;
                        }
                        defaultConstructorMarker = null;
                        if (i4 != 0) {
                            function5 = null;
                        } else {
                            function5 = function1;
                        }
                        if (i6 != 0) {
                            popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                        } else {
                            popupProperties4 = popupProperties2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z3 = z2 | ((i3 & 112) == 32);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function4 = function5;
                        popupProperties3 = popupProperties4;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                            public final void invoke(Composer composer2, int i11) {
                                AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 24576;
                function3 = function2;
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    topStart = alignment2;
                    jM7016constructorimpl = j2;
                    function4 = function1;
                    popupProperties3 = popupProperties2;
                } else {
                    if (i9 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i10 != 0) {
                        jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                    } else {
                        jM7016constructorimpl = j2;
                    }
                    defaultConstructorMarker = null;
                    if (i4 != 0) {
                        function5 = null;
                    } else {
                        function5 = function1;
                    }
                    if (i6 != 0) {
                        popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                    } else {
                        popupProperties4 = popupProperties2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    if ((i3 & 14) == 4) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = z2 | ((i3 & 112) == 32);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function4 = function5;
                    popupProperties3 = popupProperties4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                        public final void invoke(Composer composer2, int i11) {
                            AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            popupProperties2 = popupProperties;
            if ((i2 & 16) != 0) {
                if ((i & 24576) == 0) {
                    function3 = function2;
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    topStart = alignment2;
                    jM7016constructorimpl = j2;
                    function4 = function1;
                    popupProperties3 = popupProperties2;
                } else {
                    if (i9 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i10 != 0) {
                        jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                    } else {
                        jM7016constructorimpl = j2;
                    }
                    defaultConstructorMarker = null;
                    if (i4 != 0) {
                        function5 = null;
                    } else {
                        function5 = function1;
                    }
                    if (i6 != 0) {
                        popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                    } else {
                        popupProperties4 = popupProperties2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    if ((i3 & 14) == 4) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = z2 | ((i3 & 112) == 32);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function4 = function5;
                    popupProperties3 = popupProperties4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                        public final void invoke(Composer composer2, int i11) {
                            AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 24576;
            function3 = function2;
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                topStart = alignment2;
                jM7016constructorimpl = j2;
                function4 = function1;
                popupProperties3 = popupProperties2;
            } else {
                if (i9 != 0) {
                    topStart = Alignment.INSTANCE.getTopStart();
                } else {
                    topStart = alignment2;
                }
                if (i10 != 0) {
                    jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                } else {
                    jM7016constructorimpl = j2;
                }
                defaultConstructorMarker = null;
                if (i4 != 0) {
                    function5 = null;
                } else {
                    function5 = function1;
                }
                if (i6 != 0) {
                    popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                } else {
                    popupProperties4 = popupProperties2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                if ((i3 & 14) == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = z2 | ((i3 & 112) == 32);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function4 = function5;
                popupProperties3 = popupProperties4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                    public final void invoke(Composer composer2, int i11) {
                        AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        j2 = j;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                function1 = function0;
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    popupProperties2 = popupProperties;
                    if (composerStartRestartGroup.changed(popupProperties2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i2 & 16) != 0) {
                    if ((i & 24576) == 0) {
                        function3 = function2;
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.skipToGroupEnd();
                        topStart = alignment2;
                        jM7016constructorimpl = j2;
                        function4 = function1;
                        popupProperties3 = popupProperties2;
                    } else {
                        if (i9 != 0) {
                            topStart = Alignment.INSTANCE.getTopStart();
                        } else {
                            topStart = alignment2;
                        }
                        if (i10 != 0) {
                            jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                        } else {
                            jM7016constructorimpl = j2;
                        }
                        defaultConstructorMarker = null;
                        if (i4 != 0) {
                            function5 = null;
                        } else {
                            function5 = function1;
                        }
                        if (i6 != 0) {
                            popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                        } else {
                            popupProperties4 = popupProperties2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                        if ((i3 & 14) == 4) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z3 = z2 | ((i3 & 112) == 32);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function4 = function5;
                        popupProperties3 = popupProperties4;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                            public final void invoke(Composer composer2, int i11) {
                                AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 24576;
                function3 = function2;
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    topStart = alignment2;
                    jM7016constructorimpl = j2;
                    function4 = function1;
                    popupProperties3 = popupProperties2;
                } else {
                    if (i9 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i10 != 0) {
                        jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                    } else {
                        jM7016constructorimpl = j2;
                    }
                    defaultConstructorMarker = null;
                    if (i4 != 0) {
                        function5 = null;
                    } else {
                        function5 = function1;
                    }
                    if (i6 != 0) {
                        popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                    } else {
                        popupProperties4 = popupProperties2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    if ((i3 & 14) == 4) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = z2 | ((i3 & 112) == 32);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function4 = function5;
                    popupProperties3 = popupProperties4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                        public final void invoke(Composer composer2, int i11) {
                            AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            popupProperties2 = popupProperties;
            if ((i2 & 16) != 0) {
                if ((i & 24576) == 0) {
                    function3 = function2;
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    topStart = alignment2;
                    jM7016constructorimpl = j2;
                    function4 = function1;
                    popupProperties3 = popupProperties2;
                } else {
                    if (i9 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i10 != 0) {
                        jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                    } else {
                        jM7016constructorimpl = j2;
                    }
                    defaultConstructorMarker = null;
                    if (i4 != 0) {
                        function5 = null;
                    } else {
                        function5 = function1;
                    }
                    if (i6 != 0) {
                        popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                    } else {
                        popupProperties4 = popupProperties2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    if ((i3 & 14) == 4) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = z2 | ((i3 & 112) == 32);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function4 = function5;
                    popupProperties3 = popupProperties4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                        public final void invoke(Composer composer2, int i11) {
                            AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 24576;
            function3 = function2;
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                topStart = alignment2;
                jM7016constructorimpl = j2;
                function4 = function1;
                popupProperties3 = popupProperties2;
            } else {
                if (i9 != 0) {
                    topStart = Alignment.INSTANCE.getTopStart();
                } else {
                    topStart = alignment2;
                }
                if (i10 != 0) {
                    jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                } else {
                    jM7016constructorimpl = j2;
                }
                defaultConstructorMarker = null;
                if (i4 != 0) {
                    function5 = null;
                } else {
                    function5 = function1;
                }
                if (i6 != 0) {
                    popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                } else {
                    popupProperties4 = popupProperties2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                if ((i3 & 14) == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = z2 | ((i3 & 112) == 32);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function4 = function5;
                popupProperties3 = popupProperties4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                    public final void invoke(Composer composer2, int i11) {
                        AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        function1 = function0;
        i6 = i2 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                popupProperties2 = popupProperties;
                if (composerStartRestartGroup.changed(popupProperties2)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            if ((i2 & 16) != 0) {
                if ((i & 24576) == 0) {
                    function3 = function2;
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i3 |= i8;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    topStart = alignment2;
                    jM7016constructorimpl = j2;
                    function4 = function1;
                    popupProperties3 = popupProperties2;
                } else {
                    if (i9 != 0) {
                        topStart = Alignment.INSTANCE.getTopStart();
                    } else {
                        topStart = alignment2;
                    }
                    if (i10 != 0) {
                        jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                    } else {
                        jM7016constructorimpl = j2;
                    }
                    defaultConstructorMarker = null;
                    if (i4 != 0) {
                        function5 = null;
                    } else {
                        function5 = function1;
                    }
                    if (i6 != 0) {
                        popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                    } else {
                        popupProperties4 = popupProperties2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    if ((i3 & 14) == 4) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = z2 | ((i3 & 112) == 32);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function4 = function5;
                    popupProperties3 = popupProperties4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                        public final void invoke(Composer composer2, int i11) {
                            AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 24576;
            function3 = function2;
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                topStart = alignment2;
                jM7016constructorimpl = j2;
                function4 = function1;
                popupProperties3 = popupProperties2;
            } else {
                if (i9 != 0) {
                    topStart = Alignment.INSTANCE.getTopStart();
                } else {
                    topStart = alignment2;
                }
                if (i10 != 0) {
                    jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                } else {
                    jM7016constructorimpl = j2;
                }
                defaultConstructorMarker = null;
                if (i4 != 0) {
                    function5 = null;
                } else {
                    function5 = function1;
                }
                if (i6 != 0) {
                    popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                } else {
                    popupProperties4 = popupProperties2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                if ((i3 & 14) == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = z2 | ((i3 & 112) == 32);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function4 = function5;
                popupProperties3 = popupProperties4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                    public final void invoke(Composer composer2, int i11) {
                        AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        popupProperties2 = popupProperties;
        if ((i2 & 16) != 0) {
            if ((i & 24576) == 0) {
                function3 = function2;
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i3 |= i8;
            }
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                topStart = alignment2;
                jM7016constructorimpl = j2;
                function4 = function1;
                popupProperties3 = popupProperties2;
            } else {
                if (i9 != 0) {
                    topStart = Alignment.INSTANCE.getTopStart();
                } else {
                    topStart = alignment2;
                }
                if (i10 != 0) {
                    jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
                } else {
                    jM7016constructorimpl = j2;
                }
                defaultConstructorMarker = null;
                if (i4 != 0) {
                    function5 = null;
                } else {
                    function5 = function1;
                }
                if (i6 != 0) {
                    popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                } else {
                    popupProperties4 = popupProperties2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
                if ((i3 & 14) == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = z2 | ((i3 & 112) == 32);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function4 = function5;
                popupProperties3 = popupProperties4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                    public final void invoke(Composer composer2, int i11) {
                        AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 24576;
        function3 = function2;
        if ((i3 & 9363) != 9362) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
            topStart = alignment2;
            jM7016constructorimpl = j2;
            function4 = function1;
            popupProperties3 = popupProperties2;
        } else {
            if (i9 != 0) {
                topStart = Alignment.INSTANCE.getTopStart();
            } else {
                topStart = alignment2;
            }
            if (i10 != 0) {
                jM7016constructorimpl = IntOffset.m7016constructorimpl(0L);
            } else {
                jM7016constructorimpl = j2;
            }
            defaultConstructorMarker = null;
            if (i4 != 0) {
                function5 = null;
            } else {
                function5 = function1;
            }
            if (i6 != 0) {
                popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
            } else {
                popupProperties4 = popupProperties2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(71005054, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483612592, "CC(remember):AndroidPopup.android.kt#9igjgp");
            if ((i3 & 14) == 4) {
                z2 = true;
            } else {
                z2 = false;
            }
            z3 = z2 | ((i3 & 112) == 32);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z3) {
                objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new AlignmentOffsetPositionProvider(topStart, jM7016constructorimpl, defaultConstructorMarker);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Popup((AlignmentOffsetPositionProvider) objRememberedValue, function5, popupProperties4, function3, composerStartRestartGroup, (i3 >> 3) & 8176, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function4 = function5;
            popupProperties3 = popupProperties4;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
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

                public final void invoke(Composer composer2, int i11) {
                    AndroidPopup_androidKt.m7146PopupK5zGePQ(topStart, jM7016constructorimpl, function4, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0256  */
    /* JADX WARN: Code duplicated, block: B:104:0x025e  */
    /* JADX WARN: Code duplicated, block: B:107:0x0281  */
    /* JADX WARN: Code duplicated, block: B:109:0x0289  */
    /* JADX WARN: Code duplicated, block: B:112:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:114:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:117:0x02e6  */
    /* JADX WARN: Code duplicated, block: B:119:0x02ee  */
    /* JADX WARN: Code duplicated, block: B:122:0x032c  */
    /* JADX WARN: Code duplicated, block: B:125:0x0338  */
    /* JADX WARN: Code duplicated, block: B:126:0x033c  */
    /* JADX WARN: Code duplicated, block: B:129:0x0361  */
    /* JADX WARN: Code duplicated, block: B:131:0x036f  */
    /* JADX WARN: Code duplicated, block: B:134:0x03a0  */
    /* JADX WARN: Code duplicated, block: B:136:0x03a8  */
    /* JADX WARN: Code duplicated, block: B:139:0x03b3  */
    /* JADX WARN: Code duplicated, block: B:141:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004b  */
    /* JADX WARN: Code duplicated, block: B:27:0x004e  */
    /* JADX WARN: Code duplicated, block: B:29:0x0052  */
    /* JADX WARN: Code duplicated, block: B:31:0x005a  */
    /* JADX WARN: Code duplicated, block: B:32:0x005d  */
    /* JADX WARN: Code duplicated, block: B:37:0x0067  */
    /* JADX WARN: Code duplicated, block: B:38:0x006a  */
    /* JADX WARN: Code duplicated, block: B:40:0x006e  */
    /* JADX WARN: Code duplicated, block: B:42:0x0074  */
    /* JADX WARN: Code duplicated, block: B:43:0x0077  */
    /* JADX WARN: Code duplicated, block: B:47:0x0081  */
    /* JADX WARN: Code duplicated, block: B:48:0x0083  */
    /* JADX WARN: Code duplicated, block: B:51:0x008c  */
    /* JADX WARN: Code duplicated, block: B:53:0x008f  */
    /* JADX WARN: Code duplicated, block: B:54:0x0092  */
    /* JADX WARN: Code duplicated, block: B:56:0x0096  */
    /* JADX WARN: Code duplicated, block: B:57:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:60:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:63:0x0127  */
    /* JADX WARN: Code duplicated, block: B:66:0x014f  */
    /* JADX WARN: Code duplicated, block: B:67:0x0187  */
    /* JADX WARN: Code duplicated, block: B:70:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:71:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:74:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:75:0x01af  */
    /* JADX WARN: Code duplicated, block: B:78:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:80:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:83:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:84:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:87:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:88:0x0201  */
    /* JADX WARN: Code duplicated, block: B:91:0x0217  */
    /* JADX WARN: Code duplicated, block: B:95:0x0223  */
    /* JADX WARN: Code duplicated, block: B:98:0x024c  */
    /* JADX WARN: Code duplicated, block: B:99:0x024e  */
    public static final void Popup(PopupPositionProvider popupPositionProvider, Function0<Unit> function0, PopupProperties popupProperties, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        int i3;
        Function0<Unit> function1;
        int i4;
        PopupProperties popupProperties2;
        int i5;
        int i6;
        boolean z;
        final Function0<Unit> function3;
        final PopupProperties popupProperties3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final Function0<Unit> function4;
        final PopupProperties popupProperties4;
        View view;
        Density density;
        String str;
        final LayoutDirection layoutDirection;
        CompositionContext compositionContextRememberCompositionContext;
        final State stateRememberUpdatedState;
        AndroidPopup_androidKt$Popup$popupId$1$1 androidPopup_androidKt$Popup$popupId$1$1RememberedValue;
        UUID uuid;
        Object objRememberedValue;
        String str2;
        boolean z2;
        int i7;
        final PopupLayout popupLayout;
        int i8;
        boolean z3;
        int i9;
        boolean z4;
        boolean zChanged;
        Object objRememberedValue2;
        boolean z5;
        boolean z6;
        boolean zChanged2;
        Object objRememberedValue3;
        int i10;
        boolean z7;
        boolean z8;
        Object objRememberedValue4;
        boolean zChangedInstance;
        AndroidPopup_androidKt$Popup$5$1 androidPopup_androidKt$Popup$5$1RememberedValue;
        boolean zChangedInstance2;
        Object objRememberedValue5;
        boolean zChangedInstance3;
        MeasurePolicy measurePolicyRememberedValue;
        int iM;
        Function0<ComposeUiNode> constructor;
        Composer composerM3454constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        final PopupPositionProvider popupPositionProvider2 = popupPositionProvider;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1772091631);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Popup)P(2,1,3)298@13359L7,299@13398L7,300@13442L7,301@13497L7,302@13533L28,303@13588L29,304@13653L21,304@13636L38,305@13697L1001,332@14734L388,332@14704L418,347@15139L219,347@15128L230,356@15404L126,356@15364L166,368@16121L147,368@16093L175,381@16537L573,389@17118L99,378@16446L771:AndroidPopup.android.kt#2oxthz");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(popupPositionProvider2) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i11 = i2 & 2;
        if (i11 == 0) {
            if ((i & 48) == 0) {
                function1 = function0;
                i3 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    popupProperties2 = popupProperties;
                    if (composerStartRestartGroup.changed(popupProperties2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i2 & 8) != 0) {
                    i3 |= 3072;
                } else if ((i & 3072) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i3 |= i6;
                }
                if ((i3 & 1171) != 1170) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    function3 = function1;
                    popupProperties3 = popupProperties2;
                } else {
                    if (i11 != 0) {
                        function4 = null;
                    } else {
                        function4 = function1;
                    }
                    if (i4 != 0) {
                        popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                    } else {
                        popupProperties4 = popupProperties2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1772091631, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:297)");
                    }
                    ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = composerStartRestartGroup.consume(localView);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    view = (View) objConsume;
                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume2 = composerStartRestartGroup.consume(localDensity);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    density = (Density) objConsume2;
                    ProvidableCompositionLocal<String> providableCompositionLocal = LocalPopupTestTag;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume3 = composerStartRestartGroup.consume(providableCompositionLocal);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    str = (String) objConsume3;
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume4 = composerStartRestartGroup.consume(localLayoutDirection);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    layoutDirection = (LayoutDirection) objConsume4;
                    compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
                    stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerStartRestartGroup, (i3 >> 9) & 14);
                    Object[] objArr = new Object[0];
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2121975462, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    androidPopup_androidKt$Popup$popupId$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (androidPopup_androidKt$Popup$popupId$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                        androidPopup_androidKt$Popup$popupId$1$1RememberedValue = new Function0<UUID>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupId$1$1
                            @Override // kotlin.jvm.functions.Function0
                            public final UUID invoke() {
                                return UUID.randomUUID();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(androidPopup_androidKt$Popup$popupId$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr, (Function0) androidPopup_androidKt$Popup$popupId$1$1RememberedValue, composerStartRestartGroup, 48);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2121977850, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        str2 = str;
                        i7 = 256;
                        final PopupLayout popupLayout2 = new PopupLayout(function4, popupProperties4, str2, view, density, popupPositionProvider2, uuid, null, 128, null);
                        popupPositionProvider2 = popupPositionProvider2;
                        z2 = true;
                        popupLayout2.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-297523940, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1
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

                            public final void invoke(Composer composer2, int i12) {
                                ComposerKt.sourceInformation(composer2, "C318@14188L16,320@14307L134,317@14132L528:AndroidPopup.android.kt#2oxthz");
                                if (!composer2.shouldExecute((i12 & 3) != 2, i12 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-297523940, i12, -1, "androidx.compose.ui.window.Popup.<anonymous>.<anonymous>.<anonymous> (AndroidPopup.android.kt:317)");
                                }
                                Modifier.Companion companion = Modifier.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2, 1842305004, "CC(remember):AndroidPopup.android.kt#9igjgp");
                                AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$1 androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue = composer2.rememberedValue();
                                if (androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                                    androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$1
                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            invoke2(semanticsPropertyReceiver);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            SemanticsPropertiesKt.popup(semanticsPropertyReceiver);
                                        }
                                    };
                                    composer2.updateRememberedValue(androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue, 1, null);
                                ComposerKt.sourceInformationMarkerStart(composer2, 1842308930, "CC(remember):AndroidPopup.android.kt#9igjgp");
                                boolean zChangedInstance4 = composer2.changedInstance(popupLayout2);
                                final PopupLayout popupLayout3 = popupLayout2;
                                Object objRememberedValue6 = composer2.rememberedValue();
                                if (zChangedInstance4 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue6 = (Function1) new Function1<IntSize, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$2$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                                            m7147invokeozmzZPI(intSize.m7069unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                                        public final void m7147invokeozmzZPI(long j) {
                                            popupLayout3.m7150setPopupContentSizefhxjrPA(IntSize.m7057boximpl(j));
                                            popupLayout3.updatePosition();
                                        }
                                    };
                                    composer2.updateRememberedValue(objRememberedValue6);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                Modifier modifierAlpha = AlphaKt.alpha(OnRemeasuredModifierKt.onSizeChanged(modifierSemantics$default, (Function1) objRememberedValue6), popupLayout2.getCanCalculatePosition() ? 1.0f : 0.0f);
                                Function2 function2Popup$lambda$1 = AndroidPopup_androidKt.Popup$lambda$1(stateRememberUpdatedState);
                                ComposerKt.sourceInformationMarkerStart(composer2, 26279861, "CC(SimpleStack)P(1)431@18599L899,431@18552L946:AndroidPopup.android.kt#2oxthz");
                                ComposerKt.sourceInformationMarkerStart(composer2, -514850408, "CC(remember):AndroidPopup.android.kt#9igjgp");
                                AndroidPopup_androidKt$SimpleStack$1$1 androidPopup_androidKt$SimpleStack$1$1RememberedValue = composer2.rememberedValue();
                                if (androidPopup_androidKt$SimpleStack$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                                    androidPopup_androidKt$SimpleStack$1$1RememberedValue = AndroidPopup_androidKt$SimpleStack$1$1.INSTANCE;
                                    composer2.updateRememberedValue(androidPopup_androidKt$SimpleStack$1$1RememberedValue);
                                }
                                MeasurePolicy measurePolicy = (MeasurePolicy) androidPopup_androidKt$SimpleStack$1$1RememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
                                int iM2 = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierAlpha);
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                if (!(composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    composer2.createNode(constructor2);
                                } else {
                                    composer2.useNode();
                                }
                                Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer2);
                                Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(iM2))) {
                                    composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(iM2));
                                    composerM3454constructorimpl2.apply(Integer.valueOf(iM2), setCompositeKeyHash2);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                function2Popup$lambda$1.invoke(composer2, 0);
                                composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        composerStartRestartGroup.updateRememberedValue(popupLayout2);
                        objRememberedValue = popupLayout2;
                    } else {
                        str2 = str;
                        z2 = true;
                        i7 = 256;
                    }
                    popupLayout = (PopupLayout) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122010421, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(popupLayout);
                    int i12 = i3;
                    i8 = i12 & 112;
                    if (i8 == 32) {
                        z3 = z2;
                    } else {
                        z3 = false;
                    }
                    boolean z9 = zChangedInstance4 | z3;
                    i9 = i12 & 896;
                    if (i9 == i7) {
                        z4 = z2;
                    } else {
                        z4 = false;
                    }
                    LayoutDirection layoutDirection2 = layoutDirection;
                    zChanged = z9 | z4 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection2.ordinal());
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        final String str3 = str2;
                        objRememberedValue2 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                                popupLayout.show();
                                popupLayout.updateParameters(function4, popupProperties4, str3, layoutDirection);
                                final PopupLayout popupLayout3 = popupLayout;
                                return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                                    @Override // androidx.compose.runtime.DisposableEffectResult
                                    public void dispose() {
                                        popupLayout3.disposeComposition();
                                        popupLayout3.dismiss();
                                    }
                                };
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.DisposableEffect(popupLayout, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122023212, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(popupLayout);
                    if (i8 == 32) {
                        z5 = z2;
                    } else {
                        z5 = false;
                    }
                    boolean z10 = zChangedInstance5 | z5;
                    if (i9 == i7) {
                        z6 = z2;
                    } else {
                        z6 = false;
                    }
                    zChanged2 = z10 | z6 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection2.ordinal());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        final String str4 = str2;
                        objRememberedValue3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
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
                                popupLayout.updateParameters(function4, popupProperties4, str4, layoutDirection);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.SideEffect((Function0) objRememberedValue3, composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122031599, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    boolean zChangedInstance6 = composerStartRestartGroup.changedInstance(popupLayout);
                    i10 = i12 & 14;
                    if (i10 == 4) {
                        z7 = z2;
                    } else {
                        z7 = false;
                    }
                    z8 = zChangedInstance6 | z7;
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (!z8 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                                popupLayout.setPositionProvider(popupPositionProvider2);
                                popupLayout.updatePosition();
                                return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                                    @Override // androidx.compose.runtime.DisposableEffectResult
                                    public void dispose() {
                                    }
                                };
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.DisposableEffect(popupPositionProvider2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue4, composerStartRestartGroup, i10);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122054564, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    zChangedInstance = composerStartRestartGroup.changedInstance(popupLayout);
                    androidPopup_androidKt$Popup$5$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance || androidPopup_androidKt$Popup$5$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                        androidPopup_androidKt$Popup$5$1RememberedValue = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                        composerStartRestartGroup.updateRememberedValue(androidPopup_androidKt$Popup$5$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.LaunchedEffect(popupLayout, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) androidPopup_androidKt$Popup$5$1RememberedValue, composerStartRestartGroup, 0);
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122068302, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    zChangedInstance2 = composerStartRestartGroup.changedInstance(popupLayout);
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue5 = (Function1) new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                                invoke2(layoutCoordinates);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(LayoutCoordinates layoutCoordinates) {
                                LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                                Intrinsics.checkNotNull(parentLayoutCoordinates);
                                popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(companion, (Function1) objRememberedValue5);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122086420, "CC(remember):AndroidPopup.android.kt#9igjgp");
                    zChangedInstance3 = composerStartRestartGroup.changedInstance(popupLayout) | composerStartRestartGroup.changed(layoutDirection2.ordinal());
                    measurePolicyRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance3 || measurePolicyRememberedValue == Composer.INSTANCE.getEmpty()) {
                        measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                            @Override // androidx.compose.ui.layout.MeasurePolicy
                            public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i13) {
                                return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i13);
                            }

                            @Override // androidx.compose.ui.layout.MeasurePolicy
                            public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i13) {
                                return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i13);
                            }

                            @Override // androidx.compose.ui.layout.MeasurePolicy
                            public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i13) {
                                return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i13);
                            }

                            @Override // androidx.compose.ui.layout.MeasurePolicy
                            public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i13) {
                                return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i13);
                            }

                            @Override // androidx.compose.ui.layout.MeasurePolicy
                            /* JADX INFO: renamed from: measure-3p2s80s */
                            public final MeasureResult mo72measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                                popupLayout.setParentLayoutDirection(layoutDirection);
                                return MeasureScope.CC.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Placeable.PlacementScope placementScope) {
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                        invoke2(placementScope);
                                        return Unit.INSTANCE;
                                    }
                                }, 4, null);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(measurePolicyRememberedValue);
                    }
                    MeasurePolicy measurePolicy = (MeasurePolicy) measurePolicyRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
                    iM = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnGloballyPositioned);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(iM))) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(iM));
                        composerM3454constructorimpl.apply(Integer.valueOf(iM), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -958253783, "C:AndroidPopup.android.kt#2oxthz");
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = function4;
                    popupProperties3 = popupProperties4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.9
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

                        public final void invoke(Composer composer2, int i13) {
                            AndroidPopup_androidKt.Popup(popupPositionProvider2, function3, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            popupProperties2 = popupProperties;
            if ((i2 & 8) != 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i3 |= i6;
            }
            if ((i3 & 1171) != 1170) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                function3 = function1;
                popupProperties3 = popupProperties2;
            } else {
                if (i11 != 0) {
                    function4 = null;
                } else {
                    function4 = function1;
                }
                if (i4 != 0) {
                    popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                } else {
                    popupProperties4 = popupProperties2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1772091631, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:297)");
                }
                ProvidableCompositionLocal<View> localView2 = AndroidCompositionLocals_androidKt.getLocalView();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume5 = composerStartRestartGroup.consume(localView2);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                view = (View) objConsume5;
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume6 = composerStartRestartGroup.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume6;
                ProvidableCompositionLocal<String> providableCompositionLocal2 = LocalPopupTestTag;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume7 = composerStartRestartGroup.consume(providableCompositionLocal2);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                str = (String) objConsume7;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume8 = composerStartRestartGroup.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                layoutDirection = (LayoutDirection) objConsume8;
                compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
                stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerStartRestartGroup, (i3 >> 9) & 14);
                Object[] objArr2 = new Object[0];
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2121975462, "CC(remember):AndroidPopup.android.kt#9igjgp");
                androidPopup_androidKt$Popup$popupId$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (androidPopup_androidKt$Popup$popupId$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    androidPopup_androidKt$Popup$popupId$1$1RememberedValue = new Function0<UUID>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupId$1$1
                        @Override // kotlin.jvm.functions.Function0
                        public final UUID invoke() {
                            return UUID.randomUUID();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(androidPopup_androidKt$Popup$popupId$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr2, (Function0) androidPopup_androidKt$Popup$popupId$1$1RememberedValue, composerStartRestartGroup, 48);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2121977850, "CC(remember):AndroidPopup.android.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    str2 = str;
                    i7 = 256;
                    final PopupLayout popupLayout3 = new PopupLayout(function4, popupProperties4, str2, view, density, popupPositionProvider2, uuid, null, 128, null);
                    popupPositionProvider2 = popupPositionProvider2;
                    z2 = true;
                    popupLayout3.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-297523940, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1
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

                        public final void invoke(Composer composer2, int i13) {
                            ComposerKt.sourceInformation(composer2, "C318@14188L16,320@14307L134,317@14132L528:AndroidPopup.android.kt#2oxthz");
                            if (!composer2.shouldExecute((i13 & 3) != 2, i13 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-297523940, i13, -1, "androidx.compose.ui.window.Popup.<anonymous>.<anonymous>.<anonymous> (AndroidPopup.android.kt:317)");
                            }
                            Modifier.Companion companion2 = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1842305004, "CC(remember):AndroidPopup.android.kt#9igjgp");
                            AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$1 androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue = composer2.rememberedValue();
                            if (androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                                androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$1
                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        SemanticsPropertiesKt.popup(semanticsPropertyReceiver);
                                    }
                                };
                                composer2.updateRememberedValue(androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion2, false, (Function1) androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue, 1, null);
                            ComposerKt.sourceInformationMarkerStart(composer2, 1842308930, "CC(remember):AndroidPopup.android.kt#9igjgp");
                            boolean zChangedInstance7 = composer2.changedInstance(popupLayout3);
                            final PopupLayout popupLayout4 = popupLayout3;
                            Object objRememberedValue6 = composer2.rememberedValue();
                            if (zChangedInstance7 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = (Function1) new Function1<IntSize, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$2$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                                        m7147invokeozmzZPI(intSize.m7069unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                                    public final void m7147invokeozmzZPI(long j) {
                                        popupLayout4.m7150setPopupContentSizefhxjrPA(IntSize.m7057boximpl(j));
                                        popupLayout4.updatePosition();
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue6);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier modifierAlpha = AlphaKt.alpha(OnRemeasuredModifierKt.onSizeChanged(modifierSemantics$default, (Function1) objRememberedValue6), popupLayout3.getCanCalculatePosition() ? 1.0f : 0.0f);
                            Function2 function2Popup$lambda$1 = AndroidPopup_androidKt.Popup$lambda$1(stateRememberUpdatedState);
                            ComposerKt.sourceInformationMarkerStart(composer2, 26279861, "CC(SimpleStack)P(1)431@18599L899,431@18552L946:AndroidPopup.android.kt#2oxthz");
                            ComposerKt.sourceInformationMarkerStart(composer2, -514850408, "CC(remember):AndroidPopup.android.kt#9igjgp");
                            AndroidPopup_androidKt$SimpleStack$1$1 androidPopup_androidKt$SimpleStack$1$1RememberedValue = composer2.rememberedValue();
                            if (androidPopup_androidKt$SimpleStack$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                                androidPopup_androidKt$SimpleStack$1$1RememberedValue = AndroidPopup_androidKt$SimpleStack$1$1.INSTANCE;
                                composer2.updateRememberedValue(androidPopup_androidKt$SimpleStack$1$1RememberedValue);
                            }
                            MeasurePolicy measurePolicy2 = (MeasurePolicy) androidPopup_androidKt$SimpleStack$1$1RememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
                            int iM2 = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, modifierAlpha);
                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor2);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer2);
                            Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(iM2))) {
                                composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(iM2));
                                composerM3454constructorimpl2.apply(Integer.valueOf(iM2), setCompositeKeyHash2);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                            function2Popup$lambda$1.invoke(composer2, 0);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    composerStartRestartGroup.updateRememberedValue(popupLayout3);
                    objRememberedValue = popupLayout3;
                } else {
                    str2 = str;
                    z2 = true;
                    i7 = 256;
                }
                popupLayout = (PopupLayout) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122010421, "CC(remember):AndroidPopup.android.kt#9igjgp");
                boolean zChangedInstance7 = composerStartRestartGroup.changedInstance(popupLayout);
                int i13 = i3;
                i8 = i13 & 112;
                if (i8 == 32) {
                    z3 = z2;
                } else {
                    z3 = false;
                }
                boolean z11 = zChangedInstance7 | z3;
                i9 = i13 & 896;
                if (i9 == i7) {
                    z4 = z2;
                } else {
                    z4 = false;
                }
                LayoutDirection layoutDirection3 = layoutDirection;
                zChanged = z11 | z4 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection3.ordinal());
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    final String str5 = str2;
                    objRememberedValue2 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.show();
                            popupLayout.updateParameters(function4, popupProperties4, str5, layoutDirection);
                            final PopupLayout popupLayout4 = popupLayout;
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    popupLayout4.disposeComposition();
                                    popupLayout4.dismiss();
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    final String str6 = str2;
                    objRememberedValue2 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.show();
                            popupLayout.updateParameters(function4, popupProperties4, str6, layoutDirection);
                            final PopupLayout popupLayout4 = popupLayout;
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    popupLayout4.disposeComposition();
                                    popupLayout4.dismiss();
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.DisposableEffect(popupLayout, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122023212, "CC(remember):AndroidPopup.android.kt#9igjgp");
                boolean zChangedInstance8 = composerStartRestartGroup.changedInstance(popupLayout);
                if (i8 == 32) {
                    z5 = z2;
                } else {
                    z5 = false;
                }
                boolean z12 = zChangedInstance8 | z5;
                if (i9 == i7) {
                    z6 = z2;
                } else {
                    z6 = false;
                }
                zChanged2 = z12 | z6 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection3.ordinal());
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged2) {
                    final String str7 = str2;
                    objRememberedValue3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
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
                            popupLayout.updateParameters(function4, popupProperties4, str7, layoutDirection);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    final String str8 = str2;
                    objRememberedValue3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
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
                            popupLayout.updateParameters(function4, popupProperties4, str8, layoutDirection);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.SideEffect((Function0) objRememberedValue3, composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122031599, "CC(remember):AndroidPopup.android.kt#9igjgp");
                boolean zChangedInstance9 = composerStartRestartGroup.changedInstance(popupLayout);
                i10 = i13 & 14;
                if (i10 == 4) {
                    z7 = z2;
                } else {
                    z7 = false;
                }
                z8 = zChangedInstance9 | z7;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!z8) {
                    objRememberedValue4 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.setPositionProvider(popupPositionProvider2);
                            popupLayout.updatePosition();
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.setPositionProvider(popupPositionProvider2);
                            popupLayout.updatePosition();
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.DisposableEffect(popupPositionProvider2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue4, composerStartRestartGroup, i10);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122054564, "CC(remember):AndroidPopup.android.kt#9igjgp");
                zChangedInstance = composerStartRestartGroup.changedInstance(popupLayout);
                androidPopup_androidKt$Popup$5$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    androidPopup_androidKt$Popup$5$1RememberedValue = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                    composerStartRestartGroup.updateRememberedValue(androidPopup_androidKt$Popup$5$1RememberedValue);
                } else {
                    androidPopup_androidKt$Popup$5$1RememberedValue = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                    composerStartRestartGroup.updateRememberedValue(androidPopup_androidKt$Popup$5$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.LaunchedEffect(popupLayout, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) androidPopup_androidKt$Popup$5$1RememberedValue, composerStartRestartGroup, 0);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122068302, "CC(remember):AndroidPopup.android.kt#9igjgp");
                zChangedInstance2 = composerStartRestartGroup.changedInstance(popupLayout);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance2) {
                    objRememberedValue5 = (Function1) new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                            invoke2(layoutCoordinates);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(LayoutCoordinates layoutCoordinates) {
                            LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                            Intrinsics.checkNotNull(parentLayoutCoordinates);
                            popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = (Function1) new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                            invoke2(layoutCoordinates);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(LayoutCoordinates layoutCoordinates) {
                            LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                            Intrinsics.checkNotNull(parentLayoutCoordinates);
                            popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierOnGloballyPositioned2 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion2, (Function1) objRememberedValue5);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122086420, "CC(remember):AndroidPopup.android.kt#9igjgp");
                zChangedInstance3 = composerStartRestartGroup.changedInstance(popupLayout) | composerStartRestartGroup.changed(layoutDirection3.ordinal());
                measurePolicyRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance3) {
                    measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                            return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i14);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                            return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i14);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                            return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i14);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                            return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i14);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        /* JADX INFO: renamed from: measure-3p2s80s */
                        public final MeasureResult mo72measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                            popupLayout.setParentLayoutDirection(layoutDirection);
                            return MeasureScope.CC.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Placeable.PlacementScope placementScope) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                    invoke2(placementScope);
                                    return Unit.INSTANCE;
                                }
                            }, 4, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(measurePolicyRememberedValue);
                } else {
                    measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                            return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i14);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                            return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i14);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                            return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i14);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                            return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i14);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        /* JADX INFO: renamed from: measure-3p2s80s */
                        public final MeasureResult mo72measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                            popupLayout.setParentLayoutDirection(layoutDirection);
                            return MeasureScope.CC.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Placeable.PlacementScope placementScope) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                    invoke2(placementScope);
                                    return Unit.INSTANCE;
                                }
                            }, 4, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(measurePolicyRememberedValue);
                }
                MeasurePolicy measurePolicy2 = (MeasurePolicy) measurePolicyRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
                iM = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnGloballyPositioned2);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(iM));
                    composerM3454constructorimpl.apply(Integer.valueOf(iM), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(iM));
                    composerM3454constructorimpl.apply(Integer.valueOf(iM), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -958253783, "C:AndroidPopup.android.kt#2oxthz");
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = function4;
                popupProperties3 = popupProperties4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.9
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

                    public final void invoke(Composer composer2, int i14) {
                        AndroidPopup_androidKt.Popup(popupPositionProvider2, function3, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        function1 = function0;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                popupProperties2 = popupProperties;
                if (composerStartRestartGroup.changed(popupProperties2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i2 & 8) != 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i3 |= i6;
            }
            if ((i3 & 1171) != 1170) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                function3 = function1;
                popupProperties3 = popupProperties2;
            } else {
                if (i11 != 0) {
                    function4 = null;
                } else {
                    function4 = function1;
                }
                if (i4 != 0) {
                    popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
                } else {
                    popupProperties4 = popupProperties2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1772091631, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:297)");
                }
                ProvidableCompositionLocal<View> localView3 = AndroidCompositionLocals_androidKt.getLocalView();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume9 = composerStartRestartGroup.consume(localView3);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                view = (View) objConsume9;
                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume10 = composerStartRestartGroup.consume(localDensity3);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                density = (Density) objConsume10;
                ProvidableCompositionLocal<String> providableCompositionLocal3 = LocalPopupTestTag;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume11 = composerStartRestartGroup.consume(providableCompositionLocal3);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                str = (String) objConsume11;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume12 = composerStartRestartGroup.consume(localLayoutDirection3);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                layoutDirection = (LayoutDirection) objConsume12;
                compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
                stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerStartRestartGroup, (i3 >> 9) & 14);
                Object[] objArr3 = new Object[0];
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2121975462, "CC(remember):AndroidPopup.android.kt#9igjgp");
                androidPopup_androidKt$Popup$popupId$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (androidPopup_androidKt$Popup$popupId$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    androidPopup_androidKt$Popup$popupId$1$1RememberedValue = new Function0<UUID>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupId$1$1
                        @Override // kotlin.jvm.functions.Function0
                        public final UUID invoke() {
                            return UUID.randomUUID();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(androidPopup_androidKt$Popup$popupId$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr3, (Function0) androidPopup_androidKt$Popup$popupId$1$1RememberedValue, composerStartRestartGroup, 48);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2121977850, "CC(remember):AndroidPopup.android.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    str2 = str;
                    i7 = 256;
                    final PopupLayout popupLayout4 = new PopupLayout(function4, popupProperties4, str2, view, density, popupPositionProvider2, uuid, null, 128, null);
                    popupPositionProvider2 = popupPositionProvider2;
                    z2 = true;
                    popupLayout4.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-297523940, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1
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

                        public final void invoke(Composer composer2, int i14) {
                            ComposerKt.sourceInformation(composer2, "C318@14188L16,320@14307L134,317@14132L528:AndroidPopup.android.kt#2oxthz");
                            if (!composer2.shouldExecute((i14 & 3) != 2, i14 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-297523940, i14, -1, "androidx.compose.ui.window.Popup.<anonymous>.<anonymous>.<anonymous> (AndroidPopup.android.kt:317)");
                            }
                            Modifier.Companion companion3 = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, 1842305004, "CC(remember):AndroidPopup.android.kt#9igjgp");
                            AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$1 androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue = composer2.rememberedValue();
                            if (androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                                androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$1
                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        SemanticsPropertiesKt.popup(semanticsPropertyReceiver);
                                    }
                                };
                                composer2.updateRememberedValue(androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion3, false, (Function1) androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue, 1, null);
                            ComposerKt.sourceInformationMarkerStart(composer2, 1842308930, "CC(remember):AndroidPopup.android.kt#9igjgp");
                            boolean zChangedInstance10 = composer2.changedInstance(popupLayout4);
                            final PopupLayout popupLayout5 = popupLayout4;
                            Object objRememberedValue6 = composer2.rememberedValue();
                            if (zChangedInstance10 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = (Function1) new Function1<IntSize, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$2$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                                        m7147invokeozmzZPI(intSize.m7069unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                                    public final void m7147invokeozmzZPI(long j) {
                                        popupLayout5.m7150setPopupContentSizefhxjrPA(IntSize.m7057boximpl(j));
                                        popupLayout5.updatePosition();
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue6);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier modifierAlpha = AlphaKt.alpha(OnRemeasuredModifierKt.onSizeChanged(modifierSemantics$default, (Function1) objRememberedValue6), popupLayout4.getCanCalculatePosition() ? 1.0f : 0.0f);
                            Function2 function2Popup$lambda$1 = AndroidPopup_androidKt.Popup$lambda$1(stateRememberUpdatedState);
                            ComposerKt.sourceInformationMarkerStart(composer2, 26279861, "CC(SimpleStack)P(1)431@18599L899,431@18552L946:AndroidPopup.android.kt#2oxthz");
                            ComposerKt.sourceInformationMarkerStart(composer2, -514850408, "CC(remember):AndroidPopup.android.kt#9igjgp");
                            AndroidPopup_androidKt$SimpleStack$1$1 androidPopup_androidKt$SimpleStack$1$1RememberedValue = composer2.rememberedValue();
                            if (androidPopup_androidKt$SimpleStack$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                                androidPopup_androidKt$SimpleStack$1$1RememberedValue = AndroidPopup_androidKt$SimpleStack$1$1.INSTANCE;
                                composer2.updateRememberedValue(androidPopup_androidKt$SimpleStack$1$1RememberedValue);
                            }
                            MeasurePolicy measurePolicy3 = (MeasurePolicy) androidPopup_androidKt$SimpleStack$1$1RememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
                            int iM2 = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, modifierAlpha);
                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor2);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer2);
                            Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(iM2))) {
                                composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(iM2));
                                composerM3454constructorimpl2.apply(Integer.valueOf(iM2), setCompositeKeyHash2);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                            function2Popup$lambda$1.invoke(composer2, 0);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    composerStartRestartGroup.updateRememberedValue(popupLayout4);
                    objRememberedValue = popupLayout4;
                } else {
                    str2 = str;
                    z2 = true;
                    i7 = 256;
                }
                popupLayout = (PopupLayout) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122010421, "CC(remember):AndroidPopup.android.kt#9igjgp");
                boolean zChangedInstance10 = composerStartRestartGroup.changedInstance(popupLayout);
                int i14 = i3;
                i8 = i14 & 112;
                if (i8 == 32) {
                    z3 = z2;
                } else {
                    z3 = false;
                }
                boolean z13 = zChangedInstance10 | z3;
                i9 = i14 & 896;
                if (i9 == i7) {
                    z4 = z2;
                } else {
                    z4 = false;
                }
                LayoutDirection layoutDirection4 = layoutDirection;
                zChanged = z13 | z4 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection4.ordinal());
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    final String str9 = str2;
                    objRememberedValue2 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.show();
                            popupLayout.updateParameters(function4, popupProperties4, str9, layoutDirection);
                            final PopupLayout popupLayout5 = popupLayout;
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    popupLayout5.disposeComposition();
                                    popupLayout5.dismiss();
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    final String str10 = str2;
                    objRememberedValue2 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.show();
                            popupLayout.updateParameters(function4, popupProperties4, str10, layoutDirection);
                            final PopupLayout popupLayout5 = popupLayout;
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    popupLayout5.disposeComposition();
                                    popupLayout5.dismiss();
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.DisposableEffect(popupLayout, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122023212, "CC(remember):AndroidPopup.android.kt#9igjgp");
                boolean zChangedInstance11 = composerStartRestartGroup.changedInstance(popupLayout);
                if (i8 == 32) {
                    z5 = z2;
                } else {
                    z5 = false;
                }
                boolean z14 = zChangedInstance11 | z5;
                if (i9 == i7) {
                    z6 = z2;
                } else {
                    z6 = false;
                }
                zChanged2 = z14 | z6 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection4.ordinal());
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged2) {
                    final String str11 = str2;
                    objRememberedValue3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
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
                            popupLayout.updateParameters(function4, popupProperties4, str11, layoutDirection);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    final String str12 = str2;
                    objRememberedValue3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
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
                            popupLayout.updateParameters(function4, popupProperties4, str12, layoutDirection);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.SideEffect((Function0) objRememberedValue3, composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122031599, "CC(remember):AndroidPopup.android.kt#9igjgp");
                boolean zChangedInstance12 = composerStartRestartGroup.changedInstance(popupLayout);
                i10 = i14 & 14;
                if (i10 == 4) {
                    z7 = z2;
                } else {
                    z7 = false;
                }
                z8 = zChangedInstance12 | z7;
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!z8) {
                    objRememberedValue4 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.setPositionProvider(popupPositionProvider2);
                            popupLayout.updatePosition();
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                            popupLayout.setPositionProvider(popupPositionProvider2);
                            popupLayout.updatePosition();
                            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                }
                            };
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.DisposableEffect(popupPositionProvider2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue4, composerStartRestartGroup, i10);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122054564, "CC(remember):AndroidPopup.android.kt#9igjgp");
                zChangedInstance = composerStartRestartGroup.changedInstance(popupLayout);
                androidPopup_androidKt$Popup$5$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    androidPopup_androidKt$Popup$5$1RememberedValue = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                    composerStartRestartGroup.updateRememberedValue(androidPopup_androidKt$Popup$5$1RememberedValue);
                } else {
                    androidPopup_androidKt$Popup$5$1RememberedValue = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                    composerStartRestartGroup.updateRememberedValue(androidPopup_androidKt$Popup$5$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.LaunchedEffect(popupLayout, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) androidPopup_androidKt$Popup$5$1RememberedValue, composerStartRestartGroup, 0);
                Modifier.Companion companion3 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122068302, "CC(remember):AndroidPopup.android.kt#9igjgp");
                zChangedInstance2 = composerStartRestartGroup.changedInstance(popupLayout);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance2) {
                    objRememberedValue5 = (Function1) new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                            invoke2(layoutCoordinates);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(LayoutCoordinates layoutCoordinates) {
                            LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                            Intrinsics.checkNotNull(parentLayoutCoordinates);
                            popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = (Function1) new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                            invoke2(layoutCoordinates);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(LayoutCoordinates layoutCoordinates) {
                            LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                            Intrinsics.checkNotNull(parentLayoutCoordinates);
                            popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierOnGloballyPositioned3 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion3, (Function1) objRememberedValue5);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122086420, "CC(remember):AndroidPopup.android.kt#9igjgp");
                zChangedInstance3 = composerStartRestartGroup.changedInstance(popupLayout) | composerStartRestartGroup.changed(layoutDirection4.ordinal());
                measurePolicyRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance3) {
                    measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i15) {
                            return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i15);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i15) {
                            return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i15);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i15) {
                            return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i15);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i15) {
                            return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i15);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        /* JADX INFO: renamed from: measure-3p2s80s */
                        public final MeasureResult mo72measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                            popupLayout.setParentLayoutDirection(layoutDirection);
                            return MeasureScope.CC.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Placeable.PlacementScope placementScope) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                    invoke2(placementScope);
                                    return Unit.INSTANCE;
                                }
                            }, 4, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(measurePolicyRememberedValue);
                } else {
                    measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i15) {
                            return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i15);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i15) {
                            return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i15);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i15) {
                            return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i15);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i15) {
                            return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i15);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        /* JADX INFO: renamed from: measure-3p2s80s */
                        public final MeasureResult mo72measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                            popupLayout.setParentLayoutDirection(layoutDirection);
                            return MeasureScope.CC.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Placeable.PlacementScope placementScope) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                    invoke2(placementScope);
                                    return Unit.INSTANCE;
                                }
                            }, 4, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(measurePolicyRememberedValue);
                }
                MeasurePolicy measurePolicy3 = (MeasurePolicy) measurePolicyRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
                iM = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnGloballyPositioned3);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(iM));
                    composerM3454constructorimpl.apply(Integer.valueOf(iM), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(iM));
                    composerM3454constructorimpl.apply(Integer.valueOf(iM), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -958253783, "C:AndroidPopup.android.kt#2oxthz");
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = function4;
                popupProperties3 = popupProperties4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.9
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

                    public final void invoke(Composer composer2, int i15) {
                        AndroidPopup_androidKt.Popup(popupPositionProvider2, function3, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        popupProperties2 = popupProperties;
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            if (composerStartRestartGroup.changedInstance(function2)) {
                i6 = 2048;
            } else {
                i6 = 1024;
            }
            i3 |= i6;
        }
        if ((i3 & 1171) != 1170) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
            function3 = function1;
            popupProperties3 = popupProperties2;
        } else {
            if (i11 != 0) {
                function4 = null;
            } else {
                function4 = function1;
            }
            if (i4 != 0) {
                popupProperties4 = new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null);
            } else {
                popupProperties4 = popupProperties2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1772091631, i3, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:297)");
            }
            ProvidableCompositionLocal<View> localView4 = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume13 = composerStartRestartGroup.consume(localView4);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            view = (View) objConsume13;
            ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume14 = composerStartRestartGroup.consume(localDensity4);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            density = (Density) objConsume14;
            ProvidableCompositionLocal<String> providableCompositionLocal4 = LocalPopupTestTag;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume15 = composerStartRestartGroup.consume(providableCompositionLocal4);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            str = (String) objConsume15;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume16 = composerStartRestartGroup.consume(localLayoutDirection4);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            layoutDirection = (LayoutDirection) objConsume16;
            compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerStartRestartGroup, (i3 >> 9) & 14);
            Object[] objArr4 = new Object[0];
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2121975462, "CC(remember):AndroidPopup.android.kt#9igjgp");
            androidPopup_androidKt$Popup$popupId$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (androidPopup_androidKt$Popup$popupId$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                androidPopup_androidKt$Popup$popupId$1$1RememberedValue = new Function0<UUID>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupId$1$1
                    @Override // kotlin.jvm.functions.Function0
                    public final UUID invoke() {
                        return UUID.randomUUID();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(androidPopup_androidKt$Popup$popupId$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr4, (Function0) androidPopup_androidKt$Popup$popupId$1$1RememberedValue, composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2121977850, "CC(remember):AndroidPopup.android.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                str2 = str;
                i7 = 256;
                final PopupLayout popupLayout5 = new PopupLayout(function4, popupProperties4, str2, view, density, popupPositionProvider2, uuid, null, 128, null);
                popupPositionProvider2 = popupPositionProvider2;
                z2 = true;
                popupLayout5.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-297523940, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1
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

                    public final void invoke(Composer composer2, int i15) {
                        ComposerKt.sourceInformation(composer2, "C318@14188L16,320@14307L134,317@14132L528:AndroidPopup.android.kt#2oxthz");
                        if (!composer2.shouldExecute((i15 & 3) != 2, i15 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-297523940, i15, -1, "androidx.compose.ui.window.Popup.<anonymous>.<anonymous>.<anonymous> (AndroidPopup.android.kt:317)");
                        }
                        Modifier.Companion companion4 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer2, 1842305004, "CC(remember):AndroidPopup.android.kt#9igjgp");
                        AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$1 androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue = composer2.rememberedValue();
                        if (androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                            androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$1$1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    SemanticsPropertiesKt.popup(semanticsPropertyReceiver);
                                }
                            };
                            composer2.updateRememberedValue(androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion4, false, (Function1) androidPopup_androidKt$Popup$popupLayout$1$1$1$1$1RememberedValue, 1, null);
                        ComposerKt.sourceInformationMarkerStart(composer2, 1842308930, "CC(remember):AndroidPopup.android.kt#9igjgp");
                        boolean zChangedInstance13 = composer2.changedInstance(popupLayout5);
                        final PopupLayout popupLayout6 = popupLayout5;
                        Object objRememberedValue6 = composer2.rememberedValue();
                        if (zChangedInstance13 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = (Function1) new Function1<IntSize, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$popupLayout$1$1$1$2$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                                    m7147invokeozmzZPI(intSize.m7069unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                                public final void m7147invokeozmzZPI(long j) {
                                    popupLayout6.m7150setPopupContentSizefhxjrPA(IntSize.m7057boximpl(j));
                                    popupLayout6.updatePosition();
                                }
                            };
                            composer2.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        Modifier modifierAlpha = AlphaKt.alpha(OnRemeasuredModifierKt.onSizeChanged(modifierSemantics$default, (Function1) objRememberedValue6), popupLayout5.getCanCalculatePosition() ? 1.0f : 0.0f);
                        Function2 function2Popup$lambda$1 = AndroidPopup_androidKt.Popup$lambda$1(stateRememberUpdatedState);
                        ComposerKt.sourceInformationMarkerStart(composer2, 26279861, "CC(SimpleStack)P(1)431@18599L899,431@18552L946:AndroidPopup.android.kt#2oxthz");
                        ComposerKt.sourceInformationMarkerStart(composer2, -514850408, "CC(remember):AndroidPopup.android.kt#9igjgp");
                        AndroidPopup_androidKt$SimpleStack$1$1 androidPopup_androidKt$SimpleStack$1$1RememberedValue = composer2.rememberedValue();
                        if (androidPopup_androidKt$SimpleStack$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                            androidPopup_androidKt$SimpleStack$1$1RememberedValue = AndroidPopup_androidKt$SimpleStack$1$1.INSTANCE;
                            composer2.updateRememberedValue(androidPopup_androidKt$SimpleStack$1$1RememberedValue);
                        }
                        MeasurePolicy measurePolicy4 = (MeasurePolicy) androidPopup_androidKt$SimpleStack$1$1RememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
                        int iM2 = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                        CompositionLocalMap currentCompositionLocalMap4 = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer2, modifierAlpha);
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor2);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM3454constructorimpl2 = Updater.m3454constructorimpl(composer2);
                        Updater.m3461setimpl(composerM3454constructorimpl2, measurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl2, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl2.rememberedValue(), Integer.valueOf(iM2))) {
                            composerM3454constructorimpl2.updateRememberedValue(Integer.valueOf(iM2));
                            composerM3454constructorimpl2.apply(Integer.valueOf(iM2), setCompositeKeyHash2);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl2, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                        function2Popup$lambda$1.invoke(composer2, 0);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
                composerStartRestartGroup.updateRememberedValue(popupLayout5);
                objRememberedValue = popupLayout5;
            } else {
                str2 = str;
                z2 = true;
                i7 = 256;
            }
            popupLayout = (PopupLayout) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122010421, "CC(remember):AndroidPopup.android.kt#9igjgp");
            boolean zChangedInstance13 = composerStartRestartGroup.changedInstance(popupLayout);
            int i15 = i3;
            i8 = i15 & 112;
            if (i8 == 32) {
                z3 = z2;
            } else {
                z3 = false;
            }
            boolean z15 = zChangedInstance13 | z3;
            i9 = i15 & 896;
            if (i9 == i7) {
                z4 = z2;
            } else {
                z4 = false;
            }
            LayoutDirection layoutDirection5 = layoutDirection;
            zChanged = z15 | z4 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection5.ordinal());
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                final String str13 = str2;
                objRememberedValue2 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        popupLayout.show();
                        popupLayout.updateParameters(function4, popupProperties4, str13, layoutDirection);
                        final PopupLayout popupLayout6 = popupLayout;
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                popupLayout6.disposeComposition();
                                popupLayout6.dismiss();
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                final String str14 = str2;
                objRememberedValue2 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        popupLayout.show();
                        popupLayout.updateParameters(function4, popupProperties4, str14, layoutDirection);
                        final PopupLayout popupLayout6 = popupLayout;
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                popupLayout6.disposeComposition();
                                popupLayout6.dismiss();
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.DisposableEffect(popupLayout, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122023212, "CC(remember):AndroidPopup.android.kt#9igjgp");
            boolean zChangedInstance14 = composerStartRestartGroup.changedInstance(popupLayout);
            if (i8 == 32) {
                z5 = z2;
            } else {
                z5 = false;
            }
            boolean z16 = zChangedInstance14 | z5;
            if (i9 == i7) {
                z6 = z2;
            } else {
                z6 = false;
            }
            zChanged2 = z16 | z6 | composerStartRestartGroup.changed(str2) | composerStartRestartGroup.changed(layoutDirection5.ordinal());
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!zChanged2) {
                final String str15 = str2;
                objRememberedValue3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
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
                        popupLayout.updateParameters(function4, popupProperties4, str15, layoutDirection);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                final String str16 = str2;
                objRememberedValue3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3$1
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
                        popupLayout.updateParameters(function4, popupProperties4, str16, layoutDirection);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.SideEffect((Function0) objRememberedValue3, composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122031599, "CC(remember):AndroidPopup.android.kt#9igjgp");
            boolean zChangedInstance15 = composerStartRestartGroup.changedInstance(popupLayout);
            i10 = i15 & 14;
            if (i10 == 4) {
                z7 = z2;
            } else {
                z7 = false;
            }
            z8 = zChangedInstance15 | z7;
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (!z8) {
                objRememberedValue4 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        popupLayout.setPositionProvider(popupPositionProvider2);
                        popupLayout.updatePosition();
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                objRememberedValue4 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        popupLayout.setPositionProvider(popupPositionProvider2);
                        popupLayout.updatePosition();
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.DisposableEffect(popupPositionProvider2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue4, composerStartRestartGroup, i10);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122054564, "CC(remember):AndroidPopup.android.kt#9igjgp");
            zChangedInstance = composerStartRestartGroup.changedInstance(popupLayout);
            androidPopup_androidKt$Popup$5$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (!zChangedInstance) {
                androidPopup_androidKt$Popup$5$1RememberedValue = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                composerStartRestartGroup.updateRememberedValue(androidPopup_androidKt$Popup$5$1RememberedValue);
            } else {
                androidPopup_androidKt$Popup$5$1RememberedValue = new AndroidPopup_androidKt$Popup$5$1(popupLayout, null);
                composerStartRestartGroup.updateRememberedValue(androidPopup_androidKt$Popup$5$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(popupLayout, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) androidPopup_androidKt$Popup$5$1RememberedValue, composerStartRestartGroup, 0);
            Modifier.Companion companion4 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122068302, "CC(remember):AndroidPopup.android.kt#9igjgp");
            zChangedInstance2 = composerStartRestartGroup.changedInstance(popupLayout);
            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (!zChangedInstance2) {
                objRememberedValue5 = (Function1) new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                        invoke2(layoutCoordinates);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LayoutCoordinates layoutCoordinates) {
                        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                        Intrinsics.checkNotNull(parentLayoutCoordinates);
                        popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            } else {
                objRememberedValue5 = (Function1) new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                        invoke2(layoutCoordinates);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LayoutCoordinates layoutCoordinates) {
                        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                        Intrinsics.checkNotNull(parentLayoutCoordinates);
                        popupLayout.updateParentLayoutCoordinates(parentLayoutCoordinates);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierOnGloballyPositioned4 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion4, (Function1) objRememberedValue5);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2122086420, "CC(remember):AndroidPopup.android.kt#9igjgp");
            zChangedInstance3 = composerStartRestartGroup.changedInstance(popupLayout) | composerStartRestartGroup.changed(layoutDirection5.ordinal());
            measurePolicyRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!zChangedInstance3) {
                measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i16) {
                        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i16);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i16) {
                        return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i16);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i16) {
                        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i16);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i16) {
                        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i16);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo72measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                        popupLayout.setParentLayoutDirection(layoutDirection);
                        return MeasureScope.CC.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope placementScope) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }
                        }, 4, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(measurePolicyRememberedValue);
            } else {
                measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i16) {
                        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i16);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i16) {
                        return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i16);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i16) {
                        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i16);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i16) {
                        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i16);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo72measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                        popupLayout.setParentLayoutDirection(layoutDirection);
                        return MeasureScope.CC.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$8$1.1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope placementScope) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }
                        }, 4, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(measurePolicyRememberedValue);
            }
            MeasurePolicy measurePolicy4 = (MeasurePolicy) measurePolicyRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            iM = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnGloballyPositioned4);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3454constructorimpl.getInserting()) {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(iM));
                composerM3454constructorimpl.apply(Integer.valueOf(iM), setCompositeKeyHash);
            } else {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(iM));
                composerM3454constructorimpl.apply(Integer.valueOf(iM), setCompositeKeyHash);
            }
            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -958253783, "C:AndroidPopup.android.kt#2oxthz");
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function3 = function4;
            popupProperties3 = popupProperties4;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.9
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

                public final void invoke(Composer composer2, int i16) {
                    AndroidPopup_androidKt.Popup(popupPositionProvider2, function3, popupProperties3, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int createFlags(boolean z, SecureFlagPolicy secureFlagPolicy, boolean z2) {
        int i = !z ? 262152 : 262144;
        if (secureFlagPolicy == SecureFlagPolicy.SecureOn) {
            i |= 8192;
        }
        return !z2 ? i | 512 : i;
    }

    public static final ProvidableCompositionLocal<String> getLocalPopupTestTag() {
        return LocalPopupTestTag;
    }

    public static final void PopupTestTag(final String str, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1357513789);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PopupTestTag)P(1)423@18214L75:AndroidPopup.android.kt#2oxthz");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1357513789, i2, -1, "androidx.compose.ui.window.PopupTestTag (AndroidPopup.android.kt:422)");
            }
            CompositionLocalKt.CompositionLocalProvider(LocalPopupTestTag.provides(str), function2, composerStartRestartGroup, (i2 & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.PopupTestTag.1
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
                    AndroidPopup_androidKt.PopupTestTag(str, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    private static final void SimpleStack(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 26279861, "CC(SimpleStack)P(1)431@18599L899,431@18552L946:AndroidPopup.android.kt#2oxthz");
        ComposerKt.sourceInformationMarkerStart(composer, -514850408, "CC(remember):AndroidPopup.android.kt#9igjgp");
        AndroidPopup_androidKt$SimpleStack$1$1 androidPopup_androidKt$SimpleStack$1$1RememberedValue = composer.rememberedValue();
        if (androidPopup_androidKt$SimpleStack$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
            androidPopup_androidKt$SimpleStack$1$1RememberedValue = AndroidPopup_androidKt$SimpleStack$1$1.INSTANCE;
            composer.updateRememberedValue(androidPopup_androidKt$SimpleStack$1$1RememberedValue);
        }
        MeasurePolicy measurePolicy = (MeasurePolicy) androidPopup_androidKt$SimpleStack$1$1RememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        int i2 = ((i << 3) & 112) | ((i >> 3) & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
        ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
        int iM = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifier);
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        int i3 = ((i2 << 6) & 896) | 6;
        ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
        if (!(composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor);
        } else {
            composer.useNode();
        }
        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer);
        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(iM))) {
            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(iM));
            composerM3454constructorimpl.apply(Integer.valueOf(iM), setCompositeKeyHash);
        }
        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
        function2.invoke(composer, Integer.valueOf((i3 >> 6) & 14));
        composer.endNode();
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
    }

    public static final boolean isFlagSecureEnabled(View view) {
        ViewGroup.LayoutParams layoutParams = view.getRootView().getLayoutParams();
        WindowManager.LayoutParams layoutParams2 = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        return (layoutParams2 == null || (layoutParams2.flags & 8192) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int flagsWithSecureFlagInherited(PopupProperties popupProperties, boolean z) {
        if (popupProperties.getInheritSecurePolicy() && z) {
            return popupProperties.getFlags() | 8192;
        }
        if (popupProperties.getInheritSecurePolicy() && !z) {
            return popupProperties.getFlags() & (-8193);
        }
        return popupProperties.getFlags();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntRect toIntBounds(Rect rect) {
        return new IntRect(rect.left, rect.top, rect.right, rect.bottom);
    }

    public static /* synthetic */ boolean isPopupLayout$default(View view, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return isPopupLayout(view, str);
    }

    public static final boolean isPopupLayout(View view, String str) {
        if (view instanceof PopupLayout) {
            return str == null || Intrinsics.areEqual(str, ((PopupLayout) view).getTestTag());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2<Composer, Integer, Unit> Popup$lambda$1(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        return (Function2) state.getValue();
    }
}
